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

@UrlBinding("/pengambilan/pembatalan")
public class ButiranPembatalanHakLaluLangActionBean extends AbleActionBean{

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pembatalanhaklalulalang/butiran_pembatalan.jsp").addParameter("tab", "true");
    }

}