/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.MenuItemDAO;
import etanah.model.MenuCapaian;
import etanah.model.MenuItem;
import etanah.service.uam.MenuItemService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurashidah.rosman
 */
@UrlBinding("/uam/senarai_menu")
public class SenaraiMenuItemActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SenaraiMenuItemActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private MenuItemService menuItemService;
    @Inject
    MenuItemDAO menuItemDAO;
    @Inject
    MenuCapaianDAO menuCapaianDAO;
    private MenuItem menuItem;
//    private MenuCapaian menuCapaian;
    private String tajuk;
    private List<MenuItem> senaraiMenuItem;
    private List<MenuCapaian> listAllPerananForMenu;  //list peranan for menu
    private List<MenuItem> listAllMenuidAtasForMenu;  //list peranan for menu

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/uam/senaraiMenuItem.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        logger.info("------------rehydrate--------------");
        senaraiMenuItem = menuItemService.getSenaraiItem();
        System.out.println("size menu : " + senaraiMenuItem.size());
    }

    public Resolution searchMenuItem() throws NamingException {
        if (tajuk != null) {
            System.out.println("search tajuk : " + tajuk);
            senaraiMenuItem = new ArrayList<MenuItem>();
            senaraiMenuItem.addAll(menuItemService.searchTajuk(tajuk.toLowerCase()));
            System.out.println("size search result(tajuk) : " + senaraiMenuItem.size());
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/senaraiMenuItem.jsp");
    }

    public Resolution deleteMenu() throws NamingException {
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();

        String id = getContext().getRequest().getParameter("idMenu");
        System.out.println("get id: " + id);

        if (id != null) {

            listAllMenuidAtasForMenu = menuItemService.findMenuidAtas(Integer.parseInt(id));
            System.out.println("size idatas for menu item in menu_item table : " + listAllMenuidAtasForMenu.size());

            if (listAllMenuidAtasForMenu != null) {
                for (MenuItem mi : listAllMenuidAtasForMenu) {
                    System.out.println("idmenu utk idatas :" + mi.getIdMenu());
                    listAllPerananForMenu = menuItemService.findMenu(mi.getIdMenu());
                    System.out.println("size peranan for idatas in menu_capai table : " + listAllPerananForMenu.size());

                    if (listAllPerananForMenu != null) {

                        logger.info("------------delete peranan id atas--------");
                        for (int j = 0; j < listAllPerananForMenu.size(); j++) {
                            for (MenuCapaian mc : listAllPerananForMenu) {
                                System.out.println("idcapai utk idatas(menuitem) and delete idcapai in menu_capai table:" + mc);
                                menuItemService.delete(mc);
                            }
                        }
//                            System.out.println("idmenu-idatas, delete idmenu for idatas :" + mi);
//                            menuItemService.delete(mi);
                    }

                    System.out.println("idmenu-idatas, delete idmenu for idatas in menu_item table :" + mi);
                    menuItemService.delete(mi);
                }

                listAllPerananForMenu = menuItemService.findMenu(Integer.parseInt(id));
                System.out.println("size peranan for menu item in menu_capai table : " + listAllPerananForMenu.size());

                if (listAllPerananForMenu != null) {

                    for (int j = 0; j < listAllPerananForMenu.size(); j++) {
                        logger.info("------------delete peranan utk id menu--------");
                        for (MenuCapaian mc : listAllPerananForMenu) {
                            System.out.println("idcapai utk id menu and delete idcapai io menu_capai table:" + mc);
                            menuItemService.delete(mc);
                        }
                    }
                }

                System.out.println("idmenu-idatas, delete idmenu menu_item table :" + id);
                MenuItem mi = menuItemDAO.findById(Integer.parseInt(id));
                menuItemService.delete(mi);

            } else {

                listAllPerananForMenu = menuItemService.findMenu(Integer.parseInt(id));
                System.out.println("size peranan for menu item in menu_capai table : " + listAllPerananForMenu.size());

                if (listAllPerananForMenu != null) {

                    for (int j = 0; j < listAllPerananForMenu.size(); j++) {
                        logger.info("------------delete peranan utk id menu--------");
                        for (MenuCapaian mc : listAllPerananForMenu) {
                            System.out.println("idcapai utk id menu and delete idcapai in menu_capai table:" + mc);
                            menuItemService.delete(mc);
                        }
                    }
                }

                System.out.println("idmenu-idatas, delete idmenu in menu_item table:" + id);
                MenuItem mi = menuItemDAO.findById(Integer.parseInt(id));
                menuItemService.delete(mi);
            }

//            tx.commit();
            senaraiMenuItem = menuItemService.getSenaraiItem();
            System.out.println("size menu : " + senaraiMenuItem.size());

            addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        }


        return new ForwardResolution("/WEB-INF/jsp/uam/senaraiMenuItem.jsp");
    }

    public List<MenuItem> getSenaraiMenuItem() {
        return senaraiMenuItem;
    }

    public void setSenaraiMenuItem(List<MenuItem> senaraiMenuItem) {
        this.senaraiMenuItem = senaraiMenuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public List<MenuCapaian> getListAllPerananForMenu() {
        return listAllPerananForMenu;
    }

    public void setListAllPerananForMenu(List<MenuCapaian> listAllPerananForMenu) {
        this.listAllPerananForMenu = listAllPerananForMenu;
    }

    public List<MenuItem> getListAllMenuidAtasForMenu() {
        return listAllMenuidAtasForMenu;
    }

    public void setListAllMenuidAtasForMenu(List<MenuItem> listAllMenuidAtasForMenu) {
        this.listAllMenuidAtasForMenu = listAllMenuidAtasForMenu;
    }
}
