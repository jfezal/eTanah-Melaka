/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.guice.AbleContextListener;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.JabatanConstants;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodUrusan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodKlasifikasi;
import etanah.model.Notifikasi;
import etanah.service.NotifikasiService;
import etanah.service.common.PermohonanService;
import etanah.view.workflow.KernelActionBean;
import java.util.Date;
import java.util.HashMap;
import net.sourceforge.stripes.action.HandlesEvent;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author md.nurfikri
 */
@HttpCache(allow = false)
@UrlBinding("/stage")
public class StageActionBean extends AbleActionBean {

    @Inject
    TabManager tabManager;
    @Inject
    PermohonanDAO permohananDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    etanah.Configuration conf;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    EnforcementService enforcementService;

    @Inject
    NotifikasiService notifikasiService;

    private String kodUrusan;
    private String workflowId;
    private Permohonan permohonan;
    private String idPermohonan;
    private BPelService service;
    private Pengguna pengguna;
    private String taskId;
    private String stageId;
    Task task = null;
    private String idFolder = "";
    private static final Logger LOGGER = Logger.getLogger(StageActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    private boolean isThereAnyResult = Boolean.TRUE;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean advanceRule = false;
    private static String NEXT_STAGE = "APPROVE";
    private static String WITHDRAW_TASK = "_WORKFLOW_DIRECTIVE_WITHDRAW";
    private String[] JABATAN_TO_BYPASS = {
        JabatanConstants.LELONG,
        JabatanConstants.PELUPUSAN,
        JabatanConstants.PENGUATKUASAAN,
        JabatanConstants.CONSENT,
        JabatanConstants.PEMBANGUNAN,
        JabatanConstants.STRATA, // added by zadirul for strata use
        JabatanConstants.PENGAMBILAN
    };

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/main.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!nextStageBerkumpulan", "!genReportBerkumpulan"})
    public void rehydrate() {
        service = new BPelService();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (StringUtils.isBlank(idPermohonan)) {
            idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        }

        permohonan = permohananDAO.findById(idPermohonan);

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }

        task = service.getTaskFromBPel(taskId, pengguna);

        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }

        if (permohonan != null) {
            KodUrusan ku = permohonan.getKodUrusan();
            kodUrusan = ku.getKod();
            workflowId = ku.getIdWorkflow();
            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            advanceRule = tabManager.advanceRule(workflowId, stageId);
        }

        if (isDebug) {
            LOGGER.debug("kod urusan = " + kodUrusan);
            LOGGER.debug("stage id = " + stageId);
            LOGGER.debug("workflow id = " + workflowId);
            LOGGER.debug("folder id = " + idFolder);
            LOGGER.debug("advanceRule = " + advanceRule);
        }
    }

    @HandlesEvent("nextStage")
    public Resolution keStageSeterusnya() throws Exception {
        Long t = System.currentTimeMillis();

        if (isDebug) {
            LOGGER.debug("start @ = " + t);
        }

        String event = getContext().getRequest().getParameter("event");
        String mesej = getContext().getRequest().getParameter("mesej");
        if (StringUtils.isNotBlank(event)) {
            NEXT_STAGE = event;
        }

        StringBuilder msg = new StringBuilder();
        StringBuilder url = new StringBuilder("senaraiTugasan?");
        String finalResult = "";

        StageContextImpl stageImp = null;
        List<String> validators = new ArrayList<String>();
        List<Map<String, String>> finalMap = new ArrayList<Map<String, String>>();
        String strByPass = getContext().getRequest().getParameter("bypass");
        boolean bypass = Boolean.FALSE;
        if (StringUtils.isNotBlank(strByPass)) {
            bypass = Boolean.parseBoolean(strByPass);
        }
        boolean isFinalStage = tabManager.isFinalStage(workflowId, stageId);
        String jabatanTerlibat = "";
        if (permohonan != null) {

            //pendaftaran
            String server = conf.getProperty("signed.required");
            jabatanTerlibat = permohonan.getKodUrusan().getJabatan().getKod();

            if (StringUtils.isNotBlank(server) && server.equals("true")) { //production mode ? tmp only
                String reslt = "";
                if (permohonan.getKeputusan() != null) {
                    reslt = permohonan.getKeputusan().getKod();
                }
                if (jabatanTerlibat.equals(JabatanConstants.PENDAFTARAN)
                        && stageId.equals("keputusan") //pendaftar shj
                        && NEXT_STAGE.equals("APPROVE") && StringUtils.isNotBlank(reslt)
                        && reslt.equals("D")) {
                    List<KandunganFolder> senaraiDok = permohonan.getFolderDokumen().getSenaraiKandungan();
                    for (KandunganFolder kf : senaraiDok) {
                        if (kf == null) {
                            continue;
                        }
                        Dokumen d = kf.getDokumen();
                        if (d.getKodDokumen().getKod().equals("DHDE")
                                && StringUtils.isNotBlank(d.getNamaFizikal())) {
                            LOGGER.debug(">>>>>>>>>> file utk sign :" + d.getNamaFizikal());
                            String docPath = conf.getProperty("document.path");
                            String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
                            File signFile = new File(path + ".sig");
                            if (!signFile.exists()) {
                                return new StreamingResolution("text/plain", "senaraiTugasan?error=Dokumen tidak ditandatangani. "
                                        + "Sila tandatangan dokumen sebelum ke peringkat seterusnya. Fail yang berkenaan :" + d.getNamaFizikal());
                            }
                        }
                        if (d.getKodDokumen().getKod().equals("DHKE")
                                && StringUtils.isNotBlank(d.getNamaFizikal())) {
                            LOGGER.debug(">>>>>>>>>> file utk sign :" + d.getNamaFizikal());
                            String docPath = conf.getProperty("document.path");
                            String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
                            File signFile = new File(path + ".sig");
                            if (!signFile.exists()) {
                                return new StreamingResolution("text/plain", "senaraiTugasan?error=Dokumen tidak ditandatangani. "
                                        + "Sila tandatangan dokumen sebelum ke peringkat seterusnya. Fail yang berkenaan :" + d.getNamaFizikal());
                            }
                        }
                    }
                }
            }

            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
            validators = tabManager.getValidatorClass(workflowId, stageId, kodUrusan);

            if (NEXT_STAGE.equals("APPROVE")) {

                isThereAnyResult = tabManager.anyKeputusanToPerform(workflowId, stageId, kodUrusan);
                if (isThereAnyResult) {
                    if (!fasaPermohonanService.checkKeputusan(idPermohonan, pengguna.getIdPengguna(), null, stageId)) {

                        return new StreamingResolution("text/plain", "2");

                    }
                } else {
                    //edited again by rajib//

                    if (jabatanTerlibat.equals(JabatanConstants.PEMBANGUNAN) && conf.getProperty("kodNegeri").equals("05")) {
                        if (permohonan.getKodUrusan().getKod().equals("RLTB") || permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RPS")) {
                            if (stageId == "sediakertasringkas3bln" || stageId == "sediaderafmmk") {
                                if (permohonan.getPermohonanSebelum() == null) {
                                    LOGGER.debug("idsebelum--->" + permohonan.getPermohonanSebelum());
                                    return new StreamingResolution("text/plain", "5");
                                }
                            }
                        }
                    }

                }

                LOGGER.debug("is final stage = " + isFinalStage);
                if (isFinalStage && permohonan.getKeputusan() != null) {
                    finalResult = String.valueOf(permohonan.getKeputusan().getKod());
                } else {
                    FasaPermohonan fp = fasaPermohonanService.getKeputusan(idPermohonan, pengguna.getIdPengguna(), stageId);
                    if (fp != null && fp.getKeputusan() != null) {
                        finalResult = String.valueOf(fp.getKeputusan().getKod());
                    }
                }
                if (isDebug) {
                    LOGGER.debug("final result = " + finalResult);
                }

                StringBuilder sb = new StringBuilder();
                for (String validator : validators) {
                    Class clazz = Class.forName(validator);
                    Object obj = clazz.newInstance();
                    if (obj instanceof StageListener) {
                        LOGGER.info("instance of StageListener [ " + obj.getClass().getSimpleName() + " ]");
                        StageListener q = (StageListener) obj;
                        AbleContextListener.getInjector().injectMembers(obj);
                        stageImp = (StageContextImpl) createStageContext(permohonan, pengguna, bypass);

                        try {
                            finalResult = q.beforeComplete(stageImp, finalResult);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return new StreamingResolution("text/plain", "senaraiTugasan?error=Sila Hubungi Pentadbir Sistem ["
                                    + ex.toString() + "]");
                        }

                        LOGGER.info("stage listener:: finish");
                        LOGGER.debug("returnResult ::" + finalResult);
                        for (String string : stageImp.messages) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(string);
                        }
                        if (StringUtils.isBlank(finalResult)) {
                            if (sb.toString().indexOf("Terdapat urusan sebelum yang masih belum selesai") >= 0) {
                                addSimpleError(sb.toString());
                                return new StreamingResolution("text/plain", "daftar/senarai_keutamaan?toByPassKeutamaan");
                            } else {
                                if (finalResult == null) {
                                    return new StreamingResolution("text/plain", "senaraiTugasan?error=" + sb.toString());
                                } else {
                                    boolean f = tabManager.isValidKeputusan(workflowId, stageId, kodUrusan, finalResult);
                                    if (!f) {
                                        return new StreamingResolution("text/plain", "senaraiTugasan?error=Keputusan Tidak Sah.");
                                    }
                                }
                            }
                        } else {
                            //withdraw task..
                            if (finalResult.equals(WITHDRAW_TASK)) {
                                WorkFlowService.withdrawTask(taskId);
                                return new StreamingResolution("text/plain", "senaraiTugasan?message=" + sb.toString());
                            }
                            // 2 - (For pelupusan) add w.fairul
                            if (!ArrayUtils.contains(JABATAN_TO_BYPASS, jabatanTerlibat)) {
                                boolean f = tabManager.isValidKeputusan(workflowId, stageId, kodUrusan, finalResult);
                                if (!f) {
                                    return new StreamingResolution("text/plain", "senaraiTugasan?error=Sila Semak Keputusan.");
                                }
                            }
                        }
                    }
                }
            }

            String idKump = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKump)) {
                List<Map<String, String>> list = getPermohonanWithTaskID(ctx, idKump);
                for (Map<String, String> map : list) {
                    taskId = map.get("taskId");
                    idPermohonan = map.get("idPermohonan");
                    permohonan = permohonanService.findById(idPermohonan);
                    if (permohonan != null && permohonan.getKeputusan() != null) {
                        finalResult = permohonan.getKeputusan().getKod();
                    }

                    if (msg.length() > 0) {
                        msg.append(",");
                    }
                    msg.append(idPermohonan);

                    Map peta = new HashMap();
                    peta.put("taskId", taskId);
                    peta.put("result", finalResult);
                    peta.put("idPermohonan", idPermohonan);
                    peta.put("rujukan", stageImp != null ? stageImp.getNoRujukan() : "");
                    finalMap.add(peta);
                }
            } else {
                if (msg.length() > 0) {
                    msg.append(",");
                }
                msg.append(idPermohonan);
                Map peta = new HashMap();
                peta.put("taskId", taskId);
                peta.put("result", finalResult);
                peta.put("idPermohonan", idPermohonan);
                peta.put("rujukan", stageImp != null ? stageImp.getNoRujukan() : "");
                finalMap.add(peta);
            }

//            String idKump = permohonan.getIdKumpulan();
//            if (StringUtils.isNotBlank(idKump)) {
//                List<Map<String, String>> list = getPermohonanWithTaskID(ctx, idKump);
//                for (Map<String, String> map : list) {
//                    taskId = map.get("taskId");
//                    idPermohonan = map.get("idPermohonan");
//                    if (msg.length() > 0) {
//                        msg.append(",");
//                    }
//                    msg.append(idPermohonan);
//                    permohonan = permohananDAO.findById(idPermohonan);
//                    if (permohonan == null) {
//                        return new StreamingResolution("text/plain", "1");
//                    }
//                    KodUrusan ku = permohonan.getKodUrusan();
//                    kodUrusan = ku.getKod();
//                    workflowId = ku.getIdWorkflow();
//                    idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
//                    task = service.getTaskFromBPel(taskId, pengguna);
//                    if (task == null) {
//                        return new StreamingResolution("text/plain", "1");
//                    }
//                    stageId = task.getSystemAttributes().getStage();
//                    if (StringUtils.isBlank(stageId)) {
//                        return new StreamingResolution("text/plain", "1");
//                    }
//
//                    if (NEXT_STAGE.equals("APPROVE")) {
//                        isThereAnyResult = tabManager.anyKeputusanToPerform(workflowId, stageId, kodUrusan);
//                        if (isThereAnyResult) {
//                            if (!fasaPermohonanService.checkKeputusan(idPermohonan, pengguna.getIdPengguna(), null, stageId)) {
//                                return new StreamingResolution("text/plain", "2");
//                            }
//                        }
//
//                        boolean isFinalStage = tabManager.isFinalStage(workflowId, stageId);
//                        if (permohonan.getKeputusan() != null && isFinalStage) {
//                            finalResult = String.valueOf(permohonan.getKeputusan().getKod());
//                        } else {
//                            FasaPermohonan fp = fasaPermohonanService.getKeputusan(idPermohonan, pengguna.getIdPengguna(), stageId);
//                            if (fp != null && fp.getKeputusan() != null) {
//                                finalResult = String.valueOf(fp.getKeputusan().getKod());
//                            }
//                        }
//
//                        validators = tabManager.getValidatorClass(workflowId, stageId, kodUrusan);
//                        StringBuilder sb = new StringBuilder();
//                        for (String validator : validators) {
//                            Class clazz = Class.forName(validator);
//                            Object obj = clazz.newInstance();
//                            if (obj instanceof StageListener) {
//                                LOGGER.info("stage listener:: begin");
//                                StageListener q = (StageListener) obj;
//                                AbleContextListener.getInjector().injectMembers(obj);
//                                stageImp = (StageContextImpl) createStageContext(permohonan, pengguna, bypass);
//                                finalResult = q.beforeComplete(stageImp, finalResult);
//                                LOGGER.info("stage listener:: finish");
//                                LOGGER.debug("returnResult ::" + finalResult);
//                                for (String string : stageImp.messages) {
//                                    if (sb.length() > 0) {
//                                        sb.append(",");
//                                    }
//                                    sb.append(string);
//                                }
//                                if (finalResult == null) {
//
//                                    if (sb.toString().indexOf("Terdapat urusan sebelum yang masih belum selesai") >= 0) {
//                                        addSimpleError(sb.toString());
//                                        return new StreamingResolution("text/plain", "daftar/senarai_keutamaan?toByPassKeutamaan");
//                                    } else {
//                                        return new StreamingResolution("text/plain", "senaraiTugasan?error=" + sb.toString());
//                                    }
//                                }
//
//                                boolean f = tabManager.isValidKeputusan(workflowId, stageId, kodUrusan, finalResult);
//                                if (!f) {
//                                    return new StreamingResolution("text/plain", "senaraiTugasan?error=Keputusan Tidak Sah.");
//                                }
//                            }
//                        }
//                    }
//
//                    Map peta = new HashMap();
//                    peta.put("taskId", taskId);
//                    peta.put("result", finalResult);
//                    peta.put("idPermohonan", idPermohonan);
//                    peta.put("rujukan", stageImp != null ? stageImp.getNoRujukan() : "");
//                    finalMap.add(peta);
//                }
//            } else {
//            }
            StringBuilder m = new StringBuilder();
            if (stageImp != null) {
                for (String string : stageImp.messages) {
                    m.append(string);
                }
            }
            StringBuilder pushBackMessage = new StringBuilder();

            List<FasaPermohonanLog> senaraiFasaLog = new ArrayList<FasaPermohonanLog>();
            try {
                //workflow function               

                for (Map<String, String> map : finalMap) {

                    InfoAudit ia = new InfoAudit();

                    String nextStage = "";
                    taskId = map.get("taskId");
                    finalResult = map.get("result");
                    idPermohonan = map.get("idPermohonan");
                    String noRujukan = map.get("rujukan");
                    permohonan = permohonanService.findById(idPermohonan);

                    if (isDebug) {
                        LOGGER.debug("taskId = " + taskId);
                        LOGGER.debug("idPermohonan = " + idPermohonan);
                    }

                    //add code result for advance rules (temporary)
//                System.out.println("finalResult  : " + finalResult);
//                System.out.println("stageId  : " + stageId);
//                System.out.println("advanceRule  : " + advanceRule);
                    if (StringUtils.isNotBlank(finalResult)) {
                        if (finalResult.equalsIgnoreCase("SS")
                                || finalResult.equalsIgnoreCase("G")
                                || advanceRule) {
                            LOGGER.debug("advance rule");

                            if (NEXT_STAGE.equals("APPROVE")) {

                                //For Penguatkuasaan module
                                //normal send to next stage 1: advance rule
                                if (jabatanTerlibat.equals(JabatanConstants.PENGUATKUASAAN) && conf.getProperty("kodNegeri").equals("04")) {
                                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                                        if (stageId.equalsIgnoreCase("kaji_ks")) {
                                            //Send tugasan based on user & keputusan : kaji_ks
                                            LOGGER.debug("Penguatkuasaan ::::: for stage : " + stageId + " & keputusan : " + finalResult);

                                            if (finalResult.equalsIgnoreCase("LK")) {
                                                //For keputusan : LK = Lengkap
                                                LOGGER.debug("Penguatkuasaan ::::: For keputusan : LK = Lengkap");
                                                PegawaiPenyiasat pegawaiSiasat = enforcementService.findPengguna(permohonan.getIdPermohonan());
                                                Task task2 = WorkFlowService.reassignTask(ctx, taskId, pegawaiSiasat.getNamaJabatan(), "user", finalResult);
                                                nextStage = task2.getSystemAttributes().getStage();
                                            } else {
                                                //For keputusan : TL = Tidak Lengkap
                                                LOGGER.debug("Penguatkuasaan ::::: For keputusan : TL = Tidak Lengkap");
                                                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, finalResult);
                                            }

                                            permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);
                                        } else {
                                            //For 427 & 428 except stage kaji_ks
                                            LOGGER.debug("Penguatkuasaan ::::: for other stage : " + stageId + " & keputusan : " + finalResult);
                                            nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                    ctx, finalResult);
                                        }

                                    } else {
                                        //For others seksyen (Enforcement, Melaka) & except stage kaji_ks
                                        nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                ctx, finalResult);
                                    }
                                } else {
                                    //For others module
                                    if (permohonan.getKodUrusan().getKod().equals("BACT")) {
                                        if (finalResult.equals("T")) {
                                            finalResult = "L";
                                        }
                                        nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                ctx, finalResult);
                                    } else {
                                        nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                ctx, finalResult);
                                    }

                                }

                                //For pelupusan by afham
                                if (jabatanTerlibat.equals(JabatanConstants.PELUPUSAN) && conf.getProperty("kodNegeri").equals("04")) {
                                    LOGGER.info("Next stage : " + nextStage);
                                    if (nextStage == null) {
                                        LOGGER.debug("Ke seterusnya");
                                    } else {
                                        LOGGER.debug("Ke seterusnya");
                                        if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PPRU")) {
                                            if (nextStage.equals("agihan_tugas2") && finalResult.equals("TA")) {
                                                LOGGER.info("Skip one stage");
                                                List<Task> taskList = null;
                                                ctx = WorkFlowService.authenticate("ptlupus1");
                                                do {
                                                    taskList = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                                                } while (taskList.size() == 0);
                                                Task a = taskList.get(0);
                                                taskId = a.getSystemAttributes().getTaskId();
                                                WorkFlowService.acquireTask(taskId, ctx);
                                                nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                        ctx, "APPROVE");
                                            } else if (nextStage.equals("tolak_ringkas") && finalResult.equals("PP")) {
                                                LOGGER.info("Skip one stage");
                                                List<Task> taskList = null;
                                                ctx = WorkFlowService.authenticate("ptlupus1");
                                                do {
                                                    taskList = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                                                } while (taskList.size() == 0);
                                                Task a = taskList.get(0);
                                                taskId = a.getSystemAttributes().getTaskId();
                                                WorkFlowService.acquireTask(taskId, ctx);
                                                nextStage = WorkFlowService.updateTaskOutcome(taskId,
                                                        ctx, "APPROVE");
                                            }

                                        }
                                    }
                                }

                                permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);

                            } else {
                                //FasaPermohonan fp = fasaPermohonanService.getLastStage(idPermohonan);
                                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "back");
                                permohonanService.updateStsPermohonan(permohonan, "SS", pengguna);
                            }

                        } else {
                            LOGGER.debug("APPROVE");

                            if (NEXT_STAGE.equals("APPROVE")) {
                                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
                                permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);

                            } else {
                                //FasaPermohonan fp = fasaPermohonanService.getLastStage(idPermohonan);
                                String result = "back";
                                for (String validator : validators) {
                                    Class clazz = Class.forName(validator);
                                    Object obj = clazz.newInstance();
                                    if (obj instanceof StageListener) {
                                        LOGGER.info("stage listener:: begin");
                                        StageListener q = (StageListener) obj;
                                        AbleContextListener.getInjector().injectMembers(obj);
                                        stageImp = (StageContextImpl) createStageContext(permohonan, pengguna, false);
                                        try {
                                            result = q.beforePushBack(stageImp, finalResult);
                                            if (stageImp != null) {
                                                for (String string : stageImp.messages) {
                                                    pushBackMessage.append(string);
                                                }
                                            }
                                        } catch (Exception ex) {
                                            LOGGER.error(ex);
                                        }
                                    }
                                }
                                if (StringUtils.isNotEmpty(result)) {
                                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "back");
                                    permohonanService.updateStsPermohonan(permohonan, "SS", pengguna);
                                }
                            }
                        }

                    } else if (finalResult != null) {
                        if (NEXT_STAGE.equals("APPROVE")) {

                            //For Penguatkuasaan module
                            if (jabatanTerlibat.equals(JabatanConstants.PENGUATKUASAAN) && conf.getProperty("kodNegeri").equals("04")) {
                                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                                    if (stageId.equalsIgnoreCase("keputusan_kompaun")) {
                                        //Send tugasan based on user only : keputusan_kompaun
                                        PegawaiPenyiasat pegawaiSiasat = enforcementService.findPengguna(permohonan.getIdPermohonan());
                                        Task task2 = WorkFlowService.reassignTask(ctx, taskId, pegawaiSiasat.getNamaJabatan(), "user");
                                        nextStage = task2.getSystemAttributes().getStage();
                                    } else {
                                        //For sek 427 & 428, except stage keputusan_kompaun
                                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
                                        permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);
                                    }

                                } else {
                                    //For others seksyen (Enforcement, Melaka) & except stage keputusan_kompaun
                                    System.out.println("for others seksyen for enforcement");
                                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
                                    permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);
                                }
                            } else {
                                //For other module
                                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")
                                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS")
                                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD")
                                        //|| permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPP")
                                        //|| permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPC")
                                        //|| permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSBS")
                                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBS")) {
                                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "L");
                                    permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);
                                } else {
                                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
                                    permohonanService.updateStsPermohonan(permohonan, "AK", pengguna);
                                }

                            }
                        } else {
                            //FasaPermohonan fp = fasaPermohonanService.getLastStage(idPermohonan);
                            String result = "back";
                            for (String validator : validators) {
                                Class clazz = Class.forName(validator);
                                Object obj = clazz.newInstance();
                                if (obj instanceof StageListener) {
                                    LOGGER.info("stage listener:: begin");
                                    StageListener q = (StageListener) obj;
                                    AbleContextListener.getInjector().injectMembers(obj);
                                    stageImp = (StageContextImpl) createStageContext(permohonan, pengguna, false);
                                    try {
                                        result = q.beforePushBack(stageImp, finalResult);
                                        if (stageImp != null) {
                                            for (String string : stageImp.messages) {
                                                pushBackMessage.append(string);
                                            }
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            }
                            if (StringUtils.isNotEmpty(result)) {
                                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctx, "back");
                                permohonanService.updateStsPermohonan(permohonan, "SS", pengguna);
                            }
                        }
                    }

                    String f_stage = "";
                    try {
                        f_stage = WorkFlowService.getCurrentState(taskId);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    LOGGER.debug("current state = " + f_stage);
                    LOGGER.debug("nextStage = " + nextStage);
                    if (f_stage.equalsIgnoreCase("completed")) {
                        if (jabatanTerlibat.equals(JabatanConstants.LELONG)) {
                            LOGGER.debug("finalResult : " + finalResult);
                            permohonanService.updateStsPermohonan(permohonan, finalResult, pengguna);
                        } else {
                            permohonanService.updateStsPermohonan(permohonan, "SL", pengguna);
                        }
                    }

                    //For pembangunan by rajib
                    if (jabatanTerlibat.equals(JabatanConstants.PEMBANGUNAN) && conf.getProperty("kodNegeri").equals("04")) {
                        LOGGER.info("Next stage : " + nextStage);
                        if (nextStage == null) {
                            LOGGER.debug("Ke seterusnya");
                        } else {
                            LOGGER.debug("Ke seterusnya");
                            if (nextStage.equals("pindaanagihan") || nextStage.equals("pindaanrencanajkbb") || nextStage.equals("semakpindaan")) {
                                permohonanService.updateStsPermohonan(permohonan, "SS", pengguna);
                            }

                        }
                    }

                    FasaPermohonan fp = fasaPermohonanService.getKeputusan(idPermohonan, pengguna.getIdPengguna(), stageId);
                    if (fp != null) {
                        fp.setTarikhHantar(new Date());
                        fasaPermohonanService.saveOrUpdate(fp);
                    }

                    if (!permohonan.getSenaraiFasa().isEmpty()) {
                        for (FasaPermohonan f : permohonan.getSenaraiFasa()) {
                            if (f.getIdAliran().equals(nextStage)) {
                                if (NEXT_STAGE.equals("APPROVE") && !f_stage.equalsIgnoreCase("completed")) {
                                    IWorkflowContext ctx_q = WorkFlowService.authenticate(f.getIdPengguna());
                                    WorkFlowService.acquireTask(taskId, ctx_q);
                                    f.setMesej(null);
                                    f.setDimesejOleh(null);
                                    f.setTarikhMesej(null);
                                    fasaPermohonanService.saveOrUpdate(f);
                                    fp = f;
                                    break;
                                } else if (NEXT_STAGE.equals("PUSHBACK")) {
                                    if (f.getTarikhHantar() == null) {
                                        continue;
                                    }
                                    //LIVE : new request - floating when push back
//                                    IWorkflowContext ctx_q = WorkFlowService.authenticate(f.getIdPengguna());
//                                    WorkFlowService.acquireTask(taskId, ctx_q);
                                    f.setMesej(mesej);
                                    f.setDimesejOleh(pengguna);
                                    f.setTarikhMesej(new Date());
                                    fasaPermohonanService.saveOrUpdate(f);
                                    fp = f;
                                    break;
                                }
                            }
                        }
                    }

                    //log the movement
                    ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());

                    FasaPermohonanLog fasaLog = new FasaPermohonanLog();
                    fasaLog.setInfoAudit(ia);
                    fasaLog.setTarikhHantar(new Date());
                    fasaLog.setFasa(fp);
                    if (StringUtils.isNotBlank(finalResult)) {
                        fasaLog.setKeputusan(kodKeputusanDAO.findById(finalResult));
                    }
                    fasaLog.setNomborRujukan(noRujukan);
                    KodCawangan cawangan = null;
                    if (fp != null && fp.getCawangan() != null) {
                        cawangan = fp.getCawangan();
                    } else {
                        cawangan = pengguna.getKodCawangan();
                    }
                    fasaLog.setCawangan(cawangan);
                    fasaLog.setUlasan(fp.getUlasan());
                    senaraiFasaLog.add(fasaLog);

                    for (String validator : validators) {
                        Class clazz = Class.forName(validator);
                        Object obj = clazz.newInstance();
                        if (obj instanceof StageListener) {
                            LOGGER.info("stage listener:: begin");
                            StageListener q = (StageListener) obj;
                            AbleContextListener.getInjector().injectMembers(obj);
                            stageImp = (StageContextImpl) createStageContext(permohonan, pengguna, false);
                            try {
                                if (NEXT_STAGE.equals("APPROVE")) {
                                    q.afterComplete(stageImp);
                                } else {
                                    q.afterPushBack(stageImp);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                LOGGER.error(ex);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                return new StreamingResolution("text/plain",
                        "senaraiTugasan?error=" + ex.getMessage());
            }

            if (senaraiFasaLog.size() > 0) {
                fasaPermohonanService.saveFasaLog(senaraiFasaLog);
            }

            if (NEXT_STAGE.equals("PUSHBACK")) {
                if (pushBackMessage.length() <= 0) {
                    url.append("message=");
                    msg.append(" - Penghantaran Semula Berjaya.");
                } else {
                    url.append("error=");
                    msg.append(" - ");
                    msg.append(pushBackMessage.toString());
                }
            } else if (NEXT_STAGE.equals("APPROVE")) {
                url.append("message=");
//                System.out.println("kod " + permohonan.getKodUrusan().getKod());
//                System.out.println("stage :" + stageId);
                if (m.length() <= 0) {
                    if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("RBHS")) && (stageId.equalsIgnoreCase("sediasurat"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P14A")) && (stageId.equalsIgnoreCase("sediakertassiasatan1"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P14A")) && (stageId.equalsIgnoreCase("sediakertassiasatan2"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P14A")) && (stageId.equalsIgnoreCase("janasurattolak"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P22B")) && (stageId.equalsIgnoreCase("sediakertassiasatan1"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P22B")) && (stageId.equalsIgnoreCase("sediakertassiasatan2"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("P22B")) && (stageId.equalsIgnoreCase("janasurattolak"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTPS")) && (stageId.equalsIgnoreCase("sediasurat"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTHS")) && (stageId.equalsIgnoreCase("sediasurat"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PWPN")) && (stageId.equalsIgnoreCase("keputusan"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PNB")) && (stageId.equalsIgnoreCase("sediasurat"))) {
                        msg.append(" - Urusan telah selesai.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBS")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBTS")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPC")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPP")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBSS")) && (stageId.equalsIgnoreCase("sediasuratbatal"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPPP")) && (stageId.equalsIgnoreCase("semaksijiltptg"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD"))
                            || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBSS")) || permohonan.getKodUrusan().getKod().equals("PBS")
                            || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                            || permohonan.getKodUrusan().getKod().equals("PHPP")) && (stageId.equalsIgnoreCase("g_semaklaporan") || stageId.equalsIgnoreCase("g_semakpelan"))) {
                        msg.append(" -  Penghantaran Berjaya.");
                    } else if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD"))
                            || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBSS")) || permohonan.getKodUrusan().getKod().equals("PBS")
                            || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                            || permohonan.getKodUrusan().getKod().equals("PHPP")) && (stageId.equalsIgnoreCase("sediasurattolak"))) {
                        msg.append(" - Penghantaran Berjaya.");
                    } else {
                        msg.append(" - Penghantaran Berjaya.");
                    }
                } else {
                    msg.append(m.toString());
                }

            }
            url.append(msg.toString());

        } else {
            return new StreamingResolution("text/plain",
                    "senaraiTugasan?error=Terdapat Masalah Teknikal Pada Permohonan ini .[ " + idPermohonan + " ]");
        }

        if (isDebug) {
            LOGGER.debug("took = " + (System.currentTimeMillis() - t));
        }

        if (jabatanTerlibat.equals(JabatanConstants.PENDAFTARAN) && isFinalStage && !NEXT_STAGE.equals("PUSHBACK")) {
            url = new StringBuilder("daftar/senarai_keutamaan?")
                    .append("cetakGeran")
                    .append("&idPermohonan=")
                    .append(idPermohonan)
                    .append("&message=")
                    .append(msg.toString());
        }
        return new StreamingResolution("text/plain", url.toString());
    }

    private List<Map<String, String>> getPermohonanWithTaskID(IWorkflowContext ctx, String idKump) throws Exception {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        senaraiPermohonanBerangkai = permohonanService.getPermohonanByIdKump(idKump);
        Map<String, String> map = null;
        LOGGER.info("Urusan Berangkai");
//        List taskList = WorkFlowService.queryTasks(ctx, p.getKodCawangan().getKod());
        for (Permohonan mohon : senaraiPermohonanBerangkai) {
            idPermohonan = mohon.getIdPermohonan();
            List taskList = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
            if (!taskList.isEmpty()) {
                Task impl = (Task) taskList.get(0);
                taskId = impl.getSystemAttributes().getTaskId();
                map = new HashMap<String, String>();
                map.put("idPermohonan", mohon.getIdPermohonan());
                map.put("taskId", taskId);
                list.add(map);
            }
        }
//        for (int i = 0; i < taskList.size(); i++) {
//            Task impl = (Task) taskList.get(i);
//            taskId = impl.getSystemAttributes().getTaskId();
//            idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
//
//            Task temp = WorkFlowService.getTask(taskId, ctx);
//            if (temp.getSystemAttributes().getAcquiredBy() == null) {
//                WorkFlowService.acquireTask(taskId, ctx);
//            }
//
//            for (Permohonan mohon : senaraiPermohonanBerangkai) {
//                if (mohon.getIdPermohonan().equals(idPermohonan)) {
//                    map = new HashMap<String, String>();
//                    map.put("idPermohonan", mohon.getIdPermohonan());
//                    map.put("taskId", taskId);
//                    list.add(map);
//                }
//            }
//        }
        return list;
    }

    public Resolution genReportBerkumpulan() {
        LOGGER.info("genReportBerkumpulan :: start");
        try {
            service = new BPelService();
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohananDAO.findById(idPermohonan);
            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
            String idKump = "";
            if (permohonan != null) {
                idKump = permohonan.getIdKumpulan();
            }
//            senaraiPermohonanBerangkai = permohonanService.getPermohonanByIdKump(idKump);
            List<Map<String, String>> list = getPermohonanWithTaskID(ctx, idKump);
            boolean flag = Boolean.TRUE;
            boolean flgResult = Boolean.TRUE;
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (Map<String, String> map : list) {
                taskId = map.get("taskId");
                idPermohonan = map.get("idPermohonan");
                task = service.getTaskFromBPel(taskId, pengguna);
                stageId = task.getSystemAttributes().getStage();
                Permohonan curr_permohonan = permohananDAO.findById(idPermohonan);
                if (curr_permohonan.getKeputusan() != null && curr_permohonan.getKeputusan().getKod().equals("D")) {
                    permohonan = curr_permohonan;
                }
                KodUrusan ku = curr_permohonan.getKodUrusan();
                kodUrusan = ku.getKod();
                workflowId = ku.getIdWorkflow();
                idFolder = String.valueOf(curr_permohonan.getFolderDokumen().getFolderId());
                isThereAnyResult = tabManager.anyKeputusanToPerform(workflowId, stageId, kodUrusan);
                if (isThereAnyResult) {
                    if (!fasaPermohonanService.checkKeputusan(idPermohonan, pengguna.getIdPengguna(), null, stageId)) {
                        flag = Boolean.FALSE;
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(idPermohonan);
                    }
//                    if (!fasaPermohonanService.checkKeputusan(idPermohonan, p.getIdPengguna(), "D")) {
//                        flgResult = Boolean.FALSE;
//                        if (sb2.length() > 0) {
//                            sb2.append(",");
//                        }
//                        sb2.append(idPermohonan);
//                    }
                }
            }
            if (!flag) {
                return new RedirectResolution(KernelActionBean.class, "showForm").addParameter("idPermohonan", idPermohonan).addParameter("error", "Tiada keputusan dibuat untuk permohonan " + sb.toString());
            }

            if (stageId.equals("keputusan")) {
                LOGGER.info("creating surat2");
                for (Map<String, String> map : list) {

                    taskId = map.get("taskId");
                    idPermohonan = map.get("idPermohonan");
                    task = service.getTaskFromBPel(taskId, pengguna);
                    stageId = task.getSystemAttributes().getStage();

                    permohonan = permohananDAO.findById(idPermohonan);

                    KodUrusan ku = permohonan.getKodUrusan();
                    kodUrusan = ku.getKod();
                    workflowId = ku.getIdWorkflow();

                    Map<String, String> senarai_surat
                            = tabManager.getOutComesReport(workflowId, stageId, kodUrusan, permohonan.getKeputusan().getKod());
                    if (senarai_surat != null) {
                        //TODO : insert into table?
                        //regenerate?
                        generateDokumen(senarai_surat.get("generator"), senarai_surat.get("code"));
                    }
                }
            }

            LOGGER.info("genReportFromListener");
            genReportFromListener();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(KernelActionBean.class, "showForm").addParameter("idPermohonan", idPermohonan).addParameter("error", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReportBerkumpulan :: finish");
        return new RedirectResolution(KernelActionBean.class, "showForm").addParameter("idPermohonan", idPermohonan).addParameter("message", "Perjanaan Dokumen Berjaya. Sila Semak Dokumen sebelum daftar.");
    }

    private void generateDokumen(String reportName, String code) {
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String dokumenPath = conf.getProperty("document.path");
        InfoAudit ia = new InfoAudit();
        String path = "";
        String[] params = {"p_id_mohon"};
        String[] values = {idPermohonan};
        String idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        KodDokumen kd = kodDokumenDAO.findById(code);

        Dokumen doc = semakDokumenService.semakDokumen(kd, fd, idPermohonan);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        doc.setNoVersi("1.0");
        doc.setTajuk(permohonan.getKodUrusan().getKod() + "-" + permohonan.getIdPermohonan());
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setDalamanNilai1(idPermohonan);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(doc.getIdDokumen());
        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), doc.getIdDokumen());

        if (permohonan.getSenaraiHakmilik().size() > 0) {
            List<HakmilikPermohonan> senarai = new ArrayList<HakmilikPermohonan>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                hp.setDokumen4(doc);
                senarai.add(hp);
            }
            hakmilikPermohonanService.saveHakmilikPermohonan(senarai);
        }
    }

    public Resolution sendNotifikasi() {
        String result = "0";

        String msg = getContext().getRequest().getParameter("mesej");

        Permohonan permohonanSebelum = permohonan.getPermohonanSebelum();

        String status = permohonanSebelum.getStatus();

        if ("SL".equals(status)) {

            StringBuilder sb = new StringBuilder("Permohonan ")
                    .append(permohonanSebelum.getIdPermohonan())
                    .append(" telah selesai. Notifikasi tidak dapat dihantar.");

            return new StreamingResolution("text/plain", sb.toString());
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setCawangan(permohonanSebelum.getCawangan());
        notifikasi.setMesej(msg);
        notifikasi.setTajuk("Maklumat Permohonan " + permohonanSebelum.getIdPermohonan() + " tidak lengkap.");
        notifikasi.setInfoAudit(ia);
        if (StringUtils.isNotBlank(permohonan.getIdStagePermohonanSebelum())) {
            FasaPermohonan fp
                    = fasaPermohonanService.getCurrentStage(permohonanSebelum.getIdPermohonan(), permohonan.getIdStagePermohonanSebelum());
            if (fp != null && fp.getInfoAudit() != null) {
                Pengguna p = fp.getInfoAudit().getDimasukOleh();
                notifikasi.setPengguna(p);
            }
        }

        notifikasiService.save(notifikasi);

        return new StreamingResolution("text/plain", result);
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        String msg = "Dokumen telah dijana. Sila buat semakan sebelum menghantar ke peringkat seterusnya.";

        if (StringUtils.isBlank(stageId)) {
            LOGGER.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }
        isThereAnyResult = tabManager.anyKeputusanToPerform(workflowId, stageId, kodUrusan);
        if (isThereAnyResult) {
            if (!fasaPermohonanService.checkKeputusan(idPermohonan, pengguna.getIdPengguna(), null, stageId)) {
                return new StreamingResolution("text/plain", "Tiada keputusan dibuat untuk permohonan " + idPermohonan);
            }
        }
        StageContextImpl sc = (StageContextImpl) createStageContext(permohonan, pengguna, false);

        if (!doBeforeGenerateReport(sc)) {
            return new StreamingResolution("text/plain", generateMessage(sc.messages));
        }

        try {
            if ((!permohonan.getKodUrusan().getJabatan().getKod().equals(JabatanConstants.PENDAFTARAN))
                    && (!stageId.equals("kemasukan"))) {
                LOGGER.info("genReportFromXML");
                genReportFromXml();
            }
            LOGGER.info("genReportFromListener");
            genReportFromListener();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void genReportFromXml() throws Exception {
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        stageId = task.getSystemAttributes().getStage();
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            List<Map<String, String>> l = tabManager.getReports(workflowId, stageId, kodUrusan);
            for (Map<String, String> m : l) {
                String gen = (String) m.get("generator");
                String prefix = (String) m.get("prefix");
                String code = (String) m.get("code");
                String multipleReport = (String) m.get("multiple");
                LOGGER.info("multipleReport :: " + multipleReport);
                String[] params = null;
                String[] values = null;
                KodDokumen kd = kodDokumenDAO.findById(code);
                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
                //multiple hakmilik should generate multiple report ie vdoc
                if (StringUtils.isNotBlank(multipleReport) && multipleReport.equals("true")) {
//                    List<String> idHakmilikList = hakmilikPermohonanService.findIdHakmilikByPermohonan(idPermohonan);
//                    for (String idHakmilik : idHakmilikList) {
                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                    for (HakmilikPermohonan hakmilikPermohonan : hk) {
                        LOGGER.info("hakmilik ::" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        //TODO : to add idhakmilik in params
                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                        d = saveOrUpdateDokumen(fd, kd, hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        LOGGER.info("::Path To save :: " + (dokumenPath + path));
                        LOGGER.info("::Report Name ::" + gen);
                        reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                        if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                            hakmilikPermohonan.setDokumen1(d);
                        }
                        if (kd.getKod().equals("DHKE")) {
                            hakmilikPermohonan.setDokumen3(d);
                        }
                        if (kd.getKod().equals("DHDE")) {
                            hakmilikPermohonan.setDokumen2(d);
                        }
                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);

                    }
                } else {
                    params = new String[]{"p_id_mohon"};
                    values = new String[]{idPermohonan};
                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    LOGGER.info("::Path To save :: " + (dokumenPath + path));
                    LOGGER.info("::Report Name ::" + gen);
                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                    if (hk.size() != 0) {
                        HakmilikPermohonan hakmilikPermohonan = hk.get(0);
                        if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                            hakmilikPermohonan.setDokumen1(d);
                        }
                        if (kd.getKod().equals("DHKE")) {
                            hakmilikPermohonan.setDokumen3(d);
                        }
                        if (kd.getKod().equals("DHDE")) {
                            hakmilikPermohonan.setDokumen2(d);
                        }
                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                    }
                }
            }
        }
    }

    private void genReportFromListener() throws Exception {
        List<String> list = tabManager.getValidatorClass(workflowId, stageId, kodUrusan);
        for (String str : list) {
            //try to convert class of given string..
            Class clazz = Class.forName(str);
            Object obj = clazz.newInstance();
            if (obj instanceof StageListener) {
                LOGGER.info("::begin");
                StageListener q = (StageListener) obj;
                AbleContextListener.getInjector().injectMembers(obj);
                q.onGenerateReports(createStageContext(permohonan, pengguna, false));
                LOGGER.info("::finish");
            }

        }
    }

    private boolean doBeforeGenerateReport(StageContextImpl sc) {
        boolean f = Boolean.TRUE;
        List<String> list = tabManager.getValidatorClass(workflowId, stageId, kodUrusan);
        for (String str : list) {
            //try to convert class of given string..
            try {
                Class clazz = Class.forName(str);
                Object obj = clazz.newInstance();
                if (obj instanceof StageListener) {
                    LOGGER.info("doBeforeGenerateReport::begin");
                    StageListener q = (StageListener) obj;
                    AbleContextListener.getInjector().injectMembers(obj);
                    f = q.beforeGenerateReports(sc);
                    if (!f) {
                        break;
                    }
                    LOGGER.info("doBeforeGenerateReport::finish");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex);
            }
        }
        return f;
    }

    private StageContext createStageContext(Permohonan p, Pengguna pengguna, boolean byPass) {
        StageContextImpl st = new StageContextImpl();
        st.permohonan = p;
        st.pengguna = pengguna;
        st.byPass = byPass;
        st.stageName = stageId;
        return st;
    }

    private String generateMessage(List<String> msgs) {
        StringBuilder sb = new StringBuilder();
        for (String str : msgs) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
