/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.CarianHakmilikDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganJabatanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.*;
import etanah.service.common.CarianHakmilikService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PihakService;
import etanah.service.daftar.KutipanDataService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.DokumenValue;
import etanah.view.kaunter.UrusanValue;
import java.io.File;
import java.io.Serializable;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;

/**
 *
 *
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1"})
@UrlBinding("/daftar/carian_strata")
public class CarianStrataAction extends AbleActionBean {

    @Inject
    private KaunterService kaunterService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    private DokumenService dokumenService;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private PermohonanCarianDAO permohonanCarianDAO;
    @Inject
    private CarianHakmilikDAO carianHakmilikDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private List<CaraBayaran> caraBayaranList = new ArrayList<CaraBayaran>();
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private ReportName reportName;
    //Step 3a DAO and services
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private JUBLDAO jUBLDAO;
    @Inject
    private KodAgensiDAO kodAgensiDAO;
    @Inject
    private KodJabatanDAO kodJabatanDAO;
    @Inject
    private KodCawanganJabatanDAO kodCawanganJabatanDAO;
    @Inject
    private PihakService pihakService;
    @Inject
    private CarianHakmilikService carianHakmilikService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KutipanDataService kutipanDataService;
    @Inject
    StrataPtService strataPtService;
    @Inject
    private RegService regService;
    //End of Step 3a DAO and services
    private ArrayList<UrusanValue> senaraiUrusan = new ArrayList<UrusanValue>();
    private List<Hakmilik> hakmilikPermohonan = new ArrayList<Hakmilik>();
    private ArrayList<String> idHakmilikSiriDari = new ArrayList<String>();
    private ArrayList<String> idHakmilikSiriKe = new ArrayList<String>();
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    private List<KandunganFolder> senaraiKandunganFolder = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandunganFolder2 = new ArrayList<KandunganFolder>();
    private List<Hakmilik> listHakmilikStrata = new ArrayList<Hakmilik>();
    private List<Hakmilik> listhakmilikPermohonan = new ArrayList<Hakmilik>();
    private List<Hakmilik> listHakmilikStrataversi1 = new ArrayList<Hakmilik>();
    private List<Hakmilik> listHakmilikStrataProv = new ArrayList<Hakmilik>();
    private List<Hakmilik> listHakmilikStrataDaftar = new ArrayList<Hakmilik>();
    private List<Dokumen> senaraiDokumen;
    private String jenisCarian;
    // information on Penyerah (Step3a)
    private String idPenyerah;
    private String urlReport;
    private ArrayList<String> senaraiUrlReport = new ArrayList<String>();
    private KodPenyerah penyerahKod;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private KodNegeri penyerahNegeri;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String kodNeg;
    private String namaPemohon;
    private String kodSerah;
    private String urusan;
    private String idFolder;
    private String idHakmilikInduk;
    private String idHakmilik;
    public String selectedTab = "0";
    private int kumpHm;
    private List<HakmilikUrusan> listHakmilikUrusanSC;
    private List<HakmilikUrusan> listHakmilikUrusanB;
    private List<HakmilikUrusan> listHakmilikUrusanN;
    private Hakmilik hakmilik;
    private Hakmilik hakmilikStrata;
    private String kodDaerah;
    private String kodSyarat;
    private String kodSekatan;
    private String kodKatTanah;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<HakmilikPihakBerkepentingan> listHakmilikPihak = new ArrayList();
    private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
    private List<HakmilikSebelum> listHakmilikSblm = new ArrayList();
    private List<KodSeksyen> listKodSeksyen;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<Hakmilik> listSenaraiHakmilik = new ArrayList();
    private static String KEMASKINI_TAB = "daftar/utiliti/kemaskini_tab.jsp";
    private String noLot;
    private String pegangan;
    private String kodSeksyen;
    private int kodBPM;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public int getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(int kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<Hakmilik> getListSenaraiHakmilik() {
        return listSenaraiHakmilik;
    }

    public void setListSenaraiHakmilik(List<Hakmilik> listSenaraiHakmilik) {
        this.listSenaraiHakmilik = listSenaraiHakmilik;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public List<HakmilikAsal> getListHakmilikAsal() {
        return listHakmilikAsal;
    }

    public void setListHakmilikAsal(List<HakmilikAsal> listHakmilikAsal) {
        this.listHakmilikAsal = listHakmilikAsal;
    }

    public List<HakmilikSebelum> getListHakmilikSblm() {
        return listHakmilikSblm;
    }

    public void setListHakmilikSblm(List<HakmilikSebelum> listHakmilikSblm) {
        this.listHakmilikSblm = listHakmilikSblm;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    public String getKodSyarat() {
        return kodSyarat;
    }

    public void setKodSyarat(String kodSyarat) {
        this.kodSyarat = kodSyarat;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikUrusan> getListHakmilikUrusanSC() {
        return listHakmilikUrusanSC;
    }

    public void setListHakmilikUrusanSC(List<HakmilikUrusan> listHakmilikUrusanSC) {
        this.listHakmilikUrusanSC = listHakmilikUrusanSC;
    }

    public List<HakmilikUrusan> getListHakmilikUrusanB() {
        return listHakmilikUrusanB;
    }

    public void setListHakmilikUrusanB(List<HakmilikUrusan> listHakmilikUrusanB) {
        this.listHakmilikUrusanB = listHakmilikUrusanB;
    }

    public List<HakmilikUrusan> getListHakmilikUrusanN() {
        return listHakmilikUrusanN;
    }

    public void setListHakmilikUrusanN(List<HakmilikUrusan> listHakmilikUrusanN) {
        this.listHakmilikUrusanN = listHakmilikUrusanN;
    }

    public int getKumpHm() {
        return kumpHm;
    }

    public void setKumpHm(int kumpHm) {
        this.kumpHm = kumpHm;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Hakmilik> getListHakmilikStrata() {
        return listHakmilikStrata;
    }

    public void setListHakmilikStrata(List<Hakmilik> listHakmilikStrata) {
        this.listHakmilikStrata = listHakmilikStrata;
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public List<KandunganFolder> getSenaraiKandunganFolder2() {
        return senaraiKandunganFolder2;
    }

    public void setSenaraiKandunganFolder2(List<KandunganFolder> senaraiKandunganFolder2) {
        this.senaraiKandunganFolder2 = senaraiKandunganFolder2;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public KodPenyerah getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(KodPenyerah penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }
    // End of information on Penyerah (Step3a)

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getUrlReport() {
        return urlReport;
    }

    public void setUrlReport(String urlReport) {
        this.urlReport = urlReport;
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<Hakmilik> getListHakmilikStrataDaftar() {
        return listHakmilikStrataDaftar;
    }

    public void setListHakmilikStrataDaftar(List<Hakmilik> listHakmilikStrataDaftar) {
        this.listHakmilikStrataDaftar = listHakmilikStrataDaftar;
    }
    private int bilHakmilik = 5;
    private int selectedItem = -1;
    private static final Logger LOG = Logger.getLogger(CarianStrataAction.class);
    private static final boolean debug = LOG.isDebugEnabled();
    //crhmt, crhmr, crhmb, cab
    public static String[] URUSAN_TERLIBAT = {
        "CRHMR",
        "CRHMT",
        "CRHMB",
        "SSHMA",
        "CSBB"
    };
    public static String[] Report_Name = {
        "UTILITIB2K_MLK.rdf",
        "UTILITIB3K_MLK.rdf",
        "UTILITIBSKDHDK_MLK.rdf",
        "UTILITIBSKDHKK_MLK.rdf",
        "UTILITIPSK_MLK.rdf",
        "UTILITIB4KDHDK_MLK.rdf",
        "UTILITIB4KDHKK_MLK.rdf",
        "UTILITIB4AKDHDK_MLK.rdf",
        "UTILITIB4AKDHKK_MLK.rdf",};
    public static String[] Report_NameNS = {
        "UTILITIB2K_NS.rdf",
        "UTILITIB3K_NS.rdf",
        "UTILITIBSKDHDK_NS.rdf",
        "UTILITIBSKDHKK_NS.rdf",
        "UTILITIPSK_NS.rdf",
        "UTILITIB4KDHDK_NS.rdf",
        "UTILITIB4KDHKK_NS.rdf",
        "UTILITIB4AKDHDK_NS.rdf",
        "UTILITIB4AKDHKK_NS.rdf",};
    public static String[] Report_Label = {
        "2K",
        "3K",
        "BSKDK",
        "BSKKK",
        "PSK",
        "4KDHD",
        "4KDHK",
        "4AKDH",
        "4AKKK",};
    //crhmt, crhmr, crhmb, cab

    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiUrusan;
        ArrayList<DokumenValue> senaraiDokumenSerahan;
        ArrayList<DokumenValue> senaraiDokumenTamb;
        ArrayList<CarianHakmilik> hakmilikPermohonan;
        ArrayList<CarianHakmilik> perserahanPermohonan;
        ArrayList<String> idHakmilikSiriDari;
        ArrayList<String> idHakmilikSiriKe;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public List<KodUrusan> getPilihanKodUrusan() {

        //QC - hanya 3 urusan shj = crhmr, crhmt, crhmb
        List<KodUrusan> senaraiUrusan = new ArrayList<KodUrusan>();

        List<KodUrusan> senarai = kaunterService.findCarianUrusan(jenisCarian);
        for (KodUrusan kodUrusan : senarai) {
            if (ArrayUtils.contains(URUSAN_TERLIBAT, kodUrusan.getKod())) {
                senaraiUrusan.add(kodUrusan);
            }
        }

        //Added by Aizuddin to add Salinan Sah to Carian Tanpa Bayaran

        /*       List<KodUrusan> senarai2 = kaunterService.findCarianUrusan(jenisCarian);
        for (KodUrusan kodUrusan2 : senarai2) {
        if (ArrayUtils.contains(URUSAN_TERLIBAT, kodUrusan2.getKod())) {
        senaraiUrusan.add(kodUrusan2);
        }
        } */
        return senaraiUrusan;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(ArrayList<UrusanValue> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public ArrayList<UrusanValue> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<UrusanValue> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getJenisCarian() {
        return jenisCarian;
    }

    public void setJenisCarian(String jenisCarian) {
        this.jenisCarian = jenisCarian;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bil) {
        this.bilHakmilik = bil;
    }

    public ArrayList<String> getIdHakmilikSiriDari() {
        return idHakmilikSiriDari;
    }

    public void setIdHakmilikSiriDari(ArrayList<String> idHakmilikSiriDari) {
        this.idHakmilikSiriDari = idHakmilikSiriDari;
    }

    public ArrayList<String> getIdHakmilikSiriKe() {
        return idHakmilikSiriKe;
    }

    public void setIdHakmilikSiriKe(ArrayList<String> idHakmilikSiriKe) {
        this.idHakmilikSiriKe = idHakmilikSiriKe;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<Hakmilik> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public List<CaraBayaran> getCaraBayaranList() {
        return caraBayaranList;
    }

    public void setCaraBayaranList(List<CaraBayaran> caraBayaranList) {
        this.caraBayaranList = caraBayaranList;
    }

    public List<KandunganFolder> getSenaraiKandunganFolder() {
        return senaraiKandunganFolder;
    }

    public void setSenaraiKandunganFolder(List<KandunganFolder> senaraiKandunganFolder) {
        this.senaraiKandunganFolder = senaraiKandunganFolder;
    }

    public ArrayList<String> getSenaraiUrlReport() {
        return senaraiUrlReport;
    }

    public void setSenaraiUrlReport(ArrayList<String> senaraiUrlReport) {
        this.senaraiUrlReport = senaraiUrlReport;
    }

    public Hakmilik getHakmilikStrata() {
        return hakmilikStrata;
    }

    public List<Hakmilik> getListHakmilikStrataversi1() {
        return listHakmilikStrataversi1;
    }

    public void setListHakmilikStrataversi1(List<Hakmilik> listHakmilikStrataversi1) {
        this.listHakmilikStrataversi1 = listHakmilikStrataversi1;
    }

    public void setHakmilikStrata(Hakmilik hakmilikStrata) {
        this.hakmilikStrata = hakmilikStrata;
    }

    public String getKodNeg() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "n9";
        }
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNeg = "n9";
        }
        this.kodNeg = kodNeg;
    }

    /* @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
    getContext().getRequest().setAttribute("tanpaBayaran", true);
    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
    }*/
    @HandlesEvent("Step1")
    @DefaultHandler
    @DontValidate
    public Resolution selectTitles() {
        getContext().getRequest().setAttribute("tanpaBayaran", true);
        getContext().getRequest().setAttribute("janaSemua", false);
        // reset senaraiHakmilik
        if (hakmilikPermohonan.isEmpty()) {
            for (int i = 0; i < bilHakmilik; i++) {
                Hakmilik h = new Hakmilik();
                hakmilikPermohonan.add(h);
            }

        }
        idHakmilikInduk = "";
        pegangan = "";
        hakmilikPermohonan.clear();
        listHakmilikStrataProv.clear();
        bilHakmilik = 5;
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");

    }

    public Resolution cariHakmilikStrata() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        listHakmilikStrataProv.clear();

        if (StringUtils.isNotBlank(idHakmilikInduk)) {
            LOG.info("id hakmilik induk " + idHakmilikInduk);
//            listHakmilikStrata = hakmilikService.getIDHakmilikByInduk(idHakmilikInduk); findIdHakmilikByIdHakmilikIndukversi0
            listHakmilikStrata = strataPtService.findIdHakmilikByIdHakmilikIndukversi0(idHakmilikInduk);
            listHakmilikStrataversi1 = strataPtService.findIdHakmilikByIdHakmilikIndukversi1(idHakmilikInduk);
            List<String> nobgnn = strataPtService.findNoBangunanProv(idHakmilikInduk);

            for (int i = 0; i < nobgnn.size(); i++) {
                String nobgn = nobgnn.get(i);
                List<Hakmilik> hmbngn = strataPtService.findHm(idHakmilikInduk, nobgn);
                if (hmbngn != null) {
                    listHakmilikStrataProv.add(hmbngn.get(0));
                }
            }

            if (!listHakmilikStrata.isEmpty()) {
                Permohonan mohon = strataPtService.findPermohonanByHakmilik2(idHakmilikInduk);
                if (mohon != null) {
                    for (Hakmilik hm : listHakmilikStrata) {
                        if (hm.getTarikhDaftar() == null) {
                            hm.setTarikhDaftar(mohon.getInfoAudit().getTarikhMasuk());
                            strataPtService.simpanhakmilik(hm);
                        }
                    }
                }
            }

            StringBuilder hmBatal = new StringBuilder();
            String results = "0";

            for (Hakmilik h : listHakmilikStrata) {
                String st = h.getKodStatusHakmilik().getKod();
                if ("B".equals(st)) {
                    if (hmBatal.length() > 0) {
                        hmBatal.append(",");
                    }
                    hmBatal.append(h.getIdHakmilik());
                }
            }

            //check if there is id Hakmilik Batal
            if (hmBatal.length() > 0) {
                LOG.info("ade batal ");
                results = " " + hmBatal;
                addSimpleError("Terdapat Id Hakmilik batal :" + results);
            }

            LOG.info("size listHakmilikStrata" + listHakmilikStrata.size());
            bilHakmilik = listHakmilikStrata.size();
        }

        int l = 0;
        for (Hakmilik h : listHakmilikStrata) {
            if (h.getKodKategoriBangunan() != null) {
                if (!h.getKodKategoriBangunan().getKod().equals("P")) {
                    hakmilikPermohonan.add(h);
                }
//                else {
//                    if (l == 0) {
//                        hakmilikPermohonan.add(h);
//                        l++;
//                    }
//                }
            }
        }
        pegangan = "";

        getContext().getRequest().setAttribute("janaSemua", true);
        LOG.info("hakmilikPermohonan size" + hakmilikPermohonan.size());
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
    }

    @HandlesEvent("Step2")
    public Resolution kemaskini() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.getRequest().setAttribute("tanpaBayaran", true);

        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        senaraiPermohonan.clear();
        listSenaraiHakmilik.clear();

//        Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(hakmilikPermohonan.get(0).getIdHakmilik());
        int count = 0;

        List<String> nobgnn = strataPtService.findNoBangunanProv(idHakmilikInduk);

        for (int i = 0; i < nobgnn.size(); i++) {
            String nobgn = nobgnn.get(i);
            List<Hakmilik> hmbngn = strataPtService.findHm(idHakmilikInduk, nobgn);
            if (hmbngn.size() > 0) {
                if (hmbngn.get(0).getNoVersiDhde() == 0) {
                    listHakmilikStrataProv.add(hmbngn.get(0));
                }
            }
        }

        for (Hakmilik hmProv : listHakmilikStrataProv) {
            hakmilikPermohonan.add(hmProv);
        }
        LOG.info("hakmilikPermohonan size" + hakmilikPermohonan.size());
        for (Hakmilik hm : hakmilikPermohonan) {
            LOG.info("hm ##" + hm.getIdHakmilik());
            Hakmilik hmInduk2 = hakmilikService.findHakmilikIndukByIdHakmilik(hm.getIdHakmilik());
            if (hmInduk2.getKodStatusHakmilik().getKod().equals("SU")) {
                hmInduk2 = null;
            }
            if (hmInduk2 == null) {
                if (hm.getIdHakmilik().length() == 20) {
                    String idhmProv = hm.getIdHakmilik().substring(0, 17);
                    String noBngn = hm.getIdHakmilik().substring(17, 20).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    if (!listHmProv.isEmpty()) {
                        listhakmilikPermohonan.add(listHmProv.get(0));
                        if (listHmProv.get(0).getNoVersiDhde() != 0) {
                            addSimpleError("Id Hakmilik " + hm.getIdHakmilik() + " telah ditukarganti.");
                            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                        }
                    }
                    LOG.info("prov  :" + idhmProv + "bngn--" + noBngn);
                    LOG.info("listHmProv  :" + listHmProv.size());
                }
                if (hm.getIdHakmilik().length() == 19) {
                    String idhmProv = hm.getIdHakmilik().substring(0, 16);
                    String noBngn = hm.getIdHakmilik().substring(16, 19).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    if (!listHmProv.isEmpty()) {
                        listhakmilikPermohonan.add(listHmProv.get(0));
                        if (listHmProv.get(0).getNoVersiDhde() != 0) {
                            addSimpleError("Id Hakmilik " + hm.getIdHakmilik() + " telah ditukarganti.");
                            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                        }
                    }
                    LOG.info("prov  :" + idhmProv + "bngn--" + noBngn);
                    LOG.info("listHmProv  :" + listHmProv.size());
                }
                if (hm.getIdHakmilik().length() != 20 && hm.getIdHakmilik().length() != 19) {
                    addSimpleError("Id Hakmilik " + hm.getIdHakmilik() + " ini tidak dijumpai");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                }
            } else {
                if (hmInduk2.getKodKategoriBangunan().getKod().equals("L") || hmInduk2.getKodKategoriBangunan().getKod().equals("B")) {
                    listhakmilikPermohonan.add(hmInduk2);
                }
                if (hmInduk2.getKodKategoriBangunan().getKod().equals("P") && count == 0 && hmInduk2.getNoVersiDhde() == 0) {
//                List<Hakmilik> listHmp = strataPtService.findHakmilibyParentProvDistinct(hmInduk2.getIdHakmilikInduk());
                    List<String> listBngn = strataPtService.findNoBangunanProv(hmInduk2.getIdHakmilikInduk());
//                    Hakmilik hmInduk3 = hakmilikService.findHakmilikIndukByIdHakmilik(hmInduk2.getIdHakmilikInduk()); //if 1prov
//                    listhakmilikPermohonan.add(hmInduk3);
                    LOG.info("listHmp~~~~" + listBngn.size());
                    for (int i = 0; i < listBngn.size(); i++) {
                        LOG.info("listBngn.get(i).getNoBangunan()~~~~" + listBngn.get(i));
                        String nobgn = listBngn.get(i);
                        List<Hakmilik> hmbngn = strataPtService.findHm(hmInduk2.getIdHakmilikInduk(), nobgn);
                        if (hmbngn.size() > 0) {
                            if (hmbngn.get(0).getNoVersiDhde() == 0) {
                                kumpHm = 1;
                                listhakmilikPermohonan.add(hmbngn.get(0));
                            }
                        }
                    }
                    count++;
                }
            }

        }

        if (hakmilikPermohonan.isEmpty()) {
            addSimpleError("Hakmilik ini telah di tukarganti");
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
        } else {
            LOG.info("bilhakmilik : " + bilHakmilik);
            for (Hakmilik hm : hakmilikPermohonan) {
//                    idHakmilik = hm.getIdHakmilik();
                hakmilikStrata = hakmilikDAO.findById(hm.getIdHakmilik());
                if (hakmilikStrata.getTarikhDaftar() == null) {
                    Permohonan mohon = strataPtService.findPermohonanByHakmilik2(hm.getIdHakmilikInduk());
                    if (mohon != null) {
                        for (Hakmilik hmilik : listHakmilikStrata) {
                            if (hmilik.getTarikhDaftar() == null) {
                                hmilik.setTarikhDaftar(mohon.getInfoAudit().getTarikhMasuk());
                                strataPtService.simpanhakmilik(hmilik);
                            }
                        }
                    } else {
                        addSimpleError("Urusan HTB bagi Hakmilik master ini tidak didaftarkan. ");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                    }
                }
                if (hakmilikStrata != null) {
                    hakmilik = hakmilikDAO.findById(hakmilikStrata.getIdHakmilikInduk());
                    if (hakmilikStrata.getNoVersiDhde() != 0) {
                        if (hakmilikStrata.getInfoAudit().getTarikhKemaskini() != null) {
                            List<Dokumen> dk = strataPtService.findGeranStrata(hm.getIdHakmilik());
                            HakmilikTukarGantiStrata hmTGStr = strataPtService.findHmStrTG(hakmilikStrata.getIdHakmilik());
                            if (!dk.isEmpty()) {
                                String tarikh = sdf.format(dk.get(0).getInfoAudit().getTarikhMasuk());
                                if (hmTGStr != null) {
                                    addSimpleError("Id Hakmilik Strata " + hm.getIdHakmilik() + " ini sudah ditukarganti oleh " + hmTGStr.getInfoAudit().getDimasukOleh().getNama() + " (" + sdf.format(hmTGStr.getTarikhTukarganti4k()) + ")");
                                } else {
                                    addSimpleError("Id Hakmilik Strata " + hm.getIdHakmilik() + " ini sudah ditukarganti oleh " + dk.get(0).getInfoAudit().getDimasukOleh().getNama() + " (" + tarikh + ")");
                                }
                            } else {
                                addSimpleError("Id Hakmilik Strata " + hm.getIdHakmilik() + " ini sudah ditukarganti ");
                            }
                            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                        } else {
                            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_cetak_resit_2.jsp");
                        }
                    }
                } else if (hm.getIdHakmilik().length() != 20 && hm.getIdHakmilik().length() != 19) {
                    addSimpleError("Id Hakmilik ini tidak dijumpai");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
                }
            }
            ctx.removeWorkdata();
        }
        pegangan = "";
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_cetak_resit_2.jsp");
    }

    @HandlesEvent("Stepkemaskini")
    public Resolution StepKemaskini() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.getRequest().setAttribute("tanpaBayaran", true);

        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        senaraiPermohonan.clear();
        listSenaraiHakmilik.clear();
//        hakmilikPermohonan.clear();

        bilHakmilik = bilHakmilik;
        List<Hakmilik> hm = new ArrayList<Hakmilik>();
        for (int i = 0; i < bilHakmilik; i++) {
            hm.add(hakmilikPermohonan.get(i));
        }
        
        hakmilikPermohonan.clear();
        for (int i = 0; i < hm.size(); i++) {
            hakmilikPermohonan.add(hm.get(i));
        }
                
        idHakmilikInduk = null;
        pegangan = "";
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_2.jsp");
    }

    @HandlesEvent("Step3")
    public Resolution save() {

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.getRequest().setAttribute("tanpaBayaran", true);

        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        InfoAudit ia2 = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(now);

//        senaraiPermohonan.clear();
        //  try {
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk("-");
        fd.setInfoAudit(ia);
        folderDokumenDAO.save(fd);

        int count = 0;

//          List<String> nobgnn = strataPtService.findNoBangunanProv(idHakmilikInduk);
//        for (int i = 0; i < nobgnn.size(); i++) {
//        String idProv = getContext().getRequest().getParameter("idHakmilikProv"+i);
//        LOG.info("idProv==="+idProv);
//            String nobgn = nobgnn.get(i);
//            if (idProv != null) {
//                if (idProv.length() == 20) {
//                    String idhmProv = idProv.substring(0, 17);
//                    String noBngn = idProv.substring(17, 20).replaceAll("^0*", "");
//                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
//                    if (!listHmProv.isEmpty()) {
//                        listHakmilikStrataProv.add(listHmProv.get(0));
//                    }
//                }
//                if (idProv.length() == 19) {
//                    String idhmProv = idProv.substring(0, 16);
//                    String noBngn = idProv.substring(16, 19).replaceAll("^0*", "");
//                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
//                    if (!listHmProv.isEmpty()) {
//                        listHakmilikStrataProv.add(listHmProv.get(0));
//                    }
//                }
//            }
//        }
        LOG.info("listHakmilikStrataProv===" + hakmilikPermohonan.size());
        LOG.info("listHakmilikStrataProv===" + listHakmilikStrataProv.size());
//        for (Hakmilik hmProv : listHakmilikStrataProv) {
//            hakmilikPermohonan.add(hmProv);
//        }
        for (Hakmilik hm4 : hakmilikPermohonan) {
            Hakmilik hmInduk2 = hakmilikService.findHakmilikIndukByIdHakmilik(hm4.getIdHakmilik());

            if (hmInduk2 != null) {
                if (hmInduk2.getKodKategoriBangunan().getKod().equals("L") || hmInduk2.getKodKategoriBangunan().getKod().equals("B")) {
                    listhakmilikPermohonan.add(hmInduk2);
                }
                if (hmInduk2.getKodKategoriBangunan().getKod().equals("P")) {
//                Hakmilik hmInduk3 = hakmilikService.findHakmilikIndukByIdHakmilik(hmInduk2.getIdHakmilikInduk());
//                listhakmilikPermohonan.add(hmInduk3);
//                count++;
//                List<String> listBngn = strataPtService.findNoBangunanProv(hmInduk2.getIdHakmilikInduk());
//                    Hakmilik hmInduk3 = hakmilikService.findHakmilikIndukByIdHakmilik(hmInduk2.getIdHakmilikInduk()); //if 1prov
//                    listhakmilikPermohonan.add(hmInduk3);
//                LOG.info("listHmp~~~~" + listBngn.size());
//                for (int i = 0; i < listBngn.size(); i++) {
//                    LOG.info("listBngn.get(i).getNoBangunan()~~~~" + listBngn.get(i));
//                    String nobgn = listBngn.get(i);
                    List<Hakmilik> hmbngn = strataPtService.findHm(hmInduk2.getIdHakmilikInduk(), hmInduk2.getNoBangunan());
                    if (hmbngn != null) {
                        listhakmilikPermohonan.add(hmbngn.get(0));
                    }
//                }
                    count++;
                }
            } else {
                if (hm4.getIdHakmilik().length() == 20) {
                    String idhmProv = hm4.getIdHakmilik().substring(0, 17);
                    String noBngn = hm4.getIdHakmilik().substring(17, 20).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    if (!listHmProv.isEmpty()) {
                        listhakmilikPermohonan.add(listHmProv.get(0));
                    }
                }
                if (hm4.getIdHakmilik().length() == 19) {
                    String idhmProv = hm4.getIdHakmilik().substring(0, 16);
                    String noBngn = hm4.getIdHakmilik().substring(16, 19).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    if (!listHmProv.isEmpty()) {
                        listhakmilikPermohonan.add(listHmProv.get(0));
                    }
                }
            }
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            for (int i = 0; i < Report_Name.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_Name.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_Name[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);

                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_Name[i];

                /*PermohonanCarian p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, fd,
                hakmilikPermohonan, s,
                idHakmilikSiriDari, idHakmilikSiriKe, ia);*/
                senaraiKandunganFolder = generateReport(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {

            for (int i = 0; i < Report_NameNS.length; i++) {
                // LOG.debug("id hakmilik =" + ch.getIdHakmilik());
                LOG.debug("SIZE REPORT =" + Report_NameNS.length);
                LOG.debug("SIZE REPORT =" + Report_Label.length);
                LOG.debug("name REPORT =" + Report_NameNS[i]);
                LOG.debug("label REPORT =" + Report_Label[i]);

                //   KodUrusan kodUrusan = kodUrusanDAO.findById("CRHMR");
                KodDokumen kodDokumen = null;
                String nameOfReport = "";
                kodDokumen = kodDokumenDAO.findById(Report_Label[i]);
                LOG.debug("kod dokumen =" + kodDokumen.getKod());
                nameOfReport = Report_NameNS[i];

                /*PermohonanCarian p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, fd,
                hakmilikPermohonan, s,
                idHakmilikSiriDari, idHakmilikSiriKe, ia);*/
                senaraiKandunganFolder = generateReportNS(kodDokumen, ia, documentPath, nameOfReport, fd);

            }
        }

        senaraiKandunganFolder2 = kandunganFolderService.findByIdFolder(fd);
        LOG.info("size dokumen~~~~" + senaraiKandunganFolder2.size());

        idHakmilik = hakmilikPermohonan.get(0).getIdHakmilik();

        Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(hakmilikPermohonan.get(0).getIdHakmilik());

        idFolder = String.valueOf(fd.getFolderId());
        LOG.info("idfolder~~~~" + idFolder);

        ctx.removeWorkdata();
        addSimpleMessage("Hakmilik Ini Telah Berjaya Ditukarganti ke Versi 1.");
        return new ForwardResolution(
                "/WEB-INF/jsp/daftar/carian/carian_cetak_resit_2.jsp");

    }

    @HandlesEvent("Step4")
    public Resolution cetakHakmilik() {
//        save();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.getRequest().setAttribute("tanpaBayaran", true);

        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        InfoAudit ia2 = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(now);

        if (!hakmilikPermohonan.isEmpty()) {
            Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(hakmilikPermohonan.get(0).getIdHakmilik());
        }
//        LOG.info("hmInduk~~~~" + hmInduk.getIdHakmilikInduk());

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        LOG.info("idHakmilikInduk~~~~" + idHakmilikInduk);

        if (idHakmilikInduk == null) {
            for (Hakmilik h : hakmilikPermohonan) {
                LOG.info("h.getIdHakmilik().length()~~~~" + h.getIdHakmilik().length());
                if (h.getIdHakmilik().length() == 20) {
                    String idhmProv = h.getIdHakmilik().substring(0, 17);
                    String noBngn = h.getIdHakmilik().substring(17, 20).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    for (Hakmilik hmprov : listHmProv) {
                        hmprov.setNoVersiDhde(1);
                        hmprov.setNoVersiDhke(1);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(pengguna);
                        hmprov.setInfoAudit(ia);

                        hakmilikService.saveHakmilik(hmprov);

                        Hakmilik hkinduk = hakmilikDAO.findById(hmprov.getIdHakmilikInduk());
                        HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(hmprov.getIdHakmilik());
                        if (hmTG == null) {
                            HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                            HmSTG.setHakmilikInduk(hkinduk);
                            HmSTG.setHakmilikStrata(hmprov.getIdHakmilik());
                            HmSTG.setInfoAudit(ia);
                            HmSTG.setTarikhTukarganti4k(now);
                            HmSTG.setVersi4k(1);
                            strataPtService.simpanHmTukarGantiStrata(HmSTG);
                        } else {
                            if (hmTG.getTarikhTukarganti4k() == null) {
                                hmTG.setTarikhTukarganti4k(now);
                            }
                            hmTG.setVersi4k(1);
                            hmTG.setInfoAudit(ia2);
                            strataPtService.simpanHmTukarGantiStrata(hmTG);
                        }
                    }
                }
                if (h.getIdHakmilik().length() == 19) {
                    String idhmProv = h.getIdHakmilik().substring(0, 16);
                    String noBngn = h.getIdHakmilik().substring(16, 19).replaceAll("^0*", "");
                    List<Hakmilik> listHmProv = strataPtService.findHm(idhmProv, noBngn);
                    for (Hakmilik hmprov : listHmProv) {
                        hmprov.setNoVersiDhde(1);
                        hmprov.setNoVersiDhke(1);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(pengguna);
                        hmprov.setInfoAudit(ia);

                        hakmilikService.saveHakmilik(hmprov);

                        Hakmilik hkinduk = hakmilikDAO.findById(hmprov.getIdHakmilikInduk());
                        HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(hmprov.getIdHakmilik());
                        if (hmTG == null) {
                            HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                            HmSTG.setHakmilikInduk(hkinduk);
                            HmSTG.setHakmilikStrata(hmprov.getIdHakmilik());
                            HmSTG.setInfoAudit(ia);
                            HmSTG.setTarikhTukarganti4k(now);
                            HmSTG.setVersi4k(1);
                            strataPtService.simpanHmTukarGantiStrata(HmSTG);
                        } else {
                            if (hmTG.getTarikhTukarganti4k() == null) {
                                hmTG.setTarikhTukarganti4k(now);
                            }
                            hmTG.setVersi4k(1);
                            hmTG.setInfoAudit(ia2);
                            strataPtService.simpanHmTukarGantiStrata(hmTG);
                        }
                    }
                }
                if (h.getIdHakmilik().length() != 20 && h.getIdHakmilik().length() != 19) {
                    LOG.info("id hakmilik" + h.getIdHakmilik());
                    Hakmilik hk = hakmilikService.findById(h.getIdHakmilik());
                    Hakmilik hkinduk = hakmilikService.findById(hk.getIdHakmilikInduk());
                    LOG.info("hkinduk" + hkinduk.getIdHakmilik());
                    if (hk != null && hk.getNoVersiDhde() == 0) {
                        LOG.info("id hakmilik update versi " + hk.getIdHakmilik());
                        hk.setNoVersiDhde(1);
                        hk.setNoVersiDhke(1);
//                    hk.setKumpulan(0);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(pengguna);
                        hk.setInfoAudit(ia);

                        hakmilikService.saveHakmilik(hk);
                    }

                    if (hkinduk != null) {
                        HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(h.getIdHakmilik());
                        if (hmTG == null) {
                            HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                            HmSTG.setHakmilikInduk(hkinduk);
                            HmSTG.setHakmilikStrata(hk.getIdHakmilik());
                            HmSTG.setInfoAudit(ia);
                            HmSTG.setTarikhTukarganti4k(now);
                            HmSTG.setVersi4k(1);
                            strataPtService.simpanHmTukarGantiStrata(HmSTG);
                        } else {
                            if (hmTG.getTarikhTukarganti4k() == null) {
                                hmTG.setTarikhTukarganti4k(now);
                            }
                            hmTG.setVersi4k(1);
                            hmTG.setInfoAudit(ia2);
                            strataPtService.simpanHmTukarGantiStrata(hmTG);
                        }
                    }
                }
            }
        }

        if (idHakmilikInduk != null) {

            List<String> nobgnn = strataPtService.findNoBangunanProv(idHakmilikInduk);

            for (int i = 0; i < nobgnn.size(); i++) {
                String nobgn = nobgnn.get(i);
                List<Hakmilik> hmbngn = strataPtService.findHm(idHakmilikInduk, nobgn);
                if (hmbngn != null) {
                    if (hmbngn.get(0).getNoVersiDhde() == 0) {
                        hakmilikPermohonan.add(hmbngn.get(0));
                    }
                }
            }

            for (Hakmilik h : hakmilikPermohonan) {
                LOG.info("id hakmilik" + h.getIdHakmilik());
                Hakmilik hk = hakmilikService.findById(h.getIdHakmilik());
                LOG.info("id getIdHakmilikInduk" + hk.getIdHakmilikInduk());
                Hakmilik hkInduk = hakmilikService.findById(idHakmilikInduk);
                if (hk != null) {
                    LOG.info("id hakmilik update versi " + hk.getIdHakmilik());
                    if (hk.getNoVersiDhde() == 0) {
                        hk.setNoVersiDhde(1);
                        hk.setNoVersiDhke(1);
                    }
                    if (hkInduk.getNoVersiIndeksStrata() == null || hkInduk.getNoVersiIndeksStrata() == 0) {
                        hk.setNoVersiIndeksStrata(1);
                        hk.setVersion(1);
                        pegangan = "2K3K";
                    }
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    hk.setInfoAudit(ia);

                    hakmilikService.saveHakmilik(hk);

                    if (hkInduk != null) {

                        HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(h.getIdHakmilik());
                        if (hmTG == null) {
                            HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                            HmSTG.setHakmilikInduk(hkInduk);
                            HmSTG.setHakmilikStrata(hk.getIdHakmilik());
                            HmSTG.setInfoAudit(ia);
                            if (pegangan != null) {
                                HmSTG.setTarikhTukarganti2k(now);
                                HmSTG.setVersi2k(1);
                            }
                            HmSTG.setTarikhTukarganti4k(now);
                            HmSTG.setVersi4k(1);
                            strataPtService.simpanHmTukarGantiStrata(HmSTG);
                        } else {
                            if (pegangan != null) {
                                hmTG.setTarikhTukarganti2k(now);
                                hmTG.setVersi2k(1);
                            }
                            if (hmTG.getTarikhTukarganti4k() == null) {
                                hmTG.setTarikhTukarganti4k(now);
                            }
                            hmTG.setVersi4k(1);
                            hmTG.setInfoAudit(ia2);
                            strataPtService.simpanHmTukarGantiStrata(hmTG);
                        }

                    }
                    if (hk.getKodKategoriBangunan().getKod().equals("P")) {
                        List<Hakmilik> hmprov = strataPtService.findHm(hk.getIdHakmilikInduk(), hk.getNoBangunan());
                        for (Hakmilik hmprov1 : hmprov) {
                            hmprov1.setNoVersiDhde(1);
                            hmprov1.setNoVersiDhke(1);

                            if (pegangan != null) {
                                hmprov1.setNoVersiIndeksStrata(1);
                                hmprov1.setVersion(1);
                            }
                            hakmilikService.saveHakmilik(hk);

                            HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(hmprov1.getIdHakmilik());
                            if (hmTG == null) {
                                HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                                HmSTG.setHakmilikInduk(hkInduk);
                                HmSTG.setHakmilikStrata(hmprov1.getIdHakmilik());
                                HmSTG.setInfoAudit(ia);
                                if (pegangan != null) {
                                    HmSTG.setTarikhTukarganti2k(now);
                                    HmSTG.setVersi2k(1);
                                }
                                HmSTG.setTarikhTukarganti4k(now);
                                HmSTG.setVersi4k(1);
                                strataPtService.simpanHmTukarGantiStrata(HmSTG);
                            } else {
                                if (pegangan != null) {
                                    hmTG.setTarikhTukarganti2k(now);
                                    hmTG.setVersi2k(1);
                                }
                                if (hmTG.getTarikhTukarganti4k() == null) {
                                    hmTG.setTarikhTukarganti4k(now);
                                }
                                hmTG.setVersi4k(1);
                                hmTG.setInfoAudit(ia2);
                                strataPtService.simpanHmTukarGantiStrata(hmTG);
                            }
                        }
                    }
                }
            }

            listHakmilikStrataDaftar = strataPtService.findIdHakmilikByIdHakmilikInduk(idHakmilikInduk);
            if (pegangan != null) {
                for (Hakmilik h : listHakmilikStrataDaftar) {
                    Hakmilik hkinduk = hakmilikService.findById(h.getIdHakmilikInduk());
                    if (h != null) {
                        h.setNoVersiIndeksStrata(1);
                        h.setVersion(1);
                        hakmilikService.saveHakmilik(h);
                    }
                    HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(h.getIdHakmilik());
                    if (hmTG != null) {
                        hmTG.setVersi2k(1);
                        if (hmTG.getTarikhTukarganti2k() == null) {
                            hmTG.setTarikhTukarganti2k(now);
                        }
                        hmTG.setInfoAudit(ia2);
                        strataPtService.simpanHmTukarGantiStrata(hmTG);
                    } else {
                        HakmilikTukarGantiStrata HmSTG = new HakmilikTukarGantiStrata();
                        HmSTG.setHakmilikInduk(hkinduk);
                        HmSTG.setHakmilikStrata(h.getIdHakmilik());
                        HmSTG.setInfoAudit(ia);
                        HmSTG.setTarikhTukarganti2k(now);
                        HmSTG.setVersi2k(1);
                        strataPtService.simpanHmTukarGantiStrata(HmSTG);
                    }
                }
            }

            Hakmilik hk1 = hakmilikService.findById(idHakmilikInduk);
            if (pegangan != null) {
                if (hk1 != null) {
                    HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(idHakmilikInduk);

                    hk1.setNoVersiIndeksStrata(1);
                    hk1.setVersion(1);
                    hakmilikService.saveHakmilik(hk1);

                    if (hmTG != null) {
                        hmTG.setVersi2k(1);
                        if (hmTG.getTarikhTukarganti2k() == null) {
                            hmTG.setTarikhTukarganti2k(now);
                        }
                        hmTG.setInfoAudit(ia2);
                        strataPtService.simpanHmTukarGantiStrata(hmTG);
                    }
                }
            }
        }

        tx.commit();

        save();
        LOG.info("idfolder~~~~" + idFolder);
        FolderDokumen fldr = kandunganFolderService.findFolder(Long.parseLong(idFolder));

        senaraiKandunganFolder2 = kandunganFolderService.findByIdFolder(fldr);

        return new JSP("daftar/cetak_geran_strata.jsp");

    }

    private List<KandunganFolder> generateReport(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {
        if (debug) {
            LOG.debug("generateReport");
        }
        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (StringUtils.isNotBlank(idHakmilikInduk)) {
            if (reportName.equals("UTILITIB2K_MLK.rdf") || reportName.equals("UTILITIB3K_MLK.rdf")) {
                Hakmilik hm2 = hakmilikDAO.findById(idHakmilikInduk);
                if (pegangan != null) {

                    Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(hakmilikPermohonan.get(0).getIdHakmilik());
                    LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = hmInduk.getIdHakmilikInduk();

                    Dokumen dokumenCarian = new Dokumen();
                    dokumenCarian.setFormat("application/pdf");
                    dokumenCarian.setInfoAudit(ia);
                    dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    dokumenCarian.setKodDokumen(kodDokumen);
                    dokumenCarian.setNoVersi("1.0");
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                    dokumenCarian.setHakmilik(valueToPass);
                    dokumenCarian.setDalamanNilai1(valueToPass);
                    dokumenCarian.setSaiz(0);
                    dokumenDAO.saveOrUpdate(dokumenCarian);
                    LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                    KandunganFolder kf1 = new KandunganFolder();
                    kf1.setFolder(fd);
                    LOG.info("id_folder : " + fd.getFolderId());
                    kf1.setDokumen(dokumenCarian);
                    LOG.info("id_dokumen : " + dokumenCarian);
                    kf1.setInfoAudit(ia);
                    kandunganFolderDAO.save(kf1);
                    String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                            + String.valueOf(dokumenCarian.getIdDokumen());
                    LOG.info("path : " + path);
                    reportUtil.generateReport(reportName,
                            new String[]{parameterToPass, parameterToPass2},
                            new String[]{valueToPass, valueToPass2},
                            path, ia.getDimasukOleh());
                    LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                    dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                    dokumenDAO.update(dokumenCarian);
                    LOG.info("Finishh~~~~~~" + reportName);
                    senaraiKF.add(kf1);

                }
            }
        }

        if (reportName.equals("UTILITIBSKDHDK_MLK.rdf") || reportName.equals("UTILITIBSKDHKK_MLK.rdf")
                || reportName.equals("UTILITIPSK_MLK.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                if (h == null) {
                    continue;
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                if (h.getKodKategoriBangunan().getKod().equals("P")) {
                    String nobgn = StringUtils.leftPad(h.getNoBangunan(), 3, '0');
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setHakmilik(h.getIdHakmilikInduk());
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + h.getIdHakmilikInduk() + nobgn + ")");
                    dokumenCarian.setDalamanNilai1(h.getIdHakmilikInduk());
                } else {
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setHakmilik(valueToPass);
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                    dokumenCarian.setDalamanNilai1(valueToPass);
                }
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }
        if (reportName.equals("UTILITIB4KDHDK_MLK.rdf") || reportName.equals("UTILITIB4KDHKK_MLK.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                LOG.info("h =======+" + h.getIdHakmilik());
                Hakmilik hm = hakmilikService.findHakmilikIndukByIdHakmilik(h.getIdHakmilik());
                LOG.info("hm =======+" + hm.getIdHakmilik());
                if (h == null) {
                    continue;
                }
                if (hm.getKodKategoriBangunan().getKod().equals("P")) {
                    continue;
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";
                valueToPass = h.getIdHakmilik();

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                dokumenCarian.setHakmilik(valueToPass);
                dokumenCarian.setDalamanNilai1(valueToPass);
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }
        if (reportName.equals("UTILITIB4AKDHKK_MLK.rdf") || reportName.equals("UTILITIB4AKDHDK_MLK.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                Hakmilik hm = hakmilikService.findHakmilikIndukByIdHakmilik(h.getIdHakmilik());
                if (h == null) {
                    continue;
                }
                if (hm.getIdHakmilikInduk() != null) {
                    if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
                        continue;
                    }
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";
                valueToPass = h.getIdHakmilik();

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                if (h.getKodKategoriBangunan().getKod().equals("P")) {
                    String nobgn = StringUtils.leftPad(h.getNoBangunan(), 3, '0');
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + h.getIdHakmilikInduk() + nobgn + ")");
                    dokumenCarian.setHakmilik(h.getIdHakmilikInduk());
                    dokumenCarian.setDalamanNilai1(h.getIdHakmilikInduk());
                }
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }

        //To-do by Aizuddin (if generateReport2 does not work pass url report here)        
        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    private List<KandunganFolder> generateReportNS(KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, FolderDokumen fd) {
        if (debug) {
            LOG.debug("generateReport");
        }
        String parameterToPass = "";
        String parameterToPass2 = "";
        String valueToPass = "";
        String valueToPass2 = "";
        //Added by Aizuddin seems to me parameter and value not pass correctly
        String[] params = null;
        String[] values = null;

        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        //  List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        if (StringUtils.isNotBlank(idHakmilikInduk)) {
            if (reportName.equals("UTILITIB2K_NS.rdf") || reportName.equals("UTILITIB3K_NS.rdf")) {
                Hakmilik hm2 = hakmilikDAO.findById(idHakmilikInduk);
                if (pegangan != null) {

                    Hakmilik hmInduk = hakmilikService.findHakmilikIndukByIdHakmilik(hakmilikPermohonan.get(0).getIdHakmilik());
                    LOG.info("~~~~~gune id hakmilik induk sahaja~~~~~" + hmInduk.getIdHakmilikInduk());
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = hmInduk.getIdHakmilikInduk();

                    Dokumen dokumenCarian = new Dokumen();
                    dokumenCarian.setFormat("application/pdf");
                    dokumenCarian.setInfoAudit(ia);
                    dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                    dokumenCarian.setKodDokumen(kodDokumen);
                    dokumenCarian.setNoVersi("1.0");
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                    dokumenCarian.setHakmilik(valueToPass);
                    dokumenCarian.setDalamanNilai1(valueToPass);
                    dokumenCarian.setSaiz(0);
                    dokumenDAO.saveOrUpdate(dokumenCarian);
                    LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                    KandunganFolder kf1 = new KandunganFolder();
                    kf1.setFolder(fd);
                    LOG.info("id_folder : " + fd.getFolderId());
                    kf1.setDokumen(dokumenCarian);
                    LOG.info("id_dokumen : " + dokumenCarian);
                    kf1.setInfoAudit(ia);
                    kandunganFolderDAO.save(kf1);
                    String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                            + String.valueOf(dokumenCarian.getIdDokumen());
                    LOG.info("path : " + path);
                    reportUtil.generateReport(reportName,
                            new String[]{parameterToPass, parameterToPass2},
                            new String[]{valueToPass, valueToPass2},
                            path, ia.getDimasukOleh());
                    LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                    dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                    dokumenDAO.update(dokumenCarian);
                    LOG.info("Finishh~~~~~~" + reportName);
                    senaraiKF.add(kf1);

                }
            }
        }

        if (reportName.equals("UTILITIBSKDHDK_NS.rdf") || reportName.equals("UTILITIBSKDHKK_NS.rdf")
                || reportName.equals("UTILITIPSK_NS.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                if (h == null) {
                    continue;
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                if (h.getKodKategoriBangunan().getKod().equals("P")) {
                    String nobgn = StringUtils.leftPad(h.getNoBangunan(), 3, '0');
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setHakmilik(h.getIdHakmilikInduk());
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + h.getIdHakmilikInduk() + nobgn + ")");
                    dokumenCarian.setDalamanNilai1(h.getIdHakmilikInduk());
                } else {
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setHakmilik(valueToPass);
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                    dokumenCarian.setDalamanNilai1(valueToPass);
                }
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }
        if (reportName.equals("UTILITIB4KDHDK_NS.rdf") || reportName.equals("UTILITIB4KDHKK_NS.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                LOG.info("h =======+" + h.getIdHakmilik());
                Hakmilik hm = hakmilikService.findHakmilikIndukByIdHakmilik(h.getIdHakmilik());
                LOG.info("hm =======+" + hm.getIdHakmilik());
                if (h == null) {
                    continue;
                }
                if (hm.getKodKategoriBangunan().getKod().equals("P")) {
                    continue;
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";
                valueToPass = h.getIdHakmilik();

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + valueToPass + ")");
                dokumenCarian.setHakmilik(valueToPass);
                dokumenCarian.setDalamanNilai1(valueToPass);
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }
        if (reportName.equals("UTILITIB4AKDHKK_NS.rdf") || reportName.equals("UTILITIB4AKDHDK_NS.rdf")) {
            for (Hakmilik h : listhakmilikPermohonan) {
                Hakmilik hm = hakmilikService.findHakmilikIndukByIdHakmilik(h.getIdHakmilik());
                if (h == null) {
                    continue;
                }
                if (hm.getIdHakmilikInduk() != null) {
                    if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
                        continue;
                    }
                }
                LOG.info("gune id hakmilik panjang");
                parameterToPass = "p_id_hakmilik";
                valueToPass = h.getIdHakmilik();

                Dokumen dokumenCarian = new Dokumen();
                dokumenCarian.setFormat("application/pdf");
                dokumenCarian.setInfoAudit(ia);
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                dokumenCarian.setKodDokumen(kodDokumen);
                dokumenCarian.setNoVersi("1.0");
                if (h.getKodKategoriBangunan().getKod().equals("P")) {
                    String nobgn = StringUtils.leftPad(h.getNoBangunan(), 3, '0');
                    valueToPass = h.getIdHakmilik();
                    dokumenCarian.setTajuk(kodDokumen.getNama() + " (" + h.getIdHakmilikInduk() + nobgn + ")");
                    dokumenCarian.setHakmilik(h.getIdHakmilikInduk());
                    dokumenCarian.setDalamanNilai1(h.getIdHakmilikInduk());
                }
                dokumenCarian.setSaiz(0);
                dokumenDAO.saveOrUpdate(dokumenCarian);
                LOG.info("nama dokumen yang di save ~~~~" + dokumenCarian.getTajuk());
                KandunganFolder kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                LOG.info("id_folder : " + fd.getFolderId());
                kf1.setDokumen(dokumenCarian);
                LOG.info("id_dokumen : " + dokumenCarian);
                kf1.setInfoAudit(ia);
                kandunganFolderDAO.save(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(dokumenCarian.getIdDokumen());
                LOG.info("path : " + path);
                reportUtil.generateReport(reportName,
                        new String[]{parameterToPass, parameterToPass2},
                        new String[]{valueToPass, valueToPass2},
                        path, ia.getDimasukOleh());
                LOG.info("reportUtil.getDMSPath() : " + reportUtil.getDMSPath());
                dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(dokumenCarian);
                LOG.info("Finishh~~~~~~" + reportName);
                senaraiKF.add(kf1);
            }
        }

        //To-do by Aizuddin (if generateReport2 does not work pass url report here)
        LOG.info("senarai kandungan" + senaraiKF);
        LOG.info("senarai kandungan" + senaraiKF.size());
        return senaraiKF;
    }

    private static final boolean isUrusanNull(UrusanValue uv) {
        return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));
    }

    private void checkHakmilikPerserahanValid(ArrayList<UrusanCache> txnGroups) {
        for (UrusanCache urusanCache : txnGroups) {
        }
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public List<Hakmilik> getListhakmilikPermohonan() {
        return listhakmilikPermohonan;
    }

    public void setListhakmilikPermohonan(List<Hakmilik> listhakmilikPermohonan) {
        this.listhakmilikPermohonan = listhakmilikPermohonan;
    }

    public List<Hakmilik> getListHakmilikStrataProv() {
        return listHakmilikStrataProv;
    }

    public void setListHakmilikStrataProv(List<Hakmilik> listHakmilikStrataProv) {
        this.listHakmilikStrataProv = listHakmilikStrataProv;
    }
}
