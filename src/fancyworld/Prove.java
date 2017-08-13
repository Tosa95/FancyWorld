/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fancyworld;

import java.io.IOException;
import java.util.Random;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.info.BaseTrialedGameInfo;
import tosatto.fancyworld.game.info.KeyedGameInfo;
import tosatto.fancyworld.game.interactions.places.UniversalPlaceInteraction;
import tosatto.fancyworld.game.interactions.trials.FortuneTrialInteraction;
import tosatto.fancyworld.game.interactions.trials.SequenceTrialInteraction;
import tosatto.fancyworld.game.interactions.trials.UniversalTrialInteraction;
import tosatto.fancyworld.game.interactions.trials.WordTrialInteraction;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.TrialedWorldFactory;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.places.TrialedPlace;
import tosatto.fancyworld.game.world.trials.FortuneTrial;
import tosatto.fancyworld.game.world.trials.SequenceTrial;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.game.world.trials.WordTrial;
import tosatto.fancyworld.game.world.trials.Words;

/**
 *
 * @author Davide
 */
public class Prove {
    
    public static void main(String[] args) throws IOException {
        //SequenceTrialInteraction sti = new SequenceTrialInteraction();
        
        //sti.interact(new SequenceTrial(), new ConsoleMessageIO());
        
//        FortuneTrial ft = new FortuneTrial();
//        
//        FortuneTrialInteraction fti = new FortuneTrialInteraction();
//        
//        fti.interact(ft, new ConsoleMessageIO());

//        Words w = Words.getInstance();
//        
//        for (int i = 0; i < 100; i++)
//        {
//            System.out.println(w.getRandomWord(5));
//        }

//        WordTrial wt = new WordTrial();
//        
//        wt.init();
//        
//        System.out.println(wt.getObfuscatedWord());
//        System.out.println(wt.getRealWord());

//        UniversalTrialInteraction uti = new UniversalTrialInteraction();
//        
//        uti.interact(new Trial() {
//            @Override
//            public int getValue() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public String getType() {
//                return "eh, volevi!";
//            }
//        }, new ConsoleMessageIO());
//        uti.interact(new WordTrial(), new ConsoleMessageIO());
//        uti.interact(new SequenceTrial(), new ConsoleMessageIO());
//        uti.interact(new FortuneTrial(), new ConsoleMessageIO());

        BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(101274981236489263L), new TrialedWorldFactory(), 5, 10, 10, 20, 1);

        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);
        
        TrialedRandomWorldGenerator trwg = new TrialedRandomWorldGenerator(krwg, 1);
        
        trwg.generate();

        TrialedWorld w = new TrialedWorld("prova", "prova");
        
        w.addKey(new Key("k", 20));
        w.addTrial(new WordTrial());
        
        TrialedPlace p = new TrialedPlace("prova", "", false, 0);
        
        w.addLevel(new Level(0, "livello 0", "bla bla"));
        
        w.addPlace(p);
        
        p.setKey("k");
        p.setTrial("Word");
        
        UniversalPlaceInteraction upi = new UniversalPlaceInteraction();
        
        BaseGame g = new BaseGame(new PointedPlayer("prova"), w, "prova");
        
        g.setGameInfo(new BaseTrialedGameInfo(g));
        
        upi.interact(new ConsoleMessageIO(), g, p);
    }
}
