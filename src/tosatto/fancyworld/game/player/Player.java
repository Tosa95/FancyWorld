/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.player;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.info.GameInfo;

/**
 *
 * Rappresenta il giocatore
 * 
 * @author Davide
 */
@Root (name = "Player")
public class Player {
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "place")
    private String place;

    protected GameInfo gi;
    
    public Player(@Attribute (name = "name") String name) 
    {
        this.name = name;
    }

    /**
     * Imposta un oggetto GameInfo in modo da poter usufruire di aclune funzionalità
     * di Game e World senza un riferimento diretto (che creerebbe una dipendenza ciclica)
     * @param gi 
     */
    public void setGi(GameInfo gi) {
        this.gi = gi;
        setSubGi(gi);
    }

    /**
     * Se una sottoclasse fa l'override, il metodo sovrascritto verrà chiamato quando
     * dopo aver impostato il campo gi della classe base (utile perchè a volte le classi
     * derivate devono fare dei cast sull'oggetto GameInfo) [Template Method]
     * @param gi 
     */
    protected void setSubGi(GameInfo gi)
    {
        
    }
    
    /**
     * Imposta il nome del luogo corrente del giocatore
     * 
     * Precondizione:
     *  - Il luogo è effettivamente parte del mondo in cui si trova il giocatore
     * 
     * @param place 
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Ritorna il nome del luogo corrente del giocatore
     * @return 
     */
    public String getPlace() {
        return place;
    }

    /**
     * Ritorna il nome del giocatore
     * @return 
     */
    public String getName() {
        return name;
    }
    
}
