package etanah.view.daftar.kaunter;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.CaraBayaran;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.UrusanDokumen;
import etanah.model.Akaun;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.JUBL;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodRujukan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.Peguam;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.model.SejarahHakmilik;
import etanah.model.strata.BadanPengurusan;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.service.AkaunService;
import etanah.service.KaunterService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PihakService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.DokumenValue;
import etanah.view.kaunter.TransaksiValue;
import etanah.view.kaunter.UrusanValue;
import etanah.workflow.WorkFlowService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import able.stripes.AbleActionBean;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.model.HakmilikWaris;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihak;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.view.kaunter.UrusanPermohonan;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.fraction.Fraction;

@HttpCache(allow = false)
@Wizard(startEvents = {"Step1", "Step6b"})
@UrlBinding("/daftar/kaunter")
public class PermohonanKaunter extends AbleActionBean {

    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    private char tambahUrusan = 'T';
    // DISPLAY OBJECTS
    // Urusan Value Object (used in Step1 & Step6a)
    private ArrayList<UrusanValue> senaraiUrusan = new ArrayList<UrusanValue>();
    // Dokumen submitted by check list (used in Step 2)
    private ArrayList<DokumenValue> senaraiDokumenSerahan = new ArrayList<DokumenValue>();
    // Additional Dokumen submitted (not in check list) (used in Step 2)
    private ArrayList<DokumenValue> senaraiDokumenTamb = new ArrayList<DokumenValue>();
    // no of Hakmilik in for the Urusan (used in Step3)
    private int bilHakmilik = 5;
    // list of Hakmilik in the Urusan (used in Step3)
    private ArrayList<HakmilikPermohonan> hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    // list of Hakmilik bersiri (from) (used in Step3)
    private ArrayList<String> idHakmilikSiriDari = new ArrayList<String>();
    // list of Hakmilik bersiri (from) (used in Step3)
    private ArrayList<String> idHakmilikSiriKe = new ArrayList<String>();
    // list of summary (used in Step5a)
    List<UrusanCache> txnGroups;
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
    //information on Hakmilik (Step3b)
    private Hakmilik hakmilik;
    private int totalHakmilik = 1;
    private String namaBPM;
    private String namaNegeri;
    private String kodDaerah;
    private String kodNegeri;
    private String kodBPM;
    private String namaUrusan;
    private String kodJenisHakmilik;
    private String kodJenisDaerah;
    private String kodTarafPegangan;
    private KodRujukan kodRujukan;
    private String kodRUJ;
    private String HakmilikLama;
    private Long idUrusanGDHmBaru;
    private HakmilikUrusan HakmilikUrusanBaruGD;
    private HakmilikUrusan HakmilikUrusanBaruPMT;
    Fraction sum = new Fraction(0, 1);
//    private String kodCawangan;
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPetakAksesori> listHakmilikPetakAksesori = new ArrayList<HakmilikPetakAksesori>();
    private ArrayList<HakmilikPihakBerkepentingan> lhp = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<KodDaerah> senaraiKodDaerah = new ArrayList();
    private List<KodHakmilik> senaraiKodHakmilik = new ArrayList();
    private List<KodHakmilik> senaraiKodHakmilikPTD = new ArrayList();
    // list of payment methods for the Urusan (used in Step6)
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    // value object of amount of charges (used in Step6)
    private BigDecimal jumlahCaj;
    // senarai permohonan for display in Step7. Being initialized in save() method (used in Step7)
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    private ArrayList<Permohonan> senaraiPermohonan2 = new ArrayList<Permohonan>();
    private List<HakmilikWaris> hwList;
    private List<HakmilikSebelum> hakmilikSblm;
    private List<HakmilikUrusan> hakmilikUrusan;
    private List<HakmilikPermohonan> mohonHakmilik;
    private List<Pemohon> pemohon;
    private List<PermohonanPihak> mohonPihak;
    private String urusan;
    private UrusanPermohonan urusanPermohonan;
    private String kodSerah;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    PembetulanService pService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
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
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    private GeneratorNoResit noResitGenerator;
    @Inject
    private KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    private SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO;
    @Inject
    private PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    private PemohonService pemohonService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private JUBLDAO jUBLDAO;
    @Inject
    private KodJabatanDAO kodJabatanDAO;
    @Inject
    private KodCawanganJabatanDAO kodCawanganJabatanDAO;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    private KodAgensiDAO kodAgensiDAO;
    @Inject
    private PihakService pihakService;
    @Inject
    private PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    private RegService regService;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    @Inject
    private KodDaerahDAO kodDaerahDAO;
    @Inject
    private HakmilikService hakmilikService;
    @Inject
    private StrataPtService strataService;
    @Inject
    private KodHakmilikDAO kodHakmilikDAO;
    private String resitNo;
    private String idFail;
    private String strIdMohon;
    private StringBuilder catatan = new StringBuilder();

    // class to cache urusan (before registering a new one)
    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiUrusan;
        ArrayList<DokumenValue> senaraiDokumenSerahan;
        ArrayList<DokumenValue> senaraiDokumenTamb;
        ArrayList<HakmilikPermohonan> hakmilikPermohonan;
        ArrayList<String> idHakmilikSiriDari;
        ArrayList<String> idHakmilikSiriKe;
    }
    private static final Logger LOG = Logger.getLogger(PermohonanKaunter.class);
    private static final boolean debug = LOG.isDebugEnabled();

    public String getKodTarafPegangan() {
        return kodTarafPegangan;
    }

    public void setKodTarafPegangan(String kodTarafPegangan) {
        this.kodTarafPegangan = kodTarafPegangan;
    }

    public String getKodJenisHakmilik() {
        return kodJenisHakmilik;
    }

    public void setKodJenisHakmilik(String kodJenisHakmilik) {
        this.kodJenisHakmilik = kodJenisHakmilik;
    }

    public String getKodJenisDaerah() {
        return kodJenisDaerah;
    }

    public void setKodJenisDaerah(String kodJenisDaerah) {
        this.kodJenisDaerah = kodJenisDaerah;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    public Fraction getSum() {
        return sum;
    }

    public void setSum(Fraction sum) {
        this.sum = sum;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public int getTotalHakmilik() {
        return totalHakmilik;
    }

    public void setTotalHakmilik(int totalHakmilik) {
        this.totalHakmilik = totalHakmilik;
    }

    public List<KodHakmilik> getSenaraiKodHakmilik() {
        return senaraiKodHakmilik;
    }

    public void setSenaraiKodHakmilik(List<KodHakmilik> senaraiKodHakmilik) {
        this.senaraiKodHakmilik = senaraiKodHakmilik;
    }

    public List<KodHakmilik> getSenaraiKodHakmilikPTD() {
        return senaraiKodHakmilikPTD;
    }

    public void setSenaraiKodHakmilikPTD(List<KodHakmilik> senaraiKodHakmilikPTD) {
        this.senaraiKodHakmilikPTD = senaraiKodHakmilikPTD;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

//    public String getKodCawangan() {
//        return kodCawangan;
//    }
//
//    public void setKodCawangan(String kodCawangan) {
//        this.kodCawangan = kodCawangan;
//    }
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public ArrayList<Hakmilik> getListHakmilikBaru() {
        return listHakmilikBaru;
    }

    public void setListHakmilikBaru(ArrayList<Hakmilik> listHakmilikBaru) {
        this.listHakmilikBaru = listHakmilikBaru;
    }

    public ArrayList<HakmilikPermohonan> getListMohonHakmilikBaru() {
        return listMohonHakmilikBaru;
    }

    public void setListMohonHakmilikBaru(ArrayList<HakmilikPermohonan> listMohonHakmilikBaru) {
        this.listMohonHakmilikBaru = listMohonHakmilikBaru;
    }

    public ArrayList<HakmilikPihakBerkepentingan> getLhp() {
        return lhp;
    }

    public void setLhp(ArrayList<HakmilikPihakBerkepentingan> lhp) {
        this.lhp = lhp;
    }

    public String getNamaBPM() {
        return namaBPM;
    }

    public void setNamaBPM(String namaBPM) {
        this.namaBPM = namaBPM;
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

    public char getTambahUrusan() {
        return tambahUrusan;
    }

    public void setTambahUrusan(char tambahUrusan) {
        this.tambahUrusan = tambahUrusan;
    }

    public ArrayList<DokumenValue> getSenaraiDokumenSerahan() {
        return senaraiDokumenSerahan;
    }

    public void setSenaraiDokumenSerahan(ArrayList<DokumenValue> senaraiDokumenSerahan) {
        this.senaraiDokumenSerahan = senaraiDokumenSerahan;
    }

    public void setSenaraiDokumenTamb(ArrayList<DokumenValue> senaraiDokumenTamb) {
        this.senaraiDokumenTamb = senaraiDokumenTamb;
    }

    public ArrayList<DokumenValue> getSenaraiDokumenTamb() {
        return senaraiDokumenTamb;
    }

    public ArrayList<UrusanValue> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<UrusanValue> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    /**
     * Get the checklist Dokumen for the given list Urusan.
     *
     * @return
     */
    public List<UrusanDokumen> getSenaraiDokumen() {
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        for (UrusanValue u : senaraiUrusan) {
            senaraiKodUrusan.add(u.getKodUrusan());
        }

        return kaunterService.getUrusanDokumen(senaraiKodUrusan);
    }

    /**
     * Get the KodDokumen which are not required for transactions
     *
     * @return
     */
    public List<KodDokumen> getKodDokumenNotRequired() {
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        for (UrusanValue u : senaraiUrusan) {
            senaraiKodUrusan.add(u.getKodUrusan());
        }

        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
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

    public List<KodUrusan> getPilihanKodUrusan() {
        return kaunterService.findAllUrusanByJabatan();
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

    public ArrayList<HakmilikPermohonan> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(ArrayList<HakmilikPermohonan> hakmilikPermohonan) {
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

    public String getPenyerahNegeriNama() {
        return kodNegeriDAO.findById(penyerahNegeri.getKod()).getNama();
    }

    public String getResitNo() {
        return resitNo;
    }

    public String getIdFail() {
        return idFail;
    }

    public void setIdFail(String idFail) {
        this.idFail = idFail;
    }

    public String getStrIdMohon() {
        return strIdMohon;
    }

    public void setStrIdMohon(String strIdMohon) {
        this.strIdMohon = strIdMohon;
    }

    public ArrayList<HakmilikPetakAksesori> getListHakmilikPetakAksesori() {
        return listHakmilikPetakAksesori;
    }

    public void setListHakmilikPetakAksesori(ArrayList<HakmilikPetakAksesori> listHakmilikPetakAksesori) {
        this.listHakmilikPetakAksesori = listHakmilikPetakAksesori;
    }

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {
        resetAll();

        if ("Y".equals(tambahUrusan)) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/tambah_urusan.jsp");
        }

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
    }

    @HandlesEvent("Step2")
    @DontValidate
    public Resolution selectDocuments() {
        if (debug) {
            LOG.debug("senaraiUrusan.size()=" + senaraiUrusan.size());
            for (UrusanValue uv : senaraiUrusan) {
                LOG.debug(uv.getKodUrusan());
            }
        }
        if (senaraiUrusan.isEmpty()) {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            txnGroups = getAllUrusanFromSession(ctx);
            if (!txnGroups.isEmpty()) {
                senaraiUrusan = txnGroups.get(0).senaraiUrusan;
                senaraiDokumenTamb = txnGroups.get(0).senaraiDokumenTamb;
                senaraiDokumenSerahan = txnGroups.get(0).senaraiDokumenSerahan;
            }
        }
        // validate if any urusan selected
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        if (!pengguna.getKodCawangan().getKod().equals("00")) {
            if (u.getKodUrusan().equals("BMSTM")) {
                resetAll();
                addSimpleError("Urusan ini hanya untuk Pejabat Pendaftar sahaja.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
            }
        }

        //urusan = ku.getKodPerserahan().getKod();
        urusan = ku.getKod().toUpperCase();
        namaUrusan = ku.getNama();

        if (u == null || u.getKodUrusan() == null || "0".equals(u.getKodUrusan())) {
            addSimpleError("Sila pilih Kod Urusan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        }
        for (UrusanValue uv : senaraiUrusan) {
            if (uv.getKodUrusan() != null) {
                uv.setKodUrusan(uv.getKodUrusan().toUpperCase());
            }
        }

        // reset the additional documents submitted to 10
        if (senaraiDokumenTamb.size() == 0) {
            for (int i = 0; i < 10; i++) {
                DokumenValue d = new DokumenValue();
                senaraiDokumenTamb.add(d);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/senarai_semakan.jsp");
    }

    @HandlesEvent("Step3")
    @DontValidate
    public Resolution selectTitles() {
        // validate all the mandatory documents submitted
        List<UrusanDokumen> lud = getSenaraiDokumen();
        UrusanValue u = senaraiUrusan.get(0);
        if (lud.size() > 0) {
            boolean n6a = false;
            boolean wng6a = false;
            for (UrusanDokumen ud : lud) {
                if (ud.getWajib() == 'Y') {
                    boolean fd = false;
                    for (DokumenValue d : senaraiDokumenSerahan) {
                        if (d == null) {
                            continue;
                        }

                        if (d != null && d.getKodDokumen() != null && ud.getKodDokumen().getKod().equals(d.getKodDokumen())) {
                            fd = true;
                            break;
                        }

                    }
                    if (!fd) {
                        addSimpleError("Dokumen yang wajib (" + ud.getKodDokumen().getNama() + ") untuk urusan tidak diserahkan.");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/senarai_semakan.jsp");
                    }
                }
            }

//            if (u.getKodUrusan().toUpperCase().equals("N6A")) {
//
//                for (DokumenValue d : senaraiDokumenSerahan) {
//                    if (d == null) {
//                        continue;
//                    }
//                    LOG.debug("kod dokumen = " + d.getKodDokumen());
//
//                    if (d.getKodDokumen().equals("N6A")) {
//                        n6a = true;
//                    }
//                    if (d.getKodDokumen().equals("WNG6A")) {
//                        wng6a = true;
//                    }
//                }
//
//
//                LOG.debug("n6a =" + n6a + ", wng6a =" + wng6a);
////                if ((n6a && wng6a) || (!n6a && !wng6a)) {
////                    addSimpleError("Sila pilih salah satu daripada N6A atau WNG6A.");
////                    return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/senarai_semakan.jsp");
////                  } disable for comfirmation
//            }
        }

        if (u.getKodUrusan().toUpperCase().equals("HSBM")
                || u.getKodUrusan().toUpperCase().equals("BMSTM")) {
            return getSpecificInfoForUrusan();
        }
        //For NB - Pembetulan 380
        for (UrusanValue uv : senaraiUrusan) {
            if (uv == null || StringUtils.isBlank(uv.getKodUrusan())) {
                continue;
            }
            if (uv.getKodUrusan().toUpperCase().equals("N8AB")
                    || uv.getKodUrusan().toUpperCase().equals("BETST")
                    || uv.getKodUrusan().toUpperCase().equals("BETLP")
                    || uv.getKodUrusan().toUpperCase().equals("HKSTB")
                    || uv.getKodUrusan().toUpperCase().equals("HSSTB")
                    || uv.getKodUrusan().toUpperCase().equals("PRPMB")) {
                getContext().getRequest().setAttribute("batal", "true");
            }
        }

        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        //urusan = ku.getKodPerserahan().getKod();
        urusan = ku.getKod().toUpperCase();
        kodNegeri = conf.getProperty("kodNegeri");
        //logger.debug("::rehydrate kodNegeri :" + kodNegeri);
        KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
        namaNegeri = kn.getNama();
        kodSerah = ku.getKodPerserahan().getKod();
        namaUrusan = ku.getNama();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.debug("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiAllKodHakmilikPTG(urusan);
            kodDaerah = "01";
            List<KodDaerah> list = kodDaerahDAO.findAll();
            for (KodDaerah kd : list) {
                if (kd == null || kd.getKod() == null || kd.getNama() == null) {
                    continue;
                }
                senaraiKodDaerah.add(kd);
            }
            LOG.debug("size senaraiKodDaerah:" + senaraiKodDaerah.size());
        } else {
            LOG.debug("::get jeniskodhakmilik PTD :::");
            if (kodNegeri.equals("05")) {
                senaraiKodHakmilik = regService.listAllKodHakmilikPTDns(urusan); // add by azri 30/7/2013 : for N9, there's hakmilik HMM and GMM 
            } else {
                senaraiKodHakmilik = regService.senaraiAllKodHakmilikPTD(urusan); // for melaka include hakmilik HMM and GMM
            }
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
            senaraiKodDaerah = regService.cariDaerah(kodDaerah);
            LOG.debug("size senaraiKodDaerah:" + senaraiKodDaerah.size());
        }

        // reset senaraiHakmilik
        for (int i = 0; i < bilHakmilik; i++) {
            HakmilikPermohonan h = new HakmilikPermohonan();
            hakmilikPermohonan.add(h);
        }

//        LOG.debug("selectTitles = saving ctx");
//        saveUrusanToSession(ctx);
//        resetUrusan();
        //return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/pilih_hakmilik.jsp");
        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
    }

    @HandlesEvent("Step3a")
    public Resolution selectTitle3() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        txnGroups = getAllUrusanFromSession(ctx);
        if (!txnGroups.isEmpty()) {
            senaraiUrusan = txnGroups.get(0).senaraiUrusan;
            senaraiDokumenSerahan = txnGroups.get(0).senaraiDokumenSerahan;
            hakmilikPermohonan = txnGroups.get(0).hakmilikPermohonan;
        }

        return selectTitles();
    }

    @HandlesEvent("Step3b")
    @DontValidate
    public Resolution selectTitles2() {
        LOG.info("/* STEP 3b */");
        String[] EXCLUDE = {"HTSPV", "HTSPB", "HTSC", "HTSPS"};
        // validate all the mandatory documents submitted
        List<UrusanDokumen> lud = getSenaraiDokumen();
        if (lud.size() > 0) {
            for (UrusanDokumen ud : lud) {
                if (ud.getWajib() == 'Y') {
                    boolean fd = false;
                    for (DokumenValue d : senaraiDokumenSerahan) {
                        if (d != null && d.getKodDokumen() != null && ud.getKodDokumen().getKod().equals(d.getKodDokumen())) {
                            fd = true;
                            break;
                        }
                    }
                    if (!fd) {
                        addSimpleError("Dokumen yang wajib (" + ud.getKodDokumen().getNama() + ") untuk urusan tidak diserahkan.");
                        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/senarai_semakan.jsp");
                    }

                }
            }
        }
        if (debug) {
            LOG.debug("senaraiUrusan.size()=" + senaraiUrusan.size());
            for (UrusanValue uv : senaraiUrusan) {
                LOG.debug(uv.getKodUrusan());
            }
        }
        // validate if any urusan selected
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        //urusan = ku.getKodPerserahan().getKod();
        urusan = ku.getKod().toUpperCase();
        kodSerah = ku.getKodPerserahan().getKod();
        namaUrusan = ku.getNama();
        kodNegeri = conf.getProperty("kodNegeri");
        //logger.debug("::rehydrate kodNegeri :" + kodNegeri);
        KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
        namaNegeri = kn.getNama();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        LOG.debug("hakmilik" + hakmilikPermohonan.size());
        for (HakmilikPermohonan hp : hakmilikPermohonan) {
            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            kodTarafPegangan = String.valueOf(hm.getPegangan());
            LOG.debug("kodTarafPegangan" + kodTarafPegangan);
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.debug("::get jeniskodhakmilik PTG:::");
            if (urusan.equals("HTIR")) {
                if (kodTarafPegangan.equals("S")) {
                    List<KodHakmilik> list = kodHakmilikDAO.findAll();
                    for (KodHakmilik kh : list) {
                        if (kh.getKod().equals("GRN")) {
                            senaraiKodHakmilik.add(kh);
                        } else if (kh.getKod().equals("GM")) {
                            senaraiKodHakmilikPTD.add(kh);
                        }
                    }
                } else if (kodTarafPegangan.equals("P")) {
                    List<KodHakmilik> list = kodHakmilikDAO.findAll();
                    for (KodHakmilik kh : list) {
                        if (kh.getKod().equals("PN")) {
                            senaraiKodHakmilik.add(kh);
                        } else if (kh.getKod().equals("PM")) {
                            senaraiKodHakmilikPTD.add(kh);
                        }
                    }
                }
            } else {
                senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(urusan);
            }
            kodDaerah = "01";
            List<KodDaerah> list = kodDaerahDAO.findAll();
            for (KodDaerah kd : list) {
                if (kd == null || kd.getKod() == null || kd.getNama() == null) {
                    continue;
                }
                senaraiKodDaerah.add(kd);
            }
        } else {
            LOG.debug("::get jeniskodhakmilik PTD :::");
            if (kodNegeri.equals("05")) {
                senaraiKodHakmilik = regService.listKodHakmilikPTDns(urusan); // add by azri 30/7/2013 : for N9, there's hakmilik HMM and GMM 
            } else {
                senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(urusan); // for melaka include hakmilik HMM and GMM
            }
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
            senaraiKodDaerah = regService.cariDaerah(kodDaerah);
        }
        LOG.debug("size senaraiKodDaerah:" + senaraiKodDaerah.size());

//    if (urusan.equals("BMSTM")) {
//        String[] kodHakmilik = {"GRS","PNS"};
//        senaraiKodHakmilik = regService.listKodHakmilikByKod(kodHakmilik);
//    }
        // reset senaraiHakmilik
        for (int i = 0; i < bilHakmilik; i++) {
            HakmilikPermohonan h = new HakmilikPermohonan();
            hakmilikPermohonan.add(h);
        }
//        if (urusan.equals("HSPB") || urusan.equals("HKPB")
//                || urusan.equals("HSPS") || urusan.equals("HKPS")
//                || urusan.equals("HKPTK") || urusan.equals("HSBTK")
//                || urusan.equals("HKBTK") || urusan.equals("HKPS")
//                || urusan.equals("HSPTK")) {
//            totalHakmilik = 2;
//        }
        if (urusan.equals("HSPB") || urusan.equals("HKPB")
                || urusan.equals("HSPS") || urusan.equals("HKPS") || urusan.equals("HTSPS")
                || urusan.equals("HKPTK") || urusan.equals("HSBTK")
                || urusan.equals("HKBTK") || urusan.equals("HKPS")
                || urusan.equals("HSPTK")) {
            if (hakmilikPermohonan.size() > 0) {
                HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                Hakmilik hakmilikBaru = new Hakmilik();
                Hakmilik hakmilikTerpilih = hp.getHakmilik();
                String id = hakmilikTerpilih.getIdHakmilik();
                hakmilikTerpilih = hakmilikDAO.findById(id);
                //totalHakmilik = hakmilikTerpilih.getSenaraiPihakBerkepentingan().size();
                totalHakmilik = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikTerpilih).size();

            }
        }

        if (urusan.equals("HKTHK") || urusan.equals("HSTHK")) {
            boolean needInfo = false;
            if (needInfo) {
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/urusan_info_tamb.jsp");
            } else if (checkValidation()) {
                return setPenyerah();
            } else {
                addSimpleError("Tiada Permohonan untuk hakmilik " + hakmilik.getIdHakmilik() + " untuk dibuat Kelulusan atau Pembatalan.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
            }

        } else {
            LOG.debug("hakmilikPermohonan.size() : " + hakmilikPermohonan.size());
            if (hakmilikPermohonan.get(0).getHakmilik() != null
                    || (!idHakmilikSiriDari.isEmpty() && !idHakmilikSiriKe.isEmpty())) {
                if (hakmilikPermohonan.size() > 0) {
                    StringBuilder message = new StringBuilder();
                    boolean gotError = false;
                    List<String> listidHakmilik = new ArrayList<String>();
                    for (HakmilikPermohonan hp : hakmilikPermohonan) {
                        if (debug) {
                            LOG.debug("start to create hakmilik asal permohonan");
                        }
                        if (hp == null) {
                            continue;
                        }
                        Hakmilik hm = hp.getHakmilik();

                        if (hm == null) {
                            continue;
                        }
                        String id = hm.getIdHakmilik();
                        hm = hakmilikDAO.findById(id);
                        listidHakmilik.add(id);
                    }
                    /*check notting selain hakmilik*/
                    for (String hmk : listidHakmilik) {
                        List<String> errorM = new ArrayList(checkValidationNottingMH(hmk.toString()));
                        gotError = false;
                        if (!ArrayUtils.contains(EXCLUDE, urusan)) {
                            if (errorM.size() > 0) {
                                /*throw validation error*/
                                for (int i = 0; i < errorM.size(); i++) {
                                    message.append(errorM.get(i));
                                }
                                //addSimpleError(message.toString());
                                gotError = true;
                            } else {
                                gotError = false;
                            }
                        }
                    }
                    if (gotError) {
                        LOG.debug(message.toString());
                        addSimpleError(message.toString());
                        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
                    } else if (urusan.equals("HKTP")) { // add by azri 29/8/2013 : for urusan HKTP skip step "Penyerahan Hakmilik"
                        return setPenyerah();
                    } else {
                        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik.jsp");
                    }

                } else {

                    StringBuilder message = new StringBuilder();
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    List<String> errorM = new ArrayList(checkValidationNottingMH(id));
                    if (!ArrayUtils.contains(EXCLUDE, urusan)) {
                        if (errorM.size() > 0) {

                            LOG.debug("TOTAL MESSAGES:" + errorM.size());
                            /*throw validation error*/
                            for (int i = 0; i < errorM.size(); i++) {
                                message.append(errorM.get(i));
                            }
                            addSimpleError(message.toString());
                            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
                        } else {
                            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik.jsp");
                        }
                    } else {
                        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik.jsp");
                    }

                }
            } else if (urusan.equals("HSBM") || urusan.equals("HKBM") || urusan.equals("BMSTM")) {
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik.jsp");
            } else {
                addSimpleError("Sila Masukkan Hakmilik");
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
            }

//            }
        }
    }

    /**
     * To get the specific information for certain Urusan. The extra values will
     * be updated in UrusanValue properties (nilai, tarikh1, amaun1).
     *
     * @return the page that handle the info gathering.
     */
    @HandlesEvent("Step4")
    @DontValidate
    public Resolution getSpecificInfoForUrusan() {
        LOG.info("  /* ENTeR STEP 4 */  ");
        // no other specific info needed, skip this step
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        urusan = ku.getKod();
        StringBuilder validateMessage = new StringBuilder();
        boolean needInfo = false;
        if (needInfo) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/urusan_info_tamb.jsp");
        } else {

//            HakmilikPermohonan hp = hakmilikPermohonan.get(0);
//            Hakmilik hakmilikTerpilih = hp.getHakmilik();
//            String id = hakmilikTerpilih.getIdHakmilik();
            LOG.debug("checkValidation : " + checkValidation());
            LOG.debug("checkCukai empty? : " + checkCukai().isEmpty());
            if (urusan.equals("HSBM") || urusan.equals("HKBM") || urusan.equals("BETSW") || urusan.equals("BMSTM")) {
                return setPenyerah();
            }
            if (checkValidation() && checkCukai().isEmpty()
                    && (hakmilikPermohonan.size() > 0 || checkHakmilikSiri())) {
                return setPenyerah();
            } else if (!checkValidation() || !checkCukai().isEmpty()
                    || (hakmilikPermohonan.size() < 1 && (idHakmilikSiriDari.isEmpty() && idHakmilikSiriKe.isEmpty()))) {
                LOG.debug(":masuk validation error:");
                if (!checkValidation()) {
                    validateMessage.append("Tiada Permohonan untuk hakmilik " + hakmilik.getIdHakmilik() + " untuk dibuat Kelulusan atau Pembatalan.");
                }
                if (!checkCukai().isEmpty()) {
                    validateMessage.append(checkCukai().toString());
                }
                if (hakmilikPermohonan.size() < 1 && (idHakmilikSiriDari.isEmpty() && idHakmilikSiriKe.isEmpty())) {
                    validateMessage.append("Sila Masukkan ID Hakmilik");
                }
                addSimpleError(validateMessage.toString());
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
            } else {
                addSimpleError("Sila Masukkan ID Hakmilik");
                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerahan_hakmilik_mh.jsp");
            }
//                    addSimpleError("Cukai masih belum dijelaskan untuk hakmilik " + hakmilik.getIdHakmilik());
        }

    }

    private boolean checkHakmilikSiri() {
        boolean flg = false;
        for (String idHakmilik : idHakmilikSiriDari) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                flg = true;
                break;
            }
        }
        if (flg) {
            return flg;
        }
        for (String idHakmilik : idHakmilikSiriKe) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                flg = true;
                break;
            }
        }

        return flg;
    }

    private String checkCukai() {
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        String kodSerah = ku.getKodPerserahan().getKod();
        String kodUrusan = ku.getKod();
        StringBuilder message = new StringBuilder();
        boolean flaghmnull = false;
        for (HakmilikPermohonan hp : hakmilikPermohonan) {
            if (hp == null) {
//                flaghmnull = true;
                LOG.debug("HP IS NULL!");
                continue;
            }
            Hakmilik hm = hp.getHakmilik();

            if (hm == null) {
                LOG.debug("HAKMILIK IS NULL!");
//                flaghmnull = true;
                continue;
            }

            Hakmilik hmk = hakmilikDAO.findById(hm.getIdHakmilik());
            //exclude kod serah 'N' dari check cukai
            //exclude kod serah 'MH'dari check cukai
            if (!(kodSerah.equals("NB") || kodSerah.equals("N") || kodSerah.equals("MH"))) {
                LOG.debug("CHECK HAKMILIK BATAL");
                String st = hmk.getKodStatusHakmilik().getKod();

                if (!(ku.getKod().equalsIgnoreCase("N8AB") || ku.getKod().equalsIgnoreCase("PRPMB") || (ku.getKod().equalsIgnoreCase("RPBNB")) //Batal
                        )) {
                    LOG.debug("CHECK Batal");
                    if ("B".equals(st)) {
                        message.append("Hakmilik " + hmk.getIdHakmilik() + " tidak wujud.");
                    }

                }

                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                KodCawangan caw = pengguna.getKodCawangan();
                KodDaerah daerah = caw.getDaerah();
                String kodDaerah = null;
                if (daerah != null) {
                    kodDaerah = daerah.getKod();
                }
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    kodDaerah = null;
                }

                Hakmilik h = hakmilikService.findHakmilikInDaerah(hmk.getIdHakmilik(), kodDaerah);

                if (h == null) {
                    message.append("Hakmilik " + hmk.getIdHakmilik() + " tidak wujud");
                }

                if (!ku.getKod().equals("HMSC") && "P".equals(st)) {
                    message.append("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
                }
                LOG.debug("CHECK CUKAI");

                Akaun ac = hmk.getAkaunCukai();
                if (!(ku.getKod().equalsIgnoreCase("N6A")
                        || ku.getKod().equalsIgnoreCase("N6AB")
                        || ku.getKod().equalsIgnoreCase("N8A")
                        || ku.getKod().equalsIgnoreCase("N8AB")
                        || ku.getKod().equalsIgnoreCase("PRPMB")
                        || ku.getKod().equalsIgnoreCase("RPBNB")
                        || ku.getKod().equalsIgnoreCase("HSSAA")
                        || ku.getKod().equalsIgnoreCase("HTIR")
                        || ku.getKod().startsWith("KVP"))) { //CUKAI // add by azri 24/7/2013: HSSAA don't have to check cukai
                    LOG.debug("CHECK LEPAS");

                    if (!(ku.getKodPerserahan().getKod().equalsIgnoreCase("B") && (ku.getPerluJelasCukai() == 'T'))) {
                        LOG.debug("SINI");
                        if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && hmk.getIdHakmilikInduk() == null) {
                            message.append("Cukai masih belum dijelaskan untuk hakmilik " + hmk.getIdHakmilik());
                        }
                    }
                }

            }
            //Start alert for Urusan SHKK, SHSK, SHKB, SHSB.
            if (urusan.equals("SHKK") || urusan.equals("SHKB")) {
                if (hmk.getKodHakmilik().getKod().equals("HSD") || hmk.getKodHakmilik().getKod().equals("HSM") || hmk.getKodHakmilik().getKod().equals("HMM")) {
                    message.append("Id Hakmilik Sementara " + hmk.getIdHakmilik() + " ini tidak boleh digunakan untuk urusan ini. ");
                }
            }
            if (urusan.equals("SHSK") || urusan.equals("SHSB")) {
                if (hmk.getKodHakmilik().getKod().equals("GM") || hmk.getKodHakmilik().getKod().equals("GMM") || hmk.getKodHakmilik().getKod().equals("PN") || hmk.getKodHakmilik().getKod().equals("PM")) {
                    message.append("Id Hakmilik Kekal " + hmk.getIdHakmilik() + " ini tidak boleh digunakan untuk urusan ini. ");
                }
            }//End alert for Urusan SHKK, SHSK, SHKB, SHSB.

        }

//        if(flaghmnull){
//            message.append("Hakmilik tidak wujud!");
//        }
        return message.toString();
    }

    private List checkValidationNottingMH(String idHakmilik) {
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        String kodSerah = ku.getKodPerserahan().getKod();
        urusan = ku.getKod();
        List<String> errorMessages = new ArrayList<String>();
        LOG.debug("checkNottingMH:kodUrusan: " + urusan + " idHakmilik : " + idHakmilik);
        List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
        List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
        List<HakmilikAsalPermohonan> lhap = regService.findIdHakmilikAsalMohonByIdHakmilikSej(idHakmilik);
        List<HakmilikUrusan> lhu = hakmilikUrusanService.findUrusanNottingMH(idHakmilik, urusan);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            kodDaerah = null;
        }

        Hakmilik hmk = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah);

        if (hmk == null) {
            LOG.debug("VALIDATE VALID HAKMILIK");
            errorMessages.add("Hakmilik " + idHakmilik + "tidak wujud");
        }

        String st = hmk.getKodStatusHakmilik().getKod();

        LOG.debug("CHECK Batal");
        if (!urusan.equals("HSSTB")) {
            if ("B".equals(st)) {
                errorMessages.add("Hakmilik " + hmk.getIdHakmilik() + " tidak wujud.");
            }
        }

        Akaun ac = h.getAkaunCukai();

        if (!urusan.equals("HSSAA") && !urusan.equals("HTIR") && !urusan.equals("HMSC") && !urusan.equals("HKGHS") && !urusan.equals("ECPI") && !urusan.equals("HKSTK") && !urusan.equals("HKABS") && !kodSerah.equals("MH")) { // add by azri 24/7/2013: skip cukai for HSSAA
            if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && h.getIdHakmilikInduk() == null) {
                LOG.debug("VALIDATE CUKAI : " + h.getIdHakmilik());
                errorMessages.add("Cukai masih belum dijelaskan untuk hakmilik " + h.getIdHakmilik());
            }
        }

        if (lhp.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                if (!hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PCT") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("HSSTB") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRUS") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPCB")
                        && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBM") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTD") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTG") && !hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
                    if (!hakmilikPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikPermohonan.getPermohonan().getStatus().equals("TK") && !hakmilikPermohonan.getPermohonan().getStatus().equals("XY") && !hakmilikPermohonan.getPermohonan().getStatus().equals("ZY")) {
                        errorMessages.add("Hakmilik " + idHakmilik + " masih mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai.");
                    }
                }
            }
        }
        if (lhsp.size() > 0) {
            LOG.debug("CHECK MOHON HAKMILIK SEBELUM");
            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
                if (!hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PCT") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("HSSTB") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRUS") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPCB")
                        && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBM") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTD") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTG") && !hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
                    if (!hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("TK") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("XY") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("ZY")) {
                        //message.append("Hakmilik mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + ": idPerserahan : " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + "\n");
                        errorMessages.add("Hakmilik " + idHakmilik + " masih mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai.");
                    }
                }
            }
        }
        if (lhap.size() > 0) {
            LOG.debug("CHECK MOHON HAKMILIK ASAL");
            for (HakmilikAsalPermohonan hakmilikAsalPermohonan : lhap) {
                if (!hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PCT") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("HSSTB") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PPRUS") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PPCB")
                        && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PBM") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PBPTD") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PBPTG") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
                    if (!hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getStatus().equals("SL") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getStatus().equals("TK") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getStatus().equals("XY") && !hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getStatus().equals("ZY")) {
                        
                        errorMessages.add("Hakmilik " + idHakmilik + " masih mempunyai urusan " + hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getKodUrusan().getKod() + " " + hakmilikAsalPermohonan.getHakmilikPermohonan().getPermohonan().getIdPermohonan() + " yang belum selesai.");
                    }
                }
            }
        }      
        /* HSPS,HKPS,HKPB,HSPB,HSC,HKC,HKABS,HKABT,HSSBB,HSSTB,HSSB,HKSB */
//        if (urusan.equals("HSPS") || urusan.equals("HTSPS") || urusan.equals("HKPS") || urusan.equals("HKPB") || urusan.equals("HSPB")
//                || urusan.equals("HSC") || urusan.equals("HKC") || urusan.equals("HKABS") || urusan.equals("HKABT")
//                || urusan.equals("HSSBB") || urusan.equals("HSSTB") || urusan.equals("HSSB") || urusan.equals("HKSB")) {
//            if (lhu.size() < 1) {
//                errorMessages.add("Hakmilik " + idHakmilik + " tidak mempunyai notting berkaitan untuk meneruskan urusan.");
//            }
//        }

        //Aizuddin for new nota module alert
        if (lhp.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("ADBSB")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("ADBSS")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("TT")
                        || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("TTW")) {
                    LOG.info("==============ADA URUSAN AKU ENTER DRAGON!!!!!===================");
                    if (hakmilikPermohonan.getPermohonan().getStatus().equalsIgnoreCase("SL")) {
                        if (!hakmilikPermohonan.getPermohonan().getKeputusan().getKod().isEmpty()) {
                            if (hakmilikPermohonan.getPermohonan().getKeputusan().getKod().equalsIgnoreCase("D")) {
                                errorMessages.add("Hakmilik ini mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang didaftarkan");
                            }
                        }
                    }
                }
            }
        }

        LOG.debug(errorMessages.size());

        return errorMessages;
    }

    private boolean checkValidation() {
        UrusanValue u = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        urusan = ku.getKod();
        kodSerah = ku.getKodPerserahan().getKod();
        if (StringUtils.isNotBlank(kodSerah) && kodSerah.equals("N")) {
            boolean flag = true;
            boolean flagToValidate = false;
            String urusanToValidate = "";
            List<String> senaraiUrusan = new ArrayList<String>();

            //todo properties
            //todo properties
            if (urusan.equals("CL")) {
//                urusanToValidate = "CM";
                senaraiUrusan.add("CM");
                flagToValidate = true;
            }
            if (urusan.equals("CB")) {
//                urusanToValidate = "CM";
                senaraiUrusan.add("CM");
                senaraiUrusan.add("CL");
                flagToValidate = true;
            }
            if (urusan.equals("TSB")) {
//                urusanToValidate = "TSM";
                senaraiUrusan.add("TSM");
                senaraiUrusan.add("TSL");
                flagToValidate = true;
            }
            if (urusan.equals("TSL")) {
//                urusanToValidate = "TSM";
                senaraiUrusan.add("TSM");
                flagToValidate = true;
            }
            if (urusan.equals("TSSKL")) {
//                urusanToValidate = "TSSKM";
                senaraiUrusan.add("TSSKM");
                flagToValidate = true;
            }
            if (urusan.equals("TSSKB")) {
//                urusanToValidate = "TSSKM";
                senaraiUrusan.add("TSSKM");
                senaraiUrusan.add("TSSKL");
                flagToValidate = true;
            }
            if (urusan.equals("PSL")) {
//                urusanToValidate = "PSM";
                senaraiUrusan.add("PSM");
                flagToValidate = true;
            }
            if (urusan.equals("PSB")) {
                senaraiUrusan.add("PSL");
                senaraiUrusan.add("PSM");
//                urusanToValidate = "PSM";

                flagToValidate = true;
            }
            if (urusan.equals("SBTL")) {
                senaraiUrusan.add("SBTM");
//                urusanToValidate = "SBTM";
                flagToValidate = true;
            }
            if (urusan.equals("SBTB")) {
//                urusanToValidate = "SBTM";
                senaraiUrusan.add("SBTM");
                senaraiUrusan.add("SBTL");
                flagToValidate = true;
            }
            if (urusan.equals("ABTB")) {
//                urusanToValidate = "ABT-D";
                senaraiUrusan.add("ABT-D");
                senaraiUrusan.add("ABT");
                senaraiUrusan.add("ABT-K");
                senaraiUrusan.add("ABTKB");
                senaraiUrusan.add("ABTS");
                senaraiUrusan.add("ABTBH");
                flagToValidate = true;
            }//('ABT','ABT-K','ABT-D','ABTKB','ABTS', 'ABTBH')";
            if (urusan.equals("ABTKB")) {
//                urusanToValidate = "ABT-D";
                senaraiUrusan.add("ABT-D");
                flagToValidate = true;
            }
            if (urusan.equals("HLLB")) {
//                urusanToValidate = "HLLA";
                senaraiUrusan.add("HLLA");//HLLS
                senaraiUrusan.add("HLLS");
                flagToValidate = true;
            }
            if (urusan.equals("HLTEB")) {
//                urusanToValidate = "HLTE";
                senaraiUrusan.add("HLTE");
                flagToValidate = true;
            }
            if (urusan.equals("HMVB")) {
//                urusanToValidate = "HMV";
                senaraiUrusan.add("HMV");
                flagToValidate = true;
            }
            if (urusan.equals("PBBL")) {
//                urusanToValidate = "PBBM";
                senaraiUrusan.add("PBBM");
                flagToValidate = true;
            }
            if (urusan.equals("PBBB")) {
//                urusanToValidate = "PBBM";
                senaraiUrusan.add("PBBM");
                senaraiUrusan.add("PBBL");
                flagToValidate = true;
            }
            if (urusan.equals("PBL")) {
//                urusanToValidate = "PBM";
                senaraiUrusan.add("PBM");
                flagToValidate = true;
            }
            if (urusan.equals("PBB")) {
//                urusanToValidate = "PBM";
                senaraiUrusan.add("PBM");
                senaraiUrusan.add("PBL");
                flagToValidate = true;
            }
            if (urusan.equals("PSTSL")) {
//                urusanToValidate = "PSTSM";
                senaraiUrusan.add("PSTSM");
                flagToValidate = true;
            }
            if (urusan.equals("PSTSB")) {
                senaraiUrusan.add("PSTSM");
                senaraiUrusan.add("PSTSL");
                flagToValidate = true;
            }
            if (urusan.equals("SBKSL")) {
//                urusanToValidate = "SBKSM";
                senaraiUrusan.add("SBKSM");
                flagToValidate = true;
            }
//            if (urusan.equals("SBKBG")) {
//                senaraiUrusan.add("SBKSM");
//                flagToValidate = true;
//            }
            if (urusan.equals("SBKSB")) {
//                urusanToValidate = "SBKSM";
                senaraiUrusan.add("SBKSM");
                senaraiUrusan.add("SBKSL");
                flagToValidate = true;
            }
            if (urusan.equals("PBSCB")) {
//                urusanToValidate = "PBSCM";
                senaraiUrusan.add("PBSCM");
                senaraiUrusan.add("PBSCL");
                flagToValidate = true;
            }
            if (urusan.equals("PBSCL")) {
//                urusanToValidate = "PBSCM";
                senaraiUrusan.add("PBSCM");
                flagToValidate = true;
            }
            if (urusan.equals("PSKB")) {
//                urusanToValidate = "PSKM";
                senaraiUrusan.add("PSKM");
                senaraiUrusan.add("PSKL");
                flagToValidate = true;
            }
            if (urusan.equals("PSKL")) {
//                urusanToValidate = "PSKM";
                senaraiUrusan.add("PSKM");
                flagToValidate = true;
            }
            if (urusan.equals("PSKSB")) {
//                urusanToValidate = "PSKSM";
                senaraiUrusan.add("PSKSM");
                senaraiUrusan.add("PSKSL");
                flagToValidate = true;
            }
            if (urusan.equals("PSKSL")) {
//                urusanToValidate = "PSKSM";
                senaraiUrusan.add("PSKSM");
                flagToValidate = true;
            }
            if (urusan.equals("SBSTB")) {
//                urusanToValidate = "SBSTM";
                senaraiUrusan.add("SBSTM");
                senaraiUrusan.add("SBSTL");
                flagToValidate = true;
            }
            if (urusan.equals("SBSTL")) {
//                urusanToValidate = "SBSTM";
                senaraiUrusan.add("SBSTM");
                flagToValidate = true;
            }
            if (urusan.equals("MCLL")) {
//                urusanToValidate = "MCLM";
                senaraiUrusan.add("MCLM");
                flagToValidate = true;
            }
            if (urusan.equals("ABT-K")) {
//                urusanToValidate = "ABT-D";
                senaraiUrusan.add("ABT-D");
                flagToValidate = true;
            }
            if (urusan.equals("N6AB")) {
//                urusanToValidate = "N6A";
                senaraiUrusan.add("N6A");
                flagToValidate = true;
            }
            if (urusan.equals("N8AB")) {
//                urusanToValidate = "N8A";
                senaraiUrusan.add("N8A");
                flagToValidate = true;
            }
            
            if (urusan.equals("ADBSL")) {
                senaraiUrusan.add("ADBSS");
                senaraiUrusan.add("ADBSB");
                flagToValidate = true;
            }
            
            if (urusan.equals("ADMRL")) {
                senaraiUrusan.add("ADMNS");
                senaraiUrusan.add("ADMNB");
                flagToValidate = true;
            }
            //
            if (flagToValidate) {
                for (HakmilikPermohonan hp : hakmilikPermohonan) {
                    if (hp == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    List<HakmilikUrusan> senarai = hakmilikUrusanService.findByIdHakmilikKodUrusan(hm.getIdHakmilik(), senaraiUrusan);
                    if (senarai.isEmpty()) {
                        hakmilik = hm;
                        flag = false;
                        break;
                    }

//                    Akaun ac = hm.getAkaunCukai();
//                    if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
//                        hakmilik = hm;
//                        flag = false;
//                        break;
//                    }
                }
            }

            return flag;
        } else {
            return true;
        }
    }

    @HandlesEvent("Step5")
    @DontValidate
    public Resolution setPenyerah() {
        LOG.debug("setPenyerah = saving ctx");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        saveUrusanToSession(ctx);
        resetUrusan();

        //UrusanValue u = senaraiUrusan.get(0);
        List<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        UrusanValue u = txnGroups.get(0).senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan().toUpperCase());
        //urusan = ku.getKodPerserahan().getKod();
        urusan = ku.getKod().toUpperCase();
        namaUrusan = ku.getNama();
//        if (urusan.equals("HKCSD") || urusan.equals("HSCSD")) {
//            /*TODO:TEMP*/
//            penyerahNama = "Pendaftaran";
//            penyerahAlamat1 = "Pejabat Tanah";
//            penyerahNegeri = kodNegeriDAO.findById("05");

        // reset all values
        LOG.debug("set penyerah urusan :" + urusan);
        if (urusan.equals("HKTHK") || urusan.equals("HSTHK")) {
            KodCawanganJabatan kcj = regService.getJabatanPendaftaranByCaw(pengguna.getKodCawangan().getKod());
            if (kcj != null) {
                LOG.debug("MASUK SET PENYERAH");
                setPenyerahKod(kodPenyerahDAO.findById(kcj.getKodPenyerah()));
                setIdPenyerah(String.valueOf(kcj.getIdPenyerah()));
                setPenyerahNama(kcj.getNama());
                setPenyerahNoPengenalan(kcj.getNoPengenalan());
                LOG.debug("SETTING PENYERAH :" + kcj.getNama());
                penyerahJenisPengenalan = kcj.getJenisPengenalan();
                penyerahAlamat1 = kcj.getAlamat1();
                penyerahAlamat2 = kcj.getAlamat2();
                penyerahAlamat3 = kcj.getAlamat3();
                penyerahAlamat4 = kcj.getAlamat4();
                penyerahPoskod = kcj.getPoskod();
//        LOG.debug("kcj negeri :" + kcj.getNegeri().getKod());
                if (kcj.getNegeri() != null) {
                    penyerahNegeri = kodNegeriDAO.findById(kcj.getNegeri().getKod());
                }

            } else {
                LOG.debug("KCJ NULL!!!");
            }
            return viewSummary();
        } else {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
        }
    }

    @HandlesEvent("Step5a")
    @DontValidate
    public Resolution viewSummary() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        txnGroups = getAllUrusanFromSession(ctx);

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/rumusan.jsp");
    }

    public Resolution collectPayment() {
        // validate Penyerah
        if (penyerahNama == null || penyerahNama.trim().length() == 0 || penyerahAlamat1 == null || penyerahAlamat1.trim().length() == 0
                || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
            addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        if (txnGroups == null) {
            addSimpleError("Data simpanan sementara telah korup. Anda perlu mula semula.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/tambah_urusan.jsp");
        }

        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/bayaran.jsp");
    }

    @HandlesEvent("Step6a")
    public Resolution addTransaction() {
        LOG.debug("addTransaction = saving ctx");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);

        // reset all values
        resetUrusan();

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/tambah_urusan.jsp");
    }

    @HandlesEvent("Step6b")
    public Resolution suggestTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/tambah_urusan_2.jsp");
    }

    @HandlesEvent("Step6")
    public Resolution save() {
        LOG.info("  /* STEP 6 */  ");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
//
//        if (debug) {
//            LOG.debug("senaraiUrusan.size()=" + senaraiUrusan.size());
//            for (UrusanValue uv : senaraiUrusan) {
//                LOG.debug(uv.getKodUrusan());
//            }
//        }
//        // validate if any urusan selected
//        UrusanValue urus = senaraiUrusan.get(0);
//        KodUrusan kurus = kodUrusanDAO.findById(urus.getKodUrusan().toUpperCase());
//        //urusan = ku.getKodPerserahan().getKod();
//        urusan = kurus.getKod().toUpperCase();
//        kodSerah = kurus.getKodPerserahan().getKod();
//        namaUrusan = kurus.getNama();

        if (penyerahNama == null || penyerahNama.trim().length() == 0 || penyerahAlamat1 == null
                || penyerahAlamat1.trim().length() == 0
                || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
//            addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
//            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
            //temp solution
            KodCawanganJabatan kj = kodCawanganJabatanDAO.findById("16");
            if (kj != null) {
                setPenyerahKod(kodPenyerahDAO.findById(kj.getKodPenyerah()));
                setIdPenyerah(String.valueOf(kj.getIdPenyerah()));
                setPenyerahNama(kj.getNama());
                setPenyerahNoPengenalan(kj.getNoPengenalan());
                penyerahJenisPengenalan = kj.getJenisPengenalan();
                penyerahAlamat1 = kj.getAlamat1();
                penyerahAlamat2 = kj.getAlamat2();
                penyerahAlamat3 = kj.getAlamat3();
                penyerahAlamat4 = kj.getAlamat4();
                penyerahPoskod = kj.getPoskod();
                penyerahNegeri = kj.getNegeri();
            }
        }

        LOG.debug("senarai Urusan " + senaraiUrusan.size());

        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");

//        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
//        if (akTerima == null) {
//        	LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
//            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
//        }
        List<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);

        // initialize senaraiTransaksi & jumlahCaj
        /*COMMENT SAT*/
        List<TransaksiValue> atv = getSenaraiTransaksi();
        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj);
        }
        /*END COMMENT*/

//        Dokumen resit = null;
//        DokumenKewangan dk = null;
        // create DokumenKewangan
        // Resit is always created even if no charged involved
//        dk = new DokumenKewangan();
//        dk.setCawangan(caw);
//        dk.setKodDokumen(kodDokumenDAO.findById("RBY")); // TODO cache
//        dk.setIsuKepada(penyerahNama);
//        dk.setIdDokumenKewangan(noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna));
//        // set idResit to pageContext
//        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
//        dk.setAmaunBayaran(jumlahCaj);
//        dk.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        // if there is payments, save payment methods
//        if (!jumlahCaj.equals(BigDecimal.ZERO)) {
//            BigDecimal paid = BigDecimal.ZERO;
//            for (CaraBayaran cb : senaraiCaraBayaran) {
//                if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0") &&
//                		cb.getAmaun() != null && !cb.getAmaun().equals(BigDecimal.ZERO)) {
//                    paid = paid.add(cb.getAmaun());
//                }
//            }
//            // validate amounts paid
//            if (jumlahCaj.compareTo(paid) != 0) {
//            	if (jumlahCaj.compareTo(paid) > 0)
//            		addSimpleError("Amaun dibayar kurang daripada jumlah Caj dikenakan.");
//            	if (jumlahCaj.compareTo(paid) < 0)
//            		addSimpleError("Amaun dibayar melebihi daripada jumlah Caj dikenakan.");
//
//                return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/bayaran.jsp");
//            }
//
//            // save cara bayaran
//            saveCaraBayaran(caw, dk, ia);
//        }
        // save the resit
//        dokumenKewanganDAO.save(dk);
        // generate dokumen resit
//        resit = new Dokumen();
//        resit.setFormat("application/pdf");
//        resit.setInfoAudit(ia);
//        resit.setKlasifikasi(klasifikasiAm);
//        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
//        resit.setKodDokumen(kodResit);
//        resit.setNoVersi("1.0");
//        resit.setTajuk(kodResit.getNama());
//        dokumenDAO.save(resit);
        int cntPermohonan = 0;
        Map<String, Permohonan> mapPermohonan = new HashMap<String, Permohonan>();
        senaraiPermohonan.clear();
        // iterate the list of transactions one by one
        try {
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
                                d.setKodDokumen(kd);
                                d.setInfoAudit(ia);
                                d.setTajuk(kd.getKod());
                                d.setNoVersi("1.0");
                                d.setKlasifikasi(klasifikasiAm);
                                dokumenDAO.save(d);
                                KandunganFolder k = new KandunganFolder();
                                k.setFolder(fd);
                                k.setDokumen(d);
                                k.setInfoAudit(ia);
                                akf.add(k);
                            }
                        }
                    }
                }
                // additional documents
                if (debug) {
                    LOG.debug("u.senaraiDokumenTamb != null?" + (u.senaraiDokumenTamb != null));
                }
                if (u.senaraiDokumenTamb != null) {
                    for (DokumenValue dv : u.senaraiDokumenTamb) {
                        if (dv == null || dv.getKodDokumen() == null) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        KodDokumen kd = kodDokumenDAO.findById(dv.getKodDokumen());
                        if (kd == null) {
                            continue;
                        }
                        String c = dv.getCatatan();
                        if ((kd != null && !kd.getKod().equals("0")) || (c != null && c.trim().length() > 0)) {
                            if (kd != null && kd.getKod().equals("0")) { // the type not set
                                d.setKodDokumen(kd);
                                d.setTajuk("KIV");
                            } else //                                d.setTajuk(kd.getKod());
                            if (kd != null) {
                                d.setKodDokumen(kd);
                                d.setTajuk(kd.getNama());
                            }
                            d.setInfoAudit(ia);
                            d.setNoVersi("1.0");
                            d.setKlasifikasi(klasifikasiAm);
                            dokumenDAO.save(d);
                            KandunganFolder f = new KandunganFolder();
                            f.setFolder(fd);
                            f.setInfoAudit(ia);
                            f.setDokumen(d);
                            akf.add(f);
                        }
                    }
                }

                // attach resit to folder
//                KandunganFolder kf2 = new KandunganFolder();
//                kf2.setFolder(fd);
//                kf2.setDokumen(resit);
//                kf2.setInfoAudit(ia);
//                akf.add(kf2);
                folderDokumenDAO.save(fd);

                // WARNING: folder.tajuk column is 200 bytes max
                StringBuilder tajukFolder = new StringBuilder();
                int cnt = 0;
//                String idKumpulan = null;
                String[] idKumpulan = new String[]{null}; // made as array so that modifiable inside savePermohonan
                // check berangkai or not
                int cntBerangkai = 0;
                for (UrusanValue ku : u.senaraiUrusan) {
                    if (!isUrusanNull(ku)) {
                        cntBerangkai++;
                    }
                }

                // adding Permohonan
                boolean isBerangkai = cntBerangkai > 1;
                boolean adaPermohonanSebelum = StringUtils.isNotBlank(strIdMohon);
                Permohonan permohonanSebelum = null;
                String[] urusanConvert = {"HSTHK", "HKTHK", "HMSC", "HTIR"};

                for (int a = 0; a < u.senaraiUrusan.size(); a++) {
                    UrusanValue uv = u.senaraiUrusan.get(a);
                    if (uv.getKodUrusan() == null) {
                        continue;
                    }
                    KodUrusan kodUrusan = kodUrusanDAO.findById(uv.getKodUrusan());
                    if (kodUrusan == null || ArrayUtils.contains(urusanConvert, kodUrusan.getKod())) {
                        continue;
                    }
                    //urusan tukar ganti
                    ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);

                    String senaraiHakmilik = getSenaraiHakmilikUrusan(u.hakmilikPermohonan,
                            u.idHakmilikSiriDari, u.idHakmilikSiriKe);
                    if (StringUtils.isNotBlank(senaraiHakmilik)) {

                        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();

                        String[] idHakmiliks = senaraiHakmilik.split(",");
                        for (String idHakmilik : idHakmiliks) {
                            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
                            if (hm != null) {
                                listHakmilik.add(hm);
                            }
                        }
                        if (!listHakmilik.isEmpty()) {
                            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(ctx.getKodNegeri(), pengguna, listHakmilik);
                            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                                for (Permohonan p : senaraiPermohonanTukarGanti) {
                                    senaraiPermohonan2.add(p);
                                    if (catatan.length() > 0) {
                                        catatan.append(",");
                                    }
                                    catatan.append(p.getIdPermohonan());
                                }

                                List<Hakmilik> hms = tukarGanti.getSenaraiHm();
                                if (hms != null && !hms.isEmpty()) {
                                    for (Hakmilik hm : hms) {
                                        HakmilikPermohonan hp = new HakmilikPermohonan();
                                        hp.setHakmilik(hm);

                                    }
                                }
                            }
                        }
                    }
                }
                for (cnt = 0; cnt < u.senaraiUrusan.size(); cnt++) {
                    UrusanValue uv = u.senaraiUrusan.get(cnt);
                    if (isUrusanNull(uv)) {
                        continue;
                    }

                    // find the jabatan for urusan
                    KodUrusan kodUrusan = kodUrusanDAO.findById(uv.getKodUrusan());

                    if (kodUrusan == null) {
                        addSimpleError("Urusan \"" + uv + "\" tidak dijumpai!");
                        throw new RuntimeException("Urusan \"" + uv + "\" tidak dijumpai!");
                    }

                    if (debug) {
                        LOG.debug("adding urusan:" + uv + " for jabatan " + kodUrusan.getJabatanNama());
                    }

                    Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, isBerangkai, adaPermohonanSebelum,
                            idKumpulan, fd,
                            permohonanSebelum, u.hakmilikPermohonan, u.idHakmilikSiriDari, u.idHakmilikSiriKe, ia);
                    mapPermohonan.put(p.getIdPermohonan(), p);
                    permohonanSebelum = p;
                    if (!senaraiPermohonan2.isEmpty()) {
                        for (Permohonan p2 : senaraiPermohonan2) {
                            if (StringUtils.isEmpty(p2.getUlasan())) {
                                p2.setUlasan(p.getIdPermohonan());
                                permohonanDAO.save(p2);
                            }
                        }
                    }

                    if (tajukFolder.length() > 0) {
                        tajukFolder.append(",");
                    }
                    tajukFolder.append(p.getIdPermohonan());

                    // surat akuan penerimaan
                    Dokumen akuanPenerimaan = new Dokumen();
                    akuanPenerimaan.setKodDokumen(kodDokumenDAO.findById("UNKN1"));
                    akuanPenerimaan.setFormat("application/pdf");
                    akuanPenerimaan.setInfoAudit(ia);
                    akuanPenerimaan.setKlasifikasi(klasifikasiAm);
                    akuanPenerimaan.setNoVersi("1.0");
                    akuanPenerimaan.setTajuk("Akuan Penerimaan " + p.getIdPermohonan());
                    dokumenDAO.save(akuanPenerimaan);
                    KandunganFolder kf1 = new KandunganFolder();
                    kf1.setFolder(fd);
                    kf1.setDokumen(akuanPenerimaan);
                    kf1.setInfoAudit(ia);
                    akf.add(kf1);
                    uv.setAkuanPenerimaan(akuanPenerimaan);
//                    fd.getSenaraiKandungan().add(kf1);

                    senaraiPermohonan.add(uv);
                    cntPermohonan++;
                }

                if (akf.size() > 0) {
                    fd.setSenaraiKandungan(akf);
                }

                fd.setTajuk(tajukFolder.toString());
                folderDokumenDAO.save(fd);

                //utk fail ID
                if (StringUtils.isNotBlank(idFail)) {

                    if (u.hakmilikPermohonan.isEmpty()) {
                        PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
                        prl.setInfoAudit(ia);
                        prl.setNoFail(idFail);
                        prl.setCawangan(caw);
                        prl.setPermohonan(permohonanSebelum);
                        KodRujukan kj = new KodRujukan();
                        kj.setKod("FL");
                        prl.setKodRujukan(kj);

                        permohonanRujukanLuarDAO.save(prl);
                    } else {
                        for (HakmilikPermohonan hp : u.hakmilikPermohonan) {
                            if (hp == null || hp.getHakmilik() == null) {
                                continue;
                            }
                            PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
                            prl.setHakmilik(hp.getHakmilik());
                            prl.setInfoAudit(ia);
                            prl.setNoFail(idFail);
                            prl.setCawangan(caw);
                            prl.setPermohonan(permohonanSebelum);
                            KodRujukan kj = new KodRujukan();
                            kj.setKod("FL");
                            prl.setKodRujukan(kj);
                            permohonanRujukanLuarDAO.save(prl);
                        }
                    }

                }
            }

//            if (atv.size() > 0){
//	            for (TransaksiValue tv: atv){
//	            	if (tv.getKodTransaksi() == null){
//	            		if (tv.getAmaun().compareTo(BigDecimal.ZERO) > 0){
//	            			LOG.warn("No KodTransaksi but amount is > 0");
//	            		}
//	            	}
//		            // create txn
//		            KodTransaksi kt = kodTransaksiDAO.findById(tv.getKodTransaksi());
//		            if (kt != null) {
//		                Transaksi t = new Transaksi();
//		                t.setCawangan(caw);
//		                t.setKodTransaksi(kt);
//		                t.setAmaun(tv.getAmaun());
//		                t.setStatus(kodKewanganStatusDAO.findById('A'));
//		                t.setAkaunDebit(akTerima);
//		                t.setInfoAudit(ia);
//		                t.setDokumenKewangan(dk);
//		                for (UrusanValue uv : senaraiPermohonan){
//		                	if (tv.getUtkUrusan() == uv){
//				                t.setPermohonan(mapPermohonan.get(uv.getIdPermohonan()));
//				                break;
//		                	}
//		                }
//		                transaksiDAO.save(t);
//		            }
//	            }
//            }
            // update akaun kutipan harian
//            if (!jumlahCaj.equals(BigDecimal.ZERO)) {
//                s.lock(akTerima, LockMode.UPGRADE);
//                akTerima.setBaki(akTerima.getBaki().add(jumlahCaj));
//            }
            tx.commit();
//            resitNo = dk.getIdDokumenKewangan();

            tx = s.beginTransaction();

            // generate report for resit
//            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
//                    + String.valueOf(resit.getIdDokumen());
//            if (debug) LOG.debug("HSLResitPelbagai" + path);
//            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
//                    new String[]{"p_id_kew_dok"},
//                    new String[]{resitNo},
//                    path);
//            resit.setNamaFizikal(String.valueOf(resit.getIdDokumen()));
//            getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
//            dokumenDAO.update(resit);
            // generate Surat Akuan Penerimaan for each Permohonan
//            for (UrusanValue uv : senaraiPermohonan) {
//                if (uv.getAkuanPenerimaan() == null) {
//                    continue;
//                }
//
//                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
//                        + uv.getAkuanPenerimaan().getIdDokumen();
//                if (debug) {
//                    LOG.debug("HSLResitAkuanPenerimaan=" + path);
//                }
//                reportUtil.generateReport("HSLResitAkuanPenerimaan002.rdf",
//                        new String[]{"p_id_mohon"},
//                        new String[]{uv.getIdPermohonan()},
//                        path);
//                uv.getAkuanPenerimaan().setNamaFizikal(String.valueOf(uv.getAkuanPenerimaan().getIdDokumen()));
////                getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
//                dokumenDAO.update(uv.getAkuanPenerimaan());
//            }
            for (UrusanValue uv : senaraiPermohonan) {
                if (uv.getAkuanPenerimaan() == null) {
                    continue;
                }

                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator) + uv.getAkuanPenerimaan().getIdDokumen();
                if (debug) {
                    LOG.debug("HSLResitAkuanPenerimaan=" + path);
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002_MLK.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.getIdPermohonan()},
                            path, pengguna);
                } else {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.getIdPermohonan()},
                            path, pengguna);
                }
                uv.getAkuanPenerimaan().setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(uv.getAkuanPenerimaan());
            }

            tx.commit();

        } catch (Exception e) {
            LOG.error(e);
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());

            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
        } finally {
        }

        // workflow
        // TODO: put some error handling code (retry etc)
        for (Permohonan p : senaraiPermohonan2) {
            KodUrusan ku = p.getKodUrusan();
            try {
                if (ku.getKePTG() == 'Y') {
                    WorkFlowService.initiateTask(ku.getIdWorkflow(),
                            p.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
                            ku.getNama());
                } else if (ku.getKePTG() == 'T') {
                    WorkFlowService.initiateTask(ku.getIdWorkflow(),
                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            ku.getNama());
                }
            } catch (Exception e) {
                LOG.error(e);
            }
        }

        for (UrusanCache uc : txnGroups) {
            for (int i = 0; i < uc.senaraiUrusan.size(); i++) {
                UrusanValue uv = uc.senaraiUrusan.get(i);
                if (isUrusanNull(uv)) {
                    continue;
                }
                if (debug) {
                    LOG.debug("kod urusan " + uv.getKodUrusan());
                }
                KodUrusan ku = kodUrusanDAO.findById(uv.getKodUrusan());
                try {
                    if (ku.getKePTG() == 'Y') {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                uv.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
                                ku.getNama());
                    } else if (ku.getKePTG() == 'T') {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                uv.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                ku.getNama());
                    }
                } catch (Exception e) {
                    LOG.error(e);
                }
            }

        }

        //utk kegunaan tukar ganti. display urusan kat page cetak_resit.jsp
        for (Permohonan p : senaraiPermohonan2) {
            if (p != null) {
                UrusanValue uv = etanahContextListener.newInstance(UrusanValue.class);
                uv.setIdPermohonan(p.getIdPermohonan());
                uv.setKodUrusan(p.getKodUrusan().getKod());
                uv.setAkuanPenerimaan(null);
                senaraiPermohonan.add(uv);
            }
        }

        // clear session
        ctx.removeWorkdata();

        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/cetak_resit.jsp");
    }

    public Resolution updatePenyerah() {
        String kod = getContext().getRequest().getParameter("kod");

        Transaction tx = null;

        if (penyerahNama == null || penyerahNama.trim().length() == 0
                || penyerahAlamat1 == null || penyerahAlamat1.trim().length() == 0
                || penyerahPoskod == null || penyerahPoskod.trim().length() == 0
                || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
            addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
        }

        try {

            Session s = sessionProvider.get();
            tx = s.beginTransaction();

            if (kod.equals("1")) {
                if ("01".equals(penyerahKod.getKod())) { // PEGUAM
                    LOG.debug("save Peguam");
                    Peguam pguam = peguamDAO.findById(Long.parseLong(idPenyerah));
                    if (pguam != null) {
                        pguam.setNama(penyerahNama);
                        pguam.setAlamat1(penyerahAlamat1);
                        pguam.setAlamat2(penyerahAlamat2);
                        pguam.setAlamat3(penyerahAlamat3);
                        pguam.setAlamat4(penyerahAlamat4);
                        pguam.setPoskod(penyerahPoskod);
                        pguam.setNegeri(penyerahNegeri);
                        peguamDAO.update(pguam);
                    }
                } else if ("02".equals(penyerahKod.getKod())) { // JUBL
                    LOG.debug("save JUBL");
                    JUBL jubl = jUBLDAO.findById(Long.parseLong(idPenyerah));
                    if (jubl != null) {
                        jubl.setNama(penyerahNama);
                        jubl.setAlamat1(penyerahAlamat1);
                        jubl.setAlamat2(penyerahAlamat2);
                        jubl.setAlamat3(penyerahAlamat3);
                        jubl.setAlamat4(penyerahAlamat4);
                        jubl.setPoskod(penyerahPoskod);
                        jubl.setNegeri(penyerahNegeri);
                        jUBLDAO.update(jubl);
                    }
                } else if ("00".equals(penyerahKod.getKod())) {
                    LOG.debug("save Unit Dalaman");
//                    LOG.info(penyerahKod.getKod());
//                    LOG.info(idPenyerah);
//                    LOG.info(penyerahNoPengenalan);
                    idPenyerah = penyerahNoPengenalan;
//                    LOG.info(idPenyerah);
                    KodCawanganJabatan kj = kodCawanganJabatanDAO.findById(idPenyerah);
                    if (kj != null) {
                        kj.setNama(penyerahNama);
                        kj.setAlamat1(penyerahAlamat1);
                        kj.setAlamat2(penyerahAlamat2);
                        kj.setAlamat3(penyerahAlamat3);
                        kj.setAlamat4(penyerahAlamat4);
                        kj.setPoskod(penyerahPoskod);
                        kj.setNegeri(penyerahNegeri);
//                         kj.setNoTelefon1(penyerahNoTelefon);
                        kodCawanganJabatanDAO.update(kj);
                    }
                } else if ("05".equals(penyerahKod.getKod())) {
                    LOG.debug("save Agensi Kerajaan");
                    KodAgensi ka = kodAgensiDAO.findById(idPenyerah);
                    if (ka != null) {
                        ka.setNama(penyerahNama);
                        ka.setAlamat1(penyerahAlamat1);
                        ka.setAlamat2(penyerahAlamat2);
                        ka.setAlamat3(penyerahAlamat3);
                        ka.setAlamat4(penyerahAlamat4);
                        ka.setPoskod(penyerahPoskod);
                        ka.setNegeri(penyerahNegeri);
//                         ka.setNoTelefon1(penyerahNoTelefon);
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
                    pihakService.saveOrUpdate(p);
                }
            }
            tx.commit();
            addSimpleMessage("Kemaskini data berjaya.");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Kemaskini data tidak berjaya.");
        }

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
    }

    @HandlesEvent("Cancel")
    public Resolution cancel() {
        resetUrusan();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> senaraiUrusan = getAllUrusanFromSession(ctx);

        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/penyerah.jsp");
        }
    }

    @HandlesEvent("CancelAll")
    public Resolution cancelAll() {
        resetAll();

        return new ForwardResolution("/WEB-INF/jsp/daftar/kaunter/main.jsp");
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
        long lTo = 0l;
        try {
            lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");
        }

        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        // validate the series along the way
        if (idHakmilikDari.length() != idHakmilikKe.length() || !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length())) || lTo < lFrom) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");
        } else {

            listIdHakmilik.add(idHakmilikDari);
            listIdHakmilik.add(idHakmilikKe);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.setGroupingUsed(false);
            df.setMinimumIntegerDigits(from.length());
            for (long l = lFrom + 1; l < lTo; l++) {
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
    private String getSenaraiHakmilikUrusan(ArrayList<HakmilikPermohonan> hakmilikPermohonan,
            ArrayList<String> idHakmilikSiriDari, ArrayList<String> idHakmilikSiriKe) {
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.setGroupingUsed(false);

        StringBuilder list = new StringBuilder();
        int s = hakmilikPermohonan.size();
        for (int j = 0; j < s; j++) {
            HakmilikPermohonan hp = hakmilikPermohonan.get(j);
            if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null && hp.getHakmilik().getIdHakmilik().trim().length() > 0) {
                if (j != 0) {
                    list.append(", ");
                }
                list.append(hp.getHakmilik().getIdHakmilik());
            }
        }
        // hakmilik bersiri
        if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
            for (int k = 0; k < idHakmilikSiriDari.size(); k++) {
                String dr = idHakmilikSiriDari.get(k);
                String ke = idHakmilikSiriKe.get(k);
                if (dr != null && dr.trim().length() > 0 && ke != null && ke.trim().length() > 0) {
                    appendIdHakmilikBersiri(list, dr, ke, df);
                }
            }
        }

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

    @SuppressWarnings("unchecked")
    private final void saveUrusanToSession(etanahActionBeanContext ctx) {
        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
            if (debug) {
                LOG.debug("no urusan to be saved to session");
            }
            return;
        }

        // save all data to Urusan object
        if (debug) {
            for (UrusanValue ku : senaraiUrusan) {
                LOG.debug("saving urusan " + ku.getKodUrusan());
            }
        }
        UrusanCache u = new UrusanCache();
        u.hakmilikPermohonan = hakmilikPermohonan;
        u.idHakmilikSiriDari = idHakmilikSiriDari;
        u.idHakmilikSiriKe = idHakmilikSiriKe;
        u.senaraiDokumenSerahan = senaraiDokumenSerahan;
        u.senaraiDokumenTamb = senaraiDokumenTamb;
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
        au.add(u);

    }

    private void resetUrusan() {
        senaraiUrusan = null;
        hakmilikPermohonan = null;
        idHakmilikSiriDari = null;
        idHakmilikSiriKe = null;
        senaraiDokumenSerahan = null;
        senaraiDokumenTamb = null;
    }

    private void resetAll() {
        resetUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        idPenyerah = null;
        penyerahKod = null;
        penyerahJenisPengenalan = null;
        penyerahNoPengenalan = null;
        penyerahNama = null;
        penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4 = penyerahPoskod = null;
        penyerahNegeri = null;
        idFail = null;
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
        idHakmilikSiriDari = u.idHakmilikSiriDari;
        idHakmilikSiriKe = u.idHakmilikSiriKe;
        senaraiDokumenSerahan = u.senaraiDokumenSerahan;
        senaraiDokumenTamb = u.senaraiDokumenTamb;
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

        int skwBaru = 0; // surat kuasa wakil baru
        int sbBaru = 0; // surat kuasa kebenaran baru
        int saBaru = 0; // surat kuasa amanah baru
        ArrayList<TransaksiValue> senaraiTransaksi = new ArrayList<TransaksiValue>();
        for (UrusanCache uc : txnGroups) {
            // prepare list of hakmilik
            String senaraiHakmilik = getSenaraiHakmilikUrusan(uc.hakmilikPermohonan,
                    uc.idHakmilikSiriDari, uc.idHakmilikSiriKe);

            for (int i = 0; i < uc.senaraiUrusan.size(); i++) {
                UrusanValue uv = uc.senaraiUrusan.get(i);
                if (debug) {
                    LOG.debug("processing " + uv.getKodUrusan());
                }
                if (isUrusanNull(uv)) {
                    continue;
                }
                TransaksiValue t = new TransaksiValue();
                KodUrusan ku = kodUrusanDAO.findById(uv.getKodUrusan());
                if (debug) {
                    LOG.debug("nama urusan " + ku.getKod());
                }
                t.setKodUrusan(uv.getKodUrusan());
                t.setNamaUrusan(ku.getNama());
                t.setSenaraiHakmilik(senaraiHakmilik);
                t.setUtkUrusan(uv);
                t.setAmaun(ku.getCaj());
                if (ku.getKodTransaksi() != null) {
                    t.setKodTransaksi(ku.getKodTransaksi().getKod());
                } else {
                    t.setAmaun(BigDecimal.ZERO);
                }
                // TODO t.kodTransaksi
                senaraiTransaksi.add(t);

                jumlahCaj = jumlahCaj.add(t.getAmaun());

            }

            // count suratkuasa baru submitted
            for (DokumenValue dv : uc.senaraiDokumenSerahan) {
                if (dv == null) {
                    continue;
                }
                if ("SWB".equals(dv.getKodDokumen())) {
                    skwBaru += dv.getBil();
                } else if ("SBB".equals(dv.getKodDokumen())) {
                    sbBaru += dv.getBil();
                } else if ("SAB".equals(dv.getKodDokumen())) {
                    saBaru += dv.getBil();
                }
            }

        }

        // check for SuratKuasa Wakil Baru, Surat Kebenaran Baru, Surat Amanah Baru
        if (skwBaru > 0) {
            KodUrusan ku = kodUrusanDAO.findById("SWB");
            TransaksiValue t3 = new TransaksiValue();
            t3.setKodUrusan("SKW");
            t3.setNamaUrusan(ku.getNama());
            t3.setSenaraiHakmilik("-");
            t3.setAmaun(ku.getCaj().multiply(new BigDecimal(skwBaru)));
            t3.setUtkUrusan(null);
            t3.setKodTransaksi(ku.getKodTransaksi().getKod());
            t3.setKuantiti(skwBaru);

            senaraiTransaksi.add(t3);
            jumlahCaj = jumlahCaj.add(t3.getAmaun());
        }

        // check for Surat Kebenaran Baru
        if (sbBaru > 0) {
            KodUrusan ku = kodUrusanDAO.findById("SBB");
            TransaksiValue t3 = new TransaksiValue();
            t3.setKodUrusan("SBB");
            t3.setNamaUrusan(ku.getNama());
            t3.setSenaraiHakmilik("-");
            t3.setAmaun(ku.getCaj().multiply(new BigDecimal(sbBaru)));
            t3.setUtkUrusan(null);
            t3.setKodTransaksi(ku.getKodTransaksi().getKod());
            t3.setKuantiti(sbBaru);

            senaraiTransaksi.add(t3);
            jumlahCaj = jumlahCaj.add(t3.getAmaun());
        }

        // check for Surat Amanah Baru
        if (saBaru > 0) {
            KodUrusan ku = kodUrusanDAO.findById("SAB");
            TransaksiValue t3 = new TransaksiValue();
            t3.setKodUrusan("SAB");
            t3.setNamaUrusan(ku.getNama());
            t3.setSenaraiHakmilik("-");
            t3.setAmaun(ku.getCaj().multiply(new BigDecimal(sbBaru)));
            t3.setUtkUrusan(null);
            t3.setKodTransaksi(ku.getKodTransaksi().getKod());
            t3.setKuantiti(saBaru);

            senaraiTransaksi.add(t3);
            jumlahCaj = jumlahCaj.add(t3.getAmaun());
        }

        jumlahCaj.setScale(2);
        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj.toPlainString());
        }

        return senaraiTransaksi;
    }

    @SuppressWarnings("unchecked")
    private static final void ensureListCapacity(List l, int c) {
        if (l.size() < c) {
            for (int i = l.size(); i <= c; i++) {
                l.add(null);
            }
        }
    }

    private static final boolean isUrusanNull(UrusanValue uv) {
        return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));
    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0") && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
    }

    private Permohonan savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan,
            UrusanValue uv, boolean isBerangkai, boolean adaPermohonanSebelum, String[] idKumpulan, FolderDokumen fd,
            Permohonan permohonanSebelum, List<HakmilikPermohonan> hakmilikPermohonan,
            List<String> idHakmilikSiriDari, List<String> idHakmilikSiriKe, InfoAudit ia) {
        String idPermohonan = null;
        if (KOD_JABATAN_PENDAFTARAN.equals(kodUrusan.getJabatan().getKod())) { // for pendaftaran, use ID Perserahan
            LOG.info("/* GENERATE ID MOHON PENDAFTARAN HERE */");
            LOG.info("kod Daerah~~~" + kodJenisDaerah);
            if (kodJenisDaerah != null) {
                KodCawangan kc = new KodCawangan();
                kc.setKod(kodJenisDaerah);
                idPermohonan = idPerserahanGenerator.generate(
                        kodNegeri, kc, kodUrusan);
            } else {
                idPermohonan = idPerserahanGenerator.generate(
                        kodNegeri, pengguna.getKodCawangan(), kodUrusan);
            }
        } else {
            idPermohonan = idPermohonanGenerator.generate(
                    kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        }
        uv.setIdPermohonan(idPermohonan);

        if (isBerangkai && idKumpulan[0] == null) {
            idKumpulan[0] = idPermohonan;
            LOG.info("Id Kumpulan::" + idKumpulan);
        }

        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        if (kodJenisDaerah != null) {
            KodCawangan kc = new KodCawangan();
            kc.setKod(kodJenisDaerah);
            p.setCawangan(kc);
        } else {
            p.setCawangan(pengguna.getKodCawangan());
        }
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);
//        if (isBerangkai) {
//            p.setIdKumpulan(idKumpulan[0]);
//        }
        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);
        // penyerah
        if (idPenyerah != null && idPenyerah.length() > 0 && !"0".equals(idPenyerah)) {
            p.setIdPenyerah(Long.parseLong(idPenyerah));
        }
        if (penyerahKod != null && penyerahKod.getKod() != null && !"0".equals(penyerahKod.getKod())) {
            p.setKodPenyerah(penyerahKod);
        }
        p.setCatatan(String.valueOf(totalHakmilik));
        if (catatan.length() > 0) {
            p.setCatatan(catatan.toString()); //utk kegunaan tukar ganti!!
        }
        p.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
        p.setPenyerahNoPengenalan(penyerahNoPengenalan);
        p.setPenyerahNama(penyerahNama);
        p.setPenyerahAlamat1(penyerahAlamat1);
        p.setPenyerahAlamat2(penyerahAlamat2);
        p.setPenyerahAlamat3(penyerahAlamat3);
        p.setPenyerahAlamat4(penyerahAlamat4);
        p.setPenyerahPoskod(penyerahPoskod);
        if (penyerahNegeri != null) {
            p.setPenyerahNegeri(penyerahNegeri);
        }
        if (isBerangkai && permohonanSebelum != null) {
            p.setPermohonanSebelum(permohonanSebelum);
        }
        if (adaPermohonanSebelum && permohonanSebelum == null) {
            p.setPermohonanSebelum(permohonanDAO.findById(strIdMohon));
        }
        permohonanDAO.save(p);

        List<Hakmilik> listId = null;

        // attach Hakmilik bersiri
        if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
            for (int i = 0; i < idHakmilikSiriDari.size(); i++) {
                String idH1 = idHakmilikSiriDari.get(i);
                String idH2 = idHakmilikSiriKe.get(i);

                if (idH1 == null || idH1.trim().length() == 0 || idH2 == null || idH2.trim().length() == 0) {
                    continue;
                }

                ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);

                listId = sessionProvider.get().createQuery(
                        "select h from Hakmilik h inner join fetch h.senaraiAkaun a " + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();

                if (kodUrusan.getKodPerserahan().getKod().equals("MH")) {
                    continue;
                }

                for (Hakmilik hm : listId) {
                    /*
                     if (perluJelasCukai){
                     Akaun ac = hm.getAkaunCukai();
                     if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0){
                     throw new RuntimeException("ID Hakmilik " + hm.getIdHakmilik()
                     + " masih belum menjelaskan Cukai. Urusan ini memerlukan cukai dijelaskan.");
                     }
                     }
                     */
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hpa.setInfoAudit(ia);
                    hpa.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa);
                }
            }
        }

        //special case for mohon hakmilik cantuman
        //will generate new id hakmilik
        //insert into table hakmilik, mohon_pihak, hakmilik_urusan
        if (kodUrusan.getKod().equals("HKC") || kodUrusan.getKod().equals("HSC")
                || kodUrusan.getKod().equals("HKCTK") || kodUrusan.getKod().equals("HSCTK")) {
            LOG.debug("start save hakmilik cantum");
            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
//            janaHakmilikBaruFromHakmilikTerlibat(hakmilikPermohonan, hakmilik, ia, p, pengguna, totalHakmilik, kodBPM);
            saveHakmilikCantuman(hakmilikPermohonan, iaPermohonan, p, pengguna);
        } else if (kodUrusan.getKod().equals("HT") || kodUrusan.getKod().equals("HTSC")
                || kodUrusan.getKod().equals("HTSPB") || kodUrusan.getKod().equals("HTSPS")
                || kodUrusan.getKod().equals("HTSPV")) {
            generateHakmilikStrata(ia, p, pengguna);
        } else if (kodUrusan.getKod().equals("HSBM") || kodUrusan.getKod().equals("HKBM") || kodUrusan.getKod().equals("BMSTM")) {
            janaHakmilikBaru(hakmilik, ia, p, pengguna, totalHakmilik);
        } else if (kodUrusan.getKod().equals("HKTHK") || kodUrusan.getKod().equals("HSTHK")) {
            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
            convertHakmilikSPTB(hakmilikPermohonan, ia, p);
        } else if (kodUrusan.getKod().equals("HKPS") || kodUrusan.getKod().equals("HTSPS")
                || kodUrusan.getKod().equals("HSPS") || kodUrusan.getKod().equals("HKSB")
                || kodUrusan.getKod().equals("HKPB") || kodUrusan.getKod().equals("HSPB")
                || kodUrusan.getKod().equals("HKBTK") || kodUrusan.getKod().equals("HSBTK")
                || kodUrusan.getKod().equals("HSPTK") || kodUrusan.getKod().equals("HKABS")
                || kodUrusan.getKod().equals("HKABT") || kodUrusan.getKod().equals("HKGHS")
                || kodUrusan.getKod().equals("HKPTK") || kodUrusan.getKod().equals("HMSC")
                || kodUrusan.getKod().equals("HKTK") || kodUrusan.getKod().equals("HSTK")
                || kodUrusan.getKod().equals("HKSTK") || kodUrusan.getKod().equals("HSSB") || kodUrusan.getKod().equals("HSSHS")
                || kodUrusan.getKod().equals("HSSTA") || kodUrusan.getKod().equals("HSSBB")
                || kodUrusan.getKod().equals("HKSBB") || kodUrusan.getKod().equals("HSSTA")
                || kodUrusan.getKod().equals("HKSTA") || kodUrusan.getKod().equals("HMSTP")) {
            LOG.debug("start save hakmilik selain cantum dan berimilik");
            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
            janaHakmilikBaruFromHakmilikTerlibat(hakmilikPermohonan, hakmilik, ia, p, pengguna, totalHakmilik, kodBPM);
        } // add by azri : hakmilik_asal and hakmilik_sblm
        else if (kodUrusan.getKod().equals("HKSHS") || kodUrusan.getKod().equals("HKTK")
                || kodUrusan.getKod().equals("HSTK") || kodUrusan.getKod().equals("HKSA")
                || kodUrusan.getKod().equals("HSSA") || kodUrusan.getKod().equals("HKTKP")
                || kodUrusan.getKod().equals("HSTKP")
                || kodUrusan.getKod().equals("HSSAA") || kodUrusan.getKod().equals("HKP")
                || kodUrusan.getKod().equals("HSP")) {
            LOG.info("/* TEST UNTUK URUSAN CARA SIMPAN DALAM TABLE MOHON_HAKMILIK_ASAL n MOHON_HAKMILIK_SBLM*/");
            LOG.info("--> kod urusan : " + kodUrusan.getKod());
            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
            janaHakmilikBaruFromHakmilikTerlibatV2(hakmilikPermohonan, hakmilik, ia, p, pengguna, totalHakmilik, kodBPM);
            //start
        } else if (kodUrusan.getKod().equals("HTIR")) {

            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
            LOG.debug("kod Jenis hakmilik~~~~~" + kodJenisHakmilik);
            if (kodJenisHakmilik.equalsIgnoreCase("GRN") || kodJenisHakmilik.equalsIgnoreCase("PN")) {
                LOG.debug("start generate hakmilik baru");
                janaHakmilikBaruFromHakmilikTerlibatV2(hakmilikPermohonan, hakmilik, ia, p, pengguna, totalHakmilik, kodBPM);
            } else if (kodJenisHakmilik.equalsIgnoreCase("GM") || kodJenisHakmilik.equalsIgnoreCase("PM")) {
                LOG.debug("start generate hakmilik daerah baru");
                janaHakmilikBaruFromHakmilikDaerah(hakmilikPermohonan, hakmilik, ia, p, pengguna, totalHakmilik, kodBPM);
            }
        } //end 
        else if (kodUrusan.getKod().equals("HSSTB") || kodUrusan.getKod().equals("HKSTB")) {
            LOG.debug("start save hakmilik serahbalik semua dan berimilik");
            if (listId != null) {
                for (Hakmilik hm : listId) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hakmilikPermohonan.add(hpa);
                }
            }
            saveHakmilikSerahbalikSemuaTanahBerimilik(hakmilikPermohonan, ia, p, pengguna, totalHakmilik, kodUrusan);
        } else if (!kodUrusan.getKod().equals("MH")) {
            // attach hakmilik
            LOG.debug("start save hakmilik selain MH");
            if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
                for (HakmilikPermohonan hp : hakmilikPermohonan) {
                    if (hp == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (hm != null && hm.getIdHakmilik() != null && hm.getIdHakmilik().trim().length() > 0) {
                        String id = hm.getIdHakmilik();
                        hm = hakmilikDAO.findById(id);
                        if (hm == null) {
                            throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                        }
                        HakmilikPermohonan hpa = new HakmilikPermohonan();
                        hpa.setHakmilik(hm);
                        hpa.setInfoAudit(ia);
                        hpa.setPermohonan(p);
                        hakmilikPermohonanDAO.save(hpa);
                    }
                }
            }
        } else {
            saveHakmilikSelainPecahCantumDanBerimilik(hakmilikPermohonan, ia, p, pengguna);
        }
        return p;
    }

    private Hakmilik generateStrata(InfoAudit ia, int noPetakNew, HakmilikPermohonan hp, Permohonan p, BigDecimal luasBaru, int kes,
            BigDecimal unitSyerBaru) {
//        for (int s = 0; s < kes; s++) {
        Hakmilik h = new Hakmilik();
        h.setKodHakmilik(hp.getHakmilik().getKodHakmilik());
        h.setIdHakmilik(hp.getHakmilik().getIdHakmilikInduk());
        h.setNoBangunan(hp.getHakmilik().getNoBangunan());
        h.setNoTingkat(hp.getHakmilik().getNoTingkat());
        h.setNoPetak(String.valueOf(noPetakNew));
        String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, h);
        h = strataService.hakmilikBaru(hp.getHakmilik().getIdHakmilikInduk(), hp.getHakmilik(), idHakmilikBaru, luasBaru, String.valueOf(noPetakNew), unitSyerBaru, ia);
        regService.simpanHakmilik(h);

        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
        mohonHakmilikBaru.setHakmilik(h);
        mohonHakmilikBaru.setPermohonan(p);
        mohonHakmilikBaru.setInfoAudit(ia);
        regService.simpanMohonHakmilik(mohonHakmilikBaru);
        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hp.getHakmilik());
        LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }

            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
            hpb.setHakmilik(h);
            hpb.setCawangan(h.getCawangan());
            hpb.setPihakCawangan(hpk.getPihakCawangan());
            hpb.setJenis(hpk.getJenis());
            hpb.setSyerPembilang(hpk.getSyerPembilang());
            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
            hpb.setPerserahan(hpk.getPerserahan());
            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
            hpb.setKaveatAktif(hpk.getKaveatAktif());
            hpb.setAktif(hpk.getAktif());
            hpb.setPihak(hpk.getPihak());
            hpb.setInfoAudit(ia);
            hpkService.save(hpb);

//            }
        }
        return h;
    }

    private void generateHakmilikStrata(InfoAudit ia, Permohonan p, Pengguna pengguna) {
        Permohonan permohonanSebelum = p.getPermohonanSebelum();
//        List<HakmilikPihakBerkepentingan> lhp = new  ArrayList<HakmilikPihakBerkepentingan>();
        if (permohonanSebelum != null) {
            Hakmilik hMasterTitle = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
            List<PermohonanStrata> listStrata = permohonanSebelum.getSenaraiStrata();
            BadanPengurusan bdn = strataService.findBdn(permohonanSebelum.getIdPermohonan());
            LOG.debug(":generate Hakmilik Strata:");
            LOG.debug("idPermohonanSebelum :" + permohonanSebelum.getIdPermohonan());
//            List<PermohonanBangunan> senaraiBangunan = permohonanSebelum.getSenaraiBangunan();

            if (p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSC") || p.getKodUrusan().getKod().equals("HTSPS")) {
                int kes = 0;
                BigDecimal luasBaru = BigDecimal.ZERO;
                BigDecimal unitSyerBaru = BigDecimal.ZERO;
                int noPetakNew = 0;
                HakmilikPermohonan hakmil = new HakmilikPermohonan();
                Hakmilik siap = new Hakmilik();
                for (HakmilikPermohonan hp : permohonanSebelum.getSenaraiHakmilik()) {
                    Hakmilik maxPetak = strataService.getMaxbyBngnAndInduk(hp.getHakmilik().getIdHakmilikInduk(), hp.getHakmilik().getNoBangunan());
                    noPetakNew = Integer.parseInt(maxPetak.getNoPetak());
                    if (p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSPS")) {
                        luasBaru = hp.getHakmilik().getLuas().divide(new BigDecimal(2));
                        unitSyerBaru = hp.getHakmilik().getUnitSyer().divide(new BigDecimal(2));
                        kes = 2;
                        for (int i = 0; i < kes; i++) {
                            noPetakNew = noPetakNew + 1;
                            siap = generateStrata(ia, noPetakNew, hp, p, luasBaru, kes, unitSyerBaru);
                            HakmilikPermohonan hp1 = regService.searchMohonHakmilikObject(siap.getIdHakmilik(), p.getIdPermohonan());
                            strataService.updateHakmilikSblm(hp.getHakmilik(), siap, ia, hp1);
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                            System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                            HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                            hakmilikSebelumBaru.setHakmilik(siap);
                            System.out.println("--hakmilik adding to hakmiliksebelumlist--:" + hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setInfoAudit(ia);
                            regService.simpanHakmilikSebelum(hakmilikSebelumBaru);
                        }
                    }
                    if (p.getKodUrusan().getKod().equals("HTSC")) {
                        luasBaru = luasBaru.add(hp.getHakmilik().getLuas());
                        unitSyerBaru = unitSyerBaru.add(hp.getHakmilik().getUnitSyer());
                        kes = 1;
                        hakmil = hp;

                    }

                }
                if (p.getKodUrusan().getKod().equals("HTSC")) {
                    noPetakNew = noPetakNew + 1;
                    siap = generateStrata(ia, noPetakNew, hakmil, p, luasBaru, kes, unitSyerBaru);
                    HakmilikPermohonan hp1 = regService.searchMohonHakmilikObject(siap.getIdHakmilik(), p.getIdPermohonan());
                    for (HakmilikPermohonan hp : permohonanSebelum.getSenaraiHakmilik()) {
                        strataService.updateHakmilikSblm(hp.getHakmilik(), siap, ia, hp1);
                        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                        System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                        HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                        hakmilikSebelumBaru.setHakmilik(siap);
                        System.out.println("--hakmilik adding to hakmiliksebelumlist--:" + hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setInfoAudit(ia);
                        regService.simpanHakmilikSebelum(hakmilikSebelumBaru);
                    }

                }

            }

            if (p.getKodUrusan().getKod().equals("HT") || p.getKodUrusan().getKod().equals("HTSPV")) {
                List<PermohonanBangunan> senaraiBangunan = regService.senaraiBangunan(permohonanSebelum);

                LOG.debug(":Bangunan : " + senaraiBangunan.size());
                for (PermohonanBangunan permohonanBangunan : senaraiBangunan) {

                    List<BangunanTingkat> senaraiTingkat = permohonanBangunan.getSenaraiTingkat();
                    LOG.debug(":Tingkat : " + senaraiTingkat.size());
                    int countTingkat = 1;
                    int countPetak = 1;
                    for (BangunanTingkat bangunanTingkat : senaraiTingkat) {
                        List<BangunanPetak> senaraiPetak = bangunanTingkat.getSenaraiPetak();

                        LOG.debug(":Petak : " + senaraiPetak.size());

                        for (BangunanPetak bangunanPetak : senaraiPetak) {
                            String noBangunan = permohonanBangunan.getNama();

                            String noTingkat = String.valueOf(countTingkat);
                            String noPetak = String.valueOf(countPetak);

                            Hakmilik hakmilik = new Hakmilik();
                            hakmilik.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilik.setDaerah(hMasterTitle.getDaerah());
                            hakmilik.setNoBangunan(noBangunan);
                            hakmilik.setNoTingkat(noTingkat);
                            hakmilik.setNoPetak(noPetak);

                            hakmilik.setIdHakmilik(hMasterTitle.getIdHakmilik());
                            LOG.debug("noBangunan : " + hakmilik.getNoBangunan());
                            LOG.debug("noBangunan substring 1 : " + hakmilik.getNoBangunan().substring(1));
                            String idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);

                            LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                            Hakmilik hakmilikBaru = new Hakmilik();
                            /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                            hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
                            hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                            hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
                            hakmilikBaru.setBandarPekanMukim(hMasterTitle.getBandarPekanMukim());
                            hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
                            hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
                            hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
                            hakmilikBaru.setLot(hMasterTitle.getLot());
                            hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
                            hakmilikBaru.setKategoriTanah(hMasterTitle.getKategoriTanah());
                            hakmilikBaru.setKegunaanTanah(hMasterTitle.getKegunaanTanah());

                            hakmilikBaru.setTarikhDaftar(new java.util.Date());
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("T");
                            hakmilikBaru.setKodStatusHakmilik(ksh);
                            hakmilikBaru.setLotBumiputera(hMasterTitle.getLotBumiputera());
                            hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
                            hakmilikBaru.setKodUnitLuas(bangunanPetak.getLuasUom());
                            hakmilikBaru.setLuas(bangunanPetak.getLuas());
                            hakmilikBaru.setUnitSyer(new BigDecimal(bangunanPetak.getSyer()));
                            hakmilikBaru.setJumlahUnitSyer(listStrata.get(0).getJumlahSyer());
                            if (bdn != null) {
                                hakmilikBaru.setBadanPengurusan(bdn);
                            }
                            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                            /*copy NO HAKMILIK*/
                            LOG.debug("noHakmilik :" + noHakmilik);
                            hakmilikBaru.setNoHakmilik(noHakmilik);
                            hakmilikBaru.setNoPelan(hMasterTitle.getNoPelan());
                            hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
                            hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
                            hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
                            hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
                            hakmilikBaru.setPbt(hMasterTitle.getPbt());
                            hakmilikBaru.setCukai(hMasterTitle.getCukai());
                            hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
                            hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
                            hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
                            hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
                            hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
                            hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
                            hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());
                            hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
                            hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
                            hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
                            hakmilikBaru.setNoBangunan(noBangunan);
                            hakmilikBaru.setNoTingkat(noTingkat);
                            hakmilikBaru.setNoPetak(noPetak);
                            hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
                            hakmilikBaru.setInfoAudit(ia);

                            /*INSERT INTO MOHON HAKMILIK*/
                            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru.setPermohonan(p);
                            mohonHakmilikBaru.setInfoAudit(ia);
                            listMohonHakmilikBaru.add(mohonHakmilikBaru);
                            listHakmilikBaru.add(hakmilikBaru);

                            List<BangunanPetakAksesori> senaraiPetakAksesori = bangunanPetak.getSenaraiPetakAksesori();
                            for (BangunanPetakAksesori bangunanPetakAksesori : senaraiPetakAksesori) {
                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                hpa.setHakmilik(hakmilikBaru);
                                hpa.setCawangan(hakmilikBaru.getCawangan());
                                hpa.setKegunaaanPetak(bangunanPetakAksesori.getKegunaan());
                                hpa.setNama(bangunanPetakAksesori.getNama());
                                hpa.setLokasi(bangunanPetakAksesori.getLokasi());
                                hpa.setStatusHakmilik(hakmilikBaru.getKodStatusHakmilik());
                                hpa.setInfoAudit(ia);
                                listHakmilikPetakAksesori.add(hpa);
                            }

//                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hMasterTitle.getSenaraiPihakBerkepentingan();
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hMasterTitle);
                            LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                                if (hpk == null || hpk.getAktif() == 'T') {
                                    continue;
                                }

                                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                                hpb.setHakmilik(hakmilikBaru);
                                hpb.setCawangan(hakmilikBaru.getCawangan());
                                hpb.setPihakCawangan(hpk.getPihakCawangan());
                                hpb.setJenis(hpk.getJenis());
                                hpb.setSyerPembilang(hpk.getSyerPembilang());
                                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                                hpb.setPerserahan(hpk.getPerserahan());
                                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                                hpb.setKaveatAktif(hpk.getKaveatAktif());
                                hpb.setAktif(hpk.getAktif());
                                hpb.setPihak(hpk.getPihak());
                                hpb.setInfoAudit(ia);
                                //hpkService.save(hpb);
                                lhp.add(hpb);
                            }

                            countPetak += 1;
                        }
                        countTingkat += 1;
                    }

                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                regService.simpanHakmilikList(listHakmilikBaru);
            }
            if (!listMohonHakmilikBaru.isEmpty()) {
                regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
            }
            if (!listHakmilikPetakAksesori.isEmpty()) {
                //TODO:HAKMILIKPETAKAKSESORI SERVICE TO INSERT
                regService.simpanHakmilikPetakAksesori(listHakmilikPetakAksesori);
            }
            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (p.getKodUrusan().getKod().equals("HTSPV")) {
                int i = 0;
                for (HakmilikPermohonan hp : listMohonHakmilikBaru) {
                    if (listMohonHakmilikBaru.size() == p.getPermohonanSebelum().getSenaraiHakmilik().size()) {
                        i = i + 1;
                    } else {
                        i = i;
                    }
                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                    SejarahHakmilik sj = new SejarahHakmilik();
                    sj.setIdHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(i).getHakmilik().getIdHakmilik());
                    sj.setTarikhDaftar(p.getPermohonanSebelum().getSenaraiHakmilik().get(i).getHakmilik().getTarikhDaftar());
                    hsp.setCawangan(hp.getHakmilik().getCawangan());
                    hsp.setHakmilik(hp.getHakmilik());
                    hsp.setHakmilikPermohonan(hp);
                    hsp.setHakmilikSejarah(sj.getIdHakmilik());
                    hsp.setPermohonan(p);
                    hsp.setInfoAudit(ia);
                    regService.simpanMohonHakmilikSebelum(hsp);
                    HakmilikSebelum hs = new HakmilikSebelum();
                    hs.setHakmilik(hp.getHakmilik());
                    hs.setHakmilikSebelum(sj.getIdHakmilik());
                    hs.setInfoAudit(ia);
                    regService.simpanHakmilikSebelum(hs);
                }
            }
        }

    }

    private void saveHakmilikSelainPecahCantumDanBerimilik(List<HakmilikPermohonan> hakmilikPermohonan, InfoAudit ia, Permohonan p, Pengguna pengguna) {
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        if (hakmilikPermohonan.size() > 0) {
            for (HakmilikPermohonan hp : hakmilikPermohonan) {

                if (hp == null) {
                    continue;
                }
                hakmilikBaru = new Hakmilik();
                Hakmilik hakmilikTerpilih = hp.getHakmilik();
                String id = hakmilikTerpilih.getIdHakmilik();
                hakmilikTerpilih = hakmilikDAO.findById(id);
                LOG.debug("kod pekan " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                hakmilikTerpilih.setKodHakmilik(hakmilik.getKodHakmilik());
                String kodNegeri = conf.getProperty("kodNegeri");
                String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikTerpilih);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                hakmilikBaru.setNoFail(idFail);
                hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
                hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());

                //hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                KodLot kl = new KodLot();
                if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                        || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                    kl.setKod("1");
                } else {
                    kl.setKod("2");
                }
                hakmilikBaru.setLot(kl);
                hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());
//                if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() > 0) {
//                    hakmilikBaru.setTarikhDaftar(hakmilikTerpilih.getSenaraiHakmilikAsal().get(0).getHakmilikAsal().getTarikhDaftar());
//                    hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getSenaraiHakmilikAsal().get(0).getHakmilikAsal().getTarikhLuput());
//                } else {
//                    hakmilikBaru.setTarikhDaftar(hakmilikTerpilih.getTarikhDaftar());
//                    hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
//                }
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int tempPeganganYear = Integer.parseInt(sdf.format(hakmilikTerpilih.getTarikhDaftar()));
                int curr = Integer.parseInt(sdf.format(new java.util.Date()));
                int balYear = curr - tempPeganganYear;

                int totaltempPegangan = 0;
                LOG.debug("tempohpegangan :" + hakmilikTerpilih.getTempohPegangan());
                if (!hakmilikTerpilih.getPegangan().equals('S')) {
                    totaltempPegangan = hakmilikTerpilih.getTempohPegangan() - balYear;
                } else {
                    totaltempPegangan = 0;
                }
                LOG.debug("tarikhluput :" + hakmilikTerpilih.getTarikhLuput());
                hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                hakmilikBaru.setTempohPegangan(totaltempPegangan);
                hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());
                hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());
                hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());
                hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());
                hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                if (!p.getKodUrusan().getKod().equals("HKABS")) {
                    hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                    hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                }
                hakmilikBaru.setInfoAudit(ia);

                if (debug) {
                    LOG.debug("start to create new Hakmilik Permohonanan.");
                }
                mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);

                hakmilikDAO.save(hakmilikBaru);
                hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                Hakmilik hm = hp.getHakmilik();
                //String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);

                if (hm.getSenaraiHakmilikAsal().size() > 0) {
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
//          hap.setHakmilikSejarah(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal().getIdHakmilik());
                    hap.setHakmilikSejarah(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                    hap.setInfoAudit(ia);
                    hakmilikAsalPermohonanDAO.save(hap);
                }
                if (hm.getSenaraiHakmilikSebelum().size() > 0) {
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum()));
                    hap.setHakmilikSejarah(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                    hap.setInfoAudit(ia);
                }

                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                hsp.setPermohonan(p);
                hsp.setCawangan(pengguna.getKodCawangan());
                hsp.setHakmilik(hm);
                hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);

                if (debug) {
                    LOG.debug("start to create mohon pihak");
                }
//                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
                for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                    if (hpk == null || hpk.getAktif() == 'T') {
                        continue;
                    }
                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    hpb.setHakmilik(hakmilikBaru);
                    hpb.setCawangan(hakmilikBaru.getCawangan());
                    hpb.setPihakCawangan(hpk.getPihakCawangan());
                    hpb.setJenis(hpk.getJenis());
                    hpb.setSyerPembilang(hpk.getSyerPembilang());
                    hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                    hpb.setPerserahan(hpk.getPerserahan());
                    hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                    hpb.setKaveatAktif(hpk.getKaveatAktif());
                    hpb.setAktif(hpk.getAktif());
                    hpb.setPihak(hpk.getPihak());
                    hpb.setInfoAudit(ia);
                    hpkService.save(hpb);
                }
                if (debug) {
                    LOG.debug("start to create hakmilik urusan");
                }

                List<HakmilikPermohonan> listhakmilikpermohonanlama = regService.senaraiHakmilikPermohonanByIDHakmilik(hm.getIdHakmilik());
                if (!listhakmilikpermohonanlama.isEmpty()) {
                    Permohonan permohonanlama = listhakmilikpermohonanlama.get(0).getPermohonan();
                    List<PermohonanRujukanLuar> senaraiRujLuar = permohonanlama.getSenaraiRujukanLuar();
                    PermohonanRujukanLuar permohonanRujukanLuar = null;
                    if (senaraiRujLuar != null && senaraiRujLuar.size() > 0) {
                        permohonanRujukanLuar = senaraiRujLuar.get(0);//TODO : for multiple rujukan luar?
                    }
                }
                //for (HakmilikPermohonan hakmilikPermohonanLama : listhakmilikpermohonanlama) {

                List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                    if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                        continue;
                    }
                    HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                    hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                    hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                    hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                    hakmilikUrusanBaru.setAktif(hu.getAktif());
                    hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                    hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                    hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                    hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                    hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                    hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                    //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
                    hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                    hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                    hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                    hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
//                    if (permohonanRujukanLuar != null) {
//                        hakmilikUrusanBaru.setTempohTahun(permohonanRujukanLuar.getTempohTahun());
//                        hakmilikUrusanBaru.setTempohBulan(permohonanRujukanLuar.getTempohBulan());
//                        hakmilikUrusanBaru.setTempohHari(permohonanRujukanLuar.getTempohHari());
//                    }
                    hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                    hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                    hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                    hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                    hakmilikUrusanBaru.setItem(hu.getItem());
                    hakmilikUrusanBaru.setInfoAudit(ia);
                    hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                }

            }
        }

    }

    private void convertHakmilikSPTB(List<HakmilikPermohonan> hakmilikPermohonan, InfoAudit ia, Permohonan p) {
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        long t = System.currentTimeMillis();
        //List<HakmilikPermohonan> hp2 = hakmilikPermohonan;
        LOG.debug("::START CONVERT HAKMILIK SPTB::");
        if (hakmilikPermohonan.size() > 0) {
            LOG.debug("size hakmilikPermohonan :" + hakmilikPermohonan.size());
            for (HakmilikPermohonan hp : hakmilikPermohonan) {
                if (hp == null) {
                    continue;
                }
//                Hakmilik hm = hakmilikDAO.findById(hakmilikPermohonan1.getHakmilik().getIdHakmilik());
//                if (hm == null) {
//                    continue;
//                }
                if (hp.getHakmilik() == null) {
                    continue;
                }

                if (hp.getHakmilik().getIdHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                if (hm == null) {
                    continue;
                }

                Hakmilik hakmilikTerpilih = hp.getHakmilik();
                String id = hakmilikTerpilih.getIdHakmilik();
                hakmilikTerpilih = hakmilikDAO.findById(id);

                mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hp.getHakmilik());
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                hakmilikPermohonanDAO.save(mohonHakmilikBaru);

            }
        }
    }

    private void janaHakmilikBaruFromHakmilikTerlibat(List<HakmilikPermohonan> hakmilikPermohonan, Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, String kodBPM) {
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        long t = System.currentTimeMillis();
        List<HakmilikPermohonan> hp2 = hakmilikPermohonan;

        HakmilikPermohonan mohonHakmilikBaru2 = null;
        PermohonanRujukanLuar mohonRujLuarBaru = null;

        List<HakmilikAsalPermohonan> senaraiAsal = new ArrayList<HakmilikAsalPermohonan>();
        List<HakmilikSebelumPermohonan> senaraiSblm = new ArrayList<HakmilikSebelumPermohonan>();
        List<HakmilikSebelumPermohonan> senaraiSblm2 = new ArrayList<HakmilikSebelumPermohonan>();

        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {
                for (int i = 0; i < totalHakmilik; i++) {
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    hakmilikBaru = new Hakmilik();
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    LOG.info("idHakmilik = " + id);
                    hakmilikTerpilih = hakmilikDAO.findById(id);

                    hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
                    //hakmilikTerpilih.setKodHakmilik(hakmilik.getKodHakmilik());
                    if (hakmilik.getKodHakmilik() != null) {
                        hakmilikBaru.setKodHakmilik(hakmilik.getKodHakmilik());
                    } else {
                        hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
                    }
                    if (hakmilikTerpilih.getPermohonan() != null) {
                        hakmilikBaru.setPermohonan(hakmilikTerpilih.getPermohonan());
                    }

                    if (StringUtils.isNotBlank(kodBPM)) {
                        LOG.debug("tukar bpm :+" + kodBPM);
                        KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        kbpm = regService.cariBPM(kodBPM, hakmilikTerpilih.getDaerah().getKod());
                        //hakmilik.setBandarPekanMukim(kbpm);
                        hakmilikBaru.setBandarPekanMukim(kbpm);
                    } else {
                        hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                    }
                    LOG.debug("kod pekan " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                    String kodNegeri = conf.getProperty("kodNegeri");
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setNoFail(idFail);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());;
                    hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());;
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    if (p.getKodUrusan().getKod().equals("HKBTK") || p.getKodUrusan().getKod().equals("HKCTK")
                            || p.getKodUrusan().getKod().equals("HKPTK")
                            || p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HSBTK")
                            || p.getKodUrusan().getKod().equals("HSBTK") || p.getKodUrusan().getKod().equals("HSCTK")
                            || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HSTK")
                            || p.getKodUrusan().getKod().equals("HKSHS") || p.getKodUrusan().getKod().equals("HSSHS")
                            || p.getKodUrusan().getKod().equals("HKP") || p.getKodUrusan().getKod().equals("HSP")
                            || p.getKodUrusan().getKod().equals("HSSAA") || p.getKodUrusan().getKod().equals("HMSC")) {

                        hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                        hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                        hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                        hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                        hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                        hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                    } else {
                        BigDecimal totalLuas = new BigDecimal(0);
                        hakmilikBaru.setLuas(totalLuas);
                    }
                    //hakmilikBaru.setLot(hakmilikTerpilih.getLot());
//                    hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());

                    LOG.debug("size hakmilik asal :" + hakmilikTerpilih.getSenaraiHakmilikAsal().size());
                    LOG.debug("size hakmilik sblm :" + hakmilikTerpilih.getSenaraiHakmilikSebelum().size());

                    LOG.debug("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                    if (hakmilikTerpilih.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                    }
                    hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());

                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());

                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());

                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());
                    hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                    LOG.info("hakmilikBaru3 = " + hakmilikBaru.getLuas());

                    if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HKGHS") || p.getKodUrusan().getKod().equals("HKSTK")) { //yus remark2
                        if (p.getKodUrusan().getKod().equals("HKABS")) {
                            String kodUrusanNoting = "ABTKB";
                            List<HakmilikPermohonan> hpTerpilihList = pService.findByIdHakmilikOnlyByNotingPengambilan(hakmilikTerpilih.getIdHakmilik(), kodUrusanNoting);
                            HakmilikPermohonan hpNoting = hpTerpilihList.get(0);
                            if (hpNoting.getLuasTerlibat() != null) {
                                hakmilikBaru.setLuas(hpNoting.getLuasTerlibat());
                            } else {
//                                LOG.info("hpNoting = " + hpNoting.getluas);
                                if (hpNoting.getLuasLama() != null)  {
                                    hakmilikBaru.setLuas(hpNoting.getLuasLama());
                                } else if (hpNoting.getLuasTerlibat() != null) {
                                    hakmilikBaru.setLuas(hpNoting.getLuasTerlibat());
                                }
                            }

                        }
//                            else {
//                            hakmilikBaru.setLuasLama(hakmilikTerpilih.getLuas());
//                        }

                    }

                    if (debug) {
                        LOG.debug("start to divide luas");
                    }
                    hakmilikBaru.setInfoAudit(ia);
                    LOG.info("hakmilikTerpilih = " + hakmilikTerpilih.getIdHakmilik());
                    LOG.info("hakmilikTerpilih = " + hakmilikBaru.getLuas());
                    /* To do start*/
                    kodRujukan = kodRujukanDAO.findById("FL");
                    LOG.debug("copy mohon rujuk luar  from hakmilik lama");
                    List<PermohonanRujukanLuar> senaraiMrl = regService.searchMohonRujLuarByIdHakmilik(hakmilikTerpilih.getIdHakmilik());
                    LOG.debug("size senarai Mrl~~~" + senaraiMrl.size());

                    //get mohon rujuk luar from hakmilik lama
                    for (PermohonanRujukanLuar mohonRujLuar : senaraiMrl) {
                        mohonRujLuarBaru = new PermohonanRujukanLuar();
                        mohonRujLuarBaru.setHakmilik(hakmilikBaru);
                        mohonRujLuarBaru.setPermohonan(mohonRujLuar.getPermohonan());
                        mohonRujLuarBaru.setCatatan(mohonRujLuar.getCatatan());
                        mohonRujLuarBaru.setCawangan(mohonRujLuar.getCawangan());
                        if (mohonRujLuar.getKodRujukan() != null) {
                            mohonRujLuarBaru.setKodRujukan(mohonRujLuar.getKodRujukan());
                        } else if (mohonRujLuar.getKodRujukan() == null) {
                            mohonRujLuarBaru.setKodRujukan(kodRujukan);
                        }
                        mohonRujLuarBaru.setNoFail(mohonRujLuar.getNoFail());
                        mohonRujLuarBaru.setNoRujukan(mohonRujLuar.getNoRujukan());
                        mohonRujLuarBaru.setNoSidang(mohonRujLuar.getNoSidang());
                        mohonRujLuarBaru.setNamaSidang(mohonRujLuar.getNamaSidang());
                        mohonRujLuarBaru.setTarikhSidang(mohonRujLuar.getTarikhSidang());
                        mohonRujLuarBaru.setTarikhKuatkuasa(mohonRujLuar.getTarikhKuatkuasa());
                        mohonRujLuarBaru.setUlasan(mohonRujLuar.getUlasan());
                        if (mohonRujLuar.getTempohBulan() != null) {
                            mohonRujLuarBaru.setTempohBulan(mohonRujLuar.getTempohBulan());
                        } else {
                            mohonRujLuarBaru.setTempohBulan(0);
                        }
                        if (mohonRujLuar.getTempohHari() != null) {
                            mohonRujLuarBaru.setTempohHari(mohonRujLuar.getTempohHari());
                        } else {
                            mohonRujLuarBaru.setTempohHari(0);
                        }
                        if (mohonRujLuar.getTempohTahun() != null) {
                            mohonRujLuarBaru.setTempohTahun(mohonRujLuar.getTempohTahun());
                        } else {
                            mohonRujLuarBaru.setTempohTahun(0);
                        }
                        mohonRujLuarBaru.setTarikhRujukan(mohonRujLuar.getTarikhRujukan());
                        mohonRujLuarBaru.setItem(mohonRujLuar.getItem());
                        mohonRujLuarBaru.setInfoAudit(ia);
                        permohonanRujukanLuarDAO.saveOrUpdate(mohonRujLuarBaru);
                    }

                    /* end */
                    if (debug) {
                        LOG.debug("start to create new Hakmilik Permohonanan.");
                    }

                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    /*TODO:SAVE LUAS TERLIBAT FROM MOHON HAKMILIK LAMA UTK HKABS*/
                    List<HakmilikPermohonan> temphp = new ArrayList();

                    LOG.debug("SET LUAS TERLIBAT DARI NOTTING HKSB"); //yus remark
                    List<HakmilikPermohonan> senaraiHP = regService.searchMohonHakmilik(hakmilikTerpilih.getIdHakmilik());
                    for (HakmilikPermohonan hakmilikPermohonan1 : senaraiHP) {
                        LOG.debug("LIST URUSAN UNTUK HAKMILIK : " + hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod());
                        if (hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("SBSTL")
                                || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABT-K")
                                || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTKB")
                                || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTBH")) {
                            //temphp.add(hakmilikPermohonan1);
                            mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getLuasTerlibat());
                            mohonHakmilikBaru.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                            mohonHakmilikBaru.setCukaiBaru(hakmilikPermohonan1.getCukaiBaru());
                        }
                        mohonHakmilikBaru.setSekatanKepentingan(hakmilikPermohonan1.getSekatanKepentingan());
                        mohonHakmilikBaru.setSyaratNyata(hakmilikPermohonan1.getSyaratNyata());
//                        mohonHakmilikBaru.setKodKegunaanTanah(hakmilikPermohonan1.getHakmilik().getKegunaanTanah());
                        //mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getHakmilik().getLuas());
                        mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getLuasTerlibat());
                        mohonHakmilikBaru.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                        mohonHakmilikBaru.setLot(hakmilikPermohonan1.getLot());
                        mohonHakmilikBaru.setNoLot(hakmilikPermohonan1.getNoLot());
                        mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilikPermohonan1.getHakmilik().getKategoriTanah());
                        //mohonHakmilikBaru.setKodKegunaanTanah(hakmilikPermohonan1.getHakmilik().getKegunaanTanah());
                        mohonHakmilikBaru.setSekatanKepentingan(hakmilikPermohonan1.getSekatanKepentingan());
                        mohonHakmilikBaru.setSyaratNyata(hakmilikPermohonan1.getSyaratNyata());
                        //mohonHakmilikBaru.setKodKegunaanTanah(hakmilikPermohonan1.getHakmilik().getKegunaanTanah());
                        //mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getHakmilik().getLuas());
                        mohonHakmilikBaru.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                        mohonHakmilikBaru.setLot(hakmilikPermohonan1.getLot());
                        mohonHakmilikBaru.setNoLot(hakmilikPermohonan1.getNoLot());
                        mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilikPermohonan1.getHakmilik().getKategoriTanah());
                    }

                    HakmilikLama = hp2.get(0).getHakmilik().getIdHakmilik();

                    List<HakmilikUrusan> senaraiHakmilikurusan1 = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikOnly(HakmilikLama);
                    for (HakmilikUrusan hu : senaraiHakmilikurusan1) {
//                     for (HakmilikUrusan hmu : hakmilikUrusan) {
                        String idPermohonanUrusan = hu.getIdPerserahan();
                        String idHakmilikHU = hu.getHakmilik().getIdHakmilik();
                        if (idPermohonanUrusan != null) {
                            List<HakmilikPermohonan> senaraiHP1 = regService.searchMohonHakmilikByIdPermohonan(idPermohonanUrusan, idHakmilikHU);
                            for (HakmilikPermohonan hakmilikPermohonan5 : senaraiHP1) {
                                LOG.info("IDpERMOHONAN " + hakmilikPermohonan5.getPermohonan().getIdPermohonan());
                                LOG.info("IDHAKMILIK " + hakmilikPermohonan5.getHakmilik().getIdHakmilik());
                                HakmilikPermohonan _hakmilikPermohonan1 = new HakmilikPermohonan();
                                PermohonanPihak _pp = new PermohonanPihak();
                                _hakmilikPermohonan1.setSekatanKepentingan(hakmilikPermohonan5.getSekatanKepentingan());
                                _hakmilikPermohonan1.setSyaratNyata(hakmilikPermohonan5.getSyaratNyata());
                                hakmilikPermohonan5.setKodKegunaanTanah(hakmilikPermohonan5.getHakmilik().getKegunaanTanah());
                                if (hakmilikPermohonan5.getPermohonan().getKodUrusan().getKod().equals("SBSTL")
                                        || hakmilikPermohonan5.getPermohonan().getKodUrusan().getKod().equals("ABT-K")
                                        || hakmilikPermohonan5.getPermohonan().getKodUrusan().getKod().equals("ABTKB")
                                        || hakmilikPermohonan5.getPermohonan().getKodUrusan().getKod().equals("ABTBH")) {
                                    _hakmilikPermohonan1.setLuasTerlibat(_hakmilikPermohonan1.getLuasTerlibat());
                                } else {
                                    _hakmilikPermohonan1.setLuasTerlibat(hakmilikPermohonan5.getLuasTerlibat());
                                }

                                _hakmilikPermohonan1.setKodUnitLuas(hakmilikPermohonan5.getKodUnitLuas());
                                _hakmilikPermohonan1.setLot(hakmilikPermohonan5.getLot());
                                _hakmilikPermohonan1.setNoLot(hakmilikPermohonan5.getNoLot());
                                _hakmilikPermohonan1.setJenisPenggunaanTanah(hakmilikPermohonan5.getHakmilik().getKategoriTanah());
                                _hakmilikPermohonan1.setKodKegunaanTanah(hakmilikPermohonan5.getHakmilik().getKegunaanTanah());
                                _hakmilikPermohonan1.setSekatanKepentingan(hakmilikPermohonan5.getSekatanKepentingan());
                                _hakmilikPermohonan1.setSyaratNyata(hakmilikPermohonan5.getSyaratNyata());
                                _hakmilikPermohonan1.setKodKegunaanTanah(hakmilikPermohonan5.getHakmilik().getKegunaanTanah());
                                _hakmilikPermohonan1.setLuasTerlibat(hakmilikPermohonan5.getLuasTerlibat());
                                _hakmilikPermohonan1.setKodUnitLuas(hakmilikPermohonan5.getKodUnitLuas());
                                _hakmilikPermohonan1.setLot(hakmilikPermohonan5.getLot());
                                _hakmilikPermohonan1.setNoLot(hakmilikPermohonan5.getNoLot());
                                _hakmilikPermohonan1.setJenisPenggunaanTanah(hakmilikPermohonan5.getHakmilik().getKategoriTanah());
                                _hakmilikPermohonan1.setHakmilik(hakmilikBaru);
                                _hakmilikPermohonan1.setPermohonan(hakmilikPermohonan5.getPermohonan());

                                _hakmilikPermohonan1.setInfoAudit(ia);
                                hakmilikPermohonanDAO.save(_hakmilikPermohonan1);
                                LOG.info("PRIMARY KEY" + _hakmilikPermohonan1.getId());
//                                mohonHakmilikBaru.setInfoAudit(ia);
                            }
                        }
                    }

                    //LOG.debug("temphp size : "+temphp.size());
                    //LOG.debug("LUAS TERLIBAT : "+temphp.get(0).getLuasTerlibat());
                    //if (temphp.size() > 0) {
                    //     mohonHakmilikBaru.setLuasTerlibat(temphp.get(0).getLuasTerlibat());
                    // }
                    mohonHakmilikBaru.setInfoAudit(ia);
                    LOG.info("hakmilikBaru2 = " + hakmilikBaru.getIdHakmilik());
                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    Hakmilik hm = hp.getHakmilik();
                    hm = hakmilikDAO.findById(id);

//                    if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
                    LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
                    LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                    LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

                    if (hm.getSenaraiHakmilikAsal().size() > 0) {
                        List<HakmilikAsal> senaraiHA = hm.getSenaraiHakmilikAsal();
                        for (HakmilikAsal hakmilikAsasl : senaraiHA) {
                            LOG.debug("start copy hakmilik asal hakmilik lama ke hakmilik asal hakmilik baru");
                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hap.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                            hap.setHakmilik(hakmilikBaru);
                            hap.setHakmilikSejarah(hakmilikAsasl.getHakmilikAsal());
                            hap.setInfoAudit(ia);
                            senaraiAsal.add(hap);
                        }
                    }
                    if (hm.getSenaraiHakmilikSebelum().size() > 0) {
                        List<HakmilikSebelum> senaraiHS = hm.getSenaraiHakmilikSebelum();
                        for (HakmilikSebelum hakmilikSblm : senaraiHS) {
                            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                            hsp.setPermohonan(p);
                            hsp.setCawangan(pengguna.getKodCawangan());
                            hsp.setHakmilik(hakmilikBaru);
                            hsp.setHakmilikSejarah(hakmilikSblm.getHakmilikSebelum());
                            hsp.setInfoAudit(ia);
                            senaraiSblm.add(hsp);
                        }
                    }

                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                    if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                        hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                    }
                    hsp.setPermohonan(p);
                    hsp.setCawangan(pengguna.getKodCawangan());
//          hsp.setHakmilik(hm);
                    hsp.setHakmilik(hakmilikBaru);
                    hsp.setHakmilikSejarah(hm.getIdHakmilik());
                    hsp.setInfoAudit(ia);
                    senaraiSblm2.add(hsp);
//          hakmilikSblmPermohonanDAO.save(hsp);
                    if (debug) {
                        LOG.debug("start to create hakmilik pihak");
                    }
//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();

                    List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                        if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                            continue;
                        }
                        LOG.info("  /* CREATE HAKMILK URUSAN FOR NEW HAKMILIK */  ");
//check sini
                        if (!p.getKodUrusan().getKod().equals("HKSB") && !p.getKodUrusan().getKod().equals("HSSB") && !p.getKodUrusan().getKod().equals("HKGHS")
                                && !p.getKodUrusan().getKod().equals("HKABS") && !p.getKodUrusan().getKod().equals("HKABT") && !p.getKodUrusan().getKod().equals("HKABS")
                                && !p.getKodUrusan().getKod().equals("HKSTK")) {
                            LOG.info("hakmilik terpilih =: " + hakmilikTerpilih.getIdHakmilik());
                            List<HakmilikUrusan> hakmilikUrusan = regService.searchHakmilikUrusan(hakmilikTerpilih.getIdHakmilik());
                        }
                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        if (hu.getTarikhDaftar() != null) {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        } else if (hu.getInfoAudit() != null) { //yus add 20/02/2019. data hu sptb ada yang takde tarikh daftar n tarikh masuk.
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getInfoAudit().getTarikhMasuk());
                        }
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setNoFail(hu.getNoFail());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                        hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        hakmilikUrusanBaru.setItem(hu.getItem());
                        hakmilikUrusanBaru.setInfoAudit(ia);
                        hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);
                    }

                    List<HakmilikUrusan> senaraiHakmilikurusanGD = hakmilikUrusanService.findHakmilikUrusanGDActiveByIdHakmilik(hm.getIdHakmilik());
                    if (senaraiHakmilikurusanGD.size() > 0) {
                        Long hakmilikUrusanGD = senaraiHakmilikurusanGD.get(0).getIdUrusan();
                        String IdMhnUrusanGD = senaraiHakmilikurusanGD.get(0).getIdPerserahan();
                        HakmilikUrusanBaruGD = hakmilikUrusanService.findByIdPerserahanIdHakmilik(IdMhnUrusanGD, hakmilikBaru.getIdHakmilik());
                    }

                    List<HakmilikUrusan> senaraiHakmilikUrusanPMT = hakmilikUrusanService.findHakmilikUrusanPMTActiveByIdHakmilik(hm.getIdHakmilik());
                    if (senaraiHakmilikUrusanPMT.size() > 0) {
                        Long hakmilikUrusanPMT = senaraiHakmilikUrusanPMT.get(0).getIdUrusan();
                        String IdMhnUrusanPMT = senaraiHakmilikUrusanPMT.get(0).getIdPerserahan();
                        HakmilikUrusanBaruPMT = hakmilikUrusanService.findByIdPerserahanIdHakmilik(IdMhnUrusanPMT, hakmilikBaru.getIdHakmilik());
                    }
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        LOG.info("HakmilikPihakBerkepentingan = " + senaraiHakmilikPihak.size());
                        LOG.info("hpk.getpihak.getnama = " + hpk.getNama());
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        }
                        //sini

                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilikBaru);
                        hpb.setCawangan(hakmilikBaru.getCawangan());
                        hpb.setPihakCawangan(hpk.getPihakCawangan());
                        hpb.setJenis(hpk.getJenis());
                        hpb.setSyerPembilang(hpk.getSyerPembilang());
                        hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                        LOG.info("JENIS  = " + hpb.getJenis().getKod());
//                        if (HakmilikUrusanBaruGD != null) {
//                            if (hpb.getJenis().getKod().equals("PG")) {
//                                hpb.setPerserahan(HakmilikUrusanBaruGD);
//                            }
//                        }
//                        if (HakmilikUrusanBaruPMT != null) {
//                            if (hpb.getJenis().getKod().equals("PM")) {
//                                hpb.setPerserahan(HakmilikUrusanBaruPMT);
//                            }
//                        }
                        LOG.info("hpk.getpihak.getnama = " + hpb.getHakmilik().getIdHakmilik());
                        LOG.info("hpk.getpihak.getnama = " + hpb.getHakmilik().getIdHakmilik());
//                        LOG.info("hpk.getpihak.getnama = " + hpb.getPihak().getIdPihak());
                        if (HakmilikUrusanBaruGD != null || HakmilikUrusanBaruPMT != null) {
                            LOG.info("JENIS2  = " + hpb.getJenis().getKod());
                            LOG.info("JENIS3  = " + hpk.getJenis().getKod());
                            if (hpb.getJenis().getKod().equals("PG")) {
//                                LOG.info("JENIS3  = " + HakmilikUrusanBaruGD.getIdPerserahan());
                                hpb.setPerserahan(HakmilikUrusanBaruGD);
                            } else if (hpb.getJenis().getKod().equals("PM")) {
//                                LOG.info("JENIS3  = " + HakmilikUrusanBaruPMT.getIdPerserahan());
                                hpb.setPerserahan(HakmilikUrusanBaruPMT);
                            }
                        }
//                            hpb.setPerserahan(hakmilikUrusanGD);
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
                        hpb.setNama(hpk.getNama());
                        hpb.setAlamat1(hpk.getAlamat1());
                        hpb.setAlamat2(hpk.getAlamat2());
                        hpb.setAlamat3(hpk.getAlamat3());
                        hpb.setAlamat4(hpk.getAlamat4());
                        hpb.setAlamatSurat(hpk.getAlamatSurat());
                        hpb.setPoskod(hpk.getPoskod());
                        hpb.setNegeri(hpk.getNegeri());
                        hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                        if (hpk.getJenisPengenalan() != null) {
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                        }
                        if (hpk.getPenubuhanSyarikat() != null) {
                            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
                        }
                        if (hpk.getSyerPenyebut() != null) {
                            hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                        }
                        if (hpk.getSyerPembilang() != null) {
                            hpb.setJumlahPembilang(hpk.getSyerPembilang());
                        }
                        hpb.setWargaNegara(hpk.getWargaNegara());
                        hpb.setInfoAudit(ia);

                        if (hpk.getNoPengenalanLama() != null) {

                            if (hpk.getNoPengenalanLama() != null) {

                                hpb.setNoPengenalanLama(hpk.getNoPengenalanLama());
                            }
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
//                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
//                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                            hpb.setJumlahPembilang(hpk.getSyerPembilang());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setInfoAudit(ia);
//                            
                        } else if (hpk.getPerserahan() == null) {
//                            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                            hpb.setHakmilik(hakmilikBaru);
                            hpb.setCawangan(hakmilikBaru.getCawangan());
                            hpb.setPihakCawangan(hpk.getPihakCawangan());
                            hpb.setJenis(hpk.getJenis());
                            hpb.setSyerPembilang(hpk.getSyerPembilang());
                            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
//                            hpb.setPerserahan(h1);
                            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                            hpb.setKaveatAktif(hpk.getKaveatAktif());
                            hpb.setAktif(hpk.getAktif());
                            hpb.setPihak(hpk.getPihak());
                            hpb.setNama(hpk.getNama());
                            hpb.setAlamat1(hpk.getAlamat1());
                            hpb.setAlamat2(hpk.getAlamat2());
                            hpb.setAlamat3(hpk.getAlamat3());
                            hpb.setAlamat4(hpk.getAlamat4());
                            hpb.setAlamatSurat(hpk.getAlamatSurat());
                            hpb.setPoskod(hpk.getPoskod());
                            hpb.setNegeri(hpk.getNegeri());
                            hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                            hpb.setNoPengenalan(hpk.getNoPengenalan());
                            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
//                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
//                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                            hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                            hpb.setJumlahPembilang(hpk.getSyerPembilang());
                            hpb.setWargaNegara(hpk.getWargaNegara());
                            hpb.setInfoAudit(ia);
                        }
                        hpb = hpkService.save(hpb);
                        hwList = pService.findHakmilikWarisByPihakKepentingan(hpk.getIdHakmilikPihakBerkepentingan());

                        for (HakmilikWaris listHw : hwList) {
                            HakmilikWaris hw = new HakmilikWaris();
                            hw.setInfoAudit(ia);
                            hw.setJenisPengenalan(listHw.getJenisPengenalan());
                            hw.setPemegangAmanah(hpb);
                            hw.setNama(listHw.getNama());
                            hw.setNoPengenalan(listHw.getNoPengenalan());
                            hw.setSyerPembilang(listHw.getSyerPembilang());
                            hw.setSyerPenyebut(listHw.getSyerPenyebut());
                            hw.setWargaNegara(listHw.getWargaNegara());
                            hw.setNegeri(listHw.getNegeri());
                            hw.setCawangan(listHw.getCawangan());
                            hakmilikWarisDAO.save(hw);
                        }

                        if (debug) {
                            LOG.debug("start to create hakmilik urusan");
                        }
                    }
                }
            }

            mohonHakmilik = regService.searchMohonHakmilikByIdMohon(p.getIdPermohonan());
            for (HakmilikPermohonan hp : mohonHakmilik) {
                pemohon = pemohonService.findPemohonByHakmilik(HakmilikLama);
                for (Pemohon pmh : pemohon) {
                    Pemohon _pmh = new Pemohon();
                    _pmh.setPermohonan(pmh.getPermohonan());
                    _pmh.setHakmilik(hp.getHakmilik());
                    _pmh.setAlamat(pmh.getAlamat());
                    _pmh.setAlamatSurat(pmh.getAlamatSurat());
                    if (pmh.getCawangan() != null) {
                        _pmh.setCawangan(pmh.getCawangan());
                    } else if (pmh.getCawangan() != null) {
                        pmh.setCawangan(p.getCawangan());
                    }
                    _pmh.setDalamanNilai1(pmh.getDalamanNilai1());
                    _pmh.setDalamanNilai2(pmh.getDalamanNilai2());
                    _pmh.setJenis(pmh.getJenis());
                    _pmh.setJenisPemohon(pmh.getJenisPemohon());
                    _pmh.setJenisPengenalan(pmh.getJenisPengenalan());
                    _pmh.setJumlahPembilang(pmh.getJumlahPembilang());
                    _pmh.setJumlahPenyebut(pmh.getJumlahPenyebut());
                    _pmh.setNama(pmh.getNama());
                    _pmh.setNoPengenalan(pmh.getNoPengenalan());
                    _pmh.setPekerjaan(pmh.getPekerjaan());
                    _pmh.setPendapatan(pmh.getPendapatan());
                    _pmh.setPihak(pmh.getPihak());
                    _pmh.setPihakCawangan(pmh.getPihakCawangan());
                    _pmh.setSyerPembilang(pmh.getSyerPembilang());
                    _pmh.setSyerPenyebut(pmh.getSyerPenyebut());
                    _pmh.setWargaNegara(pmh.getWargaNegara());
                    _pmh.setInfoAudit(ia);
                    pemohonService.saveOrUpdate(_pmh);
                }
            }
            mohonHakmilik = regService.searchMohonHakmilikByIdMohon(p.getIdPermohonan());
            for (HakmilikPermohonan hp : mohonHakmilik) {
                mohonPihak = regService.findMohonPihakByHakmilik(HakmilikLama);
                for (PermohonanPihak pp : mohonPihak) {
                    PermohonanPihak _pp = new PermohonanPihak();
                    _pp.setAlamat(pp.getAlamat());
                    _pp.setAlamatSurat(pp.getAlamatSurat());
                    if (pp.getCawangan() != null) {
                        _pp.setCawangan(pp.getCawangan());
                    } else if (pp.getCawangan() != null) {
                        _pp.setCawangan(p.getCawangan());
                    }
                    _pp.setHakmilik(hp.getHakmilik());
                    _pp.setInfoAudit(ia);
                    _pp.setJenis(pp.getJenis());
                    _pp.setJenisPengenalan(pp.getJenisPengenalan());
                    _pp.setJumlahPembilang(pp.getJumlahPembilang());
                    _pp.setJumlahPenyebut(pp.getJumlahPenyebut());
                    _pp.setNama(pp.getNama());
                    _pp.setNoPengenalan(pp.getNoPengenalan());
                    _pp.setNoRujukan(pp.getNoRujukan());
                    _pp.setPermohonan(pp.getPermohonan());
                    _pp.setPihak(pp.getPihak());
                    _pp.setPihakCawangan(pp.getPihakCawangan());
                    _pp.setSyerBersama(pp.getSyerBersama());
                    if (pp.getSyerPembilang() != null) {
                        _pp.setSyerPembilang(pp.getSyerPembilang());
                    }

                    if (pp.getSyerPenyebut() != null) {
                        _pp.setSyerPenyebut(pp.getSyerPenyebut());
                    }
                    _pp.setWargaNegara(pp.getWargaNegara());
                    permohonanPihakService.saveOrUpdate(_pp);
                }
            }
            if (senaraiAsal.size()
                    > 0) {
                for (HakmilikAsalPermohonan asal : senaraiAsal) {
                    hakmilikAsalPermohonanDAO.save(asal);
                }
                for (HakmilikSebelumPermohonan sblm : senaraiSblm) {
                    hakmilikSblmPermohonanDAO.save(sblm);
                }

                for (HakmilikSebelumPermohonan sblm2 : senaraiSblm2) {
                    hakmilikSblmPermohonanDAO.save(sblm2);
                }
            } else if (!p.getKodUrusan().getKod().equals("HSSTB")) {
                for (HakmilikSebelumPermohonan sblm2 : senaraiSblm2) {
                    HakmilikAsalPermohonan asal = new HakmilikAsalPermohonan();
                    if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                        asal.setHakmilikPermohonan(sblm2.getHakmilikPermohonan());
                    }
                    asal.setHakmilik(sblm2.getHakmilik());
                    asal.setHakmilikSejarah(sblm2.getHakmilikSejarah());
                    asal.setInfoAudit(sblm2.getInfoAudit());
                    hakmilikAsalPermohonanDAO.save(asal);
                }
            }
        }
    }

    private void janaHakmilikBaruFromHakmilikDaerah(List<HakmilikPermohonan> hakmilikPermohonan,
            Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, String kodBPM) {
        LOG.info("  /* JANA HAKMILIK BARU FROM HAKMILIK TERLIBAT 1*/  ");
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru2 = null;
        PermohonanRujukanLuar mohonRujLuarBaru = null;
//    List<HakmilikPermohonan> hakmilikPermohonan = new ArrayList<HakmilikPermohonan> (); 
//    int totalHakmilik = 0;

//    KodCawangan kodCaw = null;
//    
//    if (permohonan != null) {
//        
//        if(permohonan.getCatatan() != null)
//        {
//            totalHakmilik = Integer.parseInt(permohonan.getCatatan());
//        }
//        
//        else{
//            totalHakmilik = 1;
//        }
//        
//        
//        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
//        
//        for (HakmilikPermohonan hp : senaraiHakmilik) {
//            if (hp != null && hp.getHakmilik() != null 
//                    && hp.getHakmilik().getIdHakmilik().contains("IR")){
//                hakmilikPermohonan.add(hp);
//                if (kodCaw == null) kodCaw = hp.getHakmilik().getCawangan();
//            }
//        }        
//    }
        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {

                String kodNegeri = conf.getProperty("kodNegeri");

                for (int i = 0; i < totalHakmilik; i++) {
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    hakmilikTerpilih = hakmilikDAO.findById(id); // old hakmilik

                    LOG.info(" /* SAVE NEW HAKMILIK in TABLE HAKMILIK */ ");
                    hakmilikBaru = new Hakmilik();
                    KodDaerah kd = new KodDaerah();
                    kd.setKod(kodJenisDaerah);
                    hakmilikBaru.setDaerah(kd);
                    if (hakmilik.getPermohonan() != null) {
                        hakmilikBaru.setPermohonan(hakmilik.getPermohonan());
                    }

                    if (hakmilikTerpilih.getPegangan() == 'S') {
                        hakmilikBaru.setKodHakmilik(kodHakmilikDAO.findById("GM"));
                    } else {
                        hakmilikBaru.setKodHakmilik(kodHakmilikDAO.findById("PM"));
                    }

                    hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());

                    LOG.info("--> kod pekan : " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                    LOG.debug("hakmilik baru~~~~~" + hakmilikBaru);
                    /* GENERATE HAKMILIK BARU HERE */
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    KodCawangan kc = new KodCawangan();
                    kc.setKod(kodJenisDaerah);
                    hakmilikBaru.setCawangan(kc);
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
                    hakmilikBaru.setNoVersiDhde(0); // set DHDE version to zero will plus 1 after daftar urusan
                    hakmilikBaru.setNoVersiDhke(0); // set DHKE version to zero will plus 1 after daftar urusan
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                    hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                    hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                    if (hakmilikTerpilih.getCukaiSebenar() != null) {
                        hakmilikBaru.setCukaiSebenar(hakmilikTerpilih.getCukaiSebenar());
                    }
                    hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                    hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());

                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());

                    LOG.info("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                    if (hakmilikTerpilih.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                    }

                    hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());
                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());
                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());
                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                    hakmilikBaru.setInfoAudit(ia);
                    /* To do start*/
                    if (p.getKodUrusan().getKod().equals("HTIR")) {
                        LOG.debug("copy mohon rujuk luar n mohon hakmilik from hakmilik lama");
                        List<HakmilikPermohonan> senaraiHP = regService.searchMohonHakmilik(hakmilikTerpilih.getIdHakmilik());
                        List<PermohonanRujukanLuar> senaraiMrl = regService.searchMohonRujLuarByIdHakmilik(hakmilikTerpilih.getIdHakmilik());
                        LOG.debug("size senarai hp~~~" + senaraiHP.size());
                        LOG.debug("size senarai Mrl~~~" + senaraiMrl.size());
                        //get mohon hakmilik from hakmilik lama
                        for (HakmilikPermohonan hakmilikPermohonan1 : senaraiHP) {
                            mohonHakmilikBaru2 = new HakmilikPermohonan();
                            mohonHakmilikBaru2.setHakmilik(hakmilikBaru);
                            mohonHakmilikBaru2.setPermohonan(hakmilikPermohonan1.getPermohonan());
                            mohonHakmilikBaru2.setLuasTerlibat(hakmilikPermohonan1.getLuasTerlibat());
                            mohonHakmilikBaru2.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                            mohonHakmilikBaru2.setCukaiBaru(hakmilikPermohonan1.getCukaiBaru());
                            mohonHakmilikBaru2.setInfoAudit(ia);
                            hakmilikPermohonanDAO.save(mohonHakmilikBaru2);
                        }
                        //get mohon rujuk luar from hakmilik lama
                        for (PermohonanRujukanLuar mohonRujLuar : senaraiMrl) {
                            mohonRujLuarBaru = new PermohonanRujukanLuar();
                            mohonRujLuarBaru.setHakmilik(hakmilikBaru);
                            mohonRujLuarBaru.setPermohonan(mohonRujLuar.getPermohonan());
                            mohonRujLuarBaru.setCatatan(mohonRujLuar.getCatatan());
                            mohonRujLuarBaru.setCawangan(mohonRujLuar.getCawangan());
                            if (mohonRujLuar.getKodRujukan() != null) {
                                mohonRujLuarBaru.setKodRujukan(mohonRujLuar.getKodRujukan());
                            }
                            mohonRujLuarBaru.setNoFail(mohonRujLuar.getNoFail());
                            mohonRujLuarBaru.setNoRujukan(mohonRujLuar.getNoRujukan());
                            mohonRujLuarBaru.setNoSidang(mohonRujLuar.getNoSidang());
                            mohonRujLuarBaru.setNamaSidang(mohonRujLuar.getNamaSidang());
                            mohonRujLuarBaru.setTarikhSidang(mohonRujLuar.getTarikhSidang());
                            mohonRujLuarBaru.setTarikhKuatkuasa(mohonRujLuar.getTarikhKuatkuasa());
                            if (mohonRujLuar.getTempohBulan() != null) {
                                mohonRujLuarBaru.setTempohBulan(mohonRujLuar.getTempohBulan());
                            } else {
                                mohonRujLuarBaru.setTempohBulan(0);
                            }
                            if (mohonRujLuar.getTempohHari() != null) {
                                mohonRujLuarBaru.setTempohHari(mohonRujLuar.getTempohHari());
                            } else {
                                mohonRujLuarBaru.setTempohHari(0);
                            }
                            if (mohonRujLuar.getTempohTahun() != null) {
                                mohonRujLuarBaru.setTempohTahun(mohonRujLuar.getTempohTahun());
                            } else {
                                mohonRujLuarBaru.setTempohTahun(0);
                            }
                            mohonRujLuarBaru.setTarikhRujukan(mohonRujLuar.getTarikhRujukan());
                            mohonRujLuarBaru.setItem(mohonRujLuar.getItem());
                            mohonRujLuarBaru.setInfoAudit(ia);
                            permohonanRujukanLuarDAO.saveOrUpdate(mohonRujLuarBaru);
                        }
                    }
                    /* end */
                    /* CREATE NEW MOHON_HAKMILIK  */
                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);

                    mohonHakmilikBaru.setInfoAudit(ia);
                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    Hakmilik hm = hp.getHakmilik();
                    hm = hakmilikDAO.findById(id);

                    LOG.info("/* Start Copy Hakmilik Asal Untuk Urusan Selain HSSTB /HKSTB 1 */ ");
                    LOG.info("--> size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                    LOG.info("--> size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

                    List<HakmilikAsal> hmAsal = regService.cariHakmilikAsalDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_asal bagi hakmilik lama
                    List<HakmilikSebelum> hmSblm = regService.cariHakmilikSebelumDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_Sblm bagi hakmilik lama

                    /* REFER from Pn Safina(SME): check new hakmilik in hakmilik_asal. 
                     * If there's no record, insert table mohon_hakmilik_asal
                     * if there's record, insert table mohon_hakmilik_sebelum
                     */
                    if (hmAsal.isEmpty()) {
                        HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                        if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                            hma.setHakmilikPermohonan(mohonHakmilikBaru);
                        }
                        hma.setHakmilik(hakmilikBaru);
                        hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                        hma.setInfoAudit(ia);
                        hakmilikAsalPermohonanDAO.save(hma);
                    } else {

                        for (HakmilikAsal asal : hmAsal) {

                            // save in table mohon_hakmilik_asal
                            HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hma.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                            hma.setHakmilik(hakmilikBaru);
                            //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilikAsal()).getIdHakmilik());
                            //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilik().getIdHakmilik()).getIdHakmilik());
                            hma.setHakmilikSejarah(asal.getHakmilikAsal());//get hakmilik asal from hakmilik lama
                            hma.setInfoAudit(ia);
                            hakmilikAsalPermohonanDAO.save(hma);
                        }
                        if (!hmSblm.isEmpty()) {

                            for (HakmilikSebelum sblm : hmSblm) {
                                /* //save in table hakmilik_sblm
                                 HakmilikSebelum hsblm = new HakmilikSebelum();
                                 hsblm.setHakmilik(mohonHakmilikBaru.getHakmilik()); 
                                 hsblm.setHakmilikSebelum(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum().getIdHakmilik())); 
                                 hsblm.setInfoAudit(ia);
                                 regService.simpanHakmilikSebelum(hsblm);*/

                                // save in table mohon_hakmilik_sblm
                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                hsp.setPermohonan(p);
                                hsp.setCawangan(pengguna.getKodCawangan());
                                hsp.setHakmilik(hakmilikBaru);
                                hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum()).getIdHakmilik());
                                hsp.setInfoAudit(ia);
                                hakmilikSblmPermohonanDAO.save(hsp);
                            }
                        }

                        HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                        hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                        hsp.setPermohonan(p);
                        hsp.setCawangan(pengguna.getKodCawangan());
                        hsp.setHakmilik(hakmilikBaru);
                        hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                        hsp.setInfoAudit(ia);
                        hakmilikSblmPermohonanDAO.save(hsp);
                    }
//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        }
                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilikBaru);
                        hpb.setCawangan(hakmilikBaru.getCawangan());
                        hpb.setPihakCawangan(hpk.getPihakCawangan());
                        hpb.setJenis(hpk.getJenis());
                        hpb.setSyerPembilang(hpk.getSyerPembilang());
                        hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                        hpb.setPerserahan(hpk.getPerserahan());
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
                        hpb.setNama(hpk.getNama());
                        hpb.setAlamat1(hpk.getAlamat1());
                        hpb.setAlamat2(hpk.getAlamat2());
                        hpb.setAlamat3(hpk.getAlamat3());
                        hpb.setAlamat4(hpk.getAlamat4());
                        hpb.setAlamatSurat(hpk.getAlamatSurat());
                        hpb.setPoskod(hpk.getPoskod());
                        hpb.setNegeri(hpk.getNegeri());
                        hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                        hpb.setInfoAudit(ia);
                        hpkService.save(hpb);
                    }

                    List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                        if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                            continue;
                        }

                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        if (hu.getTarikhDaftar() != null) {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        } else {
                            hakmilikUrusanBaru.setTarikhDaftar(new java.util.Date());
                        }
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                        hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        hakmilikUrusanBaru.setItem(hu.getItem());
                        hakmilikUrusanBaru.setInfoAudit(ia);
                        hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                        Permohonan p2 = permohonanDAO.findById(hu.getIdPerserahan());
                        if (p2 != null) {
                            List<Pemohon> senaraiPemohon = p2.getSenaraiPemohon();
                            for (Pemohon pmh : senaraiPemohon) {
                                Pemohon _pmh = new Pemohon();
                                _pmh.setPermohonan(p2);
                                _pmh.setHakmilik(hakmilikBaru);
                                _pmh.setAlamat(pmh.getAlamat());
                                _pmh.setAlamatSurat(pmh.getAlamatSurat());
                                _pmh.setCawangan(pmh.getCawangan());
                                _pmh.setDalamanNilai1(pmh.getDalamanNilai1());
                                _pmh.setDalamanNilai2(pmh.getDalamanNilai2());
                                _pmh.setJenis(pmh.getJenis());
                                _pmh.setJenisPemohon(pmh.getJenisPemohon());
                                _pmh.setJenisPengenalan(pmh.getJenisPengenalan());
                                _pmh.setJumlahPembilang(pmh.getJumlahPembilang());
                                _pmh.setJumlahPenyebut(pmh.getJumlahPenyebut());
                                _pmh.setNama(pmh.getNama());
                                _pmh.setNoPengenalan(pmh.getNoPengenalan());
                                _pmh.setPekerjaan(pmh.getPekerjaan());
                                _pmh.setPendapatan(pmh.getPendapatan());
                                _pmh.setPihak(pmh.getPihak());
                                _pmh.setPihakCawangan(pmh.getPihakCawangan());
                                _pmh.setSyerPembilang(pmh.getSyerPembilang());
                                _pmh.setSyerPenyebut(pmh.getSyerPenyebut());
                                _pmh.setWargaNegara(pmh.getWargaNegara());
                                _pmh.setInfoAudit(ia);
                                pemohonService.saveOrUpdate(_pmh);
                            }

                            List<PermohonanPihak> senaraiPermohonanPihak = p2.getSenaraiPihak();
                            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                                PermohonanPihak _pp = new PermohonanPihak();
                                _pp.setAlamat(pp.getAlamat());
                                _pp.setAlamatSurat(pp.getAlamatSurat());
                                _pp.setCawangan(pp.getCawangan());
                                _pp.setHakmilik(hakmilikBaru);
                                _pp.setInfoAudit(ia);
                                _pp.setJenis(pp.getJenis());
                                _pp.setJenisPengenalan(pp.getJenisPengenalan());
                                _pp.setJumlahPembilang(pp.getJumlahPembilang());
                                _pp.setJumlahPenyebut(pp.getJumlahPenyebut());
                                _pp.setNama(pp.getNama());
                                _pp.setNoPengenalan(pp.getNoPengenalan());
                                _pp.setNoRujukan(pp.getNoRujukan());
                                _pp.setPermohonan(pp.getPermohonan());
                                _pp.setPihak(pp.getPihak());
                                _pp.setPihakCawangan(pp.getPihakCawangan());
                                _pp.setSyerBersama(pp.getSyerBersama());
                                _pp.setSyerPembilang(pp.getSyerPembilang());
                                _pp.setSyerPenyebut(pp.getSyerPenyebut());
                                _pp.setWargaNegara(pp.getWargaNegara());
                                permohonanPihakService.saveOrUpdate(_pp);
                            }
                        }

                    }
                }

                try {

                    WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                            p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                            p.getKodUrusan().getNama());
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }

            }
        }

    }
    // add by azri 1/8/2013 

    private void janaHakmilikBaruFromHakmilikTerlibatV2(List<HakmilikPermohonan> hakmilikPermohonan,
            Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, String kodBPM) {
        LOG.info("  /* JANA HAKMILIK BARU FROM HAKMILIK TERLIBAT 2*/  ");
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
//    long t = System.currentTimeMillis();    
//    List<HakmilikPermohonan> hp2 = hakmilikPermohonan;
        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {

                for (int i = 0; i < totalHakmilik; i++) {
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    hakmilikTerpilih = hakmilikDAO.findById(id); // old hakmilik

                    LOG.info(" /* SAVE NEW HAKMILIK in TABLE HAKMILIK */ ");
                    hakmilikBaru = new Hakmilik();
                    hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
                    if (hakmilik.getPermohonan() != null) {
                        hakmilikBaru.setPermohonan(hakmilik.getPermohonan());
                    }
                    if (hakmilik.getKodHakmilik() != null) {
                        hakmilikBaru.setKodHakmilik(hakmilik.getKodHakmilik());
                    } else {
                        hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
                    }
                    if (StringUtils.isNotBlank(kodBPM)) {
                        LOG.info("--> tukar bpm : " + kodBPM);
//            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        KodBandarPekanMukim kbpm = regService.cariBPM(kodBPM, hakmilikTerpilih.getDaerah().getKod());
                        hakmilikBaru.setBandarPekanMukim(kbpm);
                    } else {
                        hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                    }
                    LOG.info("--> kod pekan : " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                    kodNegeri = conf.getProperty("kodNegeri");
                    /* GENERATE HAKMILIK BARU HERE */
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setNoFail(idFail);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
                    hakmilikBaru.setNoVersiDhde(0); // set DHDE version to zero will plus 1 after daftar urusan
                    hakmilikBaru.setNoVersiDhke(0); // set DHKE version to zero will plus 1 after daftar urusan
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    if (p.getKodUrusan().getKod().equals("HKBTK") || p.getKodUrusan().getKod().equals("HKCTK")
                            || p.getKodUrusan().getKod().equals("HKPTK")
                            || p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HSBTK")
                            || p.getKodUrusan().getKod().equals("HSBTK") || p.getKodUrusan().getKod().equals("HSCTK")
                            || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HSTK")
                            || p.getKodUrusan().getKod().equals("HKSHS") || p.getKodUrusan().getKod().equals("HSSHS")
                            || p.getKodUrusan().getKod().equals("HKP") || p.getKodUrusan().getKod().equals("HSP")
                            || p.getKodUrusan().getKod().equals("HSSAA") || p.getKodUrusan().getKod().equals("HTIR")) {
                        hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                        hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                        hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                        if (hakmilikTerpilih.getCukaiSebenar() != null) {
                            hakmilikBaru.setCukaiSebenar(hakmilikTerpilih.getCukaiSebenar());
                        }
                        hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                        hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                        hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                    } else {
                        BigDecimal totalLuas = new BigDecimal(0);
                        hakmilikBaru.setLuas(totalLuas);
                    }
                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());

                    LOG.info("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                    if (hakmilikTerpilih.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() != null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                    }

                    hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());
                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());
                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());
                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                    if (debug) {
                        LOG.debug("start to divide luas");
                    }
                    hakmilikBaru.setInfoAudit(ia);

                    if (debug) {
                        LOG.debug("start to create new Hakmilik Permohonanan.");
                    }

                    /* CREATE NEW MOHON_HAKMILIK  */
                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    /*TODO:SAVE LUAS TERLIBAT FROM MOHON HAKMILIK LAMA UTK HKABS*/
//          List<HakmilikPermohonan> temphp = new ArrayList();
                    if (p.getKodUrusan().getKod().equals("HKSB")
                            || p.getKodUrusan().getKod().equals("HSSB")
                            || p.getKodUrusan().getKod().equals("HKABS")
                            || p.getKodUrusan().getKod().equals("HKABT")) {
                        LOG.debug("SET LUAS TERLIBAT DARI NOTTING HKSB");
                        List<HakmilikPermohonan> senaraiHP = regService.searchMohonHakmilik(hakmilikTerpilih.getIdHakmilik());

                        for (HakmilikPermohonan hakmilikPermohonan1 : senaraiHP) {
                            LOG.debug("LIST URUSAN UNTUK HAKMILIK : " + hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod());
                            if (hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("SBSTL")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABT-K")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTKB")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTBH")) {
                                //temphp.add(hakmilikPermohonan1);
                                mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getLuasTerlibat());
                                mohonHakmilikBaru.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                                mohonHakmilikBaru.setCukaiBaru(hakmilikPermohonan1.getCukaiBaru());
                            }
                        }
                    }

                    mohonHakmilikBaru.setInfoAudit(ia);
                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    Hakmilik hm = hp.getHakmilik();
                    hm = hakmilikDAO.findById(id);

                    LOG.info("/* Start Copy Hakmilik Asal Untuk Urusan Selain HSSTB /HKSTB 2*/ ");
                    LOG.info("--> size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                    LOG.info("--> size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());
                    /* add by azri 1/8/2013 
                     * check id_hakmilik_asal for new hakmilik in table mohon_hakmilik_asal*/
                    LOG.info(" /******************************************** ");
                    LOG.info("--> hakmilik baru : " + hakmilikBaru.getIdHakmilik());
                    LOG.info("--> hakmilik lama : " + hm.getIdHakmilik());
                    LOG.info(" *********************************************/");

//          List<HakmilikAsalPermohonan> checkMohonAsal = regService.searchMohonHakmilikAsalByIDHakmilik(idHakmilikBaru);
//          List<HakmilikAsal> hmAsal = regService.cariHakmilikAsal(idHakmilikBaru); // check senarai hakmilik_asal bagi hakmilik baru
                    List<HakmilikAsal> hmAsal = regService.cariHakmilikAsalDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_asal bagi hakmilik lama
                    List<HakmilikSebelum> hmSblm = regService.cariHakmilikSebelumDariHakmilik(hm.getIdHakmilik()); // check senarai hakmilik_Sblm bagi hakmilik lama
                    LOG.info("--> hakmilik ASAL 2 size() : " + hmAsal.size());
                    /* REFER from Pn Safina(SME): check new hakmilik in hakmilik_asal. 
                     * If there's no record, insert table mohon_hakmilik_asal
                     * if there's record, insert table mohon_hakmilik_sebelum
                     */
                    if (p.getKodUrusan().getKod().equals("HSSTB")) {
                        if (hmAsal.isEmpty()) {
                            LOG.info("/* TABLE HAKMILIK_ASAl IS EMPTY */");
                            LOG.info("/* INSERT TABLE MOHON_HAKMILIK_ASAL */");
                            HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hma.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                            hma.setHakmilik(hakmilikBaru);
                            hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                            hma.setInfoAudit(ia);
                            hakmilikAsalPermohonanDAO.save(hma);
                        } else {
                            LOG.info("/* TABLE_HAKMILIK_ASAL IS NOT EMPTY */");
                            LOG.info("/* INHERIT TABLE HAKMILIK_ASAL */");
                            for (HakmilikAsal asal : hmAsal) {
                                /* // save in table hakmilik asal
                                 HakmilikAsal ha = new HakmilikAsal();
                                 ha.setHakmilik(mohonHakmilikBaru.getHakmilik());
                                 ha.setHakmilikAsal(sejarahHakmilikDAO.findById(asal.getHakmilikAsal().getIdHakmilik()));
                                 ha.setInfoAudit(ia);
                                 regService.simpanHakmilikAsal(ha);*/

                                // save in table mohon_hakmilik_asal
                                HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                                if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                    hma.setHakmilikPermohonan(mohonHakmilikBaru);
                                }
                                hma.setHakmilik(hakmilikBaru);
                                //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilikAsal()).getIdHakmilik());
                                // hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilik().getIdHakmilik()).getIdHakmilik());
                                hma.setHakmilikSejarah(asal.getHakmilikAsal());//get hakmilik asal from hakmilik lama
                                hma.setInfoAudit(ia);
                                hakmilikAsalPermohonanDAO.save(hma);
                            }
                            if (!hmSblm.isEmpty()) {
                                LOG.info("/* INHERIT TABLE HAKMILIK_SEBELUM */");
                                for (HakmilikSebelum sblm : hmSblm) {
                                    /* //save in table hakmilik_sblm
                                     HakmilikSebelum hsblm = new HakmilikSebelum();
                                     hsblm.setHakmilik(mohonHakmilikBaru.getHakmilik()); 
                                     hsblm.setHakmilikSebelum(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum().getIdHakmilik())); 
                                     hsblm.setInfoAudit(ia);
                                     regService.simpanHakmilikSebelum(hsblm);*/

                                    // save in table mohon_hakmilik_sblm
                                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                    if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                        hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                    }
                                    hsp.setPermohonan(p);
                                    hsp.setCawangan(pengguna.getKodCawangan());
                                    hsp.setHakmilik(hakmilikBaru);
                                    hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(sblm.getHakmilikSebelum()).getIdHakmilik());
                                    hsp.setInfoAudit(ia);
                                    hakmilikSblmPermohonanDAO.save(hsp);
                                }
                            }
                            LOG.info("/* INSERT TABLE MOHON_HAKMILIK_SEBELUM */");
                            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                            hsp.setPermohonan(p);
                            hsp.setCawangan(pengguna.getKodCawangan());
                            hsp.setHakmilik(hakmilikBaru);
                            hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                            hsp.setInfoAudit(ia);
                            hakmilikSblmPermohonanDAO.save(hsp);
                        }
                    }
                    if (p.getKodUrusan().getKod().equals("HTIR")) {
                        if (hmAsal.isEmpty()) {
                            LOG.info("/* TABLE HAKMILIK_ASAl IS EMPTY */");
                            LOG.info("/* INSERT TABLE MOHON_HAKMILIK_ASAL */");
                            HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                            hma.setHakmilikPermohonan(mohonHakmilikBaru);
                            hma.setHakmilik(hakmilikBaru);
                            hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                            hma.setInfoAudit(ia);
                            hakmilikAsalPermohonanDAO.save(hma);
                        } else {
                            LOG.info("/* TABLE_HAKMILIK_ASAL IS NOT EMPTY */");
                            LOG.info("/* INHERIT TABLE HAKMILIK_ASAL */");
                            for (HakmilikAsal asal : hmAsal) {
                                // save in table mohon_hakmilik_asal
                                HakmilikAsalPermohonan hma = new HakmilikAsalPermohonan();
                                hma.setHakmilikPermohonan(mohonHakmilikBaru);
                                hma.setHakmilik(hakmilikBaru);
                                //hma.setHakmilikSejarah(sejarahHakmilikDAO.findById(asal.getHakmilikAsal()).getIdHakmilik());
                                hma.setHakmilikSejarah(asal.getHakmilikAsal());
                                hma.setInfoAudit(ia);
                                hakmilikAsalPermohonanDAO.save(hma);
                            }
                            if (!hmSblm.isEmpty()) {
                                LOG.info("/* INHERIT TABLE HAKMILIK_SEBELUM */");
                                for (HakmilikSebelum sblm : hmSblm) {
                                    // save in table mohon_hakmilik_sblm
                                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                    hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                                    hsp.setPermohonan(p);
                                    hsp.setCawangan(pengguna.getKodCawangan());
                                    hsp.setHakmilik(hakmilikBaru);
                                    hsp.setHakmilikSejarah(sblm.getHakmilikSebelum());
                                    hsp.setInfoAudit(ia);
                                    hakmilikSblmPermohonanDAO.save(hsp);
                                }
                            }

                            LOG.info("/* INSERT TABLE MOHON_HAKMILIK_SEBELUM */");
                            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                            hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                            hsp.setPermohonan(p);
                            hsp.setCawangan(pengguna.getKodCawangan());
                            hsp.setHakmilik(hakmilikBaru);
                            hsp.setHakmilikSejarah(hm.getIdHakmilik());
                            hsp.setInfoAudit(ia);
                            hakmilikSblmPermohonanDAO.save(hsp);
                        }
                    }

//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        }
                        LOG.info("  /* INSERT HAKMILK_PIHAK FOR NEW HAKMILIK */  ");
                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilikBaru);
                        hpb.setCawangan(hakmilikBaru.getCawangan());
                        hpb.setPihakCawangan(hpk.getPihakCawangan());
                        hpb.setJenis(hpk.getJenis());
                        hpb.setSyerPembilang(hpk.getSyerPembilang());
                        hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                        hpb.setPerserahan(hpk.getPerserahan());
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
                        hpb.setNama(hpk.getNama());
                        hpb.setAlamat1(hpk.getAlamat1());
                        hpb.setAlamat2(hpk.getAlamat2());
                        hpb.setAlamat3(hpk.getAlamat3());
                        hpb.setAlamat4(hpk.getAlamat4());
                        hpb.setAlamatSurat(hpk.getAlamatSurat());
                        hpb.setPoskod(hpk.getPoskod());
                        hpb.setNegeri(hpk.getNegeri());
                        hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                        hpb.setInfoAudit(ia);
                        hpkService.save(hpb);
                    }

                    List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                        if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                            continue;
                        }
                        LOG.info("  /* CREATE HAKMILK URUSAN FOR NEW HAKMILIK */  ");
                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        if (hu.getTarikhDaftar() != null) {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        } else {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getInfoAudit().getTarikhMasuk());
                        }
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setNoFail(hu.getNoFail());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                        hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        hakmilikUrusanBaru.setItem(hu.getItem());
                        hakmilikUrusanBaru.setInfoAudit(ia);
                        hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                        Permohonan p2 = permohonanDAO.findById(hu.getIdPerserahan());
                        if (p2 != null) {
                            List<Pemohon> senaraiPemohon = p2.getSenaraiPemohon();
                            for (Pemohon pmh : senaraiPemohon) {
                                Pemohon _pmh = new Pemohon();
                                _pmh.setPermohonan(p2);
                                _pmh.setHakmilik(hakmilikBaru);
                                _pmh.setAlamat(pmh.getAlamat());
                                _pmh.setAlamatSurat(pmh.getAlamatSurat());
                                _pmh.setCawangan(pmh.getCawangan());
                                _pmh.setDalamanNilai1(pmh.getDalamanNilai1());
                                _pmh.setDalamanNilai2(pmh.getDalamanNilai2());
                                _pmh.setJenis(pmh.getJenis());
                                _pmh.setJenisPemohon(pmh.getJenisPemohon());
                                _pmh.setJenisPengenalan(pmh.getJenisPengenalan());
                                _pmh.setJumlahPembilang(pmh.getJumlahPembilang());
                                _pmh.setJumlahPenyebut(pmh.getJumlahPenyebut());
                                _pmh.setNama(pmh.getNama());
                                _pmh.setNoPengenalan(pmh.getNoPengenalan());
                                _pmh.setPekerjaan(pmh.getPekerjaan());
                                _pmh.setPendapatan(pmh.getPendapatan());
                                _pmh.setPihak(pmh.getPihak());
                                _pmh.setPihakCawangan(pmh.getPihakCawangan());
                                _pmh.setSyerPembilang(pmh.getSyerPembilang());
                                _pmh.setSyerPenyebut(pmh.getSyerPenyebut());
                                _pmh.setWargaNegara(pmh.getWargaNegara());
                                _pmh.setInfoAudit(ia);
                                pemohonService.saveOrUpdate(_pmh);
                            }

                            List<PermohonanPihak> senaraiPermohonanPihak = p2.getSenaraiPihak();
                            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                                PermohonanPihak _pp = new PermohonanPihak();
                                _pp.setAlamat(pp.getAlamat());
                                _pp.setAlamatSurat(pp.getAlamatSurat());
                                _pp.setCawangan(pp.getCawangan());
                                _pp.setHakmilik(hakmilikBaru);
                                _pp.setInfoAudit(ia);
                                _pp.setJenis(pp.getJenis());
                                _pp.setJenisPengenalan(pp.getJenisPengenalan());
                                _pp.setJumlahPembilang(pp.getJumlahPembilang());
                                _pp.setJumlahPenyebut(pp.getJumlahPenyebut());
                                _pp.setNama(pp.getNama());
                                _pp.setNoPengenalan(pp.getNoPengenalan());
                                _pp.setNoRujukan(pp.getNoRujukan());
                                _pp.setPermohonan(pp.getPermohonan());
                                _pp.setPihak(pp.getPihak());
                                _pp.setPihakCawangan(pp.getPihakCawangan());
                                _pp.setSyerBersama(pp.getSyerBersama());
                                _pp.setSyerPembilang(pp.getSyerPembilang());
                                _pp.setSyerPenyebut(pp.getSyerPenyebut());
                                _pp.setWargaNegara(pp.getWargaNegara());
                                permohonanPihakService.saveOrUpdate(_pp);
                            }
                        }

                    }
                }
            }
        }
    }

    private void janaHakmilikBaru(Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik) {
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                LOG.debug("daerah :" + hakmilik.getDaerah().getKod());
                kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                hakmilik.setBandarPekanMukim(kbpm);
                LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";
//                if (p.getKodUrusan().getKod().equals("HT") || p.getKodUrusan().getKod().equals("HTSC")
//                        || p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSPS")
//                        || p.getKodUrusan().getKod().equals("HTSPV")) {
//                    idHakmilikBaru = idHakmilikGenerator.generateStrata(kodNegeri, null, hakmilik);
//                } else {
                if (!p.getKodUrusan().getKod().equals("HKTHK") && !p.getKodUrusan().getKod().equals("HSTHK")) {
                    idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilik);
                } else {
                    idHakmilikBaru = hakmilik.getIdHakmilik();
                }
                //}
                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                hakmilikBaru.setNoFail(idFail);
                if (hakmilik.getPermohonan() != null) {
                    hakmilikBaru.setPermohonan(hakmilik.getPermohonan());
                }
                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                hakmilikBaru.setDaerah(hakmilik.getDaerah());
                hakmilikBaru.setBandarPekanMukim(kbpm);

//                KodSeksyen ks = new KodSeksyen();
//                ks.setKod(000);
                hakmilikBaru.setSeksyen(null);
                KodHakmilik kodHakmilik = new KodHakmilik();
                kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
                hakmilikBaru.setKodHakmilik(kodHakmilik);
                KodLot kl = new KodLot();
                kl.setKod("1");
                hakmilikBaru.setLot(kl);
                //hakmilikBaru.setNoLot("0");
                KodKategoriTanah kkt = new KodKategoriTanah();
                //set default kategori tanah bangunan
//                kkt.setKod("1");
                kkt.setKod("2");
                hakmilikBaru.setKategoriTanah(kkt);
//                KodKegunaanTanah kgt = new KodKegunaanTanah();
//                kgt.setKod("BO1");
//                kgt.setKategoriTanah(kkt);
                String kodgunaguna = "B01";
                hakmilikBaru.setKegunaanTanah(regService.cariKegunaanTanah(kodgunaguna, kkt.getKod()));
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
                KodUOM kuo = new KodUOM();
                kuo.setKod("M");
                hakmilikBaru.setKodUnitLuas(kuo);
                hakmilikBaru.setLuas(BigDecimal.ZERO);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                /*AUDIT INFO*/

//                InfoAudit info = pengguna.getInfoAudit();
//                info.setDimasukOleh(pengguna);
//                info.setTarikhMasuk(new java.util.Date());
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);

            }
        }

        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }

    }

    private void saveHakmilikCantuman(List<HakmilikPermohonan> hakmilikPermohonan, InfoAudit ia, Permohonan p, Pengguna pengguna) {
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        long t = System.currentTimeMillis();

        if (hakmilikPermohonan.size() > 0) {
            if (debug) {
                LOG.debug("start to create new Hakmilik.");
            }
            HakmilikPermohonan hp = hakmilikPermohonan.get(0);
            hakmilikBaru = new Hakmilik();
            Hakmilik hakmilikTerpilih = hp.getHakmilik();
            String id = hakmilikTerpilih.getIdHakmilik();
            hakmilikTerpilih = hakmilikDAO.findById(id);

            hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());

            //hakmilikTerpilih.setKodHakmilik(hakmilik.getKodHakmilik());
            if (hakmilik.getKodHakmilik() != null) {
                hakmilikBaru.setKodHakmilik(hakmilik.getKodHakmilik());
            } else {
                hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
            }

            if (StringUtils.isNotBlank(kodBPM)) {
                LOG.debug("tukar bpm :+" + kodBPM);
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                kbpm = regService.cariBPM(kodBPM, hakmilikTerpilih.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                hakmilikBaru.setBandarPekanMukim(kbpm);
            } else {
                hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
            }
            LOG.debug("kod pekan " + hakmilikTerpilih.getBandarPekanMukim().getKod());
            String kodNegeri = conf.getProperty("kodNegeri");
            //String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikTerpilih);
            String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
            String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
            KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
            hakmilikBaru.setIdHakmilik(idHakmilikBaru);
            hakmilikBaru.setNoFail(idFail);
            hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
            hakmilikBaru.setNoHakmilik(noHakmilik);
            hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
            //hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
            //hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
            hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
            hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
            hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
            hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
            hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
            hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
            hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
            hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
            KodLot kl = new KodLot();
            if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                    || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                kl.setKod("1");
            } else {
                kl.setKod("2");
            }
            hakmilikBaru.setLot(kl);
            hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
            hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());
            if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() > 0) {
                LOG.debug("::start copy tarikh daftar asal dari hakmilik asal");
                Hakmilik hmAsal = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                if (hmAsal != null) {
                    hakmilikBaru.setTarikhDaftarAsal(hmAsal.getTarikhDaftar());
                    if (hmAsal.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhLuput(hmAsal.getTarikhLuput());
                    }
                }

            } else if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() < 0 && hakmilikTerpilih.getSenaraiHakmilikSebelum().size() > 0) {

                LOG.debug("::start copy tarikh daftar asal dari hakmilik sebelum");

                /*TODO:AMIK TARIKH DAFTAR ASAL PALING LAMA*/
                Hakmilik sblm = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                if (sblm != null) {
                    hakmilikBaru.setTarikhDaftarAsal(sblm.getTarikhDaftar());
                    if (sblm.getTarikhLuput() != null) {
                        hakmilikBaru.setTarikhLuput(sblm.getTarikhLuput());
                    }
                }

            } else {

                Calendar cal = Calendar.getInstance();
                Date currTarikhDaftar = cal.getTime();
                List<Date> ltd = new ArrayList();
                List<Date> ltl = new ArrayList();
                for (HakmilikPermohonan hp2 : hakmilikPermohonan) {
                    if (hp2 == null) {
                        continue;
                    }
                    Hakmilik hm = hp2.getHakmilik();
                    String idh = hm.getIdHakmilik();
                    Hakmilik hakmilikT = hakmilikDAO.findById(idh);
                    if (hakmilikT == null) {
                        continue;
                    }

                    ltd.add(hakmilikT.getTarikhDaftar());
                    if (hakmilikT.getPegangan() == 'P') {
                        ltl.add(hakmilikT.getTarikhLuput());
                    }

                }
                Collections.sort(ltd);
                hakmilikBaru.setTarikhDaftarAsal(ltd.get(0));
                if (hakmilikTerpilih.getPegangan() == 'P') {
                    Collections.sort(ltl);
                    hakmilikBaru.setTarikhLuput(ltl.get(0));
                }
            }

            if (hakmilikTerpilih.getTempohPegangan() != null) {
                hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
            }
            hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
            hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
            hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
            hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());
            hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
            hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
            hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
            hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());

            hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
            hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
            hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());

            hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
            hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
            hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());

            hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
            hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

            BigDecimal totalLuas = BigDecimal.ZERO;
            for (HakmilikPermohonan hp2 : hakmilikPermohonan) {
                if (hp2 == null) {
                    continue;
                }
                Hakmilik hm = hakmilikDAO.findById(hp2.getHakmilik().getIdHakmilik());
                if (hm == null) {
                    continue;
                }
                totalLuas = totalLuas.add(hm.getLuas());
            }

            hakmilikBaru.setLuas(totalLuas);
            hakmilikBaru.setInfoAudit(ia);

            if (debug) {
                LOG.debug("start to create new Hakmilik Permohonanan.");
            }
            mohonHakmilikBaru = new HakmilikPermohonan();
            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
            mohonHakmilikBaru.setPermohonan(p);
            mohonHakmilikBaru.setInfoAudit(ia);

            hakmilikDAO.save(hakmilikBaru);
            hakmilikPermohonanDAO.save(mohonHakmilikBaru);
        }

        List<HakmilikUrusan> lhutemp = new ArrayList();
        List<HakmilikAsalPermohonan> lhatemp = new ArrayList();
        List<HakmilikSebelumPermohonan> lhstemp = new ArrayList();
        List<HakmilikSebelumPermohonan> lhstemp2 = new ArrayList();

        /*
         * 1) salah satu hm lama ada hm asal --> hm asal akan jd hm asal pd hm baru, semua hm lama akan jadi hm sblm
         * 2) hm lama tiada hm asal --> hm lama akan jd hm asal pd hm baru, hm sblm tiada utk hm baru
         * 
         */
        for (HakmilikPermohonan hp : hakmilikPermohonan) {
            if (debug) {
                LOG.debug("start to create hakmilik asal permohonan");
            }
            if (hp == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            String id = hm.getIdHakmilik();
            hm = hakmilikDAO.findById(id);

            LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
            LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

            if (hm.getSenaraiHakmilikAsal().size() > 0) {

                for (HakmilikAsal asal : hm.getSenaraiHakmilikAsal()) {
                    LOG.debug("SET HAKMILIK ASAL HAKMILIK LAMA JADI HAKMILIK ASAL UNTUK HAKMILIK BARU");
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    }
                    hap.setHakmilik(hakmilikBaru);
                    hap.setHakmilikSejarah(asal.getHakmilikAsal());
                    hap.setInfoAudit(ia);
                    //hakmilikAsalPermohonanDAO.save(hap);
                    lhatemp.add(hap);
                }
            }

            if (hm.getSenaraiHakmilikSebelum().size() > 0) {
                for (HakmilikSebelum sblm : hm.getSenaraiHakmilikSebelum()) {
                    LOG.debug("SET HAKMILIK SBLM HAKMILIK LAMA JADI HAKMILIK ASAL UNTUK HAKMILIK BARU");
                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                    if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                        hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                    }
                    hsp.setHakmilik(hakmilikBaru);
                    hsp.setPermohonan(p);
                    hsp.setCawangan(pengguna.getKodCawangan());
                    hsp.setHakmilikSejarah(sblm.getHakmilikSebelum());
                    hsp.setInfoAudit(ia);
                    lhstemp.add(hsp);
                }
            }

            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
            }
            //      hsp.setHakmilik(hm);
            hsp.setHakmilik(hakmilikBaru);
            hsp.setPermohonan(p);
            hsp.setCawangan(pengguna.getKodCawangan());
            hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
            hsp.setInfoAudit(ia);
            lhstemp2.add(hsp);
            //      hakmilikSblmPermohonanDAO.save(hsp);

            if (debug) {
                LOG.debug("start to create hakmilik urusan");
            }

            List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hm.getIdHakmilik());
            LOG.debug("copy senarai urusan from urusan lame");
            LOG.debug("size urusan lame :" + senaraiHakmilikurusan.size());
            /*TODO:set tarikh daftar asal*/
        }

//        hakmilikUrusan.clear();
//         List<ArrayList> hakmilikUrusan1 = new ArrayList<ArrayList>();
        List<HakmilikUrusan> hakmilikUrusan = new ArrayList<HakmilikUrusan>();
        List<HakmilikUrusan> hakmilikUrusanSimpan = new ArrayList<HakmilikUrusan>();
        for (HakmilikPermohonan hp : hakmilikPermohonan) {

            List<HakmilikUrusan> checkingHakmilikUrusanBaru = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hp.getHakmilik().getIdHakmilik());
            HakmilikUrusan hakmilikU;

            if (hakmilikUrusan.size() <= 0) {
                hakmilikUrusan.addAll(checkingHakmilikUrusanBaru);
            } else {
                for (HakmilikUrusan huBaru : checkingHakmilikUrusanBaru) {
                    hakmilikUrusan.remove(t);
                    hakmilikUrusan.add(huBaru);
                }
            }
        }
        for (HakmilikUrusan huNew : hakmilikUrusan) {
            HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
            hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
            hakmilikUrusanBaru.setIdPerserahan(huNew.getIdPerserahan());
            hakmilikUrusanBaru.setKodUrusan(huNew.getKodUrusan());
            hakmilikUrusanBaru.setAktif(huNew.getAktif());
            hakmilikUrusanBaru.setTarikhBatal(huNew.getTarikhBatal());
            hakmilikUrusanBaru.setTarikhDaftar(huNew.getTarikhDaftar());
            hakmilikUrusanBaru.setKodUnitLuas(huNew.getKodUnitLuas());
            hakmilikUrusanBaru.setLuasTerlibat(huNew.getLuasTerlibat());
            hakmilikUrusanBaru.setCawangan(huNew.getCawangan());
            hakmilikUrusanBaru.setDaerah(huNew.getDaerah());
            hakmilikUrusanBaru.setCukaiLama(huNew.getCukaiLama());
            hakmilikUrusanBaru.setCukaiBaru(huNew.getCukaiBaru());
            hakmilikUrusanBaru.setFolderDokumen(huNew.getFolderDokumen());
            hakmilikUrusanBaru.setNoRujukan(huNew.getNoRujukan());
            hakmilikUrusanBaru.setTempohTahun(huNew.getTempohTahun());
            hakmilikUrusanBaru.setTempohBulan(huNew.getTempohBulan());
            hakmilikUrusanBaru.setTempohHari(huNew.getTempohHari());
            hakmilikUrusanBaru.setTarikhRujukan(huNew.getTarikhRujukan());
            hakmilikUrusanBaru.setItem(huNew.getItem());
//            hakmilikUrusanSimpan.add(hakmilikUrusanBaru);
            hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);
        }
//        hakmilikUrusanService.saveOrUpdateUrusan(hakmilikUrusanSimpan);

        for (HakmilikUrusan huNew : hakmilikUrusan) {
            List<PermohonanPihak> senaraiMp = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(huNew.getIdPerserahan(), huNew.getHakmilik().getIdHakmilik());
            List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(huNew.getIdPerserahan(), huNew.getHakmilik().getIdHakmilik());

            PermohonanPihak _pp = new PermohonanPihak();
            Pemohon _pmh = new Pemohon();
            Permohonan mohon = permohonanDAO.findById(huNew.getIdPerserahan());

            for (PermohonanPihak pp : senaraiMp) {
//                    PermohonanPihak _pp = new PermohonanPihak();
                _pp.setPermohonan(mohon);
                _pp.setAlamat(pp.getAlamat());
                _pp.setAlamatSurat(pp.getAlamatSurat());
                _pp.setCawangan(pp.getCawangan());
                _pp.setHakmilik(hakmilikBaru);
                _pp.setInfoAudit(ia);
                _pp.setJenis(pp.getJenis());
                _pp.setJenisPengenalan(pp.getJenisPengenalan());
                _pp.setJumlahPembilang(pp.getJumlahPembilang());
                _pp.setJumlahPenyebut(pp.getJumlahPenyebut());
                _pp.setNama(pp.getNama());
                _pp.setNoPengenalan(pp.getNoPengenalan());
                _pp.setNoRujukan(pp.getNoRujukan());
                _pp.setPermohonan(pp.getPermohonan());
                _pp.setPihak(pp.getPihak());
                _pp.setPihakCawangan(pp.getPihakCawangan());
                _pp.setSyerBersama(pp.getSyerBersama());
                if (pp.getSyerPembilang() != null) {
                    _pp.setSyerPembilang(pp.getSyerPembilang());
                }

                if (pp.getSyerPenyebut() != null) {
                    _pp.setSyerPenyebut(pp.getSyerPenyebut());
                }
                _pp.setWargaNegara(pp.getWargaNegara());
                permohonanPihakService.saveOrUpdate(_pp);

            }
            for (Pemohon pmh : senaraiPemohon) {
//                    Pemohon _pmh = new Pemohon();

                _pmh.setPermohonan(mohon);
                _pmh.setHakmilik(hakmilikBaru);
                _pmh.setAlamat(pmh.getAlamat());
                _pmh.setAlamatSurat(pmh.getAlamatSurat());
                _pmh.setCawangan(pmh.getCawangan());
                _pmh.setDalamanNilai1(pmh.getDalamanNilai1());
                _pmh.setDalamanNilai2(pmh.getDalamanNilai2());
                _pmh.setJenis(pmh.getJenis());
                _pmh.setJenisPemohon(pmh.getJenisPemohon());
                _pmh.setJenisPengenalan(pmh.getJenisPengenalan());
                _pmh.setJumlahPembilang(pmh.getJumlahPembilang());
                _pmh.setJumlahPenyebut(pmh.getJumlahPenyebut());
                _pmh.setNama(pmh.getNama());
                _pmh.setNoPengenalan(pmh.getNoPengenalan());
                _pmh.setPekerjaan(pmh.getPekerjaan());
                _pmh.setPendapatan(pmh.getPendapatan());
                _pmh.setPihak(pmh.getPihak());
                _pmh.setPihakCawangan(pmh.getPihakCawangan());
                _pmh.setSyerPembilang(pmh.getSyerPembilang());
                _pmh.setSyerPenyebut(pmh.getSyerPenyebut());
                _pmh.setWargaNegara(pmh.getWargaNegara());
                _pmh.setInfoAudit(ia);
                pemohonService.saveOrUpdate(_pmh);
            }
        }
//        }

        HakmilikPermohonan hp = hakmilikPermohonan.get(0);
        Hakmilik hm = hp.getHakmilik();
        String id = hm.getIdHakmilik();
        hm = hakmilikDAO.findById(id);

        /*BUANG HAKMILIK ASAL ID HAKMILIK SAMA*/
        if (lhatemp.size() > 0) {
            for (HakmilikAsalPermohonan asal : lhatemp) {
                hakmilikAsalPermohonanDAO.save(asal);
            }
            for (HakmilikSebelumPermohonan sblm : lhstemp) {
                hakmilikSblmPermohonanDAO.save(sblm);
            }

            for (HakmilikSebelumPermohonan sblm2 : lhstemp2) {
                hakmilikSblmPermohonanDAO.save(sblm2);
            }
        } else {
            for (HakmilikSebelumPermohonan sblm2 : lhstemp2) {
                HakmilikAsalPermohonan asal = new HakmilikAsalPermohonan();
                if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                    asal.setHakmilikPermohonan(sblm2.getHakmilikPermohonan());
                }
                asal.setHakmilik(sblm2.getHakmilik());
                asal.setHakmilikSejarah(sblm2.getHakmilikSejarah());
                asal.setInfoAudit(sblm2.getInfoAudit());
                hakmilikAsalPermohonanDAO.save(asal);
            }
        }

        /*BUANG URUSAN IDPERSERAHAN SAMA*/
//        if (lhutemp.size() > 0) {
//            LOG.debug("size lhutemp: " + lhutemp.size());
//            String idPerserahan = "0";
//            for (HakmilikUrusan hakmilikUrusan : lhutemp) {
//                HakmilikUrusan hakmilikUrusanCantum = new HakmilikUrusan();
//                LOG.debug("idPerserahan :" + idPerserahan);
//                if (idPerserahan.equals(hakmilikUrusan.getIdPerserahan())) {
//                    LOG.debug("idPerserahan sama detected:" + idPerserahan);
//                    continue;
//                }
//                hakmilikUrusanCantum.setHakmilik(hakmilikBaru);
//                hakmilikUrusanCantum.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
//                hakmilikUrusanCantum.setKodUrusan(hakmilikUrusan.getKodUrusan());
//                hakmilikUrusanCantum.setAktif(hakmilikUrusan.getAktif());
//                hakmilikUrusanCantum.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
//                hakmilikUrusanCantum.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
//                hakmilikUrusanCantum.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
//                hakmilikUrusanCantum.setLuasTerlibat(hakmilikUrusan.getLuasTerlibat());
//                hakmilikUrusanCantum.setCawangan(hakmilikUrusan.getCawangan());
//                hakmilikUrusanCantum.setDaerah(hakmilikUrusan.getDaerah());
//                //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
//                hakmilikUrusanCantum.setCukaiLama(hakmilikUrusan.getCukaiLama());
//                hakmilikUrusanCantum.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
//                hakmilikUrusanCantum.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
//                hakmilikUrusanCantum.setNoRujukan(hakmilikUrusan.getNoRujukan());
//                hakmilikUrusanCantum.setTempohTahun(hakmilikUrusan.getTempohTahun());
//                hakmilikUrusanCantum.setTempohBulan(hakmilikUrusan.getTempohBulan());
//                hakmilikUrusanCantum.setTempohHari(hakmilikUrusan.getTempohHari());
//                hakmilikUrusanCantum.setInfoAudit(ia);
//                hakmilikUrusanService.saveOrUpdate(hakmilikUrusanCantum);
//                idPerserahan = hakmilikUrusan.getIdPerserahan();
//
//            }
//        }
        //copy only one from the combined hakmilik
//        LOG.debug("::start cantum pihak");
//        LOG.debug("copy pihak dari : " + hm.getIdHakmilik());
//        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);

        LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }
            if (hpk.getPerserahan() != null) {
                HakmilikUrusan h1 = pService.findHUrusan(hpk.getHakmilik().getIdHakmilik(), hpk.getPerserahan().getIdPerserahan());

                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                hpb.setHakmilik(hakmilikBaru);
                hpb.setCawangan(hakmilikBaru.getCawangan());
                hpb.setPihakCawangan(hpk.getPihakCawangan());
                hpb.setJenis(hpk.getJenis());
                hpb.setSyerPembilang(hpk.getSyerPembilang());
                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                hpb.setPerserahan(h1);
                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                hpb.setKaveatAktif(hpk.getKaveatAktif());
                hpb.setAktif(hpk.getAktif());
                hpb.setPihak(hpk.getPihak());
                hpb.setNama(hpk.getNama());
                hpb.setAlamat1(hpk.getAlamat1());
                hpb.setAlamat2(hpk.getAlamat2());
                hpb.setAlamat3(hpk.getAlamat3());
                hpb.setAlamat4(hpk.getAlamat4());
                hpb.setAlamatSurat(hpk.getAlamatSurat());
                hpb.setPoskod(hpk.getPoskod());
                hpb.setNegeri(hpk.getNegeri());
                hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                hpb.setNoPengenalan(hpk.getNoPengenalan());
                if (hpk.getNoPengenalanLama() != null) {
                    hpb.setNoPengenalanLama(hpk.getNoPengenalanLama());
                }
                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
//                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
//                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                hpb.setJumlahPembilang(hpk.getSyerPembilang());
                hpb.setWargaNegara(hpk.getWargaNegara());
                hpb.setInfoAudit(ia);

                hwList = pService.findHakmilikWarisByPihakKepentingan(hpk.getIdHakmilikPihakBerkepentingan());

                for (HakmilikWaris listHw : hwList) {
                    HakmilikWaris hw = new HakmilikWaris();
                    hw.setInfoAudit(ia);
                    hw.setJenisPengenalan(listHw.getJenisPengenalan());
                    hw.setPemegangAmanah(hpb);
                    hw.setNama(listHw.getNama());
                    hw.setNoPengenalan(listHw.getNoPengenalan());
                    hw.setSyerPembilang(listHw.getSyerPembilang());
                    hw.setSyerPenyebut(listHw.getSyerPenyebut());
                    hw.setWargaNegara(listHw.getWargaNegara());
                    hw.setNegeri(listHw.getNegeri());
                    hw.setCawangan(listHw.getCawangan());
                    hakmilikWarisDAO.save(hw);
                }
                hpkService.save(hpb);
//                            
            } else if (hpk.getPerserahan() == null) {
                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                hpb.setHakmilik(hakmilikBaru);
                hpb.setCawangan(hakmilikBaru.getCawangan());
                hpb.setPihakCawangan(hpk.getPihakCawangan());
                hpb.setJenis(hpk.getJenis());
                hpb.setSyerPembilang(hpk.getSyerPembilang());
                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
//                            hpb.setPerserahan(h1);
                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                hpb.setKaveatAktif(hpk.getKaveatAktif());
                hpb.setAktif(hpk.getAktif());
                hpb.setPihak(hpk.getPihak());
                hpb.setNama(hpk.getNama());
                hpb.setAlamat1(hpk.getAlamat1());
                hpb.setAlamat2(hpk.getAlamat2());
                hpb.setAlamat3(hpk.getAlamat3());
                hpb.setAlamat4(hpk.getAlamat4());
                hpb.setAlamatSurat(hpk.getAlamatSurat());
                hpb.setPoskod(hpk.getPoskod());
                hpb.setNegeri(hpk.getNegeri());
                hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                hpb.setNoPengenalan(hpk.getNoPengenalan());
                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
//                            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
//                            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                hpb.setJumlahPembilang(hpk.getSyerPembilang());
                hpb.setWargaNegara(hpk.getWargaNegara());
                hpb.setInfoAudit(ia);

                hwList = pService.findHakmilikWarisByPihakKepentingan(hpk.getIdHakmilikPihakBerkepentingan());

                for (HakmilikWaris listHw : hwList) {
                    HakmilikWaris hw = new HakmilikWaris();
                    hw.setInfoAudit(ia);
                    hw.setJenisPengenalan(listHw.getJenisPengenalan());
                    hw.setPemegangAmanah(hpb);
                    hw.setNama(listHw.getNama());
                    hw.setNoPengenalan(listHw.getNoPengenalan());
                    hw.setSyerPembilang(listHw.getSyerPembilang());
                    hw.setSyerPenyebut(listHw.getSyerPenyebut());
                    hw.setWargaNegara(listHw.getWargaNegara());
                    hw.setNegeri(listHw.getNegeri());
                    hw.setCawangan(listHw.getCawangan());
                    hakmilikWarisDAO.save(hw);

                }
                hpkService.save(hpb);
            }

        }

        if (debug) {
            LOG.debug("finished. took = " + (System.currentTimeMillis() - t) + " ms");
        }
    }

    private void saveHakmilikSerahbalikSemuaTanahBerimilik(List<HakmilikPermohonan> hakmilikPermohonan, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, KodUrusan kodUrusan) {
        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        long t = System.currentTimeMillis();

        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {
                for (int i = 0; i < totalHakmilik; i++) {
                    if (debug) {
                        LOG.debug("start to create new Hakmilik.");
                    }
                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    hakmilikBaru = new Hakmilik();
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    hakmilikTerpilih = hakmilikDAO.findById(id);

                    hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());

                    //hakmilikTerpilih.setKodHakmilik(hakmilik.getKodHakmilik());
                    if (hakmilik.getKodHakmilik() != null) {
                        hakmilikBaru.setKodHakmilik(hakmilik.getKodHakmilik());
                    } else {
                        hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
                    }

                    if (StringUtils.isNotBlank(kodBPM)) {
                        LOG.debug("tukar bpm :+" + kodBPM);
                        KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        kbpm = regService.cariBPM(kodBPM, hakmilikTerpilih.getDaerah().getKod());
                        //hakmilik.setBandarPekanMukim(kbpm);
                        hakmilikBaru.setBandarPekanMukim(kbpm);
                    } else {
                        hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                    }
                    LOG.debug("kod pekan " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                    String kodNegeri = conf.getProperty("kodNegeri");
                    //String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikTerpilih);
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setNoFail(idFail);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                    //hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
                    //hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
                    hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());

                    LOG.debug("size hakmilik asal :" + hakmilikTerpilih.getSenaraiHakmilikAsal().size());
                    LOG.debug("size hakmilik sblm :" + hakmilikTerpilih.getSenaraiHakmilikSebelum().size());

                    LOG.debug("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                    if (hakmilikTerpilih.getTarikhLuput() != null) {
                        if (!kodUrusan.getKod().equals("HSSTB")) {
                            hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        }

                        hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() != null) {
                        if (!kodUrusan.getKod().equals("HSSTB")) {
                            hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                        }
                    }
                    if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                        if (!kodUrusan.getKod().equals("HSSTB")) {
                            hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                        }
                    }

                    // }
                    if (hakmilikTerpilih.getTempohPegangan() != null) {
                        hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    }
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());

                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());

                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());

                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                    BigDecimal totalLuas = BigDecimal.ZERO;
                    for (HakmilikPermohonan hp2 : hakmilikPermohonan) {
                        if (hp2 == null) {
                            continue;
                        }
                        Hakmilik hm = hakmilikDAO.findById(hp2.getHakmilik().getIdHakmilik());
                        if (hm == null) {
                            continue;
                        }
                        totalLuas = totalLuas.add(hm.getLuas());
                    }

                    hakmilikBaru.setLuas(totalLuas);
                    hakmilikBaru.setInfoAudit(ia);

                    if (debug) {
                        LOG.debug("start to create new Hakmilik Permohonanan.");
                    }
                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    mohonHakmilikBaru.setInfoAudit(ia);

                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    //copy only one from the combined hakmilik
                    LOG.debug("::start cantum pihak");

//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hakmilikTerpilih.getSenaraiPihakBerkepentingan();
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikTerpilih);
                    LOG.debug("size pihak setiap hakmilik : " + senaraiHakmilikPihak.size());
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        }

                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilikBaru);
                        hpb.setCawangan(hakmilikBaru.getCawangan());
                        hpb.setPihakCawangan(hpk.getPihakCawangan());
                        hpb.setJenis(hpk.getJenis());
                        hpb.setSyerPembilang(hpk.getSyerPembilang());
                        hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                        hpb.setPerserahan(hpk.getPerserahan());
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
                        hpb.setNama(hpk.getNama());
                        hpb.setAlamat1(hpk.getAlamat1());
                        hpb.setAlamat2(hpk.getAlamat2());
                        hpb.setAlamat3(hpk.getAlamat3());
                        hpb.setAlamat4(hpk.getAlamat4());
                        hpb.setAlamatSurat(hpk.getAlamatSurat());
                        hpb.setPoskod(hpk.getPoskod());
                        hpb.setNegeri(hpk.getNegeri());
                        hpb.setPihakKongsiBersama(hpk.getPihakKongsiBersama());
                        hpb.setNoPengenalan(hpk.getNoPengenalan());
                        hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                        hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
                        hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                        hpb.setJumlahPembilang(hpk.getSyerPembilang());
                        hpb.setWargaNegara(hpk.getWargaNegara());
                        hpb.setInfoAudit(ia);
                        hpkService.save(hpb);
                    }

                    LOG.debug("hakmilikPermohonan size:" + hakmilikPermohonan.size());
                    for (HakmilikPermohonan hp2 : hakmilikPermohonan) {

                        LOG.debug(":start copy hakmilik asal / hakmilik sebelum:");
                        if (hp2 == null) {
                            continue;
                        }
                        if (hp2.getHakmilik().getIdHakmilik() == null) {
                            continue;
                        }

                        Hakmilik hm = hakmilikDAO.findById(hp2.getHakmilik().getIdHakmilik());
                        if (hm == null) {
                            continue;
                        }
                        HakmilikPermohonan hakmilikPermohonanBaru = hakmilikPermohonanDAO.findById(mohonHakmilikBaru.getId());
                        LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                        LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

                        if (hm.getSenaraiHakmilikAsal().size() > 0) {
                            LOG.debug("SET HAKMILIK ASAL HAKMILIK LAMA JADI HAKMILIK ASAL UNTUK HAKMILIK BARU");
                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                            KodUrusan kodurusan = p.getKodUrusan();
                            if (!kodurusan.getKod().equals("HSSTB")) {
                                if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                }
                            }
                            hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                            hap.setHakmilikSejarah(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                            hap.setInfoAudit(ia);
                            hakmilikAsalPermohonanDAO.save(hap);
                            //lhatemp.add(hap);
                        }
                        if (hm.getSenaraiHakmilikSebelum().size() > 0) {
                            LOG.debug("SET HAKMILIK SBLM HAKMILIK LAMA JADI HAKMILIK ASAL UNTUK HAKMILIK BARU");
                            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                            KodUrusan kodurusan = p.getKodUrusan();
                            if (!kodurusan.getKod().equals("HSSTB")) {
                                if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                                }
                            }
                            hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum()));
                            hap.setHakmilikSejarah(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                            hap.setInfoAudit(ia);
                            hakmilikAsalPermohonanDAO.save(hap);
                            //lhatemp.add(hap);
                        }

                        HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                        KodUrusan kodurusan = p.getKodUrusan();
                        if (!kodurusan.getKod().equals("HSSTB")) {
                            if (p.getKodUrusan().getKod() != "HSSTB" || p.getKodUrusan().getKod() != "HKBM" || p.getKodUrusan().getKod() != "HKSTB") {
                                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                            }
                        }
                        hsp.setHakmilik(hm);
                        hsp.setPermohonan(p);
                        hsp.setCawangan(pengguna.getKodCawangan());
                        hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(hm.getIdHakmilik()).getIdHakmilik());
                        hsp.setInfoAudit(ia);
                        hakmilikSblmPermohonanDAO.save(hsp);
                    }
                    if (debug) {
                        LOG.debug("start to create hakmilik urusan");
                    }

                    List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hakmilikTerpilih.getIdHakmilik());
                    LOG.debug("copy senarai urusan from urusan lame");

                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                        if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                            continue;
                        }
                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                        hakmilikUrusanBaru.setItem(hu.getItem());
                        hakmilikUrusanBaru.setInfoAudit(ia);
                        //lhutemp.add(hakmilikUrusanBaru);
                        hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                    }

                }

                if (debug) {
                    LOG.debug("finished. took = " + (System.currentTimeMillis() - t) + " ms");
                }
            }
        }
    }

    public List<UrusanCache> getTxnGroups() {
        return txnGroups;
    }

    public void setTxnGroups(List<UrusanCache> txnGroups) {
        this.txnGroups = txnGroups;
    }

    // label for screen
    public String getSenaraiUrusanLabel() {
        StringBuilder sb = new StringBuilder();
        if (senaraiUrusan != null) {
            int sz = senaraiUrusan.size();
            for (int i = 0; i < sz; i++) {
                if (senaraiUrusan.get(i).getKodUrusan() == null) {
                    continue;
                }
                sb.append(senaraiUrusan.get(i).getNamaUrusan()).append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // remove last comma
        }
        return sb.toString();
    }

    public UrusanPermohonan getUrusanPermohonan() {
        return urusanPermohonan;
    }

    public void setUrusanPermohonan(UrusanPermohonan urusanPermohonan) {
        this.urusanPermohonan = urusanPermohonan;
    }

    public List<HakmilikWaris> getHwList() {
        return hwList;
    }

    public void setHwList(List<HakmilikWaris> hwList) {
        this.hwList = hwList;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public String getKodRUJ() {
        return kodRUJ;
    }

    public void setKodRUJ(String kodRUJ) {
        this.kodRUJ = kodRUJ;
    }

    public String getHakmilikLama() {
        return HakmilikLama;
    }

    public void setHakmilikLama(String HakmilikLama) {
        this.HakmilikLama = HakmilikLama;
    }

    public Long getIdUrusanGDHmBaru() {
        return idUrusanGDHmBaru;
    }

    public void setIdUrusanGDHmBaru(Long idUrusanGDHmBaru) {
        this.idUrusanGDHmBaru = idUrusanGDHmBaru;
    }

    public HakmilikUrusan getHakmilikUrusanBaruGD() {
        return HakmilikUrusanBaruGD;
    }

    public void setHakmilikUrusanBaruGD(HakmilikUrusan HakmilikUrusanBaruGD) {
        this.HakmilikUrusanBaruGD = HakmilikUrusanBaruGD;
    }

    public HakmilikUrusan getHakmilikUrusanBaruPMT() {
        return HakmilikUrusanBaruPMT;
    }

    public void setHakmilikUrusanBaruPMT(HakmilikUrusan HakmilikUrusanBaruPMT) {
        this.HakmilikUrusanBaruPMT = HakmilikUrusanBaruPMT;
    }

    public ArrayList<Permohonan> getSenaraiPermohonan2() {
        return senaraiPermohonan2;
    }

    public void setSenaraiPermohonan2(ArrayList<Permohonan> senaraiPermohonan2) {
        this.senaraiPermohonan2 = senaraiPermohonan2;
    }

    public List<HakmilikSebelum> getHakmilikSblm() {
        return hakmilikSblm;
    }

    public void setHakmilikSblm(List<HakmilikSebelum> hakmilikSblm) {
        this.hakmilikSblm = hakmilikSblm;
    }

    public List<HakmilikUrusan> getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(List<HakmilikUrusan> hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public List<HakmilikPermohonan> getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(List<HakmilikPermohonan> mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public List<Pemohon> getPemohon() {
        return pemohon;
    }

    public void setPemohon(List<Pemohon> pemohon) {
        this.pemohon = pemohon;
    }

    public List<PermohonanPihak> getMohonPihak() {
        return mohonPihak;
    }

    public void setMohonPihak(List<PermohonanPihak> mohonPihak) {
        this.mohonPihak = mohonPihak;
    }

    public KodRujukanDAO getKodRujukanDAO() {
        return kodRujukanDAO;
    }

    public void setKodRujukanDAO(KodRujukanDAO kodRujukanDAO) {
        this.kodRujukanDAO = kodRujukanDAO;
    }

    public HakmilikWarisDAO getHakmilikWarisDAO() {
        return hakmilikWarisDAO;
    }

    public void setHakmilikWarisDAO(HakmilikWarisDAO hakmilikWarisDAO) {
        this.hakmilikWarisDAO = hakmilikWarisDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PembetulanService getpService() {
        return pService;
    }

    public void setpService(PembetulanService pService) {
        this.pService = pService;
    }

    public PenggunaDAO getPenggunaDAO() {
        return penggunaDAO;
    }

    public void setPenggunaDAO(PenggunaDAO penggunaDAO) {
        this.penggunaDAO = penggunaDAO;
    }

    public KodUrusanDAO getKodUrusanDAO() {
        return kodUrusanDAO;
    }

    public void setKodUrusanDAO(KodUrusanDAO kodUrusanDAO) {
        this.kodUrusanDAO = kodUrusanDAO;
    }

    public FolderDokumenDAO getFolderDokumenDAO() {
        return folderDokumenDAO;
    }

    public void setFolderDokumenDAO(FolderDokumenDAO folderDokumenDAO) {
        this.folderDokumenDAO = folderDokumenDAO;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public KodKlasifikasiDAO getKodKlasifikasiDAO() {
        return kodKlasifikasiDAO;
    }

    public void setKodKlasifikasiDAO(KodKlasifikasiDAO kodKlasifikasiDAO) {
        this.kodKlasifikasiDAO = kodKlasifikasiDAO;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public CaraBayaranDAO getCaraBayaranDAO() {
        return caraBayaranDAO;
    }

    public void setCaraBayaranDAO(CaraBayaranDAO caraBayaranDAO) {
        this.caraBayaranDAO = caraBayaranDAO;
    }

    public KaunterService getKaunterService() {
        return kaunterService;
    }

    public void setKaunterService(KaunterService kaunterService) {
        this.kaunterService = kaunterService;
    }

    public AkaunService getAkaunService() {
        return akaunService;
    }

    public void setAkaunService(AkaunService akaunService) {
        this.akaunService = akaunService;
    }

    public DokumenKewanganDAO getDokumenKewanganDAO() {
        return dokumenKewanganDAO;
    }

    public void setDokumenKewanganDAO(DokumenKewanganDAO dokumenKewanganDAO) {
        this.dokumenKewanganDAO = dokumenKewanganDAO;
    }

    public TransaksiDAO getTransaksiDAO() {
        return transaksiDAO;
    }

    public void setTransaksiDAO(TransaksiDAO transaksiDAO) {
        this.transaksiDAO = transaksiDAO;
    }

    public KodTransaksiDAO getKodTransaksiDAO() {
        return kodTransaksiDAO;
    }

    public void setKodTransaksiDAO(KodTransaksiDAO kodTransaksiDAO) {
        this.kodTransaksiDAO = kodTransaksiDAO;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public KodStatusTransaksiKewanganDAO getKodKewanganStatusDAO() {
        return kodKewanganStatusDAO;
    }

    public void setKodKewanganStatusDAO(KodStatusTransaksiKewanganDAO kodKewanganStatusDAO) {
        this.kodKewanganStatusDAO = kodKewanganStatusDAO;
    }

    public GeneratorIdPermohonan getIdPermohonanGenerator() {
        return idPermohonanGenerator;
    }

    public void setIdPermohonanGenerator(GeneratorIdPermohonan idPermohonanGenerator) {
        this.idPermohonanGenerator = idPermohonanGenerator;
    }

    public GeneratorIdPerserahan getIdPerserahanGenerator() {
        return idPerserahanGenerator;
    }

    public void setIdPerserahanGenerator(GeneratorIdPerserahan idPerserahanGenerator) {
        this.idPerserahanGenerator = idPerserahanGenerator;
    }

    public GeneratorIdHakmilik getIdHakmilikGenerator() {
        return idHakmilikGenerator;
    }

    public void setIdHakmilikGenerator(GeneratorIdHakmilik idHakmilikGenerator) {
        this.idHakmilikGenerator = idHakmilikGenerator;
    }

    public GeneratorNoResit getNoResitGenerator() {
        return noResitGenerator;
    }

    public void setNoResitGenerator(GeneratorNoResit noResitGenerator) {
        this.noResitGenerator = noResitGenerator;
    }

    public KodStatusHakmilikDAO getKodStatusHakmilikDAO() {
        return kodStatusHakmilikDAO;
    }

    public void setKodStatusHakmilikDAO(KodStatusHakmilikDAO kodStatusHakmilikDAO) {
        this.kodStatusHakmilikDAO = kodStatusHakmilikDAO;
    }

    public SejarahHakmilikDAO getSejarahHakmilikDAO() {
        return sejarahHakmilikDAO;
    }

    public void setSejarahHakmilikDAO(SejarahHakmilikDAO sejarahHakmilikDAO) {
        this.sejarahHakmilikDAO = sejarahHakmilikDAO;
    }

    public HakmilikAsalPermohonanDAO getHakmilikAsalPermohonanDAO() {
        return hakmilikAsalPermohonanDAO;
    }

    public void setHakmilikAsalPermohonanDAO(HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO) {
        this.hakmilikAsalPermohonanDAO = hakmilikAsalPermohonanDAO;
    }

    public HakmilikSebelumPermohonanDAO getHakmilikSblmPermohonanDAO() {
        return hakmilikSblmPermohonanDAO;
    }

    public void setHakmilikSblmPermohonanDAO(HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO) {
        this.hakmilikSblmPermohonanDAO = hakmilikSblmPermohonanDAO;
    }

    public PermohonanPihakDAO getPermohonanPihakDAO() {
        return permohonanPihakDAO;
    }

    public void setPermohonanPihakDAO(PermohonanPihakDAO permohonanPihakDAO) {
        this.permohonanPihakDAO = permohonanPihakDAO;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public HakmilikUrusanService getHakmilikUrusanService() {
        return hakmilikUrusanService;
    }

    public void setHakmilikUrusanService(HakmilikUrusanService hakmilikUrusanService) {
        this.hakmilikUrusanService = hakmilikUrusanService;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public PeguamDAO getPeguamDAO() {
        return peguamDAO;
    }

    public void setPeguamDAO(PeguamDAO peguamDAO) {
        this.peguamDAO = peguamDAO;
    }

    public JUBLDAO getjUBLDAO() {
        return jUBLDAO;
    }

    public void setjUBLDAO(JUBLDAO jUBLDAO) {
        this.jUBLDAO = jUBLDAO;
    }

    public KodJabatanDAO getKodJabatanDAO() {
        return kodJabatanDAO;
    }

    public void setKodJabatanDAO(KodJabatanDAO kodJabatanDAO) {
        this.kodJabatanDAO = kodJabatanDAO;
    }

    public KodCawanganJabatanDAO getKodCawanganJabatanDAO() {
        return kodCawanganJabatanDAO;
    }

    public void setKodCawanganJabatanDAO(KodCawanganJabatanDAO kodCawanganJabatanDAO) {
        this.kodCawanganJabatanDAO = kodCawanganJabatanDAO;
    }

    public KodPenyerahDAO getKodPenyerahDAO() {
        return kodPenyerahDAO;
    }

    public void setKodPenyerahDAO(KodPenyerahDAO kodPenyerahDAO) {
        this.kodPenyerahDAO = kodPenyerahDAO;
    }

    public KodAgensiDAO getKodAgensiDAO() {
        return kodAgensiDAO;
    }

    public void setKodAgensiDAO(KodAgensiDAO kodAgensiDAO) {
        this.kodAgensiDAO = kodAgensiDAO;
    }

    public PihakService getPihakService() {
        return pihakService;
    }

    public void setPihakService(PihakService pihakService) {
        this.pihakService = pihakService;
    }

    public PermohonanUrusanDAO getPermohonanUrusanDAO() {
        return permohonanUrusanDAO;
    }

    public void setPermohonanUrusanDAO(PermohonanUrusanDAO permohonanUrusanDAO) {
        this.permohonanUrusanDAO = permohonanUrusanDAO;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public HakmilikPihakKepentinganService getHpkService() {
        return hpkService;
    }

    public void setHpkService(HakmilikPihakKepentinganService hpkService) {
        this.hpkService = hpkService;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public HakmilikService getHakmilikService() {
        return hakmilikService;
    }

    public void setHakmilikService(HakmilikService hakmilikService) {
        this.hakmilikService = hakmilikService;
    }

    public StrataPtService getStrataService() {
        return strataService;
    }

    public void setStrataService(StrataPtService strataService) {
        this.strataService = strataService;
    }

    public KodHakmilikDAO getKodHakmilikDAO() {
        return kodHakmilikDAO;
    }

    public void setKodHakmilikDAO(KodHakmilikDAO kodHakmilikDAO) {
        this.kodHakmilikDAO = kodHakmilikDAO;
    }

    public StringBuilder getCatatan() {
        return catatan;
    }

    public void setCatatan(StringBuilder catatan) {
        this.catatan = catatan;
    }
}
