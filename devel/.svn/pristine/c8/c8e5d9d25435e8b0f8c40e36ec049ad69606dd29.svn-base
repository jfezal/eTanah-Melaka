/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.model.Permohonan;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.Dokumen;
import etanah.model.IntegrasiDokumen;
import etanah.model.FolderDokumen;
import etanah.model.KodDokumen;
import etanah.model.FasaPermohonan;
import etanah.dao.FasaPermohonanDAO;
import etanah.model.Integrasi;
import etanah.model.PermohonanDokumenIringan;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.Transaksi;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KandunganFolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.hibernate.Session;

/**
 *
 * @author nordiyana
 */
public class JupemService {

    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrasipermohonanButirDAO;
    @Inject
    FasaPermohonanDAO fasapermohonanDAO;
    FasaPermohonan fasapermohonan;
    Permohonan permohonan;
    IntegrasiPermohonan integrasiPermohonan;
    IntegrasiPermohonanButir integrasiPermohonanButir;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Permohonan findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();

    }

    public FasaPermohonan findFasaByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (FasaPermohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    @Transactional
    public void simpanInIntegrasiPermohonan(IntegrasiPermohonan integrasiPermohonan) {
        integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);
    }

    @Transactional
    public void simpanIntegrasiPermohonanButir(IntegrasiPermohonanButir integrasipermohonanButir) {
        integrasipermohonanButirDAO.saveOrUpdate(integrasipermohonanButir);
    }

    public IntegrasiPermohonan findByIdInteg(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.IntegrasiPermohonan p where p.permohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (IntegrasiPermohonan) q.uniqueResult();
    }

//     public List<IntegrasiPermohonanButir> getIntegMohonButirListByidInteg(long idInteg) {
//        String query = "SELECT b FROM etanah.model.IntegrasiPermohonanButir b WHERE b.IntegrasiPermohonan.idInteg = :idInteg";
//        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idInteg", idInteg);
//        return  q.list();
//    }
    @Transactional
    public void simpanInteg(IntegrasiPermohonan integrasiPermohonan) {
        integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);
    }

    public List<IntegrasiPermohonanButir> findByidInteg(Long idInteg) {
        String query = "SELECT b FROM etanah.model.IntegrasiPermohonanButir b where b.IntegrasiPermohonan.idInteg = :idInteg";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idInteg", idInteg);
        return q.list();
    }
//    public List<Dokumen> findByIdFolder(FolderDokumen fd,Dokumen d,IntegrasiDokumen integdok,KodDokumen f)

    public List<Dokumen> findDokumenList(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select d from IntegrasiDokumen k,"
                + "Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = k.kodDokumen.kod "
                + "and d.kodDokumen.kod = f.kod "
                + "and k.diperluOleh in ('GIS','JUPEM') "
                + "and a.idPermohonan = :idPermohonan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> findDokumenListByKodurusan(String idPermohonan, String kodUrusan) {
        Session s = sessionProvider.get();
        String query = "select d from IntegrasiDokumen k,"
                + "Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = k.kodDokumen.kod "
                + "and d.kodDokumen.kod = f.kod "
                + "and k.diperluOleh in ('GIS','JUPEM') "
                + "and a.idPermohonan = :idPermohonan "
                + "and k.kodUrusan.kod = :kodUrusan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<Dokumen> findDokumenlist(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        String query = "select d from IntegrasiDokumen k,"
                + "Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = k.kodDokumen.kod "
                + "and d.kodDokumen.kod = f.kod "
                + "and k.diperluOleh in ('GIS','JUPEM') "
                + "and k.dokHantar = 'Y' "
                + "and a.idPermohonan = :idPermohonan "
                + "and k.idAliran = :idAliran";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return q.list();
    }

    public List<Dokumen> findDokumenlist(String idPermohonan, String idAliran, String kodUrusan) {
        Session s = sessionProvider.get();
        String query = "select d from IntegrasiDokumen k,"
                + "Permohonan a,"
                + "FolderDokumen b,"
                + "Dokumen d,"
                + "KandunganFolder c,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = c.folder.folderId "
                + "and c.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = k.kodDokumen.kod "
                + "and d.kodDokumen.kod = f.kod "
                + "and k.diperluOleh in ('GIS','JUPEM') "
                + "and k.dokHantar = 'Y' "
                + "and a.idPermohonan = :idPermohonan"
                + " and k.idAliran = :idAliran"
                + " and k.kodUrusan.kod = :kodUrusan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public void copyDirectory(File srcPath, File dstPath) throws IOException {

        if (srcPath.isDirectory()) {
            if (!dstPath.exists()) {
                dstPath.mkdir();
            }

            String files[] = srcPath.list();
            for (int i = 0; i < files.length; i++) {
                copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i]));
            }

        } else {
            if (!srcPath.exists()) {
                System.out.println("File or directory does not exist.");

            } else {
                InputStream in = new FileInputStream(srcPath);
                OutputStream out = new FileOutputStream(dstPath);
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
        System.out.println("file copied.");
    }

    public Integrasi getInteg(String kod, String idAliranHantar) {
        String query = "SELECT b FROM etanah.model.Integrasi b where b.kodUrusan.kod = :kod and b.idAliranHantar = :idAliranHantar";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("idAliranHantar", idAliranHantar);
        return (Integrasi) q.uniqueResult();

    }

    public IntegrasiPermohonan getByidMohonNaliran(String idPermohonan, String idAliran) {
        String query = "SELECT b FROM etanah.model.IntegrasiPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.idAliran = :idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (IntegrasiPermohonan) q.uniqueResult();
    }

    public List<IntegrasiDokumen> findIntegrasiDokumenList(String kodUrusan, String idAliran) {
        String query = "SELECT b FROM etanah.model.IntegrasiDokumen b where b.kodUrusan = :kodUrusan AND idAliran= :idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        q.setString("idAliran", idAliran);
        return q.list();
    }

    public List<Dokumen> findDocByMohonIringan(Permohonan permohonan) {
        String query = "select d from Dokumen d, Permohonan p, PermohonanDokumenIringan b, KandunganFolder kf where b.idPermohonan.idPermohonan = p.idPermohonan and b.kodDokumen.kod = d.kodDokumen.kod and kf.dokumen.idDokumen = d.idDokumen and kf.folder.folderId = p.folderDokumen.folderId and p.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", permohonan.getIdPermohonan());
        return q.list();
    }
//select a.id_mohon,e.kod_urusan,e.id_aliran,e.diperlu_oleh,d.id_dokumen,d.kod_dokumen,f.nama as nama_dokumen,d.format,'/nfs_app/dms/' as dok_path,d.nama_fizikal
//from   kicker.mohon a, kicker.folder b, kicker.folder_dok c, kicker.dokumen d ,kicker.integrasi_dokumen e,kicker.kod_dokumen f
//where  a.id_folder = b.id_folder
//and    b.id_folder = c.id_folder
//and    c.id_dokumen = d.id_dokumen
//and    d.kod_dokumen = e.kod_dokumen
//and    d.kod_dokumen = f.kod
//and    e.diperlu_oleh in ('GIS','JUPEM');
//
}
