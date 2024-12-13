package etanah.view.lelong.dokumen;

import com.google.inject.Inject;

import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Permohonan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
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
import etanah.dao.NotisDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanCarian;
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
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.GenerateIdPermohonanWorkflow;
import etanah.workflow.WorkFlowService;
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
 * @author nur.aqilah
 */
@UrlBinding("/lelong/dokumen/folder2")
public class FolderAction2 extends AbleActionBean {

    @Inject
    private LaporanTanahService laporanTanahService;
    @Inject
    private ImejLaporanDAO imejLaporanDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private KaunterService kaunterService;
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
    private HakmilikService hakmilikService;
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
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    etanah.Configuration conf;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private Pengguna pengguna;
    private Permohonan p;
    private Permohonan permohonan;
    private Permohonan permohonan1;
    private String idEnkuiri;
    private Enkuiri enkuiri;
    private Enkuiri enkuiri1;
    private Enkuiri enkuiri2;
    private KandunganFolder kandunganFolder;
    private Hakmilik h;
    private TabManager tabManager;
    private FasaPermohonanDAO fasaPermohonanDAO;
    private FasaPermohonanService fasaPermohonanManager;
    private KodCawanganDAO kodCawanganDAO;
    private PermohonanService permohonanManager;
    private Dokumen dokumen;
    private Hakmilik hakmilik;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonanLog fasaPermohonanLogs;
    private HakmilikPermohonan hp;
    private KodUrusan kodUrusan;
    private KodDokumen kodDokumen;
    private boolean showForm;
    private boolean smk = false;
    private String status;
    private String participant;
    private String stage;
    private String idPermohonan;
    private String idDokumen;
    private String idFolder;
    private String folderdokumen;
    private String idSebelum;
    private String tarikhDokumen;
    private String ks;
    private String ulasan;
    private String keputusan;
    private String urusan;
    private String idHakmilik;
    private List<Enkuiri> senaraiEnkuiri;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<Permohonan> senaraiPermohonan;
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Kehadiran> senaraiKehadiran = new ArrayList<Kehadiran>();
    private List<Notis> senaraiNotis = new ArrayList<Notis>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private List<Lelongan> senaraiLelongan;
    private List<Kehadiran> kehadiran;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<FasaPermohonan> listFasa;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private static final Logger LOG = Logger.getLogger(FolderAction.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    public FolderAction2(FasaPermohonanDAO fasaPermohonanDAO, PermohonanDAO permohonanDAO,
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
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

    public Resolution showFormA() {
        LOG.info("tab keputusan FolderAB");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/tab_keputusan_upload.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/tab_mahkamah.jsp");
    }

    public Resolution saveFolderInfo() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
//        Pengguna pguna = (Pengguna)getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

    public Resolution find() {
        LOG.info("rehydrate - start");
        LOG.info("idPermohonan : " + idPermohonan);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (isIS_DEBUG()) {
            LOG.debug("======= page: " + page);
        }
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
            LOG.debug("======= StringUtils is not blank ");
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

        if (isIS_DEBUG()) {
            LOG.debug("====== idPermohonan: " + idPermohonan);
        }

        if ((StringUtils.isNotBlank(idPermohonan))) {

            if (idPermohonan != null) {
                LOG.info("MASUK idPermohonan!=null");
                permohonan = getPermohonanDAO().findById(idPermohonan);

                if (permohonan == null) {
                    setShowForm(true);
                } else {
                    setShowForm(false);
                }

                LOG.debug("Permohonan " + permohonan);

                //id mohon not exist / search by wildcard
                if (permohonan == null) {

                    permohonan = null;

                    LOG.info("MASUK permohonan==null");

                    setSenaraiPermohonan(lelongService.getSenaraiPermohonan(idPermohonan, pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records()));

                    LOG.info("senaraiPermohonan " + getSenaraiPermohonan().size());
                    set__pg_total_records(lelongService.getTotalRecordFolderAction(idPermohonan, pengguna.getKodCawangan().getKod()).intValue());

                    //find in carian
                    if (getSenaraiPermohonan().isEmpty()) {
                        setSenaraiPermohonanCarian(lelongService.getSenaraiPermohonanCarian(idPermohonan,
                                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records()));

                        LOG.info("senaraiPermohonanCarian " + getSenaraiPermohonanCarian().size());
                    }
                    if (getSenaraiPermohonan().isEmpty()
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
        return new JSP("/lelong/dokumen/folder2.jsp");
    }

    public Resolution search() {
        LOG.info("rehydrate - start");
        //todo : add norujukan fail
        LOG.info("idPermohonan : " + idPermohonan);

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = getPermohonanDAO().findById(idPermohonan);

            //  LOG.info("Id Permohonan" + permohonan.getIdPermohonan());
            if (permohonan != null) {
                LOG.debug("MASUK ID !=NULL");
                LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
//                permohonan.getFolderDokumen().getTajuk();LOG.info("tajuk :" + permohonan.getFolderDokumen().getTajuk());
                LOG.info("tajuk :" + permohonan.getFolderDokumen().getTajuk());

                List<Task> l;
                try {
                    l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

                    for (Task t : l) {
                        setStage(t.getSystemAttributes().getStage());
                        setParticipant(t.getSystemAttributes().getAcquiredBy());

                    }
                } catch (WorkflowException ex) {
                    LOG.error(ex);
                }

            } else {
                LOG.debug("MASUK ID ==NULL");
                addSimpleError("Id Permohonan tidak wujud");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            }

            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String id = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
            String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
            if (id == null) {
                LOG.info("-----if------");
                folderDokumen = folderDAO.findById(Long.valueOf(id));
                LOG.info("id Folder :" + folderDokumen.getFolderId());
            } else {
                LOG.info("-----else------");
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
                        LOG.info("kodUrusan " + p.getKodUrusan().getKod());

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

                } else {
                    addSimpleError("Folder tidak ditentukan.");
                }

                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            }

            //status message
            status = permohonan.getStatus();

            LOG.info("status: " + getStatus());
            if (getStatus() == null) {
                //addSimpleMessage("Urusan ini sedang diproses");
                addSimpleMessage("Permohonan Ini Diperingkat" + stage); //added by murali 05042013
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TL".equals(getStatus())) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Ditolak ");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TT".equals(getStatus())) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Ditutup ");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("RM".equals(getStatus())) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Dirujuk Ke Mahkamah");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("SD".equals(getStatus())) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Tidak Aktif. Menunggu Keputusan Dari Mahkamah");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TS".equals(getStatus())) {
                addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TA".equals(getStatus())) {
                addSimpleMessage("Tugasan ini belum diambil oleh sesiapa");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("AK".equals(getStatus())) {
                addSimpleMessage("Urusan ini sedang diproses.");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            }
                else if ("MK".equals(getStatus())) {
                addSimpleMessage("Urusan ini telah dibatalkan oleh mahkamah.");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("GB".equals(getStatus())) { // Gantung
                // TODO: check if task own by SPOC
                addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                        + permohonan.getKodUrusan().getJabatanNama());
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TK".equals(getStatus())) {//TK - Tidak Aktif - Urusan telah dibatalkan
                addSimpleMessage("Urusan telah dibatalkan");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("TP".equals(getStatus())) {
                addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("SL".equals(getStatus())) {
                //addSimpleMessage("Urusan in telah selesai diproses. Keputusan telah dikeluarkan");
                addSimpleMessage("Permohonan ini Telah Selesai"); //added By murali 05042013
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else if ("SS".equals(getStatus())) {
                addSimpleMessage("Urusan ini telah disemak semula");
                return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
            } else {

                return null;

            }

        }
        LOG.info("Rehydrate - finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

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
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }
    //reset

    public Resolution reset() {
        LOG.info("---nk reset---");
        permohonan = new Permohonan();
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

    public Resolution refreshpage() {
        LOG.info("refreshPage : start");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        search();
        LOG.info("refreshPage : finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        LOG.info("reload");
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
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
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

    public Resolution viewPDF() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/uploadFileApplet.jsp");

    }
    /*
     * fikri: for upload document tambahan
     *
     */

    public Resolution dokumenTambahanForm() {
        // reset the additional documents submitted to 10

//        if (p == null) {
//            addSimpleError("Sila masukkan IdPermohonan terlebih dahulu.");
//            return new JSP("/lelong/dokumen/folder.jsp");
//        } else {

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        LOG.info("idDokumen : " + idDokumen);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());

        LOG.info("----------dokumenTambahanForm--------");
        LOG.info("senaraiKodUrusan : " + senaraiKodUrusan.size());

        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i
                    < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
//        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/dokumen_tambahan2.jsp");
    }

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

    public Resolution simpanDokumenTambahan() {
        LOG.info("simpanDokumenTambahan::start");
        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        LOG.info("kandunganFolderTamb : " + kandunganFolderTamb.size());

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

                } //                KodDokumen kd = d.getKodDokumen();
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
                        LOG.error(ex);
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
        LOG.info("simpanDokumenTambahan::finish");
        return new StreamingResolution("text/plain", result);
    }

    public Resolution seterusnya() {
        LOG.info("------start--------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna1 = ctx.getUser();

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
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
        LOG.info("------finish--------");
        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/folder2.jsp");
    }

    public Resolution save() {
        LOG.info("------keputusan dlm FolderAB-------");

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        p = getPermohonanDAO().findById(idMohon);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());
        LOG.info("kodUrusan : " + p.getKodUrusan().getKod());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //save in mohon_fasa
        if (p != null) {

            listFasa = ES.getSenaraiFasaPermohonan(p.getIdPermohonan());
            LOG.info("listFasa.size : " + listFasa.size());

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
            LOG.info("idfasa : " + fasaPermohonan.getIdFasa());


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
        FolderAction2.isDebug = isDebug;
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

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }
}
