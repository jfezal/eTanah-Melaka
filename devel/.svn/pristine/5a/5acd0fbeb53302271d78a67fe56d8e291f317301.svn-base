/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
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
@author mazurahayati.yusop
 */
public class Semak16HValidatorN9 implements StageListener {

    private static final Logger LOG = Logger.getLogger(Semak16HValidator.class);
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
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh 160/Pembatalan 160 Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(permohonan.getIdPermohonan());
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(permohonan.getIdPermohonan());
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
                
        FasaPermohonan fasa = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
        proposedOutcome = fasa.getKeputusan().getKod();
        if (fasa.getKeputusan().getKod().equals("JL")) {
            List<Lelongan> listLel = lelongService.listLelongan(permohonan.getIdPermohonan());
            for (Lelongan lel : listLel) {
                if (lel.getSytJuruLelong()== null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Berlesen Di Tab Maklumat Keputusan");
                    return null;
                }
            }
        }

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        if (fasa.getKeputusan().getKod().equals("PH")) {
            String notis = "";
            for (KandunganFolder kf : listKD) {
                if (kf.getDokumen().getKodDokumen().getKod().equals("NL")) {
                    notis = "NL";
                    break;
                }
            }
            if (!notis.equals("NL")) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Notis Lelongan di Tab Maklumat Keputusan");
                return null;
            }

        } else {
            if (fasa.getKeputusan().getKod().equals("JL")) {
                String notis = "";
                String lantik = "";
                for (KandunganFolder kf : listKD) {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("NL") || (kf.getDokumen().getKodDokumen().getKod().equals("LEL"))) {
                        notis = "NL";
                        lantik = "LEL";
                    }
                }
                if (!notis.equals("NL") || (!lantik.equals("LEL"))) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Notis Lelongan dan Surat Lantikan Jurulelong di Tab Maklumat Keputusan");
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
