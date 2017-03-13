/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world.factories;

import tosatto.fancyworld.world.World;
import tosatto.fancyworld.world.levels.Level;
import tosatto.fancyworld.world.passages.OpenPassage;
import tosatto.fancyworld.world.passages.Passage;
import tosatto.fancyworld.world.places.Place;

/**
 *
 * @author davide
 */
public class BasicWorldFactory implements WorldFactory{

    @Override
    public World getWorld(String name, String startPlace) {
        return new World(name, startPlace);
    }

    @Override
    public Level getLevel(int id, String name, String description) {
        return new Level (id, name, description);
    }

    @Override
    public Passage getPassage(String name, String p1, String p2) {
        return new OpenPassage(name, p1, p2);
    }

    @Override
    public Place getPlace(String name, String description, boolean goal, int level) {
        return new Place(name, description, goal, level);
    }
    
}
