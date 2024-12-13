/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.model.Enkuiri;
import etanah.model.KodStatusEnkuiri;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class SemakSijilRujukValodator implements StageListener {

    private static final Logger LOG = Logger.getLogger(SemakSijilRujukValodator.class);
    @Inject
    LelongService lelongService;
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

        permohonan.setStatus("RM");
        lelongService.saveOrUpdate(permohonan);

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
                    LOG.info("SL/RM");
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
                LOG.info("Berstatus SL/RM");

                KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("SL");
                Enkuiri enkuiri = null;

                enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());
                enkuiri.setStatus(kodEnkuiri);
                lelongService.saveOrUpdate(enkuiri);
            }
        }

//        if (permohonan.getPermohonanSebelum() == null) {
//            KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("RM");
//            Enkuiri en = null;
//            if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
//                en = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
//            } else {
//                en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
//            }
//            en.setStatus(kodEnkuiri);
//            lelongService.saveOrUpdate(en);
//        }

        proposedOutcome = "RM";
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
