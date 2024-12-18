/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.Notis;
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
public class RekodNotisN9Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(SuratSiasatanValidator.class);
    @Inject
    LelongService lelongService;

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
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String idPermohonan = context.getPermohonan().getIdPermohonan();
//        String idPermohonan = permohonan.getIdPermohonan();

        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
//    
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
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(idPermohonan);
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }

        List<FasaPermohonan> fasaPermohonan = lelongService.getPermonanFasa(idPermohonan);
        if (!fasaPermohonan.isEmpty()) {
            FasaPermohonan ff = fasaPermohonan.get(0);
            lelongService.delete(ff);
        }

        List<FasaPermohonan> fasaPermohonan1 = lelongService.getFasaPermohonanSemakRekodPenghantaran(idPermohonan);
        if (!fasaPermohonan1.isEmpty()) {
            FasaPermohonan ff = fasaPermohonan1.get(0);
            lelongService.delete(ff);
        }

        List<FasaPermohonan> fasaPermohonan2 = lelongService.getFasaPermohonanMohonTangguh(idPermohonan);
        if (!fasaPermohonan2.isEmpty()) {
            FasaPermohonan ff = fasaPermohonan2.get(0);
            lelongService.delete(ff);
        }

//        if (!fasaPermohonan.isEmpty()) {
//            for (FasaPermohonan fp : fasaPermohonan) {
//                LOG.info("idAliran :" + fp.getIdAliran());
//                lelongService.delete(fp);
//            }
//        }



        List<Notis> list = lelongService.getListNotis(idPermohonan, "SSL");

        for (Notis nn : list) {
            LOG.info("=== Cek for Notis ===");
//          if (!"AR".equals(nn.getKodCaraPenghantaran().getKod())) {

//            if (nn.getKodCaraPenghantaran() == null) {
//                context.addMessage(idPermohonan + " - Sila Pilih Cara Penghantaran Notis Di Tab Rekod Penghantaran");
//                return null;
//            }
            LOG.info("=== kod cara hantar = " + nn.getKodCaraPenghantaran().getKod());
            if ("AR".equals(nn.getKodCaraPenghantaran().getKod()) || "PO".equals(nn.getKodCaraPenghantaran().getKod())) {
                LOG.info("=== Cek CaraHantar = !AR/!PO/null ===");
                LOG.info("=== kod cara hantar = " + nn.getKodCaraPenghantaran().getKod());
            } else {
                if (nn.getPenghantarNotis() == null) {
                    LOG.info("=== Cek Penghantar ===");
                    context.addMessage(idPermohonan + " - Sila Pilih Nama Penghantar Notis Di Tab Rekod Penghantaran");
                    return null;
                }
            }
        if (nn.getKodStatusTerima() == null) {
            context.addMessage(idPermohonan + " - Sila Pilih Status Penyampaian Notis Di Tab Rekod Penghantaran");
            return null;
        }

        if (nn.getTarikhHantar() == null) {
            context.addMessage(idPermohonan + " - Sila Pilih Tarikh Hantar Di Tab Rekod Penghantaran");
            return null;
        }
    }
    List<Kehadiran> listKehadiran = lelongService.getListKehadiran(idPermohonan);

    if (!listKehadiran.isEmpty () 
        ) {
                for (Kehadiran kk : listKehadiran) {
            if (!kk.getEnkuiri().getStatus().getKod().equals("AK")) {
                showForm4(listKehadiran, context.getPermohonan().getIdPermohonan());
            }
        }
    }
//        }
    return proposedOutcome ;
}
public void showForm4(List<Kehadiran> listKehadiran, String idMohon) {

        Enkuiri enkuiri = lelongService.findEnkuiri(idMohon);
        if (enkuiri == null) {
            List<Enkuiri> enkuiriList = lelongService.findEnkuiriTG(idMohon);
            enkuiri = enkuiriList.get(0);
        } else {
            for (Kehadiran kk : listKehadiran) {
                InfoAudit info = kk.getInfoAudit().getDimasukOleh().getInfoAudit();
                info.setDiKemaskiniOleh(kk.getInfoAudit().getDimasukOleh());
                info.setTarikhKemaskini(new java.util.Date());
                kk.setInfoAudit(info);
                kk.setHadir(null);
                kk.setCatatan(null);
                kk.setWakilNama(null);
                kk.setKeterangan(null);
                kk.setWakilJenisPengenalan(null);
                kk.setWakilNoPengenalan(null);
                kk.setEnkuiri(enkuiri);
                lelongService.saveOrUpdate(kk);
            }
        }
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
