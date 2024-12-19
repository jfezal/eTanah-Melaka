/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import etanah.service.common.*;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class BayaranPampasanService {

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public boolean saveHakmilikPermohonan(List<HakmilikPermohonan> list) {

        for (HakmilikPermohonan hakmilikPermohonan : list) {
            hakmilikPermohonan = (HakmilikPermohonan) hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
            if (hakmilikPermohonan == null) {
                return false;
            }
        }
        return true;
    }

    public void saveOrUpdateWithoutConnection(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.save(hp);
    }

    @Transactional
    public boolean saveSingleHakmilikPermohonan(HakmilikPermohonan hp) {
        hp = (HakmilikPermohonan) hakmilikPermohonanDAO.saveOrUpdate(hp);
        return (hp != null);
    }

    public List<String> findIdHakmilikByPermohonan(String idPermohonan) {
        String q = "Select hp.hakmilik.idHakmilik FROM etanah.model.HakmilikPermohonan hp " + "WHERE hp.permohonan.idPermohonan = :idPermohonan";
        Query query = sessionProvider.get().createQuery(q).setString("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilik(String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

         // Added to get HakmilikPerbicaraan based on IdHakmilikPermohonan --- Imran Khan
//     public List<HakmilikPerbicaraan> findByIdHakmilikPermohonan(long idHakmilikPermohonan){
//        String q = "Select ambil FROM etanah.model.ambil.HakmilikPerbicaraan ambil "
//                + "WHERE ambil.hakmilikPermohonan.id = :idHakmilikPermohonan order by tarikhBicara desc";
//        Query query = sessionProvider.get().createQuery(q).setLong("idHakmilikPermohonan", idHakmilikPermohonan);
//        return query.list();
//    }

    public List<HakmilikPerbicaraan> findByIdHakmilikPermohonan(Long idHakmilikPermohonan){
        String q = "Select ambil FROM etanah.model.ambil.HakmilikPerbicaraan ambil "
                + "WHERE ambil.hakmilikPermohonan.id = :idHakmilikPermohonan";
        Query query = sessionProvider.get().createQuery(q).setLong("idHakmilikPermohonan", idHakmilikPermohonan);
        return query.list();
    }
}
