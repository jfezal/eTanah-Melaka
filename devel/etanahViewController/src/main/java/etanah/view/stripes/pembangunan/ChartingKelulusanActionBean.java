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
import etanah.model.FasaPermohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/chartingKelulusan")
public class ChartingKelulusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private Permohonan permohonan;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private String keputusan;
    private String idPermohonan;
    private Pengguna pguna;
    private String taskId;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pembangunan/pecahSempadan/dev_charting_kelulusan.jsp").addParameter("tab", "true");
    }

    public Resolution chartingBatal(){
        return new JSP("pembangunan/pecahSempadan/dev_charting_batal.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniHakmilik(){
        return new JSP("pembangunan/pecahSempadan/dev_kemaskini_hakmilik.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna =  (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = getCurrentStageId(taskId);

        if(permohonan != null && permohonan.getKeputusan()!= null){
            keputusan = permohonan.getKeputusan().getNama();
        }

//         if (!(permohonan.getSenaraiFasa().isEmpty())) {
//
//            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {
//
//                fasaMohon = new FasaPermohonan();
//                fasaMohon = permohonan.getSenaraiFasa().get(i);
//
//                if(fasaMohon.getIdAliran().equals("rekodkeputusanmmkn")){
//                    keputusan = fasaMohon.getKeputusan().getNama();
//                } else if(fasaMohon.getIdAliran().equals("cetakjkbbrekodkpsn")){
//                    keputusan = fasaMohon.getKeputusan().getNama();
//                }
//            }
//         }

    }


    public String getCurrentStageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
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

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
