/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import electric.xml.Document;
import electric.xml.Element;
import electric.xml.Elements;
import electric.xml.XPath;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.manager.TabManager;
import etanah.model.KodUrusan;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author khairul
 * refer = LaporanMaklumatUrusanMis.java
 */
@UrlBinding("/utiliti/reports")
public class UtilitiReportActionBean extends AbleActionBean{
  
  private static final Logger LOG = Logger.getLogger(UtilitiReportActionBean.class);  
  private List<Document> xmlList;
  private String[] laporanPPCB;
  private String[] laporanPPCBReport;
  private String[] laporanTsSama;
  private String[] laporanTsSamaReport;
  private String[] laporanTsLainKatg;
  private String[] laporanTsLainKatgReport;
  private String[] laporanTsSyaratNyata;
  private String[] laporanTsSyaratNyataReport;
  private String[] laporanPPCS;
  private String[] laporanPPCSReport;
  private String[] laporanPYTN;
  private String[] laporanPYTNReport;
  private String[] laporanPSMT;
  private String[] laporanPSMTReport;
  private String[] laporanSBMS;
  private String[] laporanSBMSReport;
  private String[] laporanSerahBalikTanah;
  private String[] laporanSerahBalikTanahReport;
  private String[] laporanTSPSS;
  private String[] laporanTSPSSReport;
  private String[] laporan6Urusan;
  private String[] laporan6UrusanReport;
  private String[] laporanStatusPermohonan;
  private String[] laporanStatusPermohonanReport;
  private String[] laporanKemajuanRayuan;
  private String[] laporanKemajuanRayuanReport;
  private String[] laporanPBSK;
  private String[] laporanPBSKReport;
  private String[] laporan5a;
  private String[] laporan5aReport;
  private String[] laporan7g;
  private String[] laporan7gReport;
  private String[] laporan17;
  private String[] laporan17Report;
  private String[] laporan18;
  private String[] laporan18Report;
  private String[] laporan19;
  private String[] laporan19Report;
  
  private String reportName;
  private String report;
  private List<String> listYear = new ArrayList<String>();
  private Pengguna peng;
  private String kodNegeri;
  private List<Pengguna> listPguna;
  private String kodCaw;
  private String kodDaerah;
  private String daerahHasil;
  private String report_p_bil_hari;
  private static String staticKodCaw;
  private List<KodBandarPekanMukim> senaraiBPM;
  private List<KodBandarPekanMukim> senaraiBPM04;
  private List<KodBandarPekanMukim> senaraiBPM05;
  private List<KodUrusan> senaraiUrusan;
  //protected com.google.inject.Provider<Session> sessionProvider;
  private String kodNeg;
  private String daerah;
  private String report_p_kod_daerah;
  private static String staticKodDaerah;
  
  @Inject
  PenggunaDAO penggunaDao;
  @Inject
  private etanah.Configuration conf;
  @Inject
  KodCawanganDAO kodCawanganDAO; 
  @Inject
  PembetulanService pService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
 
  

  @DefaultHandler
  public Resolution showForm() {
    LOG.info("showForm");
//    if ("04".equals(conf.getProperty("kodNegeri"))) {
//      kodNegeri = "melaka";
//    }
//    if ("05".equals(conf.getProperty("kodNegeri"))) {
//      kodNegeri = "n9";
//    }
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    showReports();
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka.jsp");
    
  }

  private void showReports() {
    LOG.info("showReport:start");

      //NAMA REPORT
      laporanPPCB = new String[]{"Laporan Kemajuan Pecah Bahagian"};
      laporanTsSama = new String[]{"Laporan Permohonan Tukar Syarat Sama Kategori"};
      laporanTsLainKatg = new String [] {"Laporan Permohonan Tukar Syarat Lain Kategori"}; 
      laporanTsSyaratNyata = new String [] {"Laporan Permohonan Tukar Syarat Nyata"}; 
      laporanPPCS = new String [] {"Laporan Kemajuan Permohonan Pecah Sempadan"}; 
      laporanPYTN = new String [] {"Laporan Kemajuan Permohonan Penyatuan Tanah"}; 
      laporanPSMT = new String [] {"Laporan Kemajuan Permohonan Serah Semua Tanah"}; 
      laporanSBMS = new String [] {"Laporan Kemajuan Permohonan Penyerahan Balik Dan Pemberimilikan Semula"}; 
      laporanSerahBalikTanah = new String [] {"Laporan Kemajuan Permohonan Penyerahan Balik Tanah"}; 
      laporanTSPSS = new String [] {"Laporan Kemajuan Permohonan Pecah Sempadan Dan Tukar Syarat Serentak"}; 
      laporan6Urusan = new String [] {"Laporan Kemajuan Permohonan Pecah Sempadan/Pecah Bahagian/Penyatuan Tanah/"
                                      + "Pecah Sempadan Dan Tukar Syarat Serentak/Penyerahan Balik Dan Pemberimilikan Semula/Tukar Syarat"};     
      laporanStatusPermohonan = new String[] {"Laporan Status Permohonan"}; 
      laporanKemajuanRayuan = new String[] {"Laporan Kemajuan Permohonan Rayuan Pembangunan Tanah"}; 
      laporanPBSK = new String[] {"Laporan Kemajuan Permohonan Sekatan Kepentingan"}; 
      laporan5a = new String[] {"Laporan Pembayaran Premium (5A) Pembangunan Tanah"};
      laporan7g = new String[] {"Laporan Pembayaran Premium (7G) Pembangunan Tanah"};
      laporan17 = new String[] {"Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan"
                                    + "Mengikut Tahun/Bulan/Minggu"};
      laporan18 = new String[] {"Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Sekatan Kepentingan Bagi Bilangan Permohonan"
                                    + "Mengikut Individu/Syarikat"};
      laporan19 = new String[] {"Laporan Permohonan Meminda/Membatal/Mengena/Menghapus Bagi Status Permohonan Tugasan Siap"
                                    + "Disetiap Peringkat"};
      
      //REPORTS RDF
      laporanPPCBReport = new String[]{"DEV_STAT_PPCBbydaerah.rdf"};   
      laporanTsSamaReport = new String[]{"DEV_STAT_TSSamabydaerah.rdf"};
      laporanTsLainKatgReport = new String[]{"DEV_STAT_TSLainbydaerah.rdf"};   
      laporanTsSyaratNyataReport = new String[]{"DEV_STAT_TSSyaratNyatabydaerah.rdf"};   
      laporanPPCSReport = new String[]{"DEV_STAT_PPCSbydaerah.rdf"};
      laporanPYTNReport = new String[]{"DEV_STAT_PYTNbydaerah.rdf"};
      laporanPSMTReport = new String[]{"DEV_STAT_PSMTbydaerah.rdf"};
      laporanSBMSReport = new String[]{"DEV_STAT_SBMSbydaerah.rdf"};
      laporanSerahBalikTanahReport = new String[]{"DEV_STAT_SerahBalikTanahbydaerah.rdf"};
      laporanTSPSSReport = new String[]{"DEV_STAT_TSPSSbydaerah.rdf"};
      laporan6UrusanReport = new String[]{"DEV_STAT_Progress_6urusanbydaerah.rdf"};
      laporanStatusPermohonanReport = new String[] {"DEV_StatusPermohonan_bydaerah.rdf"};
      laporanKemajuanRayuanReport = new String[] {"DEV_STAT_All_Rayuan_bydaerah.rdf"};
      laporanPBSKReport = new String[] {"DEV_STAT_PBSKbydaerah.rdf"};
      laporan5aReport = new String[] {"DEV_STAT_5A_bydaerah.rdf"};
      laporan7gReport = new String[] {"DEV_STAT_7G_bydaerah.rdf"};
      laporan17Report = new String[] {"DEV_STAT_PBSK_bytime_bydaerah.rdf"};
      laporan18Report = new String[] {"DEV_STAT_PBSK_bypemohon_bydaerah.rdf"};
      laporan19Report = new String[] {"DEV_Bil_Tugasan_Siap_bydaerah.rdf"};     

    LOG.info("showReport:finish");  
    }

  public Resolution requestParam() {
    reportName = getContext().getRequest().getParameter("namaReport");
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    KodCawangan kc = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
    listPguna = pService.findPenggunaKodCaw(kc);
    if (peng.getKodCawangan().getDaerah() != null) {
      kodDaerah = peng.getKodCawangan().getDaerah().getKod();
      kodCaw = peng.getKodCawangan().getKod();
      LOG.info("(requestParam) kodDaerah :" + kodDaerah);
      LOG.info("(requestParam) kodCaw :" + kodCaw);
      staticKodDaerah = kodDaerah;
      staticKodCaw = kodCaw;
      penyukatanBPM();
      sukatBPMml();
      sukatBPM();
    }
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka_popup.jsp").addParameter("popup", "true");
  }

  public Resolution penyukatanBPM() {
    kodDaerah = (String) getContext().getRequest().getParameter("daerah");
    reportName = getContext().getRequest().getParameter("namaReport");
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    LOG.info("------------------- " + kodDaerah);
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
    senaraiBPM = q.list();
    LOG.info("senaraiBPM.size() : " + senaraiBPM.size());
    report_p_kod_daerah = kodDaerah;
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka_popup.jsp").addParameter("popup", "true");
  }

//  filter Urusan
  public Resolution penyukatanUrusan() {
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    String kodPerserahan = getContext().getRequest().getParameter("kodPerserahan");
    LOG.debug("---kod Serah --> " + kodPerserahan);
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    if (kodPerserahan == null || kodPerserahan.equals("")) {
      sql = "select ks from KodUrusan ks ";
      q = s.createQuery(sql);
    } else {
      sql = "select ks from KodUrusan ks where ks.kodPerserahan.kod = :kod ";
      q = s.createQuery(sql);
      q.setString("kod", kodPerserahan);
    }
    senaraiUrusan = q.list();
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka_popup.jsp").addParameter("popup", "true");
  }
//  filter urusan END

  public Resolution sukatBPMml() {
    reportName = getContext().getRequest().getParameter("namaReport");
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    kodDaerah = (String) peng.getKodCawangan().getDaerah().getKod();
    LOG.info("------------------- " + kodDaerah);
    String sql = null;
    int kod = 04;
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

    senaraiBPM04 = q.list();
    LOG.info("senaraiBPM04.size() : " + senaraiBPM04.size());
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka_popup.jsp").addParameter("popup", "true");
  }

  public Resolution sukatBPM() {
    kodDaerah = (String) getContext().getRequest().getParameter("daerah");
    reportName = getContext().getRequest().getParameter("namaReport");
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    LOG.info("------------------- " + kodDaerah);
    String sql = null;
    Session s = sessionProvider.get();
    Query q = null;
    if (kodDaerah == null || kodDaerah.equals("")) {
      sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = 05";
      q = s.createQuery(sql);
    }

    senaraiBPM05 = q.list();
    LOG.info("senaraiBPM05.size() : " + getSenaraiBPM05().size());
    return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utiliti_report_melaka_popup.jsp").addParameter("popup", "true");
  }

  public void getDaysToComplete(String workflowId, String stageId, String txncode) {
    int bilHari = 0;
    for (Document document : xmlList) {
      Elements parent =
              document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
              + stageId + "']/txncode[@id='" + txncode + "']"));
      while (parent.hasMoreElements()) {
        Element child = parent.next();
        String days = child.getAttributeValue("daysToComplete");
        if (StringUtils.isNotBlank(days)) {
          bilHari = Integer.parseInt(days);
        }
      }
    }

    report_p_bil_hari = (String.valueOf(bilHari));
    LOG.info("-------------------" + report_p_bil_hari);

  }


  public List<Pengguna> getListPguna() {
    return listPguna;
  }

  public void setListPguna(List<Pengguna> listPguna) {
    this.listPguna = listPguna;
  }

  public String getReportName() {
    return reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public List<String> getListYear() {
    //calendar for year
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    listYear.add(String.valueOf(year));
    for (int i = 0; i < 30; i++) {
      year--;
      listYear.add(String.valueOf(year));
    }
    return listYear;
  }

  public Pengguna getPeng() {
    return peng;
  }

  public void setPeng(Pengguna peng) {
    this.peng = peng;
  }

  public String getKodNegeri() {
    return kodNegeri;
  }

  public void setKodNegeri(String kodNegeri) {
    this.kodNegeri = kodNegeri;
  }

  public String getReport() {
    return report;
  }

  public void setReport(String report) {
    this.report = report;
  }

  public String getKodCaw() {
    return kodCaw;
  }

  public void setKodCaw(String kodCaw) {
    this.kodCaw = kodCaw;
  }

  public String getDaerahHasil() {
    return daerahHasil;
  }

  public void setDaerahHasil(String daerahHasil) {
    this.daerahHasil = daerahHasil;
  }

  public String getKodDaerah() {
    return kodDaerah;
  }

  public void setKodDaerah(String kodDaerah) {
    this.kodDaerah = kodDaerah;
  }

  public static String getStaticKodDaerah() {
    return staticKodDaerah;
  }

  public static void setStaticKodDaerah(String staticKodDaerah) {
    UtilitiReportActionBean.staticKodDaerah = staticKodDaerah;
  }

  public List<KodBandarPekanMukim> getSenaraiBPM() {
    return senaraiBPM;
  }

  public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
    this.senaraiBPM = senaraiBPM;
  }

  public String getDaerah() {
    return daerah;
  }

  public void setDaerah(String daerah) {
    this.daerah = daerah;
  }

  public List<KodBandarPekanMukim> getSenaraiBPM04() {
    return senaraiBPM04;
  }

  public void setSenaraiBPM04(List<KodBandarPekanMukim> senaraiBPM04) {
    this.senaraiBPM04 = senaraiBPM04;
  }

  public List<KodBandarPekanMukim> getSenaraiBPM05() {
    return senaraiBPM05;
  }

  public void setSenaraiBPM05(List<KodBandarPekanMukim> senaraiBPM05) {
    this.senaraiBPM05 = senaraiBPM05;
  }

  public String getReport_p_kod_daerah() {
    return report_p_kod_daerah;
  }

  public void setReport_p_kod_daerah(String report_p_kod_daerah) {
    this.report_p_kod_daerah = report_p_kod_daerah;
  }

  public String getReport_p_bil_hari() {
    return report_p_bil_hari;
  }
  
  public void setReport_p_bil_hari(String report_p_bil_hari) {
    this.report_p_bil_hari = report_p_bil_hari;
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
  
  public List<KodUrusan> getSenaraiUrusan() {
    return senaraiUrusan;
  }

  public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
    this.senaraiUrusan = senaraiUrusan;
  }

    public String[] getLaporanPPCB() {
        return laporanPPCB;
    }

    public void setLaporanPPCB(String[] laporanPPCB) {
        this.laporanPPCB = laporanPPCB;
    }

    public String[] getLaporanPPCBReport() {
        return laporanPPCBReport;
    }

    public void setLaporanPPCBReport(String[] laporanPPCBReport) {
        this.laporanPPCBReport = laporanPPCBReport;
    }

    public String[] getLaporanTsSama() {
        return laporanTsSama;
    }

    public void setLaporanTsSama(String[] laporanTsSama) {
        this.laporanTsSama = laporanTsSama;
    }

    public String[] getLaporanTsSamaReport() {
        return laporanTsSamaReport;
    }

    public void setLaporanTsSamaReport(String[] laporanTsSamaReport) {
        this.laporanTsSamaReport = laporanTsSamaReport;
    }

    public String[] getLaporanTsLainKatg() {
        return laporanTsLainKatg;
    }

    public void setLaporanTsLainKatg(String[] laporanTsLainKatg) {
        this.laporanTsLainKatg = laporanTsLainKatg;
    }

    public String[] getLaporanTsLainKatgReport() {
        return laporanTsLainKatgReport;
    }

    public void setLaporanTsLainKatgReport(String[] laporanTsLainKatgReport) {
        this.laporanTsLainKatgReport = laporanTsLainKatgReport;
    }

    public String[] getLaporanTsSyaratNyata() {
        return laporanTsSyaratNyata;
    }

    public void setLaporanTsSyaratNyata(String[] laporanTsSyaratNyata) {
        this.laporanTsSyaratNyata = laporanTsSyaratNyata;
    }

    public String[] getLaporanTsSyaratNyataReport() {
        return laporanTsSyaratNyataReport;
    }

    public void setLaporanTsSyaratNyataReport(String[] laporanTsSyaratNyataReport) {
        this.laporanTsSyaratNyataReport = laporanTsSyaratNyataReport;
    }

    public String[] getLaporanPPCS() {
        return laporanPPCS;
    }

    public void setLaporanPPCS(String[] laporanPPCS) {
        this.laporanPPCS = laporanPPCS;
    }

    public String[] getLaporanPPCSReport() {
        return laporanPPCSReport;
    }

    public void setLaporanPPCSReport(String[] laporanPPCSReport) {
        this.laporanPPCSReport = laporanPPCSReport;
    }

    public String[] getLaporanPYTN() {
        return laporanPYTN;
    }

    public void setLaporanPYTN(String[] laporanPYTN) {
        this.laporanPYTN = laporanPYTN;
    }

    public String[] getLaporanPYTNReport() {
        return laporanPYTNReport;
    }

    public void setLaporanPYTNReport(String[] laporanPYTNReport) {
        this.laporanPYTNReport = laporanPYTNReport;
    }

    public String[] getLaporanPSMT() {
        return laporanPSMT;
    }

    public void setLaporanPSMT(String[] laporanPSMT) {
        this.laporanPSMT = laporanPSMT;
    }

    public String[] getLaporanPSMTReport() {
        return laporanPSMTReport;
    }

    public void setLaporanPSMTReport(String[] laporanPSMTReport) {
        this.laporanPSMTReport = laporanPSMTReport;
    }

    public String[] getLaporanSBMS() {
        return laporanSBMS;
    }

    public void setLaporanSBMS(String[] laporanSBMS) {
        this.laporanSBMS = laporanSBMS;
    }

    public String[] getLaporanSBMSReport() {
        return laporanSBMSReport;
    }

    public void setLaporanSBMSReport(String[] laporanSBMSReport) {
        this.laporanSBMSReport = laporanSBMSReport;
    }

    public String[] getLaporanSerahBalikTanah() {
        return laporanSerahBalikTanah;
    }

    public void setLaporanSerahBalikTanah(String[] laporanSerahBalikTanah) {
        this.laporanSerahBalikTanah = laporanSerahBalikTanah;
    }

    public String[] getLaporanSerahBalikTanahReport() {
        return laporanSerahBalikTanahReport;
    }

    public void setLaporanSerahBalikTanahReport(String[] laporanSerahBalikTanahReport) {
        this.laporanSerahBalikTanahReport = laporanSerahBalikTanahReport;
    }

    public String[] getLaporanTSPSS() {
        return laporanTSPSS;
    }

    public void setLaporanTSPSS(String[] laporanTSPSS) {
        this.laporanTSPSS = laporanTSPSS;
    }

    public String[] getLaporanTSPSSReport() {
        return laporanTSPSSReport;
    }

    public void setLaporanTSPSSReport(String[] laporanTSPSSReport) {
        this.laporanTSPSSReport = laporanTSPSSReport;
    }

    public String[] getLaporan6Urusan() {
        return laporan6Urusan;
    }

    public void setLaporan6Urusan(String[] laporan6Urusan) {
        this.laporan6Urusan = laporan6Urusan;
    }

    public String[] getLaporan6UrusanReport() {
        return laporan6UrusanReport;
    }

    public void setLaporan6UrusanReport(String[] laporan6UrusanReport) {
        this.laporan6UrusanReport = laporan6UrusanReport;
    }    

    public String[] getLaporanStatusPermohonan() {
        return laporanStatusPermohonan;
    }

    public void setLaporanStatusPermohonan(String[] laporanStatusPermohonan) {
        this.laporanStatusPermohonan = laporanStatusPermohonan;
    }

    public String[] getLaporanStatusPermohonanReport() {
        return laporanStatusPermohonanReport;
    }

    public void setLaporanStatusPermohonanReport(String[] laporanStatusPermohonanReport) {
        this.laporanStatusPermohonanReport = laporanStatusPermohonanReport;
    }

    public String[] getLaporanKemajuanRayuan() {
        return laporanKemajuanRayuan;
    }

    public void setLaporanKemajuanRayuan(String[] laporanKemajuanRayuan) {
        this.laporanKemajuanRayuan = laporanKemajuanRayuan;
    }

    public String[] getLaporanKemajuanRayuanReport() {
        return laporanKemajuanRayuanReport;
    }

    public void setLaporanKemajuanRayuanReport(String[] laporanKemajuanRayuanReport) {
        this.laporanKemajuanRayuanReport = laporanKemajuanRayuanReport;
    }

    public String[] getLaporanPBSK() {
        return laporanPBSK;
    }

    public void setLaporanPBSK(String[] laporanPBSK) {
        this.laporanPBSK = laporanPBSK;
    }

    public String[] getLaporanPBSKReport() {
        return laporanPBSKReport;
    }

    public void setLaporanPBSKReport(String[] laporanPBSKReport) {
        this.laporanPBSKReport = laporanPBSKReport;
    }

    public String[] getLaporan5a() {
        return laporan5a;
    }

    public void setLaporan5a(String[] laporan5a) {
        this.laporan5a = laporan5a;
    }

    public String[] getLaporan5aReport() {
        return laporan5aReport;
    }

    public void setLaporan5aReport(String[] laporan5aReport) {
        this.laporan5aReport = laporan5aReport;
    }

    public String[] getLaporan7g() {
        return laporan7g;
    }

    public void setLaporan7g(String[] laporan7g) {
        this.laporan7g = laporan7g;
    }

    public String[] getLaporan7gReport() {
        return laporan7gReport;
    }

    public void setLaporan7gReport(String[] laporan7gReport) {
        this.laporan7gReport = laporan7gReport;
    }

    public String[] getLaporan17() {
        return laporan17;
    }

    public void setLaporan17(String[] laporan17) {
        this.laporan17 = laporan17;
    }

    public String[] getLaporan17Report() {
        return laporan17Report;
    }

    public void setLaporan17Report(String[] laporan17Report) {
        this.laporan17Report = laporan17Report;
    }

    public String[] getLaporan18() {
        return laporan18;
    }

    public void setLaporan18(String[] laporan18) {
        this.laporan18 = laporan18;
    }

    public String[] getLaporan18Report() {
        return laporan18Report;
    }

    public void setLaporan18Report(String[] laporan18Report) {
        this.laporan18Report = laporan18Report;
    }

    public String[] getLaporan19() {
        return laporan19;
    }

    public void setLaporan19(String[] laporan19) {
        this.laporan19 = laporan19;
    }

    public String[] getLaporan19Report() {
        return laporan19Report;
    }

    public void setLaporan19Report(String[] laporan19Report) {
        this.laporan19Report = laporan19Report;
    }
}

