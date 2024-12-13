/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.filter;


import com.theta.portal.model.UserTable;
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
/**
 *
 * @author wan.fairul
 */
public class LoggingFilter implements Filter{
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
            UserTable pguna = (UserTable) session.getAttribute(com.theta.portal.stripes.config.ableActionBeanContext.USER_ID_KEY);
            if (pguna != null) {
                user = pguna.getUserName();
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

