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
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
//import etanah.service.BPelService;
//import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.PembangunanService;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.FasaPermohonan;
import etanah.dao.PermohonanDAO;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/keputusan_mmk")
public class KeputusanMMKActionBean extends AbleActionBean{
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;

    private Permohonan permohonan;
    private FasaPermohonan fasaMohon;
    private String stageId;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

    }

    public Resolution simpan(){

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

         infoAudit.setDimasukOleh(pengguna);
         infoAudit.setTarikhMasuk(new java.util.Date());

//              BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//
            //test wani
            stageId = "keputusanmmk";

        if(permohonan != null){
            fasaMohon.setCawangan(pengguna.getKodCawangan());
            fasaMohon.setPermohonan(permohonan);
            fasaMohon.setInfoAudit(infoAudit);
            fasaMohon.setIdAliran(stageId);
            fasaMohon.setIdPengguna(pengguna.getIdPengguna());
            devService.simpanFasaPermohonan(fasaMohon);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_kemasukan_keputusan_MMK.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

}
