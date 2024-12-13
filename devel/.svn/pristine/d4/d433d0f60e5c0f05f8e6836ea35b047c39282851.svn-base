/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mdizzat.mashrom
 */
public class RekodPermohonanBatalValidator implements StageListener {

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
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String idPermohonan = context.getPermohonan().getIdPermohonan();
        Permohonan permohonan = context.getPermohonan();

        if (permohonan.getPermohonanSebelum() == null) {
            context.addMessage(idPermohonan +" - Sila Pilih Senarai Urusan Terlibat Di Tab Senarai Urusan");
            return null;
        }
        
        if (permohonan.getPenyerahNoRujukan() == null) {
            context.addMessage(idPermohonan+" - Sila Masukkan No Ruj Penyerah Di Tab Permohonan : ");
            return null;
        }
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
