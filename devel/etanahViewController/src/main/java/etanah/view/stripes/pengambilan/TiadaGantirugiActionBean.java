/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.FasaPermohonan;
import etanah.service.BPelService;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import org.hibernate.Transaction;
import java.text.ParseException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.worklist.api.payload.Form;

@UrlBinding("/pengambilan/tiadaGantiRugi")
public class TiadaGantirugiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(TiadaGantirugiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private Dokumen dokumen;
    private String kodDokumen;
    private Pemohon pemohon;
    private Pihak pihak;
    private KodCawangan cawangan;
    private String idPermohonan;
    private String stageId;
    private String mesej;
    private Boolean errorMsg = false;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            logger.debug("-------taskId--------"+taskId);
//
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
            FasaPermohonan fasaPermohonan;
            FasaPermohonan fasaPermohonan2;
            fasaPermohonan = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14TentukanKos");
            fasaPermohonan2 = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "16KeputusanBicara");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = new KodKeputusan();
                    kodKeputusan = fasaPermohonan.getKeputusan();

                    if (kodKeputusan != null) {
                        if (fasaPermohonan.getIdAliran().equals("14TentukanKos") && !kodKeputusan.getKod().equals("XG")) {
                            addSimpleError("Keputusan Bukan Tiada GantiRugi");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
            if (fasaPermohonan2 != null) {
                if (fasaPermohonan2.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = new KodKeputusan();
                    kodKeputusan = fasaPermohonan2.getKeputusan();

                    if (kodKeputusan != null) {
                        if (fasaPermohonan2.getIdAliran().equals("16KeputusanBicara") && !kodKeputusan.getKod().equals("XG")) {
                            addSimpleError("Keputusan Bukan Tiada GantiRugi");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
        }
//        }
        return new JSP("pengambilan/tiadaGantirugi.jsp").addParameter("tab", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Boolean getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Boolean errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PengambilanService getPengambilanService() {
        return pengambilanService;
    }

    public void setPengambilanService(PengambilanService pengambilanService) {
        this.pengambilanService = pengambilanService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
