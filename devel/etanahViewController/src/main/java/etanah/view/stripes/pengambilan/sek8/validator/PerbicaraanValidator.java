/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.ref.pengambilan.sek8a.RefPeringkat;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;

import etanah.service.acq.service.BorangEFService;
import etanah.util.StringUtils;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class PerbicaraanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(PerbicaraanValidator.class);
    @Inject
    BorangEFService borangEFService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        List<BorangEForm> listBorangE = borangEFService.findHakmilikAktifNotTDK(permohonan.getIdPermohonan());
        for (BorangEForm e : listBorangE) {
            List<BorangFForm> listBorangF = borangEFService.listBorangF(permohonan.getIdPermohonan(), e.getHm().getHakmilik().getIdHakmilik());
            for (BorangFForm f : listBorangF) {
                NotaSiasatanLengkap nsl = borangEFService.findNotaSiasatanByPerPB(f.getBppb());
                if (StringUtils.isBlank(nsl.getFlagBicara())) {
                    proposedOutcome = null;
                    context.addMessage("Terdapat Perbicaran yang masih belum dilakukan. Hakmilik :" + e.getHm().getHakmilik().getIdHakmilik());
                    return proposedOutcome;
                }
            }
        }
         RefPeringkat ref = new RefPeringkat();

         sek8aIntegrationFlowService.completeTask(ref.SEDIA_GH, context.getPermohonan(), context.getPengguna());

        
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
