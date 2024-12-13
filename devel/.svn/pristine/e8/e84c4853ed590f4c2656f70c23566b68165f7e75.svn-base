/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanAtasPihakBerkepentinganDAO;
import etanah.model.Hakmilik;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class PermohonanAtasPihakBerkepentinganService {

    @Inject
    private PermohonanAtasPihakBerkepentinganDAO mapDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public PermohonanAtasPihakBerkepentingan findById(String id) {
        return mapDAO.findById(Long.parseLong(id));
    }

    @Transactional
    public boolean save(PermohonanAtasPihakBerkepentingan map) {
        map = mapDAO.saveOrUpdate(map);
        if (map != null) {
            return true;
        }

        return false;
    }

    @Transactional
    public void save(List<PermohonanAtasPihakBerkepentingan> senarai) {
        for (PermohonanAtasPihakBerkepentingan permohonanAtasPihakBerkepentingan : senarai) {
            mapDAO.saveOrUpdate(permohonanAtasPihakBerkepentingan);
        }
    }

    @Transactional
    public void delete(String[] uids) {
        for (String str : uids) {
            PermohonanAtasPihakBerkepentingan map = mapDAO.findById(Long.parseLong(str));
            if (map == null) {
                continue;
            }
            mapDAO.delete(map);
        }
    }

    @Transactional
    public void delete(Long id) {
        PermohonanAtasPihakBerkepentingan map = mapDAO.findById(id);
        if (map != null) {
            mapDAO.delete(map);
        }
    }

    public PermohonanAtasPihakBerkepentingan findByPermohonan(Permohonan permohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", permohonan.getIdPermohonan());
        return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByPermohonanList(Permohonan permohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", permohonan.getIdPermohonan());
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByPermohonanPihak(String idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.permohonanPihak.idPermohonanPihak = :idMohonPihak");
        q.setString("idMohonPihak", idPermohonanPihak);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByPermohonanPihakAndIdMohon(String idPermohonanPihak, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.permohonanPihak.idPermohonanPihak = :idMohonPihak "
                + "and p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idMohonPihak", idPermohonanPihak);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByPemohonPihakAndIdMohon(String idPemohon, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.pemohon.idPemohon = :idPemohon "
                + "and p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPemohon", idPemohon);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByHakmilikPihakPihakAndIdMohon(String idHakmilikPihakBerkepentingan, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p WHERE p.pihakBerkepentingan.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan "
                + "and p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findPihakByIdMohonIdHakmilik(Permohonan permohonan, Hakmilik hm) {
        Session session = sessionProvider.get();
        StringBuilder sb = new StringBuilder("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p ")
                .append("Where p.permohonan.idPermohonan = :idMohon ")
                .append("And p.hakmilik.idHakmilik = :idHakmilik");
        Query q = session.createQuery(sb.toString());
        q.setString("idMohon", permohonan.getIdPermohonan());
        q.setString("idHakmilik", hm.getIdHakmilik());
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findPihakByIdMohonIdHakmilik2(String idPermohonan, String idHakmlik) {
        Session session = sessionProvider.get();
        StringBuilder sb = new StringBuilder("SELECT p FROM etanah.model.PermohonanAtasPihakBerkepentingan p ")
                .append("Where p.permohonan.idPermohonan = :idMohon ")
                .append("And p.hakmilik.idHakmilik = :idHakmilik");
        Query q = session.createQuery(sb.toString());
        q.setString("idMohon", idPermohonan);
        q.setString("idHakmilik", idHakmlik);
        return q.list();
    }

    public PermohonanAtasPihakBerkepentingan findByAtasPihak(Long idHakmilikPihakBerkepentingan, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pihakBerkepentingan.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setLong("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan).setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByAtasPihakList(Long idHakmilikPihakBerkepentingan, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pihakBerkepentingan.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setLong("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan).setString("idPermohonan", idPermohonan);
        return q.list();
    }

}
