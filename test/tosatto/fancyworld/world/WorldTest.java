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
        instance = new World("prova", "Buio");
        
        Place p1 = new Place("Buio", "Luogo deprimente", false, 0);
        Place p2 = new Place("Luce", "Luogo felice", true, 0);
        Place p3 = new Place("Luce", "Luogo felice", true, 101010);
        
        p1.addPassage(Directions.WEST, new OpenPassage("P1", "Luce"));
        p2.addPassage(Directions.EST, new OpenPassage("P1", "Buio"));
        
        instance.addLevel(new Level(0, "In principio", ""));
        
        instance.addPlace(p1);
        instance.addPlace(p2);
        
        try{
            instance.addPlace(p3);
            fail();
        }catch (IllegalArgumentException e)
        {
            
        }
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPlace method, of class World.
     */
    @Test
    public void testSaveAndLoad() throws Exception {
        Persister p = new Persister();
        
        p.write(instance, new File(instance.getName() + ".xml"));
        
        System.out.println(instance.toString());
        
        World w = p.read(World.class, new File(instance.getName() + ".xml"));
        
        System.out.println(w.toString());
    }
    
}
