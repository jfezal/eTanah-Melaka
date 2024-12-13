/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

//import com.google.inject.Inject;
import etanah.view.pembangunan.validator.*;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.*;
import etanah.service.*;
import java.util.Date;

public class CompleteValidator implements StageListener {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    private static final Logger LOG = Logger.getLogger(PindaanValidator.class);
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";
        try {
            outcome = checkstageID(context);
            if (outcome != null && !outcome.equals("")) {
                proposedOutcome = outcome;
                LOG.info(" ******** outcome condition***********:" + outcome);
            }
            LOG.info(" ******** outcome ***********:" + outcome);
            context.addMessage(" - Urusan telah selesai. ");
        } catch (WorkflowException ex) {
            LOG.info(" ******Exception** outcome ***********:" + outcome);
            LOG.error(ex.getMessage());
            return proposedOutcome;
        }
        LOG.info(" ******** proposedOutcome ***********:" + proposedOutcome);
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(" ******** stage ***********:" + stage);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPC")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSBS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBS")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPPP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PKKR")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("SUBMC")) {
                FasaPermohonan mohonFasa = null;
//                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")) {
//                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
//                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPPP")) {
                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semaksijil");
                } else {
                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
                }
                Pengguna peng = context.getPengguna();

                LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
                if (permohonan != null) {

                    permohonan.setKeputusan(mohonFasa.getKeputusan());
                    permohonan.setKeputusanOleh(peng);
                    permohonan.setTarikhKeputusan(new Date());
                    permohonan.setStatus("SL");
                    strService.updateMohon(permohonan);

                }
            }
            value = "COMPLETE";
        }
        return value;
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
//        return proposedOutcome;
        return "back";
    }
}
