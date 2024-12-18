/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.StrataPtService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;

/**
 *
 * @author Murali
 */
public class PenamatanStrataValidator1 implements StageListener {

    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private static final Logger LOG = Logger.getLogger(PenamatanStrataValidator1.class);
    private Hakmilik hakmilik;
    private String idHakmilik;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        System.out.println("--before selesai--");

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        FasaPermohonan mohonFasa = new FasaPermohonan();
        KodKeputusan kodkpsn = kodKeputusanDAO.findById("L");
        mohonFasa.setKeputusan(kodkpsn);

        permohonan.setKeputusan(kodkpsn);
        peng = (Pengguna) context.getPengguna();
        permohonan.setKeputusanOleh(peng);
        permohonan.setTarikhKeputusan(new Date());
        strService.updateMohon(permohonan);


        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};

        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
        LOG.info("--Task Initiating to HTBT--");
        KodUrusan kod = kodUrusanDAO.findById("HTBT");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());

        Permohonan mohonReg = new Permohonan();

        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
        } else {
            mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPNSS(kod, peng, senaraiHakmilik, permohonan);
        }

        LOG.info("--Task Initiated to HTBT--");

        LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
        LOG.info("--Saving in Mohon Rujuluar--:");
        PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
        permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
        permohonanRujLuar.setCawangan(peng.getKodCawangan());
        permohonanRujLuar.setPermohonan(mohonReg);
        //ida tmbh
        permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
        permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
        permohonanRujLuar.setNoRujukan("1");
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String formattedDate = dateFormat.format(date);
            LOG.info("--formattedDate--" + formattedDate);
            permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
        } catch (Exception e) {
        }
        KodRujukan kodRujukan;
        kodRujukan = kodRujukanDAO.findById("FL");
        permohonanRujLuar.setKodRujukan(kodRujukan);
        strService.SimpanMohonRujukLuar(permohonanRujLuar);
        LOG.info("--Saved in Mohon Rujuluar--:");

        System.out.println("--after selesai--message--");
        //context.addMessage(" - Penghantaran ke Modul Pendaftaran berjaya");
        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran ("+mohonReg.getKodUrusan().getNama()+").");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        context.addMessage("  Penghantaran ke Modul Pendaftaran berjaya");
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
