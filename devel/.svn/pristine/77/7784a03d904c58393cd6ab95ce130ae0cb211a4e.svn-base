/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
public class EndorsanPHHL implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private static final Logger LOG = Logger.getLogger(EndorsanPHHL.class);

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

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
        LOG.info("Initiate Pengambilan Endorsan PHLL");

        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        List<HakmilikPermohonan> hakmilikPermohonanList;
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

//        for(int i=0;i<hakmilikPermohonanList.size();i++)
//        {
//        LOG.debug("::Loop Mohon Hakmilik::");
//        HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
//        LOG.debug(hmp.getHakmilik().getIdHakmilik());
//        for(Hakmilik h :senaraiHakmilik)
//        {
//            LOG.debug("::Loop Hakmilik::");
//            h.setIdHakmilik(hmp.getHakmilik().getIdHakmilik());
//            h.setUnitSyer(hmp.getLuasTerlibat());
//            senaraiHakmilik.add(h);
//        }
//
//
//
//        }
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());

        }
        if (!senaraiHakmilik.isEmpty()) {
            //Need To generate before Noting
            LOG.info("buat urusan tukar ganti");
            for (Hakmilik h : senaraiHakmilik) {
                if (h.getNoVersiDhde() == 0) {
                    prosesTukarGanti(peng, senaraiHakmilik);
                }
            }
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBA")) {
            KodUrusan kod = kodUrusanDAO.findById("ABTB");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA")) {
            KodUrusan kod = kodUrusanDAO.findById("HLLA");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP")) {
            KodUrusan kod = kodUrusanDAO.findById("HLLS");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            //  generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PILDA")) {
            KodUrusan kod = kodUrusanDAO.findById("HLTE");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHL")) {
            KodUrusan kod = kodUrusanDAO.findById("HLLB");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            // generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
            LOG.debug("::masuk sini::");
            KodUrusan kod = kodUrusanDAO.findById("HLTE");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
        }

//        }

        return proposedOutcome;
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {

        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        if (!senaraiHakmilik.isEmpty()) {

            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());

                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        }


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
        return proposedOutcome;
    }
}
