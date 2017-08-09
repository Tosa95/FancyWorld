/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.SequenceTrial;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class SequenceTrialInteraction implements TrialInteraction{

    private final static int SHOWN_SEQUENCE_LENGTH = 6;
    
    @Override
    public boolean interact(Trial t, MessageIO io) {
        
        io.inform("Ti verrà ora presentata una sequenza di numeri: ");
        
        SequenceTrial st = (SequenceTrial)t;
        
        st.init();
        
        for (int i = 0; i < SHOWN_SEQUENCE_LENGTH; i++)
        {
            io.inform(Integer.toString(st.next()));
        }
        
        
        while (true)
        {
            try{
                String resp = io.ask("Inserire il numero successivo nella sequenza: ");

                int r = Integer.parseInt(resp);
                
                if (r == st.next())
                {
                    io.inform("Bravo! La risposta è corretta");
                    return true;
                } else {
                    
                    io.inform(String.format("Purtroppo non hai indovinato. La formula della"
                            + " sequenza era: next = prev*(%d) + %d + (%d)*prev2", st.getMul(), st.getSum(), st.getPrevCoeff()));
                    
                    return false;
                }
                
            }catch(NumberFormatException ex){
                io.inform("Devi inserire un numero intero!!!");
            }
        }
        
    }
    
}
