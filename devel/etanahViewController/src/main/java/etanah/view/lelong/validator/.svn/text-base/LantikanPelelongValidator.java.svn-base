/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.Lelongan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Mazurahayati
 */
public class LantikanPelelongValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(LantikanPelelongValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    etanah.Configuration conf;

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

        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();

        FasaPermohonan fasa = lelongService.findlantikanPelelong(permohonan.getIdPermohonan());

        List<Lelongan> ll = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
        if (fasa.getKeputusan().getKod().equals("PH") || fasa.getKeputusan().getKod().equals("JL")) {
            if (ll.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Tetapkan Tarikh Lelongan Di Tab Maklumat Lelongan");
                return null;
            } else {
                for (Lelongan lel : ll) {
                    if (lel.getTarikhLelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Lelongan");
                        return null;
                    }
                }
            }
        }

        if (fasa.getKeputusan() == null) {
            context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Di Tab Maklumat Keputusan");
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
