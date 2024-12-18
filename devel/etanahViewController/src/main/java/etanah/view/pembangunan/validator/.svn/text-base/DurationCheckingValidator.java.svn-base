/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import etanah.workflow.StageListener;
import com.google.inject.Inject;
import java.util.List;
import etanah.workflow.StageContext;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Permohonan;
import org.apache.log4j.Logger;
import etanah.model.Notis;
import etanah.model.PermohonanAsal;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import etanah.service.PembangunanService;

/**
 *
 * @author nursyazwani
 */
public class DurationCheckingValidator implements StageListener {

    @Inject
    PembangunanService devServ;
    @Inject
    PembangunanService devService;
    private static final Logger LOG = Logger.getLogger(DurationCheckingValidator.class);
    private String stage;

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
            //validate to create permohonan sebelum & pemohon
            Permohonan permohonan = context.getPermohonan();
            if (permohonan.getKodUrusan().getKod().equals("RLTB") && context.getStageName().equals("sediakertasringkas3bln")|| permohonan.getKodUrusan().getKod().equals("RPS") && context.getStageName().equals("sediaderafmmk") || permohonan.getKodUrusan().getKod().equals("RPP") && context.getStageName().equals("sediaderafmmk")) {
                if (permohonan.getSenaraiPemohon().isEmpty()) {
                    context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                    LOG.info("mohon asal is null!!");
                    return null;
                }
                PermohonanAsal mohonAsal = devService.findPermohonanAsal(permohonan.getIdPermohonan());
                 if (mohonAsal == null) {
                    context.addMessage("Sila masukkan Maklumat Permohonan Sebelum: " + permohonan.getIdPermohonan());
                    LOG.info("mohon asal is null!!");
                    return null;
                }
            }
            outcome = checkstageID(context);
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        proposedOutcome = outcome;
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);
            if (stage.contentEquals("sediaderafmmk")) {
                PermohonanAsal ma = devServ.findPermohonanAsal(permohonan.getIdPermohonan());
                Notis notis = devServ.findNotisByIdPermohonan(ma.getIdPermohonanAsal().getIdPermohonan());
                try {
                    if (notis != null) {
                        Date date = notis.getTarikhNotis();
                        Calendar calNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                        Calendar now = Calendar.getInstance();
                        Calendar endNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                        calNotis.add(Calendar.DATE, 76);
                        endNotis.add(Calendar.DATE, 90);
                        if (now.after(calNotis) && now.before(endNotis)) {
                            value = "DT";
                            return value;
                        } else if (now.before(calNotis)) {
                            value = "HT";
                            return value;
                        } else if (now.after(endNotis)) {
                            value = "HT";
                            return value;
                        }
                    }
                } catch (Exception e) {
                    LOG.info("Exception:" + e);
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
