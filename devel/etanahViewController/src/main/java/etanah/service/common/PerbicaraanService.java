/**
 *
 * @author nordiyana
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.model.Hakmilik;
import etanah.model.KodDaerah;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanJUBL;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.util.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class PerbicaraanService {

    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public boolean saveSingleHakmilikPerbicaraan(HakmilikPerbicaraan hp) {
        hp = (HakmilikPerbicaraan) hakmilikPerbicaraanDAO.saveOrUpdate(hp);
        return (hp != null);
    }

    @Transactional
    public HakmilikPerbicaraan saveOrUpdateHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        return hakmilikPerbicaraanDAO.saveOrUpdate(hakmilikPerbicaraan);
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByIdMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.lokasiBicara != 'Mahkamah Tinggi'");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }
    
     public HakmilikPerbicaraan getHakmilikPerbicaraanByIdMH3(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.lokasiBicara != 'Mahkamah Tinggi'");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByIdMH2(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.catatan = 'Lulus'");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByIdBicara(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.idPerbicaraan = :idPerbicaraan");
        q.setParameter("idPerbicaraan", idPerbicaraan);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByIdMHByTMPTBicara(long idMH, String lokasiBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.lokasiBicara = :lokasiBicara ");
        q.setParameter("idMH", idMH);
        q.setParameter("lokasiBicara", lokasiBicara);
        return (HakmilikPerbicaraan) q.uniqueResult();

    }
    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanByTangguh(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.catatan = 'Tangguh'");
        q.setParameter("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPerbicaraan> findBicara(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return q.list();

    }

    public HakmilikPerbicaraan findAmbilHakmilikBicaraFindByIdSblm(String idSebelum) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.ambil.HakmilikPerbicaraan b where b.permohonan.idPermohonan = :idSebelum");
        q.setParameter("idSebelum", idSebelum);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }
    
     public PermohonanJUBL findPermohonanJUBLbyid(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.PermohonanJUBL b where b.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return (PermohonanJUBL) q.uniqueResult();
    }

//    public HakmilikPerbicaraan getHakmilikPerbicaraanByIdMH3 (long idPerbicaraan){
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT hb, pk FROM etanah.model.ambil.HakmilikPerbicaraan hb" +
//                "where hb.idPerbicaraan = :idPerbicaraan");
//        q.setParameter("idPerbicaraan", idPerbicaraan);
//        return (HakmilikPerbicaraan) q.uniqueResult();
//    }
//
//    public PerbicaraanKehadiran getHakmilikPerbicaraanByIdMH4 (long idPerbicaraan){
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT pk FROM etanah.model.ambil.PerbicaraanKehadiran pk" +
//                "where hb.idPerbicaraan = :idPerbicaraan");
//        q.setParameter("idPerbicaraan", idPerbicaraan);
//        return (PerbicaraanKehadiran) q.uniqueResult();
//    }
    @Transactional
    public void deleteAllRL(HakmilikPerbicaraan h) {
        hakmilikPerbicaraanDAO.delete(h);

    }

    public List<HakmilikPermohonan> findByHakmilikPermohonanList(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct n FROM etanah.model.ambil.HakmilikPerbicaraan m, etanah.model.HakmilikPermohonan n WHERE m.hakmilikPermohonan.id = n.id and n.permohonan.idPermohonan = :idPermohonan and m.catatan != 'Lulus' ORDER BY n.hakmilik.idHakmilik ASC");
//        q.setParameter("idMH", idMH);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList(long idMH) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH order by m.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanByIdMHList(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.lokasiBicara != 'Mahkamah Tinggi'");
        q.setLong("idMH", idMH);
        return q.list();
    }
}

