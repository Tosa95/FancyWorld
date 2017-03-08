/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.player.Player;
import tosatto.fancyworld.world.World;

/**
 * Classe Game.
 * 
 * Fa da contesto per tutta l'applicazione. Viene passata in modo ramificato a
 * tutti i componenti del mondo e a player, in modo che tutti possano avere 
 * informazioni sul mondo e sul player (utile, per esempio, per fare il controllo
 * delle chiavi in maniera "pulita" all'interno dei passaggi con chiave).
 * 
 * E' inioltre la classe per il salvataggio delle partite in quanto contiene tutto
 * lo stato del'applicazione.
 * 
 * Contiene la logica del gioco.
 * 
 * @author Davide
 */
@Root (name = "Game")
public class Game {
    
    @Element (name = "player")
    private Player player;
    
    @Element (name = "world")
    private World world;

    public Game(@Element (name = "player") Player player,
                @Element (name = "world") World world) 
    {
        player.setGm(this);
        this.player = player;
        
        world.setGm(this);
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
    
    
}
