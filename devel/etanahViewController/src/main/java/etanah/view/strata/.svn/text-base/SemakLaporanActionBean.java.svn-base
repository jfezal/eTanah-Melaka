package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/strata/semakLaporan")
public class SemakLaporanActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    private String SERVER_LOCATION = "";
    private String reportKey;
    private String reportName = "";
    private String iframeURL = "";
    private String arahan = "";

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/Ruang_Udara/hal_halLain.jsp").addParameter("tab", "true");
    }

    public Resolution PPRUS() {
        reportName = "STRLaporanTanahPermitRuangUdara_MLK.rdf";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        SERVER_LOCATION = conf.getProperty("report.server.location");
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        System.out.println("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
arahan = "Sila semak laporan yang disediakan";
        return new JSP("strata/common/semakLaporanTanah.jsp").addParameter("tab", "true").addParameter("arahan", arahan);
    }

    public Resolution PPRUSJana() {
        reportName = "STR_LaporanTanah_PermitRuangUdara_MLK.rdf";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        SERVER_LOCATION = conf.getProperty("report.server.location");
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        System.out.println("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
arahan = "Sila semak laporan yang disediakan dan tekan butang jana dokumen";
        return new JSP("strata/common/semakLaporanTanah.jsp").addParameter("tab", "true").addParameter("arahan", arahan);
    }
    public String getSERVER_LOCATION() {
        return SERVER_LOCATION;
    }

    public void setSERVER_LOCATION(String SERVER_LOCATION) {
        this.SERVER_LOCATION = SERVER_LOCATION;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getArahan() {
        return arahan;
    }

    public void setArahan(String arahan) {
        this.arahan = arahan;
    }

    
}
