/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Davide
 */
public class Words {
    
    private final static String WORDS_FILE_NAME = "words.txt";
    
    private List<String> words = new ArrayList<>();

    private static Words instance = null;
    
    public static Words getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new Words();
        }
        
        return instance;
    }
    
    private Words() throws FileNotFoundException, IOException {
        
        try(BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(WORDS_FILE_NAME))))
        {
            String line;
            while ((line = br.readLine())!=null)
            {
                words.add(line);
            }
        }
        
    
    }
    
    public String getRandomWord (int minLen)
    {
        String res = "";
        
        Random r = new Random();
        
        while (res.length() < minLen)
        {
            int index = r.nextInt(words.size());
            
            res = words.get(index);
        }
        
        return res;
    }
}
