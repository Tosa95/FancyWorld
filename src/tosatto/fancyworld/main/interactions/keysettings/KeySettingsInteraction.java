/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.interactions.keysettings;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.World;

/**
 *
 * @author Davide
 */
public interface KeySettingsInteraction {
    
    public void interact(World w, MessageIO io);
    
}
