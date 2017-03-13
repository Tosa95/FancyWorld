/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.keys;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 *
 * @author davide
 */
@Root (name = "Key")
public class Key {
    
    @Attribute (name = "name")
    private String name;

    public Key(@Attribute (name = "name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
