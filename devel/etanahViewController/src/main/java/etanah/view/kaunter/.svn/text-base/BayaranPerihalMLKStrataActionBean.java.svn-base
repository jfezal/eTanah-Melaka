/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import etanah.model.CaraBayaran;
import java.text.ParseException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodRujukan;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import java.text.DateFormat;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KandunganFolder;
import java.util.Date;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKutipanDAO;
import etanah.model.Hakmilik;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTuntut;
import etanah.model.KodUrusan;
import etanah.model.LanjutanTempoh;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.service.common.TaskDebugService;
import etanah.view.BasicTabActionBean;
import etanah.view.strata.CommonService;
import etanah.view.strata.InitiateTaskService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import etanah.dao.KodRujukanDAO;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;

/**
 * Based on given idPermohonan/idPerserahan, suggest the next Urusan for the
 * Permohonan. The cases: 1 If the given Permohonan is NOT completed: 1.1 If the
 * Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
 * from user (e.g. Submit more documents, pay fees etc) 1.2 If not in SPOC's
 * senaraiTugasan, display the status 2 If Permohonan is completed, check the
 * next suggested Urusan for the Permohonan. E.g. after Consent's "Kelulusan
 * Pindahmilik", next is Registration's "PindahMilik Tanah". 3 If Permohonan is
 * completed but rejected, suggest Urusan for Rayuan.
 *
 * @author user
 *
 */
@HttpCache(allow = false)
@UrlBinding("/kaunter/strata/MLK/BayaranPerihal")
public class BayaranPerihalMLKStrataActionBean extends BasicTabActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    LelongService lelongService;
    @Inject
    StrataPtService strService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodBankDAO kodBankDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private CaraBayaranDAO caraBayaranDAO;
    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private AkaunService akaunService;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusDAO;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private GeneratorNoResit2 noResitGenerator;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    StrataPtService strataService;
    private List<DokumenKewanganBayaran> dkbList;
    private String resitNo;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private String kodNegeri;
    private String mod = "";
    BigDecimal total = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranPerihalMLKStrataActionBean.class);
    private String idPermohonan;
    private List<String> tarikhCek = new ArrayList<String>();
    List<BayaranValue> listBayaran = new ArrayList();
    List<BayaranValue> listSahBayaran = new ArrayList();
    IWorkflowContext ctxW = null;
    Pengguna pguna = null;
    Permohonan permohonan;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos1;
    @Inject
    KodUrusanDAO kodurusanDAO;
    private String idHakmilik;
    @Inject
    InitiateTaskService initiateTask;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strataservice;
    @Inject
    private TaskDebugService tds;
    private String taskId;
    private Boolean isBayaranVisible;
    private Boolean bayaran2Visible;
    private Boolean bayaran3Visible = Boolean.FALSE;
    private Permohonan permohonRBHS;
    private String userid;
    private String p14Amohon;
    private String PBBBmohon;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;

    @DefaultHandler
    public Resolution showForm() {
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }
        return new JSP("kaunter/bayaran_perihal_strata_mlk.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        return new RedirectResolution(PermohonanKaunter.class);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("IDPERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
//kes kalu nk filter by modul
        if (permohonan.getKodUrusan().getJabatan().getKod().equals("")) {
            /**
             * kalau xpakai table mohon tuntut kos, set kat dlm ni. Set
             * jumlahCaj sekali!
             */
            if (total == null) {
                total = total; // set if total is null
            } else {
                total = total.add(total); //total from all amount
            }
        } else {
//            senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            // Added code to exclude sewaBaru

            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pguna);
                stageId = t.getSystemAttributes().getStage();
            }

            LOG.info("stage Id: " + stageId);

            kodNegeri = conf.getProperty("kodNegeri");
            LOG.info("kodNegeri: " + kodNegeri);

            senaraiMohonTuntutKos1 = strService.findMohonTuntutKosExcludeBayaran(idPermohonan, "SN5");
            LOG.info("senaraiMohonTuntutKos exclude SN5" + senaraiMohonTuntutKos1.size());

            //saving default data in mohontuntutkos for bayaran5F
            if (stageId.equals("bayaran5F")) {
                senaraiMohonTuntutKos = strService.findMohonTuntutKosIncludeBayaran(idPermohonan, "SN5");
                LOG.info("bayaran 5F senaraiMohonTuntutKos" + senaraiMohonTuntutKos.size());
                if (senaraiMohonTuntutKos.isEmpty()) {
                    LOG.info("senaraiMohonTuntutKos is null for bayaran5F");
                    LOG.info("bayaran 5F saving");
                    PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(infoAudit);
                    mohonTuntutKos.setCawangan(pguna.getKodCawangan());
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setItem("Bayaran Notis5F");
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        BigDecimal bd = new BigDecimal(20);
                        mohonTuntutKos.setAmaunTuntutan(bd);
                    } else {
                        BigDecimal bd = new BigDecimal(20);
                        mohonTuntutKos.setAmaunTuntutan(bd);
                    }
                    KodTuntut kt = kodTuntutDAO.findById("SN5");
                    mohonTuntutKos.setKodTuntut(kt);
                    PermohonanTuntutanKos pk = senaraiMohonTuntutKos1.get(0);
                    LOG.info("pk" + pk);
                    mohonTuntutKos.setKodTransaksi(pk.getKodTransaksi());
                    strService.simpanBayaran(mohonTuntutKos);
                    LOG.info("bayaran 5F saved");
                }
            }

            if (stageId.equals("terimabayaran") || stageId.equals("terimabayaran2")) {
                senaraiMohonTuntutKos = strService.findMohonTuntutKosExcludeBayaran(idPermohonan, "SN5");
            }
            if (stageId.equals("bayaran5F")) {
                senaraiMohonTuntutKos = strService.findMohonTuntutKosIncludeBayaran(idPermohonan, "SN5");
            }
            LOG.info("senaraiMohonTuntutKos_strata : " + senaraiMohonTuntutKos.size());
            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
                LOG.info("senaraiMohonTuntutBayar_strata size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
                if (senaraiMohonTuntutBayar.isEmpty()) {
                    BayaranValue list = new BayaranValue();
                    list.setIdKos(pTK.getIdKos());
                    list.setIdPermohonan(pTK.getPermohonan().getIdPermohonan());
                    list.setAmaun(pTK.getAmaunTuntutan());
                    list.setKodUrusan(pTK.getPermohonan().getKodUrusan().getKod());
                    list.setNamaUrusan(pTK.getItem());
                    if (pTK != null && pTK.getKodTransaksi() != null) {
                        list.setKodTransaksi(pTK.getKodTransaksi().getKod());
                    }
                    list.setPermohonan(permohonan);
                    list.setTuntutKos(pTK);
                    listBayaran.add(list);

                    if (total == null) {
                        total = pTK.getAmaunTuntutan();
                    } else {
                        total = total.add(pTK.getAmaunTuntutan());
                    }
                }
            }
            LOG.info("listBayaran : " + listBayaran.size());
            jumlahCaj = total;
        }
        /*Stage:terimabayaran: if user not pay with in 14 days, need to go stage: suratperingatan
         Stage:terimabayaran2: if user not pay with in 30 days redirect to spoc*/
        checkBayaran();
    }

    public Resolution save() throws ParseException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");

        if (permohonan.getKodUrusan().getJabatan().getKod().equals("24")) {
            total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
        }

        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        mod = modKutip.loadPenyerahFromSession(ctx);
        try{
            //set KEW_DOKUMEN
//            resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
            resitNo = noResitGenerator.getAndLockSerialNo(pengguna);       //new noResitGenerator
            LOG.info("RESIT NO:" + resitNo);
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resitNo);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(total);
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setAkaun(akTerima);
            dk.setIdKaunter(pengguna.getIdKaunter());
            dk.setTarikhTransaksi(now);
            if (mod != null && mod.length() > 0) {
                dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
            }
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
                listSahBayaran.add(mtk);
                LOG.info("START KEW_TRANS");
                Transaksi trans = new Transaksi();
                trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                trans.setAkaunDebit(akTerima);
                trans.setAmaun(mtk.getAmaun());
                trans.setUntukTahun(year);
                trans.setTahunKewangan(year);
                trans.setPermohonan(mtk.getPermohonan());
                trans.setDokumenKewangan(dk);
                trans.setPerihal(mtk.getNamaUrusan());
                trans.setStatus(kodStatusDAO.findById('T'));
                trans.setKodUrusan(mohon.getKodUrusan().getKod());
                trans.setKuantiti(1);
                trans.setInfoAudit(ia);
                trans.setCawangan(caw);
                transaksiDAO.save(trans);
                LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());

                LOG.info("START PermohonanTuntutanBayaran");
                PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
                ptb.setPermohonan(permohonan);
                ptb.setDokumenKewangan(dk);
                ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                ptb.setTarikhBayar(now);
                ptb.setInfoAudit(ia);
                permohonanTuntutanBayarDAO.save(ptb);
                LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
            }

            //set KEW_AKAUN ---> AKH
            LOG.info(akTerima.getCawangan().getKod());
            if (!total.equals(BigDecimal.ZERO)) {
                s.lock(akTerima, LockMode.UPGRADE);
                akTerima.setBaki(akTerima.getBaki().add(total));
            }
            //dokumen dokumen_capai folder folder_dok
            long idFolder = mohon.getFolderDokumen().getFolderId();
            Dokumen resit = new Dokumen();
            resit.setFormat("application/pdf");
            resit.setInfoAudit(ia);
            resit.setKlasifikasi(klasifikasiAm);
            KodDokumen kodResit = kodDokumenDAO.findById("RBY");
            resit.setKodDokumen(kodResit);
            resit.setNoVersi("1.0");
            LOG.info(kodResit.getNama());
            resit.setTajuk("Resit Bayaran Proses");
            resit = dokumenDAO.saveOrUpdate(resit);
            tx.commit();
            
            //new requirement by hasil's team
            penyataPemungutService.savePenyataPemungut(dk);

            LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
            String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path + path2, pengguna);
            } else {
                reportUtil.generateReport("HSLResitUrusanTanah.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path + path2, pengguna);
            }
            resit.setNamaFizikal(reportUtil.getDMSPath());
            getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
            dokumenDAO.update(resit);
            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(resit);
            kf.setFolder(folderDokumenDAO.findById(idFolder));
            kf.setInfoAudit(ia);
            kf.setCatatan("");
            kandunganFolderDAO.save(kf);
            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
            }

            LOG.info("sstage Id_saving: " + stageId);

            FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            StringBuilder sb = new StringBuilder();
            
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";

            LOG.info("-----Generating IdStrata-----:");
            /*if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                //strService.generateHakmilikStrata(ia, permohonan, pengguna);
                comm.generateHakmilikStrata3(ia, permohonan, pengguna);
            } else {
                comm.generateHakmilikStrata(ia, permohonan, pengguna);
            }*/
            LOG.info("-----IdStrata Generated-----:");
            
            try {
                List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
                for (Map<String, String> map : list) {
                    String taskID = map.get("taskId");
                    String idMOhon = map.get("idPermohonan");
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(idMOhon);
                    LOG.info("TaskID ::" + taskID);
                    LOG.info("idPermohonan ::" + idMOhon);
                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
    //                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                }

            } catch (Exception ex) {
                LOG.error(ex);
                return new RedirectResolution(BayaranPerihalMLKStrataActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }

            if (stageId.equals("bayaran5F")) {
                addSimpleMessage(permohonan.getIdPermohonan() + " - Urusan telah selesai.");
            } else {
                addSimpleMessage(permohonan.getIdPermohonan() + " - Penghantaran Berjaya.");
            }
        }catch (Exception e) {
            LOG.error(e);
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
        } finally {
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) throws ParseException {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        LOG.info("START CARABAYAR:-----------------------------");
        LOG.info("senaraiCaraBayaran.size() : " + senaraiCaraBayaran.size());
        for (int m = 0; m < senaraiCaraBayaran.size(); m++) {
            CaraBayaran cb = senaraiCaraBayaran.get(m);
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                if ((crByr.getKod().equals("CB")) || (crByr.getKod().equals("CC")) || (crByr.getKod().equals("CT"))
                        || (crByr.getKod().equals("CL")) || (crByr.getKod().equals("IB")) || (crByr.getKod().equals("BC"))) {
                    Date d = sdf.parse(tarikhCek.get(m));
                    cb.setTarikhCek(d);
                }
                if (cb.getBank() != null) {
                    KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                    cb.setBank(bank);
                }
                if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
                    cb.setBank(kodBankDAO.findById("PMB"));
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                cb.setKodCaraBayaran(crByr);
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
        }
        LOG.info("adkb : " + adkb.size());
        dk.setSenaraiBayaran(adkb);
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        LOG.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);
        return list;
    }

    public void checkBayaran() {
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, "terimabayaran");

        if (fasaPermohonan != null) {
            InfoAudit dt = fasaPermohonan.getInfoAudit();
            Date userDate = dt.getTarikhMasuk();
            LOG.debug("----User date first time login to terimabayaran stage from mohon_fasa----:" + userDate);

            long days;
            days = getDaysBetweenDates(userDate);
            LOG.info("----Days between mohon fasa and current system date----:" + days);

            if (stageId.equals("terimabayaran")) {
                if (days > 14) {
                    LOG.info("----expired 14 days----:");
                    isBayaranVisible = Boolean.FALSE;
                } else {
                    LOG.info("----not expired----:");
                    isBayaranVisible = Boolean.TRUE;
                }
                LOG.info("----isBayaranVisible----:" + isBayaranVisible);
            }

            if (stageId.equals("terimabayaran2")) {
                LOG.info("----terimabayaran2----:");
                permohonRBHS = strataservice.findIDSblmByKodUrusan(idPermohonan, "RBHS"); //RBHS registered or not   
                LOG.info("----permohonRBHS----:" + permohonRBHS);
                if (permohonRBHS == null) { // RBHS not registered                    
                    if (days > 30) {    // 30 days expired
                        LOG.info("----expired 30 days----:");
                        try {
                            initiateToPenguatkuasaan();
                        } catch (Exception ex) {
                            LOG.error(ex);
                        }
                        bayaran2Visible = Boolean.FALSE;
                    } else {
                        LOG.info("----not expired 30 days----:");// 30 days not expired                        
                        bayaran2Visible = Boolean.TRUE;
                    }
                } else { // RBHS registered
                    LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
                    LOG.info("----ID MH for lanjutTempoh / RBHS----:" + permohonRBHS.getSenaraiHakmilik().get(0).getId());
                    lanjutTempoh = strataservice.findMohonLanjutTempoh(permohonRBHS.getSenaraiHakmilik().get(0).getId());
                    LOG.info("----lanjutTempoh----:" + lanjutTempoh);
                    long days1 = 0;
                    if (lanjutTempoh != null && lanjutTempoh.getTarikhAkhirDilulus() != null) { //jika RBHS da selesai
                        LOG.info("----TarikhAkhirDilulus from lanju_tempoh-RBHS----:" + lanjutTempoh.getTarikhAkhirDilulus());
                        days1 = getDaysBetweenDates(lanjutTempoh.getTarikhAkhirDilulus());
                        LOG.info("----days between current date and TarikhAkhirDilulus--lanju_tempoh-RBHS--:" + days1);
                        if (days1 <= 0) {
                            LOG.info("----not expired----:");
                            bayaran2Visible = Boolean.TRUE;
                        } else {
                            LOG.info("----Days expired-----initiating to P14A----:");
                            try {
                                initiateToPenguatkuasaan();
                            } catch (Exception ex) {
                                LOG.error(ex);
                            }
                            bayaran2Visible = Boolean.TRUE;
                        }
                    } else { //Jika RBHS dalam proses
                        bayaran2Visible = Boolean.FALSE;
                    }
                }
                LOG.info("----bayaran2Visible----:" + bayaran2Visible);
            }
        }
    }

    public long getDaysBetweenDates(Date userDate) {

        Date currDate = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.clear();
        Calendar c2 = Calendar.getInstance();
        c2.clear();
        c1.set(userDate.getYear() + 1900, userDate.getMonth(), userDate.getDate());
        c2.set(currDate.getYear() + 1900, currDate.getMonth(), currDate.getDate());

        // Print out the dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LOG.info("----User date----:" + sdf.format(c1.getTime()));
        LOG.info("----current date----:" + sdf.format(c2.getTime()));

        // Get the represented date in milliseconds
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = time2 - time1;

        // Difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }

    public Resolution suratPeringatan() throws ParseException {
        StringBuilder sb = new StringBuilder();
        try {
            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan1 = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                //WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
                WorkFlowService.updateTaskOutcome(taskID, ctxW, "TB");
                LOG.info("----approved to next stage----:");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(BayaranPerihalStrataActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        addSimpleMessage(permohonan.getIdPermohonan() + " - Penghantaran Berjaya.");
        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran2.jsp");
    }

    public void withDrawIdPermohonan() {
        try {
            List senaraiTask = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            if (senaraiTask.isEmpty()) {
            } else {

                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    LOG.info(task);
                    taskId = task.getSystemAttributes().getTaskId();
                }
            }
            WorkFlowService.withdrawTask(taskId);
            permohonan.setStatus("BP");
            lelongService.saveOrUpdate(permohonan);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void initiateToPenguatkuasaan() throws ParseException {
        try {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna peng = ctx.getUser();
//            String[] kodursns = {"PBBS", "PBBD", "PBS", "PBTS", "PHPC", "PHPP"};
             String[] kodursns = {"PBBS", "PBBD", "PBS"};
            if (ArrayUtils.contains(kodursns, permohonan.getKodUrusan().getKod())) {
                KodUrusan ku = kodurusanDAO.findById("P14A");
                Permohonan P14Apermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "P14A");
                Permohonan PBBBpermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "PBBB");
                if (P14Apermohon == null) {
                    P14Apermohon = initiateTask.generatePermohonanBaru(permohonan, ku, peng);
                    initiateTask.setHakmilikPermohonan(permohonan, P14Apermohon);
                }
                if (PBBBpermohon == null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    String[] name = {"idHakmilik"};
                    Object[] value = {idHakmilik};
                    List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                    KodUrusan kod = kodUrusanDAO.findById("PBBB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                }
                Map m = tds.traceTask(P14Apermohon.getIdPermohonan());
                Map m2 = tds.traceTask(PBBBpermohon.getIdPermohonan());
                String user = (String) m.get("participant");
                String user2 = (String) m2.get("participant");
                p14Amohon = P14Apermohon.getIdPermohonan();
                PBBBmohon = PBBBpermohon.getIdPermohonan();
                userid = user.substring(user.indexOf(".") + 1);
            }
            if (permohonan.getKodUrusan().getKod().equals("PSBS")) {
                //P22B
                KodUrusan ku = kodurusanDAO.findById("P22B");
                Permohonan P22Bpermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "P22B");
                if (P22Bpermohon == null) {
                    P22Bpermohon = initiateTask.generatePermohonanBaru(permohonan, ku, peng);
                    initiateTask.setHakmilikPermohonan(permohonan, P22Bpermohon);
                }
                Map m = tds.traceTask(P22Bpermohon.getIdPermohonan());
                String user = (String) m.get("participant");
                p14Amohon = P22Bpermohon.getIdPermohonan();
                userid = user.substring(user.indexOf(".") + 1);
                //PBBB
                Permohonan PBBBpermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "PBBB");
                if (PBBBpermohon == null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    String[] name = {"idHakmilik"};
                    Object[] value = {idHakmilik};
                    List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                    KodUrusan kod = kodUrusanDAO.findById("PBBB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);                    
                    Map m2 = tds.traceTask(PBBBpermohon.getIdPermohonan());                    
                    String user2 = (String) m2.get("participant");                    
                    PBBBmohon = PBBBpermohon.getIdPermohonan();
                    userid = user.substring(user2.indexOf(".") + 1);
                }
            }


           if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                KodUrusan ku = kodurusanDAO.findById("P14A");
                Permohonan P14Apermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "P14A");
                Permohonan PBCTBpermohon = strataservice.findIDSblmByKodUrusan(idPermohonan, "PBCTB");
                if (P14Apermohon == null) {
                    P14Apermohon = initiateTask.generatePermohonanBaru(permohonan, ku, peng);
                    initiateTask.setHakmilikPermohonan(permohonan, P14Apermohon);
                }
                if (PBCTBpermohon == null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    String[] name = {"idHakmilik"};
                    Object[] value = {idHakmilik};
                    List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                    KodUrusan kod = kodUrusanDAO.findById("PBCTB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                }
                Map m = tds.traceTask(P14Apermohon.getIdPermohonan());
                Map m2 = tds.traceTask(PBCTBpermohon.getIdPermohonan());
                String user = (String) m.get("participant");
                String user2 = (String) m2.get("participant");
                p14Amohon = P14Apermohon.getIdPermohonan();
                PBBBmohon = PBCTBpermohon.getIdPermohonan();
                userid = user.substring(user.indexOf(".") + 1);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<BayaranValue> getListBayaran() {
        return listBayaran;
    }

    public void setListBayaran(List<BayaranValue> listBayaran) {
        this.listBayaran = listBayaran;
    }

    public List<BayaranValue> getListSahBayaran() {
        return listSahBayaran;
    }

    public void setListSahBayaran(List<BayaranValue> listSahBayaran) {
        this.listSahBayaran = listSahBayaran;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsBayaranVisible() {
        return isBayaranVisible;
    }

    public void setIsBayaranVisible(Boolean isBayaranVisible) {
        this.isBayaranVisible = isBayaranVisible;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Boolean getBayaran2Visible() {
        return bayaran2Visible;
    }

    public Boolean getBayaran3Visible() {
        return bayaran3Visible;
    }

    public void setBayaran3Visible(Boolean bayaran3Visible) {
        this.bayaran3Visible = bayaran3Visible;
    }

    public void setBayaran2Visible(Boolean bayaran2Visible) {
        this.bayaran2Visible = bayaran2Visible;
    }

    public Permohonan getPermohonRBHS() {
        return permohonRBHS;
    }

    public void setPermohonRBHS(Permohonan permohonRBHS) {
        this.permohonRBHS = permohonRBHS;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getP14Amohon() {
        return p14Amohon;
    }

    public void setP14Amohon(String p14Amohon) {
        this.p14Amohon = p14Amohon;
    }

    public String getPBBBmohon() {
        return PBBBmohon;
    }

    public void setPBBBmohon(String PBBBmohon) {
        this.PBBBmohon = PBBBmohon;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
