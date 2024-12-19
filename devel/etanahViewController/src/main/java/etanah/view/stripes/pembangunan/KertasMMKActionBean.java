/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKeadaanTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.KodSyaratNyata;
import etanah.model.KodSekatanKepentingan;
import etanah.model.LaporanTanahSempadan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.PermohonanLaporanCerun;
import etanah.model.Notis;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.RegService;
import org.apache.commons.lang.WordUtils;

@UrlBinding("/pembangunan/kertas_mmk")
public class KertasMMKActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasMMKActionBean.class);
    @Inject
    BPelService service;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    RegService regService;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    KodLotDAO kodLotDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private HakmilikPermohonan hp;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //add by wani
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List senaraiPlot;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private List<PermohonanKertasKandungan> senaraiKandungan7;
    private List<PermohonanKertasKandungan> senaraiKandungan8;
    private List<PermohonanKertasKandungan> senaraiKandungan9;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanTanamanList;
    private List<PermohonanLaporanCerun> findListlaporCerun;
    private List<PermohonanPlotPelan> listHakmilik;
    private List<PermohonanPlotPelan> listKerajaanAndRizab;
    private ArrayList listKerajaan = new ArrayList();
    private ArrayList listRizab = new ArrayList();
    private boolean btn;
    private String lokasi;
    private String kmno;
    private String pejTanah;
    private String tajuk;
    private String nama;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String taskId;
    private Task task = null;
    private Pengguna pengguna;
    private KodDokumen kd;
    private String kodHakmilikSementara;
    private String kodHakmilikTetap;
    private String kategoriTanahBaru;
    private String jenisPenggunaanTanah;
    private String kodSyaratNyataBaru;
    private String kodSekatanKepentinganBaru;
    private String kodKegunaanTanah;
    private String infra;
    private String pegangan;
    private int rowCount;
    private int rowCount7;
    private int rowCount8;
    private int rowCount9;
    private String idPermohonan;
    private String huraianPentadbir1;
    private String syorPentadbir1;
    private String huraianPtg1;
    private String syorPtg1;
    private List<Integer> tempohTamat;
    private boolean check = true;
    private String noRujukan;
    private Date tarikhSidang;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private BigDecimal kadarpremium;
    private List<PermohonanPlotPelan> listplotpelan;
    private PermohonanPlotPelan mpp;
    private BigDecimal jumluasHakmilik = new BigDecimal(0.00);
    private BigDecimal jumluasTHakmilik = new BigDecimal(0.00);
    private BigDecimal jumluasKerajaan = new BigDecimal(0.00);
    private BigDecimal jumlahPlotYang = new BigDecimal(0.00);
    private BigDecimal baki = new BigDecimal(0.00);
    private int bilplotHakmiliktemp = 0;
    private int bilplotTHakmiliktemp = 0;
    private int bilplotKerajaantemp = 0;
    private int bilplotHakmiliktbl = 0;
    private int bilplotTHakmiliktbl = 0;
    private int bilplotKerajaantbl = 0;
    private String noPlot;
    private PermohonanKertas permohonanKertas;
    private List<PermohonanRujukanLuar> mohonRujLuarList;
    private HakmilikPermohonan hakmilikPermohonan1;
    private List<LaporanTanahSempadan> laporanTanahSempadanList;
    List<LaporanTanah> laporanTanahList2;
    private List<LaporanTanahSempadan> laporanTanahSempadanList2 = new ArrayList<LaporanTanahSempadan>();
    private List<LaporanTanah> laporanTanah2;
    private List<LaporanTanah> laporanTanahKeadaanTanah;
    private List<HakmilikPermohonan> hakmilikbyKeadaanTanah;
    private List<LaporanTanahSempadan> laporanTanahSempadanForU;
    private List<LaporanTanahSempadan> laporanTanahSempadanForT;
    private List<LaporanTanahSempadan> laporanTanahSempadanForB;
    private List<LaporanTanahSempadan> laporanTanahSempadanForS;
    private int senaraiplotsize;
    private String pelantatatur;
    private String kedudukanTanah;
    private String keadaanTanah;
    private String tanaman;
    private String bangunan;
    private LaporanTanah ltt;
    private LaporanTanahSempadan lts;
    private List<PermohonanPlotPelan> listKatgBangunanB01;
    private List<PermohonanPlotPelan> listKatgBangunanByIdPlot;
    private List<PermohonanPlotPelan> listKatgPertanian;
    private List<PermohonanPlotPelan> listKatgPertanianByIdPlot;
    private List<PermohonanPlotPelan> listKatgIndustri;
    private List<PermohonanPlotPelan> listKatgIndustriByIdPlot;
    private List<PermohonanPlotPelan> listallplotpelan;
    private List<PermohonanPlotPelan> senaraiBangunanTerpilih;
    private List<PermohonanPlotPelan> senaraiIndustriTerpilih;
    private List<PermohonanPlotPelan> senaraiPertanianTerpilih;
    private String forEdit;
    private String idPlot;
    private String tempohPegangan;
    private String pegangan0;
    private String janisHakmilikSementara;
    private String jenisHakmilikTetap;
    private String kadarCukai;
    private String kadarPremium;
    private String kadarInfra;
    private String kadarUpah;
    private String kadarDaftar;
    private String kodSyaratNyataMpp;
    private String kodSekatanKepentinganBaruMpp;
    private String ingatan;
    private String syaratNyata;
    private String kodSekatan;
    private String kodSyaratNyata;
    private String sekatan;
    private String kodKatTanah;
    private String idLapor;
    //ArahMataAngin
    private String milikKjaanUtara;
    private String noLotUtara;
    private String gunaUtara;
    private String catatanUtara;
    private String milikKjaanTimur;
    private String noLotTimur;
    private String gunaTimur;
    private String catatanTimur;
    private String milikKjaanSelatan;
    private String noLotSelatan;
    private String gunaSelatan;
    private String catatanSelatan;
    private String milikKjaanBarat;
    private String noLotBarat;
    private String gunaBarat;
    private String catatanBarat;
    private String kodLotUtara;
    private String kodLotTimur;
    private String kodLotBarat;
    private String kodLotSelatan;

    public Resolution kodSekatanPopup() {
        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution kodSyaratNyataPopup() {
        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    @DefaultHandler
    public Resolution viewAndEditPTD() {
        getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.FALSE);
        getContext().getRequest().setAttribute("viewOnly", Boolean.FALSE);

        System.out.println("Urusan : " + permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
            getContext().getRequest().setAttribute("plot", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        }

        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution viewAndEditPTG() {
        getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.FALSE);
        getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewOnly", Boolean.FALSE);

        System.out.println("Urusan : " + permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
            getContext().getRequest().setAttribute("plot", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        }

        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnly() {
        getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.FALSE);
        getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.FALSE);
        getContext().getRequest().setAttribute("viewOnly", Boolean.TRUE);

        System.out.println("Urusan : " + permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
            getContext().getRequest().setAttribute("plot", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        }

        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    //@DefaultHandler
    public Resolution showForm() {
        btn = true;
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (pengguna.getKodCawangan().getDaerah().getKod().equals("00")) {
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }

        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    //Guna utk PTG
    public Resolution showForm2() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (kertasK != null) {
            btn = false;
        }
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        Permohonan ma = permohonanDAO.findById(idPermohonan);
        Notis notis = new Notis();
        if (ma != null) {
            notis = devService.findNotisByIdPermohonan(ma.getPermohonanSebelum().getIdPermohonan());
        } else {
            addSimpleError("Sila masukkan maklumat permohonan asal terlebih dahulu.");
        }

        try {
            if (notis != null) {
                Date date = notis.getTarikhNotis();
                Calendar calNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                Calendar now = Calendar.getInstance();
                Calendar endNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                calNotis.add(Calendar.DATE, 76);
                endNotis.add(Calendar.DATE, 90);
                if (now.after(calNotis) && now.before(endNotis)) {
                    check = true;
                    addSimpleMessage("Masih di dalam tempoh 14 hari sebelum tamat tempoh (3 Bulan). Rayuan boleh diteruskan. Sila lengkapkan maklumat-maklumat untuk penyediaan Kertas Ringkas di Tab Deraf MMK.");
                } else if (now.before(calNotis)) {
                    check = false;
//                    System.out.println("calNotis: " + calNotis.getTime());
                    addSimpleMessage("Tidak di dalam tempoh 14 hari sebelum tamat tempoh (3 Bulan). Rayuan tidak boleh diteruskan. Sila klik butang 'Selesai' untuk menolak rayuan ini.");
                } else if (now.after(endNotis)) {
                    check = false;
//                    System.out.println("enddate: " + endNotis.getTime());
                    addSimpleMessage("Telah tamat tempoh (3 Bulan). Rayuan tidak boleh diteruskan. Sila klik butang 'Selesai' untuk menolak rayuan ini.");
                }
            }
        } catch (Exception e) {
            LOG.info("Exception:" + e);
            e.printStackTrace();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    //guna untuk plot_plan
    public Resolution showForm6() {
        btn = true;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!showForm4"})
    // @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        rowCount = 1;
        rowCount7 = 1;
        rowCount8 = 1;
        rowCount9 = 1;

        pelantatatur = permohonan.getCatatan();

        laporanTanahList2 = new ArrayList<LaporanTanah>();
        laporanTanahList2 = devService.findLaporanTanahByIdPermohonan3(idPermohonan);

        System.out.println("List2 : " + laporanTanahList2.size());
        for (int a = 0; a < laporanTanahList2.size(); a++) {
            laporanTanah = laporanTanahList2.get(a);
            System.out.println("Id Laporan : " + laporanTanah.getIdLaporan());
            laporanTanahSempadanList = devService.findLaporanTanahSempadan(laporanTanah.getIdLaporan());

            laporanTanahSempadanList2.addAll(a, laporanTanahSempadanList);
            System.out.println("------------->" + laporanTanahSempadanList2.size());
        }

        System.out.println("Total ------------->" + laporanTanahSempadanList2.size());
        permohonanKertas = pembangunanServ.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "MMK");

        HakmilikPermohonan hakp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        System.out.println("Kod Urusan ---------->" + permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
            senaraiPlot = permohonan.getSenaraiHakmilik();
        } else {
            senaraiPlot = pembangunanServ.findPermohonanPlotPelanGroupByIdPermohonan(idPermohonan);
        }
        senaraiplotsize = senaraiPlot.size();
        System.out.println("Senarai Plot Pelan : " + senaraiPlot.size());
        hp = permohonan.getSenaraiHakmilik().get(0);

        listPemohon = new ArrayList<Pemohon>();
        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);

        //wani
        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        PermohonanPlotPelan mpp1 = new PermohonanPlotPelan();
        List listLuas = new ArrayList();
        BigDecimal luasAmbilTanahKerajaan = new BigDecimal(0);
        BigDecimal luasAmbilTanahKerajaanTambah = new BigDecimal(0);
        if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
            mpp1 = (PermohonanPlotPelan) devService.findluasPlotPelanByIdMohon(permohonan.getIdPermohonan());
        } else if (permohonan.getKodUrusan().getKod().equals("SBPS")) {
            listLuas = devService.findListLuasPlotPelanByIdMohon(permohonan.getIdPermohonan());

            for (int a = 0; a < listLuas.size(); a++) {
                mpp1 = (PermohonanPlotPelan) listLuas.get(a);
                luasAmbilTanahKerajaan = mpp1.getLuas();
                luasAmbilTanahKerajaanTambah = luasAmbilTanahKerajaanTambah.add(luasAmbilTanahKerajaan);
            }
        }

        tempohTamat = new ArrayList<Integer>();
        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hakp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hakp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + " seluas " + mpp1.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                } else {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                }
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + mpp1.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    } else {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + hakmilik.getLuas() + hakmilik.getKodUnitLuas().getNama() + ", ";
                    }
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + mpp1.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    } else {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    }
                }
            }

            // calculate difference between dates
            int yrs = 0;
            LOG.info("-------hakmilik.getTarikhLuput()-------:" + hakmilik.getTarikhLuput());
            if (hakmilik.getTarikhLuput() != null) {
                Date date = hakmilik.getTarikhLuput();
                Calendar cal = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                Calendar now = new GregorianCalendar();
                yrs = cal.get(Calendar.YEAR) - now.get(Calendar.YEAR);
                if ((now.get(Calendar.MONTH) > cal.get(Calendar.MONTH))
                        || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) < now
                        .get(Calendar.DAY_OF_MONTH))) {
                    yrs--;
                }
                tempohTamat.add(new Integer(yrs));
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("SBPS")) {
            if (listLuas.size() != 0) {
                lokasi = lokasi + " dan mengusahakan tanah kerajaan seluas " + luasAmbilTanahKerajaanTambah + " " + mpp1.getKodUnitLuas().getNama();
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("RPS")) {
            lokasi = lokasi + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama());

        } else {
            lokasi = lokasi + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " di bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();
        }




        LOG.info("-----------rehydrate---------");
        LOG.info("-----------listPemohon-Size--------:" + listPemohon.size());

        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);
            LOG.info("-----------pm---------:" + pm);
            if (j == 0) {
                nama = pm.getPihak().getNama();
                LOG.info("-----------nama---------:" + nama);
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
                LOG.info("-----------nama2---------:" + nama);
            }
        }

        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("-------------stageId--" + stageId);
        }

        mohonRujLuarList = pembangunanServ.findUlasanJabatanTek1(idPermohonan);
        hakmilikPermohonan1 = pembangunanServ.findByIdPermohonan(idPermohonan);
        laporanTanah2 = pembangunanServ.findLaporanTanahByIdPermohonan3(idPermohonan);

//         testing
//        stageId = "sediaderafmmk";
        LOG.info("-----------rehydrate---------");
        PermohonanKertas kertasP = new PermohonanKertas();
        permohonan.setSenaraiKertas(devService.findMohonanKertasByIdPermohonan(idPermohonan));
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
            senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "MMK");
            System.out.println("senaraiKertas: " + senaraiKertas.size());
            if (senaraiKertas.size() > 0) {
                kertasP = senaraiKertas.get(0);
            } else {
                kertasP = null;
            }
            if (kertasP != null) {
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                }

                if (permohonan.getKodUrusan().getKod().equals("RPS")) {
                    noRujukan = permohonan.getPermohonanSebelum().getIdPermohonan();
                } else {
                    noRujukan = kertasP.getNomborRujukan();
                    //kmno = kertasP.getTajuk();
                }

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                    kertasK = kertasP.getSenaraiKandungan().get(j);
                    if (kertasK.getBil() == 1) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        pembangunanDicadang = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                        huraianPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7 && kertasK.getSubtajuk().equals("7.1")) {
                        syorPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8 && kertasK.getSubtajuk().equals("8.1")) {
                        huraianPtg1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
                        syorPtg1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 11) {
                        tajuk = kertasK.getKandungan();
                    }
                }

                senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan9 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                senaraiKandungan7 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 7);
                senaraiKandungan8 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 8);
                senaraiKandungan9 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 9);

                rowCount = senaraiKandungan.size();
                rowCount7 = senaraiKandungan7.size();
                rowCount8 = senaraiKandungan8.size();
                rowCount9 = senaraiKandungan9.size();
                if (rowCount == 0) {
                    rowCount = 1;
                }
                if (rowCount7 == 0) {
                    rowCount7 = 1;
                }
                if (rowCount8 == 0) {
                    rowCount8 = 1;
                }
                if (rowCount9 == 0) {
                    rowCount9 = 1;
                }
            }
        }

        if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null) {
            if (hakmilikPermohonanList.size() > 5) {
                if (permohonan.getKodUrusan().getKod().equals("TSPTD") || permohonan.getKodUrusan().getKod().equals("TSPTG") || permohonan.getKodUrusan().getKod().equals("TSMMK")) {
                    tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + " bagi " + hakmilik.getSyaratNyata().getSyarat() + " kepada " + hakmilikPermohonan1.getSyaratNyataBaru().getSyarat() + hakmilikPermohonanList.size() + " hakmilik seperti di jadual butir-butir hakmilik Kanun Tanah Negara 1965.";
                } else {
                    tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + hakmilikPermohonanList.size() + " hakmilik seperti di jadual butir-butir hakmilik Kanun Tanah Negara 1965.";
                }
                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Majlis Mesyuarat kerajaan Negeri Sembilan Darul Khusus bagi permohonan daripada" + UpperCaseWords(nama) + " untuk " + permohonan.getKodUrusan().getNama().replaceAll("-", "") + " " + " bagi " + hakmilikPermohonanList.size() + " hakmilik seperti di jadual butir-butir hakmilik Kanun Tanah Negara 1965.";
            } else {
                if (permohonan.getKodUrusan().getKod().equals("TSPTD") || permohonan.getKodUrusan().getKod().equals("TSPTG") || permohonan.getKodUrusan().getKod().equals("TSMMK")) {
                    tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + " dari " + hakmilik.getSyaratNyata().getSyarat().trim() + " kepada " + hakmilikPermohonan1.getSyaratNyataBaru().getSyarat().trim() + " di " + lokasi + ".";
                } else {
                    tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + " di " + lokasi + ".";
                }
                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus bagi permohonan daripada " + UpperCaseWords(nama) + " bagi " + permohonan.getKodUrusan().getNama() + " untuk " + lokasi + ".";
            }
            tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
        }

        if (pengguna.getKodCawangan().getDaerah() != null) {
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("01")) {
                pejTanah = "Jelebu";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("02")) {
                pejTanah = "Kuala Pilah";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("03")) {
                pejTanah = "Port Dickson";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("04")) {
                pejTanah = "Rembau";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("05")) {
                pejTanah = "Seremban";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("06")) {
                pejTanah = "Tampin";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("07")) {
                pejTanah = "Jempol";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("08")) {
                pejTanah = "Kecil Gemas";
            }
        }

        ////HAKMILIK
        listHakmilik = new ArrayList<PermohonanPlotPelan>();
        listKerajaan = new ArrayList();
        listRizab = new ArrayList();
        LOG.info("plot pelan list.");
        listplotpelan = new ArrayList<PermohonanPlotPelan>();
        listKerajaanAndRizab = new ArrayList<PermohonanPlotPelan>();
//            listplotpelan = permohonanPlotPelanDAO.findByEqualCriterias(tname, model, null);
        listplotpelan = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
        listKerajaanAndRizab = pembangunanServ.findPermohonanPlotPelanForRizabAndKerajaanByIdPermohonan(idPermohonan);
        // Added code for PPT
        if (listplotpelan.isEmpty() && permohonan.getKodUrusan().getKod().equals("PPT")) {
            if (permohonan.getPermohonanSebelum() != null) {
                listplotpelan = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            }
        }

        LOG.info("---------listplotpelan---------list:" + listplotpelan);
        if (!(listplotpelan.isEmpty())) {

            for (int a = 0; a < listplotpelan.size(); a++) {
                mpp = new PermohonanPlotPelan();
                mpp = listplotpelan.get(a);

                if (mpp.getPemilikan().getKod() == 'H') {
                    LOG.info("plot pelan hakmilik list.");
                    bilplotHakmiliktbl = mpp.getBilanganPlot();
//                           System.out.println("plot hakmilik db = " + bilplotHakmiliktbl);
//                           System.out.println("plot hakmilik temp = " + bilplotHakmiliktemp);
                    BigDecimal luasH = mpp.getLuas();
                    luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                    System.out.println("****** luasH ******* = " + luasH);
                    jumluasHakmilik = jumluasHakmilik.add(luasH);
                    bilplotHakmiliktemp = bilplotHakmiliktemp + bilplotHakmiliktbl;
                    System.out.println("plot hakmilik = " + bilplotHakmiliktemp);
                    listHakmilik.add(mpp);

                    //Bangunan
                    if (mpp.getKategoriTanah().getKod().equals("2")) {
                        listKatgBangunanB01 = devService.findSenaraiPermohonanPlotPelanByOneKatgKatgBangunan(idPermohonan);
                        senaraiBangunanTerpilih = new ArrayList<PermohonanPlotPelan>();

                        List<KodKegunaanTanah> listguna = new ArrayList<KodKegunaanTanah>();
                        for (PermohonanPlotPelan mpp : listKatgBangunanB01) {
                            if (listguna.size() > 0) {
                                Boolean found = false;
                                for (KodKegunaanTanah guna : listguna) {
                                    if (guna.equals(mpp.getKegunaanTanah())) {
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    listguna.add(mpp.getKegunaanTanah());
                                    senaraiBangunanTerpilih.add(mpp);
                                }

                            } else {
                                listguna.add(mpp.getKegunaanTanah());
                                senaraiBangunanTerpilih.add(mpp);
                            }
                        }

                        listKatgBangunanB01.clear();
                        listKatgBangunanB01.addAll(senaraiBangunanTerpilih);
                    } else if (mpp.getKategoriTanah().getKod().equals("1")) { //pertanian
                        listKatgPertanian = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgPertanian(idPermohonan);
                        senaraiPertanianTerpilih = new ArrayList<PermohonanPlotPelan>();

                        List<KodKegunaanTanah> listguna = new ArrayList<KodKegunaanTanah>();
                        for (PermohonanPlotPelan mpp : listKatgPertanian) {
                            if (listguna.size() > 0) {
                                Boolean found = false;
                                for (KodKegunaanTanah guna : listguna) {
                                    if (guna.equals(mpp.getKegunaanTanah())) {
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    listguna.add(mpp.getKegunaanTanah());
                                    senaraiPertanianTerpilih.add(mpp);
                                }

                            } else {
                                listguna.add(mpp.getKegunaanTanah());
                                senaraiPertanianTerpilih.add(mpp);
                            }
                        }

                        listKatgPertanian.clear();
                        listKatgPertanian.addAll(senaraiPertanianTerpilih);
                    } else if (mpp.getKategoriTanah().getKod().equals("3")) { //industri
                        listKatgIndustri = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgIndustri(idPermohonan);
                        senaraiIndustriTerpilih = new ArrayList<PermohonanPlotPelan>();

                        List<KodKegunaanTanah> listguna = new ArrayList<KodKegunaanTanah>();
                        for (PermohonanPlotPelan mpp : listKatgIndustri) {
                            if (listguna.size() > 0) {
                                Boolean found = false;
                                for (KodKegunaanTanah guna : listguna) {
                                    if (guna.equals(mpp.getKegunaanTanah())) {
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    listguna.add(mpp.getKegunaanTanah());
                                    senaraiIndustriTerpilih.add(mpp);
                                }

                            } else {
                                listguna.add(mpp.getKegunaanTanah());
                                senaraiIndustriTerpilih.add(mpp);
                            }
                        }

                        listKatgIndustri.clear();
                        listKatgIndustri.addAll(senaraiIndustriTerpilih);
                    }

                } else if (mpp.getPemilikan().getKod() == 'R') {
                    LOG.info("plot pelan rizab list.");
                    bilplotTHakmiliktbl = mpp.getBilanganPlot();
                    BigDecimal luasTH = mpp.getLuas();
                    jumluasTHakmilik = jumluasTHakmilik.add(luasTH);
                    bilplotTHakmiliktemp = bilplotTHakmiliktemp + bilplotTHakmiliktbl;
                    listRizab.add(mpp);
                } else if (mpp.getPemilikan().getKod() == 'K') {
                    LOG.info("plot pelan agensi kerajaan list.");
                    bilplotKerajaantbl = mpp.getBilanganPlot();
                    BigDecimal luasK = mpp.getLuas();
                    jumluasKerajaan = jumluasKerajaan.add(luasK);
                    bilplotKerajaantemp = bilplotKerajaantemp + bilplotKerajaantbl;
                    listKerajaan.add(mpp);
                }
            }
        }
        ////

        findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();
        permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        findListlaporCerun = devService.findLaporCerunListByIdPermohonan(idPermohonan);
        permohonanLaporanTanamanList = devService.getLaporTnmnByIdLaporan(idPermohonan);
        permohonanLaporanBangunanList = devService.getLaporBngnByIdLaporan(idPermohonan);

        //NO 5 - KEADAAN DAN KEDUDUKAN TANAH
        laporanTanahKeadaanTanah = new ArrayList<LaporanTanah>();
        laporanTanahSempadanForU = new ArrayList<LaporanTanahSempadan>();
        laporanTanahSempadanForT = new ArrayList<LaporanTanahSempadan>();
        laporanTanahSempadanForS = new ArrayList<LaporanTanahSempadan>();
        laporanTanahSempadanForB = new ArrayList<LaporanTanahSempadan>();

        laporanTanahKeadaanTanah = devService.findLaporanTanahByIdPermohonan3(idPermohonan);
        idLapor = getContext().getRequest().getParameter("idLapor");
        if (idLapor != null) {

            ltt = laporanTanahDAO.findById(Long.valueOf(idLapor));
            if (ltt.getHakmilikPermohonan().getLokasi() != null) {
                kedudukanTanah = ltt.getHakmilikPermohonan().getLokasi();
            }
            if (ltt.getKodKeadaanTanah() != null) {
                keadaanTanah = ltt.getKodKeadaanTanah().getKod();
            }
            if (ltt.getUsaha() != null) {
                tanaman = ltt.getUsaha().toString();
            }
            if (ltt.getAdaBangunan() != null) {
                bangunan = ltt.getAdaBangunan().toString();
            }

            //utara
            laporanTanahSempadanForU = devService.findLaporTanahSmpdnByIdLaporNJSmpdn(Long.parseLong(idLapor), "U");
            for (LaporanTanahSempadan laporU : laporanTanahSempadanForU) {
                if (laporU.getMilikKerajaan() != null) {
                    milikKjaanUtara = laporU.getMilikKerajaan();
                }
                if (laporU.getKodLot() != null) {
                    kodLotUtara = laporU.getKodLot().getKod();
                }
                if (laporU.getNoLot() != null) {
                    noLotUtara = laporU.getNoLot();
                }
                if (laporU.getGuna() != null) {
                    gunaUtara = laporU.getGuna();
                }
                if (laporU.getCatatan() != null) {
                    catatanUtara = laporU.getCatatan();
                }
            }


//            for (int i = 0; i < laporanTanahSempadanForU.size(); i++) {
//
//                LaporanTanahSempadan laporU = new LaporanTanahSempadan();
//                laporU = (LaporanTanahSempadan) laporanTanahSempadanForU.get(i);
//                
//                if(laporU.getMilikKerajaan() != null){milikKjaanUtara = laporU.getMilikKerajaan();}
//                if(laporU.getNoLot() != null){noLotUtara = laporU.getNoLot();}
//                if(laporU.getGuna() != null){gunaUtara = laporU.getGuna();}
//                if(laporU.getCatatan() != null){catatanUtara = laporU.getCatatan();}
//
//            }

            //timur
            laporanTanahSempadanForT = devService.findLaporTanahSmpdnByIdLaporNJSmpdn(Long.parseLong(idLapor), "T");
            for (LaporanTanahSempadan laporT : laporanTanahSempadanForT) {
                if (laporT.getMilikKerajaan() != null) {
                    milikKjaanTimur = laporT.getMilikKerajaan();
                }
                if (laporT.getKodLot() != null) {
                    kodLotTimur = laporT.getKodLot().getKod();
                }
                if (laporT.getNoLot() != null) {
                    noLotTimur = laporT.getNoLot();
                }
                if (laporT.getGuna() != null) {
                    gunaTimur = laporT.getGuna();
                }
                if (laporT.getCatatan() != null) {
                    catatanTimur = laporT.getCatatan();
                }
            }

            //selatan
            laporanTanahSempadanForS = devService.findLaporTanahSmpdnByIdLaporNJSmpdn(Long.parseLong(idLapor), "S");
            for (LaporanTanahSempadan laporS : laporanTanahSempadanForS) {
                if (laporS.getMilikKerajaan() != null) {
                    milikKjaanSelatan = laporS.getMilikKerajaan();
                }
                if (laporS.getKodLot() != null) {
                    kodLotSelatan = laporS.getKodLot().getKod();
                }
                if (laporS.getNoLot() != null) {
                    noLotSelatan = laporS.getNoLot();
                }
                if (laporS.getGuna() != null) {
                    gunaSelatan = laporS.getGuna();
                }
                if (laporS.getCatatan() != null) {
                    catatanSelatan = laporS.getCatatan();
                }
            }

            //barat
            laporanTanahSempadanForB = devService.findLaporTanahSmpdnByIdLaporNJSmpdn(Long.parseLong(idLapor), "B");
            for (LaporanTanahSempadan laporB : laporanTanahSempadanForB) {
                if (laporB.getMilikKerajaan() != null) {
                    milikKjaanBarat = laporB.getMilikKerajaan();
                }
                if (laporB.getKodLot() != null) {
                    kodLotBarat = laporB.getKodLot().getKod();
                }
                if (laporB.getNoLot() != null) {
                    noLotBarat = laporB.getNoLot();
                }
                if (laporB.getGuna() != null) {
                    gunaBarat = laporB.getGuna();
                }
                if (laporB.getCatatan() != null) {
                    catatanBarat = laporB.getCatatan();
                }
            }

        }
        //No.7
        idPlot = getContext().getRequest().getParameter("idPlot");
        if (idPlot != null) {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));

            if (mpp.getTempohPegangan() != null) {
                tempohPegangan = mpp.getTempohPegangan().toString();
            } else {
                tempohPegangan = "0";
            }

            if (mpp.getPegangan() != null) {
                pegangan0 = mpp.getPegangan().toString();
            }

            if (mpp.getKodHakmilikSementara() != null) {
                janisHakmilikSementara = mpp.getKodHakmilikSementara().getKod();
            }

            if (mpp.getKodHakmilikTetap() != null) {
                jenisHakmilikTetap = mpp.getKodHakmilikTetap().getKod();
            }

            if (mpp.getKeteranganCukaiBaru() != null) {
                kadarCukai = mpp.getKeteranganCukaiBaru();
            }

            if (mpp.getKeteranganKadarPremium() != null) {
                kadarPremium = mpp.getKeteranganKadarPremium();
            }

            if (mpp.getKosInfra() != null) {
                kadarInfra = mpp.getKosInfra();
            }

            if (mpp.getKeteranganKadarUkur() != null) {
                kadarUpah = mpp.getKeteranganKadarUkur();
            }

            if (mpp.getKeteranganKadarDaftar() != null) {
                kadarDaftar = mpp.getKeteranganKadarDaftar();
            }

            if (mpp.getKodSyaratNyata() != null) {
                kodSyaratNyataMpp = mpp.getKodSyaratNyata().getSyarat();
            }

            if (mpp.getKodSekatanKepentingan() != null) {
                kodSekatanKepentinganBaruMpp = mpp.getKodSekatanKepentingan().getSekatan();
            }

            if (mpp.getPeringatan() != null) {
                ingatan = mpp.getPeringatan();
            }

        } else {
            LOG.debug("---NO DATA---");
        }
    }

    // Added by NageswaraRao
    public Resolution kertasMMKBaru() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMK");

        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("--------taskId----------:" + taskId);
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("--------stageId----------:" + stageId);
        }

        //Testing Purpose
//        stageId = "sediakertasmmktangguh1";
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        ArrayList listBill = new ArrayList();
        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if (noRujukan == null || noRujukan.equals("")) {
            noRujukan = " ";
        }
        /*if (kmno == null || kmno.equals("")) {
         kmno = " ";
         }*/
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (pembangunanDicadang == null || pembangunanDicadang.equals("")) {
            pembangunanDicadang = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listUlasan.add(pembangunanDicadang);
        listUlasan.add(tajuk);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
        listBill.add(new Integer(4));
        listBill.add(new Integer(11));

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        for (int i = 0; i < listUlasan.size(); i++) {
            String ulasan = (String) listUlasan.get(i);
            String subtajuk = (String) listSubtajuk.get(i);
            Integer bil = (Integer) listBill.get(i);
//                    PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
            kertasK = new PermohonanKertasKandungan();

            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            //permohonanKertas.setTajuk(kmno);
            permohonanKertas.setNomborRujukan(noRujukan);
            try {
                if (tarikhMesyuarat != null && !tarikhMesyuarat.equals("")) {
                    permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            permohonanKertas.setKodDokumen(kd);
            kertasK.setKertas(permohonanKertas);
            kertasK.setBil(bil);
            kertasK.setInfoAudit(infoAudit);
            kertasK.setKandungan(ulasan);
            kertasK.setSubtajuk(subtajuk);
            kertasK.setCawangan(pengguna.getKodCawangan());
            devService.simpanPermohonanKertas(permohonanKertas);
            devService.simpanPermohonanKertasKandungan(kertasK);
        }

        List<PermohonanKertasKandungan> senaraiHuraian = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
//        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
//        for (int i = 1; i <= kira; i++) {
        for (int i = 0; i < senaraiKandungan.size(); i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiHuraian.size() != 0 && i <= senaraiHuraian.size()) {
                Long id = senaraiHuraian.get(i).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(6);
//            String kandungan = getContext().getRequest().getParameter("huraianPentadbir" + i);
            String kandungan = senaraiKandungan.get(i).getKandungan();
            System.out.println("kandungan huraian: " + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + (i + 1));
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        List<PermohonanKertasKandungan> senaraiSyor = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
//        int kira7 = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
//        for (int i = 1; i <= kira7; i++) {
        for (int i = 0; i < senaraiKandungan7.size(); i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiSyor.size() != 0 && i <= senaraiSyor.size()) {
                Long id = senaraiSyor.get(i).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(7);
//            String kandungan = getContext().getRequest().getParameter("syorPentadbir" + i);
            String kandungan = senaraiKandungan7.get(i).getKandungan();
            System.out.println("kandungan syor: " + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("7." + (i + 1));
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        int kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));
        for (int i = 1; i <= kira8; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan8.size() != 0 && i <= senaraiKandungan8.size()) {
                Long id = senaraiKandungan8.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(8);
            String kandungan = getContext().getRequest().getParameter("huraianPtg" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("8." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan9 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        int kira9 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
        for (int i = 1; i <= kira9; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan9.size() != 0 && i <= senaraiKandungan9.size()) {
                Long id = senaraiKandungan9.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(9);
            String kandungan = getContext().getRequest().getParameter("syorPtg" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("9." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

//      for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//           kodHakmilikSementara = getContext().getRequest().getParameter("kodHakmilikSementara" + i);
//           kodHakmilikTetap = getContext().getRequest().getParameter("kodHakmilikTetap" + i);
//           kategoriTanahBaru = getContext().getRequest().getParameter("kategoriTanahBaru" + i);
//           jenisPenggunaanTanah = getContext().getRequest().getParameter("jenisPenggunaanTanah" + i);
//           kodSyaratNyataBaru = getContext().getRequest().getParameter("kodSyaratNyataBaru" + i);
//           kodSekatanKepentinganBaru = getContext().getRequest().getParameter("kodSekatanKepentinganBaru" + i);
//
//           HakmilikPermohonan hp = new HakmilikPermohonan();
//           hp = hakmilikPermohonanList.get(i);
//           if (hp != null) {
//
//               if (kodHakmilikSementara != null && !kodHakmilikSementara.equals("")) {
//                   KodHakmilik hakmilikSementara = kodHakmilikDAO.findById(kodHakmilikSementara);
//                   hp.setKodHakmilikSementara(hakmilikSementara);
//               } else {
//                   hp.setKodHakmilikSementara(null);
//               }
//               if (kodHakmilikTetap != null && !kodHakmilikTetap.equals("")) {
//                   KodHakmilik hakmilikTetap = kodHakmilikDAO.findById(kodHakmilikTetap);
//                   hp.setKodHakmilikTetap(hakmilikTetap);
//               } else {
//                   hp.setKodHakmilikTetap(null);
//               }
//               if (kategoriTanahBaru != null && !kategoriTanahBaru.equals("")) {
//                   KodKategoriTanah kategoriTanah = kodKategoriTanahDAO.findById(kategoriTanahBaru);
//                   hp.setKategoriTanahBaru(kategoriTanah);
//               } else {
//                   hp.setKategoriTanahBaru(null);
//               }
//               if (jenisPenggunaanTanah != null && !jenisPenggunaanTanah.equals("")) {
//                   KodKategoriTanah penggunaanTanah = kodKategoriTanahDAO.findById(jenisPenggunaanTanah);
//                   hp.setJenisPenggunaanTanah(penggunaanTanah);
//               } else {
//                   hp.setJenisPenggunaanTanah(null);
//               }
//               if (kodSyaratNyataBaru != null && !kodSyaratNyataBaru.equals("")) {
//                   KodSyaratNyata syaratNyataBaru = kodSyaratNyataDAO.findById(kodSyaratNyataBaru);
//                   hp.setSyaratNyataBaru(syaratNyataBaru);
//               } else {
//                   hp.setSyaratNyataBaru(null);
//               }
//               if (kodSekatanKepentinganBaru != null && !kodSekatanKepentinganBaru.equals("")) {
//                   KodSekatanKepentingan sekatanKepentinganBaru = kodSekatanKepentinganDAO.findById(kodSekatanKepentinganBaru);
//                   hp.setSekatanKepentinganBaru(sekatanKepentinganBaru);
//               } else {
//                   hp.setSekatanKepentinganBaru(null);
//               }
//
//               hp.setPermohonan(permohonan);
//               hp.setInfoAudit(infoAudit);
//               devService.simpanHakmilikPermohonan(hp);
//           }
//       }
//            rehydrate();
        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        senaraiKandungan9 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        LOG.info("-------simpan--------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMK");

        /*String tarikhSidangS = getContext().getRequest().getParameter("permohonanKertas.tarikhSidang");
         if (tarikhSidangS != null) {
         try {
         tarikhSidang = new SimpleDateFormat("ddMMyyyyhhmmss").parse(tarikhSidangS);
         } catch (ParseException ex) {
         java.util.logging.Logger.getLogger(KertasMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
         }
         }*/
        //kmno = getContext().getRequest().getParameter("permohonanKertas.kmno");
        //System.out.println("KM no : "+kmno);
        PermohonanKertas permohonanKertas = new PermohonanKertas();
        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("--------taskId----------:" + taskId);
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("--------stageId----------:" + stageId);
        }

        ArrayList listBill = new ArrayList();
        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (pembangunanDicadang == null || pembangunanDicadang.equals("")) {
            pembangunanDicadang = "TIADA DATA.";
        }
        if (noRujukan == null || noRujukan.equals("")) {
            noRujukan = " ";
        }
        /*if (kmno == null || kmno.equals("")) {
         kmno = " ";
         }*/
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listUlasan.add(pembangunanDicadang);
        listUlasan.add(tajuk);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
        listBill.add(new Integer(4));
        listBill.add(new Integer(11));

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(pembangunanDicadang);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(tajuk);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("Tiada");
                    if (pengguna.getKodCawangan().getKod().equals("00")) {
                        permohonanKertas.setNomborRujukan(noRujukan);
                    }

                    /*try {
                     if (tarikhSidang != null && !tarikhSidang.equals("")) {
                     permohonanKertas.setTarikhSidang(tarikhSidang);
                     }
                     } catch (Exception e) {
                     e.printStackTrace();
                     }*/
                    permohonanKertas.setKodDokumen(kd);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {
            System.out.println("ELSE--------------->");
            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bill = (Integer) listBill.get(i);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("Tiada");
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    permohonanKertas.setNomborRujukan(noRujukan);
                }
                //permohonanKertas.setNomborRujukan(noRujukan);
                /*try {
                 if (tarikhSidang != null && !tarikhSidang.equals("")) {
                 permohonanKertas.setTarikhSidang(tarikhSidang);
                 }
                 } catch (Exception e) {
                 e.printStackTrace();
                 }*/
                permohonanKertas.setKodDokumen(kd);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bill);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setSubtajuk(subtajuk);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }

        senaraiKandungan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        LOG.info("-----------count------------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan.size() != 0 && i <= senaraiKandungan.size()) {
                Long id = senaraiKandungan.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(6);
            String kandungan = getContext().getRequest().getParameter("huraianPentadbir" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
        int kira7 = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
        for (int i = 1; i <= kira7; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan7.size() != 0 && i <= senaraiKandungan7.size()) {
                Long id = senaraiKandungan7.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(7);
            String kandungan = getContext().getRequest().getParameter("syorPentadbir" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("7." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
            String strCount8 = getContext().getRequest().getParameter("rowCount8");
            LOG.info("---------StrCount8------------:" + strCount8);
            if (strCount8 != null) {
                int kira8 = Integer.parseInt(strCount8);
                for (int i = 1; i <= kira8; i++) {
                    InfoAudit iaP = new InfoAudit();
                    PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    if (senaraiKandungan8.size() != 0 && i <= senaraiKandungan8.size()) {
                        Long id = senaraiKandungan8.get(i - 1).getIdKandungan();
                        if (id != null) {
                            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                            iaP = permohonanKertasKandungan1.getInfoAudit();
                            iaP.setTarikhKemaskini(new Date());
                            iaP.setDiKemaskiniOleh(pengguna);
                        }
                    } else {
                        permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                        iaP.setTarikhMasuk(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                    permohonanKertasKandungan1.setKertas(permohonanKertas);
                    permohonanKertasKandungan1.setBil(8);
                    String kandungan = getContext().getRequest().getParameter("huraianPtg" + i);
                    if (kandungan == null || kandungan.equals("")) {
                        kandungan = "TIADA DATA.";
                    }
                    permohonanKertasKandungan1.setKandungan(kandungan);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("8." + i);
                    permohonanKertasKandungan1.setInfoAudit(iaP);
                    devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                }
            }

            senaraiKandungan9 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
            String strCount9 = getContext().getRequest().getParameter("rowCount9");
            LOG.info("---------StrCount9------------:" + strCount9);
            if (strCount9 != null) {
                int kira9 = Integer.parseInt(strCount9);
                //        int kira9 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
                for (int i = 1; i <= kira9; i++) {
                    InfoAudit iaP = new InfoAudit();
                    PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    if (senaraiKandungan9.size() != 0 && i <= senaraiKandungan9.size()) {
                        Long id = senaraiKandungan9.get(i - 1).getIdKandungan();
                        if (id != null) {
                            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                            iaP = permohonanKertasKandungan1.getInfoAudit();
                            iaP.setTarikhKemaskini(new Date());
                            iaP.setDiKemaskiniOleh(pengguna);
                        }
                    } else {
                        permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                        iaP.setTarikhMasuk(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                    permohonanKertasKandungan1.setKertas(permohonanKertas);
                    permohonanKertasKandungan1.setBil(9);
                    String kandungan = getContext().getRequest().getParameter("syorPtg" + i);
                    if (kandungan == null || kandungan.equals("")) {
                        kandungan = "TIADA DATA.";
                    }
                    permohonanKertasKandungan1.setKandungan(kandungan);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("9." + i);
                    permohonanKertasKandungan1.setInfoAudit(iaP);
                    devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                }
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) { //update mohon_plot_pelan
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                kodHakmilikSementara = getContext().getRequest().getParameter("kodHakmilikSementara" + i);
                kodHakmilikTetap = getContext().getRequest().getParameter("kodHakmilikTetap" + i);
                kategoriTanahBaru = getContext().getRequest().getParameter("kategoriTanahBaru" + i);
//           jenisPenggunaanTanah = getContext().getRequest().getParameter("jenisPenggunaanTanah" + i);
                kodSyaratNyataBaru = getContext().getRequest().getParameter("kodSyaratNyataBaru" + i);
                kodSekatanKepentinganBaru = getContext().getRequest().getParameter("kodSekatanKepentinganBaru" + i);
                kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);

                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp = hakmilikPermohonanList.get(i);
                if (hp != null) {
//               LOG.debug("------i value--------:" + i);
//               LOG.debug("------kodHakmilikSementara--------:" + kodHakmilikSementara);
//               LOG.debug("------kodHakmilikTetap--------:" + kodHakmilikTetap);
//               LOG.debug("------kategoriTanahBaru--------:" + kategoriTanahBaru);
//               LOG.debug("------jenisPenggunaanTanah--------:" + jenisPenggunaanTanah);
//               LOG.debug("------kodSyaratNyataBaru--------:" + kodSyaratNyataBaru);
//               LOG.debug("------kodSekatanKepentinganBaru--------:" + kodSekatanKepentinganBaru);

                    if (kodHakmilikSementara != null && !kodHakmilikSementara.equals("")) {
                        KodHakmilik hakmilikSementara = kodHakmilikDAO.findById(kodHakmilikSementara);
//                        LOG.debug("------hakmilikSementara--------:"+hakmilikSementara);
                        hp.setKodHakmilikSementara(hakmilikSementara);
                    } else {
                        hp.setKodHakmilikSementara(null);
                    }
                    if (kodHakmilikTetap != null && !kodHakmilikTetap.equals("")) {
                        KodHakmilik hakmilikTetap = kodHakmilikDAO.findById(kodHakmilikTetap);
//                        LOG.debug("------hakmilikTetap--------:"+hakmilikTetap);
                        hp.setKodHakmilikTetap(hakmilikTetap);
                    } else {
                        hp.setKodHakmilikTetap(null);
                    }
                    if (kategoriTanahBaru != null && !kategoriTanahBaru.equals("")) {
                        KodKategoriTanah kategoriTanah = kodKategoriTanahDAO.findById(kategoriTanahBaru);
//                        LOG.debug("------kategoriTanah--------:"+kategoriTanah);
                        hp.setKategoriTanahBaru(kategoriTanah);
                    } else {
                        hp.setKategoriTanahBaru(null);
                    }
//               if (jenisPenggunaanTanah != null && !jenisPenggunaanTanah.equals("")) {
//                   KodKategoriTanah penggunaanTanah = kodKategoriTanahDAO.findById(jenisPenggunaanTanah);
////                        LOG.debug("$$$$$$$------penggunaanTanah---------:"+penggunaanTanah);
//                   hp.setJenisPenggunaanTanah(penggunaanTanah);
//               } else {
//                   hp.setJenisPenggunaanTanah(null);
//               }
                    if (kodSyaratNyataBaru != null && !kodSyaratNyataBaru.equals("")) {
                        KodSyaratNyata syaratNyataBaru = kodSyaratNyataDAO.findById(kodSyaratNyataBaru);
//                        LOG.debug("$$$$$$$------syaratNyataBaru---------:"+syaratNyataBaru);
                        hp.setSyaratNyataBaru(syaratNyataBaru);
                    } else {
                        hp.setSyaratNyataBaru(null);
                    }
                    if (kodSekatanKepentinganBaru != null && !kodSekatanKepentinganBaru.equals("")) {
                        KodSekatanKepentingan sekatanKepentinganBaru = kodSekatanKepentinganDAO.findById(kodSekatanKepentinganBaru);
//                        LOG.debug("$$$$$$$------sekatanKepentinganBaru---------:"+sekatanKepentinganBaru);
                        hp.setSekatanKepentinganBaru(sekatanKepentinganBaru);
                    } else {
                        hp.setSekatanKepentinganBaru(null);
                    }

                    if (kodKegunaanTanah != null && !kodKegunaanTanah.equals("")) {
                        KodKegunaanTanah kegunaanTanah = kodKegunaanTanahDAO.findById(kodKegunaanTanah);
                        LOG.debug("$$$$$$$------kegunaanTanah---------:" + kegunaanTanah);
                        hp.setKodKegunaanTanah(kegunaanTanah);
                    } else {
                        hp.setKodKegunaanTanah(null);
                    }

                    hp.setPermohonan(permohonan);
                    hp.setInfoAudit(infoAudit);
                    devService.simpanHakmilikPermohonan(hp);
                }
            }
        }

//        else {
//            for (int i = 0; i < senaraiPlot.size(); i++) {
//                kodHakmilikSementara = getContext().getRequest().getParameter("kodHakmilikSementara" + i);
//                kodHakmilikTetap = getContext().getRequest().getParameter("kodHakmilikTetap" + i);
//                kategoriTanahBaru = getContext().getRequest().getParameter("kategoriTanahBaru" + i);
//                kodSyaratNyataBaru = getContext().getRequest().getParameter("kodSyaratNyataBaru" + i);
//                kodSekatanKepentinganBaru = getContext().getRequest().getParameter("kodSekatanKepentinganBaru" + i);
//                kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);
//                infra = getContext().getRequest().getParameter("kosInfra" + i);
//                pegangan = getContext().getRequest().getParameter("pegangan" + i);
//
////                char peganganc = pegangan.charAt(0);
//
//                PermohonanPlotPelan ppp = new PermohonanPlotPelan();
//                ppp = (PermohonanPlotPelan) senaraiPlot.get(i);
//                System.out.println("Id Plot : " + ppp.getIdPlot());
//
//                if (senaraiPlot.size() > 0) {
//                    LOG.debug("------i value--------:" + i);
//                    LOG.debug("------kodHakmilikSementara--------:" + kodHakmilikSementara);
//                    LOG.debug("------kodHakmilikTetap--------:" + kodHakmilikTetap);
//                    LOG.debug("------kategoriTanahBaru--------:" + kategoriTanahBaru);
//                    //            LOG.debug("------jenisPenggunaanTanah--------:" + jenisPenggunaanTanah);
//                    LOG.debug("------kodSyaratNyataBaru--------:" + kodSyaratNyataBaru);
//                    LOG.debug("------kodSekatanKepentinganBaru--------:" + kodSekatanKepentinganBaru);
//                    LOG.debug("------infra--------:" + infra);
//                    LOG.debug("-----pegangan-------:" + pegangan);
//
//                    if (pegangan != null && !pegangan.equals("")) {
//                        ppp.setPegangan(pegangan.charAt(0));
//                    } else {
//                        ppp.setPegangan(null);
//                    }
//                    if (infra != null && !infra.equals("")) {
//                        ppp.setKosInfra(infra);
//                    } else {
//                        ppp.setKosInfra(null);
//                    }
//                    if (kodHakmilikSementara != null && !kodHakmilikSementara.equals("")) {
//                        KodHakmilik hakmilikSementara = kodHakmilikDAO.findById(kodHakmilikSementara);
////                        LOG.debug("------hakmilikSementara--------:"+hakmilikSementara);
//                        ppp.setKodHakmilikSementara(hakmilikSementara);
//                    } else {
//                        ppp.setKodHakmilikSementara(null);
//                    }
//                    if (kodHakmilikTetap != null && !kodHakmilikTetap.equals("")) {
//                        KodHakmilik hakmilikTetap = kodHakmilikDAO.findById(kodHakmilikTetap);
////                        LOG.debug("------hakmilikTetap--------:"+hakmilikTetap);
//                        ppp.setKodHakmilikTetap(hakmilikTetap);
//                    } else {
//                        ppp.setKodHakmilikTetap(null);
//                    }
//                    if (kategoriTanahBaru != null && !kategoriTanahBaru.equals("")) {
//                        KodKategoriTanah kategoriTanah = kodKategoriTanahDAO.findById(kategoriTanahBaru);
//                        ppp.setKategoriTanah(kategoriTanah);
//                    } else {
//                        ppp.setKategoriTanah(null);
//                    }
//                    if (kodSyaratNyataBaru != null && !kodSyaratNyataBaru.equals("")) {
//                        KodSyaratNyata syaratNyataBaru = kodSyaratNyataDAO.findById(kodSyaratNyataBaru);
////                        LOG.debug("$$$$$$$------syaratNyataBaru---------:"+syaratNyataBaru);
//                        ppp.setKodSyaratNyata(syaratNyataBaru);
//                    } else {
//                        ppp.setKodSyaratNyata(null);
//                    }
//                    if (kodSekatanKepentinganBaru != null && !kodSekatanKepentinganBaru.equals("")) {
//                        KodSekatanKepentingan sekatanKepentinganBaru = kodSekatanKepentinganDAO.findById(kodSekatanKepentinganBaru);
////                        LOG.debug("$$$$$$$------sekatanKepentinganBaru---------:"+sekatanKepentinganBaru);
//                        ppp.setKodSekatanKepentingan(sekatanKepentinganBaru);
//                    } else {
//                        ppp.setKodSekatanKepentingan(null);
//                    }
//
//                    if (kodKegunaanTanah != null && !kodKegunaanTanah.equals("")) {
//                        KodKegunaanTanah kegunaanTanah = kodKegunaanTanahDAO.findById(kodKegunaanTanah);
//                        LOG.debug("$$$$$$$------kegunaanTanah---------:" + kegunaanTanah);
//                        ppp.setKegunaanTanah(kegunaanTanah);
//                    } else {
//                        ppp.setKegunaanTanah(null);
//                    }
//
//                    devService.simpanPermohonanPlotPelan(ppp);
//                }
//            }
//        }

        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan9 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        senaraiKandungan9 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        rowCount = senaraiKandungan.size();
        rowCount7 = senaraiKandungan7.size();
        rowCount8 = senaraiKandungan8.size();
        rowCount9 = senaraiKandungan9.size();

        if (permohonan.getKodUrusan().getKod().equals("RPP")) {
            if (permohonan.getPermohonanSebelum().getIdPermohonan() != null) {
                LOG.info("permohonanSebelum : " + permohonan.getPermohonanSebelum().getIdPermohonan());
                permohonanTuntutanKos = devService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04");
            }
        }
        //LOG.info("Kos sebenar : "+permohonanTuntutanKos.getAmaunSebenar());
        if (permohonan.getKodUrusan().getKod().equals("RPP")) {
            if (permohonanTuntutanKos.getAmaunSebenar() == null || permohonanTuntutanKos.getAmaunSebenar().equals("")) {
                permohonanTuntutanKos.setAmaunSebenar(permohonanTuntutanKos.getAmaunTuntutan());
                LOG.info("Amount Sebenar OK : " + permohonanTuntutanKos.getAmaunSebenar());

                permohonanTuntutanKos.setAmaunTuntutan(kadarpremium);
                LOG.info("Amount Tuntut OK : " + permohonanTuntutanKos.getAmaunTuntutan());

                devService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
            } else {
                permohonanTuntutanKos.setAmaunTuntutan(kadarpremium);
                devService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
                getContext().getRequest().setAttribute("plot", Boolean.FALSE);
            } else {
                getContext().getRequest().setAttribute("plot", Boolean.TRUE);
            }
            getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.TRUE);
            getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.FALSE);
            //rehydrate();
        } else {
            if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
                getContext().getRequest().setAttribute("plot", Boolean.FALSE);
            } else {
                getContext().getRequest().setAttribute("plot", Boolean.TRUE);
            }

            getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.TRUE);
            getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.FALSE);
            rehydrate();
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
            e.printStackTrace();
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        rehydrate();

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.TRUE);
            getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("viewAndEditPTD", Boolean.TRUE);
            getContext().getRequest().setAttribute("viewAndEditPTG", Boolean.FALSE);
        }

        System.out.println("Urusan : " + permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RLTB")) {
            getContext().getRequest().setAttribute("plot", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("plot", Boolean.TRUE);
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_kertasMMK_full.jsp").addParameter("tab", "true");
    }

    public Resolution editKeadaanTanah() {
        LOG.debug("editKeadaanTanah");
        idLapor = getContext().getRequest().getParameter("idLapor");
        ltt = laporanTanahDAO.findById(Long.valueOf(idLapor));
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_keadaanTanah.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKeadaanTanah() {
        LOG.info("kemaskini keadaan tanah.");
        idLapor = getContext().getRequest().getParameter("idLapor");
        ltt = laporanTanahDAO.findById(Long.valueOf(idLapor));
        lts = laporanTanahSempadanDAO.findById(Long.valueOf(idLapor));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        for (int i = 0; i < laporanTanahKeadaanTanah.size(); i++) {

            LaporanTanah lapor = new LaporanTanah();
            lapor = (LaporanTanah) laporanTanahKeadaanTanah.get(i);

            if (kedudukanTanah != null) {
                HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = devService.findHakmilikPermohonanByIdHakmilik(idPermohonan, ltt.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                hakmilikPermohonan.setPermohonan(permohonan);
                hakmilikPermohonan.setLokasi(kedudukanTanah);
                hakmilikPermohonan.setInfoAudit(ia);
                devService.simpanHakmilikPermohonan(hakmilikPermohonan);
            }

            if (keadaanTanah != null) {
                lapor.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanTanah));
            }
            if (bangunan != null) {
                lapor.setAdaBangunan(bangunan.charAt(0));
            }
            if (tanaman != null) {
                lapor.setUsaha(tanaman.charAt(0));
            }

            lapor.setPermohonan(permohonan);
            lapor.setInfoAudit(ia);
            devService.simpanLaporanTanah(lapor);
        }

        //utara
//        for (int i = 0; i < laporanTanahSempadanForU.size(); i++) {
//
//            LaporanTanahSempadan laporU = new LaporanTanahSempadan();
//            laporU = (LaporanTanahSempadan) laporanTanahSempadanForU.get(i);
//
//            if (milikKjaanUtara != null) {
//                laporU.setMilikKerajaan(milikKjaanUtara);
//            }
//
//            if (noLotUtara != null) {
//                laporU.setNoLot(noLotUtara);
//            }
//            if (gunaUtara != null) {
//                laporU.setGuna(gunaUtara);
//            }
//            if (catatanUtara != null) {
//                laporU.setCatatan(catatanUtara);
//            }
//
//            laporU.setLaporanTanah(laporanTanahDAO.findById(Long.parseLong(idLapor)));
//            laporU.setInfoAudit(ia);
//            devService.simpanLaporanTanahSempadan(laporU);
//        }

        //utara
        for (LaporanTanahSempadan laporU : laporanTanahSempadanForU) {
            if (milikKjaanUtara != null) {
                laporU.setMilikKerajaan(milikKjaanUtara);
            }
            if (kodLotUtara != null) {
                laporU.setKodLot(kodLotDAO.findById(kodLotUtara));
            }
            if (noLotUtara != null) {
                laporU.setNoLot(noLotUtara);
            }
            if (gunaUtara != null) {
                laporU.setGuna(gunaUtara);
            }
            if (catatanUtara != null) {
                laporU.setCatatan(catatanUtara);
            }

            laporU.setLaporanTanah(laporanTanahDAO.findById(Long.parseLong(idLapor)));
            laporU.setInfoAudit(ia);
            devService.simpanLaporanTanahSempadan(laporU);
        }

        //timur
        for (LaporanTanahSempadan laporT : laporanTanahSempadanForT) {
            if (milikKjaanTimur != null) {
                laporT.setMilikKerajaan(milikKjaanTimur);
            }
            if (kodLotTimur != null) {
                laporT.setKodLot(kodLotDAO.findById(kodLotTimur));
            }
            if (noLotTimur != null) {
                laporT.setNoLot(noLotTimur);
            }
            if (gunaTimur != null) {
                laporT.setGuna(gunaTimur);
            }
            if (catatanTimur != null) {
                laporT.setCatatan(catatanTimur);
            }

            laporT.setLaporanTanah(laporanTanahDAO.findById(Long.parseLong(idLapor)));
            laporT.setInfoAudit(ia);
            devService.simpanLaporanTanahSempadan(laporT);
        }

        //selatan
        for (LaporanTanahSempadan laporS : laporanTanahSempadanForS) {
            if (milikKjaanSelatan != null) {
                laporS.setMilikKerajaan(milikKjaanSelatan);
            }
            if (kodLotSelatan != null) {
                laporS.setKodLot(kodLotDAO.findById(kodLotSelatan));
            }
            if (noLotSelatan != null) {
                laporS.setNoLot(noLotSelatan);
            }
            if (gunaSelatan != null) {
                laporS.setGuna(gunaSelatan);
            }
            if (catatanSelatan != null) {
                laporS.setCatatan(catatanSelatan);
            }

            laporS.setLaporanTanah(laporanTanahDAO.findById(Long.parseLong(idLapor)));
            laporS.setInfoAudit(ia);
            devService.simpanLaporanTanahSempadan(laporS);
        }

        //barat
        for (LaporanTanahSempadan laporB : laporanTanahSempadanForB) {
            if (milikKjaanBarat != null) {
                laporB.setMilikKerajaan(milikKjaanBarat);
            }
            if (kodLotBarat != null) {
                laporB.setKodLot(kodLotDAO.findById(kodLotBarat));
            }
            if (noLotBarat != null) {
                laporB.setNoLot(noLotBarat);
            }
            if (gunaBarat != null) {
                laporB.setGuna(gunaBarat);
            }
            if (catatanBarat != null) {
                laporB.setCatatan(catatanBarat);
            }

            laporB.setLaporanTanah(laporanTanahDAO.findById(Long.parseLong(idLapor)));
            laporB.setInfoAudit(ia);
            devService.simpanLaporanTanahSempadan(laporB);
        }
        addSimpleMessage("Kemaskini telah berjaya.");
        LOG.info("kemaskini keadaan tanah.");
        rehydrate();
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_keadaanTanah.jsp").addParameter("popup", "true");
    }

    public Resolution editPopup() {
        LOG.debug("editPopup");
        forEdit = getContext().getRequest().getParameter("forEdit");
        idPlot = getContext().getRequest().getParameter("idPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMKPopupMaklumat.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup() {
        LOG.info("kemaskini mohon plot pelan start.");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (mpp.getKategoriTanah().getKod().equals("1")) {
            listKatgPertanianByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgPertanianByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgPertanianByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgPertanianByIdPlot;
        } else if (mpp.getKategoriTanah().getKod().equals("3")) {
            listKatgIndustriByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgIndustriByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgIndustriByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgIndustriByIdPlot;
        } else if (mpp.getKategoriTanah().getKod().equals("2")) {
            listKatgBangunanByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgBangunanByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgKatgBangunanByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgBangunanByIdPlot;
        }

        for (int i = 0; i < listallplotpelan.size(); i++) {

            PermohonanPlotPelan mppp = new PermohonanPlotPelan();
            mppp = (PermohonanPlotPelan) listallplotpelan.get(i);

            if (pegangan0 != null) {
                mppp.setPegangan(pegangan0.charAt(0));
            }

            if (tempohPegangan != null) {
                mppp.setTempohPegangan(Integer.parseInt(tempohPegangan));
            }

            if (janisHakmilikSementara != null) {
                mppp.setKodHakmilikSementara(kodHakmilikDAO.findById(janisHakmilikSementara));
            }

            if (jenisHakmilikTetap != null) {
                mppp.setKodHakmilikTetap(kodHakmilikDAO.findById(jenisHakmilikTetap));
            }

            if (kadarCukai != null) {
                mppp.setKeteranganCukaiBaru(kadarCukai);
            }

            if (kadarPremium != null) {
                mppp.setKeteranganKadarPremium(kadarPremium);
            }

            if (kadarInfra != null) {
                mppp.setKosInfra(kadarInfra);
            }

            if (kadarUpah != null) {
                mppp.setKeteranganKadarUkur(kadarUpah);
            }

            if (kadarDaftar != null) {
                mppp.setKeteranganKadarDaftar(kadarDaftar);
            }

            if (ingatan != null) {
                mppp.setPeringatan(ingatan);
            }
            devService.simpanPlotPelan(mppp);
        }
        addSimpleMessage("Kemaskini telah berjaya.");
        LOG.info("kemaskini mohon plot pelan finish.");
        rehydrate();
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMKPopupMaklumat.jsp").addParameter("popup", "true");
    }

    //kodSyaratNyata&&sekatanKepentingan
    public Resolution editNyata() {
        LOG.debug("editNyata");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_kodSy.jsp").addParameter("popup", "true");
    }

    public Resolution editSekatan() {
        LOG.debug("editSekatan");
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_kodSy.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatanKertasMMK() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_carianSy.jsp").addParameter("tab", "true");
    }

    public Resolution showFormCariKodSyaratNyataKertasMMK() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_carianSy.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSekatanKertasMMK() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        forEdit = getContext().getRequest().getParameter("forEdit");
        LOG.info(idPlot + "+" + forEdit);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatan != null) {
            listKodSekatan = devService.searchKodSekatan(kodSekatan, kc, sekatan);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = devService.searchKodSekatan("%", kc, sekatan);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_carianSy.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSyaratNyataKertasMMK() {
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

        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_carianSy.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKodSyaratNyataKertasMMK() {
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
        if (mpp.getKategoriTanah().getKod().equals("1")) {
            listKatgPertanianByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgPertanianByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgPertanianByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgPertanianByIdPlot;
        } else if (mpp.getKategoriTanah().getKod().equals("3")) {
            listKatgIndustriByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgIndustriByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgForKatgIndustriByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgIndustriByIdPlot;
        } else if (mpp.getKategoriTanah().getKod().equals("2")) {
            listKatgBangunanByIdPlot = new ArrayList<PermohonanPlotPelan>();
            listKatgBangunanByIdPlot = devService.findSenaraiPermohonanPlotPelanByOneKatgKatgBangunanByIdPlot(idPermohonan, mpp.getKegunaanTanah().getKod());
            listallplotpelan = listKatgBangunanByIdPlot;
        }

        for (int i = 0; i < listallplotpelan.size(); i++) {

            PermohonanPlotPelan mppp = new PermohonanPlotPelan();
            mppp = (PermohonanPlotPelan) listallplotpelan.get(i);

            String kodSyaratNyata1 = getContext().getRequest().getParameter("selectedKod");
            LOG.info("---kodSyaratNyata" + kodSyaratNyata1);
            if (kodSyaratNyata1 != null) {
                mppp.setKodSyaratNyata(kodSyaratNyataDAO.findById(kodSyaratNyata1));
            }

            String kodSyaratSekatan1 = getContext().getRequest().getParameter("selectedKod1");
            LOG.info("----kodSyaratSekatan" + kodSyaratSekatan1);
            if (kodSyaratSekatan1 != null) {
                mppp.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(kodSyaratSekatan1));
            }

            devService.simpanPlotPelan(mppp);
        }

        addSimpleMessage("Maklumat Telah Ditambah");
        getContext().getRequest().setAttribute("idPlot", Boolean.TRUE);
        getContext().getRequest().setAttribute("forEdit", Boolean.TRUE);
        LOG.info("---CODE FINISH----.");
        rehydrate();
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_kodSy.jsp").addParameter("popup", "true");
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

    public String getHuraianPtg1() {
        return huraianPtg1;
    }

    public void setHuraianPtg1(String huraianPtg1) {
        this.huraianPtg1 = huraianPtg1;
    }

    public String getSyorPentadbir1() {
        return syorPentadbir1;
    }

    public void setSyorPentadbir1(String syorPentadbir1) {
        this.syorPentadbir1 = syorPentadbir1;
    }

    public String getSyorPtg1() {
        return syorPtg1;
    }

    public void setSyorPtg1(String syorPtg1) {
        this.syorPtg1 = syorPtg1;
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

    //add by wani
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

    public String getPembangunanDicadang() {
        return pembangunanDicadang;
    }

    public void setPembangunanDicadang(String pembangunanDicadang) {
        this.pembangunanDicadang = pembangunanDicadang;
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
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

    public String getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(String kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public String getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(String kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public String getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setKategoriTanahBaru(String kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public String getJenisPenggunaanTanah() {
        return jenisPenggunaanTanah;
    }

    public void setJenisPenggunaanTanah(String jenisPenggunaanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunaanTanah;
    }

    public String getKodSekatanKepentinganBaru() {
        return kodSekatanKepentinganBaru;
    }

    public void setKodSekatanKepentinganBaru(String kodSekatanKepentinganBaru) {
        this.kodSekatanKepentinganBaru = kodSekatanKepentinganBaru;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

//    public String getKodSyaratNyataBaru1() {
//        return kodSyaratNyataBaru1;
//    }
//
//    public void setKodSyaratNyataBaru1(String kodSyaratNyataBaru1) {
//        this.kodSyaratNyataBaru1 = kodSyaratNyataBaru1;
//    }
//    public String getKodSekatanKepentinganBaru1() {
//        return kodSekatanKepentinganBaru1;
//    }
//
//    public void setKodSekatanKepentinganBaru1(String kodSekatanKepentinganBaru1) {
//        this.kodSekatanKepentinganBaru1 = kodSekatanKepentinganBaru1;
//    }
    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public List<PermohonanPlotPelan> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<PermohonanPlotPelan> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getHuraianPentadbir1() {
        return huraianPentadbir1;
    }

    public void setHuraianPentadbir1(String huraianPentadbir1) {
        this.huraianPentadbir1 = huraianPentadbir1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan7() {
        return senaraiKandungan7;
    }

    public void setSenaraiKandungan7(List<PermohonanKertasKandungan> senaraiKandungan7) {
        this.senaraiKandungan7 = senaraiKandungan7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan8() {
        return senaraiKandungan8;
    }

    public void setSenaraiKandungan8(List<PermohonanKertasKandungan> senaraiKandungan8) {
        this.senaraiKandungan8 = senaraiKandungan8;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan9() {
        return senaraiKandungan9;
    }

    public void setSenaraiKandungan9(List<PermohonanKertasKandungan> senaraiKandungan9) {
        this.senaraiKandungan9 = senaraiKandungan9;
    }

    public int getRowCount7() {
        return rowCount7;
    }

    public void setRowCount7(int rowCount7) {
        this.rowCount7 = rowCount7;
    }

    public int getRowCount8() {
        return rowCount8;
    }

    public void setRowCount8(int rowCount8) {
        this.rowCount8 = rowCount8;
    }

    public int getRowCount9() {
        return rowCount9;
    }

    public void setRowCount9(int rowCount9) {
        this.rowCount9 = rowCount9;
    }

    public List<Integer> getTempohTamat() {
        return tempohTamat;
    }

    public void setTempohTamat(List<Integer> tempohTamat) {
        this.tempohTamat = tempohTamat;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanTanamanList() {
        return permohonanLaporanTanamanList;
    }

    public void setPermohonanLaporanTanamanList(List<PermohonanLaporanBangunan> permohonanLaporanTanamanList) {
        this.permohonanLaporanTanamanList = permohonanLaporanTanamanList;
    }

    public List<PermohonanLaporanCerun> getFindListlaporCerun() {
        return findListlaporCerun;
    }

    public void setFindListlaporCerun(List<PermohonanLaporanCerun> findListlaporCerun) {
        this.findListlaporCerun = findListlaporCerun;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(String kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public BigDecimal getJumluasHakmilik() {
        return jumluasHakmilik;
    }

    public void setJumluasHakmilik(BigDecimal jumluasHakmilik) {
        this.jumluasHakmilik = jumluasHakmilik;
    }

    public int getBilplotTHakmiliktbl() {
        return bilplotTHakmiliktbl;
    }

    public void setBilplotTHakmiliktbl(int bilplotTHakmiliktbl) {
        this.bilplotTHakmiliktbl = bilplotTHakmiliktbl;
    }

    public ArrayList getListRizab() {
        return listRizab;
    }

    public void setListRizab(ArrayList listRizab) {
        this.listRizab = listRizab;
    }

    public List<HakmilikPermohonan> getSenaraiPlot() {
        return senaraiPlot;
    }

    public void setSenaraiPlot(List<HakmilikPermohonan> senaraiPlot) {
        this.senaraiPlot = senaraiPlot;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanRujukanLuar> getMohonRujLuarList() {
        return mohonRujLuarList;
    }

    public void setMohonRujLuarList(List<PermohonanRujukanLuar> mohonRujLuarList) {
        this.mohonRujLuarList = mohonRujLuarList;
    }

    public HakmilikPermohonan getHakmilikPermohonan1() {
        return hakmilikPermohonan1;
    }

    public void setHakmilikPermohonan1(HakmilikPermohonan hakmilikPermohonan1) {
        this.hakmilikPermohonan1 = hakmilikPermohonan1;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanList() {
        return laporanTanahSempadanList;
    }

    public void setLaporanTanahSempadanList(List<LaporanTanahSempadan> laporanTanahSempadanList) {
        this.laporanTanahSempadanList = laporanTanahSempadanList;
    }

    public List<LaporanTanah> getLaporanTanahList2() {
        return laporanTanahList2;
    }

    public void setLaporanTanahList2(List<LaporanTanah> laporanTanahList2) {
        this.laporanTanahList2 = laporanTanahList2;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanList2() {
        return laporanTanahSempadanList2;
    }

    public void setLaporanTanahSempadanList2(List<LaporanTanahSempadan> laporanTanahSempadanList2) {
        this.laporanTanahSempadanList2 = laporanTanahSempadanList2;
    }

    public List<LaporanTanah> getLaporanTanah2() {
        return laporanTanah2;
    }

    public void setLaporanTanah2(List<LaporanTanah> laporanTanah2) {
        this.laporanTanah2 = laporanTanah2;
    }

    public int getSenaraiplotsize() {
        return senaraiplotsize;
    }

    public void setSenaraiplotsize(int senaraiplotsize) {
        this.senaraiplotsize = senaraiplotsize;
    }

    public String getPelantatatur() {
        return pelantatatur;
    }

    public void setPelantatatur(String pelantatatur) {
        this.pelantatatur = pelantatatur;
    }

    public List<PermohonanPlotPelan> getListKerajaanAndRizab() {
        return listKerajaanAndRizab;
    }

    public void setListKerajaanAndRizab(List<PermohonanPlotPelan> listKerajaanAndRizab) {
        this.listKerajaanAndRizab = listKerajaanAndRizab;
    }

    public String getKedudukanTanah() {
        return kedudukanTanah;
    }

    public void setKedudukanTanah(String kedudukanTanah) {
        this.kedudukanTanah = kedudukanTanah;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getTanaman() {
        return tanaman;
    }

    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public LaporanTanah getLtt() {
        return ltt;
    }

    public void setLtt(LaporanTanah ltt) {
        this.ltt = ltt;
    }

    public List<PermohonanPlotPelan> getListKatgPertanian() {
        return listKatgPertanian;
    }

    public void setListKatgPertanian(List<PermohonanPlotPelan> listKatgPertanian) {
        this.listKatgPertanian = listKatgPertanian;
    }

    public List<PermohonanPlotPelan> getListKatgIndustri() {
        return listKatgIndustri;
    }

    public void setListKatgIndustri(List<PermohonanPlotPelan> listKatgIndustri) {
        this.listKatgIndustri = listKatgIndustri;
    }

    public List<PermohonanPlotPelan> getListKatgBangunanB01() {
        return listKatgBangunanB01;
    }

    public void setListKatgBangunanB01(List<PermohonanPlotPelan> listKatgBangunanB01) {
        this.listKatgBangunanB01 = listKatgBangunanB01;
    }

    public String getForEdit() {
        return forEdit;
    }

    public void setForEdit(String forEdit) {
        this.forEdit = forEdit;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }

    public String getInfra() {
        return infra;
    }

    public void setInfra(String infra) {
        this.infra = infra;
    }

    public BigDecimal getKadarpremium() {
        return kadarpremium;
    }

    public void setKadarpremium(BigDecimal kadarpremium) {
        this.kadarpremium = kadarpremium;
    }

    public String getTempohPegangan() {
        return tempohPegangan;
    }

    public void setTempohPegangan(String tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
    }

    public String getPegangan0() {
        return pegangan0;
    }

    public void setPegangan0(String pegangan0) {
        this.pegangan0 = pegangan0;
    }

    public String getJanisHakmilikSementara() {
        return janisHakmilikSementara;
    }

    public void setJanisHakmilikSementara(String janisHakmilikSementara) {
        this.janisHakmilikSementara = janisHakmilikSementara;
    }

    public String getJenisHakmilikTetap() {
        return jenisHakmilikTetap;
    }

    public void setJenisHakmilikTetap(String jenisHakmilikTetap) {
        this.jenisHakmilikTetap = jenisHakmilikTetap;
    }

    public String getKadarCukai() {
        return kadarCukai;
    }

    public void setKadarCukai(String kadarCukai) {
        this.kadarCukai = kadarCukai;
    }

    public String getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(String kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public String getKadarInfra() {
        return kadarInfra;
    }

    public void setKadarInfra(String kadarInfra) {
        this.kadarInfra = kadarInfra;
    }

    public String getKadarUpah() {
        return kadarUpah;
    }

    public void setKadarUpah(String kadarUpah) {
        this.kadarUpah = kadarUpah;
    }

    public String getKadarDaftar() {
        return kadarDaftar;
    }

    public void setKadarDaftar(String kadarDaftar) {
        this.kadarDaftar = kadarDaftar;
    }

    public String getKodSyaratNyataMpp() {
        return kodSyaratNyataMpp;
    }

    public void setKodSyaratNyataMpp(String kodSyaratNyataMpp) {
        this.kodSyaratNyataMpp = kodSyaratNyataMpp;
    }

    public String getIngatan() {
        return ingatan;
    }

    public void setIngatan(String ingatan) {
        this.ingatan = ingatan;
    }

    public String getKodSekatanKepentinganBaruMpp() {
        return kodSekatanKepentinganBaruMpp;
    }

    public void setKodSekatanKepentinganBaruMpp(String kodSekatanKepentinganBaruMpp) {
        this.kodSekatanKepentinganBaruMpp = kodSekatanKepentinganBaruMpp;
    }

    public PermohonanPlotPelan getMpp() {
        return mpp;
    }

    public void setMpp(PermohonanPlotPelan mpp) {
        this.mpp = mpp;
    }

    public List<PermohonanPlotPelan> getListKatgPertanianByIdPlot() {
        return listKatgPertanianByIdPlot;
    }

    public void setListKatgPertanianByIdPlot(List<PermohonanPlotPelan> listKatgPertanianByIdPlot) {
        this.listKatgPertanianByIdPlot = listKatgPertanianByIdPlot;
    }

    public List<PermohonanPlotPelan> getListallplotpelan() {
        return listallplotpelan;
    }

    public void setListallplotpelan(List<PermohonanPlotPelan> listallplotpelan) {
        this.listallplotpelan = listallplotpelan;
    }

    public List<PermohonanPlotPelan> getListKatgIndustriByIdPlot() {
        return listKatgIndustriByIdPlot;
    }

    public void setListKatgIndustriByIdPlot(List<PermohonanPlotPelan> listKatgIndustriByIdPlot) {
        this.listKatgIndustriByIdPlot = listKatgIndustriByIdPlot;
    }

    public List<PermohonanPlotPelan> getSenaraiBangunanTerpilih() {
        return senaraiBangunanTerpilih;
    }

    public void setSenaraiBangunanTerpilih(List<PermohonanPlotPelan> senaraiBangunanTerpilih) {
        this.senaraiBangunanTerpilih = senaraiBangunanTerpilih;
    }

    public List<PermohonanPlotPelan> getListKatgBangunanByIdPlot() {
        return listKatgBangunanByIdPlot;
    }

    public void setListKatgBangunanByIdPlot(List<PermohonanPlotPelan> listKatgBangunanByIdPlot) {
        this.listKatgBangunanByIdPlot = listKatgBangunanByIdPlot;
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

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public List<LaporanTanah> getLaporanTanahKeadaanTanah() {
        return laporanTanahKeadaanTanah;
    }

    public void setLaporanTanahKeadaanTanah(List<LaporanTanah> laporanTanahKeadaanTanah) {
        this.laporanTanahKeadaanTanah = laporanTanahKeadaanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikbyKeadaanTanah() {
        return hakmilikbyKeadaanTanah;
    }

    public void setHakmilikbyKeadaanTanah(List<HakmilikPermohonan> hakmilikbyKeadaanTanah) {
        this.hakmilikbyKeadaanTanah = hakmilikbyKeadaanTanah;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanForU() {
        return laporanTanahSempadanForU;
    }

    public void setLaporanTanahSempadanForU(List<LaporanTanahSempadan> laporanTanahSempadanForU) {
        this.laporanTanahSempadanForU = laporanTanahSempadanForU;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanForT() {
        return laporanTanahSempadanForT;
    }

    public void setLaporanTanahSempadanForT(List<LaporanTanahSempadan> laporanTanahSempadanForT) {
        this.laporanTanahSempadanForT = laporanTanahSempadanForT;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanForB() {
        return laporanTanahSempadanForB;
    }

    public void setLaporanTanahSempadanForB(List<LaporanTanahSempadan> laporanTanahSempadanForB) {
        this.laporanTanahSempadanForB = laporanTanahSempadanForB;
    }

    public List<LaporanTanahSempadan> getLaporanTanahSempadanForS() {
        return laporanTanahSempadanForS;
    }

    public void setLaporanTanahSempadanForS(List<LaporanTanahSempadan> laporanTanahSempadanForS) {
        this.laporanTanahSempadanForS = laporanTanahSempadanForS;
    }

    public LaporanTanahSempadan getLts() {
        return lts;
    }

    public void setLts(LaporanTanahSempadan lts) {
        this.lts = lts;
    }

    public String getMilikKjaanUtara() {
        return milikKjaanUtara;
    }

    public void setMilikKjaanUtara(String milikKjaanUtara) {
        this.milikKjaanUtara = milikKjaanUtara;
    }

    public String getNoLotUtara() {
        return noLotUtara;
    }

    public void setNoLotUtara(String noLotUtara) {
        this.noLotUtara = noLotUtara;
    }

    public String getGunaUtara() {
        return gunaUtara;
    }

    public void setGunaUtara(String gunaUtara) {
        this.gunaUtara = gunaUtara;
    }

    public String getCatatanUtara() {
        return catatanUtara;
    }

    public void setCatatanUtara(String catatanUtara) {
        this.catatanUtara = catatanUtara;
    }

    public String getMilikKjaanTimur() {
        return milikKjaanTimur;
    }

    public void setMilikKjaanTimur(String milikKjaanTimur) {
        this.milikKjaanTimur = milikKjaanTimur;
    }

    public String getNoLotTimur() {
        return noLotTimur;
    }

    public void setNoLotTimur(String noLotTimur) {
        this.noLotTimur = noLotTimur;
    }

    public String getGunaTimur() {
        return gunaTimur;
    }

    public void setGunaTimur(String gunaTimur) {
        this.gunaTimur = gunaTimur;
    }

    public String getCatatanTimur() {
        return catatanTimur;
    }

    public void setCatatanTimur(String catatanTimur) {
        this.catatanTimur = catatanTimur;
    }

    public String getMilikKjaanSelatan() {
        return milikKjaanSelatan;
    }

    public void setMilikKjaanSelatan(String milikKjaanSelatan) {
        this.milikKjaanSelatan = milikKjaanSelatan;
    }

    public String getNoLotSelatan() {
        return noLotSelatan;
    }

    public void setNoLotSelatan(String noLotSelatan) {
        this.noLotSelatan = noLotSelatan;
    }

    public String getGunaSelatan() {
        return gunaSelatan;
    }

    public void setGunaSelatan(String gunaSelatan) {
        this.gunaSelatan = gunaSelatan;
    }

    public String getCatatanSelatan() {
        return catatanSelatan;
    }

    public void setCatatanSelatan(String catatanSelatan) {
        this.catatanSelatan = catatanSelatan;
    }

    public String getMilikKjaanBarat() {
        return milikKjaanBarat;
    }

    public void setMilikKjaanBarat(String milikKjaanBarat) {
        this.milikKjaanBarat = milikKjaanBarat;
    }

    public String getNoLotBarat() {
        return noLotBarat;
    }

    public void setNoLotBarat(String noLotBarat) {
        this.noLotBarat = noLotBarat;
    }

    public String getGunaBarat() {
        return gunaBarat;
    }

    public void setGunaBarat(String gunaBarat) {
        this.gunaBarat = gunaBarat;
    }

    public String getCatatanBarat() {
        return catatanBarat;
    }

    public void setCatatanBarat(String catatanBarat) {
        this.catatanBarat = catatanBarat;
    }

    public List<PermohonanPlotPelan> getSenaraiIndustriTerpilih() {
        return senaraiIndustriTerpilih;
    }

    public void setSenaraiIndustriTerpilih(List<PermohonanPlotPelan> senaraiIndustriTerpilih) {
        this.senaraiIndustriTerpilih = senaraiIndustriTerpilih;
    }

    public List<PermohonanPlotPelan> getSenaraiPertanianTerpilih() {
        return senaraiPertanianTerpilih;
    }

    public void setSenaraiPertanianTerpilih(List<PermohonanPlotPelan> senaraiPertanianTerpilih) {
        this.senaraiPertanianTerpilih = senaraiPertanianTerpilih;
    }

    public String getKodLotUtara() {
        return kodLotUtara;
    }

    public void setKodLotUtara(String kodLotUtara) {
        this.kodLotUtara = kodLotUtara;
    }

    public String getKodLotTimur() {
        return kodLotTimur;
    }

    public void setKodLotTimur(String kodLotTimur) {
        this.kodLotTimur = kodLotTimur;
    }

    public String getKodLotBarat() {
        return kodLotBarat;
    }

    public void setKodLotBarat(String kodLotBarat) {
        this.kodLotBarat = kodLotBarat;
    }

    public String getKodLotSelatan() {
        return kodLotSelatan;
    }

    public void setKodLotSelatan(String kodLotSelatan) {
        this.kodLotSelatan = kodLotSelatan;
    }


    public String UpperCaseWords(String line) {

        if (line != null) {

            line = line.trim().toLowerCase();
            String data[] = line.split("\\s");
            line = "";
            for (int i = 0; i < data.length; i++) {
                if (data[i].length() > 1) {
                    line = line + data[i].substring(0, 1).toUpperCase() + data[i].substring(1) + " ";
                } else {
                    line = line + data[i].toUpperCase();
                }
            }
            line.trim();
        }
        return line;
    }
}