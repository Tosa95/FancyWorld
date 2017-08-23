/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.TrialPool;

/**
 * Definisce l'interazione con un bundle standard, ossia quello contenente i parametri
 * descritti nel documento contenente le richieste per il progetto
 * @author Davide
 */
public class StandardBundleInteraction implements BundleInteraction{

    public static final String GOAL_POINTS = "GOAL_POINTS";
    public static final String INITIAL_POINTS = "INITIAL_POINTS";
    public static final String MAX_TRIAL_VALUE = "MAX_TRIAL_VALUE";
    public static final String TRIAL_TYPES_NUMBER = "TRIAL_TYPES_NUMBER";
    public static final String MAX_KEY_WEIGHT = "MAX_KEY_WEIGHT";
    public static final String MAX_KEYRING_SIZE = "MAX_KEYRING_SIZE";
    public static final String MAX_KEYRING_WEIGHT = "MAX_KEYRING_WEIGHT";
    public static final String KEY_TYPE_NUMBER = "KEY_TYPE_NUMBER";
    
    //TODO: Strategy pattern
    private boolean checkBundleValidity (ParametersBundle b)
    {
        for (String par : b.getParameterNames())
        {
            if (b.getParameter(par) <= 0)
                return false;
        }
        
        if (b.getParameter(MAX_KEY_WEIGHT) > b.getParameter(MAX_KEYRING_WEIGHT) )
            return false;
        
        int maxPossibleTrials = TrialPool.getInstance().getTrials().size();
        
        if (b.getParameter(TRIAL_TYPES_NUMBER) > maxPossibleTrials)
            return false;
        
        return true;
    }
    
    public ParametersBundle getStandardBundle ()
    {
        ParametersBundle res = new ParametersBundle();
        
        res.addParameter(KEY_TYPE_NUMBER, 5);
        res.addParameter(MAX_KEYRING_WEIGHT, 50);
        res.addParameter(MAX_KEYRING_SIZE, 5);
        res.addParameter(MAX_KEY_WEIGHT, 25);
        res.addParameter(TRIAL_TYPES_NUMBER, 3);
        res.addParameter(MAX_TRIAL_VALUE, 20);
        res.addParameter(INITIAL_POINTS, 10);
        res.addParameter(GOAL_POINTS, 100);
        
        return res;
    }
    
    
    private String[] prepareParameterModificationMenuEntry (ParametersBundle b, List<String> pars)
    {
        List<String> entries = new ArrayList<>();
        
        for (String par : pars)
        {
            entries.add(par + " = " + b.getParameter(par));
        }
        
        entries.add("continua");
        
        String[] res = new String[entries.size()];
        
        return entries.toArray(res);
    }
    
    @Override
    public void interact(ParametersBundle b, MessageIO io) {
       
        io.inform("Se vuoi puoi modificare dei parametri: ");
        
        int response = 0;
        
        List<String> pars = new ArrayList<>(b.getParameterNames());
        
        do
        {
            
            response = io.showMenu("Seleziona quale parametro modificare", prepareParameterModificationMenuEntry(b, pars));
            
            if (response < pars.size())
            {
                int newVal = io.askForInteger("Inserisci il nuovo valore per il parametro selezionato", "Devi inserire un numero intero!");
                
                int oldVal = b.getParameter(pars.get(response));
                
                b.setParameter(pars.get(response), newVal);
                
                if (!checkBundleValidity(b))
                {
                    io.inform("Le modifiche apportate non soddisfano i vincoli imposti sui parametri. Modifica annullata");
                    
                    b.setParameter(pars.get(response), oldVal);
                    
                }
            }
            
        }while(response < pars.size());
    }
    
}
