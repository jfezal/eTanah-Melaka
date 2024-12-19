/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import etanah.model.KandunganFolder;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.UrusanDokumen;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorIdPermohonanKelompok;
import etanah.service.KodService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisCarianBayaran;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakWakil;
import etanah.model.Pihak;
import etanah.model.SalinanKepada;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanService;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.utility.UtilitiSalinanActionBean;
//import etanah.view.strata.PenarikanBalikActionBean;
//import etanah.view.stripes.pelupusan.disClass.DisCarianFasa;
import java.text.ParseException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khairul.hazwan refer afham
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/utilitiSalinanDev")
public class UtilitiSalinanKepadaDevActionBean extends AbleActionBean {
    
    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UtilitiSalinanKepadaDevActionBean.class);
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private SalinanKepada salinanKepada;
    private List<SalinanKepada> listSalinanKepada;
    private List<KandunganFolder> senaraiKandungan;
    private String idPermohonan;
    private String kodNegeri;
    private String kodDokumen;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/pembangunan/utiliti/utiliti_salinan_surat.jsp");
    }

    public Resolution showFormSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (idPermohonan != null && kodDokumen != null) {
            listSalinanKepada = new ArrayList<SalinanKepada>();
            listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
        }
        return new JSP("pembangunan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showFormTambahSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        salinanKepada = new SalinanKepada();
        return new JSP("pembangunan/utiliti/utiliti_salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution showFormEditSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        String idSalinan = getContext().getRequest().getParameter("idSalinan");

        salinanKepada = new SalinanKepada();
        salinanKepada = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
        return new JSP("pembangunan/utiliti/utiliti_salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution findKandungan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen == null) {
                    addSimpleError("Tiada senarai dokumen dalam permohonan ini");
                } else {
                    senaraiKandungan = disLaporanTanahService.getPelupusanService().getListDokumenSurat(folderDokumen.getFolderId());
                }
            } else {
                addSimpleError("Permohonan tidak dijumpai");
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_salinan_surat.jsp");

    }

    public Resolution simpanSalinan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = (String) getContext().getRequest().getParameter("kodDokumen");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                SalinanKepada salinanTemp = new SalinanKepada();
                String idSalinan = (String) getContext().getRequest().getParameter("salinanKepada.idSalinanKepada");
                if (idSalinan != null && !idSalinan.equals("0")) {
                    salinanTemp = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
                    infoAudit = salinanTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                salinanTemp.setInfoAudit(infoAudit);
                salinanTemp.setNama(salinanKepada.getNama());
                salinanTemp.setAlamat1(salinanKepada.getAlamat1());
                salinanTemp.setAlamat2(salinanKepada.getAlamat2());
                salinanTemp.setAlamat3(salinanKepada.getAlamat3());
                salinanTemp.setAlamat4(salinanKepada.getAlamat4());
                salinanTemp.setPoskod(salinanKepada.getPoskod());
                salinanTemp.setNegeri(salinanKepada.getNegeri());
                salinanTemp.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
                salinanTemp.setPermohonan(permohonan);
                salinanTemp.setCawangan(permohonan.getCawangan() != null ? permohonan.getCawangan() : null);

                disLaporanTanahService.getPelupusanService().kemaskiniSalian(salinanTemp);

            }

            if (idPermohonan != null && kodDokumen != null) {
                listSalinanKepada = new ArrayList<SalinanKepada>();
                listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
            }
//            showFormSalinan();
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", true);

    }
    
    public Resolution deleteRow() throws ParseException {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKandungan != null && tName != null) {
            forwardJSP = disLaporanTanahService.delObject(tName, new String[]{idKandungan}, typeSyor);
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new JSP("/WEB-INF/jsp/pembangunan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", "true");
    }
    
    //Jana Dokumen
    public Resolution janaDokumen() {
        LOG.info("showForm");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/dev_laporan_salinanKepada.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
         senaraiReport = new String[]{
                    //PPCB,PPCS,PYTN
                    "Surat Minta Ulasan Adun", //SUA
                    "Surat Minta Ulasan Teknikal", //SUT
                    "Surat Lampiran JKBB (PTD)", //JKBBS
                    "Surat Keputusan JKBB (PTG)", //SJKBB
                    "Surat Lulus Minta Sedia Pelan Prahitung", //SLPH
                    "Surat Kelulusan Sedia Pra Hitung", //SMD
                    
                    //TSKKT,TSPSN,TSKSN,TSBSN
                    "Surat Nilaian Tanah JPPH", //SJPPH
                    "Surat Kelulusan Syarat Baru dan Pengesahan Bayaran Premium", //SLSB
                    "Surat 7G", //SRT7G
                    "Surat Pembatalan Selepas 3 Bulan", //SBTL
                    "Surat Peringatan 2 Minggu Sebelum Tamat Tempoh 90 hari", //SPER
                    
                    //PPK
                    "Surat Keputusan (PPK)", //SLPK
                    "Surat Endors (PPK)", //SIPK
                    
                    //PSBT,PSMT
                    "Surat Keputusan Penyerahan Semua Tanah (PSMT)", //SPSMT
                    "Surat Keputusan Penyerahan Sebahagian Tanah (PSBT)", //SPSBT
                    "Surat Pengecualian Upah Ukur(PTD)", //SBUUD
                    "Surat Iringan Pengecualian Bayaran Ukur", //SIPBU
                    
                    //RBPA
                    "Surat Kelulusan (RBPA)", //SRBPA
                    
                    //RNS
                    "Surat Permohonan (RNS)", //SPRNS
                    "Surat Kelulusan (RNS)(PTG Ke PTD)", //SRNSG
                    "Surat Penolakan (RNS) PTG", //STRNG
                    "Surat Kelulusan (RNS) (PTD Kepada Pemohon)", //SRNSD
                    "Surat Penolakan (RNS)", //STRNS
                    
                    //RPP
                    "Surat Kelulusan (RPP) (PTG Ke PTD)", //SRPPG
                    "Surat Kelulusan (RPP) (PTD Kepada Pemohon)", //SRPPD
                    "Surat Penolakan (RPP,RLTB,RPMMK)", //STLK
                    
                    //RLTB
                    "Surat Kelulusan (RLTB)", //SRLTB
                    
                    //RPMMK
                    "Surat Keputusan (RPMMK)", //KPRPM
                    "Surat Kelulusan (RPMMK)", //SLLS
                    
                    //PBSK
                    "Surat Keputusan (PBSK)", //KPPBS
                    "Surat Kelulusan (PBSK)", //SLPBS
                    "Surat Tolak (PBSK)", //STPBS
                    
                    //TSPSS                 
                    //SBMS                    
                    //PPT
                    //PBTL
                    "Surat Arahan Pembatalan (PBTL)", //SMBTL
                    "Surat Pembatalan (PBTL)", //SPBTL
                    
                    //RTLK
               
                   };

        senaraiReportName = new String[]{
                    //PPCB,PPCS,PYTN
                    "DEVSMUYB_MLK.rdf",
                    "DEVSUT_MLK.rdf",
                    "DEVSRJKBBPTD_MLK.rdf",
                    "DEVSKpsnJKBB_MLK.rdf",
                    "DEVSrtLPra_MLK.rdf",
                    "DEVSMD_V2_MLK.rdf", //DEVSMD_MLK.rdf
                    
                    //TSKKT,TSPSN,TSKSN,TSBSN
                    "DEVSJPPH2_MLK.rdf",
                    "DEVSLA_MLK.rdf",
                    "DEVS7G_MLK.rdf",
                    "DEVSB_MLK.rdf",
                    "DEVSP_MLK.rdf",
                    
                    //PPK
                    "DEV_SrtKpsnPPK_MLK.rdf",
                    "DEV_SrtEndosPPK_MLK.rdf",
                    
                    //PSBT,PSMT
                    "DEVSrtKpsnPSMT_MLK.rdf",
                    "DEVSrtKpsnPSBT_MLK.rdf",
                    "DEVSBUU_MLK.rdf",
                    "DEVSIBUU_MLK.rdf",
                    
                    //RBPA
                    "DEVSrtLulusRBPA_MLK.rdf",
                    
                    //RNS
                    "DEVSrtRNS_MLK.rdf",
                    "DEVSrtKpsnRNSPTG_MLK.rdf",
                    "DEVSrtTlkRNSPTG_MLK.rdf",
                    "DEVSrtKpsnRNSPTD_MLK.rdf",
                    "DEVSrtTolakRNS_MLK.rdf",
                    
                    //RPP
                    "DEVSrtKpsnRPPPTG_MLK.rdf",
                    "DEVSrtKpsnRPPPTD_MLK.rdf",
                    "DEVSrtTolak_MLK.rdf",
                    
                    //RLTB
                    "DEVSrtLulusRLTB_MLK.rdf",
                    
                    //RPMMK
                    "DEVSrtKpsnRPMMK_MLK.rdf",
                    "DEVSrtLulus_MLK.rdf",
                    
                    //PBSK
                    "DEVSrtKpsnPBSK_MLK.rdf",
                    "DEVSrtLulusPBSK_MLK.rdf",
                    "DEVSrtTolakPBSK_MLK.rdf",
                    
                    //TSPSS                 
                    //SBMS                   
                    //PPT
                    //PBTL
                    "DEVSrtArahBatal_MLK.rdf",
                    "DEVSrtPBTL_MLK.rdf",
                    
                    //RTLK
                    
                    };
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        return new JSP("/pembangunan/utiliti/dev_laporan_param_salinanKepada.jsp").addParameter("popup", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public SalinanKepada getSalinanKepada() {
        return salinanKepada;
    }

    public void setSalinanKepada(SalinanKepada salinanKepada) {
        this.salinanKepada = salinanKepada;
    }

    public List<SalinanKepada> getListSalinanKepada() {
        return listSalinanKepada;
    }

    public void setListSalinanKepada(List<SalinanKepada> listSalinanKepada) {
        this.listSalinanKepada = listSalinanKepada;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }   

    public String[] getSenaraiReport() {
        return senaraiReport;
    }

    public void setSenaraiReport(String[] senaraiReport) {
        this.senaraiReport = senaraiReport;
    }

    public String[] getSenaraiReportName() {
        return senaraiReportName;
    }

    public void setSenaraiReportName(String[] senaraiReportName) {
        this.senaraiReportName = senaraiReportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}

