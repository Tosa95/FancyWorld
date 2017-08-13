/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyworld;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.GamePersister;
import tosatto.fancyworld.game.KeyedGameInfo;
import tosatto.fancyworld.game.interactions.KeyedMainInteraction;
import tosatto.fancyworld.game.interactions.KeyedPassageInteraction;
import tosatto.fancyworld.game.interactions.places.KeyedPlaceInteraction;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.factories.BasicWorldFactory;
import tosatto.fancyworld.game.world.factories.KeyedWorldFactory;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;

/**
 *
 * @author Davide
 */
public class FancyWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MessageIO io = new ConsoleMessageIO();
        
//        io.inform("Hello world");
//        
////        io.ask("Come va?", new String[]{"bene","male"});
//        
//        io.presentMenu("Prova", new String[]{"ciao", "come", "va"});

        GamePersister gp = new GamePersister(GamePersister.SAVE_FILENAME_TEMPORARY, BaseGame.class);
        io = new ConsoleMessageIO();
        
        boolean load = false;
        
        if (gp.existsFile())
        {
            int resp = io.ask("C'è una partita salvata, vuoi caricarla?", new String[]{"si", "no"});
            
            if (resp == 0)
                load = true;
        }

        BaseGame g  = null;
        
        if (load)
        {
            try {
                g = (BaseGame)gp.load();
            } catch (Exception ex) {
                Logger.getLogger(FancyWorld.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(101274981236489263L), new KeyedWorldFactory(), 5, 10, 10, 20, 1);

            KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);

            KeyedPlayer p = new KeyedPlayer("Granli Brum");
            KeyedWorld w = (KeyedWorld)krwg.generate();

            g = new BaseGame(p, w, "Game");
            
            p.setPlace(w.getStartPlace());
        }
        
        KeyedPlayer kp = (KeyedPlayer)g.getPlayer();
        KeyedWorld kw = (KeyedWorld)g.getWorld();
        
        g.setGameInfo(new KeyedGameInfo() {
            @Override
            public boolean playerHasKey(String keyName) {
                return kp.hasKey(keyName);
            }

            @Override
            public int getKeyWeight(String keyName) {
                return kw.getKey(keyName).getWeight();
            }
        });
        
        
        g.setIo(io);
        g.setInteractions(new KeyedPassageInteraction(), new KeyedPlaceInteraction(), new KeyedMainInteraction());
        g.play();
    }
    
}
