/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyworld;

import java.io.IOException;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.game.interactions.trials.FortuneTrialInteraction;
import tosatto.fancyworld.game.interactions.trials.SequenceTrialInteraction;
import tosatto.fancyworld.game.world.trials.FortuneTrial;
import tosatto.fancyworld.game.world.trials.SequenceTrial;
import tosatto.fancyworld.game.world.trials.Words;

/**
 *
 * @author Davide
 */
public class Prove {
    
    public static void main(String[] args) throws IOException {
        //SequenceTrialInteraction sti = new SequenceTrialInteraction();
        
        //sti.interact(new SequenceTrial(), new ConsoleMessageIO());
        
//        FortuneTrial ft = new FortuneTrial();
//        
//        FortuneTrialInteraction fti = new FortuneTrialInteraction();
//        
//        fti.interact(ft, new ConsoleMessageIO());

        Words w = Words.getInstance();
        
        for (int i = 0; i < 100; i++)
        {
            System.out.println(w.getRandomWord(5));
        }
    }
}
