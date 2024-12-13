/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.filter;

import etanah.model.Pengguna;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.MDC;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String user = "N/A";
        if (session != null) {
            Pengguna pguna = (Pengguna) session.getAttribute(etanah.view.etanahActionBeanContext.KEY_USER);
            if (pguna != null) {
                user = pguna.getIdPengguna();
            }
        }
        MDC.put("idpguna", user);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
