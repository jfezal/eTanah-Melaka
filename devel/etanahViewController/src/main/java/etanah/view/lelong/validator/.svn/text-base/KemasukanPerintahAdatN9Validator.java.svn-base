/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.Kehadiran;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author mazurahayati.yusop
 */
public class KemasukanPerintahAdatN9Validator implements StageListener {

    @Inject
    LelongService lelongService;

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

        Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());

        if (permohonan.getPenyerahNoRujukan() == null) {
            context.addMessage(idPermohonan + " - Sila Masukkan No Ruj Penyerah Di Tab Permohonan");
            return null;
        }

        List<Kehadiran> hadir = lelongService.getListKehadiran(permohonan.getIdPermohonan());
        if (hadir.size() == 0) {
            context.addMessage(idPermohonan + " - Sila Masukkan Maklumat Datuk Lembaga di Tab Maklumat Datuk Lembaga");
            return null;
        }
        return proposedOutcome;
    }

 @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
