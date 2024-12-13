/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

//import able.stripes.AbleActionBean;
import com.google.inject.Inject;

import etanah.dao.PenggunaDAO;
import java.util.Date;
import etanah.model.Pengguna;
import etanah.dao.PenggunaPerananDAO;
import etanah.ldap.LDAPManager;
import etanah.model.KodStatusPengguna;
import etanah.model.PenggunaPeranan;
import etanah.service.session.LoginSession;
import etanah.service.session.SessionManager;
import etanah.service.uam.PasswordExp;
import etanah.service.uam.UamService;
import etanah.view.BasicTabActionBean;
import java.text.SimpleDateFormat;
import java.util.List;

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
import javax.servlet.http.HttpSessionEvent;
import org.hibernate.Session;

/**
 * 
 * @author khairil
 */
@UrlBinding("/login")
public class LoginActionBean extends BasicTabActionBean {

    private static final Logger logger = Logger.getLogger(LoginActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private static final String VIEW = "/WEB-INF/jsp/login/login.jsp";
    private boolean flag = false;
    public static boolean sessionLogOut = false;
    private String kadPengenalan;
    // private static final String ROOT = "/WEB-INF/jsp/pendaftaran/";
    PenggunaDAO penggunaDao;
    PenggunaPerananDAO penggunaPerananDao;
    // public Pengguna pengguna = new Pengguna();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    HttpSessionEvent e;
    @Inject
    UamService uam;
    
    private String kodNegeri;

    @Inject
    public LoginActionBean(PenggunaDAO penggunaDao,
            PenggunaPerananDAO penggunaPerananDao) {
        this.penggunaDao = penggunaDao;
        this.penggunaPerananDao = penggunaPerananDao;
    }
    @Inject
    etanah.Configuration conf;

    @DefaultHandler
    @DontValidate
    public Resolution view() {
        getContext().getRequest().getSession().setAttribute("version", conf.getProperty("version"));
        kodNegeri = conf.getKodNegeri();        
        return new ForwardResolution(VIEW);
    }

    public Resolution login() {
         HttpSession ses = context.getRequest().getSession();
        logger.debug("##############login############");
        Pengguna peng = penggunaDao.findById(IDPengguna);
        PasswordExp pe = new PasswordExp();
        pe = uam.checkExpiry(IDPengguna);
        if (pe.isExp()) {
             SessionManager sm = SessionManager.getInstance();
            sm.removeSession(ses.getId());
                        KodStatusPengguna ksp = new KodStatusPengguna();
            ksp.setKod("X");
            peng.setStatus(ksp);
                 uam.savePengguna(peng);
            addSimpleError("Akaun anda telah disekat. Sila hubungi pentadbir sistem");
            return new ForwardResolution(VIEW);
        } else {
            if (pe.isReminder()) {
                logger.info("TRUe");
                addSimpleMessage("Katalaluan anda akan tamat dalam tempoh " + pe.getBil() + " hari. Sila tukar katalaluan anda");
                return new ForwardResolution("/WEB-INF/jsp/uam/pengesahan_katalaluan.jsp");

            } else {
                peng.setTarikhAkhirLogin(new Date());
                 uam.savePengguna(peng);
//                 logger.debug("30 min inactive session");
//                ses.setMaxInactiveInterval(30*60);
//                 
//                if (peng.getPerananUtama()!=null && peng.getPerananUtama().getKod().equals("6")) {
//                     logger.debug("admin never expired session");
//                     ses.setMaxInactiveInterval(-1);
//                } else {
//                     List<PenggunaPeranan> senaraiPeranan = peng.getSenaraiPeranan();
//                     for (PenggunaPeranan pp : senaraiPeranan) {
//                          if (pp !=null && pp.getPeranan().getKod().equals("6")) {
//                              logger.debug("admin never expired session");
//                              ses.setMaxInactiveInterval(-1);
//                              break;
//                          }
//                     }
//                }
                 
                if (peng.getPerananUtama() == null || peng.getPerananUtama().getDefaultScreen() == null) {
                    return new RedirectResolution(MainActionBean.class);
                } else {
                    return new RedirectResolution("/" + peng.getPerananUtama().getDefaultScreen());
                }
            }
        }

    }

    public Resolution logoutSession() {
        String id = getContext().getRequest().getParameter("IDPengguna");
        logger.debug("##############logout############");
        logger.debug("Id Pengguna hidden" + id);
        return new ForwardResolution(VIEW);
    }
    @Validate(required = true)
    private String IDPengguna;

    public String getIDPengguna() {
        return IDPengguna;
    }

    public void setIDPengguna(String IDPengguna) {
        this.IDPengguna = IDPengguna;
    }
    private String idPguna;

    public String getIdPguna() {
        return idPguna;
    }

    public void setIdPguna(String idPguna) {
        this.idPguna = idPguna;
    }
    @Validate(required = true)
    private String kataLaluan;

    public String getKataLaluan() {
        return kataLaluan;
    }

    public void setKataLaluan(String kataLaluan) {
        this.kataLaluan = kataLaluan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKadPengenalan() {
        return kadPengenalan;
    }

    public void setKadPengenalan(String kadPengenalan) {
        this.kadPengenalan = kadPengenalan;
    }

    public void update() {
        Pengguna peng = penggunaDao.findById(IDPengguna);
        peng.setTarikhAkhirLogin(new Date());
        penggunaDao.update(peng);
    }

    public Resolution reset() {
        return new ForwardResolution(LoginActionBean.class);
    }

    @ValidationMethod
    public void validateUser(ValidationErrors errors) throws WorkflowException {
        logger.debug("sessionLogout : " + sessionLogOut);
        Pengguna peng = penggunaDao.findById(IDPengguna);
//        if (sessionLogOut == true) {
//            logger.debug("--if--");
//            SessionManager sm = SessionManager.getInstance();
//            sm.killSession(IDPengguna);
//            sessionLogOut = false;
//            logger.debug("sessionLogout <false>: " + sessionLogOut);
//           //view();
//        } else {
        logger.debug("--else--");

        if (peng != null) {
            try {
                // if user does not exists on LDAP
                // then we must not allow it
                if (!LDAPManager.isValidUser(IDPengguna, kataLaluan)) {
                    if (peng != null) {
                        if (peng.getBilCubaan() != null) {
                            peng.setBilCubaan(peng.getBilCubaan() + 1);
                        } else {
                            peng.setBilCubaan(Long.parseLong("1"));
                        }
                    }
                    uam.savePengguna(peng);
                    peng = null;
                }
            } catch (Exception e) {
                logger.info("User LDAP validation fail");
                peng = null;
            }
        }
        if (peng == null) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_FAIL");
            peng = penggunaDao.findById(IDPengguna);


//            } else if (!LDAPManager.useLdap() && peng.getPassword() == null) {
//                errors.add("KataLaluan", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
//                auditLogin("PASS_NULL");

//            } else if (!LDAPManager.useLdap() && !peng.getPassword().equals(kataLaluan)) {
//                errors.add("KataLaluan", new SimpleError(
//                        "Maklumat anda untuk log masuk tidak sah"));
//                auditLogin("PASS_FAIL");

        } else if (peng.getStatus() == null || !"A".equals(peng.getStatus().getKod())) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_INACTIVE");

        } else if (peng.getBilCubaan() != null && peng.getBilCubaan() >= 3) {
            errors.add("IDPengguna", new SimpleError("Akaun anda telah disekat. Sila hubungi pentadbir sistem untuk mengaktifkan semula akaun"));
            KodStatusPengguna ksp = new KodStatusPengguna();
            ksp.setKod("X");
            peng.setStatus(ksp);
                 uam.savePengguna(peng);
            auditLogin("USER_INACTIVE");

        } else {
            SessionManager sm = SessionManager.getInstance();
            LoginSession ls = sm.getActiveSessionByUserId(IDPengguna);
            if (ls != null) {
                if (sessionLogOut) {
                    if (org.apache.commons.lang.StringUtils.isNotBlank(kadPengenalan) && peng.getNoPengenalan().equals(kadPengenalan)) {
                        sm.killSession(ls.getSessionId());
                    } else {
                        errors.add("IDPengguna", new SimpleError("Maklumat untuk \"" + IDPengguna + "\" "
                                + "tidak sah. Sila log masuk semula"));
//                        errors.add("IDPengguna", new SimpleError("No Kad Pengenalan untuk \"" + IDPengguna + "\" "
//                                + "tidak sah. Sila hubungi pentadbir sistem"));
                        setFlag(false);
                        sessionLogOut = false;
                    }
                } else {
                    errors.add("IDPengguna", new SimpleError("Pengguna \"" + IDPengguna + "\" "
                            + "telah pun log masuk pada " + formatSDF(ls.getDateCreated()) + ". Sila klik Log Keluar untuk keluar "
                            + "dari sistem terlebih dahulu atau sila masukkan No. Kad Pengenalan untuk memulakan sesi baru."));
                    setFlag(true);
                    auditLogin("MULTIPLE_LOGIN_ATTEMPT");

                    sessionLogOut = true;
                    return;
                }
            }
            auditLogin("USER_OK");
            peng.setBilCubaan(null);
            uam.savePengguna(peng);
            if (peng.getTarikhAkhirLogin() == null) {
                peng.setTarikhAkhirLogin(new java.util.Date());

            }

            HttpSession ses = context.getRequest().getSession();
            ses.setAttribute(etanah.view.etanahActionBeanContext.KEY_USER, peng);

            sm.addSession(ses.getId(), peng.getIdPengguna(), new Date(),
                    context.getRequest().getRemoteAddr(), context.getRequest().getRemoteHost());
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

    public String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

     public String getKodNegeri() {
          return kodNegeri;
     }

     public void setKodNegeri(String kodNegeri) {
          this.kodNegeri = kodNegeri;
     }
}
