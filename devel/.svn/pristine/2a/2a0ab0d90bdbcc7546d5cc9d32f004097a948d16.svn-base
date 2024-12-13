/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
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

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/laporanConvertHakmilik")
public class LaporanConvertHakmilik extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LaporanConvertHakmilik.class);
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiConvertHakmilik;
    private String[] senaraiConvertHakmilikRN;
    private String reportName;
    private String report;
    private List<String> listYear = new ArrayList<String>();
    private Pengguna peng;
    private String kodNegeri;
    private List<Pengguna> listPguna;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/common/laporan_convert_hakmilik.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");
        senaraiConvertHakmilik = new String[]{"Laporan TukarGanti Hakmilik"};
        // Report Name
        senaraiConvertHakmilikRN = new String[]{"ETMIS84_1NS.rdf"};

        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        report = getContext().getRequest().getParameter("report");
        listPguna = penggunaDao.findAll();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/common/laporan_convert_param.jsp").addParameter("popup", "true");
    }

    public String[] getSenaraiConvertHakmilik() {
        return senaraiConvertHakmilik;
    }

    public void setSenaraiConvertHakmilik(String[] senaraiConvertHakmilik) {
        this.senaraiConvertHakmilik = senaraiConvertHakmilik;
    }

    public String[] getSenaraiConvertHakmilikRN() {
        return senaraiConvertHakmilikRN;
    }

    public void setSenaraiConvertHakmilikRN(String[] senaraiConvertHakmilikRN) {
        this.senaraiConvertHakmilikRN = senaraiConvertHakmilikRN;
    }

    public List<Pengguna> getListPguna() {
        return listPguna;
    }

    public void setListPguna(List<Pengguna> listPguna) {
        this.listPguna = listPguna;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
