/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;

/**
 * Rappresenta un oggetto in grado di guidare l'utente nella scelta di un mondo in cui giocare
 * @author Davide
 */
public interface WorldChooser {
    
    public Game choose (MessageIO io);
    
}
