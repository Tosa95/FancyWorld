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
import tosatto.fancyworld.game.info.KeyedGameInfo;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.generators.algorithms.GoalReachableChecker;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.KeyedPlace;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Genera la disposizione di chiavi e passaggi chiusi all'interno di un mondo.
 * 
 * Funge da decorator per un altro RandomWorldGenerator.
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
    
    private int maxKeyWeight;
    
    private GoalReachableChecker grc;
    
    
    private static double DEFAULT_KEY_PLACE_PROBABILITY = 0.3;
    private static double DEFAULT_KEY_PASSAGE_PROBABILITY = 1;
    private static int DEFAULT_MAX_KEY_WEIGHT = 20;
    private static int DEFAULT_MIN_KEYS = 5;
    private static int DEFAULT_MAX_KEYS = 10;
    
    /**
     * Inizializza il generatore
     * 
     * Precondizione:
     *  - maxKeys > minKeys
     * 
     * @param rwg Generatore base
     * @param minKeys Minimo numero di tipi di chiave
     * @param maxKeys Massimo numero di tipi di chiave
     * @param maxKeyWeight Masimo peso di una chiave (minimo 1)
     * @param keyPlaceProb Probabilità del posizionamento di una chiave in un posto
     * @param keyPassageProb Probabilità della chiusura a chiave di un passaggio
     */
    public KeyedRandomWorldGenerator(RandomWorldGenerator rwg, int minKeys, 
            int maxKeys, int maxKeyWeight,
            double keyPlaceProb, double keyPassageProb, GoalReachableChecker grc)
    {
        this.rwg = rwg;
        
        this.ng = rwg.getNameGenerator();
        this.wf = rwg.getWorldFactory();
        this.r = rwg.getRandom();
        
        this.minKeys = minKeys;
        this.maxKeys = maxKeys;
        this.maxKeyWeight = maxKeyWeight;
        
        this.keyPlaceProb = keyPlaceProb;
        this.keyPassageProb = keyPassageProb;
        
        this.grc = grc;
    }

    public KeyedRandomWorldGenerator(RandomWorldGenerator rwg, GoalReachableChecker grc)
    {
        this(rwg, DEFAULT_MIN_KEYS, DEFAULT_MAX_KEYS, DEFAULT_MAX_KEY_WEIGHT, DEFAULT_KEY_PLACE_PROBABILITY, DEFAULT_KEY_PASSAGE_PROBABILITY, grc);
    }
    
    private void placeRandomKeys (KeyedWorld w)
    {   
        String[] keys = w.getKeys().keySet().toArray(new String[w.getKeys().keySet().size()]);
        
        for (Place p : w.getPlaces())
        {
            if (!p.isGoal() && !p.getName().equals("start"))
            {
                KeyedPlace kp = (KeyedPlace) p;

                if (r.nextDouble()<getKeyPlaceProb())
                    kp.setKey(keys[r.nextInt(keys.length)]);
            }
        }
    }
    
    /**
     * Genera un mondo dotato di chiavi e di passaggi chiusi a chiave
     * 
     * Postcondizione:
     *  - il luogo goal è raggiubngibile anche tenendo conto delle chiavi ottenibili e dei passaggi chiusi a chiave
     * 
     * @return 
     */
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
        
        int keys = Helper.boundRnd(r, getMinKeys(), getMaxKeys());
        
        List<Key> keyList = new ArrayList<>();
        
        //Crea chiavi a caso
        for (int i = 0; i < keys; i++)
        {
            Key k = new Key(ng.getUniqueRandomName(2, 4), 1 + r.nextInt(getMaxKeyWeight()));
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
            if (r.nextDouble()<getKeyPassageProb())
            {
                //System.out.println("K");
                
                //Sceglie una chiave a caso
                Key k = keyList.get(r.nextInt(keyList.size()));
                
                //La usa per chiudere il passaggio
                kp.setKey(k.getName());
                
                //Controlla che il goal sia ancora raggiungibile
                if (!grc.isGoalReachable(res)){
                    //System.out.println("NC");
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

    /**
     * @return the minKeys
     */
    public int getMinKeys() {
        return minKeys;
    }

    /**
     * @param minKeys the minKeys to set
     */
    public void setMinKeys(int minKeys) {
        this.minKeys = minKeys;
    }

    /**
     * @return the maxKeys
     */
    public int getMaxKeys() {
        return maxKeys;
    }

    /**
     * @param maxKeys the maxKeys to set
     */
    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    /**
     * @return the keyPlaceProb
     */
    public double getKeyPlaceProb() {
        return keyPlaceProb;
    }

    /**
     * @param keyPlaceProb the keyPlaceProb to set
     */
    public void setKeyPlaceProb(double keyPlaceProb) {
        this.keyPlaceProb = keyPlaceProb;
    }

    /**
     * @return the keyPassageProb
     */
    public double getKeyPassageProb() {
        return keyPassageProb;
    }

    /**
     * @param keyPassageProb the keyPassageProb to set
     */
    public void setKeyPassageProb(double keyPassageProb) {
        this.keyPassageProb = keyPassageProb;
    }

    /**
     * @return the maxKeyWeight
     */
    public int getMaxKeyWeight() {
        return maxKeyWeight;
    }

    /**
     * @param maxKeyWeight the maxKeyWeight to set
     */
    public void setMaxKeyWeight(int maxKeyWeight) {
        this.maxKeyWeight = maxKeyWeight;
    }
    
    
}
