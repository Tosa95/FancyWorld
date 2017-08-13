/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.simpleframework.xml.ElementMap;

/**
 *
 * @author Davide
 */
public class ParametersBundle {
    
    @ElementMap (name = "bundle")
    private Map<String, Integer> bundle;

    public ParametersBundle() {
        bundle = new HashMap<>();
    }
    
    public void addParameter(String name, int value)
    {
        bundle.put(name, value);
    }
    
    public void setParameter(String name, int value)
    {
        addParameter(name, value);
    }
    
    public int getParameter(String name)
    {
        return bundle.get(name);
    }
    
    public boolean hasParameter(String name)
    {
        return bundle.containsKey(name);
    }
    
    public Set<String> getParameterNames ()
    {
        return bundle.keySet();
    }
}
