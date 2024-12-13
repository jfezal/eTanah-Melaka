/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.b1b2.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.DevIntegrationService;
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
public class JanaHakmilikB2SBMSValidator implements StageListener {

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
        try {
            KodUrusan kod = kodUrusanDAO.findById("SBKSL");
            dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "6", "T", context.getStageName());
            
            if(!hakmilikIntegrationService.findListHakmilikLanjutTempoh(context.getPermohonan().getIdPermohonan()).isEmpty()){
                        KodUrusan kodLanjut = kodUrusanDAO.findById("PSPL");
                                    dis.intRegPermohonan(kodLanjut, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(hakmilikIntegrationService.findListHakmilikLanjutTempoh(context.getPermohonan().getIdPermohonan())), context.getPermohonan(), "6", "T", context.getStageName());


            }
            
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(context.getPengguna());
            ia.setTarikhMasuk(new Date());
            KodUrusan kodQT = kodUrusanDAO.findById("HSSTB");
            List<PermohonanPlotPelan> l = hakmilikIntegrationService.findIdPlotByIdPermohonan(context.getPermohonan().getIdPermohonan());
            if (!l.isEmpty()) {
                RekodHakmilikBaru rekod = hakmilikIntegrationService.findRekodHakmilikBaruSementaraByIdPermohonan(l.get(0).getPermohonanPlotKpsn().getIdPlotKpsn());
                String idPermohonanPendaftaran = hakmilikIntegrationService.janaHakmilik(rekod, context.getPermohonan().getIdPermohonan(), initiatePermohonanService.generateIdPerserahan(kodQT, rekod.getCawangan(), context.getPengguna(), context.getPermohonan(), context.getStageName()), ia);
                WorkFlowService.initiateTask(kodQT.getIdWorkflowIntegration(),
                        idPermohonanPendaftaran, rekod.getCawangan().getKod(), context.getPengguna().getIdPengguna(),
                        kodQT.getNama());
                return proposedOutcome;
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(JanaHakmilikB2SBMSValidator.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
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
