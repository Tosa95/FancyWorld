/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.GamePersister;
import tosatto.fancyworld.game.checkers.PointBasedWinChecker;
import tosatto.fancyworld.game.info.BaseTrialedGameInfo;
import tosatto.fancyworld.game.info.KeyedGameInfo;
import tosatto.fancyworld.game.interactions.KeyedMainInteraction;
import tosatto.fancyworld.game.interactions.KeyedPassageInteraction;
import tosatto.fancyworld.game.interactions.places.KeyedPlaceInteraction;
import tosatto.fancyworld.game.interactions.places.UniversalPlaceInteraction;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.factories.BasicWorldFactory;
import tosatto.fancyworld.game.world.factories.KeyedWorldFactory;
import tosatto.fancyworld.game.world.factories.TrialedWorldFactory;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;

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

//        GamePersister gp = new GamePersister(BaseGame.class, "Fancy Worls NG");
//        io = new ConsoleMessageIO();
//        
//        boolean load = false;
//        
//        if (gp.existsFile())
//        {
//            int resp = io.ask("C'è una partita salvata, vuoi caricarla?", new String[]{"si", "no"});
//            
//            if (resp == 0)
//                load = true;
//        }
//
//        BaseGame g  = null;
//        
//        if (load)
//        {
//            try {
//                g = (BaseGame)gp.load();
//            } catch (Exception ex) {
//                Logger.getLogger(FancyWorld.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            //101274981236489263L
//            BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(101274981236489267L), new TrialedWorldFactory(), 5, 10, 10, 20, 1);
//
//            //KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);
//            KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);
//            
//            TrialedRandomWorldGenerator trwg = new TrialedRandomWorldGenerator(krwg, 0.4);
//
//            PointedPlayer p = new PointedPlayer("Granli Brum");
//            p.setPoints(10);
//            TrialedWorld w = (TrialedWorld)trwg.generate();
//
//            g = new BaseGame(p, w, "Game");
//            
//            p.setPlace(w.getStartPlace());
//        }
//        
//        PointedPlayer kp = (PointedPlayer)g.getPlayer();
//        KeyedWorld kw = (KeyedWorld)g.getWorld();
//        

        WorldChooser chooser = new ChooseYourWorldChooser();
        
        BaseGame g = (BaseGame)chooser.choose(io);
        
        g.setIo(io);
        g.setInteractions(new KeyedPassageInteraction(), new UniversalPlaceInteraction(), new KeyedMainInteraction());
        g.play(new PointBasedWinChecker());
    }
    
}
