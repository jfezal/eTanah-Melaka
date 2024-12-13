/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author haqqiem
 */
public class MaklumanNotis implements StageListener {
    private static final Logger LOG = Logger.getLogger(MaklumanNotis.class);

    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    etanah.kodHasilConfig khconf;

    @Override
    public boolean beforeStart(StageContext sc) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String proposedOutcome) {
        LOG.info("beforeComplete:start");
        String result = "";
        Permohonan permohonan = sc.getPermohonan();
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setTajuk("Makluman " + permohonan.getKodUrusan().getNama());
        Pengguna p = sc.getPengguna();
        notifikasi.setCawangan(p.getKodCawangan());

        List<KodPeranan> senaraiPeranan = new ArrayList<KodPeranan>();
        if (permohonan.getKodUrusan().getKod().equals("NT6A")) {
            senaraiPeranan.add(kodPerananDAO.findById("5")); //Pembantu Tadbir Hasil
            notifikasi.setMesej("ID Permohonan " + permohonan.getIdPermohonan() + " untuk urusan " + permohonan.getKodUrusan().getNama() + " telah selesai. Sila masukkan Rekod Penghantaran.");
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        notifikasi.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(notifikasi, p.getKodCawangan(), senaraiPeranan);

//        throw new UnsupportedOperationException("Not supported yet.");
        sc.addMessage(" - Permohonan selesai. Sila masukkan Rekod Penghantaran.");
        LOG.info("beforeComplete:finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
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
