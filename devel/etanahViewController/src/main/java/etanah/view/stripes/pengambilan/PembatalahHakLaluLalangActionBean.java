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
@UrlBinding("/pengambilan/pembatalan_hak_lalu_lalang")
public class PembatalahHakLaluLalangActionBean extends AbleActionBean{

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/pembatalan_hak_lalu_lalang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/notifikasiPHLL.jsp").addParameter("tab", "true");
    }

}
