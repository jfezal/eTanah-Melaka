/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
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
 * @author ezat.amir
 */

@UrlBinding("/HasilLaporan_MLK")
public class LaporanHasilMelakaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanHasilMelakaActionBean.class);
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String kodCaw;
    private String kodDaerah;
    private String daerahHasil;
    private static String staticKodDaerah;
    private static String staticKodCaw;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna pengguna;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private List<String> senaraiKaunter = new ArrayList<String>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
   
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/hasil/laporan_hasil.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");

        senaraiReport = new String[]{
                    //Laporan Spoc = 18
                    
            "Ringkasan Denda Lewat Semasa"
                };

        senaraiReportName = new String[]{
                    //laporan Spoc = 18
                    "HSL_R_61_MLK.rdf"
                    
                };

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if(pengguna.getKodCawangan().getDaerah() != null){
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
            kodCaw = pengguna.getKodCawangan().getKod();
            LOG.info("(requestParam) kodDaerah :"+kodDaerah);
            LOG.info("(requestParam) kodCaw :"+kodCaw);
            staticKodDaerah = kodDaerah;
            staticKodCaw = kodCaw;
            doFilterBPM();
            doCollectKaunter();
            doFilterDaerahBPM();            
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");      
    }

    public Resolution doCollectKaunter(){
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if(kodCaw != null)
            kodCaw = cawKod;
        String namaReport = getContext().getRequest().getParameter("reportNama");
        if(namaReport != null)
            reportName = namaReport;
        LOG.info("kodCaw :"+kodCaw);

        if(kodCaw == null)
            kodCaw = staticKodCaw;

        if(!senaraiKaunter.isEmpty()){
            senaraiKaunter.clear();
            LOG.info("senaraiKaunter : is Empty now");
        }
        if(kodCaw != null){
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
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
    }
    
    public Resolution doFilterDaerahBPM(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if(cawKod != null)
            kodCaw = cawKod;
        LOG.info("(doFilterDaerahBPM) kodCaw :"+kodCaw);

        if(kodCaw == null)
            kodCaw = staticKodCaw;

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
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_melaka.jsp").addParameter("popup", "true");
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

        if(kodDaerah == null)
            kodDaerah = staticKodDaerah;

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
        LaporanHasilMelakaActionBean.staticKodCaw = staticKodCaw;
    }

    public static String getStaticKodDaerah() {
        return staticKodDaerah;
    }

    public static void setStaticKodDaerah(String staticKodDaerah) {
        LaporanHasilMelakaActionBean.staticKodDaerah = staticKodDaerah;
    }
}