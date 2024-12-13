/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

/**
 *
 * @author farah.shafilla
 */
import com.google.inject.Inject;
//import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanDAO;
//import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikUrusan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UrusanGadaianService {

   //  @Inject
   // HakmilikUrusanDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

  public List<HakmilikUrusan> searchHakmilikUrusanGadaian(String idHakmilik){
        String query = "Select h from etanah.model.HakmilikUrusan h where" +
                " h.kodUrusan.kod in ('MGGS','GD','GDPJ','GDPJK') and h.hakmilik.idHakmilik = :idHakmilik" +
                " ORDER BY kodUrusan.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

}
