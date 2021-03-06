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
 * Rappresenta una prova di tipo sequenza, ossia in cui il giocatore deve indovinare
 * il prossimo numero in una sequenza numerica
 * @author Davide
 */
@Root(name = "SequenceTrial")
public class SequenceTrial implements Trial{
    
    private Random r;
    private int currentNum;
    private int prevNum;
    private int sum;
    private int mul;
    private int prevCoeff;
    
    @Attribute
    private int value = 15;

    public SequenceTrial() {
    
       this.init();
    
    }
    
    
    
    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Sequence";
    }
    
    /**
     * Inizializza la prova
     */
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
    
    /**
     * Ritorna il prossimo numero nella sequenza
     * 
     * Postcondizione:
     *  - l'oggetto viene predisposto per fare in modo che alla prossima chiamata di next
     *    si ritorni il numero successivo a quello ritornato nella sequenza
     * 
     * @return 
     */
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

    @Override
    public void setValue(int value) {
        this.value = value;
    }
    
    
}
