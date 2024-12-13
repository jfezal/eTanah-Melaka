/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
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
 * @author hazirah
 */
public class MohonBahanBatuanValidator implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    private etanah.Configuration conf;
    private PermohonanBahanBatuan permohonanBahanBatuan;
   
    private static final Logger LOG = Logger.getLogger(MohonBahanBatuanValidator.class);

  

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
        Pengguna peng = (Pengguna) context.getPengguna();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        permohonanBahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohon(permohonan.getIdPermohonan());

        if (permohonanBahanBatuan == null) {
            context.addMessage("Sila Masukkan Maklumat Bahan Batuan");
            return null;
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
