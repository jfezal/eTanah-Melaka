/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;


/**
 *
 * @author mdizzat.mashrom
 */
public class BayaranValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(BayaranValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//        LOG.info("-------beforeComplete----");
//        Permohonan permohonan = context.getPermohonan();
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
//        if (fasaPermohonan != null) {
//            if (fasaPermohonan.getKeputusan().getKod().equals("LB")) {
//                LOG.info("-----belum bayar----");
//                if (permohonan.getSenaraiHakmilik().size() >= 2) {
//                    List<Lelongan> ll = lelongService.listLelongan(permohonan.getIdPermohonan());
//                    List<Hakmilik> hh = new ArrayList<Hakmilik>();
//                    KodUrusan kk = kodUrusanDAO.findById("PPJP");
//                    Pengguna pp = penggunaDAO.findById("ptptdlelong1");
//                    FolderDokumen ff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
//                    for (Lelongan oo : ll) {
//                        if (oo.getBaki() == null) {
//                            hh.add(oo.getHakmilikPermohonan().getHakmilik());
//                        }
//                    }
//                    if (hh.size() > 0) {
//                        LOG.info("----tak bayar lagi----");
//                        try {
//                            Permohonan p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, ff);
//                            updateOutcome(p, pp);
//                        } catch (StaleObjectException ex) {
//                            LOG.info(ex);
//                        } catch (WorkflowException ex) {
//                            LOG.info(ex);
//                        }
//                    }
//                    proposedOutcome = fasaPermohonan.getKeputusan().getKod();
//                }
//            }
//        }
//        context.setNoRujukan(context.getPermohonan().getIdPermohonan());
//        return proposedOutcome;
//    }
//
//    public void updateOutcome(Permohonan p, Pengguna pp) throws WorkflowException, StaleObjectException {
//
//        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
//        LOG.info("IDMohon : " + p.getIdPermohonan());
//        List<Task> taskList = null;
//        do {
//            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
//            LOG.info("taskList : " + taskList.size());
//        } while (taskList.size() == 0);
//        if (taskList.size() > 0) {
//            Task t = taskList.get(0);
//            String stageID = null;
//            if (t.getSystemAttributes().getTaskId() != null) {
//                String taskId = t.getSystemAttributes().getTaskId();
//                LOG.info("taskId : " + taskId);
//                Task tt = null;
////                do {
////                    tt = workFlowService.acquireTask(taskId, ctx);
////                    LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
////                } while (tt.getSystemAttributes().getAcquiredBy() == null);
//                do {
//                    if (stageID != null) {
//                        if (stageID.equals("semakanPermohonan") || stageID.equals("semak16HLantikJurulelong") || stageID.equals("semakPembida")) {
//                            LOG.info("-----pptlelong----");
//                            Pengguna ppp = penggunaDAO.findById("pptlelong1");
//                            ctx = workFlowService.authenticate(ppp.getIdPengguna());
//                        } else {
//                            LOG.info("-----ptptdlelong----");
//                            ctx = workFlowService.authenticate(pp.getIdPengguna());
//                        }
//                    }
//                    tt = workFlowService.acquireTask(taskId, ctx);
//                    LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
//                    do {
//                        taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
//                        LOG.info("taskList : " + taskList.size());
//                    } while (taskList.size() == 0);
//                    t = taskList.get(0);
//                    taskId = t.getSystemAttributes().getTaskId();
//                    stageID = workFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
//                    LOG.info("stageID : " + stageID);
//                } while (!stageID.equals("cetakSuratBakiBidaan"));
//            }
//        }
//
//    }
        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
