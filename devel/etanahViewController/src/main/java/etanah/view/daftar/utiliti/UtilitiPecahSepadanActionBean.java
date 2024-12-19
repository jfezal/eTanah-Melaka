package etanah.view.daftar.utiliti;

import java.util.*;
import etanah.dao.*;
import java.io.File;
import etanah.model.*;
import etanah.report.*;
import org.hibernate.Session;
import java.util.concurrent.*;
import etanah.service.common.*;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.service.RegService;
import able.stripes.AbleActionBean;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.*;
import etanah.service.SemakDokumenService;
import org.apache.commons.lang.ArrayUtils;
import etanah.view.etanahActionBeanContext;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.util.WORMUtil;
import etanah.view.daftar.validator.HsbmPTValidation;
import etanah.view.etanahContextListener;
import etanah.workflow.WorkFlowService;
import java.io.IOException;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;

import etanah.model.FasaPermohonan;

/**
 * @author haqqiem 14 July 2015
 */
@HttpCache(allow = false)
@Wizard(startEvents = {"Step1", "delete", "janaTukarGanti"})
@UrlBinding("/daftar/utilitiPecahSepadan")
public class UtilitiPecahSepadanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiTukarGantActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";

    private String kodUrusan;
    private String kodSerah;
    private String idPenyerah;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String idMohon;
    private String idHakmilik;
    private String idHakmilik2;
    private String idHakmilikFirst;
    private Hakmilik idHakmilikFirst1;
    private String idSerah;
    private String idHakmilikDari;
    private String idHakmilikHingga;
    private String listHakmilikSiri;
    private boolean flag = false;
    private int bilHakmilik = 5;
    private int bilHakmilikSiri = 0;

    private KodUrusan ku;
    private Pengguna pguna;
    private KodPenyerah penyerahKod;
    private KodNegeri penyerahNegeri;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private Permohonan permohonan = new Permohonan();
    private Permohonan perserahan = new Permohonan();
    private HakmilikLama hakmilikLama = new HakmilikLama();
    private static final String[] PAPAR_PRINT_DOKUMEN = {"DHKE", "DHDE", "PB1DE", "PB1KE", "PB2DE", "PB2KE"};

    private List<HakmilikPermohonan> hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private ArrayList<KodUrusan> senaraiUrusan = new ArrayList<KodUrusan>();
    private List<KandunganFolder> listKandunganFolder = new ArrayList<KandunganFolder>();
    private List<Hakmilik> senaraiHakmilikKekal = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikSementara = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<HakmilikPermohonan> senaraiHP;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    private FasaPermohonan mohonFasa;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private KodUrusanDAO kodUrusanDAO;

    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;

    @Inject
    private HakmilikDAO hakmilikDAO;

    @Inject
    private HakmilikLamaDAO hakmilikLamaDAO;

    @Inject
    StrataPtService strService;

    @Inject
    private RegService regService;

    @Inject
    KandunganFolderService kandunganFolderService;

    @Inject
    KodPenyerahDAO kodPenyerahDAO;

    @Inject
    FolderDokumenDAO folderDokumenDAO;

    @Inject
    KodKeputusanDAO kodKeputusanDAO;

    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;

    @Inject
    private KodNegeriDAO kodNegeriDAO;

    @Inject
    SemakDokumenService semakDokumenService;

    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;

    @Inject
    private etanah.Configuration conf;

    @Inject
    DokumenService dokumenService;

    @Inject
    HakmilikService hakmilikService;

    @Inject
    HakmilikUrusanService hakmilikUrusanService;

    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    @Inject
    ReportUtil reportUtil, reportUtil2;

    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    KandunganFolderDAO kandunganFolderDAO;

    @Inject
    private etanah.view.stripes.hasil.KutipanHasilManager manager;

    @Inject
    private HsbmPTValidation hsbmPTValidation;

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;

    @Inject
    FasaPermohonanService FasaPermohonanservice;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution selectTransaction() {
        carianPerserahan();

        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    public List<KodUrusan> getSenaraiUrusanTukarganti() {
        Session s = sessionProvider.get();
        return s.createQuery("SELECT ku FROM KodUrusan ku WHERE ku.jabatan.id = '16' "
                + "AND ku.urusanBelakangKaunter ='Y' AND ku.aktif='Y' "
                + "AND ku.kod IN ('HSTHK','HKTHK') ORDER BY ku.kod ASC").list();
    }

    @HandlesEvent("Step2")
    public Resolution selectTitles() {
        LOG.info("kodUrusan : " + kodUrusan.toUpperCase());
        ku = kodUrusanDAO.findById(kodUrusan.toUpperCase());
        kodSerah = ku.getKodPerserahan().getKod();
        LOG.info("namaUrusan : " + ku.getNama());
        senaraiUrusan.add(ku);
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_hm.jsp");
    }

    @HandlesEvent("Step3")
    public Resolution updateNoOfTitles() {
        LOG.info("kodUrusan : " + kodUrusan.toUpperCase());
        ku = kodUrusanDAO.findById(kodUrusan.toUpperCase());
        kodSerah = ku.getKodPerserahan().getKod();
        LOG.info("namaUrusan : " + ku.getNama());

        // reset senaraiHakmilik
        for (int i = 0; i < bilHakmilik; i++) {
            HakmilikPermohonan h = new HakmilikPermohonan();
            hakmilikPermohonan.add(h);
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_hm.jsp");
    }

    @HandlesEvent("Step4")
    public Resolution setPenyerah() {
        ku = kodUrusanDAO.findById(kodUrusan.toUpperCase());
        kodSerah = ku.getKodPerserahan().getKod();
        senaraiUrusan.add(ku);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        KodCawanganJabatan kcj = regService.getJabatanPendaftaranByCaw(pengguna.getKodCawangan().getKod());
        if (kcj != null) {
            setPenyerahKod(kodPenyerahDAO.findById(kcj.getKodPenyerah()));
            setIdPenyerah(String.valueOf(kcj.getIdPenyerah()));
            setPenyerahNama(kcj.getNama());
            setPenyerahNoPengenalan(kcj.getNoPengenalan());
            penyerahJenisPengenalan = kcj.getJenisPengenalan();
            penyerahAlamat1 = kcj.getAlamat1();
            penyerahAlamat2 = kcj.getAlamat2();
            penyerahAlamat3 = kcj.getAlamat3();
            penyerahAlamat4 = kcj.getAlamat4();
            penyerahPoskod = kcj.getPoskod();
            if (kcj.getNegeri() != null) {
                penyerahNegeri = kodNegeriDAO.findById(kcj.getNegeri().getKod());
            }
        } else {
            LOG.debug("KCJ NULL!!!");
        }
        LOG.info("hakmilikPermohonan.size() : " + hakmilikPermohonan.size());
        if (hakmilikPermohonan.isEmpty()) {
            LOG.info("listHakmilikSiri : " + listHakmilikSiri);
            String[] hm = listHakmilikSiri.split(",");
            LOG.info("hm.length : " + hm.length);

            List<HakmilikPermohonan> lhp = new ArrayList<HakmilikPermohonan>();
            for (int m = 0; m < hm.length; m++) {
                HakmilikPermohonan hp1 = new HakmilikPermohonan();
                String id = hm[m];
                LOG.info(id);
                Hakmilik idHm = hakmilikDAO.findById(id);
                hp1.setHakmilik(idHm);
                lhp.add(hp1);
            }
            LOG.info(lhp.size());
            hakmilikPermohonan = lhp;
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_rumusan.jsp");
    }

//    @HandlesEvent("Step5")
//    public Resolution selesai() {
//        LOG.info("  /* STEP 5 */  ");
//        ku = kodUrusanDAO.findById(kodUrusan.toUpperCase());
//
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();
//        pguna = pengguna;
//
//        KodCawangan kodCaw = pengguna.getKodCawangan();
//        String kodNegeri = ctx.getKodNegeri();
//
//        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
//        String documentPath = conf.getProperty("document.path");
//        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
//        LOG.info("hakmilikPermohonan.size() : " + hakmilikPermohonan.size());
//        LOG.info("hakmilikPermohonan.size() : " + hakmilikPermohonan.size());
//        if ((hakmilikPermohonan.isEmpty()) && (senaraiHakmilik.isEmpty())) {
//            LOG.info("-------" + listHakmilikSiri);
//            LOG.info("listHakmilikSiri : " + listHakmilikSiri);
//            String[] hm = listHakmilikSiri.split(",");
//            LOG.info("hm.length : " + hm.length);
//
//            List<HakmilikPermohonan> lhp = new ArrayList<HakmilikPermohonan>();
//            for (int m = 0; m < hm.length; m++) {
//                HakmilikPermohonan hp1 = new HakmilikPermohonan();
//                String id = hm[m];
//                LOG.info(id);
//                Hakmilik idHm = hakmilikDAO.findById(id);
//                hp1.setHakmilik(idHm);
//                lhp.add(hp1);
//            }
//            LOG.info(lhp.size());
//            hakmilikPermohonan = lhp;
//        }
//
//        if (hakmilikPermohonan.size() > 0) {
//            for (HakmilikPermohonan hp : hakmilikPermohonan) {
//                listHakmilik.add(hp.getHakmilik());
//            }
//        }
//
//        if (hakmilikPermohonan.size() < 1) {
//            listHakmilik = senaraiHakmilik;
//        }
//
//        LOG.info("listHakmilik.size() : " + listHakmilik.size());
//        Transaction tx = sessionProvider.get().beginTransaction();
//        tx.begin();
//        try {
//            if (kodUrusan.equals("HSTHK")) {
//                ku = kodUrusanDAO.findById("HSTHK");
//                permohonan = doSave(kodNegeri, pengguna, kodCaw, listHakmilik, ku);
//            }
//            if (kodUrusan.equals("HKTHK")) {
//                ku = kodUrusanDAO.findById("HKTHK");
//                permohonan = doSave(kodNegeri, pengguna, kodCaw, listHakmilik, ku);
//            }
//            addSimpleMessage("Urusan telah berjaya didaftarkan dan dokumen telah dijana. ID Permohonan : " + permohonan.getIdPermohonan());
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            LOG.error(e.getMessage());
//            addSimpleError("Urusan tidak berjaya didaftarkan.");
//        } finally {
//            LOG.info("(((((((((((((((((((((((FINALLY))))))))))))))))))))))))))))))");
//            onGenerateReports(permohonan);
//            LOG.info("(((((((((((((((((((((((FINALLY TUTUP))))))))))))))))))))))))))))))");
//        }
//        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
//    }
    private Permohonan doSave(String kodNegeri, Pengguna pengguna, KodCawangan kodCawangan,
            List<Hakmilik> listHakmilik, KodUrusan kodUrusan) {

        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);

        if (kodCawangan == null) {
            kodCawangan = pengguna.getKodCawangan();
        }
        String idPermohonan = idPerserahanGenerator.generate(
                kodNegeri, kodCawangan, kodUrusan);

        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk(idPermohonan);
        fd.setInfoAudit(iaPermohonan);

        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(kodCawangan);
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);
        p.setInfoAudit(iaPermohonan);

        KodCawanganJabatan kcj = regService.getJabatanPendaftaranByCaw(pengguna.getKodCawangan().getKod());
        if (kcj != null) {
            p.setKodPenyerah(kodPenyerahDAO.findById(kcj.getKodPenyerah()));
            p.setIdPenyerah(kcj.getIdPenyerah());
            p.setPenyerahNama(kcj.getNama());
            p.setPenyerahNoPengenalan(kcj.getNoPengenalan());
            p.setPenyerahJenisPengenalan(kcj.getJenisPengenalan());
            p.setPenyerahAlamat1(kcj.getAlamat1());
            p.setPenyerahAlamat2(kcj.getAlamat2());
            p.setPenyerahAlamat3(kcj.getAlamat3());
            p.setPenyerahAlamat4(kcj.getAlamat4());
            p.setPenyerahPoskod(kcj.getPoskod());
            p.setPenyerahNegeri(kcj.getNegeri());
        }
        p.setUntukTahun(d.getYear() + 1900);
        p.setIdWorkflow(kodUrusan.getIdWorkflow());
        manager.saveOrUpdate(p);

        // attach Hakmilik
        if (listHakmilik != null) {
            for (Hakmilik hm : listHakmilik) {
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(iaPermohonan);
                hpa.setPermohonan(p);
                manager.saveOrUpdate(hpa);
            }
        }
        return p;
    }

//    public boolean onGenerateReports(Permohonan p) {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();
//
//        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
//        String dokumenPath = conf.getProperty("document.path");
//        Dokumen e = null;
//        Dokumen f = null;
//        String idFolder = "";
//
//        String idPermohonan = p.getIdPermohonan();
//        idFolder = String.valueOf(p.getFolderDokumen().getFolderId());
//        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//        String[] params = null;
//        String[] values = null;
//        String path2 = "";
//        String path3 = "";
//        String gen2 = "";
//        String gen3 = "";
//        KodDokumen kd2 = new KodDokumen();
//        KodDokumen kd3 = new KodDokumen();
//
//        KodUrusan ku = p.getKodUrusan();
//
////        String[] urusanConvert = {"HSTHK", "HKTHK"};
////        if (ArrayUtils.contains(urusanConvert, ku.getKod())) {
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
//
//        permohonan = permohonanDAO.findById(p.getIdPermohonan());
//        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan";
//        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", permohonan.getIdPermohonan());
//        hakmilikPermohonan = q.list();
//        for (HakmilikPermohonan hp : hakmilikPermohonan) {
//            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//            LOG.info("hp.getId() : " + hp.getId());
//            if (hm == null) {
//                continue;
//            }
//
//            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//            values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
//            gen2 = "regBorangHMDHKE_util.rdf"; //DHKE report name
//            gen3 = "regBorangHMDHDE_util.rdf"; //DHDE report name
//
//            // generate DHKE hakmilik baru
//            kd2.setKod("DHKE");
//            e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context, 0);
//            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//            LOG.info("::Path To save :: " + path2);
//            LOG.info("::Report Name ::" + gen2);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
//            Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
//            File sign = new File(dokumenPath + path2 + ".sig");
//            if (sign.exists()) {
//                sign.delete();
//            }
//
//            //generat DHDE hakmilik baru
//            kd3.setKod("DHDE");
//            f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context, 0);
//            path3 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//            LOG.info("::Path To save :: " + path3);
//            LOG.info("::Report Name ::" + gen3);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
//            Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
//            sign = new File(dokumenPath + path3 + ".sig");
//            if (sign.exists()) {
//                sign.delete();
//            }
//
//            LOG.debug("Waiting for reports to complete...");
//            try {
//                dhke.get();
//            } catch (Exception ex) {
//                LOG.debug(ex.getMessage(), ex);
//            }
//            try {
//                dhde.get();
//            } catch (Exception ex) {
//                LOG.debug(ex.getMessage(), ex);
//            }
//            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
//            updatePathDokumen(reportUtil2.getDMSPath(), f.getIdDokumen());
//
//            //save to mohon hakmilikLogic.
//            hp.setDokumen2(e);
//            hm.setDhke(e);
//            hp.setDokumen3(f);
//            hm.setDhde(f);
//            if (hm.getKodHakmilik().getKod().equals("PN")
//                    || hm.getKodHakmilik().getKod().equals("GRN")
//                    || hm.getKodHakmilik().getKod().equals("GM")
//                    || hm.getKodHakmilik().getKod().equals("GMM")
//                    || hm.getKodHakmilik().getKod().equals("PM")) {
//                if (conf.getProperty("kodNegeri").equals("05")) {
//                    //b1 n9
//                    gen2 = "REG_BorangB1eNS.rdf";
//                    gen3 = "REG_BorangB1eNS_DHKe.rdf";
//                } else {
//                    //b1 melaka
//                    gen2 = "REG_BorangB1eMLK.rdf";
//                    gen3 = "REG_BorangB1eMLK_DHKe.rdf";
//                }
//                kd2.setKod("PB1DE");
//                kd3.setKod("PB1KE");
//
//            } else {
//                if (conf.getProperty("kodNegeri").equals("05")) {
//                    //b2 n9
//                    gen2 = "REG_BorangB2eNS.rdf";
//                    gen3 = "REG_BorangB2eNS_DHKe.rdf";
//                } else {
//                    //b2 melaka
//                    gen2 = "REG_BorangB2eMLK.rdf";
//                    gen3 = "REG_BorangB2eMLK_DHKe.rdf";
//                }
//                kd2.setKod("PB2DE");
//                kd3.setKod("PB2KE");
//            }
//            //gen Pelan DHDE
//            LOG.debug("START GENERATE PELAN DHDE");
//            e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context, hakmilikPermohonan.size());
//            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//            LOG.info("::Path To save :: " + path2);
//            LOG.info("::Report Name ::" + gen2);
//            syslog.info(peng.getIdPengguna() + " generate report " + gen2 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
//            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
//            updatePathDokumenDHKE(reportUtil.getDMSPath(), e.getIdDokumen());
//            LOG.debug("::saving pelan into mohon hakmilik::");
//            hp.setDokumen5(e);
//            hm.setPelan(e);
//
//            //gen Pelan DHKE
//            LOG.debug("START GENERATE PELAN DHKE");
//            f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context, hakmilikPermohonan.size());
//            path3 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//            LOG.info("::Path To save :: " + path3);
//            LOG.info("::Report Name ::" + gen3);
//            syslog.info(peng.getIdPengguna() + " generate report " + gen3 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
//            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
//            updatePathDokumenDHDE(reportUtil.getDMSPath(), f.getIdDokumen());
//            LOG.debug("::saving pelan into mohon hakmilik::");
//            hp.setDokumen6(f);
//
//            manager.saveOrUpdate(hp);
//            manager.saveOrUpdate(hm);
//        }
////        }
//        return true;
//    }
    private Dokumen saveOrUpdateDokumenV2DHKE(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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

        LOG.debug("idT :" + idT);

        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        if (kd.getKod().equals("DHKE")) {
            Dokumen dokDHKE = hp.getDokumen2();
            if (dokDHKE != null) {
                LOG.debug("doc lama");
                doc = hp.getDokumen2();
                //doc = new Dokumen();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

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

    private Dokumen saveOrUpdateDokumenV2DHDE(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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

        LOG.debug("idT :" + idT);

        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);

        if (kd.getKod().equals("DHDE")) {
            Dokumen dokDHDE = hp.getDokumen3();
            if (dokDHDE != null) {
                LOG.debug("doc lama");
                //doc = new Dokumen();
                doc = hp.getDokumen3();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

        doc.setTajuk(kd.getKod() + " (" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc.setNamaFizikal(null);
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

    private Dokumen saveOrUpdateDokumenV2(FolderDokumen fd, String idMohon, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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

        LOG.debug("idT :" + idT);

        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        if (kd.getKod().equals("DHKE")) {
            Dokumen dokDHKE = hp.getDokumen2();
            if (dokDHKE != null) {
                LOG.debug("doc lama");
                doc = hp.getDokumen2();
                //doc = new Dokumen();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }
        if (kd.getKod().equals("DHDE")) {
            Dokumen dokDHDE = hp.getDokumen3();
            if (dokDHDE != null) {
                LOG.debug("doc lama");
                //doc = new Dokumen();
                doc = hp.getDokumen3();
                ia = doc.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                doc.setBaru('T');
                LOG.debug("no versi lama : " + doc.getNoVersi());
                Double noVersi = Double.parseDouble(doc.getNoVersi());
                doc.setNoVersi(String.valueOf(noVersi + 1));
                LOG.debug("no versi baru : " + noVersi);
            } else {
                LOG.debug("doc baru");
                doc = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                doc.setBaru('Y');
                doc.setNoVersi("1.0");
            }
        }

        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

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

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, ActionBeanContext context1, int sizeHakmilik) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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

        LOG.debug("idT :" + idT);
        //doc = semakDokumenService.semakDokumen(kd, fd, idT);               
        HakmilikPermohonan hp2 = hakmilikPermohonanService.findHakmilikPermohonan(id, idMohon);
        if (kd.getKod().equals("PB2DE")) {
            doc = hp2.getDokumen5();
            LOG.debug("doc pb2de : " + doc);
        }
        if (kd.getKod().equals("PB2KE")) {
            doc = hp2.getDokumen6();
            LOG.debug("doc pb2ke : " + doc);
        }
        if (kd.getKod().equals("DDHDK")) {
            doc = hp2.getDokumen1();
            LOG.debug("doc ddhdk : " + doc);
        }        
        if (doc == null) {
            LOG.debug("doc baru");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");

        } else {
            LOG.debug("doc lama");
            //doc = new Dokumen();
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            //ia.setTarikhMasuk(new java.util.Date());            
            ia.setTarikhKemaskini(new java.util.Date());
            //doc.setBaru('Y');
            //doc.setNoVersi("1.0");            
            //doc.setBaru('T');
            //LOG.debug("no versi lama : " + doc.getNoVersi());
            //Double noVersi = Double.parseDouble(doc.getNoVersi());
            //doc.setNoVersi(String.valueOf(noVersi + 1));
            //LOG.debug("no versi baru : " + noVersi);
        }
            
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);

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

    private void updatePathDokumenDHDE(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        if (d.getNamaFizikal() != null) {
            d.setNamaFizikal(null);
            dokumenService.saveOrUpdate(d);
        }
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void updatePathDokumenDHKE(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        if (d.getNamaFizikal() != null) {
            d.setNamaFizikal(null);
            dokumenService.saveOrUpdate(d);
        }
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen1(FolderDokumen fd, KodDokumen kd, String id) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
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

    @HandlesEvent("Step6")
    public Resolution daftar() {
        LOG.info(" /* DAFTAR */ " + permohonan.getIdPermohonan());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(now);

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        boolean check = checkingSign(permohonan);
        LOG.info("check : " + check);
        if (check) {
            if (permohonan != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {
                    updateNoVersi(permohonan, permohonan.getInfoAudit());

                    permohonan.setStatus("SL");
                    permohonan.setKeputusan(kodKeputusanDAO.findById("D"));
                    permohonan.setKeputusanOleh(pengguna);
                    permohonan.setUlasan("DAFTAR");
                    permohonan.setTarikhKeputusan(now);

                    manager.saveOrUpdate(permohonan);

                    hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
                    String idPermohonan = permohonan.getIdPermohonan();
                    for (HakmilikPermohonan hp : hakmilikPermohonan) {
                        Hakmilik hm = hp.getHakmilik();
                        if (hm == null) {
                            continue;
                        }
                        HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();

                        hakmilikUrusan.setInfoAudit(info);
                        hakmilikUrusan.setDaerah(hm.getDaerah());
                        hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
                        hakmilikUrusan.setCawangan(permohonan.getCawangan());
                        hakmilikUrusan.setHakmilik(hp.getHakmilik());
                        hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
                        hakmilikUrusan.setLuasTerlibat(hp.getLuasTerlibat());
                        hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
                        hakmilikUrusan.setKodUnitLuas(hp.getHakmilik().getKodUnitLuas());
                        hakmilikUrusan.setAktif('Y');

                        manager.saveOrUpdate(hakmilikUrusan); // save urusan in table hakmilik_urusan
                        withdrawTask(permohonan);
                    }

                    tx.commit();
                    addSimpleMessage("Urusan telah selesai.");
                } catch (Exception e) {
                    tx.rollback();
                    LOG.error(e.getMessage());
                    permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                    ku = permohonan.getKodUrusan();
                    pguna = pengguna;
                    hakmilikPermohonan = permohonan.getSenaraiHakmilik();
                    addSimpleError("Urusan tidak berjaya didaftarkan.");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
                } finally {
                    integrateHcap(permohonan);
                }
                listKandunganFolder = new ArrayList<KandunganFolder>();
                List<KandunganFolder> listTempKandunganFolder = new ArrayList<KandunganFolder>();
                List<Permohonan> listPermohonan = new ArrayList<Permohonan>();
                if (permohonan.getIdKumpulan() != null) {
                    LOG.info(" /* DAFTAR */ " + permohonan.getIdPermohonan());
                } else {
                    listPermohonan.add(permohonan);
                }

                for (Permohonan p : listPermohonan) {
                    listTempKandunganFolder.addAll(p.getFolderDokumen().getSenaraiKandungan());
                }
                for (KandunganFolder kf : listTempKandunganFolder) {
                    if (ArrayUtils.contains(PAPAR_PRINT_DOKUMEN, kf.getDokumen().getKodDokumen().getKod())) {
                        listKandunganFolder.add(kf);
                    }
                }
            }
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_cetak.jsp");
        } else {
            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
            ku = permohonan.getKodUrusan();
            pguna = pengguna;
            hakmilikPermohonan = permohonan.getSenaraiHakmilik();
            addSimpleError("Urusan tidak berjaya didaftarkan. Sila tandatangan terlebih dahulu");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
        }
    }

    public boolean checkingSign(Permohonan p) {
        LOG.info(" /* checkingSign */ " + permohonan.getIdPermohonan());
        List<KandunganFolder> senaraiDok = permohonan.getFolderDokumen().getSenaraiKandungan();
        LOG.info("senaraiDok.size() : " + senaraiDok.size());
        for (KandunganFolder kf : senaraiDok) {
            LOG.info("kf.getDokumen().getIdDokumen() : " + kf.getDokumen().getIdDokumen());
            if (kf == null) {
                continue;
            }
            Dokumen d = kf.getDokumen();
            if (d.getKodDokumen().getKod().equals("DHDE")
                    && StringUtils.isNotBlank(d.getNamaFizikal())) {
                String docPath = conf.getProperty("document.path");
                String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
                LOG.info("path DHDE : " + path);
                File signFile = new File(path + ".sig");
                if (!signFile.exists()) {
                    return false;
                }
            }
            if (d.getKodDokumen().getKod().equals("DHKE")
                    && StringUtils.isNotBlank(d.getNamaFizikal())) {
                String docPath = conf.getProperty("document.path");
                String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
                LOG.info("path DHKE : " + path);
                File signFile = new File(path + ".sig");
                if (!signFile.exists()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateNoVersi(Permohonan permohonan, InfoAudit info) {
        LOG.info(" /* UPDATE NO VERSI */ ");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        List<HakmilikPermohonan> senaraiHakmilik = new ArrayList();
        List<Hakmilik> lhk = new ArrayList();
        List<HakmilikLama> senaraiHakmilikLama = new ArrayList();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            Hakmilik hk = hp.getHakmilik();
            hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
            hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hk.setInfoAudit(info);
            lhk.add(hk);

            hakmilikLama = hakmilikLamaDAO.findById(hp.getHakmilik().getIdHakmilik());
            if (hakmilikLama != null) {
                hakmilikLama.setVersiDhdk(hk.getNoVersiDhde().toString());
                hakmilikLama.setVersiDhkk(hk.getNoVersiDhke().toString());
                hakmilikLama.setPegawaiJilid16(pengguna.getNama());
                hakmilikLama.setPenggunaJilid16(pengguna);
                hakmilikLama.setTarikhJilid16(info.getTarikhKemaskini());
                senaraiHakmilikLama.add(hakmilikLama);
            }
//            else{
//                hakmilikLama.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
//                hakmilikLama.setVersiDhdk(hk.getNoVersiDhde().toString());
//                hakmilikLama.setVersiDhkk(hk.getNoVersiDhke().toString());
//                hakmilikLama.setPegawaiJilid16(pengguna.getNama());
//                hakmilikLama.setPenggunaJilid16(pengguna);
//                hakmilikLama.setTarikhJilid16(info.getTarikhKemaskini());
//                    info.setDimasukOleh(pengguna);
//                    info.setTarikhMasuk(new java.util.Date());
//                hakmilikLama.setInfoAudit(info);
//                senaraiHakmilikLama.add(hakmilikLama);
//            }
        }
        regService.simpanHakmilikList(lhk);
        regService.simpanHakmilikLamaList(senaraiHakmilikLama);
    }

    //integrate with HCAP
    //todo : save sign file to HCAP
    private void integrateHcap(Permohonan p) {
        LOG.info(" /* integrateHcap */ ");
        WORMUtil worm = etanahContextListener.newInstance(WORMUtil.class);
        List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
        String docPath = conf.getProperty("document.path");
        List<HakmilikPermohonan> senaraiHm = p.getSenaraiHakmilik();
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
                    try {
                        int status = worm.put(dhde,
                                hm.getIdHakmilik(),
                                hm.getDaerah().getKod(), hm.getBandarPekanMukim().getbandarPekanMukim(),
                                null,
                                hm.getKodHakmilik().getKod(),
                                String.valueOf(hm.getNoVersiDhde() != null ? hm.getNoVersiDhde() : 0),
                                hm.getKodStatusHakmilik().getKod());
                        if (status == WORMUtil.SC_CREATED
                                || status == WORMUtil.SC_CREATED_W_ERROR) {
                            // once successed create on WORM
                            // delete file on dms, update path dokumen
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
                            // once successed create on WORM
                            // delete file on dms, update path dokumen
                            sign.delete();
                        }
                    } catch (IOException ex) {
                    }
                }
            }
        }
        if (!senaraiDokumen.isEmpty()) {
            dokumenService.saveOrUpdate(senaraiDokumen);
        }
    }

    @HandlesEvent("Step7a")
    public Resolution carianPerserahan() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idMohon);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        if (idMohon != null) {
            permohonan = permohonanDAO.findById(idMohon);

            if (permohonan != null) {
//            carian();
                permohonan = permohonanDAO.findById(idMohon);
//            hakmilikPermohonan = permohonan.getSenaraiHakmilik();
                hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
                idHakmilikFirst1 = hakmilikPermohonan.get(0).getHakmilik();
                idHakmilikFirst = hakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
                if (!permohonan.getStatus().equals("TK")) {
                    senaraiFasaPermohonan = FasaPermohonanservice.findStatusDESC(idMohon);
//                    if (senaraiFasaPermohonan.size() > 0) {
//                        mohonFasa = senaraiFasaPermohonan.get(0);
//                        if (!pguna.getIdPengguna().equals(mohonFasa.getIdPengguna())) {
//                            addSimpleError(idMohon + " yang dimasukan tidak berada dalam inbox anda");
//                            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
//                        }
//                    } else {
//                        addSimpleError(idMohon + " Sila pastikan perserahan telah diambil terlebih dahulu");
//                        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
//                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    @HandlesEvent("carianHakmilikBetween")
    public Resolution carianHakmilikBetween() {
        LOG.info(" /* CARIANPERSERAHAN */ " + idMohon);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);
        LOG.info(" /* CARIANPERSERAHAN */ " + permohonan);
        LOG.info(" /* CARIANPERSERAHAN */ " + idMohon);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);

        permohonan = permohonanDAO.findById(idMohon);
//        hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
//        idHakmilikFirst1 = hakmilikPermohonan.get(0).getHakmilik();
        if (permohonan != null) {
            if (idHakmilik2 != null) {
                if (idHakmilikFirst != null) {
                    String id = idMohon;
                    String idHakmilikMula = idHakmilikFirst;
                    String idHakmilikAkhir = idHakmilik2;
                    LOG.info("id : " + id);
                    LOG.info("id : " + idHakmilikAkhir);
                    LOG.info("id : " + idHakmilikMula);
                    senaraiHP = hakmilikPermohonanService.carianHakmilikBetween(idMohon, idHakmilikMula, idHakmilikAkhir);
                    if (senaraiHP.size() > 0){
                    permohonan = permohonanDAO.findById(idMohon);
//                    hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
                    idHakmilikFirst1 = senaraiHP.get(0).getHakmilik();
                    idHakmilikFirst = senaraiHP.get(0).getHakmilik().getIdHakmilik();
                    }else {
                        addSimpleError("Sila Semak dengan Pihak IT Untuk Id Hakmilik Terakhir Untuk Urusan " + permohonan.getIdPermohonan());
                    }
                    if (!permohonan.getStatus().equals("TK")) {
                        senaraiFasaPermohonan = FasaPermohonanservice.findStatusDESC(idMohon);
                        mohonFasa = senaraiFasaPermohonan.get(0);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    public Resolution simpanKeputusan3() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        LOG.info(idMohon);
        LOG.info(pguna);

        if (idMohon != null) {
            senaraiFasaPermohonan = FasaPermohonanservice.findStatusDESC(idMohon);
            InfoAudit info = new InfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            if (senaraiFasaPermohonan.size() > 0) {
                mohonFasa = senaraiFasaPermohonan.get(0);
                if (mohonFasa.getIdAliran().equals("keputusan")) {
                    KodKeputusan kp = kodKeputusanDAO.findById("D");
                    mohonFasa.setKeputusan(kp);
                    mohonFasa.setTarikhKeputusan(new Date());
                    mohonFasa.setInfoAudit(info);
                    FasaPermohonanservice.saveOrUpdate(mohonFasa);
                }
                Permohonan mohon = permohonanDAO.findById(idMohon);
                if (mohon != null) {
                    KodKeputusan kodKpsn = kodKeputusanDAO.findById("D");
                    mohon.setKeputusan(kodKpsn);
                    mohon.setKeputusanOleh(pguna);
                    mohon.setInfoAudit(info);
                    permohonanDAO.saveOrUpdate(mohon);
                }
                List<HakmilikPermohonan> listHakmilikP = hakmilikPermohonanService.findByIdPermohonanOnly(idMohon);
                for (HakmilikPermohonan hp : listHakmilikP) {
                    Hakmilik hm = hp.getHakmilik();
                    if (hm.getNoVersiDhde() == null) {
                        hm.setNoVersiDhde(0);
                    }
                    if (hm.getNoVersiDhke() == null) {
                        hm.setNoVersiDhke(0);
                    }
                    hm.setInfoAudit(info);
                    hakmilikService.saveHakmilik(hm);
                }
            }
        }
        carianPerserahan();
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    public void simpanKeputusan() {
        LOG.info(idMohon);
        LOG.info(pguna);
        if (idMohon != null) {
            senaraiFasaPermohonan = FasaPermohonanservice.findStatus(idMohon);
            if (senaraiFasaPermohonan.size() > 0) {
                mohonFasa = senaraiFasaPermohonan.get(0);
                if (mohonFasa.getIdAliran().equals("keputusan")) {
                    KodKeputusan kp = kodKeputusanDAO.findById("D");
                    mohonFasa.setKeputusan(kp);
                    FasaPermohonanservice.saveOrUpdate(mohonFasa);
                }
            }
        }
        carianPerserahan();
    }

    @HandlesEvent("Step7")
    public Resolution carian() {
        LOG.info(" /* CARIAN */ ");
        permohonan = new Permohonan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        permohonan = permohonanDAO.findById(idMohon);
        if (permohonan != null) {
            ku = permohonan.getKodUrusan();
            hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
            if ((permohonan.getKodUrusan().getKod().equals("HSTHK"))
                    || (permohonan.getKodUrusan().getKod().equals("HKTHK"))) {
                if (permohonan.getStatus().equals("SL")) {
                    addSimpleError("ID Permohonan " + permohonan.getIdPermohonan() + " yang dimasukkan telah didaftarkan.");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
                } else if ((permohonan.getStatus().equals("BP")) || (permohonan.getStatus().equals("TK"))) {
                    addSimpleError("ID Permohonan " + permohonan.getIdPermohonan() + " yang dimasukkan telah dibatalkan.");
                    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
                } else {
                    hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
                    return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
                }
            } else {
                addSimpleError("ID Permohonan yang dimasukkan bukan Urusan Tukar Ganti.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
            }
        } else {
            addSimpleError("ID Permohonan yang dimasukkan tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
        }
    }

//    @HandlesEvent("Step8")
//    public Resolution janaSemula() {
//        LOG.info(" /* JANASEMULA */ " + permohonan.getIdPermohonan());
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        pguna = ctx.getUser();
//        Permohonan p = permohonanDAO.findById(permohonan.getIdPermohonan());
//        ku = p.getKodUrusan();
//
//        boolean flag = onGenerateReports(p);
//        LOG.info("flag : " + flag);
//
//        addSimpleMessage("Dokumen telah berjaya dijana semula.");
//        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
//    }
    public Resolution delete() {
        LOG.info("DELETE : " + getContext().getRequest().getParameter("id").trim());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        String idHp = getContext().getRequest().getParameter("id").trim();
        String idMh = getContext().getRequest().getParameter("idMohon").trim();
        HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idHp));

        Hakmilik h = hp.getHakmilik();
        h.setDhde(null);
        h.setDhke(null);
        h.setPelan(null);
        manager.saveOrUpdate(h);                    // delete id_dhke, id_dhde, id_pelan table Hakmilik

        Dokumen d1 = hp.getDokumen2();
        Dokumen d2 = hp.getDokumen3();
        Dokumen d3 = hp.getDokumen5();
        Dokumen d4 = hp.getDokumen6();

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT kf FROM etanah.model.KandunganFolder kf "
                + "WHERE kf.dokumen.idDokumen in (:kod1,:kod2,:kod3,:kod4)");
        q.setLong("kod1", d1.getIdDokumen());
        q.setLong("kod2", d2.getIdDokumen());
        q.setLong("kod3", d3.getIdDokumen());
        q.setLong("kod4", d4.getIdDokumen());

        List<KandunganFolder> senaraiDokumen = q.list();
        LOG.info("senaraiDokumen.size() : " + senaraiDokumen.size());
        manager.deleteKandunganFolder(senaraiDokumen);  // delete folder_dok

        manager.deleteHakmilikPermohonan(hp);       // delete mohon_hakmilik
        permohonan = permohonanDAO.findById(idMh);
        hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);

        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
    }

    @HandlesEvent("Step9")
    public Resolution mainPage() {
        return new RedirectResolution(UtilitiTukarGantActionBean.class);
    }

    @HandlesEvent("Step10")
    public Resolution searchingByIDHakmilik() {
        LOG.info(" /* CARIAN ID HAKMILIK */ ");
        permohonan = new Permohonan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();

        Hakmilik hm = hakmilikDAO.findById(idHakmilik);
        if (hm == null) {
            addSimpleError("ID Hakmilik yang dimasukkan tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
        }

        if ((hm.getNoVersiDhde() > 0) && (hm.getNoVersiDhke() > 0)) {
            addSimpleError("ID Hakmilik yang dimasukkan telah ditukar ganti.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
        } else {
            Session s = sessionProvider.get();
            Query q = s.createQuery("SELECT m FROM etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp "
                    + "WHERE hp.permohonan.idPermohonan = m.idPermohonan "
                    + "AND m.kodUrusan.kod in ('HKTHK','HSTHK') "
                    + "AND m.status != 'BP' "
                    + "AND hp.hakmilik.idHakmilik =:idHakmilik");
            q.setString("idHakmilik", hm.getIdHakmilik());
            Permohonan p = (Permohonan) q.uniqueResult();
            if (p != null) {
                permohonan = permohonanDAO.findById(p.getIdPermohonan());
                hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
                ku = permohonan.getKodUrusan();
                hakmilikPermohonan = regService.senaraiMohonHakmilikOrderByASC(idMohon);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_daftar.jsp");
    }

    @HandlesEvent("Step11")
    public Resolution cariPerserahan() {
        LOG.info(" /* CARIAN PERSERAHAN */ ");
        perserahan = permohonanDAO.findById(idSerah);
        if (perserahan == null) {
            addSimpleError("ID Perserahan yang dimasukkan tidak dijumpai.");
        } else {
            flag = true;
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
    }

    @HandlesEvent("Step12")
    public Resolution hapusPerserahan() {
        LOG.info(" /* HAPUS PERSERAHAN */ ");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = ctx.getUser();
        InfoAudit info = new InfoAudit();

        perserahan = permohonanDAO.findById(idSerah);
        List<HakmilikPermohonan> listHp = new ArrayList<HakmilikPermohonan>();
        if (perserahan != null) {
            Transaction tx = sessionProvider.get().beginTransaction();
            tx.begin();
            try {
                hakmilikPermohonan = perserahan.getSenaraiHakmilik();
                listHp = hakmilikPermohonan;
                for (HakmilikPermohonan hp : hakmilikPermohonan) {
                    HakmilikPermohonan mh = hp;
                    Hakmilik h = hp.getHakmilik();
                    h.setDhde(null);
                    h.setDhke(null);
                    h.setPelan(null);
                    manager.saveOrUpdate(h);                    // delete id_dhke, id_dhde, id_pelan table Hakmilik

                    Dokumen d1 = hp.getDokumen2();
                    Dokumen d2 = hp.getDokumen3();
                    Dokumen d3 = hp.getDokumen5();
                    Dokumen d4 = hp.getDokumen6();

                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT kf FROM etanah.model.KandunganFolder kf "
                            + "WHERE kf.dokumen.idDokumen in (:kod1,:kod2,:kod3,:kod4)");
                    q.setLong("kod1", d1.getIdDokumen());
                    q.setLong("kod2", d2.getIdDokumen());
                    q.setLong("kod3", d3.getIdDokumen());
                    q.setLong("kod4", d4.getIdDokumen());

                    List<KandunganFolder> senaraiDokumen = q.list();
                    LOG.info("senaraiDokumen.size() : " + senaraiDokumen.size());
                    manager.deleteKandunganFolder(senaraiDokumen);  // delete folder_dok

                    hp.setAktif('T');
                    info.setDimasukOleh(hp.getInfoAudit().getDimasukOleh());
                    info.setTarikhMasuk(hp.getInfoAudit().getTarikhMasuk());
                    info.setDiKemaskiniOleh(pguna);
                    info.setTarikhKemaskini(new Date());
                    hp.setInfoAudit(info);
                    manager.saveOrUpdate(hp);       // delete mohon_hakmilik
                }

                perserahan.setStatus("TK");
                info.setDimasukOleh(perserahan.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(perserahan.getInfoAudit().getTarikhMasuk());
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new Date());
                perserahan.setInfoAudit(info);
                manager.saveOrUpdate(perserahan);
                tx.commit();
                addSimpleMessage("ID Perserahan " + perserahan.getIdPermohonan() + " telah berjaya dihapuskan.");
            } catch (Exception e) {
                tx.rollback();
                LOG.error(e.getMessage());
                addSimpleError("ID Perserahan " + perserahan.getIdPermohonan() + " tidak berjaya dihapuskan.");
            }
            perserahan = new Permohonan();
            permohonan = new Permohonan();
            idSerah = "";
        }
        return new RedirectResolution(UtilitiTukarGantActionBean.class);
    }

//    public Resolution janaTukarGanti() {
//        LOG.info(" /* JANATUKARGANTI */ ");
//        String err = getContext().getRequest().getParameter("idHakmilik");
//        LOG.info(" /* JANATUKARGANTI */ " + err);
//        String[] tmp = err.split(",");
//        LOG.info("tmp : " + tmp.length);
//
//        for (String str : tmp) {
//            LOG.info("str : " + str);
//            Hakmilik h = hakmilikDAO.findById(str);
//            if (h != null) {
//                // find hakmilik sementara
//                if ((h.getKodHakmilik().getKod().equals("HSD"))
//                        || (h.getKodHakmilik().getKod().equals("HSM"))
//                        || (h.getKodHakmilik().getKod().equals("HMM"))) {
//                    senaraiHakmilikSementara.add(h);
//                } else {
//                    senaraiHakmilikKekal.add(h);
//                }
//            }
//        }
//
//        if (senaraiHakmilikKekal.size() > 0) {
//            LOG.info("--------------------------kekal----------------------------------------");
//            kodUrusan = "HKTHK";
//            ku = kodUrusanDAO.findById(kodUrusan);
//            senaraiHakmilik = senaraiHakmilikKekal;
//            selesai();
//            hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
//        }
//        LOG.info("senaraiHakmilik.size : " + senaraiHakmilik.size());
//        senaraiHakmilik = new ArrayList<Hakmilik>();
//        if (senaraiHakmilikSementara.size() > 0) {
//            LOG.info("--------------------------sementara----------------------------------------");
//            kodUrusan = "HSTHK";
//            ku = kodUrusanDAO.findById(kodUrusan);
//            senaraiHakmilik = senaraiHakmilikSementara;
//            selesai();
//            hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
//        }
//        LOG.info("senaraiHakmilik.size : " + senaraiHakmilik.size());
//
//        LOG.info("hakmilikPermohonan.size : " + hakmilikPermohonan.size());
//        LOG.info("senaraiHakmilikSementara.size : " + senaraiHakmilikSementara.size());
//        LOG.info("senaraiHakmilikKekal.size : " + senaraiHakmilikKekal.size());
//        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tukarganti_main.jsp");
//    }
    @HandlesEvent("Step8a")
    public Resolution onGeneratePelan() {
        LOG.info(" /* onGenerateGeran */ " + idMohon);
        LOG.info(" /* onGenerateGeran */ " + senaraiHP);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);

        senaraiHP = hakmilikPermohonanService.carianHakmilikBetween(idMohon, idHakmilikMula, idHakmilikAkhir);
        permohonan = permohonanDAO.findById(idMohon);
        ku = permohonan.getKodUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        pguna = ctx.getUser();

        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        String idFolder = "";

        String idPermohonan = permohonan.getIdPermohonan();
        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String path2 = "";
        String path3 = "";
        String gen2 = "";
        String gen3 = "";
        KodDokumen kd = new KodDokumen();
        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();

        KodUrusan ku = permohonan.getKodUrusan();

        String[] urusanConvert = {"HSTHK", "HKTHK"};

//        if (ArrayUtils.contains(urusanConvert, ku.getKod())) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", permohonan.getIdPermohonan());
        hakmilikPermohonan = q.list();
        for (HakmilikPermohonan hp : senaraiHP) {
            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            LOG.info("hp.getId() : " + hp.getId());
            if (hm == null) {
                continue;
            }

            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};

            //save to mohon hakmilikLogic.
//                hp.setDokumen2(e);
//                hm.setDhke(e);
//                hp.setDokumen3(f);
//                hm.setDhde(f);
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
            LOG.debug("START GENERATE PELAN DHDE");
            e = saveOrUpdateDokumen(fd, kd2, hm.getIdHakmilik(), context, hakmilikPermohonan.size());
            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            LOG.info("::Path To save :: " + path2);
            LOG.info("::Report Name ::" + gen2);
            syslog.info(peng.getIdPengguna() + " generate report " + gen2 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
            LOG.debug("::saving pelan into mohon hakmilik::");
            hp.setDokumen5(e);
            hm.setPelan(e);

            //gen Pelan DHKE
            LOG.debug("START GENERATE PELAN DHKE");
            f = saveOrUpdateDokumen(fd, kd3, hm.getIdHakmilik(), context, hakmilikPermohonan.size());
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            LOG.info("::Path To save :: " + path3);
            LOG.info("::Report Name ::" + gen3);
            syslog.info(peng.getIdPengguna() + " generate report " + gen3 + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            LOG.debug("::saving pelan into mohon hakmilik::");
            hp.setDokumen6(f);

            manager.saveOrUpdate(hp);
            manager.saveOrUpdate(hm);
//            }
        }
        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Pelan Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    public void calculateIdhakmilik(String idHakmilikLama, String idMohon) {
        idHakmilikFirst1 = hakmilikDAO.findById(idHakmilikLama);
        HakmilikPermohonan hp1 = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilikFirst1.getIdHakmilik(), idMohon);
        if (hp1 != null) {
            int noHakmilik = Integer.valueOf(idHakmilikFirst1.getNoHakmilik());
            noHakmilik = noHakmilik + 1;
            String noHakmilik1 = StringUtils.leftPad(String.valueOf(noHakmilik), 8, '0');
            String bpm = StringUtils.leftPad(String.valueOf(idHakmilikFirst1.getBandarPekanMukim().getKod()), 2, '0');
            LOG.info(" /* onGenerateGeran */ " + noHakmilik);
            idHakmilikFirst = "04" + idHakmilikFirst1.getDaerah().getKod() + bpm + idHakmilikFirst1.getKodHakmilik().getKod() + noHakmilik1;
            idHakmilik2 = null;
            LOG.info(" /* onGenerateGeran */ " + noHakmilik);
            senaraiFasaPermohonan = FasaPermohonanservice.findStatusDESC(hp1.getPermohonan().getIdPermohonan());
            mohonFasa = senaraiFasaPermohonan.get(0);
        } else {
            addSimpleError(idHakmilikLama + " adalah hakmilik terakhir untuk urusan ini");
        }
    }

    @HandlesEvent("Step8c")
    public Resolution onGenerateDHKKPT() {
        LOG.info(" /* onGenerateGeran */ " + idMohon);
        LOG.info(" /* onGenerateGeran */ " + senaraiHP);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);

        senaraiHP = hakmilikPermohonanService.carianHakmilikBetween(idMohon, idHakmilikMula, idHakmilikAkhir);
        permohonan = permohonanDAO.findById(idMohon);
//        Permohonan p = permohonanDAO.findById(idMohon);
        ku = permohonan.getKodUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        pguna = ctx.getUser();

        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        String idFolder = "";

        String idPermohonan = permohonan.getIdPermohonan();
        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String path2 = "";
        String path3 = "";
        String gen2 = "";
        String gen3 = "";
        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();
        KodDokumen kd4 = new KodDokumen();
        KodDokumen kd5 = new KodDokumen();

        KodUrusan ku = permohonan.getKodUrusan();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        permohonan = permohonanDAO.findById(idMohon);
//        for (HakmilikPermohonan hp : senaraiHP) {
//            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//            LOG.info("hp.getId() : " + hp.getId());
//            if (hm == null) {
//                continue;
//            }
//            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
//            values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
//            gen2 = "REG_BorangB2eMLK.rdf";
//            gen3 = "REG_BorangB2eMLK_DHKe.rdf";
//
//            kd2.setKod("PB2DE");
//            kd3.setKod("PB2KE");
//
//            LOG.debug("START GENERATE PELAN DHDE");
//            e = saveOrUpdateDokumen(fd, kd2, hp.getHakmilik().getIdHakmilik(), context, senaraiHP.size());
//            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
//            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
//            hp.setDokumen5(e);
//            hp.getHakmilik().setPelan(e);
//            
//         
//
//            LOG.debug("START GENERATE PELAN DHKE");
//            f = saveOrUpdateDokumen(fd, kd3, hp.getHakmilik().getIdHakmilik(), context, senaraiHP.size());
//            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
//            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
//            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
//            hp.setDokumen6(f);
//
////            LOG.debug("Waiting for reports to complete...");
//////            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
//////            hp.setDokumen5(e);
//////            hp.setDokumen6(f);
//
//            manager.saveOrUpdate(hp);
//        }

        for (HakmilikPermohonan hp : senaraiHP) {
            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            LOG.info("hp.getId() : " + hp.getId());
            if (hm == null) {
                continue;
            }

            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
//            gen3 = "REGDrafDokHMMLK001_util.rdf"; //DHDE report name
            gen2 = "REGDrafDokHMMLK001_util.rdf"; //DHKE report name
            Dokumen d = null;
            String path = "";
            kd4.setKod("DDHDK");
            d = saveOrUpdateDokumen(fd, kd4, hp.getHakmilik().getIdHakmilik(), context, senaraiHP.size());
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen2, params, values, dokumenPath + path, peng);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            hp.setDokumen1(d);
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            hakmilikService.saveHakmilik(hp.getHakmilik());

            LOG.debug("Waiting for reports to complete...");


            //save to mohon hakmilikLogic.



            manager.saveOrUpdate(hp);
        }
        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    @HandlesEvent("Step8b")
    public Resolution onGenerateGeran() {
        LOG.info(" /* onGenerateGeran */ " + idMohon);
        LOG.info(" /* onGenerateGeran */ " + senaraiHP);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilikFirst);
        LOG.info(" /* CARIANPERSERAHAN */ " + idHakmilik2);

        String id = idMohon;
        String idHakmilikMula = idHakmilikFirst;
        String idHakmilikAkhir = idHakmilik2;
        LOG.info("id : " + id);
        LOG.info("id : " + idHakmilikAkhir);
        LOG.info("id : " + idHakmilikMula);

        senaraiHP = hakmilikPermohonanService.carianHakmilikBetween(idMohon, idHakmilikMula, idHakmilikAkhir);
        permohonan = permohonanDAO.findById(idMohon);
//        Permohonan p = permohonanDAO.findById(idMohon);
        ku = permohonan.getKodUrusan();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        pguna = ctx.getUser();

        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        String idFolder = "";

        String idPermohonan = permohonan.getIdPermohonan();
        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String path2 = "";
        String path3 = "";
        String gen2 = "";
        String gen3 = "";
        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();

        KodUrusan ku = permohonan.getKodUrusan();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        permohonan = permohonanDAO.findById(idMohon);

        for (HakmilikPermohonan hp : senaraiHP) {
            Hakmilik hm = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            LOG.info("hp.getId() : " + hp.getId());
            if (hm == null) {
                continue;
            }

            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hm.getIdHakmilik()};
            //                gen2 = "regBorangHMDHKE.rdf"; //DHKE report name
            gen2 = "regBorangHMDHKE_util.rdf"; //DHKE report name
            gen3 = "regBorangHMDHDE_util.rdf"; //DHDE report name

            kd2.setKod("DHKE");
            kd3.setKod("DHDE");

            e = saveOrUpdateDokumenV2DHKE(fd, idMohon, kd2, hm.getIdHakmilik(), context, 0);
            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());

            LOG.info("::Path To save DHKE:: " + path2);
            LOG.info("::Report Name DHKE::" + gen2);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path2);
//            Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
            Future<byte[]> dhke = executor.schedule(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng), 1, TimeUnit.SECONDS);
//            executor.submit(new CallableReport(reportUtil, gen2, params, values, dokumenPath + path2, peng));
            File signKE = new File(dokumenPath + path2 + ".sig");
            if (signKE.exists()) {
                signKE.delete();
            }
            try {
                dhke.get();
            } catch (Exception ex) {
                LOG.debug(ex.getMessage(), ex);
            }

            f = saveOrUpdateDokumenV2DHDE(fd, idMohon, kd3, hm.getIdHakmilik(), context, 0);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            LOG.info("::Path To save DHDE:: " + path3);
            LOG.info("::Report Name DHDE::" + gen3);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hm.getIdHakmilik() + " and saving it to:" + path3);
            Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, gen3, params, values, dokumenPath + path3, peng), 1, TimeUnit.SECONDS);
            File signDE = new File(dokumenPath + path3 + ".sig");
            if (signDE.exists()) {
                signDE.delete();
            }
            try {
                dhde.get();
            } catch (Exception ex) {
                LOG.debug(ex.getMessage(), ex);
            }

            LOG.debug("Waiting for reports to complete...");

            if (kd2.getKod().equals("DHKE")) {
                updatePathDokumenDHKE(reportUtil.getDMSPath(), e.getIdDokumen());
            }
            if (kd3.getKod().equals("DHDE")) {
                updatePathDokumenDHDE(reportUtil2.getDMSPath(), f.getIdDokumen());
            }

            //save to mohon hakmilikLogic.
            hp.setDokumen2(e);
            hm.setDhke(e);
            hp.setDokumen3(f);
            hm.setDhde(f);

            manager.saveOrUpdate(hp);
            manager.saveOrUpdate(hm);

        }

        calculateIdhakmilik(idHakmilik2, idMohon);
        addSimpleMessage("Geran Hakmilik telah berjaya dijana semula.");
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pecah_sepadan_main.jsp");
    }

    public Resolution withdrawTask(Permohonan mohon) throws WorkflowException {
        LOG.info(" withdrawTask ");
        LOG.info("mohon.getIdPermohonan() : " + mohon.getIdPermohonan());
        String error = "";
        String msg = "";
        String taskId = "";
        String idPermohonan = mohon.getIdPermohonan();

        List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
        if (l != null) {
            for (Task t : l) {
                taskId = t.getSystemAttributes().getTaskId();
                if (StringUtils.isNotBlank(taskId)) {
                    try {
                        WorkFlowService.withdrawTask(taskId);
                        msg = "Withdraw Success!!";
                        addSimpleMessage("Permohonan " + mohon.getIdPermohonan() + " telah selesai.");
                    } catch (StaleObjectException ex) {
                        ex.printStackTrace();
                        LOG.error(ex);
                        addSimpleError(ex.getMessage());
                        return new RedirectResolution(WithdrawTaskActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                    }
                }
            }
        }
        return new RedirectResolution(WithdrawTaskActionBean.class, "showForm");
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(List<HakmilikPermohonan> hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public KodPenyerah getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(KodPenyerah penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public ArrayList<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getPenyerahNegeriNama() {
        return kodNegeriDAO.findById(penyerahNegeri.getKod()).getNama();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Hakmilik> getSenaraiHakmilikKekal() {
        return senaraiHakmilikKekal;
    }

    public void setSenaraiHakmilikKekal(List<Hakmilik> senaraiHakmilikKekal) {
        this.senaraiHakmilikKekal = senaraiHakmilikKekal;
    }

    public List<Hakmilik> getSenaraiHakmilikSementara() {
        return senaraiHakmilikSementara;
    }

    public void setSenaraiHakmilikSementara(List<Hakmilik> senaraiHakmilikSementara) {
        this.senaraiHakmilikSementara = senaraiHakmilikSementara;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdSerah() {
        return idSerah;
    }

    public void setIdSerah(String idSerah) {
        this.idSerah = idSerah;
    }

    public Permohonan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(Permohonan perserahan) {
        this.perserahan = perserahan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIdHakmilikDari() {
        return idHakmilikDari;
    }

    public void setIdHakmilikDari(String idHakmilikDari) {
        this.idHakmilikDari = idHakmilikDari;
    }

    public String getIdHakmilikHingga() {
        return idHakmilikHingga;
    }

    public void setIdHakmilikHingga(String idHakmilikHingga) {
        this.idHakmilikHingga = idHakmilikHingga;
    }

    public int getBilHakmilikSiri() {
        return bilHakmilikSiri;
    }

    public void setBilHakmilikSiri(int bilHakmilikSiri) {
        this.bilHakmilikSiri = bilHakmilikSiri;
    }

    public String getListHakmilikSiri() {
        return listHakmilikSiri;
    }

    public void setListHakmilikSiri(String listHakmilikSiri) {
        this.listHakmilikSiri = listHakmilikSiri;
    }

    public HakmilikLama getHakmilikLama() {
        return hakmilikLama;
    }

    public void setHakmilikLama(HakmilikLama hakmilikLama) {
        this.hakmilikLama = hakmilikLama;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public String getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(String idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public List<HakmilikPermohonan> getSenaraiHP() {
        return senaraiHP;
    }

    public void setSenaraiHP(List<HakmilikPermohonan> senaraiHP) {
        this.senaraiHP = senaraiHP;
    }

    public String getIdHakmilikFirst() {
        return idHakmilikFirst;
    }

    public void setIdHakmilikFirst(String idHakmilikFirst) {
        this.idHakmilikFirst = idHakmilikFirst;
    }

    public FasaPermohonanService getFasaPermohonanservice() {
        return FasaPermohonanservice;
    }

    public void setFasaPermohonanservice(FasaPermohonanService FasaPermohonanservice) {
        this.FasaPermohonanservice = FasaPermohonanservice;
    }

    public Hakmilik getIdHakmilikFirst1() {
        return idHakmilikFirst1;
    }

    public void setIdHakmilikFirst1(Hakmilik idHakmilikFirst1) {
        this.idHakmilikFirst1 = idHakmilikFirst1;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

}
