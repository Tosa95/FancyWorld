/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.IO;

/**
 *
 * @author Davide
 */
public interface MessageIO {
    
    /**
     * Informa un utente riguardo a un fatto
     * @param msg Fatto da presentare all'utente
     */
    public void inform (String msg);
    
    /**
     * Chiede un'informazione all'utente
     * @param msg Richiesta
     * @param alternatives Possibili risposte
     * @return Indice della risposta selezionata
     */
    public int ask (String msg, String[] alternatives);
    
    /**
     * Chiede un'informazione all'utente
     * @param msg Richiesta
     * @return Risposta
     */
    public String ask (String msg);
    
    /**
     * Presenta un menu all'utente
     * @param title Titolo del menu
     * @param entries Voci del menu
     * @return Voce selezionata
     */
    public int presentMenu (String title, String[] entries);
}
