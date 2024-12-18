/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
//import etanah.Configuration;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
//import etanah.model.PenggunaPeranan;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.Configuration;
import etanah.model.InfoAudit;
import etanah.service.BPelService;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.RedirectResolution;



/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pengambilan/agihan_tugasB1")
public class agihan_tugasanB1 extends AbleActionBean{
    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    private Permohonan mohon;
    String urusan;
    private Pengguna pengguna;
    private Pengguna pguna;
//    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<Pengguna> listPp2;
    private List<Pengguna> listPp3;
    private List<Pengguna> listPp4;
    private List<Pengguna> listPp5;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String stage;
//    private etanah.Configuration conf;
    private String kodNegeri;
    private String idPermohonan;
    private String stageId;

    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanService permohonanService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(agihan_tugasanB1.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");

    @Inject
    public agihan_tugasanB1(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

//    pelukispelan
    @DefaultHandler
    public Resolution showForm() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("39")) { // for NS
//                    if (p.getPerananUtama().getKod().equals("31")) { //for Melaka
                      if (p.getPerananUtama().getKod().equals("82")) { //for Office Melaka
                        listPp.add(p);
                        stage = "agihantugas1";
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

//    penolongpegawaitanah
    public Resolution showForm2() {

       KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                        if (p.getPerananUtama().getKod().equals("24")) { //for Melaka
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan1.jsp").addParameter("tab", "true");
    }

//    penolongpentadbir/pembantutadbirtertinggi - Melaka
//    penolong/timbalan ptg & ptg - NS
     public Resolution showForm3() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp3 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//               if (p.getPerananUtama().getKod().equals("12")) {  //forNS
                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
//                    if (p.getPerananUtama().getKod().equals("81")) { //for office-Melaka
                        listPp3.add(p);
                    }
//                    if (p.getPerananUtama().getKod().equals("54")){ //for NS
                if (p.getPerananUtama().getKod().equals("223")){ //for Melaka
//                    if (p.getPerananUtama().getKod().equals("223")){ //for office-Melaka
                        listPp3.add(p);
                    }
//                    if (p.getPerananUtama().getKod().equals("55")){ //for NS
//                        listPp3.add(p);
//                    }
                    stage = "agihantugas3";
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

//     penolong pegawai tanah ptg
     public Resolution showForm4() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("48")) {  //forNS
//                      if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
                        listPp4.add(p);
                        stage = "agihantugas4";
                    }

                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

    //ptptg - Melaka
    public Resolution showForm5() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("45")) { //for Melaka
                        listPp5.add(p);
                        stage = "agihantugas5";
                    }

                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

    // Need to Show "Semak PA/B1" button
    public Resolution showForm6() {
         getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
         stage = "";
         return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
     }

    // Need to Show "Hantar PA/B1" button
     public Resolution showForm7() {
         getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
         stage = "";
         return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
     }

     // GIS Inbound Integration
   public Resolution getInboundGIS() throws IOException, Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idAliran = getContext().getRequest().getParameter("idAliran");
        InfoAudit info = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(peng);

        logger.info("------idPermohonan : " + idPermohonan);
        logger.info("------idAliran : " + idAliran);
        if (peng != null && idPermohonan != null && idAliran != null) {
            jup.setIa(info);
            jup.setIdAliran(idAliran);
            jup.setIdPermohonan(idPermohonan);
            String msg = jup.inboundGIS(true);
        }
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        stage = "";
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }



     // Need to Show "Terima & Agih PA/B1" button
     public Resolution showForm8() {
         KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp2 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("41")) { //for NS
//                        if (p.getPerananUtama().getKod().equals("73")) { //for Melaka
//                          if (p.getPerananUtama().getKod().equals("83")) { //for Office Melaka
                        listPp2.add(p);
                        stage = "agihantugas2";
                    }
                }
            }
        }
         getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
         return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
     }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);

        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        System.out.println("IdPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);
        }
    }

    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        StringBuilder sb = new StringBuilder();
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID();
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if(sb.length() > 0) sb.append(",");
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                syslog.info(pguna.getIdPengguna()+" agih tugasan untuk permohonan :"+idPermohonan+" kepada "+pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new RedirectResolution(SenaraiTugasanActionBean.class)
                    .addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SenaraiTugasanActionBean.class)
                .addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
    }

    private List<Map<String, String>> getPermohonanWithTaskID() throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        logger.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", mohon.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);

        return list;
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


    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<Pengguna> getListPp2() {
        return listPp2;
    }

    public void setListPp2(List<Pengguna> listPp2) {
        this.listPp2 = listPp2;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Pengguna> getListPp3() {
        return listPp3;
    }

    public void setListPp3(List<Pengguna> listPp3) {
        this.listPp3 = listPp3;
    }

//    public Configuration getConf() {
//        return conf;
//    }
//
//    public void setConf(Configuration conf) {
//        this.conf = conf;
//    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Pengguna> getListPp4() {
        return listPp4;
    }

    public void setListPp4(List<Pengguna> listPp4) {
        this.listPp4 = listPp4;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }




}
