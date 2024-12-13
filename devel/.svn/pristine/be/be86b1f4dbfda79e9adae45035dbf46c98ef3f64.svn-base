package etanah.view.notifikasi;


import etanah.dao.NotifikasiDAO;
import etanah.model.Notifikasi;
import etanah.service.NotifikasiService;
import able.stripes.JSP;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.InfoAudit;
import java.util.Date;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.BasicTabActionBean;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import net.sourceforge.stripes.action.StreamingResolution;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

@HttpCache(allow = false)
@UrlBinding("/mesej/{idMesej}")
public class NotifikasiAction extends AbleActionBean {

    private String idMesej;
    //private long idNotifikasi;
    private Notifikasi notifikasi;
    private List<Notifikasi> listNotifikasi;
    @Inject
    private NotifikasiService notifikasiService;
    @Inject
    private NotifikasiDAO notifikasiDAO;
    
     @Inject
    private TabManager tm;
    @Inject
    private PermohonanDAO pDAO;
    @Inject
    BasicTabActionBean bt;
    @Inject
    HakmilikUrusanService huService;

    private static final Logger log = Logger.getLogger(NotifikasiAction.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    public List listValue = new ArrayList();
    public String fromDate;
    public String untilDate;
    public String idInsert = new String();
    public String link = "";
    private String size = "";
    private List taskList;
    private String tajuk;
    private String penghantar;
    private boolean red = false;
    private String jabatan = null;
    static final long ONE_HOUR = 60 * 60 * 1000L;
    private boolean flag = true;
    private boolean flag2 = true;



    public void setIdMesej(String idMesej) {
        this.idMesej = idMesej;
    }

    public String getIdMesej() {
        return idMesej;
    }

    public void setNotifikasi(Notifikasi notifikasi) {
        this.notifikasi = notifikasi;
    }

    public Notifikasi getNotifikasi() {
        return notifikasi;
    }

    public void setListNotifikasi(List<Notifikasi> listNotifikasi) {
        this.listNotifikasi = listNotifikasi;
    }

    public List<Notifikasi> getListNotifikasi() {
        return listNotifikasi;
    }

    static final Comparator<Task> TASK_ORDER =
                                 new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {

            Date d1 = t1.getSystemAttributes().getAssignedDate().getTime();
            Date d2 = t2.getSystemAttributes().getAssignedDate().getTime();

            if (d1.after(d2)) return 1;
            else if (d1.before(d2)) return -1;
            else return 0;

        }
    };


    @DefaultHandler
    public Resolution viewMesejSaya() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        
        if (idMesej == null) {
            listNotifikasi = notifikasiService.findNotifikasiForPengguna(p);
            List<Notifikasi> tempList = new ArrayList<Notifikasi>();
            
            for (Notifikasi mesej : listNotifikasi) {
                mesej.setTelahDibaca('Y');
                InfoAudit ia = mesej.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new Date());                
                tempList.add(mesej);
            }
            notifikasiService.saveMultiple(tempList);
            return new JSP("notifikasi/list_mesej.jsp");
        } else {
            long id = Long.parseLong(idMesej);
            notifikasi = notifikasiDAO.findById(id);
            notifikasi.setTelahDibaca('Y');
            InfoAudit ia = notifikasi.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new Date());
            notifikasiService.save(notifikasi);
            return new JSP("notifikasi/view_mesej.jsp");
        }
    }
    
    public Resolution viewUnreadMessage() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        long unread = notifikasiService.findTotalNotifikasiForPengguna(ctx.getUser());
        
        return new StreamingResolution("text/plain", String.valueOf(unread));
    }

    public Resolution deleteSingle() {

        idMesej = getContext().getRequest().getParameter("idNotifikasi");
        notifikasi = notifikasiService.findByIdNotifikasi(Long.parseLong(idMesej));
        if (notifikasi != null) {
            notifikasiService.deleteAll(notifikasi);
        }
        return new RedirectResolution(NotifikasiAction.class, "viewMesejSaya");

    }



     public Resolution searchAllMesej() throws WorkflowException, ParseException {

            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Date d = null;
            Date d1 = null;

            if (fromDate != null) {
            d = sd.parse(fromDate);
            }
            if (untilDate != null) {

            String dateHingga = untilDate + " 23:59:59 PM";

            sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

            d1 = sd.parse(dateHingga);
            Calendar now = Calendar.getInstance();
            now.setTime(sd.parse(dateHingga));
            now.add(Calendar.DATE, 1);  // number of days to add
            d1 = now.getTime() ;


           } 
           listNotifikasi = notifikasiService.getNotifikasiList(tajuk, penghantar, d, d1, ctx.getUser());


       /* HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);

        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        if (idInsert != null) {
            taskList = WorkFlowService.queryTasksByIdMohonBranch(ctx, idInsert, pengguna.getKodCawangan().getKod());

            setMapIntoList();
        } else if (untilDate != null && fromDate != null) {
            //TODO :search from date and until date
            Date d1 = sd.parse(fromDate);
            String dateHingga = untilDate + " 23:59:59";
            sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date d2 = sd.parse(dateHingga);

            taskList = WorkFlowService.queryTasksByDate2(ctx, d1, d2, pengguna.getKodCawangan().getKod());

            setMapIntoList();
        } else if (fromDate != null) {
            Date d = sd.parse(fromDate);
            taskList = WorkFlowService.queryTasksByDate(ctx, d, pengguna.getKodCawangan().getKod());

            setMapIntoList();
        } else if (untilDate != null) {

            String dateHingga = untilDate + " 23:59:59";

            sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date d = sd.parse(dateHingga);

            taskList = WorkFlowService.queryTasksByDate3(ctx, d, pengguna.getKodCawangan().getKod());

            setMapIntoList();
        } else {
            setDefault();
        }*/
        return new JSP("notifikasi/list_mesej.jsp");
    }

     private void setDefault() throws WorkflowException {

        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());

        size = taskList.size() + " Tugasan";
        ses.setAttribute("size", size);
        listValue = new ArrayList();

        setMapIntoList();
    }

      private void setMapIntoList() {
        Map m = new HashMap();
        log.info("Bil Task :" + size);

        //disini saya cuba meningkat lg kemampuan sistem
        //dimana,  hanya mengambil rekod tertentu shj
        //contohnya, mukasurat 1, rekod dari 0 ke 9 shj, mukasurat 2, rekod 10 ke 19 dan seterusnya
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        set__pg_total_records(taskList.size());
        if (StringUtils.isNotBlank(page)) {
            set__pg_start( (Integer.parseInt(page) - 1) * get__pg_max_records() );
            set__pg_max_records ( get__pg_start() + get__pg_max_records() );
        }

        if ( get__pg_max_records() > get__pg_total_records() ) {
            set__pg_max_records (get__pg_total_records());
        }
        log.debug("[ dari rekod " + get__pg_start() + " ke rekod " + get__pg_max_records() + " ]");

        long startTime = System.currentTimeMillis();
        Collections.sort(listValue, TASK_ORDER);

        for (int i = get__pg_start(); i < get__pg_max_records(); i++) {
            log.debug("**********rekod [" + i + "]********************");
            Task impl = (Task) taskList.get(i);
//            flag = true;
            flag2 = true;
            bt.setDocumentGenerated(true);
//            log.info("Bil Task : " + taskList.size());
            String idM = impl.getSystemMessageAttributes().getTextAttribute1();
            Permohonan pMohon = pDAO.findById(idM);
            //untuk modul pelupusan. utk kod SD, tidak perlu papar dalam senarai tugasan!
            if (pMohon!= null && StringUtils.isNotBlank(pMohon.getStatus()) && pMohon.getStatus().equals("SD")) continue;
            log.info("Id Permohonan :" + idM);
            m = new HashMap();
            m.put("idPermohonan", idM);
            m.put("tajuk", impl.getTitle());
            log.info("Tajuk :" + impl.getTitle());
            if (impl.getSystemAttributes().getAssignedDate() != null) {
                Date sd = impl.getSystemAttributes().getAssignedDate().getTime();
                log.debug("tarikh mula = " + sd.toString());
                m.put("tarikhMula", sdf.format(sd));
            }
//            Date date = new Date();

            log.debug("pMohon is null ? " + (pMohon == null ? "true" : "false"));
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
                    if (f) flag = false;
//                    if (!f) flag = fg;
                }
                bil = tm.getDaysToComplete(pMohon.getKodUrusan().getIdWorkflow(), impl.getSystemAttributes().getStage(), pMohon.getKodUrusan().getKod());
                tindakan = tm.getCurrentAction(pMohon.getKodUrusan().getIdWorkflow(), impl.getSystemAttributes().getStage());
            } else {
                bil = 0;
            }

//            if (StringUtils.isNotBlank(jabatan)) {
//                HakmilikUrusan hu = huService.findByIdPerserahan(idM);
//                if (hu != null) {
//                    flag2 = false;
//                }
//            }

            Calendar ca = impl.getSystemAttributes().getAssignedDate();
            long l = daysBetween(ca);
            ca.add(Calendar.DATE,  bil);
            log.info("documentGenerate ?" + flag);
            m.put("documentGenerate", flag);
            m.put("proceed", flag2);
            m.put("tarikhTamat", sdf.format(ca.getTime()));
            m.put("red", red);
            m.put("tindakan", tindakan);
            m.put("bil", l);

            link = context.getRequest().getContextPath() + "/linkActionBean?idPermohonan=" + impl.getSystemMessageAttributes().getTextAttribute1() + "&taskId=" + impl.getSystemAttributes().getTaskId();
            log.info("link : " + link);
            m.put("link", link);
            m.put("taskId", impl.getSystemAttributes().getTaskId());
            listValue.add(m);
        }
        log.debug("Time taken to generate senarai tugasan: " + (System.currentTimeMillis()-startTime) + "ms.");
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getPenghantar() {
        return penghantar;
    }

    public void setPenghantar(String penghantar) {
        this.penghantar = penghantar;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }





}




