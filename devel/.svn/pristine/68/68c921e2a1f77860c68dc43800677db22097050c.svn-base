package etanah.service.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import etanah.Configuration;
import etanah.model.Pengguna;

public class LoginListener implements HttpSessionListener {
    
    private static Logger LOG = Logger.getLogger(LoginListener.class);
    
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        LOG.debug("session created with ID=" + e.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        HttpSession s = e.getSession();
        Pengguna p = (Pengguna) s.getAttribute(etanah.view.etanahActionBeanContext.KEY_USER);
        if (p != null){
            LOG.debug("sesion to be destroyed " + p.getIdPengguna() + " created " + s.getCreationTime());
            SessionManager.getInstance().removeSession(s.getId());
        }
    }

}
