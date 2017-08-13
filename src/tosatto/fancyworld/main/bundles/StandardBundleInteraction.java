/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;

/**
 *
 * @author Davide
 */
public class StandardBundleInteraction implements BundleInteraction{

    private boolean checkBundleValidity (ParametersBundle b)
    {
        for (String par : b.getParameterNames())
        {
            if (b.getParameter(par) <= 0)
                return false;
        }
        
        if (b.getParameter("MAX_KEY_WEIGHT") > b.getParameter("MAX_KEYRING_WEIGHT") )
            return false;
        
        return true;
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
