/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.model.PermohonanNota;
import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.PenggunaPeranan;
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
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.common.EnforcementService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanRujukanLuarService;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurshahida.radzi
 */
@UrlBinding("/penguatkuasaan/agih_tugasan")
public class AgihTugasanActionBean extends AbleActionBean {

    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    String IdPermohonan;
    private List<Pengguna> listPt;
    private List<Pengguna> listPp;
    private List<Pengguna> listPp1;
    private List<Pengguna> listPptd;
    private List<Pengguna> listPptk;
    private List<Pengguna> listIo;
    private List<Pengguna> listPptg;
    private List<Pengguna> listRo;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean isBerangkai = Boolean.FALSE;
    private boolean utama = Boolean.FALSE;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    LelongService lelongService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihTugasanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private boolean isBerhalang = Boolean.FALSE;
    private String kodNegeri;
    private String stageIDAgihTugas;
    private PermohonanNota permohonanNota;
    private boolean statusNotaExist = Boolean.TRUE;
    private String taskId;
    private String stageId;
    private Permohonan permohonan;

    @Inject
    public AgihTugasanActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDao.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();

        if (lhp.size() > 0) {
            Hakmilik h = lhp.get(0).getHakmilik();
//            isBerhalang = regService.periksaHalangan(permohonan, h);
            logger.debug("isBerhalang:" + isBerhalang);
            if (isBerhalang) {
                addSimpleError("Hakmilik ini Mempunyai Halangan Mahkamah");
            }
        }

//        KodCawangan kod = pguna.getKodCawangan();
        // kene cater ikut peranan..
        //List<PenggunaPeranan> list = penggunaperananDao.findAll();
//        List<Pengguna> lp = penggunaDao.findAll();
        listPt = new ArrayList<Pengguna>();
        listPp = new ArrayList<Pengguna>();
        listPp1 = new ArrayList<Pengguna>();
        listPptd = new ArrayList<Pengguna>();
        listPptk = new ArrayList<Pengguna>();
        listIo = new ArrayList<Pengguna>();
        listPptg = new ArrayList<Pengguna>();


//        for (Pengguna p : lp) {
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {
//                    //TODO : change peranan utama
//                    if (p.getPerananUtama().getKod().equals("PT")) {
//                        listPt.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("PPT")) {
//                        listPp.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("PP")) {
//                        listPp1.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("PPTD")) {
//                        listPptd.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("PPTK")) {
//                        listPptk.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("IO")) {
//                        listIo.add(p);
//                    }
//
//                }
//            }
//        }

        // get pengguna using enforcement service since need to sorting list by kekananan : modified by latifah 8/10/12
        List<String> kodList = new ArrayList<String>();
        kodList.add("PT");
        listPt = enforcementService.getSenaraiKumpulanBpel(kodList);
        kodList = new ArrayList<String>();
        kodList.add("2410");
        kodList.add("PPT");
        listPp = enforcementService.getSenaraiKumpulanBpel(kodList);
        kodList = new ArrayList<String>();
        kodList.add("PP");
        listPp1 = enforcementService.getSenaraiKumpulanBpel(kodList);
        kodList = new ArrayList<String>();
        kodList.add("PPTD");
        kodList.add("2407");
        kodList.add("9");
        kodList.add("71");
        kodList.add("89");
        listPptd = enforcementService.getSenaraiKumpulanBpel(kodList);
        kodList = new ArrayList<String>();
        kodList.add("32");
        kodList.add("PPTK");
        listPptk = enforcementService.getSenaraiKumpulanBpel(kodList);
        kodList = new ArrayList<String>();
        if (conf.getProperty("kodNegeri").equalsIgnoreCase("04")) {
            kodList.add("IO");
            listIo = enforcementService.getSenaraiKumpulanBpel(kodList);
        } else if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            BPelService service = new BPelService();

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pguna);
                if (task != null) {
                    stageId = task.getSystemAttributes().getStage();
                }
            } else {
                stageId = "keputusan_tindakan";
            }

            if (stageId.equalsIgnoreCase("arah_operasi1")
                    || stageId.equalsIgnoreCase("kpsn2")
                    || stageId.equalsIgnoreCase("lantik_io")
                    || stageId.equalsIgnoreCase("keputusan_tindakan")
                    || stageId.equalsIgnoreCase("maklum_tidak_bayar_kompaun")
                    || stageId.equalsIgnoreCase("keputusan1")) { //Lantik IO
                kodList = new ArrayList<String>();
                kodList.add("PPTK");
                kodList.add("2410");
                listRo = enforcementService.getSenaraiKumpulanBpel(kodList);

                kodList.add("2407");
                kodList.add("PPTK");
                kodList.add("2410");
                listIo = enforcementService.getSenaraiKumpulanBpel(kodList);
            } else {
                FasaPermohonan fasaPermohonan = null;

                if (stageId.equalsIgnoreCase("arah_simpan_barang")
                        || stageId.equalsIgnoreCase("maklum_jual_barang")) { //agih pada RO
                    fasaPermohonan = enforcementService.findByStageId(idPermohonan, "jalan_operasi1");

                    if (fasaPermohonan != null) {
                        Pengguna pengguna = enforcementService.findByIdPengguna(fasaPermohonan.getIdPengguna());
                        listRo = new ArrayList<Pengguna>();
                        listRo.add(pengguna);
                    }
                } else if (stageId.equalsIgnoreCase("kpsn3")
                        || stageId.equalsIgnoreCase("kemaskini_inventori")
                        || stageId.equalsIgnoreCase("tindakan_simpan_barang")
                        || stageId.equalsIgnoreCase("kpsn4")
                        || stageId.equalsIgnoreCase("maklum_bayaran_kompaun")
                        || stageId.equalsIgnoreCase("maklum_tidak_bayar_kompaun")
                        || stageId.equalsIgnoreCase("maklum_rayuan_kompaun")
                        || stageId.equalsIgnoreCase("peraku_surat_lepas_brg")
                        || stageId.equalsIgnoreCase("imbas_surat_tuntutan")
                        || stageId.equalsIgnoreCase("hantar_surat_lepas_brg")
                        || stageId.equalsIgnoreCase("keputusan2")) { //agih pada IO
                    fasaPermohonan = enforcementService.findByStageId(idPermohonan, "sedia_kertas_pertuduhan");

                    if (fasaPermohonan == null) {
                        fasaPermohonan = enforcementService.findByStageId(idPermohonan, "arah_simpan_barang");
                    }
                    if (fasaPermohonan == null) {
                        fasaPermohonan = enforcementService.findByStageId(idPermohonan, "sedia_kertas_siasatan");
                    }
                    if (fasaPermohonan == null) {
                        fasaPermohonan = enforcementService.findByStageId(idPermohonan, "rujuk_kepada_tpr");
                    }
                    if (fasaPermohonan == null) {
                        fasaPermohonan = enforcementService.findByStageId(idPermohonan, "sedia_perintah_alih_halangan");
                    }

                    if (fasaPermohonan != null) {
                        Pengguna pengguna = enforcementService.findByIdPengguna(fasaPermohonan.getIdPengguna());
                        listIo = new ArrayList<Pengguna>();
                        listIo.add(pengguna);
                    }
                }
            }
        }

        kodList = new ArrayList<String>();
        kodList.add("PPTG");
        listPptg = enforcementService.getSenaraiKumpulanBpel(kodList);


        logger.info("listPt (PT) :" + listPt.size());
        logger.info("listPp (PPT) :" + listPp.size());
        logger.info("listPp1 (PP) :" + listPp1.size());
        logger.info("listPptd (PPTD) :" + listPptd.size());
        logger.info("listPptk (PPTK) :" + listPptk.size());
        logger.info("listIo (IO) :" + listIo.size());
        logger.info("listTptg (TPTG) :" + listPptg.size());
        return new JSP("/penguatkuasaan/agih_tugasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri : " + kodNegeri);
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        System.out.println("IdPermohonan :" + idPermohonan);
        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);

        }
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageIDAgihTugas = t.getSystemAttributes().getStage();

        } else {
            stageIDAgihTugas = "keputusan_tindakan";
        }

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
            }
        }

        System.out.println("-------------stageIDAgihTugas--" + stageIDAgihTugas);

        permohonanNota = enforcementService.findEmptyNotaMinit(mohon.getIdPermohonan(), stageIDAgihTugas);
        logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota != null) {
            logger.info("::: kandungan nota :" + permohonanNota.getNota());
            statusNotaExist = false;
        }

    }

    public Resolution agihPT1() {
        //TODO INTEGRATION WITH BPEL
        //        idPermohonan = mohon.getIdPermohonan();
//        mohon = mohonDao.findById(idPermohonan);
        StringBuilder sb = new StringBuilder();
        try {
            //TODO urusan berangkai
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            System.out.println("taskId :" + taskId);
//            System.out.println("pguna :" + pguna.getIdPengguna());
//            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
//            if (ctx != null) {
//                System.out.println("hantar kepada " + pengguna.getIdPengguna());
//                //update outcome to move to next stage
//                WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
//                WorkFlowService.reassignTask(ctx, taskId, pengguna.getIdPengguna(), "user");
//            }

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
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
                Task task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                String stageId = task.getSystemAttributes().getStage();


                Permohonan permohonan = mohonDao.findById(idPermohonan);

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equals(stageId)
                            && fp.getIdPengguna().equals(pengguna.getIdPengguna())) {
                        _fp = fp;
                        break;
                    }
                }

                logger.debug("cawangan " + pengguna.getKodCawangan());
                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                if (_fp != null) {
                    au = _fp.getInfoAudit();
                    au.setTarikhKemaskini(new Date());
                    au.setDiKemaskiniOleh(pguna);
                    _fp.setInfoAudit(au);
                } else {
                    _fp = new FasaPermohonan();
                    au.setDimasukOleh(pguna);
                    au.setTarikhMasuk(new Date());
                    _fp.setInfoAudit(au);
                    _fp.setPermohonan(permohonan);
                    _fp.setCawangan(pengguna.getKodCawangan());
                    _fp.setIdAliran(stageId);
                    _fp.setIdPengguna(pengguna.getIdPengguna());
                }
                if (mohon != null && utama != false) {

                    mohon.setUtama(1);
                    mohon.setInfoAudit(au);
                    mohonDao.saveOrUpdate(mohon);
                    tx.commit();
                }
                _fp.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(_fp);
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
    }

    public Resolution agihPT3() {
        //TODO INTEGRATION WITH BPEL
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
                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
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
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public Resolution agihPT4() {

        String msg = "";
        //TODO INTEGRATION WITH BPEL
        //        idPermohonan = mohon.getIdPermohonan();
//        mohon = mohonDao.findById(idPermohonan);
        StringBuilder sb = new StringBuilder();
        try {
            //TODO urusan berangkai
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            System.out.println("taskId :" + taskId);
//            System.out.println("pguna :" + pguna.getIdPengguna());
//            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
//            if (ctx != null) {
//                System.out.println("hantar kepada " + pengguna.getIdPengguna());
//                //update outcome to move to next stage
//                WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
//                WorkFlowService.reassignTask(ctx, taskId, pengguna.getIdPengguna(), "user");
//            }

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);

                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                try {
                    InfoAudit ia = new InfoAudit();
                    if (mohon == null) {
                        mohon = new Permohonan();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    } else {
                        ia = mohon.getInfoAudit();
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }

                    if (mohon != null && utama != false) {

                        mohon.setUtama(1);
                        mohon.setInfoAudit(ia);
                        mohonDao.saveOrUpdate(mohon);
                        tx.commit();
                    }

                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    logger.error(t);
                }
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                logger.info("id pengguna ::" + pengguna.getIdPengguna());

//                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                System.out.println(" ctx : " + ctx);
                System.out.println(" taskID : " + taskID);
                System.out.println(" id pengguna : " + pengguna.getIdPengguna() + " nama pengguna : " + pengguna.getNama());

                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getNama() + "(" + pengguna.getIdPengguna() + ")");

//                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getNama() + "(" + pengguna.getIdPengguna() + ")" + " telah berjaya.";
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", msg);
    }

    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        //        idPermohonan = mohon.getIdPermohonan();
//        mohon = mohonDao.findById(idPermohonan);
        StringBuilder sb = new StringBuilder();
        try {
            //TODO urusan berangkai
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            System.out.println("taskId :" + taskId);
//            System.out.println("pguna :" + pguna.getIdPengguna());
//            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
//            if (ctx != null) {
//                System.out.println("hantar kepada " + pengguna.getIdPengguna());
//                //update outcome to move to next stage
//                WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
//                WorkFlowService.reassignTask(ctx, taskId, pengguna.getIdPengguna(), "user");
//            }

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
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
                permohonan = mohonDao.findById(idPermohonan);
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna() + "(" + pengguna.getNama() + ")");

                Task task = null;

                if (kodNegeri.equalsIgnoreCase("05")) {
                    System.out.println(stageId);
                    logger.info("kod urusan ::" + permohonan.getKodUrusan().getKod());
                    logger.info("-------------stageIDAgihTugas--" + stageIDAgihTugas);
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49") && stageId.equalsIgnoreCase("agih_tugasan2")) {
                        stageIDAgihTugas = "keputusan2";
                    }
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49") && stageId.equalsIgnoreCase("agih_tugasan1")) {
                        stageIDAgihTugas = "sedia_laporan1";
                    }
                    FasaPermohonan fp = enforcementService.findByStageId(idPermohonan, stageIDAgihTugas);
                    if (fp != null) {
                        if (fp.getKeputusan() == null) {
                            task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");

                            String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                            Permohonan permohonan = mohonDao.findById(idPermohonan);

                            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                            FasaPermohonan _fp = null;
                            InfoAudit au = new InfoAudit();

                            for (FasaPermohonan mf : senaraiFasa) {
                                if (mf.getIdAliran().equals(stageIdAgihan)
                                        && mf.getIdPengguna().equals(pengguna.getIdPengguna())) {
                                    _fp = mf;
                                    break;
                                }
                            }

                            logger.debug("cawangan " + pengguna.getKodCawangan());

                            if (_fp != null) {
                                au = _fp.getInfoAudit();
                                au.setTarikhKemaskini(new Date());
                                au.setDiKemaskiniOleh(pguna);
                                _fp.setInfoAudit(au);
                            } else {
                                _fp = new FasaPermohonan();
                                au.setDimasukOleh(pguna);
                                au.setTarikhMasuk(new Date());
                                _fp.setInfoAudit(au);
                                _fp.setPermohonan(permohonan);
                                _fp.setCawangan(pengguna.getKodCawangan());
                                _fp.setIdAliran(stageIdAgihan);
                                _fp.setIdPengguna(pengguna.getIdPengguna());
                            }
                            _fp.setTarikhHantar(new Date());
                            fasaPermohonanManager.saveOrUpdate(_fp);
                        } else {
                            if (fp.getKeputusan().getKod().isEmpty()) {
                                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");

                                String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                                Permohonan permohonan = mohonDao.findById(idPermohonan);

                                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                                FasaPermohonan _fp = null;
                                InfoAudit au = new InfoAudit();

                                for (FasaPermohonan mf1 : senaraiFasa) {
                                    if (mf1.getIdAliran().equals(stageIdAgihan)
                                            && mf1.getIdPengguna().equals(pengguna.getIdPengguna())) {
                                        _fp = mf1;
                                        break;
                                    }
                                }

                                logger.debug("cawangan " + pengguna.getKodCawangan());

                                if (_fp != null) {
                                    au = _fp.getInfoAudit();
                                    au.setTarikhKemaskini(new Date());
                                    au.setDiKemaskiniOleh(pguna);
                                    _fp.setInfoAudit(au);
                                } else {
                                    _fp = new FasaPermohonan();
                                    au.setDimasukOleh(pguna);
                                    au.setTarikhMasuk(new Date());
                                    _fp.setInfoAudit(au);
                                    _fp.setPermohonan(permohonan);
                                    _fp.setCawangan(pengguna.getKodCawangan());
                                    _fp.setIdAliran(stageIdAgihan);
                                    _fp.setIdPengguna(pengguna.getIdPengguna());
                                }
                                _fp.setTarikhHantar(new Date());
                                fasaPermohonanManager.saveOrUpdate(_fp);
                            } else {
                                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user", fp.getKeputusan().getKod());

                                String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                                Permohonan permohonan = mohonDao.findById(idPermohonan);

                                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                                FasaPermohonan _fp = null;
                                InfoAudit au = new InfoAudit();

                                for (FasaPermohonan mf2 : senaraiFasa) {
                                    if (mf2.getIdAliran().equals(stageIdAgihan)
                                            && mf2.getIdPengguna().equals(pengguna.getIdPengguna())) {
                                        _fp = mf2;
                                        break;
                                    }
                                }

                                logger.debug("cawangan " + pengguna.getKodCawangan());

                                if (_fp != null) {
                                    au = _fp.getInfoAudit();
                                    au.setTarikhKemaskini(new Date());
                                    au.setDiKemaskiniOleh(pguna);
                                    _fp.setInfoAudit(au);
                                } else {
                                    _fp = new FasaPermohonan();
                                    au.setDimasukOleh(pguna);
                                    au.setTarikhMasuk(new Date());
                                    _fp.setInfoAudit(au);
                                    _fp.setPermohonan(permohonan);
                                    _fp.setCawangan(pengguna.getKodCawangan());
                                    _fp.setIdAliran(stageIdAgihan);
                                    _fp.setIdPengguna(pengguna.getIdPengguna());
                                }
                                _fp.setTarikhHantar(new Date());
                                fasaPermohonanManager.saveOrUpdate(_fp);
                            }
                        }
                    } else {
                        task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");

                        String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                        Permohonan permohonan = mohonDao.findById(idPermohonan);

                        List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                        FasaPermohonan _fp = null;
                        InfoAudit au = new InfoAudit();

                        for (FasaPermohonan fp1 : senaraiFasa) {
                            if (fp1.getIdAliran().equals(stageIdAgihan)
                                    && fp1.getIdPengguna().equals(pengguna.getIdPengguna())) {
                                _fp = fp1;
                                break;
                            }
                        }

                        logger.debug("cawangan " + pengguna.getKodCawangan());

                        if (_fp != null) {
                            au = _fp.getInfoAudit();
                            au.setTarikhKemaskini(new Date());
                            au.setDiKemaskiniOleh(pguna);
                            _fp.setInfoAudit(au);
                        } else {
                            _fp = new FasaPermohonan();
                            au.setDimasukOleh(pguna);
                            au.setTarikhMasuk(new Date());
                            _fp.setInfoAudit(au);
                            _fp.setPermohonan(permohonan);
                            _fp.setCawangan(pengguna.getKodCawangan());
                            _fp.setIdAliran(stageIdAgihan);
                            _fp.setIdPengguna(pengguna.getIdPengguna());
                        }
                        _fp.setTarikhHantar(new Date());
                        fasaPermohonanManager.saveOrUpdate(_fp);
                    }
                } else {
                    task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");

                    String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                    Permohonan permohonan = mohonDao.findById(idPermohonan);

                    List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                    FasaPermohonan _fp = null;
                    InfoAudit au = new InfoAudit();

                    for (FasaPermohonan fp : senaraiFasa) {
                        if (fp.getIdAliran().equals(stageIdAgihan)
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
                        _fp.setInfoAudit(au);
                    } else {
                        _fp = new FasaPermohonan();
                        au.setDimasukOleh(pguna);
                        au.setTarikhMasuk(new Date());
                        _fp.setInfoAudit(au);
                        _fp.setPermohonan(permohonan);
                        _fp.setCawangan(pengguna.getKodCawangan());
                        _fp.setIdAliran(stageIdAgihan);
                        _fp.setIdPengguna(pengguna.getIdPengguna());
                    }
                    _fp.setTarikhHantar(new Date());
                    fasaPermohonanManager.saveOrUpdate(_fp);
                }



                PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageIDAgihTugas);
                if (nota != null) {
                    logger.info("::: update status nota to T = tidak aktif ::: ");
                    nota.setStatusNota('T');
                    enforceService.simpanNota(nota);
                }

                //Hafifi : 12/2/2014 - generate surat lantikan IO for N9
                if (kodNegeri.equalsIgnoreCase("05")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")
                            || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")
                            || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")
                            || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                        if (stageIDAgihTugas.equalsIgnoreCase("kpsn2")
                                || stageIDAgihTugas.equalsIgnoreCase("lantik_io")
                                || stageIDAgihTugas.equalsIgnoreCase("keputusan_tindakan")) {
                            String gen = "ENFLantikIO_NS.rdf";
                            String code = "SLPPS";
                            lelongService.reportGen(permohonan, gen, code, pguna);
                        }
                    }
                }
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya kepada " + pengguna.getIdPengguna() + "(" + pengguna.getNama() + ").");
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if (isBerangkai) {
            logger.info("Urusan Berangkai");
            List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            logger.info("taskList :: " + taskList.size());
            for (int i = 0; i < taskList.size(); i++) {
                Task impl = (Task) taskList.get(i);
                String taskId = impl.getSystemAttributes().getTaskId();
                String idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
                System.out.println("taskID::" + taskId);
                Task temp = WorkFlowService.getTask(taskId, ctx);

                if (temp.getSystemAttributes().getAcquiredBy() == null) {
                    WorkFlowService.acquireTask(taskId, ctx);
                }

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    logger.info("idPermohonan :: " + idPermohonan);
                    logger.info("p.idPermohonan :: " + p.getIdPermohonan());
                    if (p.getIdPermohonan().equals(idPermohonan)) {
                        map = new HashMap<String, String>();
                        map.put("idPermohonan", p.getIdPermohonan());
                        map.put("taskId", taskId);
                        list.add(map);
                    }
                }
            }
        } else {
            //standalone
            logger.info("Urusan tidak berangkai");
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            map = new HashMap<String, String>();
            map.put("idPermohonan", mohon.getIdPermohonan());
            map.put("taskId", taskId);
            list.add(map);
        }
        return list;
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

    public List<Pengguna> getListPp1() {
        return listPp1;
    }

    public void setListPp1(List<Pengguna> listPp1) {
        this.listPp1 = listPp1;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<Permohonan> getSenaraiPermohonanBerangkai() {
        return senaraiPermohonanBerangkai;
    }

    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
    }

    public boolean isUtama() {
        return utama;
    }

    public void setUtama(boolean utama) {
        this.utama = utama;
    }

    public List<Pengguna> getListPptd() {
        return listPptd;
    }

    public void setListPptd(List<Pengguna> listPptd) {
        this.listPptd = listPptd;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<Pengguna> getListPptk() {
        return listPptk;
    }

    public void setListPptk(List<Pengguna> listPptk) {
        this.listPptk = listPptk;
    }

    public List<Pengguna> getListIo() {
        return listIo;
    }

    public void setListIo(List<Pengguna> listIo) {
        this.listIo = listIo;
    }

    public String getStageIDAgihTugas() {
        return stageIDAgihTugas;
    }

    public void setStageIDAgihTugas(String stageIDAgihTugas) {
        this.stageIDAgihTugas = stageIDAgihTugas;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public List<Pengguna> getListPt() {
        return listPt;
    }

    public void setListPt(List<Pengguna> listPt) {
        this.listPt = listPt;
    }

    public String getIdPermohonan() {
        return IdPermohonan;
    }

    public void setIdPermohonan(String IdPermohonan) {
        this.IdPermohonan = IdPermohonan;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }

    public List<Pengguna> getListRo() {
        return listRo;
    }

    public void setListRo(List<Pengguna> listRo) {
        this.listRo = listRo;
    }
}
