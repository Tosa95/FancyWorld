/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.world.KeyedWorld;
import tosatto.fancyworld.game.world.factories.KeyedWorldFactory;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;

/**
 *
 * @author Davide
 */
public class GamePersisterTest {
    
    private BaseGame g;
    private GamePersister gp;
    
    public GamePersisterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         BasicRandomWorldGenerator brwg = new BasicRandomWorldGenerator(new Random(101274981236489263L), new KeyedWorldFactory(), 5, 10, 10, 20, 1);
        
        KeyedRandomWorldGenerator krwg = new KeyedRandomWorldGenerator(brwg, 10, 15, 0.3, 1);

        KeyedPlayer p = new KeyedPlayer("Granli Brum");
        KeyedWorld w = (KeyedWorld)krwg.generate();
        
        p.setPlace(w.getStartPlace());
        
        g = new BaseGame(p, w, "Game");
        gp = new GamePersister("prova.xml", BaseGame.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class GamePersister.
     */
    @Test
    public void testSaveAndLoad() throws Exception {
        gp.save(g);
        Game g2 = gp.load();
        
        System.out.println(g2.getWorld().toString());
    }
}
