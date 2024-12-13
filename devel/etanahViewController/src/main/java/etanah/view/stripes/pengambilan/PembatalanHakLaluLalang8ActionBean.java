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
@UrlBinding("/pengambilan/pembatalan_hak_lalu")
public class PembatalanHakLaluLalang8ActionBean extends AbleActionBean{

     @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/pembatalan_hak_lalu.jsp").addParameter("tab", "true");
    }


}
