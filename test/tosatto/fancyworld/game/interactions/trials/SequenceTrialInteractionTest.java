/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tosatto.fancyworld.IO.ConsoleMessageIO;
import tosatto.fancyworld.game.world.trials.SequenceTrial;

/**
 *
 * @author Davide
 */
public class SequenceTrialInteractionTest {
    
    public SequenceTrialInteractionTest() {
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
    public void testInteraction() {
        
        SequenceTrialInteraction sti = new SequenceTrialInteraction();
        
        sti.interact(new SequenceTrial(), new ConsoleMessageIO());
        
        
        
    }
    
}
