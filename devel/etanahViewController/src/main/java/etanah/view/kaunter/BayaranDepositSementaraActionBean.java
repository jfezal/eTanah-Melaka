/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import etanah.model.CaraBayaran;
import java.text.ParseException;
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
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKutipanDAO;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit;
import etanah.service.AkaunService;
import etanah.service.LaporanTanahService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.BasicTabActionBean;
import etanah.view.strata.CommonService;
import etanah.view.strata.InitiateTaskService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import etanah.dao.KodRujukanDAO;
import etanah.model.KodAkaun;
import etanah.model.KodKadarBayaran;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.PermohonanBangunan;
import etanah.sequence.GeneratorNoResit2;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import org.apache.commons.lang.ArrayUtils;

/*
 * @ Murali
 */
@HttpCache(allow = false)
@UrlBinding("/kaunter/strata/MLK/BayaranDeposit")
public class BayaranDepositSementaraActionBean extends BasicTabActionBean {

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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    KodKutipanDAO kodKutipDAO;
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private ModKutipService modKutip;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodBankDAO kodBankDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private CaraBayaranDAO caraBayaranDAO;
    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private AkaunService akaunService;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
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
    StrataPtService strataService;
    private List<DokumenKewanganBayaran> dkbList;
    private String resitNo;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private String kodNegeri;
    private String mod = "";
    BigDecimal total = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranDepositSementaraActionBean.class);
    private List<String> tarikhCek = new ArrayList<String>();
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
    KodTuntutDAO kodTuntutDAO;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos1;
    @Inject
    KodUrusanDAO kodurusanDAO;
    private String idHakmilik;
    @Inject
    InitiateTaskService initiateTask;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strataservice;
    private String taskId;
    private Boolean isBayaranVisible;
    private Boolean bayaran2Visible;
    private Boolean bayaran3Visible = Boolean.FALSE;
    private Permohonan permohonRBHS;
    private String userid;
    private String p14Amohon;
    private String PBBBmohon;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    private Boolean found = Boolean.FALSE;
    private int noPetakProv;
    private BigDecimal jumlahCajProv;
    private String idPermohonan;
    private Akaun akaun;
    private String[] kodursns = {"PBBS", "PBBD"};

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }
        return new JSP("kaunter/bayaran_deposit_Sementara.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getJabatan().getKod().equals("PBBS")) {
            if (total == null) {
                total = total; // set if total is null
            } else {
                total = total.add(total); //total from all amount
            }
        } else {
            senaraiMohonTuntutKos1 = strService.findMohonTuntutKosExcludeBayaran(idPermohonan, "BDST");
            PermohonanTuntutanKos deposit = strService.findMohonTuntutKosPBBSDeposit(permohonan.getIdPermohonan());
            if (deposit == null || deposit.getKuantiti() == null || deposit.getKuantiti() == 0) {
                found = Boolean.FALSE;
            } else {
                found = Boolean.TRUE;
            }
            senaraiMohonTuntutKos = strService.findMohonTuntutKosIncludeBayaran(idPermohonan, "BDST");
            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
                LOG.info("senaraiMohonTuntutBayar_strata size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
                if (senaraiMohonTuntutBayar.isEmpty()) {
                    BayaranValue list = new BayaranValue();
                    list.setIdKos(pTK.getIdKos());
                    list.setIdPermohonan(pTK.getPermohonan().getIdPermohonan());
                    list.setAmaun(pTK.getAmaunTuntutan());
                    list.setKodUrusan(pTK.getPermohonan().getKodUrusan().getKod());
                    list.setNamaUrusan(pTK.getItem());
                    list.setKuantiti(pTK.getKuantiti());
                    if (pTK != null && pTK.getKodTransaksi() != null) {
                        list.setKodTransaksi(pTK.getKodTransaksi().getKod());
                    }
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
            jumlahCaj = total;
        }
    }

    public Resolution simpanNoPetak() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit audit = peng.getInfoAudit();
        audit.setDimasukOleh(peng);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
        if (noPetakProv != 0) {
            PermohonanTuntutanKos deposit = strService.findMohonTuntutKosPBBSDeposit(permohonan.getIdPermohonan());
            if (deposit == null) {
                deposit = new PermohonanTuntutanKos();
                deposit.setPermohonan(permohonan);
                deposit.setCawangan(permohonan.getCawangan());
                KodTuntut ktt = kodTuntutDAO.findById("BDST");
                deposit.setKodTuntut(ktt);
                deposit.setItem(ktt.getNama());
                KodTransaksi kkt = kodTransaksiDAO.findById("40041");
                deposit.setKodTransaksi(kkt);
                deposit.setKuantiti(1);
                KodKadarBayaran kadarb = strService.getKodByKat(permohonan.getKodUrusan().getKod(), "12");
                jumlahCajProv = kadarb.getKadar().multiply(new BigDecimal(noPetakProv));
                deposit.setAmaunTuntutan(jumlahCajProv);
            } else {
                KodKadarBayaran kadarb = strService.getKodByKat(permohonan.getKodUrusan().getKod(), "12");
                jumlahCajProv = kadarb.getKadar().multiply(new BigDecimal(noPetakProv));
                deposit.setAmaunTuntutan(jumlahCajProv);
            }
            deposit.setInfoAudit(audit);
            strService.simpanBayaran(deposit);
            addSimpleMessage("Maklumat telah berjaya disimpan ");
        } else {
            addSimpleError("Sila masukkan jumlah petak blok sementara ");
        }
        return new RedirectResolution(BayaranDepositSementaraActionBean.class, "showForm").addParameter("idPermohonan", permohonan.getIdPermohonan());
    }

    public Resolution save() throws ParseException {
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
            //        resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
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
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
                listSahBayaran.add(mtk);
                LOG.info("START KEW_TRANS");
                Transaksi trans = new Transaksi();
                trans.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                trans.setAkaunDebit(akTerima);
                trans.setAmaun(mtk.getAmaun());
                trans.setUntukTahun(year);
                trans.setTahunKewangan(year);
                trans.setPermohonan(mtk.getPermohonan());
                trans.setDokumenKewangan(dk);
                trans.setPerihal(mtk.getNamaUrusan());
                trans.setStatus(kodStatusDAO.findById('T'));
                trans.setKodUrusan(mohon.getKodUrusan().getKod());
                trans.setKuantiti(1);
                trans.setInfoAudit(ia);
                trans.setCawangan(caw);
                transaksiDAO.save(trans);
                LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());

                LOG.info("START PermohonanTuntutanBayaran");
                PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
                ptb.setDokumenKewangan(dk);
                ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                ptb.setTarikhBayar(now);
                ptb.setInfoAudit(ia);
                permohonanTuntutanBayarDAO.save(ptb);
                LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
            }

            //kew_Akaun        
            if (ArrayUtils.contains(kodursns, permohonan.getKodUrusan().getKod())) {
                List<PermohonanTuntutanKos> senaraiMohonTuntutKos = strService.findMohonTuntutKosIncludeBayaran(permohonan.getIdPermohonan(), "BDST");
                if (!senaraiMohonTuntutKos.isEmpty()) {
                    akaun = strService.findIDMohonDeposit(idPermohonan);
                    InfoAudit info = new InfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(now);
                    KodAkaun kodAkaun = new KodAkaun();
                    kodAkaun = strService.findKodAkaun("AD");
                    if (akaun != null) {
                        akaun.setBaki(jumlahCajProv.multiply(new BigDecimal(-1)));
                    } else {
                        akaun = new Akaun();
                        akaun.setNoAkaun(idPermohonan);
                        akaun.setKodAkaun(kodAkaun);
                        akaun.setCawangan(caw);
                        akaun.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
                        akaun.setPermohonan(permohonan);
                        jumlahCajProv = senaraiMohonTuntutKos.get(0).getAmaunTuntutan();
                        akaun.setBaki(jumlahCajProv.multiply(new BigDecimal(-1)));
                    }
                    akaun.setInfoAudit(info);
                    strService.simpanAkaun(akaun);
                }
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
            tx.commit();
            LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
            String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                        new String[]{"p_id_kew_dok"},
                        new String[]{resitNo},
                        path + path2, pengguna);
            } else {
                reportUtil.generateReport("HSLResitUrusanTanah.rdf",
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
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            addSimpleMessage(permohonan.getIdPermohonan() + " - Penghantaran Berjaya.");
            
            penyataPemungutService.savePenyataPemungut(dk);
        } catch (Exception e) {
            LOG.error(e);
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
        } finally {
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }
        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) throws ParseException {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        LOG.info("START CARABAYAR:-----------------------------");
        LOG.info("senaraiCaraBayaran.size() : " + senaraiCaraBayaran.size());
        for (int m = 0; m < senaraiCaraBayaran.size(); m++) {
            CaraBayaran cb = senaraiCaraBayaran.get(m);
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                if ((crByr.getKod().equals("CB")) || (crByr.getKod().equals("CC")) || (crByr.getKod().equals("CT"))
                        || (crByr.getKod().equals("CL")) || (crByr.getKod().equals("IB")) || (crByr.getKod().equals("BC"))) {
                    Date d = sdf.parse(tarikhCek.get(m));
                    cb.setTarikhCek(d);
                }
                if (cb.getBank() != null) {
                    KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                    cb.setBank(bank);
                }
                if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
                    cb.setBank(kodBankDAO.findById("PMB"));
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                cb.setKodCaraBayaran(crByr);
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
        LOG.info("adkb : " + adkb.size());
        dk.setSenaraiBayaran(adkb);
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

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public List<String> getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(List<String> tarikhCek) {
        this.tarikhCek = tarikhCek;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsBayaranVisible() {
        return isBayaranVisible;
    }

    public void setIsBayaranVisible(Boolean isBayaranVisible) {
        this.isBayaranVisible = isBayaranVisible;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Boolean getBayaran2Visible() {
        return bayaran2Visible;
    }

    public Boolean getBayaran3Visible() {
        return bayaran3Visible;
    }

    public void setBayaran3Visible(Boolean bayaran3Visible) {
        this.bayaran3Visible = bayaran3Visible;
    }

    public void setBayaran2Visible(Boolean bayaran2Visible) {
        this.bayaran2Visible = bayaran2Visible;
    }

    public Permohonan getPermohonRBHS() {
        return permohonRBHS;
    }

    public void setPermohonRBHS(Permohonan permohonRBHS) {
        this.permohonRBHS = permohonRBHS;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getP14Amohon() {
        return p14Amohon;
    }

    public void setP14Amohon(String p14Amohon) {
        this.p14Amohon = p14Amohon;
    }

    public String getPBBBmohon() {
        return PBBBmohon;
    }

    public void setPBBBmohon(String PBBBmohon) {
        this.PBBBmohon = PBBBmohon;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public BigDecimal getJumlahCajProv() {
        return jumlahCajProv;
    }

    public void setJumlahCajProv(BigDecimal jumlahCajProv) {
        this.jumlahCajProv = jumlahCajProv;
    }

    public int getNoPetakProv() {
        return noPetakProv;
    }

    public void setNoPetakProv(int noPetakProv) {
        this.noPetakProv = noPetakProv;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
