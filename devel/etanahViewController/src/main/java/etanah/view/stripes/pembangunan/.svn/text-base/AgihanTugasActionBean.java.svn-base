/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

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
//import etanah.Configuration;
import etanah.model.InfoAudit;
import etanah.service.BPelService;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.common.FasaPermohonanService;
import etanah.model.FasaPermohonan;
import etanah.service.PembangunanService;
import java.util.Date;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/agihan_tugas")
public class AgihanTugasActionBean extends AbleActionBean {

    @Inject
    PembangunanService pembangunanService;
    @Inject
    private etanah.Configuration conf;
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
    private List<Pengguna> listPp6;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String stage;
    private String kodNegeri;
    private String idPermohonan;
    private String stageId;
    private String stageIdForGis;
    
    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanService permohonanService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihanTugasActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    PembangunanService ps;

    @Inject
    public AgihanTugasActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

//    pelukispelan
//    @DefaultHandler
//    public Resolution showForm() {
//        String kodPeranan ="";
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//           kodPeranan = "72";  // Melaka
//        }else{
//           kodPeranan = "82";   // NS
//        }        
//        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
//        logger.info("------Kod Peranan---- : " + kodPeranan);
//
//        KodCawangan kod = pguna.getKodCawangan();
//        List<Pengguna> lp = penggunaDao.findAll();
//        listPp = new LinkedList<Pengguna>();
//
//        for (Pengguna p : lp) {
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null && p.getKodJabatan()!=null) {
//                    if (p.getPerananUtama().getKod().equals(kodPeranan) && p.getKodJabatan().getKod().equals("6")) {
//                        logger.info("------Kod jabatan--- : " + p.getKodJabatan().getKod());
//                        listPp.add(p);
//                        stage = "agihantugas1";
//                    }
//                }
//            }
//        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
//    }
    @DefaultHandler
    public Resolution showForm() {
        String kumpBpel = "";        
        kumpBpel = "ppelanbangun";  //melaka & n9
        String kumpBpel2 = "";        
        kumpBpel2 = "PPelan";  //melaka & n9
        
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kump Bpel---- : " + kumpBpel);
        
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        logger.info("-----kod.getKod()---- : " + kod.getKod());
        logger.info("------Kump Bpel For one---- : " + kumpBpel);
        //listPp = pembangunanService.findPenggunaForOnlyOneKumpBpel(kumpBpel, kod.getKod());
        listPp = pembangunanService.findPenggunaByTwoKumpBpel(kumpBpel, kumpBpel2, kod.getKod());
        logger.info("------listPp---- : " + listPp);
        stage = "agihantugas1";
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

    //    penolongpegawaitanah
    public Resolution showForm2() {
        String kumpBpel = "";
        kumpBpel = "pptdbangun";   //melaka & n9
        String kumpBpel2 = "";
        kumpBpel2 = "ppt";   //melaka & n9
        
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kump Bpel---- : " + kumpBpel);
        
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp2 = new LinkedList<Pengguna>();
        logger.info("-----kod.getKod()---- : " + kod.getKod());
        logger.info("------Kump Bpel For one---- : " + kumpBpel);
        listPp2 = pembangunanService.findPenggunaByTwoKumpBpel(kumpBpel, kumpBpel2, kod.getKod());
        logger.info("------listPp---- : " + listPp2);
        stage = "agihantugas2";

//        String kodPeranan ="";
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//           //kodPeranan = "73";  // Melaka
//        }else{
//           //kodPeranan = "83";   // NS
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);        
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }

//    penolongpentadbir/pembantutadbirtertinggi - Melaka
//    penolong/timbalan ptg & ptg - NS
    public Resolution showForm3() {
        //use showForm13

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp3 = new LinkedList<Pengguna>();
        String kodPeranan1 = "";
        String kodPeranan2 = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodPeranan1 = "71";  // Melaka
            kodPeranan2 = "223";
        } else {
            kodPeranan1 = "91";   // NS
            kodPeranan2 = "233";
        }
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kod Peranan1---- : " + kodPeranan1);
        logger.info("------Kod Peranan2---- : " + kodPeranan2);
        
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals(kodPeranan1)) {
                        listPp3.add(p);
                    }
                    if (p.getPerananUtama().getKod().equals(kodPeranan2)) {
                        listPp3.add(p);
                    }
                    if ("05".equals(conf.getProperty("kodNegeri"))) {  // for NS
                        if (p.getPerananUtama().getKod().equals("55")) {                            
                            listPp3.add(p);
                        }
                    }
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
        listPp5 = new LinkedList<Pengguna>();
        String kodPeranan = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodPeranan = "71";  // Melaka
        } else {
            kodPeranan = "70";   // NS
        }
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kod Peranan---- : " + kodPeranan);
        
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals(kodPeranan)) {
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
    
    public Resolution showForm10() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp6 = new LinkedList<Pengguna>();
        System.out.println("Kod Caw : " + kod.getKod());
        
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals("00")) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("237")) { //for NS
                        listPp6.add(p);
                        stage = "agihantugas6";
                    }
                    
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm12() {
        String kodPeranan = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodPeranan = "73";  // Melaka
        } else {
            kodPeranan = "83";   // NS
        }
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kod Peranan---- : " + kodPeranan);
        
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp2 = new LinkedList<Pengguna>();
        
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals(kodPeranan)) {                        
                        listPp2.add(p);
                        stage = "agihantugas2";
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);        
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
        String kodPeranan = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodPeranan = "73";  // Melaka
        } else {
            kodPeranan = "41";   // NS
        }
        
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals(kodPeranan)) {
                        listPp2.add(p);
                        stage = "agihantugas2";
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);        
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }
    //pelukispelanptg - Melaka

    public Resolution showForm9() {
        String kumpBpel = "ptgppelan";
        
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp5 = new LinkedList<Pengguna>();
        listPp5 = pembangunanService.findPenggunaForOnlyOneKumpBpel(kumpBpel, kod.getKod());
        stage = "agihantugas5";        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm11() {
        getContext().getRequest().setAttribute("edit4", Boolean.TRUE);
        //stage = "gis";
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm13() {
        String kumpBpel = "";
        String kumpBpel1 = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kumpBpel = "pptdkananbangun";
            kumpBpel1 = "pptd";
        }
        
        logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
        logger.info("------Kump Bpel---- : " + kumpBpel + kumpBpel1);
        
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp3 = new LinkedList<Pengguna>();
        logger.info("-----kod.getKod()---- : " + kod.getKod());
        logger.info("------Kump Bpel For one---- : " + kumpBpel + kumpBpel1);
        listPp3 = pembangunanService.findPenggunaByTwoKumpBpel(kumpBpel, kumpBpel1, kod.getKod());
        logger.info("------listPp3---- : " + listPp3);
        stage = "agihantugas3";
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/agihan_tugasan.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        System.out.println("IdPermohonan :" + idPermohonan);
        
        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);
        }

        //for gis Urusan PPK
        stageIdForGis = "";
        
    }
    
    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idMohon);
        
        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idMohon);
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                logger.info("-----Stage ID:" + stageId + "-----");
            }
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        
        StringBuilder sb = new StringBuilder();
        try {
            
            List<Map<String, String>> list = getPermohonanWithTaskID();
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                Task task = null;
                String stageID = null;
                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                stageID = task.getSystemAttributes().getStage();
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                
                logger.info("stage from task: " + stageId);
                FasaPermohonan fasaCurrent = ps.findFasaPermohonanByIdAliran(idPermohonan, stageId);
                if (fasaCurrent != null) {
                    fasaCurrent.setTarikhHantar(new Date());
                } else {
                                    InfoAudit au = new InfoAudit();
                    au.setDimasukOleh(pguna);
                    logger.debug("Pguna ~.~ " + pguna);
                    au.setTarikhMasuk(new Date());
                    fasaCurrent = new FasaPermohonan();
                    fasaCurrent.setIdAliran(stageId);
                    fasaCurrent.setTarikhHantar(new Date());
                    fasaCurrent.setInfoAudit(au);
                    fasaCurrent.setPermohonan(mohon);
                }
                ps.simpanFasaPermohonan(fasaCurrent);
                
                Permohonan permohonan = mohonDao.findById(idPermohonan);
                
                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();
                
                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equals(stageID)
                            && fp.getIdPengguna().equals(pengguna.getIdPengguna())) {
                        _fp = fp;
                        break;
                    }
                }
                
                logger.debug("cawangan " + pengguna.getKodCawangan());
                
                if (_fp != null) {
                    au = _fp.getInfoAudit();
                    au.setTarikhKemaskini(new Date());
                    au.setDiKemaskiniOleh(pguna);
                    logger.debug("Pguna ~ " + pguna);
                    _fp.setInfoAudit(au);
                    //_fp.setTarikhHantar(au.getTarikhKemaskini());
                } else {
                    _fp = new FasaPermohonan();
                    au.setDimasukOleh(pguna);
                    logger.debug("Pguna ~.~ " + pguna);
                    au.setTarikhMasuk(new Date());
                    _fp.setInfoAudit(au);
                    _fp.setPermohonan(mohon);
                    _fp.setCawangan(pengguna.getKodCawangan());
                    _fp.setIdAliran(stageID);
                    _fp.setIdPengguna(pengguna.getIdPengguna());
                    //_fp.setTarikhHantar(au.getTarikhMasuk());
                }
                //_fp.setTarikhHantar(new Date());
                //ps.simpanFasaPermohonan(_fp);
                fasaPermohonanManager.saveOrUpdate(_fp);

                //For pembangunan by rajib
                if (conf.getProperty("kodNegeri").equals("04")) {
                    logger.info("Next stage : " + stageID);
                    if (stageID == null) {
                        logger.debug("Ke seterusnya");
                    } else {
                        logger.debug("Ke seterusnya");
                        if (stageID.equals("pindaanagihan") || stageID.equals("pindaanrencanajkbb") || stageID.equals("semakpindaan")) {
                            permohonanService.updateStsPermohonan(permohonan, "SS", pguna);
                        }
                        
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " : Agihan tugasan kepada " + pengguna.getNama() + " " + "(" + pengguna.getIdPengguna() + ")" + " telah berjaya."); //altered by azwady.org 11/03/2014
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
    
    public List<Pengguna> getListPp5() {
        return listPp5;
    }
    
    public void setListPp5(List<Pengguna> listPp5) {
        this.listPp5 = listPp5;
    }
    
    public List<Pengguna> getListPp6() {
        return listPp6;
    }
    
    public void setListPp6(List<Pengguna> listPp6) {
        this.listPp6 = listPp6;
    }
    
    public String getStageIdForGis() {
        return stageIdForGis;
    }
    
    public void setStageIdForGis(String stageIdForGis) {
        this.stageIdForGis = stageIdForGis;
    }
    
}
