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
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
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
import etanah.dao.PermohonanAsalDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.LaporanTanahService;
import etanah.service.PembangunanService;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PengambilanDepositService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.BasicTabActionBean;
import etanah.view.pembangunan.validator.PindaanN9Validator;
import etanah.view.pengambilan.validator.pelukispelanValidator;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.validator.GenerateIdPerserahanWorkflow;
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
@UrlBinding("/kaunter/BayaranPerihal")
public class BayaranPerihalActionBean extends BasicTabActionBean {
@Inject
SBMSIntegrationFlowService sBMSIntegrationFlowService;
    @Inject
    PembangunanService devServ;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PengambilanDepositService pengambilanDepositService;
    @Inject
    LelongService lelongService;
    @Inject
    StrataPtService strService;
    @Inject
    ConsentPtdService consService;
    @Inject
    PelupusanService lupusservice;
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
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    private List<DokumenKewanganBayaran> dkbList;
    private String resitNo;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj;
    private static String kodNegeri;
    BigDecimal total = null;
    BigDecimal totalpremiumbaru = null;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    private static final Logger LOG = Logger.getLogger(BayaranPerihalActionBean.class);
    private String idPermohonan;
    private String mod = "";
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
    FasaPermohonan fasaPermohonan = new FasaPermohonan();
    BigDecimal totalpremiumbayar = null;
    PermohonanTuntutanKos ptk1 = null;
    BigDecimal cajPengecualianUkur = null;
    BigDecimal cajPengecualianSem = null;
    boolean tambahan = Boolean.FALSE;
    boolean statusRayuanPTD = Boolean.FALSE;
    boolean statusRayuanPTG = Boolean.FALSE;
    private String tarikhAkhirBayaranRayuan;
    private String kodP = "58007";

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
        return new JSP("kaunter/bayaran_perihal.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPengambilan() {
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
        return new JSP("kaunter/bayaran_perihal_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPelupusan() {
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
        tambahan = Boolean.FALSE;
        return new JSP("kaunter/bayaran_perihal_pelupusan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPelupusanBayaranTambahan() {
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
        tambahan = Boolean.TRUE;
        return new JSP("kaunter/bayaran_perihal_pelupusan.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("Kembali")
    @DontValidate
    public Resolution Kembali() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        return new RedirectResolution(PermohonanKaunter.class);

    }

    public Resolution back() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("ID PERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<PermohonanTuntutanKos> mohonTuntutKosList = consService.findSenaraiMohonTuntutKosByKodTuntut(permohonan.getIdPermohonan(), "CON04");

        if (!mohonTuntutKosList.isEmpty()) {
            consService.deleteMohonTuntutKos(mohonTuntutKosList.get(0));
        }

        rehydrate();

        return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran_perihal.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!rayuanBayaran", "!back"})
//    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctxW = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("ID PERMOHONAN: " + idPermohonan);
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
            if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RPS") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
                LOG.info("Permohonan Asal : " + permohonan.getPermohonanSebelum().getIdPermohonan());
                senaraiMohonTuntutKos = strService.findMohonTuntutKosExclude(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV10");
            } else if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {//FOR URUSAN MMKN MELAKA CONSENT

                if (permohonan.getKeputusan().getKod().equals("T2")) {
                    senaraiMohonTuntutKos = consService.findSenaraiMohonTuntutKosByCatatan(idPermohonan, "RAYUAN");
                } else {
                    senaraiMohonTuntutKos = consService.findSenaraiMohonTuntutKos(idPermohonan);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PPJP")) {//FOR LELONG
                senaraiMohonTuntutKos = lelongService.listPermohonanTuntutanKos2(idPermohonan);
                LOG.info("senaraiMohonTuntutKosLelong : " + senaraiMohonTuntutKos.size());
            } else if (permohonan.getKodUrusan().getKod().equals("SUBMC")) {//FOR LELONG
                senaraiMohonTuntutKos = lelongService.listPermohonanTuntutanSTR(idPermohonan);
                LOG.info("senaraiMohonTuntutKosLelong : " + senaraiMohonTuntutKos.size());
            } else if (permohonan.getKodUrusan().getKod().equals("831B") || permohonan.getKodUrusan().getKod().equals("831C")) {//FOR PENGAMBILAN
                fasaPermohonan = pengambilanService.findFasaPermohonanDepositKedua(permohonan.getIdPermohonan());
                if (fasaPermohonan == null) { // terima deposit pertama
                    senaraiMohonTuntutKos = pengambilanDepositService.findByIDMohonTuntut(idPermohonan, kodP);
//                    senaraiMohonTuntutKos = pengambilanService.listPermohonanTuntutanKosPertama(idPermohonan);
                } else {
                    senaraiMohonTuntutKos = pengambilanService.listPermohonanTuntutanKosKedua(idPermohonan);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PBMMK") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                senaraiMohonTuntutKos = strService.findMohonTuntutKosDepositlist(idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
                senaraiMohonTuntutKos = lupusservice.findListMohonTuntutKosByIdPermohonan(idPermohonan);
            } else {
                senaraiMohonTuntutKos = strService.findMohonTuntutKosExclude(idPermohonan, "DEV10");
            }

            //Added to integrate PMBT with RAYL & RLPTG
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    LOG.info("::find id RAYL or RLPTG : ");
                    List<Permohonan> senaraiPermohonanPBMT = lupusservice.findListPermohonanByIdPermohonanSebelum(idPermohonan);
                    if (!senaraiPermohonanPBMT.isEmpty()) {
                        for (Permohonan p : senaraiPermohonanPBMT) {
                            if (p.getKodUrusan().getKod().equalsIgnoreCase("RAYL")) {
                                List<Notis> listNotis = lupusservice.getListNotis2(idPermohonan, "N5A");
                                Notis n = new Notis();
                                if (!listNotis.isEmpty()) {
                                    n = listNotis.get(0);
                                }
                                if (n != null) {
                                    if (n.getTarikhTerima() != null && n.getBilangan().equalsIgnoreCase("1")) {
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(n.getTarikhTerima());
                                        cal.add(Calendar.MONTH, n.getTempohBulan());

                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                        tarikhAkhirBayaranRayuan = dateFormat.format(cal.getTime());
                                        statusRayuanPTD = true;
                                    }
                                }

                                List<Permohonan> senaraiPermohonanRLPTG = lupusservice.findListPermohonanByIdPermohonanSebelum(p.getIdPermohonan());

                                if (!senaraiPermohonanRLPTG.isEmpty()) {
                                    for (Permohonan mohon : senaraiPermohonanRLPTG) {
                                        if (mohon.getKodUrusan().getKod().equalsIgnoreCase("RLPTG")) {
                                            if (n != null) {
                                                if (n.getTarikhTerima() != null && n.getBilangan().equalsIgnoreCase("2")) {
                                                    Calendar cal = Calendar.getInstance();
                                                    cal.setTime(n.getTarikhTerima());
                                                    cal.add(Calendar.MONTH, n.getTempohBulan());

                                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                                    tarikhAkhirBayaranRayuan = dateFormat.format(cal.getTime());
                                                    statusRayuanPTG = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //find RLPTG
                    LOG.info("::status id RAYL : " + statusRayuanPTD);
                    LOG.info("::status id RLPTG : " + statusRayuanPTG);
                    LOG.info("::tarikh akhir bayaran : " + tarikhAkhirBayaranRayuan);
                }
            }
            LOG.info("senaraiMohonTuntutKos : " + senaraiMohonTuntutKos.size());
            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);
            for (PermohonanTuntutanKos pTK : senaraiMohonTuntutKos) {
                senaraiMohonTuntutBayar = strService.findMohonTuntutBayar(pTK.getIdKos());
                LOG.info("senaraiMohonTuntutBayar size : " + senaraiMohonTuntutBayar.size() + " IDKOS: " + pTK.getIdKos());
                if (senaraiMohonTuntutBayar.isEmpty()) {
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
                } else if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RPS") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
                    BayaranValue list = new BayaranValue();
                    list.setIdKos(pTK.getIdKos());
                    list.setIdPermohonan(pTK.getPermohonan().getIdPermohonan());
                    /*
                         if (sizeFasaPermohonan1.size() > 1 && !pTK.getKodTuntut().getKod().equals("DEV04")) {
                         list.setAmaun(new BigDecimal(0));
                         } else {
                     */
                    list.setAmaun(pTK.getAmaunTuntutan());
                    //}
                    list.setKodUrusan(pTK.getPermohonan().getKodUrusan().getKod());
                    list.setNamaUrusan(pTK.getItem());
                    list.setKodTransaksi(pTK.getKodTransaksi().getKod());
                    list.setPermohonan(permohonan);
                    list.setTuntutKos(pTK);
                    listBayaran.add(list);

                    if (total == null) {
                        total = pTK.getAmaunTuntutan();
                    } else {
                        /*
                             if (sizeFasaPermohonan1.size() > 1 && !pTK.getKodTuntut().getKod().equals("DEV04")) {
                             total = total.add(new BigDecimal(0));
                             } else {
                         */
                        total = total.add(pTK.getAmaunTuntutan());
                        //}
                    }

                    List sizeFasaPermohonan = devServ.findFasaPermohonanSizeMMK(permohonan.getIdPermohonan());
                    if (sizeFasaPermohonan.size() == 1) {
                        getContext().getRequest().setAttribute("rayuan", Boolean.FALSE);
                    } else {
                        getContext().getRequest().setAttribute("rayuan", Boolean.TRUE);
                    }

                } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
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
                    total = total.add(pTK.getAmaunTuntutan());
                } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
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
                    total = total.add(pTK.getAmaunTuntutan());
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            LOG.info("listBayaran : " + listBayaran.size());
            LOG.info("total : " + total.setScale(2));
            jumlahCaj = total.setScale(2);

            if (permohonan.getKodUrusan().getKod().equals("RLTB")) {
                List listFasa = devServ.findFasaPermohonanSize(permohonan.getIdPermohonan());

                getContext().getRequest().setAttribute("buttonsaverltb", Boolean.TRUE);
                if (listFasa.size() == 1) {
                    getContext().getRequest().setAttribute("semak3bln", Boolean.TRUE);
                } else if (listFasa.size() == 2) {
                    getContext().getRequest().setAttribute("semak6bln", Boolean.TRUE);
                } else if (listFasa.size() == 3) {
                    getContext().getRequest().setAttribute("semak12bln", Boolean.TRUE);
                }
            }
        }
    }

    public Resolution save() throws ParseException, InterruptedException {
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

            //PBMT - For Caj Pengecualian Ukur dan Pengecualian Tanda Sempadan
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                final BigDecimal NEGATION = new BigDecimal(-1);
                if (mtk.getTuntutKos().getKodTuntut().getKod().equals("DISSEM")) {
                    if (cajPengecualianSem != null && !cajPengecualianSem.equals(BigDecimal.ZERO)) {
                        Transaksi transPSem = new Transaksi();

                        LOG.info("START KEW_TRANS PENGECUALIAN TANDA SEMPADAN");
                        transPSem.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                        transPSem.setAkaunDebit(akTerima);
                        transPSem.setAmaun(cajPengecualianSem.multiply(NEGATION));
                        transPSem.setUntukTahun(year);
                        transPSem.setTahunKewangan(year);
                        transPSem.setPermohonan(mtk.getPermohonan());
                        transPSem.setDokumenKewangan(dk);
                        transPSem.setPerihal("Pengecualian Bayaran Tanda Sempadan");
                        transPSem.setStatus(kodStatusDAO.findById('T'));
                        transPSem.setKodUrusan(mohon.getKodUrusan().getKod());
                        transPSem.setKuantiti(1);
                        transPSem.setInfoAudit(ia);
                        transPSem.setCawangan(caw);
                        transaksiDAO.save(transPSem);
                        LOG.info("END KEW_TRANS PENGECUALIAN TANDA SEMPADAN: " + trans.getIdTransaksi());

                    }

                }
                if (mtk.getTuntutKos().getKodTuntut().getKod().equals("DISUKUR")) {
                    if (cajPengecualianUkur != null && !cajPengecualianUkur.equals(BigDecimal.ZERO)) {
                        Transaksi transPUkur = new Transaksi();

                        LOG.info("START KEW_TRANS PENGECUALIAN BAYARAN UKUR");
                        transPUkur.setKodTransaksi(kodTransaksiDAO.findById(mtk.getKodTransaksi()));
                        transPUkur.setAkaunDebit(akTerima);
                        transPUkur.setAmaun(cajPengecualianUkur.multiply(NEGATION));
                        transPUkur.setUntukTahun(year);
                        transPUkur.setTahunKewangan(year);
                        transPUkur.setPermohonan(mtk.getPermohonan());
                        transPUkur.setDokumenKewangan(dk);
                        transPUkur.setPerihal("Pengecualian Bayaran Ukur");
                        transPUkur.setStatus(kodStatusDAO.findById('T'));
                        transPUkur.setKodUrusan(mohon.getKodUrusan().getKod());
                        transPUkur.setKuantiti(1);
                        transPUkur.setInfoAudit(ia);
                        transPUkur.setCawangan(caw);
                        transaksiDAO.save(transPUkur);
                        LOG.info("END KEW_TRANS PENGECUALIAN BAYARAN UKUR: " + trans.getIdTransaksi());
                    }
                }
            }

            LOG.info("START PermohonanTuntutanBayaran");
            PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                ptb = lupusservice.findMohonTuntutanBayarByIdTuntutKos(mtk.getTuntutKos().getIdKos());
            }
            ptb.setPermohonanTuntutanKos(mtk.getTuntutKos());
            ptb.setDokumenKewangan(dk);
            //PBMT - Pengecualian Bayaran Ukur dan Tanda Sempadan
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                final BigDecimal NEGATION = new BigDecimal(-1);
                if (mtk.getTuntutKos().getKodTuntut().getKod().equals("DISSEM")) {
                    if (cajPengecualianSem != null && !cajPengecualianSem.equals(BigDecimal.ZERO)) {
                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan().add(cajPengecualianSem.multiply(NEGATION)));
                    }

                } else if (mtk.getTuntutKos().getKodTuntut().getKod().equals("DISUKUR")) {
                    if (cajPengecualianUkur != null && !cajPengecualianUkur.equals(BigDecimal.ZERO)) {
                        ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan().add(cajPengecualianUkur.multiply(NEGATION)));
                    }
                } else {
                    ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
                }
            } else {
                ptb.setAmaun(mtk.getTuntutKos().getAmaunTuntutan());
            }

            ptb.setTarikhBayar(now);
            ptb.setInfoAudit(ia);
            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                permohonanTuntutanBayarDAO.saveOrUpdate(ptb);
            } else {
                ptb.setPermohonan(permohonan);

                permohonanTuntutanBayarDAO.save(ptb);
            }
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

        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            PermohonanUkur mohonUkur = new PermohonanUkur();
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
            mohonUkur.setTarikhResit(now);
            mohonUkur.setNoResit(resitNo);
            mohonUkur.setPermohonan(mohon);
            mohonUkur.setNoPermohonanUkur("-");
            mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
            disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
        }
        
        if (permohonan.getKodUrusan().getKod().equals("SBMS")) {
                                  sBMSIntegrationFlowService.initiateAgihTugasNoPuPt(permohonan);
                            Thread.sleep(15000);

            sBMSIntegrationFlowService.initiateAgihTugasChartingLulus(permohonan);


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

                //SDP PBMT Kelompok
//                if (StringUtils.isNotBlank(idMOhon)) {
//                    mohon = permohonanDAO.findById(idMOhon);
//                    if (mohon.getKodUrusan().getKod().equals("PBMT")) {
//                        List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
//                        listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(idMOhon);
//                        String idMohonKelompok = new String();
//                        if (listMohonKelompok.size() > 0) {
//                            PermohonanKelompok pk = new PermohonanKelompok();
//                            pk = listMohonKelompok.get(0);
//                            if (pk.getPermohonanInduk().getCatatan().equals("K")) {
//                                mohon.setInfoAudit(disLaporanTanahService.findAudit(mohon, "update", pengguna));
//                                mohon.setStatus("SD");
//                                disLaporanTanahService.getPelupusanService().simpanPermohonan(mohon);
//                                LOG.info("status permohonan:" + mohon.getStatus());
//                                idMohonKelompok = pk.getPermohonanInduk().getIdPermohonan();
//                            }
//                        }
//
//                        if (StringUtils.isNotBlank(idMohonKelompok)) {
//                            List<PermohonanKelompok> listKelompok = new ArrayList<PermohonanKelompok>();
//                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idMohonKelompok);
//                            boolean statusKelompok = true;
//                            if (listKelompok.size() > 0) {
//                                for (PermohonanKelompok pkk : listKelompok) {
//                                    if (pkk.getPermohonan().getStatus() != null) {
//                                        if (!pkk.getPermohonan().getStatus().equals("SD")) {
//                                            statusKelompok = false;
//                                        }
//                                    }
//                                }
//                            }
//                            if (statusKelompok) {
//                                mohon = permohonanDAO.findById(idMohonKelompok);
//                                if (mohon != null) {
//                                    mohon.setInfoAudit(disLaporanTanahService.findAudit(mohon, "update", pengguna));
//                                    mohon.setStatus("AK");
//                                    disLaporanTanahService.getPelupusanService().simpanPermohonan(mohon);
//                                    LOG.info("status permohonan kelompok:" + mohon.getStatus());
//                                }
//                            }
//                        }
//                    }
//                }
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            LOG.error(ex);

            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dk};
        dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
        String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
    }

    public Resolution saveRayuan() throws ParseException {
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
        dk.setIdKaunter(pengguna.getIdKaunter());
        saveCaraBayaran(caw, dk, ia);
        dokumenKewanganDAO.save(dk);
        //set KEW_TRANS
        LOG.info("listBayaran : " + listBayaran.size());

        for (BayaranValue mtk : listBayaran) {

            if (mtk.getTuntutKos().getKodTuntut().getKod().equals("CON04")) {

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
                permohonanTuntutanBayarDAO.save(ptb);
                LOG.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());
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
        penyataPemungutService.savePenyataPemungut(dk);
        tx.commit();

        noResitGenerator.commitAndUnlockSerialNo(pguna);

        LOG.info("RESIT ID DOCUMENT: " + resit.getIdDokumen());
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
                WorkFlowService.updateTaskOutcome(taskID, ctxW, "RN");
            }

        } catch (Exception ex) {
            LOG.error(ex);

            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dk};
        dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
        String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        addSimpleMessage("Permohonan telah berjaya dihantar.");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
    }

    public Resolution save3bulan() throws ParseException {
        boolean check = checkDateLanjutTempoh3Bulan();
        LOG.debug("Check ->" + check);
        if (check) {
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

            if (permohonan.getKodUrusan().getJabatan().getKod().equals("24") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
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
            dk.setIdKaunter(pengguna.getIdKaunter());
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);

            //set KEW_TRANS
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
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
                LOG.debug("--> amount" + mtk.getAmaun());
                if (permohonan.getKodUrusan().getKod().equals("RLTB") && mtk.getTuntutKos().getKodTuntut().getKod().equals("DEV04")) {
                    BigDecimal totalsub = jumlahCaj.subtract(total);
                    totalpremiumbayar = mtk.getAmaun().subtract(totalsub);
                    LOG.debug("DEV04 : " + totalpremiumbayar);
                    trans.setAmaun(totalpremiumbayar);
                } else {
                    trans.setAmaun(mtk.getAmaun());
                }

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

            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
            ptk = devServ.findMohonTuntutKosByPremium(permohonan.getPermohonanSebelum().getIdPermohonan());
            BigDecimal amountTuntutAsal = ptk.getAmaunTuntutan();

            LOG.debug("/***********************/");
            LOG.debug("Amount Tuntut Asal : " + amountTuntutAsal);
            LOG.debug("Premium Baru : " + totalpremiumbayar);
            LOG.debug("/***********************/");
            BigDecimal totalpremiumbaru = amountTuntutAsal.subtract(totalpremiumbayar);

            ptk.setAmaunSebenar(amountTuntutAsal);
            ptk.setAmaunTuntutan(totalpremiumbaru);
            devServ.simpanPermohonanTuntutanKos(ptk);

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

            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                PermohonanUkur mohonUkur = new PermohonanUkur();
                mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
                mohonUkur.setTarikhResit(now);
                mohonUkur.setNoResit(resitNo);
                mohonUkur.setPermohonan(permohonan);
                mohonUkur.setNoPermohonanUkur("-");
                mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
                disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
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
                    if (permohonan.getKodUrusan().getKod().equals("RLTB")) {
                        int comparevalue = jumlahCaj.compareTo(total);
                        LOG.debug("Compare Value : " + comparevalue);
                        if (comparevalue == 0) {
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "L");
                            //PindaanN9Validator pN9 = new PindaanN9Validator();
                            //pN9.beforeComplete(context, "L");

                            //WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),permohonan.getIdPermohonan(), permohonan.getCawangan().getKod(), pengguna.getIdPengguna(),permohonan.getKodUrusan().getNama());
                        } else {
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "R6");
                        }
                    }
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
            addSimpleMessage("Urusan telah berjaya didaftarkan.");

            return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
        } else {
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

                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "XY");
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }

            addSimpleMessage("Permohonan ini telah tamat tempoh 3 bulan dari waktu permohonan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran_perihal.jsp");
        }
    }

    public Resolution save6bulan() throws ParseException {
        boolean check = checkDateLanjutTempoh6Bulan();
        LOG.debug("Check ->" + check);
        if (check) {
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

            if (permohonan.getKodUrusan().getJabatan().getKod().equals("24") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
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
            dk.setIdKaunter(pengguna.getIdKaunter());
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);

            //set KEW_TRANS
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
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
                LOG.debug("--> amount" + mtk.getAmaun());
                if (permohonan.getKodUrusan().getKod().equals("RLTB") && mtk.getTuntutKos().getKodTuntut().getKod().equals("DEV04")) {
                    BigDecimal totalsub = jumlahCaj.subtract(total);
                    totalpremiumbaru = mtk.getAmaun().subtract(totalsub);
                    LOG.debug("DEV04 : " + totalpremiumbaru);
                    trans.setAmaun(totalpremiumbaru);
                } else {
                    trans.setAmaun(mtk.getAmaun());
                }

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

            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
            ptk = devServ.findMohonTuntutKosByPremium(permohonan.getPermohonanSebelum().getIdPermohonan());
            BigDecimal amountTuntutAsal = ptk.getAmaunTuntutan();

            LOG.debug("/***********************/");
            LOG.debug("Amount Tuntut Asal : " + amountTuntutAsal);
            LOG.debug("Premium Baru : " + totalpremiumbaru);
            LOG.debug("/***********************/");

            ptk.setAmaunSebenar(amountTuntutAsal);
            ptk.setAmaunTuntutan(totalpremiumbaru);
            devServ.simpanPermohonanTuntutanKos(ptk);

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

            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                PermohonanUkur mohonUkur = new PermohonanUkur();
                mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
                mohonUkur.setTarikhResit(now);
                mohonUkur.setNoResit(resitNo);
                mohonUkur.setPermohonan(permohonan);
                mohonUkur.setNoPermohonanUkur("-");
                mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
                disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
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
                    if (permohonan.getKodUrusan().getKod().equals("RLTB")) {
                        int comparevalue = jumlahCaj.compareTo(total);
                        LOG.debug("Compare Value : " + comparevalue);
                        if (comparevalue == 0) {
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "L");
                        } else {
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "R12");
                        }
                    }
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
            addSimpleMessage("Urusan telah berjaya didaftarkan.");

            return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
        } else {
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

                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "XY");
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }

            addSimpleMessage("Permohonan ini telah tamat tempoh 6 bulan dari waktu permohonan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran_perihal.jsp");
        }
    }

    public Resolution lanjut12() throws ParseException {
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
                if (permohonan.getKodUrusan().getKod().equals("RLTB")) {

                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "R12");

                }
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            LOG.error(ex);

            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        addSimpleMessage("Permohonan lanjut tempoh telah berjaya");
        return new ForwardResolution("/WEB-INF/jsp/main.jsp");
    }

    public Resolution save12bulan() throws ParseException {
        boolean check = checkDateLanjutTempoh12Bulan();
        LOG.debug("Check ->" + check);
        if (check) {
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

            if (permohonan.getKodUrusan().getJabatan().getKod().equals("24") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
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
            dk.setIdKaunter(pengguna.getIdKaunter());
            saveCaraBayaran(caw, dk, ia);
            dokumenKewanganDAO.save(dk);

            //set KEW_TRANS
            LOG.info("listBayaran : " + listBayaran.size());
            for (BayaranValue mtk : listBayaran) {
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
                LOG.debug("--> amount" + mtk.getAmaun());
                if (permohonan.getKodUrusan().getKod().equals("RLTB") && mtk.getTuntutKos().getKodTuntut().getKod().equals("DEV04")) {
                    BigDecimal totalsub = jumlahCaj.subtract(total);
                    totalpremiumbaru = mtk.getAmaun().subtract(totalsub);
                    LOG.debug("DEV04 : " + totalpremiumbaru);
                    trans.setAmaun(totalpremiumbaru);
                } else {
                    trans.setAmaun(mtk.getAmaun());
                }

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

            PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
            ptk = devServ.findMohonTuntutKosByPremium(permohonan.getPermohonanSebelum().getIdPermohonan());
            BigDecimal amountTuntutAsal = ptk.getAmaunTuntutan();

            LOG.debug("/***********************/");
            LOG.debug("Amount Tuntut Asal : " + amountTuntutAsal);
            LOG.debug("Premium Baru : " + totalpremiumbaru);
            LOG.debug("/***********************/");

            ptk.setAmaunSebenar(amountTuntutAsal);
            ptk.setAmaunTuntutan(totalpremiumbaru);
            devServ.simpanPermohonanTuntutanKos(ptk);

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

            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                PermohonanUkur mohonUkur = new PermohonanUkur();
                mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
                mohonUkur.setTarikhResit(now);
                mohonUkur.setNoResit(resitNo);
                mohonUkur.setPermohonan(permohonan);
                mohonUkur.setNoPermohonanUkur("-");
                mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
                disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
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
                    if (permohonan.getKodUrusan().getKod().equals("RLTB")) {
                        int comparevalue = jumlahCaj.compareTo(total);
                        LOG.debug("Compare Value : " + comparevalue);
                        if (comparevalue == 0) {
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "L");
                        }
                    }
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }
            String[] n1 = {"dokumenKewangan"};
            Object[] v1 = {dk};
            dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
            String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
            addSimpleMessage("Urusan telah berjaya didaftarkan.");

            return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
        } else {
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

                    WorkFlowService.updateTaskOutcome(taskID, ctxW, "XY");
                }

            } catch (Exception ex) {
                LOG.error(ex);

                noResitGenerator.rollbackAndUnlockSerialNo(pguna);
                return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
            }

            addSimpleMessage("Permohonan ini telah tamat tempoh 12 bulan dari waktu permohonan");
            return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran_perihal.jsp");
        }
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

    private boolean checkDateLanjutTempoh3Bulan() {
        boolean ok = false;

        // Date Expired
        Date dateMohon = permohonan.getInfoAudit().getTarikhMasuk();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateMohon);
        calendar.add(Calendar.DATE, 90);
        Date expiredDate = new Date();
        expiredDate = calendar.getTime();

        //Current Date
        Date dateCurrent = new Date();

        LOG.debug("Date Mohon : " + dateMohon);
        LOG.debug("Expired Date : " + expiredDate);
        LOG.debug("Current Date : " + dateCurrent);

        if (dateCurrent.before(expiredDate) && dateCurrent.after(dateMohon)) {
            ok = true;
            LOG.debug("Date = true");
        }

        return ok;
    }

    private boolean checkDateLanjutTempoh6Bulan() {
        boolean ok = false;

        // Date Expired
        Date dateMohon = permohonan.getInfoAudit().getTarikhMasuk();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateMohon);
        calendar.add(Calendar.DATE, 180);
        Date expiredDate = new Date();
        expiredDate = calendar.getTime();

        //Current Date
        Date dateCurrent = new Date();

        LOG.debug("Date Mohon : " + dateMohon);
        LOG.debug("Expired Date : " + expiredDate);
        LOG.debug("Current Date : " + dateCurrent);

        if (dateCurrent.before(expiredDate) && dateCurrent.after(dateMohon)) {
            ok = true;
            LOG.debug("Date = true");
        }

        return ok;
    }

    private boolean checkDateLanjutTempoh12Bulan() {
        boolean ok = false;

        // Date Expired
        Date dateMohon = permohonan.getInfoAudit().getTarikhMasuk();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateMohon);
        calendar.add(Calendar.DATE, 365);
        Date expiredDate = new Date();
        expiredDate = calendar.getTime();

        //Current Date
        Date dateCurrent = new Date();

        LOG.debug("Date Mohon : " + dateMohon);
        LOG.debug("Expired Date : " + expiredDate);
        LOG.debug("Current Date : " + dateCurrent);

        if (dateCurrent.before(expiredDate) && dateCurrent.after(dateMohon)) {
            ok = true;
            LOG.debug("Date = true");
        }

        return ok;
    }

    public Resolution rayuanBayaran() throws ParseException {

        LOG.info(":::: RAYUAN BAYARAN ::::");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("ID PERMOHONAN: " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        for (int i = 0; i < 5; i++) {
            CaraBayaran cr = new CaraBayaran();
            cr.setAmaun(BigDecimal.ZERO); // reset amount
            senaraiCaraBayaran.add(cr);
        }

        List<PermohonanTuntutanKos> mohonTuntutKosList = consService.findSenaraiMohonTuntutKosByKodTuntut(permohonan.getIdPermohonan(), "CON04");

        if (mohonTuntutKosList.isEmpty()) {

            int amaun = 1000 * permohonan.getSenaraiHakmilik().size();
            PermohonanTuntutanKos permohonanTuntutanKos = new PermohonanTuntutanKos();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            KodTuntut kodTuntut = new KodTuntut();
            kodTuntut.setKod("CON04");
            permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setAmaunTuntutan(new BigDecimal(amaun));
            permohonanTuntutanKos.setKodTuntut(kodTuntut);
            permohonanTuntutanKos.setItem("Bayaran Rayuan - " + permohonan.getIdPermohonan());
            permohonanTuntutanKos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
            permohonanTuntutanKos.setInfoAudit(infoAudit);
            LOG.info("----Simpan Maklumat Rayuan Bayaran----");
            consService.simpanMohonTuntutKos(permohonanTuntutanKos);

            listBayaran = new ArrayList<BayaranValue>();
            total = new BigDecimal(BigInteger.ZERO);

            BayaranValue list = new BayaranValue();

            list.setIdKos(permohonanTuntutanKos.getIdKos());
            list.setIdPermohonan(permohonanTuntutanKos.getPermohonan().getIdPermohonan());
            list.setAmaun(permohonanTuntutanKos.getAmaunTuntutan());
            list.setKodUrusan(permohonanTuntutanKos.getPermohonan().getKodUrusan().getKod());
            list.setNamaUrusan(permohonanTuntutanKos.getItem());
            list.setKodTransaksi(permohonanTuntutanKos.getKodTransaksi().getKod());
            list.setPermohonan(permohonan);
            list.setTuntutKos(permohonanTuntutanKos);
            list.setKuantiti(permohonan.getSenaraiHakmilik().size());

            listBayaran.add(list);

            if (total == null) {
                total = new BigDecimal(amaun);
            } else {
                total = total.add(new BigDecimal(amaun));
            }

            LOG.info("listBayaran : " + listBayaran.size());
            LOG.info("total : " + total.setScale(2));
            jumlahCaj = total.setScale(2);
        }

        getContext().getRequest().setAttribute("rayuanBayaran", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/kaunter/bayaran_perihal.jsp");

    }

//    public Resolution rayuanBayaran() throws ParseException {
//        LOG.info(":::: RAYUAN BAYARAN ::::");
//
//        StringBuilder sb = new StringBuilder();
//        try {
//            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
//            for (Map<String, String> map : list) {
//                String taskID = map.get("taskId");
//                String idMOhon = map.get("idPermohonan");
//                if (sb.length() > 0) {
//                    sb.append(",");
//                }
//                sb.append(idMOhon);
//                LOG.info("TaskID ::" + taskID);
//                LOG.info("idPermohonan ::" + idMOhon);
//
//                WorkFlowService.updateTaskOutcome(taskID, ctxW, "RN");
//            }
//
//        } catch (Exception ex) {
//            LOG.error(ex);
//
//            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
//            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
//        }
//        addSimpleMessage("Permohonan telah berjaya dihantar untuk proses rayuan bayaran.");
//        return new ForwardResolution("/WEB-INF/jsp/common/msg.jsp");
//
//    }
    public Resolution saveTambahan() throws ParseException {
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
            ptb.setPermohonan(permohonan);
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
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(ia);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setTarikhHantar(new java.util.Date());
        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        //Integration Pendaftaran
        LOG.info("Initiate HKBM");
        Boolean statusQT = Boolean.FALSE;
        String[] name = {"permohonan"};
        Object[] value = {permohonan};
        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
        List<HakmilikPermohonan> senaraiHakmilikHKBMPendaftar = new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> senaraiHakmilikHKBMDaerah = new ArrayList<HakmilikPermohonan>();
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        for (HakmilikPermohonan hm : senaraiHakmilik) {
            hm.setInfoAudit(info);
            if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                if (hm.getKodHakmilikTetap().getKod().equals("GRN") || hm.getKodHakmilikTetap().getKod().equals("PN")) {
                    hm.setKodHakmilikSementara(null);
                    if (hm.getKodHakmilik().getKod().equals("PN")) {
                        hm.setPegangan("P");
                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                    } else {
                        hm.setPegangan("S");
                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                    }
                    hm.setPermohonan(permohonan);
                    hm = strService.saveHakmilikPermohonan(hm);
                    senaraiHakmilikHKBMPendaftar.add(hm);
                } else if (hm.getKodHakmilikTetap().getKod().equals("GM") || hm.getKodHakmilikTetap().getKod().equals("PM")) {
                    hm.setKodHakmilikSementara(null);
                    if (hm.getKodHakmilik().getKod().equals("PM")) {
                        hm.setPegangan("P");
                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                    } else {
                        hm.setPegangan("S");
                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                    }
                    hm.setPermohonan(permohonan);
                    hm = strService.saveHakmilikPermohonan(hm);
                    senaraiHakmilikHKBMDaerah.add(hm);
                }
                statusQT = Boolean.TRUE;
            } else { //Straight FT
                if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                    hm.setKodHakmilikSementara(null);
                    if (hm.getKodHakmilik().getKod().equals("PN")) {
                        hm.setPegangan("P");
                    } else {
                        hm.setPegangan("S");
                    }
                    hm.setPermohonan(permohonan);
                    hm = strService.saveHakmilikPermohonan(hm);
                    senaraiHakmilikHKBMPendaftar.add(hm);
                } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                    hm.setKodHakmilikSementara(null);
                    if (hm.getKodHakmilik().getKod().equals("PM")) {
                        hm.setPegangan("P");
                    } else {
                        hm.setPegangan("S");
                    }
                    hm.setPermohonan(permohonan);
                    hm = strService.saveHakmilikPermohonan(hm);
                    senaraiHakmilikHKBMDaerah.add(hm);
                }
                statusQT = Boolean.FALSE;
            }
        }
        KodUrusan kod = new KodUrusan();
        if (senaraiHakmilikHKBMPendaftar.size() > 0) {
            if (statusQT) {
                kod = kodUrusanDAO.findById("HKGHS");
                LOG.info("HKGHS Pendaftar");
            } else {
                kod = kodUrusanDAO.findById("HKBM");
                LOG.info("HKBM Pendaftar");
            }
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, pengguna, senaraiHakmilikHKBMPendaftar, permohonan, stageId);
        }

        if (senaraiHakmilikHKBMDaerah.size() > 0) {
            if (statusQT) {
                kod = kodUrusanDAO.findById("HKGHS");
                LOG.info("HKGHS Pendaftar");
            } else {
                kod = kodUrusanDAO.findById("HKBM");
                LOG.info("HKBM Pendaftar");
            }
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, pengguna, senaraiHakmilikHKBMDaerah, permohonan, stageId);
        }
        tambahan = Boolean.TRUE;
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
                WorkFlowService.updateTaskOutcome(taskID, ctxW, "L"); //For send outcome L
//                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            LOG.error(ex);

            noResitGenerator.rollbackAndUnlockSerialNo(pguna);
            return new RedirectResolution(BayaranPerihalActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
        String[] n1 = {"dokumenKewangan"};
        Object[] v1 = {dk};
        dkbList = dokKewBayaranDAO.findByEqualCriterias(n1, v1, null);
        String msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        return new ForwardResolution("/WEB-INF/jsp/kaunter/cetak_resit_bayaran.jsp");
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
        BayaranPerihalActionBean.kodNegeri = kodNegeri;
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

    public BigDecimal getCajPengecualianUkur() {
        return cajPengecualianUkur;
    }

    public void setCajPengecualianUkur(BigDecimal cajPengecualianUkur) {
        this.cajPengecualianUkur = cajPengecualianUkur;
    }

    public BigDecimal getCajPengecualianSem() {
        return cajPengecualianSem;
    }

    public void setCajPengecualianSem(BigDecimal cajPengecualianSem) {
        this.cajPengecualianSem = cajPengecualianSem;
    }

    public boolean isTambahan() {
        return tambahan;
    }

    public void setTambahan(boolean tambahan) {
        this.tambahan = tambahan;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public boolean isStatusRayuanPTD() {
        return statusRayuanPTD;
    }

    public void setStatusRayuanPTD(boolean statusRayuanPTD) {
        this.statusRayuanPTD = statusRayuanPTD;
    }

    public boolean isStatusRayuanPTG() {
        return statusRayuanPTG;
    }

    public void setStatusRayuanPTG(boolean statusRayuanPTG) {
        this.statusRayuanPTG = statusRayuanPTG;
    }

    public String getTarikhAkhirBayaranRayuan() {
        return tarikhAkhirBayaranRayuan;
    }

    public void setTarikhAkhirBayaranRayuan(String tarikhAkhirBayaranRayuan) {
        this.tarikhAkhirBayaranRayuan = tarikhAkhirBayaranRayuan;
    }
}
