/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.EnforceService;
import etanah.service.NotifikasiService;
import etanah.service.common.EnforcementService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sitifariza.hanim
 */
public class NotifikasiPTG implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;

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
        List<HakmilikPermohonan> senaraiHakmilik = context.getPermohonan().getSenaraiHakmilik();
        String stageId = context.getStageName();

        if (!senaraiHakmilik.isEmpty()) {
            if (context.getPermohonan().getKodUrusan().getKod().equals("127")) {
                System.out.println("masuk 127");

                HakmilikPermohonan hm = senaraiHakmilik.get(0);
                FasaPermohonan fp = enforcementService.findByStageId(context.getPermohonan().getIdPermohonan(), stageId);
                
                n.setTajuk("Maklum keputusan Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama() + " bagi urusan dibawah Seksyen 127 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                n.setMesej("Dimaklumkan bahawa satu keputusan " + fp.getKeputusan().getNama() + " telah dibuat oleh Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama()
                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
            }else if(context.getPermohonan().getKodUrusan().getKod().equals("351")){
                System.out.println("masuk 351");
                HakmilikPermohonan hm = senaraiHakmilik.get(0);
                FasaPermohonan fp = enforcementService.findByStageId(context.getPermohonan().getIdPermohonan(), stageId);
                
                n.setTajuk("Maklum keputusan Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama() + " bagi urusan dibawah Seksyen 351 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                n.setMesej("Dimaklumkan bahawa satu keputusan " + fp.getKeputusan().getNama() + " telah dibuat oleh Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama()
                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
            }else if(context.getPermohonan().getKodUrusan().getKod().equals("352")){
                System.out.println("masuk 352");
                HakmilikPermohonan hm = senaraiHakmilik.get(0);
                FasaPermohonan fp = enforcementService.findByStageId(context.getPermohonan().getIdPermohonan(), stageId);
                
                n.setTajuk("Maklum keputusan Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama() + " bagi urusan dibawah Seksyen 352 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                n.setMesej("Dimaklumkan bahawa satu keputusan " + fp.getKeputusan().getNama() + " telah dibuat oleh Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama()
                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
            }
        }

        System.out.println("notify");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("234")); //N9 - PTG (PAT)
        list.add(kodPerananDAO.findById("12")); //N9 - PTG (FAT)
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage(" Makluman kepada Pengarah Tanah dan Galian telah dihantar.");
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
