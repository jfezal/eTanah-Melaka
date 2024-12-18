//    Document   : DrafMMKNActionBean.java
//    Created on : May 04, 2010, 4:40:36 PM
//    Author     : murali
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.KodKadarPremium;
//import etanah.service.BPelService;
import etanah.model.KodTuntut;
import etanah.model.KodUOM;
import etanah.model.LaporanTanahSempadan;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.RegService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
//import java.text.SimpleDateFormat;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.workflow.WorkFlowService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.apache.log4j.Logger;
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pelupusan/draf_mmkn_pptr")
public class DrafMMKN_PPTRActionBean extends AbleActionBean {

    Logger logger = Logger.getLogger(DrafMMKN_PLPSActionBean.class);
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    BPelService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanPihakDAO PermohonanPihakDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    PemohonHubunganDAO PemohonHubunganDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
//    @Inject
//    KodItemPermitDAO kodItemPermitDAO;
    private List<KodItemPermit> senaraiKodItemPermit;
    private PermohonanPermitItem permohonanPermitItem;
    private KodTuntut kodTuntut;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private KodKadarPremium kodKadarPremium;
    private List<KodKadarPremium> senaraikodKadarPremium;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertas permohonankertas;
    private LaporanTanah laporanTanah;
    private PermohonanPlotPelan permohonanPlotPelan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String stageId;
    private String syortolaklulus;
    private Pengguna peng;
    private PemohonHubungan pemohonHubungan;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    String pejTanah;
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private PihakPengarah pihakPengarah;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private HakmilikPermohonan hakmilikPermohonan;
    private DisLaporanTanahSempadan disLaporanTanahSempadan;
    private String huraianPentadbir2;
    private String huraianPentadbir3;
    private String huraianPentadbir4;
    private String huraianPentadbir5;
    private String syorPentadbir2;
    private String syorPentadbir3;
    private String syorPentadbir4;
    private String syorPentadbir5;
    private String huraianPtg2;
    private String huraianPtg3;
    private String huraianPtg4;
    private String huraianPtg5;
    private String syorPtg2;
    private String syorPtg3;
    private String syorPtg4;
    private String syorPtg5;
    private boolean btn = true;
    private String lokasi;
    private String index;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private String sekatKpntgn2;
    private String syaratNyata2;
    private String kodSktn;
    private String kod;
    private String jenishakmilik;
    private String tempoh;
    private String hasil;
    private String kodHmlk;
    private KodHakmilik kodHakmilik;
    private String catatan;
    private Boolean edit;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodDUN kodDun;
    private KodDokumen kodDokumen;
    private FasaPermohonan fasaPermohonan;
    private KodKeputusan kodKeputusan;
    private String kodKepu;
    private String participant;
    private String tajuk;
    private String perihalpermohonan;
    private String perihalpermohonan1;
    private String perihalpermohonan2;
    private String perihaltanah;
    private String perihalBadanPengawal;
//    private String perihaltanah1;
    private String perihalpemohon;
    private String perihalpemohon_s;
    private String perihalpemohon1;
    private String perihalpemohon2;
    private String perihalpemohon2suami;
    private String pentabirtanah1;
    private String pentabirtanah2;
    private String ulasanyb;
    private String hurianpengarah;
    private String syorpengarah;
    private String tajukPlps;
    private String tujuanPlps;
    private String perihalpermohonanPlps;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan3;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah;
    private List<PermohonanKertasKandungan> senaraiPerihalTanah;
    private List<PermohonanRujukanLuar> senaraiYBAdun;
    private String syaratNyata;
    private String syaratNyata1;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private PermohonanPermitItem permitItem;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
    private String year;
    private KodUOM kULuas;
    private List senaraiLaporanKandunganUtil;
    private List<PermohonanRujukanLuar> senaraiLaporanKandunganRujukLuar;
    private boolean ptd;
    private boolean editPTD;
    private boolean editPTG;
    private boolean ptg;
    private boolean openPTG;
    private boolean firstTimeOpen;
    private PermohonanBahanBatuan permohonanBahanBatuan;

    /*
     * LEGEND : 
     * EDITPTD TRUE  : IF PTD IS EDITABLE
     * EDITPTD FALSE : IF PTD IS UNEDITABLE
     * editPTG TRUE  : IF PTG IS EDITABLE
     * editPTG FALSE : IF PTG IS UNEDITABLE
     */
    @DefaultHandler
    public Resolution showForm() {
//        edit = Boolean.FALSE;
        edit = Boolean.TRUE;
        ptd = Boolean.FALSE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.TRUE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTG() {
//        edit = Boolean.FALSE;
        edit = Boolean.TRUE;
        ptd = Boolean.FALSE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.TRUE;
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormForPTG() {
        edit = Boolean.TRUE;
        ptd = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        openPTG = Boolean.TRUE;
        editPTG = Boolean.TRUE;
//        ptg = Boolean.TRUE;
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormForPTD() {
        edit = Boolean.TRUE;
        ptd = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        openPTG = Boolean.FALSE;
        ptg = Boolean.TRUE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = pelupusanService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pelupusanService.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata("%", kc, syaratNyata2);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata_drafMMK.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        year = sdf.format(new Date());

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        logger.info("permohonan urusan :>>>>>>>>" + permohonan.getKodUrusan().getKod());
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
        PermohonanRujukanLuar permohonanRujukanLuarBdnPngwl = new PermohonanRujukanLuar();
        permohonanRujukanLuarBdnPngwl = pelupusanService.findPermohonanRujForBdnPngwal(idPermohonan);
        if (permohonanRujukanLuarBdnPngwl != null) {
            perihalBadanPengawal = permohonanRujukanLuarBdnPngwl.getUlasan();
        }
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanRujukanLuar> listprl = new Vector();
        listprl = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
        if (listprl.size() > 0) {
            PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
            prl = listprl.get(0);
            kodDun = pelupusanService.findKodDUNByAgensi(prl.getAgensi().getKod());
        }

        senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermit();
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if (pemohon != null) {
            pemohonHubungan = pelupusanService.findHubunganByIDIsteri(pemohon.getIdPemohon());
        }
        if (pemohon != null) {
            listPengarah = pelupusanService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
        }


        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        if ((permohonanLaporanPelan != null) && (permohonanLaporanPelan.getKodTanah() != null)) {
            logger.info("--------------KodTanah:" + permohonanLaporanPelan.getKodTanah().getKod());
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            logger.info("--------------KodGunaTanah:" + hakmilikPermohonan.getKodKegunaanTanah().getKod());
        }

        if ((permohonanLaporanPelan != null) && (permohonanLaporanPelan.getKodTanah() != null) && (hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            senaraikodKadarPremium = pelupusanService.findKodKadarPremiumNama(permohonanLaporanPelan.getKodTanah().getKod(), hakmilikPermohonan.getKodKegunaanTanah().getKod());
            logger.info("--------------senaraikodKadarPremium:" + senaraikodKadarPremium);
        }
        if (kodKadarPremium != null) {
        }

        if (pemohon != null) {
            pihak = pemohon.getPihak();
        }


        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }

//        Task task = service.getTaskFromBPel(taskId, peng);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
//        }
//REMOVE
//        stageId = "sedia_draf_mmkn";
        stageId = stageId(taskId);
        logger.info("----------stageID-------------------" + stageId);
//        /*
//         * FILTER FOR PTG STAGE
//         */
//            if (stageId.equals("semak_draf_mmkn3_1")||stageId.equals("semak_draf_mmkn3_2")||stageId.equals("semak_draf_mmkn3_3")||stageId.equals("pindaan_draf_mmkn")||stageId.equals("perakuan_ptg_risalat_mmkn")) {
//                ptd = Boolean.TRUE;
//                ptg = Boolean.TRUE;
//                openPTG = Boolean.TRUE;
//            }if(stageId.equals("terima_semak_risalat_mmkn")){
//                ptg = Boolean.FALSE;
//                openPTG = Boolean.TRUE;
//            } 
//            /*
//             * FOR PTD
//             */
//            if (stageId.equals("semak_draf_mmkn1")||stageId.equals("semak_draf_mmkn2")||stageId.equals("perakuan_ptd_risalat_mmkn")) {
//                ptd = Boolean.TRUE;
//                openPTG = Boolean.FALSE;
//            } 
//        
//       
//        
//

//          try {
//            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
//            for (Task t : l) {
//                stageID = t.getSystemAttributes().getStage();
//            participant = t.getSystemAttributes().getParticipantName();
//            logger.info("-----------------Stage ID:" + stageID + "----default--------");
//            }
//        } catch (Exception e) {
//            logger.error("error ::" + e.getMessage());
//        }



        String kc = peng.getKodCawangan().getKod();
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        String noLot = "";
        String bpm = "";
        String lotNama = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String ursan = "";
        String ktanah = "";
        String sbb = "";
//        kULuas = new KodUOM();
////        String kodUnitLuas = getContext().getRequest().getParameter("kodUnitLuas.kod");
////        
////        kULuas.setKod(kodUnitLuas);
////        hakmilikPermohonan.setKodUnitLuas(kULuas);


        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            sbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
        }


        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
            if (hakmilikPermohonan.getKodHakmilik() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilik().getKod();

            }
//            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
//                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
//            }
//            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
//                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
//            }

            // rohan added
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                String kod1 = (hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
                logger.info("----kod1---------" + kod1);
                syaratNyata = "" + kod + " -- " + kod1 + "";
                logger.info("----syaratNyata---------" + syaratNyata);
            } else {
                kod = "";
            }

            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
                logger.info("----kodSktn1---------" + kodSktn);
                String kod2 = (hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
                syaratNyata1 = "" + kodSktn + " -- " + kod2 + "";
                logger.info("----syaratNyata1---------" + syaratNyata1);
            } else {
                kodSktn = "";
            }

            if (hakmilikPermohonan.getNoLot() != null) {
                noLot = hakmilikPermohonan.getNoLot();
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasTerlibat() != null)) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }
            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getLot() != null) {
                lotNama = hakmilikPermohonan.getLot().getNama();
            }
        }
//        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
//            ursan = "Memiliki Tanah Kerajaan";
//        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            ursan = "MENDUDUKI TANAH KERAJAAN";
        }

        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }
        lokasi = lotNama + " " + noLot + ", " + bpm + ", Daerah " + daerah + ", ";

        String sub = permohonan.getIdPermohonan().substring(0, 2);
        String tempat = "";
        if (sub.equals("04")) {
            tempat = "Melaka";
        } else if (sub.equals("05")) {
            tempat = "Negeri Sembilan Darul Khusus";
        }

        String koduom = " ";
        if (hakmilikPermohonan.getKodUnitLuas() != null) {
            koduom = hakmilikPermohonan.getKodUnitLuas().getNama();
            logger.info("-----------koduom---------" + koduom);
        }

//        tujuan = " Tujuan kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai permohonan daripada "
//                + nama + ", No. K/P: " + noPengenalan + " untuk " + tujuanUrusanPlps + " ke atas PT/LOT " + noLot + ", seluas " + luas + " " + koduom + ", Mukim / Kawasan "
//                + bpm + ", Daerah " + daerah + " untuk tujuan " + sbb + " .";

        String tujuanUrusanPlps = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            tujuanUrusanPlps = "memiliki tanah Kerajaan di bawah Seksyen 65(1) Kanun Tanah Negara secara Lesen Pendudukan Sementara";
            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            sbb = permitItem.getKodItemPermit().getNama();
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            tujuanUrusanPlps = "memajak selama " + hakmilikPermohonan.getTempohPajakan() + " tahun tanah " + pelUtiliti.convertStringtoInitCapOnlyOnce(hakmilikPermohonan.getKodMilik().getNama()) + " di bawah Seksyen 63 Kanun Tanah Negara";
            sbb = !StringUtils.isEmpty(permohonan.getSebab()) ? permohonan.getSebab().toLowerCase() : new String();
        }
        String nama = " ";
        if ((pemohon != null) && (pemohon.getPihak() != null)) {
            nama = pemohon.getPihak().getNama();
        }

        String noPengenalan = " ";
        if ((pemohon != null) && (pemohon.getPihak() != null)) {
            noPengenalan = pemohon.getPihak().getNoPengenalan();
        }

        int umur = 0;
        BigDecimal gaji = BigDecimal.ZERO;
        String kerja = " ";
        int tanggungan = 0;

        if (!pemohon.getPihak().getJenisPengenalan().getKod().equals("S") && !pemohon.getPihak().getJenisPengenalan().getKod().equals("U")) {
            if (pemohon != null) {
                umur = pemohon.getUmur() != null ? pemohon.getUmur() : 0;
            }
            if (pemohon != null) {
                gaji = pemohon.getPendapatan() != null ? pemohon.getPendapatan() : new BigDecimal(0);
            }
            if (pemohon != null) {
                tanggungan = pemohon.getTanggungan() != null ? pemohon.getTanggungan() : 0;
            }
            if (pemohon != null && pemohon.getPekerjaan() != null) {
                kerja = pelUtiliti.convertStringtoInitCap(pemohon.getPekerjaan());
            }
        }

        nama = pelUtiliti.convertStringtoInitCap(nama);
        bpm = pelUtiliti.convertStringtoInitCap(bpm);
        if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
            daerah = pelUtiliti.convertStringtoInitCap(daerah);
        }
        String tujuanPTLot = !noLot.isEmpty() ? " ke atas PT/LOT " + noLot : " ";
        tujuanPlps = " Tujuan kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai permohonan daripada "
                + nama + ", No. K/P: " + noPengenalan + " untuk " + tujuanUrusanPlps + tujuanPTLot + ", seluas " + luas + " " + koduom + ", "
                + bpm + ", Daerah " + daerah + " untuk tujuan " + sbb + " .";

        String lokasi = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLokasi() != null)) {
            lokasi = hakmilikPermohonan.getLokasi();
        }

        String pihakname = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakname = pemohon.getPihak().getNama();
        }

        String mtarikmasuk = " ";
        String date2 = " ";
        if (permohonan != null) {
            mtarikmasuk = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
            date2 = sdf2.format(permohonan.getInfoAudit().getTarikhMasuk());
        }

//        perihalpermohonan = " Pentadbir Tanah " + permohonan.getCawangan().getDaerah().getNama() + " telah menerima permohonan ini pada " + mtarikmasuk + ".";

        String urusanPlps1 = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            urusanPlps1 = "Menduduki Tanah Kerajaan Secara Pendudukan Sementara";
        }

        String daerahPTD = pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama());
        perihalpermohonanPlps = " Pentadbir Tanah " + daerahPTD + " telah menerima permohonan ini pada " + mtarikmasuk + ".";

        String kodmilik = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
            kodmilik = pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getKodMilik().getNama());
            if (kodmilik.equals("Lps")) {
                kodmilik = "Lesen Pendudukan Sementara";
            }
        }

        String jarak = "";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getJarak() != null)) {
            jarak = hakmilikPermohonan.getJarak();
        }

        String unitJarak = "";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getUnitJarak() != null)) {
            unitJarak = hakmilikPermohonan.getUnitJarak().getNama();
        }

        String jarakDari = "";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getJarakDari() != null)) {
            jarakDari = hakmilikPermohonan.getJarakDari();
        }

        String jarakKediaman = "";
        if (jarak.trim().equals("") && unitJarak.trim().equals("") && jarakDari.trim().equals("")) {
            jarakKediaman = ".";
        } else {
            jarakKediaman = " iaitu lebih kurang " + jarak + " " + unitJarak + " dari " + jarakDari + ".";

        }

        //Laporan Tanah

        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);
        String keadaanTanah = " ";
        if (laporanTanah != null) {
            if (laporanTanah.getKecerunanTanah() != null) {
                keadaanTanah = keadaanTanah + laporanTanah.getKecerunanTanah().getNama().toLowerCase() + ", ";
            }
            if (laporanTanah.getStrukturTanahLain() != null) {
                keadaanTanah = keadaanTanah + laporanTanah.getStrukturTanahLain().toLowerCase() + ", ";
            } else {
                if (laporanTanah.getStrukturTanah() != null) {
                    keadaanTanah = keadaanTanah + laporanTanah.getStrukturTanah().getNama().toLowerCase() + ", ";
                }
            }
            if (laporanTanah.getKodKeadaanTanah() != null) {
                keadaanTanah = keadaanTanah + laporanTanah.getKodKeadaanTanah().getNama().toLowerCase();
            }
            if ((laporanTanah.getKeadaanTanah() != null)) {
                keadaanTanah = keadaanTanah + " dan " + laporanTanah.getKeadaanTanah();
            } else {
                keadaanTanah = keadaanTanah + ".";
            }
        }
        if (keadaanTanah.equals(" ")) {
            keadaanTanah = "tiada";
        }
        lokasi = lokasi != null && !("").equals(lokasi) ? pelUtiliti.convertStringtoInitCap(lokasi) : lokasi;
        String pekerjaan = "";
        if ((kerja != null) && (pemohon.getPendapatan() != null)) {
            pekerjaan = " dan bekerja sebagai " + kerja + " dengan pendapatan sebanyak RM" + pemohon.getPendapatan() + " sebulan.";
        } else {
            pekerjaan = ".";
        }
        perihaltanah = " Tanah yang dipohon adalah tanah rizab Kerajaan yang tidak boleh diberimilik. Tanah ini ialah tanah " + kodmilik + " yang terletak berhampiran "
                + lokasi + jarakKediaman + " Keadaan tanah adalah " + keadaanTanah;

        perihalpermohonan1 = " Permohonan ini adalah merupakan permohonan untuk memperbaharui semula lesen yang tamat tempoh pada 31 Disember " + date2 + " .";

        perihalpermohonan2 = " Tanah yang dipohon ditunjukkan dengan tepi warna merah dalam pelan yang dilampirkan. ";

//        perihaltanah1 = " Tanah yang dipohon adalah tanah Kerajaan yang boleh diberimilik. ";

        perihalpemohon = " Pemohon ialah " + nama + ", No. KP: " + noPengenalan + " adalah seorang Warganegara Malaysia.  Beliau berumur " + umur
                + " tahun" + pekerjaan;

        logger.info("------perihalpemohon--if (B)----" + perihalpemohon);

        String pihaknama = " ";
        if ((pihak != null) && (pihak.getNama() != null)) {
            pihaknama = pihak.getNama();
        }


        perihalpemohon_s = "Jika syarikat: Pemohon adalah syarikat " + pihaknama + " yang didaftarkan di Malaysia pada " + pihak.getTarikhLahir()
                + " Nombor pendaftarannya ialah " + noPengenalan + " Senarai Ahli Lembaga Pengarah adalah seperti berikut; " + '\n' + '\n';

        if ((pihakPengarah != null) && (listPengarah != null)) {
            for (PihakPengarah pp : listPengarah) {
                perihalpemohon_s = perihalpemohon_s + '\n' + pp.getNama() + "  " + " " + pp.getNoPengenalan();
            }
        }

        logger.info("------perihalpemohon--if (S)----" + perihalpemohon);

        int tahun = 0;
        if (pemohon != null) {
            if (pemohon.getTempohMastautin() != null) {
                tahun = pemohon.getTempohMastautin();
            }
        }

        String alamat = " ";
        if (pemohon != null) {
            if (pemohon.getPihak().getAlamat1() != null) {
                alamat = alamat + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat1());
            }
            if (pemohon.getPihak().getAlamat2() != null) {
                alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat2());
            }
            if (pemohon.getPihak().getAlamat3() != null) {
                alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat3());
            }
            if (pemohon.getPihak().getPoskod() != null) {
                alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getPoskod());
            }
            if (pemohon.getPihak().getAlamat4() != null) {
                alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat4()) + ".";
            }

        }

        perihalpemohon1 = " Pemohon tinggal di " + alamat + ".";


        String pHubunganNama = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getNama() != null)) {
            pHubunganNama = pelUtiliti.convertStringtoInitCap(pemohonHubungan.getNama());
        }

        String pHubunganNoKP = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getNoPengenalan() != null)) {
            pHubunganNoKP = pemohonHubungan.getNoPengenalan();
        }

        String pHubunganKerja = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getPekerjaan() != null)) {
            pHubunganKerja = " adalah merupakan seorang " + pelUtiliti.convertStringtoInitCap(pemohonHubungan.getPekerjaan());
        }

        BigDecimal pHubunganGaji = BigDecimal.ZERO;
        String pHGaji = "";
        if ((pemohonHubungan != null) && (pemohonHubungan.getGaji() != null)) {
            pHubunganGaji = pemohonHubungan.getGaji();
            pHGaji = " yang berpendapatan sebanyak RM " + pHubunganGaji;
        }

        perihalpemohon2 = " Isteri pemohon " + pHubunganNama + ", No. KP: " + pHubunganNoKP + pHubunganKerja + pHGaji + ".";


        perihalpemohon2suami = " Suami beliau " + pHubunganNama + ", No. KP: " + pHubunganNoKP + pHubunganKerja + pHGaji + ".";


        String cawangan = " ";
        if ((permohonan != null) && (permohonan.getCawangan() != null)) {
            cawangan = permohonan.getCawangan().getDaerah().getNama();
        }

        String agency = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getAgensi() != null)) {
            agency = permohonanRujukanLuar.getAgensi().getKategoriAgensi().getNama();
        }

        pentabirtanah1 = " Setelah meneliti permohonan ini dan mengambil kira ulasan Jabatan-Jabatan Teknikal dan ulasan YB ADUN Kawasan "
                + agency + ", Pentadbir Tanah " + cawangan + " berpendapat bahawa permohonan ini boleh dipertimbangkan untuk diluluskan";

//        if(stageId.equals("sedia_draf_mmkn")){
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PPRU")) {
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_mmkn");
        } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_mmkn");
        } else {
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_rencana_ptg");
        }


        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            kodKepu = fasaPermohonan.getKeputusan().getKod();

            if (syortolaklulus == null) {
                syortolaklulus = fasaPermohonan.getKeputusan().getKod();
            }
            if (syortolaklulus != null && syortolaklulus.equals("SL")) {
                pentabirtanah1 = new String();
                pentabirtanah1 = " Setelah meneliti permohonan ini dan mengambil kira ulasan Jabatan-Jabatan Teknikal dan ulasan YB ADUN Kawasan "
                        + agency + ", Pentadbir Tanah " + cawangan + " berpendapat bahawa permohonan ini boleh dipertimbangkan untuk diluluskan"
                        + " kerana pemohon ingin memperbaharui lesen yang tamat pada 31 Disember " + date2;
            }
            if (syortolaklulus != null && syortolaklulus.equals("ST")) {
                pentabirtanah1 = new String();
                pentabirtanah1 = " Setelah meneliti permohonan ini dan mengambil kira ulasan Jabatan-Jabatan Teknikal dan ulasan YB ADUN Kawasan "
                        + agency + ", Pentadbir Tanah " + cawangan + " berpendapat bahawa permohonan ini boleh dipertimbangkan untuk ditolak"
                        + " kerana pemohon ingin memperbaharui lesen yang tamat pada 31 Disember " + date2;
            }
        }
//        }
//        else{
//            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
//            if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
//                kodKepu = fasaPermohonan.getKeputusan().getKod();
//            }
//        }

        String pihakName = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakName = pemohon.getPihak().getNama();
        }

        String koduom1 = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
//            koduom1 = hakmilikPermohonan.getKodUnitLuas().getNama();
            koduom1 = hakmilikPermohonan.getKodUnitLuas().getNama();
            logger.info("----koduom1----" + koduom1);
        }

        String tajukKategoriTanah = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKategoriTanahBaru() != null)) {
            tajukKategoriTanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
        }

        String tajuksbb = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
//            tajuksbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
            tajuksbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
        }


//        tajuk = " PERMOHONAN DARIPADA  " + pihakName + ", NO. K/P : " + noPengenalan + " UNTUK " + tujuanUrusanPlps + " KE ATAS PT/LOT " + noLot + ", SELUAS " + luas + " " + koduom1 + " MUKIM/KAWASAN "
//                + bpm + ", DAERAH " + daerah + " UNTUK TUJUAN " + tajukKategoriTanah + ".";


        String urusanPlps = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            urusanPlps = "MEMILIKI TANAH KERAJAAN DI BAWAH SEKSYEN 65(1) KANUN TANAH NEGARA SECARA LESEN PENDUDUKAN SEMENTARA";
            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (permitItem.getKodItemPermit() != null) {
                tajuksbb = permitItem.getKodItemPermit().getNama();
            } else {
                tajuksbb = hakmilikPermohonan.getCatatan();
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            urusanPlps = "MENGGUNAKAN RUANG UDARA DI ATAS TANAH " + hakmilikPermohonan.getKodMilik().getNama().toUpperCase() + " DI BAWAH SEKSYEN 75A KANUN TANAH NEGARA";
//            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
//            if(permitItem.getKodItemPermit() != null){
//                tajuksbb = permitItem.getKodItemPermit().getNama() ;
//            }
//            else {
//                tajuksbb = hakmilikPermohonan.getCatatan() ;
//            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            urusanPlps = "MEMAJAK SELAMA " + hakmilikPermohonan.getTempohPajakan() + " TAHUN TANAH " + hakmilikPermohonan.getKodMilik().getNama() + " DI BAWAH SEKSYEN 63 KANUN TANAH NEGARA";
//            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
//            if(permitItem.getKodItemPermit() != null){
//                tajuksbb = permitItem.getKodItemPermit().getNama() ;
//            }
//            else {
//                tajuksbb = hakmilikPermohonan.getCatatan() ;
//            }
        }
        if (noLot.isEmpty()) {
            noLot = " ";
        } else {
            String a = noLot;
            noLot = a + " ";
        }
        String tujuanTajukPlps = "TIADA";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            tujuanTajukPlps = permitItem.getKodItemPermit().getNama();
        }
        if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
//        permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
            tujuanTajukPlps = permohonan.getSebab();
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
//        permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
            tujuanTajukPlps = permohonan.getSebab();
        }
        String tajukLot = "";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLot() != null)) {
            tajukLot = hakmilikPermohonan.getLot().getNama();
        }

        tajukPlps = " PERMOHONAN DARIPADA  " + pihakName + ", NO. K/P : " + noPengenalan + " UNTUK " + urusanPlps + " " + tajukLot + " " + noLot + ", SELUAS " + luas + " " + koduom1 + " " + bpm + ", DAERAH " + daerah + " UNTUK TUJUAN " + tujuanTajukPlps + ".";


        String pnopng = " ";
        if ((pihak != null) && (pihak.getNoPengenalan() != null)) {
            pnopng = pihak.getNoPengenalan();
        }

        String hpnolot = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getNoLot() != null)) {
            hpnolot = hakmilikPermohonan.getNoLot();
        }

        BigDecimal luasterlibat = BigDecimal.ZERO;
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasTerlibat() != null)) {
            luasterlibat = hakmilikPermohonan.getLuasTerlibat();
        }

//        String ursan2 = " ";
//        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
//            ursan2 = "Memilik Tanah Kerajaan";
//        }

        String koduom2 = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
            koduom2 = hakmilikPermohonan.getKodUnitLuas().getNama();
        }
        cawangan = pelUtiliti.convertStringtoInitCap(cawangan);
        pihakName = pelUtiliti.convertStringtoInitCap(pihakName);

        pentabirtanah2 = " Pentadbir Tanah Daerah " + cawangan + " dengan ini memperakukan supaya permohonan daripada " + pihakName
                + " No. KP: " + noPengenalan + " untuk " + tujuanUrusanPlps + " ke atas PT/LOT " + noLot + ", seluas " + luas + " " + koduom2 + ", Mukim/Kawasan "
                + bpm + ", Daerah " + cawangan + " untuk tujuan " + sbb + " diluluskan seperti pelan yang dilampirkan dengan dikenakan syarat-syarat seperti berikut:- ";


        String noruj = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getNoRujukan() != null)) {
            noruj = permohonanRujukanLuar.getNoRujukan();
        }

        String trhruj = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getTarikhRujukan() != null)) {
            trhruj = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
        }

        String lokasiagency = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getLokasiAgensi() != null)) {
            lokasiagency = permohonanRujukanLuar.getLokasiAgensi();
        }

//        ulasanyb = " Pentadbir Tanah Melaka Tengah telah meminta ulasan " + permohonanRujukanLuar.getNamaAgensi() + " melalui surat bilangan " + noruj
//                + " bertarikh " + trhruj + " dan ulasan beliau adalah " + permohonanRujukanLuar.getUlasan() ;

        senaraiYBAdun = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
        permohonankertas = new PermohonanKertas();
        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        List<PermohonanKertasKandungan> mohonKertasKand = new ArrayList<PermohonanKertasKandungan>();

        if (permohonankertas != null) {
            mohonKertasKand = pelupusanService.findByIdKertasOnly(permohonankertas.getIdKertas());
            for (int j = 0; j < mohonKertasKand.size(); j++) {
//                kertasK = new PermohonanKertasKandungan();
                kertasK = mohonKertasKand.get(j);

                if (kertasK.getBil() == 0) {
                    tajukPlps = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("1.1")) {
                    tujuanPlps = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.1.1")) {
                    perihalpermohonanPlps = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.1.2")) {
                    perihalpermohonan1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.1.3")) {
                    perihalpermohonan2 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.2.1")) {
                    perihaltanah = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.1")) {
                    if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {
                        perihalpemohon = kertasK.getKandungan();
                    } else {
                        perihalpemohon_s = kertasK.getKandungan();
                    }
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.2")) {
                    perihalpemohon1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.3")) {
                    if (pemohon.getStatusKahwin().equals("Berkahwin")) {
                        if (pemohonHubungan.getKaitan().equals("ISTERI")) {
                            perihalpemohon2 = kertasK.getKandungan();
                        }
                        if (pemohonHubungan.getKaitan().equals("SUAMI")) {
                            perihalpemohon2suami = kertasK.getKandungan();
                        }
                    }
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("4.1")) {
                    ulasanyb = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("5.1")) {
                    pentabirtanah1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("5.2")) {
                    pentabirtanah2 = kertasK.getKandungan();
                }
//                else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.2.2")) {
//                    perihaltanah1 = kertasK.getKandungan();
//                }

            }
        }

        if (permohonankertas != null) {
            List<PermohonanKertasKandungan> vecTemp = new Vector();
            senaraiLaporanKandungan1 = new Vector();
            vecTemp = pelupusanService.findByIdbyBil(permohonankertas.getIdKertas(), 2);
            for (PermohonanKertasKandungan pkk : vecTemp) {
                if (Integer.parseInt(pkk.getSubtajuk()) > 2) {
                    senaraiLaporanKandungan1.add(pkk);
                }
            }
            senaraiLaporanKandunganptg1 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 8);
            logger.info("-------senaraiLaporanKandunganptg1------" + senaraiLaporanKandunganptg1);
            senaraiLaporanKandunganptg2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 9);
            logger.info("-------senaraiLaporanKandunganptg2------" + senaraiLaporanKandunganptg2);
//            senaraiLaporanKandunganpbtanah = pelupusanService.findBySubtajuk(permohonankertas.getIdKertas(), 5);
            senaraiLaporanKandunganpbtanah = pelupusanService.findByIdbyBil(permohonankertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
            logger.info("-------senaraiLaporanKandunganpbtanah------" + senaraiLaporanKandunganpbtanah);
            senaraiPerihalTanah = pelupusanService.findByIdbyBilExcludeSubtajuk(permohonankertas.getIdKertas(), 3, "1"); //FOR PERIHAL TANAH
            logger.info("-------senaraiPerihalTanah------" + senaraiPerihalTanah);

        } else {
            senaraiLaporanKandungan1 = new Vector();
            senaraiLaporanKandunganptg1 = new Vector();
            senaraiLaporanKandunganptg2 = new Vector();
            senaraiLaporanKandunganpbtanah = new Vector();
            senaraiPerihalTanah = new Vector();
        }

        // Mohon Rujulur
        senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        if (permohonankertas != null) {
            senaraiLaporanKandunganUtil = new Vector();
            //senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
            senaraiLaporanKandunganRujukLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan);
            for (int i = 0; i < senaraiLaporanKandunganRujukLuar.size(); i++) {
                //PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                //kand1 = senaraiLaporanKandungan2.get(i);
                PermohonanRujukanLuar kand1 = new PermohonanRujukanLuar();
                kand1 = senaraiLaporanKandunganRujukLuar.get(i);
                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                pelUtil.setPermohonanRujukanLuar(kand1);
                if (kand1.getAgensi().getKategoriAgensi().getKod().equals("JTK")) {
                    senaraiLaporanKandunganUtil.add(pelUtil);
                }
            }
            logger.info("------if-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        } else {
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiLaporanKandunganUtil = new Vector();
            senaraiPermohonanRujLuar = pelupusanService.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
            logger.info("-----------senaraiPermohonanRujLuar-------:" + senaraiPermohonanRujLuar.size());
            if (!senaraiPermohonanRujLuar.isEmpty()) {
                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();

                    PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                    if (rujLuar.getUlasan() == null) {
                        rujLuar.setUlasan("Tiada ulasan diterima");
                    }
                    pelUtil.setPermohonanRujukanLuar(rujLuar);
                    //kand1.setKandungan(kandVal);
                    //kand1.setSubtajuk(rujLuar.getAgensi().getNama());
                    if (rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK")) //senaraiLaporanKandungan2.add(kand1);
                    {
                        senaraiLaporanKandunganUtil.add(pelUtil);
                    }
                }
            }


            logger.info("------else-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        }

        //Laporan Tanah

        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);

        // mohon tutukos
        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);

        // mohon permit item
        permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
        /*
         * Added For Dynamic Lot-Lot Sempadan
         */
        disLaporanTanahSempadan = new DisLaporanTanahSempadan();
        disLaporanTanahSempadan.setListLaporTanahSpdnU(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnB(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnS(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnT(new ArrayList());

        List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnU().add(ls);
        }
        uSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
        }
        sSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
        }
        tSize = listLaporTanahSpdnTemp.size();
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
        }
        bSize = listLaporTanahSpdnTemp.size();
        /*
         * END
         */
        stageId = "sedia_draf_mmkn";
        if (stageId.equals("sedia_draf_mmkn") || stageId.equals("sedia_draf_rencana_ptg")) {
            simpanNew();
        }
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                senaraiLaporanKandungan1.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                senaraiPerihalTanah.add(pkk);
                break;
//            case 4:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 4);
//                listKertasSyorPTD.add(pkk);
//                break;
//            case 5:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 5);
//                listKertasPerakuanPTG.add(pkk);
//                break;
//            case 6:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 6);
//                listKertasKeputusanPTG.add(pkk);
//                break;
            case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 7);
                senaraiLaporanKandunganpbtanah.add(pkk);
                break;
            case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 8);
                senaraiLaporanKandunganptg1.add(pkk);
                break;
            case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 9);
                senaraiLaporanKandunganptg2.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {
//        System.out.println("--------------memasuki update kandungan dgn jayanyer!!!!!!!!!!");


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (permohonankertas != null) {
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonankertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
//            System.out.println("-------------------masuk kat plps");
            permohonankertas.setTajuk("KERTAS MMKN");
            KodDokumen kod = kodDokumenDAO.findById("RMN");
            permohonankertas.setKodDokumen(kod);

        } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
//            System.out.println("-----------------masuk kat pptr");
            permohonankertas.setTajuk("KERTAS MMKN");
            KodDokumen kod = kodDokumenDAO.findById("RMN");
            permohonankertas.setKodDokumen(kod);
        }
        permohonankertas.setCawangan(cawangan);
        permohonankertas.setInfoAudit(infoAudit);
        permohonankertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonankertas);

        long a = permohonankertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);



        if (plk.isEmpty()) {
            pLK.setSubtajuk("1");
//            LOG.info("PLK" + pLK.getSubtajuk());
        } else {
            int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
            //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

            pLK.setSubtajuk(String.valueOf(n));
        }
        pLK.setBil((short) i);
        pLK.setKandungan(kand);
        pLK.setKertas(permohonankertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2: //FOR Perihal Permohonan
                updateKandungan(2, kand);

                break;
            case 3://FOR PERIHAL TANAH
                updateKandungan(3, kand);
                break;
//            case 4:
//
//                updateKandungan(4, kand);
//
//                break;
//            case 5:
//
//                updateKandungan(5, kand);
//
//                break;
//            case 6:
//
//                updateKandungan(6, kand);
//                break;
            case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                updateKandungan(7, kand);
                break;
            case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                updateKandungan(8, kand);
                break;
            case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                updateKandungan(9, kand);
                break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanNew() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonankertas = new PermohonanKertas();

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        /*
         * INSERTING MOHON_KERTAS
         */
        if (permohonankertas == null) {
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(pengguna);

            permohonankertas = new PermohonanKertas();
            permohonankertas.setInfoAudit(infoAudit);
            permohonankertas.setCawangan(pengguna.getKodCawangan());
            permohonankertas.setPermohonan(permohonan);
            permohonankertas.setTajuk("KERTAS MMKN");
            permohonankertas.setKodDokumen(kodDokumenDAO.findById("RMN"));
            permohonankertas.setInfoAudit(iaP);
            pelupusanService.simpanPermohonanKertas(permohonankertas);

            permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
            if (permohonankertas != null) {


                /*
                 * INSERTING TAJUK
                 */
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(0);
                String kandungan = tajukPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING TUJUAN
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(1);
                kandungan = tujuanPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL PERMOHONAN
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(2);
                kandungan = perihalpermohonanPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(2);
                kandungan = perihalpermohonan2;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL TANAH
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(3);
                kandungan = perihaltanah;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL PEMOHON
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(4);
                if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {
                    kandungan = perihalpemohon;
                } else if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D") || pihak.getJenisPengenalan().getKod().equals("S")) {
                    kandungan = perihalpemohon_s;
                }

                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(4);

                kandungan = perihalpemohon1;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                if (pemohonHubungan != null) {
                    if (!StringUtils.isBlank(pemohonHubungan.getKaitan())) {
                        if (pemohon.getStatusKahwin().equals("Berkahwin")) {
                            permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                            permohonanKertasKandungan1.setKertas(permohonankertas);
                            permohonanKertasKandungan1.setBil(4);
                            if (!StringUtils.isBlank(pemohonHubungan.getKaitan())) {
                                if (pemohonHubungan.getKaitan().equalsIgnoreCase("ISTERI")) {
                                    kandungan = perihalpemohon2;
                                }
                                if (pemohonHubungan.getKaitan().equalsIgnoreCase("SUAMI")) {
                                    kandungan = perihalpemohon2suami;
                                }
                            }
                            permohonanKertasKandungan1.setKandungan(kandungan);
                            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan1.setSubtajuk("3");
                            permohonanKertasKandungan1.setInfoAudit(iaP);
                            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                        }
                    }
                }



//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
//                permohonanKertasKandungan1.setKertas(permohonankertas);
//                permohonanKertasKandungan1.setBil(7);
//                
//                kandungan = pentabirtanah2;                
//                permohonanKertasKandungan1.setKandungan(kandungan);
//                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//                permohonanKertasKandungan1.setSubtajuk("2");
//                permohonanKertasKandungan1.setInfoAudit(iaP);
//                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

        }
        /*
         * END OF MOHON_KERTAS AND MOHON_KERTAS KANDUNGAN
         */
        //kULuas = new KodUOM();asdasdasd
        String kodUnitLuas = getContext().getRequest().getParameter("kodUnitLuas.kod");

        //kULuas.setKod(kodUnitLuas);
        if (kodUnitLuas != null && !("").equals(kodUnitLuas)) {
            hakmilikPermohonan.setKodUnitLuas(kodUOMDAO.findById(kodUnitLuas));
        }

        InfoAudit info = new InfoAudit();
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan != null) {
            info = fasaPermohonan.getInfoAudit();
            info.setTarikhKemaskini(new java.util.Date());
            info.setDiKemaskiniOleh(pengguna);
        } else {
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setIdAliran(stageId);
        }
        fasaPermohonan.setInfoAudit(info);
        if (syortolaklulus != null) {
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
        }
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
        pelupusanService.simpanPermohonan(permohonan);


        //STRAT: FOR LPS

        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {

            logger.info("--------------LPS-------------------");

            // Mohon Hakmilik

            logger.info("--------------LPS hakmilikPermohonan Saving::-------------------");
            if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setPermohonan(permohonan);
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

            //Mohon Tuntukos

            logger.info("--------------LPS PermohonanTuntutanKos Saving::-------------------");

            PermohonanTuntutanKos permohonanTuntutanKosTemp = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);

            logger.info("--------------LPS permohonanTuntutanKosTemp ::-------------------:" + permohonanTuntutanKosTemp);

            if (permohonanTuntutanKosTemp != null) {
                logger.info("--------------LPS permohonanTuntutanKosTemp Not Null ::-------------------:");
                infoAudit = permohonanTuntutanKosTemp.getInfoAudit();
                logger.info("--------------LPS infoAudit ::-------------------:" + infoAudit);
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                logger.info("--------------LPS permohonanTuntutanKosTemp Null ::-------------------:");
                permohonanTuntutanKosTemp = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            permohonanTuntutanKosTemp.setInfoAudit(infoAudit);
            permohonanTuntutanKosTemp.setPermohonan(permohonan);
            permohonanTuntutanKosTemp.setCawangan(pengguna.getKodCawangan());
            permohonanTuntutanKosTemp.setKodTransaksi(kodTuntutDAO.findById("DISB4A").getKodKewTrans());
            permohonanTuntutanKosTemp.setItem(kodTuntutDAO.findById("DISB4A").getNama());
            permohonanTuntutanKosTemp.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
            permohonanTuntutanKosTemp.setHakmilikPermohonan(hakmilikPermohonan);
            if (permohonanTuntutanKos.getAmaunTuntutan() != null && !("").equals(permohonanTuntutanKos.getAmaunTuntutan())) {
                permohonanTuntutanKosTemp.setAmaunTuntutan(permohonanTuntutanKos.getAmaunTuntutan());
            } else {
                permohonanTuntutanKosTemp.setAmaunTuntutan(new BigDecimal(0));
            }
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKosTemp);


            // Mohon Permit Item

            logger.info("--------------LPS permohonanPermitItem Saving::-------------------");
            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
            permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (permohonanPermitItem != null) {
                logger.info("--------------LPS permohonanPermitItem  NOT Null::-------------------");

                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                if (kodPermit != null && !("").equals(kodPermit)) {
                    permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodPermit));
                }
                pelupusanService.saveOrUpdate(permohonanPermitItem);

            } else {
                logger.info("--------------LPS permohonanPermitItem Null::-------------------");
                permohonanPermitItem = new PermohonanPermitItem();
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                if (kodPermit != null && !("").equals(kodPermit)) {
                    permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodPermit));
                }
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        }
        // END : LPS
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanNew2() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonankertas = new PermohonanKertas();

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        /*
         * INSERTING MOHON_KERTAS
         */
        if (permohonankertas == null) {
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(pengguna);

            permohonankertas = new PermohonanKertas();
            permohonankertas.setInfoAudit(infoAudit);
            permohonankertas.setCawangan(pengguna.getKodCawangan());
            permohonankertas.setPermohonan(permohonan);
            permohonankertas.setTajuk("KERTAS MMKN");
            permohonankertas.setKodDokumen(kodDokumenDAO.findById("RMN"));
            permohonankertas.setInfoAudit(iaP);
            pelupusanService.simpanPermohonanKertas(permohonankertas);

            permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
            if (permohonankertas != null) {


                /*
                 * INSERTING TAJUK
                 */
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(0);
                String kandungan = tajukPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING TUJUAN
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(1);
                kandungan = tujuanPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL PERMOHONAN
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(2);
                kandungan = perihalpermohonanPlps;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(2);
                kandungan = perihalpermohonan2;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL TANAH
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(3);
                kandungan = perihaltanah;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                /*
                 * INSERTING PERIHAL PEMOHON
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(4);
                if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {
                    kandungan = perihalpemohon;
                } else if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D") || pihak.getJenisPengenalan().getKod().equals("S")) {
                    kandungan = perihalpemohon_s;
                }

                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                permohonanKertasKandungan1.setKertas(permohonankertas);
                permohonanKertasKandungan1.setBil(4);

                kandungan = perihalpemohon1;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);


                if (pemohon.getStatusKahwin().equalsIgnoreCase("Berkahwin")) {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setKertas(permohonankertas);
                    permohonanKertasKandungan1.setBil(4);

                    if (pemohonHubungan.getKaitan().equalsIgnoreCase("ISTERI")) {
                        kandungan = perihalpemohon2;
                    }
                    if (pemohonHubungan.getKaitan().equalsIgnoreCase("SUAMI")) {
                        kandungan = perihalpemohon2suami;
                    }

                    permohonanKertasKandungan1.setKandungan(kandungan);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("3");
                    permohonanKertasKandungan1.setInfoAudit(iaP);
                    pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                }

//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
//                permohonanKertasKandungan1.setKertas(permohonankertas);
//                permohonanKertasKandungan1.setBil(7);
//                
//                kandungan = pentabirtanah2;                
//                permohonanKertasKandungan1.setKandungan(kandungan);
//                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//                permohonanKertasKandungan1.setSubtajuk("2");
//                permohonanKertasKandungan1.setInfoAudit(iaP);
//                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

        }
        /*
         * END OF MOHON_KERTAS AND MOHON_KERTAS KANDUNGAN
         */
        //kULuas = new KodUOM();asdasdasd
        String kodUnitLuas = getContext().getRequest().getParameter("kodUnitLuas.kod");

        //kULuas.setKod(kodUnitLuas);
        if (kodUnitLuas != null && !("").equals(kodUnitLuas)) {
            hakmilikPermohonan.setKodUnitLuas(kodUOMDAO.findById(kodUnitLuas));
        }

        InfoAudit info = new InfoAudit();
        if (fasaPermohonan != null) {
            info = fasaPermohonan.getInfoAudit();
            info.setTarikhKemaskini(new java.util.Date());
            info.setDiKemaskiniOleh(pengguna);
        } else {
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            fasaPermohonan = new FasaPermohonan();
        }
        fasaPermohonan.setInfoAudit(info);
        fasaPermohonan.setCawangan(pengguna.getKodCawangan());
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setIdAliran(stageId);
        if (syortolaklulus != null) {
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
        }
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
        pelupusanService.simpanPermohonan(permohonan);


        //STRAT: FOR LPS

        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {

            logger.info("--------------LPS-------------------");

            // Mohon Hakmilik

            logger.info("--------------LPS hakmilikPermohonan Saving::-------------------");
            if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setPermohonan(permohonan);
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

            //Mohon Tuntukos

            logger.info("--------------LPS PermohonanTuntutanKos Saving::-------------------");

            PermohonanTuntutanKos permohonanTuntutanKosTemp = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);

            logger.info("--------------LPS permohonanTuntutanKosTemp ::-------------------:" + permohonanTuntutanKosTemp);

            if (permohonanTuntutanKosTemp != null) {
                logger.info("--------------LPS permohonanTuntutanKosTemp Not Null ::-------------------:");
                infoAudit = permohonanTuntutanKosTemp.getInfoAudit();
                logger.info("--------------LPS infoAudit ::-------------------:" + infoAudit);
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                logger.info("--------------LPS permohonanTuntutanKosTemp Null ::-------------------:");
                permohonanTuntutanKosTemp = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            permohonanTuntutanKosTemp.setInfoAudit(infoAudit);
            permohonanTuntutanKosTemp.setPermohonan(permohonan);
            permohonanTuntutanKosTemp.setCawangan(pengguna.getKodCawangan());
            permohonanTuntutanKosTemp.setKodTransaksi(kodTuntutDAO.findById("DISB4A").getKodKewTrans());
            permohonanTuntutanKosTemp.setItem(kodTuntutDAO.findById("DISB4A").getNama());
            permohonanTuntutanKosTemp.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
            permohonanTuntutanKosTemp.setHakmilikPermohonan(hakmilikPermohonan);
            permohonanTuntutanKosTemp.setAmaunTuntutan(permohonanTuntutanKos.getAmaunTuntutan());
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKosTemp);


            // Mohon Permit Item

            logger.info("--------------LPS permohonanPermitItem Saving::-------------------");
            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
            permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (permohonanPermitItem != null) {
                logger.info("--------------LPS permohonanPermitItem  NOT Null::-------------------");

                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                if (kodPermit != null && !("").equals(kodPermit)) {
                    permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodPermit));
                }
                pelupusanService.saveOrUpdate(permohonanPermitItem);

            } else {
                logger.info("--------------LPS permohonanPermitItem Null::-------------------");
                permohonanPermitItem = new PermohonanPermitItem();
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                if (kodPermit != null && !("").equals(kodPermit)) {
                    permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodPermit));
                }
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        // END : LPS
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }
//    public Resolution simpan() {
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit infoAudit = new InfoAudit();
//
//        PermohonanKertas permohonanKertas = new PermohonanKertas();
//
//        if (kertasK != null) {
//            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//        } else {
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//        }
//
//        kULuas = new KodUOM();
//        String kodUnitLuas = getContext().getRequest().getParameter("kodUnitLuas.kod");
//        
//        kULuas.setKod(kodUnitLuas);
//        hakmilikPermohonan.setKodUnitLuas(kULuas);
//
//        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();
//
//        if (tajukPlps == null || tajukPlps.equals("")) {
//            tajukPlps = "TIADA DATA";
//        }
//
//        if (tujuanPlps == null || tujuanPlps.equals("")) {
//            tujuanPlps = "TIADA DATA";
//        }
//
//        if (perihalpermohonanPlps == null || perihalpermohonanPlps.equals("")) {
//            perihalpermohonanPlps = "TIADA DATA";
//        }
//
//        if (perihalpermohonan1 == null || perihalpermohonan1.equals("")) {
//            perihalpermohonan1 = "TIADA DATA";
//        }
//        
//        if (perihalpermohonan2 == null || perihalpermohonan2.equals("")) {
//            perihalpermohonan2 = "TIADA DATA";
//        }
//
//        if (perihaltanah == null || perihaltanah.equals("")) {
//            perihaltanah = "TIADA DATA";
//        }
//
////        if (perihaltanah1 == null || perihaltanah1.equals("")) {
////            perihaltanah1 = "TIADA DATA";
////        }
//
////        if(pihak.getJenisPengenalan().equals('B')||pihak.getJenisPengenalan().equals('L')||pihak.getJenisPengenalan().equals('P')||pihak.getJenisPengenalan().equals('T')||pihak.getJenisPengenalan().equals('I')){
//            if (perihalpemohon == null || perihalpemohon.equals("")) {
//                perihalpemohon = "TIADA DATA";
//            }
////        }
////        if (pihak.getJenisPengenalan().equals('S')){
//            if (perihalpemohon_s == null || perihalpemohon_s.equals("")) {
//                perihalpemohon_s = "TIADA DATA";
//            }
////        }
//        
//        if (pentabirtanah1 == null || pentabirtanah1.equals("")) {
//            pentabirtanah1 = "TIADA DATA";
//        }
//
//        if (pentabirtanah2 == null || pentabirtanah2.equals("")) {
//            pentabirtanah2 = "TIADA DATA";
//        }
//
//        if (ulasanyb == null || ulasanyb.equals("")) {
//            ulasanyb = "TIADA DATA";
//        }
//
//        if (perihalpemohon1 == null || perihalpemohon1.equals("")) {
//            perihalpemohon1 = "TIADA DATA";
//        }
//        
////        if(pemohon.getStatusKahwin().equals("Berkahwin")){
////            if (pemohonHubungan.getKaitan().equals("ISTERI")){
//                if (perihalpemohon2 == null || perihalpemohon2.equals("")) {
//                    perihalpemohon2 = "TIADA DATA";
//                }
////            }if (pemohonHubungan.getKaitan().equals("SUAMI")){
//                 if (perihalpemohon2suami == null || perihalpemohon2suami.equals("")) {
//                     perihalpemohon2suami = "TIADA DATA";
//                 }
////            }
////        }
//        
//        
//
//        logger.info("--------------tajuk-------------------" + tajukPlps);
//        logger.info("--------------tujuan-------------------" + tujuanPlps);
//        logger.info("--------------perihalpermohonan-------------------" + perihalpermohonanPlps);
//        logger.info("--------------perihaltanah-------------------" + perihaltanah);
//        listUlasan.add(tajukPlps);
//        listUlasan.add(tujuanPlps);
//        listUlasan.add(perihalpermohonanPlps);
////        listUlasan.add(perihalpermohonan1);
//        listUlasan.add(perihalpermohonan2);
//        listUlasan.add(perihaltanah);
////        listUlasan.add(perihaltanah1);
//        if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) 
//        {
//            listUlasan.add(perihalpemohon);
//            listUlasan.add(perihalpemohon1);
//            if (pemohon.getStatusKahwin().equals("Berkahwin")) {
//                if (pemohonHubungan.getKaitan().equals("ISTERI")) {
//                    listUlasan.add(perihalpemohon2);
//                } else {
//                    listUlasan.add(perihalpemohon2suami);
//                }
//
//            }
//        }
//         else {
//        listUlasan.add(perihalpemohon_s);
//         listUlasan.add(perihalpemohon1);
//         }
//        listUlasan.add(ulasanyb);
//        listUlasan.add(pentabirtanah1);
//        listUlasan.add(pentabirtanah2);
//
//
//        listSubtajuk.add(" ");
//        listSubtajuk.add("1.1");
//        listSubtajuk.add("2.1.1");
//        listSubtajuk.add("2.1.2");
////        listSubtajuk.add("2.1.3");
//        listSubtajuk.add("2.2.1");
////        listSubtajuk.add("2.2.2");
//        if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) 
//        {
//            listSubtajuk.add("2.3.1");
//            listSubtajuk.add("2.3.2");
//            if (pemohon.getStatusKahwin().equals("Berkahwin")) {
//                if (pemohonHubungan.getKaitan().equals("ISTERI")) {
//                    listSubtajuk.add("2.3.3");
//                } else {
//                    listSubtajuk.add("2.3.4");
//                }
//            }
//        }
//        else {
//            listSubtajuk.add("2.3.1");
//            listSubtajuk.add("2.3.2");
//        }
//        listSubtajuk.add("4.1");
//        listSubtajuk.add("5.1");
//        listSubtajuk.add("5.2");
//
//        billNo.add("0");
//        billNo.add("1");
//        billNo.add("2");
//        billNo.add("2");
//        billNo.add("2");
//        billNo.add("2");
//        if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {
//            billNo.add("2");
//            billNo.add("2");
//            if (pemohon.getStatusKahwin().equals("Berkahwin")) {
//                if (pemohonHubungan.getKaitan().equals("ISTERI")) {
//                    billNo.add("2");
//                } else {
//                    billNo.add("2");
//                }
//
//            }
//        } else {
//            billNo.add("2");
//            billNo.add("2");
//        }
//        
//        billNo.add("4");
//        billNo.add("5");
//        billNo.add("5");
//
//
//        if (permohonankertas != null) {
//            logger.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
//            infoAudit = permohonankertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            pelupusanService.simpanPermohonanKertas(permohonankertas);
//
//
//            if (!permohonankertas.getSenaraiKandungan().isEmpty()) {
//
//                for (int j = 0; j < permohonankertas.getSenaraiKandungan().size(); j++) {
//                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
//                    kertasKandungan = permohonankertas.getSenaraiKandungan().get(j);
//
//                    if (kertasKandungan.getBil() == 0) {
//                        kertasKandungan.setKandungan(tajukPlps);
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("1.1")) {
//                        kertasKandungan.setKandungan(tujuanPlps);
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("2.1.1")) {
//                        kertasKandungan.setKandungan(perihalpermohonanPlps);
//                    }
////                    if (kertasKandungan.getSubtajuk().equals("2.1.2")) {
////                        kertasKandungan.setKandungan(perihalpermohonan1);
////                    }
//                    if (kertasKandungan.getSubtajuk().equals("2.1.2")) {
//                        kertasKandungan.setKandungan(perihalpermohonan2);
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("2.2.1")) {
//                        kertasKandungan.setKandungan(perihaltanah);
//                    }
////                    if (kertasKandungan.getSubtajuk().equals("2.2.2")) {
////                        kertasKandungan.setKandungan(perihaltanah1);
////                    }
//                    if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O") || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) 
//                    {
//                        if (kertasKandungan.getSubtajuk().equals("2.3.1")) {
//
//                            kertasKandungan.setKandungan(perihalpemohon);
//                        }
//                        if (kertasKandungan.getSubtajuk().equals("2.3.2")) {
//                            kertasKandungan.setKandungan(perihalpemohon1);
//                        }
//                        if (kertasKandungan.getSubtajuk().equals("2.3.3")) {
//                            kertasKandungan.setKandungan(perihalpemohon2);
//                        }
//                        if(kertasKandungan.getSubtajuk().equals("2.3.4")){
//                            kertasKandungan.setKandungan(perihalpemohon2suami);
//                        }
//                    }
//                    else {
//                         if (kertasKandungan.getSubtajuk().equals("2.3.1")) {
//                            kertasKandungan.setKandungan(perihalpemohon_s);
//                        }
//                        if (kertasKandungan.getSubtajuk().equals("2.3.2")) {
//                            kertasKandungan.setKandungan(perihalpemohon1);
//                        }
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("4.1")) {
//                        kertasKandungan.setKandungan(ulasanyb);
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("5.1")) {
//                        kertasKandungan.setKandungan(pentabirtanah1);
//                    }
//                    if (kertasKandungan.getSubtajuk().equals("5.2")) {
//                        kertasKandungan.setKandungan(pentabirtanah2);
//                    }
//                    kertasKandungan.setInfoAudit(infoAudit);
//                    pelupusanService.simpanPermohonanKertasKandungan(kertasKandungan);
//                }
//            }
//        } else {
//            logger.info("------else------permohonankertas:: NULL--------------::" + permohonankertas);
//
//            permohonankertas = new PermohonanKertas();
//            permohonankertas.setInfoAudit(infoAudit);
//            permohonankertas.setCawangan(pengguna.getKodCawangan());
//            permohonankertas.setPermohonan(permohonan);
//            permohonankertas.setTajuk("KERTAS MMKN");
//            permohonankertas.setKodDokumen(kodDokumenDAO.findById("RMN"));
//            pelupusanService.simpanPermohonanKertas(permohonankertas);
//
//            for (int i = 0; i < listUlasan.size(); i++) {
//                String ulasan = (String) listUlasan.get(i);
//                logger.info("---------ulasan-----------::" + ulasan);
//                String billNo1 = billNo.get(i);
//                String subTajuk = listSubtajuk.get(i);
//                logger.info("---------subTajuk-----------::" + subTajuk);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//                PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
//                kertasKandungan.setKertas(permohonankertas);
//                // kertasKdgn.setBil(i + 1);
//                kertasKandungan.setBil(Integer.parseInt(billNo1));
//                kertasKandungan.setSubtajuk(subTajuk);
//                kertasKandungan.setInfoAudit(infoAudit);
//                kertasKandungan.setKandungan(ulasan);
//                kertasKandungan.setCawangan(pengguna.getKodCawangan());
//                pelupusanService.simpanPermohonanKertasKandungan(kertasKandungan);
//            }
//
//        }
//
//        senaraiLaporanKandungan1 = pelupusanService.findByIdbysubtajuk(permohonankertas.getIdKertas(), 2);
//        logger.info("---------senaraiLaporanKandungan1--------:" + senaraiLaporanKandungan1);
//        logger.info("---------senaraiLaporanKandungan1--size------:" + senaraiLaporanKandungan1.size());
//
//        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//        logger.info("---------kira--------:" + kira);
//        InfoAudit iaP = new InfoAudit();
//        for (int i = 4; i <= kira; i++) {
//            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
//                logger.info("---------if--------:");
//                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
//                logger.info("---------id--------:" + id);
//                if (id != null) {
//                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
//                    iaP = permohonanKertasKandungan1.getInfoAudit();
//                    iaP.setDiKemaskiniOleh(pengguna);
//                    iaP.setTarikhKemaskini(new java.util.Date());
//
//                    logger.info("---------Bil--------:" + permohonanKertasKandungan1.getBil());
//                    logger.info("------SubTajuk-----------:" + permohonanKertasKandungan1.getSubtajuk());
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(pengguna);
//            }
//            permohonanKertasKandungan1.setKertas(permohonankertas);
//            logger.info("---------i--------:" + i);
//            permohonanKertasKandungan1.setBil(2);
//            String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
//            logger.info("---------kandungan--------:" + kandungan);
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            logger.info("---------Bil-2-------:" + permohonanKertasKandungan1.getBil());
//            logger.info("------SubTajuk2-----------:" + permohonanKertasKandungan1.getSubtajuk());
//            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//
//        }
//
//        senaraiLaporanKandunganpbtanah = pelupusanService.findByIdbysubtajuk(permohonankertas.getIdKertas(), 5);
//        logger.info("---------senaraiLaporanKandunganpbtanah--------:" + senaraiLaporanKandunganpbtanah);
//        logger.info("---------senaraiLaporanKandunganpbtanah--size------:" + senaraiLaporanKandunganpbtanah.size());
//
//        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//        logger.info("---------kira--------:" + kira);
//        //InfoAudit iaP = new InfoAudit();
//        for (int i = 2; i <= kira; i++) {
//            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            if (senaraiLaporanKandunganpbtanah.size() != 0 && i <= senaraiLaporanKandunganpbtanah.size()) {
//                logger.info("---------if--------:");
//                Long id = senaraiLaporanKandunganpbtanah.get(i - 1).getIdKandungan();
//                logger.info("---------id--------:" + id);
//                if (id != null) {
//                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
//                    iaP = permohonanKertasKandungan1.getInfoAudit();
//                    iaP.setDiKemaskiniOleh(pengguna);
//                    iaP.setTarikhKemaskini(new java.util.Date());
//
//                    logger.info("---------Bil--------:" + permohonanKertasKandungan1.getBil());
//                    logger.info("------SubTajuk-----------:" + permohonanKertasKandungan1.getSubtajuk());
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(pengguna);
//            }
//            permohonanKertasKandungan1.setKertas(permohonankertas);
//            logger.info("---------i--------:" + i);
//            permohonanKertasKandungan1.setBil(5);
//            String kandungan = getContext().getRequest().getParameter("kandungan5" + i);
//            logger.info("---------kandungan--------:" + kandungan);
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("5.1." + i);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            logger.info("---------Bil-2-------:" + permohonanKertasKandungan1.getBil());
//            logger.info("------SubTajuk2-----------:" + permohonanKertasKandungan1.getSubtajuk());
//            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//
//        }
//        
//        //added for Mohon_rujulur
//
// senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
//        senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
//        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
//        for (int i = 1; i <= kira; i++) {
//           
//            String kandungan = getContext().getRequest().getParameter("ulasan" + i);
//            String moh_rujLuar = getContext().getRequest().getParameter("rowAgensi" + i);
//            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
//            permohonanRujukanLuar = pelupusanService.findByIdRujukan(Long.parseLong(moh_rujLuar));
//            permohonanRujukanLuar.setUlasan(kandungan);
//            pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);
//        }
//
//
//
////        if (kodHmlk != null) {
////            KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
////            hakmilikPermohonan.setKodHakmilik(khm);
////        }
////
////        if (kod != null) {
////            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
////            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
////        }
////
////        if (kodSktn != null) {
////            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
////            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
////        }
////
////        if (jenishakmilik != null) {
////            KodHakmilik jenh = kodHakmilikDAO.findById(jenishakmilik);
////            hakmilikPermohonan.setKodHakmilik(jenh);
////        }
//        InfoAudit info = new InfoAudit();
//        if (fasaPermohonan != null) {
//            info = fasaPermohonan.getInfoAudit();
//            info.setTarikhKemaskini(new java.util.Date());
//            info.setDiKemaskiniOleh(pengguna);
//        } else {
//            info.setDimasukOleh(pengguna);
//            info.setTarikhMasuk(new java.util.Date());
//            fasaPermohonan = new FasaPermohonan();
//        }
//        // fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kodKepu));
//        fasaPermohonan.setInfoAudit(info);
//        fasaPermohonan.setCawangan(pengguna.getKodCawangan());
//        fasaPermohonan.setPermohonan(permohonan);
//        fasaPermohonan.setIdAliran(stageId);
//        if(syortolaklulus!=null)
//            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
//        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
//        pelupusanService.simpanPermohonan(permohonan);
//
//
//        //STRAT: FOR LPS
//
//        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
//
//            logger.info("--------------LPS-------------------");
//
//            // Mohon Hakmilik
//
//            logger.info("--------------LPS hakmilikPermohonan Saving::-------------------");
//            if (hakmilikPermohonan != null) {
//                infoAudit = hakmilikPermohonan.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//
//            } else {
//                hakmilikPermohonan = new HakmilikPermohonan();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }
//
//            logger.info("--------------pengguna-------------------" + pengguna);
//            hakmilikPermohonan.setInfoAudit(infoAudit);
//            hakmilikPermohonan.setPermohonan(permohonan);
//            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//
//            //Mohon Tuntukos
//
//            logger.info("--------------LPS PermohonanTuntutanKos Saving::-------------------");
//
//            PermohonanTuntutanKos permohonanTuntutanKosTemp = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
//
//            logger.info("--------------LPS permohonanTuntutanKosTemp ::-------------------:" + permohonanTuntutanKosTemp);
//
//            if (permohonanTuntutanKosTemp != null) {
//                logger.info("--------------LPS permohonanTuntutanKosTemp Not Null ::-------------------:");
//                infoAudit = permohonanTuntutanKosTemp.getInfoAudit();
//                logger.info("--------------LPS infoAudit ::-------------------:" + infoAudit);
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            } else {
//                logger.info("--------------LPS permohonanTuntutanKosTemp Null ::-------------------:");
//                permohonanTuntutanKosTemp = new PermohonanTuntutanKos();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }
//
//            logger.info("--------------pengguna-------------------" + pengguna);
//            permohonanTuntutanKosTemp.setInfoAudit(infoAudit);
//            permohonanTuntutanKosTemp.setPermohonan(permohonan);
//            permohonanTuntutanKosTemp.setCawangan(pengguna.getKodCawangan());
//            permohonanTuntutanKosTemp.setKodTransaksi(kodTuntutDAO.findById("DISB4A").getKodKewTrans());
//            permohonanTuntutanKosTemp.setItem(kodTuntutDAO.findById("DISB4A").getNama());
//            permohonanTuntutanKosTemp.setKodTuntut(kodTuntutDAO.findById("DISB4A")) ;
//            permohonanTuntutanKosTemp.setHakmilikPermohonan(hakmilikPermohonan);
//            permohonanTuntutanKosTemp.setAmaunTuntutan(permohonanTuntutanKos.getAmaunTuntutan());
//            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKosTemp);
//
//
//            // Mohon Permit Item
//
//            logger.info("--------------LPS permohonanPermitItem Saving::-------------------");
//            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
//            KodItemPermit kitem = new KodItemPermit();
//            kitem.setKod(kodPermit);
//            infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new Date());
//            permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
//            if (permohonanPermitItem != null) {
//                 logger.info("--------------LPS permohonanPermitItem  NOT Null::-------------------");
//
//                permohonanPermitItem.setPermohonan(permohonan);
//                permohonanPermitItem.setInfoAudit(infoAudit);
//                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
//                permohonanPermitItem.setKodItemPermit(kitem);
//                pelupusanService.saveOrUpdate(permohonanPermitItem);
//
//            } else {
//                 logger.info("--------------LPS permohonanPermitItem Null::-------------------");
//                permohonanPermitItem = new PermohonanPermitItem();
//                permohonanPermitItem.setPermohonan(permohonan);
//                permohonanPermitItem.setInfoAudit(infoAudit);
//                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
//                permohonanPermitItem.setKodItemPermit(kitem);
//                pelupusanService.saveOrUpdate(permohonanPermitItem);
//            }
//        }
////senaraiLaporanKandunganpbtanah = pelupusanService.findBySubtajuk(permohonankertas.getIdKertas(), 5);
//        // END : LPS
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
////        rehydrate();
//
// return new ForwardResolution("/WEB-INF/jsp/pelupusan/plps/draf_mmkn_mlk.jsp").addParameter("tab", "true");
//    }

    public Resolution showsyortolaklulus() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        syortolaklulus = (String) getContext().getRequest().getParameter("syortolaklulus");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        FasaPermohonan mohonFasaSyorTolak = new FasaPermohonan();
        String idAliran = new String();
        if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            idAliran = "sedia_draf_mmkn";
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            idAliran = "sedia_draf_mmkn";
        }
        mohonFasaSyorTolak = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, idAliran);
        InfoAudit ia = new InfoAudit();
        if (mohonFasaSyorTolak != null) {
            ia = mohonFasaSyorTolak.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonFasaSyorTolak = new FasaPermohonan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        mohonFasaSyorTolak.setInfoAudit(ia);
        mohonFasaSyorTolak.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
        rehydrate();
        return new JSP("pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }
    // saving PTG

    public Resolution simpanPTG() {


        logger.info("------------Simpan Hurian started-----------::");


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");

        logger.info("-------Simpan---PTG--in--permohonankertas--------------::");


        if (permohonankertas != null) {
            logger.info("------if------permohonankertas NOT Null--------------::" + permohonankertas);
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pelupusanService.simpanPermohonanKertas(permohonankertas);


            senaraiLaporanKandunganptg1 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 6);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganptg1.size() != 0 && i <= senaraiLaporanKandunganptg1.size()) {
                    Long id = senaraiLaporanKandunganptg1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(6);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("6." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonankertas);
                String kandungan = getContext().getRequest().getParameter("kandungan2" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }


            senaraiLaporanKandunganptg2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 7);
            senaraiPerihalTanah = pelupusanService.findByIdbyBilExcludeSubtajuk(permohonankertas.getIdKertas(), 3, "1");


            kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganptg2.size() != 0 && i <= senaraiLaporanKandunganptg2.size()) {
                    Long id = senaraiLaporanKandunganptg2.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(7);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("7." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonankertas);
                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pptr/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getHuraianPentadbir2() {
        return huraianPentadbir2;
    }

    public void setHuraianPentadbir2(String huraianPentadbir2) {
        this.huraianPentadbir2 = huraianPentadbir2;
    }

    public String getHuraianPentadbir3() {
        return huraianPentadbir3;
    }

    public void setHuraianPentadbir3(String huraianPentadbir3) {
        this.huraianPentadbir3 = huraianPentadbir3;
    }

    public String getHuraianPentadbir4() {
        return huraianPentadbir4;
    }

    public void setHuraianPentadbir4(String huraianPentadbir4) {
        this.huraianPentadbir4 = huraianPentadbir4;
    }

    public String getHuraianPentadbir5() {
        return huraianPentadbir5;
    }

    public void setHuraianPentadbir5(String huraianPentadbir5) {
        this.huraianPentadbir5 = huraianPentadbir5;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public String getHuraianPtg2() {
        return huraianPtg2;
    }

    public void setHuraianPtg2(String huraianPtg2) {
        this.huraianPtg2 = huraianPtg2;
    }

    public String getHuraianPtg3() {
        return huraianPtg3;
    }

    public void setHuraianPtg3(String huraianPtg3) {
        this.huraianPtg3 = huraianPtg3;
    }

    public String getHuraianPtg4() {
        return huraianPtg4;
    }

    public void setHuraianPtg4(String huraianPtg4) {
        this.huraianPtg4 = huraianPtg4;
    }

    public String getHuraianPtg5() {
        return huraianPtg5;
    }

    public void setHuraianPtg5(String huraianPtg5) {
        this.huraianPtg5 = huraianPtg5;
    }

    public String getSyorPtg2() {
        return syorPtg2;
    }

    public void setSyorPtg2(String syorPtg2) {
        this.syorPtg2 = syorPtg2;
    }

    public String getSyorPtg3() {
        return syorPtg3;
    }

    public void setSyorPtg3(String syorPtg3) {
        this.syorPtg3 = syorPtg3;
    }

    public String getSyorPtg4() {
        return syorPtg4;
    }

    public void setSyorPtg4(String syorPtg4) {
        this.syorPtg4 = syorPtg4;
    }

    public String getSyorPtg5() {
        return syorPtg5;
    }

    public void setSyorPtg5(String syorPtg5) {
        this.syorPtg5 = syorPtg5;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getSyorPentadbir2() {
        return syorPentadbir2;
    }

    public void setSyorPentadbir2(String syorPentadbir2) {
        this.syorPentadbir2 = syorPentadbir2;
    }

    public String getSyorPentadbir3() {
        return syorPentadbir3;
    }

    public void setSyorPentadbir3(String syorPentadbir3) {
        this.syorPentadbir3 = syorPentadbir3;
    }

    public String getSyorPentadbir4() {
        return syorPentadbir4;
    }

    public void setSyorPentadbir4(String syorPentadbir4) {
        this.syorPentadbir4 = syorPentadbir4;
    }

    public String getSyorPentadbir5() {
        return syorPentadbir5;
    }

    public void setSyorPentadbir5(String syorPentadbir5) {
        this.syorPentadbir5 = syorPentadbir5;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKodKepu() {
        return kodKepu;
    }

    public void setKodKepu(String kodKepu) {
        this.kodKepu = kodKepu;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getPerihalpermohonan() {
        return perihalpermohonan;
    }

    public void setPerihalpermohonan(String perihalpermohonan) {
        this.perihalpermohonan = perihalpermohonan;
    }

    public String getPerihaltanah() {
        return perihaltanah;
    }

    public void setPerihaltanah(String perihaltanah) {
        this.perihaltanah = perihaltanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public String getPerihalpermohonan1() {
        return perihalpermohonan1;
    }

    public void setPerihalpermohonan1(String perihalpermohonan1) {
        this.perihalpermohonan1 = perihalpermohonan1;
    }

    public String getPerihalpermohonan2() {
        return perihalpermohonan2;
    }

    public void setPerihalpermohonan2(String perihalpermohonan2) {
        this.perihalpermohonan2 = perihalpermohonan2;
    }

//    public String getPerihaltanah1() {
//        return perihaltanah1;
//    }
//
//    public void setPerihaltanah1(String perihaltanah1) {
//        this.perihaltanah1 = perihaltanah1;
//    }
    public String getPerihalpemohon() {
        return perihalpemohon;
    }

    public void setPerihalpemohon(String perihalpemohon) {
        this.perihalpemohon = perihalpemohon;
    }

    public String getPentabirtanah1() {
        return pentabirtanah1;
    }

    public void setPentabirtanah1(String pentabirtanah1) {
        this.pentabirtanah1 = pentabirtanah1;
    }

    public String getPentabirtanah2() {
        return pentabirtanah2;
    }

    public void setPentabirtanah2(String pentabirtanah2) {
        this.pentabirtanah2 = pentabirtanah2;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan3() {
        return senaraiLaporanKandungan3;
    }

    public void setSenaraiLaporanKandungan3(List<PermohonanKertasKandungan> senaraiLaporanKandungan3) {
        this.senaraiLaporanKandungan3 = senaraiLaporanKandungan3;
    }

    public String getUlasanyb() {
        return ulasanyb;
    }

    public void setUlasanyb(String ulasanyb) {
        this.ulasanyb = ulasanyb;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg1() {
        return senaraiLaporanKandunganptg1;
    }

    public void setSenaraiLaporanKandunganptg1(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1) {
        this.senaraiLaporanKandunganptg1 = senaraiLaporanKandunganptg1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg2() {
        return senaraiLaporanKandunganptg2;
    }

    public void setSenaraiLaporanKandunganptg2(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2) {
        this.senaraiLaporanKandunganptg2 = senaraiLaporanKandunganptg2;
    }

    public String getHurianpengarah() {
        return hurianpengarah;
    }

    public void setHurianpengarah(String hurianpengarah) {
        this.hurianpengarah = hurianpengarah;
    }

    public String getSyorpengarah() {
        return syorpengarah;
    }

    public void setSyorpengarah(String syorpengarah) {
        this.syorpengarah = syorpengarah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganpbtanah() {
        return senaraiLaporanKandunganpbtanah;
    }

    public void setSenaraiLaporanKandunganpbtanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah) {
        this.senaraiLaporanKandunganpbtanah = senaraiLaporanKandunganpbtanah;
    }

    public String getPerihalpemohon1() {
        return perihalpemohon1;
    }

    public void setPerihalpemohon1(String perihalpemohon1) {
        this.perihalpemohon1 = perihalpemohon1;
    }

    public String getPerihalpemohon2() {
        return perihalpemohon2;
    }

    public void setPerihalpemohon2(String perihalpemohon2) {
        this.perihalpemohon2 = perihalpemohon2;
    }

    public String getPerihalpemohon_s() {
        return perihalpemohon_s;
    }

    public void setPerihalpemohon_s(String perihalpemohon_s) {
        this.perihalpemohon_s = perihalpemohon_s;
    }

    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public List<KodKadarPremium> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<KodKadarPremium> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getTajukPlps() {
        return tajukPlps;
    }

    public void setTajukPlps(String tajukPlps) {
        this.tajukPlps = tajukPlps;
    }

    public String getTujuanPlps() {
        return tujuanPlps;
    }

    public void setTujuanPlps(String tujuanPlps) {
        this.tujuanPlps = tujuanPlps;
    }

    public String getPerihalpermohonanPlps() {
        return perihalpermohonanPlps;
    }

    public void setPerihalpermohonanPlps(String perihalpermohonanPlps) {
        this.perihalpermohonanPlps = perihalpermohonanPlps;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public String getPerihalpemohon2suami() {
        return perihalpemohon2suami;
    }

    public void setPerihalpemohon2suami(String perihalpemohon2suami) {
        this.perihalpemohon2suami = perihalpemohon2suami;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
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

    public PermohonanPermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermohonanPermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public KodUOM getkULuas() {
        return kULuas;
    }

    public void setkULuas(KodUOM kULuas) {
        this.kULuas = kULuas;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public List<PermohonanRujukanLuar> getSenaraiLaporanKandunganRujukLuar() {
        return senaraiLaporanKandunganRujukLuar;
    }

    public void setSenaraiLaporanKandunganRujukLuar(List<PermohonanRujukanLuar> senaraiLaporanKandunganRujukLuar) {
        this.senaraiLaporanKandunganRujukLuar = senaraiLaporanKandunganRujukLuar;
    }

    public List getSenaraiLaporanKandunganUtil() {
        return senaraiLaporanKandunganUtil;
    }

    public void setSenaraiLaporanKandunganUtil(List senaraiLaporanKandunganUtil) {
        this.senaraiLaporanKandunganUtil = senaraiLaporanKandunganUtil;
    }

    public boolean isPtd() {
        return ptd;
    }

    public void setPtd(boolean ptd) {
        this.ptd = ptd;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public KodDUN getKodDun() {
        return kodDun;
    }

    public void setKodDun(KodDUN kodDun) {
        this.kodDun = kodDun;
    }

    public String getSyortolaklulus() {
        return syortolaklulus;
    }

    public void setSyortolaklulus(String syortolaklulus) {
        this.syortolaklulus = syortolaklulus;
    }

    public boolean isOpenPTG() {
        return openPTG;
    }

    public void setOpenPTG(boolean openPTG) {
        this.openPTG = openPTG;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public boolean isEditPTG() {
        return editPTG;
    }

    public void setEditPTG(boolean editPTG) {
        this.editPTG = editPTG;
    }

    public boolean isFirstTimeOpen() {
        return firstTimeOpen;
    }

    public void setFirstTimeOpen(boolean firstTimeOpen) {
        this.firstTimeOpen = firstTimeOpen;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<PermohonanRujukanLuar> getSenaraiYBAdun() {
        return senaraiYBAdun;
    }

    public void setSenaraiYBAdun(List<PermohonanRujukanLuar> senaraiYBAdun) {
        this.senaraiYBAdun = senaraiYBAdun;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public String getPerihalBadanPengawal() {
        return perihalBadanPengawal;
    }

    public void setPerihalBadanPengawal(String perihalBadanPengawal) {
        this.perihalBadanPengawal = perihalBadanPengawal;
    }

    public DisLaporanTanahSempadan getDisLaporanTanahSempadan() {
        return disLaporanTanahSempadan;
    }

    public void setDisLaporanTanahSempadan(DisLaporanTanahSempadan disLaporanTanahSempadan) {
        this.disLaporanTanahSempadan = disLaporanTanahSempadan;
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

    public List<PermohonanKertasKandungan> getSenaraiPerihalTanah() {
        return senaraiPerihalTanah;
    }

    public void setSenaraiPerihalTanah(List<PermohonanKertasKandungan> senaraiPerihalTanah) {
        this.senaraiPerihalTanah = senaraiPerihalTanah;
    }
}
