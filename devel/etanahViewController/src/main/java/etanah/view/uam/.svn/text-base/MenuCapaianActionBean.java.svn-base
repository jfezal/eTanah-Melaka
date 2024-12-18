/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.MenuItemDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.MenuCapaian;
import etanah.model.MenuItem;
import etanah.model.Pengguna;
import etanah.service.uam.MenuItemService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
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
@UrlBinding("/uam/menu_capai")
public class MenuCapaianActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MenuCapaianActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private MenuItemService menuItemService;
    @Inject
    private MenuItemDAO menuItemDAO;
    @Inject
    private MenuCapaianDAO menuCapaianDAO;
    @Inject
    private KodPerananDAO kodPerananDAO;
    private List<MenuItem> senaraiMenu;
    private List<KodPeranan> senaraiKodPeranan;
    private List<KodPeranan> senaraiPeranan = new ArrayList<KodPeranan>();
    private List<MenuCapaian> senaraiKPMenu;
    private MenuCapaian menuCapai;
    private MenuItem menuItem;
    private KodPeranan kodPeranan;
    private String menu;
    private String peranan;
    private String senaraiKP;
    private String kPeranan;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/uam/menuCapai.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        logger.info("------------rehydrate--------------");
        senaraiMenu = menuItemService.getSenaraiItem();
        System.out.println("size menu : " + senaraiMenu.size());

        logger.info("------------rehydrate--------------");
        senaraiKodPeranan = menuItemService.getKodPeranan();
        System.out.println("size kod peranan : " + senaraiKodPeranan.size());
    }

    public Resolution simpan() throws Exception {
        logger.info("------------simpan--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        System.out.println("row count :" + rowCount);
        System.out.println("menu : " + menu);

        for (int i = 0; i < rowCount; i++) {

            String idPeranan = getContext().getRequest().getParameter("idPeranan" + i);
            if (idPeranan != null) {
                System.out.println("id peranan : " + idPeranan + "[" + i + "]");

                menuCapai = menuItemService.findExistingRecord(Integer.parseInt(menu), idPeranan);
                if (menuCapai == null) {
                    System.out.println("menu capai null");
                    menuCapai = new MenuCapaian();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    System.out.println("menu capai tak null");
                    ia = menuCapai.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                }

                menuCapai.setInfoAudit(ia);
                menuCapai.setMenu(menuItemDAO.findById(Integer.parseInt(menu)));
                menuCapai.setPeranan(kodPerananDAO.findById(idPeranan));
                menuItemService.simpan(menuCapai);
            } else {
                System.out.println("idPeranan null, nothing to save.....");
            }
        }

        senaraiKPMenu = menuItemService.findMenu(Integer.parseInt(menu));

        addSimpleMessage("Maklumat berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/uam/menuCapai.jsp");
    }

    public Resolution findIdMenu() throws Exception {
        logger.info("------------find menu--------");

        String id = getContext().getRequest().getParameter("id");
        if (id != null) {
            System.out.println("search id : " + id);
//            senaraiKPMenu = new ArrayList<MenuCapaian>()
//            senaraiKPMenu.addAll(menuItemService.findMenu(Integer.parseInt(id)));

            MenuItem menuItem = menuItemDAO.findById(Integer.parseInt(id));
            this.setMenu(String.valueOf(menuItem.idMenu));
            senaraiKPMenu = menuItemService.findMenu(Integer.parseInt(id));
            System.out.println("size menu : " + senaraiKPMenu.size());
//            rehydrate();
        }

//        return new RedirectResolution(MenuCapaianActionBean.class, "locate");
        return new JSP("uam/menuCapai.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/uam/menuCapai.jsp");
    }

    public Resolution deletePerananMenu() {
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();

        String id = getContext().getRequest().getParameter("idCapai");
        System.out.println("get id : " + id);

        String idMenu = getContext().getRequest().getParameter("idMenu");
        System.out.println("get idMenu : " + idMenu);

        menuCapai = menuCapaianDAO.findById(Integer.parseInt(id));

        if (menuCapai != null) {
//            menuCapaianDAO.delete(menuCapai);
            menuItemService.delete(menuCapai);
//            tx.commit();
        }
        senaraiKPMenu = menuItemService.findMenu(Integer.parseInt(idMenu));

        addSimpleMessage("Maklumat telah berjaya dihapuskan.");

        return new ForwardResolution("/WEB-INF/jsp/uam/menuCapai.jsp");
    }

    public List<KodPeranan> getSenaraiKodPeranan() {
        return senaraiKodPeranan;
    }

    public void setSenaraiKodPeranan(List<KodPeranan> senaraiKodPeranan) {
        this.senaraiKodPeranan = senaraiKodPeranan;
    }

    public List<MenuItem> getSenaraiMenu() {
        return senaraiMenu;
    }

    public void setSenaraiMenu(List<MenuItem> senaraiMenu) {
        this.senaraiMenu = senaraiMenu;
    }

    public String getPeranan() {
        return peranan;
    }

    public void setPeranan(String peranan) {
        this.peranan = peranan;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public MenuCapaian getMenuCapai() {
        return menuCapai;
    }

    public void setMenuCapai(MenuCapaian menuCapai) {
        this.menuCapai = menuCapai;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getSenaraiKP() {
        return senaraiKP;
    }

    public void setSenaraiKP(String senaraiKP) {
        this.senaraiKP = senaraiKP;
    }

    public List<KodPeranan> getSenaraiPeranan() {
        return senaraiPeranan;
    }

    public void setSenaraiPeranan(List<KodPeranan> senaraiPeranan) {
        this.senaraiPeranan = senaraiPeranan;
    }

    public List<MenuCapaian> getSenaraiKPMenu() {
        return senaraiKPMenu;
    }

    public void setSenaraiKPMenu(List<MenuCapaian> senaraiKPMenu) {
        this.senaraiKPMenu = senaraiKPMenu;
    }

    public String getkPeranan() {
        return kPeranan;
    }

    public void setkPeranan(String kPeranan) {
        this.kPeranan = kPeranan;
    }
}
