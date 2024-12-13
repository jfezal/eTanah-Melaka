/*
 * 2nd Version of NotadaftarValidation for Orogenic
 * 
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.CallableReport;
import etanah.report.ReportUtil;
import etanah.service.SyerValidationService;
import etanah.service.common.PihakService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import etanah.service.NotaService;
import etanah.service.NotifikasiService;
import etanah.service.RegService;
import etanah.service.ReportName;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.BatalNotaService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.Date;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;
import etanah.service.daftar.PelarasanCukaiService;
import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.ArrayUtils;

//
/**
 *
 * @author mohd.fairul
 */
public class NotadaftarValidationV2 implements StageListener {

    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    SyerValidationService syerService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakService pihakService;
    @Inject
    NotaService notaService;
    @Inject
    BatalNotaService batalnotaService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    ReportUtil reportUtil2;
    @Inject
    ReportUtil reportUtil3;
    @Inject
    ReportUtil reportUtil4;
    @Inject
    ReportUtil reportUtil5;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    RegService regService;
    @Inject
    BatalNotaService batalNotaService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hmPDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportName reportName;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PelarasanCukaiService pelarasanCukaiService;
    @Inject
    private KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    HakmilikService hakmilikService2;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    StrataPtService strataPtService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    IWorkflowContext ctx = null;
    IWorkflowContext ctxOnBehalf = null;
    private static final Logger LOG = Logger.getLogger(NotadaftarValidationV2.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private PermohonanRujukanLuar permohonanRujLuar;
    private Hakmilik hakmilik;
    private Pengguna pengguna;
    private String hakmilikInduk;
    private HakmilikUrusan hakmilikUrusan;
    private String taskId;
    private String stage;
    private static String DAFTAR = "D";
    private static String TOLAK = "T";
    private static String GANTUNG = "G";
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Hakmilik> hakmilikStrataList;
    private static String[] URUSAN_WITHOUT_DHKE_DHDE = {
        "PBCTL",
        "PBCTT",
        "PBCTM"
    };
    private static String GEN_REPORT_WITHOUT_DHKE_2 = "NO";
    private static String GEN_REPORT_WITHOUT_DHKE_3 = "NO";
    private static String GEN_REPORT_WITHOUT_DHKE_4 = "NO";
    private boolean JANA_BORANG_DHKK = false;
    private boolean JANA_BORANG_DHDK = false;
    private boolean JANA_BORANG_SK_DHKK = false;
    private boolean JANA_BORANG_SK_DHDK = false;
    private static String[] URUSAN_WITHOUT_DHKE_DHDE_IDHM = {
        "KVLP",
        "PPM",
        "PBPM"
    };
    private static String GEN_REPORT_PINDE_2 = "NO";
    private static String GEN_REPORT_PINDE_3 = "NO";
    private static String GEN_REPORT_PINDE_4 = "NO";
    private static String[] URUSAN_PINDE = {
        "PINDE"
    };
    private static String GEN_REPORT_WITHOUT_DHKE_2_IDHM = "NO";
    private static String GEN_REPORT_WITHOUT_DHKE_3_IDHM = "NO";
    private static String GEN_REPORT_WITHOUT_DHKE_4_IDHM = "NO";
    private static String[] URUSAN_GEN_NEW_REPORT_MANY = {
        "HTBT",
        "HTT"
    };
    private static String GEN_REPORT_MANY_4 = "NO";
    private static String[] URUSAN_GEN_NEW_REPORT_SINGLE = {
        "IRTB",
        "IRTBB",
        "KRM",
        "KRMB",
        "IPM",
        "IRM",
        "ADBSB",
        "ADBSS",
        "LTDBL",
        "PSPBB",
        "PSPBN",
        "TTWB",
        "TTWKP",
        "TTWLB",
        "TTWLM",
        "IDMLB",
        "JPB",
        "HTT"
    };
    private static String GEN_REPORT_SINGLE_4 = "NO";
    private static String GEN_REPORT_SINGLE_5 = "NO";
    private static String GEN_REPORT_SINGLE_6 = "NO";
    private static final String[] HAKMILIK_URUSAN_TAK_KUAT_KUASA = {"LMTPB", "ADBSL", "TTWB", "TTB", "IKOAB", "KRMB", "TSPB", "PSPBB", "KBB", "TTWLB", "TTWLM","HTBT"}; // remove PMDPT by azri 15/7/2013
    private static final String[] UPDATE_HAKMILIK_PIHAK_KEPADA_TAK_KUAT_KUASA = {"TTWLB"}; //, "TTWLM" buang pd 23/01/2014.

    @Override
    public boolean beforeStart(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.debug("Before Complete :: start");
        Permohonan permohonan = ctx.getPermohonan();
        String kod = permohonan.getKodUrusan().getKod();
        if (permohonan != null) {
            if (permohonan.getSenaraiHakmilik().size() > 0) {
                Hakmilik h = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
                Boolean f = Boolean.FALSE;
                if (!ctx.isByPass()) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        f = permohonanService.isPrevUrusanBerangkaiNotComplete(h.getIdHakmilik(), permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        Date d = permohonan.getInfoAudit().getTarikhMasuk();
                        f = permohonanService.isPrevUrusanNotComplete(h.getIdHakmilik(), d);
                    }
                }
                LOG.info("isPrevUrusanNotComplete :: " + (!f ? "complete" : "not complete"));

                if (f) {
                    ctx.addMessage("Terdapat urusan sebelum yang masih belum selesai.");
                    return null;
                }
            }
        }
        pengguna = ctx.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        LOG.debug("KOD URUSAN :: " + kod);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {//calling function to update geran (info will update: e.g Cukai, Sekatan,Syarat )
            if (kod.equalsIgnoreCase("PCT") || kod.equalsIgnoreCase("KCL")) {
//                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateHakmilik(permohonan, info, proposedOutcome);

            } else if (kod.equalsIgnoreCase("MCLL") || kod.equalsIgnoreCase("TSSKL") || kod.equalsIgnoreCase("SSKPL") || kod.equalsIgnoreCase("EUBS")) {
                LOG.info("test kod update : " + kod);
                updateHakmilik(permohonan, info, proposedOutcome);
//                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateUrusan(permohonan, info, proposedOutcome);

            } //ABT-k, sbstl
            else if (kod.equalsIgnoreCase("ABT-K") || kod.equalsIgnoreCase("ABTS") || kod.equals("N8AB")
                    || kod.equals("RPBNB") || kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("SBKSL")
                    || kod.equalsIgnoreCase("TMAK") || kod.equalsIgnoreCase("TBTWK") || kod.equalsIgnoreCase("TTTK")
                    || kod.equalsIgnoreCase("TTWKP")) {
                updateStatusHakmilik(permohonan, info, proposedOutcome);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateUrusan(permohonan, info, proposedOutcome);

                if (kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("TTTK") || kod.equalsIgnoreCase("TTWKP")) {
                    intergrationNota(permohonan, pengguna);
                }

                String[] URUSAN_BATAL_AKAUN = {"TTTK", "TMAK", "TTWKP", "SBTL", "ABT-K", "TMAK"};

                if (ArrayUtils.contains(URUSAN_BATAL_AKAUN, kod)) {
                    updateAkaunLama(permohonan, info);
                }

            } else if (kod.equalsIgnoreCase("ABTB") || kod.equalsIgnoreCase("CB") || kod.equalsIgnoreCase("CL") || kod.equalsIgnoreCase("CMB")
                    || kod.equalsIgnoreCase("EUB") || kod.equalsIgnoreCase("IRMB") || kod.equalsIgnoreCase("ITBB") || kod.equalsIgnoreCase("ITPB")
                    || kod.equalsIgnoreCase("N6AB") || kod.equalsIgnoreCase("N7AB") || kod.equalsIgnoreCase("PBB") || kod.equalsIgnoreCase("PBBB")
                    || kod.equalsIgnoreCase("PBBT") || kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBL") || kod.equalsIgnoreCase("PBSCB")
                    || kod.equalsIgnoreCase("PSB") || kod.equalsIgnoreCase("PSKB") || kod.equalsIgnoreCase("PSKSB") || kod.equalsIgnoreCase("PSL")
                    || kod.equalsIgnoreCase("PSTSB") || kod.equalsIgnoreCase("PSTSL") || kod.equalsIgnoreCase("SBKL") || kod.equalsIgnoreCase("SBSTB")
                    || kod.equalsIgnoreCase("SBSTL") || kod.equalsIgnoreCase("SBTB") || kod.equalsIgnoreCase("SSKPB") || kod.equalsIgnoreCase("TSB")
                    || kod.equalsIgnoreCase("TSSKB") || kod.equalsIgnoreCase("ADMRL")//will update when hakmlik batal dibenarkan
                    || kod.equalsIgnoreCase("HLLB") || kod.equalsIgnoreCase("SBKSB") || kod.equalsIgnoreCase("HLTEB") || kod.equalsIgnoreCase("HMVB")
                    || kod.equalsIgnoreCase("IGSAB") || kod.equalsIgnoreCase("IPMB") || kod.equalsIgnoreCase("TBTWB") || kod.equalsIgnoreCase("PBBL")
                    || kod.equalsIgnoreCase("HSAB") || kod.equalsIgnoreCase("LPBB") || kod.equalsIgnoreCase("IROAB") || kod.equalsIgnoreCase("IRTBB")
                    || kod.equalsIgnoreCase("PREMB") || kod.equalsIgnoreCase("ABTBH") || kod.equalsIgnoreCase("ABTKB") || kod.equalsIgnoreCase("KRMB")
                    || kod.equalsIgnoreCase("ADBSL") || kod.equalsIgnoreCase("HTBT") || kod.equalsIgnoreCase("IKOAB") || kod.equalsIgnoreCase("KBB")
                    || kod.equalsIgnoreCase("LMTPB") || kod.equalsIgnoreCase("PSPBB") || kod.equalsIgnoreCase("SHKB") || kod.equalsIgnoreCase("SHSB")
                    || kod.equalsIgnoreCase("TSPB") || kod.equalsIgnoreCase("TTB") || kod.equalsIgnoreCase("TTWB")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateUrusan(permohonan, info, proposedOutcome);
                if (kod.equalsIgnoreCase("SBTB") || kod.equalsIgnoreCase("SBSTL") || kod.equalsIgnoreCase("SBSTB") || kod.equalsIgnoreCase("HLLB")
                        || kod.equalsIgnoreCase("TTB") || kod.equalsIgnoreCase("TTWB") || kod.equalsIgnoreCase("PSPBB")) {
                    intergrationNota(permohonan, pengguna);
                }
            } //intergration strata
            else if ((kod.equalsIgnoreCase("PBBM")) || (kod.equalsIgnoreCase("HTT")) || (kod.equalsIgnoreCase("HTB"))
                    //added by murali to initiate back to STRATA for urusan PBCTM/PBCTT on 01-10-2012
                    || (kod.equalsIgnoreCase("PBCTM")) || (kod.equalsIgnoreCase("PBCTT")) || (kod.equalsIgnoreCase("PPM"))
                    || (kod.equalsIgnoreCase("PBPM"))) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                intergrationNota(permohonan, pengguna);

                if ((kod.equalsIgnoreCase("HTT"))) {
                    updateUrusan(permohonan, info, proposedOutcome);
                    List<Hakmilik> listHakmilik = hakmilikPermohonanService.findHakmilikStrataByIdHakmilikInduk(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    for (Hakmilik h : listHakmilik) {
                        KodStatusHakmilik ks = kodStatusHakmilikDAO.findById("B");
                        h.setKodStatusHakmilik(ks);
                        h.setTarikhBatal(new Date());
                        strataPtService.simpanHakmilik(h);
                        
                        Akaun akaun = regService.getAkaunLama(h.getIdHakmilik());
                        if (akaun != null) {
                        akaun.setStatus(kodStatusAkaunDAO.findById("B"));
                        info = akaun.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new Date());
                        akaun.setInfoAudit(info);
                        regService.saveOrUpdate(akaun);
                        }
                    }
                    
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanService.findByIdHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    for (HakmilikPermohonan hp : senaraiHakmilik) {

                            Akaun akaun = regService.getAkaunFreeze(hp.getHakmilik().getIdHakmilik());
                            if (akaun != null) {
                                akaun.setStatus(kodStatusAkaunDAO.findById("A"));
                                info = akaun.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new Date());
                                akaun.setInfoAudit(info);
                                regService.saveOrUpdate(akaun);
                            }
                    }
                    
                }

            } else if ((kod.equalsIgnoreCase("SHSK")) || (kod.equalsIgnoreCase("SHKK"))) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateHakmilikPihakNoSalinan(permohonan, info, proposedOutcome, hakmilikUrusan);
            } else if (kod.equalsIgnoreCase("N8A")) {
                updateStatusHakmilik(permohonan, info, proposedOutcome);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                //Integration for pengambilan HLLA, HLLS added by Aizuddin
            } else if (kod.equalsIgnoreCase("HLLA") || kod.equalsIgnoreCase("HLLS") || kod.equalsIgnoreCase("HLTE")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                intergrationNota(permohonan, pengguna);
            } else if (kod.equalsIgnoreCase("TT") || kod.equalsIgnoreCase("TTW") || kod.equalsIgnoreCase("PSPBN")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                intergrationNota(permohonan, pengguna);
            } else if (kod.equalsIgnoreCase("STMA")) {

                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                String[] name = {"idHakmilik"};
                Object[] value = {permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()};
                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                KodUrusan kodUrusanBaru = kodUrusanDAO.findById("HKSTA");
                LOG.info(kodUrusanBaru.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kodUrusanBaru, pengguna, senaraiHakmilik, permohonan);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            } else if (kod.equalsIgnoreCase("N6A")) {
                String negeri = conf.getProperty("kodNegeri");
                //TODO: Filter by kod negeri
                if (negeri.equalsIgnoreCase("04")) {
                    List<HakmilikPermohonan> lsH = permohonan.getSenaraiHakmilik();
                    for (HakmilikPermohonan hP : lsH) {
                        pelarasanCukaiService.larasCukaiBaki(permohonan, hP.getHakmilik().getIdHakmilik(), "10", "notis6A", pengguna);
                        LOG.debug("End of penyelarasan");
                        LOG.debug("Start Send Notifikasi");
//            TODO: if case for intergration
                        notifikasi(permohonan, pengguna);
                    }
                }
                LOG.debug("End of penyelarasan");
//            LOG.debug("Start Send Notifikasi");
//            TODO: if case for intergration
//            notifikasi(permohonan, pengguna);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            } else if (kod.equalsIgnoreCase("PMDPT")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                initiateBPELurusanDipilih(permohonan, pengguna);
                updatePermohonan(permohonan, pengguna);
            } else if (kod.equals("PINDE")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                doTransferUrusan(permohonan, info, proposedOutcome);
            } else if (kod.equals("TTWLB") || kod.equals("TTWLM")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateUrusan(permohonan, info, proposedOutcome);
                intergrationNota(permohonan, pengguna);
            } else {
                // default
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            }

//            //update no versi
            updateNoVersi(permohonan, info);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            ctx.addMessage("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
            return null;
        }

        LOG.debug("Transaction Finish ::");
//        ctx.addMessage(" -Pendaftaran Urusan Berjaya.");
        StringBuilder msg = new StringBuilder(" - Pendaftaran Urusan Berjaya.");
        if (permohonan.getPermohonanSebelum() != null) {
            msg.append(" Notifikasi telah dihantar ke modul sebelum.");
            //return null;
        }
        ctx.addMessage(msg.toString());
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        // TODO Auto-generated method stub
        Permohonan permohonan = context.getPermohonan();
        List<Permohonan> senaraiPermohonanTerlibat = new ArrayList<Permohonan>();
        StringBuilder sb = new StringBuilder();

        if (permohonan != null && permohonan.getKeputusan() != null
                && permohonan.getKeputusan().getKod().equals(DAFTAR)) {

            String idKumpulan = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonanTerlibat = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonanTerlibat.add(permohonan);
            }

            for (Permohonan pmohon : senaraiPermohonanTerlibat) {
                if (pmohon == null) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(pmohon.getIdPermohonan());
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = pmohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (hp.getDokumen2() != null) {
                        hm.setDhke(hp.getDokumen2());
                    }
                    if (hp.getDokumen3() != null) {
                        hm.setDhde(hp.getDokumen3());
                    }
                }
            }

            context.addMessage("Perserahan " + sb.toString() + " telah berjaya didaftarkan.");

            String[] URUSAN_UPDATE_CUKAI_BARU = {"TSSKL", "SBSTL", "SSKPL", "ABTKB", "ABTBH", "PCT", "KCL", "IRM", "SBKSL", "IRMB"};
            if (ArrayUtils.contains(URUSAN_UPDATE_CUKAI_BARU, permohonan.getKodUrusan().getKod())) {
                List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                    if (hp == null || hp.getHakmilik() == null || hp.getCukaiBaru() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (permohonan.getKodUrusan().getKod().equals("IRMB")) {
                        hm.setCukaiSebenar(hp.getCukaiBaru().multiply(new BigDecimal(2)));
                    } else {
                        hm.setCukaiSebenar(hp.getCukaiBaru());
                    }
                    hakmilikService2.saveHakmilik(hm);
                }
            }

            //penghantaran notifikasi kepada yg berkenaan
            if (permohonan.getPermohonanSebelum() != null) {
                InfoAudit ia = new InfoAudit();
                pengguna = context.getPengguna();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                Permohonan integrasiPermohonan = permohonan.getPermohonanSebelum();
                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setTajuk("Maklumat Perserahan berjaya didaftarkan");
                notifikasi.setCawangan(integrasiPermohonan.getCawangan());
                StringBuilder mesej = new StringBuilder("ID Permohonan ")
                        .append(integrasiPermohonan.getIdPermohonan())
                        .append(" ( ")
                        .append(integrasiPermohonan.getKodUrusan().getNama())
                        .append(" ) ")
                        .append("telah di ")
                        .append(permohonan.getKeputusan().getNama());

                FasaPermohonan fp = fasaPermohonanService.getCurrentStage(integrasiPermohonan.getIdPermohonan(), permohonan.getIdStagePermohonanSebelum());
                if (fp != null && fp.getInfoAudit() != null) {
                    Pengguna p = fp.getInfoAudit().getDimasukOleh();
                    notifikasi.setPengguna(p);
                }

                notifikasi.setMesej(mesej.toString());
                notifikasi.setInfoAudit(ia);
                notifikasiService.save(notifikasi);
            }

        }
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public void onGenerateReports(StageContext context) {
        FolderDokumen fd1 = new FolderDokumen();
        Pengguna pengguna = context.getPengguna();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null; //DHKE
        Dokumen f = null; //DHDE
        Dokumen g = null; //Report ke-4
        Dokumen h = null; //Report ke-5
        Dokumen i = null; //Report ke-6
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        List<HakmilikPermohonan> senaraiHkamilikPermohonan = permohonan.getSenaraiHakmilik();
        if (permohonan != null && permohonan.getKeputusan().getKod().equals("D")) {
            String kod = permohonan.getKodUrusan().getKod();
            //BETPB - Pembetulan 380 - Maklumat Pihak Berkepentingan
//            if (kod.equalsIgnoreCase("BETPB") || kod.equalsIgnoreCase("PPBM")) {
//                //TODO:
//                LOG.info("::BETPB - Masuk :: ");
//                betpb(context, permohonan);
//            }
            String idPermohonan = permohonan.getIdPermohonan();
            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            String[] params = null;
            String[] values = null;
            String[] values1 = null;
            String[] params2 = {"p_id_mohon"};
            String[] values2 = {idPermohonan};

            String path2 = ""; //DHKE //DHKK
            String path3 = ""; //DHDE //DHDK
            String path4 = ""; //Report ke-4
            String path5 = ""; //Report ke-5
            String path6 = ""; //Report ke-6

            String gen2 = ""; //DHKE //DHKK
            String gen3 = ""; //DHDE //DHDK
            String gen4 = ""; //Report ke-4
            String gen5 = ""; //Report ke-5
            String gen6 = ""; //Report ke-6

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen(); //DHKE //DHKK
            KodDokumen kd3 = new KodDokumen(); //DHDE //DHDK
            KodDokumen kd4 = new KodDokumen(); //Report ke-4
            KodDokumen kd5 = new KodDokumen(); //Report ke-5
            KodDokumen kd6 = new KodDokumen(); //Report ke-6

            //Generate report strata without geran, using hakmilik strata
            if (ArrayUtils.contains(URUSAN_WITHOUT_DHKE_DHDE, permohonan.getKodUrusan().getKod())) {
                LOG.info(":::::::::::::Generate 4K::::::::::::::: ");
                for (HakmilikPermohonan hpMany : hakmilikPermohonanList) {
                    String hakmilikInduk = hpMany.getHakmilik().getIdHakmilik();
                    LOG.info(":::::::::::::ID Hakmilik Induk::::::::::::::: " + hakmilikInduk);
                    hakmilikStrataList = hakmilikPermohonanService.findHakmilikStrataByIdHakmilikInduk(hakmilikInduk);
                    for (Hakmilik hakmilikStrata : hakmilikStrataList) {
                        LOG.info(":::::::::::::ID Hakmilik Strata::::::::::::::: " + hakmilikStrata.getIdHakmilik());
                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values = new String[]{idPermohonan.trim(), hakmilikStrata.getIdHakmilik()}; //Pass hakmilik strata
//                        values = new String[]{idPermohonan.trim(), hpMany.getHakmilik().getIdHakmilik()}; //Pass hakmilik induk
                        String negeri = conf.getProperty("kodNegeri");

                        if (negeri.equalsIgnoreCase("05")) {
                            if (kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBCTT") || kod.equalsIgnoreCase("PBCTM") || kod.equalsIgnoreCase("HTT")) {
                                GEN_REPORT_WITHOUT_DHKE_2 = "YES";
                                gen2 = "REGB4K_NS.rdf";
                                kd2.setKod("4K");
                            }
                        } else if (negeri.equalsIgnoreCase("04")) {
                            if (kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBCTT") || kod.equalsIgnoreCase("PBCTM")) {
                                GEN_REPORT_WITHOUT_DHKE_2 = "YES";
                                gen2 = "REGB4K_MLK.rdf";
                                kd2.setKod("4K");
                            }
                            if (kod.equalsIgnoreCase("HTT")) {
                                GEN_REPORT_WITHOUT_DHKE_2 = "YES";
                                GEN_REPORT_WITHOUT_DHKE_3 = "YES";
                                gen2 = "REGB4KDHKKHTT_MLK.rdf";
                                gen3 = "REGB4KDHDKHTT_MLK.rdf";
                                kd2.setKod("4K");
                                kd3.setKod("4K");
                            }
                        }

                        if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_2, "YES")) {
                            e = saveOrUpdateDokumen(fd, kd2, hakmilikStrata.getIdHakmilik(), context);
                            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                            LOG.info("::Path To save :: " + path2);
                            LOG.info("::Report Name ::" + gen2);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path2);
                            Future<byte[]> report2 = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                            File sign = new File(dokumenPath + path2 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report2.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                        }
                        if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_3, "YES")) {
                            f = saveOrUpdateDokumen(fd, kd3, hakmilikStrata.getIdHakmilik(), context);
                            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                            LOG.info("::Path To save :: " + path3);
                            LOG.info("::Report Name ::" + gen3);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path3);
                            Future<byte[]> report3 = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path3 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report3.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                        }

                        if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_4, "YES")) {
                            g = saveOrUpdateDokumen(fd, kd4, hakmilikStrata.getIdHakmilik(), context);
                            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                            LOG.info("::Path To save :: " + path4);
                            LOG.info("::Report Name ::" + gen4);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path4);
                            Future<byte[]> report4 = executor.schedule(new CallableReport(reportUtil3, gen4, params, values, dokumenPath + path4, peng), 2, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path4 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report4.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }
                            updatePathDokumen(reportUtil3.getDMSPath(), g.getIdDokumen(), reportUtil3.getContentType());
                        }
                    }
                    if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_2, "YES")) {
                        hpMany.setDokumen2(e);
                    } else if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_3, "YES")) {
                        hpMany.setDokumen3(f);
                    } else if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_4, "YES")) {
                        hpMany.setDokumen4(g);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpMany);
                }
            } else if (ArrayUtils.contains(URUSAN_WITHOUT_DHKE_DHDE_IDHM, permohonan.getKodUrusan().getKod())) {
                LOG.info("::::::::::Generate 4K MSUK HKMILIK STRATA::::::::::::::: ");
                for (HakmilikPermohonan hpMany : hakmilikPermohonanList) {
//                    String hakmilikInduk = hpMany.getHakmilik().getIdHakmilik();
//                    LOG.info(":::::::::::::ID Hakmilik Induk::::::::::::::: " + hakmilikInduk);
//                    hakmilikStrataList = hakmilikPermohonanService.findHakmilikStrataByIdHakmilikInduk(hakmilikInduk);
//                    for (Hakmilik hakmilikStrata : hakmilikStrataList) {
//                        LOG.info(":::::::::::::ID Hakmilik Strata::::::::::::::: " + hakmilikStrata.getIdHakmilik());
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                        values = new String[]{idPermohonan.trim(), hakmilikStrata.getIdHakmilik()}; //Pass hakmilik strata
                    values = new String[]{idPermohonan.trim(), hpMany.getHakmilik().getIdHakmilik()}; //Pass hakmilik induk
                    String negeri = conf.getProperty("kodNegeri");

                    if (negeri.equalsIgnoreCase("05")) {
                        if (kod.equalsIgnoreCase("PPM") || kod.equalsIgnoreCase("KVLP") || kod.equalsIgnoreCase("PBPM")) {
                            GEN_REPORT_WITHOUT_DHKE_2_IDHM = "YES";
//              gen2 = "REGB4K_NS.rdf";  // comment out by azri 29/8/2013 : must generate DHDK and DHKK separately
//              kd2.setKod("4K");
                            gen2 = "REGB4KKK_NS.rdf"; // DHKK rdf file name 
                            gen3 = "REGB4KDK_NS.rdf"; // DHDK rdf file name

                        }
                    } else if (negeri.equalsIgnoreCase("04")) {
                        if (kod.equalsIgnoreCase("PPM") || kod.equalsIgnoreCase("KVLP") || kod.equalsIgnoreCase("PBPM")) {
                            GEN_REPORT_WITHOUT_DHKE_2_IDHM = "YES";
//              gen2 = "REGB4K_MLK.rdf";
                            gen2 = "REGB4KDHKK_MLK.rdf";
                            gen3 = "REGB4KDHDK_MLK.rdf";
//            kd2.setKod("4K");
                        }
                    }

                    if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_2_IDHM, "YES")) {

                        kd2.setKod("DHKK"); // generate DHKK
                        e = saveOrUpdateDokumen(fd, kd2, hpMany.getHakmilik().getIdHakmilik(), context);
                        path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                        LOG.info("::Path To save :: " + path2);
                        LOG.info("::Report Name ::" + gen2);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
                        Future<byte[]> dhdk = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                        File sign = new File(dokumenPath + path2 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        kd3.setKod("DHDK");  // generate DHDK
                        f = saveOrUpdateDokumen(fd, kd3, hpMany.getHakmilik().getIdHakmilik(), context);
                        path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                        LOG.info("::Path To save :: " + path3);
                        LOG.info("::Report Name ::" + gen3);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                        Future<byte[]> dhkk = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                        sign = new File(dokumenPath + path3 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            dhdk.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }
                        try {
                            dhkk.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        // update dokumen path
                        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                        updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());

                    }
                    if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_3_IDHM, "YES")) {
                        f = saveOrUpdateDokumen(fd, kd3, hpMany.getHakmilik().getIdHakmilik(), context);
                        path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                        LOG.info("::Path To save :: " + path3);
                        LOG.info("::Report Name ::" + gen3);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                        Future<byte[]> report3 = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                        File sign = new File(dokumenPath + path3 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            report3.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                    }

                    if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_4_IDHM, "YES")) {
                        g = saveOrUpdateDokumen(fd, kd4, hpMany.getHakmilik().getIdHakmilik(), context);
                        path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        LOG.info("::Path To save :: " + path4);
                        LOG.info("::Report Name ::" + gen4);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path4);
                        Future<byte[]> report4 = executor.schedule(new CallableReport(reportUtil3, gen4, params, values, dokumenPath + path4, peng), 2, TimeUnit.SECONDS);
                        File sign = new File(dokumenPath + path4 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            report4.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }
                        updatePathDokumen(reportUtil3.getDMSPath(), g.getIdDokumen(), reportUtil3.getContentType());
                    }
//                    }
                    if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_2_IDHM, "YES")) {
                        if (negeri.equalsIgnoreCase("05")) { // N9
                            hpMany.setDokumen2(e); // save DHDK in column2
                            hpMany.setDokumen3(f); // save DHKK in column3
                        } else { // Melaka
                            hpMany.setDokumen2(e);
                        }
                    } else if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_3_IDHM, "YES")) {
                        hpMany.setDokumen3(f);
                    } else if (StringUtils.equals(GEN_REPORT_WITHOUT_DHKE_4_IDHM, "YES")) {
                        hpMany.setDokumen4(g);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpMany);
                }
            } else if (ArrayUtils.contains(URUSAN_PINDE, permohonan.getKodUrusan().getKod())) {
                LOG.info("::::::::::Generate 4K PINDE::::::::::::::: ");
                for (HakmilikPermohonan hpMany : hakmilikPermohonanList) {

                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                        values = new String[]{idPermohonan.trim(), hakmilikStrata.getIdHakmilik()}; //Pass hakmilik strata
                    values = new String[]{idPermohonan.trim(), hpMany.getHakmilik().getIdHakmilik()}; //Pass hakmilik induk
                    String negeri = conf.getProperty("kodNegeri");

                    if (negeri.equalsIgnoreCase("05")) {
                        if (kod.equalsIgnoreCase("PINDE")) {
                            GEN_REPORT_PINDE_2 = "YES";
//              gen4 = "REGB4K_NS.rdf";
//              kd4.setKod("4K");
                            gen4 = "REGB4KKK_NS.rdf"; // rdf name DHKK for Urusan PINDE
                            gen5 = "REGB4KDK_NS.rdf"; // rdf name DHDK for Urusan PINDE
                        }
                    } else if (negeri.equalsIgnoreCase("04")) {
                        if (kod.equalsIgnoreCase("PINDE")) {
                            GEN_REPORT_PINDE_2 = "YES";
                            gen4 = "REGB4KDHKK_MLK.rdf";
                            gen5 = "REGB4KDHDK_MLK.rdf";
                        }
                    }

                    if (StringUtils.equals(GEN_REPORT_PINDE_2, "YES")) {
                        kd4.setKod("DHKK"); // generate DHKK
                        e = saveOrUpdateDokumen(fd, kd4, hpMany.getHakmilik().getIdHakmilik(), context);
                        path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                        LOG.info("::Path To save :: " + path4);
                        LOG.info("::Report Name ::" + gen4);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path4);
                        Future<byte[]> dhdk = executor.submit(new CallableReport(reportUtil, gen4, params, values, dokumenPath + path4, peng));
                        File sign = new File(dokumenPath + path4 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        kd5.setKod("DHDK");  // generate DHDK
                        f = saveOrUpdateDokumen(fd, kd5, hpMany.getHakmilik().getIdHakmilik(), context);
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                        LOG.info("::Path To save :: " + path5);
                        LOG.info("::Report Name ::" + gen5);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
                        Future<byte[]> dhkk = executor.schedule(new CallableReport(reportUtil2, gen5, params, values, dokumenPath + path5, peng), 1, TimeUnit.SECONDS);
                        sign = new File(dokumenPath + path5 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            dhdk.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }
                        try {
                            dhkk.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        // update dokumen path
                        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                        updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                    }

                    if (StringUtils.equals(GEN_REPORT_PINDE_2, "YES")) {
                        hpMany.setDokumen5(e);
                        hpMany.setDokumen6(f);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpMany);
                }

                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilikInduk()};
                    String negeri = conf.getProperty("kodNegeri");
                    if (negeri.equalsIgnoreCase("05")) {
                        gen2 = "regBorangHMDHKE.rdf";
                        gen3 = "regBorangHMDHDE.rdf";
                    } else if (negeri.equalsIgnoreCase("04")) {
                        gen2 = "regBorangHMDHKEml.rdf";
                        gen3 = "regBorangHMDHDEml.rdf";
                    }

                    kd2.setKod("DHKE");
                    e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilikInduk(), context);
                    path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                    LOG.info("::Path To save :: " + path2);
                    LOG.info("::Report Name ::" + gen2);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() + " and saving it to:" + path2);
                    Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
//                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                    File sign = new File(dokumenPath + path2 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    //gen DHKE
                    kd3.setKod("DHDE");
                    f = saveOrUpdateDokumen(fd, kd3, hakmilikPermohonan.getHakmilik().getIdHakmilikInduk(), context);
                    path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                    LOG.info("::Path To save :: " + path3);
                    LOG.info("::Report Name ::" + gen3);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() + " and saving it to:" + path3);
                    Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
//                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                    sign = new File(dokumenPath + path3 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    LOG.debug("Waiting for reports to complete...");
                    try {
                        dhke.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }
                    try {
                        dhde.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }

                    LOG.debug("saving dokumen : " + e.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    LOG.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());

                    // dhke
                    updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                    hakmilikPermohonan.setDokumen2(e);
                    // dhde
                    updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                    hakmilikPermohonan.setDokumen3(f);

                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                }
            } else {

                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                    String negeri = conf.getProperty("kodNegeri");
                    if (negeri.equalsIgnoreCase("05")) {
                        gen2 = "regBorangHMDHKE.rdf";
                        gen3 = "regBorangHMDHDE.rdf";
                    } else if (negeri.equalsIgnoreCase("04")) {
                        gen2 = "regBorangHMDHKEml.rdf";
                        gen3 = "regBorangHMDHDEml.rdf";
                    }

                    kd2.setKod("DHKE");
                    e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                    LOG.info("::Path To save :: " + path2);
                    LOG.info("::Report Name ::" + gen2);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
                    Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
//                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                    File sign = new File(dokumenPath + path2 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    //gen HKE
                    kd3.setKod("DHDE");
                    f = saveOrUpdateDokumen(fd, kd3, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                    LOG.info("::Path To save :: " + path3);
                    LOG.info("::Report Name ::" + gen3);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                    Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
//                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                    sign = new File(dokumenPath + path3 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    LOG.debug("Waiting for reports to complete...");
                    try {
                        dhke.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }
                    try {
                        dhde.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }

                    LOG.debug("saving dokumen : " + e.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    LOG.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());

                    // dhke
                    updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                    hakmilikPermohonan.setDokumen2(e);
                    // dhde
                    updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                    hakmilikPermohonan.setDokumen3(f);

                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                }
            }

            //Report that require dhke, also for strata using hakmilik strata
            if (ArrayUtils.contains(URUSAN_GEN_NEW_REPORT_MANY, permohonan.getKodUrusan().getKod())) {
                LOG.info(":::::::::::::Generate 4K Ada DHKE/DHDE::::::::::::::: ");
                for (HakmilikPermohonan hpMany : hakmilikPermohonanList) {
                    String hakmilikInduk = hpMany.getHakmilik().getIdHakmilik();
                    LOG.info(":::::::::::::ID Hakmilik Induk::::::::::::::: " + hakmilikInduk);
                    hakmilikStrataList = hakmilikPermohonanService.findHakmilikStrataByIdHakmilikInduk(hakmilikInduk);
                    for (Hakmilik hakmilikStrata : hakmilikStrataList) {
                        LOG.info(":::::::::::::ID Hakmilik Strata::::::::::::::: " + hakmilikStrata.getIdHakmilik());
                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                        values = new String[]{idPermohonan.trim(), hakmilikStrata.getIdHakmilik()}; //Pass hakmilik strata
                        values = new String[]{idPermohonan.trim(), hakmilikStrata.getIdHakmilik()}; //Pass hakmilik induk
                        String negeri = conf.getProperty("kodNegeri");

                        if (negeri.equalsIgnoreCase("05")) {
                            if (kod.equalsIgnoreCase("HTT") || kod.equalsIgnoreCase("PINDE")) {
                                GEN_REPORT_MANY_4 = "YES";
                                gen4 = "REGB4K_NS.rdf";
                                kd4.setKod("4K");
                            } else if (kod.equalsIgnoreCase("HTBT")) {
                                GEN_REPORT_MANY_4 = "YES";
                                gen4 = "STRBSK_NS.rdf";
                                kd4.setKod("SK");
                            }
                        } else if (negeri.equalsIgnoreCase("04")) {
                            if (kod.equalsIgnoreCase("HTT")) {
                                JANA_BORANG_DHKK = true;
                                JANA_BORANG_DHDK = true;
                                //JANA_BORANG_SK_DHKK = true;
                                //JANA_BORANG_SK_DHDK = true;
                                gen2 = "REGB4KDHKKHTT_MLK.rdf";
                                gen3 = "REGB4KDHDKHTT_MLK.rdf";
                                //gen4 = "REGBSKDHDK_MLK.rdf";
                                //gen5 = "REGBSKDHKK_MLK.rdf";
                                kd2.setKod("DHKK");
                                kd3.setKod("DHDK");
                                //kd4.setKod("SK");
                                //kd5.setKod("SK");
                            } else if (kod.equalsIgnoreCase("HTBT")) {
                                GEN_REPORT_MANY_4 = "YES";
                                gen4 = "STRBSK_MLK.rdf";
                                kd4.setKod("SK");
                            }
                        }
//          Start: 01/12/2013
                        if (JANA_BORANG_DHKK) {
                            e = saveOrUpdateDokumen(fd, kd2, hakmilikStrata.getIdHakmilik(), context);
                            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                            LOG.info("::Path To save :: " + path2);
                            LOG.info("::Report Name ::" + gen2);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path2);
                            Future<byte[]> report2 = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                            File sign = new File(dokumenPath + path2 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report2.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                        }
                        if (JANA_BORANG_DHDK) {
                            f = saveOrUpdateDokumen(fd, kd3, hakmilikStrata.getIdHakmilik(), context);
                            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                            LOG.info("::Path To save :: " + path3);
                            LOG.info("::Report Name ::" + gen3);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path3);
                            Future<byte[]> report3 = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path3 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report3.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                        }
                        if (JANA_BORANG_SK_DHKK) {
                            g = saveOrUpdateDokumen(fd, kd4, hakmilikStrata.getIdHakmilik(), context);
                            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                            LOG.info("::Path To save :: " + path5);
                            LOG.info("::Report Name ::" + gen5);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path4);
                            Future<byte[]> report4 = executor.schedule(new CallableReport(reportUtil3, gen4, params, values, dokumenPath + path4, peng), 1, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path4 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report4.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil3.getDMSPath(), g.getIdDokumen(), reportUtil3.getContentType());
                        }
                        if (JANA_BORANG_SK_DHDK) {
                            h = saveOrUpdateDokumen(fd, kd5, hakmilikStrata.getIdHakmilik(), context);
                            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
                            LOG.info("::Path To save :: " + path5);
                            LOG.info("::Report Name ::" + gen5);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path5);
                            Future<byte[]> report5 = executor.schedule(new CallableReport(reportUtil4, gen5, params, values, dokumenPath + path5, peng), 1, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path5 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report5.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }
                            updatePathDokumen(reportUtil4.getDMSPath(), h.getIdDokumen(), reportUtil4.getContentType());
                        }
//                   END: 01/12/2013
                        LOG.info(":: GEN_REPORT_MANY_4 :: " + GEN_REPORT_MANY_4);
                        if (StringUtils.equals(GEN_REPORT_MANY_4, "YES")) {
                            g = saveOrUpdateDokumen(fd, kd4, hakmilikStrata.getIdHakmilik(), context);
                            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                            LOG.info("::Path To save :: " + path4);
                            LOG.info("::Report Name ::" + gen4);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikStrata.getIdHakmilik() + " and saving it to:" + path4);
                            Future<byte[]> report4 = executor.schedule(new CallableReport(reportUtil5, gen4, params, values, dokumenPath + path4, peng), 2, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path4 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report4.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil5.getDMSPath(), g.getIdDokumen(), reportUtil5.getContentType());
                        }
                    }

                    if (JANA_BORANG_DHKK) {
                        hpMany.setDokumen5(e);
                    }
                    if (JANA_BORANG_DHDK) {
                        hpMany.setDokumen6(f);
                    }
                    /*if (JANA_BORANG_SK_DHKK) {
                     hpMany.setDokumen4(g);
                     }
                     if (JANA_BORANG_SK_DHDK) {
                     hpMany.setDokumen5(h);
                     }*/
                    if (StringUtils.equals(GEN_REPORT_MANY_4, "YES")) {
                        hpMany.setDokumen5(g);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpMany);
                }
            }//End of generating reports base on hakmilik  

            //For resetting
            LOG.info(":: RESETTTTT :: ");
            GEN_REPORT_MANY_4 = "NO";
            GEN_REPORT_SINGLE_4 = "NO";
            GEN_REPORT_SINGLE_5 = "NO";
            GEN_REPORT_SINGLE_6 = "NO";
            LOG.info(":: GEN_REPORT_MANY_4 :: " + GEN_REPORT_MANY_4);
            LOG.info(":: GEN_REPORT_SINGLE_4 :: " + GEN_REPORT_SINGLE_4);
            LOG.info(":: GEN_REPORT_SINGLE_5 :: " + GEN_REPORT_SINGLE_5);
            LOG.info(":: GEN_REPORT_SINGLE_6 :: " + GEN_REPORT_SINGLE_6);

            //Report require dhke, but only generate per id permohonan
            if (ArrayUtils.contains(URUSAN_GEN_NEW_REPORT_SINGLE, permohonan.getKodUrusan().getKod())) {
                LOG.info(":::::::::::::Generate Single Report::::::::::: ");
                String negeri = conf.getProperty("kodNegeri");
                if (negeri.equalsIgnoreCase("05")) {
                    if (kod.equalsIgnoreCase("IRTB")
                            || kod.equalsIgnoreCase("IRTBB")
                            || kod.equalsIgnoreCase("JPB")
                            || kod.equalsIgnoreCase("IDMLB")
                            || kod.equalsIgnoreCase("TT")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGSuratMohonDHKK.rdf";
                        kd4.setKod("SMKD");
                    } else if (kod.equalsIgnoreCase("IPM")
                            || kod.equalsIgnoreCase("IRM")
                            || kod.equalsIgnoreCase("KRM")
                            || kod.equalsIgnoreCase("KRMB")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGBorangB.rdf";
                        kd4.setKod("BRGB");
                    } else if (kod.equalsIgnoreCase("HTT")) {
                        GEN_REPORT_SINGLE_5 = "YES";
                        //gen5 = "REGB3K_NS.rdf";
                        gen5 = "REGSB3K_NS.rdf";
                        kd5.setKod("3K");
                        GEN_REPORT_SINGLE_6 = "YES";
                        //gen6 = "REGB2K_NS.rdf";
                        //gen6 = "STRB2K_NS.rdf";
                        gen6 = "REGSB2K_NS.rdf";
                        kd6.setKod("2K");
                    } else if (kod.equalsIgnoreCase("ADBSB")
                            || kod.equalsIgnoreCase("ADBSS")
                            || kod.equalsIgnoreCase("LTDBL")
                            || kod.equalsIgnoreCase("PSPBB")
                            || kod.equalsIgnoreCase("PSPBN")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGSuratMaklumanPT.rdf";
                        kd4.setKod("SMPT");
                    } else if (kod.equalsIgnoreCase("TTWB")
                            || kod.equalsIgnoreCase("TTW")
                            || kod.equalsIgnoreCase("TTWLM")
                            || kod.equalsIgnoreCase("TTWKP")
                            || kod.equalsIgnoreCase("TTWLB")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGSuratMaklumanPT.rdf";
                        kd4.setKod("SMDP");
                    }

                } else if (negeri.equalsIgnoreCase("04")) {
                    if (kod.equalsIgnoreCase("IRTB")
                            || kod.equalsIgnoreCase("IRTBB")
                            || kod.equalsIgnoreCase("JPB")
                            || kod.equalsIgnoreCase("IDMLB")
                            || kod.equalsIgnoreCase("TT")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGSuratMohonDHKK.rdf";
                        kd4.setKod("SMKD");
                    } else if (kod.equalsIgnoreCase("IPM")
                            || kod.equalsIgnoreCase("IRM")
                            || kod.equalsIgnoreCase("KRM")
                            || kod.equalsIgnoreCase("KRMB")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGBorangB.rdf";
                        kd4.setKod("BRGB");
                    } else if (kod.equalsIgnoreCase("HTT")) {
                        GEN_REPORT_SINGLE_5 = "YES";
                        gen5 = "REGB3K_MLK.rdf";
                        kd5.setKod("3K");
                        GEN_REPORT_SINGLE_6 = "YES";
                        gen6 = "REGB2K_MLK.rdf";
                        kd6.setKod("2K");
                    } else if (kod.equalsIgnoreCase("ADBSB")
                            || kod.equalsIgnoreCase("ADBSS")
                            || kod.equalsIgnoreCase("LTDBL")
                            || kod.equalsIgnoreCase("PSPBB")
                            || kod.equalsIgnoreCase("PSPBN")
                            || kod.equalsIgnoreCase("TTWB")
                            || kod.equalsIgnoreCase("TTWLM")
                            || kod.equalsIgnoreCase("TTWKP")
                            || kod.equalsIgnoreCase("TTWLB")) {
                        GEN_REPORT_SINGLE_4 = "YES";
                        gen4 = "REGSuratMaklumanPT.rdf";
                        kd4.setKod("SMPT");
                    }
                }

                LOG.info(":: GEN_REPORT_SINGLE_4 :: " + GEN_REPORT_SINGLE_4);
                if (StringUtils.equals(GEN_REPORT_SINGLE_4, "YES")) {
                    g = saveOrUpdateDokumen(fd, kd4, permohonan.getIdPermohonan(), context);
                    path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                    LOG.info("::Path To save :: " + path4);
                    LOG.info("::Report Name ::" + gen4);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path4);
                    Future<byte[]> report4 = executor.schedule(new CallableReport(reportUtil3, gen4, params2, values2, dokumenPath + path4, peng), 2, TimeUnit.SECONDS);
                    File sign = new File(dokumenPath + path4 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    try {
                        report4.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }
                    updatePathDokumen(reportUtil3.getDMSPath(), g.getIdDokumen(), reportUtil3.getContentType());
                    for (HakmilikPermohonan list : hakmilikPermohonanList) {
                        // USE THIS TO REPLACE Surat Dafar with doc SMDP in table mohon_hakmilik column dokumen4
                        if (kd4.getKod().equalsIgnoreCase("SMDP") || "SMPT".equals(kd4.getKod()) || "SMKD".equals(kd4.getKod())) {
                            list.setDokumen4(g);
                            hakmilikPermohonanService.saveSingleHakmilikPermohonan(list);
                        }
                    }
                }

                if (negeri.equalsIgnoreCase("05")) {
                    LOG.info(":: GEN_REPORT_SINGLE_5 :: " + GEN_REPORT_SINGLE_5);
                    if (StringUtils.equals(GEN_REPORT_SINGLE_5, "YES")) {
                        LOG.info(":::::::::::::Generate Single Report 5::::::::::: ");
                        h = saveOrUpdateDokumen(fd, kd5, permohonan.getIdPermohonan(), context);
                        for (Hakmilik strata : hakmilikStrataList) {
                            LOG.info(strata.getIdHakmilikInduk());
                            values1 = new String[]{idPermohonan.trim(), strata.getIdHakmilikInduk()};
                        }
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen() + File.separator + String.valueOf(kd5.getKod()));
                        LOG.info("::Path To save :: " + path5);
                        LOG.info("::Report Name ::" + gen5);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path5);
                        Future<byte[]> report5 = executor.schedule(new CallableReport(reportUtil5, gen5, params, values1, dokumenPath + path5, peng), 3, TimeUnit.SECONDS);
                        File sign = new File(dokumenPath + path5 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            report5.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        updatePathDokumen(reportUtil5.getDMSPath(), h.getIdDokumen(), reportUtil5.getContentType());
                    }

                    LOG.info(":: GEN_REPORT_SINGLE_6 :: " + GEN_REPORT_SINGLE_6);
                    if (StringUtils.equals(GEN_REPORT_SINGLE_6, "YES")) {
                        LOG.info(":::::::::::::Generate Single Report 6::::::::::: ");
                        i = saveOrUpdateDokumen(fd, kd6, permohonan.getIdPermohonan(), context);
                        path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen() + File.separator + String.valueOf(kd6.getKod()));
//          values1 = new String[]{idPermohonan.trim(), ha};
                        for (Hakmilik strata : hakmilikStrataList) {
                            LOG.info(strata.getIdHakmilikInduk());
                            values1 = new String[]{idPermohonan.trim(), strata.getIdHakmilikInduk()};
                        }
                        LOG.info("values1 : " + values1);
                        LOG.info("::Path To save :: " + path6);
                        LOG.info("::Report Name ::" + gen6);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path6);
                        Future<byte[]> report6 = executor.schedule(new CallableReport(reportUtil5, gen6, params, values1, dokumenPath + path6, peng), 4, TimeUnit.SECONDS);
                        File sign = new File(dokumenPath + path6 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            report6.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        updatePathDokumen(reportUtil5.getDMSPath(), i.getIdDokumen(), reportUtil5.getContentType());
                    }
                } else {
                    for (HakmilikPermohonan hakmilikPermohonan: hakmilikPermohonanList){
                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                        values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                        LOG.info(":: GEN_REPORT_SINGLE_5 :: " + GEN_REPORT_SINGLE_5);
                        if (StringUtils.equals(GEN_REPORT_SINGLE_5, "YES")) {
                            LOG.info(":::::::::::::Generate Single Report 5::::::::::: ");
                            h = saveOrUpdateDokumen(fd, kd5, permohonan.getIdPermohonan(), context);
                            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen() + File.separator + String.valueOf(kd5.getKod()));
                            LOG.info("::params 5 :: " + params);
                            LOG.info("::values 5 :: " + values);
                            LOG.info("::Path To save :: " + path5);
                            LOG.info("::Report Name ::" + gen5);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path5);
                            Future<byte[]> report5 = executor.schedule(new CallableReport(reportUtil4, gen5, params, values, dokumenPath + path5, peng), 3, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path5 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report5.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }
                            updatePathDokumen(reportUtil4.getDMSPath(), h.getIdDokumen(), reportUtil4.getContentType());
                        }

                        LOG.info(":: GEN_REPORT_SINGLE_6 :: " + GEN_REPORT_SINGLE_6);
                        if (StringUtils.equals(GEN_REPORT_SINGLE_6, "YES")) {
                            LOG.info(":::::::::::::Generate Single Report 6::::::::::: ");
                            i = saveOrUpdateDokumen(fd, kd6, permohonan.getIdPermohonan(), context);
                            path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen() + File.separator + String.valueOf(kd6.getKod()));
                            LOG.info("::params 6 :: " + params);
                            LOG.info("::values 6 :: " + values);
                            LOG.info("::Path To save :: " + path6);
                            LOG.info("::Report Name ::" + gen6);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path6);
                            Future<byte[]> report6 = executor.schedule(new CallableReport(reportUtil5, gen6, params, values, dokumenPath + path6, peng), 4, TimeUnit.SECONDS);
                            File sign = new File(dokumenPath + path6 + ".sig");
                            if (sign.exists()) {
                                sign.delete();
                            }

                            try {
                                report6.get();
                            } catch (Exception ex) {
                                LOG.debug(ex.getMessage(), ex);
                            }

                            updatePathDokumen(reportUtil5.getDMSPath(), i.getIdDokumen(), reportUtil5.getContentType());
                        }
                   }//end for
              }
            }//End generate single report
        } //Delete  DHDE/DHKE Untuk Tolak selepas user tersilap daftar.
        else {
            fd1 = permohonan.getFolderDokumen();
            List<KandunganFolder> senaraiKandungan = fd1.getSenaraiKandungan();
            List<String> senaraiDokumen = new ArrayList<String>();
            List<String> senaraiKandunganFolder = new ArrayList<String>();
            List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

            for (KandunganFolder kf : senaraiKandungan) {
                if (kf == null || kf.getDokumen() == null) {
                    continue;
                }
                if (kf.getDokumen().getKodDokumen().getKod().equals("DHKE")
                        || kf.getDokumen().getKodDokumen().getKod().equals("DHDE")) {
                    senaraiDokumen.add(String.valueOf(kf.getDokumen().getIdDokumen()));
                    senaraiKandunganFolder.add(String.valueOf(kf.getIdKandunganFolder()));
                    senaraiKF.add(kf);
                }
            }

            for (KandunganFolder kf : senaraiKF) {
                senaraiKandungan.remove(kf);
            }

            for (HakmilikPermohonan hp : senaraiHkamilikPermohonan) {
                hp.setDokumen2(null);
                hp.setDokumen3(null);
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            }

            if (senaraiDokumen.size() > 0) {
                for (int x = 0; x < senaraiDokumen.size(); x++) {
                    String idDokumen = senaraiDokumen.get(x);
                    String idKandunganFolder = senaraiKandunganFolder.get(x);
                    dokumenService.deleteDokumenFolderDok(idDokumen, idKandunganFolder);
                }
            }
        }
    }

    private void updatePathDokumen1(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        LOG.debug("folder dokumen = " + fd);
        LOG.debug("kod dokumen = " + kd.getKod());
        LOG.debug("id = " + id);
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        KodKlasifikasi kodKlasifikasi = kodKlasifikasiDAO.findById(3);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setKlasifikasi(kodKlasifikasi);
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            doc.setKlasifikasi(kodKlasifikasi);
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        LOG.info("-----------TAJUK di NotadaftarValidationV2-------------");
        if (kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) {
            doc.setTajuk(kd.getKod() + "-" + id);   // example DHKE = idhakmilik
        } else if (kd.getKod().equals("4K") || kd.getKod().equals("SK")) {
            doc.setTajuk(kd.getKod() + "-" + id);
        } else if (kd.getKod().equals("SMPT") || kd.getKod().equals("SMKD") || kd.getKod().equals("BRGB")
                || kd.getKod().equals("3K") || kd.getKod().equals("2K")) {
            KodDokumen kdtemp = dokumenService.findByKodDokumen(kd.getKod());
            doc.setTajuk(kdtemp.getNama());
        } else if (kd.getKod().equals("DHKK") || kd.getKod().equals("DHDK")) {
            doc.setTajuk(kd.getKod() + "-" + id);
        } else {
            doc.setTajuk(kd.getKod());
        }
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    //Added by Aizuddin for urusan need restart back bpel
    public void initiateBPELurusanDipilih(Permohonan mohon, Pengguna pengguna) {
        LOG.info("Initiating BPEL....");
        mohon = permohonanDAO.findById(mohon.getIdPermohonan());

        List<PermohonanAtasPerserahan> permohonanAtasPerserahanList = mohon.getSenaraiPermohonanAtasPerserahan();
        for (PermohonanAtasPerserahan pas : permohonanAtasPerserahanList) {
            HakmilikPermohonan hp = pas.getHakmilikPermohonan();
            if (hp != null) {
                Permohonan permohonan = hp.getPermohonan();

                if (permohonan == null) {
                    continue;
                }

                try {
                    if (permohonan.getKodUrusan().getKePTG() == 'Y') {
                        WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                                permohonan.getIdPermohonan(), permohonan.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                                permohonan.getKodUrusan().getNama());
                    } else if (permohonan.getKodUrusan().getKePTG() == 'T') {
                        WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                                permohonan.getIdPermohonan(), permohonan.getCawangan().getKod(), pengguna.getIdPengguna(),
                                permohonan.getKodUrusan().getNama());
                    }
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            } else {
                LOG.error("Sila Pilih Perserahan Terlibat");
            }
            LOG.info("Initiating BPEL Success....");
        }
    }

    private void insertUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome, Pengguna peng) {
        LOG.info("insertUrusan :: start");
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());

        if (proposedOutcome.equals("D")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                HakmilikUrusan hu = strataPtService.findHakmilikUrusanByidHakmilikAndIdMohon(hakmilikPermohonan.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
//                HakmilikUrusan hu2 = strataPtService.findHakmilikUrusanByidHakmilikAndIdMohon(hakmilikPermohonan.getHakmilik().getIdHakmilikInduk(), permohonan.getIdPermohonan());

                if (permohonan.getKodUrusan().getKod().equals("PINDE")) {
                    Hakmilik hakmilikInduk = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilikInduk());
                    HakmilikUrusan hakmilikU = strataPtService.findHakmilikUrusanByidHakmilikAndIdMohon(hakmilikInduk.getIdHakmilik(), permohonan.getIdPermohonan());
                    if (hakmilikU == null) {
                        hakmilikUrusan = new HakmilikUrusan();
                        hakmilikUrusan.setInfoAudit(info);

                        hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                        hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                        LOG.info("Kod Daerah:::::" + hakmilikPermohonan.getHakmilik().getCawangan());
                        hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
//                LOG.info("Kod Daerah::" + hakmilikPermohonan.getHakmilik().getCawangan());
//                hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                        hakmilikUrusan.setHakmilik(hakmilikInduk);
                        hakmilikUrusan.setAktif('Y');
                        hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                        hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                        hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                        hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());

//                new added
                        hakmilikUrusan.setCukaiLama(hakmilikPermohonan.getHakmilik().getCukai());
                        hakmilikUrusan.setCukaiBaru(hakmilikPermohonan.getCukaiBaru());
                        hakmilikUrusan.setSekatanKepentinganLama(hakmilikPermohonan.getHakmilik().getSekatanKepentingan());
                        hakmilikUrusan.setSekatanKepentinganBaru(hakmilikPermohonan.getSekatanKepentinganBaru());
                        hakmilikUrusan.setSyaratNyataLama(hakmilikPermohonan.getHakmilik().getSyaratNyata());
                        hakmilikUrusan.setSyaratNyataBaru(hakmilikPermohonan.getSyaratNyataBaru());
                        if (senaraiPermohonanRujukanLuar.size() != 0) {
                            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);

                            if (permohonanRujLuar.getNoRujukan() != null) {
                                hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
                            }
                            if (permohonanRujLuar.getNoSidang() != null) {
                                hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                            }
                            if (permohonanRujLuar.getNoFail() != null) {
                                hakmilikUrusan.setNoFail(permohonanRujLuar.getNoFail());
                            }
                            if (permohonanRujLuar.getTarikhRujukan() != null) {
                                hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                            }
                            if (permohonanRujLuar.getTarikhSidang() != null) {
                                hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                            }
                            if (permohonanRujLuar.getItem() != null) {
                                hakmilikUrusan.setItem(permohonanRujLuar.getItem());
                            }
                            hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
                        }
                    }
                }

                if (!permohonan.getKodUrusan().getKod().equals("PINDE")) {
                    if (hu == null) {
                        hakmilikUrusan = new HakmilikUrusan();
                        hakmilikUrusan.setInfoAudit(info);

                        hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                        hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                        LOG.info("Kod Daerah:::::" + hakmilikPermohonan.getHakmilik().getCawangan());
                        hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
//                LOG.info("Kod Daerah::" + hakmilikPermohonan.getHakmilik().getCawangan());
//                hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                        hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
                        hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                        hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                        hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                        hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());

//                new added
                        hakmilikUrusan.setCukaiLama(hakmilikPermohonan.getHakmilik().getCukai());
                        hakmilikUrusan.setCukaiBaru(hakmilikPermohonan.getCukaiBaru());
                        hakmilikUrusan.setSekatanKepentinganLama(hakmilikPermohonan.getHakmilik().getSekatanKepentingan());
                        hakmilikUrusan.setSekatanKepentinganBaru(hakmilikPermohonan.getSekatanKepentinganBaru());
                        hakmilikUrusan.setSyaratNyataLama(hakmilikPermohonan.getHakmilik().getSyaratNyata());
                        hakmilikUrusan.setSyaratNyataBaru(hakmilikPermohonan.getSyaratNyataBaru());
                        if (senaraiPermohonanRujukanLuar.size() != 0) {
                            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);

                            if (permohonanRujLuar.getNoRujukan() != null) {
                                hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
                            }
                            if (permohonanRujLuar.getNoSidang() != null) {
                                hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                            }
                            if (permohonanRujLuar.getNoFail() != null) {
                                hakmilikUrusan.setNoFail(permohonanRujLuar.getNoFail());
                            }
                            if (permohonanRujLuar.getTarikhRujukan() != null) {
                                hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                            }
                            if (permohonanRujLuar.getTarikhSidang() != null) {
                                hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                            }
                            if (permohonanRujLuar.getItem() != null) {
                                hakmilikUrusan.setItem(permohonanRujLuar.getItem());
                            }
                        }
                        if (ArrayUtils.contains(HAKMILIK_URUSAN_TAK_KUAT_KUASA, permohonan.getKodUrusan().getKod())) {
                            if (permohonan.getKodUrusan().getKod().equals("TTWLB")
                                    || permohonan.getKodUrusan().getKod().equals("TTWLM")
                                    || permohonan.getKodUrusan().getKod().equals("TTWKP")) {
                                hakmilikUrusan.setAktif('Y');
                            } else {
                                hakmilikUrusan.setAktif('T');
                            }
                        } else {
                            hakmilikUrusan.setAktif('Y');
                        }

                        hakmilikUrusanList.add(hakmilikUrusan);
                    }
                }
            }
            batalnotaService.saveOrUpdate(hakmilikUrusanList);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR")) {
                mengDeactivekanPihakBerkerpentingan(permohonan, info, proposedOutcome);
                updateHakmilikPihak(permohonan, info, proposedOutcome, hakmilikUrusan);
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB")) {
                mengDeactivekanPihakBerkerpentingan(permohonan, info, proposedOutcome);
                updateHakmilikPihak(permohonan, info, proposedOutcome, hakmilikUrusan);
            }

            if (ArrayUtils.contains(UPDATE_HAKMILIK_PIHAK_KEPADA_TAK_KUAT_KUASA, permohonan.getKodUrusan().getKod())) {
                mengDeactivekanPihakBerkerpentingan(permohonan, info, proposedOutcome);
                updateHakmilikPihak(permohonan, info, proposedOutcome, hakmilikUrusan);
            }
            LOG.info("insertUrusan :: finish");
        }
    }

    /*
     * untuk urusan yg membatalkan urusan sebelum nya. Cth : gadaian dan
     * melepaskan gadaian
     */
    private void updateUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        LOG.info("updateUrusan :: start :: keputusan :: " + proposedOutcome);
        if (proposedOutcome.equals("D")) {
//            List<PermohonanAtasPerserahan> permohonanAtasPerserahanList = permohonan.getSenaraiPermohonanAtasPerserahan();
            List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            String idHakmilikSama;
            List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : hpList) {
                idHakmilikSama = hp.getHakmilik().getIdHakmilik();
                if (idHakmilikSama != null) {//start hakmiliksama
                    List<HakmilikUrusan> hakmilikUrusanList1 = batalNotaService.findHakmilikUrusanByIdHakmilik(idHakmilikSama);
                    for (HakmilikUrusan HU : hakmilikUrusanList1) {

//                    String idPermohonanSebelum = HU.getIdPerserahan();
                        List<HakmilikUrusan> hakmilikUrusanList2 = batalNotaService.searchHakmilikUrusanNota(
                                permohonan.getKodUrusan().getKod(), HU.getHakmilik().getIdHakmilik());//idPermohonanSebelum
                        if (hakmilikUrusanList1.size() != 0) {

                            for (HakmilikUrusan huBatal : hakmilikUrusanList2) {
                                huBatal.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                                huBatal.setIdPermohonanBatal(permohonan.getIdPermohonan());
                                huBatal.setTarikhBatal(new java.util.Date());
//                            huBatal.setKodUrusanBatal(permohonan.getKodUrusan().getKod());
//                            LOG.info("Tracing Kod Urusan Sekarang::----------------------->"+permohonan.getKodUrusan());
                                huBatal.setAktif('T');
                                info.setDiKemaskiniOleh(info.getDimasukOleh());
                                info.setTarikhKemaskini(new java.util.Date());
                                huBatal.setInfoAudit(info);
                                hakmilikUrusanList.add(huBatal);
                                LOG.info("Tracing Kod Urusan Sebelum::--------------------->" + huBatal.getKodUrusan().getNama());
                            }
                        }
                    }
                    batalnotaService.saveOrUpdate(hakmilikUrusanList);
                } //end for hakmilikSama
            }
        }
        LOG.info("updateUrusan :: finish");
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("updateNoVersi :: started");
        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> senaraiKF = fd.getSenaraiKandungan();
        List<HakmilikPermohonan> hp = new ArrayList();
        List<Hakmilik> lhk = new ArrayList();
        hp = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hakmilikPermohonan : hp) {
            Hakmilik hk = hakmilikPermohonan.getHakmilik();

            LOG.info("size check::" + senaraiKF.size());
            LOG.info("update version DHDE");

            hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
            LOG.info("update version DHKE");

            hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);

            info = hk.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hk.setInfoAudit(info);
            lhk.add(hk);
        }
        regService.simpanHakmilikList(lhk);
        LOG.info("updateNoVersi :: finish");
    }

//    update maklumat di "Geran"....
    public void updateHakmilik(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info("updateHakmilik :: start");
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();//permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }

        if (proposedOutcome.equals("D")) {

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (hakmilik != null) {
                    hakmilik.setInfoAudit(info);
                    //Update maklumat di geran (01/08/2010)
                    //cukai
                    if (hakmilikPermohonan.getCukaiBaru() != null) {
                        LOG.info("Get CUkai baru : " + hakmilikPermohonan.getCukaiBaru());
                        hakmilik.setCukai(hakmilikPermohonan.getCukaiBaru());
                    }
                    //Sekatan Kepentingan
                    if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                        hakmilik.setSekatanKepentingan(hakmilikPermohonan.getSekatanKepentinganBaru());
                    }
                    //SyaratNyata
                    if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                        hakmilik.setSyaratNyata(hakmilikPermohonan.getSyaratNyataBaru());
                    }
                    //Kategori
                    if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                        hakmilik.setKategoriTanah(hakmilikPermohonan.getKategoriTanahBaru());
                    }

                }
                hakmilikUrusan = new HakmilikUrusan();
                hakmilikUrusan.setInfoAudit(info);

                hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                LOG.info("Kod Caw:::::" + hakmilikPermohonan.getHakmilik().getCawangan().getKod());
                LOG.info("Kod Daerah:::::" + hakmilikPermohonan.getHakmilik().getDaerah().getKod());
                hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
                hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
                hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());

//                new added
                hakmilikUrusan.setCukaiLama(hakmilikPermohonan.getHakmilik().getCukai());
                hakmilikUrusan.setCukaiBaru(hakmilikPermohonan.getCukaiBaru());
                hakmilikUrusan.setSekatanKepentinganLama(hakmilikPermohonan.getHakmilik().getSekatanKepentingan());
                hakmilikUrusan.setSekatanKepentinganBaru(hakmilikPermohonan.getSekatanKepentinganBaru());
                hakmilikUrusan.setSyaratNyataLama(hakmilikPermohonan.getHakmilik().getSyaratNyata());
                hakmilikUrusan.setSyaratNyataBaru(hakmilikPermohonan.getSyaratNyataBaru());
                if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
                    hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
                }
                if (permohonanRujLuar.getNoSidang() != null) {
                    hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                }
                if (permohonanRujLuar.getNoFail() != null) {
                    hakmilikUrusan.setNoFail(permohonanRujLuar.getNoFail());
                }
                if (permohonanRujLuar.getTarikhRujukan() != null) {
                    hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                }
                if (permohonanRujLuar.getTarikhSidang() != null) {
                    hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                }
                hakmilikUrusan.setAktif('Y');
                hakmilikUrusanList.add(hakmilikUrusan);
            }
            batalnotaService.saveOrUpdate(hakmilikUrusanList);
            LOG.info("insertUrusan :: finish");
        }

    }

    //batal hakmilik n8a, ABT-k, sbtl add new kod urusan RPBNB, PRPMB, SBKSL
    public void updateStatusHakmilik(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        String kod = permohonan.getKodUrusan().getKod();
        KodStatusHakmilik kodStatusHakmilik;
        LOG.info("updateStatusHakmilik :: start");
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }

        if (proposedOutcome.equals("D")) {

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (hakmilik != null) {
                    hakmilik.setInfoAudit(info);
                    //Update maklumat di geran (01/08/2010)
                    //status
                    if (kod.equalsIgnoreCase("N8A") || kod.equalsIgnoreCase("ABT-K") || kod.equalsIgnoreCase("ABTS")
                            || kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("SBKSL") || kod.equalsIgnoreCase("TMA")
                            || kod.equalsIgnoreCase("TBTWK") || kod.equalsIgnoreCase("TTTK") || kod.equalsIgnoreCase("TTWKP")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
                        LOG.info(">>>>>>>>>>>>>>>>>>>>>>. TTTK >>>>>>>>>>>>>>>>>");
                        kodStatusHakmilik = kodStatusHakmilikDAO.findById("B");
                        hakmilik.setTarikhBatal(new java.util.Date());
                        hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                    }
                    if (kod.equalsIgnoreCase("N8AB") || kod.equalsIgnoreCase("RPBNB") || kod.equalsIgnoreCase("PRPMB")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
                        kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
                        hakmilik.setTarikhBatal(null);
                        hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                    }
                }

                batalnotaService.saveOrUpdate(hakmilikUrusanList);
                LOG.info("updateStatusHakmilik :: finish");
            }
        }

    }

//    PPKR HTB
    public void updateHakmilikPihak(Permohonan permohonan, InfoAudit info, String proposedOutcome, HakmilikUrusan hU) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info("UpdateHakmilikPihak :: start");
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }

        if (proposedOutcome.equals("D")) {

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            HakmilikPihakBerkepentingan hP = new HakmilikPihakBerkepentingan();
            PermohonanPihak mP = new PermohonanPihak();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {

                mP = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (mP != null) {
                    hP.setInfoAudit(info);
                    hP.setJenis(mP.getJenis());
//                    hP.setPerserahan(hU);
                    hP.setHakmilik(hakmilikPermohonan.getHakmilik());
                    hP.setKaveatAktif('T');
//                    hP.setNama(mP.getPihak().getNama());
                    hP.setPihak(mP.getPihak());
                    hP.setSyerPembilang(mP.getSyerPembilang());
                    hP.setSyerPenyebut(mP.getSyerPenyebut());
                    hP.setAktif('Y');
                    hP.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
                    hP.setNama(mP.getNama());
                    hP.setJenisPengenalan(mP.getJenisPengenalan());
                    hP.setNoPengenalan(mP.getNoPengenalan());
                    hP.setAlamat1(mP.getAlamat().getAlamat1());
                    hP.setAlamat2(mP.getAlamat().getAlamat2());
                    hP.setAlamat3(mP.getAlamat().getAlamat3());
                    hP.setAlamat4(mP.getAlamat().getAlamat4());
                    hP.setPoskod(mP.getAlamat().getPoskod());
                    hP.setNegeri(mP.getAlamat().getNegeri());
                    hP.setAlamatSurat(mP.getAlamatSurat());

                    batalnotaService.saveOrUpdateHakmilikPihak(hP);

                }

                LOG.info("UpdateHakmilikPihak :: finish");
            }
        }

    }

    public void updateHakmilikPihakNoSalinan(Permohonan permohonan, InfoAudit info, String proposedOutcome, HakmilikUrusan hU) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info("UpdateHakmilikPihakSalinan :: start");
        if (proposedOutcome.equals("D")) {

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            HakmilikPihakBerkepentingan hP = new HakmilikPihakBerkepentingan();

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {

                List<PermohonanPihak> mP = notaService.findByidPermohonanAndHakmilikList(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (mP.size() != 0) {
                    LOG.info(mP.size());
                    for (PermohonanPihak mPihak : mP) {
                        LOG.info("Testsetset::" + mPihak.getPihak().getNama());
                        hP = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihakBukanPM(mPihak.getPihak(), hakmilikPermohonan.getHakmilik());
                        if (hP != null) {
                            hP.setInfoAudit(info);
                            hP.setNoSalinan(hP.getNoSalinan() + 1);

                            LOG.info("Strt set no salinan lama ");

                            mPihak.setDalamanNilai1(String.valueOf(hP.getNoSalinan()));
                            hakmilikPermohonan.setLokasi(String.valueOf(hakmilikPermohonan.getHakmilik().getNoVersiDhde() + 1)); // DHde sementara
                            hakmilikPermohonan.setNoPajakan(String.valueOf(hakmilikPermohonan.getHakmilik().getNoVersiDhke() + 1)); // DHke sementara

                            batalnotaService.saveOrUpdateHakmilikPihak(hP);
                            notaService.simpanSingle(hakmilikPermohonan);
                            notaService.simpanMohon(mPihak);
                        }
                    }
                }

                LOG.info("UpdateHakmilikPihakNoSalinan :: finish");
            }
        }

    }

//  For Urusan PMDPT Only
    public void updatePermohonan(Permohonan mohon, Pengguna pengguna) {
        mohon = permohonanDAO.findById(mohon.getIdPermohonan());

        List<PermohonanAtasPerserahan> permohonanAtasPerserahanList = mohon.getSenaraiPermohonanAtasPerserahan();
        for (PermohonanAtasPerserahan pas : permohonanAtasPerserahanList) {

            Permohonan p = permohonanDAO.findById(pas.getHakmilikPermohonan().getPermohonan().getIdPermohonan());
            if (p != null) {
                InfoAudit ia = new InfoAudit();
                ia = p.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(pengguna);
                p.setInfoAudit(ia);
                p.setKeputusan(null);
                p.setStatus("TA");

                List<FasaPermohonan> senaraiFasa = p.getSenaraiFasa();
                if (senaraiFasa != null) {
                    if (!senaraiFasa.isEmpty()) {
                        p.setSenaraiFasa(null);
//            fasaPermohonanService.deleteFasaLog(senaraiFasa);
                        fasaPermohonanService.deleteFasaAll(senaraiFasa);
                    }
                }
            }
        }

    }

    private void updateAkaunLama(Permohonan permohonan, InfoAudit info) {

        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {

            List<HakmilikSebelumPermohonan> senaraiHmSblm = hp.getSenaraiHakmilikSebelum();
            for (HakmilikSebelumPermohonan hmSblm : senaraiHmSblm) {
                if (hmSblm == null
                        || StringUtils.isBlank(hmSblm.getHakmilikSejarah())
                        || hakmilikDAO.findById(hmSblm.getHakmilikSejarah()) == null) {
                    continue;
                }

                Akaun akaun = regService.getAkaunLama(hmSblm.getHakmilikSejarah());
                if (akaun != null) {
                    akaun.setStatus(kodStatusAkaunDAO.findById("B"));
                    info = akaun.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new Date());
                    akaun.setInfoAudit(info);
                    regService.saveOrUpdate(akaun);
                }
            }
        }
    }

    public void mengDeactivekanPihakBerkerpentingan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info("Deactive Pihakberkepentingan :: start");
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }

        if (proposedOutcome.equals("D")) {

            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<HakmilikPihakBerkepentingan> hPList = new ArrayList<HakmilikPihakBerkepentingan>();
            PermohonanPihak mP = new PermohonanPihak();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {

                mP = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());

                if (mP != null) {
                    hPList = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(mP.getHakmilik());
                    for (HakmilikPihakBerkepentingan hpBerkepentingan : hPList) {

                        hpBerkepentingan.setInfoAudit(info);
                        hpBerkepentingan.setAktif('T');

                        batalnotaService.saveOrUpdateHakmilikPihak(hpBerkepentingan);
                    }
                }

                LOG.info("Deactive Pihakberkepentingan :: finish");
            }
        }

    }

    private void doTransferUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {

        if ("D".equals(proposedOutcome)) {

            List<HakmilikUrusan> senaraiUrusan = new ArrayList<HakmilikUrusan>();
            List<PermohonanAtasPerserahan> list = permohonan.getSenaraiPermohonanAtasPerserahan();
            List<HakmilikPermohonan> listHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (!list.isEmpty()) {
                for (PermohonanAtasPerserahan p : list) {
                    HakmilikUrusan hu = p.getUrusan();
                    HakmilikPermohonan hp = p.getHakmilikPermohonan();
                    for (HakmilikPermohonan hakmilikPermohonan : listHakmilikPermohonan) {
                        if (hu != null && hp != null) {
                            HakmilikUrusan hu1 = new HakmilikUrusan();

                            hu1.setIdPerserahan(hu.getIdPerserahan());
                            hu1.setCawangan(hu.getCawangan());
                            hu1.setDaerah(hu.getDaerah());
                            hu1.setKodUrusan(hu.getKodUrusan());
                            hu1.setLuasTerlibat(hu.getLuasTerlibat());
                            hu1.setKodUnitLuas(hu.getKodUnitLuas());
                            hu1.setCukaiLama(hu.getCukaiLama());
                            hu1.setCukaiBaru(hu.getCukaiBaru());
                            hu1.setSekatanKepentinganLama(hu.getSekatanKepentinganLama());
                            hu1.setSekatanKepentinganBaru(hu.getSekatanKepentinganBaru());
                            hu1.setSyaratNyataLama(hu.getSyaratNyataLama());
                            hu1.setSyaratNyataBaru(hu.getSyaratNyataBaru());
                            hu1.setNoRujukan(hu.getNoRujukan());
                            hu1.setNoSidang(hu.getNoSidang());
                            hu1.setNoFail(hu.getNoFail());
                            hu1.setTarikhRujukan(hu.getTarikhRujukan());
                            hu1.setTarikhSidang(hu.getTarikhSidang());
                            hu1.setItem(hu.getItem());
                            hu1.setHakmilik(hakmilikPermohonan.getHakmilik());
                            hu1.setFolderDokumen(hu.getFolderDokumen());
                            hu1.setInfoAudit(info);
                            hu1.setAktif('Y');
                            hu1.setTarikhDaftar(hu.getTarikhDaftar());
                            hakmilikUrusanDAO.saveOrUpdate(hu1);
//              senaraiUrusan.add(hu1);

                            hu.setAktif('T');
                            InfoAudit a = hu.getInfoAudit();
                            if (a != null) {
                                a.setDiKemaskiniOleh(pengguna);
                                a.setTarikhKemaskini(new Date());
                            }
                            hu.setInfoAudit(a);
                            hu.setUrusanBatal(hakmilikUrusan);
                            senaraiUrusan.add(hu);
                        }
                    }

                }

                if (!senaraiUrusan.isEmpty()) {
//          batalnotaService.saveOrUpdate(senaraiUrusan);
                }
            }
        }

    }

    public void intergrationNota(Permohonan permohonan, Pengguna pguna) throws WorkflowException {

        if (permohonan.getPermohonanSebelum() != null && permohonan.getPermohonanSebelum().getIdPermohonan() != null) {
            LOG.debug("ID Sebelum ::" + permohonan.getPermohonanSebelum().getIdPermohonan());
            ctxOnBehalf = WorkFlowService.authenticate("sysdaftar1"); //sysjupem1

            if (ctxOnBehalf != null) {
                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getPermohonanSebelum().getIdPermohonan());
                LOG.info("Task FOund::" + l);

                for (Task t : l) {
                    stage = t.getSystemAttributes().getStage();
                    taskId = t.getSystemAttributes().getTaskId();
                    try {
                        WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                        LOG.debug("Claim Found Task::" + taskId);
                        WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "APPROVE"); // "L" comfirm balik
                        LOG.debug("Update Task Outcome" + stage);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(NotadaftarValidationV2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void notifikasi(Permohonan p, Pengguna pguna) {
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Pembatalan Perserahan - " + p.getIdPermohonan());
        n.setMesej("Permohonan Pembatalan Perserahan " + p.getIdPermohonan() + "<br/><br/>Sebab Pembatalan :" + p.getSebab());
        n.setCawangan(pguna.getKodCawangan());
        ArrayList<KodPeranan> listrole = new ArrayList<KodPeranan>();

        //TODO: IF case for Urusan
        listrole.add(kodPerananDAO.findById("5"));
        listrole.add(kodPerananDAO.findById("10"));
//        listrole.add(kodPerananDAO.findById("13"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan(), listrole);
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

//    public String getParticipant() {
//        return participant;
//    }
//
//    public void setParticipant(String participant) {
//        this.participant = participant;
//    }
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //increase no versi of VDOC if push back
//        Permohonan permohonan = context.getPermohonan();
//        if ( permohonan != null ) {
//            List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
//            List<Dokumen> senaraiDokumenToUpdate = new ArrayList<Dokumen>();
//
//            String idKumpulan = permohonan.getIdKumpulan();
//
//            if (StringUtils.isNotBlank(idKumpulan)) {
//                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
//            } else {
//                senaraiPermohonan.add(permohonan);
//            }
//
//            for(Permohonan p : senaraiPermohonan) {
//                if (p == null || p.getFolderDokumen() == null) continue;
//                List<KandunganFolder> senaraiKandungan = p.getFolderDokumen().getSenaraiKandungan();
//                for( KandunganFolder kf : senaraiKandungan ) {
//                    if ( kf == null || kf.getDokumen() == null ) continue;
//                    Dokumen d = kf.getDokumen();
//                    if (d.getKodDokumen() == null) continue;
//                    if (d.getKodDokumen().getKod().equalsIgnoreCase("VDOC")) {
//                        Double noVersi = Double.parseDouble(d.getNoVersi());
//                        d.setNoVersi( String.valueOf( noVersi + 1 ) );
//                        senaraiDokumenToUpdate.add(d);
//                    }
//                }
//            }
//
//            if (!senaraiDokumenToUpdate.isEmpty())
//                dokumenService.saveOrUpdate(senaraiDokumenToUpdate);
//        }
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }
}
