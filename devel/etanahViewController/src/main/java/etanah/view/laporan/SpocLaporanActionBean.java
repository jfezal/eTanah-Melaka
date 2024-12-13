/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodAgensiKutipan;
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
 * @author abu.mansur
 */
@UrlBinding("/laporanSpoc")
public class SpocLaporanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(SpocLaporanActionBean.class);
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
    private String kodAgensi;
    private Pengguna pengguna;
    private List<String> listYear = new ArrayList<String>();
    private List<String> senaraiKaunter = new ArrayList<String>();
    private List<Pengguna> senaraiPenggunaCaw = new ArrayList<Pengguna>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
    private List<KodAgensiKutipan> senaraiKodAgensiKutipan = new ArrayList<KodAgensiKutipan>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    @Inject
    PenggunaDAO penggunaDAO;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/common/spoc_laporan.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
        String DiffReport2 = null;
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
            senaraiReport = new String[]{
                "Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                "Senarai Terimaan",
                "Senarai Terimaan Tempatan",
                "Senarai Terimaan Kutipan Luar",
                "Kemajuan Cukai Tanah, Denda dan Notis Mengikut Mukim",
                "Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
                "Laporan Penyata Pemungut",
                "Senarai Hakmilik Luar Yang Dikutip",
                "Senarai Kutipan Batal",
                "Senarai Terimaan Harian Dari Agensi (cth: POS dan sebagainya)",
//                "Senarai Kutipan Tunai/Cek/Jurnal",
                "Senarai Kutipan Cukai Tahun Pertama",
//                "Senarai Pelarasan Hasil",
                "Laporan Penyata Penyesuaian",
                "Statistik Permohonan Yang Ditolak",
                "Statistik Permohonan Yang Diterima",
                "Statistik Pelanggan Mengikut Modul"
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
//                "HSL_R_24.rdf",
                "HSL_R_25.rdf",
//                "HSL_R_26.rdf",
                "HSL_ADD_10.rdf",
                "HSL_R_28.rdf",
                "HSL_R_29.rdf",
                "HSL_R_30.rdf"
            };
        }else if ("04".equals(conf.getProperty("kodNegeri"))) {// for Negeri Melaka
            senaraiReport = new String[]{
                "Senarai Terimaan Harian",
                "Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                "Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim",
                "Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
                "Laporan Penyata Pemungut",
                
                "Senarai Hakmilik Daerah Lain Yang Dikutip",
                "Senarai Hakmilik Yang Dikutip oleh Daerah Lain",
                "Senarai Kutipan Batal",
                "Senarai Terimaan Harian Dari Agensi (eg: POS dan etc)",
                "Senarai Kutipan Cukai Tahun Pertama",
                "Statistik Permohonan Yang Ditolak",
                
                "Statistik Permohonan Yang Diterima",
                "Statistik Pelanggan Mengikut Modul",
  //              "Senarai Terimaan SPEKS",
                "Laporan Penyata Kutipan Cukai Harian Mengikut Mukim",
                "Laporan Kutipan Kaunter (Summary + Details)",
                
                "Laporan Pembayaran Ansuran",
                "Ringkasan Tunggakan Cukai Ikut Kategori (Summary)",
                "Senarai Tunggakan Cukai Ikut Kategori",
                "Senarai Terimaan Kew 38",
                "Laporan Tunggakan Sehingga....",
                "Senarai Terimaan Harian Dari Agensi E-bayar",
                "Senarai Terimaan Harian Dari Agensi E-bayar ( Bayaran Pukal )",
                "Senarai Terimaan Harian Dari Agensi JomPay"
                
            };
            senaraiReportName = new String[]{
                "HSL_R_01_MLK.rdf",
                "HSL_R_02_MLK.rdf",
                "HSL_R_03_MLK.rdf",
                "HSL_R_04_MLK.rdf",
                "HSL_PP_MLK.rdf",

                "HSL_R_05_MLK.rdf",
                "HSL_R_50_MLK.rdf",
                "HSL_R_07_MLK.rdf",
                "HSL_R_06_MLK.rdf",
                "HSL_R_09_MLK.rdf",
                "HSL_R_12_MLK.rdf",

                "HSL_R_13_MLK.rdf",
                "HSL_R_14_MLK.rdf",
  //            "",
                "HSL_R_16_MLK.rdf",
                "HSL_R_17_MLK.rdf",

                "HSL_R_19_MLK.rdf",
                "HSL_R_63_MLK.rdf",
                "HSL_R_23_MLK.rdf",
                "HSL_R_68_MLK.rdf",
                "HSL_R_24_MLK.rdf",
                "HSL_R_69_MLK.rdf",
                "HSL_R_69_MLK_PUKAL.rdf",
                "HSL_R_15_MLK_JOMPAY.rdf"
                
            };
        }
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if(pengguna.getKodCawangan() != null)
            kodCaw = pengguna.getKodCawangan().getKod();
        doCollectKaunter();
        doFilterDaerahBPM();
        doFilterKodTransaksi();
        Agensi();
//        LOG.info("kodCaw :"+kodCaw);
//        getPenggunaCaw(kodCaw);
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
    }

    private void getPenggunaCaw(String caw){
        List<Pengguna> senaraiPguna = new ArrayList<Pengguna>();
        String[] name = {"kodCawangan.kod"};
        Object[] value= {caw};
        senaraiPguna = penggunaDAO.findByEqualCriterias(name, value, "idPengguna");
        for (Pengguna pguna : senaraiPguna) {
            if(pguna.getStatus() == null)
                continue;
            if(pguna.getStatus().getKod().equals("A")) // A=aktif
                senaraiPenggunaCaw.add(pguna);
        }
        LOG.info("senaraiPenggunaCaw.size :"+senaraiPenggunaCaw.size());
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
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
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

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public List<Pengguna> getSenaraiPenggunaCaw() {
        return senaraiPenggunaCaw;
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
    public void setSenaraiPenggunaCaw(List<Pengguna> senaraiPenggunaCaw) {
        this.senaraiPenggunaCaw = senaraiPenggunaCaw;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public void setSenaraiKodTransaksi(List<KodTransaksi> senaraiKodTransaksi) {
        this.senaraiKodTransaksi = senaraiKodTransaksi;
    }
   public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
   }
}
    
