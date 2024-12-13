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
import etanah.dao.KodHakmilikDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.Integrasi;
import etanah.model.IntegrasiDokumen;
import etanah.model.NoPt;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.JupemService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
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
 * @author Rohan
 */
@UrlBinding("/pelupusan/PUNew")
public class PUNewActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PUNewActionBean.class);
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService  pelupusanService;
    @Inject
    JupemService jupemService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    @Inject
    PermohonanTuntutanDAO mohonTuntutanDAO ;
    private PermohonanUkur permohonanUkur;
    private char statusSHS;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String kodNegeri;
    private String  stageId;
    private String idPermohonan;
    private List<NoPt> noPTList;
    private PermohonanTuntutanKos mohonTuntutKos ;
    private List<PermohonanTuntutanKos> mohonTuntutKosList ;
    private Boolean edit1;
    private Boolean edit2;
    private Boolean edit3;
    private Boolean edit4;
    private Boolean edit5;
    private Boolean edit6; //FOR SEMAK PU in N9
    private Boolean edit7; //For Urusan Navin
    private Boolean hantarDMS;
    private Boolean view;
    private String taskId;
    private String kodHakmilik;
    private String noResit ;
    private BigDecimal amaun ;
    private Date tarikhResit ;
    private PermohonanTuntutanBayar mohonTuntutanBayar ;
    private HakmilikPermohonan hakmilikPermohonan ;
     private List<HakmilikPermohonan> hakmilikPermohonanList ;


    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }


    @DefaultHandler
    public Resolution showForm() {
        edit1 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.FALSE;
        edit4 = Boolean.TRUE;
        edit5 = Boolean.FALSE;
        view = Boolean.FALSE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.FALSE;
        view = Boolean.TRUE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.FALSE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.FALSE;
        view = Boolean.TRUE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }

       public Resolution showForm4() {
        edit1 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.TRUE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.FALSE;
        view = Boolean.FALSE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }
        public Resolution showFormSimpan() {
        edit1 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.FALSE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.FALSE;
        edit7 = Boolean.TRUE;
        view = Boolean.FALSE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }
        public Resolution showForm5() {
        edit1 = Boolean.TRUE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.FALSE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.TRUE;
        view = Boolean.FALSE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }
        public Resolution showSemakPU() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.FALSE;
        edit3 = Boolean.FALSE;
        edit4 = Boolean.FALSE;
        edit5 = Boolean.FALSE;
        edit6 = Boolean.TRUE;
        view = Boolean.FALSE;
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
    }
//        public Resolution hantarForm() {
//        edit1 = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        edit4 = Boolean.FALSE;
//        edit5 = Boolean.FALSE;
//        view = Boolean.TRUE;
//        hantarDMS = Boolean.TRUE;
//        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true");
//        }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);

        System.out.println("idPermohonan::"+idPermohonan);
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "Melaka";
        }

        if(idPermohonan!= null){
            mohonTuntutKosList = pelupusanService.findTuntutByIdMohon(idPermohonan) ;
            if(!mohonTuntutKosList.isEmpty()){
                for(int i = 0 ; i < mohonTuntutKosList.size() ; i++){
                    if(mohonTuntutKosList.get(i).getKodTuntut().getKod().equals("DISUKUR")){
                        Long id = mohonTuntutKosList.get(i).getIdKos() ;
                        mohonTuntutanBayar = pelupusanService.findMohonTuntutanBayarByIdTuntutKos(id) ;
                        if(mohonTuntutanBayar != null){
                            noResit = mohonTuntutanBayar.getDokumenKewangan().getIdDokumenKewangan() ;
                            tarikhResit = mohonTuntutanBayar.getTarikhBayar() ;
                            amaun = mohonTuntutanBayar.getAmaun() ;
                        }

                    }
                }
            }
            permohonanUkur = pelupusanService.findPermohonanUkurByIdPermohonan(idPermohonan);
            if(permohonanUkur !=null)
                statusSHS = permohonanUkur.getStatusSuratanHakmilikSementara();
            else
                statusSHS = 'T';
            if(permohonanUkur!=null && permohonanUkur.getKodHakmilik()!=null){
                kodHakmilik=permohonanUkur.getKodHakmilik().getKod();
            }
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik() ;
            if(hakmilikPermohonanList != null){
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
            }
        }
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        String cara = (String) getContext().getRequest().getParameter("cara");
        LOG.info("cara :" + cara);
        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----"+idMohonUkur);
        if(idMohonUkur != null && !idMohonUkur.equals("")){
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }else{
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            NumberFormat df = new DecimalFormat("0000");
            Date dt= new Date();
            PermohonanUkur maxPuPermohonanUkur = pelupusanService.getMaxNoPUofPermohonanUkur(Integer.toString(1900+dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------"+maxPuPermohonanUkur);

            if(maxPuPermohonanUkur == null){
                   int val=1;
                   String noPU ="";
                   noPU = df.format(val)+"/"+(1900+dt.getYear());

                   System.out.println("-----------Seq-------------"+noPU);
                   permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else{
                   String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                   System.out.println("-----------maxNoPU-------------"+maxNoPU);
                   if(Integer.parseInt(maxNoPU.substring(5,9)) == (1900+dt.getYear())){
                       String subNoPU1 = maxNoPU.substring(0,4);
//                     String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
//                     int val = Integer.parseInt(subNoPU2);
                       int val = Integer.parseInt(subNoPU1);
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

//            permohonanUkurTemp.setNoRujukanPejabatTanah(permohonanUkur.getNoRujukanPejabatTanah());
            permohonanUkurTemp.setNoRujukanPejabatTanah(idPermohonan);
//            permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
//            //permohonanUkurTemp.setNoPermohonanUkur(permohonanUkur.getNoPermohonanUkur());
            if(permohonanUkur.getPerincianBorang5b() != null)
            permohonanUkurTemp.setPerincianBorang5b(permohonanUkur.getPerincianBorang5b());
            if(permohonanUkur.getPerincianBorang5c()!= null)
            permohonanUkurTemp.setPerincianBorang5c(permohonanUkur.getPerincianBorang5c());
            if(permohonanUkur.getPerincianBorang5d() != null)
            permohonanUkurTemp.setPerincianBorang5d(permohonanUkur.getPerincianBorang5d());
            if(permohonanUkur.getPerincianBorang5e() != null)
            permohonanUkurTemp.setPerincianBorang5e(permohonanUkur.getPerincianBorang5e());
            if(permohonanUkur.getPerincianPajakanLombong() != null)
            permohonanUkurTemp.setPerincianPajakanLombong(permohonanUkur.getPerincianPajakanLombong());
            if(permohonanUkur.getPerincianStrata() != null)
            permohonanUkurTemp.setPerincianStrata(permohonanUkur.getPerincianStrata());
//            permohonanUkurTemp.setStatusSuratanHakmilik(permohonanUkur.getStatusSuratanHakmilik());
            if(permohonanUkur.getTujuan() != null)
                permohonanUkurTemp.setTujuan(permohonanUkur.getTujuan());
            if(cara != null) {
                Character c = cara.charAt(0);
                permohonanUkurTemp.setStatusBayaranUkur(c);
            }
            if(permohonanUkur == null)
                permohonanUkurTemp.setPemberiPengecualian(new String()); 
            else {
                permohonanUkurTemp.setPemberiPengecualian(permohonanUkur.getPemberiPengecualian());   
                if(permohonanUkur.getPerengganKTN() != null)
                permohonanUkurTemp.setPerengganKTN(permohonanUkur.getPerengganKTN());
               if(permohonanUkur.getRujukanKTN() != null)
                permohonanUkurTemp.setRujukanKTN(permohonanUkur.getRujukanKTN());
               if(permohonanUkur.getJumlahPengecualian() != null)
                permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
            }
           
           
           
            permohonanUkurTemp.setJumlahBayaranUkur(amaun);
            String resitS = "" + noResit ;
            permohonanUkurTemp.setNoResit(resitS);
            permohonanUkurTemp.setTarikhResit(tarikhResit);
            permohonanUkurTemp.setStatusSuratanHakmilikSementara(statusSHS);
            permohonanUkurTemp.setInfoAudit(infoAudit);
            permohonanUkurTemp.setPermohonan(permohonan);
            if(kodHakmilik !=null){
                permohonanUkurTemp.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
            }

            pelupusanService.simpanPermohonanUkur(permohonanUkurTemp);
            
            pelupusanService.simpanHakmilikPermohonan2(hakmilikPermohonan);

            if(idPermohonan!=null){
                permohonanUkur = pelupusanService.findPermohonanUkurByIdPermohonan(idPermohonan);
            }
            addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
           // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/PUNew.jsp").addParameter("tab", "true");
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

    //-------------------------------Transfer File To JUPEM-------------------------------------//
     public Resolution transferFile() {
        String msg = "";
        String error = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
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
        return new JSP("pelupusan/PUNew.jsp").addParameter("tab", "true").addParameter("error", error).addParameter("message", msg);
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

    public List<NoPt> getNoPTList() {
        return noPTList;
    }

    public void setNoPTList(List<NoPt> noPTList) {
        this.noPTList = noPTList;
    }

    public Boolean getEdit1() {
        return edit1;
    }

    public void setEdit1(Boolean edit1) {
        this.edit1 = edit1;
    }

    public Boolean getEdit2() {
        return edit2;
    }

    public void setEdit2(Boolean edit2) {
        this.edit2 = edit2;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public Boolean getEdit3() {
        return edit3;
    }

    public void setEdit3(Boolean edit3) {
        this.edit3 = edit3;
    }

    public Boolean getEdit4() {
        return edit4;
    }

    public void setEdit4(Boolean edit4) {
        this.edit4 = edit4;
    }

    public Boolean getEdit5() {
        return edit5;
    }

    public void setEdit5(Boolean edit5) {
        this.edit5 = edit5;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getHantarDMS() {
        return hantarDMS;
    }

    public void setHantarDMS(Boolean hantarDMS) {
        this.hantarDMS = hantarDMS;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public List<PermohonanTuntutanKos> getMohonTuntutKosList() {
        return mohonTuntutKosList;
    }

    public void setMohonTuntutKosList(List<PermohonanTuntutanKos> mohonTuntutKosList) {
        this.mohonTuntutKosList = mohonTuntutKosList;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public Date getTarikhResit() {
        return tarikhResit;
    }

    public void setTarikhResit(Date tarikhResit) {
        this.tarikhResit = tarikhResit;
    }

    public PermohonanTuntutanBayar getMohonTuntutanBayar() {
        return mohonTuntutanBayar;
    }

    public void setMohonTuntutanBayar(PermohonanTuntutanBayar mohonTuntutanBayar) {
        this.mohonTuntutanBayar = mohonTuntutanBayar;
    }

    public char getStatusSHS() {
        return statusSHS;
    }

    public void setStatusSHS(char statusSHS) {
        this.statusSHS = statusSHS;
    }

    public Boolean getEdit6() {
        return edit6;
    }

    public void setEdit6(Boolean edit6) {
        this.edit6 = edit6;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Boolean getEdit7() {
        return edit7;
    }

    public void setEdit7(Boolean edit7) {
        this.edit7 = edit7;
    }

    

    
}
