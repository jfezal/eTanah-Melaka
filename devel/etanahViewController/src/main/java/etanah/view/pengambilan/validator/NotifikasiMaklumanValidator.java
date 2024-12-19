/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import java.util.ArrayList;
import java.util.Date;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author massita
 */
public class NotifikasiMaklumanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(NotifikasiMaklumanValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    private static final String kodPTD = "33";
    private static final String kodKPT = "15";
    private static final String kodPT = "35";
    
    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
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

            Permohonan permohonan = context.getPermohonan();
            String stageId = context.getStageName();            
           
            // send notification
            Notifikasi n = new Notifikasi();            
            Pengguna p = context.getPengguna();
            
            InfoAudit ia = new InfoAudit();            
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            
            n.setInfoAudit(ia);
            n.setCawangan(p.getKodCawangan());
            n.setTajuk("Makluman dan Arahan Bina Surat Jabatan Teknikal");
            
            int urusan = permohonan.getKodUrusan().getKod().equals("PHLLP")?1:0;
            switch(urusan){
                case 1 : n.setMesej(context.getPermohonan().getIdPermohonan()+" - " +permohonan.getKodUrusan().getNama()+" telah di" + context.getPermohonan().getKeputusan().getNama()+" untuk tindakan selanjutnya.");
                         //send notifikasi
                         if(stageId.equals("08SemakanArahan")){
                         sendNotifikasi(n,p,kodPT);    
                         sendNotifikasi(n,p,kodKPT); 
                         sendNotifikasi(n,p,kodPTD);
                         }
                         break;
            }
        context.addMessage(" : Makluman dan arahan bina surat ulasan Jabatan Teknikal Telah Dihantar.");
        return proposedOutcome;
    }

    public void sendNotifikasi(Notifikasi n, Pengguna p,String kodPeranan){
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById(kodPeranan));
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
    }            
            
     @Override
    public void afterComplete(StageContext sc) {
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

