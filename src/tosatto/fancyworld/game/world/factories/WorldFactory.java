/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.factories;

import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.levels.Level;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Interfaccia definita con lo scopo di implementare un pattern AbstractFactory 
 * in modo da permettere ai generatori automatici di mondi di istanziare livelli,
 * luoghi e passaggi della tipologia corretta.
 * 
 * @author davide
 */
public interface WorldFactory {
    
    /**
     * Ritorna un nuovo mondo vuoto
     * @param name Nome del nuovo mondo
     * @param startPlace Nome del posto iniziale
     * @return 
     */
    public World getWorld (String name, String startPlace);
    
    /**
     * Ritorna un nuovo livello
     * @param id Identificativo numerico del livello
     * @param name Nome del livello
     * @param description Descrizione del livello
     * @return 
     */
    public Level getLevel (int id, String name, String description);
    
    /**
     * Ritorna un nuovo luogo
     * @param name Nome del luogo
     * @param description Descrizione del luogo
     * @param goal True: Il luogo è il luogo goal; False: Il luogo non è il luogo goal
     * @param level Identificativo del livello a cui il luogo appartiene
     * @return 
     */
    public Place getPlace (String name, String description, boolean goal, int level);
    
    /**
     * Ritorna un nuovo passaggio
     * @param name Nome del passaggio
     * @param p1 Nome del primo luogo
     * @param p2 Nome del secondo luogo
     * @return 
     */
    public Passage getPassage (String name, String p1, String p2);
    
}
