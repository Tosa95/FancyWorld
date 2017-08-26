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
import tosatto.fancyworld.game.world.generators.algorithms.PlaceConnectionChecker;
import tosatto.fancyworld.game.world.generators.algorithms.WorldConnectionChecker;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.OpenPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Genera la topologia base di un mondo, senza aggiungere ne chiavi ne prove
 * @author Davide
 */
public class BasicRandomWorldGenerator implements RandomWorldGenerator{
    
    private static final String[] dirsOnLevel = {Directions.EST, Directions.NORTH, Directions.SOUTH, Directions.WEST};
    private static final String[] dirsUD = {Directions.UP, Directions.DOWN};
    
    private Random r;
    private NameGenerator ng;
    private WorldFactory wf;

    private int minLvl, maxLvl, minPlaces, maxPlaces, difficulty;
    
    private PlaceConnectionChecker pcc;
    private WorldConnectionChecker wcc;
    
    /**
     * Inizializza il generatore.
     * 
     * @param r Oggetto Random usato per la generazione casuale di numeri
     * @param wf Factory usata per la creazione di ogni oggetto facente parte del mondo
     * @param minLvl Minimo numero di livelli
     * @param maxLvl Massimo numero di livelli
     * @param minPlaces Minimo numero di posti
     * @param maxPlaces Massimo numero di posti
     * @param difficulty Parametro che decide quanti collegamenti ci siano tra i posti. Più è elevato, meno collegamenti ci saranno
     */
    public BasicRandomWorldGenerator(Random r, 
                                WorldFactory wf,
                                int minLvl, 
                                int maxLvl, 
                                int minPlaces, 
                                int maxPlaces, 
                                int difficulty,
                                PlaceConnectionChecker pcc,
                                WorldConnectionChecker wcc) {
        
        this.r = r;
        ng = new NameGenerator(r);
        this.wf = wf;
        
        this.minLvl = minLvl;
        this.maxLvl = maxLvl;
        
        this.minPlaces = minPlaces;
        this.maxPlaces = maxPlaces;
        
        this.difficulty = difficulty;
        
        this.pcc = pcc;
        this.wcc = wcc;
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
     * @return the minLvl
     */
    public int getMinLvl() {
        return minLvl;
    }

    /**
     * @param minLvl the minLvl to set
     */
    public void setMinLvl(int minLvl) {
        this.minLvl = minLvl;
    }

    /**
     * @return the maxLvl
     */
    public int getMaxLvl() {
        return maxLvl;
    }

    /**
     * @param maxLvl the maxLvl to set
     */
    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    /**
     * @return the minPlaces
     */
    public int getMinPlaces() {
        return minPlaces;
    }

    /**
     * @param minPlaces the minPlaces to set
     */
    public void setMinPlaces(int minPlaces) {
        this.minPlaces = minPlaces;
    }

    /**
     * @return the maxPlaces
     */
    public int getMaxPlaces() {
        return maxPlaces;
    }

    /**
     * @param maxPlaces the maxPlaces to set
     */
    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    /**
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the pcc
     */
    public PlaceConnectionChecker getPcc() {
        return pcc;
    }

    /**
     * @param pcc the pcc to set
     */
    public void setPcc(PlaceConnectionChecker pcc) {
        this.pcc = pcc;
    }

    /**
     * @return the wcc
     */
    public WorldConnectionChecker getWcc() {
        return wcc;
    }

    /**
     * @param wcc the wcc to set
     */
    public void setWcc(WorldConnectionChecker wcc) {
        this.wcc = wcc;
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
        if (wcc.isConnected(w))
            //Se il grafo è connesso, non è possibile trovare una coppia di posti non connessi
            return null;
        
        while (true)
        {
            PlacePair res = pickPair(w);
            
            if (!pcc.arePlacesConnected(w, res.p1, res.p2) )
            {
                if (canBeDirectlyConnected(w, w.getPlace(res.p1), w.getPlace(res.p2)))
                {
                    return res;
                }
                    
            }
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
     * Connette due nodi.
     * 
     * Precondizione: i due nodi devono essere direttamente collegati
     * Postcondizione: i due nodi passati sono collegati
     * 
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
     * 
     * Postcondizione: il mondo ritornato è connesso, ossia presa una qualsiasi coppia di posti,
     * esiste un cammino che li unisce
     * 
     * @return Il mondo generato
     */
    public World generate()
    {
        final String startName = "start";
        
        //Sceglie un numero di livelli
        int lvls = boundRnd(getMinLvl(), getMaxLvl());

        //Sceglie un numero di posti
        int places = boundRnd(getMinPlaces(), getMaxPlaces());
        
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
        while (!wcc.isConnected(res))
        {
            //Per creare un grafo minimo, connette solo posti non già connessi
            PlacePair pair = pickRandomUnconnectedPair(res);
            
            connect(ng, res, pair.p1, pair.p2);
        }
        
        
        
        
        //Evita divby0
        if (getDifficulty() == 0)
            setDifficulty(1);
        
        int trials = r.nextInt(((100 * places * lvls)/getDifficulty())+1);
        
        
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
    
}
