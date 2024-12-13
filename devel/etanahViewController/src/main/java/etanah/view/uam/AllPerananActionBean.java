/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodPerananDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodPeranan;
import etanah.model.PenggunaPeranan;
import etanah.service.common.LelongService;
import etanah.service.uam.UamService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
@HttpCache(allow = false)
@UrlBinding("/uam/peranan")
public class AllPerananActionBean extends AbleActionBean {

    @Inject
    private KodPerananDAO kodPerananDAO;
    @Inject
    private UamService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    PenggunaDAO penggunaDao;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AllPerananActionBean.class);
    private String kod;
    private KodPeranan kodPeranan;
    private List<PenggunaPeranan> listPengguna;
    private List<KodPeranan> senaraiPeranan;
    private boolean flag = false;
    private String[] senaraiPerananKump;
    private String[] senaraiPerananKumpRN;
    private String reportName;
    private String report;
    private boolean melaka = false;

    @DefaultHandler
    public Resolution showForm() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        showReports();
        return new JSP("uam/peranan.jsp");
    }

    public Resolution viewPengguna() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        kod = (String) getContext().getRequest().getParameter("kod");
        kodPeranan = kodPerananDAO.findById(kod);
        if (kodPeranan != null) {
            listPengguna = kodPeranan.getSenaraiPengguna();
        }
        return new JSP("uam/pengguna.jsp").addParameter("popup", "true");
    }

    public Resolution searchPeranan() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        senaraiPeranan = service.findPeranan(getContext().getRequest().getParameterMap());
        setFlag(true);
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/uam/peranan.jsp");
    }

    private void showReports() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        logger.info("showReport:start");
        senaraiPerananKump = new String[]{"Senarai Kumpulan Pengguna",
            "Senarai Pengguna Berdasarkan Kumpulan Pengguna"};

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            // Report Name
            senaraiPerananKumpRN = new String[]{"UAMSenaraiPeranan_MLK.rdf",
                "UAMSenaraiPguna_MLK.rdf"};
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            // Report Name
            senaraiPerananKumpRN = new String[]{"UAMSenaraiPeranan_NS.rdf",
                "UAMSenaraiPguna_NS.rdf"};
        }
        logger.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        report = getContext().getRequest().getParameter("report");
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_peranan_param.jsp").addParameter("popup", "true");
    }

    public Resolution viewReport() {
        String add = conf.getProperty("report.server.location");
        String reportKey = conf.getProperty("report.key");
        String SERVER_LOCATION = "";
        logger.info("report.server.location : " + add);
        logger.info("reportName : " + reportName);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        String kod_caw = getContext().getRequest().getParameter("kod_caw");
        String kod_peranan = getContext().getRequest().getParameter("kod_peranan");
        
        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            logger.info("host name : " + lelongService.getIP(add));
            logger.info("ip address : " + address.getHostAddress());
            if (conf.getProperty("kodNegeri").equals("04")) {
                //hard code
               SERVER_LOCATION = "http://172.17.250.225:9003/reports/rwservlet";
            } else {
                SERVER_LOCATION = lelongService.replaceDNS(address.getHostAddress(), add);
            }
            
        } catch (UnknownHostException ex) {
            logger.info("Error-" + ex);
        }
        
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName);
        if (StringUtils.isNotBlank(kod_caw)) {
            cmd.append("&").append("p_kod_caw").append("=").append(kod_caw);
        }
        if (StringUtils.isNotBlank(kod_peranan)) {
            cmd.append("&").append("p_kod_peranan").append("=").append(kod_peranan);
        }
        logger.info("SERVER REPORT : " + cmd);
        String url = cmd.toString();
//        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_view.jsp").addParameter("popup", "true");
        return new StreamingResolution("text/plain", url);
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String[] getSenaraiPerananKump() {
        return senaraiPerananKump;
    }

    public void setSenaraiPerananKump(String[] senaraiPerananKump) {
        this.senaraiPerananKump = senaraiPerananKump;
    }

    public String[] getSenaraiPerananKumpRN() {
        return senaraiPerananKumpRN;
    }

    public void setSenaraiPerananKumpRN(String[] senaraiPerananKumpRN) {
        this.senaraiPerananKumpRN = senaraiPerananKumpRN;
    }

    public List<KodPeranan> getSenaraiPeranan() {
        return senaraiPeranan;
    }

    public void setSenaraiPeranan(List<KodPeranan> senaraiPeranan) {
        this.senaraiPeranan = senaraiPeranan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public List<PenggunaPeranan> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<PenggunaPeranan> listPengguna) {
        this.listPengguna = listPengguna;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }
}
