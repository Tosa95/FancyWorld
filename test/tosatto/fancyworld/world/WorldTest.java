/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simpleframework.xml.core.Persister;
import tosatto.fancyworld.game.world.factories.BasicWorldFactory;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.OpenPassage;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class WorldTest {
    
    public WorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    World instance;
    
    @Before
    public void setUp() {
        instance = new BasicRandomWorldGenerator(new BasicWorldFactory(), 5, 8, 10, 27, 1).generate();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        Persister p = new Persister();
        
        p.write(instance, new File(instance.getName() + ".xml"));
        
        System.out.println(instance.toString());
        
        World w = p.read(World.class, new File(instance.getName() + ".xml"));
        
        System.out.println(w.toString());
    }
    
}
