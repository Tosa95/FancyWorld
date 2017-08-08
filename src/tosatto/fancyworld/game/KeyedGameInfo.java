package tosatto.fancyworld.game;

/**
 * Serve per permettere a un passaggio di sapere se un giocatore ha le chiavi per passare
 * @author davide
 */
public interface KeyedGameInfo extends GameInfo{
    
    public boolean playerHasKey (String keyName);
    public int getKeyWeight (String keyName);
    
}
