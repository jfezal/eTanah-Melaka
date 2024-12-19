/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.model.PermohonanHubungan;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

//apool

public class PermohonanHubunganService {

    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject PermohonanHubunganDAO permohonanHubunganDAO;
    
    @Transactional
    public void deleteHubungan(String[] uids) {
        for (String id : uids) {
            if (StringUtils.isBlank(id)) continue;
            PermohonanHubungan ph = permohonanHubunganDAO.findById(Long.parseLong(id));
            if (ph == null) continue;
            permohonanHubunganDAO.delete(ph);
        }
    }
    
    public List<PermohonanHubungan> findMohonAtasUrusanByIDSumberAndIDHakmilik(String idPermohonan,String idHakmilik){
    Session session = sessionProvider.get();
    Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanHubungan p WHERE p.permohonanSumber.idPermohonan  = :idPermohonan AND (p.hakmilik.idHakmilik = :idHakmilik OR p.catatan = :idHakmilik)");
    q.setParameter("idPermohonan", idPermohonan);
    q.setParameter("idHakmilik", idHakmilik);
     return q.list();
    }
}
