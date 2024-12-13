/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import etanah.service.EnforceService;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.service.common.IntegrasiService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author ct.zainal
 */
public class IntegrationMMKN implements StageListener {

    @Inject
    private IntegrasiService intgServ;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    PembangunanService pembangunanServices;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    PendudukanSementaraMMKNService Servicemmkn;
    @Inject
    PengambilanService pengambilanService;
    EMMKNService service;
    Pemohon pemohon;
    Permohonan permohonan;
    PermohonanKertas mohonKertas;
    PermohonanKertasKandungan kertasKandung;
    private static final Logger LOG = Logger.getLogger(etanah.view.penguatkuasaan.validator.IntegrationMMKN.class);

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        LOG.info("------------------Integration--------------");
        service = new EMMKNService();
        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
        permohonan = context.getPermohonan();

        mohonKertas = new PermohonanKertas();
        mohonKertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "MMKN");


        if (mohonKertas != null) {
            System.out.println("---------- masuk mohonKertas!=null-------------");
            for (int a = 0; a < mohonKertas.getSenaraiKandungan().size(); a++) {
                kertasKandung = (PermohonanKertasKandungan) mohonKertas.getSenaraiKandungan().get(a);
                int caw = Integer.parseInt(kertasKandung.getCawangan().getKod());
                if (kertasKandung.getBil() == 1) {
                    LOG.info("---------- PTG --> tujuan" + kertasKandung.getKandungan());
                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                }
                if (kertasKandung.getBil() == 2) {
                    LOG.info("------------ PTG --> latarBelakang" + kertasKandung.getKandungan());
                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                }
                if (kertasKandung.getBil() == 3) {
                    LOG.info("-------------- PTG --> ulasan" + kertasKandung.getKandungan());
                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                }
                if (kertasKandung.getBil() == 4) {
                    LOG.info("------------- PTG --> keputusan" + kertasKandung.getKandungan());
                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                }

            }
        }

        service.addJabatan("taman jaya", "test@mail.com", "24", "01288888");

        //Set lot information
        service.addLotInformation(null, "31",
                null, "4776", null);

        //set land application
        service.addLandApplication("NOTICE_ENFORCE", "1");

        //set land details
        service.addLandDetails(null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null);

        //set kertas risalat
        String tajuk = mohonKertas.getTajuk();
        System.out.println("----tajuk------------>>" + tajuk);

        System.out.println("------>>kod urusan-- :::" + permohonan.getKodUrusan().getRujukanKanun());

        System.out.println("----------enter service create risalat------------");
        service.createRisalat(null, null, null, permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), null, tajuk);



        //Invoke the service and send the data
        String result;
        result = service.sendData();
        System.out.println("------------result ialah----->>>> :::" + result);


        if (!"SUCCESS".equals(result)) {
            System.out.println("------tidak berjaya ala sedihnyerrrr!!--------");
            context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
            context.addMessage("Message : " + service.getStatusMessage());
            LOG.info("Penghantaran" + " ke " + " e-MMKN" + " tidak" + " berjaya");
            return null;
        } else {
            System.out.println("----------berjaya!!!hoorey!---------");
            context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
            LOG.info("Penghantaran ke e-MMKN berjaya");
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
