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
 * @author Massita
 */
@UrlBinding("/pengambilan/maklumanEndosanBrgD")
public class MaklumanEndosanBorangDActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm1() {
        return new JSP("pengambilan/maklumanEndosanBorangD.jsp").addParameter("tab", "true");
    }
 }
