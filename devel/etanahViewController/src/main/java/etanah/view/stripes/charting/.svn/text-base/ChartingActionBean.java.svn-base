/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.charting;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author w.fairul
 */
@UrlBinding("/chart/charting")
public class ChartingActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(ChartingActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String jawatan;
    private Pengguna pguna;
    private Permohonan permohonan;
    private String stageId;
    @Inject
    PermohonanDAO permohonanDAO;
    private Pengguna peng;

    @DefaultHandler
    public Resolution showForm() throws WorkflowException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        pguna = pengguna;
        String a = pengguna.getJawatan();
        jawatan = a.replace(" ", "_");
          List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                 LOG.info(stageId);
            }
        return new ForwardResolution("/WEB-INF/jsp/charting/charting.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    /**
     * To be used in tab page
     * 
     * @return Resolution JSP
     * @author hairudin
     * @version 1.0 13 May 2010
     */
    public Resolution showFormTab() throws WorkflowException {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        pguna = pengguna;
        LOG.info(idPermohonan);
        String a = pengguna.getJawatan();
        jawatan = a.replace(" ", "_");
          List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                 LOG.info(stageId);
            }
        return new JSP("charting/charting2.jsp").addParameter("tab", "true");
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
