/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.bundles.check;

/**
 *
 * @author Davide
 */
public class CheckResult {
    
    private String msg;
    private boolean res;

    public CheckResult(boolean res, String msg) {
        this.msg = msg;
        this.res = res;
    }
    
    public boolean getResult ()
    {
        return res;
    }
    
    public String getMessage ()
    {
        return msg;
    }
}
