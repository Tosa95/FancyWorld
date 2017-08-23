/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.common.UniqueRandomGenerator;

/**
 * Rappresenta una prova in cui il giocatore deve indovinare una parola, dopo aver preso
 * visione di una versione di essa con alcune lettere offuscate
 * @author Davide
 */
@Root(name = "WordTrial")
public class WordTrial implements Trial{

    private final static int MINIMUM_WORD_SIZE = 5;
    
    //Numero massimo di tentativi ammessi
    public final static int MAXIMUM_TRIAL_NUMBER = 3;
    
    //Probabilità di un carattere di non venire offuscato
    private final static double ALIVE_CHARACTERS_RATIO = 0.7;
    
    private int trialCount;
    private String extractedWord;
    private String obfuscatedWord;
    
    @Attribute
    private int value = 10;
    
    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Word";
    }
    
    /**
     * Ritorna il numero massimo di tentativi
     * @return 
     */
    public int getMaxTrialNumber()
    {
        return MAXIMUM_TRIAL_NUMBER;
    }
    
    /**
     * Ritorna il numero attuale di tentativi
     * @return 
     */
    public int getCurrentTrialCount()
    {
        return this.trialCount;
    }
    
    /**
     * Offusca alcuni caratteri in una parola, secondo la probabilità aliveRatio
     * @param str
     * @param aliveRatio
     * @return 
     */
    private String obfuscate (String str, double aliveRatio)
    {
        UniqueRandomGenerator r = new UniqueRandomGenerator();
        
        StringBuilder res = new StringBuilder(str);
        
        int toBeObfuscated = (int)(str.length() * (1 - aliveRatio));
        
        for (int i = 0; i < toBeObfuscated; i++)
        {
            res.setCharAt(r.getUniqueInt(0, str.length()), '*');
        }
        
        return res.toString();
    }
    
    /**
     * Inizializza la prova
     */
    public void init()
    {
        try {
            
            this.extractedWord = Words.getInstance().getRandomWord(MINIMUM_WORD_SIZE);
            this.obfuscatedWord = obfuscate(this.extractedWord, ALIVE_CHARACTERS_RATIO);
            this.trialCount = 0;
            
        } catch (IOException ex) {
            Logger.getLogger(WordTrial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Ritorna la parola offuscata
     * @return 
     */
    public String getObfuscatedWord()
    {
        return this.obfuscatedWord;
    }
    
    /**
     * Ritorna la parola originale
     * @return 
     */
    public String getRealWord()
    {
        return this.extractedWord;
    }
    
    /**
     * Dice se la parola passata corrisponde a quella originale (non offuscata)
     * @param word
     * @return
     * @throws MaxTrialCountReachedException 
     */
    public boolean isExact(String word) throws MaxTrialCountReachedException
    {
        if(trialCount < MAXIMUM_TRIAL_NUMBER)
        {
            if (word.equals(extractedWord))
            {
                return true;
            } else {
                trialCount++;
                return false;
            }
        } else {
            throw new MaxTrialCountReachedException("Conteggio massimo di tentativi raggiunto");
        }
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
    
}
