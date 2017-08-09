/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public interface TrialInteraction {
    public boolean interact (Trial t, MessageIO io);
}
