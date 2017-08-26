/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles.factories;

import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;
import tosatto.fancyworld.main.bundles.check.StandardBundleChecker;

/**
 *
 * @author Davide
 */
public class StandardBundleFactory implements BundleFactory{

    @Override
    public ParametersBundle create() {
        
        ParametersBundle res = new ParametersBundle(new StandardBundleChecker());
        
        res.addParameter(StandardBundleParametersNames.KEY_TYPE_NUMBER, 5);
        res.addParameter(StandardBundleParametersNames.MAX_KEYRING_WEIGHT, 50);
        res.addParameter(StandardBundleParametersNames.MAX_KEYRING_SIZE, 5);
        res.addParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT, 25);
        res.addParameter(StandardBundleParametersNames.TRIAL_TYPES_NUMBER, 3);
        res.addParameter(StandardBundleParametersNames.MAX_TRIAL_VALUE, 20);
        res.addParameter(StandardBundleParametersNames.INITIAL_POINTS, 10);
        res.addParameter(StandardBundleParametersNames.GOAL_POINTS, 100);
        
        return res;
        
    }
    
}
