/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
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
import java.util.Collections;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodJenisPendudukan;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.model.KodPemilikan;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTanah;
import etanah.model.KodPeranan;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import etanah.util.FileUtil;
import etanah.view.stripes.pelupusan.disClass.DisLaporTanahKawasan;
import etanah.view.stripes.pengambilan.DisLaporanTanahController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pengambilan.AcqPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodRujukan;
import etanah.model.PermohonanLaporanUsaha;
import etanah.view.penguatkuasaan.EnfPermohonanPage;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author User
 */
@UrlBinding("/pengambilan/laporan_tanahV2_sblm")
public class LaporanTanahNewDisposalSblmActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private String idPermohonan;
    private String stageId;
    private String kodNegeri;
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
    private String keteranganKadarPremium;
    private String kodD;
    private String kodPar;
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
    private String kegunaan;
    String id2;
    String id;
    private char kodP;
    private char pandanganImej;
    private NoPt noPT = new NoPt();
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
    private TanahRizabPermohonan tanahrizabpermohonan1;
    private PermohonanPermitItem permohonanPermitItem;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanLaporanUsaha permohonanLaporanUsaha;
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private PermohonanPermitItem pmi = new PermohonanPermitItem();
    private KodUOM jumlahIsipaduUOM;
    private FileBean fileToBeUpload;
    private Pemohon pemohon;
    etanahActionBeanContext ctx;
    Permohonan prmhnn;
    private KodCawangan cawangan;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> senaraiHakmilikPerihalTanah;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanLaporanUlasan> senaraiLaporanKandungan1;
    private List<PermohonanLaporanUlasan> senaraiLaporanKandunganPPTKanan;
    private List<String> senaraikodKadarPremium;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanManual> permohonanManualList;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal;
    private List<KodSeksyen> kodSeksyenList;
    private List<ImejLaporan> hakmilikImejLaporanList;
    private List<ImejLaporan> hakmilikImejLaporanListEdit;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List<PermohonanLaporanUsaha> listPermohonanLaporanUsaha;
    private List hakmilikPermohonanList;
    List<HakmilikPermohonan> senaraiHakmilik;
    private List<Hakmilik> list;
    private BigDecimal luasDilulus;
    private BigDecimal usahaHarga;
    private BigDecimal amnt = BigDecimal.ZERO;
    private BigDecimal usahaLuas;
    private Integer usahaBilanganPokok = new Integer(0);
    private static int day;
    private int sizeKod;
    private List<KodAgensi> listKodAgensi;
    private KodAgensi kodAgensi;
    private String kodAgensiNama;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private static final Logger LOG = Logger.getLogger(LaporanTanahNewDisposalActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng; //Add for charting
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    private String syorUlasan;
    private String syorUlasanPPTK;
    private PermohonanLaporanUlasan laporanUlasanPPT;
    private PermohonanLaporanUlasan laporanUlasanPPTK;
    private KodPeranan kodPeranan;

    public String getSyorUlasanPPTK() {
        return syorUlasanPPTK;
    }

    public void setSyorUlasanPPTK(String syorUlasanPPTK) {
        this.syorUlasanPPTK = syorUlasanPPTK;
    }

    public PermohonanLaporanUlasan getLaporanUlasanPPT() {
        return laporanUlasanPPT;
    }

    public void setLaporanUlasanPPT(PermohonanLaporanUlasan laporanUlasanPPT) {
        this.laporanUlasanPPT = laporanUlasanPPT;
    }

    public PermohonanLaporanUlasan getLaporanUlasanPPTK() {
        return laporanUlasanPPTK;
    }

    public void setLaporanUlasanPPTK(PermohonanLaporanUlasan laporanUlasanPPTK) {
        this.laporanUlasanPPTK = laporanUlasanPPTK;
    }

    public String getSyorUlasan() {
        return syorUlasan;
    }

    public void setSyorUlasan(String syorUlasan) {
        this.syorUlasan = syorUlasan;
    }

    @DefaultHandler
    public Resolution viewOnlyLaporanTanahPPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanTanahController = new DisLaporanTanahController();
        disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
        httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
        return new JSP(AcqPermohonanPage.getLT_MAIN_PAGE_SBLM()).addParameter("tab", "true");
    }

    public Resolution viewOnlyLaporanTanahPPTKanan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanTanahController = new DisLaporanTanahController();
        disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
        httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
        return new JSP(AcqPermohonanPage.getLT_MAIN_PAGE_SBLM()).addParameter("tab", "true");
    }

    public Resolution viewOnlyLaporanTanah() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanTanahController = new DisLaporanTanahController();
        disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
        httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
        return new JSP(AcqPermohonanPage.getLT_MAIN_PAGE_SBLM()).addParameter("tab", "true");
    }

    public Resolution carianHakmilikPopup() {
        list = Collections.EMPTY_LIST;
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_Hakmilik_Popup.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
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

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan(), idHakmilik}, 0);
        } else {
            /*
             * SENARAI HAKMILIK
             */
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//                senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTNB")) {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }

            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilikPermohonanList = new ArrayList();
                HakmilikPermohonan mohonHM = new HakmilikPermohonan();
                DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

                mohonHM = permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0);
                disMohonHM.setHakmilikPermohonan(mohonHM);
                hakmilikPermohonanList.add(disMohonHM);
                if (disMohonHM.getHakmilikPermohonan().getHakmilik() != null) {
                    idHakmilik = disMohonHM.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                    hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
                } else {
                    if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                    } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                    } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                        HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                        hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);

                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                    HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                    hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                    hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);

                }
            }
        }
        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getKodDUN() != null) {
                kodD = hakmilikPermohonan.getKodDUN().getKod();
            }
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
        }
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
            laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", pguna));
            laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanah.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanah);
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);

        }
        if (laporanTanah.getUsaha() == null || !Character.isDefined(laporanTanah.getUsaha()) || Character.isWhitespace(laporanTanah.getUsaha())) {
            laporanTanah.setUsaha('T');
        }
        if (laporanTanah.getAdaBangunan() == null || !Character.isDefined(laporanTanah.getAdaBangunan()) || Character.isWhitespace(laporanTanah.getAdaBangunan())) {
            laporanTanah.setAdaBangunan('T');
        }
        if (StringUtils.isNotBlank(type)) {
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
     * Note : the type is set based on frame in laporanTanahV2, please consult before making changes since this method is sharing
     */
    public String refreshData(String type) {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println(idPermohonan);
        String forwardJSP = new String();
        int typeNum = type.equals("pTanah") ? 1
                : type.equals("sempadan") ? 2
                : type.equals("kTanah") ? 3
                : type.equals("lSempadan") ? 4
                : type.equals("dKawasan") ? 5
                : type.equals("syorPPT") ? 6
                : type.equals("main") ? 7
                : type.equals("imgPopup") ? 8
                : type.equals("syorPPTKanan") ? 9
                : type.equals("jTeknikal") ? 10
                : 0;
        System.out.println("-------------typenum-------------" + typeNum);
        switch (typeNum) {
            case 1:
                System.out.println("Masuk case 1");
                forwardJSP = AcqPermohonanPage.getLT_PTANAH_PAGE();


                tanahrizabpermohonan1 = new TanahRizabPermohonan();
                if (StringUtils.isEmpty(idHakmilik)) {
                    tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 0);
                } else {
                    tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan(), idHakmilik}, 0);
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(permohonan.getPermohonanSebelum().getIdPermohonan());
//                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan}, 0);
//                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);
                }
                if ((permohonanLaporanPelan != null) && permohonanLaporanPelan.getKodTanah() != null) {
                    kodT = permohonanLaporanPelan.getKodTanah().getKod();
                }
                if ((tanahrizabpermohonan1 != null) && tanahrizabpermohonan1.getRizab() != null) {
                    int tr = tanahrizabpermohonan1.getRizab().getKod();
                    tanahR = Integer.toString(tr);
                }
                if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
                    kand = permohonanLaporanKandungan.getKand();
                }
                break;
            case 2:
                System.out.println("Masuk case 2");
                forwardJSP = AcqPermohonanPage.getLT_SEMPADAN_PAGE();
                break;
            case 3:
                System.out.println("Masuk case 3");
                permohonanLaporanBangunanList = disLaporanTanahService.getLaporanTanahService().getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                listPermohonanLaporanUsaha = disLaporanTanahService.getPelupusanService().findListMohonLaporUsahaByIdLaporan(laporanTanah.getIdLaporan());
                if (listPermohonanLaporanUsaha.size() > 0) {
                    permohonanLaporanUsaha = listPermohonanLaporanUsaha.get(0);
                }
                forwardJSP = AcqPermohonanPage.getLT_KTANAH_PAGE();
                break;
            case 4:
                System.out.println("Masuk case 4");
                /*
                 * Added For Dynamic Lot-Lot Sempadan
                 */
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
                LOG.info("listLaporTanahSpdnU.size():" + disLaporanTanahSempadan.getListLaporTanahSpdnU().size());
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


                forwardJSP = AcqPermohonanPage.getLT_LSEMPADAN_PAGE();
                break;
            case 5:
                System.out.println("Masuk case 5");
                senaraiLaporanKawasan = new ArrayList<PermohonanLaporanKawasan>();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                    if (hp != null) {
                        senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
//                        senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNidMH(idPermohonan, hp.getId());
                    } else {
                        senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNidMH(idPermohonan, hp.getId());
//                        senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
                    }
                }

                forwardJSP = AcqPermohonanPage.getLT_DKAWASAN_PAGE();
                break;
            case 6:
                System.out.println("Masuk case 6");
                System.out.println("---Masuk di Laporan Ulasan PPT-----");
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                System.out.println(idPermohonan);
                laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(permohonan.getPermohonanSebelum().getIdPermohonan(), "PPT", "24");
                if (laporanUlasanPPT != null) {
                    syorUlasan = laporanUlasanPPT.getUlasan();
                }
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PAGE();
                //                    senaraiLaporanKandungan1 = disLaporanTanahService.getPlpservice().findUlasanByIdMohon(idPermohonan);
                break;
            case 7:
                System.out.println("Masuk case 7");
                rehydrate();
                disLaporanTanahController = (DisLaporanTanahController) getContext().getRequest().getSession().getAttribute("disLaporanTanahController");
                forwardJSP = AcqPermohonanPage.getLT_MAIN_PAGE();
                break;
            case 8:
                System.out.println("Masuk case 8");
                if (pandanganImej == 'H') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLaporTanah));
                } else if (pandanganImej == 'U') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', disLaporanTanahSempadan.getLaporanTanahSempadan());
                } else if (pandanganImej == 'S') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', disLaporanTanahSempadan.getLaporanTanahSempadan());
                } else if (pandanganImej == 'T') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', disLaporanTanahSempadan.getLaporanTanahSempadan());
                } else if (pandanganImej == 'B') {
                    hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', disLaporanTanahSempadan.getLaporanTanahSempadan());
                }
                forwardJSP = AcqPermohonanPage.getLT_IMGUTIL_PAGE();
                break;
            case 9:
                System.out.println("Masuk case 9");
                laporanUlasanPPTK = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPTK", "32");
                if (laporanUlasanPPTK != null) {
                    syorUlasanPPTK = laporanUlasanPPTK.getUlasan();
                }
//                forwardJSP = AcqPermohonanPage.getLT_SYOR_PAGE();
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PPTKANAN();
                break;
            case 10:
                senaraiUlasanJabatanTeknikal = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
                forwardJSP = AcqPermohonanPage.getLT_JTEK_PAGE();
                break;
        }
        return forwardJSP;
    }

    public String getSyorPPT() {
        String forwardJSP = new String();
        int typeNum = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 2
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 3
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 5
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 6
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 7
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 8
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 9
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 10
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 12
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 13
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 14
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 15
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 18
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 19
                : permohonan.getKodUrusan().getKod().equals("PTNB") ? 20
                : 0;


        switch (typeNum) {
            case 1: // Urusan = PPBB
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PPBB();
                break;
            case 2: // Urusan = PBPTD
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PBPTD();
                break;
            case 3: // Urusan = PBPTG
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PBPTG();
                break;
            case 4: // Urusan = LMCRG
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_LMCRG();
                break;
            case 5: // Urusan = PJLB
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PJLB();
                break;
            case 6: // Urusan = LPSP
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_LPSP();
                break;
            case 7: // Urusan = PLPS
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PLPS();
                break;
            case 8: // Urusan = PPRU
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PPRU();
                break;
            case 9: // Urusan = PPTR
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PPTR();
                break;
            case 10: // Urusan = PTGSA
                stageId = "04SediaLaporanTanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PTGSA();
                break;
            case 11: // Urusan = PRMP
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PRMP();
                break;
            case 12: // Urusan = PBMT
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PBMT();
                break;
            case 13: // Urusan = MCMCL
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_MCMCL();
                break;
            case 14: // Urusan = MMMCL
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_MMMCL();
                break;
            case 15: // Urusan = PRIZ
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PRIZ();
                break;
            case 16: // Urusan = PHLA
                stageId = "08KmsknLap";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PHLA();
                break;
            case 17: // Urusan = PBRZ
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PBRZ();
                break;
            case 18: // Urusan = PBHL
                stageId = "laporan_tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PBHL();
                break;
            case 19: // Urusan
                stageId = "Laporan_Tanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_BMBT();
                break;
            case 20: // Urusan = Pengambilan PTNB
                stageId = "06LaporanTanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PAGE();
                break;
            default:
                stageId = "03ArahLaporanTanah";
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PAGE();
                break;
        }

        return forwardJSP;
    }

    public Resolution tambahBangunanPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2BangunanPopup.jsp").addParameter("popup", "true");
    }

    public Resolution kawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2TambahKawasan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    /*
     * Author = Shazwan
     * Purpose : To Save Bangunan
     */
    public Resolution simpanBangunan() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String idLaporBangunan = ctx.getRequest().getParameter("idLaporBangunan");

        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        laporanTanah = (LaporanTanah) disLaporanTanahService.getPlpservice().findDAOByPK(laporanTanah, Long.parseLong(idLaporTanah));
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            PermohonanLaporanBangunan permohonanLaporanBangunanCRUD = new PermohonanLaporanBangunan();

            if (StringUtils.isEmpty(idLaporBangunan) || idLaporBangunan.equals("0")) {
                permohonanLaporanBangunanCRUD.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanBangunanCRUD, "new", pguna));
                permohonanLaporanBangunanCRUD.setPermohonan(permohonan);
                permohonanLaporanBangunanCRUD.setCawangan(permohonan.getCawangan());
                permohonanLaporanBangunanCRUD.setLaporanTanah(laporanTanah);
            } else {
                permohonanLaporanBangunanCRUD = (PermohonanLaporanBangunan) disLaporanTanahService.findObject(permohonanLaporanBangunanCRUD, new String[]{idLaporBangunan}, 0);
                permohonanLaporanBangunanCRUD.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanBangunanCRUD, "update", pguna));
            }

            permohonanLaporanBangunanCRUD.setKategori(kategori);
            permohonanLaporanBangunanCRUD.setJenisBangunan(permohonanLaporanBangunan.getJenisBangunan());
            if (permohonanLaporanBangunan.getUkuran() != null && !StringUtils.isEmpty(permohonanLaporanBangunan.getUkuran().toString())) {
                permohonanLaporanBangunanCRUD.setUkuran(permohonanLaporanBangunan.getUkuran());
            }
            if (permohonanLaporanBangunan.getUomUkuran() != null) {
                permohonanLaporanBangunanCRUD.setUomUkuran(disLaporanTanahService.getKodUOMDAO().findById(permohonanLaporanBangunan.getUomUkuran().getKod()));
            }
            if (permohonanLaporanBangunan.getNilai() != null && !StringUtils.isEmpty(permohonanLaporanBangunan.getNilai().toString())) {
                permohonanLaporanBangunanCRUD.setNilai(permohonanLaporanBangunan.getNilai());
            }
            if (permohonanLaporanBangunan.getTahunDibina() != null && permohonanLaporanBangunan.getTahunDibina() > 0) {
                permohonanLaporanBangunanCRUD.setTahunDibina(permohonanLaporanBangunan.getTahunDibina());
            }
            permohonanLaporanBangunanCRUD.setNamaPemunya(permohonanLaporanBangunan.getNamaPemunya());
            permohonanLaporanBangunanCRUD.setNamaKetua(permohonanLaporanBangunan.getNamaKetua());
            if (permohonanLaporanBangunan.getJenisPendudukan() != null) {
                permohonanLaporanBangunanCRUD.setJenisPendudukan((KodJenisPendudukan) disLaporanTanahService.getPlpservice().findDAOByPK(permohonanLaporanBangunan.getJenisPendudukan(), permohonanLaporanBangunan.getJenisPendudukan().getKod()));
            }
            disLaporanTanahService.getLaporanTanahService().simpanLaporanTanahBangunan(permohonanLaporanBangunanCRUD);
        }
        permohonanLaporanBangunanList = disLaporanTanahService.getLaporanTanahService().getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
        if (permohonanLaporanBangunanList.size() > 0) {
            laporanTanah.setAdaBangunan('Y');
            disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanah);
        }

        //addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(AcqPermohonanPage.getLT_KTANAH_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpanUlasan() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String type = getContext().getRequest().getParameter("type");
        String forwardJSP = new String();
        InfoAudit infoAudit;
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
//        laporanTanah = (LaporanTanah) disLaporanTanahService.getPlpservice().findDAOByPK(laporanTanah, Long.parseLong(idLaporTanah));
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);


            if (laporanTanah != null) {
//                laporanUlasan = laporanTanahService.getLaporUlasanByIdMohonidLaporan(idPermohonan, laporanTanah.getIdLaporan());
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(permohonan.getIdPermohonan(), hakmilikPermohonan.getId());
                laporanUlasanPPT = laporanTanahService.findMohonLaporUlas(idPermohonan, "PPT");
                kodPeranan = laporanTanahService.getKodPeranan("24");

                if (laporanUlasanPPT == null) {

                    laporanUlasanPPT = new PermohonanLaporanUlasan();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    laporanUlasanPPT.setInfoAudit(infoAudit);
                    laporanUlasanPPT.setPermohonan(permohonan);
                    laporanUlasanPPT.setCawangan(permohonan.getCawangan());
                    laporanUlasanPPT.setLaporanTanah(laporanTanah);
                    laporanUlasanPPT.setJenisUlasan("PPT");
                    laporanUlasanPPT.setKodPeranan(kodPeranan);

                } else {
                    infoAudit = laporanUlasanPPT.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    laporanUlasanPPT.setInfoAudit(infoAudit);
                }
                syorUlasan = (String) getContext().getRequest().getParameter("syorUlasan");
                laporanUlasanPPT.setUlasan(syorUlasan);

                laporanTanahService.simpanLaporanUlasan(laporanUlasanPPT);
                forwardJSP = refreshData("syorPPT");
            }

        }


        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
//        return new JSP(forwardJSP).addParameter("popup", "true");
        return new JSP(AcqPermohonanPage.getLT_SYOR_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpanUlasanPPTK() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        InfoAudit infoAudit;
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        String type = getContext().getRequest().getParameter("type");
        String forwardJSP = new String();
//        laporanTanah = (LaporanTanah) disLaporanTanahService.getPlpservice().findDAOByPK(laporanTanah, Long.parseLong(idLaporTanah));

        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(permohonan.getIdPermohonan(), hakmilikPermohonan.getId());


            if (laporanTanah != null) {
//                laporanUlasan = laporanTanahService.getLaporUlasanByIdMohonidLaporan(idPermohonan, laporanTanah.getIdLaporan());
//                laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPT", "228");
                laporanUlasanPPTK = laporanTanahService.findMohonLaporUlas(idPermohonan, "PPTK");
                kodPeranan = laporanTanahService.getKodPeranan("32");

                if (laporanUlasanPPTK == null) {
                    laporanUlasanPPTK = new PermohonanLaporanUlasan();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    laporanUlasanPPTK.setInfoAudit(infoAudit);
                    laporanUlasanPPTK.setPermohonan(permohonan);
                    laporanUlasanPPTK.setCawangan(permohonan.getCawangan());
                    laporanUlasanPPTK.setJenisUlasan("PPTK");
                    laporanUlasanPPTK.setKodPeranan(kodPeranan);
//                    laporanUlasanPPTK.setKodPeranan(kodPerananDAO.findById("32"));
                } else {
                    infoAudit = laporanUlasanPPTK.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    laporanUlasanPPTK.setInfoAudit(infoAudit);
                }
                syorUlasanPPTK = (String) getContext().getRequest().getParameter("syorUlasanPPTK");
                laporanUlasanPPTK.setUlasan(syorUlasanPPTK);
                laporanTanahService.simpanLaporanUlasan(laporanUlasanPPTK);
                forwardJSP = refreshData("syorPPTKanan");
            }
        }


//        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(forwardJSP).addParameter("popup", "true");

//        return new JSP(AcqPermohonanPage.getLT_SYOR_PPTKANAN()).addParameter("tab", "true");

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

        //addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(AcqPermohonanPage.getLT_KTANAH_PAGE()).addParameter("tab", "true");

    }
    
     public Resolution tambahNamaPengusahaPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        permohonanLaporanUsaha = new PermohonanLaporanUsaha();
        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2PengusahaPopup.jsp").addParameter("popup", "true");
    }
     
    public Resolution simpanKodSyaratNyata() {
        String forwardJSP = new String();
        forwardJSP = this.getSyorPPT();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKod())) {
                KodSyaratNyata kodSN = new KodSyaratNyata();
                kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{disSyaratSekatan.getKod()}, 0);
                if (kodSN != null) {
                    hakmilikPermohonanSave.setSyaratNyataBaru(kodSN);
                }
            }
        }

        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        return new JSP(forwardJSP).addParameter("tab", "true");
    }

    public Resolution simpanKodSekatan() {
        String forwardJSP = new String();
        forwardJSP = this.getSyorPPT();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSktn())) {
                KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{disSyaratSekatan.getKodSktn()}, 0);
                if (kodSK != null) {
                    hakmilikPermohonanSave.setSekatanKepentinganBaru(kodSK);
                }
            }
        }
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        return new JSP(forwardJSP).addParameter("tab", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSyaratNyata())) {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata(disSyaratSekatan.getKodSyaratNyata(), kc, disSyaratSekatan.getSyaratNyata2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSyaratNyata().size());
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata("%", kc, disSyaratSekatan.getSyaratNyata2()));
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata("%", kc, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(AcqPermohonanPage.getLT_SYARATNYATA_PAGE()).addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), kc, disSyaratSekatan.getSekatKpntgn2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(AcqPermohonanPage.getLT_SEKATAN_PAGE()).addParameter("popup", "true");
    }

    public Resolution editBangunanPopup() {
        String idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        kategori = getContext().getRequest().getParameter("kategori");
        permohonanLaporanBangunan = disLaporanTanahService.getPermohonanLaporanBangunanDAO().findById(Long.valueOf(idLaporBangunan));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2BangunanPopup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKeadaanTanah() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        LaporanTanah laporanTanahTemp = new LaporanTanah();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }

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
            try {
                hakmilikPermohonanSave.setJarak(hakmilikPermohonan.getJarak());
            } catch (Exception h) {
            }
            String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
            if (!StringUtils.isEmpty(unitJarak)) {
                hakmilikPermohonanSave.setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(unitJarak));
            }
            try {
                hakmilikPermohonanSave.setJarakDari(hakmilikPermohonan.getJarakDari());
            } catch (Exception h) {
            }
            try {
                hakmilikPermohonanSave.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
            } catch (Exception h) {
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
                }
            }

            if (!StringUtils.isEmpty(klasifikasiT)) {
                laporanTanahTemp.setStrukturTanah(disLaporanTanahService.getKodStrukturTanahDAO().findById(klasifikasiT));
            }

            laporanTanahTemp.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
            if (!StringUtils.isEmpty(keadaanTanah)) {
                laporanTanahTemp.setKodKeadaanTanah(disLaporanTanahService.getKodKeadaanTanahDAO().findById(keadaanTanah));
                // Saving into MOHON LAPOR KAND
                if (keadaanTanah.equals("LL")) {
                    permohonanLaporanKandungan = disLaporanTanahService.getPlpservice().findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
                    if (permohonanLaporanKandungan != null) {
                        permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "update", pguna));
                    } else {
                        permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                        permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "new", pguna));
                    }
                    permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
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
            if (usahaHarga != null && !StringUtils.isEmpty(usahaHarga.toString())) {
                laporanTanahTemp.setUsahaHarga(usahaHarga);
            }
            if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha().equals('Y')) {
                laporanTanahTemp.setDiusaha(laporanTanah.getDiusaha());
                if (laporanTanah.getTarikhMulaUsaha2() != null) {
                    laporanTanahTemp.setTarikhMulaUsaha2(laporanTanah.getTarikhMulaUsaha2());
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
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                laporanTanahTemp.setTarikhMulaUsaha(laporanTanah.getTarikhMulaUsaha());
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                laporanTanahTemp.setNilaiTanah(laporanTanah.getNilaiTanah());
            }

            disLaporanTanahService.getPlpservice().simpanKemaskiniLaporanTanah(laporanTanahTemp);
        }
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(AcqPermohonanPage.getLT_KTANAH_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpanImejLaporan() throws Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        String forwardJSP = new String();
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = disLaporanTanahService.getLaporanTanahDAO().findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = pengambilanService.findLaporanTanahByIdPermohonanidMH(idPermohonan, hakmilikPermohonan.getId());
            }

            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", peng));
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = disLaporanTanahService.getLaporanTanahService().simpanLaporanTanah(laporanTanah);
            }

            if (listLaporanTanah.isEmpty()) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", peng));
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = disLaporanTanahService.getLaporanTanahService().simpanLaporanTanah(laporanTanah);
            }
            String dokumenPath = disLaporanTanahService.getConf().getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null) {
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("pengambilan/melaka/LaporanTanahEdit/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("pengambilan/melaka/LaporanTanahEdit/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("pengambilan/melaka/LaporanTanahEdit/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
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
                            imejLaporan.setHakmilik(hakmilik);
                        }
                    }
                }

                disLaporanTanahService.getLaporanTanahService().simpanHakmilikImej(imejLaporan);
//                addSimpleMessage("Muat naik fail berjaya.");

                InfoAudit ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(peng);

                if (fileToBeUpload != null) {
                    try {
                        System.out.println("no null::" + fileToBeUpload.getContentType());
                        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
                        FileUtil fileUtil = new FileUtil(dokumenPath);
                        LOG.info("###### fileUtil :" + fileUtil.toString());
                        doc = disLaporanTanahService.getCommonService().saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
                        String dokumenId = String.valueOf(doc.getIdDokumen());
                        LOG.info("###### dokumenId :" + dokumenId);
                        fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
                        String fizikalPath = permohonan.getFolderDokumen().getFolderId() + File.separator + dokumenId;
                        LOG.info("###### fizikalPath :" + fizikalPath);
//                        updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan, pguna);

                        LOG.info("simpanMuatNaik::finish");
                        addSimpleMessage("Muat naik fail berjaya.");
                    } catch (Exception ex) {
                        Logger.getLogger(LaporanTanahNewDisposalActionBean.class).error(ex);
                    }
                } else {
                    addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
                }

            }
            catatan = new String();
            idLaporTanah = String.valueOf(laporanTanah.getIdLaporan());
            forwardJSP = refreshData("imgPopup");
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution reload() {
        return showFormDisplay();
    }

    public Resolution showFormDisplay() {
//        statusPage = new String();
//        display = Boolean.TRUE;
        //Purpose : TO CATER LOADING MULTIPLE HAKMILIK USING DROPDOWN LIST
        rehydrate();
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanTanahController = new DisLaporanTanahController();
        int typeNum = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 2
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 3
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 5
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 6
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 7
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 8
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 9
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 10
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 12
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 13
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 14
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 15
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 18
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 19
                : permohonan.getKodUrusan().getKod().equals("PTNB") ? 20
                : 0;


        switch (typeNum) {
            case 1: // Urusan = PPBB
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 2: // Urusan = PBPTD
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }

                break;
            case 3: // Urusan = PBPTG
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 4: // Urusan = LMCRG
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 5: // Urusan = PJLB
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 6: // Urusan = LPSP
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 7: // Urusan = PLPS
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 8: // Urusan = PPRU
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 9: // Urusan = PPTR
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 10: // Urusan = PTGSA
                if (stageId.equalsIgnoreCase("04SediaLaporanTanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("05SemakLaporan")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 11: // Urusan = PRMP
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 12: // Urusan = PBMT
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 13: // Urusan = MCMCL
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 14: // Urusan = MMMCL
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 15: // Urusan = PRIZ
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPTKanan();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                } else {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }

//                if (stageId.equalsIgnoreCase("laporan_tanah")) {
//                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
//                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
//                }
                break;
            case 16: // Urusan = PHLA
                if (stageId.equalsIgnoreCase("08KmsknLap")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 17: // Urusan = PBRZ
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 18: // Urusan = PBHL
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 19: // Urusan = BMBT
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            case 20: // Urusan = PTNB
                stageId = "06LaporanTanah";
                if (stageId.equalsIgnoreCase("06LaporanTanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanahPPT();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
            default:
                stageId = "03ArahLaporanTanah";
                if (stageId.equalsIgnoreCase("03ArahLaporanTanah")) {
                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
                }
                break;
        }
        return new JSP(AcqPermohonanPage.getLT_MAIN_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanBersempadanan", "!showFormPopUp", "!simpanKandungan", "!simpanPerihal", "!uploadForm", "!simpanKeadaanTanah", "!deleteRow", "!deleteImage", "!simpanImejLaporan"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonanLaporanPelan = new PermohonanLaporanPelan();
        laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(permohonan.getPermohonanSebelum().getIdPermohonan(), "PPT", "24");
        if (laporanUlasanPPT != null) {
            syorUlasan = laporanUlasanPPT.getUlasan();
        }
        laporanUlasanPPTK = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPTK", "32");
        if (laporanUlasanPPTK != null) {
            syorUlasanPPTK = laporanUlasanPPTK.getUlasan();
        }
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //Add for charting
        /*
         * Perihal Tanah
         */
        hakmilikImejLaporanList = new ArrayList<ImejLaporan>();

        if (idPermohonan != null) {

            int syx = 0;
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan != null) {
                catatan = new String();
                catatan = permohonan.getSebab();
            }
            /*
             * SENARAI HAKMILIK
             */
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//              senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else {
                senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
            }

            if (!StringUtils.isEmpty(idHakmilik)) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                if (senaraiHakmilikPermohonan.size() > 0) {
                    hakmilikPermohonanList = new ArrayList();
                    for (HakmilikPermohonan mohonHM : senaraiHakmilikPermohonan) {
                        if (mohonHM.getHakmilik() != null) {
                            if (mohonHM.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                                DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();
                                disMohonHM.setHakmilikPermohonan(mohonHM);
                                hakmilikPermohonanList.add(disMohonHM);
                                break;
                            }
                        }

                    }
                }
                permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(idPermohonan);
//                permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan}, 0);
//                permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);
            } else {
                if (senaraiHakmilikPermohonan.size() > 0) {
                    hakmilikPermohonanList = new ArrayList();
                    HakmilikPermohonan mohonHM = new HakmilikPermohonan();
                    DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

                    mohonHM = permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0);
                    disMohonHM.setHakmilikPermohonan(mohonHM);
                    hakmilikPermohonanList.add(disMohonHM);
                    if (disMohonHM.getHakmilikPermohonan().getHakmilik() != null) {
                        idHakmilik = disMohonHM.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                        hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
                        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(idPermohonan);
//                        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan}, 0);
//                        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);
                    } else {
                        if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                        } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                        } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                        } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                            HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                            hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
                            if (hakmilikPermohonan != null) {
                                hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "update", pguna));
                            } else {
                                hakmilikPermohonan = new HakmilikPermohonan();
                                hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "new", pguna));
                                hakmilikPermohonan.setPermohonan(permohonan);
                            }
                            hakmilikPermohonan.setHakmilik(hakmilikPermohonanSblm.getHakmilik());
                            disLaporanTanahService.getPlpservice().saveOrUpdate(hakmilikPermohonan);
                        } else {
                            hakmilikPermohonan = new HakmilikPermohonan();
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                        }
//                        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan}, 0);
                        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(idPermohonan);
                    }
                } else {
                    if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                        HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                        hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
                        if (hakmilikPermohonan != null) {
                            hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "update", pguna));
                        } else {
                            hakmilikPermohonan = new HakmilikPermohonan();
                            hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "new", pguna));
                            hakmilikPermohonan.setPermohonan(permohonan);
                        }
                        hakmilikPermohonan.setHakmilik(hakmilikPermohonanSblm.getHakmilik());
                        disLaporanTanahService.getPlpservice().saveOrUpdate(hakmilikPermohonan);
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                        if (hakmilikPermohonan == null) {
                            hakmilikPermohonan = new HakmilikPermohonan();
                        }
                    }
//                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan}, 0);
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(idPermohonan);
                }
            }
            if (hakmilikPermohonan != null) {
                if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                    syx = hakmilikPermohonan.getBandarPekanMukimBaru().getKod();
                    kodSeksyenList = disLaporanTanahService.getPlpservice().getSenaraiKodSeksyenByBPM(syx);
                }
            }

            /*
             *  DALAM KAWASAN
             */
            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                if (hp != null) {
                    senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
//                    senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNidMH(idPermohonan, hp.getId());
                } else {
                    senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNidMH(idPermohonan, hp.getId());
//                    senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
                }
            }

            /*
             * PERIHAL SYOR
             */
            catatanKeg = permohonan.getSebab() != null && !("").equals(permohonan.getSebab()) ? permohonan.getSebab() : new String();

            /*
             * LAPORAN TANAH
             */
            if (hakmilikPermohonan != null) {
                laporanTanah = new LaporanTanah();
                laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
            } else {
                laporanTanah = new LaporanTanah();
                laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{idPermohonan}, 1);
            }

            if (laporanTanah != null) {
                /*
                 * UPLOAD IMAGE
                 */
                // imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                // utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                // selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                // timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                // baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
                listPermohonanLaporanUsaha = disLaporanTanahService.getPelupusanService().findListMohonLaporUsahaByIdLaporan(laporanTanah.getIdLaporan());
                if (listPermohonanLaporanUsaha.size() > 0) {
                    permohonanLaporanUsaha = listPermohonanLaporanUsaha.get(0);
                }
            } else {
                laporanTanah = new LaporanTanah();
            }

            /*
             * MOHON FASA
             */
            List<FasaPermohonan> listFasa;
//            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            //REMOVE 02SediaLaporan
//            stageId = "semak_laporan_tanah";
            listFasa = permohonan.getSenaraiFasa();
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
                    if (fp.getIdAliran().equals("laporan_tanah") || fp.getIdAliran().equals("04SediaLaporanTanah") || fp.getIdAliran().equals("04SediaLaporan")) {
                        fasaPermohonan = listFasa.get(i);
                        //ulasan = fasaPermohonan.getUlasan() ; DISABLED SINCE USING PTLPULASAN1
                        if (fasaPermohonan.getUlasan() != null) {
                            ulasan = fasaPermohonan.getUlasan();
                        }
                        if (fasaPermohonan.getKeputusan() != null) {
                            ksn = fasaPermohonan.getKeputusan().getKod();
                        }
                    }
                }
            }

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

            disLaporanTanahSempadan.setuSize(listLaporTanahSpdnTemp.size());
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
            }
            disLaporanTanahSempadan.setsSize(listLaporTanahSpdnTemp.size());
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
            }
            disLaporanTanahSempadan.settSize(listLaporTanahSpdnTemp.size());
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
            }
            disLaporanTanahSempadan.setbSize(listLaporTanahSpdnTemp.size());
            /*
             * END
             */
        }
        tanahrizabpermohonan1 = new TanahRizabPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{idPermohonan, hakmilikPermohonan.getHakmilik().getNoHakmilik()}, 0);
        } else {
            tanahrizabpermohonan1 = (TanahRizabPermohonan) disLaporanTanahService.findObject(tanahrizabpermohonan1, new String[]{idPermohonan}, 1);
        }

        if (tanahrizabpermohonan1 != null) {
            if (!StringUtils.isBlank(tanahrizabpermohonan1.getPenjaga())) {
                statBdnPngwl = tanahrizabpermohonan1.getPenjaga().equals("SUK Negeri") ? "1"
                        : tanahrizabpermohonan1.getPenjaga().equals("Pesuruhjaya Tanah Persekutuan") ? "2"
                        : !tanahrizabpermohonan1.getPenjaga().equals("SUK Negeri") && !tanahrizabpermohonan1.getPenjaga().equals("Pesuruhjaya Tanah Persekutuan") ? "3"
                        : "0";
            } else {
                statBdnPngwl = "0";
            }
        }
        permohonanManualList = disLaporanTanahService.getPlpservice().findByIdMohonlist(idPermohonan);
        PermohonanLaporanKandungan permohonanLaporanKandungan = new PermohonanLaporanKandungan();
        permohonanLaporanKandungan = (PermohonanLaporanKandungan) disLaporanTanahService.findObject(permohonanLaporanKandungan, new String[]{"Syarat Tambahan LPS", String.valueOf(laporanTanah.getIdLaporan())}, 0);

        if (permohonanLaporanKandungan != null) {
            ulsn = permohonanLaporanKandungan.getKand();
        }


        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilik() != null)) {
            kodHmlk = hakmilikPermohonan.getKodHakmilik().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilikSementara() != null)) {
            kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilikTetap() != null)) {
            kodHmlkTetap = hakmilikPermohonan.getKodHakmilikTetap().getKod();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (!StringUtils.isEmpty(ksn)) {
                if (ksn.equals("SL")) {
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasLulusUom() != null)) {
                        kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
                    }
                }
            }
        } else {
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasLulusUom() != null)) {
                kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
            }
        }

        if ((laporanTanah != null) && (laporanTanah.getKodKeadaanTanah() != null)) {
            keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
        }


        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium() != null)) {
            keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();

        }

        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getHakmilik() == null) {
                if (permohonan.getKodUrusan().getKod().equals("PRMP") || permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                    // kodP = 'H';
                    if (hakmilikPermohonan.getKodMilik() != null) {
                        kodP = hakmilikPermohonan.getKodMilik().getKod();
                    }
                } else {
                    if (hakmilikPermohonan.getKodMilik() != null) {
                        kodP = hakmilikPermohonan.getKodMilik().getKod();
                    }
                }
            } else {
                if (hakmilikPermohonan.getKodMilik() != null) {
                    kodP = hakmilikPermohonan.getKodMilik().getKod();
                } else {
                    kodP = 'H';
                }
            }
        }

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
            kodD = hakmilikPermohonan.getKodDUN().getKod();
        }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
            kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
        }

        /*
         * MOHON BAHAN BATU
         * PPBB, PBPTD, PBPTG ,LPSP
         */
        if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
            disPermohonanBahanBatu = new DisPermohonanBahanBatu();
            disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
            //FOR JUMLAH KENE BAYAR IN SYARAT-SYARAT
            if (hakmilikPermohonan.getKodMilik().getKod().equals('H')) {
                if (disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BG") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("PS")
                        || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TL") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("RP")
                        || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TM") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BT")) {
                    if (disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu() != null) {
                        disPermohonanBahanBatu.setJumlahKeneBayar(disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getRoyaltiTanahMilik().multiply(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu()).toString());
                        disPermohonanBahanBatu.setCagarKeneBayar((0.2) * Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()));
                    }
                }
            }
            if (hakmilikPermohonan.getKodMilik().getKod().equals('K')) {
                if (disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BG") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("PS")
                        || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TL") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("RP")
                        || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TM") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BT")) {
                    if (disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu() != null) {
                        disPermohonanBahanBatu.setJumlahKeneBayar(disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getRoyaltiTanahKerajaan().multiply(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu()).toString());
                        disPermohonanBahanBatu.setCagarKeneBayar(0.2 * Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()));
                    }
                }
            }
            PermohonanTuntutanKos mohonTuntutKosA = new PermohonanTuntutanKos();
            mohonTuntutKosA = (PermohonanTuntutanKos) disLaporanTanahService.findObject(mohonTuntutKosA, new String[]{idPermohonan, "DISKD"}, 0);
            if (mohonTuntutKosA != null) {
                disPermohonanBahanBatu.setKuponQty(mohonTuntutKosA.getKuantiti());
                disPermohonanBahanBatu.setKuponAmaun(50.00);
                disPermohonanBahanBatu.setKupon(BigDecimal.valueOf(Double.parseDouble(String.valueOf(disPermohonanBahanBatu.getKuponQty())) * disPermohonanBahanBatu.getKuponAmaun()));

            } else {
                disPermohonanBahanBatu.setKuponAmaun(50.00);
            }

            PermohonanTuntutanKos mohonTuntutKosB = new PermohonanTuntutanKos();
            mohonTuntutKosB = (PermohonanTuntutanKos) disLaporanTanahService.findObject(mohonTuntutKosB, new String[]{idPermohonan, "DISCJ"}, 0);
            if (mohonTuntutKosB != null) {
                disPermohonanBahanBatu.setCagarJalan(mohonTuntutKosB.getAmaunTuntutan());
            }
            //JUMLAH KESELURUHAN
            if (StringUtils.isEmpty(String.valueOf(disPermohonanBahanBatu.getCagarKeneBayar()))) {
                disPermohonanBahanBatu.setCagarKeneBayar(0.00);
            }
            if (disPermohonanBahanBatu.getCagarJalan() != null) {
                if (StringUtils.isEmpty(disPermohonanBahanBatu.getCagarJalan().toString())) {
                    disPermohonanBahanBatu.setCagarJalan(new BigDecimal(0));
                }
            } else {
                disPermohonanBahanBatu.setCagarJalan(new BigDecimal(0));
            }
            if (disPermohonanBahanBatu.getKupon() != null) {
                if (StringUtils.isEmpty(disPermohonanBahanBatu.getKupon().toString())) {
                    disPermohonanBahanBatu.setKupon(new BigDecimal(0));
                }
            } else {
                disPermohonanBahanBatu.setKupon(new BigDecimal(0));
            }

            BigDecimal cagaran = new BigDecimal(0);
//            double percentDouble = 20 / 100;
//            BigDecimal percent = BigDecimal.valueOf(percentDouble);
//            cagaran = BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar()).multiply(percent);
            cagaran = BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar());
//            disPermohonanBahanBatu.setTotalAll(disPermohonanBahanBatu.getCagarJalan().add(BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar())).add(disPermohonanBahanBatu.getKupon()).add(cagaran));
            if (disPermohonanBahanBatu.getJumlahKeneBayar() != null) {
                disPermohonanBahanBatu.setTotalAll(disPermohonanBahanBatu.getCagarJalan().add(BigDecimal.valueOf(Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()))).add(disPermohonanBahanBatu.getKupon()).add(cagaran));
            }
        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
            disPermohonanBahanBatu = new DisPermohonanBahanBatu();
            disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
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
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("LPSP")) {
            permohonanPermitItem = new PermohonanPermitItem();
            if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                List<PermohonanPermitItem> senaraiPermohonanPermitItem = new ArrayList<PermohonanPermitItem>();
                senaraiPermohonanPermitItem = disLaporanTanahService.getPelupusanService().findPermohonanPermitItemByIdMohonList(idPermohonan);
                keg = new String();
                for (PermohonanPermitItem ppi : senaraiPermohonanPermitItem) {
                    if (ppi.getKodItemPermit().getKod().equals("LN")) {
                        keg = ppi.getKodItemPermit().getKod();

                    }
                }
            } else {
                permohonanPermitItem = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItem, new String[]{idPermohonan}, 0);
                if (permohonanPermitItem != null) {
                    if ((permohonanPermitItem != null) && (permohonanPermitItem.getKodItemPermit() != null)) {
                        keg = permohonanPermitItem.getKodItemPermit().getKod();
                    }
                } else {
                    permohonanPermitItem = new PermohonanPermitItem();
                }
            }


        }
        // Changes for LPSP - For Bayaran LPS
        permohonanTuntutanKos = new PermohonanTuntutanKos();
        if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
            permohonanTuntutanKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
            if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
                amnt = permohonanTuntutanKos.getAmaunTuntutan();
                if ((amnt != null) && (disPermohonanBahanBatu.getTotalAll() != null)) {
                    disPermohonanBahanBatu.setTotalLPSdanPermit(disPermohonanBahanBatu.getTotalAll().add(amnt));
                }
            }
        } else {
            permohonanTuntutanKos = (PermohonanTuntutanKos) disLaporanTanahService.findObject(permohonanTuntutanKos, new String[]{idPermohonan}, 1);
            if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
                amnt = permohonanTuntutanKos.getAmaunTuntutan();
            }
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

        /*
         * SYARAT NYATA DAN SEKATAN KEPENTINGAN
         */
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getSyaratNyataBaru() != null)) {
            String kod1 = (hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            syaratNyata = "" + kod1;
        } else {
            kod = "";
        }

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getSekatanKepentinganBaru() != null)) {
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            String kod2 = (hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
            syaratNyata1 = "" + kod2;
        } else {
            kodSktn = "";
        }
        if (laporanTanah != null) {
            if (laporanTanah.getAdaBangunan() != null && laporanTanah.getAdaBangunan() == 'Y') {
                permohonanLaporanBangunanList = disLaporanTanahService.getLaporanTanahService().getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
            }
        }
        if (!permohonan.getKodUrusan().getKod().equals("PTNB")) {
            senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        }
        if (laporanTanah != null) {
            permohonanLaporanKandungan = new PermohonanLaporanKandungan();
            permohonanLaporanKandungan = (PermohonanLaporanKandungan) disLaporanTanahService.findObject(permohonanLaporanKandungan, new String[]{"Lain-lain Jenis Tanah", String.valueOf(laporanTanah.getIdLaporan())}, 0);
        }

        if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
            kand = permohonanLaporanKandungan.getKand();
        }

        if (laporanTanah != null) {
            permohonanLaporanKandungan = new PermohonanLaporanKandungan();
            permohonanLaporanKandungan = (PermohonanLaporanKandungan) disLaporanTanahService.findObject(permohonanLaporanKandungan, new String[]{"Lain-lain Keadaan Tanah", String.valueOf(laporanTanah.getIdLaporan())}, 0);
        }

        if ((laporanTanah != null) && (permohonanLaporanKandungan != null)) {
            keadaankand = permohonanLaporanKandungan.getKand();
        }

        if (ksn != null) {

            senaraiLaporanKandungan1 = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "Ulasan PPT");

        } else {
            senaraiLaporanKandungan1 = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "Ulasan PPT");
        }

        if (permohonan.getKodUrusan().getKod().equals("PTNB")) {
            laporanUlasanPPT = laporanTanahService.getLaporUlasan(idPermohonan, "PPT");
            laporanUlasanPPTK = laporanTanahService.getLaporUlasan(idPermohonan, "PPTK");

            if (laporanUlasanPPT != null) {
                syorUlasan = laporanUlasanPPT.getUlasan();
                LOG.info("Laporan Ulasan PPT" + syorUlasan);
            }
            if (laporanUlasanPPTK != null) {
                syorUlasanPPTK = laporanUlasanPPTK.getUlasan();
                LOG.info("Laporan Ulasan PPT" + syorUlasanPPTK);
            }

        } else {
            senaraiLaporanKandunganPPTKanan = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "PPTKanan");
        }
//
//        PermohonanLaporanUlasan permohonanLaporanUlasan = new PermohonanLaporanUlasan();
//        permohonanLaporanUlasan = (PermohonanLaporanUlasan) disLaporanTanahService.findObject(permohonanLaporanUlasan, new String[]{idPermohonan, "UlsanPTKnn"}, 1);
//
//        if (permohonanLaporanUlasan != null) {
//            ulasanKanan = permohonanLaporanUlasan.getUlasan();
//        }
//
//        if (!(senaraiLaporanKandungan1.isEmpty())) {
//            PermohonanLaporanUlasan permohonanLaporanUlasan1 = new PermohonanLaporanUlasan();
//            permohonanLaporanUlasan1 = senaraiLaporanKandungan1.get(0);
//            plpulasan0 = permohonanLaporanUlasan1.getUlasan();
//        }

//        senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
//        List<PermohonanLaporanUlasan> permohonanLaporanUlasan_ref = new ArrayList<PermohonanLaporanUlasan>();
//        permohonanLaporanUlasan_ref = disLaporanTanahService.getPlpservice().findUlasan(idPermohonan, "Syarat4A");
        // System.out.println("----------permohonanLaporanUlasan_ref out-----------"+permohonanLaporanUlasan_ref.size());
        // if(permohonanLaporanUlasan_ref.size() > 0){
        // System.out.println("----------permohonanLaporanUlasan_ref in-----------"+permohonanLaporanUlasan_ref.size());
        // pasId = "1";
        // }
//        senaraiLaporanKandungan1 = permohonanLaporanUlasan_ref;

        /*
         * NO_PT
         */
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
            noPT = new NoPt();
            noPT = (NoPt) disLaporanTanahService.findObject(noPT, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);

            if (noPT != null) {
                if (noPT.getKodUOM() != null) {
                    kodSU = noPT.getKodUOM().getKod();
                }
                if (noPT.getLuasDilulus() != null) {
                    luasDilulus = noPT.getLuasDilulus();
                }
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
            permohonanBahanBatuan = (PermohonanBahanBatuan) disLaporanTanahService.findObject(permohonanBahanBatuan, new String[]{idPermohonan}, 0);
            if (permohonanBahanBatuan != null && permohonanBahanBatuan.getTempohSyorUom() != null) {
                tempohSyorUOM = permohonanBahanBatuan.getTempohSyorUom().getKod();
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
            pmi = new PermohonanPermitItem();
            pmi = (PermohonanPermitItem) disLaporanTanahService.findObject(pmi, new String[]{idPermohonan}, 0);
        }

        /*
         * PERIHAL TANAH
         */
        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getKodMilik() != null) {
                if (hakmilikPermohonan.getKodMilik().getKod().equals('H')) {
                    senaraiHakmilikPerihalTanah = !permohonan.getKodUrusan().getKod().equals("PHLP") ? permohonan.getSenaraiHakmilik() : hakmilikPermohonanList;
                }
            }
        }
        senaraiUlasanJabatanTeknikal = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
//        laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPT", "24");
//            syorUlasan = laporanUlasanPPT.getUlasan();
//
//            System.out.println("------------getUlasan-----------" + laporanUlasanPPT.getUlasan());
//            System.out.println("------------syorUlasan------------" + syorUlasan);
    }

    public Resolution showFormPopUp() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
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
                    disLaporanTanahSempadan.setJarakDariTanahUOM(disLaporanTanahSempadan.getLaporanTanahSempadan().getJarakUom().getKod());
                }
                disLaporanTanahSempadan.setNoLot(disLaporanTanahSempadan.getLaporanTanahSempadan().getNoLot());
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

        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddEditLotSmpdn.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskiniKandunganSempadan() throws ParseException {
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        String kegunaanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.kegunaan");
        String idHakmilikSmpdn = getContext().getRequest().getParameter("disLaporanTanahSempadan.idHakmilikSmpdn");
        String keadaanTanahSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.keadaanTanah");
        String catatanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.catatan");
        String milikKerajaanSempadan = getContext().getRequest().getParameter("disLaporanTanahSempadan.milikKerajaan");
        String jarakDariTanah = getContext().getRequest().getParameter("disLaporanTanahSempadan.jarakDariTanah");
        String jarakDariTanahUOM = getContext().getRequest().getParameter("disLaporanTanahSempadan.jarakDariTanahUOM");
        String kodLot = getContext().getRequest().getParameter("disLaporanTanahSempadan.kodLot");
        String noLot = getContext().getRequest().getParameter("disLaporanTanahSempadan.noLot");

        LaporanTanahSempadan lts = new LaporanTanahSempadan();
        lts = disLaporanTanahService.getPlpservice().findLaporanTanahSempadanById(Long.parseLong(idKandungan));
        lts.setCatatan(catatanSempadan);
        lts.setKeadaanTanah(keadaanTanahSempadan);
        lts.setGuna(kegunaanSempadan);
        lts.setMilikKerajaan(milikKerajaanSempadan);
        lts.setNoLot(noLot);
        if (!jarakDariTanah.equals("")) {
            lts.setJarak(new BigDecimal(jarakDariTanah));
        }
        KodUOM kodUOM = new KodUOM();
        kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{jarakDariTanahUOM}, 0);
        lts.setJarakUom(kodUOM);
        KodLot kodLotx = new KodLot();
        kodLotx = (KodLot) disLaporanTanahService.findObject(kodLotx, new String[]{kodLot}, 0);
        lts.setKodLot(kodLotx);
        disLaporanTanahService.getPlpservice().saveOrUpdateSempadan(lts);

        return new JSP(AcqPermohonanPage.getLT_LSEMPADAN_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanKandunganSempadan() throws ParseException {

        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
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
        if (!StringUtils.isBlank(typeIndex));
        numType = typeIndex.equals("U") ? 1 : typeIndex.equals("B") ? 2 : typeIndex.equals("T") ? 3 : typeIndex.equals("S") ? 4 : 0;

        String[] data = {idHakmilikSempadan,
            kegunaanSempadan,
            keadaanTanahSempadan,
            catatanSempadan,
            milikKerajaanSempadan,
            jarakDariTanah,
            jarakDariTanahUOM,
            kodLot,
            noLot,
            idKandungan};
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
        return new JSP(AcqPermohonanPage.getLT_LSEMPADAN_PAGE()).addParameter("tab", "true");
    }

    public void updateKandungan(String jnsSmpdn, String[] data) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());

        Hakmilik hakmilikSmpdn = new Hakmilik();
        LOG.info("data length >> " + data.length);
        if (data.length > 0) {
            LOG.debug("data[9] >>> " + data[9]);
            if (data[9] != null) {
                data[0] = data[9];
            }
            try {
                hakmilikSmpdn = disLaporanTanahService.getHakmilikDAO().findById(data[0]);
            } catch (Exception hj) {
            }
            LaporanTanah lt = new LaporanTanah();
//            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
            if (lt == null) {
                lt = new LaporanTanah();
                lt.setInfoAudit(disLaporanTanahService.findAudit(lt, "new", pengguna));
                lt.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().simpanLaporanTanah(lt);
                lt = new LaporanTanah();
                lt = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
            }
            LOG.info("lt >>>>> " + lt.getKeadaanTanah());
            if (lt != null) {
                LaporanTanahSempadan lts = new LaporanTanahSempadan();
                KodUOM kodUOM = new KodUOM();
                KodLot kodLot = new KodLot();
                if (StringUtils.isNotBlank(disLaporanTanahSempadan.getStatusSempadan()) && (disLaporanTanahSempadan.getStatusSempadan().equals("U"))) {
                    lts = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                    if (lts != null) {

                        lts.setInfoAudit(disLaporanTanahService.findAudit(lt, "update", pengguna));
                        lts.setLaporanTanah(lt);
                        lts.setHakmilik(hakmilikSmpdn != null ? hakmilikSmpdn : new Hakmilik());
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
                } else {
                    List<LaporanTanahSempadan> listLTS = disLaporanTanahService.getPlpservice().findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(), data[0]);
                    if (listLTS.size() > 0) {
                        addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                    } else {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL")) {
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
                            if (hakmilikSmpdn != null || !StringUtils.isEmpty(data[8])) {
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
                            } else if (data[4].equalsIgnoreCase("Y")) {
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
                            } else if (data[4].equalsIgnoreCase("R")) {
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
                                addSimpleError("SILA PASTIKAN ID HAKMILIK DIISI LENGKAP / NO LOT TELAH DIISI");
                            }
                        }
                    }
                }
//                else {
//                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
//                }
            }
        }
    }

    public Resolution uploadDoc() {
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
        String idLapor = getContext().getRequest().getParameter("idLapor");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        laporanTanah = new LaporanTanah();
        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{idLapor}, 2);
        if (pandanganImej == 'H') {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLapor));
        } else {
            String idlaporTnhSmpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
            LaporanTanahSempadan lts = new LaporanTanahSempadan();
            lts = (LaporanTanahSempadan) disLaporanTanahService.findObject(lts, new String[]{idlaporTnhSmpdn}, 1);
            disLaporanTanahSempadan.setLaporanTanahSempadan(lts);
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
        return new JSP(AcqPermohonanPage.getLT_IMGUTIL_PAGE()).addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        //FOR UPLOAD IMAGE
        String typeImage = ctx.getRequest().getParameter("pandanganImej");
        idLaporTanah = ctx.getRequest().getParameter("idLapor");
        if (typeImage != null && !typeImage.isEmpty()) {
            pandanganImej = typeImage.charAt(0);
        }

        //END
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution simpanPerihal() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
        }
        PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
        if (StringUtils.isEmpty(idHakmilik)) {
            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan}, 0);
        } else {
            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.getPlpservice().findByIdPermohonanPelan1(idPermohonan);
//            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan}, 0);
//            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan, idHakmilik}, 1);
        }
        /*
         * MOHON TANAH RIZAB
         */
        TanahRizabPermohonan mohonTanahRizab = new TanahRizabPermohonan();
        if (StringUtils.isNotBlank(tanahR)) {
            if (!StringUtils.isEmpty(idHakmilik)) {
                mohonTanahRizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(mohonTanahRizab, new String[]{idPermohonan, mohonHakmilik.getHakmilik().getNoHakmilik()}, 0);
            } else {
                mohonTanahRizab = (TanahRizabPermohonan) disLaporanTanahService.findObject(mohonTanahRizab, new String[]{idPermohonan}, 1);
            }
            if (mohonTanahRizab != null) {
                mohonTanahRizab.setInfoAudit(disLaporanTanahService.findAudit(mohonTanahRizab, "update", pguna));
            } else {
                mohonTanahRizab = new TanahRizabPermohonan();
                mohonTanahRizab.setInfoAudit(disLaporanTanahService.findAudit(mohonTanahRizab, "new", pguna));
                mohonTanahRizab.setPermohonan(mohonHakmilik.getPermohonan());
                if (mohonHakmilik.getHakmilik() != null) {
                    mohonTanahRizab.setCawangan(mohonHakmilik.getHakmilik().getCawangan());
                    mohonTanahRizab.setDaerah(mohonHakmilik.getHakmilik().getDaerah());
                    mohonTanahRizab.setNoHakmilik(mohonHakmilik.getHakmilik().getNoHakmilik());
                    mohonTanahRizab.setBandarPekanMukim(mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    mohonTanahRizab.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                } else {
                    mohonTanahRizab.setCawangan(mohonHakmilik.getPermohonan().getCawangan());
                    mohonTanahRizab.setDaerah(mohonHakmilik.getPermohonan().getCawangan().getDaerah());
                    mohonTanahRizab.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
                    mohonTanahRizab.setNoLot(mohonHakmilik.getNoLot());
                }


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
                    disLaporanTanahService.getPlpservice().simpanTanahRizabPermohonan(mohonTanahRizab);
                }
            }
        }

        /*
         * MOHON LAPOR PELAN
         */
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
            if (kodT.equalsIgnoreCase("99")) {
                permohonanLaporanKandungan = (PermohonanLaporanKandungan) disLaporanTanahService.findObject(permohonanLaporanKandungan, new String[]{"Lain-lain Jenis Tanah", String.valueOf(laporanTanah.getIdLaporan())}, 0);
                if (permohonanLaporanKandungan != null) {
                    permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "update", pguna));
                } else {
                    permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                    permohonanLaporanKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanKandungan, "new", pguna));
                }
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Jenis Tanah");
                permohonanLaporanKandungan.setKand(kand);
                disLaporanTanahService.getPlpservice().simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
            }

            disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);
        }


        if (mohonHakmilik != null) {
            if (laporanTanah != null) {
                /*
                 * LAPORAN TANAH
                 */
                LaporanTanah laporanTanahTemp = new LaporanTanah();
                laporanTanahTemp = (LaporanTanah) disLaporanTanahService.findObject(laporanTanahTemp, new String[]{String.valueOf(mohonHakmilik.getId())}, 0);
                if (laporanTanahTemp != null) {
                    if (StringUtils.isNotBlank(laporanTanah.getBolehBerimilik())) {
                        laporanTanahTemp.setBolehBerimilik(laporanTanah.getBolehBerimilik());
                        if (laporanTanah.getBolehBerimilik().equalsIgnoreCase("T")) {
                            laporanTanahTemp.setSebabTidakBolehBerimilik(laporanTanah.getSebabTidakBolehBerimilik());
                        }
                    }
                    disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanahTemp);
                }
            }


            /*
             * HAKMILIKPERMOHONAN
             */
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
            if (kodD != null) {
                KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                mohonHakmilik.setKodDUN(kd);
            }
            if (hakmilikPermohonan != null && !hakmilikPermohonan.getLokasi().isEmpty()) {
                mohonHakmilik.setLokasi(hakmilikPermohonan.getLokasi());
            }
            mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "update", pguna));
            disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilik);
        }

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(AcqPermohonanPage.getLT_PTANAH_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpanBersempadanan() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        LaporanTanah laporanTanahTemp = new LaporanTanah();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
        }

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
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(AcqPermohonanPage.getLT_SEMPADAN_PAGE()).addParameter("tab", "true");

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
        return new JSP(AcqPermohonanPage.getLT_PTANAH_PAGE()).addParameter("tab", "true");

    }

    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
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
            return new JSP(AcqPermohonanPage.getLT_PTANAH_PAGE()).addParameter("popup", "true");
        } else {
            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
                hakmilikPermohonan = disLaporanTanahService.getPlpservice().findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                    List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                    senaraiHakmilikTerlibat = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                    if (senaraiHakmilikTerlibat.size() > 0) {
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                    }
                } else {
                    hakmilikPermohonan = disLaporanTanahService.getPlpservice().findByIdPermohonan(idPermohonan);
                }
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

        if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
            hakmilikPermohonanList = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
        } else {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP(AcqPermohonanPage.getLT_PTANAH_PAGE()).addParameter("tab", "true");
    }

    public Resolution reset() {
        kegunaan = null;
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddEditLotSmpdn.jsp");
    }

    public Resolution deleteImage() {
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");

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
                forwardJSP = refreshData(type);

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

        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
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
        return new JSP("pelupusan/laporan_tanah/laporan_tanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution permohonanTerdahuluPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pengambilan/melaka/LaporanTanahEdit/carian_permohonan_terdahulu.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution simpanKawasan() throws ParseException {
        PermohonanLaporanKawasan kws = new PermohonanLaporanKawasan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
        }
        kws.setInfoAudit(disLaporanTanahService.findAudit(kws, "new", pguna));
//        KodRizab kr = new KodRizab();
        KodRizab kr = disLaporanTanahService.getKodRizabDAO().findById(Integer.parseInt(disLaporTanahKawasan.getStringRizab()));
        if (kr != null) {
            kws.setKodRizab(kr);
        }
        if (disLaporTanahKawasan.getStringRizab().equals("99")) {
            kws.setCatatan(disLaporTanahKawasan.getCatatan());
        }
        kws.setNoWarta(disLaporTanahKawasan.getNoWarta());
        if (disLaporTanahKawasan.getTarikhWarta() != null && !("").equals(disLaporTanahKawasan.getTarikhWarta())) {
            kws.setTarikhWarta(sdf.parse(disLaporTanahKawasan.getTarikhWarta()));
        }
        kws.setNoPelanWarta(disLaporTanahKawasan.getPelanWarta());
        kws.setPermohonan(permohonan);
        kws.setKodCawangan(permohonan.getCawangan());
        kws.setHakmilikPermohonan(mohonHakmilik);
        disLaporanTanahService.getPlpservice().simpanPermohonanLaporKawasan(kws);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        return new JSP(AcqPermohonanPage.getLT_DKAWASAN_PAGE()).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandungan() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        int index = 0;
        boolean updateDB = false;
        boolean updateDB2 = false;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        String forwardJSP = new String();

        switch (index) {
            case 1:

                break;
            case 5:// MOHON LAPOR ULAS
                forwardJSP = this.getSyorPPT();
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
                } else if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    updateMohonTuntutKos();
                } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn.equals("SU")) {
                        updateMohonPermitItem();
                    }
                    updateMohonTuntutKos();
                    updateNoPT();
                    updateDB = updateKandunganUlas(ulsn, "PLPSSyarat");
                } else if (permohonan.getKodUrusan().getKod().equals("MCMCL")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PRIZ")) {
                    updateDB2 = updateMohonFasa();
                    updateMohonHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PBRZ")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PBHL")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
                    updateDB2 = updateMohonFasa();
                } else if (permohonan.getKodUrusan().getKod().equals("PTNB")) {
                    updateDB2 = updateMohonFasa();
                }

                updateDB = updateKandunganUlas(kand, "PPT");
                break;
            case 6:
                forwardJSP = AcqPermohonanPage.getLT_SYOR_PPTKANAN();
                updateDB = updateKandunganUlas(kand, "PPTKanan");
                break;
            default:
                break;
        }
//        forwardJSP = refreshData("syorPPT");
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP(forwardJSP).addParameter("tab", Boolean.TRUE);
//        return new JSP(SYOR_PAGE).addParameter("tab", "true").addParameter("msg","Maklumat Berjaya Disimpan");
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

    public void updateNoPT() {
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (!StringUtils.isEmpty(idHakmilik)) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
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
        noPTSave.setLuasDilulus(noPT.getLuasDilulus());
        noPTSave.setHakmilikPermohonan(mohonHakmilik);
        disLaporanTanahService.getPlpservice().simpanNoPt(noPTSave);
    }

    public void updateMohonPermitItem() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = disLaporanTanahService.getPelupusanService().findPermohonanPermitItemByIdMohonList(idPermohonan);
        for (PermohonanPermitItem ppi : senaraiMohonPermitItem) {
            if (!ppi.getKodItemPermit().getKod().equalsIgnoreCase("KB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("PB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("MB")) {
                PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getIdMohonPermitItem().toString()}, 0);
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

    public Boolean updateMohonHakmilik() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kodU = getContext().getRequest().getParameter("kodU");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
        hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
        if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
            } else {
                addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
            }
        }

        if (!StringUtils.isEmpty(hakmilikPermohonan.getStatusLuasDiluluskan())) {
            hakmilikPermohonanSave.setStatusLuasDiluluskan(hakmilikPermohonan.getStatusLuasDiluluskan());
        }

        kodHmlk = getContext().getRequest().getParameter("kodHmlk");
        if (!StringUtils.isEmpty(kodHmlk)) {
            KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHmlk);
            hakmilikPermohonanSave.setKodHakmilikSementara(khm);
        }
        keteranganKadarPremium = getContext().getRequest().getParameter("keteranganKadarPremium");
        if (!StringUtils.isEmpty(keteranganKadarPremium)) {
            hakmilikPermohonanSave.setKeteranganKadarPremium(keteranganKadarPremium);
        }
        if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
            hakmilikPermohonanSave.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
        }
        if (!StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
            hakmilikPermohonanSave.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
        }
        if (hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
            hakmilikPermohonanSave.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
        }

        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);


        return updateDB;
    }

    public void updateBahanBatu() {
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanBahanBatuan mohonBahanBatuSave = new PermohonanBahanBatuan();
        mohonBahanBatuSave = (PermohonanBahanBatuan) disLaporanTanahService.findObject(mohonBahanBatuSave, new String[]{idPermohonan}, 0);
        if (!StringUtils.isEmpty(ksn) && ksn.equalsIgnoreCase("SL")) {
            if (mohonBahanBatuSave != null) {
                mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "update", pengguna));
            } else {
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                }
                mohonBahanBatuSave = new PermohonanBahanBatuan();
                mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "new", pengguna));
                mohonBahanBatuSave.setPermohonan(permohonan);
                if (mohonHakmilik != null) {
                    mohonBahanBatuSave.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
                }
            }
            String stringUOM = new String();
            KodUOM kodUOM = new KodUOM();
            stringUOM = getContext().getRequest().getParameter("jumlahIsipaduUom");
            if (!StringUtils.isEmpty(stringUOM)) {
                kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{stringUOM}, 0);
                mohonBahanBatuSave.setJumlahIsipaduUom(kodUOM);
            } else {
                mohonBahanBatuSave.setJumlahIsipaduUom(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipaduDipohonUom());
            }


            if (disPermohonanBahanBatu != null) {
                if (disPermohonanBahanBatu.getSyaratBahanBatu() != null) {
                    if (!StringUtils.isEmpty(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu().toString())) {
                        mohonBahanBatuSave.setJumlahIsipadu(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu());
                    }
                    if (disPermohonanBahanBatu.getSyaratBahanBatu().getTempohDisyor() != null) {
                        mohonBahanBatuSave.setTempohDisyor(disPermohonanBahanBatu.getSyaratBahanBatu().getTempohDisyor());
                    }
                }

            }
            stringUOM = new String();
            kodUOM = new KodUOM();
            stringUOM = getContext().getRequest().getParameter("tempohSyorUom");
            if (!StringUtils.isEmpty(stringUOM)) {
                kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{stringUOM}, 0);
                mohonBahanBatuSave.setTempohSyorUom(kodUOM);
            } else {
                if (disPermohonanBahanBatu.getSyaratBahanBatu() == null) {
                    PermohonanBahanBatuan mohonBahanBatu = new PermohonanBahanBatuan();
                    mohonBahanBatu = (PermohonanBahanBatuan) disLaporanTanahService.findObject(mohonBahanBatu, new String[]{idPermohonan}, 0);
//                    mohonBahanBatuSave.setTempohSyorUom(disLaporanTanahService.getKodUOMDAO().findById(mohonBahanBatu.getUnitTempohKeluar().getKod()));
                } else {
                    String stringUnitTempohUOM = new String();
                    stringUnitTempohUOM = getContext().getRequest().getParameter("unitTempohUOM");
                    mohonBahanBatuSave.setTempohSyorUom(disLaporanTanahService.getKodUOMDAO().findById(stringUnitTempohUOM));
                }

            }

            disLaporanTanahService.getPlpservice().simpanPermohonanBahanBatuan(mohonBahanBatuSave);
        }
    }

    public void updateMohonTuntutKos() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        HakmilikPermohonan mohonHM = new HakmilikPermohonan();

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 2
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 5
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
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
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    // plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                    disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
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

                if (mohonTuntutKosCagarJln == null) {
                    mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "new", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
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
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ").getKodKewTrans());
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
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISLB"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 4:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
            case 6:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4D");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
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
        }

    }

    public Resolution showFormPopUpJTek() {

        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
    }

    public Boolean updateMohonFasa() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        KodCawangan cawangan = new KodCawangan();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ksn = ctx.getRequest().getParameter("syorKpsn");
        cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
        fasaPermohonan = new FasaPermohonan();
        fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
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
                if (!StringUtils.isEmpty(ulsn)) {
                    PermohonanLaporanKandungan mohonLaporKandSave = new PermohonanLaporanKandungan();
                    idHakmilik = ctx.getRequest().getParameter("idHakmilik");
                    laporanTanah = new LaporanTanah();
                    if (!StringUtils.isEmpty(idHakmilik)) {
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
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

            }

        }

        return updateDB;
    }

    public Resolution simpanAgensi() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listAgensi = item.split(",");
        LOG.info("Size agensi >>>>>>>>>>>>> :" + listAgensi.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listAgensi.length; i++) {

            if (!listAgensi[i].equals("T")) {
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setPermohonan(permohonan);
                LOG.info(listAgensi[i]);
                KodAgensi agen = disLaporanTanahService.getKodAgensiDAO().findById(listAgensi[i]);
                KodRujukan kodRujukan = disLaporanTanahService.getKodRujukanDAO().findById("FL");
                mohonRujLuar.setAgensi(agen);
                try {
                    mohonRujLuar.setNamaAgensi(agen.getNama());
                } catch (Exception h) {
                }
                mohonRujLuar.setKodRujukan(kodRujukan);
                mohonRujLuar.setTempohHari(14);
                mohonRujLuar.setTarikhRujukan(new java.util.Date());
                mohonRujLuar.setTarikhDisampai(new java.util.Date());
                mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                mohonRujLuar.setUlasanMandatori("T");
                pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
            }
        }
        return new JSP(AcqPermohonanPage.getLT_JTEK_PAGE()).addParameter("popup", "true");
    }

    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        day = Calendar.DAY_OF_WEEK;
        if (day == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 18);
        } else if (day == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 19);
        } else {
            cal.add(Calendar.DATE, 20);// +days
        }
        return cal.getTime();
    }

    public Resolution tambahRow() {
        LOG.debug("tambah row");

        PermohonanLaporanUlasan pkk = new PermohonanLaporanUlasan();
        String forwardJSP = new String();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        LOG.debug("index " + index);
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
                LOG.debug("case 5");
                forwardJSP = refreshData("syorPPT");
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
                forwardJSP = refreshData("syorPPTKanan");
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
                forwardJSP = AcqPermohonanPage.getLT_SYARATNYATA_PAGE();
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
                forwardJSP = AcqPermohonanPage.getLT_SEKATAN_PAGE();
            }
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution cariKodAgensiJTK() {
        listKodAgensi = new ArrayList<KodAgensi>();
        List<KodAgensi> listKodAgensiTemp = new ArrayList<KodAgensi>();
        List<PermohonanRujukanLuar> listMohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (!StringUtils.isBlank(idPermohonan)) {
            listMohonRujLuar = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
        }
//code changed by rohan
        if (kod != null) {
            listKodAgensiTemp = disLaporanTanahService.getPelupusanService().searchKodAgensiJTK(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
                                checkExist = false;
                                break;
                            }
                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                    return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
                }
            } else {
                if (listKodAgensiTemp.size() < 1) {
                    kodAgensi = new KodAgensi();
                    kodAgensi.setKod(kod);
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
                    //addSimpleError("Kod Agensi Tidak Sah");
                } else {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    kodAgensiNama = null;
                    return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
                }
            }

        } else {
            listKodAgensiTemp = pelupusanService.searchKodAgensiJTK("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
                                checkExist = false;
                                break;
                            }
                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                }
            } else {
                for (KodAgensi ka : listKodAgensiTemp) {
                    listKodAgensi.add(ka);
                }
            }

            System.out.println("listKodSyaratNyata.size : " + listKodAgensiTemp.size());

            sizeKod = listKodAgensi.size();
            LOG.debug("size agensi list: " + sizeKod);
            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            kodAgensiNama = null;
            return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
        }
        return new JSP("pengambilan/melaka/LaporanTanahEdit/laporan_tanahV2AddJabatanTeknikal.jsp").addParameter("popup", "true");
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

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
    }

    /**
     * @return the kodPeranan
     */
    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    /**
     * @param kodPeranan the kodPeranan to set
     */
    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
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

    public List<PermohonanLaporanUsaha> getListPermohonanLaporanUsaha() {
        return listPermohonanLaporanUsaha;
    }

    public void setListPermohonanLaporanUsaha(List<PermohonanLaporanUsaha> listPermohonanLaporanUsaha) {
        this.listPermohonanLaporanUsaha = listPermohonanLaporanUsaha;
    }
}
