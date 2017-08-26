/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.interactions.trialsettings;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
public class StandardTrialsSettingsInteraction implements TrialSettingsInteraction{

    private String[] prepareTrialValueSettingMenu (List<Trial> l)
    {
        List<String> entries = new ArrayList<>();
         
         for (Trial t : l)
         {
             entries.add(t.getType() + ": " + t.getValue());
         }
         
         entries.add("continua");
         
         String res[] = new String[entries.size()];
         
         return entries.toArray(res);
    }
    
    @Override
    public void interact(World w, MessageIO io) {
        
        BundledWorld bw = (BundledWorld) w;
        
        int maxTrialValue = bw.getBundle().getParameter(StandardBundleParametersNames.MAX_TRIAL_VALUE);
        
        io.inform("Puoi impostare il valore delle prove: ");
        
        int response = 0;
        
        do
        {
            List<Trial> trials = new ArrayList<>(bw.getTrials());
            
            response = io.showMenu("Seleziona quale valore modificare", prepareTrialValueSettingMenu(trials));
            
            if (response < bw.getTrials().size())
            {
                int newVal = io.askForPositiveInteger("Inserisci il nuovo valore della prova", "Devi inserire un numero intero positivo!");
                
                if (newVal <= maxTrialValue)
                {
                    trials.get(response).setValue(newVal);
                    io.inform("Il valore della prova selezionata è stato modificato con successo");
                } else {
                    io.inform("Le prove non possono avere valore superiore a " + Integer.toString(maxTrialValue));
                }
            }
            
        }while(response < bw.getTrials().size());
    }
    
}
