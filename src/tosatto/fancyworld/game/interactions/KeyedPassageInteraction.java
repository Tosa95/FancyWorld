/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.passages.ClosedPassage;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;

/**
 *
 * @author Davide
 */
public class KeyedPassageInteraction implements PassageInteraction{

    @Override
    public void interact(MessageIO io, Game g, Passage p) {
        
        if (!(p instanceof KeyedPassage))
        {
            //TODO: Aggiustare qua, non mi piace
        }
        else
        {
            KeyedPassage kp = (KeyedPassage)p;
            KeyedPlayer kPlayer = (KeyedPlayer)g.getPlayer();
            KeyedWorld kw = (KeyedWorld)g.getWorld();

            if (kp.isClosed())
            {
                io.inform(String.format("Il passaggio è chiuso a chiave. Serve la chiave %s", kp.requiredKey()));

                if (kPlayer.hasKey(kp.requiredKey()))
                {
                    int ans = io.ask("Hai la chiave per aprire il passaggio! Vuoi aprirlo?", new String[]{"si","no"});

                    if (ans == 0)
                    {
                        kp.open();
                        io.inform("Passaggio aperto");
                    } else {
                        io.inform("Ok. Non apro. Però se non apri non puoi passare!");
                    }
                }else{
                    io.inform("Mi dispiace, ma non possiedi la chiave necessaria per passare. Riprova quando la avrai");
                }
            }
        }
    }
    
}
