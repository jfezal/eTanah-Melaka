/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class CetakWartaValidator implements StageListener {

    @Inject
    LelongService lelongService;
    private static final Logger LOG = Logger.getLogger(RekodPenghantaran16HValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        List<Notis> list = lelongService.getListNotis2(idPermohonan, "2A");
        if (list.size() != 0) {
            for (Notis nn : list) {
                if (nn.getNoRujukan() == null) {
                    context.addMessage(idPermohonan + " - Sila Masukkan No Rujukan PUNS Di Tab No Rujukan PUNS");
                    return null;
                }
            }
        } else {
            context.addMessage(idPermohonan + " - Sila Masukkan No Rujukan PUNS Di Tab No Rujukan PUNS");
            return null;
        }

        FasaPermohonan fasa = lelongService.findFasaPermohonanSemakRekodPenghantaran(idPermohonan);
        if(fasa != null ){
             FasaPermohonan ff = lelongService.findFasaPermohonanCetakWarta(permohonan.getIdPermohonan());

            if (ff != null) {
                ff.setKeputusan(fasa.getKeputusan());
                lelongService.saveUpdate2(ff);
            } else {
                ff = new FasaPermohonan();
                Pengguna p = (Pengguna) context.getPengguna();
                InfoAudit info = p.getInfoAudit();
                info.setDimasukOleh(p);
                info.setTarikhMasuk(new java.util.Date());
                ff.setCawangan(fasa.getCawangan());
                ff.setIdPengguna(fasa.getIdPengguna());
                ff.setInfoAudit(info);
                ff.setIdAliran("cetakWarta");
                ff.setPermohonan(permohonan);
                ff.setTarikhHantar(new java.util.Date());
                if (StringUtils.isNotEmpty(fasa.getUlasan())) {
                    ff.setUlasan(fasa.getUlasan());
                }
                ff.setKeputusan(fasa.getKeputusan());
                lelongService.saveUpdate2(ff);
            }
        }
        proposedOutcome = fasa.getKeputusan().getKod();
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
