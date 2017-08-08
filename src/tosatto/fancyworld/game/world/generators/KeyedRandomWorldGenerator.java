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
    
    private final static int MAX_KEY_WEIGHT = 25;
    
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
            if (!p.isGoal() && !p.getName().equals("start"))
            {
                KeyedPlace kp = (KeyedPlace) p;

                if (r.nextDouble()<keyPlaceProb)
                    kp.setKey(keys[r.nextInt(keys.length)]);
            }
        }
    }
    
    /**
     * Unisce due mappe nomePosto:insiemeChiavi
     * @param h1 Prima mappa, contiene anche il risultato
     * @param h2 Seconda mappa
     */
    private void mergeHM (HashMap <String, Set<String>> h1, HashMap <String, Set<String>> h2)
    {
        HashMap <String, Set<String>> res = new HashMap<>(h1);
        
        for (String key : h2.keySet())
        {
            if (h1.containsKey(key) /*Il posto c'è in entrambe le mappe*/)
            {
                Set<String> merged = new HashSet<>(h1.get(key));
                
                merged.addAll(h2.get(key)); /*unisce le chiavi*/
                
                res.put(key, merged);
            }else{
                res.put(key, h2.get(key));
            }
        }
        
        h1.clear();
        h1.putAll(res);
    }
    
    /**
     * Controlla se s1 contiene s2
     * @param s1 Primo insieme
     * @param s2 Secondo insieme
     * @return true: s2 <- s1, false altrimenti
     */
    private boolean includeSet (Set<String> s1, Set<String> s2)
    {
        
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
    
    /**
     * Versione modificata dell'algoritmo di Djikstra per calcolare le chiavi effettivamente
     * possibili da avere in ogni posto.
     * @param w Mondo in esame
     * @param actPlace Posto attuale
     * @param availKeys Chiavi che si possono avere con il cammino in esame
     * @param hm Risultato, mappa [nomeposto, lista chiavi]
     */
    private void getAvailableKeysAtPlaces (World w, String actPlace, Set<String> availKeys, HashMap <String, Set<String>> hm)
    {
        //System.out.println ("Visiting " + actPlace);
        
        //System.out.println("Begun " + actPlace + "   " + Integer.toString(callCount));
        
        //callCount++;
        
        HashMap<String, Set<String>> temp = new HashMap<>();
        
        availKeys = new HashSet<>(availKeys);
        
        KeyedPlace pl = (KeyedPlace)w.getPlace(actPlace);
        
        if (pl.hasKey() && !availKeys.contains(pl.getKey()))
            availKeys.add(pl.getKey());

        if (hm.containsKey(actPlace) /*sono già passato di qua*/ && 
                includeSet(hm.get(actPlace), availKeys) /*il percorso attuale non 
                aggiunge chiavi a quelle già ottenibili nel posto*/)
        {
             return;
        }
        
        //Se arrivo qua significa che con il percorso attuale sto aggiungendo chiavi
        //a quelle ottenibili dal posto, quindi devo rivalutare tutti gli altri posti
        //collegati per vedere se aggiungo anche a loro
        
        temp.put(actPlace, availKeys);
        mergeHM(hm, temp); //Aggiungo le info sulle chiavi ottenibili alla mappa principale
        
        
        for (Passage p : w.getAllPassages(w.getPlace(actPlace).getPassages().values()))
        {
            KeyedPassage kp = (KeyedPassage)p;
            
            try {
                String nextPlace = w.getPlace(p.next(actPlace)).getName();
                
                if (!kp.isClosed()/*se è un passaggio aperto*/||availKeys.contains(kp.requiredKey())/*oppure ho la chiave*/ )
                {
                    /*provo a passare, se era murato, mi da eccezione*/
                    getAvailableKeysAtPlaces(w, nextPlace, availKeys, hm);
                }
            } catch (PassageException ex) {
                //Ok, passaggio murato
            }
            
        }
        
        //System.out.println("Done " + actPlace + "   " + Integer.toString(callCount));
        //callCount--;
    }
    
    /**
     * Wrapper per getAvailableKeysAtPlaces con più parametri, in modo da semplificare la chiamata
     * @param w Il mondo 
     * @return la mappa contenente le chiavi ottenibili in ogni posto
     */
    private HashMap <String, Set<String>> getAvailableKeysAtPlaces (World w)
    {
        HashMap <String, Set<String>> res = new HashMap<>();
        getAvailableKeysAtPlaces(w, w.getStartPlace(), new HashSet<>(), res);
        return res;
    }
    
    /**
     * Dice se è possibile raggiungere tutti i posti, tenendo in considerazione anche
     * le chiavi 
     * @param w
     * @return 
     */
    public boolean connected (World w)
    {
        //Basta che l'algoritmo di mapping posto-chiavi sia in grado di raggiungere tutti i posti
        return getAvailableKeysAtPlaces(w).keySet().size() == w.getPlaces().size();
    }
    
    /**
     * Controlla se il goal è raggiungibile con le chiavi correntemente piazzate
     * @param w
     * @return 
     */
    public boolean canReachGoal (World w)
    {
        String goal = "";
        
        for (Place p : w.getPlaces())
        {
            if (p.isGoal())
                goal = p.getName();
        }
        
        HashMap <String, Set<String>> map = getAvailableKeysAtPlaces(w);
        
        return map.containsKey(goal);
    }
    
    @Override
    public World generate() {
        
        //Fa generare il mondo senza chiavi al generatore di cui è decorator
        KeyedWorld res = (KeyedWorld) rwg.generate();
        
        
        res.setGi(new KeyedGameInfo() {
            @Override
            public boolean playerHasKey(String keyName) {
                return true;
            }

            @Override
            public int getKeyWeight(String keyName) {
                return 0;
            }
        });
        
        int keys = Helper.boundRnd(r, minKeys, maxKeys);
        
        List<Key> keyList = new ArrayList<>();
        
        //Crea chiavi a caso
        for (int i = 0; i < keys; i++)
        {
            Key k = new Key(ng.getUniqueRandomName(2, 4), r.nextInt(MAX_KEY_WEIGHT + 1));
            res.addKey(k);
            keyList.add(k);
        }
        
        //Piazza chiavi a caso nei vari posti
        placeRandomKeys(res);
        
        //Chiude a chiave certi passaggi
        for (Passage p : res.getAllPassages())
        {
            KeyedPassage kp = (KeyedPassage)p;
            
            //Lo chiude se il numero estratto è sotto alla probabilità prescelta
            if (r.nextDouble()<keyPassageProb)
            {
                System.out.println("K");
                
                //Sceglie una chiave a caso
                Key k = keyList.get(r.nextInt(keyList.size()));
                
                //La usa per chiudere il passaggio
                kp.setKey(k.getName());
                
                //Controlla che il goal sia ancora raggiungibile
                if (!canReachGoal(res)){
                    System.out.println("NC");
                    //Se non è raggiungibile, riapre il passaggio
                    kp.setKey(null);
                }
            }
        }
        
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
