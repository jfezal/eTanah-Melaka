/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author mdizzat.mashrom
 */
public class KemasukanPerintahValidator implements StageListener {

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
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
//            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
//            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(idPermohonan);
//        if(!listPermohonan.isEmpty()){
//            context.addMessage( idPermohonan + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        if (permohonan.getPenyerahNoRujukan() == null) {
            context.addMessage( idPermohonan + " - Sila Masukkan No Ruj Penyerah Di Tab Permohonan");
            return null;
        }
//        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
//            if (pp.getNomborRujukan() == null) {
//                context.addMessage( idPermohonan + " - Sila Pilih No Perserahan Gadaian Di Tab Maklumat Tanah/Pihak Berkepentingan");
//                return null;
//            }
//        }
//
//        List<PermohonanPihak> listPermohonanPihak = lelongService.getSenaraiPmohonPihak(idPermohonan);
//
//        if (listPermohonanPihak.size() == 0) {
//            context.addMessage( idPermohonan + " - Sila Pilih Penggadai Di Tab Maklumat Tanah/Pihak Berkepentingan");
//            return null;
//        }

        List<Enkuiri> listEnkuiri = lelongService.getEnkuiri(idPermohonan);

        if (listEnkuiri.size() == 0) {
            context.addMessage(idPermohonan + " - Sila Masukkan Maklumat Enkuiri Di Tab Siasatan Yang Akan Dijalankan");
            return null;
        } else {
            for (Enkuiri enkuiri : listEnkuiri) {

                if (enkuiri.getStatus() == null) {
                    context.addMessage(idPermohonan + " - Sila Masukkan Maklumat Enkuiri Di Tab Siasatan Yang Akan Dijalankan");
                    return null;
                }
                if(enkuiri.getTarikhEnkuiri() == null){
                    context.addMessage(idPermohonan + " - Sila Masukkan Tarikh Enkuiri Di Tab Siasatan Yang Akan Dijalankan");
                    return null;
                }
                if(enkuiri.getTempat() == null){
                    context.addMessage(idPermohonan + "Sila Masukkan Tempat Enkuiri Di Tab Siasatan Yang Akan Dijalankan");
                    return null;
                }
            }
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