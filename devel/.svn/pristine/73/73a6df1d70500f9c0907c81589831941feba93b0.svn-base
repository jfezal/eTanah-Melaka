package etanah.view.utility;

import able.stripes.JSP;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import able.stripes.AbleActionBean;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**

 * @author wan.fairul
 *
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/undo_mohon")
public class UtilitiUndoPermohonan extends AbleActionBean {

    private Permohonan permohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FasaPermohonanService fService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    private String kodUrusan;
    private String idHakmilik;
    private String taskId;
    private String idPermohonan;
    private Hakmilik hb;
    private List<FasaPermohonan> fasapermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private static final Logger LOG = Logger.getLogger(UtilitiUndoPermohonan.class);
    private boolean form = false;
    private String stage;
    Pengguna pengguna;
    private Pengguna pguna;
    private List<Pengguna> listPp;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    IWorkflowContext ctx = null;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean isBerangkai = Boolean.FALSE;
    private String sebab;
    private String peringkat;

    @Inject
    public UtilitiUndoPermohonan(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Hakmilik getHb() {
        return hb;
    }

    public void setHb(Hakmilik hb) {
        this.hb = hb;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanKemaskini() {
        return hakmilikPermohonanKemaskini;
    }

    public void setHakmilikPermohonanKemaskini(List<HakmilikPermohonan> hakmilikPermohonanKemaskini) {
        this.hakmilikPermohonanKemaskini = hakmilikPermohonanKemaskini;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<FasaPermohonan> getFasapermohonan() {
        return fasapermohonan;
    }

    public void setFasapermohonan(List<FasaPermohonan> fasapermohonan) {
        this.fasapermohonan = fasapermohonan;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("utiliti/undo_permohonan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Resolution checkPermohonan() throws WorkflowException {

        if (StringUtils.isBlank(permohonan.getIdPermohonan())) {
            addSimpleError("Sila masukan ID Permohonan");
            return new JSP("utiliti/undo_permohonan.jsp");
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        if (permohonan != null) {
            String status = permohonan.getStatus();
            if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
                addSimpleError("Urusan telah dibatalkan!");
                return new JSP("utiliti/undo_permohonan.jsp");
            } else if ("SL".equals(status)) {
                addSimpleError("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan!");
                return new JSP("utiliti/undo_permohonan.jsp");
            } else if ("SS".equals(status)) {
                addSimpleError("Urusan ini telah disemak semula!");
                return new JSP("utiliti/undo_permohonan.jsp");
            } else {
                List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                if (!l.isEmpty()) {
                    Task task = l.get(0);
                    stage = task.getSystemAttributes().getStage();
                    String currUser = task.getSystemAttributes().getAcquiredBy();
                    loadPT(currUser);
                }
                form = true;
            }
        } else {
            addSimpleError("Permohonan " + permohonan.getIdPermohonan() + " tidak wujud.");
            return new JSP("utiliti/undo_permohonan.jsp");
        }

        return new JSP("utiliti/undo_permohonan.jsp");
    }

    public Resolution doAgihToPT() {

        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

            if (!l.isEmpty()) {
                IWorkflowContext admin = WorkFlowService.authenticateAdmin();
                LOG.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :"
                        + permohonan.getIdPermohonan() + " kepada " + pengguna.getIdPengguna());

                Task task = l.get(0);

                taskId = task.getSystemAttributes().getTaskId();

                String currUser = task.getSystemAttributes().getAcquiredBy();

                if (StringUtils.isBlank(currUser)) {
                    addSimpleError("Tugasan masih belum diambil oleh pengguna. Sila semak tugasan!");
                    return new JSP("utiliti/undo_permohonan.jsp");
                }
                //todo to make it flexible
                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
                if (peringkat.equalsIgnoreCase("N")) {
                    IWorkflowContext currContext = WorkFlowService.authenticate(currUser);
                    WorkFlowService.updateTaskOutcome(taskId, currContext, "back");
                } else if (peringkat.contentEquals("Y")) {
                    task = WorkFlowService.releaseTask(admin, taskId);
                }
                task = WorkFlowService.acquireTask(taskId, ctx);
            }
            addSimpleMessage("Agihan Tugasan Berjaya.");
        } catch (Exception ex) {
            ex.printStackTrace();
            addSimpleError("Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        return new JSP("utiliti/undo_permohonan.jsp");
    }

    private void loadPT(String userId) {
        KodCawangan kod = pguna.getKodCawangan();
        listPp = new LinkedList<Pengguna>();
        List<Pengguna> lp = penggunaDao.findAll();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getIdPengguna().equals(userId)) {
                    continue;
                }
                if (p.getPerananUtama() != null && p.getStatus() != null) {
                    if (p.getStatus().getKod().equals("A")) {
                        listPp.add(p);
                    }
                }
            }
        }
    }

    public Resolution agihPT() throws WorkflowException {
        //TODO INTEGRATION WITH BPEL
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        StringBuilder sb = new StringBuilder();
        ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                LOG.info("TaskID ::" + taskID);
                LOG.info("idPermohonan ::" + idPermohonan);
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                LOG.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                Task task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                String stageId = task.getSystemAttributes().getStage();
                pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                permohonan = mohonDao.findById(idPermohonan);
                InfoAudit iaPermohonan = new InfoAudit();
                iaPermohonan = permohonan.getInfoAudit();
                iaPermohonan.setTarikhKemaskini(new java.util.Date());
                iaPermohonan.setDiKemaskiniOleh(pguna);
                permohonan.setInfoAudit(iaPermohonan);
                permohonan.setSebab(sebab);
                permohonanService.saveOrUpdate(permohonan);

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

                LOG.debug("cawangan " + pengguna.getKodCawangan());

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
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addSimpleError("Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            return showForm();
        }
        addSimpleMessage("Agihan Tugasan Berjaya.");
        return showForm();
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if (isBerangkai) {
            LOG.info("Urusan Berangkai");
            List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            LOG.info("taskList :: " + taskList.size());
            for (int i = 0; i < taskList.size(); i++) {
                Task impl = (Task) taskList.get(i);
                taskId = impl.getSystemAttributes().getTaskId();
                idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
                System.out.println("taskID::" + taskId);
                Task temp = WorkFlowService.getTask(taskId, ctx);

                if (temp.getSystemAttributes().getAcquiredBy() == null) {
                    WorkFlowService.acquireTask(taskId, ctx);
                }

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    LOG.info("idPermohonan :: " + idPermohonan);
                    LOG.info("p.idPermohonan :: " + p.getIdPermohonan());
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
            LOG.info("Urusan tidak berangkai");
            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            map = new HashMap<String, String>();
            map.put("idPermohonan", permohonan.getIdPermohonan());
            map.put("taskId", taskId);
            list.add(map);
        }
        return list;
    }
}
