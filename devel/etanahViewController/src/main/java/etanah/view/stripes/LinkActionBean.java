/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.daftar.SenaraiKeutamaanActionBean;

import etanah.view.etanahActionBeanContext;
import etanah.view.workflow.KernelActionBean;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/linkActionBean")
public class LinkActionBean extends AbleActionBean {

    TabManager tabManager;
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    PermohonanService permohonanService;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private static String ACQUIRED = "ACQUIRED";
    private static String DELEGATED = "DELEGATED";

    @Inject
    public LinkActionBean(TabManager tabManager, PermohonanDAO permohonanDAO) {
        this.tabManager = tabManager;
        this.permohonanDAO = permohonanDAO;
    }

    @DefaultHandler
    public Resolution showForm() throws Exception {
        HttpSession ses = context.getRequest().getSession();
        String taskId = getContext().getRequest().getParameter("taskId");
        Pengguna p = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        ses.setAttribute("taskId", taskId);
        IWorkflowContext ctx = WorkFlowService.authenticate(p.getIdPengguna());

        //=======getStage========
        Task t = WorkFlowService.getTask(taskId, ctx);
        Date d = t.getSystemAttributes().getAssignedDate().getTime();
        String tarikhMasuk = sdf.format(d);
        String id = (String) context.getRequest().getParameter("idPermohonan");

        System.out.println("acquired by = " + t.getSystemAttributes().getSubstate());       

        Permohonan permohonan = permohonanDAO.findById(id);
        if (permohonan != null) {
            KodUrusan ku = permohonan.getKodUrusan();
            String jab = ku.getJabatanNama();
            if (jab.equalsIgnoreCase("pendaftaran")) {
                if (permohonan.getSenaraiHakmilik() != null
                        && permohonan.getSenaraiHakmilik().size() > 0
                        && StringUtils.isBlank(permohonan.getIdKumpulan())) {
                    return new RedirectResolution(SenaraiKeutamaanActionBean.class).addParameter("idPermohonan", id);
                } else {
//                    t.getSystemAttributes().getSubstate() == null || (!t.getSystemAttributes().getSubstate().equals(ACQUIRED)
//                            && !t.getSystemAttributes().getSubstate().equals(DELEGATED))
                    if ( t.getSystemAttributes().getAcquiredBy() == null) {
                        return new RedirectResolution(AquireActionBean.class).addParameter("idPermohonan", id).addParameter("tarikhMasuk", tarikhMasuk);
                    }
                }
            } else {
                if (t.getSystemAttributes().getAcquiredBy() == null) {
                    return new RedirectResolution(AquireActionBean.class).addParameter("idPermohonan", id).addParameter("tarikhMasuk", tarikhMasuk);
                }
            }
        }
//        if (t.getSystemAttributes().getAcquiredBy() == null) {
//            return new RedirectResolution(AquireActionBean.class).addParameter("idPermohonan", id).addParameter("tarikhMasuk", tarikhMasuk);
//        }
        return new RedirectResolution(KernelActionBean.class).addParameter("idPermohonan", id);
    }

    public Resolution autoAcquire() throws WorkflowException, StaleObjectException {        
        String id = (String) getContext().getRequest().getParameter("idPermohonan");
        String taskId = getContext().getRequest().getParameter("taskId");
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);        
        IWorkflowContext ctx = WorkFlowService.authenticate(p.getIdPengguna());
        Task t = WorkFlowService.getTask(taskId, ctx);
        getContext().getRequest().getSession().setAttribute("taskId", taskId);



        if (StringUtils.isNotBlank(id)) {
            Permohonan permohonan = permohonanDAO.findById(id);
            if (StringUtils.isNotBlank(permohonan.getIdKumpulan())) {
                List<Permohonan> senaraiPermohonan = permohonanService.getPermohonanByIdKump(permohonan.getIdKumpulan());
                for (Permohonan mhn : senaraiPermohonan) {
                    Task task = null;
                    String idPermohonan = mhn.getIdPermohonan();
                    List senaraiTask = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
                    if (senaraiTask.size() > 0) {
                        task = (Task) senaraiTask.get(0);
                    }

                    if (task != null) {
                        if (t.getSystemAttributes().getAcquiredBy() == null) {
                            taskId = task.getSystemAttributes().getTaskId();
                            WorkFlowService.acquireTask(taskId, ctx);
                            doCreateUpdateFasa(taskId, p, mhn);
                        }
                    }
                }
            } else {
                if (t.getSystemAttributes().getAcquiredBy() == null ) {
                    WorkFlowService.acquireTask(taskId,ctx);
                    doCreateUpdateFasa(taskId, p, permohonan);
                }
        }  
        }



//        if (t.getSystemAttributes().getAcquiredBy() == null) {
//
//            WorkFlowService.acquireTask(taskId, ctx);
//
//            InfoAudit au = new InfoAudit();
//            au.setTarikhMasuk(new Date());
//            au.setDimasukOleh(p);
//
//            String stageId = t.getSystemAttributes().getStage();
//            Permohonan permohonan = permohonanDAO.findById(id);
//
//            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
//            FasaPermohonan _fp = null;
//
//
//            for (FasaPermohonan fp : senaraiFasa) {
//                if (fp.getIdAliran().equals(stageId)
//                        && fp.getIdPengguna().equals(p.getIdPengguna())) {
//                    _fp = fp;
//                    break;
//                }
//            }
//
//            if (_fp != null) {
//                au = _fp.getInfoAudit();
//                au.setTarikhKemaskini(new Date());
//                au.setDiKemaskiniOleh(p);
//                _fp.setInfoAudit(au);
//            } else {
//                _fp = new FasaPermohonan();
//                _fp.setInfoAudit(au);
//                _fp.setPermohonan(permohonan);
//                _fp.setCawangan(p.getKodCawangan());
//                _fp.setIdAliran(stageId);
//                _fp.setIdPengguna(p.getIdPengguna());
//            }
//
//            fasaPermohonanManager.saveOrUpdate(_fp);
//        }
        return new RedirectResolution(KernelActionBean.class).addParameter("idPermohonan", id);
    }


    private void doCreateUpdateFasa(String taskId, Pengguna p, Permohonan mohon) {
        BPelService service = new BPelService();
        InfoAudit au = new InfoAudit();
        au.setTarikhMasuk(new Date());
        au.setDimasukOleh(p);
        Task t = service.getTaskFromBPel(taskId, p);
        String stageId = t.getSystemAttributes().getStage();
        List<FasaPermohonan> senaraiFasa = mohon.getSenaraiFasa();
        FasaPermohonan _fp = null;

        for (FasaPermohonan fp : senaraiFasa) {
            if(fp.getIdAliran().equals(stageId)
                    && fp.getIdPengguna().equals(p.getIdPengguna())) {
                _fp = fp;
                break;
            }
        }

        if(_fp != null) {
            au = _fp.getInfoAudit();
            au.setTarikhKemaskini(new Date());
            au.setDiKemaskiniOleh(p);
            _fp.setInfoAudit(au);
        } else {
            _fp = new FasaPermohonan();
            _fp.setInfoAudit(au);
            _fp.setPermohonan(mohon);
            _fp.setCawangan(p.getKodCawangan());
            _fp.setIdAliran(stageId);
            _fp.setIdPengguna(p.getIdPengguna());
        }

        fasaPermohonanManager.saveOrUpdate(_fp);
    }

}
