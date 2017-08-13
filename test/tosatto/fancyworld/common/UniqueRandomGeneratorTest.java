/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.common;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide
 */
public class UniqueRandomGeneratorTest {
    
    public UniqueRandomGeneratorTest() {
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
     * Test of getUniqueInt method, of class UniqueRandomGenerator.
     */
    @Test
    public void testGetUniqueInt() {
        UniqueRandomGenerator instance = new UniqueRandomGenerator();
        
        List<Integer> extracted = new ArrayList<>();
        
        for (int i = 0; i < 1000; i++)
        {
            int extr = instance.getUniqueInt(0, 1000);
            
            if (extracted.contains(extr))
                fail();
            else
                extracted.add(extr);
        }
    }
    
}
