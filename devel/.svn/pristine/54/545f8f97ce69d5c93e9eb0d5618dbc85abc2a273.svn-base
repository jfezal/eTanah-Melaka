/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKeputusanDAO;
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
import etanah.model.InfoAudit;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.CalcTaxPelupusan;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import org.apache.log4j.Logger;
import java.text.ParseException;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisRekodKeputusanController;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author Afham
 */
@UrlBinding("/pelupusan/rekod_keputusanPindaanMMKV2")
public class RekodKeputusanPindaanMMKV2ActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikPermohonanDAO hakmillikPermohonanDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    CalcTaxPelupusan calcTax;
    private String idPermohonan;
    private String stageId;
    private String idHakmilik;
    private String kodNegeri;
    private String keputusan;
    private String kpsnMohonFasa;
    private String kod;
    private String syaratNyata;
    private String kodSktn;
    private String syaratNyata1;
    private String indexSyarat;
    private String kodU;
    private String kodHakmilik;
    private String keteranganKadarPremium;
    private String kodGunaTanah;
    private String index;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private String sekatKpntgn2;
    private String syaratNyata2;
    private Permohonan permohonan;
    private BigDecimal amnt;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    etanahActionBeanContext ctx;
    private DisRekodKeputusanController disRekodKeputusanController;
    private DisSyaratSekatan disSyaratSekatan;
    private DisPermohonanBahanBatu disPermohonanBahanBatu;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan;
    private List<HakmilikPermohonan> senaraiHakmilikTolak;
    private List<HakmilikPermohonan> senaraiHakmilikLulus;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanah;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<String> senaraikodKadarPremium;
    private List hakmilikPermohonanList;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private static final Logger LOG = Logger.getLogger(RekodKeputusanMMKV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng;
    private int sizeTiadaKeputusan;
    private int sizeLulus;
    private int sizeTolak;
    private String katTanahPilihan;
    private FasaPermohonan mohonFasa;

    @DefaultHandler
    public Resolution viewOnlyRekodKeputusanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution showFormBeforeMesyuarat() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.showFormBeforeMesyuarat();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        httpSession.setAttribute("disShowFormBeforeMesyuarat", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyRekodKeputusan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusan();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        else {
//            stageId = "g_charting_keputusan";
//        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanKeputusan", "!simpanSyarat"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonFasa = new FasaPermohonan();
        mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_MMK2"}, 0);

        /**
         * Senarai Hakmilik
         */
        if (idPermohonan != null) {

            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : null;

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
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }

            if (senaraiHakmilikPermohonan.size() > 0) {
                senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();

                senaraiHakmilikTiadaKeputusan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(idPermohonan);
                sizeTiadaKeputusan = senaraiHakmilikTiadaKeputusan.size() > 0 ? senaraiHakmilikTiadaKeputusan.size() : 0;
                senaraiHakmilikLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(idPermohonan);
                sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                senaraiHakmilikTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(idPermohonan);
                sizeTolak = senaraiHakmilikTolak.size() > 0 ? senaraiHakmilikTolak.size() : 0;
            }
            String kodDok = kertasDok(permohonan.getKodUrusan().getKod());
            permohonanKertas = new PermohonanKertas();
            permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
            senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        }
        if (StringUtils.isNotEmpty(idHakmilik)) {
            hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
            if (hakmilikPermohonan != null) {
                senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(hakmilikPermohonan.getKategoriTanahBaru().getKod());
                if(hakmilikPermohonan.getKadarPremium() != null){
                    amnt = hakmilikPermohonan.getKadarPremium() ;
                }
            }
        }
        
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
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();

        if (senaraiHakmilikPermohonan.size() > 0) {
            senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();

            senaraiHakmilikTiadaKeputusan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(idPermohonan);
            senaraiHakmilikLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(idPermohonan);
            senaraiHakmilikTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(idPermohonan);
        }

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution editDetails() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String type = ctx.getRequest().getParameter("type");
        idHakmilik = ctx.getRequest().getParameter("idMH");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
            katTanahPilihan = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                kodGunaTanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
            }
            senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(katTanahPilihan);
        }
        if(hakmilikPermohonan.getKadarPremium() != null){
            amnt = hakmilikPermohonan.getKadarPremium();
        }
        senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        if (type.equals("update")) {
            return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", "true");
        } else {
            return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_SYRT_VIEW_PAGE()).addParameter("tab", "true");
        }

    }

    public Resolution editDetailsData() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String luas = ctx.getRequest().getParameter("luas");
        String kodKat = ctx.getRequest().getParameter("kodKat");
        idHakmilik = ctx.getRequest().getParameter("idMH");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        if (StringUtils.isNotEmpty(luas)) {
            double a = Double.parseDouble(luas);
            BigDecimal l = BigDecimal.valueOf(a);
            hakmilikPermohonan.setLuasDiluluskan(l);
        }
        if (StringUtils.isNotEmpty(kodKat)) {
            hakmilikPermohonan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(kodKat));
            katTanahPilihan = kodKat;
            senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(katTanahPilihan);
        }

        senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", "true");


    }

    public String refreshData(String type) {
        String forwardJSP = new String();
        String kodDok = new String();
        int typeNum = type.equals("mMesyuarat") ? 1
                : type.equals("sKelulusan") ? 2
                : type.equals("main") ? 3
                : type.equals("beforeMesyuarat") ? 6
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getRK_PINDAAN_MMK_V2_MM_PAGE();
                kodDok = kertasDok(permohonan.getKodUrusan().getKod());
                permohonanKertas = new PermohonanKertas();
                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_MMK2"}, 0);
                if (mohonFasa != null) {
                    kpsnMohonFasa = mohonFasa.getKeputusan() != null ? mohonFasa.getKeputusan().getKod() : new String();
                }
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getRK_PINDAAN_MMK_V2_SYRT_EDIT_PAGE();
                LOG.debug("Page--->" + forwardJSP);
                break;
            case 3:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_PINDAAN_MMK_V2_MAIN_PAGE();
                break;
            case 6:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
                forwardJSP = DisPermohonanPage.getRK_PINDAAN_MMK_V2_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    public String kertasDok(String urusan) {
        String kodDok = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : 0;

        switch (typeUrusan) {
            case 1:
                kodDok = "PMMK";
                break;
            case 2:
                kodDok = "PMMK";
                break;
            case 3:
                kodDok = "PMMK";
                break;
        }
        return kodDok;
    }

    public Resolution refreshpage() {

        //END
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
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
        disRekodKeputusanController = new DisRekodKeputusanController();
        int typeNum = permohonan.getKodUrusan().getKod().equals("BMBT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 2
                : 0;

        switch (typeNum) {
            case 1: // Urusan = PPBB
//                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
//                } 
//                } else {
//                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
//                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
//                }
                break;
            case 2: // Urusan = PJBTR
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
                break;
            default:
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        }
        return new JSP(DisPermohonanPage.getRK_MAIN_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKeputusan() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Permohonan permohonanTemp = new Permohonan();
        permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        }
        if (kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PBMT") && !StringUtils.isEmpty(kpsnMohonFasa)) {
            mohonFasa = new FasaPermohonan();
            mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_MMK2"}, 0);
            mohonFasa.setInfoAudit(disLaporanTanahService.findAudit(mohonFasa, "update", peng));
            KodKeputusan kodKpsn = new KodKeputusan();
            kodKpsn = (KodKeputusan) disLaporanTanahService.findObject(kodKpsn, new String[]{kpsnMohonFasa}, 0);
            mohonFasa.setKeputusan(kodKpsn);
            disLaporanTanahService.getPlpservice().simpanFasaPermohonan(mohonFasa);
        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_MM_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanMesyuarat() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Permohonan permohonanTemp = new Permohonan();
        permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_MM_PAGE()).addParameter("tab", "true");
    }


    public Resolution simpanSyarat() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
            updateMohonHakmilik();
        }
        if (permohonan.getKodUrusan().getKod().equals("PJBTR")) {
            updateMohonHakmilik();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            updateMohonHakmilik();
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP(DisPermohonanPage.getRK_PINDAAN_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", Boolean.TRUE);
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
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);;
        }
        hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
        if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
            } else {
                addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
            }
        } else {
            if (hakmilikPermohonanSave.getKodUnitLuas() != null) {
                hakmilikPermohonanSave.setLuasLulusUom(hakmilikPermohonan.getKodUnitLuas());
            }
        }


        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);


        return updateDB;
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

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public DisRekodKeputusanController getDisRekodKeputusanController() {
        return disRekodKeputusanController;
    }

    public void setDisRekodKeputusanController(DisRekodKeputusanController disRekodKeputusanController) {
        this.disRekodKeputusanController = disRekodKeputusanController;
    }

    public DisSyaratSekatan getDisSyaratSekatan() {
        return disSyaratSekatan;
    }

    public void setDisSyaratSekatan(DisSyaratSekatan disSyaratSekatan) {
        this.disSyaratSekatan = disSyaratSekatan;
    }

    public String getIndexSyarat() {
        return indexSyarat;
    }

    public void setIndexSyarat(String indexSyarat) {
        this.indexSyarat = indexSyarat;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
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

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public DisPermohonanBahanBatu getDisPermohonanBahanBatu() {
        return disPermohonanBahanBatu;
    }

    public void setDisPermohonanBahanBatu(DisPermohonanBahanBatu disPermohonanBahanBatu) {
        this.disPermohonanBahanBatu = disPermohonanBahanBatu;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTiadaKeputusan() {
        return senaraiHakmilikTiadaKeputusan;
    }

    public void setSenaraiHakmilikTiadaKeputusan(List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan) {
        this.senaraiHakmilikTiadaKeputusan = senaraiHakmilikTiadaKeputusan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTolak() {
        return senaraiHakmilikTolak;
    }

    public void setSenaraiHakmilikTolak(List<HakmilikPermohonan> senaraiHakmilikTolak) {
        this.senaraiHakmilikTolak = senaraiHakmilikTolak;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikLulus() {
        return senaraiHakmilikLulus;
    }

    public void setSenaraiHakmilikLulus(List<HakmilikPermohonan> senaraiHakmilikLulus) {
        this.senaraiHakmilikLulus = senaraiHakmilikLulus;
    }

    public int getSizeTiadaKeputusan() {
        return sizeTiadaKeputusan;
    }

    public void setSizeTiadaKeputusan(int sizeTiadaKeputusan) {
        this.sizeTiadaKeputusan = sizeTiadaKeputusan;
    }

    public int getSizeLulus() {
        return sizeLulus;
    }

    public void setSizeLulus(int sizeLulus) {
        this.sizeLulus = sizeLulus;
    }

    public int getSizeTolak() {
        return sizeTolak;
    }

    public void setSizeTolak(int sizeTolak) {
        this.sizeTolak = sizeTolak;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanah() {
        if (StringUtils.isNotBlank(katTanahPilihan)) {
            return pelupusanService.getSenaraiKegunaanTanah(katTanahPilihan);
        }
        return new ArrayList<KodKegunaanTanah>();
    }

    public void setSenaraiKodKegunaanTanah(List<KodKegunaanTanah> senaraiKodKegunaanTanah) {
        this.senaraiKodKegunaanTanah = senaraiKodKegunaanTanah;
    }

    public String getKatTanahPilihan() {
        return katTanahPilihan;
    }

    public void setKatTanahPilihan(String katTanahPilihan) {
        this.katTanahPilihan = katTanahPilihan;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getKpsnMohonFasa() {
        return kpsnMohonFasa;
    }

    public void setKpsnMohonFasa(String kpsnMohonFasa) {
        this.kpsnMohonFasa = kpsnMohonFasa;
    }
}
