/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.algorithms;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class BaseWorldTraveler implements PlaceConnectionChecker, WorldConnectionChecker, GoalReachableChecker, PlaceIterator{

    /**
     * Dice se il mondo passato è connesso
     * @param w
     * @return 
     */
    @Override
    public boolean isConnected(World w) {
        
        /*
        * ATTENZIONE: il metodo qua utilizzato per verificare la connessione del mondo è altamente
        * inefficiente. Si è a conoscenza del problema, ma non si è considerato di primaria importanza
        * risolverlo
        */
        
        Place[] places = w.getPlaces().toArray(new Place[w.getPlaces().size()]);
        
        int pNum = places.length;
        
        for (int i = 0; i < pNum; i++)
        {
            for (int j = 0; j < pNum; j++)
            {
                if (! arePlacesConnected(w, places[i].getName(), places[j].getName()))
                {
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * Dice se il luogo goal è raggiungibile
     * @param w
     * @return 
     */
    @Override
    public boolean isGoalReachable(World w) {
        
        String goal = "";
        
        for (Place p: w.getPlaces())
        {
            if (p.isGoal())
            {
                goal = p.getName();
                break;
            }
        }
        
        
        
        return arePlacesConnected(w, w.getStartPlace(),goal);
    }

    /**
     * Permette di specificare un'azione da compiere su tutti i posti raggiungibili di un mondo
     * @param w
     * @param v L'azione da compiere
     */
    @Override
    public void visitAllReachablePlaces(World w, PlaceVisitor v) {
        
        Place[] places = w.getPlaces().toArray(new Place[w.getPlaces().size()]);
        String start = w.getStartPlace();
        
        for (Place p : places)
        {
            if (arePlacesConnected(w, start, p.getName()))
            {
                v.visit(p);
            }
        }
    }

    
    /**
     * Dice se due posti sono direttamente connessi o no.
     * 
     * Precondizione:
     *  - p1 e p2 sono luoghi che fanno effettivamente parte del mondo
     * 
     * @param p1
     * @param p2
     * @return 
     */
    private boolean directlyConnected (World w, Place p1, Place p2)
    {
        
        for (Passage pass: w.getAllPassages(p1.getPassages().values()))
        {
            try {
                if (pass.next(p1.getName()).equals(p2.getName()))
                {
                    return true;
                }
            } catch (PassageException ex) {
                
            }
        }
        
        return false;
    }
    
    /**
     * Dice se due posti sono connessi.
     * 
     * Ricorsiva!!!
     * 
     * Precondizione:
     *  - visited contiene la lista dei posti già visitati
     * 
     * @param w
     * @param p1
     * @param p2
     * @param visited Lista dei posti già visitati. Deve essere vuota all'inizio
     * @return 
     */
    private boolean connected (World w, String p1, String p2, List<String> visited)
    {
        Place pl1 = w.getPlace(p1);
        Place pl2 = w.getPlace(p2);
        
        if (p1.equals(p2))
        {
            return true;
        }
        else if (directlyConnected(w, pl1, pl2))
        {
            return true;
        } else {
            visited.add(p1);
            
            for (Passage pass: w.getAllPassages(pl1.getPassages().values()))
            {
                try {
                    String nxt = pass.next(p1);
                    
                    if (!visited.contains(nxt) && connected(w, nxt, p2, visited))
                    {
                        return true;
                    }
                    
                } catch (PassageException ex) {
                    
                }
                
            }
            
            return false;
        }
    }
    
    /**
     * Dice se due posti sono connessi
     * @param w
     * @param p1
     * @param p2
     * @return 
     */
    @Override
    public boolean arePlacesConnected(World w, String p1, String p2) {
        return connected(w, p1, p2, new ArrayList<>());
    }
    
    
    
    
}
