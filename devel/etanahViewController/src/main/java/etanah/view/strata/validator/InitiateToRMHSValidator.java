
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
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.gis.PelanGIS;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class InitiateToRMHSValidator implements StageListener {

    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenDAO dokDAO;
    @Inject
    DokumenService dokServ;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private TaskDebugService tds;
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(InitiateToRMHSValidator.class);
    private String[] kodurs = {"PBBS", "PBBD", "PBS", "PBTS", "PBBSS"};

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
        try {

            FasaPermohonan mohonFasa = new FasaPermohonan();
            Permohonan mohonRMHS = new Permohonan();
            Permohonan mohonRTPS = new Permohonan();


            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "perakuan");
            if (mohonFasa.getKeputusan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan syor TPTG(Pd). ");
                return null;
            } else {
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
                if (permohonan != null) {
                    permohonan.setKeputusan(mohonFasa.getKeputusan());
                    Pengguna peng = (Pengguna) context.getPengguna();
                    permohonan.setKeputusanOleh(peng);
                    permohonan.setTarikhKeputusan(new Date());
                    strService.updateMohon(permohonan);

                    mohonFasa.setTarikhKeputusan(new Date());
                    strService.saveFasaMohon(mohonFasa);
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBS")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PBBD") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMH1A");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan dan Tanah \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }

                Permohonan mohonRMHS1 = new Permohonan();
                mohonRMHS1 = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS1");
                if (mohonRMHS1 != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS1.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS1.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS1.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PBBSS")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS5");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Apabila Bangunan Sudah Siap \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PHPC")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS6");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Petak \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }

                mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
                if (mohonRTPS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMHS7");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Penyatuan Petak \n\n<br>"
                                + "ID Permohonan: " + mohonRMHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }

                mohonRTPS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTPS");
                if (mohonRTPS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTPS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRTPS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Pindah Milik Hakmilik Strata \n\n<br>"
                                + "ID Permohonan: " + mohonRTPS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }
            }

            if (ArrayUtils.contains(kodurs, permohonan.getKodUrusan().getKod())) {
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }

                Permohonan mohonRTHS = new Permohonan();
                mohonRTHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RTHS");
                if (mohonRTHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRTHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRTHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Perlanjutan Tempoh Memohon Hakmilik Strata \n\n<br>"
                                + "ID Permohonan: " + mohonRTHS.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
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
                    Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLK");
                    Dokumen dokPLK = dokDAO.findById(d.getIdDokumen());
                    dokPLK.setCatatanMinit(null);
                    dokPLK.setPerihal(null);
                    dokServ.saveOrUpdate(dokPLK);
                    Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                    Dokumen dokJPP = dokDAO.findById(dk.getIdDokumen());
                    dokJPP.setCatatanMinit(null);
                    dokServ.saveOrUpdate(dokJPP);
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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }
}
