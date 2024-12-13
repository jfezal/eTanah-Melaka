/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.KodStatusEnkuiri;
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
public class KpsnSiasatanN9Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KpsnSiasatanN9Validator.class);
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

        FasaPermohonan fasa = lelongService.findFasaPermohonanSiasatan(permohonan.getIdPermohonan());
        Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());
        List<Kehadiran> hadir = lelongService.getListKehadiran(permohonan.getIdPermohonan());
        if (fasa.getKeputusan() != null && !fasa.getKeputusan().getKod().equals("BP")) {
            if (!hadir.isEmpty()) {
                for (Kehadiran kk : hadir) {
                    if (kk.getHadir() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Rekod Kehadiran Di Tab Rekod Kehadiran");
                        return null;
                    }
                }
            }
        }

//        if (enkuiri != null) {
//            if (enkuiri.getNamaPeminjam() == null || enkuiri.getAmaunGadaian() == null || enkuiri.getAmaunTunggakan() == null) {
//                context.addMessage(permohonan.getIdPermohonan() + " - Sila Rekod Maklumat Pinjaman Di Tab Pinjaman");
//                return null;
//            }
//        }

        if (fasa.getKeputusan().getKod().equals("PJ")) {
            LOG.info("-----PJ-----");
            List<Lelongan> listLel = lelongService.listLelongan(permohonan.getIdPermohonan());
            if (!listLel.isEmpty()) {
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
            proposedOutcome = fasa.getKeputusan().getKod();
        }

        if (fasa.getKeputusan().getKod().equals("TG")) {
            List<Enkuiri> list = lelongService.findEnkuiriTG(permohonan.getIdPermohonan());
            if (list.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                return null;
            } else {
                if (enkuiri == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                } else {
                    Date dateEn = enkuiri.getInfoAudit().getTarikhMasuk();
                    Date dateFasa = fasa.getInfoAudit().getTarikhMasuk();
                    if (dateEn.before(dateFasa)) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                        return null;
                    } else {
                        if (enkuiri.getTarikhEnkuiri() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                            return null;
                        }
                    }
                }
            }

            hadir = lelongService.getListKehadiran(permohonan.getIdPermohonan());
            for (Kehadiran kk : hadir) {
                kk.setHadir(null);
                kk.setCatatan(null);
                kk.setWakilNama(null);
                kk.setKeterangan(null);
                kk.setWakilJenisPengenalan(null);
                kk.setWakilNoPengenalan(null);
                kk.setEnkuiri(enkuiri);
                lelongService.saveOrUpdate(kk);
            }

            if (fasa != null) {
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(new java.util.Date());
                fasa.setInfoAudit(ia);
                fasa.setKeputusan(null);
                fasa.setUlasan(null);
                fasa.setTarikhKeputusan(null);
                lelongService.saveUpdate2(fasa);
            }
            proposedOutcome = "TG";
        }

        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("T")) {
            List<KandunganFolder> listKD = lelongService.getListDokumenT(permohonan.getFolderDokumen().getFolderId());
            if (listKD.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Surat Tolak Di Tab Maklumat Keputusan");
                return null;
            }
        }
        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("BP")) {
            List<KandunganFolder> listKD = lelongService.getListDokumenSB(permohonan.getFolderDokumen().getFolderId());
            if (listKD.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Jana Batal Permohonan Di Tab Maklumat Keputusan");
                return null;
            }
        }

        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("T")) {
            KodStatusEnkuiri kod = kodStatusEnkuiriDAO.findById("T");
            enkuiri.setStatus(kod);
            lelongService.saveOrUpdate(enkuiri);
            permohonan.setStatus("T");
            lelongService.saveOrUpdate(permohonan);
            proposedOutcome = fasa.getKeputusan().getKod();
        }
        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("BP")) {
            KodStatusEnkuiri kod = kodStatusEnkuiriDAO.findById("BP");
            enkuiri.setStatus(kod);
            lelongService.saveOrUpdate(enkuiri);
            permohonan.setStatus("BP");
            lelongService.saveOrUpdate(permohonan);
            proposedOutcome = fasa.getKeputusan().getKod();
        }
        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("BL")) {
            proposedOutcome = fasa.getKeputusan().getKod();
        }
        if (fasa.getKeputusan() != null && fasa.getKeputusan().getKod().equals("TT")) {
            KodStatusEnkuiri kod = kodStatusEnkuiriDAO.findById("T");//tukar dr TP
            enkuiri.setStatus(kod);
            lelongService.saveOrUpdate(enkuiri);
            permohonan.setStatus("TT");
            lelongService.saveOrUpdate(permohonan);
            proposedOutcome = fasa.getKeputusan().getKod();
        }

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
//        return proposedOutcome;
    }
}
