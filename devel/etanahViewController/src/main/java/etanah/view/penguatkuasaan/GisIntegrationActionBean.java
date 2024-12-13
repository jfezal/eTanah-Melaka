/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author zabedah.zainal modify by sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/charting")
public class GisIntegrationActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private String idPermohonan;
    private String nama;
    private Pengguna pguna;
    private String stageId;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @DefaultHandler
    public Resolution showForm() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/gis_integration.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/Semak_Charting.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/charting_akhir.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/sediakan_PU_GIS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/semak_PU_GIS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/sahkan_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/laporan_tanah_GIS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm9() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/terima_B1.jsp").addParameter("tab", "true");
    }

    public Resolution showForm10() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/hantar_jupem.jsp").addParameter("tab", "true");
    }

    public Resolution showForm11() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/lakaran_kasar.jsp").addParameter("tab", "true");
    }
    
      public Resolution showForm12() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/kembali_PU.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }
}
