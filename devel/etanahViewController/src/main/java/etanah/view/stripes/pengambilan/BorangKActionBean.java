/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PerbicaraanService;
import etanah.service.JupemService;
import etanah.service.PengambilanService;
import etanah.service.daftar.PembetulanService;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.report.ReportUtil;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.LelongService;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pengambilan/borangK")
public class BorangKActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembetulanService pembetulanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    JupemService servicejupem;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private PerbicaraanService perbicaraanService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    LelongService lelongService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private String idPermohonan;
    private String idHakmilik;
    private String tarikhDok;
    private String KodDok;
    private String mesej;
    private String taskId;
    private String stageId;
    private Pengguna pengguna;
    private Boolean errorMsg = false;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran = new ArrayList<PerbicaraanKehadiran>();
    private List<KandunganFolder> permohonanDok = new ArrayList<KandunganFolder>();
    private List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
    private List<PermohonanRujukanLuar> mrlList;
    private String item;
    private String tarikhLulus;
    private List<String> idBicara = new ArrayList<String>();
    private List<HakmilikPermohonan> sebahagianList;
    private List<HakmilikPermohonan> keseluruhanList;
    private boolean keseluruhan=false;
    private boolean sebahagian=false;
    private static final Logger LOGGER = Logger.getLogger(BorangKActionBean.class);

   

    @DefaultHandler
    public Resolution showForm() {
        if(errorMsg)
        {
            addSimpleError(mesej);

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/BorangK.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        permohonanPengambilan=pengambilanService.findByIdMohon(idPermohonan);
        sebahagianList=hakmilikservice.findMHSebahagian(p.getIdPermohonan());
        keseluruhanList=hakmilikservice.findMHKeseluruhan(p.getIdPermohonan());
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        BPelService bpelService = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOGGER.info("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "41_2MaklumanEndorsan";
            LOGGER.info("-------------stageId: BPEL OFF ----" + stageId);
        }

       // System.out.println(kd.get(0).getKod());
        if (idPermohonan != null) {
            permohonanDok=p.getFolderDokumen().getSenaraiKandungan();
//            mrlList=service.findByidPermohonanList(idPermohonan);
            mrlList=service.findByidPermohonanListBorangK(idPermohonan);
            int countRL=mrlList.size();
            int counter=countRL-1;
            item=mrlList.get(counter).getItem();
            tarikhLulus=sdf.format(mrlList.get(counter).getTarikhLulus()).substring(0, 10);
            
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            for(HakmilikPermohonan hp:hakmilikPermohonanList)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hp.getId());

            }

            for(int i=0;i<hakmilikPermohonanList.size();i++)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonanList.get(i).getId());
            idBicara.add(String.valueOf(hakmilikPerbicaraan.getIdPerbicaraan()));

            }
            if(sebahagianList.size()!=0){
                System.out.println("sebahagian List"+sebahagianList.size());
                sebahagian=true;
            for(HakmilikPermohonan hp:sebahagianList)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hp.getId());

            }

            for(int i=0;i<sebahagianList.size();i++)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(sebahagianList.get(i).getId());
            idBicara.add(String.valueOf(hakmilikPerbicaraan.getIdPerbicaraan()));

            }
            }
            if(keseluruhanList.size()!=0){
                keseluruhan=true;
                System.out.println("keseluruhanList List"+keseluruhanList.size());
            for(HakmilikPermohonan hp:keseluruhanList)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hp.getId());

            }

            for(int i=0;i<keseluruhanList.size();i++)
            {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(keseluruhanList.get(i).getId());
            idBicara.add(String.valueOf(hakmilikPerbicaraan.getIdPerbicaraan()));

            }


            }
            for (int i = 0; i < permohonanDok.size(); i++)
            {
                KodDok = permohonanDok.get(i).getDokumen().getKodDokumen().getKod();
                if(KodDok.equals("RBY"))
                {
                    Date tarikh1 =permohonanDok.get(i).getDokumen().getInfoAudit().getTarikhMasuk();
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(tarikh1);
                    cal.add(Calendar.DATE,15);
                    System.out.println("tarikh1 "+tarikh1);
                    System.out.println("cal "+cal.getTime());
                    
                    tarikhDok = sdf.format(permohonanDok.get(i).getDokumen().getInfoAudit().getTarikhMasuk()).substring(0, 10);
                    Date current=new java.util.Date();

                    if(cal.getTime().before(current))
                    {
                        System.out.println("asdasasdas");
                        setErrorMsg(false);
                        
                    }
                    else
                    {
                        System.out.println("reterter");
                        setErrorMsg(true);
                        setMesej("Sila Jana Borang K setelah 15 hari Borang E dikeluarkan.");
                        
                    }
//                    break;
                }
//                    else
//                {
//                setErrorMsg(false);
//                }
            }

           
        }


        }


     public Resolution genReport() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code="";
        sebahagianList=hakmilikservice.findMHSebahagian(p.getIdPermohonan());
        keseluruhanList=hakmilikservice.findMHKeseluruhan(p.getIdPermohonan());


        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
//            gen = "ACQDocK_MLK.rdf";

             if(sebahagianList.size()>0){
            gen = "ACQDocK2_MLK.rdf";
            LOGGER.info("ACQDocK2_MLK.rdf");
            }
            if(keseluruhanList.size()>0){

            gen = "ACQDocK_MLK.rdf";
            LOGGER.info("ACQDocK_MLK.rdf");
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if(sebahagianList.size()>0){
            gen = "ACQDocK2_NS.rdf";
            }
            if(keseluruhanList.size()>0){
            gen = "ACQDocK_NS.rdf";
            }
            
        }
        //SEBAHAGIAN TANAH
         if(sebahagianList.size()>0){
            code = "8K";
            try {
                LOGGER.info("genReportFromXML");
                lelongService.reportGen(p, gen, code, pengguna);
                } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
            //KESELURUHAN TANAH
        }
        if(keseluruhanList.size()>0){
            code = "K";
            try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(p, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        }



        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

     public Resolution genReportK() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code="";
        sebahagianList=hakmilikservice.findMHSebahagian(p.getIdPermohonan());
        keseluruhanList=hakmilikservice.findMHKeseluruhan(p.getIdPermohonan());


        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
//            gen = "ACQDocK_MLK.rdf";

//             if(sebahagianList.size()>0){
//            gen = "ACQDocK2_MLK.rdf";
//            LOGGER.info("ACQDocK2_MLK.rdf");
//            }
//            if(keseluruhanList.size()>0){
            LOGGER.info("ACQDocK_MLK.rdf");
            gen = "ACQDocK_MLK.rdf";

//            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if(sebahagianList.size()>0){
            gen = "ACQDocK2_NS.rdf";
            }
            if(keseluruhanList.size()>0){
            gen = "ACQDocK_NS.rdf";
            }

        }
        //SEBAHAGIAN TANAH
//         if(sebahagianList.size()>0){
//            code = "8K";
//            try {
//                LOGGER.info("genReportFromXML");
//                lelongService.reportGen(p, gen, code, pengguna);
//                } catch (Exception ex) {
//            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//        }
//            //KESELURUHAN TANAH
//        }
//        if(keseluruhanList.size()>0){
            code = "K";
            try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(p, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
//        }



        LOGGER.info("genReportK :: finish");
        return new StreamingResolution("text/plain", msg);
    }

     public Resolution genReportS() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code="";
        sebahagianList=hakmilikservice.findMHSebahagian(p.getIdPermohonan());
        keseluruhanList=hakmilikservice.findMHKeseluruhan(p.getIdPermohonan());


        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
//            gen = "ACQDocK_MLK.rdf";

//             if(sebahagianList.size()>0){

            LOGGER.info("ACQDocK2_MLK.rdf");
            gen = "ACQDocK2_MLK.rdf";
//            }
//            if(keseluruhanList.size()>0){
//
//            gen = "ACQDocK_MLK.rdf";
//            LOGGER.info("ACQDocK_MLK.rdf");
//            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if(sebahagianList.size()>0){
            gen = "ACQDocK2_NS.rdf";
            }
            if(keseluruhanList.size()>0){
            gen = "ACQDocK_NS.rdf";
            }

        }
        //SEBAHAGIAN TANAH
//         if(sebahagianList.size()>0){
            code = "8K";
            try {
                LOGGER.info("genReportFromXML");
                lelongService.reportGen(p, gen, code, pengguna);
                } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
            //KESELURUHAN TANAH
//        }
//        if(keseluruhanList.size()>0){
//            code = "K";
//            try {
//            LOGGER.info("genReportFromXML");
//            lelongService.reportGen(p, gen, code, pengguna);
//        } catch (Exception ex) {
//            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//        }
//        }



        LOGGER.info("genReportS :: finish");
        return new StreamingResolution("text/plain", msg);
    }



     public Resolution janaDokumen() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("ACQDocA.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    

         public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }
     public String getKodDok() {
        return KodDok;
    }

    public void setKodDok(String KodDok) {
        this.KodDok = KodDok;
    }

    public String getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(String tarikhDok) {
        this.tarikhDok = tarikhDok;
    }
     public List<KandunganFolder> getPermohonanDok() {
        return permohonanDok;
    }

    public void setPermohonanDok(List<KandunganFolder> permohonanDok) {
        this.permohonanDok = permohonanDok;
    }
    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }


    public Boolean isErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Boolean errorMsg) {
        this.errorMsg = errorMsg;
    }


    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
     public boolean isKeseluruhan() {
        return keseluruhan;
    }

    public void setKeseluruhan(boolean keseluruhan) {
        this.keseluruhan = keseluruhan;
    }

    public boolean isSebahagian() {
        return sebahagian;
    }

    public void setSebahagian(boolean sebahagian) {
        this.sebahagian = sebahagian;
    }




    public List<HakmilikPermohonan> getKeseluruhanList() {
        return keseluruhanList;
    }

    public void setKeseluruhanList(List<HakmilikPermohonan> keseluruhanList) {
        this.keseluruhanList = keseluruhanList;
    }

    public List<HakmilikPermohonan> getSebahagianList() {
        return sebahagianList;
    }

    public void setSebahagianList(List<HakmilikPermohonan> sebahagianList) {
        this.sebahagianList = sebahagianList;
    }

    public List<String> getIdBicara() {
        return idBicara;
    }

    public void setIdBicara(List<String> idBicara) {
        this.idBicara = idBicara;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(String tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }

    public List<PermohonanRujukanLuar> getMrlList() {
        return mrlList;
    }

    public void setMrlList(List<PermohonanRujukanLuar> mrlList) {
        this.mrlList = mrlList;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

}
