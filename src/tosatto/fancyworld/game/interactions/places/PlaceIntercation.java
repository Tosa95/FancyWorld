/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.places;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Astrae un'interazione tra il giocatore e un luogo
 * @author Davide
 */
public interface PlaceIntercation {
    
    /**
     * Interagisce con il giocatore per permettergli di gestire le risorse presenti nel luogo
     * @param io
     * @param g
     * @param p 
     */
    public void interact (MessageIO io, Game g, Place p); 
    
    /**
     * Dice quale tipologia di luogo l'oggetto è in grado di trattare
     * @return 
     */
    public Class<?> type ();
}
