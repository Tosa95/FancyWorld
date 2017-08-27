/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.names;

/**
 *
 * @author Davide
 */
public interface NameGenerator {
    
    public String getRandomName (int lettersMin, int lettersMax);
    public String getUniqueRandomName (int lettersMin, int lettersMax);
    
}
