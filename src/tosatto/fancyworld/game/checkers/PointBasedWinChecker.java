/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.checkers;

import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.main.bundles.StandardBundleInteraction;

/**
 *
 * @author Davide
 */
public class PointBasedWinChecker implements WinChecker{

    @Override
    public boolean wins(Game g) {
        BaseGame bg = (BaseGame)g;
        
        BundledWorld bw = (BundledWorld)bg.getWorld();
        
        PointedPlayer pp = (PointedPlayer)bg.getPlayer();
        
        if (bw.getPlace(pp.getPlace()).isGoal() && pp.getPoints() >= bw.getBundle().getParameter(StandardBundleInteraction.GOAL_POINTS))
        {
            return true;
        }
        
        return false;
    }

    @Override
    public int pointsToWin(Game g) {
        BaseGame bg = (BaseGame)g;
        
        BundledWorld bw = (BundledWorld)bg.getWorld();
        
        return bw.getBundle().getParameter(StandardBundleInteraction.GOAL_POINTS);

    }
    
    
    
}
