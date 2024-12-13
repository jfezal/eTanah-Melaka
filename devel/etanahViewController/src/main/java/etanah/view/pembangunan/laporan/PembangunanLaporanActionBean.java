/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.laporan;

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
 * @author syafiq adam
 */
@UrlBinding("/dev/laporanpembangunan")
public class PembangunanLaporanActionBean extends AbleActionBean {
    
    private static final Logger LOG = Logger.getLogger(PembangunanLaporanActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String urusan;
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
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/laporan/pembangunan_laporan.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
        String DiffReport2 = null;
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
            senaraiReport = new String[]{
                "Laporan Permohonan Tukar Syarat Kelulusan PTD (TSPTD)",
                "Laporan Permohonan Tukar Syarat Kelulusan PTG (TSPTG)",
                "Laporan Permohonan Tukar Syarat Kelulusan TSMMK (TSMMK)",
                "Laporan Permohonan Penyatuan Tanah (PYTN)",
                "Laporan Permohonan Pecah Bahagi (PPCB)",
                "Laporan Permohonan Pecah Bahagi Seksyen 141A (PPCBA)",
                "Laporan Permohonan Pecah Sempadan (PPCS)",
                "Laporan Permohonan Tukar Syarat Pecah Sempadan Serentak (TSPSS)",
                "Laporan Permohonan Serahbalik Pohon Semula (SBPS)",
                "Laporan Permohonan Serahbalik Berimilik Semula (SBMS)",
                "Laporan Permohonan Serahbalik Seluruh Tanah (PSMT)",
                "Laporan Permohonan Serahbalik Sebahagian Tanah (PSBT)",
                "Laporan Rayuan Lanjut Tempoh Bayaran (RLTB)",
                "Laporan Rayuan Pertimbangan Semula (RPS)",
                "Laporan Rayuan Pengurangan Premium (RPP)",
                "Statistik Untuk Setiap Urusan (Statistik)",
                "Laporan Kemajuan Bulanan (Laporan)"
            };
            senaraiReportName = new String[]{
                "dev_laporan_tsptd.rdf",
                "dev_laporan_tsptg.rdf",
                "dev_laporan_tsmmk.rdf",
                "dev_laporan_pytn.rdf",
                "dev_laporan_ppcb.rdf",
                "dev_laporan_ppcba.rdf",
                "dev_laporan_ppcs.rdf",              
                "dev_laporan_tspss.rdf",
                "dev_laporan_sbps.rdf",
                "dev_laporan_sbms.rdf",
                "dev_laporan_psmt.rdf",
                "dev_laporan_psbt.rdf",
                "dev_laporan_rltb.rdf",
                "dev_laporan_rps.rdf",
                "dev_laporan_rpp.rdf",
                "dev_laporan_statistik.rdf",
                "dev_laporan_kemajuanbulanan.rdf"
            };
        }

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        urusan = getContext().getRequest().getParameter("urusan");
        int mula = urusan.indexOf("(");
        int akhir = urusan.indexOf(")");
        urusan = urusan.substring(mula+1, akhir);
        System.out.println("URUSAN : "+urusan);
        if (pengguna.getKodCawangan() != null) {
            kodCaw = pengguna.getKodCawangan().getKod();
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/laporan/laporan_param_pembangunan.jsp").addParameter("popup", "true");
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

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }
    
}
