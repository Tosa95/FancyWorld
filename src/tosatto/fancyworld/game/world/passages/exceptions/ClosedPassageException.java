/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages.exceptions;

/**
 *
 * @author Davide
 */
public class ClosedPassageException extends PassageException{

    public ClosedPassageException() {
        super("Passage is closed. You cannot pass.");
    }
    
    
    
}
