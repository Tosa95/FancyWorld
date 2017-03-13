/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.player;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.GameInfo;

/**
 *
 * @author Davide
 */
@Root (name = "Player")
public class Player {
    
    @Attribute (name = "name")
    private String name;
    
    @Attribute (name = "place")
    private String place;

    protected GameInfo gi;
    
    public Player(@Attribute (name = "name") String name) 
    {
        this.name = name;
    }

    public void setGi(GameInfo gi) {
        this.gi = gi;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }
    
}
