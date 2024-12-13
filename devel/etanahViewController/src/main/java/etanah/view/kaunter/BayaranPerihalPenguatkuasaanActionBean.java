/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import etanah.model.CaraBayaran;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAkaun;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoAkaun;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.BasicTabActionBean;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.Configuration;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;

/**
 * Based on given idPermohonan/idPerserahan, suggest the next Urusan for
 * the Permohonan. The cases:
 * 1 If the given Permohonan is NOT completed:
 * 		1.1 If the Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
 *          from user (e.g. Submit more documents, pay fees etc)
 *      1.2 If not in SPOC's senaraiTugasan, display the status
 * 2 If Permohonan is completed, check the next suggested Urusan for the Permohonan. E.g. after Consent's
 *   "Kelulusan Pindahmilik", next is Registration's "PindahMilik Tanah".
 * 3 If Permohonan is completed but rejected, suggest Urusan for Rayuan.
 * @author user
 *
 */
@HttpCache(allow = false)
@UrlBinding("/kaunter/BayaranPerihalKuasa")
public class BayaranPerihalPenguatkuasaanActionBean extends BasicTabActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    LelongService lelongService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private CaraBayaranDAO caraBayaranDAO;
    @Inject
    private AkaunService akaunService;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusDAO;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private GeneratorNoResit2 noResitGenerator;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    StrataPtService strataService;
    @Inject
    GeneratorNoAkaun generatorNoAkaun;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;

    private String resitNo;
    private String mod = "";
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private static String kodNegeri;
    BigDecimal total = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranPerihalPenguatkuasaanActionBean.class);
    private String idPermohonan;
    List<BayaranValue> listBayaran = new ArrayList();
    List<BayaranValue> listSahBayaran = new ArrayList();
    IWorkflowContext ctxW = null;
    Pengguna pguna = null;
    Permohonan permohonan;
    Pemohon pmohon;
    private String kod="79501";
    List<Akaun> akaunDeposit;
    Akaun akAmanah;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    Configuration configuration;

    @DefaultHandler
    public Resolution showForm() {
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }
        return new JSP("kaunter/bayaran_perihal_kuasa.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        return new RedirectResolution(PermohonanKaunter.class);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("IDPERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
//kes kalu nk filter by modul
        if (permohonan.getKodUrusan().getJabatan().getKod().equals("")) {
            /**
             * kalau xpakai table mohon tuntut kos, set kat dlm ni. Set jumlahCaj sekali!
             */
            if (total == null) {
                total = total; // set if total is null
            } else {
                total = total.add(total); //total from all amount
            }
        } else {
            senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            LOG.info("senaraiMohonTuntutKos : " + senaraiMohonTuntutKos.size());
            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
                LOG.info("senaraiMohonTuntutBayar size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
                if (senaraiMohonTuntutBayar.size() == 0) {
                    BayaranValue list = new BayaranValue();
                    list.setIdKos(pTK.getIdKos());
                    list.setIdPermohonan(pTK.getPermohonan().getIdPermohonan());
                    list.setAmaun(pTK.getAmaunTuntutan());
                    list.setKodUrusan(pTK.getPermohonan().getKodUrusan().getKod());
                    list.setNamaUrusan(pTK.getItem());
                    list.setKodTransaksi(pTK.getKodTransaksi().getKod());
                    list.setPermohonan(permohonan);
                    list.setTuntutKos(pTK);
                    listBayaran.add(list);

                    if (total == null) {
                        total = pTK.getAmaunTuntutan();
                    } else {
                        total = total.add(pTK.getAmaunTuntutan());
                    }
                }
            }
            LOG.info("listBayaran : " + listBayaran.size());
            jumlahCaj = total;
        }
    }

    public Resolution save() {
        LOG.info("start save");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");

        if (permohonan.getKodUrusan().getJabatan().getKod().equals("24")) {
            total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
        }

        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        mod = modKutip.loadPenyerahFromSession(ctx);
        try {
            //set KEW_DOKUMEN
//            resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
            resitNo = noResitGenerator.getAndLockSerialNo(pengguna);       //new noResitGenerator
            LOG.info("RESIT NO:" + resitNo);
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resitNo);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            dk.setAmaunBayaran(total);
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setAkaun(akTerima);
            if (mod != null && mod.length() > 0) {
                dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
            }
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);
            System.out.println("value kew_dok :" + dk.getIdDokumenKewangan());
            //set KEW_TRANS
            String arrSplit[], aSplit;
            pmohon = enforceService.findById(idPermohonan);
            Long idPihak = pmohon.getPihak().getIdPihak();
            akaunDeposit = enforceService.findByIDMohonIDPihak(idPermohonan, Long.toString(idPihak));
            for (BayaranValue mtk : listBayaran) {
                for (int i = 1; i <= listBayaran.size(); i++) {
                    aSplit = getContext().getRequest().getParameter("idKos" + i);
                    if (aSplit != null) {
                        System.out.println("asplit x null :" + aSplit);
                        arrSplit = aSplit.split(",");
                        if (Long.parseLong(arrSplit[0]) == mtk.getIdKos()) {
                            listSahBayaran.add(mtk);
                            LOG.info("START KEW_AKAUN");
                            // create Ak.Amanah

                            if (akaunDeposit.size() == 0) {
                                Akaun akAmanah = new Akaun();
                                KodCawangan cawAkaunDeposit = permohonan.getCawangan();
                                KodAkaun akaunDeposit = new KodAkaun();
                                akaunDeposit.setKod("DP");
                                akAmanah.setCawangan(cawAkaunDeposit);
                                akAmanah.setNoAkaun(generatorNoAkaun.generate(configuration.getProperty("kodNegeri"), caw, akAmanah));
                                akAmanah.setKodAkaun(akaunDeposit);
                                akAmanah.setPermohonan(mohon);
                                akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
                                akAmanah.setPemegang(pmohon.getPihak());
                                akAmanah.setBilCetakPenyata(0);
                                akAmanah.setBaki(new BigDecimal(0)); // kredit
                                akAmanah.setInfoAudit(ia);
                                akaunDAO.save(akAmanah);
                                System.out.println("value kew_akaun :" + akAmanah.getNoAkaun());

                                LOG.info("END KEW_AKAUN: " + akAmanah.getNoAkaun());
                            }

                            LOG.info("START KEW_TRANS");
                            Transaksi trans = new Transaksi();

                            trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                            trans.setAkaunDebit(akTerima);
                            trans.setAkaunKredit(akAmanah);
                            trans.setAmaun(mtk.getAmaun());
                            trans.setUntukTahun(year);
                            trans.setPermohonan(mtk.getPermohonan());
                            trans.setDokumenKewangan(dk);
                            trans.setStatus(kodStatusDAO.findById('T'));
                            trans.setKuantiti(1);
                            trans.setInfoAudit(ia);
                            trans.setCawangan(caw);
                            transaksiDAO.save(trans);
                            System.out.println("value kew_trans :" + trans.getIdTransaksi());

                            LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());

                            LOG.info("START PermohonanTuntutanBayaran");
                            PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                            ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
                            ptb.setDokumenKewangan(dk);
                            ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                            ptb.setTarikhBayar(now);
                            ptb.setInfoAudit(ia);
                            permohonanTuntutanBayarDAO.save(ptb);
                            System.out.println("value mohon_tuntut_bayar :" + ptb.getIdBayar());
                            LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
                            break;
                        }
                    }
                }
            }
            //new requirement by hasil's team
            penyataPemungutService.savePenyataPemungut(dk);
        } catch (Exception e) {
            LOG.error(e);
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
        } finally {
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }


//set KEW_AKAUN ---> AKH
        LOG.info(akTerima.getCawangan().getKod());
        if (!total.equals(BigDecimal.ZERO)) {
            s.lock(akTerima, LockMode.UPGRADE);
            akTerima.setBaki(akTerima.getBaki().add(total));
        }



//dokumen dokumen_capai folder folder_dok
        long idFolder = mohon.getFolderDokumen().getFolderId();
        Dokumen resit = new Dokumen();
        resit.setFormat("application/pdf");
        resit.setInfoAudit(ia);
        resit.setKlasifikasi(klasifikasiAm);
        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
        resit.setKodDokumen(kodResit);
        resit.setNoVersi("1.0");
        LOG.info(kodResit.getNama());
        resit.setTajuk("Resit Bayaran Proses");
        resit = dokumenDAO.saveOrUpdate(resit);
        System.out.println("value dokumen :"+resit.getIdDokumen());
 tx.commit();
        LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, null);
        } else {
            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, null);
        }
        resit.setNamaFizikal(reportUtil.getDMSPath());
        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
        dokumenDAO.update(resit);

        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(resit);
        kf.setFolder(folderDokumenDAO.findById(idFolder));
        kf.setInfoAudit(ia);
        kf.setCatatan("");
        kandunganFolderDAO.save(kf);
        System.out.println("value folder_dok :"+kf.getIdKandunganFolder());



        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

//        System.out.println("mohon :"+mohon.getIdPermohonan()+ ">> "+mohon);
//        System.out.println("stage id : "+stageId);
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
//        System.out.println("fasa :"+fasaPermohonan);
//        fasaPermohonan.setPermohonan(mohon);
//        fasaPermohonan.setCawangan(mohon.getCawangan());
//        fasaPermohonan.setInfoAudit(ia);
//        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//        fasaPermohonan.setIdAliran(stageId);
//        fasaPermohonan.setTarikhHantar(new java.util.Date());

        //lelong
//        if (permohonan.getKodUrusan().getJabatan().getKod().equals("8")) {
//
//            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                if (stageId.equals("bayaranKomisyen")) {
//                    FasaPermohonan ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
//                    fasaPermohonan.setKeputusan(ff.getKeputusan());
//                }
//            } else {
//
//                //not finish yet
//                if (stageId.equals("bayaran")) {
//                    FasaPermohonan ff = lelongService.findFasaPermohonanSediaPengistiharan(permohonan.getIdPermohonan());
//                    fasaPermohonan.setKeputusan(ff.getKeputusan());
//                }
//                if (stageId.equals("bayaranKomisyen")) {
//                    FasaPermohonan ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
//                    fasaPermohonan.setKeputusan(ff.getKeputusan());
//                }
//
//            }
//        }
//        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        String msg = "";
        StringBuilder sb = new StringBuilder();
        try {
            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                LOG.info("TaskID ::" + taskID);
                LOG.info("idPermohonan ::" + idPermohonan);
                WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        System.out.println("agihan tugas ");
        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        System.out.println("agihan tugas after");
        addSimpleMessage("Urusan telah berjaya didaftarkan.");
        System.out.println("Urusan telah berjaya didaftarkan ");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
    }

//    public Resolution save() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();
//        KodCawangan caw = pengguna.getKodCawangan();
//        Permohonan mohon = permohonanDAO.findById(idPermohonan);
//        Date now = new Date();
//        int year = now.getYear() + 1900;
//        InfoAudit ia = new InfoAudit();
//        ia.setDimasukOleh(pengguna);
//        ia.setTarikhMasuk(now);
//        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
//        String documentPath = conf.getProperty("document.path");
//
//        if (permohonan.getKodUrusan().getJabatan().getKod().equals("24")) {
//            total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
//        }
//
//        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
//        if (akTerima == null) {
//            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
//            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
//        }
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        //set KEW_DOKUMEN
//        resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
//        LOG.info("RESIT NO:" + resitNo);
//        DokumenKewangan dk = new DokumenKewangan();
//        dk.setIdDokumenKewangan(resitNo);
//        // set idResit to pageContext
//        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
//        dk.setAmaunBayaran(total);
//        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
//        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
//        dk.setInfoAudit(ia);
//        dk.setCawangan(caw);
//        dk.setAkaun(akTerima);
//        saveCaraBayaran(caw, dk, ia);
//        dokumenKewanganDAO.save(dk);
////set KEW_TRANS
//        String arrSplit[], aSplit;
//    //tambah nie
//        pmohon=enforceService.findById(idPermohonan);
//        Long idPihak=pmohon.getPihak().getIdPihak();
//        akaunDeposit=enforceService.findByIDMohonIDPihak(idPermohonan,Long.toString(idPihak));
//
//        for (BayaranValue mtk : listBayaran) {
//            for (int i = 0; i < listBayaran.size(); i++) {
//                aSplit = getContext().getRequest().getParameter("idKos" + i);
//                if (aSplit != null) {
//                    arrSplit = aSplit.split(",");
//                    if (Long.parseLong(arrSplit[0]) == mtk.getIdKos()) {
//                        listSahBayaran.add(mtk);
//                        LOG.info("START KEW_TRANS");
//                          // create Ak.Amanah
//
//                        if(akaunDeposit.size()==0){
//			Akaun akAmanah = new Akaun();
//			KodCawangan cawAkaunDeposit = permohonan.getCawangan();
//                        KodAkaun akaunDeposit=new KodAkaun();
//                        akaunDeposit.setKod("DP");
//			akAmanah.setCawangan(cawAkaunDeposit);
//			akAmanah.setNoAkaun(generatorNoAkaun.generate(configuration.getProperty("kodNegeri"), caw, akAmanah));
//			akAmanah.setKodAkaun(akaunDeposit);
//			akAmanah.setPermohonan(mohon);
//			akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
//			akAmanah.setPemegang(pmohon.getPihak());
//			akAmanah.setBilCetakPenyata(0);
//			akAmanah.setBaki(new BigDecimal(0)); // kredit
//			akAmanah.setInfoAudit(ia);
//			akaunDAO.save(akAmanah);
//
//                        LOG.info("END KEW_AKAUN: " + akAmanah.getNoAkaun());
//                        }
//
//
//                        Transaksi trans = new Transaksi();
//                        trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
//                        trans.setAkaunDebit(akTerima);
//                        trans.setAmaun(mtk.getAmaun());
//                        trans.setUntukTahun(year);
//                        trans.setPermohonan(mtk.getPermohonan());
//                        trans.setDokumenKewangan(dk);
//                        trans.setPerihal(listBayaran.get(i).getNamaUrusan());
//                        trans.setStatus(kodStatusDAO.findById('T'));
//                        trans.setKuantiti(1);
//                        trans.setInfoAudit(ia);
//                        trans.setCawangan(caw);
//                        transaksiDAO.save(trans);
//                        LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());
//
//                        LOG.info("START PermohonanTuntutanBayaran");
//                        PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
//                        ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
//                        ptb.setDokumenKewangan(dk);
//                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
//                        ptb.setTarikhBayar(now);
//                        ptb.setInfoAudit(ia);
//                        permohonanTuntutanBayarDAO.save(ptb);
//                        LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
//                        break;
//                    }
//                }
//            }
//        }
//
//
////set KEW_AKAUN ---> AKH
//        LOG.info(akTerima.getCawangan().getKod());
//        if (!total.equals(BigDecimal.ZERO)) {
//            s.lock(akTerima, LockMode.UPGRADE);
//            akTerima.setBaki(akTerima.getBaki().add(total));
//        }
//
//
//
////dokumen dokumen_capai folder folder_dok
//        long idFolder = mohon.getFolderDokumen().getFolderId();
//        Dokumen resit = new Dokumen();
//        resit.setFormat("application/pdf");
//        resit.setInfoAudit(ia);
//        resit.setKlasifikasi(klasifikasiAm);
//        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
//        resit.setKodDokumen(kodResit);
//        resit.setNoVersi("1.0");
//        LOG.info(kodResit.getNama());
//        resit.setTajuk("Resit Bayaran Proses");
//        resit = dokumenDAO.saveOrUpdate(resit);
//        tx.commit();
//        LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
//        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
//        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//            reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
//                    new String[]{"p_id_kew_dok"},
//                    new String[]{resitNo},
//                    path + path2, pengguna);
//        } else {
//            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
//                    new String[]{"p_id_kew_dok"},
//                    new String[]{resitNo},
//                    path + path2, pengguna);
//        }
//        resit.setNamaFizikal(reportUtil.getDMSPath());
//        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
//        dokumenDAO.update(resit);
//
//        KandunganFolder kf = new KandunganFolder();
//        kf.setDokumen(resit);
//        kf.setFolder(folderDokumenDAO.findById(idFolder));
//        kf.setInfoAudit(ia);
//        kf.setCatatan("");
//        kandunganFolderDAO.save(kf);
//
//
//
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//        }
//
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
//        fasaPermohonan.setPermohonan(permohonan);
//        fasaPermohonan.setCawangan(permohonan.getCawangan());
//        fasaPermohonan.setInfoAudit(ia);
//        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//        fasaPermohonan.setIdAliran(stageId);
//        fasaPermohonan.setTarikhHantar(new java.util.Date());
//
//        String kpsn = "APPROVE";
//
//        //lelong
//        if (permohonan.getKodUrusan().getJabatan().getKod().equals("8")) {
//            LOG.info("----Lelong-----");
//            FasaPermohonan ff = null;
//            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                LOG.info("----Melaka----");
//                if (stageId.equals("bayaranKomisyen")) {
//                    LOG.info("----bayaranKomisyen----");
//                    ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
//                    if (ff != null) {
//                        LOG.info("---cetakSuratBakiBidaan---");
//                        fasaPermohonan.setKeputusan(ff.getKeputusan());
//                        LOG.info("----ff.getKeputusan().getKod() : " + ff.getKeputusan().getKod());
//                        if (ff.getKeputusan().getKod().equals("LB") || ff.getKeputusan().getKod().equals("AC")) {
//                            LOG.info("-----hoho-----");
//                            kpsn = ff.getKeputusan().getKod();
//                            LOG.info("----kpsn----- : " + kpsn);
//                            KodStatusLelongan kod = null;
//                            if (permohonan.getSenaraiHakmilik().size() >= 2) {
//                                List<Lelongan> ll = lelongService.listLelonganAP(permohonan.getIdPermohonan());
//                                List<Hakmilik> hh = new ArrayList<Hakmilik>();
//                                List<Lelongan> lel = new ArrayList<Lelongan>();
//                                KodUrusan kk = kodUrusanDAO.findById("PPJP");
//                                Pengguna pp = penggunaDAO.findById("ptptdlelong1");
//                                FolderDokumen fff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
//                                LOG.info("BigDecimal.ZERO : " + BigDecimal.ZERO);
//                                for (Lelongan oo : ll) {
//                                    if (oo.getBaki() == BigDecimal.ZERO) {
//                                        hh.add(oo.getHakmilikPermohonan().getHakmilik());
//                                        lel.add(oo);
//                                    }
//                                }
//                                LOG.info("hh.size() : " + hh.size());
//                                if (hh.size() > 0) {
//                                    LOG.info("----tak bayar lagi----");
//                                    try {
//                                        Permohonan p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, fff,lel);
////                                    updateOutcome(p, pp);
//                                        IWorkflowContext ctxx = workFlowService.authenticate(pp.getIdPengguna());
//                                        LOG.info("IDMohon : " + p.getIdPermohonan());
//                                        List<Task> taskList = null;
//                                        do {
//                                            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
//                                            LOG.info("taskList : " + taskList.size());
//                                        } while (taskList.size() == 0);
//                                        if (taskList.size() > 0) {
//                                            Task t = taskList.get(0);
//                                            String stageID = null;
//                                            if (t.getSystemAttributes().getTaskId() != null) {
//                                                taskId = t.getSystemAttributes().getTaskId();
//                                                LOG.info("taskId : " + taskId);
//                                                Task tt = null;
//                                                do {
//                                                    if (stageID != null) {
//                                                        if (stageID.equals("semakanPermohonan") || stageID.equals("semak16HLantikJurulelong") || stageID.equals("semakPembida")) {
//                                                            LOG.info("-----pptlelong----");
//                                                            Pengguna ppp = penggunaDAO.findById("pptlelong1");
//                                                            ctxx = workFlowService.authenticate(ppp.getIdPengguna());
//                                                        } else if (stageID.equals("bayaranKomisyen")) {
//                                                            LOG.info("-----ptspoc1----");
//                                                            Pengguna ppp = penggunaDAO.findById("ptspoc1");
//                                                            ctxx = workFlowService.authenticate(ppp.getIdPengguna());
//                                                        } else {
//                                                            LOG.info("-----ptptdlelong----");
//                                                            ctxx = workFlowService.authenticate(pp.getIdPengguna());
//                                                        }
//                                                    }
//                                                    tt = workFlowService.acquireTask(taskId, ctxx);
//                                                    LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
//                                                    do {
//                                                        taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
//                                                        LOG.info("taskList : " + taskList.size());
//                                                    } while (taskList.size() == 0);
//                                                    t = taskList.get(0);
//                                                    taskId = t.getSystemAttributes().getTaskId();
//                                                    stageID = workFlowService.updateTaskOutcome(taskId, ctxx, "APPROVE");
//                                                    LOG.info("stageID : " + stageID);
//                                                } while (!stageID.equals("sediaCetak16I"));
//                                            }
//                                            FasaPermohonan fp = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
//                                            lelongService.delete(fp);
//                                        }
//                                    } catch (Exception ex) {
//                                        LOG.info("Error : " + ex);
//                                    }
//
//                                }
//                            }
//                            if (permohonan.getSenaraiHakmilik().size() == 1) {
//                                LOG.info("------1------");
//                                List<Lelongan> ll = lelongService.listLelonganAP(permohonan.getIdPermohonan());
//                                for (Lelongan oo : ll) {
//                                    kod = kodStatusLelonganDAO.findById("TK");
//                                    oo.setKodStatusLelongan(kod);
//                                    lelongService.saveOrUpdate(oo);
//                                    FasaPermohonan fp = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
//                                    lelongService.delete(fp);
//                                }
//                            }
//                        }
//                    }
//                    ff = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());
//                    if (ff != null) {
//                        if (ff.getKeputusan().getKod().equals("RM")) {
//                            kpsn = ff.getKeputusan().getKod();
//                        }
//                    }
//                }
//            } else {
//                LOG.info("----Negori----");
//                //not finish yet
//                if (stageId.equals("bayaran")) {
//                    ff = lelongService.findFasaPermohonanSediaPengistiharan(permohonan.getIdPermohonan());
//                    fasaPermohonan.setKeputusan(ff.getKeputusan());
//                }
//                if (stageId.equals("bayaranKomisyen")) {
//                    ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
//                    fasaPermohonan.setKeputusan(ff.getKeputusan());
//                }
//
//            }
//
//        }
//        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
//
//        String msg = "";
//        StringBuilder sb = new StringBuilder();
//        try {
//            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
//            for (Map<String, String> map : list) {
//                String taskID = map.get("taskId");
//                String idPermohonan = map.get("idPermohonan");
//                if (sb.length() > 0) {
//                    sb.append(",");
//                }
//                sb.append(idPermohonan);
//                LOG.info("TaskID ::" + taskID);
//                LOG.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctxW, kpsn);
////                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return new RedirectResolution(BayaranPerihalPenguatkuasaanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
//        }
//
//        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        addSimpleMessage("Urusan telah berjaya didaftarkan.");
//
//        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
//    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        LOG.info("START CARABAYAR:-----------------------------");
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        LOG.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);
        return list;
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        BayaranPerihalPenguatkuasaanActionBean.kodNegeri = kodNegeri;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<BayaranValue> getListBayaran() {
        return listBayaran;
    }

    public void setListBayaran(List<BayaranValue> listBayaran) {
        this.listBayaran = listBayaran;
    }

    public List<BayaranValue> getListSahBayaran() {
        return listSahBayaran;
    }

    public void setListSahBayaran(List<BayaranValue> listSahBayaran) {
        this.listSahBayaran = listSahBayaran;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}


///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package etanah.view.kaunter;
//
//import etanah.model.CaraBayaran;
//import net.sourceforge.stripes.action.DefaultHandler;
//import net.sourceforge.stripes.action.DontValidate;
//import net.sourceforge.stripes.action.ForwardResolution;
//import net.sourceforge.stripes.action.HandlesEvent;
//import net.sourceforge.stripes.action.Resolution;
//import net.sourceforge.stripes.action.UrlBinding;
//import etanah.view.etanahActionBeanContext;
//
//import able.stripes.JSP;
//import com.google.inject.Inject;
//import etanah.dao.CaraBayaranDAO;
//import etanah.dao.DokumenDAO;
//import etanah.dao.DokumenKewanganDAO;
//import etanah.dao.FolderDokumenDAO;
//import etanah.dao.KandunganFolderDAO;
//import etanah.dao.KodDokumenDAO;
//import etanah.dao.KodKlasifikasiDAO;
//import etanah.dao.KodStatusDokumenKewanganDAO;
//import etanah.dao.KodStatusTransaksiKewanganDAO;
//import etanah.dao.KodTransaksiDAO;
//import etanah.dao.PenggunaDAO;
//import etanah.dao.PermohonanDAO;
//import etanah.dao.PermohonanTuntutanBayarDAO;
//import etanah.dao.TransaksiDAO;
//import etanah.model.Akaun;
//import etanah.model.Dokumen;
//import etanah.model.DokumenKewangan;
//import etanah.model.DokumenKewanganBayaran;
//import etanah.model.FasaPermohonan;
//import etanah.model.InfoAudit;
//import etanah.model.KandunganFolder;
//import etanah.model.KodCawangan;
//import etanah.model.KodDokumen;
//import etanah.model.KodKlasifikasi;
//import etanah.model.Pengguna;
//import etanah.model.Permohonan;
//import etanah.model.PermohonanTuntutanBayar;
//import etanah.model.PermohonanTuntutanKos;
//import etanah.model.Transaksi;
//import etanah.report.ReportUtil;
//import etanah.sequence.GeneratorNoResit;
//import etanah.service.AkaunService;
//import etanah.service.BPelService;
//import etanah.service.LaporanTanahService;
//import etanah.service.StrataPtService;
//import etanah.service.common.PengambilanDepositService;
//import etanah.service.common.LelongService;
//import etanah.view.BasicTabActionBean;
//import etanah.workflow.WorkFlowService;
//import java.io.File;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import net.sourceforge.stripes.action.Before;
//import net.sourceforge.stripes.action.HttpCache;
//import net.sourceforge.stripes.action.RedirectResolution;
//import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.workflow.task.model.Task;
//import oracle.bpel.services.workflow.verification.IWorkflowContext;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.hibernate.LockMode;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import etanah.sequence.GeneratorNoAkaun;
//import etanah.Configuration;
//import etanah.dao.AkaunDAO;
//import etanah.dao.KodAkaunDAO;
//import etanah.dao.KodStatusAkaunDAO;
//import etanah.model.KodAkaun;
//import etanah.model.Pemohon;
//
///**
// * Based on given idPermohonan/idPerserahan, suggest the next Urusan for
// * the Permohonan. The cases:
// * 1 If the given Permohonan is NOT completed:
// * 		1.1 If the Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
// *          from user (e.g. Submit more documents, pay fees etc)
// *      1.2 If not in SPOC's senaraiTugasan, display the status
// * 2 If Permohonan is completed, check the next suggested Urusan for the Permohonan. E.g. after Consent's
// *   "Kelulusan Pindahmilik", next is Registration's "PindahMilik Tanah".
// * 3 If Permohonan is completed but rejected, suggest Urusan for Rayuan.
// * @author user
// *
// */
//@HttpCache(allow = false)
//@UrlBinding("/kaunter/BayaranPerihalKuasa")
//public class BayaranPerihalPenguatkuasaanActionBean extends BasicTabActionBean {
//
//    @Inject
//    private etanah.Configuration conf;
//    @Inject
//    LaporanTanahService laporanTanahService;
//    @Inject
//    LelongService lelongService;
//    @Inject
//    StrataPtService strService;
//    @Inject
//    KodKlasifikasiDAO kodKlasifikasiDAO;
//    @Inject
//    protected com.google.inject.Provider<Session> sessionProvider;
//    @Inject
//    private PermohonanDAO permohonanDAO;
//    @Inject
//    PenggunaDAO penggunaDAO;
//    @Inject
//    private FolderDokumenDAO folderDokumenDAO;
//    @Inject
//    private DokumenDAO dokumenDAO;
//    @Inject
//    private KandunganFolderDAO kandunganFolderDAO;
//    @Inject
//    private CaraBayaranDAO caraBayaranDAO;
//    @Inject
//    private AkaunService akaunService;
//    @Inject
//    private DokumenKewanganDAO dokumenKewanganDAO;
//    @Inject
//    private TransaksiDAO transaksiDAO;
//    @Inject
//    private KodTransaksiDAO kodTransaksiDAO;
//    @Inject
//    private KodDokumenDAO kodDokumenDAO;
//    @Inject
//    private KodStatusTransaksiKewanganDAO kodStatusDAO;
//    @Inject
//    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
//    @Inject
//    private GeneratorNoResit noResitGenerator;
//    @Inject
//    private ReportUtil reportUtil;
//    @Inject
//    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
//    @Inject
//    StrataPtService strataService;
//    @Inject
//    PengambilanDepositService pengambilanService;
//
//    @Inject
//    GeneratorNoAkaun generatorNoAkaun;
//    @Inject
//    Configuration configuration;
//    @Inject
//    AkaunDAO akaunDAO;
//    @Inject
//    KodAkaunDAO kodAkaunDAO;
//    @Inject
//    KodStatusAkaunDAO kodStatusAkaunDAO;
//    private String resitNo;
//    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
//    private BigDecimal jumlahCaj;
//    private static String kodNegeri;
//    BigDecimal total = null;
//    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
//    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
//    private static final Logger LOG = Logger.getLogger(BayaranPerihalPenguatkuasaanActionBean.class);
//    private String idPermohonan;
//    List<BayaranValue> listBayaran = new ArrayList();
//    List<BayaranValue> listSahBayaran = new ArrayList();
//    IWorkflowContext ctxW = null;
//    Pengguna pguna = null;
//    Permohonan permohonan;
//    Pemohon pmohon;
//    private String kod="11304";
//    List<Akaun> akaunDeposit;
//    Akaun akAmanah;
//
//    @DefaultHandler
//    public Resolution showForm() {
//        if (senaraiCaraBayaran == null) {
//            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
//        }
//
//        if (senaraiCaraBayaran.size() == 0) {
//            for (int i = 0; i < 5; i++) {
//                CaraBayaran cr = new CaraBayaran();
//                cr.setAmaun(BigDecimal.ZERO); // reset amount
//                senaraiCaraBayaran.add(cr);
//            }
//        }
//        return new JSP("kaunter/bayaran_perihal_kuasa.jsp").addParameter("tab", "true");
//    }
//
//    @HandlesEvent("Kembali")
//    @DontValidate
//    public Resolution Kembali() {
////        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        return new RedirectResolution(PermohonanKaunter.class);
//
//    }
//
//    @Before(stages = {LifecycleStage.BindingAndValidation})
//    public void rehydrate() {
//        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        try {
//            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
//        } catch (Exception e) {
//            LOG.error("error ::" + e.getMessage());
//        }
//
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        LOG.info("IDPERMOHONAN: " + idPermohonan);
//        permohonan = permohonanDAO.findById(idPermohonan);
////kes kalu nk filter by modul
//        if (permohonan.getKodUrusan().getJabatan().getKod().equals("")) {
//            /**
//             * kalau xpakai table mohon tuntut kos, set kat dlm ni. Set jumlahCaj sekali!
//             */
//            if (total == null) {
//                total = total; // set if total is null
//            } else {
//                total = total.add(total); //total from all amount
//            }
//        } else {
//
//
//            senaraiMohonTuntutKos = pengambilanService.findByIDMohonTuntut(idPermohonan,kod);
//
//            LOG.info("senaraiMohonTuntutKos : " + senaraiMohonTuntutKos.size());
//            listBayaran = new ArrayList<BayaranValue>();
//            total = new BigDecimal(BigInteger.ZERO);
//            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
//                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
//                LOG.info("senaraiMohonTuntutBayar size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
//                if (senaraiMohonTuntutBayar.size() == 0) {
//                    BayaranValue list = new BayaranValue();
//                    list.setIdKos(pTK.getIdKos());
//                    list.setIdPermohonan(pTK.getPermohonan().getIdPermohonan());
//                    list.setAmaun(pTK.getAmaunTuntutan());
//                    list.setKodUrusan(pTK.getPermohonan().getKodUrusan().getKod());
//                    list.setNamaUrusan(pTK.getItem());
//                    list.setKodTransaksi(pTK.getKodTransaksi().getKod());
//                    list.setPermohonan(permohonan);
//                    list.setTuntutKos(pTK);
//                    listBayaran.add(list);
//
//                    if (total == null) {
//                        total = pTK.getAmaunTuntutan();
//                    } else {
//                        total = total.add(pTK.getAmaunTuntutan());
//                    }
//
//            }
//
//
//
//            }
//
//
//            LOG.info("listBayaran : " + listBayaran.size());
//            jumlahCaj = total;
//        }
//
//    }
//
//    public Resolution save() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();
//        KodCawangan caw = pengguna.getKodCawangan();
//        Permohonan mohon = permohonanDAO.findById(idPermohonan);
//        Date now = new Date();
//        int year = now.getYear() + 1900;
//        InfoAudit ia = new InfoAudit();
//        ia.setDimasukOleh(pengguna);
//        ia.setTarikhMasuk(now);
//        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
//        String documentPath = conf.getProperty("document.path");
//
//        if (permohonan.getKodUrusan().getJabatan().getKod().equals("24")) {
//            total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
//        }
//
//        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
//        if (akTerima == null) {
//            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
//            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
//        }
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        //set KEW_DOKUMEN
//        resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
//        LOG.info("RESIT NO:" + resitNo);
//        DokumenKewangan dk = new DokumenKewangan();
//        dk.setIdDokumenKewangan(resitNo);
//        // set idResit to pageContext
//        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
//        dk.setAmaunBayaran(total);
//        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
//        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
//        dk.setInfoAudit(ia);
//        dk.setCawangan(caw);
//        dk.setAkaun(akTerima);
//        saveCaraBayaran(caw, dk, ia);
//        dokumenKewanganDAO.save(dk);
////set KEW_TRANS
//        String arrSplit[], aSplit;
//        pmohon=pengambilanService.findById(idPermohonan);
//        Long idPihak=pmohon.getPihak().getIdPihak();
//        akaunDeposit=pengambilanService.findByIDMohonIDPihak(idPermohonan,Long.toString(idPihak));
//        for (BayaranValue mtk : listBayaran) {
//            for (int i = 1; i <= listBayaran.size(); i++) {
//                aSplit = getContext().getRequest().getParameter("idKos" + i);
//                if (aSplit != null) {
//                    arrSplit = aSplit.split(",");
//                    if (Long.parseLong(arrSplit[0]) == mtk.getIdKos()) {
//                        listSahBayaran.add(mtk);
//                        LOG.info("START KEW_AKAUN");
//                        // create Ak.Amanah
//
//                        if(akaunDeposit.size()==0){
//			Akaun akAmanah = new Akaun();
//			KodCawangan cawAkaunDeposit = permohonan.getCawangan();
//                        KodAkaun akaunDeposit=new KodAkaun();
//                        akaunDeposit.setKod("DP");
//			akAmanah.setCawangan(cawAkaunDeposit);
//			akAmanah.setNoAkaun(generatorNoAkaun.generate(configuration.getProperty("kodNegeri"), caw, akAmanah));
//			akAmanah.setKodAkaun(akaunDeposit);
//			akAmanah.setPermohonan(mohon);
//			akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
//			akAmanah.setPemegang(pmohon.getPihak());
//			akAmanah.setBilCetakPenyata(0);
//			akAmanah.setBaki(new BigDecimal(0)); // kredit
//			akAmanah.setInfoAudit(ia);
//			akaunDAO.save(akAmanah);
//
//                        LOG.info("END KEW_AKAUN: " + akAmanah.getNoAkaun());
//                        }
//
//
//                        LOG.info("START KEW_TRANS");
//                        Transaksi trans = new Transaksi();
//
//                        trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
//                        trans.setAkaunDebit(akTerima);
//                        trans.setAkaunKredit(akAmanah);
//                        trans.setAmaun(mtk.getAmaun());
//                        trans.setUntukTahun(year);
//                        trans.setPermohonan(mtk.getPermohonan());
//                        trans.setDokumenKewangan(dk);
//                        trans.setStatus(kodStatusDAO.findById('T'));
//                        trans.setKuantiti(1);
//                        trans.setInfoAudit(ia);
//                        trans.setCawangan(caw);
//                        transaksiDAO.save(trans);
//
//                        LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());
//
//                        LOG.info("START PermohonanTuntutanBayaran");
//                        PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
//                        ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
//                        ptb.setDokumenKewangan(dk);
//                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
//                        ptb.setTarikhBayar(now);
//                        ptb.setInfoAudit(ia);
//                        permohonanTuntutanBayarDAO.save(ptb);
//                        LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
//                        break;
//                    }
//                }
//            }
//
//
//        }
//
//
////set KEW_AKAUN ---> AKH
//        LOG.info(akTerima.getCawangan().getKod());
//        if (!total.equals(BigDecimal.ZERO)) {
//            s.lock(akTerima, LockMode.UPGRADE);
//            akTerima.setBaki(akTerima.getBaki().add(total));
//        }
//
//
//
////dokumen dokumen_capai folder folder_dok
//        long idFolder = mohon.getFolderDokumen().getFolderId();
//        Dokumen resit = new Dokumen();
//        resit.setFormat("application/pdf");
//        resit.setInfoAudit(ia);
//        resit.setKlasifikasi(klasifikasiAm);
//        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
//        resit.setKodDokumen(kodResit);
//        resit.setNoVersi("1.0");
//        LOG.info(kodResit.getNama());
//        resit.setTajuk("Resit Bayaran Proses");
//        resit = dokumenDAO.saveOrUpdate(resit);
// tx.commit();
//        LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
//        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
//        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//            reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
//                    new String[]{"p_id_kew_dok"},
//                    new String[]{resitNo},
//                    path + path2, null);
//        } else {
//            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
//                    new String[]{"p_id_kew_dok"},
//                    new String[]{resitNo},
//                    path + path2, null);
//        }
//        resit.setNamaFizikal(reportUtil.getDMSPath());
//        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
//        dokumenDAO.update(resit);
//
//        KandunganFolder kf = new KandunganFolder();
//        kf.setDokumen(resit);
//        kf.setFolder(folderDokumenDAO.findById(idFolder));
//        kf.setInfoAudit(ia);
//        kf.setCatatan("");
//        kandunganFolderDAO.save(kf);
//
//
//
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//        }
//
//
//        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
//        fasaPermohonan.setPermohonan(mohon);
//        fasaPermohonan.setCawangan(mohon.getCawangan());
//        fasaPermohonan.setInfoAudit(ia);
//        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//        fasaPermohonan.setIdAliran(stageId);
//        fasaPermohonan.setTarikhHantar(new java.util.Date());
//
//        //lelong
////        if (permohonan.getKodUrusan().getJabatan().getKod().equals("8")) {
////
////            if ("04".equals(conf.getProperty("kodNegeri"))) {
////                if (stageId.equals("bayaranKomisyen")) {
////                    FasaPermohonan ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
////                    fasaPermohonan.setKeputusan(ff.getKeputusan());
////                }
////            } else {
////
////                //not finish yet
////                if (stageId.equals("bayaran")) {
////                    FasaPermohonan ff = lelongService.findFasaPermohonanSediaPengistiharan(permohonan.getIdPermohonan());
////                    fasaPermohonan.setKeputusan(ff.getKeputusan());
////                }
////                if (stageId.equals("bayaranKomisyen")) {
////                    FasaPermohonan ff = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
////                    fasaPermohonan.setKeputusan(ff.getKeputusan());
////                }
////
////            }
////        }
////        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
//
//        String msg = "";
//        StringBuilder sb = new StringBuilder();
//        try {
//            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
//            for (Map<String, String> map : list) {
//                String taskID = map.get("taskId");
//                idPermohonan = map.get("idPermohonan");
//                if (sb.length() > 0) {
//                    sb.append(",");
//                }
//                sb.append(idPermohonan);
//                LOG.info("TaskID ::" + taskID);
//                LOG.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
////                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
//        }
//
//        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        addSimpleMessage("Urusan telah berjaya didaftarkan.");
//
//        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
//    }
//
//    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
//        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
//        LOG.info("START CARABAYAR:-----------------------------");
//        for (CaraBayaran cb : senaraiCaraBayaran) {
//            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
//                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
//                // clear if null
//                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
//                    cb.setBank(null);
//                    cb.setBankCawangan(null);
//                }
//                cb.setCawangan(caw);
//                cb.setInfoAudit(ia);
//                cb.setAktif('Y');
//                caraBayaranDAO.save(cb);
//                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
//                dkb.setCaraBayaran(cb);
//                dkb.setDokumenKewangan(dk);
//                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
//                dkb.setInfoAudit(ia);
//                dkb.setAktif('Y');
//                adkb.add(dkb);
//            }
//        }
//        dk.setSenaraiBayaran(adkb);
//    }
//
//    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        Map<String, String> map = null;
//        LOG.info("Urusan tidak berangkai");
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        map = new HashMap<String, String>();
//        map.put("idPermohonan", permohonan.getIdPermohonan());
//        map.put("taskId", taskId);
//        list.add(map);
//        return list;
//    }
//
//    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
//        return senaraiMohonTuntutKos;
//    }
//
//    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
//        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
//    }
//
//    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
//        return senaraiCaraBayaran;
//    }
//
//    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
//        this.senaraiCaraBayaran = senaraiCaraBayaran;
//    }
//
//    public BigDecimal getJumlahCaj() {
//        return jumlahCaj;
//    }
//
//    public void setJumlahCaj(BigDecimal jumlahCaj) {
//        this.jumlahCaj = jumlahCaj;
//    }
//
//    public static String getKodNegeri() {
//        return kodNegeri;
//    }
//
//    public static void setKodNegeri(String kodNegeri) {
//        BayaranPerihalPenguatkuasaanActionBean.kodNegeri = kodNegeri;
//    }
//
//    public BigDecimal getTotal() {
//        return total;
//    }
//
//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }
//
//    public List<BayaranValue> getListBayaran() {
//        return listBayaran;
//    }
//
//    public void setListBayaran(List<BayaranValue> listBayaran) {
//        this.listBayaran = listBayaran;
//    }
//
//    public List<BayaranValue> getListSahBayaran() {
//        return listSahBayaran;
//    }
//
//    public void setListSahBayaran(List<BayaranValue> listSahBayaran) {
//        this.listSahBayaran = listSahBayaran;
//    }
//
//    public String getResitNo() {
//        return resitNo;
//    }
//
//    public void setResitNo(String resitNo) {
//        this.resitNo = resitNo;
//    }
//
//    public List<Akaun> getAkaunDeposit() {
//        return akaunDeposit;
//    }
//
//    public void setAkaunDeposit(List<Akaun> akaunDeposit) {
//        this.akaunDeposit = akaunDeposit;
//    }
//     public Akaun getakAmanah() {
//        return akAmanah;
//    }
//
//    public void setakAmanah(Akaun akAmanah) {
//        this.akAmanah = akAmanah;
//    }
//}
