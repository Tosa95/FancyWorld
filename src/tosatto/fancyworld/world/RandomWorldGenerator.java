/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import java.util.ArrayList;
import java.util.Random;
import tosatto.fancyworld.levels.Level;
import tosatto.fancyworld.places.Place;

/**
 *
 * @author Davide
 */
public class RandomWorldGenerator {
    
    private static int boundRnd (int min, int max)
    {
        Random r = new Random();
        
        return min + r.nextInt(max-min);
    }
    
    public static World generate(int minLvl, int maxLvl, int minPlaces, int maxPlaces)
    {
        int lvls = boundRnd(minLvl, maxLvl);
        int places = boundRnd(minPlaces, maxPlaces);
        
        World res = new World(NameGenerator.getRandomName(4, 10), "start");
        
        
        
        for (int i = 0; i < lvls; i++)
        {
            res.addLevel(new Level(i, String.format("lvl%d", i), ""));
        }
        
        res.addPlace(new Place("start", "", false, 0));
        
        for (int pl = places - 2; pl > 0; pl--)
        {
            res.addPlace(new Place(NameGenerator.getRandomName(4, 8), "", false, boundRnd(0, lvls)));
        }
        
        res.addPlace(new Place(NameGenerator.getRandomName(4, 8), "", true, boundRnd(0, lvls)));
        
        return res;
    }
}
