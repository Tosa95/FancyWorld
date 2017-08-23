/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.info;

import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.player.KeyedPlayer;
import tosatto.fancyworld.game.world.KeyedWorld;

/**
 * Implementa l'interfaccia TrialedGameInfo e ne implementa tutti i metodi 
 * con accesso diretto al mondo di gioco
 * @author Davide
 */
public class BaseTrialedGameInfo implements TrialedGameInfo{

    protected BaseGame g;
    private KeyedPlayer kp;
    private KeyedWorld kw;
    
    /**
     * Inizializza l'oggetto
     * 
     * Precondizione:
     *  - g deve possedere un mondo di tipo KeyedWorld
     *  - g deve possedere un giocatore di tipo KeyedPlayer
     * 
     * @param g 
     */
    public BaseTrialedGameInfo (BaseGame g)
    {
        this.g = g;
        
        kp = (KeyedPlayer)g.getPlayer();
        kw = (KeyedWorld)g.getWorld();
    }
    
    @Override
    public boolean playerHasKey(String keyName) {
        return kp.hasKey(keyName);
    }

    @Override
    public int getKeyWeight(String keyName) {
        return kw.getKey(keyName).getWeight();
    }
    
}
