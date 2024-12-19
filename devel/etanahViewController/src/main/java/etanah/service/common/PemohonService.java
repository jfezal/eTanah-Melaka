package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class PemohonService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    private static final Logger LOG = Logger.getLogger(PemohonService.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    public List<Pemohon> findPemohonByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
     public List<Pemohon> findPemohonByIdPermohonanPemaju(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pemohonJenis = 'Pemaju'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> findPemohonByIdPermohonanAndHakmilik(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pemohon> findPemohonByIdPermohonanAndHakmilikBaru(String idPermohonanLama, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Pemohon m WHERE "
                + "m.idPermohonanLama = :idPermohonanLama "
                + "AND m.hakmilik.idHakmilik = :idHakmilik "
                + "and m.kodStatus ='T'");
        q.setParameter("idPermohonanLama", idPermohonanLama);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pemohon> findPemohonByHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pemohon> findPemohonHbgnByIdPemohon(String idPermohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohon", idPermohon);
        return q.list();
    }

    public PemohonHubungan findHbgnByIdPemohon(String idPemohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PemohonHubungan p where p.pemohon.idPemohon = :idPemohon");
        q.setString("idPemohon", idPemohon);
        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (PemohonHubungan) q.list().get(0);
        }
        return (PemohonHubungan) q.uniqueResult();
    }

    public List<Pemohon> senaraiPemohonByIdPermohonanIdHakmilik(String idPermohonan, String idHakmilik) {
        Query query = sessionProvider.get().createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik").setParameter("idPermohonan", idPermohonan).setParameter("idHakmilik", idHakmilik);
        return query.list();
    }

    public List<Pemohon> senaraiPemohonByIdPermohonan(String idPermohonan) {
        Query query = sessionProvider.get().createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan")
                .setParameter("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<Pemohon> senaraiPemohonByIdPihakIdHakmilik(Long idPihak, String idHakmilik) {
        Query query = sessionProvider.get().createQuery("Select p from etanah.model.Pemohon p where p.pihak.idPihak = :idPihak and p.hakmilik.idHakmilik = :idHakmilik").setParameter("idPihak", idPihak).setParameter("idHakmilik", idHakmilik);
        return query.list();
    }

    public List<Pemohon> senaraiPemohonByIdPihakIdHakmilikIdMohon(Long idPihak, String idHakmilik, String idMohon) {
        Query query = sessionProvider.get().createQuery("Select p from etanah.model.Pemohon p where p.pihak.idPihak = :idPihak and p.hakmilik.idHakmilik = :idHakmilik and p.permohonan.idPermohonan = :idMohon").setParameter("idPihak", idPihak).setParameter("idHakmilik", idHakmilik).setParameter("idMohon", idMohon);
        return query.list();
    }

    public Pemohon findPemohonByPermohonanPihak(Permohonan p, Pihak ph) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idPihak");
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setLong("idPihak", ph.getIdPihak());

        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }
    
    public Pemohon findPemohonByPermohonanPihakHakmilik(Permohonan p, Pihak ph, Hakmilik hm) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idPihak and p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setLong("idPihak", ph.getIdPihak());
        q.setParameter("idHakmilik", hm.getIdHakmilik());
        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonByPermohonanPihak(Permohonan p, Pihak ph, Hakmilik hm, String kod) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idPihak and p.hakmilik.idHakmilik = :idHakmilik");
        if (StringUtils.isNotBlank(kod)) {
            sb.append(" and p.jenis.kod = :kod");
        }

        Session s = sessionProvider.get();
        Query q = s.createQuery(sb.toString());
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setLong("idPihak", ph.getIdPihak());
        q.setParameter("idHakmilik", hm.getIdHakmilik());
        if (StringUtils.isNotBlank(kod)) {
            q.setParameter("kod", kod);
        }

        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonByidmohonAndIdHakmilik(Permohonan p, Hakmilik hm) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik");
        Session s = sessionProvider.get();
        Query q = s.createQuery(sb.toString());
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setParameter("idHakmilik", hm.getIdHakmilik());
        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findByIdPemohon(Long idPemohon) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pemohon p where p.idPemohon = :idPemohon");
        Session s = sessionProvider.get();
        Query q = s.createQuery(sb.toString());
        q.setParameter("idPemohon", idPemohon);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonByPermohonanPihak(Permohonan p, Pihak ph, Hakmilik hm, String kod, String idHp) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idPihak and p.hakmilik.idHakmilik = :idHakmilik");
        if (StringUtils.isNotBlank(kod)) {
            sb.append(" and p.jenis.kod = :kod");
        }
        if (StringUtils.isNotBlank(idHp)) {
            sb.append(" and p.hakmilikPihak.idHakmilikPihakBerkepentingan = :id");
        }

        Session s = sessionProvider.get();
        Query q = s.createQuery(sb.toString());
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setLong("idPihak", ph.getIdPihak());
        q.setParameter("idHakmilik", hm.getIdHakmilik());
        if (StringUtils.isNotBlank(kod)) {
            q.setParameter("kod", kod);
        }
        if (StringUtils.isNotBlank(idHp)) {
            q.setParameter("id", Long.parseLong(idHp));
        }

        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }

    public List<Pemohon> findPemohonByIdPihak(Pihak ph) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.pihak.idPihak = :idPihak");
        q.setLong("idPihak", ph.getIdPihak());
        return q.list();
    }

    public List<Pemohon> findPemohonByIdPihakAndJabatan(Pihak ph, String jabatan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.pihak.idPihak = :idPihak AND p.permohonan.kodUrusan.jabatan.kod = :jabatan");
        q.setLong("idPihak", ph.getIdPihak());
        q.setParameter("jabatan", jabatan);
        return q.list();
    }

    public void saveWithoutConnection(Pemohon p) {
        pemohonDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdate(Pemohon p) {
        pemohonDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdateMultiple(List<Pemohon> senaraiPemohon) {
        LOG.debug("start saving...");
        for (Pemohon p : senaraiPemohon) {
            pemohonDAO.saveOrUpdate(p);
        }
        LOG.debug("finish saving...");
    }

    @Transactional
    public void simpanPihakPemohon(Pihak pihak, Pemohon pemohon) {
        pihakDAO.saveOrUpdate(pihak);
        pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void delete(Pemohon p) {
        pemohonDAO.delete(p);
    }

    @Transactional
    public void delete(List<Pemohon> senaraiPemohon) {
        for (Pemohon p : senaraiPemohon) {
            pemohonDAO.delete(p);
        }
    }

    @Transactional
    public void deleteSelectedPemohon(String[] uid) {

        for (String str : uid) {
            Pemohon pemohon = pemohonDAO.findById(Long.valueOf(str));
            if (pemohon == null) {
                continue;
            }
            pemohonDAO.delete(pemohon);
        }
    }

    @Transactional
    public void saveOrUpdateHbgn(PemohonHubungan pemohonHubungan) {
        pemohonHubunganDAO.saveOrUpdate(pemohonHubungan);

    }

    public Pemohon findById(String idPemohon) {
        return pemohonDAO.findById(Long.valueOf(idPemohon));
    }

    @Transactional
    public void deleteWaris(PermohonanPihak p) {
        permohonanPihakDAO.delete(p);
    }

    public Pemohon findPemohonByIdPermohonanAndJenisPemohon(String idPermohonan, String pw) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan "
                + "and p.kodJnsPemohon.kod =:pw");
        Session s = sessionProvider.get();
        Query q = s.createQuery(sb.toString());
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("pw", pw);

        return (Pemohon) q.uniqueResult();
    }
}
