/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PembangunanService;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/terimaPaJupem")
public class TerimaPaJupemActionBean extends AbleActionBean{

    private String noPelan;

    @DefaultHandler
    public Resolution showForm(){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/terima_pa_jupem.jsp").addParameter("tab", "true");
    }

    public String getNoPelan() {
        return noPelan;
    }

    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

   public Resolution simpan(){

       getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/terima_pa_jupem.jsp").addParameter("tab", "true");
   }
}
