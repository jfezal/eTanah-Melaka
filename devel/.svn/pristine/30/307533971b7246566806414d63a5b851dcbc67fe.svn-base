/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8.validator;

import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.ambil.PermohonanHakmilikBaru;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.service.ambil.PengambilanAPTService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zipzap
 */
public class EndorsanBorangKValidator implements StageListener {
    @Inject
    PengambilanAPTService pengambilanAPTService;
    

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(context.getPengguna());
        infoAudit.setTarikhMasuk(new Date());
        List<PermohonanPengambilanHakmilik> lphh = pengambilanAPTService.findHakmilikAktifNotTDK(context.getPermohonan().getIdPermohonan());
        
        
        for (PermohonanPengambilanHakmilik pengambilanHakmilik : lphh) {
            if (pengambilanHakmilik.getFlagAmbilSbgn().equals("Y")) {
                for (int i = 0; i < pengambilanHakmilik.getJumlahHakmilik(); i++) {
                    PermohonanHakmilikBaru mhb = new PermohonanHakmilikBaru();
                    mhb.setHakmilikPermohonan(pengambilanHakmilik.getHakmilikPermohonan());
                    mhb.setInfoAudit(infoAudit);
                    pengambilanAPTService.savePermohonanHakmilikBaru(mhb);
                }
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

}
