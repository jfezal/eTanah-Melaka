/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.JabatanConstants;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import etanah.service.RegService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.workflow.KernelActionBean;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/aquireActionBean")
public class AquireActionBean extends AbleActionBean {
    @Inject
    TabManager tabManager;
    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    PermohonanService permohonanService;
    @Inject
    private etanah.Configuration conf;
    @Inject 
    RegService regService;
    
    private Permohonan permohonan;
    String tarikhMasuk;
    String idPermohonan;
    String idHakmilik;
    private List<Map<String,String>> senaraiHakmilikTerlibat;

    private KodBandarPekanMukim kodMukim;

    private static String ACQUIRED = "ACQUIRED";
    
    private static String DELEGATED = "DELEGATED";
    
    
    static final Comparator<Map<String,String>> HAKMILIK_ORDER =
                                 new Comparator<Map<String,String>>() {
        @Override
        public int compare(Map<String,String> m1, Map<String,String> m2) {           

          String id1 = m1.get("idHakmilik");
          String id2 = m2.get("idHakmilik");
          
          return id1.compareTo(id2);
            
        }
    };

    @DefaultHandler
    public Resolution showForm() {
        if(permohonan == null)addSimpleError("Ralat : Permohonan tidak dikenali.");
        return new JSP("common/aquire.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String)context.getRequest().getParameter("idPermohonan");
        tarikhMasuk = (String)context.getRequest().getParameter("tarikhMasuk");
        if(idPermohonan != null){
            permohonan = mohonDAO.findById(idPermohonan);
            StringBuilder sb = new StringBuilder();
            if(permohonan != null){
              if(permohonan.getSenaraiHakmilik() != null) {
                  senaraiHakmilikTerlibat = new ArrayList<Map<String,String>>();                  
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        Map<String,String> map = new HashMap<String,String>();
                        if(hp == null || hp.getHakmilik() == null)continue;
                        Hakmilik h = hp.getHakmilik();
                        if (h == null) continue;
                        if(sb.length()>0)sb.append(",");
                        sb.append(h.getIdHakmilik());
                        map.put("idHakmilik",h.getIdHakmilik());
                        if (hp.getBandarPekanMukimBaru() != null){                            
                            sb.append(" ( ").append(hp.getBandarPekanMukimBaru().getNama()).append("),");
                            map.put("bpm", hp.getBandarPekanMukimBaru().getNama());
                        }
                        else if (h.getBandarPekanMukim() != null){
                            KodBandarPekanMukim bpm = regService
                                    .cariBPM(h.getBandarPekanMukim().getbandarPekanMukim(), h.getDaerah().getKod());
                            if (bpm != null) {
                                sb.append(" ( ").append(bpm.getNama()).append("),");
                                map.put("bpm", bpm.getNama());
                            }
                        }
                        senaraiHakmilikTerlibat.add(map);
                    }
                }
            }            
            idHakmilik = sb.toString();
            if (!senaraiHakmilikTerlibat.isEmpty()) {
                Collections.sort(senaraiHakmilikTerlibat, HAKMILIK_ORDER);
            }
        }
    }

    public Resolution terus() throws WorkflowException, StaleObjectException{
        
        HttpSession ses = context.getRequest().getSession();
        String taskId = (String) ses.getAttribute("taskId");
        Pengguna p = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(p.getIdPengguna());
        
        //utk senarai berangkai, acquired satu akan acquired semua
        if(StringUtils.isNotBlank(permohonan.getIdKumpulan())) {
            List<Permohonan> senaraiPermohonan = permohonanService.getPermohonanByIdKump(permohonan.getIdKumpulan());
            for (Permohonan permohonan : senaraiPermohonan) {
                Task task = null;
                idPermohonan = permohonan.getIdPermohonan();
                List senaraiTask = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
                if(senaraiTask.size() > 0) task = (Task)senaraiTask.get(0);

                if(task != null) {
                    System.out.println("acquired=" + task.getSystemAttributes().getSubstate());
                    if(task.getSystemAttributes().getSubstate() == null || ( !task.getSystemAttributes().getSubstate().equals(ACQUIRED)
                            && !task.getSystemAttributes().getSubstate().equals(DELEGATED)) ) {
                        taskId = task.getSystemAttributes().getTaskId();
                        WorkFlowService.acquireTask(taskId,ctx);
                        doCreateUpdateFasa(taskId, p, permohonan);
                        permohonanService.updateStsPermohonan(permohonan, "AK", p);
                    }                    
                }
            }
        } else {
            WorkFlowService.acquireTask(taskId,ctx);    
            doCreateUpdateFasa(taskId, p, permohonan);
            permohonanService.updateStsPermohonan(permohonan, "AK", p);
            
            
        }
        
        //permohonan dev by rajib -- flow pinda need to use "SS"
            if(permohonan.getKodUrusan().getJabatan()!=null){
               String jabatanTerlibat = permohonan.getKodUrusan().getJabatan().getKod();
                if (jabatanTerlibat.equals(JabatanConstants.PEMBANGUNAN) && conf.getProperty("kodNegeri").equals("04")) {
                    updateStatusPermohonanDevSS(taskId,p);
                } 
            }
            
        
        return new RedirectResolution(KernelActionBean.class).addParameter("idPermohonan",idPermohonan);
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
    
    private void updateStatusPermohonanDevSS(String taskId,Pengguna pengguna){
        BPelService service = new BPelService();        
        Task t = service.getTaskFromBPel(taskId, pengguna);
        String stageId = t.getSystemAttributes().getStage();
        if (stageId.equals("pindaanagihan") || stageId.equals("pindaanrencanajkbb") || stageId.equals("semakpindaan")) {
                permohonanService.updateStsPermohonan(permohonan, "SS", pengguna);
        }
    }

    public Resolution kembali(){
        return new RedirectResolution(SenaraiTugasanActionBean.class);
    }

    public String getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(String tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public KodBandarPekanMukim getKodMukim() {
        return kodMukim;
    }

    public void setKodMukim(KodBandarPekanMukim kodMukim) {
        this.kodMukim = kodMukim;
    }

    public List<Map<String,String>> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<Map<String,String>> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

}
