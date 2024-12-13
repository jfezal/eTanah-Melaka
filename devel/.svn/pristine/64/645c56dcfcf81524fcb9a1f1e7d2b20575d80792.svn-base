package etanah.service.common;

import com.google.inject.Inject;
import etanah.dao.HakmilikWarisDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikWaris;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 *
 * @author ${user}
 */
public class HakmilikWarisService {
    private static final Logger LOGGER = Logger.getLogger(HakmilikWarisService.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikWarisDAO hakmilikWarisDAO;


    public List<HakmilikWaris> getSenaraiWarisByIdHakmilikPihak (String id) {
        String strQuery = "Select h from etanah.model.HakmilikWaris h where h.pemegangAmanah.idHakmilikPihakBerkepentingan = :id and h.status in ('A')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("id", id);
        return q.list();
    }    

    public void save (List<HakmilikWaris> senarai) {
        for (HakmilikWaris hw : senarai) {
            if (hw == null) continue;
            hakmilikWarisDAO.save(hw);
        }
    }
    
    public HakmilikWaris getWaris (String id) {
        String strQuery = "Select h from etanah.model.HakmilikWaris h where h.idWaris = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("id", Long.parseLong(id));
        return (HakmilikWaris) q.uniqueResult();
    }
}
