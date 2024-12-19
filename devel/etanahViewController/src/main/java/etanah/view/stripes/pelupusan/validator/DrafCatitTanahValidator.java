/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

/**
 *
 * @author afham
 */

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
//import etanah.service.PelupusanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

public class DrafCatitTanahValidator implements StageListener {

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        //throw new UnsupportedOperationException("Not supported yet.");
    	return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan() ;
        if(permohonan.getSenaraiKertas().isEmpty()){
            context.addMessage("Sila simpan maklumat kertas kandungan");
            return null ;
        }
        return proposedOutcome ;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}
