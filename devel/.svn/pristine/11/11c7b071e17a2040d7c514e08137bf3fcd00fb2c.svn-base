/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
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
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class UlasanMMKValidator implements StageListener {

    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO mohonFasaDAO;
    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    Dokumen dokumen;
    String path;
    Pengguna pengguna;
    KodDokumen kd = null;
    private static final Logger LOG = Logger.getLogger(UlasanMMKValidator.class);
    FolderDokumen fd;



    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan fasaPermohonan = new FasaPermohonan();
        fasaPermohonan = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusanmmk");
        String kodNegeri = conf.getProperty("kodNegeri");
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan() != null) {
                if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusBgnKosRendah_MLK.rdf");
                        t.add("STRBHKosRendah_MLK.rdf");
                        t2.add("SLKR");
                        t2.add("KKR");
                        reportGen(permohonan, t, t2);
                    } else {
                        t.add("STRSLulusBgnKosRendah_NS.rdf");
                        t.add("STRBHKosRendah_NS.rdf");
                        t2.add("SLKR");
                        t2.add("KKR");
                        reportGen(permohonan, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakBgnKosRendah_MLK.rdf");
                        t2.add("STKR");
                        reportGen(permohonan, t, t2);
                    } else {
                        t.add("STRSTolakBgnKosRendah_NS.rdf");
                        t2.add("STKR");
                        reportGen(permohonan, t, t2);
                    }
                }


            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private void reportGen(Permohonan p, List<String> t, List<String> t2) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        for (int i = 0; i < t.size(); i++) {
            String gen = t.get(i);
            String code = t2.get(i);
            kd = kodDokumenDAO.findById(code);
            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen, params, values, dokumenPath + path, this.pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

        }
    }
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(this.pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(this.pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
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
