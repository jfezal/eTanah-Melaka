/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class Cetak16IValaidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(BayaranValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;

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
        LOG.info("-------beforeComplete----");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

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
//        
        List<Lelongan> ll = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());

        for (Lelongan oo : ll) {
            oo.setKodStatusLelongan(kodStatusLelonganDAO.findById("SL"));
            lelongService.saveOrUpdate(oo);
        }

        FasaPermohonan fasa = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());
        if (fasa != null) {
            permohonan.setKeputusanOleh(fasa.getInfoAudit().getDimasukOleh());
        } else {
            fasa = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
            if (fasa != null) {
                permohonan.setKeputusanOleh(fasa.getInfoAudit().getDimasukOleh());
            }
        }
        permohonan.setStatus("SL");
        permohonan.setTarikhKeputusan(new Date());
        KodKeputusan kodKpsn = kodKeputusanDAO.findById("L");
        permohonan.setKeputusan(kodKpsn);
        lelongService.saveOrUpdate(permohonan);


//        if (permohonan.getPermohonanSebelum() == null) {
//            Enkuiri en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
//            en.setStatus(kodStatusEnkuiriDAO.findById("SL"));
//            lelongService.saveOrUpdate(en);
//        }

        //editted by nur.aqilah
        //check idmohon ada jadi idmohon sebelum bg idmohon lain ke tak

        List<Permohonan> checkIdSebelum = lelongService.checkIdMohonSebelum2(idPermohonan);
        LOG.info("Jadi Id Mohon Sebelum Ke Tidak " + checkIdSebelum.size());

        if (checkIdSebelum.isEmpty()) {
            LOG.info("Tak Jadi Id Mohon Sebelum");
            KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("SL");
            Enkuiri enkuiri = null;
            if (permohonan.getPermohonanSebelum() == null) {
                LOG.info("Tak ada Id Mohon Sebelum");
                enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());
                enkuiri.setStatus(kodEnkuiri);
                lelongService.saveOrUpdate(enkuiri);
            } else {
                LOG.info("Ada Id Mohon Sebelum");
                LOG.info("Status Id Mohon Sebelum " + permohonan.getPermohonanSebelum().getStatus());
                LOG.info("Status Id Mohon Sebelum " + permohonan.getPermohonanSebelum().getIdPermohonan());
                if (permohonan.getPermohonanSebelum().getStatus().equals("SL") || permohonan.getPermohonanSebelum().getStatus().equals("RM") || permohonan.getPermohonanSebelum().getStatus().equals("L") || permohonan.getPermohonanSebelum().getStatus().equals("T")) {
                    LOG.info("Berstatus SL/RM/L/T");
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    enkuiri.setStatus(kodEnkuiri);
                    lelongService.saveOrUpdate(enkuiri);
                }

            }
        } else {
            LOG.info("Jadi Id Mohon Sebelum");

            String idMohon = checkIdSebelum.get(0).getIdPermohonan();
            LOG.info("Id Mohon yg ada Id Mohon Sebelum " + idMohon + " Status " + checkIdSebelum.get(0).getStatus());

            //check id mohon yg ada id mohon sebelum punya status
            //kalo status sudah selesai atau rujuk mahkamah 
            //update table enkuiri kepada selesai/rujuk mahkamah
            if (checkIdSebelum.get(0).getStatus().equals("SL") || checkIdSebelum.get(0).getStatus().equals("RM") || checkIdSebelum.get(0).getStatus().equals("L") || checkIdSebelum.get(0).getStatus().equals("T")) {
                LOG.info("Berstatus SL/RM/L/T");

                KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("SL");
                Enkuiri enkuiri = null;

                enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());
                enkuiri.setStatus(kodEnkuiri);
                lelongService.saveOrUpdate(enkuiri);
            }
        }


        proposedOutcome = "SL";
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
