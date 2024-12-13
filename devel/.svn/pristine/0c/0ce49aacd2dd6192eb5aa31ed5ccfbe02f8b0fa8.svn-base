/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.workflow;

import com.google.inject.Inject;
import etanah.Configuration;
import etanah.JabatanConstants;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.service.BPelService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author md.nurfikri
 */
@HttpCache(allow = false)
@UrlBinding("/workflow/kernal_action")
public class KernelActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PenggunaPerananDAO penggunaperananDao;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanRujukanLuarService serviceBaru;
//    @Inject
//    KernalService kernelService;
    private String mesej;
    private boolean bpelWorflowEngine = Boolean.FALSE;
    private boolean isSummaryRequired = Boolean.FALSE;
    private PermohonanPengambilan permohonanPengambilan;

    private String notifikasi;

    private String perluConvert;
    private String modulpendaftaran;

    private String errMsg = "";
    private BPelService service;
    private String warnaModul;
    private String namaUrusan;
    private static final Logger LOG = Logger.getLogger(KernelActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String printMethod = "";
    private List<Permohonan> permohonanBerangkai;
    private String[] isUrusanBerangkaiDone;
    private boolean isDistribute = Boolean.FALSE;
    private boolean selesaiBtn = Boolean.TRUE;
    private Pengguna pengguna;
    private List<Pengguna> listPp;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Boolean> senaraiSign = new ArrayList<Boolean>();
    private Permohonan permohonan;
    private String trhPerserahan;
    private String kodUrusan;
    private boolean isSWexist = false;

    @DontValidate
    @DefaultHandler
    public Resolution showForm() {
        long t = System.currentTimeMillis();
        if (!rehydrate()) {
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        String kodNegeri = conf.getProperty("kodNegeri");
        if ("05".equals(kodNegeri)) {
            getContext().getRequest().setAttribute("cetak", Boolean.FALSE);
        }
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String jabatan = permohonan.getKodUrusan().getJabatan().getKod();
            if (jabatan.equals("16")) {
                modulpendaftaran = "true";
            }
        }

        //Berangkai        
        String details = getContext().getRequest().getParameter("detail");
        String size = getContext().getRequest().getParameter("size_berangkai");
        if (StringUtils.isNotBlank(size)) {
            getContext().getRequest().setAttribute("size_berangkai", size);
        }
        if (isFinalStage()) {
            //for urusan berangkai and final stage, the report should generate once
            getContext().getRequest().setAttribute("finalStage", Boolean.TRUE);
        }
        if (StringUtils.isBlank(details)) {
            String idKump = "";
            if (StringUtils.isNotBlank(idPermohonan)) {
                permohonan = permohonanDAO.findById(idPermohonan);
                if (permohonan != null) {
                    idKump = permohonan.getIdKumpulan();
                    if (StringUtils.isNotBlank(idKump)) {
                        permohonanBerangkai = permohonanService.getPermohonanByIdKump(idKump);
                        for (Permohonan permohonan1 : permohonanBerangkai) {
                            if (permohonan1 == null) {
                                continue;
                            }
                            initiateDokumen(permohonan1);
                        }
                    }

                    Permohonan permohonanSebelum = permohonan.getPermohonanSebelum();
                    String jabatan = permohonan.getKodUrusan().getJabatan().getKod();
                    if (permohonanSebelum != null && jabatan.equals("16")) {
                        notifikasi = "true";
                    }
                    String[] URUSAN_CONVERT = {
                        "HSTHK",
                        "HKTHK",
                        "HMSC",
                        "HTIR"
                    };
                    if (jabatan.equals("16")
                            && !ArrayUtils.contains(URUSAN_CONVERT, permohonan.getKodUrusan().getKod())) {
//                        RegService reg = etanahContextListener.newInstance(RegService.class);
                        StringBuilder idHakmiliks = new StringBuilder();
                        List<Hakmilik> senaraiHakmilikConvert = new ArrayList<Hakmilik>();
                        List<HakmilikPermohonan> senarai = permohonan.getSenaraiHakmilik();
                        for (HakmilikPermohonan hp : senarai) {
                            if (hp == null || hp.getHakmilik() == null) {
                                continue;
                            }
                            Hakmilik hm = hp.getHakmilik();
                            if (hm.getNoVersiDhde() != null && hm.getNoVersiDhde() == 0
                                    && hm.getKodStatusHakmilik() != null && hm.getKodStatusHakmilik().getKod().equals("D")) {

//                                if ("T".equals(hm.getKodStatusHakmilik().getKod())) {
//                                    List<HakmilikAsalPermohonan> list 
//                                            = reg.searchMohonHakmilikAsalByIDHakmilik(hm.getIdHakmilik());
//                                    for (HakmilikAsalPermohonan asal : list) {
//                                        if (idHakmiliks.length() > 0) idHakmiliks.append(",");
//                                        idHakmiliks.append(asal.getHakmilikSejarah());
//                                    }
//                                    
//                                } else {
//                                    if (idHakmiliks.length() > 0) idHakmiliks.append(",");
//                                    idHakmiliks.append(hm.getIdHakmilik());
//                                    
//                                }
                                if (idHakmiliks.length() > 0) {
                                    idHakmiliks.append(",");
                                }
                                idHakmiliks.append(hm.getIdHakmilik());
                                senaraiHakmilikConvert.add(hm);

                            }
                        }
                        if (!senaraiHakmilikConvert.isEmpty()) {
                            perluConvert = "true";

                            addSimpleError("Hakmilik " + idHakmiliks.toString() + " perlu ditukarganti.");
                        }
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                trhPerserahan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
            }
            if (permohonanBerangkai != null && permohonanBerangkai.size() > 0) {
                //untuk agih tugas
                if (isDistribute) {
                    doPopulatePTForAgihan();
                }

                isUrusanBerangkaiDone = new String[permohonanBerangkai.size()];
                int i = 0;

                for (Permohonan p : permohonanBerangkai) {
                    String id = p.getIdPermohonan();
                    boolean REPORT_GENERATED = false;

                    String curr_result = "";
                    if (stageId.equals("kemasukan")) {
                        curr_result = null;
                        REPORT_GENERATED = checkReport(id, p.getKodUrusan().getJabatan().getKod(), curr_result);
                    } else {
                        if (p.getKeputusan() != null) {
                            curr_result = p.getKeputusan().getKod();
                            REPORT_GENERATED = true;
                        }
                    }

                    isUrusanBerangkaiDone[i] = String.valueOf(REPORT_GENERATED);
                    semakKeputusan(id, pengguna, stageId);
                    i = i + 1;
                }
                //TODO : check keputusan and report to enable hantar                
                return new ForwardResolution("/WEB-INF/jsp/tab/main_page_berangkai.jsp");
            }
        } else {
            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
        }
        if (IS_LOG_DEBUG) {
            LOG.debug("took = " + (System.currentTimeMillis() - t) + " ms");
        }
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    private void doPopulatePTForAgihan() {
        KodCawangan kod = pengguna.getKodCawangan();
        // kene cater ikut peranan..        
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
//            System.out.println("p : " + p.getIdPengguna());
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null && p.getStatus() != null) {
                    //TODO : change peranan utama
                    if (p.getPerananUtama().getKod().equals("1")
                            && p.getStatus().getKod().equals("A")) {
                        listPp.add(p);
                    }
                }
            }
        }
    }

    /**
     * for flow that doesn't need bpel process. i.e Hasil..
     */
    public Resolution withoutBpelProcess() {
        idPermohonan = "";
        stageId = getContext().getRequest().getParameter("stageId");
        txnCode = getContext().getRequest().getParameter("txncode");
        if (StringUtils.isBlank(stageId)) {
            addSimpleError("Stage ID tidak dikenali. Sila hubungi Pentadbir Sistem");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        if (StringUtils.isBlank(txnCode)) {
            addSimpleError("Kod Urusan tidak dikenali. Sila hubungi Pentadbir Sistem");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        KodUrusan ku = kodUrusanDAO.findById(txnCode);
        if (ku == null) {
            addSimpleError("Urusan tidak dikenali. Sila hubungi Pentadbir Sistem");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        idWorkFlow = ku.getIdWorkflow();
        warnaModul = ku.getJabatan().getWarnaModul();
        if (ku.equals("831")) {
            namaUrusan = ku.getNama() + "(A)";
        } else {
            namaUrusan = ku.getNama();
        }

        if (!doTabbing()) {
            addSimpleError("Tiada Maklumat Untuk Permohonan Ini : " + idPermohonan);
        }
        checkReport(idPermohonan, ku.getJabatan().getKod(), null);
        checkPushBack();
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    public Resolution viewModeOnly() {
        LOG.info("::ViewModeOnly::");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        stageId = getContext().getRequest().getParameter("stageId");
        if (StringUtils.isBlank(idPermohonan)) {
            addSimpleError("Id Permohonan tiada.");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        if (StringUtils.isBlank(stageId)) {
            addSimpleError("Stage Id tiada.");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        }
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodUrusan ku = permohonan.getKodUrusan();
        idWorkFlow = permohonan.getIdWorkflow();
        if (idWorkFlow == null || "".equals(idWorkFlow)) {
            idWorkFlow = ku.getIdWorkflow();
        }

        txnCode = ku.getKod();
        if (!doValidateViewRoles(pengguna)) {
            addSimpleError("Anda tiada akses untuk melihat permohonan ini. Sila hubungi IT helpdesk.");
            return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
        } else {
            if (!doTabbing()) {
                addSimpleError("Tiada Maklumat Untuk Permohonan Ini : " + idPermohonan);
            }
        }
        getContext().getRequest().getSession().setAttribute("idPermohonan", idPermohonan);
        getContext().getRequest().setAttribute("viewOnly", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

//    @Before(stages = {LifecycleStage.BindingAndValidation})
    public boolean rehydrate() {
        String jabatan = "";
        String keputusan = "";
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (!loadConfigFile()) {
            addSimpleError(errMsg);
            return false;
        }
        if (StringUtils.isNotBlank(taskUtil) && StringUtils.isNotBlank(stageId)) {
            bpelWorflowEngine = false;
        }
        if (bpelWorflowEngine) {
            //TODO : get stageId from bpel wflow engine. get user id from bpel, check against current user
            service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            if (StringUtils.isBlank(taskId)) {
                addSimpleError("Task Id tidak dikenali. Sila hubungi IT helpdesk.");
                return false;
            }
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);

            if (t == null) {
                addSimpleError(service.getErrMsg());
                return false;
            }

            permohonan = permohonanDAO.findById(idPermohonan);
            //validate current id mohon same from bpel based on task id
            if (StringUtils.isBlank(permohonan.getIdKumpulan())) {
                String id = t.getSystemMessageAttributes().getTextAttribute1();
                if (!id.equals(idPermohonan)) {
                    addSimpleError("Pengguna tidak boleh capai permohonan ini. Sila hubungi IT helpdesk.");
                    return false;
                }
            }
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
            getContext().getRequest().getSession().setAttribute("stageId", stageId);

        }

        ///Add By Mohd Syafiq(STRATA 1/8/2013)
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                if (stageId.equals("g_semaklaporan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("1")) {
                        LOG.info("true---------:");
                        selesaiBtn = true;
                    } else {
                        selesaiBtn = false;
                        LOG.info("false---------:");
                    }
                } else if (stageId.equals("g_semakpelan")) {
                    Dokumen dd = new Dokumen();
                    LOG.info("idPermohonan------::" + permohonan.getIdPermohonan());
                    dd = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    if (dd != null && dd.getCatatanMinit() != null && dd.getCatatanMinit().equals("2")) {
                        LOG.info("true---------:");
                        selesaiBtn = true;
                    } else {
                        selesaiBtn = false;
                        LOG.info("false---------:");
                    }
                } else {
                    LOG.info("lastTrue---------:");
                    selesaiBtn = true;
                }
            } else {
                selesaiBtn = false;
            }
        } else {
            selesaiBtn = false;
        }
        if (stageId == null) {
            addSimpleError("Stage id tidak dikenali. Sila hubungi IT helpdesk.");
            return false;
        }
        LOG.info("Stage ID ::" + stageId);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanPengambilan = serviceBaru.findByidP(idPermohonan);
//            ((etanahActionBeanContext)getContext()).setPermohonan(idPermohonan);
//            Permohonan permohonan = ((etanahActionBeanContext)getContext()).getPermohonan();
            if (permohonan == null) {
                addSimpleError("Tiada Maklumat Untuk Permohonan Ini : " + idPermohonan);
                return false;
            }
            KodUrusan ku = permohonan.getKodUrusan();
            if (!stageId.equals("keputusan") && !"agih_tugas".equals(stageId)) {
                if (!"SA".equals(ku.getKod()) && !"SW".equals(ku.getKod()) && !"SB".equals(ku.getKod())) {
                    semakDokumenSuratKuasa(permohonan);
                }
            }
            getContext().getRequest().getSession().setAttribute("idPermohonan", idPermohonan);

            LOG.info(ku.getKod());
            txnCode = ku.getKod();
            if (txnCode.equals("831")) {
                if (permohonanPengambilan.getKatPengambilan().equals("1")) {
                    namaUrusan = ku.getNama() + " (A)";
                } else if (permohonanPengambilan.getKatPengambilan().equals("2")) {
                    namaUrusan = ku.getNama() + " (B)";
                } else {
                    namaUrusan = ku.getNama() + " (C)";
                }

            } else {
                namaUrusan = ku.getNama();
            }
//            namaUrusan = ku.getNama();
            //get workflow namespace
            idWorkFlow = permohonan.getIdWorkflow();
            if (idWorkFlow == null || "".equals(idWorkFlow)) {
                idWorkFlow = ku.getIdWorkflow();
            }
            warnaModul = ku.getJabatan().getWarnaModul();
            jabatan = ku.getJabatan().getKod();

            if (!doTabbing()) {
                addSimpleError("Terdapat masalah Konfigurasi untuk Urusan ini. Sila hubungi IT helpdesk.");
                return false;
            }

            isDistribute = isDistribute();
            if (isDistribute) {
                getContext().getRequest().setAttribute("distribute", Boolean.TRUE);
            }
            for (FasaPermohonan fp : permohonan.getSenaraiFasa()) {
                if (fp.getIdAliran().equals(stageId)) {
                    mesej = fp.getMesej();
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                    }
                    break;
                }
            }
        } else {
            addSimpleError("ID Permohonan tidak dikenali. Sila hubungi IT helpdesk.");
            return false;
        }

        //check if there got any report to generate.
        //TODO : check if report already generated
        LOG.debug("check report by id mohon");

        if (stageId.equals("keputusan")
                && (txnCode.equals("HKTHK") || txnCode.equals("HSTHK"))) {
            selesaiBtn = true;
        } else {
            selesaiBtn = checkReport(idPermohonan, jabatan, keputusan);
        }
        checkPushBack();
        semakKeputusan(idPermohonan, pengguna, stageId);
        if (jabatan.equalsIgnoreCase(JabatanConstants.CONSENT)) {
            isSummaryRequired = Boolean.TRUE;
        }
        return true;
    }

    private void initiateDokumen(Permohonan permohonan) {
        //dont mind which permohonan. should be same dokumens for all permohonan berangkai
        FolderDokumen fd = permohonan.getFolderDokumen();

        String docPath = conf.getProperty("document.path");

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator);

        for (KandunganFolder kf : fd.getSenaraiKandungan()) {

            if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                continue;
            }
            String kod = kf.getDokumen().getKodDokumen().getKod();
            LOG.debug("kod dokumen = " + kod);
            if (kod.equals("DHKE") || kod.equals("DHDE") || kod.equals("VDOC")
                    || kod.equals("SD") || kod.equals("ST") || kod.equals("SGT")
                    || kod.equals("DHDK") || kod.equals("DHKK")) {
                senaraiKandungan.add(kf);
                path = path + kf.getDokumen().getNamaFizikal();
                File signFile = new File(path + ".sig");
                if (signFile.exists()) {
                    senaraiSign.add(true);
                } else {
                    senaraiSign.add(false);
                }
            }
        }

        Collections.sort(senaraiKandungan, new Comparator<KandunganFolder>() {
            /**
             * Sorting where Y < T
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             *
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(KandunganFolder o1, KandunganFolder o2) {
                return o1.getDokumen().getKodDokumen().getKod().compareToIgnoreCase(o2.getDokumen().getKodDokumen().getKod());
            }
        });
    }

    public void semakDokumenSuratKuasa(Permohonan permohonan) {
        FolderDokumen fd = permohonan.getFolderDokumen();
        kodUrusan = permohonan.getKodUrusan().getKod();
        for (KandunganFolder kf : fd.getSenaraiKandungan()) {
            if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                continue;
            }
            String kod = kf.getDokumen().getKodDokumen().getKod();
            String kodNegeri = conf.getProperty("kodNegeri");
            String kodJabatan = permohonan.getKodUrusan().getJabatan().getKod();
            if (kodJabatan.equals("16")) {
                if (kodNegeri.equals("05")) {
                    if (kod.equals("SWD") || kod.equals("SWB") || kod.equals("SW")
                            || kod.equals("SAD") || kod.equals("SAB") || kod.equals("SBB")) {
                        isSWexist = true;
                        break;
                    }
                } else {
                    if (kod.equals("SWD") || kod.equals("SWB") || kod.equals("SW")
                            || kod.equals("SAD") || kod.equals("SBB") || kod.equals("SBD") || kod.equals("SAB")) {
                        isSWexist = true;
                        break;
                    }
                }
            }
        }
    }

    private boolean loadConfigFile() {
        Properties props = new Properties();
        try {
            props.load(KernelActionBean.class.getResourceAsStream(Configuration.CONF_FILE));
            String a = props.getProperty("bpel.workflow.engine");
            String pm = props.getProperty("print.method");
            bpelWorflowEngine = Boolean.parseBoolean(a);
            printMethod = pm;
            return Boolean.TRUE;
        } catch (Exception ex) {
            ex.printStackTrace();
            errMsg = ex.getMessage();
            return Boolean.FALSE;
        }
    }

    public String getWarnaModul() {
        return warnaModul;
    }

    public void setWarnaModul(String warnaModul) {
        this.warnaModul = warnaModul;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    public String getPrintMethod() {
        return printMethod;
    }

    public void setPrintMethod(String printMethod) {
        this.printMethod = printMethod;
    }

    public List<Permohonan> getPermohonanBerangkai() {
        return permohonanBerangkai;
    }

    public void setPermohonanBerangkai(List<Permohonan> permohonanBerangkai) {
        this.permohonanBerangkai = permohonanBerangkai;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }

    public boolean getSummaryRequired() {
        return isSummaryRequired;
    }

    public void setIsSummaryRequired(boolean isSummaryRequired) {
        this.isSummaryRequired = isSummaryRequired;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getTrhPerserahan() {
        return trhPerserahan;
    }

    public void setTrhPerserahan(String trhPerserahan) {
        this.trhPerserahan = trhPerserahan;
    }

    public boolean getIsSWexist() {
        return isSWexist;
    }

    public void setIsSWexist(boolean isSWexist) {
        this.isSWexist = isSWexist;
    }

    public String[] getIsUrusanBerangkaiDone() {
        return isUrusanBerangkaiDone;
    }

    public void setIsUrusanBerangkaiDone(String[] isUrusanBerangkaiDone) {
        this.isUrusanBerangkaiDone = isUrusanBerangkaiDone;
    }

    public List<Boolean> getSenaraiSign() {
        return senaraiSign;
    }

    public void setSenaraiSign(List<Boolean> senaraiSign) {
        this.senaraiSign = senaraiSign;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public boolean isSelesaiBtn() {
        return selesaiBtn;
    }

    public void setSelesaiBtn(boolean selesaiBtn) {
        this.selesaiBtn = selesaiBtn;
    }

    public String getNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(String notifikasi) {
        this.notifikasi = notifikasi;
    }

    public String getPerluConvert() {
        return perluConvert;
    }

    public void setPerluConvert(String perluConvert) {
        this.perluConvert = perluConvert;
    }

    public String getModulpendaftaran() {
        return modulpendaftaran;
    }

    public void setModulpendaftaran(String modulpendaftaran) {
        this.modulpendaftaran = modulpendaftaran;
    }

    public PermohonanRujukanLuarService getServiceBaru() {
        return serviceBaru;
    }

    public void setServiceBaru(PermohonanRujukanLuarService serviceBaru) {
        this.serviceBaru = serviceBaru;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

}
