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
import tosatto.fancyworld.game.world.generators.algorithms.KeyedWorldTraveler;
import tosatto.fancyworld.game.world.generators.names.BasicNameGenerator;
import tosatto.fancyworld.game.world.generators.names.NameGenerator;
import tosatto.fancyworld.game.world.generators.names.NoFoulLanguageNameGenerator;
import tosatto.fancyworld.main.WorldTopologies;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
public class StandardWorldGeneratorStackFactory implements WorldGeneratorStackFactory{
    
    private static final int MIN_LVL = 5;
    private static final int MAX_LVL = 10;
    private static final int MIN_PLACES = 10;
    private static final int MAX_PLACES = 20;
    private static final int DIFFICULTY = 1; 
    
    private static double KEY_PLACE_PROBABILITY = 0.3;
    private static double KEY_PASSAGE_PROBABILITY = 1;
    
    private static double TRIAL_PLACE_PROBABILITY = 0.4;
    
    
    
    @Override
    public RandomWorldGenerator create(ParametersBundle bundle, String topology) 
    {
        BaseWorldTraveler bwt = new BaseWorldTraveler();
        KeyedWorldTraveler kwt = new KeyedWorldTraveler();
        
        Random r = new Random(WorldTopologies.getInstance().getTopologySeed(topology));
        
        NameGenerator ng = new NoFoulLanguageNameGenerator(new BasicNameGenerator(r));
        
        BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(r, new BundledWorldFactory(), ng, bwt, bwt);
        
        brwg.setMinLvl(MIN_LVL);
        brwg.setMaxLvl(MAX_LVL);
        brwg.setMinPlaces(MIN_PLACES);
        brwg.setMaxPlaces(MAX_PLACES);
        brwg.setDifficulty(DIFFICULTY);

        int ktp = bundle.getParameter(StandardBundleParametersNames.KEY_TYPE_NUMBER);
           
        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, bwt);
        
        krwg.setMinKeys(ktp);
        krwg.setMaxKeys(ktp + 1);
        krwg.setMaxKeyWeight(bundle.getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT));
        krwg.setKeyPlaceProb(KEY_PLACE_PROBABILITY);
        krwg.setKeyPassageProb(KEY_PASSAGE_PROBABILITY);

        TrialedRandomWorldGenerator trwg = new TrialedRandomWorldGenerator(krwg, bwt);

        trwg.setTrialRatio(TRIAL_PLACE_PROBABILITY);
        trwg.setTrialTypeNumber(bundle.getParameter(StandardBundleParametersNames.TRIAL_TYPES_NUMBER));
        
        BundledRandomWorldGenerator bundrwg = new BundledRandomWorldGenerator(trwg, bundle);
        
        return bundrwg;
    }
    
}
