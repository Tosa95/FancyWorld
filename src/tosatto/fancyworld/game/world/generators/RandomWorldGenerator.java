/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.Random;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;

/**
 *
 * @author davide
 */
public interface RandomWorldGenerator {
    public World generate();
    
    public Random getRandom();
    public NameGenerator getNameGenerator();
    public WorldFactory getWorldFactory ();
}
