package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
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
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.model.SejarahDokumenKewangan;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
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
import etanah.model.Pemohon;
import etanah.model.PermohonanPihakPendeposit;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.PemohonService;
import etanah.service.daftar.PembetulanService;
import java.util.Iterator;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ErrorResolution;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.service.PelupusanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import java.io.File;

/**
 *
 * @author Admin
 */
@UrlBinding("/pengambilan/kemaskini_aduan")
public class AduanEditActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(AduanEditActionBean.class);
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
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
    @Inject
    PelupusanService pelupusanService;
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
    private List<HakmilikPihakBerkepentingan> pihakList;
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
    private HakmilikPermohonan hakmilikPermohonanS;
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
    private HakmilikPermohonan hakP;
    private String selectedMH;
    private Permohonan pSebelum;
    private String dokumenKod;
    FileBean fileToBeUpload;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanSblmList;
    private String namaPengadu;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String kodNegeriAlamat;
    private String email;
    private String noTelefon;
    private String jenisPengenalan;
    private Alamat alamat;
    private PermohonanPihakHubungan permohonanPihakHubungan;
    private List<PermohonanPihakHubungan> listWakil;
    private String jenisPengenalanWakil;
    private String kodNegeriWakil;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Aduan/kemasukan_aduan_kemaskini.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        return new JSP("pengambilan/Aduan/kemasukan_aduan_papar.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            if (permohonan.getPermohonanSebelum() == null) {
                hakmilikPermohonanS = aduanService.findHPByIdMohonSek4(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                logger.info("hakmilikPermohonanS -- " + hakmilikPermohonanS.getPermohonan().getIdPermohonan());

                permohonan.setPermohonanSebelum(hakmilikPermohonanS.getPermohonan());
                aduanService.simpanIdHakmilik(permohonan);
            }
            pSebelum = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            hakmilikPermohonanSblmList = pSebelum.getSenaraiHakmilik();
            permohonanAduan = aduanService.findPermohonanAduanByIdMohon(idPermohonan);
            if (permohonanAduan != null) {
                perihal = permohonanAduan.getPerihal();
            }
//            pmohon = aduanService.findPemohonByIdMohon(idPermohonan);
            pmohon = aduanService.findPemohonByIdMohon(pSebelum.getIdPermohonan());
            pihak = pihakDAO.findById(pmohon.getPihak().getIdPihak());
            permohonanPihak = aduanService.findPihakByIdMohon(idPermohonan);
//            listWakil=permohonanPihak.getSenaraiHubungan();
//            if(listWakil.size()!=0){
//                permohonanPihakHubungan=permohonanPihakHubunganDAO.findById(listWakil.get(0).getIdHubungan());
//                if(permohonanPihakHubungan!=null)
//                {
//                    jenisPengenalanWakil=permohonanPihakHubungan.getJenisPengenalan().getKod();
//                    kodNegeriWakil = permohonanPihakHubungan.getNegeri().getKod();
//
//
//                }
//            }

            if (permohonanPihak != null) {

                kodNegeriAlamat = permohonanPihak.getAlamat().getNegeri().getKod();


            }
            logger.debug("pihak" + pihak.getNama());
        }

    }

    public Resolution saveMohon() {
        logger.debug("saveMohon");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMH = getContext().getRequest().getParameter("idMH");
        logger.info("id mh -- "+ idMH);
        permohonanAduan = aduanService.findPermohonanAduanByIdMohon(idPermohonan);
        //PermohonanPihak --Pengadu
//        pmohon = aduanService.findPemohonByIdMohon(idPermohonan);
        pmohon = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
        pihak = pihakDAO.findById(pmohon.getPihak().getIdPihak());
        System.out.println("pihak:" + pihak.getNama());


        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit audit = new InfoAudit();
        audit.setDimasukOleh(pguna);
        audit.setDiKemaskiniOleh(pguna);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
        hakmilikPermohonan = aduanService.findHPByIdMohon(idPermohonan);

        logger.debug(":: PERMOHONAN START ::");

        logger.debug("idPermohonan" + permohonan.getIdPermohonan());

        logger.debug(":: PERMOHONAN END ::");


        logger.debug(":: PERMOHONAN ADUAN START ::");
        if (permohonanAduan == null) {
            PermohonanAduan pa = new PermohonanAduan();
            audit.setDimasukOleh(pguna);
            audit.setTarikhMasuk(new java.util.Date());
//            pa.setInfoAudit(audit);

            pa.setPerihal(perihal);
            pa.setPermohonan(permohonan);
            pa.setCawangan(permohonan.getCawangan());
            KodBandarPekanMukim bpm = new KodBandarPekanMukim();
            bpm = kodbandarPekanMukimDAO.findById(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
            pa.setBandarPekanMukim(bpm);
            aduanService.simpanPA(pa);
        }

        logger.debug(":: PERMOHONAN ADUAN END ::");

        logger.debug(":: PENGADU START ::");
        logger.debug("idPermohonan Pmohon" + permohonan.getIdPermohonan());
        if (pihak != null) {
            logger.debug("pihak x empty::update");
            pihak.setNama(pihak.getNama());
            pihak.setJenisPengenalan(pihak.getJenisPengenalan());
            pihak.setNoPengenalan(pihak.getNoPengenalan());
            pihak.setNoTelefon1(pihak.getNoTelefon1());
            pihak.setEmail(pihak.getEmail());
            pihak.setInfoAudit(audit);
            kodNegeriAlamat = getContext().getRequest().getParameter("kodNegeriAlamat");
            pelupusanService.saveOrUpdate(pihak);
//            permohonanPihak = aduanService.findPihakByIdMohon(idPermohonan);
//            permohonanPihak = aduanService.findPihakByIdMohon(pmohon.getPermohonan().getIdPermohonan());
//            if (permohonanPihak != null) {
//                listWakil=permohonanPihak.getSenaraiHubungan();
//                if(listWakil.size()==0){
//                  logger.debug("permohonanPihakHubungan is null");
//                  permohonanPihakHubungan=new PermohonanPihakHubungan();
//                  permohonanPihakHubungan=listWakil.get(0);
//                  permohonanPihakHubungan.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanWakil));
//                  permohonanPihakHubungan.setNegeri(kodNegeriDAO.findById(kodNegeriWakil));
//                  permohonanPihakHubungan.setCawangan(permohonanPihak.getCawangan());
//                  permohonanPihakHubungan.setPihak(permohonanPihak);
//                  permohonanPihakHubungan.setInfoAudit(audit);
//                  aduanService.simpanWakil(permohonanPihakHubungan);
//                }
//                else{
//                  logger.debug("permohonanPihakHubungan not null");
//                  permohonanPihakHubungan.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanWakil));
//                  permohonanPihakHubungan.setNegeri(kodNegeriDAO.findById(kodNegeriWakil));
//                  permohonanPihakHubungan.setCawangan(permohonanPihak.getCawangan());
//                  permohonanPihakHubungan.setPihak(permohonanPihak);
//                  aduanService.simpanWakil(permohonanPihakHubungan);
//
//                }


//                alamat = new Alamat();
//                kodNegeriAlamat = getContext().getRequest().getParameter("kodNegeriAlamat");
//                alamat.setNegeri(kodNegeriDAO.findById(kodNegeriAlamat));
//             poskod=getContext().getRequest().getParameter("poskod");
//            alamat.setPoskod(getContext().getRequest().getParameter("permohonanPihak.alamat.alamat4"));
//            permohonanPihak.setAlamat(alamat);
//            permohonanPihak.setPihak(pihak);
//                aduanService.simpanPP(permohonanPihak);
//            }

        }

        addSimpleMessage("Maklumat telah Berjaya dikemaskini");
//        rehydrate();
        return new JSP("pengambilan/Aduan/kemasukan_aduan_kemaskini.jsp").addParameter("tab", "true");

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
                reportUtil.generateReport("ENFAPA_MLK.rdf",
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

    public Resolution refreshpage() {
        idMH = getContext().getRequest().getParameter("idMH");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan/kemasukan_aduan_details.jsp");
    }

    public Resolution reload() {
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

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
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
        return KodDokumenNotRequired;
    }

    public void setKodDokumenNotRequired(List<KodDokumen> KodDokumenNotRequired) {
        this.KodDokumenNotRequired = KodDokumenNotRequired;
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanSblmList() {
        return hakmilikPermohonanSblmList;
    }

    public void setHakmilikPermohonanSblmList(List<HakmilikPermohonan> hakmilikPermohonanSblmList) {
        this.hakmilikPermohonanSblmList = hakmilikPermohonanSblmList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getNamaPengadu() {
        return namaPengadu;
    }

    public void setNamaPengadu(String namaPengadu) {
        this.namaPengadu = namaPengadu;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getKodNegeriAlamat() {
        return kodNegeriAlamat;
    }

    public void setKodNegeriAlamat(String kodNegeriAlamat) {
        this.kodNegeriAlamat = kodNegeriAlamat;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public PermohonanPihakHubungan getPermohonanPihakHubungan() {
        return permohonanPihakHubungan;
    }

    public void setPermohonanPihakHubungan(PermohonanPihakHubungan permohonanPihakHubungan) {
        this.permohonanPihakHubungan = permohonanPihakHubungan;
    }

    public List<PermohonanPihakHubungan> getListWakil() {
        return listWakil;
    }

    public void setListWakil(List<PermohonanPihakHubungan> listWakil) {
        this.listWakil = listWakil;
    }

    public String getJenisPengenalanWakil() {
        return jenisPengenalanWakil;
    }

    public void setJenisPengenalanWakil(String jenisPengenalanWakil) {
        this.jenisPengenalanWakil = jenisPengenalanWakil;
    }

    public String getKodNegeriWakil() {
        return kodNegeriWakil;
    }

    public void setKodNegeriWakil(String kodNegeriWakil) {
        this.kodNegeriWakil = kodNegeriWakil;
    }

    public HakmilikPermohonan getHakmilikPermohonanS() {
        return hakmilikPermohonanS;
    }

    public void setHakmilikPermohonanS(HakmilikPermohonan hakmilikPermohonanS) {
        this.hakmilikPermohonanS = hakmilikPermohonanS;
    }
}
