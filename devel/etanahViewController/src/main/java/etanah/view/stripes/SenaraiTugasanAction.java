/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.service.dev.integrationflow.TugasanUtilitiService;
import etanah.view.daftar.utiliti.UtilitiTukarGantActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.tugasan.TugasanForm;
import etanah.workflow.WorkFlowService;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author fikri
 */
@UrlBinding("/senaraiTugasan")
public class SenaraiTugasanAction extends AbleActionBean {

    private Pengguna pengguna;
    private List<Task> tasks;
    private List<Task> tasks2;
    private String idInsert;
    private List<String> senaraiInsert;
    private String[] idList;
    private boolean red = Boolean.FALSE;
    private List<Map<String, String>> listValue;
    private IWorkflowContext wc;
    private String kodJabatan;
    private String kodSerah;
    private String berangkai;
    private String senarai;
    private Boolean flag = false;
    @Inject
    private PermohonanService permohonanService;
    @Inject
    private FasaPermohonanService mohonFasaService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TabManager manager;
    @Inject
            TugasanUtilitiService tugasanUtilitiService;
    List<TugasanForm> listPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
    private String kodNegeri;
    private static final Logger LOG = Logger.getLogger(SenaraiTugasanAction.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    private String[] URUSAN_TUKAR_GANTI = {"HSTHK", "HKTHK", "HMSC", "HTIR"};
    static final Comparator<PermohonanForm> TASK_ORDER = new Comparator<PermohonanForm>() {
        @Override
        public int compare(PermohonanForm t1, PermohonanForm t2) {

//                    Date d1 = t1.getInfoAudit().getTarikhMasuk();
//                    Date d2 = t2.getInfoAudit().getTarikhMasuk();
//
//                    if (d1.after(d2)) {
//                        return 1;
//                    } else if (d1.before(d2)) {
//                        return -1;
//                    } else {
//                        return 0;
//                    }

            if (t1.getBil() > t2.getBil()) {
                return 1;
            } else if (t1.getBil() < t2.getBil()) {
                return -1;
            } else {
                return 0;
            }

        }
    };
    static final Comparator<Map> TASK_ORDER3 = new Comparator<Map>() {
        @Override
        public int compare(Map t1, Map t2) {

//                    Date d1 = t1.getInfoAudit().getTarikhMasuk();
//                    Date d2 = t2.getInfoAudit().getTarikhMasuk();
//
//                    if (d1.after(d2)) {
//                        return 1;
//                    } else if (d1.before(d2)) {
//                        return -1;
//                    } else {
//                        return 0;
//                    }

            if (Long.parseLong(t1.get("bil").toString()) > Long.parseLong(t2.get("bil").toString())) {
                return -1;
            } else if (Long.parseLong(t1.get("bil").toString()) < Long.parseLong(t2.get("bil").toString())) {
                return 1;
            } else {
                return 0;
            }

        }
    };
    static final Comparator<Task> TASK_ORDER_2 = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {

            Date d1 = t1.getSystemAttributes().getAssignedDate().getTime();
            Date d2 = t2.getSystemAttributes().getAssignedDate().getTime();

            if (d1.after(d2)) {
                return 1;
            } else if (d1.before(d2)) {
                return -1;
            } else {
                return 0;
            }

        }
    };

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws WorkflowException {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();

        listValue = new ArrayList<Map<String, String>>();

        wc = WorkFlowService.authenticate(pengguna.getIdPengguna());
        
                listPermohonan = tugasanUtilitiService.findListTugasan2(ctx.getUser().getIdPengguna(),null,null);


    }

    public Resolution searchAllPermohonan() throws IllegalAccessException, InvocationTargetException {
        LOG.info("Pengguna Yang Menggunakan Senarai Tugasan Ialah " + pengguna.getIdPengguna());
        senaraiInsert = new ArrayList<String>();
        if (idInsert != null) {
            List<Permohonan> permohonans = permohonanService.findByIdSblm(idInsert);
            for (Permohonan mohon : permohonans) {
                String idInsert2 = mohon.getIdPermohonan();
                senaraiInsert.add(idInsert2);
            }

            senaraiInsert.add(idInsert);
        }

        try {
            doProcessTasksWithParameter();
            listPermohonan = tugasanUtilitiService.findListTugasan2(pengguna.getIdPengguna(),idInsert,null);
        } catch (WorkflowException ex) {
            addSimpleError(ex.toString());
        }
        flag = true;
        return new JSP("/common/senarai_tugasan2.jsp");
    }

    public Resolution doViewHm() {

        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanService.findById(idPermohonan);
            if (p != null) {
                senaraiHakmilik = p.getSenaraiHakmilik();
            }
        }
        return new JSP("/common/view_hakmilik_popup.jsp").addParameter("popup", "true");
    }

    @DefaultHandler
    public Resolution showForm() {
        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("/common/senarai_tugasan2.jsp");
    }

    public Resolution SearchAll() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pengguna = ctx.getUser();
        LOG.info("id Pengguna  =============================================================== " + pengguna.getIdPengguna());
        LOG.info("Nama Pengguna ================================================================ " + pengguna.getNama());

        try {
            String message = getContext().getRequest().getParameter("message");
            String error = getContext().getRequest().getParameter("error");
            if (StringUtils.isNotBlank(message)) {
                addSimpleMessage(message);
            }
            if (StringUtils.isNotBlank(error)) {
                addSimpleError(error);
            }
            flag = true;
            doProcessTasks();
        } catch (WorkflowException ex) {
            addSimpleError(ex.toString());
        }

        return new JSP("/common/senarai_tugasan2.jsp");
    }

    private void setPage(List senarai) {

        ParamEncoder encoder = new ParamEncoder("line");

        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        set__pg_total_records(senarai.size());
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        if (get__pg_max_records() > get__pg_total_records()) {
            set__pg_max_records(get__pg_total_records());
        }

    }

    private void doProcessTasks() throws WorkflowException {
        tasks = tasks = WorkFlowService.queryTasks(wc, pengguna.getKodCawangan().getKod());

        Collections.sort(tasks, TASK_ORDER_2);

        setPage(tasks);

        int bil = 0;

        String tindakan = "";

        if (!tasks.isEmpty()) {
            for (int i = get__pg_start(); i < get__pg_max_records(); i++) {

                Task task = (Task) tasks.get(i);
                if (task == null) {
                    continue;
                }

                String idPermohonan = task.getSystemMessageAttributes().getTextAttribute1();

                Permohonan p = permohonanService.findById(idPermohonan);

                if (p != null && StringUtils.isNotBlank(p.getStatus()) && p.getStatus().equals("SD")) {
                    continue;
                }

                StringBuilder mesej = new StringBuilder("");
                Map map = new HashMap();
                map.put("idPermohonan", idPermohonan);
                if (p != null && p.getPermohonanSebelum() != null) {
                    map.put("idPermohonanSebelum", p.getPermohonanSebelum().getIdPermohonan());
                }
//                map.put("idPermohonanSebelum", p.getPermohonanSebelum() != null
//                        ? p.getPermohonanSebelum().getIdPermohonan() : null);
                map.put("tajuk", (p != null && p.getKodUrusan() != null) ? p.getKodUrusan().getNama() : task.getTitle());
                if (p != null) {
                    KodUrusan ku = p.getKodUrusan();
                    map.put("status", p.getStatus() != null ? p.getStatus() : "");
                    map.put("proceed", Boolean.TRUE);
                    checkUrusanTukarganti(p, mesej);

                    if (mesej.length() > 0) {
                        map.put("tukarganti", mesej.toString());
                    }

                    if (ArrayUtils.contains(URUSAN_TUKAR_GANTI, ku.getKod())) {
                        if (StringUtils.isNotBlank(p.getUlasan())) { //temp solution !! for tukar ganti
                            Permohonan p2 = permohonanService.findById(p.getUlasan());
                            if (p2 != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Tukarganti untuk :")
                                        .append(p2.getIdPermohonan())
                                        .append(" (")
                                        .append(p2.getKodUrusan() != null ? p2.getKodUrusan().getNama() : "")
                                        .append(" )");

                                map.put("urusanSelepas", sb.toString());
                            }
                        }
                    }

                    bil = Integer.parseInt(mohonFasaService.getSasaranHari(p.getKodUrusan().getKod(), task.getSystemAttributes().getStage()));

                    if (bil == 0) {
                        bil = manager.getDaysToComplete(p.getKodUrusan().getIdWorkflow(), task.getSystemAttributes().getStage(),
                                p.getKodUrusan().getKod());
                    }

                    tindakan = manager.getCurrentAction(p.getKodUrusan().getIdWorkflow(),
                            task.getSystemAttributes().getStage());

                    FasaPermohonan fp = mohonFasaService.getCurrentStage(p.getIdPermohonan(), task.getSystemAttributes().getStage());
                    if (fp != null && StringUtils.isNotBlank(fp.getMesej())) {
                        map.put("mesej", fp.getMesej());
                    }
                } else {
                    bil = 0;
                    tindakan = "";
                }

                StringBuilder link = new StringBuilder(getContext().getRequest().getContextPath())
                        .append("/linkActionBean?idPermohonan=")
                        .append(idPermohonan)
                        .append("&taskId=")
                        .append(task.getSystemAttributes().getTaskId());

                map.put("link", link.toString());
                map.put("taskId", task.getSystemAttributes().getTaskId());

                if (task.getSystemAttributes().getAssignedDate() != null) {
                    Date sd = task.getSystemAttributes().getAssignedDate().getTime();
                    map.put("tarikhMula", sdf.format(sd));
                }
                Calendar ca = task.getSystemAttributes().getAssignedDate();
                long l = daysBetween(ca);
                ca.add(Calendar.DATE, bil);

                map.put("red", red);
                map.put("tindakan", tindakan);
                if (p.getKeputusan() != null) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("G") && p.getStatus().equalsIgnoreCase("AK")) {
                        GregorianCalendar da1 = new GregorianCalendar();
                        long milPerDay = 1000 * 60 * 60 * 24;
                        long d1 = da1.getTime().getTime();
                        long d2 = p.getTarikhKeputusan().getTime();

                        long difMil = d1 - d2;
                        long d = ((d2 - d1) / milPerDay);
                        if (d < 0) {
                            red = Boolean.TRUE;
                        } else {
                            red = Boolean.FALSE;
                        }

                        long l_ = (difMil / milPerDay);
                        map.put("bil", l_);
                    } else {
                        map.put("bil", l);
                    }
                } else {
                    map.put("bil", l);
                }
                map.put("tarikhTamat", sdf.format(ca.getTime()));

                listValue.add(map);

            }
        }

    }

    private void doProcessTasksWithParameter() throws WorkflowException, IllegalAccessException, InvocationTargetException {
        long startTime = System.currentTimeMillis();

        if (senaraiInsert.size() > 0) {
            for (String insert1 : senaraiInsert) {
                tasks = WorkFlowService.queryTasksByIdMohonBranch(wc, insert1, pengguna.getKodCawangan().getKod());
                Collections.sort(tasks, TASK_ORDER_2);
                List<etanah.model.Permohonan> list = permohonanService.getPermohonanByPermohonanSebelum(insert1);
                for (etanah.model.Permohonan p : list) {
                    if (p != null) {
                        List<Task> senaraiTask = WorkFlowService.queryTasksByIdMohonBranch(wc, p.getIdPermohonan(),
                                pengguna.getKodCawangan().getKod());
                        for (Task task : senaraiTask) {
                            tasks.add(task);
                        }
                    }
                }
            }
        } //        if (StringUtils.isNotBlank(idInsert)) {
        //            tasks = WorkFlowService.queryTasksByIdMohonBranch(wc, idInsert, pengguna.getKodCawangan().getKod());
        //            List<etanah.model.Permohonan> list = permohonanService.getPermohonanByPermohonanSebelum(idInsert);
        //            for (etanah.model.Permohonan p : list) {
        //                if (p != null) {
        //                    List<Task> senaraiTask
        //                            = WorkFlowService.queryTasksByIdMohonBranch(wc, p.getIdPermohonan(),
        //                                    pengguna.getKodCawangan().getKod());
        //                    for (Task task : senaraiTask) {
        //                        tasks.add(task);
        //                    }
        //                }
        //            }
        //        } 
        else {
            tasks = WorkFlowService.queryTasks(wc, pengguna.getKodCawangan().getKod());
            LOG.debug("tasks " + tasks.size());
            Collections.sort(tasks, TASK_ORDER_2);
        }

        if (!tasks.isEmpty()) {

            boolean isBerangkai = false;

            if (StringUtils.isNotBlank(berangkai)) {
                if ("Y".equals(berangkai)) {
                    isBerangkai = true;
                }
            }

            int bil = 0;

            String tindakan = "";

            List<String> listPermohonan = new ArrayList<String>();
            List<Map<String, Task>> mapTaskPermohonan = new ArrayList<Map<String, Task>>();

            for (Task task : tasks) {
                if (task == null) {
                    continue;
                }
                Map<String, Task> map = new HashMap<String, Task>();
                String idPermohonan = task.getSystemMessageAttributes().getTextAttribute1();
                listPermohonan.add(idPermohonan);
                map.put(idPermohonan, task);
                mapTaskPermohonan.add(map);
            }


            if (kodJabatan == null) {
                setPage(tasks);
                if (!tasks.isEmpty()) {


                    if (StringUtils.isNotBlank(berangkai)) {
                        if ("Y".equals(berangkai)) {
                            isBerangkai = true;
                        }
                    }



                    for (Task task : tasks) {
                        if (task == null) {
                            continue;
                        }
                        Map<String, Task> map = new HashMap<String, Task>();
                        String idPermohonan = task.getSystemMessageAttributes().getTextAttribute1();
                        listPermohonan.add(idPermohonan);
                        map.put(idPermohonan, task);
                        mapTaskPermohonan.add(map);
                    }

                    List<Permohonan> filterPermohonan = permohonanService.getPermohonanList(listPermohonan, kodJabatan, kodSerah, isBerangkai);

                    LOG.debug("tasks " + filterPermohonan.size());
                    setPage(filterPermohonan);

                    for (int i = get__pg_start(); i < get__pg_max_records(); i++) {
                        Permohonan p = filterPermohonan.get(i);
                        if (p == null) {
                            continue;
                        }
                        KodUrusan ku = p.getKodUrusan();
                        StringBuilder mesej = new StringBuilder("");
                        Map map = new HashMap();
                        map.put("idPermohonan", p.getIdPermohonan());
                        map.put("idPermohonanSebelum", p.getPermohonanSebelum() != null
                                ? p.getPermohonanSebelum().getIdPermohonan() : null);
                        map.put("tajuk", p.getKodUrusan().getNama());
                        map.put("status", p.getStatus() != null ? p.getStatus() : "");
                        map.put("proceed", Boolean.TRUE);
                        if (isBerangkai) {
                            map.put("idKump", p.getIdKumpulan());
                        }
                        checkUrusanTukarganti(p, mesej);
                        if (mesej.length() > 0) {
                            map.put("tukarganti", mesej.toString());
                        }

                        if (ArrayUtils.contains(URUSAN_TUKAR_GANTI, ku.getKod())) {
                            if (StringUtils.isNotBlank(p.getUlasan())) { //temp solution !! for tukar ganti
                                Permohonan p2 = permohonanService.findById(p.getUlasan());
                                if (p2 != null) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Tukarganti untuk :")
                                            .append(p2.getIdPermohonan())
                                            .append(" (")
                                            .append(p2.getKodUrusan() != null ? p2.getKodUrusan().getNama() : "")
                                            .append(" )");

                                    map.put("urusanSelepas", sb.toString());
                                }
                            }
                        }

                        for (Map<String, Task> m : mapTaskPermohonan) {
                            Task task = m.get(p.getIdPermohonan());
                            if (task != null) {
                                StringBuilder link = new StringBuilder(getContext().getRequest().getContextPath())
                                        .append("/linkActionBean?idPermohonan=")
                                        .append(p.getIdPermohonan())
                                        .append("&taskId=")
                                        .append(task.getSystemAttributes().getTaskId());

                                map.put("link", link.toString());
                                map.put("taskId", task.getSystemAttributes().getTaskId());

                                if (task.getSystemAttributes().getAssignedDate() != null) {
                                    Date sd = task.getSystemAttributes().getAssignedDate().getTime();
                                    map.put("tarikhMula", sdf.format(sd));
                                }

                                bil = manager.getDaysToComplete(p.getKodUrusan().getIdWorkflow(), task.getSystemAttributes().getStage(),
                                        p.getKodUrusan().getKod());

                                tindakan = manager.getCurrentAction(p.getKodUrusan().getIdWorkflow(),
                                        task.getSystemAttributes().getStage());

                                FasaPermohonan fp = mohonFasaService.getCurrentStage(p.getIdPermohonan(), task.getSystemAttributes().getStage());
                                if (fp != null && StringUtils.isNotBlank(fp.getMesej())) {
                                    map.put("mesej", fp.getMesej());
                                }

                                Calendar ca = task.getSystemAttributes().getAssignedDate();
                                long l = daysBetween(ca);
                                ca.add(Calendar.DATE, bil);

                                map.put("red", red);
                                map.put("tindakan", tindakan);
                                if (p.getKeputusan() != null) {
                                    if (p.getKeputusan().getKod().equalsIgnoreCase("G") && p.getStatus().equalsIgnoreCase("AK")) {
                                        GregorianCalendar da1 = new GregorianCalendar();
                                        long milPerDay = 1000 * 60 * 60 * 24;
                                        long d1 = da1.getTime().getTime();
                                        long d2 = p.getTarikhKeputusan().getTime();

                                        long difMil = d1 - d2;
                                        long d = ((d2 - d1) / milPerDay);
                                        if (d < 0) {
                                            red = Boolean.TRUE;
                                        } else {
                                            red = Boolean.FALSE;
                                        }

                                        long l_ = (difMil / milPerDay);
                                        map.put("bil", l_);
                                    } else {
                                        map.put("bil", l);
                                    }
                                } else {
                                    map.put("bil", l);
                                }
                                map.put("tarikhTamat", sdf.format(ca.getTime()));

                                break;
                            }
                        }
                        listValue.add(map);
                    }
                }

            } else {
                tasks2 = new ArrayList<Task>();
                
                for (Task task : tasks) {
                    if (task == null) {
                        continue;
                    }
                    Map<String, Task> map = new HashMap<String, Task>();
                    String idPermohonan = task.getSystemMessageAttributes().getTextAttribute1();
                    Permohonan p = permohonanService.findById(idPermohonan);
                    if (p.getKodUrusan().getJabatan().getKod().equals(kodJabatan)) {
                        tasks2.add(task);
                    }
                }
                setPage(tasks2);

                for (int i = get__pg_start(); i < get__pg_max_records(); i++) {

                    Task task = (Task) tasks2.get(i);
                    if (task == null) {
                        continue;
                    }

                    String idPermohonan = task.getSystemMessageAttributes().getTextAttribute1();

                    Permohonan p = permohonanService.findById(idPermohonan);

                    if (p != null && StringUtils.isNotBlank(p.getStatus()) && p.getStatus().equals("SD")) {
                        continue;
                    }
                    LOG.info("p.getKodUrusan().getJabatan().getNama() =" + p.getKodUrusan().getJabatan().getNama());
                    if (p.getKodUrusan().getJabatan().getKod().equals(kodJabatan)) {
                        StringBuilder mesej = new StringBuilder("");
                        Map map = new HashMap();
                        map.put("idPermohonan", idPermohonan);
                        if (p != null && p.getPermohonanSebelum() != null) {
                            map.put("idPermohonanSebelum", p.getPermohonanSebelum().getIdPermohonan());
                        }
//                map.put("idPermohonanSebelum", p.getPermohonanSebelum() != null
//                        ? p.getPermohonanSebelum().getIdPermohonan() : null);
                        map.put("tajuk", (p != null && p.getKodUrusan() != null) ? p.getKodUrusan().getNama() : task.getTitle());
                        if (p != null) {
                            KodUrusan ku = p.getKodUrusan();
                            map.put("status", p.getStatus() != null ? p.getStatus() : "");
                            map.put("proceed", Boolean.TRUE);
                            checkUrusanTukarganti(p, mesej);

                            if (mesej.length() > 0) {
                                map.put("tukarganti", mesej.toString());
                            }

                            if (ArrayUtils.contains(URUSAN_TUKAR_GANTI, ku.getKod())) {
                                if (StringUtils.isNotBlank(p.getUlasan())) { //temp solution !! for tukar ganti
                                    Permohonan p2 = permohonanService.findById(p.getUlasan());
                                    if (p2 != null) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Tukarganti untuk :")
                                                .append(p2.getIdPermohonan())
                                                .append(" (")
                                                .append(p2.getKodUrusan() != null ? p2.getKodUrusan().getNama() : "")
                                                .append(" )");

                                        map.put("urusanSelepas", sb.toString());
                                    }
                                }
                            }

                            bil = Integer.parseInt(mohonFasaService.getSasaranHari(p.getKodUrusan().getKod(), task.getSystemAttributes().getStage()));

                            if (bil == 0) {
                                bil = manager.getDaysToComplete(p.getKodUrusan().getIdWorkflow(), task.getSystemAttributes().getStage(),
                                        p.getKodUrusan().getKod());
                            }

                            tindakan = manager.getCurrentAction(p.getKodUrusan().getIdWorkflow(),
                                    task.getSystemAttributes().getStage());

                            FasaPermohonan fp = mohonFasaService.getCurrentStage(p.getIdPermohonan(), task.getSystemAttributes().getStage());
                            if (fp != null && StringUtils.isNotBlank(fp.getMesej())) {
                                map.put("mesej", fp.getMesej());
                            }
                        } else {
                            bil = 0;
                            tindakan = "";
                        }

                        StringBuilder link = new StringBuilder(getContext().getRequest().getContextPath())
                                .append("/linkActionBean?idPermohonan=")
                                .append(idPermohonan)
                                .append("&taskId=")
                                .append(task.getSystemAttributes().getTaskId());

                        map.put("link", link.toString());
                        map.put("taskId", task.getSystemAttributes().getTaskId());

                        if (task.getSystemAttributes().getAssignedDate() != null) {
                            Date sd = task.getSystemAttributes().getAssignedDate().getTime();
                            map.put("tarikhMula", sdf.format(sd));
                        }
                        Calendar ca = task.getSystemAttributes().getAssignedDate();
                        long l = daysBetween(ca);
                        ca.add(Calendar.DATE, bil);

                        map.put("red", red);
                        map.put("tindakan", tindakan);
                        if (p.getKeputusan() != null) {
                            if (p.getKeputusan().getKod().equalsIgnoreCase("G") && p.getStatus().equalsIgnoreCase("AK")) {
                                GregorianCalendar da1 = new GregorianCalendar();
                                long milPerDay = 1000 * 60 * 60 * 24;
                                long d1 = da1.getTime().getTime();
                                long d2 = p.getTarikhKeputusan().getTime();

                                long difMil = d1 - d2;
                                long d = ((d2 - d1) / milPerDay);
                                if (d < 0) {
                                    red = Boolean.TRUE;
                                } else {
                                    red = Boolean.FALSE;
                                }

                                long l_ = (difMil / milPerDay);
                                map.put("bil", l_);
                            } else {
                                map.put("bil", l);
                            }
                        } else {
                            map.put("bil", l);
                        }
                        map.put("tarikhTamat", sdf.format(ca.getTime()));

                        listValue.add(map);

                    }
                }
            }
//            Collections.sort(listValue, TASK_ORDER3);
        }
        LOG.debug("Time taken to generate senarai tugasan: " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    private void checkUrusanTukarganti(Permohonan permohonan, StringBuilder mesej) {
        if (permohonan == null) {
            return;
        }
//        String[] URUSAN_TUKAR_GANTI = {"HSTHK", "HKTHK", "HMSC", "HTIR"};

        String catatan = permohonan.getCatatan();
        if (StringUtils.isNotBlank(catatan)) {
            String[] ids = catatan.split(",");
            for (String id : ids) {
                Permohonan p = permohonanService.findById(id);
                if (p != null && p.getKodUrusan() != null) {
                    KodUrusan ku = p.getKodUrusan();
                    if (ArrayUtils.contains(URUSAN_TUKAR_GANTI, ku.getKod())) {
                        if (mesej.length() > 0) {
                            mesej.append("<br/>");
                        }
                        mesej.append("Ada Urusan TukarGanti ")
                                .append("<br/>")
                                .append(p.getIdPermohonan())
                                .append("<br/>")
                                .append(p.getKodUrusan().getNama())
                                .append("");
                        break;
                    }
                }
            }
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

    public Resolution process() {
        LOG.info("process");
        LOG.info("listHakmilik.size() : " + listHakmilik.size());
        LOG.info("listHakmilik.size() : " + senarai);

        return new RedirectResolution(UtilitiTukarGantActionBean.class, "janaTukarGanti").addParameter("idHakmilik", senarai);
    }

    public String getIdInsert() {
        return idInsert;
    }

    public void setIdInsert(String idInsert) {
        this.idInsert = idInsert;
    }

    public List<Map<String, String>> getListValue() {
        return listValue;
    }

    public void setListValue(List<Map<String, String>> listValue) {
        this.listValue = listValue;
    }

    public String getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(String kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    public String getBerangkai() {
        return berangkai;
    }

    public void setBerangkai(String berangkai) {
        this.berangkai = berangkai;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getKodNegeri() {
        return conf.getKodNegeri();
    }

    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public String getSenarai() {
        return senarai;
    }

    public void setSenarai(String senarai) {
        this.senarai = senarai;
    }

    public List<String> getSenaraiInsert() {
        return senaraiInsert;
    }

    public void setSenaraiInsert(List<String> senaraiInsert) {
        this.senaraiInsert = senaraiInsert;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks2() {
        return tasks2;
    }

    public void setTasks2(List<Task> tasks2) {
        this.tasks2 = tasks2;
    }

    public List<TugasanForm> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<TugasanForm> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }
    
    
}
