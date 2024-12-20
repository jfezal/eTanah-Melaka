
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import etanah.model.PermohonanRujukanLuar;
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
import etanah.service.ConsentPtdService;
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
public class MmkValidation implements StageListener {

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
    ConsentPtdService consentService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private static Pengguna pengguna;
    private static final Logger LOGGER = Logger.getLogger(MmkValidation.class);

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

            //function to generate paper mmk/rayuan moved to stage 4
            if (context.getStageName().equals("Stage4") || context.getStageName().equals("Stage6")) {

                if (permohonan.getPermohonanSebelum() == null) {
                    //KES PERMOHONAN BIASA
                    kd = kodDokumenDAO.findById("KKM"); // FOR KERTAS RINGKASAN MMK
                    if (permohonan.getKodUrusan().getKod().equals("PCMMK")) {
                        reportName = "CONSKertasRingkasanMMK_Serentak.rdf";
                    } else if (permohonan.getKodUrusan().getKod().equals("GSMMK")) {
                        reportName = "CONSKertasRingkasanMMK_GSA.rdf";
                    } else {
                        reportName = "CONSKertasRingkasanMMK.rdf";
                    }

                } else {
                    //KES PERMOHONAN RAYUAN
                    kd = kodDokumenDAO.findById("KRY"); // FOR KERTAS RINGKASAN MMK RAYUAN
                    reportName = "CONSKertasRayuanMMK.rdf";
                }

                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            } else if (context.getStageName().equals("Stage9")) {

                if (permohonan.getKeputusan() != null) {

                    if (permohonan.getKeputusan().getKod().equals("L") || permohonan.getKeputusan().getKod().equals("T")) {
                        //SK no longer generated by etanah; SPE remain (for MMK)
                        for (int i = 1; i < 2; i++) {
                            if (i == 0) {
                                kd = kodDokumenDAO.findById("SK"); // FOR SURAT KEBENARAN
                                if (permohonan.getKodUrusan().getKod().equals("PCMMK")) {
                                    reportName = "CONS_Surat_Keb_PMTGD_MMK_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("PJMMK")) {
                                    reportName = "CONS_Surat_Keb_PJK_MMK_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("MCMMK")) {
                                    reportName = "CONS_Surat_Keb_GD_MMK_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().startsWith("PMMK")) {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("GSMMK")) {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                } else {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                }

                            } else {
                                kd = kodDokumenDAO.findById("SPE"); // FOR SURAT PEMBERITAHUAN
                                if (permohonan.getKodUrusan().getKod().equals("PCMMK")) {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_PMT_GD_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("PJMMK")) {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_PJK_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("MCMMK")) {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_GD_NS.rdf";
                                //} else if (permohonan.getKodUrusan().getKod().startsWith("PMMK")) {
                                } else if (permohonan.getKodUrusan().getKod().equals("PMMK")) {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_PMT_NS.rdf";
                                } else if (permohonan.getKodUrusan().getKod().equals("GSMMK")) {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_PMT_NS.rdf";
                                } else {
                                    reportName = "CONS_SRT_PEMBERITAHUAN_PMT_NS.rdf";
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

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        if (context.getStageName().equals("Stage4")) {
            try {
                boolean generated = false;

                for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("KKM") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("KRY")) {
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
        } else if (context.getStageName().equals("Stage9")) {
            try {
                boolean generated = false;

                for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SPE")) {
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
        } else if (context.getStageName().equals("Stage8")) {
            //to key in maklumat mesyuarat @stage8 urusan MMK            

            if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                return null;
            } else {
                PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                    context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                    return null;
                }
            }
        }

        //stage to key-in maklumat mesyuarat MMK by SUMMK no longer required
        /*if (permohonan.getKodUrusan().getKod().equals("PMWNA") || permohonan.getKodUrusan().getKod().equals("PMWWA")) {
         if (context.getStageName().equals("Stage23")) {

         PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(permohonan.getIdPermohonan());
         if (permohonanRujukanLuar == null) {
         context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
         return null;
         } else {
         if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
         context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
         return null;
         }
         }
         }
         }*/
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        //stage to key-in maklumat mesyuarat MMK by SUMMK no longer required
        /*Permohonan permohonan = ctx.getPermohonan();

         if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
         ctx.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
         return Boolean.FALSE;
         } else {
         PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
         if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
         ctx.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
         return Boolean.FALSE;
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
