/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.BasicTabActionBean;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/daftar/withdraw")
public class WithdrawTaskActionBean extends AbleActionBean {

    private String idPerserahan;
    private Permohonan perserahan;
    private String taskId;
    private List taskList;
    public String link = "";
    private String size = "";
    public List listValue = new ArrayList();
    private boolean red = false;
    private String jabatan = null;
    static final long ONE_HOUR = 60 * 60 * 1000L;
    private boolean flag = true;
    private boolean flag2 = true;
    private static final Logger LOG = Logger.getLogger(WithdrawTaskActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    private TabManager tm;
    @Inject
    private PermohonanDAO pDAO;
    @Inject
    BasicTabActionBean bt;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private String idInsert;

    @DefaultHandler
    public Resolution showForm() throws WorkflowException {

        String message = getContext().getRequest().getParameter("message");
        String idInsert = getContext().getRequest().getParameter("idInsert");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        LOG.info("idInsert = " + idInsert);
        if (idInsert != null) {
            setDefault();
        }
        return new JSP("daftar/utiliti/senarai_tugasan_to_withdraw.jsp");
    }

    private void setDefault() throws WorkflowException {

        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//        wc = WorkFlowService.authenticate(pengguna.getIdPengguna());
//        taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());

        taskList = WorkFlowService.queryTasksByIdMohonBranch(ctx, idInsert, pengguna.getKodCawangan().getKod());

        size = taskList.size() + " Tugasan";
        ses.setAttribute("size", size);
        listValue = new ArrayList();

        setMapIntoList();
    }

    private void setMapIntoList() {
        Map m = new HashMap();
        //disini saya cuba meningkat lg kemampuan sistem
        //dimana,  hanya mengambil rekod tertentu shj
        //contohnya, mukasurat 1, rekod dari 0 ke 9 shj, mukasurat 2, rekod 10 ke 19 dan seterusnya
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        set__pg_total_records(taskList.size());
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        if (get__pg_max_records() > get__pg_total_records()) {
            set__pg_max_records(get__pg_total_records());
        }
        long startTime = System.currentTimeMillis();
        for (int i = get__pg_start(); i < get__pg_max_records(); i++) {
            Task impl = (Task) taskList.get(i);
//            flag = true;
            flag2 = true;
            bt.setDocumentGenerated(true);
//            log.info("Bil Task : " + taskList.size());
            String idM = impl.getSystemMessageAttributes().getTextAttribute1();
            Permohonan pMohon = pDAO.findById(idM);
            //untuk modul pelupusan. utk kod SD, tidak perlu papar dalam senarai tugasan!
            if (pMohon != null && StringUtils.isNotBlank(pMohon.getStatus()) && pMohon.getStatus().equals("SD")) {
                continue;
            }
            m = new HashMap();
            m.put("idPermohonan", idM);
            m.put("tajuk", impl.getTitle());
            if (impl.getSystemAttributes().getAssignedDate() != null) {
                Date sd = impl.getSystemAttributes().getAssignedDate().getTime();
                m.put("tarikhMula", sdf.format(sd));
            }
            int bil = 0;
            String tindakan = "";

            if (pMohon != null) {
                m.put("status", pMohon.getStatus() != null ? pMohon.getStatus() : "");
                //pMohon = pDAO.findById(idM);
                if (StringUtils.isBlank(jabatan)) {
                    jabatan = pMohon.getKodUrusan().getJabatanNama();
                    if (jabatan.equalsIgnoreCase("Pendaftaran")) {
                        getContext().getRequest().setAttribute("registration", Boolean.TRUE);
                    }
                }
                //check for not berangkai only
                if (StringUtils.isBlank(pMohon.getIdKumpulan())) {
                    bt.setIdWorkFlow(pMohon.getKodUrusan().getIdWorkflow());
                    bt.setTxnCode(pMohon.getKodUrusan().getKod());
                    bt.setStageId(impl.getSystemAttributes().getStage());
//                    boolean fg = bt.checkReport(idM, jabatan, null);
                    boolean f = tm.isDistribute(pMohon.getKodUrusan().getIdWorkflow(), impl.getSystemAttributes().getStage());
                    if (f) {
                        flag = false;
                    }
//                    if (!f) flag = fg;
                }
                bil = tm.getDaysToComplete(pMohon.getKodUrusan().getIdWorkflow(), impl.getSystemAttributes().getStage(), pMohon.getKodUrusan().getKod());
                tindakan = tm.getCurrentAction(pMohon.getKodUrusan().getIdWorkflow(), impl.getSystemAttributes().getStage());
            } else {
                bil = 0;
            }
            Calendar ca = impl.getSystemAttributes().getAssignedDate();
            long l = daysBetween(ca);
            ca.add(Calendar.DATE, bil);
            m.put("documentGenerate", flag);
            m.put("proceed", flag2);
            m.put("tarikhTamat", sdf.format(ca.getTime()));
            m.put("red", red);
            m.put("tindakan", tindakan);
            m.put("bil", l);

            link = context.getRequest().getContextPath() + "/linkActionBean?idPermohonan=" + impl.getSystemMessageAttributes().getTextAttribute1() + "&taskId=" + impl.getSystemAttributes().getTaskId();
            m.put("link", link);
            m.put("taskId", impl.getSystemAttributes().getTaskId());
            listValue.add(m);
        }
    }

    private long daysBetween(Calendar ca) {

        GregorianCalendar da1 = new GregorianCalendar();
        long milPerDay = 1000 * 60 * 60 * 24;
        long d1 = da1.getTime().getTime();
        long d2 = ca.getTime().getTime();
        long difMil = d1 - d2;
        long d = ((d2 - d1) / milPerDay);
        if (d < 0) {
            red = Boolean.TRUE;
        } else {
            red = Boolean.FALSE;
        }

        return (difMil / milPerDay);
    }

    public Resolution withdrawTask() throws WorkflowException {

        String error = "";
        String msg = "";
        String[] param = getContext().getRequest().getParameterValues("idPermohonan");
        for (String idPermohonan : param) {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            if (l != null) {
                for (Task t : l) {
                    taskId = t.getSystemAttributes().getTaskId();
                    if (StringUtils.isNotBlank(taskId)) {
                        try {
                            WorkFlowService.withdrawTask(taskId);
                            msg = "Withdraw Success!!";
                        } catch (StaleObjectException ex) {
                            ex.printStackTrace();
                            LOG.error(ex);
                            addSimpleError(ex.getMessage());
                            return new RedirectResolution(WithdrawTaskActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                        }
                    }
                }
            }
        }
        return new RedirectResolution(WithdrawTaskActionBean.class, "showForm");
    }

    public Resolution withdrawTaskWithTaskId() {
        String result = "0";
        taskId = getContext().getRequest().getParameter("taskId");
        if (StringUtils.isBlank(taskId)) {
            result = "1";
        } else {
            try {
                WorkFlowService.withdrawTask(taskId);
            } catch (Exception ex) {
                LOG.debug("ex {}", ex);
                result = "1";
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public Permohonan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(Permohonan perserahan) {
        this.perserahan = perserahan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List getListValue() {
        return listValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }

    public String getIdInsert() {
        return idInsert;
    }

    public void setIdInsert(String idInsert) {
        this.idInsert = idInsert;
    }

}
