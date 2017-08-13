/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.places;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.interactions.trials.TrialInteraction;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class UniversalPlaceInteraction implements PlaceIntercation{

    private List<PlaceIntercation> interactions = new ArrayList<>();

    public UniversalPlaceInteraction() {
        
        interactions.add(new KeyedPlaceInteraction());
        interactions.add(new TrialedPlaceInteraction());
        
    }
    
    
    
    @Override
    public void interact(MessageIO io, Game g, Place p) {
        
        for (PlaceIntercation pi : interactions)
        {
            Class<?> c = pi.type();
            if (c.isAssignableFrom(p.getClass()))
            {
                pi.interact(io, g, p);
            }
        }
        
    }

    @Override
    public Class<?> type() {
        return Place.class;
    }
    
}
