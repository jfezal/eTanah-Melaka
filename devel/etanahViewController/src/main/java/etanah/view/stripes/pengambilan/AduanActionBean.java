/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import java.util.List;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.model.KodBandarPekanMukim;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import etanah.model.Hakmilik;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.service.common.HakmilikUrusanService;
import etanah.model.Pihak;
import etanah.model.KumpulanAkaun;
import etanah.dao.PihakDAO;
import etanah.model.SejarahTransaksi;
import etanah.dao.SejarahTransaksiDAO;
import java.util.ArrayList;
import etanah.model.Transaksi;
import etanah.model.Akaun;
import etanah.dao.AkaunDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import java.math.BigDecimal;
import java.util.Date;
import etanah.model.InfoAudit;
import etanah.service.HakmilikService;
import java.util.Map;
import etanah.model.Pengguna;
import etanah.workflow.WorkFlowService;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.common.PermohonanService;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCaraPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.SejarahDokumenKewangan;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import etanah.manager.TabManager;
import etanah.model.Alamat;
import etanah.model.CaraBayaran;//
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodSeksyen;
import etanah.model.PermohonanAduan;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanAduanService;
import etanah.service.RegService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.model.KodUrusan;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.model.Dokumen;
import etanah.model.KodCaraPermohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.EnforceService;
import java.io.Serializable;
import org.hibernate.Transaction;
import etanah.view.kaunter.UrusanValue;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihakHubungan;
import etanah.service.KaunterService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.PemohonService;
import etanah.service.daftar.PembetulanService;
import java.util.Iterator;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ErrorResolution;
import etanah.util.DMSUtil;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import java.io.File;

/**
 *
 * @author Admin
 */
@UrlBinding("/pengambilan/carian_aduan")
public class AduanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(AduanActionBean.class);
    private static boolean isDebug = logger.isDebugEnabled();
    String negeri;
    String kodDaerah;
    private String caw;
    private String noHakmilik;
    private String namaPembayar;
    private String noPengenalanP;
    private String kodHakmilik;
    private String bandarPekanMukim;
    private String seksyen;
    private String lot;
    String namaBPM;
    int kodBPM;
    String daerah;
    String noLot;
    String jenisHakmilik;
    String jenisLot;
    private Hakmilik hakmilik;
    private Pengguna pengguna;
    private Akaun akaunKredit;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    etanah.Configuration etanahConf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    RegService regService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakKepentinganService hpkService;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    SejarahTransaksiDAO sejarahTransaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    WorkFlowService WorkFlowService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hpDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    TabManager tabManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    String idHakmilik;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private KodBandarPekanMukimDAO kodbandarPekanMukimDAO;
    @Inject
    private KodCaraPermohonanDAO caraPermohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private DokumenService dokumenService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodPBTDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private KaunterService kaunterService;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListTakAktif;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganList;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikUrusan> hakmilikUrusanListTaktif;
    private List<HakmilikUrusan> hakmilikUrusanProsesList;
    private List<Permohonan> mohonTolakGantung;
    String idPihak;
    String idHakmilikPihakBerkepentingan;
    private String pegang;
    Pihak pihak;
    HakmilikPihakBerkepentingan hpk;
    private List<Transaksi> transList;
    private BigDecimal baki;
    private List<HakmilikPihakBerkepentingan> pihakList;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal jumCukai = new BigDecimal(0.00);
    private BigDecimal y;
    private BigDecimal w;
    private BigDecimal z;
    private BigDecimal r;
    private BigDecimal jum = new BigDecimal(0.00);
    private BigDecimal amaunRemesyen = new BigDecimal(0.00);
    private BigDecimal amaunDebit = new BigDecimal(0.00);
    private BigDecimal totalAmaunDebit = new BigDecimal(0.00);
    private BigDecimal jumDenda;
    private BigDecimal notis;
//    private double denda;
    private BigDecimal denda;
    private BigDecimal amaun;
    private BigDecimal amaunDenda;
    private String btn2;
    private List<Map<String, String>> senaraiUrusan;
    private List<Map<String, String>> senaraiUrusanProses;
    private List<Map<String, String>> senaraiUrusanProsesDistinct;
    public String selectedTab = "0";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private List<Akaun> listAkaun = new ArrayList();
    private boolean flag = false;
    private boolean carianPermohonan = false;
    private List<KodBandarPekanMukim> senaraiBPM;
    private KodBandarPekanMukim bandarPekanMukim1;
    private String noAkaun;
    private String kodNegeri;
    private Akaun akaun;
    private List<Akaun> list;//
    private boolean del = true;//
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();//
    private boolean visible = true;//
    private int bil = 0;//
    private boolean button = false;//
    private List<SejarahTransaksi> sejarahList;
    private List<Hakmilik> senaraiHakmilikBerikut;
    private String kodPengenalan;
    private String kodStatusHakmilik;
    private String noPengenalan;
    private String nama;
    private String namaPemilik;
    private List<HakmilikPihakBerkepentingan> l;
    private List<Akaun> senaraiAkaun;
    private Pihak pemegang;
    private Transaksi idTransaksi;
    private boolean test = false;
    private SejarahDokumenKewangan dokumenKewangan;
    private List<HakmilikAsal> listHakmilikAsal = new ArrayList();
    private List<HakmilikSebelum> listHakmilikSebelum = new ArrayList();
    private List<HakmilikAsal> listSejarahHakmilikAsal;
    private List<HakmilikSebelum> listSejarahHakmilikSebelum;
    private BigDecimal a = new BigDecimal(0);
    private KumpulanAkaun kumpulan;
    private List<DasarTuntutanCukaiHakmilik> dasarTuntutanCukai = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private DasarTuntutanCukaiHakmilik dasarTuntutan;
    private List<KodSeksyen> senaraiKodSeksyen;
    private PermohonanPihak permohonanPihak;
//    private List<PermohonanPihak> permohonanPihakList = new ArrayList();
    private Permohonan permohonanPemilikan;
    private List<HakmilikPermohonan> listHakmilik;
    private String idMH;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private PermohonanAduan permohonanAduan;
    private KodUrusan kod;
    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private KodCawangan cawangan;
    private FolderDokumen folderDokumen;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> KodDokumenNotRequired = new ArrayList<KodDokumen>();
    private KodUrusan kodUrusan;
    private String kodUrusanTemp;
    private String idPermohonan;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private String perihal;
    private KodCaraPermohonan caraPermohonan;
    private String stageId;
    private String nextStage;
    private String taskId;
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    IWorkflowContext ctxOnBehalf = null;
    private long idDokumen;
    private List<Permohonan> listMohon;
    private Pemohon pmohon;
    private List<String> selectedPihak = new ArrayList<String>();
    private String selectedPihak2;
    private String selectedWakil;
    private HakmilikPermohonan hakP;
    private String selectedMH;
    private Permohonan pSebelum;
    private String dokumenKod;
    private Alamat alamat;
    FileBean fileToBeUpload;
    private String kodNegeriAlamat;
    FileBean fileToBeUploaded;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String idPihakVal;
    private String idPermohonanBaru;
    private KodNegeri kodN;
    private PermohonanPihakHubungan permohonanPihakHubungan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");
    }

    public Resolution pertanyaanHakmilik() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("common/pertanyaan_hakmilik.jsp");
    }

    public Resolution tabPertanyaanHakmilik() {
        return new JSP("common/tab_maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution kembali() {
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");
    }

    public final void getUrusanfromSession() {
        logger.debug("getUrusanfromSession");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            UrusanCache u = (UrusanCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();

                }
                idPihakVal = u.idPihakVal;
                selectedMH = u.selectedMH;
            } else {
                logger.debug("no data in session");
            }
        } catch (Exception ex) {
            logger.error(ex);
            ctx.removeWorkdata();
        }

    }

    public final void saveToSession(etanahActionBeanContext ctx) {
        logger.debug("saveToSession");
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
        u.permohonan = permohonan;
        u.idPihakVal = idPihakVal;
        u.selectedMH = selectedMH;

        ctx.setWorkData(u);
    }

    public Resolution kembali2() {
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedMH));
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        HakmilikPermohonan hp = hakmilikPermohonanList.get(0);
        logger.info("hp != null" + hp.getPermohonan().getIdPermohonan());
        if (hakmilikPermohonan != null) {
            logger.info("HakmilikPermohonan != null");
            logger.debug("hp punye idpermohonan " + hakmilikPermohonan.getPermohonan().getIdPermohonan());
            permohonanAduan = aduanService.findPermohonanAduanByIdMohon(idPermohonan);
            if (permohonanAduan != null) {
                logger.info("permohonanAduan x null");
                perihal = permohonan.getSebab();

            }
            pSebelum = permohonanDAO.findById(hakmilikPermohonan.getPermohonan().getIdPermohonan());

        }
        System.out.println("::::::::::::::::  " + idPihakVal);
        System.out.println("::::::: " + hakmilikPermohonan);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_aduan_details.jsp");
    }

    class UrusanCache implements Serializable {

        Permohonan permohonan;
        PermohonanAduan permohonanAduan;
        String idPihakVal;
        String selectedMH;
    }

    public Resolution back() {
        return new JSP("common/pertanyaan_hakmilik.jsp");
    }

    public Resolution search() throws WorkflowException, ParseException {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
//            __pg_start = (Integer.parseInt(page) - 1) * __pg_max_records;
//            __pg_max_records = __pg_start + __pg_max_records;
        }
        listHakmilik = aduanService.getSeneraiPermohonanByidHakmilik(idHakmilik, "SEK4");

        if (isDebug) {
            logger.debug("page_start = " + get__pg_start());
            logger.debug("max_records = " + get__pg_max_records());
            logger.debug("total record = " + get__pg_total_records());
        }

        setFlag(true);
        setDaerah(null);
        carianPermohonan = true;
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");
    }

    public Resolution pihakDetails() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String id = getContext().getRequest().getParameter("idMH");
        logger.info("lepas select permohonan");
        logger.info("idMH :" + id);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        if (id != null) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(id));
            if (hakmilikPermohonan != null) {
                logger.debug("hp punye idpermohonan " + hakmilikPermohonan.getPermohonan().getIdPermohonan());
                permohonan = permohonanDAO.findById(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                permohonanAduan = aduanService.findPermohonanAduanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                if (permohonanAduan == null) {
                    logger.info("permohonanAduan is null");

                }
            }
//            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kod, peng, senaraiHakmilik, permohonan);
        }
//        return new JSP("pengambilan/Aduan/kemasukan_aduan_details.jsp").addParameter("popup", "true");
        carianPermohonan = true;
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_aduan_details.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        negeri = etanahConf.getProperty("kodNegeri");
        if ("04".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }
        if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "negeri";

        }
        listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(daerah);
        getUrusanfromSession();

        String ids = getContext().getRequest().getParameter("folder.idFolder");
        if (ids != null && ids.length() > 0) {
            folderDokumen = folderDAO.findById(Long.valueOf(ids));
        } else {
            ids = getContext().getRequest().getParameter("permohonan.idPermohonan");
            if (ids != null && ids.length() > 0) {
                permohonan = permohonanDAO.findById(ids);
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen != null) {
                    if (folderDokumen.getSenaraiKandungan() != null) {
                        for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                            kf.setDokumen(d);
                            senaraiKandungan.add(kf);
                        }
                    }
                }
            }
        }

    }

    public Resolution saveMohon() {
        logger.debug("saveMohon");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        idMH = getContext().getRequest().getParameter("idMH");
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedMH));

        Pihak phk = new Pihak();

        if (hakmilikPermohonan != null) {
            phk = pihakDAO.findById(Long.parseLong(selectedPihak2));
            if (phk != null) {
                    idPihakVal = Long.toString(phk.getIdPihak());
                }
            System.out.println("::::selectedPihak2 :" + selectedPihak2);
            List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentingan = hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan();
            System.out.println("::: size hakmilikPihakBerkepentingan :" + hakmilikPihakBerkepentingan.size());
//            for (int i = 0; i < hakmilikPihakBerkepentingan.size(); i++) {
//                selectedPihak2 = getContext().getRequest().getParameter("selectedPihak2" + i);
//                System.out.println("::::selectedPihak2 :" + selectedPihak2);
//                phk = pihakDAO.findById(Long.parseLong(selectedPihak2));
//                if (phk != null) {
//                    idPihakVal = Long.toString(phk.getIdPihak());
//                }
//
//            }

        }

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        String kodurusan = "SEK4A";
        if (selectedMH != null) {
            logger.debug("hakmilikPermohonan :: 1");
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedMH));
            Permohonan checkExistingPermohonan = aduanService.findpermohonanExist(hakmilikPermohonan.getPermohonan().getIdPermohonan(), phk.getIdPihak(), kodurusan);

            if (checkExistingPermohonan != null) {
                addSimpleMessage("Aduan terhadap Permohonan :" + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " oleh Pengadu " + phk.getNama() + " telah dibuat.");
                return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");



            } else {
                kod = new KodUrusan();
                if (kodNegeri.equalsIgnoreCase("04")) {
                    kodUrusan = kodUrusanDAO.findById("SEK4A");

                } else {
                    //for NS
                    kodUrusan = kodUrusanDAO.findById("SEK4A");
                }

                logger.info("kod urusan temp ::::: " + kodUrusanTemp);
                logger.info("kod urusan  ::::: " + kodUrusan.getKod());

                if (permohonan == null) {
                    idPermohonan = idPermohonanGenerator.generate(
                            ctx.getKodNegeri(), pengguna.getKodCawangan(), kodUrusan);
                    permohonan = new Permohonan();
                }
                logger.debug(":: PERMOHONAN START ::");
                caraPermohonan = new KodCaraPermohonan();
                caraPermohonan = caraPermohonanDAO.findById("KN");
                permohonan.setStatus("TA");
                permohonan.setIdPermohonan(idPermohonan);
                permohonan.setCawangan(pengguna.getKodCawangan());
                permohonan.setKodUrusan(kodUrusan);
                permohonan.setRujukanUndang2(kodUrusanTemp);
                permohonan.setCaraPermohonan(caraPermohonan);
                permohonan.setSebab(perihal);
                permohonan.setPenyerahNama(phk.getNama());
                permohonan.setPenyerahJenisPengenalan(phk.getJenisPengenalan());
                permohonan.setPenyerahNoPengenalan(phk.getNoPengenalan());
                permohonan.setPenyerahAlamat1(phk.getAlamat1());
                permohonan.setPenyerahAlamat2(phk.getAlamat2());
                permohonan.setPenyerahAlamat3(phk.getAlamat3());
                permohonan.setPenyerahAlamat4(phk.getAlamat4());
                permohonan.setPenyerahPoskod(phk.getPoskod());
                permohonan.setPenyerahNegeri(phk.getNegeri());
                permohonan.setPenyerahNoTelefon1(phk.getNoTelefon1());
                permohonan.setPenyerahEmail(phk.getEmail());
                permohonan.setInfoAudit(ia);
                permohonan.setPermohonanSebelum(hakmilikPermohonan.getPermohonan());
                enforceService.savePermohonan(permohonan);
                if (folderDokumen != null) {
                    permohonan.setFolderDokumen(folderDokumen);
                }
                logger.debug("idPermohonan" + permohonan.getIdPermohonan());

                logger.debug(":: PERMOHONAN END ::");
                logger.debug(":: FOLDER DOKUMEN START ::");
                permohonan = permohonanDAO.findById(idPermohonan);
//                idPermohonanBaru = idPermohonan;
                if (permohonan != null) {
                    logger.debug("permohonan x null");
                    folderDokumen = permohonan.getFolderDokumen();
                    if (folderDokumen != null) {
                        logger.debug("folder dok x null");
                        if (folderDokumen.getSenaraiKandungan() != null) {
                            logger.debug("senarai folder x null");
                            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                                if (kf == null || kf.getDokumen() == null) {
                                    continue;
                                }
                                Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                                d.setInfoAudit(ia);
                                kf.setDokumen(d);
                                senaraiKandungan.add(kf);
                            }
                        }
                        permohonan.setFolderDokumen(folderDokumen);

                    } else {
                        logger.debug("folder dok is null");
                        FolderDokumen fd = new FolderDokumen();
                        fd.setTajuk(permohonan.getIdPermohonan());
                        fd.setInfoAudit(ia);
                        folderDAO.save(fd);
                        permohonan.setFolderDokumen(fd);
                    }
                }
                logger.debug(":: FOLDER DOKUMEN END ::");



                logger.debug(":: PERMOHONAN ADUAN START ::");
                permohonanAduan = new PermohonanAduan();
                permohonanAduan.setPermohonan(permohonan);
                permohonanAduan.setBandarPekanMukim(hakmilikPermohonan.getHakmilik().getBandarPekanMukim());
                permohonanAduan.setPerihal(perihal);
                permohonanAduan.setCawangan(caw);
                permohonanAduan.setInfoAudit(ia);
                aduanService.simpanPA(permohonanAduan);
                logger.debug(":: PERMOHONAN ADUAN END ::");

                logger.debug(":: PEMOHON START ::");

                pmohon = pemohonService.findPemohonByPermohonanPihak(permohonan, phk);
                logger.debug("idPermohonan Pmohon" + permohonan.getIdPermohonan());
                if (pmohon == null) {
                    logger.debug("Pemohon :: null");
                    pmohon = new Pemohon();
                    pmohon.setPermohonan(permohonan);
                    pmohon.setPihak(phk);
                    pmohon.setCawangan(caw);
                    pmohon.setInfoAudit(ia);
                    pemohonService.saveOrUpdate(pmohon);

                }
                logger.debug(":: PEMOHON END ::");
                logger.debug(":: PERMOHONAN PIHAK START ::");

                permohonanPihak = aduanService.findPihakByIdMohon(idPermohonan);
                logger.debug("permohonanPihak" + permohonan.getIdPermohonan());
//                if(selectedWakil !=null){
//                    if (permohonanPihak == null) {
//                    logger.debug("ada wakil :: permohonanPihak :: null");
//                    permohonanPihak = new PermohonanPihak();
//                    permohonanPihak.setPermohonan(permohonan);
//                    permohonanPihak.setPihak(phk);
//                    logger.debug("phk.getAlamat1()" + phk.getAlamat1());
//                    alamat1=getContext().getRequest().getParameter("permohonanPihak.alamat.alamat1");
//                    alamat2=getContext().getRequest().getParameter("permohonanPihak.alamat.alamat2");
//                    alamat3=getContext().getRequest().getParameter("permohonanPihak.alamat.alamat3");
//                    alamat4=getContext().getRequest().getParameter("permohonanPihak.alamat.alamat4");
//                    alamat = new Alamat();
//                    alamat.setAlamat1(alamat1);
//                    alamat.setAlamat2(alamat2);
//                    alamat.setAlamat3(alamat3);
//                    alamat.setAlamat4(alamat4);
//                    alamat.setPoskod(getContext().getRequest().getParameter("permohonanPihak.alamat.poskod"));
//                    alamat.setNegeri(kodNegeriDAO.findById("04"));
//                    permohonanPihak.setHakmilik(hakmilikPermohonan.getHakmilik());
//                    permohonanPihak.setAlamat(alamat);
//                    permohonanPihak.setJenis(kodPBTDAO.findById("PM"));
//                    permohonanPihak.setCawangan(caw);
//                    permohonanPihak.setInfoAudit(ia);
//                    permohonanPihak.setNama(getContext().getRequest().getParameter("permohonanPihak.nama"));
//                    permohonanPihak.setJenisPengenalan(kodJenisPengenalanDAO.findById(getContext().getRequest().getParameter("permohonanPihak.jenisPengenalan.kod")));
//                    permohonanPihak.setNoPengenalan(getContext().getRequest().getParameter("permohonanPihak.noPengenalan"));
//                    permohonanPihak.setKaitan(getContext().getRequest().getParameter("permohonanPihak.kaitan"));
//                    aduanService.simpanPP(permohonanPihak);
//                    }
//
//
//
//
//
//                }
//                else {
                    if(permohonanPihak == null)
                    {
                    logger.debug("tiada wakil :: permohonanPihak :: null");
                    permohonanPihak = new PermohonanPihak();
                    permohonanPihak.setPermohonan(permohonan);
                    permohonanPihak.setPihak(phk);
                    logger.debug("phk.getAlamat1()" + phk.getAlamat1());
                    alamat = new Alamat();
                    alamat.setAlamat1(phk.getAlamat1());
                    alamat.setAlamat2(phk.getAlamat2());
                    alamat.setAlamat3(phk.getAlamat3());
                    alamat.setAlamat4(phk.getAlamat4());
                    alamat.setPoskod(phk.getPoskod());
//                    kodNegeri=phk.getNegeri().getKod();
//                    if(kodNegeri !=null){
//                    alamat.setNegeri(kodNegeriDAO.findById(kodNegeri));
//                    }else{
                    alamat.setNegeri(kodNegeriDAO.findById("04"));
//                    }
                    permohonanPihak.setHakmilik(hakmilikPermohonan.getHakmilik());
                    permohonanPihak.setAlamat(alamat);
                    permohonanPihak.setJenis(kodPBTDAO.findById("PM"));
                    permohonanPihak.setCawangan(caw);
                    permohonanPihak.setInfoAudit(ia);
                    aduanService.simpanPP(permohonanPihak);

                }
//                }
                logger.debug(":: PERMOHONAN PIHAK END ::");


                logger.debug(":: HAKMILIK PERMOHONAN START ::");
                if (selectedMH != null) {
                    logger.debug("hakmilikPermohonan :: 1");
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedMH));
                    if (hakmilikPermohonan != null) {
                        logger.debug("hakmilikPermohonan :: 2");
                        hakP = pembetulanService.findByIdPermohonan(idPermohonan);
                        if (hakP == null) {
                            logger.debug("hakmilikPermohonan :: 3");
                            hakP = new HakmilikPermohonan();
                            hakP.setHakmilik(hakmilikPermohonan.getHakmilik());
                            hakP.setPermohonan(permohonan);
                            hakP.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                            hakP.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
                            hakP.setNoLot(hakmilikPermohonan.getNoLot());
                            hakP.setInfoAudit(ia);
                            hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hakP);
                        }
                    }
                }
                logger.debug(":: HAKMILIK PERMOHONAN END ::");
//        saveToSession(ctx);
                addSimpleMessage("ID Aduan : " + idPermohonan);
            }
        }

        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_dokumen_tambahan.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukanAduan.jsp");

    }

    public Resolution addDocForm() {
        logger.debug("addDocForm");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
            logger.debug("kandunganFolderTamb.size = 0");
//            for (int i = 0; i < 3; i++) {
            KandunganFolder kf = new KandunganFolder();
            kandunganFolderTamb.add(kf);
//            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/upload_doc.jsp").addParameter("popup", "true");
    }

    //simpan semua data
    public Resolution simpan() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
//        tarikhLahirOKS = hari + "/" + bulan + "/" + tahun;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            permohonan.setInfoAudit(ia);
            if (permohonan.getFolderDokumen() == null) {
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk(permohonan.getIdPermohonan());
                fd.setInfoAudit(ia);
                folderDAO.save(fd);
                permohonan.setFolderDokumen(fd);
            }
            permohonanDAO.save(permohonan);

            logger.debug("kodUrusan :" + permohonan.getKodUrusan().getKod());
            logger.debug("kodUrusan temp:" + permohonan.getRujukanUndang2());

//            aduanLokasi.setInfoAudit(ia);
//            aduanLokasiDAO.save(aduanLokasi);

            if (permohonan.getKodUrusan().getKod().equals("SEK4A")) {
                kodUrusan = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
                if (kodUrusan.getKePTG() == 'Y') {

                    WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                            idPermohonan, pengguna.getKodCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                            kodUrusan.getNama());
                } else if (kodUrusan.getKePTG() == 'T') {
                    WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                            idPermohonan, pengguna.getKodCawangan().getKod(), pengguna.getIdPengguna(),
                            kodUrusan.getNama());
                }
            }

            UrusanValue uv = new UrusanValue();
            uv.setKodUrusan(kodUrusan.getKod());
            uv.setIdPermohonan(idPermohonan);
            senaraiPermohonan.add(uv);
            tx.commit();

            //generate report for Terimaan Aduan
            long idFolder = permohonan.getFolderDokumen().getFolderId();
            String documentPath = conf.getProperty("document.path");
            Dokumen resit = new Dokumen();
            resit.setFormat("application/pdf");
            resit.setInfoAudit(ia);
            resit.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            KodDokumen kodResit = kodDokumenDAO.findById("RAP");
            resit.setKodDokumen(kodResit);
            resit.setNoVersi("1.0");
            logger.info(kodResit.getNama());
            resit.setTajuk(kodResit.getNama());
            resit.setPerihal("T");//save temp as flag at sedia_laporan1 (sek999)
            resit = dokumenDAO.saveOrUpdate(resit);
            logger.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            idDokumen = resit.getIdDokumen();
            logger.info("ID DOKUMEN: " + idDokumen);
            String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
            String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("ENFAPA_MLK2.rdf",
                        new String[]{"p_id_mohon"},
                        new String[]{permohonan.getIdPermohonan()},
                        path + path2, pengguna);
            } else {
                reportUtil.generateReport("ENFAPA_NS.rdf",
                        new String[]{"p_id_mohon"},
                        new String[]{permohonan.getIdPermohonan()},
                        path + path2, pengguna);
            }
            logger.info("NAMA FIZIKAL TO BE SET AT DOKUMEN : " + reportUtil.getDMSPath());
            resit.setNamaFizikal(reportUtil.getDMSPath());
            getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
            dokumenService.saveOrUpdate(resit);
            System.out.println("after update fizikal name : " + resit.getNamaFizikal());

            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(resit);
            kf.setFolder(folderDokumenDAO.findById(idFolder));
            kf.setInfoAudit(ia);
            kf.setCatatan("");
            dokumenService.saveKandunganWithDokumen(kf);


            addSimpleMessage("Maklumat telah berjaya disimpan dan dokumen kemasukkan aduan telah dijana.");

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        } finally {
//            resetAll();
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/tamat.jsp");
    }

    public Resolution genReport() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("genReport :: start");
        logger.info("generate report start.");
//        String msg = "Laporan telah dijana. Sila Klik Butang Papar dan Cetak.";
        String gen = "";
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            logger.info("NS");
            gen = "ENFAPA_NS.rdf";
        } else {
            logger.info("MLK");
            gen = "ENFAPA_MLK2.rdf";
        }
        String code = "RAP";
        logger.info("idPermohonan : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        try {
            logger.info("genReportFromXML");
            Dokumen doc = lelongService.reportGen2(permohonan, gen, code, pengguna);
            idDokumen = doc.getIdDokumen();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        logger.info("genReport :: finish");
        logger.info("idDokumen : " + idDokumen);
        listMohon = new ArrayList<Permohonan>();
        listMohon.add(permohonan);
        logger.info("listMohon : " + listMohon.size());
        addSimpleMessage("Aduan yang diterima telah dijana. Sila Klik Butang Papar Aduan untuk  semak dan cetak aduan yang telah dijana.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/tamat.jsp");
    }

    public Resolution refreshpage() {
        idMH = getContext().getRequest().getParameter("idMH");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_aduan_details.jsp");
    }

    public Resolution refreshpage2() {
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution seterus() {

//        selectedMH = getContext().getRequest().getParameter("selectedMH");
        logger.info("Radio : " + selectedMH);
        logger.info("Seterusnya");

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedMH));

        if (hakmilikPermohonan != null) {
            logger.info("HakmilikPermohonan != null");

            logger.debug("hp punye idpermohonan " + hakmilikPermohonan.getPermohonan().getIdPermohonan());
            pSebelum = permohonanDAO.findById(hakmilikPermohonan.getPermohonan().getIdPermohonan());

        }
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_aduan_details.jsp");
    }

    public Resolution simpanDokumenTambahan() {
        logger.debug("simpanDokumenTambahan");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        String result = "fail";
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        if (folderDokumen != null) {
            akf = folderDokumen.getSenaraiKandungan();
        } else {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonan.getIdPermohonan());

            permohonan.setFolderDokumen(fd);
            folderDokumen = permohonan.getFolderDokumen();
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            KandunganFolder f = kandunganFolderTamb.get(0);
            Dokumen d = f.getDokumen();
            String c = f.getCatatan();

            KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());

            if ((kd != null && !kd.getKod().equals("0"))
                    || (c != null && c.trim().length() > 0)) {
                d.setInfoAudit(ia);
                KodDokumen kDok = kodDokumenDAO.findById(kd.getKod());
                d.setTajuk(kd == null ? "KIV" : kDok.getNama());
                d.setNoVersi("1.0");
                d.setKodDokumen(kd);
                d.setKlasifikasi(klasifikasiAm);
                dokumenDAO.save(d);
                f.setFolder(folderDokumen);
                f.setInfoAudit(ia);
                f.setDokumen(d);
                akf.add(f);
            }
            if (akf.size() > 0) {
                folderDokumen.setSenaraiKandungan(akf);
            }

            folderDokumen.setInfoAudit(ia);
            folderDAO.save(folderDokumen);
//            kandunganFolderDAO.save(f);
            tx.commit();
            result = "success";
//            permohonan.setFolderDokumen(folderDokumen);
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        logger.info("simpanDokumen::finish");
        return new StreamingResolution("text/plain", result);
//        return new RedirectResolution(AduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public Resolution reload() {
        logger.info("----reload----");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        if (folderDokumen != null) {
            if (folderDokumen.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                    kf.setDokumen(d);
                    senaraiKandungan.add(kf);
                }
            }
        }
        addSimpleMessage("ID Aduan : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_dokumen_tambahan.jsp").addParameter("popup", "true");

    }

    public Resolution reload2() {
        logger.info("----reload----");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_dokumen_tambahan.jsp");

    }

    public Resolution deleteSelected() {
        logger.debug("deleteSelected");
        String[] ids = getContext().getRequest().getParameterValues("chkbox");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Long id1 = Long.parseLong(id);
                Dokumen dok1 = dokumenDAO.findById(id1);
                dokumenDAO.delete(dok1);

                List<KandunganFolder> akf = folderDokumen.getSenaraiKandungan();
                for (Iterator<KandunganFolder> it = akf.iterator(); it.hasNext();) {
                    KandunganFolder fd1 = it.next();
                    if (id1 == fd1.getDokumen().getIdDokumen()) {
                        it.remove();
                        break;
                    }
                }
                folderDokumen.setSenaraiKandungan(akf);
            }
            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());


        }
        return new RedirectResolution(AduanActionBean.class, "reload2").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public Resolution muatNaikForm1() {
        logger.info("--start muatNaikForm1--");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);



        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            permohonan = permohonanDAO.findById(idPermohonan);
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            KodDokumen kodDokumen = kodDokumenDAO.findById(dokumenKod);
            Dokumen d = new Dokumen();
            d.setInfoAudit(ia);
            d.setTajuk(kodDokumen.getNama());
            d.setNoVersi("1.0");
            d.setKodDokumen(kodDokumen);
            d.setKlasifikasi(klasifikasiAm);

            KandunganFolder kf = new KandunganFolder();
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setDokumen(d);
            kf.setInfoAudit(ia);

            Transaction tx = sessionProvider.get().getTransaction();
            tx.begin();
            d = dokumenDAO.saveOrUpdate(d);
            kandunganFolderDAO.save(kf);

            if (dokumenKod.matches("IB")) {
//                BarangRampasan barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
//                barangRampasan.setImej(d);
//                barangRampasan.setInfoAudit(ia);
//                barangRampasanDAO.saveOrUpdate(barangRampasan);
            }
            if (dokumenKod.startsWith("I") && !dokumenKod.matches("IB")) {
//                LaporanTanah laporanTanah;
//                if (idLaporan.matches("")) {
//                    laporanTanah = new LaporanTanah();
//                    laporanTanah.setInfoAudit(ia);
//                    laporanTanah.setPermohonan(permohonan);
//                    laporanTanahDAO.saveOrUpdate(laporanTanah);
//
//                } else {
//                    laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporan));
//                }
//                ImejLaporan imejLaporan = new ImejLaporan();
//                imejLaporan.setCawangan(peng.getKodCawangan());
//                imejLaporan.setDokumen(d);
//                if (dokumenKod.length() > 2) {
//                    imejLaporan.setPandanganImej(dokumenKod.charAt(2));
//                }
//                if (StringUtils.isNotBlank(idHakmilik)) {
//                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
//                    if (hakmilik != null) {
//                        imejLaporan.setHakmilik(hakmilik);
//                    }
//                }
//                imejLaporan.setLaporanTanah(laporanTanah);
//                imejLaporan.setInfoAudit(ia);
//                imejLaporanDAO.save(imejLaporan);
            }
            if (dokumenKod.matches("KS")) {
//                PermohonanKertas permohonanKertas;
//                if (idKertas.matches("")) {
//                    permohonanKertas = new PermohonanKertas();
//                    permohonanKertas.setTajuk("Draf Kertas Siasatan");
//                    permohonanKertas.setInfoAudit(ia);
//                    permohonanKertas.setCawangan(peng.getKodCawangan());
//                    permohonanKertas.setPermohonan(permohonan);
//                } else {
//                    permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
//                }
//                permohonanKertas.setDokumen(d);
//                permohonanKertasDAO.saveOrUpdate(permohonanKertas);
            }
            if (dokumenKod.matches("LP")) {
//                PermohonanRujukanLuar permohonanRujukanLuar;
//                if (idRujukan.matches("")) {
//                    permohonanRujukanLuar = new PermohonanRujukanLuar();
//                    permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
//                    permohonanRujukanLuar.setInfoAudit(ia);
//                    permohonanRujukanLuar.setPermohonan(permohonan);
//                    KodRujukan kodRujukan = new KodRujukan();
//                    kodRujukan.setKod("NF");
//                    permohonanRujukanLuar.setKodRujukan(kodRujukan);
//                    KodAgensi kodAgensi = new KodAgensi();
//                    kodAgensi.setKod("0302");
//                    permohonanRujukanLuar.setAgensi(kodAgensi);
//                    permohonanRujukanLuar.setDokumen(d);
//                    permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
//
//                }
//                if (idRujukan.matches("")) {
//                    permohonanRujukanLuar = new PermohonanRujukanLuar();
//                    permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
//                    permohonanRujukanLuar.setInfoAudit(ia);
//                    permohonanRujukanLuar.setPermohonan(permohonan);
//                    KodRujukan kodRujukan =  new KodRujukan();
//                    kodRujukan.setKod("NF");
//                    permohonanRujukanLuar.setKodRujukan(kodRujukan);
//                    KodAgensi kodAgensi = new KodAgensi();
//                    kodAgensi.setKod("0302");
//                    permohonanRujukanLuar.setAgensi(kodAgensi);
//                } else {
//                    permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
//                }
//                permohonanRujukanLuar.setDokumen(d);
//                permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            }
            if (dokumenKod.matches("KMD")) {
//                PermohonanRujukanLuar permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
//                permohonanRujukanLuar.setDokumen(d);
//                permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            }
            if (dokumenKod.matches("LO")) {
//                if (idOperasi.matches("")) {
//                    OperasiPenguatkuasaan operasiPenguatkuasaan = new OperasiPenguatkuasaan();
//                    operasiPenguatkuasaan.setCawangan(peng.getKodCawangan());
//                    operasiPenguatkuasaan.setPermohonan(permohonan);
//                    operasiPenguatkuasaan.setInfoAudit(ia);
//                    operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
//                    operasiPenguatkuasaanDAO.save(operasiPenguatkuasaan);
//                }
            }
//            if (d != null) {
//                getContext().getRequest().setAttribute("dokumenId", d.getIdDokumen());
//                tx.commit();
//            } else {
//                tx.rollback();
//            }
            //return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }
        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }

        return new JSP("pengambilan/Aduan/muat_naik_doc.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        logger.info("simpanMuatNaik::start :processUploadDoc");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderId = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        String catatan = getContext().getRequest().getParameter("catatan");
        String result = "success";
        permohonan = permohonanDAO.findById(idPermohonan);


        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                logger.debug("content type = " + contentType);

                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {
                    System.out.println("updatePathDokumen");
                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), contentType, catatan);



                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return new JSP("pengambilan/Aduan/muat_naik_doc.jsp").addParameter("popup", "true");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return new JSP("pengambilan/Aduan/muat_naik_doc.jsp").addParameter("popup", "true");
            }
        }
        rehydrate();
        addSimpleMessage("Muat naik fail berjaya.");
//        return new RedirectResolution(AduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
//        return new StreamingResolution("text/plain", result);
        return new JSP("pengambilan/Aduan/muat_naik_doc.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null && StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution saveMohon2() {
        logger.debug("saveMohon2");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        System.out.println(":::: idPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanAduan = aduanService.findPermohonanAduanByIdMohon(idPermohonan);
            pmohon = aduanService.findPemohonByIdMohon(idPermohonan);
            if (pmohon != null) {
                if (pmohon.getPihak() != null) {
                    pihak = pihakDAO.findById(pmohon.getPihak().getIdPihak());
                    System.out.println("pihak:" + pihak.getNama());
                }
            }
            hakmilikPermohonan = aduanService.findHPByIdMohon(idPermohonan);
            logger.debug("idPermohonan" + permohonan.getIdPermohonan());


        }
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idMH = getContext().getRequest().getParameter("idMH");
        //PermohonanPihak --Pengadu
//        Pihak phk = pihakDAO.findById(Long.parseLong(selectedPihak2));


        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit audit = new InfoAudit();
        audit.setDimasukOleh(pguna);
        audit.setDiKemaskiniOleh(pguna);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());

        logger.debug(":: PERMOHONAN START ::");


        logger.debug(":: PERMOHONAN END ::");


        logger.debug(":: PERMOHONAN ADUAN START ::");
        if (permohonanAduan != null) {
            permohonanAduan.setPerihal(perihal);
            permohonanAduan.setInfoAudit(audit);
            aduanService.simpanPA(permohonanAduan);
        }

        logger.debug(":: PERMOHONAN ADUAN END ::");

        logger.debug(":: PENGADU START ::");
        logger.debug("idPermohonan Pmohon" + permohonan.getIdPermohonan());


        addSimpleMessage("Maklumat telah Berjaya dikemaskini");
//        rehydrate();
//        return new JSP("pengambilan/Aduan/kemasukan_aduan_details.jsp").addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_dokumen_tambahan.jsp");

    }

    public List<Permohonan> getMohonTolakGantung() {
        return mohonTolakGantung;
    }

    public void setMohonTolakGantung(List<Permohonan> mohonTolakGantung) {
        this.mohonTolakGantung = mohonTolakGantung;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListTaktif() {
        return hakmilikUrusanListTaktif;
    }

    public void setHakmilikUrusanListTaktif(List<HakmilikUrusan> hakmilikUrusanListTaktif) {
        this.hakmilikUrusanListTaktif = hakmilikUrusanListTaktif;
    }

    public List<HakmilikAsal> getListSejarahHakmilikAsal() {
        return listSejarahHakmilikAsal;
    }

    public void setListSejarahHakmilikAsal(List<HakmilikAsal> listSejarahHakmilikAsal) {
        this.listSejarahHakmilikAsal = listSejarahHakmilikAsal;
    }

    public List<HakmilikSebelum> getListSejarahHakmilikSebelum() {
        return listSejarahHakmilikSebelum;
    }

    public void setListSejarahHakmilikSebelum(List<HakmilikSebelum> listSejarahHakmilikSebelum) {
        this.listSejarahHakmilikSebelum = listSejarahHakmilikSebelum;
    }

    public String getIdHakmilikPihakBerkepentingan() {
        return idHakmilikPihakBerkepentingan;
    }

    public void setIdHakmilikPihakBerkepentingan(String idHakmilikPihakBerkepentingan) {
        this.idHakmilikPihakBerkepentingan = idHakmilikPihakBerkepentingan;
    }

    public List<HakmilikAsal> getListHakmilikAsal() {
        return listHakmilikAsal;
    }

    public void setListHakmilikAsal(List<HakmilikAsal> listHakmilikAsal) {
        this.listHakmilikAsal = listHakmilikAsal;
    }

    public List<HakmilikSebelum> getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(List<HakmilikSebelum> listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<Akaun> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public List<Map<String, String>> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<Map<String, String>> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public List<Map<String, String>> getSenaraiUrusanProses() {
        return senaraiUrusanProses;
    }

    public void setSenaraiUrusanProses(List<Map<String, String>> senaraiUrusanProses) {
        this.senaraiUrusanProses = senaraiUrusanProses;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public BigDecimal getAmaunRemesyen() {
        return amaunRemesyen;
    }

    public void setAmaunRemesyen(BigDecimal amaunRemesyen) {
        this.amaunRemesyen = amaunRemesyen;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }

    public BigDecimal getJumDenda() {
        return jumDenda;
    }

    public void setJumDenda(BigDecimal jumDenda) {
        this.jumDenda = jumDenda;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getW() {
        return w;
    }

    public void setW(BigDecimal w) {
        this.w = w;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public HakmilikPihakBerkepentingan getHpk() {
        return hpk;
    }

    public void setHpk(HakmilikPihakBerkepentingan hpk) {
        this.hpk = hpk;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public List<HakmilikUrusan> getHakmilikUrusanProsesList() {
        return hakmilikUrusanProsesList;
    }

    public void setHakmilikUrusanProsesList(List<HakmilikUrusan> hakmilikUrusanProsesList) {
        this.hakmilikUrusanProsesList = hakmilikUrusanProsesList;
    }

    public List<Map<String, String>> getSenaraiUrusanProsesDistinct() {
        return senaraiUrusanProsesDistinct;
    }

    public void setSenaraiUrusanProsesDistinct(List<Map<String, String>> senaraiUrusanProsesDistinct) {
        this.senaraiUrusanProsesDistinct = senaraiUrusanProsesDistinct;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganListAktif() {
        return hakmilikPihakKepentinganListAktif;
    }

    public void setHakmilikPihakKepentinganListAktif(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListAktif) {
        this.hakmilikPihakKepentinganListAktif = hakmilikPihakKepentinganListAktif;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganListTakAktif() {
        return hakmilikPihakKepentinganListTakAktif;
    }

    public void setHakmilikPihakKepentinganListTakAktif(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganListTakAktif) {
        this.hakmilikPihakKepentinganListTakAktif = hakmilikPihakKepentinganListTakAktif;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getJenisLot() {
        return jenisLot;
    }

    public void setJenisLot(String jenisLot) {
        this.jenisLot = jenisLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public int getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(int kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getNamaBPM() {
        return namaBPM;
    }

    public void setNamaBPM(String namaBPM) {
        this.namaBPM = namaBPM;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public List<HakmilikPihakBerkepentingan> getL() {
        return l;
    }

    public void setL(List<HakmilikPihakBerkepentingan> l) {
        this.l = l;
    }

    public Pihak getPemegang() {
        return pemegang;
    }

    public void setPemegang(Pihak pemegang) {
        this.pemegang = pemegang;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public void setAkaunKredit(Akaun akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    public List<SejarahTransaksi> getSejarahList() {
        return sejarahList;
    }

    public void setSejarahList(List<SejarahTransaksi> sejarahList) {
        this.sejarahList = sejarahList;
    }

    public String getPegang() {
        return pegang;
    }

    public void setPegang(String pegang) {
        this.pegang = pegang;
    }

    public Transaksi getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Transaksi idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public SejarahDokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(SejarahDokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }

    public String getNoPengenalanP() {
        return noPengenalanP;
    }

    public void setNoPengenalanP(String noPengenalanP) {
        this.noPengenalanP = noPengenalanP;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public BigDecimal getAmaunDenda() {
        return amaunDenda;
    }

    public void setAmaunDenda(BigDecimal amaunDenda) {
        this.amaunDenda = amaunDenda;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonanPemilikan() {
        return permohonanPemilikan;
    }

    public void setPermohonanPemilikan(Permohonan permohonanPemilikan) {
        this.permohonanPemilikan = permohonanPemilikan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    /**
     * @return the kodStatusHakmilik
     */
    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    /**
     * @param kodStatusHakmilik the kodStatusHakmilik to set
     */
    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    /**
     * @return the jumCukai
     */
    public BigDecimal getJumCukai() {
        return jumCukai;
    }

    /**
     * @param jumCukai the jumCukai to set
     */
    public void setJumCukai(BigDecimal jumCukai) {
        this.jumCukai = jumCukai;
    }

    /**
     * @return the a
     */
    public BigDecimal getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(BigDecimal a) {
        this.a = a;
    }

    /**
     * @return the amaunDebit
     */
    public BigDecimal getAmaunDebit() {
        return amaunDebit;
    }

    /**
     * @param amaunDebit the amaunDebit to set
     */
    public void setAmaunDebit(BigDecimal amaunDebit) {
        this.amaunDebit = amaunDebit;
    }

    /**
     * @return the totalAmaunDebit
     */
    public BigDecimal getTotalAmaunDebit() {
        return totalAmaunDebit;
    }

    /**
     * @param totalAmaunDebit the totalAmaunDebit to set
     */
    public void setTotalAmaunDebit(BigDecimal totalAmaunDebit) {
        this.totalAmaunDebit = totalAmaunDebit;
    }

    /**Tulasi**/
    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
    String st;

    public Boolean getDatun() {
        return datun;
    }

    public void setDatun(Boolean datun) {
        this.datun = datun;
    }
    private Boolean datun = false;

    public DasarTuntutanCukaiHakmilik getDasarTuntutan() {
        return dasarTuntutan;
    }

    public void setDasarTuntutan(DasarTuntutanCukaiHakmilik dasarTuntutan) {
        this.dasarTuntutan = dasarTuntutan;
    }

    public List<DasarTuntutanCukaiHakmilik> getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(List<DasarTuntutanCukaiHakmilik> dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public DasarTuntutanNotis getDasarTuntutanNotis() {
        return dasarTuntutanNotis;
    }

    public void setDasarTuntutanNotis(DasarTuntutanNotis dasarTuntutanNotis) {
        this.dasarTuntutanNotis = dasarTuntutanNotis;
    }
    private DasarTuntutanNotis dasarTuntutanNotis;

    /**
     * @return the kumpulan
     */
    public KumpulanAkaun getKumpulan() {
        return kumpulan;
    }

    /**
     * @param kumpulan the kumpulan to set
     */
    public void setKumpulan(KumpulanAkaun kumpulan) {
        this.kumpulan = kumpulan;
    }

    /**
     * @return the list
     */
    public List<Akaun> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Akaun> list) {
        this.list = list;
    }

    /**
     * @return the del
     */
    public boolean isDel() {
        return del;
    }

    /**
     * @param del the del to set
     */
    public void setDel(boolean del) {
        this.del = del;
    }

    /**
     * @return the senaraiCaraBayaran
     */
    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    /**
     * @param senaraiCaraBayaran the senaraiCaraBayaran to set
     */
    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the bil
     */
    public int getBil() {
        return bil;
    }

    /**
     * @param bil the bil to set
     */
    public void setBil(int bil) {
        this.bil = bil;
    }

    /**
     * @return the button
     */
    public boolean isButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(boolean button) {
        this.button = button;
    }

    public List<Hakmilik> getSenaraiHakmilikBerikut() {
        return senaraiHakmilikBerikut;
    }

    public void setSenaraiHakmilikBerikut(List<Hakmilik> senaraiHakmilikBerikut) {
        this.senaraiHakmilikBerikut = senaraiHakmilikBerikut;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakKepentinganList() {
        return hakmilikPihakKepentinganList;
    }

    public void setHakmilikPihakKepentinganList(List<HakmilikPihakBerkepentingan> hakmilikPihakKepentinganList) {
        this.hakmilikPihakKepentinganList = hakmilikPihakKepentinganList;
    }

    public List<HakmilikPermohonan> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<HakmilikPermohonan> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public KodUrusan getKod() {
        return kod;
    }

    public void setKod(KodUrusan kod) {
        this.kod = kod;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public PermohonanAduan getPermohonanAduan() {
        return permohonanAduan;
    }

    public void setPermohonanAduan(PermohonanAduan permohonanAduan) {
        this.permohonanAduan = permohonanAduan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public boolean isCarianPermohonan() {
        return carianPermohonan;
    }

    public void setCarianPermohonan(boolean carianPermohonan) {
        this.carianPermohonan = carianPermohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Pemohon getPmohon() {
        return pmohon;
    }

    public void setPmohon(Pemohon pmohon) {
        this.pmohon = pmohon;
    }

    public KodBandarPekanMukim getBandarPekanMukim1() {
        return bandarPekanMukim1;
    }

    public void setBandarPekanMukim1(KodBandarPekanMukim bandarPekanMukim1) {
        this.bandarPekanMukim1 = bandarPekanMukim1;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public GenerateIdPerserahanWorkflow getGenerateIdPerserahanWorkflow() {
        return generateIdPerserahanWorkflow;
    }

    public void setGenerateIdPerserahanWorkflow(GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow) {
        this.generateIdPerserahanWorkflow = generateIdPerserahanWorkflow;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public GeneratorIdPermohonan getIdPermohonanGenerator() {
        return idPermohonanGenerator;
    }

    public void setIdPermohonanGenerator(GeneratorIdPermohonan idPermohonanGenerator) {
        this.idPermohonanGenerator = idPermohonanGenerator;
    }

    public String getKodUrusanTemp() {
        return kodUrusanTemp;
    }

    public void setKodUrusanTemp(String kodUrusanTemp) {
        this.kodUrusanTemp = kodUrusanTemp;
    }

    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(ArrayList<UrusanValue> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public HakmilikPermohonan getHakP() {
        return hakP;
    }

    public void setHakP(HakmilikPermohonan hakP) {
        this.hakP = hakP;
    }

    public List<String> getSelectedPihak() {
        return selectedPihak;
    }

    public void setSelectedPihak(List<String> selectedPihak) {
        this.selectedPihak = selectedPihak;
    }

    public String getSelectedMH() {
        return selectedMH;
    }

    public void setSelectedMH(String selectedMH) {
        this.selectedMH = selectedMH;
    }

    public Permohonan getpSebelum() {
        return pSebelum;
    }

    public void setpSebelum(Permohonan pSebelum) {
        this.pSebelum = pSebelum;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public String getDokumenKod() {
        return dokumenKod;
    }

    public void setDokumenKod(String dokumenKod) {
        this.dokumenKod = dokumenKod;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getKodNegeriAlamat() {
        return kodNegeriAlamat;
    }

    public void setKodNegeriAlamat(String kodNegeriAlamat) {
        this.kodNegeriAlamat = kodNegeriAlamat;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getSelectedPihak2() {
        return selectedPihak2;
    }

    public void setSelectedPihak2(String selectedPihak2) {
        this.selectedPihak2 = selectedPihak2;
    }

    public String getIdPihakVal() {
        return idPihakVal;
    }

    public void setIdPihakVal(String idPihakVal) {
        this.idPihakVal = idPihakVal;
    }

    public String getIdPermohonanBaru() {
        return idPermohonanBaru;
    }

    public void setIdPermohonanBaru(String idPermohonanBaru) {
        this.idPermohonanBaru = idPermohonanBaru;
    }

    public KodNegeri getKodN() {
        return kodN;
    }

    public void setKodN(KodNegeri kodN) {
        this.kodN = kodN;
    }


    public PermohonanPihakHubungan getPermohonanPihakHubungan() {
        return permohonanPihakHubungan;
    }

    public void setPermohonanPihakHubungan(PermohonanPihakHubungan permohonanPihakHubungan) {
        this.permohonanPihakHubungan = permohonanPihakHubungan;
    }

    public String getSelectedWakil() {
        return selectedWakil;
    }

    public void setSelectedWakil(String selectedWakil) {
        this.selectedWakil = selectedWakil;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }
}
