/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author davide
 */
@Root(name = "KeyedPlayer")
public class KeyedPlayer extends Player{
    
    @ElementList(name = "keys")
    private List<String> keys = new ArrayList<>();
    
    public KeyedPlayer(@Attribute (name = "name") String name) {
        super(name);
    }
    
    public boolean getKey (String keyName)
    {
        return keys.contains(keyName);
    }
    
    public void addKey (String keyName)
    {
        keys.add(keyName);
    }
    
    public void removeKey (String keyName)
    {
        keys.remove(keyName);
    }
    
    public Collection<String> getKeys ()
    {
        return Collections.unmodifiableCollection(keys);
    }
    
    public boolean hasKey (String keyName)
    {
        return keys.contains(keyName);
    }
    
}
