/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.model.KodUrusan;
import etanah.model.PermohonanTuntutan;
import etanah.view.kaunter.*;
import etanah.model.CaraBayaran;
import java.util.Calendar;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Kompaun;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PengambilanDepositService;
import etanah.view.BasicTabActionBean;
import etanah.view.penguatkuasaan.BayaranKompaunActionBean;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
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


/**
 *
 * @author nordiyana
 */
@HttpCache(allow = false)
@UrlBinding("/pengambilan/BayaranDeposit")
public class BayaranDepositNSActionBean extends BasicTabActionBean {
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
    NotisPenerimaanService pengambilanService;
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
    private ReportUtil reportUtil;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    StrataPtService strataService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    PengambilanDepositService pengambilanDepositService;
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
    private static final Logger LOG = Logger.getLogger(BayaranDepositNSActionBean.class);
    private String idPermohonan;
    List<BayaranValue> listBayaran = new ArrayList();
    List<BayaranValue> listSahBayaran = new ArrayList();
    IWorkflowContext ctxW = null;
    Pengguna pguna = null;
    Permohonan permohonan;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    private Long idDokumen;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> listUnpaidKompaun;
    private String warnaModul;
    private List<PermohonanTuntutan> listMohonTuntut = new ArrayList<PermohonanTuntutan>();
    private Date date = new Date();
    private Boolean UnpaidFlag = false;
    private List<Notis> listNotis;
    private int jumlahHari;
    private Date tarikhTerima;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final long MILISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    BigDecimal totalPayment = BigDecimal.ZERO;
    private Boolean dendaTambahanFlag = false;
    private String kod="58007";

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
            System.out.println("size senarai cara bayaran : " + senaraiCaraBayaran.size());
        }

        if (listMohonTuntut.size() == 0) {
            UnpaidFlag = true;
        }

        if (UnpaidFlag == false) {
            addSimpleError("Terdapat " + listMohonTuntut.size() + " kompaun/denda yang masih belum dijelaskan dan tempoh pembayaran telah tamat");
        }
        return new JSP("penguatkuasaan/bayaran_kompaun.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
        return new RedirectResolution(BayaranDepositNSActionBean.class);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }
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

            //find senaraiMohonTuntutKos for kompaun/denda/denda tambahan/bayaran pelupusan based on negeri
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                    if (stageId.equalsIgnoreCase("112TerimaDepositpertama")) {
                        senaraiMohonTuntutKos = pengambilanService.findMohonTuntutKos(idPermohonan);
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Denda (DD) ----------- : " + senaraiMohonTuntutKos.size());
                    } 
                } 
            }// else for NS
            else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                    if (stageId.equalsIgnoreCase("bayaran_denda")) {
                        senaraiMohonTuntutKos = pengambilanService.findMohonTuntutKos(idPermohonan);
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Denda (DD) ----------- : " + senaraiMohonTuntutKos.size());
                    }
                }

            }
            System.out.println("jumlah hari rehydrate : " + jumlahHari);
            System.out.println("denda tambahan flag : " + dendaTambahanFlag);

            if (jumlahHari == 0) {
                jumlahHari = 1;
            }
            BigDecimal hari = new BigDecimal(jumlahHari);



            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            
            senaraiMohonTuntutKos = pengambilanDepositService.findByIDMohonTuntut(idPermohonan,kod);

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
            KodUrusan ku = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
            warnaModul = ku.getJabatan().getWarnaModul();
            LOG.info("warnaModul : " + warnaModul);

        }
    }

    public Resolution save() {
        LOG.info("save bayaran");
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
            dk.setIdKaunter(pengguna.getIdKaunter());
            if (mod != null && mod.length() > 0) {
                dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
            }
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);

            //set KEW_TRANS
            String arrSplit[], aSplit;
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
                for (int i = 0; i < listBayaran.size(); i++) {
                    aSplit = getContext().getRequest().getParameter("idKos" + (i + 1));
                    LOG.info("value aSplit : " + aSplit);
                    if (aSplit != null) {
                        arrSplit = aSplit.split(",");
                        LOG.info("value after split : " + arrSplit.toString() + " value mtk_idKos : " + mtk.getIdKos());
                        if (Long.parseLong(arrSplit[0]) == mtk.getIdKos()) {
                            listSahBayaran.add(mtk);

                            LOG.info("START KEW_TRANS");
                            Transaksi trans = new Transaksi();
                            trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                            trans.setAkaunDebit(akTerima);
                            trans.setAmaun(mtk.getAmaun());
                            trans.setUntukTahun(year);
                            trans.setPermohonan(mtk.getPermohonan());
                            trans.setDokumenKewangan(dk);
                            trans.setPerihal(mtk.getNamaUrusan());
                            trans.setStatus(kodStatusDAO.findById('T'));
                            trans.setKuantiti(1);
                            trans.setInfoAudit(ia);
                            trans.setCawangan(caw);
                            transaksiDAO.save(trans);
                            LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());

                            LOG.info("START PermohonanTuntutanBayaran");
                            PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                            ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
                            ptb.setDokumenKewangan(dk);
    //                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                            ptb.setAmaun(mtk.getAmaun());
                            ptb.setTarikhBayar(now);
                            ptb.setInfoAudit(ia);
                            permohonanTuntutanBayarDAO.save(ptb);
                            LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
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
            System.out.println("total sebelum add dlm akaun : " + total);
            akTerima.setBaki(akTerima.getBaki().add(total));
            System.out.println("baki dalam akaun : " + akTerima.getBaki());
        }

        //update table kompaun after payment had been paid
        try {
            LOG.info("Update table kompaun : update column resit -------size senarai kompaun --------- : " + senaraiKompaun.size());

            for (int i = 0; i < senaraiKompaun.size(); i++) {
                Kompaun kompaun = senaraiKompaun.get(i);
                if (kompaun.getResit() == null) {
                    PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (ptb != null) {
                        LOG.info("ID KOMPAUN : " + kompaun.getIdKompaun());
                        kompaun.setResit(ptb.getDokumenKewangan());
                        enforcementService.simpanKompaun(kompaun);

                        //generate report
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
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                                if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                                    //for denda
                                    resit.setTajuk("Resit Bayaran Denda");
                                    LOG.info("resit bayaran denda (DD)");
                                } else {
                                    //for denda tambahan
                                    resit.setTajuk("Resit Bayaran Denda Tambahan");
                                    LOG.info("resit bayaran denda tambahan(DT)");
                                }
                            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                                if (stageId.equalsIgnoreCase("bayaran_pelupusan")) {
                                    //for bayaran pelupusan
                                    resit.setTajuk("Resit Bayaran Pelupusan");
                                    LOG.info("resit bayaran pelupusan(BP)");
                                } else if (stageId.equalsIgnoreCase("bayaran_jaminan_rampasan")) {
                                    //for bayaran jaminan
                                    resit.setTajuk("Resit Bayaran Jaminan");
                                    LOG.info("resit bayaran jaminan (JM)");
                                } else {
                                    //for bayaran kompaun
                                    resit.setTajuk("Resit Bayaran kompaun");
                                    LOG.info("resit bayaran kompaun (sek425)");
                                }
                            } else {
                                //for bayaran kompaun others seksyen
                                resit.setTajuk("Resit Bayaran kompaun");
                                LOG.info("resit bayaran kompaun (others seksyen)");
                            }
                        }// else for NS
                        else {
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                                if (stageId.equalsIgnoreCase("bayaran_denda")) {
                                    //for denda
                                    resit.setTajuk("Resit Bayaran Denda");
                                    LOG.info("resit bayaran denda (DD)");
                                } else {
                                    //for denda tambahan
                                    resit.setTajuk("Resit Bayaran Denda Tambahan");
                                    LOG.info("resit bayaran denda tambahan(DT)");
                                }
                            } else {
                                //for bayaran kompaun others seksyen
                                resit.setTajuk("Resit Bayaran kompaun");
                                LOG.info("resit bayaran kompaun (others seksyen)");
                            }
                        }

                        resit = dokumenDAO.saveOrUpdate(resit);
                        tx.commit();
                        LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
                        idDokumen = resit.getIdDokumen();
                        LOG.info("ID DOKUMEN: " + idDokumen);
                        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
                        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            reportUtil.generateReport("HSLResitUrusanTanah_ENF_MLK.rdf",
                                    new String[]{"p_id_kew_dok"},
                                    new String[]{resitNo},
                                    path + path2, pengguna);
                        } else {
                            reportUtil.generateReport("HSLResitUrusanTanah_ENF.rdf",
                                    new String[]{"p_id_kew_dok"},
                                    new String[]{resitNo},
                                    path + path2, pengguna);
                        }
                        LOG.info("NAMA FIZIKAL TO BE SET AT DOKUMEN : " + reportUtil.getDMSPath());
                        resit.setNamaFizikal(reportUtil.getDMSPath());
                        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
                        dokumenDAO.update(resit);
                        System.out.println("after update fizikal name : " + resit.getNamaFizikal());

                        KandunganFolder kf = new KandunganFolder();
                        kf.setDokumen(resit);
                        kf.setFolder(folderDokumenDAO.findById(idFolder));
                        kf.setInfoAudit(ia);
                        kf.setCatatan("");
                        kandunganFolderDAO.save(kf);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(ia);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setTarikhHantar(new java.util.Date());
        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        if (dendaTambahanFlag == true) {
            //update hari at table kkuasa_kompaun : based on tarikh terima (notis) - tarikh bayar
            if (senaraiKompaun.size() != 0) {
                for (int i = 0; i < senaraiKompaun.size(); i++) {
                    Kompaun kompaun = senaraiKompaun.get(i);
                    if (kompaun != null) {

                        ia = kompaun.getInfoAudit();
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        LOG.info("------------update jumlahHari : Kompaun --------------");

                        kompaun.setTempohHari(jumlahHari);
                        kompaun.setInfoAudit(ia);
                        enforcementService.simpanKompaun(kompaun);
                    }
                }
            }
        }

        //if all kompaun had been paid, then only go to next stage based on kod negeri. 04(Melaka) & 05 (NS)
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                    //for denda
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DD");
                    LOG.info("size unpaid denda list : " + listUnpaidKompaun.size());
                } else {
                    //for denda tambahan
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DT");
                    LOG.info("size unpaid denda tambahan list : " + listUnpaidKompaun.size());
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                if (stageId.equalsIgnoreCase("bayaran_pelupusan")) {
                    //for bayaran pelupusan
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BP");
                    LOG.info("size unpaid Bayaran Pelupusan  list : " + listUnpaidKompaun.size());
                } else if (stageId.equalsIgnoreCase("bayaran_jaminan_rampasan")) {
                    //for bayaran jaminan
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "JM");
                    LOG.info("size unpaid Bayaran Jaminan  list : " + listUnpaidKompaun.size());
                } else {
                    //for normal kompaun
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan);
                    LOG.info("size unpaid kompaun list (425): " + listUnpaidKompaun.size());
                }
            } else {
                //for normal kompaun (others seksyen)
                listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan);
                LOG.info("size unpaid kompaun list (others seksyen): " + listUnpaidKompaun.size());
            }
        }// else for NS
        else {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("bayaran_denda")) {
                    //for denda
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DD");
                    LOG.info("size unpaid denda list : " + listUnpaidKompaun.size());
                } else {
                    //for denda tambahan
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DT");
                    LOG.info("size unpaid denda tambahan list : " + listUnpaidKompaun.size());
                }
            } else {
                listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan);
                LOG.info("size unpaid kompaun list : " + listUnpaidKompaun.size());
            }

        }


        LOG.info("CURRENT LIST SIZE OF UNPAID KOMPAUN : " + listUnpaidKompaun.size());
        if (listUnpaidKompaun.size() == 0) {
            LOG.info("-------------SEND TUGASAN TO NEXT STAGE WHEN ALL KOMPAUN HAD BEEN PAID-------------");
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
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return new RedirectResolution(BayaranKompaunActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }
        }

        if (listUnpaidKompaun.size() == 0) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                    if (stageId.equalsIgnoreCase("terima_byr_kompaun")) {
                        updateKeputusan();

                    }
                }

            }

        }

        if (listUnpaidKompaun.size() == 0) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                        addSimpleMessage("Pembayaran denda telah berjaya. Tiada denda yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_denda_tambahan")) {
                        addSimpleMessage("Pembayaran denda tambahan telah berjaya. Tiada denda tambahan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    if (stageId.equalsIgnoreCase("bayaran_pelupusan")) {
                        addSimpleMessage("Pembayaran muktamad pelupusan telah berjaya. Tiada bayaran pelupusan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_jaminan_rampasan")) {
                        addSimpleMessage("Pembayaran jaminan telah berjaya. Tiada bayaran jaminan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    } else {
                        addSimpleMessage("Pembayaran kompaun telah berjaya. Tiada kompaun yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    }
                } else {
                    addSimpleMessage("Pembayaran kompaun telah berjaya. Tiada kompaun yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                }
            } else {//else for NS
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("bayaran_denda")) {
                        addSimpleMessage("Pembayaran denda telah berjaya. Tiada denda yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_denda_tambahan")) {
                        addSimpleMessage("Pembayaran denda tambahan telah berjaya. Tiada denda tambahan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    }
                } else {
                    addSimpleMessage("Pembayaran kompaun telah berjaya. Tiada kompaun yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                }
            }

        } else { //else for unpaid denda/kompaun
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                        addSimpleMessage("Pembayaran denda telah berjaya. Terdapat " + listUnpaidKompaun.size() + " denda yang masih belum dibayar. Semua denda hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_denda_tambahan")) {
                        addSimpleMessage("Pembayaran denda tambahan telah berjaya. Terdapat " + listUnpaidKompaun.size() + " denda tambahan yang masih belum dibayar. Semua denda tambahan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                    if (stageId.equalsIgnoreCase("bayaran_pelupusan")) {
                        addSimpleMessage("Pembayaran pelupusan telah berjaya. Terdapat " + listUnpaidKompaun.size() + " bayaran pelupusan yang masih belum dibayar. Semua bayaran pelupusan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_jaminan_rampasan")) {
                        addSimpleMessage("Pembayaran jaminan telah berjaya. Terdapat " + listUnpaidKompaun.size() + " bayaran jaminan yang masih belum dibayar. Semua bayaran jaminan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    } else {
                        addSimpleMessage("Pembayaran kompaun telah berjaya. Terdapat " + listUnpaidKompaun.size() + " kompaun yang masih belum dibayar. Semua kompaun hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    }
                } else {
                    addSimpleMessage("Pembayaran kompaun telah berjaya. Terdapat " + listUnpaidKompaun.size() + " kompaun yang masih belum dibayar. Semua kompaun hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                }
            } else { //else for NS
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("bayaran_denda")) {
                        addSimpleMessage("Pembayaran denda telah berjaya. Terdapat " + listUnpaidKompaun.size() + " denda yang masih belum dibayar. Semua denda hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    } else if (stageId.equalsIgnoreCase("bayaran_denda_tambahan")) {
                        addSimpleMessage("Pembayaran denda tambahan telah berjaya. Terdapat " + listUnpaidKompaun.size() + " denda tambahan yang masih belum dibayar. Semua denda tambahan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    }
                } else {
                    addSimpleMessage("Pembayaran kompaun telah berjaya. Terdapat " + listUnpaidKompaun.size() + " kompaun yang masih belum dibayar. Semua kompaun hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/cetak_resit_bayaran.jsp");
    }

    public void updateKeputusan() {
        LOG.info("-------updateKeputusan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKeputusan(kodKeputusanDAO.findById("KS")); // KS = kes selesai
            enforceService.simpanPermohonan(permohonan);

        }
    }

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

    public Resolution kembali() {
        LOG.info("--------Kembali To Page Pembayaran Kompaun--------");
        return new JSP("penguatkuasaan/bayaran_kompaun.jsp").addParameter("tab", "true");
    }

    public static int getDaysBetweenDates(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        int days = (int) Math.floor(diff / MILISECONDS_PER_DAY);
        return Math.abs(days);
    }

    public void calculateDendaTambahan(List<Kompaun> senaraiKompaun) {
        LOG.info("----------- calculateDendaTambahan ----------- ");
        //To get tarikh terima and convert it to day format table from Notis (Kod : PDT - Notis Denda Tambahan)
        listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
        LOG.info("----------- listNotis ----------- : " + listNotis.size());
        for (int i = 0; i < listNotis.size(); i++) {
            if (listNotis.get(i).getKodNotis().getKod().equalsIgnoreCase("PDT")) { // PDT = Notis Denda Tambahan
                System.out.println("kod notis : " + listNotis.get(i).getKodNotis().getKod());
                tarikhTerima = listNotis.get(i).getTarikhTerima();
                jumlahHari = getDaysBetweenDates(tarikhTerima, date);
                System.out.println("tarikh terima : " + tarikhTerima);
                System.out.println("jumlah hari : " + jumlahHari);
            }
        }

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
        BayaranDepositNSActionBean.kodNegeri = kodNegeri;
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

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<Kompaun> getListUnpaidKompaun() {
        return listUnpaidKompaun;
    }

    public void setListUnpaidKompaun(List<Kompaun> listUnpaidKompaun) {
        this.listUnpaidKompaun = listUnpaidKompaun;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public String getWarnaModul() {
        return warnaModul;
    }

    public void setWarnaModul(String warnaModul) {
        this.warnaModul = warnaModul;
    }

    public Boolean getUnpaidFlag() {
        return UnpaidFlag;
    }

    public void setUnpaidFlag(Boolean UnpaidFlag) {
        this.UnpaidFlag = UnpaidFlag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PermohonanTuntutan> getListMohonTuntut() {
        return listMohonTuntut;
    }

    public void setListMohonTuntut(List<PermohonanTuntutan> listMohonTuntut) {
        this.listMohonTuntut = listMohonTuntut;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<PermohonanTuntutanBayar> getSenaraiMohonTuntutBayar() {
        return senaraiMohonTuntutBayar;
    }

    public void setSenaraiMohonTuntutBayar(List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar) {
        this.senaraiMohonTuntutBayar = senaraiMohonTuntutBayar;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public int getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        this.jumlahHari = jumlahHari;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Boolean getDendaTambahanFlag() {
        return dendaTambahanFlag;
    }

    public void setDendaTambahanFlag(Boolean dendaTambahanFlag) {
        this.dendaTambahanFlag = dendaTambahanFlag;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
