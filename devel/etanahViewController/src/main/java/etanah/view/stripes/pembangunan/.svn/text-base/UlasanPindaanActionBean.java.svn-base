/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import etanah.dao.PermohonanDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.FasaPermohonan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/ulasanPindaan")
public class UlasanPindaanActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(UlasanPindaanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PembangunanService devServ;

    private Pengguna pengguna;
    private Permohonan permohonan;
    private FasaPermohonan fp;
    private String stageId;
    private String ulasan;
    private String taskId;
    private Task task = null;
    private BPelService service;


    
//    public Resolution showForm() {
//        LOG.info("show editable Form");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new JSP("pembangunan/pecahSempadan/dev_ulasan_pindaan.jsp").addParameter("tab", "true");
//    }
    @DefaultHandler
    public Resolution showForm() {
        LOG.info("display form");
        return new JSP("pembangunan/pecahSempadan/dev_ulasan_pindaan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        LOG.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        fp =  new FasaPermohonan();
        List<FasaPermohonan> fplist;
        fplist = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
        System.out.println("fplist: " + fplist.size());

        if (!(fplist.isEmpty())) {
           for (int i = 0; i < fplist.size(); i++) {
               FasaPermohonan fasapermohonan = new FasaPermohonan();
               fasapermohonan = fplist.get(i);

               taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
               if (StringUtils.isBlank(taskId)) {
               taskId = getContext().getRequest().getParameter("taskId");
               }
               task = service.getTaskFromBPel(taskId, pengguna);
               if (task != null) {
               stageId = task.getSystemAttributes().getStage();
               } else {
               stageId = getContext().getRequest().getParameter("stageId");
               }
               
               if(!permohonan.getKodUrusan().getKod().equals("PPK")){
               if(fasapermohonan.getIdAliran().equals("perakuanjkbbptg")) {
                   fp = fplist.get(i);
                   ulasan = fp.getUlasan();
               }
               else if(fasapermohonan.getIdAliran().equals("perakuanmmknptg")) {
                   fp = fplist.get(i);
                   ulasan = fp.getUlasan();
               }}
               
                      
               //PPK
               if(permohonan.getKodUrusan().getKod().equals("PPK")){                  
               if(stageId.equals("pindaPelan3") || stageId.equals("sediapelan3")){
               if(fasapermohonan.getIdAliran().equals("semakpelanringkasantptg")) {
                   fp = fplist.get(i);
                   ulasan = new String();
                   ulasan = fp.getUlasan();
               }}
               
               if(stageId.equals("pindaPelan2") || stageId.equals("sediapelan2")){
               if(fasapermohonan.getIdAliran().equals("perakuanptg")) {
                   fp = fplist.get(i);
                   ulasan = new String();
                   ulasan = fp.getUlasan();
               }}
               
               if(stageId.equals("pindaPelan") || stageId.equals("sediapelan")){
               if(fasapermohonan.getIdAliran().equals("semakpelanringkasan")) {
                   fp = fplist.get(i);
                   ulasan = new String();
                   ulasan = fp.getUlasan();
               }}}
               
           }
       }
       LOG.info("rehydrate finish");
    }

//    public Resolution simpan() throws Exception {
//        LOG.info("simpan start");
//        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//        }
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(pengguna);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        System.out.println("---------taskId-------"+taskId);
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//            System.out.println("-------------stageId--"+stageId);
//        }
//
//        //testing purpose
//        stageId = "ulasanpindaan";
//
//        if (fp != null) {
//            fp.setPermohonan(permohonan);
//            fp.setCawangan(permohonan.getCawangan());
//            fp.setInfoAudit(infoAudit);
//            fp.setIdPengguna(pengguna.getIdPengguna());
//            fp.setIdAliran(stageId);
//            devServ.simpanFasaPermohonan(fp);
//        }
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        LOG.info("simpan finish");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_ulasan_pindaan.jsp").addParameter("tab", "true");
//    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

}
