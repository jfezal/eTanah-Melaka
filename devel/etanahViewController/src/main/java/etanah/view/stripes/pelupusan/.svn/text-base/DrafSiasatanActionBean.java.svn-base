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
@UrlBinding("/pelupusan/draf_siasatan")
public class DrafSiasatanActionBean extends AbleActionBean {

    private String sebabPenangguhan;

 @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/draf_siasatan.jsp").addParameter("tab", "true");
    }

    public String getSebabPenangguhan() {
        return sebabPenangguhan;
    }

    public void setSebabPenangguhan(String sebabPenangguhan) {
        this.sebabPenangguhan = sebabPenangguhan;
    }
 
}
