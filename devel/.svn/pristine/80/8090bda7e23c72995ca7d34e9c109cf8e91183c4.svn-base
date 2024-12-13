/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

import com.google.inject.Inject;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.manager.TabManager;
import java.util.HashMap;
import java.util.Map;
import etanah.workflow.WorkFlowService;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import java.util.ArrayList;

/**
 *
 * @author solahuddin
 */
public class TaskDebugService {
    private Map m;
    @Inject
    private TabManager tm;
    @Inject
    private PermohonanDAO pDAO;

    public Map traceTask(String idM) throws WorkflowException{
        m = new HashMap();
        List<Task> l = WorkFlowService.queryTasksByAdmin(idM);
        for(Task t : l){
            m.put("idPermohonan", t.getSystemMessageAttributes().getTextAttribute1());
            m.put("stage", t.getSystemAttributes().getStage());
            Permohonan p = pDAO.findById(idM);
            m.put("tindakan", tm.getCurrentAction(p.getKodUrusan().getIdWorkflow(),t.getSystemAttributes().getStage()));
            m.put("taskID", t.getSystemAttributes().getTaskId());
            m.put("taskNumber", t.getSystemAttributes().getTaskNumber());
                m.put("caw", t.getSystemMessageAttributes().getTextAttribute2());
            m.put("participant", t.getSystemAttributes().getParticipantName());
            m.put("acquiredBy", t.getSystemAttributes().getAcquiredBy());
            m.put("task_state", t.getSystemAttributes().getState());
            m.put("list_group", t.getSystemAttributes().getAssigneeGroups());
            m.put("creator", t.getCreator());
            m.put("workflowid", t.getTaskDefinitionId());
        }
        return m;
    }
    
    public List<Map> traceTaskList(String idM) throws WorkflowException{
        List<Task> l = WorkFlowService.queryTasksByAdmin(idM);
        List<Map>lis = new ArrayList<Map>();
        for(Task t : l){
                    m = new HashMap();
            m.put("idPermohonan", t.getSystemMessageAttributes().getTextAttribute1());
            m.put("stage", t.getSystemAttributes().getStage());
            
            Permohonan p = pDAO.findById(idM);
            m.put("tindakan", tm.getCurrentAction(p.getKodUrusan().getIdWorkflow(),t.getSystemAttributes().getStage()));
            m.put("taskID", t.getSystemAttributes().getTaskId());
            m.put("taskNumber", t.getSystemAttributes().getTaskNumber());
            m.put("participant", t.getSystemAttributes().getParticipantName());
            m.put("acquiredBy", t.getSystemAttributes().getAcquiredBy());
            m.put("task_state", t.getSystemAttributes().getState());
            m.put("creator", t.getCreator());

            lis.add(m);
        }
        return lis;
    }
}
