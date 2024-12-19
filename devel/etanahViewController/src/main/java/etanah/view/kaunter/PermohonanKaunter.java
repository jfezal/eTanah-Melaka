package etanah.view.kaunter;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import able.stripes.AbleActionBean;

import com.google.inject.Inject;

import etanah.Configuration;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KodAgensiDAO;
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
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
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
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Peguam;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.UrusanDokumen;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.sequence.GeneratorNoResit;
import etanah.service.AkaunService;
import etanah.service.KaunterService;
import etanah.service.common.PihakService;
import etanah.service.daftar.SuratWakilKuasaService;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;
import etanah.workflow.WorkFlowService;
import org.apache.commons.lang.StringUtils;
import etanah.view.stripes.hasil.PenyataPemungutService;

@Deprecated
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1", "editUrusan", "Step6b"})
@UrlBinding("/kaunter/permohonan_deprecated")
public class PermohonanKaunter extends AbleActionBean {

    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    private char tambahUrusan = 'T';
    // for deleting/editing urusan
    private int selectedItem = -1;
    // DISPLAY OBJECTS
    // Urusan Value Object (used in Step1 & Step6a)
    private ArrayList<UrusanValue> senaraiUrusan = new ArrayList<UrusanValue>();
    // Dokumen submitted by check list (used in Step 2)
    private ArrayList<DokumenValue> senaraiDokumenSerahan = new ArrayList<DokumenValue>();
    private int suratSABKuantiti = 0;
    private ArrayList<String> suratSAD = new ArrayList<String>();
    private int suratSWBKuantiti = 0;
    private ArrayList<String> suratSWD = new ArrayList<String>();
    private int suratSBBKuantiti = 0;
    private ArrayList<String> suratSBD = new ArrayList<String>();
    //Urusan Syarikat MCL
    private int SMKuantiti = 0;
    private ArrayList<String> smPerserahan = new ArrayList<String>();
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
    private static final Map<String, String[]> URUSAN_INFO = new HashMap<String, String[]>();
    // information on Penyerah (Step5)
    private String idPenyerah;
    private KodPenyerah penyerahKod;
    private Pengguna pguna;
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
    private boolean ptg = true;
    private String mod = "";
    // list of payment methods for the Urusan (used in Step6)
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    // value object of amount of charges (used in Step6)
    private BigDecimal jumlahCaj;
    // senarai permohonan for display in Step7. Being initialized in save() method (used in Step7)
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
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
    //@Inject
    //private UrusanFeeCalculator urusanFeeCalculator;
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
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    PenyataPemungutService pp;
    // No. generator for Surat Kuasa Wakil
    @Inject
    private etanah.sequence.GeneratorIdWakil idWakilGenerator;
    @Inject
    private GeneratorNoResit noResitGenerator;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private JUBLDAO jUBLDAO;
    @Inject
    private KodJabatanDAO kodJabatanDAO;
    @Inject
    KodCawanganJabatanDAO kodCawanganJabatanDAO;
    @Inject
    private KodAgensiDAO kodAgensiDAO;
    @Inject
    private PihakService pihakService;
    @Inject
    private PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    KodKutipanDAO kodKutipanDAO;
    @Inject
    private SuratWakilKuasaService suratWakilKuasaService;
    @Inject
    private UrusanValidator urusanValidator;
    @Inject
    private UrusanPostProcess urusanPostProcessor;
    private String resitNo;

    // class to cache urusan (before registering a new one)
    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiUrusan;
        ArrayList<DokumenValue> senaraiDokumenSerahan;
        private int suratSABKuantiti = 0;
        private ArrayList<String> suratSAD = new ArrayList<String>();
        private int suratSWBKuantiti = 0;
        private ArrayList<String> suratSWD = new ArrayList<String>();
        private int suratSBBKuantiti = 0;
        private ArrayList<String> suratSBD = new ArrayList<String>();
        //Urusan SM
        private int SMKuantiti = 0;
        private ArrayList<String> smPerserahan = new ArrayList<String>();
        ArrayList<DokumenValue> senaraiDokumenTamb;
        ArrayList<HakmilikPermohonan> hakmilikPermohonan;
        ArrayList<String> idHakmilikSiriDari;
        ArrayList<String> idHakmilikSiriKe;
    }
    private static final Logger LOG = Logger.getLogger(PermohonanKaunter.class);
    private static final boolean debug = LOG.isDebugEnabled();

    static {
        // loading configuration file: kaunterUrusanInfo
        Properties p = new Properties();
        try {
            p.load(Configuration.class.getResourceAsStream("/kaunterUrusanInfo.properties"));
            Set<String> setKodUrusan = p.stringPropertyNames();
            for (Iterator<String> i = setKodUrusan.iterator(); i.hasNext();) {
                String kodUrusan = i.next();
                if (debug) {
                    LOG.debug("Configuration loading " + kodUrusan);
                }
                String info = p.getProperty(kodUrusan);
                if (debug) {
                    LOG.debug("Configuration for " + kodUrusan + ":" + info);
                }
                if (info == null || info.length() == 0) {
                    LOG.warn("No configuration for " + kodUrusan);
                    continue;
                }
                String[] infos = info.split("\\|");
                if (debug) {
                    LOG.debug("infos.length " + infos.length);
                }
                if (infos.length != 3) {
                    String[] b = new String[3];
                    for (int x = 0; x < infos.length && x < 3; x++) {
                        b[x] = infos[x];
                    }
                    infos = b;
                }
                for (int j = 0; j < 3; j++) {
                    if (infos[j] != null) {
                        infos[j] = infos[j].trim();
                        if (infos[j].length() == 0) {
                            infos[j] = null;
                        }
                    }
                }
                URUSAN_INFO.put(kodUrusan, infos);
            }

        } catch (Exception e) {
            LOG.fatal("Configuration for Kaunter cannot be loaded!");
            LOG.fatal(e.getMessage(), e);
        }

    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return selectedItem;
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

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
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

    // label for screen
    public String getSenaraiUrusanLabel() {
        StringBuilder sb = new StringBuilder();
        if (senaraiUrusan != null) {
            int sz = senaraiUrusan.size();
            for (int i = 0; i < sz; i++) {
                if (senaraiUrusan.get(i).kodUrusan == null) {
                    continue;
                }
                sb.append(senaraiUrusan.get(i).getNamaUrusan()).append(", ");
            }
        }
        sb.setLength(sb.length() - 2); // remove last comma

        return sb.toString();
    }

    /**
     * Get the checklist Dokumen for the given list Urusan.
     *
     * @return
     */
    public List<UrusanDokumen> getSenaraiDokumen() {
        return getSenaraiDokumen(senaraiUrusan);
    }

    public List<UrusanDokumen> getSenaraiDokumen(ArrayList<UrusanValue> senaraiUrusan) {
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        for (UrusanValue u : senaraiUrusan) {
            senaraiKodUrusan.add(u.getKodUrusan());
        }

        return kaunterService.getUrusanDokumen(senaraiKodUrusan);
    }

    public int getSMKuantiti() {
        return SMKuantiti;
    }

    public void setSMKuantiti(int SMKuantiti) {
        this.SMKuantiti = SMKuantiti;
    }

    public ArrayList<String> getSmPerserahan() {
        return smPerserahan;
    }

    public void setSmPerserahan(ArrayList<String> smPerserahan) {
        this.smPerserahan = smPerserahan;
    }

    public int getSuratSABKuantiti() {
        return suratSABKuantiti;
    }

    public void setSuratSABKuantiti(int suratSABKuantiti) {
        this.suratSABKuantiti = suratSABKuantiti;
    }

    public ArrayList<String> getSuratSAD() {
        return suratSAD;
    }

    public void setSuratSAD(ArrayList<String> suratSAD) {
        this.suratSAD = suratSAD;
    }

    public int getSuratSWBKuantiti() {
        return suratSWBKuantiti;
    }

    public void setSuratSWBKuantiti(int suratSWBKuantiti) {
        this.suratSWBKuantiti = suratSWBKuantiti;
    }

    public ArrayList<String> getSuratSWD() {
        return suratSWD;
    }

    public void setSuratSWD(ArrayList<String> suratSWD) {
        this.suratSWD = suratSWD;
    }

    public int getSuratSBBKuantiti() {
        return suratSBBKuantiti;
    }

    public void setSuratSBBKuantiti(int suratSBBKuantiti) {
        this.suratSBBKuantiti = suratSBBKuantiti;
    }

    public ArrayList<String> getSuratSBD() {
        return suratSBD;
    }

    public void setSuratSBD(ArrayList<String> suratSBD) {
        this.suratSBD = suratSBD;
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

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
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

    public String getResitNo() {
        return resitNo;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {
        if (debug) {
            LOG.debug("tambahUrusan=" + tambahUrusan);
        }
        if (tambahUrusan == 'Y') {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
        }

        resetAll();
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        pguna = new Pengguna();
        pguna = ctx.getUser();
        if (debug) {
            LOG.debug("pguna.getKodCawangan().getKod() : " + pguna.getKodCawangan().getKod());
        }
        if (pguna.getKodCawangan().getKod().equals("00")) {
            ptg = false;
            if (debug) {
                LOG.debug("ptg : " + ptg);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
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
        // validate if any urusan selected
        UrusanValue u = senaraiUrusan.get(0);
        if (u == null || u.getKodUrusan() == null || "0".equals(u.getKodUrusan())) {
            addSimpleError("Sila pilih Kod Urusan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }
        for (UrusanValue uv : senaraiUrusan) {
            if (uv.kodUrusan != null) {
                KodUrusan ku = kodUrusanDAO.findById(uv.kodUrusan);
                if (ku == null || ku.getUrusanKaunter() != 'Y') {
                    addSimpleError("Urusan tidak sah di Kaunter.");
                    return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
                }
                uv.kodUrusan = ku.getKod();
                uv.kodJabatan = ku.getJabatan().getKod();
                uv.kodUrusanPilih = ku.getKod();
            }
        }

        // for editing urusan purpose
        if (selectedItem >= 0) {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            List<UrusanCache> luc = getSessionCache(ctx);
            // checklist dokumen
            if (senaraiDokumenSerahan == null || senaraiDokumenSerahan.size() == 0) {
                if (luc != null) {
                    senaraiDokumenSerahan = luc.get(selectedItem).senaraiDokumenSerahan;
                }
            }
            // checklist dokumen tamb
            if (senaraiDokumenTamb == null || senaraiDokumenTamb.size() == 0) {
                if (luc != null) {
                    senaraiDokumenTamb = luc.get(selectedItem).senaraiDokumenTamb;
                }
            }

        }

        // reset the additional documents submitted to 10 if none
        if (senaraiDokumenTamb.size() == 0) {
            for (int i = 0; i < 5; i++) {
                DokumenValue d = new DokumenValue();
                senaraiDokumenTamb.add(d);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan.jsp");
    }

    @HandlesEvent("Step3")
    @DontValidate
    public Resolution selectTitles() {
        // validate all the mandatory documents submitted
        List<UrusanDokumen> lud = getSenaraiDokumen();
        if (lud.size() > 0) {
            for (UrusanDokumen ud : lud) {
                if (ud.getWajib() == 'Y') {
                    boolean fd = false;
                    for (DokumenValue d : senaraiDokumenSerahan) {
                        if (d != null
                                && d.kodDokumen != null
                                && ud.getKodDokumen().getKod().equals(d.kodDokumen)) {
                            fd = true;
                            break;
                        }
                    }
                    if (!fd) {
                        // as per requirement from Hanim/Azliza/Sherina(JKPTG), can proceed with
                        // submission even without mandatory documents
                        // 21/4 However, NRE has reverted the decision 
                        addSimpleError("AMARAN: Dokumen yang mandatori (" + ud.getKodDokumen().getNama()
                                + ") untuk urusan tidak diserahkan! Ini boleh mengakibatkan "
                                + "Permohonan/Perserahan ditolak!");
                        return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan.jsp");
                    }

                }
            }
        }

        // validation of Surat2 Berdaftar
        if (suratSWD != null && suratSWD.size() > 0) {
            ArrayList<String> listSurat = new ArrayList<String>();
            // clean up the list
            for (String s : suratSWD) {
                if (s == null || s.trim().length() == 0) {
                    continue;
                }

                listSurat.add(s);
            }

            List<String> invalidSurat = suratWakilKuasaService.validateNoSurat(listSurat);
            if (invalidSurat != null && invalidSurat.size() > 0) {
                StringBuilder msg = new StringBuilder();
                for (String s : invalidSurat) {
                    if (msg.length() > 0) {
                        msg.append(", ");
                    }
                    msg.append(s);
                }
                addSimpleMessage("AMARAN: Surat Kuasa Wakil ini tiada dalam rekod: " + msg);
                //return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan.jsp"); THERE ARE PRE-SPTBs
            }
        }

        // for editing urusan purpose
        if (selectedItem >= 0) {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            List<UrusanCache> luc = getSessionCache(ctx);
            // hakmilik terlibat
            if (hakmilikPermohonan == null || hakmilikPermohonan.size() == 0) {
                if (luc != null) {
                    hakmilikPermohonan = luc.get(selectedItem).hakmilikPermohonan;
                }
            }
            // hakmilik bersiri dari
            if (idHakmilikSiriDari == null || idHakmilikSiriDari.size() == 0) {
                if (luc != null) {
                    idHakmilikSiriDari = luc.get(selectedItem).idHakmilikSiriDari;
                }
            }
            // hakmilik bersiri ke
            if (idHakmilikSiriKe == null || idHakmilikSiriKe.size() == 0) {
                if (luc != null) {
                    idHakmilikSiriKe = luc.get(selectedItem).idHakmilikSiriKe;
                }
            }
        }

        // reset senaraiHakmilik
        if (hakmilikPermohonan.size() == 0) {
            for (int i = 0; i < bilHakmilik; i++) {
                HakmilikPermohonan h = new HakmilikPermohonan();
                hakmilikPermohonan.add(h);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
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
        /*
         // validate the urusan
         ArrayList<String> errs = new ArrayList<String>();
         for (UrusanValue uv : senaraiUrusan){
         if (isUrusanNull(uv)) continue;
    		
         KodUrusan ku = kodUrusanDAO.findById(uv.kodUrusan);
         String err = urusanValidator.validateUrusan(ku, hakmilikPermohonan);
         if (StringUtils.isNotBlank(err))
         errs.add(err);
         }
    	
         if (errs.size() > 0){
         for (String s: errs){
         addSimpleError(s);
         }
         return new ForwardResolution("/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
         }
         */

        // reset the urusan
        ArrayList<UrusanValue> list = new ArrayList<UrusanValue>();
        // no other specific info needed, skip this step
        boolean needInfo = false;
        for (UrusanValue uv : senaraiUrusan) {
            if (!isUrusanNull(uv)) {
                list.add(uv);
                String[] infos = URUSAN_INFO.get(uv.kodUrusan);
                if (infos != null) {
                    uv.labelAmaun1 = infos[0];
                    uv.labelTarikh1 = infos[1];
                    uv.labelNilai1 = infos[2];
                    needInfo = true;
                }
            }
        }
        senaraiUrusan = list;

        if (needInfo) {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/urusan_info_tamb.jsp");
        } else {
            return getAdditionalPaymentInfo();
        }
    }

    @HandlesEvent("Step5")
    @DontValidate
    public Resolution getAdditionalPaymentInfo() {
        // validate extra info
        for (UrusanValue uv : senaraiUrusan) {
            if (!isUrusanNull(uv)) {
                String[] infos = URUSAN_INFO.get(uv.kodUrusan);
                if (infos != null) {
                    LOG.debug(uv.amaun1 + " " + uv.tarikh1 + " " + uv.nilai1);
                    if ((infos[0] != null && uv.amaun1 == null)
                            || (infos[1] != null && uv.tarikh1 == null)
                            || (infos[2] != null && uv.nilai1 == null)) {
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/caj_tamb.jsp");
    }

    @HandlesEvent("Step6")
    @DontValidate
    public Resolution setPenyerah() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);
        // reset all values
        resetUrusan();

        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
    }

    @HandlesEvent("Step7")
    @DontValidate
    public Resolution collectPayment() {
        // validate Penyerah
        if (penyerahNama == null || penyerahNama.trim().length() == 0
                || penyerahAlamat1 == null || penyerahAlamat1.trim().length() == 0
                || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
            addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> txnGroups = getAllUrusanFromSession(ctx);
        if (txnGroups == null) {
            addSimpleError("Data simpanan sementara telah korup. Anda perlu mula semula.");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
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

        return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
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

    /**
     * From kesinambungan urusan
     *
     * @return
     */
    @HandlesEvent("Step6b")
    public Resolution suggestTransaction() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        selectedItem = -1;

        return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
    }

    @HandlesEvent("Step8")
    public Resolution save() {
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

        Dokumen resit = null;
        DokumenKewangan dk = null;
        // create DokumenKewangan
        // Resit is always created even if no charged involved (N9 asked no resit  if jumlah caj == 0)
        if (!BigDecimal.ZERO.equals(jumlahCaj)) {
            dk = new DokumenKewangan();
            dk.setCawangan(caw);
            dk.setKodDokumen(kodDokumenDAO.findById("RBY")); // TODO cache
            dk.setIsuKepada(penyerahNama);
            dk.setIdDokumenKewangan(noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("T"));
            dk.setTarikhTransaksi(new java.util.Date());
            dk.setIdKaunter(pengguna.getIdKaunter());
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(jumlahCaj);
            if (mod.equalsIgnoreCase("Lewat")) {
                dk.setMod(kodKutipanDAO.findById('L'));
            }
            if (mod.equalsIgnoreCase("Biasa")) {
                dk.setMod(kodKutipanDAO.findById('B'));
            }
            dk.setInfoAudit(ia);
        }

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        // if there is payments, save payment methods
        if (!jumlahCaj.equals(BigDecimal.ZERO)) {
            BigDecimal paid = BigDecimal.ZERO;
            for (CaraBayaran cb : senaraiCaraBayaran) {
                if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                        && cb.getAmaun() != null && !cb.getAmaun().equals(BigDecimal.ZERO)) {
                    paid = paid.add(cb.getAmaun());
                }
            }
            // validate amounts paid
            if (jumlahCaj.compareTo(paid) != 0) {
                if (jumlahCaj.compareTo(paid) > 0) {
                    addSimpleError("Amaun dibayar kurang daripada jumlah Caj dikenakan.");
                }
                if (jumlahCaj.compareTo(paid) < 0) {
                    addSimpleError("Amaun dibayar melebihi daripada jumlah Caj dikenakan.");
                }

                return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
            }

            // save cara bayaran
            saveCaraBayaran(caw, dk, ia);
        }

        // save the resit
        if (!BigDecimal.ZERO.equals(jumlahCaj)) {
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
        }

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
                        KodDokumen kd = kodDokumenDAO.findById(dv.kodDokumen);
                        // saving noRujukan by comma delimited
                        String[] listTajuk = null;
                        if (dv.noRujukan != null && dv.noRujukan.length() > 0) {
                            listTajuk = dv.noRujukan.split(",");
                        }

                        // save for every copy
                        for (int j = 0; j < dv.getBil(); j++) {
                            Dokumen d = new Dokumen();
                            d.setKodDokumen(kd);
                            d.setInfoAudit(ia);
                            if (listTajuk != null && j < listTajuk.length && listTajuk[j] != null) {
                                String noRujukan = listTajuk[j].trim();
                                d.setTajuk(kd.getKod() + " " + noRujukan);
                                d.setNoRujukan(noRujukan);
                            } else {
                                d.setTajuk(kd.getNama());
                            }
                            d.setNoVersi("1.0");
                            d.setKlasifikasi(klasifikasiAm);

                            // generate No.Rujukan for SWB, SAB (todo), SBB (todo)
                            if (kd != null && "SWB".equals(kd.getKod())) {
                                String noSWB = idWakilGenerator.generate(
                                        kodNegeri, pengguna.getKodCawangan(), null);
                                d.setNoRujukan(noSWB);
                            }

                            dokumenDAO.save(d);
                            KandunganFolder k = new KandunganFolder();
                            k.setFolder(fd);
                            k.setDokumen(d);
                            k.setInfoAudit(ia);
                            akf.add(k);
                        }
                    }
                }
                // additional documents
                if (debug) {
                    LOG.debug("u.senaraiDokumenTamb != null?" + (u.senaraiDokumenTamb != null));
                }
                if (u.senaraiDokumenTamb != null) {
                    for (DokumenValue dv : u.senaraiDokumenTamb) {
                        if (dv == null || dv.kodDokumen == null) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        KodDokumen kd = kodDokumenDAO.findById(dv.getKodDokumen());

                        String c = dv.catatan;
                        if ((kd != null && !kd.getKod().equals("0"))
                                || (c != null && c.trim().length() > 0)) {
                            if (kd != null && kd.getKod().equals("0")) { // the type not set
                                d.setKodDokumen(null);
                                d.setTajuk("KIV");
                            } else {
                                if (kd == null) {
                                    d.setTajuk("KIV");
                                } else {
                                    d.setKodDokumen(kd);
                                    d.setTajuk(kd.getNama());
                                }
                            }
                            d.setInfoAudit(ia);
                            d.setNoVersi("1.0");
                            d.setKlasifikasi(klasifikasiAm);
                            dokumenDAO.save(d);
                            KandunganFolder f = new KandunganFolder();
                            f.setCatatan(dv.getCatatan());
                            f.setDokumen(d);
                            f.setFolder(fd);
                            f.setInfoAudit(ia);
                            akf.add(f);
                        }
                    }
                }
                // surat kuasa wakil
                LOG.debug("suratSWBKuantiti=" + u.suratSWBKuantiti);
                if (u.suratSWBKuantiti > 0) {
                    KodUrusan kodUrusan = kodUrusanDAO.findById("SW");
                    KodDokumen kdSW = kodDokumenDAO.findById("SWB");

                    for (int i = 0; i < u.suratSWBKuantiti; i++) {
                        // generate permohonan Surat Kuasa Wakil Baru
                        UrusanValue uv = etanahContextListener.newInstance(UrusanValue.class);
                        uv.kodJabatan = "16";
                        uv.kodUrusan = "SW";
                        Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, false, null, fd,
                                null, null, null, ia);
                        uv.idPermohonan = p.getIdPermohonan();
                        mapPermohonan.put(p.getIdPermohonan(), p);
                        senaraiPermohonan.add(uv);

                        // add to folder
                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSW);
                        d.setInfoAudit(ia);
                        d.setTajuk("SW " + uv.idPermohonan);
                        d.setNoRujukan(uv.idPermohonan);
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
                if (u.suratSWD != null) {
                    KodDokumen kdSW = kodDokumenDAO.findById("SWD");

                    for (String surat : u.suratSWD) {
                        if (surat == null || surat.trim().length() == 0) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSW);
                        d.setInfoAudit(ia);
                        d.setTajuk("SW " + surat);
                        d.setNoRujukan(surat);
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
                // surat amanah
                if (u.suratSABKuantiti > 0) {
                    KodUrusan kodUrusan = kodUrusanDAO.findById("SA");
                    KodDokumen kdSA = kodDokumenDAO.findById("SAB");

                    for (int i = 0; i < u.suratSABKuantiti; i++) {
                        // generate permohonan Surat Kuasa Wakil Baru
                        UrusanValue uv = etanahContextListener.newInstance(UrusanValue.class);
                        uv.kodJabatan = "16";
                        uv.kodUrusan = "SA";
                        Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, false, null, fd,
                                null, null, null, ia);
                        uv.idPermohonan = p.getIdPermohonan();
                        mapPermohonan.put(p.getIdPermohonan(), p);
                        senaraiPermohonan.add(uv);

                        // add to folder
                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSA);
                        d.setInfoAudit(ia);
                        d.setTajuk("SA " + uv.idPermohonan);
                        d.setNoRujukan(uv.idPermohonan);
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
                if (u.suratSAD != null) {
                    KodDokumen kdSA = kodDokumenDAO.findById("SAD");

                    for (String surat : u.suratSAD) {
                        if (surat == null || surat.trim().length() == 0) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSA);
                        d.setInfoAudit(ia);
                        d.setTajuk("SA " + surat);
                        d.setNoRujukan(surat);
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
                // surat kebenaran
                if (u.suratSBBKuantiti > 0) {
                    KodUrusan kodUrusan = kodUrusanDAO.findById("SB");
                    KodDokumen kdSB = kodDokumenDAO.findById("SBB");

                    for (int i = 0; i < u.suratSBBKuantiti; i++) {
                        // generate permohonan Surat Kuasa Wakil Baru
                        UrusanValue uv = etanahContextListener.newInstance(UrusanValue.class);
                        uv.kodJabatan = "16";
                        uv.kodUrusan = "SB";
                        Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, false, null, fd,
                                null, null, null, ia);
                        uv.idPermohonan = p.getIdPermohonan();
                        mapPermohonan.put(p.getIdPermohonan(), p);
                        senaraiPermohonan.add(uv);

                        // add to folder
                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSB);
                        d.setInfoAudit(ia);
                        d.setTajuk("SB " + uv.idPermohonan);
                        d.setNoRujukan(uv.idPermohonan);
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
                if (u.suratSBD != null) {
                    KodDokumen kdSB = kodDokumenDAO.findById("SBD");

                    for (String surat : u.suratSBD) {
                        if (surat == null || surat.trim().length() == 0) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSB);
                        d.setInfoAudit(ia);
                        d.setTajuk("SB " + surat);
                        d.setNoRujukan(surat);
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

                //Added by Aizuddin for SM
                if (u.SMKuantiti > 0) {
                    KodUrusan kodUrusan = kodUrusanDAO.findById("SMD");
                    KodDokumen kdSA = kodDokumenDAO.findById("SMB");

                    for (int i = 0; i < u.SMKuantiti; i++) {
                        // generate permohonan Surat Kuasa Wakil Baru
                        UrusanValue uv = etanahContextListener.newInstance(UrusanValue.class);
                        uv.kodJabatan = "16";
                        uv.kodUrusan = "SMD";
                        Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, false, null, fd,
                                null, null, null, ia);
                        uv.idPermohonan = p.getIdPermohonan();
                        mapPermohonan.put(p.getIdPermohonan(), p);
                        senaraiPermohonan.add(uv);

                        // add to folder
                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSA);
                        d.setInfoAudit(ia);
                        d.setTajuk("SM " + uv.idPermohonan);
                        d.setNoRujukan(uv.idPermohonan);
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
                if (u.smPerserahan != null) {
                    KodDokumen kdSA = kodDokumenDAO.findById("SMB");

                    for (String surat : u.smPerserahan) {
                        if (surat == null || surat.trim().length() == 0) {
                            continue;
                        }

                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kdSA);
                        d.setInfoAudit(ia);
                        d.setTajuk("SM " + surat);
                        d.setNoRujukan(surat);
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
                //End for save SM dokumen

                // attach resit to folder
                if (!BigDecimal.ZERO.equals(jumlahCaj)) {
                    KandunganFolder kf2 = new KandunganFolder();
                    kf2.setFolder(fd);
                    kf2.setDokumen(resit);
                    kf2.setInfoAudit(ia);
                    akf.add(kf2);
                }

                if (akf.size() > 0) {
                    fd.setSenaraiKandungan(akf);
                }
                folderDokumenDAO.save(fd);

                // WARNING: folder.tajuk column is 200 bytes max
                StringBuilder tajukFolder = new StringBuilder();
                int cnt = 0;
                String[] idKumpulan = new String[]{null}; // made as array so that modifiable inside savePermohonan
                // check berangkai or not
                int cntBerangkai = 0;
                for (UrusanValue ku : u.senaraiUrusan) {
                    if (!isUrusanNull(ku)) {
                        cntBerangkai++;
                    }
                }

                ArrayList<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
                // processing Hakmilik
                if (u.hakmilikPermohonan != null && u.hakmilikPermohonan.size() > 0) {
                    for (HakmilikPermohonan hp : u.hakmilikPermohonan) {
                        if (hp == null) {
                            continue;
                        }
                        Hakmilik hm = hp.getHakmilik();
                        if (hm != null && hm.getIdHakmilik() != null
                                && hm.getIdHakmilik().trim().length() > 0) {
                            String id = hm.getIdHakmilik();
                            hm = hakmilikDAO.findById(id);
                            if (hm == null) {
                                throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                            }
                            listHakmilik.add(hm);
                        }
                    }
                }

                // processing hakmilik bersiri
                if (u.idHakmilikSiriDari != null && u.idHakmilikSiriKe != null) {
                    for (int i = 0; i < u.idHakmilikSiriDari.size(); i++) {
                        String idH1 = u.idHakmilikSiriDari.get(i);
                        String idH2 = u.idHakmilikSiriKe.get(i);

                        if (idH1 == null || idH1.trim().length() == 0
                                || idH2 == null || idH2.trim().length() == 0) {
                            continue;
                        }

                        ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);

                        List<Hakmilik> listId = sessionProvider.get().createQuery(
                                "select h from Hakmilik h inner join fetch h.senaraiAkaun a "
                                + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();
                        listHakmilik.addAll(listId);
                    }
                }


                // adding Permohonan
                boolean isBerangkai = cntBerangkai > 1;
                Permohonan permohonanBerangkaiSebelum = null;
                for (cnt = 0; cnt < u.senaraiUrusan.size(); cnt++) {
                    UrusanValue uv = u.senaraiUrusan.get(cnt);
                    if (isUrusanNull(uv)) {
                        continue;
                    }

                    /**
                     * Permohonan lama ialah permohonan sebelum yang ada kaitan
                     * dgn permohonan sekarang.
                     */
                    String idPermohonanLama = uv.idPermohonanSebelum;
                    Permohonan permohonanLama = null;
                    if (idPermohonanLama != null) {
                        permohonanLama = permohonanDAO.findById(idPermohonanLama);
                        if (permohonanLama == null) {
                            LOG.error("ID Permohonan " + idPermohonanLama
                                    + " yang diberikan tidak wujud!");
                            throw new RuntimeException("ID Permohonan/Perserahan " + idPermohonanLama
                                    + " yang diberikan tidak wujud!");
                        }
                    }

                    // find the jabatan for urusan
                    KodUrusan kodUrusan = kodUrusanDAO.findById(uv.getKodUrusan());

                    if (kodUrusan == null) {
                        addSimpleError("Urusan \"" + uv + "\" tidak dijumpai!");
                        throw new RuntimeException("Urusan \"" + uv + "\" tidak dijumpai!");
                    }
                    boolean perluJelasCukai = (kodUrusan.getPerluJelasCukai() == 'Y');
                    if (perluJelasCukai) {
                        for (Hakmilik hm : listHakmilik) {
                            Akaun ac = hm.getAkaunCukai();
                            if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
                                throw new RuntimeException("ID Hakmilik " + hm.getIdHakmilik()
                                        + " masih belum menjelaskan Cukai. Urusan \""
                                        + kodUrusan.getNama()
                                        + "\" memerlukan cukai dijelaskan.");
                            }
                        }
                    }



                    if (debug) {
                        LOG.debug("adding urusan:" + uv + " for jabatan " + kodUrusan.getJabatanNama());
                    }

                    Permohonan p = savePermohonan(ctx.getKodNegeri(), pengguna, kodUrusan, uv, isBerangkai, idKumpulan, fd,
                            permohonanLama, permohonanBerangkaiSebelum, listHakmilik, ia);
                    //for sorting purposes
                    int i = cnt + 1;
                    p.setKumpulanNo(i);
                    permohonanDAO.save(p);
                    mapPermohonan.put(p.getIdPermohonan(), p);
                    uv.idPermohonan = p.getIdPermohonan();
                    permohonanBerangkaiSebelum = p;

                    // handling special urusan
                    // TODO move to UrusanPostProcess
                    PermohonanUrusan pu = null;
                    String[] infos = URUSAN_INFO.get(uv.kodUrusan);
                    if (infos != null && infos[0] != null && "Amaun Transaksi (RM)".equalsIgnoreCase(infos[0])) {
                        pu = new PermohonanUrusan();
                        pu.setCawangan(caw);
                        pu.setPermohonan(p);
                        pu.setInfoAudit(ia);
                        pu.setPerjanjianAmaun(uv.amaun1);
                    }
                    if (infos != null && infos[1] != null && "Tarikh Penyaksian".equalsIgnoreCase(infos[1])) {
                        if (pu == null) {
                            pu = new PermohonanUrusan();
                            pu.setCawangan(caw);
                            pu.setPermohonan(p);
                            pu.setInfoAudit(ia);
                        }
                        pu.setTarikhSaksi(uv.tarikh1);
                    }
                    if (pu != null) {
                        permohonanUrusanDAO.save(pu);
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
                    fd.getSenaraiKandungan().add(kf1);
                    uv.akuanPenerimaan = akuanPenerimaan;

                    // post processing
                    urusanPostProcessor.performPostProcess(uv, p, listHakmilik, atv, ia);

                    senaraiPermohonan.add(uv);
                    cntPermohonan++;
                }

                fd.setTajuk(tajukFolder.toString());
                folderDokumenDAO.save(fd);
            }

            if (!BigDecimal.ZERO.equals(jumlahCaj) && atv.size() > 0) {
                for (TransaksiValue tv : atv) {
                    if (tv.kodTransaksi == null) {
                        if (tv.amaun.compareTo(BigDecimal.ZERO) > 0) {
                            LOG.warn("No KodTransaksi but amount is > 0");
                        }
                    }
                    // create txn
                    KodTransaksi kt = kodTransaksiDAO.findById(tv.kodTransaksi);
                    if (kt != null) {
                        Transaksi t = new Transaksi();
                        t.setCawangan(caw);
                        t.setKodTransaksi(kt);
                        t.setAmaun(tv.amaun);
                        t.setKodUrusan(tv.kodUrusan);
                        t.setPerihal(tv.namaUrusan);
                        t.setStatus(kodKewanganStatusDAO.findById('T'));
                        t.setAkaunDebit(akTerima);
                        if (tv.akaunKredit != null) {
                            t.setAkaunKredit(tv.akaunKredit);
                        }
                        t.setUntukTahun(year);
                        t.setKuantiti(tv.kuantiti);
                        t.setInfoAudit(ia);
                        t.setDokumenKewangan(dk);
                        if (tv.utkUrusan != null) {
                            for (UrusanValue uv : senaraiPermohonan) {
                                if (tv.utkUrusan == uv) {
                                    t.setPermohonan(mapPermohonan.get(uv.idPermohonan));
                                    break;
                                }
                            }
                        }
                        transaksiDAO.save(t);
                        if (t.getAmaun() != null && t.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                            pp.savePenyataPemungut(dk);
                        }
                    }
                }
            }

            // update akaun kutipan harian
            if (!jumlahCaj.equals(BigDecimal.ZERO)) {
                s.lock(akTerima, LockMode.UPGRADE);
                akTerima.setBaki(akTerima.getBaki().add(jumlahCaj));

                resitNo = dk.getIdDokumenKewangan();
            }

            tx.commit();

            tx = s.beginTransaction();

            if (!BigDecimal.ZERO.equals(jumlahCaj)) {
                // generate report for resit
                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
                String path2 = "RESIT" + File.separator + String.valueOf(resit.getIdDokumen());
                if (debug) {
                    LOG.debug("HSLResitPelbagai" + path);
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                            new String[]{"p_id_kew_dok"},
                            new String[]{resitNo},
                            path + path2, pengguna);
                } else {
                    reportUtil.generateReport("HSLResitUrusanTanah.rdf",
                            new String[]{"p_id_kew_dok"},
                            new String[]{resitNo},
                            path + path2, pengguna);
                }
                resit.setNamaFizikal(reportUtil.getDMSPath());
                getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
                dokumenDAO.update(resit);
            }

            // generate Surat Akuan Penerimaan for each Permohonan
            for (UrusanValue uv : senaraiPermohonan) {
                if (uv.akuanPenerimaan == null) {
                    continue;
                }

                String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
                String path2 = "AkuanPenerimaan" + File.separator + uv.akuanPenerimaan.getIdDokumen();
                if (debug) {
                    LOG.debug("HSLResitAkuanPenerimaan=" + path + path2);
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002_MLK.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.idPermohonan},
                            path + path2, pengguna);
                } else {
                    reportUtil.generateReport("HSLResitAkuanPenerimaan002.rdf",
                            new String[]{"p_id_mohon"},
                            new String[]{uv.idPermohonan},
                            path + path2, pengguna);
                }
                uv.akuanPenerimaan.setNamaFizikal(reportUtil.getDMSPath());
                dokumenDAO.update(uv.akuanPenerimaan);
            }

            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());

            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
        } finally {
        }


        for (UrusanValue u : senaraiPermohonan) {
            LOG.debug(("initiating task " + u.idPermohonan));
            KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan());
            try {
                if (ku.getKePTG() == 'Y') {
                    WorkFlowService.initiateTask(ku.getIdWorkflow(),
                            u.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
                            ku.getNama());
                } else if (ku.getKePTG() == 'T') {
                    WorkFlowService.initiateTask(ku.getIdWorkflow(),
                            u.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                            ku.getNama());
                }

            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

        /*
         // workflow
         // TODO: put some error handling code (retry etc)
         for (UrusanCache uc : txnGroups) {
         for (int i = 0; i < uc.senaraiUrusan.size(); i++) {
         UrusanValue u = uc.senaraiUrusan.get(i);
         if (isUrusanNull(u)) {
         continue;
         }
         KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan());
         try {
         if(ku.getKePTG() == 'Y'){
         WorkFlowService.initiateTask(ku.getIdWorkflow(),
         u.getIdPermohonan(), caw.getKod() + ",00", pengguna.getIdPengguna(),
         ku.getNama());
         }else if(ku.getKePTG() == 'T'){
         WorkFlowService.initiateTask(ku.getIdWorkflow(),
         u.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
         ku.getNama());
         }
	                
         } catch (Exception e) {
         LOG.error(e.getMessage(), e);
         }
         }
         }
         */

        // clear session
        ctx.removeWorkdata();

        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit.jsp");
    }

    @HandlesEvent("editUrusan")
    @DontValidate
    public Resolution editUrusanCache() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanCache> luc = getSessionCache(ctx);

        if (selectedItem < 0 || selectedItem >= luc.size()) {
            addSimpleError("Item tidak sah");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
        }

        loadUrusan(selectedItem);

        return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
    }

    @HandlesEvent("removeUrusan")
    @DontValidate
    public Resolution removeUrusanCache() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanCache> luc = getSessionCache(ctx);

        if (selectedItem < 0 || selectedItem >= luc.size()) {
            addSimpleError("Item tidak sah");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
        }

        luc.remove(selectedItem);

        if (luc.size() > 0) {
            // need to update UrusanValue.position
            for (int i = 0; i < luc.size(); i++) {
                UrusanCache uc = luc.get(i);
                for (UrusanValue uv : uc.senaraiUrusan) {
                    uv.position = i;
                }
            }
        } else { // no more Urusan to register
            addSimpleMessage("Tiada urusan untuk didaftarkan");
            return selectTransaction();
        }

        addSimpleMessage("Urusan yang dipilih telah dihapuskan.");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran.jsp");
    }

    public Resolution updatePenyerah() {
        String kod = getContext().getRequest().getParameter("kod");
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
                    KodCawanganJabatan kcj = kodCawanganJabatanDAO.findById(idPenyerah);
                    if (kcj != null) {
                        kcj.setNama(penyerahNama);
                        kcj.setAlamat1(penyerahAlamat1);
                        kcj.setAlamat2(penyerahAlamat2);
                        kcj.setAlamat3(penyerahAlamat3);
                        kcj.setAlamat4(penyerahAlamat4);
                        kcj.setPoskod(penyerahPoskod);
                        kcj.setNegeri(penyerahNegeri);
                        kcj.setNoTelefon1(penyerahNoTelefon);
                        kodCawanganJabatanDAO.update(kcj);
                    }
                } else if ("05".equals(penyerahKod.getKod())) {
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
            addSimpleMessage("Kemaskini data berjaya.");
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Kemaskini data tidak berjaya.");
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
    }

    @HandlesEvent("Cancel")
    public Resolution cancel() {
        resetUrusan();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ArrayList<UrusanCache> senaraiUrusan = getAllUrusanFromSession(ctx);

        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/penyerah.jsp");
        }
    }

    @HandlesEvent("CancelAll")
    public Resolution cancelAll() {
        resetAll();

        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
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
     * @param hakmilikPermohonan
     * @param idHakmilikSiriDari
     * @param idHakmilikSiriKe
     * @return
     */
    private String getSenaraiHakmilikUrusan(ArrayList<HakmilikPermohonan> hakmilikPermohonan,
            List<String> listHakmilikBersiri) {

        StringBuilder list = new StringBuilder();
        int s = 0;
        if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
            s = hakmilikPermohonan.size();
            for (int j = 0; j < s; j++) {
                HakmilikPermohonan hp = hakmilikPermohonan.get(j);
                if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null
                        && hp.getHakmilik().getIdHakmilik().trim().length() > 0) {
                    if (j != 0) {
                        list.append(", ");
                    }
                    list.append(hp.getHakmilik().getIdHakmilik());
                }
            }
        }

        // hakmilik bersiri
        if (listHakmilikBersiri != null && listHakmilikBersiri.size() > 0) {
            if (s > 0) {
                list.append(", ");
            }
            s = listHakmilikBersiri.size();
            for (int i = 0; i < s; i++) {
                if (i != 0) {
                    list.append(",");
                }
                list.append(listHakmilikBersiri.get(i));
            }
        }

        return list.toString();
    }

    /**
     * Update list with idHakmilik from idHakmilik bersiri given.
     *
     * @param list to be updated
     * @param idHakmilikDari
     * @param idHakmilikKe
     * @param df
     */
    private void updateIdHakmilikFromBersiri(List<String> list, List<String> listIdHakmilikDari,
            List<String> listIdHakmilikKe) {
        if (listIdHakmilikDari == null || listIdHakmilikDari.size() == 0
                || listIdHakmilikKe == null || listIdHakmilikKe.size() == 0) {
            return;
        }

        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.setGroupingUsed(false);

        StringBuilder from = new StringBuilder();
        for (int x = 0; x < listIdHakmilikDari.size(); x++) {
            String idHakmilikDari = listIdHakmilikDari.get(x);
            String idHakmilikKe = listIdHakmilikKe.get(x);
            if (idHakmilikDari == null || idHakmilikKe == null) {
                continue;
            }

            from.setLength(0); // reset
            // from
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

            // validate both are the same
            if (!idHakmilikKe.startsWith(pre)) {
                LOG.warn("IdHakmilik bersiri tidak sah. IdHakmilikDari=" + idHakmilikDari
                        + ", IdHakmilikKe=" + idHakmilikKe);
                continue;
            }

            // to
            long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));

            list.add(idHakmilikDari);
            df.setMinimumIntegerDigits(from.length());
            for (long l = lFrom + 1; l < lTo; l++) {
                String id = pre + df.format(l);
                list.add(id);
            }
            list.add(idHakmilikKe);
        }
    }

    /**
     * private final static void appendIdHakmilikBersiri(StringBuilder list,
     * String idHakmilikDari, String idHakmilikKe, DecimalFormat df) { // from
     * StringBuilder from = new StringBuilder(); for (int i =
     * idHakmilikDari.length() - 1; i >= 0; i--) { char c =
     * idHakmilikDari.charAt(i); if (c >= '0' && c <= '9') { from.insert(0, c);
     * } else { break; } } long lFrom = Long.parseLong(from.toString()); String
     * pre = idHakmilikDari.substring(0, idHakmilikDari.length() -
     * from.length());
     *
     * // to long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(),
     * idHakmilikDari.length()));
     *
     * if (list.length() > 0) { list.append(", "); }
     * list.append(idHakmilikDari).append(", ");
     * df.setMinimumIntegerDigits(from.length()); for (long l = lFrom + 1; l <
     * lTo; l++) { String id = pre + df.format(l); list.append(id).append(", ");
     * } list.append(idHakmilikKe); }
     */
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
        u.suratSABKuantiti = suratSABKuantiti;
        u.suratSAD = suratSAD;
        u.suratSWBKuantiti = suratSWBKuantiti;
        u.suratSWD = suratSWD;
        u.suratSBBKuantiti = suratSBBKuantiti;
        u.suratSBD = suratSBD;
        //SM
        u.SMKuantiti = SMKuantiti;
        u.smPerserahan = smPerserahan;


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
                uv.position = l;
            }
        } else { // updating existing cache
            au.set(selectedItem, u);
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
        senaraiDokumenTamb = null;
        suratSABKuantiti = 0;
        suratSAD = null;
        suratSWBKuantiti = 0;
        suratSWD = null;
        suratSBBKuantiti = 0;
        suratSBD = null;
        //SM
        SMKuantiti = 0;
        smPerserahan = null;
    }

    private void resetAll() {
        if (debug) {
            LOG.debug("invoked");
        }
        resetUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        idPenyerah = null;
        penyerahKod = null;
        penyerahJenisPengenalan = null;
        penyerahNoPengenalan = null;
        penyerahNama = null;
        penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4 =
                penyerahPoskod = penyerahEmail = penyerahNoTelefon = null;
        penyerahNegeri = null;
        selectedItem = -1;

        // reset cara bayaran
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
        senaraiDokumenSerahan = u.senaraiDokumenSerahan;
        if (debug) {
            LOG.debug("senaraiDokumenSerahan.size=" + senaraiDokumenSerahan.size());
        }
        senaraiDokumenTamb = u.senaraiDokumenTamb;
        suratSABKuantiti = u.suratSABKuantiti;
        suratSAD = u.suratSAD;
        suratSWBKuantiti = u.suratSWBKuantiti;
        suratSWD = u.suratSWD;
        suratSBBKuantiti = u.suratSBBKuantiti;
        suratSBD = u.suratSBD;
        //SM
        SMKuantiti = u.SMKuantiti;
        smPerserahan = u.smPerserahan;
    }

    public List<TransaksiValue> getSenaraiTransaksiSemasa() {
        jumlahCaj = BigDecimal.ZERO;

        ArrayList<TransaksiValue> senaraiTransaksi = new ArrayList<TransaksiValue>();

        // prepare list of hakmilik
        ArrayList<String> listBersiri = new ArrayList<String>();
        updateIdHakmilikFromBersiri(listBersiri, idHakmilikSiriDari, idHakmilikSiriKe);
        String senaraiHakmilik = getSenaraiHakmilikUrusan(hakmilikPermohonan, listBersiri);

        UrusanValue firstUrusan = null;
        TransaksiValue firstTransaksi = null;
        for (int i = 0; i < senaraiUrusan.size(); i++) {
            UrusanValue uv = senaraiUrusan.get(i);
            if (debug) {
                LOG.debug("processing " + uv.kodUrusan);
            }
            if (isUrusanNull(uv)) {
                continue;
            }
            if (firstUrusan == null) {
                firstUrusan = uv;
            }

            KodUrusan ku = kodUrusanDAO.findById(uv.kodUrusan);
            if (ku.getKodTransaksi() != null) {
                List<TransaksiValue> listTransaksi = kaunterService.calculateFee(ku, uv.amaun1, uv.tarikh1, uv.nilai1,
                        null, null, hakmilikPermohonan, listBersiri);
                for (int x = 0; x < listTransaksi.size(); x++) {
                    TransaksiValue t = listTransaksi.get(x);
                    t.senaraiHakmilik = senaraiHakmilik;
                    t.utkUrusan = uv;
                    jumlahCaj = jumlahCaj.add(t.amaun);
                }
                senaraiTransaksi.addAll(listTransaksi);
            } else {
                // create zero transaction
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(ku.getKod());
                t.setNamaUrusan(ku.getNama());
                t.amaun = BigDecimal.ZERO;
                senaraiTransaksi.add(t);
            }

            // TODO t.kodTransaksi
            if (firstTransaksi == null) {
                firstTransaksi = senaraiTransaksi.get(0);
            }

            // caj based on documents submittted
            List<UrusanDokumen> lud = getSenaraiDokumen();
            if (i == 0) {
                for (DokumenValue dv : senaraiDokumenSerahan) {
                    if (dv != null && dv.kodDokumen != null) {
                        for (int k = 0; k < lud.size(); k++) {
                            if (lud.get(k).getKodDokumen().getKod().equals(dv.kodDokumen)) {
                                BigDecimal caj = lud.get(k).getCaj();
                                if (caj != null && (caj.compareTo(BigDecimal.ZERO)) > 0) {
                                    // create a new transaction
                                    TransaksiValue dokumenT = new TransaksiValue();
                                    dokumenT.kodUrusan = uv.kodUrusan;
                                    dokumenT.namaUrusan = dv.kodDokumen;
                                    // t3.senaraiHakmilik = t.senaraiHakmilik;
                                    dokumenT.kuantiti = dv.bil;
                                    dokumenT.amaun = caj.multiply(new BigDecimal(dv.bil));
                                    dokumenT.utkUrusan = uv;
                                    dokumenT.kodTransaksi = lud.get(k).getKodTransaksi().getKod();
                                    senaraiTransaksi.add(dokumenT);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            // caj for Surat Amanah Baru
            if (suratSABKuantiti > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SAB", suratSABKuantiti);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }

            // caj for Surat Amanah Daftar
            int sizeSurat = getCountValidSuratSWD(suratSAD); // process valid only
            if (sizeSurat > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SAD", sizeSurat);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }


            // caj for Surat Kebenaran Baru
            if (suratSBBKuantiti > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SBB", suratSBBKuantiti);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }

            // caj for Surat Kebenaran Daftar
            sizeSurat = getCountValidSuratSWD(suratSBD); // process valid only
            if (sizeSurat > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SBD", sizeSurat);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }

            // caj for Surat Kuasa Wakil Baru
            if (suratSWBKuantiti > 0) {
                TransaksiValue t4 = calculateFeeForDocuments(uv, "SWB", suratSWBKuantiti);
                senaraiTransaksi.add(t4);
                jumlahCaj = jumlahCaj.add(t4.amaun);
            }

            // caj for Surat Kuasa Wakil Daftar
            sizeSurat = getCountValidSuratSWD(suratSWD); // process valid only
            if (sizeSurat > 0) {
                TransaksiValue t5 = calculateFeeForDocuments(uv, "SWD", sizeSurat);
                senaraiTransaksi.add(t5);
                jumlahCaj = jumlahCaj.add(t5.amaun);
            }

            //Added by Aizuddin for SM
            // caj for SM
            if (SMKuantiti > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SMD", SMKuantiti);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }

            // caj for SM Batal
            sizeSurat = getCountValidSuratSWD(smPerserahan); // process valid only
            if (sizeSurat > 0) {
                TransaksiValue t = calculateFeeForDocuments(uv, "SMD", sizeSurat);
                senaraiTransaksi.add(t);
                jumlahCaj = jumlahCaj.add(t.amaun);
            }
            //End added by Aizuddin
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
        for (UrusanCache uc : txnGroups) {
            // prepare list of hakmilik
            ArrayList<String> listBersiri = new ArrayList<String>();
            updateIdHakmilikFromBersiri(listBersiri, uc.idHakmilikSiriDari, uc.idHakmilikSiriKe);
            String senaraiHakmilik = getSenaraiHakmilikUrusan(uc.hakmilikPermohonan, listBersiri);

            UrusanValue firstUrusan = null;
            TransaksiValue firstTransaksi = null;
            for (int i = 0; i < uc.senaraiUrusan.size(); i++) {
                UrusanValue uv = uc.senaraiUrusan.get(i);
                if (debug) {
                    LOG.debug("processing " + uv.kodUrusan);
                }
                if (isUrusanNull(uv)) {
                    continue;
                }
                if (firstUrusan == null) {
                    firstUrusan = uv;
                }

                /*
                 TransaksiValue t = new TransaksiValue();
                 KodUrusan ku = kodUrusanDAO.findById(uv.kodUrusan);
                 t.kodUrusan = uv.kodUrusan;
                 t.namaUrusan = ku.getNama();
                 t.senaraiHakmilik = senaraiHakmilik;
                 t.utkUrusan = uv;
                 t.amaun = kaunterService.calculateFee(ku, uv.amaun1, uv.nilai1, uc.hakmilikPermohonan,
                 listBersiri);
                 if (ku.getKodTransaksi() != null){
                 t.kodTransaksi = ku.getKodTransaksi().getKod();
                 } else{
                 t.amaun = BigDecimal.ZERO;
                 }
                 */
                KodUrusan ku = kodUrusanDAO.findById(uv.kodUrusan);
                if (ku.getKodTransaksi() != null) {
                    List<TransaksiValue> listTransaksi = kaunterService.calculateFee(ku, uv.amaun1, uv.tarikh1, uv.nilai1,
                            null, null, uc.hakmilikPermohonan, listBersiri);
                    for (int x = 0; x < listTransaksi.size(); x++) {
                        TransaksiValue t = listTransaksi.get(x);
                        t.senaraiHakmilik = senaraiHakmilik;
                        t.utkUrusan = uv;
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }
                    if (firstTransaksi == null) {
                        firstTransaksi = listTransaksi.get(0);
                    }
                    senaraiTransaksi.addAll(listTransaksi);
                } else {
                    // create zero transaction
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(ku.getKod());
                    t.setNamaUrusan(ku.getNama());
                    t.amaun = BigDecimal.ZERO;
                    senaraiTransaksi.add(t);
                }

                // TODO t.kodTransaksi

                // calculation for pelepasan/pengecualian/notis/bayaran fail/lain2
                final BigDecimal NEGATION = new BigDecimal(-1);
                if (uv.cajPelepasan != null && !uv.cajPelepasan.equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.kodUrusan = uv.kodUrusan;
                    t4.namaUrusan = "Pelepasan";
                    t4.senaraiHakmilik = senaraiHakmilik;
                    t4.amaun = uv.cajPelepasan.multiply(NEGATION);
                    t4.utkUrusan = uv;
                    // TODO get proper kods for this!
                    t4.kodTransaksi = firstTransaksi.kodTransaksi;
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.amaun);
                }
                if (uv.cajPengecualian != null && !uv.cajPengecualian.equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.kodUrusan = uv.kodUrusan;
                    t4.namaUrusan = "Pengecualian";
                    t4.senaraiHakmilik = senaraiHakmilik;
                    t4.amaun = uv.cajPengecualian.multiply(NEGATION);
                    t4.utkUrusan = uv;
                    // TODO get proper kods for this!
                    t4.kodTransaksi = firstTransaksi.kodTransaksi;
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.amaun);
                }
                if (uv.cajNotis != null && !uv.cajNotis.equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.kodUrusan = uv.kodUrusan;
                    t4.namaUrusan = "Notis";
                    t4.senaraiHakmilik = senaraiHakmilik;
                    t4.amaun = uv.cajNotis;
                    t4.utkUrusan = uv;
                    // TODO get proper kods for this!
                    t4.kodTransaksi = firstTransaksi.kodTransaksi;
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.amaun);
                }
                if (uv.cajFail != null && !uv.cajFail.equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.kodUrusan = uv.kodUrusan;
                    t4.namaUrusan = "Bayaran Fail";
                    t4.senaraiHakmilik = senaraiHakmilik;
                    t4.amaun = uv.cajFail;
                    t4.utkUrusan = uv;
                    // TODO get proper kods for this!
                    t4.kodTransaksi = firstTransaksi.kodTransaksi;
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.amaun);
                }
                if (uv.cajLain != null && !uv.cajLain.equals(BigDecimal.ZERO)) {
                    // create a new transaction
                    TransaksiValue t4 = new TransaksiValue();
                    t4.kodUrusan = uv.kodUrusan;
                    t4.namaUrusan = "Bayaran-bayaran Lain";
                    t4.senaraiHakmilik = senaraiHakmilik;
                    t4.amaun = uv.cajLain;
                    t4.utkUrusan = uv;
                    // TODO get proper kods for this!
                    t4.kodTransaksi = firstTransaksi.kodTransaksi;
                    senaraiTransaksi.add(t4);
                    jumlahCaj = jumlahCaj.add(t4.amaun);
                }

                // caj based on documents submittted
                List<UrusanDokumen> lud = getSenaraiDokumen(uc.senaraiUrusan);
                if (i == 0) { // calculate only for first urusan, don't duplicate payment!
                    for (DokumenValue dv : uc.senaraiDokumenSerahan) {
                        if (dv != null && dv.kodDokumen != null) {
                            for (int k = 0; k < lud.size(); k++) {
                                if (lud.get(k).getKodDokumen().getKod().equals(dv.kodDokumen)) {
                                    BigDecimal caj = lud.get(k).getCaj();
                                    if (caj != null && (caj.compareTo(BigDecimal.ZERO)) > 0) {
                                        // create a new transaction
                                        TransaksiValue t3 = new TransaksiValue();
                                        t3.kodUrusan = uv.kodUrusan;
                                        t3.namaUrusan = dv.kodDokumen;
                                        // t3.senaraiHakmilik = t.senaraiHakmilik;
                                        t3.kuantiti = dv.bil;
                                        t3.amaun = caj.multiply(new BigDecimal(dv.bil));
                                        t3.utkUrusan = uv;
                                        t3.kodTransaksi = lud.get(k).getKodTransaksi().getKod();
                                        senaraiTransaksi.add(t3);
                                        jumlahCaj = jumlahCaj.add(t3.amaun);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    // caj for Surat Amanah Baru
                    if (uc.suratSABKuantiti > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SAB", uc.suratSABKuantiti);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }

                    // caj for Surat Amanah Daftar
                    int sizeSurat = getCountValidSuratSWD(uc.suratSAD); // process valid only
                    if (sizeSurat > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SAD", sizeSurat);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }


                    // caj for Surat Kebenaran Baru
                    if (uc.suratSBBKuantiti > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SBB", uc.suratSBBKuantiti);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }

                    // caj for Surat Kebenaran Daftar
                    sizeSurat = getCountValidSuratSWD(uc.suratSBD); // process valid only
                    if (sizeSurat > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SBD", sizeSurat);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }

                    // caj for Surat Kuasa Wakil Baru
                    if (uc.suratSWBKuantiti > 0) {
                        TransaksiValue t4 = calculateFeeForDocuments(uv, "SWB", uc.suratSWBKuantiti);
                        senaraiTransaksi.add(t4);
                        jumlahCaj = jumlahCaj.add(t4.amaun);
                    }

                    // caj for Surat Kuasa Wakil Daftar
                    sizeSurat = getCountValidSuratSWD(uc.suratSWD); // process valid only
                    if (sizeSurat > 0) {
                        TransaksiValue t5 = calculateFeeForDocuments(uv, "SWD", sizeSurat);
                        senaraiTransaksi.add(t5);
                        jumlahCaj = jumlahCaj.add(t5.amaun);
                    }

                    //Added by Aizuddin
                    // caj for SM
                    if (uc.SMKuantiti > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SMD", uc.SMKuantiti);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }

                    // caj for SM Batal
                    sizeSurat = getCountValidSuratSWD(uc.smPerserahan); // process valid only
                    if (sizeSurat > 0) {
                        TransaksiValue t = calculateFeeForDocuments(uv, "SMD", sizeSurat);
                        senaraiTransaksi.add(t);
                        jumlahCaj = jumlahCaj.add(t.amaun);
                    }
                    //End added by Aizuddin

                }
            }

        }

        jumlahCaj.setScale(2);
        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj.toPlainString());
        }

        return senaraiTransaksi;
    }

    private TransaksiValue calculateFeeForDocuments(UrusanValue uv, String kodUrusan, int kuantiti) {
        if (kuantiti > 0) {
            KodUrusan urusanSWD = kodUrusanDAO.findById(kodUrusan);
            // create a new transaction
            TransaksiValue t5 = new TransaksiValue();
            t5.utkUrusan = uv;
            t5.kodUrusan = kodUrusan;
            t5.namaUrusan = urusanSWD.getNama();
            t5.kuantiti = kuantiti;
            t5.amaun = urusanSWD.getCaj().multiply(new BigDecimal(kuantiti));
            t5.kodTransaksi = urusanSWD.getKodTransaksi().getKod();
            return t5;
        } else {
            return null;
        }
    }

    private int getCountValidSuratSWD(ArrayList<String> suratSWD) {
        int c = 0;
        for (String s : suratSWD) {
            if (StringUtils.isNotBlank(s)) {
                ++c;
            }
        }

        return c;
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
        return (uv == null || uv.kodUrusan == null || "0".equals(uv.kodUrusan));
    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
    }

    private Permohonan savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan,
            UrusanValue uv, boolean isBerangkai, String[] idKumpulan, FolderDokumen fd,
            Permohonan permohonanLama, Permohonan permohonanSebelum,
            List<Hakmilik> listHakmilik, InfoAudit ia) {
        String idPermohonan = null;
        if (KOD_JABATAN_PENDAFTARAN.equals(kodUrusan.getJabatan().getKod())) { // for pendaftaran, use ID Perserahan
            idPermohonan = idPerserahanGenerator.generate(
                    kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        } else {
            idPermohonan = idPermohonanGenerator.generate(
                    kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        }
        uv.idPermohonan = idPermohonan;

        if (isBerangkai && idKumpulan[0] == null) {
            idKumpulan[0] = idPermohonan;
        }

        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(pengguna.getKodCawangan());
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);
        if (isBerangkai) {
            p.setIdKumpulan(idKumpulan[0]);
        }
        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        p.setInfoAudit(iaPermohonan);
        // penyerah
        if (idPenyerah != null && idPenyerah.length() > 0
                && !"0".equals(idPenyerah)) {
            p.setIdPenyerah(Long.parseLong(idPenyerah));
        }
        if (penyerahKod != null && penyerahKod.getKod() != null
                && !"0".equals(penyerahKod.getKod())) {
            p.setKodPenyerah(penyerahKod);
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
        } else if (permohonanLama != null) {
            p.setPermohonanSebelum(permohonanLama);
        }
        p.setPenyerahEmail(penyerahEmail);
        p.setPenyerahNoTelefon1(penyerahNoTelefon);
        p.setUntukTahun(d.getYear() + 1900);
        permohonanDAO.save(p);

        // attach Hakmilik
        if (listHakmilik != null) {
            for (Hakmilik hm : listHakmilik) {
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
            }
        }

        // processing permohonan lama
        if (permohonanLama != null && (!"PPTL".equals(permohonanLama.getKodUrusan().getKod())
                && !"PPJP".equals(permohonanLama.getKodUrusan().getKod()))) {
            Session s = sessionProvider.get();


            // copy data from permohonanLama
            p.setCatatan("Permohonan ini telah dibuat berdasarkan Permohonan/Perserahan "
                    + permohonanLama.getIdPermohonan() + ".");

            // copy pemohon
            List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
            if (senaraiPemohon != null) {
                List<Pemohon> newList = new ArrayList<Pemohon>();
                for (Pemohon p1 : senaraiPemohon) {
                    Pemohon p2 = new Pemohon();
                    p2.setCawangan(pengguna.getKodCawangan());
                    p2.setPermohonan(p);
                    p2.setPihak(p1.getPihak());
                    p2.setPihakCawangan(p1.getPihakCawangan());
                    p2.setKaitan(p1.getKaitan());
                    p2.setPekerjaan(p1.getPekerjaan());
                    p2.setPendapatan(p1.getPendapatan());
                    p2.setPendapatanLain(p1.getPendapatanLain());
                    p2.setStatusKahwin(p1.getStatusKahwin());
                    p2.setTanggungan(p1.getTanggungan());
                    p2.setUmur(p1.getUmur());
                    p2.setMatawang(p1.getMatawang());
                    p2.setWargaNegara(p1.getWargaNegara());
                    p2.setNama(p1.getNama());
                    p2.setJenisPengenalan(p1.getJenisPengenalan());
                    p2.setNoPengenalan(p1.getNoPengenalan());
                    p2.setAlamat(p1.getAlamat());
                    p2.setAlamatSurat(p1.getAlamatSurat());
                    p2.setInfoAudit(ia);
                    newList.add(p2);
                }
                p.setSenaraiPemohon(newList);
            }

            // copy PermohonanPihak
            List<PermohonanPihak> senaraiPihak = permohonanLama.getSenaraiPihak();
            if (senaraiPihak != null) {
                List<PermohonanPihak> newList = new ArrayList<PermohonanPihak>();
                for (PermohonanPihak p1 : senaraiPihak) {
                    PermohonanPihak p2 = new PermohonanPihak();
                    p2.setCawangan(pengguna.getKodCawangan());
                    p2.setPermohonan(p);
                    p2.setPihak(p1.getPihak());
                    p2.setPihakCawangan(p1.getPihakCawangan());
                    p2.setHakmilik(p1.getHakmilik());
                    p2.setJenis(p1.getJenis());
                    p2.setKaitan(p1.getKaitan());
                    p2.setPekerjaan(p1.getPekerjaan());
                    p2.setPendapatan(p1.getPendapatan());
                    p2.setStatusKahwin(p1.getStatusKahwin());
                    p2.setSyerPembilang(p1.getSyerPembilang());
                    p2.setSyerPenyebut(p1.getSyerPenyebut());
                    p2.setTangungan(p1.getTangungan());
                    p2.setUmur(p1.getUmur());
                    p2.setDalamanNilai1(p1.getDalamanNilai1());
                    p2.setKodMataWang(p1.getKodMataWang());
                    p2.setWargaNegara(p1.getWargaNegara());
                    p2.setNama(p1.getNama());
                    p2.setJenisPengenalan(p1.getJenisPengenalan());
                    p2.setNoPengenalan(p1.getNoPengenalan());
                    p2.setAlamat(p1.getAlamat());
                    p2.setAlamatSurat(p1.getAlamatSurat());
                    p2.setInfoAudit(ia);
                    newList.add(p2);
                }
                p.setSenaraiPihak(newList);
            }

            permohonanDAO.save(p);
        }

        return p;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        // to load the current parameters with data from session.
        String toFill = getContext().getRequest().getParameter("fill");
        if (toFill != null) {
            int i = Integer.parseInt(toFill);
            selectedItem = i;
            loadUrusan(i);
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

    private Resolution validateHakmilik() {
        UrusanValue uv0 = senaraiUrusan.get(0);
        KodUrusan ku = kodUrusanDAO.findById(uv0.kodUrusan);
        boolean perluJelasCukai = ku.getPerluJelasCukai() == 'Y';
        String kodJabatan = ku.getJabatan().getKod();
        // if deal with Hakmilik Daerah sahaja
        boolean milikDaerahOnly = "16".equals(kodJabatan) || "8".equals(kodJabatan); // only Pendaftaran & Lelong
        String kodDaerah = null;
        KodDaerah daerah = ((etanahActionBeanContext) getContext()).getUser().getKodCawangan().getDaerah();
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }

        // validate Hakmilik
        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        // get hakmilik
        if (hakmilikPermohonan.size() > 0) {
            for (HakmilikPermohonan hp : hakmilikPermohonan) {
                if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null) {
                    String idHakmilik = hp.getHakmilik().getIdHakmilik().trim();
                    if (idHakmilik.length() == 0) {
                        continue;
                    }
                    listIdHakmilik.add(idHakmilik);
                }
            }
        }
        // get hakmilik bersiri
        if (idHakmilikSiriDari != null && idHakmilikSiriKe != null) {
            for (int i = 0; i < idHakmilikSiriDari.size(); i++) {
                String idH1 = idHakmilikSiriDari.get(i);
                String idH2 = idHakmilikSiriKe.get(i);

                if (idH1 == null || idH1.trim().length() == 0
                        || idH2 == null || idH2.trim().length() == 0) {
                    continue;
                }

                ArrayList<String> list = getIdHakmilikFromSiri(idH1, idH2);
                listIdHakmilik.addAll(list);
            }
        }
        String hql = "select h from Hakmilik h where h.idHakmilik h.idHakmilik in (:listHakmilik) "
                + " and h.kodStatusHakmilik.id in ('D')";
        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        } else if (milikDaerahOnly || kodDaerah == null) {
            hql += " and h.kodHakmilik.milikDaerah = :milikDaerah"; // hakmilik pendaftar sahaja
        }
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) sessionProvider.get().createQuery(hql).setParameterList("listHakmilik", listIdHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        } else if (kodDaerah == null || milikDaerahOnly) {
            q.setCharacter("milikDaerah", kodDaerah == null ? 'T' : 'Y');
        }
        List<Hakmilik> listHakmilik = q.list();
        // size must be the same
        if (listIdHakmilik.size() != listHakmilik.size()) {
            addSimpleError("Terdapat " + (listIdHakmilik.size() - listHakmilik.size()) + " Hakmilik yang tidak wujud!");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
        }
        // check for cukai has paid
        if (perluJelasCukai) {
            for (Hakmilik hm : listHakmilik) {
                Akaun ac = hm.getAkaunCukai();
                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
                    addSimpleError("Hakmilik " + hm.getIdHakmilik() + " belum menjelaskan cukai!");
                    return new ForwardResolution("/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
                }
            }
        }

        return null;
    }
}
