/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
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
@UrlBinding("/strata/laporanstrata")
public class StrataLaporanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(StrataLaporanActionBean.class);
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
    private Pengguna pengguna;
    private List<String> listYear = new ArrayList<String>();
    private List<String> senaraiKaunter = new ArrayList<String>();
    private List<Pengguna> senaraiPenggunaCaw = new ArrayList<Pengguna>();
    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();
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
        // return new ForwardResolution("/WEB-INF/jsp/common/spoc_laporan.jsp");
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/strata_laporan.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
        String DiffReport2 = null;
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
            senaraiReport = new String[]{
                "Laporan Prestasi Permohonan Hakmilik Strata",
                "Laporan Terperinci Prestasi Permohonan Hakmilik Strata",
                //"Laporan Keseluruhan Permohonan Hakmilik Strata Mengikut Tahun",
                "Laporan Keseluruhan Permohonan Hakmilik Strata",
                "Laporan Statistik Hakmilik Strata Kos Rendah",
                "Laporan Statistik Pindah Milik Strata",
                "Laporan Status Pindahmilik yang telah mencapai 25%",
               // "Laporan Peratusan Pindah Milik Bagi Skim Strata",
               // "Laporan Terperinci Peratusan Pindah Milik Bagi Skim Strata",
                "Laporan Buku Daftar Strata",
                "Laporan Penguatkuasaan Akta Hakmilik Strata 1985",
                "Senarai Hakmilik Strata Yang Telah Ditamatkan",
                "Senarai Pemohon Yang Belum Menjelaskan Bayaran Kelulusan",
                "Senarai Pemohon Yang Belum Mengambil Hakmilik Strata",
                "Senarai Nama Perbadanan Pengurusan"                
            };
            senaraiReportName = new String[]{
                "STRLaporanPrestasi_NS.rdf",
                "STRLaporPerinciPrestasi_NS.rdf",
                "STRLaporMohon_NS.rdf",              
                "STRLaporKosRendah_NS.rdf",
                //"STRPindahMilikSkim_NS.rdf",
                "STRPindahMilik_NS.rdf",
                "STRPeratusPindahMilik_NS.rdf",
                //"STRPerinciPindahMilik_NS.rdf",
                "STRBukuDaftar_NS.rdf",
                "STRLaporanPenguatkuasaan_NS.rdf",
                "STRPenamatanStrata_NS.rdf",
                "STRBayarKelulusan_NS.rdf",
                "STRStatusHakmilik_NS.rdf",
                "STRBdnUrus_NS.rdf"               
            };
        }

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if (pengguna.getKodCawangan() != null) {
            kodCaw = pengguna.getKodCawangan().getKod();
        }
        doCollectKaunter();
        doFilterDaerahBPM();
        doFilterKodTransaksi();
//        LOG.info("kodCaw :"+kodCaw);
//        getPenggunaCaw(kodCaw);
        return new ForwardResolution("/laporan/laporan_param_strata.jsp").addParameter("popup", "true");
    }

    public Resolution doCollectKaunter() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            kodCaw = cawKod;
        }
        String report = getContext().getRequest().getParameter("reportNama");
        if (report != null) {
            reportName = report;
        }
        LOG.info("kodCaw :" + kodCaw);
        if (!senaraiKaunter.isEmpty()) {
            senaraiKaunter.clear();
            LOG.info("senaraiKaunter : is Empty now");
        }
        if (reportName != null) {
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
            getPenggunaCaw(kodCaw);
        }
        return new ForwardResolution("/laporan/laporan_param_strata.jsp").addParameter("popup", "true");
    }

    public Resolution doFilterDaerahBPM() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            kodCaw = cawKod;
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
        return new ForwardResolution("/laporan/laporan_param_strata.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param_strata.jsp").addParameter("popup", "true");
    }

    private void getPenggunaCaw(String caw) {
        List<Pengguna> senaraiPguna = new ArrayList<Pengguna>();
        String[] name = {"kodCawangan.kod"};
        Object[] value = {caw};
        senaraiPguna = penggunaDAO.findByEqualCriterias(name, value, "idPengguna");
        for (Pengguna pguna : senaraiPguna) {
            if (pguna.getStatus() == null) {
                continue;
            }
            if (pguna.getStatus().getKod().equals("A")) // A=aktif
            {
                senaraiPenggunaCaw.add(pguna);
            }
        }
        LOG.info("senaraiPenggunaCaw.size :" + senaraiPenggunaCaw.size());
    }

    private void doFilterKodTransaksi() {
        String sql = "FROM etanah.model.KodTransaksi kt WHERE kt.kod IN (:kod1, :kod2, :kod3) ORDER BY kt.kod asc";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("kod1", khconf.getProperty("cukaiTanahSemasa"));
        q.setString("kod2", khconf.getProperty("cukaiTanahTunggakan"));
        q.setString("kod3", khconf.getProperty("dendaLewat"));
        senaraiKodTransaksi = q.list();
        LOG.info("doFilterKodTransaksi : senaraiKodTransaksi :" + senaraiKodTransaksi.size());
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
