/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Davide
 */
public class NameGenerator {
    
    //Frequenze dei caratteri italiani
    private final static double[] freq = {10.85,1.05,4.30,3.39,11.49,1.01,1.65,1.43,10.18,0,0,5.70,
                             2.87,7.02,9.97,2.96,0.45,6.19,5.48,6.97,3.16,1.75,0,0,0,0.75};
    
    //Somme parziali delle frequenze
    private final static double[] partials = {10.85,11.90,16.20,19.59,31.08,32.09,
                                              33.74,35.17,45.35,45.35,45.35,51.05,
                                              53.92,60.94,70.91,73.87,74.32,80.51,
                                              85.99,92.96,96.12,97.87,97.87,97.87,
                                              97.87,98.62};
    
    //Somma di tutte le frequenze
    private final static double sum = 98.61999999999999;
    
    private Random r;

    public NameGenerator(Random r) {
        this.r = r;
    }

    public NameGenerator() {
        this.r = new Random();
    }
    
    
    
    /**
     * Ritorna un carattere casuale, con distribuzione simile a quella della lingua italiana
     * @return 
     */
    private char getRandomChar ()
    {
        
        double rnd = r.nextFloat()*sum;
        
        for (int i = 0; i<partials.length; i++)
        {
            if (rnd <= partials[i])
                return (char)((int)'a'+i);
        }
        
        return 'z';
    }
    
    /**
     * Dice se un carattere è vocale
     * @param c
     * @return 
     */
    private boolean isVocal (char c)
    {
        char[] vocals = {'a', 'e', 'i', 'o', 'u'};
        
        for (char vocal : vocals)
        {
            if (c == vocal)
                return true;
        }
        
        return false;
    }
    
    /**
     * Ritorna un nome pseudo-italiano
     * @param lettersMin Numero minimo di lettere
     * @param lettersMax Numero massimo di lettere
     * @return 
     */
    public String getRandomName (int lettersMin, int lettersMax)
    {
        
        int letters = lettersMin + r.nextInt(lettersMax-lettersMin);
        
        StringBuilder sb = new StringBuilder();
        
        int vocalCount = 0;
        int consCount = 0;
        
        char prev = '$';
        
        int i = 0;
        
        while (i<letters)
        {
            char act = getRandomChar();
            
            if (isVocal(act))
            {
                if (isVocal(prev) && act != prev && vocalCount < 2)
                {
                    prev = act;
                    sb.append(act);
                    vocalCount++;
                    i++;
                } 
                else if (!isVocal(prev))
                {
                    consCount = 0;
                    prev = act;
                    sb.append(act);
                    vocalCount++;
                    i++;
                }
                    
            }
            else
            {
                if (!isVocal(prev) && consCount < 2)
                {
                    prev = act;
                    sb.append(act);
                    consCount++;
                    i++;
                }
                else if (isVocal(prev))
                {
                    vocalCount = 0;
                    prev = act;
                    sb.append(act);
                    consCount++;
                    i++;
                }
            }
            
            
            
            
        }
        
        return sb.toString();
    }
    
    /**
     * Usato per contenere i nomi già estratti
     */
    private final List<String> alreadyExtracted = new ArrayList<>();
    
    /**
     * Ritorna un nome pseudo-italiano unico
     * @param lettersMin
     * @param lettersMax
     * @return 
     */
    public String getUniqueRandomName (int lettersMin, int lettersMax)
    {
        String res = getRandomName(lettersMin, lettersMax);
        
        while (alreadyExtracted.contains(res))
        {
            res = getRandomName(lettersMin, lettersMax);
        }
        
        alreadyExtracted.add(res);
        
        return res;
    }
}
