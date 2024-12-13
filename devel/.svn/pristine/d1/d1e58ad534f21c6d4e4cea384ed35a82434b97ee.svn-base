/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.Configuration;
import etanah.dao.PermohonanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pelupusan/PU")
public class PUActionBean extends AbleActionBean {
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    private etanah.Configuration conf;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String  stageId;
    private String idPermohonan;
    private String kodNegeri;


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/PU.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna =  (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        System.out.println("idPermohonan::"+idPermohonan);
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "Melaka";
        }

        if(idPermohonan!= null){
            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }

    }

     public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public Resolution simpan() {
      
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();
        if(idMohonUkur != null && !idMohonUkur.equals("")){
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        else{
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            NumberFormat df = new DecimalFormat("0000");
            Date dt= new Date();
            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900+dt.getYear()));
            if(maxPuPermohonanUkur == null){
                   int val=1;
                   String noPU ="";
                   noPU = df.format(val)+"/"+(1900+dt.getYear());
                   permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else{
                   String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                   if(Integer.parseInt(maxNoPU.substring(5,9)) == (1900+dt.getYear())){
                       String subNoPU1 = maxNoPU.substring(0,4);
                       int val = Integer.parseInt(subNoPU1);
                       val = val+1;
                       String noPU ="";
                       noPU = df.format(val)+"/"+(1900+dt.getYear());
                       permohonanUkurTemp.setNoPermohonanUkur(noPU);
                   } else{
                       int value = 1;
                       String nopu ="";

                       nopu = df.format(value)+"/"+(1900+dt.getYear());
                       permohonanUkurTemp.setNoPermohonanUkur(nopu);
                   }
             }

         }

            permohonanUkurTemp.setNoRujukanPejabatTanah(permohonanUkur.getNoRujukanPejabatTanah());
            permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
            permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
            permohonanUkurTemp.setPerincianBorang5b(permohonanUkur.getPerincianBorang5b());
            permohonanUkurTemp.setPerincianBorang5c(permohonanUkur.getPerincianBorang5c());
            permohonanUkurTemp.setPerincianBorang5d(permohonanUkur.getPerincianBorang5d());
            permohonanUkurTemp.setPerincianBorang5e(permohonanUkur.getPerincianBorang5e());
            permohonanUkurTemp.setPerincianPajakanLombong(permohonanUkur.getPerincianPajakanLombong());
            permohonanUkurTemp.setPerincianStrata(permohonanUkur.getPerincianStrata());
            permohonanUkurTemp.setStatusSuratanHakmilik(permohonanUkur.getStatusSuratanHakmilik());
            permohonanUkurTemp.setPemberiPengecualian(permohonanUkur.getPemberiPengecualian());
            permohonanUkurTemp.setPerengganKTN(permohonanUkur.getPerengganKTN());
            permohonanUkurTemp.setRujukanKTN(permohonanUkur.getRujukanKTN());
            permohonanUkurTemp.setJumlahBayaranUkur(permohonanUkur.getJumlahBayaranUkur());
            permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
            permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
            permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
            permohonanUkurTemp.setInfoAudit(infoAudit);
            permohonanUkurTemp.setPermohonan(permohonan);

            devService.simpanPermohonanUkur(permohonanUkurTemp);

            if(idPermohonan!=null){
                permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            }
            addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/PU.jsp").addParameter("tab", "true");
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }



    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

}
