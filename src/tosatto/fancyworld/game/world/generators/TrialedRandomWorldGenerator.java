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
import tosatto.fancyworld.game.world.generators.names.BasicNameGenerator;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.generators.algorithms.PlaceIterator;
import tosatto.fancyworld.game.world.generators.algorithms.PlaceVisitor;
import tosatto.fancyworld.game.world.generators.names.NameGenerator;
import tosatto.fancyworld.game.world.places.Place;
import tosatto.fancyworld.game.world.places.TrialedPlace;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.game.world.trials.TrialPool;

/**
 * Genera la disposizione di prove all'interno di un mondo.
 * 
 * Funge da decorastor per un KeyedRandomWporldGenerator
 * 
 * @author Davide
 */
public class TrialedRandomWorldGenerator implements RandomWorldGenerator{

    private NameGenerator ng;
    private WorldFactory wf;
    private Random r;
    
    private RandomWorldGenerator base;
    private double trialRatio;
   
    private int trialTypeNumber;
    
    private static double DEFAULT_TRIAL_PLACE_PROBABILITY = 0.4;
    private static int DEFAULT_TRIAL_TYPE_NUMBER = 3;
    
    private PlaceIterator pi;
    
    /**
     * Inizializza il generatore casuale di un mondo con prove.
     * 
     * Precondizione: trialTypeNumber deve essere inferiore o uguale al numero di prove
     * effettivamente definite nell'apposito package. Se non rispettata, il risultato sarà
     * un ciclo infinito
     * 
     * @param base
     * @param trialRatio Probabilità che in un posto venga piazzata una chiave
     * @param trialTypeNumber Numero di tipi di prova desiderati
     */
    public TrialedRandomWorldGenerator(RandomWorldGenerator base, double trialRatio, int trialTypeNumber, PlaceIterator pi) {
        this.base = base;
        
        ng = base.getNameGenerator();
        wf = base.getWorldFactory();
        r = base.getRandom();
        
        trialTypeNumber = trialTypeNumber;
        
        this.trialRatio = trialRatio;
        
        this.pi = pi;
        
    }
    
    public TrialedRandomWorldGenerator(RandomWorldGenerator base, PlaceIterator pi)
    {
        this(base, DEFAULT_TRIAL_PLACE_PROBABILITY, DEFAULT_TRIAL_TYPE_NUMBER, pi);
    }
    
    /**
     * Dice se almeno una prova è raggiungibile(condizione sufficiente per rendere possibile la vittoria)
     * @param w
     * @return 
     */
    private boolean canReachATrial (World w)
    {
        Boolean reachable[] = new Boolean[] {false};
        
        pi.visitAllReachablePlaces(w, new PlaceVisitor() {
            @Override
            public void visit(Place p) {
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
    
    /**
     * Genera un mondo dotato di prove.
     * 
     * Postcondizione:
     *  - Almeno una prova è raggiungibile (condizione sufficiente per rendere possibile la vittoria)
     * 
     * @return 
     */
    @Override
    public World generate() {
        TrialedWorld res = (TrialedWorld)base.generate();
        
        
        //Carico la lista di prove definite nell'apposito package
        List<Trial> possibleTrials = TrialPool.getInstance().getTrials();

        
        UniqueRandomGenerator urg = new UniqueRandomGenerator(r);
        
        //Ciclo per decidere quali tipologie di prove ci saranno nel mondo        
        for (int  i = 0; i < getTrialTypeNumber(); i++)
        {
            int index = urg.getUniqueInt(0, possibleTrials.size());
            
            res.addTrial(possibleTrials.get(index));
        }
        
        //Pongo come prove possibili le sole prove selezionate per il mondo corrente
        possibleTrials = new ArrayList<>(res.getTrials());
        
        //Aggiungo prove ai posti in base alla probabilità trialRatio
        for (Place p:res.getPlaces())
        {
            
            if (urg.getRandomBooleanWithProbability(getTrialRatio()) && !p.getName().equals(res.getStartPlace()) && !p.isGoal())
            {
                //System.out.println("Trial added in " + p.getName());
                TrialedPlace tp = (TrialedPlace)p;

                tp.setTrial(possibleTrials.get(urg.getInt(0, possibleTrials.size())).getType());
            }
        }
        
        List<Place> places = new ArrayList<>(res.getPlaces());
        
        //Mi assicuro che almeno una prova sia raggiungibile in modo da poter effettivamente completare il gioco
        while (! canReachATrial(res))
        {
            //System.out.println("TRIAL NOT REACHABLE!!!");
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

    /**
     * @return the trialRatio
     */
    public double getTrialRatio() {
        return trialRatio;
    }

    /**
     * @param trialRatio the trialRatio to set
     */
    public void setTrialRatio(double trialRatio) {
        this.trialRatio = trialRatio;
    }

    /**
     * @return the trialTypeNumber
     */
    public int getTrialTypeNumber() {
        return trialTypeNumber;
    }

    /**
     * @param trialTypeNumber the trialTypeNumber to set
     */
    public void setTrialTypeNumber(int trialTypeNumber) {
        this.trialTypeNumber = trialTypeNumber;
    }
    
}
