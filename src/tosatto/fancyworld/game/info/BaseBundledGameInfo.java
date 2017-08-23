/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.info;

import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.world.BundledWorld;

/**
 * Implementa l'interfaccia BundledGameInfo e ne implementa tutti i metodi 
 * con accesso diretto al mondo di gioco
 * @author Davide
 */
public class BaseBundledGameInfo extends BaseTrialedGameInfo implements BundledGameInfo{
    
    /**
     * Inizizza l'oggetto
     * 
     * Precondizione: 
     *  - g deve possedere un mondo di tipo BundledWorld
     * 
     * @param g 
     */
    public BaseBundledGameInfo(BaseGame g) {
        super(g);
    }

    @Override
    public int getParameterValue(String parameterName) {
        BundledWorld bw = (BundledWorld)g.getWorld();
        
        return bw.getBundle().getParameter(parameterName);
    }
    
}
