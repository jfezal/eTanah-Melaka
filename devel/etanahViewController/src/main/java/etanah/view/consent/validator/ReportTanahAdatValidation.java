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
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.report.ReportUtil;
import etanah.service.ConsentPtdService;
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
public class ReportTanahAdatValidation implements StageListener {

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
    ConsentPtdService consentService;
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
    private static final Logger LOGGER = Logger.getLogger(ReportTanahAdatValidation.class);
    private static Pengguna pengguna;

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

            //function to generate paper mmk moved to stage 6
            if (permohonan.getKodUrusan().getKod().equals("BTADT") && context.getStageName().equals("Stage6")) {
                //-------GENERATE KERTAS MMK BANTAHAN TANAH ADAT FIRST ROUND
                kd = kodDokumenDAO.findById("KKM");
                reportName = "CONSMMK_TanahAdat.rdf";
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //function to generate paper mmk2 moved to stage 15
            } else if (permohonan.getKodUrusan().getKod().equals("BTADT") && context.getStageName().equals("Stage15")) {
                //-------GENERATE KERTAS MMK BANTAHAN TANAH ADAT SECOND ROUND
                kd = kodDokumenDAO.findById("KKM2");
                reportName = "CONSMMK_TanahAdat.rdf";
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            } else if ((permohonan.getKodUrusan().getKod().equals("TMADT") && context.getStageName().equals("Stage8")) || (permohonan.getKodUrusan().getKod().equals("TMWNA") && context.getStageName().equals("Stage8"))) {
                //-------GENERATE PERINTAH UNTUK URUSAN TMADT AND TMWNA
                kd = kodDokumenDAO.findById("PBI");

                boolean foundPA = false;

                for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                    if (perPihak.getJenis().getKod().equals("PA")) {
                        foundPA = true;
                        break;
                    }
                }

                if (foundPA) {
                    reportName = "CONSPerintahSeksyen10Adat_PAADT_JadualF.rdf";
                } else {
                    reportName = "CONSPerintahSeksyen10Adat_JadualF.rdf";
                }

                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            } else {
                if (!context.getStageName().equals("Stage8")) {
                    //-------GENERATE SEMUA DOKUMEN TANAH ADAT KECUALI KERTAS MMK BANTAHAN TANAH ADAT
                    FasaPermohonan fasaPermohonan = new FasaPermohonan();

                    if (permohonan.getKodUrusan().getKod().equals("BTADT")) {
                        fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage14");
                    } else if (permohonan.getKodUrusan().getKod().equals("PMWNA")
                            || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                        fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage18");
                    } else {
                        fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage6");
                    }

                    if (fasaPermohonan != null) {
                        //-------IF KEPUTUSAN TANGGUH-------

                        if (permohonan.getKodUrusan().getKod().equals("BTADT")) { //BANTAHAN HANYA NOTIS BICARA
                            kd = kodDokumenDAO.findById("STBI");
                            reportName = "CONSSuratTangguhTanahAdat.rdf";
                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                        } else {
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    kd = kodDokumenDAO.findById("STBI");    // FOR SURAT TANGGUH BICARA
                                    reportName = "CONSSuratTangguhTanahAdat.rdf";

                                } else {
                                    kd = kodDokumenDAO.findById("NTB");     // FOR NOTA BICARA
                                    if (permohonan.getKodUrusan().getKod().equals("TMADT")
                                            || permohonan.getKodUrusan().getKod().equals("TMWNA")) {
                                        reportName = "CONSNotaBicara_TMADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PMADT")
                                            || permohonan.getKodUrusan().getKod().equals("PMWNA")
                                            || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                                        reportName = "CONSNotaBicara_PMADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("CGADT")) {
                                        reportName = "CONSNotaBicara_CGADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PAADT")) {
                                        reportName = "CONSNotaBicara_PAADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PPADT")) {
                                        reportName = "CONSNotaBicara_PPADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("TTADT")) {
                                        reportName = "CONSNotaBicara_TTADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("BPADT")) {
                                        reportName = "CONSNotaBicara_BPADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PJADT")) {
                                        reportName = "CONSNotaBicara_PJADT.rdf";
                                    } else {
                                        reportName = "CONSNotaBicara_TTADT.rdf";
                                    }
                                }

                                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                            }
                        }

                    } else {
                        //-------IF TIADA KEPUTUSAN TANGGUH-------

                        if (permohonan.getKodUrusan().getKod().equals("BTADT")) { //BANTAHAN HANYA NOTIS BICARA
                            kd = kodDokumenDAO.findById("NB");
                            reportName = "CONSNotisBantahanAdat.rdf";
                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                        } else {
                            for (int i = 0; i < 2; i++) {
                                if (i == 0) {
                                    kd = kodDokumenDAO.findById("NB");      // FOR NOTIS BICARA
                                    if (permohonan.getKodUrusan().getKod().equals("TMADT") || permohonan.getKodUrusan().getKod().equals("BPADT")
                                            || permohonan.getKodUrusan().getKod().equals("TMWNA")) {
                                        reportName = "CONSNotisSeksyen10Adat_JadualE.rdf";   // FOR NOTIS BICARA SEKSYEN 10
                                    } else if (permohonan.getKodUrusan().getKod().equals("PMADT")
                                            || permohonan.getKodUrusan().getKod().equals("CGADT")
                                            || permohonan.getKodUrusan().getKod().equals("PAADT")
                                            || permohonan.getKodUrusan().getKod().equals("PJADT")
                                            || permohonan.getKodUrusan().getKod().equals("PMWNA")
                                            || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                                        reportName = "CONSNotisSeksyen7Adat.rdf";            // FOR NOTIS BICARA SEKSYEN 7
                                    } else if (permohonan.getKodUrusan().getKod().equals("TTADT")) {
                                        reportName = "CONSNotisSeksyen9Adat.rdf";            // FOR NOTIS BICARA SEKSYEN 9
                                    } else if (permohonan.getKodUrusan().getKod().equals("PPADT")) {
                                        reportName = "CONSNotisSeksyen12Adat.rdf";            // FOR NOTIS BICARA SEKSYEN 12
                                    } else {
                                        reportName = "CONSNotisSeksyen10Adat_JadualE.rdf";
                                    }
                                } else {
                                    kd = kodDokumenDAO.findById("NTB");     // FOR NOTA BICARA
                                    if (permohonan.getKodUrusan().getKod().equals("TMADT")
                                            || permohonan.getKodUrusan().getKod().equals("TMWNA")) {
                                        reportName = "CONSNotaBicara_TMADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PMADT")
                                            || permohonan.getKodUrusan().getKod().equals("PMWNA")
                                            || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
                                        reportName = "CONSNotaBicara_PMADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("CGADT")) {
                                        reportName = "CONSNotaBicara_CGADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PAADT")) {
                                        reportName = "CONSNotaBicara_PAADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PPADT")) {
                                        reportName = "CONSNotaBicara_PPADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("TTADT")) {
                                        reportName = "CONSNotaBicara_TTADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("BPADT")) {
                                        reportName = "CONSNotaBicara_BPADT.rdf";
                                    } else if (permohonan.getKodUrusan().getKod().equals("PJADT")) {
                                        reportName = "CONSNotaBicara_PJADT.rdf";
                                    } else {
                                        reportName = "CONSNotaBicara_TMADT.rdf";
                                    }
                                }

                                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        try {
            boolean checkReport = false;
            String kodDokumen;

            //function to generate paper mmk moved to stage 6
            if (permohonan.getKodUrusan().getKod().equals("BTADT") && context.getStageName().equals("Stage6")) {
                kodDokumen = "KKM";
            } 
            //function to generate paper mmk moved to stage 15
            else if (permohonan.getKodUrusan().getKod().equals("BTADT") && context.getStageName().equals("Stage15")) {
                kodDokumen = "KKM2";
            } else if ((permohonan.getKodUrusan().getKod().equals("TMADT") && context.getStageName().equals("Stage8")) || (permohonan.getKodUrusan().getKod().equals("TMWNA") && context.getStageName().equals("Stage8"))) {
                kodDokumen = "PBI";
            } else {
                kodDokumen = "NB";
            }

            for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                    checkReport = true;
                }
            }

            if (!checkReport) {
                context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + permohonan.getIdPermohonan());
                return null;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        //stage to key-in maklumat mesyuarat MMK by SUMMK no longer required
        /*if (ctx.getPermohonan().getKodUrusan().getKod().equals("BTADT")) {
            if (ctx.getStageName().equals("Stage9") || ctx.getStageName().equals("Stage18")) {
                if (ctx.getPermohonan().getSenaraiRujukanLuar().isEmpty()) {
                    ctx.addMessage("Sila masukkan maklumat mesyuarat terlebih dahulu.");
                    return Boolean.FALSE;
                }
            }
        }*/
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
            doc.setBaru('T');
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
