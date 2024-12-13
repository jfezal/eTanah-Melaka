/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.b1;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.SenaraiTugasanAction;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author john
 */
@UrlBinding("/common/b1/agihan_tugas")

public class AgihanTugasSediaB1ActionBean extends AbleActionBean {
    
    @Inject
    private etanah.Configuration conf;
    @Inject
    PelupusanService perlupusanService;
    @Inject
    PembangunanService pembangunanService;
    private List<Pengguna> listPp;
    @Inject
    PenggunaDAO penggunaDao;
    Pengguna pengguna;
    private Pengguna pguna;
    @Inject
    PermohonanDAO mohonDao;
    private Permohonan mohon;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    Logger logger = Logger.getLogger(AgihanTugasSediaB1ActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;
    IWorkflowContext ctx = null;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    
    @DefaultHandler
    public Resolution agihtugasanSO() {
        String msg = getContext().getRequest().getParameter("message");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        List<String> kumpBpel = new ArrayList();
        kumpBpel.add("pptdbangun");
        this.getListPguna(kumpBpel, pguna.getKodCawangan().getKod());
        
        return new ForwardResolution("/WEB-INF/jsp/common/b1/agih_tugasan.jsp").addParameter("tab", "true");
    }
    
    public Resolution agihPT() throws WorkflowException {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idPermohonan);
        String kodUrusan = mohon.getKodUrusan().getKod();
        String kodNegeri = conf.getProperty("kodNegeri");
        
        String stageID = getContext().getRequest().getParameter("stageId");
        
        FasaPermohonan fasaMohon = notisPenerimaanService.getFasaPermohonan(idPermohonan, stageID);
        if (fasaMohon != null) {
            InfoAudit ia = new InfoAudit();
            ia = fasaMohon.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pguna);
            fasaMohon.setInfoAudit(ia);
            fasaMohon.setTarikhHantar(new Date());
            fasaPermohonanManager.saveOrUpdate(fasaMohon);
        }
        
        StringBuilder sb = new StringBuilder();
        try {
            
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(idPermohonan);
            logger.info("idPermohonan ::" + idPermohonan);
            pengguna = penggunaDao.findById(pengguna.getIdPengguna());
            syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            if (sBMSIntegrationFlowService.initiateTerimaPelanB1(mohon)) {
                Thread.sleep(5000);
                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
//                Task l = WorkFlowService.getTask(taskId, ctx);
                for (Task t : l) {
                    String stageId = null;
                    
                    stageId = t.getSystemAttributes().getStage();
                    
                    if (stageId.equals("g_terima_pa_b1")) {
                        Task task = l.get(0);
                        WorkFlowService.acquireTask(task.getSystemAttributes().getTaskId(), ctx);
                        
                        Permohonan permohonan = mohonDao.findById(idPermohonan);
                        
                        List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                        FasaPermohonan _fp = null;
                        InfoAudit au = new InfoAudit();
                        
                        for (FasaPermohonan fp : senaraiFasa) {
                            if (fp.getIdAliran().equals(stageId)
                                    && fp.getIdPengguna().equals(pengguna.getIdPengguna())) {
                                _fp = fp;
                                break;
                            }
                        }
                        
                        if (_fp != null) {
                            au = _fp.getInfoAudit();
                            au.setTarikhKemaskini(new Date());
                            au.setDiKemaskiniOleh(pguna);
                            _fp.setInfoAudit(au);
                        } else {
                            _fp = new FasaPermohonan();
                            au.setDimasukOleh(pguna);
                            au.setTarikhMasuk(new Date());
                            _fp.setInfoAudit(au);
                            _fp.setPermohonan(permohonan);
                            _fp.setCawangan(pengguna.getKodCawangan());
                            _fp.setIdAliran(stageId);
                            _fp.setIdPengguna(pengguna.getIdPengguna());
                        }
                        _fp.setTarikhHantar(new Date());
                        fasaPermohonanManager.saveOrUpdate(_fp);
                        break;
                    }
                }
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanAction.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        
        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanAction.class).addParameter("message", msg);
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idPermohonan);
    }
    
    public List<Pengguna> getListPp() {
        return listPp;
    }
    
    private void getListPguna(List kumpBpel, String kodCaw) {
        listPp = perlupusanService.findPenggunaByBPELAgihanSemulaSBMS("pptdbangun", kodCaw);
    }
    
    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }
    
    public Pengguna getPguna() {
        return pguna;
    }
    
    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }
    
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
    public Permohonan getMohon() {
        return mohon;
    }
    
    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }
    
}
