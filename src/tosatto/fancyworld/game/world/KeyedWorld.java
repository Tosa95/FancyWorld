/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import tosatto.fancyworld.game.world.keys.Key;

/**
 * Rappresenta un mondo dotato di chiavi
 * 
 * Invariante:
 *  - per ogni possibile nome (tipo), esso identifica al massimo una chiave (il nome (tipo) di una chiave la identifica univocamente)
 * 
 * @author davide
 */
public class KeyedWorld extends World{
    
    @ElementMap(name = "keys")
    private HashMap<String, Key> keys = new HashMap<>();
    
    public KeyedWorld(@Attribute (name = "name") String name,
                      @Attribute (name = "start") String startPlace) {
        super(name, startPlace);
    }

    /**
     * Ritorna tutte le chiavi facenti parte del mondo
     * @return 
     */
    public Map<String, Key> getKeys() {
        return Collections.unmodifiableMap(keys);
    }
    
    /**
     * Permette di accedere ad una chiave, dato il suo nome (ossia la sua tipologia)
     * 
     * Precondizione:
     *  - name identifica una chiave facente parte del mondo
     * 
     * @param name
     * @return 
     */
    public Key getKey(String name)
    {
        if (!keys.containsKey(name))
        {
            throw new IllegalAccessError("The requested key does not exist.");
        }
        
        return keys.get(name);
    }
    
    /**
     * Aggiunge una chiave al mondo
     * @param k 
     */
    public void addKey (Key k)
    {
        keys.put(k.getName(), k);
    }
    
}
