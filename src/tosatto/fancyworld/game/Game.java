/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.world.World;

/**
 * 
 * Classe base di Game. Generaslizza tutte le possibili partite. Ha solo un nome,
 * un giocatore e un mondo
 * 
 * @author Davide
 */
public abstract class Game {
     public abstract String getName();
     public abstract Player getPlayer();
     public abstract World getWorld();
     public abstract void play();
     public abstract void setGameInfo(GameInfo gi);
}
