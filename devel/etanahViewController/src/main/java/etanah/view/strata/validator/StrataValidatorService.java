/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author faidzal
 */
public class StrataValidatorService {

    @Inject
    private TabManager tm;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    Permohonan permohonan;
    private Map<String, String> m;
    private List<Permohonan> listPermohonan = new ArrayList<Permohonan>();

    public Map<String, String> semakPermohonan(Permohonan p) throws WorkflowException {
        p = permohonanDAO.findById(p.getIdPermohonan());
        m = new HashMap();
        String status = null;
        String msg = null;
        Permohonan permohonanBaru = new Permohonan();
        listPermohonan = new ArrayList<Permohonan>();
        listPermohonan = strService.findListPermohonan(p.getIdPermohonan());
        for (Permohonan mohon : listPermohonan) {
            if (mohon.getKeputusan() != null) {
                status = "proceed";
            } else {
                List<Task> s = WorkFlowService.queryTasksByAdmin(mohon.getIdPermohonan());
                if (!s.isEmpty()) {
                    String currentAction = tm.getCurrentAction(mohon.getKodUrusan().getIdWorkflow(), s.get(0).getSystemAttributes().getStage());;
                    msg = "Harap maaf. Terdapat urusan : " + mohon.getIdPermohonan() + "("
                            + mohon.getKodUrusan().getNama() + ") sedang diproses oleh " + s.get(0).getSystemAttributes().getAcquiredBy() + " untuk tindakan " + currentAction;
                    status = null;
                }
                break;
            }
        }
        m.put("Mesej", msg);
        m.put("status", status);
        return m;
    }

    public void terminatePermohonan(Permohonan mohon, String string) throws WorkflowException, StaleObjectException {
        List<Task> s = WorkFlowService.queryTasksByAdmin(mohon.getIdPermohonan());
        if (!s.isEmpty()) {
            WorkFlowService.withdrawTask(s.get(0).getSystemAttributes().getTaskId());
        }
    }
}
