/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.checkers;

import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.info.BundledGameInfo;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.main.interactions.bundles.BaseBundleInteraction;

/**
 * Determina la vittoria del giocatore in base ai punti accumulati e al suo posizionamento nel luogo goal
 * @author Davide
 */
public class PointBasedWinChecker implements WinChecker{

    @Override
    public boolean wins(Game g) {
        BaseGame bg = (BaseGame)g;
        
        BundledWorld bw = (BundledWorld)bg.getWorld();
        
        PointedPlayer pp = (PointedPlayer)bg.getPlayer();
        
        if (bw.getPlace(pp.getPlace()).isGoal() && pp.getPoints() >= pointsToWin(g))
        {
            return true;
        }
        
        return false;
    }

    @Override
    public int pointsToWin(Game g) {
        BaseGame bg = (BaseGame)g;
        
        BundledWorld bw = (BundledWorld)bg.getWorld();
        
        BundledGameInfo bgi = (BundledGameInfo)bw.getGi();
        
        return bgi.getPointsToWin();

    }
    
    
    
}
