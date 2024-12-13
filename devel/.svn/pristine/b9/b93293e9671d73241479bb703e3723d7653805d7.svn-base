/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodPeranan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPembetulanHakmilik;
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
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.PermohonanRujukanLuarService;
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
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
public class NotadaftarValidation implements StageListener {

    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    PembetulanService pembetulanService;
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
    private KodStatusAkaunDAO kodStatusAkaunDAO;
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
    etanah.view.stripes.consent.GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PelarasanCukaiService pelarasanCukaiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    NotisService notisService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarServices;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikService hakmilikService2;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    IWorkflowContext ctx = null;
    IWorkflowContext ctxOnBehalf = null;
    private static final Logger LOG = Logger.getLogger(NotadaftarValidation.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private PermohonanRujukanLuar permohonanRujLuar;
    private Hakmilik hakmilik;
    private Pengguna pengguna;
    private HakmilikUrusan hakmilikUrusan;
    private String taskId;
    private String stage;
    private static String DAFTAR = "D";
    private static String TOLAK = "T";
    private static String GANTUNG = "G";
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private static final String[] URUSAN_HAKMILIK_BATAL = {"PMHHB", "RPBNB", "PRPMB"};
    private static final String[] HAKMILIK_URUSAN_TAK_AKTIF = {"IROAB", "IPMB", "PSPB", "N6AB", "N7AB", "N7BB", "N8AB", "PBB", "PBBB", "PBBT", "PBCTB", "PBCTT", "PBSCB", "ABTB", "ADMRL", "CB", "CMB", "EUB", "EUBS", "SBKSB", "SBSTB", "SBTB", "SSKPB", "ITBB", "ITPB ", "HLLB", "HLTEB", "PREMB", "PRPMB", "PSB", "PSKB", "RPBNB", "TSB", "TSSKB", "IGSAB"};
    private static final String[] URUSAN_INTEGRATION = {"PCT"};

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

            } else if (kod.equalsIgnoreCase("ECPI")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateAkaun(permohonan, info, proposedOutcome);
            } else if (kod.equalsIgnoreCase("HLPK")) {
                updateStatusHakmilik(permohonan, info, proposedOutcome);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateAkaun(permohonan, info, proposedOutcome);
            } else if (kod.equalsIgnoreCase("KVSPT") || kod.equalsIgnoreCase("KVPP") || kod.equalsIgnoreCase("KVPPT")) {
                updateHakmilikPihak(permohonan, info, proposedOutcome, hakmilikUrusan);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            } else if (kod.equalsIgnoreCase("MCLL") || kod.equalsIgnoreCase("TSSKL") || kod.equalsIgnoreCase("SSKPL") || kod.equalsIgnoreCase("EUBS")) {
                LOG.info("test kod update : " + kod);
                updateHakmilik(permohonan, info, proposedOutcome);
                updateUrusan(permohonan, info, proposedOutcome);
                if (kod.equalsIgnoreCase("TSSKL") || kod.equalsIgnoreCase("SSKPL")) {
                    intergrationNota(permohonan, pengguna);
                }

            } else if (kod.equalsIgnoreCase("PSPM") || kod.equalsIgnoreCase("PSPL") || kod.equalsIgnoreCase("PSPB")) {
                LOG.info("test kod update : " + kod);
                if (kod.equalsIgnoreCase("PSPM")) {
                    insertUrusan(permohonan, info, proposedOutcome, pengguna);
                }
                if (kod.equalsIgnoreCase("PSPB")) {
                    updateUrusanPajakan(permohonan, info, proposedOutcome);
                    insertUrusan(permohonan, info, proposedOutcome, pengguna);
                }
                if (kod.equalsIgnoreCase("PSPL")) {
                    insertUrusan(permohonan, info, proposedOutcome, pengguna);
                }

            } else if (kod.equalsIgnoreCase("ABTS") || kod.equals("N8AB") || kod.equals("RPBNB") || kod.equals("PRPMB")
                    || kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("SBKSL") || kod.equalsIgnoreCase("TBTWK") || kod.equalsIgnoreCase("TTTK")
                    || kod.equalsIgnoreCase("TTWKP") || kod.equalsIgnoreCase("SBTM")
                    || kod.equalsIgnoreCase("SBKSM")) {
                updateStatusHakmilik(permohonan, info, proposedOutcome);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateUrusan(permohonan, info, proposedOutcome);

                if (kod.equalsIgnoreCase("SBKSL") || kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("TBTWK") || kod.equalsIgnoreCase("TTTK") || kod.equalsIgnoreCase("TTWKP")) {
                    updateAkaun(permohonan, info, proposedOutcome);
                }

                if (kod.equalsIgnoreCase("SBTL")
                        || kod.equalsIgnoreCase("SBTM") || kod.equalsIgnoreCase("SBKSM") || kod.equalsIgnoreCase("SBKSL")
                        || kod.equalsIgnoreCase("ABT-K")) {
                    intergrationNota(permohonan, pengguna);
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
                    || kod.equalsIgnoreCase("PREMB") || kod.equalsIgnoreCase("ABTBH") || kod.equalsIgnoreCase("KRMB") || kod.equalsIgnoreCase("PSM")
                    || kod.equalsIgnoreCase("HSPS") || kod.equalsIgnoreCase("HSPTK") || kod.equalsIgnoreCase("HKPS") || kod.equalsIgnoreCase("HKPTK")
                    || kod.equalsIgnoreCase("PBM") || kod.equalsIgnoreCase("HSPB") || kod.equalsIgnoreCase("HSBTK") || kod.equalsIgnoreCase("HKPB")
                    || kod.equalsIgnoreCase("HKBTK") || kod.equalsIgnoreCase("CM") || kod.equalsIgnoreCase("HSC") || kod.equalsIgnoreCase("HSCTK")
                    || kod.equalsIgnoreCase("HKC") || kod.equalsIgnoreCase("HKCTK") || kod.equalsIgnoreCase("TSSKM") || kod.equalsIgnoreCase("SSKPM")
                    || kod.equalsIgnoreCase("SBSTM") || kod.equalsIgnoreCase("HSSB") || kod.equalsIgnoreCase("HKSB") || kod.equalsIgnoreCase("HSSTB")
                    || kod.equalsIgnoreCase("HKSTB")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                if (kod.equalsIgnoreCase("HMVB")) {
                    updateStatusHakmilik(permohonan, info, proposedOutcome);
                }
                //exclude TSSKM from update permohonan batal
                if (!(kod.equalsIgnoreCase("TSSKM")) && !(kod.equalsIgnoreCase("PSM")) && !(kod.equalsIgnoreCase("SSKPM")) && !(kod.equalsIgnoreCase("PBM"))) {
                    updateUrusan(permohonan, info, proposedOutcome);
                }
                if (kod.equalsIgnoreCase("SBTB") || kod.equalsIgnoreCase("SBSTL") || kod.equalsIgnoreCase("SBSTB") || kod.equalsIgnoreCase("HLLB")
                        || kod.equalsIgnoreCase("N7AB") || kod.equalsIgnoreCase("ABTB") || kod.equalsIgnoreCase("PSL") || kod.equalsIgnoreCase("PSB")
                        || kod.equalsIgnoreCase("PSM") || kod.equalsIgnoreCase("HSPS") || kod.equalsIgnoreCase("HSPTK") || kod.equalsIgnoreCase("HKPS")
                        || kod.equalsIgnoreCase("HKPTK") || kod.equalsIgnoreCase("PBL") || kod.equalsIgnoreCase("PBB") || kod.equalsIgnoreCase("HSPB")
                        || kod.equalsIgnoreCase("HKBTK") || kod.equalsIgnoreCase("HKPB") || kod.equalsIgnoreCase("HSBTK") || kod.equalsIgnoreCase("PBM")
                        || kod.equalsIgnoreCase("CL") || kod.equalsIgnoreCase("CB") || kod.equalsIgnoreCase("TSSKB") || kod.equalsIgnoreCase("CM")
                        || kod.equalsIgnoreCase("HSC") || kod.equalsIgnoreCase("HSCTK") || kod.equalsIgnoreCase("HKC") || kod.equalsIgnoreCase("HKCTK")
                        || kod.equalsIgnoreCase("TSSKM") || kod.equalsIgnoreCase("SSKPB") || kod.equalsIgnoreCase("SSKPM") || kod.equalsIgnoreCase("SBSTM")
                        || kod.equalsIgnoreCase("HSSB") || kod.equalsIgnoreCase("HKSB") || kod.equalsIgnoreCase("SBKSB") || kod.equalsIgnoreCase("HSSTB")
                        || kod.equalsIgnoreCase("HKSTB")) {
                    //**NOTES: use this to send back module integration 
                    intergrationNota(permohonan, pengguna);
                }
            } //intergration strata
            else if ((kod.equalsIgnoreCase("PBBM")) || (kod.equalsIgnoreCase("HTT")) || (kod.equalsIgnoreCase("HTB"))
                    //added by murali to initiate back to STRATA for urusan PBCTM/PBCTT on 01-10-2012
                    || (kod.equalsIgnoreCase("PBCTM")) || (kod.equalsIgnoreCase("PBCTT"))) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                intergrationNota(permohonan, pengguna);

            } else if ((kod.equalsIgnoreCase("SHSK")) || (kod.equalsIgnoreCase("SHKK"))) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                updateHakmilikPihakNoSalinan(permohonan, info, proposedOutcome, hakmilikUrusan);

            } else if (kod.equals("PBBT")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
//                updateHakmilikPihakNoSalinan(permohonan, info, proposedOutcome, hakmilikUrusan);

            } else if (kod.equalsIgnoreCase("N8A")) {
                updateStatusHakmilik(permohonan, info, proposedOutcome);
                updateUrusan(permohonan, info, proposedOutcome);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                intergrationNota(permohonan, pengguna);
                updateAkaunLama(permohonan, info);

                //Integration for pengambilan HLLA, HLLS added by Aizuddin
            } else if (kod.equalsIgnoreCase("HLLA") || kod.equalsIgnoreCase("HLLS") || kod.equalsIgnoreCase("HLTE") || kod.equalsIgnoreCase("ABT-D")
                    || kod.equalsIgnoreCase("ABTKB") || kod.equalsIgnoreCase("ABT-K")) {
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
                insertPermohonanSebelum(permohonan, pengguna);
                intergrationNota(permohonan, pengguna);
                if (kod.equalsIgnoreCase("ABTKB") || kod.equalsIgnoreCase("ABT-K")) { //
                    if (kod.equalsIgnoreCase("ABT-K")) {
                        updateAkaun(permohonan, info, proposedOutcome);
                    }

                    updateStatusHakmilik(permohonan, info, proposedOutcome);
                    updateUrusan(permohonan, info, proposedOutcome); // add by azri 19/10/2013: ABTKB need to unactive urusan terlibat
                }
            } else if (kod.equalsIgnoreCase("N6A")) {
                String negeri = conf.getProperty("kodNegeri");
                //TODO: Filter by kod negeri
                if (negeri.equalsIgnoreCase("04")) {
                    List<HakmilikPermohonan> lsH = permohonan.getSenaraiHakmilik();
                    for (HakmilikPermohonan hP : lsH) {
                        pelarasanCukaiService.larasCukaiBaki(permohonan, hP.getHakmilik().getIdHakmilik(), "0", "notis6A", pengguna);
                        LOG.debug("End of penyelarasan");
                        LOG.debug("Start Send Notifikasi");
//            TODO: if case for intergration
                        notifikasi(permohonan, pengguna);
                        if (tx.wasCommitted()) {
                            tx.begin();
                        }
                    }
                }
                LOG.debug("End of penyelarasan");
//            LOG.debug("Start Send Notifikasi");
//            TODO: if case for intergration
//            notifikasi(permohonan, pengguna);
                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            } else if (kod.equalsIgnoreCase("N7A") || kod.equalsIgnoreCase("N7B")
                    || kod.equalsIgnoreCase("N7BB") || kod.equalsIgnoreCase("STMA") || kod.equalsIgnoreCase("TMA")
                    || kod.equalsIgnoreCase("TMAK")) {
                intergrationNota(permohonan, pengguna);

                if (kod.equalsIgnoreCase("TMAK")) {
                    updateAkaun(permohonan, info, proposedOutcome);
                }

                insertUrusan(permohonan, info, proposedOutcome, pengguna);
            } else {
               if (!kod.equalsIgnoreCase("PBBL")) {
                   insertUrusan(permohonan, info, proposedOutcome, pengguna);
               }

            }
            if (permohonan.getKeputusan().getKod().equals(DAFTAR)) {
                updateNoVersi(permohonan, info);
            }

            if (ArrayUtils.contains(URUSAN_INTEGRATION, permohonan.getKodUrusan().getKod())) {
                intergrationNota(permohonan, pengguna);
            }

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
//    ctx.addMessage(" -Pendaftaran Urusan Berjaya.");
        StringBuilder msg = new StringBuilder(" - Pendaftaran Urusan Berjaya.");
        if (permohonan.getPermohonanSebelum() != null) {
            msg.append(" Notifikasi telah dihantar ke modul sebelum.");
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

            Permohonan sebelum = permohonan.getPermohonanSebelum();

            //penghantaran notifikasi kepada yg berkenaan
            if (sebelum != null) {
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

                String idPermohonanSblm = sebelum.getIdPermohonan();

                if (permohonan.getKodUrusan().getKod().equals("N8A")
                        && idPermohonanSblm.toUpperCase().contains("REV")) {
                    KodUrusan kodUrusan = kodUrusanDAO.findById("100");

                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();

                    for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
                    }

                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kodUrusan, pengguna, senaraiHakmilik, permohonan);
                }
            }

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
        //TODO: generate the report....
        FolderDokumen fd1 = new FolderDokumen();
        Pengguna pengguna = context.getPengguna();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null; //DHKE
        Dokumen f = null; //DHDE
        Dokumen g = null; //Report ke-4
        Dokumen h = null; //Report ke-5
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
        StringBuilder sb = new StringBuilder();
        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon == null || mohon.getKeputusan() == null) {
                continue;
            }
        }
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
            String[] params2 = {"p_id_mohon"};
            String[] values2 = {idPermohonan};
//        String path = "";
            String path2 = ""; //DHKE
            String path3 = ""; //DHDE
            String path4 = ""; //Report ke-4
            String path5 = ""; //Report ke-5

            String gen2 = ""; //DHKE
            String gen3 = ""; //DHDE
            String gen4 = ""; //Report ke-4
            String gen5 = ""; //Report ke-5

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen(); //DHKE
            KodDokumen kd3 = new KodDokumen(); //DHDE
            KodDokumen kd4 = new KodDokumen(); //Report ke-4
            KodDokumen kd5 = new KodDokumen(); //Report ke-5

            String[] URUSAN_WITHOUT_DHKE_DHDE = {
                "PBCTL",
                "PBCTT",
                "PBCTM",
                "TENPT",
                "KVLP",
                "KVPP",
                "KVPPT",
                "KVSPT",
                "PPM",
                "HTPV",
                "PBCTB"
            };

            if (ArrayUtils.contains(URUSAN_HAKMILIK_BATAL, permohonan.getKodUrusan().getKod())) {
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    Hakmilik hm = hakmilikPermohonan.getHakmilik();
                    KodStatusHakmilik kodSts = kodStatusHakmilikDAO.findById("T");
                    InfoAudit ia = new InfoAudit();
                    ia.setTarikhKemaskini(new Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    hm.setKodStatusHakmilik(kodSts);
                    hm.setInfoAudit(ia);
                    hakmilikService2.saveHakmilik(hm);
                }

            }

            if (ArrayUtils.contains(URUSAN_WITHOUT_DHKE_DHDE, permohonan.getKodUrusan().getKod())) {
                for (HakmilikPermohonan hpMany : hakmilikPermohonanList) {
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hpMany.getHakmilik().getIdHakmilik()};
                    String negeri = conf.getProperty("kodNegeri");
                    if (negeri.equalsIgnoreCase("05")) {
                        if (kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBCTT") || kod.equalsIgnoreCase("PBCTM")
                                || kod.equalsIgnoreCase("TENPT") || kod.equalsIgnoreCase("KVLP") || kod.equalsIgnoreCase("KVPP") || kod.equalsIgnoreCase("KVSPT")
                                || kod.equalsIgnoreCase("KVPPT")
                                || kod.equalsIgnoreCase("PPM") || kod.equalsIgnoreCase("HTPV")
                                || kod.equalsIgnoreCase("PBCTB")) {
                            // gen2 = "REGB4K_NS.rdf";
                            gen2 = "REGB4KKK_NS.rdf"; // DHKK rdf name
                            gen3 = "REGB4KDK_NS.rdf"; // DHDK rdf name  
                        }
                    } else if (negeri.equalsIgnoreCase("04")) {
                        if (kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBCTT") || kod.equalsIgnoreCase("PBCTM")
                                || kod.equalsIgnoreCase("TENPT") || kod.equalsIgnoreCase("KVLP") || kod.equalsIgnoreCase("KVPP") || kod.equalsIgnoreCase("KVSPT")
                                || kod.equalsIgnoreCase("KVPPT")
                                || kod.equalsIgnoreCase("PPM") || kod.equalsIgnoreCase("HTPV")
                                || kod.equalsIgnoreCase("PBCTB")) {
                            //gen2 = "REGB4K_MLK.rdf";
                            gen2 = "REGB4KDHKK_MLK.rdf";
                            gen3 = "REGB4KDHDK_MLK.rdf";
                        }
                    }

                    //Make sure to add kod_dokumen at database for setKod()
                    if (kod.equalsIgnoreCase("PBCTL") || kod.equalsIgnoreCase("PBCTT") || kod.equalsIgnoreCase("PBCTM")
                            || kod.equalsIgnoreCase("TENPT") || kod.equalsIgnoreCase("KVLP") || kod.equalsIgnoreCase("KVPP")
                            || kod.equalsIgnoreCase("KVSPT")
                            || kod.equalsIgnoreCase("KVPPT")
                            || kod.equalsIgnoreCase("PPM") || kod.equalsIgnoreCase("HTPV")
                            || kod.equalsIgnoreCase("PBCTB")) {
                        if (negeri.equalsIgnoreCase("05")) {
                            kd2.setKod("DHKK");
                            kd3.setKod("DHDK");
                        } else {
                            //kd2.setKod("4K");
                            kd2.setKod("DHKK");
                            kd3.setKod("DHDK");
                        }
                        e = saveOrUpdateDokumen(fd, kd2, hpMany.getHakmilik().getIdHakmilik(), context);
                        path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                        LOG.info("::Path To save :: " + path2);
                        LOG.info("::Report Name ::" + gen2);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
                        Future<byte[]> b4k = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                        File sign = new File(dokumenPath + path2 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }

                        try {
                            b4k.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        LOG.debug("saving dokumen : " + e.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hpMany.getHakmilik().getIdHakmilik());

                        // Update Path
                        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());

                        hpMany.setDokumen2(e);

                        ///// Dokumen 3                       
                        e = saveOrUpdateDokumen(fd, kd3, hpMany.getHakmilik().getIdHakmilik(), context);
                        path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                        LOG.info("::Path To save :: " + path3);
                        LOG.info("::Report Name ::" + gen3);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hpMany.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                        Future<byte[]> dhdk = executor.submit(new CallableReport(reportUtil, gen3, params, values, dokumenPath + path3, peng));
                        File sign2 = new File(dokumenPath + path3 + ".sig");
                        if (sign2.exists()) {
                            sign2.delete();
                        }

                        try {
                            dhdk.get();
                        } catch (Exception ex) {
                            LOG.debug(ex.getMessage(), ex);
                        }

                        LOG.debug("saving dokumen : " + e.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hpMany.getHakmilik().getIdHakmilik());

                        // Update Path
                        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen(), reportUtil.getContentType());
                        hpMany.setDokumen3(e);

                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpMany);
                    }
                }

                String idKumpulan = permohonan.getIdKumpulan();

                if (StringUtils.isNotBlank(idKumpulan)) {
                    senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
                } else {
                    senaraiPermohonan.add(permohonan);
                }

                ///generate 19A
                for (Permohonan mohon : senaraiPermohonan) {
                    if (mohon == null || mohon.getKeputusan() == null) {
                        continue;
                    }

                    List<HakmilikPermohonan> senaraiHkamilikPermohonan1 = mohon.getSenaraiHakmilik();

                    KodUrusan ku = mohon.getKodUrusan();

                    if (ku.getKod().equals("KVSPT")) {
                        String documentPath_ = conf.getProperty("document.path");
                        String[] params_ = new String[]{"p_id_mohon"};
                        String[] values_ = new String[]{mohon.getIdPermohonan()};
                        String path_ = "";
                        String reportName_ = "";
                        String kod1 = conf.getProperty("kodNegeri");
                        KodDokumen kd_ = null;
                        Dokumen doc19A = null;

                        kd_ = kodDokumenDAO.findById("19A");
                        if (kod1.equals("04")) {
                            //reportName = "REG_Notis19A_Pers_mlk.rdf";
                            reportName_ = "REG_Notis19A_mlk.rdf";
                        } else {
                            reportName_ = "REG_Notis19A_Pers.rdf";
                        }

                        FolderDokumen fd_ = folderDokumenDAO.findById(mohon.getFolderDokumen().getFolderId());

                        InfoAudit ia = new InfoAudit();

                        doc19A = semakDokumenService.semakDokumen(kd_, fd_, mohon.getIdPermohonan());
                        if (doc19A == null) {
                            LOG.debug("new Document");
                            doc19A = new Dokumen();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            doc19A.setBaru('Y');
                            doc19A.setNoVersi("1.0");
                        } else {
                            LOG.debug("old Document");
                            ia = doc19A.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());
                            doc19A.setBaru('T');
                            Double noVersi = Double.parseDouble(doc19A.getNoVersi());
                            doc19A.setNoVersi(String.valueOf(noVersi + 1));
                        }
                        doc19A.setFormat("application/pdf");
                        doc19A.setInfoAudit(ia);
                        LOG.debug(doc19A.getInfoAudit().getDimasukOleh().getIdPengguna());
                        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(3);
                        doc19A.setKlasifikasi(klasifikasi_AM);
                        doc19A.setTajuk(kd_.getKod() + "-" + mohon.getIdPermohonan());
                        doc19A.setKodDokumen(kd_);
                        doc19A.setDalamanNilai1(null);
                        doc19A = dokumenService.saveOrUpdate(doc19A);
                        KandunganFolder kf = kandunganFolderService.findByDokumen(doc19A, fd);
                        if (kf == null) {
                            kf = new KandunganFolder();
                        } else {
                            ia = kf.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());
                        }
                        kf.setInfoAudit(ia);
                        kf.setFolder(fd);
                        kf.setDokumen(doc19A);
                        dokumenService.saveKandunganWithDokumen(kf);

                        /**/
                        path_ = mohon.getFolderDokumen().getFolderId() + File.separator + String.valueOf(doc19A.getIdDokumen());
                        reportUtil.generateReport(reportName_, params_, values_, documentPath_ + path_, pengguna);
                        /**/
                        Dokumen d_ = dokumenService.findById(doc19A.getIdDokumen());
                        d_.setFormat(reportUtil.getContentType());
                        d_.setNamaFizikal(reportUtil.getDMSPath());
                        dokumenService.saveOrUpdate(d_);

                    }
                }
                ///generate 19A------   
            } else {
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                    String negeri = conf.getProperty("kodNegeri");

                    hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());

                    if (hakmilik.getIdHakmilikInduk() != null) {
                        if (negeri.equalsIgnoreCase("05")) {
                            gen2 = "REGB4KKK_NS.rdf"; // DHKK rdf name
                            gen3 = "REGB4KDK_NS.rdf"; // DHDK rdf name  
                        } else if (negeri.equalsIgnoreCase("04")) {
                            gen2 = "REGB4KDHKK_MLK.rdf";
                            gen3 = "REGB4KDHDK_MLK.rdf";
                        }
                        kd2.setKod("DHKK");
                        kd3.setKod("DHDK");
                    } else {
                        if (negeri.equalsIgnoreCase("05")) {
                            gen2 = "regBorangHMDHKE.rdf";
                            gen3 = "regBorangHMDHDE.rdf";
                        } else if (negeri.equalsIgnoreCase("04")) {
                            gen2 = "regBorangHMDHKEml.rdf";
                            gen3 = "regBorangHMDHDEml.rdf";
                        }
                        kd2.setKod("DHKE");
                        kd3.setKod("DHDE");
                    }

//          kd2.setKod("DHKE");
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
//          kd3.setKod("DHDE");
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

            //Added by Aizuddin if want generate new report put it here. Just add kod urusan
            //For urusan generate single report doesnt matter how many hakmilik that permohonan have       
            String[] URUSAN_GEN_NEW_REPORT_SINGLE = {
                "HLLA",
                "HLLS"};

            if (ArrayUtils.contains(URUSAN_GEN_NEW_REPORT_SINGLE, permohonan.getKodUrusan().getKod())) {
                String negeri = conf.getProperty("kodNegeri");
                if (negeri.equalsIgnoreCase("05")) {
                    if (kod.equalsIgnoreCase("HLLA") || kod.equalsIgnoreCase("HLLS")) {
                        gen4 = "RegBorang28B.rdf";
                    }
                } else if (negeri.equalsIgnoreCase("04")) {
                    if (kod.equalsIgnoreCase("HLLA") || kod.equalsIgnoreCase("HLLS")) {
                        gen4 = "RegBorang28B.rdf";
                    }
                }

                //Make sure to add kod_dokumen at database for setKod()
                if (kod.equalsIgnoreCase("HLLA") || kod.equalsIgnoreCase("HLLS")) {
                    kd4.setKod("28B");
                    g = saveOrUpdateDokumen(fd, kd4, permohonan.getIdPermohonan(), context);
                    path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                    LOG.info("::Path To save :: " + path4);
                    LOG.info("::Report Name ::" + gen4);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + permohonan.getIdPermohonan() + " and saving it to:" + path4);
                    Future<byte[]> b28b = executor.schedule(new CallableReport(reportUtil3, gen4, params2, values2, dokumenPath + path4, peng), 2, TimeUnit.SECONDS);
                    File sign = new File(dokumenPath + path4 + ".sig");
                    if (sign.exists()) {
                        sign.delete();
                    }

                    try {
                        b28b.get();
                    } catch (Exception ex) {
                        LOG.debug(ex.getMessage(), ex);
                    }

                    for (HakmilikPermohonan hpSingle : hakmilikPermohonanList) {
                        LOG.info("=============hakmilikPermohonan============= " + hpSingle);
                        LOG.debug("saving dokumen : " + g.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hpSingle.getHakmilik().getIdHakmilik());
                        // Update path
                        updatePathDokumen(reportUtil3.getDMSPath(), g.getIdDokumen(), reportUtil3.getContentType());
                        hpSingle.setDokumen4(g);
                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hpSingle);
                    }
                }
                //End of generate single report

                //For urusan that need generate report for each hakmilik
                String[] URUSAN_GEN_NEW_REPORT_MANY = { //TO-DO
                };

                if (ArrayUtils.contains(URUSAN_GEN_NEW_REPORT_MANY, permohonan.getKodUrusan().getKod())) {
                    //TO-DO                    
                }//End of generating reports base on hakmilik                
            }//End of generating new report
        } //    Delete  DHDE/DHKE Untuk Tolak selepas user tersilap daftar.
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
                for (int i = 0; i < senaraiDokumen.size(); i++) {
                    String idDokumen = senaraiDokumen.get(i);
                    String idKandunganFolder = senaraiKandunganFolder.get(i);
                    dokumenService.deleteDokumenFolderDok(idDokumen, idKandunganFolder);
                }
            }

        }
//    
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
        if (kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) {
            doc.setTajuk(kd.getKod() + "-" + id);   // example DHKE = idhakmilik
            //Added by Aizuddin
        } else if (kd.getKod().equals("4K") || kd.getKod().equals("3K") || kd.getKod().equals("2K")
                || kd.getKod().equals("DHKK") || kd.getKod().equals("DHDK")
                || kd.getKod().equals("SKDK") || kd.getKod().equals("SKDD")) {
            doc.setTajuk(kd.getKod() + "(" + id + ")");
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

    //permohonan = HLLA, HLLS, HLLE, HLLB, Added back plz update b4 commit
    private void insertPermohonanSebelum(Permohonan permohonan, Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        if (permohonan.getPermohonanSebelum() != null
                && permohonan.getPermohonanSebelum().getIdPermohonan() != null) {
            LOG.debug("ID Sebelum ::" + permohonan.getPermohonanSebelum().getIdPermohonan());
            Permohonan p = permohonan.getPermohonanSebelum();
            p.setPermohonanSebelum(permohonan);
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
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
                if (ArrayUtils.contains(HAKMILIK_URUSAN_TAK_AKTIF, permohonan.getKodUrusan().getKod())) {
                    hakmilikUrusan.setAktif('T');
                } else {
                    hakmilikUrusan.setAktif('Y');
                }

                hakmilikUrusanList.add(hakmilikUrusan);
            }

            if (permohonan.getKodUrusan().getKod().equals("PSPL") || permohonan.getKodUrusan().getKod().equals("PSPB")) {
                List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
                Hakmilik hm = senaraiHP.get(0).getHakmilik();
                List<HakmilikUrusan> senaraiHU = regService.searchHakmilikUrusanActive(hm.getIdHakmilik());
                for (HakmilikUrusan Hu : senaraiHU) {
                    if (Hu.getKodUrusan().getKod().equals("PSPM")) {
                        Hu.setAktif('T');
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("ECPI")) {
                List<HakmilikPermohonan> senaraiHp = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHp) {
                    List<Hakmilik> senaraiHmStrata = hakmilikService2.getHakmilikStratanInduk(hp.getHakmilik().getIdHakmilik());
                    for (Hakmilik HakmilikStrata : senaraiHmStrata) {
                        if (HakmilikStrata.getNoVersiDhke() != 0) {
                            hakmilikUrusan = new HakmilikUrusan();
                            hakmilikUrusan.setInfoAudit(info);
                            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                            hakmilikUrusan.setCawangan(HakmilikStrata.getCawangan());
                            hakmilikUrusan.setDaerah(HakmilikStrata.getDaerah());
                            hakmilikUrusan.setHakmilik(HakmilikStrata);
                            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                            hakmilikUrusan.setAktif('Y');
                            hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
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
                            hakmilikUrusanList.add(hakmilikUrusan);
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PBBT")) {
                List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
                Hakmilik hm = senaraiHP.get(0).getHakmilik();
                List<HakmilikUrusan> senaraiHU = regService.searchHakmilikUrusanActive(hm.getIdHakmilik());
                for (HakmilikUrusan Hu : senaraiHU) {
                    if (Hu.getKodUrusan().getKod().equals("PBBM")) {
                        Hu.setAktif('T');
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
            LOG.info("insertUrusan :: finish");
        }
    }


    /*
     * untuk urusan yg membatalkan urusan sebelum nya. Cth : gadaian dan
     * melepaskan gadaian
     */
    private void updateUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        if (proposedOutcome.equals("D")) {
            KodUrusan ku = permohonan.getKodUrusan();
            LOG.info("-> kod urusan : " + ku.getKod());
            if (ku.getKod().equals("PRPMB") || ku.getKod().equals("SSKPB") || ku.getKod().equals("SSKPL") || ku.getKod().equals("CB")
                    || ku.getKod().equals("CL") || ku.getKod().equals("PBL") || ku.getKod().equals("PBB") || ku.getKod().equals("PSB")
                    || ku.getKod().equals("PSL") || ku.getKod().equals("SBKSL") || ku.getKod().equals("SBKSB") || ku.getKod().equals("SBSTM")
                    || ku.getKod().equals("SBSTL") || ku.getKod().equals("SSSTB") || ku.getKod().equals("SBSTB") || ku.getKod().equals("SBTM") || ku.getKod().equals("TSSKL")
                    || ku.getKod().equals("SBTL") || ku.getKod().equals("SBTB") || ku.getKod().equals("SBKSM") || ku.getKod().equals("ABTKB") || ku.getKod().equals("EUB")
                    || ku.getKod().equals("CM") || ku.getKod().equals("PBM") || ku.getKod().equals("TSSKB") || ku.getKod().equals("ABTB")) {

                List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
                List<PermohonanAtasPerserahan> list = permohonan.getSenaraiPermohonanAtasPerserahan();

                /* SEARCH URUSAN BATAL FROM TABLE MOHON ATAS URUSAN */
                for (PermohonanAtasPerserahan pap : list) {
                    HakmilikUrusan huBatal = pap.getUrusan();
                    LOG.info("-> id mohon terlibat : " + pap.getUrusan().getIdPerserahan() + ", kod urusan : " + pap.getUrusan().getKodUrusan().getKod());
                    huBatal.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                    huBatal.setIdPermohonanBatal(permohonan.getIdPermohonan());
                    huBatal.setTarikhBatal(new java.util.Date());
                    huBatal.setAktif('T');
                    info.setDiKemaskiniOleh(info.getDimasukOleh());
                    info.setTarikhKemaskini(new java.util.Date());
                    huBatal.setInfoAudit(info);
                    hakmilikUrusanList.add(huBatal);
                }
                batalnotaService.saveOrUpdate(hakmilikUrusanList);

            } else {

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
                                    LOG.info("Tracing Kod Urusan Sebelum::---> " + huBatal.getKodUrusan().getNama());
                                }
                            }
                        }
                        batalnotaService.saveOrUpdate(hakmilikUrusanList);
                    } //end for hakmilikSama
                }
            }
        }
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("updateNoVersi :: started");
        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> senaraiKF = fd.getSenaraiKandungan();
        List<HakmilikPermohonan> hp = new ArrayList();
        List<Hakmilik> lhk = new ArrayList();
        hp = permohonan.getSenaraiHakmilik();
        if (!permohonan.getKodUrusan().getKod().equals("ECPI")) {
            for (HakmilikPermohonan hakmilikPermohonan : hp) {
                Hakmilik hk = hakmilikPermohonan.getHakmilik();

                LOG.info("size check::" + senaraiKF.size());
                LOG.info("update version DHDE");

                if (hk.getNoVersiDhde() != null) {
                    if (permohonan.getKeputusan().getKod().equals("D")) {
                        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
                    }
                } else {
                    hk.setNoVersiDhde(1);
                }
                LOG.info("update version DHKE");

                if (hk.getNoVersiDhke() != null) {
                    if (permohonan.getKeputusan().getKod().equals("D")) {
                        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
                    }
                } else {
                    hk.setNoVersiDhke(1);
                }
                //  hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);

                info = hk.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hk.setInfoAudit(info);
                lhk.add(hk);
            }
        } else {
            for (HakmilikPermohonan hakmilikPermohonan : hp) {
                List<Hakmilik> senaraiHmStrata = hakmilikService2.getHakmilikStratanInduk(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                for (Hakmilik hmStrata : senaraiHmStrata) {
                    int versi = hmStrata.getNoVersiDhke();
                    if (versi != 0) {
                        Hakmilik hk = hmStrata;

                        if (hk.getNoVersiDhde() != null) {
                            if (permohonan.getKeputusan().getKod().equals("D")) {
                                hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
                            }
                        } else {
                            hk.setNoVersiDhde(1);
                        }
                        LOG.info("update version DHKE");

                        if (hk.getNoVersiDhke() != null) {
                            if (permohonan.getKeputusan().getKod().equals("D")) {
                                hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
                            }
                        } else {
                            hk.setNoVersiDhke(1);
                        }
                        //  hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);

                        info = hk.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        hk.setInfoAudit(info);
                        lhk.add(hk);
                    }
                }
            }

            for (HakmilikPermohonan hakmilikPermohonan : hp) {
                Hakmilik hk = hakmilikPermohonan.getHakmilik();

                LOG.info("size check::" + senaraiKF.size());
                LOG.info("update version DHDE");

                if (hk.getNoVersiDhde() != null) {
                    if (permohonan.getKeputusan().getKod().equals("D")) {
                        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
                    }
                } else {
                    hk.setNoVersiDhde(1);
                }
                LOG.info("update version DHKE");

                if (hk.getNoVersiDhke() != null) {
                    if (permohonan.getKeputusan().getKod().equals("D")) {
                        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
                    }
                } else {
                    hk.setNoVersiDhke(1);
                }
                //  hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);

                info = hk.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hk.setInfoAudit(info);
                lhk.add(hk);
            }

        }

        regService.simpanHakmilikList(lhk);
        LOG.info("updateNoVersi :: finish");
    }

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

            if (!permohonan.getKodUrusan().getKod().equals("HSPL") || (!permohonan.getKodUrusan().getKod().equals("HSPB"))
                    || (!permohonan.getKodUrusan().getKod().equals("PSPL")) || (!permohonan.getKodUrusan().getKod().equals("PSPB"))
                    || (!permohonan.getKodUrusan().getKod().equals("HSPM"))) {

                List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
                List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                    hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());

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
                    hakmilikUrusan.setAktif('Y');

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

                    hakmilikUrusanList.add(hakmilikUrusan);

                    if (hakmilik != null) {
                        hakmilik.setInfoAudit(info);
                        //Update maklumat di geran (01/08/2010)
                        //cukai
                        if (hakmilikPermohonan.getCukaiBaru() != null) {
                            LOG.info("Get CUkai baru : " + hakmilikPermohonan.getCukaiBaru());
                            hakmilik.setCukai(hakmilikPermohonan.getCukaiBaru());
                            hakmilik.setCukaiSebenar(hakmilikPermohonan.getCukaiBaru());
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
                        //kegunaan Tanah(rujukan hasil)
                        if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                            hakmilik.setKegunaanTanah(hakmilikPermohonan.getKodKegunaanTanah());
                        }

                    }
                }
                batalnotaService.saveOrUpdate(hakmilikUrusanList);
                LOG.info("insertUrusan :: finish");
            }
        }

    }

//    update maklumat di "Geran"....
    public void insertPajakan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
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

            List<HakmilikPermohonan> senaraiHp = permohonan.getSenaraiHakmilik();
            if (!permohonan.getKodUrusan().getKod().equals("PSPL")
                    || (!permohonan.getKodUrusan().getKod().equals("PSPB"))
                    || (!permohonan.getKodUrusan().getKod().equals("HSPM"))) {
                for (HakmilikPermohonan hp : senaraiHp) {
                    Hakmilik h = hp.getHakmilik();
                    PermohonanPembetulanHakmilik senaraiPPH = pembetulanService.findByidPidH(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                    if (senaraiPPH != null) {

                        h.setTarikhDaftar(senaraiPPH.getTarikhDaftar());
                        h.setTempohPegangan(senaraiPPH.getTempohPenganganSemasa());
                        h.setTarikhLuput(senaraiPPH.getTarikhLuputSemasa());
                        h.setInfoAudit(info);

                        regService.simpanHakmilik(h);
                    }
                }
            }
        }

    }

    public void updateUrusanPajakan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
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

                List<HakmilikUrusan> senaraihakmilikUrusan = regService.searchHakmilikUrusanActive(hakmilikPermohonan.getHakmilik().getIdHakmilik());

                for (HakmilikUrusan hu : senaraihakmilikUrusan) {
                    if (hu.getKodUrusan().getKod().equals("PSPL")) {
                        hu.setAktif('T');
                        hu.setIdPermohonanBatal(permohonan.getIdPermohonan());
//                    hu.setTarikhBatal(java.sql.Date);
                        hu.setInfoAudit(info);
                    }
                }
            }
            batalnotaService.saveOrUpdate(hakmilikUrusanList);
            LOG.info("insertUrusan :: finish");
        }

    }

    public void updateAkaun(Permohonan permohonan, InfoAudit info, String proposedOutcome) {

        if (proposedOutcome.equals("D")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("ECPI")) {
                    List<Akaun> Senaraiakaun = regService.findAkaunByIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    if (Senaraiakaun.size() > 0) {
                        for (Akaun akaun : Senaraiakaun) {
                            akaun.setStatus(kodStatusAkaunDAO.findById("F"));
                            akaun.getInfoAudit().setDiKemaskiniOleh(pengguna);
                            akaun.getInfoAudit().setTarikhKemaskini(new Date());
                            info.setDiKemaskiniOleh(info.getDimasukOleh());
                            info.setTarikhKemaskini(new java.util.Date());
                            akaun.setInfoAudit(info);
                            regService.saveOrUpdate(akaun);
                        }
                    }
                } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("N8A")) {
                    List<Akaun> Senaraiakaun = regService.findAkaunByIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    if (Senaraiakaun.size() > 0) {
                        for (Akaun akaun : Senaraiakaun) {
                            akaun.setStatus(kodStatusAkaunDAO.findById("B"));
                            akaun.getInfoAudit().setDiKemaskiniOleh(pengguna);
                            akaun.getInfoAudit().setTarikhKemaskini(new Date());
                            info.setDiKemaskiniOleh(info.getDimasukOleh());
                            info.setTarikhKemaskini(new java.util.Date());
                            akaun.setInfoAudit(info);
                            regService.saveOrUpdate(akaun);
                        }
                    }
                } else {
                    List<Akaun> Senaraiakaun = regService.findAkaunByIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    if (Senaraiakaun.size() > 0) {
                        for (Akaun akaun : Senaraiakaun) {
                            akaun.setStatus(kodStatusAkaunDAO.findById("B"));
                            akaun.getInfoAudit().setDiKemaskiniOleh(pengguna);
                            akaun.getInfoAudit().setTarikhKemaskini(new Date());
                            info.setDiKemaskiniOleh(info.getDimasukOleh());
                            info.setTarikhKemaskini(new java.util.Date());
                            akaun.setInfoAudit(info);
                            regService.saveOrUpdate(akaun);
                        }
                    }
                }
            }
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
                            || kod.equalsIgnoreCase("SBTL") || kod.equalsIgnoreCase("SBKSL") || kod.equalsIgnoreCase("TBTWK")
                            || kod.equalsIgnoreCase("TTTK") || kod.equalsIgnoreCase("TTWKP") || kod.equalsIgnoreCase("HLPK")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
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
                    if (kod.equalsIgnoreCase("HMVB")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
                        kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
//                        hakmilik.setTarikhBatal(null);
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
//            PermohonanPihak mP = new PermohonanPihak();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                List<PermohonanPihak> senaraiMp = notaService.findByidPermohonanAndHakmilikList(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
                for (PermohonanPihak mP : senaraiMp) {
                    if (mP != null) {
                        HakmilikPihakBerkepentingan hP = new HakmilikPihakBerkepentingan();
                        hP.setInfoAudit(info);
                        hP.setJenis(mP.getJenis());
                        hP.setPerserahan(hU);
                        hP.setHakmilik(hakmilikPermohonan.getHakmilik());
                        hP.setKaveatAktif('T');
                        hP.setNama(mP.getPihak().getNama());
                        hP.setJenisPengenalan(mP.getJenisPengenalan());
                        hP.setNoPengenalan(mP.getNoPengenalan());
                        if (mP.getAlamat() != null) {
                            hP.setAlamat1(mP.getAlamat().getAlamat1() != null ? mP.getAlamat().getAlamat1() : "");
                            hP.setAlamat2(mP.getAlamat().getAlamat2() != null ? mP.getAlamat().getAlamat2() : "");
                            hP.setAlamat3(mP.getAlamat().getAlamat3() != null ? mP.getAlamat().getAlamat3() : "");
                            hP.setAlamat4(mP.getAlamat().getAlamat4() != null ? mP.getAlamat().getAlamat4() : "");
                            hP.setPoskod(mP.getAlamat().getPoskod() != null ? mP.getAlamat().getPoskod() : "");
                            hP.setNegeri(mP.getAlamat().getNegeri() != null ? mP.getAlamat().getNegeri() : null);
                        }

                        hP.setWargaNegara(mP.getWargaNegara());
                        hP.setPihak(mP.getPihak());
                        hP.setSyerPembilang(mP.getSyerPembilang());
                        hP.setSyerPenyebut(mP.getSyerPenyebut());
                        hP.setAktif('Y');
                        hP.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());

                        batalnotaService.saveOrUpdateHakmilikPihak(hP);

                    }

                    LOG.info("UpdateHakmilikPihak :: finish");
                }
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
                        java.util.logging.Logger.getLogger(NotadaftarValidation.class
                                .getName()).log(Level.SEVERE, null, ex);
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
            List<Akaun> listAcc = hp.getHakmilik().getSenaraiAkaun();
            for (Akaun acc : listAcc) {
                if (!acc.getStatus().equals("A")) {
                    acc.setStatus(kodStatusAkaunDAO.findById("B"));
                    info = acc.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new Date());
                    acc.setInfoAudit(info);
                    regService.saveOrUpdate(acc);
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
