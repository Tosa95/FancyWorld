/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simpleframework.xml.core.Persister;
import tosatto.fancyworld.levels.Level;
import tosatto.fancyworld.passages.OpenPassage;
import tosatto.fancyworld.places.Directions;
import tosatto.fancyworld.places.Place;

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
        instance = RandomWorldGenerator.generate(5, 8, 10, 27);
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
