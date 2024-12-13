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
import etanah.model.AuditDataId;
import etanah.service.common.LelongService;
import etanah.service.uam.UamService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
 * @author zahidaziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/auditMedan")
public class AuditMedanBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuditMedanBean.class);
// private String idAudit;
    private AuditDataId idAuditData;
    private String jadual;
// private String medan;
    private String aktiviti;
    private String idPengguna;
    private boolean flag = false;
    private Date tarikhDari;
    private int paparan = 20;
    private int bilPapar;
    private List<AuditData> auditMedanList;
    private Date tarikhHingga;
    private Date currDate;
    private Calendar cd;
    private Pengguna pguna;
    @Inject
    private UamService service;
    @Inject
    private etanah.Configuration conf;
    private boolean melaka = false;
    @Inject
    LelongService lelongService;
 String reportName;
    @DefaultHandler
    public Resolution showForm() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
             reportName = "UAMAuditMedan_MLK.rdf";
        } else {
            melaka = false;
                reportName = "UAMAuditMedan_NS.rdf";
        }
        return new JSP("uam/auditMedan.jsp");
    }

    public Resolution findAudit() throws Exception {
if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
             reportName = "UAMAuditMedan_MLK.rdf";
        } else {
            melaka = false;
                reportName = "UAMAuditMedan_NS.rdf";
        }
        paparan = (Integer) this.getPaparan();


        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        cd = Calendar.getInstance();
        currDate = cd.getTime();
        String date = formatDate.format(currDate);
        logger.debug("###>>" + date + "<<###");

        logger.debug(currDate);
        logger.debug(new Date());
        auditMedanList = service.findAuditMedan(getContext().getRequest().getParameterMap());
        logger.debug("masa"+auditMedanList.get(0).getMasa());
        if (auditMedanList.size() > 0) {
            setFlag(true);
        } else {
            addSimpleError("Maaf, data tidak wujud.");
        }


        return new ForwardResolution("/WEB-INF/jsp/uam/auditMedan.jsp");



    }

    public Resolution viewReport() {
        String add = conf.getProperty("report.server.location");
        String reportKey = conf.getProperty("report.key");
        String SERVER_LOCATION = "";
        logger.info("report.server.location : " + add);
       
        logger.info("reportName : " + reportName);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
             reportName = "UAMAuditMedan_MLK.rdf";
        } else {
            melaka = false;
                reportName = "UAMAuditMedan_NS.rdf";
        }
        String idAudit = getContext().getRequest().getParameter("idAudit");
       

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
        if (StringUtils.isNotBlank(idAudit)) {
            cmd.append("&").append("p_id_audit").append("=").append(idAudit);
        }
        logger.info("SERVER REPORT : " + cmd);
        String url = cmd.toString();
//        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_view.jsp").addParameter("popup", "true");
        return new StreamingResolution("text/plain", url);
    }

    public AuditDataId getIdAuditData() {
        return idAuditData;
    }

    public void setIdAuditData(AuditDataId idAuditData) {
        this.idAuditData = idAuditData;
    }

    public Date getTarikhDari() {
        return tarikhDari;
    }

    public void setTarikhDari(Date tarikhDari) {
        this.tarikhDari = tarikhDari;
    }

    public Date getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Date currDate) {
        this.currDate = currDate;
    }

    public Calendar getCd() {
        return cd;
    }

    public void setCd(Calendar cd) {
        this.cd = cd;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Date getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(Date tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getJadual() {
        return jadual;
    }

    public void setJadual(String jadual) {
        this.jadual = jadual;
    }

    public String getAktiviti() {
        return aktiviti;
    }

    public void setAktiviti(String aktiviti) {
        this.aktiviti = aktiviti;
    }

    public List<AuditData> getAuditMedanList() {
        return auditMedanList;
    }

    public void setAuditMedanList(List<AuditData> auditMedanList) {
        this.auditMedanList = auditMedanList;
    }

    public int getPaparan() {
        return paparan;
    }

    public void setPaparan(int paparan) {
        this.paparan = paparan;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    
}