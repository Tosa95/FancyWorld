/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.interactions.keysettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;

/**
 *
 * @author Davide
 */
public class StandardKeySettingsInteraction implements KeySettingsInteraction{

    private String[] prepareKeySettingsMenuEntries (Collection<Key> l)
    {
        List<String> entries = new ArrayList<>();
         
         for (Key k : l)
         {
             entries.add(k.getName() + ": " + k.getWeight());
         }
         
         entries.add("continua");
         
         String res[] = new String[entries.size()];
         
         return entries.toArray(res);
    }

    /**
     * Interagisce con l'utente per fargli impostare il valore delle chiavi.
     * 
     * Precondizione:
     *  - w deve essere di tipo BundledWorld
     *  - il bundle contenuto in w deve essere di tipo standard
     * 
     * @param w 
     * @param io 
     */
    @Override
    public void interact(World w, MessageIO io) {
        
        BundledWorld bw = (BundledWorld)w;
        
        int maxKeyWeight = bw.getBundle().getParameter(StandardBundleParametersNames.MAX_KEY_WEIGHT);
        
        io.inform("Puoi impostare il peso delle chiavi: ");
        
        int response = 0;
        
        do
        {
            
           List<Key> keyList = new ArrayList<>(bw.getKeys().values());
            
           response = io.showMenu("Seleziona quale peso modificare", prepareKeySettingsMenuEntries(keyList));
            
            if (response < bw.getKeys().size())
            {
                int newVal = io.askForPositiveInteger("Inserisci il nuovo peso della chiave", "Devi inserire un numero intero positivo!");
                
                if (newVal <= maxKeyWeight)
                {
                    keyList.get(response).setWeight(newVal);
                    io.inform("Peso della chiave selezionata correttamente modificato");
                } else {
                    io.inform("Le chiavi non possono avere peso superiore a " + Integer.toString(maxKeyWeight));
                }
            }
            
        }while(response < bw.getKeys().size());
        
    }
    
    
    
}
