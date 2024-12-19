/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.manager;


import etanah.ldap.LDAPManager;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.security.controller.StripesSecurityManager;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;


/**
 *
 * @author fikri
 */

public class SecurityManager implements StripesSecurityManager{

    private static final Logger LOG = Logger.getLogger(SecurityManager.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    private LDAPManager ldapManager;

    @Override
    public boolean isUserInRole(List<String> roles, ActionBeanContext context) {        
        
        if (roles == null || roles.isEmpty()) return false;
        
        boolean flag = false;

        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (isDebug) LOG.debug("pengguna = " + pengguna.getIdPengguna());

        String[] rolesList = (String[])roles.toArray();        

        try{
            
            ldapManager = new LDAPManager();
            List peranans = ldapManager.getGroupsByUsername(pengguna.getIdPengguna());

            for(int i=0; i<peranans.size(); i++) {
                String peranan = (String)peranans.get(i);
                if (isDebug) LOG.debug(peranan);
                if (ArrayUtils.contains(rolesList, peranan)) {
                    flag = true;
                    break;
                }
            }
            ldapManager.close();
            
        }catch (Exception ex) {
            LOG.error(ex);
        } 
        return flag;
    }

    @Override
    public boolean isUserInRole(List<String> roles, HttpServletRequest request, HttpServletResponse response) {
        Pengguna pengguna = (Pengguna) request.getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (isDebug) LOG.debug("pengguna = " + pengguna.getIdPengguna());

        String[] rolesList = (String[])roles.toArray();
        
         boolean flag = false;

        try{           

            ldapManager = new LDAPManager();
            List peranans = ldapManager.getGroupsByUsername(pengguna.getIdPengguna());

            for(int i=0; i<peranans.size(); i++) {
                String peranan = (String)peranans.get(i);
                if (isDebug) LOG.debug(peranan);
                if (ArrayUtils.contains(rolesList, peranan)) {
                    flag = true;
                    break;
                }
            }
            ldapManager.close();

        }catch (Exception ex) {
            LOG.error(ex);
        }
        return flag;
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}
