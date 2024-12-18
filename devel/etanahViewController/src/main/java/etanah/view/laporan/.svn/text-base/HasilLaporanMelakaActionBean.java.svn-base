/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodAgensiKutipan;
import etanah.model.KodSeksyen;
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
@UrlBinding("/laporanHasil_MLK")
public class HasilLaporanMelakaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(HasilLaporanMelakaActionBean.class);
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String kodCaw;
    private String kodDaerah;
    private String kodDaerah1;
    private String kodAgensi;
    private String daerahHasil;
    private String seksyen;
    private static String staticKodDaerah;
    private static String staticKodCaw;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna pengguna;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private List<String> senaraiKaunter = new ArrayList<String>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<KodDaerah> senaraiKodDaerah = new ArrayList<KodDaerah>();
    private List<KodAgensiKutipan> senaraiKodAgensiKutipan = new ArrayList<KodAgensiKutipan>();

    private List<KodSeksyen> senaraiKodSeksyen;
    private String bandarPekanMukim;
    String daerah;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();

        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_melaka.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");

        senaraiReport = new String[]{
            //Laporan Spoc = 18

            /*1*/"Senarai Terimaan Harian",
            /*2*/ "Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
            /*3*/ "Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim",
            /*4*/ "Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
            /*5*/ "Laporan Penyata Pemungut",
            /*6*/ "Senarai Hakmilik Daerah Lain Yang Dikutip",
            /*6a*/ "Senarai Hakmilik Yang Dikutip oleh Daerah Lain",
            /*7*/ "Senarai Kutipan Batal",
            /*8*/ "Senarai Terimaan Harian Dari Agensi (eg: POS dan etc)",
            /*9*/ "Senarai Kutipan Cukai Tahun Pertama",
            /*10*/ "Statistik Permohonan Yang Ditolak",
            /*11*/ "Statistik Permohonan Yang Diterima",
            /*12*/ "Statistik Pelanggan Mengikut Modul",
            //"Senarai Terimaan SPEKS",
            /*13*/ "Laporan Penyata Kutipan Cukai Harian Mengikut Mukim",
             /*13*/ "Laporan Penyata Kutipan Cukai Harian Mengikut Mukim (Strata)",
            
            /*14*/ "Laporan Kutipan Kaunter (Summary + Details)",
            /*15*/ "Laporan Pembayaran Ansuran",
            /*16*/ "Ringkasan Tunggakan Cukai Ikut Kategori (Summary)",
            /*16*/ "Ringkasan Tunggakan Cukai Ikut Kategori (Summary Strata)",
            /*16a*/ "Senarai Tunggakan Cukai Ikut Kategori",
            /*16a*/ "Senarai Tunggakan Cukai Ikut Kategori (Strata)",
            /*16b*/ "Senarai Terimaan Kew 38",
            /*17*/ "Laporan Tunggakan Sehingga....",
            /*18*/ "Senarai Resit Batal (Kutipan Cek)",
            /*19*/ "Senarai Resit Yang Dibatalkan Bagi Bulan..Tahun..",
            //laporan Hasil = 12
            /*20*/ "Laporan Induk Cukai Tanah",
            /*20*/ "Laporan Induk Cukai Tanah (Strata)",
            /*a20*/ "Ringkasan Induk Cukai Tanah",
            /*a20*/ "Ringkasan Induk Cukai Tanah(Strata)",
            /*21*/ "Senarai Hakmilik Yang Dilucut hakkan Melalui Borang 8A & Warta Kerajaan  Bagi Tahun",
            /*22*/ "Senarai Hakmilik Yang Mempunyai Jumlah Baki Dan Jumlah Debit/Kredit Tidak Sama",
            /*23*/ "Laporan Terimaan Harian Dari Agensi - Mengikut Kod Jenis Hasil",
            /*24*/ "Senarai Akaun Masa Hadapan ( Hasil Tahun Pertama)",
            /*25*/ "Ringkasan Umuran Tunggakan Cukai Tanah",
            /*26*/ "Penyata Buku Tunai (KEW 250) ",
            /*27*/ "Laporan Tiada Nama/Alamat bagi Pembayar",
            /*28*/ "Penyata Buku Tunai (KEW 248)",
            /*29*/ "Ringkasan Daftar Cukai Mengikut Jenis Hakmilik bagi Setiap Mukim",
            /*30*/ "Maklumat Kutipan Cukai Tanah ",
            /*30*/ "Maklumat Kutipan Cukai Tanah ( Strata )",
            /*31*/ "Senarai Daftar Cukai",
            /*31*/ "Senarai Daftar Cukai ( Strata )",
            /*32*/ "Senarai Cukai Tanah Yang DiKutip Dari..",
            /*32*/ "Senarai Cukai Tanah Yang DiKutip Dari.. ( Strata )",
            /*33*/ "Laporan SPEKS",
            /*34*/ "Senarai Terimaan Harian Dari Agensi E-bayar",
            /*35*/ "Senarai Terimaan Harian Dari Agensi E-bayar (Bayaran Pukal)",
            /*36*/ "Senarai Laporan Penyata Buku Tunai",
                "Senarai Terimaan Harian Dari Agensi JomPay"
        };

        senaraiReportName = new String[]{
            //laporan Spoc = 18
            /*1*/"HSL_R_01_MLK.rdf",
            /*2*/ "HSL_R_02_MLK.rdf",
            /*3*/ "HSL_R_03_MLK.rdf",
            /*4*/ "HSL_R_04_MLK.rdf",
            /*5*/ "HSL_PP_MLK.rdf",
            /*6*/ "HSL_R_05_MLK.rdf",
            /*6a*/ "HSL_R_50_MLK.rdf",
            /*7*/ "HSL_R_07_MLK.rdf",
            /*8*/ "HSL_R_06_MLK.rdf",
            /*9*/ "HSL_R_09_MLK.rdf",
            /*10*/ "HSL_R_12_MLK.rdf",
            /*11*/ "HSL_R_13_MLK.rdf",
            /*12*/ "HSL_R_14_MLK.rdf",
            //"",
            /*13*/ "HSL_R_16_MLK.rdf",
            /*13*/ "HSL_R_70_MLK.rdf",
            /*14*/ "HSL_R_17_MLK.rdf",
            /*15*/ "HSL_R_19_MLK.rdf",
            /*16a*/ "HSL_R_63_MLK.rdf",
            /*16a*/ "HSL_R_71_MLK.rdf",
            /*16*/ "HSL_R_23_MLK.rdf",
            /*16*/ "HSL_R_72_MLK.rdf",
            /*16b*/ "HSL_R_68_MLK.rdf",
            /*17*/ "HSL_R_24_MLK.rdf",
            /*18*/ "HSL_R_25_MLK.rdf",
            /*19*/ "HSL_R_27_MLK.rdf",
            //laporan Hasil = 12
            /*20*/ "HSL_R_34_MLK.rdf",
            /*20*/ "HSL_R_73_MLK.rdf",
            /*a20*/ "HSL_R_66_MLK.rdf",
            /*a20*/ "HSL_R_74_MLK.rdf",
            /*21*/ "HSL_R_37_MLK.rdf",
            /*22*/ "HSL_R_40_MLK.rdf",
            /*23*/ "HSL_R_41_MLK.rdf",
            /*24*/ "HSL_R_45_MLK.rdf",
            /*25*/ "HSL_R_46_MLK.rdf",
            /*26*/ "HSL_R_48_MLK.rdf",
            /*27*/ "HSL_R_47_MLK.rdf",
            /*28*/ "HSL_R_49_MLK.rdf",
            /*29*/ "HSL_R_44_MLK.rdf",
            /*30*/ "HSL_R_43_MLK.rdf",
            /*30*/ "HSL_R_75_MLK.rdf",
            /*31*/ "HSL_R_42_MLK.rdf",
            /*31*/ "HSL_R_76_MLK.rdf",
            /*32*/ "HSL_R_67_MLK.rdf",
            /*32*/ "HSL_R_77_MLK.rdf",
            /*33*/ "HSL_R_SPEKS_MLK.rdf",
            /*34*/ "HSL_R_69_MLK.rdf",
            /*35*/ "HSL_R_69_MLK_PUKAL.rdf",
            /*36*/ "HSL_BUKU_TUNAI_MLK.rdf",
            "HSL_R_15_MLK_JOMPAY.rdf"
        };

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if (pengguna.getKodCawangan().getDaerah() != null) {
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
            kodDaerah1 = pengguna.getKodCawangan().getDaerah().getKod();
            kodCaw = pengguna.getKodCawangan().getKod();
            LOG.info("(requestParam) kodDaerah :" + kodDaerah);
            LOG.info("(requestParam) kodCaw :" + kodCaw);
            staticKodDaerah = kodDaerah;
            staticKodCaw = kodCaw;
            doFilterBPM();
            doCollectKaunter();
            doFilterDaerahBPM();
            Agensi();
            senaraiKodDaerah.add(pengguna.getKodCawangan().getDaerah());
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }

    public Resolution doCollectKaunter() {
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (kodCaw != null) {
            kodCaw = cawKod;
        }
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }
        LOG.info("kodCaw :" + kodCaw);

        if (kodCaw == null) {
            kodCaw = staticKodCaw;
        }

        if (!senaraiKaunter.isEmpty()) {
            senaraiKaunter.clear();
            LOG.info("senaraiKaunter : is Empty now");
        }
        if (kodCaw != null) {
            String sql = "SELECT distinct p.idKaunter FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod = :kod ORDER BY p.idKaunter asc";
            Session s = sessionProvider.get();
            Query q = s.createQuery(sql);
            q.setString("kod", kodCaw);
            List<String> senaraiPengguna = new ArrayList<String>();
            senaraiPengguna = q.list();
            int count = 1;
            for (String pguna : senaraiPengguna) {
                if (pguna == null) {
                    continue;
                }
                senaraiKaunter.add(pguna);
//                LOG.debug(count+") senaraiKaunter :"+pguna);
                count++;
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }

    public Resolution doFilterDaerahBPM() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            kodCaw = cawKod;
        }
        LOG.info("(doFilterDaerahBPM) kodCaw :" + kodCaw);

        if (kodCaw == null) {
            kodCaw = staticKodCaw;
        }

        KodCawangan kc = kodCawanganDAO.findById(kodCaw);
        if (kc.getDaerah() != null) {
            kodDaerah = null;
            daerahHasil = kc.getDaerah().getKod();
        }
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }
        kodDaerah = daerahHasil;
        doFilterBPM();
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }

    public Resolution doFilterBPM() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String daerahKod = getContext().getRequest().getParameter("kodDaerah");
        LOG.info("(doFilterBPM) daerahKod :" + daerahKod);
        if (daerahKod != null) {
            kodDaerah = daerahKod;
        }
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if (namaReport != null) {
            reportName = namaReport;
        }
        LOG.info("(doFilterBPM) kodDaerah :" + kodDaerah);

        if (kodDaerah == null) {
            kodDaerah = staticKodDaerah;
        }

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
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }

    public Resolution penyukatanBPM() {
        String kodDaerah = getDaerah();
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
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }
    
    public Resolution Agensi() {
        String kodAgensi = getKodAgensi();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodAgensi == null || kodAgensi.equals("")) {
            sql = "select kg from KodAgensiKutipan kg ";
            q = s.createQuery(sql);
        } 
        List<KodAgensiKutipan> senaraiKodAgensi1 = q.list();
        for (KodAgensiKutipan kodAgesnsi1 : senaraiKodAgensi1){
            if (!kodAgesnsi1.getKod().equals("AGPYT")){
                senaraiKodAgensiKutipan.add(kodAgesnsi1);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }

    public Resolution penyukatanSeksyen() {
//        kodDaerah = pengguna.getKodCawangan().getName();
        String kodDaerah = getContext().getRequest().getParameter("bandarPekanMukim");
        LOG.info("kodDaerah = " + kodDaerah);
        String sql = null;
        Session s = sessionProvider.get();
        KodBandarPekanMukim kodBPM1 = kodBandarPekanMukimDAO.findById(Integer.valueOf(kodDaerah));
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
        String kodBPM = getBandarPekanMukim();
//        logger.debug("BPM-->" + kodBPM);
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
        if (kodBPM == null || kodBPM.equals("")) {
            sql = "select sek from KodSeksyen sek ";
            q = s.createQuery(sql);
        } else {
            sql = "select sek from KodSeksyen sek where sek.kodBandarPekanMukim.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodBPM);
        }
        senaraiKodSeksyen = q.list();

        if (senaraiKodSeksyen.size() > 0) {
            seksyen = "1";
        } else {
            seksyen = "0";
        }

//        kodDaerah1 = senaraiBPM.get(0).getCawangan().getName();
//           String kodDaerah = getDaerah();
//        String sql = null;
//        Session s = sessionProvider.get();
//        Query q = null;
        if (kodDaerah1 == null || kodDaerah1.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah1);
        }
        senaraiBPM = q.list();
      
        senaraiKodDaerah.add(kodBPM1.getDaerah());

//        this.kodDaerah  = pengguna.getKodCawangan().getKod();
//        logger.debug("senaraiKodSeksyen-->" + senaraiKodSeksyen.get(0).getNama());
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
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

    public String getDaerahHasil() {
        return daerahHasil;
    }

    public void setDaerahHasil(String daerahHasil) {
        this.daerahHasil = daerahHasil;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public static String getStaticKodCaw() {
        return staticKodCaw;
    }

    public static void setStaticKodCaw(String staticKodCaw) {
        HasilLaporanMelakaActionBean.staticKodCaw = staticKodCaw;
    }

    public static String getStaticKodDaerah() {
        return staticKodDaerah;
    }

    public static void setStaticKodDaerah(String staticKodDaerah) {
        HasilLaporanMelakaActionBean.staticKodDaerah = staticKodDaerah;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<KodAgensiKutipan> getSenaraiKodAgensiKutipan() {
        return senaraiKodAgensiKutipan;
    }

    public void setSenaraiKodAgensiKutipan(List<KodAgensiKutipan> senaraiKodAgensiKutipan) {
        this.senaraiKodAgensiKutipan = senaraiKodAgensiKutipan;
    }

    
    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }
    
    public String getKodDaerah1() {
        return kodDaerah1;
    }

    public void setKodDaerah1(String kodDaerah1) {
        this.kodDaerah1 = kodDaerah1;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }
    

}
