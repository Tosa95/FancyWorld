/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.world.passages.Passage;

/**
 * Astrae l'interazione principale tra giocatore e sistema: quella in cui il giocatore
 * decide la direzione in cui andare oppure decide di uscire
 * @author Davide
 */
public interface MainInteraction {
    
    /**
     * Interagisce con il giocatore per fargli decidere cosa fare.
     * @param io
     * @param passInt
     * @param g
     * @return True: il giocatore ha scelto di continuare in una direzione, false: il giocatore ha scelto di uscire
     */
    public boolean interact (MessageIO io, PassageInteraction passInt, Game g);
}
