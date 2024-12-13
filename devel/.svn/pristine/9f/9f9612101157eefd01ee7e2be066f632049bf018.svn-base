/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Lelongan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
public class KemasukanJurulelongValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KemasukanJurulelongValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;

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

        List<FasaPermohonan> checkStatussemak16HLantikJurulelong = lelongService.checkStatussemak16HLantikJurulelong(permohonan.getIdPermohonan());

        //kalo tak agihkan tugas pada jurulelong keluar mesej (check by keputusan kt mohon fasa)
        if (!permohonan.getKodUrusan().getKod().equals("PPTL")) {
            if (!checkStatussemak16HLantikJurulelong.isEmpty()) {
                LOG.info("Selesai Tanpa Agihkan Tugas Kepada Jurulelong Yang Di pilih");
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Agihkan Tugas Kepada Jurulelong Berlesen Yang Di Pilih");
                return null;
            }
        }

        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }

        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        if (!permohonan.getKodUrusan().getKod().equals("PPTL")) {
            FasaPermohonan fasa = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
            proposedOutcome = fasa.getKeputusan().getKod();
            if (fasa.getKeputusan().getKod().equals("JL")) {
                List<Lelongan> listLel = lelongService.listLelongan(permohonan.getIdPermohonan());
                for (Lelongan lel : listLel) {
                    if (lel.getJurulelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Berlesen Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            }
        }
        List<FasaPermohonan> mohonFasa2 = lelongService.findFasa(permohonan.getIdPermohonan());
        if (!permohonan.getKodUrusan().getKod().equals("PPTL")) {
            for (FasaPermohonan mf : mohonFasa2) {
                if (mf.getIdAliran().equals("kmskJurulelong")) {
                    FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanTangguh(permohonan.getIdPermohonan());
                    permohonan.setStatus(fasaPermohonan.getKeputusan().getKod());
                    permohonanDAO.saveOrUpdate(permohonan);
                    lelongService.saveOrUpdate(permohonan);
                }
            }
        } else {
            permohonan.setStatus("L");
            permohonanDAO.saveOrUpdate(permohonan);
            lelongService.saveOrUpdate(permohonan);
        }
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
