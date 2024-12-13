/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.service.common.PermohonanBaruPengambilanService;
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
import etanah.service.common.NotisPenerimaanService;
import java.math.BigDecimal;

/**
 *
 * @author nordiyana
 */
public class NotaBicaraValidator implements StageListener {
    private static final Logger LOG = Logger.getLogger(NotaBicaraValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    NotisPenerimaanService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanBaruPengambilanService gipw;
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
    HakmilikPermohonanDAO hakpDAO;
     @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
//    private HakmilikPerbicaraan hp;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPerbicaraan> listLulus;
    private List<HakmilikPerbicaraan> listTangguh;
    private List<HakmilikPerbicaraan> listPertikai;
    private List<HakmilikPermohonan> listSenaraiLulus;
    private List<HakmilikPermohonan> listSenaraiTangguh;
    private List<HakmilikPermohonan> listSenaraiPertikai;
    private String idPermohonan;

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
        hakmilikPermohonanList=permohonan.getSenaraiHakmilik();
        KodUrusan kk = permohonan.getKodUrusan();
        Pengguna pp = context.getPengguna();
        FolderDokumen ff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        idPermohonan=permohonan.getIdPermohonan();
        int cL=0;
        int cTG=0;
        int cPR=0;
        int totalResult=0;
        listLulus=service.getHakmilikPerbicaraanLulus(idPermohonan);
        listTangguh=service.getHakmilikPerbicaraanTangguh(idPermohonan);
        listPertikai=service.getHakmilikPerbicaraanPertikai(idPermohonan);
        listSenaraiLulus=service.getSenaraiHakmilikPerbicaraanLulus(idPermohonan);
        cL=listLulus.size();
        cTG=listTangguh.size();
        cPR=listPertikai.size();
        totalResult=cL+cTG+cPR;
        HakmilikPermohonan hakp;
        
       
        List<Hakmilik> hh = new ArrayList<Hakmilik>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                hh.add(hakmilikPermohonan.getHakmilik());
            }


  
           if(hakmilikPermohonanList.size() == totalResult)
           {
               if(cL!=0)
               {
                   LOG.info("Lulus");
                   for(HakmilikPermohonan hakmilikPermohonan:listSenaraiLulus)
                   {
                       LOG.info("Tukar status L dlm mohon hakmilik");
                            InfoAudit info = pp.getInfoAudit();
                            info.setDiKemaskiniOleh(pp);
                            info.setTarikhKemaskini(new java.util.Date());
                            hakmilikPermohonan.setInfoAudit(info);
                            hakmilikPermohonan.setPenjenisan("L");
                            service.saveOrUpdateHakmilikPermohonan(hakmilikPermohonan);
                   }
                
               }
               if(cTG!=0)
               {
                   LOG.info("Tangguh");
                   Permohonan pBaru = null;
                   String status="TA";
                   InfoAudit info = pp.getInfoAudit();
                   info.setDimasukOleh(pp);
                   info.setTarikhMasuk(new java.util.Date());
                   for (HakmilikPermohonan hakmilikPermohonan : listSenaraiTangguh) 
                   {
                            hh.add(hakmilikPermohonan.getHakmilik());
                   }
                        
                        try {
                            LOG.info("Create ID Permohonan Baru");
                            pBaru = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, ff);
                            updateOutcome(pBaru, pp, status);
                            pBaru.setInfoAudit(info);
                            pBaru.setPermohonanSebelum(permohonan);
                            pBaru.setStatus(status);
                            service.saveOrUpdatePermohonan(pBaru);
                            for(HakmilikPermohonan hakmilikPermohonan:listSenaraiTangguh)
                            {
                            
                            for(HakmilikPermohonan hakPer:hakmilikPermohonanList)
                            {
                                LOG.info("Checking idmh dlm listtangguh ngan hakmilikpermohonan");
                              if(hakPer.getId()==hakmilikPermohonan.getId())
                              {
                                 LOG.info("save hakmilikpermohonan id mohon baru");
                                 hakmilikPermohonan.setInfoAudit(info);
                                 hakmilikPermohonan.setPermohonan(pBaru);
                                 hakmilikPermohonan.setHakmilik(hakPer.getHakmilik());
                                 hakmilikPermohonan.setKodHakmilik(hakPer.getKodHakmilik());
                                 hakmilikPermohonan.setLuasTerlibat(hakPer.getLuasTerlibat());
                                 hakmilikPermohonan.setKodUnitLuas(hakPer.getKodUnitLuas());
                              }
                            }
                            listSenaraiTangguh.add(hakmilikPermohonan);
                            service.saveOrUpdateHakmilikPermohonan(hakmilikPermohonan);
                            }
                            LOG.info("save mohon pihak id mohon baru");
                            permohonanSupayaBantahanService.simpanPermohonanPihak(pBaru, pp);
                        } catch (StaleObjectException ex) {
                            LOG.info(ex);
                        } catch (WorkflowException ex) {
                            LOG.info(ex);
                        }

                        context.addMessage(" - Penghantaran Berjaya. Perbicaraan ke atas hakmilik - hakmilik yang ditangguh akan Disambung Pada ID Permohonan Berikut : " + pBaru.getIdPermohonan());
                
               }
               if(cPR!=0)
               {
                   Permohonan pBaru = null;
                   String status="TA";
                        
                   InfoAudit info = pp.getInfoAudit();
                   info.setDimasukOleh(pp);
                   info.setTarikhMasuk(new java.util.Date());
                   for (HakmilikPermohonan hakmilikPermohonan : listSenaraiTangguh) 
                   {
                            hh.add(hakmilikPermohonan.getHakmilik());
                   }
                        
                        try {
                            pBaru = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, ff);
                            updateOutcome(pBaru, pp, status);
                            
                            for(HakmilikPermohonan hakmilikPermohonan:listSenaraiTangguh)
                            {
                            
                            for(HakmilikPermohonan hakPer:hakmilikPermohonanList)
                            {
                              if(hakPer.getHakmilik()==hakmilikPermohonan.getHakmilik())
                              {
                                 
                                 hakmilikPermohonan.setInfoAudit(info);
                                 hakmilikPermohonan.setPermohonan(pBaru);
                                 hakmilikPermohonan.setHakmilik(hakPer.getHakmilik());
                                 hakmilikPermohonan.setKodHakmilik(hakPer.getKodHakmilik());
                                 hakmilikPermohonan.setLuasTerlibat(hakPer.getLuasTerlibat());
                                 hakmilikPermohonan.setKodUnitLuas(hakPer.getKodUnitLuas());
                                 hakmilikPermohonan.setPenjenisan("L");
                              }
                            }
                            listSenaraiTangguh.add(hakmilikPermohonan);
                            service.saveOrUpdateHakmilikPermohonan(hakmilikPermohonan);
                            }
                            pBaru.setInfoAudit(info);
                            pBaru.setPermohonanSebelum(permohonan);
                            pBaru.setStatus(status);
                            service.saveOrUpdatePermohonan(pBaru);
                            permohonanSupayaBantahanService.simpanPermohonanPihak(pBaru, pp);
                        } catch (StaleObjectException ex) {
                            LOG.info(ex);
                        } catch (WorkflowException ex) {
                            LOG.info(ex);
                        }

                        context.addMessage(" - Penghantaran Berjaya. Perbicaraan yang terdapat pertikaian yang akan dirujuk ke Mahkamah akan Disambung Pada ID Permohonan Berikut : " + pBaru.getIdPermohonan());
                
               }
           }

        return proposedOutcome;
    }

    @SuppressWarnings("static-access")
    public void updateOutcome(Permohonan p, Pengguna pp, String status) throws WorkflowException, StaleObjectException {

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
                    LOG.info("-----ptptdambil----");
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
                stageID = workFlowService.updateTaskOutcome(taskId, ctx, status);
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
