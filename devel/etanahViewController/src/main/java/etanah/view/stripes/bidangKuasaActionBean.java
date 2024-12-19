/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/bidang_kuasa")
public class bidangKuasaActionBean extends AbleActionBean {
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/bidang_kuasa.jsp").addParameter("tab", "true");
    }

}

