/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.player;

import org.simpleframework.xml.Attribute;

/**
 *
 * @author Davide
 */
public class PointedPlayer extends KeyedPlayer {
    
    @Attribute(name = "points")
    private int points;
    
    public static int MINIMUM_POINTS = 0;
    
    public PointedPlayer(String name) {
        super(name);
        points = 0;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void applyDeltaPoints (int delta)
    {
        points += delta;
        
        if (points < MINIMUM_POINTS)
            points = MINIMUM_POINTS;
    }
}
