/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.InfoMmknDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.TugasanUtilitiDAO;
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
import etanah.model.KodPeringkat;
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.TanahRizabPermohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.UrusanDokumen;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.BPelService;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8BCIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.InfoMmknService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.ListUtil;
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.DocumentUtilityAction;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@UrlBinding("/pengambilan/common/rekodKpsnMMKActionBean")
public class RekodKpsnMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    InfoMmknDAO infoMmknDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDao;
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
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
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
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<PermohonanRujukanLuar> mrlList;
    private List<KodDokumen> senaraiKodMMK;
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
    private Pengguna pengguna;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private UrusanDokumen urusanDokumen;
    FileBean fileToBeUpload;
    private static Logger LOG = Logger.getLogger(RekodKpsnMMKActionBean.class);
    private Dokumen dokumen;
    private String kodDokumen;
    private String stageId = "";
    Task task = null;
    String name;

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        stageId = task.getSystemAttributes().getStage();
////                stageId = "RekodKeputusanJKKPT";
//        if (stageId.equals("RekodKeputusanMMK")) {
//            name = "Rekod Keputusan MMKN";
//        } else if (stageId.equals("rekod_surat_kpsn")) {
//            name = "Rekod Keputusan RKMK";
//        } else if (stageId.equals("RekodKeputusanJKKPT")) {
//            name = "Rekod Keputusan JKKPT";
//        }
////        task = service.getTaskFromBPel(taskId, pengguna);
////        if (task != null) {
////            stageId = task.getSystemAttributes().getStage();
////        }
//        if (stageId.equals("RekodKeputusanJKKPT")) {
//            return new JSP("/pengambilan/APT/kpsn_JKKPT.jsp").addParameter("tab", "true");
//        } else {
        return new JSP("/pengambilan/APT/kpsn_MMMKN.jsp").addParameter("tab", "true");
        // }

    }

    public Resolution showFormJKKPT() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        stageId = task.getSystemAttributes().getStage();

        return new JSP("/pengambilan/APT/kpsn_JKKPT.jsp").addParameter("tab", "true");

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

    public Resolution simpanKpsnMMKN() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BPelService service = new BPelService();
        tugasanUtiliti = tugasanUtilitiDao.findById(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (tugasanUtiliti != null) {
            infoMmkn = infoMmknService.findByIdMohonAndKodPeringkat(idPermohonan, tugasanUtiliti.getKodPeringkat().getKod());
            if (infoMmkn != null) {
                infoMmkn.setTempatSidang(tempatBersidang);
                infoMmkn.setTrhSidang(sdf.parse(tarikhSidang));
                infoMmkn.setTrhSah(sdf.parse(tarikhSah));
                infoMmkn.setNoFailMmkn(noFail);
                infoMmkn.setKodKpsn(kpsnMMKN);
                infoMmkn.setKodPeringkat(tugasanUtiliti.getKodPeringkat());
                infoMmkn.setPermohonan(permohonan);
                infoMmkn.setKeteranganMmkn(keterangan);
                infoMmkn.setInfoAudit(ia);
                infoMmknService.simpanInfoMMKN(infoMmkn);
                addSimpleMessage("Kemaskini Telah Berjaya");
            } else {
                InfoMmkn newInfoMmkn = new InfoMmkn();
                newInfoMmkn.setTempatSidang(tempatBersidang);
                newInfoMmkn.setPermohonan(permohonan);
                newInfoMmkn.setTrhSidang(sdf.parse(tarikhSidang));
                newInfoMmkn.setTrhSah(sdf.parse(tarikhSah));
                newInfoMmkn.setNoFailMmkn(noFail);
                newInfoMmkn.setKodKpsn(kpsnMMKN);
                newInfoMmkn.setKodPeringkat(tugasanUtiliti.getKodPeringkat());
                newInfoMmkn.setKeteranganMmkn(keterangan);
                newInfoMmkn.setInfoAudit(ia);
                infoMmknService.simpanInfoMMKN(newInfoMmkn);
                addSimpleMessage("Kemaskini Telah Berjaya");
            }
        }
        rehydrate();
        return showForm();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        tugasanUtiliti = tugasanUtilitiDao.findById(idPermohonan);
        if (tugasanUtiliti != null) {
            infoMmkn = infoMmknService.findByIdMohonAndKodPeringkat(idPermohonan, tugasanUtiliti.getKodPeringkat().getKod());

        } else {
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            BPelService service = new BPelService();
            task = service.getTaskFromBPel(taskId, pengguna);
            stageId = task.getSystemAttributes().getStage();
//            stageId = "rekod_kpsn_mmkn";
            if (permohonan.getKodUrusan().getKod().equals("831")) {
                if (stageId.equals("DrafSrtMaklum") || stageId.equals("SemakanSrtMaklum") || stageId.equals("CetakSrtMaklum") || stageId.equals("SediaBorang5Atau6")) {
                    kodPeringkat = kodPeringkatDAO.findById("MMKBC");
                }
                if (stageId.equals("rekod_surat_kpsn") || stageId.equals("semak_tandatangan") || stageId.equals("cetak_surat")) {
                    kodPeringkat = kodPeringkatDAO.findById("RKMK");
                }

                //kodPeringkat = infoMmkn.getKodPeringkat();
            } else if (permohonan.getKodUrusan().getKod().equals("SEK4")) {
                if (stageId.equals("rekod_surat_kpsn") || stageId.equals("semak_tandatangan") || stageId.equals("cetak_surat")) {
                    kodPeringkat = kodPeringkatDAO.findById("RKMMK");
                }
            } else if (permohonan.getKodUrusan().getKod().equals("SEK4A")) {
                if (stageId.equals("rekod_kpsn_mmkn")) {
                    kodPeringkat = kodPeringkatDAO.findById("RAMMK");
                }

            }else if (permohonan.getKodUrusan().getKod().equals("ESK8")) {
                if (stageId.equals("maklum_keputusan")) {
                    kodPeringkat = kodPeringkatDAO.findById("ET8MK");
                }

            }
            infoMmkn = infoMmknService.findByIdMohonAndKodPeringkat(idPermohonan, kodPeringkat.getKod());
        }

        createNewDocument(pengguna, permohonan);
        if (infoMmkn != null) {
            if (infoMmkn.getTrhSidang() != null) {
                tarikhSidang = sdf.format(infoMmkn.getTrhSidang());
            }
            if (infoMmkn.getTrhSah() != null) {
                tarikhSah = sdf.format(infoMmkn.getTrhSah());
            }
            if (infoMmkn.getNoFailMmkn() != null) {
                noFail = infoMmkn.getNoFailMmkn();
            }
            if (infoMmkn.getTempatSidang() != null) {
                tempatBersidang = infoMmkn.getTempatSidang();
            }
            if (infoMmkn.getKodKpsn() != null) {
                kpsnMMKN = infoMmkn.getKodKpsn();
            }
            if (infoMmkn.getKeteranganMmkn() != null) {
                keterangan = infoMmkn.getKeteranganMmkn();
            }

        }

        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
        } else {
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            senaraiKandungan = new ArrayList<KandunganFolder>();
//            senaraiKandunganSebelum = new ArrayList<Dokumen>();

            if (pengguna != null) {
                getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
            }
            String id = getContext().getRequest().getParameter("folder.idFolder");
//            String filter = getContext().getRequest().getParameter("MMKN"); // to filter dokumen current permohonan
//            String filter2 = getContext().getRequest().getParameter("MMKT"); // to filter dokumen permohonan sebelum
//            if (id != null && id.length() > 0) {
//                folderDokumen = folderDAO.findById(Long.valueOf(id));
//            } else {
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
                    if (tugasanUtiliti != null) {
                        if (tugasanUtiliti.getKodPeringkat() != null) {
                            if (tugasanUtiliti.getKodPeringkat().getKod().equals("JKKPT")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("JKKPT") || kf.getDokumen().getKodDokumen().getKod().equals("JKKKT")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            } else if (tugasanUtiliti.getKodPeringkat().getKod().equals("RKMK") || tugasanUtiliti.getKodPeringkat().getKod().equals("RKMMK") 
                                    || tugasanUtiliti.getKodPeringkat().getKod().equals("RAMMK")
                                    || tugasanUtiliti.getKodPeringkat().getKod().equals("MMKBC")||tugasanUtiliti.getKodPeringkat().getKod().equals("ETHMK")||tugasanUtiliti.getKodPeringkat().getKod().equals("ET8MK")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }
                        }
                    } else if (stageId != null) {
                        if (permohonan.getKodUrusan().getKod().equals("831")) {
                            if (stageId.equals("DrafSrtMaklum") || stageId.equals("SemakanSrtMaklum") || stageId.equals("CetakSrtMaklum") || stageId.equals("SediaBorang5Atau6")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }
                            if (stageId.equals("rekod_surat_kpsn") || stageId.equals("semak_tandatangan") || stageId.equals("cetak_surat")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }

//                kodPeringkat = tugasanUtiliti.getKodPeringkat();
                        } else if (permohonan.getKodUrusan().getKod().equals("SEK4")) {
                            if (stageId.equals("rekod_surat_kpsn") || stageId.equals("semak_tandatangan") || stageId.equals("cetak_surat")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("SEK4A")) {
                            if (stageId.equals("rekod_kpsn_mmkn")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }

                        }else if (permohonan.getKodUrusan().getKod().equals("ESK4")) {
                            if (stageId.equals("rekod_kpsn_mmkn")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }

                        }
                    }
                }
            }

        }
    }

    public Resolution hantar() throws IOException {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanPengambilan ambil = pengambilanAPTService.findPermohonanPengambilanByIdMH(mohon.getIdPermohonan());
        BPelService service = new BPelService();
        task = service.getTaskFromBPel(taskId, pengguna);

        tugasanUtiliti = tugasanUtilitiDao.findById(idPermohonan);
        if (tugasanUtiliti != null) {
            infoMmkn = infoMmknService.findByIdMohonAndKodPeringkat(idPermohonan, tugasanUtiliti.getKodPeringkat().getKod());

        }
        tugasanUtiliti = null;
        stageId = infoMmkn.getKodPeringkat().getKod();

        if (mohon.getKodUrusan().getKod().equals("SEK4")) {
            RefPeringkat ref = new RefPeringkat();
            sek4IntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_MMK, mohon, pengguna);
        } else if (mohon.getKodUrusan().getKod().equals("SEK4A")) {
            etanah.ref.pengambilan.sek4aduan.RefPeringkat ref = new etanah.ref.pengambilan.sek4aduan.RefPeringkat();
            sek4AduanIntegrationFlowService.completeTask(ref.SURAT_BAYAR, mohon, pengguna);
        }else if (mohon.getKodUrusan().getKod().equals("ESK4")) {
            etanah.ref.pengambilan.myetapp.RefPeringkat ref = new etanah.ref.pengambilan.myetapp.RefPeringkat();
            myeTappIntegrationFlowService.completeTask(ref.SEK4_TERIMA_MMK, mohon, pengguna,mohon.getCawangan().getDaerah());
        }else if (mohon.getKodUrusan().getKod().equals("ESK8")) {
            etanah.ref.pengambilan.myetapp.RefPeringkat ref = new etanah.ref.pengambilan.myetapp.RefPeringkat();
            myeTappIntegrationFlowService.completeTask(ref.SEK8_TERIMA_MMK, mohon, pengguna,mohon.getCawangan().getDaerah());
        } else {
            if (ambil.getKatPengambilan().equals("1")) {
                etanah.ref.pengambilan.sek8a.RefPeringkat ref = new etanah.ref.pengambilan.sek8a.RefPeringkat();
                sek8aIntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_MMK, mohon, pengguna);
            } else {
                etanah.ref.pengambilan.sek8bc.RefPeringkat ref = new etanah.ref.pengambilan.sek8bc.RefPeringkat();

                if (stageId.equals("JKKPT")) {
                    sek8BCIntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_JKKPT, mohon, pengguna);
                } else {
                    sek8BCIntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_MMK_8BC, mohon, pengguna);
                }

            }

        }
        return new RedirectResolution("/tugasan/mmkn");
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
        return new JSP("/pengambilan/APT/muat_naik_APT.jsp").addParameter("popup", "true");
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
                            if (kf.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                                senaraiKandungan2.add(kf);
                            }
//                            else if (kodDokumen.equals("MMKN")) {
//                                senaraiKandungan2.add(kf);
//                            }
                        }
                        if (senaraiKandungan2.size() < 0) {
//                            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
//                            fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
//                            String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
//                            updatePathDokumen2(fizikalPath, Long.parseLong(dokumenId), contentType);
//                            addSimpleMessage("Muat naik fail berjaya.");
                        } else {
                            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                            fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                            String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                            updatePathDokumen(fizikalPath, senaraiKandungan2.get(0).getDokumen().getIdDokumen(), contentType);
                            addSimpleMessage("Muat naik fail berjaya.");
                        }
                    }
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
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        BPelService service = new BPelService();
//        task = service.getTaskFromBPel(taskId, pengguna);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        }

        if (infoMmkn != null) {
            if (infoMmkn.getKodPeringkat().getKod().equals("MMKBK") ) {
                senaraiKodMMK = listUtil.getSenaraiKodDokumenMMKN();
            }

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
                    dokumen.setTajuk(kd.getNama());
                    dokumen.setKodDokumen(kd);
//            dokumen.setDalamanNilai1(id);
                    dokumen = dokumenService.saveOrUpdate(dokumen);

                    KandunganFolder folderDokumen = new KandunganFolder();
                    folderDokumen.setInfoAudit(ia);
                    folderDokumen.setFolder(permohonan.getFolderDokumen());
                    folderDokumen.setDokumen(dokumen);
                    dokumenService.saveKandunganWithDokumen(folderDokumen);
//                KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, fd);
//                KandunganFolder kf = kandunganFolderService.findByIdFolder(permohonan)
//                if (kf == null) {
//                    kf = new KandunganFolder();
//                }
//                kf.setInfoAudit(ia);
//                kf.setFolder(fd);
//                kf.setDokumen(doc);
//                dokumenService.saveKandunganWithDokumen(kf);

                }
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

    public static void setLOG(Logger LOG) {
        RekodKpsnMMKActionBean.LOG = LOG;
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

}
