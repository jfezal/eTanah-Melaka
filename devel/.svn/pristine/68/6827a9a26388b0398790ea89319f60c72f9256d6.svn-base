/**
 *
 * @author Shazwan
 */
package etanah.view.stripes.pelupusan;

import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodPeranan;
import etanah.model.NoPt;
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
import etanah.service.PengambilanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PelupusanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.view.stripes.pelupusan.disClass.DisAgihanTugas;

@UrlBinding("/pelupusan/agihTugasanV2/{currentStage}")
public class AgihanTugasDisV2ActionBean extends AbleActionBean {

    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    private FasaPermohonan fp;
    private NoPt noPT;
    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean isBerangkai = Boolean.FALSE;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihanTugasDisActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    //private boolean isBerhalang = Boolean.FALSE;
    private String nota;
    private String stageID;
    private String kodJabatan;
    private String kodPeranan;
    private String participant;
    @Inject
    PermohonanService permohonanService;
    @Inject
    DisAgihanTugas dat;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    @Inject
    PelupusanService perlupusanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PermohonanDAO mohonDao;
    @Inject
    PenggunaPerananDAO penggunaperananDao;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String currentStage = getContext().getRequest().getParameter("currentStage");
        logger.info("currentStage: " + currentStage);

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        String kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri: " + kodNegeri);
        urusan = permohonan.getKodUrusan().getKod();
        KodJabatan kodJabatan = kodJabatanDAO.findById("2");
        KodPeranan kodPeranan = kodPerananDAO.findById("31"); //modify by siti fariza tukar kod 20102014
        // kene cater ikut peranan..
        // List<PenggunaPeranan> list = penggunaperananDao.findAll();
        listPp = new LinkedList<Pengguna>();

        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stageID = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getParticipantName();
                if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                    break;
                }
                logger.info("-----Stage ID:" + stageID + "-----");
            }
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }

        /*
         * CALLING PENGGUNA METHOD
         */
        this.getListPguna(permohonan, kodNegeri);

//        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
//            this.getListPgunaPLPS(permohonan, kodNegeri, pguna);
//        } else {
//            this.getListPguna(permohonan, kodNegeri);
//        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/common/agih_tugasanV2.jsp").addParameter("tab", "true");
    }

    /**
     *
     * @author Shazwan
     * @version 1.0 date 31/10/2011
     */
    private void getListPguna(Permohonan permohonan, String negeri) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPRU") ? 1
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 2
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 3
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 5
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 6
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 7
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 8
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 9
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 11
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 12
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 13
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 14
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 15
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 17
                : permohonan.getKodUrusan().getKod().equals("RLPSK") ? 18
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 19
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 20
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 21
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 22
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 23
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 24
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 25
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 26
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 27
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 28
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 29
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 30
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 31
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 32
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 33
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 34
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 35
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 36
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 37
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 38
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 39
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 40
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 41
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 42
                : permohonan.getKodUrusan().getKod().equals("BMRE") ? 43
                : permohonan.getKodUrusan().getKod().equals("LSTP") ? 44
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 45
                : 0;
//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";
        listPp = dat.getPengguna(numUrusan, stageID, negeri, permohonan.getCawangan().getKod());
        this.checkNoPtSementara(numUrusan, permohonan);

    }

    private void getListPgunaPLPS(Permohonan permohonan, String negeri, Pengguna peng) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPRU") ? 1
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 2
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 3
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 5
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 6
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 7
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 8
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 9
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 11
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 12
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 13
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 14
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 15
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 17
                : permohonan.getKodUrusan().getKod().equals("RLPSK") ? 18
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 19
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 20
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 21
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 22
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 23
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 24
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 25
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 26
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 27
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 28
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 29
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 30
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 31
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 32
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 33
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 34
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 35
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 36
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 37
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 38
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 39
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 40
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 41
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 42
                : permohonan.getKodUrusan().getKod().equals("BMRE") ? 43
                : 0;
//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";

        fp = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(permohonan.getIdPermohonan(), stageID);
        if (fp.getIdAliran().equalsIgnoreCase("agihan_tugas") || fp.getIdAliran().equals("agihan_tugas3") || fp.getIdAliran().equals("agihan_tugas4") || fp.getIdAliran().equals("agihan_tugas5")) {
//            listPp = dat.getPenggunaPLPS(numUrusan, stageID, negeri, permohonan.getCawangan().getKod(), permohonan.getKodUrusan().getJabatan().getKod(), "31");
        } else if (fp.getIdAliran().equals("agihan_tugas2") || fp.getIdAliran().equals("terima_keputusan_mmkn")) {
//            listPp = dat.getPenggunaPLPS(numUrusan, stageID, negeri, permohonan.getCawangan().getKod(), permohonan.getKodUrusan().getJabatan().getKod(), "24");
        }
        this.checkNoPtSementara(numUrusan, permohonan);

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

        // get stageID
        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idMohon);
            for (Task t : l) {
                stageID = t.getSystemAttributes().getStage();
                logger.info("-----Stage ID:" + stageID + "-----");

                FasaPermohonan fasaMohon = notisPenerimaanService.getFasaPermohonan(idMohon, stageID);
                if (fasaMohon != null) {
                    InfoAudit ia = new InfoAudit();
                    ia = fasaMohon.getInfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pguna);
                    fasaMohon.setInfoAudit(ia);
                    fasaMohon.setTarikhHantar(new Date());
                    fasaPermohonanManager.saveOrUpdate(fasaMohon);
                }
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
                    if (kodUrusan.equals("PBMT") && stageID.equals("arahan_tugas")) {
                        if (mohon.getKeputusan().getKod().equals("L")) {
                            task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user", mohon.getKeputusan().getKod());
                        } else {
                            task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                        }
                        stageId = task.getSystemAttributes().getStage();
                    } /*
                     * else if(kodUrusan.equals("PLPS") &&
                     * stageID.equals("g_charting_keputusan")) { task =
                     * WorkFlowService.reassignTask(ctx, taskID,
                     * pengguna.getIdPengguna(), "user", "L"); stageId =
                     * task.getSystemAttributes().getStage();
                     }
                     */ else {
                        task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                        stageId = task.getSystemAttributes().getStage();
                    }
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
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", msg);
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

    public void checkNoPtSementara(int numUrusan, Permohonan permohonan) {
        switch (numUrusan) {
            case 15:
                createNoPtSementara(permohonan);
                break;
        }

    }

    public void createNoPtSementara(Permohonan permohonan) {
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        boolean check = false;
        if (hakmilikPermohonanList.size() > 0) {

            int index = 0;
            //Checking
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                if (hp.getHakmilik() != null) {
                    noPT = perlupusanService.findNoPtByIdHakmilikPermohonan(hp.getId());
                    if (noPT != null) {
                        check = true;
                    } else {
                        noPT = new NoPt();
                        index++;
                        noPT.setInfoAudit(info);
                        if (hp.getHakmilik() != null) {
                            noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
                        } else {
                            noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod()));
                        }
                        noPT.setHakmilikPermohonan(hp);
                        Long maxNoPt = new Long(0);
                        Long maxNoPtSementara = new Long(0);
                        if (hp.getHakmilik() != null) {
                            maxNoPt = perlupusanService.getMaxOfNoPTNew(hp.getHakmilik().getBandarPekanMukim().getKod());
                            maxNoPtSementara = perlupusanService.getMaxOfNoPTSementaraNew(hp.getHakmilik().getBandarPekanMukim().getKod());
                        } else {
                            maxNoPt = perlupusanService.getMaxOfNoPTNew(hp.getBandarPekanMukimBaru().getKod());
                            maxNoPtSementara = perlupusanService.getMaxOfNoPTSementaraNew(hp.getBandarPekanMukimBaru().getKod());
                        }

                        if (maxNoPt == null) {
                            maxNoPt = new Long(0);
                        }
                        if (maxNoPtSementara == null) {
                            maxNoPtSementara = new Long(0);
                        }
                        int no1 = 0;
                        String noPtSementara = null;
                        if (!maxNoPtSementara.toString().equals("0")) {
                            if (!maxNoPt.toString().equals("0")) {
                                if (maxNoPt > maxNoPtSementara) {
                                    no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                } else {
                                    no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                }
                            } else {
                                no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            }
                        } else {
                            if (!maxNoPt.toString().equals("0")) {
                                no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            } else { //For the first data
                                no1 = 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            }
                        }
                        perlupusanService.simpanNoPt(noPT);
                    }
                }
            }
        }
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

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }
}
