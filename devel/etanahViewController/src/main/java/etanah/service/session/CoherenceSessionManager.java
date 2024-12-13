package etanah.service.session;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import etanah.service.session.LoginSession.LOGIN_DIRECTIVE;
import java.util.Set;

/**
 * Using load-balanced, failsafe coherence cluster to store Session objects.
 * Based on implementation of {@link DefaultSessionManager}.
 * @author hishammk
 *
 */
public class CoherenceSessionManager extends SessionManager {

    protected static NamedCache cache;
    // near cache, with unlimited backing store, expires after 1d
    private static final String _KEY = "unear-eTanah_WL_SESSION";
    
    
    static {
        if ("true".equals(System.getProperty("etanah/coherence"))) {
            // return instance of NamedCache
            LOG.info("Using coherence to store session");
            cache = CacheFactory.getCache(_KEY);        
        }
        else {
            LOG.info("Coherence is not enabled! Please enable coherence by setting coherence=true");
            throw new ExceptionInInitializerError("Coherence not explicitly enabled for deployment!");
        }
    }
    
    @Override
    public LoginSession addSession(String sessionId, String userId, Date dateCreated,  String IPAddr, String computerName) {
        LoginSessionImpl l = new LoginSessionImpl();
        l.sessionId = sessionId;
        l.userId = userId;
        l.dateCreated = dateCreated;
        l.IPAddr = IPAddr;
        l.computerName = computerName;
        
        cache.lock(sessionId);
        try {
            cache.put(sessionId, l);
        } finally {
            cache.unlock(sessionId);
        }
        
        return l;
    }
    
    @Override
    public void killSession(String sessionId){
        cache.lock(sessionId);
        try {
            LoginSession l = (LoginSession) cache.get(sessionId);
            if (l != null){
                l.setDirective(LOGIN_DIRECTIVE.ADMIN_LOGOUT);
                // put back, to make the cache sync
                cache.put(sessionId, l);
            }
        } finally {
            cache.unlock(sessionId);
        }
    }

    @Override
    public void removeSession(String sessionId) {
        cache.lock(sessionId);
        try {
            cache.remove(sessionId);
        } finally {
            cache.unlock(sessionId);
        }
    }

    @Override
    public LoginSession getSession(String sessionId) {
        return (LoginSession) cache.get(sessionId);
    }
    
    @Override
    public LoginSession getActiveSessionByUserId(String userId){
        for (Entry<String, LoginSession> e : (Set<Entry<String, LoginSession>>) cache.entrySet()){
            LoginSession s = e.getValue();
            if (s!= null && s.getDirective() == LOGIN_DIRECTIVE.NONE && userId.equals(s.getUserId())){
                return e.getValue();
            }
        }
        
        return null;
    }

    @Override
    public List<LoginSession> getSessions() {
        ArrayList l = new ArrayList<LoginSession>(cache.size());
        for (String sessionId : (Set<String>) cache.keySet()){
            l.add(cache.get(sessionId));
        }
        
        Collections.sort(l, new Comparator<LoginSession>(){
            @Override
            public int compare(LoginSession a, LoginSession b) {
                return a.getUserId().compareTo(b.getUserId());
            }
        });

        return l;
    }
    
    @Override
    public List<LoginSession> getActiveSessions() {
        ArrayList l = new ArrayList<LoginSession>(cache.size());
        for (String sessionId : (Set<String>)cache.keySet()){
            LoginSession s = (LoginSession) cache.get(sessionId);
            if (s == null)
                LOG.warn("found null session entry for " + sessionId);
            if (s != null && s.getDirective() != LOGIN_DIRECTIVE.ADMIN_LOGOUT)
                l.add(s);
        }
        
        Collections.sort(l, new Comparator<LoginSession>(){
            @Override
            public int compare(LoginSession a, LoginSession b) {
                return a.getUserId().compareTo(b.getUserId());
            }
        });

        return l;
    }

    @Override
    public void clear(){
        cache.clear();
    }

}
