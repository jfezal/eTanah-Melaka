/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.model.Pengguna;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.service.session.SessionManager;
import etanah.view.BasicTabActionBean;

import java.util.Date;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author khairil
 */
@UrlBinding("/logout")
public class LogoutActionBean extends BasicTabActionBean {

    private static final Logger logger = Logger.getLogger(LogoutActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");

    public PenggunaDAO pengDAO;

    @Inject
    public LogoutActionBean(PenggunaDAO pengDAO) {
        this.pengDAO = pengDAO;
    }

    @DefaultHandler
    public Resolution logout() {
        HttpSession ses = context.getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute("_KEY_USER");
        if (pengguna != null) {
            Pengguna pengDB = pengDAO.findById(pengguna.getIdPengguna());
            auditLogout(pengDB.getIdPengguna());
            
            update(pengDB);

            SessionManager sm = SessionManager.getInstance();
            sm.removeSession(ses.getId());
        } else {
            auditLogout(null);
        }

        if (ses != null) {
            ses.invalidate();
        }

        return new RedirectResolution(LoginActionBean.class);
    }

    @Transactional
    public void update(HttpSession ses) {

        Pengguna pengguna = (Pengguna) ses.getAttribute("_KEY_USER");
        if (pengguna != null) {
            Pengguna pengDB = pengDAO.findById(pengguna.getIdPengguna());
            pengDB.setTarikhAkhirLogin(new Date());

            InfoAudit a = pengDB.getInfoAudit();
            a.setDiKemaskiniOleh(pengguna);
            a.setTarikhKemaskini(new Date());
            pengDB.setInfoAudit(a);

            pengDAO.update(pengDB);
        }

    }
    
    @Transactional
    public void update(Pengguna pengguna) {
        if (pengguna != null) {
            Pengguna pengDB = pengDAO.findById(pengguna.getIdPengguna());
            pengDB.setTarikhAkhirLogin(new Date());

            InfoAudit a = pengDB.getInfoAudit();
            a.setDiKemaskiniOleh(pengguna);
            a.setTarikhKemaskini(new Date());
            pengDB.setInfoAudit(a);

            pengDAO.update(pengDB);
        }
    }
    
    private void auditLogout(String pguna) {
        // in case getting request information failed
        String str = String.format("LOGOUT [USER:%s]", pguna);
        try {
            HttpServletRequest req = getContext().getRequest();
            str += String.format(" [IP:%s,%d] [ClientIP:%s] [SSL:%b] [UA:%s]",
                    req.getRemoteAddr(), req.getRemotePort(),
                    req.getHeader("Proxy-Client-IP"), req.isSecure(),
                    req.getHeader("User-Agent"));
        } catch (Exception e) {
            logger.error(e);
        } finally {
            syslog.info(str);
        }
    }

}
