/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;

/**
 * Contenitore delle prove definite all'interno del package
 * @author Davide
 */
public class TrialPool {
    
    private static TrialPool pool = null;
    
    private TrialPool()
    {
        
    }
    
    public static TrialPool getInstance ()
    {
        if (pool == null)
        {
            pool = new TrialPool();
        }
        
        return pool;
    }
    
    /**
     * Ritorna la lista di tutte le prove definite nel package
     * @return 
     */
    public List<Trial> getTrials ()
    {
        //Utilizza la reflection per raccogliere automaticamente tutte le prove definite,
        //senza bisogno di alcuna modifica da parte del programmatore
        Reflections ref = new Reflections("tosatto.fancyworld.game.world.trials");
        Set<Class<? extends Trial>> subTypes = ref.getSubTypesOf(Trial.class);
        
        List<Trial> possibleTrials = new ArrayList<>();
        
        for (Class<? extends Trial> cls: subTypes)
        {
             try {
                 //TUTTE le prove definite devono avere un costruttore senza parametri
                 Trial t = cls.newInstance();
                 possibleTrials.add(t);
             } catch (InstantiationException ex) {
                 Logger.getLogger(TrialedRandomWorldGenerator.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(TrialedRandomWorldGenerator.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return possibleTrials;
    }
    
}
