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
 *
 * @author Davide
 */
public interface PlaceIntercation {
    public void interact (MessageIO io, Game g, Place p); 
    public Class<?> type ();
}
