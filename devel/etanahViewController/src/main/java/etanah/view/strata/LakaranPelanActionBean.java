/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;

import able.stripes.JSP;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
/**
 *
 * @author Murali
 */
@UrlBinding("/strata/lakaranPelan")
public class LakaranPelanActionBean extends BasicTabActionBean {
    
    @DefaultHandler
    public Resolution showForm() {
        
        return new JSP("strata/kuatkuasa/lakaran_Pelan.jsp").addParameter("tab", "true");
    }    
}
