package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * @author abdul.hakim
 */

@UrlBinding("/hasil/logOff")
public class LogOffActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(LogOffActionBean.class);
    
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private String kodCaw;
    private List<String> listYear = new ArrayList<String>();
    private List<String> senaraiKaunter = new ArrayList<String>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    private etanah.Configuration conf;
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        logger.info("showForm");
        changeAccount();
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/common/spoc_laporan.jsp");
    }

    private void showReports() {
        logger.info("showReport:start");
        String DiffReport = null;
        if ("05".equals(conf.getProperty("kodNegeri"))) {                       //for Negeri Sembilan
            DiffReport = "HSL_Penyata_Pemungut_NS.rdf";
            logger.debug("Laporan Negeri Sembilan");
        } else if ("04".equals(conf.getProperty("kodNegeri"))) {             // for Negeri Melaka
            DiffReport = "SPOCPenyataPemungut(BankIn).rdf";
            logger.debug("Laporan Negeri Melaka");
        }
        senaraiReport = new String[]{
                    "Laporan Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                    "Laporan Senarai Terimaan",
                    "Laporan Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim",
                    "Laporan Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
                    "Laporan Penyata Pemungut",};

        senaraiReportName = new String[]{
                    "HSL_R_01.rdf",
                    "HSL_R_02.rdf",
                    "HSL_R_03.rdf",
                    "HSL_R_04.rdf",
                    DiffReport};
        logger.info("showReport:finish");
    }
    
    public void changeAccount() throws ParseException{
        logger.info("..:: changeAccount START ::..");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("pengguna.getIdPengguna() : "+pengguna.getIdPengguna());
        logger.info("pengguna.getIdKaunter() : "+pengguna.getIdKaunter());
        String idKaunter = pengguna.getIdKaunter();
        Date now = new Date();
        String tarikh = sdf.format(now);
        Date tt = sdf.parse(tarikh);
        logger.info("idKaunter : "+idKaunter);
        List<DokumenKewangan> senaraiDK = new ArrayList<DokumenKewangan>();

        String sql = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.infoAudit.tarikhMasuk LIKE to_date(current_date,'dd/MM/yyyy')";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        List<DokumenKewangan> dkList = q.list();
        logger.info("dkList.size() : "+dkList.size());
        for (DokumenKewangan dk : dkList) {
            logger.info("dk.getInfoAudit().getTarikhMasuk() : "+dk.getInfoAudit().getTarikhMasuk());
            String d = sdf.format(dk.getInfoAudit().getTarikhMasuk());
            logger.info("d : "+d);

            if(d.equals(tarikh)){
                logger.info("dk.getIdDokumenKewangan() : "+dk.getIdDokumenKewangan());
                String kaunter = dk.getIdDokumenKewangan().substring(10, 12);
                logger.info("kaunter : "+kaunter);
                if((idKaunter.equals(kaunter))&&(dk.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod()))){
                    senaraiDK.add(dk);
                }
            }
        }
        logger.info("senaraiDK.size() : "+senaraiDK.size());
        logger.info("..:: changeAccount FINISH ::..");
    }


    public Resolution requestParam() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        reportName = getContext().getRequest().getParameter("namaReport");
        if (pengguna.getKodCawangan() != null) {
            kodCaw = pengguna.getKodCawangan().getKod();
        }
        doCollectKaunter();
        return new ForwardResolution("/laporan/laporan_param.jsp").addParameter("popup", "true");
    }

    public Resolution doCollectKaunter() {
        String cawKod = getContext().getRequest().getParameter("kodCawangan");
        if (cawKod != null) {
            kodCaw = cawKod;
        }
        String report = getContext().getRequest().getParameter("reportNama");
        if (report != null) {
            reportName = report;
        }
        logger.info("kodCaw :" + kodCaw);
        if (!senaraiKaunter.isEmpty()) {
            senaraiKaunter.clear();
            logger.info("senaraiKaunter : is Empty now");
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
//                logger.debug(count+") senaraiKaunter :"+pguna);
                count++;
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
}