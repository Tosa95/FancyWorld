/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.Random;

/**
 *
 * @author davide
 */
public class Helper {
    
    /**
     * Ritorna un intero casuale contenuto in [min,max[
     * 
     * Precondizione: max > min
     * 
     * @param r 
     * @param min
     * @param max
     * @return 
     */    
    public static int boundRnd (Random r, int min, int max)
    {
        
        return min + r.nextInt(max-min);
    }
}
