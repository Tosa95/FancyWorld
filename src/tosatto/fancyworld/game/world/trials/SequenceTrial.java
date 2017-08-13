/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

import java.util.Random;

/**
 *
 * @author Davide
 */
public class SequenceTrial implements Trial{
    
    private Random r;
    private int currentNum;
    private int prevNum;
    private int sum;
    private int mul;
    private int prevCoeff;

    public SequenceTrial() {
    
       this.init();
    
    }
    
    
    
    @Override
    public int getValue() {
        return 25;
    }

    @Override
    public String getType() {
        return "Sequence";
    }
    
    public void init()
    {
        r = new Random();
        currentNum = 1;
        prevNum = 1;

        sum = r.nextInt(11) - 5;
        
        do{
            mul = r.nextInt(5) - 2;
        }while(getMul()==0);
        
        prevCoeff = r.nextInt(2);
    }
    
    public int next()
    {
        int res = currentNum;
        
        currentNum = (currentNum * getMul())+getSum() + getPrevCoeff()*prevNum;
        
        prevNum = res;
        
        return res;
    }

    /**
     * @return the sum
     */
    public int getSum() {
        return sum;
    }

    /**
     * @return the mul
     */
    public int getMul() {
        return mul;
    }

    /**
     * @return the prevCoeff
     */
    public int getPrevCoeff() {
        return prevCoeff;
    }
    
    
}