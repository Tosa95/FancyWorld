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
import tosatto.fancyworld.main.bundles.StandardBundleInteraction;

/**
 * Rappresenta un oggetto in grado di guidare l'utente nella scelta di un mondo in cui giocare
 * @author Davide
 */
public class ChooseYourWorldChooser implements WorldChooser{
    
    
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
    
    private String[] prepareKeySettingsMenuEntries (List<Key> l)
    {
        List<String> entries = new ArrayList<>();
         
         for (Key k : l)
         {
             entries.add(k.getName() + ": " + k.getWeight());
         }
         
         entries.add("continua");
         
         String res[] = new String[entries.size()];
         
         return entries.toArray(res);
    }
    
    private void setKeyWeights (KeyedWorld w, MessageIO io, int maxKeyWeight)
    {
        
        io.inform("Puoi impostare il peso delle chiavi: ");
        
        int response = 0;
        
        do
        {
            
           List<Key> keyList = new ArrayList<>(w.getKeys().values());
            
           response = io.showMenu("Seleziona quale peso modificare", prepareKeySettingsMenuEntries(keyList));
            
            if (response < w.getKeys().size())
            {
                int newVal = io.askForPositiveInteger("Inserisci il nuovo peso della chiave", "Devi inserire un numero intero positivo!");
                
                if (newVal <= maxKeyWeight)
                {
                    keyList.get(response).setWeight(newVal);
                    io.inform("Peso della chiave selezionata correttamente modificato");
                } else {
                    io.inform("Le chiavi non possono avere peso superiore a " + Integer.toString(maxKeyWeight));
                }
            }
            
        }while(response < w.getKeys().size());
        
    }
    
    private String[] prepareTrialValueSettingMenu (List<Trial> l)
    {
        List<String> entries = new ArrayList<>();
         
         for (Trial t : l)
         {
             entries.add(t.getType() + ": " + t.getValue());
         }
         
         entries.add("continua");
         
         String res[] = new String[entries.size()];
         
         return entries.toArray(res);
    }
    
    private void setTrialsValues (TrialedWorld w, MessageIO io, int maxTrialValue)
    {
        io.inform("Puoi impostare il valore delle prove: ");
        
        int response = 0;
        
        do
        {
            List<Trial> trials = new ArrayList<>(w.getTrials());
            
            response = io.showMenu("Seleziona quale valore modificare", prepareTrialValueSettingMenu(trials));
            
            if (response < w.getTrials().size())
            {
                int newVal = io.askForPositiveInteger("Inserisci il nuovo valore della prova", "Devi inserire un numero intero positivo!");
                
                if (newVal <= maxTrialValue)
                {
                    trials.get(response).setValue(newVal);
                    io.inform("Il valore della prova selezionata è stato modificato con successo");
                } else {
                    io.inform("Le prove non possono avere valore superiore a " + Integer.toString(maxTrialValue));
                }
            }
            
        }while(response < w.getTrials().size());
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
           io.inform("Ora verrà creata una nuova sessione.");
           
           
           
           StandardBundleInteraction sbp = new StandardBundleInteraction();
           
           ParametersBundle pb = sbp.getStandardBundle();
           
           sbp.interact(pb, io);
           
           BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(WorldTopologies.getInstance().getTopologySeed(selectedTopology))
                   , new BundledWorldFactory(), 5, 10, 10, 20, 1);

           KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, pb.getParameter(sbp.KEY_TYPE_NUMBER), pb.getParameter(sbp.MAX_KEY_WEIGHT),  pb.getParameter(sbp.KEY_TYPE_NUMBER)+1, 0.3, 1);

           TrialedRandomWorldGenerator trwg = new TrialedRandomWorldGenerator(krwg, 0.4, pb.getParameter(sbp.TRIAL_TYPES_NUMBER));

           BundledRandomWorldGenerator bundrwg = new BundledRandomWorldGenerator(trwg, pb);
           
           PointedPlayer p = new PointedPlayer("Granli Brum");
           p.setPoints(pb.getParameter(sbp.INITIAL_POINTS));
           BundledWorld w = (BundledWorld)bundrwg.generate();

           w.setBundle(pb);
           
           game = new BaseGame(p, w, "Game");
           
           

           p.setPlace(w.getStartPlace());
           
           setKeyWeights(w, io, pb.getParameter(StandardBundleInteraction.MAX_KEY_WEIGHT));
           setTrialsValues(w, io, pb.getParameter(StandardBundleInteraction.MAX_TRIAL_VALUE));
           
           game.setName(selectedTopology);
       }
       
       game.setGameInfo(new BaseBundledGameInfo(game));
       
       return game;
        
    }
    
}
