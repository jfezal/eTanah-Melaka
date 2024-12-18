/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.manager.TabBean;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.NotaService;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/keputusan")
public class StatusActionBean extends AbleActionBean {

    TabManager tabManager;
    FasaPermohonanDAO fasaPermohonanDAO;
    PermohonanDAO permohonanDAO;
    KodCawanganDAO kodCawanganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    NotaService notaService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PenggunaPerananDAO penggunaperananDao;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    StrataPtService strService;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    private KodRujukan kodRujukan;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String idPermohonan;
    private TabBean tabBean;
    private String stageId;
    private boolean pendaftar = Boolean.FALSE;
    private Pengguna pengguna;
    private InfoAudit info;
    Pengguna pguna;
    private List<FasaPermohonan> listFasa;
    private List<FasaPermohonan> listFasaSebelum;
    private List<FasaPermohonanLog> senaraiFasaLog;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private boolean finalStage = Boolean.FALSE;
    private String idWorkFlow = "";
    private String txnCode = "";
    private List<Pengguna> listPp;
    PermohonanService permohonanManager;
    FasaPermohonanService fasaPermohonanManager;
    IWorkflowContext ctx = null;
    private String DEFAULT_SUCCESS_MSG = "Kemasukan Data Berjaya.";
    @Inject
    etanah.Configuration conf;
    private static final Logger LOGGER = Logger.getLogger(StatusActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private String tarikhKeputusan;
    private String kodJabatan;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    public StatusActionBean(FasaPermohonanDAO fasaPermohonanDAO, PermohonanDAO permohonanDAO,
            KodCawanganDAO kodCawanganDAO, TabManager tabManager, PermohonanService permohonanManager,
            FasaPermohonanService fasaPermohonanManager) {
        this.fasaPermohonanDAO = fasaPermohonanDAO;
        this.permohonanDAO = permohonanDAO;
        this.kodCawanganDAO = kodCawanganDAO;
        this.tabManager = tabManager;
        this.permohonanManager = permohonanManager;
        this.fasaPermohonanManager = fasaPermohonanManager;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!save"})
    public void rehydrate() {
        KodUrusan ku = null;
        BPelService service = new BPelService();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        } catch (Exception e) {
            LOGGER.error("error ::" + e.getMessage());
        }
        //stageId = getContext().getRequest().getParameter("stageId");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            //no task id found
            //try to get stageid via parameter
            stageId = getContext().getRequest().getParameter("stageId");
            if (StringUtils.isEmpty(stageId)) {
                addSimpleError("Stage id tidak dikenali. Sila hubungi Pentadbir Sistem.");
                return;
            }
        } else {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            if (t == null) {
                addSimpleError("Task tidak dikenali. Sila hubungi Pentadbir Sistem.");
                return;
            }
            stageId = t.getSystemAttributes().getStage();
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOGGER.debug("idpermohonan.status :" + idPermohonan);
        if (permohonan != null) {
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
                LOGGER.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
                if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
                    permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
                    if (permohonanRujLuar.getTarikhSidang() == null) {
                        permohonanRujLuar.setTempohHari(0);
                    }
                }
            }
            if (StringUtils.isBlank(permohonan.getIdWorkflow())) {
                ku = permohonan.getKodUrusan();
                idWorkFlow = ku.getIdWorkflow();
            } else {
                idWorkFlow = permohonan.getIdWorkflow();
                ku = permohonan.getKodUrusan();
            }
            txnCode = ku.getKod();
            kodJabatan = ku.getJabatan().getKod();
            listFasa = new ArrayList<FasaPermohonan>();
            listFasaSebelum = new ArrayList<FasaPermohonan>();
            senaraiFasaLog = new ArrayList<FasaPermohonanLog>();
            List<FasaPermohonan> list_tmp = permohonan.getSenaraiFasa();

            for (FasaPermohonan fp : list_tmp) {
                if (fp.getIdPengguna() != null) {
                    if (fp.getIdPengguna().equals(pengguna.getIdPengguna())
                            && fp.getIdAliran().equals(stageId)) {
                        fasaPermohonan = fp;
                        if (fp.getKeputusan() == null) {
                            continue;
                        }
                        if (fp.getTarikhKeputusan() != null) {
                            tarikhKeputusan = sdf.format(fp.getTarikhKeputusan());
                        } else {
                            tarikhKeputusan = sdf.format(new Date());
                        }
                    } else {
                        if (fp.getKeputusan() == null && fp.getUlasan() == null) {
                            continue;
                        }
                    }
                    listFasa.add(fp);
                }

                List<FasaPermohonanLog> senarai = fasaPermohonanManager.senaraiFasaLog(fp);

                for (FasaPermohonanLog fpl : senarai) {
                    if (fpl == null) {
                        continue;
                    }
                    senaraiFasaLog.add(fpl);
                }
            }

            if (permohonan.getPermohonanSebelum() != null) {
                list_tmp = permohonan.getPermohonanSebelum().getSenaraiFasa();

                for (FasaPermohonan fp : list_tmp) {

                    if (fp.getKeputusan() == null) {
                        continue;
                    }

                    listFasaSebelum.add(fp);
                }
            }

            Collections.sort(senaraiFasaLog, new Comparator<FasaPermohonanLog>() {
                /**
                 * Sorting where Y < T
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
                public int compare(FasaPermohonanLog o1, FasaPermohonanLog o2) {
                    return (int) o1.getIdLog() - (int) o2.getIdLog();
                }
            });
        }
        tabBean = tabManager.getTabFlow(ku.getKod(), stageId, idWorkFlow);
        finalStage = tabManager.isFinalStage(idWorkFlow, stageId);
        boolean toModifyDate = tabManager.resultDateToInsert(idWorkFlow, stageId);

        if (finalStage) {
            getContext().getRequest().setAttribute("pendaftar", Boolean.TRUE);
        }
        if (toModifyDate) {
            getContext().getRequest().setAttribute("modifyDate", Boolean.TRUE);
        }
    }

    @DefaultHandler
    public Resolution showForm() {
        Long t = System.currentTimeMillis();
        String message = getContext().getRequest().getParameter("message");
        String berangkai = getContext().getRequest().getParameter("berangkai");
        String reportGenerate = getContext().getRequest().getParameter("generateReport");
        KodCawangan kod = pengguna.getKodCawangan();

        if (finalStage) {
            getContext().getRequest().setAttribute("finalStage", finalStage);
        }

        if (StringUtils.isNotBlank(berangkai)) {
            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
        }
        if (StringUtils.isNotBlank(reportGenerate)) {
            boolean f = tabManager.getSimpanDanJana(idWorkFlow, stageId, txnCode);
            if (f) {
                getContext().getRequest().setAttribute("reportGenerated", Boolean.parseBoolean(reportGenerate));
            }
        }
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        LOGGER.debug("took = " + (System.currentTimeMillis() - t));
//        List<PenggunaPeranan> list = penggunaperananDao.findAll();
//        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

//        for (Pengguna p : lp) {
////            System.out.println("p : " + p.getIdPengguna());
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {
//                    //TODO : change peranan utama
//                    if (p.getPerananUtama().getKod().equals("7")
//                            && p.getStatus().getKod().equals("A")) {
//                        listPp.add(p);
//                    }
//                }
//            }
//        }
        return new ForwardResolution("/WEB-INF/jsp/keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution save() throws Exception {
        //got problem when using rehydrate
        long timeStart = System.currentTimeMillis();
        KodUrusan ku = null;
        BPelService service = new BPelService();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        LOGGER.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        LOGGER.debug("senaraisenaraiHakmilik =" + senaraiHakmilik.size());
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String genReport = getContext().getRequest().getParameter("saveAndGenerateReport");
        LOGGER.debug("TO Generate Report =" + genReport);

        for (HakmilikPermohonan hp : senaraiHakmilik) {
            if (hp.getPermohonan().getKodUrusan().getKod().equals("HKABS") || (hp.getPermohonan().getKodUrusan().getKod().equals("HKGHS"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HSPB"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HSPS"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HKSTK"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HSSTB"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HKSB"))
                    || (hp.getPermohonan().getKodUrusan().getKod().equals("HSC"))) {
                LOGGER.debug("senaraisenaraiHakmilik =" + hp.getHakmilik().getIdHakmilik());
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                hakmilik.setTarikhDaftar(new java.util.Date());
            }
        }

        if (permohonan != null) {
            ku = permohonan.getKodUrusan();
            if (StringUtils.isBlank(permohonan.getIdWorkflow())) {
                ku = permohonan.getKodUrusan();
                idWorkFlow = ku.getIdWorkflow();
            } else {
                idWorkFlow = permohonan.getIdWorkflow();
                ku = permohonan.getKodUrusan();
            }
            txnCode = ku.getKod();
            InfoAudit in = permohonan.getInfoAudit();
            if (permohonanRujLuar != null) {
                permohonanRujLuar.setPermohonan(permohonan);
                if (idPermohonan != null) {
                    if ((senaraiPermohonanRujukanLuar.isEmpty())) {
                        permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
                        permohonanRujLuar.setInfoAudit(info);
                    }

                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    permohonanRujLuar.setPermohonan(permohonan);
                    permohonanRujLuar.setInfoAudit(in);
                    notaService.simpanPermohonanRujLuar(permohonanRujLuar);
//                addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
                }
            }
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
            }
            finalStage = tabManager.isFinalStage(idWorkFlow, stageId);

        }
        LOGGER.debug("final stage = " + finalStage);
        InfoAudit info = new InfoAudit();
        KodKeputusan kk = null;
        if (fasaPermohonan.getKeputusan() != null) {
            kk = kodKeputusanDAO.findById(fasaPermohonan.getKeputusan().getKod());
        }
        String ulasanTmp = fasaPermohonan.getUlasan();
        //try to search existing fasa permohonan
        //if exist just update, if not, insert new row
        fasaPermohonan = fasaPermohonanDAO.findById(fasaPermohonan.getIdFasa());
        LOGGER.debug("fasaPermohonan=" + fasaPermohonan);
        if (fasaPermohonan != null) {
            LOGGER.debug("UPDATE Fasa Permohonan");
            info = fasaPermohonan.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            LOGGER.debug("Dimasukan Oleh :" + info.getDikemaskiniOleh().getIdPengguna());
        } else {
            LOGGER.debug("INSERT Fasa Permohonan");
            fasaPermohonan = new FasaPermohonan();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            LOGGER.debug("Dimasukan Oleh :" + info.getDimasukOleh().getIdPengguna());
        }
        fasaPermohonan.setInfoAudit(info);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setCawangan(pengguna.getKodCawangan());
        fasaPermohonan.setPermohonan(permohonan);
        if (kk != null) {
            //added for strata
            if (permohonan.getKodUrusan().getKod().equals("RTPS") || permohonan.getKodUrusan().getKod().equals("RTHS")) {
                FasaPermohonan mohonFasa = new FasaPermohonan();
                mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semaklaporan");
                if (kk.getKod().equals("L") && mohonFasa.getKeputusan().getKod().contains("DT")) {
                    addSimpleError("");
                } else {
                    fasaPermohonan.setKeputusan(kk);
                }
            } else {
                fasaPermohonan.setKeputusan(kk);
            }
        }
        if (StringUtils.isNotBlank(tarikhKeputusan)) {
            Date trh = sdf.parse(tarikhKeputusan);
            fasaPermohonan.setTarikhKeputusan(trh);
        } else {
            fasaPermohonan.setTarikhKeputusan(new Date());
        }
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setUlasan(ulasanTmp);
        fasaPermohonanManager.saveOrUpdate(fasaPermohonan);

        //final stage should save keputusan into permohonan table.
        if (finalStage) {
            info = permohonan.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());

            String kodKeputusanLama = "";
            if (permohonan.getKeputusan() != null) {
                kodKeputusanLama = permohonan.getKeputusan().getKod();
            }
            permohonan.setKeputusan(kk);
            permohonan.setTarikhKeputusan(fasaPermohonan.getTarikhKeputusan());
            permohonan.setKeputusanOleh(pengguna);
            permohonan.setTarikhKeputusan(fasaPermohonan.getTarikhKeputusan());
            permohonan.setUlasan(fasaPermohonan.getUlasan());
//            permohonan.setTarikhKeputusan(new java.util.Date());
            permohonan.setInfoAudit(info);
            permohonanManager.saveOrUpdate(permohonan);
            //TODO : generate report/surat if any
            String idKumpulan = permohonan.getIdKumpulan();
            //if berangkai, only last urusan will generate surat
            if (StringUtils.isBlank(idKumpulan)) {
                //jika terdapat keputusan lama, kena delete dulu report yg lama.
                //EX : pendaftaran , kalau daftar, akan generate surat daftar
                //kalau tolak, akan generate surat tolak.
                if (StringUtils.isNotBlank(kodKeputusanLama)) {
                    Map<String, String> map = tabManager.getOutComesReport(idWorkFlow, stageId, txnCode, kodKeputusanLama);
                    if (map != null) {
                        KodDokumen kd = kodDokumenDAO.findById(map.get("code"));
                        FolderDokumen fd = permohonan.getFolderDokumen();
                        Dokumen d = semakDokumenService.semakDokumen(kd, fd, idPermohonan);
                        if (d != null) {
                            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
                            for (HakmilikPermohonan hp : senaraiHakmilik) {
                                if (hp == null) {
                                    continue;
                                }
                                if (hp.getDokumen1() != null && (hp.getDokumen1().getIdDokumen() == d.getIdDokumen())) {
                                    hp.setDokumen1(null);
                                } else if (hp.getDokumen2() != null && (hp.getDokumen2().getIdDokumen() == d.getIdDokumen())) {
                                    hp.setDokumen2(null);
                                } else if (hp.getDokumen3() != null && (hp.getDokumen3().getIdDokumen() == d.getIdDokumen())) {
                                    hp.setDokumen3(null);
                                } else if (hp.getDokumen4() != null && (hp.getDokumen4().getIdDokumen() == d.getIdDokumen())) {
                                    hp.setDokumen4(null);
                                } else if (hp.getDokumen5() != null && (hp.getDokumen5().getIdDokumen() == d.getIdDokumen())) {
                                    hp.setDokumen5(null);
                                }
                                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                            }
                            dokumenService.deleteKandunganWithDokumen(d, fd);
                        }
                    }
                }

                //kalu regenerate takya nak padam surat2.
                Map<String, String> map = tabManager.getOutComesReport(idWorkFlow, stageId, txnCode, permohonan.getKeputusan().getKod());
                if (map != null) {
                    //TODO : insert into table?
                    //regenerate?
                    generateDokumen(map.get("generator"), map.get("code"));
                }
            }
        }

        addSimpleMessage(DEFAULT_SUCCESS_MSG);
        LOGGER.debug("took = " + (System.currentTimeMillis() - timeStart) + " ms");
        if (StringUtils.isNotBlank(genReport)) {
            return new RedirectResolution(StageActionBean.class, "genReport").addParameter("stageId", stageId).addParameter("keputusan", kk.getKod());
        } else {
            return new RedirectResolution(StatusActionBean.class).addParameter("message", DEFAULT_SUCCESS_MSG).addParameter("stageId", stageId);
        }
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
        LOGGER.debug("-----------TAJUK di StatusActionBean-------------");
//        doc.setTajuk(kd.getKod());
        doc.setTajuk(kd.getNama());
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
        updatePathDokumen(reportUtil.getDMSPath(), doc.getIdDokumen(), reportUtil.getContentType());

        if (permohonan.getSenaraiHakmilik().size() > 0) {
            List<HakmilikPermohonan> senarai = new ArrayList<HakmilikPermohonan>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                hp.setDokumen4(doc);
                senarai.add(hp);
            }
            hakmilikPermohonanService.saveHakmilikPermohonan(senarai);
        }
    }

    public Resolution agihPT() {

        String msg = "";
        //TODO INTEGRATION WITH BPEL
        StringBuilder sb = new StringBuilder();
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                LOGGER.info("TaskID ::" + taskID);
                LOGGER.info("idPermohonan ::" + idPermohonan);
//                Task task = WorkFlowService.delegateTask(ctx, taskID, pguna.getIdPengguna(), pengguna.getIdPengguna());
//                String stageId = task.getSystemAttributes().getStage();

                Permohonan permohonan = permohonanDAO.findById(idPermohonan);

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

                LOGGER.debug("cawangan " + pengguna.getKodCawangan());

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
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        LOGGER.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);

        return list;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String contentType) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(contentType);
        dokumenService.saveOrUpdate(d);
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public TabBean getTabBean() {
        return tabBean;
    }

    public void setTabBean(TabBean tabBean) {
        this.tabBean = tabBean;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public List<FasaPermohonanLog> getSenaraiFasaLog() {
        return senaraiFasaLog;
    }

    public void setSenaraiFasaLog(List<FasaPermohonanLog> senaraiFasaLog) {
        this.senaraiFasaLog = senaraiFasaLog;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public String getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(String kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public List<FasaPermohonan> getListFasaSebelum() {
        return listFasaSebelum;
    }

    public void setListFasaSebelum(List<FasaPermohonan> listFasaSebelum) {
        this.listFasaSebelum = listFasaSebelum;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}
