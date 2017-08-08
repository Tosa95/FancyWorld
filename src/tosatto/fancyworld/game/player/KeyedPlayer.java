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
import tosatto.fancyworld.game.GameInfo;
import tosatto.fancyworld.game.KeyedGameInfo;
import tosatto.fancyworld.game.player.exceptions.UnableToPickUpKeyException;

/**
 *
 * @author davide
 */
@Root(name = "KeyedPlayer")
public class KeyedPlayer extends Player{
    
    @ElementList(name = "keys")
    private List<String> keys = new ArrayList<>();
    
    private KeyedGameInfo kgi;
    
    public static final int MAX_KEYRING_WEIGHT = 50;
    public static final int MAX_KEYRING_SIZE = 5;
    
    public KeyedPlayer(@Attribute (name = "name") String name) {
        super(name);
        
        kgi = (KeyedGameInfo)gi;
    }
    
    public boolean getKey (String keyName)
    {
        return keys.contains(keyName);
    }

    @Override
    protected void setSubGi(GameInfo gi) {
        super.setSubGi(gi);
        this.kgi = (KeyedGameInfo) gi;
    }
    
    
    
    public void addKey (String keyName) throws UnableToPickUpKeyException
    {
        if ((getKeyringWeight() + kgi.getKeyWeight(keyName)) > MAX_KEYRING_WEIGHT)
        {
            throw new UnableToPickUpKeyException("Impossibile raccogliere chiave in quanto supera il peso massimo trasportabile");
        }
        
        if ((keys.size() + 1) > MAX_KEYRING_SIZE)
        {
            throw new UnableToPickUpKeyException("Impossibile raccogliere chiave. Numero massimo raggiunto");
        }
        
        keys.add(keyName);
    }
    
    public int getKeyringWeight()
    {
        int res = 0;
        
        for (String k : keys)
        {
            res += kgi.getKeyWeight(k);
        }
        
        return res;
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
