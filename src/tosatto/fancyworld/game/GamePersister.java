/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.simpleframework.xml.core.Persister;
import tosatto.fancyworld.game.world.World;



/**
 * Rappresenta un oggetto in grado di salvare una partita
 * @author Davide
 */
public class GamePersister{

    //Serve per fare sapere alla libreria di caricamento da file xml che tipo esatto
    //di oggetto attendersi.
    private Class<?> gameClass;
    private String saveName;
    
    public final static String SAVE_DIRECTORY = "saves";
    
    public GamePersister(Class<?>gameClass, String saveName) {
        this.gameClass = gameClass;
        this.saveName = saveName;
    }
    
    /**
     * Calcola il nome del file di salvataggio
     * @param saveName
     * @return 
     */
    private String computeFileName (String saveName)
    {
        Path path = Paths.get(SAVE_DIRECTORY, saveName + ".xml");
        
        return path.toString();
    }
    
    /**
     * Salva un Game
     * @param g
     * @throws Exception 
     */
    public void save(Game g) throws Exception
    {
        Persister p = new Persister();
        
        p.write(g, new File(computeFileName(saveName)));
    }
    
    /**
     * Carica un Game
     * @return
     * @throws Exception 
     */
    public Game load() throws Exception
    {
        Persister p = new Persister();
        
        Game g = (Game)p.read(gameClass, new File(computeFileName(saveName)));
        
        return g;
    }
    
    /**
     * Dice se esiste un salvataggio per il nome indicato nel costruttore
     * @return 
     */
    public boolean existsFile()
    {
        File f = new File(computeFileName(saveName));
        
        return f.exists();
    }
    
}
