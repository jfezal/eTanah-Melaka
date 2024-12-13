/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.strata.CommonService;
import etanah.view.strata.InitiateTaskService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class PendaftaranEndorsanValidation implements StageListener {

    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    InitiateTaskService its;
    private static final Logger LOG = Logger.getLogger(PendaftaranEndorsanValidation.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    LelongService lelongService;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (mohon.getKodUrusan().getKod().equals("PNB")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {

                        t.add("STRLaporanTarikBalik_MLK.rdf");
                        t2.add("LTBHS");
                        t.add("STRSLulusTarikBalik_MLK.rdf");
                        t2.add("SKBS");
                        comm.reportGen(mohon, t, t2);
                    }
//                    else {
//                        t.add("STRSLulusTarikBalik_NS.rdf");
//                        t2.add("SKBS");
//                        comm.reportGen(mohon, t, t2);
//                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRLaporanTarikBalik_MLK.rdf");
                        t2.add("LTBHS");
                        t.add("STRSTolakTarikBalik_MLK.rdf");
                        t2.add("SPBS");
                        comm.reportGen(mohon, t, t2);
                    }
//                    else {
//                        t.add("STRSTolakTarikBalik_NS.rdf");
//                        t2.add("SPBS");
//                        comm.reportGen(mohon, t, t2);
//                    }

                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        String outcome = null;
        FasaPermohonan mohonFasa = new FasaPermohonan();

        Permohonan permohonan = sc.getPermohonan();
        Pengguna peng = (Pengguna) sc.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        //mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
        mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getPermohonanSebelum().getIdPermohonan(), "keputusan1");
        //if (mohonFasa != null) {
        //if (mohonFasa.getKeputusan() != null) {
        // if (mohonFasa.getKeputusan().getKod().equals("L")) {
        KodUrusan kod = kodUrusanDAO.findById("PBBB");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        Permohonan permohonanBaru = new Permohonan();
        its.setPengguna(peng);
        try {
            permohonanBaru = its.createPermohonanBaru(permohonan, kod);
            its.setHakmilikPermohonan(permohonan, permohonanBaru);
            its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
            outcome = string;
        } catch (WorkflowException ex) {
            java.util.logging.Logger.getLogger(PendaftaranEndorsanValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(PendaftaranEndorsanValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PendaftaranEndorsanValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        // } else {
        //     outcome = null;
        // }
        //} else {
        //     outcome = null;
        //  }

        // } else {
        //     sc.addMessage("Sila  masukkan dan simpan keputusan sebelum menekan butang Selesai.");
        //     return null;
        //  }

        return outcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        Permohonan permohonan = sc.getPermohonan();
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    try {
                        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getPermohonanSebelum().getIdPermohonan());
                        try {
                            WorkFlowService.withdrawTask(l.get(0).getSystemAttributes().getTaskId());
                            permohonan.getPermohonanSebelum().setStatus("BP");
                            lelongService.saveOrUpdate(permohonan.getPermohonanSebelum());
                        } catch (StaleObjectException ex) {
                            java.util.logging.Logger.getLogger(PendaftaranEndorsanValidation.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(PendaftaranEndorsanValidation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        return string;
    }

    @Override
    public void afterPushBack(StageContext sc) {
    }
}
