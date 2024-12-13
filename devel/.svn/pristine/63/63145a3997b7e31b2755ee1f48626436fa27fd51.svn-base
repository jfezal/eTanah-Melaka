/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.DevIntegrationService;
import etanah.service.NotifikasiService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nursyazwani
 */
public class PembatalanNotification implements StageListener{
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    DevIntegrationService dis;

     @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Pembatalan");
        n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " untuk id permohonan:" + context.getPermohonan().getIdPermohonan() + " telah dibatalkan.");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//        list.add(kodPerananDAO.findById("53"));
//        list.add(kodPerananDAO.findById("9"));
        list.add(kodPerananDAO.findById("10")); //melaka - ptd
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage("Makluman kepada Pentadbir Tanah telah dihantar.");

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();                       
        KodUrusan kod = kodUrusanDAO.findById("TSSKB");
        dis.intRegPermohonan(kod, peng, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context.getStageName());


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
