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
@UrlBinding("/maklumat_ismen")
public class MaklumatHakmilikIsmenActionBean extends AbleActionBean{
@DefaultHandler
    public Resolution showForm() {
        return new JSP("common/paparan_maklumat_hakmilik_ismen.jsp").addParameter("tab", "true");
    }
}
