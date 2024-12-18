/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package etanah.view.stripes.pengambilan;

import etanah.view.stripes.pembangunan.*;
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
import etanah.service.PembangunanService;
//import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author Massita
 */
@UrlBinding("/pengambilan/puHakLalu")
public class PuHakLaluActionBean extends AbleActionBean {
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
    private Pengguna pengguna;
    private String kodNegeri;


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        System.out.println("idPermohonan::"+idPermohonan);
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "Negeri Sembilan";
        }

        if(idPermohonan!= null){
            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }

    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----"+idMohonUkur);
        if(idMohonUkur != null && !idMohonUkur.equals("")){
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        else{
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
//        }
            NumberFormat df = new DecimalFormat("0000");
            Date dt= new Date();
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
            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900+dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------"+maxPuPermohonanUkur);

            if(maxPuPermohonanUkur == null){
                   int val=1;
                   String noPU ="";
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
                   noPU = df.format(val)+"/"+(1900+dt.getYear());

                   System.out.println("-----------Seq-------------"+noPU);
                   permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else{
                   String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                   System.out.println("-----------maxNoPU-------------"+maxNoPU);
                   if(Integer.parseInt(maxNoPU.substring(5,9)) == (1900+dt.getYear())){
                       String subNoPU1 = maxNoPU.substring(0,4);
                       String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
                       int val = Integer.parseInt(subNoPU2);
                       val = val+1;

                       String noPU ="";

                       noPU = df.format(val)+"/"+(1900+dt.getYear());

                       System.out.println("-----------Seq-------------"+noPU);
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
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/pu.jsp").addParameter("tab", "true");
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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
}
