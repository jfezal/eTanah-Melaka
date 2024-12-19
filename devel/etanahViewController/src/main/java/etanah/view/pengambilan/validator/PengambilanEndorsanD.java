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
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
public class PengambilanEndorsanD implements StageListener {

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
    HakmilikService hakmilikservice;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private static final Logger LOG = Logger.getLogger(PengambilanEndorsanD.class);
    List<Hakmilik> shptg;

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
        LOG.info("Initiate Pengambilan D");

        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> ptgHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> sbnHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> pdHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> tampinHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> jelebuHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> jempulHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> pilahHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> rembauHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
//        List<HakmilikPermohonan> hakmilikPermohonan = permohonan.getSenaraiHakmilik();
//        for (int i = 0; i < hakmilikPermohonan.size(); i++) {
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {

            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("00")) {
                ptgHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("01")) {
                jelebuHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("02")) {
                pilahHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("03")) {
                pdHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("04")) {
                rembauHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("05")) {
                sbnHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("06")) {
                tampinHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
            if (hakmilikPermohonan.getHakmilik().getCawangan().getKod().equals("07")) {
                jempulHakmilik.add(hakmilikPermohonan.getHakmilik());
            }
        }

        if (!ptgHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, ptgHakmilik, permohonan, context.getStageName());

        }
        if (!jelebuHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, jelebuHakmilik, permohonan, context.getStageName());

        }
        if (!pilahHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, pilahHakmilik, permohonan, context.getStageName());

        }
        if (!pdHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, pdHakmilik, permohonan, context.getStageName());

        }
        if (!rembauHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, rembauHakmilik, permohonan, context.getStageName());

        }
        if (!sbnHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, sbnHakmilik, permohonan, context.getStageName());

        }
        if (!tampinHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, tampinHakmilik, permohonan, context.getStageName());

        }
        if (!jempulHakmilik.isEmpty()) {
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById("ABT-D"), peng, jempulHakmilik, permohonan, context.getStageName());

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


//        KodUrusan kod = kodUrusanDAO.findById("ABT-D");
//        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
//        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan, context.getStageName());
//        }
//        return null;
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
//
//     void initiate(StageContext context, String proposedOutcome){
//
//         Permohonan permohonan = context.getPermohonan();
//         Pengguna peng = (Pengguna) context.getPengguna();
//         PermohonanRujukanLuar mrl = mrlservice.findByidPermohonan(permohonan.getIdPermohonan());
//         InfoAudit infoAudit = new InfoAudit();
//         infoAudit.setDimasukOleh(peng);
//         infoAudit.setTarikhMasuk(new java.util.Date());
//         List<HakmilikPermohonan> mhList=permohonan.getSenaraiHakmilik();
//
//         for(HakmilikPermohonan hp:mhList){
//         idHakmilik=hp.getHakmilik().getIdHakmilik();
//         }
//         String[] name = {"idHakmilik"};
//         Object[] value = {idHakmilik};
//         List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//         KodUrusan kod = new KodUrusan();
//         LOG.info("Kod Urusan " + context.getPermohonan().getKodUrusan().getKod());
////          if(context.getPermohonan().getKodUrusan().getKod().equals("831A")||context.getPermohonan().getKodUrusan().getKod().equals("831B")||context.getPermohonan().getKodUrusan().getKod().equals("831C")){
//             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         if(context.getPermohonan().getKodUrusan().getKod().equals("831A")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831B")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831C")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
//         generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//     }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
