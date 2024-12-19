/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;

import able.stripes.JSP;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/muat_naik")
public class MuatNaikPetakActionBean extends BasicTabActionBean{


     @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/pbbm/muat_naikPetak.jsp").addParameter("tab", "true");
    }


    
    
}
