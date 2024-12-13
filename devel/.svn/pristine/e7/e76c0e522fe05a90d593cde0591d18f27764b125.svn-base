package etanah.view.filter;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import able.guice.AbleContextListener;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;

import etanah.Configuration;
import etanah.model.Pengguna;
import etanah.view.etanahContextListener;

public class SecurityFilter implements Filter{
	
	public static final String DEFAULT_LOGIN_PATH = "/login";
	
	@Inject
    protected com.google.inject.Provider<Session> sessionProvider;
		
	private static String loginPath;
	
	private static String publicPath;
	
	private static final Logger LOG = Logger.getLogger(SecurityFilter.class);
	private static boolean info = LOG.isInfoEnabled();
	private static boolean debug = LOG.isDebugEnabled();

        private static final Logger syslog = Logger.getLogger("SYSLOG");
	
	private static ThreadLocal<Boolean> flagDbSession = new ThreadLocal<Boolean>();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) req;
		String reqPath = r.getRequestURI(); 
				
		// ignore if going to login page
		if (loginPath.equals(reqPath) || reqPath.startsWith(publicPath)){
			fc.doFilter(req, res);
			return;
		}
		
		if (debug){
			LOG.debug("REQ:" + reqPath + " from " + r.getRemoteAddr() + " begin");
		}
		
		// check if user is authenticated  or not
		HttpSession session = r.getSession();
		Pengguna p = (Pengguna)session.getAttribute("_KEY_USER"); 
		if (p == null){
			// redirect to login page
                        syslog.info("Session Terminated / No Session Access from "+r.getRemoteAddr());
			HttpServletResponse rs = (HttpServletResponse) res;
			rs.sendRedirect(loginPath);
			// TODO: save form's parameters in session
			
			return;
		}
		
		Boolean sessionSet = flagDbSession.get();
		if (sessionSet == null){
			// set the DB client 
			Injector injector = etanahContextListener.getInjector();
			sessionProvider = injector.getProvider(Session.class);
			if (sessionProvider == null){
				LOG.fatal("Failed to get Hibernate sessionProvider!!");
				return;
			}
			Connection c = sessionProvider.get().connection();
			CallableStatement cs = null;
			if (debug){
				LOG.debug("setting up DBMS_SESSION using idPengguna");
			}
			try{
				cs = c.prepareCall("{ call DBMS_SESSION.SET_IDENTIFIER(?) }");
				cs.setString(1, p.getIdPengguna());
				cs.execute();
			} catch (SQLException e){
				LOG.fatal("Cannot set the user database session (" + e.getMessage() + ")", 
						e);
				// redirect to login page
				HttpServletResponse rs = (HttpServletResponse) res;
				rs.sendRedirect(loginPath);
				
				return;
			} finally{
				if (cs != null) try { cs.close(); } catch (SQLException e1) { LOG.error(e1); }
				if (c != null) try { c.close(); } catch (SQLException e1) { LOG.error(e1); }
			}
			
			flagDbSession.set(true);
		}		
		
		fc.doFilter(req, res);
		
		flagDbSession.remove();
		
		// finish up, clear the DB session
		// set the DB client 
/*		c = sessionProvider.get().connection();
		cs = null;
		if (debug){
			LOG.debug("resetting DBMS_SESSION using userID");
		}
		try{
			cs = c.prepareCall("{ call DBMS_SESSION.SET_IDENTIFIER('') }");
			cs.execute();
		} catch (SQLException e){
			LOG.fatal("Cannot set the user database session (" + e.getMessage() + ")", 
					e);
			// redirect to login page
			
			return;
		} finally{
			if (cs != null) try { cs.close(); } catch (SQLException e1) { LOG.error(e1); }
			if (c != null) try { c.close(); } catch (SQLException e1) { LOG.error(e1); }
		}
*/
		
		if (debug){
			LOG.debug("REQ:" + r.getRequestURI() + " from " + r.getRemoteAddr() + " ended");
	}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		String ctxPath = fc.getServletContext().getContextPath();
		
		Configuration conf = new Configuration();
		loginPath = conf.getProperty("security.login.uri");
		if (loginPath == null || loginPath.length() == 0){
			loginPath = DEFAULT_LOGIN_PATH;
		}
		if (!loginPath.startsWith("/")) loginPath = "/" + loginPath;
		loginPath = ctxPath + loginPath;
		
		if (info) LOG.info("Login path = " + loginPath);
		
		publicPath = ctxPath + "/pub";
	}

}
