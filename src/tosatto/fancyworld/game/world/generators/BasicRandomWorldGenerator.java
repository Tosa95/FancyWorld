/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.OpenPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class BasicRandomWorldGenerator implements RandomWorldGenerator{
    
    private static final String[] dirsOnLevel = {Directions.EST, Directions.NORTH, Directions.SOUTH, Directions.WEST};
    private static final String[] dirsUD = {Directions.UP, Directions.DOWN};
    
    private Random r;
    private NameGenerator ng;
    private WorldFactory wf;

    private int minLvl, maxLvl, minPlaces, maxPlaces, difficulty;
    
    public BasicRandomWorldGenerator(Random r, 
                                WorldFactory wf,
                                int minLvl, 
                                int maxLvl, 
                                int minPlaces, 
                                int maxPlaces, 
                                int difficulty) {
        
        this.r = r;
        ng = new NameGenerator(r);
        this.wf = wf;
        
        this.minLvl = minLvl;
        this.maxLvl = maxLvl;
        
        this.minPlaces = minPlaces;
        this.maxPlaces = maxPlaces;
        
        this.difficulty = difficulty;
    }

    public BasicRandomWorldGenerator(WorldFactory wf,
                                int minLvl, 
                                int maxLvl, 
                                int minPlaces, 
                                int maxPlaces, 
                                int difficulty) {
        this(new Random(), wf, minLvl, maxLvl, minPlaces, maxPlaces, difficulty);
    }
    
    
    
    /**
     * Ritorna un numero casuale in un intervallo
     * @param min 
     * @param max 
     * @return 
     */
    private int boundRnd (int min, int max)
    {
        
        return Helper.boundRnd(r, min, max);
    }
    
    /**
     * Dice se due posti sono direttamente connessi o no
     * @param p1
     * @param p2
     * @return 
     */
    private boolean directlyConnected (World w, Place p1, Place p2)
    {
        
        for (Passage pass: w.getAllPassages(p1.getPassages().values()))
        {
            try {
                if (pass.next(p1.getName()).equals(p2.getName()))
                {
                    return true;
                }
            } catch (PassageException ex) {
                
            }
        }
        
        return false;
    }
    
    /**
     * Dice se due posti sono connessi
     * @param w
     * @param p1
     * @param p2
     * @param visited Lista dei posti già visitati. Deve essere vuota all'inizio
     * @return 
     */
    private boolean connected (World w, String p1, String p2, List<String> visited)
    {
        Place pl1 = w.getPlace(p1);
        Place pl2 = w.getPlace(p2);
        
        if (p1.equals(p2))
        {
            return true;
        }
        else if (directlyConnected(w, pl1, pl2))
        {
            return true;
        } else {
            visited.add(p1);
            
            for (Passage pass: w.getAllPassages(pl1.getPassages().values()))
            {
                try {
                    String nxt = pass.next(p1);
                    
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

    @Override
    public Random getRandom() {
        return r;
    }

    @Override
    public NameGenerator getNameGenerator() {
        return ng;
    }

    @Override
    public WorldFactory getWorldFactory() {
        return wf;
    }
    
    /**
     * Classe usata per ritornare una coppia di posti
     */
    private class PlacePair {
        String p1;
        String p2;

        public PlacePair(String p1, String p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        
        
     }
    
    /**
     * Dice se il grafo dei posti di un mondo è connesso o meno
     * @param w
     * @return 
     */
    private boolean connected (World w)
    {
        
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
    
    /**
     * Dice se due posti possono essere connessi direttamente.
     * 
     * 
     * @param p1
     * @param p2
     * @return 
     */
    private boolean canBeDirectlyConnected (World w, Place p1, Place p2)
    {
        if (p1.getLevel() == p2.getLevel() && hasFreeDirection(w, p1, dirsOnLevel) && hasFreeDirection(w, p2, dirsOnLevel))
        {
            return true;
        }
        else if (p1.getLevel() == p2.getLevel() + 1 && isFree(w, p1, Directions.DOWN) && isFree(w, p2, Directions.UP)) 
        {
            return true;
        }
        else if (p1.getLevel() == p2.getLevel() - 1 && isFree(w, p1, Directions.UP) && isFree(w, p2, Directions.DOWN)) 
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Ritorna una coppia a caso di posti (può ritornare due volte lo stesso posto)
     * @param w
     * @return 
     */
    private PlacePair pickPair (World w)
    {
        
        Place[] places = w.getPlaces().toArray(new Place[w.getPlaces().size()]);
        
        int pNum = places.length;
        
        
        Place p1 = places[r.nextInt(pNum)];
        Place p2 = places[r.nextInt(pNum)];
        
        while (p1.getName().equals(p2.getName()))
        {
            p1 = places[r.nextInt(pNum)];
            p2 = places[r.nextInt(pNum)];
        }
        
        return new PlacePair(p1.getName(), p2.getName());
    }
    
    /**
     * Ritorna una coppia di posti non connessi
     * @param w
     * @return 
     */
    private PlacePair pickRandomUnconnectedPair (World w)
    {
        if (connected(w))
            //Se il grafo è connesso, non è possibile trovare una coppia di posti non connessi
            return null;
        
        while (true)
        {
            PlacePair res = pickPair(w);
            
            if (!connected(w, res.p1, res.p2, new ArrayList<>()) )
            {
                if (canBeDirectlyConnected(w, w.getPlace(res.p1), w.getPlace(res.p2)))
                {
                    return res;
                }
                    
            }
            
            connected(w);
        }
    }
    
    /**
     * Dice se una direzione è libera
     * @param p
     * @param dir
     * @return 
     */
    public boolean isFree (World w, Place p, String dir)
    {
        try{
            w.getPassage(p.getPassage(dir)).next(p.getName());
        }catch (PassageException e)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Dice se un posto ha direzioni libere
     * @param p
     * @param dirs Lista delle direzioni da controllare
     * @return 
     */
    public boolean hasFreeDirection (World w, Place p, String[] dirs)
    {
        for (String dir: dirs)
        {
            if (isFree(w, p,dir))
                return true;
        }
        
        return false;
    }
    
    /**
     * Connette due nodi. DEVONO ESSERE DIRETTAMENTE COLLEGABILI
     * @param w
     * @param p1
     * @param p2 
     */
    public void connect (NameGenerator ng, World w, String p1, String p2)
    {
        
        Place pl1 = w.getPlace(p1);
        Place pl2 = w.getPlace(p2);
        
        String dir1 = "", dir2 = "";
        
        if (pl1.getLevel() == pl2.getLevel())
        {
            String[] dirs = dirsOnLevel;

            int d1,d2;
            
            d1 = r.nextInt(dirs.length);

            while (!isFree(w, pl1,dirs[d1]))
                d1 = r.nextInt(dirs.length);

            d2 = r.nextInt(dirs.length);

            while (!isFree(w, pl2,dirs[d2]))
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
        
        String passageName = ng.getUniqueRandomName(3, 6);

        Passage p = wf.getPassage(passageName, p1, p2);
        
        w.addPassage(p);

        pl1.addPassage(dir1, passageName);
        pl2.addPassage(dir2, passageName);
    }
    
    /**
     * Genera un nuovo mondo casuale
     * @return 
     */
    public World generate()
    {
        final String startName = "start";
        
        //Sceglie un numero di livelli
        int lvls = boundRnd(minLvl, maxLvl);

        //Sceglie un numero di posti
        int places = boundRnd(minPlaces, maxPlaces);
        
        if (places<lvls)
            places = lvls + 2;
        
        World res = wf.getWorld(ng.getUniqueRandomName(4, 10), startName);
        
        
        //Aggiunge i livelli e un posto per ogni livello (altrimenti non potrei avere un grafo connesso)
        for (int i = 0; i < lvls; i++)
        {
            res.addLevel(wf.getLevel(i, String.format("lvl%d", i), ""));
            res.addPlace(wf.getPlace(ng.getUniqueRandomName(4, 8), "", false, i));
        }
        
        //Aggiunge il posto iniziale
        res.addPlace(wf.getPlace(startName, "", false, 0));
        
        //Aggiunge altri posti
        for (int pl = places - (2 + lvls); pl > 0; pl--)
        {
            res.addPlace(wf.getPlace(ng.getUniqueRandomName(4, 8), "", false, boundRnd(0, lvls)));
        }
        
        
        String goalName = ng.getUniqueRandomName(4, 8);
        
        //Aggiunge il posto goal
        res.addPlace(wf.getPlace(goalName, "", true, boundRnd(0, lvls)));
        
        //Aggiunge link fino ad ottenere un grafo connesso
        while (!connected(res))
        {
            //Per creare un grafo minimo, connette solo posti non già connessi
            PlacePair pair = pickRandomUnconnectedPair(res);
            
            connect(ng, res, pair.p1, pair.p2);
        }
        
        
        
        
        //Evita divby0
        if (difficulty == 0)
            difficulty = 1;
        
        int trials = r.nextInt(((100 * places * lvls)/difficulty)+1);
        
        
        //Aggiunge altri collegamenti tra nodi, in numero inversamente proporzionale alla difficoltà
        for (int i = 0; i < trials; i++)
        {
            PlacePair pair = pickPair(res);
            
            if (canBeDirectlyConnected(res, res.getPlace(pair.p1), res.getPlace(pair.p2)))
            {
                connect(ng, res, pair.p1, pair.p2);
            }
        }
        
        return res;
    }
    
    public void test ()
    {
        World w = new World("prova", "start");
        
        Place start = new Place("start", "", false, 0);
        Place p2 = new Place("p2", "", false, 0);
        
        w.addLevel(new Level(0, "", ""));
        
        w.addPlace(start);
        w.addPlace(p2);
        
        connect(ng, w, "start", "p2");
        
        if (!directlyConnected(w, start, p2))
            throw new IllegalAccessError("Errore. Dovrebbero essere connessi!");
    }
}
