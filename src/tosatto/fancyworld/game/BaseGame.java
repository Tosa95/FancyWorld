/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.interactions.MainInteraction;
import tosatto.fancyworld.game.interactions.PassageInteraction;
import tosatto.fancyworld.game.interactions.places.PlaceIntercation;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.ClosedPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.world.World;

/**
 * Classe Game.
 * 
 * Fa da contesto per tutta l'applicazione. Viene passata in modo ramificato a
 * tutti i componenti del mondo e a player, in modo che tutti possano avere 
 * informazioni sul mondo e sul player (utile, per esempio, per fare il controllo
 * delle chiavi in maniera "pulita" all'interno dei passaggi con chiave).
 * 
 * 
 * E' inioltre la classe per il salvataggio delle partite in quanto contiene tutto
 * lo stato del'applicazione.
 * 
 * Contiene la logica del gioco.
 * 
 * @author Davide
 */
@Root (name = "Game")
public class BaseGame extends Game{
    
    @Element (name = "player")
    private Player player;
    
    @Element (name = "world")
    private World world;

    @Attribute (name = "name")
    private String name;
    
    
    
    private PassageInteraction passInt;
    
    private PlaceIntercation placeInt;
    
    private MainInteraction mainInt;
    
    private MessageIO io;
    
    public BaseGame(@Element (name = "player") Player player,
                @Element (name = "world") World world,
                @Attribute (name = "name") String name) 
    {
        this.player = player;
        this.world = world;
        this.name = name;
    }

    public void setIo(MessageIO io) {
        this.io = io;
    }
    
    public void setInteractions (PassageInteraction passInt, PlaceIntercation placeInt, MainInteraction mainInt)
    {
        this.passInt = passInt;
        this.placeInt = placeInt;
        this.mainInt = mainInt;
    }
    
    private boolean exit (int choice)
    {
        return choice == Directions.DIRECTIONS.length;
    }
    
    @Override
    public void play()
    {
        io.inform("Welcome to Fancy World");
        
        io.inform("Il mondo è generato casualmente, come lo sono i nomi dei posti!");
        
        
        
        io.inform(String.format("Benvenuto. Ti trovi nel luogo %s. Devi "
                + "raggiungere un luogo che si trova al livello %d", player.getPlace(), world.getEndLevelIndex()));
        
        String[] choices = ArrayUtils.addAll(Directions.DIRECTIONS, new String[]{"esci"});
        
        String prevPlace = null;
        boolean exit = false;
        
        while (! world.getPlace(player.getPlace()).isGoal() && !exit)
        {
            if (prevPlace == null || !player.getPlace().equals(prevPlace))
                placeInt.interact(io, this, world.getPlace(player.getPlace()));
            
            prevPlace = player.getPlace();
            
            exit = !mainInt.interact(io, passInt, this);
            
            
            
            
        }
        
        if (exit)
        {
            io.inform("OK, MI CHIUDO. MA MI DELUDI, MAI ARRENDERSI!!!");
            int resp = io.ask("Anzi, aspetta... Prima di andartene almeno vuoi salvare?", new String[]{"si", "no"});
            
            if (resp == 0)
            {
                GamePersister gp = new GamePersister(GamePersister.SAVE_FILENAME_TEMPORARY, BaseGame.class);
                
                try {
                    gp.save(this);
                } catch (Exception ex) {
                    Logger.getLogger(BaseGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                io.inform("Allora non ti importa proprio nulla di me :(");
                io.inform("Non sono stato un bravo giochino???");
                io.inform("Vabbè allora mi dileguo. Buon proseguimanto :D");
            }
                    
        } else {
            io.inform(String.format("Sei nel luogo goal: %s. HAI VINTO!!!", player.getPlace()));
        }
        
        
        
    }
    
    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public String getName() {
        return name;
    }

    public void setGameInfo(GameInfo gi) {
        world.setGi(gi);
        player.setGi(gi);
    }
    
    
    
    public class BaseGameInfo implements GameInfo
    {
        
    }
    
}
