
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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class InitiateTBTWKValidator implements StageListener {

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
    private static final Logger LOG = Logger.getLogger(InitiateTBTWKValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;

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
        if (permohonan.getKeputusan().getKod().equals("WJ")) {
            LOG.info("Initiate TBTWK");

            Pengguna peng = (Pengguna) context.getPengguna();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
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
            KodUrusan kod = kodUrusanDAO.findById("TBTWK");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan, null);
        } else {
            LOG.info("Initiate TBTWB");

            Pengguna peng = (Pengguna) context.getPengguna();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
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
            KodUrusan kod = kodUrusanDAO.findById("TBTWB");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan, null);
        }


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

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}