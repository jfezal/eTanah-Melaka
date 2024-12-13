/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import etanah.model.Pengguna;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 *
 * @author md.nurfikri
 */
public class BPelService {
    private String errMsg = "";

    public Task getTaskFromBPel(String taskId, Pengguna p) {
        System.out.println("BPelService taskId :" + taskId);
        System.out.println("BPelService Pengguna :" + p.getIdPengguna());
        Task t = null;
        IWorkflowContext ctx = null;
        try {
            ctx = WorkFlowService.authenticate(p.getIdPengguna());
            t = WorkFlowService.getTask(taskId, ctx);
        } catch (Exception ex) {            
            setErrMsg(ex.getMessage());
            return null;
        }
        return t;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
