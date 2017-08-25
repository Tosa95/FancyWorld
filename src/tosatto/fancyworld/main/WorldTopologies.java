/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main;

import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.simpleframework.xml.ElementMap;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.GamePersister;

/**
 * Contiene i seed delle topologie di mondo selezionabili. Implementa pattern Singleton
 * @author Davide
 */
public class WorldTopologies {
    
    //Dato che il seed è sufficiente per identificare univocamente la topologia di un
    //mondo, usiamo esso per identificarla. Cambiando i parametri ovviamente cambiano
    //le collocazioni di prove e/o chiavi ma il sistema di generazione casuale
    //realizzato ha la caratteristica di non variare la topologia anche in caso 
    //di variazione dei parametri dei livelli sovrastanti di generazione (chiavi e prove)
    @ElementMap (name = "seeds")
    private Map<String, Long> seeds;

    private static WorldTopologies wt = null;
    
    public static WorldTopologies getInstance()
    {
        if (wt == null)
        {
            wt = new WorldTopologies();
        }
        
        return wt;
    }
    
    private WorldTopologies() {
        seeds = new HashMap<>();
        
        seeds.put("Fancy World", 101274981236489263L);
        seeds.put("Fancy World NG", 101274981236489267L);
        seeds.put("Another one", 666666666666666666L);
    }
    
    public List<String> getWorldTopologiesNames()
    {
        return new ArrayList<>(seeds.keySet());
    }
    
    /**
     * Ritorna il seed associato alla topologia indicata da topologyName
     * 
     * Precondizione:
     *  - topologyName deve effettivamente essere presente nell'oggetto
     * 
     * @param topologyName
     * @return 
     */
    public long getTopologySeed(String topologyName)
    {
        return seeds.get(topologyName);
    }
    
    public boolean topologyHasSave(String topologyName)
    {
        GamePersister gp = new GamePersister(BaseGame.class, topologyName);
        return gp.existsFile();
    }
    
}
