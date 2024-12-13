package etanah.view.strata;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import java.io.File;

public class ReportGeneratorPBBS {

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
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO mohonFasaDAO;
    @Inject
    StrataPtService strService;
    Dokumen dokumen;
    String path;
    Pengguna pengguna;
    KodDokumen kd = null;

    public String generateReport(String idPermohonan, String stageId, String kodNegeri) {
        FasaPermohonan mohonFasa = new FasaPermohonan();
        String reportName;
        mohonFasa = strService.findMohonFasa(idPermohonan, stageId);
        FolderDokumen fd = null;

        StringBuilder sb = new StringBuilder();
        String idFolder = String.valueOf(mohonFasa.getPermohonan().getFolderDokumen().getFolderId());
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        dokumen = saveOrUpdateDokumen(fd, kd, sb.toString(), pengguna);
        path = idFolder + File.separator + String.valueOf(dokumen.getIdDokumen());
        reportName = getReportName(mohonFasa, dokumenPath);
        return null;
    }

    public String getReportName(FasaPermohonan mohonFasa, String dokumenPath) {
        String reportName = null;
        String params[] = null;
        String values[] = null;
        if (mohonFasa.getPermohonan().getKodUrusan().getKod().equals("PBBS")) {
            if (mohonFasa.getIdAliran().equals("semaklaporan")) {
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("Y")) {
                        reportName = "STR_LaporanTanah_MLK.rdf";
                        kd = kodDokumenDAO.findById("LT");
                    } else {
                        reportName = "STR_LaporanTanah_MLK.rdf";
                        kd = kodDokumenDAO.findById("LT");
                    }
                } else {
                }

            }
            if (mohonFasa.getIdAliran().equals("keputusan")) {
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("Y")) {
                        reportName = "STR_LaporanTanah_MLK.rdf";
                        kd = kodDokumenDAO.findById("LT");
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen());

                        reportName = "STR_LaporanTanah_MLK.rdf";
                        kd = kodDokumenDAO.findById("LT");
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen());
                    } else {
                        reportName = "STR_LaporanTanah_MLK.rdf";
                        kd = kodDokumenDAO.findById("LT");
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen());
                    }
                } else {
                }
            }
        }

        return reportName;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd,
            KodDokumen kd,
            String id,
            Pengguna pengguna) {

        pengguna = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
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
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(3);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod());
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
}
