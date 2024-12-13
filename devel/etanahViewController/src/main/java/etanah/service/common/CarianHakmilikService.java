/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;
import com.google.inject.Inject;
import etanah.model.CarianHakmilik;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author tstr
 */
public class CarianHakmilikService {
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private static final Logger LOGGER = Logger.getLogger(PermohonanCarianService.class);
    private boolean isDebug = LOGGER.isDebugEnabled();
    
    public List<CarianHakmilik> findCarianByIdCarian(String idCarian) {
        
        String q = "Select chm from etanah.model.CarianHakmilik chm where chm.permohonanCarian.idCarian = :idCarian";
        Query query = sessionProvider.get().createQuery(q).setString("idCarian",idCarian);
        return query.list();
    }
}
