/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.KodUrusan;
import etanah.service.AduanService;
import etanah.service.EnforceService;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import net.sourceforge.stripes.action.HandlesEvent;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_seksyen")
public class MaklumatSeksyenActionBean extends AbleActionBean {

     private static final Logger log = Logger.getLogger(MaklumatSeksyenActionBean.class);

    @Inject
    EnforceService enforceService;
    @Inject
    AduanService aduanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanService permohonanService;
    private static boolean isDebug = log.isDebugEnabled();
    private List<KodUrusan> senaraiUrusan;
    private Permohonan permohonan;
    private KodUrusan kodUrusan;
     private String taskId;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/maklumat_seksyen.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            senaraiUrusan = aduanService.getSenaraiUrusan();
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    public Resolution batalPerserahan() {
        try {
            if (isDebug) {
                log.debug("taskId = " + taskId);
            }
            if (StringUtils.isNotBlank(taskId)) {
                WorkFlowService.withdrawTask(taskId);

//                perserahan.setStatus("TK");
//                InfoAudit ia = new InfoAudit();
//                ia = perserahan.getInfoAudit();
//                ia.setDiKemaskiniOleh(pengguna);
//                ia.setTarikhKemaskini(new Date());
                permohonanService.saveOrUpdate(permohonan);
                addSimpleMessage("Pembatalan Berjaya");

            }
        } catch (Exception ex) {
            log.error(ex);
            addSimpleError(ex.getMessage());
        }

        return new JSP("penguatkuasaan/maklumat_seksyen.jsp").addParameter("tab", "true");
    }
@HandlesEvent("save")
    public Resolution changeUrusan() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {

            permohonan.setRujukanUndang2(kodUrusan.getKod());
            InfoAudit iaPermohonan = new InfoAudit();
            iaPermohonan = permohonan.getInfoAudit();
            iaPermohonan.setTarikhKemaskini(new Date());
            iaPermohonan.setDiKemaskiniOleh(peng);

            permohonan.setInfoAudit(iaPermohonan);
            tx.commit();
            if (!permohonan.getKodUrusan().getKod().matches(permohonan.getRujukanUndang2())){
                log.debug("same code-"+permohonan.getRujukanUndang2()+","+permohonan.getKodUrusan().getKod());
                String idPermohonan=permohonan.getIdPermohonan();
             List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
                if (senaraiTask.isEmpty()) {
                    addSimpleError("Permohonan tidak di jumpai");
                } else {
                    Task task = (Task) senaraiTask.get(0);
                    if (task != null) {
                        taskId = task.getSystemAttributes().getTaskId();
                        permohonan = permohonanService.findById(idPermohonan);
                    }
                }
                try {
            if (isDebug) {
                log.debug("taskId = " + taskId);
            }
            if (StringUtils.isNotBlank(taskId)) {
                WorkFlowService.withdrawTask(taskId);

//                perserahan.setStatus("TK");
//                InfoAudit ia = new InfoAudit();
//                ia = perserahan.getInfoAudit();
//                ia.setDiKemaskiniOleh(pengguna);
//                ia.setTarikhKemaskini(new Date());
                permohonanService.saveOrUpdate(permohonan);
                addSimpleMessage("Pembatalan Berjaya");

            }
        } catch (Exception ex) {
            log.error(ex);
            addSimpleError(ex.getMessage());
        }
//
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            log.error(t);
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
        }
        return new JSP("penguatkuasaan/maklumat_seksyen.jsp").addParameter("tab", "true");
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    
}
