/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.Random;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.generators.names.BasicNameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.interactions.bundles.BaseBundleInteraction;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
public class BundledRandomWorldGenerator implements RandomWorldGenerator{

    private RandomWorldGenerator base;
    private ParametersBundle bundle;
    
    public BundledRandomWorldGenerator(RandomWorldGenerator base, ParametersBundle bundle) {
        this.base = base;
        this.bundle = bundle;
    }

    /**
     * Controlla il peso delle chiavi e tronca al massimo
     * 
     * Precondizione: 
     *  - b è un bundle di tipo standard
     * 
     * @param w
     * @param b 
     */
    private void checkKeyWeights (BundledWorld w, ParametersBundle b)
    {
        for (Key k : w.getKeys().values())
        {
            if (k.getWeight() >= b.getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT))
            {
                k.setWeight(b.getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT));
            }
        }
    }
        
    /**
     * Controlla il valore delle prove e tronca al massimo
     * 
     * Precondizione:
     *  - b è un bundle di tipo standard
     * 
     * @param w
     * @param b 
     */
    private void checkTrialValues (BundledWorld w, ParametersBundle b)
    {
        for (Trial t : w.getTrials())
        {
            if (t.getValue() >= b.getParameter(StandardBundleParametersNames.MAX_TRIAL_VALUE))
            {
                t.setValue(b.getParameter(StandardBundleParametersNames.MAX_TRIAL_VALUE));
            }
        }
    }
    
    /**
     * Genera il mondo
     * 
     * Precondizione:
     *  - Base deve aver generato un mondo di tipo Bundled o un suo derivato
     * 
     * @return 
     */
    @Override
    public World generate() {
        BundledWorld bw = (BundledWorld)base.generate();
        
        bw.setBundle(bundle);
        
        checkKeyWeights(bw, bundle);
        checkTrialValues(bw, bundle);
        
        return bw;
    }

    @Override
    public Random getRandom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BasicNameGenerator getNameGenerator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WorldFactory getWorldFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
