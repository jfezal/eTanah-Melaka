package etanah.view.stripes.hasil;

import java.util.List;

import org.apache.log4j.Logger;

import etanah.service.session.SessionManager;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import etanah.model.Pengguna;
import etanah.service.session.LoginSession;
import etanah.view.etanahActionBeanContext;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author abdul.hakim
 * 
 */

@UrlBinding("/hasil/mod_kutip")
public class ModKutipService extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(ModKutipService.class);
    private static final boolean debug = LOG.isDebugEnabled();

    private String modKutip = "";
    private String session;
    private String tempSession = "";
    private List<LoginSession> sessionList;

    // try other solution
    private String sess= "";
    private String tempSess= "F";

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    public String getTempSess() {
        return tempSess;
    }

    public void setTempSess(String tempSess) {
        this.tempSess = tempSess;
    }

    

    @DefaultHandler
    public Resolution view(){
        SessionManager sm = SessionManager.getInstance();
        sessionList = sm.getActiveSessions();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();

        for (LoginSession ls : sessionList) {
            LOG.info(ls.getSessionId()+" "+ls.getUserId());
            if(pengguna.getIdPengguna().equals(ls.getUserId())){
                session = ls.getSessionId();
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/session.jsp");
    }

    // other solution
    public Resolution checkSession(){
        String mod = "";
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        HttpSession s = ctx.getRequest().getSession();

        sess = (String) s.getAttribute("modKutip");
        if(sess !=null){
            mod = "T"+sess;
            return new StreamingResolution("text/plain", mod);
        }else{
            mod = "F";
            return new StreamingResolution("text/plain", mod);
        }
    }

    public Resolution retrieveSession() {
        SessionManager sm = SessionManager.getInstance();
        sessionList = sm.getActiveSessions();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String results = "T";
        String kutip = "";

        for (LoginSession ls : sessionList) {
            LOG.info(ls.getSessionId()+" "+ls.getUserId());
            if(pengguna.getIdPengguna().equals(ls.getUserId())){
                session = ls.getSessionId();
            }
        }
        if(!tempSession.equals(session)){
            tempSession = session;
            results = "F";
            kutip = results+modKutip;
        }else{
            results = "T";
            kutip = results+modKutip;}

        LOG.debug("results : " + kutip);
        return new StreamingResolution("text/plain", kutip);
    }

    public Resolution saveModToSession1() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        HttpSession s = ctx.getRequest().getSession();
        s.removeAttribute("modKutip");
        return new StreamingResolution("text/plain", sess);
    }

    public void savePenyerahToSession1(etanahActionBeanContext ctx, String m) {
        HttpSession s = ctx.getRequest().getSession();
        s.removeAttribute("modKutip");
    }

    public Resolution saveModToSession() {
        LOG.debug("-------------------------");
        String mod = getContext().getRequest().getParameter("mode");
        LOG.debug("mod : "+mod);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        modKutip = mod;
        savePenyerahToSession(ctx);

        return new StreamingResolution("text/plain", modKutip);
    }
    
    public void savePenyerahToSession(etanahActionBeanContext ctx) {
        HttpSession s = ctx.getRequest().getSession();
        s.setAttribute("modKutip", modKutip);
    }

    public String loadPenyerahFromSession(etanahActionBeanContext ctx) {
        HttpSession s = ctx.getRequest().getSession();
        modKutip = (String) s.getAttribute("modKutip");
        return modKutip;
    }

    public String getModKutip() {
        return modKutip;
    }

    public void setModKutip(String modKutip) {
        this.modKutip = modKutip;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<LoginSession> getSessionList() {
        return sessionList;
    }

    public String getTempSession() {
        return tempSession;
    }

    public void setTempSession(String tempSession) {
        this.tempSession = tempSession;
    }
    
}
