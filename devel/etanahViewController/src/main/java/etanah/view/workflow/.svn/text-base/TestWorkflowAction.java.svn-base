/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.workflow;

import able.stripes.AbleActionBean;
import etanah.workflow.WorkFlowService;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fikri
 */
@UrlBinding("/utility/workflow/{idPermohonan}")
public class TestWorkflowAction extends AbleActionBean{
    
    private String idPermohonan;
    
    private String result;
    
    private String taskId;
    
    private String idPengguna;
    
    private static Logger LOG = LoggerFactory.getLogger(TestWorkflowAction.class);

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    @DefaultHandler
    public Resolution queryTasksByAdmin() {
        String result = "0";
        LOG.debug("id Permohonan :{}", idPermohonan);
        
        if (StringUtils.isNotBlank(idPermohonan)) {
            
            try{
                List<Task> senarai = WorkFlowService.queryTasksByAdmin(idPermohonan);
                if (!senarai.isEmpty()) {
                    result = String.valueOf(senarai.size());
                }
            } catch (Exception ex) {
                LOG.error("ex {}", ex);
            }
        } else {
            result = "USAGE = ../workflow/{idmohon}";
        }        
        return new StreamingResolution("text/plain", result);
    }
    
    
    public Resolution updateOutcome() {
        
        LOG.debug("result {}", result);
        try {
            
            idPengguna = getContext().getRequest().getParameter("idPengguna");
//            taskId = getContext().getRequest().getParameter("taskId");
            result = getContext().getRequest().getParameter("result");
            
            List<Task> senarai = WorkFlowService.queryTasksByAdmin(idPermohonan);
            if (!senarai.isEmpty()) {
                LOG.debug("senarai list {}", senarai.size());
                Task task = senarai.get(0);
                if (task != null) {
                    taskId = task.getSystemAttributes().getTaskId();
                    
                    LOG.debug("task id {}", taskId);
                    
                    IWorkflowContext ctx = WorkFlowService.authenticate(idPengguna);
                    
                    LOG.debug("updateTaskOutcome..");
        
                    WorkFlowService.updateTaskOutcome(taskId,
                                            ctx, result);
                    
                    LOG.debug("queryTasksByAdmin..");
                    
                    senarai = WorkFlowService.queryTasksByAdmin(idPermohonan);
                    
                    result = String.valueOf(senarai.size());
                    
                }
            }
            
        } catch (WorkflowException ex) {
            LOG.error("err {}", ex);
            result = ex.getMessage();
        } catch (StaleObjectException ex) {
            LOG.error("err {}", ex);
            result = ex.getMessage();
        } 
        
        return new StreamingResolution("text/plain", result);
        
    }
    
    
}
