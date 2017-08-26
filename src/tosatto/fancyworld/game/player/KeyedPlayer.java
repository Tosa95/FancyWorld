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
import tosatto.fancyworld.game.info.BundledGameInfo;
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.info.KeyedGameInfo;
import tosatto.fancyworld.game.player.exceptions.UnableToPickUpKeyException;
import tosatto.fancyworld.main.bundles.BaseBundleInteraction;

/**
 * Rappresenta un giocatore dotato di chiavi
 * 
 * Invariante:
 *  - Il peso delle chiavi possedute dal giocatore non supera mai il peso massimo definito nel bundle
 *  - Il numero delle chiavi possedute del giocatore non supera mei il numero massimo definito nel bundle
 * 
 * @author davide
 */
@Root(name = "KeyedPlayer")
public class KeyedPlayer extends Player{
    
    @ElementList(name = "keys")
    private List<String> keys = new ArrayList<>();
    
    private BundledGameInfo bgi;
    
    public KeyedPlayer(@Attribute (name = "name") String name) {
        super(name);
        
        bgi = (BundledGameInfo)gi;
    }
    
    /**
     * 
     * Svista di programmazione, DA RIMUOVERE in manutenzione
     * @param keyName
     * @return 
     */
    //TODO: Rimuovere
    public boolean getKey (String keyName)
    {
        return keys.contains(keyName);
    }

    /**
     * Imposta l'oggetto GameInfo.
     * 
     * Precondizione:
     *  - gi è di tipo BundledGameInfo
     * 
     * @param gi 
     */
    @Override
    protected void setSubGi(GameInfo gi) {
        super.setSubGi(gi);
        this.bgi = (BundledGameInfo) gi;
    }
    
    
    /**
     * Aggiunge una chiave al giocatore
     * 
     * Precondizioni:
     *  - Il peso totale delle chiavi possedute dal giocatore, aggiunta la nuova chiave, non deve superare il peso massimo definito nel bundle
     *  - Il numero totale di chiavi possedute dal giocatore, aggiunta la nuova chiave, non deve superare il numero massimo definito nel bundle
     * 
     * nota: Le precondizioni sono comunque controllate nel codice e viene l'anciata un'eccezione qualora non rispettate.
     * questo è utile per evitare di doverle controllare ogni volta lato client e permettere così di
     * centralizzare il controllo in questo metodo. La classe client sarà avvisata dell'eventuale errore attraverso
     * eccezione.
     *
     * 
     * @param keyName
     * @throws UnableToPickUpKeyException 
     */
    public void addKey (String keyName) throws UnableToPickUpKeyException
    {
        if ((getKeyringWeight() + bgi.getKeyWeight(keyName)) > bgi.getMaxKeyringWeight())
        {
            throw new UnableToPickUpKeyException("Impossibile raccogliere chiave in quanto supera il peso massimo trasportabile");
        }
        
        if ((keys.size() + 1) > bgi.getMaxKeyringSize())
        {
            throw new UnableToPickUpKeyException("Impossibile raccogliere chiave. Numero massimo raggiunto");
        }
        
        keys.add(keyName);
    }
    
    /**
     * Ritorna il peso della totalità delle chiavi possedute dal giocatore
     * @return 
     */
    public int getKeyringWeight()
    {
        int res = 0;
        
        for (String k : keys)
        {
            res += bgi.getKeyWeight(k);
        }
        
        return res;
    }
    
    /**
     * Rimuove una chiave dal giocatore
     * 
     * Precondizione:
     *  - La chiave identificata da keyName è effettivamente posseduta dal giocatore
     * 
     * @param keyName 
     */
    public void removeKey (String keyName)
    {
        keys.remove(keyName);
    }
    
    /**
     * Ritorna tutte le chiavi possedute dal giocatore
     * @return 
     */
    public Collection<String> getKeys ()
    {
        return Collections.unmodifiableCollection(keys);
    }
    
    /**
     * Dice se il giocatore possiede la chiave identificata da keyName
     * @param keyName
     * @return True: chiave posseduta, False: chiave non posseduta
     */
    public boolean hasKey (String keyName)
    {
        return keys.contains(keyName);
    }
    
}
