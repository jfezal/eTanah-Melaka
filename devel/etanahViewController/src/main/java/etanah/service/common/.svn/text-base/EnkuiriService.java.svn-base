/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPeguamDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Peguam;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPeguam;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
public class EnkuiriService {

    private static final Logger LOG = Logger.getLogger(EnkuiriService.class);
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanlogDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanrujukanluarDAO;
    @Inject
    private PermohonanPeguamDAO ppeguamDAO;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private PembidaDAO pembidaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public void saveEnkuiri(Enkuiri e) {
        enkuiriDAO.saveOrUpdate(e);
        System.out.println("idEnkuiri :" + e.getIdEnkuiri());
    }

    @Transactional
    public Long save(Enkuiri e) {
        Long idEnquiri;
        idEnquiri = enkuiriDAO.saveOrUpdate(e).getIdEnkuiri();
        return idEnquiri;
    }

    @Transactional
    public Long simpan(Lelongan lel) {
        Long idLelong;
        idLelong = lelonganDAO.saveOrUpdate(lel).getIdLelong();
        return idLelong;
    }

    @Transactional
    public Long simpanLelong(Lelongan lel) {
        Lelongan lelo = new Lelongan();
        lelo = lelonganDAO.saveOrUpdate(lel);
        return lelo.getIdLelong();

    }
//
//     @Transactional
//     public Long simpanNotis(Notis notis){
//         Notis note = new Notis();
//         note = notisDAO.saveOrUpdate(notis);
//         return note.getIdNotis();
//     }

    @Transactional
    public void saveNotis(Notis notis) {
        notisDAO.save(notis);
        System.out.println("idNotis :" + notis.getIdNotis());
    }

    @Transactional
    public void saveOrUpdate(Enkuiri en) {
        enkuiriDAO.saveOrUpdate(en);
    }

    @Transactional
    public Notis saveOrUpdate(Notis j) {
        return notisDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveMohonFasa(FasaPermohonan fm) {
        fasaPermohonanDAO.saveOrUpdate(fm);
        System.out.println("idFasa :" + fm.getIdFasa());
    }

    @Transactional
    public void saveDokumenNotis(Dokumen docu) {
        dokumenDAO.save(docu);
        System.out.println("idDocument :" + docu.getIdDokumen());
    }

//    @Transactional
//    public Long simpanDocument(Notis doc) {
//        Long idDocument;
//        idDocument = notisDAO.saveOrUpdate(doc).getDokumenNotis();
//        return idEnquiri;
//    }
    @Transactional
    public Long savePermohonanPihak(Pihak pihak, PermohonanPihak permohonanPihak) {
        Pihak p = new Pihak();
        PermohonanPihak pp = new PermohonanPihak();
        p = pihakDAO.saveOrUpdate(pihak);
        permohonanPihak.setPihak(p);
        pp = permohonanPihakDAO.saveOrUpdate(permohonanPihak);
        return pp.getIdPermohonanPihak();

    }

    @Transactional
    public Long savePihak(Pihak pihak, PermohonanPihak permohonanPihak) {
        Pihak p = new Pihak();
        PermohonanPihak pp = new PermohonanPihak();
        p = pihakDAO.saveOrUpdate(pihak);
        permohonanPihak.setPihak(p);
        pp = permohonanPihakDAO.saveOrUpdate(permohonanPihak);
        return p.getIdPihak();

    }

    @Transactional
    public Long savePihakPembida(Pihak pihak, Pembida pembida) {
        Pihak p = new Pihak();
        Pembida pem = new Pembida();
        p = pihakDAO.saveOrUpdate(pihak);
        pembida.setPihak(p);
        pem = pembidaDAO.saveOrUpdate(pembida);
        return p.getIdPihak();

    }

    @Transactional
    public void saveOrUpdate(Pembida pem) {
        pembidaDAO.update(pem);
    }

    @Transactional
    public void saveOrUpdate1(Pembida pem) {
        pembidaDAO.saveOrUpdate(pem);
    }

    @Transactional
    public void savePihakLelong(Pihak pihak, Lelongan lelong, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        pihak.setInfoAudit(ia);
        pihakDAO.saveOrUpdate(pihak).getIdPihak();
//        lelong.setPembida(pihak);
        lelonganDAO.update(lelong);
    }

    @Transactional
    public void deletePermohonanPihak(PermohonanPihak permohonanPihak) {
        permohonanPihakDAO.delete(permohonanPihak);
    }

    public PermohonanPihak findById(String idPermohonanPihak) {
        return permohonanPihakDAO.findById(Long.valueOf(idPermohonanPihak));
    }

    @Transactional
    public void deletePihak(Pihak pihak) {
        pihakDAO.delete(pihak);
    }

    public List<Notis> getListNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getSenaraiNotis(String idMohon) {
        System.out.println("idMohon service : " + idMohon);
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<Notis> getNotis(long idNotis) {
        System.out.println("idNotis service : " + idNotis);
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.idNotis = :id_notis");
        q.setParameter("id_notis", idNotis);
        return q.list();
    }

    public List<PermohonanPihak> getsenaraiPermohonanPihak(String idMohon, String jenis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT pp FROM etanah.model.PermohonanPihak pp WHERE pp.permohonan.idPermohonan = :id_mohon AND pp.jenis.kod = :kod_pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_pihak", jenis);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getsenaraiHakmilikPihakBerkepentingan(String idHakmilik, String jenis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT hp FROM etanah.model.HakmilikPihakBerkepentingan hp WHERE hp.hakmilik.idHakmilik = :id_hakmilik AND hp.jenis.kod = :kod_pb");
        q.setParameter("id_hakmilik", idHakmilik);
        q.setParameter("kod_pb", jenis);
        return q.list();
    }

    public List<Lelongan> getSenaraiLelong(String idPermohonan, String idHakmilik) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT L FROM etanah.model.Lelongan L, etanah.model.Enkuiri E WHERE E.idEnkuiri = L.enkuiri.idEnkuiri AND"
                + " E.permohonan.idPermohonan = :idMohon and E.status.kod = 'AK' and L.pembida is not null and L.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik");
        query.setParameter("idMohon", idPermohonan);
        query.setParameter("idHakmilik", idHakmilik);
        return query.list();
    }

    public List<Enkuiri> getSenaraiEnkuiri(String idPermohonan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT E FROM etanah.model.Enkuiri E WHERE E.permohonan.idPermohonan = :idMohon ORDER BY E.idEnkuiri ASC");
//        Query query = ses.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
        query.setParameter("idMohon", idPermohonan);
        return query.list();
    }

    public List<FolderDokumen> getSenaraiFolder(String idFolder) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT F FROM etanah.model.FolderDokumen F WHERE F.folderdokumen.idFolder = :idFolder ORDER BY F.idFolder ASC");
//        Query query = ses.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
        query.setParameter("idFolder", idFolder);
        return query.list();
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuar(String idPermohonan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.PermohonanRujukanLuar P WHERE P.permohonan.idPermohonan = :idMohon ORDER BY P.idRujukan ASC");
//        Query query = ses.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
        query.setParameter("idMohon", idPermohonan);
        return query.list();
    }

    public List<Enkuiri> getListEnkuiri(String idPermohonan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT E FROM etanah.model.Enkuiri E WHERE E.permohonan.idPermohonan = :idPermohonan and E.status.kod = 'AK'");
        query.setParameter("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<Permohonan> getListPeguam(String idPermohonan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Permohonan P WHERE P.idPermohonan = :idPermohonan");
        query.setParameter("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<Lelongan> getALLLelongan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query query = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.baki is not null and m.permohonan.idPermohonan = :idPermohonan");
        query.setParameter("idPermohonan", idPermohonan);
        return query.list();
    }

//    public List<Lelongan> getALLLelong() {
//        String query = "SELECT m FROM etanah.model.Lelongan m WHERE to_char(sysdate,'dd/MM/yyyy') = to_char(m.tarikhLelong,'dd/MM/yyyy') and m.tarikhLelong is not null and m.kodStatusLelongan.kod ='AK' order by m.idLelong asc";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        return q.list();
//    }
    // COMMENT JAP TUK SEMENTARA (filter date)
//    public List<Lelongan> getALLLelong() {
//        String query = "SELECT DISTINCT m.permohonan.idPermohonan FROM etanah.model.Lelongan m WHERE to_char(sysdate,'dd/MM/yyyy') = to_char(m.tarikhLelong,'dd/MM/yyyy') and m.tarikhLelong is not null and m.kodStatusLelongan.kod ='AK'";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        return q.list();
//    }
    // WAT NI DLU..XYAH FILTER DATE ...WAT SEENTARA (x filter date)
    public List<Lelongan> getALLLelong(String kod) {
        String query = "SELECT DISTINCT m.permohonan.idPermohonan FROM etanah.model.Lelongan m WHERE m.tarikhLelong is not null and m.kodStatusLelongan.kod ='AK' and m.enkuiri.permohonan.cawangan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }
    
        public List<Lelongan> getALLLelongforJLB() {
        String query = "SELECT DISTINCT m.permohonan.idPermohonan FROM etanah.model.Lelongan m WHERE m.tarikhLelong is not null and m.kodStatusLelongan.kod ='AK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Lelongan> getLeloganbyMohon(Long idLelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.idLelong = :idLelong order by l.idLelong asc");
        q.setParameter("idLelong", idLelong);
        return q.list();
    }

    public List<Lelongan> getLeloganbyMohonA(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan and l.kodStatusLelongan.kod ='AK'order by l.idLelong asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Lelongan getStatusEnkuiri(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan order by l.idLelong asc ");
        q.setParameter("idPermohonan", idPermohonan);
        return (Lelongan) q.uniqueResult();
    }

    public Lelongan getLelongJeee(Long idLelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.idLelong = :idLelong order by l.idLelong asc ");
//        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idLelong", idLelong);
        return (Lelongan) q.uniqueResult();
    }

    public Lelongan getLelongIdMohon(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan order by l.idLelong asc ");
//        q.setString("idPermohonan", idPermohonan);
        q.setParameter("idPermohonan", idPermohonan);
        return (Lelongan) q.uniqueResult();
    }

    public Lelongan findLelong(String idPermohonan, String idHakmilik) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan and l.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (Lelongan) q.uniqueResult();
    }
    
    public Pembida findPihak(Long idPihak) {
        String query = "Select p FROM etanah.model.Pembida p WHERE p.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPihak", idPihak);
        return (Pembida) q.uniqueResult();
    }
    
        public Pembida findPihakBJ(Long idLelong, Long idPihak) {
        String query = "Select p FROM etanah.model.Pembida p WHERE p.lelong.idLelong = :idLelong and p.pihak.idPihak = :idPihak AND p.kodStsPembida.kod = 'BJ'";
        Query q = sessionProvider.get().createQuery(query);
         q.setParameter("idLelong", idLelong);
        q.setParameter("idPihak", idPihak);
        return (Pembida) q.uniqueResult();
    }

    public Pembida getLelongPembidaPihakJeee(Long idPembida) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Pembida p WHERE p.idPembida = :idPembida order by p.idPembida asc");
//        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idPembida", idPembida);
        return (Pembida) q.uniqueResult();
    }

    public Pembida getPembidaID(String idPembida) {
        String query = "SELECT pem FROM etanah.model.Pembida pem WHERE pem.idPembida = :idPembida";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPembida", idPembida);
        return (Pembida) q.uniqueResult();
    }

    public List<Pembida> getListPembida(Long idLelong) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Pembida P WHERE P.lelong.idLelong = :idLelong order by P.idPembida asc");
        query.setParameter("idLelong", idLelong);
        return query.list();
    }
       public Pembida getListPembidaByBerjaya(Long idLelong, String kodStatusPembida) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Pembida P WHERE P.lelong.idLelong = :idLelong and P.kodStsPembida.kod = :kodStatusPembida order by P.idPembida asc");
        query.setParameter("idLelong", idLelong);
        query.setParameter("kodStatusPembida", kodStatusPembida);
       return (Pembida) query.uniqueResult();
    }

    public List<Pembida> getListPembidaPihak(Long idPihak) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Pembida P WHERE P.pihak.idPihak = :idPihak");
        query.setParameter("idPihak", idPihak);
        return query.list();
    }

    public Pembida getLelongPembida(Long idLelong) {
        Session ses = sessionProvider.get();
        Query q = ses.createQuery("SELECT P FROM etanah.model.Pembida P WHERE P.lelong.idLelong = :idLelong");
        q.setParameter("idLelong", idLelong);
        return (Pembida) q.uniqueResult();
    }

    public List<Pembida> getALLPembida() {
        String query = "SELECT p FROM etanah.model.Pembida p order by p.idPembida asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pembida> getListPembidawithHakmilik(Long idLelong, String idHakmilik) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Pembida P,etanah.model.Lelongan L WHERE P.lelong.idLelong = :idLelong AND L.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik AND P.lelong.idLelong = L.idLelong");
        query.setParameter("idLelong", idLelong);
        query.setParameter("idHakmilik", idHakmilik);
        return query.list();
    }

    public Pembida getPihakIC(String noPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT pem FROM etanah.model.Pembida pem WHERE pem.pihak.noPengenalan = :noPengenalan ");
        q.setParameter("noPengenalan", noPengenalan);
        return (Pembida) q.uniqueResult();
    }

    public List<FolderDokumen> getALLFolder() {
        String query = "SELECT F FROM etanah.model.FolderDokumen F WHERE F.tajuk is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public Hakmilik findHakmilik(String idHakmilik) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return (Hakmilik) q.uniqueResult();

    }

    @Transactional
    public void saveResetBaki(Lelongan lelong, Pengguna pengguna) {
        InfoAudit ia = lelong.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        lelonganDAO.update(lelong);
    }

    @Transactional
    public Long saveRujukanLuar(PermohonanRujukanLuar rujukanluar) {
        Long idRujukan;
        idRujukan = permohonanrujukanluarDAO.saveOrUpdate(rujukanluar).getIdRujukan();
        return idRujukan;
    }

    @Transactional
    public List<KandunganFolder> getSenaraifolder(String idFolder) {
        LOG.info("idFolder : " + idFolder);
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT k FROM etanah.model.KandunganFolder k WHERE k.folder = :id_folder");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    @Transactional
    public Long saveFasaLog(FasaPermohonanLog fpl) {
        Long idLog;
        idLog = fasaPermohonanlogDAO.saveOrUpdate(fpl).getIdLog();
        return idLog;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan(String idPermohonan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT F FROM etanah.model.FasaPermohonan F WHERE F.permohonan.idPermohonan = :id_mohon");
//        Query query = ses.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
        query.setParameter("id_mohon", idPermohonan);
        return query.list();
    }

    @Transactional
    public List<PermohonanPeguam> listPeguamY(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT PPP FROM etanah.model.PermohonanPeguam PPP WHERE PPP.idPermohonan.idPermohonan = :id_mohon order by PPP.infoAudit.tarikhMasuk desc");
        q.setParameter("id_mohon", idPermohonan);
        return q.list();
    }

    @Transactional
    public List<PermohonanPeguam> listPeguamAK(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT PPP FROM etanah.model.PermohonanPeguam PPP WHERE PPP.idPermohonan.idPermohonan = :id_mohon and PPP.aktif = 'Y'");
        q.setParameter("id_mohon", idPermohonan);
        return q.list();
    }

    @Transactional
    public Long saveFasaMohon(FasaPermohonan fp) {
        Long idFasa;
        idFasa = fasaPermohonanDAO.saveOrUpdate(fp).getIdFasa();
        return idFasa;
    }

    @Transactional
    public void saveOrUpdate(PermohonanPeguam ppeguam) {
        ppeguamDAO.saveOrUpdate(ppeguam);
    }

    @Transactional
    public void saveOrUpdatePeguam(Peguam peguam) {
        peguamDAO.saveOrUpdate(peguam);
    }
}
