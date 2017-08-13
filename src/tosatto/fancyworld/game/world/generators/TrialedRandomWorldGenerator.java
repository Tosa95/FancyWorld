/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;
import tosatto.fancyworld.common.UniqueRandomGenerator;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.places.Place;
import tosatto.fancyworld.game.world.places.TrialedPlace;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class TrialedRandomWorldGenerator implements RandomWorldGenerator{

    private NameGenerator ng;
    private WorldFactory wf;
    private Random r;
    
    private KeyedRandomWorldGenerator base;
    private double trialRatio;
    
    private static final int WORLD_TRIALS_NUMBER = 3;
    
    
    public TrialedRandomWorldGenerator(KeyedRandomWorldGenerator base, double trialRatio) {
        this.base = base;
        
        ng = base.getNameGenerator();
        wf = base.getWorldFactory();
        r = base.getRandom();
        
        this.trialRatio = trialRatio;
        
    }
    
    private boolean canReachATrial (World w)
    {
        Boolean reachable[] = new Boolean[] {false};
        
        base.visitGraph(w, new PlaceAction() {
            @Override
            public void interact(Place p) {
                if (p instanceof TrialedPlace)
                {
                    TrialedPlace tp = (TrialedPlace)p;
                    
                    if (tp.hasTrial())
                        reachable[0] = true;
                }
            }
        });
        
        return reachable[0];
    }
    
    @Override
    public World generate() {
        TrialedWorld res = (TrialedWorld)base.generate();
        
        Reflections ref = new Reflections("tosatto.fancyworld.game.world.trials");
        Set<Class<? extends Trial>> subTypes = ref.getSubTypesOf(Trial.class);
        
        List<Trial> possibleTrials = new ArrayList<>();
        
        for (Class<? extends Trial> cls: subTypes)
        {
             try {
                 Trial t = cls.newInstance();
                 possibleTrials.add(t);
             } catch (InstantiationException ex) {
                 Logger.getLogger(TrialedRandomWorldGenerator.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(TrialedRandomWorldGenerator.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        UniqueRandomGenerator urg = new UniqueRandomGenerator(r);
        
        for (int  i = 0; i < WORLD_TRIALS_NUMBER; i++)
        {
            int index = urg.getUniqueInt(0, possibleTrials.size());
            
            res.addTrial(possibleTrials.get(index));
        }
        
        for (Place p:res.getPlaces())
        {
            
            if (urg.getRandomBooleanWithProbability(trialRatio) && !p.getName().equals(res.getStartPlace()) && !p.isGoal())
            {
                System.out.println("Trial added in " + p.getName());
                TrialedPlace tp = (TrialedPlace)p;

                tp.setTrial(possibleTrials.get(urg.getInt(0, possibleTrials.size())).getType());
            }
        }
        
        List<Place> places = new ArrayList<>(res.getPlaces());
        
        //Mi assicuro che almeno una prova sia raggiungibile in modo da poter effettivamente completare il gioco
        while (! canReachATrial(res))
        {
            System.out.println("TRIAL NOT REACHABLE!!!");
            TrialedPlace tp = (TrialedPlace)places.get(urg.getInt(0, places.size()));
            tp.setTrial(possibleTrials.get(urg.getInt(0, possibleTrials.size())).getType());
        }
        
        return res;
    }

    @Override
    public Random getRandom() {
        return r;
    }

    @Override
    public NameGenerator getNameGenerator() {
        return ng;
    }

    @Override
    public WorldFactory getWorldFactory() {
        return wf;
    }
    
}
