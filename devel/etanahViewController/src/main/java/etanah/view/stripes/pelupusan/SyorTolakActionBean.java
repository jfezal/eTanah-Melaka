/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/syor_tolak_ringkasan")
public class SyorTolakActionBean extends AbleActionBean {

    private String sebabPenolakan;

 @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/syor_tolak_ringkasan.jsp").addParameter("tab", "true");
    }

    public String getSebabPenolakan() {
        return sebabPenolakan;
    }

    public void setSebabPenolakan(String sebabPenolakan) {
        this.sebabPenolakan = sebabPenolakan;
    }
   
}

