/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.FortuneTrial;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class FortuneTrialInteraction implements TrialInteraction{

    @Override
    public boolean interact(Trial t, MessageIO io) {
        io.inform("Questa prova serve a valutare la tua fortuna (e anche la tua conoscenza della probabilità).");
        io.inform("Verranno lanciati 2 dadi");

        int r = 1;
        
        while(true){
            try{
                String resp = io.ask("Prova ad indovinare quale sarà la somma dei due: ");

                r = Integer.parseInt(resp);
                
                int [] dices = ((FortuneTrial)t).extract();
                
                io.inform (String.format("I numeri estratti sono: %d, %d. Somma: %d", dices[0], dices[1], dices[0] + dices[1]));
                
                if (r == dices[0] + dices[1])
                {
                    io.inform("Complimenti, hai fortuna!");
                    return true;
                } else {
                    io.inform("Peccato, sembri parecchio sfortunato.");
                    return false;
                }
                
            }catch(NumberFormatException ex){
                io.inform("Devi inserire un numero intero!!!");
            }
        }
    }
    
}
