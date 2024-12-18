/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.view.stripes.pembangunan.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.Configuration;
import etanah.dao.PermohonanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.IntegrasiDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.JupemService;
import etanah.service.PembangunanService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import etanah.view.utility.JupemInIntegration;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Rohan
 */
@UrlBinding("/penguatkuasaan/pu")
public class PuActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PuActionBean.class);
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    JupemService jupemService;
//    @Inject
//    PembangunanService pembangunanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PembangunanUtility pu;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private List<NoPt> noPTList;
    private Boolean edit1;
    private Boolean edit2;
    private Boolean view;
    private String taskId;
    private String kodHakmilik;
    private String sipuTundatangan;
    private String ditundatangan;
    private List<Pengguna> penggunaList;
    private Long noPTSementara;
    private Task task = null;
    private String workflowId;
    private String idFolder = "";
    private String kodUrusan;
    private KodCawanganJabatan alamat;
    private HakmilikPermohonan hakmilikMukim;
    private List<NoPt> noPtList2;
    private PermohonanPlotPelan mpp;
    private List<PermohonanPlotPelan> mppMelaka;
    private List listAlamat;
    private Pihak pihak;
    private List<Pihak> uniquePemohonList2;
    private String nama;
    private BigDecimal luasKeseluruhan;
    private String kodKeseluruhan;
    private String noLot;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        edit1 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        view = Boolean.FALSE;
        return new JSP("penguatkuasaan/pu/pu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.TRUE;
        view = Boolean.TRUE;
        return new JSP("penguatkuasaan/pu/pu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.FALSE;
        view = Boolean.TRUE;
        return new JSP("penguatkuasaan/pu/pu.jsp").addParameter("tab", "true");
    }

    // add SIPU
    public Resolution showSIPU() {
        return new JSP("penguatkuasaan/pu/sipu_tandatangan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        
        if(StringUtils.isNotBlank(taskId)){
            stageId = currentStageId(taskId);
        }else{
            stageId = "g_penyediaan_pu";
        }
        

        System.out.println("idPermohonan::" + idPermohonan);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Negeri Sembilan";
        }

        if (idPermohonan != null) {
            penggunaList = getSenaraiPengguna(permohonan.getCawangan());

            permohonanUkur = enforcementService.findPermohonanUkurByIdPermohonan(idPermohonan);
            if (permohonanUkur != null && permohonanUkur.getKodHakmilik() != null) {
                kodHakmilik = permohonanUkur.getKodHakmilik().getKod();
            }
            if (permohonanUkur == null) {
                permohonanUkur = new PermohonanUkur();
            }
        }
        
        noLot = permohonanUkur.getUkurSemulaLot();

        PermohonanTandatanganDokumen tandatanganDokumen = new PermohonanTandatanganDokumen();
        PermohonanTandatanganDokumen tandatanganDokumen1 = new PermohonanTandatanganDokumen();
        PermohonanTandatanganDokumen tandatanganDokumen2 = new PermohonanTandatanganDokumen();
        tandatanganDokumen = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
        tandatanganDokumen2 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
        tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SN2A");
        LOG.info("----------------tandatanganDokumen-------------------" + tandatanganDokumen);
        LOG.info("----------------tandatanganDokumen1-------------------" + tandatanganDokumen1);
        LOG.info("----------------tandatanganDokumen2-------------------" + tandatanganDokumen2);
        if (tandatanganDokumen != null) {
            ditundatangan = tandatanganDokumen.getDiTandatangan();
        }
        if (tandatanganDokumen1 != null) {
            sipuTundatangan = tandatanganDokumen1.getDiTandatangan();
        }
        if (tandatanganDokumen2 != null) {
            sipuTundatangan = tandatanganDokumen2.getDiTandatangan();
        }

        if (permohonan != null) {
            KodUrusan ku = permohonan.getKodUrusan();
            kodUrusan = ku.getKod();
            workflowId = ku.getIdWorkflow();
            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {            
            //TODO: Hafifi 18/4/2014 : Maklumat Pemohon (PTD atau Pemilik Tanah)

            hakmilikMukim = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);

            //TODO: Hafifi 18/4/2014 : Jumlah luas yang tinggal lepas terhakis
            luasKeseluruhan = hakmilikMukim.getLuasTerlibat();
            kodKeseluruhan = hakmilikMukim.getKodUnitLuas().getNama();
        }
        
        System.out.println(permohonanUkur.getUkurSemulaLot());
    }

    public void generateMohonUkur() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();
        if (idPermohonan != null) {
            permohonanUkur = enforcementService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }
        System.out.println("----------permohonanUkur------start-----" + permohonanUkur);
        if (permohonanUkur == null) {
            permohonanUkur = new PermohonanUkur();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
            PermohonanUkur maxPuPermohonanUkur = enforcementService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
                noPU = df.format(val) + "/" + (1900 + dt.getYear());
                System.out.println("-----------Seq-------------" + noPU);
                permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    permohonanUkurTemp.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    permohonanUkurTemp.setNoPermohonanUkur(nopu);
                }
            }


            permohonanUkurTemp.setNoRujukanPejabatTanah(idPermohonan);
            permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
            //permohonanUkurTemp.setNoPermohonanUkur(permohonanUkur.getNoPermohonanUkur());
            permohonanUkurTemp.setPerincianBorang5b(permohonanUkur.getPerincianBorang5b());
            permohonanUkurTemp.setPerincianBorang5c(permohonanUkur.getPerincianBorang5c());
            permohonanUkurTemp.setPerincianBorang5d(permohonanUkur.getPerincianBorang5d());
            permohonanUkurTemp.setPerincianBorang5e(permohonanUkur.getPerincianBorang5e());
            permohonanUkurTemp.setPerincianPajakanLombong(permohonanUkur.getPerincianPajakanLombong());
            permohonanUkurTemp.setPerincianStrata(permohonanUkur.getPerincianStrata());
            permohonanUkurTemp.setStatusSuratanHakmilik(permohonanUkur.getStatusSuratanHakmilik());
            permohonanUkurTemp.setPemberiPengecualian(permohonanUkur.getPemberiPengecualian());
            permohonanUkurTemp.setPerengganKTN(permohonanUkur.getPerengganKTN());
            permohonanUkurTemp.setRujukanKTN(permohonanUkur.getRujukanKTN());
            permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
            permohonanUkurTemp.setJumlahBayaranUkur(permohonanUkur.getJumlahBayaranUkur());
            permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
            permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
            permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
            permohonanUkurTemp.setInfoAudit(infoAudit);
            permohonanUkurTemp.setPermohonan(permohonan);
            if (kodHakmilik != null) {
                permohonanUkurTemp.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
            }
            enforcementService.simpanPermohonanUkur(permohonanUkurTemp);
            permohonanUkur = enforcementService.findPermohonanUkurByIdPermohonan(idPermohonan);
            System.out.println("----------permohonanUkur------end-----" + permohonanUkur);
        }
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----" + idMohonUkur);
        if (idMohonUkur != null && !idMohonUkur.equals("") && !idMohonUkur.equals("0")) {
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
            PermohonanUkur maxPuPermohonanUkur = enforcementService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
                noPU = df.format(val) + "/" + (1900 + dt.getYear());

                System.out.println("-----------Seq-------------" + noPU);
                permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    permohonanUkurTemp.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    permohonanUkurTemp.setNoPermohonanUkur(nopu);
                }
            }

        }

        permohonanUkurTemp.setNoRujukanPejabatTanah(idPermohonan);
        permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
        permohonanUkurTemp.setPerincianBorang5b(permohonanUkur.getPerincianBorang5b());
        permohonanUkurTemp.setPerincianBorang5c(permohonanUkur.getPerincianBorang5c());
        permohonanUkurTemp.setPerincianBorang5d(permohonanUkur.getPerincianBorang5d());
        permohonanUkurTemp.setPerincianBorang5e(permohonanUkur.getPerincianBorang5e());
        permohonanUkurTemp.setPerincianPajakanLombong(permohonanUkur.getPerincianPajakanLombong());
        permohonanUkurTemp.setPerincianStrata(permohonanUkur.getPerincianStrata());
        permohonanUkurTemp.setStatusSuratanHakmilik(permohonanUkur.getStatusSuratanHakmilik());
        permohonanUkurTemp.setPemberiPengecualian(permohonanUkur.getPemberiPengecualian());
        permohonanUkurTemp.setPerengganKTN(permohonanUkur.getPerengganKTN());
        permohonanUkurTemp.setRujukanKTN(permohonanUkur.getRujukanKTN());
        permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
        permohonanUkurTemp.setJumlahBayaranUkur(permohonanUkur.getJumlahBayaranUkur());
        permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
        permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
        permohonanUkurTemp.setNoResitD(permohonanUkur.getNoResitD());
        permohonanUkurTemp.setTarikhResitD(permohonanUkur.getTarikhResitD());
        permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
        permohonanUkurTemp.setPengencualian(permohonanUkur.getPengencualian());
        permohonanUkurTemp.setPerengganKtn(permohonanUkur.getPerengganKtn());
        permohonanUkurTemp.setRujukanKtn(permohonanUkur.getRujukanKtn());
        permohonanUkurTemp.setCamtumanLot(permohonanUkur.getCamtumanLot());
        permohonanUkurTemp.setCamtumanLotDipecah(permohonanUkur.getCamtumanLotDipecah());
        permohonanUkurTemp.setSerahSebahagianLot(permohonanUkur.getSerahSebahagianLot());
        permohonanUkurTemp.setBermilikTanahKerajaan(permohonanUkur.getBermilikTanahKerajaan());
        permohonanUkurTemp.setBerimilikSemulaBahagian(permohonanUkur.getBerimilikSemulaBahagian());
        permohonanUkurTemp.setAmbilSebahagianLot(permohonanUkur.getAmbilSebahagianLot());
        //permohonanUkurTemp.setUkurSemulaLot(permohonanUkur.getUkurSemulaLot());
        permohonanUkurTemp.setUkurSemulaLot(noLot);
        permohonanUkurTemp.setSebabUkur(permohonanUkur.getSebabUkur());
        permohonanUkurTemp.setStatusBayaranUkur(permohonanUkur.getStatusBayaranUkur());
        permohonanUkurTemp.setPengecualianOlehLihat(permohonanUkur.getPengecualianOlehLihat());
        System.out.println("--------------------------------------->>>>>>>>>>" + permohonanUkur.getStatusBayaranUkur());
        permohonanUkurTemp.setInfoAudit(infoAudit);
        permohonanUkurTemp.setPermohonan(permohonan);
        if (kodHakmilik != null) {
            permohonanUkurTemp.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
        }

        enforcementService.simpanPermohonanUkur(permohonanUkurTemp);

        if (idPermohonan != null) {
            permohonanUkur = enforcementService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        genReport();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/pu/pu.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        System.out.println("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum membawanya ke Permohonan Ukur.";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
            LOG.info("genReportFromXML");
            genReportFromXml();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    private void genReportFromXml() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        //stageId = task.getSystemAttributes().getStage();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        LOG.info("----StageId------:" + stageId);
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            String gen = "DEVBPU_MLK.rdf";
            String prefix = "VDOC";
            String code = "PU";
            String[] params = null;
            String[] values = null;
            KodDokumen kd = kodDokumenDAO.findById(code);
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            params = new String[]{"p_id_mohon"};
            values = new String[]{idPermohonan};
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("::Path To save :: " + (dokumenPath + path));
            LOG.info("::Report Name ::" + gen);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                hakmilikPermohonan.setDokumen1(d);
            }
            if (kd.getKod().equals("DHKE")) {
                hakmilikPermohonan.setDokumen3(d);
            }
            if (kd.getKod().equals("DHDE")) {
                hakmilikPermohonan.setDokumen2(d);
            }
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }

        if (workflowId != null && stageId != null) {
            String gen = "DEVSIPU_MLK.rdf";
            String prefix = "VDOC";
            String code = "SIPU";
            String[] params = null;
            String[] values = null;
            KodDokumen kd = kodDokumenDAO.findById(code);
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            params = new String[]{"p_id_mohon"};
            values = new String[]{idPermohonan};
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("::Path To save :: " + (dokumenPath + path));
            LOG.info("::Report Name ::" + gen);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                hakmilikPermohonan.setDokumen1(d);
            }
            if (kd.getKod().equals("DHKE")) {
                hakmilikPermohonan.setDokumen3(d);
            }
            if (kd.getKod().equals("DHDE")) {
                hakmilikPermohonan.setDokumen2(d);
            }
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        //stageId = "g_penyediaan_pu_pt";
        return stageId;
    }

    //-------------------------------Transfer File To JUPEM-------------------------------------//
    public Resolution transferFile() {

        //not comment for checking
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info(" ---transferFile----idPermohonan : " + idPermohonan);
        List<Dokumen> listDoc1 = jupemService.findDokumenlist(idPermohonan, "g_penyediaan_pu");
        LOG.info(" -------Nama fizikal : " + listDoc1.size());
        for (Dokumen doc : listDoc1) {
            LOG.info(" -------Nama fizikal : " + doc.getNamaFizikal());
            LOG.info(" ------Id Dokumen : " + doc.getIdDokumen());
        }

        String msg = "";
        String error = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String idAliran = currentStageId(taskId);
        LOG.info("idAliran :" + idAliran);
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);

        // compare files
        //not comment for checking
        List<Dokumen> dokumenList = new ArrayList<Dokumen>();
        dokumenList = jupemService.findDokumenListByKodurusan(idPermohonan, permohonan.getKodUrusan().getKod());
        LOG.info("dokumenList :" + dokumenList.size());
        for (int i = 0; i < dokumenList.size(); i++) {
            LOG.info("Kod Dokumen :" + dokumenList.get(i).getKodDokumen().getKod());
        }
        List<IntegrasiDokumen> integDokumenList = new ArrayList<IntegrasiDokumen>();
        LOG.info(this.permohonan.getKodUrusan().getKod());
        //String idAliran = "g_hantar_pu";
        integDokumenList = jupemService.findIntegrasiDokumenList(permohonan.getKodUrusan().getKod(), idAliran);
        LOG.info("integDokumenList:" + integDokumenList.size());
        for (int i = 0; i < integDokumenList.size(); i++) {
            LOG.info("Kod dokumen :" + integDokumenList.get(i).getKodDokumen().getKod());
        }

        if ((integDokumenList.size() != dokumenList.size()) && "04".equals(conf.getProperty("kodNegeri"))) {
            addSimpleError("Dokumen tiada dalam senarai, sila jana dokumen terlebih dahulu..");
            return new JSP("penguatkuasaan/pu/pu.jsp").addParameter("tab", "true");
        }

//        obi = new OutBoundIntegration(permohonan, peng, taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();

        addSimpleMessage("Fail telah berjaya dihantar");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        return new JSP("penguatkuasaan/pu/pu.jsp").addParameter("tab", "true").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. " + ditundatangan);
        if (ditundatangan != null) {
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("PU"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(ditundatangan);
            enforcementService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod tandatangan telah dimasukkan");


        return new RedirectResolution(PuActionBean.class, "showForm");
    }

    public Resolution simpanSIPU() {
        String stageId = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = new PermohonanTandatanganDokumen();
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        LOG.info("stage id" + stageId);
        if (permohonan.getKodUrusan().getKod().equals("PPCB")) {
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (stageId.equals("cetaknotispemilikbersama")) {
                    tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SN2A");
                } else {
                    tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
                }
            } else {
                tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
            }
        } else {
            tandatanganDokumen1 = enforcementService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
        }
        sipuTundatangan = getContext().getRequest().getParameter("sipuTundatangan");
        LOG.info("testing.............. " + sipuTundatangan);
        if (sipuTundatangan != null) {
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                if (permohonan.getKodUrusan().getKod().equals("PPCB")) {
                    if ("05".equals(conf.getProperty("kodNegeri"))) {

                        if (stageId.equals("cetaknotispemilikbersama")) {
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SN2A"));
                        } else {
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SIPU"));
                        }
                    }
                } else {

                    tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SIPU"));
                }
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(sipuTundatangan);
            enforcementService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new JSP("penguatkuasaan/pu/sipu_tandatangan.jsp").addParameter("tab", "true");
    }

    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            // Melaka
            String query = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                query = "Select u from etanah.model.Pengguna u where u.perananUtama in ('71','75','77') and u.kodCawangan.kod = :kod order by u.nama";
            } // NS
            else if ("05".equals(conf.getProperty("kodNegeri"))) {
                query = "Select u from etanah.model.Pengguna u where u.perananUtama in ('84','12','14','10','32') and u.kodCawangan.kod = :kod and u.status.kod = 'A' order by u.nama";
            }

            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaNotis(KodCawangan kod) {
        try {
            // Melaka
            String query = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                query = "Select u from etanah.model.Pengguna u where u.perananUtama in('71','75','77') and u.kodCawangan.kod = :kod order by u.nama";
            } // NS
            else if ("05".equals(conf.getProperty("kodNegeri"))) {
                query = "Select u from etanah.model.Pengguna u where u.perananUtama in(select kp.kod from etanah.model.KodPeranan kp where kumpBPEL = 'pptd') and u.kodCawangan.kod = :kod and u.status.kod = 'A' and u.tarikhAkhirLogin is not null order by u.nama";
            }
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<NoPt> getNoPTList() {
        return noPTList;
    }

    public void setNoPTList(List<NoPt> noPTList) {
        this.noPTList = noPTList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getEdit1() {
        return edit1;
    }

    public void setEdit1(Boolean edit1) {
        this.edit1 = edit1;
    }

    public Boolean getEdit2() {
        return edit2;
    }

    public void setEdit2(Boolean edit2) {
        this.edit2 = edit2;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getSipuTundatangan() {
        return sipuTundatangan;
    }

    public void setSipuTundatangan(String sipuTundatangan) {
        this.sipuTundatangan = sipuTundatangan;
    }

    public Long getNoPTSementara() {
        return noPTSementara;
    }

    public void setNoPTSementara(Long noPTSementara) {
        this.noPTSementara = noPTSementara;
    }

    public KodCawanganJabatan getAlamat() {
        return alamat;
    }

    public void setAlamat(KodCawanganJabatan alamat) {
        this.alamat = alamat;
    }

    public HakmilikPermohonan getHakmilikMukim() {
        return hakmilikMukim;
    }

    public void setHakmilikMukim(HakmilikPermohonan hakmilikMukim) {
        this.hakmilikMukim = hakmilikMukim;
    }

    public PermohonanPlotPelan getMpp() {
        return mpp;
    }

    public void setMpp(PermohonanPlotPelan mpp) {
        this.mpp = mpp;
    }

    public List getNoPtList2() {
        return noPtList2;
    }

    public void setNoPtList2(List noPtList2) {
        this.noPtList2 = noPtList2;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanPlotPelan> getMppMelaka() {
        return mppMelaka;
    }

    public void setMppMelaka(List<PermohonanPlotPelan> mppMelaka) {
        this.mppMelaka = mppMelaka;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getLuasKeseluruhan() {
        return luasKeseluruhan;
    }

    public void setLuasKeseluruhan(BigDecimal luasKeseluruhan) {
        this.luasKeseluruhan = luasKeseluruhan;
    }

    public String getKodKeseluruhan() {
        return kodKeseluruhan;
    }

    public void setKodKeseluruhan(String kodKeseluruhan) {
        this.kodKeseluruhan = kodKeseluruhan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
}
