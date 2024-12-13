package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/pelupusan/dalam_perhatian/{kodJbtn}")
public class UtilitiDalamPerhatianActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    private TaskDebugService taskDebugService;
    @Inject
    PelupusanService plpservice;
    @Inject
    PembangunanService pbservice;
    private String kodJbtn;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pihak pihakPemohon;
    private Pihak pihakPenerima;
    private Pihak pihakSearch;
    private String stage;
    private String tindakan;
    private String taskId;
    private String taskNumber;
    private String participant;
    private String acquiredBy;
    private String assignGroup;
    private String taskState;
    private String namaPemohon;
    private String namaPenerima;
    private String kodUrusan;
    public String fromDate;
    public String untilDate;
    private String stageId;
    private String nextStage;
    private String strKategoriPemohon;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    List<IdentityType> groupList = new ArrayList<IdentityType>();
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<HakmilikPermohonan> mohonHakmilikList = new ArrayList<HakmilikPermohonan>();
    List<Pihak> pihakList = new ArrayList<Pihak>();
    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
    List<Pengguna> listPengguna = new ArrayList<Pengguna>();
    private static final Logger LOG = Logger.getLogger(UtilitiDalamPerhatianActionBean.class);
    private Pengguna pengguna;
    private FasaPermohonan fasaPermohonanExist;
    private FasaPermohonan lastStageOfFasaPermohonan;
    private FasaPermohonan mohonf;
    private int size;
    IWorkflowContext ctxOnBehalf = null;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodJbtn = getContext().getRequest().getParameter("kodJbtn");

    }

    public Resolution search() throws ParseException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodJbtn = getContext().getRequest().getParameter("kodJbtn");
        if (StringUtils.isBlank(kodJbtn)) {
            addSimpleError("Sila pastikan kod Jabatan telah dimasukkan pada URI Utiliti ini");
        } else {
            if (permohonan == null && hakmilik == null && kodUrusan == null) {
                addSimpleError("Sila Masukkan ID Permohonan, ID Hakmilik atau Kod Urusan.");
            } else {

                if (permohonan != null) {
                    LOG.info("---CARIAN BY ID PERMOHONAN " + permohonan.getIdPermohonan());
                    permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                    if (permohonan != null) {
                        List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
                        hakmilikMohonlist = hakmilikPermohonanService.findIDMHByIDMohonPBA(permohonan.getIdPermohonan());
                        size = hakmilikMohonlist.size();

                        if (!hakmilikMohonlist.isEmpty()) {
                            for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
                                mohonHakmilikList.add(hakmilikPermohonan);
                            }
                        }
                    }
                } else if (hakmilik != null) {
                    LOG.info("---CARIAN BY No Lot " + hakmilik.getNoLot());
                    LOG.info("---kodJbtn " + kodJbtn);
                    List<HakmilikPermohonan> hakmilikMohonlist = new ArrayList<HakmilikPermohonan>();
                    hakmilikMohonlist = hakmilikPermohonanService.findByNoLotAndJabatan(hakmilik.getNoLot(), kodJbtn);
                    LOG.info("---hakmilikMohonlist " + hakmilikMohonlist.size());
                    size = hakmilikMohonlist.size();

                    if (!hakmilikMohonlist.isEmpty()) {
                        for (HakmilikPermohonan hakmilikPermohonan : hakmilikMohonlist) {
                            mohonHakmilikList.add(hakmilikPermohonan);
                        }
                    }
                }
//                else if (kodUrusan != null && StringUtils.isNotBlank(kodUrusan)) {
//                    LOG.info("---CARIAN BY KOD URUSAN " + kodUrusan + "Pengguna Kod Caw ==" + pengguna.getKodCawangan().getKod());
//
//                    permohonanList = plpservice.findPermohonanByKodUrusanByDaerah(kodUrusan, pengguna.getKodCawangan().getKod());
//                    //permohonanList = plpservice.findPermohonanByKodUrusan(kodUrusan, pengguna.getKodCawangan().getKod());
//                }

                if (mohonHakmilikList.isEmpty()) {
                    addSimpleError("Carian maklumat permohonan tidak dijumpai");
                } else {
                    addSimpleMessage("Carian maklumat permohonan berjaya.");
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    public Resolution perhatian() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan == null) {
            addSimpleError("Sila pilih permohonan");
        } else {
            permohonan = permohonanDAO.findById(idPermohonan);

            Map m = taskDebugService.traceTask(idPermohonan);
            stage = (String) m.get("stage");
            LOG.info("stage : " + stage);

            mohonf = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stage);
            InfoAudit infoAudit;
            if (permohonan != null) {
                infoAudit = permohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonan.setStatus("SD");
                plpservice.simpanPermohonan(permohonan);

            }

            addSimpleMessage("Permohonan " + permohonan.getIdPermohonan() + " telah disimpan dalam perhatian.");
        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    public Resolution teruskan() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan == null) {
            addSimpleError("Sila pilih permohonan");
        } else {
            permohonan = permohonanDAO.findById(idPermohonan);

            Map m = taskDebugService.traceTask(idPermohonan);
            stage = (String) m.get("stage");
            LOG.info("stage : " + stage);

            ArrayList kumpulanBpel = new ArrayList<String>();
            kumpulanBpel.add("PPTanah");

            listPengguna = plpservice.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
//        untuk find pengguna based on kumpulan bpel
//            String idPengguna = listPengguna.get(0).getIdPengguna();
            ctxOnBehalf = WorkFlowService.authenticate(peng.getIdPengguna());

            if (ctxOnBehalf != null) {
                System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                System.out.println("id mohon : " + permohonan.getIdPermohonan());

                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                LOG.info("1) Task FOund(size)::" + l.size());
                if (l.isEmpty()) {
                    try {
                        Thread.sleep(5000);
                        l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
                LOG.info("2) Task FOund (size)::" + l.size());
                for (Task t : l) {
                    stageId = t.getSystemAttributes().getStage();
                    taskId = t.getSystemAttributes().getTaskId();
                    LOG.debug("Claim Found Task::" + taskId);
                    if (t.getSystemAttributes().getAcquiredBy().isEmpty()) {
                        WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, stage);
                    }

                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "APPROVE"); //LS proceed


                    LOG.debug("stage id ::::::::::::::::" + stageId);
                    LOG.debug("next stage ::::::::::::::::" + nextStage);
                    LOG.debug("Tugasan dihantar ke : " + nextStage);
                }
                addSimpleMessage("Permohonan " + permohonan.getIdPermohonan() + " telah berjaya dihantar.");
            }

        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    public Resolution syor() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("id mohon : " + idPermohonan);
        if (idPermohonan == null) {
            addSimpleError("Sila pilih permohonan");
        } else {
            permohonan = permohonanDAO.findById(idPermohonan);

            Map m = taskDebugService.traceTask(idPermohonan);
            stage = (String) m.get("stage");
            LOG.info("stage : " + stage);

            ArrayList kumpulanBpel = new ArrayList<String>();
            kumpulanBpel.add("PPTanah");

            listPengguna = plpservice.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
//        untuk find pengguna based on kumpulan bpel
//        String idPengguna = listPengguna.get(0).getIdPengguna();
            ctxOnBehalf = WorkFlowService.authenticate(peng.getIdPengguna());

            if (ctxOnBehalf != null) {
                System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                System.out.println("id mohon : " + permohonan.getIdPermohonan());

                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                LOG.info("1) Task FOund(size)::" + l.size());
                if (l.isEmpty()) {
                    try {
                        Thread.sleep(5000);
                        l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
                LOG.info("2) Task FOund (size)::" + l.size());
                for (Task t : l) {
                    stageId = t.getSystemAttributes().getStage();
                    taskId = t.getSystemAttributes().getTaskId();

                    if (t.getSystemAttributes().getAcquiredBy().isEmpty()) {
                        WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                    }
                    LOG.debug("Claim Found Task::" + taskId);
                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "back"); //BC back


                    LOG.debug("stage id ::::::::::::::::" + stageId);
                    LOG.debug("next stage ::::::::::::::::" + nextStage);
                    LOG.debug("Tugasan dihantar ke : " + nextStage);
                }
                addSimpleMessage("Permohonan " + permohonan.getIdPermohonan() + " telah dihantar semula.");
            }

        }

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    public Resolution viewStatus() throws WorkflowException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Map m = taskDebugService.traceTask(idPermohonan);
        stage = (String) m.get("stage");
        LOG.info("stage : " + stage);
        tindakan = (String) m.get("tindakan");
        taskId = (String) m.get("taskID");
        taskNumber = "" + m.get("taskNumber");
        participant = (String) m.get("participant");
        acquiredBy = (String) m.get("acquiredBy");
        groupList = (List<IdentityType>) m.get("list_group");

        if (groupList != null) {
            assignGroup = groupList.get(0).getId();
        }



//        fasaPermohonanList = fasaPermohonanService.findStatus(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<PermohonanPihak> penerimaList = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "WAR", "TER");
        permohonanPihakList = penerimaList;

        fasaPermohonanExist = pbservice.findFasaPermohonanExist(idPermohonan);
        if (fasaPermohonanExist != null) {
            fasaPermohonanList = fasaPermohonanService.findStatusSelesai1(idPermohonan);
            lastStageOfFasaPermohonan = fasaPermohonanService.findLastStage(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            pemohonList = permohonan.getSenaraiPemohon();
            strKategoriPemohon = cariKategoriPemohonByIdPermohonan(permohonan);
        } else {
            addSimpleMessage("Tugasan Masih Belum Dicapai Oleh Pengguna.");
        }
//        for (PermohonanPihak perPihak : penerimaList) {
//            LOG.info("---Permohonan Pihak Size : " + penerimaList.size());
//            boolean flag = true;
//            if (permohonanPihakList.isEmpty()) {
//                permohonanPihakList.add(perPihak);
//            } else {
//                for (PermohonanPihak perPihak2 : permohonanPihakList) {
//                    
//                    if (perPihak.getJenis().getKod().equals(perPihak2.getJenis().getKod())) {
//                        flag = false;
//                    } else if (perPihak.getPihak().getIdPihak() == perPihak2.getPihak().getIdPihak()) {
//                        flag = false;
//                    } 
////                    else if (perPihak.getJenis().getKod().equals(perPihak2.getJenis().getKod())) {
////                        flag = false;
////                    }
//                }
//                if (flag) {
//                    permohonanPihakList.add(perPihak);
//                }
//            }
//        }

        pemohonList = permohonan.getSenaraiPemohon();
        strKategoriPemohon = cariKategoriPemohonByIdPermohonan(permohonan);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_status_popup.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        hakmilik = new Hakmilik();
        pihakPemohon = new Pihak();
        pihakPenerima = new Pihak();
        namaPemohon = null;
        namaPenerima = null;
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_dalam_perhatian.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihakPemohon() {
        return pihakPemohon;
    }

    public void setPihakPemohon(Pihak pihak) {
        this.pihakPemohon = pihak;
    }

    public List<Permohonan> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<Permohonan> permohonanList) {
        this.permohonanList = permohonanList;
    }

    public List<FasaPermohonan> getFasaPermohonanList() {
        return fasaPermohonanList;
    }

    public void setFasaPermohonanList(List<FasaPermohonan> fasaPermohonanList) {
        this.fasaPermohonanList = fasaPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pihak getPihakSearch() {
        return pihakSearch;
    }

    public void setPihakSearch(Pihak pihakSearch) {
        this.pihakSearch = pihakSearch;
    }

    public String getAcquiredBy() {
        return acquiredBy;
    }

    public void setAcquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public Pihak getPihakPenerima() {
        return pihakPenerima;
    }

    public void setPihakPenerima(Pihak pihakPenerima) {
        this.pihakPenerima = pihakPenerima;
    }

    public String getAssignGroup() {
        return assignGroup;
    }

    public void setAssignGroup(String assignGroup) {
        this.assignGroup = assignGroup;
    }

    public List<IdentityType> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<IdentityType> groupList) {
        this.groupList = groupList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getKodJbtn() {
        return kodJbtn;
    }

    public void setKodJbtn(String kodJbtn) {
        this.kodJbtn = kodJbtn;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getStrKategoriPemohon() {
        return strKategoriPemohon;
    }

    public void setStrKategoriPemohon(String strKategoriPemohon) {
        this.strKategoriPemohon = strKategoriPemohon;
    }

    public FasaPermohonan getFasaPermohonanExist() {
        return fasaPermohonanExist;
    }

    public void setFasaPermohonanExist(FasaPermohonan fasaPermohonanExist) {
        this.fasaPermohonanExist = fasaPermohonanExist;
    }

    public FasaPermohonan getLastStageOfFasaPermohonan() {
        return lastStageOfFasaPermohonan;
    }

    public void setLastStageOfFasaPermohonan(FasaPermohonan lastStageOfFasaPermohonan) {
        this.lastStageOfFasaPermohonan = lastStageOfFasaPermohonan;
    }

    public List<HakmilikPermohonan> getMohonHakmilikList() {
        return mohonHakmilikList;
    }

    public void setMohonHakmilikList(List<HakmilikPermohonan> mohonHakmilikList) {
        this.mohonHakmilikList = mohonHakmilikList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FasaPermohonan getMohonf() {
        return mohonf;
    }

    public void setMohonf(FasaPermohonan mohonf) {
        this.mohonf = mohonf;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    private String cariKategoriPemohonByIdPermohonan(Permohonan permohonan) {
        List<Dokumen> listDok = plpservice.cariKategoriPemohonByIdPermohonan(permohonan.getIdPermohonan());
        List<UrusanDokumen> listUrusanDok = plpservice.cariUrusanDokumenByKodUrusan(permohonan.getKodUrusan().getKod());
        boolean foundIndividu = false;
        boolean foundSyarikat = false;
        String katPemohon = "";

        for (Dokumen dokumenD : listDok) {
            if (foundIndividu) {
                break;
            }
            if (foundSyarikat) {
                break;
            }

            if (!foundIndividu && !foundSyarikat) {
                for (UrusanDokumen uDok : listUrusanDok) {
                    if (dokumenD.getKodDokumen().equals(uDok.getKodDokumen()) && uDok.getKategoriPemohon() == 'I') {
                        foundIndividu = true;
                        katPemohon = "Individu";
                        break;
                    }
                    if (dokumenD.getKodDokumen().equals(uDok.getKodDokumen()) && uDok.getKategoriPemohon() == 'O') {
                        foundSyarikat = true;
                        katPemohon = "Organisasi/Syarikat";
                        break;
                    }

                }

            }
        }
        return katPemohon;
    }
}
