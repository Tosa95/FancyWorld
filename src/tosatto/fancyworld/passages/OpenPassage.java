/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.passages;

import java.util.HashMap;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.passages.exceptions.PassageException;
import tosatto.fancyworld.places.Place;

/**
 *
 * @author Davide
 */
@Root
public class OpenPassage extends Passage{

    @Attribute (name = "next")
    private final String nextPlace;
    
    public OpenPassage (@Attribute (name = "name") String name, @Attribute (name = "next") String nextPlace)
    {
        super(name);
        this.nextPlace = nextPlace;
    }
    
    @Override
    public String pass() throws PassageException {
        return nextPlace;
    }

    @Override
    public String toString() {
        return super.toString() + ": --> " + nextPlace;
    }
    
    
}
