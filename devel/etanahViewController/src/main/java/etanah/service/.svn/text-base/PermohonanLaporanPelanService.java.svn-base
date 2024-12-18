/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class PermohonanLaporanPelanService {

    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanPelanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;//add by tulasi

    @Transactional
    public PermohonanLaporanKawasan saveOrUpdate(PermohonanLaporanKawasan permohonanLaporanPelan) {
        return permohonanLaporanPelanDAO.saveOrUpdate(permohonanLaporanPelan);
    }

     @Transactional
     public List<HakmilikPermohonan> getHakmilikPermohan(String idperMohan) {
        String query = "Select i FROM etanah.model.HakmilikPermohonan i WHERE i.permohonan.idPermohonan = :idperMohan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idperMohan", idperMohan);
        return q.list();
    }
     @Transactional
     public  PermohonanLaporanKawasan  getPermohonanLaporanKawasan(String idperMohan) {
        String query = "Select i FROM etanah.model.PermohonanLaporanKawasan i WHERE i.permohonan.idPermohonan = :idperMohan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idperMohan", idperMohan);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

}
