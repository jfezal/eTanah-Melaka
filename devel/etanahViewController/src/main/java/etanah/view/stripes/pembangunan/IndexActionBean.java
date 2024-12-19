/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanUkur;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


@UrlBinding("/pembangunan/melaka/index")
public class IndexActionBean extends AbleActionBean {

    @Inject
    PelupusanService plpservice ;
    @Inject
    PermohonanDAO permohonanDAO ;
    private static final Logger LOG = Logger.getLogger(IndexActionBean.class);
    private String idPermohonan ;
    private Permohonan permohonan ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private List<NoPt> noPtList ;
    private NoPt noPt ;
    private PermohonanLaporanPelan permohonanLaporanPelan ;
    private PermohonanUkur permohonanUkur ;
    private Permohonan permohonanSebelum ;
    private String stageId;
    private HakmilikPermohonan hakmilikPermohonanBaru ;
    private Hakmilik hakmilikBaru ;
    private Hakmilik hakmilik;
    private String noLot;
    private String noHakmilik;


    private Pengguna peng ;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/dev_pt_index.jsp").addParameter("tab", "true");
    }

     public Resolution showForm2() {
        return new JSP("pembangunan/melaka/dev_lot_index.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); ;
        if(idPermohonan != null){
                
        hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan) ;
        noLot = hakmilikPermohonan.getHakmilik().getNoLot();
        noHakmilik = hakmilikPermohonan.getHakmilik().getNoHakmilik();
        
        
        noPtList=new ArrayList<NoPt>();
        if(hakmilikPermohonan.getBandarPekanMukimBaru()!=null){
            noPtList = plpservice.getNoPTByIdPermohonan(idPermohonan, hakmilikPermohonan.getBandarPekanMukimBaru().getKod()) ;
        }
        permohonanLaporanPelan = plpservice.findByIdPermohonanPelan(idPermohonan) ;
        permohonanUkur = plpservice.findPermohonanUkurByIdPermohonan(idPermohonan) ;
            if(!noPtList.isEmpty()){
                noPt = noPtList.get(0) ;
            }
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                BPelService serviceBpel = new BPelService();
                if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                    t = serviceBpel.getTaskFromBPel(taskId, peng);
                    stageId = t.getSystemAttributes().getStage();
                }

    //               permohonanSebelum = plpservice.findPermohonanByIdPermohonanSebelum(idPermohonan) ;
                   String[] kod = {"HSBM" , "HKBM"} ;
                   for(int i = 0 ; i < 2 ; i++){
                       permohonanSebelum = plpservice.findPermohonanByIdPermohonanSebelum(idPermohonan, kod[i]) ;
                   }
                   if(permohonanSebelum != null){
                   hakmilikPermohonanBaru = plpservice.findByIdPermohonan(permohonanSebelum.getIdPermohonan()) ;
                   hakmilikBaru = hakmilikPermohonanBaru.getHakmilik() ;
                   }

        }


    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public List<NoPt> getNoPtList() {
        return noPtList;
    }

    public void setNoPtList(List<NoPt> noPtList) {
        this.noPtList = noPtList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public Hakmilik getHakmilikBaru() {
        return hakmilikBaru;
    }

    public void setHakmilikBaru(Hakmilik hakmilikBaru) {
        this.hakmilikBaru = hakmilikBaru;
    }

    public HakmilikPermohonan getHakmilikPermohonanBaru() {
        return hakmilikPermohonanBaru;
    }

    public void setHakmilikPermohonanBaru(HakmilikPermohonan hakmilikPermohonanBaru) {
        this.hakmilikPermohonanBaru = hakmilikPermohonanBaru;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }
}
