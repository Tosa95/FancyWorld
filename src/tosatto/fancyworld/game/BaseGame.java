/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import tosatto.fancyworld.game.info.GameInfo;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.checkers.WinChecker;
import tosatto.fancyworld.game.interactions.MainInteraction;
import tosatto.fancyworld.game.interactions.PassageInteraction;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.ClosedPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;
import tosatto.fancyworld.game.player.Player;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.interactions.places.PlaceInteraction;

/**
 * 
 * Continene l'intero stato dell'applicazione e, dato ciò, viene utilizzata come classe
 * per il salvataggio della partita.
 * 
 * Contiene inoltre la logica del gioco.
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
    
    private PlaceInteraction placeInt;
    
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
    
    /**
     * Imposta il nome della partita
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Imposta l'oggetto da usare come per eseguire operazioni di input - output
     * @param io 
     */
    public void setIo(MessageIO io) {
        this.io = io;
    }
    
    /**
     * Imposta gli oggetti da usare per gestire alcune interazioni con l'utente
     * @param passInt
     * @param placeInt
     * @param mainInt 
     */
    public void setInteractions (PassageInteraction passInt, PlaceInteraction placeInt, MainInteraction mainInt)
    {
        this.passInt = passInt;
        this.placeInt = placeInt;
        this.mainInt = mainInt;
    }
    
    /**
     * Controlla se la scelta dell'utente equivale ad uscire
     * @param choice
     * @return 
     */
    private boolean exit (int choice)
    {
        return choice == Directions.DIRECTIONS.length;
    }
    
    @Override
    public void play(WinChecker check)
    {
        io.inform("Welcome to Fancy World");
        
        io.inform("Il mondo è generato casualmente, come lo sono i nomi dei posti!");
        
        
        
        io.inform(String.format("Benvenuto. Ti trovi nel luogo %s. Devi "
                + "raggiungere un luogo che si trova al livello %d", player.getPlace(), world.getEndLevelIndex()));
        
        String[] choices = ArrayUtils.addAll(Directions.DIRECTIONS, new String[]{"esci"});
        
        String prevPlace = null;
        boolean exit = false;
        
        while (!exit)
        {   
            if (prevPlace == null || !player.getPlace().equals(prevPlace))
            {
                if (world.getPlace(player.getPlace()).isGoal())
                {
                    io.inform("Attenzione!!! Hai raggiunto il luogo goal!!!");
                    
                    if (!check.wins(this))
                    {
                        io.inform("Purtroppo hai solo " + ((PointedPlayer)player).getPoints() +
                                " punti, ma te ne servono " + check.pointsToWin(this) + " torna quando li avrai");
                        
                    }else{
                        break;
                    }
                }
                
                placeInt.interact(io, this, world.getPlace(player.getPlace()));
            }
            
            prevPlace = player.getPlace();
            
            exit = !mainInt.interact(io, passInt, this);
            
            
            
            
        }
        
        if (exit)
        {
            io.inform("OK, MI CHIUDO. MA MI DELUDI, MAI ARRENDERSI!!!");
            int resp = io.ask("Anzi, aspetta... Prima di andartene almeno vuoi salvare?", new String[]{"si", "no"});
            
            if (resp == 0)
            {
                GamePersister gp = new GamePersister(BaseGame.class, this.name);
                
                try {
                    gp.save(this);
                    io.inform("Salvataggio effettuato. Arrivederci!");
                } catch (Exception ex) {
                    Logger.getLogger(BaseGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                io.inform("Allora non ti importa proprio nulla di me :(");
                io.inform("Non sono stato un bravo giochino???");
                io.inform("Vabbè allora mi dileguo. Buon proseguimento :D");
            }
                    
        } else {
            io.inform(String.format("Gentile avventuriero, %s era il luogo da raggiungere"
                    + " e lo hai raggiunto con %d punti. HAI VINTO!!!", player.getPlace(), ((PointedPlayer)player).getPoints()));
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

    /**
     * Imposta (in modo ramificato) un oggetto di tipo GameInfo a tutti gli oggetti
     * facenti parte del gioco in modo che possano accedere ad alcune delle funzionalità
     * fornite dalla classe game e/o da altri componenti del gioco.
     * @param gi 
     */
    public void setGameInfo(GameInfo gi) {
        world.setGi(gi);
        player.setGi(gi);
    }
    
    
}
