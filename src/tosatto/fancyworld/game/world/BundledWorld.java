/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import tosatto.fancyworld.game.info.GameInfo;
import tosatto.fancyworld.main.bundles.ParametersBundle;

/**
 * Rappresenta un mondo dotato di un bundle di parametri
 * 
 * @author Davide
 */
public class BundledWorld extends TrialedWorld{
    
    @Element(name = "bundle")
    private ParametersBundle bundle;
    
    public BundledWorld(@Attribute (name = "name") String name,
                      @Attribute (name = "start") String startPlace) {
        super(name, startPlace);
    }
    
    /**
     * Imposta il bundle di parametri
     * @param pb 
     */
    public void setBundle (ParametersBundle pb)
    {
        bundle = pb;
    }
    
    /**
     * Recupera il bundle di parametri
     * @return 
     */
    public ParametersBundle getBundle ()
    {
        return bundle;
    }
    
    public GameInfo getGi()
    {
        return super.gi;
    }
        
}
