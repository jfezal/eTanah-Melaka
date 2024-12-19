
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.IntegrasiService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class SemakPelanValidator implements StageListener {

    @Inject
    private etanah.Configuration conf;
    @Inject
    IntegrasiService integService;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    DokumenDAO dokDAO;
    @Inject
    DokumenService dokServ;
    @Inject
    private TaskDebugService tds;
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(SemakPelanValidator.class);
    private String kodNegeri;
    private int kodSeksyen;
    private String[] kodDOC = {"SLLB", "PBT", "PLK", "PPP", "PTG", "JPP", "PAB"};
    //private String[] kodDOC = {"ALJ", "PBN", "PLK", "PTG", "SJT", "SPL", "JPP", "KJL", "AJB", "PBT", "RBU", "STR1", "STR1A"};
    private String pathLoc = "";

    public String[] getKodDOC() {
        return kodDOC;
    }

    public void setKodDOC(String[] kodDOC) {
        this.kodDOC = kodDOC;
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
        kodNegeri = conf.getProperty("kodNegeri");
        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan permohonan = context.getPermohonan();
        try {
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS")) {
                //check urusan: PNB registered or not if registered, have to complete it first
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                LOG.debug("----PNB----Registered----:" + mohonPNB);
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    LOG.debug("----mohonFasa----:" + mohonFasa);
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String user = (String) m.get("participant");
                    LOG.debug("----mohonPNB----user----:" + user);
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }
            if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                        || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                        || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                        || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                    LOG.info("----ID Permohonan----::" + permohonan.getIdPermohonan());
                    Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                    Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                    if (dokPLK.getCatatanMinit() != null && dokPLK.getCatatanMinit().equals("2")) {
                        dokPLK.setCatatanMinit(null);
                        dokServ.saveOrUpdate(dokPLK);
                    } else {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila pilih kaedah penghantaran fail ke JUPEM.");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

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

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
