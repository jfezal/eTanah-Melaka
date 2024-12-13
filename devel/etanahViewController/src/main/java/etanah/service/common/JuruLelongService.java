/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

//import com.google.inject.Inject;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.JuruLelongDAO;
import etanah.dao.LelonganDAO;
import etanah.model.JuruLelong;
import etanah.model.Lelongan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
//import javax.jcr.query.Query;
//import java.util.logging.Logger;
//import javax.jcr.Session;

/**
 *
 * @author mdizzat.mashrom
 */
public class JuruLelongService {

    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    LelonganDAO lelonganDAO;
    JuruLelong jurulelong;
//    Lelongan lelongan;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
//    @Inject
//    protected com.google.inject.Provider<Session> sessionProvider;
//    private static final Logger LOGGER = Logger.getLogger(JuruLelongService.class);
//
//    @Inject
//    public JuruLelongService(JuruLelongDAO jurulelongDAO) {
//        this.jurulelongDAO = jurulelongDAO;
//    }

    @Transactional
    public void saveOrUpdate(JuruLelong j) {
        jurulelongDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveOrUpdate(Lelongan lelong) {
        lelonganDAO.saveOrUpdate(lelong);
    }

    @Transactional
    public void update(JuruLelong j) {
        jurulelongDAO.update(j);
    }

    @Transactional
    public void save(JuruLelong j) {
        jurulelongDAO.save(j);
    }

    @Transactional
    public JuruLelong findById(Long idJlb) {
        return jurulelongDAO.findById(idJlb);
    }

     @Transactional
     public void deleteAll(JuruLelong j) {
        jurulelongDAO.delete(j);
     }

    @Transactional
    public void delete(JuruLelong j) {
        jurulelongDAO.delete(j);
    }

    @Transactional
    public void delete(Lelongan lelong) {
        lelonganDAO.delete(lelong);
    }


    @Transactional
    public void simpanPermohonan(JuruLelong j) {
        jurulelongDAO.update(j);
    }

//
    public List<JuruLelong> getSenaraiJuruLelong(Long idJlb){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT j FROM etanah.model.JuruLelong j WHERE j.idJlb = :id_jlb");
        q.setParameter("id_jlb", idJlb);
        return q.list();
//
    }

    public List<JuruLelong> findPemohonByIdPermohonan(Long idJlb) {
    Session s = sessionProvider.get();
    Query q = s.createQuery("Select j from etanah.model.JuruLelong j where j.idJlb = :id_jlb");
    q.setParameter("id_jlb", idJlb);
    return q.list();
    }
    
    public List<JuruLelong> findByNama(String nama) {
    Session s = sessionProvider.get();
    Query q = s.createQuery("Select j from etanah.model.JuruLelong j where j.nama = :nama");
    q.setParameter("nama", nama);
    return q.list();
    }
//        public List<JuruLelong> getSenaraiPmohonPihak(String idMohon) {
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon");
//        q.setParameter("id_mohon", idMohon);
//        return q.list();

    @Transactional
    public JuruLelong simpanPemohon(JuruLelong j) {
        return(JuruLelong)jurulelongDAO.saveOrUpdate(j);
    }
}



 