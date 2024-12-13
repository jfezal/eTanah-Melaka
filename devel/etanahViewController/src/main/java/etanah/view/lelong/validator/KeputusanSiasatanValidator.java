/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Kehadiran;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodStatusPermohonan;
import etanah.model.Lelongan;
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
public class KeputusanSiasatanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KeputusanSiasatanValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    KodStatusPermohonanDAO kodStatusMohonDAO;

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

        List<FasaPermohonan> fasaList = lelongService.findFasaPermohonanSiasatanList(permohonan.getIdPermohonan());
        FasaPermohonan fasa = fasaList.get(0);
        if (fasa.getKeputusan().getKod().equals("PJ")) {
            LOG.info("-----PJ-----");
            List<Lelongan> listLel = lelongService.listLelongan(permohonan.getIdPermohonan());
            if (listLel.size() != 0) {
                for (Lelongan ll : listLel) {
                    if (ll.getTarikhLelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                        return null;
                    }
                    if (ll.getTempat() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tempat Lelongan Di Tab Maklumat Keputusan");
                        return null;
                    }
                    if (ll.getDeposit() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Deposit Di Tab Maklumat Keputusan");
                        return null;
                    }
                    if (ll.getHargaRizab() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Harga Rizab Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                return null;
            }
        }

        if (fasa.getKeputusan().getKod().equals("TG")) {

            Enkuiri en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
            if (en != null) {
                if (en.getTarikhEnkuiri() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                }
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                return null;
            }

        }

        List<Kehadiran> hadir = lelongService.getListKehadiran(permohonan.getIdPermohonan());
        if (hadir.size() != 0) {
            for (Kehadiran kk : hadir) {
                if (kk.getHadir() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Rekod Kehadiran Di Tab Rekod Kehadiran");
                    return null;
                }
            }
        }

//        if (fasa.getKeputusan().getKod().equals("PJ")) {
//            KodStatusEnkuiri kodEnk = kodStatusEnkuiriDAO.findById("TA");
//            Enkuiri en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
//            en.setStatus(kodEnk);
//            lelongService.saveOrUpdate(en);
//        }

        if (fasa.getKeputusan().getKod().equals("T")) {
            permohonan.setStatus("T");
            lelongService.saveOrUpdate(permohonan);
            proposedOutcome = fasa.getKeputusan().getKod();
            return proposedOutcome;
        }
        if (fasa.getKeputusan().getKod().equals("ZY")) {
            LOG.info("kod : " + fasa.getKeputusan().getKod());
            KodStatusEnkuiri kod = kodStatusEnkuiriDAO.findById("T");//TP
            Enkuiri en = lelongService.findEnkuiri(permohonan.getIdPermohonan());
            en.setStatus(kod);
            lelongService.saveOrUpdate(en);
            permohonan.setStatus("TT");
            lelongService.saveOrUpdate(permohonan);
//            proposedOutcome = "TT";
            proposedOutcome = "ZY";
        }

//        proposedOutcome = fasa.getKeputusan().getKod();
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        LOG.info("---afterComplete---");
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
