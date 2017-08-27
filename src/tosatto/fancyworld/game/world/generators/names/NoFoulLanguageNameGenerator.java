/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.names;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Oggetto che ha il compito di controllare che un generatore non produca parole offensive.
 * 
 * Si applica al di sopra di un altro name generator seguendo il pattern decorator.
 * 
 * @author Davide
 */
public class NoFoulLanguageNameGenerator implements NameGenerator{

    private NameGenerator base;

    private List<String> filtered = new ArrayList<>();
    
    public NoFoulLanguageNameGenerator(NameGenerator base) {
        this.base = base;
    }
    
    @Override
    public String getRandomName(int lettersMin, int lettersMax) {
        
        String res = "";
        
        try {

            do{
                res = base.getRandomName(lettersMin, lettersMax);
                
                if (BadWords.getInstance().isBad(res))
                    getFiltered().add(res);
                
            }while(BadWords.getInstance().isBad(res));
            
        } catch (IOException ex) {
            
            //Nel dubbio, piuttosto che ritornare una parola offensiva, si ritorna null
            return null;
            
        }
        
        return res;
        
    }

    @Override
    public String getUniqueRandomName(int lettersMin, int lettersMax) {
        
        String res = "";
        
        try {

            do{
                res = base.getUniqueRandomName(lettersMin, lettersMax);
            }while(BadWords.getInstance().isBad(res));
            
            if (BadWords.getInstance().isBad(res))
                    getFiltered().add(res);
            
        } catch (IOException ex) {
            
            //Nel dubbio, piuttosto che ritornare una parola offensiva, si ritorna null
            return null;
            
        }
        
        return res;
    }

    /**
     * @return the filtered
     */
    public List<String> getFiltered() {
        return filtered;
    }
    
    
    
}
