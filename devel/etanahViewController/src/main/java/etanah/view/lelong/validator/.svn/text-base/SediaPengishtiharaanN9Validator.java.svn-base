package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.Lelongan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author ${user}
 */
public class SediaPengishtiharaanN9Validator implements StageListener {

    @Inject
    LelongService lelongService;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        Permohonan permohonan = sc.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            sc.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            sc.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            sc.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        } 
        
        Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());

        if (enkuiri != null) {
            if (enkuiri.getCaraLelong().equals("B")) {
                if (enkuiri.getPerihalTanah() == null || enkuiri.getPerihalTanahBI() == null) {
                    sc.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Perihal Tanah Di Tab Perisytiharan Jualan");
                    return null;
                }
            }
            if (enkuiri.getCaraLelong().equals("A")) {
                List<Lelongan> listLelongan = lelongService.listLelonganAK(permohonan.getIdPermohonan());
                if (listLelongan.size() != 0) {
                    for (Lelongan ll : listLelongan) {
                        if (ll.getPerihalTanah() == null || ll.getPerihalTanahBahasaInggeris() == null) {
                            sc.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Perihal Tanah Di Tab Perisytiharan Jualan");
                            return null;
                        }
                    }
                }
            }
        }

        return string;
    }

    @Override
    public void afterComplete(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
