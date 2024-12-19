/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.b1b2.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.DevIntegrationService;
import etanah.service.common.PelanB1Service;
import etanah.service.dev.integrationflow.HakmilikIntegrationService;
import etanah.service.dev.integrationflow.InitiatePermohonanService;
import etanah.service.dev.integrationflow.RekodHakmilikBaru;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class JanaHakmilikB1SBMSValidator implements StageListener {

    @Inject
    PelanB1Service pelanB1Service;
    @Inject
    HakmilikIntegrationService hakmilikIntegrationService;
    @Inject
    DevIntegrationService dis;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    InitiatePermohonanService initiatePermohonanService;

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
        KodUrusan kodQT = kodUrusanDAO.findById("HKSTB");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(context.getPengguna());
        ia.setTarikhMasuk(new Date());
        List<PermohonanPlotPelan> l = hakmilikIntegrationService.findIdPlotByIdPermohonan(context.getPermohonan().getIdPermohonan());
        if (!l.isEmpty()) {
            RekodHakmilikBaru rekod = hakmilikIntegrationService.findRekodHakmilikBaruKekalByIdPermohonan(l.get(0).getPermohonanPlotKpsn().getIdPlotKpsn());
            List<Hakmilik> hak = pelanB1Service.findListQT(context.getPermohonan().getIdPermohonan());
            for (Hakmilik ha : hak) {
                try {
                    String idPermohonanPendaftaran = hakmilikIntegrationService.janaHakmilikKekal(rekod, context.getPermohonan().getIdPermohonan(), initiatePermohonanService.generateIdPerserahan(kodQT, rekod.getCawangan(), context.getPengguna(), context.getPermohonan(), context.getStageName()), ha.getIdHakmilik(),ia);

                    WorkFlowService.initiateTask(kodQT.getIdWorkflowIntegration(),
                            idPermohonanPendaftaran, rekod.getCawangan().getKod(), context.getPengguna().getIdPengguna(),
                            kodQT.getNama());
                } catch (Exception ex) {
                    Logger.getLogger(JanaHakmilikB1SBMSValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

}
