/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.algorithms;

import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public interface PlaceVisitor {
    
    public void visit(Place p);
    
}
