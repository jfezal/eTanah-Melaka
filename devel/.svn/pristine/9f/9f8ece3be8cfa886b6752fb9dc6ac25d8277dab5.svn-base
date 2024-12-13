
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiateN7BBValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow1 generateIdPerserahanWorkflow1;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(InitiateN7BBValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String stageId;
    private PermohonanNota permohonanNota;
    private String keputusan;

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
        stageId = context.getStageName();

        List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

        for (FasaPermohonan fp : senaraiFasa) {
            if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                if (fp.getKeputusan() != null) {
                    keputusan = fp.getKeputusan().getKod();
                    LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                }
            }
        }

        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("terima_laporan6")) {
                    if (keputusan.equalsIgnoreCase("RE")) { //RE = Remedi
                        LOG.info("-------1-------");
                        updateKeputusan(permohonan, pengguna);
                        initiateN7BB(permohonan, pengguna);
                    }
                }
            }
        } else {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("kpsn_remedi3")) {
                    if (keputusan.equalsIgnoreCase("RE")) { //RE = Remedi
                        LOG.info("-------2-------");
                        initiateN7BB(permohonan, pengguna);

                        if (!senaraiHakmilik.isEmpty()) {
                            //Need To generate before Noting
                            LOG.info("buat urusan tukar ganti 7BB");
                            for (Hakmilik h : senaraiHakmilik) {
                                if (h.getNoVersiDhde() == 0) {
                                    prosesTukarGanti(pengguna, senaraiHakmilik);
                                }
                            }
                        }
                    }
                }
            }
        }

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforceService.findNotaByIdMohon(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        try {
            if (permohonanNota == null) {
                LOG.info("--------------Permohonan Nota Null-------------");
                context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                return null;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    public void initiateN7BB(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("Initiate N7BB");
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById("N7BB");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, pengguna, senaraiHakmilik, permohonan, null);
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
        //throw new UnsupportedOperationException("Not supported yet.");
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
}
