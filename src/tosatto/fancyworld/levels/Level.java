/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.levels;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.Game;

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
    
    protected Game gm;

    public Level(@Attribute (name = "id") int id,
                 @Attribute (name = "name") String name,
                 @Attribute (name = "description") String description) 
    {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public void setGm(Game gm) {
        this.gm = gm;
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
