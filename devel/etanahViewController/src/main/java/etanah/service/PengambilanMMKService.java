/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.etapp.EtappPermohonan;
import etanah.model.etapp.TBLINTPPTDERAFMMK;
import etanah.model.etapp.TBLINTPPTHAKMILIK;
import etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nordiyana
 */
public class PengambilanMMKService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fp) {
        fasaPermohonanDAO.saveOrUpdate(fp);
    }

    @Transactional
    public void deleteKertas(PermohonanKertas pk) {
        permohonanKertasDAO.delete(pk);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan pkk) {
        permohonanKertasKandunganDAO.delete(pkk);
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.update(permohonan);
    }

    @Transactional
    public void simpanKertas(PermohonanKertas permohonanKertas, PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    public PermohonanKertas findById(String idKertas) {
        return permohonanKertasDAO.findById(Long.valueOf(idKertas));
    }

    public List<PermohonanKertasKandungan> findByKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=3 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertas> findPermohonanKertasByIdPermohonan(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.tajuk = :tajuk");
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasKandunganByJabatan(String idPermohonan, String jabatanTeknikal) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk, etanah.model.PermohonanKertas pk where pk.idKertas = pkk.kertas.idKertas and pk.permohonan.idPermohonan = :idMohon and pkk.subtajuk = :subtajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("subtajuk", jabatanTeknikal);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findKertasKandunganByIdKertas(String idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk where pkk.kertas.idKertas = :idKertas");
        q.setString("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertas findKertasByTajuk(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idMohon and pk.tajuk = :tajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("tajuk", tajuk);
        return (PermohonanKertas) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public PermohonanKertas findMohonKertasByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findHakmilikByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hp from etanah.model.HakmilikPermohonan hp where ph.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public EtappPermohonan getNofail(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select e from etanah.model.etapp.EtappPermohonan e where e.mohon.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (EtappPermohonan) q.uniqueResult();
    }

    public TBLPPTINTDERAFMMKTAJUK findTajuk(String noFail) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mt from etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK mt where mt.noFailJKPTG = :noFail");
        q.setString("noFail", noFail);
        return (TBLPPTINTDERAFMMKTAJUK) q.uniqueResult();
    }

    @Transactional
    public PermohonanKertas savePermohonanKertas(PermohonanKertas kertas) {
        return permohonanKertasDAO.saveOrUpdate(kertas);
    }

    public List<PermohonanKertasKandungan> findListByIdkertas(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public List<TBLINTPPTDERAFMMK> findMMKdraf(String noFail) {
        String query = "SELECT b FROM etanah.model.etapp.TBLINTPPTDERAFMMK b where b.noFailJKPTG = :noFail";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        return q.list();
    }

    public List<TBLINTPPTDERAFMMK> findMMKdrafKand1(String noFail, String item) {
        String query = "SELECT b FROM etanah.model.etapp.TBLINTPPTDERAFMMK b where b.noFailJKPTG = :noFail and b.jenisMaklumatMMK like :item";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        q.setString("item", "%" + item + "%");
        return q.list();
    }

    public List<PermohonanKertasKandungan> findMMKdrafKandNew(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas =:idKertas and b.bil =:bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<TBLINTPPTDERAFMMK> findMMKdrafKand(String noFail, String item) {
        String query = "SELECT b FROM etanah.model.etapp.TBLINTPPTDERAFMMK b where b.noFailJKPTG =:noFail and b.jenisMaklumatMMK =:item";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        q.setString("item", item);
        return q.list();
    }

    public PermohonanKertasKandungan findMMKdrafKandNew1(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas =:idKertas and b.bil =:bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List FindDaerah(String noFail) {
        String query = "SELECT DISTINCT b.kodDaerah FROM etanah.model.etapp.TBLINTPPTHAKMILIK b where b.noFailJKPTG = :noFail";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        return q.list();
    }

    public PermohonanKertas findMohonKertasByIdMohon(String idPermohonan, String kodDokumen) {
       Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanKertas) q.uniqueResult();
    }
}
