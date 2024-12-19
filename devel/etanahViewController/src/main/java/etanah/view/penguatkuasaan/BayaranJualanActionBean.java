/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

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
import etanah.dao.KodAkaunDAO;
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
import etanah.dao.PihakDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Kompaun;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenguatkuasaanBarangJual;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoAkaun;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.BasicTabActionBean;
import etanah.view.stripes.bidangKuasaActionBean;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
 * Based on given idPermohonan/idPerserahan, suggest the next Urusan for the
 * Permohonan. The cases: 1 If the given Permohonan is NOT completed: 1.1 If the
 * Permohonan is at SPOC's senaraiTugasan, show the Workflow & required action
 * from user (e.g. Submit more documents, pay fees etc) 1.2 If not in SPOC's
 * senaraiTugasan, display the status 2 If Permohonan is completed, check the
 * next suggested Urusan for the Permohonan. E.g. after Consent's "Kelulusan
 * Pindahmilik", next is Registration's "PindahMilik Tanah". 3 If Permohonan is
 * completed but rejected, suggest Urusan for Rayuan.
 *
 * @author user
 *
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/bayaran_jualan")
public class BayaranJualanActionBean extends BasicTabActionBean {

    @Inject
    PihakDAO pihakDAO;
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
    private GeneratorNoResit noResitGenerator;
    @Inject
    private GeneratorNoResit2 noResitGenerator2;
    @Inject
    PenyataPemungutService pp;
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
    KodAkaunDAO kodAkaunDAO;
    @Inject
    GeneratorNoAkaun generatorNoAkaun;
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;
    private String resitNo;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private String kodNegeri;
    private String mod = "";
    BigDecimal total = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranJualanActionBean.class);
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
    private List<Kompaun> senaraiKompaun = new ArrayList<Kompaun>();
    private List<Kompaun> listUnpaidKompaun = new ArrayList<Kompaun>();
    private String warnaModul;
    private List<PermohonanTuntutan> listMohonTuntut = new ArrayList<PermohonanTuntutan>();
    private List<PermohonanTuntutanButiran> listMohonTuntutButiran = new ArrayList<PermohonanTuntutanButiran>();
    private Date date = new Date();
    private Boolean UnpaidFlag = false;
    private List<Notis> listNotis;
    private int jumlahHari;
    private Date tarikhTerima;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final long MILISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    BigDecimal totalPayment = BigDecimal.ZERO;
    private Boolean dendaTambahanFlag = false;
    private Boolean statusAnsuranFlag = false;
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private BigDecimal amaunBulanan;
    private static final int DEFAULT_MONTH = 6;
    private BigDecimal paidAmaun = BigDecimal.ZERO;
    private BigDecimal balanceAmaun = BigDecimal.ZERO;
    private BigDecimal amaunNeedToPay = BigDecimal.ZERO;
    List<BayaranAnsuranValue> listBayaranAnsuran = new ArrayList();
    private int bilPayment;
    private List<PermohonanTuntutan> listTarikhTuntut = new ArrayList<PermohonanTuntutan>();
    private List<HakmilikPihakBerkepentingan> hakMilikPihakBerkepentinganList;
    private String idPihakBayar;
    private List<PenguatkuasaanBarangJual> listUnpaidPembelian = new ArrayList<PenguatkuasaanBarangJual>();
    private List<PenguatkuasaanBarangJual> senaraiJualanBarang = new ArrayList<PenguatkuasaanBarangJual>();
    private ArrayList<String> senaraiPembeli = new ArrayList<String>();

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
        return new JSP("penguatkuasaan/bayaran_jualan.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
        return new RedirectResolution(BayaranJualanActionBean.class);

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
        } else {
            stageId = "bayaran_jualan";
        }
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("IDPERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");

        //kes kalu nk filter by modul
        if (permohonan.getKodUrusan().getJabatan().getKod().equals("")) {
            /**
             * kalau xpakai table mohon tuntut kos, set kat dlm ni. Set
             * jumlahCaj sekali!
             */
            if (total == null) {
                total = total; // set if total is null
            } else {
                total = total.add(total); //total from all amount
            }
        } else {

            //find senaraiMohonTuntutKos for kompaun/denda/denda tambahan/bayaran pelupusan based on negeri
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A"))) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        senaraiMohonTuntutKos = enforcementService.findMohonTuntutKosPembelian(idPermohonan);
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Jualan (BJ) ----------- : " + senaraiMohonTuntutKos.size());

                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        senaraiMohonTuntutKos = enforcementService.findMohonTuntutKosPembelian(idPermohonan);
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Jualan (BJ) ----------- : " + senaraiMohonTuntutKos.size());
                    }
                }
            }// else for NS




            //find unpaid kompaun/denda/denda tambahan/bayaran pelupusan based on negeri & senarai kompaun based on kod status terima

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A"))) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        //for bayaran jualan
                        listUnpaidPembelian = enforcementService.findUnpaidPembelian(idPermohonan);
                        LOG.info("size unpaid Bayaran Jualan  list : " + listUnpaidPembelian.size());
                        senaraiJualanBarang = enforcementService.findPembelianBarang(idPermohonan);
                        LOG.info("::::::: size senaraiJualanBarang : " + senaraiJualanBarang.size());
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        //for bayaran jualan
                        listUnpaidPembelian = enforcementService.findUnpaidPembelian(idPermohonan);
                        LOG.info("size unpaid Bayaran Jualan  list : " + listUnpaidPembelian.size());
                        senaraiJualanBarang = enforcementService.findPembelianBarang(idPermohonan);
                        LOG.info("size senaraiJualanBarang (BJ) : " + senaraiJualanBarang.size());
                    }
                }
            }// else for NS

            HashSet hsKod = new HashSet();


            for (int i = 0; i < senaraiJualanBarang.size(); i++) {
                PenguatkuasaanBarangJual k = senaraiJualanBarang.get(i);
                hsKod.add(String.valueOf(k.getPemohon().getIdPemohon()));
            }

            Iterator it = hsKod.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                senaraiPembeli.add(value);
            }

            LOG.info("senaraiPembeli  : " + senaraiPembeli.size());





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
                    if (pTK.getKuantiti() != null) {
                        list.setKuantiti(pTK.getKuantiti());
                    }
                    listBayaran.add(list);
                    LOG.info("--------info list bayaran value-------");
                    LOG.info("info list-id mohon :" + list.getIdPermohonan());
                    LOG.info("info list-amaun : " + list.getAmaun());
                    LOG.info("info list-kod urusan : " + list.getKodUrusan());
                    LOG.info("info list-nama urusan : " + list.getNamaUrusan());
                    LOG.info("info list-kuantiti : " + list.getKuantiti());
                    LOG.info("info list-tuntut kos : " + list.getTuntutKos().getIdKos());


                    if (total == null) {

                        total = pTK.getAmaunTuntutan();

                    } else {

                        total = total.add(pTK.getAmaunTuntutan());

                    }

                    PermohonanTuntutanButiran ptb = enforceService.findPermohonanTuntutanButiran(pTK.getIdKos());

                    if (ptb != null) {
                        listMohonTuntut = enforceService.findMohonTuntutbyIdTuntut(date, ptb.getPermohonanTuntutan().getIdTuntut());

                        if (!listMohonTuntut.isEmpty()) {
                            listTarikhTuntut.add(listMohonTuntut.get(0));
                        }

                        System.out.println("list mohon tuntut : " + listTarikhTuntut.size());
                    }
                }
            }




            if (listTarikhTuntut.size() != 0) {
                for (int i = 0; i < listTarikhTuntut.size(); i++) {
                    PermohonanTuntutanButiran ptb = enforceService.findTuntutanButiran(listTarikhTuntut.get(i).getIdTuntut());
                    if (ptb != null) {
                        System.out.println("id mohontuntutButiran : " + ptb.getIdTuntut());
                        if (ptb.getPermohonanTuntutanKos() != null) {
                            System.out.println("id ptk kat ptb : " + ptb.getPermohonanTuntutanKos().getIdKos());
                            ptb.setPermohonanTuntutanKos(ptb.getPermohonanTuntutanKos());
                        }
                        listMohonTuntutButiran.add(ptb);

                    }

                }
            }

            LOG.info("listBayaran : " + listBayaran.size());

            LOG.info("total : " + total);
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
            //resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
//            if ("05".equals(conf.getProperty("kodNegeri"))) {
//                resitNo = noResitGenerator2.getAndLockSerialNo(pengguna);
//            } else {
//                resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
//            }{
            resitNo = noResitGenerator2.getAndLockSerialNo(pengguna);
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
//            enforceService.simpanDokumenKewangan(dk);
            dokumenKewanganDAO.save(dk);

//            if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("~~~~~~~~~~~Save PenyataPemungutService~~~~~~~~~~");
            //new requirement by hasil's team
            DokumenKewangan dok = dokumenKewanganDAO.findById(resitNo);
            pp.savePenyataPemungut(dok);
//            }

            //set KEW_TRANS/
            String arrSplit[], aSplit;
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
                for (int i = 0; i < listBayaran.size(); i++) {
                    aSplit = getContext().getRequest().getParameter("idKos" + (i + 1));
                    LOG.info("value aSplit : " + aSplit);
                    if (aSplit != null) {
                        arrSplit = aSplit.split(",");
                        LOG.info("value after split : " + arrSplit[0] + " value mtk_idKos : " + mtk.getIdKos());
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
//                            enforceService.simpanTransaksi(trans);
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
//                            enforceService.simpanPermohonanTuntutanBayaran(ptb);
                            LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            noResitGenerator2.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
        } finally {
            noResitGenerator2.commitAndUnlockSerialNo(pengguna);
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
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425"))) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        //for bayaran pelupusan
                        resit.setTajuk("Resit Bayaran Jualan");
                        LOG.info("resit bayaran jualan(BJ)");
                    } else {
                        //for bayaran kompaun
                        resit.setTajuk("Resit Bayaran kompaun");
                        LOG.info("resit bayaran kompaun (sek425)");
                    }
                }
            }// else for NS


            resit = dokumenDAO.saveOrUpdate(resit);
//            enforceService.simpanDokumen(resit);
            tx.commit();
            //en
            LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            idDokumen = resit.getIdDokumen();
            LOG.info("ID DOKUMEN: " + idDokumen);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
            String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        //for bayaran jualan
                        LOG.info("::::: nama report ENFHSLResitJualan_MLK");
                        reportUtil.generateReport("ENFHSLResitJualan_MLK.rdf",
                                new String[]{"p_id_kew_dok"},
                                new String[]{resitNo},
                                path + path2, pengguna);
                    }
                }
            }
            LOG.info("NAMA FIZIKAL TO BE SET AT DOKUMEN : " + reportUtil.getDMSPath());
            resit.setNamaFizikal(reportUtil.getDMSPath());
            getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
            dokumenDAO.update(resit);
//            enforceService.simpanDokumen(resit);
            System.out.println("after update fizikal name : " + resit.getNamaFizikal());

            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(resit);
            kf.setFolder(folderDokumenDAO.findById(idFolder));
            kf.setInfoAudit(ia);
            kf.setCatatan("");
//            enforceService.simpanKandunganFolder(kf);
            kandunganFolderDAO.save(kf);

            for (int i = 0; i < senaraiJualanBarang.size(); i++) {
                LOG.info("Update table kompaun : update column resit -------size senarai jualan --------- : " + senaraiJualanBarang.size());
                PenguatkuasaanBarangJual p = senaraiJualanBarang.get(i);
                if (StringUtils.isBlank(p.getIdDokumenKewangan())) {
                    PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(p.getKos().getIdKos());
                    if (ptb != null) {
                        LOG.info("ID BARANG JUALAN : " + p.getIdBarangJual());
                        p.setIdDokumenKewangan(ptb.getDokumenKewangan().getIdDokumenKewangan());
                        p.setTarikhBayaran(ptb.getTarikhBayar());
                        enforcementService.simpanPembelianBarang(p);
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
        } else {
            stageId = "bayaran_jualan";
        }

        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(ia);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setTarikhHantar(new java.util.Date());
        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);


        //if all kompaun had been paid, then only go to next stage based on kod negeri. 04(Melaka) & 05 (NS)
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                    //for denda
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DD");
                    LOG.info("size unpaid denda list : " + listUnpaidKompaun.size());
                } else if (stageId.equalsIgnoreCase("bayaran_kos_remedi")) {
                    //for bayaran remedi
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "BR");
                    LOG.info("size unpaid remedi list : " + listUnpaidKompaun.size());
                } else {
                    //for denda tambahan
                    listUnpaidKompaun = enforcementService.findUnpaidKompaun(idPermohonan, "DT");
                    LOG.info("size unpaid denda tambahan list : " + listUnpaidKompaun.size());
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A"))) {
                if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                    //for bayaran jualan
                    listUnpaidPembelian = enforcementService.findUnpaidPembelian(idPermohonan);
                    LOG.info("size unpaid Bayaran Jualan  list : " + listUnpaidPembelian.size());
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                    //for bayaran jualan
                    listUnpaidPembelian = enforcementService.findUnpaidPembelian(idPermohonan);
                    LOG.info("size unpaid Bayaran Jualan  list : " + listUnpaidPembelian.size());
                }
            }
        }// else for NS



        LOG.info("CURRENT LIST SIZE OF UNPAID JUALAN : " + listUnpaidPembelian.size());
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                    if (listUnpaidPembelian.size() == 0) {
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
                            return new RedirectResolution(BayaranJualanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
                        }
                    }
                }


            }
        }



        if (listUnpaidPembelian.size() == 0) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        addSimpleMessage("Pembayaran bayaran jualan telah berjaya. Tiada bayaran jualan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        addSimpleMessage("Pembayaran bayaran jualan telah berjaya. Tiada bayaran jualan yang masih belum dibayar. Tugasan telah berjaya dihantar ke peringkat seterusnya.");
                    }
                }
            }

        } else { //else for unpaid denda/kompaun
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        addSimpleMessage("Pembayaran jualan telah berjaya. Terdapat " + listUnpaidPembelian.size() + " bayaran jualan yang masih belum dibayar. Semua bayaran jualan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("bayaran_jualan")) {
                        addSimpleMessage("Pembayaran jualan telah berjaya. Terdapat " + listUnpaidPembelian.size() + " bayaran jualan yang masih belum dibayar. Semua bayaran jualan hendaklah dibayar sebelum pergi ke peringkat seterusnya.");
                    }
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/cetak_resit_jualan.jsp");
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
        return new JSP("penguatkuasaan/bayaran_jualan.jsp").addParameter("tab", "true");
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
                if (listNotis.get(i).getTarikhTerima() != null) {
                    tarikhTerima = listNotis.get(i).getTarikhTerima();
                    jumlahHari = getDaysBetweenDates(tarikhTerima, date);
                }

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

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
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

    public List<PermohonanTuntutanButiran> getListMohonTuntutButiran() {
        return listMohonTuntutButiran;
    }

    public void setListMohonTuntutButiran(List<PermohonanTuntutanButiran> listMohonTuntutButiran) {
        this.listMohonTuntutButiran = listMohonTuntutButiran;
    }

    public Boolean getStatusAnsuranFlag() {
        return statusAnsuranFlag;
    }

    public void setStatusAnsuranFlag(Boolean statusAnsuranFlag) {
        this.statusAnsuranFlag = statusAnsuranFlag;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public BigDecimal getAmaunBulanan() {
        return amaunBulanan;
    }

    public void setAmaunBulanan(BigDecimal amaunBulanan) {
        this.amaunBulanan = amaunBulanan;
    }

    public BigDecimal getPaidAmaun() {
        return paidAmaun;
    }

    public void setPaidAmaun(BigDecimal paidAmaun) {
        this.paidAmaun = paidAmaun;
    }

    public BigDecimal getBalanceAmaun() {
        return balanceAmaun;
    }

    public void setBalanceAmaun(BigDecimal balanceAmaun) {
        this.balanceAmaun = balanceAmaun;
    }

    public BigDecimal getAmaunNeedToPay() {
        return amaunNeedToPay;
    }

    public void setAmaunNeedToPay(BigDecimal amaunNeedToPay) {
        this.amaunNeedToPay = amaunNeedToPay;
    }

    public List<BayaranAnsuranValue> getListBayaranAnsuran() {
        return listBayaranAnsuran;
    }

    public void setListBayaranAnsuran(List<BayaranAnsuranValue> listBayaranAnsuran) {
        this.listBayaranAnsuran = listBayaranAnsuran;
    }

    public int getBilPayment() {
        return bilPayment;
    }

    public void setBilPayment(int bilPayment) {
        this.bilPayment = bilPayment;
    }

    public List<PermohonanTuntutan> getListTarikhTuntut() {
        return listTarikhTuntut;
    }

    public void setListTarikhTuntut(List<PermohonanTuntutan> listTarikhTuntut) {
        this.listTarikhTuntut = listTarikhTuntut;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public List<HakmilikPihakBerkepentingan> getHakMilikPihakBerkepentinganList() {
        return hakMilikPihakBerkepentinganList;
    }

    public void setHakMilikPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> hakMilikPihakBerkepentinganList) {
        this.hakMilikPihakBerkepentinganList = hakMilikPihakBerkepentinganList;
    }

    public String getIdPihakBayar() {
        return idPihakBayar;
    }

    public void setIdPihakBayar(String idPihakBayar) {
        this.idPihakBayar = idPihakBayar;
    }

    public List<PenguatkuasaanBarangJual> getListUnpaidPembelian() {
        return listUnpaidPembelian;
    }

    public void setListUnpaidPembelian(List<PenguatkuasaanBarangJual> listUnpaidPembelian) {
        this.listUnpaidPembelian = listUnpaidPembelian;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public List<PenguatkuasaanBarangJual> getSenaraiJualanBarang() {
        return senaraiJualanBarang;
    }

    public void setSenaraiJualanBarang(List<PenguatkuasaanBarangJual> senaraiJualanBarang) {
        this.senaraiJualanBarang = senaraiJualanBarang;
    }

    public ArrayList<String> getSenaraiPembeli() {
        return senaraiPembeli;
    }

    public void setSenaraiPembeli(ArrayList<String> senaraiPembeli) {
        this.senaraiPembeli = senaraiPembeli;
    }
}
