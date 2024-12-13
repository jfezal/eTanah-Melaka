/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.Configuration;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
@UrlBinding("/pembangunan/melaka/penyediaanB2")
public class PenyediaanB2ActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PenyediaanB2ActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String stageId;
    private String idPermohonan;
    private String b2;
    private String penyediaanB2;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/penyediaan_B2.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("-------rehydrate----PenyediaanB2ActionBean-----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);

        penyediaanB2 = "g_penyediaan_b2";

        List<NoPt> noPtList = new ArrayList<NoPt>();
        noPtList = devService.senaraiNoPTByIdPermohonan(permohonan.getIdPermohonan());
        Long noPTSementara = 0L;

        if (!noPtList.isEmpty() && noPtList != null) {
            NoPt noPt = noPtList.get(0);
            noPTSementara = noPt.getNoPtSementara();
            if (noPTSementara == null || noPTSementara == 0) {
                System.out.println("Inside Null or 0");
                noPTSementara = 0L;
            }
            System.out.println("ol :"+0L);
        }
        System.out.println("-->noPTSementara : "+noPTSementara);
        if (noPTSementara == 1000 || noPTSementara == 0) {
            b2 = "Y";
        } else {
            b2 = "T";
        }
    }

    public String stageId(String taskId) {
        LOG.info("StageId ");
        BPelService service = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        LOG.debug("stageId:" + stageId);
        return stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getPenyediaanB2() {
        return penyediaanB2;
    }

    public void setPenyediaanB2(String penyediaanB2) {
        this.penyediaanB2 = penyediaanB2;
    }
}
