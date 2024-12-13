/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author khairul.hazwan
 */
public class notaValidator implements StageListener {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(notaValidator.class);
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    PembangunanService devService;
    
    private String stage;
    
    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {       
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Pengguna p = context.getPengguna();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        
        Permohonan permohonan = context.getPermohonan();
        if(context.getStageName().equalsIgnoreCase("semakpelanringkasan")){
            carianMohonFasa("semakpelanringkasan",permohonan,p,ia);
                                              
        }else if(context.getStageName().equalsIgnoreCase("semakpelanringkasantptg")){
            carianMohonFasa("semakpelanringkasantptg",permohonan,p,ia); 
            
        }else if(context.getStageName().equalsIgnoreCase("perakuanptg")){
            carianMohonFasa("perakuanptg",permohonan,p,ia);                 
        }
        
    return proposedOutcome; 
//    return null;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    private void saveToNotaMohon(InfoAudit ia, String idAliran, Permohonan permohonan, KodCawangan kodCawangan, String ulasan) {
        
        PermohonanNota nota = new PermohonanNota();
        nota.setCawangan(kodCawangan);
        nota.setIdAliran(idAliran);
        nota.setPermohonan(permohonan);
        nota.setNota(ulasan);
        nota.setInfoAudit(ia);
        nota.setStatusNota('A');
        //permohonanNotaDAO.save(nota);
        devService.simpanNota(nota);
        LOG.info(" ******** outcome condition***********:"+kodCawangan+idAliran+permohonan+ulasan+ia);
    }
    
    private void carianMohonFasa(String stage, Permohonan permohonan,Pengguna p,InfoAudit ia){
                      String[] tname = {"permohonan"};
                      Object[] model = {permohonan};

                        FasaPermohonan fp =  new FasaPermohonan();
                        List<FasaPermohonan> fplist;
                        fplist = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
                        System.out.println("fplist: " + fplist.size());
                        
                         if (!(fplist.isEmpty())) {
                            for (int i = 0; i < fplist.size(); i++) {
                            FasaPermohonan fasapermohonan = new FasaPermohonan();
                            fasapermohonan = fplist.get(i);
                            if(fasapermohonan.getIdAliran().equalsIgnoreCase(stage)){
                                //saveToNotaMohon(ia,fasapermohonan.getIdAliran(),permohonan,p.getKodCawangan(),fasapermohonan.getUlasan()); 
                                saveToNotaMohon(ia,stage,permohonan,p.getKodCawangan(),fasapermohonan.getUlasan()); 
                            }                
                            }
                         }       
    }
    
}
