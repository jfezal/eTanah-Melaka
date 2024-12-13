/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.Pihak;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import java.util.List;


/**
 *
 * @author nordiyana
 */
public class PihakPengambilanService {
   @Inject
    PermohonanDAO permohonanDAO;
    protected com.google.inject.Provider<Session> sessionProvider;



    public Permohonan findById(String Id){
        return permohonanDAO.findById(Id);
    }

    public List<HakmilikPermohonan> findByidP(String idPermohonan){
        System.out.println("test masuk");
       //String query = "select count(hp.pihak) FROM etanah.model.HakmilikPihakBerkepentingan hp where hp.hakmilik in(select mh.hakmilik from HakmilikPermohonan mh where mh.idPermohonan= :idPermohonan)";
        String query = "select mh.hakmilik from HakmilikPermohonan mh where mh.idPermohonan= :idPermohonan";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("idPermohonan", idPermohonan);
//            System.out.println("jumlah pihak:" + q);
//            String jumlah = q.toString();
            return q.list();
    }

    public HakmilikPihakBerkepentingan findByidHakmilik(String idHakmilik){
        String query = "Select p FROM etanah.model.HakmilikPihakBerkepentingan p WHERE p.HakmilikPermohonan.idHakmilik = :idHakmilik";
        return (HakmilikPihakBerkepentingan) sessionProvider.get().createQuery(query)
                .setString("idHakmilik", idHakmilik).uniqueResult();

}
    public List<Pihak> findPihakActiveByHakmilik(String idPermohonan) {
        //fikri :: return only tuan tanah and pemegang amanah
        String query = "select * from etanah.model.Pihak p where p.idPihak in(select hp.pihak FROM etanah.model.HakmilikPihakBerkepentingan hp in(select mh.hakmilik from HakmilikPermohonan mh where mh.idPermohonan= :idPermohonan)";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();


    }

     public List <Pihak> findByidPihak(Long idPihak){
        String query = "Select p FROM etanah.model.Pihak p WHERE p.HakmilikPihakBerkepentingan.idPihak = :idPihak";
//        return (Pihak) sessionProvider.get().createQuery(query)
//                .setLong("idPihak", idPihak).uniqueResult();
        Query q = sessionProvider.get().createQuery(query).setLong("idPihak", idPihak);
        return q.list();
}

 public List <HakmilikPermohonan> findByMHidPihak(String idPermohonan){
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
//        return (Pihak) sessionProvider.get().createQuery(query)
//                .setLong("idPihak", idPihak).uniqueResult();
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
}

}
