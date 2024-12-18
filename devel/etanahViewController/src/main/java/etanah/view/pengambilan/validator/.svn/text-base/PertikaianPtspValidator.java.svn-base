/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
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
import etanah.model.KodKeputusan;
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
 * @author Admin
 */
public class PertikaianPtspValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(PertikaianPtspValidator.class);
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
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;

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

        List<HakmilikPermohonan> hakmilikPermohonan = pengambilanService.findPermohonanByIdPermohonanPegangan(idPermohonan);
        if (hakmilikPermohonan.size() > 0) {
            context.addMessage(permohonan.getIdPermohonan() + " - Penghantaran Tidak Berjaya Dihantar. Sila Klik Butang Simpan di Tab Pemilihan Keputusan Hakmilik.");
            return null;
        }


        FasaPermohonan fasaPermohonan = pengambilanService.findFasaPermohonanPertikaian(permohonan.getIdPermohonan());
        LOG.info("fasaPermohonan : " + pengambilanService.findFasaPermohonanPertikaian(permohonan.getIdPermohonan()));
        List<HakmilikPermohonan> hPP = notisPenerimaanService.getSenaraiHakmilikKedesakan(idPermohonan);
        List<HakmilikPermohonan> hPPM = notisPenerimaanService.getSenaraiHakmilikXKedesakan(idPermohonan);

        Pengguna pp = penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna());
        LOG.info("pp : " + penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna()));

//        }

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        //untuk dapatkan list hakmilik


        if (permohonan.getSenaraiHakmilik().size() == 1) {
            if (fasaPermohonan != null) {
                if (!(hPP.isEmpty()) && hPPM.isEmpty()) {
                    proposedOutcome = "PR";
                }
                if (hPP.isEmpty() && !(hPPM.isEmpty())) {
                    proposedOutcome = "PC";
                }
            }
        }

        KodKeputusan keputusan = new KodKeputusan();

        if (permohonan.getSenaraiHakmilik().size() > 1) {
            if (fasaPermohonan != null) {
                List<Hakmilik> hh = new ArrayList<Hakmilik>();
                StringBuilder sb = new StringBuilder();
                switch (gatCase(hPP, hPPM)) {
                    case 1:
                        //kedesakan semua
                        LOG.info("case 1");
                        proposedOutcome = "PR";
                        try {
                            keputusan = pengambilanService.findByKodKeputusan("PR");
                            fasaPermohonan.setKeputusan(keputusan);
                            fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
                        } catch (Exception h) {
                            System.out.println("Case 1 : Error Update Fasa PErmohonan : " + h);
                        }
                        break;
                    case 2:
                        //x kedesakan semua
                        LOG.info("case 2");
                        proposedOutcome = "PC";
                        try {
                            keputusan = pengambilanService.findByKodKeputusan("PC");
                            fasaPermohonan.setKeputusan(keputusan);
                            fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
                        } catch (Exception h) {
                            System.out.println("Case 2 :Error Update Fasa PErmohonan : " + h);
                        }
                        break;
                    case 3:
                        //satu kedesakan (idMohon Baru) , satu x kedesakan(idMohon Lama)
                        LOG.info("case 3");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : hPP) {
                            hh.add(hp.getHakmilik());
                        }
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat (Pertikaian) " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(hPP, null, pp, permohonan, hh));

                        proposedOutcome = "PC";
                        try {
                            keputusan = pengambilanService.findByKodKeputusan("PC");
                            fasaPermohonan.setKeputusan(keputusan);
                            fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
                        } catch (Exception h) {
                            System.out.println("Case 3 :Error Update Fasa PErmohonan : " + h);
                        }
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

    public int gatCase(List<HakmilikPermohonan> hPP, List<HakmilikPermohonan> hPPM) {
        if (!hPP.isEmpty() && hPPM.isEmpty()) {
            //case 1
            return 1;
        }
        if (hPP.isEmpty() && !hPPM.isEmpty()) {
            //case 2
            return 2;
        }
        if (!hPP.isEmpty() && !hPPM.isEmpty()) {
            //case 3
            return 3;
        }
        return 0;
    }

    public String newIdMohon(List<HakmilikPermohonan> hPP, List<HakmilikPermohonan> hPPM, Pengguna pp, Permohonan permohonan, List<Hakmilik> hh) {

        Permohonan p = null;
        String kptsn = "";
        if (!hPP.isEmpty()) {
            try {
                if (hPP != null) {
                    kptsn = "PR";
                    System.out.println("PERTIKAIAN ....");
                    p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById("PTSP"), pp, hh, permohonan, permohonan.getFolderDokumen());


                }
                if (hPPM != null) {
                    kptsn = "PC";
                    p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById("PTSP"), pp, hh, permohonan, permohonan.getFolderDokumen());

                }
                updateOutcome(p, pp, kptsn);

//                /////////  update kod keputusan di Fasa permohonan
//                if (hPK != null) {
//                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
//                    fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanBaru(p.getIdPermohonan());
//                    KodKeputusan keputusan = new KodKeputusan();
//                    keputusan = pengambilanService.findByKodKeputusan("DE");
//                    fasaPermohonan.setKeputusan(keputusan);
//                    fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
//                }
//
//                if (hPXK != null) {
//                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
//                    fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanBaru(p.getIdPermohonan());
//                    KodKeputusan keputusan = new KodKeputusan();
//                    keputusan = pengambilanService.findByKodKeputusan("XK");
//                    fasaPermohonan.setKeputusan(keputusan);
//                    fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
//                }

            } catch (StaleObjectException ex) {
                LOG.info(ex);
            } catch (WorkflowException ex) {
                LOG.info(ex);
            }
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
