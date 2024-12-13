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
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.model.PermohonanRujukanLuar;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikService;
import etanah.service.common.LelongService;
import etanah.service.PengambilanService;
import etanah.service.common.PermohonanRujukanLuarService;

/**
 *
 * @author nordiyana
 */
public class PengambilanEndorsanK_2 implements StageListener {

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
        LOG.info("Initiate Pengambilan K Sebahagian untuk tiada desakan sahaja");
        stage = context.getStageName();
        LOG.info("stage :: " + stage);
        FasaPermohonan fasaPermohonan = null;
        try {
            if (stage.equals("51SemakanLaporan")) {
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
//        if (fasaPermohonan.getKeputusan().getKod().equals("DE")) {
//            LOG.debug("Kedesakan");
//            if (!sebahagianList.isEmpty()) {
//                LOG.debug("Kedesakan dan sebahagian");
//                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
//                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
//                }
//                KodUrusan kod = kodUrusanDAO.findById("ABTKB");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikS, permohonan);
//
//            }
//            if (!keseluruhanList.isEmpty()) {
//                LOG.debug("Kedesakan dan keseluruhan");
//                for (HakmilikPermohonan hakmilikPermohonan : keseluruhanList) {
//                    senaraiHakmilikK.add(hakmilikPermohonan.getHakmilik());
//                }
//                KodUrusan kod = kodUrusanDAO.findById("ABT-K");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikK, permohonan);
//
//            }
//        }

        if (fasaPermohonan.getKeputusan().getKod().equals("XK")) {
            LOG.debug("Tiada Kedesakan");
            if (!sebahagianList.isEmpty()) {
                LOG.debug("Tiada Kedesakan dan sebahagian");
                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
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
