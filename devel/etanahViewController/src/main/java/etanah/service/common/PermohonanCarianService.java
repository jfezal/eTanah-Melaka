package etanah.service.common;
import com.google.inject.Inject;
import etanah.dao.PermohonanCarianDAO;
import etanah.model.DokumenKewangan;
import etanah.model.PermohonanCarian;
import etanah.model.CarianHakmilik;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author ${user}
 */
public class PermohonanCarianService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanCarianDAO carianDAO;

    private static final Logger LOGGER = Logger.getLogger(PermohonanCarianService.class);
    private boolean isDebug = LOGGER.isDebugEnabled();

    public PermohonanCarian findById(String id) {
        return carianDAO.findById(id);
    }
    
    public PermohonanCarian findByIdKewDok(String id_kew_dok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select a from etanah.model.PermohonanCarian a where a.resit.idDokumenKewangan = :id_kew_dok");
        q.setString("id_kew_dok", id_kew_dok);
        if (isDebug) {
            LOGGER.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (PermohonanCarian) q.list().get(0);
        }
        return (PermohonanCarian) q.uniqueResult();
    }
    
    public List<PermohonanCarian> getSenaraiPermohonanPartial(String idCarian, String kodCaw, int start, int max) {
        if (isDebug) {
            LOGGER.debug("from record [" + start + "]");
            LOGGER.debug("to record [" + max + "]");
        }
        
        String q = "Select m from etanah.model.PermohonanCarian m where m.idCarian like :idCarian";
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q).setString("idCarian", "%" + idCarian + "%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }
    
        public List<CarianHakmilik> findCarianByIdHakmilik(String idHakmilik, String kodCaw, int start, int max) {
        if (isDebug) {
            LOGGER.debug("from record [" + start + "]");
            LOGGER.debug("to record [" + max + "]");
        }
        
        String q = "Select chm from etanah.model.CarianHakmilik chm where chm.idHakmilik like :idHakmilik";
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and chm.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q).setString("idHakmilik", "%" + idHakmilik + "%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

}
