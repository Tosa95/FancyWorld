/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.checkers;

import tosatto.fancyworld.game.Game;

/**
 * Interfaccia implementata da un oggetto in grado di determinare se un giocatore abbia vinto o meno
 * @author Davide
 */
public interface WinChecker {
    
    /**
     * Determina se il giocatore ha vinto
     * @param g
     * @return 
     */
    public boolean wins (Game g);
    
    /**
     * Ritorna il numero di punti necessari per vincere
     * @param g
     * @return 
     */
    public int pointsToWin (Game g);
    
}
