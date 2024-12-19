/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class ProsesSuratNotis implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesSuratNotis.class);
    
    @Inject
    KodDokumenDAO kodDokumenDAO;
    
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;

    @Inject
    ReportUtil reportUtil;

    @Inject
    DokumenService dokumenService;

    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    @Inject
    SemakDokumenService semakDokumenService;

    @Inject
    KandunganFolderService kandunganFolderService;

    @Inject
    etanah.Configuration conf;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        KodUrusan kuMohon = permohonan.getKodUrusan();
        HakmilikPermohonan hakmilikP = permohonan.getSenaraiHakmilik().get(0);  //assume 1 permohonan = 1 hakmilik
//        Hakmilik hakmilik = hakmilikP.getHakmilik();
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        String path = null;
        String[] params = null;
        String[] values = null;
        String reportName = null;
        KodDokumen kd = new KodDokumen();
        try{
            LOG.info("start");
            if("NT6A".equals(kuMohon.getKod())){ // untuk urusan Notis 6A
                kd = kodDokumenDAO.findById("N6A"); // set kod dokumen for Notis 6A
//                reportName = "hasilNotis6A.rdf"; // old reportname
                reportName = "hasilNotis6A_MLK.rdf"; // old reportname
//                reportName = "HSLPerintahPTD_NS.rdf";
            }else if("NT8A".equals(kuMohon.getKod())){ // utk urusan Notis 8A
                kd = kodDokumenDAO.findById("MN8A"); // set kod dokumen for Memorandum Penyampaian Notis 8A
//                reportName = "hasilNotis8A.rdf"; // old reportname
                reportName = "HSLN8A_02_NS.rdf"; // assume one report per hakmilik
            }
            params = new String[]{"p_id_mohon"};
            values = new String[]{permohonan.getIdPermohonan()};

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            e = saveOrUpdateDokumen(fd, kd, hakmilikP.getHakmilik().getIdHakmilik(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
        }catch (Exception ex){
            LOG.error("Masalah untuk jana dokumen :"+ex);
            ex.printStackTrace(); // for development only
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//        throw new UnsupportedOperationException("Not supported yet.");
        context.addMessage(" - Permohonan telah berjaya dihantar ke peringkat Ketua Pembantu Tadbir untuk semakan notis.");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("doc null");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("doc :"+doc.getIdDokumen());
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setKlasifikasi(kodKlas);
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}
