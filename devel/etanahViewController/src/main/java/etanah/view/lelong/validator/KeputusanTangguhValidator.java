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
 * @author mdizzat.mashrom
 */
public class KeputusanTangguhValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KeputusanTangguhValidator.class);
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
        Permohonan permohonan = context.getPermohonan();

        //FasaPermohonan fasa = lelongService.findFasaPermohonanTangguh(permohonan.getIdPermohonan());
         List<FasaPermohonan> mohonFasaList = lelongService.findFasaPermohonanTangguhList(permohonan.getIdPermohonan());
            FasaPermohonan fasa = null;
            if(mohonFasaList.size() > 0){
                 fasa = mohonFasaList.get(0);
            }
        if (fasa != null) {
            if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("L")) {  
                List<Lelongan> listLelong = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (listLelong.isEmpty()) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                    return null;
                } else {
                    for (Lelongan ll : listLelong) {
                        if (ll.getTarikhLelong() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                            return null;
                        }
                    }
                }

//                FasaPermohonan fasa2 = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
//                if (fasa2 == null) {
//                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Di Tab Maklumat Jurulelong");
//                    return null;
//                } else {
//                    if (fasa2.getKeputusan() != null && fasa2.getKeputusan().getKod().equals("JL")) {
//                        for (Lelongan ll : listLelong) {
//                            if (ll.getJurulelong() == null) {
//                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Di Tab Maklumat Jurulelong");
//                                return null;
//                            }
//                        }
//                    }
                    
                    // edited on 01/11/2012 - melaka xd NL
                    
////                    if ("05".equals(conf.getProperty("kodNegeri"))) {
//                        if (fasa2.getKeputusan() != null && fasa2.getKeputusan().getKod().equals("PH")) {
//                            List<KandunganFolder> listKD = lelongService.getListALLDokumen(permohonan.getFolderDokumen().getFolderId());
//                            String notis = "";
//                            for (KandunganFolder kf : listKD) {
//                                if (kf.getDokumen().getKodDokumen().getKod().equals("NL")) {
//                                    notis = "NL";
//                                    break;
//                                }
//                            }
//                            if (!notis.equals("NL")) {
//                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Notis Lelongan di Tab Maklumat Jurulelong");
//                                return null;
//                            }
//                        }
////                    }
//                }
            }
            if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("T")) {
                proposedOutcome = fasa.getKeputusan().getKod();
            }
        }
        context.setNoRujukan(context.getPermohonan().getIdPermohonan());
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
