package com.theta.portal.stripes;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.RefUserTypeDAO;
import com.wideplay.warp.persist.Transactional;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.Employee;
import com.theta.portal.model.RefUserType;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;

import com.theta.portal.service.session.LoginSession;
import com.theta.portal.service.session.SessionManager;
import com.theta.portal.stripes.helpdesk.HelpDeskActionBean;
import com.theta.portal.stripes.util.EmailUtil;
import com.theta.portal.stripes.util.GeneratePwd;
import com.theta.portal.stripes.util.ReportUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/helpdesk/login")
public class LoginActionBean extends WelcomeActionBean {

    UserTable pengguna;
    Employee employee;
    @Validate(required = true, label = "ID Pengguna")
    private String userLogin;
    @Validate(required = true, label = "Kata Laluan")
    private String userPassword;
    private boolean fLogin;
    @Inject
    Configuration conf;
    @Inject
    private UserManager userManager = new UserManager();

    private static Logger LOG = Logger.getLogger(LoginActionBean.class);
    @Inject
    private EmailUtil emailUtil;
    @Inject
    private GeneratePwd generatePwd;
    @Inject
    RefUserTypeDAO refUserTypeDAO;

    private String kataLaluan;
    private String question1;
    private String answer1;
    private String question2;
    private String answer2;
    private String question3;
    private String answer3;
    private String imageOnline;
    private String frasaRahsia;

    private String noPengenalan;
    private String nama;
    private Date tarikhMula;
    private Date tarikhAkhir;

    private String namaBank = "";
    private String noAkaun = "";
    private String noTelefon = "";
    private String emel = "";
    private String alamat = "";
    private String faks = "";
    private String version = "";
    private String tarikhBuild = "";

    @Inject
    private ReportUtil reportUtil;

    @DefaultHandler
    @DontValidate
    public Resolution showForm() {
        getContext().getRequest().setAttribute("front", true);
        String kodNegeri = conf.getProperty("state");
        version = conf.getProperty("version");
        tarikhBuild = conf.getProperty("build.date");
        if (kodNegeri != null) {
//            phk = userManager.findHubungiKami(kodNegeri);
        }
        pengguna = getContext().getCurrentUser();
        if (pengguna != null) {
            return new RedirectResolution(HelpdeskMainActionBean.class);
        }
        return new JSP("/helpdesk/login.jsp");
    }

    public Resolution login() {
        String kodNegeri = conf.getProperty("state");
        if (kodNegeri != null) {
//            phk = userManager.findHubungiKami(kodNegeri);
        }
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    @DontValidate
    public Resolution prelogin() {
        if (StringUtils.isNotBlank(getUserLogin())) {
            pengguna = userManager.findUserId(getUserLogin());
            if (pengguna == null) {
                addSimpleError("Pengguna ini perlu didaftarkan terlebih dahulu!!");
                pengguna = new UserTable();
                getContext().getRequest().setAttribute("front", true);
            }

        } else {
            addSimpleError("Sila Masukkan ID Pengguna!!");
            pengguna = new UserTable();
            getContext().getRequest().setAttribute("front", true);
        }
        String kodNegeri = conf.getProperty("state");
        if (kodNegeri != null) {
//            phk = userManager.findHubungiKami(kodNegeri);
        }
        return new JSP("/helpdesk/login.jsp");
    }

    @ValidationMethod
    @Transactional
    public void validate_login() {

        if (!userManager.authenticate(getUserLogin(), getUserPassword())) {
            pengguna = userManager.findUserId(getUserLogin());
            employee = userManager.findByPengguna(pengguna);

        } else {
            pengguna = userManager.findUserId(getUserLogin());
            employee = userManager.findByPengguna(pengguna);

            SessionManager sm = SessionManager.getInstance();
            LoginSession ls = sm.getActiveSessionByUserId(getUserLogin());
            if (ls != null) {
                addSimpleError("User \"" + getUserLogin() + "\" "
                        + "has been log in at " + formatSDF(ls.getDateCreated()));
                return;
            }
            String a = Boolean.FALSE.toString();
            String b = Boolean.FALSE.toString();
            String c = Boolean.FALSE.toString();
            String d = Boolean.FALSE.toString();
            String nonuser = Boolean.FALSE.toString();
            for (UserRole u : pengguna.getUserRoleList()) {
                System.out.println(u.getTypeId().getTypeId());
                RefUserType type = refUserTypeDAO.findById(u.getTypeId().getTypeId());
                Long ab = type.getTypeId();
                if (ab==Long.parseLong("14")) {
                    a = Boolean.TRUE.toString();
                    nonuser = Boolean.TRUE.toString();
                } else if (type.getTypeId().equals("")) {
                    b = Boolean.TRUE.toString();
                } else if (ab==Long.parseLong("13")) {
                    c = Boolean.TRUE.toString();
                    nonuser = Boolean.TRUE.toString();
                } else if (ab==Long.parseLong("15")) {
                    d = Boolean.TRUE.toString();
                    nonuser = Boolean.TRUE.toString();
                }
            }
//if(Integer.parseInt(a))
            HttpSession ses = context.getRequest().getSession();
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.USER_ID_KEY, pengguna);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.USER_ID_DETAIL, employee);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.AGIH_GRP, a);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.USER_GRP, b);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.PTEKNIKAL_GRP, c);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.VENDOR_GRP, d);
            ses.setAttribute(com.theta.portal.stripes.config.ableActionBeanContext.ACCESS_LEVEL, nonuser);

            sm.addSession(ses.getId(), getUserLogin(), new Date(),
                    context.getRequest().getRemoteAddr(), context.getRequest().getRemoteHost());

        }
    }

    @DontValidate
    public Resolution register() {
        return new JSP("register.jsp");
    }

    private String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        LoginActionBean.LOG = LOG;
    }

    public EmailUtil getEmailUtil() {
        return emailUtil;
    }

    public void setEmailUtil(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    public GeneratePwd getGeneratePwd() {
        return generatePwd;
    }

    public void setGeneratePwd(GeneratePwd generatePwd) {
        this.generatePwd = generatePwd;
    }

    public boolean isfLogin() {
        return fLogin;
    }

    public void setfLogin(boolean fLogin) {
        this.fLogin = fLogin;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getKataLaluan() {
        return kataLaluan;
    }

    public void setKataLaluan(String kataLaluan) {
        this.kataLaluan = kataLaluan;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getImageOnline() {
        return imageOnline;
    }

    public void setImageOnline(String imageOnline) {
        this.imageOnline = imageOnline;
    }

    public String getFrasaRahsia() {
        return frasaRahsia;
    }

    public void setFrasaRahsia(String frasaRahsia) {
        this.frasaRahsia = frasaRahsia;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(Date tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFaks() {
        return faks;
    }

    public void setFaks(String faks) {
        this.faks = faks;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTarikhBuild() {
        return tarikhBuild;
    }

    public void setTarikhBuild(String tarikhBuild) {
        this.tarikhBuild = tarikhBuild;
    }

    public UserTable getPengguna() {
        return pengguna;
    }

    public void setPengguna(UserTable pengguna) {
        this.pengguna = pengguna;
    }

}
