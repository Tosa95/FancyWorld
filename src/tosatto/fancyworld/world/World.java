/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.GameInfo;
import tosatto.fancyworld.world.levels.Level;
import tosatto.fancyworld.world.passages.ClosedPassage;
import tosatto.fancyworld.world.passages.Passage;
import tosatto.fancyworld.world.places.Place;

/**
 *
 * @author Davide
 */
@Root
public class World {
    
    @ElementMap (name = "places")
    private HashMap<String, Place> places = new HashMap<>();
    
    @ElementMap (name = "levels")
    private HashMap<Integer, Level> levels = new HashMap<>();
    
    @ElementMap (name = "passages")
    private HashMap<String, Passage> passages = new HashMap<>();
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "start")
    private String startPlace;
    
    protected GameInfo gi;
    
    public World (@Attribute (name = "name") String name, 
                  @Attribute (name = "start") String startPlace)
    {
        this.name = name;
        this.startPlace = startPlace;
    }

    public void setGi(GameInfo gi) {
        this.gi = gi;
        
        for (Place p: places.values())
            p.setGi(gi);
        
        for (Level l: levels.values())
            l.setGi(gi);
    }

    public String getStartPlace() {
        return startPlace;
    }
    
    public String getName ()
    {
        return name;
    }
    
    public void addPlace (Place p)
    {
        if (!levels.containsKey(p.getLevel()))
            throw new IllegalArgumentException("Level " + p.getLevel() + " does not exist");
        
        p.setGi(gi);
        
        places.put(p.getName(), p);
    }
    
    public Place getPlace (String name)
    {
        return places.get(name);
    }
    
    public Collection<Place> getPlaces ()
    {
        return Collections.unmodifiableCollection(places.values());
    }
    
    public void addLevel (Level l)
    {
        l.setGi(gi);
        levels.put(l.getId(), l);
    }
    
    public Level getLevel (int index)
    {
        if (!levels.containsKey(index))
            throw new IllegalArgumentException("Level " + index + " does not exist");
        
        return levels.get(index);
    }

    public void addPassage (Passage p)
    {
        p.setGi(gi);
        passages.put(p.getName(), p);
    }
    
    public Passage getPassage (String name)
    {
        if (name == null)
            return new ClosedPassage();
        
        if (!passages.containsKey(name))
            throw new IllegalArgumentException ("Passage " + name + " does not exist");
        
        return passages.get(name);
    }
    
    public Collection<Passage> getAllPassages (Collection<String> names)
    {
        List<Passage> res = new ArrayList<>();
        
        names.stream().forEach(n->res.add(getPassage(n)));
        
        return res;
    }
    
    public int getEndLevelIndex ()
    {
        for (Place p: places.values())
        {
            if (p.isGoal())
                return p.getLevel();
        }
        
        throw new IllegalStateException("No goal place defined");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(name).append("\n\nPlaces:\n\n");
        
        for (Place p: places.values())
        {
            sb.append(p.toString()).append("\n\n");
        }
        
        sb.append("\n\nLevels:\n\n");
        
        for (Level l: levels.values())
        {
            sb.append(" ").append(l.getId()).append(" : ").append(l.getName());
        }
        
        return sb.toString();
    }
    
    
}
