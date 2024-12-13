/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.EnkuiriPenguatkuasaanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.FolderDokumen;
import etanah.model.KodDokumen;
import etanah.service.common.EnforcementService;
import etanah.view.penguatkuasaan.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.report.ReportUtil;
import java.io.File;
import etanah.service.SemakDokumenService;
import etanah.dao.KodKlasifikasiDAO;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.model.KandunganFolder;
import etanah.model.KodKlasifikasi;

/**
 *
 * @author nurshahida.radzi
 */
public class SediaBorangValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    EnkuiriPenguatkuasaanDAO enkuiriPenguatkuasaanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private static final Logger LOG = Logger.getLogger(SediaBorangValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private FasaPermohonan fasaPermohonan;
    private static Pengguna pengguna;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        pengguna = context.getPengguna();
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            String[] tnameFasa = {"permohonan", "idAliran"};
            Object[] modelFasa = {permohonan, "Sedia_borang"};

            List<FasaPermohonan> listFasa = fasaPermohonanDAO.findByEqualCriterias(tnameFasa, modelFasa, null);
            if (listFasa.size() != 0) {
                fasaPermohonan = listFasa.get(0);
                if (fasaPermohonan.getKeputusan().getKod().equals("BH")) {
                    if (permohonan.getKodUrusan().getKod().matches("351")) {
                        kd = kodDokumenDAO.findById("23A");
                        reportName = "ENF_Borang_23A.rdf";
                    } else {
                        kd = kodDokumenDAO.findById("23B");
                        reportName = "ENF_Borang_23B.rdf";
                    }
                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                    path = String.valueOf(permohonan.getFolderDokumen().getFolderId()) + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                }
           }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        String[] tnameFasa = {"permohonan", "idAliran"};
        Object[] modelFasa = {permohonan, "Sedia_borang"};

        List<FasaPermohonan> listFasa = fasaPermohonanDAO.findByEqualCriterias(tnameFasa, modelFasa, null);
        if (listFasa.size() != 0) {
            fasaPermohonan = listFasa.get(0);

            if (fasaPermohonan.getKeputusan().getKod().equals("BH")) {
                Pengguna peng = (Pengguna) context.getPengguna();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                String[] name = {"idHakmilik"};
                Object[] value = {idHakmilik};
                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                KodUrusan kod = kodUrusanDAO.findById("TBTW");
                LOG.info("KODURUSAN: " + kod.getKod());
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
            }
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
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

