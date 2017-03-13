/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world.levels;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.GameInfo;

/**
 *
 * @author Davide
 */
@Root (name = "Level")
public class Level {
    
    @Attribute (name = "id")
    private int id;
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "description")
    private String description;

    protected GameInfo gi;
    
    public Level(@Attribute (name = "id") int id,
                 @Attribute (name = "name") String name,
                 @Attribute (name = "description") String description) 
    {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public void setGi(GameInfo gi) {
        this.gi = gi;
    }
    
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    

    public int getId() {
        return id;
    }
    
    
    
}
