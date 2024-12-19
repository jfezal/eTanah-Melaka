/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
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
import etanah.service.PembangunanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;

/**
 *
 * @author nursyazwani
 */
public class UlasanPindaanYABNotification implements StageListener {
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PembangunanService devService;
@Inject
SBMSIntegrationFlowService sBMSIntegrationFlowService;
    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(),"perakuanptgyab");
//
//        if(fp!=null && fp.getKeputusan().getKod().equals("TL")){
//            Notifikasi n = new Notifikasi();
//            n.setTajuk("Makluman Pindaan Rencana Pertimbangan Y.A.B Menteri ");
//            n.setMesej("Rencana Pertimbangan Y.A.B Menteri dengan id permohonan :" + context.getPermohonan().getIdPermohonan() + " telah dihantar ke PTD semula untuk pindaan. Ulasan pindaan: " + fp.getUlasan());
//            Pengguna p = context.getPengguna();
//            n.setCawangan(p.getKodCawangan());
//            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//    //        list.add(kodPerananDAO.findById("53"));
//    //        list.add(kodPerananDAO.findById("9"));
//            list.add(kodPerananDAO.findById("95")); //melaka - PT Kanan PTG
//            InfoAudit ia = new InfoAudit();
//            ia.setDimasukOleh(p);
//            ia.setTarikhMasuk(new Date());
//            n.setInfoAudit(ia);
//            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
//            context.addMessage("Makluman kepada Pembantu Tadbir Kanan PTG (Pembangunan) telah dihantar.");
//            proposedOutcome = "TL";
//        }
//        else if(fp!=null && fp.getKeputusan().getKod().equals("LK")){
//            sBMSIntegrationFlowService.insertTugasanTable(context.getPermohonan(), "REYAB", context.getPengguna().getIdPengguna());
//            
//            context.addMessage("Permohonan Lengkap. Tiada Pindaan.");
//            
//        }
sBMSIntegrationFlowService.initiateKeputusanRencanaMenteri(context.getPermohonan());
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
