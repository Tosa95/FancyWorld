/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.levels;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.info.GameInfo;

/**
 * Rappresentas un livello
 * @author Davide
 */
@Root (name = "Level")
public class Level {
    
    @Attribute (name = "id")
    private int id;
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "description")
    private String description;

    protected GameInfo gi;
    
    public Level(@Attribute (name = "id") int id,
                 @Attribute (name = "name") String name,
                 @Attribute (name = "description") String description) 
    {
        this.id = id;
        this.description = description;
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
     * Ritorna la descrizione del livello
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ritorna il nonme del livello
     * @return 
     */
    public String getName() {
        return name;
    }

    
    /**
     * Ritorna l'identificativo del livello
     * @return 
     */
    public int getId() {
        return id;
    }
    
    
    
}
