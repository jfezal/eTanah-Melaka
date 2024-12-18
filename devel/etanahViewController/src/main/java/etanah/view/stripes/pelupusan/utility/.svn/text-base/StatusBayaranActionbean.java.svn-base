/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import etanah.model.KandunganFolder;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.UrusanDokumen;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorIdPermohonanKelompok;
import etanah.service.KodService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisCarianBayaran;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import java.util.Calendar;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author wazer
 */
@UrlBinding("/pelupusan/utiliti/statusbayaran")
public class StatusBayaranActionbean extends AbleActionBean {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(StatusBayaranActionbean.class);
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<KandunganFolder> senaraiKandunganFolder;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    List<Permohonan> senaraiPermohonan;
    private List<DisCarianBayaran> senaraiBayaranTamatTempoh;
    private List<DisCarianBayaran> senaraiBayaranBelumTamat;
    private static String kodNegeri;
    private Pengguna pguna;
    private KandunganFolder folderDok;
    private boolean ptg = true;
    private static final boolean debug = LOG.isDebugEnabled();
    private int selectedItem = -1;
    private String jabatan = "";
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private KodUrusan ku;
    private KodUrusanDAO kodUrusanDAO;
    private List<UrusanDokumen> senaraiUrusanDokumen = new ArrayList<UrusanDokumen>();
    private UrusanDokumenDAO urusanDokumenDAO;
    private boolean flag = false;
    private KodJabatanDAO kodJabatanDAO;
    private Permohonan permohonan;
    private List<KodJabatan> senaraiKodJabatan;
    private final static String DEFAULT_VIEW = "/WEB-INF/jsp/pelupusan/utiliti/Utiliti_Carian_bayaran.jsp";
    @Inject
    KodService kodService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    ListUtil listUtil;
    @Inject
    RegService regService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PelupusanService pservice;
//    private Object FolderId;
    private int idFolder;
    private int idDokumen;
    private Notis n;

    @Inject
    public StatusBayaranActionbean(KodUrusanDAO kodUrusanDAO, UrusanDokumenDAO urusanDokumenDAO, KodDokumenDAO kodDokumenDAO,
            KodJabatanDAO kodJabatanDAO) {
        this.kodUrusanDAO = kodUrusanDAO;

        this.kodJabatanDAO = kodJabatanDAO;
    }

    @DefaultHandler
    public Resolution showForm() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }



        return new ForwardResolution(DEFAULT_VIEW);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws WorkflowException {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kod_Negeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        senaraiMohonTuntutKos = new ArrayList<PermohonanTuntutanKos>();
        senaraiKandunganFolder = new ArrayList<KandunganFolder>();
        senaraiMohonTuntutBayar = new ArrayList<PermohonanTuntutanBayar>();
        senaraiBayaranBelumTamat = new ArrayList<DisCarianBayaran>();
        senaraiBayaranTamatTempoh = new ArrayList<DisCarianBayaran>();
        senaraiPermohonan = new ArrayList<Permohonan>();
        List<String> senaraiPermohonanRaw = new ArrayList<String>();

        List<Pengguna> senaraiPguna = new ArrayList<Pengguna>();
//        senaraiPermohonan = disLaporanTanahService.getPelupusanService().findMohonByJabatanNKodUrusan("2");
        int count = 1;
        senaraiPguna = disLaporanTanahService.getPelupusanService().findPenggunaGroupBpelKodCaw("ptspoc", pguna.getKodCawangan() != null ? pguna.getKodCawangan().getKod() : new String());
        /*
         * TO GET ALL ID MOHON BASED ON ALL USER ON THAT BPEL GROUP (ptspoc)
         */
        for (Pengguna pengguna : senaraiPguna) {
            List<Task> taskList = new ArrayList<Task>();
            IWorkflowContext ctx;
            try {
                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
                taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            } catch (Exception e) {
                LOG.error("error ::" + e.getMessage());
            }

            for (Task task : taskList) {
                task.getSystemMessageAttributes().getTextAttribute1();
                if (senaraiPermohonanRaw.size() > 0) {
                    Boolean flagMohon = false;
                    for (String rawMohon : senaraiPermohonanRaw) {
                        if (task.getSystemMessageAttributes().getTextAttribute1().equalsIgnoreCase(rawMohon)) {
                            flagMohon = true;
                            break;
                        }
                    }
                    if (!flagMohon) {
                        if (task.getSystemMessageAttributes().getTextAttribute1().matches("(.*)DIS(.*)")) {
                            senaraiPermohonanRaw.add(task.getSystemMessageAttributes().getTextAttribute1());
                        }
                    }

                } else {
                    if (task.getSystemMessageAttributes().getTextAttribute1().matches("(.*)DIS(.*)")) {
                        senaraiPermohonanRaw.add(task.getSystemMessageAttributes().getTextAttribute1());
                    }
                }
//                senaraiPermohonanRaw.add(mohon);
            }
        }
        /*
         * FIND ALL ID MOHON ON THE WHITELIST & BLACKLIST
         */
        senaraiPermohonan = disLaporanTanahService.getPelupusanService().findPermohonanByListIDMohon(senaraiPermohonanRaw);

        for (Permohonan mohon : senaraiPermohonan) {
            LOG.info(count + " of " + senaraiPermohonan.size());
            LOG.info("THIS IS ID PERMOHONAN" + mohon.getIdPermohonan());
            KandunganFolder kf = new KandunganFolder();
            senaraiMohonTuntutKos = disLaporanTanahService.getPelupusanService().findListMohonTuntutKosByIdPermohonan(mohon.getIdPermohonan());

            int numUrusan = getListUrusan(mohon);
            System.out.println("numUrusan :::::::::::::::::" + numUrusan);
            String kodDok = reportName(numUrusan, kod_Negeri);
            System.out.println(":::::::::::::::::" + kodDok);
            if (!StringUtils.isEmpty(kodDok)) {
                senaraiKandunganFolder = findListKandunganFolderByIdDok(mohon.getFolderDokumen());
                LOG.info("THIS IS SENARAI URUSAN --->" + senaraiKandunganFolder.size());
                for (KandunganFolder kfold : senaraiKandunganFolder) {
                    if (kfold.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kodDok)) {
                        kf = kfold;
                        LOG.info("THIS IS COPY BEAN ->" + kodDok);
                        break;
                    }
                }
            }
            for (PermohonanTuntutanKos mohonTuntutKos : senaraiMohonTuntutKos) {
                PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                ptb = disLaporanTanahService.getPelupusanService().findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                if (ptb == null) {
                    DisCarianBayaran dcb = new DisCarianBayaran();
                    dcb.setMohon(mohonTuntutKos.getPermohonan());
                    if (mohonTuntutKos.getHakmilikPermohonan() != null && mohonTuntutKos.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
                        dcb.setKodBPM(mohonTuntutKos.getHakmilikPermohonan().getBandarPekanMukimBaru());
                    } else {
                        if (mohonTuntutKos.getHakmilikPermohonan() != null && mohonTuntutKos.getHakmilikPermohonan().getHakmilik() != null && mohonTuntutKos.getHakmilikPermohonan().getHakmilik().getBandarPekanMukim() != null) {
                            dcb.setKodBPM(mohonTuntutKos.getHakmilikPermohonan().getHakmilik().getBandarPekanMukim());
                        }
                    }
                    dcb.setKodUrusan(mohonTuntutKos.getPermohonan().getKodUrusan());
                    if (kf != null) {
                        if (mohonTuntutKos.getPermohonan() != null) {
                            if (mohonTuntutKos.getPermohonan().getFolderDokumen() != null) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Calendar curDate = Calendar.getInstance();
                                if (kf.getFolder() != null) {
//                                            LOG.info("TARIKH BYRN AKHIR :-"+kf.getDokumen().getTarikhDokumen());
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(kf.getDokumen().getInfoAudit().getTarikhMasuk());

                                    if (mohonTuntutKos.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                                        LOG.info("::::::::: PBMT");
                                        List<Notis> listNotis = pservice.getListNotis2(mohonTuntutKos.getPermohonan().getIdPermohonan(), "N5A");
                                        if (!listNotis.isEmpty()) {
                                            n = listNotis.get(0);
                                        }
                                        if (n != null) {
                                            if (n.getTempohBulan() != 0) {
                                                LOG.info("::::::::: PBMT : get bulan from notis N5A");
                                                c.add(Calendar.MONTH, n.getTempohBulan());

                                            }
                                        } else {
                                            if (mohonTuntutKos.getPermohonan().getTempohBulan() != null) {
                                                LOG.info("::::::::: PBMT : get bulan from mohon");
                                                c.add(Calendar.MONTH, mohonTuntutKos.getPermohonan().getTempohBulan());

                                            }
                                        }



                                    } else {
                                        c.add(Calendar.MONTH, 3);
                                    }

                                    dcb.setTarikhTerimaNotis(kf.getDokumen().getInfoAudit().getTarikhMasuk());
                                    dcb.setTarikhBayaranAkhir(c.getTime());
                                    LOG.info("THIS IS REAL DATE->" + kf.getDokumen().getInfoAudit().getTarikhMasuk());
                                    LOG.info("THIS IS BYRN AKHIR (REAL DATE+30 days)->" + c.getTime());
                                    if (curDate.getTime().after(dcb.getTarikhBayaranAkhir())) {
                                        // BLACKLIST
                                        senaraiBayaranTamatTempoh.add(dcb);
                                    } else {
                                        // WHITELIST
                                        senaraiBayaranBelumTamat.add(dcb);
                                    }
                                }
                            }
                        }
                    }

                    break;
                }
            }
            count++;
        }



    }

    private int getListUrusan(Permohonan permohonan) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PBMT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 2
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 3
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 5
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 8
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 9
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 12
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 13
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 14
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 15
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 18
                : permohonan.getKodUrusan().getKod().equals("RAYA") ? 19
                : permohonan.getKodUrusan().getKod().equals("RAYL") ? 20
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 21
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 22
                : permohonan.getKodUrusan().getKod().equals("JMRE") ? 23
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 24
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 25
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 26
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 27
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 28
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 29
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 30
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 31
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 32
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 33
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 34
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 35
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 36
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 37
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 38
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 39
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 40
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 41
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 42
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 43
                : 0;
        return numUrusan;

    }

    public Resolution tolakPermohonan() throws WorkflowException, StaleObjectException {
        IWorkflowContext ctxW = null;
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan p = new Permohonan();
        p = (Permohonan) disLaporanTanahService.findObject(p, new String[]{idPermohonan}, 0);
        LOG.info("Try skip stage");
        String taskId = new String();
        String nextStage = new String();
        ctxW = WorkFlowService.authenticate("sysspoc1");
        List<Task> taskList = null;
        do {
            taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
        } while (taskList.size() == 0);
        if (taskList.size() > 0) {
            Task a = taskList.get(0);
            taskId = a.getSystemAttributes().getTaskId();
            LOG.info("Task Id :" + taskId);
            WorkFlowService.acquireTask(taskId, ctxW);
            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "DT");
        }
        rehydrate();
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public String reportName(int numUrusan, String negeri) {
        String reportName = new String();
        switch (numUrusan) {
            case 1: //PBMT
                if (negeri.equalsIgnoreCase("04")) {
                    reportName = "N5A";
                } else {
                    reportName = "5AA";
//                    reportName = "LPE";
                }
                break;
            case 5: //LPSP
                if (negeri.equalsIgnoreCase("04")) {
                    reportName = "SL";
                } else {
//                    
                }
                break;
            case 6: //MMMCL
                if (negeri.equalsIgnoreCase("04")) {
                    reportName = "5A";
                } else {
//                    
                }
                break;
            case 19: //RAYA
                if (negeri.equalsIgnoreCase("04")) {
                    reportName = "SL";
                } else {
//                    
                }

                break;
            case 20: //RAYL
                if (negeri.equalsIgnoreCase("04")) {
                    reportName = "SL";
                } else {
                    reportName = "5AA";
                }

                break;

        }
        return reportName;

    }

    public Resolution filterByJabatan() {
//        LOG.info("kodJabatan :" + getContext().getRequest().getParameter("kodJabatan"));
//        LOG.info("jabatan : " + jabatan);
        String kodJab = jabatan;
//        LOG.info("kodJab :" + kodJab);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodJab == null || kodJab.equals("") || kodJab.equals("0")) {
            sql = "FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.kodTuntut.kod = :kod_tuntut";
            q = s.createQuery(sql);
        }
        senaraiKodUrusan = q.list();
        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");

    }

    public List<PermohonanTuntutanKos> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.PermohonanTuntutanKos p where b.permohonan.idPermohonan LIKE :idMohon AND b.permohonan.status = 'AK'");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }

    public List<PermohonanTuntutanKos> findAllKelompokDIS() {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan LIKE :idMohon  AND b.permohonan.status = 'AK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        Query q = sessionProvider.get().createQuery(query.toString());
        q.setString("idMohon", "%DIS%");
        return q.list();
    }

    private KandunganFolder findKandunganFolderByIdDok(String idDokumen, FolderDokumen folderDok) {
        String query = "SELECT kf From etanah.model.KandunganFolder kf where kf.folder.folderId =:idFolder and kf.dokumen.kodDokumen.kod = :idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idDokumen", idDokumen);
        q.setLong("idFolder", folderDok.getFolderId());
        return (KandunganFolder) q.uniqueResult();

    }

    private List<KandunganFolder> findListKandunganFolderByIdDok(FolderDokumen folderDok) {
        String query = "SELECT kf From etanah.model.KandunganFolder kf where kf.folder.folderId =:idFolder";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFolder", folderDok.getFolderId());
        return q.list();

    }

    private List<KandunganFolder> findByIdDok(String idDokumen) {
        String query = "SELECT kf From etanah.model.KandunganFolder kf where kf.dokumen.kodDokumen.kod =:idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idDokumen", idDokumen);
        return q.list();

    }
//    public SuratRujukanLuar getSenaraiSuratRujukanLuarByIDRujLuar(String idRujukan) {
//        String query = "SELECT b FROM etanah.model.SuratRujukanLuar b where b.permohonanRujukanLuar.idRujukan = :idRujukan";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idRujukan", idRujukan);
//        return (SuratRujukanLuar) q.uniqueResult();
//    }

    private List<Dokumen> findByDokumen(String tarikh) {
        String query = "SELECT d from.etanah.model.Dokumen d where d.tarikhDokumen =: tarikhDokumen";
        Query q = sessionProvider.get().createQuery(query).setString("tarikh", tarikh);
        return q.list();
    }

    public List<KodJabatan> getSenaraiKodJabatan() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        if (p.getPerananUtama().getKod() != null) {
            if (p.getPerananUtama().getKod().equals("2")) {
                return kodJabatanDAO.findAll();
            } else {
                return kodService.findKodJabatan(p.getPerananUtama().getKodJabatan().getKod());
            }
        } else {
            return kodJabatanDAO.findAll();
        }
    }

    public List<DisCarianBayaran> getSenaraiBayaranTamatTempoh() {
        return senaraiBayaranTamatTempoh;
    }

    public void setSenaraiBayaranTamatTempoh(List<DisCarianBayaran> senaraiBayaranTamatTempoh) {
        this.senaraiBayaranTamatTempoh = senaraiBayaranTamatTempoh;
    }

    public List<DisCarianBayaran> getSenaraiBayaranBelumTamat() {
        return senaraiBayaranBelumTamat;
    }

    public void setSenaraiBayaranBelumTamat(List<DisCarianBayaran> senaraiBayaranBelumTamat) {
        this.senaraiBayaranBelumTamat = senaraiBayaranBelumTamat;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        StatusBayaranActionbean.kodNegeri = kodNegeri;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public List<PermohonanTuntutanBayar> getSenaraiMohonTuntutBayar() {
        return senaraiMohonTuntutBayar;
    }

    public void setSenaraiMohonTuntutBayar(List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar) {
        this.senaraiMohonTuntutBayar = senaraiMohonTuntutBayar;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    public KodUrusanDAO getKodUrusanDAO() {
        return kodUrusanDAO;
    }

    public void setKodUrusanDAO(KodUrusanDAO kodUrusanDAO) {
        this.kodUrusanDAO = kodUrusanDAO;
    }

    public List<UrusanDokumen> getSenaraiUrusanDokumen() {
        return senaraiUrusanDokumen;
    }

    public void setSenaraiUrusanDokumen(List<UrusanDokumen> senaraiUrusanDokumen) {
        this.senaraiUrusanDokumen = senaraiUrusanDokumen;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public PermohonanTuntutanKosDAO getPermohonanTuntutanKosDAO() {
        return permohonanTuntutanKosDAO;
    }

    public void setPermohonanTuntutanKosDAO(PermohonanTuntutanKosDAO permohonanTuntutanKosDAO) {
        this.permohonanTuntutanKosDAO = permohonanTuntutanKosDAO;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public KodJabatanDAO getKodJabatanDAO() {
        return kodJabatanDAO;
    }

    public void setKodJabatanDAO(KodJabatanDAO kodJabatanDAO) {
        this.kodJabatanDAO = kodJabatanDAO;
    }

    public KodService getKodService() {
        return kodService;
    }

    public void setKodService(KodService kodService) {
        this.kodService = kodService;
    }

    public KandunganFolder getFolderDok() {
        return folderDok;
    }

    public void setFolderDok(KandunganFolder folderDok) {
        this.folderDok = folderDok;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
//    private List<KandunganFolder> findByIdDok(String idDokumen) {
//        
//        String query = "SELEECT kf From etanah.model.KandunganFolder kf where kf.dokumen =: idDokumen";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString(idDokumen, idDokumen);
//        return q.list();
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Notis getN() {
        return n;
    }

    public void setN(Notis n) {
        this.n = n;
    }
}
