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
 * Rappresenta un contenitore per parametri interi
 * @author Davide
 */
public class ParametersBundle {
    
    @ElementMap (name = "bundle")
    private Map<String, Integer> bundle;

    public ParametersBundle() {
        bundle = new HashMap<>();
    }
    
    /**
     * Aggiunge un parametro
     * @param name
     * @param value 
     */
    public void addParameter(String name, int value)
    {
        bundle.put(name, value);
    }
    
    /**
     * Imposta il valore di un parametro
     * @param name
     * @param value 
     */
    public void setParameter(String name, int value)
    {
        addParameter(name, value);
    }
    
    /**
     * Ritorna il valore di un parametro
     * 
     * Precondizione:
     *  - name deve identificare un parametro effettivamente presente nel bundle
     * 
     * @param name
     * @return 
     */
    public int getParameter(String name)
    {
        return bundle.get(name);
    }
    
    /**
     * Dice se un parametro è contenuto nel bundle
     * @param name
     * @return 
     */
    public boolean hasParameter(String name)
    {
        return bundle.containsKey(name);
    }
    
    /**
     * Ritorna i nomi di tutti i parametri contenuti nel bundle
     * @return 
     */
    public Set<String> getParameterNames ()
    {
        return bundle.keySet();
    }
}
