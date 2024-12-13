package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanSemakPelanDAO;
import etanah.model.PermohonanSemakPelan;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class PemohonanSemakPelanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanSemakPelanDAO permohonanSemakPelanDAO;

    
    private static final Logger LOG = Logger.getLogger(PemohonanSemakPelanService.class);

    

    @Transactional
    public void saveOrUpdate(PermohonanSemakPelan permohonanSemakPelan) {
        permohonanSemakPelanDAO.saveOrUpdate(permohonanSemakPelan);
    }
    
    
     public Integer findPermohonanSemakPelanId() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select max(substr(msp.idMsp,1,3))+1 from etanah.model.PermohonanSemakPelan msp");
        Integer idmsp = (Integer) q.uniqueResult();
        return idmsp;
    }
     
    public PermohonanSemakPelan findPermohonanSemakPelanIdHakmilik(String idHakmilik){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select msp from etanah.model.PermohonanSemakPelan msp, etanah.model.PermohonanSemakPelanHakmilik msph"
                + " where msp.idMsp = mpsh.permohonanSemakPelan.idMsp and msph.hakmilik.idHakmilik = :idHakmilik ");
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanSemakPelan) q.uniqueResult(); 
    }

   
}
