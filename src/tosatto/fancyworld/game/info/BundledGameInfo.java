/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.info;

/**
 * Permette alle entità facenti parte del gioco di accedere ai parametri definiti
 * nel bundle del mondo
 * @author Davide
 */
public interface BundledGameInfo extends TrialedGameInfo{
    
    /**
     * Ritorna il valore di un parametro.
     * 
     * Precondizione:
     *  - parameterName deve identificare un parametro facente parte del bundle associato al mondo
     * 
     * @param parameterName
     * @return 
     */
    public int getParameterValue (String parameterName);
    
}
