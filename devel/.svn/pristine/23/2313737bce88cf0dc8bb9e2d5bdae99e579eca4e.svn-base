/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/laporanPrestasiReg")
public class LaporanPrestasiRegActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanPrestasiRegActionBean.class);
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private List<String> listYear = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/common/laporanPrestasi.jsp");
    }

       private void showReports() {
        LOG.info("showReport:start");

            senaraiReport = new String[]{"Laporan Prestasi Kerani",
                        "Laporan Prestasi Kerani Kemasukan Mengikut Jenis Perserahan",
                        "Kemajuan Permohonan Permotongan Kaveat",
                        "Laporan Prestasi Unit Pendaftaran",
                        "Kemajuan Pendaftaran Hakmilik Hancur"};

            senaraiReportName = new String[]{"REGStat_1.rdf",
                        "REGStat_2.rdf",
                        "REGStat_3.rdf",
                        "REGStat_4.rdf",
                        "REGStat_5.rdf"};

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        return new ForwardResolution("/WEB-INF/jsp/common/laporanPrestasi_param.jsp").addParameter("popup", "true");
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
