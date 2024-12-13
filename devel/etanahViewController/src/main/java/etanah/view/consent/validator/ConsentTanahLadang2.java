/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.consent.validator;

import etanah.dao.KodPerananDAO;
import etanah.dao.NotifikasiDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;

import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.inject.Inject;

import etanah.service.NotifikasiService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.Notifikasi;

/**
 *
 * @author solahuddin
 */
public class ConsentTanahLadang2 implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    NotifikasiService notifikasiService;
    
    @Inject
    KodPerananDAO kodPerananDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Permohonan Consent");
        n.setMesej("Permohonan "+context.getPermohonan().getKodUrusan().getNama()+" telah dihantar kepada Pembantu Tadbir di PTG untuk tindakan seterusnya");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("14l"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(),list);
        
        context.addMessage("Makluman kepada Timbalan Pentadbir Tanah dan Pentadbir Tanah telah dihantar.");
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
        return "back";
    }
}
