/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.factories;

import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.places.KeyedPlace;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author davide
 */
public class KeyedWorldFactory implements WorldFactory{

    @Override
    public World getWorld(String name, String startPlace) {
        return new KeyedWorld(name, startPlace);
    }

    @Override
    public Level getLevel(int id, String name, String description) {
        return new Level(id, name, description);
    }

    @Override
    public Place getPlace(String name, String description, boolean goal, int level) {
        return new KeyedPlace(name, description, goal, level);
    }

    @Override
    public Passage getPassage(String name, String p1, String p2) {
        return new KeyedPassage(name, p1, p2);
    }
    
}
