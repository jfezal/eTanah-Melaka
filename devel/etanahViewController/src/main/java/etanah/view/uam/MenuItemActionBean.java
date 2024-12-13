/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.MenuItemDAO;
import etanah.model.InfoAudit;
import etanah.model.MenuItem;
import etanah.model.Pengguna;
import etanah.service.uam.MenuItemService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurashidah.rosman
 */
@UrlBinding("/uam/menu")
public class MenuItemActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MenuItemActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private MenuItemService menuItemService;
    @Inject
    MenuItemDAO menuItemDAO;

    private MenuItem menuItem;
    private String tajuk;
    private String uri;
    private String urlIkon;
    private int turutan;
    private List<MenuItem> senaraiMenu;
    private String idAtas;
    private String idMenu;
    private char umum;
    private char aktif;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/uam/menuItem.jsp");
    }

    public Resolution viewMenu() {
        String id = getContext().getRequest().getParameter("idMenu");
        menuItem = menuItemDAO.findById(Integer.parseInt(id));
        if (menuItem != null) {
            idMenu = Integer.toString(menuItem.getIdMenu());
            tajuk = menuItem.getTajuk();

            if (menuItem.getAtas() != null){
                idAtas = String.valueOf(menuItem.getAtas().getIdMenu());
            }

            uri = menuItem.getUri();
            turutan = menuItem.getTurutan();
            umum = menuItem.getUmum();
            aktif = menuItem.getAktif();
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/menuItem.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        logger.info("------------rehydrate--------------");
        senaraiMenu = menuItemService.getSenaraiItem();
        System.out.println("size menu : " + senaraiMenu.size());
    }

    public Resolution simpan() throws Exception {
        logger.info("------------simpan--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        System.out.println("menuitem  :" + menuItem);
        menuItem = new MenuItem();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        menuItem.setInfoAudit(ia);
        menuItem.setTajuk(tajuk);
        menuItem.setUri(uri);
        menuItem.setUrlIkon(urlIkon);
        menuItem.setTurutan(turutan);
        menuItem.setUmum(umum);
        menuItem.setAktif(aktif);

        if (idAtas != null) {
            MenuItem idAtasMenu = menuItemDAO.findById(Integer.parseInt(idAtas));
            menuItem.setAtas(idAtasMenu);
        } else {
            menuItem.setAtas(null);
        }
        logger.info("######" + idAtas);

//        menuItemDAO.saveOrUpdate(menuItem);
//        tx.commit();

        menuItemService.simpan(menuItem);
idMenu = String.valueOf(menuItem.getIdMenu());
        senaraiMenu = menuItemService.getSenaraiItem();
        System.out.println("size menu : " + senaraiMenu.size());

        addSimpleMessage("Maklumat berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/uam/menuItem.jsp");
    }

    public Resolution edit() throws Exception {
        logger.info("------------edit--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        String id = getContext().getRequest().getParameter("idMenu");
        System.out.println("idMenu :" + id);
        menuItem = menuItemDAO.findById(Integer.parseInt(id));
        System.out.println("menuitem  :" + menuItem);

//        if (menuItem == null) {
//
//            menuItem = new MenuItem();
//            ia.setDimasukOleh(peng);
//            ia.setTarikhMasuk(new java.util.Date());
//
//        } else {
            ia = menuItem.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
//        }

        menuItem.setInfoAudit(ia);
        menuItem.setTajuk(tajuk);
        menuItem.setUri(uri);
        menuItem.setUrlIkon(urlIkon);
        menuItem.setTurutan(turutan);
        menuItem.setUmum(umum);
        menuItem.setAktif(aktif);

//        String idAts = getContext().getRequest().getParameter("idAtas");
//        System.out.println("idAtas :" + idAts);
        if (idAtas != null) {
            MenuItem idAtasMenu = menuItemDAO.findById(Integer.parseInt(idAtas));
            menuItem.setAtas(idAtasMenu);
        } else {
            menuItem.setAtas(null);
        }
        logger.info("######" + idAtas);

//        menuItemDAO.saveOrUpdate(menuItem);
//        tx.commit();

        menuItemService.update(menuItem);

        senaraiMenu = menuItemService.getSenaraiItem();
        System.out.println("size menu : " + senaraiMenu.size());

        addSimpleMessage("Maklumat berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/uam/menuItem.jsp");
    }
    
        public Resolution terus() throws Exception {
        logger.info("------------edit--------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        String id = getContext().getRequest().getParameter("id");
        System.out.println("idMenu :" + idMenu);
        menuItem = menuItemDAO.findById(Integer.parseInt(idMenu));
       
getContext().getRequest().setAttribute("id", menuItem.idMenu);
        return new RedirectResolution(MenuCapaianActionBean.class, "findIdMenu").addParameter("id", menuItem.idMenu);
    }


    public String getIdAtas() {
        return idAtas;
    }

    public void setIdAtas(String idAtas) {
        this.idAtas = idAtas;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<MenuItem> getSenaraiMenu() {
        return senaraiMenu;
    }

    public void setSenaraiMenu(List<MenuItem> senaraiMenu) {
        this.senaraiMenu = senaraiMenu;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public int getTurutan() {
        return turutan;
    }

    public void setTurutan(int turutan) {
        this.turutan = turutan;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrlIkon() {
        return urlIkon;
    }

    public void setUrlIkon(String urlIkon) {
        this.urlIkon = urlIkon;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getUmum() {
        return umum;
    }

    public void setUmum(char umum) {
        this.umum = umum;
    }
}
