/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world.factories;

import tosatto.fancyworld.world.World;
import tosatto.fancyworld.world.levels.Level;
import tosatto.fancyworld.world.passages.Passage;
import tosatto.fancyworld.world.places.Place;

/**
 *
 * @author davide
 */
public interface WorldFactory {
    
    public World getWorld (String name, String startPlace);
    public Level getLevel (int id, String name, String description);
    public Place getPlace (String name, String description, boolean goal, int level);
    public Passage getPassage (String name, String p1, String p2);
    
}
