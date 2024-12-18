/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KandunganFolder;
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
public class RekodBidaanN9Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(RekodBidaanN9Validator.class);
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
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
        if (fasaPermohonan != null) {
            FasaPermohonan ff2 = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
            if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                FolderDokumen fd = permohonan.getFolderDokumen();
                List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
                String memo = "";
                String km = "";
                for (KandunganFolder kf : listKD) {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                        memo = "MEMO";
                    }
                    if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                        km = "KM";
                    }
                }

                if (ff2.getKeputusan().getKod().equals("PH")) {
                    if (!memo.equals("MEMO")) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Memorandum Jualan Di Tab Memorandum Jualan");
                        return null;
                    }
                    if (!km.equals("KM")) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Komisyen Jualan Di Tab Komisyen Jualan");
                        return null;
                    }
                }
            }

            if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                FasaPermohonan fp = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
                if (fp != null) {
                    lelongService.delete(fp);
                }
                LOG.info(this);
                if (permohonan.getSenaraiHakmilik().size() >= 2) {
                    List<Lelongan> ll = lelongService.listLelonganA(permohonan.getIdPermohonan());
                    List<Hakmilik> hh = new ArrayList<Hakmilik>();
                    List<Lelongan> lel = new ArrayList<Lelongan>();
                    KodUrusan kk = permohonan.getKodUrusan();
                    Pengguna pp = penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna());
                    FolderDokumen ff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    for (Lelongan oo : ll) {
                        if (oo.getPembida() != null) {
                            hh.add(oo.getHakmilikPermohonan().getHakmilik());
                            lel.add(oo);
                        }
                    }
                    LOG.info("List<Lelongan> : " + lel.size());
                    LOG.info("List<Hakmilik> : " + hh.size());
                    if (hh.size() > 0) {
                        FolderDokumen fd = permohonan.getFolderDokumen();
                        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
                        String memo = "";
                        String km = "";
                        for (KandunganFolder kf : listKD) {
                            if (kf.getDokumen().getKodDokumen().getKod().equals("MEMO")) {
                                memo = "MEMO";
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals("KM")) {
                                km = "KM";
                            }
                        }
                        if (!memo.equals("MEMO")) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Memorandum Jualan Di Tab Memorandum Jualan");
                            return null;
                        }
                        if (ff2.getKeputusan().getKod().equals("PH")) {
                            if (!km.equals("KM")) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Komisyen Jualan Di Tab Komisyen Jualan");
                                return null;
                            }
                        }
                        try {
                            Permohonan p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, ff, lel);
                            updateOutcome(p, pp);
                        } catch (StaleObjectException ex) {
                            LOG.info(ex);
                        } catch (WorkflowException ex) {
                            LOG.info(ex);
                        }
                    }
                }
            }
            if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                List<Lelongan> lel = lelongService.listLelonganAK(permohonan.getIdPermohonan());
                for (Lelongan ll : lel) {
                    if (ll.getPembida() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Maklumat Pembida Di Tab Kemasukan Rekod Bidaan");
                        return null;
                    }
                }
                FasaPermohonan fp = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
                if (fp != null) {
                    lelongService.delete(fp);
                }
            }
        }
        proposedOutcome = fasaPermohonan.getKeputusan().getKod();
//        context.setNoRujukan(context.getPermohonan().getIdPermohonan());
        return proposedOutcome;
    }

    @SuppressWarnings("static-access")
    public void updateOutcome(Permohonan p, Pengguna pp) throws WorkflowException, StaleObjectException {

        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
        LOG.info("IDMohon : " + p.getIdPermohonan());
        List<Task> taskList = null;
        do {
            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
            LOG.info("taskList : " + taskList.size());
        } while (taskList.size() == 0);
        if (taskList.size() > 0) {
            Task t = taskList.get(0);
            String stageID = null;
            if (t.getSystemAttributes().getTaskId() != null) {
                String taskId = t.getSystemAttributes().getTaskId();
                LOG.info("taskId : " + taskId);
                Task tt = null;
                if (stageID != null) {
                    LOG.info("-----ptptdlelong----");
                    ctx = workFlowService.authenticate(pp.getIdPengguna());
                }
                tt = workFlowService.acquireTask(taskId, ctx);
                LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                do {
                    taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                    LOG.info("taskList : " + taskList.size());
                } while (taskList.size() == 0);
                t = taskList.get(0);
                taskId = t.getSystemAttributes().getTaskId();
                stageID = workFlowService.updateTaskOutcome(taskId, ctx, "L");
                LOG.info(" new stageID : " + stageID);
                LOG.info("----Abis Dah---- : " + stageID);
            }
        }
        LOG.info("------Finish------");
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
