
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class SediaLPRValidator implements StageListener {

    @Inject
    private etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private TaskDebugService tds;
    private String taskId;
    private String kodNegeri;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(SediaLPRValidator.class);
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
        kodNegeri = conf.getProperty("kodNegeri");
        Permohonan permohonan = context.getPermohonan();
        try {

            FasaPermohonan mohonFasa = new FasaPermohonan();
            Permohonan mohonRMHS = new Permohonan();
            Permohonan mohonRTPS = new Permohonan();

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

            //ida edit~~~~
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
                List<HakmilikPermohonan> listHMohon = new ArrayList<HakmilikPermohonan>();
                if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                    LOG.info("----------HakmilikPermohonan List size--------------: " + getSenaraiHakmilikPermohonan().size());
                    int k = 0;
                    for (HakmilikPermohonan hmMohon : senaraiHakmilikPermohonan) {
                        k++;
                        LOG.info("----------Id Mohon Hakmilik--------------: " + k + ". " + hmMohon.getId());
                        PermohonanStrata mhnStrata = strService.findIDByidMH(permohonan.getIdPermohonan(), hmMohon.getId());
                        LOG.info("----------mhnStrata--------------: " + mhnStrata);
                        LaporanTanah lprTanah = strService.findLaporanTanahByIdMH(permohonan.getIdPermohonan(), hmMohon.getId());
                        LOG.info("----------lprTanah--------------: " + lprTanah);
                        if (lprTanah == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan maklumat " + hmMohon.getHakmilik().getIdHakmilik() + " di tab 'Laporan Tanah'");
                            return null;
                        }
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

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }
}
