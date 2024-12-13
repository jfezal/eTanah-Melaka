/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author md.nurfikri
 */
public class PermohonanPihakService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;

    @Inject
    private PermohonanDAO permohonanDAO;

    @Inject
    PemohonService pemohonService;
    private final static String[] JENIS_TUAN_TANAH = {
        "PM",
//        "PG",
        "CP",
        "PA",
        "WAR",
        "ASL",
        "JA",
        "JK",
        "KL",
        "PDP",
        "PK",
        "PLK",
        "PP",
        "RP",
        "WKL",
        "WPA",
        "PL",
        "WS",
        "PH",
        "PML"
    };

    public List<PermohonanPihak> getSenaraiPmohonPihak(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByKod(String idMohon, String jenisPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod = :jenis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        return q.list();
    }

    public List<PermohonanPihak> findPermohonanPihakByIdPihak(Pihak phk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.pihak.idPihak = :idPihak");
        q.setLong("idPihak", phk.getIdPihak());
        return q.list();
    }

    public List<PermohonanPihak> findPermohonanPihakByIdPihakNotTwoKod(Pihak phk, String jenisPihak, String jenisPihak2) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.pihak.idPihak = :idPihak AND p.jenis.kod <> :jenis AND p.jenis.kod <> :jenis2");
        q.setLong("idPihak", phk.getIdPihak());
        q.setParameter("jenis", jenisPihak);
        q.setParameter("jenis2", jenisPihak2);
        return q.list();
    }

    public List<PermohonanPihak> findPermohonanPihakByIdPihakNotTwoKodAndJabatan(Pihak phk, String jenisPihak, String jenisPihak2, String jabatan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.pihak.idPihak = :idPihak AND p.jenis.kod <> :jenis AND p.jenis.kod <> :jenis2 AND p.permohonan.kodUrusan.jabatan.kod = :jabatan");
        q.setLong("idPihak", phk.getIdPihak());
        q.setParameter("jenis", jenisPihak);
        q.setParameter("jenis2", jenisPihak2);
        q.setParameter("jabatan", jabatan);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByKodAndIdHakmilik(String idMohon, String jenisPihak, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod = :jenis AND m.hakmilik.idHakmilik = :id_hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik and m.jenis.kod in "
                + "('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS')");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiMohonPihakForMultipleHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik order by m.nama asc");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);

        return q.list();
    }

    public List<PermohonanPihak> getAllSenaraiPmohonPihakByHakmilikAndMohon(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getAllSenaraiPmohonPihakByHakmilikAndMohonAktif(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        //   Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik");
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m,etanah.model.HakmilikPihakBerkepentingan hk WHERE hk.hakmilik.idHakmilik=m.hakmilik.idHakmilik and hk.aktif='Y' and hk.pihak.idPihak=m.pihak.idPihak and m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_hakmilik ");

        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByIdHmAndIdMohon(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon AND m.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idMohon", idMohon);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }
    
    public List<PermohonanPihak> findMohonPihakByIdHmAndIdMohonBaru(String idPermohonanLama, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE "
                + "m.idPermohonanLama = :idPermohonanLama "
                + "AND m.hakmilik.idHakmilik = :idHakmilik "
                + "and m.kodStatus ='T'");
        q.setParameter("idPermohonanLama", idPermohonanLama);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByKodAndNilai(String idMohon, String jenisPihak, String dNilai) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod = :jenis and m.dalamanNilai1 = :d_Nilai");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        q.setParameter("d_Nilai", dNilai);
        return q.list();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak AND kodStatus != 'T'");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }
    
    public List<PermohonanPihak> getSenaraiPmohonPihakByIdMohonIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return q.list();        
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihakKodPihak(String idMohon, String idHakmilik, Long idPihak, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak AND m.jenis.kod = :kod");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        q.setParameter("kod", kod);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihakList(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihakOnly(String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihakListAktif(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m,etanah.model.HakmilikPihakBerkepentingan hk WHERE hk.hakmilik.idHakmilik=m.hakmilik.idHakmilik and hk.aktif='Y' and hk.pihak.idPihak=m.pihak.idPihak and m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihak2(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.noHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByNotKod(String idMohon, String jenisPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod <> :jenis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByNotKodAndIdHakmilik(String idMohon, String jenisPihak, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod <> :jenis AND m.hakmilik.idHakmilik = :id_hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByNotTwoKod(String idMohon, String jenisPihak, String jenisPihak2) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod <> :jenis AND m.jenis.kod <> :jenis2");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        q.setParameter("jenis2", jenisPihak2);
        return q.list();
    }

    public PermohonanPihak getPmohonPihakByKodAndIdHakmilik(String idMohon, String idHakmilik, Long idPihak, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak AND m.jenis.kod = :kod_jenis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        q.setParameter("kod_jenis", kod);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(String idMohon, String jenisPihak, String jenisPihak2, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.jenis.kod <> :jenis AND m.jenis.kod <> :jenis2 AND m.hakmilik.idHakmilik = :id_hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("jenis", jenisPihak);
        q.setParameter("jenis2", jenisPihak2);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getAllSenaraiPmohonPihakByHakmilikAndMohonNotKod(String idMohon, String idHakmilik, String jenisPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik and m.jenis.kod <> :jenis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        q.setParameter("jenis", jenisPihak);
        return q.list();
    }

    public PermohonanPihak getPmohonPihakByIdPihakNotKod(String idMohon, Long idPihak, String jenis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPihak = :id_Pihak AND m.jenis.kod <> :jenis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        q.setParameter("jenis", jenis);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiMohonPihakByIdPihakIdHakmilik(Long idPihak, String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.pihak.idPihak = :idPihak " + "AND m.hakmilik.idHakmilik = :idHakmilik " + "AND m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPihak", idPihak);
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiMohonPihakByIdPihakIdHakmilik2(Long idPihak, String idHakmilik, String idPermohonan, Long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.idPermohonanPihak = :idMP AND m.pihak.idPihak = :idPihak " + " AND m.hakmilik.idHakmilik = :idHakmilik " + "AND m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPihak", idPihak);
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idMP", idMP);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiMohonPihakByIdPihak(String idPermohonan, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan AND  m.pihak.idPihak = :idPihak");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idPihak", idPihak);
        return q.list();
    }
    
    public List<PermohonanPihak> getSenaraiMohonPihakByIdMohonSyerBersama(String idPermohonan) {
//        String idPihakKongsi = null;
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan AND  m.syerBersama in ('Y')  AND m.idPihakKongsi is null order by m.idPermohonanPihak asc");
        q.setParameter("idPermohonan", idPermohonan);     
//        q.setParameter("idPihakKongsi", idPihakKongsi);   
        return q.list();
    }
    
    public List<PermohonanPihak> getSenaraiMohonPihakByIdMohonSyerBersamaBerkumpulan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT  m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan AND  m.syerBersama in ('Y')  AND m.idPihakKongsi is not null");
        q.setParameter("idPermohonan", idPermohonan);     
        return q.list();
    }
    
    public List<PermohonanPihak> getSenaraiMohonPihakByIdMohonIdPihakKongsi(String idPermohonan, Integer idPihakKongsi) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT  m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan  AND m.idPihakKongsi = :idPihakKongsi");
        q.setParameter("idPermohonan", idPermohonan);   
        q.setParameter("idPihakKongsi", idPihakKongsi);  
        return q.list();
    }
    
    //error fix 
    public List<PermohonanPihak> getSenaraiMohonPihakByKodAndJenisPengenalan(String kodPengenalan, String noPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.jenisPengenalan.kod = :kod AND m.noPengenalan = :no "
                + "AND m.pihak is not null");
        q.setParameter("kod", kodPengenalan);
        q.setParameter("no", noPengenalan);
        return q.list();
    }

    public PermohonanPihak getSenaraiPermohonanPihak(Long idPihak, String idHakmilik, Permohonan permohonan, String jenisPihak) {
        StringBuilder sb = new StringBuilder("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.pihak.idPihak = :idPihak").append(" AND m.hakmilik.idHakmilik = :idHakmilik").append(" AND m.permohonan.idKumpulan = :idKump").append(" AND m.permohonan.kumpulanNo < :kumpNo");
        if (StringUtils.isNotBlank(jenisPihak)) {
            sb.append(" AND m.jenis.kod = :kod");
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(sb.toString());
        q.setParameter("idPihak", idPihak);
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idKump", permohonan.getIdKumpulan());
        q.setParameter("kumpNo", permohonan.getKumpulanNo());
        if (StringUtils.isNotBlank(jenisPihak)) {
            q.setParameter("kod", jenisPihak);
        }

        List<PermohonanPihak> senarai = q.list();
        if (senarai.size() > 0) {
            return (PermohonanPihak) q.list().get(0);
        }

        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPermohonanPihak2(Long idPihak, String idHakmilik, String jenisPihak) {
        StringBuilder sb = new StringBuilder("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.pihak.idPihak = :idPihak").append(" AND m.hakmilik.idHakmilik = :idHakmilik");
        if (StringUtils.isNotBlank(jenisPihak)) {
            sb.append(" AND m.jenis.kod = :kod");
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(sb.toString());
        q.setParameter("idPihak", idPihak);
        q.setParameter("idHakmilik", idHakmilik);
        if (StringUtils.isNotBlank(jenisPihak)) {
            q.setParameter("kod", jenisPihak);
        }

        List<PermohonanPihak> senarai = q.list();
        if (senarai.size() > 0) {
            return (PermohonanPihak) q.list().get(0);
        }

        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getMohonPihakByIdPihakIdHakmilikNotKod(Long idPihak, String idHakmilik, String idPermohonan, String jenis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.pihak.idPihak = :idPihak " + "AND m.hakmilik.idHakmilik = :idHakmilik " + "AND m.permohonan.idPermohonan = :idPermohonan AND m.jenis.kod <> :jenis");
        q.setParameter("idPihak", idPihak);
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenis", jenis);

        List<PermohonanPihak> list = q.list();
        if (list.size() > 0) {
            return (PermohonanPihak) q.list().get(0);
        }
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> senaraiTuanTanahBerkumpulan(String idKumpulan, String idPermohonan,
            String idHakmilik, boolean bukanPemilik, boolean semuaPihak, int kumpNo) {

        List<Long> senaraiPemohonSementara = new ArrayList<Long>();
        List<HakmilikPihakBerkepentingan> senaraiPihakSementara = new ArrayList<HakmilikPihakBerkepentingan>();

        StringBuilder q = new StringBuilder("Select mp from etanah.model.Pemohon mp, etanah.model.Permohonan m")
                .append(" where mp.permohonan.idPermohonan = m.idPermohonan")
                .append(" and m.idKumpulan = :idKump") //                .append(" and mp.jenis.kod in (:senaraiPihak)")
                .append(" and mp.hakmilik.idHakmilik = :idHakmilik")
                .append(" and mp.permohonan.idPermohonan not in (:idPermohonan)")
                .append(" and m.kumpulanNo <= :kumpNo ").append(" and mp.jenisPemohon in ('X')");

//        StringBuilder q = new StringBuilder("Select p from etanah.model.Pemohon p, etanah.model.Permohonan m")
//                .append(" where p.permohonan.idPermohonan = m.idPermohonan")
//                .append(" and p.permohonan.idPermohonan not in (:idPermohonan)")
//                .append(" and m.idKumpulan = :idKump")
//                .append(" and m.kumpulanNo <= :kumpNo")
//                .append(" and p.hakmilik.idHakmilik = :idHakmilik and p.jenisPemohon in ('X')");
        if (!semuaPihak) {
            if (bukanPemilik) {
                q.append(" and mp.jenis.kod not in (:senaraiPihak)");
            } else {
                q.append(" and mp.jenis.kod in (:senaraiPihak)");
            }
        }
        Session s = sessionProvider.get();

        Query query = s.createQuery(q.toString())
                .setParameter("idPermohonan", idPermohonan)
                .setString("idKump", idKumpulan)
                .setParameter("kumpNo", kumpNo)
                .setParameter("idHakmilik", idHakmilik);

        if (!semuaPihak) {
            query.setParameterList("senaraiPihak", JENIS_TUAN_TANAH);
        }

        List<Pemohon> senaraiPemohon = query.list();

        q = new StringBuilder("Select h from etanah.model.HakmilikPihakBerkepentingan h")
                .append(" where h.hakmilik.idHakmilik = :idHakmilik").append(" and h.aktif = 'Y'");

        if (!semuaPihak) {
            if (bukanPemilik) {
                q.append(" and h.jenis.kod not in (:senaraiPihak)");
            } else {
                q.append(" and h.jenis.kod in (:senaraiPihak)");
            }
        }

        query = sessionProvider.get().createQuery(q.toString()).setParameter("idHakmilik", idHakmilik);
        if (!semuaPihak) {
            query.setParameterList("senaraiPihak", JENIS_TUAN_TANAH);
        }

        List<HakmilikPihakBerkepentingan> senaraiPihak = query.list();
        senaraiPihakSementara = query.list();

        for (Pemohon pemohon : senaraiPemohon) {
            boolean f = true;
            for (HakmilikPihakBerkepentingan hp : senaraiPihakSementara) {
//                if (hp.getPihak().equals(pemohon.getPihak())) {
                if (hp.equals(pemohon.getHakmilikPihak())) {
                    f = false;
                    senaraiPihak.remove(hp);
                    break;
                }
            }
            if (f) {
                senaraiPemohonSementara.add(pemohon.getIdPemohon());
            }
        }

        q = new StringBuilder("Select mp from etanah.model.PermohonanPihak mp, etanah.model.Permohonan m")
                .append(" where mp.permohonan.idPermohonan = m.idPermohonan")
                .append(" and m.idKumpulan = :idKump")
                .append(" and mp.hakmilik.idHakmilik = :idHakmilik")
                .append(" and mp.permohonan.idPermohonan not in (:idPermohonan)")
                .append(" and m.kumpulanNo <= :kumpNo");
//                .append(" and m.kodUrusan.kod not in ('PHMM')"); // FIXME: temp solution : bugs for 0400SC2015004005 !!
//                .append(" and mp.permohonan.idPermohonan not in")
//                .append(" ( Select pm.permohonan.idPermohonan from etanah.model.Pemohon pm, etanah.model.Permohonan m")
//                .append(" where pm.permohonan.idPermohonan = m.idPermohonan")
//                .append(" and pm.permohonan.idPermohonan not in (:idPermohonan)")
//                .append(" and m.idKumpulan = :idKump and pm.hakmilik.idHakmilik = :idHakmilik and pm.jenisPemohon in ('X') )");
        if (senaraiPemohonSementara.size() > 0) {
            q.append(" and mp.pihak.idPihak not in (:senaraiPemohon)");
        }
        if (!semuaPihak) {
            if (bukanPemilik) {
                q.append(" and mp.jenis.kod not in (:senaraiPihak)");
            } else {
                q.append(" and mp.jenis.kod in (:senaraiPihak)");
            }
        }

        query = sessionProvider.get().createQuery(q.toString()).setString("idKump", idKumpulan).setParameter("idHakmilik", idHakmilik).setParameter("idPermohonan", idPermohonan).setParameter("kumpNo", kumpNo);
        if (senaraiPemohonSementara.size() > 0) {
            query.setParameterList("senaraiPemohon", senaraiPemohonSementara);
        }
        if (!semuaPihak) {
            query.setParameterList("senaraiPihak", JENIS_TUAN_TANAH);
        }

        List<PermohonanPihak> senaraiPihakBerkumpulan = query.list();
        List<PermohonanPihak> senaraiPihakBerkumpulanTemp = query.list();
        List<PermohonanPihak> senaraiPihakBerkumpulanV2 = new ArrayList<PermohonanPihak>();

        for (PermohonanPihak mp : senaraiPihakBerkumpulanTemp) {
            if (mp == null) {
                continue;
            }
            for (Pemohon p : senaraiPemohon) {
                if (p.getPihak().equals(mp.getPihak())
                        && (!p.getPermohonan().equals(mp.getPermohonan()))) {
                    senaraiPihakBerkumpulan.remove(mp);
                    senaraiPemohon.remove(p);
                    break;
                }
            }
        }

        //new feature. disbbkan ada bug semasa urusan PHMMK->TN->TN->GDL
        for (PermohonanPihak mp : senaraiPihakBerkumpulan) {
            PermohonanPihak pp = new PermohonanPihak();
            pp.setPihak(mp.getPihak());
            pp.setSyerPembilang(mp.getSyerPembilang() == null ? 1 : mp.getSyerPembilang());
            pp.setSyerPenyebut(mp.getSyerPenyebut() == null ? 1 : mp.getSyerPenyebut());
            pp.setHakmilik(mp.getHakmilik());
            pp.setJenis(mp.getJenis());
            pp.setNoRujukan("Y");//todo: differ from hakmilikPihak and mohonPihak
            pp.setNama(mp.getNama());

            if (mp.getPermohonan() != null) {
                pp.setPermohonan(mp.getPermohonan());
            }

            senaraiPihakBerkumpulanV2.add(pp);
        }

        //to check tukar nama
        StringBuilder sb
                = new StringBuilder("SELECT mkk FROM etanah.model.PermohonanPihakKemaskini mkk,")
                .append("etanah.model.Permohonan m,")
                .append("etanah.model.Pemohon p ")
                .append("WHERE mkk.permohonan.idPermohonan = m.idPermohonan ")
                .append("AND p.permohonan.idPermohonan = m.idPermohonan ")
                .append("AND mkk.pemohon.idPemohon = p.idPemohon ")
                .append("AND p.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND m.idKumpulan = :idKump ")
                .append("AND p.permohonan.idPermohonan not in (:idPermohonan) ")
                .append("AND m.kumpulanNo <= :kumpNo ")
                .append("AND p.pihak.idPihak = :idPihak ")
                .append("AND mkk.namaMedan = :namaMedan ")
                .append("ORDER BY m.kumpulanNo DESC");

        for (PermohonanPihak p : senaraiPihakBerkumpulanV2) {
            query = sessionProvider.get().createQuery(sb.toString())
                    .setString("idKump", idKumpulan)
                    .setParameter("idHakmilik", idHakmilik)
                    .setParameter("idPermohonan", idPermohonan)
                    .setParameter("kumpNo", kumpNo)
                    .setParameter("idPihak", p.getPihak().getIdPihak())
                    .setParameter("namaMedan", "nama");

            if (!query.list().isEmpty()) {
                PermohonanPihakKemaskini kk = (PermohonanPihakKemaskini) query.list().get(0);
                p.setNama(kk.getNilaiBaru());
            }

            query = sessionProvider.get().createQuery(sb.toString())
                    .setString("idKump", idKumpulan)
                    .setParameter("idHakmilik", idHakmilik)
                    .setParameter("idPermohonan", idPermohonan)
                    .setParameter("kumpNo", kumpNo)
                    .setParameter("idPihak", p.getPihak().getIdPihak())
                    .setParameter("namaMedan", "nokp");

            if (!query.list().isEmpty()) {
                PermohonanPihakKemaskini kk = (PermohonanPihakKemaskini) query.list().get(0);
                p.setNoPengenalan(kk.getNilaiBaru());
            }
        }

        for (HakmilikPihakBerkepentingan hpb : senaraiPihak) {
            PermohonanPihak pp = new PermohonanPihak();
            pp.setPihak(hpb.getPihak());
            pp.setSyerPembilang(hpb.getSyerPembilang() == null ? 1 : hpb.getSyerPembilang());
            pp.setSyerPenyebut(hpb.getSyerPenyebut() == null ? 1 : hpb.getSyerPenyebut());
            pp.setHakmilik(hpb.getHakmilik());
            pp.setJenis(hpb.getJenis());
            pp.setNoRujukan("Y");//todo: differ from hakmilikPihak and mohonPihak
            pp.setDalamanNilai2(String.valueOf(hpb.getIdHakmilikPihakBerkepentingan())); //temp : store id_hp

            if (hpb.getPerserahan() != null && StringUtils.isNotBlank(hpb.getPerserahan().getIdPerserahan())) {
                Permohonan p = permohonanDAO.findById(hpb.getPerserahan().getIdPerserahan());
                if (p != null) {
                    pp.setPermohonan(p);
                }
            }

            query = sessionProvider.get().createQuery(sb.toString())
                    .setString("idKump", idKumpulan)
                    .setParameter("idHakmilik", idHakmilik)
                    .setParameter("idPermohonan", idPermohonan)
                    .setParameter("kumpNo", kumpNo)
                    .setParameter("idPihak", hpb.getPihak().getIdPihak())
                    .setParameter("namaMedan", "nama");

            if (!query.list().isEmpty()) {
                PermohonanPihakKemaskini kk = (PermohonanPihakKemaskini) query.list().get(0);
                pp.setNama(kk.getNilaiBaru());
            } else {
                pp.setNama(StringUtils.isNotBlank(hpb.getNama()) ? hpb.getNama() : hpb.getPihak().getNama());
            }

            query = sessionProvider.get().createQuery(sb.toString())
                    .setString("idKump", idKumpulan)
                    .setParameter("idHakmilik", idHakmilik)
                    .setParameter("idPermohonan", idPermohonan)
                    .setParameter("kumpNo", kumpNo)
                    .setParameter("idPihak", hpb.getPihak().getIdPihak())
                    .setParameter("namaMedan", "nokp");

            if (!query.list().isEmpty()) {
                PermohonanPihakKemaskini kk = (PermohonanPihakKemaskini) query.list().get(0);
                pp.setNoPengenalan(kk.getNilaiBaru());
            } else {
                pp.setNoPengenalan(StringUtils.isNotBlank(hpb.getNoPengenalan()) ? hpb.getNoPengenalan() : hpb.getPihak().getNoPengenalan());
            }

//            pp.setNoPengenalan(hpb.getNoPengenalan());
            pp.setPermohonan(hpb.getPerserahan() != null
                    ? permohonanDAO.findById(hpb.getPerserahan().getIdPerserahan()) : null);
            senaraiPihakBerkumpulanV2.add(pp);
        }
        return senaraiPihakBerkumpulanV2;
    }

    public List<PermohonanPihak> getSenaraiTuanTanahForBerangkai(String idKumpulan, String idPermohonan,
            String idHakmilik, boolean bukanPemilik, boolean semuaPihak, int kumpNo) {
        StringBuilder q = new StringBuilder("Select mp from etanah.model.PermohonanPihak mp, etanah.model.Permohonan m").append(" where mp.permohonan.idPermohonan = m.idPermohonan").append(" and m.idKumpulan = :idKump") //                .append(" and mp.jenis.kod in (:senaraiPihak)")
                .append(" and mp.hakmilik.idHakmilik = :idHakmilik").append(" and mp.permohonan.idPermohonan not in (:idPermohonan)").append(" and m.kumpulanNo <= :kumpNo ").append(" and mp.pihak.idPihak not in").append(" (Select pm.pihak.idPihak from etanah.model.Pemohon pm, etanah.model.Permohonan m").append(" where pm.permohonan.idPermohonan = m.idPermohonan").append(" and pm.permohonan.idPermohonan not in (:idPermohonan)").append(" and m.idKumpulan = :idKump and pm.hakmilik.idHakmilik = :idHakmilik and pm.jenisPemohon in ('X') )");

        if (!semuaPihak) {
            if (bukanPemilik) {
                q.append(" and mp.jenis.kod not in (:senaraiPihak)");
            } else {
                q.append(" and mp.jenis.kod in (:senaraiPihak)");
            }
        }
//        String q = "SELECT mp FROM etanah.model.PermohonanPihak mp, etanah.model.Permohonan m WHERE mp.permohonan.idPermohonan = m.idPermohonan"
//                + " AND m.idKumpulan = :idKump"
//                + " AND mp.jenis.kod in (:senaraiPihak)"
//                + " AND mp.hakmilik.idHakmilik = :idHakmilik"
//                + " AND mp.permohonan.idPermohonan not in (:idPermohonan)"
//                + " AND mp.pihak.idPihak not in (Select pm.pihak.idPihak from etanah.model.Pemohon pm, etanah.model.Permohonan m"
//                + " where pm.permohonan.idPermohonan = m.idPermohonan "
//                + " and pm.permohonan.idPermohonan not in (:idPermohonan) "
//                + " and m.idKumpulan = :idKump and pm.hakmilik.idHakmilik = :idHakmilik and pm.jenisPemohon in ('X') ) ";

        Query query = sessionProvider.get().createQuery(q.toString()).setString("idKump", idKumpulan).setParameter("idHakmilik", idHakmilik).setParameter("idPermohonan", idPermohonan).setParameter("kumpNo", kumpNo);
        if (!semuaPihak) {
            query.setParameterList("senaraiPihak", JENIS_TUAN_TANAH);
        }
        return query.list();
    }

    public List<PermohonanPihak> senaraiPihakBerkepentinganBerangkai(String idKump, String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select mp from etanah.model.PermohonanPihak mp, etanah.model.Permohonan m ");
        sb.append("WHERE mp.permohonan.idPermohonan = m.idPermohonan ");
        sb.append("AND m.idKumpulan = :idKump ");
        sb.append("AND mp.hakmilik.idHakmilik = :idHakmilik ");
        sb.append("AND mp.jenis.kod not in (:senaraiPihak)");
        Query query = sessionProvider.get().createQuery(sb.toString()).setString("idKump", idKump).setParameterList("senaraiPihak", JENIS_TUAN_TANAH).setParameter("idHakmilik", idHakmilik);
        return query.list();
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak(String idKumpulan, Long idPihak, String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select mp from etanah.model.PermohonanPihak mp, etanah.model.Permohonan m ").append("Where mp.permohonan.idPermohonan = m.idPermohonan ").append("And m.idKumpulan = :idKump ").append("And mp.jenis.kod in (:senarai) ").append("And mp.hakmilik.idHakmilik = :idHakmilik ");
        //idPihak yg berlainan , nama sama, no pengenalan sama, kod pengenalan sama
//                .append("And mp.pihak.idPihak = :idPihak");

        Query query = sessionProvider.get().createQuery(sb.toString()).setString("idKump", idKumpulan).setParameterList("senarai", JENIS_TUAN_TANAH).setParameter("idHakmilik", idHakmilik);
//                .setParameter("idPihak", idPihak);
        return query.list();
    }

    public PermohonanPihak findById(String id) {
        return permohonanPihakDAO.findById(Long.parseLong(id));
    }

    @Transactional
    public PermohonanPihak saveOrUpdate(PermohonanPihak p) {
        return permohonanPihakDAO.saveOrUpdate(p);
    }

    public PermohonanPihak saveWithoutConnection(PermohonanPihak pp) {
        return permohonanPihakDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdate(List<PermohonanPihak> pp) {
        for (PermohonanPihak permohonanPihak : pp) {
            permohonanPihakDAO.saveOrUpdate(permohonanPihak);
        }
    }

    @Transactional
    public void delete(PermohonanPihak p) {
        permohonanPihakDAO.delete(p);
    }
    //@Transactional

    public void delete(List<PermohonanPihak> pp) {
        for (PermohonanPihak permohonanPihak : pp) {
            permohonanPihakDAO.delete(permohonanPihak);
        }
    }

    @Transactional
    public void deleteMultiple(List<PermohonanPihak> pp) {
        for (PermohonanPihak permohonanPihak : pp) {
            permohonanPihakDAO.delete(permohonanPihak);
        }
    }

    @Transactional
    public void delete(String[] uids) {
        for (int i = 0; i < uids.length; i++) {
            PermohonanPihak p = permohonanPihakDAO.findById(Long.parseLong(uids[i]));
            if (p == null) {
                continue;
            }

            if (p.getSenaraiHubungan().size() > 0) {
                for (PermohonanPihakHubungan pph : p.getSenaraiHubungan()) {
                    if (pph == null) {
                        continue;
                    }
                    permohonanPihakHubunganDAO.delete(pph);
                }
            }
            permohonanPihakDAO.delete(p);

        }
    }

    @Transactional
    public void deletePemohonAndMohonPihak(String[] uids) {
        for (int i = 0; i < uids.length; i++) {

            PermohonanPihak mohonPihak = permohonanPihakDAO.findById(Long.parseLong(uids[i]));

            if (mohonPihak != null) {

                Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(mohonPihak.getPermohonan(), mohonPihak.getPihak());

                if (pemohon != null) {
                    pemohonDAO.delete(pemohon);
                }

                if (mohonPihak.getSenaraiHubungan().size() > 0) {
                    for (PermohonanPihakHubungan pph : mohonPihak.getSenaraiHubungan()) {
                        if (pph == null) {
                            continue;
                        }
                        permohonanPihakHubunganDAO.delete(pph);
                    }
                }
                permohonanPihakDAO.delete(mohonPihak);
            }
        }
    }

    //added by murali
    public List<PermohonanPihak> getSenaraiPmohonPihakByIdMohonAndIdHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    //Added by Aizuddin
    public List<PermohonanPihak> findPermohonanPihakByIdPermohonan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }
    public List<PermohonanPihak> findPermohonanPihakByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public Long getBilGroupByHakmilik(String idPermohonan) {
        Session session = sessionProvider.get();
        StringBuilder sb = new StringBuilder("Select count(m.hakmilik.idHakmilik) from ")
                .append("etanah.model.PermohonanPihak m Where ")
                .append("m.permohonan.idPermohonan = :idPermohonan ")
                .append("Group by m.hakmilik.idHakmilik");
        Query q = session.createQuery(sb.toString()).setParameter("idPermohonan", idPermohonan);
        return (Long) q.iterate().next();

    }

    @Transactional
    public void calculateJumSyer(String idHakmilik, Long idPihak, String kodJenisPihak,
            PermohonanPihak permohonanPihak, boolean berangkai) {
        Session session = sessionProvider.get();

        Fraction sumAllSamePihak = Fraction.ZERO;

        Permohonan p = permohonanPihak.getPermohonan();

        StringBuilder sb1 = new StringBuilder("SELECT MP FROM etanah.model.PermohonanPihak MP ")
                .append("WHERE MP.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND MP.pihak.idPihak = :idPihak ")
                .append("AND MP.jenis.kod = :kod ");
        if (!berangkai) {
            sb1.append("AND MP.permohonan.idPermohonan = :idPermohonan ");
        }
        if (berangkai) {
            sb1.append("AND MP.permohonan.idKumpulan = :idKump ")
                    .append("AND MP.permohonan.kumpulanNo <= :kumpNo ")
                    .append("AND MP.pihak.idPihak not in (")
                    .append("SELECT P.pihak.idPihak FROM etanah.model.Pemohon P ")
                    .append("WHERE P.jenis.kod = :kod ")
                    .append("AND P.hakmilik.idHakmilik = :idHakmilik ")
                    .append("AND P.permohonan.idKumpulan = :idKump ")
                    .append("AND P.permohonan.kumpulanNo <= :kumpNo )");
        }

        Query q1 = session.createQuery(sb1.toString())
                .setParameter("idHakmilik", idHakmilik)
                .setParameter("idPihak", idPihak)
                .setParameter("kod", kodJenisPihak);
        if (!berangkai) {
            q1.setParameter("idPermohonan", p.getIdPermohonan());
        }
        if (berangkai) {
            q1.setParameter("idKump", p.getIdKumpulan())
                    .setParameter("kumpNo", p.getKumpulanNo());
        }

        if (!q1.list().isEmpty()) {
            List<PermohonanPihak> senaraiPihakBerangkai = q1.list();
            for (PermohonanPihak pp : senaraiPihakBerangkai) {
                if (pp.getSyerPembilang() == 0 && pp.getSyerPenyebut() == 0) {
                    continue;
                }
                sumAllSamePihak = sumAllSamePihak.add(new Fraction(pp.getSyerPembilang(), pp.getSyerPenyebut()));
            }
        }

        StringBuilder sb = new StringBuilder("SELECT HP FROM etanah.model.HakmilikPihakBerkepentingan HP ")
                .append("WHERE HP.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND HP.pihak.idPihak = :idPihak ")
                .append("AND HP.aktif = 'Y' ")
                .append("AND HP.jenis.kod = :kod ");
        if (berangkai) {
            sb.append("AND HP.pihak.idPihak not in (")
                    .append("SELECT P.pihak.idPihak FROM etanah.model.Pemohon P ")
                    .append("WHERE P.jenis.kod = :kod ")
                    .append("AND P.hakmilik.idHakmilik = :idHakmilik ")
                    .append("AND P.permohonan.idKumpulan = :idKump ")
                    .append("AND P.permohonan.kumpulanNo < :kumpNo )");
        }

        Query q = session.createQuery(sb.toString())
                .setParameter("idHakmilik", idHakmilik)
                .setParameter("idPihak", idPihak)
                .setParameter("kod", kodJenisPihak);
        if (berangkai) {
            q.setParameter("idKump", p.getIdKumpulan())
                    .setParameter("kumpNo", p.getKumpulanNo());
        }

        List<HakmilikPihakBerkepentingan> senaraiPihakSama = q.list();

        if (!berangkai) {
            List<Pemohon> senaraiPemohon = p.getSenaraiPemohon();
            for (Pemohon pemohon : senaraiPemohon) {
                if((pemohon.getSyerPembilang() != 0)||(pemohon.getSyerPenyebut() != 0)){
                Fraction syerPemohon = new Fraction(pemohon.getSyerPembilang(), pemohon.getSyerPenyebut());
                for (HakmilikPihakBerkepentingan hp : senaraiPihakSama) {
                    if (pemohon.getPihak().getIdPihak() == hp.getPihak().getIdPihak()
                            && pemohon.getJenis().getKod().equals(hp.getJenis().getKod())
                            && pemohon.getHakmilik().getIdHakmilik().equals(hp.getHakmilik().getIdHakmilik())) {
                        Fraction syerHP = new Fraction(hp.getSyerPembilang(), hp.getSyerPenyebut());
                        if (syerPemohon.compareTo(syerHP) == 0) {
                            senaraiPihakSama.remove(hp);
                        }
                        break;
                    }
                }
                }
            }
        }

        if (!senaraiPihakSama.isEmpty()) {
            for (HakmilikPihakBerkepentingan hp : senaraiPihakSama) {
                if (hp.getSyerPembilang() == 0 && hp.getSyerPenyebut() == 0) {
                    continue;
                }
                sumAllSamePihak = sumAllSamePihak.add(new Fraction(hp.getSyerPembilang(), hp.getSyerPenyebut()));
            }
        }

        if (sumAllSamePihak.compareTo(Fraction.ZERO) >= 1) {
            Fraction abs = sumAllSamePihak.abs();
            permohonanPihak.setJumlahPembilang(abs.getNumerator());
            permohonanPihak.setJumlahPenyebut(abs.getDenominator());
            permohonanPihakDAO.save(permohonanPihak);
        } else {
            permohonanPihak.setJumlahPembilang(permohonanPihak.getSyerPembilang());
            permohonanPihak.setJumlahPenyebut(permohonanPihak.getSyerPenyebut());
            permohonanPihakDAO.save(permohonanPihak);
        }

    }
    
    public List<PermohonanPihak> getSenaraiPmohonPihakPG(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m, etanah.model.HakmilikPermohonan p "
                + "WHERE m.hakmilik.idHakmilik = p.hakmilik.idHakmilik "
                + "and m.jenis.kod = 'PG'"
                + "and m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }
    
    public PermohonanPihak getSenaraiPmohonPihakByIdMhnIdHm(String idMohon, String idhm) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m "
                + "WHERE m.hakmilik.idHakmilik = :id_hm "
                + "and m.jenis.kod = 'PG'"
                + "and m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_hm", idhm);
        q.setParameter("id_mohon", idMohon);
        return (PermohonanPihak) q.uniqueResult();
    }

}
