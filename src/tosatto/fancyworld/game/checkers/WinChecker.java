/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.checkers;

import tosatto.fancyworld.game.Game;

/**
 *
 * @author Davide
 */
public interface WinChecker {
    
    public boolean wins (Game g);
    
    public int pointsToWin (Game g);
    
}
