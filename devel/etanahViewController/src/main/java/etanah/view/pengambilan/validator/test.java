/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
public class test implements StageListener{
    private static final Logger LOG = Logger.getLogger(test.class);
    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    String idSblm;
    @Inject
    StrataPtService strService;

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

 
    
    
    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();


        if ((kodNegeri.equals("05")) && (mohon.getKodUrusan().getKod().equals("PB"))) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "01DrafPenarikanBalik");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("8B")) {

                    t.add("ACQSrtPenarikan_NS.rdf");
                    t2.add("8TOL");
                    comm.reportGen(mohon, t, t2);

                } 
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        Permohonan pSblm=permohonan.getPermohonanSebelum();
        idSblm=pSblm.getIdPermohonan();
        LOG.info("Id Permohonan Sebelum : "+idSblm);
        
        FasaPermohonan fasa = lelongService.findFasaPermohonanPB(permohonan.getIdPermohonan());
        if (fasa.getKeputusan().getKod().equals("8L")) {
            pSblm.setStatus("TS");
            lelongService.saveOrUpdate(pSblm);
        }
        proposedOutcome = fasa.getKeputusan().getKod();
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        LOG.info("---afterComplete---");
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
