
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanStrata;
import etanah.model.gis.PelanGIS;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class SediaKertasValidator implements StageListener {

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
    private static final Logger LOG = Logger.getLogger(SediaKertasValidator.class);
    private String[] kodurs = {"PBBS", "PBBD", "PBS", "PBTS", "PBBSS"};
    private String namaSkim;

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

        String stage = "";
        FasaPermohonan mohonFasa = new FasaPermohonan();

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semaklaporan");
        if (mohonFasa != null) {
            stage = "H1";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_sediakertasptg");
        if (mohonFasa != null) {
            stage = "T1";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakpelan");
        if (mohonFasa != null) {
            stage = "H2";
        }

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_terimapab");
        if (mohonFasa != null) {
            stage = "T2";
        }
        PelanGIS p;
        PermohonanStrata mohonStrata = strService.findIDNamaSkim(permohonan.getIdPermohonan());
        LOG.info("-----mohonStrata.getNamaSkim()-----" + mohonStrata.getNamaSkim());
        LOG.info("-----stage-----" + stage);
        List<PelanGIS> lp = strService.findListPelanGISPKByNoSkim(mohonStrata.getNamaSkim(), stage);

        try {

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
                    if (dokPLK.getPerihal() != null && dokPLK.getPerihal().equals("1")) {
                        if (!lp.isEmpty()) {
                            if (dokPLK.getNamaFizikal() == null && dokPLK.getCatatanMinit() == null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Sila muat turun Dokumen dari JUPEM.");
                                return null;
                            } else if (dokPLK.getNamaFizikal() == null || dokPLK.getCatatanMinit() == null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Sila muat turun Dokumen dari JUPEM.");
                                return null;
                            }
                        } else {
                            context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Dokumen dari JUPEM masih belum diterima.");
                            return null;
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBBSS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                PermohonanKertas mohonKertas = strService.findIDKertasByIDMohon(permohonan.getIdPermohonan());
                if (mohonKertas == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan BIL dan Ulasan JUPEM");
                    return null;
                }
                List<PermohonanKertasKandungan> mohonKertasKand = strService.findKandunganByIDKertas(mohonKertas.getIdKertas());
                if (mohonKertasKand == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan BIL dan Ulasan JUPEM");
                    return null;
                }
                PermohonanKertasKandungan kertasKand = mohonKertasKand.get(0);
                if (mohonKertas.getNomborRujukan() == null || kertasKand.getKandungan() == null) {
                    context.addMessage("Sila masukkan BIL dan Ulasan JUPEM");
                    return null;
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
                    if (dokPLK.getPerihal() != null && dokPLK.getPerihal().equals("1")) {
                        if (!lp.isEmpty()) {
                            if (dokPLK.getNamaFizikal() != null && dokPLK.getCatatanMinit().equalsIgnoreCase("1")) {
                                LOG.info("Dokumen telah diterima. Proceed to the next stage");
                            }
                        } else {
                            context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Dokumen dari JUPEM masih belum diterima.");
                            return null;
                        }
                    } else {
                        LOG.info("Dokumen telah diterima. Proceed to the next stage");
                    }
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
//        return null;
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
