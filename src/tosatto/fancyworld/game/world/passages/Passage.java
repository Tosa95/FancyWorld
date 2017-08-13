/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
@Root
public abstract class Passage {
    
    @Attribute (name = "name")
    private String name;
    
    protected GameInfo gi;
    
    public Passage (@Attribute (name = "name") String name)
    {
        this.name = name;
    }

    public void setGi(GameInfo gi) {
        this.gi = gi;
    }
    
    public abstract String next (String actPlace) throws PassageException;
    
    public String getName ()
    {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
