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
import etanah.model.CaraBayaran;
import etanah.model.CarianHakmilik;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.KaunterService;
import etanah.service.ReportName;
import etanah.service.common.CarianHakmilikService;
import etanah.service.common.DokumenService;
import etanah.service.common.PihakService;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1"})
@UrlBinding("/daftar/carian_tanpa_bayaran")
public class CarianTanpaBayaranAction extends AbleActionBean {
 
    @Inject
    private KaunterService kaunterService;
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
    //End of Step 3a DAO and services
    private ArrayList<UrusanValue> senaraiUrusan = new ArrayList<UrusanValue>();
    private ArrayList<CarianHakmilik> hakmilikPermohonan = new ArrayList<CarianHakmilik>();
    private ArrayList<String> idHakmilikSiriDari = new ArrayList<String>();
    private ArrayList<String> idHakmilikSiriKe = new ArrayList<String>();
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    private List<KandunganFolder> senaraiKandunganFolder = new ArrayList<KandunganFolder>();
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

    public String getIdPenyerah() {
        return idPenyerah;
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
    
    
    private int bilHakmilik = 5;
    private int selectedItem = -1;
    private static final Logger LOG = Logger.getLogger(CarianTanpaBayaranAction.class);
    private static final boolean debug = LOG.isDebugEnabled();
    //crhmt, crhmr, crhmb, cab
    public static String[] URUSAN_TERLIBAT = {
        "CRHMR",
        "CRHMT",
        "CRHMB",
        "SSHMA",
        "CSBB"
    };

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

    public ArrayList<CarianHakmilik> getHakmilikPermohonan() {
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
   
    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("tanpaBayaran", true);
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
    }

    @HandlesEvent("Step2")
    @DontValidate
    public Resolution selectTitles() {
        getContext().getRequest().setAttribute("tanpaBayaran", true);
        if (senaraiUrusan == null || senaraiUrusan.get(0).getKodUrusan() == null) {
            addSimpleError("Sila Pilih Urusan.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
        }
        // reset senaraiHakmilik
        if (hakmilikPermohonan.isEmpty()) {
            for (int i = 0; i < bilHakmilik; i++) {
                CarianHakmilik h = new CarianHakmilik();
                hakmilikPermohonan.add(h);
            }
        }

        String kodUrusan = senaraiUrusan.get(0).getKodUrusan();
        String namaUrusan = senaraiUrusan.get(0).getNamaUrusan();
        urusan = kodUrusan;
        KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
        kodSerah = ku.getKodPerserahan().getKod();

        if (debug) {
            LOG.debug("nama urusan = " + namaUrusan);
            LOG.debug("is contains hakmilik ? " + (namaUrusan.matches("(?i).*hakmilik.*")));
        }
        if (namaUrusan.matches("(?i).*hakmilik.*") || kodUrusan.equals("CRHMR")) {
            if (kodUrusan.equals("CRHMB")) {
                getContext().getRequest().setAttribute("batal", true);
            }
            if (kodUrusan.equals("SSHMA")) {
                getContext().getRequest().setAttribute("SSHMA", true);
            }
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp");

        } else if (kodUrusan.equals("CSMNP")
                || kodUrusan.equals("CSDOK")
                || kodUrusan.equals("CSBP")
                || kodUrusan.equals("CSBB")) { //FIXME
            if (kodUrusan.equals("CSBB")) {
                getContext().getRequest().setAttribute("CSBB", true);
            }
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_perserahan.jsp");
        } else if (kodUrusan.equals("SSDOK")) {
            bilHakmilik = 1;
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bilangan_dokumen.jsp");
        }
        return save();
    }

    //Added by Aizuddin to insert penyerah
    //Only SSHMA using this
    @HandlesEvent("Step3a")
    @DontValidate
    public Resolution setPenyerah() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        checkHakmilikPerserahanValid(txnGroups);

        resetUrusan();
        getContext().getRequest().setAttribute("SSHMA", true);
        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
    }

    @HandlesEvent("Step3")
    public Resolution save() {
        
       
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.getRequest().setAttribute("tanpaBayaran", true);

        String documentPath = conf.getProperty("document.path");

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        saveUrusanToSession(ctx);
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);

        resetUrusan();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        senaraiPermohonan.clear();

        try {
            for (int g = 0; g < txnGroups.size(); g++) {
                UrusanCache u = txnGroups.get(g);

                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk("-");
                fd.setInfoAudit(ia);

                folderDokumenDAO.save(fd);

                // save the content
                ArrayList<KandunganFolder> akf = new ArrayList<KandunganFolder>();

                StringBuilder tajukFolder = new StringBuilder();
                int cnt = 0;

                for (cnt = 0; cnt < u.senaraiUrusan.size(); cnt++) {
                    UrusanValue uv = u.senaraiUrusan.get(cnt);
                    if (isUrusanNull(uv)) {
                        continue;
                    }

                    KodUrusan kodUrusan = kodUrusanDAO.findById(uv.getKodUrusan());

                    if (kodUrusan == null) {
                        addSimpleError("Urusan \"" + uv + "\" tidak dijumpai!");
                        throw new RuntimeException("Urusan \"" + uv + "\" tidak dijumpai!");
                    }

                    if (debug) {
                        LOG.debug("adding urusan:" + uv + " for jabatan " + kodUrusan.getJabatanNama());
                    }

                    PermohonanCarian p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, fd,
                            u.hakmilikPermohonan, s,
                            u.idHakmilikSiriDari, u.idHakmilikSiriKe, ia);

                    KandunganFolder kandunganFolder = new KandunganFolder();

                    if (kodUrusan.getKod().equals("CRHMR")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                                nameOfReport = "regCarianRasmiHMml.rdf";
                        } else {
                                nameOfReport = "regCarianRasmiHM.rdf";
                            }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p);

                    } else if (kodUrusan.getKod().equals("CRHMT")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                                nameOfReport = "regSijilCarianHMMakTakKuasaml.rdf";
                        } else {
                                nameOfReport = "regSijilCarianHMMakTakKuasa.rdf";
                            }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p);
                    } else if (kodUrusan.getKod().equals("CRHMB")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMBatalml.rdf";
                        } else {
                            nameOfReport = "regSijilCarianHMBatal.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p);

                    } else if (kodUrusan.getKod().equals("CSHMB")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("DHDE");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regCarianBatalml.rdf";
                        } else {
                            nameOfReport = "regCarianBatal.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p);
                    } else if (kodUrusan.getKod().equals("SSHMA")) {
                        getContext().getRequest().setAttribute("SSHMA", true);
//                        ArrayList<KodDokumen> kodDokumen = new ArrayList<KodDokumen>();
                        
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        
                        List<CarianHakmilik> list = p.getSenaraiHakmilik();
//                        int count = 0;
//                        String [] nameOfReport = new String [list.size()*2] ; 
       
                        for (CarianHakmilik ch2 : list) {
                            if (ch2 == null) {
                                continue;
                            }
                            String idHakmilik = ch2.getIdHakmilik();
                            if (StringUtils.isBlank(idHakmilik)) {
                                continue;
                            }
                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            if (hakmilik == null) {
                                continue;
                            }

                            String[] Kod_Hakmilik_FT = {
                                "GRN",
                                "GM",
                                "PM",
                                "PN",
                                "GMM"
                            };  
                            
                            kodDokumen = kodDokumenDAO.findById("SSHMA");
                            nameOfReport = "regCarianSSHMA.rdf";
                            
                            kandunganFolder = 
                                    generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                            senaraiKandunganFolder.add(kandunganFolder);
                            
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "RegCR_SshmaB1eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
                                    
                                } else {
                                    nameOfReport = "RegCR_SshmaB2eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
                                }
                            } else {
                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "regCRBorangB1eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
                                    
                                } else {
                                    nameOfReport = "regCRBorangB2eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
                                }
                            }
                            
                            kandunganFolder = 
                                    generateReport3(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen(),pengguna.getIdPengguna());
                            senaraiKandunganFolder.add(kandunganFolder);
//                            kodDokumen.add(count, kodDokumenDAO.findById("SSHMA"));
//
//                            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                                nameOfReport[count] = "regCarianSSHMA.rdf";
//                                count++;
//                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
//                                    nameOfReport[count] = "regCRBorangB1eMLK.rdf";
//                                    kodDokumen.add(count, kodDokumenDAO.findById("PB1DE"));
//                                } else {
//                                    nameOfReport[count] = "regCRBorangB2eMLK.rdf";
//                                    kodDokumen.add(count, kodDokumenDAO.findById("PB2DE"));
//                                }
//                            } else {
//                                nameOfReport[count] = "regCarianSSHMA.rdf";
//                                count++;
//                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {                                    
//                                    nameOfReport[count] = "regCRBorangB1eNS.rdf";
//                                    kodDokumen.add(count, kodDokumenDAO.findById("PB1DE"));
//                                } else {
//                                    nameOfReport[count] = "regCRBorangB2eNS.rdf";
//                                    kodDokumen.add(count, kodDokumenDAO.findById("PB2DE"));
//                                }
//                            }
//                            count++;
                            LOG.info(":::::::::::::::ID Hakmilik::::::::::::::::::"+idHakmilik);
                        }
//                        for (int i = 0; i < kodDokumen.size(); i++) {
//                            KodDokumen kd = kodDokumen.get(i);
//                            String reportName2 = nameOfReport[i];
////                            listkandFold = generateReport(kodUrusan, kd, ia, documentPath, reportName2, p);
//                            kandunganFolder = generateReport2(kodUrusan, kd, ia, documentPath, reportName2, p);
//                            senaraiKandunganFolder.add(kandunganFolder);
//                        }
                        LOG.info("Size Kandungan folder untuk cetak " + senaraiKandunganFolder.size());
                    }else if (kodUrusan.getKod().equals("SSHM")){
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        
                        List<CarianHakmilik> list = p.getSenaraiHakmilik();
//                        int count = 0;
//                        String [] nameOfReport = new String [list.size()*2] ; 
       
                        for (CarianHakmilik ch2 : list) {
                            if (ch2 == null) {
                                continue;
                            }
                            String idHakmilik = ch2.getIdHakmilik();
                            if (StringUtils.isBlank(idHakmilik)) {
                                continue;
                            }
                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            if (hakmilik == null) {
                                continue;
                            }

                            String[] Kod_Hakmilik_FT = {
                                "GRN",
                                "GM",
                                "PM",
                                "PN",
                                "GMM"
                            };  
                            
                            kodDokumen = kodDokumenDAO.findById("DHDE");
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                nameOfReport = "regSalinanDHDEml.rdf";
                            } else {
                                nameOfReport = "regSalinanDHDE.rdf";
                            }
                            
                            kandunganFolder = 
                                    generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                            senaraiKandunganFolder.add(kandunganFolder);
                            
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "RegCR_SshmB1eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
                                    
                                } else {
                                    nameOfReport = "RegCR_SshmB2eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
                                }
                            } else {
                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "regCRBorangB1eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
                                    
                                } else {
                                    nameOfReport = "regCRBorangB2eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
                                }
                            }
                            
                            kandunganFolder = 
                                    generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                            senaraiKandunganFolder.add(kandunganFolder);
                        }
                    
                    }                                                          
                    else if (kodUrusan.getKod().equals("SSHMK") //urusan salinan sah akan dicetak di pendaftar
                            || kodUrusan.getKod().equals("SSSC") || kodUrusan.getKod().equals("SSSW")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";

                       if (kodUrusan.getKod().equals("SSHMK")) {
                            kodDokumen = kodDokumenDAO.findById("DHKE");
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                nameOfReport = "regSalinanDHKEml.rdf";
                            } else {
                                nameOfReport = "regSalinanDHKE.rdf";
                            }
                        } else if (kodUrusan.getKod().equals("SSSC")) {
                        } else if (kodUrusan.getKod().equals("SSSW")) {
                        } else if (kodUrusan.getKod().equals("SSDOK")) {
                            senaraiDokumen = new ArrayList<Dokumen>();
                            List<CarianHakmilik> list = p.getSenaraiHakmilik();
                            for (CarianHakmilik ch : list) {
                                if (ch == null) {
                                    continue;
                                }
                                String idPerserahan = ch.getIdPerserahan();
                                if (StringUtils.isBlank(idPerserahan)) {
                                    continue;
                                }
                                Permohonan mohon = permohonanDAO.findById(idPerserahan);
                                if (mohon == null) {
                                    continue;
                                }
                                List<KandunganFolder> senaraiKandungan = mohon.getFolderDokumen().getSenaraiKandungan();
                                for (KandunganFolder kf : senaraiKandungan) {
                                    if (kf == null || kf.getDokumen() == null) {
                                        continue;
                                    }
                                    KodDokumen kd = kf.getDokumen().getKodDokumen();
                                    if (kd == null || kd.getKod().equals("VDOC")
                                            || kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) {
                                        continue;
                                    }

                                    senaraiDokumen.add(kf.getDokumen());
                                }
                            }
                        }

                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p);

                    } else if (!kodUrusan.getKod().equals("SSDOK") && !kodUrusan.getKod().equals("SSHMA")
                            && !kodUrusan.getKod().equals("SSSC") && !kodUrusan.getKod().equals("SSSW")
                            && !kodUrusan.getKod().equals("SSHM") && !kodUrusan.getKod().equals("SSHMK")
                            && !kodUrusan.getKod().equals("CSHMB") && !kodUrusan.getKod().equals("CRHMR")
                            && !kodUrusan.getKod().equals("CRHMB") && !kodUrusan.getKod().equals("CRHMT") && !kodUrusan.getKod().equals("CSBB")) {

                        senaraiKandunganFolder = generateSijil(kodUrusan, ia, fd, documentPath, p);

                    }
                    else if (kodUrusan.getKod().equals("CSBB")) {

                        senaraiKandunganFolder = generateAkuanPenerimaan(kodUrusan, ia, fd, documentPath, p);
                    }

                    if (senaraiKandunganFolder.size() > 0) {
                        List<Long> senaraiDokumen = new ArrayList<Long>();
                        for (KandunganFolder kf : senaraiKandunganFolder) {
                            akf.add(kf);
                            senaraiDokumen.add(kf.getDokumen().getIdDokumen());
                        }
                        uv.setSenaraiDokumen(senaraiDokumen);
                    }

                    if (tajukFolder.length() > 0) {
                        tajukFolder.append(",");
                    }
                    tajukFolder.append(p.getIdCarian());
                    senaraiPermohonan.add(uv);
                }
                
                tx = s.beginTransaction();

                fd.setTajuk(tajukFolder.toString());
                if (akf.size() > 0) {
                    fd.setSenaraiKandungan(akf);
                }
                folderDokumenDAO.save(fd);
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.error(ex);
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
            return selectTitles();
        }

        ctx.removeWorkdata();
        addSimpleMessage("Carian Berjaya. Sila cetak carian/sijil.");
        return new ForwardResolution(
                "/WEB-INF/jsp/daftar/carian/carian_cetak_resit.jsp");

    }

    private List<KandunganFolder> generateReport(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, PermohonanCarian pc) {
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

        List<CarianHakmilik> list = pc.getSenaraiHakmilik();


        for (CarianHakmilik ch : list) {
            LOG.info("ch.getIdPerserahan()"+ch.getPermohonanCarian().getIdCarian());
            if (ch == null) {
                continue;
            }
            
            //SSHMA xnk pki nie..
            if (!(kodUrusan.getKod().equalsIgnoreCase("SSHMA"))) {
                if (StringUtils.isNotBlank(ch.getIdHakmilik())) {
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = ch.getIdHakmilik();
                } else if (StringUtils.isNotBlank(ch.getIdPerserahan())) {
                    parameterToPass = "p_id_mohon";
                    valueToPass = ch.getIdPerserahan();
                }
                // ----------------------------------------- betulkan tarikh (CRHMR)-----------------------------------------
//                if(kodUrusan.getKod().equalsIgnoreCase("CRHMR")){
//                    parameterToPass2 = "p_carian";
//                    valueToPass2 = ch.getPermohonanCarian().getIdCarian();
//                }
                parameterToPass2 = "p_carian";
                valueToPass2 = pc.getIdCarian();
            }

            //Added by Aizuddin only SSHMA use this
            if (kodUrusan.getKod().equalsIgnoreCase("SSHMA")) {
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                values = new String[]{ch.getPermohonanCarian().getIdCarian(), ch.getIdHakmilik()};
            }

            Dokumen dokumenCarian = new Dokumen();
            dokumenCarian.setFormat("application/pdf");
            dokumenCarian.setInfoAudit(ia);
            dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            dokumenCarian.setKodDokumen(kodDokumen);
            dokumenCarian.setNoVersi("1.0");
            dokumenCarian.setTajuk(kodDokumen.getNama() + "(" + ch.getIdHakmilik() + ")");
            dokumenDAO.save(dokumenCarian);
            KandunganFolder kf1 = new KandunganFolder();
            kf1.setFolder(pc.getFolderDokumen());
            kf1.setDokumen(dokumenCarian);
            kf1.setInfoAudit(ia);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(dokumenCarian.getIdDokumen());
            LOG.info("path : "+path);

            if (kodUrusan.getKod().equalsIgnoreCase("SSHMA")) {
                reportUtil.generateReport(reportName,
                        params,
                        values,
                        path, ia.getDimasukOleh());
            } 
//            else if (kodUrusan.getKod().equalsIgnoreCase("CRHMR")) {
            else {
                Hakmilik hm = hakmilikDAO.findById(ch.getIdHakmilik());
                // ----------------------------------------- betulkan tarikh (CRHMR)-----------------------------------------
                if (hm.getIdHakmilikInduk() != null) {
                    if (kodUrusan.getKod().equals("CRHMR")) {
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            reportName = "regSijilCarianHMSB4K.rdf";
                        } else {
                            reportName = "STRSijilCarianHMSB4K.rdf";
                        }
                    } else if (kodUrusan.getKod().equals("CRHMT")) {
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            reportName = "regCatatanCarianHMSB4K.rdf";
                        } else {
                            reportName = "STRCatatanCarianHMSB4K.rdf";
                        }
                    }
                    reportUtil.generateReport(reportName,
                            new String[]{parameterToPass, parameterToPass2},
                            new String[]{valueToPass, valueToPass2},
                            path, ia.getDimasukOleh());
                } else {

                    reportUtil.generateReport(reportName,
                            new String[]{parameterToPass, parameterToPass2},
                            new String[]{valueToPass, valueToPass2},
                            path, ia.getDimasukOleh());
                }
            }
//            else {
//                reportUtil.generateReport(reportName,
//                        new String[]{parameterToPass},
//                        new String[]{valueToPass},
//                        path, ia.getDimasukOleh());
//            }

            LOG.info("reportUtil.getDMSPath() : "+reportUtil.getDMSPath());
            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
            dokumenDAO.update(dokumenCarian);
            senaraiKF.add(kf1);
        }
        
        //To-do by Aizuddin (if generateReport2 does not work pass url report here)        
        
        return senaraiKF;
    }
    
    //Added by Aizuddin to generate report first than add to list later
    private KandunganFolder generateReport2(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, String idCarian, String idHakmilik, FolderDokumen fd) {
        if (debug) {
            LOG.debug("generateReport");
        }

        String[] params = null;
        String[] values = null;


//        List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        KandunganFolder kFolder = new KandunganFolder();


//        for (CarianHakmilik ch : list) {
//            if (ch == null) {
//                continue;
//            }
//            
//            LOG.info("::::::::::::::ID Hakmilik Generate Report::::::::::::::"+ch.getIdHakmilik());
//            
//            
//        }


        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
        values = new String[]{idCarian, idHakmilik};

        Dokumen dokumenCarian = new Dokumen();
        dokumenCarian.setFormat("application/pdf");
        dokumenCarian.setInfoAudit(ia);
        dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
        dokumenCarian.setKodDokumen(kodDokumen);
        dokumenCarian.setNoVersi("1.0");
        dokumenCarian.setTajuk(kodDokumen.getNama() + "(" + idHakmilik + ")");
        dokumenDAO.save(dokumenCarian);
        kFolder.setFolder(fd);
        kFolder.setDokumen(dokumenCarian);
        kFolder.setInfoAudit(ia);
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                + String.valueOf(dokumenCarian.getIdDokumen());
        
        reportUtil.generateReport(reportName,
                    params,
                    values,
                    path, ia.getDimasukOleh());

//        if (kodUrusan.getKod().equalsIgnoreCase("SSHMA")) {
//            reportUtil.generateReport(reportName,
//                    params,
//                    values,
//                    path, ia.getDimasukOleh());
////                reportUtil.getUrlReport();
//        }



        dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(dokumenCarian);

        return kFolder;
    }
    
        private KandunganFolder generateReport3(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, String idCarian, String idHakmilik, FolderDokumen fd, String idPguna) {
        if (debug) {
            LOG.debug("generateReport");
        }

        String[] params = null;
        String[] values = null;


//        List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        KandunganFolder kFolder = new KandunganFolder();


//        for (CarianHakmilik ch : list) {
//            if (ch == null) {
//                continue;
//            }
//            
//            LOG.info("::::::::::::::ID Hakmilik Generate Report::::::::::::::"+ch.getIdHakmilik());
//            
//            
//        }


        params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_id_pguna"};
        values = new String[]{idCarian, idHakmilik, idPguna};

        Dokumen dokumenCarian = new Dokumen();
        dokumenCarian.setFormat("application/pdf");
        dokumenCarian.setInfoAudit(ia);
        dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
        dokumenCarian.setKodDokumen(kodDokumen);
        dokumenCarian.setNoVersi("1.0");
        dokumenCarian.setTajuk(kodDokumen.getNama() + "(" + idHakmilik + ")");
        dokumenDAO.save(dokumenCarian);
        kFolder.setFolder(fd);
        kFolder.setDokumen(dokumenCarian);
        kFolder.setInfoAudit(ia);
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                + String.valueOf(dokumenCarian.getIdDokumen());
        
        reportUtil.generateReport(reportName,
                    params,
                    values,
                    path, ia.getDimasukOleh());

//        if (kodUrusan.getKod().equalsIgnoreCase("SSHMA")) {
//            reportUtil.generateReport(reportName,
//                    params,
//                    values,
//                    path, ia.getDimasukOleh());
////                reportUtil.getUrlReport();
//        }



        dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(dokumenCarian);

        return kFolder;
    }

    private List<KandunganFolder> generateSijil(KodUrusan ku, InfoAudit ia,
            FolderDokumen fd, String documentPath, PermohonanCarian pc) {
        LOG.debug("generateSijil kodUrusan =" + ku.getKod());
        Dokumen sijilCarian = null;
        StringBuilder sijil = new StringBuilder();
        KandunganFolder kf1 = null;
        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();
        String rn = "";
        KodDokumen kodResit = null;

        if (ku.getKodPerserahan().getKod().equals("CR")) {
            //carian rasmi

            List<CarianHakmilik> list = pc.getSenaraiHakmilik();


            for (CarianHakmilik hp : list) {
                if (hp == null) {
                    continue;
                }
                sijilCarian = new Dokumen();
                sijilCarian.setFormat("application/pdf");
                sijilCarian.setInfoAudit(ia);
                sijilCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                kodResit = kodDokumenDAO.findById("SCR"); //fixme
                sijilCarian.setKodDokumen(kodResit);
                sijilCarian.setNoVersi("1.0");
                sijilCarian.setTajuk(kodResit.getNama());
                dokumenDAO.save(sijilCarian);
                kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                kf1.setDokumen(sijilCarian);
                kf1.setInfoAudit(ia);

                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(sijilCarian.getIdDokumen());

                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    rn = reportName.getCarianRasmiByIDHakmilikReportName();
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_hakmilik"},
                            new String[]{hp.getIdHakmilik()},
                            path, ia.getDimasukOleh());

                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                    rn = reportName.getCarianRasmiByIDMohonReportName();
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_mohon"},
                            new String[]{hp.getIdPerserahan()},
                            path, ia.getDimasukOleh());
                }
                if (sijil.length() > 0) {
                    sijil.append(",");
                }

                sijil.append(sijilCarian.getIdDokumen());
                sijilCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(sijilCarian);
                senaraiKF.add(kf1);
            }
        } else if (ku.getKodPerserahan().getKod().equals("CS")) {
            //carian persendirian

            List<CarianHakmilik> list = pc.getSenaraiHakmilik();


            for (CarianHakmilik hp : list) {
                if (hp == null) {
                    continue;
                }

                sijilCarian = new Dokumen();
                sijilCarian.setFormat("application/pdf");
                sijilCarian.setInfoAudit(ia);
                sijilCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                kodResit = kodDokumenDAO.findById("SCR"); //fixme
                sijilCarian.setKodDokumen(kodResit);
                sijilCarian.setNoVersi("1.0");
                sijilCarian.setTajuk(kodResit.getNama());
                dokumenDAO.save(sijilCarian);
                kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                kf1.setDokumen(sijilCarian);
                kf1.setInfoAudit(ia);

                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(sijilCarian.getIdDokumen());

                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    rn = reportName.getCarianPersendirianByIDHakmilikReportName();
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_hakmilik"},
                            new String[]{hp.getIdHakmilik()},
                            path, ia.getDimasukOleh());

                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                    rn = reportName.getCarianPersendirianByIDMohonReportName();
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_mohon"},
                            new String[]{hp.getIdPerserahan()},
                            path, ia.getDimasukOleh());
                }
                if (sijil.length() > 0) {
                    sijil.append(",");
                }
                sijil.append(sijilCarian.getIdDokumen());
                sijilCarian.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(sijilCarian);
                senaraiKF.add(kf1);
            }
        }
        return senaraiKF;
    }

    private PermohonanCarian savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan,
            UrusanValue uv, FolderDokumen fd,
            List<CarianHakmilik> hakmilikPermohonan, Session s,
            List<String> idHakmilikSiriDari, List<String> idHakmilikSiriKe, InfoAudit ia) {
        
        Transaction tx = s.beginTransaction();
        
        String idPermohonan = null;
        idPermohonan = idPerserahanGenerator.generate(
                kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        uv.setIdPermohonan(idPermohonan);

        List<CarianHakmilik> senaraiTmp = new ArrayList<CarianHakmilik>();

        PermohonanCarian p = new PermohonanCarian();
        p.setIdCarian(idPermohonan);
        p.setUrusan(kodUrusan);
        p.setCawangan(pengguna.getKodCawangan());
        p.setFolderDokumen(fd);

        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);

        // Added by Aizuddin to save penyerah
        if (p.getUrusan().getKod().equalsIgnoreCase("SSHMA")) {
            if (idPenyerah != null && idPenyerah.length() > 0
                    && !"0".equals(idPenyerah)) {
                p.setIdPenyerah(Integer.parseInt(idPenyerah));
            }
            p.setCatatan(namaPemohon);
            p.setPenyerahNama(penyerahNama);
            p.setPenyerahAlamat1(penyerahAlamat1);
            p.setPenyerahAlamat2(penyerahAlamat2);
            p.setPenyerahAlamat3(penyerahAlamat3);
            p.setPenyerahAlamat4(penyerahAlamat4);
            p.setPenyerahPoskod(penyerahPoskod);

            if (penyerahNegeri != null) {
                p.setPenyerahNegeri(penyerahNegeri);
            }
        }


        permohonanCarianDAO.save(p);

        // attach hakmilik


        if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
            for (CarianHakmilik hp : hakmilikPermohonan) {
                if (hp == null
                        || (StringUtils.isBlank(hp.getIdHakmilik()) && StringUtils.isBlank(hp.getIdPerserahan()))) {
                    continue;
                }
                CarianHakmilik ch = new CarianHakmilik();


                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    if (debug) {
                        LOG.debug("adding hakmilik...");
                    }
                    String id = hp.getIdHakmilik();
                    Hakmilik hm = hakmilikDAO.findById(id);
                    if (hm == null) {
                        throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                    }
                    ch.setIdHakmilik(id);

                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                    if (debug) {
                        LOG.debug("adding perserahan..");
                    }
                    String id = hp.getIdPerserahan();
                    Permohonan pmhn = permohonanDAO.findById(id);


                    if (pmhn == null) {
                        throw new RuntimeException("ID Perserahan " + id + " tidak dijumpai.");
                    }
                    ch.setIdPerserahan(id);
                    ch.setKodUrusan(pmhn.getKodUrusan());

                    if (pmhn.getSenaraiHakmilik().size() > 0) {
                        StringBuilder sb = new StringBuilder();

                        for (HakmilikPermohonan l : pmhn.getSenaraiHakmilik()) {
                            Hakmilik hm = l.getHakmilik();

                            if (hm == null) {
                                continue;
                            }
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hm.getIdHakmilik());
                        }
                        ch.setIdHakmilik(sb.toString());
                    }
                }
                ch.setInfoAudit(ia);
                ch.setPermohonanCarian(p);
                ch.setCawangan(pengguna.getKodCawangan());
                ch.setKodUrusan(kodUrusan);
                carianHakmilikDAO.save(ch);
                senaraiTmp.add(ch);
            }
        }
        // attach Hakmilik bersiri
        if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
            for (int i = 0; i
                    < idHakmilikSiriDari.size(); i++) {
                String idH1 = idHakmilikSiriDari.get(i);
                String idH2 = idHakmilikSiriKe.get(i);



                if (idH1 == null || idH1.trim().length() == 0
                        || idH2 == null || idH2.trim().length() == 0) {
                    continue;


                }

                ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);



                for (String string : list) {
                    LOG.debug("id =" + string);


                }

                List<Hakmilik> listId = sessionProvider.get().createQuery(
                        "select distinct(h) from Hakmilik h inner join fetch h.senaraiAkaun a "
                        + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();



                for (Hakmilik hm : listId) {
                    if (debug) {
                        LOG.debug("id hakmilik =" + hm.getIdHakmilik());


                    }
                    /*
                     if (perluJelasCukai){
                     Akaun ac = hm.getAkaunCukai();
                     if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0){
                     throw new RuntimeException("ID Hakmilik " + hm.getIdHakmilik()
                     + " masih belum menjelaskan Cukai. Urusan ini memerlukan cukai dijelaskan.");
                     }
                     }
                     */
                    CarianHakmilik ch = new CarianHakmilik();
                    ch.setIdHakmilik(hm.getIdHakmilik());
                    ch.setInfoAudit(ia);
                    ch.setPermohonanCarian(p);
                    ch.setCawangan(pengguna.getKodCawangan());
                    carianHakmilikDAO.save(ch);
                    senaraiTmp.add(ch);


                }
            }
        }

        p.setSenaraiHakmilik(senaraiTmp);
        tx.commit();

        return p;


    }

    private void resetUrusan() {
        if (debug) {
            LOG.debug("resetUrusan :: invoked");
        }
        senaraiUrusan = null;
        hakmilikPermohonan = null;
        idHakmilikSiriDari = null;
        idHakmilikSiriKe = null;
    }

    @SuppressWarnings("unchecked")
    private final void saveUrusanToSession(etanahActionBeanContext ctx) {
        if (senaraiUrusan == null || senaraiUrusan.isEmpty()) {
            if (debug) {
                LOG.debug("no urusan to be saved to session");


            }
            return;


        } // save all data to Urusan object
        if (debug) {
            for (UrusanValue ku : senaraiUrusan) {
                LOG.debug("saving urusan " + ku.getKodUrusan());


            }
        }
        UrusanCache u = new UrusanCache();
        u.hakmilikPermohonan = hakmilikPermohonan;
        u.idHakmilikSiriDari = idHakmilikSiriDari;
        u.idHakmilikSiriKe = idHakmilikSiriKe;
        u.senaraiUrusan = senaraiUrusan;

        ArrayList<UrusanCache> au = null;
        Object obj = ctx.getWorkData();


        if (obj == null || !(obj instanceof ArrayList)) {
            if (debug) {
                LOG.debug("creating a new ArrayList of Urusan in session");


            }
            au = new ArrayList<UrusanCache>();
            ctx.setWorkData(au);


        } else {
            au = (ArrayList<UrusanCache>) obj;


            if (debug) {
                LOG.debug("there are already " + au.size() + " registered in session listed below:");


                for (UrusanCache u1 : au) {
                    for (UrusanValue ku1 : u1.senaraiUrusan) {
                        LOG.debug(ku1.getKodUrusan());


                    }
                }
            }
        }
        if (selectedItem < 0) { // new urusan
            int l = au.size();
            au.add(l, u);
            // set position l for all urusan


            for (UrusanValue uv : u.senaraiUrusan) {
                uv.setPosition(l);


            }
        } else { // updating existing cache
            au.set(selectedItem, u);


        }
    }

    private final ArrayList<UrusanCache> getAllUrusanFromSession(etanahActionBeanContext ctx) {
        Object obj = ctx.getWorkData();


        if (!(obj instanceof ArrayList)) {
            LOG.debug("work data is null!!!");


            return null;


        }

        ArrayList<UrusanCache> au = (ArrayList<UrusanCache>) obj;


        if (debug) {
            LOG.debug("there are " + au.size() + " registered in session listed below:");


            for (UrusanCache u1 : au) {
                for (UrusanValue ku1 : u1.senaraiUrusan) {
                    LOG.debug(ku1.getKodUrusan());


                }
            }
        }

        return (ArrayList<UrusanCache>) obj;
    }

    private ArrayList<String> getIdHakmilikFromSiri(String idHakmilikDari, String idHakmilikKe) {
        StringBuilder from = new StringBuilder();


        for (int i = idHakmilikDari.length() - 1; i
                >= 0; i--) {
            char c = idHakmilikDari.charAt(i);


            if (c >= '0' && c <= '9') {
                from.insert(0, c);


            } else {
                break;


            }
        }
        long lFrom = Long.parseLong(from.toString());
        String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());

        // to


        long lTo = 0l;


        try {
            lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));


        } catch (NumberFormatException e) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");


        }

        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        // validate the series along the way


        if (idHakmilikDari.length() != idHakmilikKe.length()
                || !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length()))
                || lTo < lFrom) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");


        } else {

            listIdHakmilik.add(idHakmilikDari);
            listIdHakmilik.add(idHakmilikKe);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.setGroupingUsed(false);
            df.setMinimumIntegerDigits(from.length());


            for (long l = lFrom + 1; l
                    < lTo; l++) {
                String id = pre + df.format(l);
                listIdHakmilik.add(id);


            }
        }

        return listIdHakmilik;


    }

    private static final boolean isUrusanNull(UrusanValue uv) {
        return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));
    }

    private void checkHakmilikPerserahanValid(ArrayList<UrusanCache> txnGroups) {
        for (UrusanCache urusanCache : txnGroups) {
        }
    }

    public Resolution updatePenyerah() {
        String kod = getContext().getRequest().getParameter("kod");
        getContext().getRequest().setAttribute("carian", true);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();


        try {
            if (kod.equals("1")) {
                if ("01".equals(penyerahKod.getKod())) { // PEGUAM
                    Peguam pguam = peguamDAO.findById(Long.parseLong(idPenyerah));


                    if (pguam != null) {
                        pguam.setNama(penyerahNama);
                        pguam.setAlamat1(penyerahAlamat1);
                        pguam.setAlamat2(penyerahAlamat2);
                        pguam.setAlamat3(penyerahAlamat3);
                        pguam.setAlamat4(penyerahAlamat4);
                        pguam.setPoskod(penyerahPoskod);
                        pguam.setNegeri(penyerahNegeri);
                        pguam.setNoTelefon1(penyerahNoTelefon);
                        peguamDAO.update(pguam);


                    }
                } else if ("02".equals(penyerahKod.getKod())) { // JUBL
                    JUBL jubl = jUBLDAO.findById(Long.parseLong(idPenyerah));


                    if (jubl != null) {
                        jubl.setNama(penyerahNama);
                        jubl.setAlamat1(penyerahAlamat1);
                        jubl.setAlamat2(penyerahAlamat2);
                        jubl.setAlamat3(penyerahAlamat3);
                        jubl.setAlamat4(penyerahAlamat4);
                        jubl.setPoskod(penyerahPoskod);
                        jubl.setNegeri(penyerahNegeri);
                        jubl.setNoTelefon1(penyerahNoTelefon);
                        jUBLDAO.update(jubl);


                    }
                } else if ("00".equals(penyerahKod.getKod())) {
                    KodCawanganJabatan kj = kodCawanganJabatanDAO.findById(idPenyerah);
                    if (kj != null) {
                        kj.setNama(penyerahNama);
                        kj.setAlamat1(penyerahAlamat1);
                        kj.setAlamat2(penyerahAlamat3);
                        kj.setAlamat4(penyerahAlamat4);
                        kj.setPoskod(penyerahPoskod);
                        kj.setNegeri(penyerahNegeri);
                        kj.setNoTelefon1(penyerahNoTelefon);
                        kodCawanganJabatanDAO.update(kj);
                    }
                } else if ("05".equals(penyerahKod.getKod())) {
                    KodAgensi ka = kodAgensiDAO.findById(idPenyerah);
                    if (ka != null) {
                        ka.setNama(penyerahNama);
                        ka.setAlamat1(penyerahAlamat1);
                        ka.setAlamat2(penyerahAlamat3);
                        ka.setAlamat4(penyerahAlamat4);
                        ka.setPoskod(penyerahPoskod);
                        ka.setNegeri(penyerahNegeri);
                        ka.setNoTelefon1(penyerahNoTelefon);
                        ka.setEmel(penyerahEmail);
                        kodAgensiDAO.update(ka);
                    }
                } else if ("06".equals(penyerahKod.getKod())) {
                    KodAgensi ka = kodAgensiDAO.findById(idPenyerah);
                    if (ka != null) {
                        ka.setNama(penyerahNama);
                        ka.setAlamat1(penyerahAlamat1);
                        ka.setAlamat2(penyerahAlamat2);
                        ka.setAlamat3(penyerahAlamat3);
                        ka.setAlamat4(penyerahAlamat4);
                        ka.setPoskod(penyerahPoskod);
                        ka.setNegeri(penyerahNegeri);
                        ka.setNoTelefon1(penyerahNoTelefon);
                        ka.setEmel(penyerahEmail);
                        kodAgensiDAO.update(ka);
                    }
                } else if ("07".equals(penyerahKod.getKod())) {
                    KodAgensi ka = kodAgensiDAO.findById(idPenyerah);
                    if (ka != null) {
                        ka.setNama(penyerahNama);
                        ka.setAlamat1(penyerahAlamat1);
                        ka.setAlamat2(penyerahAlamat2);
                        ka.setAlamat3(penyerahAlamat3);
                        ka.setAlamat4(penyerahAlamat4);
                        ka.setPoskod(penyerahPoskod);
                        ka.setNegeri(penyerahNegeri);
                        ka.setNoTelefon1(penyerahNoTelefon);
                        ka.setEmel(penyerahEmail);
                        kodAgensiDAO.update(ka);
                    }
                }
            } else if (kod.equals("2")) {
                Pihak p = pihakService.findPihak(penyerahJenisPengenalan.getKod(), penyerahNoPengenalan);


                if (p != null) {
//                    p.setNama(penyerahNama);
                    p.setSuratAlamat1(penyerahAlamat1);
                    p.setSuratAlamat2(penyerahAlamat2);
                    p.setSuratAlamat3(penyerahAlamat3);
                    p.setSuratAlamat4(penyerahAlamat4);
                    p.setSuratPoskod(penyerahPoskod);
                    p.setSuratNegeri(penyerahNegeri);
                    p.setNoTelefon1(penyerahNoTelefon);
                    p.setEmail(penyerahEmail);
                    pihakService.saveOrUpdate(p);


                }
            }
            tx.commit();
            addSimpleMessage(
                    "Kemaskini data berjaya.");


        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError(
                    "Kemaskini data tidak berjaya.");


        }
        getContext().getRequest().setAttribute("SSHMA", true);
        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
//        return new JSP("/WEB-INF/jsp/kaunter/penyerah.jsp").addParameter("SSHMA", "true");

    }
    
    private List<KandunganFolder> generateAkuanPenerimaan(KodUrusan ku, InfoAudit ia,
        FolderDokumen fd, String documentPath, PermohonanCarian pc) {
        LOG.debug("generateAkuanPenerimaan =" + ku.getKod());
        StringBuilder sijil = new StringBuilder();
        KandunganFolder kf1 = null;
        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();
        String rn = "";
        KodDokumen kodResit = null;
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        
            List<CarianHakmilik> list = carianHakmilikService.findCarianByIdCarian(pc.getIdCarian());


            for (CarianHakmilik hp : list) {
            if (hp == null) {
                continue;
            }

            /* Save Surat Akuan Penerimaan */
            Dokumen akuanPenerimaan = new Dokumen();
            akuanPenerimaan.setKodDokumen(kodDokumenDAO.findById("UNKN1"));
            akuanPenerimaan.setFormat("application/pdf");
            akuanPenerimaan.setInfoAudit(ia);
            akuanPenerimaan.setKlasifikasi(klasifikasiAm);
            akuanPenerimaan.setNoVersi("1.0");
            akuanPenerimaan.setTajuk("Akuan Penerimaan " + pc.getIdCarian());
            dokumenDAO.save(akuanPenerimaan);
            kf1 = new KandunganFolder();
            kf1.setFolder(fd);
            kf1.setDokumen(akuanPenerimaan);
            kf1.setInfoAudit(ia);

            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(akuanPenerimaan.getIdDokumen());

            if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                rn = "HSLResitAkuanPenerimaan_CSBB";
                reportUtil.generateReport(rn,
                        new String[]{"p_id_carian"},
                        new String[]{pc.getIdCarian()},
                        path, ia.getDimasukOleh());
            }

            if (sijil.length() > 0) {
                sijil.append(",");
            }
            sijil.append(akuanPenerimaan.getIdDokumen());
            akuanPenerimaan.setNamaFizikal(reportUtil.getDMSPath());
            dokumenDAO.update(akuanPenerimaan);
            senaraiKF.add(kf1);
        }
        
        return senaraiKF;
    }
}
