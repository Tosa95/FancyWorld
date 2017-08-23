/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles;

import tosatto.fancyworld.IO.MessageIO;

/**
 * Rappresenta un oggetto in grado di guidare l'interazione tra un giocatore e un bundle di parametri
 * @author Davide
 */
public interface BundleInteraction {
    
    public void interact (ParametersBundle b, MessageIO io);
    
}
