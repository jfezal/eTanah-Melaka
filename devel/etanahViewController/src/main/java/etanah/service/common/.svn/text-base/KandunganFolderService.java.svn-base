package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KandunganFolderDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.PermohonanRujukanLuar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class KandunganFolderService {

    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public KandunganFolder saveOrUpdate(KandunganFolder kf) {
        return kandunganFolderDAO.saveOrUpdate(kf);
    }

    @Transactional
    public void delete(KandunganFolder kf) {
        kandunganFolderDAO.delete(kf);
    }

    @Transactional
    public void save(KandunganFolder kf) {
        kandunganFolderDAO.save(kf);
    }

    public List<KandunganFolder> findByIdFolder(FolderDokumen fd) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select k from etanah.model.KandunganFolder k where k.folder.folderId = :folderId");
        q.setParameter("folderId", fd.getFolderId());
        return q.list();
    }

    public KandunganFolder findByDokumen(Dokumen d, FolderDokumen fd) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select k from etanah.model.KandunganFolder k where k.folder.folderId = :folderId and k.dokumen.idDokumen = :dokumenId");
        q.setParameter("folderId", fd.getFolderId());
        q.setParameter("dokumenId", d.getIdDokumen());
        return (KandunganFolder) q.uniqueResult();
    }

    public FolderDokumen findFolder(Long id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select k from etanah.model.FolderDokumen k where k.folderId = :folderId");
        q.setLong("folderId", id);
        return (FolderDokumen) q.uniqueResult();
    }

    public KandunganFolder findByIdDokumen(Long idDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select k from etanah.model.KandunganFolder k where k.dokumen.idDokumen = :dokumenId");
        q.setParameter("dokumenId", idDokumen);
        return (KandunganFolder) q.uniqueResult();
    }

    public KandunganFolder getDokumenByIdFolderAndCatatan(Long idFolder, String catatan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select k from etanah.model.KandunganFolder k where  k.folder.folderId = :idFolder and k.catatan =:catatan");
        q.setParameter("idFolder", idFolder);
        q.setParameter("catatan", catatan);
        return (KandunganFolder) q.uniqueResult();
    }
}