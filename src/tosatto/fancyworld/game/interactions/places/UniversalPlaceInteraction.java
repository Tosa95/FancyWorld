/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.places;

import java.util.ArrayList;
import java.util.List;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.interactions.trials.TrialInteraction;
import tosatto.fancyworld.game.world.places.Place;

/**
 * Interagisce con più tipi di posti
 * @author Davide
 */
public class UniversalPlaceInteraction implements PlaceInteraction{

    
    /*
    * Contiene una lista di PlaceInteractions conosciute
    */
    private List<PlaceInteraction> interactions = new ArrayList<>();

    public UniversalPlaceInteraction() {
         //TODO: portare fuori dal costruttore e passare la lista
        interactions.add(new KeyedPlaceInteraction());
        interactions.add(new TrialedPlaceInteraction());
        
    }
    
    
    
    @Override
    public void interact(MessageIO io, Game g, Place p) {
        
        /*
        * Scansiona la lista di place interactions. Se ne trova una in grado di 
        * gestire il luogo corrente, la utilizza.
        *
        * Non si ferma alla prima, ma continua fino in fondo alla lista in modo
        * da richiamare tutte le interazioni possibili per il luogo.
        *
        * E' garantito che venga mantenuto l'ordine in cui le interazioni sono state 
        * aggiunte nella lista.
        *
        * Ad esempio se abbiamo un posto di tipo Trialed, verrà eseguita prima
        * l'interazione di tipo keyed (dato che TrialedPlace è derivato da KeyedPlace) e
        * poi quella di tipo trialed
        */
        for (PlaceInteraction pi : interactions)
        {
            Class<?> c = pi.type();
            if (c.isAssignableFrom(p.getClass()))
            {
                pi.interact(io, g, p);
            }
        }
        
    }

    @Override
    public Class<?> type() {
        return Place.class;
    }
    
}
