
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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiateN7BValidator implements StageListener {

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
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    private static final Logger LOG = Logger.getLogger(InitiateN7BValidator.class);
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
        LOG.info("Initiate N7B");

        Pengguna peng = (Pengguna) context.getPengguna();
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById("N7B");
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
