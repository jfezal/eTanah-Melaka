/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

//import com.google.inject.Inject;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanTuntutanBayar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import etanah.model.PermohonanTuntutan;
import etanah.service.DevIntegrationService;
import java.util.ArrayList;

/**
 *
 * @author nursyazwani
 */
public class SemakPrahitungValidator implements StageListener {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    DevIntegrationService dis;
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

            if ((stage.contentEquals("semakprahitungtatatur9a"))
                    || (stage.contentEquals("semakprahitungtatatur9b"))
                    || (stage.contentEquals("semakprahitungtatatur9c"))) {

                if (h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN")) {
                    value = "HP";
                    LOG.info(" ******** value ***semakprahitungtatatur********:" + value);
                }
            }

            if ((stage.contentEquals("endorsanprahitungtatatur9a"))
                    || (stage.contentEquals("endorsanprahitungtatatur9b"))
                    || (stage.contentEquals("endorsanprahitungtatatur9c"))) {

                if (h.getKodHakmilik().getKod().equals("HSM") || h.getKodHakmilik().getKod().equals("GM")
                        || h.getKodHakmilik().getKod().equals("HMM") || h.getKodHakmilik().getKod().equals("GMM")
                        || h.getKodHakmilik().getKod().equals("PM")) {
                    value = "HT";
                    LOG.info(" ******** value *****endorsanprahitungtatatur******:" + value);
                }
            }

            
            //Tumpang Untuk Kes Lulus @ Tolak
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSKKT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSN")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSBSN") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSKSN")) {
                
                if (stage.contentEquals("terimakeputusanptg") || stage.contentEquals("terimakpsnptg")) {
                    if (permohonan.getKeputusan().getKod().equalsIgnoreCase("Y")) {
                        value = "Y";
                    } else {
                        value = "T";
                    }
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
}
