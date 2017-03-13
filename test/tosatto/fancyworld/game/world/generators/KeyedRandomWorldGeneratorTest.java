/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.NameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.KeyedWorldFactory;
import tosatto.fancyworld.game.world.factories.WorldFactory;

/**
 *
 * @author davide
 */
public class KeyedRandomWorldGeneratorTest {
    
    public KeyedRandomWorldGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    KeyedWorld kw;
    
    @Before
    public void setUp() {
        
        BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new KeyedWorldFactory(), 5, 10, 10, 20, 1);
        
        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.7, 0.2);
        
        kw = (KeyedWorld)krwg.generate();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generate method, of class KeyedRandomWorldGenerator.
     */
    @Test
    public void testGenerate() {
        System.out.println("generate");
        KeyedRandomWorldGenerator instance = null;
        World expResult = null;
        World result = instance.generate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandom method, of class KeyedRandomWorldGenerator.
     */
    @Test
    public void testGetRandom() {
        System.out.println("getRandom");
        KeyedRandomWorldGenerator instance = null;
        Random expResult = null;
        Random result = instance.getRandom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameGenerator method, of class KeyedRandomWorldGenerator.
     */
    @Test
    public void testGetNameGenerator() {
        System.out.println("getNameGenerator");
        KeyedRandomWorldGenerator instance = null;
        NameGenerator expResult = null;
        NameGenerator result = instance.getNameGenerator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorldFactory method, of class KeyedRandomWorldGenerator.
     */
    @Test
    public void testGetWorldFactory() {
        System.out.println("getWorldFactory");
        KeyedRandomWorldGenerator instance = null;
        WorldFactory expResult = null;
        WorldFactory result = instance.getWorldFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
