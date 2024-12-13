/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.daftar.utiliti.WithdrawTaskActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.penguatkuasaan.MahkamahActionBean;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MohammadHafifi
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/utiliti_tutup_kertas_sisatan")
public class UtilitiTutupKertasSiasatan extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MahkamahActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private String ulasan;
    private String taskId;
    private String stageId;

    @DefaultHandler
    public Resolution findKertasSiasatan() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_tutup_kertas_siasatan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Resolution searchNoSerahan() throws WorkflowException {
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);

            //if (l.isEmpty()) {
                //addSimpleError("Id Permohonan " + idPermohonan + " tidak dijumpai.");
                //permohonan = null;
            //} else {
                addSimpleMessage("Id Permohonan " + idPermohonan + " dijumpai.");
            //}
        } else {
            addSimpleError("Id Permohonan " + idPermohonan + " tidak dijumpai.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_tutup_kertas_siasatan.jsp");
    }

    public Resolution simpanTutupKes() throws WorkflowException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            tx.begin();
            String error = "";
            String msg = "";

            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);

                List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                if (l != null) {
                    for (Task t : l) {
                        taskId = t.getSystemAttributes().getTaskId();
                        stageId = t.getSystemAttributes().getStage();
                    }
                }

                permohonan.setUlasan(ulasan);
                permohonan.setStatus("SL");
                permohonan.setInfoAudit(ia);
                permohonanDAO.saveOrUpdate(permohonan);

                PermohonanNota permohonanNota = new PermohonanNota();
                ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanNota.setInfoAudit(ia);
                permohonanNota.setPermohonan(permohonan);
                permohonanNota.setInfoAudit(ia);
                permohonanNota.setCawangan(pengguna.getKodCawangan());
                permohonanNota.setNota(ulasan);
                permohonanNota.setIdAliran(stageId);
                permohonanNota.setStatusNota('T');
                permohonanNotaDAO.saveOrUpdate(permohonanNota);

                WorkFlowService.withdrawTask(taskId);
                addSimpleMessage("Maklumat id permohonan telah berjaya di kemaskini dan kertas siasatan telah berjaya ditutup.");
            }

            tx.commit();
        } catch (StaleObjectException ex) {
            tx.rollback();
            ex.printStackTrace();
            addSimpleError("Maklumat Id Permohonan " + idPermohonan + " tidak berjaya di kemaskini.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_tutup_kertas_siasatan.jsp");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}
