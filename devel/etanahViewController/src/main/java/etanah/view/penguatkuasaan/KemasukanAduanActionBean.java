/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.model.KodBangsa;
import etanah.model.KodJantina;
import etanah.model.KodWarganegara;
import etanah.model.KodWarnaKP;
import etanah.model.WarisOrangKenaSyak;
import etanah.report.ReportUtil;
import etanah.service.common.DokumenService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;

import etanah.dao.*;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCaraPermohonan;
import etanah.model.AduanLokasi;
import etanah.model.KodPemilikan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Alamat;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import etanah.view.kaunter.UrusanValue;
import etanah.service.AduanService;
import org.apache.commons.lang.StringUtils;
import able.stripes.JSP;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.KodDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodKlasifikasi;
import etanah.model.KodLot;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.workflow.WorkFlowService;
import etanah.service.KaunterService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/penguatkuasaan/kemasukan_aduan")
public class KemasukanAduanActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    private static final Logger log = Logger.getLogger(KemasukanAduanActionBean.class);
    private static final boolean debug = log.isDebugEnabled();
    private String beanFlag = "kemasukan_aduan";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private KodBandarPekanMukimDAO kodbandarPekanMukimDAO;
    @Inject
    private KodPemilikanDAO kodPemilikanDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private AduanLokasiDAO aduanLokasiDAO;
    @Inject
    private AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private AduanService aduanService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private WarisOrangKenaSyakDAO warisOrangKenaSyakDAO;
    @Inject
    private KodBangsaDAO kodBangsaDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private DokumenService dokumenService;
    @Inject
    private KodLotDAO kodLotDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    private ArrayList<OksValue> senaraiOKS = new ArrayList<OksValue>();
    private ArrayList<LokasiKejadianValue> senaraiLokasiKejadian = new ArrayList<LokasiKejadianValue>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KodCaraPermohonan> senaraiKodCaraPermohonan;
    private List<KodBandarPekanMukim> senaraiKodBandarPekanMukim;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> KodDokumenNotRequired = new ArrayList<KodDokumen>();
    private FolderDokumen folderDokumen;
    private String kodDaerah;
    private String daerah;
    private String sebab;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private String penyerahNoPengenalanBaru;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private KodNegeri penyerahNegeri;
    private String penyerahEmail;
    private String penyerahNoTelefon1;
    private AduanLokasi aduanLokasi;
    private KodPemilikan pemilikan;
    private KodBandarPekanMukim bandarPekanMukim;
    private String idPermohonan;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<AduanLokasi> senaraiAduanLokasi;
    private String noLot;
    private String lokasi;
    private String idAduanLokasi;
    private Permohonan permohonan;
    private KodCaraPermohonan caraPermohonan;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private WarisOrangKenaSyak warisOrangKenaSyak;
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private KodUrusan kodUrusan;
    private String nama;
    private Alamat alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeriO;
    private String noPengenalanBaru;
    private String noPengenalanLain;
    private String noTelefon1;
    private String idOrangKenaSyak;
    private String id;
    private String kod;
    private String bandar;
    private String jenisTanah;
    private Boolean enabledSeksyen49 = false;
    private List<KodUrusan> senaraiUrusan;
    private String senaraiHitam;
    private String tarikhSenaraiHitam;
    private String keterangan;
    private String jumlahSenaraiLokasi;
    private KodJenisPengenalan kodJenisPengenalan;
    private String tempatOksDitahan;
    private String tempatDireman;
    private String tarikhDitahan;
    private String tarikhDiBebas;
    private String diBebas;
    private KodJantina kodJantina;
    private KodWarnaKP kodWarnaKP;
    private KodWarganegara kodWarganegara;
    private String namaMajikan;
    private String noTelMajikan;
    private String noFaksMajikan;
    private String alamat1Majikan;
    private String alamat2Majikan;
    private String alamat3Majikan;
    private String alamat4Majikan;
    private String poskodMajikan;
    private KodNegeri kodNegeriMajikan;
    private String namaWaris;
    private String alamat1Waris;
    private String alamat2Waris;
    private String alamat3Waris;
    private String alamat4Waris;
    private String poskodWaris;
    private KodNegeri kodNegeriWaris;
    private String hubungan;
    private String noPengenalanWaris;
    private KodJenisPengenalan jenisPengenalanWaris;
    private KodBangsa kodBangsaWaris;
    private KodJantina kodJantinaWaris;
    private KodWarganegara kodWarganegaraWaris;
    private String noTelWaris;
    private String noTelBimbitWaris;
    private String kwg;
    private long idDokumen;
    private String pekerjaanOKS;
    private Date tarikhLahirOKS;
    private String kodBangsaOKS;
    private List<Permohonan> listMohon;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private HakmilikPermohonan hakmilikPermohonan;
    private List<KodBangsa> senaraiBangsa;
    private String hari;
    private String bulan;
    private String tahun;
    private String penyerahNoPengenalanLain;
    private KodLot kodLot;
    private String kodNegeri;
    private String hariReadonly;
    private String bulanReadonly;
    private String tahunReadonly;
    private static final Logger LOG = Logger.getLogger(KemasukanAduanActionBean.class);
    IWorkflowContext ctxOnBehalf = null;
    private String stageId;
    private String nextStage;
    private String taskId;
    private String kodUrusanTemp;
    private String dokumenKod;
    FileBean fileToBeUpload;

    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiPermohonan;
        ArrayList<OksValue> senaraiOKS;
        ArrayList<LokasiKejadianValue> senaraiLokasiKejadian;
        Permohonan permohonan;
        AduanLokasi aduanLokasi;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String bandarPekanMukim1 = "";
        cawangan = pguna.getKodCawangan();
        
        if (pguna.getKodCawangan().getDaerah() != null) {
            kodDaerah = pguna.getKodCawangan().getDaerah().getKod();
            daerah = pguna.getKodCawangan().getDaerah().getNama();            
            bandarPekanMukim1 = cawangan.getDaerah().getKod();
        } else {
            kodDaerah = "00";
            daerah = "PTG Melaka";
        }
        
        getContext().getRequest().setAttribute("kodDaerah", kodDaerah);        
        getContext().getRequest().setAttribute("daerah", daerah);
        getContext().getRequest().setAttribute("sebab", sebab);
        senaraiKodCaraPermohonan = aduanService.getSenaraiKodCaraPermohonan();
        senaraiKodBandarPekanMukim = aduanService.getSenaraiKodBandarPekanMukim();
        
        
        if (StringUtils.isNotBlank(bandarPekanMukim1)) {
            setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
        }
        else{            
            setListBandarPekanMukim(enforceService.getSenaraiKodBPMAll());
        }
              
        kodNegeri = conf.getProperty("kodNegeri");

        if (kodNegeri.equalsIgnoreCase("04")) {
            senaraiUrusan = aduanService.getSenaraiUrusanEnfMLK();
        } else {
            senaraiUrusan = aduanService.getSenaraiUrusan();
        }

        log.info("::: size senaraiUrusan : " + senaraiUrusan.size());
        senaraiBangsa = enforceService.senaraiBangsa();
        senaraiKandungan = new ArrayList<KandunganFolder>();

        jumlahSenaraiLokasi = String.valueOf(senaraiLokasiKejadian.size());


        getUrusanfromSession();

        String ids = getContext().getRequest().getParameter("folder.idFolder");
        if (ids != null && ids.length() > 0) {
            folderDokumen = folderDAO.findById(Long.valueOf(ids));
        } else {
            ids = getContext().getRequest().getParameter("permohonan.idPermohonan");
            if (ids != null && ids.length() > 0) {

                if (permohonan == null) {
                    permohonan = permohonanDAO.findById(ids);
                }
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

    @DefaultHandler
    public Resolution setAduan() {
        log.debug("setAduan");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_aduan.jsp");
    }

    public Resolution showForm() {
        log.debug("show form");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_aduan.jsp").addParameter("tab", true);
    }

    public Resolution setKembali() {
        log.debug("setKembali");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_aduan.jsp").addParameter("senaraiKandungan", senaraiKandungan);
    }

    public Resolution oksPopup() {
        log.debug("oksPopup");
        resetOKS();
        return new JSP("penguatkuasaan/oksPopupSave.jsp").addParameter("popup", "true");
    }

    public Resolution aduanLokasiPopup() {
        log.debug("aduanLokasiPopup");
        resetAduanLokasi();
        return new JSP("penguatkuasaan/aduanLokasiPopup.jsp").addParameter("popup", "true");
    }

    public Resolution oksSave() {
        log.debug("oksSave");
        try {
            if (StringUtils.isNotEmpty(hariReadonly)) {
                tarikhLahirOKS = dateF.parse(hariReadonly + "/" + bulanReadonly + "/" + tahunReadonly);
            } else {
                tarikhLahirOKS = dateF.parse(hari + "/" + bulan + "/" + tahun);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        OksValue ov = new OksValue();
        ov.setNama(nama);
        ov.setKodJenisPengenalan(kodJenisPengenalan);
        if (kodJenisPengenalan != null) {
            if (kodJenisPengenalan.getKod().equalsIgnoreCase("B")) {
                ov.setNoPengenalanBaru(noPengenalanBaru);
            } else {
                ov.setNoPengenalanLain(noPengenalanLain);
            }
        } else {
            ov.setNoPengenalanLain(noPengenalanLain);
        }

        ov.setNoTelefon1(noTelefon1);
        ov.setSenaraiHitam(senaraiHitam);
        ov.setKeterangan(keterangan);
        ov.setTarikhSenaraiHitam(tarikhSenaraiHitam);

        ov.setTarikhDitahan(tarikhDitahan);
        ov.setTarikhDiBebas(tarikhDiBebas);
        ov.setTempatDireman(tempatDireman);
        ov.setTempatOksDitahan(tempatOksDitahan);
        ov.setDiBebas(diBebas);
        ov.setNamaMajikan(namaMajikan);
        ov.setNoTelMajikan(noTelMajikan);
        ov.setNoFaksMajikan(noFaksMajikan);
        ov.setAlamat1Majikan(alamat1Majikan);
        ov.setAlamat2Majikan(alamat2Majikan);
        ov.setAlamat3Majikan(alamat3Majikan);
        ov.setAlamat4Majikan(alamat4Majikan);
        ov.setPoskodMajikan(poskodMajikan);
        ov.setKodNegeriMajikan(kodNegeriMajikan);
        ov.setKodWarganegara(kodWarganegara);
        ov.setKodWarnaKP(kodWarnaKP);
        ov.setKodJantina(kodJantina);
        ov.setNamaWaris(namaWaris);
        ov.setAlamat1Waris(alamat1Waris);
        ov.setAlamat2Waris(alamat2Waris);
        ov.setAlamat3Waris(alamat3Waris);
        ov.setAlamat4Waris(alamat4Waris);
        ov.setPoskodWaris(poskodWaris);
        ov.setKodNegeriWaris(kodNegeriWaris);
//        ov.setKodWarganegaraWaris(kodWarganegaraWaris);
        ov.setHubungan(hubungan);
        ov.setNoPengenalanWaris(noPengenalanWaris);
        ov.setJenisPengenalanWaris(jenisPengenalanWaris);
        ov.setKodJantinaWaris(kodJantinaWaris);
        ov.setNoTelBimbitWaris(noTelBimbitWaris);
        ov.setNoTelWaris(noTelWaris);
//        ov.setKodBangsaWaris(kodBangsaWaris);
        ov.setTarikhLahirOKS(tarikhLahirOKS);
        ov.setPekerjaanOKS(pekerjaanOKS);
        ov.setKodBangsaOKS(kodBangsaOKS);

        Alamat al = new Alamat();
        al.setAlamat1(alamat1);
        al.setAlamat2(alamat2);
        al.setAlamat3(alamat3);
        al.setAlamat4(alamat4);
        al.setPoskod(poskod);

        if (negeriO != null) {
            KodNegeri kn = new KodNegeri();
            kn = kodNegeriDAO.findById(negeriO);
            al.setNegeri(kn);
        } else {
            al.setNegeri(null);
        }
        ov.setAlamat(al);
        senaraiOKS.add(ov);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

    //edit oks
    public Resolution oksPopup2() {
        log.debug("oksPopup2");
//        tarikhLahirOKS = hari + "/" + bulan + "/" + tahun;
        id = getContext().getRequest().getParameter("id");
        int idInt = Integer.parseInt(id);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            nama = u.senaraiOKS.get(idInt).getNama();
            kodJenisPengenalan = u.senaraiOKS.get(idInt).getKodJenisPengenalan();
            if (kodJenisPengenalan != null) {
                if (u.senaraiOKS.get(idInt).getKodJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                    noPengenalanBaru = u.senaraiOKS.get(idInt).getNoPengenalanBaru();
                } else {
                    noPengenalanLain = u.senaraiOKS.get(idInt).getNoPengenalanLain();
                }
            } else {
                noPengenalanLain = u.senaraiOKS.get(idInt).getNoPengenalanLain();
            }
            keterangan = u.senaraiOKS.get(idInt).getKeterangan();
            senaraiHitam = u.senaraiOKS.get(idInt).getSenaraiHitam();
            tarikhSenaraiHitam = u.senaraiOKS.get(idInt).getTarikhSenaraiHitam();
            alamat1 = u.senaraiOKS.get(idInt).getAlamat().getAlamat1();
            alamat2 = u.senaraiOKS.get(idInt).getAlamat().getAlamat2();
            alamat3 = u.senaraiOKS.get(idInt).getAlamat().getAlamat3();
            alamat4 = u.senaraiOKS.get(idInt).getAlamat().getAlamat4();
            poskod = u.senaraiOKS.get(idInt).getAlamat().getPoskod();
            tempatOksDitahan = u.senaraiOKS.get(idInt).getTempatOksDitahan();
            tempatDireman = u.senaraiOKS.get(idInt).getTempatDireman();
            tarikhDiBebas = u.senaraiOKS.get(idInt).getTarikhDiBebas();
            tarikhDitahan = u.senaraiOKS.get(idInt).getTarikhDitahan();
            diBebas = u.senaraiOKS.get(idInt).getDiBebas();
            kodWarnaKP = u.senaraiOKS.get(idInt).getKodWarnaKP();
            kodJantina = u.senaraiOKS.get(idInt).getKodJantina();
            kodWarganegara = u.senaraiOKS.get(idInt).getKodWarganegara();
            namaMajikan = u.senaraiOKS.get(idInt).getNamaMajikan();
            noTelMajikan = u.senaraiOKS.get(idInt).getNoTelMajikan();
            noFaksMajikan = u.senaraiOKS.get(idInt).getNoFaksMajikan();
            alamat1Majikan = u.senaraiOKS.get(idInt).getAlamat1Majikan();
            alamat2Majikan = u.senaraiOKS.get(idInt).getAlamat2Majikan();
            alamat3Majikan = u.senaraiOKS.get(idInt).getAlamat3Majikan();
            alamat4Majikan = u.senaraiOKS.get(idInt).getAlamat4Majikan();
            poskodMajikan = u.senaraiOKS.get(idInt).getPoskodMajikan();
            kodNegeriMajikan = u.senaraiOKS.get(idInt).getKodNegeriMajikan();
            namaWaris = u.senaraiOKS.get(idInt).getNamaWaris();
            hubungan = u.senaraiOKS.get(idInt).getHubungan();
            alamat1Waris = u.senaraiOKS.get(idInt).getAlamat1Waris();
            alamat2Waris = u.senaraiOKS.get(idInt).getAlamat2Waris();
            alamat3Waris = u.senaraiOKS.get(idInt).getAlamat3Waris();
            alamat4Waris = u.senaraiOKS.get(idInt).getAlamat4Waris();
            poskodWaris = u.senaraiOKS.get(idInt).getPoskodWaris();
            kodNegeriWaris = u.senaraiOKS.get(idInt).getKodNegeriWaris();
            jenisPengenalanWaris = u.senaraiOKS.get(idInt).getJenisPengenalanWaris();
            noPengenalanWaris = u.senaraiOKS.get(idInt).getNoPengenalanWaris();
            kodJantinaWaris = u.senaraiOKS.get(idInt).getKodJantinaWaris();
            noTelWaris = u.senaraiOKS.get(idInt).getNoTelWaris();
            noTelBimbitWaris = u.senaraiOKS.get(idInt).getNoTelBimbitWaris();
            tarikhLahirOKS = u.senaraiOKS.get(idInt).getTarikhLahirOKS();
            System.out.println("tarikhLahirOKS :" + tarikhLahirOKS);
            try {
                if (tarikhLahirOKS != null) {
                    hari = dateF.format(tarikhLahirOKS).substring(0, 2);
                    System.out.println("hari" + hari);
                    if (hari.startsWith("0")) {
                        System.out.println("hari ada 0");
                        hari = dateF.format(tarikhLahirOKS).substring(1, 2);
                        System.out.println("value hari ada 0 : " + hari);
                    }
                    bulan = dateF.format(tarikhLahirOKS).substring(3, 5);
                    System.out.println("bulan" + bulan);
                    if (bulan.startsWith("0")) {
                        System.out.println("bulan ada 0");
                        bulan = dateF.format(tarikhLahirOKS).substring(4, 5);
                        System.out.println("value bulan ada 0 : " + bulan);
                    }
                    tahun = dateF.format(tarikhLahirOKS).substring(6, 10);
                    System.out.println("tahun" + tahun);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }

            kodBangsaOKS = u.senaraiOKS.get(idInt).getKodBangsaOKS();
            pekerjaanOKS = u.senaraiOKS.get(idInt).getPekerjaanOKS();


            if (u.senaraiOKS.get(idInt).getAlamat().getNegeri() != null) {
                negeriO = u.senaraiOKS.get(idInt).getAlamat().getNegeri().getKod();
            }
            noTelefon1 = u.senaraiOKS.get(idInt).getNoTelefon1();
        } else {
            log.debug("no data in session");
        }
        return new JSP("penguatkuasaan/oksPopupEdit.jsp").addParameter("popup", "true");
    }

    public Resolution editOKS() {
        log.debug("editOKS");
        try {
            if (StringUtils.isNotBlank(hariReadonly) && StringUtils.isNotBlank(bulanReadonly) && StringUtils.isNotBlank(tahunReadonly)) {
                tarikhLahirOKS = dateF.parse(hariReadonly + "/" + bulanReadonly + "/" + tahunReadonly);
            } else {
                tarikhLahirOKS = dateF.parse(hari + "/" + bulan + "/" + tahun);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        id = getContext().getRequest().getParameter("id");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            OksValue ov = new OksValue();
            ov.setNama(nama);
            ov.setKodJenisPengenalan(kodJenisPengenalan);
            if (kodJenisPengenalan != null) {
                if (kodJenisPengenalan.getKod().equalsIgnoreCase("B")) {
                    ov.setNoPengenalanBaru(noPengenalanBaru);
                } else {
                    ov.setNoPengenalanLain(noPengenalanLain);
                }
            } else {
                ov.setNoPengenalanLain(noPengenalanLain);
            }

            ov.setNoTelefon1(noTelefon1);
            ov.setSenaraiHitam(senaraiHitam);
            ov.setKeterangan(keterangan);
            ov.setTarikhSenaraiHitam(tarikhSenaraiHitam);
            ov.setDiBebas(diBebas);
            ov.setTarikhDiBebas(tarikhDiBebas);
            ov.setTarikhDitahan(tarikhDitahan);
            ov.setTempatDireman(tempatDireman);
            ov.setTempatOksDitahan(tempatOksDitahan);
            ov.setKodJantina(kodJantina);
            ov.setKodWarnaKP(kodWarnaKP);
            ov.setKodWarganegara(kodWarganegara);
            ov.setNamaMajikan(namaMajikan);
            ov.setNoTelMajikan(noTelMajikan);
            ov.setNoFaksMajikan(noFaksMajikan);
            ov.setAlamat1Majikan(alamat1Majikan);
            ov.setAlamat2Majikan(alamat2Majikan);
            ov.setAlamat3Majikan(alamat3Majikan);
            ov.setAlamat4Majikan(alamat4Majikan);
            ov.setPoskodMajikan(poskodMajikan);
            ov.setKodNegeriMajikan(kodNegeriMajikan);
            ov.setNamaWaris(namaWaris);
            ov.setJenisPengenalanWaris(jenisPengenalanWaris);
            ov.setNoPengenalanWaris(noPengenalanWaris);
            ov.setAlamat1Waris(alamat1Waris);
            ov.setAlamat2Waris(alamat2Waris);
            ov.setAlamat3Waris(alamat3Waris);
            ov.setAlamat4Waris(alamat4Waris);
            ov.setPoskodWaris(poskodWaris);
            ov.setKodJantinaWaris(kodJantinaWaris);
            ov.setNoTelWaris(noTelWaris);
            ov.setNoTelBimbitWaris(noTelBimbitWaris);
            ov.setHubungan(hubungan);
            ov.setKodNegeriWaris(kodNegeriWaris);
            ov.setTarikhLahirOKS(tarikhLahirOKS);
            ov.setPekerjaanOKS(pekerjaanOKS);
            ov.setKodBangsaOKS(kodBangsaOKS);

            Alamat al = new Alamat();
            al.setAlamat1(alamat1);
            al.setAlamat2(alamat2);
            al.setAlamat3(alamat3);
            al.setAlamat4(alamat4);
            al.setPoskod(poskod);
            if (negeriO != null) {
                KodNegeri kn = new KodNegeri();
                kn = kodNegeriDAO.findById(negeriO);
                al.setNegeri(kn);
            }
            ov.setAlamat(al);
            u.senaraiOKS.set(Integer.parseInt(id), ov);
        } else {
            log.debug("no data in session");
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

    public Resolution removeOKS() {
        log.debug("removeOKS");
        id = getContext().getRequest().getParameter("id");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            ArrayList<OksValue> ov = u.senaraiOKS;
            for (Iterator<OksValue> it = ov.iterator(); it.hasNext();) {
                OksValue ov1 = it.next();
                if (Integer.parseInt(id) == ov.indexOf(ov1)) {
                    it.remove();
                    break;
                }
            }
        } else {
            log.debug("no data in session");
        }

        saveToSession(ctx);



        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

//    @HandlesEvent("refreshPage")
    public Resolution refreshPage() {
        log.debug("refreshPage");
        return setAduan();
//        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

//    @HandlesEvent("save")
    public Resolution saveMohon() {
        log.debug("saveMohon");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        if (permohonan != null) {
            folderDokumen = permohonan.getFolderDokumen();
            if (folderDokumen != null) {
                if (folderDokumen.getSenaraiKandungan() != null) {
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
            }

//            senaraiKandungan = permohonan.getFolderDokumen().getSenaraiKandungan();
//            folderDokumen.setSenaraiKandungan(permohonan.getFolderDokumen().getSenaraiKandungan());
        }

        kodUrusan = new KodUrusan();

        if (kodNegeri.equalsIgnoreCase("04")) {
            if (getContext().getRequest().getParameter("kodUrusan.kod").equals("")) {
                kodUrusan = kodUrusanDAO.findById("ADUAN");
            } else if (!pengguna.getPerananUtama().getKumpBPEL().equals("PPTanah")) {//pptptdkuasa
                log.info(":::: Kemasukkan aduan for PT");
                if (getContext().getRequest().getParameter("kodUrusan.kod").equals("426") || getContext().getRequest().getParameter("kodUrusan.kod").equals("425")
                        || getContext().getRequest().getParameter("kodUrusan.kod").equals("425A") || getContext().getRequest().getParameter("kodUrusan.kod").equals("127")) { // || getContext().getRequest().getParameter("kodUrusan.kod").equals("127")
                    kodUrusan = kodUrusanDAO.findById("999");
                    kodUrusanTemp = getContext().getRequest().getParameter("kodUrusan.kod");
                } else {
                    kodUrusan = kodUrusanDAO.findById(getContext().getRequest().getParameter("kodUrusan.kod"));
                    kodUrusanTemp = getContext().getRequest().getParameter("kodUrusan.kod");
                }
            } else {
                log.info(":::: Kemasukkan aduan for PPT");
                kodUrusan = kodUrusanDAO.findById(getContext().getRequest().getParameter("kodUrusan.kod"));
                kodUrusanTemp = getContext().getRequest().getParameter("kodUrusan.kod");
            }
        } else {
            //for NS
            if (getContext().getRequest().getParameter("kodUrusan.kod").equals("")) {
                kodUrusan = kodUrusanDAO.findById("ADUAN");
            } else {
                kodUrusan = kodUrusanDAO.findById(getContext().getRequest().getParameter("kodUrusan.kod"));
            }
        }

        log.info("kod urusan temp ::::: " + kodUrusanTemp);
        log.info("kod urusan  ::::: " + kodUrusan.getKod());

        if (permohonan == null) {
            idPermohonan = idPermohonanGenerator.generate(
                    ctx.getKodNegeri(), pengguna.getKodCawangan(), kodUrusan);
            permohonan = new Permohonan();
        }


//        permohonan = new Permohonan();

        permohonan.setStatus("TA");
        permohonan.setIdPermohonan(idPermohonan);
        permohonan.setCawangan(pengguna.getKodCawangan());
        permohonan.setKodUrusan(kodUrusan);
        permohonan.setRujukanUndang2(kodUrusanTemp);
        permohonan.setCaraPermohonan(caraPermohonan);
        permohonan.setSebab(sebab);
        permohonan.setPenyerahNama(penyerahNama);
        permohonan.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
        if (penyerahJenisPengenalan != null) {
            if (penyerahJenisPengenalan.getKod().equalsIgnoreCase("B")) {
                permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanBaru);
            } else {
                permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
            }
        } else {
            permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
        }

        permohonan.setPenyerahAlamat1(penyerahAlamat1);
        permohonan.setPenyerahAlamat2(penyerahAlamat2);
        permohonan.setPenyerahAlamat3(penyerahAlamat3);
        permohonan.setPenyerahAlamat4(penyerahAlamat4);
        permohonan.setPenyerahPoskod(penyerahPoskod);
        permohonan.setPenyerahNegeri(penyerahNegeri);
        permohonan.setPenyerahNoTelefon1(penyerahNoTelefon1);
        permohonan.setPenyerahEmail(penyerahEmail);
        permohonan.setInfoAudit(ia);
//        enforceService.savePermohonan(permohonan);
//        if (folderDokumen != null) {
//            permohonan.setFolderDokumen(folderDokumen);
//        }
        aduanLokasi = new AduanLokasi();
        aduanLokasi.setPermohonan(permohonan);

        if (bandarPekanMukim == null) {
            String[] tname = {"bandarPekanMukim"};
            Object[] tvalue = {"00"};
            for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                aduanLokasi.setBandarPekanMukim(kbpm);
            }
        } else {
            aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
        }

        aduanLokasi.setCawangan(caw);
        aduanLokasi.setPemilikan(pemilikan);
        aduanLokasi.setNoLot(noLot);
        aduanLokasi.setKodLot(kodLot);
        aduanLokasi.setLokasi(lokasi);

        saveToSession(ctx);

        addSimpleMessage("ID Aduan : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution addDocForm() {
        log.debug("addDocForm");
        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
//            for (int i = 0; i < 3; i++) {
            KandunganFolder kf = new KandunganFolder();
            kandunganFolderTamb.add(kf);
//            }
        }
        return new JSP("penguatkuasaan/kemasukan_tambahan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        log.debug("simpanDokumenTambahan");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

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

            permohonan.setFolderDokumen(folderDokumen);
            saveToSession(ctx);

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
        return new RedirectResolution(KemasukanAduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public Resolution reload() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution deleteSelected() {
        log.debug("deleteSelected");
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
        return new RedirectResolution(KemasukanAduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    //simpan semua data
    public Resolution simpan() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
//        tarikhLahirOKS = hari + "/" + bulan + "/" + tahun;

        System.out.println("::::::::::::: " + pengguna.getPerananUtama().getKumpBPEL());
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

            log.debug("kodUrusan :" + permohonan.getKodUrusan().getKod());
            log.debug("kodUrusan temp:" + permohonan.getRujukanUndang2());

            aduanLokasi.setInfoAudit(ia);
            aduanLokasiDAO.save(aduanLokasi);

            if (kodNegeri.equalsIgnoreCase("05") && permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {

                /* save lokasi aduan to table mohon_hakmilik (for GIS)*/
                log.debug("------------save to table mohon_hakmilik--------------------");
                hakmilikPermohonan = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);
                if (hakmilikPermohonan == null) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    ia = hakmilikPermohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                }
                hakmilikPermohonan.setHakmilik(null);
                hakmilikPermohonan.setPermohonan(permohonan);
                hakmilikPermohonan.setInfoAudit(ia);

                if (bandarPekanMukim == null) {
                    String[] tname = {"bandarPekanMukim"};
                    Object[] tvalue = {"00"};
                    for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                        hakmilikPermohonan.setBandarPekanMukimBaru(kbpm);
                    }
                } else {
                    hakmilikPermohonan.setBandarPekanMukimBaru(bandarPekanMukim);
                }

                hakmilikPermohonan.setNoLot(noLot);
                enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
            }

            if (kodNegeri.equalsIgnoreCase("04")) {
                if (permohonan.getRujukanUndang2().equalsIgnoreCase("127")) { //remove || permohonan.getRujukanUndang2().equalsIgnoreCase("425") || permohonan.getRujukanUndang2().equalsIgnoreCase("425A") 3/6/2014
                    log.debug("------------save to table pemohon--------------------");
                    Pemohon p = new Pemohon();
                    p.setPermohonan(permohonan);
                    p.setCawangan(cawangan);
                    p.setInfoAudit(ia);
                    p.setNama(penyerahNama);
                    enforcementService.simpanMaklumatPihak(p);
                }
            }


            for (int i = 0; i < senaraiOKS.size(); i++) {
                aduanOrangKenaSyak = new AduanOrangKenaSyak();
                aduanOrangKenaSyak.setDiBebas(senaraiOKS.get(i).getDiBebas());
                aduanOrangKenaSyak.setNama(senaraiOKS.get(i).getNama());
                aduanOrangKenaSyak.setAlamat(senaraiOKS.get(i).getAlamat());
                aduanOrangKenaSyak.setCawangan(pengguna.getKodCawangan());
                System.out.println("senaraiOKS.get(i).getKodJenisPengenalan() : " + senaraiOKS.get(i).getKodJenisPengenalan());
                aduanOrangKenaSyak.setKodJenisPengenalan(senaraiOKS.get(i).getKodJenisPengenalan());
                if (senaraiOKS.get(i).getKodJenisPengenalan() != null) {
                    if (senaraiOKS.get(i).getKodJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                        aduanOrangKenaSyak.setNoPengenalan(senaraiOKS.get(i).getNoPengenalanBaru());
                    } else {
                        aduanOrangKenaSyak.setNoPengenalan(senaraiOKS.get(i).getNoPengenalanLain());
                    }
                } else {
                    aduanOrangKenaSyak.setNoPengenalan(senaraiOKS.get(i).getNoPengenalanLain());
                }

                aduanOrangKenaSyak.setPermohonan(permohonan);
                aduanOrangKenaSyak.setNoTelefon1(senaraiOKS.get(i).getNoTelefon1());
                aduanOrangKenaSyak.setKeterangan(senaraiOKS.get(i).getKeterangan());
                aduanOrangKenaSyak.setSenaraiHitam(senaraiOKS.get(i).getSenaraiHitam());
                aduanOrangKenaSyak.setTempatDireman(senaraiOKS.get(i).getTempatDireman());
                aduanOrangKenaSyak.setTempatOksDitahan(senaraiOKS.get(i).getTempatOksDitahan());
                aduanOrangKenaSyak.setNamaMajikan(senaraiOKS.get(i).getNamaMajikan());
                aduanOrangKenaSyak.setKodJantina(senaraiOKS.get(i).getKodJantina());
                aduanOrangKenaSyak.setKodWarnaKP(senaraiOKS.get(i).getKodWarnaKP());
                System.out.println("senaraiOKS.get(i).getKodWarnaKP() :" + senaraiOKS.get(i).getKodWarnaKP());
                aduanOrangKenaSyak.setKodWarganegara(senaraiOKS.get(i).getKodWarganegara());
                aduanOrangKenaSyak.setNoTelMajikan(senaraiOKS.get(i).getNoTelMajikan());
                aduanOrangKenaSyak.setNoFaksMajikan(senaraiOKS.get(i).getNoFaksMajikan());
                aduanOrangKenaSyak.setAlamat1Majikan(senaraiOKS.get(i).getAlamat1Majikan());
                aduanOrangKenaSyak.setAlamat2Majikan(senaraiOKS.get(i).getAlamat2Majikan());
                aduanOrangKenaSyak.setAlamat3Majikan(senaraiOKS.get(i).getAlamat3Majikan());
                aduanOrangKenaSyak.setAlamat4Majikan(senaraiOKS.get(i).getAlamat4Majikan());
                aduanOrangKenaSyak.setPoskodMajikan(senaraiOKS.get(i).getPoskodMajikan());
                aduanOrangKenaSyak.setKodNegeriMajikan(senaraiOKS.get(i).getKodNegeriMajikan());
                aduanOrangKenaSyak.setPekerjaan(senaraiOKS.get(i).getPekerjaanOKS());
                if (senaraiOKS.get(i).getKodBangsaOKS() != null) {
                    KodBangsa bangsaOKS = kodBangsaDAO.findById(senaraiOKS.get(i).getKodBangsaOKS());
                    aduanOrangKenaSyak.setKodBangsa(bangsaOKS);
                }


                //maklumat waris OKS
                warisOrangKenaSyak = new WarisOrangKenaSyak();
                warisOrangKenaSyak.setCawangan(pengguna.getKodCawangan());
                warisOrangKenaSyak.setNama(senaraiOKS.get(i).getNamaWaris());
                warisOrangKenaSyak.setAlamat1(senaraiOKS.get(i).getAlamat1Waris());
                warisOrangKenaSyak.setAlamat2(senaraiOKS.get(i).getAlamat2Waris());
                warisOrangKenaSyak.setAlamat3(senaraiOKS.get(i).getAlamat3Waris());
                warisOrangKenaSyak.setAlamat4(senaraiOKS.get(i).getAlamat4Waris());
                warisOrangKenaSyak.setPoskod(senaraiOKS.get(i).getPoskodWaris());
                warisOrangKenaSyak.setNegeri(senaraiOKS.get(i).getKodNegeriWaris());
                warisOrangKenaSyak.setHubungan(senaraiOKS.get(i).getHubungan());
                warisOrangKenaSyak.setNoPengenalan(senaraiOKS.get(i).getNoPengenalanWaris());
                warisOrangKenaSyak.setJenisPengenalan(senaraiOKS.get(i).getJenisPengenalanWaris());
                warisOrangKenaSyak.setNoTelefon(senaraiOKS.get(i).getNoTelWaris());
                warisOrangKenaSyak.setNoTelefonBimbit(senaraiOKS.get(i).getNoTelBimbitWaris());
//                warisOrangKenaSyak.setKodWarganegara(senaraiOKS.get(i).getKodWarganegaraWaris());
//                warisOrangKenaSyak.setKodBangsa(senaraiOKS.get(i).getKodBangsaWaris());
                warisOrangKenaSyak.setKodJantina(senaraiOKS.get(i).getKodJantinaWaris());
//                System.out.println("kod jantina waris ialah :" + senaraiOKS.get(i).getKodJantinaWaris());
//                System.out.println("senaraiOKS.get(i).getKodNegeriMajikan():" + senaraiOKS.get(i).getKodNegeriMajikan());


                if (senaraiOKS.get(i).getTarikhDiBebas() != null) {
                    try {
                        aduanOrangKenaSyak.setTarikhDiBebas(dateF.parse(senaraiOKS.get(i).getTarikhDiBebas()));
                    } catch (ParseException ex) {
                        System.out.println("Got error" + ex);
                    }
                }
                if (senaraiOKS.get(i).getTarikhDitahan() != null) {
                    try {
                        aduanOrangKenaSyak.setTarikhDitahan(dateF.parse(senaraiOKS.get(i).getTarikhDitahan()));
                    } catch (ParseException ex) {
                        System.out.println("Got error" + ex);
                    }
                }

                if (senaraiOKS.get(i).getTarikhSenaraiHitam() != null) {
                    try {
                        aduanOrangKenaSyak.setTarikhSenaraiHitam(dateF.parse(senaraiOKS.get(i).getTarikhSenaraiHitam()));
                    } catch (ParseException ex) {
                        System.out.println("Got error" + ex);
                    }
                }

                if (senaraiOKS.get(i).getTarikhLahirOKS() != null) {
                    try {
                        aduanOrangKenaSyak.setTarikhlahir(senaraiOKS.get(i).getTarikhLahirOKS());
                    } catch (Exception ex) {
                        System.out.println("Got error" + ex);
                    }
                }
                aduanOrangKenaSyak.setInfoAudit(ia);
                aduanOrangKenaSyakDAO.save(aduanOrangKenaSyak);
                warisOrangKenaSyak.setInfoAudit(ia);
                warisOrangKenaSyak.setAduanOrangKenaSyak(aduanOrangKenaSyak);
                warisOrangKenaSyakDAO.save(warisOrangKenaSyak);
            }

            if (!permohonan.getKodUrusan().getKod().equals("ADUAN")) {
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

            if (!permohonan.getKodUrusan().getKod().equals("ADUAN") && pengguna.getPerananUtama().getKumpBPEL().equals("PPTanah")) {


                LOG.info("------------- SKIP STAGE ---------------");

                ctxOnBehalf = WorkFlowService.authenticate("ptenf1"); //pptd
                if (ctxOnBehalf != null) {
                    System.out.println("ctxOnBehalf : " + ctxOnBehalf);
                    System.out.println("id mohon : " + permohonan.getIdPermohonan());

                    List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                    LOG.info("1) Task FOund(size)::" + l.size());
                    if (l.isEmpty()) {
                        try {
                            Thread.sleep(5000);
                            l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                        } catch (Exception ex) {
                            LOG.error(ex);
                        }
                    }
                    LOG.info("2) Task FOund (size)::" + l.size());
                    for (Task t : l) {
                        stageId = t.getSystemAttributes().getStage();
                        taskId = t.getSystemAttributes().getTaskId();
                        WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                        LOG.debug("Claim Found Task::" + taskId);
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                            System.out.println("----- for sek426 or sek425----- ");
                            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "AT"); //
                        }


                        LOG.debug("stage id ::::::::::::::::" + stageId);
                        LOG.debug("next stage ::::::::::::::::" + nextStage);
                        LOG.debug("Tugasan dihantar ke : " + nextStage);
                    }
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
            log.info(kodResit.getNama());
            resit.setTajuk(kodResit.getNama());
            resit.setPerihal("T");//save temp as flag at sedia_laporan1 (sek999)
            resit = dokumenDAO.saveOrUpdate(resit);
            log.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
            idDokumen = resit.getIdDokumen();
            log.info("ID DOKUMEN: " + idDokumen);
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
            log.info("NAMA FIZIKAL TO BE SET AT DOKUMEN : " + reportUtil.getDMSPath());
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
            resetAll();
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tamat.jsp");
    }

    public void resetAll() {
        log.debug("resetAll");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        caraPermohonan = null;
        sebab = null;
        penyerahJenisPengenalan = null;
        penyerahNoPengenalanBaru = null;
        penyerahNoPengenalanLain = null;
        penyerahNama = null;
        penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4 =
                penyerahPoskod = null;
        penyerahNegeri = null;
        penyerahEmail = null;
        penyerahNoTelefon1 = null;
        bandarPekanMukim = null;
        pemilikan = null;
        lokasi = null;
    }

    public void resetOKS() {
        log.debug("resetOKS");
        nama = null;
        noPengenalanBaru = null;
        noPengenalanLain = null;
        alamat1 = alamat2 = alamat3 = alamat4 = null;
        keterangan = null;
        senaraiHitam = null;
        tarikhSenaraiHitam = null;
        poskod = null;
        negeriO = null;
        noTelefon1 = null;
    }

    public void resetAduanLokasi() {
        log.debug("resetAduanLokasi");
        bandarPekanMukim = null;
        pemilikan = null;
        noLot = null;
        lokasi = null;
    }

    public final void saveToSession(etanahActionBeanContext ctx) {
        log.debug("saveToSession");
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
        u.senaraiOKS = senaraiOKS;
        u.senaraiLokasiKejadian = senaraiLokasiKejadian;
        u.permohonan = permohonan;
        u.aduanLokasi = aduanLokasi;

        ctx.setWorkData(u);
    }

    public final void getUrusanfromSession() {
        log.debug("getUrusanfromSession");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            UrusanCache u = (UrusanCache) ctx.getWorkData();
            if (u != null) {
                for (int i = 0; i < u.senaraiOKS.size(); i++) {
                    OksValue ov = new OksValue();
                    ov.setNama(u.senaraiOKS.get(i).getNama());
                    ov.setKodJenisPengenalan(u.senaraiOKS.get(i).getKodJenisPengenalan());
                    if (u.senaraiOKS.get(i).getKodJenisPengenalan() != null) {
                        if (u.senaraiOKS.get(i).getKodJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                            ov.setNoPengenalanBaru(u.senaraiOKS.get(i).getNoPengenalanBaru());
                        } else {
                            ov.setNoPengenalanLain(u.senaraiOKS.get(i).getNoPengenalanLain());
                        }
                    } else {
                        ov.setNoPengenalanLain(u.senaraiOKS.get(i).getNoPengenalanLain());
                    }

                    ov.setNoTelefon1(u.senaraiOKS.get(i).getNoTelefon1());
                    ov.setAlamat(u.senaraiOKS.get(i).getAlamat());
                    ov.setKeterangan(u.senaraiOKS.get(i).getKeterangan());
                    ov.setSenaraiHitam(u.senaraiOKS.get(i).getSenaraiHitam());
                    ov.setTarikhSenaraiHitam(u.senaraiOKS.get(i).getTarikhSenaraiHitam());
                    ov.setTempatOksDitahan(u.senaraiOKS.get(i).getTempatOksDitahan());
                    ov.setTempatDireman(u.senaraiOKS.get(i).getTempatDireman());
                    ov.setTarikhDitahan(u.senaraiOKS.get(i).getTarikhDitahan());
                    ov.setTarikhDiBebas(u.senaraiOKS.get(i).getTarikhDiBebas());
                    ov.setDiBebas(u.senaraiOKS.get(i).getDiBebas());
                    ov.setKodWarganegara(u.senaraiOKS.get(i).getKodWarganegara());
                    ov.setKodWarnaKP(u.senaraiOKS.get(i).getKodWarnaKP());
                    ov.setKodJantina(u.senaraiOKS.get(i).getKodJantina());
                    ov.setNamaMajikan(u.senaraiOKS.get(i).getNamaMajikan());
                    ov.setNoTelMajikan(u.senaraiOKS.get(i).getNoTelMajikan());
                    ov.setNoFaksMajikan(u.senaraiOKS.get(i).getNoFaksMajikan());
                    ov.setAlamat1Majikan(u.senaraiOKS.get(i).getAlamat1Majikan());
                    ov.setAlamat2Majikan(u.senaraiOKS.get(i).getAlamat2Majikan());
                    ov.setAlamat3Majikan(u.senaraiOKS.get(i).getAlamat3Majikan());
                    ov.setAlamat4Majikan(u.senaraiOKS.get(i).getAlamat4Majikan());
                    ov.setPoskodMajikan(u.senaraiOKS.get(i).getPoskodMajikan());
                    ov.setKodNegeriMajikan(u.senaraiOKS.get(i).getKodNegeriMajikan());
                    ov.setNamaWaris(u.senaraiOKS.get(i).getNamaWaris());
                    ov.setJenisPengenalanWaris(u.senaraiOKS.get(i).getJenisPengenalanWaris());
                    ov.setNoPengenalanWaris(u.senaraiOKS.get(i).getNoPengenalanWaris());
                    ov.setAlamat1Waris(u.senaraiOKS.get(i).getAlamat1Waris());
                    ov.setAlamat2Waris(u.senaraiOKS.get(i).getAlamat2Waris());
                    ov.setAlamat3Waris(u.senaraiOKS.get(i).getAlamat3Waris());
                    ov.setAlamat4Waris(u.senaraiOKS.get(i).getAlamat4Waris());
                    ov.setPoskodWaris(u.senaraiOKS.get(i).getPoskodWaris());
                    ov.setKodNegeriWaris(u.senaraiOKS.get(i).getKodNegeriWaris());
                    ov.setKodJantinaWaris(u.senaraiOKS.get(i).getKodJantinaWaris());
                    ov.setNoTelWaris(u.senaraiOKS.get(i).getNoTelWaris());
                    ov.setNoTelBimbitWaris(u.senaraiOKS.get(i).getNoTelBimbitWaris());
                    ov.setHubungan(u.senaraiOKS.get(i).getHubungan());
                    ov.setTarikhLahirOKS(u.senaraiOKS.get(i).getTarikhLahirOKS());
                    ov.setPekerjaanOKS(u.senaraiOKS.get(i).getPekerjaanOKS());
                    ov.setKodBangsaOKS(u.senaraiOKS.get(i).getKodBangsaOKS());

                }
                for (int i = 0; i < u.senaraiLokasiKejadian.size(); i++) {
                    LokasiKejadianValue lokasiKejadian = new LokasiKejadianValue();
                    lokasiKejadian.setBandarPekanMukim(u.senaraiLokasiKejadian.get(i).getBandarPekanMukim());
                    lokasiKejadian.setJenisTanah(u.senaraiLokasiKejadian.get(i).getJenisTanah());
                    lokasiKejadian.setLokasi(u.senaraiLokasiKejadian.get(i).getLokasi());
                    lokasiKejadian.setNoLot(u.senaraiLokasiKejadian.get(i).getNoLot());
                }

                senaraiLokasiKejadian = u.senaraiLokasiKejadian;
                senaraiOKS = u.senaraiOKS;
                permohonan = u.permohonan;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();
                    caraPermohonan = permohonan.getCaraPermohonan();
                    sebab = permohonan.getSebab();
                    kodUrusan = permohonan.getKodUrusan();
                    penyerahJenisPengenalan = permohonan.getPenyerahJenisPengenalan();
                    if (permohonan.getPenyerahJenisPengenalan() != null) {
                        if (permohonan.getPenyerahJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                            penyerahNoPengenalanBaru = permohonan.getPenyerahNoPengenalan();
                        } else {
                            penyerahNoPengenalanLain = permohonan.getPenyerahNoPengenalan();
                        }
                    } else {
                        penyerahNoPengenalanLain = permohonan.getPenyerahNoPengenalan();
                    }

                    penyerahNama = permohonan.getPenyerahNama();
                    penyerahAlamat1 = permohonan.getPenyerahAlamat1();
                    penyerahAlamat2 = permohonan.getPenyerahAlamat2();
                    penyerahAlamat3 = permohonan.getPenyerahAlamat3();
                    penyerahAlamat4 = permohonan.getPenyerahAlamat4();
                    penyerahPoskod = permohonan.getPenyerahPoskod();
                    penyerahNegeri = permohonan.getPenyerahNegeri();
                    penyerahEmail = permohonan.getPenyerahEmail();
                    penyerahNoTelefon1 = permohonan.getPenyerahNoTelefon1();
                    folderDokumen = permohonan.getFolderDokumen();
//                if (permohonan.getFolderDokumen() == null) log.debug("tak ado");
//                senaraiKandungan = permohonan.getFolderDokumen().getSenaraiKandungan();
                }
                aduanLokasi = u.aduanLokasi;
                if (aduanLokasi != null) {
                    bandarPekanMukim = aduanLokasi.getBandarPekanMukim();
                    pemilikan = aduanLokasi.getPemilikan();
                    noLot = aduanLokasi.getNoLot();
                    kodLot = aduanLokasi.getKodLot();
                    lokasi = aduanLokasi.getLokasi();
                }

            } else {
                log.debug("no data in session");
            }
        } catch (Exception ex) {
            log.error(ex);
            ctx.removeWorkdata();
        }

    }

    public Resolution aduanLokasiSave() {
        log.debug("save aduan lokasi");
        LokasiKejadianValue lokasiKejadian = new LokasiKejadianValue();

        if (bandar != null) {
            KodBandarPekanMukim mukim = new KodBandarPekanMukim();
            mukim = kodbandarPekanMukimDAO.findById(Integer.parseInt(bandar));
            lokasiKejadian.setBandarPekanMukim(mukim);
        } else {
            lokasiKejadian.setBandarPekanMukim(null);
        }


        if (jenisTanah != null) {
            KodPemilikan p = new KodPemilikan();
            p = kodPemilikanDAO.findById(jenisTanah.charAt(0));
            lokasiKejadian.setJenisTanah(p);
        } else {
            lokasiKejadian.setJenisTanah(null);
        }
        lokasiKejadian.setLokasi(lokasi);
        lokasiKejadian.setNoLot(noLot);

        senaraiLokasiKejadian.add(lokasiKejadian);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);

        addSimpleMessage("Maklumat telah berjaya disimpan.");


        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

    public Resolution editAduanLokasi() {
        log.debug("editAduanLokasi");
        id = getContext().getRequest().getParameter("id");
        int idInt = Integer.parseInt(id);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            bandar = Integer.toString(u.senaraiLokasiKejadian.get(idInt).getBandarPekanMukim().getKod());
            jenisTanah = Character.toString(u.senaraiLokasiKejadian.get(idInt).getJenisTanah().getKod());
            noLot = u.senaraiLokasiKejadian.get(idInt).getNoLot();
            lokasi = u.senaraiLokasiKejadian.get(idInt).getLokasi();
        } else {
            log.debug("no data in session");
        }
        return new JSP("penguatkuasaan/aduanLokasiPopupEdit.jsp").addParameter("popup", "true");
    }

    public Resolution editAduanLokasiPopup() {
        log.debug("editAduanLokasiPopup");
        id = getContext().getRequest().getParameter("id");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            LokasiKejadianValue lokasiKejadian = new LokasiKejadianValue();
            if (bandar != null) {
                KodBandarPekanMukim mukim = new KodBandarPekanMukim();
                mukim = kodbandarPekanMukimDAO.findById(Integer.parseInt(bandar));
                lokasiKejadian.setBandarPekanMukim(mukim);
            } else {
                lokasiKejadian.setBandarPekanMukim(null);
            }
            if (jenisTanah != null) {
                KodPemilikan p = new KodPemilikan();
                p = kodPemilikanDAO.findById(jenisTanah.charAt(0));
                lokasiKejadian.setJenisTanah(p);
            } else {
                lokasiKejadian.setJenisTanah(null);
            }
            lokasiKejadian.setLokasi(lokasi);
            lokasiKejadian.setNoLot(noLot);
            u.senaraiLokasiKejadian.set(Integer.parseInt(id), lokasiKejadian);
        } else {
            log.debug("no data in session");
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");


        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

    public Resolution removeAduanLokasi() {
        log.debug("removeAduanLokasi");
        id = getContext().getRequest().getParameter("id");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            ArrayList<LokasiKejadianValue> lokasi = u.senaraiLokasiKejadian;
            for (Iterator<LokasiKejadianValue> it = lokasi.iterator(); it.hasNext();) {
                LokasiKejadianValue lokasiAduan = it.next();
                if (Integer.parseInt(id) == lokasi.indexOf(lokasiAduan)) {
                    it.remove();
                    break;
                }
            }
        } else {
            log.debug("no data in session");
        }

        addSimpleMessage("Maklumat telah berjaya dihapus.");


        return new RedirectResolution(KemasukanAduanActionBean.class, "locate");
    }

    public Resolution genReport() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        log.info("genReport :: start");
        log.info("generate report start.");
//        String msg = "Laporan telah dijana. Sila Klik Butang Papar dan Cetak.";
        String gen = "";
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            log.info("NS");
            gen = "ENFAPA_NS.rdf";
        } else {
            log.info("MLK");
            gen = "ENFAPA_MLK.rdf";
        }
        String code = "RAP";
        log.info("idPermohonan : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        try {
            log.info("genReportFromXML");
            Dokumen doc = lelongService.reportGen2(permohonan, gen, code, pengguna);
            idDokumen = doc.getIdDokumen();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        log.info("genReport :: finish");
        log.info("idDokumen : " + idDokumen);
        listMohon = new ArrayList<Permohonan>();
        listMohon.add(permohonan);
        log.info("listMohon : " + listMohon.size());
        addSimpleMessage("Aduan yang diterima telah dijana. Sila Klik Butang Papar Aduan untuk  semak dan cetak aduan yang telah dijana.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/tamat.jsp");
    }

//
//    public void changeSeksyen() {
//        log.debug("changeSeksyen");
//        String value = getContext().getRequest().getParameter("value");
//        if (value.equalsIgnoreCase("49")) {
//            enabledSeksyen49 = true;
//        } else {
//            enabledSeksyen49 = false;
//        }
//
//    }
//    public Resolution changeSeksyen() {
//        log.debug("changeSeksyen");
//        String result = "";
//        String value = getContext().getRequest().getParameter("value");
//         if (value.equalsIgnoreCase("49")) {
//            enabledSeksyen49 = true;
//        } else {
//            enabledSeksyen49 = false;
//        }
//
//        result = enabledSeksyen49.toString();
//
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        saveToSession(ctx);
//        return new StreamingResolution("text/plain", result);
//    }
    public Resolution muatNaikForm1() {
        log.info("--start muatNaikForm1--");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        String idBarang = getContext().getRequest().getParameter("idBarang");
        String idLaporan = getContext().getRequest().getParameter("idLaporan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idKertas = getContext().getRequest().getParameter("idKertas");
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
        String idOperasi = getContext().getRequest().getParameter("idOperasi");

        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
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
                BarangRampasan barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
                barangRampasan.setImej(d);
                barangRampasan.setInfoAudit(ia);
                barangRampasanDAO.saveOrUpdate(barangRampasan);
            }
            if (dokumenKod.startsWith("I") && !dokumenKod.matches("IB")) {
                LaporanTanah laporanTanah;
                if (idLaporan.matches("")) {
                    laporanTanah = new LaporanTanah();
                    laporanTanah.setInfoAudit(ia);
                    laporanTanah.setPermohonan(permohonan);
                    laporanTanahDAO.saveOrUpdate(laporanTanah);

                } else {
                    laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporan));
                }
                ImejLaporan imejLaporan = new ImejLaporan();
                imejLaporan.setCawangan(peng.getKodCawangan());
                imejLaporan.setDokumen(d);
                if (dokumenKod.length() > 2) {
                    imejLaporan.setPandanganImej(dokumenKod.charAt(2));
                }
                if (StringUtils.isNotBlank(idHakmilik)) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    if (hakmilik != null) {
                        imejLaporan.setHakmilik(hakmilik);
                    }
                }
                imejLaporan.setLaporanTanah(laporanTanah);
                imejLaporan.setInfoAudit(ia);
                imejLaporanDAO.save(imejLaporan);
            }
            if (dokumenKod.matches("KS")) {
                PermohonanKertas permohonanKertas;
                if (idKertas.matches("")) {
                    permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setTajuk("Draf Kertas Siasatan");
                    permohonanKertas.setInfoAudit(ia);
                    permohonanKertas.setCawangan(peng.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                } else {
                    permohonanKertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
                }
                permohonanKertas.setDokumen(d);
                permohonanKertasDAO.saveOrUpdate(permohonanKertas);
            }
            if (dokumenKod.matches("LP")) {
                PermohonanRujukanLuar permohonanRujukanLuar;
                if (idRujukan.matches("")) {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujukanLuar.setInfoAudit(ia);
                    permohonanRujukanLuar.setPermohonan(permohonan);
                    KodRujukan kodRujukan = new KodRujukan();
                    kodRujukan.setKod("NF");
                    permohonanRujukanLuar.setKodRujukan(kodRujukan);
                    KodAgensi kodAgensi = new KodAgensi();
                    kodAgensi.setKod("0302");
                    permohonanRujukanLuar.setAgensi(kodAgensi);
                    permohonanRujukanLuar.setDokumen(d);
                    permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);

                }

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
                PermohonanRujukanLuar permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                permohonanRujukanLuar.setDokumen(d);
                permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            }
            if (dokumenKod.matches("LO")) {
                if (idOperasi.matches("")) {
                    OperasiPenguatkuasaan operasiPenguatkuasaan = new OperasiPenguatkuasaan();
                    operasiPenguatkuasaan.setCawangan(peng.getKodCawangan());
                    operasiPenguatkuasaan.setPermohonan(permohonan);
                    operasiPenguatkuasaan.setInfoAudit(ia);
                    operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
                    operasiPenguatkuasaanDAO.save(operasiPenguatkuasaan);
                }
            }
            if (d != null) {
                getContext().getRequest().setAttribute("dokumenId", d.getIdDokumen());
                tx.commit();
            } else {
                tx.rollback();
            }
            //return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }
        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }

        return new JSP("penguatkuasaan/muat_naik1.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        log.info("simpanMuatNaik::start");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderId = getContext().getRequest().getParameter("folderId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        String catatan = getContext().getRequest().getParameter("catatan");

//        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//        permohonan = permohonanDAO.findById(idPermohonan);

        if (fileToBeUpload != null) {
            try {
                DMSUtil dmsUtil = new DMSUtil();
                String fileName = fileToBeUpload.getFileName();
                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                System.out.println("no null::" + fileToBeUpload.getContentType());
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
                FileUtil fileUtil = new FileUtil(dokumenPath);
                fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
                String fizikalPath1 = folderId + File.separator + dokumenId;
                System.out.println("fizikalPath : " + fizikalPath);
                System.out.println("fizikalPath1 : " + fizikalPath1);
                updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("penguatkuasaan/muat_naik1.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        log.info("simpanMuatNaik::start :processUploadDoc");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderId = getContext().getRequest().getParameter("folderId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        String catatan = getContext().getRequest().getParameter("catatan");

//        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//        permohonan = permohonanDAO.findById(idPermohonan);


        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                log.debug("content type = " + contentType);

                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {

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
                    return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("penguatkuasaan/muat_naik1.jsp").addParameter("popup", "true");
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

    public Resolution reload1() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        return new RedirectResolution(KemasukanAduanActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return senaraiKodBandarPekanMukim;
    }

    public void setSenaraiKodBandarPekanMukim(List<KodBandarPekanMukim> senaraiKodBandarPekanMukim) {
        this.senaraiKodBandarPekanMukim = senaraiKodBandarPekanMukim;
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return senaraiKodCaraPermohonan;
    }

    public void setSenaraiKodCaraPermohonan(List<KodCaraPermohonan> senaraiKodCaraPermohonan) {
        this.senaraiKodCaraPermohonan = senaraiKodCaraPermohonan;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
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

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public String getNegeriO() {
        return negeriO;
    }

    public void setNegeriO(String negeriO) {
        this.negeriO = negeriO;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public void setSenaraiPermohonan(ArrayList<UrusanValue> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public ArrayList<OksValue> getSenaraiOKS() {
        return senaraiOKS;
    }

    public void setSenaraiOKS(ArrayList<OksValue> senaraiOKS) {
        this.senaraiOKS = senaraiOKS;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(
            KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String nama) {
        this.penyerahNama = nama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public void setPenyerahNoTelefon1(String penyerahNoTelefon1) {
        this.penyerahNoTelefon1 = penyerahNoTelefon1;
    }

    public String getPenyerahNoTelefon1() {
        return penyerahNoTelefon1;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String email) {
        this.penyerahEmail = email;
    }

    public String getIdAduanLokasi() {
        return idAduanLokasi;
    }

    public void setIdAduanLokasi(String idAduanLokasi) {
        this.idAduanLokasi = idAduanLokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<AduanLokasi> getSenaraiAduanLokasi() {
        return senaraiAduanLokasi;
    }

    public void setSenaraiAduanLokasi(List<AduanLokasi> senaraiAduanLokasi) {
        this.senaraiAduanLokasi = senaraiAduanLokasi;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getBandar() {
        return bandar;
    }

    public void setBandar(String bandar) {
        this.bandar = bandar;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public ArrayList<LokasiKejadianValue> getSenaraiLokasiKejadian() {
        return senaraiLokasiKejadian;
    }

    public void setSenaraiLokasiKejadian(ArrayList<LokasiKejadianValue> senaraiLokasiKejadian) {
        this.senaraiLokasiKejadian = senaraiLokasiKejadian;
    }

    public Boolean getEnabledSeksyen49() {
        return enabledSeksyen49;
    }

    public void setEnabledSeksyen49(Boolean enabledSeksyen49) {
        this.enabledSeksyen49 = enabledSeksyen49;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getSenaraiHitam() {
        return senaraiHitam;
    }

    public void setSenaraiHitam(String senaraiHitam) {
        this.senaraiHitam = senaraiHitam;
    }

    public String getTarikhSenaraiHitam() {
        return tarikhSenaraiHitam;
    }

    public void setTarikhSenaraiHitam(String tarikhSenaraiHitam) {
        this.tarikhSenaraiHitam = tarikhSenaraiHitam;
    }

    public String getJumlahSenaraiLokasi() {
        return jumlahSenaraiLokasi;
    }

    public String getTarikhDiBebas() {
        return tarikhDiBebas;
    }

    public void setTarikhDiBebas(String tarikhDiBebas) {
        this.tarikhDiBebas = tarikhDiBebas;
    }

    public String getTarikhDitahan() {
        return tarikhDitahan;
    }

    public void setTarikhDitahan(String tarikhDitahan) {
        this.tarikhDitahan = tarikhDitahan;
    }

    public String getTempatDireman() {
        return tempatDireman;
    }

    public void setTempatDireman(String tempatDireman) {
        this.tempatDireman = tempatDireman;
    }

    public String getTempatOksDitahan() {
        return tempatOksDitahan;
    }

    public void setTempatOksDitahan(String tempatOksDitahan) {
        this.tempatOksDitahan = tempatOksDitahan;
    }

    public void setJumlahSenaraiLokasi(String jumlahSenaraiLokasi) {
        this.jumlahSenaraiLokasi = jumlahSenaraiLokasi;
    }

    public String getDiBebas() {
        return diBebas;
    }

    public void setDiBebas(String diBebas) {
        this.diBebas = diBebas;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getAlamat1Majikan() {
        return alamat1Majikan;
    }

    public void setAlamat1Majikan(String alamat1Majikan) {
        this.alamat1Majikan = alamat1Majikan;
    }

    public String getAlamat2Majikan() {
        return alamat2Majikan;
    }

    public void setAlamat2Majikan(String alamat2Majikan) {
        this.alamat2Majikan = alamat2Majikan;
    }

    public String getAlamat3Majikan() {
        return alamat3Majikan;
    }

    public void setAlamat3Majikan(String alamat3Majikan) {
        this.alamat3Majikan = alamat3Majikan;
    }

    public String getAlamat4Majikan() {
        return alamat4Majikan;
    }

    public void setAlamat4Majikan(String alamat4Majikan) {
        this.alamat4Majikan = alamat4Majikan;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public String getNamaMajikan() {
        return namaMajikan;
    }

    public void setNamaMajikan(String namaMajikan) {
        this.namaMajikan = namaMajikan;
    }

    public String getNoFaksMajikan() {
        return noFaksMajikan;
    }

    public void setNoFaksMajikan(String noFaksMajikan) {
        this.noFaksMajikan = noFaksMajikan;
    }

    public String getNoTelMajikan() {
        return noTelMajikan;
    }

    public void setNoTelMajikan(String noTelMajikan) {
        this.noTelMajikan = noTelMajikan;
    }

    public String getPoskodMajikan() {
        return poskodMajikan;
    }

    public void setPoskodMajikan(String poskodMajikan) {
        this.poskodMajikan = poskodMajikan;
    }

    public KodNegeri getKodNegeriMajikan() {
        return kodNegeriMajikan;
    }

    public void setKodNegeriMajikan(KodNegeri kodNegeriMajikan) {
        this.kodNegeriMajikan = kodNegeriMajikan;
    }

    public KodWarnaKP getKodWarnaKP() {
        return kodWarnaKP;
    }

    public void setKodWarnaKP(KodWarnaKP kodWarnaKP) {
        this.kodWarnaKP = kodWarnaKP;
    }

    public WarisOrangKenaSyak getWarisOrangKenaSyak() {
        return warisOrangKenaSyak;
    }

    public void setWarisOrangKenaSyak(WarisOrangKenaSyak warisOrangKenaSyak) {
        this.warisOrangKenaSyak = warisOrangKenaSyak;
    }

    public String getAlamat1Waris() {
        return alamat1Waris;
    }

    public void setAlamat1Waris(String alamat1Waris) {
        this.alamat1Waris = alamat1Waris;
    }

    public String getAlamat2Waris() {
        return alamat2Waris;
    }

    public void setAlamat2Waris(String alamat2Waris) {
        this.alamat2Waris = alamat2Waris;
    }

    public String getAlamat3Waris() {
        return alamat3Waris;
    }

    public void setAlamat3Waris(String alamat3Waris) {
        this.alamat3Waris = alamat3Waris;
    }

    public String getAlamat4Waris() {
        return alamat4Waris;
    }

    public void setAlamat4Waris(String alamat4Waris) {
        this.alamat4Waris = alamat4Waris;
    }

    public String getNamaWaris() {
        return namaWaris;
    }

    public void setNamaWaris(String namaWaris) {
        this.namaWaris = namaWaris;
    }

    public String getPoskodWaris() {
        return poskodWaris;
    }

    public void setPoskodWaris(String poskodWaris) {
        this.poskodWaris = poskodWaris;
    }

    public KodNegeri getKodNegeriWaris() {
        return kodNegeriWaris;
    }

    public void setKodNegeriWaris(KodNegeri kodNegeriWaris) {
        this.kodNegeriWaris = kodNegeriWaris;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getNoPengenalanWaris() {
        return noPengenalanWaris;
    }

    public void setNoPengenalanWaris(String noPengenalanWaris) {
        this.noPengenalanWaris = noPengenalanWaris;
    }

    public KodJenisPengenalan getJenisPengenalanWaris() {
        return jenisPengenalanWaris;
    }

    public void setJenisPengenalanWaris(KodJenisPengenalan jenisPengenalanWaris) {
        this.jenisPengenalanWaris = jenisPengenalanWaris;
    }

    public KodJantina getKodJantinaWaris() {
        return kodJantinaWaris;
    }

    public void setKodJantinaWaris(KodJantina kodJantinaWaris) {
        this.kodJantinaWaris = kodJantinaWaris;
    }

    public KodWarganegara getKodWarganegaraWaris() {
        return kodWarganegaraWaris;
    }

    public void setKodWarganegaraWaris(KodWarganegara kodWarganegaraWaris) {
        this.kodWarganegaraWaris = kodWarganegaraWaris;
    }

    public KodBangsa getKodBangsaWaris() {
        return kodBangsaWaris;
    }

    public void setKodBangsaWaris(KodBangsa kodBangsaWaris) {
        this.kodBangsaWaris = kodBangsaWaris;
    }

    public String getNoTelBimbitWaris() {
        return noTelBimbitWaris;
    }

    public void setNoTelBimbitWaris(String noTelBimbitWaris) {
        this.noTelBimbitWaris = noTelBimbitWaris;
    }

    public String getNoTelWaris() {
        return noTelWaris;
    }

    public void setNoTelWaris(String noTelWaris) {
        this.noTelWaris = noTelWaris;
    }

    public String getKwg() {
        return kwg;
    }

    public void setKwg(String kwg) {
        this.kwg = kwg;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<KodBangsa> getSenaraiBangsa() {
        return senaraiBangsa;
    }

    public void setSenaraiBangsa(List<KodBangsa> senaraiBangsa) {
        this.senaraiBangsa = senaraiBangsa;
    }

    public String getKodBangsaOKS() {
        return kodBangsaOKS;
    }

    public void setKodBangsaOKS(String kodBangsaOKS) {
        this.kodBangsaOKS = kodBangsaOKS;
    }

    public String getPekerjaanOKS() {
        return pekerjaanOKS;
    }

    public void setPekerjaanOKS(String pekerjaanOKS) {
        this.pekerjaanOKS = pekerjaanOKS;
    }

    public Date getTarikhLahirOKS() {
        return tarikhLahirOKS;
    }

    public void setTarikhLahirOKS(Date tarikhLahirOKS) {
        this.tarikhLahirOKS = tarikhLahirOKS;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBeanFlag() {
        return beanFlag;
    }

    public void setBeanFlag(String beanFlag) {
        this.beanFlag = beanFlag;
    }

    public String getPenyerahNoPengenalanBaru() {
        return penyerahNoPengenalanBaru;
    }

    public void setPenyerahNoPengenalanBaru(String penyerahNoPengenalanBaru) {
        this.penyerahNoPengenalanBaru = penyerahNoPengenalanBaru;
    }

    public String getPenyerahNoPengenalanLain() {
        return penyerahNoPengenalanLain;
    }

    public void setPenyerahNoPengenalanLain(String penyerahNoPengenalanLain) {
        this.penyerahNoPengenalanLain = penyerahNoPengenalanLain;
    }

    public String getNoPengenalanBaru() {
        return noPengenalanBaru;
    }

    public void setNoPengenalanBaru(String noPengenalanBaru) {
        this.noPengenalanBaru = noPengenalanBaru;
    }

    public String getNoPengenalanLain() {
        return noPengenalanLain;
    }

    public void setNoPengenalanLain(String noPengenalanLain) {
        this.noPengenalanLain = noPengenalanLain;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getBulanReadonly() {
        return bulanReadonly;
    }

    public void setBulanReadonly(String bulanReadonly) {
        this.bulanReadonly = bulanReadonly;
    }

    public String getHariReadonly() {
        return hariReadonly;
    }

    public void setHariReadonly(String hariReadonly) {
        this.hariReadonly = hariReadonly;
    }

    public String getTahunReadonly() {
        return tahunReadonly;
    }

    public void setTahunReadonly(String tahunReadonly) {
        this.tahunReadonly = tahunReadonly;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
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

    public String getKodUrusanTemp() {
        return kodUrusanTemp;
    }

    public void setKodUrusanTemp(String kodUrusanTemp) {
        this.kodUrusanTemp = kodUrusanTemp;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public GeneratorIdPermohonan getIdPermohonanGenerator() {
        return idPermohonanGenerator;
    }

    public void setIdPermohonanGenerator(GeneratorIdPermohonan idPermohonanGenerator) {
        this.idPermohonanGenerator = idPermohonanGenerator;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getDokumenKod() {
        return dokumenKod;
    }

    public void setDokumenKod(String dokumenKod) {
        this.dokumenKod = dokumenKod;
    }
}
