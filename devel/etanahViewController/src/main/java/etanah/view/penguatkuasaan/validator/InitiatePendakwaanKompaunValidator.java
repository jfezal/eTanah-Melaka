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
import etanah.model.AduanOrangKenaSyak;
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
public class InitiatePendakwaanKompaunValidator implements StageListener {

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
    private static final Logger LOG = Logger.getLogger(InitiatePendakwaanKompaunValidator.class);
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    private List<Permohonan> senaraiPermohonanBaru = new ArrayList<Permohonan>();
    private String idPermohonanForDakwa;
    private String idPermohonanForKompaun;
    private String idPermohonanForNFA;
    private List<AduanOrangKenaSyak> senaraiOksForKompaunDakwa = new ArrayList<AduanOrangKenaSyak>();

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();

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

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

        senaraiPermohonanBaru = enforcementService.findSenaraiPermohonanBaruForOks(permohonan.getIdPermohonan());

        //1)Initiate task for new id permohonan
        for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {
            Permohonan permohonanBaru = senaraiPermohonanBaru.get(i);
            initiateTask(permohonanBaru, pengguna);
        }

        if (permohonan.getPermohonanSebelum() != null) {
            try {
                senaraiOksForKompaunDakwa = enforcementService.getListKompaunDakwaOks(permohonan.getPermohonanSebelum().getIdPermohonan());
                LOG.info("senaraiOksForKompaunDakwa : " + senaraiOksForKompaunDakwa.size());

                for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {
                    Permohonan permohonanBaru = senaraiPermohonanBaru.get(i);
                    for (int j = 0; j < senaraiOksForKompaunDakwa.size(); j++) {
                        AduanOrangKenaSyak aduanOrangKenaSyak = senaraiOksForKompaunDakwa.get(j);

                        if (aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan().equalsIgnoreCase(permohonanBaru.getIdPermohonan())) {
                            if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("D")) {
                                idPermohonanForDakwa = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            } else if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("K")) {
                                idPermohonanForKompaun = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            } else if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("N")) {
                                idPermohonanForNFA = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            }

                        }
                    }
                }
                LOG.info("idPermohonanForDakwa : " + idPermohonanForDakwa);
                LOG.info("idPermohonanForKompaun : " + idPermohonanForKompaun);
                LOG.info("idPermohonanForNFA : " + idPermohonanForNFA);

                //2) Skip stage

                try {
                    if (!StringUtils.isBlank(idPermohonanForDakwa)) {
                        skipStage(idPermohonanForDakwa, "DK");
                    }
                    if (!StringUtils.isBlank(idPermohonanForKompaun)) {
                        skipStage(idPermohonanForKompaun, "C");
                    }
                    if (!StringUtils.isBlank(idPermohonanForNFA)) {
                        skipStage(idPermohonanForNFA, "TT");
                    }
                } catch (WorkflowException ex) {
                    java.util.logging.Logger.getLogger(InitiatePendakwaanKompaunValidator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (StaleObjectException ex) {
                    java.util.logging.Logger.getLogger(InitiatePendakwaanKompaunValidator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(InitiatePendakwaanKompaunValidator.class.getName()).log(Level.SEVERE, null, ex);
                }

                //3) withdraw current task

                List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

                if (l.isEmpty()) {
                    try {
                        Thread.sleep(5000);
                        l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
                if (l != null) {
                    for (Task t : l) {
                        taskId = t.getSystemAttributes().getTaskId();
                        if (org.apache.commons.lang.StringUtils.isNotBlank(taskId)) {
                            try {
                                System.out.println("task id :::: " + taskId);
                                WorkFlowService.withdrawTask(taskId);
                                LOG.info("Pembatalan Berjaya");
                            } catch (StaleObjectException ex) {
                                ex.printStackTrace();
                                LOG.error(ex);
                            }
                        }
                    }
                }
            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(InitiatePendakwaanKompaunValidator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void initiateTask(Permohonan permohonanBaru, Pengguna pengguna) {
        LOG.info("-------initiateTask------- :::: " + permohonanBaru.getIdPermohonan());

        //initiate tugasan
        try {
            if (permohonanBaru.getKodUrusan().getKePTG() == 'Y') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            } else if (permohonanBaru.getKodUrusan().getKePTG() == 'T') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod(), pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void skipStage(String permohonanBaru, String keputusan) throws WorkflowException, StaleObjectException, Exception {

        LOG.info("------------- SKIP STAGE ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonanBaru);

            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru);
            LOG.info("1) Task FOund(size)::" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru);
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

                Permohonan p = permohonanDAO.findById(permohonanBaru);
                if (p.getKodUrusan().getKod().equalsIgnoreCase("426") || p.getKodUrusan().getKod().equalsIgnoreCase("425") || p.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    System.out.println("----- for sek426 or sek425----- ");
                    LOG.info("::: keputusan :" + keputusan);
                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, keputusan);
                }


                LOG.debug("current stage id ::::::::::::::::" + stageId);
                LOG.debug("next stage ::::::::::::::::" + nextStage);
                LOG.debug("Tugasan dihantar ke : " + nextStage);
            }
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
