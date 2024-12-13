/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;

/**
 *
 * @author mdizzat.mashrom
 */
public class RekodPenghantaran16HValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    etanah.Configuration conf;
    private String stage;
    private static final Logger LOG = Logger.getLogger(RekodPenghantaran16HValidator.class);

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
        String outcome = "";
        LOG.info("---BEFORE COMPLETE---");

        try {
            if (conf.getProperty("kodNegeri").equals("04")) {
                outcome = checkstageID(context);
            } else {
                outcome = checkstageID(context);
            }
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        
        //tambahan baru
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        } 
        
        // farizal
        List<Notis> list = lelongService.getListNotis(idPermohonan, "16H");
      
        for (Notis nm : list) {
            LOG.info("=== Cek for Notis ===");

            if ("AR".equals(nm.getKodCaraPenghantaran().getKod()) || "PO".equals(nm.getKodCaraPenghantaran().getKod())) {
                LOG.info("=== Cek CaraHantar != AR ===");
                LOG.info("=== kod cara hantar = " + nm.getKodCaraPenghantaran());
            } else {
                if (nm.getPenghantarNotis() == null) {
                    LOG.info("=== Cek Penghantar ===");
                    context.addMessage(idPermohonan + " - Sila Pilih Nama Penghantar Notis Di Tab Rekod Penghantaran");
                    return null;
                }
            }
            if (nm.getKodStatusTerima() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Status Penyampaian Notis Di Tab Rekod Penghantaran");
                return null;
            }
            
            if (nm.getTarikhHantar() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Tarikh Hantar Di Tab Rekod Penghantaran");
                return null;
            }
        }

        proposedOutcome = outcome;
        LOG.info("proposed outcome: " + proposedOutcome);
        return proposedOutcome;
    }
    
    
    private String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        String urusan = permohonan.getKodUrusan().getKod();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            setStage(t.getSystemAttributes().getStage());
            LOG.info(getStage());

            if (urusan.equalsIgnoreCase("PPJP") || urusan.equalsIgnoreCase("PPTL") || urusan.equalsIgnoreCase("PJTA") ) {
                if (getStage().contentEquals("rekodPenghantaran16H")) {
                    String idAliranMhonFasa = "semak16HLantikJurulelong"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = lelongService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
        }
        return value;
    }

//    @Override
//    public String beforeComplete(StageContext context, String proposedOutcome) {
//        LOG.info("------beforeComplete1-------");
//        Permohonan permohonan = context.getPermohonan();
//        String idPermohonan = permohonan.getIdPermohonan();
//
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
//
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
//        if (fasaPermohonan != null) {
//
//            FasaPermohonan ff = lelongService.findFasaPermohonanRekodPenghantaran16H(permohonan.getIdPermohonan());
//
//            if (ff != null) {
//                ff.setKeputusan(fasaPermohonan.getKeputusan());
//                lelongService.saveUpdate2(ff);
//            } else {
//                ff = new FasaPermohonan();
//                Pengguna p = (Pengguna) context.getPengguna();
//                InfoAudit info = p.getInfoAudit();
//                info.setDimasukOleh(p);
//                info.setTarikhMasuk(new java.util.Date());
//                ff.setCawangan(fasaPermohonan.getCawangan());
//                ff.setIdPengguna(fasaPermohonan.getIdPengguna());
//                ff.setInfoAudit(info);
//                ff.setIdAliran("rekodPenghantaran16H");
//                ff.setPermohonan(permohonan);
//                ff.setTarikhHantar(new java.util.Date());
//                if (StringUtils.isNotEmpty(fasaPermohonan.getUlasan())) {
//                    ff.setUlasan(fasaPermohonan.getUlasan());
//                }
//                ff.setKeputusan(fasaPermohonan.getKeputusan());
//                lelongService.saveUpdate2(ff);
//            }
//        }
//
//        List<Notis> list = lelongService.getListNotis2(idPermohonan, "16H");
//        for (Notis nn : list) {
//            if (nn.getPenghantarNotis() == null) {
//                context.addMessage(idPermohonan + " - Sila Pilih Nama Penghantar Notis Di Tab Rekod Penghantaran");
//                return null;
//            }
//            if (nn.getKodStatusTerima() == null) {
//                context.addMessage(idPermohonan + " - Sila Pilih Status Penyampaian Notis Di Tab Rekod Penghantaran");
//                return null;
//            }
//            if (nn.getKodCaraPenghantaran() == null) {
//                context.addMessage(idPermohonan + " - Sila Pilih Cara Penghantaran Notis Di Tab Rekod Penghantaran");
//                return null;
//            }
//            if (nn.getTarikhHantar() == null) {
//                context.addMessage(idPermohonan + " - Sila Pilih Tarikh Hantar Di Tab Rekod Penghantaran");
//                return null;
//            }
//        }
//
//        proposedOutcome = fasaPermohonan.getKeputusan().getKod();
//        LOG.info("proposedOutcome : " + proposedOutcome);
//        return proposedOutcome;
//    }
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

    /**
     * @return the stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
    }
}
