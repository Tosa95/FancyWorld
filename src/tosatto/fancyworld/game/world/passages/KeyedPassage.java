/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.KeyedGameInfo;
import tosatto.fancyworld.game.world.passages.exceptions.NoKeyPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;

/**
 *
 * @author davide
 */
@Root (name = "KeyedPassage")
public class KeyedPassage extends OpenPassage{
    
    @Attribute (name = "opened")
    private boolean opened = false;
    
    @Attribute (name = "neededKey")
    private String neededKey = null;
    
    public KeyedPassage(@Attribute(name="name") String name,
                        @Attribute(name="p1") String p1, 
                        @Attribute(name="p2") String p2) {
        super(name, p1, p2);
    }

    @Override
    public String next(String actPlace) throws PassageException {
        
        KeyedGameInfo kgi = (KeyedGameInfo)gi;
        
        if (neededKey == null || opened || kgi.playerHasKey(neededKey))
            return super.next(actPlace);
        else
            throw new NoKeyPassageException();
    }
    
    public boolean isClosed ()
    {
        return neededKey!=null && !opened;
    }
    
    public String requiredKey ()
    {
        return neededKey;
    }
    
}