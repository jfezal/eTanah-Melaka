/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class KeputusanKuatkuasaValidator implements StageListener {

    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    StrataPtService strService;
    private FasaPermohonan fasaPermohonan;
    private static final Logger LOG = Logger.getLogger(KeputusanKuatkuasaValidator.class);

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
  
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = context.getPengguna();
        KodKeputusan kk = null;

        fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "signsuratlantikan");

        PegawaiPenyiasat pp = strService.findPegawaiSiasatByJawatan(permohonan.getIdPermohonan());
        if (pp != null) {
            //if peranan = penptsrata1 (penolong pegawai tadbir), kod keputusan = A1
            if (pp.getJawatan().equals("22")) {
                proposedOutcome = "A1";
                kk = kodKeputusanDAO.findById("A1");
                LOG.info("A1");
            }
             //if peranan = pptsrata1 (penolong pegawai tanah), kod keputusan = A2
            if (pp.getJawatan().equals("20")) {
                proposedOutcome = "A2";
                kk = kodKeputusanDAO.findById("A2");
                LOG.info("A2");
            }
        }

        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "signsuratlantikan");
        InfoAudit infoAudit = new InfoAudit();
        if (mohonFasa != null) {
            infoAudit = mohonFasa.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        } else {
            mohonFasa = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        mohonFasa.setPermohonan(permohonan);
        mohonFasa.setUlasan("");
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setIdPengguna(peng.getIdPengguna());
        mohonFasa.setIdAliran("signsuratlantikan");
        mohonFasa.setCawangan(permohonan.getCawangan());
        mohonFasa.setKeputusan(kk);
        LOG.info("in saving mohonfasa : stageId = signsuratlantikan");
        mohonFasa = strService.saveFasaMohon(mohonFasa);

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        Permohonan permohonan = sc.getPermohonan();

    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
