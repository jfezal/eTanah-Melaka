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
import etanah.model.FasaPermohonanLog;

/**
 *
 * @author mdizzat.mashrom
 */
public class SuratSiasatanValidator implements StageListener {

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

//        FasaPermohonan fasa = lelongService.findFasaPermohonanMohonTangguh(idPermohonan);
//        LOG.info("==MOHON FASA LOG : " + fasa);
        FasaPermohonan fasa;
//        Pengguna pguna = context.getPengguna();
        List<FasaPermohonan> fasaList = lelongService.findFasaPermohonanMohonTangguhList(idPermohonan);
        if (!fasaList.isEmpty()) {
            fasa = fasaList.get(0);
            List<FasaPermohonan> fasaPermohonanTannguh = lelongService.getPermohonanMohonTangguh(idPermohonan);
            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasa);
            if (fasaPermohonanLog != null) {
                lelongService.deletetest(fasaPermohonanLog, fasa);
            }
            LOG.info("+++ DELETE MOHONTANGGUH +++");
            lelongService.delete(fasa);
        }

        List<FasaPermohonan> fasaPermohonan = lelongService.getPermonanFasa(idPermohonan);
        if (!fasaPermohonan.isEmpty()) {
            FasaPermohonan ff = fasaPermohonan.get(0);
            lelongService.delete(ff);
        }
        //SSLNG untuk kptsn warta dan sume kptsn. SSL je tu nanti error if kptsn = warta
//        List<Notis> list = lelongService.getListNotis(idPermohonan, "SSL");
        List<Notis> list = lelongService.getListNotisSSLNG(idPermohonan);
        LOG.info("Size list " + list.size());
        String ng = "";
        String xHadir = "";

        //kalo user tak klik tab rekod penghantaran notis SSL tak create kt table 
        //if user klik selesai b4 klik tab rekod penghantaran message akn appear dan urusan tidak
        //di hantar ke stage seterusnya

        List<Permohonan> checkMohon = lelongService.validatorRekodPenghantaran(idPermohonan);
        LOG.info("Size checkMohon " + checkMohon.size());

        if (checkMohon.size() > 0 && list.size() < 1) {
            LOG.info("User tak klik lg tab rekod penghantaran");
            LOG.info("Notis SSL belum ada kat table Notis");
            context.addMessage(idPermohonan + " - Sila Masukan Maklumat Rekod Penghantaran");
            return null;
        }

        for (Notis nn : list) {
            if (nn.getPenghantarNotis() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Nama Penghantar Notis Di Tab Rekod Penghantaran");
                return null;
            }
            if (nn.getKodStatusTerima() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Status Penyampaian Notis Di Tab Rekod Penghantaran");
                return null;
            }
            if (nn.getKodCaraPenghantaran() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Cara Penghantaran Notis Di Tab Rekod Penghantaran");
                return null;
            }
            if (nn.getTarikhHantar() == null) {
                context.addMessage(idPermohonan + " - Sila Pilih Tarikh Hantar Di Tab Rekod Penghantaran");
                return null;
            }

            if (nn.getKodStatusTerima() != null) {
                if (nn.getKodStatusTerima().getKod().equals("XT")) {
                    xHadir = "true";
                    List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenNG(context.getPermohonan().getFolderDokumen().getFolderId());
                    LOG.debug("listKandunganFolder : " + listKandunganFolder.size());
                    if (!listKandunganFolder.isEmpty()) {
                        for (KandunganFolder kf : listKandunganFolder) {
                            if (kf.getDokumen().getKodDokumen().getKod().equals("NG")) {
                                ng = "NG";
                                break;
                            }
                        }
                    }
                }
            }

            List<Kehadiran> listKehadiran = lelongService.getListKehadiran(idPermohonan);
            if (!listKehadiran.isEmpty()) {
                for (Kehadiran kk : listKehadiran) {
                    if (!kk.getEnkuiri().getStatus().getKod().equals("AK")) {
                        showForm4(listKehadiran, context.getPermohonan().getIdPermohonan());
                        break;
                    }
                }
            }
        }
        LOG.debug("NG : " + ng);
//        if (xHadir.equals("true")) {
//            if (!ng.equals("NG")) {
//                context.addMessage(idPermohonan + " - Sila Jana Surat Warta Di Tab Rekod Penghantaran");
//                return null;
//            }
//        }

//        FasaPermohonan fasa = lelongService.findFasaPermohonanMohonTangguh(idPermohonan);
//        if (fasa != null) {
//            lelongService.delete(fasa);
//        }
//        if (fasa != null) {
//            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasa);
//            if (fasaPermohonanLog != null) {
//                lelongService.deletetest(fasaPermohonanLog, fasa);
//            }
//        }

        return proposedOutcome;
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
