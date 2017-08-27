/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.interactions.bundles;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.TrialPool;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.check.CheckResult;

/**
 * Definisce l'interazione con un bundle standard, ossia quello contenente i parametri
 * descritti nel documento contenente le richieste per il progetto
 * @author Davide
 */
public class BaseBundleInteraction implements BundleInteraction{

    
    
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
                
                CheckResult cr = b.check();
                
                if (!cr.getResult())
                {
                    io.inform(cr.getMessage());
                    io.inform("Modifica annullata");
                    
                    b.setParameter(pars.get(response), oldVal);
                    
                } else {
                    io.inform("Modifica effettuata");
                }
            }
            
        }while(response < pars.size());
    }
    
}
