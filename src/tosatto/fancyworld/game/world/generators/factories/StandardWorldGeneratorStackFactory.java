/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.factories;

import java.util.Random;
import tosatto.fancyworld.game.world.factories.BundledWorldFactory;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.BundledRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.RandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.algorithms.BaseWorldTraveler;
import tosatto.fancyworld.main.WorldTopologies;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
public class StandardWorldGeneratorStackFactory implements WorldGeneratorStackFactory{
    
    private static int MIN_LVL = 5;
    private static int MAX_LVL = 10;
    private static int MIN_PLACES = 10;
    private static int MAX_PLACES = 20;
    private static int DIFFICULTY = 1; 
    
    private static double KEY_PLACE_PROBABILITY = 0.3;
    private static double KEY_PASSAGE_PROBABILITY = 1;
    
    private static double TRIAL_PLACE_PROBABILITY = 0.4;
    
    
    
    @Override
    public RandomWorldGenerator create(ParametersBundle bundle, String topology) 
    {
        BaseWorldTraveler bwt = new BaseWorldTraveler();
        
        BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(WorldTopologies.getInstance().getTopologySeed(topology))
                   , new BundledWorldFactory(), MIN_LVL, MAX_LVL, MIN_PLACES, MAX_PLACES, DIFFICULTY, bwt, bwt);

        int ktp = bundle.getParameter(StandardBundleParametersNames.KEY_TYPE_NUMBER);
           
        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, bundle.getParameter(StandardBundleParametersNames.KEY_TYPE_NUMBER), bundle.getParameter(StandardBundleParametersNames.KEY_TYPE_NUMBER)+1,
                bundle.getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT), KEY_PLACE_PROBABILITY, KEY_PASSAGE_PROBABILITY);

        TrialedRandomWorldGenerator trwg = new TrialedRandomWorldGenerator(krwg, TRIAL_PLACE_PROBABILITY, bundle.getParameter(StandardBundleParametersNames.TRIAL_TYPES_NUMBER));

        BundledRandomWorldGenerator bundrwg = new BundledRandomWorldGenerator(trwg, bundle);
        
        return bundrwg;
    }
    
}
