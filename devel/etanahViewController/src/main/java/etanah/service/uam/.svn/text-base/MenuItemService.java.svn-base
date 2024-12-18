/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.uam;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.MenuItemDAO;
import etanah.model.KodPeranan;
import etanah.model.MenuCapaian;
import etanah.model.MenuItem;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurashidah.rosman
 */
public class MenuItemService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger SYSLOG = etanah.SYSLOG.getLogger();
    @Inject
    private MenuCapaianDAO menuCapaianDAO;
     @Inject
    private MenuItemDAO menuItemDAO;

    public List<MenuItem> getSenaraiItem() throws NamingException {
        String query = "Select m From etanah.model.MenuItem m where m.umum = 'T' Order By tajuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        SYSLOG.info("Listing all menu item...");
        return q.list();
    }

    public List<MenuItem> searchTajuk(String tajuk) {
        String query = "Select m from etanah.model.MenuItem m WHERE lower(tajuk) LIKE :tajuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("tajuk", "%" + tajuk + "%");

        SYSLOG.info("Search tajuk menu item........ ");
        return q.list();
    }

    public List<KodPeranan> getKodPeranan() throws NamingException {
        String query = "Select kp from etanah.model.KodPeranan kp WHERE kp.aktif = 'Y' Order By nama ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        SYSLOG.info("Listing all Kod Peranan...");
        return q.list();
    }

    @Transactional
    public void simpan(MenuCapaian menuCapai) {
        menuCapaianDAO.saveOrUpdate(menuCapai);
    }

    @Transactional
    public void simpan(MenuItem menuItem) {
        menuItemDAO.save(menuItem);
    }

    @Transactional
    public void update(MenuItem menuItem) {
        menuItemDAO.saveOrUpdate(menuItem);
    }

    public MenuCapaian findExistingRecord(int idMenu, String idPeranan) {
        String query = "Select m FROM etanah.model.MenuCapaian m WHERE m.menu = :idMenu AND m.peranan.kod = :idPeranan";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("idMenu", idMenu);
        q.setString("idPeranan", idPeranan);
        return (MenuCapaian) q.uniqueResult();
    }

    public List<MenuCapaian> findMenu(int idMenu) {
        System.out.println("idMenu at service, cari peranan utk id menu : "+idMenu);
        String query = "Select mc FROM etanah.model.MenuCapaian mc, etanah.model.KodPeranan kp "
                        + "WHERE mc.menu = :idMenu AND mc.peranan = kp.kod Order By upper(kp.nama)";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("idMenu", idMenu);

        return q.list();
    }

    public List<MenuItem> findMenuidAtas(int idMenu) {
        String query = "Select m from etanah.model.MenuItem m WHERE m.atas = :idMenu";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("idMenu", idMenu);

        SYSLOG.info("Search menu-id atas for menu item........ ");
        return q.list();
    }

    @Transactional
    public void delete(MenuItem menuItem) {
        menuItemDAO.delete(menuItem);
    }

    @Transactional
    public void delete(MenuCapaian menuCapaian) {
        menuCapaianDAO.delete(menuCapaian);
    }
}
