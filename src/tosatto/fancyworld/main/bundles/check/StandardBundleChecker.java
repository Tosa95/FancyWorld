/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles.check;

import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.world.trials.TrialPool;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
@Root
public class StandardBundleChecker implements BundleChecker{

    @Override
    public CheckResult check(ParametersBundle bundle) {
        
        for (String par : bundle.getParameterNames())
        {
            if (bundle.getParameter(par) <= 0)
                return new CheckResult(false, "Tutti i parametri devono avere valore positivo, mentre "
                        + par + " è stato impostato al valore " + bundle.getParameter(par));
        }
        
        if (bundle.getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT) > bundle.getParameter(StandardBundleParametersNames.MAX_KEYRING_WEIGHT) )
            return new CheckResult(false, StandardBundleParametersNames.MAX_KEY_WEIGHT + " non può essere maggiore di "
                    + StandardBundleParametersNames.MAX_KEYRING_WEIGHT);
        
        int maxPossibleTrials = TrialPool.getInstance().getTrials().size();
        
        if (bundle.getParameter(StandardBundleParametersNames.TRIAL_TYPES_NUMBER) > maxPossibleTrials)
            return new CheckResult(false, StandardBundleParametersNames.TRIAL_TYPES_NUMBER + " non può essere maggiore di "
                    + maxPossibleTrials);
        
        return new CheckResult(true, "Tutto ok");
    }
    
}
