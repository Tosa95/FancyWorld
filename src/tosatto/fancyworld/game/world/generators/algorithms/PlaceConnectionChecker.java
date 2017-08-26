/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.algorithms;

import tosatto.fancyworld.game.world.World;

/**
 *
 * @author Davide
 */
public interface PlaceConnectionChecker {
    
    public boolean arePlacesConnected (World w, String p1, String p2);
    
}
