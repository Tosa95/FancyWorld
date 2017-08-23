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
 * Astrae l'interazione di un giocatore con un passaggio
 * @author Davide
 */
public interface PassageInteraction {
    
    /**
     * Interagisce con il giocatore per permettergli di compiere le scelte necessarie ad 
     * attraversare con successo un passaggio
     * @param io
     * @param g
     * @param p 
     */
    public void interact (MessageIO io, Game g, Passage p);
}
