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
 *
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
            res = min + r.nextInt(max-min); 
        }while(extractedInts.contains(res));
        
        extractedInts.add(res);
        
        return res;
    }
    
}
