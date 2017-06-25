/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyworld;

import java.util.Random;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.interactions.KeyedMainInteraction;
import tosatto.fancyworld.game.interactions.KeyedPassageInteraction;
import tosatto.fancyworld.game.interactions.KeyedPlaceInteraction;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.world.NameGenerator;
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

        BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(101274981236489263L), new KeyedWorldFactory(), 5, 10, 10, 20, 1);
        
        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);

        BaseGame g = new BaseGame(new KeyedPlayer("Davide"), krwg.generate(), "Gioco prova");
        g.setIo(new ConsoleMessageIO());
        g.setInteractions(new KeyedPassageInteraction(), new KeyedPlaceInteraction(), new KeyedMainInteraction());
        g.play();
    }
    
}
