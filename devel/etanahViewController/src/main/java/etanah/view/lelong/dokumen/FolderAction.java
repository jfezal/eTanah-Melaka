package etanah.view.lelong.dokumen;

import com.google.inject.Inject;

import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Permohonan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.manager.TabManager;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.Dokumen;
import etanah.dao.EnkuiriDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.DokumenCapaian;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Peguam;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanCarian;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.SytJuruLelong;
import etanah.model.Transaksi;
import etanah.service.HakmilikService;
import etanah.service.KaunterService;
import etanah.service.LaporanTanahService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.service.common.PermohonanCarianService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.GenerateIdPermohonanWorkflow;
import etanah.workflow.WorkFlowService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.*;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mazurahayati.yusop, nur.aqilah
 */
@UrlBinding("/lelong/dokumen/folder")
public class FolderAction extends AbleActionBean {

    @Inject
    private LaporanTanahService laporanTanahService;
    @Inject
    private ImejLaporanDAO imejLaporanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    private KodKlasifikasiDAO kodKlasDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodStatusPermohonanDAO kodStatusPermohonanDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    private KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenService dokumenService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    private EnkuiriDAO enkuiriDAO;
    @Inject
    private NotisDAO notisDAO;
    @Inject
    private LelongService lelongService;
    @Inject
    private EnkuiriService enkuiriService;
    @Inject
    private PembetulanService pService;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarHakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    EnkuiriService ES;
    @Inject
    PermohonanBaruLelongService permohonanBLS;
    @Inject
    PembidaDAO pembidaDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanService permohonanService;
    @Inject
    private TabManager tm;
    private ArrayList<Pembida> pembidaList = new ArrayList<Pembida>();
    private Pembida pembida;
    private Pembida pembida2;
    private SytJuruLelong sytJuruLelong;
    private List<AkuanTerimaDeposit> listATD;
    private List<AkuanTerimaDeposit> listATD2;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2;
    private List<HakmilikPermohonan> addPermohonan = new ArrayList<HakmilikPermohonan>();
    private List<HakmilikUrusan> addCheckUrusan;
    private List<HakmilikPermohonan> getIdMohon;
    private List<HakmilikPermohonan> getIdMohon2;
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar;
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2;
    private List<Permohonan> addListMohon = new ArrayList<Permohonan>();
    private List<Permohonan> checkUrusanMohon;
    private List<Lelongan> listLel1;
    private List<Lelongan> senaraiPembeli;
    private List<Pembida> senaraiPembida2;
    private List<Pembida> senaraiPembida;
    private List<Pembida> checkStatusPembida;
    private List<Pembida> checkStatusPembida2;
    private List<Pembida> checkListStatusPembida = new ArrayList<Pembida>();
    private List<Pembida> checkListStatusPembida2 = new ArrayList<Pembida>();
    private List<Enkuiri> senaraiEnkuiri;
    private List<FasaPermohonan> checkIdAliran;
    private List<Permohonan> getCatatan;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private Pengguna pengguna;
    private Permohonan p;
    private KandunganFolder kandunganFolder;
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Kehadiran> senaraiKehadiran = new ArrayList<Kehadiran>();
    private List<Notis> senaraiNotis = new ArrayList<Notis>();
    private List<Notis> senaraiNotis2 = new ArrayList<Notis>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri2;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<PermohonanCarian> senaraiPermohonanCarian2;
    private static Logger LOG = Logger.getLogger(FolderAction.class);
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    private static boolean isDebug = LOG.isDebugEnabled();
    private BigDecimal deposit;
    private String kodBank2;
    private AkuanTerimaDeposit atd;
    private AkuanTerimaDeposit atd2;
    private Pihak pihak;
    private BigDecimal hargaBidaan2;
    private BigDecimal deposit2;
    private BigDecimal baki;
    private String kodCareBayar;
    private String kodCareBayar2;
    private String kodBank;
    Permohonan permohonan;
    private Permohonan permohonan1;
    private boolean show16H;
    private boolean showForm;
    private boolean showForm1;
    private Peguam peguam;
    private String idEnkuiri;
    private Enkuiri enkuiri;
    private Lelongan lelong;
    private Enkuiri enkuiri1;
    private Enkuiri enkuiri2;
    private String status;
    private String participant;
    private String stage;
    private String idPermohonan;
    private String idDokumen;
    private String idFolder;
    private String folderdokumen;
    private String idSebelum;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhDokumen;
    private Dokumen dokumen;
    private Hakmilik hakmilik;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<Kehadiran> kehadiran;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private Hakmilik h;
    private String ks;
    private HakmilikPermohonan hp;
    private KodUrusan kodUrusan;
    private List<FasaPermohonan> listFasa;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonanLog fasaPermohonanLogs;
    TabManager tabManager;
    FasaPermohonanDAO fasaPermohonanDAO;
    FasaPermohonanService fasaPermohonanManager;
    KodCawanganDAO kodCawanganDAO;
    PermohonanService permohonanManager;
    private String ulasan;
    private String keputusan;
    private KodStatusPermohonan kodStatusPermohonan;
    private String urusan;
    private KodDokumen kodDokumen;
    private boolean smk = false;
    private boolean showPopupPembeli = false;
    private boolean showPopupPembidaBJ = false;
    private String idHakmilik;
    private String idNotis;
    private String idPerserahan;
    private String idPerserahan2;
    private String idPerserahan3;
    private List<Permohonan> senaraiPermohonan;
    private List<Permohonan> senaraiPermohonan2;
    private List<Lelongan> senaraiIdLelong;
    public String selectedTab = "0";
    private boolean melaka = true;

    @Inject
    public FolderAction(FasaPermohonanDAO fasaPermohonanDAO, PermohonanDAO permohonanDAO,
            KodCawanganDAO kodCawanganDAO, TabManager tabManager, PermohonanService permohonanManager,
            FasaPermohonanService fasaPermohonanManager) {
        this.fasaPermohonanDAO = fasaPermohonanDAO;
        this.permohonanDAO = permohonanDAO;
        this.kodCawanganDAO = kodCawanganDAO;
        this.tabManager = tabManager;
        this.permohonanManager = permohonanManager;
        this.fasaPermohonanManager = fasaPermohonanManager;
    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("default handler");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }
    
    public Resolution showForm1() {
        LOG.info("default handler");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    public Resolution showFormA() {
        LOG.info("tab keputusan FolderAB");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/tab_keputusan_upload.jsp");
    }

    //save folder info
    public Resolution saveFolderInfo() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        try {
            folderDAO.saveOrUpdate(folderDokumen);
            tx.commit();
            addSimpleMessage(
                    "Kemasukan Data Berjaya.");

        } catch (Exception ex) {
            addSimpleError("Kemasukan Data Gagal." + ex.getMessage());
            tx.rollback();
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    //added by nur.aqilah
    //search id mohon by willcard
    public Resolution find() {
        getLOG().info("rehydrate - start");
        getLOG().info("idPermohonan : " + idPermohonan);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (isIS_DEBUG()) {
            getLOG().debug("======= page: " + page);
        }
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
            getLOG().debug("======= StringUtils is not blank ");
        }

        if (StringUtils.isBlank(idPermohonan)) {
            LOG.debug("======= idPemohon is EMPTY: ");

            setListT(hakmilikService.findMohon(getContext().getRequest().getParameterMap()));
            for (Transaksi t : getListT()) {

                if (t.getPermohonan() != null) {
                    String idMohon = t.getPermohonan().getIdPermohonan();
                    idPermohonan = idMohon;
                } else {
                    addSimpleError("Tiada Maklumat dijumpai.");
                }
            }
        }

        if (IS_DEBUG) {
            LOG.debug("====== idPermohonan: " + idPermohonan);
        }

        if ((StringUtils.isNotBlank(idPermohonan))) {

            if (idPermohonan != null) {
                LOG.info("MASUK idPermohonan!=null");
                permohonan = getPermohonanDAO().findById(idPermohonan);

                if (permohonan == null) {
                    showForm = true;
                } else {
                    showForm = false;
                }
//                
//                String tindakan = "";
//
//                tindakan = tm.getCurrentAction(permohonan.getKodUrusan().getIdWorkflow(), getStage());
//                LOG.debug("Tindakn " + tindakan);

                //id mohon not exist / search by wildcard
                if (permohonan == null) {

                    permohonan = null;

                    LOG.info("MASUK permohonan==null");

                    senaraiPermohonan = lelongService.getSenaraiPermohonan(idPermohonan, pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());

                    LOG.info("senaraiPermohonan " + senaraiPermohonan.size());
                    set__pg_total_records(lelongService.getTotalRecordFolderAction(idPermohonan, pengguna.getKodCawangan().getKod()).intValue());

                    //find in carian
                    if (senaraiPermohonan.isEmpty()) {
                        setSenaraiPermohonanCarian(lelongService.getSenaraiPermohonanCarian(idPermohonan,
                                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records()));

                        LOG.info("senaraiPermohonanCarian " + senaraiPermohonanCarian.size());
                    }
                    if (senaraiPermohonan.isEmpty()
                            && getSenaraiPermohonanCarian().isEmpty()) {
                        LOG.error("senaraiPermohonan tiada");
                        addSimpleError("Tiada Maklumat dijumpai.");
                    }
                    //id mohon exist
                } else {
                    search();
                }
            }
        }

        LOG.info("Rehydrate - finish");
        return new JSP("/lelong/dokumen/folder.jsp");
    }

    //added by nur.aqilah
    //search id mohon
    public Resolution search() {

        LOG.info("MASUK SEARCH");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        
            if (StringUtils.isNotBlank(idPermohonan)) {
                permohonan = getPermohonanDAO().findById(idPermohonan);
                
                //check kod_caw for table pengguna & permohonan
                if(!pengguna.getKodCawangan().getKod().equals( permohonan.getCawangan().getKod()) || pengguna.getKodCawangan().getKod().equals("00") || permohonan.getCawangan().getKod().equals("00")){
                    addSimpleError("ID Permohonan Tidak Wujud");
                }else{
              
                    if (permohonan != null) {
                        LOG.debug("MASUK ID !=NULL");
                        LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
                        LOG.info("tajuk :" + permohonan.getFolderDokumen().getTajuk());

                    if (permohonan != null) {
                        showForm1 = true;
                    } else {
                        showForm1 = false;
                    }

                    List<Task> l;
                    try {
                        l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

                        for (Task t : l) {
                            setStage(t.getSystemAttributes().getStage());
                            setParticipant(t.getSystemAttributes().getAcquiredBy());
                        }
                    } catch (WorkflowException ex) {
                        getLOG().error(ex);
                    }
                    permohonan = getPermohonanDAO().findById(permohonan.getIdPermohonan());

                    //tambah baru
                    if (permohonan.getPermohonanSebelum() != null) {
                        enkuiri = getLelongService().findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                        setSenaraiEnkuiri2(lelongService.findEnkuiriAll2(permohonan.getPermohonanSebelum().getIdPermohonan()));
                        senaraiLelongan = lelongService.getAllLelongan2(permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        enkuiri = getLelongService().findEnkuiri2(permohonan.getIdPermohonan());
                        setSenaraiEnkuiri2(lelongService.findEnkuiriAll2(idPermohonan));

                        //editted by nur.aqilah
                        //keluarkan sejarah lelongan
                          LOG.info("id enkuiri: " + enkuiri.getIdEnkuiri());
                            LOG.info("idmohon: " + permohonan.getIdPermohonan());
                            senaraiLelongan = lelongService.getAllLelongan(enkuiri.getIdEnkuiri());
//                        senaraiLelongan = lelongService.getAllLelongan2(permohonan.getIdPermohonan());
                    }

                    //find keputusan permohonan
                    kodStatusPermohonan = kodStatusPermohonanDAO.findById(permohonan.getStatus());

                    LOG.info("size senarai lelongan: " + senaraiLelongan.size());
                    LOG.info("size senarai enkuiri2: " + senaraiEnkuiri2.size());

                    //added by nur.aqilah
                    if("04".equals(conf.getProperty("kodNegeri"))){
                        melaka = true;
                        for (Lelongan ll : getSenaraiLelongan()) {
                            lelong = ll;
                            getLOG().info("id lelong: " + ll.getIdLelong());
                            //list all pembida
                            senaraiPembida = lelongService.getListPembidaByIdLelong(lelong.getIdLelong());

                            // list pembida where their status = BJ, AT
                            checkStatusPembida2 = lelongService.checkPembidaStatus(lelong.getIdLelong());

                            for (Pembida p : senaraiPembida) {
                                LOG.info("Pembida " + p.getPihak().getNama());
                                pembida = pembidaDAO.findById(p.getIdPembida());
                                pembidaList.add(pembida);
                            }

                            for (Pembida p : checkStatusPembida2) {
                                pembida2 = p;
                                LOG.info("Pembida " + p.getPihak().getNama());
                                pembida2 = pembidaDAO.findById(p.getIdPembida());
                                LOG.debug("pembida2" + pembida2);


                                if (pembida2 != null) {
                                    LOG.info("pembida ada laaa---");
                                    checkListStatusPembida2.add(pembida2);
                                }

                            }
                        }
                    //added by syazwan
                    //show senarai pembida for N9
                    }else if ("05".equals(conf.getProperty("kodNegeri"))){
                        melaka = false;
                        //list all pembeli
                        senaraiPembeli = lelongService.getLelonganForFindAllPembida(idPermohonan);
                            
                            if(!senaraiPembeli.isEmpty()){
                                LOG.info("----lelong not null----");
                                lelong = senaraiPembeli.get(0);
                                pihak = lelong.getPembida();
                            }
                    }
                            
                    //added by nur.aqilah
                    //(boolean for popup maklumat pembida yg berjaya dan tidak bayar baki sahaja)
                    if (!checkListStatusPembida2.isEmpty()) {
                        showPopupPembidaBJ = true;
                        LOG.info("masuk popup true");
                    } else {
                        showPopupPembidaBJ = false;
                        LOG.info("masuk popup false");
                    }

                    LOG.info("checkListStatusPembida2.size()" + checkListStatusPembida2.size());
                    LOG.info("Size Pembida utk JSP " + pembidaList.size());

                    //keluarkan catatan dari table mohon sebagai ulasan jika kod status EM/LM
                    getCatatan = lelongService.getCatatan(permohonan.getIdPermohonan());
                    LOG.info("Ada EM/LM : " + getCatatan.size());

                    //keluarkan mesej if status mohon SD
                    if (permohonan.getStatus().equals("SD")) {
                        addSimpleError("Permohonan Ini Di Gantung Kerana Mempunyai Perintah Mahkamah / Perintah Larangan");

                        //keluarkan mesej if status mohon MK
                    } else if (permohonan.getStatus().equals("MK")) {
                        addSimpleError("Permohonan Telah Dibatalkan Atas Perintah Mahkamah");

                        //keluarkan mesej if status mohon RM
                    } else if (permohonan.getStatus().equals("RM")) {
                        addSimpleError("Permohonan Dirujuk Ke Mahkamah");

                        //keluarkan mesej if status mohon SL
                    } else if (permohonan.getStatus().equals("SL")) {
                        addSimpleError("Permohonan Telah Selesai");

                        //keluarkan mesej if status mohon BP
                    } else if (permohonan.getStatus().equals("BP")) {
                        addSimpleError("Permohonan Telah Dibatalkan");

                    }
                    //keluarkan mesej jika ada perintah mahkamah
                    //CONDITION 1//
                    //check idmohon ada perintah mahkamah ke tak
                    for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
                        List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());
                        setAddCheckUrusan(new ArrayList<HakmilikUrusan>());

                        for (HakmilikUrusan hu : checkUrusan) {
                            LOG.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                            getAddCheckUrusan().add(hu);
                        }
                    }

                    //id perserahan utk CONDITION 1
                    StringBuilder sb = new StringBuilder();

                    if (!addCheckUrusan.isEmpty()) {

                        for (HakmilikUrusan acu : getAddCheckUrusan()) {
                            if (acu == null) {
                                continue;
                            }

                            if (sb.length() > 0) {
                                sb.append(" , ");
                            }
                            sb.append(acu.getIdPerserahan());
                        }
                        idPerserahan = sb.toString();

                        addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + idPerserahan);
                    }

                    //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
                    //check perintah mahkamah dah daftar ke belum
                    for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

                        //check id hakmilik ada ke tak dlm table mohon rujuk luar
                        List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
                        setAddCheckPermohonanRujukanLuar(new ArrayList<PermohonanRujukanLuar>());

                        for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                            getAddCheckPermohonanRujukanLuar().add(prl);
                        }
                        LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar().size());

                        if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                            for (PermohonanRujukanLuar prl2 : getAddCheckPermohonanRujukanLuar()) {
                                LOG.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                                //check id hakmilik ada ke tak dlm table hakmilik urusan
                                List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                                addCheckDaftarHakMilikUrusan = new ArrayList<HakmilikUrusan>();
                                for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                                    addCheckDaftarHakMilikUrusan.add(hu);
                                }

                                //check ada urusan perintah mahkamah ke takk
                                if (addCheckDaftarHakMilikUrusan.isEmpty()) {

                                    //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                                    setGetIdMohon(lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik()));
                                    LOG.info("belum di daftarkn: " + getIdMohon.size());

                                }
                            }
                        }
                    }

                    StringBuilder sb2 = new StringBuilder();
                    //id perserahan utk CONDITION 2
                    if (!addCheckPermohonanRujukanLuar.isEmpty()
                            && addCheckDaftarHakMilikUrusan.isEmpty() && !getIdMohon.isEmpty()) {
                        LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                        for (HakmilikPermohonan p2 : getGetIdMohon()) {
                            if (p2 == null) {
                                continue;
                            }

                            if (sb2.length() > 0) {
                                sb2.append(" , ");
                            }
                            sb2.append(p2.getPermohonan().getIdPermohonan());
                        }
                        idPerserahan2 = sb2.toString();

                        addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan2 + " - belum didaftarkan");
                    }

                    //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
                    //check perintah mahkamah dah daftar ke belum

                    //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)
                    for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

                        //check id hakmilik ada ke tak dlm table mohon rujuk luar
                        List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
                        setAddCheckPermohonanRujukanLuar2(new ArrayList<PermohonanRujukanLuar>());

                        for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                            getAddCheckPermohonanRujukanLuar2().add(prl);
                        }
                        LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar2().size());
                        if (getAddCheckPermohonanRujukanLuar2().isEmpty()) {

                            //check id hakmilik ada ke tak dlm hak milik urusan
                            List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(pp.getHakmilik().getIdHakmilik());
                            setAddCheckDaftarHakMilikUrusan2(new ArrayList<HakmilikUrusan>());

                            for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                                getAddCheckDaftarHakMilikUrusan2().add(hu);
                            }

                            if (getAddCheckDaftarHakMilikUrusan2().isEmpty()) {
                                //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                                setGetIdMohon2(lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik()));

                                LOG.info("belum di daftarkan " + getGetIdMohon2().size());

                            }
                        }
                    }

                    StringBuilder sb3 = new StringBuilder();
                    //id perserahan utk CONDITION 3
                    if (getAddCheckPermohonanRujukanLuar2().isEmpty()
                            && getAddCheckDaftarHakMilikUrusan2().isEmpty() && !getIdMohon2.isEmpty()) {
                        LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                        for (HakmilikPermohonan p2 : getGetIdMohon2()) {
                            if (p2 == null) {
                                continue;
                            }

                            if (sb3.length() > 0) {
                                sb3.append(" , ");
                            }
                            sb3.append(p2.getPermohonan().getIdPermohonan());
                        }
                        idPerserahan3 = sb3.toString();

                        addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan3 + " - belum didaftarkan");
                    }

                    //keluarkan sejarah siasatan
                    senaraiEnkuiri = lelongService.getAllDesc(permohonan.getIdPermohonan());

                    if (permohonan.getPermohonanSebelum() == null) {
                        enkuiri = lelongService.findEnkuiri(idPermohonan);
                    } else {
                        List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (list2.isEmpty()) {
                            enkuiri = lelongService.findEnkuiri1(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (enkuiri == null) {
                                List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                                if (!list3.isEmpty()) {
                                    enkuiri = list3.get(0);
                                }
                            }
                            setSenaraiEnkuiri(lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan()));
                        } else {
                            enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            if (enkuiri == null) {
                                List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                if (!list3.isEmpty()) {
                                    enkuiri = list3.get(0);
                                }
                            }
                            setSenaraiEnkuiri(lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan()));
                        }
                    }

                    //dapatkan id permohonan bagi permohonan terdahulu
                    if (permohonan.getPermohonanSebelum() != null) {
                        setIdSebelum(permohonan.getPermohonanSebelum().getIdPermohonan());
                    }
                } else {
                    getLOG().debug("MASUK ID ==NULL");
                    addSimpleError("Id Permohonan tidak wujud");
                    return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
                }

            //keluarkan senarai kandungan dokumen
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String id = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
            String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
            if (id == null) {
                getLOG().info("-----if------");
                folderDokumen = folderDAO.findById(Long.valueOf(id));
                getLOG().info("id Folder :" + folderDokumen.getFolderId());
            } else {
                getLOG().info("-----else------");
                id = permohonan.getIdPermohonan();
                if (id != null && id.length() > 0) {
                    p = getPermohonanDAO().findById(id);
                    if (p == null) {
                        addSimpleError("ID Permohonan " + id + " tidak wujud");
                    }
                    folderDokumen = p.getFolderDokumen();
                    if (p.getPermohonanSebelum() != null) {
                        folderDokumenSebelum = p.getPermohonanSebelum().getFolderDokumen();
                        for (KandunganFolder kf : folderDokumenSebelum.getSenaraiKandungan()) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            if (kd == null) {
                                continue;
                            }
                            kodMapSebelum.put(kd.getKod(), kd.getNama());
                            if (StringUtils.isNotBlank(filter2)) {
                                if (kd.getKod().equals(filter2)) {
                                    senaraiKandunganSebelum.add(kf.getDokumen());
                                }
                            } else {
                                senaraiKandunganSebelum.add(kf.getDokumen());
                            }
                        }
                    }


                    if (p != null) {
                        senaraiKodUrusan.add(p.getKodUrusan().getKod());
                        getLOG().info("kodUrusan " + p.getKodUrusan().getKod());

                    } //for filtering purposes
                    for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                        if (kf == null || kf.getDokumen() == null) {
                            continue;
                        }
                        KodDokumen kd = kf.getDokumen().getKodDokumen();
                        if (kd != null) {
                            kodMap.put(kd.getKod(), kd.getNama());
                        }


                        if (StringUtils.isNotBlank(filter)) {
                            if (kd.getKod().equals(filter)) {
                                senaraiKandungan.add(kf);
                            }
                        } else {
                            senaraiKandungan.add(kf);
                        }

                        //for filter enable button keputusan mahkamah
                        if ("SMK".equals(kd.getKod())) {
                            smk = true;
                        }

                    }
                    LOG.info("senaraiKandungan" + senaraiKandungan.size());

                } else {
                    addSimpleError("Folder tidak ditentukan.");
                }

                    return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
                }
            }
         }

        return new JSP(
                "/lelong/dokumen/folder.jsp");
    }

    //reset id mohon
    public Resolution reset() {
        LOG.info("---nk reset---");
        permohonan = new Permohonan();
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    //view notis
    public Resolution view() {

        idNotis = context.getRequest().getParameter("idNotis");

        getLOG().info("idNotis : " + idNotis);

        List<Notis> listNS = lelongService.getNotis(Long.parseLong(idNotis));
        getLOG().info("listNS : " + listNS.size());
        if (listNS.get(0).getBuktiPenerimaan() != null) {
            idDokumen = String.valueOf(listNS.get(0).getBuktiPenerimaan().getIdDokumen());
        }
        getLOG().info("idDokumen : " + idDokumen);
        if (idDokumen == null) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }
        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        if (d.getBaru() == 'Y') {
            ia = d.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(pengguna);
            d.setInfoAudit(ia);
            d.setBaru('T');
            dokumenDAO.update(d);
        }
        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            if (f != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                if (isDebug) {
                    getLOG().debug("creating watermark..");
                }
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
                    File signFile = new File(path + ".sig");
                    if (signFile.exists()) {
                        createWatermark = false;
                    }
                }

                if (createWatermark) {
                    final InputStream is = fis;
                    final String format = d.getFormat();

                    ByteArrayOutputStream bous = FileUtil.createWaterMark(is);

                    InputStream inputStream = new ByteArrayInputStream(bous.toByteArray());

                    return new StreamingResolution(d.getFormat(), inputStream);
                }
            }
        } catch (Exception e) {
            getLOG().error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }
        getContext().getResponse().setContentType("application/octet-stream");
        return new StreamingResolution(d.getFormat(), fis);
    }

    //popup hak milik
    public Resolution viewhakmilikDetail() {
        setIdHakmilik((String) getContext().getRequest().getParameter("idHakmilik"));
        setHakmilik(pService.findHakmilik(getIdHakmilik()));
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //popup maklumat kehadiran, notis siasatan, notis 16H
    public Resolution viewRecordDetail() {

        getLOG().info("MASUK SINI viewRecordDetail");
        idEnkuiri = (String) getContext().getRequest().getParameter("idEnkuiri");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);

        checkIdAliran = lelongService.Check16H(permohonan);

        if (checkIdAliran.isEmpty()) {
            show16H = false;
        } else {
            show16H = true;
        }

        peguam = peguamDAO.findById(permohonan.getIdPenyerah());

        if (peguam != null) {
            LOG.info("Peguam Terlibat : " + peguam.getNama());
        }

        senaraiKehadiran = lelongService.getAllKehadiran(Long.parseLong(idEnkuiri));
        setSenaraiNotis(lelongService.getNotisNot16H(idPermohonan));
        setSenaraiNotis2(lelongService.getNotis16H(idPermohonan));

        getLOG().info("Size List 1: " + senaraiKehadiran.size());


        getLOG().info("Size List 2: " + getSenaraiNotis().size());

        getLOG().info("Size List 3: " + getSenaraiNotis2().size());

        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/paparan_rekod.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //popup maklumat pembida berjaya/pembeli
    public Resolution viewDetail() {
        LOG.info("---masuk view detail---");
        String idLelong = getContext().getRequest().getParameter("idLelong");
        System.out.println("id lelong : " + idLelong);

        senaraiPembida = lelongService.getPembidaListBerjayaTakByrBaki(Long.parseLong(idLelong));
        lelong = lelongService.findLelong3(idLelong);
        idPermohonan = lelong.getPermohonan().getIdPermohonan();
        System.out.println("id permohonan : " + idPermohonan);

        listLel1 = lelongService.getLelonganForFindAllPembida(idPermohonan);
        LOG.info("size listLel1: " + getListLel1().size());

        for (Lelongan lelo : listLel1) {
            LOG.info("MASUK LIST 1");
            enkuiri = lelo.getEnkuiri();
            LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());

            if (enkuiri.getCaraLelong().equals("A")) {
                LOG.info("cara lelong " + enkuiri.getCaraLelong());

                listATD = lelongService.findATDS(idPermohonan, senaraiPembida.get(0).getLelong().getIdLelong());
                listATD2 = lelongService.findATD2S(idPermohonan, senaraiPembida.get(0).getLelong().getIdLelong());
                LOG.info("senaraiPembida.get(0).getLelong().getIdLelong()" + senaraiPembida.get(0).getLelong().getIdLelong());
            }

            if (enkuiri.getCaraLelong().equals("B")) {
                LOG.info("cara lelong " + enkuiri.getCaraLelong());
                listATD = lelongService.findATDS(idPermohonan, lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("listATD" + listATD.size());

                listATD2 = lelongService.findATD2S(idPermohonan, lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("lelo.getEnkuiri().getIdEnkuiri()" + lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("listATD2" + listATD2.size());
            }

        }

        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/semak_pembida.jsp").addParameter("popup", "true");

    }

    public Resolution viewDetailN9() {
        LOG.info("---masuk view detail N9---");
        String idLelong = getContext().getRequest().getParameter("idLelong");
        System.out.println("id lelong : " + idLelong);

        lelong = lelongService.findLelong3(idLelong);

        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/semak_pembida_N9.jsp").addParameter("popup", "true");

    }

    //added by nur.aqilah
    //popup maklumat syarikat jurulelong
    public Resolution viewSyarikat() {
        LOG.info("--masuk viewSyarikat--");
        String idSytJlb = getContext().getRequest().getParameter("idSytJlb");

        SytJuruLelong sj = null;

        try {
            sj = sytJuruLelongDAO.findById(Long.parseLong(idSytJlb));
        } catch (Exception ex) {
            LOG.debug(ex);
        }

        if (sj != null) {
            sytJuruLelong = sj;
            LOG.debug("SytJuruLelong found! noPengenalan=" + sj.getNoPengenalan());

        } else {
            LOG.debug("Syarikat Jurulelong not found");
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/semak_syarikat_jurulelong.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //popup maklumat pembida 
    public Resolution viewPembida() {
        LOG.info("---masuk viewPembida---");
        String idLelong = getContext().getRequest().getParameter("idLelong");

        LOG.info("id lelong : " + idLelong);

        checkStatusPembida = lelongService.getListPembidaByIdLelong(Long.parseLong(idLelong));

        lelong = lelongService.findLelong3(idLelong);
        idPermohonan = lelong.getPermohonan().getIdPermohonan();
        System.out.println("id permohonan : " + idPermohonan);


        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/semak_pembida2.jsp").addParameter("popup", "true");

    }

    //refresh
    public Resolution refreshpage() {
        getLOG().info("refreshPage : start");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        find();
        getLOG().info("refreshPage : finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp").addParameter("popup", "true");
    }

    //reload
    public Resolution reload() {
        getLOG().info("reload");
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        getLOG().info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));

        for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
            if (kf == null || kf.getDokumen() == null) {
                continue;
            }
            KodDokumen kd = kf.getDokumen().getKodDokumen();
            if (kd != null) {
                kodMap.put(kd.getKod(), kd.getNama());
            }
            senaraiKandungan.add(kf);
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    public Resolution viewPDF() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/uploadFileApplet.jsp");

    }

    //upload dokumen tambahan
    public Resolution dokumenTambahanForm() {
        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        getLOG().info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        getLOG().info("idDokumen : " + idDokumen);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());

        getLOG().info("----------dokumenTambahanForm--------");
        getLOG().info("senaraiKodUrusan : " + senaraiKodUrusan.size());

        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i
                    < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/dokumen_tambahan.jsp");
    }

    //save edit info
    public Resolution saveEditInfo() {
        String result = "0";
        String tajuk = getContext().getRequest().getParameter("tajuk");
        String idDok = getContext().getRequest().getParameter("idDok");
        if (StringUtils.isNotBlank(idDok)) {
            Dokumen d = dokumenDAO.findById(Long.valueOf(idDok));
            if (d != null) {
                d.setTajuk(tajuk);
                dokumenService.saveOrUpdate(d);
                result = "1";
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    //simpan dokumen tambahan
    public Resolution simpanDokumenTambahan() {
        getLOG().info("simpanDokumenTambahan::start");
        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        getLOG().info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        getLOG().info("kandunganFolderTamb : " + kandunganFolderTamb.size());

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        String result = null;

        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            for (KandunganFolder f : kandunganFolderTamb) {
                if (f == null) {
                    continue;
                }

                Dokumen d = f.getDokumen();
                if (d == null || d.getKodDokumen() == null) {
                    continue;

                }
                KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());
                if (StringUtils.isBlank(kd.getKod())) {
                    continue;
                }
                String c = f.getCatatan();
                if ((kd != null && !kd.getKod().equals("0"))
                        || (c != null && c.trim().length() > 0)) {
                    d.setInfoAudit(ia);
                    d.setTajuk(kd == null ? "KIV" : kd.getNama());
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    try {
                        d.setTarikhDokumen(sdf.parse(tarikhDokumen));

                    } catch (Exception ex) {
                        getLOG().error(ex);
                    }

                    dokumenDAO.save(d);
                    f.setCatatan(c);
                    f.setFolder(folderDokumen);
                    f.setInfoAudit(ia);
                    akf.add(f);
                }
            }
            if (akf.size() > 0) {
                folderDokumen.setSenaraiKandungan(akf);
            }
            folderDAO.save(folderDokumen);

            tx.commit();
            result = "success";
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(
                    t.getMessage());
            result = "fail";
        }
        getLOG().info("simpanDokumenTambahan::finish");
        return new StreamingResolution("text/plain", result);
    }

    //seterusnya
    public Resolution seterusnya() {
        getLOG().info("------start--------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna1 = ctx.getUser();

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        getLOG().info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hp.getHakmilik());
        }
        boolean wujud = false;
        for (KandunganFolder folderKand : p.getFolderDokumen().getSenaraiKandungan()) {
            if ("SMK".equals(folderKand.getDokumen().getKodDokumen().getKod())) {
                wujud = true;
            }
        }

        KodUrusan kd = new KodUrusan();
        kd.setKod("PPTL");
        if (permohonanBLS.generateIdPermohonanWorkflow(p.getKodUrusan(), pengguna1, senaraiHakmilik, p, folderDokumen)) {
            String[] name = {"permohonanSebelum"};
            Object[] value = {p};
            Permohonan permohonanBaru = getPermohonanDAO().findByEqualCriterias(name, value, null).get(0);
            addSimpleMessage(
                    "Permohonan Berjaya. ID Permohonan : " + permohonanBaru.getIdPermohonan());
        } else {
            addSimpleError("Permohonan Gagal.");
        }
        getLOG().info("------finish--------");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    //simpan 
    public Resolution save() {
        getLOG().info("------keputusan dlm FolderAB-------");

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        getLOG().info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());
        getLOG().info("kodUrusan : " + p.getKodUrusan().getKod());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //save in mohon_fasa
        if (p != null) {

            listFasa = ES.getSenaraiFasaPermohonan(p.getIdPermohonan());
            getLOG().info("listFasa.size : " + listFasa.size());

            InfoAudit info = new InfoAudit();
            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);

            fasaPermohonan = new FasaPermohonan();

            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());

            fasaPermohonan.setInfoAudit(info);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(p);
            fasaPermohonan.setIdAliran("N/A");
            fasaPermohonan.getUlasan();
            fasaPermohonan.setUlasan(ulasan);
            fasaPermohonan.getKeputusan();
            fasaPermohonan.setKeputusan(kodKeputusan);

            ES.saveFasaMohon(fasaPermohonan);
            getLOG().info("idfasa : " + fasaPermohonan.getIdFasa());


            //save in mohon_fasa_log
            KodKeputusan kodKeputusan1 = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            fasaPermohonanLogs = new FasaPermohonanLog();

            InfoAudit info1 = new InfoAudit();
            info1.setDimasukOleh(pengguna);
            info1.setTarikhMasuk(new java.util.Date());
            info1.setDiKemaskiniOleh(pengguna);
            info1.setTarikhKemaskini(new java.util.Date());

            fasaPermohonanLogs.setInfoAudit(info1);
            fasaPermohonanLogs.setCawangan(pengguna.getKodCawangan());
            fasaPermohonanLogs.setFasa(fasaPermohonan);
            fasaPermohonanLogs.setUlasan(ulasan);
            fasaPermohonanLogs.setCawangan(pengguna.getKodCawangan());
            fasaPermohonanLogs.setNomborRujukan(p.getIdPermohonan());
            fasaPermohonanLogs.setKeputusan(kodKeputusan);

            ES.saveFasaLog(fasaPermohonanLogs);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/tab_keputusan_upload.jsp");
    }

    //delete dokumen
    public Resolution deleteSelected() {
        LOG.info("masuk deleteSelected");
        String[] ids = getContext().getRequest().getParameterValues("chkbox");
        String idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            p = permohonanDAO.findById(idPermohonan);
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Dokumen d = dokumenDAO.findById(Long.parseLong(id));
                if (d != null) {

                    List<HakmilikPermohonan> senarai = p.getSenaraiHakmilik();
                    for (HakmilikPermohonan hp : senarai) {
                        if (hp.getDokumen1() != null && hp.getDokumen1().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen1(null);
                        }
                        if (hp.getDokumen2() != null && hp.getDokumen2().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen2(null);
                        }
                        if (hp.getDokumen3() != null && hp.getDokumen3().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen3(null);
                        }
                        if (hp.getDokumen4() != null && hp.getDokumen4().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen4(null);
                        }
                        if (hp.getDokumen5() != null && hp.getDokumen5().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen5(null);
                        }
                        hakmilikPermohonanService.saveOrUpdateWithoutConnection(hp);
                    }
                    ImejLaporan imej = laporanTanahService.getImejLaporan(d.getIdDokumen());
                    if (imej != null) {
                        imejLaporanDAO.delete(imej);
                    }

                    dokumenDAO.delete(d);
                    addSimpleMessage("Dokumen berjaya di hapus.");
                }
            }
        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        tx.commit();
        search();
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder.jsp");
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public EnkuiriDAO getEnkuiriDAO() {
        return enkuiriDAO;
    }

    public void setEnkuiriDAO(EnkuiriDAO enkuiriDAO) {
        this.enkuiriDAO = enkuiriDAO;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(String idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public List<Kehadiran> getSenaraiKehadiran() {
        return senaraiKehadiran;
    }

    public void setSenaraiKehadiran(List<Kehadiran> senaraiKehadiran) {
        this.senaraiKehadiran = senaraiKehadiran;
    }

    public List<Kehadiran> getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(List<Kehadiran> kehadiran) {
        this.kehadiran = kehadiran;
    }

    public List<Notis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<Notis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }

    public List<Notis> getSenaraiNotis2() {
        return senaraiNotis2;
    }

    public void setSenaraiNotis2(List<Notis> senaraiNotis2) {
        this.senaraiNotis2 = senaraiNotis2;
    }

    public boolean isShow16H() {
        return show16H;
    }

    public void setShow16H(boolean show16H) {
        this.show16H = show16H;
    }

    public List<FasaPermohonan> getCheckIdAliran() {
        return checkIdAliran;
    }

    public void setCheckIdAliran(List<FasaPermohonan> checkIdAliran) {
        this.checkIdAliran = checkIdAliran;
    }

    public List<Enkuiri> getSenaraiEnkuiri2() {
        return senaraiEnkuiri2;
    }

    public void setSenaraiEnkuiri2(List<Enkuiri> senaraiEnkuiri2) {
        this.senaraiEnkuiri2 = senaraiEnkuiri2;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger aLOG) {
        LOG = aLOG;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public FolderDokumen getFolderDokumenSebelum() {
        return folderDokumenSebelum;
    }

    public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
        this.folderDokumenSebelum = folderDokumenSebelum;
    }

    public void setPermohonan(Permohonan p) {
        this.p = p;
    }

    public Permohonan getPermohonan() {
        return this.p;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public Map<String, String> getKodMapSebelum() {
        return kodMapSebelum;
    }

    public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
        this.kodMapSebelum = kodMapSebelum;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<Dokumen> getSenaraiKandunganSebelum() {
        return senaraiKandunganSebelum;
    }

    public void setSenaraiKandunganSebelum(List<Dokumen> senaraiKandunganSebelum) {
        this.senaraiKandunganSebelum = senaraiKandunganSebelum;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhDokumen() {
        return tarikhDokumen;
    }

    public void setTarikhDokumen(String tarikhDokumen) {
        this.tarikhDokumen = tarikhDokumen;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Hakmilik getH() {
        return h;
    }

    public void setH(Hakmilik h) {
        this.h = h;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public KandunganFolder getKandunganFolder() {
        return kandunganFolder;
    }

    public void setKandunganFolder(KandunganFolder kandunganFolder) {
        this.kandunganFolder = kandunganFolder;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getFolderdokumen() {
        return folderdokumen;
    }

    public void setFolderdokumen(String folderdokumen) {
        this.folderdokumen = folderdokumen;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public EnkuiriService getES() {
        return ES;
    }

    public void setES(EnkuiriService ES) {
        this.ES = ES;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public FasaPermohonanLog getFasaPermohonanLogs() {
        return fasaPermohonanLogs;
    }

    public void setFasaPermohonanLogs(FasaPermohonanLog fasaPermohonanLogs) {
        this.fasaPermohonanLogs = fasaPermohonanLogs;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        FolderAction.isDebug = isDebug;
    }

    public KandunganFolderDAO getKandunganFolderDAO() {
        return kandunganFolderDAO;
    }

    public void setKandunganFolderDAO(KandunganFolderDAO kandunganFolderDAO) {
        this.kandunganFolderDAO = kandunganFolderDAO;
    }

    public KaunterService getKaunterService() {
        return kaunterService;
    }

    public void setKaunterService(KaunterService kaunterService) {
        this.kaunterService = kaunterService;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public PermohonanBaruLelongService getPermohonanBLS() {
        return permohonanBLS;
    }

    public void setPermohonanBLS(PermohonanBaruLelongService permohonanBLS) {
        this.permohonanBLS = permohonanBLS;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public PermohonanService getPermohonanManager() {
        return permohonanManager;
    }

    public void setPermohonanManager(PermohonanService permohonanManager) {
        this.permohonanManager = permohonanManager;
    }

    public boolean isSmk() {
        return smk;
    }

    public void setSmk(boolean smk) {
        this.smk = smk;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }

    public List<Permohonan> getSenaraiPermohonan2() {
        return senaraiPermohonan2;
    }

    public void setSenaraiPermohonan2(List<Permohonan> senaraiPermohonan2) {
        this.senaraiPermohonan2 = senaraiPermohonan2;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian2() {
        return senaraiPermohonanCarian2;
    }

    public void setSenaraiPermohonanCarian2(List<PermohonanCarian> senaraiPermohonanCarian2) {
        this.senaraiPermohonanCarian2 = senaraiPermohonanCarian2;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }
        
    public boolean isShowForm1() {
        return showForm1;
    }

    public void setShowForm1(boolean showForm1) {
        this.showForm1 = showForm1;
    }

    public List<Lelongan> getSenaraiIdLelong() {
        return senaraiIdLelong;
    }

    public void setSenaraiIdLelong(List<Lelongan> senaraiIdLelong) {
        this.senaraiIdLelong = senaraiIdLelong;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public List<Pembida> getSenaraiPembida2() {
        return senaraiPembida2;
    }

    public void setSenaraiPembida2(List<Pembida> senaraiPembida2) {
        this.senaraiPembida2 = senaraiPembida2;
    }
    
    //added
    public List<Lelongan> getSenaraiPembeli() {
        return senaraiPembeli;
    }

    public void setSenaraiPembeli(List<Lelongan> senaraiPembeli) {
        this.senaraiPembeli = senaraiPembeli;
    }

    public ArrayList<Pembida> getPembidaList() {
        return pembidaList;
    }

    public void setPembidaList(ArrayList<Pembida> pembidaList) {
        this.pembidaList = pembidaList;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getKodBank2() {
        return kodBank2;
    }

    public void setKodBank2(String kodBank2) {
        this.kodBank2 = kodBank2;
    }

    public AkuanTerimaDeposit getAtd2() {
        return atd2;
    }

    public void setAtd2(AkuanTerimaDeposit atd2) {
        this.atd2 = atd2;
    }

    public BigDecimal getHargaBidaan2() {
        return hargaBidaan2;
    }

    public void setHargaBidaan2(BigDecimal hargaBidaan2) {
        this.hargaBidaan2 = hargaBidaan2;
    }

    public BigDecimal getDeposit2() {
        return deposit2;
    }

    public void setDeposit2(BigDecimal deposit2) {
        this.deposit2 = deposit2;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public String getKodCareBayar() {
        return kodCareBayar;
    }

    public void setKodCareBayar(String kodCareBayar) {
        this.kodCareBayar = kodCareBayar;
    }

    public String getKodCareBayar2() {
        return kodCareBayar2;
    }

    public void setKodCareBayar2(String kodCareBayar2) {
        this.kodCareBayar2 = kodCareBayar2;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public AkuanTerimaDeposit getAtd() {
        return atd;
    }

    public void setAtd(AkuanTerimaDeposit atd) {
        this.atd = atd;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pembida> getCheckStatusPembida() {
        return checkStatusPembida;
    }

    public void setCheckStatusPembida(List<Pembida> checkStatusPembida) {
        this.checkStatusPembida = checkStatusPembida;
    }

    public List<Pembida> getCheckListStatusPembida() {
        return checkListStatusPembida;
    }

    public void setCheckListStatusPembida(List<Pembida> checkListStatusPembida) {
        this.checkListStatusPembida = checkListStatusPembida;
    }

    public boolean isShowPopupPembeli() {
        return showPopupPembeli;
    }

    public void setShowPopupPembeli(boolean showPopupPembeli) {
        this.showPopupPembeli = showPopupPembeli;
    }

    public Pembida getPembida2() {
        return pembida2;
    }

    public void setPembida2(Pembida pembida2) {
        this.pembida2 = pembida2;
    }

    public List<Lelongan> getListLel1() {
        return listLel1;
    }

    public void setListLel1(List<Lelongan> listLel1) {
        this.listLel1 = listLel1;
    }

    public List<AkuanTerimaDeposit> getListATD() {
        return listATD;
    }

    public void setListATD(List<AkuanTerimaDeposit> listATD) {
        this.listATD = listATD;
    }

    public List<AkuanTerimaDeposit> getListATD2() {
        return listATD2;
    }

    public void setListATD2(List<AkuanTerimaDeposit> listATD2) {
        this.listATD2 = listATD2;
    }

    public List<Pembida> getCheckStatusPembida2() {
        return checkStatusPembida2;
    }

    public void setCheckStatusPembida2(List<Pembida> checkStatusPembida2) {
        this.checkStatusPembida2 = checkStatusPembida2;
    }

    public List<Pembida> getCheckListStatusPembida2() {
        return checkListStatusPembida2;
    }

    public void setCheckListStatusPembida2(List<Pembida> checkListStatusPembida2) {
        this.checkListStatusPembida2 = checkListStatusPembida2;
    }

    public boolean isShowPopupPembidaBJ() {
        return showPopupPembidaBJ;
    }

    public void setShowPopupPembidaBJ(boolean showPopupPembidaBJ) {
        this.showPopupPembidaBJ = showPopupPembidaBJ;
    }

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }

    public KodStatusPermohonan getKodStatusPermohonan() {
        return kodStatusPermohonan;
    }

    public void setKodStatusPermohonan(KodStatusPermohonan kodStatusPermohonan) {
        this.kodStatusPermohonan = kodStatusPermohonan;
    }

    public List<Permohonan> getGetCatatan() {
        return getCatatan;
    }

    public void setGetCatatan(List<Permohonan> getCatatan) {
        this.getCatatan = getCatatan;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan() {
        return addCheckDaftarHakMilikUrusan;
    }

    public void setAddCheckDaftarHakMilikUrusan(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan) {
        this.addCheckDaftarHakMilikUrusan = addCheckDaftarHakMilikUrusan;
    }

    public List<HakmilikPermohonan> getAddPermohonan() {
        return addPermohonan;
    }

    public void setAddPermohonan(List<HakmilikPermohonan> addPermohonan) {
        this.addPermohonan = addPermohonan;
    }

    public List<Permohonan> getAddListMohon() {
        return addListMohon;
    }

    public void setAddListMohon(List<Permohonan> addListMohon) {
        this.addListMohon = addListMohon;
    }

    public List<Permohonan> getCheckUrusanMohon() {
        return checkUrusanMohon;
    }

    public void setCheckUrusanMohon(List<Permohonan> checkUrusanMohon) {
        this.checkUrusanMohon = checkUrusanMohon;
    }

    public String getIdPerserahan2() {
        return idPerserahan2;
    }

    public void setIdPerserahan2(String idPerserahan2) {
        this.idPerserahan2 = idPerserahan2;
    }

    public String getIdPerserahan3() {
        return idPerserahan3;
    }

    public void setIdPerserahan3(String idPerserahan3) {
        this.idPerserahan3 = idPerserahan3;
    }

    public List<HakmilikUrusan> getAddCheckUrusan() {
        return addCheckUrusan;
    }

    public void setAddCheckUrusan(List<HakmilikUrusan> addCheckUrusan) {
        this.addCheckUrusan = addCheckUrusan;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar() {
        return addCheckPermohonanRujukanLuar;
    }

    public void setAddCheckPermohonanRujukanLuar(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar) {
        this.addCheckPermohonanRujukanLuar = addCheckPermohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar2() {
        return addCheckPermohonanRujukanLuar2;
    }

    public void setAddCheckPermohonanRujukanLuar2(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2) {
        this.addCheckPermohonanRujukanLuar2 = addCheckPermohonanRujukanLuar2;
    }

    public List<HakmilikPermohonan> getGetIdMohon() {
        return getIdMohon;
    }

    public void setGetIdMohon(List<HakmilikPermohonan> getIdMohon) {
        this.getIdMohon = getIdMohon;
    }

    public List<HakmilikPermohonan> getGetIdMohon2() {
        return getIdMohon2;
    }

    public void setGetIdMohon2(List<HakmilikPermohonan> getIdMohon2) {
        this.getIdMohon2 = getIdMohon2;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan2() {
        return addCheckDaftarHakMilikUrusan2;
    }

    public void setAddCheckDaftarHakMilikUrusan2(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2) {
        this.addCheckDaftarHakMilikUrusan2 = addCheckDaftarHakMilikUrusan2;
    }
   
    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }
    
    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }
}
