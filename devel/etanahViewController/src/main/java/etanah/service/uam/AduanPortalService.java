/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.uam;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AduanPortalDAO;
import etanah.dao.KodAduanPortalDAO;
import etanah.dao.KodStatusPortalDAO;
import etanah.model.AduanPortal;
import etanah.model.KodStatusPortal;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
public class AduanPortalService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger SYSLOG = etanah.SYSLOG.getLogger();
    @Inject
    private AduanPortalDAO aduanPortalDAO;
    @Inject
    private KodAduanPortalDAO kodAduanPortalDAO;
    @Inject
    private KodStatusPortalDAO kodStatusPortalDAO;

    public List<AduanPortal> getSenaraiAduan() {
        String query = "Select m From etanah.model.AduanPortal m where status = '1' or status = '2' order by idAduan,status asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        SYSLOG.info("Listing all menu item...");
        return q.list();
    }

    public List<KodStatusPortal> getSenaraiStatus() {
        String query = "Select ksp from etanah.model.KodStatusPortal ksp";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        SYSLOG.info("Search senarai Aduan........ ");
        return q.list();
    }

    public List<AduanPortal> searchStatus(String status) {
        String query = "Select ad from etanah.model.AduanPortal ad WHERE status LIKE :status";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("status", "%" + status + "%");

        SYSLOG.info("Search Aduan for status........ ");
        return q.list();
    }

    public List<AduanPortal> getSenaraiNewAduan() {
        String query = "Select na from etanah.model.AduanPortal na WHERE status = '1' ";
        Query q = sessionProvider.get().createQuery(query);
        SYSLOG.info("Search New Aduan List........ ");
        return q.list();
    }

    @Transactional
    public void update(AduanPortal aduanPortal) {
        aduanPortalDAO.saveOrUpdate(aduanPortal);
    }
}