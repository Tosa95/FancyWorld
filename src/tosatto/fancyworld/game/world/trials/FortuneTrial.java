/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.util.Random;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 *
 * @author Davide
 */
@Root(name = "FortuneTrial")
public class FortuneTrial implements Trial{

    @Attribute
    private int value = 2;
    
    @Override
    public int getValue() {
        return value;
    }

    private Random r;
    
    public FortuneTrial() {
        
        r = new Random();
    }
    
    
    

    @Override
    public String getType() {
        return "Fortune";
    }
    
    public int[] extract ()
    {
        int n1 = r.nextInt(6) + 1;
        int n2 = r.nextInt(6) + 1;
        
        return new int[]{n1, n2};
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
    
}
