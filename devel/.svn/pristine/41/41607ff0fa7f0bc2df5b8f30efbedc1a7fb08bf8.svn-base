/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.service.DevIntegrationService;
import etanah.service.NotifikasiService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;

/**
 *
 * @author faidzal
 */
public class MelakaDEVValidator implements StageListener {

    @Inject
    DevIntegrationService dis;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

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
        KodUrusan kod = new KodUrusan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (context.getPermohonan().getKodUrusan().getKod().equals("TSPSS")) {

            if (context.getStageName().equals("g_penyediaan_b2")) {
                kod = kodUrusanDAO.findById("PSTSL");
                //dis.intRegKelulusan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "16", "T", context);
                dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "6", "T", context.getStageName());
                
                kod = kodUrusanDAO.findById("HSPS");
       
            }
            if (context.getStageName().equals("g_hantar_pa_b1")) {
                kod = kodUrusanDAO.findById("HKPS");
       
            }

        }
        if (context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {
            if (context.getStageName().equals("g_penyediaan_b2")) {
                kod = kodUrusanDAO.findById("SBKSL");
                //dis.intRegKelulusan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "16", "T", context);
                dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "6", "T", context.getStageName());
                
                
                kod = kodUrusanDAO.findById("HSSTB");
             
            }
            if (context.getStageName().equals("g_hantar_pa_b1")) {
                kod = kodUrusanDAO.findById("HKSTB");
              
            }
        }


        //dis.intRegKelulusan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "16", "T", context);
        String devInfo = dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), context.getPermohonan(), "6", "T", context.getStageName());
        
        Notifikasi n = new Notifikasi();

        n.setTajuk("Makluman dan Pengesahan Pengiraan Cukai Bagi Integrasi Modul Pembangunan");
        n.setMesej(devInfo);
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("9"));    
        n.setInfoAudit(infoAudit);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
    }
}
