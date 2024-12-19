/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.service.common.TaskDebugService;
import java.util.List;
import java.util.Map;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/common/task_debug")
public class TaskDebug extends AbleActionBean {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TaskDebug.class);
    private String id_mohon;
    private String stage;
    private String tindakan;
    private String task_id;
    private String task_number;
    private String participant;
    private String acquired_by;
    private String task_state;
    private String creator;
    @Inject
    private TaskDebugService ts;
private List<Map>lis;
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        return new JSP("common/task_debug.jsp");
    }

    public Resolution trace() throws WorkflowException {
        Map m = ts.traceTask(id_mohon);
                 lis = ts.traceTaskList(id_mohon);

        stage = (String)m.get("stage");
        tindakan = (String)m.get("tindakan");
        task_id = (String)m.get("taskID");
        task_number = ""+m.get("taskNumber");
        participant = (String)m.get("participant");
        acquired_by = (String)m.get("acquiredBy");
        creator = (String) m.get("creator");

        return new ForwardResolution("/WEB-INF/jsp/common/task_debug.jsp");
    }

    public String getId_mohon() {
        return id_mohon;
    }

    public void setId_mohon(String id_mohon) {
        this.id_mohon = id_mohon;
    }

    public String getAcquired_by() {
        return acquired_by;
    }

    public void setAcquired_by(String acquired_by) {
        this.acquired_by = acquired_by;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_number() {
        return task_number;
    }

    public void setTask_number(String task_number) {
        this.task_number = task_number;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getTask_state() {
        return task_state;
    }

    public void setTask_state(String task_state) {
        this.task_state = task_state;
    }

    public List<Map> getLis() {
        return lis;
    }

    public void setLis(List<Map> lis) {
        this.lis = lis;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    
}
