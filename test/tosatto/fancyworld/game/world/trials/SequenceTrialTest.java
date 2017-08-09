/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

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
public class SequenceTrialTest {
    
    public SequenceTrialTest() {
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

    @Test
    public void testNext() {
        SequenceTrial instance = new SequenceTrial();
        
        instance.init();
        
        for (int i = 0; i < 6; i++)
        {
            int a = instance.next();
            System.out.println(a);
        }
        
        System.out.println(String.format("mul: %d, sum: %d, prevCoeff: %d", instance.getMul(), instance.getSum(), instance.getPrevCoeff()));
    }
    
}
