/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions;

import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.world.passages.Passage;

/**
 *
 * @author Davide
 */
public interface MainInteraction {
    public boolean interact (MessageIO io, PassageInteraction passInt, Game g);
}
