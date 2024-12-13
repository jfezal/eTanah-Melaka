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
@UrlBinding("/strata/laporanstrataTG")
public class StrataTukargantiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(StrataTukargantiActionBean.class);
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
    private List<String> senaraiPgunaKkini = new ArrayList<String>();
    private List<String> senaraiPgunaMasuk = new ArrayList<String>();
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
//        getPenggunaCaw(pengguna.getKodCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/strata_laporanTG.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        String DiffReport = null;
        String DiffReport2 = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            senaraiReport = new String[]{
                        "Laporan Keseluruhan Tukarganti Hakmilik Strata",
                        "Laporan Harian Tukarganti Hakmilik Strata"
                    };
            senaraiReportName = new String[]{
                        "STRLaporanKeseluruhanTG_MLK.rdf",
                        "STRLaporanHarianTG_MLK.rdf"
                    };
        }
        else{
            senaraiReport = new String[]{
                        "Laporan Keseluruhan Tukarganti Hakmilik Strata",
                        "Laporan Harian Tukarganti Hakmilik Strata"
                    };
            senaraiReportName = new String[]{
                        "STRLaporanKeseluruhanTG_NS.rdf",
                        "STRLaporanHarianTG_NS.rdf"
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


        getPgunaMasuk();
        for (int x = 0; x < senaraiPgunaMasuk.size(); x++) {
            String pguna = senaraiPgunaMasuk.get(x);
            Pengguna pg = penggunaDAO.findById(pguna);
            if (pg.getKodCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                senaraiPenggunaCaw.add(pg);
            }
        }
        getPgunaKkini();
        for (int x = 0; x < senaraiPgunaKkini.size(); x++) {
            String pguna = senaraiPgunaKkini.get(x);
            Pengguna pg = penggunaDAO.findById(pguna);
            if (pg.getKodCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                LOG.info("senaraiPenggunaCaw1++++" + senaraiPenggunaCaw);
                int count = 0;
                if (senaraiPenggunaCaw.size() > 0) {
                    for (int z = 0; z < senaraiPenggunaCaw.size(); z++) {
                        if (!pg.getIdPengguna().equals(senaraiPenggunaCaw.get(z).getIdPengguna())) {
                            count++;
//                        senaraiPenggunaCaw.add(pg);
                        }
                        if (count == senaraiPenggunaCaw.size()) {
                            senaraiPenggunaCaw.add(pg);
                        }
                    }
                } else {
                    senaraiPenggunaCaw.add(pg);
                }
            }
        }
        LOG.info("senaraiPenggunaCaw++++" + senaraiPenggunaCaw);

//        getPenggunaCaw(pengguna.getKodCawangan().getKod());
//        doCollectKaunter();
//        doFilterDaerahBPM();
//        doFilterKodTransaksi();
//        LOG.info("kodCaw :"+kodCaw);
//        getPenggunaCaw(kodCaw);
        return new ForwardResolution("/laporan/laporan_param_strataTG.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param_strataTG.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param_strataTG.jsp").addParameter("popup", "true");
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
        return new ForwardResolution("/laporan/laporan_param_strataTG.jsp").addParameter("popup", "true");
    }

    private void getPenggunaCaw(String caw) {
        List<Pengguna> senaraiPguna = new ArrayList<Pengguna>();
        String[] name = {"kodCawangan.kod"};
        Object[] value = {caw};
        senaraiPguna = penggunaDAO.findByEqualCriterias(name, value, "idPengguna");

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        sql = "select p from Pengguna p, PenggunaPeranan pp where p.idPengguna = pp.pengguna.idPengguna"
                + " and pp.peranan.kod in ('7','23','22') and p.idPengguna not like 'jvco%' and p.idPengguna not like 'ptg%' and p.idPengguna not like 'kpt%'"
                + " and p.status.kod = 'A' and p.kodCawangan.kod = :caw";
        q = s.createQuery(sql);
        q.setString("caw", caw);
        senaraiPenggunaCaw = q.list();

        LOG.info("senaraiPenggunaCaw.size :" + senaraiPenggunaCaw.size());
    }

    private void getPgunaMasuk() {

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        sql = "select distinct tg.infoAudit.dimasukOleh.idPengguna from HakmilikTukarGantiStrata tg where tg.infoAudit.dimasukOleh is not null";
        q = s.createQuery(sql);
        senaraiPgunaMasuk = q.list();
        LOG.info("senaraiPgunaMasuk.size :" + senaraiPgunaMasuk.size());

    }

    private void getPgunaKkini() {

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        sql = "select distinct tg.infoAudit.dikemaskiniOleh.idPengguna from HakmilikTukarGantiStrata tg where tg.infoAudit.dikemaskiniOleh is not null";
        q = s.createQuery(sql);
        senaraiPgunaKkini = q.list();

        LOG.info("senaraiPgunaKkini.size :" + senaraiPgunaKkini.size());
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

    public List<String> getSenaraiPgunaKkini() {
        return senaraiPgunaKkini;
    }

    public void setSenaraiPgunaKkini(List<String> senaraiPgunaKkini) {
        this.senaraiPgunaKkini = senaraiPgunaKkini;
    }

    public List<String> getSenaraiPgunaMasuk() {
        return senaraiPgunaMasuk;
    }

    public void setSenaraiPgunaMasuk(List<String> senaraiPgunaMasuk) {
        this.senaraiPgunaMasuk = senaraiPgunaMasuk;
    }
}
