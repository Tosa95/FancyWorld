/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.player;

import org.simpleframework.xml.Attribute;

/**
 * Rappresenta un giocatore dotato di punti.
 * 
 * Invariante:
 *  - il numero di punti è sempre >= 0
 * 
 * @author Davide
 */
public class PointedPlayer extends KeyedPlayer {
    
    @Attribute(name = "points")
    private int points;
    
    public static int MINIMUM_POINTS = 0;
    
    public PointedPlayer(@Attribute (name = "name") String name) {
        super(name);
        points = 0;
    }
    
    /**
     * Ritorna il numero di punti attualmente posseduti dal giocatore
     * @return 
     */
    public int getPoints()
    {
        return points;
    }
    
    /**
     * Imposta il numero di punti posseduti dal giocatore
     * @param pt 
     */
    public void setPoints(int pt)
    {
        this.points = pt;
    }
    
    /**
     * Aumenta i punti del giocatore di delta.
     * 
     * Nota: per diminuire i punti è sufficiente usare un delta negativo
     * 
     * @param delta 
     */
    public void applyDeltaPoints (int delta)
    {
        points += delta;
        
        if (points < MINIMUM_POINTS)
            points = MINIMUM_POINTS;
    }
}
