package etanah.view.dokumen;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;

import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.lowagie.text.*;
import java.io.*;
import com.lowagie.text.pdf.*;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPerserahanPaksa;
import etanah.service.CalcTax;
import etanah.service.RegService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import etanah.util.FileUtil;
import etanah.util.WORMUtil;
import etanah.view.ListUtil;
import etanah.view.etanahContextListener;
import etanah.view.utility.PdfImageUtil;
import java.awt.Color;
import java.util.*;
//import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPembetulanUrusanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.Hakmilik;
import etanah.model.AlamatSurat;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodBangsa;
import etanah.model.KodJabatan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodPerintah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.model.PermohonanPembetulanUrusan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.model.PihakPengarah;
import etanah.sequence.GeneratorIdPerserahanPaksa;
import etanah.service.CalcTax;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.ListUtil;
import etanah.view.daftar.MohonRujLuar;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.Transaksi;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;

/**
 * Class for viewing document. This request MUST NOT be decorated by Wazer.
 *
 * @author wazer
 *
 */
@HttpCache(allow = true)
@UrlBinding("/dokumen/view/SemakTTActionBean")
public class SemakTTActionBean extends AbleActionBean {

    @Inject
    DokumenService dokumenService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    GeneratorIdPerserahanPaksa generatorIdPerserahanPaksa;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarService service; // mohon_ruj_luar service
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanService;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodBandarPekanMukimDAO kodBpmDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikPihakBerkepentinganDAO hpbDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KandunganFolderService kandunganFolderService;

    private Dokumen dokumen;
    private long idDokumen;
    private Long idFol;
    private FolderDokumen idFolder;
    private KandunganFolder idFolderDok;
    private Permohonan pemohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private List<Dokumen> senaraiDokumen;
    private HakmilikSebelumPermohonan hakmilikSebelumPermohonan;
    private HakmilikAsalPermohonan hakmilikAsalPermohonan;
    private List<HakmilikSebelumPermohonan> senaraiHakmilikSblm;
    private List<Hakmilik> SenaraihakmilikBlmTTDE;
    private List<Hakmilik> SenaraihakmilikBlmTTKE;
    private List<Hakmilik> SenaraihakmilikBlmTTB1;
    private List<Hakmilik> SenaraihakmilikBlmTTB2KE;
    private List<Hakmilik> SenaraihakmilikBlmTTB2DE;
    private List<HakmilikAsalPermohonan> senaraiHakmilikAsal;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<Permohonan> senaraiPermohonanBerkuatKuasa;

//    private static final Logger LOGGER = Logger.getLogger(SemakTT.class);
    private static final Logger LOGGER = Logger.getLogger(ViewDocumentAction.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    public Resolution checkTtangan() {
        idPermohonan = (String) context.getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            senaraiHakmilikPermohonan = hakmilikpermohonanService.findByIdPermohonanOnly(idPermohonan);
            pemohonan = permohonanDAO.findById(idPermohonan);
            if (pemohonan.getKodUrusan().getKodPerserahan().equals("MH")) {
                perserahanMH();
            } else if (pemohonan.getKodUrusan().getKodPerserahan().equals("SC") || pemohonan.getKodUrusan().getKodPerserahan().equals("B") || pemohonan.getKodUrusan().getKodPerserahan().equals("N")) {
                PerserahanBiasa();
            }
            SenaraihakmilikBlmTTKE = new ArrayList<Hakmilik>();
            SenaraihakmilikBlmTTDE = new ArrayList<Hakmilik>();
            SenaraihakmilikBlmTTB1 = new ArrayList<Hakmilik>();
            SenaraihakmilikBlmTTB2KE = new ArrayList<Hakmilik>();
            SenaraihakmilikBlmTTB2DE = new ArrayList<Hakmilik>();
            for (HakmilikPermohonan hakmilikP : senaraiHakmilikPermohonan) {
                hakmilikPermohonan = hakmilikpermohonanService.findHakmilikPermohonan(hakmilikP.getHakmilik().getIdHakmilik(), idPermohonan);
                List<KandunganFolder> senaraiKF = kandunganFolderService.findByIdFolder(hakmilikPermohonan.getPermohonan().getFolderDokumen());
                LOGGER.info(senaraiKF.size());  
                for (KandunganFolder kandF : senaraiKF) {
                    senaraiDokumen = new ArrayList<Dokumen>();
                    senaraiDokumen.add(kandF.getDokumen());
                    idDokumen = kandF.getDokumen().getIdDokumen();
                    dokumen = dokumenService.findById(idDokumen);
                    LOGGER.info("Nama dokumen " + dokumen.getKodDokumen().getNama());
                    String kodDokumen = dokumen.getKodDokumen().getKod();
                    if (kodDokumen.equals("DHKE")) {
                        String docPath = conf.getProperty("document.path");
                        String namaFizikalAsal = dokumen.getNamaFizikal();
                        File DHKE = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                + namaFizikalAsal);
                        if (DHKE != null) {
                            File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                    + namaFizikalAsal + ".sig");
                            if (sign.exists()) {
                                String filename = hakmilikPermohonan.getHakmilik().getIdHakmilik() + ".sig";
                            } else if (!sign.exists()) {
                                
                                SenaraihakmilikBlmTTKE.add(hakmilikP.getHakmilik());
                                LOGGER.info("SenaraihakmilikBlmTTKE" + SenaraihakmilikBlmTTKE.size());
                            }
                        }
                    } else if (kodDokumen.equals("DHDE")) {
                        String docPath = conf.getProperty("document.path");
                        String namaFizikalAsal = dokumen.getNamaFizikal();
                        File DHDE = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                + namaFizikalAsal);
                        if (DHDE != null) {
                            File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                    + namaFizikalAsal + ".sig");
                            if (sign.exists()) {
                                String filename = hakmilikPermohonan.getHakmilik().getIdHakmilik() + ".sig";
                            } else if (!sign.exists()) {
                                
                                SenaraihakmilikBlmTTDE.add(hakmilikP.getHakmilik());
                                LOGGER.info("SenaraihakmilikBlmTTDE" + SenaraihakmilikBlmTTDE.size());
                            }
                        }
                    } else if (kodDokumen.equals("PB1KE")) {
                        String docPath = conf.getProperty("document.path");
                        String namaFizikalAsal = dokumen.getNamaFizikal();
                        File PB1KE = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                + namaFizikalAsal);
                        if (PB1KE != null) {
                            File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                    + namaFizikalAsal + ".sig");
                            if (sign.exists()) {
                                String filename = hakmilikPermohonan.getHakmilik().getIdHakmilik() + ".sig";
                            } else if (!sign.exists()) {
                                
                                SenaraihakmilikBlmTTB1.add(hakmilikP.getHakmilik());
                            }
                        }
                    } else if (kodDokumen.equals("PB2KE")) {
                        String docPath = conf.getProperty("document.path");
                        String namaFizikalAsal = dokumen.getNamaFizikal();
                        File PB2 = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                + namaFizikalAsal);
                        if (PB2 != null) {
                            File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                    + namaFizikalAsal + ".sig");
                            if (sign.exists()) {
                                String filename = hakmilikPermohonan.getHakmilik().getIdHakmilik() + ".sig";
                            } else if (!sign.exists()) {
                                
                                SenaraihakmilikBlmTTB2KE.add(hakmilikP.getHakmilik());
                                LOGGER.info("SenaraihakmilikBlmTTB2KE" + SenaraihakmilikBlmTTB2KE.size());
                            }
                        }
                    } else if (kodDokumen.equals("PB2DE")) {
                        String docPath = conf.getProperty("document.path");
                        String namaFizikalAsal = dokumen.getNamaFizikal();
                        File PB2DE = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                + namaFizikalAsal);
                        if (PB2DE != null) {
                            File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                                    + namaFizikalAsal + ".sig");
                            if (sign.exists()) {
                                String filename = hakmilikPermohonan.getHakmilik().getIdHakmilik() + ".sig";
                            } else if (!sign.exists()) {
                                
                                SenaraihakmilikBlmTTB2DE.add(hakmilikP.getHakmilik());
                                LOGGER.info("SenaraihakmilikBlmTTB2DE" + SenaraihakmilikBlmTTB2DE.size());
                            }
                        }
                    }
                }
            }
        }
        return new JSP("dokumen/semak_tandatangan.jsp").addParameter("popup", "true");
    }

    private void perserahanMH() {
        String idHakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
        if (idHakmilik != null) {
            senaraiHakmilikSblm = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
            if (senaraiHakmilikSblm.size() > 0) {
                for (HakmilikSebelumPermohonan hmSblm : senaraiHakmilikSblm) {
                    String idHmSblm = hmSblm.getHakmilikSejarah();
                    List<HakmilikPermohonan> senaraiHakmilikPermohonan = pService.findByIdHakmilikOnly(idHmSblm);
                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                        if (hp.getPermohonan().getStatus().equals("AK")) {
                            senaraiPermohonanBerkuatKuasa = new ArrayList<Permohonan>();
                            senaraiPermohonanBerkuatKuasa.add(hp.getPermohonan());
                        }
                    }
                }
            }
            if (senaraiHakmilikSblm.size() < 0) {
                senaraiHakmilikAsal = regService.findIdHakmilikAsalMohonByIdHakmilik(idHakmilik);
                if (senaraiHakmilikAsal.size() > 0) {
                    for (HakmilikAsalPermohonan hmAsal : senaraiHakmilikAsal) {
                        String idHmAsal = hmAsal.getHakmilikSejarah();
                        List<HakmilikPermohonan> senaraiHakmilikPermohonan = pService.findByIdHakmilikOnly(idHmAsal);
                        for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                            if (hp.getPermohonan().getStatus().equals("AK")) {
                                senaraiPermohonanBerkuatKuasa = new ArrayList<Permohonan>();
                                senaraiPermohonanBerkuatKuasa.add(hp.getPermohonan());
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution PerserahanBiasa() {
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikpermohonanService.findByIdPermohonan(pemohonan.getIdPermohonan());
        for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan){
            List<Permohonan> perserahanBelumDaftar = permohonanService.findByIdmohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
            for(Permohonan pbd: perserahanBelumDaftar){
                if (pbd.getStatus().equals("SL")){
                    
                }
            }
        }
        return null;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long id) {
        this.idDokumen = id;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Long getIdFol() {
        return idFol;
    }

    public void setIdFol(Long idFol) {
        this.idFol = idFol;
    }

    public FolderDokumen getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(FolderDokumen idFolder) {
        this.idFolder = idFolder;
    }

    public KandunganFolder getIdFolderDok() {
        return idFolderDok;
    }

    public void setIdFolderDok(KandunganFolder idFolderDok) {
        this.idFolderDok = idFolderDok;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikSblm() {
        return senaraiHakmilikSblm;
    }

    public void setSenaraiHakmilikSblm(List<HakmilikSebelumPermohonan> senaraiHakmilikSblm) {
        this.senaraiHakmilikSblm = senaraiHakmilikSblm;
    }

    public List<Hakmilik> getSenaraihakmilikBlmTTDE() {
        return SenaraihakmilikBlmTTDE;
    }

    public void setSenaraihakmilikBlmTTDE(List<Hakmilik> SenaraihakmilikBlmTTDE) {
        this.SenaraihakmilikBlmTTDE = SenaraihakmilikBlmTTDE;
    }

    public List<Hakmilik> getSenaraihakmilikBlmTTKE() {
        return SenaraihakmilikBlmTTKE;
    }

    public void setSenaraihakmilikBlmTTKE(List<Hakmilik> SenaraihakmilikBlmTTKE) {
        this.SenaraihakmilikBlmTTKE = SenaraihakmilikBlmTTKE;
    }

    public List<Hakmilik> getSenaraihakmilikBlmTTB1() {
        return SenaraihakmilikBlmTTB1;
    }

    public void setSenaraihakmilikBlmTTB1(List<Hakmilik> SenaraihakmilikBlmTTB1) {
        this.SenaraihakmilikBlmTTB1 = SenaraihakmilikBlmTTB1;
    }

    public List<Hakmilik> getSenaraihakmilikBlmTTB2KE() {
        return SenaraihakmilikBlmTTB2KE;
    }

    public void setSenaraihakmilikBlmTTB2KE(List<Hakmilik> SenaraihakmilikBlmTTB2KE) {
        this.SenaraihakmilikBlmTTB2KE = SenaraihakmilikBlmTTB2KE;
    }

    public List<HakmilikAsalPermohonan> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(List<HakmilikAsalPermohonan> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<Permohonan> getSenaraiPermohonanBerkuatKuasa() {
        return senaraiPermohonanBerkuatKuasa;
    }

    public void setSenaraiPermohonanBerkuatKuasa(List<Permohonan> senaraiPermohonanBerkuatKuasa) {
        this.senaraiPermohonanBerkuatKuasa = senaraiPermohonanBerkuatKuasa;
    }

    public List<Hakmilik> getSenaraihakmilikBlmTTB2DE() {
        return SenaraihakmilikBlmTTB2DE;
    }

    public void setSenaraihakmilikBlmTTB2DE(List<Hakmilik> SenaraihakmilikBlmTTB2DE) {
        this.SenaraihakmilikBlmTTB2DE = SenaraihakmilikBlmTTB2DE;
    }
    
}
