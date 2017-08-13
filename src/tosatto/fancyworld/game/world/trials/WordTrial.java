/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.common.UniqueRandomGenerator;

/**
 *
 * @author Davide
 */
@Root(name = "WordTrial")
public class WordTrial implements Trial{

    private final static int MINIMUM_WORD_SIZE = 5;
    public final static int MAXIMUM_TRIAL_NUMBER = 3;
    private final static double ALIVE_CHARACTERS_RATIO = 0.7;
    
    private int trialCount;
    private String extractedWord;
    private String obfuscatedWord;
    
    @Override
    public int getValue() {
        return 10;
    }

    @Override
    public String getType() {
        return "Word";
    }
    
    public int getMaxTrialNumber()
    {
        return MAXIMUM_TRIAL_NUMBER;
    }
    
    public int getCurrentTrialCount()
    {
        return this.trialCount;
    }
    
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
    
    public String getObfuscatedWord()
    {
        return this.obfuscatedWord;
    }
    
    public String getRealWord()
    {
        return this.extractedWord;
    }
    
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
    
}
