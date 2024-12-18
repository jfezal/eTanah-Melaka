/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rajib
 */
public class RegDoneHSValidator implements StageListener {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RegDoneHSValidator.class);
    @Inject
    PembangunanService devService;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String proposedOutcome) {
        LOG.info(" ******** proposed Outcome***********");
        String returnResult = proposedOutcome;
        Permohonan permohonan = sc.getPermohonan();
        List<Permohonan> senaraiMohon = new ArrayList<Permohonan>();
        senaraiMohon = devService.getListPermohonanByIdSebelumAndKodUrusan(permohonan.getIdPermohonan(),devService.cariKodUrusanHSPendaftaran(permohonan.getKodUrusan().getKod()));
        if(senaraiMohon.size()==1){
            for(Permohonan p:senaraiMohon){
                if(p.getStatus().equalsIgnoreCase("SL")&&p.getKeputusan().getKod().equalsIgnoreCase("D")){
                    returnResult = proposedOutcome;
                    LOG.info(" ******** proposed Outcome1***********:" + proposedOutcome);
                }else{
                    returnResult = null;
                    LOG.info(" ******** x");
                }


            }
        }else{
            returnResult = null;
            LOG.info(" ******** y");
        }
        
        return returnResult;
        //return null;
    }

    @Override
    public void afterComplete(StageContext sc) {
    //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String proposedOutcome) {
        return proposedOutcome;
        //    throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
