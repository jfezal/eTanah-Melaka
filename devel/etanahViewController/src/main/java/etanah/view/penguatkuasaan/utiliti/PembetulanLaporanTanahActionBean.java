/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.view.penguatkuasaan.LaporanTanahV2ActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author MohammadHafifi
 */
@UrlBinding("/penguatkuasaan/pembetulan_laporan_tanah")
public class PembetulanLaporanTanahActionBean extends AbleActionBean {
    
    private Pengguna pguna;
    private String idPermohonan;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/utiliti/carian_dokumen_siasatan.jsp");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }
    
    public Resolution carianLaporanTanah() throws WorkflowException {
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_tanah/laporan_tanahV2.jsp")
//                .addParameter("idPermohonan", idPermohonan)
//                .addParameter("jenisLaporan", "LTNH");
        return new RedirectResolution(LaporanTanahV2ActionBean.class, "viewOnlyLaporanTanahPPT")
                .addParameter("jenisLaporan", "LTNH")
                .addParameter("idPermohonan", idPermohonan)
                .addParameter("tab", "false");
    }
}
