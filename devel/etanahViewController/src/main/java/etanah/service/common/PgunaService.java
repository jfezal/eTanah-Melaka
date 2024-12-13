/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import org.hibernate.Session;
import etanah.model.Pengguna;
import etanah.dao.PenggunaDAO;
import org.hibernate.Query;

/**
 *
 * @author khairil
 */
public class PgunaService {

    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Pengguna saveOrUpdate(Pengguna p) {
        return penggunaDAO.saveOrUpdate(p);
    }
    
    public String findNamaPengguna(String idPengguna){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p.nama FROM etanah.model.Pengguna p WHERE p.idPengguna = :idPengguna");
        q.setParameter("idPengguna",idPengguna);
        return (String) q.uniqueResult();
    }
}
