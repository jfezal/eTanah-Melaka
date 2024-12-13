/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

//import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;

import etanah.dao.PenggunaDAO;
import java.util.Date;
import etanah.model.Pengguna;
import etanah.dao.PenggunaPerananDAO;
import etanah.ldap.LDAPManager;
import etanah.model.KodStatusPengguna;
import etanah.service.session.LoginSession;
import etanah.service.session.SessionManager;
import etanah.service.uam.PasswordExp;
import etanah.service.uam.UamService;
import etanah.view.BasicTabActionBean;
import etanah.view.uam.PengesahanKataLaluan;
import etanah.view.uam.ViewMohonPgunaData;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.SimpleError;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;

/**
 *
 * @author amir.muhaimin
 */
@UrlBinding("/first_login")
public class FirstTimeLoginBean extends BasicTabActionBean {

    private static final Logger logger = Logger.getLogger(FirstTimeLoginBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private static final String VIEW = "/WEB-INF/jsp/login/firstTime_login.jsp";
    // private static final String ROOT = "/WEB-INF/jsp/pendaftaran/";
    PenggunaDAO penggunaDao;
    PenggunaPerananDAO penggunaPerananDao;
    KodPerananDAO kodPerananDOA;
    // public Pengguna pengguna = new Pengguna();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String[] besar = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] kecik = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private String[] nombor = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    @Inject
    UamService uam;

    public boolean checkPass(String str) {
        boolean s = false;
        for (int i = 0; i < besar.length; i++) {
            s = str.contains(besar[i]);
            if (s) {
                break;
            }
        }
        if (s) {
            for (int t = 0; t < kecik.length; t++) {
                s = str.contains(kecik[t]);
                if (s) {
                    break;
                }
            }
        }
        if (s) {
            for (int n = 0; n < nombor.length; n++) {
                s = str.contains(nombor[n]);
                if (s) {
                    break;
                }
            }
        }

        return s;
    }

    @Inject
    public FirstTimeLoginBean(PenggunaDAO penggunaDao,
            PenggunaPerananDAO penggunaPerananDao, KodPerananDAO kodPerananDOA) {
        this.penggunaDao = penggunaDao;
        this.penggunaPerananDao = penggunaPerananDao;
        this.kodPerananDOA = kodPerananDOA;
    }
    @Inject
    etanah.Configuration conf;

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        getContext().getRequest().getSession().setAttribute("version", conf.getProperty("version"));
        return new ForwardResolution(VIEW);
    }

    public Resolution login() {

        logger.debug("##############login############");

        Pengguna peng = penggunaDao.findById(IDPengguna);//        return new ForwardResolution("/WEB-INF/jsp/uam/pengesahan_katalaluan.jsp");
//        return new RedirectResolution(MainActionBean.class);
        if (peng.getPerananUtama() == null || peng.getPerananUtama().getDefaultScreen() == null) {
                    return new RedirectResolution(MainActionBean.class);
                } else {
                    return new RedirectResolution("/" + peng.getPerananUtama().getDefaultScreen());
                }
//        return new ForwardResolution("/WEB-INF/jsp/main/main.jsp");
    }
    @Validate(required = true)
    private String IDPengguna;

    public String getIDPengguna() {
        return IDPengguna;
    }

    public void setIDPengguna(String IDPengguna) {
        this.IDPengguna = IDPengguna;
    }
    @Validate(required = true)
    private String kataLaluan;

    public String getKataLaluan() {
        return kataLaluan;
    }

    public void setKataLaluan(String kataLaluan) {
        this.kataLaluan = kataLaluan;
    }
//    ******
    @Validate(required = true)
    private String kataLaluanB;

    public String getKataLaluanB() {
        return kataLaluanB;
    }

    public void setKataLaluanB(String kataLaluanB) {
        this.kataLaluanB = kataLaluanB;
    }
//    ***********
    @Validate(required = true)
    private String pengKatalaluanB;

    public String getPengKatalaluanB() {
        return pengKatalaluanB;
    }

    public void setPengKatalaluanB(String pengKatalaluanB) {
        this.pengKatalaluanB = pengKatalaluanB;
    }

    public void update() {
        Pengguna peng = penggunaDao.findById(IDPengguna);
        KodStatusPengguna ksp = new KodStatusPengguna();
        ksp.setKod("A");
        peng.setTarikhAkhirLogin(new Date());
        peng.setStatus(ksp);
        peng.setTarikhKemaskiniKatalaluan(new Date());
        uam.savePengguna(peng);
    }

    public Resolution reset() {
        return new ForwardResolution(FirstTimeLoginBean.class);
    }

    @ValidationMethod
    public void validateUser(ValidationErrors errors) throws WorkflowException, NamingException {

        Pengguna peng1 = penggunaDao.findById(IDPengguna);
        if (peng1 != null) {
            try {
                // if user does not exists on LDAP
                // then we must not allow it
                if (!LDAPManager.isValidUser(IDPengguna, kataLaluan)) {
                    peng1 = null;
                }
            } catch (Exception e) {
                logger.info("User LDAP validation fail");
                peng1 = null;
            }
        }

        if (peng1 == null) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_FAIL");

        } else if (kataLaluan.equals(kataLaluanB)) {
            errors.add("KataLaluan baru", new SimpleError(
                    "Katalaluan baru yang dimasukkan sama dengan lama. Sila masukkan katalaluan lain."));
            auditLogin("PASS_FAIL");

        } else if (!checkPass(kataLaluanB)) {
            errors.add("KataLaluan baru", new SimpleError(
                    "Katalaluan baru yang dimasukkan tidak mempunyai kombinasi huruf besar, huruf kecil, nombor dan minimun 8 aksara. Sila masukkan katalaluan lain."));
            auditLogin("PASS_FAIL");

        } else if (!pengKatalaluanB.equals(kataLaluanB)) {
            errors.add("KataLaluan baru", new SimpleError(
                    "Katalaluan baru yang dimasukkan tidak sama dengan pengesahan katalaluan. Sila masukkan katalaluan lain."));
            auditLogin("PASS_FAIL");

        } else if (peng1.getStatus() == null || !"B".equals(peng1.getStatus().getKod())) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_INACTIVE");

        } else {
//            for single login session
            LDAPManager ldapManager = new LDAPManager();
            ldapManager.changePassword(IDPengguna, kataLaluanB);

            SessionManager sm = SessionManager.getInstance();
            LoginSession ls = sm.getActiveSessionByUserId(IDPengguna);
            if (ls != null) {
                errors.add("IDPengguna", new SimpleError("Pengguna \"" + IDPengguna + "\" "
                        + "telah pun log masuk pada " + ls.getDateCreated() + ". Hubungi Pentadbir "
                        + "sistem untuk bantuan."));
                auditLogin("MULTIPLE_LOGIN_ATTEMPT");
                return;
            }

            auditLogin("USER_OK");

            if (peng1.getTarikhAkhirLogin() == null) {
                peng1.setTarikhAkhirLogin(new java.util.Date());
            }

            HttpSession ses = context.getRequest().getSession();
            ses.setAttribute(etanah.view.etanahActionBeanContext.KEY_USER, peng1);

            sm.addSession(ses.getId(), peng1.getIdPengguna(), new Date(),
                    context.getRequest().getRemoteAddr(), context.getRequest().getRemoteHost());
            update();
        }

    }

    private void auditLogin(String status) {
        // in case getting request information failed
        String str = String.format("LOGIN [USER:%s] [STATUS:%s]", IDPengguna,
                status);
        try {
            HttpServletRequest req = getContext().getRequest();
            str += String.format(" [IP:%s,%d] [ClientIP:%s] [SSL:%b] [UA:%s]",
                    req.getRemoteAddr(), req.getRemotePort(),
                    req.getHeader("ClientIP"), req.isSecure(),
                    req.getHeader("User-Agent"));
        } catch (Exception e) {
            logger.error(e);
        } finally {
            syslog.info(str);
        }
    }
}
