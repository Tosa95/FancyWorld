package tosatto.fancyworld.game.info;

/**
 * Serve per permettere alle entità facenti parte del gioco di avere informazioni
 * sulle chiavi possedute dal giocatore
 * @author davide
 */
public interface KeyedGameInfo extends GameInfo{
    
    /**
     * Dice se un giocatore possiede una chiave
     * @param keyName
     * @return 
     */
    public boolean playerHasKey (String keyName);
    
    /**
     * Ritorna il peso di una chiave
     * 
     * Precondizione:
     *  - keyName identifica una chiave facente parte del mondo
     * 
     * @param keyName
     * @return 
     */
    public int getKeyWeight (String keyName);
    
}
