/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.validator;
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

/**
 *
 * @author sitifariza.hanim
 */
public class NotifikasiSOKanan implements StageListener {
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

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
        if(context.getPermohonan().getKodUrusan().getKod().equals("49")){
            System.out.println("masuk 49");
            n.setTajuk("Makluman Aduan Dari PPTD ke Penolong Pegawai Tanah Kanan");
            n.setMesej("Makluman Aduan dari PPTD untuk id permohonan:" + context.getPermohonan().getIdPermohonan() + " telah dihantar kepada Penolong Pegawai Tanah Kanan (Penguatkuasaan) untuk tindakan selanjutnya.");
        }
        else {
            n.setTajuk("Makluman Aduan Dari PPTD");
            n.setMesej("Makluman Aduan dari Pembantu Tadbir Penguatkuasa untuk id permohonan:" + context.getPermohonan().getIdPermohonan() + " telah dihantar kepada Penolong Pegawai Tanah Kanan (Penguatkuasaan) untuk tindakan selanjutnya.");
        }
        System.out.println("notify");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//        list.add(kodPerananDAO.findById("53"));
//        list.add(kodPerananDAO.findById("9"));
        list.add(kodPerananDAO.findById("PPTK")); //N9 - SOKanan
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage(" Makluman kepada Penolong Pegawai Tanah Kanan telah dihantar.");
        System.out.println("notify 2");
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
