/**
 *
 * @author wan.fairul
 *
 */
package etanah.view.daftar.validator;

import etanah.report.CallableReport;
import com.google.inject.Inject;
import etanah.SYSLOG;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.report.ReportUtil;
import etanah.service.*;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PelarasanCukaiService;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.math.*;
import etanah.dao.PermohonanPembetulanHakmilikDAO;
import etanah.model.PermohonanPembetulanHakmilik;

public class PembetulanValidation implements StageListener {

    //inject
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RegService regService;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    ReportUtil reportUtil1;
    @Inject
    ReportUtil reportUtil2;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKemaskiniService;
    @Inject
    HakmilikWarisService hakmilikWarisService;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PembetulanService pService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    NotaService notaService;
    @Inject
    ReportName reportName;
    @Inject
    PelarasanCukaiService pcService;
    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    private KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    SejarahDokumenDAO sejarahDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    //Declaration
    private Pengguna pengguna;
    private FolderDokumen fd;
    private PermohonanPembetulanUrusan betulUrusan;
    private PermohonanPembetulanHakmilik permohonanHakmilikBetul;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private PermohonanRujukanLuar permohonanRujLuar;
    private List<HakmilikAsal> hakmilikAsalList;
    private List<HakmilikSebelum> hakmilikSebelumList;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanUrusan mohonUrusan;
    private Pemohon pemohon;
    private PermohonanPihak mohonPihak;
    private PermohonanRujukanLuar pkl;
    private PermohonanRujukanLuar pkl2;
    private List<PermohonanPembetulanHakmilik> permohonanHakmilikBetulList;
    private List<PermohonanPembetulanUrusan> permohonanUrusanBetulList;
    private HakmilikUrusan hakmilikUrusan;
    private Pihak pihak;
    private KodRujukan kodRujukan;
    private Permohonan permohonanLama;
    private HakmilikPihakBerkepentingan hpb;
    private static final Logger LOG = Logger.getLogger(PembetulanValidation.class);
    private static final Logger syslog = SYSLOG.getLogger();
    private static String DAFTAR = "D";
    private static final String[] urusanKaveat = {
        //        "PMPJK",
        //        "PHMMK",
        //        "PHMM",
        //        "GDPJK",
        "KVAK",
        "KVAS",
        "KVAT",
        "KVLK",
        "KVLP",
        "KVLS",
        "KVLT",
        "KVPK",
        "KVPP",
        "KVPPT",
        "KVPS",
        "KVPT",
        "KVSK",
        "KVSPT",
        "KVSS",
        "KVST"
//        "PMHBE",
//        "PNPHB",
//        "PJKT",
//        "GDPJ",
//        "PNPBK",
//        "PNPBA",
//        "PLT",
//        "PLK",
//        "PLS",
//        "PMHUK",
//        "KVSPC",
//        "LTK",
//        "JPGPJ"
    };

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanPembetulanUrusan getBetulUrusan() {
        return betulUrusan;
    }

    public void setBetulUrusan(PermohonanPembetulanUrusan betulUrusan) {
        this.betulUrusan = betulUrusan;
    }

    public FolderDokumen getFd() {
        return fd;
    }

    public void setFd(FolderDokumen fd) {
        this.fd = fd;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        //TODO: generate the report....
        Pengguna pengguna = context.getPengguna();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        Dokumen g = null;
        Dokumen h = null;
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        if (permohonan != null && permohonan.getKeputusan().getKod().equals("D")) {
            String kod = permohonan.getKodUrusan().getKod();
            //BETPB - Pembetulan 380 - Maklumat Pihak Berkepentingan

            String idPermohonan = permohonan.getIdPermohonan();
            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            String[] params = null;
            String[] values = null;
            String path2 = "";
            String path3 = "";
            String path4 = "";
            String path5 = "";
            String path6 = "";
            String path7 = "";
            String gen2 = "";
            String gen3 = "";
            String gen4 = "";
            String gen5 = "";
            String gen6 = "";
            String gen7 = "";

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen();
            KodDokumen kd3 = new KodDokumen();
            KodDokumen kd4 = new KodDokumen();
            KodDokumen kd5 = new KodDokumen();
            KodDokumen kd6 = new KodDokumen();
            KodDokumen kd7 = new KodDokumen();

            if (permohonan.getKodUrusan().getKod().equals("BET3K")) {
                List<HakmilikPermohonan> hp = permohonan.getSenaraiHakmilik();
                hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                for (HakmilikPermohonan hkp : hp) {
                    if (hkp.getHakmilik().getIdHakmilikInduk() == null) {
                        hakmilikPermohonanList.add(hkp);
                    }
                }
            }
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                String negeri = conf.getProperty("kodNegeri");

                if (!permohonan.getKodUrusan().getKod().equals("BET3K")) {
                    if (negeri.equalsIgnoreCase("05")) {
                        gen2 = "regBorangHMDHKE.rdf";
                        gen3 = "regBorangHMDHDE.rdf";
                    } else if (negeri.equalsIgnoreCase("04")) {
                        gen2 = "regBorangHMDHKEml.rdf";
                        gen3 = "regBorangHMDHDEml.rdf";
                    }

                    kd2.setKod("DHKE");
                    kd3.setKod("DHDE");

                    if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() != null) {
                        if (negeri.equalsIgnoreCase("05")) {
                            gen2 = "REGB4KKK_NS.rdf";
                            gen3 = "REGB4KDK_NS.rdf";
                        } else if (negeri.equalsIgnoreCase("04")) {
                            gen2 = "REGB4KDHKK_MLK.rdf";
                            gen3 = "REGB4KDHDK_MLK.rdf";
                        }
                        kd2.setKod("DHKK");
                        kd3.setKod("DHDK");
                    }
                }

                if (permohonan.getKodUrusan().getKod().equals("BET3K")) {
                    if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() == null) {
                        if (negeri.equalsIgnoreCase("05")) {
                            gen4 = "REGB3K_NS.rdf";
                            gen5 = "REGB2K_NS.rdf";
                            gen2 = "regBorangHMDHKEml.rdf";
                            gen3 = "regBorangHMDHDEml.rdf";
                        } else if (negeri.equalsIgnoreCase("04")) {
//                            gen4 = "REGB3KBET.rdf";
//                            gen5 = "REGB2KBET.rdf";
                            gen2 = "REGB3KBET.rdf";
                            gen3 = "REGB2KBET.rdf";
                        }
//                        kd4.setKod("3K");
//                        kd5.setKod("2K");
                        kd2.setKod("3K");
                        kd3.setKod("2K");
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("BET4K")) {
                    if (negeri.equalsIgnoreCase("05")) {
                        gen2 = "REGB4KKK_NS.rdf";
                        gen3 = "REGB4KDK_NS.rdf";
                    } else if (negeri.equalsIgnoreCase("04")) {
                        gen2 = "REGB4KDHKKBET_MLK.rdf";
                        gen3 = "REGB4KDHDKBET_MLK.rdf";
                        gen4 = "REGB2KBET_MLK.rdf";
                        gen5 = "REGPSKBET_MLK.rdf";



                        gen6 = "REGBSKDHKKBET_MLK.rdf";
                        gen7 = "REGBSKDHDKBET_MLK.rdf";
                    }
                    kd2.setKod("DHKK");
                    kd2.setNama("Dokumen Hakmilik Keluaran (Komputer)");
                    kd3.setKod("DHDK");
                    kd3.setNama("Dokumen Hakmilik (Komputer)");
                    kd4.setKod("B2K");
                    kd4.setNama("Borang 2K Indeks Daftar Strata ");
                    kd5.setKod("PSK");
                    kd5.setNama("Pelan Akui Berkomputer(PA(B)) - Borang S(K)");
                    kd6.setKod("BSKK");
                    kd6.setNama("Borang S(K) DHKK");
                    kd7.setKod("BSDK");
                    kd7.setNama("Borang S(K) DHDK");
                }

                e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                if ((kod.equalsIgnoreCase("BETUR")) && (kd2.getKod().equals("DHKE"))) {
                    Hakmilik hm_ = hakmilikPermohonan.getHakmilik();
                    hm_.setDhke(e);
                    pService.updateHakmilik(hm_);
                }
                if (kd2.getKod().equals("DHKE")) {
                    Hakmilik hm_ = hakmilikPermohonan.getHakmilik();
                    hm_.setDhke(e);
                    pService.updateHakmilik(hm_);
                }

                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                LOG.info("::Path To save :: " + path2);
                LOG.info("::Report Name ::" + gen2);
                syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
                Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil1, gen2, params, values, dokumenPath + path2, peng));
//                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                File sign = new File(dokumenPath + path2 + ".sig");
                if (sign.exists()) {
                    sign.delete();
                }

                //gen HKE
//                kd3.setKod("DHDE");
                f = saveOrUpdateDokumen(fd, kd3, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                if ((kod.equalsIgnoreCase("BETUR")) && (kd3.getKod().equals("DHDE"))) {
                    Hakmilik hm_ = hakmilikPermohonan.getHakmilik();
                    hm_.setDhde(f);
                    pService.updateHakmilik(hm_);
                }
                if (kd3.getKod().equals("DHDE")) {
                    Hakmilik hm_ = hakmilikPermohonan.getHakmilik();
                    hm_.setDhde(f);
                    pService.updateHakmilik(hm_);
                }
                path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
                LOG.info("::Path To save :: " + path3);
                LOG.info("::Report Name ::" + gen3);
                syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
                Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
//                reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
                sign = new File(dokumenPath + path3 + ".sig");
                if (sign.exists()) {
                    sign.delete();
                }

                //Generate Pelan both for BETP and BETUL
                //BETP - Pembetulan 380 - Pelan
                //BETUL - Pembetulan 380 - Bawah Seksyen 380 KTN 
                if ((kod.equalsIgnoreCase("BETP")) || (kod.equalsIgnoreCase("BETUL"))) {
                    updateHakmilik(context, permohonan);
                    Hakmilik hm = hakmilikPermohonan.getHakmilik();
                    //TODO:
                    LOG.info(":: Generate Pelan :: ");

                    if (hm.getKodHakmilik().getKod().equals("PN")
                            || hm.getKodHakmilik().getKod().equals("GRN")
                            || hm.getKodHakmilik().getKod().equals("GM")
                            || hm.getKodHakmilik().getKod().equals("GMM")
                            || hm.getKodHakmilik().getKod().equals("PM")) {
                        if (conf.getProperty("kodNegeri").equals("05")) {
                            //b1 n9
                            gen4 = "REG_BorangB1eNS.rdf";
                            gen5 = "REG_BorangB1eNS_DHKe.rdf";

                            if (hm.getIdHakmilikInduk() != null) {
                                gen4 = "UTILITIPSK_MLK.rdf";
                            }
                        } else {
                            //b1 melaka
                            gen4 = "REG_BorangB1eMLK.rdf";
                            gen5 = "REG_BorangB1eMLK_DHKe.rdf";

                            if (hm.getIdHakmilikInduk() != null) {
                                gen4 = "UTILITIPSK_NS.rdf";
                            }
                        }
                        kd4.setKod("PB1DE");
                        kd5.setKod("PB1KE");

                    } else {
                        if (conf.getProperty("kodNegeri").equals("05")) {
                            //b2 n9
                            gen4 = "REG_BorangB2eNS.rdf";
                            gen5 = "REG_BorangB2eNS_DHKe.rdf";
                        } else {
                            //b2 melaka
                            gen4 = "REG_BorangB2eMLK.rdf";
                            gen5 = "REG_BorangB2eMLK_DHKe.rdf";
                        }
                        kd4.setKod("PB2DE");
                        kd5.setKod("PB2KE");
                    }

                    if (hm.getIdHakmilikInduk() != null) {
                        if (negeri.equalsIgnoreCase("05")) {
                            gen4 = "UTILITIPSK_NS.rdf";
                        } else if (negeri.equalsIgnoreCase("04")) {
                            gen4 = "UTILITIPSK_MLK.rdf";
                        }
                        kd4.setKod("PSK");
                        kd5.setKod(null);
                    }

                    g = saveOrUpdateDokumen(fd, kd4, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                    path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                    LOG.info("::Path To save :: " + path4);
                    LOG.info("::Report Name ::" + gen4);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path4);
                    reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
                    sign = new File(dokumenPath + path4 + ".sig");
                    hm.setPelan(g);
                    if (sign.exists()) {
                        sign.delete();
                    }

                    // PB1DE
                    updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());
                    hakmilikPermohonan.setDokumen5(g);

                    if (hm.getIdHakmilikInduk() == null) {
                        h = saveOrUpdateDokumen(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
                        LOG.info("::Path To save :: " + path5);
                        LOG.info("::Report Name ::" + gen5);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
                        reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
                        sign = new File(dokumenPath + path5 + ".sig");
                        //hm.setPelan(h);
                        if (sign.exists()) {
                            sign.delete();
                        }
                        // PB1KE
                        updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen(), reportUtil.getContentType());
                        hakmilikPermohonan.setDokumen6(h);
                    }
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                }

//                if (permohonan.getKodUrusan().getKod().equals("BET3K")) {
//                    if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() == null) {
//                        g = saveOrUpdateDokumen(fd, kd4, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
//                        path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
//                        LOG.info("::Path To save :: " + path4);
//                        LOG.info("::Report Name ::" + gen4);
//                        syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path4);
//                        reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
//                        sign = new File(dokumenPath + path4 + ".sig");
//                        if (sign.exists()) {
//                            sign.delete();
//                        }
//                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());
//
//                        g = saveOrUpdateDokumen(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
//                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
//                        LOG.info("::Path To save :: " + path5);
//                        LOG.info("::Report Name ::" + gen5);
//                        syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
//                        reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
//                        sign = new File(dokumenPath + path5 + ".sig");
//                        if (sign.exists()) {
//                            sign.delete();
//                        }
//                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());
//                    }
//                }
                if (permohonan.getKodUrusan().getKod().equals("BET4K")) {
                    if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() != null) {
                        g = saveOrUpdateDokumen(fd, kd4, hakmilikPermohonan.getHakmilik().getIdHakmilikInduk(), context);
                        path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        LOG.info("::Path To save :: " + path4);
                        LOG.info("::Report Name ::" + gen4);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() + " and saving it to:" + path4);
                        reportUtil.generateReport(gen4, params, new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilikInduk()}, dokumenPath + path4, peng);
                        sign = new File(dokumenPath + path4 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());

                        g = saveOrUpdateDokumen(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        LOG.info("::Path To save :: " + path5);
                        LOG.info("::Report Name ::" + gen5);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
                        reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
                        sign = new File(dokumenPath + path5 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());

                        g = saveOrUpdateDokumen(fd, kd6, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                        path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        LOG.info("::Path To save :: " + path6);
                        LOG.info("::Report Name ::" + gen6);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path6);
                        reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
                        sign = new File(dokumenPath + path6 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());

                        g = saveOrUpdateDokumen(fd, kd7, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
                        path7 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
                        LOG.info("::Path To save :: " + path7);
                        LOG.info("::Report Name ::" + gen7);
                        syslog.info(peng.getIdPengguna() + " generate report " + kd7.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path7);
                        reportUtil.generateReport(gen7, params, values, dokumenPath + path7, peng);
                        sign = new File(dokumenPath + path7 + ".sig");
                        if (sign.exists()) {
                            sign.delete();
                        }
                        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen(), reportUtil.getContentType());
                    }
                }

                LOG.debug("Waiting for reports to complete...");
                try {
                    dhke.get();
                } catch (Exception ex) {
                    LOG.debug(ex.getMessage(), ex);
                }
                try {
                    dhde.get();
                } catch (Exception ex) {
                    LOG.debug(ex.getMessage(), ex);
                }
                LOG.debug("saving dokumen : " + e.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                LOG.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());

                // dhke
                updatePathDokumen(reportUtil1.getDMSPath(), e.getIdDokumen(), reportUtil1.getContentType());
                hakmilikPermohonan.setDokumen2(e);
                // dhde
                updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen(), reportUtil2.getContentType());
                hakmilikPermohonan.setDokumen3(f);

                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.debug("Before Complete :: start");
        Permohonan permohonan = context.getPermohonan();
        String kod = permohonan.getKodUrusan().getKod();

        pengguna = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        LOG.debug("KOD URUSAN :: " + kod);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            //Added by Aizuddin for PPBM
            if (kod.equalsIgnoreCase("PPBM")) {
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
//                    betpb(context, permohonan);
//                    bethm(context, permohonan);
//                    betpb(context, permohonan);
                    betul(context, permohonan);
                    beten(context, permohonan);
                    betsw(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            if (kod.equalsIgnoreCase("BETUR")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    updateFolder(context, permohonan);
//                    betpb(context, permohonan);
//                    beten(context, permohonan);
                    betul(context, permohonan);
                    betur(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            if (kod.equalsIgnoreCase("BETUL")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    updateFolder(context, permohonan);
//                    betpb(context, permohonan);
//                    beten(context, permohonan);
                    betul(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            if (kod.equalsIgnoreCase("BETC")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            if (kod.equalsIgnoreCase("BET3K") || kod.equalsIgnoreCase("BET4K")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    updateHakmilikSTR(context, permohonan);
                    updateHakmilikUrusanSTR(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
// BETEN - Pembetulan 380 - Batal Endosan
            if (kod.equalsIgnoreCase("BETEN")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    beten(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            /* BETHM :: PEMBETULAN 380 - Silap Endosan atas Hakmilik */
            if (kod.equalsIgnoreCase("BETHM")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    bethm(context, permohonan); // update urusan yang dibetulkan   
                    insertHakmilikUrusan(context, permohonan); // update hakmilik urusan BETHM
                    updateHakmilik(context, permohonan);
//                    bethm(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }

            // BETLP - Pembetulan 380 - Menghidupkan Hakmilik Luput(Silap Daftar)
            if (kod.equalsIgnoreCase("BETLP")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            // BETP - Pembetulan 380 - Pelan
            if (kod.equalsIgnoreCase("BETP")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
//                    updateHakmilik(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }

            //BETPB - Pembetulan 380 - Maklumat Pihak Berkepentingan
            if (kod.equalsIgnoreCase("BETPB")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    betpb(context, permohonan);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            //BETST - Pembetulan 380 - Status Hakmilik
            if (kod.equalsIgnoreCase("BETST")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    updateAkaun(permohonan, info);
                    LOG.info(generatePerihal(permohonan));
                }
            }
            //BETSW - Pembetulan 380 - Suratkuasa Wakil

            if (kod.equalsIgnoreCase("BETSW")) {
//TODO:
                if (proposedOutcome.equals("D")) {
                    insertHakmilikUrusan(context, permohonan);
                    updateHakmilik(context, permohonan);
                    betsw(context, permohonan);
                }
            }

            //update no versi
//            updateNoVersi(permohonan, info);
            if (kod.equalsIgnoreCase("BET3K")) {
                updateNoVersi3K(permohonan, info);
            } else {
                updateNoVersi(permohonan, info);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            context.addMessage("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
            return null;
        }
        LOG.debug("Transaction Finish ::");
//    context.addMessage(" - Pendaftaran Urusan Berjaya.");
        StringBuilder msg = new StringBuilder(" - Pendaftaran Urusan Berjaya.");
        if (permohonan.getPermohonanSebelum() != null) {
            msg.append(" Notifikasi telah dihantar ke modul sebelum.");
        }
        context.addMessage(msg.toString());
        return proposedOutcome;
//    return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        // TODO Auto-generated method stub
        Permohonan permohonan = context.getPermohonan();
        List<Permohonan> senaraiPermohonanTerlibat = new ArrayList<Permohonan>();
        StringBuilder sb = new StringBuilder();

        if (permohonan != null && permohonan.getKeputusan() != null
                && permohonan.getKeputusan().getKod().equals(DAFTAR)) {

            String idKumpulan = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonanTerlibat = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonanTerlibat.add(permohonan);
            }

            for (Permohonan pmohon : senaraiPermohonanTerlibat) {
                if (pmohon == null) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(pmohon.getIdPermohonan());
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = pmohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null || hp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hp.getHakmilik();
                    if (hp.getDokumen2() != null) {
                        hm.setDhke(hp.getDokumen2());
                    }
                    if (hp.getDokumen3() != null) {
                        hm.setDhde(hp.getDokumen3());
                    }
                }
            }

            context.addMessage("Perserahan " + sb.toString() + " telah berjaya didaftarkan.");

            //integrate with HCAP
            //todo : save sign file to HCAP
            LOG.debug("WORM process.....");
            WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
            List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
            String docPath = conf.getProperty("document.path");
            List<HakmilikPermohonan> senaraiHm = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hmp : senaraiHm) {
                Hakmilik hm = hmp.getHakmilik();
                if (hm == null) {
                    continue;
                }
                Dokumen d = hm.getDhde();

                if (d != null) {
                    String namaFizikalAsal = d.getNamaFizikal();
                    File dhde = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                            + namaFizikalAsal);
                    if (dhde != null) {
                        LOG.debug("insert into WORM [dhde]");
                        try {
                            int status = worm.put(dhde,
                                    hm.getIdHakmilik(),
                                    hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                    null,
                                    hm.getKodHakmilik().getKod(),
                                    String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                    hm.getKodStatusHakmilik().getKod());
                            LOG.debug("[status] =  " + status);
                            if (status == WORMUtil.SC_CREATED
                                    || status == WORMUtil.SC_CREATED_W_ERROR) {
                                dhde.delete();
                                String path = worm.buildPath(hm.getDaerah().getKod(),
                                        hm.getBandarPekanMukim().getbandarPekanMukim(),
                                        null,
                                        hm.getKodHakmilik().getKod(),
                                        String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0)).toString();
                                d.setNamaFizikal(path + File.separator + hm.getIdHakmilik());
                                senaraiDokumen.add(d);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    File sign = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator)
                            + namaFizikalAsal + ".sig");
                    if (sign.exists()) {
                        String filename = hm.getIdHakmilik() + ".sig";
                        try {
                            int status = worm.put(sign,
                                    filename,
                                    hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                    null,
                                    hm.getKodHakmilik().getKod(),
                                    String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                    hm.getKodStatusHakmilik().getKod());

                            if (status == WORMUtil.SC_CREATED
                                    || status == WORMUtil.SC_CREATED_W_ERROR) {
                                sign.delete();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit infoAudit) throws Exception {
        //todo check if there is no DHKE generate
        LOG.debug("[ updateNoVersi start ]");
        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilikUpdate = new ArrayList<Hakmilik>();

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            LOG.debug("hakmilik to update no versi [ " + hm.getIdHakmilik() + " ]");
            infoAudit = hm.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new Date());
            hm.setNoVersiDhde(hm.getNoVersiDhde() + 1);
            hm.setNoVersiDhke(hm.getNoVersiDhke() + 1);

            if (permohonan.getKodUrusan().getKod().equals("BET4K")) {
                hm.setNoVersiIndeksStrata(hm.getNoVersiIndeksStrata() + 1);
            }
            hm.setInfoAudit(infoAudit);
            senaraiHakmilikUpdate.add(hm);
        }

        if (!senaraiHakmilikUpdate.isEmpty()) {
            regService.simpanHakmilikList(senaraiHakmilikUpdate);
        }
        LOG.debug("[ updateNoVersi finish ]");
    }

//    private void updateNoVersi(Permohonan permohonan, InfoAudit info, String proposedOutcome) throws Exception {
//        LOG.info("updateNoVersi :: started");
//        if (proposedOutcome.equals("D")) {
//            FolderDokumen fd = permohonan.getFolderDokumen();
//            List<KandunganFolder> senaraiKF = fd.getSenaraiKandungan();
//            List<HakmilikPermohonan> hp = new ArrayList();
//            List<Hakmilik> lhk = new ArrayList();
//            hp = permohonan.getSenaraiHakmilik();
//            for (KandunganFolder kf : senaraiKF) {
//                if (kf == null) {
//                    continue;
//                }
//                if (kf.getDokumen() == null || (!kf.getDokumen().getKodDokumen().getKod().equals("DHDE") && !kf.getDokumen().getKodDokumen().getKod().equals("DHKE"))) {
//                    continue;
//                }
//                Dokumen d = kf.getDokumen();
//                for (HakmilikPermohonan hakmilikPermohonan : hp) {
//                    Hakmilik hk = hakmilikPermohonan.getHakmilik();
//                    if (d.getKodDokumen().getKod().equals("DHDE")) {
//                        LOG.info("update version DHDE");
////                        hk.setDhde(d);
//                        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
//                    } else if (d.getKodDokumen().getKod().equals("DHKE")) {
//                        LOG.info("update version DHKE");
//                        //todo : if no Dhke no need to update DHKE version
////                        hk.setDhke(d);
//                        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
//                    }
//                    info = hk.getInfoAudit();
//                    info.setDiKemaskiniOleh(pengguna);
//                    info.setTarikhKemaskini(new java.util.Date());
//                    hk.setInfoAudit(info);
//                    lhk.add(hk);
//                }
//            }
//            regService.simpanHakmilikList(lhk);
//        }
//        LOG.info("updateNoVersi :: finish");
//    }
    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        KodKlasifikasi kodKlasifikasi = kodKlasifikasiDAO.findById(3);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setKlasifikasi(kodKlasifikasi);
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            doc.setKlasifikasi(kodKlasifikasi);
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");

        if (kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) {
            doc.setTajuk(kd.getKod() + "-" + id);
        } else if (kd.getNama() != null) {
            doc.setTajuk(kd.getKod() + " - " + kd.getNama());
        } else {
            doc.setTajuk(kd.getKod());
        }

        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        dokumenService.saveOrUpdate(d);
    }

    private String generatePerihal(Permohonan permohonan) {
        String perihal = "";
        String medanHakmilikAsal = "";
        String medanTarikhDaftarHAsal = "";
        String medanHakmilikSebelum = "";
        String nilaiHakmilikAsalLama = "";
        String nilaiTarikhDaftarAsal = "";
        String nilaiHakmilikSebelumLama = "";
        String medanCukai = "";
        String medanKodLot = "";
        String medanNoLot = "";
        String medanUnitLuas = "";
        String medanLuas = "";
        String medanKatTanah = "";
        String medanPiawai = "";
        String medanNoPelan = "";
        String medanNoFail = "";
        String medanSyaratNyata = "";
        String medanSekatanKepentingan = "";
        String medanStatusDaftar = "";
        String medanPegangan = "";
        String medanTarikhDaftar = "";
        String medanRezab = "";
        String medanNoPU = "";
        String medanLokasi = "";
        String medanTarikhLuput = "";
        permohonanHakmilikBetulList = pService.findidPermohonan(permohonan.getIdPermohonan());
        LOG.info("Generate Perihal Start");
        for (PermohonanPembetulanHakmilik pbH : permohonanHakmilikBetulList) {
//            hakmilikAsalList = pService.findidHakmilik(pbH.getIdHakmilik());
//            hakmilikSebelumList = pService.findHSidHakmilik(pbH.getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(pbH.getPermohonan().getIdPermohonan(), pbH.getIdHakmilik());

            // get old Value
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETUL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETUR")) {//urusan betul start here
//                for (HakmilikAsal hA : hakmilikAsalList) {
//                    nilaiHakmilikAsalLama = hA.getHakmilik().getIdHakmilik();
//                    nilaiTarikhDaftarAsal = hA.getHakmilik().getTarikhDaftar().toString();
//
//                }
//
//                for (HakmilikSebelum hS : hakmilikSebelumList) {
//                    nilaiHakmilikSebelumLama = hS.getHakmilik().getIdHakmilik();
//                }

                //checking and get new value
//                if (!StringUtils.isBlank(pbH.getIdHakmilikSebelum())) {
//                    medanHakmilikSebelum = "Hakmilik Sebelum (Daripada: " + nilaiHakmilikSebelumLama + " Kepada: " + pbH.getIdHakmilikSebelum() + ")";
//                }
//                if (pbH.getIdHakmilikAsal() != null) {
//                    medanHakmilikAsal = "Hakmilik Asal (Daripada: " + nilaiHakmilikAsalLama + " Kepada: " + pbH.getIdHakmilikAsal() + ")";
//                }
//                if (pbH.getTarikhDaftarHakmilikAsal() != null) {
//                    medanTarikhDaftarHAsal = "Tarikh Daftar Hakmilik Asal (Daripada: " + nilaiTarikhDaftarAsal + " Kepada: " + pbH.getTarikhDaftarHakmilikAsal() + ")";
//
//                }
                if (pbH.getCukai() != null) {
                    if (hakmilikPermohonan.getHakmilik().getCukai() != null
                            && hakmilikPermohonan.getHakmilik().getCukai().toString() != null) {
                        medanCukai = "Cukai (Daripada:" + hakmilikPermohonan.getHakmilik().getCukai().toString() + " Kepada: " + pbH.getCukai() + ")";
                    } else {
                        medanCukai = "Cukai (Daripada: Tiada" + " Kepada: " + pbH.getCukai() + ")";
                    }
                }
                if (pbH.getKodLot() != null) {
                    if (hakmilikPermohonan.getHakmilik().getLot() != null) {
                        medanKodLot = "Kod Lot (Daripada: " + hakmilikPermohonan.getHakmilik().getLot().getNama() + " Kepada: " + pbH.getKodLot().getNama() + ")";
                    } else {
                        medanKodLot = "Kod Lot (Daripada: Tiada" + " Kepada: " + pbH.getKodLot().getNama() + ")";
                    }
                }
                if (pbH.getNoLot() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoLot() != null) {
                        medanNoLot = "No. Lot (Daripada: " + hakmilikPermohonan.getHakmilik().getNoLot() + " Kepada: " + pbH.getNoLot() + ")";
                    } else {
                        medanNoLot = "No. Lot (Daripada: Tiada" + " Kepada: " + pbH.getNoLot() + ")";
                    }
                }
                if (pbH.getUom() != null) {
                    if (hakmilikPermohonan.getHakmilik().getKodUnitLuas() != null) {
                        medanUnitLuas = "Unit Luas (Daripada: " + hakmilikPermohonan.getHakmilik().getKodUnitLuas().getNama() + " Kepada: " + pbH.getUom().getNama() + ")";
                    } else {
                        medanUnitLuas = "Unit Luas (Daripada: Tiada" + " Kepada: " + pbH.getUom().getNama() + ")";
                    }
                }
                if (pbH.getLuas() != null) {
                    if (hakmilikPermohonan.getHakmilik().getLuas() != null) {
                        medanLuas = "Luas (Daripada: " + hakmilikPermohonan.getHakmilik().getLuas() + " Kepada: " + pbH.getLuas() + ")";
                    } else {
                        medanLuas = "Luas (Daripada: Tiada" + " Kepada: " + pbH.getLuas() + ")";
                    }

                }
                if (pbH.getKategoriTanah() != null) {
                    if (hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama() != null) {
                        medanKatTanah = "Kategori Tanah (Daripada: " + hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama() + " Kepada: " + pbH.getKategoriTanah().getNama() + ")";
                    } else {
                        medanKatTanah = "Kategori Tanah (Daripada: Tiada Kepada: " + pbH.getKategoriTanah().getNama() + ")";
                    }
                }
                if (pbH.getNoLitho() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoLitho() != null) {
                        medanPiawai = "No. Lembaran Piawai (Daripada: " + hakmilikPermohonan.getHakmilik().getNoLitho() + " Kepada: " + pbH.getNoLitho() + ")";
                    } else {
                        medanPiawai = "No. Lembaran Piawai (Daripada: Tiada Kepada: " + pbH.getNoLitho() + ")";
                    }
                }
                if (pbH.getNoPelan() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoPelan() != null) {
                        medanNoPelan = "No. Pelan (Daripada: " + hakmilikPermohonan.getHakmilik().getNoPelan() + " Kepada: " + pbH.getNoPelan() + ")";
                    } else {
                        medanNoPelan = "No. Pelan (Daripada: Tiada Kepada: " + pbH.getNoPelan() + ")";
                    }
                }
                if (pbH.getNoFail() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoPelan() != null) {
                        medanNoFail = "No. Fail (Daripada: " + hakmilikPermohonan.getHakmilik().getNoFail() + " Kepada: " + pbH.getNoFail() + ")";
                    } else {
                        medanNoFail = "No. Fail (Daripada: Tiada Kepada: " + pbH.getNoFail() + ")";
                    }
                }
                if (pbH.getSyaratNyata() != null) {
                    if (hakmilikPermohonan.getHakmilik().getSyaratNyata() != null) {
                        medanSyaratNyata = "Syarat Nyata (Daripada: " + hakmilikPermohonan.getHakmilik().getSyaratNyata().getKod() + " Kepada: " + pbH.getSyaratNyata().getKod() + ")";
                    } else {
                        medanSyaratNyata = "Syarat Nyata (Daripada: Tiada Kepada: " + pbH.getSyaratNyata().getKod() + ")";
                    }
                }

                if (pbH.getSekatanKepentingan() != null) {
                    if (hakmilikPermohonan.getHakmilik().getSekatanKepentingan() != null) {
                        medanSekatanKepentingan = "Sekatan Kepentingan (Daripada: " + hakmilikPermohonan.getHakmilik().getSekatanKepentingan().getKod() + " Kepada: " + pbH.getSekatanKepentingan().getKod() + ")";
                    } else {
                        medanSekatanKepentingan = "Sekatan Kepentingan (Daripada: Tiada Kepada: " + pbH.getSekatanKepentingan().getKod() + ")";
                    }
                }
                if (pbH.getStatusHakmilik() != null) {
                    if (hakmilikPermohonan.getHakmilik().getKodStatusHakmilik() != null) {
                        medanStatusDaftar = "Status Hakmilik (Daripada: " + hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama() + " Kepada: " + pbH.getStatusHakmilik().getNama() + ")";
                    } else {
                        medanStatusDaftar = "Status Hakmilik (Daripada: Tiada Kepada: " + pbH.getStatusHakmilik().getNama() + ")";
                    }
                }

                if (pbH.getPegangan() != null) {
                    if (hakmilikPermohonan.getHakmilik().getPegangan() != null) {
                        medanPegangan = "Pegangan (Daripada: " + hakmilikPermohonan.getHakmilik().getPegangan() + " Kepada: " + pbH.getPegangan() + ")";
                    } else {
                        medanPegangan = "Pegangan (Daripada: Tiada Kepada: " + pbH.getPegangan() + ")";
                    }
                }

                if (pbH.getTarikhDaftar() != null) {
                    if (hakmilikPermohonan.getHakmilik().getTarikhDaftar() != null) {
                        medanTarikhDaftar = "Tarikh Daftar (Daripada: " + hakmilikPermohonan.getHakmilik().getTarikhDaftar() + " Kepada: " + pbH.getTarikhDaftar() + ")";
                    } else {
                        medanTarikhDaftar = "Tarikh Daftar (Daripada: Tiada Kepada: " + pbH.getTarikhDaftar() + ")";
                    }
                }
                if (pbH.getRizab() != null) {
                    if (hakmilikPermohonan.getHakmilik().getRizab() != null) {
                        medanRezab = "Jenis Rezab (Daripada: " + hakmilikPermohonan.getHakmilik().getRizab().getNama() + " Kepada: " + pbH.getRizab().getNama() + ")";
                    } else {
                        medanRezab = "Jenis Rezab (Daripada: Tiada Kepada: " + pbH.getRizab().getNama() + ")";
                    }
                }
                if (pbH.getNoPU() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoPu() != null) {
                        medanNoPU = "No. Permohonan Ukur (Daripada: " + hakmilikPermohonan.getHakmilik().getNoPu() + " Kepada: " + pbH.getNoPU() + ")";
                    } else {
                        medanNoPU = "No. Permohonan Ukur (Daripada: Tiada Kepada: " + pbH.getNoPU() + ")";
                    }
                }
                if (pbH.getLokasi() != null) {
                    if (hakmilikPermohonan.getHakmilik().getLokasi() != null) {
                        medanLokasi = "Lokasi (Daripada: " + hakmilikPermohonan.getHakmilik().getLokasi() + " Kepada: " + pbH.getLokasi() + ")";
                    } else {
                        medanLokasi = "Lokasi (Daripada: Tiada Kepada: " + pbH.getLokasi() + ")";
                    }
                }

                perihal = "Pembetulan telah dibuat keatas medan " + medanCukai + "<br/>" + medanKodLot + "<br/>" + medanNoLot + "<br/>" + medanUnitLuas + "<br/>" + medanLuas + "<br/>" + medanKatTanah + "<br/>" + medanPiawai + "<br/>" + medanNoPelan + "<br/>" + medanNoFail + "<br/>" + medanSyaratNyata + "<br/>" + medanSekatanKepentingan + "<br/>" + medanStatusDaftar + "<br/>" + medanPegangan + "<br/>" + medanTarikhDaftar + "<br/>" + medanRezab + "<br/>" + medanNoPU + "<br/>" + medanLokasi + "<br/>" + medanHakmilikSebelum + "<br/>" + medanHakmilikAsal + "<br/>" + medanTarikhDaftarHAsal;

            }//end if BETUL

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETC")) {//BETC start here
//           JOptionPane.showMessageDialog(null, "START BETC");
                if (pbH.getCukai() != null) {
                    if (hakmilikPermohonan.getHakmilik().getCukai() != null && hakmilikPermohonan.getHakmilik().getCukai().toString() != null) {
                        medanCukai = "Cukai (Daripada:" + hakmilikPermohonan.getHakmilik().getCukai().toString() + " Kepada: " + pbH.getCukai() + ")";
                    } else {
                        medanCukai = "Cukai (Daripada: Tiada" + " Kepada: " + pbH.getCukai() + ")";
                    }
                    perihal = "Pembetulan telah dibuat keatas medan " + medanCukai;
                }
                if (pbH.getCukaiSemasa() != null) {
                    if (hakmilikPermohonan.getHakmilik().getCukaiSebenar() != null && hakmilikPermohonan.getHakmilik().getCukaiSebenar().toString() != null) {
                        medanCukai = "Cukai Tahunan (Daripada:" + hakmilikPermohonan.getHakmilik().getCukaiSebenar().toString() + " Kepada: " + pbH.getCukaiSemasa() + ")";
                    } else {
                        medanCukai = "Cukai Tahunan (Daripada: Tiada" + " Kepada: " + pbH.getCukaiSemasa() + ")";
                    }
                    perihal = "Pembetulan telah dibuat keatas medan " + medanCukai;
                }
            }
            //BETST - Pembetulan 380 - Status Hakmilik
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETST")) {
                if (pbH.getStatusHakmilik() != null) {
                    if (hakmilikPermohonan.getHakmilik().getKodStatusHakmilik() != null) {
                        medanStatusDaftar = "Status Hakmilik (Daripada: " + hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama() + " Kepada: " + pbH.getStatusHakmilik().getNama() + ")";
                    } else {
                        medanStatusDaftar = "Status Hakmilik (Daripada: Tiada Kepada: " + pbH.getStatusHakmilik().getNama() + ")";
                    }
                }
            }

            //BETP - Pembetulan 380 - Pelan
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETP")) {
                if (pbH.getNoPelan() != null) {
                    if (hakmilikPermohonan.getHakmilik().getNoPelan() != null) {
                        medanNoPelan = "No. Pelan (Daripada: " + hakmilikPermohonan.getHakmilik().getNoPelan() + " Kepada: " + pbH.getNoPelan() + ")";
                    } else {
                        medanNoPelan = "No. Pelan (Daripada: Tiada Kepada: " + pbH.getNoPelan() + ")";
                    }
                }
            }

            //BETLP - Pembetulan 380 - Menghidupkan Hakmilik Luput(Silap Daftar)
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETLP")) {
                if (pbH.getTarikhLuput() != null) {
                    if (hakmilikPermohonan.getHakmilik().getTarikhLuput() != null) {
                        medanTarikhLuput = "Tarikh Luput (Daripada: " + hakmilikPermohonan.getHakmilik().getTarikhLuput() + " Kepada: " + pbH.getTarikhLuput() + ")";
                    } else {
                        medanTarikhLuput = "Tarikh Luput (Daripada: Tiada Kepada: " + pbH.getTarikhLuput() + ")";
                    }
                }
            }

        }//end of loop
        LOG.info("Generate Perihal End");
        LOG.info(perihal);
        return perihal;
    }

    private void insertHakmilikUrusan(StageContext context, Permohonan permohonan) {
        LOG.info("Insert Into HakmilikUrusan Start");
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        String kod = permohonan.getKodUrusan().getKod();
        KodUrusan kodUrusan = kodUrusanDAO.findById(kod);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();

        List<PermohonanPembetulanHakmilik> permohonanBetulHakmilikList = pService.findidPermohonan(permohonan.getIdPermohonan());

        //FOR BETUR since betur betul urusan jer
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETUR")) {
            List<PermohonanPembetulanUrusan> permohonanBetulUrusanList = pService.findBetulMaklumatUrusan_(permohonan.getIdPermohonan());
            for (PermohonanPembetulanUrusan mub : permohonanBetulUrusanList) {
                HakmilikPermohonan haPermohonan = pService.findByIdMohonHakmilik(mub.getHakmilik().getId());
                List<HakmilikUrusan> Hulist = regService.searchHakmilikUrusan(haPermohonan.getHakmilik().getIdHakmilik());

                if (mub.getUlasan() != null) {
                    if (mub.getUlasan().equals("TAMBAHBARU")) {
                        hakmilikUrusan = new HakmilikUrusan();
                        LOG.info("BETUR masuk hakmilik urusan");
                        hakmilikUrusan.setInfoAudit(info);
                        hakmilikUrusan.setHakmilik(haPermohonan.getHakmilik());
                        hakmilikUrusan.setTarikhDaftar(mub.getTarikhDaftar());
                        hakmilikUrusan.setAktif('Y');
                        hakmilikUrusan.setIdPerserahan(mub.getIdPerserahanLama());

                        hakmilikUrusan.setKodUrusan(mub.getKodUrusan());
                        hakmilikUrusan.setDaerah(haPermohonan.getHakmilik().getDaerah());
                        if (senaraiPermohonanRujukanLuar.size() != 0) {
                            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);

                            if (permohonanRujLuar.getNoRujukan() != null) {
                                hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
                            }
                            if (permohonanRujLuar.getNoSidang() != null) {
                                hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                            }
                            if (permohonanRujLuar.getNoFail() != null) {
                                hakmilikUrusan.setNoFail(permohonanRujLuar.getNoFail());
                            }
                            if (permohonanRujLuar.getTarikhRujukan() != null) {
                                hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                            }
                            if (permohonanRujLuar.getTarikhSidang() != null) {
                                hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                            }

                        } 
                        else if (mub.getUlasan().equals("KEMASKINI")) {
                            hakmilikUrusan.setInfoAudit(info);
                            hakmilikUrusan.setHakmilik(mub.getHakmilik().getHakmilik());
                            hakmilikUrusan.setTarikhDaftar(permohonan.getTarikhKeputusan());
                            hakmilikUrusan.setAktif('Y');
                            hakmilikUrusan.setIdPerserahan(String.valueOf(mub.getIdPembetulan()));
                            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                            hakmilikUrusan.setDaerah(mub.getHakmilik().getHakmilik().getDaerah());
                            hakmilikUrusan.setCawangan(permohonan.getCawangan());
                            hakmilikUrusanList.add(hakmilikUrusan);
                        }
                        if (mub.getCawangan() != null) {
                            hakmilikUrusan.setCawangan(mub.getCawangan());
                        } else {
                            hakmilikUrusan.setCawangan(permohonan.getCawangan());
                        }
//                        List<HakmilikPermohonan> hakmilikPermohonan = permohonan.getSenaraiHakmilik();
//                        for (HakmilikPermohonan hp : hakmilikPermohonan) {
//                            hakmilikUrusan = new HakmilikUrusan();
//                            hakmilikUrusan.setInfoAudit(info);
//                            hakmilikUrusan.setHakmilik(hp.getHakmilik());
//                            hakmilikUrusan.setTarikhDaftar(mub.getTarikhDaftar());
//                            hakmilikUrusan.setAktif('Y');
//                            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
//                            hakmilikUrusan.setCatatan(generatePerihal(permohonan));
//                            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
//                            hakmilikUrusan.setDaerah(hp.getHakmilik().getDaerah());
//                            hakmilikUrusan.setCawangan(permohonan.getCawangan());
//                            hakmilikUrusan.setCatatan(generatePerihal(permohonan));
//                            hakmilikUrusanList.add(hakmilikUrusan);
//                        }
                    } else if (mub.getUlasan().equals("KEMASKINIURUSANBATAL")) {
//                            for (HakmilikUrusan hUrusan : Hulist) {
                        if (mub.getUrusan() != null) {
                            HakmilikUrusan hu = hakmilikUrusanDAO.findById(mub.getUrusan().getIdUrusan());
                            if (mub.getAktif().equals("Y")) {
                                hu.setAktif('Y');
                            } else if (mub.getAktif().equals("T")) {
                                hu.setAktif('T');
                            }
                            hu.setInfoAudit(info);
                            hakmilikUrusanList.add(hu);
                        }
                    }
                    List<PermohonanPembetulanHakmilik> permohonanBetulHakmilikList1 = pService.findidPermohonan(mub.getIdPerserahanLama());
                    for (PermohonanPembetulanHakmilik pbHak : permohonanBetulHakmilikList1) {
                        if (mub.getIdPerserahanLama().equals(pbHak.getPermohonan().getIdPermohonan())) {
                            if (mub.getTempohTahun()!= null) {
                                pbHak.setTempohPenganganSemasa(mub.getTempohTahun());
                            }
                            if (mub.getTarikhTamat()!= null) {
                                pbHak.setTarikhLuputSemasa(mub.getTarikhTamat());
                            }

                            pService.simpanBetul(pbHak);
                        }

                    } 
                    if (mub.getUlasan().equals("KEMASKINI")) {
                        List<PermohonanPembetulanHakmilik> listmhb = pService.findIdPermohonanIdHakmilik(mub.getUrusan().getIdPerserahan(), permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                             LOG.info("mhb PSPL:" + listmhb.size());
                             if (listmhb.size() == 1) {
                                 for (PermohonanPembetulanHakmilik mhb : listmhb) {
                                      LOG.info("betur update tempoh PSPL");
                                      mhb.setTempohPenganganSemasa(mub.getTempohTahun());
                                      mhb.setTarikhLuputSemasa(mub.getTarikhTamat());
                                      mhb.setInfoAudit(info);
                                      pService.simpanBetul(mhb);
                                 }
                            }
                             
                            /*List<PermohonanRujukanLuar> senaraiMrl = regService.searchMohonRujLuarByIdMohonAndIdHakmilik(mub.getUrusan().getIdPerserahan(), permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                            LOG.info("ID MOHON MRL:" + mub.getUrusan().getIdPerserahan());
                            LOG.info("kemaskini MRL:" + senaraiMrl.size());
                            for (PermohonanRujukanLuar mrl : senaraiMrl) {
                                if (senaraiPermohonanRujukanLuar.size() != 0) {
                                    mrl = senaraiPermohonanRujukanLuar.get(0);

                                if (mrl.getNoRujukan() != null) {
                                    mrl.setNoRujukan(mub.getNoRujukan());
                                }
                                if (mrl.getNoSidang() != null) {
                                    mrl.setNoSidang(mub.getNoSidang());
                                }
                                if (mrl.getNoFail() != null) {
                                    mrl.setNoFail(mub.getNoFail());
                                }
                                if (mrl.getTarikhRujukan() != null) {
                                    mrl.setTarikhRujukan(mub.getTarikhRujukan());
                                }
                                if (mrl.getTarikhSidang() != null) {
                                    mrl.setTarikhSidang(mub.getTarikhSidang());
                                }
                                    notaService.simpanPermohonanRujLuar(mrl);
                                }

                            }

                            
                            for (HakmilikUrusan hu1 : Hulist)
                            if (senaraiPermohonanRujukanLuar.size() != 0) {
                                permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);

                                if (permohonanRujLuar.getNoRujukan() != null) {
                                    hu1.setNoRujukan(permohonanRujLuar.getNoRujukan());
                                }
                                if (permohonanRujLuar.getNoSidang() != null) {
                                    hu1.setNoSidang(permohonanRujLuar.getNoSidang());
                                }
                                if (permohonanRujLuar.getNoFail() != null) {
                                    hu1.setNoFail(permohonanRujLuar.getNoFail());
                                }
                                if (permohonanRujLuar.getTarikhRujukan() != null) {
                                    hu1.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                                }
                                if (permohonanRujLuar.getTarikhSidang() != null) {
                                    hu1.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                                }
                                pService.saveOrUpdate(hakmilikUrusanList);
                            }*/
                         
                    }
                }
                List<HakmilikPermohonan> hakmilikPermohonan = permohonan.getSenaraiHakmilik();
                        for (HakmilikPermohonan hp : hakmilikPermohonan) {
                            hakmilikUrusan = new HakmilikUrusan();
                            hakmilikUrusan.setInfoAudit(info);
                            hakmilikUrusan.setHakmilik(hp.getHakmilik());
                            hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk());
                            hakmilikUrusan.setAktif('Y');
                            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                            hakmilikUrusan.setCatatan(generatePerihal(permohonan));
                            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                            hakmilikUrusan.setDaerah(hp.getHakmilik().getDaerah());
                            hakmilikUrusan.setCawangan(permohonan.getCawangan());
                            hakmilikUrusan.setCatatan(generatePerihal(permohonan));
                            hakmilikUrusanList.add(hakmilikUrusan);
                        }

                pService.saveOrUpdate(hakmilikUrusanList);
                break;

            }

        }
        for (PermohonanPembetulanHakmilik pbHak : permohonanBetulHakmilikList) {
            HakmilikPermohonan haPermohonan = pService.findByIdHakmilikIdPermohonan(pbHak.getPermohonan().getIdPermohonan(), pbHak.getIdHakmilik());
            LOG.info("masuk11111");
            hakmilikUrusan = new HakmilikUrusan();
            hakmilikUrusan.setInfoAudit(info);
            hakmilikUrusan.setHakmilik(haPermohonan.getHakmilik());
            hakmilikUrusan.setTarikhDaftar(new java.util.Date());
            hakmilikUrusan.setAktif('Y');
            hakmilikUrusan.setIdPerserahan(pbHak.getPermohonan().getIdPermohonan());
            hakmilikUrusan.setBandarPekanMukimBaru(pbHak.getBandarPekanMukim());
            hakmilikUrusan.setCatatan(generatePerihal(permohonan));
            hakmilikUrusan.setCukaiBaru(pbHak.getCukai());
            hakmilikUrusan.setIdPerserahan(pbHak.getPermohonan().getIdPermohonan());
            hakmilikUrusan.setKategoriTanahBaru(pbHak.getKategoriTanah());
            hakmilikUrusan.setKodUnitLuas(pbHak.getUom());
            hakmilikUrusan.setKodUrusan(kodUrusan);
            hakmilikUrusan.setLuasTerlibat(pbHak.getLuas());
            hakmilikUrusan.setSekatanKepentinganBaru(pbHak.getSekatanKepentingan());
            hakmilikUrusan.setSyaratNyataBaru(pbHak.getSyaratNyata());
            hakmilikUrusan.setDaerah(haPermohonan.getHakmilik().getDaerah());
            if (senaraiPermohonanRujukanLuar.size() != 0) {
                permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);

                if (permohonanRujLuar.getNoRujukan() != null) {
                    hakmilikUrusan.setNoRujukan(permohonanRujLuar.getNoRujukan());
                }
                if (permohonanRujLuar.getNoSidang() != null) {
                    hakmilikUrusan.setNoSidang(permohonanRujLuar.getNoSidang());
                }
                if (permohonanRujLuar.getNoFail() != null) {
                    hakmilikUrusan.setNoFail(permohonanRujLuar.getNoFail());
                }
                if (permohonanRujLuar.getTarikhRujukan() != null) {
                    hakmilikUrusan.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                }
                if (permohonanRujLuar.getTarikhSidang() != null) {
                    hakmilikUrusan.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                }
            }

            if (pbHak.getDaerah() != null) {
                hakmilikUrusan.setCawangan(pbHak.getCawangan());
            } else {
                hakmilikUrusan.setCawangan(permohonan.getCawangan());
            }

            hakmilikUrusanList.add(hakmilikUrusan);
            pService.saveOrUpdate(hakmilikUrusanList);
        }

        /* BETHM - PEMBETULAN 380 - Silap Endosan Atas Hakmilik */
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETHM")) {
            LOG.info("/* BETHM UPDATE INFO*/");
            List<PermohonanPembetulanHakmilik> mohonHakmilikBetulList = pService.findidPermohonan(permohonan.getIdPermohonan());
            //add by azri 8/7/2013: update hakmilik_pihak status
            for (PermohonanPembetulanHakmilik pBetul : mohonHakmilikBetulList) {
                PermohonanAtasPerserahan mohonAtasUrusan = pService.findByIdAU(pBetul.getIdAtasUrusan(), permohonan.getIdPermohonan());
                long idUrusan = mohonAtasUrusan.getUrusan().getIdUrusan();
                // update Hakmilik_Pihak status for old id hakmilik 
                List<HakmilikPihakBerkepentingan> listPihakLama = pService.findByIdUrusanAndIdHakmilik(idUrusan, pBetul.getHakmilik().getHakmilik().getIdHakmilik());
                if (listPihakLama.size() > 0) {
                    LOG.info("/* UPDATE HAKMILIK_PIHAK - old */");
                    for (HakmilikPihakBerkepentingan pihakLama : listPihakLama) {
                        info = peng.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        pihakLama.setInfoAudit(info);
                        pihakLama.setAktif('T'); //set NOT aktif
                        pService.save(pihakLama);
                    }
                }
                // update Hakmilik_Pihak status for new id hakmilik 
                List<HakmilikPihakBerkepentingan> listPihakBaru = pService.findByIdUrusanAndIdHakmilik(idUrusan, pBetul.getIdHakmilik());
                if (listPihakBaru.size() > 0) {
                    LOG.info("/* UPDATE HAKMILIK_PIHAK - new */");
                    for (HakmilikPihakBerkepentingan pihakBaru : listPihakBaru) {
                        info = peng.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        pihakBaru.setInfoAudit(info);
                        pihakBaru.setAktif('Y'); // set aktif
                        pService.save(pihakBaru);
                    }
                }
            }
        }//add by azri 8/7/2013: End

        if (kod.equalsIgnoreCase("BETPB") || kod.equalsIgnoreCase("BETEN") || kod.equalsIgnoreCase("BETSW")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : hakmilikList) {
                LOG.info("masuk2222");
                hakmilikUrusan = new HakmilikUrusan();
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                hakmilikUrusan.setCawangan(hp.getHakmilik().getCawangan());
                hakmilikUrusan.setDaerah(hp.getHakmilik().getDaerah());
                LOG.info("Kod Daerah::" + hp.getHakmilik().getCawangan());
                hakmilikUrusan.setCawangan(hp.getHakmilik().getCawangan());
                hakmilikUrusan.setHakmilik(hp.getHakmilik());
                hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                hakmilikUrusan.setLuasTerlibat(hp.getLuasTerlibat());
                hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                hakmilikUrusan.setKodUnitLuas(hp.getHakmilik().getKodUnitLuas());
                hakmilikUrusan.setCukaiLama(hp.getHakmilik().getCukai());
                hakmilikUrusan.setCukaiBaru(hp.getCukaiBaru());
                hakmilikUrusan.setSekatanKepentinganLama(hp.getHakmilik().getSekatanKepentingan());
                hakmilikUrusan.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru());
                hakmilikUrusan.setSyaratNyataLama(hp.getHakmilik().getSyaratNyata());
                hakmilikUrusan.setSyaratNyataBaru(hp.getSyaratNyataBaru());
                hakmilikUrusan.setAktif('Y');
                hakmilikUrusanList.add(hakmilikUrusan);
                pService.saveOrUpdate(hakmilikUrusanList);
            }
        }

        /* URUSAN PPBM  */
        // add by azri 29/7/2013
        if (kod.equalsIgnoreCase("PPBM")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            List<PermohonanPembetulanUrusan> listUrusanBetul = pService.findUrusanBetulList(permohonan.getIdPermohonan());
            for (HakmilikPermohonan hp : hakmilikList) {
                LOG.info(" /* PPBM :: CREATE DATA HAKMILIK_URUSAN */ ");
                hakmilikUrusan = new HakmilikUrusan();
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                hakmilikUrusan.setCawangan(hp.getHakmilik().getCawangan());
                hakmilikUrusan.setDaerah(hp.getHakmilik().getDaerah());
                hakmilikUrusan.setHakmilik(hp.getHakmilik());
                hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                hakmilikUrusan.setLuasTerlibat(hp.getLuasTerlibat());
                hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                hakmilikUrusan.setKodUnitLuas(hp.getHakmilik().getKodUnitLuas());
                hakmilikUrusan.setCukaiLama(hp.getHakmilik().getCukai());
                hakmilikUrusan.setCukaiBaru(hp.getCukaiBaru());
                hakmilikUrusan.setSekatanKepentinganLama(hp.getHakmilik().getSekatanKepentingan());
                hakmilikUrusan.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru());
                hakmilikUrusan.setSyaratNyataLama(hp.getHakmilik().getSyaratNyata());
                hakmilikUrusan.setSyaratNyataBaru(hp.getSyaratNyataBaru());
                hakmilikUrusan.setAktif('Y');
                hakmilikUrusanList.add(hakmilikUrusan);
                pService.saveOrUpdate(hakmilikUrusanList);
            }

            for (PermohonanPembetulanUrusan urusanBetul : listUrusanBetul) {
                LOG.info(" /* PPBM :: CREATE DATA HAKMILIK_URUSAN ID MOHON PAKSA */ ");
                LOG.info("--> id perserahan lama : " + urusanBetul.getIdPerserahanLama());
                HakmilikUrusan hu = new HakmilikUrusan();
                hu.setInfoAudit(info);
                hu.setIdPerserahan(urusanBetul.getIdPerserahanLama());
                hu.setCawangan(urusanBetul.getCawangan());
                hu.setDaerah(urusanBetul.getHakmilik().getHakmilik().getDaerah());
                hu.setHakmilik(urusanBetul.getHakmilik().getHakmilik());
                hu.setKodUrusan(permohonan.getKodUrusan());
                hu.setLuasTerlibat(urusanBetul.getLuasTerlibat());
                hu.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                hu.setKodUnitLuas(urusanBetul.getHakmilik().getKodUnitLuas());
                hu.setCukaiLama(urusanBetul.getHakmilik().getHakmilik().getCukai());
                hu.setCukaiBaru(urusanBetul.getCukaiBaru());
                hu.setSekatanKepentinganLama(urusanBetul.getHakmilik().getSekatanKepentingan());
                hu.setSekatanKepentinganBaru(urusanBetul.getSekatanKepentinganBaru());
                hu.setSyaratNyataLama(urusanBetul.getHakmilik().getSyaratNyata());
                hu.setSyaratNyataBaru(urusanBetul.getSyaratNyataBaru());
                hu.setAktif('Y');
                hakmilikUrusanList.add(hu);
                pService.saveOrUpdate(hakmilikUrusanList);

                /* UPDATE TABLE MOHON */
                Permohonan updateMohon = permohonanService.findById(urusanBetul.getIdPerserahanLama());
                LOG.info("/* UPDATE TABLE MOHON */");
                updateMohon.setInfoAudit(info);
                KodKeputusan k = new KodKeputusan();
                k.setKod("D");        // mohon = daftar
                updateMohon.setKeputusan(k);
                updateMohon.setStatus("SL");
                permohonanService.update(updateMohon);
            }
        }

        LOG.info("End of insert HakmilikUrusan");
    }

    private void updateFolder(StageContext context, Permohonan permohonan) {
        LOG.info("Update Folder");
        List<PermohonanPembetulanUrusan> listUrusanBetul = pService.findUrusanBetulList2(permohonan.getIdPermohonan());
        if (listUrusanBetul != null) {
            for (PermohonanPembetulanUrusan urusanBetul : listUrusanBetul) {
                if (urusanBetul.getUrusan() != null) {
                    hakmilikUrusan = pService.findUrusan(String.valueOf(urusanBetul.getUrusan().getIdUrusan()));
                    fd = pService.findFolderByTajuk(hakmilikUrusan.getIdPerserahan());
                    if (fd != null) {
                        fd.setNoFolio(urusanBetul.getNoFolio());
                        fd.setNoJilid(urusanBetul.getNoJilid());
                        folderDokumenDAO.save(fd);
                    }
                }
            }
        }
    }

    public void updateAkaun(Permohonan permohonan, InfoAudit info) {
        List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            Akaun akaun = regService.getAkaunLama(hakmilik.getIdHakmilik());

            if (akaun != null) {
                akaun.setStatus(kodStatusAkaunDAO.findById("B"));
                akaun.getInfoAudit().setDiKemaskiniOleh(pengguna);
                akaun.getInfoAudit().setTarikhKemaskini(new Date());
                info.setDiKemaskiniOleh(info.getDimasukOleh());
                info.setTarikhKemaskini(new java.util.Date());
                akaun.setInfoAudit(info);
                regService.saveOrUpdate(akaun);
            }
        }
    }

    private void updateHakmilik(StageContext context, Permohonan permohonan) {
        LOG.info("Update Hakmilik Start");
        Hakmilik hakmilik = new Hakmilik();
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());

        List<PermohonanPembetulanHakmilik> permohonanBetulHakmilikList = pService.findidPermohonan(permohonan.getIdPermohonan());
        for (PermohonanPembetulanHakmilik pBetulH : permohonanBetulHakmilikList) {
            HakmilikPermohonan hPermohonan = pService.findByIdHakmilikIdPermohonan(pBetulH.getPermohonan().getIdPermohonan(), pBetulH.getIdHakmilik());
            hakmilik = hakmilikDAO.findById(pBetulH.getIdHakmilik());
            info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
            info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
            hakmilik.setInfoAudit(info);
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETUL")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPBM")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETUR")) {
                if (pBetulH.getCukai() != null) {
                    hakmilik.setCukai(pBetulH.getCukai());
                }
                if (pBetulH.getCukai() != null) {
                    hakmilik.setCukaiSebenar(pBetulH.getCukai());
                }
                if (pBetulH.getKodLot() != null) {
                    hakmilik.setLot(pBetulH.getKodLot());
                }
                if (pBetulH.getNoLot() != null) {
                    hakmilik.setNoLot(pBetulH.getNoLot());
                }
                if (pBetulH.getUom() != null) {
                    hakmilik.setKodUnitLuas(pBetulH.getUom());
                }
                if (pBetulH.getLuas() != null) {
                    hakmilik.setLuas(pBetulH.getLuas());
                }
                if (pBetulH.getKategoriTanah() != null) {
                    hakmilik.setKategoriTanah(pBetulH.getKategoriTanah());
                }
                if (pBetulH.getKegunaanTanah() != null) {
                    hakmilik.setKegunaanTanah(pBetulH.getKegunaanTanah());
                }
                if (pBetulH.getNoLitho() != null) {
                    hakmilik.setNoLitho(pBetulH.getNoLitho());
                }
                if (pBetulH.getNoPelan() != null) {
                    hakmilik.setNoPelan(pBetulH.getNoPelan());
                }
                if (pBetulH.getNoFail() != null) {
                    hakmilik.setNoFail(pBetulH.getNoFail());
                }
                if (pBetulH.getSyaratNyata() != null) {
                    hakmilik.setSyaratNyata(pBetulH.getSyaratNyata());
                }
                if (pBetulH.getSekatanKepentingan() != null) {
                    hakmilik.setSekatanKepentingan(pBetulH.getSekatanKepentingan());
                }
                if (pBetulH.getStatusHakmilik() != null) {
                    hakmilik.setKodStatusHakmilik(pBetulH.getStatusHakmilik());
                }
                if (pBetulH.getPegangan() != null) {
                    hakmilik.setPegangan(pBetulH.getPegangan());
                }
                if (pBetulH.getTempohPengangan() != null) {
                    hakmilik.setTempohPegangan(pBetulH.getTempohPengangan());
                }
                if (pBetulH.getRizab() != null) {
                    hakmilik.setRizab(pBetulH.getRizab());
                }
                if (pBetulH.getNoPU() != null) {
                    hakmilik.setNoPu(pBetulH.getNoPU());
                }
                if (pBetulH.getLokasi() != null) {
                    hakmilik.setLokasi(pBetulH.getLokasi());
                }
                if (pBetulH.getDaerah() != null) {
                    hakmilik.setDaerah(pBetulH.getDaerah());
                }
                if (pBetulH.getTarikhLuput() != null) {
                    hakmilik.setTarikhLuput(pBetulH.getTarikhLuput());
                }
                if (pBetulH.getTarikhDaftar() != null) {
                    hakmilik.setTarikhDaftar(pBetulH.getTarikhDaftar());
                }
                if (pBetulH.getTarikhDaftarHakmilikAsal() != null) {
                    hakmilik.setTarikhDaftarAsal(pBetulH.getTarikhDaftarHakmilikAsal());
                }
                if (pBetulH.getUomLama() != null) {
                    hakmilik.setKodUnitLuasLama(pBetulH.getUomLama());
                }
                if (pBetulH.getLuasLama() != null) {
                    hakmilik.setLuasLama(pBetulH.getLuasLama());
                }
            }//end BETUL

// BETC - Pembetulan 380 - Cukai
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETC")) {
                if (pBetulH.getCukai() != null) {
                    hakmilik.setCukai(pBetulH.getCukai());
                    hakmilik.setCukaiSebenar(pBetulH.getCukai());
                    hakmilik.setDhde(hPermohonan.getDokumen3());
                    hakmilik.setDhke(hPermohonan.getDokumen2());
//          pcService.larasCukai(pBetulH.getIdHakmilik(), String.valueOf(pBetulH.getCukai()), "61401");
                }
                if (pBetulH.getCukaiSemasa() != null) {
                    hakmilik.setCukaiSebenar(pBetulH.getCukaiSemasa());
                }
            }

//BETST - Pembetulan 380 - Status Hakmilik
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETST")) {
                if (pBetulH.getStatusHakmilik() != null) {
                    KodStatusHakmilik kod = new KodStatusHakmilik();
                    kod.setKod(pBetulH.getStatusHakmilik().getKod());
//                    hakmilik.setKodStatusHakmilik(kod);
                    if (pBetulH.getStatusHakmilik().getKod().equalsIgnoreCase("D")) {
                        hakmilik.setKodStatusHakmilik(pBetulH.getStatusHakmilik());
                        hakmilik.setTarikhBatal(null);
                    }
                    if (pBetulH.getStatusHakmilik().getKod().equalsIgnoreCase("B")) {
                        hakmilik.setKodStatusHakmilik(pBetulH.getStatusHakmilik());
                        hakmilik.setTarikhBatal(new java.util.Date());
                    }
//                    if (pBetulH.getStatusHakmilikSemasa() != null) {
//                        hakmilik.setKodStatusHakmilik(pBetulH.getStatusHakmilikSemasa());
//                    }

                }
                pService.updateHakmilik(hakmilik);
                hakmilikDAO.save(hakmilik);
            }
//BETP - Pembetulan 380 - Pelan
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETP")) {
                if (pBetulH.getNoPelan() != null) {
                    hakmilik.setNoPelan(pBetulH.getNoPelan());
                }
            }

//BETLP - Pembetulan 380 - Menghidupkan Hakmilik Luput(Silap Daftar)
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETLP")) {
                if (pBetulH.getTarikhLuput() != null) {
                    hakmilik.setTarikhLuput(pBetulH.getTarikhLuput());
                    hakmilik.setTempohPegangan(pBetulH.getTempohPengangan());
                }

                Akaun akaun = regService.getAkaunLama(hakmilik.getIdHakmilik());
                if (akaun != null) {
                    akaun.setStatus(kodStatusAkaunDAO.findById("A"));
                    info = akaun.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new Date());
                    akaun.setInfoAudit(info);
                    regService.saveOrUpdateWTrans(akaun);
                }

            }

            pService.updateHakmilik(hakmilik);
            LOG.info("Update Hakmilik END");
        }
    }

    private void beten(StageContext context, Permohonan permohonan) {
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        List<PermohonanAtasPerserahan> mapList = pService.findAtasMohon(permohonan.getIdPermohonan());
        for (PermohonanAtasPerserahan pbMap : mapList) {
            hakmilikUrusan = pService.findUrusan(String.valueOf(pbMap.getUrusan().getIdUrusan()));
            LOG.info("Update Existing Record");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikUrusan.setInfoAudit(info);
            hakmilikUrusan.setAktif('T');
            hakmilikUrusan.setHakmilik(hakmilikUrusan.getHakmilik());
            pService.updateHakmilikUrusan(hakmilikUrusan);
            List<HakmilikPihakBerkepentingan> hpbList = pService.findHakmilikPihakActiveSelainPemilik(String.valueOf(pbMap.getUrusan().getIdUrusan()));
            if (hpbList != null) {
                for (HakmilikPihakBerkepentingan hpk : hpbList) {
                    hpb = pService.findByIdHp(String.valueOf(hpk.getIdHakmilikPihakBerkepentingan()));
                    hpb.setAktif('T');
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    hpb.setInfoAudit(info);
                    pService.updateHakmilikPihakBerkepentingan(hpb);
                }
            }
        }
    }

    private void bethm(StageContext context, Permohonan permohonan) {
        LOG.info("/* BETHM :: UPDATE URUSAN YANG DIBETULKAN */");
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        List<PermohonanPembetulanHakmilik> permohonanBetulHakmilikList = pService.findidPermohonan(permohonan.getIdPermohonan());
        for (PermohonanPembetulanHakmilik pBetulL : permohonanBetulHakmilikList) {
            // add by azri 31/7/2013 : update table mohon_hakmilik and mohon_ruj_luar
            String idHakmilikLama = pBetulL.getHakmilik().getHakmilik().getIdHakmilik();  // get id hakmilik lama      
            String idHakmilikBaru = pBetulL.getIdHakmilik();                  // get id_hakmilik baru
            Hakmilik hmBaru = hakmilikDAO.findById(idHakmilikBaru);           // Hakmilik id_hakmilik_baru
            String idMohon = pBetulL.getPermohonan().getIdPermohonan();       // get id_mohon BETHM
            long idAu = pBetulL.getIdAtasUrusan();                            // get id mohon_atas_urusan
            PermohonanAtasPerserahan atasUrusan = pService.findByIdAU(idAu, idMohon);     // find data in mohon_atas_urusan
            String idMohonBetul = atasUrusan.getUrusan().getIdPerserahan();  // get id_mohon urusan pembetulan

            /* UPDATE MOHON_RUJ_LUAR */
            PermohonanRujukanLuar mrl = pService.findRujukanIDMohonHakmilik(idMohonBetul, idHakmilikLama);
            if (mrl != null) {
                LOG.info("/* UPDATE MOHON_RUJ_LUAR */");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                mrl.setInfoAudit(info);
                mrl.setHakmilik(hmBaru);
                if (mrl.getKodRujukan() == null) {
                    kodRujukan = kodRujukanDAO.findById("FL");
                    mrl.setKodRujukan(kodRujukan);
                }
                notaService.simpanPermohonanRujLuar(mrl);
            }

            /* UPDATE MOHON_HAKMILIK */
            HakmilikPermohonan hMohon = pService.findByIdHakmilikIdPermohonan(idMohonBetul, idHakmilikLama);
            if (hMohon != null) {
                LOG.info("/* UPDATE MOHON_HAKMILIK */");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hMohon.setInfoAudit(info);
                hMohon.setHakmilik(hmBaru);
                pService.updateMohonHakmilik(hMohon);
            }

            /* UPDATE HAKMILIK_PIHAK */
            //add by azri 8/7/2013: update table hakmilik_pihak 
            LOG.info("/* UPDATE HAKMILIK_PIHAK */");
            PermohonanAtasPerserahan mohonAtasUrusan = pService.findByIdAU(idAu, permohonan.getIdPermohonan());
            long idUrusan = mohonAtasUrusan.getUrusan().getIdUrusan();
            // update Hakmilik_Pihak for old id_hakmilik
            List<HakmilikPihakBerkepentingan> listPihakLama = pService.findByIdUrusanAndIdHakmilik(idUrusan, idHakmilikLama);
            if (listPihakLama.size() > 0) {
                LOG.info("/* UPDATE HAKMILIK_PIHAK - old */");
                for (HakmilikPihakBerkepentingan pihakLama : listPihakLama) {
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    pihakLama.setInfoAudit(info);
                    pihakLama.setAktif('T'); //set NOT aktif
                    pService.save(pihakLama);
                }
            }
            List<HakmilikPihakBerkepentingan> listPihakLamaBatal = pService.findByIdUrusanBatalAndIdHakmilik(idUrusan, idHakmilikLama);
            if (listPihakLamaBatal.size() > 0) {
                for (HakmilikPihakBerkepentingan pihakLama : listPihakLamaBatal) {
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    pihakLama.setInfoAudit(info);
                    pihakLama.setAktif('Y'); //set aktif
                    pService.save(pihakLama);
                }
            }

            // update Hakmilik_Pihak for new id hakmilik 
            List<HakmilikPihakBerkepentingan> listPihakBaru = pService.findByIdUrusanAndIdHakmilik(idUrusan, idHakmilikBaru);
            if (listPihakBaru.size() > 0) {
                LOG.info("/* UPDATE HAKMILIK_PIHAK - new */");
                for (HakmilikPihakBerkepentingan pihakBaru : listPihakBaru) {
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    pihakBaru.setInfoAudit(info);
                    pihakBaru.setAktif('Y'); // set aktif
                    pService.save(pihakBaru);
                }
            }
            List<HakmilikPihakBerkepentingan> listPihakBaruBatal = pService.findByIdUrusanBatalAndIdHakmilik(idUrusan, idHakmilikBaru);
            if (listPihakBaruBatal.size() > 0) {
                for (HakmilikPihakBerkepentingan pihakBaru : listPihakBaruBatal) {
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    pihakBaru.setInfoAudit(info);
                    pihakBaru.setAktif('T'); //set NOT aktif
                    pService.save(pihakBaru);
                }
            }

            /* UPDATE HAKMILIK_URUSAN*/
            List<PermohonanAtasPerserahan> mapList = pService.findAtasMohon(pBetulL.getPermohonan().getIdPermohonan());
            for (PermohonanAtasPerserahan pbMap : mapList) {
                hakmilikUrusan = hakmilikUrusanDAO.findById(pbMap.getUrusan().getIdUrusan());
                LOG.info("hakmilikUrusan.getIdPerserahan() =" + hakmilikUrusan.getIdPerserahan());
                LOG.info("hakmilikUrusan.getHakmilik().getIdHakmilik()" + hakmilikUrusan.getHakmilik().getIdHakmilik());
                String idMohonBaru = hakmilikUrusan.getIdPerserahan();
                String idHakmilik2 = hakmilikUrusan.getHakmilik().getIdHakmilik();
                List<HakmilikPermohonan> hpList = pService.findByIdHakmilikIdPermohonanList(idMohonBaru, idHakmilik2);
                for (HakmilikPermohonan hp : hpList) {
                    if (hp.getHakmilik().getIdHakmilik().equals(hakmilikUrusan.getHakmilik().getIdHakmilik())
                            && hp.getPermohonan().getIdPermohonan().equals(hakmilikUrusan.getIdPerserahan())) {
//                HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(hakmilikUrusan.getHakmilik().getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
                        LOG.info(" /* UPDATE HAKMILIK_URUSAN */ ");
                        Hakmilik hmk = new Hakmilik();
                        hmk.setIdHakmilik(pBetulL.getIdHakmilik());
                        hakmilikUrusan.setHakmilik(hmk);
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        hakmilikUrusan.setInfoAudit(info);
                        pService.updateHakmilikUrusan(hakmilikUrusan);
                    }
                }
            }
        }
    }

    private void updateNoVersi3K(Permohonan permohonan, InfoAudit infoAudit) throws Exception {
        //todo check if there is no DHKE generate
        LOG.debug("[ updateNoVersi start ]");
        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilikUpdate = new ArrayList<Hakmilik>();

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            List<Hakmilik> hakmilik3k = strService.findIdHakmilikByIdHakmilikInduk(hp.getHakmilik().getIdHakmilik());
            for (Hakmilik hm : hakmilik3k) {
                if (hm == null || hm.getIdHakmilikInduk() == null) {
                    continue;
                }
                LOG.debug("hakmilik to update no versi [ " + hm.getIdHakmilik() + " ]");
                infoAudit = hm.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new Date());
                hm.setNoVersiDhde(hm.getNoVersiDhde() + 1);
                hm.setNoVersiDhke(hm.getNoVersiDhke() + 1);
                hm.setVersion(hm.getVersion() + 1);
                hm.setNoVersiIndeksStrata(hm.getNoVersiIndeksStrata() + 1);
                hm.setInfoAudit(infoAudit);
                senaraiHakmilikUpdate.add(hm);
            }
        }

        if (!senaraiHakmilikUpdate.isEmpty()) {
            regService.simpanHakmilikList(senaraiHakmilikUpdate);
        }
    }

    private void updateHakmilikSTR(StageContext context, Permohonan permohonan) throws ParseException {
        LOG.info("Update Hakmilik Start");
        Hakmilik hakmilik = new Hakmilik();
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());

        InfoAudit infoMasuk = new InfoAudit();
        infoMasuk.setDimasukOleh(peng);
        infoMasuk.setTarikhMasuk(new java.util.Date());

//        info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
//        info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
        hakmilik.setInfoAudit(info);

        //Pembetulan 380 hm strata
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET3K")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET4K")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            List<HakmilikPermohonan> hmList = pService.findByIdPermohonanList(permohonan.getIdPermohonan());
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET4K")) {
                for (HakmilikPermohonan hm : hmList) {
                    List<HakmilikPetakAksesori> aksrList = strService.findBngnAks(hm.getHakmilik().getIdHakmilik());
                    if (aksrList.size() > 0) {
                        strService.deleteHakmilikAksr(aksrList.get(0).getHakmilik().getIdHakmilik());
                    }
                }
            }
            for (HakmilikPermohonan hm : hmList) {
                List<Hakmilik> hakmilik3k = strService.findIdHakmilikByIdHakmilikInduk(hm.getHakmilik().getIdHakmilik());
                List<PermohonanPihakKemaskini> permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(permohonan.getIdPermohonan(), hm.getHakmilik().getIdHakmilik());
                for (PermohonanPihakKemaskini kkini : permohonanPihakKemaskiniList) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET3K")) {
                        for (Hakmilik hk : hakmilik3k) {
                            if (kkini.getNamaMedan().equals("noFailSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setNoFail(kkini.getNilaiBaru());
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("tarikhDaftarSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setTarikhDaftar(sdf.parse(kkini.getNilaiBaru()));
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("nopuSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setNoPu(kkini.getNilaiBaru());
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("noBukuDaftarSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setNoBukuDaftarStrata(kkini.getNilaiBaru());
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("noSkimSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setNoSkim(kkini.getNilaiBaru());
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("jumSyerSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    hk.setJumlahUnitSyer(Integer.parseInt(kkini.getNilaiBaru()));
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                            if (kkini.getNamaMedan().equals("bdnUrusSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    BadanPengurusan bdn = strService.findBdnIdHm380(permohonan.getIdPermohonan(),
                                            hm.getHakmilik().getIdHakmilik());
                                    hk.setBadanPengurusan(bdn);
                                    hk.setInfoAudit(info);
                                    hakmilikDAO.saveOrUpdate(hk);
                                }
                            }
                        }
                    }
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET4K")) {
                        hakmilik = hakmilikDAO.findById(hm.getHakmilik().getIdHakmilik());
                        if (kkini.getNamaMedan().equals("unitSyerSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setUnitSyer(BigDecimal.valueOf(Double.valueOf(kkini.getNilaiBaru())));
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }
                        if (kkini.getNamaMedan().equals("noPelanPetakSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setNoPelan(kkini.getNilaiBaru());
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }
                        if (kkini.getNamaMedan().equals("noPetakSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setNoPetak(kkini.getNilaiBaru());
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }
                        if (kkini.getNamaMedan().equals("luasPetakSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setLuas(BigDecimal.valueOf(Double.valueOf(kkini.getNilaiBaru())));
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }
                        if (kkini.getNamaMedan().equals("cukaiPetakSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setCukai(BigDecimal.valueOf(Double.valueOf(kkini.getNilaiBaru())));
                                hakmilik.setCukaiSebenar(BigDecimal.valueOf(Double.valueOf(kkini.getNilaiBaru())));
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }
                        if (kkini.getNamaMedan().equals("noTingkatSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                hakmilik.setNoTingkat(kkini.getNilaiBaru());
                                hakmilik.setInfoAudit(info);
                                hakmilikDAO.saveOrUpdate(hakmilik);
                            }
                        }

//                        List<PermohonanPihakKemaskini> kkiniPetakAksr = pService.findListbyIdMohonIdHmMedan(permohonan.getIdPermohonan(),
//                                hm.getHakmilik().getIdHakmilik(), "petakAksrSTR");
                        if (kkini.getNamaMedan().equals("petakAksrSTR")) {
                            if (kkini.getNilaiBaru() != null) {
                                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                hpa.setCawangan(hakmilik.getCawangan());
                                hpa.setHakmilik(hakmilik);
                                hpa.setInfoAudit(infoMasuk);
                                hpa.setLokasi(kkini.getNilaiBerkaitan1());
                                if (kkini.getNilaiBerkaitan2() != null) {
                                    hpa.setLuas(BigDecimal.valueOf(Double.valueOf(kkini.getNilaiBerkaitan2())));
                                }
                                hpa.setNama(kkini.getNilaiBaru());
                                hpa.setPetakSangkut(hakmilik.getNoBangunan() + "-" + hakmilik.getNoTingkat() + "-" + hakmilik.getNoPetak());
                                strService.simpanhakmilikPetakAks(hpa);
                            }
                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET4K")) {
                    for (HakmilikPermohonan mh : hmList) {
                        List<PermohonanPihakKemaskini> pihakKkini = pService.findListbyIdMohonIdHm(permohonan.getIdPermohonan(), mh.getHakmilik().getIdHakmilik());
                        List<PermohonanPihakKemaskini> kkiniPelanAksr = pService.findListbyIdMohonIdHmMedan(permohonan.getIdPermohonan(),
                                mh.getHakmilik().getIdHakmilik(), "noPelanSTR");
                        List<HakmilikPetakAksesori> aksrList = strService.findBngnAks(mh.getHakmilik().getIdHakmilik());

                        int i = 1;
                        int x = 1;
                        for (PermohonanPihakKemaskini kkini : pihakKkini) {
                            if (kkini.getNamaMedan().equals("noPelanSTR")) {
                                if (kkini.getNilaiBaru() != null) {
                                    if (kkiniPelanAksr.size() <= aksrList.size()) {
                                        for (HakmilikPetakAksesori hpaList : aksrList) {
                                            if (i == 1) {
                                                if (hpaList.getNoPelan() == null) {
                                                    hpaList.setNoPelan(kkini.getNilaiBaru());
                                                    strService.simpanhakmilikPetakAks(hpaList);
                                                    i++;
                                                }
                                            }
                                            if (i <= x) {
                                                if (hpaList.getNoPelan() == null) {
                                                    hpaList.setNoPelan(kkini.getNilaiBaru());
                                                    strService.simpanhakmilikPetakAks(hpaList);
                                                    i++;
                                                }
                                            }
                                        }
                                    } else if (i <= aksrList.size()) {
                                        for (HakmilikPetakAksesori hpaList : aksrList) {
                                            if (hpaList.getNoPelan() == null) {
                                                hpaList.setNoPelan(kkini.getNilaiBaru());
                                                strService.simpanhakmilikPetakAks(hpaList);
                                                i++;
                                            }
                                        }
                                    } else {
                                        HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                                        hpa.setCawangan(mh.getCawangan());
                                        hpa.setHakmilik(mh.getHakmilik());
                                        hpa.setInfoAudit(infoMasuk);
                                        hpa.setLokasi(kkini.getIdPermohonanLama());
                                        hpa.setNama(null);
                                        hpa.setNoPelan(kkini.getNilaiBaru());
                                        hpa.setPetakSangkut(mh.getHakmilik().getNoBangunan() + "-" + mh.getHakmilik().getNoTingkat()
                                                + "-" + mh.getHakmilik().getNoPetak());
                                        strService.simpanhakmilikPetakAks(hpa);
                                    }
                                }
                                x++;
                            }
                        }
                    }
                }
            }
        }

        if (hakmilik.getIdHakmilik() != null) {
            pService.updateHakmilik(hakmilik);
        }
        LOG.info("Update Hakmilik END");
    }

    private void updateHakmilikUrusanSTR(StageContext context, Permohonan permohonan) throws ParseException {
        LOG.info("Update Hakmilik Urusan Start");
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());

        InfoAudit infoMasuk = new InfoAudit();
        infoMasuk.setDimasukOleh(peng);
        infoMasuk.setTarikhMasuk(new java.util.Date());

        info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
        info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());

        //Pembetulan 380 hm strata
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET3K")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            List<HakmilikPermohonan> hmList = pService.findByIdPermohonanList(permohonan.getIdPermohonan());

            for (HakmilikPermohonan hm : hmList) {
                List<Hakmilik> hakmilik3k = strService.findIdHakmilikByIdHakmilikInduk(hm.getHakmilik().getIdHakmilik());
                for (Hakmilik hk : hakmilik3k) {
                    HakmilikUrusan hu = new HakmilikUrusan();
                    hu.setHakmilik(hk);
                    hu.setKodUrusan(permohonan.getKodUrusan());
                    hu.setTarikhDaftar(hm.getPermohonan().getTarikhKeputusan());
                    hu.setCawangan(permohonan.getCawangan());
                    hu.setDaerah(hk.getDaerah());
                    hu.setAktif('Y');
                    hu.setIdPerserahan(permohonan.getIdPermohonan());
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    hu.setInfoAudit(info);
                    pService.simpanHakmilikUrusan(hu);
                }
            }

        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BET4K")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            List<HakmilikPermohonan> hmList = pService.findByIdPermohonanList(permohonan.getIdPermohonan());

            for (HakmilikPermohonan hm : hmList) {
                HakmilikUrusan hu = new HakmilikUrusan();
                hu.setIdPerserahan(permohonan.getIdPermohonan());
                hu.setHakmilik(hm.getHakmilik());
                hu.setKodUrusan(hm.getPermohonan().getKodUrusan());
                hu.setTarikhDaftar(hm.getPermohonan().getTarikhKeputusan());
                hu.setCawangan(permohonan.getCawangan());
                hu.setDaerah(hm.getHakmilik().getDaerah());
                hu.setAktif('Y');
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hu.setInfoAudit(info);
                pService.simpanHakmilikUrusan(hu);
            }

        }

        LOG.info("Update Hakmilik END");
    }

    private void betpb(StageContext context, Permohonan permohonan) {
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        List<HakmilikWaris> listhw = pService.findWarisIDMohon(permohonan.getIdPermohonan());
        List<PermohonanAtasPihakBerkepentingan> ListAtasPihak = pService.findByAtasPihakByIdMohon(permohonan.getIdPermohonan());
//        PermohonanAtasPerserahan mau = pService.findMohonAtsUrusanbyIdMhn(permohonan.getIdPermohonan());
        for (PermohonanAtasPihakBerkepentingan pAtasPihakL : ListAtasPihak) {
            Long idAtasPihak = pAtasPihakL.getIdAtasPihak();
            if (pAtasPihakL.getJenisTugasan().equals("KEMASKINI")) {
                LOG.info("permohonan.getIdPermohonan() ::" + permohonan.getIdPermohonan());
                LOG.info("String.valueOf(idAtasPihak) ::" + String.valueOf(idAtasPihak));
                List<PermohonanPihakKemaskini> phkList = pService.findAtasIdHp(permohonan.getIdPermohonan(), String.valueOf(idAtasPihak));
                for (PermohonanPihakKemaskini pbMap : phkList) {
//                LOG.info("test kod pb = " + pbMap.getJenis().getKod());
                    KodJenisPihakBerkepentingan kodPb = pbMap.getJenis();
                    String idPermohonanLama = pbMap.getIdPermohonanLama();
                    if (idPermohonanLama != null) {
                        permohonanLama = permohonanDAO.findById(idPermohonanLama);
                    }
                    if (kodPb.getKod().equals("PM") || kodPb.getKod().equals("BP")) {
                        if (idPermohonanLama == null) {
                            hpb = pService.findByIdHp(String.valueOf(pAtasPihakL.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan()));
                            pihak = pihakDAO.findById(hpb.getPihak().getIdPihak());

                            String hakmilikPihak = pAtasPihakL.getPihakBerkepentingan().getHakmilik().getIdHakmilik();

                            Alamat alamatMP = new Alamat();
                            Alamat alamatP = new Alamat();

                            LOG.info("Update Existing Record");
                            if (pbMap.getNamaMedan().equals("nama")) {
                                pihak.setNama(pbMap.getNilaiBaru());
                                hpb.setNama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                KodJenisPengenalan kjp = new KodJenisPengenalan();
                                kjp.setKod(pbMap.getNilaiBaru());
                                pihak.setJenisPengenalan(kjp);
                                hpb.setJenisPengenalan(kjp);

                            }
                            if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                hpb.setNoPengenalan(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                                hpb.setNoPengenalanLama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("bangsa")) {
                                KodBangsa kb = new KodBangsa();
                                kb.setKod(pbMap.getNilaiBaru());
                                pihak.setBangsa(kb);
                            }
                            if (pbMap.getNamaMedan().equals("jantina")) {
                                pihak.setKodJantina(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("warganegara")) {
                                KodWarganegara kw = new KodWarganegara();
                                kw.setKod(pbMap.getNilaiBaru());
                                pihak.setWargaNegara(kw);
                                hpb.setWargaNegara(kw);
                            }
                            if (pbMap.getNamaMedan().equals("jenisPB")) {
                                KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                kjpb.setKod(pbMap.getNilaiBaru());
                                hpb.setJenis(kjpb);
                            }
                            if (pbMap.getNamaMedan().equals("penyebut")) {
                                hpb.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                hpb.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("pembilang")) {
                                hpb.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                hpb.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("syarikatTubuh")) {
                                hpb.setPenubuhanSyarikat(kodPenubuhanSyarikatDAO.findById(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("alamat1")) {
                                pihak.setAlamat1(pbMap.getNilaiBaru());
                                hpb.setAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat2")) {
                                if (pbMap.getNilaiBaru() == null || pbMap.getNilaiBaru() == "null") {
                                    pihak.setAlamat2(null);
                                    hpb.setAlamat2(null);
                                } else {
                                    pihak.setAlamat2(pbMap.getNilaiBaru());
                                    hpb.setAlamat2(pbMap.getNilaiBaru());
                                }
                            }
                            if (pbMap.getNamaMedan().equals("alamat3")) {
                                if (pbMap.getNilaiBaru() == null || pbMap.getNilaiBaru() == "null") {
                                    pihak.setAlamat3(null);
                                    hpb.setAlamat3(null);
                                } else {
                                    pihak.setAlamat3(pbMap.getNilaiBaru());
                                    hpb.setAlamat3(pbMap.getNilaiBaru());
                                }

                            }
                            if (pbMap.getNamaMedan().equals("alamat4")) {
                                if (pbMap.getNilaiBaru() == null || pbMap.getNilaiBaru() == "null") {
                                    pihak.setAlamat4(null);
                                    hpb.setAlamat4(null);
                                } else {
                                    pihak.setAlamat4(pbMap.getNilaiBaru());
                                    hpb.setAlamat4(pbMap.getNilaiBaru());
                                }
                            }
                            if (pbMap.getNamaMedan().equals("poskod")) {
                                pihak.setPoskod(pbMap.getNilaiBaru());
                                hpb.setPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeri")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setNegeri(kn);
                                hpb.setNegeri(kn);
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                pihak.setSuratPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setSuratNegeri(kn);
                            }

                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            pihak.setInfoAudit(info);
                            hpb.setInfoAudit(info);
                            pService.updatePihak(pihak);
                            pService.updateHakmilikPihakBerkepentingan(hpb);
                            pService.save(hpb);
                            hakmilikPihakBerkepentinganDAO.saveOrUpdate(hpb);
                        }
                    }
                    if (!kodPb.getKod().equals("PM") || (!kodPb.getKod().equals("PG"))) {
                        if (idPermohonanLama == null) {
                            hpb = pService.findByIdHp(String.valueOf(pAtasPihakL.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan()));
                            pihak = pihakDAO.findById(hpb.getPihak().getIdPihak());

                            String hakmilikPihak = pAtasPihakL.getPihakBerkepentingan().getHakmilik().getIdHakmilik();

                            Alamat alamatMP = new Alamat();
                            Alamat alamatP = new Alamat();

                            LOG.info("Update Existing Record");
                            if (pbMap.getNamaMedan().equals("nama")) {
                                pihak.setNama(pbMap.getNilaiBaru());
                                hpb.setNama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                KodJenisPengenalan kjp = new KodJenisPengenalan();
                                kjp.setKod(pbMap.getNilaiBaru());
                                pihak.setJenisPengenalan(kjp);
                                hpb.setJenisPengenalan(kjp);

                            }
                            if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                hpb.setNoPengenalan(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                                hpb.setNoPengenalanLama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("bangsa")) {
                                KodBangsa kb = new KodBangsa();
                                kb.setKod(pbMap.getNilaiBaru());
                                pihak.setBangsa(kb);
                            }
                            if (pbMap.getNamaMedan().equals("jantina")) {
                                pihak.setKodJantina(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("warganegara")) {
                                KodWarganegara kw = new KodWarganegara();
                                kw.setKod(pbMap.getNilaiBaru());
                                pihak.setWargaNegara(kw);
                                hpb.setWargaNegara(kw);
                            }
                            if (pbMap.getNamaMedan().equals("jenisPB")) {
                                KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                kjpb.setKod(pbMap.getNilaiBaru());
                                hpb.setJenis(kjpb);
                            }
                            if (pbMap.getNamaMedan().equals("penyebut")) {
                                hpb.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                hpb.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("pembilang")) {
                                hpb.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                hpb.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("syarikatTubuh")) {
                                hpb.setPenubuhanSyarikat(kodPenubuhanSyarikatDAO.findById(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("alamat1")) {
                                pihak.setAlamat1(pbMap.getNilaiBaru());
                                hpb.setAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat2")) {
                                pihak.setAlamat2(pbMap.getNilaiBaru());
                                hpb.setAlamat2(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat3")) {
                                pihak.setAlamat3(pbMap.getNilaiBaru());
                                hpb.setAlamat3(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat4")) {
                                pihak.setAlamat4(pbMap.getNilaiBaru());
                                hpb.setAlamat4(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("poskod")) {
                                pihak.setPoskod(pbMap.getNilaiBaru());
                                hpb.setPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeri")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setNegeri(kn);
                                hpb.setNegeri(kn);
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                pihak.setSuratPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setSuratNegeri(kn);
                            }

                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            pihak.setInfoAudit(info);
                            hpb.setInfoAudit(info);
                            pService.updatePihak(pihak);
                            pService.updateHakmilikPihakBerkepentingan(hpb);
                            pService.save(hpb);
                            hakmilikPihakBerkepentinganDAO.saveOrUpdate(hpb);
                        }
                    }
//                LOG.info("test kod pb2 = " + pbMap.getJenis().getKod());
                    if (kodPb.getKod().equals("PM")|| kodPb.getKod().equals("BP")) {
                        if (idPermohonanLama != null) {
                            Pemohon pmhn = pAtasPihakL.getPemohon();
                            if (pmhn != null) {
                                LOG.info("ape benda dlm nie = : " + pmhn.getIdPemohon());
                                if (pmhn.getPihak() != null) {
                                    Long idPihak = pmhn.getPihak().getIdPihak();
                                    LOG.info("idPihak =" + idPihak);
                                    pihak = pihakDAO.findById(idPihak);
                                }

                                Alamat alamatMP = new Alamat();
                                Alamat alamatP = new Alamat();
                                Alamat alamat = pmhn.getAlamat();
                                if (alamat == null) {
                                    alamat = new Alamat();
                                }

                                LOG.info("Update Existing Record");
                                if (pbMap.getNamaMedan().equals("nama")) {
                                    pihak.setNama(pbMap.getNilaiBaru());
                                    pmhn.setNama(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                    KodJenisPengenalan kjp = new KodJenisPengenalan();
                                    kjp.setKod(pbMap.getNilaiBaru());
                                    pihak.setJenisPengenalan(kjp);
                                    pmhn.setJenisPengenalan(kjp);

                                }
                                if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                    pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                    pmhn.setNoPengenalan(pbMap.getNilaiBaru());
                                }

                                if (pbMap.getNamaMedan().equals("bangsa")) {
                                    KodBangsa kb = new KodBangsa();
                                    kb.setKod(pbMap.getNilaiBaru());
                                    pihak.setBangsa(kb);
                                }
                                if (pbMap.getNamaMedan().equals("jantina")) {
                                    pihak.setKodJantina(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("warganegara")) {
                                    KodWarganegara kw = new KodWarganegara();
                                    kw.setKod(pbMap.getNilaiBaru());
                                    pihak.setWargaNegara(kw);
                                    pmhn.setWargaNegara(kw);
                                }
                                if (pbMap.getNamaMedan().equals("jenisPB")) {
                                    KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                    kjpb.setKod(pbMap.getNilaiBaru());
                                    pmhn.setJenis(kjpb);
                                }
                                if (pbMap.getNamaMedan().equals("penyebut")) {
                                    pmhn.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                    pmhn.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                }
                                if (pbMap.getNamaMedan().equals("pembilang")) {
                                    pmhn.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                    pmhn.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                }
                                if (pbMap.getNamaMedan().equals("alamat1")) {
                                    pihak.setAlamat1(pbMap.getNilaiBaru());
                                    alamat.setAlamat1(pbMap.getNilaiBaru());

                                }
                                if (pbMap.getNamaMedan().equals("alamat2")) {
                                    pihak.setAlamat2(pbMap.getNilaiBaru());
                                    alamat.setAlamat2(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamat3")) {
                                    pihak.setAlamat3(pbMap.getNilaiBaru());
                                    alamat.setAlamat3(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamat4")) {
                                    pihak.setAlamat4(pbMap.getNilaiBaru());
                                    alamat.setAlamat4(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("poskod")) {
                                    pihak.setPoskod(pbMap.getNilaiBaru());
                                    alamat.setPoskod(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("negeri")) {
                                    KodNegeri kn = new KodNegeri();
                                    kn.setKod(pbMap.getNilaiBaru());
                                    alamat.setNegeri(kn);
                                    pihak.setPoskod(pbMap.getNilaiBaru());
                                }

                                if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                    pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                    pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                    pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                    pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                    pihak.setSuratPoskod(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                    KodNegeri kn = new KodNegeri();
                                    kn.setKod(pbMap.getNilaiBaru());
                                    pihak.setSuratNegeri(kn);
                                }

                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                pihak.setInfoAudit(info);
                                pmhn.setInfoAudit(info);
                                pService.updatePihak(pihak);
                                pService.updatePemohon(pmhn);
                            }
                        }
                    }
                    if (!kodPb.getKod().equals("PM")) {
                        if (idPermohonanLama != null) {
                            Pemohon pmhn = pAtasPihakL.getPemohon();
                            if (pmhn != null) {
                                LOG.info("ape benda dlm nie = : " + pmhn.getIdPemohon());
                                if (pmhn.getPihak() != null) {
                                    Long idPihak = pmhn.getPihak().getIdPihak();
                                    LOG.info("idPihak =" + idPihak);
                                    pihak = pihakDAO.findById(idPihak);
                                }

                                Alamat alamatMP = new Alamat();
                                Alamat alamatP = new Alamat();
                                Alamat alamat = pmhn.getAlamat();
                                if (alamat == null) {
                                    alamat = new Alamat();
                                }

                                LOG.info("Update Existing Record");
                                if (pbMap.getNamaMedan().equals("nama")) {
                                    pihak.setNama(pbMap.getNilaiBaru());
                                    pmhn.setNama(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                    KodJenisPengenalan kjp = new KodJenisPengenalan();
                                    kjp.setKod(pbMap.getNilaiBaru());
                                    pihak.setJenisPengenalan(kjp);
                                    pmhn.setJenisPengenalan(kjp);

                                }
                                if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                    pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                    pmhn.setNoPengenalan(pbMap.getNilaiBaru());
                                }

                                if (pbMap.getNamaMedan().equals("bangsa")) {
                                    KodBangsa kb = new KodBangsa();
                                    kb.setKod(pbMap.getNilaiBaru());
                                    pihak.setBangsa(kb);
                                }
                                if (pbMap.getNamaMedan().equals("jantina")) {
                                    pihak.setKodJantina(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("warganegara")) {
                                    KodWarganegara kw = new KodWarganegara();
                                    kw.setKod(pbMap.getNilaiBaru());
                                    pihak.setWargaNegara(kw);
                                    pmhn.setWargaNegara(kw);
                                }
                                if (pbMap.getNamaMedan().equals("jenisPB")) {
                                    KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                    kjpb.setKod(pbMap.getNilaiBaru());
                                    pmhn.setJenis(kjpb);
                                }
                                if (pbMap.getNamaMedan().equals("penyebut")) {
                                    pmhn.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                    pmhn.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                }
                                if (pbMap.getNamaMedan().equals("pembilang")) {
                                    pmhn.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                    pmhn.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                }
                                if (pbMap.getNamaMedan().equals("alamat1")) {
                                    pihak.setAlamat1(pbMap.getNilaiBaru());
                                    alamat.setAlamat1(pbMap.getNilaiBaru());

                                }
                                if (pbMap.getNamaMedan().equals("alamat2")) {
                                    pihak.setAlamat2(pbMap.getNilaiBaru());
                                    alamat.setAlamat2(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamat3")) {
                                    pihak.setAlamat3(pbMap.getNilaiBaru());
                                    alamat.setAlamat3(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamat4")) {
                                    pihak.setAlamat4(pbMap.getNilaiBaru());
                                    alamat.setAlamat4(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("poskod")) {
                                    pihak.setPoskod(pbMap.getNilaiBaru());
                                    alamat.setPoskod(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("negeri")) {
                                    KodNegeri kn = new KodNegeri();
                                    kn.setKod(pbMap.getNilaiBaru());
                                    alamat.setNegeri(kn);
                                    pihak.setPoskod(pbMap.getNilaiBaru());
                                }

                                if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                    pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                    pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                    pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                    pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                    pihak.setSuratPoskod(pbMap.getNilaiBaru());
                                }
                                if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                    KodNegeri kn = new KodNegeri();
                                    kn.setKod(pbMap.getNilaiBaru());
                                    pihak.setSuratNegeri(kn);
                                }

                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                pihak.setInfoAudit(info);
                                pmhn.setInfoAudit(info);
                                pService.updatePihak(pihak);
                                pService.updatePemohon(pmhn);
                            }
                        }
                    }
                    if (permohonanLama != null) {
//                       String kaveat = (permohonanLama.getKodUrusan().getKod().substring(4, 6));
//                       urusanKaveat
//                        if (permohonanLama.getKodUrusan().getKod().equals("PMT") || permohonanLama.getKodUrusan().getKod().equals(urusanKaveat)) {
                        if (permohonanLama.getKodUrusan().getKod().equals("PMT") || permohonanLama.getKodUrusan().getKod().equals("KVAK")
                                || permohonanLama.getKodUrusan().getKod().equals("KVAS") || permohonanLama.getKodUrusan().getKod().equals("KVAT")
                                || permohonanLama.getKodUrusan().getKod().equals("KVLK") || permohonanLama.getKodUrusan().getKod().equals("KVLP")
                                || permohonanLama.getKodUrusan().getKod().equals("KVLS") || permohonanLama.getKodUrusan().getKod().equals("KVLT")
                                || permohonanLama.getKodUrusan().getKod().equals("KVPK") || permohonanLama.getKodUrusan().getKod().equals("KVPP")
                                || permohonanLama.getKodUrusan().getKod().equals("KVPPT") || permohonanLama.getKodUrusan().getKod().equals("KVPS")
                                || permohonanLama.getKodUrusan().getKod().equals("KVPT") || permohonanLama.getKodUrusan().getKod().equals("KVSK")
                                || permohonanLama.getKodUrusan().getKod().equals("KVSPT") || permohonanLama.getKodUrusan().getKod().equals("KVSS")
                                || permohonanLama.getKodUrusan().getKod().equals("KVST") || permohonanLama.getKodUrusan().getKod().equals("HTB")) {
                            if (kodPb.getKod().equals("PM") || (kodPb.getKod().equals("PG") || (kodPb.getKod().equals("PKS")) || kodPb.getKod().equals("BP"))) {
                                mohonPihak = pAtasPihakL.getPermohonanPihak();
//                                PermohonanPihakk mPihak2 = regService.findm pAtasPihakL.getPermohonanPihak()
                                if (mohonPihak != null) {
                                    pihak = pihakDAO.findById(mohonPihak.getPihak().getIdPihak());

                                    LOG.info(mohonPihak.getIdPermohonanPihak());
                                    LOG.info(permohonanLama.getIdPermohonan());
                                    LOG.info(mohonPihak.getIdPermohonanLama());

                                    Alamat alamatMP = new Alamat();
                                    Alamat alamatP = new Alamat();
                                    Alamat alamat = mohonPihak.getAlamat();
                                    if (alamat == null) {
                                        alamat = new Alamat();
                                    }

                                    LOG.info("Update Existing Record");
                                    if (pbMap.getNamaMedan().equals("nama")) {
                                        pihak.setNama(pbMap.getNilaiBaru());
                                        mohonPihak.setNama(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                        KodJenisPengenalan kjp = new KodJenisPengenalan();
                                        kjp.setKod(pbMap.getNilaiBaru());
                                        pihak.setJenisPengenalan(kjp);
                                        mohonPihak.setJenisPengenalan(kjp);

                                    }
                                    if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                        pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                        mohonPihak.setNoPengenalan(pbMap.getNilaiBaru());
                                    }
//                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
//                        mPihak.setNoPengenalanLama(pbMap.getNilaiBaru());
//                    }
                                    if (pbMap.getNamaMedan().equals("bangsa")) {
                                        KodBangsa kb = new KodBangsa();
                                        kb.setKod(pbMap.getNilaiBaru());
                                        pihak.setBangsa(kb);
                                    }
                                    if (pbMap.getNamaMedan().equals("jantina")) {
                                        pihak.setKodJantina(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("warganegara")) {
                                        KodWarganegara kw = new KodWarganegara();
                                        kw.setKod(pbMap.getNilaiBaru());
                                        pihak.setWargaNegara(kw);
                                        mohonPihak.setWargaNegara(kw);
                                    }
                                    if (pbMap.getNamaMedan().equals("jenisPB")) {
                                        KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                        kjpb.setKod(pbMap.getNilaiBaru());
                                        mohonPihak.setJenis(kjpb);
                                    }
                                    if (pbMap.getNamaMedan().equals("penyebut")) {
                                        mohonPihak.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                        mohonPihak.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                    }
                                    if (pbMap.getNamaMedan().equals("pembilang")) {
                                        mohonPihak.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                        mohonPihak.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat1")) {
                                        pihak.setAlamat1(pbMap.getNilaiBaru());
                                        alamat.setAlamat1(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat2")) {
                                        pihak.setAlamat2(pbMap.getNilaiBaru());
                                        alamat.setAlamat2(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat3")) {
                                        pihak.setAlamat3(pbMap.getNilaiBaru());
                                        alamat.setAlamat3(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat4")) {
                                        pihak.setAlamat4(pbMap.getNilaiBaru());
                                        alamat.setAlamat4(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("poskod")) {
                                        pihak.setPoskod(pbMap.getNilaiBaru());
                                        alamat.setPoskod(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("negeri")) {
                                        KodNegeri kn = new KodNegeri();
                                         kn.setKod(pbMap.getNilaiBaru());
                                         pihak.setNegeri(kn);
                                         alamat.setNegeri(kn);
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                        pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                        pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                        pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                        pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                        pihak.setSuratPoskod(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                        KodNegeri kn = new KodNegeri();
                                        kn.setKod(pbMap.getNilaiBaru());
                                        pihak.setSuratNegeri(kn);
                                    }

                                    info.setDimasukOleh(peng);
                                    info.setTarikhMasuk(new java.util.Date());
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    pihak.setInfoAudit(info);
                                    mohonPihak.setInfoAudit(info);
                                    pService.updatePihak(pihak);
//                    pService.updateHakmilikPihakBerkepentingan(hpb);
                                    pService.updatePermohonanPihak(mohonPihak);
                                } else if (pAtasPihakL.getPihakBerkepentingan() != null) {
                                    hpb = pService.findByIdHp(String.valueOf(pAtasPihakL.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan()));
                                    pihak = pihakDAO.findById(hpb.getPihak().getIdPihak());

                                    String hakmilikPihak = pAtasPihakL.getPihakBerkepentingan().getHakmilik().getIdHakmilik();

                                    Alamat alamatMP = new Alamat();
                                    Alamat alamatP = new Alamat();

                                    LOG.info("Update Existing Record");
                                    if (pbMap.getNamaMedan().equals("nama")) {
                                        pihak.setNama(pbMap.getNilaiBaru());
                                        hpb.setNama(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                        KodJenisPengenalan kjp = new KodJenisPengenalan();
                                        kjp.setKod(pbMap.getNilaiBaru());
                                        pihak.setJenisPengenalan(kjp);
                                        hpb.setJenisPengenalan(kjp);

                                    }
                                    if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                        pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                        hpb.setNoPengenalan(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                                        hpb.setNoPengenalanLama(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("bangsa")) {
                                        KodBangsa kb = new KodBangsa();
                                        kb.setKod(pbMap.getNilaiBaru());
                                        pihak.setBangsa(kb);
                                    }
                                    if (pbMap.getNamaMedan().equals("jantina")) {
                                        pihak.setKodJantina(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("warganegara")) {
                                        KodWarganegara kw = new KodWarganegara();
                                        kw.setKod(pbMap.getNilaiBaru());
                                        pihak.setWargaNegara(kw);
                                        hpb.setWargaNegara(kw);
                                    }
                                    if (pbMap.getNamaMedan().equals("jenisPB")) {
                                        KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                        kjpb.setKod(pbMap.getNilaiBaru());
                                        hpb.setJenis(kjpb);
                                    }
                                    if (pbMap.getNamaMedan().equals("penyebut")) {
                                        hpb.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                        hpb.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                    }
                                    if (pbMap.getNamaMedan().equals("pembilang")) {
                                        hpb.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                        hpb.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                    }
                                    if (pbMap.getNamaMedan().equals("syarikatTubuh")) {
                                        hpb.setPenubuhanSyarikat(kodPenubuhanSyarikatDAO.findById(pbMap.getNilaiBaru()));
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat1")) {
                                        pihak.setAlamat1(pbMap.getNilaiBaru());
                                        hpb.setAlamat1(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat2")) {
                                        pihak.setAlamat2(pbMap.getNilaiBaru());
                                        hpb.setAlamat2(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat3")) {
                                        pihak.setAlamat3(pbMap.getNilaiBaru());
                                        hpb.setAlamat3(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamat4")) {
                                        pihak.setAlamat4(pbMap.getNilaiBaru());
                                        hpb.setAlamat4(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("poskod")) {
                                        pihak.setPoskod(pbMap.getNilaiBaru());
                                        hpb.setPoskod(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("negeri")) {
                                        KodNegeri kn = new KodNegeri();
                                        kn.setKod(pbMap.getNilaiBaru());
                                        pihak.setNegeri(kn);
                                        hpb.setNegeri(kn);
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                        pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                        pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                        pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                        pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                        pihak.setSuratPoskod(pbMap.getNilaiBaru());
                                    }
                                    if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                        KodNegeri kn = new KodNegeri();
                                        kn.setKod(pbMap.getNilaiBaru());
                                        pihak.setSuratNegeri(kn);
                                    }

                                    info.setDimasukOleh(peng);
                                    info.setTarikhMasuk(new java.util.Date());
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    pihak.setInfoAudit(info);
                                    hpb.setInfoAudit(info);
                                    pService.updatePihak(pihak);
                                    pService.updateHakmilikPihakBerkepentingan(hpb);
                                    pService.save(hpb);
                                    hakmilikPihakBerkepentinganDAO.saveOrUpdate(hpb);
                                }

                            }
                        }
                    }
                    if (kodPb.getKod().equals("PG")) {

                        PermohonanPihak mPihak = pAtasPihakL.getPermohonanPihak();
                        if (mPihak != null) {
                            pihak = pihakDAO.findById(mPihak.getPihak().getIdPihak());

//                    String hakmilikPihak = pAtasPihakL.getPihakBerkepentingan().getHakmilik().getIdHakmilik();
                            Alamat alamatMP = new Alamat();
                            Alamat alamatP = new Alamat();
                            Alamat alamat = mPihak.getAlamat();
                            if (alamat == null) {
                                alamat = new Alamat();
                            }
                            LOG.info("pbMap.getNamaMedan()::" + pbMap.getNamaMedan());
                            LOG.info("pbMap.getNilaiBaru()::" + pbMap.getNilaiBaru());
                            LOG.info("mPihak.getAlamat()::" + mPihak.getAlamat());
                            LOG.info("pAtasPihakL.getPermohonanPihak()::" + pAtasPihakL.getPermohonanPihak());
                            LOG.info("Update Existing Record");
                            if (pbMap.getNamaMedan().equals("nama")) {
                                pihak.setNama(pbMap.getNilaiBaru());
                                mPihak.setNama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                KodJenisPengenalan kjp = new KodJenisPengenalan();
                                kjp.setKod(pbMap.getNilaiBaru());
                                pihak.setJenisPengenalan(kjp);
                                mPihak.setJenisPengenalan(kjp);

                            }
                            if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                pihak.setNoPengenalan(pbMap.getNilaiBaru());
                                mPihak.setNoPengenalan(pbMap.getNilaiBaru());
                            }
//                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
//                        mPihak.setNoPengenalanLama(pbMap.getNilaiBaru());
//                    }
                            if (pbMap.getNamaMedan().equals("bangsa")) {
                                KodBangsa kb = new KodBangsa();
                                kb.setKod(pbMap.getNilaiBaru());
                                pihak.setBangsa(kb);
                            }
                            if (pbMap.getNamaMedan().equals("jantina")) {
                                pihak.setKodJantina(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("warganegara")) {
                                KodWarganegara kw = new KodWarganegara();
                                kw.setKod(pbMap.getNilaiBaru());
                                pihak.setWargaNegara(kw);
                                mPihak.setWargaNegara(kw);
                            }
                            if (pbMap.getNamaMedan().equals("jenisPB")) {
                                KodJenisPihakBerkepentingan kjpb = new KodJenisPihakBerkepentingan();
                                kjpb.setKod(pbMap.getNilaiBaru());
                                mPihak.setJenis(kjpb);
                            }
                            if (pbMap.getNamaMedan().equals("penyebut")) {
                                mPihak.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                                mPihak.setJumlahPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("pembilang")) {
                                mPihak.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                                mPihak.setJumlahPembilang(Integer.parseInt(pbMap.getNilaiBaru()));
                            }
                            if (pbMap.getNamaMedan().equals("alamat1")) {
                                pihak.setAlamat1(pbMap.getNilaiBaru());
                                alamat.setAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat2")) {
                                pihak.setAlamat2(pbMap.getNilaiBaru());
                                alamat.setAlamat2(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat3")) {
                                pihak.setAlamat3(pbMap.getNilaiBaru());
                                alamat.setAlamat3(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamat4")) {
                                pihak.setAlamat4(pbMap.getNilaiBaru());
                                alamat.setAlamat4(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("poskod")) {
                                pihak.setPoskod(pbMap.getNilaiBaru());
                                alamat.setPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeri")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setNegeri(kn);
                                alamat.setNegeri(kn);
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat1")) {
                                pihak.setSuratAlamat1(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat2")) {
                                pihak.setSuratAlamat2(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat3")) {
                                pihak.setSuratAlamat3(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("alamatSurat4")) {
                                pihak.setSuratAlamat4(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("poskodSurat")) {
                                pihak.setSuratPoskod(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("negeriSurat")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                pihak.setSuratNegeri(kn);
                            }

                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            pihak.setInfoAudit(info);
                            mPihak.setInfoAudit(info);
                            pService.updatePihak(pihak);
//                    pService.updateHakmilikPihakBerkepentingan(hpb);
                            pService.updatePermohonanPihak(mPihak);
                        }
                    }
                }
            } else if (pAtasPihakL.getJenisTugasan().equals("HAPUS")) {
                if (pAtasPihakL.getPihakBerkepentingan() != null) {
                    HakmilikPihakBerkepentingan hakmilikPihak = hakmilikPihakBerkepentinganDAO.findById(pAtasPihakL.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan());
                    hakmilikPihak.setAktif('T');
                    pService.simpanHakmilikPihakBerkepentingan(hakmilikPihak);
                    hakmilikPihakBerkepentinganDAO.saveOrUpdate(hakmilikPihak);
                    regService.simpanHakmilikPihak(hakmilikPihak);
                }
                if (pAtasPihakL.getPermohonanPihak() != null) {
                    PermohonanPihak mohonPihak = permohonanPihakService.findById(String.valueOf(pAtasPihakL.getPermohonanPihak().getIdPermohonanPihak()));
                    mohonPihak.setKodStatus(String.valueOf('T'));
//                    permohonanPihakService.delete(mohonPihak);
                    pService.simpanMohonPihak(mohonPihak);
                }
                if (pAtasPihakL.getPemohon() != null) {
                    Pemohon pemohon = pemohonService.findById(String.valueOf(pAtasPihakL.getPemohon().getIdPemohon()));
                    pemohon.setKodStatus(String.valueOf('T'));
                    pemohonService.saveOrUpdate(pemohon);
                }
            } else if (pAtasPihakL.getJenisTugasan().equals("Tambah")) {
                if (pAtasPihakL.getPihakBerkepentingan() != null) {
                    HakmilikPihakBerkepentingan hakmilikPihak = hakmilikPihakBerkepentinganDAO.findById(pAtasPihakL.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan());
                    hakmilikPihak.setAktif('Y');
                    hakmilikPihakService.save(hakmilikPihak);

                }
                if (pAtasPihakL.getPermohonanPihak() != null) {
                    PermohonanPihak mohonPihak = permohonanPihakService.findById(String.valueOf(pAtasPihakL.getPermohonanPihak().getIdPermohonanPihak()));
                    mohonPihak.setKodStatus(String.valueOf('Y'));
//                    permohonanPihakService.delete(mohonPihak);
                    pService.simpanMohonPihak(mohonPihak);
                }
                if (pAtasPihakL.getPemohon() != null) {
                    Pemohon pemohon = pemohonService.findById(String.valueOf(pAtasPihakL.getPemohon().getIdPemohon()));
                    pemohon.setKodStatus(String.valueOf('Y'));
                    pemohonService.saveOrUpdate(pemohon);
                }
            } else if (pAtasPihakL.getJenisTugasan().equals("KEMASKINIWARIS")) {

                List<PermohonanPihakKemaskini> mpkkList = pService.findAtasIdHp(pAtasPihakL.getPermohonan().getIdPermohonan(), String.valueOf(pAtasPihakL.getIdAtasPihak()));
                for (PermohonanPihakKemaskini pbMap : mpkkList) {
                    if (pbMap.getWarisTerlibat() != null) {
                        HakmilikWaris hpw = hakmilikWarisDAO.findById(pbMap.getWarisTerlibat().getIdWaris());

                        String hakmilikPihak = pAtasPihakL.getPihakBerkepentingan().getHakmilik().getIdHakmilik();

                        Alamat alamatMP = new Alamat();
                        Alamat alamatP = new Alamat();
                        if (hpw != null) {
                            LOG.info("Update Existing Record");
                            if (pbMap.getNamaMedan().equals("nama")) {
                                hpw.setNama(pbMap.getNilaiBaru());
                            }
                            if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                                KodJenisPengenalan kjp = new KodJenisPengenalan();
                                kjp.setKod(pbMap.getNilaiBaru());
                                hpw.setJenisPengenalan(kjp);

                            }
                            if (pbMap.getNamaMedan().equals("noPengenalan")) {
                                hpw.setNoPengenalan(pbMap.getNilaiBaru());
                            }

                            if (pbMap.getNamaMedan().equals("warganegara")) {
                                KodWarganegara kw = new KodWarganegara();
                                kw.setKod(pbMap.getNilaiBaru());
                                hpw.setWargaNegara(kw);
                            }

                            if (pbMap.getNamaMedan().equals("penyebut")) {
                                hpw.setSyerPenyebut(Integer.parseInt(pbMap.getNilaiBaru()));

                            }
                            if (pbMap.getNamaMedan().equals("pembilang")) {
                                hpw.setSyerPembilang(Integer.parseInt(pbMap.getNilaiBaru()));

                            }
                            if (pbMap.getNamaMedan().equals("negeri")) {
                                KodNegeri kn = new KodNegeri();
                                kn.setKod(pbMap.getNilaiBaru());
                                hpw.setNegeri(kn);
                            }
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            hpw.setInfoAudit(info);
                            hpw.setStatus("T");
                            pService.updateWaris(hpw);
                        }
                    }
                }
            }
        }

        if (listhw != null) {
            for (HakmilikWaris hw : listhw) {
                HakmilikWaris waris = pService.findWarisByID(String.valueOf(hw.getBetulWaris().getIdWaris()));
                HakmilikWaris warisbetul = pService.findWarisBetulByID(String.valueOf(hw.getBetulWaris().getIdWaris()));
                if (waris != null) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    if (warisbetul.getNama() == null) {
                        waris.setNama(waris.getNama());
                    } else {
                        waris.setNama(warisbetul.getNama());
                    }
                    if (warisbetul.getJenisPengenalan() == null) {
                        waris.setJenisPengenalan(waris.getJenisPengenalan());
                    } else {
                        waris.setJenisPengenalan(warisbetul.getJenisPengenalan());
                    }
                    if (warisbetul.getNoPengenalan() == null) {
                        waris.setNoPengenalan(waris.getNoPengenalan());
                    } else {
                        waris.setNoPengenalan(warisbetul.getNoPengenalan());
                    }
                    if (String.valueOf(warisbetul.getSyerPembilang()) == null) {
                        waris.setSyerPembilang(waris.getSyerPembilang());
                    } else {
                        waris.setSyerPembilang(warisbetul.getSyerPembilang());
                    }
                    if (String.valueOf(warisbetul.getSyerPenyebut()) == null) {
                        waris.setSyerPenyebut(waris.getSyerPenyebut());
                    } else {
                        waris.setSyerPenyebut(warisbetul.getSyerPenyebut());
                    }
                    if (warisbetul.getNegeri() == null) {
                        waris.setNegeri(waris.getNegeri());
                    } else {
                        waris.setNegeri(warisbetul.getNegeri());
                    }
                    if (warisbetul.getWargaNegara() == null) {
                        waris.setWargaNegara(waris.getWargaNegara());
                    } else {
                        waris.setWargaNegara(warisbetul.getWargaNegara());
                    }
                    waris.setInfoAudit(info);
                    pService.updateWaris(waris);
                }
                if (warisbetul != null) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    warisbetul.setInfoAudit(info);
                    warisbetul.setStatus("T");
                    pService.updateWaris(warisbetul);
                }

            }
        }
    }

    private void betsw(StageContext context, Permohonan permohonan) {
        LOG.info("MASUK");
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        List<PermohonanAtasPerserahan> mohonAtasUrusan = pService.findAtasUrusan(permohonan.getIdPermohonan());
        LOG.debug("Mohon Atas Urusan Size:" + mohonAtasUrusan.size());
        for (PermohonanAtasPerserahan pp : mohonAtasUrusan) {
            PermohonanSuratPembetulan mohonSuratBetul = pService.findSuratIDMohon(permohonan.getIdPermohonan(), String.valueOf(pp.getUrusan().getIdUrusan()));
            hakmilikUrusan = pService.findUrusan(String.valueOf(pp.getUrusan().getIdUrusan()));
            List<HakmilikUrusanSurat> huList = pService.findHakmilikUrusanSrt(String.valueOf(pp.getUrusan().getIdUrusan()), mohonSuratBetul.getWakilKuasa().getIdWakil());
            if (huList != null) {
                for (HakmilikUrusanSurat hus : huList) {
                    hus.setUrusan(hakmilikUrusan);
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    hus.setInfoAudit(info);
                    hus.setNoSurat(mohonSuratBetul.getNoRujukan());
                    KodDokumen kd = new KodDokumen();
                    kd.setKod("SWD");
                    hus.setKodDokumen(kd);
                    pService.simpanHakmilikUrusanSrt(hus);
                }
            }
        }
    }

    private void betul(StageContext context, Permohonan permohonan) {
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();

        // add hakmilik asal
        List<PermohonanPembetulanHakmilik> mapList = pService.findHakmilikAsal(permohonan.getIdPermohonan());
        if (mapList != null) {
            for (PermohonanPembetulanHakmilik pbH : mapList) {
                LOG.info("New Record - hakmilik asal ");
                HakmilikAsal ha = new HakmilikAsal();
                hakmilik = hakmilikDAO.findById(pbH.getIdHakmilik());
                ha.setHakmilik(hakmilik);
                SejarahHakmilik hAsal = sejarahHakmilikDAO.findById(pbH.getIdHakmilikAsal());
                ha.setHakmilikAsal(pbH.getIdHakmilikAsal());
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                ha.setInfoAudit(info);
                pService.simpanHakmilikAsal(ha);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("BETUL")) {
            List<HakmilikPermohonan> mohonHakmilikList = pService.findByIdPermohonanList(permohonan.getIdPermohonan());
            for (HakmilikPermohonan mohonHakmilik : mohonHakmilikList) {
                //sini 
                PermohonanPembetulanUrusan mohonPembetuan = pService.findBetulMaklumatUrusanbyHakmilikIdMohon(permohonan.getIdPermohonan(), mohonHakmilik.getHakmilik().getIdHakmilik());
                if (mohonPembetuan != null) {
                    HakmilikUrusan hakmilikurusan = hakmilikUrusanDAO.findById(mohonPembetuan.getUrusan().getIdUrusan());
                    HakmilikPermohonan hakmilikPermohonan = pService.findByIdHakmilik(hakmilikurusan.getIdPerserahan(), hakmilikurusan.getHakmilik().getIdHakmilik());
                    if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKodPerserahan().getKod().equals("N")) {
                        PermohonanPembetulanHakmilik mohonBetulHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        hakmilikPermohonan.setLuasTerlibat(mohonBetulHakmilik.getLuas());
                        info.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(info);
                        hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilik);
                    }
                }
            }
        }

        // add hakmilik sebelumkini new version by nad 21/01/2020
        List<HakmilikSebelumPermohonan> mapList2 = regService.searchMohonHakmilikSblmByIDPermohonan(permohonan.getIdPermohonan());
        if (mapList2 != null) {
            for (HakmilikSebelumPermohonan hsP : mapList2) {
                LOG.info("NEW RECORD - hakmilik sebelumkini ");
                HakmilikSebelum hs = new HakmilikSebelum();
                hakmilik = hakmilikDAO.findById(hsP.getHakmilik().getIdHakmilik());
                //hakmilik = hakmilikDAO.findById(String.valueOf(hsP.getHakmilik()));
                hs.setHakmilik(hakmilik);
                SejarahHakmilik hSblm = sejarahHakmilikDAO.findById(hsP.getHakmilikSejarah());
                if (hSblm != null) {
                    hs.setHakmilikSebelum(hSblm.getIdHakmilik());
                } else {
                    hs.setHakmilikSebelum(hsP.getHakmilikSejarah());
                }
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hs.setInfoAudit(info);
                pService.simpanHakmilikSblm(hs);
            }
        }

        // add hakmilik sebelumkini
        /*List<PermohonanPembetulanHakmilik> mapList2 = pService.findHakmilikSblm(permohonan.getIdPermohonan());
         if (mapList2
         != null) {
         for (PermohonanPembetulanHakmilik pbH : mapList2) {
         LOG.info("New Record - hakmilik sebelumkini ");
         HakmilikSebelum hs = new HakmilikSebelum();
         hakmilik = hakmilikDAO.findById(pbH.getIdHakmilik());
         hs.setHakmilik(hakmilik);
         SejarahHakmilik hSblm = sejarahHakmilikDAO.findById(pbH.getIdHakmilikSebelum());
         if (hSblm != null) {
         hs.setHakmilikSebelum(hSblm.getIdHakmilik());
         } else {
         hs.setHakmilikSebelum(pbH.getIdHakmilikSebelum());
         }
         info.setDimasukOleh(peng);
         info.setTarikhMasuk(new java.util.Date());
         info.setDiKemaskiniOleh(peng);
         info.setTarikhKemaskini(new java.util.Date());
         hs.setInfoAudit(info);
         pService.simpanHakmilikSblm(hs);
         }
         }*/

        // tambah urusan
        List<PermohonanPembetulanUrusan> mapList4 = pService.findUrusanBetulList(String.valueOf(permohonan.getIdPermohonan()));
        if (mapList4
                != null) {
            for (PermohonanPembetulanUrusan pbT : mapList4) {
                LOG.info("tambah mohon");
                Permohonan mohon = permohonanService.findById(String.valueOf(pbT.getIdPerserahanLama()));
                if (mohon == null) {
                    Permohonan m = new Permohonan();
                    m.setIdPermohonan(pbT.getIdPerserahanLama());
                    m.setCawangan(permohonan.getCawangan());
                    m.setKodUrusan(pbT.getKodUrusan());
                    KodKeputusan k = new KodKeputusan();
                    k.setKod("D");
                    m.setKeputusan(k);
                    m.setStatus("SL");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    m.setInfoAudit(info);
                    permohonanService.saveOrUpdate(m);
                }
                LOG.info("tambah record urusan ");
//        hakmilikPermohonan = pService.findByIdPermohonan(permohonan.getIdPermohonan());
                hakmilikPermohonan = pbT.getHakmilik();
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                HakmilikUrusan hu = new HakmilikUrusan();
                hu.setHakmilik(hakmilik);
                hu.setIdPerserahan(pbT.getIdPerserahanLama());

                hu.setKodUrusan(pbT.getKodUrusan());
                hu.setTarikhDaftar(pbT.getTarikhDaftar());
                if (pbT.getLuasTerlibat() != null) {
                    hu.setLuasTerlibat(pbT.getLuasTerlibat());
                }
                if (pbT.getKodUnitLuas() != null) {
                    hu.setKodUnitLuas(pbT.getKodUnitLuas());
                }
                if (pbT.getCukaiBaru() != null) {
                    hu.setCukaiBaru(pbT.getCukaiBaru());
                }
                hu.setCawangan(permohonan.getCawangan());
                hu.setDaerah(hakmilik.getDaerah());
                hu.setAktif('Y');
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hu.setInfoAudit(info);
                pService.simpanHakmilikUrusan(hu);

                // save hakmilikpihak
                List<Pemohon> p = pService.findPemohon(String.valueOf(permohonan.getIdPermohonan()), pbT.getIdPerserahanLama());
                if (p != null) {
                    for (Pemohon p1 : p) {
                        HakmilikUrusan h1 = pService.findHUrusan(hakmilik.getIdHakmilik(), pbT.getIdPerserahanLama());
                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilik);
                        hpb.setCawangan(permohonan.getCawangan());
                        hpb.setPerserahan(h1);
//                        hpb.setIdHakmilikPihakBerkepentingan(p1.getPihak().getIdPihak());
                        KodJenisPihakBerkepentingan kj = kodJenisPihakBerkepentinganDAO.findById("PMB");
                        hpb.setJenis(kj);
                        hpb.setInfoAudit(info);
                        hpb.setNama(p1.getPihak().getNama());
                        hpb.setJenisPengenalan(p1.getPihak().getJenisPengenalan());
                        hpb.setNoPengenalan(p1.getPihak().getNoPengenalan());
                        hpb.setPihak(p1.getPihak());
                        hpb.setAktif('Y');
                        pService.simpanHakmilikPihakBerkepentingan(hpb);
                    }
                }
                // tambah hakmilik pihak - mohon pihak
                List<PermohonanPihak> p2 = pService.findPihakByIdMohon(String.valueOf(permohonan.getIdPermohonan()), pbT.getIdPerserahanLama());
                if (p2 != null) {
                    for (PermohonanPihak p3 : p2) {
                        HakmilikUrusan h2 = pService.findHUrusan(hakmilik.getIdHakmilik(), pbT.getIdPerserahanLama());
                        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                        hpb.setHakmilik(hakmilik);
                        hpb.setCawangan(permohonan.getCawangan());
                        hpb.setPerserahan(h2);
//                        hpb.setIdHakmilikPihakBerkepentingan(p3.getPihak().getIdPihak());
                        hpb.setJenis(p3.getJenis());
                        hpb.setInfoAudit(info);
                        hpb.setNama(p3.getPihak().getNama());
                        hpb.setJenisPengenalan(p3.getPihak().getJenisPengenalan());
                        hpb.setNoPengenalan(p3.getPihak().getNoPengenalan());
                        hpb.setPihak(p3.getPihak());
                        hpb.setAktif('Y');
                        pService.simpanHakmilikPihakBerkepentingan(hpb);
                    }
                }
            }
        }

        // betul maklumat urusan
        List<PermohonanPembetulanUrusan> mapList3 = pService.findBetulMaklumatUrusan(permohonan.getIdPermohonan());
        if (mapList3
                != null) {
            for (PermohonanPembetulanUrusan pbM : mapList3) {
                LOG.info("update record maklumat urusan ");
                hakmilikUrusan = pService.findUrusan(String.valueOf(pbM.getUrusan().getIdUrusan()));
                mohonUrusan = pService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
                hakmilikPermohonan = hakmilikPermohonanService.findHakmilikPermohonan(pbM.getUrusan().getHakmilik().getIdHakmilik(), pbM.getUrusan().getIdPerserahan());
                //Added by Aizuddin to call 2 report
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                pkl2 = pService.findRujukanIDMohonHakmilik3(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                
                if (hakmilikPermohonan != null) {
                    LOG.info("/* UPDATE MOHON_HAKMILIK */");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                    if (pbM.getKodUnitLuas() != null) {
                        hakmilikPermohonan.setKodUnitLuas(pbM.getKodUnitLuas());
                    }
                    if (pbM.getLuasTerlibat() != null) {
                        hakmilikPermohonan.setLuasTerlibat(pbM.getLuasTerlibat());
                    }
                    if (pbM.getCukaiBaru() != null) {
                        hakmilikPermohonan.setCukaiBaru(pbM.getCukaiBaru());
                    }

                    hakmilikPermohonan.setInfoAudit(info);
                    pService.updateMohonHakmilik(hakmilikPermohonan);
                }

                if (pbM.getTarikhDaftar() != null) {
                    hakmilikUrusan.setTarikhDaftar(pbM.getTarikhDaftar());
                }
                if (pbM.getKodUnitLuas() != null) {
                    hakmilikUrusan.setKodUnitLuas(pbM.getKodUnitLuas());
                }
                if (pbM.getLuasTerlibat() != null) {
                    hakmilikUrusan.setLuasTerlibat(pbM.getLuasTerlibat());
                }
                if (pbM.getCukaiBaru() != null) {
                    hakmilikUrusan.setCukaiBaru(pbM.getCukaiBaru());
                }
                if (pbM.getNoFail() != null) {
                    LOG.info("NO FAIL mohon urusan betul ::: " +pbM.getNoFail());
                    if (pkl2 != null){
                        pkl2.setNoFail(pbM.getNoFail());                     
                    }else{
                        pkl.setNoFail(pbM.getNoFail()); 
                    }

                }

                //Added by Aizuddin in case before dont have mohon urusan so need to create new mohon urusan
                if (mohonUrusan != null) {
                    // Urusan SC
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SC")) {
                        if (pbM.getPerjanjianAmaun() != null) {
                            mohonUrusan.setPerjanjianAmaun(pbM.getPerjanjianAmaun());
                        }
                        if (pbM.getPerjanjianNoRujukan() != null) {
                            mohonUrusan.setPerjanjianNoRujukan(pbM.getPerjanjianNoRujukan());
                        }
                        if (pbM.getPerjanjianDutiStem() != null) {
                            mohonUrusan.setPerjanjianDutiSetem(pbM.getPerjanjianDutiStem());
                        }
                        if (pbM.getTarikhSaksi() != null) {
                            mohonUrusan.setTarikhSaksi(pbM.getTarikhSaksi());
                        }
                        mohonUrusan.setCawangan(permohonan.getCawangan());
                        mohonUrusan.setInfoAudit(info);
                        //Added by Aizuddin to save permohonan urusan
                        permohonanUrusanDAO.saveOrUpdate(mohonUrusan);
                    }
                } else { // If null create new
                    LOG.info(":::::::::::Mohon Urusan Null:::::::::::::::");
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SC")) {
                        PermohonanUrusan mohonUrusanNew = new PermohonanUrusan();
                        if (pbM.getPerjanjianAmaun() != null) {
                            mohonUrusanNew.setPerjanjianAmaun(pbM.getPerjanjianAmaun());
                        }
                        if (pbM.getPerjanjianNoRujukan() != null) {
                            mohonUrusanNew.setPerjanjianNoRujukan(pbM.getPerjanjianNoRujukan());
                        }
                        if (pbM.getPerjanjianDutiStem() != null) {
                            mohonUrusanNew.setPerjanjianDutiSetem(pbM.getPerjanjianDutiStem());
                        }
                        if (pbM.getTarikhSaksi() != null) {
                            mohonUrusanNew.setTarikhSaksi(pbM.getTarikhSaksi());
                        }
                        mohonUrusanNew.setCawangan(permohonan.getCawangan());
                        mohonUrusanNew.setInfoAudit(info);
                        permohonanUrusanDAO.saveOrUpdate(mohonUrusanNew);
                    }
                }

                //Added by Aizuddin in case before dont have mohon rujuk luar so need to create new mohon rujuk luar
                // Urusan N
                if (pkl != null) {
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("N")) {
                        if (pbM.getTarikhSidang() != null) {
                            pkl.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            pkl.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getNoRujukan() != null) {
                            pkl.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            pkl.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getCatatan() != null) {
                            pkl.setCatatan(pbM.getCatatan());
                        }
                        if (pbM.getItem() != null) {
                            pkl.setItem(pbM.getItem());
                        }
                        if (pbM.getTempohTahun() != null) {
                            pkl.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            pkl.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            pkl.setTempohHari(pbM.getTempohHari());
                        }
                    }

                    //Urusan B
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("B")) {
                        if (pbM.getKodPerintah() != null) {
                            pkl.setKodPerintah(pbM.getKodPerintah());
                        }
                        if (pbM.getNoRujukan() != null) {
                            pkl.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            pkl.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getItem() != null) {
                            pkl.setNamaSidang(pbM.getItem());
                        }
                        if (pbM.getTarikhSidang() != null) {
                            pkl.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getPerintahSebab() != null) {
                            pkl.setUlasan(pbM.getPerintahSebab());
                        }
                        if (pbM.getTempohTahun() != null) {
                            pkl.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            pkl.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            pkl.setTempohHari(pbM.getTempohHari());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            pkl.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getPerintahTarikhKuatkuasa() != null) {
                            pkl.setTarikhKuatkuasa(pbM.getPerintahTarikhKuatkuasa());
                        }
                        if (pbM.getTarikhTamat() != null) {
                            pkl.setTarikhTamat(pbM.getTarikhTamat());
                        }
                    }

                    //Added by Aizuddin urusan SC
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SC")) {
                        if (pbM.getKodPerintah() != null) {
                            pkl.setKodPerintah(pbM.getKodPerintah());
                        }
                        if (pbM.getNoRujukan() != null) {
                            pkl.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            pkl.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getItem() != null) {
                            pkl.setNamaSidang(pbM.getItem());
                        }
                        if (pbM.getTarikhSidang() != null) {
                            pkl.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getPerintahSebab() != null) {
                            pkl.setUlasan(pbM.getPerintahSebab());
                        }
                        if (pbM.getTempohTahun() != null) {
                            pkl.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            pkl.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            pkl.setTempohHari(pbM.getTempohHari());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            pkl.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getPerintahTarikhKuatkuasa() != null) {
                            pkl.setTarikhKuatkuasa(pbM.getPerintahTarikhKuatkuasa());
                        }
                        if (pbM.getTarikhTamat() != null) {
                            pkl.setTarikhTamat(pbM.getTarikhTamat());
                        }
                    }

                    if (pbM.getUrusan().getKodUrusan().getKod().equalsIgnoreCase("N6A")) {//N6A
                        if (StringUtils.isNotBlank(pkl.getNoRujukan())) {
                            kodRujukan = kodRujukanDAO.findById("WT");
                        } else {
                            kodRujukan = kodRujukanDAO.findById("FL");
                        }
                    } else {
                        kodRujukan = kodRujukanDAO.findById("FL");
                    }

                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    hakmilikUrusan.setCawangan(permohonan.getCawangan());
                    hakmilikUrusan.setDaerah(hakmilikUrusan.getDaerah());
                    hakmilikUrusan.setInfoAudit(info);
                    pkl.setCawangan(permohonan.getCawangan());
                    pkl.setKodRujukan(kodRujukan);
                    pkl.setInfoAudit(info);
                    notaService.simpanPermohonanRujLuar(pkl);
                    notaService.simpanPermohonanRujLuar(pkl2);
                    pService.updateHakmilikUrusan(hakmilikUrusan);
                } else { // mohon rujuk luar null
                    LOG.info(":::::::::::Mohon Rujuk Luar Null:::::::::::::::");
                    PermohonanRujukanLuar mohonRujukLuarNew = new PermohonanRujukanLuar();
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("N")) {
                        if (pbM.getTarikhSidang() != null) {
                            mohonRujukLuarNew.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            mohonRujukLuarNew.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getNoRujukan() != null) {
                            mohonRujukLuarNew.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            mohonRujukLuarNew.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getCatatan() != null) {
                            mohonRujukLuarNew.setCatatan(pbM.getCatatan());
                        }
                        if (pbM.getItem() != null) {
                            mohonRujukLuarNew.setItem(pbM.getItem());
                        }
                        if (pbM.getTempohTahun() != null) {
                            mohonRujukLuarNew.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            mohonRujukLuarNew.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            mohonRujukLuarNew.setTempohHari(pbM.getTempohHari());
                        }
                    }

                    //Urusan B
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("B")) {
                        if (pbM.getKodPerintah() != null) {
                            mohonRujukLuarNew.setKodPerintah(pbM.getKodPerintah());
                        }
                        if (pbM.getNoRujukan() != null) {
                            mohonRujukLuarNew.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            mohonRujukLuarNew.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getItem() != null) {
                            mohonRujukLuarNew.setNamaSidang(pbM.getItem());
                        }
                        if (pbM.getTarikhSidang() != null) {
                            mohonRujukLuarNew.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getPerintahSebab() != null) {
                            mohonRujukLuarNew.setUlasan(pbM.getPerintahSebab());
                        }
                        if (pbM.getTempohTahun() != null) {
                            mohonRujukLuarNew.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            mohonRujukLuarNew.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            mohonRujukLuarNew.setTempohHari(pbM.getTempohHari());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            mohonRujukLuarNew.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getPerintahTarikhKuatkuasa() != null) {
                            mohonRujukLuarNew.setTarikhKuatkuasa(pbM.getPerintahTarikhKuatkuasa());
                        }
                        if (pbM.getTarikhTamat() != null) {
                            mohonRujukLuarNew.setTarikhTamat(pbM.getTarikhTamat());
                        }
                    }

                    //Added by Aizuddin urusan SC
                    if (pbM.getUrusan().getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SC")) {
                        if (pbM.getKodPerintah() != null) {
                            pkl.setKodPerintah(pbM.getKodPerintah());
                        }
                        if (pbM.getNoRujukan() != null) {
                            pkl.setNoRujukan(pbM.getNoRujukan());
                        }
                        if (pbM.getNoSidang() != null) {
                            pkl.setNoSidang(pbM.getNoSidang());
                        }
                        if (pbM.getItem() != null) {
                            pkl.setNamaSidang(pbM.getItem());
                        }
                        if (pbM.getTarikhSidang() != null) {
                            pkl.setTarikhSidang(pbM.getTarikhSidang());
                        }
                        if (pbM.getPerintahSebab() != null) {
                            pkl.setUlasan(pbM.getPerintahSebab());
                        }
                        if (pbM.getTempohTahun() != null) {
                            pkl.setTempohTahun(pbM.getTempohTahun());
                        }
                        if (pbM.getTempohBulan() != null) {
                            pkl.setTempohBulan(pbM.getTempohBulan());
                        }
                        if (pbM.getTempohHari() != null) {
                            pkl.setTempohHari(pbM.getTempohHari());
                        }
                        if (pbM.getTarikhRujukan() != null) {
                            pkl.setTarikhRujukan(pbM.getTarikhRujukan());
                        }
                        if (pbM.getPerintahTarikhKuatkuasa() != null) {
                            pkl.setTarikhKuatkuasa(pbM.getPerintahTarikhKuatkuasa());
                        }
                        if (pbM.getTarikhTamat() != null) {
                            pkl.setTarikhTamat(pbM.getTarikhTamat());
                        }
                    }

                    if (pbM.getUrusan().getKodUrusan().getKod().equalsIgnoreCase("N6A")) {//N6A
                        if (StringUtils.isNotBlank(mohonRujukLuarNew.getNoRujukan())) {
                            kodRujukan = kodRujukanDAO.findById("WT");
                        } else {
                            kodRujukan = kodRujukanDAO.findById("FL");
                        }
                    } else {
                        kodRujukan = kodRujukanDAO.findById("FL");
                    }

                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    hakmilikUrusan.setCawangan(permohonan.getCawangan());
                    hakmilikUrusan.setDaerah(hakmilikUrusan.getDaerah());
                    hakmilikUrusan.setInfoAudit(info);
                    mohonRujukLuarNew.setCawangan(permohonan.getCawangan());
                    mohonRujukLuarNew.setPermohonan(permohonan);
                    mohonRujukLuarNew.setKodRujukan(kodRujukan);
                    mohonRujukLuarNew.setInfoAudit(info);
                    notaService.simpanPermohonanRujLuar(mohonRujukLuarNew);
                    pService.updateHakmilikUrusan(hakmilikUrusan);
                }
            }
        }

    }

    private void betur(StageContext context, Permohonan permohonan) {
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();

        List<PermohonanPihakKemaskini> listPkkini = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), null, permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listNama = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NAMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listNoPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NO_PENGENALAN", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listKodPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_PENGENALAN", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listNamaPenerima = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NAMA_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listNoPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NO_PENGENALAN_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listKodPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_PENGENALAN_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listAlamat1 = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT1_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listAlamat2 = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT2_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listAlamat3 = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT3_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listAlamat4 = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT4_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listPoskod = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "POSKOD_PNERIMA", permohonan.getIdPermohonan());
        List<PermohonanPihakKemaskini> listKodNegeri = pService.findListbyIdMohonIdHmMedanIdlama(null,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_NEGERI_PNERIMA", permohonan.getIdPermohonan());

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());

        for (PermohonanPihakKemaskini kkini : listPkkini) {

            WakilKuasaPemberi wakilKuasaPemberi = pService.findWakilKuasaPemberi(kkini.getPermohonan().getIdPermohonan());
            WakilKuasaPenerima wakilKuasaPenerima = pService.findWakilKuasaPenerima(kkini.getPermohonan().getIdPermohonan());

            if (wakilKuasaPemberi != null) {
                for (PermohonanPihakKemaskini ls : listNama) {
                    wakilKuasaPemberi.setNama(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listNoPengenalan) {
                    wakilKuasaPemberi.setNoPengenalan(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listKodPengenalan) {
                    wakilKuasaPemberi.setKodPengenalan(ls.getNilaiBaru());
                }
                wakilKuasaPemberi.setInfoAudit(ia2);
                wakilKuasaPemberiDAO.saveOrUpdate(wakilKuasaPemberi);
            }
            if (wakilKuasaPenerima != null) {
                for (PermohonanPihakKemaskini ls : listNamaPenerima) {
                    wakilKuasaPenerima.setNama(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listNoPengenalanPenerima) {
                    wakilKuasaPenerima.setNoPengenalan(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listKodPengenalanPenerima) {
                    wakilKuasaPenerima.setJenisPengenalan(kodJenisPengenalanDAO.findById(ls.getNilaiBaru()));
                }
                for (PermohonanPihakKemaskini ls : listAlamat1) {
                    wakilKuasaPenerima.setAlamat2(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listAlamat2) {
                    wakilKuasaPenerima.setAlamat2(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listAlamat3) {
                    wakilKuasaPenerima.setAlamat3(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listAlamat4) {
                    wakilKuasaPenerima.setAlamat4(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listPoskod) {
                    wakilKuasaPenerima.setPoskod(ls.getNilaiBaru());
                }
                for (PermohonanPihakKemaskini ls : listKodNegeri) {
                    wakilKuasaPenerima.setNegeri(kodNegeriDAO.findById(ls.getNilaiBaru()));
                }
                wakilKuasaPenerima.setInfoAudit(ia2);
                wakilKuasaPenerimaDAO.saveOrUpdate(wakilKuasaPenerima);
            }
        }
    }

    @Override
    public void afterPushBack(StageContext context) {
        //increase no versi of VDOC if push back
        Permohonan permohonan = context.getPermohonan();
        if (permohonan != null) {
            List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
            List<Dokumen> senaraiDokumenToUpdate = new ArrayList<Dokumen>();

            String idKumpulan = permohonan.getIdKumpulan();

            if (StringUtils.isNotBlank(idKumpulan)) {
                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonan.add(permohonan);
            }

            for (Permohonan p : senaraiPermohonan) {
                if (p == null || p.getFolderDokumen() == null) {
                    continue;
                }
                List<KandunganFolder> senaraiKandungan = p.getFolderDokumen().getSenaraiKandungan();
                for (KandunganFolder kf : senaraiKandungan) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    Dokumen d = kf.getDokumen();
                    if (d.getKodDokumen() == null) {
                        continue;
                    }
                    if (d.getKodDokumen().getKod().equalsIgnoreCase("VDOC")) {
                        Double noVersi = Double.parseDouble(d.getNoVersi());
                        d.setNoVersi(String.valueOf(noVersi + 1));
                        senaraiDokumenToUpdate.add(d);
                    }
                }
            }

            if (!senaraiDokumenToUpdate.isEmpty()) {
                dokumenService.saveOrUpdate(senaraiDokumenToUpdate);
            }
        }
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }

    public Permohonan getPermohonanLama() {
        return permohonanLama;
    }

    public void setPermohonanLama(Permohonan permohonanLama) {
        this.permohonanLama = permohonanLama;
    }
}
