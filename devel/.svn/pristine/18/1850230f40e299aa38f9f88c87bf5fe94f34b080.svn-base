/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package etanah.view.stripes.pengambilan;

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
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.Integrasi;
import etanah.model.IntegrasiDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.NoPt;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.JupemService;
import etanah.service.PembangunanService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import etanah.view.utility.JupemInIntegration;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pengambilan/pu")
public class PuActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PuActionBean.class);
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    JupemService jupemService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String kodNegeri;
    private String  stageId;
    private String idPermohonan;
    private List<NoPt> noPTList;
    private Boolean edit1;
    private Boolean edit2;
    private Boolean view;
    private String taskId;
    private String kodHakmilik;
    private String sipuTundatangan;
    private String ditundatangan;
    private List<Pengguna> penggunaList;
    private Long noPTSementara;
    private Task task = null;
    private String workflowId;
    private String idFolder = "";
    private String kodUrusan;

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
        view = Boolean.FALSE;
        return new JSP("pengambilan/melaka/pu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.TRUE;
        view = Boolean.TRUE;
        return new JSP("pengambilan/melaka/pu.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
        edit1 = Boolean.FALSE;
        edit2 = Boolean.FALSE;
        view = Boolean.TRUE;
        return new JSP("pengambilan/melaka/pu.jsp").addParameter("tab", "true");
    }

  // add SIPU
    public Resolution showSIPU() {        
        return new JSP("pembangunan/melaka/sipu_tandatangan.jsp").addParameter("tab", "true");
    }
//    public Resolution showForm3() {
//        noPTList = new ArrayList<NoPt>();
//        noPTList = new ArrayList<NoPt>();
//        List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
//        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//        if(!hakmilikPermohonanList.isEmpty()){
//            HakmilikPermohonan hp = new HakmilikPermohonan();
//            hp = hakmilikPermohonanList.get(0);
//            int kodBPM = hp.getHakmilik().getBandarPekanMukim().getKod();
//            noPTList = pembangunanService.getNoPTByIdPermohonan(idPermohonan,kodBPM);
//        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
//        return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);

        System.out.println("idPermohonan::"+idPermohonan);
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "Melaka";
        }
        else if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "Negeri Sembilan";
        }

        if(idPermohonan!= null){
            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            if(permohonanUkur != null &&  permohonanUkur.getKodHakmilik()!= null){
                kodHakmilik=permohonanUkur.getKodHakmilik().getKod();
            }
            if(permohonanUkur == null){
                permohonanUkur = new PermohonanUkur();
            }
        }

         PermohonanTandatanganDokumen tandatanganDokumen=new PermohonanTandatanganDokumen();
         PermohonanTandatanganDokumen tandatanganDokumen2=new PermohonanTandatanganDokumen();
         tandatanganDokumen = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
         tandatanganDokumen2 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
         LOG.info("----------------tandatanganDokumen-------------------"+tandatanganDokumen);
         LOG.info("----------------tandatanganDokumen2-------------------"+tandatanganDokumen2);
          if(tandatanganDokumen!=null){
              ditundatangan= tandatanganDokumen.getDiTandatangan();
          }
          if(tandatanganDokumen2!=null){
              sipuTundatangan= tandatanganDokumen2.getDiTandatangan();
          }
          
          List<NoPt> noPtList=new ArrayList<NoPt>(); 
            noPtList = pembangunanService.senaraiNoPTByIdPermohonan(idPermohonan);
            if(!noPtList.isEmpty() && noPtList!=null){
                NoPt noPt=noPtList.get(0);
                noPTSementara = noPt.getNoPtSementara();
                if(noPTSementara == null)
                    noPTSementara = 0L;
            } 
            
           if (permohonan != null) {
            KodUrusan ku = permohonan.getKodUrusan();
            kodUrusan = ku.getKod();
            workflowId = ku.getIdWorkflow();
            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
          } 
            
     }     
//         List<PermohonanTandatanganDokumen> ttDokList = pembangunanService.findPermohonanDokTTByIdPermohonanrehy(idPermohonan);
//               LOG.info("----------------ttDokList-------------------"+ttDokList.size());
//               if(ttDokList.size()>0){
//                   tandatanganDokumen = ttDokList.get(0);
//                   ditundatangan= tandatanganDokumen.getDiTandatangan();
//                   LOG.info("----------------ditundatangan-------------------"+ditundatangan);
//               }
        
    

//        public Resolution executeCMD() throws IOException, Exception {
//        LOG.info("idPermohonan ........... ");
////        String peng = getContext().getRequest().getParameter("pengguna");
////        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
////        String idAliran = getContext().getRequest().getParameter("idAliran");
//        String  peng = "admin";
//        String  idPermohonan = "0405DEV2011007095";
//        String  idAliran ="agihancharting";
//
//        LOG.info("idPermohonan : " + idPermohonan);
//        LOG.info("idAliran : " + idAliran);
//        LOG.info("peng : " + peng);
////        Pengguna pguna = penggunaDAO.findById(peng);
//        if (peng != null && idPermohonan != null && idAliran != null) {
//            //jup.setIa(strataService.getInfo(peng));
//            InfoAudit ia = new InfoAudit();
//             ia.setTarikhMasuk(new Date());
//             ia.setDimasukOleh(pguna);
//          //  jup.setIa(jup.getInfoPenguna(peng));
//            jup.setIa(ia);
//            jup.setIdAliran(idAliran);
//            jup.setIdPermohonan(idPermohonan);
//            String msg = jup.inboundGIS();
//        }
//        return null;
//    }


    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----"+idMohonUkur);
        if(idMohonUkur != null && !idMohonUkur.equals("") && !idMohonUkur.equals("0")){
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }else{
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            NumberFormat df = new DecimalFormat("0000");
            Date dt= new Date();
            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900+dt.getYear()));
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
            permohonanUkurTemp.setTujuan(permohonanUkur.getTujuan());
            permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
            permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
            permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
            permohonanUkurTemp.setInfoAudit(infoAudit);
            permohonanUkurTemp.setPermohonan(permohonan);
            if(kodHakmilik !=null){
                permohonanUkurTemp.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
            }

            devService.simpanPermohonanUkur(permohonanUkurTemp);

            if(idPermohonan!=null){
                permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            }
            addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
            genReport();
           // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pu.jsp").addParameter("tab", "true");
    }
    
    
     public Resolution genReport() {
        LOG.info("genReport :: start");
        System.out.println("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum membawanya ke Permohonan Ukur.";
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
                LOG.info("genReportFromXML");
                genReportFromXml();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }
     
     private void genReportFromXml() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        //stageId = task.getSystemAttributes().getStage();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        LOG.info("----StageId------:"+stageId);
//        stageId = "cetakjkbbrekodkpsn";
        Dokumen d = null;
        
        if (workflowId != null && stageId != null) {
                String gen = "DEVBPU_MLK.rdf";
                String prefix = "VDOC";
                String code = "PU";
                String[] params = null;
                String[] values = null;
                KodDokumen kd = kodDokumenDAO.findById(code);
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                    params = new String[]{"p_id_mohon"};
                    values = new String[]{idPermohonan};
                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                    HakmilikPermohonan hakmilikPermohonan = hk.get(0);
                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    LOG.info("::Path To save :: " + (dokumenPath + path));
                    LOG.info("::Report Name ::" + gen);
                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                        hakmilikPermohonan.setDokumen1(d);
                    }
                    if (kd.getKod().equals("DHKE")) {
                        hakmilikPermohonan.setDokumen3(d);
                    }
                    if (kd.getKod().equals("DHDE")) {
                        hakmilikPermohonan.setDokumen2(d);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }

        if (workflowId != null && stageId != null) {
                String gen = "DEVSIPU_MLK.rdf";
                String prefix = "VDOC";
                String code = "SIPU";
                String[] params = null;
                String[] values = null;
                KodDokumen kd = kodDokumenDAO.findById(code);
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                    params = new String[]{"p_id_mohon"};
                    values = new String[]{idPermohonan};
                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                    HakmilikPermohonan hakmilikPermohonan = hk.get(0);
                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    LOG.info("::Path To save :: " + (dokumenPath + path));
                    LOG.info("::Report Name ::" + gen);
                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                        hakmilikPermohonan.setDokumen1(d);
                    }
                    if (kd.getKod().equals("DHKE")) {
                        hakmilikPermohonan.setDokumen3(d);
                    }
                    if (kd.getKod().equals("DHDE")) {
                        hakmilikPermohonan.setDokumen2(d);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }        
    }
 
       private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);   
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }
       
    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
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


     public String currentStageId(String taskId) {
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
        
         //not comment for checking
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info(" ---transferFile----idPermohonan : " + idPermohonan);
//        List<Dokumen> listDoc1 = jupemService.findDokumenlist("0505DEV2011150187", "g_penyediaan_pu_pt");
        List<Dokumen> listDoc1 = jupemService.findDokumenlist(idPermohonan, "g_penyediaan_pu");
        LOG.info(" -------Nama fizikal : " + listDoc1.size());
        for (Dokumen doc : listDoc1) {
         LOG.info(" -------Nama fizikal : " + doc.getNamaFizikal());
         LOG.info(" ------Id Dokumen : " + doc.getIdDokumen());
//         LOG.info(" ------Id Dokumen--------- : " +  doc.getNamaFizikal().lastIndexOf("/"));
        }

        String msg = "";
        String error = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String idAliran = currentStageId(taskId);
        LOG.info("idAliran :"+idAliran);
        LOG.info("PENGGUNA : " + peng.getIdPengguna());        
        LOG.info("TASK ID : " + taskId);

        // compare files
        //not comment for checking
        List<Dokumen> dokumenList=new ArrayList<Dokumen>();
        dokumenList = jupemService.findDokumenListByKodurusan(idPermohonan,permohonan.getKodUrusan().getKod());
        LOG.info("dokumenList :"+dokumenList.size());
            for(int i=0;i<dokumenList.size();i++){
                LOG.info("Kod Dokumen :"+dokumenList.get(i).getKodDokumen().getKod());
            }
        List<IntegrasiDokumen> integDokumenList = new ArrayList<IntegrasiDokumen>();
        LOG.info(this.permohonan.getKodUrusan().getKod());                
        //String idAliran = "g_hantar_pu";
        integDokumenList = jupemService.findIntegrasiDokumenList(permohonan.getKodUrusan().getKod(),idAliran);
        LOG.info("integDokumenList:"+integDokumenList.size());
            for(int i=0;i<integDokumenList.size();i++){
                LOG.info("Kod dokumen :"+integDokumenList.get(i).getKodDokumen().getKod());
            }

//        if(integDokumenList.size() != dokumenList.size()){
//            addSimpleError("Dokumen tiada dalam senarai, sila jana dokumen terlebih dahulu..");
//            return new JSP("pengambilan/melaka/pu.jsp").addParameter("tab", "true");
//        }

//        obi = new OutBoundIntegration(permohonan, peng, taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();

        addSimpleMessage("Fail telah berjaya dihantar");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/melaka/pu.jsp").addParameter("tab", "true").addParameter("error", error).addParameter("message", msg);
    }




     public Resolution simpanTandatangan() {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         InfoAudit ia = new InfoAudit() ;
         PermohonanTandatanganDokumen tandatanganDokumen1 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. "+ditundatangan);
        if(ditundatangan != null){
        if (tandatanganDokumen1 != null) {
            ia = tandatanganDokumen1.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
            tandatanganDokumen1.setPermohonan(permohonan);
            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("PU"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen1.setInfoAudit(ia);
        tandatanganDokumen1.setDiTandatangan(ditundatangan);
        pembangunanService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new RedirectResolution(PuActionBean.class, "showForm");
    }

       public Resolution simpanSIPU() {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         InfoAudit ia = new InfoAudit() ;
         PermohonanTandatanganDokumen tandatanganDokumen1 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
        sipuTundatangan = getContext().getRequest().getParameter("sipuTundatangan");
        LOG.info("testing.............. "+sipuTundatangan);
        if(sipuTundatangan != null){
        if (tandatanganDokumen1 != null) {
            ia = tandatanganDokumen1.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
            tandatanganDokumen1.setPermohonan(permohonan);
            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SIPU"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen1.setInfoAudit(ia);
        tandatanganDokumen1.setDiTandatangan(sipuTundatangan);
        pembangunanService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new JSP("pembangunan/melaka/sipu_tandatangan.jsp").addParameter("tab", "true");
    }




     public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            // Melaka
            String query = "";
            if("04".equals(conf.getProperty("kodNegeri"))){
                query = "Select u from etanah.model.Pengguna u where u.perananUtama in('71','75','77') and u.kodCawangan.kod = :kod order by u.nama";
            }
            // NS
            else if("05".equals(conf.getProperty("kodNegeri"))){
                 query = "Select u from etanah.model.Pengguna u where u.perananUtama in('PTD','PPTD','TPTD','PPTK') and u.kodCawangan.kod = :kod order by u.nama";
            }
            
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
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


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getSipuTundatangan() {
        return sipuTundatangan;
    }

    public void setSipuTundatangan(String sipuTundatangan) {
        this.sipuTundatangan = sipuTundatangan;
    }

    public Long getNoPTSementara() {
        return noPTSementara;
    }

    public void setNoPTSementara(Long noPTSementara) {
        this.noPTSementara = noPTSementara;
    }

}
