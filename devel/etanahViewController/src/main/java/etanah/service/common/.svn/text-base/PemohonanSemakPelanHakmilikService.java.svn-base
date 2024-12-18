package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanSemakPelanHakmilikDAO;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.PermohonanSemakPelanHakmilik;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class PemohonanSemakPelanHakmilikService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanSemakPelanHakmilikDAO permohonanSemakPelanHakmilikDAO;

    
    private static final Logger LOG = Logger.getLogger(PemohonanSemakPelanHakmilikService.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    public PermohonanSemakPelanHakmilik findPermohonanSemakPelanByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select msph from etanah.model.PermohonanSemakPelan psp, "
                + "etanah.model.PermohonanSemakPelanHakmilik msph where psp.idmsp = msph.permohonanSemakPelan.idmsp and msph.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanSemakPelanHakmilik) q.uniqueResult();
    }
    
    public List<PermohonanSemakPelanHakmilik> searchMohonSemakPelanByHakmilikID(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("Select msph from etanah.model.PermohonanSemakPelan psp, etanah.model.PermohonanSemakPelanHakmilik msph where psp.idmsp = msph.permohonanSemakPelan.idmsp and msph.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }
    }
    

    @Transactional
    public void saveOrUpdate(PermohonanSemakPelanHakmilik permohonanSemakPelanHakmilik) {
        permohonanSemakPelanHakmilikDAO.saveOrUpdate(permohonanSemakPelanHakmilik);
    }
    

   
}
