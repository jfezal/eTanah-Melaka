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
import etanah.dao.AkaunDAO;
import etanah.dao.AkuanTerimaDepositDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
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
import etanah.dao.KodStatusAkaunDAO;
import etanah.model.Akaun;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAkaun;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTransaksi;
import etanah.model.Kompaun;
import etanah.model.Notis;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoAkaun;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import etanah.service.PengambilanService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PengambilanDepositService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.BasicTabActionBean;
import etanah.view.penguatkuasaan.BayaranAnsuranValue;
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
/**
 *
 * @author Admin
 */
@HttpCache(allow = false)
@UrlBinding("/pengambilan/BayaranKompaun")

public class BayaranKompaunPengambilanActionBean extends BasicTabActionBean {
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
    private KodStatusAkaunDAO kodStatusAkaunDAO;
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
    KodAkaunDAO kodAkaunDAO;
    @Inject
    GeneratorNoAkaun generatorNoAkaun;
    @Inject
    PengambilanDepositService pengambilanService;
    @Inject
    PengambilanService pengambilanDService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
    private String resitNo;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private static String kodNegeri;
    BigDecimal total = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranKompaunPengambilanActionBean.class);
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
    @Inject
    private AkuanTerimaDepositDAO akuanTerimaDepositDAO;
    
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;
    private Long idDokumen;
    private String mod = "";
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> listUnpaidKompaun;
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
    Pemohon pmohon;
//    private String kod="79501";
    private String kodT;
    private KodTransaksi kod;

    public KodTransaksi getKod() {
        return kod;
    }

    public void setKod(KodTransaksi kod) {
        this.kod = kod;
    }
    List<Akaun> akaunDeposit;
    Akaun akAmanah;
    private AkuanTerimaDeposit akTerimaDep;
    private PermohonanPihak pp;
    private List<DokumenKewanganBayaran> dkbList;




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
        return new JSP("kaunter/bayaran_perihal_pengambilan.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
        return new RedirectResolution(BayaranKompaunPengambilanActionBean.class);

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
            stageId = "01Kemasukan";
        }
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("IDPERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        kod=permohonan.getKodUrusan().getKodTransaksi();
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
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("BMAHK")) {
                    if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                        senaraiMohonTuntutKos = enforceService.findMohonTuntutKos(idPermohonan, "DD");
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Denda (DD) ----------- : " + senaraiMohonTuntutKos.size());
                    } else if (stageId.equalsIgnoreCase("bayaran_kos_remedi")) {
                        senaraiMohonTuntutKos = enforceService.findMohonTuntutKos(idPermohonan, "BR");
                        if (!senaraiMohonTuntutKos.isEmpty()) {
                            amaunNeedToPay = senaraiMohonTuntutKos.get(0).getAmaunTuntutan();
                            amaunNeedToPay = amaunNeedToPay.add(BigDecimal.valueOf(0.00));
                            amaunNeedToPay = amaunNeedToPay.setScale(2);
                            System.out.println(":::::::::::::::::::::::::amaun kena bayar : " + amaunNeedToPay);
                        }
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran Remedi (BR) ----------- : " + senaraiMohonTuntutKos.size());
                    } else {
                        senaraiMohonTuntutKos = enforceService.findMohonTuntutKos(idPermohonan, "DT");
                        LOG.info("----------- senaraiMohonTuntutKos for bayaran denda tambahan (DT) -----------: " + senaraiMohonTuntutKos.size());
                    }
                } 
            }// else for NS
            
            
            
            else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")|| permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                    if (stageId.equalsIgnoreCase("bayaran_denda")) {
                        senaraiMohonTuntutKos = pengambilanService.findByIDMohonTuntut(idPermohonan, "ACQD75");
                        LOG.info("----------- senaraiMohonTuntutKos for Deposit 75 (ACQD75) ----------- : " + senaraiMohonTuntutKos.size());
                    } else {
                        senaraiMohonTuntutKos = pengambilanService.findByIDMohonTuntut(idPermohonan, "ACQD50");
                        LOG.info("----------- senaraiMohonTuntutKos for Deposit 50 (ACQD50) -----------: " + senaraiMohonTuntutKos.size());
                    }
                }

            }
           

            
//            System.out.println("jumlah hari (rehydrate) : " + jumlahHari);
//            System.out.println("denda tambahan flag : " + dendaTambahanFlag);
//
//            if (jumlahHari == 0) {
//                jumlahHari = 1;
//            }
//            BigDecimal hari = new BigDecimal(jumlahHari);
//
//            for (int k = 0; k < senaraiKompaun.size(); k++) {
//                Kompaun kompaun = senaraiKompaun.get(k);
//                if (kompaun != null) {
//                    if (kompaun.getRayuan() != null) {
//                        if (kompaun.getRayuan() == 'Y') {
//                            statusAnsuranFlag = true;
//                        }
//                    }
//                }
//            }
//
//            System.out.println("statusAnsuranFlag : " + statusAnsuranFlag);

            
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
                        LOG.info("--------info list bayaran value-------");
                        LOG.info("info list-id mohon :" + list.getIdPermohonan());
                        LOG.info("info list-amaun : " + list.getAmaun());
                        LOG.info("info list-kod urusan : " + list.getKodUrusan());
                        LOG.info("info list-nama urusan : " + list.getNamaUrusan());
                        LOG.info("info list-kod transaksi : " + list.getKodTransaksi());
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

        if (permohonan.getKodUrusan().getJabatan().getKod().equals("9")) {
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
            LOG.info("kod negeri : " + ctx.getKodNegeri());
            LOG.info("cawangan : " + caw);
            LOG.info("pengguna : " + pengguna);
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
            //enforceService.simpanDokumenKewangan(dk);
            dokumenKewanganDAO.save(dk);

            //set KEW_TRANS/
            pmohon=pengambilanService.findById(idPermohonan);
            Long idPihak=pmohon.getPihak().getIdPihak();
            akaunDeposit=pengambilanService.findByIDMohonIDPihak(idPermohonan,Long.toString(idPihak));
            pp = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pmohon.getPihak().getIdPihak());
            if (pp==null)
            {
                pp=new PermohonanPihak();
                pp.setPihak(pmohon.getPihak());
                pp.setPermohonan(permohonan);
                pp.setCawangan(caw);
                pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("TER"));
                pp.setInfoAudit(ia);
                notisPenerimaanService.simpanMohonPemohon(pp);
            }
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
                             if(akaunDeposit.size()==0){
                            Akaun akAmanah = new Akaun();
                            KodCawangan cawAkaunDeposit = permohonan.getCawangan();
                            KodAkaun akaunDeposit=new KodAkaun();
                            akaunDeposit.setKod("DP");
                            akAmanah.setCawangan(cawAkaunDeposit);
                            akAmanah.setNoAkaun(idPermohonan);
                            akAmanah.setKodAkaun(akaunDeposit);
                            akAmanah.setPermohonan(mohon);
                            akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
                            akAmanah.setPemegang(pmohon.getPihak());
                            akAmanah.setBilCetakPenyata(0);
                            akAmanah.setBaki(new BigDecimal(0)); // kredit
                            akAmanah.setInfoAudit(ia);
                            akaunDAO.save(akAmanah);

                            LOG.info("END KEW_AKAUN: " + akAmanah.getNoAkaun());
                            }

                            LOG.info("START KEW_TRANS");
                            Transaksi trans = new Transaksi();
                            Akaun akKreditDeposit = pengambilanService.getAkaunDeposit(idPermohonan, Long.toString(idPihak));
                            trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                            trans.setAkaunDebit(akTerima);
                            trans.setAkaunKredit(akKreditDeposit);
                            trans.setAmaun(mtk.getAmaun());
                            trans.setUntukTahun(year);
                            trans.setPermohonan(mtk.getPermohonan());
                            trans.setDokumenKewangan(dk);
                            trans.setPerihal(mtk.getNamaUrusan());
                            trans.setStatus(kodStatusDAO.findById('T'));
                            trans.setKuantiti(1);
                            trans.setInfoAudit(ia);
                            trans.setCawangan(caw);
                            //enforceService.simpanTransaksi(trans);
                            transaksiDAO.save(trans);
                            LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());

                              //save baki inside Kew_Amaun
                            LOG.info("START KEW_AKAUN");
                            if(akKreditDeposit!=null)
                            {  BigDecimal baki =akKreditDeposit.getBaki();
                               BigDecimal amaun=trans.getAmaun();
                               BigDecimal balanceDeposit=baki.subtract(amaun);
                               akKreditDeposit.setBaki(balanceDeposit);
                               akKreditDeposit.setInfoAudit(ia);
                               akaunDAO.save(akKreditDeposit);

                            }
                              LOG.info("END KEW_AKAUN: " + akKreditDeposit.getBaki());

                            LOG.info("START PermohonanTuntutanBayaran");
                            PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                            ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
                            ptb.setDokumenKewangan(dk);
    //                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                            ptb.setAmaun(mtk.getAmaun());
                            ptb.setTarikhBayar(now);
                            ptb.setInfoAudit(ia);
                            permohonanTuntutanBayarDAO.save(ptb);
                            //enforceService.simpanPermohonanTuntutanBayaran(ptb);
                            LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());

                            LOG.info("START AKUAN_DEPOSIT");
                            AkuanTerimaDeposit atd = new AkuanTerimaDeposit();
                            atd.setPermohonan(mtk.getPermohonan());
                            atd.setCaraBayaran(dk.getSenaraiBayaran().get(0).getCaraBayaran().getKodCaraBayaran());
    //                        atd.setNoResit(ptb.getDokumenKewangan().getIdDokumenKewangan());
                            atd.setTarikhResit(now);
                            atd.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                            atd.setInfoAudit(ia);
                            atd.setPermohonanPihak(pp);
                            akuanTerimaDepositDAO.save(atd);

                            LOG.info("END AKUAN_DEPOSIT: ");
                        }
                    }
                }
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
            resit.setTajuk("Resit Bayaran Deposit");
            resit = dokumenDAO.saveOrUpdate(resit);
            tx.commit();
            LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            idDokumen = resit.getIdDokumen();
            LOG.info("ID DOKUMEN: " + idDokumen);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
            String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path + path2, pengguna);
            } else {
                reportUtil.generateReport("ACQResitUrusanTanah.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path + path2, pengguna);
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


            StringBuilder sb = new StringBuilder();
            try {
                List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
                for (Map<String, String> map : list) {
                    String taskID = map.get("taskId");
                    String idMOhon = map.get("idPermohonan");
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(idMOhon);
                    LOG.info("TaskID ::" + taskID);
                    LOG.info("idPermohonan ::" + idMOhon);
                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
    //                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                }

            } catch (Exception ex) {
                LOG.error(ex);
                return new RedirectResolution(BayaranKompaunPengambilanActionBean.class).addParameter("error", sb.toString());
            }

            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";


            addSimpleMessage(permohonan.getIdPermohonan() +" - Penghantaran Berjaya.");
            
            //new requirement by hasil's team
            penyataPemungutService.savePenyataPemungut(dk);
        } catch (Exception e) {
            LOG.error(e);
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
        } finally {
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/cetak_resit_bayaran.jsp");
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
        return new JSP("kaunter/bayaran_perihal_pengambilan.jsp").addParameter("tab", "true");
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
        BayaranKompaunPengambilanActionBean.kodNegeri = kodNegeri;
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
    
    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

}
