/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.KandunganFolder;
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
public class Sedai16HN9Validator implements StageListener {

 private static final Logger LOG = Logger.getLogger(Sedai16HN9Validator.class);
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
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonanla = context.getPermohonan();
        String idPermohonan = permohonanla.getIdPermohonan();
        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(idPermohonan);
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----List not empty----");
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        } 
        
        List<KandunganFolder> listKandunganFolder = lelongService.getListALLDokumen(permohonanla.getFolderDokumen().getFolderId());
        LOG.info("listKandunganFolder : " +listKandunganFolder.size());
        String h16 = "";
        for (KandunganFolder kf : listKandunganFolder) {
            if(kf.getDokumen().getKodDokumen().getKod().equals("16H")&& kf.getDokumen().getNamaFizikal() != null){
                h16 = "16H";
            }
        }
        LOG.info("h16 : " +h16);

        if(!h16.equals("16H")){
            LOG.info("---16H-Error---");
            context.addMessage(idPermohonan + " - Sila Jana Borang 16H Penyerah Di Tab Borang 16H Penyerah");
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
