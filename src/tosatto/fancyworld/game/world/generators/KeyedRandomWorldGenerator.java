/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.game.KeyedGameInfo;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.KeyedPlace;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author davide
 */
public class KeyedRandomWorldGenerator implements RandomWorldGenerator{
    
    private NameGenerator ng;
    private WorldFactory wf;
    private Random r;
    
    private RandomWorldGenerator rwg;
    
    private int minKeys, maxKeys;
    
    private double keyPlaceProb, keyPassageProb;
    
    public KeyedRandomWorldGenerator(RandomWorldGenerator rwg, int minKeys, int maxKeys, double keyPlaceProb, double keyPassageProb)
    {
        this.rwg = rwg;
        
        this.ng = rwg.getNameGenerator();
        this.wf = rwg.getWorldFactory();
        this.r = rwg.getRandom();
        
        this.minKeys = minKeys;
        this.maxKeys = maxKeys;
        
        this.keyPlaceProb = keyPlaceProb;
        this.keyPassageProb = keyPassageProb;
    }

    private void placeRandomKeys (KeyedWorld w)
    {   
        String[] keys = w.getKeys().keySet().toArray(new String[w.getKeys().keySet().size()]);
        
        for (Place p : w.getPlaces())
        {
            KeyedPlace kp = (KeyedPlace) p;
        
            if (r.nextDouble()<keyPlaceProb)
                kp.setKey(keys[r.nextInt(keys.length)]);
        }
    }
    
    private void mergeHM (HashMap <String, Set<String>> h1, HashMap <String, Set<String>> h2)
    {
        HashMap <String, Set<String>> res = new HashMap<>(h1);
        
        for (String key : h2.keySet())
        {
            if (h1.containsKey(key))
            {
                Set<String> merged = new HashSet<>(h1.get(key));
                
                merged.addAll(h2.get(key));
                
                res.put(key, merged);
            }else{
                res.put(key, h2.get(key));
            }
        }
        
        h1.clear();
        h1.putAll(res);
    }
    
    private boolean setEq (Set<String> s1, Set<String> s2)
    {
        for (String str : s1)
        {
            if (!s2.contains(str))
            {
                return false;
            }
        }
        
        for (String str : s2)
        {
            if (!s1.contains(str))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private int callCount = 0;
    
    private void getAvailableKeysAtPlaces (World w, String actPlace, Set<String> availKeys, HashMap <String, Set<String>> hm)
    {
        //System.out.println ("Visiting " + actPlace);
        
        callCount++;
        
        HashMap<String, Set<String>> temp = new HashMap<>();
        
        availKeys = new HashSet<>(availKeys);
        
        KeyedPlace pl = (KeyedPlace)w.getPlace(actPlace);
        
        if (pl.hasKey() && !availKeys.contains(pl.getKey()))
            availKeys.add(pl.getKey());

        if (hm.containsKey(actPlace) && setEq(hm.get(actPlace), availKeys))
        {
             return;
        }
        
        temp.put(actPlace, availKeys);
        mergeHM(hm, temp);
        
        for (Passage p : w.getAllPassages(w.getPlace(actPlace).getPassages().values()))
        {
            KeyedPassage kp = (KeyedPassage)p;
            
            try {
                String nextPlace = w.getPlace(p.next(actPlace)).getName();
                
                if (!kp.isClosed()||availKeys.contains(kp.requiredKey()))
                {
                    getAvailableKeysAtPlaces(w, nextPlace, availKeys, hm);
                }
            } catch (PassageException ex) {
                //Ok, passaggio murato
            }
            
        }
        
        System.out.println("Done " + actPlace);
    }
    
    private HashMap <String, Set<String>> getAvailableKeysAtPlaces (World w)
    {
        HashMap <String, Set<String>> res = new HashMap<>();
        getAvailableKeysAtPlaces(w, w.getStartPlace(), new HashSet<>(), res);
        return res;
    }
    
    @Override
    public World generate() {
        
        KeyedWorld res = (KeyedWorld) rwg.generate();
        
        res.setGi(new KeyedGameInfo() {
            @Override
            public boolean playerHasKey(String keyName) {
                return true;
            }
        });
        
        int keys = Helper.boundRnd(r, minKeys, maxKeys);
        
        for (int i = 0; i < keys; i++)
        {
            res.addKey(new Key(ng.getUniqueRandomName(2, 4)));
        }
        
        placeRandomKeys(res);
        
        HashMap<String, Set<String>> availK = getAvailableKeysAtPlaces(res);
        
        return res;
        
    }

    @Override
    public Random getRandom() {
        return r;
    }

    @Override
    public NameGenerator getNameGenerator() {
        return ng;
    }

    @Override
    public WorldFactory getWorldFactory() {
        return wf;
    }
    
    
}
