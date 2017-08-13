/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.MaxTrialCountReachedException;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.game.world.trials.WordTrial;

/**
 *
 * @author Davide
 */
public class WordTrialInteraction implements TrialInteraction{

    @Override
    public boolean interact(Trial t, MessageIO io) {
        
        WordTrial wt = (WordTrial)t;
        
        wt.init();
        
        io.inform("Ti verrà ora presentata una parola in cui alcune lettere sono offuscate.");
        io.inform(String.format("Hai %d tentativi per indovinare di quale parola si tratta. Buona fortuna!", wt.getMaxTrialNumber()));
        
        try {
            while (wt.getCurrentTrialCount() < wt.getMaxTrialNumber())
            {

                io.inform(wt.getObfuscatedWord());
                String resp = io.ask(String.format("Tentativo %d: Quale è la parola nascosta?", wt.getCurrentTrialCount() + 1));

                if (wt.isExact(resp))
                {
                    io.inform("Complimenti!!! Hai indovinato");
                    return true;
                } else {
                    io.inform("Tentativo fallito. Ritenta!!!");
                }


            }
            io.inform("Tentativi esauriti. Hai perso :(");
            io.inform("La parola era " + wt.getRealWord());
            return false;
        } catch (MaxTrialCountReachedException ex) {
                
        } 
        
        return false;
        
    }
    
}
