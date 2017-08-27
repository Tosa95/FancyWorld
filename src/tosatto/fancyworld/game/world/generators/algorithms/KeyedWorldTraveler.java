/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.passages.KeyedPassage;
import tosatto.fancyworld.game.world.passages.Passage;
import tosatto.fancyworld.game.world.passages.exceptions.PassageException;
import tosatto.fancyworld.game.world.places.KeyedPlace;
import tosatto.fancyworld.game.world.places.Place;

/**
 *
 * @author Davide
 */
public class KeyedWorldTraveler implements GoalReachableChecker, PlaceIterator, WorldConnectionChecker, PlaceConnectionChecker{
    
    /**
     * Unisce due mappe nomePosto:insiemeChiavi
     * @param h1 Prima mappa, contiene anche il risultato
     * @param h2 Seconda mappa
     */
    private void mergeHM (HashMap <String, Set<String>> h1, HashMap <String, Set<String>> h2)
    {
        HashMap <String, Set<String>> res = new HashMap<>(h1);
        
        for (String key : h2.keySet())
        {
            if (h1.containsKey(key) /*Il posto c'è in entrambe le mappe*/)
            {
                Set<String> merged = new HashSet<>(h1.get(key));
                
                merged.addAll(h2.get(key)); /*unisce le chiavi*/
                
                res.put(key, merged);
            }else{
                res.put(key, h2.get(key));
            }
        }
        
        h1.clear();
        h1.putAll(res);
    }
    
    /**
     * Controlla se s1 contiene s2
     * @param s1 Primo insieme
     * @param s2 Secondo insieme
     * @return true: s2 <- s1, false altrimenti
     */
    private boolean includeSet (Set<String> s1, Set<String> s2)
    {
        
        for (String str : s2)
        {
            if (!s1.contains(str))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Versione modificata dell'algoritmo di Djikstra per calcolare le chiavi effettivamente
     * possibili da avere in ogni posto.
     * 
     * Precondizioni: 
     *  - sctPlace è un luogo facente parte di w
     *  - hm != null
     *  - availKeys != null
     *  - w != null
     * 
     * @param w Mondo in esame
     * @param actPlace Posto attuale
     * @param availKeys Chiavi che si possono avere con il cammino in esame
     * @param hm Risultato, mappa [nomeposto, lista chiavi]
     * @param v Azione da intraprendere per i posti raggiungibili. Nota: non è garantita la visita dei posti una sola volta!!!
     */
    private void getAvailableKeysAtPlaces (World w, String actPlace, Set<String> availKeys, HashMap <String, Set<String>> hm, PlaceVisitor v)
    {
        //System.out.println ("Visiting " + actPlace);
        
        //System.out.println("Begun " + actPlace + "   " + Integer.toString(callCount));
        
        //callCount++;
        
        if (v != null)
            v.visit(w.getPlace(actPlace));
        
        HashMap<String, Set<String>> temp = new HashMap<>();
        
        availKeys = new HashSet<>(availKeys);
        
        KeyedPlace pl = (KeyedPlace)w.getPlace(actPlace);
        
        if (pl.hasKey() && !availKeys.contains(pl.getKey()))
            availKeys.add(pl.getKey());

        if (hm.containsKey(actPlace) /*sono già passato di qua*/ && 
                includeSet(hm.get(actPlace), availKeys) /*il percorso attuale non 
                aggiunge chiavi a quelle già ottenibili nel posto*/)
        {
             return;
        }
        
        //Se arrivo qua significa che con il percorso attuale sto aggiungendo chiavi
        //a quelle ottenibili dal posto, quindi devo rivalutare tutti gli altri posti
        //collegati per vedere se aggiungo anche a loro
        
        temp.put(actPlace, availKeys);
        mergeHM(hm, temp); //Aggiungo le info sulle chiavi ottenibili alla mappa principale
        
        
        for (Passage p : w.getAllPassages(w.getPlace(actPlace).getPassages().values()))
        {
            KeyedPassage kp = (KeyedPassage)p;
            
            try {
                String nextPlace = w.getPlace(p.next(actPlace)).getName();
                
                if (!kp.isClosed()/*se è un passaggio aperto*/||availKeys.contains(kp.requiredKey())/*oppure ho la chiave*/ )
                {
                    /*provo a passare, se era murato, mi da eccezione*/
                    getAvailableKeysAtPlaces(w, nextPlace, availKeys, hm, v);
                }
            } catch (PassageException ex) {
                //Ok, passaggio murato
            }
            
        }
        
        //System.out.println("Done " + actPlace + "   " + Integer.toString(callCount));
        //callCount--;
    }

    /**
     * Wrapper per getAvailableKeysAtPlaces con più parametri, in modo da semplificare la chiamata
     * @param w Il mondo 
     * @return la mappa contenente le chiavi ottenibili in ogni posto
     */
    private HashMap <String, Set<String>> getAvailableKeysAtPlaces (World w)
    {
        HashMap <String, Set<String>> res = new HashMap<>();
        getAvailableKeysAtPlaces(w, w.getStartPlace(), new HashSet<>(), res, null);
        return res;
    }
    
    /**
     * Controlla che il luogo goal sia raggiungibile.
     * 
     * Precondizione:
     *  - w è di tipo keyed o derivati
     * 
     * @param w 
     * @return 
     */
    @Override
    public boolean isGoalReachable(World w) {
        String goal = "";
        
        for (Place p : w.getPlaces())
        {
            if (p.isGoal())
                goal = p.getName();
        }
        
        HashMap <String, Set<String>> map = getAvailableKeysAtPlaces(w);
        
        return map.containsKey(goal);
    }

    /**
     * Permette di fare eseguire un'azione in tutti i posti raggiungibili.
     * 
     * ATTENZIONE: non è garantito che l'azione venga eseguita una sola volta per
     * ogni posto raggiungibile
     * 
     * Precondizione:
     *  - w è di tipo Keyed o derivati
     * 
     * @param w
     * @param v 
     */
    @Override
    public void visitAllReachablePlaces(World w, PlaceVisitor v) {
        
        HashMap <String, Set<String>> res = new HashMap<>();
        getAvailableKeysAtPlaces(w, w.getStartPlace(), new HashSet<>(), res, v);
        
    }
    
    /**
     * Dice se il mondo è connesso, secondo l'avanzamento di un giocatore,
     * quindi considerando anche le chiavi.
     * 
     * Precondizione:
     *  - w è di tipo Keyed o derivati
     * 
     * @param w
     * @return 
     */
    @Override
    public boolean isConnected(World w) {
        //Basta che l'algoritmo di mapping posto-chiavi sia in grado di raggiungere tutti i posti
        return getAvailableKeysAtPlaces(w).keySet().size() == w.getPlaces().size();
    }

    /**
     * Dice se due posti sono connessi, secondo l'avanzamento di un giocatore,
     * quindi considerando anche le chiavi.
     * 
     * Precondizione:
     *  - w è di tipo Keyed o derivati
     * 
     * @param w
     * @param p1
     * @param p2
     * @return 
     */
    @Override
    public boolean arePlacesConnected(World w, String p1, String p2) {
        
        HashMap <String, Set<String>> res = new HashMap<>();
        getAvailableKeysAtPlaces(w, p1, new HashSet<>(), res, null);
        
        return res.containsKey(p2);
    }

 
    
}
