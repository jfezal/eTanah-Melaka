/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.InfoAudit;
import etanah.service.NotifikasiService;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.KodPeranan;
import java.util.Date;
import etanah.dao.KodPerananDAO;
import java.util.ArrayList;
import etanah.dao.KodCawanganDAO;

/**
 *
 * @author nursyazwani
 */
public class PindaanMMKNValidator implements StageListener {
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
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
            proposedOutcome = outcome;

            Notifikasi n = new Notifikasi();
            n.setTajuk("Makluman Pindaan Kertas MMKN (PTD)");
            n.setMesej("Kertas MMKN telah dipinda dan dihantar semula ke PTG.");
            Pengguna p = context.getPengguna();
//            n.setCawangan(p.getKodCawangan());
            n.setCawangan(kodCawanganDAO.findById("00"));
            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
            list.add(kodPerananDAO.findById("12")); //melaka - ptg
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            n.setInfoAudit(ia);
//            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            notifikasiService.addRolesToNotifikasi(n, kodCawanganDAO.findById("00"), list);
            context.addMessage("Makluman kepada Pengarah Tanah dan Galian telah dihantar.");
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
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
            if (stage.contentEquals("terimapindaan")) {
                value = "BP";
                return value;
            }
            if (stage.contentEquals("semakpindaanmmkn")) {
                value = "CP";
                return value;
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
