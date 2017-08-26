/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main;

import tosatto.fancyworld.main.bundles.ParametersBundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.GamePersister;
import tosatto.fancyworld.game.info.BaseBundledGameInfo;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.TrialedWorld;
import tosatto.fancyworld.game.world.factories.BundledWorldFactory;
import tosatto.fancyworld.game.world.factories.TrialedWorldFactory;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.BundledRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;
import tosatto.fancyworld.game.world.keys.Key;
import tosatto.fancyworld.game.world.trials.Trial;
import tosatto.fancyworld.main.interactions.bundles.BaseBundleInteraction;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;
import tosatto.fancyworld.main.bundles.factories.BundleFactory;
import tosatto.fancyworld.main.bundles.factories.StandardBundleFactory;
import tosatto.fancyworld.main.interactions.newsession.NewSessionInteraction;

/**
 * Rappresenta un oggetto in grado di guidare l'utente nella scelta di un mondo in cui giocare
 * @author Davide
 */
public class ChooseYourWorldChooser implements WorldChooser{
    
    private NewSessionInteraction newSessInt;

    public ChooseYourWorldChooser(NewSessionInteraction newSessInt) {
        this.newSessInt = newSessInt;
    }
    
    
    
    private String[] prepareTopologyMenuEntries ()
    {
         List<String> entries = new ArrayList<>();
         
         for (String tname : WorldTopologies.getInstance().getWorldTopologiesNames())
         {
             entries.add(tname);
         }
         
         String res[] = new String[entries.size()];
         
         return entries.toArray(res);
    }
    
    @Override
    public Game choose(MessageIO io) {
        
       io.inform("Benvrenuto in Choose Your World. Seleziona la tipologia di mondo di cui vuoi fare parte");
       
       String[] menu = prepareTopologyMenuEntries();
       
       int res = io.showMenu("Tipologie di mondo", menu);
       
       String selectedTopology = WorldTopologies.getInstance().getWorldTopologiesNames().get(res);
       
       GamePersister gp = new GamePersister(BaseGame.class, selectedTopology);
       
       boolean newInstance = true;
       
       BaseGame game = null;
       
       if (gp.existsFile())
       {
           int load = io.ask("C'è una sessione già salvata per questa tipologia di mondo, vuoi caricarla?", new String[]{"si", "no"});
       
           if (load == 0)
           {
               newInstance = false;
               try {
                   game = (BaseGame)gp.load();
                   
                   io.inform("Sessione caricata");
               } catch (Exception ex) {
                   io.inform("Errore nel caricamento della sessione. Riportare il seguente messaggio ad un programmatore; "
                           + ex.toString());
               }
           }
           
       }
       
       if (newInstance)
       {
           game = (BaseGame)newSessInt.interact(selectedTopology, io);
       }
       
       game.setGameInfo(new BaseBundledGameInfo(game));
       
       return game;
        
    }
    
}
