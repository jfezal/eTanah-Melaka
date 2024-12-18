/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.Lelongan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PelupusanService;
import etanah.service.common.LelongService;
import etanah.view.stripes.pelupusan.disClass.DisAgihanTugas;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/agihanTugasJurulelong/{currentStage}")
public class AgihanTugasToJurulelongActionBean extends AbleActionBean {

    private Permohonan mohon;
    private FasaPermohonan fasaPermohonan;
    private Lelongan lelong;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private List<Lelongan> senaraiLelongan;
    private boolean isBerangkai = Boolean.FALSE;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihanTugasToJurulelongActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    //private boolean isBerhalang = Boolean.FALSE;
    private String nota;
    private String stageID;
    private String participant;
    private boolean button = false;
    private boolean check = false;
    @Inject
    PermohonanService permohonanService;
    @Inject
    AgihanTugasPerananActionBean agihTugas;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    @Inject
    PelupusanService perlupusanService;
    @Inject
    LelongService lelongService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PermohonanDAO mohonDao;
    @Inject
    PenggunaPerananDAO penggunaperananDao;

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String currentStage = getContext().getRequest().getParameter("currentStage");
        logger.info("currentStage: " + currentStage);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);

        //FasaPermohonan mohonFasa = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
        List<FasaPermohonan> mohonFasaList = lelongService.findListFasaPermohonanSemakPembida(idPermohonan);
        FasaPermohonan mohonFasa = null;
        if (!mohonFasaList.isEmpty()) {
            mohonFasa = mohonFasaList.get(0);
        }
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan().getKod().equals("AA")) {
                logger.info("------Jurulelong------");
                List<Lelongan> lel = null;
                if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                    lel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    lel = lelongService.listLelonganAK(idPermohonan);
                }

                if (lel.isEmpty()) {
                    addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                    button = true;
                    return new ForwardResolution("/WEB-INF/jsp/lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
                } else {
                    for (Lelongan ll : lel) {
                        if (ll.getTarikhLelong() == null) {
                            addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                            button = true;
                            return new ForwardResolution("/WEB-INF/jsp/lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
                        }
                    }
//                }
//            } else {

                    button = false;
                    if (StringUtils.isNotBlank(msg)) {
                        addSimpleMessage(msg);
                    }

                    String kodNegeri = conf.getProperty("kodNegeri");
                    logger.info("kod negeri: " + kodNegeri);
                    urusan = permohonan.getKodUrusan().getKod();
                    // kene cater ikut peranan..
                    // List<PenggunaPeranan> list = penggunaperananDao.findAll();
                    listPp = new LinkedList<Pengguna>();

                    try {
                        List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                        for (Task t : l) {
                            stageID = t.getSystemAttributes().getStage();
                            participant = t.getSystemAttributes().getParticipantName();
                            logger.info("-----Stage ID:" + stageID + "-----");
                        }
                    } catch (Exception e) {
                        logger.error("error ::" + e.getMessage());
                    }

                    /*
                     * CALLING PENGGUNA METHOD
                     */
                    if (permohonan.getKodUrusan().getKod().equals("PPJP")) {
                        this.getListPguna(permohonan, kodNegeri);
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                        this.getListPgunaPPTL(permohonan, kodNegeri);
                    }

                    senaraiLelongan = lelongService.listLelonganAK(idPermohonan);

                    for (Lelongan lll : senaraiLelongan) {
                        if (lll.getKodStatusLelongan().getKod().equals("AK")) {
                            lelong = lll;
                            break;
                        }
                        if (lll.getJurulelong() == null) {
                            check = true;
                        }
                    }
                }
            }
        } else {
            button = false;
            if (StringUtils.isNotBlank(msg)) {
                addSimpleMessage(msg);
            }

            String kodNegeri = conf.getProperty("kodNegeri");
            logger.info("kod negeri: " + kodNegeri);
            urusan = permohonan.getKodUrusan().getKod();
            // kene cater ikut peranan..
            // List<PenggunaPeranan> list = penggunaperananDao.findAll();
            listPp = new LinkedList<Pengguna>();

            try {
                List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                for (Task t : l) {
                    stageID = t.getSystemAttributes().getStage();
                    participant = t.getSystemAttributes().getParticipantName();
                    logger.info("-----Stage ID:" + stageID + "-----");
                }
            } catch (Exception e) {
                logger.error("error ::" + e.getMessage());
            }

            /*
             * CALLING PENGGUNA METHOD
             */
            String kod = permohonan.getKodUrusan().getKod();
            if (permohonan.getKodUrusan().getKod().equals("PPJP")) {
                this.getListPguna(permohonan, kodNegeri);
            }
            if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                this.getListPgunaPPTL(permohonan, kodNegeri);
            }

            senaraiLelongan = lelongService.listLelonganAK(idPermohonan);

            for (Lelongan lll : senaraiLelongan) {
                if (lll.getKodStatusLelongan().getKod().equals("AK")) {
                    lelong = lll;
                    break;
                }
                if (lll.getJurulelong() == null) {
                    check = true;
                }
            }
        }
//    }

//       }
//        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
    }

    public Resolution showFormSemakPembida() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            if (fasaPermohonan.getKeputusan() == null) {
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
            } else if (fasaPermohonan.getKeputusan().getKod().equals("LS")) {
                addSimpleError("Keputusan Lelongan Oleh Pentadbir Tanah, Sila Tekan Butang Selesai");
                return new JSP("lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
            } else {
                if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                    addSimpleError("Keputusan Adalah Rujuk Mahkamah, Sila Tekan Butang Selesai");
                    return new JSP("lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");
                }
                String msg = getContext().getRequest().getParameter("message");
                String currentStage = getContext().getRequest().getParameter("currentStage");
                logger.info("currentStage: " + currentStage);

                if (StringUtils.isNotBlank(msg)) {
                    addSimpleMessage(msg);
                }

//            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                Permohonan permohonan = mohonDao.findById(idPermohonan);
                String kodNegeri = conf.getProperty("kodNegeri");
                logger.info("kod negeri: " + kodNegeri);
                urusan = permohonan.getKodUrusan().getKod();
                // kene cater ikut peranan..
                // List<PenggunaPeranan> list = penggunaperananDao.findAll();
                listPp = new LinkedList<Pengguna>();

                try {
                    List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                    for (Task t : l) {
                        stageID = t.getSystemAttributes().getStage();
                        participant = t.getSystemAttributes().getParticipantName();
                        logger.info("-----Stage ID:" + stageID + "-----");
                    }
                } catch (Exception e) {
                    logger.error("error ::" + e.getMessage());
                }


                /*
                 * CALLING PENGGUNA METHOD
                 */
                this.getListPguna(permohonan, kodNegeri);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/agihTugasToJurulelong.jsp").addParameter("tab", "true");

    }

    /**
     *
     * @author Shazwan
     * @version 1.0 date 31/10/2011
     */
    private void getListPgunaPPTL(Permohonan permohonan, String negeri) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPTL") ? 1
                //                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 2
                //                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : 0;
//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";
        listPp = agihTugas.getPenggunaL(numUrusan, stageID, negeri);

    }

    private void getListPguna(Permohonan permohonan, String negeri) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPJP") ? 1
                //                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 2
                //                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : 0;
//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";
        listPp = agihTugas.getPenggunaL(numUrusan, stageID, negeri);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

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

        String msg = "";
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idMohon);
        String kodUrusan = mohon.getKodUrusan().getKod();
        String kodNegeri = conf.getProperty("kodNegeri");

        FasaPermohonan fasaPermohonan1 = lelongService.findPermonanFasaRekodBidaan1(mohon.getIdPermohonan());
        if (fasaPermohonan1 != null) {
            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasaPermohonan1);
            if (fasaPermohonanLog != null) {
                lelongService.deletetest(fasaPermohonanLog, fasaPermohonan1);
            }
        }

        // get stageID
        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idMohon);
            for (Task t : l) {
                stageID = t.getSystemAttributes().getStage();
                logger.info("-----Stage ID:" + stageID + "-----");
            }
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }

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
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());

                Task task = null;
                String stageId = null;
                if (kodNegeri.equalsIgnoreCase("04")) { // melaka
                   // fasaPermohonan = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
                    List<FasaPermohonan> mohonFasaList = lelongService.findListFasaPermohonanSemakPembida(idPermohonan);
                    FasaPermohonan mohonFasa = null;
                    if (!mohonFasaList.isEmpty()) {
                        mohonFasa = mohonFasaList.get(0);
                    }
//                   logger.info("fasa ::" + fasaPermohonan);
//                   logger.info("stage  ::" + stageID);
////                   logger.info("kpsn ::" + fasaPermohonan.getKeputusan().getKod());

                    if (stageID.equals("semakPembida") && mohonFasa.getKeputusan().getKod().equals("AA")) {

                        task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user", mohonFasa.getKeputusan().getKod());
                    } else {
                        task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                    }
                    stageId = "rekodBidaanJLB";
                    /*
                     * else if(kodUrusan.equals("PLPS") &&
                     * stageID.equals("g_charting_keputusan")) { task =
                     * WorkFlowService.reassignTask(ctx, taskID,
                     * pengguna.getIdPengguna(), "user", "L"); stageId =
                     * task.getSystemAttributes().getStage();
                     }
                     */

                } else if (kodNegeri.equalsIgnoreCase("05")) { // negeri sembilan
                    task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                    stageId = task.getSystemAttributes().getStage();
                }

                logger.info("stage from task: " + stageId);
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
                _fp.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(_fp);

//                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanLelongActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanLelongActionBean.class).addParameter("message", msg);
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
