/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe helper utile per generare numeri casuali univoci
 * @author Davide
 */
public class UniqueRandomGenerator {

    private Random r;
    private List<Integer> extractedInts = new ArrayList<>();
    
    public UniqueRandomGenerator() {
        r = new Random();
    }

    public UniqueRandomGenerator(Random r) {
        this.r = r;
    }
    
    /**
     * Ritorna un intero compreso tra min (incluso) e max (escluso)
     * @param min 
     * @param max
     * @return 
     * 
     * Precondizione: ci deve essere almeno un intero non ancora estratto nell'intervallo considerato
     * Postcondizione: l'intero ritornato non sarà mai più dato in output da questo metodo (per la stessa istanza)
     */
    public int getUniqueInt (int min, int max)
    {
        int res = 0;
        
        do
        {
            res = getInt(min, max); 
        }while(extractedInts.contains(res));
        
        extractedInts.add(res);
        
        return res;
    }
    
    /**
     * Ritorna un intero NON unico tra min(inclso) e max(escluso) 
     * @param min
     * @param max
     * @return 
     */
    public int getInt (int min, int max)
    {
        return min + r.nextInt(max-min);
    }
    
    /**
     * Ritorna un boolean.
     * 
     * 
     * @param p Probabilità che sia vero
     * @return 
     */
    public boolean getRandomBooleanWithProbability(double p)
    {
        int res = getInt(0, 1000);
        
        int bound = (int)(p*1000);
        
        return res < bound;
        
    }
    
}
