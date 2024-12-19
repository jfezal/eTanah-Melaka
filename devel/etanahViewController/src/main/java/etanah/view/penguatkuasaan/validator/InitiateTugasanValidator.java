/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import etanah.util.StringUtils;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiateTugasanValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    private PermohonanNota permohonanNota;
    private String stageId;
    private static final Logger LOG = Logger.getLogger(InitiateTugasanValidator.class);
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    String senaraiReport[], nama, kod;
    ArrayList reportName = new ArrayList<String>();
    private List<HakmilikPermohonan> senaraiTanahMilikKerajaan;
    private List<HakmilikPermohonan> senaraiTanahMilikPersendirian;
    private Boolean tanahMilik = Boolean.FALSE;
    private Boolean tanahKerajaan = Boolean.FALSE;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();
        stageId = context.getStageName();

        senaraiTanahMilikKerajaan = enforceService.findSenaraiTanahMilik(permohonan.getIdPermohonan());
        senaraiTanahMilikPersendirian = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());

        if (!senaraiTanahMilikKerajaan.isEmpty()) {
            tanahKerajaan = true;
        } else if (!senaraiTanahMilikPersendirian.isEmpty()) {
            tanahMilik = true;
        }

        LOG.info("tanahKerajaan : " + tanahKerajaan);
        LOG.info("tanahMilik : " + tanahMilik);

        if (permohonan.getRujukanUndang2() != null) {
            if (permohonan.getRujukanUndang2().equalsIgnoreCase("425") || permohonan.getRujukanUndang2().equalsIgnoreCase("425A")) {
                if (stageId.equalsIgnoreCase("g_sedia_pelan")) {
                    reportName.add("ENFLTNH351_MLK.rdf,LTNH");
                }
//                if (tanahKerajaan == true) {
//                    reportName.add("ENFLTNHKJAAN_MLK.rdf,LTNH");
//                } else if (tanahMilik == true) {
//                    reportName.add("ENFLTNH_MLK.rdf,LTNH");
//                }
            }

            if (permohonan.getRujukanUndang2().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("semak_laporan1") || stageId.equalsIgnoreCase("g_sedia_pelan")) {
                    reportName.add("ENFLTNH351_MLK.rdf,LTNH");
                }
            }

            if (permohonan.getRujukanUndang2().equalsIgnoreCase("426") || permohonan.getRujukanUndang2().equalsIgnoreCase("425") || permohonan.getRujukanUndang2().equalsIgnoreCase("425A")) {
                reportName.add("ENFDS_MLK.rdf,DIARI"); // report diari siasatan
                reportName.add("ENFNK_MLK.rdf,NKSH"); // report notis kesalahan
                reportName.add("ENFLPOP_MLK.rdf,LPOP"); // report laporan operasi polis
                reportName.add("ENFSNS_MLK.rdf,SNST"); // report notis sita
                reportName.add("ENFBB_MLK.rdf,BB"); // report borang bongkar
//                reportName.add("ENFLAWAL_MLK.rdf,LAWAL"); // report laporan awalan
                reportName.add("ENFLPST422_MLK.rdf,LPST"); // report laporan siasatan
//                reportName.add("ENFSERAH_MLK.rdf,SERAH"); // report serahan barang rampasan
            }

            LOG.info("::: size report (" + permohonan.getRujukanUndang2() + "): " + reportName.size());
            ArrayList<String> data = reportName;
            for (String a : data) {
                senaraiReport = a.split(",");
                LOG.info("length senaraiReport : " + senaraiReport.length);
                if (senaraiReport.length > 1) {
                    nama = senaraiReport[0];
                    kod = senaraiReport[1];
                    LOG.info("nama report : " + nama + " kod : " + kod);
                    janaDokumen(permohonan, pengguna, nama, kod);
                }
            }
        }

        //clear value at akuan penerimaan

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("999") && stageId.equalsIgnoreCase("g_sedia_pelan")) {
                try {
                    FolderDokumen f = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), "RAP");
                    LOG.info("::update table dokumen RAP : stage id ::: " + stageId);
                    for (int i = 0; i < listDokumen.size(); i++) {
                        Dokumen d = listDokumen.get(i).getDokumen();
                        if (d != null) {
                            d.setPerihal(null);
                            enforcementService.simpanDokumen(d);
                        }
                    }
                } catch (Exception ex) {
                    LOG.error(ex);
                }

            }
        }
    }

    public void janaDokumen(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport) {
        try {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, need to generate report for Perlantikan Pegawai Penyiasat
                LOG.info("------------generate report--------------");

                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{permohonan.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                String reportName = "";

                kd = kodDokumenDAO.findById(kodReport);
                reportName = namaReport;
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna);
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        if (!StringUtils.isBlank(kd.getNama())) {
            doc.setTajuk(kd.getNama());
        }
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
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota != null) {
            LOG.info("::: kandungan nota :" + permohonanNota.getNota());
            context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
            return null;
        } else {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }


        try {
            if (permohonanNota == null) {
                if (permohonan.getRujukanUndang2() != null) {
                    InfoAudit ia = new InfoAudit();
                    System.out.println("temp kod urusan : " + permohonan.getRujukanUndang2());
                    ia = permohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());

                    permohonan.setInfoAudit(ia);
                    permohonan.setKodUrusan(kodUrusanDAO.findById(permohonan.getRujukanUndang2()));
                    //permohonan.setRujukanUndang2(null);
                    enforceService.simpanPermohonan(permohonan);

                }
            }


            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                LOG.info("--------------Kod Urusan Null-------------");
                context.addMessage("Sila Pilih Kod Urusan Baru : " + permohonan.getIdPermohonan());
                return null;
            }


        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        try {
            Permohonan permohonan = context.getPermohonan();
            Pengguna pengguna = context.getPengguna();

            if (permohonan.getRujukanUndang2() != null) {
                InfoAudit ia = new InfoAudit();
                System.out.println("temp kod urusan : " + permohonan.getRujukanUndang2());
                ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());

                permohonan.setInfoAudit(ia);
                permohonan.setKodUrusan(kodUrusanDAO.findById(permohonan.getRujukanUndang2()));
//                permohonan.setRujukanUndang2(null);
                enforceService.simpanPermohonan(permohonan);

            }

            LOG.info("--------------before initiate : -------------" + permohonan.getKodUrusan().getKod());
            try {
                if (permohonan.getKodUrusan().getKePTG() == 'Y') {
                    WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                            permohonan.getIdPermohonan(), permohonan.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                            permohonan.getKodUrusan().getNama());
                } else if (permohonan.getKodUrusan().getKePTG() == 'T') {
                    WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                            permohonan.getIdPermohonan(), permohonan.getCawangan().getKod(), pengguna.getIdPengguna(),
                            permohonan.getKodUrusan().getNama());
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
            LOG.info("Initiating BPEL Success....");

            LOG.info("------------- SKIP STAGE ---------------");

            ctxOnBehalf = WorkFlowService.authenticate(pengguna.getIdPengguna()); //pptd
            if (ctxOnBehalf != null) {
                System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                System.out.println("id mohon : " + permohonan.getIdPermohonan());

                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                LOG.info("1) Task FOund(size)::" + l.size());
                if (l.isEmpty()) {
                    try {
                        Thread.sleep(5000);
                        l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
                LOG.info("2) Task FOund (size)::" + l.size());
                for (Task t : l) {
                    stageId = t.getSystemAttributes().getStage();
                    taskId = t.getSystemAttributes().getTaskId();
                    WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                    LOG.debug("Claim Found Task::" + taskId);
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")
                            || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                        System.out.println("----- for sek426/425/425A/127----- ");
                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "LF"); // 
//                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "T"); // 
                    }


                    LOG.debug("stage id ::::::::::::::::" + stageId);
                    LOG.debug("next stage ::::::::::::::::" + nextStage);
                    LOG.debug("Tugasan dihantar ke : " + nextStage);
                }
            }
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(InitiateTugasanValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WorkflowException ex) {
            java.util.logging.Logger.getLogger(InitiateTugasanValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        Permohonan permohonan = ctx.getPermohonan();
        if (permohonan.getRujukanUndang2() == null) {
            LOG.info("--------------Kod Urusan Null-------------");
            ctx.addMessage(permohonan.getIdPermohonan() + ": Sila Pilih Kod Urusan Baru di tab Pertukaran Urusan sebelum Jana Dokumen");
            return false;
        }

        return true;
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
