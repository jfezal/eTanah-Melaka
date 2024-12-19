
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.ValidationService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class MbValidation implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private static Pengguna pengguna;
    private static final Logger LOGGER = Logger.getLogger(MbValidation.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();

        pengguna = context.getPengguna();
        if (permohonan != null) {
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            if (context.getStageName().equals("Stage4") ||context.getStageName().equals("Stage6")) {
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        kd = kodDokumenDAO.findById("SJK"); // FOR SIJIL MB
                        if (permohonan.getKodUrusan().getKod().equals("PMTMB")) {
                            reportName = "CONS_SIJIL_MB_PMT_NS.rdf";
                        } else if (permohonan.getKodUrusan().getKod().equals("MCGMB")) {
                            reportName = "CONS_SIJIL_MB_GD_NS.rdf";
                        }
                    } else {
                        if (permohonan.getPermohonanSebelum() == null) {
                            kd = kodDokumenDAO.findById("KKM"); // FOR KERTAS RINGKASAN MB
                            //KES PERMOHONAN BIASA
                            if (permohonan.getKodUrusan().getKod().equals("PGDMB")) {
                                reportName = "CONSKertasRingkasanMB_Serentak.rdf";
                            } else {
                                reportName = "CONSKertasRingkasanMB.rdf";
                            }

                        } else {
                            //KES PERMOHONAN RAYUAN
                            kd = kodDokumenDAO.findById("KRY"); // FOR KERTAS RAYUAN MB
                            reportName = "CONSKertasRayuanMB.rdf";
                        }
                    }

                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                }
                //KES SERENTAK -- JANA DUA JENIS SIJIL
                if (permohonan.getKodUrusan().getKod().equals("PGDMB")) {
                    for (int j = 0; j < 2; j++) {
                        if (j == 0) {
                            kd = kodDokumenDAO.findById("SJK"); // FOR SIJIL MB PINDAHMILIK
                            reportName = "CONS_SIJIL_MB_PMT_NS.rdf";
                        } else {
                            kd = kodDokumenDAO.findById("SJK2"); // FOR SIJIL MB GADAIAN
                            reportName = "CONS_SIJIL_MB_GD_NS.rdf";
                        }

                        d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    }
                }
            } else if (context.getStageName().equals("Stage8")) {

                kd = kodDokumenDAO.findById("SK"); // FOR SURAT
                if (permohonan.getKodUrusan().getKod().equals("PMTMB")) {
                    reportName = "CONS_Surat_Keb_PMT_MB_NS.rdf";
                } else if (permohonan.getKodUrusan().getKod().equals("PGDMB")) {
                    reportName = "CONS_Surat_Keb_PMTGD_MB_NS.rdf";
                } else if (permohonan.getKodUrusan().getKod().equals("MCGMB")) {
                    reportName = "CONS_Surat_Keb_GD_MB_NS.rdf";
                }
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        if (context.getStageName().equals("Stage4") || context.getStageName().equals("Stage8")) {
            try {
                boolean generated = false;

                for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                    /*stage4 must generate either KKM or KRY*/
                    /*stage8 must generate SK*/
                    if (((kandunganFolder.getDokumen().getKodDokumen().getKod().equals("KKM")
                            ||kandunganFolder.getDokumen().getKodDokumen().getKod().equals("KRY")) &&  context.getStageName().equals("Stage4")) 
                            || (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK") &&  context.getStageName().equals("Stage8"))) {
                        generated = true;
                        break;
                    }
                }

                if (!generated) {
                    context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                    return null;
                }

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return null;
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
        return Boolean.TRUE;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            //doc.setBaru('Y');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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
        return "back";
    }
}
