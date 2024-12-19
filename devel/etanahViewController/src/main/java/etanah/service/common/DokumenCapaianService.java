/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.model.DokumenCapaian;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author TSTR-HP
 */
public class DokumenCapaianService {
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(DokumenService.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    
    public List<DokumenCapaian> findByIdDokumenAndCD(String idDokumen) {
        String strQuery = "Select dc from etanah.model.DokumenCapaian dc"
                + " where dc.dokumen.idDokumen = :idDokumen and dc.aktiviti = 'CD'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idDokumen", idDokumen);
        return q.list();
    }
    public List<DokumenCapaian> findByIdDokumenAndPD(String idDokumen) {
        String strQuery = "Select dc from etanah.model.DokumenCapaian dc"
                + " where dc.dokumen.idDokumen = :idDokumen and dc.aktiviti = 'PD'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idDokumen", idDokumen);
        return q.list();
    }
    
}
