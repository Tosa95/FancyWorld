/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Rappresenta un passaggio
 * @author Davide
 */
@Root
public abstract class Passage {
    
    @Attribute (name = "name")
    private String name;
    
    protected GameInfo gi;
    
    public Passage (@Attribute (name = "name") String name)
    {
        this.name = name;
    }

    /**
     * Imposta un oggetto di tipo GameInfo per permettere di accedere ad alcune delle funzionalità del 
     * gioco senza generare una dipendenza ciclica
     * @param gi 
     */
    public void setGi(GameInfo gi) {
        this.gi = gi;
    }
    
    /**
     * Ritorna il nome del luogo collegato
     * @param actPlace
     * @return
     * @throws PassageException 
     */
    public abstract String next (String actPlace) throws PassageException;
    
    /**
     * Ritorna il nome del passaggio
     * @return 
     */
    public String getName ()
    {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
