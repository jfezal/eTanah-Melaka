/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
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
/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/laporanHasilSpoc_NS")
public class NSHasilSpocLaporanActionBean extends AbleActionBean {




    private static final Logger LOG = Logger.getLogger(NSHasilSpocLaporanActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String kodCaw;
    private String kodDaerah;
    private String daerahHasil;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna pengguna;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private List<String> senaraiKaunter = new ArrayList<String>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<Pengguna> senaraiPenggunaCaw = new ArrayList<Pengguna>();
    private List<KodAgensi> senaraiAgensiPermohonan = new ArrayList<KodAgensi>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasilspoc_laporan_NS.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
            senaraiReport = new String[]{
                "Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                "Senarai Terimaan",
                "Senarai Terimaan Tempatan",
                "Senarai Terimaan - Kutipan Luar",
                "Kemajuan Cukai Tanah, Denda dan Notis Mengikut Mukim", 
                "Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
                "Laporan Penyata Pemungut",
                "Senarai Hakmilik Luar Yang Dikutip",
                "Senarai Kutipan Batal",
                "Senarai Terimaan Harian Dari Agensi (cth: POS dan sebagainya)",
                "Laporan Penyata Penyesuaian",
                "Statistik Permohonan Yang Ditolak",
                "Statistik Permohonan Yang Diterima",
                "Statistik Pelanggan Mengikut Modul",
                "Senarai Induk Hakmilik Mengikut Mukim",
                "Senarai Indek Hakmilik Ikut Nama",
                "Laporan Senarai Index Lot",
                "Senarai Hakmilik Batal Mengikut Mukim",
                "Senarai Hakmilik Yang Dikenakan Notis 6A Tetapi Belum Membuat bayaran",
                "Senarai Hakmilik Mengikut Nama",
                "Senarai Hakmilik Yang Kurang Bayar Tahun Ini",
                "Senarai Hakmilik Yang Lebih Bayar Tahun Ini",
                "Senarai Hakmilik Telah Bayar Tahun Ini",
                "Senarai Hakmilik Yang Belum Bayar Tahun Ini",
                "Senarai Hakmilik Mengikut Jenis Hakmilik",
                "Senarai Hakmilik Yang Telah Dibatalkan Akibat Sambungan",
                "Senarai Hakmilik Ada Amaun Lebih",
                "Senarai Hakmilik Tidak Beralamat",
                "Senarai Hakmilik Yang Dikutip Di Daerah Lain",
                "Senarai Hakmilik Yang Mempunyai Jumlah Baki Dan Jumlah Debit/Kredit Tidak Sama",
                "Laporan Closing Bulanan Dan Tahunan",
                "Senarai Rekod Hakmilik Yang Tertunggak Mengikut Amaun Dan Tahun",
                "Laporan Tunggakan Hasil Seperti Pada DDMMYYYY",
                "Senarai Hakmilik Untuk Amaun Patut Kutip Mengikut Mukim",
                "Statistik Perbandingan Prestasi Kutipan Cukai Tanah Mengikut Tahun",
                "Laporan Hakmilik Yang Terlibat Dengan Notis 6A Mengikut Tarikh",
                "Senarai Hakmilik Yang Membuat Bayaran Ansuran Cukai Tanah",
                "Senarai Hakmilik Yang Terlibat Dengan Pemberian Remisyen",
                "Senarai Permohonan Remisyen Mengikut Agensi",     
                "Senarai Hakmilik Yang Terlibat Dengan Notis 6A Beserta Amaun Yang Terlibat",
                "Senarai Hakmilik Yang Telah Dibatalkan Kerana 8A"                        
            };
            senaraiReportName = new String[]{
                "HSL_R_01.rdf",
                "HSL_R_02.rdf",
                "HSL_R_42.rdf",
                "HSL_R_41.rdf",
                "HSL_R_03.rdf",
                "HSL_R_04.rdf",
                "HSL_PP_NS.rdf",
                "HSL_R_05.rdf",
                "HSL_R_23.rdf",
                "HSL_R_21.rdf",
                "HSL_ADD_10.rdf",
                "HSL_R_28.rdf",
                "HSL_R_29.rdf",
                "HSL_R_30.rdf",
                "HSL_R_06.rdf",
                "HSL_R_07.rdf",
                "HSL_R_08.rdf",
                "HSL_R_09.rdf",
                "HSL_R_12.rdf",
                "HSL_R_13.rdf",
                "HSL_R_14.rdf",
                "HSL_R_15.rdf",
                "HSL_R_16.rdf",
                "HSL_R_17.rdf",
                "HSL_R_20.rdf",
                "HSL_R_31.rdf",
                "HSL_R_32.rdf",
                "HSL_R_33.rdf",
                "HSL_R_36.rdf",
                "HSL_R_37.rdf",
                "HSL_R_34.rdf",
                "HSL_ADD_01.rdf",
                "HSL_ADD_02.rdf",
                "HSL_ADD_03.rdf",
                "HSL_ADD_04.rdf",
                "HSL_ADD_05.rdf",
                "HSL_ADD_06.rdf",
                "HSL_ADD_07.rdf",
                "HSL_R_40.rdf",
                "HSL_ADD_09.rdf",
                "HSL_R_31_1.rdf"
            };
        }else if ("04".equals(conf.getProperty("kodNegeri"))) {// for Negeri Melaka
            if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
                DiffReport = "HSLSenaraiPenjenisanHasil_NS.rdf";
                LOG.debug("Laporan Negeri Sembilan");
            } else if ("04".equals(conf.getProperty("kodNegeri"))) {// for Negeri Melaka
                DiffReport = "HSLSenaraiPenjenisanHasil_MLK.rdf";
                LOG.debug("Laporan Negeri Melaka");
            }
                senaraiReport = new String[]{
                            //--------------- HASIL REPORT---------------------
                            "Laporan Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                            "Laporan Senarai Terimaan",
                            "Laporan Senarai Terimaan",
                            "Laporan Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim",
                            "Laporan Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
    //                        "Laporan Hakmilik Yang Dikutip Di Tempat Lain",
                            "Laporan Hakmilik Luar Yang Dikutip",
                            "Laporan Senarai Induk Hakmilik Mengikut Mukim",
                            "Laporan Senarai Index Hakmilik Ikut Nama",
                            "Laporan Senarai Index Lot",
                            "Laporan Senarai Hakmilik Batal Mengikut Mukim",
    //                        "Laporan Senarai Hakmilik Yang Telah Dibatalkan Mengikut Tarikh Batal",
                            "Laporan Senarai Hakmilik Tertunggak bagi Amaun Dari RM dan Ke Atas",
    //                        "Laporan Senarai Hakmilik Ada Amaun Lebih (Mengikut Daerah)",
                            "Laporan Senarai Alamat Ragu",
    //                        "Laporan Senarai Hakmilik Beralamat Dan Tidak Beralamat",
                            "Laporan Senarai Hakmilik Yang Dikenakan Notis 6A Tetapi Belum Membuat Bayaran",
    //                        "Laporan Senarai Hakmilik Yang Dikenakan Notis 6A Tetapi Telah Membuat Bayaran",
                            "Laporan Senarai Hakmilik Mengikut Nama",
                            "Laporan Senarai Hakmilik Yang Kurang Bayar Tahun Ini",
                            "Laporan Senarai Hakmilik Yang Lebih Bayar Tahun Ini",
                            "Laporan Senarai Hakmilik Telah Bayar Tahun Ini",
                            "Laporan Senarai Hakmilik Yang Belum Bayar Tahun Ini",
    //                        "Surat Keputusan Kutipan Tidak Sah",
    //                        "Surat Keputusan Pengurangan Denda",
    //                        "Surat Keputusan Permohonan Remisyen",
                            "Notis 6A",
                            "Surat Peringatan",
                            "Borang 8A",
    ////                        "Warta Kerajaan NSDK Sek 100/130 KTN",
    //                        "Borang Pembatalan Notis 6A",
                            "Perintah Notis Gantian (Surat Kebenaran Notis Gantian, Perintah Penyampaian Notis Gantian, Warta Kerajaan NSDK Notis Gantian)",
    //                        "Senarai Resit Yang Dibatalkan Bagi Bulan... Tahun...",
                            "Penyata Kutipan Harian/Mingguan",
                            "Laporan Logoff Kutipan Bayaran Urusan Tanah",
    //                        "Surat Akuan Berkanun",
                            "Bilangan Hakmilik Mengikut Jenis Hakmilik",
                            "Laporan Terimaan Harian dari POS",
                            //--------------SPOC REPORT---------------
    //                        "Laporan Senarai Terimaan",
    //                        "Laporan Senarai Terimaan Persekutuan",
    //                        "Laporan Senarai Terimaan Negeri",
    //                        "Laporan Penjenisan Hasil",
    //                        "Laporan Penyata Pemungut (Bank In)",
                            "Laporan Penyata Pemungut",
                            "Laporan Kutipan Batal",
                            "Laporan Kutipan Tunai/Cek/Jurnal",
                            "Laporan Senarai Kutipan Cukai Tahun Pertama",
                            "Laporan Senarai Pelarasan Hasil",
                            "Laporan Penyata Penyesuaian",
                            "Senarai Hakmilik Yang Dikutip Ditempat Lain",
                            "Statistik Permohonan Yang Ditolak",
                            "Statistik Permohonan Yang Diterima",
                            "Statistik Pelanggan Mengikut Modul"};

                senaraiReportName = new String[]{
                            //-----hasil report .rdf-------
                            "HSL_R_01.rdf",
                            "HSL_R_02.rdf",
                            "HSL_R_03.rdf",
                            "HSL_R_04.rdf",
                            "HSL_R_42.rdf",
    //                        "HSLHakmilikYgDikutipDiTempatLain.rdf",
                            "HSLSenaraiHakmilikLuarYgDikutip.rdf",
                            "HSLSenaraiIndukHakmilikMengikutMukim_002.rdf",
                            "HSLSenaraiIndexHakmilikIkutNama.rdf",
                            "HSLSenaraiIndexLot001.rdf",
                            "HSLSenaraiHakmilikBatalMengikutMukim_003.rdf",
    //                        "HSLSenaraiHakmilikYangTelahDibatalkanMengikutTarikhBatal_005.rdf",
                            "HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf",
    //                        "HSLSenaraiHakmilikAdaAmaunLebih001.rdf",
                            "HSLSenaraiAlamatRagu.rdf",
    //                        "HSLSenaraiHakmilikBeralamatDanTidakBeralamat.rdf",
                            "HSLSenaraiHakmilikNotis6ABelumBayar001.rdf",
    //                        "HSLSenaraiHakmilikNotis6ABayar001.rdf",
                            "HSLSenaraiHakmilikMengikutNama001.rdf",
                            "HSLSenaraiHakmilikYangKurangBayar.rdf",
                            "HSLSenaraiHakmilikYangLebihBayar.rdf",
                            "HSLSenaraiHakmilikTelahBayar.rdf",
                            "HSLSenaraiHakmilikYangBelumBayar.rdf",
    //                        "hasilSuratGantiCekTakLakuResit.rdf",
    //                        "hasilRayuanKurangCukai.rdf",
    //                        "hasilRayuanKurangCukai.rdf",
                            "hasilNotis6A.rdf",
                            "hasilSuratPeringatan.rdf",
                            "hasilNotis8A.rdf",
    ////                        "hasilWartaKerajaan.rdf",
    //                        "hasilPembatalanNotis6A.rdf",
                            "hasilNotisGantian.rdf",
    //                        "HSLSenaraiResitBatal.rdf",
                            "HSLPenyataKutipanMingguan.rdf",
                            "HSLLaporanLogoffKutipan.rdf",
    //                        "HSLSuratAkuanBerkanun.rdf",
                            "HSLPungutanHasilTanahBilanganHakmilik.rdf",
                            "HSLLaporanTerimaanPos.rdf",
                            //-----------spoc report .rdf-----
    //                        "SPOCSenaraiTerimaanHarian(Umum).rdf",
    //                        "SPOCSenaraiTerimaanHarian(Persekutuan).rdf",
    //                        "SPOCSenaraiTerimaanHarian(Negeri).rdf",
    //                        "SPOCLaporanPenjenisanHasil_NS.rdf",
    //                        "SPOCPenyataPemungut(BankIn).rdf",
                            "HSL_Penyata_Pemungut_NS.rdf",
                            "SPOCSenaraiKutipanBatal.rdf",
                            "SPOCLaporanKutipanTunaiCek.rdf",
                            "SPOCSenaraiKutipanCukaiTahunPertama.rdf",
                            "SPOCPelarasanHasil.rdf",
                            "SPOCPenyataPenyesuaianTahun.rdf",
                            "HSL_R_Senarai1.rdf",
                            "SPOCStatistikPermohonanTolak.rdf",
                            "SPOCStatistikPermohonanTerima.rdf",
                            "SPOCStatistikPelangganMengikutModul.rdf"};
        }
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if(pengguna.getKodCawangan().getDaerah() != null)
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
        doFilterBPM();
        if(pengguna.getKodCawangan() != null)
            kodCaw = pengguna.getKodCawangan().getKod();
        doCollectKaunter();
        doFilterDaerahBPM();
        getAgensiPermohonan();
        doFilterKodTransaksi();
//        getPenggunaCaw(kodCaw);
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasilspoc_laporan_param_NS.jsp").addParameter("popup", "true");
    }

    public Resolution doCollectKaunter(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if(cawKod != null)
            kodCaw = cawKod;
        String report = getContext().getRequest().getParameter("reportNama");
        if(report != null)
            reportName = report;
        LOG.info("kodCaw :"+kodCaw);
        if(!senaraiKaunter.isEmpty()){
            senaraiKaunter.clear();
            LOG.info("senaraiKaunter : is Empty now");
        }
        if(reportName != null){
            String sql = "SELECT distinct p.idKaunter FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod = :kod ORDER BY p.idKaunter asc";
            Session s = sessionProvider.get();
            Query q = s.createQuery(sql);
            q.setString("kod", kodCaw);
            List<String> senaraiPengguna = new ArrayList<String>();
            senaraiPengguna = q.list();
            int count = 1;
            for (String pguna : senaraiPengguna) {
                if(pguna == null)
                    continue;
                senaraiKaunter.add(pguna);
//                LOG.debug(count+") senaraiKaunter :"+pguna);
                count++;
            }
            getPenggunaCaw(kodCaw);
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasilspoc_laporan_param_NS.jsp").addParameter("popup", "true");
    }

    public Resolution doFilterDaerahBPM(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if(cawKod != null)
            kodCaw = cawKod;
        KodCawangan kc = kodCawanganDAO.findById(kodCaw);
        if(kc.getDaerah() != null){
            kodDaerah = null;
            daerahHasil = kc.getDaerah().getKod();
        }
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if(namaReport != null)
            reportName = namaReport;
        kodDaerah = daerahHasil;
        doFilterBPM();
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasilspoc_laporan_param_NS.jsp").addParameter("popup", "true");
    }
    
    public Resolution doFilterBPM(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String daerahKod = getContext().getRequest().getParameter("kodDaerah");
        LOG.info("(doFilterBPM) daerahKod :"+daerahKod);
        if(daerahKod != null)
            kodDaerah = daerahKod;
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if(namaReport != null)
            reportName = namaReport;
        LOG.info("(doFilterBPM) kodDaerah :"+kodDaerah);
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasilspoc_laporan_param_NS.jsp").addParameter("popup", "true");
    }

    private void getPenggunaCaw(String caw) {
//        List<Pengguna> senaraiPguna = new ArrayList<Pengguna>();
//        String[] name = {"kodCawangan.kod"};
//        Object[] value = {caw};
//        senaraiPguna = penggunaDAO.findByEqualCriterias(name, value, "idPengguna");
//        for (Pengguna pguna : senaraiPguna) {
//            if (pguna.getStatus() == null) {
//                continue;
//            }
//            if (pguna.getStatus().getKod().equals("A")) { // A=Aktif
//                if ((pguna.getPerananUtama().getKod().equals("2")) || (pguna.getPerananUtama().getKod().equals("5"))) {
//                    senaraiPenggunaCaw.add(pguna);
//                }
//            }
//        }
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT p FROM etanah.model.Pengguna p where p.kodCawangan.kod = :caw "
                + "AND p.perananUtama.kod IN ('2','5') "
                + "AND p.idKaunter IS NOT NULL "
                + "AND p.status.kod='A' "
                + "ORDER BY p.idPengguna ASC");
        q.setString("caw", caw);
        
        senaraiPenggunaCaw = q.list();
        LOG.info("senaraiPenggunaCaw.size :" + senaraiPenggunaCaw.size());
    }

    private void getAgensiPermohonan(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodAgensi u where u.kod in (:kod1,:kod2,:kod3,:kod4)");
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
            q.setString("kod1",khconf.getProperty("jabatan.jpns"));
        }else if ("04".equals(conf.getProperty("kodNegeri"))) { //for Negeri Melaka
            q.setString("kod1", khconf.getProperty("jabatan.jpnm"));
        }
        q.setString("kod2", khconf.getProperty("jabatan.risda"));
        q.setString("kod3", khconf.getProperty("jabatan.felcra"));
        q.setString("kod4", khconf.getProperty("jabatan.felda"));
        senaraiAgensiPermohonan = q.list();
    }

    private void doFilterKodTransaksi(){
        String sql = "FROM etanah.model.KodTransaksi kt WHERE kt.kod IN (:kod1, :kod2, :kod3) ORDER BY kt.kod asc";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("kod1", khconf.getProperty("cukaiTanahSemasa"));
        q.setString("kod2", khconf.getProperty("cukaiTanahTunggakan"));
        q.setString("kod3", khconf.getProperty("dendaLewat"));
        senaraiKodTransaksi = q.list();
        LOG.info("doFilterKodTransaksi : senaraiKodTransaksi :"+senaraiKodTransaksi.size());
    }

    public String[] getSenaraiReport() {
        return senaraiReport;
    }

    public void setSenaraiReport(String[] senaraiReport) {
        this.senaraiReport = senaraiReport;
    }

    public String[] getSenaraiReportName() {
        return senaraiReportName;
    }

    public void setSenaraiReportName(String[] senaraiReportName) {
        this.senaraiReportName = senaraiReportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<String> getSenaraiKaunter() {
        return senaraiKaunter;
    }

    public void setSenaraiKaunter(List<String> senaraiKaunter) {
        this.senaraiKaunter = senaraiKaunter;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getDaerahHasil() {
        return daerahHasil;
    }

    public void setDaerahHasil(String daerahHasil) {
        this.daerahHasil = daerahHasil;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getSenaraiPenggunaCaw() {
        return senaraiPenggunaCaw;
    }

    public void setSenaraiPenggunaCaw(List<Pengguna> senaraiPenggunaCaw) {
        this.senaraiPenggunaCaw = senaraiPenggunaCaw;
    }

    public List<KodAgensi> getSenaraiAgensiPermohonan() {
        return senaraiAgensiPermohonan;
    }

    public void setSenaraiAgensiPermohonan(List<KodAgensi> senaraiAgensiPermohonan) {
        this.senaraiAgensiPermohonan = senaraiAgensiPermohonan;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }

    public List<String> getListYear() {
        //calendar for year
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        listYear.add(String.valueOf(year));
        for (int i = 0; i < 10; i++) {
            year--;
            listYear.add(String.valueOf(year));
        }
        return listYear;
    }
}
