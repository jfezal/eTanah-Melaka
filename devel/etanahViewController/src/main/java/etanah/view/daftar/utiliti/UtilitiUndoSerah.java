package etanah.view.daftar.utiliti;

import able.stripes.JSP;

import com.google.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
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
import oracle.bpel.services.workflow.StaleObjectException;
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
 *
 * @author wan.fairul
 *
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/undo_serah")
public class UtilitiUndoSerah extends AbleActionBean {

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
  private static final Logger LOG = Logger.getLogger(UtilitiUndoSerah.class);
  private boolean form = false;
  private String stage;
  private String status;
  Pengguna pengguna;
  private Pengguna pguna;
  private List<Pengguna> listPp;
  @Inject
  FasaPermohonanService fasaPermohonanManager;
  IWorkflowContext ctx = null;
  private List<Permohonan> senaraiPermohonanBerangkai;
  private boolean isBerangkai = Boolean.FALSE;
  private String sebab;
  private static boolean isDebug = LOG.isDebugEnabled();

  @Inject
  public UtilitiUndoSerah(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
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

  @DefaultHandler
  public Resolution showForm() {
    return new JSP("daftar/utiliti/undo_perserahan.jsp");
  }

  @Before(stages = {LifecycleStage.BindingAndValidation})
  public void rehydrate() {

    pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
  }

  public Resolution checkPermohonan() throws WorkflowException {
//
//        if (StringUtils.isBlank( permohonan.getIdPermohonan()) ){
//            addSimpleError("Sila masukan ID Permohonan/Perserahan");
//            return new JSP("daftar/utiliti/undo_perserahan.jsp");
//        }
//        
//        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
//        if (permohonan != null) {
//            String status = permohonan.getStatus();
//            if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
//                addSimpleError("Urusan telah dibatalkan!");
//                return new JSP("daftar/utiliti/undo_perserahan.jsp");
//            } else if ("SL".equals(status)) {
//                addSimpleError("Urusan in telah selesai diproses. Keputusan telah dikeluarkan!");
//                return new JSP("daftar/utiliti/undo_perserahan.jsp");
//            } else if ("SS".equals(status)) {
//                addSimpleError("Urusan in telah disemak semula!");
//                return new JSP("daftar/utiliti/undo_perserahan.jsp");
//            } else {               
//                List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
//                if (!l.isEmpty()) {
//                    Task task = l.get(0);
//                    stage = task.getSystemAttributes().getStage();
//                    String currUser = task.getSystemAttributes().getAcquiredBy();
//                    loadPT(currUser);
//                }                 
//                form = true;
//            }
//        } else {
//            addSimpleError("Permohonan " + permohonan.getIdPermohonan() + " tidak wujud.");
//            return new JSP("daftar/utiliti/undo_perserahan.jsp");
//        }
//        
//        return new JSP("daftar/utiliti/undo_perserahan.jsp");


    if (idPermohonan == null) {
      addSimpleError("Sila masukkan ID Permohonan");
    } else {
      try {
        if (isDebug) {
          LOG.debug("ID Permohonan =" + idPermohonan);
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
        if (senaraiTask.isEmpty()) {

          if (permohonan == null) {
            addSimpleError("Permohonan tidak dijumpai.");
            return new JSP("daftar/utiliti/undo_perserahan.jsp");
          } else {
            if (permohonan.getStatus().equals("TK")) {
              addSimpleError("Urusan ini telah dibatalkan.");
              return new JSP("daftar/utiliti/undo_perserahan.jsp");
            } else {
              addSimpleError("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan.");
              return new JSP("daftar/utiliti/undo_perserahan.jsp");
            }
          }

        } else {
          if (isDebug) {
            LOG.debug("size = " + senaraiTask.size());
          }
          List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
          if (l != null) {
            for (Task t : l) {
              stage = t.getSystemAttributes().getStage();
              taskId = t.getSystemAttributes().getTaskId();
              String currUser = t.getSystemAttributes().getAcquiredBy();
              LOG.info(" /* Stage : " + stage + " , taskId : " + taskId + " , current user : " + currUser + " */");
              loadPT(currUser);
              if (permohonan != null) {
                if (stage.equalsIgnoreCase("keputusan")) {
                  addSimpleError("Urusan tidak boleh diagih");
                  return new JSP("daftar/utiliti/undo_perserahan.jsp");
                }

                status = permohonan.getStatus();
//                if ((stage.equalsIgnoreCase("keputusan")) || ("SS".equals(status))) {
                if ("SS".equals(status)) {
                  form = false;
                  addSimpleError("Urusan ini telah disemak semula");
                  return new JSP("daftar/utiliti/undo_perserahan.jsp");
                }
                form = true;
              } else {
                addSimpleError("Perserahan tidak dijumpai.");
                return new JSP("daftar/utiliti/undo_perserahan.jsp");
              }
            }
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
        LOG.error(ex);
        addSimpleError(ex.getMessage());
      }
    }
    return new JSP("daftar/utiliti/undo_perserahan.jsp");
  }

  public Resolution doAgihToPT() {
    LOG.info("/* DO AGIH PT */ ");
    try {
      List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
      LOG.info("--- l.size() : " + l.size());
      if (!l.isEmpty()) {
        IWorkflowContext admin = WorkFlowService.authenticateAdmin();
        LOG.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :"
                + permohonan.getIdPermohonan() + " kepada " + pengguna.getIdPengguna());

        Task task = l.get(0);

        taskId = task.getSystemAttributes().getTaskId();

        String currStage = task.getSystemAttributes().getStage();
        String currUser = task.getSystemAttributes().getAcquiredBy();
        LOG.info("--> currUser : " + currUser);
        LOG.info("--> currStage : " + currStage);
        if (StringUtils.isBlank(currUser)) {
          addSimpleError("Tugasan masih belum diambil oleh pengguna. Sila semak tugasan!");
          return new JSP("daftar/utiliti/undo_perserahan.jsp");
        }
        //todo to make it flexible
        ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        if (currStage.equalsIgnoreCase("keputusan")) {
          IWorkflowContext currContext = WorkFlowService.authenticate(currUser);
          WorkFlowService.updateTaskOutcome(taskId, currContext, "back");
        } else if (currStage.equalsIgnoreCase("kemasukan")) {
          task = WorkFlowService.releaseTask(admin, taskId);
        }
        task = WorkFlowService.acquireTask(taskId, ctx);
        LOG.info("--> task : " + task);
      }
      addSimpleMessage("Agihan Tugasan Berjaya.");
    } catch (Exception ex) {
      ex.printStackTrace();
      addSimpleError("Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
    }

    return new JSP("daftar/utiliti/undo_perserahan.jsp");
  }

  private void loadPT(String userId) {
    KodCawangan kod = pguna.getKodCawangan();
    listPp = new LinkedList<Pengguna>();
    List<Pengguna> lp = penggunaDao.findAll();
//    add by by azri 30/8/2013 : sort pengguna by name
    Comparator c = new Comparator<Pengguna>() {
      @Override
      public int compare(Pengguna p1, Pengguna p2) {
        String namaPguna1 = p1.getNama();
        String namaPguna2 = p2.getNama();
        return namaPguna1.compareTo(namaPguna2);
      }
    };
    Collections.sort(lp, c);

    for (Pengguna p : lp) {
      if (p.getKodCawangan().getKod().equals(kod.getKod())) {
        if (p.getIdPengguna().equals(userId)) {
          continue;
        }
        if (p.getPerananUtama() != null && p.getStatus() != null) {
          //TODO : change peranan utama
          if (p.getStatus().getKod().equals("A")) {
            if (p.getPerananUtama().getKod().equals("1")) {
              listPp.add(p);
            } else if (p.getSenaraiPeranan() != null
                    && !p.getSenaraiPeranan().isEmpty()) {
              for (PenggunaPeranan pp : p.getSenaraiPeranan()) {
                if (pp == null || pp.getPeranan() == null) {
                  continue;
                }
                if (pp.getPeranan().getKod().equals("1")) {
                  listPp.add(p);

                }
              }
            }
          }
        }
      }
    }
    LOG.info("LIST size------------" + listPp.size());
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
