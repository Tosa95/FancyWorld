/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.places;

import org.simpleframework.xml.Attribute;

/**
 *
 * Rappresenta un luogo eventualmente dotato di chiave
 * 
 * @author davide
 */
public class KeyedPlace extends Place{
    
    @Attribute (name = "key", required = false)
    private String key = null;
    
    public KeyedPlace(@Attribute (name = "name") String name,
                  @Attribute (name = "description") String description,
                  @Attribute (name = "goal") boolean goal,
                  @Attribute (name = "level") int level) {
        super(name, description, goal, level);
    }

    /**
     * Imposta il tipo di chiave presente nel luogo
     * 
     * Precondizione: 
     *  - il tipo di chiave passato fa parte del mondo
     * 
     * @param key 
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Ritorna il tipo di chiave presente nel luogo (null se non presente)
     * @return 
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Dice se il luogo possiede una chiave
     * @return 
     */
    public boolean hasKey ()
    {
        return key!=null;
    }
}
