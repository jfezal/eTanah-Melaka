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
import etanah.model.FasaPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author nursyazwani
 */
public class PPTDKeputusanNotification implements StageListener{
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

    private String keputusan;

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

//        FasaPermohonan fasaMohon = new FasaPermohonan();
//        List<FasaPermohonan> fmlist = context.getPermohonan().getSenaraiFasa();
//
//        for(int i = 0; i < fmlist.size(); i++){
//            fasaMohon = fmlist.get(i);
//            if(fasaMohon.getIdAliran().equals("sediajkbbrekodkeputusan")){
//                keputusan = fasaMohon.getKeputusan().getNama();
//            }
//        }
        keputusan = context.getPermohonan().getKeputusan().getNama();
        
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan JKBB");
        n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah mendapat keputusan " + keputusan + " daripada Jawatankuasa Belah Bahagi Tanah");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//        list.add(kodPerananDAO.findById("9"));
        list.add(kodPerananDAO.findById("75"));
        list.add(kodPerananDAO.findById("71"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage("Makluman kepada Penolong Pentadbir/Pembantu Tadbir Tertinggi telah dihantar.");
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
