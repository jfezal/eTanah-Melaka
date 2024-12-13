/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.filter;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.model.LogPenggunaApplikasi;
import etanah.model.Pengguna;
import etanah.service.PageAuditService;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class PageFilter implements Filter {

    private static final boolean debug = true;

    public static final String DEFAULT_LOGIN_PATH = "/login";

    private static String loginPath;

    private static String publicPath;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;    

    private static final Logger LOG = Logger.getLogger(PageFilter.class);

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public PageFilter() {
    } 
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

//	if (debug) log("PageFilter:doFilter()");

        HttpServletRequest r = (HttpServletRequest) request;
        HttpSession session = r.getSession();
        Pengguna p = (Pengguna)session.getAttribute("_KEY_USER"); 
        String reqPath = r.getRequestURI();
	
//        System.gc();
        
	Throwable problem = null;

	try {

//            if (debug){
//			LOG.debug("REQ:" + reqPath + " from " + r.getRemoteAddr() + " begin");
//		}
            
            if (loginPath.equals(reqPath)
                    || reqPath.endsWith("js") || reqPath.endsWith("jar")
                    || reqPath.endsWith("css") || reqPath.endsWith("xml")
                    || reqPath.endsWith("properties") || reqPath.endsWith("gif")
                    || reqPath.endsWith("png") || reqPath.endsWith("swf")) {
                chain.doFilter(request, response);
                return;
            }            
            
            if (p != null) {
                Injector injector = etanahContextListener.getInjector();
                PageAuditService pageAuditService = injector.getInstance(PageAuditService.class);                
                LogPenggunaApplikasi log = new LogPenggunaApplikasi();
                log.setIdPguna(p.getIdPengguna());
                log.setTarikhMasuk(new Date());
                log.setUrl(reqPath);
                if (request.getParameterMap().size() > 0) {
                    String param = etanah.util.StringUtils.toString(request.getParameterMap());
                    log.setParameter(param.length() > 4000 ? param.substring(0, 4000) : param);
                }
                pageAuditService.save(log);
            }


	    chain.doFilter(request, response);
	}
	catch(Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
	    LOG.error(t);
	}

	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
    }    


    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy() { 
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) {
        String ctxPath = filterConfig.getServletContext().getContextPath();        
	loginPath = ctxPath + DEFAULT_LOGIN_PATH;
        publicPath = ctxPath + "/pub";
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("PageFilter()");
	StringBuffer sb = new StringBuffer("PageFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
