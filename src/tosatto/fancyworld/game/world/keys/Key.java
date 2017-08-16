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
    
    @Attribute (name = "weight")
    private int weight;

    public Key(@Attribute (name = "name") String name, @Attribute (name = "weight") int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
}
