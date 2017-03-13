/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

/**
 *
 * @author davide
 */
public interface KeyedGameInfo extends GameInfo{
    
    public boolean playerHasKey (String keyName);
    
}
