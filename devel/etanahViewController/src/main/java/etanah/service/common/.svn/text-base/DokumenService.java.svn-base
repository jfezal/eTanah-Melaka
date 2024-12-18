package etanah.service.common;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.SejarahDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.SejarahDokumen;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class DokumenService {

    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    SejarahDokumenDAO sejarahDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(DokumenService.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    @Transactional
    public Dokumen saveOrUpdate(Dokumen d) {
        return dokumenDAO.saveOrUpdate(d);
    }

    @Transactional
    public void saveOrUpdate(List<Dokumen> list) {
        for (Dokumen d : list) {
            dokumenDAO.saveOrUpdate(d);
        }
    }

    @Transactional
    public void saveOrUpdateSejarahDokumen(List<SejarahDokumen> list) {
        for (SejarahDokumen d : list) {
            sejarahDokumenDAO.saveOrUpdate(d);
        }
    }

    @Transactional
    public void save(Dokumen dok) {
        dokumenDAO.save(dok);
    }

    @Transactional
    public void saveKandunganWithDokumen(KandunganFolder kf) {
//        d = dokumenDAO.saveOrUpdate(d);
//        kf.setDokumen(d);
        kandunganFolderDAO.saveOrUpdate(kf);
    }

    @Transactional
    public void deleteKandunganWithDokumen(Dokumen d, FolderDokumen fd) {
        KandunganFolder kf = kandunganFolderService.findByDokumen(d, fd);
        if (kf != null) {
            dokumenDAO.delete(d);
            kandunganFolderDAO.delete(kf);
        }
    }

    @Transactional
    public void deleteDokumenFolderDok(String idDokumen, String idFolderDok) {
        if (StringUtils.isNotBlank(idDokumen)) {
            Dokumen d = dokumenDAO.findById(Long.valueOf(idDokumen));
            if (d != null) {
                dokumenDAO.delete(d);
            }
        }
        if (StringUtils.isNotBlank(idFolderDok)) {
            KandunganFolder kf = kandunganFolderDAO.findById(Long.valueOf(idFolderDok));
            if (kf != null) {
                kandunganFolderDAO.delete(kf);
            }
        }
    }

    public boolean findDokumen(FolderDokumen fd,
            List<String> senaraiKodDokumen, String id) {
        Session s = sessionProvider.get();

        Query q = s.createQuery(
                "Select d from etanah.model.Dokumen d , etanah.model.KandunganFolder kf "
                + "WHERE d.idDokumen = kf.dokumen.idDokumen "
                + "AND kf.folder.folderId = :idFolder "
                + //                "AND d.idDokumen = :idDokumen " +
                "AND d.kodDokumen.kod in (:senaraiKod) "
                + "AND d.dalamanNilai1 LIKE :nilaiDalaman");
//        q.setLong ("idDokumen", dokumen.getIdDokumen());
        q.setParameter("idFolder", fd.getFolderId());
        q.setParameterList("senaraiKod", senaraiKodDokumen);
        q.setString("nilaiDalaman", "%" + id + "%");

        if (q.list().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Dokumen findByIDKodDokumen(Long idDokumen, KodDokumen kd, String id) {
//        if(isDebug){
//            LOGGER.debug("idDokumen =" + idDokumen);
//            LOGGER.debug("kodDokumen =" + kd.getKod());
//            LOGGER.debug("id = " + id);
//        }
        Session s = sessionProvider.get();
//        Query q = s.createQuery(
//                "select d from etanah.model.Dokumen d where d.idDokumen = :idDokumen and d.kodDokumen.kod = :kodDokumen and d.dalamanNilai1 = :id");

        Query q = s.createQuery(
                "select d from etanah.model.Dokumen d where d.idDokumen = :idDokumen and d.kodDokumen.kod = :kodDokumen");
        q.setLong("idDokumen", idDokumen);
//        q.setString("id", id);        
        q.setParameter("kodDokumen", kd.getKod());

        Dokumen d = (Dokumen) q.uniqueResult();
//        if(isDebug) LOGGER.debug("dokumen is null = " + (d == null ? "true" : "false"));
        if (d == null) {
            return null;
        }
        //for urusan berangkai.
        if (StringUtils.isNotBlank(d.getDalamanNilai1())) {
            String[] ids = id.split(",");
            if (ids.length > 0) {
                if (d.getDalamanNilai1().equals(id)) {
                    return d;
                }
            } else {
                String[] tmp = d.getDalamanNilai1().split(",");
                for (String str : tmp) {
                    if (str.equals(id)) {
                        return d;
                    }
                }
            }
        }
        return null;
    }

    public KandunganFolder findCatatan(long dokumenId, long folderId) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select d from etanah.model.KandunganFolder d where d.dokumen.idDokumen = :idDokumen and d.folder.folderId = :idFolder");
        q.setLong("idDokumen", dokumenId);
        q.setLong("idFolder", folderId);
        return (KandunganFolder) q.uniqueResult();
    }

    public Dokumen findById(Long id) {
        return dokumenDAO.findById(id);
    }

    public KodDokumen findByKodDokumen(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select d from etanah.model.KodDokumen d where d.kod = :kod");
        q.setParameter("kod", kod);
        KodDokumen d = (KodDokumen) q.uniqueResult();
        if (d != null) {
            return d;
        }

        return null;
    }

    public int getDokumenNoVersi(Long idDokumen, KodDokumen kd, String id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select d.noVersi from etanah.model.Dokumen d where d.idDokumen = :idDokumen and d.kodDokumen.kod = :kodDokumen and d.dalamanNilai1 = :id");
        q.setLong("idDokumen", idDokumen);
        q.setString("id", id);
        q.setParameter("kodDokumen", kd.getKod());

        return (Integer) q.iterate().next();
    }

    @Transactional
    public void deleteDok(String[] ids) {
        for (String id : ids) {
            KandunganFolder kf = kandunganFolderDAO.findById(Long.getLong(id));
            if (kf != null) {
                kandunganFolderDAO.delete(kf);
            }
        }
    }

    public FolderDokumen getFolderByIdDokumen(Long idDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "Select kf from etanah.model.KandunganFolder kf where kf.dokumen.idDokumen = :idDokumen");
        q.setLong("idDokumen", idDokumen);

        KandunganFolder kf = (KandunganFolder) q.uniqueResult();

        if (kf != null) {
            return kf.getFolder();
        }

        return null;
    }

    public List<KandunganFolder> findListKandunganFolderByIdCarian(String idCarian) {
        String strQuery = "Select kf from etanah.model.KandunganFolder kf, etanah.model.PermohonanCarian pc"
                + " where pc.idCarian = :idCarian and pc.folderDokumen.folderId = kf.folder.folderId";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idCarian", idCarian);
        return q.list();
    }

    @Transactional
    public void delete(Dokumen d) {
        dokumenDAO.delete(d);
    }

    public List<Dokumen> getDokumenByIdPenguna(String userName, Date tarikhMasuk) {
        String strQuery = "Select d from etanah.model.Dokumen d where d.infoAudit.dimasukOleh.nama = :userName ";

        strQuery += "and trim(d.infoAudit.tarikhMasuk) =:tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setDate("tarikhMasuk", tarikhMasuk);
        q.setString("userName", userName);
        return q.list();
    }

    public Dokumen findDok(String idPermohonan, String kodDokumen) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Dokumen) q.uniqueResult();

    }

    public Dokumen findDokumenByIdMohon(String idPermohonan, String kodDokumen) {
        String query = " select d from Dokumen d where d.permohonan.idPermohonan = :idPermohonan and d.kodDokumen.kod = :kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Dokumen) q.uniqueResult();

    }

    public Dokumen findDokumenRENCbyDnilai1(String idPermohonan) {
        String query = " select d from Dokumen d "
                + "where d.dalamanNilai1 = :idPermohonan and d.kodDokumen.kod in ('RENC')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Dokumen) q.uniqueResult();
    }

    // add by azri 12/7/2013: used in 'UtilitiBetulHakmilik'
    public List<Dokumen> findDokList(String idPermohonan, String kodDokumen) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    } // add by azri 12/7/2013:END

    public List<Dokumen> findDokListByNoRuj(String idPermohonan, String noRujukan) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.noRujukan = :noRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noRujukan", noRujukan);
        return q.list();
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public SejarahDokumenDAO getSejarahDokumenDAO() {
        return sejarahDokumenDAO;
    }

    public void setSejarahDokumenDAO(SejarahDokumenDAO sejarahDokumenDAO) {
        this.sejarahDokumenDAO = sejarahDokumenDAO;
    }

    public KandunganFolderDAO getKandunganFolderDAO() {
        return kandunganFolderDAO;
    }

    public void setKandunganFolderDAO(KandunganFolderDAO kandunganFolderDAO) {
        this.kandunganFolderDAO = kandunganFolderDAO;
    }

    public KandunganFolderService getKandunganFolderService() {
        return kandunganFolderService;
    }

    public void setKandunganFolderService(KandunganFolderService kandunganFolderService) {
        this.kandunganFolderService = kandunganFolderService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        DokumenService.isDebug = isDebug;
    }
}
