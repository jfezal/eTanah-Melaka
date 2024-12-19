/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

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
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.StrataPtService;
import etanah.service.PembangunanService;
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

public class semakpermohonan implements StageListener {

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
    PembangunanService pembangunanServ;
    @Inject
    etanah.Configuration conf;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private static final Logger LOG = Logger.getLogger(semakpermohonan.class);
    private Hakmilik hakmilik;
    private String idHakmilik;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("--before selesai--");

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semak_permohonan");
        List<HakmilikPihakBerkepentingan> idPhkNIPemohon = pembangunanServ.getIdPihakNotInPemohon(permohonan.getIdPermohonan());
        LOG.info("--idPhkNIPemohon--" + idPhkNIPemohon.toString());
        if (permohonan.getKodUrusan().getKod().equals("PPCB")) {
            if (idPhkNIPemohon.size() >= 1) {

                KodKeputusan kodkpsn = kodKeputusanDAO.findById("LK");
                mohonFasa.setKeputusan(kodkpsn);
            } else {
                KodKeputusan kodkpsn = kodKeputusanDAO.findById("TL");
                mohonFasa.setKeputusan(kodkpsn);
            }
            mohonFasa.setTarikhKeputusan(new Date());
            strService.saveFasaMohon(mohonFasa);
            LOG.info("----Update Keputusan Success----:");
        }

        LOG.info("--selesai--");
        return null;
//        return proposedOutcome;
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
