/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

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
 * @author ida 16/08/13
 */
@UrlBinding("/strata/LaporanUrusanStrata")
public class LaporanUrusanStrata extends AbleActionBean {

  private List<Document> xmlList;
  private static final Logger LOG = Logger.getLogger(LaporanUrusanStrata.class);
  @Inject
  PenggunaDAO penggunaDao;
  @Inject
  private etanah.Configuration conf;
  private String[] senaraiMaklumatHakmilik;
  private String[] laporanPrestasi;
  private String[] senaraiKeluasanTanah;
  private String[] senaraiPemilikan;
  private String[] laporanPermohonan;
  private String[] statistikKeluasanTanah;
  private String[] laporanKemajuan;
  private String[] laporanHakmilik;
  private String[] laporanHakmilikPTD;
  private String[] laporanTambahan;
  private String[] laporanBaru;
  private String[] laporanPrestasiRN;
  private String[] senaraiMaklumatHakmilikRN;
  private String[] senaraiKeluasanTanahRN;
  private String[] senaraiPemilikanRN;
  private String[] statistikKeluasanTanahRN;
  private String[] laporanKemajuanRN;
  private String[] laporanHakmilikRN;
  private String[] laporanHakmilikRNPTD;
  private String[] laporanTambahanRN;
  private String[] laporanBaruRN;
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

  public List<KodUrusan> getSenaraiUrusan() {
    return senaraiUrusan;
  }

  public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
    this.senaraiUrusan = senaraiUrusan;
  }
  private String daerah;
  private String report_p_kod_daerah;
  @Inject
  KodCawanganDAO kodCawanganDAO;
  private static String staticKodDaerah;
  @Inject
  PembetulanService pService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  private String kodNeg;

  @DefaultHandler
  public Resolution showForm() {
    LOG.info("showForm");
    if ("04".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "melaka";
    }
    if ("05".equals(conf.getProperty("kodNegeri"))) {
      kodNegeri = "n9";
    }
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    showReports();
    return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/laporan_urusan_strata.jsp");
  }

  private void showReports() {
    LOG.info("showReport:start");

    //ida
    if ("05".equals(conf.getProperty("kodNegeri"))) {
      laporanPrestasi = new String[]{
        "Laporan Keseluruhan Permohonan Hakmilik Strata di Pejabat Pendaftar Hakmilik",
        "Laporan Keseluruhan Permohonan Hakmilik Strata",};

      laporanPermohonan = new String[]{
        "Hakmilik Mengikut Bilangan Pemilik",
        "Hakmilik Bagi Pemilikan Agensi Kerajaan/Badan Berkanun"};

      laporanKemajuan = new String[]{
        "Laporan Kemajuan Bulanan Unit Pendaftaran",
        "Laporan Kemajuan Hakmilik Baru/Sambungan",
        "Laporan Prestasi Bulanan Kerani",
        "Laporan Prestasi Bulanan Pendaftar",
        "Laporan Prestasi B/SC/N/NB/MH",
        "Laporan Prestasi Harian",
        "Laporan Prestasi Bulanan",
        "Laporan Perserahan Urusniaga",
        "Laporan Perserahan Bukan Urusniaga",
        "Laporan Tertunggak Ikut Jenis Perserahan",
        "Laporan Tertunggak Ikut Status Perserahan",
        "Laporan Tunggakan Kerja (Urusniaga/Bukan Urusniaga)",
        "Senarai Perserahan KIV",
        "Laporan Perserahan Hakmilik Strata"};

      laporanHakmilik = new String[]{
        "Laporan Pengambilan Balik Tanah",
        "Laporan Kaveat Yang Sudah Luput",
        "Laporan Pertukaran Syarat Tanah",
        "Laporan Senarai Tanah Dimakan Air",
        "Laporan Pemilikan Tanah Warganegara Asing",
        "Hakmilik Pejabat Tanah Dan Galian",
        "Hakmilik Mengikut Keluasan Tanah",
        "Hakmilik Baru/Sambungan Mengikut Daerah/Mukim",
        "Hakmilik Sementara Kepada Hakmilik Kekal",
        "Hakmilik Mengikut Kepentingan",
        "Hakmilik Tamat Tempoh Pajakan",
        "Hakmilik Lupus/Batal",
        "Hakmilik Mengikut Bilangan Kaveat Yang Dinyatakan",
        "Hakmilik Tanah Dipindahmilik/Dilelong",
        "Hakmilik Kategori Pertanian Estate Holder (>100 Ekar)",
        "Hakmilik Mengikut Jenis Hakmilik Berdasarkan Tempoh",
        "Hakmilik Mengikut Kategori Tanah Berdasarkan Tempoh"};

      laporanHakmilikPTD = new String[]{
        "Laporan Pengambilan Balik Tanah",
        "Laporan Kaveat Yang Sudah Luput",
        "Laporan Pertukaran Syarat Tanah",
        "Laporan Senarai Tanah Dimakan Air",
        "Laporan Pemilikan Tanah Warganegara Asing",
        //                "Hakmilik Pejabat Tanah Dan Galian",
        "Hakmilik Mengikut Keluasan Tanah",
        "Hakmilik Baru/Sambungan Mengikut Daerah/Mukim",
        "Hakmilik Sementara Kepada Hakmilik Kekal",
        "Hakmilik Mengikut Kepentingan",
        "Hakmilik Tamat Tempoh Pajakan",
        "Hakmilik Lupus/Batal",
        "Hakmilik Mengikut Bilangan Kaveat Yang Dinyatakan",
        "Hakmilik Tanah Dipindahmilik/Dilelong",
        "Hakmilik Kategori Pertanian Estate Holder (>100 Ekar)",
        "Hakmilik Mengikut Jenis Hakmilik Berdasarkan Tempoh",
        "Hakmilik Mengikut Kategori Tanah Berdasarkan Tempoh"};

      laporanTambahan = new String[]{
        "Laporan Hakmilik Mengikut Urusan Dan Jenis Kegunaan Tanah",
        "Laporan Hakmilik Yang Terlibat Dengan Pengambilan Tanah",
        "Laporan Hakmilik Notis 6A Dan Notis 8A",
        "Laporan Hakmilik Sementara",
        "Laporan Hakmilik Luput/Akan Luput Tempoh Pajakan",
        "Statistik Hakmilik Sementara Yang Telah Didaftarkan Mengikut Tahun",
        "Jumlah Hakmilik Sementara Berstatus Daftar Mengikut Tahun",
        "Jumlah Hakmilik QT FT Yang Telah Didaftarkan Mengikut Tahun",
        "Buku Perserahan",
        "Laporan Kutipan Dokumen"};
//                ,"Laporan Pengeluaran Kertas Hakmilik"};// tambah ni jgak 

      // Report Name
      laporanPrestasiRN = new String[]{
        "STRLPR1_NS.rdf"};
      
      senaraiKeluasanTanahRN = new String[]{
        "STRLPR2_NS.rdf"};

      senaraiPemilikanRN = new String[]{
        "STRLPR3_NS.rdf",
        "STRLPR4_NS.rdf"};

      laporanKemajuanRN = new String[]{
        "ETMIS47A_1NS.rdf",
        "ETMIS54_1NS.rdf",
        "ETMIS43_1NS.rdf",
        "ETMIS44_1NS.rdf",
        "ETMIS51_1NS.rdf",
        "ETMIS65_1NS.rdf",
        "ETMIS65A_1NS.rdf",
        "ETMIS50_1NS.rdf",
        "ETMIS49_1NS.rdf",
        "ETMIS52_1NS.rdf",
        "ETMIS53_1NS.rdf",
        "ETMIS77_1NS.rdf",
        "ETMIS37_1NS.rdf",
        "ETMIS48_1NS.rdf"};

      laporanHakmilikRN = new String[]{
        "ETMIS12_1NS.rdf",
        "ETMIS10_1NS.rdf",
        "ETMIS13_1NS.rdf",
        "ETMIS21_1NS.rdf",
        "ETMIS55_1NS.rdf",
        "ETMIS76_1NS.rdf",
        "ETMIS78_1NS.rdf",
        "ETMIS26A_1NS.rdf",
        "ETMIS06_1NS.rdf",
        "ETMIS19_1NS.rdf",
        "ETMIS20_1NS.rdf",
        "ETMIS08_1NS.rdf",
        "ETMIS09_1NS.rdf",
        "ETMIS07_1NS.rdf",
        "ETMIS17_1NS.rdf",
        "ETMIS57_1NS.rdf",
        "ETMIS59_1NS.rdf"};

      laporanHakmilikRNPTD = new String[]{
        "ETMIS12_1NS.rdf",
        "ETMIS10_1NS.rdf",
        "ETMIS13_1NS.rdf",
        "ETMIS21_1NS.rdf",
        "ETMIS55_1NS.rdf",
        //                "ETMIS76_1NS.rdf",
        "ETMIS78_1NS.rdf",
        "ETMIS26A_1NS.rdf",
        "ETMIS06_1NS.rdf",
        "ETMIS19_1NS.rdf",
        "ETMIS20_1NS.rdf",
        "ETMIS08_1NS.rdf",
        "ETMIS09_1NS.rdf",
        "ETMIS07_1NS.rdf",
        "ETMIS17_1NS.rdf",
        "ETMIS57_1NS.rdf",
        "ETMIS59_1NS.rdf"};

      laporanTambahanRN = new String[]{
        "ETMIS_ADD_01.rdf",
        "ETMIS_ADD_02.rdf",
        "ETMIS_ADD_03.rdf",
        "ETMIS_ADD_04.rdf",
        "ETMIS_ADD_05.rdf",
        "ETMIS_ADD_06.rdf",
        "ETMIS_ADD_07.rdf",
        "ETMIS01_1.rdf",
        "ETMIS_ADD_08.rdf",
        "ETMIS_ADD_11.rdf"};    // Laporan Kutipan Dokumen
//                ,"ETMIS_ADD_09.rdf"}; // tambah ni laporan pengeluaran kertas hakmilik
    }
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
//            getDaysToComplete();
//            sukat();
//            doFilterDaerahBPM();
    }
    return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/laporan_parameter.jsp").addParameter("popup", "true");
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
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup", "true");
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
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup", "true");
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
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup", "true");
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
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup", "true");
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

//    public Resolution sukat(){
//        String kod_hakmilik = (String) getContext().getRequest().getParameter("hakmilik");
//    }
    /*
   * public Resolution doFilterDaerahBPM() { peng = (Pengguna)
   * getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
   * String cawKod = getContext().getRequest().getParameter("kodCawangan"); if
   * (cawKod != null) { kodCaw = cawKod; } LOG.info("(doFilterDaerahBPM)
   * kodCaw :" + kodCaw);
   *
   * if (kodCaw == null) { kodCaw = staticKodCaw; }
   *
   * KodCawangan kc = kodCawanganDAO.findById(kodCaw); if (kc.getDaerah() !=
   * null) { kodDaerah = null; daerahHasil = kc.getDaerah().getKod(); }
   * kodDaerah = daerahHasil; doFilterBPM(); return new
   * ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup",
   * "true"); }
   */
  public List<Pengguna> getListPguna() {
    return listPguna;
  }

  public void setListPguna(List<Pengguna> listPguna) {
    this.listPguna = listPguna;
  }

  public String[] getLaporanHakmilik() {
    return laporanHakmilik;
  }

  public void setLaporanHakmilik(String[] laporanHakmilik) {
    this.laporanHakmilik = laporanHakmilik;
  }

  public String[] getLaporanHakmilikPTD() {
    return laporanHakmilikPTD;
  }

  public void setLaporanHakmilikPTD(String[] laporanHakmilikPTD) {
    this.laporanHakmilikPTD = laporanHakmilikPTD;
  }

  public String[] getLaporanHakmilikRN() {
    return laporanHakmilikRN;
  }

  public void setLaporanHakmilikRN(String[] laporanHakmilikRN) {
    this.laporanHakmilikRN = laporanHakmilikRN;
  }

  public String[] getLaporanHakmilikRNPTD() {
    return laporanHakmilikRNPTD;
  }

  public void setLaporanHakmilikRNPTD(String[] laporanHakmilikRNPTD) {
    this.laporanHakmilikRNPTD = laporanHakmilikRNPTD;
  }

  public String[] getLaporanKemajuan() {
    return laporanKemajuan;
  }

  public void setLaporanKemajuan(String[] laporanKemajuan) {
    this.laporanKemajuan = laporanKemajuan;
  }

  public String[] getLaporanKemajuanRN() {
    return laporanKemajuanRN;
  }

  public void setLaporanKemajuanRN(String[] laporanKemajuanRN) {
    this.laporanKemajuanRN = laporanKemajuanRN;
  }

  public String[] getLaporanPrestasi() {
    return laporanPrestasi;
  }

  public void setLaporanPrestasi(String[] laporanPrestasi) {
    this.laporanPrestasi = laporanPrestasi;
  }

  public String[] getSenaraiKeluasanTanahRN() {
    return senaraiKeluasanTanahRN;
  }

  public void setSenaraiKeluasanTanahRN(String[] senaraiKeluasanTanahRN) {
    this.senaraiKeluasanTanahRN = senaraiKeluasanTanahRN;
  }

  public String[] getSenaraiMaklumatHakmilik() {
    return senaraiMaklumatHakmilik;
  }

  public void setSenaraiMaklumatHakmilik(String[] senaraiMaklumatHakmilik) {
    this.senaraiMaklumatHakmilik = senaraiMaklumatHakmilik;
  }

  public String[] getSenaraiMaklumatHakmilikRN() {
    return senaraiMaklumatHakmilikRN;
  }

  public void setSenaraiMaklumatHakmilikRN(String[] senaraiMaklumatHakmilikRN) {
    this.senaraiMaklumatHakmilikRN = senaraiMaklumatHakmilikRN;
  }

    public String[] getSenaraiKeluasanTanah() {
        return senaraiKeluasanTanah;
    }

    public void setSenaraiKeluasanTanah(String[] senaraiKeluasanTanah) {
        this.senaraiKeluasanTanah = senaraiKeluasanTanah;
    }

    public String[] getLaporanPermohonan() {
        return laporanPermohonan;
    }

    public void setLaporanPermohonan(String[] laporanPermohonan) {
        this.laporanPermohonan = laporanPermohonan;
    }

  public String[] getSenaraiPemilikan() {
    return senaraiPemilikan;
  }

  public void setSenaraiPemilikan(String[] senaraiPemilikan) {
    this.senaraiPemilikan = senaraiPemilikan;
  }

  public String[] getSenaraiPemilikanRN() {
    return senaraiPemilikanRN;
  }

  public void setSenaraiPemilikanRN(String[] senaraiPemilikanRN) {
    this.senaraiPemilikanRN = senaraiPemilikanRN;
  }

  public String[] getStatistikKeluasanTanah() {
    return statistikKeluasanTanah;
  }

  public void setStatistikKeluasanTanah(String[] statistikKeluasanTanah) {
    this.statistikKeluasanTanah = statistikKeluasanTanah;
  }

  public String[] getStatistikKeluasanTanahRN() {
    return statistikKeluasanTanahRN;
  }

  public void setStatistikKeluasanTanahRN(String[] statistikKeluasanTanahRN) {
    this.statistikKeluasanTanahRN = statistikKeluasanTanahRN;
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

  public String[] getLaporanTambahan() {
    return laporanTambahan;
  }

  public void setLaporanTambahan(String[] laporanTambahan) {
    this.laporanTambahan = laporanTambahan;
  }

  public String[] getLaporanTambahanRN() {
    return laporanTambahanRN;
  }

  public void setLaporanTambahanRN(String[] laporanTambahanRN) {
    this.laporanTambahanRN = laporanTambahanRN;
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

  public String[] getLaporanBaru() {
    return laporanBaru;
  }

  public void setLaporanBaru(String[] laporanBaru) {
    this.laporanBaru = laporanBaru;
  }

  public String[] getLaporanBaruRN() {
    return laporanBaruRN;
  }

  public void setLaporanBaruRN(String[] laporanBaruRN) {
    this.laporanBaruRN = laporanBaruRN;
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
    LaporanUrusanStrata.staticKodDaerah = staticKodDaerah;
  }

  /**
   * @return the senaraiBPM
   */
  public List<KodBandarPekanMukim> getSenaraiBPM() {
    return senaraiBPM;
  }

  /**
   * @param senaraiBPM the senaraiBPM to set
   */
  public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
    this.senaraiBPM = senaraiBPM;
  }

  /**
   * @return the daerah
   */
  public String getDaerah() {
    return daerah;
  }

  /**
   * @param daerah the daerah to set
   */
  public void setDaerah(String daerah) {
    this.daerah = daerah;
  }

  /**
   * @return the senaraiBPM04
   */
  public List<KodBandarPekanMukim> getSenaraiBPM04() {
    return senaraiBPM04;
  }

  /**
   * @param senaraiBPM04 the senaraiBPM04 to set
   */
  public void setSenaraiBPM04(List<KodBandarPekanMukim> senaraiBPM04) {
    this.senaraiBPM04 = senaraiBPM04;
  }

  /**
   * @return the senaraiBPM05
   */
  public List<KodBandarPekanMukim> getSenaraiBPM05() {
    return senaraiBPM05;
  }

  /**
   * @param senaraiBPM05 the senaraiBPM05 to set
   */
  public void setSenaraiBPM05(List<KodBandarPekanMukim> senaraiBPM05) {
    this.senaraiBPM05 = senaraiBPM05;
  }

  /**
   * @return the report_p_kod_daerah
   */
  public String getReport_p_kod_daerah() {
    return report_p_kod_daerah;
  }

  /**
   * @param report_p_kod_daerah the report_p_kod_daerah to set
   */
  public void setReport_p_kod_daerah(String report_p_kod_daerah) {
    this.report_p_kod_daerah = report_p_kod_daerah;
  }

  /**
   * @return the report_p_bil_hari
   */
  public String getReport_p_bil_hari() {
    return report_p_bil_hari;
  }

  /**
   * @param report_p_bil_hari the report_p_bil_hari to set
   */
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
}
