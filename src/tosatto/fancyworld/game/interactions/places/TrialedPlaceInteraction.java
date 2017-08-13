/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.places;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.interactions.trials.UniversalTrialInteraction;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.places.Place;
import tosatto.fancyworld.game.world.places.TrialedPlace;

/**
 *
 * @author Davide
 */
public class TrialedPlaceInteraction implements PlaceIntercation{

    @Override
    public void interact(MessageIO io, Game g, Place p) {
        TrialedPlace tp = (TrialedPlace)p;
        TrialedWorld tw = (TrialedWorld)g.getWorld();
        
        if (tp.hasTrial())
        {
            io.inform(String.format("Questo posto ha una prova di tipo %s, valore %d punti", tw.getTrial(tp.getTrial()).getType(), tw.getTrial(tp.getTrial()).getValue()));
            
            int res = io.ask("Vuoi sottoporti alla prova?", new String[] {"si", "no"});
            
            if (res == 0)
            {
                UniversalTrialInteraction uti = new UniversalTrialInteraction();
                
                uti.doTrial((PointedPlayer)g.getPlayer(), tw.getTrial(tp.getTrial()), io);
                
            }else{
                io.inform("Perfetto, continuiamo!!!");
            }
        }
    }

    @Override
    public Class<?> type() {
        return TrialedPlace.class;
    }
    
}
