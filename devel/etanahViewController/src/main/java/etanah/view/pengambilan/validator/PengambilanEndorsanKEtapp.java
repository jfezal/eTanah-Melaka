/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

// duplicate n modified for endorsan brg K for myeTapp by shida
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
public class PengambilanEndorsanKEtapp implements StageListener {

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
    private static final Logger LOG = Logger.getLogger(PengambilanEndorsanKEtapp.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private String idPermohonanReg;
    FasaPermohonan mohonaFasa;

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
        FasaPermohonan fasaPermohonan = null;
        try {
            fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanEtapp(permohonan.getIdPermohonan());
        } catch (Exception f) {
            fasaPermohonan = pengambilanService.findFasaPermohonanKedesakanBaruEtapp(permohonan.getIdPermohonan());
        }
        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<HakmilikPermohonan> keseluruhanList;
        List<HakmilikPermohonan> sebahagianList;
        sebahagianList = hakmilikservice.findMHSebahagian2(permohonan.getIdPermohonan());
        keseluruhanList = hakmilikservice.findMHKeseluruhan2(permohonan.getIdPermohonan());
        List<Hakmilik> senaraiHakmilikS = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikK = new ArrayList<Hakmilik>();
        List<Hakmilik> ptgHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> sbnHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> pdHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> tampinHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> jelebuHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> jempulHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> pilahHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> rembauHakmilik = new ArrayList<Hakmilik>();

        if (fasaPermohonan.getKeputusan().getKod().equals("XK")) {
            LOG.debug("Tiada Kedesakan");
            if (!sebahagianList.isEmpty()) {
                LOG.debug("Tiada Kedesakan dan sebahagian");
                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
                    for (Hakmilik hak : senaraiHakmilikS) {
                        if (hak.getCawangan().getKod().equals("00")) {
                            ptgHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("01")) {
                            jelebuHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("02")) {
                            pilahHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("03")) {
                            pdHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("04")) {
                            rembauHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("05")) {
                            sbnHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("06")) {
                            tampinHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("07")) {
                            jempulHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                    }
                }

                if (!ptgHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, ptgHakmilik, permohonan, context.getStageName());
                }
                if (!jelebuHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, jelebuHakmilik, permohonan, context.getStageName());
                }
                if (!pilahHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, pilahHakmilik, permohonan, context.getStageName());
                }
                if (!pdHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, pdHakmilik, permohonan, context.getStageName());
                }
                if (!rembauHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, rembauHakmilik, permohonan, context.getStageName());
                }
                if (!sbnHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, sbnHakmilik, permohonan, context.getStageName());
                }
                if (!tampinHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, tampinHakmilik, permohonan, context.getStageName());
                }
                if (!jempulHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, jempulHakmilik, permohonan, context.getStageName());
                }

//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, senaraiHakmilikS, permohonan,context.getStageName());

            } else if (!keseluruhanList.isEmpty()) {
                LOG.debug("Tiada Kedesakan dan keseluruhan");

                for (HakmilikPermohonan hakmilikPermohonan : keseluruhanList) {
                    senaraiHakmilikK.add(hakmilikPermohonan.getHakmilik());
                    
                    for (Hakmilik hakK : senaraiHakmilikK) {
                        if (hakK.getCawangan().getKod().equals("00")) {
                            ptgHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("01")) {
                            jelebuHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("02")) {
                            pilahHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("03")) {
                            pdHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("04")) {
                            rembauHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("05")) {
                            sbnHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("06")) {
                            tampinHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("07")) {
                            jempulHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                    }
                }

                if (!ptgHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, ptgHakmilik, permohonan, context.getStageName());
                }
                if (!jelebuHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, jelebuHakmilik, permohonan, context.getStageName());
                }
                if (!pilahHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, pilahHakmilik, permohonan, context.getStageName());
                }
                if (!pdHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, pdHakmilik, permohonan, context.getStageName());
                }
                if (!rembauHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, rembauHakmilik, permohonan, context.getStageName());
                }
                if (!sbnHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, sbnHakmilik, permohonan, context.getStageName());
                }
                if (!tampinHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, tampinHakmilik, permohonan, context.getStageName());
                }
                if (!jempulHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, jempulHakmilik, permohonan, context.getStageName());
                }
//                }
//                KodUrusan kod = kodUrusanDAO.findById("ABT-K");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikK, permohonan, context.getStageName());

            }

        } else {
            LOG.debug("Kedesakan");
            if (!sebahagianList.isEmpty()) {
                LOG.debug("Kedesakan dan sebahagian");
                for (HakmilikPermohonan hakmilikPermohonan : sebahagianList) {
                    senaraiHakmilikS.add(hakmilikPermohonan.getHakmilik());
                    
                    for (Hakmilik hak : senaraiHakmilikS) {
                        if (hak.getCawangan().getKod().equals("00")) {
                            ptgHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("01")) {
                            jelebuHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("02")) {
                            pilahHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("03")) {
                            pdHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("04")) {
                            rembauHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("05")) {
                            sbnHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("06")) {
                            tampinHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hak.getCawangan().getKod().equals("07")) {
                            jempulHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                    }
                }

                if (!ptgHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, ptgHakmilik, permohonan, context.getStageName());
                }
                if (!jelebuHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, jelebuHakmilik, permohonan, context.getStageName());
                }
                if (!pilahHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, pilahHakmilik, permohonan, context.getStageName());
                }
                if (!pdHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, pdHakmilik, permohonan, context.getStageName());
                }
                if (!rembauHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, rembauHakmilik, permohonan, context.getStageName());
                }
                if (!sbnHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, sbnHakmilik, permohonan, context.getStageName());
                }
                if (!tampinHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, tampinHakmilik, permohonan, context.getStageName());
                }
                if (!jempulHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABTKB"), peng, jempulHakmilik, permohonan, context.getStageName());
                }
//                }
//                KodUrusan kod = kodUrusanDAO.findById("ABTKB");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikS, permohonan, context.getStageName());

            }
            if (!keseluruhanList.isEmpty()) {
                LOG.debug("Kedesakan dan keseluruhan");
                for (HakmilikPermohonan hakmilikPermohonan : keseluruhanList) {
                    senaraiHakmilikK.add(hakmilikPermohonan.getHakmilik());
                    
                    for (Hakmilik hakK : senaraiHakmilikK) {
                        if (hakK.getCawangan().getKod().equals("00")) {
                            ptgHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("01")) {
                            jelebuHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("02")) {
                            pilahHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("03")) {
                            pdHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("04")) {
                            rembauHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("05")) {
                            sbnHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("06")) {
                            tampinHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                        if (hakK.getCawangan().getKod().equals("07")) {
                            jempulHakmilik.add(hakmilikPermohonan.getHakmilik());
                        }
                    }
                }

                if (!ptgHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, ptgHakmilik, permohonan, context.getStageName());
                }
                if (!jelebuHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, jelebuHakmilik, permohonan, context.getStageName());
                }
                if (!pilahHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, pilahHakmilik, permohonan, context.getStageName());
                }
                if (!pdHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, pdHakmilik, permohonan, context.getStageName());
                }
                if (!rembauHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, rembauHakmilik, permohonan, context.getStageName());
                }
                if (!sbnHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, sbnHakmilik, permohonan, context.getStageName());
                }
                if (!tampinHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, tampinHakmilik, permohonan, context.getStageName());
                }
                if (!jempulHakmilik.isEmpty()) {
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-K"), peng, jempulHakmilik, permohonan, context.getStageName());
                }
//                }
//                KodUrusan kod = kodUrusanDAO.findById("ABT-K");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilikK, permohonan, context.getStageName());

            }
        }





//        }

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
