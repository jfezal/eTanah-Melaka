/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject ;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan ;
import etanah.model.Pihak ;
import etanah.model.Pemohon ;
import etanah.model.PihakPengarah ;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before ;
import net.sourceforge.stripes.controller.LifecycleStage ;

/**
 *
 * @author sitifariza.hanim & afham
 */
@UrlBinding("/pelupusan/perizaban")
public class PerizabanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PihakDAO pihakDAO ;
    @Inject
    PihakPengarahDAO pihakPengarahDAO ;
    @Inject
    PemohonDAO pemohonDAO ;
    @Inject
    PelupusanService pelupusanService ;
    private String idPermohonan ;
    private Permohonan permohonan ;
    private HakmilikPermohonan hakmilikPermohonan ;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/permohonan_perizaban.jsp").addParameter("tab", "true");
    }

      public Resolution showForm1() {
        return new JSP("pelupusan/pembatalan_perizaban.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
          }

    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

   
}
