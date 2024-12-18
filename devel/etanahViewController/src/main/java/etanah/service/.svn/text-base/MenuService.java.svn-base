package etanah.service;

import java.sql.*;
import java.util.*;

import javax.persistence.NamedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.model.MenuItem;

/**
 * Displaying user menu based on role.
 * TODO
 * -caching
 * @author hishammk
 *
 */

public class MenuService {

	@Inject
    protected com.google.inject.Provider<Session> sessionProvider;
	
	private static final Logger LOG = Logger.getLogger(MenuService.class);
	private static final boolean debug = LOG.isDebugEnabled();

    /**
     * Render menu for user based on his/her role. Menu is based on JSCookMenu scripts.
     * @param userId
     * @return
     */
    public String renderUserMenu(String contextPath, String idPengguna) {
    	long t = System.currentTimeMillis();
    	
        List<MenuItem> menus = sessionProvider.get().getNamedQuery("selectMenuForUser")
        		.setString("idPengguna", idPengguna).setCacheable(true).list();
        menus = sortTree(menus);

        // translate to html string
        StringBuilder htmlMenu = new StringBuilder("var userMenu = [");
        int i = 0;
        for (i = 0; i < menus.size(); i++) {
            MenuItem m = menus.get(i);
            renderMenuItemWoClosing(htmlMenu, m, contextPath);
            int subSize = m.senaraiSubmenu.size();
            if (subSize > 0) {
                htmlMenu.append(", ");
                for (MenuItem submenu: m.senaraiSubmenu) {
                    renderChildren(submenu, htmlMenu, contextPath, i);
                }
            }
            htmlMenu.append("],");
        }
        htmlMenu.append(" ]; ");

    	if (debug){
    		LOG.debug("Menu rendering for user " + idPengguna + " took " + (System.currentTimeMillis() - t)
    				+ " ms");
    	}

        return htmlMenu.toString();
    }

    private ArrayList<MenuItem> sortTree(List<MenuItem> menus) {
        ArrayList<MenuItem> topMenus = new ArrayList<MenuItem>();
        // get the top level menus
        for (int i = 0; i < menus.size(); i++) {
            MenuItem m = menus.get(i);            
            if (m != null && m.atas == null) {
                topMenus.add(m);
                menus.set(i, null);
            }
        }
        // clear the nulls
        //menus.remove(null);

        for (int i = 0; i < menus.size(); i++) {
            MenuItem m = menus.get(i);
            if (m == null)
                continue;
            for (int j = 0; j < topMenus.size(); j++) {
                if (attach2Parent(topMenus.get(j), m) > 0) {
                    break;
                }
            }
        }

        return topMenus;
    }

    /**
     * Assume that children are sorted by sequence.
     * @param parentMenu
     * @return
     */
    private int attach2Parent(MenuItem parentMenu, MenuItem childMenu) {
        //System.out.println("attach2Parent:looking for parent " + childMenu.id + 
        //              " at parent " + parentMenu.id);
        if (parentMenu.idMenu == childMenu.atas.idMenu) {
            //System.out.println("attach2Parent:found parent"); 
            parentMenu.senaraiSubmenu.add(childMenu);
            return parentMenu.idMenu;
        }
        if (parentMenu.senaraiSubmenu.size() > 0) {
            for (int i = 0; i < parentMenu.senaraiSubmenu.size(); i++) {
                MenuItem m = parentMenu.senaraiSubmenu.get(i);
                if (m.idMenu == childMenu.atas.idMenu) {
                    //System.out.println("attach2Parent:found parent"); 
                    m.senaraiSubmenu.add(childMenu);
                    return childMenu.atas.idMenu;
                } else {
                    if (attach2Parent(m, childMenu) > 0) {
                        return childMenu.atas.idMenu;
                    }
                }
            }
        }
        //System.out.println("attach2Parent:not found parent"); 
        return 0;
    }

    private void renderChildren(MenuItem m, StringBuilder htmlMenu, 
                                             String ctxPath, int jsNo) {
        int children = m.getSenaraiSubmenu().size();
        renderMenuItemWoClosing(htmlMenu, m, ctxPath);
        if (children > 0) {
            htmlMenu.append(", ");
            for (MenuItem submenu: m.getSenaraiSubmenu()) {
                renderChildren(submenu, htmlMenu, ctxPath, jsNo);
            }
            htmlMenu.setLength(htmlMenu.length() - 2);
        }
        htmlMenu.append("], ");
    }

    private void renderMenuItemWoClosing(StringBuilder htmlMenu, 
                                                      MenuItem m, 
                                                      String contextPath) {
        htmlMenu.append("\n['").append(m.getUrlIkon() == null ? "" : "<img src=\"" + m.getUrlIkon() + "\" width=20 height=20/>").append("', '").append(m.getTajuk())
                .append("', '").append(m.getUri() == null ? "" : contextPath)
                .append(m.getUri() == null ? "#" : m.getUri()).append("', ")
                .append(" '_self', '' ");
    }

}

