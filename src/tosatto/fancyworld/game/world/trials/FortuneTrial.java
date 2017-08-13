/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.util.Random;
import org.simpleframework.xml.Root;

/**
 *
 * @author Davide
 */
@Root(name = "FortuneTrial")
public class FortuneTrial implements Trial{

    @Override
    public int getValue() {
        return 2;
    }

    @Override
    public String getType() {
        return "Fortune";
    }
    
    public int[] extract ()
    {
        Random r = new Random();
        
        int n1 = r.nextInt(7) + 1;
        int n2 = r.nextInt(7) + 1;
        
        return new int[]{n1, n2};
    }
    
}
