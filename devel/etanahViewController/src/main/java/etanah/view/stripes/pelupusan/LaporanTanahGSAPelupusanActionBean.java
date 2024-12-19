/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodTanahDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.model.KodKeadaanTanah;
import etanah.model.KodDUN;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodKecerunanTanah;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodJenisPendudukan;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.KodKadarPremium;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKeputusan;
import etanah.model.KodPemilikan;
import etanah.model.KodStrukturTanah;
import etanah.model.KodTanah;
import etanah.model.KodTuntut;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.TanahRizabService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author Afham&Shafful
 */
@UrlBinding("/pelupusan/laporan_tanahGSA")
public class LaporanTanahGSAPelupusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    CommonService commonService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    RegService regService;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    List<HakmilikPermohonan> senaraiHakmilik;
    private String kodNegeri;
    private boolean checkTanahExist = false;
    private NoPt noPT = new NoPt();
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Hakmilik hakmilik;
    private KodTanah kodTanah;
    private KodHakmilik kodHakmilik;
    private KodPemilikan kodPemilikan;
    private KodUOM kodUOM;
    private KodKeadaanTanah kodKeadaanTanah;
    private KodTuntut kodTuntut;
    private KodKadarPremium kodKadarPremium;
    private List<String> senaraikodKadarPremium;
    private KodItemPermit kodItemPermit;
    private String idHakmilik;
    private BigDecimal luasDilulus;
    private Permohonan permohonan;
    private KodDUN kodDUN;
    private KodKawasanParlimen kodKawasanParlimen;
    private KodKeputusan kodKeputusan;
    private KodStrukturTanah kodStrukturTanah;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private TanahRizabPermohonan tanahRizabPermohonan;
    private TanahRizabPermohonan tanahrizabpermohonan1;
    private HakmilikPermohonan hakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(LaporanTanah4ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private static String MAIN_PAGE = "pelupusan/gsa/laporan_tanahGSA.jsp";
    private static String PTANAH_PAGE = "pelupusan/gsa/laporan_tanahGSAPerihal.jsp";
    private static String SEMPADAN_PAGE = "pelupusan/gsa/laporan_tanahSempadanan.jsp";
    private static String KTANAH_PAGE = "pelupusan/gsa/laporan_tanahGSAKeadaanTanah.jsp";
    private static String LSEMPADAN_PAGE = "pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp";
    private static String DKAWASAN_PAGE = "pelupusan/gsa/laporan_tanahGSADalamKawasan.jsp";
    private static String BANGUNAN_PAGE = "pelupusan/gsa/laporan_tanahGSA_popup.jsp";
    private static String SYOR_PAGE = "pelupusan/gsa/laporan_tanahGSASyor.jsp";
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List hakmilikPermohonanList;
    private List <HakmilikPermohonan>senaraiHakmilikPermohonan;
    private List<TanahRizabPermohonan> tanahRizabPermohonanList;
    private char pandanganImej;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private FileBean fileToBeUpload;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String catatan;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodKecerunanTanah kecerunanTanah;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private String noLot;
    private String noLitho;
    private String noWarta;
    private String lokasi;
    private KodRizab rizab;
    private String tarikhWarta;
    private String idtanahrizabPermohonan;
    private String ulasan;
    private String mohonlaporulasan;
    boolean p;
    private List<KodSeksyen> kodSeksyenList;
    private Pengguna pguna;
    private String idPermohonan;
    private PermohonanLaporanKawasan permohonanLaporanKawasan;
    private String pbt;
    private String rizab1;
    private String rizab2;
    private PermohonanManual permohonanManual;
    private Pemohon pemohon;
    Permohonan prmhnn;
    String id2;
    String id;
    private List<PermohonanManual> permohonanManualList;
    Long idlapor;
    private String index;
    private String kod;
    private String syaratNyata;
    private String syaratNyata1;
    private String kodSktn;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String kodSekatanKepentingan;
    private String sekatKpntgn2;
    private String kodSyaratNyata;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private String syaratNyata2;
    private String kodHmlk;
    private String kodHmlkTetap;
    private String kodU;
    private String kodUPlps;
    private String keadaanTanah;
    private String negeri;
    private String keteranganKadarPremium;
    private char kodP;
    private String tanahR;
    private String kodT;
    private String kodD;
    private String kodPar;
    private String kecerunanT;
    private String klasifikasiT;
    private String ksn;
    private PermohonanPermitItem permohonanPermitItem;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private String keg;
    private BigDecimal amnt = BigDecimal.ZERO;
    private BigDecimal usahaLuas;
    private Integer usahaBilanganPokok = new Integer(0);
    private BigDecimal usahaHarga;
    private List<Hakmilik> list;
    private String kategori;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private HakmilikUrusan hakmilikUrusan;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    private PermohonanLaporanUlasan laporanUlasan;
    private char jenisKegunaan;
    private char adaJalanMasuk;
    private String adaPecahSempadan;
    private Boolean display = Boolean.FALSE;
    @Inject
    KodJenisPendudukanDAO jenisPendudukanDAO;
    private String plpulasan1;
    private String ulsn;
    private String pbt1;
    private String hutan;
    private String rizab_melayu;
    private String gsa;
    private String lain;
    private String catatanLain1;
    private String idLaporTanah;
    private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
    private PermohonanLaporanKandungan permohonanLaporanKandungan;
    @Inject
    PermohonanLaporanKandunganDAO permohonanLaporanKandunganDAO;
    private String kand;
    private String keadaankand;
    private List<PermohonanLaporanUlasan> senaraiLaporanKandungan1;
    private int rowCount2;
    private List<PermohonanLaporanUlasan> senarailaporanUlasan;
    private String kodRizab;
    private String addnoWarta;
    private String addtarikhWarta;
    private String addnoPelanWarta;
    private String addcatatan;
    private String catatanKeg;
    private String ulasanKanan;
    private String hakmilik_ref;
    private PermohonanBahanBatuan permohonanBahanBatuan;
     private LaporanTanahSempadan laporanTanahSempadan;
    private KodUOM jumlahIsipaduUOM;
    private BigDecimal jumlahIsipadu;
    private String tempohSyorUOM;
    private List listLaporTanahSpdnU;
    private List listLaporTanahSpdnS;
    private List listLaporTanahSpdnB;
    private List listLaporTanahSpdnT;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private String idKandungan;
    private String jenisSmpdn;
     private String idHakmilikSmpdn;
     private String kegunaanSmpdn;
     private String milikKerajaanSmpdn;
     private String statusSempadan;
     private String sizeSenaraiLaporUlas;
    etanahActionBeanContext ctx;
    
    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        display = Boolean.TRUE;
        return new JSP("pelupusan/gsa/laporan_tanahGSA.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload","!simpanBersempadanan","!simpanPerihal","!showFormPopUp","!openFrame","!tambahRow","!deleteRow"})
    public void rehydrate() {
        ctx = (etanahActionBeanContext) getContext();
        System.out.println("-------------rehydrate---------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--------Stage ID-----------" + stageId);
        permohonanLaporanPelan = plpservice.findByIdPermohonanPelan(idPermohonan);
        LOG.info("--------permohonanLaporanPelan-----------" + permohonanLaporanPelan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        pbt = "1";
        permohonanLaporanKawasan = plpservice.findByidMohonKodRizab(idPermohonan, pbt);
        
        LOG.info("--------permohonanLaporanKawasan-----------" + permohonanLaporanKawasan);
        if (permohonanLaporanKawasan != null) {
            rizab1 = permohonanLaporanKawasan.getKodRizab().getNama();
        }

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            catatanKeg = permohonan.getSebab() != null && !("").equals(permohonan.getSebab()) ? permohonan.getSebab() : new String();
            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
            tanahRizabPermohonanList = permohonan.getSenaraiTanahRizab();
            idHakmilik = ctx.getRequest().getParameter("idHakmilik");
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (!StringUtils.isEmpty(idHakmilik)) {
                hakmilikPermohonan = plpservice.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                if (permohonan.getSenaraiHakmilik().size() > 0) {
                    hakmilikPermohonanList = new ArrayList();
                    for (HakmilikPermohonan mohonHM : permohonan.getSenaraiHakmilik()) {
                        if (mohonHM.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                            DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();
                            disMohonHM.setHakmilikPermohonan(mohonHM);
                            hakmilikPermohonanList.add(disMohonHM);
                            break;
                        }
                    }
                }
            } else {
                if (permohonan.getSenaraiHakmilik().size() > 0) {
                    hakmilikPermohonanList = new ArrayList();
                    HakmilikPermohonan mohonHM = new HakmilikPermohonan();
                    DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

                    mohonHM = permohonan.getSenaraiHakmilik().get(0);
                    disMohonHM.setHakmilikPermohonan(mohonHM);
                    hakmilikPermohonanList.add(disMohonHM);
                    idHakmilik = disMohonHM.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                    hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
                }
            }
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;
           
        if(hakmilikPermohonan!=null){
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) findObject(laporanTanah,new String[]{String.valueOf(hakmilikPermohonan.getId())});             
        }
        
//            laporanTanah = plpservice.findLaporanTanahByIdMH(hakmilikPermohonan.getId());
//            laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            checkTanahExist = laporanTanah != null ? true : false;
            if (laporanTanah != null) {
                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
            } else {
                laporanTanah = new LaporanTanah();
            }
            
            List<FasaPermohonan> listFasa;
//            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            //REMOVE 02SediaLaporan
//            stageId = "laporan_tanah";
            listFasa = permohonan.getSenaraiFasa();
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
                    if (fp.getIdAliran().equals("03ArahLaporanTanah")) {
                        fasaPermohonan = listFasa.get(i);
                        //ulasan = fasaPermohonan.getUlasan() ; DISABLED SINCE USING PTLPULASAN1
                        if (fasaPermohonan.getUlasan() != null) {
                            plpulasan1 = fasaPermohonan.getUlasan();
                        }
                        if (fasaPermohonan.getKeputusan() != null) {
                            ksn = fasaPermohonan.getKeputusan().getKod();
                        }
                    }
                }
            }
            /*
             * LOT-LOT BERSEMPADANAN
             */
            /*
             * Added For Dynamic Lot-Lot Sempadan
             */
                listLaporTanahSpdnU = new ArrayList();
                listLaporTanahSpdnB = new ArrayList();
                listLaporTanahSpdnS = new ArrayList();
                listLaporTanahSpdnT = new ArrayList();
                
                List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();                
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnU.add(ls);
                }
                LOG.info("listLaporTanahSpdnU.size():"+listLaporTanahSpdnU.size());
                uSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnS.add(ls);
                }
                sSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnT.add(ls);
                }
                tSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnB.add(ls);
                }
                bSize = listLaporTanahSpdnTemp.size();
            /*
             * END
             */
            List<PermohonanRujukanLuar> listRujukanLuar;
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);
        }


        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String format1 = "image/jpeg";
        String format2 = "image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pguna.getNama());
        
        tanahrizabpermohonan1 = (TanahRizabPermohonan) findObject(tanahrizabpermohonan1,new String[]{idPermohonan,idHakmilik});
        
        permohonanManualList = plpservice.findByIdMohonlist(idPermohonan);
        if (permohonanManualList != null) {
            LOG.info("--------permohonanManualList---------::" + permohonanManualList);
        }

        if (laporanTanah != null) {
            idlapor = laporanTanah.getIdLaporan();
        }

        if (permohonanLaporanUlasan != null) {
            ulsn = permohonanLaporanUlasan.getUlasan();
            LOG.info("--------ulsn Rehydrate()---------::" + ulsn);
        }


        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilik() != null)) {
            kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilik() != null)) {
            kodHmlkTetap = hakmilikPermohonan.getKodHakmilikTetap().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
            kodU = hakmilikPermohonan.getKodUnitLuas().getKod();
        }
        if ((laporanTanah != null) && (laporanTanah.getKodKeadaanTanah() != null)) {
            keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
        }


        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium() != null)) {
            keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();

        }

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
            kodP = hakmilikPermohonan.getKodMilik().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
            kodD = hakmilikPermohonan.getKodDUN().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
            kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
        }

        if ((tanahrizabpermohonan1 != null) && tanahrizabpermohonan1.getRizab() != null) {
            int tr = tanahrizabpermohonan1.getRizab().getKod();
            tanahR = Integer.toString(tr);
        }

        if ((permohonanLaporanPelan != null) && permohonanLaporanPelan.getKodTanah() != null) {
            kodT = permohonanLaporanPelan.getKodTanah().getKod();
        }

        if ((laporanTanah != null) && (laporanTanah.getKecerunanTanah() != null)) {
            kecerunanT = laporanTanah.getKecerunanTanah().getKod();
        }
        if ((laporanTanah != null) && (laporanTanah.getStrukturTanah() != null)) {
            klasifikasiT = laporanTanah.getStrukturTanah().getKod();
        }

        if ((fasaPermohonan != null) && (fasaPermohonan.getKeputusan() != null)) {
            ksn = fasaPermohonan.getKeputusan().getKod();
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
            if ((permohonanPermitItem != null) && (permohonanPermitItem.getKodItemPermit() != null)) {
                keg = permohonanPermitItem.getKodItemPermit().getKod();
                LOG.info("----keg---------" + keg);
            }
        }
        permohonanTuntutanKos = plpservice.findMohonTuntutKosByIdPermohonan(idPermohonan);
        if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
            amnt = permohonanTuntutanKos.getAmaunTuntutan();
        }

        if ((laporanTanah != null) && (laporanTanah.getUsaha() != null)) {
            usahaLuas = laporanTanah.getUsahaLuas();
        }

        if ((laporanTanah != null) && (laporanTanah.getUsahaBilanganPokok() != null)) {
            usahaBilanganPokok = laporanTanah.getUsahaBilanganPokok();
        }

        if ((laporanTanah != null) && (laporanTanah.getUsahaHarga() != null)) {
            usahaHarga = laporanTanah.getUsahaHarga();
        }

//search options
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getSyaratNyataBaru() != null)) {
            String kod1 = (hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            LOG.info("----kod1---------" + kod1);
//             syaratNyata = ""+kod+" -- "+kod1+"";
            syaratNyata = "" + kod1;
            LOG.info("----syaratNyata---------" + syaratNyata);
        } else {
            kod = "";
        }

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getSekatanKepentinganBaru() != null)) {
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            LOG.info("----kodSktn1---------" + kodSktn);
            String kod2 = (hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
//               syaratNyata1 =  ""+kodSktn+" -- "+kod2+"";
            syaratNyata1 = "" + kod2;
            LOG.info("----syaratNyata1---------" + syaratNyata1);
        } else {
            kodSktn = "";
        }
        if (laporanTanah != null) {
            LOG.info("----laporanTanah.getAdaBangunan()---------" + laporanTanah.getAdaBangunan());

            if (laporanTanah.getAdaBangunan() != null && laporanTanah.getAdaBangunan() == 'Y') {
                jenisKegunaan = 'B';
                permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
            }
            LOG.info("----permohonanLaporanBangunanList---------" + permohonanLaporanBangunanList);
        }

        senaraikodKadarPremium = plpservice.getSenaraiKodKadarPremiumDistinctNama();
        LOG.info("----senaraikodKadarPremium List---------" + senaraikodKadarPremium);


        //new

        ///trace mohon_laporan_kws
        permohonanLaporKSWList = laporanPelukisPelanService.findByidMohon(idPermohonan);
        LOG.info("---------------permohonanLaporKSWList-----------------" + permohonanLaporKSWList);
        for (PermohonanLaporanKawasan lpk : permohonanLaporKSWList) {
            LOG.info("trace kod rizab :: " + lpk.getKodRizab().getKod());
            String rezKod = String.valueOf(lpk.getKodRizab().getKod());
            if (rezKod.equalsIgnoreCase("1")) {
                LOG.info("Load Kod Rizab 1");
                pbt1 = "1";
            } else if (rezKod.equalsIgnoreCase("2")) {
                gsa = "2";
            } else if (rezKod.equalsIgnoreCase("3")) {
                rizab_melayu = "3";
            } else if (rezKod.equalsIgnoreCase("4")) {
                hutan = "4";
            } else if (rezKod.equalsIgnoreCase("99")) {

                lain = "99";
                catatanLain1 = lpk.getCatatan();
            }
        }

        if (laporanTanah != null) {
            permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Jenis Tanah", laporanTanah.getIdLaporan());
        }
        LOG.info("-------permohonanLaporanKandungan rehydrate()-------::" + permohonanLaporanKandungan);
        if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
            kand = permohonanLaporanKandungan.getKand();
        }

        if (laporanTanah != null) {
            permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
        }
        LOG.info("-------permohonanLaporanKandungan rehydrate()-------::" + permohonanLaporanKandungan);
        if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
            keadaankand = permohonanLaporanKandungan.getKand();
        }
        senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
        senaraiLaporanKandungan1 = plpservice.findUlasanByIdMohon(idPermohonan);
//        senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
//        rowCount2 = senaraiLaporanKandungan1.size();
//
//        if (!(senaraiLaporanKandungan1.isEmpty())) {
//            PermohonanLaporanUlasan permohonanLaporanUlasan1 = new PermohonanLaporanUlasan();
//            permohonanLaporanUlasan1 = senaraiLaporanKandungan1.get(0);
//            LOG.info("-------permohonanLaporanUlasan1.getUlasan() ------::" + permohonanLaporanUlasan1.getUlasan());
//            plpulasan1 = permohonanLaporanUlasan1.getUlasan();
//
//        }


        /*
         * NO_PT
         */
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getIdPermohonan());
            noPT = plpservice.findNoPTByIdMH(hakmilikPermohonan.getId());

            if (noPT != null) {
                if (noPT.getKodUOM() != null) {
                    kodU = noPT.getKodUOM().getKod();
                }
                if (noPT.getLuasDilulus() != null) {
                    luasDilulus = noPT.getLuasDilulus();
                }
            }
        }
        if (permohonan.getKodUrusan().equals("PLPS")) {
            if (hakmilikPermohonan.getLuasLulusUom() != null) {
                kodUPlps = hakmilikPermohonan.getLuasLulusUom().getNama();
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
            permohonanBahanBatuan = plpservice.findByIdMBB(idPermohonan);
            if (permohonanBahanBatuan != null && permohonanBahanBatuan.getTempohSyorUom() != null) {
                tempohSyorUOM = permohonanBahanBatuan.getTempohSyorUom().getKod();
            }
        }


    }
    
    
    public Resolution tambahRow() {

        PermohonanLaporanUlasan pkk = new PermohonanLaporanUlasan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        senaraiLaporanKandungan1 = plpservice.findUlasanByIdMohon(idPermohonan);
        if(senaraiLaporanKandungan1==null){
           senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
        }
        switch (index) {
            case 1:
                break;
            case 5:
                ksn = getContext().getRequest().getParameter("kodksn");
                pkk = new PermohonanLaporanUlasan();
                senaraiLaporanKandungan1.add(pkk);
                sizeSenaraiLaporUlas = String.valueOf(senaraiLaporanKandungan1.size());
                break;
            default:
        }
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSASyor.jsp").addParameter("tab", "true");
        return new JSP(SYOR_PAGE).addParameter("popup", Boolean.TRUE);
    }
    
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
    
    public Resolution showFormPopUp() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
       if(getIdKandungan() != null){
           laporanTanahSempadan = (LaporanTanahSempadan)plpservice.findDAOByPK(laporanTanahSempadan,new Long(getIdKandungan()));
           hakmilik_ref = laporanTanahSempadan.getHakmilik().getIdHakmilik();
           
           if(laporanTanahSempadan!=null){
                jenisSmpdn = laporanTanahSempadan.getJenisSempadan();
                idHakmilikSmpdn = laporanTanahSempadan.getHakmilik().getIdHakmilik();
                kegunaanSmpdn = laporanTanahSempadan.getGuna();
                keadaanTanah = laporanTanahSempadan.getKeadaanTanah();
                catatan = laporanTanahSempadan.getCatatan();
                milikKerajaanSmpdn = laporanTanahSempadan.getMilikKerajaan();
                statusSempadan = "U"; // U for update   
           }
       }else{           
           laporanTanahSempadan = new LaporanTanahSempadan();
           jenisSmpdn = new String();
           idHakmilikSmpdn = new String();
           kegunaanSmpdn = new String();
           keadaanTanah = new String();
           catatan = new String();
           milikKerajaanSmpdn = new String();
           statusSempadan = new String();
       }

        return new JSP("pelupusan/gsa/laporan_tanahGSAAddEditLotSmpdn.jsp").addParameter("popup", "true");
    }
    
    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");        
        kodNegeri = conf.getProperty("kodNegeri");        
        permohonan = plpservice.findById(idPermohonan);       
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = (HakmilikPermohonan) findObject(hakmilikPermohonan,new String[]{idPermohonan,idHakmilik}); 
        if(hakmilikPermohonan!=null){
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) findObject(laporanTanah,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
        }
        if(laporanTanah==null){
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            laporanTanah = new LaporanTanah();
            laporanTanah.setInfoAudit(ia);
            laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanah.setPermohonan(permohonan);
            plpservice.simpanLaporanTanah(laporanTanah);
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) findObject(laporanTanah,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
            
        }
        
        if(StringUtils.isNotBlank(type)){
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);            
        }
//        rehydrate();
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
        //return showFormDisplay();
    }
    /*
     * Author : Shazwan
     * Date : 9th February 2012
     * Purpose : To Refresh data based on type given;
     * Note : the type is set based on frame in laporanTanahGSA, please consult before making changes since this method is sharing
     */
    public String refreshData(String type){
        String forwardJSP = new String();
        int typeNum = type.equals("pTanah")?1:
                          type.equals("sempadan")?2:
                          type.equals("kTanah")?3:
                          type.equals("lSempadan")?4:
                          type.equals("dKawasan")?5:
                          type.equals("syorPPT")?6:
                          type.equals("main")?7:0;
            
            switch(typeNum){
                case 1 :
                            forwardJSP = PTANAH_PAGE;
                            permohonanLaporanPelan = (PermohonanLaporanPelan) findObject(permohonanLaporanPelan,new String[]{idPermohonan}); 
                            if ((permohonanLaporanPelan != null) && permohonanLaporanPelan.getKodTanah() != null){
                                kodT = permohonanLaporanPelan.getKodTanah().getKod();
                            }
                            
                            tanahrizabpermohonan1 = (TanahRizabPermohonan) findObject(tanahRizabService,new String[]{idPermohonan});
                            if ((tanahrizabpermohonan1 != null) && tanahrizabpermohonan1.getRizab() != null) {
                                int tr = tanahrizabpermohonan1.getRizab().getKod();
                                tanahR = Integer.toString(tr);
                            }
                            if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
                                kand = permohonanLaporanKandungan.getKand();
                            }
                            break;
                case 2 :    
                            forwardJSP = SEMPADAN_PAGE;
                            break;
                case 3 :    
                            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
                            forwardJSP = KTANAH_PAGE;
                            break;
                case 4 :    
                            /*
                             * Added For Dynamic Lot-Lot Sempadan
                             */
                            listLaporTanahSpdnU = new ArrayList();
                            listLaporTanahSpdnB = new ArrayList();
                            listLaporTanahSpdnS = new ArrayList();
                            listLaporTanahSpdnT = new ArrayList();

                            List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();                
                            listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
                            for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                                LotSempadan ls = new LotSempadan();
                                ls.setLaporanTanahSmpdn(lts);
                                listLaporTanahSpdnU.add(ls);
                            }
                            LOG.info("listLaporTanahSpdnU.size():"+listLaporTanahSpdnU.size());
                            uSize = listLaporTanahSpdnTemp.size();
                            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                            listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
                            for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                                LotSempadan ls = new LotSempadan();
                                ls.setLaporanTanahSmpdn(lts);
                                listLaporTanahSpdnS.add(ls);
                            }
                            sSize = listLaporTanahSpdnTemp.size();
                            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                            listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
                            for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                                LotSempadan ls = new LotSempadan();
                                ls.setLaporanTanahSmpdn(lts);
                                listLaporTanahSpdnT.add(ls);
                            }
                            tSize = listLaporTanahSpdnTemp.size();
                            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                            listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
                            for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                                LotSempadan ls = new LotSempadan();
                                ls.setLaporanTanahSmpdn(lts);
                                listLaporTanahSpdnB.add(ls);
                            }
                            bSize = listLaporTanahSpdnTemp.size();    
                    
                            
                            forwardJSP = LSEMPADAN_PAGE;
                            break;
                case 5 :    
                            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
                            forwardJSP = DKAWASAN_PAGE;
                            break;
                case 6 :    
                            stageId = "03ArahLaporanTanah";
                            fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
                            if(fasaPermohonan!=null)
                                ksn = fasaPermohonan.getKeputusan().getKod();
                            
                            senaraiLaporanKandungan1 = plpservice.findUlasanByIdMohon(idPermohonan);
                            forwardJSP = SYOR_PAGE;
                            break;
                case 7 :    
                            rehydrate();
                            forwardJSP = MAIN_PAGE;
                            break;
            }
            return forwardJSP;
    }
    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String forwardJSP = new String();
        
        if (idKand != null && tName != null) {
            forwardJSP = refreshData(this.delObject(tName, new String[]{idKand}));            
        }
        return new ForwardResolution("/WEB-INF/jsp/"+forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }
    public Resolution refreshpage() {
         return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }
    public Resolution simpanKandunganSempadan() throws ParseException {
        
        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        String idHakmilikSempadan = new String();
        if(StringUtils.isNotBlank(statusSempadan)&&statusSempadan.equals("U"))
            idHakmilikSempadan = idHakmilikSmpdn;
        else
            idHakmilikSempadan = getContext().getRequest().getParameter("idHakmilikSmpdn");
        String kegunaanSempadan = getContext().getRequest().getParameter("kegunaan");
        String keadaanTanahSempadan = getContext().getRequest().getParameter("keadaanTanah");
        String catatanSempadan = getContext().getRequest().getParameter("catatan");
        String milikKerajaanSempadan = getContext().getRequest().getParameter("milikKerajaan");
        if(!StringUtils.isBlank(typeIndex));
         numType = typeIndex.equals("U")?1:typeIndex.equals("B")?2:typeIndex.equals("T")?3:typeIndex.equals("S")?4:0;
         
         String[] data = {idHakmilikSempadan,kegunaanSempadan,keadaanTanahSempadan,catatanSempadan,milikKerajaanSempadan};
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
                updateKandungan("U", data);
                break;
            case 2: 
                updateKandungan("B", data);
                break;
            case 3:
                updateKandungan("T", data);               
                break;
            case 4:
                updateKandungan("S", data);
                break;
        }
                rehydrate();
//        getContext().getRequest().setAttribute("display", display);
//        addSimpleMessage("Maklumat Berjaya Disimpan");
//        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
        return new JSP(LSEMPADAN_PAGE).addParameter("tab", "true");
    }
    
    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        boolean updateDB = false;
        boolean updateDB2 = false;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 5:// MOHON LAPOR ULAS
                updateDB2 = updateMohonFasa();
                updateDB = updateKandunganUlas(kand);

                break;
        }
//        rehydrate();
        if(updateDB||updateDB2)
            addSimpleMessage("Maklumat Berjaya Disimpan");
         return new JSP(refreshData("syorPPT")).addParameter("tab", Boolean.TRUE);
//        return new JSP(SYOR_PAGE).addParameter("tab", "true").addParameter("msg","Maklumat Berjaya Disimpan");
    }
    public Boolean updateMohonFasa() {

        boolean updateDB = false;
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = new KodCawangan();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ksn = ctx.getRequest().getParameter("kodksn"); 
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        
        // Saving in MOHON_FASA
        stageId = "03ArahLaporanTanah";
        fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan != null) {
            fasaPermohonan.setInfoAudit(findAudit(fasaPermohonan, "update"));
        }else{
            fasaPermohonan.setInfoAudit(findAudit(fasaPermohonan, "new"));
        }
            fasaPermohonan.setPermohonan(permohonan);            
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            if(ksn != null){
                KodKeputusan kkp = kodKeputusanDAO.findById(ksn);
                fasaPermohonan.setKeputusan(kkp);
            }
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
           
        return updateDB;
    }
    public Boolean updateKandunganUlas(String kand) {

        boolean updateDB = false;
        sizeSenaraiLaporUlas = ctx.getRequest().getParameter("sizeLU"); 
        List<PermohonanLaporanUlasan> senaraiLaporanKandungan2 = new ArrayList<PermohonanLaporanUlasan>();        
            senaraiLaporanKandungan2 = plpservice.findUlasanByIdMohon(idPermohonan);
        
        if(Integer.valueOf(sizeSenaraiLaporUlas)>0){
            if(senaraiLaporanKandungan2.size()>0){
                if(Integer.valueOf(sizeSenaraiLaporUlas)>senaraiLaporanKandungan2.size())
                    updateDB = true;            
            }else
                updateDB = true;
        }
                
        if(updateDB){
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = new KodCawangan();
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());

            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            permohonanLaporanUlasan.setInfoAudit(findAudit(permohonanLaporanUlasan, "new"));
            permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
            permohonanLaporanUlasan.setCawangan(cawangan);
            permohonanLaporanUlasan.setUlasan(kand);
            permohonanLaporanUlasan.setPermohonan(permohonan);
            pelPtService.simpanPermohonanlaporanUlas(permohonanLaporanUlasan);
        }
        return updateDB;
    }
    
    public void updateKandungan(String jnsSmpdn, String[] data) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan)plpservice.findDAOByPK(pengguna.getKodCawangan(),pengguna.getKodCawangan().getKod());
       
        Hakmilik hakmilikSmpdn = new Hakmilik();
        if(data.length>0){
            hakmilikSmpdn = hakmilikDAO.findById(data[0]);
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = (LaporanTanah) findObject(laporanTanah,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
            InfoAudit ia = new InfoAudit();
            if(lt==null){
                lt = new LaporanTanah();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                plpservice.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = (LaporanTanah) findObject(laporanTanah,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
            //lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            
            if(lt!=null){
                if(hakmilikSmpdn!=null){
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();
                    if(StringUtils.isNotBlank(statusSempadan)&&(statusSempadan.equals("U"))){
                        lts = plpservice.findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                        if(lts!=null){
                             ia = new InfoAudit();
                             ia = lts.getInfoAudit();
                             ia.setDiKemaskiniOleh(pengguna);
                             ia.setTarikhKemaskini(new java.util.Date());
                             lts.setInfoAudit(ia);
                             lts.setLaporanTanah(lt);
                             lts.setHakmilik(hakmilikSmpdn);
                             lts.setGuna(data[1]);
                             lts.setKeadaanTanah(data[2]);
                             lts.setCatatan(data[3]);
                             lts.setMilikKerajaan(data[4]);
                             lts.setJenisSempadan(jnsSmpdn);
                             plpservice.simpanLaporanTanahSempadan(lts);
                             addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                        }
                    }else{
                        List<LaporanTanahSempadan> listLTS = plpservice.findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                        if(listLTS.size()>0){
                            addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                        }else{                        
                            lts = new LaporanTanahSempadan();
                            ia = new InfoAudit();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            lts.setInfoAudit(ia);
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            lts.setJenisSempadan(jnsSmpdn);
                            plpservice.simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");                        
                        }
                    }
                    
//                    lts = plpservice.findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                    
                    
                }else{
                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
                }
            }            
        }
    }
    public Resolution simpanBersempadanan() throws ParseException {
        
       ctx = (etanahActionBeanContext) getContext();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       idHakmilik = ctx.getRequest().getParameter("idHakmilik"); 
       hakmilikPermohonan = new HakmilikPermohonan();
       LaporanTanah laporanTanahTemp = new LaporanTanah();
       hakmilikPermohonan = (HakmilikPermohonan) findObject(hakmilikPermohonan,new String[]{idPermohonan,idHakmilik}); 
        if(hakmilikPermohonan!=null)
            laporanTanahTemp = (LaporanTanah) findObject(laporanTanahTemp,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
       if(laporanTanahTemp!=null){
            laporanTanahTemp.setInfoAudit(findAudit(laporanTanahTemp, "update"));
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
            plpservice.simpanKemaskiniLaporanTanah(laporanTanahTemp);      
       }
       rehydrate() ;
       addSimpleMessage("Maklumat Telah Berjaya disimpan");
       return new JSP(SEMPADAN_PAGE).addParameter("tab", "true");

    }
    
    public Resolution simpanKeadaanTanah() throws ParseException {
        
       ctx = (etanahActionBeanContext) getContext();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       idHakmilik = ctx.getRequest().getParameter("idHakmilik"); 
       hakmilikPermohonan = new HakmilikPermohonan();
       LaporanTanah laporanTanahTemp = new LaporanTanah();
       hakmilikPermohonan = (HakmilikPermohonan) findObject(hakmilikPermohonan,new String[]{idPermohonan,idHakmilik}); 
        if(hakmilikPermohonan!=null)
            laporanTanahTemp = (LaporanTanah) findObject(laporanTanahTemp,new String[]{String.valueOf(hakmilikPermohonan.getId())}); 
       if(laporanTanahTemp!=null){
            laporanTanahTemp.setInfoAudit(findAudit(laporanTanahTemp, "update"));            
            if(!StringUtils.isEmpty(kecerunanT)){
                laporanTanahTemp.setKecerunanTanah(kodKecerunanTanahDAO.findById(kecerunanT));
                if(kecerunanT.equals("TG")){
                    laporanTanahTemp.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                    laporanTanahTemp.setKecerunanBukit(null);
                    laporanTanahTemp.setParasAir(null);
                }
                else if(kecerunanT.equals("CR")){
                    laporanTanahTemp.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                    laporanTanahTemp.setKetinggianDariJalan(null);
                    laporanTanahTemp.setParasAir(null);
                }
                else if(kecerunanT.equals("PY")){
                    laporanTanahTemp.setParasAir(laporanTanah.getParasAir());
                    laporanTanahTemp.setKetinggianDariJalan(null);
                    laporanTanahTemp.setKecerunanBukit(null);
                }
            }
            
            
            
            if(!StringUtils.isEmpty(klasifikasiT)){
                laporanTanahTemp.setStrukturTanah(kodStrukturTanahDAO.findById(klasifikasiT));
            }
            
            
            laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
            if(!StringUtils.isEmpty(keadaanTanah)){
                    laporanTanah.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanTanah));
                // Saving into MOHON LAPOR KAND
                if(keadaanTanah.equals("LL")){
                    permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
                 if(permohonanLaporanKandungan !=null){
                    permohonanLaporanKandungan.setInfoAudit(findAudit(permohonanLaporanKandungan, "new"));
                 }else{
                    permohonanLaporanKandungan.setInfoAudit(findAudit(permohonanLaporanKandungan, "update"));
                 }
                    permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                    permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                    permohonanLaporanKandungan.setKand(keadaankand);
                    plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);                    
                }
            }
            /*
             * TANAH DIUSAHAKAN
             */
            if(laporanTanah.getUsaha()!=null&&laporanTanah.getUsaha().equals('Y')){
                laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                if(laporanTanah.getTarikhMulaUsaha2()!=null)
                    laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
                laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());

                if(usahaLuas!=null&&!StringUtils.isEmpty(usahaLuas.toString())){
                    laporanTanahTemp.setUsahaLuas(usahaLuas);
                }

                if(usahaBilanganPokok != null){
                    laporanTanahTemp.setUsahaBilanganPokok(usahaBilanganPokok);
                }

                if(usahaHarga!=null&&!StringUtils.isEmpty(usahaHarga.toString())){
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
            plpservice.simpanKemaskiniLaporanTanah(laporanTanahTemp);      
       }
       rehydrate() ;
       addSimpleMessage("Maklumat Telah Berjaya disimpan");
       return new JSP(KTANAH_PAGE).addParameter("tab", "true");

    }
    
    public Resolution simpanPerihal() throws ParseException {
       ctx = (etanahActionBeanContext) getContext();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       idHakmilik = ctx.getRequest().getParameter("idHakmilik"); 
       HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();       
       mohonHakmilik = (HakmilikPermohonan) findObject(mohonHakmilik,new String[]{idPermohonan,idHakmilik});              
       PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();       
       mohonLaporPelan = (PermohonanLaporanPelan)findObject(mohonLaporPelan,new String[]{idPermohonan});
       TanahRizabPermohonan mohonTanahRizab = new TanahRizabPermohonan();
       if(StringUtils.isNotBlank(tanahR)){            
            mohonTanahRizab = (TanahRizabPermohonan) findObject(mohonTanahRizab,new String[]{idPermohonan,idHakmilik});
            if(mohonTanahRizab!=null){
                mohonTanahRizab.setInfoAudit(findAudit(mohonTanahRizab,"update"));
            }else{
                mohonTanahRizab = new TanahRizabPermohonan();
                mohonTanahRizab.setInfoAudit(findAudit(mohonTanahRizab,"new"));
                mohonTanahRizab.setPermohonan(mohonHakmilik.getPermohonan());
                mohonTanahRizab.setCawangan(mohonHakmilik.getHakmilik().getCawangan());
                mohonTanahRizab.setDaerah(mohonHakmilik.getHakmilik().getDaerah());
                mohonTanahRizab.setNoHakmilik(mohonHakmilik.getHakmilik().getNoHakmilik());
                mohonTanahRizab.setBandarPekanMukim(mohonHakmilik.getHakmilik().getBandarPekanMukim());
                mohonTanahRizab.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                
            }
            
            KodRizab kr = kodRizabDAO.findById(Integer.parseInt(tanahR));
            mohonTanahRizab.setRizab(kr);
            
       }
       if(mohonTanahRizab!=null){
           if(mohonTanahRizab.getPermohonan()!=null){
                mohonTanahRizab.setNoWarta(tanahrizabpermohonan1.getNoWarta());
                plpservice.simpanTanahRizabPermohonan(mohonTanahRizab);
           }
       }
       if(StringUtils.isNotBlank(kodT)) {
            KodTanah ktt = kodTanahDAO.findById(kodT);
            mohonLaporPelan.setKodTanah(ktt);
            
            if(kodT.equalsIgnoreCase("99")){
                 permohonanLaporanKandungan = (PermohonanLaporanKandungan) findObject(permohonanLaporanKandungan,new String[]{"Lain-lain Jenis Tanah",String.valueOf(laporanTanah.getIdLaporan())});
                 if(permohonanLaporanKandungan !=null){
                     permohonanLaporanKandungan.setInfoAudit(findAudit(permohonanLaporanKandungan,"update"));
                 }else{
                     permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                     permohonanLaporanKandungan.setInfoAudit(findAudit(permohonanLaporanKandungan,"new"));                 
                 }
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Jenis Tanah");
                permohonanLaporanKandungan.setKand(kand);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
           }
            mohonLaporPelan.setInfoAudit(findAudit(mohonLaporPelan, "update"));
            plpservice.simpanPermohonanLaporanPelan(mohonLaporPelan);
       }
       
      
       if(mohonHakmilik!=null){
           if(laporanTanah!=null){
               /*
                * LAPORAN TANAH
                */
                LaporanTanah laporanTanahTemp = new LaporanTanah();
                laporanTanahTemp = (LaporanTanah) findObject(laporanTanahTemp,new String[]{String.valueOf(mohonHakmilik.getId())}); 
                if(laporanTanahTemp!=null){
                    if(StringUtils.isNotBlank(laporanTanah.getBolehBerimilik())){
                            laporanTanahTemp.setBolehBerimilik(laporanTanah.getBolehBerimilik());
                        if(laporanTanah.getBolehBerimilik().equalsIgnoreCase("T"))
                            laporanTanahTemp.setSebabTidakBolehBerimilik(laporanTanah.getSebabTidakBolehBerimilik());
                    }
                    plpservice.simpanLaporanTanah(laporanTanahTemp);
                }
           }
           
            
            /*
             * HAKMILIKPERMOHONAN
             */
             mohonHakmilik.setLokasi(hakmilikPermohonan.getLokasi());
             mohonHakmilik.setInfoAudit(findAudit(mohonHakmilik, "update"));
             plpservice.simpanHakmilikPermohonan(mohonHakmilik);
       }       
       
       rehydrate();
       addSimpleMessage("Maklumat Telah Berjaya disimpan");
       return new JSP(PTANAH_PAGE).addParameter("tab", "true");

    }
    /*
     * Author = Shazwan
     * Purpose : To Save Bangunan
     */
    public Resolution simpanBangunan() throws ParseException {
       ctx = (etanahActionBeanContext) getContext();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       idHakmilik = ctx.getRequest().getParameter("idHakmilik"); 
       HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();       
       mohonHakmilik = (HakmilikPermohonan) findObject(mohonHakmilik,new String[]{idPermohonan,idHakmilik});              
       laporanTanah = (LaporanTanah) plpservice.findDAOByPK(laporanTanah,Long.parseLong(idLaporTanah));
       if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            PermohonanLaporanBangunan permohonanLaporanBangunanCRUD = new PermohonanLaporanBangunan();
            permohonanLaporanBangunanCRUD.setInfoAudit(findAudit(permohonanLaporanBangunanCRUD, "new"));
            permohonanLaporanBangunanCRUD.setPermohonan(permohonan);
            permohonanLaporanBangunanCRUD.setCawangan(permohonan.getCawangan());
            permohonanLaporanBangunanCRUD.setLaporanTanah(laporanTanah);
            permohonanLaporanBangunanCRUD.setKategori(kategori);
            permohonanLaporanBangunanCRUD.setJenisBangunan(permohonanLaporanBangunan.getJenisBangunan());
            if(permohonanLaporanBangunan.getUkuran()!=null&&!StringUtils.isEmpty(permohonanLaporanBangunan.getUkuran().toString())){
                permohonanLaporanBangunanCRUD.setUkuran(permohonanLaporanBangunan.getUkuran());
            }
            if(permohonanLaporanBangunan.getUomUkuran()!=null){
                permohonanLaporanBangunanCRUD.setUomUkuran(kodUOMDAO.findById(permohonanLaporanBangunan.getUomUkuran().getKod()));
            }
            if(permohonanLaporanBangunan.getNilai()!=null&&!StringUtils.isEmpty(permohonanLaporanBangunan.getNilai().toString())){
                permohonanLaporanBangunanCRUD.setNilai(permohonanLaporanBangunan.getNilai());
            }
            if(permohonanLaporanBangunan.getTahunDibina()!=null&&permohonanLaporanBangunan.getTahunDibina()>0){
                permohonanLaporanBangunanCRUD.setTahunDibina(permohonanLaporanBangunan.getTahunDibina());
            }
            permohonanLaporanBangunanCRUD.setNamaPemunya(permohonanLaporanBangunan.getNamaPemunya());
            permohonanLaporanBangunanCRUD.setNamaKetua(permohonanLaporanBangunan.getNamaKetua());
            if(permohonanLaporanBangunan.getJenisPendudukan()!=null){
                permohonanLaporanBangunanCRUD.setJenisPendudukan((KodJenisPendudukan)plpservice.findDAOByPK(permohonanLaporanBangunan.getJenisPendudukan(),permohonanLaporanBangunan.getJenisPendudukan().getKod()));
            }
            laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunanCRUD);            
        }
       permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
       if(permohonanLaporanBangunanList.size()>0){
           laporanTanah.setAdaBangunan('Y');
       }
       //addSimpleMessage("Maklumat Telah Berjaya disimpan");
       return new JSP(KTANAH_PAGE).addParameter("tab", "true");

    }
    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : to find DATA based on Object Given
     */
    public Object findObject(Object obj,String[] data){
        
        if(obj instanceof HakmilikPermohonan){
            // data[0] = idPermohonan, data[1] = idHakmilik            
            obj = plpservice.findByIdHakmilikIdPermohonan(data[0], data[1]);
        }
        else if(obj instanceof LaporanTanah){
            // data[0] = hakmilikPermohonan.getId();
            obj = plpservice.findLaporanTanahByIdMH(Long.parseLong(data[0]));
        }
        else if(obj instanceof PermohonanLaporanPelan){
            // data[0] = idPermohonan
            obj = plpservice.findByIdPermohonanPelan(data[0]);
        }
        else if(obj instanceof TanahRizabService){
            // data[0] = idPermohonan
            obj = tanahRizabService.findByidPermohonan(data[0]);
        }
        else if(obj instanceof TanahRizabPermohonan){
            // data[0] = idPermohonan, data[1] = idHakmilik
            obj = plpservice.findTanahRizabByIdPermohonanNnoHM(data[0],data[1]); //MULTIPLE HM
        }
        else if(obj instanceof PermohonanLaporanKandungan){
             // data[0] = "Lain-lain Jenis Tanah", data[1] = laporanTanah.getIdLaporan();
             obj = plpservice.findByIdLaporSubtajuk(data[0], Long.parseLong(data[1]));
        }
        else if(obj instanceof LaporanTanahSempadan){
            // data[0] = "idKand"
             obj = plpservice.findLaporanTanahSempadanById(Long.parseLong(data[0]));
        }
        else if(obj instanceof PermohonanLaporanUlasan){
            // data[0] = "idKand"
             obj = plpservice.findPermohonanLaporanUlasanById(Long.parseLong(data[0]));
        }
        else if(obj instanceof PermohonanLaporanKawasan){
            // data[0] = "idKand"
             obj = plpservice.findPermohonanLaporanKawasanById(Long.parseLong(data[0]));
        }
        return obj;
    }
    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : to Delete Data In Database based on Object Given
     */
    public String delObject(String tName,String[] data){
        String type = new String();
        if(tName.equals("mohonLaporSempadan")){            
            // data[0] = "idKand"
            LaporanTanahSempadan plk = new LaporanTanahSempadan();
            plk = (LaporanTanahSempadan) findObject(plk,new String[]{String.valueOf(data[0])});
            if (plk != null) {                    
                pelPtService.deleteLaporanTanahSempadan(plk);
                addSimpleMessage("Maklumat Berjaya Dihapuskan");
            }
            type = "lSempadan";             
        }
        else if(tName.equals("mohonLaporUlas")){            
            // data[0] = "idKand"
            PermohonanLaporanUlasan mohonLaporUlas = new PermohonanLaporanUlasan();
            mohonLaporUlas = (PermohonanLaporanUlasan) findObject(mohonLaporUlas,new String[]{String.valueOf(data[0])});
            if (mohonLaporUlas != null) {                    
                pelPtService.deletePermohonanLaporanUlasan(mohonLaporUlas);
                addSimpleMessage("Maklumat Berjaya Dihapuskan");
            }
            type = "syorPPT";             
        }
        else if(tName.equals("mohonLaporKawasan")){            
            PermohonanLaporanKawasan mohonLaporKwsn = new PermohonanLaporanKawasan();
            mohonLaporKwsn = (PermohonanLaporanKawasan) findObject(mohonLaporKwsn,new String[]{String.valueOf(data[0])});
            if (mohonLaporKwsn != null) {                    
                plpservice.deletePermohonanLaporanKwsn(mohonLaporKwsn);
                addSimpleMessage("Maklumat Berjaya Dihapuskan");
            }
        }
        
        return type;
    }
    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : setting Audit based on type
     */
    public InfoAudit findAudit(Object obj,String type){        
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        if(obj instanceof HakmilikPermohonan){
            HakmilikPermohonan mohonHM = (HakmilikPermohonan) obj;
            info = mohonHM.getInfoAudit();            
        }            
        else if(obj instanceof LaporanTanah){
            LaporanTanah laporTanah = new LaporanTanah();
            laporTanah = (LaporanTanah) obj;
            info = laporTanah.getInfoAudit();
        }
        else if(obj instanceof PermohonanLaporanPelan){
            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
            mohonLaporPelan = (PermohonanLaporanPelan) obj;
            info = mohonLaporPelan.getInfoAudit();
        }
        else if(obj instanceof TanahRizabPermohonan){
            TanahRizabPermohonan trizab = new TanahRizabPermohonan();
            trizab = (TanahRizabPermohonan) obj;
            info = trizab.getInfoAudit();
        }else if(obj instanceof PermohonanLaporanKandungan){
            PermohonanLaporanKandungan mohonLaporKand = new PermohonanLaporanKandungan();
            mohonLaporKand = (PermohonanLaporanKandungan) obj;
            info = mohonLaporKand.getInfoAudit();
        }
        else if(obj instanceof PermohonanLaporanBangunan){
            PermohonanLaporanBangunan mohonLaporBangunan = new PermohonanLaporanBangunan();
            mohonLaporBangunan = (PermohonanLaporanBangunan) obj;
            info = mohonLaporBangunan.getInfoAudit();
        }
        else if(obj instanceof PermohonanLaporanKawasan){
            PermohonanLaporanKawasan mohonLaporKawasan = new PermohonanLaporanKawasan();
            mohonLaporKawasan = (PermohonanLaporanKawasan) obj;
            info = mohonLaporKawasan.getInfoAudit();
        }
        else if(obj instanceof PermohonanLaporanUlasan){
            PermohonanLaporanUlasan mohonLaporUlasan = new PermohonanLaporanUlasan();
            mohonLaporUlasan = (PermohonanLaporanUlasan) obj;
            info = mohonLaporUlasan.getInfoAudit();
        }
        else if(obj instanceof FasaPermohonan){
            FasaPermohonan mohonFasa = new FasaPermohonan();
            mohonFasa = (FasaPermohonan) obj;
            info = mohonFasa.getInfoAudit();
        }
        
        if(type.equalsIgnoreCase("new")){
            info = new InfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());                
        }else if(type.equalsIgnoreCase("update")){
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
        }
        
        return info;
    }
    public Resolution reload() {        
        return showFormDisplay();
    }

    public Resolution showFormDisplay() {
//        statusPage = new String();
        display = Boolean.TRUE;
        rehydrate();
        return new JSP(MAIN_PAGE).addParameter("tab", Boolean.TRUE);
    }
    
    public Resolution tambahBangunanPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/gsa/laporan_tanahGSA_popup.jsp").addParameter("popup", "true");
    }
    public Resolution editBangunanPopup() {
        String idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/gsa/laporan_tanahGSA_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution kawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/gsa/tambahKawasanLaporanTanahGSA.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }
    
    public Resolution simpanKawasan() throws ParseException {
       PermohonanLaporanKawasan kws = new PermohonanLaporanKawasan();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       permohonan = permohonanDAO.findById(idPermohonan);
       idHakmilik = ctx.getRequest().getParameter("idHakmilik"); 
       HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();       
       mohonHakmilik = (HakmilikPermohonan) findObject(mohonHakmilik,new String[]{idPermohonan,idHakmilik});
       kws.setInfoAudit(findAudit(kws, "new"));
       kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
       if(kodRizab.equals("99"))
           kws.setCatatan(addcatatan);
       kws.setNoWarta(addnoWarta);
       if(addtarikhWarta!=null&&!("").equals(addtarikhWarta))
           kws.setTarikhWarta(sdf.parse(addtarikhWarta));
       kws.setNoPelanWarta(addnoPelanWarta);
       kws.setPermohonan(permohonan);
       kws.setKodCawangan(permohonan.getCawangan());
       kws.setHakmilikPermohonan(mohonHakmilik);
       plpservice.simpanPermohonanLaporKawasan(kws);
       addSimpleMessage("Maklumat Berjaya Disimpan");         
       rehydrate();
       return new JSP(DKAWASAN_PAGE).addParameter("tab", "true");
       //return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }
    
    public Resolution removeLaporKawasan()  {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idMohonlaporKws = getContext().getRequest().getParameter("idMohonlaporKws");

        if (idMohonlaporKws != null) {
            PermohonanLaporanKawasan mohonLaporKwsn = new PermohonanLaporanKawasan();
            mohonLaporKwsn = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));
            if (mohonLaporKwsn != null) {
                plpservice.deletePermohonanLaporanKwsn(mohonLaporKwsn);

            }
        }
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSADalamKawasan.jsp").addParameter("tab", "true");
    }
    public char getAdaJalanMasuk() {
        return adaJalanMasuk;
    }

    public void setAdaJalanMasuk(char adaJalanMasuk) {
        this.adaJalanMasuk = adaJalanMasuk;
    }

    public String getAdaPecahSempadan() {
        return adaPecahSempadan;
    }

    public void setAdaPecahSempadan(String adaPecahSempadan) {
        this.adaPecahSempadan = adaPecahSempadan;
    }

    public String getAddcatatan() {
        return addcatatan;
    }

    public void setAddcatatan(String addcatatan) {
        this.addcatatan = addcatatan;
    }

    public String getAddnoPelanWarta() {
        return addnoPelanWarta;
    }

    public void setAddnoPelanWarta(String addnoPelanWarta) {
        this.addnoPelanWarta = addnoPelanWarta;
    }

    public String getAddnoWarta() {
        return addnoWarta;
    }

    public void setAddnoWarta(String addnoWarta) {
        this.addnoWarta = addnoWarta;
    }

    public String getAddtarikhWarta() {
        return addtarikhWarta;
    }

    public void setAddtarikhWarta(String addtarikhWarta) {
        this.addtarikhWarta = addtarikhWarta;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCatatanKeg() {
        return catatanKeg;
    }

    public void setCatatanKeg(String catatanKeg) {
        this.catatanKeg = catatanKeg;
    }

    public String getCatatanLain1() {
        return catatanLain1;
    }

    public void setCatatanLain1(String catatanLain1) {
        this.catatanLain1 = catatanLain1;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public boolean isCheckTanahExist() {
        return checkTanahExist;
    }

    public void setCheckTanahExist(boolean checkTanahExist) {
        this.checkTanahExist = checkTanahExist;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getGsa() {
        return gsa;
    }

    public void setGsa(String gsa) {
        this.gsa = gsa;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public HakmilikUrusanService getHakmilikUrusanService() {
        return hakmilikUrusanService;
    }

    public void setHakmilikUrusanService(HakmilikUrusanService hakmilikUrusanService) {
        this.hakmilikUrusanService = hakmilikUrusanService;
    }

    public String getHutan() {
        return hutan;
    }

    public void setHutan(String hutan) {
        this.hutan = hutan;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdlapor() {
        return idlapor;
    }

    public void setIdlapor(Long idlapor) {
        this.idlapor = idlapor;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public char getJenisKegunaan() {
        return jenisKegunaan;
    }

    public void setJenisKegunaan(char jenisKegunaan) {
        this.jenisKegunaan = jenisKegunaan;
    }

    public BigDecimal getJumlahIsipadu() {
        return jumlahIsipadu;
    }

    public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
        this.jumlahIsipadu = jumlahIsipadu;
    }

    public KodUOM getJumlahIsipaduUOM() {
        return jumlahIsipaduUOM;
    }

    public void setJumlahIsipaduUOM(KodUOM jumlahIsipaduUOM) {
        this.jumlahIsipaduUOM = jumlahIsipaduUOM;
    }

    public String getKand() {
        return kand;
    }

    public void setKand(String kand) {
        this.kand = kand;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKeadaankand() {
        return keadaankand;
    }

    public void setKeadaankand(String keadaankand) {
        this.keadaankand = keadaankand;
    }

    public String getKecerunanT() {
        return kecerunanT;
    }

    public void setKecerunanT(String kecerunanT) {
        this.kecerunanT = kecerunanT;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public String getKlasifikasiT() {
        return klasifikasiT;
    }

    public void setKlasifikasiT(String klasifikasiT) {
        this.klasifikasiT = klasifikasiT;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodD() {
        return kodD;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
    }

    public KodDUN getKodDUN() {
        return kodDUN;
    }

    public void setKodDUN(KodDUN kodDUN) {
        this.kodDUN = kodDUN;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
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

    public KodItemPermit getKodItemPermit() {
        return kodItemPermit;
    }

    public void setKodItemPermit(KodItemPermit kodItemPermit) {
        this.kodItemPermit = kodItemPermit;
    }

    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public KodKawasanParlimen getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(KodKawasanParlimen kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public KodKeadaanTanah getKodKeadaanTanah() {
        return kodKeadaanTanah;
    }

    public void setKodKeadaanTanah(KodKeadaanTanah kodKeadaanTanah) {
        this.kodKeadaanTanah = kodKeadaanTanah;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public char getKodP() {
        return kodP;
    }

    public void setKodP(char kodP) {
        this.kodP = kodP;
    }

    public String getKodPar() {
        return kodPar;
    }

    public void setKodPar(String kodPar) {
        this.kodPar = kodPar;
    }

    public KodPemilikan getKodPemilikan() {
        return kodPemilikan;
    }

    public void setKodPemilikan(KodPemilikan kodPemilikan) {
        this.kodPemilikan = kodPemilikan;
    }

    public String getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(String kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public KodSekatanKepentinganDAO getKodSekatanKepentinganDAO() {
        return kodSekatanKepentinganDAO;
    }

    public void setKodSekatanKepentinganDAO(KodSekatanKepentinganDAO kodSekatanKepentinganDAO) {
        this.kodSekatanKepentinganDAO = kodSekatanKepentinganDAO;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public KodStrukturTanah getKodStrukturTanah() {
        return kodStrukturTanah;
    }

    public void setKodStrukturTanah(KodStrukturTanah kodStrukturTanah) {
        this.kodStrukturTanah = kodStrukturTanah;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKodT() {
        return kodT;
    }

    public void setKodT(String kodT) {
        this.kodT = kodT;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }

    public KodTanahDAO getKodTanahDAO() {
        return kodTanahDAO;
    }

    public void setKodTanahDAO(KodTanahDAO kodTanahDAO) {
        this.kodTanahDAO = kodTanahDAO;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public KodTuntutDAO getKodTuntutDAO() {
        return kodTuntutDAO;
    }

    public void setKodTuntutDAO(KodTuntutDAO kodTuntutDAO) {
        this.kodTuntutDAO = kodTuntutDAO;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getKodUPlps() {
        return kodUPlps;
    }

    public void setKodUPlps(String kodUPlps) {
        this.kodUPlps = kodUPlps;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public PermohonanLaporanUlasan getLaporanUlasan() {
        return laporanUlasan;
    }

    public void setLaporanUlasan(PermohonanLaporanUlasan laporanUlasan) {
        this.laporanUlasan = laporanUlasan;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public BigDecimal getLuasDilulus() {
        return luasDilulus;
    }

    public void setLuasDilulus(BigDecimal luasDilulus) {
        this.luasDilulus = luasDilulus;
    }

    public String getMohonlaporulasan() {
        return mohonlaporulasan;
    }

    public void setMohonlaporulasan(String mohonlaporulasan) {
        this.mohonlaporulasan = mohonlaporulasan;
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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public NoPt getNoPT() {
        return noPT;
    }

    public void setNoPT(NoPt noPT) {
        this.noPT = noPT;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public String getPbt1() {
        return pbt1;
    }

    public void setPbt1(String pbt1) {
        this.pbt1 = pbt1;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
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

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPList() {
        return permohonanLaporanBangunanPList;
    }

    public void setPermohonanLaporanBangunanPList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList) {
        this.permohonanLaporanBangunanPList = permohonanLaporanBangunanPList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPUList() {
        return permohonanLaporanBangunanPUList;
    }

    public void setPermohonanLaporanBangunanPUList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList) {
        this.permohonanLaporanBangunanPUList = permohonanLaporanBangunanPUList;
    }

    public PermohonanLaporanKandungan getPermohonanLaporanKandungan() {
        return permohonanLaporanKandungan;
    }

    public void setPermohonanLaporanKandungan(PermohonanLaporanKandungan permohonanLaporanKandungan) {
        this.permohonanLaporanKandungan = permohonanLaporanKandungan;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public String getPlpulasan1() {
        return plpulasan1;
    }

    public void setPlpulasan1(String plpulasan1) {
        this.plpulasan1 = plpulasan1;
    }

    public Permohonan getPrmhnn() {
        return prmhnn;
    }

    public void setPrmhnn(Permohonan prmhnn) {
        this.prmhnn = prmhnn;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getRizab1() {
        return rizab1;
    }

    public void setRizab1(String rizab1) {
        this.rizab1 = rizab1;
    }

    public String getRizab2() {
        return rizab2;
    }

    public void setRizab2(String rizab2) {
        this.rizab2 = rizab2;
    }

    public String getRizab_melayu() {
        return rizab_melayu;
    }

    public void setRizab_melayu(String rizab_melayu) {
        this.rizab_melayu = rizab_melayu;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanLaporanUlasan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public List<PermohonanLaporanUlasan> getSenarailaporanUlasan() {
        return senarailaporanUlasan;
    }

    public void setSenarailaporanUlasan(List<PermohonanLaporanUlasan> senarailaporanUlasan) {
        this.senarailaporanUlasan = senarailaporanUlasan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public String getTanahR() {
        return tanahR;
    }

    public void setTanahR(String tanahR) {
        this.tanahR = tanahR;
    }

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }

    public TanahRizabPermohonanDAO getTanahRizabPermohonanDAO() {
        return tanahRizabPermohonanDAO;
    }

    public void setTanahRizabPermohonanDAO(TanahRizabPermohonanDAO tanahRizabPermohonanDAO) {
        this.tanahRizabPermohonanDAO = tanahRizabPermohonanDAO;
    }

    public List<TanahRizabPermohonan> getTanahRizabPermohonanList() {
        return tanahRizabPermohonanList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabPermohonanList) {
        this.tanahRizabPermohonanList = tanahRizabPermohonanList;
    }

    public TanahRizabService getTanahRizabService() {
        return tanahRizabService;
    }

    public void setTanahRizabService(TanahRizabService tanahRizabService) {
        this.tanahRizabService = tanahRizabService;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan1() {
        return tanahrizabpermohonan1;
    }

    public void setTanahrizabpermohonan1(TanahRizabPermohonan tanahrizabpermohonan1) {
        this.tanahrizabpermohonan1 = tanahrizabpermohonan1;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getTempohSyorUOM() {
        return tempohSyorUOM;
    }

    public void setTempohSyorUOM(String tempohSyorUOM) {
        this.tempohSyorUOM = tempohSyorUOM;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUlasanKanan() {
        return ulasanKanan;
    }

    public void setUlasanKanan(String ulasanKanan) {
        this.ulasanKanan = ulasanKanan;
    }

    public String getUlsn() {
        return ulsn;
    }

    public void setUlsn(String ulsn) {
        this.ulsn = ulsn;
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

    public BigDecimal getUsahaLuas() {
        return usahaLuas;
    }

    public void setUsahaLuas(BigDecimal usahaLuas) {
        this.usahaLuas = usahaLuas;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdLaporTanah() {
        return idLaporTanah;
    }

    public void setIdLaporTanah(String idLaporTanah) {
        this.idLaporTanah = idLaporTanah;
    }

    public List getListLaporTanahSpdnB() {
        return listLaporTanahSpdnB;
    }

    public void setListLaporTanahSpdnB(List listLaporTanahSpdnB) {
        this.listLaporTanahSpdnB = listLaporTanahSpdnB;
    }

    public List getListLaporTanahSpdnS() {
        return listLaporTanahSpdnS;
    }

    public void setListLaporTanahSpdnS(List listLaporTanahSpdnS) {
        this.listLaporTanahSpdnS = listLaporTanahSpdnS;
    }

    public List getListLaporTanahSpdnT() {
        return listLaporTanahSpdnT;
    }

    public void setListLaporTanahSpdnT(List listLaporTanahSpdnT) {
        this.listLaporTanahSpdnT = listLaporTanahSpdnT;
    }

    public List getListLaporTanahSpdnU() {
        return listLaporTanahSpdnU;
    }

    public void setListLaporTanahSpdnU(List listLaporTanahSpdnU) {
        this.listLaporTanahSpdnU = listLaporTanahSpdnU;
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

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public String getHakmilik_ref() {
        return hakmilik_ref;
    }

    public void setHakmilik_ref(String hakmilik_ref) {
        this.hakmilik_ref = hakmilik_ref;
    }
    
    public String getIdHakmilikSmpdn() {
        return idHakmilikSmpdn;
    }

    public void setIdHakmilikSmpdn(String idHakmilikSmpdn) {
        this.idHakmilikSmpdn = idHakmilikSmpdn;
    }

    public String getJenisSmpdn() {
        return jenisSmpdn;
    }

    public void setJenisSmpdn(String jenisSmpdn) {
        this.jenisSmpdn = jenisSmpdn;
    }

    public String getKegunaanSmpdn() {
        return kegunaanSmpdn;
    }

    public void setKegunaanSmpdn(String kegunaanSmpdn) {
        this.kegunaanSmpdn = kegunaanSmpdn;
    }
    
    public String getMilikKerajaanSmpdn() {
        return milikKerajaanSmpdn;
    }

    public void setMilikKerajaanSmpdn(String milikKerajaanSmpdn) {
        this.milikKerajaanSmpdn = milikKerajaanSmpdn;
    }

    public String getStatusSempadan() {
        return statusSempadan;
    }

    public void setStatusSempadan(String statusSempadan) {
        this.statusSempadan = statusSempadan;
    }

    public String getSizeSenaraiLaporUlas() {
        return sizeSenaraiLaporUlas;
    }

    public void setSizeSenaraiLaporUlas(String sizeSenaraiLaporUlas) {
        this.sizeSenaraiLaporUlas = sizeSenaraiLaporUlas;
    }
    
    
}
