/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import tosatto.fancyworld.main.bundles.ParametersBundle;

/**
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
    
    public void setBundle (ParametersBundle pb)
    {
        bundle = pb;
    }
    
    public ParametersBundle getBundle ()
    {
        return bundle;
    }
    
        
}
