/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.info;

import tosatto.fancyworld.game.world.passages.Passage;

/**
 *
 * Contiene funzioni per accedere alle proprietà del gioco di rilievo per le classi
 * coinvolte nello stesso (ad esempio serve ai passaggi con chiave per avere accesso
 * alle chiavi possedute dal player).
 * 
 * Un oggetto di questo tipo viene passato a tutti gli oggetti appartenenti al gioco.
 * 
 * 
 * Si passa il minimo della conoscenza necessaria. 
 * 
 * Questa è l'interfaccia base usata come "segnaposto" nella prima release. I metodi
 * utili sono stati aggiunti man mano che si rendevano necessari a specializzazioni di
 * questa interfaccia
 * 
 * @author Davide
 */
public interface GameInfo {
}
