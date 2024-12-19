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
@UrlBinding("/laporanKptreg")
public class KptregLaporanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KptregLaporanActionBean.class);
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
        return new ForwardResolution("/WEB-INF/jsp/common/kptreg_laporan.jsp");
    }

       private void showReports() {
        LOG.info("showReport:start");

            senaraiReport = new String[]{"Laporan Hakmilik Baru Mengikut Kategori Tanah (Mukim)",
                        "Laporan Hakmilik Baru Mengikut Kategori Tanah (Daerah)",
                        "Laporan Hakmilik Baru Mengikut Kategori Tanah (Negeri)",
                        "Laporan Hakmilik Mengikut Kategori Tanah (Mukim)",
                        "Laporan Hakmilik Mengikut Kategori Tanah (Daerah)",
                        "Laporan Hakmilik Mengikut Kategori Tanah (Negeri)",
                        "Laporan Hakmilik Mengikut Kategori Tanah Berdasarkan Tempoh (Mukim)",
                        "Laporan Hakmilik Mengikut Kategori Tanah Berdasarkan Tempoh (Daerah)",
                        "Laporan Hakmilik Mengikut Jenis Rezab (Mukim)",
                        "Laporan Hakmilik Mengikut Jenis Rezab (Daerah)",
                        "Laporan Keluasan Hakmilik Baru Mengikut Bangsa (Mukim)",
                        "Laporan Keluasan Hakmilik Baru Mengikut Bangsa (Daerah)",
                        "Laporan Keluasan Hakmilik Baru Mengikut Bangsa (Negeri)",
                        "Laporan Keluasan Hakmilik Mengikut Bangsa (Mukim)",
                        "Laporan Keluasan Hakmilik Mengikut Bangsa (Daerah)",
                        "Laporan Keluasan Hakmilik Mengikut Bangsa (Negeri)",
                        "Laporan Hakmilik Akibat Pengambilan Balik Tanah (Mukim)",
                        "Laporan Hakmilik Akibat Pengambilan Balik Tanah (Daerah)",
                        "Laporan Hakmilik Akibat Pengambilan Balik Tanah (Negeri)",
                        "Laporan Kelulusan Pecah Bahagian,Pecah Sempadan dan Pertukaran Syarat/Sekatan/Kategori Tanah (Mukim)",
                        "Laporan Kelulusan Pecah Bahagian,Pecah Sempadan dan Pertukaran Syarat/Sekatan/Kategori Tanah (Daerah)",
                        "Laporan Hakmilik Akibat Serah Balik Tanah (Mukim)",
                        "Laporan Hakmilik Akibat Serah Balik Tanah (Daerah)",
                        "Laporan Hakmilik Akibat Serah Balik Tanah (Negeri)",
                        "Laporan Hakmilik Sementara Yang Ditukar Menjadi Hakmilik Kekal (Mukim)",
                        "Laporan Hakmilik Sementara Yang Ditukar Menjadi Hakmilik Kekal (Daerah)",
                        "Laporan Hakmilik Yang Dihakis Air (Mukim)",
                        "Laporan Hakmilik Yang Dihakis Air (Daerah)"};

            senaraiReportName = new String[]{"MIS04_1.rdf",
                        "MIS04_2.rdf",
                        "MIS04_3.rdf",
                        "MIS05_1.rdf",
                        "MIS05_2.rdf",
                        "MIS05_3.rdf",
                        "MIS07_1.rdf",
                        "MIS07_2.rdf",
                        "MIS08_1.rdf",
                        "MIS08_2.rdf",
                        "MIS10_1.rdf",
                        "MIS10_2.rdf",
                        "MIS10_3.rdf",
                        "MIS11_1.rdf",
                        "MIS11_2.rdf",
                        "MIS11_3.rdf",
                        "MIS12_1.rdf",
                        "MIS12_2.rdf",
                        "MIS12_3.rdf",
                        "MIS13_1.rdf",
                        "MIS13_2.rdf",
                        "MIS15_1.rdf",
                        "MIS15_2.rdf",
                        "MIS15_3.rdf",
                        "MIS16_1.rdf",
                        "MIS16_2.rdf",
                        "MIS22_1.rdf",
                        "MIS22_2.rdf"};

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        return new ForwardResolution("/WEB-INF/jsp/common/kptreg_laporan_param.jsp").addParameter("popup", "true");
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
