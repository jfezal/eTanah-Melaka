/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;
import java.util.Iterator;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

import etanah.model.Pengguna;
import etanah.service.EnforceService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/surat_jaminan")
public class JaminanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JaminanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private BarangRampasan barangRampasan;
    private KodCawangan cawangan;
    private String tarikhOperasi;
    private String idOperasi;
    private BigDecimal jum;
    private String stageId;
    private List<OperasiAgensi> senaraiOperasiAgensi;
    private List<BarangRampasan> senaraiBarangRampasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_jaminan_akujanji.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_jaminan_akujanji.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_jaminan_akujanji.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(JaminanActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idOperasi = getContext().getRequest().getParameter("idOperasi");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
            senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
            if (senaraiBarangRampasan != null) {
                logger.debug("not null " + senaraiBarangRampasan.size());
                jum = BigDecimal.ZERO;
                for (Iterator<BarangRampasan> sBrg = senaraiBarangRampasan.iterator(); sBrg.hasNext();) {
                    barangRampasan = sBrg.next();
//                    logger.debug(barangRampasan.getAmaunJaminan() + "abc");
                    if (barangRampasan.getAmaunJaminan() != null) {
                        jum = barangRampasan.getAmaunJaminan().add(jum);
                    }
                }
            }
            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
                if (stageId.matches("Sedia_SJ") || stageId.matches("Kpsn_n_sedia_surat_jaminan") || stageId.matches("Maklum_jaminan_dan_akujanji")
                        || stageId.matches("Perakuan_dan_pelepasan_brg") || stageId.matches("Terima_arahan_pelepasan")
                        || stageId.matches("Kkini_rekod_inventori") || stageId.matches("Peraku_SJ") || stageId.matches("Kkini_bukti_penyampaian")
                        || stageId.matches("Kkini_bukti")) {
                    getContext().getRequest().setAttribute("Jaminan", Boolean.TRUE);
                }
            }
        }
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public EnforcementService getEnforcementService() {
        return enforcementService;
    }

    public void setEnforcementService(EnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOp(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getTarikhOperasi() {
        return tarikhOperasi;
    }

    public void setTarikhOperasi(String tarikhOperasi) {
        this.tarikhOperasi = tarikhOperasi;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }

    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }

    public BarangRampasan getBarangRampasan() {
        return barangRampasan;
    }

    public void setBarangRampasan(BarangRampasan barangRampasan) {
        this.barangRampasan = barangRampasan;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }
}
