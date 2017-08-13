/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world;

import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class TrialedWorld extends KeyedWorld{
    
    @ElementMap(name = "trials")
    private Map<String, Trial> trials;
    
    public TrialedWorld(@Attribute (name = "name") String name,
                      @Attribute (name = "start") String startPlace) {
        super(name, startPlace);
        trials = new HashMap<>();
    }
    
    public void addTrial (Trial t)
    {
        trials.put(t.getType(), t);
    }
    
    public Trial getTrial (String type)
    {
        if (!trials.containsKey(type))
        {
            throw new IllegalAccessError(String.format("La prova della tipologia"
                    + " %s non appartiene a questo mondo...", type));
        }
        
        return trials.get(type);
    }
    
}
