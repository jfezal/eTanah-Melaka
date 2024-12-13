/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.model.PermohonanAsal;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.HakmilikDAO;
import etanah.model.Transaksi;
//import etanah.model.PermohonanTuntutanBayar;
import etanah.dao.TransaksiDAO;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import etanah.model.Akaun;
import etanah.dao.AkaunDAO;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.DokumenKewangan;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanTuntutan;
import etanah.view.kaunter.BayaranPerihalActionBean;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 *
 * @author syaiful
 */
public class PindaanN9Validator implements StageListener {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PembangunanService devService;
    @Inject
    AkaunDAO akaunDAO;
    private static final Logger LOG = Logger.getLogger(PindaanN9Validator.class);
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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
        String outcome = "";
        try {
            //validate to create permohonan sebelum
            Permohonan permohonan = context.getPermohonan();
            if (permohonan.getKodUrusan().getKod().equals("RLTB") && context.getStageName().equals("sediakertasringkas3bln") ||
                    permohonan.getKodUrusan().getKod().equals("RPS") && context.getStageName().equals("sediaderafmmk") ||
                    permohonan.getKodUrusan().getKod().equals("RPP") && context.getStageName().equals("sediaderafmmk")) {
                PermohonanAsal mohonAsal = devService.findPermohonanAsal(permohonan.getIdPermohonan());
                if (mohonAsal == null) {
                    context.addMessage("Sila masukkan Maklumat Permohonan Sebelum: " + permohonan.getIdPermohonan());
                    LOG.info("mohon asal is null!!");
                    return null;
                }
            }
            outcome = checkstageID(context);
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        proposedOutcome = outcome;
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "rekodbantahan");
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);
            if (stage.contentEquals("cetaksrtkpsnptdbyrnhmilik")) {
                value = "CD";
                return value;
            }
            if (stage.contentEquals("cetaksrtkpsnptgbyrnhmilik")) {
                value = "CG";
                return value;
            }
            if (stage.contentEquals("cetaksrtkpsnmmkbyrnhmilik")) {
                value = "CK";
                return value;
            }
            if (fp != null && stage.contentEquals("rekodbantahan")) {
                if (fp.getKeputusan() != null && fp.getKeputusan().getKod().equals("BA")) {
                    value = "BA";
                    return value;
                }
                if (fp.getKeputusan() != null && fp.getKeputusan().getKod().equals("BT")) {
                    value = "BT";
                    return value;
                }
            }
            if (stage.contentEquals("semakkertasmmktangguh")) {
                value = "CT";
                return value;
            }
            if (stage.contentEquals("mohonpelanpindaan")) {
                value = "T";
                return value;
            }
            if (stage.contentEquals("cetaksuratbatal")) {
                value = "COMPLETE";
                return value;
            }
            if (stage.contentEquals("terimabayaran")) {
                PermohonanTuntutanKos ptk = devService.findMohonTuntutKosByPremium(permohonan.getIdPermohonan());
                PermohonanTuntutanBayar ptb = devService.findTuntuBayarByPremium(ptk.getIdKos());

//                List<Transaksi> trans = devService.findTransaksiNotis(permohonan.getIdPermohonan());
//                Transaksi tr;
//                tr = trans.get(0);
//                Akaun ak = akaunDAO.findById(tr.getAkaunDebit().getNoAkaun());
//                BigDecimal bal = ak.getBaki();
                if (ptb != null) {
                    if (ptk.getAmaunTuntutan().equals(ptb.getAmaun())) {
//                if(bal.equals(BigDecimal.ZERO)){
                        //value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                        LOG.info("Bayaran L1");
                        value = "L";
                        return value;
                    } else {
                        LOG.info("Bayaran XY");
                        value = "XY";
                        return value;
                    }
                } else {
                    LOG.info("Bayaran L2");
                    value = "L";
                    return value;
                }
            }
            if (stage.contentEquals("terimabayaranrps")) {
                PermohonanTuntutanKos ptk = devService.findMohonTuntutKosByPremium(permohonan.getPermohonanSebelum().getIdPermohonan());
                PermohonanTuntutanBayar ptb = devService.findTuntuBayarByPremium(ptk.getIdKos());
                LOG.info("-->terimabayaranrps<--");
                LOG.info("Amount Tuntut : " + ptk.getAmaunTuntutan());
                LOG.info("Amount : " + ptb.getAmaun());
                if (ptb != null) {
                    if (ptk.getAmaunTuntutan().equals(ptb.getAmaun())) {
                        LOG.info("-->Bayaran L1");
                        value = "BY";
                        return value;
                    } else {
                        LOG.info("-->Bayaran L2");
                        value = "XY";
                        return value;
                    }
                } else {
                    LOG.info("-->Bayaran L3");
                    value = "BY";
                    return value;
                }
            }
            if (stage.contentEquals("terimabayaran3bln")) {
                List<Transaksi> trn = devService.findTransaksiNotis(permohonan.getIdPermohonan());
                BigDecimal total = BigDecimal.ZERO;
                Transaksi tr;
                for (int i = 0; i < trn.size(); i++) {
                    tr = new Transaksi();
                    tr = trn.get(i);
                    total = total.add(tr.getAmaun());
                }
                System.out.println("Total-> : " + total);//total baru

                tr = trn.get(0);
                if (tr.getStatus().getKod() != 'B') {
                    DokumenKewangan dk = new DokumenKewangan();
                    dk = devService.findDokumenKewanganByIdKewDok(tr.getDokumenKewangan().getIdDokumenKewangan());
                    BigDecimal bal23 = total.multiply(new BigDecimal(0.3333));
                    BigDecimal bal = dk.getAmaunBayaran();
                    System.out.println("Bal : " + bal); //amount dari kew_dokumen
                    System.out.println("Bal23 : " + bal23); //amaount 1/3
                    if (bal.equals(total)) {

                        String taskId2 = null;
                        String currUser2 = null;
                        PermohonanAsal pa = devService.findPermohonanAsal(permohonan.getIdPermohonan());
                        List<Task> lt = WorkFlowService.queryTasksByAdmin(pa.getIdPermohonanAsal().getIdPermohonan());
                        for (Task tt : lt) {
                            taskId2 = tt.getSystemAttributes().getTaskId();
                            currUser2 = tt.getSystemAttributes().getAcquiredBy();
                            System.out.println("Task Id --->" + taskId2);
                            System.out.println("Curr User -->" + currUser2);
                        }

                        IWorkflowContext currContext2 = WorkFlowService.authenticate(currUser2);
                        try {
                            WorkFlowService.updateTaskOutcome(taskId2, currContext2, "APPROVE");
                        } catch (Exception ex) {
                            LOG.error(ex);
                            System.out.println("EXXX : "+ex);
                            //return new RedirectResolution(PindaanN9Validator.class).addParameter("error", "Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
                        }

                        System.out.println("Lulus");
                        value = "L";
                        return value;
                    } else if (bal.equals(bal23)) {
                        System.out.println("R6");
                        value = "R6";
                        return value;
                    }
                } else {
                    System.out.println("XY");
                    value = "XY";
                    return value;
                }
            }
            if (stage.contentEquals("terimabayaran6bln")) {
//                List<Transaksi> trn = devService.findTransaksiNotis(permohonan.getIdPermohonan());
//                BigDecimal total = BigDecimal.ZERO;
//                Transaksi tr;
//                for(int i=0; i < trn.size(); i++){
//                    tr = new Transaksi();
//                    tr = trn.get(i);
//                    total = total.add(tr.getAmaun());
//                }
//                tr = trn.get(0);
//                if(tr.getStatus().getKod() != 'B'){
//                    DokumenKewangan dk = new DokumenKewangan();
//                    dk = devService.findDokumenKewanganByIdKewDok(tr.getDokumenKewangan().getIdDokumenKewangan());
//                    BigDecimal bal13 = total.multiply(new BigDecimal(0.3333));
//                    BigDecimal bal = dk.getAmaunBayaran();
//                    if(bal.equals(total)){
//                        value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
//                        return value;
//                    }
//                    else if(bal.equals(bal13)){
//                        value = "RK";
//                        return value;
//                    }
//                }
//                else{
//                    value = "XY";
//                    return value;
//                }
                value = "T";
                return value;
            }

            if (stage.contentEquals("kpsnkertaspertimbptg")) {
                if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "T";
                }
            }

        }
        return value;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
