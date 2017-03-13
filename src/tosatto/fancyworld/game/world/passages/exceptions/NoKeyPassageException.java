/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.passages.exceptions;

/**
 *
 * @author davide
 */
public class NoKeyPassageException extends PassageException{
    
    public NoKeyPassageException(String message) {
        super(message);
    }

    public NoKeyPassageException() {
        super("You do not have the key required to pass");
    }
    
    
    
}
