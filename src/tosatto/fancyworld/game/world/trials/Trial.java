/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.trials;

/**
 * Interfaccia che rappresenta una tipologia di prova
 * @author Davide
 */
public interface Trial {
    
    /**
     * Ritorna il valore della prova
     * @return 
     */
    public int getValue();
    
    /**
     * Imposta il valore della prova
     * @param value 
     */
    public void setValue(int value);
    
    /**
     * Ritorna il nome della tipologia di prova
     * @return 
     */
    public String getType();
}
