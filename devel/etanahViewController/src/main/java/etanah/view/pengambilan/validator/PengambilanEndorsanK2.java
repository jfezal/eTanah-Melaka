/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.HakmilikPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanService;
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
import etanah.view.strata.GenerateIdPerserahanWorkflow;
/**
 *
 * @author nordiyana
 */
public class PengambilanEndorsanK2 implements StageListener {
   private static final Logger LOG = Logger.getLogger(PengambilanEndorsanK2.class);
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
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
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;

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
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan;
        idPermohonan = permohonan.getIdPermohonan();
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanPerbicaraan(permohonan.getIdPermohonan());
        FasaPermohonan fasaPermohonan = pengambilanService.findFasaPermohonanPelanB1(permohonan.getIdPermohonan());
        List<HakmilikPermohonan> hX4=notisPenerimaanService.getSenaraiHakmilikKedesakan(idPermohonan);
        List<HakmilikPermohonan> hL4=notisPenerimaanService.getSenaraiHakmilikXKedesakan(idPermohonan);
        List<HakmilikPermohonan> hK4=notisPenerimaanService.getSenaraiHakmilikXKedesakan(idPermohonan);

        Pengguna pp = penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna());

//        }

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        //untuk dapatkan list hakmilik


        if (permohonan.getSenaraiHakmilik().size() == 1) {
            if (fasaPermohonan != null) {
                if (!(hX4.isEmpty()) && hL4.isEmpty() && hK4.isEmpty()) {
                    proposedOutcome = "X4";
                }
                if (hX4.isEmpty() && !(hL4.isEmpty()) && hK4.isEmpty()) {
                    proposedOutcome = "L4";
                }
                if (hX4.isEmpty() && hL4.isEmpty() && !(hK4.isEmpty())) {
                    proposedOutcome = "K4";
                }
            }
        }

        if (permohonan.getSenaraiHakmilik().size() > 1) {
            if (fasaPermohonan != null) {
                List<Hakmilik> hh = new ArrayList<Hakmilik>();
                StringBuilder sb = new StringBuilder();
                switch (gatCase(hX4, hK4, hL4)) {
                    case 1:
                        //Keluasan semua sama
                        LOG.info("case 1");
                        proposedOutcome = "X4";
                        break;
                    case 2:

                        //Keluasan semua > suku hektar
                        LOG.info("case 2");
                        proposedOutcome = "L4";
                        break;
                    case 3:
                        //Keluasan semua < suku hektar
                        LOG.info("case 3");
                        proposedOutcome = "K4";
                        break;
                    case 4:
                        //satu sama luas (idMohon Baru) , satu <1/4 (idMohon Lama)
                        LOG.info("case 4");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hX4) {
                            hh.add(hp.getHakmilik());
                        }
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(hX4, null, pp, permohonan, hh));

                        proposedOutcome = "K4";
                        break;
                    case 5:
                        //satu sama luas (idMohon Baru), satu >1/4 (idMohon Lama)
                        LOG.info("case 5");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hL4) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(hX4, null, pp, permohonan, hh));

                        proposedOutcome = "L4";
                        break;
                    case 6:
                        //satu <1/4 (idMohon Baru), satu >1/4 (idMohon Lama)
                        LOG.info("case 6");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hK4) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(null, hK4, pp, permohonan, hh));

                        proposedOutcome = "L4";
                        break;
                    case 7:
                        //sama luas (idMohon Baru) , <1/4 (idMohon Baru) , >1/4 (idMohon Lama)
                        LOG.info("case 7");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hX4) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya<br/> - Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(hX4, null, pp, permohonan, hh));

                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hK4) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage("<br/> - Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(null, hK4, pp, permohonan, hh));

                        proposedOutcome = "L4";
                        break;
                    default:
                        //todo
                        proposedOutcome = "";
                        break;
                }
            }
        }
        return proposedOutcome;
    }

    public int gatCase(List<HakmilikPermohonan> hX4, List<HakmilikPermohonan> hL4, List<HakmilikPermohonan> hK4) {
        if (!hX4.isEmpty() && hL4.isEmpty() && hK4.isEmpty()) {
            //case 1
            return 1;
        }
        if (hX4.isEmpty() && !hL4.isEmpty() && hK4.isEmpty()) {
            //case 2
            return 2;
        }
        if (hX4.isEmpty() && hL4.isEmpty() && !hK4.isEmpty()) {
            //case 3
            return 3;
        }
        if (!hX4.isEmpty() && !hL4.isEmpty() && hK4.isEmpty()) {
            //case 4
            return 4;
        }
        if (!hX4.isEmpty() && hL4.isEmpty() && !hK4.isEmpty()) {
            //case 5
            return 5;
        }
        if (hX4.isEmpty() && !hL4.isEmpty() && !hK4.isEmpty()) {
            //case 6
            return 6;
        }
        if (!hX4.isEmpty() && !hL4.isEmpty() && !hK4.isEmpty()) {
            //case 7
            return 7;
        }
        return 0;
    }

    public String newIdMohon(List<HakmilikPermohonan> hX4, List<HakmilikPermohonan> hK4, Pengguna pp, Permohonan permohonan, List<Hakmilik> hh) {

        Permohonan p = null;
        String kptsn = "";
        try {
            if (hX4 != null) {
                kptsn = "X4";
                p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById("831A"), pp, hh, permohonan, permohonan.getFolderDokumen());
            }
            if (hK4 != null) {
                kptsn = "K4";
                p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById("831A"), pp, hh, permohonan, permohonan.getFolderDokumen());
            }
            FasaPermohonan peng = notisPenerimaanService.getPenggunaKemasukan01(permohonan.getIdPermohonan());
            if (peng != null) {
                pp = penggunaDAO.findById(peng.getIdPengguna());
            }
            updateOutcome(p, pp, kptsn);
        } catch (StaleObjectException ex) {
            LOG.info(ex);
        } catch (WorkflowException ex) {
            LOG.info(ex);
        }
        return p.getIdPermohonan();
    }

    @SuppressWarnings("static-access")
    public void updateOutcome(Permohonan p, Pengguna pp, String kptsn) throws WorkflowException, StaleObjectException {
        //first stage tambah advance rule
        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
        LOG.info("IDMohon : " + p.getIdPermohonan());
        List<Task> taskList = null;
        do {
            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
            LOG.info("taskList : " + taskList.size());
        } while (taskList.isEmpty());
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
                LOG.info("-----ctx---- : " + ctx);
                tt = workFlowService.acquireTask(taskId, ctx);
                LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                do {
                    taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                    LOG.info("taskList : " + taskList.size());
                } while (taskList.isEmpty());
                t = taskList.get(0);
                taskId = t.getSystemAttributes().getTaskId();
                stageID = workFlowService.updateTaskOutcome(taskId, ctx, kptsn);
                LOG.info(" new stageID : " + stageID);
                LOG.info("----Abis Dah---- : " + stageID);
            }
        }
        LOG.info("------Finish------");
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
