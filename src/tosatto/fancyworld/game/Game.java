/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game;

/**
 * 
 * Classe base di Game. Generaslizza tutte le possibili partite. Ha solo un nome
 * 
 * @author Davide
 */
public abstract class Game {
     public abstract String getName();
     public abstract void play();
}
