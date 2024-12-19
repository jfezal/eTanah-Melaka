/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.DevIntegrationService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import org.hibernate.Session;
/**
 *
 * @author nursyazwani
 */
public class PostChartingSerahSemuaNotification implements StageListener {
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    DevIntegrationService dis;
    
    private static final Logger LOG = Logger.getLogger(ChartingNotification.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        if(context.getPermohonan().getKeputusan().getKod().equals("T")){
             Permohonan permohonan = context.getPermohonan();
             Pengguna peng = (Pengguna) context.getPengguna();                       
             KodUrusan kod = new KodUrusan();
             if(permohonan.getKodUrusan().getKod().equals("PSMT")){
                 kod = kodUrusanDAO.findById("SBTB");
             }
             if(permohonan.getKodUrusan().getKod().equals("PSBT")){
                 kod = kodUrusanDAO.findById("SBSTB");
             }
             dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), permohonan, "6", "F", context.getStageName());
            
        }        
        return proposedOutcome; 
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
