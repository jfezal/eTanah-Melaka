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
@UrlBinding("/daftar/maklumat_pemberi_penerima_suratkuasa_wakil")
public class maklumatPemberiPenerimaSuratkuasa extends AbleActionBean {
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
    }

}

