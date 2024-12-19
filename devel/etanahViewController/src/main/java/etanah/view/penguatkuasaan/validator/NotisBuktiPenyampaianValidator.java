
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import etanah.model.Pengguna;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Notis;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.common.FasaPermohonanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author ctzainal
 */
public class NotisBuktiPenyampaianValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    EnforcementService enforcementService;
    private static final Logger LOG = Logger.getLogger(NotisBuktiPenyampaianValidator.class);
    private List<Notis> listNotis;
    private String stageId;
    private Boolean NotisDendaTambahan = false;
    @Inject
    private etanah.Configuration conf;
    private PermohonanNota permohonanNota;
    private String keputusan;
    @Inject
    EnforceService enforceService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {


        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

        listNotis = enforcementService.findNotisByIDPermohonan(permohonan.getIdPermohonan());
        LOG.info("---------size bukti penyampaian--------- :  " + listNotis.size());
        try {
            stageId = context.getStageName();
            LOG.info("--------------stage id------------- : " + stageId);

            if (listNotis.isEmpty()) {
                context.addMessage("Sila masukkan maklumat Bukti Penyampaian: " + permohonan.getIdPermohonan());
                return null;

            } else {

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("kpsn_pemantauan1") || stageId.equalsIgnoreCase("sedia_denda_tambahan")) {
                        for (int i = 0; i < listNotis.size(); i++) {
                            if (listNotis.get(i).getKodNotis().getKod().equalsIgnoreCase("PDT")) { // PDT = Notis Denda Tambahan
                                NotisDendaTambahan = true;
                            }
                        }
                        System.out.println("--- NotisDendaTambahan --- : " + NotisDendaTambahan);
                        if (NotisDendaTambahan == false) {
                            context.addMessage(" Sila masukkan Notis Denda Tambahan di tab Notis Penyampaian untuk permohonan : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }

                }
            }

            LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
            try {
                permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
                LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
                if (permohonanNota != null) {
                    LOG.info("::: kandungan nota :" + permohonanNota.getNota());
                    context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                    return null;
                }


                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                        if (fp.getKeputusan() != null) {
                            keputusan = fp.getKeputusan().getKod();
                            LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                        }
                    }
                }

                //Check mohon fasa table based on id mohon
                FasaPermohonan fasa = enforcementService.findByStageId(permohonan.getIdPermohonan(), stageId);
                InfoAudit ia = new InfoAudit();

                if (fasa != null) {
                    ia = fasa.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonan.setKeputusanOleh(fasa.getInfoAudit().getDimasukOleh());
                } else {
                    fasa = new FasaPermohonan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                fasa.setIdAliran(stageId);
                fasa.setPermohonan(permohonan);
                fasa.setCawangan(pengguna.getKodCawangan());
                fasa.setIdPengguna(pengguna.getIdPengguna());
                fasa.setTarikhKeputusan(new Date());
                fasa.setInfoAudit(ia);
                fasaPermohonanService.saveOrUpdate(fasa);

                if ("05".equals(conf.getProperty("kodNegeri"))) {

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                        if (stageId.equalsIgnoreCase("kpsn_pemantauan1")) {
                            if (keputusan.equalsIgnoreCase("BE")) { //BE =Berhenti Langgar Syarat
                                LOG.info("-------1-------");
                                updateKeputusan(permohonan, pengguna);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }



        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    public void updateKeputusan(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("-------updateKeputusan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKeputusan(kodKeputusanDAO.findById("SV")); // SV = kes selesai
            enforceService.simpanPermohonan(permohonan);

        }
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota == null) {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
