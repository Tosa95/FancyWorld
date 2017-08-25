/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.interactions.trials;

import java.util.HashMap;
import java.util.Map;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.trials.Trial;

/**
 *
 * @author Davide
 */
public class UniversalTrialInteraction implements TrialInteraction{
    
    private Map<String, TrialInteraction> interactions;

    public UniversalTrialInteraction() {
        
        interactions = new HashMap<>();
        
        interactions.put("Sequence", new SequenceTrialInteraction());
        interactions.put("Fortune", new FortuneTrialInteraction());
        interactions.put("Word", new WordTrialInteraction());
        
    }

    @Override
    public boolean interact(Trial t, MessageIO io) {
        
        if (!interactions.containsKey(t.getType()))
        {
            io.inform(String.format("Impossibile interagire con una prova di tipo %s. Segnalare questo"
                    + " problema ad uno sviluppatore riportando questo messaggio", t.getType()));
            return false;
        }
        
        io.inform(String.format("Stai per venire sottoposto ad una prova di tipo %s", t.getType()));
        
        return interactions.get(t.getType()).interact(t, io);
    }
    
    public void doTrial(PointedPlayer pp, Trial t, MessageIO io)
    {
        boolean passed = this.interact(t, io);
                
        if (passed)
        {
            pp.applyDeltaPoints(t.getValue());

            io.inform(String.format("Hai vinto. Ti vengono accreditati %d punti."
                    + " Ora hai un totale di %d punti", t.getValue(), pp.getPoints()));
        } else if (pp.getPoints()>=t.getValue()){
            
            pp.applyDeltaPoints(- t.getValue());
            
            io.inform(String.format("Purtroppo hai perso. Ti vengono tolti %d punti."
                    + " Ora hai un totale di %d punti", t.getValue(), pp.getPoints()));
        }else{
            
            pp.applyDeltaPoints(- t.getValue());
            
            io.inform(String.format("Purtroppo hai perso. Dato che non avevi punti a sufficienza,"
                    + "il tuo punteggio è stato portato a 0"));
            
        }
    }
    
    
}
