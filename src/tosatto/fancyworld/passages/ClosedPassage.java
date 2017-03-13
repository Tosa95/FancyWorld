/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.passages;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.GameInfo;
import tosatto.fancyworld.passages.exceptions.ClosedPassageException;
import tosatto.fancyworld.passages.exceptions.PassageException;
import tosatto.fancyworld.places.Place;

/**
 *
 * @author Davide
 */
@Root
public class ClosedPassage extends Passage{
    
    public ClosedPassage ( @Attribute (name = "name") String name)
    {
        super(name);
    }

    public ClosedPassage() {
        super("Passaggio chiuso");
    }
    
    @Override
    public String next(String actPlace) throws PassageException {
        throw new ClosedPassageException();
    }
    
}
