/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class PenguranganDendaValidation implements StageListener{

    @Inject
    PemohonDAO pemohonDAO;
    private static final Logger LOG = Logger.getLogger(PenguranganDendaValidation.class);

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        List<Pemohon> senaraiPemohon = new ArrayList();
        String[] name = {"permohonan"};
        Object[] value= {permohonan};
        senaraiPemohon = pemohonDAO.findByEqualCriterias(name, value, null);
        LOG.debug("senaraiPemohon.size :"+senaraiPemohon.size());
        if(permohonan.getKodUrusan().equals("PPDL")){
            if(permohonan.getSebab() == null && senaraiPemohon.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Lain dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(permohonan.getSebab() == null){
                context.addMessage("Sila masukkan maklumat pada Maklumat Lain : "+permohonan.getIdPermohonan());
                return null;
            }
        }
        if(senaraiPemohon.isEmpty()){
            context.addMessage("Sila masukkan maklumat pada Tuan Tanah : "+permohonan.getIdPermohonan());
            return null;
        }
        LOG.info("beforeComplete::Finish");
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
//        return proposedOutcome;
        return "back";
    }

}
