/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/laporanHasilKutipan_NS")
public class HasilLaporanKutipanAgensi extends AbleActionBean {




    private static final Logger LOG = Logger.getLogger(HasilLaporanKutipanAgensi.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private List<String> senaraiKodAgensi = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        requestParam();
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_kutipan.jsp");
    }

//    private void showReports() {
//        LOG.info("showReport:start");
//
//            senaraiReport = new String[]{"Laporan Ringkasan Senarai Kutipan Agensi"
//                        };
//
//            senaraiReportName = new String[]{"HSL_SEN_RKA.rdf"
//                        };
//
//        LOG.info("showReport:finish");
//    }



    public Resolution requestParam() {
        reportName = "HSL_SEN_RKA.rdf" ;
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param_kutipan.jsp").addParameter("popup", "true");
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

    public List<String> getSenaraiKodAgensi() {
        return senaraiKodAgensi;
    }

    public void setSenaraiKodAgensi(List<String> senaraiKodAgensi) {
        this.senaraiKodAgensi = senaraiKodAgensi;
    }


}
