package com.theta.portal.service.session;

import com.theta.portal.model.UserTable;
import com.theta.portal.stripes.config.ableActionBeanContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;

public class LoginListener implements HttpSessionListener {
    
    private static Logger LOG = Logger.getLogger(LoginListener.class);   
    
    
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        LOG.debug("session created with ID=" + e.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        HttpSession s = e.getSession();
        UserTable p = (UserTable) s.getAttribute(com.theta.portal.stripes.config.ableActionBeanContext.USER_ID_KEY);
        if (p != null){
            LOG.debug("sesion to be destroyed " + p.getUserName() + " created " + s.getCreationTime());
            SessionManager.getInstance().removeSession(s.getId());
        }
    }

}
