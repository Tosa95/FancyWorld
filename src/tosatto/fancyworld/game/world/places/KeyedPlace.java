/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.places;

import org.simpleframework.xml.Attribute;

/**
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

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
    public boolean hasKey ()
    {
        return key!=null;
    }
}
