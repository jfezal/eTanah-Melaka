/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.ConsentPtdService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class BayaranTanahAdatValidation implements StageListener {

    @Inject
    ConsentPtdService consentService;
    private static Pengguna pengguna;
    private static final Logger LOG = Logger.getLogger(BayaranTanahAdatValidation.class);

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
        pengguna = context.getPengguna();

        //FOR URUSAN PMWNA AND PMWWA (KES 2 KALI BAYARAN)
        if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/pmwna")) {

            List<PermohonanTuntutanKos> mohonTuntutKosList = new ArrayList<PermohonanTuntutanKos>();
            if (context.getStageName().endsWith("Stage9")) {
                mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntut(permohonan.getIdPermohonan(), "CON01");
            } else {
                mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntut(permohonan.getIdPermohonan(), "CON02");
            }

            if (mohonTuntutKosList.isEmpty()) {
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanTuntutanKos permohonanTuntutanKos = new PermohonanTuntutanKos();
                permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
                permohonanTuntutanKos.setPermohonan(permohonan);

                permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                permohonanTuntutanKos.setInfoAudit(infoAudit);
                KodTuntut kodTuntut = new KodTuntut();
                if (context.getStageName().endsWith("Stage9")) {

                    kodTuntut.setKod("CON01");
                    permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(10 * permohonan.getSenaraiHakmilik().size()));
                    permohonanTuntutanKos.setItem("Bayaran Permohonan");
                } else {

                    kodTuntut.setKod("CON02");
                    permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(10));
                    permohonanTuntutanKos.setItem("Bayaran Perintah Tanah Adat");
                }
                permohonanTuntutanKos.setKodTuntut(kodTuntut);
                LOG.info("----Simpan mohon tuntut kos PMWNA and PMWWA----");
                consentService.simpanMohonTuntutKos(permohonanTuntutanKos);
            }

        } else {

            List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKos(permohonan.getIdPermohonan());

            if (mohonTuntutKosList.isEmpty()) {
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut.setKod("DEV12");
                PermohonanTuntutanKos permohonanTuntutanKos = new PermohonanTuntutanKos();
                permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
                permohonanTuntutanKos.setPermohonan(permohonan);
                permohonanTuntutanKos.setKodTuntut(kodTuntut);

                if (permohonan.getKodUrusan().getKod().equals("PMMMK")) {
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        permohonanTuntutanKos.setItem("Bayaran Premium Tanah");
                        permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(3000));
                        permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                        permohonanTuntutanKos.setInfoAudit(infoAudit);
                        LOG.info("----Simpan mohon tuntut kos urusan MMKN Melaka----");
                        consentService.simpanMohonTuntutKos(permohonanTuntutanKos);
                    }

                } else {
                    permohonanTuntutanKos.setItem("Bayaran Perintah Tanah Adat");
                    permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(10));
                    permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
                    permohonanTuntutanKos.setInfoAudit(infoAudit);
                    LOG.info("----Simpan mohon tuntut kos urusan Tanah Adat----");
                    consentService.simpanMohonTuntutKos(permohonanTuntutanKos);
                }
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
