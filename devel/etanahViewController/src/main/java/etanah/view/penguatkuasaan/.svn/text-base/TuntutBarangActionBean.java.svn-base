/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.BarangRampasan;
import etanah.model.KodCaraPermohonan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.KandunganFolder;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import etanah.service.AduanService;
import etanah.service.EnforceService;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.workflow.WorkFlowService;

/**
 *
 * @author nurshahida.radzi
 */
@UrlBinding("/penguatkuasaan/tuntutan_barang")
public class TuntutBarangActionBean extends AbleActionBean {

    private static final Logger log = Logger.getLogger(TuntutBarangActionBean.class);
    private static final boolean debug = log.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanService service;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    AduanService aduanService;
    @Inject
    EnforceService enforceService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private String idPermohonan;
    private FolderDokumen folderDokumen;
    private Dokumen dokumen;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }
//    private Boolean opFlag = false;
    String idAduan;
    private List<Permohonan> senaraiAduan;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<KandunganFolder> senaraikandungan;
    private List<Dokumen> senaraiDokumen;

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<KandunganFolder> getSenaraikandungan() {
        return senaraikandungan;
    }

    public void setSenaraikandungan(List<KandunganFolder> senaraikandungan) {
        this.senaraikandungan = senaraikandungan;
    }
    private KodCaraPermohonan caraPermohonan;
    private String sebab;
    private String stageId;
    private String barangRampasanStatus;

    public String getBarangRampasanStatus() {
        return barangRampasanStatus;
    }

    public void setBarangRampasanStatus(String barangRampasanStatus) {
        this.barangRampasanStatus = barangRampasanStatus;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<Permohonan> getSenaraiAduan() {
        return senaraiAduan;
    }

    public void setSenaraiAduan(List<Permohonan> senaraiAduan) {
        this.senaraiAduan = senaraiAduan;
    }

    public String getIdAduan() {
        return idAduan;
    }

    public void setIdAduan(String idAduan) {
        this.idAduan = idAduan;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }


    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tuntutan_barang.jsp");
    }

    public Resolution searchAduan() {
        log.debug("searchAduan : " + idAduan);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tuntutan_barang.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idAduan = getContext().getRequest().getParameter("idAduan");

        if (idAduan != null) {
            permohonan = permohonanDAO.findById(idAduan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idAduan);
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idAduan, "S");
            }
            senaraiDokumen = enforceService.findDokumenList(idAduan);
            if (operasiPenguatkuasaan != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
            }
            getContext().getRequest().setAttribute("view", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("view", Boolean.FALSE);
        }

    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        idAduan = getContext().getRequest().getParameter("idAduan");
        dokumen = enforceService.findDokumen(id);
        enforceService.deleteAll2(dokumen);

        return new RedirectResolution(TuntutBarangActionBean.class, "locate");
    }

    public Resolution Selesai() {
        System.out.println("Selesai");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            KodUrusan ku = kodUrusanDAO.findById("TBR");
            WorkFlowService.initiateTask(ku.getIdWorkflow(), permohonan.getIdPermohonan(), pengguna.getKodCawangan().getKod() + ",00", pengguna.getIdPengguna(), ku.getNama());
            addSimpleMessage("Maklumat telah berjaya dihantar.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            addSimpleError("Maklumat tidak dapat dihantar.");
        }
        return new RedirectResolution(TuntutBarangActionBean.class, "locate");
    }

    public Resolution refreshPage() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tuntutan_barang.jsp");
    }
}
