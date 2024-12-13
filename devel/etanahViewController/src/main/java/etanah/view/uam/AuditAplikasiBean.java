/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.AuditData;
import etanah.model.LogPenggunaApplikasi;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.*;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author zahidaziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/auditAplikasi")
public class AuditAplikasiBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuditAplikasiBean.class);
    private boolean flag = false;
    private List<LogPenggunaApplikasi> logApp;
    private String idPengguna;
    private Date tarikhDari;
    private Date tarikhHingga;
    private Integer paparan = 20;
    private Calendar cd;
    private Date currDate;
    @Inject
    private UamService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    private boolean melaka = false;
    @Inject StrataPtService strataService;
    String reportName;
    @DefaultHandler
    public Resolution showForm() {
        if (conf.getProperty("kodNegeri").equals("04")) {
             reportName = "UAMAuditAplikasi_MLK.rdf";
            melaka = true;
        } else {
            reportName = "UAMAuditAplikasi_NS.rdf";
            melaka = false;
        }
        return new JSP("uam/auditAplikasi.jsp");
    }

    public Resolution findAuditAplikasi() throws Exception {

        paparan = (Integer) this.getPaparan();

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        cd = Calendar.getInstance();
        currDate = cd.getTime();
        String date = formatDate.format(currDate);
        logger.debug("###>>" + date + "<<###");

        logApp = service.findAuditApp(getContext().getRequest().getParameterMap());

        logger.debug("No of list found - " + logApp.size());
        if (logApp.size() > 0) {
            setFlag(true);
        } else {
            addSimpleError("Maaf, data tidak wujud.");
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/auditAplikasi.jsp");
    }

    public Resolution viewPopup() {
                if (conf.getProperty("kodNegeri").equals("04")) {
             reportName = "UAMAuditAplikasi_MLK.rdf";
            melaka = true;
        } else {
            reportName = "UAMAuditAplikasi_NS.rdf";
            melaka = false;
        }
        return new JSP("uam/auditAplikasiPopup.jsp").addParameter("popup", true);
    }

    public Resolution viewReport() {
        String add = conf.getProperty("report.server.location");
        String reportKey = conf.getProperty("report.key");
        String SERVER_LOCATION = "";
        logger.info("report.server.location : " + add);
    
       
        if (conf.getProperty("kodNegeri").equals("04")) {
             reportName = "UAMAuditAplikasi_MLK.rdf";
            melaka = true;
        } else {
            reportName = "UAMAuditAplikasi_NS.rdf";
            melaka = false;
        }
         logger.info("reportName : " + reportName);
        String p_id_pengguna = getContext().getRequest().getParameter("p_id_pengguna");
        String p_tarikh_dari = getContext().getRequest().getParameter("p_tarikh_dari");
        String p_tarikh_hingga = getContext().getRequest().getParameter("p_tarikh_hingga");

        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            logger.info("host name : " + lelongService.getIP(add));
            logger.info("ip address : " + address.getHostAddress());
            SERVER_LOCATION = lelongService.replaceDNS(address.getHostAddress(), add);
        } catch (UnknownHostException ex) {
            logger.info("Error-" + ex);
        }

        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName);
        if (StringUtils.isNotBlank(idPengguna)) {
            cmd.append("&").append("p_id_pguna").append("=").append(idPengguna);
        }
        if (tarikhDari!=null) {
            cmd.append("&").append("p_trh_dari").append("=").append(strataService.formatSDF(tarikhDari));
        }
        if (tarikhHingga!=null) {
            cmd.append("&").append("p_trh_hingga").append("=").append(strataService.formatSDF(tarikhHingga));
        }
        logger.info("SERVER REPORT : " + cmd);
        String url = cmd.toString();
//        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_view.jsp").addParameter("popup", "true");
        return new StreamingResolution("text/plain", url);
    }

    public Integer getPaparan() {
        return paparan;
    }

    public void setPaparan(Integer paparan) {
        this.paparan = paparan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public List<LogPenggunaApplikasi> getLogApp() {
        return logApp;
    }

    public void setLogApp(List<LogPenggunaApplikasi> logApp) {
        this.logApp = logApp;
    }

    public Date getTarikhDari() {
        return tarikhDari;
    }

    public void setTarikhDari(Date tarikhDari) {
        this.tarikhDari = tarikhDari;
    }

    public Date getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(Date tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    
}
