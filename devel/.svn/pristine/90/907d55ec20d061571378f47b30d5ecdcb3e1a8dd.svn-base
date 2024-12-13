package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.service.common.TaskDebugService;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import java.util.Map;

/**
 *
 * @author ${user}
 */
public class PendaftaranHakmilikValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
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
    CommonService comm;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    //added
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    private TaskDebugService tds;
    @Inject
    KodPerananDAO kodPerananDAO;
    private static final Logger LOG = Logger.getLogger(PendaftaranHakmilikValidator.class);
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
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodUrusan kod = kodUrusanDAO.findById("HT");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());

        FasaPermohonan mohonFasa = new FasaPermohonan();

        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PSBS")) {

            try {
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
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }
        }

        /*
        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                LOG.info("--PHPC/PHPP--");
                strService.generateHakmilikStrata(infoAudit, permohonan, peng);
            } else {
                LOG.info("--PBBS/PBBD/PBS/PSBS/PBBSS--");
                comm.generateHakmilikStrata(infoAudit, permohonan, peng);
            }

        } else {
            Permohonan permohonanBaru = new Permohonan();
            try {
                permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);
            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(PendaftaranHakmilikValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StaleObjectException ex) {
                java.util.logging.Logger.getLogger(PendaftaranHakmilikValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(PendaftaranHakmilikValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            LOG.info("PERMOhONANA BARU : " + permohonanBaru.getIdPermohonan());
            comm.generateHakmilikStrata(infoAudit, permohonanBaru, peng);
        }
        */ 

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan PTG");
        n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Timbalan Pengarah (Pendaftaran) untuk makluman");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("63"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage(" - Makluman kepada Timbalan Pengarah (Pendaftaran) telah dihantar.");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
