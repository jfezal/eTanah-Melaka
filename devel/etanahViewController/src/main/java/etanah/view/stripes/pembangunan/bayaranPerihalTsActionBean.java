/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

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
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.BasicTabActionBean;
import etanah.view.kaunter.BayaranValue;
import etanah.view.kaunter.PermohonanKaunter;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
 *
 * @author user
 *
 */
@HttpCache(allow = false)
@UrlBinding("/pembangunan/bayaranPerihalTukarSyarat")
public class bayaranPerihalTsActionBean extends BasicTabActionBean {

    @Inject
    PembangunanService devServ;
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
    PenggunaDAO penggunaDAO;
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
    private AkaunDAO akaunDAO;
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
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;
    private List<DokumenKewanganBayaran> dkbList;
    private String resitNo;
    private String mod = "";
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private static String kodNegeri;
    BigDecimal total = null;
    BigDecimal totalpremiumbaru = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(bayaranPerihalTsActionBean.class);
    private String idPermohonan;
    private List<String> tarikhCek = new ArrayList<String>();
    List<BayaranValue> listBayaran = new ArrayList();
    List<BayaranValue> listSahBayaran = new ArrayList();
    IWorkflowContext ctxW = null;
    Pengguna pguna = null;
    Permohonan permohonan;
    PermohonanAsal permohonanAsal;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    private PermohonanUkur permohonanUkur;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    FasaPermohonan fasaPermohonan = null;
    BigDecimal totalpremiumbayar = null;
    PermohonanTuntutanKos ptk1 = null;

    @DefaultHandler
    public Resolution showForm() {
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
        return new JSP("pembangunan/kaunter/bayaranPerihalTsMelaka.jsp").addParameter("tab", "true");
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

//        SHAZWAN KOMEN -> JIKA ADA PERUBAHAN SPECIFIK, FILTER BY MODUL, SEBAB PAGE NI KONGSI SEMUA MODUL, AKAN BERLAKU ERROR PADA MODUL LAIN NNTI..
//        List sizeFasaPermohonan1 = devServ.findFasaPermohonanSize(permohonan.getIdPermohonan());
//        ptk1 = devServ.findMohonTuntutKos(idPermohonan);

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
//            senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            // Added code to exclude sewaBaru

            senaraiMohonTuntutKos = strService.findMohonTuntutKosExclude(idPermohonan, "DEV10");

            LOG.info("senaraiMohonTuntutKos : " + senaraiMohonTuntutKos.size());
            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
                if(!pTK.getKodTuntut().getKod().equals("DEV10")&&!pTK.getKodTuntut().getKod().equals("DEV11")){
                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
                LOG.info("senaraiMohonTuntutBayar size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
                if (senaraiMohonTuntutBayar.isEmpty()) {
                    BayaranValue list = new BayaranValue();
                    if(pTK.getAmaunTuntutan().compareTo(BigDecimal.ZERO)>0){
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
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            LOG.info("listBayaran : " + listBayaran.size());
            LOG.info("total : " + total.setScale(2));
            jumlahCaj = total.setScale(2);



            if (permohonan.getKodUrusan().getKod().equals("TSKKT") || permohonan.getKodUrusan().getKod().equals("TSKSN")
                    || permohonan.getKodUrusan().getKod().equals("TSBSN") || permohonan.getKodUrusan().getKod().equals("TSPSN")) {

                //VEE CAKAP SEMINGGU DARI TARIKH SAIN BARU KIRA (5/8/16)
                getContext().getRequest().setAttribute("buttonTS", Boolean.TRUE);
                if (checkDatePostNumOfDays(permohonan, 97, "NTS7G")) {
                    getContext().getRequest().setAttribute("check90hari", Boolean.TRUE);
                }
                if (checkDatePostNumOfDays(permohonan, 83, "NTS7G")) {
                    getContext().getRequest().setAttribute("check76hari", Boolean.TRUE);
                }
                if (doneSuratPeringatan(permohonan)) {
                    getContext().getRequest().setAttribute("checkSuratPeringatan", Boolean.TRUE);
                }

            }
        }
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
        LOG.debug("Total yg dibayar adalah : " + total);

        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
            LOG.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        mod = modKutip.loadPenyerahFromSession(ctx);
        //set KEW_DOKUMEN
        resitNo = noResitGenerator.getAndLockSerialNo(pguna);
        LOG.info("RESIT NO:" + resitNo);
        DokumenKewangan dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(resitNo);
        // set idResit to pageContext
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
        dk.setAmaunBayaran(total);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setInfoAudit(ia);
        dk.setTarikhTransaksi(now);
        dk.setCawangan(caw);
        dk.setAkaun(akTerima);
        if (mod != null && mod.length() > 0) {
            dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        }
        dk.setIdKaunter(pengguna.getIdKaunter());
        saveCaraBayaran(caw, dk, ia);
        dokumenKewanganDAO.save(dk);
//set KEW_TRANS
//        String arrSplit[], aSplit;
        LOG.info("listBayaran : " + listBayaran.size());
        for (BayaranValue mtk : listBayaran) {
//            for (int i = 0; i < listBayaran.size(); i++) {
//                aSplit = getContext().getRequest().getParameter("idKos" + (i + 1));
//                if (aSplit != null) {
//                    arrSplit = aSplit.split(",");
//                    if (Long.parseLong(arrSplit[0]) == mtk.getIdKos()) {
            listSahBayaran.add(mtk);

            Transaksi trans = new Transaksi();
            if (mtk.getTuntutKos().getKodTransaksi().getTransaksiAmanah() == 'Y') {
                Akaun akaun = new Akaun();
                BigDecimal bg = new BigDecimal(0);
                akaun = akaunDAO.findById(mtk.getIdPermohonan());
                if (akaun != null) {
                    akaun.setBaki(akaun.getBaki().subtract(mtk.getAmaun()));
                } else {
                    akaun = new Akaun();
                    akaun.setBaki(bg.subtract(mtk.getAmaun()));
                }

                akaun.setNoAkaun(mtk.getIdPermohonan());
                akaun.setCawangan(mtk.getPermohonan().getCawangan());
                akaun.setKodAkaun(kodAkaunDAO.findById("AD"));
                akaun.setPermohonan(mtk.getPermohonan());
                akaun.setInfoAudit(ia);
                akaunDAO.save(akaun);
                trans.setAkaunKredit(akaun);
            }
            LOG.info("START KEW_TRANS");
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
            ptb.setPermohonan(mtk.getPermohonan());
            permohonanTuntutanBayarDAO.save(ptb);
            LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
        }
//                }
//            }
//        }

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
        penyataPemungutService.savePenyataPemungut(dk);
        tx.commit();

        noResitGenerator.commitAndUnlockSerialNo(pguna);

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

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }


        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        if (fasaPermohonan != null) {
            InfoAudit ia1 = new InfoAudit();
            ia1.setDimasukOleh(pengguna);
            ia1.setTarikhKemaskini(now);
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia1);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        } else {
            InfoAudit ia2 = new InfoAudit();
            ia2.setDimasukOleh(pengguna);
            ia2.setTarikhMasuk(now);
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia2);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        }


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

            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
            return new RedirectResolution(bayaranPerihalTsActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dk};
        dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
        String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/kaunter/cetak_resit_bayaran_ts.jsp");
    }

    public Resolution cetakSuratPeringatan() throws ParseException {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Date now = new Date();
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }


        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        if (fasaPermohonan != null) {
            InfoAudit ia1 = new InfoAudit();
            ia1.setDimasukOleh(pengguna);
            ia1.setTarikhKemaskini(now);
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia1);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        } else {
            InfoAudit ia2 = new InfoAudit();
            ia2.setDimasukOleh(pengguna);
            ia2.setTarikhMasuk(now);
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia2);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        }
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

                WorkFlowService.updateTaskOutcome(taskID, ctxW, "LB");
            }

        } catch (Exception ex) {
            LOG.error(ex);


            return new RedirectResolution(bayaranPerihalTsActionBean.class).addParameter("error", sb.toString() + " : Tugasan Tidak Berjaya di hantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem.");
        }

        //addSimpleMessage("Permohonan ini telah tamat tempoh 3 bulan dari waktu permohonan");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/kaunter/permohonan_status.jsp");
    }
    
    public Resolution arahanBatal() throws ParseException {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Date now = new Date();
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }


        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        if (fasaPermohonan != null) {
            InfoAudit ia1 = new InfoAudit();
            ia1.setDimasukOleh(pengguna);
            ia1.setTarikhKemaskini(now);
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia1);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        } else {
            InfoAudit ia2 = new InfoAudit();
            ia2.setDimasukOleh(pengguna);
            ia2.setTarikhMasuk(now);
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(ia2);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setTarikhHantar(new java.util.Date());
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        }
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

                WorkFlowService.updateTaskOutcome(taskID, ctxW, "ZY");
            }

        } catch (Exception ex) {
            LOG.error(ex);


            return new RedirectResolution(bayaranPerihalTsActionBean.class).addParameter("error", sb.toString() + " : Tugasan Tidak Berjaya di hantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem.");
        }

        //addSimpleMessage("Permohonan ini telah tamat tempoh 3 bulan dari waktu permohonan");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/kaunter/permohonan_status.jsp");
    }

    private boolean checkDatePostNumOfDays(Permohonan permohonan, int numofdays, String kodDokumen) {
        boolean ok = false;

        // Date Expired        
        List<PermohonanTandatanganDokumen> mohondoktt = devServ.findMohonDokTTByIdMohonAndKodDokumen(permohonan.getIdPermohonan());
        if(mohondoktt.size() > 0){
            for(int i=0;i<mohondoktt.size();i++){
            PermohonanTandatanganDokumen bean = mohondoktt.get(i);
                if(bean.getKodDokumen().getKod().equals(kodDokumen)){
                        if(bean.getTrhTt()!=null){
                            Date dateMohon = bean.getTrhTt();
                            Calendar calendar = new GregorianCalendar();
                            calendar.setTime(dateMohon);
                            calendar.add(Calendar.DATE, numofdays);
                            Date expiredDate = new Date();
                            expiredDate = calendar.getTime();

                            //Current Date
                            Date dateCurrent = new Date();

                            LOG.debug("Date Mohon : " + dateMohon);
                            LOG.debug("Expired Date : " + expiredDate);
                            LOG.debug("Current Date : " + dateCurrent);

                            if (dateCurrent.after(expiredDate)) {
                                ok = true;
                                LOG.debug("Date = true");
                            }
                            break;
                        }
                        
                }
            }
            
        }
        
        return ok;
    }

    private boolean doneSuratPeringatan(Permohonan permohonan) {
        boolean ok = false;
        if (devServ.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "cetaksuratperingatan") != null) {
            ok = true;
        }
        return ok;
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

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        bayaranPerihalTsActionBean.kodNegeri = kodNegeri;
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

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
