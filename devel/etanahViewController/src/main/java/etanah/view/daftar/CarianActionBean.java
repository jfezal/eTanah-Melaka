/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.CarianHakmilikDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodCawanganJabatanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.CarianHakmilik;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.KaunterService;
import etanah.service.StrataPtService;
import etanah.service.ReportName;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.DokumenValue;
import etanah.view.kaunter.TransaksiValue;
import etanah.view.kaunter.UrusanValue;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1"})
@UrlBinding("/daftar/carian")
public class CarianActionBean extends AbleActionBean {

    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    // for deleting/editing urusan
    private int selectedItem = -1;
    // DISPLAY OBJECTS
    // Urusan Value Object (used in Step1 & Step6a)
    private ArrayList<UrusanValue> senaraiUrusan = new ArrayList<UrusanValue>();
    // no of Hakmilik in for the Urusan (used in Step3)
    private int bilHakmilik = 5;
    private int bilPerserahan = 5;
    private int bilCaraBayar = 0;
    // list of Hakmilik in the Urusan (used in Step3)
    private ArrayList<CarianHakmilik> hakmilikPermohonan = new ArrayList<CarianHakmilik>();
    private List<CaraBayaran> caraBayaranList = new ArrayList<CaraBayaran>();
    // list of Hakmilik bersiri (from) (used in Step3)
    private ArrayList<String> idHakmilikSiriDari = new ArrayList<String>();
    // list of Hakmilik bersiri (from) (used in Step3)
    private ArrayList<String> idHakmilikSiriKe = new ArrayList<String>();
    // information on Penyerah (Step5)
    private String idPenyerah;
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
    private static String kodNegeri;
    private String mod = "";
    private String bilHakmilik1 = "";
    private String bilHakmilik2 = "";
    private String kosRendah = "";
    private List<String> tarikhCek = new ArrayList<String>();
    private ArrayList<DokumenValue> senaraiDokumenSerahan = new ArrayList<DokumenValue>();
    // list of payment methods for the Urusan (used in Step6)
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<String> senaraiHakmilikPetak = new ArrayList<String>();
    // value object of amount of charges (used in Step6)
    private BigDecimal jumlahCaj;
    private BigDecimal balance = new BigDecimal(0);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    // senarai permohonan for display in Step7. Being initialized in save() method (used in Step7)
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanCarianDAO permohonanCarianDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private CarianHakmilikDAO carianHakmilikDAO;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private CaraBayaranDAO caraBayaranDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private AkaunService akaunService;
    @Inject
    private StrataPtService strataPtService;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodKewanganStatusDAO;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
//    private GeneratorNoResit noResitGenerator;
    private GeneratorNoResit2 noResitGenerator2;
    @Inject
    DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    private ReportUtil reportUtil;
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
    private PenyataPemungutService penyataPemungutService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ReportName reportName;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;
    private String resitNo;
    private String jenisCarian;
    private boolean returnBalance = false;
    private List<Dokumen> senaraiDokumen;
    private static final Map<String, String[]> URUSAN_INFO = new HashMap<String, String[]>();
    private boolean batal = false;
    private String kodNeg;
    private String kodSerah;
    private String urusan;
    private String kodUrusan;
    private Hakmilik hakmilik;
    private static String[] CARIAN_SECARA_MANUAL = {
        "CPP",
        "CSB",
        "CSBB",
        "CSBP",
        "CRHMS",
        //        "CRHS",
        //        "CRIDS",
        //        "CRPDS",
        "CRSC",
        "CSBUK",
        "CSDOK",
        "CSSC",
        "CSSS",
        "CSHMS"
//        "CSHS",
//        "CSIDS",
//        "CRPDS",
//        "CSPDS"
    };
    private static String[] CARIAN_STRATA = {
        "CRHMS",
        "CRHS",
        "CRIDS",
        "CRPDS",
        "CSHS",
        "CSIDS",
        "CSPDS",
        "CSR",
        "CSHMS"
    };

    // class to cache urusan (before registering a new one)
    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiUrusan;
        ArrayList<DokumenValue> senaraiDokumenSerahan;
        ArrayList<DokumenValue> senaraiDokumenTamb;
        ArrayList<CarianHakmilik> hakmilikPermohonan;
        ArrayList<CarianHakmilik> perserahanPermohonan;
        ArrayList<String> idHakmilikSiriDari;
        ArrayList<String> idHakmilikSiriKe;
    }
    private static final Logger LOG = Logger.getLogger(CarianActionBean.class);
    private static final boolean debug = LOG.isDebugEnabled();

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public ArrayList<UrusanValue> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<UrusanValue> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public void setSenaraiPermohonan(ArrayList<UrusanValue> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

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

    public void setPenyerahJenisPengenalan(
            KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public ArrayList<DokumenValue> getSenaraiDokumenSerahan() {
        return senaraiDokumenSerahan;
    }

    public void setSenaraiDokumenSerahan(ArrayList<DokumenValue> senaraiDokumenSerahan) {
        this.senaraiDokumenSerahan = senaraiDokumenSerahan;
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

    public List<String> getSenaraiHakmilikPetak() {
        return senaraiHakmilikPetak;
    }

    public void setSenaraiHakmilikPetak(List<String> senaraiHakmilikPetak) {
        this.senaraiHakmilikPetak = senaraiHakmilikPetak;
    }

    public List<KodUrusan> getPilihanKodUrusan() {
//        return kaunterService.findAllUrusanByJabatan();
        List<KodUrusan> senaraiUrusan = new ArrayList<KodUrusan>();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        if (pengguna.getPerananUtama() != null
                && pengguna.getPerananUtama().getKod().equals("2")) {
            List<KodUrusan> senarai = kaunterService.findCarianUrusan(jenisCarian);
            for (KodUrusan kodUrusan : senarai) {
                if (!kodUrusan.getKod().equals("CRHMR") && !kodUrusan.getKod().equals("SSHMA")) {
                    senaraiUrusan.add(kodUrusan);
                }
            }
        } else {
            List<KodUrusan> senarai = kaunterService.findCarianUrusan(jenisCarian);
            for (KodUrusan kodUrusan : senarai) {
                if (!kodUrusan.getKod().equals("CRHMR") && !kodUrusan.getKod().equals("SSHMA")) {
                    senaraiUrusan.add(kodUrusan);
                }
            }
        }

        return senaraiUrusan;
    }

    /**
     * Get the checklist Dokumen for the given list Urusan.
     *
     * @return
     */
    public List<UrusanDokumen> getSenaraiDokumenSemuaBorang() {
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        for (UrusanValue u : senaraiUrusan) {
            senaraiKodUrusan.add(u.getKodUrusan());
        }

        return kaunterService.getUrusanDokumen(senaraiKodUrusan);
    }

    public List<KandunganFolder> getSenaraiDokumenSSLSC() {
        String[] TO_EXCLUDE = {"VDOC", "DHDE", "DHKE", "SD", "DHK"};
        List<KandunganFolder> senaraiDok = new ArrayList<KandunganFolder>();
        if (!hakmilikPermohonan.isEmpty()) {
            for (CarianHakmilik hp : hakmilikPermohonan) {
                String idPerserahan = hp.getIdPerserahan();
                if (StringUtils.isBlank(idPerserahan)) {
                    continue;
                }
                Permohonan p = permohonanDAO.findById(idPerserahan);
                if (p == null || p.getFolderDokumen() == null || p.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                    continue;
                }
                for (KandunganFolder kd : p.getFolderDokumen().getSenaraiKandungan()) {
                    Dokumen dok = kd.getDokumen();
                    if (ArrayUtils.contains(TO_EXCLUDE, dok.getKodDokumen().getKod())) {
                        continue;
                    }
                    senaraiDok.add(kd);
                }
            }
        }
        return senaraiDok;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bil) {
        this.bilHakmilik = bil;
    }

    public int getBilPerserahan() {
        return bilPerserahan;
    }

    public void setBilPerserahan(int bilPerserahan) {
        this.bilPerserahan = bilPerserahan;
    }

    public ArrayList<CarianHakmilik> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(ArrayList<CarianHakmilik> hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String nama) {
        this.penyerahNama = nama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public List<CaraBayaran> getCaraBayaranList() {
        return caraBayaranList;
    }

    public void setCaraBayaranList(List<CaraBayaran> caraBayaranList) {
        this.caraBayaranList = caraBayaranList;
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

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public int getBilCaraBayar() {
        return bilCaraBayar;
    }

    public void setBilCaraBayar(int bilCaraBayar) {
        this.bilCaraBayar = bilCaraBayar;
    }

    public String getResitNo() {
        return resitNo;
    }

    public String getJenisCarian() {
        return jenisCarian;
    }

    public void setJenisCarian(String jenisCarian) {
        this.jenisCarian = jenisCarian;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isReturnBalance() {
        return returnBalance;
    }

    public void setReturnBalance(boolean returnBalance) {
        this.returnBalance = returnBalance;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getBilHakmilik1() {
        return bilHakmilik1;
    }

    public void setBilHakmilik1(String bilHakmilik1) {
        this.bilHakmilik1 = bilHakmilik1;
    }

    public String getBilHakmilik2() {
        return bilHakmilik2;
    }

    public void setBilHakmilik2(String bilHakmilik2) {
        this.bilHakmilik2 = bilHakmilik2;
    }

    public String getKosRendah() {
        return kosRendah;
    }

    public void setKosRendah(String kosRendah) {
        this.kosRendah = kosRendah;
    }

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {

        String val = getContext().getRequest().getParameter("tmbhUrusanVal");
        String semula = getContext().getRequest().getParameter("semula");

        if (StringUtils.isNotBlank(semula)) {
            resetAll();
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        tarikhCek = new ArrayList<String>();
        returnBalance = false;
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
    }

    @HandlesEvent("Step1a")
    public Resolution selectBorang() {
        String kodUrusan = senaraiUrusan.get(0).getKodUrusan();
        if (kodUrusan.equals("CSHMS") || kodUrusan.equals("CRHMS")) {
            return selectTitles(); // FIXME : not sure if need to show list of document or to lazy tu fix this..
//      return new ForwardResolution("/WEB-INF/jsp/daftar/carian/senarai_semakan.jsp"); // papar dokumen untuk dipilih
        } else {
            return selectTitles();
        }
    }

    @HandlesEvent("Step2")
    @DontValidate
    public Resolution selectTitles() {
        if (senaraiUrusan == null || senaraiUrusan.get(0).getKodUrusan() == null) {
            addSimpleError("Sila Pilih Urusan.");
            getContext().getRequest().setAttribute("carian", true);
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
        }

        // for editing urusan purpose
        if (selectedItem >= 0) {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            List<UrusanCache> luc = getSessionCache(ctx);
            // hakmilik terlibat
            if (hakmilikPermohonan == null || hakmilikPermohonan.size() == 0) {
                LOG.info("----1a----");
                if (luc != null) {
                    LOG.info("----1b----");
                    hakmilikPermohonan = luc.get(selectedItem).hakmilikPermohonan;
                }
            }
            // hakmilik bersiri dari
            if (idHakmilikSiriDari == null || idHakmilikSiriDari.size() == 0) {
                LOG.info("--- hakmilik bersiri dari --");
                if (luc != null) {
                    idHakmilikSiriDari = luc.get(selectedItem).idHakmilikSiriDari;
                }
            }
            // hakmilik bersiri ke
            if (idHakmilikSiriKe == null || idHakmilikSiriKe.size() == 0) {
                LOG.info("-- hakmilik bersiri ke --");
                if (luc != null) {
                    idHakmilikSiriKe = luc.get(selectedItem).idHakmilikSiriKe;
                }
            }
        }
        // reset senaraiHakmilik
        if (hakmilikPermohonan.size() == 0) {
            LOG.info("-- reset senaraiHakmilik --");
            for (int i = 0; i < bilHakmilik; i++) {
                CarianHakmilik h = new CarianHakmilik();
                hakmilikPermohonan.add(h);
            }
        }

        kodUrusan = senaraiUrusan.get(0).getKodUrusan();
        String namaUrusan = senaraiUrusan.get(0).getNamaUrusan();
        urusan = kodUrusan;
        KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
        kodSerah = ku.getKodPerserahan().getKod();

        if (kodUrusan.equals("CSHMS") || kodUrusan.equals("CRHMS")) {
            LOG.info("----6a----");
            if (senaraiDokumenSerahan == null) {
                LOG.info("----6b----");
                addSimpleError("Sila pilih dokumen carian.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/senarai_semakan.jsp");
            }
        }

//        if (kodUrusan.equals("CRHM") || kodUrusan.equals("CRHMB")
//                || kodUrusan.equals("CRHMR") || kodUrusan.equals("CRHMT")
//                || kodUrusan.equals("CSHM")  || kodUrusan.equals("CSHMB")
//                || kodUrusan.equals("CSBBP") || kodUrusan.equals("CSMHM")
//                || kodUrusan.equals("CSMNP") || kodUrusan.equals("CSDOK")
//                || kodUrusan.equals("SSDOK") || kodUrusan.equals("SSHM") || kodUrusan.equals("SSHMK")
//                || kodUrusan.equals("CRHMR")) {
//
        if (debug) {
            LOG.debug("nama urusan = " + namaUrusan);
            LOG.debug("is contains hakmilik ? " + (namaUrusan.matches("(?i).*hakmilik.*")));
        }

        getContext().getRequest().setAttribute("kodUrusan", kodUrusan);

        if (kodUrusan.equals("CSBUK")) {
            //do nothing
        } else if (ArrayUtils.contains(CARIAN_STRATA, kodUrusan)) { // carian strata only
            if (kodUrusan.equals("CRIDS") || kodUrusan.equals("CRPDS")
                    || kodUrusan.equals("CSIDS") || kodUrusan.equals("CSPDS") || kodUrusan.equals("CSR")) {
                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp"); // select title: hakmilik induk
            } else {
                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_strata.jsp"); // select title: hakmilik strata
            }
        } else if (namaUrusan.matches("(?i).*hakmilik.*")
                || kodUrusan.equals("CRHMR") || kodUrusan.equals("CSB")
                || kodUrusan.equals("CSBNB") || kodUrusan.equals("CAB") || kodUrusan.equals("CRCK")) {
            if (kodUrusan.equals("CSHMB") || kodUrusan.equals("CRHMB")) {
                batal = true;
                getContext().getRequest().setAttribute("batal", "true");
            } else if (kodUrusan.equals("SSHM")) {
                getContext().getRequest().setAttribute("salinan", "true");
                getContext().getRequest().setAttribute("kodUrusan", "SSHM");
//            } else if (kodUrusan.equals("SSHMA")) {
//                getContext().getRequest().setAttribute("salinan", "true");
//                getContext().getRequest().setAttribute("kodUrusan", "SSHMA");
            }
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp");
        } else if (kodUrusan.equals("CSMNP") || kodUrusan.equals("SSSW")
                || kodUrusan.equals("SSSC") || kodUrusan.equals("SSLSC")
                || kodUrusan.equals("CSDOK") || kodUrusan.equals("CSBBP") || kodUrusan.equals("CSBBH")
                || kodUrusan.equals("CSSC") || kodUrusan.equals("CRSC")) { //FIXME

            getContext().getRequest().setAttribute("kodUrusan", kodUrusan);
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_perserahan.jsp");
        } else if (kodUrusan.equals("SSDOK")) {
            bilHakmilik = 1;
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bilangan_dokumen.jsp");
        }

//        return setPenyerah();
        return getAdditionalPaymentInfo();
    }

    @HandlesEvent("Step2b")
    public Resolution getPerserahan() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/senarai_semakan_2.jsp");
    }

    @HandlesEvent("Step2c")
    public Resolution getHelaianDokumen() {
        if (senaraiDokumenSerahan.isEmpty()) {
            addSimpleError("Sila pilih dokumen lampiran!");
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/senarai_semakan_2.jsp");
        }
        getContext().getRequest().setAttribute("SSLSC", true);
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bilangan_dokumen.jsp");
    }

    @HandlesEvent("Step2d")
    public Resolution getBilPetak() {
        kodUrusan = senaraiUrusan.get(0).getKodUrusan();
        bilHakmilik1 = null;
        bilHakmilik2 = null;
        Long bilanganPetak = Long.valueOf(0);
        for (CarianHakmilik ch : hakmilikPermohonan) {
            LOG.info("ch.getIdHakmilik()===" + ch.getIdHakmilik());
            Long bilPetak = (Long) sessionProvider.get().createQuery(
                    "select count(h.idHakmilik) from Hakmilik h where h.idHakmilikInduk = :idhakmilik and h.kodStatusHakmilik.kod = 'D'").setParameter("idhakmilik", ch.getIdHakmilik()).uniqueResult();
            if (bilPetak != null) {
                LOG.info("bilpetak" + bilanganPetak + "ccc" + bilPetak);
                bilanganPetak = bilanganPetak + bilPetak;
                bilHakmilik1 = bilanganPetak.toString();
                bilHakmilik2 = bilanganPetak.toString();
                senaraiHakmilikPetak.add(ch.getIdHakmilik() + bilPetak);

            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bilangan_dokumen.jsp");

    }

    @HandlesEvent("Step2a")
    @DontValidate
    public Resolution getAdditionalPaymentInfo() {
        LOG.info("/* STEP 2a */");
        // validate extra info
        getContext().getRequest().setAttribute("carian", true);

        for (UrusanValue ku : senaraiUrusan) {
            kodUrusan = ku.getKodUrusan();
            getContext().getRequest().setAttribute("kodUrusan", kodUrusan);

            if (kodUrusan.equals("CSMNP") || kodUrusan.equals("SSLSC")
                    || kodUrusan.equals("CSDOK") || kodUrusan.equals("CSBBP") || kodUrusan.equals("CSBBH")
                    || kodUrusan.equals("CSSC") || kodUrusan.equals("CRSC")
                    || kodUrusan.equals("CSBP")) {
                getContext().getRequest().setAttribute("perserahan", true);
                if (hakmilikPermohonan.isEmpty()) {
                    addSimpleError("Sila masukan perserahan!");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_perserahan.jsp");
                } else if (kodUrusan.equals("SSLSC")) {
                    if (senaraiDokumenSerahan.isEmpty()) {
                        addSimpleError("Sila pilih dokumen lampiran!");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/senarai_semakan_2.jsp");
                    } else if (bilHakmilik < 1) {
                        addSimpleError("Sila helaian hendaklah lebih dari 0!");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bilangan_dokumen.jsp");
                    }
                }
                break;
            } else if (ArrayUtils.contains(CARIAN_STRATA, kodUrusan)) {

                // add by azri 26/8/2013
                /* HAKMILIK STRATA BERSIRI */
                if (kodUrusan.equals("CRIDS") || kodUrusan.equals("CRPDS") || kodUrusan.equals("CSIDS") || kodUrusan.equals("CSPDS") || kodUrusan.equals("CSR")) {
                    break;
                } else {
                    if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) { // for hakmilik bersiri, Do This
                        LOG.info("--> Hakmilik From : " + idHakmilikSiriDari);
                        LOG.info("--> Hakmilik To : " + idHakmilikSiriKe);
                        LOG.info("--> idHakmililkSiriDari.size() : " + idHakmilikSiriDari.size());

                        for (int i = 0; i < idHakmilikSiriDari.size(); i++) {
                            String idH1 = idHakmilikSiriDari.get(i);
                            String idH2 = idHakmilikSiriKe.get(i);

                            if (idH1 == null
                                    || idH1.trim().length() == 0
                                    || idH2 == null
                                    || idH2.trim().length() == 0) {
                                continue;
                            }

                            ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);
                            List<Hakmilik> listId = sessionProvider.get().createQuery(
                                    "select distinct(h) from Hakmilik h where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();
                            LOG.info("--> listId.size() : " + listId.size());

                            for (Hakmilik h : listId) {
                                LOG.info("--> id Hakmilik : " + h.getIdHakmilik());
                                LOG.info("--> hakmilik induk : " + h.getIdHakmilikInduk());
                                LOG.info("--> banggunan : " + h.getNoBangunan());
                                LOG.info("--> tingkat : " + h.getNoTingkat());
                                LOG.info("--> petak : " + h.getNoPetak());
                            }
                        }
                    }

                    int val = checkStrata();
                    if (val > 0) {
                        addSimpleError("Sila isi no bangunan, no petak dan no tingkat untuk hakmilik yang dipilih.");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_strata.jsp");
                    }
                    for (int i = 0; i < bilHakmilik; i++) {
                        String idstrata = getContext().getRequest().getParameter("hakmilikStrata" + i);
                        LOG.info("idstrata" + idstrata);
                        if (!idstrata.isEmpty()) {
                            Hakmilik hm = hakmilikDAO.findById(idstrata);
                            if (hm != null) {
                                if (!hm.getKodStatusHakmilik().getKod().equals("D")) {
                                    if (hm.getKodStatusHakmilik().getKod().equals("T")) {
                                        addSimpleError("Hakmilik ini tidak didaftarkan. Sila hubungi Unit Strata.");
                                    }
                                    if (hm.getKodStatusHakmilik().getKod().equals("B")) {
                                        addSimpleError("Hakmilik ini telah dibatalkan. Sila hubungi Unit Strata.");
                                    }
                                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_strata.jsp");
                                }
                                if (hm.getNoVersiDhde() == 0) {
                                    addSimpleError("Versi hakmilik " + idstrata + " adalah versi 0.Urusan Tukar Ganti Hakmilik Strata perlu dilakukan terlebih dahulu.");
                                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_strata.jsp");
                                }
                            } else {
                                addSimpleError("Id Hakmilik Tidak Dijumpai.");
                                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik_strata.jsp");
                            }
                        } else {
                            i = bilHakmilik;
                        }
                    }
                }
            } else if (kodUrusan.equals("SSDOK")) {
                break;
            } else if (hakmilikPermohonan.isEmpty() && checkIdBersiri(idHakmilikSiriDari)
                    && checkIdBersiri(idHakmilikSiriKe)) {
                addSimpleError("Sila masukan hakmilik!");
                if (kodUrusan.equals("CSHMB") || kodUrusan.equals("CRHMB")) {
                    getContext().getRequest().setAttribute("kodUrusan", kodUrusan);
                    getContext().getRequest().setAttribute("batal", "true");
                } else if (kodUrusan.equals("SSHM")) {
                    getContext().getRequest().setAttribute("salinan", "true");
                    getContext().getRequest().setAttribute("kodUrusan", "SSHM");
                }
//                    else if (kodUrusan.equals("SSHMA")) {
//                        getContext().getRequest().setAttribute("salinan", "true");
//                        getContext().getRequest().setAttribute("kodUrusan", "SSHMA");
//                    }
                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp");
            } else if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
                for (int i = 0; i
                        < idHakmilikSiriDari.size(); i++) {
                    String idH1 = idHakmilikSiriDari.get(i);
                    String idH2 = idHakmilikSiriKe.get(i);

                    if (idH1 == null || idH1.trim().length() == 0
                            || idH2 == null || idH2.trim().length() == 0) {
                        continue;
                    }

                    ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);

                    List<Hakmilik> listId = sessionProvider.get().createQuery(
                            "select distinct(h) from Hakmilik h inner join fetch h.senaraiAkaun a "
                            + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();

                    if (listId.size() < list.size()) {
                        getContext().getRequest().setAttribute("kodUrusan", kodUrusan);
                        addSimpleError("Terdapat hakmilik yang tidak wujud dalam siri " + (i + 1) + "!");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp");
                    } else if (!kodUrusan.equals("SSHM") && !kodUrusan.equals("CRHMB")) {
                        for (Hakmilik h : listId) {
                            String st = h.getKodStatusHakmilik().getKod();
                            if ("B".equals(st)) {
                                getContext().getRequest().setAttribute("kodUrusan", kodUrusan);
                                addSimpleError("Terdapat hakmilik yang batal dalam siri " + (i + 1) + "!");
                                return new ForwardResolution("/WEB-INF/jsp/daftar/carian/pilih_hakmilik.jsp");
                            }
                        }
                    }
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/caj_tamb.jsp");
    }

    private boolean checkIdBersiri(List<String> ids) {
        for (String id : ids) {
            if (StringUtils.isNotBlank(id)) {
                return false;
            }
        }
        return true;
    }

    private int checkStrata() {
        for (CarianHakmilik ch : hakmilikPermohonan) {
            if (ch == null || StringUtils.isBlank(ch.getIdHakmilik())) {
                continue;
            }
            if (!StringUtils.isBlank(ch.getNoPetak())) {
                return 0;
            }

            if (StringUtils.isBlank(ch.getNoBangunan())) {
                return 1;
            }

            if (StringUtils.isBlank(ch.getNoPetak())) {
                for (int i = 0; i < bilHakmilik; i++) {
                    String hmstrata = getContext().getRequest().getParameter("hakmilikStrata" + i);
                    LOG.info("hmstrata" + hmstrata);
                        if (!hmstrata.isEmpty()) {
                            Hakmilik hm = hakmilikDAO.findById(hmstrata);
                            if (hm != null) {
                                String kkb = hm.getKodKategoriBangunan().getKod();
                                if ("PL".equals(kkb) || "P".equals(kkb)) {
                                    return 0;
                                } else {
                                    return 2;
                                }
                            }
                        } else {
                            i = bilHakmilik;
                        }    
                }
                //return 2;
            }

            if (StringUtils.isBlank(ch.getNoTingkat())) {
                return 3;
            }
        }
        return 0;
    }

    @HandlesEvent("Step3")
    @DontValidate
    public Resolution setPenyerah() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        checkHakmilikPerserahanValid(txnGroups);

        // reset all values
        resetUrusan();
        getContext().getRequest().setAttribute("carian", true);
        if ("04".equals(conf.getKodNegeri())) {
            kodNeg = "melaka";
        } else {
            kodNeg = "n9";
        }
        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
    }

    @HandlesEvent("Step4")
    @DontValidate
    public Resolution collectPayment() {
        // validate Penyerah
        if (penyerahNama == null || penyerahNama.trim().length() == 0 || penyerahAlamat1 == null
                || penyerahAlamat1.trim().length() == 0
                || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
            addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
            getContext().getRequest().setAttribute("carian", true);
            LOG.info(".................PENYERAH.................");
            if ("04".equals(conf.getKodNegeri())) {
                kodNeg = "melaka";
            } else {
                kodNeg = "n9";
            }
            return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
        }
        getContext().getRequest().setAttribute("carian", true);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        if (txnGroups == null) {
            addSimpleError("Data simpanan sementara telah korup. Anda perlu mula semula.");
            LOG.info(".................CORRUPT.................");
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
        }
        LOG.info("------------------senaraiCaraBayaran.size()f  : " + senaraiCaraBayaran.size());
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }
        bilCaraBayar += 5;
        if (senaraiCaraBayaran.size() == 0) {
            LOG.info("bilCaraBayar : " + bilCaraBayar);
            for (int i = 0; i < bilCaraBayar; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        } else {
            for (int i = 0; i < bilCaraBayar; i++) {
                CaraBayaran cr = new CaraBayaran();
                senaraiCaraBayaran.add(cr);
            }
            bilCaraBayar = senaraiCaraBayaran.size();
        }

        for (UrusanCache urusanCache : txnGroups) {
            String kodUrusan = urusanCache.senaraiUrusan.get(0).getKodUrusan();
            if (kodUrusan.equals("CSMNP")
                    || kodUrusan.equals("CSDOK") || kodUrusan.equals("CSBBP") || kodUrusan.equals("CSBBH")
                    || kodUrusan.equals("CSSC") || kodUrusan.equals("CRSC") || kodUrusan.equals("SSLSC")
                    || kodUrusan.equals("CSBP")) { //FIXME
                getContext().getRequest().setAttribute("perserahan", true);
                break;
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
    }

    @HandlesEvent("Step6a")
    public Resolution addTransaction() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);

        // reset all values
        resetUrusan();
        selectedItem = -1;
        return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
    }

    @HandlesEvent("Step5")
    public Resolution save() throws ParseException {
        LOG.info("/* STEP 5 */");
        String[] BUKAN_SIJIL = {
            "SSSC", "SSSW", "SSHM", "SSHMK", "CSHMB", "CRHMR", "CRHMB", "CRHMT", "CSBBP", "CSHS", "CSR", "CSIDS", "CSPDS", "CRHS", "CRIDS", "CRPDS", "CSBBH", "SSLSC"
        };

        getContext().getRequest().setAttribute("carian", true);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");
        KodUrusan kodUrusan = null;

        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }

        List<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);

        // initialize senaraiTransaksi & jumlahCaj
        List<TransaksiValue> atv = getSenaraiTransaksi();
        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj);
        }

        mod = modKutip.loadPenyerahFromSession(ctx);

        Dokumen resit = null;
        DokumenKewangan dk = null;
        // create DokumenKewangan
        // Resit is always created even if no charged involved
        dk = new DokumenKewangan();
        dk.setCawangan(caw);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY")); // TODO cache
        dk.setIsuKepada(penyerahNama);
//        dk.setIdDokumenKewangan(noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw, pengguna));              // nombor resit lama
        dk.setIdDokumenKewangan(noResitGenerator2.getAndLockSerialNo(pengguna));       //new noResitGenerator
        // set idResit to pageContext
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
        dk.setAmaunBayaran(jumlahCaj);
        dk.setInfoAudit(ia);
        dk.setTarikhTransaksi(new java.util.Date());
        dk.setIdKaunter(pengguna.getIdKaunter());
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        if (mod != null && mod.length() > 0) {
            dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        }

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        boolean payMore = false;
        // if there is payments, save payment methods
        if (!jumlahCaj.equals(BigDecimal.ZERO)) {
            BigDecimal paid = BigDecimal.ZERO;
            for (CaraBayaran cb : senaraiCaraBayaran) {
                if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                        && cb.getAmaun() != null
                        && !cb.getAmaun().equals(BigDecimal.ZERO)) {
                    paid = paid.add(cb.getAmaun());
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if (crByr.getKod().equals("T")) {
                        payMore = true;
                    }
                    if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
                        cb.setBank(kodBankDAO.findById("PMB"));
                    }
                }
            }
            // validate amounts paid
            if (jumlahCaj.compareTo(paid) != 0) {
                if (jumlahCaj.compareTo(paid) > 0) {
                    addSimpleError("Amaun dibayar kurang daripada jumlah Caj dikenakan.");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
                }
                if ((jumlahCaj.compareTo(paid) < 0) && (payMore == false)) {
                    addSimpleError("Amaun dibayar melebihi daripada jumlah Caj dikenakan.");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
                }
                // return balance
                if ((jumlahCaj.compareTo(paid) < 0) && (payMore == true)) {
                    balance = paid.subtract(jumlahCaj);
                    returnBalance = true;
                }
            }
            // save cara bayaran
            saveCaraBayaran(caw, dk, ia);
        }
        // save the resit
        dokumenKewanganDAO.save(dk);

        // generate dokumen resit
        resit = new Dokumen();
        resit.setFormat("application/pdf");
        resit.setInfoAudit(ia);
        resit.setKlasifikasi(klasifikasiAm);
        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
        resit.setKodDokumen(kodResit);
        resit.setNoVersi("1.0");
        resit.setTajuk(kodResit.getNama());
        dokumenDAO.save(resit);

        int cntPermohonan = 0;
        Map<String, PermohonanCarian> mapPermohonan = new HashMap<String, PermohonanCarian>();
        List<Map<String, Transaksi>> mapTrans = new ArrayList();
        ArrayList<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
        senaraiPermohonan.clear();
        // iterate the list of transactions one by one
        try {
            LOG.debug("size atv = " + atv.size());
            if (atv.size() > 0) {
                for (TransaksiValue tv : atv) {
                    LOG.info("tv.getKodTransaksi() = " + tv.getKodTransaksi());
                    if (tv.getKodTransaksi() == null) {
                        if (tv.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                            LOG.warn("No KodTransaksi but amount is > 0");
                        }
                    }
                    // create txn
                    KodTransaksi kt = kodTransaksiDAO.findById(tv.getKodTransaksi());
                    if (kt != null) {
                        LOG.info("creating new transaksi..");
                        Map<String, Transaksi> map2 = new HashMap<String, Transaksi>();
                        Transaksi t = new Transaksi();
                        t.setCawangan(caw);
                        t.setKodTransaksi(kt);
                        t.setAmaun(tv.getAmaun());
                        t.setStatus(kodKewanganStatusDAO.findById('T'));
                        t.setAkaunDebit(akTerima);
                        t.setUntukTahun(year);
                        t.setKuantiti(tv.getKuantiti());
                        t.setInfoAudit(ia);
                        t.setDokumenKewangan(dk);
                        transaksiDAO.save(t);
                        t.setPerihal(tv.getNamaUrusan());
                        map2.put(String.valueOf(tv.getPosition()), t);
                        mapTrans.add(map2);
                    }
                }
            }
            tx.commit();
            tx = s.beginTransaction();

            for (int g = 0; g < txnGroups.size(); g++) {
                UrusanCache u = txnGroups.get(g);

                // open folder for storing submitted documents
                // only one folder for grouped submissions
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk("-");
                fd.setInfoAudit(ia);

                // save the content
                ArrayList<KandunganFolder> akf = new ArrayList<KandunganFolder>();
                if (u.senaraiDokumenSerahan != null) {
                    for (DokumenValue dv : u.senaraiDokumenSerahan) {
                        if (dv == null) {
                            continue;
                        }
                        KodDokumen kd = kodDokumenDAO.findById(dv.getKodDokumen());
                        for (int i = 0; i < dv.getBil(); i++) {
                            // save for every copy
                            for (int j = 0; j < dv.getBil(); j++) {
                                Dokumen d = new Dokumen();
                                if (StringUtils.isNotBlank(dv.getNoRujukan())) {
                                    d = dokumenDAO.findById(Long.parseLong(dv.getNoRujukan()));
                                    if (d == null) {
                                        d = new Dokumen();
                                        d.setKodDokumen(kd);
                                        d.setInfoAudit(ia);
                                        d.setTajuk(kd.getKod());
                                        d.setNoVersi("1.0");
                                        d.setKlasifikasi(klasifikasiAm);
                                        dokumenDAO.save(d);
                                    }
                                } else {
                                    d.setKodDokumen(kd);
                                    d.setInfoAudit(ia);
                                    d.setTajuk(kd.getKod());
                                    d.setNoVersi("1.0");
                                    d.setKlasifikasi(klasifikasiAm);
                                    dokumenDAO.save(d);
                                }
                                KandunganFolder k = new KandunganFolder();
                                k.setFolder(fd);
                                k.setDokumen(d);
                                k.setInfoAudit(ia);
                                akf.add(k);
                            }
                        }
                    }
                }

                // attach resit to folder
                KandunganFolder kf2 = new KandunganFolder();
                kf2.setFolder(fd);
                kf2.setDokumen(resit);
                kf2.setInfoAudit(ia);
                akf.add(kf2);
                if (akf.size() > 0) {
                    fd.setSenaraiKandungan(akf);
                }
                folderDokumenDAO.save(fd);

                // WARNING: folder.tajuk column is 200 bytes max
                StringBuilder tajukFolder = new StringBuilder();
                int cnt = 0;
                folderDokumenDAO.save(fd);

                // adding Permohonan
                for (cnt = 0; cnt < u.senaraiUrusan.size(); cnt++) {
                    UrusanValue uv = u.senaraiUrusan.get(cnt);
                    if (isUrusanNull(uv)) {
                        continue;
                    }

                    // find the jabatan for urusan
                    kodUrusan = kodUrusanDAO.findById(uv.getKodUrusan());
                    if (kodUrusan == null) {
                        addSimpleError("Urusan \"" + uv + "\" tidak dijumpai!");
                        throw new RuntimeException("Urusan \"" + uv + "\" tidak dijumpai!");
                    }
                    if (debug) {
                        LOG.debug("adding urusan:" + uv + " for jabatan " + kodUrusan.getJabatanNama());
                    }
                    PermohonanCarian p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, fd,
                            u.hakmilikPermohonan,
                            u.idHakmilikSiriDari, u.idHakmilikSiriKe, ia, dk, s, mapTrans, g);
                    if (debug) {
                        LOG.debug("size hakmilik = " + p.getSenaraiHakmilik());
                    }
                    mapPermohonan.put(p.getIdCarian(), p);
                    uv.setIdPermohonan(p.getIdCarian());
                    List<KandunganFolder> senaraiKandunganFolder = new ArrayList<KandunganFolder>();
                    List<KandunganFolder> senaraiKandunganFolder1 = new ArrayList<KandunganFolder>();
                    List<KandunganFolder> senaraiKandunganFolder2 = new ArrayList<KandunganFolder>();
                    boolean flag = (ArrayUtils.contains(CARIAN_SECARA_MANUAL, kodUrusan.getKod()));

                    /* GENARATE REPORT FOR CARIAN */
                    if (!flag && !ArrayUtils.contains(BUKAN_SIJIL, kodUrusan.getKod())) {
                        senaraiKandunganFolder = generateSijil(kodUrusan, ia, u.hakmilikPermohonan, kodResit, fd, documentPath, p, dk);
                    } else if (kodUrusan.getKod().equals("CRHMR")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regCarianRasmiHMml.rdf";
//              nameOfReport = "regCABml.rdf";
                        } else {
                            nameOfReport = "regCarianRasmiHM.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        List<KandunganFolder> senaraiTmp = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        for (KandunganFolder kf : senaraiTmp) {
//                            akf.add(kf);
//                        }
                    } else if (kodUrusan.getKod().equals("SSHM")) {
                        KandunganFolder kandunganFolder = new KandunganFolder();
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

//                            kodDokumen = kodDokumenDAO.findById("SDH");
//                            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                                nameOfReport = "rSalinanDHDEml.rdf";
//                            } else {
//                                nameOfReport = "regSalinanDHDE.rdf";
//                            }
//                            
//                            kandunganFolder = 
//                                    generateReport3(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
//                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen(),pengguna.getIdPengguna());
//                            senaraiKandunganFolder.add(kandunganFolder);
//                            if(hakmilik.getIdHakmilikInduk() == null){
//                            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
//                                    nameOfReport = "RegCR_SshmB1eMLK_DHDe.rdf";
//                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
//                                    
//                                } else {
//                                    nameOfReport = "RegCR_SshmB2eMLK_DHDe.rdf";
//                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
//                                }
//                            } else {
//                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
//                                    nameOfReport = "regCRBorangB1eNS.rdf";
//                                    kodDokumen = kodDokumenDAO.findById("PB1DE");
//                                    
//                                } else {
//                                    nameOfReport = "regCRBorangB2eNS.rdf";
//                                    kodDokumen = kodDokumenDAO.findById("PB2DE");                                    
//                                }
//                            }
//                            
//                            kandunganFolder = 
//                                    generateReport3(kodUrusan, kodDokumen, ia, documentPath, nameOfReport
//                                    , p.getIdCarian(), idHakmilik, p.getFolderDokumen(),pengguna.getIdPengguna());
//                            senaraiKandunganFolder.add(kandunganFolder);
//                            }
                        }

                    } else if (kodUrusan.getKod().equals("CRHMT")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMMakTakKuasaml.rdf";
                        } else {
                            nameOfReport = "regSijilCarianHMMakTakKuasa.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        List<KandunganFolder> senaraiTmp = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        for (KandunganFolder kf : senaraiTmp) {
//                            akf.add(kf);
//                        }
                    } else if (kodUrusan.getKod().equals("CRHMB")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMBatalml.rdf";
                        } else {
                            nameOfReport = "regSijilCarianHMBatal.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        List<KandunganFolder> senaraiTmp = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                        for (KandunganFolder kf : senaraiTmp) {
//                            akf.add(kf);
//                        }
                    } else if (kodUrusan.getKod().equals("CSHMB")) {
                        KodDokumen kodDokumen = null;
                        String nameOfReport = "";
                        kodDokumen = kodDokumenDAO.findById("SCR");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMBatalml.rdf";
                        } else {
                            nameOfReport = "regCatatanCarianHMBatal.rdf";
                        }
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
//                    } else if (kodUrusan.getKod().equals("SSHMA")) {
//                        KodDokumen kodDokumen = null;
//                        String nameOfReport = "";
//                        kodDokumen = kodDokumenDAO.findById("SSHMA");
//                            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                              nameOfReport = "regCarianSSHMA.rdf";
//                            } else {
//                              nameOfReport = "regCarianSSHMA.rdf";
//                            }
//                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } else if (kodUrusan.getKod().equals("SSHMK") //urusan salinan sah akan dicetak di pendaftar
                            || kodUrusan.getKod().equals("SSSC") || kodUrusan.getKod().equals("SSSW")) {
                        String nameOfReport = "";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SDH");
                        if (kodUrusan.getKod().equals("SSHM")) {
//                            kodDokumen = kodDokumenDAO.findById("SDH");
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                nameOfReport = "regSalinanDHDEml.rdf";
                            } else {
                                nameOfReport = "regSalinanDHDE.rdf";
                            }

                            KandunganFolder kandunganFolder = new KandunganFolder();
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

                                kodDokumen = kodDokumenDAO.findById("SSHM");

                                kandunganFolder
                                        = generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                                senaraiKandunganFolder.add(kandunganFolder);

                                if ("04".equals(conf.getProperty("kodNegeri"))) {
                                    if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                        nameOfReport = "regCRBorangB1eMLK.rdf";
                                        kodDokumen = kodDokumenDAO.findById("PB1DE");

                                    } else {
                                        nameOfReport = "regCRBorangB2eMLK.rdf";
                                        kodDokumen = kodDokumenDAO.findById("PB2DE");
                                    }
                                } else if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "regCRBorangB1eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");

                                } else {
                                    nameOfReport = "regCRBorangB2eNS.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");
                                }

                                kandunganFolder
                                        = generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen());
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
                                LOG.info(":::::::::::::::ID Hakmilik::::::::::::::::::" + idHakmilik);
                            }
//                        for (int i = 0; i < kodDokumen.size(); i++) {
//                            KodDokumen kd = kodDokumen.get(i);
//                            String reportName2 = nameOfReport[i];
////                            listkandFold = generateReport(kodUrusan, kd, ia, documentPath, reportName2, p);
//                            kandunganFolder = generateReport2(kodUrusan, kd, ia, documentPath, reportName2, p);
//                            senaraiKandunganFolder.add(kandunganFolder);
//                        }
                            LOG.info("Size Kandungan folder untuk cetak " + senaraiKandunganFolder.size());
                        } else if (kodUrusan.getKod().equals("SSHMK") //urusan salinan sah akan dicetak di pendaftar
                                || kodUrusan.getKod().equals("SSSC") || kodUrusan.getKod().equals("SSSW")) {
                            if (kodUrusan.getKod().equals("SSHMK")) {
                                if ("04".equals(conf.getProperty("kodNegeri"))) {
                                    nameOfReport = "regSalinanDHDEml.rdf";
                                } else {
                                    nameOfReport = "regSalinanDHDE.rdf";
                                }

                                KandunganFolder kandunganFolder = new KandunganFolder();
                                List<CarianHakmilik> list = p.getSenaraiHakmilik();

                                for (CarianHakmilik ch2 : list) {
                                    if (ch2 == null) {
                                        continue;
                                    }
                                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                        Hakmilik hm = hakmilikDAO.findById(ch2.getIdHakmilik());
                                        if (hm != null) {
                                            if (hm.getIdHakmilikInduk() != null) {
                                                nameOfReport = "regSalinanSah4KSK_MLK.rdf";
                                            }
                                        }
                                    } else {
                                        Hakmilik hm = hakmilikDAO.findById(ch2.getIdHakmilik());
                                        if (hm != null) {
                                            if (hm.getIdHakmilikInduk() != null) {
                                                nameOfReport = "regSalinanSah4KSK_NS.rdf";
                                            }
                                        }
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

                                    kodDokumen = kodDokumenDAO.findById("SSHMK");

                                    if (hakmilik.getIdHakmilikInduk() != null) {
                                        kandunganFolder
                                                = generateReportStr(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen(), p.getResit().getIdDokumenKewangan(), p.getTrans().getIdTransaksi());
                                        senaraiKandunganFolder.add(kandunganFolder);
                                    } else {
                                        kandunganFolder
                                                = generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                                        senaraiKandunganFolder.add(kandunganFolder);
                                    }

                                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                        if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                            nameOfReport = "regCRBorangB1eMLK.rdf";
                                            kodDokumen = kodDokumenDAO.findById("PB1DE");

                                        } else {
                                            nameOfReport = "regCRBorangB2eMLK.rdf";
                                            kodDokumen = kodDokumenDAO.findById("PB2DE");
                                        }
                                    } else if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                        nameOfReport = "regCRBorangB1eNS.rdf";
                                        kodDokumen = kodDokumenDAO.findById("PB1DE");

                                    } else {
                                        nameOfReport = "regCRBorangB2eNS.rdf";
                                        kodDokumen = kodDokumenDAO.findById("PB2DE");
                                    }
                                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                        Hakmilik hm = hakmilikDAO.findById(ch2.getIdHakmilik());
                                        if (hm != null) {
                                            if (hm.getIdHakmilikInduk() != null) {
                                                nameOfReport = "regSalinanSahPelan_MLK.rdf";
                                                kodDokumen = kodDokumenDAO.findById("PBSTR");

                                            }
                                        }
                                    } else {
                                        Hakmilik hm = hakmilikDAO.findById(ch2.getIdHakmilik());
                                        if (hm != null) {
                                            if (hm.getIdHakmilikInduk() != null) {
                                                nameOfReport = "regSalinanSahPelan_NS.rdf";
                                                kodDokumen = kodDokumenDAO.findById("PBSTR");
                                            }
                                        }
                                    }
                                    if (hakmilik.getIdHakmilikInduk() != null) {
                                        kandunganFolder
                                                = generateReportStr(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen(), p.getResit().getIdDokumenKewangan(), p.getTrans().getIdTransaksi());
                                        senaraiKandunganFolder.add(kandunganFolder);
                                    } else {
                                        kandunganFolder
                                                = generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen());
                                        senaraiKandunganFolder.add(kandunganFolder);
                                    }
//                                    kandunganFolder =
//                                            generateReport2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p.getIdCarian(), idHakmilik, p.getFolderDokumen());
//                                    senaraiKandunganFolder.add(kandunganFolder);

                                    LOG.info(":::::::::::::::ID Hakmilik::::::::::::::::::" + idHakmilik);
                                }
                                LOG.info("Size Kandungan folder untuk cetak " + senaraiKandunganFolder.size());
                            } //                        else if (kodUrusan.getKod().equals("SSHMK")) {
                            ////                            kodDokumen = kodDokumenDAO.findById("SDH");
                            //                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                            //                                nameOfReport = "regSalinanDHDEml.rdf";
                            //                            } else {
                            //                                nameOfReport = "regSalinanDHDE.rdf";
                            //                            }
                            //                        }
                            else if (kodUrusan.getKod().equals("SSSC")) {
                            } else if (kodUrusan.getKod().equals("SSSW")) {
                            }
                            List<CarianHakmilik> list = p.getSenaraiHakmilik();
                            for (CarianHakmilik ch2 : list) {
                                if (ch2 == null) {
                                    continue;
                                }
                                String idHakmilik = ch2.getIdHakmilik();

                                Hakmilik hm = hakmilikDAO.findById(idHakmilik);
                                if (hm != null) {

                                    if (hm.getIdHakmilikInduk() != null) { //hm strata xperlu jana pelan byk kali
                                        continue;
                                    }
                                    List<KandunganFolder> senaraiTmp = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                                    for (KandunganFolder kf : senaraiTmp) {
                                        akf.add(kf);
                                    }

                                }
                            }
                        } //                        else if (kodUrusan.getKod().equals("SSHMK")) {
                        ////                            kodDokumen = kodDokumenDAO.findById("SDH");
                        //                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                        //                                nameOfReport = "regSalinanDHDEml.rdf";
                        //                            } else {
                        //                                nameOfReport = "regSalinanDHDE.rdf";
                        //                            }
                        //                        }
                        else if (kodUrusan.getKod().equals("SSSC")) {
                        } else if (kodUrusan.getKod().equals("SSSW")) {
                        }

                        List<CarianHakmilik> list = p.getSenaraiHakmilik();
                        for (CarianHakmilik ch2 : list) {
                            if (ch2 == null) {
                                continue;
                            }
                            String idHakmilik = ch2.getIdHakmilik();

                            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
                            if (hm != null) {

                                if (hm.getIdHakmilikInduk() != null) { //hm strata xperlu jana pelan byk kali
                                    continue;
                                }
                                List<KandunganFolder> senaraiTmp = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                                for (KandunganFolder kf : senaraiTmp) {
                                    akf.add(kf);
                                }

                            }
                        }

                    } else if (kodUrusan.getKod().equals("CSBBP")) {
                        String nameOfReport = "regCSBBP.rdf";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Rasmi Borang 4K
                    else if (kodUrusan.getKod().equals("CRHS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMSB4K.rdf";
                        } else {
                            // nameOfReport = "REGJadual4_NS.rdf";
                            nameOfReport = "STRSijilCarianHMSB4K.rdf";
                        }
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Persendirian Borang 4K
                    else if (kodUrusan.getKod().equals("CSHS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regCatatanCarianHMSB4K.rdf";
                        } else {
                            //nameOfReport = "REGJadual4_NS.rdf";
                            // nameOfReport = "STRCatatanCarianHM.rdf";
                            nameOfReport = "STRCatatanCarianHMSB4K.rdf";
                        }
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Persendirian Strata Semua borang
                    else if (kodUrusan.getKod().equals("CSR")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "STRroll_MLK.rdf";
                        } else {
                            //nameOfReport = "REGJadual4_NS.rdf";
                            // nameOfReport = "STRCatatanCarianHM.rdf";
                            nameOfReport = "STRroll_NS.rdf";
                        }
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Persendirian Strata Semua borang
                    else if (kodUrusan.getKod().equals("CSHMS")) {
                        /*NOTES: for carian Strata Semua borang, need to generate 3 dokumen,
                         * 'Borang 2', 'Borang 3', and 'Borang 4'. for Borang 2 and Borang 3, <-- only FIX this if wrong
                         * get hakmilik induk, while borang 4, get hakmilik strata  */
                        String nameOfReport = "";
                        String nameOfReport1 = "";
                        String nameOfReport2 = "";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
//                            nameOfReport = "REGB2K_MLK.rdf"; // carian borang 2
//                            nameOfReport1 = "REGB3K_MLK.rdf";    // carian borang 3
//                            nameOfReport2 = "REGJadual4_MLK.rdf";    // carian borang 4
                            nameOfReport = "regCatatanCarianHMSB2K.rdf"; // carian borang 2
                            nameOfReport1 = "regCatatanCarianHMSB3K.rdf";    // carian borang 3
                            nameOfReport2 = "regCatatanCarianHMSB4K.rdf";    // carian borang 4
                        } else {
                            // nameOfReport = "REGB4K_NS.rdf";
//              nameOfReport = "STRCatatanCarianHMSB2K.rdf";  // carian borang 2
//              nameOfReport1 = "STRCatatanCarianHMSB3K.rdf"; // carian borang 3  
                            nameOfReport = "STRB2KCarian_NS.rdf";  // carian borang 2
                            nameOfReport1 = "STRB3KCarian_NS.rdf"; // carian borang 3
                            nameOfReport2 = "STRCatatanCarianHMSB4K.rdf"; // carian borang 4
                        }
                        // generateReport = for dokumen that need hakmilik Strata
                        // generateReportV2 = for dokumen that need hakmilik induk
                        senaraiKandunganFolder = generateReportV2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                        senaraiKandunganFolder1 = generateReportV2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport1, p, dk);
                        senaraiKandunganFolder2 = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport2, p, dk);
                    } //Carian Rasmi Strata semua borang
                    else if (kodUrusan.getKod().equals("CRHMS")) {
                        /*NOTES: for carian Strata Semua borang, need to generate 3 dokumen,
                         * 'Borang 2', 'Borang 3', and 'Borang 4'. for Borang 2 and Borang 3, <-- only FIX this if wrong
                         * get hakmilik induk, while borang 4, get hakmilik strata  */
                        String nameOfReport = "";
                        String nameOfReport1 = "";
                        String nameOfReport2 = "";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMSB2K.rdf";  // carian borang 2
                            nameOfReport1 = "regSijilCarianHMSB3K.rdf";     // carian borang 3
                            nameOfReport2 = "regSijilCarianHMSB4K.rdf";     // carian borang 4
//                            nameOfReport = "REGB2K_MLK.rdf";  // carian borang 2
//                            nameOfReport1 = "REGB3K_MLK.rdf";     // carian borang 3
//                            nameOfReport2 = "REGJadual4_MLK.rdf";     // carian borang 4
                        } else {
                            // nameOfReport = "REGB4K_NS.rdf";
//              nameOfReport = "STRSijilCarianHMSB2K.rdf"; 
                            nameOfReport = "STRB2KCarian_NS.rdf";  // carian borang 2
                            nameOfReport1 = "STRB3KCarian_NS.rdf"; // carian borang 3
                            nameOfReport2 = "STRSijilCarianHMSB4K.rdf"; // carian borang 4
                        }
                        // generateReport = for dokumen that need hakmilik Strata
                        // generateReportV2 = for dokumen that need hakmilik induk
                        senaraiKandunganFolder = generateReportV2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                        senaraiKandunganFolder1 = generateReportV2(kodUrusan, kodDokumen, ia, documentPath, nameOfReport1, p, dk);
                        senaraiKandunganFolder2 = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport2, p, dk);
                    } //Carian Persendirian Borang 2K
                    else if (kodUrusan.getKod().equals("CSIDS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regCatatanCarianHMSB2K.rdf";
                        } else {
                            //nameOfReport = "STRCarianB2K_NS.rdf";
                            nameOfReport = "STRCatatanCarianHMSB2K.rdf";
                        }
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        LOG.info("~~~~ KOD URUSAN ~~~~~~~: " + kodUrusan);
                        LOG.info("~~~~ KOD DOKUMEN ~~~~~~~: " + kodDokumen);
                        LOG.info("~~~~ IA ~~~~~~~: " + ia);
                        LOG.info("~~~~ DOKUMEN PATH ~~~~~~~: " + documentPath);
                        LOG.info("~~~~ REPORT NAME ~~~~~~~: " + nameOfReport);
                        LOG.info("~~~~ P ~~~~~~~: " + p);
                        LOG.info("~~~~ DK ~~~~~~~: " + dk);
                        LOG.info("~~~~ TAMAT ~~~~~~ ");
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Rasmi Borang 2K
                    else if (kodUrusan.getKod().equals("CRIDS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMSB2K.rdf";
//                            nameOfReport = "REGB2K_MLK.rdf";
                        } else {
                            //nameOfReport = "STRCarianB2K_NS.rdf";
//              nameOfReport = "STRSijilCarianHMSB2K.rdf";
                            nameOfReport = "STRB2KCarian_NS.rdf"; // borang 2
                        }
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Persendirian Borang 3K
                    else if (kodUrusan.getKod().equals("CSPDS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regCatatanCarianHMSB3K.rdf";
                        } else {
                            // nameOfReport = "STRCarianB3K_NS.rdf";
                            nameOfReport = "STRCatatanCarianHMSB3K.rdf";
                        }
//                        String nameOfReport = "REGB3K_MLK.rdf";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } //Carian Rasmi Borang 3K
                    else if (kodUrusan.getKod().equals("CRPDS")) {
                        String nameOfReport = "";
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "regSijilCarianHMSB3K.rdf";
                        } else {
                            // nameOfReport = "STRCarianB3K_NS.rdf";
//              nameOfReport = "STRSijilCarianHMSB3K";
                            nameOfReport = "STRB3KCarian_NS.rdf"; // borang 3k
                        }
//                        String nameOfReport = "REGB3K_MLK.rdf";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    } else if (kodUrusan.getKod().equals("CSBBH")) {
                        String nameOfReport = "regCSBBH.rdf";
                        KodDokumen kodDokumen = kodDokumenDAO.findById("SCR"); //fixme
                        senaraiKandunganFolder = generateReport(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, p, dk);
                    }

                    if (debug) {
                        LOG.debug("bil sijil generated = " + senaraiKandunganFolder.size());
                    }

                    List<Long> senaraiDokumen = new ArrayList<Long>();
                    if (senaraiKandunganFolder.size() > 0) {
//            List<Long> senaraiDokumen = new ArrayList<Long>();
                        for (KandunganFolder kf : senaraiKandunganFolder) {
                            akf.add(kf);
                            senaraiDokumen.add(kf.getDokumen().getIdDokumen());
                        }
//                        uv.setIdDokumen1(kf1.getDokumen().getIdDokumen());
                        uv.setSenaraiDokumen(senaraiDokumen);
                    }

                    if (kodUrusan.getKod().equals("CSHMS") || kodUrusan.getKod().equals("CSHMS")) {
                        if (senaraiKandunganFolder1.size() > 0) {
//              List<Long> senaraiDokumen = new ArrayList<Long>();
                            for (KandunganFolder kf : senaraiKandunganFolder1) {
                                akf.add(kf);
                                senaraiDokumen.add(kf.getDokumen().getIdDokumen());
                            }
                            uv.setSenaraiDokumen(senaraiDokumen);
                        }
                        if (senaraiKandunganFolder2.size() > 0) {
//              List<Long> senaraiDokumen = new ArrayList<Long>();
                            for (KandunganFolder kf : senaraiKandunganFolder2) {
                                akf.add(kf);
                                senaraiDokumen.add(kf.getDokumen().getIdDokumen());
                            }
                            uv.setSenaraiDokumen(senaraiDokumen);
                        }
                    }

                    if (tajukFolder.length() > 0) {
                        tajukFolder.append(",");
                    }
                    tajukFolder.append(p.getIdCarian());

                    /* Save Surat Akuan Penerimaan */
                    Dokumen akuanPenerimaan = new Dokumen();
                    akuanPenerimaan.setKodDokumen(kodDokumenDAO.findById("UNKN1"));
                    akuanPenerimaan.setFormat("application/pdf");
                    akuanPenerimaan.setInfoAudit(ia);
                    akuanPenerimaan.setKlasifikasi(klasifikasiAm);
                    akuanPenerimaan.setNoVersi("1.0");
                    akuanPenerimaan.setTajuk("Akuan Penerimaan " + p.getIdCarian());
                    dokumenDAO.save(akuanPenerimaan);
                    KandunganFolder kf1 = new KandunganFolder();
                    kf1.setFolder(fd);
                    kf1.setDokumen(akuanPenerimaan);
                    kf1.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(kf1);
                    uv.setAkuanPenerimaan(akuanPenerimaan);
                    senaraiPermohonan.add(uv);
                    cntPermohonan++;
                }
                fd.setTajuk(tajukFolder.toString());
                folderDokumenDAO.save(fd);
            }
            tx = s.beginTransaction();

            // update akaun kutipan harian
            if (!jumlahCaj.equals(BigDecimal.ZERO)) {
                s.lock(akTerima, LockMode.UPGRADE);
                akTerima.setBaki(akTerima.getBaki().add(jumlahCaj));
            }
            tx.commit();
            resitNo = dk.getIdDokumenKewangan();
            tx = s.beginTransaction();

            // generate report for resit
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator) + String.valueOf(resit.getIdDokumen());
            if (debug) {
                LOG.debug("HSLResitPelbagai" + path);
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("HSLResitPelbagai_MLK_Carian.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path, pengguna);
            } else {
                reportUtil.generateReport("HSLResitPelbagai_NS_Carian.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path, pengguna);
            }
            resit.setNamaFizikal(reportUtil.getDMSPath());
            resit.setFormat(reportUtil.getContentType());
            getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
            dokumenDAO.update(resit);

            // generate Surat Akuan Penerimaan for each Permohonan
            for (UrusanValue uv : senaraiPermohonan) {
                if (uv.getAkuanPenerimaan() == null) {
                    continue;
                }

                path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
                String path2 = "AkuanPenerimaan" + File.separator + uv.getAkuanPenerimaan().getIdDokumen();
                if (debug) {
                    LOG.debug("HSLResitAkuanPenerimaan=" + path + path2);
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    LOG.info("kod urusan report akuan penerimaan : " + kodUrusan.getKod());
                    reportUtil.generateReport("HSLResitAkuanPenerimaan_Carian_MLK.rdf",
                            new String[]{"p_id_carian"},
                            new String[]{uv.getIdPermohonan()},
                            path + path2, pengguna);
                } else if (kodUrusan.getKod().equals("CRHS") || kodUrusan.getKod().equals("CSHS") || kodUrusan.getKod().equals("CSR")) {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan_Carian_NS_Strata.rdf",
                            new String[]{"p_id_carian"},
                            new String[]{uv.getIdPermohonan()},
                            path + path2, pengguna);
                } else {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan_Carian_NS.rdf",
                            new String[]{"p_id_carian"},
                            new String[]{uv.getIdPermohonan()},
                            path + path2, pengguna);
                }
                uv.getAkuanPenerimaan().setNamaFizikal(reportUtil.getDMSPath());
                uv.getAkuanPenerimaan().setFormat(reportUtil.getContentType());
                dokumenDAO.update(uv.getAkuanPenerimaan());
            }
            tx.commit();
            //new requirement by hasil's team
            penyataPemungutService.savePenyataPemungut(dk);
        } catch (Exception e) {
            LOG.error(e);
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
        } finally {
            noResitGenerator2.commitAndUnlockSerialNo(pengguna);
        }
        // clear session
        ctx.removeWorkdata();
//        addSimpleMessage("Urusan telah berjaya didaftarkan.");
        if (ArrayUtils.contains(CARIAN_SECARA_MANUAL, kodUrusan.getKod())) {
            addSimpleMessage("Permohonan carian berjaya. Sila dapat dokumen secara manual.");
        } else if (jenisCarian.equals("CR")) {
            addSimpleMessage("Permohonan carian berjaya. Sila cetak dokumen carian rasmi.");
        } else if (jenisCarian.equals("CS")) {
            addSimpleMessage("Permohonan carian Berjaya. Sila cetak dokumen carian persendirian.");
        } else if (jenisCarian.equals("SS")) {
            addSimpleMessage("Permohonan carian Berjaya. Sila ke Pendaftar untuk cetakan Salinan Sah.");
        }
        caraBayaranList = caraBayar(resitNo, dk);
//        caraBayaranList = senaraiCaraBayaran;
        LOG.info("caraBayaranList.size() : " + caraBayaranList.size());
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_cetak_resit.jsp");
    }

    @HandlesEvent("editUrusan")
    @DontValidate
    public Resolution editUrusanCache() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanCache> luc = getSessionCache(ctx);

        if (selectedItem < 0 || selectedItem >= luc.size()) {
            getContext().getRequest().setAttribute("carian", true);
            addSimpleError(
                    "Item tidak sah");

            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");

        }

        loadUrusan(selectedItem);

        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");

    }

    @HandlesEvent("removeUrusan")
    @DontValidate
    public Resolution removeUrusanCache() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanCache> luc = getSessionCache(ctx);

        if (selectedItem < 0 || selectedItem >= luc.size()) {
            addSimpleError("Item tidak sah");

            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");

        }

        luc.remove(selectedItem);

        if (luc.size() > 0) {
            // need to update UrusanValue.position
            for (int i = 0; i
                    < luc.size(); i++) {
                UrusanCache uc = luc.get(i);

                for (UrusanValue uv : uc.senaraiUrusan) {
                    uv.setPosition(i);

                }
            }
        } else { // no more Urusan to register
            addSimpleMessage("Tiada urusan untuk didaftarkan");

            return selectTransaction();

        }

        addSimpleMessage("Urusan yang dipilih telah dihapuskan.");

        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");

    }

    public Resolution updatePenyerah() {
        String kod = getContext().getRequest().getParameter("kod");
        getContext().getRequest().setAttribute("carian", true);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if ("04".equals(conf.getKodNegeri())) {
            kodNeg = "melaka";
        } else {
            kodNeg = "n9";
        }

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

        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");

    }

    @HandlesEvent("Cancel")
    public Resolution cancel() {
        resetUrusan();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> senaraiUrusan = getAllUrusanFromSession(ctx);

        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");

        } else {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");

        }
    }

    @HandlesEvent("CancelAll")
    public Resolution cancelAll() {
        resetAll();
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian/carian_main.jsp");
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

    /**
     * Given the list of idHakmilik both nonserial and serial, compose a string
     * to display the complete list
     *
     * @param ku
     * @param hakmilikPermohonan
     * @param idHakmilikSiriDari
     * @param idHakmilikSiriKe
     * @return
     */
    private String getSenaraiHakmilikUrusan(ArrayList<CarianHakmilik> hakmilikPermohonan,
            ArrayList<String> idHakmilikSiriDari, ArrayList<String> idHakmilikSiriKe) {
        LOG.info("/* getSenaraiHakmilikUrusan */");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.setGroupingUsed(false);
        StringBuilder list = new StringBuilder();
        int s = hakmilikPermohonan.size();
        for (int j = 0; j
                < s; j++) {
            CarianHakmilik hp = hakmilikPermohonan.get(j);
            if (debug) {
                LOG.debug("hakmilik terlibat" + hp.getIdHakmilik());
            }
            if (hp.getIdHakmilik() != null
                    && hp.getIdHakmilik().trim().length() > 0) {
                if (j != 0) {
                    list.append(", ");
                }
                list.append(hp.getIdHakmilik());
                //untuk hakmilik strata
                if (StringUtils.isNotBlank(hp.getNoBangunan())
                        && StringUtils.isNotBlank(hp.getNoTingkat())
                        && StringUtils.isNotBlank(hp.getNoPetak())) {
                    LOG.info("jenis katg bngn M");
                    list.append(" (" + hp.getNoBangunan() + "/");
                    list.append(hp.getNoTingkat() + "/");
                    list.append(hp.getNoPetak() + ")");
                } else if (StringUtils.isNotBlank(hp.getNoPetak())) {
                    LOG.info("jenis katg bngn L");
                    list.append(" (" + hp.getNoPetak() + ")");
                } else if (StringUtils.isNotBlank(hp.getNoBangunan()) && StringUtils.isBlank(hp.getNoPetak())){
                    LOG.info("jenis katg bngn P & PL");
                    hakmilik = strataPtService.findCarianHakmilikStrataByHakmilikIndukKATGBangunan(hp.getIdHakmilik(), hp.getNoBangunan());
                    LOG.info("petak" + hakmilik);
                            if (hakmilik != null) {
                                String kkb = hakmilik.getKodKategoriBangunan().getKod();
                                if ("PL".equals(kkb) || "P".equals(kkb)) {
                                    list.append(" (" + hp.getNoBangunan() + ")");
                                }
                            }   
                }

            } else if (hp.getIdPerserahan() != null) {
                if (j != 0) {
                    list.append(", ");
                }
                if (debug) {
                    LOG.debug("perserahan terlibat" + hp.getIdPerserahan());
                }
                list.append(hp.getIdPerserahan());
            }
        }
        // hakmilik bersiri
        if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
            for (int k = 0; k
                    < idHakmilikSiriDari.size(); k++) {
                String dr = idHakmilikSiriDari.get(k);
                String ke = idHakmilikSiriKe.get(k);
                if (dr != null && dr.trim().length() > 0 && ke != null && ke.trim().length() > 0) {
                    if (dr.trim().length() > 20 && ke.trim().length() > 20) {
                        // for hakmilik Strata Bersiri
                        appendIdHakmilikBersiriStrata(list, dr, ke, df);
                    } else {
                        appendIdHakmilikBersiri(list, dr, ke, df);
                    }
                }
            }
        }
        LOG.info("--> list : " + list);
        return list.toString();
    }

    private final static void appendIdHakmilikBersiri(StringBuilder list, String idHakmilikDari,
            String idHakmilikKe, DecimalFormat df) {
        // from
        StringBuilder from = new StringBuilder();

        for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
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
        long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
        if (list.length() > 0) {
            list.append(", ");
        }

        list.append(idHakmilikDari).append(", ");
        df.setMinimumIntegerDigits(from.length());

        for (long l = lFrom + 1; l < lTo; l++) {
            String id = pre + df.format(l);
            list.append(id).append(", ");
        }
        list.append(idHakmilikKe);
    }

    // add by azri 3/9/2013 : use this to get hakmilik strata per series
    private final void appendIdHakmilikBersiriStrata(StringBuilder list, String idHakmilikDari,
            String idHakmilikKe, DecimalFormat df) {
        LOG.info("/* appendIdHakmilikBersiriStrata */");

        StringBuilder from = new StringBuilder();
        for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
            char c = idHakmilikDari.charAt(i);
            if (c >= '0' && c <= '9') {
                from.insert(0, c);
            } else {
                break;
            }
        }

        long lFrom = Long.parseLong(from.toString()); // from
        String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());
        long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length())); // to

        // get all id hakmilik strata per series
        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        listIdHakmilik.add(idHakmilikDari);
        listIdHakmilik.add(idHakmilikKe);
        df = (DecimalFormat) DecimalFormat.getInstance();
        df.setGroupingUsed(false);
        df.setMinimumIntegerDigits(from.length());
        for (long l = lFrom + 1; l < lTo; l++) {
            String id = pre + df.format(l);
            listIdHakmilik.add(id);
            LOG.info(id + ",");
        }

        List<Hakmilik> listHM = getSenaraiHakmilik(listIdHakmilik);
        for (int j = 0; j < listHM.size(); j++) {
            Hakmilik hp = listHM.get(j);
            LOG.info("--> idHakmilikStrata : " + hp.getIdHakmilik());
            if (hp.getIdHakmilik() != null
                    && hp.getIdHakmilik().trim().length() > 0) {
                if (j != 0) {
                    list.append(", ");
                }
                list.append(hp.getIdHakmilikInduk());

                // get "No Bangn", "No Tingkat" and "No Petak"
                if (StringUtils.isNotBlank(hp.getNoBangunan())
                        && StringUtils.isNotBlank(hp.getNoTingkat())
                        && StringUtils.isNotBlank(hp.getNoPetak())) {
                    list.append(" (" + hp.getNoBangunan() + "/");
                    list.append(hp.getNoTingkat() + "/");
                    list.append(hp.getNoPetak() + ")");
                }
            }
        }
    }

    public List<Hakmilik> getSenaraiHakmilik(List<String> listIdHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT h FROM Hakmilik h WHERE h.idHakmilik in (:listHakmilik)");
        q.setParameterList("listHakmilik", listIdHakmilik);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    private final void saveUrusanToSession(etanahActionBeanContext ctx) {
        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
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
        u.senaraiDokumenSerahan = senaraiDokumenSerahan;

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

    private void checkHakmilikPerserahanValid(ArrayList<UrusanCache> txnGroups) {
        for (UrusanCache urusanCache : txnGroups) {
        }
    }

    private void resetUrusan() {
        if (debug) {
            LOG.debug("invoked");

        }
        senaraiUrusan = null;
        hakmilikPermohonan = null;
        idHakmilikSiriDari = null;
        idHakmilikSiriKe = null;
        senaraiDokumenSerahan = null;

    }

    private void resetAll() {
        if (debug) {
            LOG.debug("invoked reset All");

        }
        resetUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        idPenyerah = null;
        penyerahKod = null;
        penyerahJenisPengenalan = null;
        penyerahNoPengenalan = null;
        penyerahNama = null;
        penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4
                = penyerahPoskod = penyerahEmail = penyerahNoTelefon = null;
        penyerahNegeri = null;
        selectedItem = -1;
//        jenisCarian = "";
        if (senaraiCaraBayaran != null) {
            senaraiCaraBayaran.clear();
        }

    }

    private void loadUrusan(int whichUrusanCacheIndex) {
        List<UrusanCache> luc = (List<UrusanCache>) ((etanahActionBeanContext) getContext()).getWorkData();

        if (luc == null || whichUrusanCacheIndex >= luc.size()) {
            LOG.error("Data simpanan sementara telah korup. Anda perlu mula semula.");

            throw new RuntimeException("Data simpanan sementara telah korup. Anda perlu mula semula.");

        }

        UrusanCache u = luc.get(whichUrusanCacheIndex);
        senaraiUrusan = u.senaraiUrusan;
        hakmilikPermohonan = u.hakmilikPermohonan;

        if (debug) {
            LOG.debug("hakmilikPermohonan.size=" + hakmilikPermohonan.size());

        }
        idHakmilikSiriDari = u.idHakmilikSiriDari;
        idHakmilikSiriKe = u.idHakmilikSiriKe;

    }

    public List<TransaksiValue> getSenaraiTransaksiSemasa() {
        jumlahCaj = BigDecimal.ZERO;

        ArrayList<TransaksiValue> senaraiTransaksi = new ArrayList<TransaksiValue>();
        // prepare list of hakmilik
        String senaraiHakmilik = getSenaraiHakmilikUrusan(hakmilikPermohonan,
                idHakmilikSiriDari, idHakmilikSiriKe);

        String[] senaraiHakmilikArray = senaraiHakmilik.split(", ");

        int bilHakmilikTerlibat = 1;

        if (senaraiHakmilikArray.length > 0) {
            bilHakmilikTerlibat = senaraiHakmilikArray.length;
        }

        UrusanValue firstUrusan = null;
        TransaksiValue firstTransaksi = null;

        for (int i = 0; i
                < senaraiUrusan.size(); i++) {
            UrusanValue uv = senaraiUrusan.get(i);

            if (debug) {
                LOG.debug("processing " + uv.getKodUrusan());

            }
            if (isUrusanNull(uv)) {
                continue;

            }
            if (firstUrusan == null) {
                firstUrusan = uv;

            }

            TransaksiValue t = new TransaksiValue();
            KodUrusan ku = kodUrusanDAO.findById(uv.getKodUrusan());
            t.setKodUrusan(uv.getKodUrusan());
            t.setNamaUrusan(ku.getNama());
            t.setSenaraiHakmilik(senaraiHakmilik);
            t.setUtkUrusan(uv);

            BigDecimal caj = ku.getCaj();

//            if (ku.getKod().equals("CRHM") || ku.getKod().equals("CSHM")
//                    || ku.getKod().equals("CRHMB")
//                    || ku.getKod().equals("CSHMB")
//                    || ku.getKod().equals("SSHMK")
//                    || ku.getKod().equals("SSHM")) {
//                    caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
//                    t.setKuantiti(bilHakmilikTerlibat);
//                } else if (ku.getKod().equals("SSDOK")) {
//                    caj = caj.multiply(new BigDecimal(bilHakmilik));
//                    t.setKuantiti(bilHakmilik);
//                }
//            if (ku.getKod().equals("CSHMS") || ku.getKod().equals("CRHMS")) {
//                caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
//                BigDecimal c = BigDecimal.ZERO;
//                List<UrusanDokumen> lud = getSenaraiDokumenSemuaBorang();
//                if (lud.size() > 0) {
//                    for (UrusanDokumen ud : lud) {
//                        for (DokumenValue d : senaraiDokumenSerahan) {
//                            if (d == null) {
//                                continue;
//                            }
//
//                            if (d != null && d.getKodDokumen() != null && ud.getKodDokumen().getKod().equals(d.getKodDokumen())) {
//                                c = ud.getCaj();
//                                c = c.multiply(new BigDecimal(d.getBil()));
//                                caj = caj.add(c);
//                            }
//                        }
//                    }
//                }
//            } else
            if (ku.getKod().equals("CSR")) {
                if (kosRendah.equals("Ya")) {
                    caj = caj.multiply(new BigDecimal(bilHakmilik1));
                    int bilHm = Integer.parseInt(bilHakmilik1);
                    LOG.info("bilHakmilik1--" + bilHakmilik1);
                    LOG.info("kosRendah--" + kosRendah);

                    if (bilHm <= 10) {
                        caj = new BigDecimal(30);
                    } else if (bilHm >= 20) {
                        caj = new BigDecimal(100);
                    } else {
                        caj = new BigDecimal(5);
                        caj = caj.multiply(new BigDecimal(bilHakmilik1));
                    }
                } else {
                    caj = caj.multiply(new BigDecimal(bilHakmilik2));
                    int bilHm = Integer.parseInt(bilHakmilik2);
                    LOG.info("bilHakmilik2--" + bilHakmilik2);
                    LOG.info("kosRendah--" + kosRendah);

                    if (bilHm <= 10) {
                        caj = new BigDecimal(50);
                    } else if (bilHm >= 11) {
                        caj = new BigDecimal(500);
                    } else {
                        caj = new BigDecimal(50);
                        caj = caj.multiply(new BigDecimal(bilHakmilik2));
                    }
                }
            }
            if (ku.getKod().equals("SSDOK")) {
                caj = caj.multiply(new BigDecimal(bilHakmilik));
                if (bilHakmilik > 10) {
                    caj = new BigDecimal(40);
                    caj = caj.multiply(new BigDecimal(bilHakmilik));
                }
                t.setKuantiti(bilHakmilik);
            } else if (ku.getKod().equals("SSLSC")) {
//                caj = caj.multiply(new BigDecimal(bilHakmilik));
                if ("04".equals(conf.getKodNegeri())) {
                    caj = new BigDecimal(50);
                    if (bilHakmilik > 4) {
                        int a = bilHakmilik - 4;
                        caj = new BigDecimal(10);
                        caj = caj.multiply(new BigDecimal(a)).add(new BigDecimal(50));
                    }
                } else {
                    BigDecimal divisor = new BigDecimal(10);
                    BigDecimal val = new BigDecimal(bilHakmilik);
                    BigDecimal result = val.divide(divisor, RoundingMode.CEILING);
                    caj = caj.multiply(result);
                }

                t.setKuantiti(bilHakmilik);
            } else {
                caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
                t.setKuantiti(bilHakmilikTerlibat);
            }

//            t.setAmaun(ku.getCaj());
            t.setAmaun(caj);
            jumlahCaj = jumlahCaj.add(t.getAmaun());

            if (ku.getKodTransaksi() != null) {
                t.setKodTransaksi(ku.getKodTransaksi().getKod());
            } else {
                t.setAmaun(BigDecimal.ZERO);
            } // TODO t.kodTransaksi
            senaraiTransaksi.add(t);

            ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
            senaraiKodUrusan.add(ku.getKod());
            // caj based on documents submittted
            List<UrusanDokumen> lud = kaunterService.getUrusanDokumen(senaraiKodUrusan);
            for (DokumenValue dv : senaraiDokumenSerahan) {
                if (dv != null && dv.getKodDokumen() != null) {
                    for (int k = 0; k < lud.size(); k++) {
                        if (lud.get(k).getKodDokumen().getKod().equals(dv.getKodDokumen())) {
                            BigDecimal caj2 = lud.get(k).getCaj();
                            if (caj2 != null && (caj2.compareTo(BigDecimal.ZERO)) > 0) {
                                // create a new transaction
                                TransaksiValue dokumenT = new TransaksiValue();
                                dokumenT.setKodUrusan(ku.getKod());
                                dokumenT.setNamaUrusan(dv.getKodDokumen());
                                // t3.senaraiHakmilik = t.senaraiHakmilik;
                                dokumenT.setKuantiti(dv.getBil());
                                dokumenT.setAmaun(caj2.multiply(new BigDecimal(dv.getBil())));
                                dokumenT.setUtkUrusan(uv);
                                LOG.debug("kod =" + lud.get(k).getKodTransaksi().getKod());
                                dokumenT.setKodTransaksi(lud.get(k).getKodTransaksi().getKod());
                                senaraiTransaksi.add(dokumenT);
                                jumlahCaj = jumlahCaj.add(dokumenT.getAmaun());
                            }
                            break;
                        }
                    }
                }
            }

            if (firstTransaksi == null) {
                firstTransaksi = t;

            }
        }

        jumlahCaj.setScale(2);

        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj.toPlainString());

        }

        return senaraiTransaksi;

    }

    /**
     * Prepare senarai Transaksi based on session data. Also will set jumlahCaj.
     *
     * @param txnGroups
     */
    public List<TransaksiValue> getSenaraiTransaksi() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> txnGroups = (ArrayList<UrusanCache>) ctx.getWorkData();

        jumlahCaj = BigDecimal.ZERO;

        ArrayList<TransaksiValue> senaraiTransaksi = new ArrayList<TransaksiValue>();

        int count = 0;

        for (UrusanCache uc : txnGroups) {
            // prepare list of hakmilik
            String senaraiHakmilik = getSenaraiHakmilikUrusan(uc.hakmilikPermohonan,
                    uc.idHakmilikSiriDari, uc.idHakmilikSiriKe);

            String[] senaraiHakmilikArray = senaraiHakmilik.split(", ");

            int bilHakmilikTerlibat = 1;
//            if (uc.hakmilikPermohonan.size() > 0) {
//                bilHakmilikTerlibat = uc.hakmilikPermohonan.size();
//            }

            if (senaraiHakmilikArray.length > 0) {
                bilHakmilikTerlibat = senaraiHakmilikArray.length;

            } // determine hakmilik daerah/pendaftar
            boolean milikDaerah = true;

            if (uc.hakmilikPermohonan.size() > 0) {
                // check only the first one
                if (uc.hakmilikPermohonan.get(0).getIdHakmilik() != null) {
                    Hakmilik hm = hakmilikDAO.findById(uc.hakmilikPermohonan.get(0).getIdHakmilik().toUpperCase());
                    milikDaerah = hm.getKodHakmilik().getMilikDaerah() == 'Y';
                }
            }

            UrusanValue firstUrusan = null;
            TransaksiValue firstTransaksi = null;

            for (int i = 0; i
                    < uc.senaraiUrusan.size(); i++) {
                UrusanValue uv = uc.senaraiUrusan.get(i);

                if (debug) {
                    LOG.debug("processing " + uv.getKodUrusan());

                }
                if (isUrusanNull(uv)) {
                    continue;

                }
                if (firstUrusan == null) {
                    firstUrusan = uv;

                }

                TransaksiValue t = new TransaksiValue();
                KodUrusan ku = kodUrusanDAO.findById(uv.getKodUrusan());
                t.setPosition(count);
                t.setKodUrusan(uv.getKodUrusan());
                t.setNamaUrusan(ku.getNama());
                t.setSenaraiHakmilik(senaraiHakmilik);
                t.setUtkUrusan(uv);
                BigDecimal caj = ku.getCaj();
                // extra charges if hakmilik pendaftar
//                if (!milikDaerah && ku.getCajTambahanHakmilikPendaftar() != null) {
//                    caj = caj.add(ku.getCajTambahanHakmilikPendaftar());
//                }
                //utk carian rasmi, caj adalah per hakmilik
                //manakala untuk carian persendirian, caj per transaksi
                LOG.debug("bil hakmilik = " + bilHakmilikTerlibat);
                LOG.debug(caj);

//                if (ku.getKod().equals("CRHM") || ku.getKod().equals("CSHM")
//                        || ku.getKod().equals("SSHMK") || ku.getKod().equals("SSHM")) {
//                    caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
//                    t.setKuantiti(bilHakmilikTerlibat);
//
//
//                } else if (ku.getKod().equals("SSDOK")) {
//                    caj = caj.multiply(new BigDecimal(bilHakmilik));
//                    t.setKuantiti(bilHakmilik);
//
//
//                }
//                if (ku.getKod().equals("CSHMS") || ku.getKod().equals("CRHMS")) {
//                    caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
//                    BigDecimal c = BigDecimal.ZERO;
//                    senaraiUrusan = uc.senaraiUrusan;
//                    List<UrusanDokumen> lud = getSenaraiDokumenSemuaBorang();
//                    if (lud.size() > 0) {
//                        for (UrusanDokumen ud : lud) {
//                            for (DokumenValue d : uc.senaraiDokumenSerahan) {
//                                if (d == null) {
//                                    continue;
//                                }
//
//                                if (d != null && d.getKodDokumen() != null && ud.getKodDokumen().getKod().equals(d.getKodDokumen())) {
//                                    c = ud.getCaj();
//                                    c = c.multiply( new BigDecimal(d.getBil()) );
//                                    caj = caj.add(c);
//                                }
//                            }
//                        }
//                    }
//                } else
                if (ku.getKod().equals("CSR")) {
                    if (kosRendah.equals("Ya")) {
                        caj = caj.multiply(new BigDecimal(bilHakmilik1));
                        int bilHm = Integer.parseInt(bilHakmilik1);
                        LOG.info("bilHakmilik1--" + bilHakmilik1);
                        LOG.info("kosRendah--" + kosRendah);

                        if (bilHm <= 10) {
                            caj = new BigDecimal(30);
                        } else if (bilHm >= 20) {
                            caj = new BigDecimal(100);
                        } else {
                            caj = new BigDecimal(5);
                            caj = caj.multiply(new BigDecimal(bilHakmilik1));
                        }
                    } else {
                        caj = caj.multiply(new BigDecimal(bilHakmilik2));
                        int bilHm = Integer.parseInt(bilHakmilik2);
                        LOG.info("bilHakmilik2--" + bilHakmilik2);
                        LOG.info("kosRendah--" + kosRendah);

                        if (bilHm <= 10) {
                            caj = new BigDecimal(50);
                        } else if (bilHm >= 11) {
                            caj = new BigDecimal(500);
                        } else {
                            caj = new BigDecimal(50);
                            caj = caj.multiply(new BigDecimal(bilHakmilik2));
                        }
                    }
                }
                if (ku.getKod().equals("SSDOK")) {
                    caj = caj.multiply(new BigDecimal(bilHakmilik));
                    t.setKuantiti(bilHakmilik);
                } else if (ku.getKod().equals("SSLSC")) {
                    if ("04".equals(conf.getKodNegeri())) {
                        caj = new BigDecimal(50);
                        if (bilHakmilik > 4) {
                            int a = bilHakmilik - 4;
                            caj = new BigDecimal(10);
                            caj = caj.multiply(new BigDecimal(a)).add(new BigDecimal(50));
                        }
                    } else {
                        BigDecimal divisor = new BigDecimal(10);
                        BigDecimal val = new BigDecimal(bilHakmilik);
                        BigDecimal result = val.divide(divisor, RoundingMode.CEILING);
                        caj = caj.multiply(result);
                    }
                    t.setKuantiti(bilHakmilik);
                } else {
                    caj = caj.multiply(new BigDecimal(bilHakmilikTerlibat));
                    t.setKuantiti(bilHakmilikTerlibat);
                }

                LOG.debug(caj);

//                t.setKuantiti(bilHakmilikTerlibat);
                t.setAmaun(caj);

                if (ku.getKodTransaksi() != null) {
                    t.setKodTransaksi(ku.getKodTransaksi().getKod());

                } else {
                    t.setAmaun(BigDecimal.ZERO);

                } // TODO t.kodTransaksi
                senaraiTransaksi.add(t);

                ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
                senaraiKodUrusan.add(ku.getKod());
                // caj based on documents submittted
                List<UrusanDokumen> lud = kaunterService.getUrusanDokumen(senaraiKodUrusan);
                for (DokumenValue dv : uc.senaraiDokumenSerahan) {
                    if (dv != null && dv.getKodDokumen() != null) {
                        for (int k = 0; k < lud.size(); k++) {
                            if (lud.get(k).getKodDokumen().getKod().equals(dv.getKodDokumen())) {
                                BigDecimal caj2 = lud.get(k).getCaj();
                                if (caj2 != null && (caj2.compareTo(BigDecimal.ZERO)) > 0) {
                                    // create a new transaction
                                    TransaksiValue dokumenT = new TransaksiValue();
                                    dokumenT.setKodUrusan(ku.getKod());
                                    dokumenT.setNamaUrusan(dv.getKodDokumen());
                                    // t3.senaraiHakmilik = t.senaraiHakmilik;
                                    dokumenT.setKuantiti(dv.getBil());
                                    dokumenT.setAmaun(caj2.multiply(new BigDecimal(dv.getBil())));
                                    dokumenT.setUtkUrusan(uv);
                                    LOG.debug("kod =" + lud.get(k).getKodTransaksi().getKod());
                                    dokumenT.setKodTransaksi(lud.get(k).getKodTransaksi().getKod());
                                    senaraiTransaksi.add(dokumenT);
                                    jumlahCaj = jumlahCaj.add(dokumenT.getAmaun());
                                }
                                break;
                            }
                        }
                    }
                }

                if (firstTransaksi == null) {
                    firstTransaksi = t;

                }

                // calculation for pelepasan/pengecualian/notis/bayaran fail/lain2
                final BigDecimal NEGATION = new BigDecimal(-1);

                if (uv.getCajPelepasan() != null && !uv.getCajPelepasan().equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.setPosition(count);
                    t4.setKodUrusan(uv.getKodUrusan());
                    t4.setNamaUrusan("Pelepasan");
                    t4.setSenaraiHakmilik(t.getSenaraiHakmilik());
                    t4.setAmaun(uv.getCajPelepasan().multiply(NEGATION));
                    t4.setUtkUrusan(uv);
                    // TODO get proper kods for this!
                    t4.setKodTransaksi(t.getKodTransaksi());
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.getAmaun());

                }
                if (uv.getCajPengecualian() != null && !uv.getCajPengecualian().equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.setPosition(count);
                    t4.setKodUrusan(uv.getKodUrusan());
                    t4.setNamaUrusan("Pengecualian");
                    t4.setSenaraiHakmilik(t.getSenaraiHakmilik());
                    t4.setAmaun(uv.getCajPengecualian().multiply(NEGATION));
                    t4.setUtkUrusan(uv);
                    // TODO get proper kods for this!
                    t4.setKodTransaksi(t.getKodTransaksi());
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.getAmaun());

                }
                if (uv.getCajNotis() != null && !uv.getCajNotis().equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.setPosition(count);
                    t4.setKodUrusan(uv.getKodUrusan());
                    t4.setNamaUrusan("Notis");
                    t4.setSenaraiHakmilik(t.getSenaraiHakmilik());
                    t4.setAmaun(uv.getCajNotis());
                    t4.setUtkUrusan(uv);
                    // TODO get proper kods for this!
                    t4.setKodTransaksi(t.getKodTransaksi());
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.getAmaun());

                }
                if (uv.getCajFail() != null && !uv.getCajFail().equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.setPosition(count);
                    t4.setKodUrusan(uv.getKodUrusan());
                    t4.setNamaUrusan("Bayaran Fail");
                    t4.setSenaraiHakmilik(t.getSenaraiHakmilik());
                    t4.setAmaun(uv.getCajFail());
                    t4.setUtkUrusan(uv);
                    // TODO get proper kods for this!
                    t4.setKodTransaksi(t.getKodTransaksi());
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.getAmaun());

                }
                if (uv.getCajLain() != null && !uv.getCajLain().equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.setPosition(count);
                    t4.setKodUrusan(uv.getKodUrusan());
                    t4.setNamaUrusan("Bayaran-bayaran Lain");
                    t4.setSenaraiHakmilik(t.getSenaraiHakmilik());
                    t4.setAmaun(uv.getCajLain());
                    t4.setUtkUrusan(uv);
                    // TODO get proper kods for this!
                    t4.setKodTransaksi(t.getKodTransaksi());
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.getAmaun());

                }
                jumlahCaj = jumlahCaj.add(t.getAmaun());

            }
            count++;

        }

        jumlahCaj.setScale(2);

        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj.toPlainString());

        }

        return senaraiTransaksi;

    }

    public List<CaraBayaran> caraBayar(String noResit, DokumenKewangan dk) {
        List<CaraBayaran> cb = new ArrayList<CaraBayaran>();

        String[] n = {"dokumenKewangan"};
        Object[] v = {dk};
        List<DokumenKewanganBayaran> listDKB = dokumenKewanganBayaranDAO.findByEqualCriterias(n, v, null);

        CaraBayaran cBayar = new CaraBayaran();

        for (DokumenKewanganBayaran dkb : listDKB) {
            cBayar = dkb.getCaraBayaran();
            KodBank kodBank = new KodBank();

            KodCaraBayaran kodCaraBayar = kodCaraBayaranDAO.findById(dkb.getCaraBayaran().getKodCaraBayaran().getKod());
            if (dkb.getCaraBayaran().getBank() != null) {
                kodBank = kodBankDAO.findById(dkb.getCaraBayaran().getBank().getKod());
            }
            cBayar.setKodCaraBayaran(kodCaraBayar);
            cBayar.setBank(kodBank);
            cb.add(cBayar);
        }
        caraBayaranList.addAll(cb);

        return caraBayaranList;
    }

    @SuppressWarnings("unchecked")
    private static final void ensureListCapacity(List l, int c) {
        if (l.size() < c) {
            for (int i = l.size(); i
                    <= c; i++) {
                l.add(null);

            }
        }
    }

    private static final boolean isUrusanNull(UrusanValue uv) {
        return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));

    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) throws ParseException {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();

        int count = 0;
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                // Cheque's Date
                if (!crByr.getKod().equals("T")) {
                    Date d = sdf.parse(tarikhCek.get(count));
                    cb.setTarikhCek(d);
                }
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                if (returnBalance) {
                    cb.setAmaun(jumlahCaj);
                }
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
            count++;
        }
        dk.setSenaraiBayaran(adkb);

    }

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

    private KandunganFolder generateReportStr(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, String idCarian, String idHakmilik, FolderDokumen fd, String resit, Long idTrans) {
        if (debug) {
            LOG.debug("generateReport");
        }

        String[] params = null;
        String[] values = null;

        KandunganFolder kFolder = new KandunganFolder();

        params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_carian", "p_resit", "p_trans", "p_id_pguna"};
        values = new String[]{idCarian, idHakmilik, idCarian, resit, String.valueOf(idTrans), ia.getDimasukOleh().getIdPengguna()};

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

        dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(dokumenCarian);

        return kFolder;
    }

    private PermohonanCarian savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan,
            UrusanValue uv, FolderDokumen fd,
            List<CarianHakmilik> hakmilikPermohonan,
            List<String> idHakmilikSiriDari, List<String> idHakmilikSiriKe, InfoAudit ia, DokumenKewangan dk, Session s,
            List<Map<String, Transaksi>> mapTrans, int bil) {
        LOG.info("/* savePermohonan */");
        Transaction tx = s.beginTransaction();

        String idPermohonan = null;
        idPermohonan = idPerserahanGenerator.generate(kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        uv.setIdPermohonan(idPermohonan);

        List<CarianHakmilik> senaraiTmp = new ArrayList<CarianHakmilik>();

        PermohonanCarian p = new PermohonanCarian();
        p.setIdCarian(idPermohonan);
        p.setUrusan(kodUrusan);
        p.setCawangan(pengguna.getKodCawangan());
        p.setFolderDokumen(fd);
        p.setResit(dk);

        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);

        for (Map<String, Transaksi> map : mapTrans) {
            Transaksi trans = map.get(String.valueOf(bil));
            if (trans == null) {
                continue;
            }
            String perihal = trans.getPerihal();

            if (perihal.equals("Pelepasan")) {
                p.setTransPelepasan(trans);
            } else if (perihal.equals("Pengecualian")) {
                p.setTransPengecualian(trans);
            } else if (perihal.equals("Notis")) {
                p.setTransNotis(trans);
            } else if (perihal.equals("Bayaran Fail")) {
                p.setTransFail(trans);
            } else if (perihal.equals("Bayaran-bayaran Lain")) {
                p.setTransLain(trans);
            } else {
                p.setTrans(trans);
            }
        }
        // penyerah
        if (idPenyerah != null && idPenyerah.length() > 0
                && !"0".equals(idPenyerah)) {
            p.setIdPenyerah(Integer.parseInt(idPenyerah));
        }
//        if (penyerahKod != null && penyerahKod.getKod() != null
//                && !"0".equals(penyerahKod.getKod())) {
//            p.setKodPenyerah(penyerahKod);
//        }
//        p.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
//        p.setPenyerahNoPengenalan(penyerahNoPengenalan);
        p.setPenyerahNama(penyerahNama);
        p.setPenyerahAlamat1(penyerahAlamat1);
        p.setPenyerahAlamat2(penyerahAlamat2);
        p.setPenyerahAlamat3(penyerahAlamat3);
        p.setPenyerahAlamat4(penyerahAlamat4);
        p.setPenyerahPoskod(penyerahPoskod);

        if (penyerahNegeri != null) {
            p.setPenyerahNegeri(penyerahNegeri);
        } //        p.setPenyerahEmail(penyerahEmail);
        //        p.setPenyerahNoTelefon1(penyerahNoTelefon);
        //        p.setUntukTahun(d.getYear() + 1900);
        permohonanCarianDAO.save(p);

        // attach hakmilik
        if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
            LOG.info("-- HAKMILIK SINGLE --");
            for (CarianHakmilik hp : hakmilikPermohonan) {
                if (hp == null
                        || (StringUtils.isBlank(hp.getIdHakmilik()) && StringUtils.isBlank(hp.getIdPerserahan()))) {
                    continue;
                }

                CarianHakmilik ch = new CarianHakmilik();
                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    String id = hp.getIdHakmilik();
                    Hakmilik hm = hakmilikDAO.findById(id);
                    if (hm == null) {
                        throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                    }
                    ch.setIdHakmilik(id);
                    ch.setNoBangunan(hp.getNoBangunan());
                    ch.setNoTingkat(hp.getNoTingkat());
                    ch.setNoPetak(hp.getNoPetak());
                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
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
                        //maximum size = 28. error if put all hakmilik
                        ch.setIdHakmilik(pmhn.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    } else {
                        ch.setIdHakmilik(id);
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
            LOG.info("-- HAKMILIK BERSIRI --");
            for (int i = 0; i < idHakmilikSiriDari.size(); i++) {
                String idH1 = idHakmilikSiriDari.get(i);
                String idH2 = idHakmilikSiriKe.get(i);

                if (idH1 == null || idH1.trim().length() == 0
                        || idH2 == null || idH2.trim().length() == 0) {
                    continue;
                }

                if (idH1.trim().length() > 20 || idH2.trim().length() > 20) {
                    // add by azri 3/9/2013 : to save hakmilik strata in table carian_hakmilik
                    LOG.info("// HAKMILIK STRATA BERSIRI");
                    ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);
                    // find list in table hakmilik
                    List<Hakmilik> listId = sessionProvider.get().createQuery(
                            "SELECT h FROM Hakmilik h WHERE h.idHakmilik in (:listHakmilik) ").setParameterList("listHakmilik", list).list();
                    LOG.info("--> Bil Hakmilik Strata : " + listId.size());

                    for (Hakmilik hm : listId) {
                        LOG.info("** Save Hakmilik " + hm.getIdHakmilik() + " in table Carian_Hakmilik **");
                        /* CREATE TABLE CARIAN_HAKMILIK */
                        CarianHakmilik ch = new CarianHakmilik();
                        ch.setIdHakmilik(hm.getIdHakmilikInduk()); // save hakmilik induk for strata
                        ch.setNoBangunan(hm.getNoBangunan());
                        ch.setNoTingkat(hm.getNoTingkat());
                        ch.setNoPetak(hm.getNoPetak());
                        ch.setInfoAudit(ia);
                        ch.setPermohonanCarian(p);
                        ch.setCawangan(pengguna.getKodCawangan());
                        ch.setKodUrusan(kodUrusan);
                        carianHakmilikDAO.save(ch);
                        senaraiTmp.add(ch);
                    }
                } else {

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
                        ch.setKodUrusan(kodUrusan);
                        carianHakmilikDAO.save(ch);
                        senaraiTmp.add(ch);
                    }
                }
            }
        }

        p.setSenaraiHakmilik(senaraiTmp);
        tx.commit();
        return p;
    }

    private List<KandunganFolder> generateSijil(KodUrusan ku, InfoAudit ia,
            List<CarianHakmilik> hakmilikPermohonan, KodDokumen kodResit,
            FolderDokumen fd, String documentPath, PermohonanCarian pc, DokumenKewangan dk) {
        LOG.debug("generateSijil kodUrusan =" + ku.getKod());
        Dokumen sijilCarian = null;
        StringBuilder sijil = new StringBuilder();
        KandunganFolder kf1 = null;
        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();
        String rn = "";
//        ku.getKod().equals("CRHM") || ku.getKod().equals("CRHMB")
//                            || ku.getKod().equals("CRHMR") || ku.getKod().equals("CRHMT")

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
                sijilCarian.setTajuk(kodResit.getKod() + "(" + hp.getIdHakmilik() + ")");
                dokumenDAO.save(sijilCarian);
                kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                kf1.setDokumen(sijilCarian);
                kf1.setInfoAudit(ia);
//                                fd.getSenaraiKandungan().add(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(sijilCarian.getIdDokumen());

                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    rn = reportName.getCarianRasmiByIDHakmilikReportName();
//                    reportUtil.generateReport(rn,
//                            new String[]{"p_id_hakmilik", "p_carian", "p_resit"},
//                            new String[]{hp.getIdHakmilik(), pc.getIdCarian(), dk.getIdDokumenKewangan()},
//                            path, ia.getDimasukOleh());
                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                    rn = reportName.getCarianRasmiByIDMohonReportName();
//                    reportUtil.generateReport(rn,
//                            new String[]{"p_id_hakmilik", "p_carian", "p_resit"},
//                            new String[]{hp.getIdHakmilik(), pc.getIdCarian(), dk.getIdDokumenKewangan()},
//                            path, ia.getDimasukOleh());
                }
                LOG.info("----hp.getIdHakmilik()-----::" + hp.getIdHakmilik());
                reportUtil.generateReport(rn,
                        new String[]{"p_id_hakmilik", "p_carian", "p_resit", "p_trans"},
                        new String[]{hp.getIdHakmilik(), pc.getIdCarian(), dk.getIdDokumenKewangan(),
                            String.valueOf(pc.getTrans().getIdTransaksi())},
                        path, ia.getDimasukOleh());

                if (sijil.length() > 0) {
                    sijil.append(",");

                }
                sijil.append(sijilCarian.getIdDokumen());

                sijilCarian.setNamaFizikal(reportUtil.getDMSPath());
                sijilCarian.setFormat(reportUtil.getContentType());
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
                sijilCarian.setTajuk(kodResit.getKod() + "(" + hp.getIdHakmilik() + ")");
                dokumenDAO.save(sijilCarian);
                kf1 = new KandunganFolder();
                kf1.setFolder(fd);
                kf1.setDokumen(sijilCarian);
                kf1.setInfoAudit(ia);
//                                fd.getSenaraiKandungan().add(kf1);
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                        + String.valueOf(sijilCarian.getIdDokumen());

                String kodUrusan = ku.getKod();
                if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
                    LOG.info("----hp.getIdHakmilik()2-----::" + hp.getIdHakmilik());
                    if (kodUrusan.equals("CSHM")) {
                        rn = reportName.getCarianPersendirianByIDHakmilikReportName();
                    } else if (kodUrusan.equals("CSHMT")) {
                        rn = reportName.getCarianPersendirianHMTakKuatKuasaByIDHakmilikReportName();
                    }
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_hakmilik", "p_carian", "p_trans"},
                            new String[]{hp.getIdHakmilik(), pc.getIdCarian(), String.valueOf(pc.getTrans().getIdTransaksi())},
                            path, ia.getDimasukOleh());

                } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
                    rn = reportName.getCarianPersendirianByIDMohonReportName();
                    reportUtil.generateReport(rn,
                            new String[]{"p_id_mohon", "p_carian", "p_trans"},
                            new String[]{hp.getIdPerserahan(), pc.getIdCarian(), String.valueOf(pc.getTrans().getIdTransaksi())},
                            path, ia.getDimasukOleh());

                }
                if (sijil.length() > 0) {
                    sijil.append(",");

                }
                sijil.append(sijilCarian.getIdDokumen());
                sijilCarian.setNamaFizikal(reportUtil.getDMSPath());
                sijilCarian.setFormat(reportUtil.getContentType());
                dokumenDAO.update(sijilCarian);
                senaraiKF.add(kf1);

            } //            if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
            //                for (CarianHakmilik hp : hakmilikPermohonan) {
            //                    if (hp == null) {
            //                        continue;
            //                    }
            //                    sijilCarian = new Dokumen();
            //                    sijilCarian.setFormat("application/pdf");
            //                    sijilCarian.setInfoAudit(ia);
            //                    sijilCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            //                    kodResit = kodDokumenDAO.findById("SCR"); //fixme
            //                    sijilCarian.setKodDokumen(kodResit);
            //                    sijilCarian.setNoVersi("1.0");
            //                    sijilCarian.setTajuk(kodResit.getNama());
            //                    dokumenDAO.save(sijilCarian);
            //                    kf1 = new KandunganFolder();
            //                    kf1.setFolder(fd);
            //                    kf1.setDokumen(sijilCarian);
            //                    kf1.setInfoAudit(ia);
            ////                                fd.getSenaraiKandungan().add(kf1);
            //                    String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
            //                            + String.valueOf(sijilCarian.getIdDokumen());
            //
            //
            //                    if (StringUtils.isNotBlank(hp.getIdHakmilik())) {
            //                        rn = reportName.getCarianPersendirianByIDHakmilikReportName();
            //                        reportUtil.generateReport(rn,
            //                                new String[]{"p_id_hakmilik"},
            //                                new String[]{hp.getIdHakmilik()},
            //                                path, ia.getDimasukOleh());
            //
            //                    } else if (StringUtils.isNotBlank(hp.getIdPerserahan())) {
            //                        rn = reportName.getCarianPersendirianByIDMohonReportName();
            //                        reportUtil.generateReport(rn,
            //                                new String[]{"p_id_mohon"},
            //                                new String[]{hp.getIdPerserahan()},
            //                                path, ia.getDimasukOleh());
            //                    }
            //                    if (sijil.length() > 0) {
            //                        sijil.append(",");
            //                    }
            //                    sijil.append(sijilCarian.getIdDokumen());
            //                    sijilCarian.setNamaFizikal(reportUtil.getDMSPath());
            //                    dokumenDAO.update(sijilCarian);
            //                    senaraiKF.add(kf1);
            //                }
            //            }
        }

        return senaraiKF;

    }

    private List<KandunganFolder> generateReport(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, PermohonanCarian pc, DokumenKewangan dk) {
        if (debug) {
            LOG.debug("generateReport");
        }
        String parameterToPass = "";
        String valueToPass = "";
        Dokumen dokumenCarian = null;
        KandunganFolder kf1 = null;
        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

        List<CarianHakmilik> list = pc.getSenaraiHakmilik();
        LOG.info("---------- senarai hakmilik : " + pc.getSenaraiHakmilik());

        for (CarianHakmilik ch : list) {
            if (ch == null) {
                continue;
            }
            LOG.info("--ch :: id hakmilik : " + ch.getIdHakmilik());
            if (StringUtils.isNotBlank(ch.getIdPerserahan())) {
                parameterToPass = "p_id_mohon";
                valueToPass = ch.getIdPerserahan();
            } else if (StringUtils.isNotBlank(ch.getIdHakmilik())) {
                parameterToPass = "p_id_hakmilik";
                valueToPass = ch.getIdHakmilik();
//                Hakmilik hm = hakmilikDAO.findById(ch.getIdHakmilik());
//                String idHM = "";
//                if (hm.getIdHakmilikInduk() != null) {
//                    idHM = hm.getIdHakmilikInduk();
//                } else {
//                    idHM = ch.getIdHakmilik();
//                }
                if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                    if (ArrayUtils.contains(CARIAN_STRATA, kodUrusan.getKod())) {
                        if (kodUrusan.getKod().equals("CRIDS") // hakmilik strata using hakmilik induk
                                || kodUrusan.getKod().equals("CRPDS")
                                || kodUrusan.getKod().equals("CSIDS")
                                || kodUrusan.getKod().equals("CSPDS")) {
                            /* FIX ME : need to search-by-id hakmilik and not hakmilik induk because no
                             * input for banggunan, petak and tingkat. system will error cos' 1 hakmilik
                             * induk have more then one idhakmilik strata */
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilik = :idhakmilik").setParameter("idhakmilik", ch.getIdHakmilik()).uniqueResult();
                        } else if (StringUtils.isBlank(ch.getNoBangunan())) {
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilikInduk = :hakmilikInduk "
                                    + "and h.noPetak = :noPetak").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noPetak", ch.getNoPetak()).uniqueResult();
                        } else {
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilikInduk = :hakmilikInduk "
                                    + "and h.noBangunan = :noBangunan and h.noTingkat LIKE :noTingkat and h.noPetak = :noPetak").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noBangunan", ch.getNoBangunan()).setParameter("noTingkat", "%" + ch.getNoTingkat() + "%").setParameter("noPetak", ch.getNoPetak()).uniqueResult();
//                                "select h.idHakmilik from Hakmilik h where h.idHakmilik = :idHakmilik "
//                                + "and h.noBangunan = :noBangunan and h.noTingkat = :noTingkat and h.noPetak = :noPetak")
//                                .setParameter("idHakmilik", ch.getIdHakmilik())
//                                .setParameter("noBangunan", ch.getNoBangunan())
//                                .setParameter("noTingkat", ch.getNoTingkat())
//                                .setParameter("noPetak", ch.getNoPetak())
//                                .uniqueResult();
                        }
                        if (kodUrusan.getKod().equals("CSR")) {
                            valueToPass = ch.getIdHakmilik();
                        }
                    }
                } else if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                    if (ArrayUtils.contains(CARIAN_STRATA, kodUrusan.getKod())) {
                        if (kodUrusan.getKod().equals("CRIDS") // hakmilik strata using hakmilik induk
                                || kodUrusan.getKod().equals("CRPDS")
                                || kodUrusan.getKod().equals("CSIDS")
                                || kodUrusan.getKod().equals("CSPDS")) {
                            /* FIX ME : need to search-by-id hakmilik and not hakmilik induk because no
                             * input for banggunan, petak and tingkat. system will error cos' 1 hakmilik
                             * induk have more then one idhakmilik strata */
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilik = :idhakmilik").setParameter("idhakmilik", ch.getIdHakmilik()).uniqueResult();
                        } else if (StringUtils.isNotBlank(ch.getNoBangunan()) && StringUtils.isBlank(ch.getNoPetak())) {
                            LOG.info("jana carian jenis katg bngn P & PL");
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilikInduk = :hakmilikInduk "
                                    + "and h.noBangunan = :noBangunan and h.kodKategoriBangunan.kod in ('P','PL') "
                                    + "and h.kodStatusHakmilik.kod = 'D'").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noBangunan", ch.getNoBangunan()).uniqueResult();
                        } else if (StringUtils.isBlank(ch.getNoBangunan())) {
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilikInduk = :hakmilikInduk "
                                    + "and h.noPetak = :noPetak").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noPetak", ch.getNoPetak()).uniqueResult();
                        } else {
                            valueToPass = (String) sessionProvider.get().createQuery(
                                    "select h.idHakmilik from Hakmilik h where h.idHakmilikInduk = :hakmilikInduk "
//                                   + "and h.noBangunan = :noBangunan and h.noTingkat like :noTingkat and h.noPetak = :noPetak").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noBangunan", ch.getNoBangunan()).setParameter("noTingkat", "%" + ch.getNoTingkat() + "%").setParameter("noPetak", ch.getNoPetak()).uniqueResult(); // yus edit 18052018 LIKE kat no tingkat
                                    + "and h.noBangunan = :noBangunan and h.noTingkat like :noTingkat and h.noPetak = :noPetak and h.kodStatusHakmilik.kod = 'D'").setParameter("hakmilikInduk", ch.getIdHakmilik()).setParameter("noBangunan", ch.getNoBangunan()).setParameter("noTingkat", ch.getNoTingkat() + "%").setParameter("noPetak", ch.getNoPetak()).uniqueResult(); // yus edit 31/05/2018                                  
                        } 
                        if (kodUrusan.getKod().equals("CSR")) {
                            valueToPass = ch.getIdHakmilik();
                        }
                    }
                }
            }

            dokumenCarian = new Dokumen();
            dokumenCarian.setFormat("application/pdf");
            dokumenCarian.setInfoAudit(ia);
            if (kodDokumen.getKod().equals("SDH")) {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(3)); //sulit
            } else if (kodUrusan.getKod().equals("CAB")
                    || kodUrusan.getKod().equals("CRHMR") //                    || kodUrusan.getKod().equals("CRHMT")
                    //                    || kodUrusan.getKod().equals("CRHMB")
                    ) {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(2)); //terhad
            } else {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1)); //Am
            }
            dokumenCarian.setKodDokumen(kodDokumen);
            dokumenCarian.setNoVersi("1.0");
            dokumenCarian.setTajuk(kodDokumen.getNama() + "-" + valueToPass);
            dokumenDAO.save(dokumenCarian);
            kf1 = new KandunganFolder();
            kf1.setFolder(pc.getFolderDokumen());
            kf1.setDokumen(dokumenCarian);
            kf1.setInfoAudit(ia);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(dokumenCarian.getIdDokumen());
            LOG.info("---- valueToPass ---- : " + valueToPass);
            reportUtil.generateReport(reportName,
                    new String[]{parameterToPass, "p_carian", "p_resit", "p_trans"},
                    new String[]{valueToPass, pc.getIdCarian(), dk.getIdDokumenKewangan(), String.valueOf(pc.getTrans().getIdTransaksi())},
                    path, ia.getDimasukOleh());

            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
            dokumenCarian.setFormat(reportUtil.getContentType());
            dokumenDAO.update(dokumenCarian);
            senaraiKF.add(kf1);
        }
        return senaraiKF;
    }

    // add by azri 19/9/2013: only used to generate Carian CSHMS and CRHMS for Borang 2 and Borang 3
    private List<KandunganFolder> generateReportV2(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, PermohonanCarian pc, DokumenKewangan dk) {
        if (debug) {
            LOG.debug("/* generateReportV2 */");
        }
        String parameterToPass = "";
        String valueToPass = "";
        Dokumen dokumenCarian = null;
        KandunganFolder kf1 = null;
        List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();
        List<CarianHakmilik> list = pc.getSenaraiHakmilik();

        for (CarianHakmilik ch : list) {
            if (ch == null) {
                continue;
            }

            if (StringUtils.isNotBlank(ch.getIdPerserahan())) {
                parameterToPass = "p_id_mohon";
                valueToPass = ch.getIdPerserahan();
            } else if (StringUtils.isNotBlank(ch.getIdHakmilik())) {
                parameterToPass = "p_id_hakmilik";
                valueToPass = ch.getIdHakmilik();

                if (ArrayUtils.contains(CARIAN_STRATA, kodUrusan.getKod())) {
                    /* FIX ME : need to search-by-id hakmilik and not hakmilik induk because no
                     * input for banggunan, petak and tingkat. system will error cos' 1 hakmilik
                     * induk have more then one idhakmilik strata */
                    valueToPass = (String) sessionProvider.get().createQuery(
                            "select h.idHakmilik from Hakmilik h where h.idHakmilik = :idhakmilik").setParameter("idhakmilik", ch.getIdHakmilik()).uniqueResult();
                }
            }

            /* UPDATE TABLE_DOKUMEN */
            dokumenCarian = new Dokumen();
            dokumenCarian.setFormat("application/pdf");
            dokumenCarian.setInfoAudit(ia);
            if (kodDokumen.getKod().equals("SDH")) {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(3)); //sulit
            } else if (kodUrusan.getKod().equals("CAB")
                    || kodUrusan.getKod().equals("CRHMR") //                    || kodUrusan.getKod().equals("CRHMT")
                    //                    || kodUrusan.getKod().equals("CRHMB")
                    ) {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(2)); //terhad
            } else {
                dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1)); //Am
            }
            dokumenCarian.setKodDokumen(kodDokumen);
            dokumenCarian.setNoVersi("1.0");
            dokumenCarian.setTajuk(kodDokumen.getNama() + "-" + valueToPass);
            dokumenDAO.save(dokumenCarian);
            kf1 = new KandunganFolder();
            kf1.setFolder(pc.getFolderDokumen());
            kf1.setDokumen(dokumenCarian);
            kf1.setInfoAudit(ia);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                    + String.valueOf(dokumenCarian.getIdDokumen());

            LOG.info("---- valueToPass ---- : " + valueToPass);
            reportUtil.generateReport(reportName,
                    new String[]{parameterToPass, "p_carian", "p_resit", "p_trans"},
                    new String[]{valueToPass, pc.getIdCarian(), dk.getIdDokumenKewangan(), String.valueOf(pc.getTrans().getIdTransaksi())},
                    path, ia.getDimasukOleh());

            dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
            dokumenCarian.setFormat(reportUtil.getContentType());
            dokumenDAO.update(dokumenCarian);
            senaraiKF.add(kf1);
        }
        return senaraiKF;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String toFill = getContext().getRequest().getParameter("fill");

        if (toFill != null) {
            int i = Integer.parseInt(toFill);
            selectedItem = i;
            loadUrusan(
                    i);

        }
    }

    private List<UrusanCache> getSessionCache(etanahActionBeanContext ctx) {
        Object obj = ctx.getWorkData();

        if (obj instanceof List) {
            return (List<UrusanCache>) obj;

        } else {
            return null;

        }
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }
}
