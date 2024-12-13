/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes;

import com.theta.portal.model.UserTable;
import com.theta.portal.service.session.SessionManager;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/logout")
public class LogoutActionBean extends BaseActionBean {
    
   
    
    @DefaultHandler
    public Resolution logout() {
        HttpSession ses = context.getRequest().getSession();
        //return new RedirectResolution(WelcomeActionBean.class);
        UserTable pengguna = getContext().getCurrentUser();       
        
        
         SessionManager sm = SessionManager.getInstance();
            sm.removeSession(ses.getId());
        _logout();
        return new RedirectResolution(HelpdeskMainActionBean.class);
        
    }

    private void _logout() {
        getContext().logout();
        addMessage("loggedOut");
    }
}