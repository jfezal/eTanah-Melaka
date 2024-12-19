
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import java.util.ArrayList;
import java.util.Date;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class MaklumanPushBackValidation implements StageListener {

    private static final Logger LOG = Logger.getLogger(MaklumanPushBackValidation.class);
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
        LOG.info(":::::::::: Start Makluman Push Back::::::::::");
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setTajuk("Makluman Semak Semula " + context.getPermohonan().getKodUrusan().getNama());

        Pengguna p = context.getPengguna();
        notifikasi.setCawangan(context.getPermohonan().getCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();

        if (context.getStageName().equals("Stage2")) {
            list.add(kodPerananDAO.findById("14")); //Peranan Timbalan Pentadbir Tanah
            notifikasi.setMesej("ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ". " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar semula kepada Pembantu Tadbir.");
        } else {
            list.add(kodPerananDAO.findById("9")); //Peranan Penolong Pentadbir Tanah
            list.add(kodPerananDAO.findById("14")); //Peranan Timbalan Pentadbir Tanah
            list.add(kodPerananDAO.findById("10")); //Peranan Pentadbir Tanah
            notifikasi.setMesej("ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ". " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar semula.");

        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        notifikasi.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(notifikasi, context.getPermohonan().getCawangan(), list);
        LOG.info(":::::::::: Finish Makluman Push Back ::::::::::");

    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
