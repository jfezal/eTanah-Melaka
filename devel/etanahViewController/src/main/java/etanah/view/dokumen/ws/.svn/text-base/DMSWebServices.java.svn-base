/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.dokumen.ws;

import com.Ostermiller.util.Base64;
import com.google.inject.Injector;
import etanah.Configuration;
import etanah.dao.AgensiKutipanDokumenDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.JteknikalDokumenTerimaDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.AgensiKutipanDokumen;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.JteknikalDokumenTerima;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.UlasanJabatanTeknikal;
import etanah.report.ReportUtil;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class DMSWebServices implements IDMSServices {

    private static Injector injector = etanahContextListener.getInjector();
    KodDokumenDAO kodDokumenDAO = injector.getInstance(KodDokumenDAO.class);
    KodKlasifikasiDAO kodKlasifikasiDAO = injector.getInstance(KodKlasifikasiDAO.class);
    DokumenDAO dokumenDAO = injector.getInstance(DokumenDAO.class);
    PenggunaDAO pgunaDAO = injector.getInstance(PenggunaDAO.class);
    UlasanJabatanTeknikalDAO jTeknikalDAO = injector.getInstance(UlasanJabatanTeknikalDAO.class);
    JteknikalDokumenTerimaDAO jteknikalDokumenTerimaDAO = injector.getInstance(JteknikalDokumenTerimaDAO.class);
    KandunganFolderDAO kandunganFolderDAO = injector.getInstance(KandunganFolderDAO.class);
    AgensiKutipanDokumenDAO agensiKutipanDokumenDAO = injector.getInstance(AgensiKutipanDokumenDAO.class);
    Configuration conf = injector.getInstance(Configuration.class);
    JteknikalDokumenTerima dokTerima;
    UlasanJabatanTeknikal jTekUlas;
    ReportUtil report = injector.getInstance(ReportUtil.class);

    @Override
    public void uploadDokumenDMS(String idUrus, String convertBacktoNormalString, String kodDokumen, String fileName, String contentType, String catatan) {


        try {
            JteknikalDokumenTerima dokJTEK = new JteknikalDokumenTerima();
            jTekUlas = jTeknikalDAO.findById(Long.valueOf(idUrus));
            Dokumen doc = new Dokumen();
            KodDokumen kd = new KodDokumen();
            KodKlasifikasi kodk = new KodKlasifikasi();
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            kd = kodDokumenDAO.findById(kodDokumen);
            Pengguna pguna = pgunaDAO.findById("portal");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            doc.setTajuk(kd.getNama() + "-" + jTekUlas.getKodAgensi().getNama());
            doc.setInfoAudit(ia);
            doc.setKodDokumen(kd);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc = dokumenDAO.saveOrUpdate(doc);


            DMSUtil dmsUtil = new DMSUtil();
            byte[] b = Base64.decodeToBytes(convertBacktoNormalString);
            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(jTekUlas.getPermohonan()));
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            fileUtil.saveFile(fileName, in);
            String fizikalPath = dmsUtil.getFizikalPath(jTekUlas.getPermohonan()) + File.separator + fileName;
            //FileOutputStream fout = new FileOutputStream(fizikalPath);
//		Base64.decodeToStream(convertBacktoNormalString, fout);
//            jTekUlas.getPermohonan().getFolderDokumen().get
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(jTekUlas.getPermohonan().getFolderDokumen());
            kf.setInfoAudit(ia);
            updateKandunganFolder(kf);
            updatePathDokumen(fizikalPath, doc, contentType);
            dokJTEK.setDokumen(doc);
            dokJTEK.setNamaFizikal(fileName);
            dokJTEK.setUlasanJabatanTeknikal(jTekUlas);
            dokJTEK.setInfoAudit(ia);
            dokJTEK.setCawangan(jTekUlas.getPermohonan().getCawangan());
            saveJtekDOK(dokJTEK);

        } catch (Exception ex) {
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
        if (f.exists()) {
        } else {
             String docPath1 = conf.getProperty("kutipan.inbound.path");
            f = new File(docPath1 + (docPath1.endsWith(File.separator) ? "" : File.separator) + fn);
        }
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

    private void saveJtekDOK(JteknikalDokumenTerima dokJTEK) {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        dokJTEK = jteknikalDokumenTerimaDAO.saveOrUpdate(dokJTEK);
        if (dokJTEK != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    private void deleteJTEKDOK(long id) {
        JteknikalDokumenTerima dokT = jteknikalDokumenTerimaDAO.findById(id);
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        if (dokT != null) {
            jteknikalDokumenTerimaDAO.delete(dokT);
        }
        KandunganFolder kf = new KandunganFolder();
        kf = getKandunganFolder(dokT.getUlasanJabatanTeknikal().getPermohonan().getFolderDokumen().getFolderId(), dokT.getDokumen().getIdDokumen());
        if (kf != null) {
            kandunganFolderDAO.delete(kf);
        }
        tx.commit();
    }
    
     private void deleteAgensiDOK(long id) {
        AgensiKutipanDokumen dokT = agensiKutipanDokumenDAO.findById(id);
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        if (dokT != null) {
            agensiKutipanDokumenDAO.delete(dokT);
        }
       
        tx.commit();
    }

    @Override
    public void deleteDokHantar(long id) {
        deleteJTEKDOK(id);
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

    @Override
    public void uploadKutipanAgensi(String s, String fileName, String contentType) {
        byte[] b = Base64.decodeToBytes(s);
        FileUtil fileUtil = new FileUtil(conf.getKutipanAgensiPath());
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        try {
            fileUtil.saveFile(fileName, in);
        } catch (Exception ex) {
            Logger.getLogger(DMSWebServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDokKutipan(long id) {
       deleteAgensiDOK(id);
    }

    @Override
    public byte[] getReportbyReportNameAndParam(String reportname, String param) {
//        reportname="ACQDocB_MLK.rdf";
        //param = "&p_id_mohon=0401ACQ2014000161";
        param = "&"+param;
     return   report.getReports(reportname, param);
    }
}
