/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.laporan.tanah;
//package com.javacodegeeks.snippets.desktop;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.KodKategoriTanahDAO; // Added by Iskandar 12 Mar 2013
import etanah.dao.KodDaerahDAO; // Added by Iskandar 12 Mar 2003
import etanah.dao.KodLotDAO;
import etanah.dao.KodUOMDAO; // Added by Iskandar 12 Mar 2003
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.model.KodPemilikan;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTanah;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanPohon;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanLaporanUsaha;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import org.apache.log4j.Logger;
import java.text.ParseException;
import net.sourceforge.stripes.action.FileBean;
import etanah.view.stripes.pelupusan.disClass.DisLaporTanahKawasan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import java.text.SimpleDateFormat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.RegService;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.service.CalcTaxPelupusan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import etanah.service.ambil.BorangPerHakmilikService;
import etanah.service.ambil.TampalHakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.stripes.pelupusan.LaporanTanah4ActionBean;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanTanahTerdahulu;
import java.util.Date;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;

import java.io.File;

/**
 *
 * @author wazer
 */
@UrlBinding("/common/laporan/tanah/laporantanahNewActionBean")
public class laporantanahNewActionBean extends AbleActionBean {

    private static String kodDaerahStatic;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodKeputusanDAO keputusanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodDaerahDAO kodDaerahDAO; // Added by Iskandar, 12 Mar 2013
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO; // Added by Iskandar, 12 Mar 2013
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO; // Added by Iskandar, 12 Mar 2013
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RegService regService;
    @Inject
    CalcTaxPelupusan calcTax;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    TampalHakmilikService tampalHakmilikService;
    @Inject
    BorangPerHakmilikService borangPerhakmilikService;
    private String noFail;
    private String idPermohonan;
    private String stageId;
    private String negeri;
    private String kodNegeri;
    private String kodDaerah;
    private String catatanKeg;
    private String idHakmilik;
    private String perihalHakmilik;
    private String ulasan;
    private String ksn;
    private String statBdnPngwl;
    private String ulsn;
    private String kodHmlk;
    private String kodHmlkTetap;
    private String kodU;
    private String kodSU;
    private String keadaanTanah;
    private String keadaanTanah1;
    private String keteranganKadarPremium;
    private Integer tempohPajakan;
    private String kodD;
    private String katTanahPilihan;
    private String kodGunaTanah;
    private String kegunaTanah;
    private String kodPar;
    private String noLitho;  // Added by Iskandar 12 Mar 2013, 12:34 PM
    private String tanahR;
    private String kodT;
    private String kecerunanT;
    private String klasifikasiT;
    private String keg;
    private String kod;
    private String syaratNyata;
    private String kodSktn;
    private String syaratNyata1;
    private String kand;
    private String keadaankand;
    private String ulasanKanan;
    private String plpulasan0;
    private String tempohSyorUOM;
    private String catatan;
    private String kategori;
    private String idLaporTanah;
    private String idKandungan;
    private String sizeSenaraiLaporUlas;
    private String indexSyarat;
    private String unitTempohUOM;
    private String kupon;
    private String noPtSementara;
    private String idtanahrizabPermohonan;
    private String catatanKWS;
    private String idMohonKws;
    private String addnoWarta;
    private String addnoPelanWarta;
    private String addtarikhWarta = null;
    private boolean forSeksyen; // Added by Iskandar, 12 Mar 2013
    private boolean forBPM; // Added by Iskandar, 12 Mar 2013
    private String mercuTanda;// assume this column is not used (nov 2014)
    String id2;
    String id;
    private char kodP;
    private char pandanganImej;
    private NoPt noPT = new NoPt();
    private NoPt noPtTemp;
    private KodBandarPekanMukim kodBPM; // Added by Iskandar 12 Mar 2013
    private DisLaporanTanahSempadan disLaporanTanahSempadan;
    private DisPermohonanBahanBatu disPermohonanBahanBatu;
    private DisLaporTanahKawasan disLaporTanahKawasan;
    private DisSyaratSekatan disSyaratSekatan;
    private DisLaporanTanahController disLaporanTanahController;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private PermohonanLaporanKawasan permohonanLaporanKawasan;
    private PermohonanLaporanKandungan permohonanLaporanKandungan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private PermohonanManual permohonanManual;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan mohonFasa;
    private TanahRizabPermohonan tanahrizabpermohonan1;
    private PermohonanPermitItem permohonanPermitItem;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private PermohonanPermitItem pmi = new PermohonanPermitItem();
    private PermohonanLaporanPohon permohonanLaporanPohon;
    private PermohonanLaporanUsaha permohonanLaporanUsaha;
    private KodUOM jumlahIsipaduUOM;
    private FileBean fileToBeUpload;
    private Pemohon pemohon;
    private PermohonanManual pm;
    private KodKeputusan kodKeputusan;
    etanahActionBeanContext ctx;
    Permohonan prmhnn;
    private Permohonan per;
    private String kodUrusan;
    private String keputusan;
    private String keputusanOleh;
    private String tarikhKeputusan;
    private String pmhn;
    private String usahaLuasUom;
    private String note;
    private KodCawangan cawangan;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<KodBandarPekanMukim> senaraiKodBPM; // Added by Iskandar, 12 Mar 2013
    private List<KodKegunaanTanah> listGT; // Added by Iskandar, 12 Mar 2013
    private List<KodKategoriTanah> senaraiKodKatTanah; // Added by Iskandar, 12 Mar 2013
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs; // Added by Iskandar, 12 Mar 2013
    private List<HakmilikPermohonan> senaraiHakmilikPerihalTanah;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanLaporanUlasan> senaraiLaporanKandungan1;
    private List<PermohonanLaporanUlasan> senaraiLaporanKandunganPPTKanan;
    private List<LaporanTanahSempadan> listlts;
    private LaporanTanahSempadan lts;
//    LaporanTanahSempadan lts = new LaporanTanahSempadan();
    private List<String> senaraikodKadarPremium;
//    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal;
    private List<PermohonanLaporanPohon> senaraiPermohonanLaporanPohon;
    private List<PermohonanManual> permohonanManualList;
    private List<KodSeksyen> kodSeksyenList;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> hakmilikImejLaporanList;
    private List<ImejLaporan> hakmilikImejLaporanListPanorama;
    private List<ImejLaporan> hakmilikImejLaporanListLampiranD;
    private List<ImejLaporan> hakmilikImejLaporanListLampiranG;
    private List<ImejLaporan> hakmilikImejLaporanListU;
    private List<ImejLaporan> hakmilikImejLaporanListS;
    private List<ImejLaporan> hakmilikImejLaporanListB;
    private List<ImejLaporan> hakmilikImejLaporanListT;
    private List<ImejLaporan> hakmilikImejLaporanListEdit;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List<LaporanTanahSempadan> senaraiLaporTanahSpdn;
    private List<NoPt> senaraiNoPt;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<PermohonanLaporanUsaha> listPermohonanLaporanUsaha;
    private List<PermohonanManual> pmList;
    private List<KodDUN> listKodDun;
    private List<KodItemPermit> kodItemPermitList;
    private List<DisPermohonanTanahTerdahulu> listpermohonanTanahTerdahulu;
    private List<HakmilikPermohonan> hmListCount;
    private List<HakmilikPermohonan> hmList;
    private HakmilikPermohonan mohonHakmilik;
    List<HakmilikPermohonan> senaraiHakmilik;
    private List<Hakmilik> list;
    private List<KodAgensi> listKodAgensi;
    private BigDecimal luasDilulus;
    private BigDecimal usahaHarga;
    private BigDecimal amnt = BigDecimal.ZERO;
    private BigDecimal nilaiTanah = BigDecimal.ZERO;
    private BigDecimal usahaLuas;
    private Integer usahaBilanganPokok = new Integer(0);
    private String kodBpm = new String(); // Added by Iskandar, 12 Mar 2013
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private long idImej;
    private static final Logger LOG = Logger.getLogger(laporantanahNewActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng; //Add for charting
    private static int day;
    private String kodAgensiNama;
    private KodAgensi kodAgensi;
    private int sizeKod;
    private String namaPemohon;
    private String tarikhDaftar;
    private boolean flag = true;
    private String kodUomLuasLulus;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private ImejLaporan imejL;
    private List<Hakmilik> senaraiHakm;
    private BigDecimal royaltiTanahKerajaan;
    private String kadarBayarPRMP;
    private String lokasi;
    String result = "";
    String royaltiTanahKerajaanUom;
    private List<String> noLotList = new ArrayList<String>();
    private List<TanahRizabPermohonan> tanahrizabpermohonan1ist = new ArrayList<TanahRizabPermohonan>();
    private List<PermohonanLaporanPelan> permohonanLaporanPelanList = new ArrayList<PermohonanLaporanPelan>();
    private List<LaporanTanah> laporanTanahList = new ArrayList<LaporanTanah>();
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
    private List<LaporanTanahSempadan> laporanTanahSempadanList = new ArrayList<LaporanTanahSempadan>();
    private List<FasaPermohonan> fasaPermohonanList = new ArrayList<FasaPermohonan>();
//     private List<LaporanTanahSempadan> senaraiLaporTanahSpdn;
//        private PermohonanLaporanPelan permohonanLaporanPelan;
//        private List<TanahRizabPermohonan> tanahrizabpermohonan1ist;
    private String idMohonHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList1;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList3;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String kodUnitLuas;
    private List<PermohonanManual> mohonManualList;
    private List<Permohonan> permohonanLamaListUrusan;
    private List<Permohonan> permohonanLamaList;
    private Permohonan permohonanLama;
    private PermohonanTuntutanKos cagaranJalan;
    private PermohonanTuntutanKos bayarankupon;
    private LaporanTanah laporanTanah1;
    DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();
//    private List hakmilikPermohonanList;
    BigDecimal luas;
    private KodUOM kodUOM;
    private String kodPa;
    private String noFailBaru;
    private String idMohonManual;
    private String sokong;
    private String sebab;
    private String ulasanSebab;
    private String idLaporUlasan;
    private String idlaporTnhSmpdn;
    private String strukturTanahLain;
    private String ulasanLain;
//    private String usaha;
    private String diusaha;
    private String oleh;
//    private String bangunan;
    private String perenggan;
    private String rancanganKerajaan;
    private String usahaTanam;
    private String tanahBertumpu;
    private String keteranganTanahBertumpu;
    private Character dilintasTiangElektrik;
    private Character dilintasTiangTelefon;
    private Character dilintasLaluanGas;
    private Character dilintasPaip;
    private Character dilintasTaliar;
    private Character dilintasSungai;
    private Character dilintasParit;
    private Character bangunan;
    private Character usaha;
    private String idMohonlaporKws;
    private String catatanKwsn;
    private Date trhWartaKwsn;
    private String noWartaKwsn;
    private String noPelankawasan;
    private String jenishasil;
    private String agensiUpahUkur;
    private Integer rizabKwsn;
    private String isiPaduBatu;
    private String tempohDisyor;
    private String cagarJalan;
    private String kuponQty;
    private String idLaporTanahSpdn;
    private String sebabBoleh;
    private String bolehBerimilik;
    private String dendaPremium;

    @DefaultHandler
    public Resolution viewOnlyLaporanTanahPPT() {
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahNew.jsp").addParameter("tab", "true");
//        return new JSP("pengambilan/APT/laporan_tanah_main.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiKodGunaTanahByKatTanah() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/partial_kodgunatanah.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodDunByKodPar() {
        String kodParlimen = (String) getContext().getRequest().getParameter("kodPar");
        if (StringUtils.isNotBlank(kodParlimen)) {
            listKodDun = disLaporanTanahService.getPelupusanService().findListKodDunByKodParlimen(kodParlimen);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/partial_kodDun.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage(); //iwe
            //stageId = "laporan_tanah";
        }
        return stageId;
    }

    public Resolution search() {
        indexSyarat = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_lptn.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        indexSyarat = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn_lptn.jsp").addParameter("popup", "true");
    }

    public Resolution perihalTanah() {

        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahService().findLaporanTanahByIdMohonIdMH(hakmilikPermohonan.getPermohonan().getIdPermohonan(), hakmilikPermohonan.getId());
        PermohonanLaporanPelan mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
//        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (hakmilikPermohonan.getKodMilik() != null) {
            kodP = hakmilikPermohonan.getKodMilik().getKod();
        }
        if (hakmilikPermohonan.getKodKawasanParlimen() != null) {
            kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
        }
        if (hakmilikPermohonan.getKodDUN() != null) {
            kodD = hakmilikPermohonan.getKodDUN().getKod();
        }
        if (hakmilikPermohonan.getLokasi() != null) {
            lokasi = hakmilikPermohonan.getLokasi();
        }
        if (!permohonan.getKodUrusan().getKod().equals("NT6A") || !permohonan.getKodUrusan().getKod().equals("sek4")) {
            if (mohonLaporPelan != null) {
                if (mohonLaporPelan.getKodTanah() != null) {
                    kodT = mohonLaporPelan.getKodTanah().getKod();
                }
            }
        }

        if (laporanTanah.getSebabTidakBolehBerimilik() != null) {
            bolehBerimilik = laporanTanah.getBolehBerimilik();
        }
        if (laporanTanah.getBolehBerimilik() != null) {
            sebabBoleh = laporanTanah.getSebabTidakBolehBerimilik();
        }
        if (laporanTanah.getSebabTidakBolehBerimilik() != null) {
            sebabBoleh = laporanTanah.getSebabTidakBolehBerimilik();
        }

        permohonan = hakmilikPermohonan.getPermohonan();

        hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
        hakmilikImejLaporanListPanorama = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdPanorama(laporanTanah.getIdLaporan());
        rehydrate();

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahPerihalNew.jsp").addParameter("popup", "true");
    }

    public Resolution latarBelakang() {

        if (idMohonHakmilik == null) {
            idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        }
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        }

        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        permohonan = hakmilikPermohonan.getPermohonan();
        rehydrate();

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahLatarBelakangNew.jsp").addParameter("popup", "true");
    }

    public Resolution sempadan() {
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
//        permohonanLaporanPohon = new PermohonanLaporanPohon();
        getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSempadanNew.jsp").addParameter("popup", "true");
    }

    public Resolution keadaanTanah() {
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        listPermohonanLaporanUsaha = disLaporanTanahService.getPelupusanService().findListMohonLaporUsahaByIdLaporan(laporanTanah.getIdLaporan());

        permohonanLaporanKandungan = pelupusanService.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
        if (listPermohonanLaporanUsaha.size() > 0) {
            permohonanLaporanUsaha = listPermohonanLaporanUsaha.get(0);
        }

        permohonanLaporanBangunanList = disLaporanTanahService.getLaporanTanahService().getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");

        if (laporanTanah.getKecerunanTanah() != null) {

            kecerunanT = laporanTanah.getKecerunanTanah().getKod();
        }

        if (laporanTanah.getStrukturTanah() != null) {
            klasifikasiT = laporanTanah.getStrukturTanah().getKod();

        }

        if (laporanTanah.getStrukturTanahLain() != null) {
            strukturTanahLain = laporanTanah.getStrukturTanahLain();
        }

        if (laporanTanah.getKodKeadaanTanah() != null) {
            keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
        }

        if (laporanTanah.getTanahBertumpu() != null) {
            tanahBertumpu = laporanTanah.getTanahBertumpu();
        }

        if (laporanTanah.getKeteranganTanahBertumpu() != null) {
            keteranganTanahBertumpu = laporanTanah.getKeteranganTanahBertumpu();
        }

        if (laporanTanah.getDilintasTiangElektrik() != null) {
            dilintasTiangElektrik = laporanTanah.getDilintasTiangElektrik();
        }

        if (laporanTanah.getDilintasTiangTelefon() != null) {
            dilintasTiangTelefon = laporanTanah.getDilintasTiangTelefon();
        }

        dilintasLaluanGas = laporanTanah.getDilintasLaluanGas();
        dilintasPaip = laporanTanah.getDilintasPaip();
        dilintasTaliar = laporanTanah.getDilintasTaliar();
        dilintasSungai = laporanTanah.getDilintasSungai();
        dilintasParit = laporanTanah.getDilintasParit();
        ulasan = laporanTanah.getSyor();
        ulasanLain = laporanTanah.getUlasan();
        usaha = laporanTanah.getUsaha();
        diusaha = laporanTanah.getDiusaha();
        if (permohonanLaporanUsaha != null) {
            oleh = permohonanLaporanUsaha.getDiUsahaOleh();
        }
        mercuTanda = laporanTanah.getMercuTanda();
        usahaTanam = laporanTanah.getUsahaTanam();
        usahaLuas = laporanTanah.getUsahaLuas();
        if (laporanTanah.getUsahaLuasUom() != null) {
            usahaLuasUom = laporanTanah.getUsahaLuasUom().getKod();
        }

        usahaBilanganPokok = laporanTanah.getUsahaBilanganPokok();
        usahaHarga = laporanTanah.getUsahaHarga();

        if (laporanTanah.getAdaBangunan() != null) {
            bangunan = laporanTanah.getAdaBangunan().charValue();
        }

        perenggan = laporanTanah.getPerenggan();
        nilaiTanah = laporanTanah.getNilaiTanah();
        rancanganKerajaan = laporanTanah.getRancanganKerajaan();

//        permohonanLaporanPohon = new PermohonanLaporanPohon();
        getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahKeadaanTanahNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanSaje() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        idMohonHakmilik = ctx.getRequest().getParameter("idMohonHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        perenggan = (String) getContext().getRequest().getParameter("laporanTanah.perenggan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        LaporanTanah laporanTanahTemp = new LaporanTanah();
        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));

        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (idMohonHakmilik != null) {
//          hakmilikPermohonanSave = hakmilikPermohonanDAO.findById(idImej);
            hakmilikPermohonanSave = hakmilikPermohonanService.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        }
//        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
//            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
//        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
////            noPtTemp = new NoPt();
////            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
//            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
//        } else if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PHLP")) {
//            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//        } else if (permohonan.getKodUrusan().getKod().equals("PLPT")) {
////            List<hakmilik
//            hakmilikPermohonanSave = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilik, idPermohonan);
//        } else {
//            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
//        }

        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PHLP")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
            for (HakmilikPermohonan hp : hpList) {
                if (hakmilikPermohonanSave != null) {
                    laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hp.getId())}, 0);
                    hakmilikPermohonanSave.setInfoAudit(disLaporanTanahService.findAudit(hp, "update", pguna));
                } else {
                    hakmilikPermohonanSave = new HakmilikPermohonan();
                    hakmilikPermohonanSave.setInfoAudit(disLaporanTanahService.findAudit(hp, "new", pguna));
                    hakmilikPermohonanSave.setPermohonan(permohonan);
                    if (!StringUtils.isEmpty(idHakmilik)) {
                        hakmilikPermohonanSave.setHakmilik(disLaporanTanahService.getHakmilikDAO().findById(idHakmilik));
                    }
                }
                /*
                 * MOHON_HAKMILIK
                 */
                if (kodNegeri.equals("05")) {
                    String hakmilikJarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                    if (!StringUtils.isEmpty(hakmilikJarak)) {
                        hakmilikPermohonanSave.setJarak(hakmilikJarak);
                    } else if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                        hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                    }
                    String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                    if (!StringUtils.isEmpty(unitJarak)) {
                        hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                        if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarakDari())) {
                            hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                        }
                        if (hakmilikPermohonan != null && hakmilikPermohonan.getJarakDariKediaman() != null) {
                            hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                        }
                    }
                    String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                        hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                    }
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                } else if (kodNegeri.equals("04")) {
                    if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                        if (!StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                            hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                        }
                        String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                        if (!StringUtils.isEmpty(unitJarak)) {
                            hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                        }
                        hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                        hakmilikPermohonanSave.setJarakDariNama(hakmilikPermohonan.getJarakDariNama());
                        hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                        String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                        if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                            hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                        }
                        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                    }
                }
                if (laporanTanahTemp != null) {
                    laporanTanahTemp.setInfoAudit(disLaporanTanahService.findAudit(laporanTanahTemp, "update", pguna));
                    if (!StringUtils.isEmpty(kecerunanT)) {
                        laporanTanahTemp.setKecerunanTanah(disLaporanTanahService.getKodKecerunanTanahDAO().findById(kecerunanT));
                        if (kecerunanT.equals("TG")) {
                            laporanTanahTemp.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                            laporanTanahTemp.setKecerunanBukit(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("CR")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("RD")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("BK")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("PY")) {
                            laporanTanahTemp.setParasAir(laporanTanah.getParasAir());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setKecerunanBukit(null);
                        } else if (kecerunanT.equals("LL")) {
                            laporanTanahTemp.setStrukturTambahanKedudukan(laporanTanah.getStrukturTambahanKedudukan());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setKecerunanBukit(null);
                        }
                    }

                    if (!StringUtils.isEmpty(klasifikasiT)) {
                        laporanTanahTemp.setStrukturTanah(disLaporanTanahService.getKodStrukturTanahDAO().findById(klasifikasiT));
                    }

                    laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
                    laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                    if (!StringUtils.isEmpty(keadaanTanah1)) {
                        laporanTanahTemp.setKodKeadaanTanah(disLaporanTanahService.getKodKeadaanTanahDAO().findById(keadaanTanah1));
                        // Saving into MOHON LAPOR KAND
                        if (keadaanTanah1.equals("LL")) {
                            permohonanLaporanKandungan = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanahTemp.getIdLaporan());
                            if (permohonanLaporanKandungan != null) {
                                permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "update", pguna));
                            } else {
                                permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                                permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "new", pguna));
                            }
                            permohonanLaporanKandungan.setLaporanTanah(laporanTanahTemp); //Afham - ubah skit untuk keadaan tanah
                            permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                            permohonanLaporanKandungan.setKand(keadaankand);
                            disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                        }
                    }
                    /*
                     * TANAH DIUSAHAKAN
                     */
                    if (Character.isDefined(laporanTanah.getUsaha())) {
                        laporanTanahTemp.setUsaha(laporanTanah.getUsaha());
                    }
                    if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha().equals('Y')) {
                        laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                        if (laporanTanah.getTarikhMulaUsaha2() != null) {
                            laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
                        }
                        if (mercuTanda == null) {
                            laporanTanahTemp.setMercuTanda(laporanTanah.getMercuTanda());
                            LOG.info("DAH SIMPAN MERCU TANDA :-)");
                        }
                        laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());

                        if (usahaLuas != null && !StringUtils.isEmpty(usahaLuas.toString())) {
                            laporanTanahTemp.setUsahaLuas(usahaLuas);
                        }

                        if (usahaBilanganPokok != null) {
                            laporanTanahTemp.setUsahaBilanganPokok(usahaBilanganPokok);
                        }

                        if (usahaHarga != null && !StringUtils.isEmpty(usahaHarga.toString())) {
                            laporanTanahTemp.setUsahaHarga(usahaHarga);
                        }
                    }
                    /*
                     * END
                     */

                    /*
                     * DILINTASI OLEH
                     */
                    laporanTanahTemp.setDilintasTiangElektrik(laporanTanah.getDilintasTiangElektrik());
                    laporanTanahTemp.setDilintasTiangTelefon(laporanTanah.getDilintasTiangTelefon());
                    laporanTanahTemp.setDilintasLaluanGas(laporanTanah.getDilintasLaluanGas());
                    laporanTanahTemp.setDilintasPaip(laporanTanah.getDilintasPaip());
                    laporanTanahTemp.setDilintasTaliar(laporanTanah.getDilintasTaliar());
                    laporanTanahTemp.setDilintasSungai(laporanTanah.getDilintasSungai());
                    laporanTanahTemp.setDilintasParit(laporanTanah.getDilintasParit());
                    laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                    laporanTanahTemp.setSyor(laporanTanah.getSyor());
                    /*
                     * END
                     */

                    laporanTanahTemp.setAdaBangunan(laporanTanah.getAdaBangunan());
                    laporanTanahTemp.setPerenggan(perenggan);
                    laporanTanahTemp.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());

                    laporanTanahTemp.setTanahBertumpu(laporanTanah.getTanahBertumpu());
                    laporanTanahTemp.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
                    KodUOM ku = new KodUOM();
                    ku = disLaporanTanahService.getKodUOMDAO().findById(usahaLuasUom);
                    if (ku != null) {
                        laporanTanahTemp.setUsahaLuasUom(ku);
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        laporanTanahTemp.setTarikhMulaUsaha(laporanTanah.getTarikhMulaUsaha());
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                        laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                    }

                    disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
                }
            }
        } else {
            if (hakmilikPermohonanSave != null) {
                laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hakmilikPermohonanSave.getId())}, 0);
                hakmilikPermohonanSave.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonanSave, "update", pguna));
            } else {
                hakmilikPermohonanSave = new HakmilikPermohonan();
                hakmilikPermohonanSave.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonanSave, "new", pguna));
                hakmilikPermohonanSave.setPermohonan(permohonan);
                if (!StringUtils.isEmpty(idHakmilik)) {
                    hakmilikPermohonanSave.setHakmilik(disLaporanTanahService.getHakmilikDAO().findById(idHakmilik));
                }
            }
            /*
             * MOHON_HAKMILIK
             */
            if (kodNegeri.equals("05")) {
                String hakmilikJarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                if (!StringUtils.isEmpty(hakmilikJarak)) {
                    hakmilikPermohonanSave.setJarak(hakmilikJarak);
                } else if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                    hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                }
                String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                if (!StringUtils.isEmpty(unitJarak)) {
                    hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                    if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarakDari())) {
                        hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getJarakDariKediaman() != null) {
                        hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                    }
                }
                String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                    hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                }
                disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
            } else if (kodNegeri.equals("04")) {
                if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    if (!StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                        hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                    }
                    String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                    if (!StringUtils.isEmpty(unitJarak)) {
                        hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                    }
                    hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                    hakmilikPermohonanSave.setJarakDariNama(hakmilikPermohonan.getJarakDariNama());
                    hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                    String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                        hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                    }
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                }
            }
            if (laporanTanahTemp != null) {
                laporanTanahTemp.setInfoAudit(disLaporanTanahService.findAudit(laporanTanahTemp, "update", pguna));
                if (!StringUtils.isEmpty(kecerunanT)) {
                    laporanTanahTemp.setKecerunanTanah(disLaporanTanahService.getKodKecerunanTanahDAO().findById(kecerunanT));
                    if (kecerunanT.equals("TG")) {
                        laporanTanahTemp.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                        laporanTanahTemp.setKecerunanBukit(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("CR")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("RD")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("BK")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("PY")) {
                        laporanTanahTemp.setParasAir(laporanTanah.getParasAir());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setKecerunanBukit(null);
                    } else if (kecerunanT.equals("LL")) {
                        laporanTanahTemp.setStrukturTambahanKedudukan(laporanTanah.getStrukturTambahanKedudukan());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setKecerunanBukit(null);
                    }
                }

                if (!StringUtils.isEmpty(klasifikasiT)) {
                    laporanTanahTemp.setStrukturTanah(disLaporanTanahService.getKodStrukturTanahDAO().findById(klasifikasiT));
                }

                laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
                laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                if (!StringUtils.isEmpty(keadaanTanah1)) {
                    laporanTanahTemp.setKodKeadaanTanah(disLaporanTanahService.getKodKeadaanTanahDAO().findById(keadaanTanah1));
                    // Saving into MOHON LAPOR KAND
                    if (keadaanTanah1.equals("LL")) {
                        permohonanLaporanKandungan = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanahTemp.getIdLaporan());
                        if (permohonanLaporanKandungan != null) {
                            permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "update", pguna));
                        } else {
                            permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                            permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "new", pguna));
                        }
                        permohonanLaporanKandungan.setLaporanTanah(laporanTanahTemp); //Afham - ubah skit untuk keadaan tanah
                        permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                        permohonanLaporanKandungan.setKand(keadaankand);
                        disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                    }
                }
                /*
                 * TANAH DIUSAHAKAN
                 */
                if (laporanTanah.getUsaha() != null) {
                    if (Character.isDefined(laporanTanah.getUsaha())) {
                        laporanTanahTemp.setUsaha(laporanTanah.getUsaha());
                    }
                }
                if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha().equals('Y')) {
                    laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                    if (laporanTanah.getTarikhMulaUsaha2() != null) {
                        laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
                    }
                    if (mercuTanda == null) {
                        laporanTanahTemp.setMercuTanda(laporanTanah.getMercuTanda());
                        LOG.info("DAH SIMPAN MERCU TANDA :-)");
                    }
                    laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());

                    if (usahaLuas != null && !StringUtils.isEmpty(usahaLuas.toString())) {
                        laporanTanahTemp.setUsahaLuas(usahaLuas);
                    }

                    if (usahaBilanganPokok != null) {
                        laporanTanahTemp.setUsahaBilanganPokok(usahaBilanganPokok);
                    }

                    if (usahaHarga != null && !StringUtils.isEmpty(usahaHarga.toString())) {
                        laporanTanahTemp.setUsahaHarga(usahaHarga);
                    }
                }
                /*
                 * END
                 */

                /*
                 * DILINTASI OLEH
                 */
                laporanTanahTemp.setDilintasTiangElektrik(laporanTanah.getDilintasTiangElektrik());
                laporanTanahTemp.setDilintasTiangTelefon(laporanTanah.getDilintasTiangTelefon());
                laporanTanahTemp.setDilintasLaluanGas(laporanTanah.getDilintasLaluanGas());
                laporanTanahTemp.setDilintasPaip(laporanTanah.getDilintasPaip());
                laporanTanahTemp.setDilintasTaliar(laporanTanah.getDilintasTaliar());
                laporanTanahTemp.setDilintasSungai(laporanTanah.getDilintasSungai());
                laporanTanahTemp.setDilintasParit(laporanTanah.getDilintasParit());
                /*
                 * END
                 */

                laporanTanahTemp.setAdaBangunan(laporanTanah.getAdaBangunan());
                laporanTanahTemp.setPerenggan(laporanTanah.getPerenggan());
                laporanTanahTemp.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());

                laporanTanahTemp.setTanahBertumpu(laporanTanah.getTanahBertumpu());
                laporanTanahTemp.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
                KodUOM ku = new KodUOM();
                ku = disLaporanTanahService.getKodUOMDAO().findById(usahaLuasUom);
                if (ku != null) {
                    laporanTanahTemp.setUsahaLuasUom(ku);
                }
                if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    laporanTanahTemp.setTarikhMulaUsaha(laporanTanah.getTarikhMulaUsaha());
                } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                } else if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                    laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                }

                disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
//        return new JSP(DisPermohonanPage.getLT_KTANAH_PAGE()).addParameter("tab", "true");
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahKeadaanTanahNew.jsp").addParameter("popup", "true");

    }

//    public Resolution simpanImejLaporanNew() throws IOException {
//
//
//
//        String fileName = fileToBeUpload.getFileName();
//        String contentType = fileToBeUpload.getContentType();
//        LOG.debug("###fileName :" + fileName + " contentType :" + contentType);
//        LOG.debug("content type = " + contentType);
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        File imageFile = new File(fileName);
//        File compressedImageFile = new File(contentType);
//
//        InputStream is = new FileInputStream(imageFile);
//        OutputStream os = new FileOutputStream(compressedImageFile);
//
//        float quality = 0.5f;
//
//        // create a BufferedImage as the result of decoding the supplied InputStream
//        BufferedImage image = ImageIO.read(is);
//
//        // get all image writers for JPG format
//        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
//
//        if (!writers.hasNext()) {
//            throw new IllegalStateException("No writers found");
//        }
//
//        ImageWriter writer = (ImageWriter) writers.next();
//        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//        writer.setOutput(ios);
//
//        ImageWriteParam param = writer.getDefaultWriteParam();
//
//        // compress to a given quality
//        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        param.setCompressionQuality(quality);
//
//        // appends a complete image stream containing a single image and
//        //associated stream and image metadata and thumbnails to the output
//        writer.write(null, new IIOImage(image, null, null), param);
//
//        // close all streams
//        is.close();
//        os.close();
//        ios.close();
//        writer.dispose();
//
//    }
    public Resolution simpanImejLaporan() throws Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (laporanTanah != null) {
            idLaporTanah = (String) getContext().getRequest().getSession().getAttribute("idLaporTanah");
        }
        String forwardJSP = new String();
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));

            String dokumenPath = disLaporanTanahService.getConf().getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null) {
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew.jsp").addParameter("popup", "true");
            }

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".JPG") || fileToBeUpload.getFileName().endsWith(".JpG")
                    || fileToBeUpload.getFileName().endsWith(".jPG") || fileToBeUpload.getFileName().endsWith(".JpG") || fileToBeUpload.getFileName().endsWith(".JPg")
                    || fileToBeUpload.getFileName().endsWith(".jPg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".Bmp")
                    || fileToBeUpload.getFileName().endsWith(".bMp") || fileToBeUpload.getFileName().endsWith(".bmP") || fileToBeUpload.getFileName().endsWith(".BmP")
                    || fileToBeUpload.getFileName().endsWith(".BMp") || fileToBeUpload.getFileName().endsWith(".bMP") || fileToBeUpload.getFileName().endsWith(".PNg")
                    || fileToBeUpload.getFileName().endsWith(".PnG") || fileToBeUpload.getFileName().endsWith(".Png") || fileToBeUpload.getFileName().endsWith(".pNg")
                    || fileToBeUpload.getFileName().endsWith(".pnG") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew.jsp").addParameter("popup", "true");
            }
            if ((catatan != null) && (fileToBeUpload != null)) {
                ImejLaporan imejLaporan = new ImejLaporan();
                imejLaporan.setCawangan(permohonan.getCawangan());
                imejLaporan.setInfoAudit(disLaporanTanahService.findAudit(imejLaporan, "new", peng));
                Dokumen doc = disLaporanTanahService.getCommonService().saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), imejLaporan.getInfoAudit(), permohonan);
                imejLaporan.setDokumen(doc);
                imejLaporan.setPandanganImej(pandanganImej);
                imejLaporan.setCatatan(catatan);
                if (pandanganImej == 'U' || pandanganImej == 'T' || pandanganImej == 'S' || pandanganImej == 'B') {
                    String idlaporTnhSmpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
                    disLaporanTanahSempadan = new DisLaporanTanahSempadan();
                    disLaporanTanahSempadan.setLaporanTanahSempadan(new LaporanTanahSempadan());
                    disLaporanTanahSempadan.setLaporanTanahSempadan((LaporanTanahSempadan) disLaporanTanahService.findObject(disLaporanTanahSempadan.getLaporanTanahSempadan(), new String[]{idlaporTnhSmpdn}, 1));
                    imejLaporan.setLaporanTanahSempadan(disLaporanTanahSempadan.getLaporanTanahSempadan());
                }

                if (laporanTanah != null) {
                    imejLaporan.setLaporanTanah(laporanTanah);
                    if (laporanTanah.getHakmilikPermohonan() != null) {
                        if (laporanTanah.getHakmilikPermohonan().getHakmilik() != null) {
                            imejLaporan.setHakmilik(laporanTanah.getHakmilikPermohonan().getHakmilik());
                        }
                    }
                }

                disLaporanTanahService.getLaporanTanahService().simpanHakmilikImej(imejLaporan);
//                addSimpleMessage("Muat naik fail berjaya.");

//                InfoAudit ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(peng);

                if (fileToBeUpload != null) {
                    try {
                        String fileName = fileToBeUpload.getFileName();
                        String contentType = fileToBeUpload.getContentType();
                        LOG.debug("###fileName :" + fileName + " contentType :" + contentType);
                        LOG.debug("content type = " + contentType);
                        permohonan = permohonanDAO.findById(idPermohonan);

                        LOG.info("simpanMuatNaik::finish");
                        addSimpleMessage("Muat naik fail berjaya.");
                    } catch (Exception ex) {
                        Logger.getLogger(LaporanTanah4ActionBean.class).error(ex);
                    }
                } else {
                    addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
                }

            }
            catatan = new String();
            idLaporTanah = String.valueOf(laporanTanah.getIdLaporan());
//            forwardJSP = refreshData("imgPopup");
        }
        getContext().getRequest().setAttribute("a", Boolean.FALSE);
        if (pandanganImej == 'U' || pandanganImej == 'T' || pandanganImej == 'S' || pandanganImej == 'B') {
            return lotSempadan();
        } else {
//             return new RedirectResolution(laporantanahNewActionBean.class, "perihalTanahUpload").addParameter("popup", "true").addParameter(idMohonHakmilik, idMohonHakmilik);
//            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahPerihalNew.jsp").addParameter("popup", "true");
            return perihalTanahUpload(idMohonHakmilik);
        }

    }

    public Resolution perihalTanahUpload(String idMohonHakmilik) {

//        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahService().findLaporanTanahByIdMohonIdMH(hakmilikPermohonan.getPermohonan().getIdPermohonan(), hakmilikPermohonan.getId());
        PermohonanLaporanPelan mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
//        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (hakmilikPermohonan.getKodMilik() != null) {
            kodP = hakmilikPermohonan.getKodMilik().getKod();
        }
        if (hakmilikPermohonan.getKodKawasanParlimen() != null) {
            kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
        }
        if (hakmilikPermohonan.getKodDUN() != null) {
            kodD = hakmilikPermohonan.getKodDUN().getKod();
        }
        if (hakmilikPermohonan.getLokasi() != null) {
            lokasi = hakmilikPermohonan.getLokasi();
        }
        if (!permohonan.getKodUrusan().getKod().equals("NT6A") || !permohonan.getKodUrusan().getKod().equals("sek4")) {
            if (mohonLaporPelan != null) {
                if (mohonLaporPelan.getKodTanah() != null) {
                    kodT = mohonLaporPelan.getKodTanah().getKod();
                }
            }
        }

        if (laporanTanah.getSebabTidakBolehBerimilik() != null) {
            bolehBerimilik = laporanTanah.getBolehBerimilik();
        }
        if (laporanTanah.getBolehBerimilik() != null) {
            sebabBoleh = laporanTanah.getSebabTidakBolehBerimilik();
        }
        if (laporanTanah.getSebabTidakBolehBerimilik() != null) {
            sebabBoleh = laporanTanah.getSebabTidakBolehBerimilik();
        }

        permohonan = hakmilikPermohonan.getPermohonan();

        hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
        hakmilikImejLaporanListPanorama = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdPanorama(laporanTanah.getIdLaporan());
        hakmilikImejLaporanListLampiranD = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdLampiranD(laporanTanah.getIdLaporan());
        hakmilikImejLaporanListLampiranG = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdLampiranG(laporanTanah.getIdLaporan());
        rehydrate();

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahPerihalNew.jsp").addParameter("popup", "true");
    }

    public Resolution lotSempadan() {
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah1 = pelupusanService.findLaporanTanahByIdPermohonanAndIdMH(idPermohonan, hakmilikPermohonan.getId());
        laporanTanahSempadanList = pelupusanService.findLaporTanahSmpdnByIdLapor(laporanTanah1.getIdLaporan());

        disLaporanTanahSempadan = new DisLaporanTanahSempadan();
        disLaporanTanahSempadan.setListLaporTanahSpdnU(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnB(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnS(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnT(new ArrayList());

        List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        //listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
        if (laporanTanah.getPermohonan() == null) {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "U"); // UTARA
        } else {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "U"); // UTARA
        }
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah1, 'U', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnU().add(ls);
        }
        uSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        //listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
        if (laporanTanah.getPermohonan() == null) {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "S"); // SELATAN
        } else {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "S"); // SELATAN
        }

        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah1, 'S', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
        }
        sSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        //listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
        if (laporanTanah.getPermohonan() == null) {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "T"); // TIMUR
        } else {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "T"); // TIMUR
        }

        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah1, 'T', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
        }
        tSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        //listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
        if (laporanTanah.getPermohonan() == null) {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "B"); // BARAT
        } else {
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah1.getIdLaporan(), "B"); // BARAT
        }

        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah1, 'B', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
        }
        bSize = listLaporTanahSpdnTemp.size();
        getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahLotSmpdnNew.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopUpLatarBelakangTanah() {
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        }
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanPohon = new PermohonanLaporanPohon();
        getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahAddLatarBelakangNew.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopUp() {
//        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
//        idLaporTanahSpdn = getContext().getRequest().getParameter("noPtSementara");
        String idLaporTanahSpdn2 = getContext().getRequest().getParameter("idKandungan");
        idLaporTanahSpdn = idLaporTanahSpdn2;
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (idLaporTanahSpdn != null) {
            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn2));
        }

//        hakmilikPermohonan = hakmilikPermohonanDAO.findById(laporanTanah.getHakmilikPermohonan().getId());
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
//        lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn));
        if (getIdKandungan() != null) {
            disLaporanTanahSempadan = new DisLaporanTanahSempadan();
            disLaporanTanahSempadan.setLaporanTanahSempadan(new LaporanTanahSempadan());
            disLaporanTanahSempadan.setLaporanTanahSempadan((LaporanTanahSempadan) disLaporanTanahService.findObject(disLaporanTanahSempadan.getLaporanTanahSempadan(), new String[]{getIdKandungan()}, 1));

            if (disLaporanTanahSempadan.getLaporanTanahSempadan() != null) {
                disLaporanTanahSempadan.setJenisSmpdn(disLaporanTanahSempadan.getJenisSmpdn());
                if (disLaporanTanahSempadan.getLaporanTanahSempadan().getHakmilik() != null) {
                    disLaporanTanahSempadan.setIdHakmilikSmpdn(disLaporanTanahSempadan.getLaporanTanahSempadan().getHakmilik().getIdHakmilik());
                    disLaporanTanahSempadan.setHakmilik_ref(disLaporanTanahSempadan.getLaporanTanahSempadan().getHakmilik().getIdHakmilik());
                }
                disLaporanTanahSempadan.setJenisSmpdn(disLaporanTanahSempadan.getLaporanTanahSempadan().getJenisSempadan());
                disLaporanTanahSempadan.setKegunaanSmpdn(disLaporanTanahSempadan.getLaporanTanahSempadan().getGuna());
                disLaporanTanahSempadan.setKeadaanTanah(disLaporanTanahSempadan.getLaporanTanahSempadan().getKeadaanTanah());
                disLaporanTanahSempadan.setCatatan(disLaporanTanahSempadan.getLaporanTanahSempadan().getCatatan());
                disLaporanTanahSempadan.setMilikKerajaanSmpdn(disLaporanTanahSempadan.getLaporanTanahSempadan().getMilikKerajaan());
                disLaporanTanahSempadan.setStatusSempadan("U"); // U for update   
                if (disLaporanTanahSempadan.getLaporanTanahSempadan().getJarak() != null) {
                    disLaporanTanahSempadan.setJarakDariTanah(disLaporanTanahSempadan.getLaporanTanahSempadan().getJarak().toString());
                    if (disLaporanTanahSempadan.getLaporanTanahSempadan().getJarakUom() != null) {
                        disLaporanTanahSempadan.setJarakDariTanahUOM(disLaporanTanahSempadan.getLaporanTanahSempadan().getJarakUom().getKod());
                    }
                }
                disLaporanTanahSempadan.setNoLot(disLaporanTanahSempadan.getLaporanTanahSempadan().getNoLot());
                if (disLaporanTanahSempadan.getLaporanTanahSempadan().getKodLot() != null) {
                    disLaporanTanahSempadan.setKodLot(disLaporanTanahSempadan.getLaporanTanahSempadan().getKodLot().getKod());
                }

                KodLot kodLotUOM = new KodLot();
                kodLotUOM = disLaporanTanahSempadan.getLaporanTanahSempadan().getKodLot();
                if (kodLotUOM != null) {
                    disLaporanTanahSempadan.setKodLotUOM((KodLot) disLaporanTanahService.findObject(kodLotUOM, new String[]{kodLotUOM.getKod()}, 0));
                }
            }
        } else {
            disLaporanTanahSempadan = new DisLaporanTanahSempadan();
            disLaporanTanahSempadan.setLaporanTanahSempadan(new LaporanTanahSempadan());
            disLaporanTanahSempadan.setJenisSmpdn(new String());
            disLaporanTanahSempadan.setIdHakmilikSmpdn(new String());
            disLaporanTanahSempadan.setKegunaanSmpdn(new String());
            disLaporanTanahSempadan.setKeadaanTanah(new String());
            disLaporanTanahSempadan.setCatatan(new String());
            disLaporanTanahSempadan.setMilikKerajaanSmpdn(new String());
            disLaporanTanahSempadan.setStatusSempadan(new String());
            disLaporanTanahSempadan.setJarakDariTanah(new String());
            disLaporanTanahSempadan.setJarakDariTanahUOM(new String());
            disLaporanTanahSempadan.setNoLot(new String());
            disLaporanTanahSempadan.setKodLotUOM(new KodLot());
        }

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahAddEditLotSmpdnNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKandunganSempadan() throws ParseException {

        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        String idHakmilikSempadan = new String();
        if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && disLaporanTanahSempadan.getStatusSempadan().equals("U")) {
            idHakmilikSempadan = disLaporanTanahSempadan.getIdHakmilikSmpdn();
        } else {
            idHakmilikSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        }
        String kegunaanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.kegunaan");
        String keadaanTanahSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.keadaanTanah");
        String catatanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.catatan");
        String milikKerajaanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.milikKerajaan");
        String jarakDariTanah = getContext().getRequest().getParameter("disLaporanTanahSempadan.jarakDariTanah");
        String jarakDariTanahUOM = getContext().getRequest().getParameter("disLaporanTanahSempadan.jarakDariTanahUOM");
        String kodLot = getContext().getRequest().getParameter("disLaporanTanahSempadan.kodLot");
        String noLot = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
//        String idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        if (!StringUtils.isBlank(typeIndex));
        numType = typeIndex.equals("U") ? 1 : typeIndex.equals("B") ? 2 : typeIndex.equals("T") ? 3 : typeIndex.equals("S") ? 4 : 0;
        LOG.info("idLaporTanahSpdn = " + idLaporTanahSpdn);
//String idMh = idMohonHakmilik;
//String noLot
        String[] data = {idHakmilikSempadan,
            kegunaanSempadan,
            keadaanTanahSempadan,
            catatanSempadan,
            milikKerajaanSempadan,
            jarakDariTanah,
            jarakDariTanahUOM,
            idLaporTanah,
            idMohonHakmilik,
            kodLot,
            idLaporTanahSpdn,
            noLot};

        /*
         * LEGEND 
         * 1 - UTARA
         * 2 - BARAT
         * 3 - TIMUR
         * 4 - SELATAN
         * 0 - NULL
         */
        switch (numType) {
            case 1:
                updateKandunganU(
                        idHakmilikSempadan,
                        kegunaanSempadan,
                        keadaanTanahSempadan,
                        catatanSempadan,
                        milikKerajaanSempadan,
                        jarakDariTanah,
                        jarakDariTanahUOM,
                        idLaporTanah,
                        idMohonHakmilik,
                        kodLot,
                        idLaporTanahSpdn,
                        noLot);
                break;
            case 2:
                updateKandunganB(
                        idHakmilikSempadan,
                        kegunaanSempadan,
                        keadaanTanahSempadan,
                        catatanSempadan,
                        milikKerajaanSempadan,
                        jarakDariTanah,
                        jarakDariTanahUOM,
                        idLaporTanah,
                        idMohonHakmilik,
                        kodLot,
                        idLaporTanahSpdn,
                        noLot);
                break;
            case 3:
                updateKandunganT(
                        idHakmilikSempadan,
                        kegunaanSempadan,
                        keadaanTanahSempadan,
                        catatanSempadan,
                        milikKerajaanSempadan,
                        jarakDariTanah,
                        jarakDariTanahUOM,
                        idLaporTanah,
                        idMohonHakmilik,
                        kodLot,
                        idLaporTanahSpdn,
                        noLot);
                break;
            case 4:
                updateKandunganS(
                        idHakmilikSempadan,
                        kegunaanSempadan,
                        keadaanTanahSempadan,
                        catatanSempadan,
                        milikKerajaanSempadan,
                        jarakDariTanah,
                        jarakDariTanahUOM,
                        idLaporTanah,
                        idMohonHakmilik,
                        kodLot,
                        idLaporTanahSpdn,
                        noLot);
                break;
        }
        rehydrate();

        return lotSempadan();
    }

    public void updateKandunganS(
            String idHakmilikSempadan,
            String kegunaanSempadan,
            String keadaanTanahSempadan,
            String catatanSempadan,
            String milikKerajaanSempadan,
            String jarakDariTanah,
            String jarakDariTanahUOM,
            String idLaporTanah,
            String idMohonHakmilik,
            String kodLot1,
            String idLaporTanahSpdn,
            String noLot) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        String idHm = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        Hakmilik hml = disLaporanTanahService.getHakmilikDAO().findById(idHm);
        String noL = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
        String jenisTanah = getContext().getRequest().getParameter("noLotChecked");
        String jenisTanah2 = getContext().getRequest().getParameter("lot");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        String jnsSmpdn = "S";

        Hakmilik hakmilikSmpdn = null;
//        if (idHakmilikSempadan != null) {
        if (StringUtils.isNotEmpty(idHakmilikSempadan)) {  //Cater by id hakmilik
            hakmilikSmpdn = new Hakmilik();
            hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(idHakmilikSempadan);
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//                for (HakmilikPermohonan hp : hpList) {
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hpList.get(0).getId())}, 0);
            }
            if (lt != null) {

                KodUOM kodUOM = new KodUOM();
//                    KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = pelupusanService.findLaporTanahSmpdnByIdLaporNNoLotOnly(lt.getIdLaporan(), noLot, jnsSmpdn);
                        if (lts == null) {
                            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn));
                        }
                    }
                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }
//                }
        } else {
            idHakmilik = ctx.getRequest().getParameter("idHakmilik");
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
            if (hakmilikPermohonan == null) {
//                  hakmilikPermohonalllln = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                List<HakmilikPermohonan> listHP = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                hakmilikPermohonan = listHP.get(0);
            }

            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
                KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNNoLot(lt.getIdLaporan(), noLot);
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();

                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());

                        lts.setInfoAudit(info);
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }

        }
//        }
    }

    public void updateKandunganB(
            String idHakmilikSempadan,
            String kegunaanSempadan,
            String keadaanTanahSempadan,
            String catatanSempadan,
            String milikKerajaanSempadan,
            String jarakDariTanah,
            String jarakDariTanahUOM,
            String idLaporTanah,
            String idMohonHakmilik,
            String kodLot1,
            String idLaporTanahSpdn,
            String noLot) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        String idHm = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        Hakmilik hml = disLaporanTanahService.getHakmilikDAO().findById(idHm);
        String noL = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
        String jenisTanah = getContext().getRequest().getParameter("noLotChecked");
        String jenisTanah2 = getContext().getRequest().getParameter("lot");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        String jnsSmpdn = "B";

        Hakmilik hakmilikSmpdn = null;
//        if (idHakmilikSempadan != null) {
        if (StringUtils.isNotEmpty(idHakmilikSempadan)) {  //Cater by id hakmilik
            hakmilikSmpdn = new Hakmilik();
            hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(idHakmilikSempadan);
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//                for (HakmilikPermohonan hp : hpList) {
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hpList.get(0).getId())}, 0);
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
//                    KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = pelupusanService.findLaporTanahSmpdnByIdLaporNNoLotOnly(lt.getIdLaporan(), noLot, jnsSmpdn);
                        if (lts == null) {
                            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn));
                        }
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }
//                }
        } else {
            idHakmilik = ctx.getRequest().getParameter("idHakmilik");
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
            if (hakmilikPermohonan == null) {
//                  hakmilikPermohonalllln = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                List<HakmilikPermohonan> listHP = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                hakmilikPermohonan = listHP.get(0);
            }

            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
                KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNNoLot(lt.getIdLaporan(), noLot);
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();

                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());

                        lts.setInfoAudit(info);
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }

        }
//        }
    }

    public void updateKandunganT(
            String idHakmilikSempadan,
            String kegunaanSempadan,
            String keadaanTanahSempadan,
            String catatanSempadan,
            String milikKerajaanSempadan,
            String jarakDariTanah,
            String jarakDariTanahUOM,
            String idLaporTanah,
            String idMohonHakmilik,
            String kodLot1,
            String idLaporTanahSpdn,
            String noLot) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        String idHm = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        Hakmilik hml = disLaporanTanahService.getHakmilikDAO().findById(idHm);
        String noL = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
        String jenisTanah = getContext().getRequest().getParameter("noLotChecked");
        String jenisTanah2 = getContext().getRequest().getParameter("lot");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        String jnsSmpdn = "T";

        Hakmilik hakmilikSmpdn = null;
//        if (idHakmilikSempadan != null) {
        if (StringUtils.isNotEmpty(idHakmilikSempadan)) {  //Cater by id hakmilik
            hakmilikSmpdn = new Hakmilik();
            hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(idHakmilikSempadan);
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//                for (HakmilikPermohonan hp : hpList) {
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hpList.get(0).getId())}, 0);
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
//                    KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = pelupusanService.findLaporTanahSmpdnByIdLaporNNoLotOnly(lt.getIdLaporan(), noLot, jnsSmpdn);
                        if (lts == null) {
                            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn));
                        }
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }
//                }
        } else {
            idHakmilik = ctx.getRequest().getParameter("idHakmilik");
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
            if (hakmilikPermohonan == null) {
//                  hakmilikPermohonalllln = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                List<HakmilikPermohonan> listHP = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                hakmilikPermohonan = listHP.get(0);
            }

            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
                KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNNoLot(lt.getIdLaporan(), noLot);
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();

                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());

                        lts.setInfoAudit(info);
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }

        }
//        }
    }

    public void updateKandunganU(
            String idHakmilikSempadan,
            String kegunaanSempadan,
            String keadaanTanahSempadan,
            String catatanSempadan,
            String milikKerajaanSempadan,
            String jarakDariTanah,
            String jarakDariTanahUOM,
            String idLaporTanah,
            String idMohonHakmilik,
            String kodLot1,
            String simpanKandunganSempadan,
            String noLot) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        String idHm = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        Hakmilik hml = disLaporanTanahService.getHakmilikDAO().findById(idHm);
        String noL = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
        String jenisTanah = getContext().getRequest().getParameter("noLotChecked");
        String jenisTanah2 = getContext().getRequest().getParameter("lot");
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        String jnsSmpdn = "U";

        Hakmilik hakmilikSmpdn = null;
//        if (idHakmilikSempadan != null) {
        if (StringUtils.isNotEmpty(idHakmilikSempadan)) {  //Cater by id hakmilik
            hakmilikSmpdn = new Hakmilik();
            hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(idHakmilikSempadan);
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//                for (HakmilikPermohonan hp : hpList) {
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hpList.get(0).getId())}, 0);
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
//                    KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = pelupusanService.findLaporTanahSmpdnByIdLaporNNoLotOnly(lt.getIdLaporan(), noLot, jnsSmpdn);
                        if (lts == null) {
                            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idLaporTanahSpdn));
                        }
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
//                                kodLot = new KodLot();
                            KodLot kodLot = kodLotDAO.findById(kodLot1);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }
//                }
        } else {
            idHakmilik = ctx.getRequest().getParameter("idHakmilik");
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
            if (hakmilikPermohonan == null) {
//                  hakmilikPermohonalllln = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                List<HakmilikPermohonan> listHP = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                hakmilikPermohonan = listHP.get(0);
            }

            lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
            }
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
                KodLot kodLot = new KodLot();

                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    if (StringUtils.isNotEmpty(idHakmilikSempadan)) {
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    } else if (StringUtils.isNotEmpty(noLot)) { //Cater by no lot
                        lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNAndSepadan(lt.getIdLaporan(), jnsSmpdn);
                    }

                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);

                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                    }
                } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), idHakmilikSempadan);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                        lts = new LaporanTanahSempadan();
                        lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                    } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                        lts = new LaporanTanahSempadan();

                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());

                        lts.setInfoAudit(info);
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn);
                        lts.setGuna(kegunaanSempadan);
                        lts.setKeadaanTanah(keadaanTanahSempadan);
                        lts.setCatatan(catatanSempadan);
                        lts.setMilikKerajaan(milikKerajaanSempadan);
                        if (!jarakDariTanah.isEmpty()) {
                            Double jarak = new Double(0.0);
                            jarak = Double.parseDouble(jarakDariTanah);
                            lts.setJarak(BigDecimal.valueOf(jarak));
                        }
                        if (!jarakDariTanahUOM.isEmpty()) {
                            kodUOM = new KodUOM();
                            kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
                            lts.setJarakUom(kodUOM);
                        }
                        if (!kodLot1.isEmpty()) {
                            kodLot = new KodLot();
                            kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLot1}, 0);
                            lts.setKodLot(kodLot);
                        }
                        lts.setNoLot(noLot);
                        lts.setJenisSempadan(jnsSmpdn);
                        disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                        addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                    }
                } else {
                    addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }

        }
//        }
    }

    public void updateKandungan(String jnsSmpdn, String[] data) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        String idHm = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        Hakmilik hml = disLaporanTanahService.getHakmilikDAO().findById(idHm);
        String noL = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");
        String jenisTanah = getContext().getRequest().getParameter("noLotChecked");
        String jenisTanah2 = getContext().getRequest().getParameter("lot");

        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));

        Hakmilik hakmilikSmpdn = null;
        if (data.length > 0) {
            if (StringUtils.isNotEmpty(data[0])) {  //Cater by id hakmilik
                hakmilikSmpdn = new Hakmilik();
                hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(data[0]);
            }
            if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//                for (HakmilikPermohonan hp : hpList) {
                LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
                if (lt == null) {
                    lt = new LaporanTanah();
                    lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                    lt.setPermohonan(permohonan);
                    disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                    lt = new LaporanTanah();
                    lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hpList.get(0).getId())}, 0);
                }
                if (lt != null) {
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();
                    KodUOM kodUOM = new KodUOM();
                    KodLot kodLot = new KodLot();

                    if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                        if (StringUtils.isNotEmpty(data[0])) {
                            lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                        } else if (StringUtils.isNotEmpty(data[8])) { //Cater by no lot
                            lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNNoLot(lt.getIdLaporan(), data[8]);
                        }

                        if (lts != null) {

                            lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);

                            }
                            lts.setNoLot(data[10]);
                            lts.setJenisSempadan(jnsSmpdn);

                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                        }
                    } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                        List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                        if (listLTS.size() > 0) {
                            addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                            lts = new LaporanTanahSempadan();
                            lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);
                            }
                            lts.setNoLot(data[8]);
                            lts.setJenisSempadan(jnsSmpdn);
                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                        } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                            lts = new LaporanTanahSempadan();
                            lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);
                            }
                            lts.setNoLot(data[8]);
                            lts.setJenisSempadan(jnsSmpdn);
                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                        }
                    } else {
                        addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                    }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
                }
//                }
            } else {
                idHakmilik = ctx.getRequest().getParameter("idHakmilik");
                LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
                hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
                if (hakmilikPermohonan == null) {
//                  hakmilikPermohonalllln = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                    List<HakmilikPermohonan> listHP = hakmilikPermohonanService.findByIdPermohonanOnly(permohonan.getIdPermohonan());
                    hakmilikPermohonan = listHP.get(0);
                }

                lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
                if (lt == null) {
                    lt = new LaporanTanah();
                    lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                    lt.setPermohonan(permohonan);
                    disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                    lt = new LaporanTanah();
                    lt = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
                }
                if (lt != null) {
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();
                    KodUOM kodUOM = new KodUOM();
                    KodLot kodLot = new KodLot();

                    if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                        if (StringUtils.isNotEmpty(data[0])) {
                            lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                        } else if (StringUtils.isNotEmpty(data[8])) { //Cater by no lot
                            lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNNoLot(lt.getIdLaporan(), data[8]);
                        }

                        if (lts != null) {

                            lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : null);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);

                            }
                            lts.setNoLot(data[8]);
                            lts.setJenisSempadan(jnsSmpdn);

                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                        }
                    } else if ((noL == null && idHm.isEmpty() && hml == null) || (noL == null && !(idHm.isEmpty()) && hml != null) || (noL != null && idHm.isEmpty() && hml == null) || (noL != null && !(idHm.isEmpty()) && hml != null)) {

                        List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                        if (listLTS.size() > 0) {
                            addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                            lts = new LaporanTanahSempadan();
                            lts.setInfoAudit(disLaporanTanahService.findAudit(lts, "new", pengguna));
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);
                            }
                            lts.setNoLot(data[8]);
                            lts.setJenisSempadan(jnsSmpdn);
                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");

                        } else {
//                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
                            lts = new LaporanTanahSempadan();

                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());

                            lts.setInfoAudit(info);
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            if (!data[5].isEmpty()) {
                                Double jarak = new Double(0.0);
                                jarak = Double.parseDouble(data[5]);
                                lts.setJarak(BigDecimal.valueOf(jarak));
                            }
                            if (!data[6].isEmpty()) {
                                kodUOM = new KodUOM();
                                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{data[6]}, 0);
                                lts.setJarakUom(kodUOM);
                            }
                            if (!data[7].isEmpty()) {
                                kodLot = new KodLot();
                                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{data[7]}, 0);
                                lts.setKodLot(kodLot);
                            }
                            lts.setNoLot(data[10]);
                            lts.setJenisSempadan(jnsSmpdn);
                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
//                            } else {
//                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
//                            }
                        }
                    } else {
                        addSimpleError("Hakmilik Tidak Dijumpai. Sila Masukkan ID Hakmilik Yang Sah");
                    }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
                }

            }
        }
    }

//@Override
    @HandlesEvent("simpanTanah")
    public Resolution simpanTanah() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        LaporanTanah laporanTanahTemp = new LaporanTanah();
        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        hakmilikPermohonanSave = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanahTemp = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        kecerunanT = getContext().getRequest().getParameter("kecerunanT");
        String ketinggianDariJalan = getContext().getRequest().getParameter("laporanTanah.ketinggianDariJalan");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
//        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
////            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
//        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
////            noPtTemp = new NoPt();
////            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
////            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
//        } else if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
//            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
//        } else {
//            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
//        }

        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
            for (HakmilikPermohonan hp : hpList) {
                if (hakmilikPermohonanSave != null) {
//                    laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hp.getId())}, 0);
                    hakmilikPermohonanSave.setInfoAudit(info);
                } else {
                    hakmilikPermohonanSave = new HakmilikPermohonan();
                    hakmilikPermohonanSave.setInfoAudit(info);
                    hakmilikPermohonanSave.setPermohonan(permohonan);
                    if (!StringUtils.isEmpty(idHakmilik)) {
                        hakmilikPermohonanSave.setHakmilik(disLaporanTanahService.getHakmilikDAO().findById(idHakmilik));
                    }
                }
                /*
                 * MOHON_HAKMILIK
                 */
                if (kodNegeri.equals("05")) {
                    String hakmilikJarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                    if (!StringUtils.isEmpty(hakmilikJarak)) {
                        hakmilikPermohonanSave.setJarak(hakmilikJarak);
                    } else if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                        hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                    }
                    String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                    if (!StringUtils.isEmpty(unitJarak)) {
                        hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                        if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarakDari())) {
                            hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                        }
                        if (hakmilikPermohonan != null && hakmilikPermohonan.getJarakDariKediaman() != null) {
                            hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                        }
                    }
                    String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                        hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                    }
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                } else if (kodNegeri.equals("04")) {
                    if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                        if (!StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                            hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                        }
                        String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                        if (!StringUtils.isEmpty(unitJarak)) {
                            hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                        }
                        hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                        hakmilikPermohonanSave.setJarakDariNama(hakmilikPermohonan.getJarakDariNama());
                        hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                        String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                        if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                            hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                        }
                        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                    }
                }
                if (laporanTanahTemp != null) {
                    laporanTanahTemp.setInfoAudit(info);
                    if (!StringUtils.isEmpty(kecerunanT)) {
                        laporanTanahTemp.setKecerunanTanah(disLaporanTanahService.getKodKecerunanTanahDAO().findById(kecerunanT));
                        if (kecerunanT.equals("TG")) {
                            laporanTanahTemp.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                            laporanTanahTemp.setKecerunanBukit(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("CR")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("RD")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("BK")) {
                            laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setParasAir(null);
                        } else if (kecerunanT.equals("PY")) {
                            laporanTanahTemp.setParasAir(laporanTanah.getParasAir());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setKecerunanBukit(null);
                        } else if (kecerunanT.equals("LL")) {
                            laporanTanahTemp.setStrukturTambahanKedudukan(laporanTanah.getStrukturTambahanKedudukan());
                            laporanTanahTemp.setKetinggianDariJalan(null);
                            laporanTanahTemp.setKecerunanBukit(null);
                        }
                    }

                    if (!StringUtils.isEmpty(klasifikasiT)) {
                        laporanTanahTemp.setStrukturTanah(disLaporanTanahService.getKodStrukturTanahDAO().findById(klasifikasiT));
                    }

                    laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
                    laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                    if (!StringUtils.isEmpty(keadaanTanah)) {
                        laporanTanahTemp.setKodKeadaanTanah(disLaporanTanahService.getKodKeadaanTanahDAO().findById(keadaanTanah));
                        // Saving into MOHON LAPOR KAND
                        if (keadaanTanah.equals("LL")) {
                            permohonanLaporanKandungan = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanahTemp.getIdLaporan());
                            if (permohonanLaporanKandungan != null) {
                                permohonanLaporanKandungan.setInfoAudit(info);
                            } else {
                                permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                                permohonanLaporanKandungan.setInfoAudit(info);
                            }
                            permohonanLaporanKandungan.setLaporanTanah(laporanTanahTemp); //Afham - ubah skit untuk keadaan tanah
                            permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                            permohonanLaporanKandungan.setKand(keadaankand);
                            disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                        }
                    }
                    /*
                     * TANAH DIUSAHAKAN
                     */
                    if (Character.isDefined(laporanTanah.getUsaha())) {
                        laporanTanahTemp.setUsaha(laporanTanah.getUsaha());
                    }
                    if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha().equals('Y')) {
                        laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                        if (laporanTanah.getTarikhMulaUsaha2() != null) {
                            laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
                        }
                        if (mercuTanda == null) {
                            laporanTanahTemp.setMercuTanda(laporanTanah.getMercuTanda());
                            LOG.info("DAH SIMPAN MERCU TANDA :-)");
                        }
                        laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());

                        if (usahaLuas != null && !StringUtils.isEmpty(usahaLuas.toString())) {
                            laporanTanahTemp.setUsahaLuas(usahaLuas);
                        }

                        if (usahaBilanganPokok != null) {
                            laporanTanahTemp.setUsahaBilanganPokok(usahaBilanganPokok);
                        }

                        if (usahaHarga != null && !StringUtils.isEmpty(usahaHarga.toString())) {
                            laporanTanahTemp.setUsahaHarga(usahaHarga);
                        }
                    }
                    /*
                     * END
                     */

                    /*
                     * DILINTASI OLEH
                     */
                    laporanTanahTemp.setDilintasTiangElektrik(laporanTanah.getDilintasTiangElektrik());
                    laporanTanahTemp.setDilintasTiangTelefon(laporanTanah.getDilintasTiangTelefon());
                    laporanTanahTemp.setDilintasLaluanGas(laporanTanah.getDilintasLaluanGas());
                    laporanTanahTemp.setDilintasPaip(laporanTanah.getDilintasPaip());
                    laporanTanahTemp.setDilintasTaliar(laporanTanah.getDilintasTaliar());
                    laporanTanahTemp.setDilintasSungai(laporanTanah.getDilintasSungai());
                    laporanTanahTemp.setDilintasParit(laporanTanah.getDilintasParit());
                    laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                    laporanTanahTemp.setSyor(laporanTanah.getSyor());
                    /*
                     * END
                     */

                    laporanTanahTemp.setAdaBangunan(laporanTanah.getAdaBangunan());
                    laporanTanahTemp.setPerenggan(laporanTanah.getPerenggan());
                    laporanTanahTemp.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());

                    laporanTanahTemp.setTanahBertumpu(laporanTanah.getTanahBertumpu());
                    laporanTanahTemp.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
                    KodUOM ku = new KodUOM();
                    ku = disLaporanTanahService.getKodUOMDAO().findById(usahaLuasUom);
                    if (ku != null) {
                        laporanTanahTemp.setUsahaLuasUom(ku);
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        laporanTanahTemp.setTarikhMulaUsaha(laporanTanah.getTarikhMulaUsaha());
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                        laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                    }

                    disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
                }
            }
        } else {
            if (hakmilikPermohonanSave != null) {
//                laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hakmilikPermohonanSave.getId())}, 0);
                hakmilikPermohonanSave.setInfoAudit(info);
            } else {
                hakmilikPermohonanSave = new HakmilikPermohonan();
                hakmilikPermohonanSave.setInfoAudit(info);
                hakmilikPermohonanSave.setPermohonan(permohonan);
                if (!StringUtils.isEmpty(idHakmilik)) {
                    hakmilikPermohonanSave.setHakmilik(disLaporanTanahService.getHakmilikDAO().findById(idHakmilik));
                }
            }
            /*
             * MOHON_HAKMILIK
             */
            if (kodNegeri.equals("05")) {
                String hakmilikJarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                if (!StringUtils.isEmpty(hakmilikJarak)) {
                    hakmilikPermohonanSave.setJarak(hakmilikJarak);
                } else if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                    hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                }
                String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                if (!StringUtils.isEmpty(unitJarak)) {
                    hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                    if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getJarakDari())) {
                        hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getJarakDariKediaman() != null) {
                        hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                    }
                }
                String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                    hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                }
                disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
            } else if (kodNegeri.equals("04")) {
                if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    if (!StringUtils.isEmpty(hakmilikPermohonan.getJarak())) {
                        hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
                    }
                    String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
                    if (!StringUtils.isEmpty(unitJarak)) {
                        hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
                    }
                    hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
                    hakmilikPermohonanSave.setJarakDariNama(hakmilikPermohonan.getJarakDariNama());
                    hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                    String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (!StringUtils.isEmpty(jarakDariKediamanUOM)) {
                        hakmilikPermohonanSave.setJarakDariKediamanUom(disLaporanTanahService.getKodUOMDAO().findById(jarakDariKediamanUOM));
                    }
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
                }
            }
            if (laporanTanahTemp != null) {
                laporanTanahTemp.setInfoAudit(info);
                if (!StringUtils.isEmpty(kecerunanT)) {
                    laporanTanahTemp.setKecerunanTanah(disLaporanTanahService.getKodKecerunanTanahDAO().findById(kecerunanT));
                    if (kecerunanT.equals("TG")) {
                        laporanTanahTemp.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                        laporanTanahTemp.setKecerunanBukit(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("CR")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("RD")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("BK")) {
                        laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setParasAir(null);
                    } else if (kecerunanT.equals("PY")) {
                        laporanTanahTemp.setParasAir(laporanTanah.getParasAir());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setKecerunanBukit(null);
                    } else if (kecerunanT.equals("LL")) {
                        laporanTanahTemp.setStrukturTambahanKedudukan(laporanTanah.getStrukturTambahanKedudukan());
                        laporanTanahTemp.setKetinggianDariJalan(null);
                        laporanTanahTemp.setKecerunanBukit(null);
                    }
                }

                if (!StringUtils.isEmpty(klasifikasiT)) {
                    laporanTanahTemp.setStrukturTanah(disLaporanTanahService.getKodStrukturTanahDAO().findById(klasifikasiT));
                }

                laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
                laporanTanahTemp.setUlasan(laporanTanah.getUlasan());
                if (!StringUtils.isEmpty(keadaanTanah)) {
                    laporanTanahTemp.setKodKeadaanTanah(disLaporanTanahService.getKodKeadaanTanahDAO().findById(keadaanTanah));
                    // Saving into MOHON LAPOR KAND
                    if (keadaanTanah.equals("LL")) {
                        permohonanLaporanKandungan = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanahTemp.getIdLaporan());
                        if (permohonanLaporanKandungan != null) {
                            permohonanLaporanKandungan.setInfoAudit(info);
                        } else {
                            permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                            permohonanLaporanKandungan.setInfoAudit(info);
                        }
                        permohonanLaporanKandungan.setLaporanTanah(laporanTanahTemp); //Afham - ubah skit untuk keadaan tanah
                        permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                        permohonanLaporanKandungan.setKand(keadaankand);
                        disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                    }
                }
                /*
                 * TANAH DIUSAHAKAN
                 */
                if (laporanTanah.getUsaha() != null) {
                    if (Character.isDefined(laporanTanah.getUsaha())) {
                        laporanTanahTemp.setUsaha(laporanTanah.getUsaha());
                    }
                }
                if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha().equals('Y')) {
                    laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                    if (laporanTanah.getTarikhMulaUsaha2() != null) {
                        laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
                    }
                    if (mercuTanda == null) {
                        laporanTanahTemp.setMercuTanda(laporanTanah.getMercuTanda());
                        LOG.info("DAH SIMPAN MERCU TANDA :-)");
                    }
                    laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());

                    if (usahaLuas != null && !StringUtils.isEmpty(usahaLuas.toString())) {
                        laporanTanahTemp.setUsahaLuas(usahaLuas);
                    }

                    if (usahaBilanganPokok != null) {
                        laporanTanahTemp.setUsahaBilanganPokok(usahaBilanganPokok);
                    }

                    if (usahaHarga != null && !StringUtils.isEmpty(usahaHarga.toString())) {
                        laporanTanahTemp.setUsahaHarga(usahaHarga);
                    }
                }
                /*
                 * END
                 */

                /*
                 * DILINTASI OLEH
                 */
                laporanTanahTemp.setDilintasTiangElektrik(laporanTanah.getDilintasTiangElektrik());
                laporanTanahTemp.setDilintasTiangTelefon(laporanTanah.getDilintasTiangTelefon());
                laporanTanahTemp.setDilintasLaluanGas(laporanTanah.getDilintasLaluanGas());
                laporanTanahTemp.setDilintasPaip(laporanTanah.getDilintasPaip());
                laporanTanahTemp.setDilintasTaliar(laporanTanah.getDilintasTaliar());
                laporanTanahTemp.setDilintasSungai(laporanTanah.getDilintasSungai());
                laporanTanahTemp.setDilintasParit(laporanTanah.getDilintasParit());
                /*
                 * END
                 */

                laporanTanahTemp.setAdaBangunan(laporanTanah.getAdaBangunan());
                laporanTanahTemp.setPerenggan(laporanTanah.getPerenggan());
                laporanTanahTemp.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());

                laporanTanahTemp.setTanahBertumpu(laporanTanah.getTanahBertumpu());
                laporanTanahTemp.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
                KodUOM ku = new KodUOM();
                ku = disLaporanTanahService.getKodUOMDAO().findById(usahaLuasUom);
                if (ku != null) {
                    laporanTanahTemp.setUsahaLuasUom(ku);
                }
                if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    laporanTanahTemp.setTarikhMulaUsaha(laporanTanah.getTarikhMulaUsaha());
                } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                } else if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                    laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
                }

                disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
            }
        }
//        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new RedirectResolution(laporantanahNewActionBean.class, "keadaanTanah").addParameter("popup", "true");
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahKeadaanTanahNew.jsp.jsp").addParameter("popup", "true");

    }

    public Resolution carianLatarBelakangTanah() {
//        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        if (StringUtils.isNotBlank(permohonanLaporanPohon.getJenisDipohon())) {
            if (permohonanLaporanPohon.getJenisDipohon().equals("H")) {
                List<Hakmilik> hmList = new ArrayList<Hakmilik>();
                Hakmilik hm = null;
                HakmilikPihakBerkepentingan hp = null;
                hmList = permohonanLaporanPohon.getNoRujukan() != null ? disLaporanTanahService.getPelupusanService().findHakmilikByNoLotORNoHMOrIDHM(null, null, permohonanLaporanPohon.getNoRujukan()) : null;
                if (hmList.size() > 0) {
                    hm = new Hakmilik();
                    hm = hmList.get(0);
                }
                if (hm != null) {
                    if (hm.getKegunaanTanah() != null) {
                        permohonanLaporanPohon.setKegunaan(hm.getKegunaanTanah().getNama());
                    }
                    if (hm.getSenaraiPihakBerkepentingan().size() > 0) {
                        namaPemohon = hm.getSenaraiPihakBerkepentingan().get(0).getPihak().getNama();
                    }
                    if (hm.getTarikhDaftar() != null) {
                        tarikhDaftar = sdf.format(hm.getTarikhDaftar());
                    }
                    getContext().getRequest().setAttribute("tanah", Boolean.TRUE);
                    addSimpleMessage("Carian Dijumpai");
                } else {
                    addSimpleError("No Hakmilik tidak dijumpai");
                    getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
                }
            } else if (permohonanLaporanPohon.getJenisDipohon().equals("LP") || permohonanLaporanPohon.getJenisDipohon().equals("P")) {
                Permit permit = new Permit();
                PermitItem permitItem = new PermitItem();
                permit = permohonanLaporanPohon.getNoRujukan() != null ? disLaporanTanahService.getPelupusanService().findIdPermitByNoPermit(permohonanLaporanPohon.getNoRujukan()) : null;
                if (permit != null) {
                    namaPemohon = permit.getPihak().getNama();
                    permitItem = disLaporanTanahService.getPelupusanService().findPermitItemByIdPermit(permit.getIdPermit());
                    if (permit.getInfoAudit() != null) {
                        tarikhDaftar = sdf.format(permit.getInfoAudit().getTarikhMasuk());
                    }
                    if (permitItem != null) {
                        permohonanLaporanPohon.setKegunaan(permitItem.getKodItemPermit().getNama());
                    }
                    getContext().getRequest().setAttribute("tanah", Boolean.TRUE);
                    addSimpleMessage("Carian Dijumpai");
                } else {
                    addSimpleError("No Permit / LPS tidak dijumpai");
                    getContext().getRequest().setAttribute("tanah", Boolean.FALSE);
                }
            }

        }
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahAddLatarBelakangNew.jsp").addParameter("popup", "true");
    }

    public Resolution kawasanPopupKembali() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));

//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/kawasanPopup.jsp").addParameter("popup", "true").addParameter("showForm", "false");

    }

    public Resolution kawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));

//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));
        if (senaraiLaporanKawasan.size() > 0) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahV2DalamKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahTambahKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        }

    }

    public Resolution deletelaporKwsn() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonanLaporanKawasan = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));

//        permohonanLaporanKawasanDAO.delete(permohonanLaporanKawasan);
        pelupusanService.deletePermohonanLaporanKwsn(permohonanLaporanKawasan);
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));
        addSimpleMessage("Data Telah Berjaya Dihapus");
        if (senaraiLaporanKawasan.size() > 0) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahV2DalamKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahTambahKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        }

    }

    public Resolution tambahkawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (idMohonlaporKws != null) {
            permohonanLaporanKawasan = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));
            catatanKwsn = permohonanLaporanKawasan.getCatatan();
            trhWartaKwsn = permohonanLaporanKawasan.getTarikhWarta();
            noWartaKwsn = permohonanLaporanKawasan.getNoWarta();
            noPelankawasan = permohonanLaporanKawasan.getNoPelanWarta();
            rizabKwsn = permohonanLaporanKawasan.getKodRizab().getKod();

        } else {
            senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));
        }

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahTambahKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution kiraCukai() {
        String result = "";
        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        HakmilikPermohonan hp = null;
        if (!StringUtils.isEmpty(noPtSementara)) {
            hp = new HakmilikPermohonan();
            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PCRG")) {
                hp = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
            } else {
                hp = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
            }
        }

        Permohonan p = permohonanDAO.findById(idPermohonan);

        if (hp != null) {
            result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(hp.getBandarPekanMukimBaru().getKod()), "H", new BigDecimal(1), hp, null));
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution syorPopapRehydrate(String idMohonHakmilik, String idLaporTanah) {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(hakmilikPermohonan.getPermohonan().getIdPermohonan(), stageId);
        if (fasaPermohonanList.size() > 0) {
            fasaPermohonan = fasaPermohonanList.get(0);
            if (fasaPermohonan.getKeputusan() != null) {
                ksn = fasaPermohonan.getKeputusan().getKod();
            }
            if (fasaPermohonan.getUlasan() != null) {
                sebab = fasaPermohonan.getUlasan();
            }
        }

        if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTD")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTG")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPBB")) {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);

            cagaranJalan = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
            bayarankupon = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
            isiPaduBatu = "" + permohonanBahanBatuan.getJumlahIsipadu();
            tempohDisyor = "" + permohonanBahanBatuan.getTempohDisyor();
            if (permohonanBahanBatuan.getTempohSyorUom() != null) {
                unitTempohUOM = permohonanBahanBatuan.getTempohSyorUom().getKod();
            }
            cagarJalan = "" + cagaranJalan.getAmaunTuntutan();
            kuponQty = "" + bayarankupon.getKuantiti();

            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PRMP")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPRMPNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PHLP")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PHLA")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPHLANew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                syaratNyata = hakmilikPermohonan.getSyaratNyataBaru().getSyarat();
            }
            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                syaratNyata1 = hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan();
            }
            senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
            syorPBMTNew(fasaPermohonan, hakmilikPermohonan);
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPBMTNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PLPT")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPLPTNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PLPS")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("MMMCL")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PTGSA")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPLPSNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syorNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        }
    }

    public Resolution syorPopap() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);

        idMohonHakmilik = (String) getContext().getRequest().getParameter("idMohonHakmilik");

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonan = hakmilikPermohonan.getPermohonan();
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(hakmilikPermohonan.getPermohonan().getIdPermohonan(), stageId);
        if (fasaPermohonanList.size() > 0) {
            fasaPermohonan = fasaPermohonanList.get(0);
            if (fasaPermohonan.getKeputusan() != null) {
                ksn = fasaPermohonan.getKeputusan().getKod();
            }
            if (fasaPermohonan.getUlasan() != null) {
                sebab = fasaPermohonan.getUlasan();
            }
        }

        if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTD")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBPTG")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPBB")) {
            permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);

            cagaranJalan = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");

            bayarankupon = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
            isiPaduBatu = "" + permohonanBahanBatuan.getJumlahIsipadu();
            tempohDisyor = "" + permohonanBahanBatuan.getTempohDisyor();
            if (permohonanBahanBatuan.getTempohSyorUom() != null) {
                unitTempohUOM = permohonanBahanBatuan.getTempohSyorUom().getKod();
            }
            if (cagaranJalan != null) {
                cagarJalan = "" + cagaranJalan.getAmaunTuntutan();
            }
            if (bayarankupon != null) {
                kuponQty = "" + bayarankupon.getKuantiti();
            }
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PRMP")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPRMPNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PHLP")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PHLA")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPHLANew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                syaratNyata = hakmilikPermohonan.getSyaratNyataBaru().getSyarat();
            }
            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                syaratNyata1 = hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan();
            }
            senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
            syorPBMTNew(fasaPermohonan, hakmilikPermohonan);
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPBMTNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PLPS")
                //                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("MMMCL")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PTGSA")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("NT6A")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPLPSNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PLPT")
                || hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("sek4")) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syor/syorPLPTNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/syorNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        }
    }

    public void syorPBMTNew(FasaPermohonan fasaPermohonan, HakmilikPermohonan hakmilikPermohonan) {
        if (fasaPermohonan.getKeputusan() != null) {
            ksn = fasaPermohonan.getKeputusan().getKod();
        }

        if (hakmilikPermohonan.getLuasLulusUom() != null) {
            kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
        }
        if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
            katTanahPilihan = hakmilikPermohonan.getKategoriTanahBaru().getKod();
        }
        if (hakmilikPermohonan.getHakmilik() != null) {
            kodHmlk = hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod();
        }
        if (hakmilikPermohonan.getTempohPajakan() != null) {
            tempohPajakan = hakmilikPermohonan.getTempohPajakan();
        }
        if (hakmilikPermohonan.getKeteranganKadarPremium() != null) {
            keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();
        }
        if (hakmilikPermohonan.getKeteranganCukaiBaru() != null) {
            jenishasil = hakmilikPermohonan.getKeteranganCukaiBaru();
        }
        if (hakmilikPermohonan.getAgensiUpahUkur() != null) {
            agensiUpahUkur = hakmilikPermohonan.getAgensiUpahUkur();
        }
    }

    public Resolution kemaskiniUlasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(hakmilikPermohonan.getPermohonan().getIdPermohonan(), stageId);
        permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(Long.parseLong(idLaporUlasan));
        senaraiLaporanKandungan1 = pelupusanService.findUlasanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());

        ulasanSebab = permohonanLaporanUlasan.getUlasan();

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahUlasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution deleteUlasan() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(hakmilikPermohonan.getPermohonan().getIdPermohonan(), stageId);
        permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(Long.parseLong(idLaporUlasan));
        senaraiLaporanKandungan1 = pelupusanService.findUlasanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());

//        permohonanLaporanUlasanDAO.delete(permohonanLaporanUlasan);
        disLaporanTanahService.getPelPtService().deletePermohonanLaporanUlasan(permohonanLaporanUlasan);
        addSimpleMessage("Ulasan Telah Berjaya Dipadamkan.");
        return new RedirectResolution(laporantanahNewActionBean.class, "viewOnlyLaporanTanahPPT");
    }

    public Resolution deleteNoFail() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        noFail = getContext().getRequest().getParameter("idMohonManual");
        LOG.info("NoFail" + noFail);
        if (noFail != null) {
            pm = permohonanManualDAO.findById(Long.valueOf(noFail));
            pembangunanService.deletePermohonanManualByNoFail(pm);
        } else {
            pembangunanService.findPermohonanManualByIdPermohonanIdManual(permohonan.getIdPermohonan(), noFail);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("No.Fail Telah Berjaya Dipadamkan.");
        return new RedirectResolution(laporantanahNewActionBean.class, "viewOnlyLaporanTanahPPT");
    }

    public Resolution deleteNoFailUrusan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        noFail = getContext().getRequest().getParameter("idMohonManual");
        LOG.info("NoFail" + noFail);

        List<PermohonanManual> senaraiPm = pembangunanService.findPermohonanManualByIdPermohonanAndNoFile(permohonan.getIdPermohonan(), noFail);

        for (PermohonanManual pm : senaraiPm) {
            pembangunanService.deletePermohonanManualByNoFail(pm);
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("No.Fail Telah Berjaya Dipadamkan.");
        return new RedirectResolution(laporantanahNewActionBean.class, "viewOnlyLaporanTanahPPT");
    }

    public Resolution ulasanPopap() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(hakmilikPermohonan.getPermohonan().getIdPermohonan(), stageId);
        senaraiLaporanKandungan1 = pelupusanService.findUlasanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahUlasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tambahNamaPengusahaPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        permohonanLaporanUsaha = new PermohonanLaporanUsaha();
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/laporan_tanahPengusahaPopupNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanUlasanPopapNew() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        String jenisLaporan = "Ulasan PPT";
        permohonanLaporanUlasan = new PermohonanLaporanUlasan();
        permohonanLaporanUlasan.setPermohonan(permohonan);
        permohonanLaporanUlasan.setIdLaporUlas(laporanTanah.getIdLaporan());
        permohonanLaporanUlasan.setUlasan(ulasanSebab);
        permohonanLaporanUlasan.setJenisUlasan(jenisLaporan);
        permohonanLaporanUlasan.setInfoAudit(ia);
        disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(permohonanLaporanUlasan);

        addSimpleMessage("Maklumat Banggunan Telah Berjaya disimpan");
        return syorPopapRehydrate(idMohonHakmilik, idLaporTanah);
//        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);
//        return new RedirectResolution(laporantanahNewActionBean, "syorPopap");
//        return new RedirectResolution(laporantanahNewActionBean.class, "syorPopap").addParameter("showForm", "false", idMohonHakmilik, idLaporTanah);
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanUlasanKemaskini() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiLaporanKandungan1 = pelupusanService.findUlasanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
        permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(Long.parseLong(idLaporUlasan));

        InfoAudit ia = new InfoAudit();
        ia.setTarikhKemaskini(new java.util.Date());
        ia.setDiKemaskiniOleh(pguna);
//        permohonanLaporanUlasan = new PermohonanLaporanUlasan();
//        permohonanLaporanUlasan.setPermohonan(permohonan);
//        permohonanLaporanUlasan.setIdLaporUlas(laporanTanah.getIdLaporan());
        permohonanLaporanUlasan.setUlasan(ulasanSebab);
//        permohonanLaporanUlasan.setJenisLaporan("Ulasan PPT");
        permohonanLaporanUlasan.setInfoAudit(ia);
        disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(permohonanLaporanUlasan);

        addSimpleMessage("Maklumat Banggunan Telah Berjaya disimpan");
//        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);
//        return new RedirectResolution(laporantanahNewActionBean, "syorPopap");
//        return new RedirectResolution(laporantanahNewActionBean.class, "syorPopap").addParameter("showForm", "false", idMohonHakmilik, idLaporTanah);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanUlasanBaru() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pguna);

        String jenisLaporan = "Ulasan PPT";

        permohonanLaporanUlasan = new PermohonanLaporanUlasan();
        permohonanLaporanUlasan.setPermohonan(permohonan);
        permohonanLaporanUlasan.setIdLaporUlas(laporanTanah.getIdLaporan());
        permohonanLaporanUlasan.setUlasan(ulasanSebab);
        permohonanLaporanUlasan.setJenisUlasan(jenisLaporan);
        permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
        permohonanLaporanUlasan.setInfoAudit(ia);
        permohonanLaporanUlasanDAO.save(permohonanLaporanUlasan);
        disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(permohonanLaporanUlasan);
//        disLaporanTanahService.getPelPtService().deletePermohonanLaporanUlasan(permohonanLaporanUlasan);

        addSimpleMessage("Maklumat Banggunan Telah Berjaya disimpan");
//        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);
//        return new RedirectResolution(laporantanahNewActionBean, "syorPopap");
//        return new RedirectResolution(laporantanahNewActionBean.class, "syorPopap").addParameter("showForm", "false", idMohonHakmilik, idLaporTanah);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahUlasanNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanSyorPopap() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonanLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(idPermohonan, stageId);
        if (fasaPermohonanList.size() > 0) {
            fasaPermohonan = fasaPermohonanList.get(0);
            kodKeputusan = kodKeputusanDAO.findById(sokong);
            fasaPermohonan.setKeputusan(kodKeputusan);
            fasaPermohonan.setUlasan(sebab);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(pguna);

        } else {
            fasaPermohonan = new FasaPermohonan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pguna);
        }
        addSimpleMessage("Maklumat Banggunan Telah Berjaya disimpan");
        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);
//        return new RedirectResolution(laporantanahNewActionBean, "syorPopap");
//        return new RedirectResolution(laporantanahNewActionBean.class, "syorPopap").addParameter("showForm", "false", idMohonHakmilik, idLaporTanah);
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPengusaha() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String idLaporBangunan = ctx.getRequest().getParameter("idLaporBangunan");
        InfoAudit ia = new InfoAudit();

        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        laporanTanah = (LaporanTanah) disLaporanTanahService.getPlpservice().findDAOByPK(laporanTanah, Long.parseLong(idLaporTanah));
        if (laporanTanah != null) {
            PermohonanLaporanUsaha permohonanLaporanUsahaTemp = new PermohonanLaporanUsaha();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pguna);
            permohonanLaporanUsahaTemp.setInfoAudit(ia);
            permohonanLaporanUsahaTemp.setDiUsahaOleh(kategori); //Untuk orang lain-lain
            permohonanLaporanUsahaTemp.setIdLaporan(laporanTanah);
            if (permohonanLaporanUsaha.getDiUsaha() != null) {
                permohonanLaporanUsahaTemp.setDiUsaha(permohonanLaporanUsaha.getDiUsaha());
            }
            disLaporanTanahService.getPelupusanService().simpanPermohonanLaporanUsaha(permohonanLaporanUsahaTemp);
        }
        listPermohonanLaporanUsaha = disLaporanTanahService.getPelupusanService().findListMohonLaporUsahaByIdLaporan(laporanTanah.getIdLaporan());
        if (listPermohonanLaporanUsaha.size() > 0) {
            laporanTanah.setUsaha('Y');
            disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanah);
            permohonanLaporanUsaha = listPermohonanLaporanUsaha.get(0);
        }

        addSimpleMessage("Maklumat Banggunan Telah Berjaya disimpan");
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSempadanNew.jsp").addParameter("popup", "true");

    }

    public Resolution updateImejLaporan() throws Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idDokumen = getContext().getRequest().getParameter("idDok");
        LOG.info("idDokumen :------ " + idDokumen);
        String forwardJSP = new String();
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);

            if (catatan == null) {
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("pelupusan/laporan_tanah/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }

            String idLapor = getContext().getRequest().getParameter("idLapor");
            ImejLaporan il = disLaporanTanahService.getLaporanTanahService().getLaporImejByLaporDokumen(Long.parseLong(idLapor), Long.parseLong(idDokumen));

            il.setCawangan(permohonan.getCawangan());
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            il.setCatatan(catatan);
            disLaporanTanahService.getLaporanTanahService().simpanHakmilikImej(il);

            addSimpleMessage("Kemaskini telah berjaya.");
            catatan = new String();
            idLaporTanah = String.valueOf(laporanTanah.getIdLaporan());
//            forwardJSP = refreshData("imgPopup");
        }
        getContext().getRequest().setAttribute("a", Boolean.FALSE);
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution reload() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        rehydrate();
//        perihalTanahUpload
        return perihalTanahUpload(idMohonHakmilik);
    }

    public Resolution reloadLotSebelah() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        rehydrate();
//        perihalTanahUpload
//        return perihalTanahUpload(idMohonHakmilik);
        return lotSempadan();
    }

    public Resolution reloadMain() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        rehydrate();
        return viewOnlyLaporanTanahPPT();
    }

    public Resolution simpanLatarBelakangTanah() throws ParseException {

        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

//        laporanTanah = (LaporanTanah) disLaporanTanahService.getPlpservice().findDAOByPK(laporanTanah, Long.parseLong(idLaporTanah));
        if (StringUtils.isNotBlank(idLaporTanah)) {
            laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        }

        if (laporanTanah != null) {
            PermohonanLaporanPohon permohonanLaporanPohonTemp = new PermohonanLaporanPohon();
            String jenisPohon = getContext().getRequest().getParameter("permohonanLaporanPohon.jenisDipohon");
            if (StringUtils.isNotBlank(jenisPohon)) {
                permohonanLaporanPohonTemp.setJenisDipohon(jenisPohon);
            }
            String noRujukan = getContext().getRequest().getParameter("permohonanLaporanPohon.noRujukan");
            if (StringUtils.isNotBlank(noRujukan)) {
                permohonanLaporanPohonTemp.setNoRujukan(noRujukan);
            } else {
                String noFail = getContext().getRequest().getParameter("noFail");
                permohonanLaporanPohonTemp.setNoRujukan(noFail);
            }
            String kegunaan = getContext().getRequest().getParameter("permohonanLaporanPohon.kegunaan");
            if (StringUtils.isNotBlank(kegunaan)) {
                permohonanLaporanPohonTemp.setKegunaan(kegunaan);
            }
            permohonanLaporanPohonTemp.setIdLaporan(laporanTanah);
            permohonanLaporanPohonTemp.setInfoAudit(ia);

            disLaporanTanahService.getPelupusanService().simpanPermohonanLaporanPohon(permohonanLaporanPohonTemp);
        }
        rehydrate();

        //addSimpleMessage("Maklumat Telah Berjaya disimpan");
        String idMh = String.valueOf(laporanTanah.getHakmilikPermohonan().getId());
        rehydrate();
        return latarBelakangReload(idMh);
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahLatarBelakangNew.jsp").addParameter("popup", "true");

    }

    public Resolution latarBelakangReload(String idMohonHakmilik1) {

        if (idMohonHakmilik1 == null) {
            idMohonHakmilik1 = getContext().getRequest().getParameter("idMohonHakmilik");
        }
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        }

        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (idMohonHakmilik != null) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        } else if (laporanTanah != null) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(laporanTanah.getHakmilikPermohonan().getId());
        }
        idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
        permohonan = hakmilikPermohonan.getPermohonan();
        rehydrate();

        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahLatarBelakangNew.jsp").addParameter("popup", "true");
    }

    public void mohonLaporTanahnNew(HakmilikPermohonan hakmilikPermohonan, Pengguna pguna) {
        laporanTanah1 = new LaporanTanah();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        laporanTanah1.setPermohonan(hakmilikPermohonan.getPermohonan());
        laporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
        laporanTanah1.setInfoAudit(infoAudit);
        disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanah1);

//        return viewOnlyLaporanTanahPPT();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanBersempadanan", "!showFormPopUp", "!simpanKandungan", "!uploadForm", "!simpanKeadaanTanah", "!deleteRow", "!deleteImage", "!simpanImejLaporan", "!simpanLatarBelakangTanah", "!latarBelakangReload"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
//        stageId = stageId(taskId, pguna); //iwe
        if (idMohonHakmilik == null) {
            idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        }

        if (idMohonHakmilik != null && permohonan.getKodUrusan().getKod().equals("NT6A")) {
            simpanMohonHakmilik(idMohonHakmilik);
        }
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
            //stageId = "laporan_tanah";
            System.out.println("-------------stageId--" + stageId);
        } else {
            if (permohonan.getKodUrusan().getKod().equals("NT6A")) {
                stageId = "Laporan_Tanah";
            } else if (permohonan.getKodUrusan().getKod().equals("PLPT")) {
                stageId = "laporantanah";
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                stageId = "07SdknLapTnh";
            } else {
                stageId = "laporan_tanah";
            }

        }
        if (idMohonHakmilik != null) {
            laporanTanah1 = pelupusanService.findLaporanTanahByIdPermohonanAndIdMH(idPermohonan, Long.parseLong(idMohonHakmilik));
            if (laporanTanah1 != null) {
                laporanTanahSempadanList = pelupusanService.findLaporTanahSmpdnByIdLapor(laporanTanah1.getIdLaporan());

            }
        }

        LOG.info(":::stage ID ::: " + stageId);
        LOG.info("idMohonHakmilik>>>>>>" + idMohonHakmilik);
        //stageId = stageId("laporan_tanah", pguna);
        negeri = conf.getProperty("kodNegeri");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonanLaporanPelan = new PermohonanLaporanPelan();
        pmList = pelupusanService.findByIdMohonlikeDIS(idPermohonan);

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //Add for charting

        hakmilikPermohonanList2 = permohonan.getSenaraiHakmilik();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        /*
         * Perihal Tanah
         */
//        hakmilikImejLaporanList = new ArrayList<ImejLaporan>();

        if (idPermohonan != null) {

            if (idMohonHakmilik == null) {
                hakmilikPermohonanList1 = permohonan.getSenaraiHakmilik();
                noLotList = new ArrayList<String>();
                for (HakmilikPermohonan hp : hakmilikPermohonanList1) {
                    if (idHakmilik != null && !idHakmilik.isEmpty()) {
                        noLotList.add(hp.getHakmilik().getNoLot().replaceAll("^0*", ""));
                    }
                }
            } else {
//                hakmilikPermohonanList1.clear();
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
                hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                laporanTanah1 = pelupusanService.findLaporanTanahByIdPermohonanAndIdMH(idPermohonan, hakmilikPermohonan.getId());
                if (laporanTanah1 == null) {
                    mohonLaporTanahnNew(hakmilikPermohonan, pguna);
                }
                idLaporTanah = String.valueOf(laporanTanah1.getIdLaporan());
                hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah1.getIdLaporan());
                for (ImejLaporan imej : hakmilikImejLaporanList) {
                    hakmilikImejLaporanListU = new ArrayList<ImejLaporan>();
                    hakmilikImejLaporanListS = new ArrayList<ImejLaporan>();
                    hakmilikImejLaporanListT = new ArrayList<ImejLaporan>();
                    hakmilikImejLaporanListB = new ArrayList<ImejLaporan>();
                    if (imej.getLaporanTanahSempadan() != null) {
                        if (imej.getLaporanTanahSempadan().getJenisSempadan().equals("U")) {
                            hakmilikImejLaporanListU.add(imej);
                        } else if (imej.getLaporanTanahSempadan().getJenisSempadan().equals("S")) {
                            hakmilikImejLaporanListS.add(imej);
                        } else if (imej.getLaporanTanahSempadan().getJenisSempadan().equals("T")) {
                            hakmilikImejLaporanListT.add(imej);
                        } else if (imej.getLaporanTanahSempadan().getJenisSempadan().equals("B")) {
                            hakmilikImejLaporanListB.add(imej);
                        }
                    }
                }
                hakmilikPermohonanList1.add(hakmilikPermohonan);
                if (idHakmilik != null && !idHakmilik.isEmpty()) {
                    noLotList.add(hakmilikPermohonan.getHakmilik().getNoLot().replaceAll("^0*", ""));
                } else {
                    if (hakmilikPermohonan.getNoLot() != null) {
                        noLotList.add(hakmilikPermohonan.getNoLot().replaceAll("^0*", ""));
                    }
                }
//                tanahrizabpermohonan1 = new TanahRizabPermohonan();
                idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
                luas = hakmilikPermohonan.getLuasTerlibat();
                kodUOM = hakmilikPermohonan.getKodUnitLuas();

                if (kodUOM != null) {
                    kodUnitLuas = kodUOM.getKod();
                }

//                tanahrizabpermohonan1 = pelupusanService.findTanahRizabByIdPermohonanAndMh(idPermohonan, idMohonHakmilik);
                tanahrizabpermohonan1ist = pelupusanService.findTanahRizabByIdPermohonanList(idPermohonan);
                for (TanahRizabPermohonan trp : tanahrizabpermohonan1ist) {
                    if (trp.getHakmilikPermohonan() != null) {
                        String idmh = String.valueOf(trp.getHakmilikPermohonan().getId());
                        if (idmh.equals(idMohonHakmilik)) {
                            tanahrizabpermohonan1 = trp;
                        }
                    }
                }
//                tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{idPermohonan, hakmilikPermohonan.getHakmilik().getNoHakmilik()}, 0);
//                tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{idPermohonan}, 1);
                permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, Long.valueOf(idMohonHakmilik));
                List<PermohonanManual> mohonManualSenarai = pembangunanService.findPermohonanManualByIdPermohonan(idPermohonan);
                permohonanLamaListUrusan = new ArrayList<Permohonan>();
                mohonManualList = new ArrayList<PermohonanManual>();
                for (PermohonanManual manual : mohonManualSenarai) {
                    if (manual.getNoFail() != null) {
                        permohonanLama = permohonanDAO.findById(manual.getNoFail());
                        if (permohonanLama != null) {
                            permohonanLamaList = new ArrayList<Permohonan>();
                            permohonanLamaListUrusan.add(permohonanLama);
                        } else {
                            mohonManualList.add(manual);
                        }

                    }
                }
                senaraiPermohonanLaporanPohon = pelupusanService.findListMohonLaporPohonByIdLaporan(laporanTanah1.getIdLaporan());
                laporanTanahSempadanList = pelupusanService.findLaporTanahSmpdnByIdLapor(laporanTanah1.getIdLaporan());
                senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));

                fasaPermohonanList = pelupusanService.findMohonFasaByIdMohonIdPenggunaList(idPermohonan, stageId);
                if (fasaPermohonanList.size() > 0) {
                    mohonFasa = fasaPermohonanList.get(0);
                }

                senaraiLaporanKandungan1 = pelupusanService.findUlasanByIdMohon(idPermohonan);

                if (hakmilikPermohonan.getKodMilik() != null) {
                    kodPa = Character.toString(hakmilikPermohonan.getKodMilik().getKod());
                }
                int syx = 0;
                if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                    syx = hakmilikPermohonan.getBandarPekanMukimBaru().getKod();
                }

                kodSeksyenList = disLaporanTanahService.getPlpservice().getSenaraiKodSeksyenByBPM(syx);
            }
            tanahrizabpermohonan1ist = pelupusanService.findTanahRizabByIdPermohonanList(idPermohonan);
            permohonanLaporanPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
            laporanTanahList.add(laporanTanah1);

//            permohonanLaporanBangunanList = disLaporanTanahService.getLaporanTanahService().getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
//            laporanTanahSempadanList = disLaporanTanahService.getLaporanTanahService().getlapor
        }
//        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/laporan_tanahV2AddLatarBelakang.jsp").addParameter("popup", "true");
    }

    public void simpanMohonHakmilik(String idMohonHakmilik) {

        HakmilikPermohonan hp = hakmilikPermohonanService.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        Hakmilik hm = hp.getHakmilik();

//        hp.setKodMilik(hm.getkod);
        hp.setCawangan(cawangan);
//        hp.setKodMilik(null);
        hp.setBandarPekanMukimBaru(hm.getBandarPekanMukim());
        hp.setKodUnitLuas(hm.getKodUnitLuas());
        hp.setLuasBaru(hm.getLuas());
        hakmilikPermohonanService.save(hp);
    }

    public Resolution uploadDoc() {
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
//        String idLapor = getContext().getRequest().getParameter("idLapor");

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        laporanTanah = new LaporanTanah();
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLapor");
        }
        if (idLaporTanah.equals("undefined")) {
            idLaporTanah = getContext().getRequest().getParameter("idLapor");
        }
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (laporanTanah != null) {
            hakmilikPermohonan = laporanTanah.getHakmilikPermohonan();
        }

        if (pandanganImej == 'H') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLaporTanah));
        } else if (pandanganImej == 'P') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdPanorama(Long.parseLong(idLaporTanah));
        } else if (pandanganImej == 'D') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdLampiranD(Long.parseLong(idLaporTanah));
        } else if (pandanganImej == 'G') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdLampiranG(Long.parseLong(idLaporTanah));
        } else {
            LaporanTanahSempadan lts = new LaporanTanahSempadan();
            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idlaporTnhSmpdn));
//            disLaporanTanahSempadan.setLaporanTanahSempadan(lts);
            if (lts != null) {
                if (pandanganImej == 'U') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts);
                } else if (pandanganImej == 'S') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts);
                } else if (pandanganImej == 'T') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts);
                } else if (pandanganImej == 'B') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts);
                }
            }
        }
        catatan = new String();
        getContext().getRequest().setAttribute("a", Boolean.FALSE);
        if (pandanganImej == 'H') {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew.jsp").addParameter("popup", "true");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew.jsp").addParameter("popup", "true");
        }

    }

    public Resolution uploadDocUTBS() {
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
//        String idLapor = getContext().getRequest().getParameter("idLapor");

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        laporanTanah = new LaporanTanah();
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLapor");
        }
        if (idLaporTanah.equals("undefined")) {
            idLaporTanah = getContext().getRequest().getParameter("idLapor");
        }
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        if (laporanTanah != null) {
            hakmilikPermohonan = laporanTanah.getHakmilikPermohonan();
        }

        if (pandanganImej == 'H') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLaporTanah));
        } else if (pandanganImej == 'P') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdPanorama(Long.parseLong(idLaporTanah));
        } else if (pandanganImej == 'D') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanIdLampiranD(Long.parseLong(idLaporTanah));
        } else {
            LaporanTanahSempadan lts = new LaporanTanahSempadan();
            lts = pelupusanService.findLaporanTanahSempadanById(Long.parseLong(idlaporTnhSmpdn));
//            disLaporanTanahSempadan.setLaporanTanahSempadan(lts);
            if (lts != null) {
                if (pandanganImej == 'U') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts);
                } else if (pandanganImej == 'S') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts);
                } else if (pandanganImej == 'T') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts);
                } else if (pandanganImej == 'B') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts);
                }
            }
        }
        catatan = new String();
        getContext().getRequest().setAttribute("a", Boolean.FALSE);
        if (pandanganImej == 'H') {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew_Arah.jsp").addParameter("popup", "true");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/Upload_imej_Laporan_TanahNew_Arah.jsp").addParameter("popup", "true");
        }

    }

    public Resolution refreshpage() {
        //FOR UPLOAD IMAGE
        String typeImage = ctx.getRequest().getParameter("pandanganImej");
        idLaporTanah = ctx.getRequest().getParameter("idLapor");
        if (typeImage != null && !typeImage.isEmpty()) {
            pandanganImej = typeImage.charAt(0);
        }
        rehydrate();
        //END
        return (reload());
    }

    public Resolution refreshpageLotSmpdn() {
        //FOR UPLOAD IMAGE
        String typeImage = ctx.getRequest().getParameter("pandanganImej");
        idLaporTanah = ctx.getRequest().getParameter("idLapor");
        String idLaporTSpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
        if (!idLaporTSpdn.isEmpty()) {
            Long idlaporTnhSmpdn = Long.parseLong(ctx.getRequest().getParameter("idlaporTnhSmpdn"));
            LOG.info("idlaporTnhSmpdn : " + idlaporTnhSmpdn);
        }
        if (typeImage != null && !typeImage.isEmpty()) {
            pandanganImej = typeImage.charAt(0);
        }
        catatan = null;

//        if (idImej != null){
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
//        }
//        if (idDokumen != null){
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
//        }

        LOG.info("id Dokumen :== " + idDokumen);
        LOG.info("id idImej :== " + idImej);
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String forwardJSP = new String();
        if (!type.isEmpty()) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
//            try {
            disLaporanTanahService.delObject("laporanImej", new String[]{String.valueOf(idImej)}, new String());
            disLaporanTanahService.delObject("dokumen", new String[]{String.valueOf(idDokumen)}, new String());
            tx.commit();
//                if (!imageType.isEmpty()) {
//                    pandanganImej = imageType.charAt(0);
//                }
//                forwardJSP = refreshData(type);

//            } catch (Exception ex) {
//                tx.rollback();
//                Throwable t = ex;
//                // getting the root cause
//                while (t.getCause() != null) {
//                    t = t.getCause();
//                }
//                ex.printStackTrace();
//                addSimpleError(t.getMessage());
//            }
        } else {
            addSimpleError("Type Tidak Dijumpai. Sila Hubungi Pentadbir Sistem Untuk Maklumat Lanjut");
        }

        laporanTanah = pelupusanService.findLaporanTanahByIdMH(Long.valueOf(idMohonHakmilik));
        if (laporanTanah != null) {
            hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
            LOG.info("imejList del : " + hakmilikImejLaporanList.size());
        }

        //END
        return reloadLotSebelah();
    }

    public Resolution refreshpageTnhSmpdn() {
        //FOR UPLOAD IMAGE
        String typeImage = ctx.getRequest().getParameter("pandanganImej");
        idLaporTanah = ctx.getRequest().getParameter("idLapor");
        String idLaporTSpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
        if (!idLaporTSpdn.isEmpty()) {
            Long idlaporTnhSmpdn = Long.parseLong(ctx.getRequest().getParameter("idlaporTnhSmpdn"));
            LOG.info("idlaporTnhSmpdn : " + idlaporTnhSmpdn);
        }
        if (typeImage != null && !typeImage.isEmpty()) {
            pandanganImej = typeImage.charAt(0);
        }
        catatan = null;

//        if (idImej != null){
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
//        }
//        if (idDokumen != null){
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
//        }

        LOG.info("id Dokumen :== " + idDokumen);
        LOG.info("id idImej :== " + idImej);
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String forwardJSP = new String();
        if (!type.isEmpty()) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
//            try {
            disLaporanTanahService.delObject("laporanImej", new String[]{String.valueOf(idImej)}, new String());
            disLaporanTanahService.delObject("dokumen", new String[]{String.valueOf(idDokumen)}, new String());
            tx.commit();
//                if (!imageType.isEmpty()) {
//                    pandanganImej = imageType.charAt(0);
//                }
//                forwardJSP = refreshData(type);

//            } catch (Exception ex) {
//                tx.rollback();
//                Throwable t = ex;
//                // getting the root cause
//                while (t.getCause() != null) {
//                    t = t.getCause();
//                }
//                ex.printStackTrace();
//                addSimpleError(t.getMessage());
//            }
        } else {
            addSimpleError("Type Tidak Dijumpai. Sila Hubungi Pentadbir Sistem Untuk Maklumat Lanjut");
        }

        laporanTanah = pelupusanService.findLaporanTanahByIdMH(Long.valueOf(idMohonHakmilik));
        if (laporanTanah != null) {
            hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
            LOG.info("imejList del : " + hakmilikImejLaporanList.size());
        }

        //END
        return reload();
    }

    public Resolution simpanPerihal() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String idMh = getContext().getRequest().getParameter("idMohonHakmilik");
        bolehBerimilik = getContext().getRequest().getParameter("bolehBerimilik");
        sebabBoleh = getContext().getRequest().getParameter("sebabBoleh");

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
//        String idMh2 = getContext().getRequest().getParameter("idMh");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        if (!idHakmilik.equals("") && !idPermohonan.equals("")) {
            HakmilikPermohonan hakmilikP = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilik, idPermohonan);
            idMh = String.valueOf(hakmilikP.getId());
        }
        List<HakmilikPermohonan> senaraiHp = permohonan.getSenaraiHakmilik();
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();

        if (idMh.equals("")) {
            idMh = String.valueOf(senaraiHp.get(0).getId());
            mohonHakmilik = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
        } else {
            mohonHakmilik = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
        }

        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));

//        PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
        PermohonanLaporanPelan mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, Long.parseLong(idMh));
        if (StringUtils.isEmpty(idHakmilik)) {
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
                for (HakmilikPermohonan hm : hpList) {
                    LOG.info("id mh == " + hm.getId());
                    LOG.info("id mohon == " + idPermohonan);
                    mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hm.getId());
                }
            }
        }

        TanahRizabPermohonan tRizab = pelupusanService.findTanahRizabByIdPermohonanAndMh(idPermohonan, idMohonHakmilik);
        if (tRizab != null) {
            tRizab.setInfoAudit(disLaporanTanahService.findAudit(tRizab, "new", pguna));
            tRizab.setPermohonan(permohonan);
            if (mohonHakmilik.getHakmilik() != null) {
                tRizab.setCawangan(mohonHakmilik.getHakmilik().getCawangan());
                tRizab.setDaerah(mohonHakmilik.getHakmilik().getDaerah());
                tRizab.setNoHakmilik(mohonHakmilik.getHakmilik().getNoHakmilik());
                tRizab.setBandarPekanMukim(mohonHakmilik.getHakmilik().getBandarPekanMukim());
                tRizab.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
            } else {
//                    tRizab.setCawangan(mohonHakmilik.getPermohonan().getCawangan());
                tRizab.setCawangan(permohonan.getCawangan());
//                    tRizab.setDaerah(mohonHakmilik.getPermohonan().getCawangan().getDaerah());
                tRizab.setDaerah(permohonan.getCawangan().getDaerah());
                tRizab.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
                tRizab.setNoLot(mohonHakmilik.getNoLot());
            }
            disLaporanTanahService.getPlpservice().simpanTanahRizabPermohonan(tRizab);

            tRizab.setAktif('Y');
        } else {
            TanahRizabPermohonan mohonTanahRizab = new TanahRizabPermohonan();
        }
        TanahRizabPermohonan mohonTanahRizab = new TanahRizabPermohonan();
        if (StringUtils.isNotBlank(tanahR) && !tanahR.equals("0")) {
            if (mohonHakmilik != null && mohonHakmilik.getHakmilik() != null) {
                mohonTanahRizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(mohonTanahRizab, new String[]{idPermohonan, mohonHakmilik.getHakmilik().getNoHakmilik()}, 0);
            } else {
                mohonTanahRizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(mohonTanahRizab, new String[]{idPermohonan}, 1);
            }
            if (mohonTanahRizab != null) {
                mohonTanahRizab.setInfoAudit(disLaporanTanahService.findAudit(mohonTanahRizab, "update", pguna));
            } else {
                mohonTanahRizab = new TanahRizabPermohonan();
                mohonTanahRizab.setInfoAudit(disLaporanTanahService.findAudit(mohonTanahRizab, "new", pguna));
                mohonTanahRizab.setPermohonan(permohonan);
                if (mohonHakmilik.getHakmilik() != null) {
                    mohonTanahRizab.setCawangan(mohonHakmilik.getHakmilik().getCawangan());
                    mohonTanahRizab.setDaerah(mohonHakmilik.getHakmilik().getDaerah());
                    mohonTanahRizab.setNoHakmilik(mohonHakmilik.getHakmilik().getNoHakmilik());
                    mohonTanahRizab.setBandarPekanMukim(mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    mohonTanahRizab.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                } else {
//                    mohonTanahRizab.setCawangan(mohonHakmilik.getPermohonan().getCawangan());
                    mohonTanahRizab.setCawangan(permohonan.getCawangan());
//                    mohonTanahRizab.setDaerah(mohonHakmilik.getPermohonan().getCawangan().getDaerah());
                    mohonTanahRizab.setDaerah(permohonan.getCawangan().getDaerah());
                    mohonTanahRizab.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
                    mohonTanahRizab.setNoLot(mohonHakmilik.getNoLot());
                }
                disLaporanTanahService.getPlpservice().simpanTanahRizabPermohonan(mohonTanahRizab);

                mohonTanahRizab.setAktif('Y');
            }

            KodRizab kr = disLaporanTanahService.getKodRizabDAO().findById(Integer.parseInt(tanahR));
            mohonTanahRizab.setRizab(kr);
            if (!StringUtils.isBlank(statBdnPngwl)) {
                String penjaga = new String();
                penjaga = statBdnPngwl.equals("1") ? "SUK Negeri"
                        : statBdnPngwl.equals("2") ? "Pesuruhjaya Tanah Persekutuan"
                        : !statBdnPngwl.equals("1") && !statBdnPngwl.equals("2") ? tanahrizabpermohonan1.getPenjaga()
                        : "0";
                mohonTanahRizab.setPenjaga(penjaga);
            }

            if (mohonTanahRizab != null) {
                if (mohonTanahRizab.getPermohonan() != null) {
                    if (tanahrizabpermohonan1 != null && !StringUtils.isEmpty(tanahrizabpermohonan1.getNoWarta())) {
                        mohonTanahRizab.setNoWarta(tanahrizabpermohonan1.getNoWarta());
                    }
                    if (tanahrizabpermohonan1 != null && tanahrizabpermohonan1.getTarikhWarta() != null) {
                        mohonTanahRizab.setTarikhWarta(tanahrizabpermohonan1.getTarikhWarta());
                    }
                    if (tanahrizabpermohonan1 != null && !StringUtils.isEmpty(tanahrizabpermohonan1.getNoPW())) {
                        mohonTanahRizab.setNoPW(tanahrizabpermohonan1.getNoPW());
                    }
                    mohonTanahRizab.setHakmilikPermohonan(hakmilikPermohonan);
                    disLaporanTanahService.getPlpservice().simpanTanahRizabPermohonan(mohonTanahRizab);
                }
            }
        }

        if (StringUtils.isNotBlank(kodT)) {
            KodTanah ktt = disLaporanTanahService.getKodTanahDAO().findById(kodT);
            if (mohonLaporPelan != null) {
                mohonLaporPelan.setKodTanah(ktt);
                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
            } else {
                mohonLaporPelan = new PermohonanLaporanPelan();
                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                mohonLaporPelan.setPermohonan(permohonan);
                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pguna));
                mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
            }

            if (kand != null) {
                mohonLaporPelan.setSyor(kand);
            }
            disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);

        }
        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonanAndIdMH(idPermohonan, hakmilikPermohonan.getId());
        if (mohonHakmilik != null) {
            if (laporanTanah != null) {
                /*
                 * LAPORAN TANAH
                 */
                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    hmList = pelupusanService.findByIdPermohonan1(idPermohonan);

                    for (HakmilikPermohonan hm : hmList) {
                        LaporanTanah laporanTanahTemp = new LaporanTanah();
                        laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hm.getId())}, 0);
                        if (laporanTanahTemp != null) {

                            laporanTanahTemp.setBolehBerimilik(bolehBerimilik);
                            laporanTanahTemp.setSebabTidakBolehBerimilik(sebabBoleh);

                            disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanahTemp);
                        }
                    }
                } else {
                    LaporanTanah laporanTanahTemp = new LaporanTanah();
                    laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(mohonHakmilik.getId())}, 0);
                    if (laporanTanahTemp != null) {
                        laporanTanahTemp.setBolehBerimilik(idHakmilik);

                        laporanTanahTemp.setBolehBerimilik(bolehBerimilik);
                        laporanTanahTemp.setSebabTidakBolehBerimilik(sebabBoleh);

                        disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanahTemp);
                    }
                }
            }


            /*
             * HAKMILIKPERMOHONAN
             */
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                hmList = pelupusanService.findByIdPermohonan1(idPermohonan);

                for (HakmilikPermohonan hm : hmList) {

                    String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");

                    if ((kodS != null) && !kodS.equals("0")) {
                        Integer a = Integer.parseInt(kodS);
                        int kod = a.intValue();
                        KodSeksyen kodSeksyen = disLaporanTanahService.getPlpservice().findByKodSeksyen(kod);
                        hm.setKodSeksyen(kodSeksyen);
                    }
                    if (!Character.isWhitespace(kodP)) {
                        KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                        hm.setKodMilik(kpm);
                    }
                    if (!StringUtils.isEmpty(kodPar)) {
                        KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                        hm.setKodKawasanParlimen(kw);
                    }
                    kodD = getContext().getRequest().getParameter("kodDun");
                    if (kodD != null) {
                        KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                        hm.setKodDUN(kd);
                    }
                    if (lokasi != null) {
                        hm.setLokasi(lokasi);

                    }
                    hm.setInfoAudit(disLaporanTanahService.findAudit(hm, "update", pguna));
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hm);
                }

            } else {
                String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");

                if ((kodS != null) && !kodS.equals("0")) {
                    Integer a = Integer.parseInt(kodS);
                    int kod = a.intValue();
                    KodSeksyen kodSeksyen = disLaporanTanahService.getPlpservice().findByKodSeksyen(kod);
                    mohonHakmilik.setKodSeksyen(kodSeksyen);
                }
                if (!Character.isWhitespace(kodP)) {
                    KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                    mohonHakmilik.setKodMilik(kpm);
                }
                if (!StringUtils.isEmpty(kodPar)) {
                    KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                    mohonHakmilik.setKodKawasanParlimen(kw);
                }
                kodD = getContext().getRequest().getParameter("kodDun");
                if (kodD != null) {
                    KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                    mohonHakmilik.setKodDUN(kd);
                }
                if (lokasi != null) {
                    mohonHakmilik.setLokasi(lokasi);

                }
//                mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "update", pguna));
//                disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilik);
//                hakmilikPermohonanDAO.save(mohonHakmilik);
                List<HakmilikPermohonan> listHP = new ArrayList<HakmilikPermohonan>();
                listHP.add(mohonHakmilik);
                hakmilikPermohonanService.save(mohonHakmilik);
                hakmilikPermohonanService.saveHakmilikPermohonan(listHP);
//                laporanPelukisPelanService.saveOrUpdateTanahMilik(hakmilikPermohonan);
                laporanPelukisPelanService.saveOrUpdateTanahMilik(mohonHakmilik);

            }
        }

//        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahPerihalNew.jsp").addParameter("popup", "true");

    }

    public Resolution simpanFailPermohonan() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idMohonManual = getContext().getRequest().getParameter("idMohonManual");
        noFailBaru = getContext().getRequest().getParameter("noFailBaru");
        noFail = getContext().getRequest().getParameter("noFail");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit = permohonan.getInfoAudit();

        permohonanManual = pembangunanService.findPermohonanManualByIdPermohonanIdManual(idPermohonan, idMohonManual);
        if (permohonanManual != null) {
            permohonanManual.setNoFail(noFail);
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pembangunanService.simpanPermohonanManual(permohonanManual);
        } else {
            permohonanManual = new PermohonanManual();
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setCawangan(peng.getKodCawangan());
            permohonanManual.setNoFail(noFailBaru);
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            pembangunanService.simpanPermohonanManual(permohonanManual);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
//        rehydrate();
        return new RedirectResolution(laporantanahNewActionBean.class, "viewOnlyLaporanTanahPPT");

    }

    public Resolution simpanBersempadanan() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        LaporanTanah laporanTanahTemp = new LaporanTanah();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            if (permohonan.getKodUrusan().getKod().equals("PBHL") || permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            } else {
//                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
            }
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
            noPtTemp = new NoPt();
            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
//            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
        } else {
//            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
        }

        if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PBHL")) {
//            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
            laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);

            if (laporanTanahTemp != null) {
                laporanTanahTemp.setInfoAudit(disLaporanTanahService.findAudit(laporanTanahTemp, "update", pguna));

            } else {
                laporanTanahTemp = new LaporanTanah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanTanahTemp.setInfoAudit(infoAudit);
            }
            laporanTanahTemp.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanahTemp.setNamaSempadanJalanraya(laporanTanah.getNamaSempadanJalanraya());
            laporanTanahTemp.setJarakSempadanJalanraya(laporanTanah.getJarakSempadanJalanraya());
            laporanTanahTemp.setNamaSempadanKeretapi(laporanTanah.getNamaSempadanKeretapi());
            laporanTanahTemp.setJarakSempadanKeretapi(laporanTanah.getJarakSempadanKeretapi());
            laporanTanahTemp.setNamaSempadanLaut(laporanTanah.getNamaSempadanLaut());
            laporanTanahTemp.setJarakSempadanLaut(laporanTanah.getJarakSempadanLaut());
            laporanTanahTemp.setNamaSempadanSungai(laporanTanah.getNamaSempadanSungai());
            laporanTanahTemp.setJarakSempadanSungai(laporanTanah.getJarakSempadanSungai());
            laporanTanahTemp.setNamaSempadanlain(laporanTanah.getNamaSempadanlain());
            laporanTanahTemp.setJarakSempadanLain(laporanTanah.getJarakSempadanLain());
            laporanTanahTemp.setJenisJalan(laporanTanah.getJenisJalan());
            laporanTanahTemp.setAdaJalanMasuk(laporanTanah.getAdaJalanMasuk());
            laporanTanahTemp.setCatatanJalanMasuk(laporanTanah.getCatatanJalanMasuk());
            pelupusanService.simpanKemaskiniLaporanTanah(laporanTanahTemp);
//                    disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);

        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);

            for (HakmilikPermohonan hp : hpList) {
                if (hakmilikPermohonan != null) {
                    laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hp.getId())}, 0);
                }
                if (laporanTanahTemp != null) {
                    laporanTanahTemp.setInfoAudit(disLaporanTanahService.findAudit(laporanTanahTemp, "update", pguna));
                    laporanTanahTemp.setNamaSempadanJalanraya(laporanTanah.getNamaSempadanJalanraya());
                    laporanTanahTemp.setJarakSempadanJalanraya(laporanTanah.getJarakSempadanJalanraya());
                    laporanTanahTemp.setNamaSempadanKeretapi(laporanTanah.getNamaSempadanKeretapi());
                    laporanTanahTemp.setJarakSempadanKeretapi(laporanTanah.getJarakSempadanKeretapi());
                    laporanTanahTemp.setNamaSempadanLaut(laporanTanah.getNamaSempadanLaut());
                    laporanTanahTemp.setJarakSempadanLaut(laporanTanah.getJarakSempadanLaut());
                    laporanTanahTemp.setNamaSempadanSungai(laporanTanah.getNamaSempadanSungai());
                    laporanTanahTemp.setJarakSempadanSungai(laporanTanah.getJarakSempadanSungai());
                    laporanTanahTemp.setNamaSempadanlain(laporanTanah.getNamaSempadanlain());
                    laporanTanahTemp.setJarakSempadanLain(laporanTanah.getJarakSempadanLain());
                    laporanTanahTemp.setJenisJalan(laporanTanah.getJenisJalan());
                    laporanTanahTemp.setAdaJalanMasuk(laporanTanah.getAdaJalanMasuk());
                    laporanTanahTemp.setCatatanJalanMasuk(laporanTanah.getCatatanJalanMasuk());
                    disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
                }
            }
        } else {
            if (hakmilikPermohonan != null) {
                laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
            }
            if (laporanTanahTemp != null) {
                laporanTanahTemp.setInfoAudit(disLaporanTanahService.findAudit(laporanTanahTemp, "update", pguna));
                laporanTanahTemp.setNamaSempadanJalanraya(laporanTanah.getNamaSempadanJalanraya());
                laporanTanahTemp.setJarakSempadanJalanraya(laporanTanah.getJarakSempadanJalanraya());
                laporanTanahTemp.setNamaSempadanKeretapi(laporanTanah.getNamaSempadanKeretapi());
                laporanTanahTemp.setJarakSempadanKeretapi(laporanTanah.getJarakSempadanKeretapi());
                laporanTanahTemp.setNamaSempadanLaut(laporanTanah.getNamaSempadanLaut());
                laporanTanahTemp.setJarakSempadanLaut(laporanTanah.getJarakSempadanLaut());
                laporanTanahTemp.setNamaSempadanSungai(laporanTanah.getNamaSempadanSungai());
                laporanTanahTemp.setJarakSempadanSungai(laporanTanah.getJarakSempadanSungai());
                laporanTanahTemp.setNamaSempadanlain(laporanTanah.getNamaSempadanlain());
                laporanTanahTemp.setJarakSempadanLain(laporanTanah.getJarakSempadanLain());
                laporanTanahTemp.setJenisJalan(laporanTanah.getJenisJalan());
                laporanTanahTemp.setAdaJalanMasuk(laporanTanah.getAdaJalanMasuk());
                laporanTanahTemp.setCatatanJalanMasuk(laporanTanah.getCatatanJalanMasuk());
                disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSempadanNew.jsp").addParameter("tab", "true");

    }

    public Resolution simpanPermohonanSblm() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        prmhnn = disLaporanTanahService.getPermohonanDAO().findById(id2);
        cawangan = permohonan.getCawangan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        permohonan.setPermohonanSebelum(prmhnn);
        permohonanManual = disLaporanTanahService.getPlpservice().findByIdMohonFailNo(idPermohonan, id2);

        InfoAudit infoAudit = new InfoAudit();
        if (permohonanManual != null) {
            permohonanManual.setInfoAudit(disLaporanTanahService.findAudit(permohonanManual, "update", peng));
        } else {
            permohonanManual = new PermohonanManual();

            permohonanManual.setInfoAudit(disLaporanTanahService.findAudit(permohonanManual, "new", peng));
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setNoFail(id2);
            permohonanManual.setCawangan(cawangan);
        }
        disLaporanTanahService.getPlpservice().simpanPermohonanManual(permohonanManual);

        addSimpleMessage("Data Telah Berjaya dikemaskini");
        rehydrate();
        return new JSP(DisPermohonanPage.getLT_PTANAH_PAGE()).addParameter("tab", "true");

    }

    public Resolution deleteRowLatarBelakang() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        if (idMohonHakmilik == null) {
            idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        }
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        }
        String forwardJSP = new String();

        if (idKand != null && tName != null) {

            ImejLaporan il = pelupusanService.findImejLaporanByIdLaporTanahSpdn(Long.parseLong(idKand));

            if (il != null) {
                pelupusanService.delImejLaporanByIdLaporTanahSpdn(Long.parseLong(idKand));
            }
            disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor);
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
//        rehydrate();
//        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
        return latarBelakangReload(idMohonHakmilik);
//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {

            ImejLaporan il = pelupusanService.findImejLaporanByIdLaporTanahSpdn(Long.parseLong(idKand));

            if (il != null) {
                pelupusanService.delImejLaporanByIdLaporTanahSpdn(Long.parseLong(idKand));
            }
            disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor);
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        rehydrate();
//        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
        return lotSempadan();
//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        perihalHakmilik = (String) getContext().getRequest().getParameter("perihalHakmilik");
        Hakmilik hakmilik = disLaporanTanahService.getHakmilikDAO().findById(perihalHakmilik);

        if (hakmilik == null) {
            addSimpleError("Maklumat hakmilik tidak dijumpai");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP(DisPermohonanPage.getLT_PTANAH_PAGE()).addParameter("popup", "true");
        } else {
            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
                hakmilikPermohonan = disLaporanTanahService.getPlpservice().findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PTGSA") || permohonan.getKodUrusan().getKod().equals("PHLA")) {
                List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikTerlibat = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                if (senaraiHakmilikTerlibat.size() > 0) {
                    hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                }
            } else {
                hakmilikPermohonan = disLaporanTanahService.getPlpservice().findByIdPermohonan(idPermohonan);
            }
            if (hakmilikPermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "new", pguna));
            } else {
                hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "update", pguna));
            }
            hakmilikPermohonan.setHakmilik(hakmilik);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PHLA")) {
            hakmilikPermohonanList = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
        } else {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP(DisPermohonanPage.getLT_PTANAH_PAGE()).addParameter("tab", "true");
    }

    public Resolution deleteImage() {
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOG.info("id Dokumen :== " + idDokumen);
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String forwardJSP = new String();
        if (!type.isEmpty()) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
            try {
                disLaporanTanahService.delObject("laporanImej", new String[]{String.valueOf(idImej)}, new String());
                disLaporanTanahService.delObject("dokumen", new String[]{String.valueOf(idDokumen)}, new String());
                tx.commit();
                if (!imageType.isEmpty()) {
                    pandanganImej = imageType.charAt(0);
                }
//                forwardJSP = refreshData(type);

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
        } else {
            addSimpleError("Type Tidak Dijumpai. Sila Hubungi Pentadbir Sistem Untuk Maklumat Lanjut");
        }

        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
            LOG.info("imejList del : " + hakmilikImejLaporanList.size());
        }

//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("popup", "true");
        return perihalTanahUpload(idMohonHakmilik);
    }

    public Resolution deleteImageArah() {
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOG.info("id Dokumen :== " + idDokumen);
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String forwardJSP = new String();
        if (!type.isEmpty()) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
            try {
                disLaporanTanahService.delObject("laporanImej", new String[]{String.valueOf(idImej)}, new String());
                disLaporanTanahService.delObject("dokumen", new String[]{String.valueOf(idDokumen)}, new String());
                tx.commit();
                if (!imageType.isEmpty()) {
                    pandanganImej = imageType.charAt(0);
                }
//                forwardJSP = refreshData(type);

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
        } else {
            addSimpleError("Type Tidak Dijumpai. Sila Hubungi Pentadbir Sistem Untuk Maklumat Lanjut");
        }

        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
            LOG.info("imejList del : " + hakmilikImejLaporanList.size());
        }

//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("popup", "true");
        return perihalTanahUpload(idMohonHakmilik);
    }

    public Resolution cariPermohonan() {
        if (id == null || id.equals("")) {
            addSimpleError("Masukkan Permohonan Id");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
        }
        prmhnn = disLaporanTanahService.getPermohonanDAO().findById(id);
        if (prmhnn != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
            pemohon = disLaporanTanahService.getPlpservice().findPemohonByIdPemohon(prmhnn.getIdPermohonan());
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            addSimpleMessage("Maklumat dijumpai");
            id2 = id;
        } else {
            addSimpleError("Maklumat tidak dijumpai");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
        }
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu_idMohon.jsp").addParameter("popup", "true");
    }

    public Resolution permohonanTerdahuluPopup() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pmList = pelupusanService.findByIdMohonlikeDIS(idPermohonan);

        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution showFormPopUpTambah() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        noPtSementara = getContext().getRequest().getParameter("noPtSementara");

        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu_idMohon.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution showFormNoFail() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/laporan_tanahV2AddFail.jsp").addParameter("popup", "true");
    }

    public Resolution simpanFail() throws ParseException {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        permohonan = permohonanDAO.findById(idPermohonan);

        System.out.println("id permohonan ::::::: " + permohonan.getIdPermohonan());
        InfoAudit infoAudit = new InfoAudit();
        permohonanManual = new PermohonanManual();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        permohonanManual.setInfoAudit(infoAudit);
        permohonanManual.setNoFail(noFail);
        System.out.println("no failllll :::" + permohonanManual.getNoFail());
        permohonanManual.setIdPermohonan(permohonan);
        System.out.println("id mohonnnnnnnnnn :::" + permohonan.getIdPermohonan());
        permohonanManual.setCawangan(permohonan.getCawangan());

        pelupusanService.simpanPermohonanManual(permohonanManual);

        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution updateRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKand");
        LOG.info("idKand : " + idKand);
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");

        pm = pelupusanService.findByidMohonManual(Long.parseLong(idKand));
        per = pelupusanService.findIdPermohonanByIdManualSD(pm.getNoFail());

        if (per != null) {
            kodUrusan = per.getKodUrusan().getKod();
            keputusan = per.getKeputusan().getNama();
            keputusanOleh = per.getNamaPenerima();
            tarikhKeputusan = sdf.format(per.getTarikhKeputusan());
            pmhn = per.getPenyerahNama();
            note = per.getSebab();
        }

//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/Update_laporan_tanahV2AddFail.jsp").addParameter("popup", "true");
    }

    public Resolution deleteRowPopUp() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKand");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {
            disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor);
        }
        addSimpleError("Maklumat Berjaya Dihapuskan");
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution updateSimpanFail() throws ParseException {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        permohonan = permohonanDAO.findById(idPermohonan);
        keputusan = getContext().getRequest().getParameter("keputusan");
        String no_Fail = getContext().getRequest().getParameter("no_Fail");
        PermohonanManual pml = pelupusanService.findByIdMohonFailNo(idPermohonan, no_Fail);
        per = pelupusanService.findIdPermohonanByIdManualSD(pml.getNoFail());
        InfoAudit infoAudit = new InfoAudit();

        if (per == null) {
            Permohonan p = new Permohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            p.setInfoAudit(infoAudit);
            p.setIdPermohonan(pml.getNoFail());
            p.setCawangan(permohonan.getCawangan());
            p.setStatus("SD");
            p.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
            if (keputusan.equals("L")) {
                p.setKeputusan(keputusanDAO.findById(keputusan));
                p.setSebab(note);
            } else if (keputusan.equals("T")) {
                p.setKeputusan(keputusanDAO.findById(keputusan));
                p.setSebab(null);
            }
//            p.setKeputusanOleh(peng);
            p.setTarikhKeputusan(sdf.parse(tarikhKeputusan));
            p.setPenyerahNama(pmhn);
            p.setNamaPenerima(keputusanOleh);

            pelupusanService.simpanPermohonan(p);
        } else {
            infoAudit = per.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            per.setIdPermohonan(pml.getNoFail());
            per.setCawangan(permohonan.getCawangan());
            per.setStatus("SD");
            per.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
            if (keputusan.equals("L")) {
                per.setKeputusan(keputusanDAO.findById(keputusan));
                per.setSebab(note);
            } else if (keputusan.equals("T")) {
                per.setKeputusan(keputusanDAO.findById(keputusan));
                per.setSebab(null);
            }
//            per.setKeputusanOleh(peng);
            per.setTarikhKeputusan(sdf.parse(tarikhKeputusan));
            per.setPenyerahNama(pmhn);
            per.setNamaPenerima(keputusanOleh);

            pelupusanService.simpanPermohonan(per);
        }

        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_pelan/laporan_pelanEdit/Update_laporan_pelanV2AddFail.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKawasan() throws ParseException {
//        PermohonanLaporanKawasan kws = new PermohonanLaporanKawasan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        InfoAudit infoAudit = new InfoAudit();
//        PermohonanLaporanKawasan kws = pelupusanService.findLaporanKawasanByIdPermohonanNidMHSingle(idPermohonan, Long.parseLong(idMohonHakmilik));
        if (idMohonlaporKws != null) {
            permohonanLaporanKawasan = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));

//        if (kws != null) {
//
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            PermohonanLaporanKawasan mhnLaporKws = new PermohonanLaporanKawasan();
            KodRizab kr = disLaporanTanahService.getKodRizabDAO().findById(rizabKwsn);
            if (kr != null) {
                permohonanLaporanKawasan.setKodRizab(kr);
            }
            permohonanLaporanKawasan.setCatatan(catatanKwsn);
            permohonanLaporanKawasan.setNoWarta(noWartaKwsn);
            if (trhWartaKwsn != null && !("").equals(trhWartaKwsn)) {
                permohonanLaporanKawasan.setTarikhWarta(trhWartaKwsn);
            }
            permohonanLaporanKawasan.setNoPelanWarta(noPelankawasan);
            permohonanLaporanKawasan.setPermohonan(permohonan);
            permohonanLaporanKawasan.setKodCawangan(permohonan.getCawangan());
//            permohonanLaporanKawasan.setHakmilikPermohonan(mohonHakmilik);
            permohonanLaporanKawasan.setInfoAudit(infoAudit);
            permohonanLaporanKawasanDAO.saveOrUpdate(permohonanLaporanKawasan);
//             permohonanLaporanKawasanDAO.saveOrUpdate(mhnLaporKws);

        } else {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
            PermohonanLaporanKawasan mhnLaporKws = new PermohonanLaporanKawasan();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            KodRizab kr = disLaporanTanahService.getKodRizabDAO().findById(rizabKwsn);
            if (kr != null) {
                mhnLaporKws.setKodRizab(kr);
            }

            mhnLaporKws.setCatatan(catatanKwsn);
            mhnLaporKws.setNoWarta(noWartaKwsn);
            if (trhWartaKwsn != null && !("").equals(trhWartaKwsn)) {
                mhnLaporKws.setTarikhWarta(trhWartaKwsn);
            }

            mhnLaporKws.setNoPelanWarta(noPelankawasan);
            mhnLaporKws.setPermohonan(permohonan);
            mhnLaporKws.setKodCawangan(permohonan.getCawangan());
            mhnLaporKws.setHakmilikPermohonan(hakmilikPermohonan);
            mhnLaporKws.setInfoAudit(infoAudit);
            permohonanLaporanKawasanDAO.saveOrUpdate(mhnLaporKws);
        }

        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMohonHakmilik));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
//        permohonanLaporanKawasan = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNidMH(idPermohonan, Long.parseLong(idMohonHakmilik));
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
        if (senaraiLaporanKawasan.size() > 0) {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahTambahKawasanNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
        } else {
            return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahTambahKawasanNew.jsp").addParameter("popup", "false");
        }
    }

    public Resolution simpanKandungan() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        isiPaduBatu = getContext().getRequest().getParameter("isiPaduBatu");
        tempohDisyor = getContext().getRequest().getParameter("tempohDisyor");
        cagarJalan = getContext().getRequest().getParameter("cagarJalan");
        kuponQty = getContext().getRequest().getParameter("kuponQty");
        unitTempohUOM = getContext().getRequest().getParameter("unitTempohUOM");
        kupon = getContext().getRequest().getParameter("kupon");
        if (idMohonHakmilik == null) {
            idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        }
        if (idLaporTanah == null) {
            idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        }

        LOG.info("idLaporTanah = " + idLaporTanah);
        LOG.info("idMohonHakmilik = " + idMohonHakmilik);
        int index = 0;
        boolean updateDB = false;
        boolean updateDB2 = false;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        String forwardJSP = new String();

        switch (index) {
            case 1:

                break;
            case 5:// MOHON LAPOR ULAS
//                forwardJSP = this.getSyorPPT();
                if (permohonan.getKodUrusan().getKod().equals("PPBB")) {
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    updatePermohonan();
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                    updateMohonPermitItem();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    updatePermohonan();
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                    updateMohonPermitItem();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonPermitItem();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn.equals("SU")) {
                        updateMohonPermitItem();
                    }
                    if (disLaporanTanahService.getConf().getKodNegeri().equals("05")) {
                        updateMohonTuntutKos();
                    }
//                    updateNoPT();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("MCMCL")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                    updateMohonHakmilik();
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PRIZ")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PLPT")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PRMMK")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PHLA") || permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PBRZ")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PBHL")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PTBTC")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
                    System.err.println(permohonan.getKodUrusan().getKod());
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn.equals("SU")) {
                        updateMohonPermitItem();
                    }
//                    updateMohonTuntutKos(); No need 
                    updateNoPT();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("MMRE")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PJBTR")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PLPTD")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonTuntutKos();
                    updateMohonPermitItem();
                } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                    updateMohonPermitItem();
                    updatePermohonanRLPS();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                    updateDB2 = updateMohonFasa();
                    //updateMohonHakmilik();
                    //updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                    updateDB = updateKandunganUlas(ulsn, "PTPBPSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("PCRG")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn.equals("SU")) {
                        updateMohonPermitItem();
                    }
                    if (disLaporanTanahService.getConf().getKodNegeri().equals("05")) {
                        updateMohonTuntutKos();
                    }
                    updateNoPT();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("LPJH")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn != null) {
                        if (ksn.equals("SU")) {
                            updateMohonPermitItem();
                        }
                    }
                    if (disLaporanTanahService.getConf().getKodNegeri().equals("05")) {
                        updateDB2 = updateMohonFasa();
                        updateMohonHakmilik();
                        updateMohonTuntutKos();
                    }
                    updateNoPT();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("LSTP")) {
                    updateDB2 = updateMohonFasa();
                    updateBahanBatu();
                    updateMohonTuntutKos();
                } else {
                    updateDB2 = updateMohonFasa();
                }

                updateDB = updateKandunganUlas(kand, "PPT");
                break;
            default:
                break;
        }
//        forwardJSP = refreshData("syorPPT");
//        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
//        return new JSP(forwardJSP).addParameter("tab", Boolean.TRUE);
        return syorPopapRehydrate(idMohonHakmilik, idLaporTanah);
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/laporan_tanahSyorNew.jsp").addParameter("popup", "true").addParameter("showForm", "false");
//        return new JSP(SYOR_PAGE).addParameter("tab", "true").addParameter("msg","Maklumat Berjaya Disimpan");
    }

    public void checkSeksyen(KodBandarPekanMukim kod) {
        try {
            String query = "Select u from etanah.model.KodSeksyen u where u.kodBandarPekanMukim.kod = :kod ";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setInteger("kod", kod.getKod());
            if (q.list().size() > 0) {
                forSeksyen = true;
                forBPM = true;
            } else {
                forSeksyen = false;
                forBPM = true;
                hakmilikPermohonan.setKodSeksyen(null);
            }

        } finally {
        }
    }

    public void updatePermohonan() {
        Permohonan permohonanSave = new Permohonan();
        permohonanSave = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        if (permohonanSave != null) {
            if (!StringUtils.isEmpty(catatan)) {
                permohonanSave.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonan(permohonanSave);
            }
        }
    }

    public void updatePermohonanRLPS() {
        Permohonan permohonanSave = new Permohonan();
        permohonanSave = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        System.out.println("catatan :" + permohonan.getCatatan());
        if (permohonanSave != null) {
            if (!StringUtils.isEmpty(catatan)) {

                permohonanSave.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonan(permohonanSave);
            }
        }
    }

    public void updateNoPT() {
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        NoPt noPt = new NoPt();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{noPtSementara}, 2);
        } else {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
        }
        NoPt noPTSave = disLaporanTanahService.getPlpservice().findNoPtByIdHakmilikPermohonan(mohonHakmilik.getId());
        if (noPTSave != null) {
            noPTSave.setInfoAudit(disLaporanTanahService.findAudit(noPTSave, "update", pengguna));
        } else {
            noPTSave = new NoPt();
            noPTSave.setInfoAudit(disLaporanTanahService.findAudit(noPTSave, "new", pengguna));
        }
        kodSU = ctx.getRequest().getParameter("kodSU");
        if (kodSU != null) {
            KodUOM kuom = disLaporanTanahService.getKodUOMDAO().findById(kodSU);
            noPTSave.setKodUOM(kuom);
        }
        //Added by Aizuddin to save kodU to noPT also
        kodU = ctx.getRequest().getParameter("kodU");
        if (permohonan.getKodUrusan().getKod().equals("PBMT") || (permohonan.getKodUrusan().getKod().equals("PBGSA"))) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = disLaporanTanahService.getKodUOMDAO().findById(kodU);
                LOG.info("&&&&&& Kod UOM kat noPT &&&&&&&&" + kodUOM);
                noPTSave.setKodUOM(kodUOM);
            } else {
                addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = disLaporanTanahService.getKodUOMDAO().findById(kodU);
                LOG.info("&&&&&& Kod UOM kat noPT &&&&&&&&" + kodUOM);
                noPTSave.setKodUOM(kodUOM);
            } else {
                addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
            }
        }

        noPTSave.setLuasDilulus(noPT.getLuasDilulus());
        noPTSave.setHakmilikPermohonan(mohonHakmilik);
        disLaporanTanahService.getPlpservice().simpanNoPt(noPTSave);
    }

    public void updateMohonPermitItem() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = disLaporanTanahService.getPlpservice().findPermohonanPermitItemByIdMohonList(idPermohonan);

        for (PermohonanPermitItem ppi : senaraiMohonPermitItem) {
//             && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("LN")
            if (ppi.getKodItemPermit() != null) {
                if (!ppi.getKodItemPermit().getKod().equalsIgnoreCase("KB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("PB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("MB")) {
                    PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                    if (ppi.getKodItemPermit() != null) {
                        permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan(), ppi.getKodItemPermit().getKod()}, 1);
                    } else {
                        permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan()}, 0);
                    }
                    if (permohonanPermitItemSave != null) {
                        permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "update", pengguna));
                    } else {
                        permohonanPermitItemSave = new PermohonanPermitItem();
                        permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                        permohonanPermitItemSave.setPermohonan(permohonan);
                        permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                    }

                    if (!StringUtils.isEmpty(keg)) {
                        KodItemPermit kip = new KodItemPermit();
                        kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                        if (kip != null) {
                            permohonanPermitItemSave.setKodItemPermit(kip);
                            disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
                        }
                    }
                }
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("RLPS") || permohonan.getKodUrusan().getKod().equals("PRMP")) {
            System.err.println(senaraiMohonPermitItem.size());
            InfoAudit info = new InfoAudit();
            PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
            if (senaraiMohonPermitItem.size() == 0) {
                System.out.println("RLPS keg :" + keg);
                permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                permohonanPermitItemSave.setPermohonan(permohonan);
                permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                    }
                }
                disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
            } else {
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanPermitItemSave = pelupusanService.findByIdMohonPermitItem(idPermohonan);
                permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "update", pengguna));
                permohonanPermitItemSave.setPermohonan(permohonan);
                permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                    }
                }
                disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
            }
        }
    }

    public Boolean updateMohonHakmilik() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        kodU = getContext().getRequest().getParameter("kodU");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        NoPt noPt = new NoPt();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
//            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        } else {
            System.out.println("hakmilikPermohonanSave idPermohonan :" + idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                hakmilikPermohonanSave = pelupusanService.findIdMohonKodSyaratBaru(idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
                for (HakmilikPermohonan hp : hpList) {
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohPajakan() != null) {
                        hp.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasDiluluskan() != null) {
                        hp.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
                    }

                    if (!StringUtils.isEmpty(kodU)) {
                        KodUOM kodUOM = new KodUOM();
                        kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                        LOG.info("&&&&&& Kod UOM kat hakmilikPermohonan &&&&&&&&" + kodUOM);
                        hp.setLuasLulusUom(kodUOM);
                    } else {
                        LOG.info("KODU : " + kodU);
                        if (!disLaporanTanahService.getConf().getKodNegeri().equals("05")) {
                            if (!permohonan.getKodUrusan().getKod().equals("PJLB") || !permohonan.getKodUrusan().getKod().equals("PLPT")) {
                                if (ksn != null) {
                                    if (ksn.equals("SL")) {
                                        addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
                                    }
                                }

                            }
                        }
                    }

//                    LOG.info("hakmilikPermohonan.getStatusLuasDiluluskan() : " + hakmilikPermohonan.getStatusLuasDiluluskan());
                    if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getStatusLuasDiluluskan())) {
                        hp.setStatusLuasDiluluskan(hakmilikPermohonan.getStatusLuasDiluluskan());
                    }

                    kodHmlk = getContext().getRequest().getParameter("kodHmlk");
                    if (!StringUtils.isEmpty(kodHmlk)) {
                        KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHmlk);
                        if ((permohonan.getKodUrusan().getKod().equals("BMBT")) || (permohonan.getKodUrusan().getKod().equals("PTBTC")) || (permohonan.getKodUrusan().getKod().equals("PTBTS"))) {
                            hp.setKodHakmilik(khm);
                        } else {
                            hp.setKodHakmilik(khm);
                        }
                    }
//                    keteranganKadarPremium = getContext().getRequest().getParameter("keteranganKadarPremium");
                    if (!StringUtils.isEmpty(keteranganKadarPremium)) {
                        hp.setKeteranganKadarPremium(keteranganKadarPremium);
                    }

//                    dendaPremium = getContext().getRequest().getParameter("dendaPremium");
                    if (!StringUtils.isEmpty(dendaPremium)) {
                        BigDecimal dendaPremium1 = new BigDecimal(dendaPremium);
                        hp.setDendaPremium(dendaPremium1);
                    }

//        tempohPajakan = getContext().getRequest().getParameter("tempohPajakan");
                    if (tempohPajakan != null) {
                        hp.setTempohPajakan(tempohPajakan);
                    }

                    if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
                        hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getCukaiPerMeterPersegi() != null) {
                        hp.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
                    }
                    if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
                        hp.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
                        hp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                        hp.setLuasDiluluskan(hakmilikPermohonan.getLuasTerlibat());
                    }

                    katTanahPilihan = getContext().getRequest().getParameter("katTanahPilihan");
                    System.out.println(katTanahPilihan);
                    if (!StringUtils.isEmpty(katTanahPilihan)) {
                        KodKategoriTanah kktb = new KodKategoriTanah();
                        kktb = (KodKategoriTanah) disLaporanTanahService.findObject(kktb, new String[]{katTanahPilihan}, 0);
                        if (kktb != null) {
                            hp.setKategoriTanahBaru(kktb);

                        }
                        kodGunaTanah = getContext().getRequest().getParameter("kodGunaTanah");
                        if (!StringUtils.isEmpty(kodGunaTanah)) {
                            KodKegunaanTanah kgt = new KodKegunaanTanah();
                            kgt = (KodKegunaanTanah) disLaporanTanahService.findObject(kgt, new String[]{kodGunaTanah}, 0);
                            if (kgt != null && kktb != null) {
                                hp.setKodKegunaanTanah(kgt);
                            }
                        }
                    }
                    System.out.println("hakmilikPermohonanSave.getId() ~ " + hp.getId());
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hp);

                }

            } else {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PJTK")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
            System.out.println("hakmilikPermohonanSave :" + hakmilikPermohonanSave.getId());
        }

        if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
            System.out.println("hakmilikPermohonanSave :" + hakmilikPermohonanSave.getId());
        }

        if (permohonan.getKodUrusan().getKod().equals("PCRG")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
            System.out.println("hakmilikPermohonanSave PCRG:::" + hakmilikPermohonanSave.getId());
        }

        if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohPajakan() != null) {
            hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
        }
        if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasDiluluskan() != null) {
            hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
        }
        if (!permohonan.getKodUrusan().getKod().equals("BMRE") || permohonan.getKodUrusan().getKod().equals("WMRE") || permohonan.getKodUrusan().getKod().equals("MMRE")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                LOG.info("&&&&&& Kod UOM kat hakmilikPermohonan &&&&&&&&" + kodUOM);

                if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                    hakmilikPermohonanSave.setKodUnitLuas(kodUOM);
                    hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
                    hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasTerlibat());
                } else {
                    hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
                }
            } else {
                LOG.info("KODU : " + kodU);
                if (!disLaporanTanahService.getConf().getKodNegeri().equals("05")) {
                    if (!permohonan.getKodUrusan().getKod().equals("PJLB")) {
                        if (!permohonan.getKodUrusan().getKod().equals("PLPT")) {
                            if (ksn != null) {
                                if (ksn.equals("SL")) {
                                    addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
                                }
                            }

                        }
                    }
                }
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("PRMMK")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                LOG.info(":::: Kod UOM PRMMK for hakmilikPermohonan :: " + kodUOM.getKod());
                hakmilikPermohonanSave.setKodUnitLuas(kodUOM);
            }
            if (!StringUtils.isEmpty(kodUomLuasLulus)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodUomLuasLulus}, 0);
                LOG.info(":::: Kod UomLuasLulus PRMMK for hakmilikPermohonan :: " + kodUOM.getKod());
                hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
            }
        }

        if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getStatusLuasDiluluskan())) {
            hakmilikPermohonanSave.setStatusLuasDiluluskan(hakmilikPermohonan.getStatusLuasDiluluskan());
        }

        kodHmlk = getContext().getRequest().getParameter("kodHmlk");
        if (!StringUtils.isEmpty(kodHmlk)) {
            KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHmlk);
            if ((permohonan.getKodUrusan().getKod().equals("BMBT")) || (permohonan.getKodUrusan().getKod().equals("PTBTC")) || (permohonan.getKodUrusan().getKod().equals("PTBTS"))) {
                hakmilikPermohonanSave.setKodHakmilik(khm);
            } else {
                hakmilikPermohonanSave.setKodHakmilik(khm);
            }
        }
        //       keteranganKadarPremium = getContext().getRequest().getParameter("keteranganKadarPremium");
        if (!StringUtils.isEmpty(keteranganKadarPremium)) {
            hakmilikPermohonanSave.setKeteranganKadarPremium(keteranganKadarPremium);
        }

        //    dendaPremium = getContext().getRequest().getParameter("dendaPremium");
        if (!StringUtils.isEmpty(dendaPremium)) {
            BigDecimal dendaPremium1 = new BigDecimal(dendaPremium);
            hakmilikPermohonanSave.setDendaPremium(dendaPremium1);
        }

//        tempohPajakan = getContext().getRequest().getParameter("tempohPajakan");
        if (tempohPajakan != null) {
            hakmilikPermohonanSave.setTempohPajakan(tempohPajakan);
        }

        if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
            hakmilikPermohonanSave.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
        }
        if (hakmilikPermohonan != null && hakmilikPermohonan.getCukaiPerMeterPersegi() != null) {
            hakmilikPermohonanSave.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
        }
        agensiUpahUkur = getContext().getRequest().getParameter("agensiUpahUkur");
        if (!StringUtils.isEmpty(agensiUpahUkur)) {
            hakmilikPermohonanSave.setAgensiUpahUkur(agensiUpahUkur);
        } else if (hakmilikPermohonan != null && !StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
            hakmilikPermohonanSave.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
        }
        if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
            hakmilikPermohonanSave.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
        }

//        syaratNyata = getContext().getRequest().getParameter("syaratNyata");
//        if (!StringUtils.isEmpty(syaratNyata)) {
//            KodSyaratNyata syarat = kodSyaratNyataDAO.findById(syaratNyata);
//            hakmilikPermohonanSave.setSyaratNyataBaru(syarat);
//        }
//        syaratNyata1 = getContext().getRequest().getParameter("syaratNyata1");
//        if (!StringUtils.isEmpty(syaratNyata1)) {
//            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(syaratNyata1);
//            hakmilikPermohonanSave.setSekatanKepentinganBaru(sekatan);
//        }

        katTanahPilihan = getContext().getRequest().getParameter("katTanahPilihan");
        System.out.println(katTanahPilihan);
        if (!StringUtils.isEmpty(katTanahPilihan)) {
            KodKategoriTanah kktb = new KodKategoriTanah();
            kktb = (KodKategoriTanah) disLaporanTanahService.findObject(kktb, new String[]{katTanahPilihan}, 0);
            if (kktb != null) {
                hakmilikPermohonanSave.setKategoriTanahBaru(kktb);

            }
            kodGunaTanah = getContext().getRequest().getParameter("kodGunaTanah");
            if (!StringUtils.isEmpty(kodGunaTanah)) {
                KodKegunaanTanah kgt = new KodKegunaanTanah();
                kgt = (KodKegunaanTanah) disLaporanTanahService.findObject(kgt, new String[]{kodGunaTanah}, 0);
                if (kgt != null && kktb != null) {
                    hakmilikPermohonanSave.setKodKegunaanTanah(kgt);
                }
            }
        }
        System.out.println(hakmilikPermohonanSave.getId());
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);

        return updateDB;
    }

    public void updateBahanBatu() {
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanBahanBatuan mohonBahanBatuSave = new PermohonanBahanBatuan();
        LOG.info("isiPaduBatu = " + isiPaduBatu);
        LOG.info("tempohDisyor = " + tempohDisyor);
        LOG.info("tempohDisyor = " + unitTempohUOM);

        mohonBahanBatuSave = pelupusanService.findByIdMBB(idPermohonan);

        if (!StringUtils.isEmpty(ksn) && ksn.equalsIgnoreCase("SL")) {
            if (mohonBahanBatuSave != null) {
                if (isiPaduBatu != null) {
                    BigDecimal isiPaduBatuValue = new BigDecimal(isiPaduBatu);
                    mohonBahanBatuSave.setJumlahIsipadu(isiPaduBatuValue);
                }
                if (tempohDisyor != null) {
                    mohonBahanBatuSave.setTempohDisyor(Integer.parseInt(tempohDisyor));
                }
                if (unitTempohUOM != null) {
                    KodUOM kod = kodUOMDAO.findById(unitTempohUOM);
                    mohonBahanBatuSave.setTempohSyorUom(kod);
                }
            } else {
                mohonBahanBatuSave = new PermohonanBahanBatuan();
                mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "new", pengguna));
                mohonBahanBatuSave.setPermohonan(permohonan);
                if (mohonHakmilik != null) {
                    mohonBahanBatuSave.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
                }
                if (isiPaduBatu != null) {
                    BigDecimal isiPaduBatuValue = new BigDecimal(isiPaduBatu);
                    mohonBahanBatuSave.setJumlahIsipadu(isiPaduBatuValue);
                }
                if (tempohDisyor != null) {
                    mohonBahanBatuSave.setTempohDisyor(Integer.parseInt(tempohDisyor));
                }
            }

            disLaporanTanahService.getPlpservice().simpanPermohonanBahanBatuan(mohonBahanBatuSave);
        }
    }

    public void updateMohonTuntutKos() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("cagarJalan = " + cagarJalan);
        LOG.info("kuponQty = " + kuponQty);

        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        HakmilikPermohonan mohonHM = new HakmilikPermohonan();
        NoPt noPt = new NoPt();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 1
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 2
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 5
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 5
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 12
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 10
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 12
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 9
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 13
                : 0;

        switch (numUrusan) {
            case 1:
                /*
                 * Add for Bayaran Kupon 
                 */

                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setKuantiti(Integer.valueOf(kuponQty));
                    if (kupon != null) {
                        BigDecimal BayaranKupon = new BigDecimal(kupon);
                        mohonTuntutKos.setAmaunTuntutan(BayaranKupon);
                    }

                    if (kodNegeri.equals("04")) {
                        BigDecimal seUnit = new BigDecimal(50);
                        mohonTuntutKos.setAmaunSeunit(seUnit);
                    } else {
                        BigDecimal seUnit = new BigDecimal(10);
                        mohonTuntutKos.setAmaunSeunit(seUnit);
                    }
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    // plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                    disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    if (kupon != null) {
                        BigDecimal BayaranKupon = new BigDecimal(kupon);
                        mohonTuntutKos.setAmaunTuntutan(BayaranKupon);
                    }
                    if (kodNegeri.equals("04")) {
                        BigDecimal seUnit = new BigDecimal(50);
                        mohonTuntutKos.setAmaunSeunit(seUnit);
                    } else {
                        BigDecimal seUnit = new BigDecimal(10);
                        mohonTuntutKos.setAmaunSeunit(seUnit);
                    }
                    mohonTuntutKos.setKuantiti(Integer.valueOf(kuponQty));
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                /* End for bayaran kupon
                 * 
                 */
                /*Add for CAGARAN JALAN
                 * 
                 */
                PermohonanTuntutanKos mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                mohonTuntutKosCagarJln = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
                cagaranJalan = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
                bayarankupon = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
                if (mohonTuntutKosCagarJln == null) {
                    mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "new", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    if (cagarJalan != null) {
                        BigDecimal cagaran = new BigDecimal(cagarJalan);
                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagaran);
                    }

////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanSavePermohonanTuntutanKos(mohonTuntutKosCagarJln);
                } else {
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "update", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    if (cagarJalan != null) {
                        BigDecimal cagaran = new BigDecimal(cagarJalan);
                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagaran);
                    }
//                    if (cagaranJalan != null) {
//                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagaranJalan.getAmaunTuntutan());
//                    } else {
//                        mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
//                    }

//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
                    if (cagaranJalan != null) {
                        mohonTuntutKosCagarJln.setKodTuntut(cagaranJalan.getKodTuntut());
                    } else {
                        mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    }
                    if (bayarankupon != null) {
                        mohonTuntutKosCagarJln.setKodTransaksi(bayarankupon.getKodTransaksi());
                    } else {
                        mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ").getKodKewTrans());
                    }

                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCagarJln);
                }
                /* 
                 *  End for bayaran kupon
                 */
                /*
                 * Add for bayaran LPS - For urusan LPSP
                 */
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    PermohonanTuntutanKos mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                    mohonTuntutKosBayaranLPS = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                    if (mohonTuntutKosBayaranLPS == null) {
                        mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "new", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKosBayaranLPS);
                    } else {
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "update", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosBayaranLPS);
                    }
                }
                /*
                 * End for bayaran LPS
                 */
                break;
            case 2:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                noPtSementara = getContext().getRequest().getParameter("noPtSementara");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;

            case 3:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                noPtSementara = getContext().getRequest().getParameter("noPtSementara");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                    mohonHM = pelupusanService.findIdMohonKodSyaratBaru(idPermohonan);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                if (disLaporanTanahService.getKodTuntutDAO().findById("DISLB") != null) {
                    mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getNama());
                    mohonTuntutKos.setAmaunTuntutan(amnt);
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISLB"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                break;
            case 4:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 5:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }

                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
                    for (HakmilikPermohonan hp : hpList) {
                        if (mohonTuntutKos == null) {
                            mohonTuntutKos = new PermohonanTuntutanKos();
                            mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                            mohonTuntutKos.setPermohonan(permohonan);
                            mohonTuntutKos.setCawangan(permohonan.getCawangan());

                        } else {
                            mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                        }
                        if (mohonHM != null) {
                            mohonTuntutKos.setHakmilikPermohonan(hp);
                        }
                        mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getNama());
                        mohonTuntutKos.setAmaunTuntutan(amnt);
                        mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A"));
                        mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getKodKewTrans());
                        disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                    }
                } else {
                    if (mohonTuntutKos == null) {
                        mohonTuntutKos = new PermohonanTuntutanKos();
                        mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                        mohonTuntutKos.setPermohonan(permohonan);
                        mohonTuntutKos.setCawangan(permohonan.getCawangan());

                    } else {
                        mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                    }
                    if (mohonHM != null) {
                        mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                    }
                    mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getNama());
                    mohonTuntutKos.setAmaunTuntutan(amnt);
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                break;
            case 6:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4D");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 7:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4E");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 8:
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                hakmilikPermohonan = pelupusanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                if (hakmilikPermohonan.getLuasLulusUom().getKod().equalsIgnoreCase("M")) {
                    String luasLulus1 = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                    BigDecimal luasLulus = new BigDecimal(luasLulus1.trim().replaceAll(",", ""));
                    LOG.info("luasLulus :: " + luasLulus);

                    pmi = new PermohonanPermitItem();
                    pmi = (PermohonanPermitItem) disLaporanTanahService.findObject(pmi, new String[]{idPermohonan}, 0);

                    if (pmi.getKodItemPermit() != null) {
                        if (pmi.getKodItemPermit().getRoyaltiTanahKerajaan() == null) {
                            royaltiTanahKerajaan = new BigDecimal(0);
                            amnt = royaltiTanahKerajaan.multiply(luasLulus);
                        } else {
                            royaltiTanahKerajaan = pmi.getKodItemPermit().getRoyaltiTanahKerajaan();
                            amnt = royaltiTanahKerajaan.multiply(luasLulus);
                        }
                    }
                } else if (hakmilikPermohonan.getLuasLulusUom().getKod().equalsIgnoreCase("H")) {
                    String luasLulus1 = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                    BigDecimal luasLulus = new BigDecimal(luasLulus1.trim().replaceAll(",", ""));
                    LOG.info("luasLulus :: " + luasLulus);

                    pmi = new PermohonanPermitItem();
                    pmi = (PermohonanPermitItem) disLaporanTanahService.findObject(pmi, new String[]{idPermohonan}, 0);

                    if (pmi.getKodItemPermit() != null) {
                        if (pmi.getKodItemPermit().getRoyaltiTanahKerajaan() == null) {
                            royaltiTanahKerajaan = new BigDecimal(0);

                            BigDecimal se = new BigDecimal(1000);
                            royaltiTanahKerajaan = royaltiTanahKerajaan.multiply(se);
                            amnt = royaltiTanahKerajaan.multiply(luasLulus);
                        } else {
                            royaltiTanahKerajaan = pmi.getKodItemPermit().getRoyaltiTanahKerajaan();

                            BigDecimal se = new BigDecimal(1000);
                            royaltiTanahKerajaan = royaltiTanahKerajaan.multiply(se);
                            amnt = royaltiTanahKerajaan.multiply(luasLulus);
                            LOG.info("amnt H :: " + amnt);
                        }
                    }
                }

                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB7"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 9:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 10:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISSTR");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 11:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB7"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 12:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonan(idPermohonan);
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                    noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 13:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                noPt = new NoPt();
                if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{noPtSementara}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
        }
    }

    public List<KodKegunaanTanah> getListGT() {
        return listGT;
    }

    public void setListGT(List<KodKegunaanTanah> listGT) {
        this.listGT = listGT;
    }

    /**
     *
     * @return
     */
    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs() {
        if (StringUtils.isNotBlank(katTanahPilihan)) {
            return pelupusanService.getSenaraiKegunaanTanah(katTanahPilihan);
        }
        return new ArrayList<KodKegunaanTanah>();
    }

    public void setSenaraiKodKegunaanTanahs(List<KodKegunaanTanah> senaraiKodKegunaanTanahs) {
        this.senaraiKodKegunaanTanahs = senaraiKodKegunaanTanahs;
    }

    public String getKatTanahPilihan() {
        return katTanahPilihan;
    }

    public void setKatTanahPilihan(String katTanahPilihan) {
        this.katTanahPilihan = katTanahPilihan;
    }

    public Boolean updateMohonFasa() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService service = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            //stageId = "laporan_tanah";
            System.out.println("-------------stageId--" + stageId);
        } else {
            stageId = "laporan_tanah";
        }

        if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
            stageId = "07SdknLapTnh";
        }

        KodCawangan cawangan = new KodCawangan();

        ksn = ctx.getRequest().getParameter("syorKpsn");
        cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
        fasaPermohonan = new FasaPermohonan();
        fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdAliranIdPengguna(idPermohonan, stageId, pengguna.getIdPengguna());
        if (fasaPermohonan != null) {
            fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "update", pengguna));
        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "new", pengguna));
        }
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        if (ksn != null) {
            KodKeputusan kkp = disLaporanTanahService.getKodKeputusanDAO().findById(ksn);
            fasaPermohonan.setKeputusan(kkp);
            if (ksn.equalsIgnoreCase("ST")) {
                fasaPermohonan.setUlasan(ulasan);
            }
        }
        fasaPermohonan.setIdAliran(stageId);
        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);

        return updateDB;
    }

    public Boolean updateKandunganUlas(String kand, String typeSyor) {

        boolean updateDB = false;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        sizeSenaraiLaporUlas = ctx.getRequest().getParameter("sizeSenaraiLaporUlas");
        List<PermohonanLaporanUlasan> senaraiLaporanKandungan2 = new ArrayList<PermohonanLaporanUlasan>();
        if (!StringUtils.isEmpty(typeSyor)) {
            if (typeSyor.equalsIgnoreCase("PPT") || typeSyor.equalsIgnoreCase("PPTKanan")) {
                if (typeSyor.equalsIgnoreCase("PPT")) {
                    senaraiLaporanKandungan2 = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "Ulasan PPT");
                } else if (typeSyor.equalsIgnoreCase("PPTKanan")) {
                    senaraiLaporanKandungan2 = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "PPTKanan");
                }
                for (PermohonanLaporanUlasan plu : senaraiLaporanKandungan2) {
                    String checkKand = String.valueOf(plu.getIdLaporUlas()) + "kandunganUlas";
                    String kandungan = ctx.getRequest().getParameter(checkKand);
                    if (!StringUtils.isEmpty(kandungan)) {
                        plu.setUlasan(kandungan);
                        plu.setInfoAudit(disLaporanTanahService.findAudit(plu, "update", pengguna));
                        disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(plu);
                    }
                }
            } else if (typeSyor.equalsIgnoreCase("PLPSSyarat")) {
//                if (!StringUtils.isEmpty(ulsn)) {
                PermohonanLaporanKandungan mohonLaporKandSave = new PermohonanLaporanKandungan();
                idHakmilik = ctx.getRequest().getParameter("idHakmilik");
                noPtSementara = ctx.getRequest().getParameter("noPtSementara");
                laporanTanah = new LaporanTanah();

                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    List<HakmilikPermohonan> hpList = pelupusanService.findByIdPermohonan1(idPermohonan);
                    for (HakmilikPermohonan hp : hpList) {
                        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hp, new String[]{idPermohonan, idHakmilik}, 0);
                        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                        noPtTemp = new NoPt();
//                        noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hp, new String[]{noPtSementara}, 2);
                        } else {
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hp, new String[]{idPermohonan}, 1);
                        }

                        if (hakmilikPermohonan != null) {
                            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hp.getId())}, 0);
                        }
                        mohonLaporKandSave = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Syarat Tambahan LPS", laporanTanah.getIdLaporan());
                        if (mohonLaporKandSave != null) {
                            mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "update", pengguna));
                        } else {
                            mohonLaporKandSave = new PermohonanLaporanKandungan();
                            mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "new", pengguna));
                            mohonLaporKandSave.setLaporanTanah(laporanTanah);

                        }
                        mohonLaporKandSave.setSubtajuk("Syarat Tambahan LPS");
                        mohonLaporKandSave.setKand(ulsn);
                        disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(mohonLaporKandSave);
                    }
                } else {
                    if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                    } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                        noPtTemp = new NoPt();
//                        noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
                    } else {
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                    }

                    if (hakmilikPermohonan != null) {
                        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
                    }
                    mohonLaporKandSave = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Syarat Tambahan LPS", laporanTanah.getIdLaporan());
                    if (mohonLaporKandSave != null) {
                        mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "update", pengguna));
                    } else {
                        mohonLaporKandSave = new PermohonanLaporanKandungan();
                        mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "new", pengguna));
                        mohonLaporKandSave.setLaporanTanah(laporanTanah);

                    }
                    mohonLaporKandSave.setSubtajuk("Syarat Tambahan LPS");
                    mohonLaporKandSave.setKand(ulsn);
                    disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(mohonLaporKandSave);
                }
//                }

            } else if (typeSyor.equalsIgnoreCase("PTPBPSyarat")) {
                if (!StringUtils.isEmpty(ulsn)) {
                    PermohonanLaporanKandungan mohonLaporKandSave = new PermohonanLaporanKandungan();
                    idHakmilik = ctx.getRequest().getParameter("idHakmilik");
                    noPtSementara = ctx.getRequest().getParameter("noPtSementara");
                    laporanTanah = new LaporanTanah();
                    if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                    } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//                        noPtTemp = new NoPt();
//                        noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
                    } else {
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                    }

                    if (hakmilikPermohonan != null) {
                        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
                    }
                    mohonLaporKandSave = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Syarat Tambahan PTPBP", laporanTanah.getIdLaporan());
                    if (mohonLaporKandSave != null) {
                        mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "update", pengguna));
                    } else {
                        mohonLaporKandSave = new PermohonanLaporanKandungan();
                        mohonLaporKandSave.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporKandSave, "new", pengguna));
                        mohonLaporKandSave.setLaporanTanah(laporanTanah);

                    }
                    mohonLaporKandSave.setSubtajuk("Syarat Tambahan PTPBP");
                    mohonLaporKandSave.setKand(ulsn);
                    disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(mohonLaporKandSave);
                }

            }

        }

        return updateDB;
    }

    public Resolution tambahRow() {

        PermohonanLaporanUlasan pkk = new PermohonanLaporanUlasan();
        String forwardJSP = new String();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        senaraiLaporanKandungan1 = disLaporanTanahService.getPlpservice().findUlasanByIdMohon(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (senaraiLaporanKandungan1 == null || senaraiLaporanKandungan1.size() <= 0) {
            senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
        }
        switch (index) {
            case 1:
                break;
            case 5: //Ulasan in SyorPPT
//                forwardJSP = refreshData("syorPPT");
                ksn = getContext().getRequest().getParameter("kodksn");
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                permohonanLaporanUlasan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanUlasan, "new", pengguna));
                permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
                permohonanLaporanUlasan.setCawangan(cawangan);
                permohonanLaporanUlasan.setPermohonan(permohonan);
                disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(permohonanLaporanUlasan);
                rehydrate();

                break;
            case 6:

                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                permohonanLaporanUlasan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanUlasan, "new", pengguna));
                permohonanLaporanUlasan.setJenisUlasan("PPTKanan");
                permohonanLaporanUlasan.setCawangan(cawangan);
                permohonanLaporanUlasan.setPermohonan(permohonan);
                disLaporanTanahService.getPelPtService().simpanPermohonanlaporanUlas(permohonanLaporanUlasan);
                rehydrate();
//                forwardJSP = refreshData("syorPPTKanan");
                break;
            default:
                break;
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution searchSyarat() {
        indexSyarat = getContext().getRequest().getParameter("index");
        String jenisSyarat = getContext().getRequest().getParameter("jenisSyarat");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = hakmilikPermohonanService.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
//        laporanTanah = hakmilikPermohonanService.findHakmilikPermohonanbyidMH(Long.parseLong(idLaporTanah));
        laporanTanah = disLaporanTanahService.getLaporanTanahDAO().findById(Long.valueOf(idLaporTanah));
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String forwardJSP = new String();
        disSyaratSekatan = new DisSyaratSekatan();
        if (!StringUtils.isEmpty(jenisSyarat)) {
            if (jenisSyarat.equalsIgnoreCase("nyata")) {
                disSyaratSekatan.setKodSyaratNyata(new String());
                disSyaratSekatan.setSyaratNyata2(new String());
                String kodSekatan = getContext().getRequest().getParameter("kodSktn");
                if (!StringUtils.isEmpty(kodSekatan)) {
                    KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                    kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{kodSekatan}, 0);
                    if (kodSK != null) {
                        disSyaratSekatan.setKodSktn(kodSK.getKod());
                        disSyaratSekatan.setSyaratSekatan(kodSK.getSekatan());
                    }
                }
                disSyaratSekatan.setListKodSyaratNyata(new ArrayList<KodSyaratNyata>());

                forwardJSP = "pelupusan/laporan_tanah/laporanTanahNew/searchSyaratNyata_New.jsp";
            } else if (jenisSyarat.equalsIgnoreCase("sekatan")) {
                disSyaratSekatan.setKodSekatan(new String());
                disSyaratSekatan.setSyaratSekatan(new String());
                String kodNyata = getContext().getRequest().getParameter("kodNyata");
                if (!StringUtils.isEmpty(kodNyata)) {
                    KodSyaratNyata kodSN = new KodSyaratNyata();
                    kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{kodNyata}, 0);
                    if (kodSN != null) {
                        disSyaratSekatan.setKod(kodSN.getKod());
                        disSyaratSekatan.setSyaratNyata(kodSN.getSyarat());
                    }
                }
                disSyaratSekatan.setListKodSekatan(new ArrayList<KodSekatanKepentingan>());

                forwardJSP = "pelupusan/laporan_tanah/laporanTanahNew/searchSekatanKpntngn_New.jsp";
            }
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();

        if (hakmilikPermohonan.getHakmilik() != null) {
            idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        }
        if (hakmilikPermohonan.getNoLot() != null) {
            noPtSementara = hakmilikPermohonan.getNoLot();
        }
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPtTemp = new NoPt();
//            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, noPtSementara}, 3);

            } else {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
            }

        } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT") || (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT"))) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                } else {
                    kc = peng.getKodCawangan().getKod();
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), kc, disSyaratSekatan.getSekatKpntgn2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Sekatan");
            }
        }
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/searchSekatanKpntngn_New.jsp").addParameter("popup", "true");
//        return new JSP(DisPermohonanPage.getLT_SEKATAN_PAGE()).addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        String kategoriTanah = new String();

//        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        if (hakmilikPermohonan.getHakmilik() != null) {
            idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        }
        if (hakmilikPermohonan.getNoLot() != null && !hakmilikPermohonan.getLot().getKod().equals("2")) {
            noPtSementara = hakmilikPermohonan.getNoLot();
        }
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPtTemp = new NoPt();
//            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
            //hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, noPtSementara}, 3);

            } else {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (kodNegeri.equals("05")) {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 1);
            } else {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
            }
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT") || (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBGSA"))) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                } else {
                    kc = peng.getKodCawangan().getKod();
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
            if (hakmilikPermohonanSave.getHakmilik() != null) {
                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
//                    kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
                    kategoriTanah = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                } else {
                    kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
                }

            } else {
                kategoriTanah = hakmilikPermohonanSave.getKategoriTanahBaru() != null ? hakmilikPermohonanSave.getKategoriTanahBaru().getKod() : new String();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSyaratNyata())) {
                if (disSyaratSekatan.getSyaratNyata2() != null) {
                    disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew(disSyaratSekatan.getKodSyaratNyata(), kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
                    LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSyaratNyata().size());
                    if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                        addSimpleError("Kod Syarat Nyata Tidak Sah");
                    }
                } else if (disSyaratSekatan.getKodSyaratNyata() != null) {
//                    disSyaratSekatan.setListKodSyaratNyata(pelupusanService.searchKodSyaratNyataOnly(disSyaratSekatan.getKodSyaratNyata()));
                }
            } else {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else if (kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PJLB")) {
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }
        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/searchSyaratNyata_New.jsp").addParameter("popup", "true");
//        return new JSP(DisPermohonanPage.getLT_SYARATNYATA_PAGE()).addParameter("popup", "true");
    }

    public Resolution simpanKodSekatan() {
        String forwardJSP = new String();
        if (hakmilikPermohonan != null) {
            idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
            if (disSyaratSekatan != null) {
                if (!StringUtils.isEmpty(disSyaratSekatan.getKodSktn())) {
                    KodSekatanKepentingan kodSekatan = new KodSekatanKepentingan();
                    String sekatan = disSyaratSekatan.getKodSktn();
                    kodSekatan = kodSekatanKepentinganDAO.findById(sekatan);
                    hakmilikPermohonan.setSekatanKepentinganBaru(kodSekatan);
                    hakmilikPermohonanService.save(hakmilikPermohonan);
                    addSimpleMessage("Kemasukan Berjaya");
                }
            }
        }

        return syorPopapRehydrate(idMohonHakmilik, idLaporTanah);
    }

    public Resolution simpanKodSyaratNyata() {
        String forwardJSP = new String();
//        forwardJSP = this.getSyorPPT();

//        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        if (hakmilikPermohonan != null) {
            idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
//            idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
            if (disSyaratSekatan != null) {
                if (!StringUtils.isEmpty(disSyaratSekatan.getKod())) {
                    KodSyaratNyata kodSN = new KodSyaratNyata();
                    String syarat = disSyaratSekatan.getKod();
                    kodSN = kodSyaratNyataDAO.findById(syarat);
                    hakmilikPermohonan.setSyaratNyataBaru(kodSN);
                    hakmilikPermohonanService.save(hakmilikPermohonan);
                    addSimpleMessage("Kemasukan Berjaya");
                }
            }

        }

//        return new JSP(forwardJSP).addParameter("tab", "true");
        return syorPopapRehydrate(idMohonHakmilik, idLaporTanah);
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/searchSyaratNyata_New.jsp").addParameter("popup", "true");
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public List<ImejLaporan> getHakmilikImejLaporanList() {
        return hakmilikImejLaporanList;
    }

    public void setHakmilikImejLaporanList(List<ImejLaporan> hakmilikImejLaporanList) {
        this.hakmilikImejLaporanList = hakmilikImejLaporanList;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getCatatanKeg() {
        return catatanKeg;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setCatatanKeg(String catatanKeg) {
        this.catatanKeg = catatanKeg;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan1() {
        return tanahrizabpermohonan1;
    }

    public void setTanahrizabpermohonan1(TanahRizabPermohonan tanahrizabpermohonan1) {
        this.tanahrizabpermohonan1 = tanahrizabpermohonan1;
    }

    public String getStatBdnPngwl() {
        return statBdnPngwl;
    }

    public void setStatBdnPngwl(String statBdnPngwl) {
        this.statBdnPngwl = statBdnPngwl;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public String getUlsn() {
        return ulsn;
    }

    public void setUlsn(String ulsn) {
        this.ulsn = ulsn;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public String getKodHmlkTetap() {
        return kodHmlkTetap;
    }

    public void setKodHmlkTetap(String kodHmlkTetap) {
        this.kodHmlkTetap = kodHmlkTetap;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public char getKodP() {
        return kodP;
    }

    public void setKodP(char kodP) {
        this.kodP = kodP;
    }

    public String getKodD() {
        return kodD;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public String getKegunaTanah() {
        return kegunaTanah;
    }

    public void setKegunaTanah(String kegunaTanah) {
        this.kegunaTanah = kegunaTanah;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
    }

    public String getKodPar() {
        return kodPar;
    }

    public void setKodPar(String kodPar) {
        this.kodPar = kodPar;
    }

    public DisPermohonanBahanBatu getDisPermohonanBahanBatu() {
        return disPermohonanBahanBatu;
    }

    public void setDisPermohonanBahanBatu(DisPermohonanBahanBatu disPermohonanBahanBatu) {
        this.disPermohonanBahanBatu = disPermohonanBahanBatu;
    }

    public String getTanahR() {
        return tanahR;
    }

    public void setTanahR(String tanahR) {
        this.tanahR = tanahR;
    }

    public String getKodT() {
        return kodT;
    }

    public void setKodT(String kodT) {
        this.kodT = kodT;
    }

    public String getKecerunanT() {
        return kecerunanT;
    }

    public void setKecerunanT(String kecerunanT) {
        this.kecerunanT = kecerunanT;
    }

    public String getKlasifikasiT() {
        return klasifikasiT;
    }

    public void setKlasifikasiT(String klasifikasiT) {
        this.klasifikasiT = klasifikasiT;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public BigDecimal getUsahaLuas() {
        return usahaLuas;
    }

    public void setUsahaLuas(BigDecimal usahaLuas) {
        this.usahaLuas = usahaLuas;
    }

    public Integer getUsahaBilanganPokok() {
        return usahaBilanganPokok;
    }

    public void setUsahaBilanganPokok(Integer usahaBilanganPokok) {
        this.usahaBilanganPokok = usahaBilanganPokok;
    }

    public BigDecimal getUsahaHarga() {
        return usahaHarga;
    }

    public void setUsahaHarga(BigDecimal usahaHarga) {
        this.usahaHarga = usahaHarga;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public String getKand() {
        return kand;
    }

    public void setKand(String kand) {
        this.kand = kand;
    }

    public String getKeadaankand() {
        return keadaankand;
    }

    public void setKeadaankand(String keadaankand) {
        this.keadaankand = keadaankand;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanLaporanUlasan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public String getUlasanKanan() {
        return ulasanKanan;
    }

    public void setUlasanKanan(String ulasanKanan) {
        this.ulasanKanan = ulasanKanan;
    }

    public String getPlpulasan0() {
        return plpulasan0;
    }

    public void setPlpulasan0(String plpulasan0) {
        this.plpulasan0 = plpulasan0;
    }

    public NoPt getNoPT() {
        return noPT;
    }

    public void setNoPT(NoPt noPT) {
        this.noPT = noPT;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public PermohonanPermitItem getPmi() {
        return pmi;
    }

    public void setPmi(PermohonanPermitItem pmi) {
        this.pmi = pmi;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPerihalTanah() {
        return senaraiHakmilikPerihalTanah;
    }

    public void setSenaraiHakmilikPerihalTanah(List<HakmilikPermohonan> senaraiHakmilikPerihalTanah) {
        this.senaraiHakmilikPerihalTanah = senaraiHakmilikPerihalTanah;
    }

    public DisLaporanTanahSempadan getDisLaporanTanahSempadan() {
        return disLaporanTanahSempadan;
    }

    public void setDisLaporanTanahSempadan(DisLaporanTanahSempadan disLaporanTanahSempadan) {
        this.disLaporanTanahSempadan = disLaporanTanahSempadan;
    }

    public PermohonanLaporanKandungan getPermohonanLaporanKandungan() {
        return permohonanLaporanKandungan;
    }

    public void setPermohonanLaporanKandungan(PermohonanLaporanKandungan permohonanLaporanKandungan) {
        this.permohonanLaporanKandungan = permohonanLaporanKandungan;
    }

    public int getbSize() {
        return bSize;
    }

    public void setbSize(int bSize) {
        this.bSize = bSize;
    }

    public int getsSize() {
        return sSize;
    }

    public void setsSize(int sSize) {
        this.sSize = sSize;
    }

    public int gettSize() {
        return tSize;
    }

    public void settSize(int tSize) {
        this.tSize = tSize;
    }

    public int getuSize() {
        return uSize;
    }

    public void setuSize(int uSize) {
        this.uSize = uSize;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public Permohonan getPrmhnn() {
        return prmhnn;
    }

    public void setPrmhnn(Permohonan prmhnn) {
        this.prmhnn = prmhnn;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIdLaporTanah() {
        return idLaporTanah;
    }

    public void setIdLaporTanah(String idLaporTanah) {
        this.idLaporTanah = idLaporTanah;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListEdit() {
        return hakmilikImejLaporanListEdit;
    }

    public void setHakmilikImejLaporanListEdit(List<ImejLaporan> hakmilikImejLaporanListEdit) {
        this.hakmilikImejLaporanListEdit = hakmilikImejLaporanListEdit;
    }

    public DisLaporTanahKawasan getDisLaporTanahKawasan() {
        return disLaporTanahKawasan;
    }

    public void setDisLaporTanahKawasan(DisLaporTanahKawasan disLaporTanahKawasan) {
        this.disLaporTanahKawasan = disLaporTanahKawasan;
    }

    public String getSizeSenaraiLaporUlas() {
        return sizeSenaraiLaporUlas;
    }

    public void setSizeSenaraiLaporUlas(String sizeSenaraiLaporUlas) {
        this.sizeSenaraiLaporUlas = sizeSenaraiLaporUlas;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public KodUOM getJumlahIsipaduUOM() {
        return jumlahIsipaduUOM;
    }

    public void setJumlahIsipaduUOM(KodUOM jumlahIsipaduUOM) {
        this.jumlahIsipaduUOM = jumlahIsipaduUOM;
    }

    public String getTempohSyorUOM() {
        return tempohSyorUOM;
    }

    public void setTempohSyorUOM(String tempohSyorUOM) {
        this.tempohSyorUOM = tempohSyorUOM;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanKandunganPPTKanan() {
        return senaraiLaporanKandunganPPTKanan;
    }

    public void setSenaraiLaporanKandunganPPTKanan(List<PermohonanLaporanUlasan> senaraiLaporanKandunganPPTKanan) {
        this.senaraiLaporanKandunganPPTKanan = senaraiLaporanKandunganPPTKanan;
    }

    public String getIndexSyarat() {
        return indexSyarat;
    }

    public void setIndexSyarat(String indexSyarat) {
        this.indexSyarat = indexSyarat;
    }

    public DisSyaratSekatan getDisSyaratSekatan() {
        return disSyaratSekatan;
    }

    public void setDisSyaratSekatan(DisSyaratSekatan disSyaratSekatan) {
        this.disSyaratSekatan = disSyaratSekatan;
    }

    public String getKodSU() {
        return kodSU;
    }

    public void setKodSU(String kodSU) {
        this.kodSU = kodSU;
    }

    public DisLaporanTanahController getDisLaporanTanahController() {
        return disLaporanTanahController;
    }

    public void setDisLaporanTanahController(DisLaporanTanahController disLaporanTanahController) {
        this.disLaporanTanahController = disLaporanTanahController;
    }

    public String getPerihalHakmilik() {
        return perihalHakmilik;
    }

    public void setPerihalHakmilik(String perihalHakmilik) {
        this.perihalHakmilik = perihalHakmilik;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getUnitTempohUOM() {
        return unitTempohUOM;
    }

    public void setUnitTempohUOM(String unitTempohUOM) {
        this.unitTempohUOM = unitTempohUOM;
    }

    public List<NoPt> getSenaraiNoPt() {
        return senaraiNoPt;
    }

    public void setSenaraiNoPt(List<NoPt> senaraiNoPt) {
        this.senaraiNoPt = senaraiNoPt;
    }

    public String getNoPtSementara() {
        return noPtSementara;
    }

    public void setNoPtSementara(String noPtSementara) {
        this.noPtSementara = noPtSementara;
    }

    public NoPt getNoPtTemp() {
        return noPtTemp;
    }

    public void setNoPtTemp(NoPt noPtTemp) {
        this.noPtTemp = noPtTemp;
    }

    public BigDecimal getLuasDilulus() {
        return luasDilulus;
    }

    public void setLuasDilulus(BigDecimal luasDilulus) {
        this.luasDilulus = luasDilulus;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        laporantanahNewActionBean.day = day;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public List<PermohonanLaporanPohon> getSenaraiPermohonanLaporanPohon() {
        return senaraiPermohonanLaporanPohon;
    }

    public void setSenaraiPermohonanLaporanPohon(List<PermohonanLaporanPohon> senaraiPermohonanLaporanPohon) {
        this.senaraiPermohonanLaporanPohon = senaraiPermohonanLaporanPohon;
    }

    public PermohonanLaporanPohon getPermohonanLaporanPohon() {
        return permohonanLaporanPohon;
    }

    public void setPermohonanLaporanPohon(PermohonanLaporanPohon permohonanLaporanPohon) {
        this.permohonanLaporanPohon = permohonanLaporanPohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public List<PermohonanLaporanUsaha> getListPermohonanLaporanUsaha() {
        return listPermohonanLaporanUsaha;
    }

    public void setListPermohonanLaporanUsaha(List<PermohonanLaporanUsaha> listPermohonanLaporanUsaha) {
        this.listPermohonanLaporanUsaha = listPermohonanLaporanUsaha;
    }

    public PermohonanLaporanUsaha getPermohonanLaporanUsaha() {
        return permohonanLaporanUsaha;
    }

    public void setPermohonanLaporanUsaha(PermohonanLaporanUsaha permohonanLaporanUsaha) {
        this.permohonanLaporanUsaha = permohonanLaporanUsaha;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public List<KodDUN> getListKodDun() {
        return listKodDun;
    }

    public void setListKodDun(List<KodDUN> listKodDun) {
        this.listKodDun = listKodDun;
    }

    public Integer getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(Integer tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public List<DisPermohonanTanahTerdahulu> getListpermohonanTanahTerdahulu() {
        return listpermohonanTanahTerdahulu;
    }

    public void setListpermohonanTanahTerdahulu(List<DisPermohonanTanahTerdahulu> listpermohonanTanahTerdahulu) {
        this.listpermohonanTanahTerdahulu = listpermohonanTanahTerdahulu;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKodUomLuasLulus() {
        return kodUomLuasLulus;
    }

    public void setKodUomLuasLulus(String kodUomLuasLulus) {
        this.kodUomLuasLulus = kodUomLuasLulus;
    }

    public String getMercuTanda() {
        return mercuTanda;
    }

    public void setMercuTanda(String mercuTanda) {
        this.mercuTanda = mercuTanda;
    }

    public long getIdImej() {
        return idImej;
    }

    public void setIdImej(long idImej) {
        this.idImej = idImej;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<PermohonanManual> getPmList() {
        return pmList;
    }

    public void setPmList(List<PermohonanManual> pmList) {
        this.pmList = pmList;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public PermohonanManual getPm() {
        return pm;
    }

    public void setPm(PermohonanManual pm) {
        this.pm = pm;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKeputusanOleh() {
        return keputusanOleh;
    }

    public void setKeputusanOleh(String keputusanOleh) {
        this.keputusanOleh = keputusanOleh;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Permohonan getPer() {
        return per;
    }

    public void setPer(Permohonan per) {
        this.per = per;
    }

    public String getPmhn() {
        return pmhn;
    }

    public void setPmhn(String pmhn) {
        this.pmhn = pmhn;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public ImejLaporan getImejL() {
        return imejL;
    }

    public void setImejL(ImejLaporan imejL) {
        this.imejL = imejL;
    }

    public List<Hakmilik> getSenaraiHakm() {
        return senaraiHakm;
    }

    public void setSenaraiHakm(List<Hakmilik> senaraiHakm) {
        this.senaraiHakm = senaraiHakm;
    }

    public String getUsahaLuasUom() {
        return usahaLuasUom;
    }

    public void setUsahaLuasUom(String usahaLuasUom) {
        this.usahaLuasUom = usahaLuasUom;
    }

    public List<KodItemPermit> getKodItemPermitList() {
        return kodItemPermitList;
    }

    public void setKodItemPermitList(List<KodItemPermit> kodItemPermitList) {
        this.kodItemPermitList = kodItemPermitList;
    }

    public BigDecimal getRoyaltiTanahKerajaan() {
        return royaltiTanahKerajaan;
    }

    public void setRoyaltiTanahKerajaan(BigDecimal royaltiTanahKerajaan) {
        this.royaltiTanahKerajaan = royaltiTanahKerajaan;
    }

    public String getKadarBayarPRMP() {
        return kadarBayarPRMP;
    }

    public void setKadarBayarPRMP(String kadarBayarPRMP) {
        this.kadarBayarPRMP = kadarBayarPRMP;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRoyaltiTanahKerajaanUom() {
        return royaltiTanahKerajaanUom;
    }

    public void setRoyaltiTanahKerajaanUom(String royaltiTanahKerajaanUom) {
        this.royaltiTanahKerajaanUom = royaltiTanahKerajaanUom;
    }

    public List<HakmilikPermohonan> getHmListCount() {
        return hmListCount;
    }

    public void setHmListCount(List<HakmilikPermohonan> hmListCount) {
        this.hmListCount = hmListCount;
    }

    public List<HakmilikPermohonan> getHmList() {
        return hmList;
    }

    public void setHmList(List<HakmilikPermohonan> hmList) {
        this.hmList = hmList;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public static String getKodDaerahStatic() {
        return kodDaerahStatic;
    }

    public static void setKodDaerahStatic(String kodDaerahStatic) {
        laporantanahNewActionBean.kodDaerahStatic = kodDaerahStatic;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public KodUOMDAO getKodUOMDAO() {
        return kodUOMDAO;
    }

    public void setKodUOMDAO(KodUOMDAO kodUOMDAO) {
        this.kodUOMDAO = kodUOMDAO;
    }

    public KodLotDAO getKodLotDAO() {
        return kodLotDAO;
    }

    public void setKodLotDAO(KodLotDAO kodLotDAO) {
        this.kodLotDAO = kodLotDAO;
    }

    public PermohonanLaporanKawasanDAO getPermohonanLaporanKawasanDAO() {
        return permohonanLaporanKawasanDAO;
    }

    public void setPermohonanLaporanKawasanDAO(PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO) {
        this.permohonanLaporanKawasanDAO = permohonanLaporanKawasanDAO;
    }

    public KodUrusanDAO getKodUrusanDAO() {
        return kodUrusanDAO;
    }

    public void setKodUrusanDAO(KodUrusanDAO kodUrusanDAO) {
        this.kodUrusanDAO = kodUrusanDAO;
    }

    public KodKeputusanDAO getKeputusanDAO() {
        return keputusanDAO;
    }

    public void setKeputusanDAO(KodKeputusanDAO keputusanDAO) {
        this.keputusanDAO = keputusanDAO;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PembangunanService getPembangunanService() {
        return pembangunanService;
    }

    public void setPembangunanService(PembangunanService pembangunanService) {
        this.pembangunanService = pembangunanService;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public KodKategoriTanahDAO getKodKategoriTanahDAO() {
        return kodKategoriTanahDAO;
    }

    public void setKodKategoriTanahDAO(KodKategoriTanahDAO kodKategoriTanahDAO) {
        this.kodKategoriTanahDAO = kodKategoriTanahDAO;
    }

    public KodItemPermitDAO getKodItemPermitDAO() {
        return kodItemPermitDAO;
    }

    public void setKodItemPermitDAO(KodItemPermitDAO kodItemPermitDAO) {
        this.kodItemPermitDAO = kodItemPermitDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public CalcTaxPelupusan getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTaxPelupusan calcTax) {
        this.calcTax = calcTax;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public PermohonanLaporanBangunanDAO getPermohonanLaporanBangunanDAO() {
        return permohonanLaporanBangunanDAO;
    }

    public void setPermohonanLaporanBangunanDAO(PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO) {
        this.permohonanLaporanBangunanDAO = permohonanLaporanBangunanDAO;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public String getCatatanKWS() {
        return catatanKWS;
    }

    public void setCatatanKWS(String catatanKWS) {
        this.catatanKWS = catatanKWS;
    }

    public String getIdMohonKws() {
        return idMohonKws;
    }

    public void setIdMohonKws(String idMohonKws) {
        this.idMohonKws = idMohonKws;
    }

    public String getAddnoWarta() {
        return addnoWarta;
    }

    public void setAddnoWarta(String addnoWarta) {
        this.addnoWarta = addnoWarta;
    }

    public String getAddnoPelanWarta() {
        return addnoPelanWarta;
    }

    public void setAddnoPelanWarta(String addnoPelanWarta) {
        this.addnoPelanWarta = addnoPelanWarta;
    }

    public String getAddtarikhWarta() {
        return addtarikhWarta;
    }

    public void setAddtarikhWarta(String addtarikhWarta) {
        this.addtarikhWarta = addtarikhWarta;
    }

    public boolean isForSeksyen() {
        return forSeksyen;
    }

    public void setForSeksyen(boolean forSeksyen) {
        this.forSeksyen = forSeksyen;
    }

    public boolean isForBPM() {
        return forBPM;
    }

    public void setForBPM(boolean forBPM) {
        this.forBPM = forBPM;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public etanahActionBeanContext getCtx() {
        return ctx;
    }

    public void setCtx(etanahActionBeanContext ctx) {
        this.ctx = ctx;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<KodKategoriTanah> getSenaraiKodKatTanah() {
        return senaraiKodKatTanah;
    }

    public void setSenaraiKodKatTanah(List<KodKategoriTanah> senaraiKodKatTanah) {
        this.senaraiKodKatTanah = senaraiKodKatTanah;
    }

    public List<LaporanTanahSempadan> getSenaraiLaporTanahSpdn() {
        return senaraiLaporTanahSpdn;
    }

    public void setSenaraiLaporTanahSpdn(List<LaporanTanahSempadan> senaraiLaporTanahSpdn) {
        this.senaraiLaporTanahSpdn = senaraiLaporTanahSpdn;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public ImejLaporan getImejLaporan() {
        return imejLaporan;
    }

    public void setImejLaporan(ImejLaporan imejLaporan) {
        this.imejLaporan = imejLaporan;
    }

    public List<String> getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(List<String> noLotList) {
        this.noLotList = noLotList;
    }

    public List<TanahRizabPermohonan> getTanahrizabpermohonan1ist() {
        return tanahrizabpermohonan1ist;
    }

    public void setTanahrizabpermohonan1ist(List<TanahRizabPermohonan> tanahrizabpermohonan1ist) {
        this.tanahrizabpermohonan1ist = tanahrizabpermohonan1ist;
    }

    public List<PermohonanLaporanPelan> getPermohonanLaporanPelanList() {
        return permohonanLaporanPelanList;
    }

    public void setPermohonanLaporanPelanList(List<PermohonanLaporanPelan> permohonanLaporanPelanList) {
        this.permohonanLaporanPelanList = permohonanLaporanPelanList;
    }

    public List<LaporanTanah> getLaporanTanahList() {
        return laporanTanahList;
    }

    public void setLaporanTanahList(List<LaporanTanah> laporanTanahList) {
        this.laporanTanahList = laporanTanahList;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanList() {
        return laporanTanahSempadanList;
    }

    public void setLaporanTanahSempadanList(List<LaporanTanahSempadan> laporanTanahSempadanList) {
        this.laporanTanahSempadanList = laporanTanahSempadanList;
    }

    public String getIdMohonHakmilik() {
        return idMohonHakmilik;
    }

    public void setIdMohonHakmilik(String idMohonHakmilik) {
        this.idMohonHakmilik = idMohonHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList1() {
        return hakmilikPermohonanList1;
    }

    public void setHakmilikPermohonanList1(List<HakmilikPermohonan> hakmilikPermohonanList1) {
        this.hakmilikPermohonanList1 = hakmilikPermohonanList1;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList3() {
        return hakmilikPermohonanList3;
    }

    public void setHakmilikPermohonanList3(List<HakmilikPermohonan> hakmilikPermohonanList3) {
        this.hakmilikPermohonanList3 = hakmilikPermohonanList3;
    }

//    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
//        return hakmilikPermohonanList;
//    }
//
//    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
//        this.hakmilikPermohonanList = hakmilikPermohonanList;
//    }
    public String getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(String kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public DisHakmilikPermohonan getDisMohonHM() {
        return disMohonHM;
    }

    public void setDisMohonHM(DisHakmilikPermohonan disMohonHM) {
        this.disMohonHM = disMohonHM;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getKodPa() {
        return kodPa;
    }

    public void setKodPa(String kodPa) {
        this.kodPa = kodPa;
    }

    public List<PermohonanManual> getMohonManualList() {
        return mohonManualList;
    }

    public void setMohonManualList(List<PermohonanManual> mohonManualList) {
        this.mohonManualList = mohonManualList;
    }

    public List<Permohonan> getPermohonanLamaListUrusan() {
        return permohonanLamaListUrusan;
    }

    public void setPermohonanLamaListUrusan(List<Permohonan> permohonanLamaListUrusan) {
        this.permohonanLamaListUrusan = permohonanLamaListUrusan;
    }

    public List<Permohonan> getPermohonanLamaList() {
        return permohonanLamaList;
    }

    public void setPermohonanLamaList(List<Permohonan> permohonanLamaList) {
        this.permohonanLamaList = permohonanLamaList;
    }

    public Permohonan getPermohonanLama() {
        return permohonanLama;
    }

    public void setPermohonanLama(Permohonan permohonanLama) {
        this.permohonanLama = permohonanLama;
    }

    public LaporanTanah getLaporanTanah1() {
        return laporanTanah1;
    }

    public void setLaporanTanah1(LaporanTanah laporanTanah1) {
        this.laporanTanah1 = laporanTanah1;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public List<FasaPermohonan> getFasaPermohonanList() {
        return fasaPermohonanList;
    }

    public void setFasaPermohonanList(List<FasaPermohonan> fasaPermohonanList) {
        this.fasaPermohonanList = fasaPermohonanList;
    }

    public String getNoFailBaru() {
        return noFailBaru;
    }

    public void setNoFailBaru(String noFailBaru) {
        this.noFailBaru = noFailBaru;
    }

    public String getIdMohonManual() {
        return idMohonManual;
    }

    public void setIdMohonManual(String idMohonManual) {
        this.idMohonManual = idMohonManual;
    }

    public String getSokong() {
        return sokong;
    }

    public void setSokong(String sokong) {
        this.sokong = sokong;
    }

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public PermohonanLaporanUlasanDAO getPermohonanLaporanUlasanDAO() {
        return permohonanLaporanUlasanDAO;
    }

    public void setPermohonanLaporanUlasanDAO(PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO) {
        this.permohonanLaporanUlasanDAO = permohonanLaporanUlasanDAO;
    }

    public String getUlasanSebab() {
        return ulasanSebab;
    }

    public void setUlasanSebab(String ulasanSebab) {
        this.ulasanSebab = ulasanSebab;
    }

    public String getIdLaporUlasan() {
        return idLaporUlasan;
    }

    public void setIdLaporUlasan(String idLaporUlasan) {
        this.idLaporUlasan = idLaporUlasan;
    }

    public PermohonanManualDAO getPermohonanManualDAO() {
        return permohonanManualDAO;
    }

    public void setPermohonanManualDAO(PermohonanManualDAO permohonanManualDAO) {
        this.permohonanManualDAO = permohonanManualDAO;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListU() {
        return hakmilikImejLaporanListU;
    }

    public void setHakmilikImejLaporanListU(List<ImejLaporan> hakmilikImejLaporanListU) {
        this.hakmilikImejLaporanListU = hakmilikImejLaporanListU;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListS() {
        return hakmilikImejLaporanListS;
    }

    public void setHakmilikImejLaporanListS(List<ImejLaporan> hakmilikImejLaporanListS) {
        this.hakmilikImejLaporanListS = hakmilikImejLaporanListS;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListB() {
        return hakmilikImejLaporanListB;
    }

    public void setHakmilikImejLaporanListB(List<ImejLaporan> hakmilikImejLaporanListB) {
        this.hakmilikImejLaporanListB = hakmilikImejLaporanListB;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListT() {
        return hakmilikImejLaporanListT;
    }

    public void setHakmilikImejLaporanListT(List<ImejLaporan> hakmilikImejLaporanListT) {
        this.hakmilikImejLaporanListT = hakmilikImejLaporanListT;
    }

    public String getIdlaporTnhSmpdn() {
        return idlaporTnhSmpdn;
    }

    public void setIdlaporTnhSmpdn(String idlaporTnhSmpdn) {
        this.idlaporTnhSmpdn = idlaporTnhSmpdn;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public String getStrukturTanahLain() {
        return strukturTanahLain;
    }

    public void setStrukturTanahLain(String strukturTanahLain) {
        this.strukturTanahLain = strukturTanahLain;
    }

    public String getUlasanLain() {
        return ulasanLain;
    }

    public void setUlasanLain(String ulasanLain) {
        this.ulasanLain = ulasanLain;
    }

    public String getDiusaha() {
        return diusaha;
    }

    public void setDiusaha(String diusaha) {
        this.diusaha = diusaha;
    }

    public String getOleh() {
        return oleh;
    }

    public void setOleh(String oleh) {
        this.oleh = oleh;
    }

    public String getPerenggan() {
        return perenggan;
    }

    public void setPerenggan(String perenggan) {
        this.perenggan = perenggan;
    }

    public String getRancanganKerajaan() {
        return rancanganKerajaan;
    }

    public void setRancanganKerajaan(String rancanganKerajaan) {
        this.rancanganKerajaan = rancanganKerajaan;
    }

    public String getUsahaTanam() {
        return usahaTanam;
    }

    public void setUsahaTanam(String usahaTanam) {
        this.usahaTanam = usahaTanam;
    }

    public String getTanahBertumpu() {
        return tanahBertumpu;
    }

    public void setTanahBertumpu(String tanahBertumpu) {
        this.tanahBertumpu = tanahBertumpu;
    }

    public String getKeteranganTanahBertumpu() {
        return keteranganTanahBertumpu;
    }

    public void setKeteranganTanahBertumpu(String keteranganTanahBertumpu) {
        this.keteranganTanahBertumpu = keteranganTanahBertumpu;
    }

    public Character getDilintasTiangElektrik() {
        return dilintasTiangElektrik;
    }

    public void setDilintasTiangElektrik(Character dilintasTiangElektrik) {
        this.dilintasTiangElektrik = dilintasTiangElektrik;
    }

    public Character getDilintasTiangTelefon() {
        return dilintasTiangTelefon;
    }

    public void setDilintasTiangTelefon(Character dilintasTiangTelefon) {
        this.dilintasTiangTelefon = dilintasTiangTelefon;
    }

    public Character getDilintasLaluanGas() {
        return dilintasLaluanGas;
    }

    public void setDilintasLaluanGas(Character dilintasLaluanGas) {
        this.dilintasLaluanGas = dilintasLaluanGas;
    }

    public Character getDilintasPaip() {
        return dilintasPaip;
    }

    public void setDilintasPaip(Character dilintasPaip) {
        this.dilintasPaip = dilintasPaip;
    }

    public Character getDilintasTaliar() {
        return dilintasTaliar;
    }

    public void setDilintasTaliar(Character dilintasTaliar) {
        this.dilintasTaliar = dilintasTaliar;
    }

    public Character getDilintasSungai() {
        return dilintasSungai;
    }

    public void setDilintasSungai(Character dilintasSungai) {
        this.dilintasSungai = dilintasSungai;
    }

    public Character getDilintasParit() {
        return dilintasParit;
    }

    public void setDilintasParit(Character dilintasParit) {
        this.dilintasParit = dilintasParit;
    }

    public Character getBangunan() {
        return bangunan;
    }

    public void setBangunan(Character bangunan) {
        this.bangunan = bangunan;
    }

    public Character getUsaha() {
        return usaha;
    }

    public void setUsaha(Character usaha) {
        this.usaha = usaha;
    }

    public String getIdMohonlaporKws() {
        return idMohonlaporKws;
    }

    public void setIdMohonlaporKws(String idMohonlaporKws) {
        this.idMohonlaporKws = idMohonlaporKws;
    }

    public String getCatatanKwsn() {
        return catatanKwsn;
    }

    public void setCatatanKwsn(String catatanKwsn) {
        this.catatanKwsn = catatanKwsn;
    }

    public Date getTrhWartaKwsn() {
        return trhWartaKwsn;
    }

    public void setTrhWartaKwsn(Date trhWartaKwsn) {
        this.trhWartaKwsn = trhWartaKwsn;
    }

    public String getNoWartaKwsn() {
        return noWartaKwsn;
    }

    public void setNoWartaKwsn(String noWartaKwsn) {
        this.noWartaKwsn = noWartaKwsn;
    }

    public String getNoPelankawasan() {
        return noPelankawasan;
    }

    public void setNoPelankawasan(String noPelankawasan) {
        this.noPelankawasan = noPelankawasan;
    }

    public Integer getRizabKwsn() {
        return rizabKwsn;
    }

    public void setRizabKwsn(Integer rizabKwsn) {
        this.rizabKwsn = rizabKwsn;
    }

    public String getJenishasil() {
        return jenishasil;
    }

    public void setJenishasil(String jenishasil) {
        this.jenishasil = jenishasil;
    }

    public String getAgensiUpahUkur() {
        return agensiUpahUkur;
    }

    public void setAgensiUpahUkur(String agensiUpahUkur) {
        this.agensiUpahUkur = agensiUpahUkur;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanTuntutanKos getCagaranJalan() {
        return cagaranJalan;
    }

    public void setCagaranJalan(PermohonanTuntutanKos cagaranJalan) {
        this.cagaranJalan = cagaranJalan;
    }

    public PermohonanTuntutanKos getBayarankupon() {
        return bayarankupon;
    }

    public void setBayarankupon(PermohonanTuntutanKos bayarankupon) {
        this.bayarankupon = bayarankupon;
    }

    public String getIsiPaduBatu() {
        return isiPaduBatu;
    }

    public void setIsiPaduBatu(String isiPaduBatu) {
        this.isiPaduBatu = isiPaduBatu;
    }

    public String getTempohDisyor() {
        return tempohDisyor;
    }

    public void setTempohDisyor(String tempohDisyor) {
        this.tempohDisyor = tempohDisyor;
    }

    public String getCagarJalan() {
        return cagarJalan;
    }

    public void setCagarJalan(String cagarJalan) {
        this.cagarJalan = cagarJalan;
    }

    public String getKuponQty() {
        return kuponQty;
    }

    public void setKuponQty(String kuponQty) {
        this.kuponQty = kuponQty;
    }

    public String getKupon() {
        return kupon;
    }

    public void setKupon(String kupon) {
        this.kupon = kupon;
    }

    public LaporanTanahSempadan getLts() {
        return lts;
    }

    public void setLts(LaporanTanahSempadan lts) {
        this.lts = lts;
    }

    public List<LaporanTanahSempadan> getListlts() {
        return listlts;
    }

    public void setListlts(List<LaporanTanahSempadan> listlts) {
        this.listlts = listlts;
    }

    public String getIdLaporTanahSpdn() {
        return idLaporTanahSpdn;
    }

    public void setIdLaporTanahSpdn(String idLaporTanahSpdn) {
        this.idLaporTanahSpdn = idLaporTanahSpdn;
    }

    public String getSebabBoleh() {
        return sebabBoleh;
    }

    public void setSebabBoleh(String sebabBoleh) {
        this.sebabBoleh = sebabBoleh;
    }

    public String getBolehBerimilik() {
        return bolehBerimilik;
    }

    public void setBolehBerimilik(String bolehBerimilik) {
        this.bolehBerimilik = bolehBerimilik;
    }

    public BigDecimal getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(BigDecimal nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public String getKeadaanTanah1() {
        return keadaanTanah1;
    }

    public void setKeadaanTanah1(String keadaanTanah1) {
        this.keadaanTanah1 = keadaanTanah1;
    }

    public KodSyaratNyataDAO getKodSyaratNyataDAO() {
        return kodSyaratNyataDAO;
    }

    public void setKodSyaratNyataDAO(KodSyaratNyataDAO kodSyaratNyataDAO) {
        this.kodSyaratNyataDAO = kodSyaratNyataDAO;
    }

    public KodSekatanKepentinganDAO getKodSekatanKepentinganDAO() {
        return kodSekatanKepentinganDAO;
    }

    public void setKodSekatanKepentinganDAO(KodSekatanKepentinganDAO kodSekatanKepentinganDAO) {
        this.kodSekatanKepentinganDAO = kodSekatanKepentinganDAO;
    }

    public TampalHakmilikService getTampalHakmilikService() {
        return tampalHakmilikService;
    }

    public void setTampalHakmilikService(TampalHakmilikService tampalHakmilikService) {
        this.tampalHakmilikService = tampalHakmilikService;
    }

    public String getDendaPremium() {
        return dendaPremium;
    }

    public void setDendaPremium(String dendaPremium) {
        this.dendaPremium = dendaPremium;
    }

    public KodNotisDAO getKodNotisDAO() {
        return kodNotisDAO;
    }

    public void setKodNotisDAO(KodNotisDAO kodNotisDAO) {
        this.kodNotisDAO = kodNotisDAO;
    }

    public BorangPerHakmilikService getBorangPerhakmilikService() {
        return borangPerhakmilikService;
    }

    public void setBorangPerhakmilikService(BorangPerHakmilikService borangPerhakmilikService) {
        this.borangPerhakmilikService = borangPerhakmilikService;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListPanorama() {
        return hakmilikImejLaporanListPanorama;
    }

    public void setHakmilikImejLaporanListPanorama(List<ImejLaporan> hakmilikImejLaporanListPanorama) {
        this.hakmilikImejLaporanListPanorama = hakmilikImejLaporanListPanorama;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListLampiranD() {
        return hakmilikImejLaporanListLampiranD;
    }

    public void setHakmilikImejLaporanListLampiranD(List<ImejLaporan> hakmilikImejLaporanListLampiranD) {
        this.hakmilikImejLaporanListLampiranD = hakmilikImejLaporanListLampiranD;
    }

}
