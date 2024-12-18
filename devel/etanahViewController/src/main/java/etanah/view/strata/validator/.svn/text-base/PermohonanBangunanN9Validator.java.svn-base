
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJabatan;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.IntegrasiPermohonanDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KodDokumen;
import etanah.model.KodTujuanUkur;
import etanah.model.PermohonanSkim;
import etanah.util.DMSUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author murali
 */
public class PermohonanBangunanN9Validator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    private PermohonanStrata pemilik;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(PermohonanBangunanN9Validator.class);
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    private Hakmilik hakmilik;
    private String idHakmilik;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrasiPermohonanButirDAO;
    @Inject
    IntegrasiPermohonanDokumenDAO integrasiPermohonanDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String[] kodurs = {"PBBS", "PBBD", "PBS", "PBTS", "PBBSS"};
    private String[] kodDOC = {"ALJ", "PBN", "PLK", "PTG", "SJT", "SPL", "JPP"};
    private String[] kodDOC2 = {"ALJ", "PBN", "PLK", "PTG", "SJT", "SPL", "JPP", "KJL", "AJB", "PBT", "RBU", "STR1", "STR1A"};
    private InfoAudit infoAudit;
    @Inject
    private etanah.Configuration conf;
    private String kodNegeri;
    private List<Dokumen> dokumen;
    private String pathLoc = "";

    public List<Dokumen> getDokumen() {
        return dokumen;
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

    public String[] getKodurs() {
        return kodurs;
    }

    public void setKodurs(String[] kodurs) {
        this.kodurs = kodurs;
    }

    public String[] getKodDOC() {
        return kodDOC;
    }

    public void setKodDOC(String[] kodDOC) {
        this.kodDOC = kodDOC;
    }

    public String[] getKodDOC2() {
        return kodDOC2;
    }

    public void setKodDOC2(String[] kodDOC2) {
        this.kodDOC2 = kodDOC2;
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
        pemilik = strService.findID(permohonan.getIdPermohonan());
        mc = strService.findBdn(permohonan.getIdPermohonan());
        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");

        try {

//            if (permohonan.getSenaraiBangunan().isEmpty()) {
//
//                context.addMessage("Sila masukkan maklumat jadual petak : " + permohonan.getIdPermohonan());
//                return null;
//            } else if (permohonan.getSenaraiPemohon().isEmpty()) {
//                context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
//                return null;
//            } else if (pemilik == null) {
//                context.addMessage("Sila masukkan maklumat pemilik  : " + permohonan.getIdPermohonan());
//                return null;
//            } else if (mc == null) {
//                context.addMessage("Sila masukkan maklumat bangunan : " + permohonan.getIdPermohonan());
//                return null;
//            }

            kodNegeri = conf.getProperty("kodNegeri");
            // Ida update on 13 may 2013

            Pengguna peng = (Pengguna) context.getPengguna();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());


            /* Insert in Stage g_semaklaporan */

            /*saving mohon_integ*/
            LOG.info("---saving in mohon_integ---");
            IntegrasiPermohonan integrasiPermohonan = new IntegrasiPermohonan();
            integrasiPermohonan.setPermohonan(permohonan);
            integrasiPermohonan.setKodUrusan(permohonan.getKodUrusan());
            integrasiPermohonan.setIdAliran("g_semaklaporan"); //dapat dari mana?
            integrasiPermohonan.setIdAliranTerima("g_sediakertasptg"); //dapat dari mana? buat mana ni hardcode dulu
            integrasiPermohonan.setInfoAudit(infoAudit);
            integrasiPermohonan = integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);

            /*saving mohon_integ_butir*/
            LOG.info("---saving in mohon_integ_butir---");
            IntegrasiPermohonanButir integrasiPermohonanButir = new IntegrasiPermohonanButir();
            integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
            integrasiPermohonanButir.setNamaFolderHantar(integrasiPermohonan.getIdAliran());
            integrasiPermohonanButir.setNamaFolderTerima(integrasiPermohonan.getIdAliranTerima());
            integrasiPermohonanButir.setInfoAudit(infoAudit);
            integrasiPermohonanButir = integrasiPermohonanButirDAO.saveOrUpdate(integrasiPermohonanButir);

            /*saving mohon_integ_dokumen*/

            LOG.info("---saving in mohon_integ_dokumen---");
            IntegrasiPermohonanDokumen integrasiPermohonanDokumen = new IntegrasiPermohonanDokumen();
            integrasiPermohonanDokumen.setIntegrasiPermohonanButir(integrasiPermohonanButir);
            integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
            integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
            integrasiPermohonanDokumen.setInfoAudit(infoAudit);
            integrasiPermohonanDokumen = integrasiPermohonanDokumenDAO.saveOrUpdate(integrasiPermohonanDokumen);


            /* Insert in Stage g_semakpelan */

            /*saving mohon_integ*/
            LOG.info("---saving in mohon_integ---");
            integrasiPermohonan = new IntegrasiPermohonan();
            integrasiPermohonan.setPermohonan(permohonan);
            integrasiPermohonan.setKodUrusan(permohonan.getKodUrusan());
            integrasiPermohonan.setIdAliran("g_semakpelan"); //dapat dari mana?
            integrasiPermohonan.setIdAliranTerima("g_terimapab"); //dapat dari mana? buat mana ni hardcode dulu
            integrasiPermohonan.setInfoAudit(infoAudit);
            integrasiPermohonan = integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);

            /*saving mohon_integ_butir*/
            LOG.info("---saving in mohon_integ_butir---");
            integrasiPermohonanButir = new IntegrasiPermohonanButir();
            integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
            integrasiPermohonanButir.setNamaFolderHantar(integrasiPermohonan.getIdAliran());
            integrasiPermohonanButir.setNamaFolderTerima(integrasiPermohonan.getIdAliranTerima());
            integrasiPermohonanButir.setInfoAudit(infoAudit);
            integrasiPermohonanButir = integrasiPermohonanButirDAO.saveOrUpdate(integrasiPermohonanButir);

            /*saving mohon_integ_dokumen*/

            LOG.info("---saving in mohon_integ_dokumen---");
            integrasiPermohonanDokumen = new IntegrasiPermohonanDokumen();
            integrasiPermohonanDokumen.setIntegrasiPermohonanButir(integrasiPermohonanButir);
            integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
            integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
            integrasiPermohonanDokumen.setInfoAudit(infoAudit);
            integrasiPermohonanDokumen = integrasiPermohonanDokumenDAO.saveOrUpdate(integrasiPermohonanDokumen);

            // end insert in table GIS

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

            if (permohonan.getKodUrusan().getKod().equals("PBBD") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                mohonRMHS = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "RMH1A");
                if (mohonRMHS != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonRMHS.getIdPermohonan(), "keputusan");
                    Map m = tds.traceTask(mohonRMHS.getIdPermohonan());
                    String user = (String) m.get("participant");
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan Dan Tanah\n\n<br>"
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
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
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
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
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
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan \n\n<br>"
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


            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
            if (permohonan != null) {
                KodKeputusan kodkpsn = kodKeputusanDAO.findById("Z1");
                if (kodkpsn != null) {
                    permohonan.setKeputusan(kodkpsn);
                }
                //Pengguna peng = (Pengguna) context.getPengguna();
                permohonan.setKeputusanOleh(peng);
                permohonan.setTarikhKeputusan(new Date());
                strService.updateMohon(permohonan);
            }

            //Pengguna peng = (Pengguna) context.getPengguna();
            //InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
                List<Hakmilik> senaraiHakmilik1 = new ArrayList<Hakmilik>();
                KodUrusan kod = new KodUrusan();
                for (HakmilikPermohonan hakmilikPermohonan : hk) {
                    LOG.info("--hk size--" + hk.size());
                    LOG.info("--adding Hakmilik to senaraiHakmilik1--" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    senaraiHakmilik1.add(hakmilikPermohonan.getHakmilik());
                    kod = kodUrusanDAO.findById("PBCTM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                }
                LOG.info("--senaraiHakmilik1 size--:" + senaraiHakmilik1.size());

                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik1, permohonan);
                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());


                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());

                //ida update 26/06/2013
                if (conf.getProperty("kodNegeri").equals("05")) {
                    //permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setCawangan(senaraiHakmilik1.get(0).getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                }
                permohonanRujLuar.setPermohonan(mohonReg);
                //ida update 29/04/2013
                // HakmilikPermohonan hakmilikPermohonan;
                permohonanRujLuar.setHakmilik(senaraiHakmilik1.get(0));
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);

            } else {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                String[] name = {"idHakmilik"};
                Object[] value = {idHakmilik};
                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                KodUrusan kod = kodUrusanDAO.findById("PBBM");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                //Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                
                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());

                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());

                //ida update 26/06/2013
                if (conf.getProperty("kodNegeri").equals("05")) {
                    //permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setCawangan(senaraiHakmilik.get(0).getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                }

                //permohonanRujLuar.setCawangan(peng.getKodCawangan());
                permohonanRujLuar.setPermohonan(mohonReg);
                //ida update 29/04/2013
                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
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
