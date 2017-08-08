/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.ClosedPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.NoKeyPassageException;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Directions;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class KeyedMainInteraction implements MainInteraction{

    @Override
    public boolean interact(MessageIO io, PassageInteraction passInt, Game g) {
        Place p = g.getWorld().getPlace(g.getPlayer().getPlace());
        
        List<String> menuAlt = new ArrayList<>();
        
        for (String dir : Directions.DIRECTIONS)
        {
            Passage pass = g.getWorld().getPassage(p.getPassage(dir));
            String nxt;
            try {
                nxt ="[" + pass.next(p.getName()) + "]"; 
            } catch (ClosedPassageException ex) {
                nxt = "[X]";
            } catch (NoKeyPassageException ex) {
                KeyedPassage kp =(KeyedPassage)pass;
                
                nxt = String.format("[K: %s]",kp.requiredKey());
            } catch (PassageException ex){
                nxt = "[?]";
            }
            
            menuAlt.add(String.format("%12s\t%s", dir, nxt));
        }
        
        menuAlt.add(String.format("%12s", "esci"));
        
        int dir = io.showMenu("Cosa si desidera fare?", menuAlt.toArray(new String[menuAlt.size()]));
        
        if (dir == Directions.DIRECTIONS.length)
            return false;
        
        try {
            passInt.interact(io, g, g.getWorld().getPassage(p.getPassage(Directions.DIRECTIONS[dir])));
            g.getPlayer().setPlace(g.getWorld().getPassage(p.getPassage(Directions.DIRECTIONS[dir])).next(p.getName()));
            io.inform("Ok, passaggio effettuato");
        } catch (ClosedPassageException ex) {
            io.inform("Passaggio murato");
        } catch (NoKeyPassageException ex) {
            io.inform("Passaggio chiuso a chiave");
        } catch (PassageException ex){
            io.inform(ex.toString());
        }
        
        return true;
    }
    
}
