/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.places;

import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.passages.ClosedPassage;
import tosatto.fancyworld.passages.Passage;

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
    protected HashMap<String, Passage> passages = new HashMap<>();
    
    
    
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
    
    public String getName ()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Map<String, Passage> getPassages ()
    {
        return (Map<String,Passage>)passages.clone();
    }

    public Passage getPassage (String direction)
    {
        if (passages.containsKey(direction))
            return passages.get(direction);
        else
            return new ClosedPassage();
    }
    
    public void addPassage (String direction, Passage p)
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
        
        for (Map.Entry<String, Passage> pair: passages.entrySet())
        {
            sb.append("  ").append(pair.getKey()).append(": ")
              .append(pair.getValue().toString());
        }
        
        return sb.toString();
    }
    
    
}
