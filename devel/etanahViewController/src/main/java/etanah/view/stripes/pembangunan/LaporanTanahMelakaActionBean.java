/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

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
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.PermohonanPlotPelan;
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
import etanah.service.RegService;

/**
 *
 * @author syaiful
 */
@UrlBinding("/pembangunan/melaka/laporanTanah")
public class LaporanTanahMelakaActionBean extends AbleActionBean {

    @Inject
    RegService regService;
    @Inject
    PermohonanDAO permohonanDAO;
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
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
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
    private List<ImejLaporan> tenggaraImejLaporanList;
    private List<ImejLaporan> baratDayaImejLaporanList;
    private List<ImejLaporan> baratLautImejLaporanList;
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
    private static final Logger LOG = Logger.getLogger(LaporanTanahMelakaActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private static final BigDecimal luas8EkarInH = new BigDecimal(3.237488);
    private static final BigDecimal luas8EkarInMp = new BigDecimal(32374.88);
    private BPelService serviceBPel;
    private boolean textSyor;
    private String noWartaKonon;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String disabled;
    String disabled2;
    String format1 = "image/jpeg";
    String format2 = "image/pjpeg";
    String _MSG_SUCCES_SAVE = "Maklumat telah berjaya disimpan.";
    String _MSG_SUCCES_UPDATE = "Maklumat telah berjaya dikemaskini.";
    String _PAGE = "pembangunan/pecahSempadan/laporan_tanah_melaka.jsp";
    String _FWRD_PAGE = "/WEB-INF/jsp/" + _PAGE;
    String _REKOD_ULASAN_POP = "pembangunan/melaka/rekod_ulasan_popup.jsp";
    String _PAGE_TRIZAB = "/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp";
    String _PAGE_IN_TRIZAB = "pengambilan/kemasukan_tanahrizab.jsp";
    String _PAGE_EDIT_TRIZAB = "pengambilan/kemaskini_trizab.jsp";
    String _PAGE_EDIT_PNGMBLN = "pengambilan/maklumat_pengambilan_pemohon.jsp";
    String _PAGE_LT_POPUP = "pembangunan/melaka/laporan_tanah_popup.jsp";
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
    private String kodKatgTanah;
    private String rujukanFail;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP(_PAGE).addParameter("tab", "true");
    }

    public Resolution showeditTanahRizab() {
        idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        return new JSP(_PAGE_EDIT_TRIZAB).addParameter("popup", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP(_PAGE_IN_TRIZAB).addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("-------------rehydrate---------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getKodNegeri();

        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        timurLautImejLaporanList = new ArrayList<ImejLaporan>();
        tenggaraImejLaporanList = new ArrayList<ImejLaporan>();
        baratLautImejLaporanList = new ArrayList<ImejLaporan>();
        baratDayaImejLaporanList = new ArrayList<ImejLaporan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
        findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            tanahRizabList = permohonan.getSenaraiTanahRizab();
            permohonanPengambilan = service.findByidPermohonan(idPermohonan);
            permohonanLaporanBangunanList = pembangunanService.getLaporBngnByIdLaporan(idPermohonan);
            permohonanLaporanTanamanList = pembangunanService.getLaporTnmnByIdLaporan(idPermohonan);
            List<LaporanTanah> listLT = pembangunanService.findLaporanTanahByIdPermohonan3(idPermohonan);
            if (listLT.size() > 0) {
                laporanTanah = listLT.get(0);
            }


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
                List<PermohonanLaporanUlasan> permohonanLU = pembangunanService.findPemohonLaporanUlasanByIdPermohonanList(idPermohonan);
                
                //eda added coz error arrayoutof bound
                if(permohonanLU.size() > 0){
                    permohonanLaporanUlasan = permohonanLU.get(0);
                }
                

                if (laporanTanah.getStrukturTanah() != null) {
                    strukturTanahString = laporanTanah.getStrukturTanah().getKod();
                } else {
                    strukturTanahString = "";
                }

                if (laporanTanah.getRancanganKerajaan() != null) {
                    rujukanFail = laporanTanah.getRancanganKerajaan();
                }

                if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                    kategoriTanahBaruString = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                } else {
                    kategoriTanahBaruString = "0";
                }
            } else {
                laporanTanah = new LaporanTanah();
            }

            listFasa = pembangunanService.findFasaPermohonanByIdPermohonan2(permohonan.getIdPermohonan());
            listFasa2 = pembangunanService.findFasaPermohonanByIdPermohonan(permohonan.getIdPermohonan());

            LOG.info("###### listFasa : " + listFasa.size());
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (fp.getIdAliran().equals("laporantanah")) {
                        fasaPermohonan = listFasa.get(i);
                    }
                }
            }
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (fp.getIdAliran().equals("semaklaporantanah") || fp.getIdAliran().equals("semakderafperakuan") || fp.getIdAliran().equals("perakuanmmknptd")) {
                        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                        fasaPermohonan2 = listFasa.get(i);
                    }
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

        }
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pengguna.getNama());

        BpelServices(stageId, pengguna);
        if (!StringUtils.isNotBlank(stageId)) {
            stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");

        }
        if (stageId.equals("semaklaporantanah") || stageId.equals("semakderafperakuan") || stageId.equals("perakuanmmknptd") || stageId.equals("semak_laporan_tanah")) {
            disabled = "true";
        } else {
            disabled = "false";
        }
        if (stageId.equals("laporantanah") || stageId.equals("laporan_tanah")) {
            disabled2 = "true";
        } else {
            disabled2 = "false";
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

    public Resolution simpanLaporanTanah() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("###### simpan Laporan Tanah");
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        LOG.info("###### id Pengguna : " + idPermohonan);
//        LaporanTanah laporanTanahcek = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        List<LaporanTanah> listLT = pembangunanService.findLaporanTanahByIdPermohonan3(idPermohonan);
        if (listLT.size() > 0) {
            LaporanTanah laporanTanahcek = listLT.get(0);


//            if (laporanTanahcek != null) {
            LOG.info("###### not null");
            infoAudit = laporanTanahcek.getInfoAudit();
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);

            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }
            if (strukturTanahString != null) {
                laporanTanah.setStrukturTanah(KST);
            } else {
                laporanTanah.setStrukturTanah(null);
            }
            laporanTanah.setRancanganKerajaan(rujukanFail);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            addSimpleMessage(_MSG_SUCCES_SAVE);
//            }
        } else {
            LOG.info("###### NULL");
            System.out.println("NULL Laporan tanah" + laporanTanah);
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            addSimpleMessage(_MSG_SUCCES_SAVE);
        }

        if (kategoriTanahBaruString != null) {
            KKtgT = kodKategoriTanahDAO.findById(kategoriTanahBaruString);
        } else {
            KKtgT = null;
        }
        if (strukturTanahString != null) {
            KST = kodStrukturTanahDAO.findById(strukturTanahString);
            laporanTanah.setStrukturTanah(KST);
        } else {
            KST = null;
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        LOG.info("####simpan###hakmilikPermohonanList : " + hakmilikPermohonanList.size());
        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hakmilikPermohonanList.get(a);
            System.out.println("#######hakmilikPermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setDendaPremium(hakmilikPermohonan.getDendaPremium());
            hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            hp.setCatatan(hakmilikPermohonan.getCatatan());
            hp.setKosInfra(hakmilikPermohonan.getKosInfra());
            if (kategoriTanahBaruString != null) {
                hp.setKategoriTanahBaru(KKtgT);
            }

            String syaratBaru1 = getContext().getRequest().getParameter("syaratBaru1");
            LOG.info("-----syaratBaru----:" + syaratBaru1);
            if (syaratBaru1 != null && !syaratBaru1.equals("")) {
                LOG.info("---if--syaratBaru----:" + syaratBaru1);
                hp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(syaratBaru1));
            } else {
                LOG.info("---if--syaratBaru----:" + syaratBaru1);
            }

            String sekatanBaru1 = getContext().getRequest().getParameter("sekatanBaru1");
            LOG.info("-----sekatanBaru----:" + sekatanBaru1);
            if (sekatanBaru1 != null && !sekatanBaru1.equals("")) {
                LOG.info("--if---sekatanBaru----:" + sekatanBaru1);
                hp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(sekatanBaru1));
            } else {
                LOG.info("--else---sekatanBaru----:" + sekatanBaru1);
            }
            pembangunanService.simpanHakmilikPermohonan(hp);
        }

        for (int i = 0; i < senaraiKodKecerunanTanah.size(); i++) {
            System.out.println("-------------senaraiKodKecerunanTanah.size---" + senaraiKodKecerunanTanah.size());
            KodKecerunanTanah kodCerunTanah = new KodKecerunanTanah();
            kodCerunTanah = senaraiKodKecerunanTanah.get(i);

            String kod = getContext().getRequest().getParameter(kodCerunTanah.getNama());
            if (kod != null && !kod.equals("")) {
                laporanCerun = new PermohonanLaporanCerun();
                laporanCerun.setIdPermohonan(permohonan);
                laporanCerun.setInfoAudit(infoAudit);
                laporanCerun.setCawangan(permohonan.getCawangan());
                laporanCerun.setKodCerunanTanah(kodKecerunanTanahDAO.findById(kod));
                pembangunanService.simpanPermohonanLaporCerun(laporanCerun);
            }
        }
        BpelServices(stageId, pengguna);
        String lT = "semaklaporantanah";
        if (fasaPermohonan != null) {
            if (stageId.equalsIgnoreCase("laporantanah")) {
                LOG.info("###### laporantanah SAVE");
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
                pembangunanService.simpanFasaPermohonan2(fasaPermohonan);
            } else {
                LOG.info("###### semaklaporantanah SAVE");
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
                fasaPermohonan2.setIdAliran(stageId);
                pembangunanService.simpanFasaPermohonan2(fasaPermohonan2);
            }
        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan2 = new FasaPermohonan();

            if (stageId.equalsIgnoreCase("laporantanah")) {
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
            } else {
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
                fasaPermohonan2.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
            }
        }

        if (fasaPermohonan != null) {
            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");//NF
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        } else {
            if (permohonanRujukanLuar != null) {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
        if (permohonanLaporanUlasan != null) {
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        } else {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution addHakmilikImage() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idDokumen = getContext().getRequest().getParameter("idDokumen");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        imejLaporan = laporanTanahService.getLaporImejByHakmilikDokumen(idHakmilik, dokumen.getIdDokumen());
        if (imejLaporan == null) {
            imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(cawangan);
            imejLaporan.setDokumen(dokumen);
            imejLaporan.setHakmilik(hakmilik);
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            imejLaporan.setInfoAudit(infoAudit);
            imejLaporan.setLaporanTanah(laporanTanah);
            laporanTanahService.simpanHakmilikImej(imejLaporan);
            rehydrate();
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            addSimpleMessage("Imej telah wujud untuk hakmilik ini.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution addLaporanImage() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idDokumen = getContext().getRequest().getParameter("idDokumen");

        pandanganImej = 'L';
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("######idhakmilik test:" + idHakmilik);
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah, dokumen, pandanganImej);
        if (imejLaporan == null) {
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(cawangan);
            imejLaporan.setDokumen(dokumen);
            imejLaporan.setPandanganImej(pandanganImej);
            imejLaporan.setInfoAudit(infoAudit);
            imejLaporan.setLaporanTanah(laporanTanah);
            imejLaporan.setHakmilik(hakmilik);
            laporanTanahService.simpanHakmilikImej(imejLaporan);
            rehydrate();
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            addSimpleMessage("Imej already exist.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution simpanLaporanTanah2() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("###### simpanLaporanTanah2 ");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        BpelServices(stageId, pengguna);
        String lT = "semaklaporantanah";

        if (fasaPermohonan2 != null) {
            LOG.info("###### semaklaporantanah2 SAVE");
            fasaPermohonan2.setPermohonan(permohonan);
            fasaPermohonan2.setCawangan(permohonan.getCawangan());
            fasaPermohonan2.setInfoAudit(infoAudit);
            fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan2.setIdAliran(lT);
            //Ulasan Penolong Pegawai Tanah Kanan
            fasaPermohonan2.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
        } else {
            fasaPermohonan2 = new FasaPermohonan();
            fasaPermohonan2.setPermohonan(permohonan);
            fasaPermohonan2.setCawangan(permohonan.getCawangan());
            fasaPermohonan2.setInfoAudit(infoAudit);
            fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan2.setIdAliran(lT);
            //Ulasan Penolong Pegawai Tanah Kanan
            fasaPermohonan2.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(_FWRD_PAGE).addParameter("tab", "true");
    }

    public Resolution tambahBangunanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        TB = getContext().getRequest().getParameter("TB");
        LOG.info("tambahBangunanPopup idPermohonan : " + idPermohonan + " TB : " + TB);
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
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
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm");
    }

    public Resolution simpanBangunan() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("###### simpanBangunan : idPermohonan = " + idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BpelServices(stageId, pengguna);
        if (idPermohonan == null) {
            System.out.println("Tiada id Permohonan");
        }
        if (idPermohonan != null) {
            System.out.println("id Permohonan" + idPermohonan);
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(permohonan.getIdPermohonan());
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
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution editBangunan() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
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
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution rekodUlasanPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("tambahBangunanPopup idPermohonan : " + idPermohonan);
        notaPermohonan = new PermohonanNota();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP(_REKOD_ULASAN_POP).addParameter("popup", "true");
    }

    public Resolution editRekodUlasanPopup() {
        String idPermohonanNota = getContext().getRequest().getParameter("idPermohonanNota");
        LOG.info("editRekodUlasanPopup idPermohonanNota : " + idPermohonanNota);
        notaPermohonan = permohonanNotaDAO.findById(Long.valueOf(idPermohonanNota));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(_REKOD_ULASAN_POP).addParameter("popup", "true");
    }

    public Resolution simpanEditRekodUlasan() {
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
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution deleteRekodUlasanPopup() {
        String idPermohonanNota = getContext().getRequest().getParameter("idPermohonanNota");
        LOG.info("deleteRekodUlasanPopup idPermohonanNota:" + idPermohonanNota);
        pembangunanService.deletePermohonanNota(Long.valueOf(idPermohonanNota));
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm");
    }

    public Resolution simpanRekodUlasan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("###### simpanRekodUlasan : idPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (idPermohonan == null) {
            LOG.info("Tiada id Permohonan");
        }
        if (idPermohonan != null) {

            BpelServices(stageId, pengguna);
            LOG.info("###### simpanRekodUlasan SAVE");
            notaPermohonan.setPermohonan(permohonanDAO.findById(idPermohonan));
            notaPermohonan.setCawangan(permohonan.getCawangan());
            notaPermohonan.setInfoAudit(infoAudit);
            notaPermohonan.setIdAliran(stageId);
            notaPermohonan.setStatusNota('A');
            pembangunanService.simpanNotaPermohonan(notaPermohonan);
        }
        addSimpleMessage(_MSG_SUCCES_SAVE);
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution deleteLaporCerun() {
        LOG.info("###### deleteLaporCerun");
        idLaporCerun = getContext().getRequest().getParameter("idLaporCerun");
        permohonanLaporanCerun = permohonanLaporanCerunDAO.findById(Long.parseLong(idLaporCerun));
        pembangunanService.deleteLaporCerun(permohonanLaporanCerun);
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "locate");
    }

    public String BpelServices(String taskId, Pengguna pengguna) {
        System.out.println("####BpelServices : TaskID : " + taskId + "Pengguna :" + pengguna);
        serviceBPel = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------" + taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBPel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }
        return stageId;
        //return stageId = "laporantanah";

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

    public Resolution searchtrizab() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        infoAudit = pengguna.getInfoAudit();

        permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
            }
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage(_MSG_SUCCES_SAVE);
        } else {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
            }
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage(_MSG_SUCCES_SAVE);
        }
        return new ForwardResolution(_PAGE_TRIZAB).addParameter("tab", "true");
    }

    public Resolution edit() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        infoAudit = new InfoAudit();
        if (infoAudit == null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
        } else {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
        }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
        addSimpleMessage("Data Telah Berjaya dikemaskini");
        return new JSP(_PAGE_EDIT_PNGMBLN).addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        infoAudit = new InfoAudit();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tanahrizabpermohonan = new TanahRizabPermohonan();
        idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

        if (tanahrizabpermohonan != null) {
            infoAudit = pengguna.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(infoAudit);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabService.deleteAll(tanahrizabpermohonan);
        }
        return new RedirectResolution(LaporanTanahMelakaActionBean.class, "locate");
    }

    public void simpanLaporanTanahpop() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        LOG.info("###### simpan Laporan Tanah pop");
        infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        LOG.info("###### id Pengguna : " + pengguna);
        laporanTanah = pembangunanService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            LOG.info("not null");
//            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }
            if (strukturTanahString != null) {
                laporanTanah.setStrukturTanah(KST);
            } else {
                laporanTanah.setStrukturTanah(null);
            }
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            LOG.info("-------------simpan if pop---------");
        } else {
            LOG.info("NULL");
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            LOG.info("-------------simpan else pop---");
        }

        if (kategoriTanahBaruString != null) {
            KKtgT = kodKategoriTanahDAO.findById(kategoriTanahBaruString);
        } else {
            KKtgT = null;
        }
        if (strukturTanahString != null) {
            KST = kodStrukturTanahDAO.findById(strukturTanahString);
            laporanTanah.setStrukturTanah(KST);
        } else {
            KST = null;
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        System.out.println("####simpan###hakmilikPermohonanList : " + hakmilikPermohonanList.size());

        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hakmilikPermohonanList.get(a);
            System.out.println("#######hakmilikPermohonan : " + hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            hp.setDendaPremium(hakmilikPermohonan.getDendaPremium());
            hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            hp.setCatatan(hakmilikPermohonan.getCatatan());
            hp.setKosInfra(hakmilikPermohonan.getKosInfra());
            if (kategoriTanahBaruString != null) {
                hp.setKategoriTanahBaru(KKtgT);
                if (kategoriTanahBaruString.equals("0")) {
                    hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
                } else if (kategoriTanahBaruString.equals("1")) {
                    hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru() + " * bagi setiap 1 ha");
                } else {
                    hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru() + " * bagi setiap 100 mp");
                }
            } else {
                // hp.setKategoriTanahBaru(null);
                hp.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            }
            pembangunanService.simpanHakmilikPermohonan(hp);
        }

        for (int i = 0; i < senaraiKodKecerunanTanah.size(); i++) {
            LOG.info("-------------senaraiKodKecerunanTanah.size---" + senaraiKodKecerunanTanah.size());
            KodKecerunanTanah kodCerunTanah = new KodKecerunanTanah();
            kodCerunTanah = senaraiKodKecerunanTanah.get(i);

            String kod = getContext().getRequest().getParameter(kodCerunTanah.getNama());
            if (kod != null && !kod.equals("")) {
                laporanCerun = new PermohonanLaporanCerun();
                laporanCerun.setIdPermohonan(permohonan);
                laporanCerun.setInfoAudit(infoAudit);
                laporanCerun.setCawangan(permohonan.getCawangan());
                laporanCerun.setKodCerunanTanah(kodKecerunanTanahDAO.findById(kod));
                pembangunanService.simpanPermohonanLaporCerun(laporanCerun);
            }
        }
        BpelServices(stageId, pengguna);
        if (fasaPermohonan != null) {
            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");//NF
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        } else {
            if (permohonanRujukanLuar != null) {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                KodDokumen kodDokumenWarta = new KodDokumen();
                KodRujukan kodRuj = new KodRujukan();
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
        if (permohonanLaporanUlasan != null) {
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        } else {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            pembangunanService.simpanLaporanUlasan(permohonanLaporanUlasan);
        }
    }

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

    public List<ImejLaporan> getTimurLautImejLaporanList() {
        return timurLautImejLaporanList;
    }

    public void setTimurLautImejLaporanList(List<ImejLaporan> timurLautImejLaporanList) {
        this.timurLautImejLaporanList = timurLautImejLaporanList;
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

    public List<ImejLaporan> getBaratLautImejLaporanList() {
        return baratLautImejLaporanList;
    }

    public void setBaratLautImejLaporanList(List<ImejLaporan> baratLautImejLaporanList) {
        this.baratLautImejLaporanList = baratLautImejLaporanList;
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

    public PermohonanLaporanCerun getPermohonanLaporanCerun() {
        return permohonanLaporanCerun;
    }

    public void setPermohonanLaporanCerun(PermohonanLaporanCerun permohonanLaporanCerun) {
        this.permohonanLaporanCerun = permohonanLaporanCerun;
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

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdLaporBangunan() {
        return idLaporBangunan;
    }

    public void setIdLaporBangunan(String idLaporBangunan) {
        this.idLaporBangunan = idLaporBangunan;
    }

    public String getIdLaporCerun() {
        return idLaporCerun;
    }

    public void setIdLaporCerun(String idLaporCerun) {
        this.idLaporCerun = idLaporCerun;
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

    public String getForEdit() {
        return forEdit;
    }

    public void setForEdit(String forEdit) {
        this.forEdit = forEdit;
    }

    public String getSumbanganSaliran() {
        return sumbanganSaliran;
    }

    public void setSumbanganSaliran(String sumbanganSaliran) {
        this.sumbanganSaliran = sumbanganSaliran;
    }

    public String getKodKatgTanah() {
        return kodKatgTanah;
    }

    public void setKodKatgTanah(String kodKatgTanah) {
        this.kodKatgTanah = kodKatgTanah;
    }

    public String getRujukanFail() {
        return rujukanFail;
    }

    public void setRujukanFail(String rujukanFail) {
        this.rujukanFail = rujukanFail;
    }

    //START SYOR 4 SBMS AND TSPSS
    public Resolution editPopup() {
        LOG.debug("editPopup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/editNilaiForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution editNyata() {
        LOG.debug("editNyata");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution editSekatan() {
        LOG.debug("editSekatan");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup() {
        LOG.info("kemaskini mohon plot pelan start.");
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
        rehydrate();
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/editNilaiForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatanSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSyaratNyataSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatanSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
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
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyataSBMSnTSPSS() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
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
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianKodSekatanDanNyataForSBMSnTSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKodSyaratNyataSBMSnTSPSS() {
        LOG.info("--Save Code--");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
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
        rehydrate();
        return new JSP("pembangunan/laporan_tanah/laporanTanahPopupMelakaOnly/carianSyaratNyataForSBMSnSPSS.jsp").addParameter("popup", "true");
    }
    //END ADDING SBMS & TSPSS CODES
}
