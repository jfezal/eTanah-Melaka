/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

/**
 *
 * @author nordiyana
 */
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
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikService;
import etanah.service.common.LelongService;
import etanah.service.PengambilanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.util.StringUtils;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author nordiyana
 */
public class PengambilanEndorsanK implements StageListener {

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
    KodCawanganDAO kodCawanganDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikService hakmilikservice;
    private static final Logger LOG = Logger.getLogger(PengambilanEndorsanK.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private String idPermohonanReg;
    FasaPermohonan mohonaFasa;
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public FasaPermohonan getMohonaFasa() {
        return mohonaFasa;
    }

    public void setMohonaFasa(FasaPermohonan mohonaFasa) {
        this.mohonaFasa = mohonaFasa;
    }

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
        LOG.info("Initiate Pengambilan K Sebahagian");
        stage = context.getStageName();
        LOG.info("stage :: " + stage);
        FasaPermohonan fasaPermohonan = null;
        try {
            if (stage.equals("43RekodBuktiSampai")) {
                fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanBaru(permohonan.getIdPermohonan());
            } else {
                fasaPermohonan = pengambilanService.findFasaPermohonanKedesakan(permohonan.getIdPermohonan());
            }
        } catch (Exception f) {
            fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanBaru(permohonan.getIdPermohonan());
        }
        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<HakmilikPermohonan> keseluruhanList;
        List<HakmilikPermohonan> sebahagianList;
        sebahagianList = hakmilikservice.findMHSebahagian(permohonan.getIdPermohonan());
        keseluruhanList = hakmilikservice.findMHKeseluruhan(permohonan.getIdPermohonan());
        List<Hakmilik> senaraiHakmilikS = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikK = new ArrayList<Hakmilik>();
        if (fasaPermohonan.getKeputusan().getKod().equals("DE")) {
            LOG.debug("Kedesakan");
            if (!sebahagianList.isEmpty()) {
                LOG.debug("Kedesakan dan sebahagian");
                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
                }
                if (!senaraiHakmilikS.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    for (Hakmilik h : senaraiHakmilikS) {
                        if (h.getNoVersiDhde() == 0) {
                            prosesTukarGanti(peng, senaraiHakmilikS);
                        }
                    }
                }

                KodUrusan kod = kodUrusanDAO.findById("ABTKB");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikS, permohonan, context.getStageName());

            }
            if (!keseluruhanList.isEmpty()) {
                LOG.debug("Kedesakan dan keseluruhan");
                for (HakmilikPermohonan hakmilikPermohonan : keseluruhanList) {
                    senaraiHakmilikK.add(hakmilikPermohonan.getHakmilik());
                }
                if (!senaraiHakmilikK.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    prosesTukarGanti(peng, senaraiHakmilikK);
                }

                KodUrusan kod = kodUrusanDAO.findById("ABT-K");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikK, permohonan, context.getStageName());

            }
        }

        if (fasaPermohonan.getKeputusan().getKod().equals("XK")) {
            LOG.debug("Tiada Kedesakan");
            if (!sebahagianList.isEmpty()) {
                LOG.debug("Tiada Kedesakan dan sebahagian");
                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
                }
                if (!senaraiHakmilikS.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    prosesTukarGanti(peng, senaraiHakmilikS);
                }

                KodUrusan kod = kodUrusanDAO.findById("ABTKB");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikS, permohonan, context.getStageName());

            }
            if (!keseluruhanList.isEmpty()) {
                LOG.debug("Tiada Kedesakan dan keseluruhan");

                for (HakmilikPermohonan hakmilikPermohonan : keseluruhanList) {
                    senaraiHakmilikK.add(hakmilikPermohonan.getHakmilik());
                }
                if (!senaraiHakmilikK.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    prosesTukarGanti(peng, senaraiHakmilikK);
                }

                KodUrusan kod = kodUrusanDAO.findById("ABT-K");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikK, permohonan, context.getStageName());

            }

        }





//        }

        return proposedOutcome;
//        return null;
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

//     void initiate(StageContext context, String proposedOutcome){
//
//         Permohonan permohonan = context.getPermohonan();
//         Pengguna peng = (Pengguna) context.getPengguna();
//         List<PermohonanRujukanLuar> mrl = mrlservice.findByidPermohonanList(permohonan.getIdPermohonan());
//         int i=mrl.size();
//         String nowarta;
//         String tarikhwarta;
//
//         nowarta=mrl.get(i).getItem();
//         tarikhwarta=mrl.get(i).getTarikhLulus().toString();
//         sebahagianList=hakmilikservice.findMHSebahagian(permohonan.getIdPermohonan());
//         InfoAudit infoAudit = new InfoAudit();
//         infoAudit.setDimasukOleh(peng);
//         infoAudit.setTarikhMasuk(new java.util.Date());
//
//         for(HakmilikPermohonan hp: sebahagianList)
//         {
//          idHakmilik = hp.getHakmilik().getIdHakmilik();
//
//
//         }
//         String[] name = {"idHakmilik"};
//         Object[] value = {idHakmilik};
//         List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//         KodUrusan kod = new KodUrusan();
////         if(context.getPermohonan().getKodUrusan().getKod().equals("831A")||context.getPermohonan().getKodUrusan().getKod().equals("831B")||context.getPermohonan().getKodUrusan().getKod().equals("831C")){
//             kod = kodUrusanDAO.findById("ABTKB");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831B")){
////             kod = kodUrusanDAO.findById("ABT-K");
////         }else if(context.getPermohonan().getKodUrusan().getKod().equals("831C")){
////             kod = kodUrusanDAO.findById("ABT-K");
////         }
//         generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
