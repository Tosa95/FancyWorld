/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages;

import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.GameInfo;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
@Root
public class OpenPassage extends Passage{

    @Attribute (name = "p1")
    private final String p1;
    
    @Attribute (name = "p2")
    private final String p2;
    
    public OpenPassage (@Attribute (name = "name") String name, @Attribute (name = "p1") String p1, @Attribute (name = "p2") String p2)
    {
        super(name);
        this.p1 = p1;
        this.p2 = p2;
    }
    
    @Override
    public String next(String actPlace) throws PassageException{
        return actPlace.equals(p1)?p2:p1;
    }

    @Override
    public String toString() {
        return String.format ("%s: %s <--> %s" , super.toString(), p1, p2);
    }
    
    
}
