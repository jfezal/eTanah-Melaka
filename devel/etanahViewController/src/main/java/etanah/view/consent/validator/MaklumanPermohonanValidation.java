
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
public class MaklumanPermohonanValidation implements StageListener {

    private static final Logger LOG = Logger.getLogger(MaklumanPermohonanValidation.class);
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
        LOG.info(":::::::::: Start Makluman ::::::::::");
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setTajuk("Makluman " + context.getPermohonan().getKodUrusan().getNama());

        Pengguna p = context.getPengguna();
        notifikasi.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();

        //makluman kepada PT consent PTD urusan kelulusan MB & MMK telah diselesaikan di peringkat PTG - komen simulasi NS 31 Okt 2014
        if ((context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/MMK") && context.getStageName().equals("Stage9"))
                || (context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/MB") && context.getStageName().equals("Stage8"))) {
            list.add(kodPerananDAO.findById("9")); //Peranan Penolong Pentadbir Tanah
            list.add(kodPerananDAO.findById("14")); //Peranan Timbalan Pentadbir Tanah
            //notifikasi.setMesej("ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ". " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah.");
            notifikasi.setMesej(context.getPermohonan().getKodUrusan().getNama()+" : ID Permohonan : " + context.getPermohonan().getIdPermohonan()  + " telah diselesaikan di PTG.");
        }
        
        if (context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/PTD")
                || context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/MMK")
                || context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/MB")
                || context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/JKTL1")) {

            list.add(kodPerananDAO.findById("9")); //Peranan Penolong Pentadbir Tanah
            list.add(kodPerananDAO.findById("14")); //Peranan Timbalan Pentadbir Tanah
            notifikasi.setMesej("ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ". " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah.");

        } else if (context.getPermohonan().getKodUrusan().getIdWorkflow().endsWith("Consent/JKTL2")) {

            list.add(kodPerananDAO.findById("9")); //Peranan Penolong Pentadbir Tanah
            list.add(kodPerananDAO.findById("14")); //Peranan Timbalan Pentadbir Tanah
            list.add(kodPerananDAO.findById("10")); //Peranan Pentadbir Tanah
            notifikasi.setMesej("ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ". " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pembantu Tadbir Consent PTG.");

        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        notifikasi.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(notifikasi, p.getKodCawangan(), list);
        LOG.info(":::::::::: Finish Makluman ::::::::::");
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
