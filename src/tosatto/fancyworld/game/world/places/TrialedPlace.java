/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.places;

import org.simpleframework.xml.Attribute;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class TrialedPlace extends KeyedPlace{
    
    @Attribute (name = "trial", required = false)
    private String trial = null;
    
    public TrialedPlace(@Attribute (name = "name") String name,
                  @Attribute (name = "description") String description,
                  @Attribute (name = "goal") boolean goal,
                  @Attribute (name = "level") int level) {
        
        super(name, description, goal, level);
        
    }
    
    public void setTrial (String trial)
    {
        this.trial = trial;
    }
    
    public String getTrial ()
    {
        return this.trial;
    }
    
    public boolean hasTrial()
    {
        return !(trial == null);
    }
    
    
}
