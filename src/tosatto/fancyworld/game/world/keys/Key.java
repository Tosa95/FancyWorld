package tosatto.fancyworld.game.world.keys;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Rappresenta una chiave
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

    /**
     * Ritorna il nome della chiave
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Ritorna il peso della chiave
     * @return 
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Imposta il peso della chiave
     * @param newWeight 
     */
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
    
}
