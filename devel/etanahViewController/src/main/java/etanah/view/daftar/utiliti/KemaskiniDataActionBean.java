/*
 * 'UTILI KUTIPAN DATA' is used to manually add hakmilik from SPTB
 *  user can add either single hakmilik or bundle
 *  version must be '0'
 * 
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PihakDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerhubunganPermohonanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodPerintahDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.dao.KodPenubuhanSyarikatDAO;
import etanah.model.Akaun;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanHubungan;
import etanah.model.KodDaerah;
import etanah.model.KodFlagPihak;
import etanah.model.KodHakmilik;
import etanah.model.KodJabatan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodSyaratNyata;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.FolderDokumen;
import etanah.model.KodDokumen;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.FasaPermohonan;
import etanah.report.CallableReport;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahanPaksa;
import etanah.service.RegService;
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.ListUtil;
import etanah.service.daftar.KutipanDataService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.sourceforge.stripes.action.Before;
//import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ei
 */
@UrlBinding("/daftar/utiliti/kemaskiniData")
public class KemaskiniDataActionBean extends AbleActionBean {

  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  @Inject
  GeneratorIdPerserahanPaksa generatorIdPerserahanPaksa;
  /* INJECT DAO  */
  @Inject
  private KodDaerahDAO kodDaerahDAO;
  @Inject
  private KodNegeriDAO kodNegeriDAO;
  @Inject
  private HakmilikDAO hakmilikDAO;
  @Inject
  KodBandarPekanMukimDAO kodBPMDAO;
  @Inject
  PihakDAO pihakDAO;
  @Inject
  HakmilikPihakBerkepentinganDAO hpbDAO;
  @Inject
  KodSyaratNyataDAO kodSyaratNyataDAO;
  @Inject
  KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
  @Inject
  KodJenisPengenalanDAO kodJenisPengenalanDAO;
  @Inject
  KodBangsaDAO kodBangsaDAO;
  @Inject
  KodWarganegaraDAO kodWarganegaraDAO;
  @Inject
  KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
  @Inject
  HakmilikUrusanDAO hakmilikUrusanDAO;
  @Inject
  KodUrusanDAO kodUrusanDAO;
  @Inject
  KodHakmilikDAO kodHakmilikDAO;
  @Inject
  KodKegunaanTanahDAO kodGunaTanahDAO;
  @Inject
  KodKategoriTanahDAO kodkatgTanahDAO;
  @Inject
  KodRujukanDAO kodRujukanDAO;
  @Inject
  KodPerintahDAO kodPerintahDAO;
  @Inject
  KodKlasifikasiDAO kodKlasifikasiDAO;
  @Inject
  private KodSeksyenDAO kodSeksyenDAO;
  @Inject
  private FolderDokumenDAO folderDokumenDAO;
  @Inject
  private KodKeputusanDAO kodKeputusanDAO;
  @Inject
  private HakmilikPermohonanDAO hakmilikPermohonanDAO;
  @Inject
  private KodPerhubunganPermohonanDAO kodPerhubunganPermohonanDAO;
  @Inject
  private PermohonanHubunganDAO permohonanHubunganDAO;
  @Inject
  private SejarahHakmilikDAO sejarahHakmilikDAO;
  /* INJECT SERVICE */
  @Inject
  private RegService regService;
  @Inject
  ReportUtil reportUtilKE;
  @Inject
  ReportUtil reportUtilDE;
  @Inject
  DokumenService dokumenService;
  @Inject
  HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
  @Inject
  PihakService pihakService;
  @Inject
  KutipanDataService kutipanDataService;
  @Inject
  PermohonanService permohonanService;
  @Inject
  HakmilikUrusanService huService;
  @Inject
  PermohonanPihakService permohonanPihakService;
  @Inject
  FasaPermohonanService fasaPermohonanService;
  @Inject
  PemohonService pemohonService;
  @Inject
  ListUtil listUtil;
  @Inject
  KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
  private String kodDaerah;  // kod daerah
  private String kodNegeri;  // kod negeri
  private String namaNegeri; // nama negeri
  private String bpm;              // nama bandar pekan mukim
  private String idHakmilik;       // id hakmilik
  private String noHakmilik;       // no hakmiilk 
  private String kodJenisHakmilik; // kod hakmilik e.g:GRN,HSD,GM,PM,etc..
  private String kodSyarat;
  private String namaSyarat;
  private String kodSekatan;
  private String namaSekatan;
  private String kodKatTanah;   // kod_katg_tanah
  private String kodGunaTanah;  // kod_guna_tanah
  private String idPihak;
  private String jenisUrusan;
  private String kodUrusan;
  private String tarikhDaftar;
  private String tarikhDaftarAsal;  
  private String jam;
  private String minit;
  private String saat;
  private String ampm;
  private String idDokumen;
  private String kodPelan = "";
  private int kumpHm; // use to group hakmilik
  private String idHP;
  private String aktif;
  /* object */
  private KodHakmilik kodHakmilik;
  private Hakmilik hakmilik;
  private Pengguna pguna;
  private HakmilikPihakBerkepentingan hakmilikPihak;
  private Pihak pihak;
  private HakmilikUrusan hakmilikUrusan;
  private Permohonan mohon;
  private PermohonanRujukanLuar mohonRujLuar;
  private PermohonanUrusan mohonUrusan;
  private PermohonanPihak permohonanPihak;
  private Pemohon pemohon;
  private Date trhDaftar;
  private Date trhDaftarAsal;
//  private Dokumen dokumen;
  /* List */
  private List<String> noHakmilikSiriDari = new ArrayList<String>();   // No hakmilik bersiri
  private List<String> noHakmilikSiriHingga = new ArrayList<String>(); // No Hakmilik bersiri
  private List<String> idHakmilikSiriDari = new ArrayList<String>();   // ID Hakmilik bersiri
  private List<String> idHakmilikSiriHingga = new ArrayList<String>(); // ID Hakmilik bersiri
  private ArrayList<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
  private List<Hakmilik> listSenaraiHakmilik = new ArrayList();
  private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
  private List<HakmilikSebelum> listHakmilikSblm = new ArrayList();
  private List<KodHakmilik> senaraiKodHakmilik = new ArrayList();
  private List<KodDaerah> senaraiKodDaerah = new ArrayList();
  private List<HakmilikPihakBerkepentingan> listHakmilikPihak = new ArrayList();
  private List<Pihak> listPihak = new ArrayList();
  private List<Akaun> listKewAkaun = new ArrayList();
  private List<KodBandarPekanMukim> listBandarPekanMukim;
  private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
  private List<KodSeksyen> listKodSeksyen;
  private List<KodSyaratNyata> lisSyaratNyata;
  private List<KodSekatanKepentingan> lisSekatan;
  private List<KodJenisPihakBerkepentingan> senaraiKodPihak;
  private List<HakmilikUrusan> listHakmilikUrusanSC;
  private List<HakmilikUrusan> listHakmilikUrusanB;
  private List<HakmilikUrusan> listHakmilikUrusanN;
  private List<HakmilikUrusan> listHakmilikUrusanNB;
  private List<HakmilikUrusan> listHakmilikUrusanSebelum;
  private List<PermohonanHubungan> listMohonHbgn;
  private List<KodUrusan> listkodUrusan;
  private List<KodUrusan> listkodUrusanN;
  private List<KodUrusan> listkodUrusanB;
  private List<KodUrusan> listkodUrusanSC;
  private List<KodUrusan> listkodUrusanNB;
  private List<PermohonanPihak> listMohonPihak;
  private List<Pemohon> listPemohon;
  private boolean kelompok = false;
  private boolean refresh = false; // use this for refreshing page
  private boolean pemegang = false;
  private boolean flag = false;
  public String selectedTab = "0";
  private int bilHakmilik = 1;
  private static final Logger LOG = Logger.getLogger(KemaskiniDataActionBean.class);
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
  /* JSP file name */
  private static String KUTIPAN_MAIN = "daftar/utiliti/kutipan_data_main.jsp";
  private static String KUTIPAN_TAB = "daftar/utiliti/kemaskini_data_tab.jsp";
  private static String MAKLUMAT_HAKMILIK_DETAIL = "daftar/utiliti/kemaskini_data_detailHakmilik.jsp";
  private static String POPUP_PIHAK = "daftar/utiliti/kemaskini_data_pihak.jsp";   // POP-UP FOR ADDING PIHAK
  private static String KEMASKINI_PIHAK = "daftar/utiliti/kemaskini_data_kemaskiniPihak.jsp"; // POP-UP FOR KEMASKINI HAKMILIK_PIHAK
  private static String POPUP_URUSAN = "daftar/utiliti/kemaskini_data_tambahUrusan.jsp";  // POP-UP FOR ADDING URUSAN
  //  @DefaultHandler
  
  @Before(stages = {LifecycleStage.BindingAndValidation})
  public void rehydrate() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pguna = ctx.getUser();
    kodNegeri = conf.getProperty("kodNegeri");
    KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
    namaNegeri = kn.getNama();

    if (!pguna.getKodCawangan().getKod().equals("00")) {
      // use to get senarai daerah for PTD
      kodDaerah = pguna.getKodCawangan().getDaerah().getKod();
      senaraiKodDaerah = regService.cariDaerah(kodDaerah);
    } else {
        senaraiKodDaerah = getSenaraiKodDaerahPTG();
    }
  }
  
  public Resolution seterusnya() {
    // KUTIPAN DATA PER HAKMILIK
    Hakmilik hm = new Hakmilik();
    String idHakmilikBaru = "";
    selectedTab = getContext().getRequest().getParameter("selectedTab");
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pguna = ctx.getUser();
   LOG.info("~~~~~~Masuk~~~~~~");
    if (StringUtils.isNotBlank(idHakmilik)) {
      // SEARCH-BY-ID HAKMILIK
//      hm = hakmilikDAO.findById(idHakmilik);
     
      hm = kutipanDataService.findHakmilikbyIdhakmilikandCaw(idHakmilik, pguna.getKodCawangan().getKod());
      if (hm == null) {
        addSimpleError("Harap maaf. Id Hakmilik " + idHakmilik + " tidak wujud.");
        return new JSP(KUTIPAN_MAIN);
      }
    } else if (StringUtils.isNotBlank(bpm) && StringUtils.isNotBlank(kodJenisHakmilik) && StringUtils.isNotBlank(noHakmilik)) {
      // SEARCH-BY-NO HAKMILIK
      KodHakmilik kodhm = kodHakmilikDAO.findById(kodJenisHakmilik);
      if (kodhm == null) { // CHECK JENIS HAKMILIk      
        addSimpleError("Jenis hakmilik " + kodJenisHakmilik + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      KodBandarPekanMukim kodbpm = kutipanDataService.findKodBPM(bpm, kodDaerah);
      if (kodbpm == null) { // CHECK BANDAR PEKAN MUKIM      
        addSimpleError("Bandar / Pekan / Mukim " + bpm + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      idHakmilikBaru = kodNegeri + kodDaerah + bpm + kodJenisHakmilik + noHakmilik;
      LOG.info("--> id hakmilik baru : " + idHakmilikBaru);
      hm = hakmilikDAO.findById(idHakmilikBaru); // check for id hakmilik
    } else {
      addSimpleError("Sila masukkan maklumat Id Hakmilik atau Bandar / Pekan / Mukim, Jenis Hakmilik dan No Hakmilik.");
      return new JSP(KUTIPAN_MAIN);
    }

    Integer kumpulan = kutipanDataService.getIdKumpMaxNum();
    kumpHm = (kumpulan + 1); // use kumpulan to group hakmilik

    if (hm != null) {
      if (hm.getNoVersiDhde() > 0) {
        // NOTES: need to check version of hakmilik. only version '0' are allowed to be edit
        addSimpleError("ID Hakmilik " + idHakmilikBaru + " telah wujud dan ia versi " + hm.getNoVersiDhde() + " .");
        return new JSP(KUTIPAN_MAIN);
      } else if (hm.getKodStatusHakmilik().equals('B')) {
        // NOTEs: check hakmilik must not 'BATAL'
        addSimpleError("ID Hakmilik " + idHakmilikBaru + " telah batal.");
        return new JSP(KUTIPAN_MAIN);
      } else {
        hm.setKumpulan(kumpHm);  // FOR KUTIPAN DATA -> need to set kod_sumber as 'DE'
        listHakmilik.add(hm);
      }
    } else {
      // GENERATE NEW ID HAKMILIK
      janaHakmilik(idHakmilikBaru, noHakmilik);
    }

    if (!listHakmilik.isEmpty()) {  // save hakmilik
      regService.simpanHakmilikList(listHakmilik);
    }

    idHakmilik = null;
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm); // list hakmilik  
//    String kodAkaunCukai = "AC";
    getKewAkaun("AC", listSenaraiHakmilik);
    senaraiHakmilikUrusan(kumpHm); // find list urusan     
    return new JSP(KUTIPAN_TAB);
  }

  public Resolution seterusnyaBerkelompok() {
    // KUTIPAN DATA BERKELOMPAK 
    String idH1;
    String idH2;
    String idHakmilikBaru;

    if (!idHakmilikSiriDari.isEmpty() && !idHakmilikSiriHingga.isEmpty()) {
      idH1 = idHakmilikSiriDari.get(0);
      idH2 = idHakmilikSiriHingga.get(0);

    } else if (!noHakmilikSiriDari.isEmpty() && !noHakmilikSiriHingga.isEmpty()) {
      if (bpm == null || kodJenisHakmilik == null) {
        addSimpleError("Sila masukkan maklumat Bandar / Pekan / Mukin dan Jenis Hakmilik.");
        return new JSP(KUTIPAN_MAIN);
      }

      KodHakmilik kodhm = kodHakmilikDAO.findById(kodJenisHakmilik);
      if (kodhm == null) { // CHECK JENIS HAKMILIk      
        addSimpleError("Jenis hakmilik " + kodJenisHakmilik + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      KodBandarPekanMukim kodbpm = kutipanDataService.findKodBPM(bpm, kodDaerah);
      if (kodbpm == null) { // CHECK BANDAR PEKAN MUKIM      
        addSimpleError("Bandar / Pekan / Mukim " + bpm + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      idH1 = noHakmilikSiriDari.get(0);
      idH2 = noHakmilikSiriHingga.get(0);

    } else {
      // If input not complete
      addSimpleError("Maaf maklumat tidak lengkap. Sila masukkan maklumat yang berkenaan.");
      return new JSP(KUTIPAN_MAIN);
    }

    Integer kumpulan = kutipanDataService.getIdKumpMaxNum();
    kumpHm = (kumpulan + 1); // use kumpulan to group hakmilik

    ArrayList<String> list = getNoHakmilikBerkelompok(idH1, idH2);
    Hakmilik hm = new Hakmilik();
    for (String string : list) {
      if (!idHakmilikSiriDari.isEmpty() && !idHakmilikSiriHingga.isEmpty()) {
        idHakmilikBaru = string;
        hm = kutipanDataService.findHakmilikbyIdhakmilikandCaw(idHakmilikBaru, pguna.getKodCawangan().getKod());
        if (hm == null) {
          hm = hakmilikDAO.findById(idHakmilikBaru);
          if (hm == null) {
            addSimpleError("Terdapat hakmilik yang tidak wujud.");
            return new JSP(KUTIPAN_MAIN);
          }
          addSimpleError("Terdapat hakmilik daripada cawangan lain.");
          return new JSP(KUTIPAN_MAIN);
        }
      } else {
        /* MANUAL GENERATE ID HAKMILIK */
        idHakmilikBaru = kodNegeri + kodDaerah + bpm + kodJenisHakmilik + string;
        hm = hakmilikDAO.findById(idHakmilikBaru);
      }

      hm = hakmilikDAO.findById(idHakmilikBaru);
      if (hm != null) {
//          listHakmilik.clear(); // clear array
        LOG.info("  >> " + hm.getKodStatusHakmilik().getKod());
        LOG.info("  >> " + hm.getNoVersiDhde());

        if (hm.getNoVersiDhde() > 0) {
          // CHECK VERSION
          addSimpleError("Terdapat versi hakmilik bukan '0' dalam siri Id Hakmilik.");
          return new JSP(KUTIPAN_MAIN);
        } else if (hm.getKodStatusHakmilik().equals("B")) {
          // CHECK STATUS HAKMILIK
          addSimpleError("Terdapat hakmilik 'Batal' dalam siri Id Hakmilik.");
          return new JSP(KUTIPAN_MAIN);
        } else {
          hm.setKumpulan(kumpHm);
          hm.setKodSumber("DE");    // FOR KUTIPAN DATA -> need to set kod_sumber as 'DE'
          InfoAudit ia = new InfoAudit();
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          hm.setInfoAudit(ia);
          listHakmilik.add(hm);
        }
      } else {
        // create hakmilik
        janaHakmilik(idHakmilikBaru, string);
      }
    }
    if (!listHakmilik.isEmpty()) {  // save many hakmilik
      regService.simpanHakmilikList(listHakmilik);
    }

    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm); // list hakmilik
    getKewAkaun("AC", listSenaraiHakmilik);
    senaraiHakmilikUrusan(kumpHm);
    return new JSP(KUTIPAN_TAB);
  }

  private ArrayList<String> getNoHakmilikBerkelompok(String noHakmilikSiriDari, String noHakmilikSiriHingga) {
    // GET ALL NO HAKMILIK BERSIRI 
    StringBuilder from = new StringBuilder();

    for (int i = noHakmilikSiriDari.length() - 1; i >= 0; i--) {
      char c = noHakmilikSiriDari.charAt(i);
      if (c >= '0' && c <= '9') {
        from.insert(0, c);
      } else {
        break;
      }
    }

    long lFrom = Long.parseLong(from.toString()); // from
    String pre = noHakmilikSiriDari.substring(0, noHakmilikSiriDari.length() - from.length());
    long lTo = 0l; // to
    try {
      lTo = Long.parseLong(noHakmilikSiriHingga.substring(pre.length(), noHakmilikSiriDari.length()));
    } catch (NumberFormatException e) {
      throw new RuntimeException("No Hakmilik bersiri tidak sah");
    }

    ArrayList<String> listNoHakmilik = new ArrayList<String>();
    // validate the series along the way
    if (noHakmilikSiriDari.length() != noHakmilikSiriHingga.length()
            || !noHakmilikSiriDari.substring(0, pre.length()).equals(noHakmilikSiriHingga.substring(0, pre.length()))
            || lTo < lFrom) {
      throw new RuntimeException("No Hakmilik bersiri tidak sah");
    } else {
      listNoHakmilik.add(noHakmilikSiriDari); // add first array
      DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
      df.setGroupingUsed(false);
      df.setMinimumIntegerDigits(from.length());

      for (long l = lFrom + 1; l
              < lTo; l++) {
        String id = pre + df.format(l);
        listNoHakmilik.add(id);
      }
      listNoHakmilik.add(noHakmilikSiriHingga); // add last array
    }
    return listNoHakmilik;
  }

  public Resolution refreshPage() {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    selectedTab = getContext().getRequest().getParameter("selectedTab");
    String eq = getContext().getRequest().getParameter("eq");
    
    LOG.info(">>>>>>>>> selectedTab : " + selectedTab);
    if (idHakmilik != null) {

      hakmilik = hakmilikDAO.findById(idHakmilik);
      if (hakmilik != null) {
        int kodBPM = hakmilik.getBandarPekanMukim().getKod();
        cariKodBPM(kodDaerah); // find kodBPM     
        cariKodSeksyen(kodBPM);  // find seksyen
        if (hakmilik.getSyaratNyata() != null) {
          kodSyarat = hakmilik.getSyaratNyata().getKod();
        }
        if (hakmilik.getSekatanKepentingan() != null) {
          kodSekatan = hakmilik.getSekatanKepentingan().getKod();
        }
       
        if (hakmilik.getKategoriTanah() != null) {
            kodKatTanah = hakmilik.getKategoriTanah().getKod();
          listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(hakmilik.getKategoriTanah().getKod());
        }
        listHakmilikPihak = kutipanDataService.findListPemilik2(idHakmilik);
        listHakmilikAsal = kutipanDataService.findListHakmilikAsalByIdHakmilik(idHakmilik);    // find list hakmilik asal
        listHakmilikSblm = kutipanDataService.findListHakmilikSebelumByIdHakmilik(idHakmilik); // find list hakmilik sebelum
      } else {
        idHakmilik = null;
      }
    }

    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    senaraiHakmilikUrusan(kumpHm);
    
    if ( StringUtils.isNotBlank(eq) && !eq.equals("0")) {
        int e = Integer.parseInt(eq);
        if (e < 0) addSimpleError("Syer kurang daripada satu");
        else if (e > 0) addSimpleError("Syer lebih daripada satu");
    }
    
    return new JSP(KUTIPAN_TAB).addParameter("kumpHm", kumpHm);
  }

  public Resolution maklumatDetail() throws ParseException {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    LOG.info("id hakmilik -->"+idHakmilik);
    hakmilik = hakmilikDAO.findById(idHakmilik);
    LOG.info("hakmilik -->"+hakmilik);
    
    if(hakmilik.getKumpulan()==null){
    LOG.info("~~~~~Update no kump ~~~~");    
    Integer kumpulan = kutipanDataService.getIdKumpMaxNum();
    kumpHm = (kumpulan + 1);
    hakmilik.setKumpulan(kumpHm);
    listHakmilik.add(hakmilik);
    regService.simpanHakmilikList(listHakmilik);
    }
    
    if (idHakmilik != null) {
      int kodBPM = hakmilik.getBandarPekanMukim().getKod();
      kumpHm = hakmilik.getKumpulan();
      cariKodBPM(kodDaerah); // find kodBPM     
      cariKodSeksyen(kodBPM);  // find seksyen

      if (hakmilik.getSyaratNyata() != null) {
        kodSyarat = hakmilik.getSyaratNyata().getKod();
      }
      if (hakmilik.getSekatanKepentingan() != null) {
        kodSekatan = hakmilik.getSekatanKepentingan().getKod();
      }
      if (hakmilik.getKategoriTanah() != null) {
        kodKatTanah = hakmilik.getKategoriTanah().getKod();
        listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(hakmilik.getKategoriTanah().getKod());
      }
      listHakmilikPihak = kutipanDataService.findListPemilik2(idHakmilik);                    // find list pemilik 
      listHakmilikAsal = kutipanDataService.findListHakmilikAsalByIdHakmilik(idHakmilik);    // find list hakmilik asal
      listHakmilikSblm = kutipanDataService.findListHakmilikSebelumByIdHakmilik(idHakmilik); // find list hakmilik sebelum
    }

    senaraiHakmilikUrusan(kumpHm);
    return new JSP(KUTIPAN_TAB).addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm).addParameter("popup", "true");
  }

  private void janaHakmilik(String idHakmilikBaru, String noHm) {
    /* *
     *  Use this to generate new id hakmilik. for new hakmilik, 
     * "kod status hakmilik" = T ('Belum Daftar')
     * format hakmilik --> kodNegeri + kodDaerah + bpm + kodJenisHakmilik + noHakmilik 
     * FOR KUTIPAN DATA --> need to set kod_sumber as 'DE'
     */
    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());

    /* Save new id Hakmilik in table hakmilik */
    Hakmilik hm = new Hakmilik();
    hm.setIdHakmilik(idHakmilikBaru);
    hm.setCawangan(pguna.getKodCawangan());
    KodDaerah daerah = kodDaerahDAO.findById(kodDaerah);
    hm.setDaerah(daerah);
    KodBandarPekanMukim kodBpm = regService.cariBPM(bpm, kodDaerah);
    hm.setBandarPekanMukim(kodBpm);
//    hm.setSeksyen(null); //FIX ME
    KodHakmilik khm = new KodHakmilik();
    khm.setKod(kodJenisHakmilik);
    hm.setKodHakmilik(khm);
    hm.setNoHakmilik(noHm);
    KodStatusHakmilik statusHakmilik = new KodStatusHakmilik();
//    if (kodJenisHakmilik.equals("EMR")) {
//        statusHakmilik.setKod("P");
//    } else {
//        statusHakmilik.setKod("D");
//    }
    statusHakmilik.setKod("D");    
    hm.setKodStatusHakmilik(statusHakmilik);
    hm.setLotBumiputera('T');
    hm.setMilikPersekutuan('T');
    hm.setLuas(BigDecimal.ZERO);
    hm.setNoVersiDhde(0);
    hm.setNoVersiDhke(0);
    hm.setKumpulan(kumpHm);
    hm.setKodSumber("DE");
    KodUOM kodUOM = new KodUOM();
    kodUOM.setKod("M");
    hm.setKodUnitLuas(kodUOM);
    hm.setInfoAudit(ia);
    listHakmilik.add(hm);
  }

  private void janaPermohonan(KodUrusan ku, String noJilid, String noFolio) {
    /* *
     * Use this to generate new id Mohon.  
     */
    InfoAudit ia = new InfoAudit();
    KodJabatan k = new KodJabatan();
    k.setAkronim("F");
    String idPermohonan = generatorIdPerserahanPaksa.generate(kodNegeri, pguna.getKodCawangan(), ku);
    LOG.info("---> id mohon paksa : " + idPermohonan);
//    LOG.info(" > tarikh Daftar : " + trhDaftar);
//    LOG.info(" > no Jilid : " + noJilid);
//    LOG.info(" > no Folio : " + noFolio);

    /* Save Dokumen folder */
    FolderDokumen fd = new FolderDokumen();
    fd.setTajuk("-");
    fd.setNoFolio(noFolio);
    fd.setNoJilid(noJilid);
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());
    fd.setInfoAudit(ia);
    folderDokumenDAO.save(fd);

    /* Save new id Mohon in table_mohon */
    mohon = new Permohonan();
    mohon.setIdPermohonan(idPermohonan);
    mohon.setCawangan(pguna.getKodCawangan());
    mohon.setKodUrusan(ku);
    mohon.setStatus("SL");
    mohon.setKeputusan(kodKeputusanDAO.findById("D"));
    mohon.setKeputusanOleh(pguna);
    mohon.setFolderDokumen(fd);
    if (trhDaftar != null) {
      mohon.setTarikhKeputusan(trhDaftar);
    } else {
      mohon.setTarikhKeputusan(new java.util.Date()); // KIV for tarikh keputusan
    }
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(trhDaftar);
    mohon.setInfoAudit(ia);
    permohonanService.saveOrUpdate(mohon);

    /* ADD MOHON_FASA
     * need to create MOHON_FASA or error in vdoc
     * */
    FasaPermohonan f = new FasaPermohonan();
    f.setCawangan(mohon.getCawangan());
    f.setPermohonan(mohon);
    f.setIdAliran("kemasukan"); // add fasa kemasukkan
    f.setIdPengguna(pguna.getIdPengguna());
    f.setTarikhKeputusan(trhDaftar);
    f.setTarikhKeputusan(trhDaftar);
    f.setTarikhHantar(trhDaftar);
    f.setKeputusan(kodKeputusanDAO.findById("SD"));
    ia.setTarikhMasuk(trhDaftar);
    f.setInfoAudit(ia);
    fasaPermohonanService.saveOrUpdate(f);

    FasaPermohonan f2 = new FasaPermohonan();
    f2.setCawangan(mohon.getCawangan());
    f2.setPermohonan(mohon);
    f2.setIdAliran("keputusan"); // add fasa keputusan
    f2.setIdPengguna(pguna.getIdPengguna());
    f2.setTarikhKeputusan(trhDaftar);
    f2.setTarikhKeputusan(trhDaftar);
    f2.setTarikhHantar(trhDaftar);
    f2.setKeputusan(kodKeputusanDAO.findById("D"));
    ia.setTarikhMasuk(trhDaftar);
    f2.setInfoAudit(ia);
    fasaPermohonanService.saveOrUpdate(f2);
  }

  public Resolution simpanPerinci() {
    LOG.info("SIMPAN PERINCIAN");
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
    Hakmilik hm = hakmilikDAO.findById(idHakmilik);
    InfoAudit ia = new InfoAudit();

    if (hm != null) {
      hm.setNoFail(hakmilik.getNoFail());
      hm.setLokasi(hakmilik.getLokasi());
      if (hakmilik.getSeksyen() != null) {
          hm.setSeksyen(kodSeksyenDAO.findById(hakmilik.getSeksyen().getKod()));
      }
      if (hakmilik.getKegunaanTanah() != null) {
        hm.setKegunaanTanah(kodGunaTanahDAO.findById(hakmilik.getKegunaanTanah().getKod()));
      }
      if (kodKatTanah != null) {
        hm.setKategoriTanah(kodkatgTanahDAO.findById(kodKatTanah));
      }

      if (!getContext().getRequest().getParameter("luasMeter").isEmpty()) {
        // insert unit matric to luas and oum
        BigDecimal luas = new BigDecimal(getContext().getRequest().getParameter("luasMeter"));
        hm.setLuas(luas);
        KodUOM unitlama = new KodUOM();
        unitlama.setKod("M");
        hm.setKodUnitLuas(unitlama);

        // insert ekar rood pole unit and ekar unit to column luas_lama and oum_lama
        hm.setLuasLama(String.valueOf(getContext().getRequest().getParameter("hakmilik.luas")));
        hm.setKodUnitLuasLama(hakmilik.getKodUnitLuas());
      } else {
        hm.setLuas(hakmilik.getLuas());
        hm.setKodUnitLuas(hakmilik.getKodUnitLuas());
      }
      hm.setLot(hakmilik.getLot());
      hm.setNoLot(hakmilik.getNoLot());
      hm.setNoPelan(hakmilik.getNoPelan());
      hm.setNoLitho(hakmilik.getNoLitho());
      hm.setCukai(hakmilik.getCukai());
      hm.setPegangan(hakmilik.getPegangan());
      hm.setTempohPegangan(hakmilik.getTempohPegangan());
      if (kodSyarat != null) {
        KodSyaratNyata ksn = kodSyaratNyataDAO.findById(kodSyarat);
        if (ksn != null) {
          hm.setSyaratNyata(ksn);
        }
      }
      if (kodSekatan != null) {
        KodSekatanKepentingan ksk = kodSekatanKepentinganDAO.findById(kodSekatan);
        if (ksk != null) {
          hm.setSekatanKepentingan(ksk);
        }
      }
      if (hakmilik.getTarikhLuput() != null) {
        hm.setTarikhLuput(hakmilik.getTarikhLuput());
      } else {
        hm.setTarikhLuput(null);
      }
      if (hakmilik.getTarikhDaftar() != null) {
        hm.setTarikhDaftar(hakmilik.getTarikhDaftar());
      }
      if (hakmilik.getTarikhDaftar() != null) {
        hm.setTarikhDaftarAsal(hakmilik.getTarikhDaftarAsal());
      }      
      hm.setNoPu(hakmilik.getNoPu());
      ia.setDiKemaskiniOleh(pguna);
      ia.setTarikhKemaskini(new java.util.Date());
      hm.setInfoAudit(ia);

      lhm.add(hm);
      if (!lhm.isEmpty()) {
        regService.simpanHakmilikList(lhm);
        addSimpleMessage("Maklumat hakmilik berjaya dikemaskini.");
      }
    } else {
      addSimpleError("Maaf. Maklumat tidak berjaya dikemaskini!");
    }
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution simpanPerinciKelompok() {
    LOG.info("/* SIMPAN PERINCI KELOMPOK */");
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
    InfoAudit ia = new InfoAudit();

    int noLot = 0;
    for (Hakmilik hm : listSenaraiHakmilik) {
      Hakmilik hm1 = hakmilikDAO.findById(hm.getIdHakmilik());

      if (hm1 != null) {
        hm1.setNoFail(hakmilik.getNoFail());
        hm1.setLokasi(hakmilik.getLokasi());
        if (hakmilik.getKegunaanTanah() != null) {
          hm1.setKegunaanTanah(kodGunaTanahDAO.findById(hakmilik.getKegunaanTanah().getKod()));
        }
        if (kodKatTanah != null) {
          hm1.setKategoriTanah(kodkatgTanahDAO.findById(kodKatTanah));
        }

        if (!getContext().getRequest().getParameter("luasMeter").isEmpty()) {
          // insert unit matric to luas and oum
          BigDecimal luas = new BigDecimal(getContext().getRequest().getParameter("luasMeter"));
          hm.setLuas(luas);
          KodUOM unitlama = new KodUOM();
          unitlama.setKod("M");
          hm.setKodUnitLuas(unitlama);

          // insert ekar rood pole unit and ekar unit to column luas_lama and oum_lama
          hm.setLuasLama(String.valueOf(getContext().getRequest().getParameter("hakmilik.luas")));
          hm.setKodUnitLuasLama(hakmilik.getKodUnitLuas());
        } else {
          hm.setLuas(hakmilik.getLuas());
          hm.setKodUnitLuas(hakmilik.getKodUnitLuas());
        }

        hm1.setLot(hakmilik.getLot());
        if (hakmilik.getNoLot()!= null && !hakmilik.getNoLot().equals("")) {
            int noLot1 = Integer.parseInt(hakmilik.getNoLot());
            noLot1 += noLot;
            hm1.setNoLot(String.valueOf(noLot1));
        } else {
            hm1.setNoLot(hakmilik.getNoLot());
        }        
        hm1.setNoPelan(hakmilik.getNoPelan());
        hm1.setNoLitho(hakmilik.getNoLitho());
        hm1.setCukai(hakmilik.getCukai());
        hm1.setPegangan(hakmilik.getPegangan());
        hm1.setTempohPegangan(hakmilik.getTempohPegangan());
        if (kodSyarat != null) {
          KodSyaratNyata ksn = kodSyaratNyataDAO.findById(kodSyarat);
          if (ksn != null) {
            hm1.setSyaratNyata(ksn);
          }
        }
        if (kodSekatan != null) {
          KodSekatanKepentingan ksk = kodSekatanKepentinganDAO.findById(kodSekatan);
          if (ksk != null) {
            hm1.setSekatanKepentingan(ksk);
          }
        }
        if (hakmilik.getTarikhLuput() != null) {
          hm1.setTarikhLuput(hakmilik.getTarikhLuput());
        } else {
          hm1.setTarikhLuput(null);
        }
        if (hakmilik.getTarikhDaftar() != null) {
          hm1.setTarikhDaftar(hakmilik.getTarikhDaftar());
        }
        hm1.setNoPu(hakmilik.getNoPu());
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());
        hm1.setInfoAudit(ia);

        lhm.add(hm1);
        noLot++;
      }
    }
    if (!lhm.isEmpty()) {
      regService.simpanHakmilikList(lhm);
      addSimpleMessage("Maklumat hakmilik berjaya dikemaskini.");
    }

    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution paparPopupHakmilikAsal() {
    getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
    LOG.info("hakmilik asal : " + idHakmilik);
    hakmilik = hakmilikDAO.findById(idHakmilik);
    trhDaftarAsal = hakmilik.getTarikhDaftarAsal();
    setFlag(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution paparPopupHakmilikSebelum() {
    getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
    LOG.info("hakmilik sebelum : " + idHakmilik);
    setFlag(Boolean.FALSE);
    return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution simpanHakmilikAsal() throws ParseException {
    Hakmilik hm = hakmilikDAO.findById(idHakmilik);
    if (hm != null) {
      if (StringUtils.isBlank(kodJenisHakmilik) || StringUtils.isBlank(noHakmilik)) {
        setFlag(Boolean.TRUE);
        addSimpleError("Sila pastikan maklumat jenis hakmilik dan no hakmilik telah dimasukkn.");
        return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
      }
      String hmAsal = kodNegeri + hm.getDaerah().getKod() + hm.getBandarPekanMukim().getbandarPekanMukim() + kodJenisHakmilik + noHakmilik;
      LOG.info(">>> id hakmilik asal : " + hmAsal);
      Hakmilik hakmilikAsal = hakmilikDAO.findById(hmAsal);
      InfoAudit ia = new InfoAudit();
      if (hakmilikAsal == null) {
        hakmilikAsal = new Hakmilik();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        hakmilikAsal.setIdHakmilik(hmAsal);
        hakmilikAsal.setCawangan(pguna.getKodCawangan());
        hakmilikAsal.setDaerah(hm.getDaerah());
        hakmilikAsal.setBandarPekanMukim(hm.getBandarPekanMukim());
        KodHakmilik khm = new KodHakmilik();
        khm.setKod(kodJenisHakmilik);
        hakmilikAsal.setKodHakmilik(khm);
        hakmilikAsal.setNoHakmilik(noHakmilik);
        KodStatusHakmilik statusHakmilik = new KodStatusHakmilik();
        statusHakmilik.setKod("B");
        hakmilikAsal.setKodStatusHakmilik(statusHakmilik);
        hakmilikAsal.setLotBumiputera('T');
        hakmilikAsal.setMilikPersekutuan('T');
        hakmilikAsal.setLuas(BigDecimal.ZERO);
        hakmilikAsal.setNoVersiDhde(0);
        hakmilikAsal.setNoVersiDhke(0);
//        hakmilikSebelum.setKodSumber("DE");
        KodUOM kodUOM = new KodUOM();
        kodUOM.setKod("M");
        hakmilikAsal.setKodUnitLuas(kodUOM);
        hakmilikAsal.setInfoAudit(ia);
        listHakmilik.add(hakmilikAsal);
        regService.simpanHakmilikList(listHakmilik);
      }

      HakmilikAsal hmA = kutipanDataService.findHakmilikAsalByIdAsal(idHakmilik, hmAsal);
      List<HakmilikAsal> lha = new ArrayList<HakmilikAsal>();
      if (hmA == null) {
        ia.setTarikhMasuk(hm.getTarikhDaftar());
        ia.setDimasukOleh(pguna);
        hmA = new HakmilikAsal();
        hmA.setHakmilik(hm);
        hmA.setHakmilikAsal(sejarahHakmilikDAO.findById(hmAsal).getIdHakmilik());
        hmA.setInfoAudit(ia);
        lha.add(hmA);
        regService.simpanHakmilikAsal(lha);
      }

      if (trhDaftarAsal != null) {
        ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
        hm.setTarikhDaftarAsal(trhDaftarAsal);
        lhm.add(hm);
        if (!lhm.isEmpty()) {
          regService.simpanHakmilikList(lhm);
        }
      }
    }
    setRefresh(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution simpanHakmilikSebelum() throws ParseException {
    Hakmilik hm = hakmilikDAO.findById(idHakmilik);
    if (hm != null) {
      if (StringUtils.isBlank(kodJenisHakmilik) || StringUtils.isBlank(noHakmilik)) {
        setFlag(Boolean.FALSE);
        addSimpleError("Sila pastikan maklumat jenis hakmilik dan no hakmilik telah dimasukkn.");
        return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
      }
      String hmSblm = kodNegeri + hm.getDaerah().getKod() + hm.getBandarPekanMukim().getbandarPekanMukim() + kodJenisHakmilik + noHakmilik;
      LOG.info(">>> id hakmilik sebelum : " + hmSblm);
      Hakmilik hakmilikSebelum = hakmilikDAO.findById(hmSblm);
      InfoAudit ia = new InfoAudit();
      if (hakmilikSebelum == null) {
        hakmilikSebelum = new Hakmilik();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        hakmilikSebelum.setIdHakmilik(hmSblm);
        hakmilikSebelum.setCawangan(pguna.getKodCawangan());
        hakmilikSebelum.setDaerah(hm.getDaerah());
        hakmilikSebelum.setBandarPekanMukim(hm.getBandarPekanMukim());
        KodHakmilik khm = new KodHakmilik();
        khm.setKod(kodJenisHakmilik);
        hakmilikSebelum.setKodHakmilik(khm);
        hakmilikSebelum.setNoHakmilik(noHakmilik);
        KodStatusHakmilik statusHakmilik = new KodStatusHakmilik();
        statusHakmilik.setKod("B");
        hakmilikSebelum.setKodStatusHakmilik(statusHakmilik);
        hakmilikSebelum.setLotBumiputera('T');
        hakmilikSebelum.setMilikPersekutuan('T');
        hakmilikSebelum.setLuas(BigDecimal.ZERO);
        hakmilikSebelum.setNoVersiDhde(0);
        hakmilikSebelum.setNoVersiDhke(0);
//        hakmilikSebelum.setKodSumber("DE");
        KodUOM kodUOM = new KodUOM();
        kodUOM.setKod("M");
        hakmilikSebelum.setKodUnitLuas(kodUOM);
        hakmilikSebelum.setInfoAudit(ia);
        listHakmilik.add(hakmilikSebelum);
        regService.simpanHakmilikList(listHakmilik);
      }

      HakmilikSebelum hms = kutipanDataService.findHakmilikSebelumByIdSblm(idHakmilik, hmSblm);
      List<HakmilikSebelum> lhs = new ArrayList<HakmilikSebelum>();
      if (hms == null) {
        ia.setTarikhMasuk(hm.getTarikhDaftar());
        ia.setDimasukOleh(pguna);
        hms = new HakmilikSebelum();
        hms.setHakmilik(hm);
        hms.setHakmilikSebelum(hmSblm);
        hms.setInfoAudit(ia);
        lhs.add(hms);
        regService.simpanHakmilikSebelum(lhs);
      }
    }
    setRefresh(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_hm_asalSebelum.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution simpanMakluamtAsas() {
//    int counter = 0;
    ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
    for (Hakmilik senaraiHakmilik : listSenaraiHakmilik) {
      LOG.info("--> id_hakmilik di update : " + senaraiHakmilik.getIdHakmilik());
      Hakmilik hm = hakmilikDAO.findById(senaraiHakmilik.getIdHakmilik());
      if (senaraiHakmilik.getTarikhLuput() != null) {
        hm.setTarikhLuput(senaraiHakmilik.getTarikhLuput());
      }
      if (senaraiHakmilik.getTarikhDaftar() != null) {
        hm.setTarikhDaftar(senaraiHakmilik.getTarikhDaftar());
      }
      hm.setPegangan(senaraiHakmilik.getPegangan());
      hm.setTempohPegangan(senaraiHakmilik.getTempohPegangan());
      hm.setNoPu(senaraiHakmilik.getNoPu());
      lhm.add(hm);
    }
    if (!lhm.isEmpty()) {
      regService.simpanHakmilikList(lhm);
      addSimpleMessage("Maklumat hakmilik berjaya di kemaskini.");
    }
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    senaraiHakmilikUrusan(kumpHm);
    selectedTab = "maklumat_asas";
    return new JSP(KUTIPAN_TAB).addParameter("hakmilik", hakmilik);
  }

  public Resolution hapusHakmilik() {
    String[] id = getContext().getRequest().getParameterValues("idHapus");

    for (String string : id) {
      String idHapus = string;
      Hakmilik hmHapus = hakmilikDAO.findById(idHapus); // find data from table Hakmilik  
      if (hmHapus != null) {
        LOG.info("--> Hakmilik untuk dihapus : " + hmHapus.getIdHakmilik());
        kumpHm = hmHapus.getKumpulan();

        List<HakmilikPihakBerkepentingan> hpbHapus = hakmilikPihakKepentinganService.findAllPihakBerkepentinganByIdHakmilik(hmHapus.getIdHakmilik()); // find data from table hakmilik_pihak        
        if (!hpbHapus.isEmpty()) {
          /* DELETE HAKMILIK_PIHAK */
          hakmilikPihakKepentinganService.deleteHakmilikPihakBerkepentingan(hpbHapus);
        }

        List<HakmilikUrusan> huHapus = kutipanDataService.findListUrusanByHakmilik(hmHapus.getIdHakmilik());
        if (!huHapus.isEmpty()) {
          for (HakmilikUrusan hu : huHapus) {
            PermohonanRujukanLuar mrlHapus = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
            if (mrlHapus != null) {
              /* DELETE MOHON_RUJ_LUAR */
              kutipanDataService.delete(mrlHapus);
            }

            HakmilikPermohonan mhm = kutipanDataService.findMohonHakmilik(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
            if (mhm != null) {
              /* DELETE MOHON_HAKMILIK */
              hakmilikPermohonanDAO.delete(mhm);
            }

            List<PermohonanPihak> listmphk = permohonanPihakService.findPermohonanPihakByIdPermohonan(hu.getIdPerserahan());
            for (PermohonanPihak mphk : listmphk) {
              /* DELETE MOHON_PIHAK */
              permohonanPihakService.delete(mphk);
            }

            listPemohon = pemohonService.findPemohonByIdPermohonan(hu.getIdPerserahan());
            if (!listPemohon.isEmpty()) {
              /* DELETE PEMOHON */
              pemohonService.delete(listPemohon);
            }

            List<PermohonanHubungan> lmohonHbgn = permohonanService.getSenaraiHubungan(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
            if (!lmohonHbgn.isEmpty()) {
              for (PermohonanHubungan mhbgn : lmohonHbgn) {
                /* DELETE MOHON_HBGN */
                kutipanDataService.deleteMohonHbgn(mhbgn);
              }
            }
          }
          /* DELETE HAKMILIK URUSAN */
          huService.deleteHakmilikUrusan(huHapus);
        }
        /* DELETE HAKMILIK */
        regService.deleteHakmilik(hmHapus);
      }
    }

    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    if (listSenaraiHakmilik.isEmpty()) {
      addSimpleMessage("Maklumat hakmilik berjaya dihapus.");
      return new JSP(KUTIPAN_MAIN);
    }

    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    senaraiHakmilikUrusan(kumpHm);
    addSimpleMessage("Maklumat hakmilik berjaya dihapus.");
//    return new JSP(KUTIPAN_TAB).addParameter("kumpHm", kumpHm);
    idHakmilik = null;
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("kumpHm", kumpHm);
  }

  public Resolution tambahUrusan() {
    jenisUrusan = getContext().getRequest().getParameter("jenisUrusan");
    kumpHm = Integer.parseInt(getContext().getRequest().getParameter("kumpHm"));
    String idUrusan = getContext().getRequest().getParameter("id");
    LOG.info("----> jenisUrusan : " + jenisUrusan);
    LOG.info("----> idUrusan : " + idUrusan);
    if (StringUtils.isNotBlank(idUrusan)) {
      // IF ID URUSAN IS NOT NULL THEN ASSUME USER TRY TO UPDATE URUSAN
      hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));
      if (hakmilikUrusan != null) {
        mohonUrusan = kutipanDataService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
        mohonRujLuar = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

        /**
         * NOTES: FOR URUSAN THAT HAS 'URUSAN TERLIBAT', ADD HERE and
         * UpdateUrusan() to save mohon_hbgn
         */
        if (hakmilikUrusan.getKodUrusan().getKod().equals("GDPJ")
                || hakmilikUrusan.getKodUrusan().getKod().equals("GDPJK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("PJKT")
                || hakmilikUrusan.getKodUrusan().getKod().equals("PJKBT")
                || hakmilikUrusan.getKodUrusan().getKod().equals("KVAK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("KVSK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("KVPK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("PLK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("KVLK")
                || hakmilikUrusan.getKodUrusan().getKod().equals("KVSPC")) {
          listHakmilikUrusanSebelum = kutipanDataService.findListUrusanSebelum(hakmilikUrusan.getKodUrusan().getKod(), hakmilikUrusan.getHakmilik().getIdHakmilik());
          listMohonHbgn = permohonanService.getSenaraiHubungan(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        }
      }
    }

    if (jenisUrusan.equalsIgnoreCase("B")) {
      listkodUrusanB = kutipanDataService.findListkodUrusanByKodSerah("B");
    } else if (jenisUrusan.equalsIgnoreCase("SC")) {
      listkodUrusanSC = kutipanDataService.findListkodUrusanByKodSerah("SC");
    } else if (jenisUrusan.equalsIgnoreCase("N")) {
      listkodUrusanN = kutipanDataService.findListkodUrusanNota();
    } else if (jenisUrusan.equalsIgnoreCase("NB")) {
      listkodUrusanNB = kutipanDataService.findListkodUrusanByKodSerah("NB");
    } else {
      listkodUrusan = kodUrusanDAO.findAll();
    }
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    idHakmilik = listSenaraiHakmilik.get(0).getIdHakmilik();
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    return new JSP(POPUP_URUSAN).addParameter("jenisUrusan", jenisUrusan).addParameter("popup", "true");
  }

  public Resolution simpanUrusan() throws ParseException {
    LOG.info("/* SIMPAN URUSAN : START */");
    jenisUrusan = getContext().getRequest().getParameter("jenisUrusan");
    String noPerserahan = getContext().getRequest().getParameter("noPerserahan");
    String tahunPerserahan = getContext().getRequest().getParameter("tahunPerserahan");
    String radio = getContext().getRequest().getParameter("radio");
    String noJilid = getContext().getRequest().getParameter("noJilid");
    String noFolio = getContext().getRequest().getParameter("noFolio");
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    String[] id = getContext().getRequest().getParameterValues("idHM");
    KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
    InfoAudit ia = new InfoAudit();

    if (ku == null) {
      addSimpleError("Harap Maaf. Urusan Tidak Dijumpai.");
      return new JSP(POPUP_URUSAN).addParameter("popup", "true");
    }

    // CONVERT TARIKH DAFTAR to dd/mm/yy hh:mm:ss am
    if (StringUtils.isNotBlank(tarikhDaftar)) {
      /*if (StringUtils.isBlank(jam)) {
        addSimpleError("Sila Masukkan Jam");
        return new JSP(POPUP_URUSAN).addParameter("popup", "true");
      }
      if (StringUtils.isBlank(minit)) {
        addSimpleError("Sila Masukkan Minit");
        return new JSP(POPUP_URUSAN).addParameter("popup", "true");
      }*/
        saat = "00";
        if(StringUtils.isBlank(jam)&&StringUtils.isBlank(minit)){
             String trhconvert = tarikhDaftar;
             trhDaftar = dateFormat.parse(trhconvert);
        }
        else{
            String trhconvert = tarikhDaftar + " " + jam + ":" + minit + ":" + saat + " " + ampm;
            trhDaftar = dateTimeFormat.parse(trhconvert);
        }
    }

    // CHECK If id perserahan is available or not
    if ("y".equals(radio)) {
      if (StringUtils.isNotBlank(noPerserahan) && StringUtils.isNotBlank(tahunPerserahan)) {
        noPerserahan = String.format("%06d", Integer.parseInt(noPerserahan));
        // MANUAL GENERATE ID PERSERAHAN
        String idPerserahan = kodNegeri + pguna.getKodCawangan().getKod() + ku.getKodPerserahan().getKod() + tahunPerserahan + noPerserahan;
        mohon = kutipanDataService.findIdMohon(idPerserahan, kodUrusan); //FIND ID_URUSAN

        if (mohon == null) {
          if (jenisUrusan.equalsIgnoreCase("B")) {
            listkodUrusanB = kutipanDataService.findListkodUrusanByKodSerah("B");
          } else if (jenisUrusan.equalsIgnoreCase("SC")) {
            listkodUrusanSC = kutipanDataService.findListkodUrusanByKodSerah("SC");
          } else if (jenisUrusan.equalsIgnoreCase("N")) {
            listkodUrusanN = kutipanDataService.findListkodUrusanNota();
          } else if (jenisUrusan.equalsIgnoreCase("NB")) {
            listkodUrusanNB = kutipanDataService.findListkodUrusanByKodSerah("NB");
          } else {
            listkodUrusan = kodUrusanDAO.findAll();
          }

          mohon = permohonanService.findById(idPerserahan);
          if (mohon != null) {
            List<String> msg = new ArrayList<String>();
            List<HakmilikUrusan> lhu = kutipanDataService.findListUrusanByIdMohon(mohon.getIdPermohonan());
            addSimpleError("Harap Maaf. Id perserahan " + mohon.getIdPermohonan() + " adalah untuk urusan " + mohon.getKodUrusan().getNama() + ".");
            for (HakmilikUrusan hu : lhu) {
              msg.add(hu.getHakmilik().getIdHakmilik());
            }
            addSimpleError("Untuk hakmilik " + msg.toString());
            hakmilikUrusan = null;
            return new JSP(POPUP_URUSAN).addParameter("popup", "true");
          } else {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("-");
            fd.setNoFolio(noFolio);
            fd.setNoJilid(noJilid);
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(trhDaftar);
            fd.setInfoAudit(ia);
            folderDokumenDAO.save(fd);

            // GENERATE ID MOHON
            mohon = new Permohonan();
            mohon.setIdPermohonan(idPerserahan);
            mohon.setCawangan(pguna.getKodCawangan());
            mohon.setKodUrusan(ku);
            mohon.setStatus("SL");
            mohon.setKeputusan(kodKeputusanDAO.findById("D"));
            mohon.setKeputusanOleh(pguna);
            mohon.setFolderDokumen(fd);
            if (trhDaftar != null) {
              mohon.setTarikhKeputusan(trhDaftar);
            } else {
              mohon.setTarikhKeputusan(new java.util.Date()); // KIV for tarikh keputusan
            }
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(trhDaftar);
            mohon.setInfoAudit(ia);
            permohonanService.saveOrUpdate(mohon);
          }
//          return new JSP(POPUP_URUSAN).addParameter("popup", "true");
        }

        /* ADD FOLDER_DOK */
        if (mohon.getFolderDokumen() != null) {
          FolderDokumen fd = folderDokumenDAO.findById(mohon.getFolderDokumen().getFolderId());
          fd.setNoFolio(noFolio);
          fd.setNoJilid(noJilid);
          ia.setDiKemaskiniOleh(pguna);
          ia.setTarikhKemaskini(new java.util.Date());
          fd.setInfoAudit(ia);
          folderDokumenDAO.save(fd);
        }

        /* ADD MOHON_FASA
         * need to create MOHON_FASA or error in vdoc
         * */
        String stage = "kemasukkan";
        FasaPermohonan f = fasaPermohonanService.getCurrentStage(mohon.getIdPermohonan(), stage);
        if (f == null) {
          f = new FasaPermohonan();
          f.setCawangan(mohon.getCawangan());
          f.setPermohonan(mohon);
          f.setIdAliran("kemasukan"); // add fasa kemasukkan
          f.setIdPengguna(pguna.getIdPengguna());
          f.setTarikhKeputusan(trhDaftar);
          f.setTarikhKeputusan(trhDaftar);
          f.setTarikhHantar(trhDaftar);
          f.setKeputusan(kodKeputusanDAO.findById("SD"));
          f.setInfoAudit(ia);
          fasaPermohonanService.saveOrUpdate(f);
        }
      } else {
        hakmilikUrusan = null;
        if (jenisUrusan.equalsIgnoreCase("B")) {
          listkodUrusanB = kutipanDataService.findListkodUrusanByKodSerah("B");
        } else if (jenisUrusan.equalsIgnoreCase("SC")) {
          listkodUrusanSC = kutipanDataService.findListkodUrusanByKodSerah("SC");
        } else if (jenisUrusan.equalsIgnoreCase("N")) {
          listkodUrusanN = kutipanDataService.findListkodUrusanNota();
        } else if (jenisUrusan.equalsIgnoreCase("NB")) {
          listkodUrusanNB = kutipanDataService.findListkodUrusanByKodSerah("NB");
        } else {
          listkodUrusan = kodUrusanDAO.findAll();
        }
        addSimpleError("Harap Maaf. Sila Masukkan Id Perserahan.");
        return new JSP(POPUP_URUSAN).addParameter("popup", "true");
      }
    } else {
      // GENERATE ID MOHON
      janaPermohonan(ku, noJilid, noFolio);
    }

    List<HakmilikUrusan> lhuBaru = new ArrayList();
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());

    for (String string : id) {
      String idHm = string;
      hakmilik = hakmilikDAO.findById(idHm);
      LOG.info(" > id hakmilik yang dipilih : " + hakmilik.getIdHakmilik());

      /* ADD NEW HAKMILIK_URUSAN */
      HakmilikUrusan hu = huService.findByIdPerserahanIdHakmilik(mohon.getIdPermohonan(), hakmilik.getIdHakmilik());
      if (hu == null) {
        hu = new HakmilikUrusan();
        hu.setHakmilik(hakmilik);
        hu.setIdPerserahan(mohon.getIdPermohonan());
        hu.setDaerah(hakmilik.getDaerah());
        hu.setCawangan(hakmilik.getCawangan());
        hu.setKodUrusan(ku);
      }
      if (trhDaftar != null) {
        hu.setTarikhDaftar(trhDaftar);
      }
      hu.setNoFail(hakmilikUrusan.getNoFail());
      if (mohon.getFolderDokumen() != null) {
        hu.setFolderDokumen(mohon.getFolderDokumen());
      }
      hu.setTempohTahun(0); // seta as default
      hu.setTempohBulan(0); // seta as default
      hu.setTempohHari(0);  // set as default
      hu.setAktif('Y');
      hu.setInfoAudit(ia);
      lhuBaru.add(hu);

      /* ADD NEW MOHON_RUJ_LUAR */
      mohonRujLuar = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(mohon.getIdPermohonan(), hakmilik.getIdHakmilik());
      if (mohonRujLuar == null) {
        mohonRujLuar = new PermohonanRujukanLuar();
        mohonRujLuar.setHakmilik(hakmilik);
        mohonRujLuar.setPermohonan(mohon);
      }
      mohonRujLuar.setNoFail(hakmilikUrusan.getNoFail());
      mohonRujLuar.setCawangan(hakmilik.getCawangan());
      mohonRujLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
      mohonRujLuar.setInfoAudit(ia);
      kutipanDataService.saveMohonRujLuar(mohonRujLuar);

      /* ADD MOHON_HAKMILIK 
       * Need to create table MOHON_HAKMILIK or DHKE and DHDE 
       * won't show MOHON_RUJ_LUAR informations
       */
      HakmilikPermohonan mhm = kutipanDataService.findMohonHakmilik(hakmilik.getIdHakmilik(), hu.getIdPerserahan());
      if (mhm == null) {
        mhm = new HakmilikPermohonan();
        mhm.setHakmilik(hakmilik);
        mhm.setPermohonan(kutipanDataService.findIdMohon(mohon.getIdPermohonan(), kodUrusan));
        mhm.setInfoAudit(ia);
        hakmilikPermohonanDAO.save(mhm);
      }
    }

    if (!lhuBaru.isEmpty()) {
      huService.saveOrUpdateUrusan(lhuBaru);
    }

    mohonUrusan = kutipanDataService.findMohonUrusan(mohon.getIdPermohonan());
    if (mohonUrusan == null) {
      /* ADD NEW MOHON_URUSAN */
      mohonUrusan = new PermohonanUrusan();
      mohonUrusan.setPermohonan(mohon);
      mohonUrusan.setCawangan(mohon.getCawangan());
      mohonUrusan.setInfoAudit(ia);
      kutipanDataService.savePermohonanUrusan(mohonUrusan);
    }

    setRefresh(Boolean.TRUE);
    return new JSP(POPUP_URUSAN).addParameter("popup", "true");
  }

  public Resolution updateUrusan() {
    String tempohTahun = getContext().getRequest().getParameter("mohonRujLuar.tempohTahun");
    String tempohBulan = getContext().getRequest().getParameter("mohonRujLuar.tempohBulan");
    String tarikhSaksi = getContext().getRequest().getParameter("mohonUrusan.tarikhSaksi");
    String tarikhKuatkuasa = getContext().getRequest().getParameter("mohonRujLuar.tarikhKuatkuasa");
    String tarikhTamat = getContext().getRequest().getParameter("mohonRujLuar.tarikhTamat");
    String tarikhSampai = getContext().getRequest().getParameter("mohonRujLuar.tarikhDisampai");

    InfoAudit ia = new InfoAudit();
    ia.setTarikhKemaskini(new java.util.Date());
    ia.setDiKemaskiniOleh(pguna);
    List<HakmilikUrusan> lhuUpdate = new ArrayList();

    List<HakmilikUrusan> listHu = kutipanDataService.findListUrusanByIdMohonAndKumpHm(hakmilikUrusan.getIdPerserahan(), kumpHm);
    for (HakmilikUrusan hu : listHu) {
      /* UPDATE HAKMILIK_URUSAN */
        
      HakmilikUrusan hu2 = huService.findById(Long.parseLong(getContext().getRequest().getParameter("idhu")));
      hu2.setAktif(aktif.charAt(0));
      
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikUrusan.noFail"))) {
        hu.setNoFail(hakmilikUrusan.getNoFail());
      }
      if (StringUtils.isNotBlank(tempohTahun)) {
        hu.setTempohTahun(Integer.parseInt(tempohTahun));
      } else {
        hu.setTempohTahun(0);
      }
      if (StringUtils.isNotBlank(tempohBulan)) {
        hu.setTempohBulan(Integer.parseInt(tempohBulan));
      } else {
        hu.setTempohBulan(0);
      }
      hu.setTempohHari(0);
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikUrusan.luasTerlibat"))) {
        BigDecimal luas = new BigDecimal(getContext().getRequest().getParameter("hakmilikUrusan.luasTerlibat"));
        hu.setLuasTerlibat(luas);
      } else {
        hu.setLuasTerlibat(null);
      }
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikUrusan.kodUnitLuas.kod"))) {
        KodUOM kodUOM = new KodUOM();
        kodUOM.setKod(getContext().getRequest().getParameter("hakmilikUrusan.kodUnitLuas.kod"));
        hu.setKodUnitLuas(kodUOM);
      } else {
        hu.setKodUnitLuas(null);
      }
      
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikUrusan.cukaiBaru"))) {
        BigDecimal cukaiBaru = new BigDecimal(getContext().getRequest().getParameter("hakmilikUrusan.cukaiBaru"));
        hu.setCukaiBaru(cukaiBaru);
      } 
      
      
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.item"))) {
        hu.setItem(mohonRujLuar.getItem());
      }
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.noRujukan"))) {
        hu.setNoRujukan(mohonRujLuar.getNoRujukan());
      }
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhRujukan"))) {
        hu.setTarikhRujukan(mohonRujLuar.getTarikhRujukan());
      }
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhTamat"))) {
        hu.setTarikhTamat(mohonRujLuar.getTarikhTamat());
      }
      hu.setInfoAudit(ia);
      lhuUpdate.add(hu);

      PermohonanRujukanLuar mrl = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
      if (mrl != null) {
        /* UPDATE MOHON_RUJ_LUAR */
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikUrusan.noFail"))) {
          mrl.setNoFail(hakmilikUrusan.getNoFail());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.kodPerintah.kod"))) {
          mrl.setKodPerintah(kodPerintahDAO.findById(getContext().getRequest().getParameter("mohonRujLuar.kodPerintah.kod")));
        }
//        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.noRujukan"))) {
        mrl.setNoRujukan(getContext().getRequest().getParameter("mohonRujLuar.noRujukan"));
//        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.noSidang"))) {
          mrl.setNoSidang(mohonRujLuar.getNoSidang());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.namaSidang"))) {
          mrl.setNamaSidang(mohonRujLuar.getNamaSidang());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.ulasan"))) {
          mrl.setUlasan(mohonRujLuar.getUlasan());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.item"))) {
          mrl.setItem(mohonRujLuar.getItem());
        }
        if (StringUtils.isNotBlank(tarikhSampai)) {
          mrl.setTarikhDisampai(mohonRujLuar.getTarikhDisampai());
        } else {
          mrl.setTarikhDisampai(null);
        }
        if (StringUtils.isNotBlank(tempohTahun)) {
          mrl.setTempohTahun(Integer.parseInt(tempohTahun));
        } else {
          mrl.setTempohTahun(0);
        }
        if (StringUtils.isNotBlank(tempohBulan)) {
          mrl.setTempohBulan(Integer.parseInt(tempohBulan));
        } else {
          mrl.setTempohBulan(0);
        }
        mrl.setTempohHari(0);
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhSidang"))) {
          mrl.setTarikhSidang(mohonRujLuar.getTarikhSidang());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhKuatkuasa"))) {
          mrl.setTarikhKuatkuasa(mohonRujLuar.getTarikhKuatkuasa());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhTamat"))) {
          mrl.setTarikhTamat(mohonRujLuar.getTarikhTamat());
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonRujLuar.tarikhRujukan"))) {
          mrl.setTarikhRujukan(mohonRujLuar.getTarikhRujukan());
        }
        mrl.setInfoAudit(ia);
        kutipanDataService.saveMohonRujLuar(mrl);
      }

      HakmilikPermohonan mhm = kutipanDataService.findMohonHakmilik(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
      if (mhm != null) {
        mhm.setLuasTerlibat(hu.getLuasTerlibat());
        mhm.setCukaiBaru(hu.getCukaiBaru());
        if (hu.getKodUnitLuas() != null) {
          mhm.setKodUnitLuas(hu.getKodUnitLuas());
        }
        hakmilikPermohonanDAO.save(mhm);
      }
    }
    huService.saveOrUpdateUrusan(lhuUpdate);

    HakmilikUrusan hu = huService.findById(Long.parseLong(getContext().getRequest().getParameter("idhu")));
    /**
     * USE THIS TO ADD MOHON_HBGN FOR URUSAN TERLIBAT
     */
    if (hu.getKodUrusan().getKod().equals("GDPJ")
            || hu.getKodUrusan().getKod().equals("GDPJK")
            || hu.getKodUrusan().getKod().equals("PJKT")
            || hu.getKodUrusan().getKod().equals("PJKBT")
            || hu.getKodUrusan().getKod().equals("KVAK")
            || hu.getKodUrusan().getKod().equals("KVSK")
            || hu.getKodUrusan().getKod().equals("KVPK")
            || hu.getKodUrusan().getKod().equals("PLK")
            || hu.getKodUrusan().getKod().equals("KVSPC")
            || hu.getKodUrusan().getKod().equals("KVLK")) {
      /* ADD MOHON_HBGN */
      String[] idhuSblm = getContext().getRequest().getParameterValues("iu");
      for (String string : idhuSblm) {
        String id = string;
        HakmilikUrusan huSblm = huService.findById(Long.parseLong(id));
        List<PermohonanHubungan> lmhbgn = new ArrayList();

        listMohonHbgn = permohonanService.getSenaraiHubungan(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        if (listMohonHbgn.isEmpty()) {
          PermohonanHubungan mohonHbgn = new PermohonanHubungan();
          mohonHbgn.setCawangan(hu.getCawangan());
          mohonHbgn.setPermohonanSumber(permohonanService.findById(hu.getIdPerserahan()));
          mohonHbgn.setPermohonanSasar(permohonanService.findById(huSblm.getIdPerserahan()));
          mohonHbgn.setHakmilik(hu.getHakmilik());
          mohonHbgn.setCatatan(hu.getHakmilik().getIdHakmilik());
          mohonHbgn.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("B"));
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(hu.getTarikhDaftar());
          mohonHbgn.setInfoAudit(ia);
          lmhbgn.add(mohonHbgn);
        } else {
          for (PermohonanHubungan mohonHbgn : listMohonHbgn) {
            if (!mohonHbgn.getPermohonanSasaran().equals(huSblm.getIdPerserahan())) {
              mohonHbgn = new PermohonanHubungan();
              mohonHbgn.setCawangan(hu.getCawangan());
              mohonHbgn.setPermohonanSumber(permohonanService.findById(hu.getIdPerserahan()));
              mohonHbgn.setPermohonanSasar(permohonanService.findById(huSblm.getIdPerserahan()));
              mohonHbgn.setHakmilik(hu.getHakmilik());
              mohonHbgn.setCatatan(hu.getHakmilik().getIdHakmilik());
              mohonHbgn.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("B"));
              ia.setDimasukOleh(pguna);
              ia.setTarikhMasuk(hu.getTarikhDaftar());
              mohonHbgn.setInfoAudit(ia);
              lmhbgn.add(mohonHbgn);
            }
          }
        }
        permohonanService.savePermohonanHubungan(lmhbgn);
      }
    }

    PermohonanUrusan pu = kutipanDataService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
    if (pu != null) {
      /* UPDATE MOHON_URUSAN */
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonUrusan.perjanjianAmaun"))) {
        BigDecimal amaun = new BigDecimal(getContext().getRequest().getParameter("mohonUrusan.perjanjianAmaun"));
        pu.setPerjanjianAmaun(amaun);
      }
      if (StringUtils.isNotBlank(getContext().getRequest().getParameter("mohonUrusan.perjanjianNoRujukan"))) {
        BigDecimal setem = new BigDecimal(getContext().getRequest().getParameter("mohonUrusan.perjanjianNoRujukan"));
        pu.setPerjanjianDutiSetem(setem);
      }
      pu.setPerjanjianNoRujukan(getContext().getRequest().getParameter("mohonUrusan.perjanjianNoRujukan"));
      if (StringUtils.isNotBlank(tarikhSaksi)) {
        pu.setTarikhSaksi(mohonUrusan.getTarikhSaksi());
      }
      if (StringUtils.isNotBlank(tempohTahun)) {
        pu.setPerjanjianTempohTahun(Integer.parseInt(tempohTahun));
      } else {
        pu.setPerjanjianTempohTahun(0);
      }
      if (StringUtils.isNotBlank(tempohBulan)) {
        pu.setPerjanjianTempohBulan(Integer.parseInt(tempohBulan));
      } else {
        pu.setPerjanjianTempohBulan(0);
      }
      pu.setPerjanjianTempohHari(0);
      if (StringUtils.isNotBlank(tarikhKuatkuasa)) {
        pu.setPerjanjianTarikhMula(mohonRujLuar.getTarikhKuatkuasa());
      }
      if (StringUtils.isNotBlank(tarikhTamat)) {
        pu.setPerjanjianTarikhTamat(mohonRujLuar.getTarikhTamat());
      }
      pu.setInfoAudit(ia);
      kutipanDataService.savePermohonanUrusan(pu);
    }

    setRefresh(Boolean.TRUE);
    addSimpleMessage("Maklumat Berjaya Dikemaskini.");
    return new JSP(POPUP_URUSAN).addParameter("selectedTab", selectedTab);
  }

  public Resolution hapusMohonHbgn() throws ParseException {
    String idHapus = getContext().getRequest().getParameter("idP");
    String idUrusan = getContext().getRequest().getParameter("idhu");
    LOG.info("----> idHapus : " + idHapus);
    hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));
    PermohonanHubungan mohonHbgn = permohonanHubunganDAO.findById(Long.parseLong(idHapus));
    if (mohonHbgn != null) {
      /* DELETE MOHON_HBGN */
      kutipanDataService.deleteMohonHbgn(mohonHbgn);
    }
    setFlag(Boolean.TRUE);
    return new JSP(POPUP_URUSAN).addParameter("selectedTab", selectedTab);
  }

  public Resolution kemaskiniPihakBerkepentingan() {
//    jenisUrusan = getContext().getRequest().getParameter("jenisUrusan");
    kumpHm = Integer.parseInt(getContext().getRequest().getParameter("kumpHm"));
    String idUrusan = getContext().getRequest().getParameter("id");
//    LOG.info("----> jenisUrusan : " + jenisUrusan);
//    LOG.info("----> kumpHm : " + kumpHm);
//    LOG.info("----> idUrusan : " + idUrusan);
    if (StringUtils.isNotBlank(idUrusan)) {
      hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));

      if (hakmilikUrusan != null) {
        listMohonPihak = kutipanDataService.findListMohonPihak(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        if (!hakmilikUrusan.getKodUrusan().getKod().equalsIgnoreCase("PLS")) {
          senaraiKodPihak = kutipanDataService.findListPenerima(hakmilikUrusan.getKodUrusan().getNama()); // find kod pihak peneriam
        }
        listHakmilikPihak = kutipanDataService.findListPemilik2(hakmilikUrusan.getHakmilik().getIdHakmilik());
        listPemohon = kutipanDataService.findListPemohonByIdHakmilikandIdMohon(hakmilikUrusan.getHakmilik().getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
      }
    }
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("jenisUrusan", jenisUrusan).addParameter("popup", "true");
  }

  public Resolution cariPemohon() throws ParseException {
    LOG.info("/* CARI Pemegang */");
    String cariNama = (String) getContext().getRequest().getParameter("pihakNama");
    String cariKodPengenalan = (String) getContext().getRequest().getParameter("pihakKodPengenalan");
    String cariNoIC = (String) getContext().getRequest().getParameter("pihakNoIC");
    idPihak = (String) getContext().getRequest().getParameter("idPihak");
    jenisUrusan = getContext().getRequest().getParameter("jenisUrusan");
    kumpHm = Integer.parseInt(getContext().getRequest().getParameter("kumpHm"));
    String idUrusan = getContext().getRequest().getParameter("id");
    LOG.info("cariNama" + cariNama);
    LOG.info("cariKodPengenalan" + cariKodPengenalan);
    LOG.info("cariNoIC" + cariNoIC);
    LOG.info("idPihak" + idPihak);
    LOG.info("jenisUrusan" + jenisUrusan);
    LOG.info("kumpHm" + kumpHm);
    LOG.info("idUrusan" + idUrusan);
    permohonanPihak = null;
    pihak = null;

    if (StringUtils.isNotBlank(idUrusan)) {
      hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));
      if (hakmilikUrusan != null) {
//        mohonUrusan = kutipanDataService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
//        mohonRujLuar = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        senaraiKodPihak = kutipanDataService.findListPenerima(hakmilikUrusan.getKodUrusan().getNama()); // find kod pihak peneriam
        listMohonPihak = kutipanDataService.findListMohonPihak(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        listHakmilikPihak = kutipanDataService.findListPemilik2(hakmilikUrusan.getHakmilik().getIdHakmilik());
        listPemohon = kutipanDataService.findListPemohonByIdHakmilikandIdMohon(hakmilikUrusan.getHakmilik().getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
      }
    }

    if (StringUtils.isNotBlank(idPihak)) {
      pihak = pihakService.findById(idPihak);
      if (hakmilikUrusan != null) {
          Hakmilik hm = hakmilikUrusan.getHakmilik();
          if (hm != null) {
              List<HakmilikPihakBerkepentingan> senaraiPemilik = hm.getSenaraiPihakBerkepentingan();
              if (!senaraiPemilik.isEmpty()) {
                  for (HakmilikPihakBerkepentingan hp : senaraiPemilik) {
                      if (hp == null || hp.getJenis() == null 
                              || !("PM").equals(hp.getJenis().getKod()) 
                              || pihak.getIdPihak() != hp.getPihak().getIdPihak()) continue;
                      
                      pihak.setAlamat1(hp.getAlamat1());
                      pihak.setAlamat2(hp.getAlamat2());
                      pihak.setAlamat3(hp.getAlamat3());
                      pihak.setAlamat4(hp.getAlamat4());
                      pihak.setPoskod(hp.getPoskod());
                      pihak.setNegeri(hp.getNegeri());
                      pihak.setSuratAlamat1(hp.getAlamatSurat().getAlamatSurat1());
                      pihak.setSuratAlamat2(hp.getAlamatSurat().getAlamatSurat2());
                      pihak.setSuratAlamat3(hp.getAlamatSurat().getAlamatSurat3());
                      pihak.setSuratAlamat4(hp.getAlamatSurat().getPoskodSurat());
                      pihak.setSuratPoskod(hp.getAlamatSurat().getPoskodSurat());
                      pihak.setSuratNegeri(hp.getAlamatSurat().getNegeriSurat());
                      break;
                  }
              }
          }
      }
//      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    } else {
      if (StringUtils.isNotBlank(cariKodPengenalan) && StringUtils.isNotBlank(cariNoIC)) {
        listPihak = pihakService.findPihakBynoKPkodKP(cariKodPengenalan, cariNoIC);
        if (listPihak.isEmpty()) {
          addSimpleError("Maaf. Tiada rekod dijumpai.");
        }
      } else if (StringUtils.isNotBlank(cariNama)) {
        listPihak = pihakService.findPihakByNama(cariNama);
        if (listPihak.isEmpty()) {
          addSimpleError("Maaf. Tiada rekod dijumpai.");
        }
      } else if (StringUtils.isNotBlank(cariKodPengenalan) && StringUtils.isBlank(cariNoIC)) {
        addSimpleError("Sila masukkan maklumat no pengenalan.");
      } else if (StringUtils.isBlank(cariKodPengenalan) && StringUtils.isNotBlank(cariNoIC)) {
        addSimpleError("Sila pilih jenis pengenalan.");
      } else {
        addSimpleError("Sila masukkan maklumat carian anda.");
      }
    }
    setPemegang(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("popup", "true").addParameter("hakmilikUrusan", hakmilikUrusan).addParameter("mohonRujLuar", mohonRujLuar);
  }

  public Resolution kemaskiniPemohon() {
    String idMP = (String) getContext().getRequest().getParameter("mp");
    String idUrusan = (String) getContext().getRequest().getParameter("id");
    permohonanPihak = permohonanPihakService.findById(idMP);
    pihak = null;

    if (permohonanPihak != null) {
      if (StringUtils.isNotBlank(idUrusan)) {
        hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));
        if (hakmilikUrusan != null) {
//          mohonUrusan = kutipanDataService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
//          mohonRujLuar = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
          senaraiKodPihak = kutipanDataService.findListPenerima(hakmilikUrusan.getKodUrusan().getNama()); // find kod pihak peneriam
          listMohonPihak = kutipanDataService.findListMohonPihak(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
          listHakmilikPihak = kutipanDataService.findListPemilik2(hakmilikUrusan.getHakmilik().getIdHakmilik());
          listPemohon = kutipanDataService.findListPemohonByIdHakmilikandIdMohon(hakmilikUrusan.getHakmilik().getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
        }
      }
    }
    setPemegang(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("popup", "true");
  }

  public Resolution simpanPemberi() throws ParseException {
//    kemaskiniPihakBerkepentingan
    String[] id = getContext().getRequest().getParameterValues("idP");
    String idMohon = getContext().getRequest().getParameter("idPerserahan");
    hakmilikUrusan = huService.findById(Long.parseLong(getContext().getRequest().getParameter("id")));
    for (String string : id) {
      String idPemilik = string;
      HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.findById(idPemilik);
      if (hp != null) {
        Pemohon p = kutipanDataService.findPemohonByIdMohonIdHakmilikandIdPihak(idMohon, hp.getHakmilik().getIdHakmilik(), hp.getPihak().getIdPihak());
        if (p == null) {
          mohon = permohonanService.findById(idMohon);
          InfoAudit ia = new InfoAudit();
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          p = new Pemohon();
          p.setCawangan(hp.getCawangan());
          p.setPermohonan(mohon);
          p.setHakmilik(hp.getHakmilik());
          p.setNama(hp.getNama());
          p.setNoPengenalan(hp.getNoPengenalan());
          p.setJenisPengenalan(hp.getJenisPengenalan());
          p.setJenis(hp.getJenis());
          Alamat alamat = new Alamat();
          alamat.setAlamat1(hp.getAlamat1());
          alamat.setAlamat2(hp.getAlamat2());
          alamat.setAlamat3(hp.getAlamat3());
          alamat.setAlamat4(hp.getAlamat4());
          alamat.setPoskod(hp.getPoskod());
          alamat.setNegeri(hp.getNegeri());
          p.setAlamat(alamat);
          p.setAlamatSurat(hp.getAlamatSurat());
          p.setInfoAudit(ia);
          pemohonService.saveOrUpdate(p);
        }
      }
    }
    setRefresh(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("selectedTab", selectedTab);
  }

  public Resolution hapusPemberi() throws ParseException {
    String idHapus = getContext().getRequest().getParameter("idP");
    LOG.info("----> idHapus : " + idHapus);
    hakmilikUrusan = huService.findById(Long.parseLong(getContext().getRequest().getParameter("id")));
    Pemohon p = kutipanDataService.findPemohonByIdPemohon(idHapus);
    if (p != null) {
      /* DELETE PEMOHON */
      pemohonService.delete(p);
    }
    setRefresh(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("selectedTab", selectedTab);
  }

  public Resolution simpanPemohon() throws ParseException {
    LOG.info("/* UPDATE PEMOHON : START */");
    idHakmilik = getContext().getRequest().getParameter("idhm");
    idPihak = getContext().getRequest().getParameter("idhm");
    String idMohon = getContext().getRequest().getParameter("idPerserahan");
    String jenisPihak = getContext().getRequest().getParameter("jenisPihak");

    hakmilikUrusan = huService.findByIdPerserahanIdHakmilik(idMohon, idHakmilik);
    listMohonPihak = kutipanDataService.findListMohonPihak(idMohon, idHakmilik);
    if (hakmilikUrusan != null) {
      senaraiKodPihak = kutipanDataService.findListPenerima(hakmilikUrusan.getKodUrusan().getNama()); // find kod pihak penerima

//      if (hakmilikUrusan.getKodUrusan().getKod().equals("PJT")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("PJBT")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")
//              || hakmilikUrusan.getKodUrusan().getKod().equals("GD")) {
        /* SIMPAN MAKLUMAT KEPADA */
      idPihak = getContext().getRequest().getParameter("pihak.idPihak");
      String idMP = (String) getContext().getRequest().getParameter("idPermohonanPihak");
      InfoAudit ia = new InfoAudit();
      PermohonanPihak mp;
      if (StringUtils.isNotBlank(idMP)) {
        mp = permohonanPihakService.findById(idMP);
      } else {
        mp = null;
      }
      if (mp != null) {
        String j = getContext().getRequest().getParameter("jenisPihakKemaskini");
        /* UPDATE MOHON_PIHAK */
        ia.setTarikhKemaskini(trhDaftar);
        ia.setDiKemaskiniOleh(pguna);
        mp.setJenis(kodJenisPihakBerkepentinganDAO.findById(j));
        mp.setNama(permohonanPihak.getNama());
        mp.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
        mp.setNoPengenalan(permohonanPihak.getNoPengenalan());
        mp.setWargaNegara(permohonanPihak.getWargaNegara());
        mp.setSyerPembilang(0);
        mp.setSyerPenyebut(0);
        Alamat add = new Alamat();
        add.setAlamat1(getContext().getRequest().getParameter("permohonanPihak.alamat.alamat1"));
        add.setAlamat2(getContext().getRequest().getParameter("permohonanPihak.alamat.alamat2"));
        add.setAlamat3(getContext().getRequest().getParameter("permohonanPihak.alamat.alamat3"));
        add.setAlamat4(getContext().getRequest().getParameter("permohonanPihak.alamat.alamat4"));
        add.setPoskod(getContext().getRequest().getParameter("permohonanPihak.alamat.poskod"));
        add.setNegeri(kodNegeriDAO.findById(getContext().getRequest().getParameter("permohonanPihak.alamat.negeri.kod")));
        mp.setAlamat(add);
        AlamatSurat addSurat = new AlamatSurat();
        addSurat.setAlamatSurat1(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.alamatSurat1"));
        addSurat.setAlamatSurat2(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.alamatSurat2"));
        addSurat.setAlamatSurat3(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.alamatSurat3"));
        addSurat.setAlamatSurat4(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.alamatSurat4"));
        addSurat.setPoskodSurat(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.poskodSurat"));
        addSurat.setNegeriSurat(kodNegeriDAO.findById(getContext().getRequest().getParameter("permohonanPihak.alamatSurat.negeriSurat.kod")));
        mp.setAlamatSurat(addSurat);
        mp.setInfoAudit(ia);
        permohonanPihakService.saveOrUpdate(mp);
        pihak = null;
      } else {
        if (StringUtils.isBlank(jenisPihak)) {
          addSimpleError("Sila Masukkan Jenis Pihak.");
          return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("selectedTab", selectedTab);
        }
        mohon = kutipanDataService.findIdMohon(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getKodUrusan().getKod());
        List<Pihak> listphk = new ArrayList<Pihak>();
        
        Pihak phk = null;
        if (StringUtils.isNotBlank(idPihak)) {
            phk = pihakDAO.findById(Long.parseLong(idPihak));
        }

        /* PIHAK */
        if (phk == null) {
          phk = new Pihak();
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          KodFlagPihak kodFlagPihak = new KodFlagPihak();
          kodFlagPihak.setKod("AK");
          phk.setKodFlagPihak(kodFlagPihak);  // SET PIHAK AS ACTIVE
          phk.setSuratAlamat1(getContext().getRequest().getParameter("pihak.suratAlamat1"));
          phk.setSuratAlamat2(getContext().getRequest().getParameter("pihak.suratAlamat2"));
          phk.setSuratAlamat3(getContext().getRequest().getParameter("pihak.suratAlamat3"));
          phk.setSuratAlamat4(getContext().getRequest().getParameter("pihak.suratAlamat4"));
          phk.setSuratPoskod(getContext().getRequest().getParameter("pihak.suratPoskod"));
          phk.setSuratNegeri(kodNegeriDAO.findById(getContext().getRequest().getParameter("pihak.suratNegeri.kod")));
        } else {
          ia.setDiKemaskiniOleh(pguna);
          ia.setTarikhKemaskini(new java.util.Date());
        }

        if (pihak.getNegeriKelahiran() != null) {
          phk.setNegeriKelahiran(pihak.getNegeriKelahiran());
        } else {
          phk.setNegeriKelahiran(null);
        }
        phk.setNama(pihak.getNama());
        phk.setTarikhLahir(pihak.getTarikhLahir());
//          phk.setBangsa(kodBangsaDAO.findById(pihak.getBangsa().getKod()));
        //          phk.setKodJantina(pihak.getKodJantina());
        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
        phk.setNoPengenalan(pihak.getNoPengenalan());
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("pihak.wargaNegara.kod"))) {
          phk.setWargaNegara(kodWarganegaraDAO.findById(getContext().getRequest().getParameter("pihak.wargaNegara.kod")));
        }
        phk.setAlamat1(getContext().getRequest().getParameter("pihak.alamat1"));
        phk.setAlamat2(getContext().getRequest().getParameter("pihak.alamat2"));
        phk.setAlamat3(getContext().getRequest().getParameter("pihak.alamat3"));
        phk.setAlamat4(getContext().getRequest().getParameter("pihak.alamat4"));
        phk.setPoskod(getContext().getRequest().getParameter("pihak.poskod"));
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("pihak.negeri.kod"))) {
          phk.setNegeri(kodNegeriDAO.findById(getContext().getRequest().getParameter("pihak.negeri.kod")));
        }
        //new format from etanah : cannot update surat alamat pihak !! 15/2/2013
        phk.setInfoAudit(ia);
        listphk.add(phk);
        if (!listphk.isEmpty()) {
          pihakService.saveOrUpdate(listphk);
        }

        // Add new mohon_pihak          
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pguna);
        mp = new PermohonanPihak();
        mp.setPermohonan(mohon);
        mp.setNama(pihak.getNama());
        mp.setHakmilik(hakmilikUrusan.getHakmilik());
        mp.setSyerPembilang(0);
        mp.setSyerPenyebut(0);
        mp.setJenis(kodJenisPihakBerkepentinganDAO.findById(jenisPihak));
        mp.setCawangan(mohon.getCawangan());
        mp.setPihak(phk);
        mp.setJenisPengenalan(phk.getJenisPengenalan());
        mp.setNoPengenalan(phk.getNoPengenalan());
        Alamat add = new Alamat();
        add.setAlamat1(getContext().getRequest().getParameter("pihak.alamat1"));
        add.setAlamat2(getContext().getRequest().getParameter("pihak.alamat2"));
        add.setAlamat3(getContext().getRequest().getParameter("pihak.alamat3"));
        add.setAlamat4(getContext().getRequest().getParameter("pihak.alamat4"));
        add.setPoskod(getContext().getRequest().getParameter("pihak.poskod"));
        add.setNegeri(kodNegeriDAO.findById(getContext().getRequest().getParameter("pihak.negeri.kod")));
        mp.setAlamat(add);
        AlamatSurat addSurat = new AlamatSurat();
        addSurat.setAlamatSurat1(getContext().getRequest().getParameter("pihak.suratAlamat1"));
        addSurat.setAlamatSurat2(getContext().getRequest().getParameter("pihak.suratAlamat2"));
        addSurat.setAlamatSurat3(getContext().getRequest().getParameter("pihak.suratAlamat3"));
        addSurat.setAlamatSurat4(getContext().getRequest().getParameter("pihak.suratAlamat4"));
        addSurat.setPoskodSurat(getContext().getRequest().getParameter("pihak.suratPoskod"));
        addSurat.setNegeriSurat(kodNegeriDAO.findById(getContext().getRequest().getParameter("pihak.suratNegeri.kod")));
        mp.setAlamatSurat(addSurat);

        mp.setInfoAudit(ia);
        permohonanPihakService.saveOrUpdate(mp);
      }

      //update hakmilik_pihak          
      HakmilikPihakBerkepentingan listhpb 
              = kutipanDataService.findPemilikbyHakmilikAndPihak(hakmilikUrusan.getHakmilik().getIdHakmilik(),
                      String.valueOf(mp.getPihak().getIdPihak()), mp.getJenis().getKod());
      if (listhpb != null) {
        /* UPDATE HAKMILIK_PIHAK */
        listhpb.setJenis(mp.getJenis());
//          listhpb.setPerserahan(hu);
//          listhpb.setKaveatAktif('T');
        listhpb.setAktif('Y');
        listhpb.setSyerPembilang(mp.getSyerPembilang());
        listhpb.setSyerPenyebut(mp.getSyerPenyebut());
//          listhpb.setPihak(mp.getPihak());
        listhpb.setJenisPengenalan(mp.getJenisPengenalan());
        listhpb.setNoPengenalan(mp.getNoPengenalan());
        listhpb.setWargaNegara(mp.getWargaNegara());
        listhpb.setNama(mp.getNama());
        listhpb.setAlamat1(mp.getAlamat().getAlamat1());
        listhpb.setAlamat2(mp.getAlamat().getAlamat2());
        listhpb.setAlamat3(mp.getAlamat().getAlamat3());
        listhpb.setAlamat4(mp.getAlamat().getAlamat4());
        listhpb.setPoskod(mp.getAlamat().getPoskod());
        listhpb.setNegeri(mp.getAlamat().getNegeri());
        listhpb.setAlamatSurat(mp.getAlamatSurat());
        hakmilikPihakKepentinganService.save(listhpb);
      } else {
        /* ADD HAKMILIK_PIHAK */
        listhpb = new HakmilikPihakBerkepentingan();
        listhpb.setCawangan(mp.getCawangan());
        listhpb.setHakmilik(mp.getHakmilik());
        listhpb.setJenis(mp.getJenis());
        listhpb.setPerserahan(hakmilikUrusan);
        listhpb.setKaveatAktif('T');
        listhpb.setAktif('Y');   // SET HAKMILIK_PIHAK as ACTIVE
        listhpb.setSyerPembilang(mp.getSyerPembilang());
        listhpb.setSyerPenyebut(mp.getSyerPenyebut());
        listhpb.setPihak(mp.getPihak());
        listhpb.setJenisPengenalan(mp.getJenisPengenalan());
        listhpb.setNoPengenalan(mp.getNoPengenalan());
        listhpb.setWargaNegara(mp.getWargaNegara());
        listhpb.setNama(mp.getNama());
        listhpb.setAlamat1(mp.getAlamat().getAlamat1());
        listhpb.setAlamat2(mp.getAlamat().getAlamat2());
        listhpb.setAlamat3(mp.getAlamat().getAlamat3());
        listhpb.setAlamat4(mp.getAlamat().getAlamat4());
        listhpb.setPoskod(mp.getAlamat().getPoskod());
        listhpb.setNegeri(mp.getAlamat().getNegeri());
        listhpb.setAlamatSurat(mp.getAlamatSurat());
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        listhpb.setInfoAudit(ia);
        hakmilikPihakKepentinganService.save(listhpb);
      }
//      }
      setRefresh(Boolean.TRUE);
      addSimpleMessage("Maklumat Urusan Berjaya Dikemaskini.");
    } else {
      addSimpleError("Kemaskini Tidak Berjaya!");
    }
//    selectedTab = "maklumat_urusan";
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("selectedTab", selectedTab);
  }

  public Resolution hapusPemohon() throws ParseException {
    String idHapus = getContext().getRequest().getParameter("idP");
    String idUrusan = getContext().getRequest().getParameter("id");
    LOG.info("----> idHapus : " + idHapus);
    hakmilikUrusan = huService.findById(Long.parseLong(getContext().getRequest().getParameter("id")));

    List<HakmilikPihakBerkepentingan> lhpbHapus = new ArrayList<HakmilikPihakBerkepentingan>();
    PermohonanPihak mphk = permohonanPihakService.findById(idHapus);
    if (mphk != null) {
      List<HakmilikPihakBerkepentingan> lhpb = kutipanDataService.findListHakmilikPihakByIdUrusanBatal(mphk.getHakmilik().getIdHakmilik(), Long.parseLong(idUrusan));
      for (HakmilikPihakBerkepentingan hp : lhpb) {
        if (hp.getPihak().equals(mphk.getPihak()) && hp.getJenis().equals(mphk.getJenis())) {
          lhpbHapus.add(hp);
        }
      }
      /* DELETE HAKMILIK_PIHAK */
      hakmilikPihakKepentinganService.deleteHakmilikPihakBerkepentingan(lhpbHapus);

      /* DELETE MOHON_PIHAK */
      permohonanPihakService.delete(mphk);
    }
    setRefresh(Boolean.TRUE);
    return new JSP("daftar/utiliti/kutipan_data_pihakBerkepentingan.jsp").addParameter("selectedTab", selectedTab);
  }

  public Resolution hapusUrusan() {
    String idHapus = getContext().getRequest().getParameter("id");
    LOG.info("----> idHapus : " + idHapus);
    HakmilikUrusan hu = huService.findById(Long.parseLong(idHapus));

    if (hu != null) {
      Transaction tx = sessionProvider.get().beginTransaction();
      tx.begin();
      try {
        LOG.info(hu.getIdPerserahan());
        LOG.info(hu.getHakmilik().getIdHakmilik());
        PermohonanRujukanLuar mrl = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        if (mrl != null) {
          /* DELETE MOHON_RUJ_LUAR */
          kutipanDataService.delete(mrl);
        }

        HakmilikPermohonan mhm = kutipanDataService.findMohonHakmilik(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        if (mhm != null) {
          /* DELETE MOHON_HAKMILIK */
          hakmilikPermohonanDAO.delete(mhm);
        }

        List<HakmilikPihakBerkepentingan> listhpb = new ArrayList<HakmilikPihakBerkepentingan>();
        List<HakmilikPihakBerkepentingan> listHpb = kutipanDataService.findListHakmilikPihakByIdUrusan(hu.getHakmilik().getIdHakmilik(), hu.getIdUrusan());
        List<HakmilikPihakBerkepentingan> listHpbBatal = kutipanDataService.findListHakmilikPihakByIdUrusanBatal(hu.getHakmilik().getIdHakmilik(), hu.getIdUrusan());
        if (!listHpb.isEmpty()) {
          for (HakmilikPihakBerkepentingan hpb : listHpb) {
            listhpb.add(hpb);
          }
        }
        if (!listHpbBatal.isEmpty()) {
          for (HakmilikPihakBerkepentingan hpbBatal : listHpbBatal) {
            listhpb.add(hpbBatal);
          }
        }
        if (!listhpb.isEmpty()) {
          /* DELETE HAKMILIK_PIHAK */
          hakmilikPihakKepentinganService.deleteHakmilikPihakBerkepentingan(listhpb);
        }

        List<PermohonanPihak> listmphk = permohonanPihakService.findPermohonanPihakByIdPermohonan(hu.getIdPerserahan());
        for (PermohonanPihak mphk : listmphk) {
          /* DELETE MOHON_PIHAK */
          permohonanPihakService.delete(mphk);
        }

        listPemohon = pemohonService.findPemohonByIdPermohonan(hu.getIdPerserahan());
        if (!listPemohon.isEmpty()) {
          /* DELETE PEMOHON */
          pemohonService.delete(listPemohon);
        }

        List<PermohonanHubungan> lmohonHbgn = permohonanService.getSenaraiHubungan(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        if (!lmohonHbgn.isEmpty()) {
          for (PermohonanHubungan mhbgn : lmohonHbgn) {
            /* DELETE MOHON_HBGN */
            permohonanHubunganDAO.delete(mhbgn);
          }
        }

        /* DELETE HAKMILIK_URUSAN */
        huService.deleteHakmilikUrusan(hu);
        tx.commit();
      } catch (Exception e) {
        tx.rollback();
        Throwable t = e;
        // getting the root cause
        while (t.getCause() != null) {
          t = t.getCause();
        }
        t.printStackTrace();
        addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
        return null;
      }
    }
    // --------------------------------------------------------------
    // NOTE : currently sys won't delete id mohon in table mohon even 
    // if there's no data in table Hakmilik_urusan and mohon_hakmilik
    // --------------------------------------------------------------

    senaraiHakmilikUrusan(kumpHm);
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    selectedTab = "maklumat_urusan"; // to make sure still in current tab
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("kumpHm", kumpHm).addParameter("selectedTab", selectedTab);
  }

  private void senaraiHakmilikUrusan(int kumpHm) {
    /* GET SENARAI URUSAN */
    LOG.info("/ SENARAI HAKMILIK URUSAN /");
    listHakmilikUrusanSC = kutipanDataService.findListUrusanByKodSerahAndKump(kumpHm, "SC");
    listHakmilikUrusanN = kutipanDataService.findListUrusanByKodSerahAndKump(kumpHm, "N");
    listHakmilikUrusanB = kutipanDataService.findListUrusanByKodSerahAndKump(kumpHm, "B");
    listHakmilikUrusanNB = kutipanDataService.findListUrusanByKodSerahAndKump(kumpHm, "NB");
  }

  public Resolution doCheckNoHakmilik() {
    // CHECK NO HAKMLIK    
    String idH1;
    String idH2;
    String idHakmilikBaru;

    if (!idHakmilikSiriDari.isEmpty() && !idHakmilikSiriHingga.isEmpty()) {
      LOG.info("/* Check No Hakmilik dari " + idHakmilikSiriDari + " hingga " + idHakmilikSiriHingga + " */");
      idH1 = idHakmilikSiriDari.get(0);
      idH2 = idHakmilikSiriHingga.get(0);

    } else if (!noHakmilikSiriDari.isEmpty() && !noHakmilikSiriHingga.isEmpty()) {
      LOG.info("/* Check No Hakmilik dari " + noHakmilikSiriDari + " hingga " + noHakmilikSiriHingga + " */");
      if (bpm == null || kodJenisHakmilik == null) {
        addSimpleError("Sila masukkan maklumat Bandar / Pekan / Mukin dan Jenis Hakmilik.");
        return new JSP(KUTIPAN_MAIN);
      }

      KodHakmilik kodhm = kodHakmilikDAO.findById(kodJenisHakmilik);
      if (kodhm == null) { // CHECK JENIS HAKMILIk      
        addSimpleError("Jenis hakmilik " + kodJenisHakmilik + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      KodBandarPekanMukim kodbpm = kutipanDataService.findKodBPM(bpm, kodDaerah);
      if (kodbpm == null) { // CHECK BANDAR PEKAN MUKIM      
        addSimpleError("Bandar / Pekan / Mukim " + bpm + " tidak wujud");
        return new JSP(KUTIPAN_MAIN);
      }

      idH1 = noHakmilikSiriDari.get(0);
      idH2 = noHakmilikSiriHingga.get(0);

    } else {
      // If input not complete
      addSimpleError("Maaf maklumat tidak lengkap. Sila masukkan maklumat yang berkenaan.");
      return new JSP(KUTIPAN_MAIN);
    }

    ArrayList<String> list = getNoHakmilikBerkelompok(idH1, idH2);
    Hakmilik hm = new Hakmilik();
    for (String string : list) {

      if (!idHakmilikSiriDari.isEmpty() && !idHakmilikSiriHingga.isEmpty()) {
        idHakmilikBaru = string;
      } else {
        /* MANUAL GENERATE ID HAKMILIK */
        idHakmilikBaru = kodNegeri + kodDaerah + bpm + kodJenisHakmilik + string;
      }

      hm = hakmilikDAO.findById(idHakmilikBaru);
      if (hm != null) {
//        LOG.info("  >> " + hm.getKodStatusHakmilik().getKod());
//        LOG.info("  >> " + hm.getNoVersiDhde());
        if (!hm.getCawangan().getKod().equals(pguna.getKodCawangan().getKod())) {
          addSimpleError("Hakmilik " + hm.getIdHakmilik() + " adalah hakmilik cawangan " + hm.getCawangan().getName() + ".");
        } else if (hm.getNoVersiDhde() > 0) {
          // CHECK VERSION
          addSimpleError("Hakmilik " + hm.getIdHakmilik() + " - versi " + hm.getNoVersiDhde() + ".");
        } else if (hm.getKodStatusHakmilik().equals("B")) {
          // CHECK STATUS HAKMILIK
          addSimpleError("Hakmilik " + hm.getIdHakmilik() + " telah Batal.");
        } else {
          addSimpleMessage("Hakmilik " + hm.getIdHakmilik() + " boleh dibuat kutipan data.");
        }
      } else {
        // new hakmilik
        addSimpleMessage("Hakmilik " + idHakmilikBaru + " boleh dibuat kutipan data.");
      }
    }
    return new JSP(KUTIPAN_MAIN);
  }

  private void cariKodBPM(String kodDaerah) {
    /* Use this to auto get bandar pekan mukin. Must send kod daerah to this method */
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    if (kodDaerah == null || kodDaerah.equals("")) {
      sql = "select bpm from KodBandarPekanMukim bpm ";
      q = s.createQuery(sql);
    } else {
      sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
      q = s.createQuery(sql);
      q.setString("kod", kodDaerah);
    }
    listBandarPekanMukim = q.list();
  }

  public Resolution cariBPM() {
    /* Use this to get bandar pekan mukin when onchange daerah */
    kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
    bpm = (String) getContext().getRequest().getParameter("bpm");
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    if (kodDaerah == null || kodDaerah.equals("")) {
      sql = "select bpm from KodBandarPekanMukim bpm ";
      q = s.createQuery(sql);
    } else {
      sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
      q = s.createQuery(sql);
      q.setString("kod", kodDaerah);
    }
    listBandarPekanMukim = q.list();
    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/kutipan_data_partialBPM.jsp")
            .addParameter("popup", "true");
  }

  private void cariKodSeksyen(int kodBPM) {
    /* Use this to auto get bandar pekan mukin. Must send kod bpm to this method */
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    sql = "SELECT s FROM KodSeksyen s WHERE s.kodBandarPekanMukim.kod = :kod and s.aktif='Y'";
    q = s.createQuery(sql);
    q.setInteger("kod", kodBPM);
    listKodSeksyen = q.list();
  }

  public Resolution paparPopupSyaratNyata() {
    /* POPUP KOD SYARAT NYATA */
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kumpHm = Integer.parseInt(getContext().getRequest().getParameter("kumpHm"));
    cariKodSyaratNyata(kodSyarat);
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    return new JSP("daftar/utiliti/kutipan_data_syaratNyata.jsp")
            .addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
  }

  public void cariKodSyaratNyata(String kodSyarat) {
    /* Use to search kod syarat nyata */
    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    String caw = peng.getKodCawangan().getKod();
    if (kodSyarat != null) {
      lisSyaratNyata = regService.searchKodSyaratNyata(kodSyarat, caw, namaSyarat, kodKatTanah);
      if (lisSyaratNyata.size() < 1) {
        addSimpleError("Kod Syarat Nyata Tidak Sah");
      }
    } else {
      lisSyaratNyata = regService.searchKodSyaratNyata("%", caw, namaSekatan, kodKatTanah);
      if (lisSyaratNyata.size() < 1) {
        addSimpleError("Kod Syarat Nyata Tidak Sah");
      }
    }
  }

  public Resolution simpanKodSyaratNyata() {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kodSyarat = getContext().getRequest().getParameter("syaratNyata");
    ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();

    Hakmilik h = hakmilikDAO.findById(idHakmilik);
    if (h != null) {
      InfoAudit ia = new InfoAudit();
      ia.setDimasukOleh(pguna);
      ia.setTarikhMasuk(new java.util.Date());
      KodSyaratNyata k = kodSyaratNyataDAO.findById(kodSyarat);
      h.setSyaratNyata(k);
      h.setInfoAudit(ia);
      lhm.add(h);
      if (!lhm.isEmpty()) {
        // need to have this or the apps wont update damn!
        regService.simpanHakmilikList(lhm);
      }
      setRefresh(Boolean.TRUE);
    }
    return new JSP("daftar/utiliti/kutipan_data_syaratNyata.jsp");
  }

  public Resolution simpanKodSyaratNyataBerkelompok() {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kodSyarat = getContext().getRequest().getParameter("syaratNyata");
    if (kumpHm != 0) {
      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);

      ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
      for (Hakmilik hm : listSenaraiHakmilik) {
        Hakmilik h = hakmilikDAO.findById(hm.getIdHakmilik());
        if (h != null) {
          InfoAudit ia = new InfoAudit();
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          KodSyaratNyata k = kodSyaratNyataDAO.findById(kodSyarat);
          h.setSyaratNyata(k);
          h.setInfoAudit(ia);
          lhm.add(h);
        }
      }

      if (!lhm.isEmpty()) {
        regService.simpanHakmilikList(lhm);
      }

      setRefresh(Boolean.TRUE);
    }
    return new JSP("daftar/utiliti/kutipan_data_syaratNyata.jsp");
  }

  public Resolution paparPopupSekatan() {
    /* POPUP KOD SEKATAN KEPENTINGAN */
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kumpHm = Integer.parseInt(getContext().getRequest().getParameter("kumpHm"));
    cariKodSekatanKepentingan(kodSekatan);
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    return new JSP("daftar/utiliti/kutipan_data_sekatanKepentingan.jsp")
            .addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
  }

  public void cariKodSekatanKepentingan(String kodSekatan) {
    pguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    String caw = pguna.getKodCawangan().getKod();
    if (kodSekatan != null) {
      lisSekatan = regService.searchKodSekatan(kodSekatan, caw, namaSekatan);
      if (lisSekatan.size() < 1) {
        addSimpleError("Kod Sekatan Tidak Sah");
      }
    } else {
      lisSekatan = regService.searchKodSekatan("%", caw, namaSekatan);
      if (lisSekatan.size() < 1) {
        addSimpleError("Kod Sekatan Tidak Sah");
      }
    }
  }

  public Resolution simpanKodSekatanKepentingan() {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kodSekatan = getContext().getRequest().getParameter("sekatan");
    ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();

    Hakmilik h = hakmilikDAO.findById(idHakmilik);
    if (h != null) {
      InfoAudit ia = new InfoAudit();
      ia.setDimasukOleh(pguna);
      ia.setTarikhMasuk(new java.util.Date());
      KodSekatanKepentingan k = kodSekatanKepentinganDAO.findById(kodSekatan);
      h.setSekatanKepentingan(k);
      h.setInfoAudit(ia);
      lhm.add(h);
      if (!lhm.isEmpty()) {
        // need to have this or the apps wont update damn!
        regService.simpanHakmilikList(lhm);
      }
      setRefresh(Boolean.TRUE);
    }
    return new JSP("daftar/utiliti/kutipan_data_sekatanKepentingan.jsp");
  }

  public Resolution simpanKodSekatanBerkelompok() {
    idHakmilik = getContext().getRequest().getParameter("idHakmilik");
    kodSekatan = getContext().getRequest().getParameter("sekatan");
    LOG.info(">> kump : " + kumpHm);
    if (kumpHm != 0) {
      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);

      ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
      InfoAudit ia = new InfoAudit();

      for (Hakmilik hm : listSenaraiHakmilik) {
        Hakmilik h = hakmilikDAO.findById(hm.getIdHakmilik());
        if (h != null) {
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          KodSekatanKepentingan k = kodSekatanKepentinganDAO.findById(kodSekatan);
          h.setSekatanKepentingan(k);
          h.setInfoAudit(ia);
          lhm.add(h);
        }
      }

      if (!lhm.isEmpty()) {
        regService.simpanHakmilikList(lhm);
      }

      setRefresh(Boolean.TRUE);
    }
    return new JSP("daftar/utiliti/kutipan_data_sekatanKepentingan.jsp");
  }

  public Resolution paparPopupPihak() {
    if (kodNegeri.equals("05")) {
      senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
    } else {
      senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
    }
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
    getContext().getRequest().setAttribute("idHakmilik", idHakmilik);

    setPemegang(Boolean.FALSE);
    return new JSP(POPUP_PIHAK).addParameter("popup", "true")
            .addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution cariPihak() throws ParseException {
    LOG.info("/* CARI PIHAK */");
    String cariNama = (String) getContext().getRequest().getParameter("pihakNama");
    String cariKodPengenalan = (String) getContext().getRequest().getParameter("pihakKodPengenalan");
    String cariNoIC = (String) getContext().getRequest().getParameter("pihakNoIC");
    idPihak = (String) getContext().getRequest().getParameter("idPihak");

    if (kodNegeri.equals("05")) {
      senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
    } else {
      senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
    }

    if (StringUtils.isNotBlank(idPihak)) {
      pihak = pihakService.findById(idPihak);
      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    } else {
      if (StringUtils.isNotBlank(cariKodPengenalan) && StringUtils.isNotBlank(cariNoIC)) {
        listPihak = pihakService.findPihakBynoKPkodKP(cariKodPengenalan, cariNoIC);
        if (listPihak.isEmpty()) {
          addSimpleError("Maaf. Tiada rekod dijumpai.");
        }
      } else if (StringUtils.isNotBlank(cariNama)) {
        listPihak = pihakService.findPihakByNama(cariNama);
        if (listPihak.isEmpty()) {
          addSimpleError("Maaf. Tiada rekod dijumpai.");
        }
      } else if (StringUtils.isNotBlank(cariKodPengenalan) && StringUtils.isBlank(cariNoIC)) {
        addSimpleError("Sila masukkan maklumat no pengenalan.");
      } else if (StringUtils.isBlank(cariKodPengenalan) && StringUtils.isNotBlank(cariNoIC)) {
        addSimpleError("Sila pilih jenis pengenalan.");
      } else {
        addSimpleError("Sila masukkan maklumat carian anda.");
      }
    }
    return new JSP(POPUP_PIHAK).addParameter("popup", "true");
  }

  public Resolution kemaskiniPihak() {
    idHP = (String) getContext().getRequest().getParameter("hp");
    idHakmilik = (String) getContext().getRequest().getParameter("hm");
    idPihak = (String) getContext().getRequest().getParameter("phk");

    if (StringUtils.isNotBlank(idPihak) && StringUtils.isNotBlank(idHakmilik) && StringUtils.isNotBlank(idHP)) {
      pihak = pihakService.findById(idPihak);
      hakmilik = hakmilikDAO.findById(idHakmilik);
      hakmilikPihak = hpbDAO.findById(Long.parseLong(idHP));
      //hakmilikPihak = findListPemilikByHakmilikAndPihakAll(hakmilik, pihak);

      if (kodNegeri.equals("05")) {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
      } else {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
      }

      return new JSP(KEMASKINI_PIHAK).addParameter("popup", "true").addParameter("hm", idHakmilik);
    } else {
      addSimpleError("Maaf. Maklumat Pihak Tidak Dijumpai.");
      return new JSP(MAKLUMAT_HAKMILIK_DETAIL).addParameter("popup", "true");
    }
  }

  public Resolution updatePihak() {
    LOG.info("/*UPDATE PIHAK*/");
    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
    String idHP = (String) getContext().getRequest().getParameter("idHP");
    idPihak = (String) getContext().getRequest().getParameter("idPihak");
    String aktif = (String) getContext().getRequest().getParameter("aktif");   
    String penubuhanSyarikat = (String) getContext().getRequest().getParameter("hakmilikPihak.penubuhanSyarikat.kod");
//    LOG.info("------ id hakmilik : " + idHakmilik);
//    LOG.info("------ idHP : " + idHP);
//    LOG.info("------ idPihak : " + idPihak);

    if (StringUtils.isNotBlank(idHP)) {
      pihak = pihakService.findById(idPihak);
      if(hakmilikPihak.getPihak()!= null){
        pihak.setKodJantina(hakmilikPihak.getPihak().getKodJantina());     
        pihak.setBangsa(kodBangsaDAO.findById(hakmilikPihak.getPihak().getBangsa().getKod()));
        pihak.setNegeriKelahiran(hakmilikPihak.getPihak().getNegeriKelahiran());
        pihak.setTarikhLahir(hakmilikPihak.getPihak().getTarikhLahir());
      }

      HakmilikPihakBerkepentingan hpb = hpbDAO.findById(Long.parseLong(idHP));
      hpb.setNama(hakmilikPihak.getNama());
      LOG.info("/*AKTIF UPDATE :*/" + aktif);
      if(aktif != null){
          hpb.setAktif(aktif.charAt(0)); 
      }      
      if (hakmilikPihak.getJenis() != null)
        hpb.setJenis(kodJenisPihakBerkepentinganDAO.findById(hakmilikPihak.getJenis().getKod()));
      if (hakmilikPihak.getJenisPengenalan()!= null)
        hpb.setJenisPengenalan(kodJenisPengenalanDAO.findById(hakmilikPihak.getJenisPengenalan().getKod()));
        hpb.setNoPengenalan(hakmilikPihak.getNoPengenalan());
        hpb.setNoPengenalanLama(hakmilikPihak.getNoPengenalanLama());
        
      if (hakmilikPihak.getWargaNegara() != null)
        hpb.setWargaNegara(kodWarganegaraDAO.findById(hakmilikPihak.getWargaNegara().getKod()));      
      hpb.setAlamat1(hakmilikPihak.getAlamat1());
      hpb.setAlamat2(hakmilikPihak.getAlamat2());
      hpb.setAlamat3(hakmilikPihak.getAlamat3());
      hpb.setAlamat4(hakmilikPihak.getAlamat4());
      hpb.setPoskod(hakmilikPihak.getPoskod());
      if ( hakmilikPihak.getNegeri() != null)
        hpb.setNegeri(kodNegeriDAO.findById(hakmilikPihak.getNegeri().getKod()));
      
        if (hakmilikPihak.getAlamatSurat() != null) {
            AlamatSurat surat = new AlamatSurat();
            if (hakmilikPihak.getAlamatSurat().getAlamatSurat1() != null)
                surat.setAlamatSurat1(hakmilikPihak.getAlamatSurat().getAlamatSurat1());
            if (hakmilikPihak.getAlamatSurat().getAlamatSurat2() != null)
                surat.setAlamatSurat2(hakmilikPihak.getAlamatSurat().getAlamatSurat2());
            if (hakmilikPihak.getAlamatSurat().getAlamatSurat3() != null)
                surat.setAlamatSurat3(hakmilikPihak.getAlamatSurat().getAlamatSurat3());
            if (hakmilikPihak.getAlamatSurat().getAlamatSurat4() != null)
                surat.setAlamatSurat4(hakmilikPihak.getAlamatSurat().getAlamatSurat4());
            if (hakmilikPihak.getAlamatSurat().getPoskodSurat() != null)
                surat.setPoskodSurat(hakmilikPihak.getAlamatSurat().getPoskodSurat());
            if (hakmilikPihak.getAlamatSurat().getNegeriSurat() != null)
                surat.setNegeriSurat(kodNegeriDAO.findById(hakmilikPihak.getAlamatSurat().getNegeriSurat().getKod()));
            hpb.setAlamatSurat(surat);
        }
        
      if (penubuhanSyarikat != null) {
          hpb.setPenubuhanSyarikat(kodPenubuhanSyarikatDAO.findById(penubuhanSyarikat));
      }
      
      InfoAudit ia = new InfoAudit();
      ia.setDiKemaskiniOleh(pguna);
      ia.setTarikhKemaskini(new java.util.Date());
      hpb.setInfoAudit(ia);
//      hakmilikPihakKepentinganService.save(hpb);
      pihakService.saveOrUpdateHakmilikPihakKepentinganPihak(pihak, hpb); // update both pihak and hakmilik_pihak
    }
    setRefresh(Boolean.TRUE);
    return new JSP(KEMASKINI_PIHAK).addParameter("popup", "true");
  }

  public Resolution simpanPihak() {
    /**
     * USE TO ADD PIHAK in HAKMILIK_PIHAK
     */
    LOG.info("/*SIMPAN PIHAK*/");
    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
    idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
    String jenisPihak = (String) getContext().getRequest().getParameter("jenisPihak");
    String add1 = (String) getContext().getRequest().getParameter("pihak.alamat1");
    String add2 = (String) getContext().getRequest().getParameter("pihak.alamat2");
    String add3 = (String) getContext().getRequest().getParameter("pihak.alamat3");
    String add4 = (String) getContext().getRequest().getParameter("pihak.alamat4");
    String postcode = (String) getContext().getRequest().getParameter("pihak.poskod");
    String negeri = (String) getContext().getRequest().getParameter("pihak.negeri.kod");
    String suratAdd1 = (String) getContext().getRequest().getParameter("pihak.suratAlamat1");
    String suratAdd2 = (String) getContext().getRequest().getParameter("pihak.suratAlamat2");
    String suratAdd3 = (String) getContext().getRequest().getParameter("pihak.suratAlamat3");
    String suratAdd4 = (String) getContext().getRequest().getParameter("pihak.suratAlamat4");
    String suratPostcode = (String) getContext().getRequest().getParameter("pihak.suratPoskod");
    String suratNegeri = (String) getContext().getRequest().getParameter("pihak.suratNegeri.kod");

    if (StringUtils.isBlank(jenisPihak)) {
      addSimpleError("Sila pilih jenis pihak.");
      if (kodNegeri.equals("05")) {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
      } else {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
      }
      return new JSP(POPUP_PIHAK).addParameter("popup", "true");
    }
    if (pihak.getJenisPengenalan() == null || pihak.getNoPengenalan() == null || pihak.getNama() == null) {
      addSimpleError("Sila pastikan maklumat mandatori telah diisi.");
      if (kodNegeri.equals("05")) {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
      } else {
        senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
      }
      return new JSP(POPUP_PIHAK).addParameter("popup", "true");
    }

    List<HakmilikPihakBerkepentingan> listhpb = new ArrayList<HakmilikPihakBerkepentingan>();
    List<Pihak> listphk = new ArrayList<Pihak>();
    InfoAudit ia = new InfoAudit();
    Hakmilik hm = hakmilikDAO.findById(idHakmilik);

    Pihak phk = null;
    if (StringUtils.isNotBlank(idPihak)) {
      phk = pihakDAO.findById(Long.parseLong(idPihak));
    }
    LOG.info("phk : " + phk);
    if (phk == null) {
      phk = new Pihak();
      ia.setDimasukOleh(pguna);
      ia.setTarikhMasuk(new java.util.Date());
      KodFlagPihak kodFlagPihak = new KodFlagPihak();
      kodFlagPihak.setKod("AK");
      phk.setKodFlagPihak(kodFlagPihak);  // SET PIHAK AS ACTIVE
      //---------------- Alamat surat ------------
      phk.setSuratAlamat1(suratAdd1);
      phk.setSuratAlamat2(suratAdd2);
      phk.setSuratAlamat3(suratAdd3);
      phk.setSuratAlamat4(suratAdd4);
      phk.setSuratPoskod(suratPostcode);
      phk.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));
    } else {
      ia.setDiKemaskiniOleh(pguna);
      ia.setTarikhKemaskini(new java.util.Date());
    }
    phk.setNama(pihak.getNama());
    phk.setNoPengenalan(pihak.getNoPengenalan());
    if (pihak.getNegeriKelahiran() != null) {
      phk.setNegeriKelahiran(pihak.getNegeriKelahiran());
    } else {
      phk.setNegeriKelahiran(null);
    }
    phk.setTarikhLahir(pihak.getTarikhLahir());
    phk.setBangsa(kodBangsaDAO.findById(pihak.getBangsa().getKod()));
    phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
    phk.setKodJantina(pihak.getKodJantina());
    phk.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
    //------------------- Alamat ---------------
    phk.setAlamat1(add1);
    phk.setAlamat2(add2);
    phk.setAlamat3(add3);
    phk.setAlamat4(add4);
    phk.setPoskod(postcode);
    phk.setNegeri(kodNegeriDAO.findById(negeri));
    //new format from etanah : cannot update surat alamat pihak !! 15/2/2013
    phk.setInfoAudit(ia);
    listphk.add(phk);
    if (!listphk.isEmpty()) {
      pihakService.saveOrUpdate(listphk);
    }

    /* ADD NEW HAKMILIK_PIHAK */
    // to prevent from duplication data
    // todo : set jum_pembilang/jum_penyebut : could be multiple data with same pihak
    HakmilikPihakBerkepentingan hpb = kutipanDataService.findPemilikbyHakmilikAndPihak(idHakmilik, idPihak, jenisPihak);
    if (hpb == null) {
      hpb = new HakmilikPihakBerkepentingan();
      hpb.setCawangan(hm.getCawangan());
      hpb.setHakmilik(hm);
      hpb.setPihak(phk);
      hpb.setNama(pihak.getNama());
      hpb.setKaveatAktif('T');
//    hpb.setPihakKongsiBersama(null);
//    hpb.setPihakCawangan(null);
      hpb.setSyerPembilang(1);
      hpb.setSyerPenyebut(1);
      hpb.setJumlahPembilang(1);
      hpb.setJumlahPenyebut(1);
      hpb.setJenis(kodJenisPihakBerkepentinganDAO.findById(jenisPihak));
      hpb.setNama(pihak.getNama());
      hpb.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
      hpb.setNoPengenalan(pihak.getNoPengenalan());
      hpb.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
      //------------------- Alamat ---------------
      hpb.setAlamat1(add1);
      hpb.setAlamat2(add2);
      hpb.setAlamat3(add3);
      hpb.setAlamat4(add4);
      hpb.setPoskod(postcode);
      hpb.setNegeri(kodNegeriDAO.findById(negeri));
      //---------------- Alamat surat ------------
      AlamatSurat surat = new AlamatSurat();
      surat.setAlamatSurat1(suratAdd1);
      surat.setAlamatSurat2(suratAdd2);
      surat.setAlamatSurat3(suratAdd3);
      surat.setAlamatSurat4(suratAdd4);
      surat.setPoskodSurat(suratPostcode);
      surat.setNegeriSurat(kodNegeriDAO.findById(suratNegeri));
      hpb.setAlamatSurat(surat);
      //------------------ Alamat END ---------------
      hpb.setAktif('Y');   // SET HAKMILIK_PIHAK as ACTIVE

      ia.setDimasukOleh(pguna);
      ia.setTarikhMasuk(new java.util.Date());
      hpb.setInfoAudit(ia);
      hakmilikPihakKepentinganService.save(hpb);
    } else {
      hpb.setNama(pihak.getNama());
      hpb.setKaveatAktif('T');
      //hpb.setAktif(aktif.charAt(0));      
      hpb.setJenis(kodJenisPihakBerkepentinganDAO.findById(jenisPihak));
      hpb.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
      hpb.setNoPengenalan(pihak.getNoPengenalan());
      hpb.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
      //------------------- Alamat ---------------
      hpb.setAlamat1(add1);
      hpb.setAlamat2(add2);
      hpb.setAlamat3(add3);
      hpb.setAlamat4(add4);
      hpb.setPoskod(postcode);
      hpb.setNegeri(kodNegeriDAO.findById(negeri));
      //---------------- Alamat surat ------------
      AlamatSurat surat = new AlamatSurat();
      surat.setAlamatSurat1(suratAdd1);
      surat.setAlamatSurat2(suratAdd2);
      surat.setAlamatSurat3(suratAdd3);
      surat.setAlamatSurat4(suratAdd4);
      surat.setPoskodSurat(suratPostcode);
      surat.setNegeriSurat(kodNegeriDAO.findById(suratNegeri));
      hpb.setAlamatSurat(surat);
      //------------------ Alamat END ---------------
      hpb.setAktif('Y');
      ia.setDiKemaskiniOleh(pguna);
      ia.setTarikhKemaskini(new java.util.Date());
      hpb.setInfoAudit(ia);
      hakmilikPihakKepentinganService.save(hpb);
    }

    LOG.info("pemegang : " + pemegang);
    if (pemegang == true) {
      LOG.info(">>>>>>>>>> masuk");
      String idUrusan = getContext().getRequest().getParameter("id");
      if (StringUtils.isNotBlank(idUrusan)) {
        hakmilikUrusan = huService.findById(Long.parseLong(idUrusan));
        if (hakmilikUrusan != null) {
          mohonUrusan = kutipanDataService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
          mohonRujLuar = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        }
      }
      return new JSP(POPUP_URUSAN).addParameter("popup", "true");
    } else {
      setRefresh(Boolean.TRUE);
      return new JSP(POPUP_PIHAK).addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }
  }

  public Resolution simpanPihakBerkelompok() {
    /**
     * USE TO ADD PIHAK FOR ALL HAKMILIK
     */
    LOG.info("/*SIMPAN PIHAK BERKELOMPOK*/");

    if (kumpHm != 0) {
      idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
      String jenisPihak = (String) getContext().getRequest().getParameter("jenisPihak");
      String add1 = (String) getContext().getRequest().getParameter("pihak.alamat1");
      String add2 = (String) getContext().getRequest().getParameter("pihak.alamat2");
      String add3 = (String) getContext().getRequest().getParameter("pihak.alamat3");
      String add4 = (String) getContext().getRequest().getParameter("pihak.alamat4");
      String postcode = (String) getContext().getRequest().getParameter("pihak.poskod");
      String negeri = (String) getContext().getRequest().getParameter("pihak.negeri.kod");
      String suratAdd1 = (String) getContext().getRequest().getParameter("pihak.suratAlamat1");
      String suratAdd2 = (String) getContext().getRequest().getParameter("pihak.suratAlamat2");
      String suratAdd3 = (String) getContext().getRequest().getParameter("pihak.suratAlamat3");
      String suratAdd4 = (String) getContext().getRequest().getParameter("pihak.suratAlamat4");
      String suratPostcode = (String) getContext().getRequest().getParameter("pihak.suratPoskod");
      String suratNegeri = (String) getContext().getRequest().getParameter("pihak.suratNegeri.kod");

      if (StringUtils.isBlank(jenisPihak)) {
        addSimpleError("Sila pilih jenis pihak.");
        if (kodNegeri.equals("05")) {
          senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
        } else {
          senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
        }
        return new JSP(POPUP_PIHAK).addParameter("popup", "true");
      }
      if (pihak.getJenisPengenalan() == null || pihak.getNoPengenalan() == null || pihak.getNama() == null) {
        addSimpleError("Sila pastikan maklumat mandatori telah diisi.");
        if (kodNegeri.equals("05")) {
          senaraiKodPihak = kutipanDataService.findListSenaraiKodPihakN9();
        } else {
          senaraiKodPihak = kutipanDataService.findListSenaraiKodPihak();
        }
        return new JSP(POPUP_PIHAK).addParameter("popup", "true");
      }

      List<HakmilikPihakBerkepentingan> listhpb = new ArrayList<HakmilikPihakBerkepentingan>();
      List<Pihak> listphk = new ArrayList<Pihak>();
      InfoAudit ia = new InfoAudit();

      // FIRST: add or update table pihak
      Pihak phk = pihakDAO.findById(Long.parseLong(idPihak));
      if (phk == null) {
        phk = new Pihak();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        KodFlagPihak kodFlagPihak = new KodFlagPihak();
        kodFlagPihak.setKod("AK");
        phk.setKodFlagPihak(kodFlagPihak);  // SET PIHAK AS ACTIVE
        phk.setSuratAlamat1(suratAdd1);
        phk.setSuratAlamat2(suratAdd2);
        phk.setSuratAlamat3(suratAdd3);
        phk.setSuratAlamat4(suratAdd4);
        phk.setSuratPoskod(suratPostcode);
        phk.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));
      } else {
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());
      }
      phk.setNama(pihak.getNama());
      phk.setNoPengenalan(pihak.getNoPengenalan());
      if (pihak.getNegeriKelahiran() != null) {
        phk.setNegeriKelahiran(pihak.getNegeriKelahiran());
      } else {
        phk.setNegeriKelahiran(null);
      }
      phk.setTarikhLahir(pihak.getTarikhLahir());
      phk.setBangsa(kodBangsaDAO.findById(pihak.getBangsa().getKod()));
      phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
      phk.setKodJantina(pihak.getKodJantina());
      phk.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
      phk.setAlamat1(add1);
      phk.setAlamat2(add2);
      phk.setAlamat3(add3);
      phk.setAlamat4(add4);
      phk.setPoskod(postcode);
      phk.setNegeri(kodNegeriDAO.findById(negeri));
      //new format from etanah : cannot update surat alamat pihak !! 15/2/2013
      phk.setInfoAudit(ia);
      listphk.add(phk);
      if (!listphk.isEmpty()) {
        pihakService.saveOrUpdate(listphk);
      }

      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);

      for (Hakmilik lhm : listSenaraiHakmilik) {

        idHakmilik = lhm.getIdHakmilik();
        LOG.info(">>>> hakmilik : " + idHakmilik);
        Hakmilik hm = hakmilikDAO.findById(idHakmilik);
        /* ADD NEW HAKMILIK_PIHAK */
        HakmilikPihakBerkepentingan hpb = kutipanDataService.findPemilikbyHakmilikAndPihak(idHakmilik, idPihak, jenisPihak);
        if (hpb == null) {
          hpb = new HakmilikPihakBerkepentingan();
          hpb.setCawangan(hm.getCawangan());
          hpb.setHakmilik(hm);
          hpb.setPihak(phk);
          hpb.setNama(pihak.getNama());
          hpb.setKaveatAktif('T');
          hpb.setSyerPembilang(1);
          hpb.setSyerPenyebut(1);
          hpb.setJenis(kodJenisPihakBerkepentinganDAO.findById(jenisPihak));
          hpb.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
          hpb.setNoPengenalan(pihak.getNoPengenalan());
          hpb.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
          //------------------- Alamat ---------------
          hpb.setAlamat1(add1);
          hpb.setAlamat2(add2);
          hpb.setAlamat3(add3);
          hpb.setAlamat4(add4);
          hpb.setPoskod(postcode);
          hpb.setNegeri(kodNegeriDAO.findById(negeri));
          //---------------- Alamat surat ------------
          AlamatSurat surat = new AlamatSurat();
          surat.setAlamatSurat1(suratAdd1);
          surat.setAlamatSurat2(suratAdd2);
          surat.setAlamatSurat3(suratAdd3);
          surat.setAlamatSurat4(suratAdd4);
          surat.setPoskodSurat(suratPostcode);
          surat.setNegeriSurat(kodNegeriDAO.findById(suratNegeri));
          hpb.setAlamatSurat(surat);
          //------------------ Alamat END ---------------
          hpb.setAktif('Y');   // SET HAKMILIK_PIHAK as ACTIVE
          ia.setDimasukOleh(pguna);
          ia.setTarikhMasuk(new java.util.Date());
          hpb.setInfoAudit(ia);
          hakmilikPihakKepentinganService.save(hpb);
        } else {
          hpb.setNama(pihak.getNama());
          hpb.setKaveatAktif('T');
          hpb.setJenis(kodJenisPihakBerkepentinganDAO.findById(jenisPihak));
          hpb.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
          hpb.setNoPengenalan(pihak.getNoPengenalan());
          hpb.setWargaNegara(kodWarganegaraDAO.findById(pihak.getWargaNegara().getKod()));
          //------------------- Alamat ---------------
          hpb.setAlamat1(add1);
          hpb.setAlamat2(add2);
          hpb.setAlamat3(add3);
          hpb.setAlamat4(add4);
          hpb.setPoskod(postcode);
          hpb.setNegeri(kodNegeriDAO.findById(negeri));
          //---------------- Alamat surat ------------
          AlamatSurat surat = new AlamatSurat();
          surat.setAlamatSurat1(suratAdd1);
          surat.setAlamatSurat2(suratAdd2);
          surat.setAlamatSurat3(suratAdd3);
          surat.setAlamatSurat4(suratAdd4);
          surat.setPoskodSurat(suratPostcode);
          surat.setNegeriSurat(kodNegeriDAO.findById(suratNegeri));
          hpb.setAlamatSurat(surat);
          //------------------ Alamat END ---------------
          hpb.setAktif('Y');
          ia.setDiKemaskiniOleh(pguna);
          ia.setTarikhKemaskini(new java.util.Date());
          hpb.setInfoAudit(ia);
          hakmilikPihakKepentinganService.save(hpb);
        }
      }
      setRefresh(Boolean.TRUE);
    }
    return new JSP(POPUP_PIHAK).addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
  }

  public Resolution hapusPihak() {
    String[] id = getContext().getRequest().getParameterValues("idHapus");
    for (String string : id) {
      String idHapus = string;
      HakmilikPihakBerkepentingan hpbHapus = hakmilikPihakKepentinganService.findById(idHapus);
      idHakmilik = hpbHapus.getHakmilik().getIdHakmilik();
      kumpHm = hpbHapus.getHakmilik().getKumpulan();
      /* DELETE HAKMILIK PIHAK */
      regService.deletePihakBerkepentingan(hpbHapus);
    }
    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah   
    senaraiHakmilikUrusan(kumpHm);

    addSimpleMessage("Maklumat pihak berjaya dihapus.");
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution agihSamaRata() {
    Fraction sumAllPemohon = Fraction.ONE;
    Fraction samaRata = Fraction.ZERO;

    hakmilik = hakmilikDAO.findById(idHakmilik);
    List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
    List<HakmilikPihakBerkepentingan> hakmilikPihakListBaru = kutipanDataService.findListPemilik(idHakmilik);
    if (hakmilikPihakListBaru != null && hakmilikPihakListBaru.size() > 0) {
      samaRata = sumAllPemohon.divide(hakmilikPihakListBaru.size());

      for (HakmilikPihakBerkepentingan hpb : hakmilikPihakListBaru) {
        hpb.setJumlahPembilang(samaRata.getNumerator());
        hpb.setJumlahPenyebut(samaRata.getDenominator());  
        hpb.setSyerPembilang(samaRata.getNumerator());
        hpb.setSyerPenyebut(samaRata.getDenominator());
        senarai.add(hpb);
      }
    }
    hakmilikPihakKepentinganService.saveList(senarai); // update pihak
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution simpanSyer() {
    // USE THIS TO SAVE SYER
    
      Fraction sum = Fraction.ZERO;
    
      
    hakmilik = hakmilikDAO.findById(idHakmilik);
    List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
    for (int i = 0; i < listHakmilikPihak.size(); i++) {
      long idhpk = listHakmilikPihak.get(i).getIdHakmilikPihakBerkepentingan();
      HakmilikPihakBerkepentingan hp = hpbDAO.findById(idhpk);
      hp.setSyerPembilang(listHakmilikPihak.get(i).getSyerPembilang());
      hp.setSyerPenyebut(listHakmilikPihak.get(i).getSyerPenyebut());
      //todo : proper insert jum_pembilang/jum_penyebut
      hp.setJumlahPembilang(listHakmilikPihak.get(i).getSyerPembilang());
      hp.setJumlahPenyebut(listHakmilikPihak.get(i).getSyerPenyebut());
      sum = sum.add(new Fraction(listHakmilikPihak.get(i).getSyerPembilang(), listHakmilikPihak.get(i).getSyerPenyebut()));      
      senarai.add(hp);
    }
    
    int eq = sum.compareTo(Fraction.ONE);
    
    if (eq == 0)
        hakmilikPihakKepentinganService.saveList(senarai); // update pihak
    
    addSimpleError("Maklumat syer berjaya dikemaskini");
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage")
            .addParameter("idHakmilik", idHakmilik)
            .addParameter("kumpHm", kumpHm).addParameter("eq", eq);
  }

  public Resolution senaraiKodGunaTanahByKatTanah() {
    LOG.info("KOD KATEGORI TANAH");
    kodKatTanah = (String) getContext().getRequest().getParameter("kt");
    idHakmilik = (String) getContext().getRequest().getParameter("hm");

    if (idHakmilik != null) {
      hakmilik = hakmilikDAO.findById(idHakmilik);
      int kodBPM = hakmilik.getBandarPekanMukim().getKod();
      cariKodBPM(kodDaerah); // find kodBPM     
      cariKodSeksyen(kodBPM);  // find seksyen
      if (hakmilik.getSyaratNyata() != null) {
        kodSyarat = hakmilik.getSyaratNyata().getKod();
      }
      if (hakmilik.getSekatanKepentingan() != null) {
        kodSekatan = hakmilik.getSekatanKepentingan().getKod();
      }
      if (StringUtils.isNotBlank(kodKatTanah)) {
        listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
      }
      listHakmilikPihak = kutipanDataService.findListPemilik(idHakmilik);
      listHakmilikAsal = kutipanDataService.findListHakmilikAsalByIdHakmilik(idHakmilik);    // find list hakmilik asal
      listHakmilikSblm = kutipanDataService.findListHakmilikSebelumByIdHakmilik(idHakmilik); // find list hakmilik sebelum
    }

    listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);
    getKewAkaun("AC", listSenaraiHakmilik); // find Cukai Tanah
    senaraiHakmilikUrusan(kumpHm);
    return new JSP(KUTIPAN_TAB).addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm);
  }

  public Resolution janaDokumen() {
    /* GENERATE REPORT*/
    if (kumpHm > 0) {
      String pathKE = ""; //DHKE
      String pathDE = ""; //DHDE
      String genKE = ""; //DHKE
      String genDE = ""; //DHDE

      ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
      String dokumenPath = conf.getProperty("document.path");
      ArrayList<Hakmilik> lhm = new ArrayList<Hakmilik>();
      listSenaraiHakmilik = kutipanDataService.findListHakmilikByKump(kumpHm);

      for (Hakmilik hm : listSenaraiHakmilik) {

        KodDokumen kdKE = new KodDokumen();
        KodDokumen kdDE = new KodDokumen();

        // REPORT NAME
        if (kodNegeri.equalsIgnoreCase("05")) {
          genKE = "regBorangHMDHKE.rdf";
          genDE = "regBorangHMDHDE.rdf";
        } else if (kodNegeri.equalsIgnoreCase("04")) {
          genKE = "regBorangHMDHKEml.rdf";
          genDE = "regBorangHMDHDEml.rdf";
        }

        // ADD TABLE DOKUMEN
        kdKE.setKod("DHKE");
        kdDE.setKod("DHDE");
        Dokumen ke = saveOrUpdateDokumen(kdKE, hm.getIdHakmilik());
        Dokumen de = saveOrUpdateDokumen(kdDE, hm.getIdHakmilik());

        // GENERATE DHKE and DHDE
        String[] params = new String[]{"p_id_hakmilik"};
        String[] values = new String[]{hm.getIdHakmilik()};
        pathKE = File.separator + String.valueOf(ke.getIdDokumen());
        LOG.info(" >> Path DHKE To save : " + pathKE);
        Future<byte[]> dhke = executor.submit(new CallableReport(reportUtilKE, genKE, params, values, dokumenPath + pathKE, pguna));
        File signKE = new File(dokumenPath + pathKE + ".sig");
        LOG.info(": >> FILE sign : " + signKE);
        if (signKE.exists()) {
          signKE.delete();
        }
        try {
          dhke.get();
        } catch (Exception ex) {
          LOG.debug(ex.getMessage(), ex);
        }

        pathDE = File.separator + String.valueOf(de.getIdDokumen());
        LOG.info(" >> Path DHKE To save : " + pathDE);
        Future<byte[]> dhde = executor.submit(new CallableReport(reportUtilDE, genDE, params, values, dokumenPath + pathDE, pguna));
        File signDE = new File(dokumenPath + pathDE + ".sig");
        LOG.info(": >> FILE sign : " + signDE);
        if (signDE.exists()) {
          signDE.delete();
        }
        try {
          dhde.get();
        } catch (Exception ex) {
          LOG.debug(ex.getMessage(), ex);
        }

        // UPDATE NAMA_FIZIKAL TABLE DOKUMEN
        updatePathDokumen(reportUtilKE.getDMSPath(), ke.getIdDokumen(), reportUtilKE.getContentType());
        updatePathDokumen(reportUtilDE.getDMSPath(), de.getIdDokumen(), reportUtilDE.getContentType());

        hm.setDhke(ke);
        hm.setDhde(de);
        lhm.add(hm);
      }

      if (!lhm.isEmpty()) {
        //UPDATE HAKMILIK
        regService.simpanHakmilikList(lhm);
      }
    }
    selectedTab = "dokumen_hakmilik";
    return new RedirectResolution(KemaskiniDataActionBean.class, "refreshPage").addParameter("idHakmilik", idHakmilik).addParameter("kumpHm", kumpHm).addParameter("selectedTab", selectedTab);
  }

  private Dokumen saveOrUpdateDokumen(KodDokumen kd, String id) {
    // USE THIS TO ADD OR UPDATE TABLE DOKUMEN
    InfoAudit ia = new InfoAudit();
    List<Dokumen> listdDokumen = kutipanDataService.findListDokumen(kd, id);
    for (Dokumen listdDoc : listdDokumen) {
      // UPDATE previous Dok as old
      ia.setDiKemaskiniOleh(pguna);
      ia.setTarikhKemaskini(new java.util.Date());
      listdDoc.setBaru('T');
      listdDoc.setInfoAudit(ia);
      dokumenService.saveOrUpdate(listdDoc);
    }

    // Add new DHKE / DHDE
    Dokumen doc = new Dokumen();
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());
    doc.setBaru('Y');
    doc.setKlasifikasi(kodKlasifikasiDAO.findById(3));
    doc.setFormat("application/pdf");
    doc.setNoVersi("0");
    doc.setKodDokumen(kd);
    doc.setDalamanNilai1(id);
    if (kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) {
      doc.setTajuk(kd.getKod() + "(" + id + ")");   // example: DHKE ( <idHakmilik> )
    } else {
      doc.setTajuk(kd.getKod());
    }
    doc.setInfoAudit(ia);
    doc = dokumenService.saveOrUpdate(doc);

    return doc;
  }

  private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
    // USE THIS TO UPDATE NAMA_FIZIKAL
    Dokumen doc = dokumenService.findById(idDokumen);
    doc.setNamaFizikal(namaFizikal);
    doc.setFormat(format);
    dokumenService.saveOrUpdate(doc);
  }

  private void getKewAkaun(String kodAkaunCukai, List<Hakmilik> listSenaraiHakmilik) {
    //USE THIS TO GET KEW_AKAUN INFO
    for (Hakmilik h : listSenaraiHakmilik) {
      Akaun kewAkaun = kutipanDataService.findAkaunForHakmilik(h.getCawangan().getKod(), h.getIdHakmilik(), kodAkaunCukai); // find kew_akaun
      if (kewAkaun != null) {
        listKewAkaun.add(kewAkaun);
      }
    }
  }

  public Resolution checkLuas() {
    /// Conversion :
    // 1 acre = 4.00000002214 rood 
    // 1 acre = 160 pole 
    // 1 acre = 0.404686 hectare 
    // 1 acre = 4046.8564 square meter

    String result = "0";
    String luas = (String) getContext().getRequest().getParameter("luas");
    String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");

    if (StringUtils.isNotBlank(luas) && StringUtils.isNotBlank(kodUOM)) {
      if (kodUOM.equalsIgnoreCase("E")) {
        // convert ekar rood pole to ekar
        LOG.info(">>>>>>> luas.toString().length() : " + luas.toString().length());
        String ekar = luas.substring(0, 4);
        String rood = luas.substring(4, 5);
        String pool = luas.substring(5, 12);

        BigDecimal total = new BigDecimal(Integer.parseInt(ekar) + (Integer.parseInt(rood) / 4.00000002214) + (Double.parseDouble(pool) / 160));
        // round up to 0.0000
        total = total.setScale(4, BigDecimal.ROUND_UP);
        result = String.valueOf(total);
      }
    } else {
      result = "";
    }
    LOG.info("result noLot : " + result);
    return new StreamingResolution("text/plain", result);
  }

  public Resolution checkPelan() {
    String result = "0";
    String noLot = (String) getContext().getRequest().getParameter("noLot");
    String kNegeri = (String) getContext().getRequest().getParameter("kodNegeri");
    String kDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
    String kodBPM = (String) getContext().getRequest().getParameter("kodBPM");
    String kodSeksyen = (String) getContext().getRequest().getParameter("kodSeksyen");
    String jenisPelan = (String) getContext().getRequest().getParameter("jenisPelan");

    if (!kodBPM.isEmpty()) {
      LOG.info("> kodNegeri " + kNegeri);
      LOG.info("> kodDaerah " + kDaerah);
      LOG.info("> kodBPM " + kodBPM);
      LOG.info("> jenisPelan " + jenisPelan);
      LOG.info("> noLot: " + noLot);

      if (StringUtils.isNotBlank(noLot)) {
        String path = regService.cariPathPelan(kNegeri, kDaerah, kodBPM, kodSeksyen, noLot, jenisPelan);
        if (StringUtils.isNotBlank(path)) {
          result = "1";
        } else {
          result = "0";
        }
      }
    }
    LOG.info("> Result Pelan : " + result);
    return new StreamingResolution("text/plain", result);
  }

  public Resolution cetak() {
    idDokumen = getContext().getRequest().getParameter("id");
    return new JSP("daftar/utiliti/kutipan_data_cetakPopup.jsp").addParameter("popup", "true");
  }

  public List<KodHakmilik> getSenaraiKodHakmilikPTD() {
    // list for all jenis hakmilik for PTD N9 Only
//    String query = "SELECT u FROM etanah.model.KodHakmilik u "
//            + "WHERE u.kod in('HSM','PM','GM','AA','MYG','OT','EMR','HSL','RH','LM','OTM') and u.aktif='Y' "
//            + "ORDER BY u.nama asc";
      
      String query = "SELECT u FROM etanah.model.KodHakmilik u "
              + "WHERE u.milikDaerah = 'Y' "
              + "ORDER BY u.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<KodHakmilik> getSenaraiKodHakmilikPTDmlk() {
    // list for all jenis hakmilik for PTD Melaka Only
    String query = "SELECT u FROM etanah.model.KodHakmilik u "
            + "WHERE u.kod in('HSM','PM','GM','AA','MYG','OT','EMR','HSL','RH','LM','OTM','GMM','HMM') and u.aktif='Y' "
            + "ORDER BY u.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<KodHakmilik> getSenaraiKodHakmilikPTG() {
    // list for all jenis hakmilik for PTG
    LOG.info("kod negeri : " + kodNegeri);
//    String query = "SELECT u FROM etanah.model.KodHakmilik u "
//            + "WHERE u.kod in('HSD','PN','GRN','GRT','SL','ACT','IR','AA','EMR') and u.aktif='Y'  "
//            + "ORDER BY u.nama asc";
    
    String query = "SELECT u FROM etanah.model.KodHakmilik u "
              + "WHERE u.milikDaerah = 'T' "
              + "ORDER BY u.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }
  public List<KodHakmilik> getSenaraiKodHakmilikAll() {
    // list for all jenis hakmilik for PTG
    LOG.info("kod negeri : " + kodNegeri);
    String query = "SELECT u FROM etanah.model.KodHakmilik u "
            + "ORDER BY u.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<KodDaerah> getSenaraiKodDaerahPTG() {
    // list for all Daerah for PTG    
    String query = "SELECT d FROM etanah.model.KodDaerah d "
            + "WHERE d.aktif='Y'  "
            + "ORDER BY d.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public HakmilikPihakBerkepentingan findListPemilikByHakmilikAndPihak(Hakmilik hakmilik, Pihak pihak) {
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik and hpb.pihak.idPihak = :idPihak and hpb.aktif='Y' ";
    Query q = sessionProvider.get().createQuery(query)
            .setString("idHakmilik", hakmilik.getIdHakmilik())
            .setLong("idPihak", pihak.getIdPihak());
    return (HakmilikPihakBerkepentingan) q.uniqueResult();
  }
  
  public HakmilikPihakBerkepentingan findListPemilikByHakmilikAndPihakAll(Hakmilik hakmilik, Pihak pihak) {
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHEre hpb.hakmilik.idHakmilik = :idHakmilik and hpb.pihak.idPihak = :idPihak ";
    Query q = sessionProvider.get().createQuery(query)
            .setString("idHakmilik", hakmilik.getIdHakmilik())
            .setLong("idPihak", pihak.getIdPihak());
    
     /* if (!q.list().isEmpty()) {
            return (HakmilikPihakBerkepentingan) q.list().get(0);
        } else {
            return (HakmilikPihakBerkepentingan) q.uniqueResult();
        }*/
   return (HakmilikPihakBerkepentingan) q.uniqueResult();
  }  

  public List<KodKategoriTanah> getSenaraiKodKategoriTanah() {
    // Kod_Katg_Tanah ONLY Kod 0 -> 4
    Session s = sessionProvider.get();
    Query q = s.createQuery("FROM etanah.model.KodKategoriTanah kt "
            + "WHERE kt.aktif = 'Y' and kt.kod in ('0','1','2','3','4')"
            + "ORDER BY kt.kod asc");
    return q.list();
  }

  public List<KodUOM> getSenaraiKodUOMByLuas2() {
    String query = "FROM etanah.model.KodUOM";
    query += " WHERE kod in ('H','M','A','P','D','E','K','R') "
            + "ORDER BY kod asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<KodLot> getSenaraiKodLot() {
    String query = "FROM etanah.model.KodLot";
    query += " WHERE kod not in ('4','7','10','11') "
            + "ORDER BY kod asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public boolean isKelompok() {
    return kelompok;
  }

  public void setKelompok(boolean kelompok) {
    this.kelompok = kelompok;
  }

  public List<KodHakmilik> getSenaraiKodHakmilik() {
    return senaraiKodHakmilik;
  }

  public void setSenaraiKodHakmilik(List<KodHakmilik> senaraiKodHakmilik) {
    this.senaraiKodHakmilik = senaraiKodHakmilik;
  }

  public String getKodNegeri() {
    return kodNegeri;
  }

  public void setKodNegeri(String kodNegeri) {
    this.kodNegeri = kodNegeri;
  }

  public List<KodDaerah> getSenaraiKodDaerah() {
    return senaraiKodDaerah;
  }

  public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
    this.senaraiKodDaerah = senaraiKodDaerah;
  }

  public String getKodDaerah() {
    return kodDaerah;
  }

  public void setKodDaerah(String kodDaerah) {
    this.kodDaerah = kodDaerah;
  }

  public String getNamaNegeri() {
    return namaNegeri;
  }

  public void setNamaNegeri(String namaNegeri) {
    this.namaNegeri = namaNegeri;
  }

  public List<KodBandarPekanMukim> getListBandarPekanMukim() {
    return listBandarPekanMukim;
  }

  public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
    this.listBandarPekanMukim = listBandarPekanMukim;
  }

  public String getSelectedTab() {
    return selectedTab;
  }

  public void setSelectedTab(String selectedTab) {
    this.selectedTab = selectedTab;
  }

  public String getNoHakmilik() {
    return noHakmilik;
  }

  public void setNoHakmilik(String noHakmilik) {
    this.noHakmilik = noHakmilik;
  }

  public List<String> getNoHakmilikSiriDari() {
    return noHakmilikSiriDari;
  }

  public void setNoHakmilikSiriDari(List<String> noHakmilikSiriDari) {
    this.noHakmilikSiriDari = noHakmilikSiriDari;
  }

  public List<String> getNoHakmilikSiriHingga() {
    return noHakmilikSiriHingga;
  }

  public void setNoHakmilikSiriHingga(List<String> noHakmilikSiriHingga) {
    this.noHakmilikSiriHingga = noHakmilikSiriHingga;
  }

  public int getBilHakmilik() {
    return bilHakmilik;
  }

  public void setBilHakmilik(int bilHakmilik) {
    this.bilHakmilik = bilHakmilik;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public String getKodJenisHakmilik() {
    return kodJenisHakmilik;
  }

  public void setKodJenisHakmilik(String kodJenisHakmilik) {
    this.kodJenisHakmilik = kodJenisHakmilik;
  }

  public KodHakmilik getKodHakmilik() {
    return kodHakmilik;
  }

  public void setKodHakmilik(KodHakmilik kodHakmilik) {
    this.kodHakmilik = kodHakmilik;
  }

  public String getBpm() {
    return bpm;
  }

  public void setBpm(String bpm) {
    this.bpm = bpm;
  }

  public Pengguna getPguna() {
    return pguna;
  }

  public void setPguna(Pengguna pguna) {
    this.pguna = pguna;
  }

  public ArrayList<Hakmilik> getListHakmilik() {
    return listHakmilik;
  }

  public void setListHakmilik(ArrayList<Hakmilik> listHakmilik) {
    this.listHakmilik = listHakmilik;
  }

  public List<KodSeksyen> getListKodSeksyen() {
    return listKodSeksyen;
  }

  public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
    this.listKodSeksyen = listKodSeksyen;
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

  public List<KodSyaratNyata> getLisSyaratNyata() {
    return lisSyaratNyata;
  }

  public void setLisSyaratNyata(List<KodSyaratNyata> lisSyaratNyata) {
    this.lisSyaratNyata = lisSyaratNyata;
  }

  public String getKodKatTanah() {
    return kodKatTanah;
  }

  public void setKodKatTanah(String kodKatTanah) {
    this.kodKatTanah = kodKatTanah;
  }

  public List<KodSekatanKepentingan> getLisSekatan() {
    return lisSekatan;
  }

  public void setLisSekatan(List<KodSekatanKepentingan> lisSekatan) {
    this.lisSekatan = lisSekatan;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public boolean isRefresh() {
    return refresh;
  }

  public void setRefresh(boolean refresh) {
    this.refresh = refresh;
  }

  public String getNamaSyarat() {
    return namaSyarat;
  }

  public void setNamaSyarat(String namaSyarat) {
    this.namaSyarat = namaSyarat;
  }

  public String getNamaSekatan() {
    return namaSekatan;
  }

  public void setNamaSekatan(String namaSekatan) {
    this.namaSekatan = namaSekatan;
  }

  public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
    return listHakmilikPihak;
  }

  public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
    this.listHakmilikPihak = listHakmilikPihak;
  }

  public List<Pihak> getListPihak() {
    return listPihak;
  }

  public void setListPihak(List<Pihak> listPihak) {
    this.listPihak = listPihak;
  }

  public String getIdPihak() {
    return idPihak;
  }

  public void setIdPihak(String idPihak) {
    this.idPihak = idPihak;
  }

  public HakmilikPihakBerkepentingan getHakmilikPihak() {
    return hakmilikPihak;
  }

  public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
    this.hakmilikPihak = hakmilikPihak;
  }

  public Pihak getPihak() {
    return pihak;
  }

  public void setPihak(Pihak pihak) {
    this.pihak = pihak;
  }

  public int getKumpHm() {
    return kumpHm;
  }

  public void setKumpHm(int kumpHm) {
    this.kumpHm = kumpHm;
  }

  public List<Hakmilik> getListSenaraiHakmilik() {
    return listSenaraiHakmilik;
  }

  public void setListSenaraiHakmilik(List<Hakmilik> listSenaraiHakmilik) {
    this.listSenaraiHakmilik = listSenaraiHakmilik;
  }

  public List<KodJenisPihakBerkepentingan> getSenaraiKodPihak() {
    return senaraiKodPihak;
  }

  public void setSenaraiKodPihak(List<KodJenisPihakBerkepentingan> senaraiKodPihak) {
    this.senaraiKodPihak = senaraiKodPihak;
  }

  public HakmilikUrusan getHakmilikUrusan() {
    return hakmilikUrusan;
  }

  public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
    this.hakmilikUrusan = hakmilikUrusan;
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

  public String getJenisUrusan() {
    return jenisUrusan;
  }

  public void setJenisUrusan(String jenisUrusan) {
    this.jenisUrusan = jenisUrusan;
  }

  public String getKodUrusan() {
    return kodUrusan;
  }

  public void setKodUrusan(String kodUrusan) {
    this.kodUrusan = kodUrusan;
  }

  public List<KodUrusan> getListkodUrusanN() {
    return listkodUrusanN;
  }

  public void setListkodUrusanN(List<KodUrusan> listkodUrusanN) {
    this.listkodUrusanN = listkodUrusanN;
  }

  public List<KodUrusan> getListkodUrusanB() {
    return listkodUrusanB;
  }

  public void setListkodUrusanB(List<KodUrusan> listkodUrusanB) {
    this.listkodUrusanB = listkodUrusanB;
  }

  public List<KodUrusan> getListkodUrusanSC() {
    return listkodUrusanSC;
  }

  public void setListkodUrusanSC(List<KodUrusan> listkodUrusanSC) {
    this.listkodUrusanSC = listkodUrusanSC;
  }

  public Permohonan getMohon() {
    return mohon;
  }

  public void setMohon(Permohonan mohon) {
    this.mohon = mohon;
  }

  public String getJam() {
    return jam;
  }

  public void setJam(String jam) {
    this.jam = jam;
  }

  public String getMinit() {
    return minit;
  }

  public void setMinit(String minit) {
    this.minit = minit;
  }

  public String getSaat() {
    return saat;
  }

  public void setSaat(String saat) {
    this.saat = saat;
  }

  public String getAmpm() {
    return ampm;
  }

  public void setAmpm(String ampm) {
    this.ampm = ampm;
  }

  public Date getTrhDaftar() {
    return trhDaftar;
  }

  public void setTrhDaftar(Date trhDaftar) {
    this.trhDaftar = trhDaftar;
  }

  public String getTarikhDaftar() {
    return tarikhDaftar;
  }

  public void setTarikhDaftar(String tarikhDaftar) {
    this.tarikhDaftar = tarikhDaftar;
  }

  public String getKodPelan() {
    return kodPelan;
  }

  public void setKodPelan(String kodPelan) {
    this.kodPelan = kodPelan;
  }

  public List<KodUrusan> getListkodUrusan() {
    return listkodUrusan;
  }

  public void setListkodUrusan(List<KodUrusan> listkodUrusan) {
    this.listkodUrusan = listkodUrusan;
  }

  public List<String> getIdHakmilikSiriDari() {
    return idHakmilikSiriDari;
  }

  public void setIdHakmilikSiriDari(List<String> idHakmilikSiriDari) {
    this.idHakmilikSiriDari = idHakmilikSiriDari;
  }

  public List<String> getIdHakmilikSiriHingga() {
    return idHakmilikSiriHingga;
  }

  public void setIdHakmilikSiriHingga(List<String> idHakmilikSiriHingga) {
    this.idHakmilikSiriHingga = idHakmilikSiriHingga;
  }

  public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
    return listKodGunaTanahByKatTanah;
  }

  public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
    this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
  }

  public String getKodGunaTanah() {
    return kodGunaTanah;
  }

  public void setKodGunaTanah(String kodGunaTanah) {
    this.kodGunaTanah = kodGunaTanah;
  }

  public PermohonanUrusan getMohonUrusan() {
    return mohonUrusan;
  }

  public void setMohonUrusan(PermohonanUrusan mohonUrusan) {
    this.mohonUrusan = mohonUrusan;
  }

  public PermohonanRujukanLuar getMohonRujLuar() {
    return mohonRujLuar;
  }

  public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
    this.mohonRujLuar = mohonRujLuar;
  }

  public String getIdDokumen() {
    return idDokumen;
  }

  public void setIdDokumen(String idDokumen) {
    this.idDokumen = idDokumen;
  }

  public PermohonanPihak getPermohonanPihak() {
    return permohonanPihak;
  }

  public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
    this.permohonanPihak = permohonanPihak;
  }

  public boolean isPemegang() {
    return pemegang;
  }

  public void setPemegang(boolean pemegang) {
    this.pemegang = pemegang;
  }

  public List<PermohonanPihak> getListMohonPihak() {
    return listMohonPihak;
  }

  public void setListMohonPihak(List<PermohonanPihak> listMohonPihak) {
    this.listMohonPihak = listMohonPihak;
  }

  public Pemohon getPemohon() {
    return pemohon;
  }

  public void setPemohon(Pemohon pemohon) {
    this.pemohon = pemohon;
  }

  public List<Pemohon> getListPemohon() {
    return listPemohon;
  }

  public void setListPemohon(List<Pemohon> listPemohon) {
    this.listPemohon = listPemohon;
  }

  public List<HakmilikUrusan> getListHakmilikUrusanSebelum() {
    return listHakmilikUrusanSebelum;
  }

  public void setListHakmilikUrusanSebelum(List<HakmilikUrusan> listHakmilikUrusanSebelum) {
    this.listHakmilikUrusanSebelum = listHakmilikUrusanSebelum;
  }

  public List<PermohonanHubungan> getListMohonHbgn() {
    return listMohonHbgn;
  }

  public void setListMohonHbgn(List<PermohonanHubungan> listMohonHbgn) {
    this.listMohonHbgn = listMohonHbgn;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public List<Akaun> getListKewAkaun() {
    return listKewAkaun;
  }

  public void setListKewAkaun(List<Akaun> listKewAkaun) {
    this.listKewAkaun = listKewAkaun;
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

  public Date getTrhDaftarAsal() {
    return trhDaftarAsal;
  }

  public void setTrhDaftarAsal(Date trhDaftarAsal) {
    this.trhDaftarAsal = trhDaftarAsal;
  }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
    
   public List<HakmilikUrusan> getListHakmilikUrusanNB() {
    return listHakmilikUrusanNB;
  }

  public void setListHakmilikUrusanNB(List<HakmilikUrusan> listHakmilikUrusanNB) {
    this.listHakmilikUrusanNB = listHakmilikUrusanN;
  }

    public List<KodUrusan> getListkodUrusanNB() {
        return listkodUrusanNB;
    }

    public void setListkodUrusanNB(List<KodUrusan> listkodUrusanNB) {
        this.listkodUrusanNB = listkodUrusanNB;
    } 
}
