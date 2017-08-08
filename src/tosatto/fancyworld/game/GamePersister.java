/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

import java.io.File;
import org.simpleframework.xml.core.Persister;
import tosatto.fancyworld.game.world.World;



/**
 *
 * @author Davide
 */
public class GamePersister{

    private String filename;
    private Class<?> gameClass;
    
    public final static String SAVE_FILENAME_TEMPORARY = "saves/save.xml";
    
    public GamePersister(String filename, Class<?>gameClass) {
        this.filename = filename;
        this.gameClass = gameClass;
    }
    
    public void save(Game g) throws Exception
    {
        Persister p = new Persister();
        
        p.write(g, new File(filename));
    }
    
    public Game load() throws Exception
    {
        Persister p = new Persister();
        
        Game g = (Game)p.read(gameClass, new File(filename));
        
        return g;
    }
    
    public boolean existsFile()
    {
        File f = new File(filename);
        
        return f.exists();
    }
    
}
