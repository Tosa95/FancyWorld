/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import tosatto.fancyworld.game.world.places.Place;

/**
 * Interfaccia utilizzata per la creazione di una verisione generica dell'algoritmo di visita del mondo
 * @author Davide
 */
public interface PlaceAction {
    
    public void interact (Place p);
    
}
