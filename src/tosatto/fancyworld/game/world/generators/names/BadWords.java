/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.names;

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
public class BadWords {
    
    private final static String WORDS_FILE_NAME = "badwords.txt";
    
    private List<String> words = new ArrayList<>();

    private static BadWords instance = null;
   
    /**
     * Pattern singleton, ritorna l'unica istanza
     * @return
     * @throws IOException 
     */
    public static BadWords getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new BadWords();
        }
        
        return instance;
    }
    
    /**
     * Costruttore privato per pattern singleton
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private BadWords() throws FileNotFoundException, IOException {
        
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
    
    /**
     * Controlla se la parola passata fa parte di quelle considerate "cattive"
     * @param word
     * @return 
     */
    public boolean isBad(String word)
    {
        return words.contains(word);
    }
}

