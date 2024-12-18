/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.Configuration;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NageswaraRao
 */

@UrlBinding("/pembangunan/melaka/semakB1")
public class SemakB1ActionBean extends AbleActionBean {
      private static final Logger LOG = Logger.getLogger(etanah.view.stripes.pembangunan.SemakB1ActionBean.class);
      private String idPermohonan;
      private Pengguna pguna;
      private String stageId;
      private String taskId;

    @DefaultHandler
    public Resolution showForm() {
           getContext().getRequest().setAttribute("edit", Boolean.TRUE);
           return new JSP("pembangunan/melaka/semak_B1.jsp").addParameter("tab", "true");
      }
    
    // added incase of semak_charting_ft needs
    public Resolution showForm2() {
         getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
         return new JSP("pembangunan/melaka/semak_B1.jsp").addParameter("tab", "true");
         //stage = "";         
         //return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
     }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        LOG.info("----StageId-----:"+stageId);
     }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }


    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


}