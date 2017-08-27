/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators;

import java.util.Random;
import tosatto.fancyworld.game.world.generators.names.BasicNameGenerator;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.WorldFactory;
import tosatto.fancyworld.game.world.generators.names.NameGenerator;

/**
 * Interfaccia che rappresenta un oggetto in grado di generare un mondo
 * @author davide
 */
public interface RandomWorldGenerator {
    
    /**
     * Ritorna un nuovo mondo
     * @return 
     */
    public World generate();
    
    /**
     * Ritorna l'oggetto random utilizzato per la generazione casuale di numeri
     * @return 
     */
    public Random getRandom();
    
    /**
     * Ritorna l'oggetto utilizzato per generare i nomi
     * @return 
     */
    public NameGenerator getNameGenerator();
    
    /**
     * Ritorna la factory utilizzata per la creazione di tutti gli oggetti costituenti il mondo
     * @return 
     */
    public WorldFactory getWorldFactory ();
}
