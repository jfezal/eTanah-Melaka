/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view;

import etanah.view.stripes.MainActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/welcome")

public class WelcomeActionBean extends BasicTabActionBean{   
    
     @DefaultHandler
    public Resolution welcome() {        
        return new RedirectResolution(MainActionBean.class);
    }
}
