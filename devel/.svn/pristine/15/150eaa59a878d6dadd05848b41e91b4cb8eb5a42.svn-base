/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/perintah_ulasanElektrik")
public class PerintahUlasanElektrikActionBean extends AbleActionBean{

    private String ulasan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/perintah_ulasanElektrik.jsp").addParameter("tab", "true");
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}
