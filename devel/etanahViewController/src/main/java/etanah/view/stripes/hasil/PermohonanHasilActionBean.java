/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/pmohonanHasil")
public class PermohonanHasilActionBean extends AbleActionBean{
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/sokongan.jsp").addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/main_page.jsp");
    }

//    public Resolution showForm2(){
//        return new ForwardResolution("hasil/PertanyaanAkaunAmanahList.jsp");
//    }

    public Resolution showForm3(){
        return new JSP("hasil/perihal_cukai.jsp").addParameter("tab", "true");
    }
}
