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
    
    @Attribute (name = "neededKey", required = false)
    private String neededKey = null;
    
    public KeyedPassage(@Attribute(name="name") String name,
                        @Attribute(name="p1") String p1, 
                        @Attribute(name="p2") String p2) {
        super(name, p1, p2);
    }

    public void setKey(String neededKey) {
        this.neededKey = neededKey;
    }
    
    @Override
    public String next(String actPlace) throws PassageException {
        
        
        
        if (neededKey == null || opened)
            return super.next(actPlace);
        else
            throw new NoKeyPassageException();
    }
    
    public boolean isClosed ()
    {
        
        
        return neededKey!=null && !opened;
    }
    
    public void open()
    {
        KeyedGameInfo kgi = (KeyedGameInfo)gi;
        
        if (kgi.playerHasKey(this.neededKey))
        {
            this.opened = true;
        }
    }
    
    public String requiredKey ()
    {
        return neededKey;
    }
    
}
