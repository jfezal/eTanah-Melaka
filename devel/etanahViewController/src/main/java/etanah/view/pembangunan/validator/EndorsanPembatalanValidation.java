/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author faidzal
 */
public class EndorsanPembatalanValidation implements StageListener {

    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPermohonan generateIdPerserahanWorkflow;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonanSebelum = new Permohonan();
        Permohonan mohon = new Permohonan();
        KodUrusan ku = new KodUrusan();
        mohon = mohonDAO.findById(context.getPermohonan().getIdPermohonan());
        permohonanSebelum = mohon.getPermohonanSebelum();

        if (permohonanSebelum.getKodUrusan().getKod().equals("PPCS")) {
            ku = kodUrusanDAO.findById("PSB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("PPCB")) {
            ku = kodUrusanDAO.findById("PBB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("PYTN")) {
            ku = kodUrusanDAO.findById("CB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("TSKKT")) {
            ku = kodUrusanDAO.findById("TSSKB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("TSBSN")) {
            ku = kodUrusanDAO.findById("TSSKB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("TSPSN")) {
            ku = kodUrusanDAO.findById("TSSKB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("TSKSN")) {
            ku = kodUrusanDAO.findById("TSSKB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("SBMS")) {
            ku = kodUrusanDAO.findById("SBKSB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("PSMT")) {
            ku = kodUrusanDAO.findById("SBTB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("PSBT")) {
            ku = kodUrusanDAO.findById("SBSTB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (permohonanSebelum.getKodUrusan().getKod().equals("TSPSS")) {
            ku = kodUrusanDAO.findById("PSTSB");
            initiateUrusan(ku, context.getPengguna(), permohonanSebelum.getSenaraiHakmilik(), mohon);
            try {
                withdrawUrusanSebelum(permohonanSebelum.getIdPermohonan());
            } catch (StaleObjectException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WorkflowException ex) {
                Logger.getLogger(EndorsanPembatalanValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return proposedOutcome;
    }

    public void initiateUrusan(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan) {
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();

        for (HakmilikPermohonan hp : senaraiHakmilik) {
            listHakmilik.add(hp.getHakmilik());
        }
        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(ku, pengguna, listHakmilik, permohonan);
    }

    public void withdrawUrusanSebelum(String idPermohonan) throws StaleObjectException, WorkflowException {
        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
        if (senaraiTask.isEmpty()) {
        } else {
            Task task = (Task) senaraiTask.get(0);
            if (task != null) {
                String taskId = task.getSystemAttributes().getTaskId();
                WorkFlowService.withdrawTask(taskId);
            }
        }
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
