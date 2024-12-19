/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanCerunDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanNota;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.KodStrukturTanah;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanLaporanCerun;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKecerunanTanah;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Dokumen;
import etanah.model.KodBandarPekanMukim;
import etanah.service.PengambilanService;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.common.TanahRizabService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanNotaDAO;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodKlasifikasi;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.PermohonanPlotPelan;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.RegService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.WorkflowException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author khairul.hazwan
 */
@UrlBinding("/utiliti/laporanTanah")
public class UtilitiLtActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiLtActionBean.class);
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    RegService regService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    KodJenisPendudukanDAO jenisPendudukanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanLaporanCerunDAO permohonanLaporanCerunDAO;
    @Inject
    PengambilanService service;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    etanah.Configuration conf;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforceService enforceService;
    private ArrayList imageList[] = new ArrayList[5];
    private List<HakmilikPermohonan> senaraiHakmilik;
    private List<FasaPermohonan> listFasa;
    private List<FasaPermohonan> listFasa2;
    private List<PermohonanNota> listNota;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<TanahRizabPermohonan> tanahRizabList;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private List<ImejLaporan> timurLautImejLaporanList;
    private List<ImejLaporan> baratLautImejLaporanList;
    private List<ImejLaporan> tenggaraImejLaporanList;
    private List<ImejLaporan> baratDayaImejLaporanList;
    private List<KodKecerunanTanah> senaraiKodKecerunanTanah;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanTanamanList;
    private List<PermohonanLaporanCerun> listlaporCerun;
    private List<PermohonanLaporanCerun> findListlaporCerun;
    private List<PermohonanRujukanLuar> listRujukanLuar;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private TanahRizabPermohonan tanahrizabpermohonan;
    private TanahRizabPermohonan trizabpermohonan;
    private PermohonanLaporanCerun laporanCerun;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private LaporanTanah checkingLaporanTanah;
    private FasaPermohonan checkingFasaPermohonan;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonan2;
    private PermohonanNota notaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanLaporanCerun permohonanLaporanCerun;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodKecerunanTanah kecerunanTanah;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private KodKategoriTanah KKtgT;
    private KodStrukturTanah KST;
    private KodRizab rizab;
    private Pengguna pengguna;
    private InfoAudit infoAudit;
    private Dokumen dokumen;
    private ImejLaporan imejLaporan;
    private String date;
    private String stageId;
    private String stageIdfound;
    private String noLot;
    private String noLitho;
    private String noWarta;
    private String lokasi;
    private String tarikhWarta;
    private String idtanahrizabPermohonan;
    private String ulasan;
    private String nota;
    private String noRujukan;
    private String idFasa;
    private char pandanganImej;
    private String idHakmilik;
    private String idPermohonan;
    private String idDokumen;
    private String idLaporBangunan;
    private String idLaporCerun;
    private String kecerunanTanahString;
    private String strukturTanahString;
    private String kategoriTanahBaruString;
    private String mohonLaporUlasan;
    private String idtanahRizabPermohonan;
    private String TB;
    private String kodNegeri;
    private BigDecimal luas;
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private static final BigDecimal luas8EkarInH = new BigDecimal(3.237488);
    private static final BigDecimal luas8EkarInMp = new BigDecimal(32374.88);
    private BPelService serviceBPel;
    private boolean textSyor;
    private String noWartaKonon;
    private Boolean ltshow = false;
    private Boolean idMohonShow = false;
    private String notaUlasan;
    private String idFolder = "";
    private String reportName;
    private String idLaporanTanah;
    private PermohonanNota hidePermohonanNota;
    private List<HakmilikPermohonan> senaraiTanahMilik = new ArrayList<HakmilikPermohonan>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String disabled;
    String disabled2;
    etanahActionBeanContext ctx;
    String format1 = "image/jpeg";
    String format2 = "image/pjpeg";
    String _MSG_SUCCES_SAVE = "Maklumat telah berjaya disimpan.";
    String _MSG_SUCCES_UPDATE = "Maklumat telah berjaya dikemaskini.";
    String _PAGE = "pembangunan/utiliti/utilitiLt.jsp";
    String _FWRD_PAGE = "/WEB-INF/jsp/" + _PAGE;
    String _REKOD_ULASAN_POP = "pembangunan/utiliti/rekod_ulasan_popup.jsp";
    String _PAGE_TRIZAB = "/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp";
    String _PAGE_IN_TRIZAB = "pengambilan/kemasukan_tanahrizab.jsp";
    String _PAGE_EDIT_TRIZAB = "pengambilan/kemaskini_trizab.jsp";
    String _PAGE_EDIT_PNGMBLN = "pengambilan/maklumat_pengambilan_pemohon.jsp";
    String _PAGE_LT_POPUP = "pembangunan/utiliti/laporan_tanah_popup.jsp";
    //added for TSPSS & SBMS
    private int bilplotHakmiliktbl = 0;
    private int bilplotHakmiliktemp = 0;
    private List<PermohonanPlotPelan> listplotpelan;
    private String idPlot;
    private String forEdit;
    private String premiumTambahan;
    private String dendaPremium;
    private String sewaTahunan;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private String kodSekatan;
    private String kodSyaratNyata;
    private String sekatan;
    private String kodKatTanah;
    private String kodSyaratNyataBaru;
    private String kodSekatanKepentinganBaru;
    private List<PermohonanPlotPelan> listHakmilik;
    private PermohonanPlotPelan mpp;
    private BigDecimal jumluasHakmilik = new BigDecimal(0.00);
    private String syaratNyata;
    private String sumbanganSaliran;
    private String idPermohonan1;
    private String kodKatgTanah;

    @DefaultHandler
    public Resolution showForm() {
        idMohonShow = true;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(UtilitiLtActionBean.class, "locate");
    }

    public Resolution reset() {
        idMohonShow = true;
        laporanTanah = new LaporanTanah();
        permohonan = new Permohonan();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
    }

    public Resolution checkPermohonan() {

        LOG.info("-------------Check Permohonan Start---------");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        if (permohonan == null) {
            addSimpleMessage("Id Permohonan" + idPermohonan + " ini tidak wujud'.");

            ltshow = false;
            idMohonShow = true;
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
        } else {

            //addCodes to recover SO Faulty
            //START
            checkingLaporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
            checkingFasaPermohonan = pembangunanService.findFasaPermohonanByIdAliran(idPermohonan, "laporantanah");
            permohonan = permohonanDAO.findById(idPermohonan);

            if (checkingFasaPermohonan != null) {
                if (checkingLaporanTanah == null) {
                    LaporanTanah laporanTanahSOFaulty = new LaporanTanah();
                    laporanTanahSOFaulty.setPermohonan(permohonan);
                    laporanTanahSOFaulty.setBilanganBangunan(0);
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    laporanTanahSOFaulty.setInfoAudit(infoAudit);
                    pembangunanService.simpanLaporanTanah(laporanTanahSOFaulty);
                } else {
                    //DO NOTHING
                }
            } else {
                addSimpleMessage("Id Permohonan" + idPermohonan + " ini tidak melibatkan proses Laporan Tanah'.");
                ltshow = false;
                idMohonShow = true;
                return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
            }
            //END

            if (idPermohonan == null) {
                ltshow = false;
                idMohonShow = true;
            } else {
                ltshow = true;
                idMohonShow = false;
                LOG.info("--------ltShow---------");
            }

            LOG.info("--------TEST---------");
            kodNegeri = conf.getKodNegeri();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            imejLaporanList = new ArrayList<ImejLaporan>();
            utaraImejLaporanList = new ArrayList<ImejLaporan>();
            selatanImejLaporanList = new ArrayList<ImejLaporan>();
            timurImejLaporanList = new ArrayList<ImejLaporan>();
            baratImejLaporanList = new ArrayList<ImejLaporan>();
            permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
            permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
            findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();

            if (idPermohonan != null) {
                addSimpleMessage("Maklumat Laporan Tanah Untuk " + idPermohonan + " Telah Dijumpai.");
                hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                tanahRizabList = permohonan.getSenaraiTanahRizab();
                permohonanPengambilan = service.findByidPermohonan(idPermohonan);
                permohonanLaporanBangunanList = pembangunanService.getLaporBngnByIdLaporan(idPermohonan);
                permohonanLaporanTanamanList = pembangunanService.getLaporTnmnByIdLaporan(idPermohonan);
                laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);

                hakmilikPermohonan = hakmilikPermohonanList.get(0);
                System.out.println("###### Laporan tanah" + laporanTanah);
                System.out.println("######### rehydrate hakmilikpermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};

                ekarCondition(hakmilik);

                if (laporanTanah != null) {

                    imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    utaraImejLaporanList = laporanTanahService.getBngnLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    timurLautImejLaporanList = laporanTanahService.getTimurLautLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    tenggaraImejLaporanList = laporanTanahService.getTenggaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    baratDayaImejLaporanList = laporanTanahService.getBaratDayaLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    baratLautImejLaporanList = laporanTanahService.getBaratLautLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                    listNota = pembangunanService.findNotaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                    permohonanLaporanUlasan = pembangunanService.findPemohonLaporanUlasanByIdPermohonan(idPermohonan);

                    if (laporanTanah.getStrukturTanah() != null) {
                        strukturTanahString = laporanTanah.getStrukturTanah().getKod();
                    } else {
                        strukturTanahString = "";
                    }
                    if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                        kategoriTanahBaruString = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                    } else {
                        kategoriTanahBaruString = "0";
                    }
                } else {
                    laporanTanah = new LaporanTanah();
                }

                hidePermohonanNota = pembangunanService.findNotaByIdMohon(idPermohonan, "laporantanah");
                listFasa = pembangunanService.findFasaPermohonanByIdPermohonan2(permohonan.getIdPermohonan());
                listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());

                LOG.info("###### listFasa : " + listFasa.size());
                if (!(listFasa.isEmpty())) {
                    for (int i = 0; i < listFasa.size(); i++) {
                        FasaPermohonan fp = new FasaPermohonan();
                        fp = listFasa.get(i);
                        fasaPermohonan = listFasa.get(i);
                    }
                }
                if (!(listFasa.isEmpty())) {
                    for (int i = 0; i < listFasa.size(); i++) {
                        FasaPermohonan fp = new FasaPermohonan();
                        fp = listFasa.get(i);
                    }
                }
                senaraiKodKecerunanTanah = new ArrayList<KodKecerunanTanah>();
                senaraiKodKecerunanTanah = pembangunanService.findKodKecerunanFilterByidPermohonan(permohonan.getIdPermohonan());
                findListlaporCerun = pembangunanService.findLaporCerunListByIdPermohonan(permohonan.getIdPermohonan());    //BARU PNY
                listRujukanLuar = permohonan.getSenaraiRujukanLuar();
                System.out.println("listRujukanLuar : " + listRujukanLuar.size());
                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        permohonanRujukanLuar = new PermohonanRujukanLuar();
                        permohonanRujukanLuar = listRujukanLuar.get(i);
                        if (permohonanRujukanLuar.getKodRujukan() != null) {
                            System.out.println("Kod : " + permohonanRujukanLuar.getKodRujukan().getKod());
                            if (permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("WT") || permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("NF")) {
                                permohonanRujukanLuar = listRujukanLuar.get(i);

                                noWartaKonon = permohonanRujukanLuar.getNoRujukan();
                            }
                        }
                    }
                }
                System.out.println("Ruj Luar : " + noWartaKonon);
                tanahRizabList = permohonan.getSenaraiTanahRizab();
                for (int i = 0; i < tanahRizabList.size(); i++) {
                    tanahrizabpermohonan = tanahRizabList.get(i);
                    System.out.println("Laporan Tanah ---->" + tanahrizabpermohonan.getNoWarta());
                }

                //ADDED 4 SBMS & TSPSS CONDITION
                //START
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")) {
                    listHakmilik = new ArrayList<PermohonanPlotPelan>();
                    bilplotHakmiliktemp = 0;
                    bilplotHakmiliktbl = 0;

                    LOG.info("plot pelan list.");
                    listplotpelan = new ArrayList<PermohonanPlotPelan>();
                    listplotpelan = pembangunanService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);

                    LOG.info("---------listplotpelan---------list:" + listplotpelan);
                    if (!(listplotpelan.isEmpty())) {
                        for (int a = 0; a < listplotpelan.size(); a++) {
                            mpp = new PermohonanPlotPelan();
                            mpp = listplotpelan.get(a);

                            if (mpp.getNoPt() == null || mpp.getNoPt().equalsIgnoreCase("Y")) {
                                if (mpp.getPemilikan().getKod() == 'H' || mpp.getPemilikan().getKod() == 'K') {
                                    LOG.info("plot pelan hakmilik list.");
                                    bilplotHakmiliktbl = mpp.getBilanganPlot();
                                    BigDecimal luasH = mpp.getLuas();
                                    luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                                    System.out.println("****** luasH ******* = " + luasH);
                                    jumluasHakmilik = jumluasHakmilik.add(luasH);
                                    bilplotHakmiliktemp = bilplotHakmiliktemp + bilplotHakmiliktbl;
                                    System.out.println("plot hakmilik = " + bilplotHakmiliktemp);
                                    listHakmilik.add(mpp);
                                }
                            }
                        }
                    }
                }

                idPlot = getContext().getRequest().getParameter("idPlot");
                if (idPlot != null) {
                    mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));

                    if (mpp.getKeteranganKadarPremium() != null) {
                        premiumTambahan = mpp.getKeteranganKadarPremium();
                    }

                    if (mpp.getKeteranganKadarDaftar() != null) {
                        dendaPremium = mpp.getKeteranganKadarDaftar();
                    }

                    if (mpp.getKeteranganCukaiBaru() != null) {
                        sewaTahunan = mpp.getKeteranganCukaiBaru();
                    }

                    if (mpp.getKodSyaratNyata() != null) {
                        kodSyaratNyataBaru = mpp.getKodSyaratNyata().getSyarat();
                    }
                    if (mpp.getKodSekatanKepentingan() != null) {
                        kodSekatanKepentinganBaru = mpp.getKodSekatanKepentingan().getSekatan();
                    }
                    if (mpp.getKosInfra() != null) {
                        sumbanganSaliran = mpp.getKosInfra();
                    }
                    if (mpp.getKategoriTanah() != null) {
                        kodKatgTanah = mpp.getKategoriTanah().getKod();
                    }
                } else {
                    LOG.debug("---NO DATA---");
                }
                //END
            }
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pengguna.getNama());
            LOG.info("-------------rehydrate Stop---------");
        }
        LOG.info("--------Check Permohonan Stop---------");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
    }

    public Resolution simpanLaporanTanah() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("Pengguna == " + pengguna);

        idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        }
        LOG.info("ID MOHON  == " + idPermohonan);


        LOG.info("###### simpan Laporan Tanah");
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        LOG.info("###### id Pengguna : " + idPermohonan);
        //LaporanTanah laporanTanahcek = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            LOG.info("###### not null");
            infoAudit = laporanTanah.getInfoAudit();
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);

            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }

            //Keadaan Tanah
            String catatanSempadanUtara = getContext().getRequest().getParameter("laporanTanah.catatanSempadanUtara");
            String catatanSempadanSelatan = getContext().getRequest().getParameter("laporanTanah.catatanSempadanSelatan");
            String dilintasTiangElektrik = getContext().getRequest().getParameter("laporanTanah.dilintasTiangElektrik");
            String dilintasTiangTelefon = getContext().getRequest().getParameter("laporanTanah.dilintasTiangTelefon");
            String dilintasLaluanGas = getContext().getRequest().getParameter("laporanTanah.dilintasLaluanGas");
            String dilintasPaip = getContext().getRequest().getParameter("laporanTanah.dilintasPaip");
            String dilintasTaliar = getContext().getRequest().getParameter("laporanTanah.dilintasTaliar");
            String dilintasSungai = getContext().getRequest().getParameter("laporanTanah.dilintasSungai");
            String dilintasParit = getContext().getRequest().getParameter("laporanTanah.dilintasParit");
            String dilintasiLain = getContext().getRequest().getParameter("laporanTanah.dilintasiLain");
            String klasifikasiTanah = getContext().getRequest().getParameter("strukturTanahString");
            KST = kodStrukturTanahDAO.findById(klasifikasiTanah);

            laporanTanah.setCatatanSempadanUtara(catatanSempadanUtara);
            laporanTanah.setCatatanSempadanSelatan(catatanSempadanSelatan);
            if (dilintasTiangElektrik != null) {
                laporanTanah.setDilintasTiangElektrik(dilintasTiangElektrik.charAt(0));
            }
            if (dilintasTiangTelefon != null) {
                laporanTanah.setDilintasTiangTelefon(dilintasTiangTelefon.charAt(0));
            }
            if (dilintasLaluanGas != null) {
                laporanTanah.setDilintasLaluanGas(dilintasLaluanGas.charAt(0));
            }
            if (dilintasPaip != null) {
                laporanTanah.setDilintasPaip(dilintasPaip.charAt(0));
            }
            if (dilintasTaliar != null) {
                laporanTanah.setDilintasTaliar(dilintasTaliar.charAt(0));
            }
            if (dilintasSungai != null) {
                laporanTanah.setDilintasSungai(dilintasSungai.charAt(0));
            }
            if (dilintasParit != null) {
                laporanTanah.setDilintasParit(dilintasParit.charAt(0));
            }
            laporanTanah.setDilintasiLain(dilintasiLain);
            laporanTanah.setStrukturTanah(KST);


            //normal form
            String dokDitandatangan = getContext().getRequest().getParameter("laporanTanah.dokDitandatangan");
            String jenisDokDitandatangan = getContext().getRequest().getParameter("laporanTanah.jenisDokDitandatangan");
            String pengesahanKepentingan = getContext().getRequest().getParameter("laporanTanah.pengesahanKepentingan");
            String nilaiBangunan = getContext().getRequest().getParameter("laporanTanah.nilaiBangunan");
            String kedudukanTanah = getContext().getRequest().getParameter("laporanTanah.kedudukanTanah");
            String namaSempadanJalanRaya = getContext().getRequest().getParameter("laporanTanah.namaSempadanJalanraya");
            String mercuTanda = getContext().getRequest().getParameter("laporanTanah.mercuTanda");
            String adaJalanMasuk = getContext().getRequest().getParameter("laporanTanah.adaJalanMasuk");
            String catatanJalanMasuk = getContext().getRequest().getParameter("laporanTanah.catatanJalanMasuk");
            String syor = getContext().getRequest().getParameter("laporanTanah.syor");
            String usaha = getContext().getRequest().getParameter("laporanTanah.usaha");
            String tanaman = getContext().getRequest().getParameter("laporanTanah.namaSempadanLaut");
            String bangunan = getContext().getRequest().getParameter("laporanTanah.adaBangunan");

            laporanTanah.setDokDitandatangan(dokDitandatangan);
            laporanTanah.setJenisDokDitandatangan(jenisDokDitandatangan);
            laporanTanah.setPengesahanKepentingan(pengesahanKepentingan);
            if (nilaiBangunan != null) {
                BigDecimal NilaiBangunan = new BigDecimal(nilaiBangunan);
                laporanTanah.setNilaiBangunan(NilaiBangunan);
            }
            laporanTanah.setKedudukanTanah(kedudukanTanah);
            laporanTanah.setNamaSempadanJalanraya(namaSempadanJalanRaya);
            laporanTanah.setMercuTanda(mercuTanda);
            if (adaJalanMasuk != null) {
                laporanTanah.setAdaJalanMasuk(adaJalanMasuk.charAt(0));
            }
            laporanTanah.setCatatanJalanMasuk(catatanJalanMasuk);
            laporanTanah.setSyor(syor);
            if (usaha != null) {
                laporanTanah.setUsaha(usaha.charAt(0));
            }
            laporanTanah.setNamaSempadanLaut(tanaman);
            if (bangunan != null) {
                laporanTanah.setAdaBangunan(bangunan.charAt(0));
            }


            //utara
            String sempadanUtaraMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraMilikKerajaan");
            String sempadanUtaraNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraNoLot");
            String sempadanUtaraJenis = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraJenis");
            String sempadanUtaraKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraKegunaan");

            if (sempadanUtaraMilikKerajaan != null) {
                laporanTanah.setSempadanUtaraMilikKerajaan(sempadanUtaraMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanUtaraNoLot(sempadanUtaraNoLot);
            laporanTanah.setSempadanUtaraJenis(sempadanUtaraJenis);
            laporanTanah.setSempadanUtaraKegunaan(sempadanUtaraKegunaan);

            //timurLaut
            String sempadanTimurLautMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanTimurLautMilikKerajaan");
            String sempadanTimurLautNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanTimurLautNoLot");
            String sempadanTimurUtaraJenis = getContext().getRequest().getParameter("laporanTanah.sempadanTimurlautJenis");
            String sempadanTimurUtaraKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanTimurlautKegunaan");

            if (sempadanTimurLautMilikKerajaan != null) {
                laporanTanah.setSempadanTimurLautMilikKerajaan(sempadanTimurLautMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanTimurLautNoLot(sempadanTimurLautNoLot);
            laporanTanah.setSempadanTimurlautJenis(sempadanTimurUtaraJenis);
            laporanTanah.setSempadanTimurlautKegunaan(sempadanTimurUtaraKegunaan);

            //timur
            String sempadanTimurMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanTimurMilikKerajaan");
            String sempadanTimurNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanTimurNoLot");
            String sempadanTimurJenis = getContext().getRequest().getParameter("laporanTanah.sempadanTimurJenis");
            String sempadanTimurKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanTimurKegunaan");

            if (sempadanTimurMilikKerajaan != null) {
                laporanTanah.setSempadanTimurMilikKerajaan(sempadanTimurMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanTimurNoLot(sempadanTimurNoLot);
            laporanTanah.setSempadanTimurJenis(sempadanTimurJenis);
            laporanTanah.setSempadanTimurKegunaan(sempadanTimurKegunaan);

            //Tenggara
            String sempadanTenggaraMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanTenggaraMilikKerajaan");
            String sempadanTenggaraNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanTenggaraNoLot");
            String sempadanTenggaraJenis = getContext().getRequest().getParameter("laporanTanah.sempadanTenggaraJenis");
            String sempadanTenggaraKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanTenggaraKegunaan");

            if (sempadanTenggaraMilikKerajaan != null) {
                laporanTanah.setSempadanTenggaraMilikKerajaan(sempadanTenggaraMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanTenggaraNoLot(sempadanTenggaraNoLot);
            laporanTanah.setSempadanTenggaraJenis(sempadanTenggaraJenis);
            laporanTanah.setSempadanTenggaraKegunaan(sempadanTenggaraKegunaan);

            //selatan
            String sempadanSelatanMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanMilikKerajaan");
            String sempadanSelatanNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanNoLot");
            String sempadanSelatanJenis = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanJenis");
            String sempadanSelatanKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanKegunaan");

            if (sempadanSelatanMilikKerajaan != null) {
                laporanTanah.setSempadanSelatanMilikKerajaan(sempadanSelatanMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanSelatanNoLot(sempadanSelatanNoLot);
            laporanTanah.setSempadanSelatanJenis(sempadanSelatanJenis);
            laporanTanah.setSempadanSelatanKegunaan(sempadanSelatanKegunaan);

            //baratDaya
            String sempadanBaratDayaMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratdayaMilikKerajaan");
            String sempadanBaratDayaNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanBaratdayaNoLot");
            String sempadanBaratDayaJenis = getContext().getRequest().getParameter("laporanTanah.sempadanBaratdayaJenis");
            String sempadanBaratDayaKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratdayaKegunaan");

            if (sempadanBaratDayaMilikKerajaan != null) {
                laporanTanah.setSempadanBaratdayaMilikKerajaan(sempadanBaratDayaMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanBaratdayaNoLot(sempadanBaratDayaNoLot);
            laporanTanah.setSempadanBaratdayaJenis(sempadanBaratDayaJenis);
            laporanTanah.setSempadanBaratdayaKegunaan(sempadanBaratDayaKegunaan);

            //barat
            String sempadanBaratMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratMilikKerajaan");
            String sempadanBaratNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanBaratNoLot");
            String sempadanBaratJenis = getContext().getRequest().getParameter("laporanTanah.sempadanBaratJenis");
            String sempadanBaratKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratKegunaan");

            if (sempadanBaratMilikKerajaan != null) {
                laporanTanah.setSempadanBaratMilikKerajaan(sempadanBaratMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanBaratNoLot(sempadanBaratNoLot);
            laporanTanah.setSempadanBaratJenis(sempadanBaratJenis);
            laporanTanah.setSempadanBaratKegunaan(sempadanBaratKegunaan);

            //baratLaut
            String sempadanBaratLautMilikKerajaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratLautMilikKerajaan");
            String sempadanBaratLautNoLot = getContext().getRequest().getParameter("laporanTanah.sempadanBaratlautNoLot");
            String sempadanBaratLautJenis = getContext().getRequest().getParameter("laporanTanah.sempadanBaratlautJenis");
            String sempadanBaratLautKegunaan = getContext().getRequest().getParameter("laporanTanah.sempadanBaratlautKegunaan");

            if (sempadanBaratLautMilikKerajaan != null) {
                laporanTanah.setSempadanBaratLautMilikKerajaan(sempadanBaratLautMilikKerajaan.charAt(0));
            }
            laporanTanah.setSempadanBaratlautNoLot(sempadanBaratLautNoLot);
            laporanTanah.setSempadanBaratlautJenis(sempadanBaratLautJenis);
            laporanTanah.setSempadanBaratlautKegunaan(sempadanBaratLautKegunaan);

            //save           
            laporanTanahService.simpanLaporanTanah(laporanTanah);

        }

        if (kategoriTanahBaruString != null) {
            KKtgT = kodKategoriTanahDAO.findById(kategoriTanahBaruString);
        } else {
            KKtgT = null;
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        //perakuan
        String premiumTamb = getContext().getRequest().getParameter("hakmilikPermohonan.keteranganKadarPremium");
        String premiumDenda = getContext().getRequest().getParameter("hakmilikPermohonan.dendaPremium");
        String hasil = getContext().getRequest().getParameter("hakmilikPermohonan.keteranganCukaiBaru");
        String saliran = getContext().getRequest().getParameter("hakmilikPermohonan.keteranganInfra");

        hakmilikPermohonan = pembangunanService.findByIdPermohonan(idPermohonan);
        hakmilikPermohonan.setKeteranganKadarPremium(premiumTamb);
        if (premiumDenda != null) {
            BigDecimal pd = new BigDecimal(premiumDenda);
            hakmilikPermohonan.setDendaPremium(pd);
        }
        hakmilikPermohonan.setKeteranganCukaiBaru(hasil);
        hakmilikPermohonan.setKeteranganInfra(saliran);

        if (kategoriTanahBaruString != null) {
            hakmilikPermohonan.setKategoriTanahBaru(KKtgT);
        } else {
            hakmilikPermohonan.setKategoriTanahBaru(null);
        }
        pembangunanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        LOG.info("---SuccessPerakuan---");

        //No Warta    
        String noWarta1 = getContext().getRequest().getParameter("permohonanRujukanLuar.noRujukan");
        LOG.info("---No warta---" + noWarta1);
        listRujukanLuar = pembangunanService.findUlasanJabatanTekLT(idPermohonan);
        if (listRujukanLuar.size() > 0) {
            permohonanRujukanLuar = listRujukanLuar.get(0);
            permohonanRujukanLuar.setNoRujukan(noWarta1);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            infoAudit = pengguna.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(infoAudit);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
        } else {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setNoRujukan(noWarta1);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(infoAudit);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            KodRujukan kodRuj = new KodRujukan();
            kodRuj = kodRujukanDAO.findById("WT");
            permohonanRujukanLuar.setKodRujukan(kodRuj);
            laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
        }

        //ulasanLaporanTanah
        String ulasan1 = getContext().getRequest().getParameter("permohonanLaporanUlasan.ulasan");
        permohonanLaporanUlasan = pembangunanService.findPemohonLaporanUlasanByIdPermohonan(idPermohonan);

        if (permohonanLaporanUlasan != null) {
            if (ulasan1 != null) {
                permohonanLaporanUlasan.setUlasan(ulasan1);
            }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        } else {
            PermohonanLaporanUlasan pLU = new PermohonanLaporanUlasan();
            pLU.setPermohonan(permohonan);
            pLU.setInfoAudit(infoAudit);
            pLU.setCawangan(permohonan.getCawangan());
            pLU.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(pLU);
        }
        //senaraiKodKecerunanTanah
        senaraiKodKecerunanTanah = new ArrayList<KodKecerunanTanah>();
        senaraiKodKecerunanTanah = pembangunanService.findKodKecerunanFilterByidPermohonan(idPermohonan);
        for (int i = 0; i < senaraiKodKecerunanTanah.size(); i++) {
            System.out.println("-------------senaraiKodKecerunanTanah.size---" + senaraiKodKecerunanTanah.size());
            KodKecerunanTanah kodCerunTanah = new KodKecerunanTanah();
            kodCerunTanah = senaraiKodKecerunanTanah.get(i);

            String kod = getContext().getRequest().getParameter(kodCerunTanah.getNama());
            LOG.info("###KodCerunTanah" + kod);

            if (kod != null && !kod.equals("")) {
                laporanCerun = new PermohonanLaporanCerun();
                laporanCerun.setIdPermohonan(permohonan);
                laporanCerun.setInfoAudit(infoAudit);
                laporanCerun.setCawangan(permohonan.getCawangan());
                laporanCerun.setKodCerunanTanah(kodKecerunanTanahDAO.findById(kod));
                pembangunanService.simpanPermohonanLaporCerun(laporanCerun);
            }
        }

        LOG.info("###### laporantanah SAVE");
        infoAudit = laporanTanah.getInfoAudit();
        infoAudit.setTarikhKemaskini(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(pengguna);
        fasaPermohonan = pembangunanService.findFasaPermohonanByIdAliran(idPermohonan, "laporantanah");
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(infoAudit);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pembangunanService.simpanFasaPermohonan2(fasaPermohonan);

        addSimpleMessage(_MSG_SUCCES_SAVE);
        getInformation(idPermohonan);
        ltshow = true;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
    }

    public Resolution tambahBangunanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        TB = getContext().getRequest().getParameter("TB");
        LOG.info("tambahBangunanPopup idPermohonan : " + idPermohonan + " TB : " + TB);
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    public Resolution editBangunanPopup() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        TB = getContext().getRequest().getParameter("TB");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    public Resolution deleteBangunanPopup() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        LOG.info("###### deleteBangunanPopup" + idLaporBangunan);
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
        pembangunanService.deleteLaporBangunan(permohonanLaporanBangunan);
        return new RedirectResolution(UtilitiLtActionBean.class, "showForm");
    }

    public Resolution editBangunan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("###### editBangunan idPermohonan == " + idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            LOG.info("###### editBangunan idPermohonan x null ######");
            permohonan = permohonanDAO.findById(idPermohonan);
            idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
            permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
            if (permohonanLaporanBangunan != null) {
                infoAudit = permohonanLaporanBangunan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(infoAudit);
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan") != null) {
                    permohonanLaporanBangunan.setJenisBangunan(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai") != null) {
                    permohonanLaporanBangunan.setNilai(new BigDecimal(getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai")));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.keteranganTahunBinaan") != null) {
                    permohonanLaporanBangunan.setKeteranganTahunBinaan(String.valueOf(getContext().getRequest().getParameter("permohonanLaporanBangunan.keteranganTahunBinaan")));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya") != null) {
                    permohonanLaporanBangunan.setNamaPemunya(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPenyewa") != null) {
                    permohonanLaporanBangunan.setNamaPenyewa(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPenyewa"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua") != null) {
                    permohonanLaporanBangunan.setNamaKetua(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua"));
                }
                if (getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod") != null) {
                    permohonanLaporanBangunan.setJenisPendudukan(jenisPendudukanDAO.findById(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod")));
                }
                laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
                addSimpleMessage(_MSG_SUCCES_UPDATE);
            }
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        getInformation(idPermohonan);
        ltshow = true;
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    public Resolution simpanBangunan() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("###### simpanBangunan : idPermohonan = " + idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan == null) {
            System.out.println("Tiada id Permohonan");
        }
        if (idPermohonan != null) {
            System.out.println("id Permohonan" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);
            permohonanLaporanBangunan.setPermohonan(permohonan);
            permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
            permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
            permohonanLaporanBangunan.setKategori(TB);
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanLaporanBangunan.setInfoAudit(infoAudit);
            laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        getInformation(idPermohonan);
        ltshow = true;
        return new JSP(_PAGE_LT_POPUP).addParameter("popup", "true");
    }

    //Start Rekod Ulasan
    public Resolution rekodUlasanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("tambahBangunanPopup idPermohonan : " + idPermohonan);
        notaPermohonan = new PermohonanNota();
        return new JSP(_REKOD_ULASAN_POP);
    }

    public Resolution editRekodUlasanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idPermohonanNota = getContext().getRequest().getParameter("idPermohonanNota");
        LOG.info("editRekodUlasanPopup idPermohonanNota : " + idPermohonanNota);
        notaPermohonan = permohonanNotaDAO.findById(Long.valueOf(idPermohonanNota));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_REKOD_ULASAN_POP).addParameter("popup", "true");
    }

    public Resolution simpanRekodUlasan() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("###### ID Permohonan" + idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("###### simpanRekodUlasan : idPermohonan = " + idPermohonan);

        if (idPermohonan == null) {
            LOG.info("Tiada id Permohonan");
        }
        if (idPermohonan != null) {

            LOG.info("###### simpanRekodUlasan SAVE");
            permohonan = permohonanDAO.findById(idPermohonan);

            String note = getContext().getRequest().getParameter("notaPermohonan.nota");
            LOG.info("###### nota" + note);

            notaPermohonan = pembangunanService.findNotaByIdMohon(idPermohonan, "laporantanah");
            if (notaPermohonan != null) {
                LOG.info("###### notaPermohonan != null");
                permohonan = permohonanDAO.findById(idPermohonan);
                notaPermohonan = permohonanNotaDAO.findById(notaPermohonan.getIdMohonNota());
                notaPermohonan.setPermohonan(permohonan);
                notaPermohonan.setCawangan(permohonan.getCawangan());
                infoAudit = pengguna.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                notaPermohonan.setInfoAudit(infoAudit);
                notaPermohonan.setNota(note);
                pembangunanService.simpanNotaPermohonan(notaPermohonan);
            } else {
                LOG.info("###### notaPermohonan == null");
                notaPermohonan = new PermohonanNota();
                permohonan = permohonanDAO.findById(idPermohonan);
                notaPermohonan.setPermohonan(permohonan);
                notaPermohonan.setCawangan(permohonan.getCawangan());
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                notaPermohonan.setInfoAudit(infoAudit);
                notaPermohonan.setNota(note);
                notaPermohonan.setIdAliran("laporantanah");
                notaPermohonan.setStatusNota('A');
                pembangunanService.simpanNotaPermohonan(notaPermohonan);
            }
        }
        getInformation(permohonan.getIdPermohonan());
        ltshow = true;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/utilitiLt.jsp");
    }

    public Resolution simpanEditRekodUlasan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("###### ID Permohonan : " + idPermohonan);
        LOG.info("###### simpanEditRekodUlasan : Id Mohon Nota = " + notaPermohonan.getIdMohonNota());
        LOG.info("###### simpanEditRekodUlasan : Nota = " + notaPermohonan.getNota());
        PermohonanNota notaPermohonanTemp = permohonanNotaDAO.findById(notaPermohonan.getIdMohonNota());
        infoAudit = new InfoAudit();
        infoAudit = notaPermohonanTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        notaPermohonanTemp.setNota(notaPermohonan.getNota());
        pembangunanService.simpanNotaPermohonan(notaPermohonanTemp);
        addSimpleMessage(_MSG_SUCCES_SAVE);

        getInformation(idPermohonan);
        ltshow = true;
        LOG.info("###### simpanEditRekodUlasan ######");
        return new JSP(_REKOD_ULASAN_POP).addParameter("popup", "true");
    }

    public Resolution deleteRekodUlasanPopup() {
        String idPermohonanNota = getContext().getRequest().getParameter("idPermohonanNota");
        LOG.info("deleteRekodUlasanPopup idPermohonanNota:" + idPermohonanNota);
        pembangunanService.deletePermohonanNota(Long.valueOf(idPermohonanNota));
        return new RedirectResolution(UtilitiLtActionBean.class, "showForm");
    }
    //END Rekod Ulasan

    public Resolution deleteLaporCerun() {
        LOG.info("###### deleteLaporCerun");
        idLaporCerun = getContext().getRequest().getParameter("idLaporCerun");
        permohonanLaporanCerun = permohonanLaporanCerunDAO.findById(Long.parseLong(idLaporCerun));
        pembangunanService.deleteLaporCerun(permohonanLaporanCerun);
        return new RedirectResolution(UtilitiLtActionBean.class, "locate");
    }

    private boolean ekarCondition(Hakmilik hakmilik) {
        String kodUOM = hakmilik.getKodUnitLuas().getKod();
        if (kodUOM.equalsIgnoreCase("M")) {
            luas = hakmilik.getLuas();
            LOG.info("###### 8ekar converted to Mp :" + luas8EkarInMp);
            LOG.info("###### luas in Mp :" + luas);
            LOG.info("###### luas.compareTo(luas8EkarInMp) :" + (luas.compareTo(luas8EkarInMp)));
            if (luas.compareTo(luas8EkarInMp) > 0) {
                LOG.info("###### return true :");
                textSyor = true;
            } else {
                LOG.info("###### return false :");
                textSyor = false;
            }
        } else if (kodUOM.equalsIgnoreCase("H")) {
            luas = hakmilik.getLuas();
            LOG.info("###### 8ekar converted to H :" + luas8EkarInH);
            LOG.info("###### luas in H :" + luas);
            if (luas.compareTo(luas8EkarInH) > 0) {
                LOG.info("###### return true :");
                textSyor = true;
            } else {
                LOG.info("###### return false :");
                textSyor = false;
            }
        }
        return textSyor;
    }

    //Generating Report   
    public Resolution requestParam() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("--idPermohonan Generating Report-- :: " + idPermohonan);

        reportName = getContext().getRequest().getParameter("namaReport");
        LOG.info("reportName :: " + reportName);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/dev_param_LT.jsp");
    }
    //End of Generating

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public KodKecerunanTanah getKodkecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKodkecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodRizabDAO getKodRizabDAO() {
        return kodRizabDAO;
    }

    public void setKodRizabDAO(KodRizabDAO kodRizabDAO) {
        this.kodRizabDAO = kodRizabDAO;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public TanahRizabPermohonan getTrizabpermohonan() {
        return trizabpermohonan;
    }

    public void setTrizabpermohonan(TanahRizabPermohonan trizabpermohonan) {
        this.trizabpermohonan = trizabpermohonan;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public FasaPermohonan getFasaPermohonan2() {
        return fasaPermohonan2;
    }

    public void setFasaPermohonan2(FasaPermohonan fasaPermohonan2) {
        this.fasaPermohonan2 = fasaPermohonan2;
    }

    public String getIdFasa() {
        return idFasa;
    }

    public void setIdFasa(String idFasa) {
        this.idFasa = idFasa;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String norujukan) {
        this.noRujukan = noRujukan;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled2() {
        return disabled2;
    }

    public void setDisabled2(String disabled2) {
        this.disabled2 = disabled2;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public List<FasaPermohonan> getListFasa2() {
        return listFasa2;
    }

    public void setListFasa2(List<FasaPermohonan> listFasa2) {
        this.listFasa2 = listFasa2;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public String getKecerunanTanahString() {
        return kecerunanTanahString;
    }

    public void setKecerunanTanahString(String kecerunanTanahString) {
        this.kecerunanTanahString = kecerunanTanahString;
    }

    public String getStrukturTanahString() {
        return strukturTanahString;
    }

    public void setStrukturTanahString(String strukturTanahString) {
        this.strukturTanahString = strukturTanahString;
    }

    public String getKategoriTanahBaruString() {
        return kategoriTanahBaruString;
    }

    public void setKategoriTanahBaruString(String kategoriTanahBaruString) {
        this.kategoriTanahBaruString = kategoriTanahBaruString;
    }

    public List<KodKecerunanTanah> getSenaraiKodKecerunanTanah() {
        return senaraiKodKecerunanTanah;
    }

    public void setSenaraiKodKecerunanTanah(List<KodKecerunanTanah> senaraiKodKecerunanTanah) {
        this.senaraiKodKecerunanTanah = senaraiKodKecerunanTanah;
    }

    public String getTB() {
        return TB;
    }

    public void setTB(String TB) {
        this.TB = TB;
    }

    public PermohonanNota getNotaPermohonan() {
        return notaPermohonan;
    }

    public void setNotaPermohonan(PermohonanNota notaPermohonan) {
        this.notaPermohonan = notaPermohonan;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanTanamanList() {
        return permohonanLaporanTanamanList;
    }

    public void setPermohonanLaporanTanamanList(List<PermohonanLaporanBangunan> permohonanLaporanTanamanList) {
        this.permohonanLaporanTanamanList = permohonanLaporanTanamanList;
    }

    public String getMohonLaporUlasan() {
        return mohonLaporUlasan;
    }

    public void setMohonLaporUlasan(String mohonLaporUlasan) {
        this.mohonLaporUlasan = mohonLaporUlasan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public List<PermohonanLaporanCerun> getFindListlaporCerun() {
        return findListlaporCerun;
    }

    public void setFindListlaporCerun(List<PermohonanLaporanCerun> findListlaporCerun) {
        this.findListlaporCerun = findListlaporCerun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isTextSyor() {
        return textSyor;
    }

    public void setTextSyor(boolean textSyor) {
        this.textSyor = textSyor;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getNoWartaKonon() {
        return noWartaKonon;
    }

    public void setNoWartaKonon(String noWartaKonon) {
        this.noWartaKonon = noWartaKonon;
    }

    public Boolean getLtshow() {
        return ltshow;
    }

    public void setLtshow(Boolean ltshow) {
        this.ltshow = ltshow;
    }

    public Boolean getIdMohonShow() {
        return idMohonShow;
    }

    public void setIdMohonShow(Boolean idMohonShow) {
        this.idMohonShow = idMohonShow;
    }

    public String getNotaUlasan() {
        return notaUlasan;
    }

    public void setNotaUlasan(String notaUlasan) {
        this.notaUlasan = notaUlasan;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public PermohonanNota getHidePermohonanNota() {
        return hidePermohonanNota;
    }

    public void setHidePermohonanNota(PermohonanNota hidePermohonanNota) {
        this.hidePermohonanNota = hidePermohonanNota;
    }

    public List<HakmilikPermohonan> getSenaraiTanahMilik() {
        return senaraiTanahMilik;
    }

    public void setSenaraiTanahMilik(List<HakmilikPermohonan> senaraiTanahMilik) {
        this.senaraiTanahMilik = senaraiTanahMilik;
    }

    public String getIdLaporanTanah() {
        return idLaporanTanah;
    }

    public void setIdLaporanTanah(String idLaporanTanah) {
        this.idLaporanTanah = idLaporanTanah;
    }

    public List<ImejLaporan> getTimurLautImejLaporanList() {
        return timurLautImejLaporanList;
    }

    public void setTimurLautImejLaporanList(List<ImejLaporan> timurLautImejLaporanList) {
        this.timurLautImejLaporanList = timurLautImejLaporanList;
    }

    public List<ImejLaporan> getBaratLautImejLaporanList() {
        return baratLautImejLaporanList;
    }

    public void setBaratLautImejLaporanList(List<ImejLaporan> baratLautImejLaporanList) {
        this.baratLautImejLaporanList = baratLautImejLaporanList;
    }

    public List<ImejLaporan> getTenggaraImejLaporanList() {
        return tenggaraImejLaporanList;
    }

    public void setTenggaraImejLaporanList(List<ImejLaporan> tenggaraImejLaporanList) {
        this.tenggaraImejLaporanList = tenggaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratDayaImejLaporanList() {
        return baratDayaImejLaporanList;
    }

    public void setBaratDayaImejLaporanList(List<ImejLaporan> baratDayaImejLaporanList) {
        this.baratDayaImejLaporanList = baratDayaImejLaporanList;
    }

    public LaporanTanah getCheckingLaporanTanah() {
        return checkingLaporanTanah;
    }

    public void setCheckingLaporanTanah(LaporanTanah checkingLaporanTanah) {
        this.checkingLaporanTanah = checkingLaporanTanah;
    }

    public FasaPermohonan getCheckingFasaPermohonan() {
        return checkingFasaPermohonan;
    }

    public void setCheckingFasaPermohonan(FasaPermohonan checkingFasaPermohonan) {
        this.checkingFasaPermohonan = checkingFasaPermohonan;
    }

    public List<PermohonanLaporanCerun> getListlaporCerun() {
        return listlaporCerun;
    }

    public void setListlaporCerun(List<PermohonanLaporanCerun> listlaporCerun) {
        this.listlaporCerun = listlaporCerun;
    }

    public List<PermohonanRujukanLuar> getListRujukanLuar() {
        return listRujukanLuar;
    }

    public void setListRujukanLuar(List<PermohonanRujukanLuar> listRujukanLuar) {
        this.listRujukanLuar = listRujukanLuar;
    }

    public PermohonanLaporanCerun getLaporanCerun() {
        return laporanCerun;
    }

    public void setLaporanCerun(PermohonanLaporanCerun laporanCerun) {
        this.laporanCerun = laporanCerun;
    }

    public KodKategoriTanah getKKtgT() {
        return KKtgT;
    }

    public void setKKtgT(KodKategoriTanah KKtgT) {
        this.KKtgT = KKtgT;
    }

    public KodStrukturTanah getKST() {
        return KST;
    }

    public void setKST(KodStrukturTanah KST) {
        this.KST = KST;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public int getBilplotHakmiliktbl() {
        return bilplotHakmiliktbl;
    }

    public void setBilplotHakmiliktbl(int bilplotHakmiliktbl) {
        this.bilplotHakmiliktbl = bilplotHakmiliktbl;
    }

    public int getBilplotHakmiliktemp() {
        return bilplotHakmiliktemp;
    }

    public void setBilplotHakmiliktemp(int bilplotHakmiliktemp) {
        this.bilplotHakmiliktemp = bilplotHakmiliktemp;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }

    public String getForEdit() {
        return forEdit;
    }

    public void setForEdit(String forEdit) {
        this.forEdit = forEdit;
    }

    public String getPremiumTambahan() {
        return premiumTambahan;
    }

    public void setPremiumTambahan(String premiumTambahan) {
        this.premiumTambahan = premiumTambahan;
    }

    public String getDendaPremium() {
        return dendaPremium;
    }

    public void setDendaPremium(String dendaPremium) {
        this.dendaPremium = dendaPremium;
    }

    public String getSewaTahunan() {
        return sewaTahunan;
    }

    public void setSewaTahunan(String sewaTahunan) {
        this.sewaTahunan = sewaTahunan;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

    public String getKodSekatanKepentinganBaru() {
        return kodSekatanKepentinganBaru;
    }

    public void setKodSekatanKepentinganBaru(String kodSekatanKepentinganBaru) {
        this.kodSekatanKepentinganBaru = kodSekatanKepentinganBaru;
    }

    public List<PermohonanPlotPelan> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<PermohonanPlotPelan> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public PermohonanPlotPelan getMpp() {
        return mpp;
    }

    public void setMpp(PermohonanPlotPelan mpp) {
        this.mpp = mpp;
    }

    public BigDecimal getJumluasHakmilik() {
        return jumluasHakmilik;
    }

    public void setJumluasHakmilik(BigDecimal jumluasHakmilik) {
        this.jumluasHakmilik = jumluasHakmilik;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSumbanganSaliran() {
        return sumbanganSaliran;
    }

    public void setSumbanganSaliran(String sumbanganSaliran) {
        this.sumbanganSaliran = sumbanganSaliran;
    }

    public String getIdPermohonan1() {
        return idPermohonan1;
    }

    public void setIdPermohonan1(String idPermohonan1) {
        this.idPermohonan1 = idPermohonan1;
    }

    public String getKodKatgTanah() {
        return kodKatgTanah;
    }

    public void setKodKatgTanah(String kodKatgTanah) {
        this.kodKatgTanah = kodKatgTanah;
    }

    public final void getInformation(String idPermohonan) {

        LOG.info("-------------Check Permohonan Start---------");

        if (idPermohonan != null) {
            idPermohonan1 = idPermohonan;
        }
        LOG.debug("======== idPermohonan========== :" + idPermohonan1);

        if (idPermohonan1 != null) {
            ltshow = true;
            idMohonShow = false;
            LOG.info("--------TEST---------");
            permohonan = permohonanDAO.findById(idPermohonan1);
            kodNegeri = conf.getKodNegeri();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            imejLaporanList = new ArrayList<ImejLaporan>();
            utaraImejLaporanList = new ArrayList<ImejLaporan>();
            selatanImejLaporanList = new ArrayList<ImejLaporan>();
            timurImejLaporanList = new ArrayList<ImejLaporan>();
            baratImejLaporanList = new ArrayList<ImejLaporan>();
            permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
            permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
            findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();

            if (idPermohonan1 != null) {
                hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                tanahRizabList = permohonan.getSenaraiTanahRizab();
                permohonanPengambilan = service.findByidPermohonan(idPermohonan1);
                permohonanLaporanBangunanList = pembangunanService.getLaporBngnByIdLaporan(idPermohonan1);
                permohonanLaporanTanamanList = pembangunanService.getLaporTnmnByIdLaporan(idPermohonan1);
                laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan1);

                hakmilikPermohonan = hakmilikPermohonanList.get(0);
                System.out.println("###### Laporan tanah" + laporanTanah);
                System.out.println("######### rehydrate hakmilikpermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};

                ekarCondition(hakmilik);

                if (laporanTanah != null) {

                    imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    utaraImejLaporanList = laporanTanahService.getBngnLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                    listNota = pembangunanService.findNotaPermohonanByIdPermohonan(permohonan.getIdPermohonan());
                    permohonanLaporanUlasan = pembangunanService.findPemohonLaporanUlasanByIdPermohonan(idPermohonan1);

                    if (laporanTanah.getStrukturTanah() != null) {
                        strukturTanahString = laporanTanah.getStrukturTanah().getKod();
                    } else {
                        strukturTanahString = "";
                    }
                    if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                        kategoriTanahBaruString = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                    } else {
                        kategoriTanahBaruString = "0";
                    }
                } else {
                    laporanTanah = new LaporanTanah();
                }

                hidePermohonanNota = pembangunanService.findNotaByIdMohon(idPermohonan1, "laporantanah");
                listFasa = pembangunanService.findFasaPermohonanByIdPermohonan2(permohonan.getIdPermohonan());
                listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());

                LOG.info("###### listFasa : " + listFasa.size());
                if (!(listFasa.isEmpty())) {
                    for (int i = 0; i < listFasa.size(); i++) {
                        FasaPermohonan fp = new FasaPermohonan();
                        fp = listFasa.get(i);
                        fasaPermohonan = listFasa.get(i);

                    }
                }
                if (!(listFasa.isEmpty())) {
                    for (int i = 0; i < listFasa.size(); i++) {
                        FasaPermohonan fp = new FasaPermohonan();
                        fp = listFasa.get(i);
                    }
                }
                senaraiKodKecerunanTanah = new ArrayList<KodKecerunanTanah>();
                senaraiKodKecerunanTanah = pembangunanService.findKodKecerunanFilterByidPermohonan(permohonan.getIdPermohonan());
                findListlaporCerun = pembangunanService.findLaporCerunListByIdPermohonan(permohonan.getIdPermohonan());    //BARU PNY
                listRujukanLuar = permohonan.getSenaraiRujukanLuar();
                System.out.println("listRujukanLuar : " + listRujukanLuar.size());
                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        permohonanRujukanLuar = new PermohonanRujukanLuar();
                        permohonanRujukanLuar = listRujukanLuar.get(i);
                        if (permohonanRujukanLuar.getKodRujukan() != null) {
                            System.out.println("Kod : " + permohonanRujukanLuar.getKodRujukan().getKod());
                            if (permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("WT") || permohonanRujukanLuar.getKodRujukan().getKod().equalsIgnoreCase("NF")) {
                                permohonanRujukanLuar = listRujukanLuar.get(i);

                                noWartaKonon = permohonanRujukanLuar.getNoRujukan();
                            }
                        }
                    }
                }
                System.out.println("Ruj Luar : " + noWartaKonon);
                tanahRizabList = permohonan.getSenaraiTanahRizab();
                for (int i = 0; i < tanahRizabList.size(); i++) {
                    tanahrizabpermohonan = tanahRizabList.get(i);
                    System.out.println("Laporan Tanah ---->" + tanahrizabpermohonan.getNoWarta());
                }

                //ADDED 4 SBMS & TSPSS CONDITION
                //START
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")) {
                    listHakmilik = new ArrayList<PermohonanPlotPelan>();
                    bilplotHakmiliktemp = 0;
                    bilplotHakmiliktbl = 0;

                    LOG.info("plot pelan list.");
                    listplotpelan = new ArrayList<PermohonanPlotPelan>();
                    listplotpelan = pembangunanService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);

                    LOG.info("---------listplotpelan---------list:" + listplotpelan);
                    if (!(listplotpelan.isEmpty())) {
                        for (int a = 0; a < listplotpelan.size(); a++) {
                            mpp = new PermohonanPlotPelan();
                            mpp = listplotpelan.get(a);

                            if (mpp.getNoPt() == null || mpp.getNoPt().equalsIgnoreCase("Y")) {
                                if (mpp.getPemilikan().getKod() == 'H' || mpp.getPemilikan().getKod() == 'K') {
                                    LOG.info("plot pelan hakmilik list.");
                                    bilplotHakmiliktbl = mpp.getBilanganPlot();
                                    BigDecimal luasH = mpp.getLuas();
                                    luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                                    System.out.println("****** luasH ******* = " + luasH);
                                    jumluasHakmilik = jumluasHakmilik.add(luasH);
                                    bilplotHakmiliktemp = bilplotHakmiliktemp + bilplotHakmiliktbl;
                                    System.out.println("plot hakmilik = " + bilplotHakmiliktemp);
                                    listHakmilik.add(mpp);
                                }
                            }
                        }
                    }
                }

                idPlot = getContext().getRequest().getParameter("idPlot");
                if (idPlot != null) {
                    mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));

                    if (mpp.getKeteranganKadarPremium() != null) {
                        premiumTambahan = mpp.getKeteranganKadarPremium();
                    }

                    if (mpp.getKeteranganKadarDaftar() != null) {
                        dendaPremium = mpp.getKeteranganKadarDaftar();
                    }

                    if (mpp.getKeteranganCukaiBaru() != null) {
                        sewaTahunan = mpp.getKeteranganCukaiBaru();
                    }

                    if (mpp.getKodSyaratNyata() != null) {
                        kodSyaratNyataBaru = mpp.getKodSyaratNyata().getSyarat();
                    }
                    if (mpp.getKodSekatanKepentingan() != null) {
                        kodSekatanKepentinganBaru = mpp.getKodSekatanKepentingan().getSekatan();
                    }
                    if (mpp.getKosInfra() != null) {
                        sumbanganSaliran = mpp.getKosInfra();
                    }
                    if (mpp.getKategoriTanah() != null) {
                        kodKatgTanah = mpp.getKategoriTanah().getKod();
                    }
                } else {
                    LOG.debug("---NO DATA---");
                }

                //END
            }
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pengguna.getNama());
            LOG.info("-------------rehydrate Stop---------");
        }
        LOG.info("--------Check Permohonan Stop---------");
    }

    //START SYOR 4 SBMS AND TSPSS
    public Resolution editPopup() {
        LOG.debug("editPopup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiEditNilaiForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution editNyata() {
        LOG.debug("editNyata");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution editSekatan() {
        LOG.debug("editSekatan");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup() {
        LOG.info("kemaskini mohon plot pelan start.");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("id = " + idPermohonan);
        idPlot = getContext().getRequest().getParameter("idPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        //premiumTambahan
        if (premiumTambahan != null) {
            mpp.setKeteranganKadarPremium(premiumTambahan);
        }

        //dendaPremium
        if (dendaPremium != null) {
            mpp.setKeteranganKadarDaftar(dendaPremium);
        }

        //sewaTahunan
        if (sewaTahunan != null) {
            mpp.setKeteranganCukaiBaru(sewaTahunan);
        }

        //sumbanganSaliran
        if (sumbanganSaliran != null) {
            mpp.setKosInfra(sumbanganSaliran);
        }

        //penjenisan
        if (kodKatgTanah != null) {
            mpp.setKategoriTanah(kodKategoriTanahDAO.findById(kodKatgTanah));
        }

        pembangunanService.simpanPlotPelan(mpp);
        addSimpleMessage("Kemaskini telah berjaya.");
        LOG.info("kemaskini mohon plot pelan finish.");
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiEditNilaiForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatanSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSyaratNyataSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatanSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info(idPlot + "+" + forEdit);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatan != null) {
            listKodSekatan = pembangunanService.searchKodSekatan(kodSekatan, kc, sekatan);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pembangunanService.searchKodSekatan("%", kc, sekatan);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyataSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info(idPlot + "+" + forEdit);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSyaratNyata != null) {
            LOG.info("---cariKodSyaratNyata2---kodKatTanah-----:" + kodKatTanah);
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata, kodKatTanah);
            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata, kodKatTanah);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKodSyaratNyataSBMSnTSPSS() {
        LOG.info("--Save Code--");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info(idPlot + "+" + forEdit);

        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        //syaratNyata & syaratKepentingan      
        String kodSyaratNyata1 = getContext().getRequest().getParameter("selectedKod");
        LOG.info("---kodSyaratNyata" + kodSyaratNyata1);
        if (kodSyaratNyata1 != null) {
            mpp.setKodSyaratNyata(kodSyaratNyataDAO.findById(kodSyaratNyata1));
        }

        String kodSyaratSekatan1 = getContext().getRequest().getParameter("selectedKod1");
        LOG.info("----kodSyaratSekatan" + kodSyaratSekatan1);
        if (kodSyaratSekatan1 != null) {
            mpp.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(kodSyaratSekatan1));
        }

        pembangunanService.simpanPlotPelan(mpp);
        addSimpleMessage("Kod Telah Ditambah");
        getContext().getRequest().setAttribute("idPlot", Boolean.TRUE);
        getContext().getRequest().setAttribute("forEdit", Boolean.TRUE);
        LOG.info("---CODE FINISH----.");
        getInformation(idPermohonan);
        return new JSP("pembangunan/utiliti/utilitiLaporanTanahPopupMelakaa/utilitiCarianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }
    //END ADDING SBMS & TSPSS CODES
}