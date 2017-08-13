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
 * coinvolte nello stesso (ad esempio servirà ai passaggi con chiave per avere accesso
 * alle chiavi possedute dal player).
 * 
 * Un oggetto di questo tipo viene "broadcastato" a tutti gli oggetti appartenenti al gioco.
 * 
 * Permette così sviluppi futuri del tipo permettere ad un luogo di avere la conoscenza
 * necessaria per creare nuovi luoghi
 * 
 * Si passa il minimo della conoscenza necessaria, nella prima release non è necessaria
 * alcuna conoscenza ulteriore alle sottoclassi, quindi non si passa nulla. Sarà 
 * utilizzata nelle sue specializzazioni in future release.
 * 
 * Serve a ridurre l'accoppiamento tra le suddette classi
 * e la classe Game.
 * 
 * @author Davide
 */
public interface GameInfo {
}
