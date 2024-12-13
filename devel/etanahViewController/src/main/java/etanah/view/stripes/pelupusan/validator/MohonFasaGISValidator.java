/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanFasaGisDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanFasaGis;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import etanah.view.stripes.pelupusan.initiateService.InitiateTaskService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author Shazwan
 * 
 */
public class MohonFasaGISValidator implements StageListener {

    @Inject
    PelupusanService plpservice;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    InitiateTaskService its;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanFasaGisDAO permohonanFasaGisDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    private static final Logger LOG = Logger.getLogger(CatitTanahValidator.class);
    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
       return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
       
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome ;
    }

    @Override
    public void afterComplete(StageContext context) {
       
    }
     @Override
    public void afterPushBack(StageContext context) {
       
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan() ;
        Pengguna peng = (Pengguna) context.getPengguna();
        int numUrusan = permohonan.getKodUrusan().getKod().equals("PHLP")?1:
                        permohonan.getKodUrusan().getKod().equals("MMMCL")?2:
                        permohonan.getKodUrusan().getKod().equals("PPTR")?3:
                        permohonan.getKodUrusan().getKod().equals("PBMT")?4:
                        permohonan.getKodUrusan().getKod().equals("PPRU")?5:
                        permohonan.getKodUrusan().getKod().equals("LMCRG")?6:
                        permohonan.getKodUrusan().getKod().equals("PLPS")?7:
                        permohonan.getKodUrusan().getKod().equals("PTGSA")?8:
                        0;
        KodUrusan kod = new KodUrusan();
        InfoAudit infoAudit = new InfoAudit();
        /*
         * ChartingStageName refer to Stage before PushBack(GIS Stage)
         */
        String chartingStageName = new String();
        switch(numUrusan){
            case 1: if(context.getStageName().equals("04agihan_tugas")){
                        chartingStageName ="g_charting_permohonan";                        
                    }                     
                    break;
            case 2: 
                    if(context.getStageName().equals("semak_charting")){
                        chartingStageName ="g_charting_permohonan";                        
                    }   
                    break;
            case 3: 
                    if(context.getStageName().equals("agihan_tugas2")){
                        chartingStageName ="g_charting_permohonan";                        
                    }   
                    break;
            case 4: 
                    if(context.getStageName().equals("semak_charting")){
                        chartingStageName ="g_charting_permohonan";                        
                    }  
             case 5: 
                    if(context.getStageName().equals("semak_charting")){
                        chartingStageName ="g_charting_permohonan";                        
                    }
           case 6: 
                    if(context.getStageName().equals("agihan_tugas")){
                        chartingStageName ="g_charting_permohonan";                        
                    }
           case 7: 
                    if(context.getStageName().equals("semak_charting")){
                        chartingStageName ="g_charting_permohonan";                        
                    }
                    break;
           case 8: 
                    if(context.getStageName().equals("03ArahLaporanTanah")){
                        chartingStageName ="g_charting_pemohonan";                        
                    }
                    break;
        }
        if(!StringUtils.isBlank(chartingStageName)){            
            PermohonanFasaGis mohonFasaGIS = new PermohonanFasaGis();
            mohonFasaGIS = pelupusanService.findMohonFasaGISByIdMohon(permohonan.getIdPermohonan());
            if(mohonFasaGIS!=null){
                infoAudit = mohonFasaGIS.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonFasaGIS.setInfoAudit(infoAudit);
            }else{
                mohonFasaGIS = new PermohonanFasaGis();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            mohonFasaGIS.setInfoAudit(infoAudit);
            mohonFasaGIS.setId_mohon(permohonan.getIdPermohonan());
            mohonFasaGIS.setIdAliran(chartingStageName);
            pelupusanService.simpanFasaPermohonanGIS(mohonFasaGIS);
        }
        return proposedOutcome;
    }
}
