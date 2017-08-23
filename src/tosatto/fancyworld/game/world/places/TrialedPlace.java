/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.places;

import org.simpleframework.xml.Attribute;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 * Rappresenta un luogo con prova
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
    
    /**
     * Imposta il tipo di prova
     * @param trial 
     */
    public void setTrial (String trial)
    {
        this.trial = trial;
    }
    
    /**
     * Ritorma il tipo di prova (null se il luogo non ha una prova)
     * @return 
     */
    public String getTrial ()
    {
        return this.trial;
    }
    
    /**
     * Dice se il luogo ha una prova
     * @return 
     */
    public boolean hasTrial()
    {
        return !(trial == null);
    }
    
    
}
