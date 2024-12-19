/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.KodPeranan;
import etanah.model.LogPenggunaApplikasi;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.service.common.LelongService;
import etanah.service.session.LoginSession;
import etanah.service.session.SessionManager;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author solahuddin/wan.fairul
 */
@HttpCache(allow = false)
@UrlBinding("/uam/all_user")
public class AllUserActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AllUserActionBean.class);
    private Pengguna pguna;
    private List<ListAllPengguna> userList;
    @Inject
    private UamService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    PenggunaDAO penggunaDao;
    private boolean flag = false;
    private String kod_caw;
    private String kod_jab;
    private String[] senaraiCarianPengguna;
    private String[] senaraiCarianPenggunaRN;
    private String reportName;
    private String report;
    private Pengguna peng;
    private List<Pengguna> listPguna;
    private List<LoginSession> sessionlist;
    private boolean equalSession = false;
    private boolean melaka = false;
    private List<KodPeranan> listPP = new ArrayList<KodPeranan>();

    @DefaultHandler
    public Resolution showForm() {
        showReports();
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new JSP("uam/AllUser.jsp");
    }

    public Resolution perananByJabatan() {
        listPP = new ArrayList<KodPeranan>();
        listPP = service.findPerananByJab(kod_jab);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new JSP("uam/AllUser.jsp");
    }

    public Resolution searchUser() throws NamingException {
        SessionManager sm = SessionManager.getInstance();
        sessionlist = sm.getActiveSessions();
        String sesiPguna;
        listPP = service.findPerananByJab(kod_jab);
        if (pguna != null) {
            if (StringUtils.isNotBlank(pguna.getIdPengguna())) {
//                boolean check = service.checkUser(pguna.getIdPengguna());
                boolean check = pguna.getIdPengguna().length() > 3 ? true : false;
                if (check) {
                    List<Pengguna> lp = service.searchingUserList(pguna.getIdPengguna());
                    if (!lp.isEmpty()) {
                        userList = new ArrayList();
                        
                        for (Pengguna p : lp) {
                            ListAllPengguna lip = new ListAllPengguna();
                            lip.setPengguna(p);
                            userList.add(lip);
                        }

                        for (int k = 0; k < userList.size(); k++) {
                            for (int m = 0; m < sessionlist.size(); m++) {
                                if (userList.get(k).getPengguna().getIdPengguna().equalsIgnoreCase(sessionlist.get(m).getUserId())) {
                                    userList.get(k).setStatusOnline(true);

                                }
                                userList.get(k).setTarikhAkhirAktiviti(find(userList.get(k).getPengguna().getIdPengguna()));
                            }
                        }
                        setFlag(true);
                        showReports();
                        return new ForwardResolution("/WEB-INF/jsp/uam/AllUser.jsp");
                    } else {
                        showReports();
                        addSimpleError("Pengguna Tidak Wujud");
                        return new ForwardResolution("/WEB-INF/jsp/uam/AllUser.jsp");
                    }
                } else {
                    showReports();
                    addSimpleError("Pengguna Tidak Wujud");
                    return new ForwardResolution("/WEB-INF/jsp/uam/AllUser.jsp");
                }
            }
        }
        listPguna = service.findAll(getContext().getRequest().getParameterMap());
        userList = new ArrayList<ListAllPengguna>();
        for (int k = 0; k < listPguna.size(); k++) {
//            userList = new ArrayList();
            ListAllPengguna lip = new ListAllPengguna();
            lip.setPengguna(listPguna.get(k));
            LoginSession ls = sm.getActiveSessionByUserId(listPguna.get(k).getIdPengguna());
            if (ls != null) {
                lip.setStatusOnline(true);
            } else {
                lip.setStatusOnline(false);
            }
            lip.setTarikhAkhirAktiviti(find(listPguna.get(k).getIdPengguna()));
            userList.add(lip);
        }
//        SessionManager sm = SessionManager.getInstance();

        setFlag(true);
        showReports();
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/AllUser.jsp");
    }

    public Date find(String idPengguna) {
        List<LogPenggunaApplikasi> logPguna = service.findLogApp(idPengguna);
        //  List<LogPenggunaApplikasi> logPguna = null;
        Date tarikh = logPguna.isEmpty() ? null : logPguna.get(0).getTarikhMasuk();
        return tarikh;
    }

    public Resolution deletePengguna() throws NamingException {

        String idPengguna = getContext().getRequest().getParameter("idPengguna");
        if (idPengguna != null) {
            try {
                service.deleteUser(idPengguna);

            } catch (Exception e) {
                addSimpleError("Pengguna Tidak Dapat Dihapuskan.");
            }
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        addSimpleError("Pengguna berjaya dihapuskan.");
        return new RedirectResolution("/etanah/uam/all_user", false).flash(this);
    }

    private void showReports() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        logger.info("showReport:start");
        senaraiCarianPengguna = new String[]{"Senarai Carian Pengguna"};
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            // Report Name
            senaraiCarianPenggunaRN = new String[]{"UAMCarianPguna_MLK.rdf"};
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            // Report Name
            senaraiCarianPenggunaRN = new String[]{"UAMCarianPguna_NS.rdf"};
        }
        logger.info("showReport:finish");
    }

    public Resolution requestParam() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        reportName = getContext().getRequest().getParameter("namaReport");
        report = getContext().getRequest().getParameter("report");
        listPguna = penggunaDao.findAll();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_pguna_param.jsp").addParameter("popup", "true");
    }

    public Resolution perananByJabatanPop() {
        kod_jab = getContext().getRequest().getParameter("jabatan");
        kod_caw = getContext().getRequest().getParameter("kod_caw");
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        listPP = new ArrayList<KodPeranan>();
        listPP = service.findPerananByJab(kod_jab);
//        reportName = getContext().getRequest().getParameter("namaReport");
//        report = getContext().getRequest().getParameter("report");
        listPguna = penggunaDao.findAll();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_pguna_param.jsp").addParameter("popup", "true");
    }

    public Resolution viewReport() {
        String add = conf.getProperty("report.server.location");
        String SERVER_LOCATION = "";
        logger.info("report.server.location : " + add);
        logger.info("reportName : " + reportName);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }

        String id_pguna = getContext().getRequest().getParameter("id_pguna");
        String kod_caw = getContext().getRequest().getParameter("kod_caw");
        String kodjab = getContext().getRequest().getParameter("kod_jab");
        String kod_peranan = getContext().getRequest().getParameter("kod_peranan");
        String status = getContext().getRequest().getParameter("status");

        String reportKey = conf.getProperty("report.key");
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
        if (StringUtils.isNotBlank(id_pguna)) {
            cmd.append("&").append("p_id_pguna").append("=").append(id_pguna);
        }
        if (StringUtils.isNotBlank(kod_caw)) {
            cmd.append("&").append("p_kod_caw").append("=").append(kod_caw);
        }
        if (StringUtils.isNotBlank(kodjab)) {
            cmd.append("&").append("p_kod_jab").append("=").append(kodjab);
        }
        if (StringUtils.isNotBlank(kod_peranan)) {
            cmd.append("&").append("p_kod_peranan").append("=").append(kod_peranan);
        }
        if (StringUtils.isNotBlank(status)) {
            cmd.append("&").append("p_status").append("=").append(status);
        }
        logger.info("SERVER REPORT : " + cmd);
        String url = cmd.toString();
//        return new ForwardResolution("/WEB-INF/jsp/uam/laporan_view.jsp").addParameter("popup", "true");
        return new StreamingResolution("text/plain", url);
    }

    public List<Pengguna> getListPguna() {
        return listPguna;
    }

    public void setListPguna(List<Pengguna> listPguna) {
        this.listPguna = listPguna;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
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

    public String[] getSenaraiCarianPengguna() {
        return senaraiCarianPengguna;
    }

    public void setSenaraiCarianPengguna(String[] senaraiCarianPengguna) {
        this.senaraiCarianPengguna = senaraiCarianPengguna;
    }

    public String[] getSenaraiCarianPenggunaRN() {
        return senaraiCarianPenggunaRN;
    }

    public void setSenaraiCarianPenggunaRN(String[] senaraiCarianPenggunaRN) {
        this.senaraiCarianPenggunaRN = senaraiCarianPenggunaRN;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKod_caw() {
        return kod_caw;
    }

    public void setKod_caw(String kod_caw) {
        this.kod_caw = kod_caw;
    }

    public String getKod_jab() {
        return kod_jab;
    }

    public void setKod_jab(String kod_jab) {
        this.kod_jab = kod_jab;
    }

    public List<LoginSession> getSessionlist() {
        return sessionlist;
    }

    public void setSessionlist(List<LoginSession> sessionlist) {
        this.sessionlist = sessionlist;
    }

    public boolean isEqualSession() {
        return equalSession;
    }

    public void setEqualSession(boolean equalSession) {
        this.equalSession = equalSession;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public List<KodPeranan> getListPP() {
        return listPP;
    }

    public void setListPP(List<KodPeranan> listPP) {
        this.listPP = listPP;
    }
}
