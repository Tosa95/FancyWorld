/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.world.passages.exceptions.ClosedPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Place;

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
