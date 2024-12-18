/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8a.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.common.IntegrasiPendaftaranService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;

/**
 *
 * @author mohd.faidzal
 */
public class KemukaBorangKValidator implements StageListener {

    @Inject
    IntegrasiPendaftaranService integrasiPendaftaranService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    @Inject
    KodUrusanDAO kodUrusanDAO;

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
            Permohonan permohonan = context.getPermohonan();
            KodUrusan kod = kodUrusanDAO.findById("ABT-K");
            integrasiPendaftaranService.setPengguna(context.getPengguna());
            Permohonan mohonBaru = integrasiPendaftaranService.initiate(permohonan, kod, context.getStageName());
            integrasiPendaftaranService.hantarNotifikasi("Kemasukan " + mohonBaru.getKodUrusan().getNama() + " :" + mohonBaru.getIdPermohonan(), "Urusan permohonan dari Unit Pembangunan untuk Id Permohonan :" + permohonan.getIdPermohonan() + " ke unit pendaftaran pada perserahan :" + mohonBaru.getIdPermohonan(), mohonBaru.getCawangan(), "ptptgregistration");
            etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();
            sek8aIntegrationFlowService.completeTask(ref8a.KEMUKA_BORANG_K, context.getPermohonan(), context.getPengguna());
            return proposedOutcome;
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(KemukaBorangKValidator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(KemukaBorangKValidator.class.getName()).log(Level.SEVERE, null, ex);
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
