/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author fikri
 */
@UrlBinding("/utiliti/jana_dokumen")
public class JanaDokumenAction extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("utiliti/jana_dokumen.jsp");
    }
}
