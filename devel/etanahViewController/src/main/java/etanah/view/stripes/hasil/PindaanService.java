package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import etanah.model.Akaun;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class PindaanService {
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

     public List<Akaun> getPindaanAkaun(){
        String query = "SELECT h FROM etanah.model.Akaun h where h.kodAkaun.kod ='ACT'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }
}
