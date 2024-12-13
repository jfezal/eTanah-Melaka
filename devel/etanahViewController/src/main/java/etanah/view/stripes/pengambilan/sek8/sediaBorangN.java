/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.InfoMmknDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.Alamat;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.InfoMmkn;
import etanah.model.KandunganFolder;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodPeringkat;
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.TanahRizabPermohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.UrusanDokumen;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.model.ambil.TuntutanPerPB;
import etanah.service.BPelService;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8BCIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.acq.service.BorangACQService;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.BorangPerHakmilikService;
import etanah.service.ambil.InfoWartaService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.InfoMmknService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.common.RekodBorangIActionBean;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import etanah.view.stripes.pengambilan.share.form.PengambilanHakmilikForm;
import etanah.view.utility.DocumentUtilityAction;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/sek8/sediaBorangN")
public class sediaBorangN extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    InfoMmknDAO infoMmknDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDao;
    @Inject
    BorangPerHakmilikService borangPerHakmilikService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    @Inject
    EnforceService enforceService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    InfoMmknService infoMmknService;
    @Inject
    CalcTax calcTax;
    Permohonan permohonan = new Permohonan();
    Pihak pihak = new Pihak();
    InfoMmkn newInfoMmkn = new InfoMmkn();
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenService dokumenService;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodPeringkatDAO kodPeringkatDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    StrataPtService strataService;
    @Inject
    Sek4IntegrationFlowService sek4IntegrationFlowService;
    @Inject
    Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    @Inject
    Sek8BCIntegrationFlowService sek8BCIntegrationFlowService;
    @Inject
    KodDokumenDAO kodDokumenDao;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    private InfoMmkn infoMmkn;
    private KodPeringkat kodPeringkat;
    private String[] arrayCheckBox;
    private String idPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;
    private String display;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    BorangACQService borangACQService;
    @Inject
    InfoWartaService infoWartaService;
    @Inject
    AduanService aduanService;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<PermohonanRujukanLuar> mrlList;
//    private List<KodDokumen> senaraiKodMMK;
    private int size = 0;
    private String[] syer1;
    private String[] syer2;
    private TanahRizabPermohonan rizab;
    private List<TanahRizabPermohonan> tanahRizabList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihak mp;
//    private int size = 0;
    private String[] listluasTerlibat;
    private String noHakmilik;
    private KodUrusan mohonKodUrusan;
    private BigDecimal convLuas;
//    private BigDecimal totalLuas;
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    private String namajaga;
    private String jagaTel;
    private String jagaFax;
    private String jagaEmail;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodRizab kodRizab;
    private String noLot;
    private String luasAmbil;
    private String adaCukai;
    private String cukai;
    private String pegangan;
    private String penarikBalikan;
    private String bpm;
    private KodDaerah daerah;
    private String koddaerahbpm;
    private String kodDaerah;
    private String kodBandarPekanMukim;
    private List<KodBandarPekanMukim> senaraiBPM;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
//    private Permohonan permohonan;
    private static String staticKodBandarPekanMukim;
    private KodCawangan cawangan;
    private String idTanahRizabPermohonan;
    private String flagPBA = "0";
    private String no = "1";
    private String kodLot;
    private List<KodSeksyen> listKodSeksyen;
    private String kodSeksyen;
    private String kodCawangan;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanListNew;
//    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> luasTerlibat1 = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private List<TanahRizabPermohonan> senaraiTanahAA;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private BigDecimal luasT;
    private String kodUnit;
    private String tarikhSidang;
    private String tarikhSah;
    private String noFail;
    private String tempatBersidang;
    private String kpsnMMKN;
    private String keterangan;
    private TugasanUtiliti tugasanUtiliti;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan2 = new ArrayList<KandunganFolder>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<BorangPerHakmilik> listBorangPerHakmilik = new ArrayList<BorangPerHakmilik>();
    private List<BorangPerHakmilik> listBorangPerHakmilik2 = new ArrayList<BorangPerHakmilik>();
    private List<BorangPerHakmilik> listBorangPerHakmilik3 = new ArrayList<BorangPerHakmilik>();
    private List<KodDokumen> senaraiKodMMK = new ArrayList<KodDokumen>();
    private List<BorangPerPB> listBorangPerPB = new ArrayList<BorangPerPB>();
    private List<TuntutanPerPB> listTuntutanPerPB = new ArrayList<TuntutanPerPB>();
    private Pengguna pengguna;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private UrusanDokumen urusanDokumen;
    FileBean fileToBeUpload;
    private static Logger LOG = Logger.getLogger(RekodBorangIActionBean.class);
    private Dokumen dokumen;
    private String kodDokumen;
    private String stageId = "";
    BorangPerPermohonan borangPerMohon;
    BorangPerHakmilik borangPerHakmilik;
    BorangPerPB borangPerPB;
    InfoWarta infoWarta;
    PermohonanMahkamah mohonMahkamah;
    Task task = null;
    String name;
    List<PengambilanHakmilikForm> listHakmilikPermohonan;
    List<HakmilikPermohonan> listHakmilikPermohonan2 = new ArrayList<HakmilikPermohonan>();
    BigDecimal jumlahTotal;
    Integer jumPihakTuntut;
    String urlKembali;
    String urusan;
    BorangEForm e;
    String idBorangPerPb;
    String tujuan;
    String ketPerbincgn;
    String statusPerbincgn;
    String idHakmilik;
    BigDecimal amaunTuntutan;
    String itemTuntutan;
    List<TuntutanPerPB> listTuntutan;
    String tuntutan;
    String bantah1;
    String bantah2;
    String bantah3;
    String bantah4;
    String kepentingan;
    String alasan;
    BorangPerPB pb;
    long jum;

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    @DefaultHandler
    public Resolution showForm() {
//         idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        tuntutan = "N";
        Permohonan p = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        List<BorangPerHakmilik> listBorngPerHM = borangPerHakmilikService.findBorangPerhakmilikByIdMHAndKod(String.valueOf(hakmilikPermohonan.getId()), "NBN");
        if (listBorngPerHM.size() > 0) {
            borangPerHakmilik = listBorngPerHM.get(0);
        }
        if (borangPerHakmilik != null) {
            listBorangPerPB = aduanService.findBorangPBbyKodNotis(borangPerHakmilik, "NBN");
            if (listBorangPerPB.size() > 0) {
                borangPerPB = listBorangPerPB.get(0);
            }
        }

        urusan = p.getKodUrusan().getNama();
        hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonanOnly(idPermohonan);
        idHakmilik = hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik();
        e = borangACQService.findBorangEByIdMohonAndIdHakmilikWithKodNotis(p.getPermohonanSebelum().getIdPermohonan(), idHakmilik, "NBE");
        return new JSP("/pengambilan/APT/borangN.jsp").addParameter("tab", "true");
//        E:\EtanahNew\devel\etanahViewController\src\main\webapp\WEB-INF\jsp\pengambilan\APT\borangN.jsp

    }

    public Resolution simpanPB() throws ParseException {
//        idBorangPerPb = (String) getContext().getRequest().getParameter("idBorangPerP");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        if (idBorangPerPb != null) {
            borangPerPB = borangPerPBDAO.findById(Long.valueOf(idBorangPerPb));
//           List<BorangPerPB> bppbList = aduanService.findBorangPBbyKodNotis(borangPerHakmilik, "NBN");
            List<BorangPerHakmilik> borangPerHakmilikList = borangPerHakmilikService.findBorangPerhakmilikByIdMHAndKod(String.valueOf(hakmilikPermohonan.getId()), "NBN");
            if (borangPerHakmilikList.size() < 0) {
                BorangPerHakmilik bpHm = new BorangPerHakmilik();
                bpHm.setHakmilikPermohonan(hakmilikPermohonan);
                KodNotis kodNotis = kodNotisDAO.findById("NBN");
                bpHm.setKodNotis(kodNotis);
                bpHm.setInfoAudit(setIA(pengguna));
                aduanService.saveBorangPerHakmilik(bpHm);
            }
            List<BorangPerHakmilik> listBorangPerhakmilik = borangPerHakmilikService.findBorangPerhakmilikByIdMHAndKod(String.valueOf(hakmilikPermohonan.getId()), "NBN");
            if (listBorangPerhakmilik.size() > 0) {
                borangPerHakmilik = listBorangPerhakmilik.get(0);
                listBorangPerPB = aduanService.findBorangPBbyKodNotis(borangPerHakmilik, "NBN");
            }

            Alamat a = new Alamat();
            if (listBorangPerPB.size() <= 0) {
                if (borangPerPB != null) {
                    a.setAlamat1(borangPerPB.getAlamat().getAlamat1());
                    a.setAlamat2(borangPerPB.getAlamat().getAlamat2());
                    a.setAlamat3(borangPerPB.getAlamat().getAlamat3());
                    a.setAlamat4(borangPerPB.getAlamat().getAlamat4());
                    a.setNegeri(borangPerPB.getAlamat().getNegeri());
                    a.setPoskod(borangPerPB.getAlamat().getPoskod());

                    BorangPerPB perPB = new BorangPerPB();
                    perPB.setBorangPerHakmilik(borangPerHakmilik);
                    perPB.setKodNotis(kodNotisDAO.findById("NBN"));
                    perPB.setInfoAudit(setIA(pengguna));

                    perPB.setAlamat(a);
                    perPB.setJenisPengenalan(borangPerPB.getJenisPengenalan());
                    perPB.setJenis_kepentingan(borangPerPB.getJenis_kepentingan());
                    perPB.setBorangPerHakmilik(borangPerHakmilik);
                    perPB.setNama(borangPerPB.getNama());
                    perPB.setNoPengenalan(borangPerPB.getNoPengenalan());
                    perPB.setSyerPembilang(borangPerPB.getSyerPembilang());
                    perPB.setSyerPenyebut(borangPerPB.getSyerPenyebut());
                    borangACQService.saveBorangPerPB(perPB);
                }
            }

        }
        return showForm();
//        return new JSP("/pengambilan/APT/kemasukan_borang_j.jsp").addParameter("tab", "true");
    }

    public Resolution hapusPB() throws ParseException {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        mohonMahkamah = aduanService.findPermohonanMahkamah(idPermohonan, idBorangPerPb);
        if (mohonMahkamah != null) {
            aduanService.delPermohonanMahkamah(mohonMahkamah);
        }

        if (idBorangPerPb != null) {
            borangPerPB = borangPerPBDAO.findById(Long.valueOf(idBorangPerPb));
            aduanService.deleteBorangPerPB(borangPerPB);
        }

        return showForm();
//        return new JSP("/pengambilan/APT/kemasukan_borang_j.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBantahan() throws ParseException {
        bantah1 = (String) getContext().getRequest().getParameter("bantah1");
        bantah2 = (String) getContext().getRequest().getParameter("bantah2");
        bantah3 = (String) getContext().getRequest().getParameter("bantah3");
        bantah4 = (String) getContext().getRequest().getParameter("bantah4");
        idBorangPerPb = (String) getContext().getRequest().getParameter("idBorangPerP");
        alasan = (String) getContext().getRequest().getParameter("alasan");
        kepentingan = (String) getContext().getRequest().getParameter("kepentingan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        if (idBorangPerPb != null) {
            borangPerPB = borangPerPBDAO.findById(Long.valueOf(idBorangPerPb));

        }
        char setuju = 'Y';
        char tolak = 'T';
        mohonMahkamah = aduanService.findPermohonanMahkamah(idPermohonan, idBorangPerPb);
        if (mohonMahkamah != null) {
            mohonMahkamah.setAlasanBantah(alasan);
            mohonMahkamah.setHamilikPermohonan(hakmilikPermohonan);
            mohonMahkamah.setPermohonan(permohonan);
            mohonMahkamah.setKepentinganTanah(kepentingan);
            mohonMahkamah.setBorangPerPB(borangPerPB);
            if (bantah1 != null) {
                mohonMahkamah.setSebabUkurTanah(setuju);
            } else {
                mohonMahkamah.setSebabUkurTanah(tolak);
            }
            if (bantah2 != null) {
                mohonMahkamah.setSebabAmnPampasan(setuju);
            } else {
                mohonMahkamah.setSebabAmnPampasan(tolak);
            }
            if (bantah3 != null) {
                mohonMahkamah.setSebabPihakPampasan(setuju);
            } else {
                mohonMahkamah.setSebabPihakPampasan(tolak);
            }
            if (bantah4 != null) {
                mohonMahkamah.setSebabUmpukanPampasan(setuju);
            } else {
                mohonMahkamah.setSebabUmpukanPampasan(tolak);
            }
            aduanService.savePermohonanMahkamah(mohonMahkamah);

        } else {
            PermohonanMahkamah mm = new PermohonanMahkamah();
            mm.setAlasanBantah(alasan);
            mm.setHamilikPermohonan(hakmilikPermohonan);
            mm.setPermohonan(permohonan);
            mm.setKepentinganTanah(kepentingan);
            mm.setBorangPerPB(borangPerPB);
            if (bantah1 != null) {
                mm.setSebabUkurTanah(setuju);
            } else {
                mm.setSebabUkurTanah(tolak);
            }
            if (bantah2 != null) {
                mm.setSebabAmnPampasan(setuju);
            } else {
                mm.setSebabAmnPampasan(tolak);
            }
            if (bantah3 != null) {
                mm.setSebabPihakPampasan(setuju);
            } else {
                mm.setSebabPihakPampasan(tolak);
            }
            if (bantah4 != null) {
                mm.setSebabUmpukanPampasan(setuju);
            } else {
                mm.setSebabUmpukanPampasan(tolak);
            }
            aduanService.savePermohonanMahkamah(mm);

        }
        return showForm();
//        return new JSP("/pengambilan/APT/kemasukan_borang_j.jsp").addParameter("tab", "true");
    }

    InfoAudit setIA(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public Resolution showFormView() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        task = service.getTaskFromBPel(taskId, pengguna);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        }
        return new JSP("/pengambilan/APT/kpsn_MMMKNView.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        tugasanUtiliti = tugasanUtilitiDao.findById(idPermohonan);
        borangPerMohon = borangACQService.findBorangA(idPermohonan, "NBN");

//         Permohonan p = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        List<BorangPerHakmilik> listBorngPerHM = borangPerHakmilikService.findBorangPerhakmilikByIdMHAndKod(String.valueOf(hakmilikPermohonan.getId()), "NBN");
        if (listBorngPerHM.size() > 0) {

            borangPerHakmilik = listBorngPerHM.get(0);
        } else {
            BorangPerHakmilik bph = new BorangPerHakmilik();
            bph.setHakmilikPermohonan(hakmilikPermohonan);
            bph.setKodNotis(kodNotisDAO.findById("NBN"));
            bph.setInfoAudit(setIA(pengguna));
            aduanService.saveBorangPerHakmilik(bph);
//            bph.set
//            bph.setTrh_tt(null);
//            bph.setDitandatangan(kodCawangan);
        }
        if (borangPerHakmilik != null) {
            listBorangPerPB = aduanService.findBorangPBbyKodNotis(borangPerHakmilik, "NBN");
            if (listBorangPerPB.size() > 0) {
                borangPerPB = listBorangPerPB.get(0);
                mohonMahkamah = aduanService.findPermohonanMahkamah(idPermohonan, String.valueOf(borangPerPB.getId()));
            }

            if (mohonMahkamah != null) {
                alasan = mohonMahkamah.getAlasanBantah();
                kepentingan = mohonMahkamah.getKepentinganTanah();
                if (mohonMahkamah.getSebabUkurTanah() != null) {
                    bantah1 = Character.toString(mohonMahkamah.getSebabUkurTanah());
                }
                if (mohonMahkamah.getSebabAmnPampasan() != null) {
                    bantah2 = Character.toString(mohonMahkamah.getSebabAmnPampasan());
                }
                if (mohonMahkamah.getSebabPihakPampasan() != null) {
                    bantah3 = Character.toString(mohonMahkamah.getSebabPihakPampasan());
                }
                if (mohonMahkamah.getSebabUmpukanPampasan() != null) {
                    bantah4 = Character.toString(mohonMahkamah.getSebabUmpukanPampasan());
                }

            }
        }

        infoWarta = infoWartaService.findByIdMohonAndKodNotis1(idPermohonan, "N");
        listHakmilikPermohonan = new ArrayList<PengambilanHakmilikForm>();
        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            PengambilanHakmilikForm e = new PengambilanHakmilikForm();
            if (hp.getHakmilik() != null) {
                e.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
            }
//            jumlahTotal = aduanService.findJumlahTuntutan(idPermohonan);
//            jumPihakTuntut = (Integer.parseInt(aduanService.findJumlahPihakTuntut(idPermohonan) + ""));
            e.setJumPihak(Integer.parseInt(aduanService.findJumlahPihakTuntut(idPermohonan) + ""));
            e.setJumlahTuntutan(jumlahTotal);

            listHakmilikPermohonan.add(e);
        }
        jumPihakTuntut = listHakmilikPermohonan.get(0).getJumPihak();
        jumlahTotal = listHakmilikPermohonan.get(0).getJumlahTuntutan();
        createNewDocument(pengguna, permohonan);

        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
        } else {
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            senaraiKandungan = new ArrayList<KandunganFolder>();

            if (pengguna != null) {
                getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
            }
            String id = getContext().getRequest().getParameter("folder.idFolder");

            id = permohonan.getIdPermohonan();
            if (id != null && id.length() > 0) {
                permohonan = permohonanDAO.findById(id);
                if (permohonan == null) {
                    addSimpleError("ID Permohonan " + id + " tidak wujud");
                }

                if (permohonan != null) {
                    senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
                }

                //for filtering purposes
                List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
                for (KandunganFolder kf : senaraiKF) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    KodDokumen kd = kf.getDokumen().getKodDokumen();
//                        urusanDokumen = strataService.findUrusanDokbyKOD(kd.getKod(), permohonan.getKodUrusan().getKod());

                    if (kd != null) {
                        kodMap.put(kd.getKod(), kd.getNama());
                    }

                    if (kf.getDokumen().getPermohonan() != null) {
                        if (kf.getDokumen().getKodDokumen().getKod().equals("N")) {
                            senaraiKandungan.add(kf);
                        }

                    }
//                    if (kf.getDokumen().getPermohonan() != null) {
//                        if (kf.getDokumen().getKodDokumen().getKod().equals("J")) {
//                            senaraiKandungan2.add(kf);
//                        }
//
//                    }
                }
            }
        }
    }

    public Resolution muatNaikForm() {

        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
        }

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }
//        return showForm();
        return new JSP("/pengambilan/APT/muat_nail_APT_borangN.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("prm"))) {
            return new RedirectResolution(DocumentUtilityAction.class, "searchPermohonan").addParameter("idPermohonan", idPermohonan).addParameter("popup", "true");
        } else {
            return new RedirectResolution(FolderAction.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
        }
    }

    public Resolution processUpload() {
        LOG.info("simpanMuatNaik::start");

        String dokumenId = getContext().getRequest().getParameter("dokumenId");

        String folderId = getContext().getRequest().getParameter("folderId");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
//        senaraiKandungan2 = null;
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        if (!kodDokumen.equals("")) {
        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                LOG.debug("content type = " + contentType);

                permohonan = permohonanDAO.findById(idPermohonan);
                if (permohonan != null) {
                    List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
                    DMSUtil dmsUtil = new DMSUtil();
                    if (senaraiKF.size() > 0) {
                        for (KandunganFolder kf : senaraiKF) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
//                        urusanDokumen = strataService.findUrusanDokbyKOD(kd.getKod(), permohonan.getKodUrusan().getKod());

                            if (kd != null) {
                                kodMap.put(kd.getKod(), kd.getNama());
                            }
                            if (kodDokumen.equals("N")) {
                                senaraiKandungan.add(kf);
                                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                                fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                                updatePathDokumen(fizikalPath, senaraiKandungan.get(0).getDokumen().getIdDokumen(), contentType);
                            }
//                            else if (kodDokumen.equals("J")) {
//                                senaraiKandungan2.add(kf);
//                                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
//                                fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
//                                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
//                                updatePathDokumen(fizikalPath, senaraiKandungan2.get(0).getDokumen().getIdDokumen(), contentType);
//                            }

                        }

                    }
//                    addSimpleMessage("Muat naik fail berjaya.");
                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return showForm();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return muatNaikForm();
            }
        }

//        Logger.getLogger(MuatnaikActionBean.class).info("simpanMuatNaik::finish");
        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }
//        return new RedirectResolution(FolderAction.class).addParameter("permohonan.idPermohonan", idPermohonan);
//        return new JSP("strata/utiliti/muat_naik_dokumen.jsp").addParameter("popup", "true");
        return muatNaikForm();
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    private void createNewDocument(Pengguna peng, Permohonan permohonan) {

        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        tugasanUtiliti = tugasanUtilitiDao.findById(permohonan.getIdPermohonan());
        FolderDokumen fd = permohonan.getFolderDokumen();
//        senaraiKodMMK = new ArrayList<KodDokumen>();
        KodDokumen kd1 = kodDokumenDao.findById("N");
//        KodDokumen kd2 = kodDokumenDao.findById("J");
        if (kd1 != null) {
            senaraiKodMMK.add(kd1);
        }
//        if (kd2 != null) {
//            senaraiKodMMK.add(kd2);
//        }

        for (KodDokumen kd : senaraiKodMMK) {
            Dokumen doc = dokumenService.findDokumenByIdMohon(idPermohonan, kd.getKod());
            if (doc == null) {
                LOG.debug("doc null");
                dokumen = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                dokumen.setBaru('Y');

                dokumen.setKlasifikasi(kodKlas);
                dokumen.setFormat("application/pdf");
                dokumen.setInfoAudit(ia);
                dokumen.setPermohonan(permohonanDAO.findById(idPermohonan));
                //TODO : increase versi if regenarate report
                dokumen.setNoVersi("1.0");
                dokumen.setTajuk(kd.getKod());
                dokumen.setKodDokumen(kd);
                dokumen = dokumenService.saveOrUpdate(dokumen);

                KandunganFolder folderDokumen = new KandunganFolder();
                folderDokumen.setInfoAudit(ia);
                folderDokumen.setFolder(permohonan.getFolderDokumen());
                folderDokumen.setDokumen(dokumen);
                dokumenService.saveKandunganWithDokumen(folderDokumen);

            }
        }
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public ConsentPtdService getConService() {
        return conService;
    }

    public void setConService(ConsentPtdService conService) {
        this.conService = conService;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public CalcTax getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTax calcTax) {
        this.calcTax = calcTax;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String[] getArrayCheckBox() {
        return arrayCheckBox;
    }

    public void setArrayCheckBox(String[] arrayCheckBox) {
        this.arrayCheckBox = arrayCheckBox;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public HakmilikPermohonanService getHakmilikpermohonanservice() {
        return hakmilikpermohonanservice;
    }

    public void setHakmilikpermohonanservice(HakmilikPermohonanService hakmilikpermohonanservice) {
        this.hakmilikpermohonanservice = hakmilikpermohonanservice;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public HakmilikPihakKepentinganService getHakmilikPihakKepentinganService() {
        return hakmilikPihakKepentinganService;
    }

    public void setHakmilikPihakKepentinganService(HakmilikPihakKepentinganService hakmilikPihakKepentinganService) {
        this.hakmilikPihakKepentinganService = hakmilikPihakKepentinganService;
    }

    public PermohonanRujukanLuarService getService() {
        return service;
    }

    public void setService(PermohonanRujukanLuarService service) {
        this.service = service;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikPihakBerkepentingan> getListTuanTanahNew() {
        return listTuanTanahNew;
    }

    public void setListTuanTanahNew(List<HakmilikPihakBerkepentingan> listTuanTanahNew) {
        this.listTuanTanahNew = listTuanTanahNew;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<HakmilikPihakBerkepentingan> getPemilik() {
        return pemilik;
    }

    public void setPemilik(List<HakmilikPihakBerkepentingan> pemilik) {
        this.pemilik = pemilik;
    }

    public List<PermohonanRujukanLuar> getMrlList() {
        return mrlList;
    }

    public void setMrlList(List<PermohonanRujukanLuar> mrlList) {
        this.mrlList = mrlList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
    }

    public TanahRizabPermohonan getRizab() {
        return rizab;
    }

    public void setRizab(TanahRizabPermohonan rizab) {
        this.rizab = rizab;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanPihak getMp() {
        return mp;
    }

    public void setMp(PermohonanPihak mp) {
        this.mp = mp;
    }

    public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public KodUrusan getMohonKodUrusan() {
        return mohonKodUrusan;
    }

    public void setMohonKodUrusan(KodUrusan mohonKodUrusan) {
        this.mohonKodUrusan = mohonKodUrusan;
    }

    public BigDecimal getConvLuas() {
        return convLuas;
    }

    public void setConvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public String getNamajaga() {
        return namajaga;
    }

    public void setNamajaga(String namajaga) {
        this.namajaga = namajaga;
    }

    public String getJagaTel() {
        return jagaTel;
    }

    public void setJagaTel(String jagaTel) {
        this.jagaTel = jagaTel;
    }

    public String getJagaFax() {
        return jagaFax;
    }

    public void setJagaFax(String jagaFax) {
        this.jagaFax = jagaFax;
    }

    public String getJagaEmail() {
        return jagaEmail;
    }

    public void setJagaEmail(String jagaEmail) {
        this.jagaEmail = jagaEmail;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(String luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public String getAdaCukai() {
        return adaCukai;
    }

    public void setAdaCukai(String adaCukai) {
        this.adaCukai = adaCukai;
    }

    public String getCukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getPenarikBalikan() {
        return penarikBalikan;
    }

    public void setPenarikBalikan(String penarikBalikan) {
        this.penarikBalikan = penarikBalikan;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getKoddaerahbpm() {
        return koddaerahbpm;
    }

    public void setKoddaerahbpm(String koddaerahbpm) {
        this.koddaerahbpm = koddaerahbpm;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(String kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public static String getStaticKodBandarPekanMukim() {
        return staticKodBandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdTanahRizabPermohonan() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizabPermohonan(String idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public String getFlagPBA() {
        return flagPBA;
    }

    public void setFlagPBA(String flagPBA) {
        this.flagPBA = flagPBA;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListNew() {
        return hakmilikPermohonanListNew;
    }

    public void setHakmilikPermohonanListNew(List<HakmilikPermohonan> hakmilikPermohonanListNew) {
        this.hakmilikPermohonanListNew = hakmilikPermohonanListNew;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public List<String> getLuasTerlibat1() {
        return luasTerlibat1;
    }

    public void setLuasTerlibat1(List<String> luasTerlibat1) {
        this.luasTerlibat1 = luasTerlibat1;
    }

    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public List<TanahRizabPermohonan> getSenaraiTanahAA() {
        return senaraiTanahAA;
    }

    public void setSenaraiTanahAA(List<TanahRizabPermohonan> senaraiTanahAA) {
        this.senaraiTanahAA = senaraiTanahAA;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public BigDecimal getLuasT() {
        return luasT;
    }

    public void setLuasT(BigDecimal luasT) {
        this.luasT = luasT;
    }

    public String getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(String kodUnit) {
        this.kodUnit = kodUnit;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getTarikhSah() {
        return tarikhSah;
    }

    public void setTarikhSah(String tarikhSah) {
        this.tarikhSah = tarikhSah;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getTempatBersidang() {
        return tempatBersidang;
    }

    public void setTempatBersidang(String tempatBersidang) {
        this.tempatBersidang = tempatBersidang;
    }

    public String getKpsnMMKN() {
        return kpsnMMKN;
    }

    public void setKpsnMMKN(String kpsnMMKN) {
        this.kpsnMMKN = kpsnMMKN;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public InfoMmknDAO getInfoMmknDAO() {
        return infoMmknDAO;
    }

    public void setInfoMmknDAO(InfoMmknDAO infoMmknDAO) {
        this.infoMmknDAO = infoMmknDAO;
    }

    public TugasanUtilitiDAO getTugasanUtilitiDao() {
        return tugasanUtilitiDao;
    }

    public void setTugasanUtilitiDao(TugasanUtilitiDAO tugasanUtilitiDao) {
        this.tugasanUtilitiDao = tugasanUtilitiDao;
    }

    public InfoMmknService getInfoMmknService() {
        return infoMmknService;
    }

    public void setInfoMmknService(InfoMmknService infoMmknService) {
        this.infoMmknService = infoMmknService;
    }

    public InfoMmkn getNewInfoMmkn() {
        return newInfoMmkn;
    }

    public void setNewInfoMmkn(InfoMmkn newInfoMmkn) {
        this.newInfoMmkn = newInfoMmkn;
    }

    public KandunganFolderDAO getKandunganFolderDAO() {
        return kandunganFolderDAO;
    }

    public void setKandunganFolderDAO(KandunganFolderDAO kandunganFolderDAO) {
        this.kandunganFolderDAO = kandunganFolderDAO;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public FolderDokumenDAO getFolderDAO() {
        return folderDAO;
    }

    public void setFolderDAO(FolderDokumenDAO folderDAO) {
        this.folderDAO = folderDAO;
    }

    public StrataPtService getStrataService() {
        return strataService;
    }

    public void setStrataService(StrataPtService strataService) {
        this.strataService = strataService;
    }

    public InfoMmkn getInfoMmkn() {
        return infoMmkn;
    }

    public void setInfoMmkn(InfoMmkn infoMmkn) {
        this.infoMmkn = infoMmkn;
    }

    public KodPeringkat getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkat kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public TugasanUtiliti getTugasanUtiliti() {
        return tugasanUtiliti;
    }

    public void setTugasanUtiliti(TugasanUtiliti tugasanUtiliti) {
        this.tugasanUtiliti = tugasanUtiliti;
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

    public Map<String, String> getKodMapSebelum() {
        return kodMapSebelum;
    }

    public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
        this.kodMapSebelum = kodMapSebelum;
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumenSebelum() {
        return folderDokumenSebelum;
    }

    public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
        this.folderDokumenSebelum = folderDokumenSebelum;
    }

    public UrusanDokumen getUrusanDokumen() {
        return urusanDokumen;
    }

    public void setUrusanDokumen(UrusanDokumen urusanDokumen) {
        this.urusanDokumen = urusanDokumen;
    }

    public KodDokumenDAO getKodDokumenDao() {
        return kodDokumenDao;
    }

    public void setKodDokumenDao(KodDokumenDAO kodDokumenDao) {
        this.kodDokumenDao = kodDokumenDao;
    }

    public KandunganFolderService getKandunganFolderService() {
        return kandunganFolderService;
    }

    public void setKandunganFolderService(KandunganFolderService kandunganFolderService) {
        this.kandunganFolderService = kandunganFolderService;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public KodKlasifikasiDAO getKodKlasifikasiDAO() {
        return kodKlasifikasiDAO;
    }

    public void setKodKlasifikasiDAO(KodKlasifikasiDAO kodKlasifikasiDAO) {
        this.kodKlasifikasiDAO = kodKlasifikasiDAO;
    }

    public FolderDokumenDAO getFolderDokumenDAO() {
        return folderDokumenDAO;
    }

    public void setFolderDokumenDAO(FolderDokumenDAO folderDokumenDAO) {
        this.folderDokumenDAO = folderDokumenDAO;
    }

    public List<KodDokumen> getSenaraiKodMMK() {
        return senaraiKodMMK;
    }

    public void setSenaraiKodMMK(List<KodDokumen> senaraiKodMMK) {
        this.senaraiKodMMK = senaraiKodMMK;
    }

    public List<KandunganFolder> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<KandunganFolder> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodPeringkatDAO getKodPeringkatDAO() {
        return kodPeringkatDAO;
    }

    public void setKodPeringkatDAO(KodPeringkatDAO kodPeringkatDAO) {
        this.kodPeringkatDAO = kodPeringkatDAO;
    }

    public Sek4IntegrationFlowService getSek4IntegrationFlowService() {
        return sek4IntegrationFlowService;
    }

    public void setSek4IntegrationFlowService(Sek4IntegrationFlowService sek4IntegrationFlowService) {
        this.sek4IntegrationFlowService = sek4IntegrationFlowService;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Sek8aIntegrationFlowService getSek8aIntegrationFlowService() {
        return sek8aIntegrationFlowService;
    }

    public void setSek8aIntegrationFlowService(Sek8aIntegrationFlowService sek8aIntegrationFlowService) {
        this.sek8aIntegrationFlowService = sek8aIntegrationFlowService;
    }

    public PengambilanAPTService getPengambilanAPTService() {
        return pengambilanAPTService;
    }

    public void setPengambilanAPTService(PengambilanAPTService pengambilanAPTService) {
        this.pengambilanAPTService = pengambilanAPTService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sek4AduanIntegrationFlowService getSek4AduanIntegrationFlowService() {
        return sek4AduanIntegrationFlowService;
    }

    public void setSek4AduanIntegrationFlowService(Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService) {
        this.sek4AduanIntegrationFlowService = sek4AduanIntegrationFlowService;
    }

    public Sek8BCIntegrationFlowService getSek8BCIntegrationFlowService() {
        return sek8BCIntegrationFlowService;
    }

    public void setSek8BCIntegrationFlowService(Sek8BCIntegrationFlowService sek8BCIntegrationFlowService) {
        this.sek8BCIntegrationFlowService = sek8BCIntegrationFlowService;
    }

    public BorangACQService getBorangACQService() {
        return borangACQService;
    }

    public void setBorangACQService(BorangACQService borangACQService) {
        this.borangACQService = borangACQService;
    }

    public BorangPerPermohonan getBorangPerMohon() {
        return borangPerMohon;
    }

    public void setBorangPerMohon(BorangPerPermohonan borangPerMohon) {
        this.borangPerMohon = borangPerMohon;
    }

    public InfoWartaService getInfoWartaService() {
        return infoWartaService;
    }

    public void setInfoWartaService(InfoWartaService infoWartaService) {
        this.infoWartaService = infoWartaService;
    }

    public InfoWarta getInfoWarta() {
        return infoWarta;
    }

    public void setInfoWarta(InfoWarta infoWarta) {
        this.infoWarta = infoWarta;
    }

    public AduanService getAduanService() {
        return aduanService;
    }

    public void setAduanService(AduanService aduanService) {
        this.aduanService = aduanService;
    }

    public List<BorangPerHakmilik> getListBorangPerHakmilik() {
        return listBorangPerHakmilik;
    }

    public void setListBorangPerHakmilik(List<BorangPerHakmilik> listBorangPerHakmilik) {
        this.listBorangPerHakmilik = listBorangPerHakmilik;
    }

    public List<BorangPerHakmilik> getListBorangPerHakmilik2() {
        return listBorangPerHakmilik2;
    }

    public void setListBorangPerHakmilik2(List<BorangPerHakmilik> listBorangPerHakmilik2) {
        this.listBorangPerHakmilik2 = listBorangPerHakmilik2;
    }

    public List<BorangPerHakmilik> getListBorangPerHakmilik3() {
        return listBorangPerHakmilik3;
    }

    public void setListBorangPerHakmilik3(List<BorangPerHakmilik> listBorangPerHakmilik3) {
        this.listBorangPerHakmilik3 = listBorangPerHakmilik3;
    }

    public List<BorangPerPB> getListBorangPerPB() {
        return listBorangPerPB;
    }

    public void setListBorangPerPB(List<BorangPerPB> listBorangPerPB) {
        this.listBorangPerPB = listBorangPerPB;
    }

    public List<TuntutanPerPB> getListTuntutanPerPB() {
        return listTuntutanPerPB;
    }

    public void setListTuntutanPerPB(List<TuntutanPerPB> listTuntutanPerPB) {
        this.listTuntutanPerPB = listTuntutanPerPB;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public BorangPerPB getBorangPerPB() {
        return borangPerPB;
    }

    public void setBorangPerPB(BorangPerPB borangPerPB) {
        this.borangPerPB = borangPerPB;
    }

    public List<PengambilanHakmilikForm> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<PengambilanHakmilikForm> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public BigDecimal getJumlahTotal() {
        return jumlahTotal;
    }

    public void setJumlahTotal(BigDecimal jumlahTotal) {
        this.jumlahTotal = jumlahTotal;
    }

    public Integer getJumPihakTuntut() {
        return jumPihakTuntut;
    }

    public void setJumPihakTuntut(Integer jumPihakTuntut) {
        this.jumPihakTuntut = jumPihakTuntut;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan2() {
        return listHakmilikPermohonan2;
    }

    public void setListHakmilikPermohonan2(List<HakmilikPermohonan> listHakmilikPermohonan2) {
        this.listHakmilikPermohonan2 = listHakmilikPermohonan2;
    }

    public String getUrlKembali() {
        return urlKembali;
    }

    public void setUrlKembali(String urlKembali) {
        this.urlKembali = urlKembali;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public BorangEForm getE() {
        return e;
    }

    public void setE(BorangEForm e) {
        this.e = e;
    }

    public BorangPerPBDAO getBorangPerPBDAO() {
        return borangPerPBDAO;
    }

    public void setBorangPerPBDAO(BorangPerPBDAO borangPerPBDAO) {
        this.borangPerPBDAO = borangPerPBDAO;
    }

    public String getIdBorangPerPb() {
        return idBorangPerPb;
    }

    public void setIdBorangPerPb(String idBorangPerPb) {
        this.idBorangPerPb = idBorangPerPb;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKetPerbincgn() {
        return ketPerbincgn;
    }

    public void setKetPerbincgn(String ketPerbincgn) {
        this.ketPerbincgn = ketPerbincgn;
    }

    public String getStatusPerbincgn() {
        return statusPerbincgn;
    }

    public void setStatusPerbincgn(String statusPerbincgn) {
        this.statusPerbincgn = statusPerbincgn;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getItemTuntutan() {
        return itemTuntutan;
    }

    public void setItemTuntutan(String itemTuntutan) {
        this.itemTuntutan = itemTuntutan;
    }

    public List<TuntutanPerPB> getListTuntutan() {
        return listTuntutan;
    }

    public void setListTuntutan(List<TuntutanPerPB> listTuntutan) {
        this.listTuntutan = listTuntutan;
    }

    public String getTuntutan() {
        return tuntutan;
    }

    public void setTuntutan(String tuntutan) {
        this.tuntutan = tuntutan;
    }

    public BorangPerPB getPb() {
        return pb;
    }

    public void setPb(BorangPerPB pb) {
        this.pb = pb;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public long getJum() {
        return jum;
    }

    public void setJum(long jum) {
        this.jum = jum;
    }

    public BorangPerHakmilikService getBorangPerHakmilikService() {
        return borangPerHakmilikService;
    }

    public void setBorangPerHakmilikService(BorangPerHakmilikService borangPerHakmilikService) {
        this.borangPerHakmilikService = borangPerHakmilikService;
    }

    public KodNotisDAO getKodNotisDAO() {
        return kodNotisDAO;
    }

    public void setKodNotisDAO(KodNotisDAO kodNotisDAO) {
        this.kodNotisDAO = kodNotisDAO;
    }

    public PermohonanMahkamah getMohonMahkamah() {
        return mohonMahkamah;
    }

    public void setMohonMahkamah(PermohonanMahkamah mohonMahkamah) {
        this.mohonMahkamah = mohonMahkamah;
    }

    public String getBantah1() {
        return bantah1;
    }

    public void setBantah1(String bantah1) {
        this.bantah1 = bantah1;
    }

    public String getBantah2() {
        return bantah2;
    }

    public void setBantah2(String bantah2) {
        this.bantah2 = bantah2;
    }

    public String getBantah3() {
        return bantah3;
    }

    public void setBantah3(String bantah3) {
        this.bantah3 = bantah3;
    }

    public String getBantah4() {
        return bantah4;
    }

    public void setBantah4(String bantah4) {
        this.bantah4 = bantah4;
    }

    public String getKepentingan() {
        return kepentingan;
    }

    public void setKepentingan(String kepentingan) {
        this.kepentingan = kepentingan;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

}
