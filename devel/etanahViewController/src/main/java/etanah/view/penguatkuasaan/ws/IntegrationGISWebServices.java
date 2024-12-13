/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.ws;

import etanah.view.dokumen.ws.*;
import com.Ostermiller.util.Base64;
import com.google.inject.Injector;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class IntegrationGISWebServices implements IntegrationGISServices {

    private static Injector injector = etanahContextListener.getInjector();
    KodDokumenDAO kodDokumenDAO = injector.getInstance(KodDokumenDAO.class);
    PermohonanDAO permohonanDAO = injector.getInstance(PermohonanDAO.class);
    KodKlasifikasiDAO kodKlasifikasiDAO = injector.getInstance(KodKlasifikasiDAO.class);
    DokumenDAO dokumenDAO = injector.getInstance(DokumenDAO.class);
    PenggunaDAO pgunaDAO = injector.getInstance(PenggunaDAO.class);
    KandunganFolderDAO kandunganFolderDAO = injector.getInstance(KandunganFolderDAO.class);
    Configuration conf = injector.getInstance(Configuration.class);
    Permohonan permohonan;
    Pengguna pguna;

    @Override
    public void uploadDokumenDMS(String idPermohonan, String namaFizikal) {

        try {

            if (StringUtils.isNotBlank(idPermohonan)) {
                permohonan = permohonanDAO.findById(idPermohonan);

                List<FasaPermohonan> listPf = new ArrayList<FasaPermohonan>();
                listPf = permohonan.getSenaraiFasa();
                if (!listPf.isEmpty()) {
                    for (FasaPermohonan fp : listPf) {
                        if (fp.getIdAliran().equalsIgnoreCase("g_hantar_pu")) {
                            pguna = pgunaDAO.findById(fp.getIdPengguna());
                        }

                    }
                }

            }

            Dokumen doc = new Dokumen();
            KodDokumen kd = new KodDokumen();
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            kd = kodDokumenDAO.findById("PU");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            doc.setTajuk(kd.getNama());
            doc.setInfoAudit(ia);
            doc.setKodDokumen(kd);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc.setBaru('Y');
            doc = dokumenDAO.saveOrUpdate(doc);


            String fizikalPath = namaFizikal;

            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setInfoAudit(ia);
            updateKandunganFolder(kf);
            updatePathDokumen(fizikalPath, doc, "image/tiff");


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
        }
    }

    @Override
    public DokumenInfo downloadDokumen(long idDokumen) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        DokumenInfo dokInfo = new DokumenInfo();
        List<byte[]> dokb = new ArrayList<byte[]>();
        if (d == null) {
            // return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }
        String docPath = conf.getProperty("document.path");

        String fn = d.getNamaFizikal();
//        if(StringUtils.isNotBlank(afterSign) && afterSign.equals("true")){
//            docPath = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + "sig";
//            fn = d.getNamaFizikal().substring(d.getNamaFizikal().indexOf(File.separator)+1);
//        }
        System.out.println("fn ::" + fn);
        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
        try {
            if (f != null) {
                InputStream inputStream = new FileInputStream(f.getAbsolutePath());
                byte[] byteArray = IOUtils.toByteArray(inputStream);
                dokInfo.setBytes(byteArray);
                dokInfo.setDocType(d.getFormat());
                dokInfo.setFileName(d.getNamaFizikal());

            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return dokInfo;
    }

    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();

        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    private void updateKandunganFolder(KandunganFolder kf) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");

        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();

        kf = kandunganFolderDAO.saveOrUpdate(kf);
        if (kf != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public KandunganFolder getKandunganFolder(long folderId, long idDokumen) {
        String query = "SELECT b FROM etanah.model.KandunganFolder b where b.folder.folderId = :folderId and b.dokumen.idDokumen = :idDokumen";
        //Session session = sessionProvider.get();
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setLong("folderId", folderId);
        q.setLong("idDokumen", idDokumen);
        return (KandunganFolder) q.uniqueResult();
    }
}
