/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import electric.xml.Document;
import electric.xml.Element;
import electric.xml.Elements;
import electric.xml.XPath;
import etanah.dao.HakmilikLamaDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.HakmilikLama;
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
import etanah.model.KodDaerah;
import etanah.model.KodUrusan;
import etanah.service.common.PgunaService;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/laporanMaklumatUrusanMis")
public class LaporanMaklumatUrusanMis extends AbleActionBean {

  private static final Logger LOG = Logger.getLogger(LaporanMaklumatUrusanMis.class);
  @Inject
  PenggunaDAO penggunaDao;
  @Inject
  HakmilikLamaDAO hakmilikLamaDAO;
  @Inject
  KodCawanganDAO kodCawanganDAO;
  @Inject
  PembetulanService pService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  private String[] senaraiMaklumatHakmilik;
  private String[] senaraiKeluasanTanah;
  private String[] senaraiPemilikan;
  private String[] statistikKeluasanTanah;
  private String[] laporanKemajuan;
  private String[] laporanHakmilik;
  private String[] laporanHakmilikPTD;
  private String[] laporanTambahan;
  private String[] laporanBaru;
  private String[] senaraiConvertHakmilik;
  private String[] senaraiConvertHakmilikRN;
  private String[] laporanKutipanData;
  private String[] laporanKutipanDataRN;
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
  private String kodNegeri;
  private String kodCaw;
  private String kodDaerah;
  private String daerahHasil;
  private String report_p_bil_hari;
  private String daerah;
  private String report_p_kod_daerah;
  private String kodNeg;
  private static String staticKodCaw;
  private static String staticKodDaerah;
  private Pengguna peng;
  private List<Pengguna> senaraiPendaftar;
  private List<String> listYear = new ArrayList<String>();
  private List<Pengguna> listPguna;
  private List<KodDaerah> listKodDaerah;
  private Date calendar;

  public List<KodDaerah> getListKodDaerah() {
    return listKodDaerah;
  }

  public void setListKodDaerah(List<KodDaerah> listKodDaerah) {
    this.listKodDaerah = listKodDaerah;
  }
  private List<KodBandarPekanMukim> senaraiBPM;
  private List<KodBandarPekanMukim> senaraiBPM04;
  private List<KodBandarPekanMukim> senaraiBPM05;
  private List<KodUrusan> senaraiUrusan;
  private List<Document> xmlList;

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
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_mis.jsp");
  }

  private void showReports() {
    LOG.info("showReport:start");

    if ("04".equals(conf.getProperty("kodNegeri"))) {
      senaraiMaklumatHakmilik = new String[]{"Maklumat Pemilikan Melalui No. Hakmilik",
        "Maklumat Pemilikan Melalui No. Kp/Passport/No. Syarikat/Nama"};

      senaraiKeluasanTanah = new String[]{"Maklumat Keluasan Mengikut Kategori Tanah",
        "Maklumat Keluasan Mengikut Status Bangsa",
        "Maklumat Keluasan Mengikut Rezab Melayu",
        "Maklumat Keluasan Mengikut Jenis Hakmilik"};

      senaraiPemilikan = new String[]{"Laporan Pemilikan Tanah Dari Pemilikan Melayu Kepada Cina",
        "Laporan Pemilikan Tanah Dari Pemilikan Cina Kepada Melayu",
        "Laporan Pemilikan Tanah Mengikut IC Dan Nama",
        "Laporan Hakmilik Mengikut Jenis Pemilikan Yang Diperolehi",
        "Laporan Pemilikan Tanah Yang Melebihi [Bil] Pemilik",
        "Senarai Tanah Milik Kerajaan Persekutuan",
        "Laporan Pemilikan Tanah Bagi Kerajaan Negeri"};

      statistikKeluasanTanah = new String[]{"Statistik Luas Mengikut Kategori Tanah",
        "Statistik Keluasan Mengikut Jenis Hakmilik",
        "Statistik Pemilikan Tanah Mengikut Bangsa"};

      laporanKemajuan = new String[]{"Laporan Kemajuan Bulanan Unit Pendaftaran (Uku07)",
        "Laporan Kemajuan Pendaftaran Hakmilik",
        "Laporan Kemajuan Pendaftaran Dan Pengeluaran Hakmilik",
        "Laporan Kemajuan Urusniaga / Bukan Urusniaga",
        "Laporan Kemajuan Harian Kerani Pendaftaran ",
        "Laporan Kemajuan Bulanan Kerani Kaunter",
        "Laporan Kemajuan Bulanan Kerani Pendaftaran",
        "Laporan Kemajuan Bulanan Pendaftar",
        "Laporan Prestasi Pendaftaran Urusniaga, Bukan Urusniaga, Nota Dan Pendaftaran Hakmilik",
        "Statistik Kemajuan Pendaftaran Nota",
        "Rekod Perserahan Harian",
        "Rekod Perserahan Bulanan",
        "Laporan Perserahan Tertunggak Status [Status] Bagi Urusniaga, Bukan Urusniaga, Nota Dan Pendaftaran Hakmilik Mengikut Tarikh",
        "Senarai Perserahan Urusniaga Yang Digantung Dalam Peringkat Kemasukan",
        "Senarai Perserahan Yang Telah Digantung ",
        "Senarai Perserahan Urusniaga Yang Disyor Tolak Dalam Peringkat Kemasukan Mengikut Tarikh",
        "Senarai Perserahan Urusniaga Digantung",
        "Perserahan Yang Telah Ditolak Setelah 14 Hari Digantung",
        "Laporan Senarai Surat Amanah / Surat Kebenaran / Suratkuasa Wakil",
        "Laporan Anggaran Cukai Tanah (pecahan QT & FT yang baru dan aktif)",
        "Laporan Carian",
        "Senarai Perserahan Akan Luput Tempoh ",
        "Laporan Urusniaga & Bukan Urusniaga yang belum Daftar",
        "Buku Perserahan",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) KESELURUHAN",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) STRATA",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) LANDED",
        "Laporan Statistik Tempoh Perserahan SuratCara (SC)",
        "Laporan Statistik Tempoh Perserahan Borang (B)",
        "Laporan Kutipan Dokumen",
        "Laporan Pengeluaran Carian Tanpa Bayaran",
        "Laporan Sejarah Cetakan Semula Dokumen Hakmilik",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) dan DUTI SETEM KESELURUHAN",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) dan DUTI SETEM STRATA",
        "Laporan Statistik Tempoh Perserahan PindahMilik Tanah (PMT) dan DUTI SETEM LANDED"};

      laporanHakmilik = new String[]{"Laporan Berdasarkan Unit Keluasan",
        "Laporan Keluasan Tanah Melebihi [Keluasan Yang Dimasukkan] ",
        "Laporan Senarai Hakmilik Mengikut Kepentingan",
        "Laporan Jenis Hakmilik Mengikut Mukim",
        "Senarai Hakmilik Baru/Sambung Mengikut Mukim",
        "Senarai Hakmilik Baru/Sambung Mengikut Daerah/Mukim",
        "Laporan Hakmilik Sementara Ditukar Kepada Hakmilik Kekal",
        "Laporan Hakmilik Yang Dilupuskan / Dibatalkan (Keseluruhan)",
        "Laporan Hakmilik Yang Dilupuskan / Dibatalkan (Mengikut Daerah)",
        "Laporan Hakmilik Yang Mempunyai [Bil] Kaveat",
        "Laporan Kaveat Yang Sudah Luput",
        "Laporan Pengambilan Balik Tanah",
        "Laporan Hakmilik Tanah  Yang Dipindahmilik/Dilelong",
        //"Laporan Hakmilik Tanah Akibat Pengambilan",
        "Statistik Jumlah Hakmilik Akibat Pengambilan Tanah",
        "Laporan Pertukaran Syarat / Sekatan Tanah Mengikut Kategori Penggunaan Tanah",
        "Laporan Hakmilik Yang Tamat Tempoh Pajakan",
        "Senarai Hakmilik Yang Akan Luput Tempoh",
        "Senarai Hakmilik Kategori Pertanian Untuk Estate Holder(>100 Ekar)",
        "Laporan Tanah Dimakan Air",
        "Laporan Tanah-tanah MCL",
        "Laporan pengeluaran QT",
        "Laporan pengeluaran FT",
        "Laporan Hakmilik Gantian",
        "Laporan pengeluaran kertas Hakmilik"};
      
      senaraiConvertHakmilik = new String[]{
        "Laporan Tukarganti Mengikut Tarikh",
        "Laporan Tukarganti Mengikut Tahun ",
        "Laporan Tukarganti Mengikut Pendaftar",
        "Laporan Ringkasan Tukarganti Mengikut Pendaftar",
        "Laporan Tukarganti Pendaftar Mengikut Tahun",
        "Laporan Sasaran Tukarganti Pendaftar Mengikut Tahun",
        "Laporan Tukarganti Syor Tolak / Tolak",
        "Laporan Statistik Tukarganti Hakmilik",
        "Laporan Prestasi Pendaftar Urusan Tukarganti Secara Bulanan"
      };
      
      laporanBaru = new String[]{"Laporan Kutipan Harian Unit Pendaftaran",
        "Laporan Bendahari Negeri Melaka",
        "Laporan Syarikat MCL", //                "Laporan Certificated Person Baru"
      };

      // Report Name
      senaraiMaklumatHakmilikRN = new String[]{"ETMIS41_1.rdf",
        "ETMIS42_1.rdf"};

      senaraiKeluasanTanahRN = new String[]{"ETMIS24_1.rdf",
        "ETMIS25_1.rdf",
        "ETMIS23_1.rdf",
        "ETMIS57_1.rdf"};

      senaraiPemilikanRN = new String[]{"ETMIS15_1.rdf",
        "ETMIS14_1.rdf",
        "ETMIS11_1.rdf",
        "ETMIS04_1.rdf",
        "ETMIS16_1.rdf",
        "ETMIS03_1.rdf",
        "ETMIS85_1.rdf"};

      statistikKeluasanTanahRN = new String[]{"ETMIS38_1.rdf",
        "ETMIS32_1.rdf",
        "ETMIS39_1.rdf"};

      laporanKemajuanRN = new String[]{"ETMIS47A_1.rdf",
        "ETMIS54_1.rdf",
        "ETMIS01_1.rdf",
        "ETMIS34_1.rdf",
        "ETMIS66_1.rdf",
        "ETMIS72_1.rdf",
        "ETMIS43_1.rdf",
        "ETMIS44_1.rdf",
        "ETMIS51_1.rdf",
        "ETMIS33_1.rdf",
        "ETMIS65_1.rdf",
        "ETMIS65A_1.rdf",
        "ETMIS53_1.rdf",
        "ETMIS36_1.rdf",
        "ETMIS67_1.rdf",
        "ETMIS35_1.rdf",
        "ETMIS37_1.rdf",
        "ETMIS75_1.rdf",
        "ETMIS47_1.rdf",
        "ETMIS70_1.rdf",
        "ETMIS81_1.rdf",
        "ETMIS73_1.rdf",
        "ETMIS86_1.rdf",
        "ETMIS_ADD_08.rdf",
        "ETMIS88_1.rdf",
        "ETMIS88_1STRATA.rdf",
        "ETMIS88_1LANDED.rdf",
        "ETMIS89_1.rdf",
        "ETMIS90_1.rdf",       
        "ETMIS91.rdf",
        "ETMIS92_1.rdf",
        "ETMIS_CetakanSemulaMLK.rdf",
        "ETMIS88_1DutiSetem.rdf",
        "ETMIS88_1STRATAdutiSetem.rdf",
        "ETMIS88_1LANDEDdutiSetem.rdf", };

      laporanHakmilikRN = new String[]{"ETMIS69_1.rdf",
        "ETMIS05_1.rdf",
        "ETMIS19_1.rdf",
        "ETMIS27_1.rdf",
        "ETMIS26_1.rdf",
        "ETMIS26A_1.rdf",
        "ETMIS06_1.rdf",
        "ETMIS08_1.rdf",
        "ETMIS08A_1.rdf",
        "ETMIS09_1.rdf",
        "ETMIS10_1.rdf",
        "ETMIS12_1.rdf",
        "ETMIS07_1.rdf",
        "ETMIS64_1.rdf",
        "ETMIS71_1.rdf",
        "ETMIS20_1.rdf",
        "ETMIS74_1.rdf",
        "ETMIS17_1.rdf",
        "ETMIS21_1.rdf",
        "ETMIS63_1.rdf",
        "ETMIS79_1.rdf",
        "ETMIS80_1.rdf",
        "ETMIS87_1.rdf",
        "ETMIS_ADD_09.rdf"};

      senaraiConvertHakmilikRN = new String[]{"ETTG01_NS.rdf",
        "ETTG02_NS.rdf",
        "ETTG03_NS.rdf",
        "ETTG09.rdf",
        "ETTG04_NS.rdf",
        "ETTG05_NS.rdf",
        "ETTG06_NS.rdf",
        "ETTG08.rdf",
        "regPrestasiPendaftar.rdf"};
      
      laporanBaruRN = new String[]{"ETMIS83_1.rdf",
        "ETMIS82_1.rdf",
        "ETMIS_ADD_10.rdf" //                "ETMIS00_1.rdf"
      };
    }

    if ("05".equals(conf.getProperty("kodNegeri"))) {
      senaraiKeluasanTanah = new String[]{
        "Maklumat Keluasan Mengikut Kategori Tanah"};

      senaraiPemilikan = new String[]{
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

      senaraiConvertHakmilik = new String[]{
        "Laporan Tukarganti Mengikut Tarikh",
        "Laporan Tukarganti Mengikut Tahun ",
        "Laporan Tukarganti Mengikut Pendaftar",
        "Laporan Tukarganti Pendaftar Mengikut Tahun",
        "Laporan Forecast Tukarganti Pendaftar Mengikut Tahun",
        "Laporan Tukarganti Syor Tolak / Tolak"
      };

      laporanKutipanData = new String[]{"Laporan Senarai Kutipan Data Hakmilik Manual"};

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
      senaraiKeluasanTanahRN = new String[]{
        "ETMIS24_1NS.rdf"};

      senaraiPemilikanRN = new String[]{
        "ETMIS16_1NS.rdf",
        "ETMIS80_1NS.rdf"};

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

      senaraiConvertHakmilikRN = new String[]{"ETTG01_NS.rdf",
        "ETTG02_NS.rdf",
        "ETTG03_NS.rdf",
        "ETTG04_NS.rdf",
        "ETTG05_NS.rdf",
        "ETTG06_NS.rdf"};

      laporanKutipanDataRN = new String[]{"ETKD01_NS.rdf"};

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

//    KodCawangan kc = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
//      listPguna = pService.findPenggunaKodCaw(kc);  // this item haven't been used. unless otherwise, please use if statement for those report

    if (peng.getKodCawangan().getDaerah() != null) {
      // IF kod daerah is null, means its PTD
      kodDaerah = peng.getKodCawangan().getDaerah().getKod();
      kodCaw = peng.getKodCawangan().getKod();
      LOG.info("(requestParam) kodDaerah :" + kodDaerah);
      LOG.info("(requestParam) kodCaw :" + kodCaw);
      staticKodDaerah = kodDaerah;
      staticKodCaw = kodCaw;

      if (!reportName.equalsIgnoreCase("ETTG03_NS.rdf")
              && !reportName.equalsIgnoreCase("ETTG09.rdf")
              && !reportName.equalsIgnoreCase("ETTG05_NS.rdf")
              && !reportName.equalsIgnoreCase("ETTG02_NS.rdf")
              && !reportName.equalsIgnoreCase("ETTG04_NS.rdf")
              && !reportName.equalsIgnoreCase("ETTG01_NS.rdf")
              && !reportName.equalsIgnoreCase("ETTG06_NS.rdf")
              && !reportName.equalsIgnoreCase("ETKD01_NS.rdf")) {
        // USE THIS FOR REPORT THAT NEED PARAMETER ON BANDAR/PEKAN/MUKIM
        penyukatanBPM();
        sukatBPMml();
        sukatBPM();
      }

      if (reportName.equalsIgnoreCase("ETKD01_NS.rdf")) {
        // USE THIS TO FILTER BANDAR PEKAN MUKIM FOR USER PTG
        Session s = sessionProvider.get();
        String sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
        Query q = s.createQuery(sql);
        q.setString("kod", kodDaerah);
        senaraiBPM = q.list();
      }
    }

    if(reportName.equalsIgnoreCase("ETTG05_NS.rdf")){
        Calendar cal = Calendar.getInstance();
        calendar = cal.getTime();
        int currentYear = cal.get(Calendar.YEAR);
    }
        
    
    if (reportName.equalsIgnoreCase("ETTG03_NS.rdf")
            || reportName.equalsIgnoreCase("ETTG09.rdf")
            || reportName.equalsIgnoreCase("ETMIS44_1NS.rdf")) {
      // USE THIS TO FIND LIST OF PENDAFTAR BY KOD_CAW 
      senaraiPendaftar = findListPendaftar(peng.getKodCawangan().getKod());
    }
    return new ForwardResolution("/WEB-INF/jsp/common/laporan_maklumaturusan_param.jsp").addParameter("popup", "true");
  }

  public Resolution penyukatanBPM() {
    kodDaerah = (String) getContext().getRequest().getParameter("daerah");
    reportName = getContext().getRequest().getParameter("namaReport");
    report = getContext().getRequest().getParameter("report");
    peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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

  public List<Pengguna> findListPendaftar(String kodCaw) {
    String query = "SELECT p FROM etanah.model.Pengguna p "
            + "WHERE p.status = 'A' and p.perananUtama.kod = '4' and p.kodCawangan.kod = :kod";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("kod", kodCaw);
    return q.list();
  }

  public List<KodDaerah> getSenaraiKodDaerahPTG() {
    String query = "SELECT d FROM etanah.model.KodDaerah d "
            + "WHERE d.aktif='Y'  "
            + "ORDER BY d.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
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

  public String[] getSenaraiKeluasanTanah() {
    return senaraiKeluasanTanah;
  }

  public void setSenaraiKeluasanTanah(String[] senaraiKeluasanTanah) {
    this.senaraiKeluasanTanah = senaraiKeluasanTanah;
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

  public String[] getSenaraiConvertHakmilik() {
    return senaraiConvertHakmilik;
  }

  public void setSenaraiConvertHakmilik(String[] senaraiConvertHakmilik) {
    this.senaraiConvertHakmilik = senaraiConvertHakmilik;
  }

  public String[] getSenaraiConvertHakmilikRN() {
    return senaraiConvertHakmilikRN;
  }

  public void setSenaraiConvertHakmilikRN(String[] senaraiConvertHakmilikRN) {
    this.senaraiConvertHakmilikRN = senaraiConvertHakmilikRN;
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

  public String[] getLaporanKutipanData() {
    return laporanKutipanData;
  }

  public void setLaporanKutipanData(String[] laporanKutipanData) {
    this.laporanKutipanData = laporanKutipanData;
  }

  public String[] getLaporanKutipanDataRN() {
    return laporanKutipanDataRN;
  }

  public void setLaporanKutipanDataRN(String[] laporanKutipanDataRN) {
    this.laporanKutipanDataRN = laporanKutipanDataRN;
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
    LaporanMaklumatUrusanMis.staticKodDaerah = staticKodDaerah;
  }

  public List<Pengguna> getSenaraiPendaftar() {
    return senaraiPendaftar;
  }

  public void setSenaraiPendaftar(List<Pengguna> senaraiPendaftar) {
    this.senaraiPendaftar = senaraiPendaftar;
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

  public List<KodUrusan> getSenaraiUrusan() {
    return senaraiUrusan;
  }

  public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
    this.senaraiUrusan = senaraiUrusan;
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

    public Date getCalendar() {
        return calendar;
    }

    public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }
    
}
