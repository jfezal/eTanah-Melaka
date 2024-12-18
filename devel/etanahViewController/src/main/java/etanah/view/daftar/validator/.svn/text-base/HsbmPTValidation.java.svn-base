/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.Dokumen;
import etanah.model.Permohonan;
import etanah.model.FolderDokumen;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodRujukanDAO;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import etanah.service.common.HakmilikPermohonanService;
import etanah.model.KodDokumen;
import java.io.File;
import etanah.report.ReportUtil;
import etanah.model.Pengguna;
import etanah.dao.PenggunaDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsalPermohonan;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.model.KandunganFolder;
import etanah.model.InfoAudit;
import etanah.service.SemakDokumenService;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.CallableReport;
import etanah.service.RegService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import org.apache.commons.math.fraction.Fraction;
import etanah.service.ReportName;
import etanah.service.StrataPtService;
import etanah.service.daftar.KutipanDataService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.ArrayUtils;

/**
 * Listener To Generate Report
 *
 * @ PT For urusan MH
 * @author khairil
 */
public class HsbmPTValidation implements StageListener {

    private static final Logger logger = Logger.getLogger(HsbmPTValidation.class);
//    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    etanah.Configuration conf;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KutipanDataService kutipanDataService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    ReportUtil reportUtil, reportUtil2;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    RegService regService;
    @Inject
    HakmilikPihakKepentinganService hpkService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportName reportName;
    Fraction sum = new Fraction(0, 1);
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen d = null;
        Dokumen e = null;
        Dokumen f = null;
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String[] params3 = null;
        String[] values3 = null;
        String path = "";
        String path2 = "";
        String path3 = "";
        String gen2 = "";
        String gen3 = "";
        KodDokumen kd = new KodDokumen();
        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();
        KodDokumen kd8 = new KodDokumen();
        String noVersi = "";

        //added on 07/08/2012
        Dokumen g = null;
        Dokumen h = null;
        Dokumen i = null;
        Dokumen j = null;
        Dokumen k = null;
        String[] params2 = null;
        String[] values2 = null;

        String path4 = "";
        String path5 = "";
        String path6 = "";
        String path7 = "";
        String gen4 = "";
        String gen5 = "";
        String gen6 = "";
        String gen7 = "";

        KodUrusan ku = permohonan.getKodUrusan();

        String[] urusanConvert = {"HSTHK", "HKTHK", "HMSC"};

        if (ArrayUtils.contains(urusanConvert, ku.getKod())) {

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

            List<HakmilikPermohonan> hmk = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : hmk) {
                Hakmilik hm = hp.getHakmilik();

                if (hm == null) {
                    continue;
                }
                if (!ku.getKod().equals("HSTHK") && !ku.getKod().equals("HKTHK")) {
                    hm.setTarikhDaftar(new java.util.Date());
                    if (hm.getPegangan() != null) {
                        if (hm.getPegangan() == 'P') {

                            Calendar calendar = new GregorianCalendar();
                            calendar.setTime(hm.getTarikhDaftar());
                            /*CALC BASED ON DAY OF YEAR*/
                            //calendar.add(Calendar.DAY_OF_YEAR, tempohPegangan * 365);
                            calendar.add(Calendar.YEAR, hm.getTempohPegangan() * 1);
                            calendar.add(Calendar.DAY_OF_MONTH, -1);
                            Date tarikhLuput = calendar.getTime();
                            logger.debug("tarikhLuput : " + tarikhLuput);
                            hm.setTarikhLuput(tarikhLuput);

                        } else {
                            hm.setTempohPegangan(0);
                            hm.setTarikhLuput(null);
                        }
                    }
                }

                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
                gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
                gen3 = "regBorangHMDHDE.rdf"; //DHDE report name

                // generate DHKE hakmilik baru
                kd2.setKod("DHKE");
                e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context, 0);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());

//                syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
                Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
                //                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                File sign = new File(dokumenPath + path2 + ".sig");
                if (sign.exists()) {
                    sign.delete();
                }

                //generat DHDE hakmilik baru
                kd3.setKod("DHDE");
                f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context, 0);
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//                syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
                Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
                //                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                sign = new File(dokumenPath + path3 + ".sig");
                if (sign.exists()) {
                    sign.delete();
                }

                logger.debug("Waiting for reports to complete...");
                try {
                    dhke.get();
                } catch (Exception ex) {
                    logger.debug(ex.getMessage(), ex);
                }
                try {
                    dhde.get();
                } catch (Exception ex) {
                    logger.debug(ex.getMessage(), ex);
                }
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen());

                //save to mohon hakmilikLogic.
                hp.setDokumen2(e);
                hm.setDhke(e);
                hp.setDokumen3(f);
                hm.setDhde(f);

                if (hm.getKodHakmilik().getKod().equals("PN")
                        || hm.getKodHakmilik().getKod().equals("GRN")
                        || hm.getKodHakmilik().getKod().equals("GM")
                        || hm.getKodHakmilik().getKod().equals("GMM")
                        || hm.getKodHakmilik().getKod().equals("PM")) {
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //b1 n9
                        gen2 = "REG_BorangB1eNS.rdf";
                        gen3 = "REG_BorangB1eNS_DHKe.rdf";
                    } else {
                        //b1 melaka
                        gen2 = "REG_BorangB1eMLK.rdf";
                        gen3 = "REG_BorangB1eMLK_DHKe.rdf";
                    }
                    kd2.setKod("PB1DE");
                    kd3.setKod("PB1KE");

                } else {
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //b2 n9
                        gen2 = "REG_BorangB2eNS.rdf";
                        gen3 = "REG_BorangB2eNS_DHKe.rdf";
                    } else {
                        //b2 melaka
                        gen2 = "REG_BorangB2eMLK.rdf";
                        gen3 = "REG_BorangB2eMLK_DHKe.rdf";
                    }
                    kd2.setKod("PB2DE");
                    kd3.setKod("PB2KE");
                }
                //gen Pelan DHDE
//                logger.debug("START GENERATE PELAN DHDE");
                e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context, hmk.size());
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());

//                syslog.info(peng.getIdPengguna() + " generate report " + gen2 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                logger.debug("::saving pelan into mohon hakmilik::");
                hp.setDokumen5(e);
                hm.setPelan(e);

                //gen Pelan DHKE
//                logger.debug("START GENERATE PELAN DHKE");
                f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context, hmk.size());
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//                syslog.info(peng.getIdPengguna() + " generate report " + gen3 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
                logger.debug("::saving pelan into mohon hakmilik::");
                hp.setDokumen6(f);

                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
                hakmilikDAO.save(hm);
            }
        } else if (!permohonan.getKodUrusan().getKod().equals("HT")
                && !permohonan.getKodUrusan().getKod().equals("HTSPV")
                && !permohonan.getKodUrusan().getKod().equals("HTPV")
                && !permohonan.getKodUrusan().getKod().equals("HTT")
                && !permohonan.getKodUrusan().getKod().equals("HTSC")
                && !permohonan.getKodUrusan().getKod().equals("HTSPB")
                && !permohonan.getKodUrusan().getKod().equals("HTSPS")) {

            List<HakmilikPermohonan> hmk = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : hmk) {
                Hakmilik hm = hp.getHakmilik();

                if (ku.getKod().equals("HKGHS") || ku.getKod().equals("HKSTK")) {
                    List<HakmilikAsalPermohonan> mohonHakmilikAsal = regService.findIdHakmilikAsalMohonByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikAsalPermohonan mha : mohonHakmilikAsal) {
                        if (!mha.getHakmilikSejarah().equals(null)) {
                            List<Hakmilik> hakmilik1 = regService.searchHakmilikByidHakmilik(mha.getHakmilikSejarah());
                            for (Hakmilik hm1 : hakmilik1) {
                                hm.setTarikhDaftar(hm1.getTarikhDaftar());
                            }
                        }
                    }
                }
                Date td = hm.getTarikhDaftar();
                logger.debug("tarikhDaftar : " + td);
            }

            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hakmilikPermohonan : hk) {
                logger.info("hakmilik ::" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                //TODO : to add idhakmilik in params
//            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//            values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                params = new String[]{"p_id_hakmilik", "p_id_mohon"};

                values = new String[]{hakmilikPermohonan.getHakmilik().getIdHakmilik(), idPermohonan.trim()};
//            if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().startsWith("HK")) {
                if (hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PN")
                        || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GRN")
                        || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GM")
                        || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GMM")
                        || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PM") //                || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GRS")
                        //                || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PNS")
                        ) {
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //b1 n9
                        gen2 = "REG_BorangB1eNS.rdf";
                        gen3 = "REG_BorangB1eNS_DHKe.rdf";
                    } else {
                        //b1 melaka
                        gen2 = "REG_BorangB1eMLK.rdf";
                        gen3 = "REG_BorangB1eMLK_DHKe.rdf";
                    }
                    kd2.setKod("PB1DE");
                    kd3.setKod("PB1KE");

                } else {
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //b2 n9
                        gen2 = "REG_BorangB2eNS.rdf";
                        gen3 = "REG_BorangB2eNS_DHKe.rdf";
                    } else {
                        //b2 melaka
                        gen2 = "REG_BorangB2eMLK.rdf";
                        gen3 = "REG_BorangB2eMLK_DHKe.rdf";
                    }
                    kd2.setKod("PB2DE");
                    kd3.setKod("PB2KE");
                }
                //gen Pelan DHDE
                logger.debug("START GENERATE PELAN DHDE");
                e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//                syslog.info(peng.getIdPengguna() + " generate report " + gen2 + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
//                logger.debug("::saving pelan into mohon hakmilik::");
                hakmilikPermohonan.setDokumen5(e);
                hakmilik.setPelan(e);

                //gen Pelan DHKE
                logger.debug("START GENERATE PELAN DHKE");
                f = saveOrUpdateDokumen(fd, kd3, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//                syslog.info(peng.getIdPengguna() + " generate report " + gen3 + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
//                logger.debug("::saving pelan into mohon hakmilik::");
                hakmilikPermohonan.setDokumen6(f);


                /*generate carian rasmi hakmilik tidak berkuatkuasa untuk urusan HKTHK dan HSTHK (melaka)*/
                logger.debug("START GENERATE CARIAN RASMI HAKMILIK~~~");
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {

                        kd8.setKod("CRHMT");
                        gen7 = "regSijilCarianHMMakTakKuasamlDoc.rdf";
                        params3 = new String[]{"p_id_hakmilik"};
                        values3 = new String[]{hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                        k = saveOrUpdateDokumen(fd, kd8, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                        path7 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());;
//                        syslog.info(peng.getIdPengguna() + " generate report " + gen7 + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path7);
                        reportUtil.generateReport(gen7, params3, values3, dokumenPath + path7, peng);

                        updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());

                        hakmilikPermohonan.setDokumen2(k);

                        logger.debug("saving dokumen : " + k.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                        hakmilikService.saveHakmilik(hakmilik);

                    }

                }

                /*TODO*/
                //gen DHKK
                logger.debug("::MASUK SINI~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ :: ");
                String gen = "";
                kd.setKod("DDHDK");
//        kd.setKod("CRHMT");

                if (conf.getProperty("kodNegeri").equals("05")) {
                    //drafhakmilik n9
                    gen = "REGDrafDokHMNS001.rdf";
                } else {
                    //drafhakmilik melaka
                    gen = "REGDrafDokHMMLK001.rdf";
//          gen="regSijilCarianHMMakTakKuasaml.rdf";
                }

                d = saveOrUpdateDokumen(fd, kd, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                logger.info("::Path To save :: " + path);
                logger.info("::Report Name ::" + gen);
//                syslog.info(peng.getIdPengguna() + " generate report " + gen + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path);
                reportUtil.generateReport(gen, params, values, dokumenPath + path, peng);

                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

                hakmilikPermohonan.setDokumen1(d);
                if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
                    hakmilik.setDhke(d);
                }
//                logger.debug("saving dokumen : " + d.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                hakmilikService.saveHakmilik(hakmilik);

                logger.debug("::Habis SINI~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ :: ");

                if (permohonan.getKodUrusan().getKod().equals("HT") && permohonan.getKodUrusan().getKod().equals("HTSC")
                        && permohonan.getKodUrusan().getKod().equals("HTSPB") && !permohonan.getKodUrusan().getKod().equals("HTSPS")
                        && permohonan.getKodUrusan().getKod().equals("HTSPV") && !permohonan.getKodUrusan().getKod().equals("HKTHK")
                        && !permohonan.getKodUrusan().getKod().equals("HSTHK")
                        && permohonan.getKodUrusan().getKod().equals("HTT")) {

                    kd.setKod("DDHDK");

                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //drafhakmilik n9
                        gen = "REGDrafDokHMNS001.rdf";
                    } else {
                        //drafhakmilik melaka
                        gen = "REGDrafDokHMMLK001.rdf";
                    }

                    d = saveOrUpdateDokumen(fd, kd, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    logger.info("::Path To save :: " + path);
//                    logger.info("::Report Name ::" + gen);
//                    syslog.info(peng.getIdPengguna() + " generate report " + gen + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path);
                    reportUtil.generateReport(gen, params, values, dokumenPath + path, peng);

                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

                    hakmilikPermohonan.setDokumen1(d);
                    if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
                        hakmilik.setDhke(d);
                    }
                    logger.debug("saving dokumen : " + d.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikService.saveHakmilik(hakmilik);

                }
                if (permohonan.getKodUrusan().getKod().equals("HKTP")) {

                    kd.setKod("DDHDK");

                    if (conf.getProperty("kodNegeri").equals("05")) {
                        //drafhakmilik n9
                        gen = "REGDrafDokHMNS001.rdf";
                    } else {
                        //drafhakmilik melaka
                        gen = "REGDrafDokHMMLK001.rdf";
                    }
                    d = saveOrUpdateDokumen(fd, kd, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    logger.info("::Path To save :: " + path);
                    logger.info("::Report Name ::" + gen);
//                    syslog.info(peng.getIdPengguna() + " generate report " + gen + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path);
                    reportUtil.generateReport(gen, params, values, dokumenPath + path, peng);

                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

                    hakmilikPermohonan.setDokumen1(d);
                    if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
                        hakmilik.setDhke(d);
                    }
                    logger.debug("saving dokumen : " + d.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikService.saveHakmilik(hakmilik);

                }

                // Comment out on 11/11/2013: don't need to generate this here
//                if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
//                    kd.setKod("DHDEC");
//                    gen = reportName.getDHDEReportName();
//                    d = saveOrUpdateDokumen(fd, kd, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context, hk.size());
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    logger.info("::Path To save :: " + path);
//                    logger.info("::Report Name ::" + gen);
//                    syslog.info(peng.getIdPengguna() + " generate report " + gen + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path);
//                    reportUtil.generateReport(gen, params, values, dokumenPath + path, peng);
//
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//
//                    hakmilikPermohonan.setDokumen1(d);
//                    if (permohonan.getKodUrusan().getKod().equals("HKTHK") || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
//                        hakmilik.setDhke(d);
//                    }
//                    logger.debug("saving dokumen : " + d.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
//                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//                    hakmilikService.saveHakmilik(hakmilik);
//                }
//            else {
//                /*SET DOKUMEN UTK HAKMILIK STRATA*/
//                kd.setKod("4K");
//                if (conf.getProperty("kodNegeri").equals("05")) {
//                    //drafhakmilik n9
//                    gen = "STR_Borang4K.rdf";
//                } else {
//                    //drafhakmilik melaka
//                    gen = "STR_Borang4K.rdf";
//                }
//            }
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("HT")
                || permohonan.getKodUrusan().getKod().equals("HTPV")
                || permohonan.getKodUrusan().getKod().equals("HTSPV")
                || permohonan.getKodUrusan().getKod().equals("HTSC")
                || permohonan.getKodUrusan().getKod().equals("HTSPB")
                || permohonan.getKodUrusan().getKod().equals("HTSPS")) {
            logger.info("--KOD URUSAN--HT/HTPV/HTSPV--::");
            logger.info("--generating Reports--::");
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            logger.info("--HakmilikPermohonan--List--::" + hk);
            KodDokumen kd4 = new KodDokumen();
            KodDokumen kd5 = new KodDokumen();
            KodDokumen kd6 = new KodDokumen();
            KodDokumen kd7 = new KodDokumen();

            logger.info("--Hakmilik--::" + hk.get(0).getHakmilik().getIdHakmilik());
            Hakmilik hkinduk = hakmilikDAO.findById(hk.get(0).getHakmilik().getIdHakmilik());
            logger.info("--Hakmilik hkinduk--::" + hkinduk);
            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};
            logger.info("--Generating B2K, B3K--::");
            if (conf.getProperty("kodNegeri").equals("05")) {
                gen2 = "STRDrafB2K_NS.rdf";
                gen3 = "STRDrafB3K_NS.rdf";
                gen5 = "REGDrafDokHMSTRNS.rdf";
            } else {
                gen2 = "B2K_MLK.rdf";
                gen3 = "B3K_MLK.rdf";
                gen5 = "REGDrafDokHMMLK001.rdf";
            }
            /* Commented @NS 10-09-2012
             kd2.setKod("2K");
             e = saveOrUpdateDokumen1(fd, kd2, hkinduk.getIdHakmilik(), context);
             path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
             logger.info("::Path To save :: " + path2);
             logger.info("::Report Name ::" + gen2);
             reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
             updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());

             kd3.setKod("3K");
             f = saveOrUpdateDokumen1(fd, kd3, hkinduk.getIdHakmilik(), context);
             path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
             logger.info("::Path To save :: " + path3);
             logger.info("::Report Name ::" + gen3);
             reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
             updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
             logger.info("--Generated B2K, B3K--::");
             */

//            
            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("HT")
                        || permohonan.getKodUrusan().getKod().equals("HTPV")
                        || permohonan.getKodUrusan().getKod().equals("HTSPV")
                        || permohonan.getKodUrusan().getKod().equals("HTSC")
                        || permohonan.getKodUrusan().getKod().equals("HTSPB")
                        || permohonan.getKodUrusan().getKod().equals("HTSPS")) {
                    // for (int k = 0; k < 1; k++) {
                    HakmilikPermohonan hp = strService.findMohonHakmilikAsc(idPermohonan);
                    i = new Dokumen();
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hp.getHakmilik().getIdHakmilik()};
                    logger.info("--Generating VDOCS--::");
//            gen5 = "REGDrafDokHMNS001.rdf";

                    kd6.setKod("VDOCS");
                    i = saveOrUpdateDokumen1(fd, kd6, hp.getHakmilik().getIdHakmilik(), context);
                    path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen());
                    logger.info("::Path To save :: " + path5);
                    logger.info("::Report Name ::" + gen5);
//                    syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hp.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
                    reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), i.getIdDokumen());
                    logger.info("--Generated VDOCS--::");
                    // }
                }

//                else if (permohonan.getKodUrusan().getKod().equals("HTPV")
//                        || permohonan.getKodUrusan().getKod().equals("HTSPV")
//                        || permohonan.getKodUrusan().getKod().equals("HTSC")
//                        || permohonan.getKodUrusan().getKod().equals("HTSPB")
//                        || permohonan.getKodUrusan().getKod().equals("HTSPS")) {
//                    for (HakmilikPermohonan hp : hk) {
//                        i = new Dokumen();
//                       
//                        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                        values = new String[]{idPermohonan.trim(), hp.getHakmilik().getIdHakmilik()};
//                        logger.info("--Generating DHKK--::");
////            gen5 = "REGDrafDokHMNS001.rdf";
//
//                        kd6.setKod("VDOCS");
//                        i = saveOrUpdateDokumen1(fd, kd6, hp.getHakmilik().getIdHakmilik(), context);
//                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen());
//                        logger.info("::Path To save :: " + path5);
//                        logger.info("::Report Name ::" + gen5);
//                        syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hp.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
//                        reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
//                        updatePathDokumen(reportUtil.getDMSPath(), i.getIdDokumen());
//                        logger.info("--Generated VDOCS--::");
//                    }
//                }
            } else {
                for (HakmilikPermohonan hp : hk) {
                    i = new Dokumen();
                    params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                    values = new String[]{idPermohonan.trim(), hp.getHakmilik().getIdHakmilik()};
                    logger.info("--Generating DHKK--::");
//            gen5 = "REGDrafDokHMNS001.rdf";

                    kd6.setKod("DHKK");
                    i = saveOrUpdateDokumen1(fd, kd6, hp.getHakmilik().getIdHakmilik(), context);
                    path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen());
                    logger.info("::Path To save :: " + path5);
                    logger.info("::Report Name ::" + gen5);
//                    syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hp.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
                    reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), i.getIdDokumen());
                    logger.info("--Generated DHKK--::");
                }
            }

            /* Commented @NS 10-09-2012
             logger.info("--Generating 4k--::");
             gen6 = "STRDrafB4K_NS.rdf";
             kd5.setKod("4K");
             h = saveOrUpdateDokumen1(fd, kd5, hkinduk.getIdHakmilik(), context);
             path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
             logger.info("::Path To save :: " + path6);
             logger.info("::Report Name ::" + gen6);
             syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path6);
             reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
             updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());
             logger.info("--Generated 4k--::");

             logger.info("--Generating BSK--::");
             gen4 = "STRDrafBSK_NS.rdf";
             kd7.setKod("BSK");
             f = saveOrUpdateDokumen1(fd, kd7, hkinduk.getIdHakmilik(), context);
             path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
             logger.info("::Path To save :: " + path4);
             logger.info("::Report Name ::" + gen4);
             //            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
             reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
             updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
             logger.info("--Generated BSK--::");
             */
//            for (HakmilikPermohonan hakmilikPermohonan : hk) {
//
//                /*TODO SET TARIKH DAFTAR AND TARIKH LUPUT FOR HSBM AND HKBM  AFTER PENDAFTAR DAFTAR*/
//                //Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
//                String idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
//                logger.info("--Hakmilik idHakmilik--::" + idHakmilik);
//                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
//                hakmilik.setTarikhDaftar(new java.util.Date());
//                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//                values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
//
//                logger.info("--Generating B4k, BSK--::");
//                if (conf.getProperty("kodNegeri").equals("05")) {
////                    gen2 = "B2K_NS.rdf";
////                    gen3 = "B3K_NS.rdf";
////                    gen5 = "B4K_NS.rdf";
//                    //gen5 = "STRB4K_NS.rdf";
//                    gen5 = "STRDrafB4K_NS.rdf";
//                    //gen6 = "STRBSK_NS.rdf";
//                    gen6 = "STRDrafBSK_NS.rdf";
//                } else {
////                    gen2 = "B2K_MLK.rdf";
////                    gen3 = "B3K_MLK.rdf";
//                    gen5 = "B4K_MLK.rdf";
//
//                    gen6 = "STRBorangSK_MLK.rdf";
//                }
//
//                /*Borang 4K untuk strata*/
//                kd5.setKod("4K");
//                h = saveOrUpdateDokumen1(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
//                path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
//                logger.info("::Path To save :: " + path5);
//                logger.info("::Report Name ::" + gen5);
//                syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
//                reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());
//
//
//                kd6.setKod("BSK");
//                f = saveOrUpdateDokumen1(fd, kd6, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
//                path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//                logger.info("::Path To save :: " + path6);
//                logger.info("::Report Name ::" + gen6);
////            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
//                reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
//
//                logger.info("--Generated B4k, BSK--::");
//
//                logger.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
////                logger.debug("saving dokumen : " + g.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
//                logger.debug("saving dokumen : " + h.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
//                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//                hakmilikDAO.save(hakmilik);
//            }
        } //END
    }

    //added on 07/08/2012 :START
    private Dokumen saveOrUpdateDokumen1(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    //END
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context, int sizeHakmilik) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        String[] tmp = id.split(",");
        String idT = "";
        if (tmp.length > 0) {
            idT = tmp[0];
        } else {
            idT = id;
        }

        logger.debug("idT :" + idT);
        doc = semakDokumenService.semakDokumen(kd, fd, idT);
        if (doc == null) {
            logger.debug("doc baru");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");

        } else {
            logger.debug("doc lama");
            //doc = new Dokumen();
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            logger.debug("no versi lama : " + doc.getNoVersi());
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            if (!kd.getKod().equals("DDHDK")) 
            doc.setNoVersi(String.valueOf(noVersi + 1));
            
            else
            doc.setNoVersi("1.0");    
            
            logger.debug("no versi baru : " + noVersi);
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        //doc.setKlasifikasi(klasifikasi_SULIT);

//    if (sizeHakmilik > 1) {
//      doc.setTajuk(kd.getKod() + " (" + id + ")");
//    } else {
//      doc.setTajuk(kd.getKod());
//    }
        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void updateVersiDrafDok(String noVersi, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNoVersi(noVersi);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        logger.debug("Starting beforeComplete Listener");
        logger.debug("proposedOutcome :" + proposedOutcome);
        Permohonan permohonan = context.getPermohonan();
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();
        try {
            if (permohonan.getSenaraiHakmilik().isEmpty()) {
                context.addMessage("Sila masukkan Sekurang-kurangnya satu hakmilik yang terlibat");
                return null;
            }
//            if (!permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")
//                && !permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")){
//            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
//                Hakmilik hk = hakmilikPermohonan.getHakmilik();
//                List<HakmilikPihakBerkepentingan> lhpb = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hk);
//                //List<HakmilikPihakBerkepentingan> lhpb = regService.searchPihakBerKepentinganPemilik(hk.getIdHakmilik());
//                if (lhpb.size() == 0) {
//                    context.addMessage("Sila masukkan sekurang-kurang nya satu pemilik");
//                    return null;
//                }
//                for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhpb) {
//                    if (hakmilikPihakBerkepentingan.getSyerPembilang() == 0 || hakmilikPihakBerkepentingan.getSyerPenyebut() == 0) {
//                        context.addMessage("Sila masukkan syer/bahagian bagi permohonan : " + permohonan.getIdPermohonan());
//                        return null;
//                    }
//                    Fraction f = new Fraction(hakmilikPihakBerkepentingan.getSyerPembilang(), hakmilikPihakBerkepentingan.getSyerPenyebut());
//                    // logger.debug("f" + i + ":" + f);
//                    sum = sum.add(f);
//                    logger.debug("syerPembilang" + hakmilikPihakBerkepentingan.getSyerPembilang());
//                    logger.debug("syerPenyebut" + hakmilikPihakBerkepentingan.getSyerPenyebut());
////                    hakmilikPihakBerkepentingan.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
////                    hakmilikPihakBerkepentingan.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
////                    pihakBerkepentingan.add(hakmilikPihakBerkepentingan);
//
//                }
//                logger.debug("sum :" + sum);
//                if (sum.doubleValue() == 1) {
//                    //permohonanPihakService.saveOrUpdate(mohonPihak);
//                    //hakmilikPihakKepentinganService.save(pihakBerkepentingan);
//                } else if (sum.doubleValue() < 1) {
//                    //result = "Jumlah pembahagian tanah tidak mencukupi";
//                    context.addMessage("Jumlah pembahagian tanah tidak mencukupi untuk : " + hk.getIdHakmilik());
//                    return null;
//                } else {
//                    //result = "Jumlah pembahagian tanah melebihi daripada 1";
////                    context.addMessage("Jumlah pembahagian tanah melebihi daripada 1 untuk : " + hk.getIdHakmilik());
////                    return null;
//                }
//                sum = new Fraction(0, 1);
//
//            }
//            }

//            if (permohonan.getSenaraiPihak().isEmpty()) {
//                context.addMessage("Sila masukkan Tuan Tanah / Pihak : " + permohonan.getIdPermohonan());
//                return null;
//            }
//            for (HakmilikPermohonan hakmilikPermohonan : lhp) {
//                if (hakmilikPermohonan.getHakmilik().getSekatanKepentingan() == null) {
//                    context.addMessage("Sila masukkan Kod Sekatan : " + permohonan.getIdPermohonan());
//                    return null;
//                }
//            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext context) {
        logger.debug("Starting beforeGenerateReports Listener");
        Permohonan permohonan = context.getPermohonan();
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();
//        List<PermohonanPihak> lpp = permohonan.getSenaraiPihak();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        try {

            if (permohonan.getSenaraiHakmilik().isEmpty()) {
                context.addMessage("Sila masukkan sekurang-kurangnya satu hakmilik ");
                return false;
            }

            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("HT")) {
                    int noPAB = 0;

                    String idPermohonan = permohonan.getIdPermohonan();
                    List<HakmilikPermohonan> senaraiHakmilik2 = strService.findIdHakmilikByIdMH(idPermohonan);
                    for (HakmilikPermohonan hmilik : senaraiHakmilik2) {
                        Hakmilik hm = strService.findInfoByIdHakmilik(hmilik.getHakmilik().getIdHakmilik());
                        if (hm.getNoPelan() == null) {
                            noPAB = noPAB + 1;
                        }
                    }

                    if (noPAB != 0) {
                        context.addMessage("Sila isikan maklumat PA(B) dengan lengkap sebelum menjana dokumen.");
                        return false;
                    }
                }
            }

//            if (permohonan.getSenaraiPihak().isEmpty()) {
//                context.addMessage("Sila masukkan Tuan Tanah / Pihak : " + permohonan.getIdPermohonan());
//                return false;
//            } else {
//                for (PermohonanPihak permohonanPihak : lpp) {
//                    if (permohonanPihak.getSyerPembilang() == 0 || permohonanPihak.getSyerPenyebut() == 0) {
//                        context.addMessage("Sila masukkan syer/bahagian bagi permohonan : " + permohonan.getIdPermohonan());
//                        return false;
//                    }
//                }
//            }
            //This filter return null pointer exception to all MH urusan
//            if (!permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")
//                && !permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")){
            if (permohonan.getKodUrusan().getKod().equals("HKTHK")
                    || permohonan.getKodUrusan().getKod().equals("HSTHK")
                    || permohonan.getKodUrusan().getKod().equals("HMSC")) {

                Pengguna pengguna = context.getPengguna();
                InfoAudit ia = new InfoAudit();

                for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                    Hakmilik hk = hakmilikPermohonan.getHakmilik();
                    PermohonanRujukanLuar mrl = kutipanDataService.findMohonRujLuarByIdMohonAndHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
                    if (mrl == null) {
                        mrl = new PermohonanRujukanLuar();
                        mrl.setPermohonan(permohonan);
                        mrl.setHakmilik(hk);
                        mrl.setCawangan(hk.getCawangan());
                        mrl.setKodRujukan(kodRujukanDAO.findById("FL"));
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new Date());
                        mrl.setInfoAudit(ia);
                        kutipanDataService.saveMohonRujLuar(mrl);
                    }

                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                    hk.setInfoAudit(ia);
                    senaraiHakmilik.add(hk);
                }
            } else {
                for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                    Hakmilik hk = hakmilikPermohonan.getHakmilik();
                    List<HakmilikPihakBerkepentingan> lhpb = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hk);
                    //List<HakmilikPihakBerkepentingan> lhpb = regService.searchPihakBerKepentinganPemilik(hk.getIdHakmilik());
                    if (lhpb.size() == 0) {
                        context.addMessage("Sila masukkan sekurang-kurang nya satu pemilik");
                        return false;
                    }
                    for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhpb) {
                        logger.info("jenis pb : " + hakmilikPihakBerkepentingan.getJenis().getKod());
                        if (hakmilikPihakBerkepentingan.getSyerPembilang() != 0 || hakmilikPihakBerkepentingan.getSyerPenyebut() != 0) {
                            if (hakmilikPihakBerkepentingan.getSyerPembilang() == 0 || hakmilikPihakBerkepentingan.getSyerPenyebut() == 0) {
                                context.addMessage("Sila masukkan syer/bahagian bagi permohonan : " + permohonan.getIdPermohonan());
                                return false;
                            }

                            //validate only allow syer < 1 to generate report / selesai
                            Fraction f = new Fraction(hakmilikPihakBerkepentingan.getSyerPembilang(), hakmilikPihakBerkepentingan.getSyerPenyebut());
                            // logger.debug("f" + i + ":" + f);
                            sum = sum.add(f);
                            logger.debug("syerPembilang" + hakmilikPihakBerkepentingan.getSyerPembilang());
                            logger.debug("syerPenyebut" + hakmilikPihakBerkepentingan.getSyerPenyebut());
//                    hakmilikPihakBerkepentingan.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
//                    hakmilikPihakBerkepentingan.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
//                    pihakBerkepentingan.add(hakmilikPihakBerkepentingan);
                        }
                    }

                    for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhpb) {
                        if (!hakmilikPihakBerkepentingan.getJenis().getKod().equals("WPA")) {
                            logger.debug("sum :" + sum);
                            if (sum.doubleValue() == 1) {
                        //permohonanPihakService.saveOrUpdate(mohonPihak);
                                //hakmilikPihakKepentinganService.save(pihakBerkepentingan);
                            } else if (sum.doubleValue() < 1) {
                                //result = "Jumlah pembahagian tanah tidak mencukupi";
                                context.addMessage("Jumlah pembahagian tanah tidak mencukupi untuk " + hk.getIdHakmilik());
//                                return false;
                            } else {
                                //result = "Jumlah pembahagian tanah melebihi daripada 1";
//                    context.addMessage("Jumlah pembahagian tanah melebihi daripada 1 untuk " + hk.getIdHakmilik());
//                    return false;
                            }
                        }
                    }

                    if (hakmilikPermohonan.getHakmilik().getSekatanKepentingan() == null) {
                        context.addMessage("Sila masukkan Kod Sekatan : " + permohonan.getIdPermohonan());
                        return false;
                    }
                    sum = new Fraction(0, 1);

                    //temp solution !! integration PART
                    InfoAudit ia = hk.getInfoAudit();
                    if (ia != null) {
                        Pengguna pengguna = context.getPengguna();
                        if (pengguna != null) {
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new Date());
                            hk.setInfoAudit(ia);
                            senaraiHakmilik.add(hk);
                        }
                    }
                }
            }
//            }

            if (!senaraiHakmilik.isEmpty()) {
                hakmilikService.saveHakmilik(senaraiHakmilik);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            context.addMessage(e.getMessage());
            return false;
        }
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
