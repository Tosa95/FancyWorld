/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyworld;

import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.factories.BasicWorldFactory;

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

        BaseGame g = new BaseGame(new Player("Davide"), new BasicRandomWorldGenerator(new BasicWorldFactory(), 5, 7, 10, 20, 100).generate(), "Gioco prova");
        g.setIo(new ConsoleMessageIO());
        g.play();
    }
    
}
