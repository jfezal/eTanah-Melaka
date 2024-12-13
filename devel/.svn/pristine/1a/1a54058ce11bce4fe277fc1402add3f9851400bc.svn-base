/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.NotisDAO;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mdizzat.mashrom
 */
public class NotisService {

    @Inject
    Notis notis;
    @Inject
    NotisDAO notisDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public Notis saveOrUpdate(Notis j) {
        return notisDAO.saveOrUpdate(j);
    }

    @Transactional
    public Notis findById(Long j) {
        return notisDAO.findById(j);
    }

    @Transactional
    public void deleteAll(Notis j) {
        notisDAO.delete(j);
    }

    public List<Notis> getSenaraiNotis(String idMohon) {
        System.out.println("idMohon service : " + idMohon);
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :permohonan");
        q.setParameter("permohonan", idMohon);
        return q.list();
    }

    public List<Notis> getNotis(long idNotis) {
        System.out.println("idNotis service : " + idNotis);
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.idNotis = :id_notis");
        q.setParameter("id_notis", idNotis);
        return q.list();
    }

    public List<Notis> getListNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> findByKodNotis(String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.kodNotis.kod = :kod_notis");
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> findByKodNotis2(String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT n FROM etanah.model.Notis n, etanah.model.Permohonan m WHERE m.idPermohonan = n.permohonan.idPermohonan AND n.kodNotis.kod = :kod_notis");
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public Notis getNotis(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idMohon "
                + "AND m.warta.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idMohon", idMohon);
        q.setParameter("idHakmilik", idHakmilik);
        return (Notis) q.uniqueResult();
    }

    public List<Notis> getNotisBorang(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<Notis> getNotisBorangK(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idMohon and m.kodNotis.kod='NBK'");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<Notis> getNotisBorangH(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idMohon and m.kodNotis.kod='NBH'");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public Notis getNotisByidDokumen(long idDokumen) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT n FROM etanah.model.Notis n WHERE n.dokumenNotis.idDokumen = :idDokumen");
        q.setLong("idDokumen", idDokumen);
        return (Notis) q.uniqueResult();
    }

    public Dokumen getNotisBorangByidDokumen(long idDokumen) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT n FROM etanah.model.Dokumen n WHERE n.idDokumen = :idDokumen");
        q.setLong("idDokumen", idDokumen);
        return (Dokumen) q.uniqueResult();
    }

    public Penilaian getNilaibyIdBicara(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.Penilaian m, etanah.model.ambil.PerbicaraanKehadiran n WHERE n.perbicaraan.idPerbicaraan = :idBicara AND m.kehadiran.idKehadiran = n.idKehadiran");
        q.setLong("idBicara", idBicara);
        return (Penilaian) q.uniqueResult();
    }
}
