/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

/**
 *
 * @author nordiyana
 */

import etanah.view.pengambilan.validator.*;
import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.PengambilanService;
import etanah.service.PengambilanService1;
import etanah.dao.KodKeputusanDAO;
import etanah.model.*;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nordiyana
 */
public class suspenPBBorangKValidator implements StageListener {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(suspenPBBorangKValidator.class);
    private String idSebelum;
    private Permohonan permohonan;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    @Inject
    etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    
    

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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

            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "01DrafPenarikanBalik");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("8B")) {
                        t.add("ACQSrtPenarikan_NS.rdf");
                        t2.add("8TOL");
                        comm.reportGen(mohon, t, t2);
                    
                } 
            }
        

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("-------Before Complete : ");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
      
        Permohonan psblm;
        FasaPermohonan fasaPermohonan = pengambilanService1.findFasaPermohonan(idPermohonan);
        if (fasaPermohonan.getKeputusan().getKod().equals("8L")) {
            permohonan.getPermohonanSebelum().setStatus("TS");
            lelongService.saveOrUpdate(permohonan);
        }
        if (fasaPermohonan.getKeputusan().getKod().equals("8B")) {
            permohonan.getPermohonanSebelum().setStatus("B");
            lelongService.saveOrUpdate(permohonan);
        }
        proposedOutcome = fasaPermohonan.getKeputusan().getKod();
        return proposedOutcome;
                         
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }
    
    

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
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
