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
import net.sourceforge.stripes.action.RedirectResolution;
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
import etanah.model.HakmilikPihakBerkepentingan;
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
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;
import etanah.view.stripes.hasil.KutipanHasilService;
import etanah.workflow.WorkFlowService;
import org.apache.commons.lang.StringUtils;
import etanah.view.stripes.hasil.PenyataPemungutService;
import org.apache.commons.lang.ArrayUtils;

@HttpCache(allow = false)
@Wizard(startEvents = {"Step1", "editUrusan", "Step6b"})
@UrlBinding("/kaunter/permohonan")
public class PermohonanKaunter2 extends AbleActionBean {

    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    public static final int KAUNTER_HAKMILIK_MAKSIMUM = 500;
    private char tambahUrusan = 'T';
    // for deleting/editing urusan
    private int selectedItem = -1;
    // DISPLAY OBJECTS
    // Urusan Value Object (used in Step1 & Step6a)
    private UrusanPermohonan urusan;
    private static final Map<String, String[]> URUSAN_INFO = new HashMap<String, String[]>();
    private static String kodNegeri;
    private boolean ptg = true;
    private boolean disRadio = true;
    private String mod = "";
    private Pengguna pguna;
    private String kodSerah;
    private int bilMohon;
    // abaikan warning semasa compilation dan teruskan mendaftar urusan
    private String abaiAmaran = "T";
    // value object of amount of charges (used in Step6)
    private BigDecimal jumlahCaj = BigDecimal.ZERO;
    @Inject
    StrataPtService strService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    KodCawanganJabatanDAO kodCawanganJabatanDAO;
    @Inject
    KodKutipanDAO kodKutipanDAO;
    @Inject
    private SuratWakilKuasaService suratWakilKuasaService;
    @Inject
    private UrusanValidator urusanValidator;
    @Inject
    private BayaranSessionService bayaranSessionService;
    @Inject
    KutipanHasilService hasilService;
    private int bilHakmilik = 5;
    private static final Logger LOG = Logger.getLogger(PermohonanKaunter2.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private String pbbd = "";

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
                if (infos.length != 4) { // copy to max
                    String[] b = new String[4];
                    for (int x = 0; x < infos.length && x < 3; x++) {
                        b[x] = infos[x];
                    }
                    infos = b;
                }
                for (int j = 0; j < 4; j++) {
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

    public List<KodUrusan> getPilihanKodUrusan() {
        return kaunterService.findAllUrusanByJabatan();
    }

    public String getKodUrusanSebelum() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanKaunter> luk = bayaranSessionService.getAllUrusanFromSession(ctx);
        if (luk != null & luk.size() > 0) {
            UrusanKaunter uk = (UrusanKaunter) luk.get(luk.size() - 1);
            if (uk instanceof UrusanPermohonan) {
                return ((UrusanPermohonan) uk).getKodUrusan();
            }
        }

        return null;
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

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public UrusanPermohonan getUrusan() {
        return urusan;
    }

    public void setUrusan(UrusanPermohonan urusan) {
        this.urusan = urusan;
    }

    public int getBilMohon() {
        return bilMohon;
    }

    public void setBilMohon(int bilMohon) {
        this.bilMohon = bilMohon;
    }
    // label for screen

    public String getSenaraiUrusanLabel() {
        if (urusan.getKodUrusan() == null) {
            return "";
        } else {
            return urusan.getNamaUrusan();
        }
    }

    public boolean isDisRadio() {
        return disRadio;
    }

    public void setDisRadio(boolean disRadio) {
        this.disRadio = disRadio;
    }

    /**
     * Get the checklist Dokumen for the given list Urusan.
     *
     * @return
     */
    public List<UrusanDokumen> getSenaraiDokumen() {
        LOG.debug("getSenaraiDokumen - invoked");

        ArrayList<String> list = new ArrayList<String>();
        list.add(urusan.getKodUrusan());
        if (kaunterService == null) {
            LOG.warn("Guice-Inject KaunterService failed");
            kaunterService = etanahContextListener.newInstance(KaunterService.class);
        }
        return kaunterService.getUrusanDokumen(list, urusan.getJenisPemohon());
    }

    /**
     * Get the KodDokumen which are not required for transactions
     *
     * @return
     */
    public List<KodDokumen> getKodDokumenNotRequired() {
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        senaraiKodUrusan.add(urusan.getKodUrusan());

        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public List<TransaksiValue> getSenaraiTransaksiPra() {
        List<TransaksiValue> ltv = urusan.getSenaraiTransaksiPra();
        for (TransaksiValue t : ltv) {
            if (urusan.getKodUrusan().equals("PPPP")) {
                if (urusan.amaun2 != null) {
                    if (urusan.amaun2.compareTo(BigDecimal.ZERO) != 0) {
                        jumlahCaj = urusan.amaun2;
                    } else {
                        jumlahCaj = urusan.amaun1;
                    }
                } else {
                    jumlahCaj = urusan.amaun1;
                }
            } else {
                jumlahCaj = jumlahCaj.add(t.amaun);
            }
        }

        return ltv;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bil) {
        if (bil <= KAUNTER_HAKMILIK_MAKSIMUM) {
            this.bilHakmilik = bil;
        } else {
            addSimpleError("Maksimum " + KAUNTER_HAKMILIK_MAKSIMUM + " hakmilik yang boleh diterima untuk satu Permohonan!");
            this.bilHakmilik = KAUNTER_HAKMILIK_MAKSIMUM;
        }
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public void setAbaiAmaran(String abaiAmaran) {
        this.abaiAmaran = abaiAmaran;
    }

    public String getAbaiAmaran() {
        return abaiAmaran;
    }

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        hasilService.resetAll(ctx);

        pguna = ctx.getUser();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        Pengguna pguna = ctx.getUser();
//        if (debug)
//            LOG.debug("pguna.getKodCawangan().getKod():" + pguna.getKodCawangan().getKod());
        if (pguna.getKodCawangan().getKod().equals("00")) {
            ptg = false;
            if (debug) {
                LOG.debug("ptg : " + ptg);
            }
        }

        // process tambah urusan baru?
        if (debug) {
            LOG.debug("tambahUrusan=" + tambahUrusan);
        }
        if (tambahUrusan == 'Y') {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }

        // edit urusan?
        if (selectedItem > -1) {
            loadUrusan(selectedItem);
            return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
    }

    @HandlesEvent("Step2")
    @DontValidate
    public Resolution selectDocuments() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        List<String> errors = urusanValidator.onStep1(urusan, p);
        if (errors != null && errors.size() > 0) {
            addErrors(errors);
            return selectTransaction();
        }

        if (debug) {
            LOG.debug("berkaitanSebelum=" + urusan.berkaitanSebelum);
            LOG.debug("selected kodUrusan=" + urusan.kodUrusan);
            LOG.debug("selectedItem=" + selectedItem);
        }
        // validate if any urusan selected
        if (isUrusanNull(urusan)) {
            addSimpleError("Sila pilih Kod Urusan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }

        KodUrusan ku = kodUrusanDAO.findById(urusan.kodUrusan);
        if (ku == null || ku.getUrusanKaunter() != 'Y') {
            addSimpleError("Urusan tidak sah di Kaunter.");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }
        urusan.kodUrusan = ku.getKod();
        urusan.kodJabatan = ku.getJabatan().getKod();
        urusan.kodUrusanPilih = ku.getKod();

        // for editing urusan purpose
        if (selectedItem >= 0) {
            UrusanPermohonan up = (UrusanPermohonan) bayaranSessionService.getUrusanFromSession(ctx, selectedItem);
            // load up document
            if (up != null) {
                urusan.kategoriPemohon = up.kategoriPemohon;
            }
            if (up != null && urusan.senaraiDokumenSerahan == null
                    || urusan.senaraiDokumenSerahan.size() == 0) {
                urusan.senaraiDokumenSerahan = new ArrayList<DokumenValue>();
                urusan.senaraiDokumenSerahan = up.senaraiDokumenSerahan;
            }
            if (up != null && urusan.senaraiDokumenTamb == null
                    || urusan.senaraiDokumenTamb.size() == 0) {
                urusan.senaraiDokumenTamb = new ArrayList<DokumenValue>();
                urusan.senaraiDokumenTamb = up.senaraiDokumenTamb;
            }
        }

        // reset the additional documents submitted to 10 if none
        if (urusan.senaraiDokumenTamb.size() == 0) {
            for (int i = 0; i < 5; i++) {
                DokumenValue d = new DokumenValue();
                urusan.senaraiDokumenTamb.add(d);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan_2.jsp");
    }

    @HandlesEvent("Step2UpdateSenaraiSemakan")
    @DontValidate
    public Resolution updateSenaraiSemakan() {
        // validate if any urusan selected
        if (isUrusanNull(urusan)) {
            addSimpleError("Sila pilih Kod Urusan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan_2.jsp");
    }

    @HandlesEvent("Step3")
    @DontValidate
    public Resolution selectTitles() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        List<String> errors = urusanValidator.onStep2(urusan, p);
        if (errors != null && errors.size() > 0) {
            addErrors(errors);
            return selectDocuments();
        }

        // validate all the mandatory documents submitted
        List<UrusanDokumen> lud = getSenaraiDokumen();
        if (lud.size() > 0) {
            for (UrusanDokumen ud : lud) {
                if (ud.getWajib() == 'Y') {
                    // check if the document wajib for whom
                    if (ud.getKategoriPemohon() == 'X' || ud.getKategoriPemohon()
                            == urusan.kategoriPemohon) {
                        boolean fd = false;
                        for (DokumenValue d : urusan.senaraiDokumenSerahan) {
                            if (d != null
                                    && d.kodDokumen != null
                                    && ud.getKodDokumen().getKod().equals(d.kodDokumen)) {
                                fd = true;
                                break;
                            }
                        }
                        if (!fd) {
                            // as per requirement from Hanim/Azliza/Sherina(JKPTG),
                            // can proceed with
                            // submission even without mandatory documents
                            // 21/4 However, NRE has reverted the decision
                            addSimpleError("AMARAN: Dokumen yang mandatori ("
                                    + ud.getKodDokumen().getNama()
                                    + ") untuk urusan tidak diserahkan! Ini boleh mengakibatkan "
                                    + "Permohonan/Perserahan ditolak!");
                            return new ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan_2.jsp");
                        }
                    }
                }
            }
        }

        // validation of Surat2 Berdaftar
        if (urusan.suratSWD != null && urusan.suratSWD.size() > 0) {
            ArrayList<String> listSurat = new ArrayList<String>();
            // clean up the list
            for (String s : urusan.suratSWD) {
                if (s == null || s.trim().length() == 0) {
                    continue;
                }

                //validate format SW.Sekiranya format surat yg dimasukkan adalah format SPTB,akan diconvert kpd format etanah    
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    String a = "04";
                    String b = p.getKodCawangan().getKod();
                    String c = a.concat(b);

                    if ((s.contains("SW")) || (s.contains("SA")) || (s.contains("SB")) || (!s.contains("/"))) {
                        LOG.debug("ikut format etanah--->"+s);
                    } else {
                        String[] SplitSlash = s.split("/");
                        String SptbID = SplitSlash[0];
                        String SptbYear = SplitSlash[1];

                        while (SptbID.length() < 6) {
                            SptbID = "0" + SptbID;
                        }

                        LOG.debug("zeropad------>" + SptbID);
                        String convertedSPTBid = c.concat("SW").concat(SptbYear).concat(SptbID);
                        LOG.debug("x ikut format,so akan diconvert kepada-->" + convertedSPTBid);
                        s = convertedSPTBid;
                    }
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
                addSimpleMessage("PERHATIAN: Surat Kuasa Wakil ini tiada dalam rekod: "
                        + msg);
                // return new
                // ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan.jsp");
                // THERE ARE PRE-SPTBs
            }
        }

        //Validation for urusan SMBT check if SMD exist
        if (urusan.smPerserahan != null && urusan.smPerserahan.size() > 0) {
            ArrayList<String> listPerserahan = new ArrayList<String>();
            // clean up the list
            for (String s : urusan.smPerserahan) {
                if (s == null || s.trim().length() == 0) {
                    continue;
                }

                listPerserahan.add(s);
            }

            List<String> invalidSurat = suratWakilKuasaService.validateSM(listPerserahan);
            if (invalidSurat != null && invalidSurat.size() > 0) {
                StringBuilder msg = new StringBuilder();
                for (String s : invalidSurat) {
                    if (msg.length() > 0) {
                        msg.append(", ");
                    }
                    msg.append(s);
                }
                addSimpleMessage("PERHATIAN: Syarikat MCL ini tiada dalam rekod atau belum didaftarkan: "
                        + msg);
                // return new
                // ForwardResolution("/WEB-INF/jsp/kaunter/senarai_semakan.jsp");
                // THERE ARE PRE-SPTBs
            }
        }

        // check if any hakmilik needed for this
        KodUrusan ku = kodUrusanDAO.findById(urusan.kodUrusan);
        kodSerah = ku.getKodPerserahan().getKod();
        if (ku.getBilMaksimumHakmilik() != null && ku.getBilMaksimumHakmilik() == 0) {
            // skip this step
            if ("kembali".equalsIgnoreCase(ctx.getRequest().getParameter("Step3"))) {
                // go to Step2
                return selectDocuments();
            } else {
                return getSpecificInfoForUrusan();
            }
        }

        // for editing urusan purpose, repopulate from Session
        if (selectedItem >= 0) {
            UrusanPermohonan up = (UrusanPermohonan) bayaranSessionService.getUrusanFromSession(ctx, selectedItem);
            // hakmilik terlibat
            if (urusan.hakmilikPermohonan == null || urusan.hakmilikPermohonan.size() == 0) {
                if (up != null) {
                    urusan.hakmilikPermohonan = up.hakmilikPermohonan;
                }
            }
            // hakmilik bersiri dari
            if (urusan.getIdHakmilikSiriDari() == null || urusan.getIdHakmilikSiriDari().size() == 0) {
                if (up != null) {
                    urusan.setIdHakmilikSiriDari(up.getIdHakmilikSiriDari());
                }
            }
            // hakmilik bersiri ke
            if (urusan.idHakmilikSiriKe == null || urusan.idHakmilikSiriKe.size() == 0) {
                if (up != null) {
                    urusan.idHakmilikSiriKe = up.idHakmilikSiriKe;
                }
            }
        } else if (urusan.hakmilikPermohonan.size() == 0) {  // try to load senarai hakmilik from previous urusan
            ArrayList<UrusanKaunter> luk = bayaranSessionService.getAllUrusanFromSession(ctx);
            if (luk != null && luk.size() > 0) {
                int pos = luk.size() - 1;
                UrusanKaunter uk = luk.get(pos);
                // copy the Hakmilik
                if (uk instanceof UrusanPermohonan) {
                    UrusanPermohonan up = (UrusanPermohonan) uk;
                    if (up.hakmilikPermohonan != null && up.hakmilikPermohonan.size() > 0) {
                        setBilHakmilik(up.hakmilikPermohonan.size());
                        for (String s : up.getHakmilikPermohonan()) {
                            urusan.hakmilikPermohonan.add(s);
                        }
                    }
                    if (up.idHakmilikSiriDari != null && up.idHakmilikSiriDari.size() > 0) {
                        for (int i = 0; i < up.idHakmilikSiriDari.size(); i++) {
                            urusan.idHakmilikSiriDari.add(up.idHakmilikSiriDari.get(i));
                            urusan.idHakmilikSiriKe.add(up.idHakmilikSiriKe.get(i));
                        }
                    }
                }
            }

        }

        // reset senaraiHakmilik
        if (urusan.hakmilikPermohonan.size() == 0) {
            urusan.hakmilikPermohonan = new ArrayList<String>();
            for (int i = 0; i < bilHakmilik; i++) {
                urusan.hakmilikPermohonan.add("");
            }
        }

        String JSP = "/WEB-INF/jsp/kaunter/pilih_hakmilik_2.jsp";

        if (urusan.kodUrusan.equals("SMK") || urusan.kodUrusan.equals("SMBT")) {
            JSP = "/WEB-INF/jsp/kaunter/caj_tamb_2.jsp";
        }

        return new ForwardResolution(JSP);
    }
    /**
     * To get the specific information for certain Urusan. The extra values will
     * be updated in UrusanPermohonan properties (nilai, tarikh1, amaun1).
     *
     * @return the page that handle the info gathering.
     */
    @HandlesEvent("Step4")
    @DontValidate
    public Resolution getSpecificInfoForUrusan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        // hakmilik validation
        KodUrusan ku = kodUrusanDAO.findById(urusan.kodUrusan);
        if (ku.getBilMinimumHakmilik() != null && ku.getBilMinimumHakmilik() > 0 && !(ku.getKod().equals("SWDB")||ku.getKod().equals("SADB")||ku.getKod().equals("SA")||ku.getKod().equals("SB")||ku.getKod().equals("SW"))) {
            boolean found = false;
            for (String idH : urusan.hakmilikPermohonan) {
                if (StringUtils.isNotEmpty(idH)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (String idH : urusan.idHakmilikSiriDari) {
                    if (StringUtils.isNotEmpty(idH)) {
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                addSimpleError("Sekurang-kurangnya " + ku.getBilMinimumHakmilik()
                        + " hakmilik mesti dimasukkan untuk urusan ini!");
                return new ForwardResolution("/WEB-INF/jsp/kaunter/pilih_hakmilik_2.jsp");
            }
        }


        List<String> errors = urusanValidator.onStep3(urusan, p);
        if (errors != null && errors.size() > 0) {
            addErrors(errors);
            return selectTitles();
        }

        // reset the urusan
        // no other specific info needed, skip this step
        boolean needInfo = false;
        String[] infos = URUSAN_INFO.get(urusan.kodUrusan);
        if (infos != null) {
            urusan.labelAmaun1 = infos[0];
            urusan.labelTarikh1 = infos[1];
            urusan.labelNilai1 = infos[2];
            urusan.labelTeks1 = infos[3];
            needInfo = true;

            // special handling of labelNilai1
            urusan.labelNilai1Senarai = new ArrayList<String>();
            if (urusan.labelNilai1 != null) {
                if (urusan.labelNilai1.endsWith("*")) { // to list out all kategori

                    String label = urusan.labelNilai1.substring(0, urusan.labelNilai1.length() - 1);
                    List<String> selection = urusan.getNilai1Selection();
                    if (selection.size() > 0) {
                        for (String s : selection) {
                            urusan.labelNilai1Senarai.add(label + " " + s);
                        }
                    }
                } else { // capture 1 kategori only
                    urusan.labelNilai1Senarai.add(urusan.labelNilai1);
                }
            }
        }
        //STRATA        
        if (urusan.kodUrusan.equalsIgnoreCase("PHPC")) {
            needInfo = true;
        }
        if (urusan.kodUrusan.equalsIgnoreCase("PPPP")) {
            needInfo = true;
        }
        if (urusan.kodUrusan.equalsIgnoreCase("PHPC")) {
            List<HakmilikPihakBerkepentingan> hmPK = strService.findSizePemilik(urusan.hakmilikPermohonan.get(0), "PM");
            if (hmPK.size() == 1) {
                disRadio = true;
            } else if (hmPK.size() > 1) {
                disRadio = false;
            }
        }
        if (urusan.kodUrusan.equalsIgnoreCase("BACT")) {
            needInfo = true;
        }
        /*End*/

        if (needInfo) {
            return new ForwardResolution("/WEB-INF/jsp/kaunter/urusan_info_tamb_2.jsp");
        } else {
            return getAdditionalPaymentInfo();
        }
    }

    @HandlesEvent("Step5")
    @DontValidate
    public Resolution getAdditionalPaymentInfo() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        List<String> errors = urusanValidator.onStep4(urusan, p);
        if (errors != null && errors.size() > 0) {
            addErrors(errors);
            return getSpecificInfoForUrusan();
        }

        // validate extra info
        if (!isUrusanNull(urusan)) {
            String[] infos = URUSAN_INFO.get(urusan.getKodUrusan());
            if (infos != null) {
                //added by murali (STRATA) For urusan PBS
//                if (urusan.getKodUrusan().equals("PBS")) {
//                    if ("05".equals(conf.getProperty("kodNegeri"))) {
//                        urusan.amaun1 = new BigDecimal(1);
//                    }
//                    LOG.debug("--Urusan PBS--:");
//                    LOG.debug("--values from JSP--:" + urusan.amaun1 + " " + urusan.amaun2 + " " + urusan.tarikh1 + " " + urusan.nilai1);
//                    if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO
//                            || urusan.amaun2 == null || urusan.amaun2 == BigDecimal.ZERO)
//                            || (infos[1] != null && urusan.tarikh1 == null)
//                            || (infos[2] != null && urusan.nilai1 == null
//                            || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
//                        urusan.amaun1 = null;
//                        addSimpleError("Sila isikan semua maklumat!");
//                        return getSpecificInfoForUrusan();
//                    }
//
//                    if (infos[0] != null && urusan.amaun1 != null && urusan.amaun2 != null) {
//                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
//                    }
//
//                } else 
                if (urusan.getKodUrusan().equals("PBTS")) {

                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        urusan.amaun1 = new BigDecimal(1);
                        urusan.amaun3 = new BigDecimal(1);
                    }
                    LOG.debug("--Urusan PBTS--:");
                    LOG.debug("--values from JSP--:" + urusan.amaun1 + " " + urusan.amaun2 + " " + urusan.amaun3 + " " + urusan.tarikh1 + " " + urusan.nilai1);

                    if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO
                            || urusan.amaun2 == null || urusan.amaun2 == BigDecimal.ZERO
                            || urusan.amaun3 == null || urusan.amaun3 == BigDecimal.ZERO)
                            || (infos[1] != null && urusan.tarikh1 == null)
                            || (infos[2] != null && urusan.nilai1 == null
                            || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                        urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null && urusan.amaun2 != null && urusan.amaun3 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }

                } else if (urusan.getKodUrusan().equals("PBBD") || urusan.getKodUrusan().equals("PBBS")
                        || urusan.getKodUrusan().equals("PBS")) {
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        /* if (!pbbd.isEmpty() && pbbd.equals("N")) {                            
                         LOG.debug("--values from JSP--:" + pbbd + " " + urusan.amaun3 + " " + urusan.amaun2 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                         if ((infos[0] != null && urusan.amaun3 == null || urusan.amaun3 == BigDecimal.ZERO
                         || urusan.amaun2 == null || urusan.amaun2 == BigDecimal.ZERO)
                         || (infos[1] != null && urusan.tarikh1 == null)
                         || (infos[2] != null && urusan.nilai1 == null
                         || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                         urusan.amaun1 = null;
                         addSimpleError("Sila isikan semua maklumat!");
                         return getSpecificInfoForUrusan();
                         }
                         if (infos[0] != null && urusan.amaun3 != null && urusan.amaun2 != null) {
                         getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                         }
                         } else {                            
                         LOG.debug(pbbd + " " + urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                         if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO)
                         || (infos[1] != null && urusan.tarikh1 == null)
                         || (infos[2] != null && urusan.nilai1 == null
                         || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                         urusan.amaun1 = null;
                         addSimpleError("Sila isikan semua maklumat!");
                         return getSpecificInfoForUrusan();
                         }
                         if (infos[0] != null && urusan.amaun1 != null) {
                         getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                         }
                         } */
                        if (!pbbd.isEmpty() && pbbd.equals("Ya")) {
                            LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                            if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO)
                                    || (infos[1] != null && urusan.tarikh1 == null)
                                    || (infos[2] != null && urusan.nilai1 == null
                                    || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                                urusan.amaun1 = null;
                                addSimpleError("Sila isikan semua maklumat!");
                                return getSpecificInfoForUrusan();
                            }
                            if (infos[0] != null && urusan.amaun1 != null && pbbd != null) {
                                getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                            }
                        } else if (!pbbd.isEmpty() && pbbd.equals("tidak")) {
                            LOG.debug(urusan.amaun2 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                            if ((infos[0] != null && urusan.amaun2 == null || urusan.amaun2 == BigDecimal.ZERO)
                                    || (infos[1] != null && urusan.tarikh1 == null)
                                    || (infos[2] != null && urusan.nilai1 == null
                                    || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                                urusan.amaun2 = null;
                                addSimpleError("Sila isikan semua maklumat!");
                                return getSpecificInfoForUrusan();
                            }
                            if (infos[0] != null && urusan.amaun2 != null) {
                                getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                            }
                        }
                    } else { //n9
                        LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                        if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO)
                                || (infos[1] != null && urusan.tarikh1 == null)
                                || (infos[2] != null && urusan.nilai1 == null
                                || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                            urusan.amaun1 = null;
                            addSimpleError("Sila isikan semua maklumat!");
                            return getSpecificInfoForUrusan();
                        }
                        if (infos[0] != null && urusan.amaun1 != null) {
                            getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                        }
                    }

                } else if (urusan.getKodUrusan().equals("PPPP")) {
                    if (!pbbd.isEmpty() && pbbd.equals("Ya")) {
                        LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    } else if (!pbbd.isEmpty() && pbbd.equals("tidak")) {
                        LOG.debug(urusan.amaun2 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    }
                    getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                } else if (urusan.getKodUrusan().equals("PBMT")) {
                    LOG.debug("Urusan PBMT");
                    LOG.debug(urusan.amaun1 + " " + urusan.nilai1);
                    if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO)
                            || (infos[1] != null && urusan.tarikh1 == null)
                            || (infos[2] != null && urusan.nilai1 == null)) {
                        urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                } else if (urusan.getKodUrusan().equals("RLPS")) {
                    LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    if (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)) {
                        //urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                }else if (urusan.getKodUrusan().equals("SEK4")) {
                    LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    if ((StringUtils.isEmpty(urusan.kategori))) {
                        //urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                }else if (urusan.getKodUrusan().equals("831")) {
                    LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    if ((StringUtils.isEmpty(urusan.idPermohonanSebelum))) {
                        //urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                }  else if (urusan.getKodUrusan().equals("BACT")) {
                    LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    if (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)) {
                        //urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                } else {
                    LOG.debug(urusan.amaun1 + " " + urusan.tarikh1 + " " + urusan.nilai1);
                    if ((infos[0] != null && urusan.amaun1 == null || urusan.amaun1 == BigDecimal.ZERO)
                            || (infos[1] != null && urusan.tarikh1 == null)
                            || (infos[2] != null && urusan.nilai1 == null
                            || (infos[3] != null && (urusan.teks1 == null || urusan.teks1.trim().length() == 0)))) {
                        urusan.amaun1 = null;
                        addSimpleError("Sila isikan semua maklumat!");
                        return getSpecificInfoForUrusan();
                    }
                    if (infos[0] != null && urusan.amaun1 != null) {
                        getContext().getRequest().setAttribute("Step4", Boolean.TRUE);
                    }
                }//else end
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/caj_tamb_2.jsp");
    }

    @HandlesEvent("Bayaran")
    @DontValidate
    public Resolution bayaran() {
        // validate the urusan
        if (isUrusanNull(urusan)) {
            throw new RuntimeException("Sila pilih Urusan");
        }

        if (!"Y".equals(abaiAmaran)) {
            List<String> errs = urusanValidator.validateUrusan(urusan);
            if (errs != null && errs.size() > 0) {
                addErrors(errs);
                //STRATA: user can not proceed urusan:Rayuan if idhakmilik pass keputusan already
                LOG.debug("--Urusan--:" + urusan);
                String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7", "RTHS", "PPPP"};
                if (!ArrayUtils.contains(kodursns, urusan.getKodUrusan())) {
                    addSimpleError("<a href=\"javascript:forceProceed();\">Abaikan Amaran dan Teruskan</a>");
                }
                return new ForwardResolution("/WEB-INF/jsp/kaunter/caj_tamb_2.jsp");
            }
        }
        if (urusan.getKodUrusan().equals("PHPC")) {
            getContext().getRequest().getSession().setAttribute("hakmlk", urusan.hakmilikPermohonan.get(0));
            getContext().getRequest().getSession().setAttribute("bilMohon", bilMohon);
        }

        /*Add by hakim for Module Consent*/
        if (urusan.getIdPermohonanSebelum() != null) {
            getContext().getRequest().getSession().setAttribute("idSblm", urusan.getIdPermohonanSebelum());
        }
        /*End*/
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveUrusanToSession(ctx);
        // reset all values
        resetUrusan();
        return new RedirectResolution(TerimaBayaran.class);
    }

    private void addErrors(List<String> errs) {
        for (String s : errs) {
            addSimpleError(s);
        }
    }

    @HandlesEvent("Step6a")
    public Resolution addTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
    }

    /**
     * From kesinambungan urusan
     *
     * @return
     */
    @HandlesEvent("Step6b")
    public Resolution suggestTransaction() {
        selectedItem = -1;

        return new ForwardResolution("/WEB-INF/jsp/kaunter/tambah_urusan.jsp");
    }

    @HandlesEvent("Cancel")
    public Resolution cancel() {
        resetUrusan();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanKaunter> senaraiUrusan = bayaranSessionService.getAllUrusanFromSession(ctx);

        if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
//            return selectTransaction();
            return new RedirectResolution(PermohonanKaunter2.class);
        } else {
            return new RedirectResolution(TerimaBayaran.class);
        }
    }

    private ArrayList<String> getIdHakmilikFromSiri(String idHakmilikDari,
            String idHakmilikKe) {
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
        String pre = idHakmilikDari.substring(0,
                idHakmilikDari.length() - from.length());

        // to
        long lTo = 0l;
        try {
            lTo = Long.parseLong(idHakmilikKe.substring(pre.length(),
                    idHakmilikDari.length()));
        } catch (NumberFormatException e) {
            throw new RuntimeException("ID Hakmilik bersiri tidak sah");
        }

        ArrayList<String> listIdHakmilik = new ArrayList<String>();
        // validate the series along the way
        if (idHakmilikDari.length() != idHakmilikKe.length()
                || !idHakmilikDari.substring(0, pre.length()).equals(
                        idHakmilikKe.substring(0, pre.length())) || lTo < lFrom) {
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
                if (hp.getHakmilik() != null
                        && hp.getHakmilik().getIdHakmilik() != null
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
    private void updateIdHakmilikFromBersiri(List<String> list,
            List<String> listIdHakmilikDari, List<String> listIdHakmilikKe) {
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
            String pre = idHakmilikDari.substring(0, idHakmilikDari.length()
                    - from.length());

            // validate both are the same
            if (!idHakmilikKe.startsWith(pre)) {
                LOG.warn("IdHakmilik bersiri tidak sah. IdHakmilikDari="
                        + idHakmilikDari + ", IdHakmilikKe=" + idHakmilikKe);
                continue;
            }

            // to
            long lTo = Long.parseLong(idHakmilikKe.substring(pre.length(),
                    idHakmilikDari.length()));

            list.add(idHakmilikDari);
            df.setMinimumIntegerDigits(from.length());
            for (long l = lFrom + 1; l < lTo; l++) {
                String id = pre + df.format(l);
                list.add(id);
            }
            list.add(idHakmilikKe);
        }
    }

    @SuppressWarnings("unchecked")
    private final void saveUrusanToSession(etanahActionBeanContext ctx) {
        if (isUrusanNull(urusan)) {
            if (debug) {
                LOG.debug("no urusan to be saved to session");
            }
            return;
        }

        // save all data to Urusan object
        if (debug) {
            LOG.debug("saving urusan " + urusan.getKodUrusan());
        }

        // check kumpulan
        if (urusan.berkaitanSebelum) {
            bayaranSessionService.groupUrusanInPreviousKumpulanUrusan(ctx, urusan);
        }

        if (selectedItem < 0) {
            bayaranSessionService.addUrusan(ctx, urusan);
        } else {
            bayaranSessionService.updateUrusan(ctx, urusan, selectedItem);
        }
    }

    private void resetUrusan() {
        if (debug) {
            LOG.debug("resetUrusan invoked");
        }
        urusan = null;
    }

    private void loadUrusan(int whichUrusanCacheIndex) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        List<UrusanKaunter> luc = bayaranSessionService.getAllUrusanFromSession(ctx);
        if (luc == null || whichUrusanCacheIndex >= luc.size()) {
            LOG.error("Data simpanan sementara telah korup. Anda perlu mula semula.");
            throw new RuntimeException("Data simpanan sementara telah korup. "
                    + "Anda perlu mula semula.");
        }

        urusan = (UrusanPermohonan) luc.get(whichUrusanCacheIndex);
    }

    private static final boolean isUrusanNull(UrusanPermohonan uv) {
        return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        // to load the current parameters with data from session.
        String toFill = getContext().getRequest().getParameter("fill");
        if (toFill != null) {
            LOG.debug("loading urusan " + toFill + " from session");
            int i = Integer.parseInt(toFill);
            selectedItem = i;
            loadUrusan(i);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "n9";
        }
    }

    @HandlesEvent("CancelAll")
    public Resolution cancelAll() {
        return new RedirectResolution(PermohonanKaunter2.class);
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    /* TO DELETE
     private Resolution validateHakmilik() {
     KodUrusan ku = kodUrusanDAO.findById(urusan.getKodUrusan());
     boolean perluJelasCukai = ku.getPerluJelasCukai() == 'Y';
     String kodJabatan = ku.getJabatan().getKod();
     // if deal with Hakmilik Daerah sahaja
     boolean milikDaerahOnly = "16".equals(kodJabatan)
     || "8".equals(kodJabatan); // only Pendaftaran & Lelong
     String kodDaerah = null;
     KodDaerah daerah = ((etanahActionBeanContext) getContext()).getUser()
     .getKodCawangan().getDaerah();
     if (daerah != null)
     kodDaerah = daerah.getKod();

     // validate Hakmilik
     ArrayList<String> listIdHakmilik = new ArrayList<String>();
     // get hakmilik
     if (urusan.hakmilikPermohonan.size() > 0) {
     for (String hp : urusan.hakmilikPermohonan) {
     if (hp != null) {
     String idHakmilik = hp.trim();
     if (idHakmilik.length() == 0)
     continue;
     listIdHakmilik.add(idHakmilik);
     }
     }
     }
     // get hakmilik bersiri
     if (urusan.getIdHakmilikSiriDari() != null && urusan.idHakmilikSiriKe != null) {
     for (int i = 0; i < urusan.getIdHakmilikSiriDari().size(); i++) {
     String idH1 = urusan.getIdHakmilikSiriDari().get(i);
     String idH2 = urusan.idHakmilikSiriKe.get(i);

     if (idH1 == null || idH1.trim().length() == 0 || idH2 == null
     || idH2.trim().length() == 0) {
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
     hql += " and h.kodHakmilik.milikDaerah = :milikDaerah"; // hakmilik
     // pendaftar
     // sahaja
     }
     if (debug)
     LOG.debug(hql);

     Query q = (Query) sessionProvider.get().createQuery(hql)
     .setParameterList("listHakmilik", listIdHakmilik);
     if (kodDaerah != null)
     q.setString("kodDaerah", kodDaerah);
     else if (kodDaerah == null || milikDaerahOnly)
     q.setCharacter("milikDaerah", kodDaerah == null ? 'T' : 'Y');
     List<Hakmilik> listHakmilik = q.list();
     // size must be the same
     if (listIdHakmilik.size() != listHakmilik.size()) {
     addSimpleError("Terdapat "
     + (listIdHakmilik.size() - listHakmilik.size())
     + " Hakmilik yang tidak wujud!");
     return new ForwardResolution(
     "/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
     }
     // check for cukai has paid
     if (perluJelasCukai) {
     for (Hakmilik hm : listHakmilik) {
     Akaun ac = hm.getAkaunCukai();
     if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
     addSimpleError("Hakmilik " + hm.getIdHakmilik()
     + " belum menjelaskan cukai!");
     return new ForwardResolution(
     "/WEB-INF/jsp/kaunter/pilih_hakmilik.jsp");
     }
     }
     }

     return null;
     }
     */
    public String getPbbd() {
        return pbbd;
    }

    public void setPbbd(String pbbd) {
        this.pbbd = pbbd;
    }
}
