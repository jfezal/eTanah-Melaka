/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

/**
 *
 * @author khairul.hazwan
 */

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import java.util.ArrayList;
import java.util.Date;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import org.apache.log4j.Logger;


public class PPKValidator implements StageListener {
    private static final Logger LOG = Logger.getLogger(PPKValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PembangunanService devService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        KodCawangan kc = new KodCawangan();        
        KodCawangan kc1 = new KodCawangan();   
        Notifikasi n = new Notifikasi();
        
        Pengguna p = context.getPengguna();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
               
        n.setTajuk("Makluman Penentuan Pelan Kuota Telah Siap Diproses");
        n.setMesej("Makluman Penentuan Pelan Kuota Telah Siap Diproses");
        n.setInfoAudit(ia);
                      
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        //ArrayList<KodPeranan> listHm = new ArrayList<KodPeranan>();
        Permohonan mohon = context.getPermohonan();
        ArrayList<HakmilikPermohonan> list1 = new ArrayList<HakmilikPermohonan>(mohon.getSenaraiHakmilik());
        if (list1.size()>0){
        
            HakmilikPermohonan hp = list1.get(0);
            kc = hp.getHakmilik().getCawangan();
            kc1 = kodCawanganDAO.findById(hp.getHakmilik().getDaerah().getKod());
                    
        }         

        //ptd
        if(!kc1.getKod().equals("00")){
        list.add(kodPerananDAO.findById("77"));   
        n.setCawangan(kc1);
        notifikasiService.addRolesToNotifikasi(n, kc1, list);
        }
        
        //hakmilik       
        list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("4"));
        n.setCawangan(kc);
        notifikasiService.addRolesToNotifikasi(n, kc, list);
        
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
