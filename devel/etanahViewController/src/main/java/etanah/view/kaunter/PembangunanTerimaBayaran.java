package etanah.view.kaunter;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import able.stripes.AbleActionBean;

import com.google.inject.Inject;
import etanah.dao.AkaunDAO;

import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodCawanganJabatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.Permohonan;
import etanah.model.WakilKuasa;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdKumpulanPermohonan;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.common.PihakService;
import etanah.service.StrataPtService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.workflow.WorkFlowService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

@HttpCache(allow = false)
@Wizard(startEvents = {"setPenyerah"})
@UrlBinding("/pembangunan/terima_bayaran")
public class PembangunanTerimaBayaran extends AbleActionBean {

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
  private String namaPenyampai;
  private String noPengenalanPenyampai;
  private String noTelPenyampai;
  private String idSblm;
  private int bilMohon;
  private String mod = "";
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  // for deleting/editing urusan
  private int selectedItem = -1;
  private BigDecimal jumlahCaj;
  private String resitNo;
  private String kodNeg;
  private WakilKuasa wakilKuasa; // used in Step 8
  private Permohonan permohonan; // used saveSuratWakil()
  // senarai permohonan for display in Step7. Being initialized in save() method (used in Step7)
  ArrayList<UrusanKaunter> allUrusan = new ArrayList<UrusanKaunter>();
  private static final Logger LOG = Logger.getLogger(PembangunanTerimaBayaran.class);
  private static final boolean debug = LOG.isDebugEnabled();
  @Inject
  private FolderDokumenDAO folderDokumenDAO;
  @Inject
  private KodKlasifikasiDAO kodKlasifikasiDAO;
  @Inject
  private DokumenDAO dokumenDAO;
  @Inject
  private CaraBayaranDAO caraBayaranDAO;
  @Inject
  KodAkaunDAO kodAkaunDAO;
  @Inject
  KodKutipanDAO kodKutipDAO;
  @Inject
  private AkaunService akaunService;
  @Inject
  private KodCaraBayaranDAO kodCaraBayaranDAO;
  @Inject
  private DokumenKewanganDAO dokumenKewanganDAO;
  @Inject
  private TransaksiDAO transaksiDAO;
  @Inject
  private KodTransaksiDAO kodTransaksiDAO;
  @Inject
  private KodDokumenDAO kodDokumenDAO;
  @Inject
  private KodStatusTransaksiKewanganDAO kodKewanganStatusDAO;
  @Inject
  private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
  @Inject
  private BayaranSessionService bayaranService;
  // No. generator for Surat Kuasa Wakil
  @Inject
  private etanah.sequence.GeneratorIdWakil idWakilGenerator;
  @Inject
  //private GeneratorNoResit noResitGenerator;
  private GeneratorNoResit2 noResitGenerator2;
  @Inject
  private ReportUtil reportUtil;
  @Inject
  private PeguamDAO peguamDAO;
  @Inject
  private JUBLDAO jUBLDAO;
  @Inject
  private KodCawanganJabatanDAO kodCawanganJabatanDAO;
  @Inject
  private KodAgensiDAO kodAgensiDAO;
  @Inject
  private PihakService pihakService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  @Inject
  PenggunaDAO penggunaDAO;
  @Inject
  private KodUrusanDAO kodUrusanDAO;
  @Inject
  private BayaranSessionService bayaranSessionService;
  @Inject
  private DokumenKewanganBayaranDAO dokKewBayaranDAO;
  @Inject
  private GeneratorIdKumpulanPermohonan generatorIdKumpulan;
  @Inject
  private PenyataPemungutService penyataPemungutService;
  @Inject
  private ModKutipService modKutip;
  @Inject
  private KodBankDAO kodBankDAO;
  @Inject
  private AkaunDAO akaunDAO;
  @Inject
  private PermohonanDAO mohonDAO;
  @Inject
  private StrataPtService strService;
  @Inject
  PendaftaranSuratKuasaService suratkuasaService;  //Surat Wakil Kuasa service use in "Step8"
  @Inject
  PermohonanDAO permohonanDAO;
  // list of payment methods for the Urusan (used in Step6)
  private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
  private List<CaraBayaran> caraBayaranList = new ArrayList<CaraBayaran>();
  private List<String> tarikhCek = new ArrayList<String>();
  private Permohonan permohonanSblm;

  public List<String> getTarikhCek() {
    return tarikhCek;
  }

  public void setTarikhCek(List<String> tarikhCek) {
    this.tarikhCek = tarikhCek;
  }

  public String getMod() {
    return mod;
  }

  public void setMod(String mod) {
    this.mod = mod;
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

    public String getNamaPenyampai() {
        return namaPenyampai;
    }

    public void setNamaPenyampai(String namaPenyampai) {
        this.namaPenyampai = namaPenyampai;
    }

    public String getNoPengenalanPenyampai() {
        return noPengenalanPenyampai;
    }

    public void setNoPengenalanPenyampai(String noPengenalanPenyampai) {
        this.noPengenalanPenyampai = noPengenalanPenyampai;
    }

    public String getNoTelPenyampai() {
        return noTelPenyampai;
    }

    public void setNoTelPenyampai(String noTelPenyampai) {
        this.noTelPenyampai = noTelPenyampai;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

  public BigDecimal getJumlahCaj() {
    return jumlahCaj;
  }

  public void setJumlahCaj(BigDecimal jumlahCaj) {
    this.jumlahCaj = jumlahCaj;
  }

  public String getResitNo() {
    return resitNo;
  }

  public int getSelectedItem() {
    return selectedItem;
  }

  public void setSelectedItem(int i) {
    selectedItem = i;
  }

  public List<CaraBayaran> getCaraBayaranList() {
    return caraBayaranList;
  }

  public void setCaraBayaranList(List<CaraBayaran> caraBayaranList) {
    this.caraBayaranList = caraBayaranList;
  }

  public void setSenaraiUrusan(ArrayList<UrusanKaunter> allUrusan) {
    this.allUrusan = allUrusan;
  }

  public ArrayList<UrusanKaunter> getSenaraiUrusan() {
    return allUrusan;
  }

  public int getBilMohon() {
    return bilMohon;
  }

  public void setBilMohon(int bilMohon) {
    this.bilMohon = bilMohon;
  }

  public WakilKuasa getWakilKuasa() {
    return wakilKuasa;
  }

  public void setWakilKuasa(WakilKuasa wakilKuasa) {
    this.wakilKuasa = wakilKuasa;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public void setPermohonan(Permohonan permohonan) {
    this.permohonan = permohonan;
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

  public List<TransaksiValue> getSenaraiTransaksi() {
    jumlahCaj = BigDecimal.ZERO;
    List<UrusanKaunter> listUrusan = bayaranSessionService.getAllUrusanFromSession(
            (etanahActionBeanContext) getContext());
    ArrayList<TransaksiValue> listT = new ArrayList<TransaksiValue>();
    for (int g = 0; g < listUrusan.size(); g++) {
      UrusanKaunter u = listUrusan.get(g);
      List<TransaksiValue> atv = u.getSenaraiTransaksi();
      if (atv != null) {
        for (TransaksiValue t : atv) {
          jumlahCaj = jumlahCaj.add(t.amaun);
        }
        listT.addAll(atv);
      }
    }

    if (debug) {
      LOG.debug("jumlahCaj=" + jumlahCaj);
    }

    return listT;
  }

  public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
    return senaraiCaraBayaran;
  }

  public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
    this.senaraiCaraBayaran = senaraiCaraBayaran;
  }

  @DefaultHandler
  public Resolution setPenyerah() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    /*Add by Syafiq Az(STRATA) for Urusan PHPC*/
    List<UrusanKaunter> senaraiUrusan = bayaranSessionService.getAllUrusanFromSession(ctx);
    if (senaraiUrusan.get(0).getKodUrusan().equalsIgnoreCase("PHPC")) {
      bilMohon = (Integer) getContext().getRequest().getSession().getAttribute("bilMohon");
    } else {
      bilMohon = 0;
    }
    /*End*/
    
    /*Add by hakim for Module Consent*/
    Permohonan mhnSblm = new Permohonan();
    idSblm = (String) getContext().getRequest().getSession().getAttribute("idSblm");
    if(idSblm != null){
        String query = "SELECT m FROM etanah.model.Permohonan m WHERE m.idPermohonan =:id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", idSblm);
        mhnSblm = (Permohonan) q.uniqueResult();
//        mhnSblm = m.getPermohonanSebelum();
    }
    /*End*/
    
    if (senaraiUrusan == null || senaraiUrusan.size() == 0) {
      LOG.warn("Nothing in session. return to kaunter");
      return new RedirectResolution(PemohonanPembangunanKaunter.class);
    } else {
      loadPenyerahFromSession(ctx);

      // if penyerah already filled up
      if (penyerahKod != null && penyerahNama != null) {
        return collectPayment();
      }
      /*Add by hakim for Module Consent*/
      if(idSblm != null){
          if(mhnSblm != null){ //added by murali (STRATA)
          penyerahKod = mhnSblm.getKodPenyerah();
          penyerahNama = mhnSblm.getPenyerahNama();
          idPenyerah = mhnSblm.getIdPenyerah().toString();
          }
      }/*End*/

      return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/penyerah.jsp");
    }
  }

  @HandlesEvent("editPenyerah")
  public Resolution editPenyerah() {
    if (penyerahNama == null) {
      loadPenyerahFromSession((etanahActionBeanContext) getContext());
    }

    return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/penyerah.jsp");
  }

  @HandlesEvent("Step7")
  @DontValidate
  public Resolution collectPayment() {
    // validate Penyerah
    if (penyerahNama == null || penyerahNama.trim().length() == 0
            || penyerahAlamat1 == null || penyerahAlamat1.trim().length() == 0
            || penyerahNegeri == null || penyerahNegeri.getKod().equals("0")) {
      addSimpleError("Sila isikan maklumat Penyerah yang mandatori");
      return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/penyerah.jsp");
    }

    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    savePenyerahToSession(ctx);

    List<UrusanKaunter> senaraiUrusan = bayaranSessionService.getAllUrusanFromSession(ctx);
    if (senaraiUrusan == null) {
      addSimpleError("Data simpanan sementara telah korup. Anda perlu mula semula.");
      return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/tambah_urusan.jsp");
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
    
   // Permohonan mhnSblm = new Permohonan();
    idSblm = (String) getContext().getRequest().getSession().getAttribute("idSblm");
    if(idSblm != null){
        String query = "SELECT m FROM etanah.model.Permohonan m WHERE m.idPermohonan =:id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", idSblm);
        permohonanSblm = (Permohonan) q.uniqueResult();
    }


    return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/bayaran_2.jsp");
  }

  @HandlesEvent("Step8")
  public Resolution save() {
    KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    List<UrusanKaunter> senaraiUrusan = bayaranSessionService.getAllUrusanFromSession(ctx);
    if (penyerahNama == null) {
      loadPenyerahFromSession(ctx);
    }
    mod = modKutip.loadPenyerahFromSession(ctx);

    Pengguna pengguna = ctx.getUser();
    KodCawangan caw = pengguna.getKodCawangan();
    Date now = new Date();
    int year = now.getYear() + 1900;

    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pengguna);
    ia.setTarikhMasuk(now);

    String documentPath = conf.getProperty("document.path");

    jumlahCaj = BigDecimal.ZERO;
    //Map<String, Permohonan> mapPermohonan = new HashMap<String, Permohonan>();
    // iterate the list of transactions one by one
    List<UrusanKaunter> listUrusan = bayaranSessionService.getAllUrusanFromSession(ctx);
    int size = listUrusan.size();
    String idKumpulan = null;
    if (size > 1) {
      idKumpulan = generatorIdKumpulan.generate(conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), null);
    }

    Session s = sessionProvider.get();
    Transaction tx = null;
    try {
      DokumenKewangan dk = null;
      Dokumen resit = null;
      ArrayList<TransaksiValue> allTransaksi = new ArrayList<TransaksiValue>();

      tx = s.beginTransaction();

      // calculate first total caj
      for (int g = 0; g < size; g++) {
        UrusanKaunter u = listUrusan.get(g);
        // saving the transaksi
        List<TransaksiValue> atv = u.getSenaraiTransaksi();
        if (atv.size() > 0) {
          allTransaksi.addAll(atv);
          // sum up
          for (TransaksiValue tv : atv) {
            if (tv.kodTransaksi == null) {
              if (tv.amaun.compareTo(BigDecimal.ZERO) > 0) {
                LOG.warn("No KodTransaksi but amount is > 0");
              }
            }
            jumlahCaj = jumlahCaj.add(tv.getAmaun());
          }
        }
      }
      // create the resit if caj > 0
      if (BigDecimal.ZERO.compareTo(jumlahCaj) < 0) {
        // create DokumenKewangan
        // Resit is always created even if no charge involved (zabidi nre)
        // updated: N9 don't want resit if amount 0
        dk = new DokumenKewangan();
        dk.setCawangan(caw);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY")); // TODO cache
        dk.setIsuKepada(penyerahNama);
        dk.setIsuKepadaAlamat1(penyerahAlamat1);
        dk.setIsuKepadaAlamat2(penyerahAlamat2);
        dk.setIsuKepadaAlamat3(penyerahAlamat3);
        dk.setIsuKepadaAlamat4(penyerahAlamat4);
        dk.setIsuKepadaPoskod(penyerahPoskod);
        dk.setIsuKepadaNegeri(penyerahNegeri);
        dk.setIsuKepadaEmail(penyerahEmail);
        dk.setIsuKepadaNoTelefon1(penyerahNoTelefon);
        // TESTING dk.setIdDokumenKewangan(noResitGenerator.generateNoResit(ctx.getKodNegeri(), caw, pengguna));
        dk.setIdDokumenKewangan(noResitGenerator2.getAndLockSerialNo(pengguna));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setInfoAudit(ia);
        dk.setIdKaunter(pengguna.getIdKaunter());
        dk.setTarikhTransaksi(now);
        if (mod != null && mod.length() > 0) {
          dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        }
        dk.setAmaunBayaran(jumlahCaj);
        dokumenKewanganDAO.save(dk);

        // TODO check if jumlahCaj == 0
        // generate dokumen resit
        resit = new Dokumen();
        resit.setFormat("application/pdf");
        resit.setInfoAudit(ia);
        resit.setKlasifikasi(klasifikasiAm);
        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
        resit.setKodDokumen(kodResit);
        resit.setNoVersi("1.0");
        resit.setTajuk(kodResit.getNama());
        resit.setNoRujukan(dk.getIdDokumenKewangan());
        dokumenDAO.save(resit);
      }
      for (int g = 0; g < size; g++) {
        UrusanKaunter u = listUrusan.get(g);
        List<UrusanKaunter> luk = (List<UrusanKaunter>) u.doSave(ctx.getKodNegeri(), pengguna, resit,
                idKumpulan, g + 1, idPenyerah, penyerahKod,
                penyerahNoPengenalan, penyerahJenisPengenalan,
                penyerahNama, penyerahAlamat1,
                penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                penyerahPoskod, penyerahNegeri,
                penyerahEmail, penyerahNoTelefon,                       
                namaPenyampai, noPengenalanPenyampai,
                noTelPenyampai,ia);
        allUrusan.addAll(luk);
      }
      if (senaraiUrusan.get(0).getKodUrusan().equalsIgnoreCase("PHPC")) {
        Permohonan mhn = mohonDAO.findById(senaraiUrusan.get(0).getPermohonan().getIdPermohonan());
        mhn.setBilanganPermohonan(bilMohon);
        mohonDAO.saveOrUpdate(mhn);
      }
      // if there are payments, save payment methods
      if (debug) {
        LOG.debug("jumlahCaj=" + jumlahCaj);
      }
      // processing payments
      if (jumlahCaj.compareTo(BigDecimal.ZERO) > 0) {
        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
          LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
          throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }

        BigDecimal paid = BigDecimal.ZERO;
        for (CaraBayaran cb : senaraiCaraBayaran) {
          if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                  && cb.getAmaun() != null && !cb.getAmaun().equals(BigDecimal.ZERO)) {
            paid = paid.add(cb.getAmaun());
          }
        }
        // validate amounts paid
  /*      if (jumlahCaj.compareTo(paid) != 0) {
          if (jumlahCaj.compareTo(paid) > 0) {
            addSimpleError("Amaun dibayar kurang daripada jumlah Caj dikenakan.");
          }
          if (jumlahCaj.compareTo(paid) < 0) {
            addSimpleError("Amaun dibayar melebihi daripada jumlah Caj dikenakan.");
          }

          return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/bayaran_2.jsp");
        }
  */
        ArrayList<Transaksi> lt = new ArrayList<Transaksi>();
        for (TransaksiValue tv : allTransaksi) {
          if (tv.kodTransaksi == null) {
            if (tv.amaun.compareTo(BigDecimal.ZERO) != 0) {
              LOG.warn("No KodTransaksi but amount is not 0");
            }
          }
          // create txn

          if (tv.getKodUrusan().equals("BMAHK")) {
            LOG.info("START KEW_AKAUN");
            Transaksi trans = new Transaksi();
            Akaun akaun = new Akaun();
            BigDecimal bg = new BigDecimal(0);
            akaun.setBaki(bg.subtract(tv.getAmaun()));
            akaun.setNoAkaun(senaraiUrusan.get(0).getPermohonan().getIdPermohonan());
            akaun.setCawangan(caw);
            akaun.setKodAkaun(kodAkaunDAO.findById("AD"));
//                        akaun.setPermohonan(senaraiUrusan.get(0).getPermohonan());
            akaun.setHantarBil('T');
            akaun.setInfoAudit(ia);
            akaunDAO.save(akaun);
            trans.setAkaunKredit(akaun);
            LOG.info("END KEW_AKAUN: " + akaun.getNoAkaun());

            LOG.info("START KEW_TRANS");
            trans.setKodTransaksi(kodTransaksiDAO.findById(tv.getKodTransaksi()));
            trans.setAkaunDebit(akTerima);
            trans.setAmaun(tv.amaun);
            trans.setUntukTahun(year);
            trans.setTahunKewangan(year);
            trans.setPermohonan(tv.utkUrusan.getPermohonan());
            trans.setDokumenKewangan(dk);
            trans.setPerihal(tv.getNamaUrusan());
            trans.setStatus(kodKewanganStatusDAO.findById('T'));
            trans.setKodUrusan(tv.kodUrusan);
            trans.setKuantiti(1);
            trans.setInfoAudit(ia);
            trans.setCawangan(caw);
            transaksiDAO.save(trans);
            lt.add(trans);
            LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());
          } else if (tv.getKodUrusan().equals("SB")) {
            KodTransaksi kt = kodTransaksiDAO.findById(tv.kodTransaksi);
            if (tv.utkUrusan.getKodUrusan().equals("SB")) {
              if (StringUtils.isNotBlank(tv.getKodDokumen())) {
                for (UrusanKaunter u : allUrusan) {
                  for (KandunganFolder kf : u.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                    String kod = kf.getDokumen().getKodDokumen().getKod();
                    if (kod.equals(tv.getKodDokumen())) {
                      Transaksi t = new Transaksi();
                      t.setCawangan(caw);
                      t.setKodTransaksi(kt);
                      t.setKodUrusan(tv.kodUrusan);
                      t.setPerihal(tv.namaUrusan);
                      t.setStatus(kodKewanganStatusDAO.findById('T'));
                      if (tv.amaun.compareTo(BigDecimal.ZERO) >= 0) {
                        t.setAmaun(tv.amaun);
                        t.setAkaunDebit(akTerima);
                        // akaun to credit can be configured in transaksi value
                        if (tv.akaunKredit != null) {
                          t.setAkaunKredit(tv.akaunKredit);
                        }
                      } else {
                        t.setAmaun(tv.amaun.negate());
                        t.setAkaunKredit(akTerima);
                        if (tv.akaunKredit != null) {
                          t.setAkaunDebit(tv.akaunKredit);
                        }
                      }
                      t.setUntukTahun(year);
                      t.setTahunKewangan(year);
                      t.setKuantiti(tv.kuantiti);
                      t.setInfoAudit(ia);
                      t.setDokumenKewangan(dk);
                      lt.add(t);
                      LOG.info("OK" + u.getPermohonan().getIdPermohonan());
                      t.setPermohonan(u.getPermohonan());
                      t.setAmaun(tv.amaun.divide(new BigDecimal(tv.kuantiti)));
                      transaksiDAO.save(t);
                    }
                    LOG.info("Permohonan : " + u.getPermohonan().getIdPermohonan());
                    LOG.info("kf.getDokumen().getKodDokumen().getKod()" + kf.getDokumen().getKodDokumen().getKod());
                    LOG.info("tv.getKodDokumen()" + tv.getKodDokumen());
                    if (kf.getDokumen().getKodDokumen().getKod().equals(tv.getKodDokumen())) {
                      LOG.info("Permohonan : " + u.getPermohonan().getIdPermohonan());
                      LOG.info("KodDokumen : " + tv.getKodDokumen());

                    }
                  }
                }

              } else {
                Transaksi t = new Transaksi();
                t.setCawangan(caw);
                t.setKodTransaksi(kt);
                t.setKodUrusan(tv.kodUrusan);
                t.setPerihal(tv.namaUrusan);
                t.setStatus(kodKewanganStatusDAO.findById('T'));
                if (tv.amaun.compareTo(BigDecimal.ZERO) >= 0) {
                  t.setAmaun(tv.amaun);
                  t.setAkaunDebit(akTerima);
                  // akaun to credit can be configured in transaksi value
                  if (tv.akaunKredit != null) {
                    t.setAkaunKredit(tv.akaunKredit);
                  }
                } else {
                  t.setAmaun(tv.amaun.negate());
                  t.setAkaunKredit(akTerima);
                  if (tv.akaunKredit != null) {
                    t.setAkaunDebit(tv.akaunKredit);
                  }
                }
                t.setUntukTahun(year);
                t.setTahunKewangan(year);
                t.setKuantiti(tv.kuantiti);
                t.setInfoAudit(ia);
                t.setDokumenKewangan(dk);
                lt.add(t);
                t.setPermohonan(allUrusan.get(0).getPermohonan());
                transaksiDAO.save(t);
              }
            }
          } else {
            KodTransaksi kt = kodTransaksiDAO.findById(tv.kodTransaksi);
            if (kt != null) {
              Transaksi t = new Transaksi();
              t.setCawangan(caw);
              t.setKodTransaksi(kt);
              t.setKodUrusan(tv.kodUrusan);
              t.setPerihal(tv.namaUrusan);
              t.setStatus(kodKewanganStatusDAO.findById('T'));
              if (tv.amaun.compareTo(BigDecimal.ZERO) >= 0) {
                t.setAmaun(tv.amaun);
                t.setAkaunDebit(akTerima);
                // akaun to credit can be configured in transaksi value
                if (tv.akaunKredit != null) {
                  t.setAkaunKredit(tv.akaunKredit);
                }
              } else {
                t.setAmaun(tv.amaun.negate());
                t.setAkaunKredit(akTerima);
                if (tv.akaunKredit != null) {
                  t.setAkaunDebit(tv.akaunKredit);
                }
              }
              t.setUntukTahun(year);
              t.setTahunKewangan(year);
              t.setKuantiti(tv.kuantiti);
              t.setInfoAudit(ia);
              t.setDokumenKewangan(dk);
              lt.add(t);
              // Need to match the transaksi with the urusan
              if (tv.utkUrusan != null && tv.utkUrusan.getPermohonan() != null) {
                t.setPermohonan(tv.utkUrusan.getPermohonan());
                transaksiDAO.save(t);
              }
            }
          }
        }
        dk.setSenaraiTransaksi(lt);

        // save cara bayaran
        saveCaraBayaran(caw, dk, ia);

        penyataPemungutService.savePenyataPemungut(dk);

        // update akaun kutipan harian
        s.lock(akTerima, LockMode.UPGRADE);
        akTerima.setBaki(akTerima.getBaki().add(jumlahCaj));

        resitNo = dk.getIdDokumenKewangan();
        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
        // set idResit to pageContext
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
      }

      tx.commit();
      // receipt generation
      if (jumlahCaj.compareTo(BigDecimal.ZERO) > 0) {

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

        tx = s.beginTransaction();
        resit.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(resit);
        tx.commit();
      }

      tx = s.beginTransaction();

      // perform save cara bayaran for all urusan
      for (int g = 0; g < size; g++) {
        UrusanKaunter u = listUrusan.get(g);
        u.doAfterSave(reportUtil, dk, ia);
      }

      tx.commit();
      noResitGenerator2.commitAndUnlockSerialNo(pengguna);

    } catch (Exception e) {
      noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);

      LOG.error(e);
      if (tx != null) {
        tx.rollback();
      }
      Throwable t = e;
      // getting the root cause
      while (t.getCause() != null) {
        t = t.getCause();
      }
      t.printStackTrace();
      addSimpleError(t.getMessage());

      return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/bayaran_2.jsp");
    } finally {
    }

    // initiate workflow for all urusan that need to
    for (UrusanKaunter u : allUrusan) {
      if (u.hasToInitiateWorkflow()) {
        LOG.debug(("initiating task " + u.getNoRujukan()));
        KodUrusan ku = kodUrusanDAO.findById(u.getKodUrusan());
        if (ku.getIdWorkflow() != null && !"".equals(ku.getIdWorkflow())) {
          String kodCaw = caw.getKod() + (ku.getKePTG() == 'Y' ? ",00" : "");
          try {
            WorkFlowService.initiateTask(ku.getIdWorkflow(),
                    u.getNoRujukan(), kodCaw, pengguna.getIdPengguna(),
                    ku.getNama());
          } catch (Exception e) {
            LOG.error(e.getMessage(), e);
          }
        }
      }
    }
    LOG.debug("all workflows initiated");
    // clear session
    resetPenyerahFromSession(ctx);
    ctx.removeWorkdata();

    //add by azri 25/6/2013: add SB to "wakil_kuasa" table currently only apply for N9
    if ("05".equals(conf.getProperty("kodNegeri"))) {
      for (UrusanKaunter u : allUrusan) {
        if ("SB".equals(u.getPermohonan().getKodUrusan().getKodPerserahan().getKod())) {
          String idMohon = u.getPermohonan().getIdPermohonan();
          saveSuratWakil(idMohon); // create table "Wakil_Kuasa"
        }
      }
    }  //   add by azri : END

    addSimpleMessage("Urusan telah berjaya didaftarkan.");
    caraBayaranList = caraBayar(resitNo);
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/cetak_resit_2.jsp");
  }

  public void saveSuratWakil(String idMohon) {
    LOG.info("-- urusan adalah SB :: Start --");
    wakilKuasa = suratkuasaService.findWakilKuasa(idMohon);
    permohonan = permohonanDAO.findById(idMohon);
    if (wakilKuasa == null) {
      Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
      wakilKuasa = new WakilKuasa();
      InfoAudit info = peng.getInfoAudit();
      info.setDimasukOleh(peng);
      info.setDiKemaskiniOleh(peng);
      info.setTarikhMasuk(new java.util.Date());
      info.setTarikhKemaskini(new java.util.Date());
      wakilKuasa.setInfoAudit(info);
      wakilKuasa.setCawangan(peng.getKodCawangan());
      wakilKuasa.setPermohonan(permohonan);
      wakilKuasa.setIdWakil(permohonan.getIdPermohonan());
      wakilKuasa.setUntukSemuaHakmilik('T');
      wakilKuasa.setTarikhDaftar(new java.util.Date());
      wakilKuasa.setAktif('Y');
      wakilKuasa.setKuasaAm('T');
      wakilKuasa.setKuasaPindahMilik('T');
      wakilKuasa.setKuasaGadai('T');
      wakilKuasa.setKuasaLepasGadai('T');
      wakilKuasa.setKuasaKaveat('T');
      wakilKuasa.setKuasaTarikKaveat('T');
      wakilKuasa.setKuasaLepasKaveat('T');
      wakilKuasa.setKuasaPajak('T');
      wakilKuasa.setKuasaPajakKecil('T');
      wakilKuasa.setKuasaSewa('T');
      suratkuasaService.saveWakilKuasa(wakilKuasa);
    }
    LOG.info("-- urusan adalah SB :: END --");
  }

  public List<CaraBayaran> caraBayar(String noResit) {
    List<CaraBayaran> cb = new ArrayList<CaraBayaran>();

    String query = "SELECT dkb FROM etanah.model.DokumenKewanganBayaran dkb WHERE dkb.dokumenKewangan.idDokumenKewangan =:resit ";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("resit", noResit);
    List<DokumenKewanganBayaran> listDKB = q.list();

    for (DokumenKewanganBayaran dkb : listDKB) {
      cb.add(dkb.getCaraBayaran());
    }

    return cb;
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
        } else if ("00".equals(penyerahKod.getKod())) { // JABATAN
          KodCawanganJabatan kj = kodCawanganJabatanDAO.findById(idPenyerah);
          if (kj != null) {
            kj.setNama(penyerahNama);
            kj.setAlamat1(penyerahAlamat1);
            kj.setAlamat2(penyerahAlamat2);
            kj.setAlamat3(penyerahAlamat3);
            kj.setAlamat4(penyerahAlamat4);
            kj.setPoskod(penyerahPoskod);
            kj.setNegeri(penyerahNegeri);
            kj.setNoTelefon1(penyerahNoTelefon);
            kodCawanganJabatanDAO.update(kj);
          }
        } else if (("05".equals(penyerahKod.getKod())) || ("06".equals(penyerahKod.getKod()))) {
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
          // p.setNama(penyerahNama);
//                    p.setSuratAlamat1(penyerahAlamat1);
//                    p.setSuratAlamat2(penyerahAlamat2);
//                    p.setSuratAlamat3(penyerahAlamat3);
//                    p.setSuratAlamat4(penyerahAlamat4);
//                    p.setSuratPoskod(penyerahPoskod);
//                    p.setSuratNegeri(penyerahNegeri);
          // To store data in Alamat
          p.setNama(penyerahNama);
          p.setAlamat1(penyerahAlamat1);
          p.setAlamat2(penyerahAlamat2);
          p.setAlamat3(penyerahAlamat3);
          p.setAlamat4(penyerahAlamat4);
          p.setPoskod(penyerahPoskod);
          p.setNegeri(penyerahNegeri);
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

    return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/penyerah.jsp");
  }

  @HandlesEvent("tambahUrusan")
  @DontValidate
  public Resolution tambahUrusan() {
    return new RedirectResolution(PemohonanPembangunanKaunter.class).addParameter("tambahUrusan", "Y");
  }

  @HandlesEvent("tambahBayaran")
  @DontValidate
  public Resolution tambahBayaran() {
    return new RedirectResolution(BayaranPelbagaiActionBean2.class);
  }

  @HandlesEvent("editUrusan")
  @DontValidate
  public Resolution editUrusanCache() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    List<UrusanKaunter> luc = bayaranSessionService.getAllUrusanFromSession(ctx);
    if (debug) {
      LOG.debug("bayaranSessionService.getAllUrusanFromSession(ctx).size=" + luc.size());
      LOG.debug("selectedItem=" + selectedItem);
    }

    if (selectedItem < 0 || selectedItem >= luc.size()) {
      addSimpleError("Item tidak sah");
      return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/bayaran_2.jsp");
    }

    return new RedirectResolution(luc.get(selectedItem).getEditActionBean())
            .addParameter("selectedItem", String.valueOf(selectedItem));
  }

  @HandlesEvent("removeUrusan")
  @DontValidate
  public Resolution removeUrusanCache() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

    int left = bayaranService.removeUrusan(ctx, selectedItem);

    if (left == 0) { // no more Urusan to register
      addSimpleMessage("Urusan yang dipilih telah dihapuskan. Tiada urusan untuk didaftarkan");
      return new RedirectResolution(PermohonanKaunter2.class);
    }

    addSimpleMessage("Urusan yang dipilih telah dihapuskan.");

    return new ForwardResolution("/WEB-INF/jsp/pembangunan/permohonan/bayaran_2.jsp");
  }

  @HandlesEvent("CancelAll")
  public Resolution cancelAll() {
    resetAll();

    return new RedirectResolution(PermohonanKaunter2.class);
  }

  private void resetAll() {
    if (debug) {
      LOG.debug("invoked");
    }

    idPenyerah = null;
    penyerahKod = null;
    penyerahJenisPengenalan = null;
    penyerahNoPengenalan = null;
    penyerahNama = null;
    penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4 =
            penyerahPoskod = penyerahEmail = penyerahNoTelefon  = namaPenyampai = 
            noPengenalanPenyampai = noTelPenyampai = null;
    penyerahNegeri = null;

    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

    resetPenyerahFromSession(ctx);

    // reset cara bayaran
    if (senaraiCaraBayaran != null) {
      senaraiCaraBayaran.clear();
    }

    bayaranSessionService.resetAll(ctx);
  }

  private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) throws ParseException {
    ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
    //        for (CaraBayaran cb : senaraiCaraBayaran) {
    for (int m = 0; m < senaraiCaraBayaran.size(); m++) {
      CaraBayaran cb = senaraiCaraBayaran.get(m);
      if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
              && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
        // clear if null
        if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
          cb.setBank(null);
          cb.setBankCawangan(null);
        }
        KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
        if ((crByr.getKod().equals("CB")) || (crByr.getKod().equals("CC")) || (crByr.getKod().equals("CT"))
                || (crByr.getKod().equals("CL")) || (crByr.getKod().equals("IB")) || (crByr.getKod().equals("BC"))) {
          Date d = sdf.parse(tarikhCek.get(m));
          cb.setTarikhCek(d);
        }
        if (cb.getBank() != null) {
          KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
          cb.setBank(bank);
        }
        if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
          cb.setBank(kodBankDAO.findById("PMB"));
        }
        cb.setCawangan(caw);
        cb.setInfoAudit(ia);
        cb.setAktif('Y');
        cb.setKodCaraBayaran(crByr);
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

  private static final boolean isUrusanNull(UrusanKaunter uv) {
    return (uv == null || uv.getKodUrusan() == null || "0".equals(uv.getKodUrusan()));
  }

  private void savePenyerahToSession(etanahActionBeanContext ctx) {
    HttpSession s = ctx.getRequest().getSession();
    s.setAttribute("idPenyerah", idPenyerah);
    s.setAttribute("penyerahKod", penyerahKod);
    s.setAttribute("penyerahJenisPengenalan", penyerahJenisPengenalan);
    s.setAttribute("penyerahNoPengenalan", penyerahNoPengenalan);
    s.setAttribute("penyerahNama", penyerahNama);
    s.setAttribute("penyerahAlamat1", penyerahAlamat1);
    s.setAttribute("penyerahAlamat2", penyerahAlamat2);
    s.setAttribute("penyerahAlamat3", penyerahAlamat3);
    s.setAttribute("penyerahAlamat4", penyerahAlamat4);
    s.setAttribute("penyerahPoskod", penyerahPoskod);
    s.setAttribute("penyerahEmail", penyerahEmail);
    s.setAttribute("penyerahNoTelefon", penyerahNoTelefon);
    s.setAttribute("namaPenyampai", namaPenyampai);
    s.setAttribute("noPengenalanPenyampai", noPengenalanPenyampai);
    s.setAttribute("noTelPenyampai", noTelPenyampai);
    s.setAttribute("penyerahNegeri", penyerahNegeri);
  }

  private void loadPenyerahFromSession(etanahActionBeanContext ctx) {
    HttpSession s = ctx.getRequest().getSession();
    idPenyerah = (String) s.getAttribute("idPenyerah");
    penyerahKod = (KodPenyerah) s.getAttribute("penyerahKod");
    penyerahJenisPengenalan = (KodJenisPengenalan) s.getAttribute("penyerahJenisPengenalan");
    penyerahNoPengenalan = (String) s.getAttribute("penyerahNoPengenalan");
    penyerahNama = (String) s.getAttribute("penyerahNama");
    penyerahAlamat1 = (String) s.getAttribute("penyerahAlamat1");
    penyerahAlamat2 = (String) s.getAttribute("penyerahAlamat2");
    penyerahAlamat3 = (String) s.getAttribute("penyerahAlamat3");
    penyerahAlamat4 = (String) s.getAttribute("penyerahAlamat4");
    penyerahPoskod = (String) s.getAttribute("penyerahPoskod");
    penyerahEmail = (String) s.getAttribute("penyerahEmail");
    penyerahNoTelefon = (String) s.getAttribute("penyerahNoTelefon");
    penyerahNegeri = (KodNegeri) s.getAttribute("penyerahNegeri");
    namaPenyampai = (String) s.getAttribute("namaPenyampai");
    noTelPenyampai = (String) s.getAttribute("noTelPenyampai");
    noPengenalanPenyampai = (String) s.getAttribute("noPengenalanPenyampai");
  }

  private void resetPenyerahFromSession(etanahActionBeanContext ctx) {
    HttpSession s = ctx.getRequest().getSession();
    s.removeAttribute("idPenyerah");
    s.removeAttribute("penyerahKod");
    s.removeAttribute("penyerahJenisPengenalan");
    s.removeAttribute("penyerahNoPengenalan");
    s.removeAttribute("penyerahNama");
    s.removeAttribute("penyerahAlamat1");
    s.removeAttribute("penyerahAlamat2");
    s.removeAttribute("penyerahAlamat3");
    s.removeAttribute("penyerahAlamat4");
    s.removeAttribute("penyerahPoskod");
    s.removeAttribute("penyerahEmail");
    s.removeAttribute("penyerahNoTelefon");
    s.removeAttribute("penyerahNegeri");
    s.removeAttribute("namaPenyampai");
    s.removeAttribute("noTelPenyampai");
    s.removeAttribute("noPengenalanPenyampai");
    s.removeAttribute("idSblm");
  }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }
  
}
