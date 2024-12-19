package etanah.view.session;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import etanah.service.session.LoginSession;
import etanah.service.session.SessionManager;
import etanah.service.session.LoginSession.LOGIN_DIRECTIVE;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import able.stripes.JSP;

@UrlBinding ("/sesi/senarai")
public class SessionList extends AbleActionBean {
    
    private List<LoginSession> sessionList;
    
    private String sessionToKill;
    
    private static Logger LOG = Logger.getLogger(SessionList.class);
    private static boolean debug = LOG.isDebugEnabled();

    public List<LoginSession> getSessionList() {
        return sessionList;
    }

    public void setSessionToKill(String sessionToKill) {
        this.sessionToKill = sessionToKill;
    }

    public String getSessionToKill() {
        return sessionToKill;
    }

    @DefaultHandler
    public Resolution view(){
        SessionManager sm = SessionManager.getInstance();
        sessionList = sm.getActiveSessions();
                        
        return new ForwardResolution("/WEB-INF/jsp/sesi/senarai.jsp");
    }
    
    public Resolution killSession(){
        if (sessionToKill == null || sessionToKill.length() == 0){
            addSimpleError("Sila pilih sesi untuk ditamatkan");
        }
        
        LOG.info("menamatkan sesi " + sessionToKill);

        SessionManager sm = SessionManager.getInstance();
        LoginSession l = sm.getSession(sessionToKill);
        
        // kill the session in session context, deprecated, not sure if working
        /*
        getContext().getRequest().getSession().getSessionContext().
                getSession(l.getSessionId()).invalidate();
        */
        
        if (l != null){
            LOG.info("menamatkan sesi " + l.getUserId());
            sm.killSession(sessionToKill);
            
            addSimpleMessage("Sesi " + l.getUserId() + " telah ditamatkan.");
        } else{
            addSimpleMessage("Sesi " + sessionToKill + " tidak dijumpai.");
        }
        
        return view();
    }
    
}
