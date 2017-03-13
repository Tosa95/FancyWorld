/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.places;

import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.GameInfo;
import tosatto.fancyworld.game.world.passages.ClosedPassage;
import tosatto.fancyworld.game.world.passages.Passage;

/**
 *
 * @author Davide
 */
@Root(name = "Place")
public class Place {
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "description")
    private String description;
    
    @Attribute (name = "goal")
    private boolean goal;
    
    @Attribute (name = "level")
    private int level;
    
    @ElementMap (name = "passages")
    protected HashMap<String, String> passages = new HashMap<>();
    
    protected GameInfo gi;
    
    public Place (@Attribute (name = "name") String name,
                  @Attribute (name = "description") String description,
                  @Attribute (name = "goal") boolean goal,
                  @Attribute (name = "level") int level)
    {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.level = level;
    }

    public void setGi(GameInfo gi) {
        this.gi = gi;
    }
    
    public String getName ()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Map<String, String> getPassages ()
    {
        return (Map<String,String>)passages.clone();
    }

    public String getPassage (String direction)
    {
        if (passages.containsKey(direction))
            return passages.get(direction);
        else
            return null;
    }
    
    public void addPassage (String direction, String p)
    {
        passages.put(direction, p);
    }
    
    public int getLevel ()
    {
        return level;
    }
    
    public boolean isGoal ()
    {
        return goal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Place)
        {
            return this.getName().equals(((Place)obj).getName());
        } else {
            throw new IllegalArgumentException("The object must be instance of Place");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(name).append("\n goal? ").append(goal).append("\n level: ")
          .append(level).append("\n Links:\n");
        
        for (Map.Entry<String, String> pair: passages.entrySet())
        {
            sb.append("  ").append(pair.getKey()).append(": ")
              .append(pair.getValue());
        }
        
        return sb.toString();
    }
    
    
}