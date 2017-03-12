/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import tosatto.fancyworld.levels.Level;
import tosatto.fancyworld.passages.OpenPassage;
import tosatto.fancyworld.passages.Passage;
import tosatto.fancyworld.passages.exceptions.PassageException;
import tosatto.fancyworld.places.Directions;
import tosatto.fancyworld.places.Place;

/**
 *
 * @author Davide
 */
public class RandomWorldGenerator {
    
    private static final String[] dirsOnLevel = {Directions.EST, Directions.NORTH, Directions.SOUTH, Directions.WEST};
    private static final String[] dirsUD = {Directions.UP, Directions.DOWN};
    
    private static int boundRnd (int min, int max)
    {
        Random r = new Random();
        
        return min + r.nextInt(max-min);
    }
    
    private static boolean directlyConnected (Place p1, Place p2)
    {
        
        for (Passage pass: p1.getPassages().values())
        {
            try {
                if (pass.next().equals(p2.getName()))
                {
                    return true;
                }
            } catch (PassageException ex) {
                
            }
        }
        
        return false;
    }
    
    private static boolean connected (World w, String p1, String p2, List<String> visited)
    {
        Place pl1 = w.getPlace(p1);
        Place pl2 = w.getPlace(p2);
        
        if (p1.equals(p2))
        {
            return true;
        }
        else if (directlyConnected(pl1, pl2))
        {
            return true;
        } else {
            visited.add(p1);
            
            for (Passage pass: pl1.getPassages().values())
            {
                try {
                    String nxt = pass.next();
                    
                    if (!visited.contains(nxt) && connected(w, nxt, p2, visited))
                    {
                        return true;
                    }
                    
                } catch (PassageException ex) {
                    
                }
                
            }
            
            return false;
        }
    }
    
    private static class PlacePair {
        String p1;
        String p2;

        public PlacePair(String p1, String p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        
        
     }
    
    private static boolean fullyConnected (World w)
    {
        Random r = new Random();
        
        Place[] places = w.getPlaces().toArray(new Place[w.getPlaces().size()]);
        
        int pNum = places.length;
        
        for (int i = 0; i < pNum; i++)
        {
            for (int j = 0; j < pNum; j++)
            {
                if (! connected(w, places[i].getName(), places[j].getName(), new ArrayList<>()))
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean canBeDirectlyConnected (Place p1, Place p2)
    {
        if (p1.getLevel() == p2.getLevel() && hasFreeDirection(p1, dirsOnLevel) && hasFreeDirection(p2, dirsOnLevel))
        {
            return true;
        }
        else if (p1.getLevel() == p2.getLevel() + 1 && isFree(p1, Directions.DOWN) && isFree(p2, Directions.UP)) 
        {
            return true;
        }
        else if (p1.getLevel() == p2.getLevel() - 1 && isFree(p1, Directions.UP) && isFree(p2, Directions.DOWN)) 
        {
            return true;
        }
        
        return false;
    }
    
    private static PlacePair pickRandomUnconnectedPair (World w)
    {
        if (fullyConnected(w))
            return null;
        
        Random r = new Random();
        
        Place[] places = w.getPlaces().toArray(new Place[w.getPlaces().size()]);
        
        int pNum = places.length;
        
        while (true)
        {
            Place p1 = places[r.nextInt(pNum)];
            Place p2 = places[r.nextInt(pNum)];
            
            if (!connected(w, p1.getName(), p2.getName(), new ArrayList<>()) )
            {
                if (canBeDirectlyConnected(p1, p2))
                {
                    return new PlacePair(p1.getName(), p2.getName());
                }
                    
            }
        }
    }
    
    public static boolean isFree (Place p, String dir)
    {
        try{
            p.getPassage(dir).next();
        }catch (PassageException e)
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean hasFreeDirection (Place p, String[] dirs)
    {
        for (String dir: dirs)
        {
            if (isFree(p,dir))
                return true;
        }
        
        return false;
    }
    
    public static void connect (World w, String p1, String p2)
    {
        
        
        Random r = new Random();
        
        Place pl1 = w.getPlace(p1);
        Place pl2 = w.getPlace(p2);
        
        String dir1 = "", dir2 = "";
        
        if (pl1.getLevel() == pl2.getLevel())
        {
            String[] dirs = dirsOnLevel;

            int d1,d2;
            
            d1 = r.nextInt(dirs.length);

            while (!isFree(pl1,dirs[d1]))
                d1 = r.nextInt(dirs.length);

            d2 = r.nextInt(dirs.length);

            while (!isFree(pl2,dirs[d2]))
                d2 = r.nextInt(dirs.length);

            dir1 = dirs[d1];
            dir2 = dirs[d2];
        } 
        else if (pl1.getLevel() == pl2.getLevel() + 1)
        {
            dir1 = Directions.DOWN;
            dir2 = Directions.UP;
        }
        else if (pl1.getLevel() == pl2.getLevel() - 1)
        {
            dir1 = Directions.UP;
            dir2 = Directions.DOWN;
        }
        
        String passageName = NameGenerator.getRandomName(3, 6);

        OpenPassage p12 = new OpenPassage(passageName, p2);
        OpenPassage p21 = new OpenPassage(passageName, p1);

        pl1.addPassage(dir1, p12);
        pl2.addPassage(dir2, p21);
    }
    
    public static World generate(int minLvl, int maxLvl, int minPlaces, int maxPlaces)
    {
        final String startName = "start";
        
        int lvls = boundRnd(minLvl, maxLvl);
        int places = boundRnd(minPlaces, maxPlaces);
        
        if (places<lvls)
            places = lvls + 2;
        
        World res = new World(NameGenerator.getRandomName(4, 10), startName);
        
        
        
        for (int i = 0; i < lvls; i++)
        {
            res.addLevel(new Level(i, String.format("lvl%d", i), ""));
            res.addPlace(new Place(NameGenerator.getRandomName(4, 8), "", false, i));
        }
        
        res.addPlace(new Place(startName, "", false, 0));
        
        for (int pl = places - (2 + lvls); pl > 0; pl--)
        {
            res.addPlace(new Place(NameGenerator.getRandomName(4, 8), "", false, boundRnd(0, lvls)));
        }
        
        String goalName = NameGenerator.getRandomName(4, 8);
        
        res.addPlace(new Place(goalName, "", true, boundRnd(0, lvls)));
        
        while (!fullyConnected(res))
        {
            PlacePair pair = pickRandomUnconnectedPair(res);
            
            connect(res, pair.p1, pair.p2);
        }
        
        return res;
    }
}
