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
import etanah.model.Dokumen;
import etanah.model.Integrasi;
import etanah.model.IntegrasiDokumen;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.JupemService;
import etanah.service.PembangunanService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ctzainal
 */
@UrlBinding("/pelupusan/permohonan_ukur")
public class PermohonanUkurPRIZActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PermohonanUkurPRIZActionBean.class);
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    JupemService jupemService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private String tujuan;
    private boolean viewGISButton;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        viewGISButton = Boolean.FALSE;
        return new JSP("pelupusan/permohonan_ukur_priz.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        viewGISButton = Boolean.FALSE;
        return new JSP("pelupusan/permohonan_ukur_priz.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        viewGISButton = Boolean.TRUE;
//        return new JSP("penguatkuasaan/hantar_PU.jsp").addParameter("tab", "true");
        return new JSP("penguatkuasaan/permohonan_ukur_priz.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId(taskId);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        System.out.println("idPermohonan::" + idPermohonan);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Melaka";
        }

        if (idPermohonan != null) {
            permohonanUkur = enforceService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }

    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----" + idMohonUkur);
        if (idMohonUkur != null && !idMohonUkur.equals("")) {
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
//        }
            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
//            String kodCaw="";
//                if(permohonan.getCawangan().getKod().equals("01")){
//                    kodCaw ="MT";
//                }else if(permohonan.getCawangan().getKod().equals("02")){
//                    kodCaw ="JS";
//                }else if(permohonan.getCawangan().getKod().equals("03")){
//                    kodCaw ="AG";
//                }
//
//              System.out.println("----------kodCaw-----------"+kodCaw);
//
//            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(kodCaw);
            PermohonanUkur maxPuPermohonanUkur = enforceService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
//                        if(permohonan.getCawangan().getKod().equals("01")){
//                            noPU = (1900+dt.getYear())+"/MT/"+df.format(val);
//                        } else if(permohonan.getCawangan().getKod().equals("02")){
//                            noPU = (1900+dt.getYear())+"/JS/"+df.format(val);
//                        } else if(permohonan.getCawangan().getKod().equals("03")){
//                            noPU = (1900+dt.getYear())+"/AG/"+df.format(val);
//                        }
////                       testing
//                        else if(permohonan.getCawangan().getKod().endsWith("05")){
//                            noPU = (1900+dt.getYear())+"/T/"+df.format(val);
//                        }
                noPU = df.format(val) + "/" + (1900 + dt.getYear());

                System.out.println("-----------Seq-------------" + noPU);
                permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
//                     String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
//                     int val = Integer.parseInt(subNoPU2);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    permohonanUkurTemp.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    permohonanUkurTemp.setNoPermohonanUkur(nopu);
                }
            }

        }

        permohonanUkurTemp.setNoRujukanPejabatTanah(permohonanUkur.getNoRujukanPejabatTanah());
        permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
        //permohonanUkurTemp.setNoPermohonanUkur(permohonanUkur.getNoPermohonanUkur());
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
        permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
        permohonanUkurTemp.setJumlahBayaranUkur(permohonanUkur.getJumlahBayaranUkur());
        permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
        permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
        permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
        permohonanUkurTemp.setInfoAudit(infoAudit);
        permohonanUkurTemp.setPermohonan(permohonan);
        permohonanUkurTemp.setTujuan(permohonanUkur.getTujuan());
        enforceService.simpanPermohonanUkur(permohonanUkurTemp);

        if (idPermohonan != null) {
            permohonanUkur = enforceService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }
        addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_ukur_priz.jsp").addParameter("tab", "true");
    }

//    public Resolution janaNomborPU(){
//         NumberFormat df = new DecimalFormat("0000");
//         Date dt= new Date();
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if(idPermohonan!=null){
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//        }
//
//        String kodCaw="";
//                if(permohonan.getCawangan().getKod().equals("01")){
//                    kodCaw ="MT";
//                }else if(permohonan.getCawangan().getKod().equals("02")){
//                    kodCaw ="JS";
//                }else if(permohonan.getCawangan().getKod().equals("03")){
//                    kodCaw ="AG";
//                }
//
//
//        if((permohonanUkur!=null)&& (permohonanUkur.getNoPermohonanUkur()==null) ){
//                // get maximum Permohonan Ukur
//                PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur();
//                if(maxPuPermohonanUkur == null){
//                       int val=1;
//                      String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                      System.out.println("-----------Seq-------------"+noPU);
//                    permohonanUkur.setNoPermohonanUkur(noPU);
//                }else{
//                    String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
//                       String subNoPU1 = maxNoPU.substring(7);
//                       String subNoPU2 = maxNoPU.substring(subNoPU1.lastIndexOf("0"));
//                       int val = Integer.parseInt(subNoPU2);
//                       val = val+1;
//                       String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                       System.out.println("-----------Seq-------------"+noPU);
//                       permohonanUkur.setNoPermohonanUkur(noPU);
//                }
//        }
//
//
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//    }
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    //-------------------------------Transfer File To JUPEM-------------------------------------//
    public Resolution transferFile() {
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);


        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();

        addSimpleMessage("Fail telah berjaya dihantar");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        return new JSP("pelupusan/permohonan_ukur_priz.jsp").addParameter("tab", "true").addParameter("error", error).addParameter("message", msg);
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isViewGISButton() {
        return viewGISButton;
    }

    public void setViewGISButton(boolean viewGISButton) {
        this.viewGISButton = viewGISButton;
    }
    
    
}
