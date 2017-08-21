/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world;

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
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.ClosedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Rappresenta un mondo di gioco.
 * 
 * Invariante:
 *  - Esiste, all'interno del mondo, al massimo un livello per ogni identificativo (identificativo identifica univocamente livello)
 *  - Esiste, all'interno del mondo, un solo posto per ogni nome (nome identifica univocamente luogo)
 *  - Esiste, all'interno del mondo, un solo passaggio per ogni nome (nome identifica univocamente passaggio)
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

    /**
     * Imposta l'oggetto game info utilizzato dalle sottoclassi di World e da
     * World stessa per riferirsi ad alcune delle caratteristiche della classe
     * Game che li contiene.
     * 
     * @param gi 
     */
    public void setGi(GameInfo gi) {
        this.gi = gi;
        
        for (Place p: places.values())
            p.setGi(gi);
        
        for (Level l: levels.values())
            l.setGi(gi);
        
        for (Passage p: passages.values())
            p.setGi(gi);
    }

    /**
     * Ritorna il luogo di partenza di questo mondo
     * @return 
     */
    public String getStartPlace() {
        return startPlace;
    }
    
    /**
     * Ritorna il nome del mondo
     * @return 
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * Permette di aggiungere un posto al mondo
     * @param p 
     */
    public void addPlace (Place p)
    {
        if (!levels.containsKey(p.getLevel()))
            throw new IllegalArgumentException("Level " + p.getLevel() + " does not exist");
        
        p.setGi(gi);
        
        places.put(p.getName(), p);
    }
    
    /**
     * Permette di accedere ad un posto contenuto nel mondo.
     * 
     * Precondizione:
     *  - Il posto il cui nome viene passato come parametro fa effettivamente parte del mondo
     * 
     * @param name
     * @return 
     */
    public Place getPlace (String name)
    {
        return places.get(name);
    }
    
    /**
     * Ritorna tutti i posti facenti parte del mondo
     * 
     * @return 
     */
    public Collection<Place> getPlaces ()
    {
        return Collections.unmodifiableCollection(places.values());
    }
    
    /**
     * Aggiunge un livello al mondo
     * @param l 
     */
    public void addLevel (Level l)
    {
        l.setGi(gi);
        levels.put(l.getId(), l);
    }
    
    /**
     * Permette di accedere ad un livello
     * 
     * Precondizione:
     *  - index identifica un livello effettivamente contenuto nel mondo
     * 
     * @param index
     * @return 
     */
    public Level getLevel (int index)
    {
        if (!levels.containsKey(index))
            throw new IllegalArgumentException("Level " + index + " does not exist");
        
        return levels.get(index);
    }

    /**
     * Aggiunge un passaggio al mondo
     * @param p 
     */
    public void addPassage (Passage p)
    {
        p.setGi(gi);
        passages.put(p.getName(), p);
    }
    
    /**
     * Permette di accedere ad un passaggio
     * 
     * Precondizione:
     *  - name deve identificare un passaggio effettivamente contenuto nel mondo
     * 
     * @param name
     * @return 
     */
    public Passage getPassage (String name)
    {
        if (name == null)
            return new ClosedPassage();
        
        if (!passages.containsKey(name))
            throw new IllegalArgumentException ("Passage " + name + " does not exist");
        
        return passages.get(name);
    }
    
    /**
     * Ritorna i passaggi identificati dai nomi contenuti in names
     * 
     * Precondizione:
     *  - ogni nome contenuto in names deve identificare un passaggio facente parte del mondo
     * 
     * @param names
     * @return 
     */
    public Collection<Passage> getAllPassages (Collection<String> names)
    {
        List<Passage> res = new ArrayList<>();
        
        names.stream().forEach(n->res.add(getPassage(n)));
        
        return res;
    }
    
    /**
     * Ritorna tutti i passaggi facenti parte del mondo
     * @return 
     */
    public Collection<Passage> getAllPassages ()
    {
        return passages.values();
    }
    
    /**
     * Ritorna l'identificativo del livello del luogo goal
     * @return 
     */
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
