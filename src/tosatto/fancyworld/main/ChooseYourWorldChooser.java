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
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;

/**
 *
 * @author Davide
 */
public class ChooseYourWorldChooser implements WorldChooser{

    private ParametersBundle getStandardBundle ()
    {
        ParametersBundle res = new ParametersBundle();
        
        res.addParameter("KEY_TYPE_NUMBER", 5);
        res.addParameter("MAX_KEYRING_WEIGHT", 50);
        res.addParameter("MAX_KEYRING_SIZE", 5);
        res.addParameter("MAX_KEY_WEIGHT", 25);
        res.addParameter("TRIAL_TYPES_NUMBER", 3);
        res.addParameter("MAX_TRIAL_VALUE", 20);
        res.addParameter("INITIAL_POINTS", 10);
        res.addParameter("GOAL_POINTS", 100);
        
        return res;
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
       
       io.showMenu("Tipologie di mondo", prepareTopologyMenuEntries());
       
       return null;
        
    }
    
}
