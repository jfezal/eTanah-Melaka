/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

/**
 *
 * @author hazirah
 */
import com.google.inject.Inject;
import etanah.workflow.StageListener;
import etanah.workflow.StageContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.hibernate.Session;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import org.apache.log4j.Logger;
import etanah.service.NotifikasiService;
import etanah.service.PengambilanService;
import etanah.service.common.HakmilikService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;

public class SemakLuasTanah implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    PermohonanBaruLelongService gipw;
    private static final Logger LOG = Logger.getLogger(SemakLuasTanah.class);
    private String idHakmilik;
    private String keputusan;
    private String idPermohonanReg;
    FasaPermohonan mohonaFasa;
    HakmilikPermohonan hakmilikPermohonan;
    @Inject
    PenggunaDAO penggunaDAO;

    public FasaPermohonan getMohonaFasa() {
        return mohonaFasa;
    }

    public void setMohonaFasa(FasaPermohonan mohonaFasa) {
        this.mohonaFasa = mohonaFasa;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        LOG.info("Initiate Laporan Pelan B1");
        String idPermohonan;
        idPermohonan = permohonan.getIdPermohonan();
        FasaPermohonan fasaPermohonan = pengambilanService.findFasaPermohonanPelanB1(permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());


        List<HakmilikPermohonan> senaraiHakmilikKecil = new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> senaraiHakmilikBesar = new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> senaraiHakmilikSama = new ArrayList<HakmilikPermohonan>();

        senaraiHakmilikBesar = hakmilikpermohonanservice.findByIdHakmilikL4(idPermohonan);
        senaraiHakmilikKecil = hakmilikpermohonanservice.findByIdHakmilikK4(idPermohonan);
        senaraiHakmilikSama = hakmilikpermohonanservice.findByIdHakmilikX4(idPermohonan);

        List<Hakmilik> senaraiHakmilikL4 = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikK4 = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikX4 = new ArrayList<Hakmilik>();
        Pengguna pp = penggunaDAO.findById(fasaPermohonan.getInfoAudit().getDimasukOleh().getIdPengguna());
        if (permohonan.getSenaraiHakmilik().size() == 1) {
            if (fasaPermohonan != null) {
                if (!(senaraiHakmilikKecil.isEmpty()) && senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {

                    for (HakmilikPermohonan hakPermohonan : senaraiHakmilikKecil) {
                        senaraiHakmilikK4.add(hakPermohonan.getHakmilik());
                    }

                    KodUrusan kod = kodUrusanDAO.findById("HKABS");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikK4, permohonan, context.getStageName());
                    proposedOutcome = "K4";
                }
                if (senaraiHakmilikKecil.isEmpty() && !(senaraiHakmilikBesar.isEmpty()) && senaraiHakmilikSama.isEmpty()) {

                    for (HakmilikPermohonan hakPermohonan : senaraiHakmilikBesar) {
                        senaraiHakmilikL4.add(hakPermohonan.getHakmilik());
                    }

                    KodUrusan kod = kodUrusanDAO.findById("HKABS");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikL4, permohonan, context.getStageName());
                    proposedOutcome = "L4";
                }
                if (senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && !(senaraiHakmilikSama.isEmpty())) {

                    for (HakmilikPermohonan hakPermohonan : senaraiHakmilikSama) {
                        senaraiHakmilikX4.add(hakPermohonan.getHakmilik());
                    }

                    KodUrusan kod = kodUrusanDAO.findById("HKABS");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikX4, permohonan, context.getStageName());
                    proposedOutcome = "X4";
                }
            }
        }

        if (permohonan.getSenaraiHakmilik().size() > 1) {
            if (fasaPermohonan != null) {
                List<Hakmilik> hh = new ArrayList<Hakmilik>();
                StringBuilder sb = new StringBuilder();
                switch (gatCase(senaraiHakmilikKecil, senaraiHakmilikBesar, senaraiHakmilikSama)) {
                    case 1:
                        //lebih semua
                        LOG.info("case 1");
                        if (!senaraiHakmilikBesar.isEmpty()) {
                            LOG.debug("Luas Semua Lebih :: Case 1");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikBesar) {
                                senaraiHakmilikL4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikL4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "L4";
                        break;
                    case 2:

                        //kurang semua
                        if (!senaraiHakmilikKecil.isEmpty()) {
                            LOG.debug("Luas Semua Kurang :: Case 2");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikKecil) {
                                senaraiHakmilikK4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikK4, permohonan, context.getStageName());

                        }
                        LOG.info("case 2");
                        proposedOutcome = "K4";
                        break;
                    case 3:
                        //sama semua
                        LOG.info("case 3");
                        if (!senaraiHakmilikSama.isEmpty()) {
                            LOG.debug("Luas Semua Sama :: Case 3");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikSama) {
                                senaraiHakmilikX4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikX4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "X4";
                        break;
                    case 4:
                        //satu sama (idMohon Baru) , satu kurang (idMohon Lama)
                        LOG.info("case 4");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : senaraiHakmilikSama) {
                            hh.add(hp.getHakmilik());
                        }
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(senaraiHakmilikSama, null, pp, permohonan, hh));
                        if (!senaraiHakmilikSama.isEmpty()) {
                            LOG.debug("Luas Sama :: Case 4");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikSama) {
                                senaraiHakmilikX4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikX4, permohonan, context.getStageName());

                        }
                        if (!senaraiHakmilikKecil.isEmpty()) {
                            LOG.debug("Luas Kurang :: Case 4");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikKecil) {
                                senaraiHakmilikK4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikK4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "K4";
                        break;
                    case 5:
                        //satu sama (idMohon Baru), satu lebih (idMohon Lama)
                        LOG.info("case 5");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : senaraiHakmilikSama) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(senaraiHakmilikSama, null, pp, permohonan, hh));
                        if (!senaraiHakmilikSama.isEmpty()) {
                            LOG.debug("Luas Sama :: Case 5");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikSama) {
                                senaraiHakmilikX4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikX4, permohonan, context.getStageName());

                        }
                        if (!senaraiHakmilikBesar.isEmpty()) {
                            LOG.debug("Luas Lebih :: Case 5");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikBesar) {
                                senaraiHakmilikL4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikL4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "L4";
                        break;
                    case 6:
                        //satu kurang (idMohon Baru), satu lebih (idMohon Lama)
                        LOG.info("case 6");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : senaraiHakmilikKecil) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya. Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(null, senaraiHakmilikKecil, pp, permohonan, hh));
                        if (!senaraiHakmilikBesar.isEmpty()) {
                            LOG.debug("Luas Lebih :: Case 6");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikBesar) {
                                senaraiHakmilikL4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikL4, permohonan, context.getStageName());

                        }
                        if (!senaraiHakmilikKecil.isEmpty()) {
                            LOG.debug("Luas Kurang :: Case 6");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikKecil) {
                                senaraiHakmilikK4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikK4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "L4";
                        break;
                    case 7:
                        //sama (idMohon Baru) , kurang (idMohon Baru) , lebih (idMohon Lama)
                        LOG.info("case 7");
                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : senaraiHakmilikKecil) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage(" - Penghantaran Berjaya<br/> - Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(senaraiHakmilikKecil, null, pp, permohonan, hh));

                        hh = new ArrayList<Hakmilik>();
                        for (HakmilikPermohonan hp : senaraiHakmilikSama) {
                            hh.add(hp.getHakmilik());
                        }
                        sb = new StringBuilder();
                        for (Hakmilik hk : hh) {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(hk.getIdHakmilik());
                        }
                        context.addMessage("<br/> - Hakmilik Yang Terlibat " + sb.toString() + " Akan Disambung Pada ID Permohonan Berikut : " + newIdMohon(null, senaraiHakmilikSama, pp, permohonan, hh));
                        if (!senaraiHakmilikSama.isEmpty()) {
                            LOG.debug("Luas Sama :: Case 7");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikSama) {
                                senaraiHakmilikX4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikX4, permohonan, context.getStageName());

                        }
                        if (!senaraiHakmilikKecil.isEmpty()) {
                            LOG.debug("Luas Kurang :: Case 7");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikKecil) {
                                senaraiHakmilikK4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikK4, permohonan, context.getStageName());

                        }
                        if (!senaraiHakmilikBesar.isEmpty()) {
                            LOG.debug("Luas Lebih :: Case 7");

                            for (HakmilikPermohonan hakPermohonan : senaraiHakmilikBesar) {
                                senaraiHakmilikL4.add(hakPermohonan.getHakmilik());
                            }
                            KodUrusan kod = kodUrusanDAO.findById("HKABS");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowHKABS(kod, peng, senaraiHakmilikL4, permohonan, context.getStageName());

                        }
                        proposedOutcome = "L4";
                        break;
                    default:
                        //todo
                        proposedOutcome = "";
                        break;
                }
            }
        }

        return proposedOutcome;
    }

    public int gatCase(List<HakmilikPermohonan> senaraiHakmilikKecil, List<HakmilikPermohonan> senaraiHakmilikBesar, List<HakmilikPermohonan> senaraiHakmilikSama) {
        //if (!senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
        if (senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
            //case 1
            return 1;
        }
        // if (senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
        if (!senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
            //case 2
            return 2;
        }
        if (senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
            //case 3
            return 3;
        }
        //  if (!senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
        if (!senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
            //case 4
            return 4;
        }
        //if (!senaraiHakmilikKecil.isEmpty() && senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
        if (!senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
            //case 5
            return 5;
        }
        //   if (senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
        if (!senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && senaraiHakmilikSama.isEmpty()) {
            //case 6
            return 6;
        }
        if (!senaraiHakmilikKecil.isEmpty() && !senaraiHakmilikBesar.isEmpty() && !senaraiHakmilikSama.isEmpty()) {
            //case 7
            return 7;
        }
        return 0;
    }

    public String newIdMohon(List<HakmilikPermohonan> hpX, List<HakmilikPermohonan> hpK, Pengguna pp, Permohonan permohonan, List<Hakmilik> hh) {

        Permohonan p = null;
        String kptsn = "";
        String KodUrusan = permohonan.getKodUrusan().getKod();
        try {
            if (hpX != null) {
                kptsn = "X4";

                p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById(KodUrusan), pp, hh, permohonan, permohonan.getFolderDokumen());
            }
            if (hpK != null) {
                kptsn = "K4";
                p = gipw.generateIdPermohonanWorkflowGetIdMohonACQ(kodUrusanDAO.findById(KodUrusan), pp, hh, permohonan, permohonan.getFolderDokumen());
            }
            FasaPermohonan peng = notisPenerimaanService.getPenggunaKemasukan01(permohonan.getIdPermohonan());
            if (peng != null) {
                pp = penggunaDAO.findById(peng.getIdPengguna());
            }
            updateOutcome(p, pp, kptsn);
        } catch (StaleObjectException ex) {
            LOG.info(ex);
        } catch (WorkflowException ex) {
            LOG.info(ex);
        }
        return p.getIdPermohonan();
    }

    @SuppressWarnings("static-access")
    public void updateOutcome(Permohonan p, Pengguna pp, String kptsn) throws WorkflowException, StaleObjectException {
        //first stage tambah advance rule
        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
        LOG.info("IDMohon : " + p.getIdPermohonan());
        List<Task> taskList = null;
        do {
            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
            LOG.info("taskList : " + taskList.size());
        } while (taskList.isEmpty());
        if (taskList.size() > 0) {
            Task t = taskList.get(0);
            String stageID = null;
            if (t.getSystemAttributes().getTaskId() != null) {
                String taskId = t.getSystemAttributes().getTaskId();
                LOG.info("taskId : " + taskId);
                Task tt = null;
                if (stageID != null) {
                    LOG.info("-----ptptdlelong----");
                    ctx = workFlowService.authenticate(pp.getIdPengguna());
                }
                LOG.info("-----ctx---- : " + ctx);
                tt = workFlowService.acquireTask(taskId, ctx);
                LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                do {
                    taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                    LOG.info("taskList : " + taskList.size());
                } while (taskList.isEmpty());
                t = taskList.get(0);
                taskId = t.getSystemAttributes().getTaskId();
                stageID = workFlowService.updateTaskOutcome(taskId, ctx, kptsn);
                LOG.info(" new stageID : " + stageID);
                LOG.info("----Abis Dah---- : " + stageID);
            }
        }
        LOG.info("------Finish------");
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
