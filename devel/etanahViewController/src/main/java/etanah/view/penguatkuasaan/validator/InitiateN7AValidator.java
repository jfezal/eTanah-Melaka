
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.dao.KodCawanganDAO;
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
public class InitiateN7AValidator implements StageListener {

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
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(InitiateN7AValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String stageId;
    private PermohonanNota permohonanNota;

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

        //permohonan.getPermohonanSebelum().
//        if (permohonan.getKeputusan().getKod().equals("WJ")) {
        LOG.info("Initiate N7A");

        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
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

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById("N7A");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan, null);


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
