/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.LelonganDAO;
import etanah.model.Enkuiri;
import etanah.model.Lelongan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mdizzat.mashrom
 */
public class LelonganService {

    @Inject
    Lelongan lelong;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<Lelongan> getIDjuruLelong(Long idjlb) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.jurulelong.idJlb= :id_jlb");
        q.setParameter("id_jlb", idjlb);
        return q.list();
    }

    public List<Lelongan> lelongList(Long idlelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.idLelong= :id_lelong");
        q.setParameter("id_lelong", idlelong);
        return q.list();
    }

    public List<Lelongan> getEnkuiri(Enkuiri enkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.enkuiri= :id_enkuiri");
        q.setParameter("id_enkuiri", enkuiri);
        return q.list();
    }

    @Transactional
    public void saveOrUpdate(Lelongan lelong) {
        lelonganDAO.saveOrUpdate(lelong);
    }
}
