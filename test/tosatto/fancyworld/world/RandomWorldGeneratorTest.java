/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.world;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tosatto.fancyworld.world.factories.BasicWorldFactory;
import tosatto.fancyworld.world.places.Place;

/**
 *
 * @author Davide
 */
public class RandomWorldGeneratorTest {
    
    public RandomWorldGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of test method, of class RandomWorldGenerator.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        new RandomWorldGenerator(new BasicWorldFactory()).test();
    }
    
}
