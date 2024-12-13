/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
//import etanah.model.FasaPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.PembangunanService;
import java.util.ArrayList;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
//import java.util.List;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;

/**
 *
 * @author nursyazwani
 */
public class ChartingNotification implements StageListener {

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
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(ChartingNotification.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        if (context.getPermohonan().getKeputusan().getKod().equals("T")) {
            //Permohonan permohonan = context.getPermohonan();
            //Pengguna peng = (Pengguna) context.getPengguna();
            //InfoAudit infoAudit = new InfoAudit();
            //infoAudit.setDimasukOleh(peng);
            //infoAudit.setTarikhMasuk(new java.util.Date());
            //idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            //String[] name = {"idHakmilik"};
            //Object[] value = {idHakmilik};
            //List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
            //KodUrusan kod = kodUrusanDAO.findById("TSSKB");
            //LOG.info(kod.getNama());
            //LOG.info(permohonan.getFolderDokumen());
            //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//            proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
            // Added new code to test Withdraw
//            try {
//                String idPermohonan = permohonan.getIdPermohonan();
//                LOG.debug("idPermohonan = " + idPermohonan);
//
//                List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
//                LOG.info("senaraiTask : " + senaraiTask.size());
//                if (senaraiTask.isEmpty()) {
//                    LOG.info("-----idPermohonan tidak di jumpai-----");
//                } else {
//                    Task task = (Task) senaraiTask.get(0);
//                    if (task != null) {
//                        LOG.info("-----idPermohonan di jumpai-----");
//                        LOG.info(task);
//                        taskId = task.getSystemAttributes().getTaskId();
//                    }
//                }
//                WorkFlowService.withdrawTask(taskId);
//                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
//                permohonan = permohonanDAO.findById(idPermohonan);
//                permohonan.setStatus("SL");
//                devService.simpanPermohonan(permohonan);
//            } catch (Exception ex) {
//                LOG.error(ex);
//            }
            proposedOutcome = "T";
//            return proposedOutcome;
        } else if (context.getPermohonan().getKeputusan().getKod().equals("L") || context.getPermohonan().getKeputusan().getKod().equals("KS")) {
            Notifikasi n = new Notifikasi();
            n.setTajuk("Makluman Charting");
            n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah dibuat Charting Kelulusan.");
            Pengguna p = context.getPengguna();
            n.setCawangan(p.getKodCawangan());
            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
            list.add(kodPerananDAO.findById("40"));
//            list.add(kodPerananDAO.findById("84")); //melaka - SO kanan
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            n.setInfoAudit(ia);
            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            context.addMessage(" " + "Makluman kepada Penolong Pegawai Tanah Kanan telah dihantar.");

            System.out.println("-->Kod Urusan : " + context.getPermohonan().getKodUrusan().getKod());
            System.out.println("proposedOutcome : " + proposedOutcome);
            if (context.getPermohonan().getKodUrusan().getKod().equals("PSBT")) {

                proposedOutcome = "L";
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        if (context.getPermohonan().getKeputusan().getKod().equals("T")) {
            if (context.getPermohonan().getKodUrusan().getKod().equals("SBMS") 
                    || context.getPermohonan().getKodUrusan().getKod().equals("TSPSS") 
                    || context.getPermohonan().getKodUrusan().getKod().equals("PSBT") 
                    || context.getPermohonan().getKodUrusan().getKod().equals("PYTN")
                    || context.getPermohonan().getKodUrusan().getKod().equals("SBPS")
                    || context.getPermohonan().getKodUrusan().getKod().equals("PPCS")
                    || context.getPermohonan().getKodUrusan().getKod().equals("PPCB")
                    || context.getPermohonan().getKodUrusan().getKod().equals("PPCBA")) {
                try {
                    String idPermohonan = context.getPermohonan().getIdPermohonan();
                    LOG.debug("idPermohonan = " + idPermohonan);
                    
                    Thread.sleep(10000);
                    List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
                    LOG.info("senaraiTask : " + senaraiTask.size());
                    if (senaraiTask.isEmpty()) {
                        LOG.info("-----idPermohonan tidak di jumpai-----");
                    } else {
                        for (int a = 0; a < senaraiTask.size(); a++) {
                            Task task = (Task) senaraiTask.get(a);
                            if (task != null) {
                                LOG.info("-----idPermohonan di jumpai-----");
                                LOG.info(task);
                                taskId = task.getSystemAttributes().getTaskId();
                                LOG.info("Task Id : " + taskId);
                                WorkFlowService.withdrawTask(taskId);
                                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                            }
                        }
                    }

                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }
        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
//    void initiate(StageContext context, String proposedOutcome) {
//        Permohonan permohonan = context.getPermohonan();
//        Pengguna peng = (Pengguna) context.getPengguna();
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(peng);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//        String[] name = {"idHakmilik"};
//        Object[] value = {idHakmilik};
//        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//        KodUrusan kod = kodUrusanDAO.findById("HSPS");
////        LOG.info(kod.getNama());
////        LOG.info(permohonan.getFolderDokumen());
//        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//    }
}
