/**
 *
 * @author Shazwan
 */
package etanah.view.penguatkuasaan;

import etanah.view.stripes.pelupusan.*;
import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.NoPt;
import etanah.model.PermohonanNota;
import etanah.service.BPelService;
import etanah.service.EnforceService;
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
import etanah.service.common.EnforcementService;
import etanah.view.stripes.pelupusan.disClass.DisAgihanTugas;

@UrlBinding("/penguatkuasaan/agihTugasanV2/{currentStage}")
public class AgihanTugasV2ActionBean extends AbleActionBean {

    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    private NoPt noPT;
    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean isBerangkai = Boolean.FALSE;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihanTugasV2ActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    //private boolean isBerhalang = Boolean.FALSE;
    private String nota;
    private String stageID;
    private String participant;
    @Inject
    PermohonanService permohonanService;
    @Inject
    EnfClassAgihanTugas enf;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    @Inject
    PelupusanService perlupusanService;
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
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    private PermohonanNota permohonanNota;
    private boolean statusNotaExist = Boolean.TRUE;

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
        // kene cater ikut peranan..
        // List<PenggunaPeranan> list = penggunaperananDao.findAll();
        listPp = new LinkedList<Pengguna>();
//
//        try {
//            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
//            for (Task t : l) {
//                stageID = t.getSystemAttributes().getStage();
//                participant = t.getSystemAttributes().getParticipantName();
//                logger.info("-----Stage ID:" + stageID + "-----");
//            }
//        } catch (Exception e) {
//            logger.error("error ::" + e.getMessage());
//        }

        /*
         * CALLING PENGGUNA METHOD
         */
        this.getListPguna(permohonan, kodNegeri);

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/agih_tugasanV2.jsp").addParameter("tab", "true");
    }

    /**
     *
     * @author Shazwan
     * @version 1.0 date 31/10/2011
     */
    private void getListPguna(Permohonan permohonan, String negeri) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("422") ? 1
                : permohonan.getKodUrusan().getKod().equals("423") ? 2
                : permohonan.getKodUrusan().getKod().equals("424") ? 3
                : permohonan.getKodUrusan().getKod().equals("425") ? 4
                : permohonan.getKodUrusan().getKod().equals("425A") ? 5
                : permohonan.getKodUrusan().getKod().equals("426") ? 6
                : permohonan.getKodUrusan().getKod().equals("427") ? 7
                : permohonan.getKodUrusan().getKod().equals("428") ? 8
                : permohonan.getKodUrusan().getKod().equals("429") ? 9
                : permohonan.getKodUrusan().getKod().equals("351") ? 10
                : permohonan.getKodUrusan().getKod().equals("352") ? 11
                : permohonan.getKodUrusan().getKod().equals("49") ? 12
                : permohonan.getKodUrusan().getKod().equals("127") ? 13
                : permohonan.getKodUrusan().getKod().equals("128") ? 14
                : permohonan.getKodUrusan().getKod().equals("129") ? 15
                : permohonan.getKodUrusan().getKod().equals("130") ? 16
                : permohonan.getKodUrusan().getKod().equals("999") ? 17
                : 0;
//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";
        listPp = enf.getPengguna(numUrusan, stageID, negeri, permohonan.getCawangan().getKod());

        System.out.println("size list pp :: " + listPp.size());

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

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageID = t.getSystemAttributes().getStage();

        } else {
            stageID = "agih_tugasan1";
        }

        logger.info("--------------stageID------------- : " + stageID);
        permohonanNota = enforcementService.findEmptyNotaMinit(mohon.getIdPermohonan(), stageID);
        logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota != null) {
            logger.info("::: kandungan nota :" + permohonanNota.getNota());
            statusNotaExist = false;
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
                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                stageId = task.getSystemAttributes().getStage();

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

                PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageID);
                if (nota != null) {
                    logger.info("::: update status nota to T = tidak aktif ::: ");
                    nota.setStatusNota('T');
                    enforceService.simpanNota(nota);
                }

//                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + "(" + pengguna.getNama() + ")" + " telah Berjaya.";
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

    public String getIdPermohonan() {
        return IdPermohonan;
    }

    public void setIdPermohonan(String IdPermohonan) {
        this.IdPermohonan = IdPermohonan;
    }

    public String getStageID() {
        return stageID;
    }

    public void setStageID(String stageID) {
        this.stageID = stageID;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }
}