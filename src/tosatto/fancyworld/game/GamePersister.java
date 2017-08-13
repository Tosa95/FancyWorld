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
 *
 * @author Davide
 */
public class GamePersister{

    private Class<?> gameClass;
    private String saveName;
    
    public final static String SAVE_DIRECTORY = "saves";
    
    public GamePersister(Class<?>gameClass, String saveName) {
        this.gameClass = gameClass;
    }
    
    private String computeFileName (String saveName)
    {
        Path path = Paths.get(SAVE_DIRECTORY, saveName + ".xml");
        
        return path.toString();
    }
    
    public void save(Game g) throws Exception
    {
        Persister p = new Persister();
        
        p.write(g, new File(computeFileName(saveName)));
    }
    
    public Game load() throws Exception
    {
        Persister p = new Persister();
        
        Game g = (Game)p.read(gameClass, new File(computeFileName(saveName)));
        
        return g;
    }
    
    public boolean existsFile()
    {
        File f = new File(computeFileName(saveName));
        
        return f.exists();
    }
    
}
