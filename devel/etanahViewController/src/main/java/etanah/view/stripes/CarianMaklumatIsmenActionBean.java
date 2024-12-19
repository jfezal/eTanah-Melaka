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
 * @author khairil
 */
@UrlBinding("/pendaftaran/carian_ismen")
public class CarianMaklumatIsmenActionBean extends AbleActionBean{
@DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/carian_maklumat_fail_ismen.jsp").addParameter("tab", "true");
    }
    public Resolution senaraiPerserahan(){

         return new JSP("daftar/senarai_perserahan.jsp").addParameter("tab", "true");
    }
}
