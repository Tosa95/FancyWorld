/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.world.places.KeyedPlace;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class KeyedPlaceInteraction implements PlaceIntercation{

    @Override
    public void interact(MessageIO io, Game g, Place p) {
        
        io.inform(String.format("Ti trovi nel luogo %s, livello %d", p.getName(), p.getLevel()));
        
        KeyedPlace kp = (KeyedPlace)p;
        KeyedPlayer kPlayer = (KeyedPlayer) g.getPlayer();
        
        if (kp.hasKey())
        {
            int ans = io.ask(String.format("In questo luogo c'è la chiave %s. Vuoi prenderla?", kp.getKey()), new String[]{"si", "no"});
            
            if (ans == 0)
            {
                kPlayer.addKey(kp.getKey());
                kp.setKey(null);
                
                io.inform("Ok. Chiave presa");
            } else {
                io.inform("Ok. Chiave lasciata dove era");
            }
                
        } else {
            int ans = io.ask("Il luogo non ha alcuna chiave. Vuoi depositarne una?", new String[]{"si", "no"});
            
            if (ans == 0)
            {
                String[] keys = kPlayer.getKeys().toArray(new String[kPlayer.getKeys().size()]);
                
                int k = io.presentMenu("Seleziona chiave", keys);
                
                kp.setKey(keys[k]);
                kPlayer.removeKey(keys[k]);
               
                io.inform("Ok, chiave depositata");
            }
            else
            {
                io.inform("Ok. Proseguiamo!");
            }
        }
    }
    
}
