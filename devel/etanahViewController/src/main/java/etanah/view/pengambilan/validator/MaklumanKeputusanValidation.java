/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;

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
 * @author abu.mansur
 * @modify by massita
 */
public class MaklumanKeputusanValidation implements StageListener {
    private static final Logger LOG = Logger.getLogger(MaklumanKeputusanValidation.class);

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
        throw new UnsupportedOperationException("Not supported yet.");
//        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
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
         if(proposedOutcome.equals("KL")){
             result = "Diluluskan .";
         }else{
             result = "Ditolak .";
         }
         List<KodPeranan> senaraiPeranan = new ArrayList<KodPeranan>();
         if(permohonan.getKodUrusan().getKod().equals("PTSP")){
             senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("ptg"))); //Pengarah Tanah dan Galian
             senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("penolongPtg"))); //Penolong Pengarah Dan Galian (PTG)
             senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("ptg1"))); //Pengarah Tanah dan Galian-opis
             senaraiPeranan.add(kodPerananDAO.findById(khconf.getProperty("penolongPtg1"))); //Penolong Pengarah Dan Galian (PTG)-opis
             notifikasi.setMesej("ID Permohonan "+permohonan.getIdPermohonan()+" untuk urusan "+permohonan.getKodUrusan().getNama()+" telah "+result);
         }

         InfoAudit ia = new InfoAudit();
         ia.setDimasukOleh(p);
         ia.setTarikhMasuk(new Date());
         notifikasi.setInfoAudit(ia);
//         if(senaraiPeranan.size() > 0 && !permohonan.getKodUrusan().getKod().equals("PTSP"))
            notifikasiService.addRolesToNotifikasi(notifikasi, p.getKodCawangan(), senaraiPeranan);
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
