/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodTanahDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodPemilikan;
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodTanah;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import etanah.service.common.TanahRizabService;
import etanah.view.etanahActionBeanContext;
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
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanPelanController;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanTanahTerdahulu;
import java.math.BigDecimal;
import javax.servlet.http.HttpSession;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/laporan_pelanV2")
public class LaporanPelanV2ActionBean extends AbleActionBean {

    //Injector
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodKeputusanDAO keputusanDAO;
    @Inject
    etanah.Configuration conf;
    //Object
    private TanahRizabPermohonan tanahrizabpermohonan;
    private Permohonan permohonan;
    private PermohonanManual permohonanManual;
    private PermohonanManual pm;
    private Permohonan prmhnn;
    private Permohonan per;
    private Pemohon pemohon;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private PermohonanLaporanKawasan permohonanLaporanKWS;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private PermohonanLaporanKandungan permohonanLaporanKandungan;
    private KodRizab rizab;
    private KodHakmilik kodHakmilik;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private KodUOM kodUL;
    private NoPt noPtTemp;
    private NoPt noPt;
    private Pengguna peng;
    private DisLaporanPelanController disLaporanPelanController;
    etanahActionBeanContext ctx;
    private List hakmilikPermohonanList;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<HakmilikPermohonan> hp;
    private List<HakmilikPermohonan> hakmilikMohonList;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List<KodSeksyen> kodSeksyenList;
    private List<Permohonan> permohonanTerdahuluList;
    private List<PermohonanManual> permohonanManualList;
    private List<PermohonanManual> pmList;
    private List<Permohonan> permohonanSebelumList = new ArrayList<Permohonan>();
    private List<DisPermohonanTanahTerdahulu> listpermohonanTanahTerdahulu;
    private List<NoPt> senaraiNoPt;
    private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
    private List<PermohonanLaporanPelan> permohonanLaporanPelanList;
    private List<PermohonanLaporanPelan> mohonLaporanPelanList;
    private List<KodDUN> listKodDun;
    List<HakmilikPermohonan> senaraiHakmilik;
    private Pengguna pengguna;
    //Variable
    private String idPermohonan;
    private String stageId;
    private String kodNegeri;
    private String noLot;
    private String noFail;
    private String noLitho;
    private String projekKerajaan;
    private String noWarta;
    private String catatan;
    private String tarikhWarta;
    private String noHakmilik;
    private String noLPS;
    private String idHakmilik;
    private String noPtSementara; //will change to get id mh (for temporary before getting new solution)
    private String idHakmilikPermohonan;
    private String idPermohonanSebelum;
    private String idtanahrizabPermohonan;
    private String lokasi;
    private String ulasan;
    private String negeri;
    private String rizab_melayu;
    private String kodRizab;
    private String addnoWarta;
    private String addtarikhWarta;
    private String addnoPelanWarta;
    private String gsa;
    private String hutan;
    private String lain;
    private String pbt;
    private String catatanLain;
    private String kodT;
    private String kodPString;
    private String kodPar;
    private String kodD;
    private String id;
    private String id2;
    private String catatanKWS;
    private String catatanKeg;
    private String kodUrusan;
    private String keputusan;
    private String keputusanOleh;
    private String tarikhKeputusan;
    private String pmhn;
    private BigDecimal luas;
    private String kodKawasanParlimen;
    private String kodDUN;
    private String kodLuas;
//    private String ksn;
    private char kodP;
    private static final Logger LOG = Logger.getLogger(LaporanPelanV2ActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution viewOnlyLaporanPelanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanPelanController = new DisLaporanPelanController();
        disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
        httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
        return new JSP(DisPermohonanPage.getLP_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyLaporanPelan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disLaporanPelanController = new DisLaporanPelanController();
        disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelan();
        httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
        return new JSP(DisPermohonanPage.getLP_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution showFormPopUp() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        return new JSP("pelupusan/laporan_pelan/laporan_pelanEdit/laporan_pelanV2AddPermohonanTerdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution showFormNoFail() {
        LOG.info("+++ masuk showFormNoFail() +++");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        return new JSP("pelupusan/laporan_pelan/laporan_pelanEdit/laporan_pelanV2AddFail.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopUp1() {
        String idMohonLaporKws = getContext().getRequest().getParameter("idMohonLaporKws");
        if (StringUtils.isNotEmpty(idMohonLaporKws)) {
            permohonanLaporanKWS = new PermohonanLaporanKawasan();
            permohonanLaporanKWS = disLaporanTanahService.getPelupusanService().findPermohonanLaporanKawasanById(Long.valueOf(idMohonLaporKws));
            if (permohonanLaporanKWS != null) {
                idtanahrizabPermohonan = permohonanLaporanKWS.getIdMohonlaporKws().toString();
//                catatanKWS = permohonanLaporanKWS.getCatatan() != null ? permohonanLaporanKWS.getCatatan() : null;
                catatan = permohonanLaporanKWS.getCatatan() != null ? permohonanLaporanKWS.getCatatan() : null;
                addnoPelanWarta = permohonanLaporanKWS.getNoPelanWarta() != null ? permohonanLaporanKWS.getNoPelanWarta() : null;
                addnoWarta = permohonanLaporanKWS.getNoWarta() != null ? permohonanLaporanKWS.getNoWarta() : null;
                addtarikhWarta = permohonanLaporanKWS.getTarikhWarta() != null ? sdf.format(permohonanLaporanKWS.getTarikhWarta()) : null;
            }
        }
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        return new JSP("pelupusan/laporan_pelan/laporan_pelanEdit/laporan_pelanV2AddDalamKawasan.jsp").addParameter("popup", "true");
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
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public Resolution refreshpage() {
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
        disLaporanPelanController = new DisLaporanPelanController();
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
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 20
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 21
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 22
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 23
                //                : permohonan.getKodUrusan().getKod().equals("ESK") ? 23
                //                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 24
                : 0;


        switch (typeNum) {
            case 1: // Urusan = PPBB
                stageId = "g_charting_permohonan";
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 2: // Urusan = PBPTD
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 3: // Urusan = PBPTG
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 4: // Urusan = LMCRG
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 5: // Urusan = PJLB
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 6: // Urusan = LPSP
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 7: // Urusan = PLPS
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                } else if (stageId.equalsIgnoreCase("g_charting_keputusan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelan();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 8: // Urusan = PPRU
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 9: // Urusan = PPTR
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 10: // Urusan = PTGSA
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 11: // Urusan = PRMP
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 12: // Urusan = PBMT
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 13: // Urusan = MCMCL
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 14: // Urusan = MMMCL
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 15: // Urusan = PRIZ
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 16: // Urusan = PHLA
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 17: // Urusan = PBRZ
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 18: // Urusan = PBHL
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 19: // Urusan = BMBT
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 20: // Urusan = PJBTR
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 21: // Urusan = PLPTD
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 22: // Urusan = PWGSA
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
            case 23: // Urusan = PHLP
                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                }
                break;
//            case 23: // Urusan = PWGSA
//                if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
//                    disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
//                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
//                }
//                break;
//            case 24: // Urusan = PWGSA
//                                  disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
//                    httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
//                break;
            default:
                disLaporanPelanController = disLaporanPelanController.viewOnlyLaporanPelanPT();
                httpSession.setAttribute("disLaporanPelanController", disLaporanPelanController);
                break;
        }
        return new JSP(DisPermohonanPage.getLP_MAIN_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            } else {
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
            }
            permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
//                noPtTemp = new NoPt();
//                noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
            }
            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
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
            } else if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
            } else {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }

            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilikPermohonanList = new ArrayList();
                HakmilikPermohonan mohonHM = new HakmilikPermohonan();
                DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

                mohonHM = permohonan.getSenaraiHakmilik().get(0);
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
                    } else if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                    } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                        HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                        hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);

                    } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                        senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                    } else if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                    HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                    hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                    hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);

                }
            }
            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
        }
        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
//        rehydrate();
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        LOG.info("+++ masuk refeshData +++");
        String forwardJSP = new String();
        int typeNum = type.equals("sTanah") ? 1
                : type.equals("pTerdahulu") ? 2
                : type.equals("dKawasan") ? 3
                : type.equals("catatan") ? 4
                : type.equals("LuasBaru") ? 6
                : type.equals("main") ? 5 : 0;

        switch (typeNum) {
            case 1:
                if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                    senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                    for (HakmilikPermohonan hm : senaraiHP) {
                        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hm.getId());

                        if (hm.getLuasDiluluskan() != null) {
                            luas = hm.getLuasDiluluskan();
                        }
                        if (hm.getLuasLulusUom() != null) {
                            kodLuas = hm.getLuasLulusUom().getKod();
                        }

                        if (permohonanLaporanPelan.getNoLitho() != null) {
                            if (hm.getKodMilik() != null) {
                                kodP = hm.getKodMilik().getKod();
                                kodPString = hm.getKodMilik().getNama();
                            }

                            if (hm.getKodKawasanParlimen() != null) {
                                kodKawasanParlimen = hm.getKodKawasanParlimen().getNama();
                            }

                            if (hm.getKodDUN() != null) {
                                kodDUN = hm.getKodDUN().getNama();
                            }
                            noLitho = permohonanLaporanPelan.getNoLitho();

                            if (hm.getKodKawasanParlimen() != null) {
                                kodPar = hm.getKodKawasanParlimen().getKod();
                            }

                            if (hm.getKodDUN() != null) {
                                kodD = hm.getKodDUN().getKod();
                            }
                            if (permohonanLaporanPelan.getKodTanah() != null) {
                                kodT = permohonanLaporanPelan.getKodTanah().getKod();
                            }
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    idHakmilik = ctx.getRequest().getParameter("idHakmilik");
//                    hakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik("L", idPermohonan, idHakmilik);
                    permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());

                    if (permohonanLaporanPelan != null) {
                        noLitho = permohonanLaporanPelan.getNoLitho();
                        projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
                        if (permohonanLaporanPelan.getKodTanah() != null) {
                            kodT = permohonanLaporanPelan.getKodTanah().getKod();
                        }
                    }
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
                        kodP = hakmilikPermohonan.getKodMilik().getKod();
                        kodPString = hakmilikPermohonan.getKodMilik().getNama();
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                        kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
                    }

                    //Add for Parlimen and Adun
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                        kodD = hakmilikPermohonan.getKodDUN().getKod();
                    }
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                        kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
                    }

                } else {
                    permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
                    if (permohonanLaporanPelan != null) {
                        noLitho = permohonanLaporanPelan.getNoLitho();
                        projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
                        if (permohonanLaporanPelan.getKodTanah() != null) {
                            kodT = permohonanLaporanPelan.getKodTanah().getKod();
                        }
                    }
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
                        kodP = hakmilikPermohonan.getKodMilik().getKod();
                        kodPString = hakmilikPermohonan.getKodMilik().getNama();
                    }
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                        kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
                    }

                    //Add for Parlimen and Adun
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                        kodD = hakmilikPermohonan.getKodDUN().getKod();
                    }
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                        kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
                    }
                }
                LOG.info("++++ smpai sini +++");
                forwardJSP = DisPermohonanPage.getLP_STS_TANAH();

                break;
            case 2:
                rehydrate();
                pmList = plpservice.findByIdMohonlikeDIS(idPermohonan);
                forwardJSP = DisPermohonanPage.getLP_MOHON_TERDAHULU();
                break;

            case 3:
                rehydrate();
                forwardJSP = DisPermohonanPage.getLP_DLM_KWS();
                break;

            case 4:
                forwardJSP = DisPermohonanPage.getLP_CATATAN();
                break;
            case 5:
                rehydrate();
                disLaporanPelanController = (DisLaporanPelanController) getContext().getRequest().getSession().getAttribute("disLaporanPelanController");
                forwardJSP = DisPermohonanPage.getLP_MAIN_PAGE();
                break;
            case 6:
                rehydrate();
                disLaporanPelanController = (DisLaporanPelanController) getContext().getRequest().getSession().getAttribute("disLaporanPelanController");
                forwardJSP = DisPermohonanPage.getLP_LUASBARU();
                break;
        }
        return forwardJSP;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!showFormPopUp", "!openFrame", "!cariPermohonan", "!simpanPermohonanSblm", "!showFormPopup1", "!simpanKawasan", "!simpanPerihal", "!simpanFail"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        stageId = stageId(taskId, pguna);
        stageId = "g_charting_permohonan";
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonanLaporanPelan = new PermohonanLaporanPelan();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //Add for charting
        catatanKeg = permohonan.getSebab();
        pmList = plpservice.findByIdMohonlikeDIS(idPermohonan);
        if (!permohonan.getIdPermohonan().equals(null)) {
            LOG.info("In rehydrate");
//            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            if (senaraiHakmilikPermohonan.isEmpty()) {
                InfoAudit ia = new InfoAudit();
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp.setPermohonan(permohonan);
                hp.setInfoAudit(ia);
                hp.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                hp.setCawangan(permohonan.getCawangan());
                pelupusanService.saveOrUpdate(hp);
            }
        }

        if (idPermohonan != null) {
            int syx = 0;
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            /*
             * SENARAI HAKMILIK
             */
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            permohonanLaporanPelanList = new ArrayList<PermohonanLaporanPelan>();
            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }
            permohonanLaporanPelanList = disLaporanTanahService.getPelupusanService().findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
            if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
                hakmilikPermohonan = new HakmilikPermohonan();
                if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                }
                if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    hakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik("L", idPermohonan, idHakmilik);
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
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
//                            hakmilikPermohonan = mohonHM;
                        }
                    }
                } else {
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
                }
                if (permohonanLaporanPelanList.size() > 0) {
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);
                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                } else {
                    permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanPelan.setPermohonan(permohonan);
                    permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                    permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                }
            } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
                if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    hp = pelupusanService.getHakmilikPermohonan(idPermohonan);
                    if (hp.size() > 0) {
                        senaraiNoPt = new ArrayList();
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                        //COmment bcoz of mulfunction
                        //hakmilikPermohonan = new HakmilikPermohonan();
                        //hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{noPtSementara}, 2);
                        noPtTemp = new NoPt();
                        noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{Long.toString(hakmilikPermohonan.getId())}, 0);
                    }
//                    hakmilikPermohonan = noPtTemp.getHakmilikPermohonan();
                    if (senaraiHakmilikPermohonan.size() > 0) {
                        senaraiNoPt = new ArrayList();
                        for (HakmilikPermohonan mohonHM : senaraiHakmilikPermohonan) {
                            NoPt noPtTemp = new NoPt();
                            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{Long.toString(mohonHM.getId())}, 0);
                            senaraiNoPt.add(noPtTemp);
                        }
                    }
                } else {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                }
                if (permohonanLaporanPelanList.size() > 0) {
                    permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                } else {
                    permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanPelan.setPermohonan(permohonan);
                    permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                    permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);

                }

            } else {
                if (senaraiHakmilikPermohonan.size() > 0) {
                    hakmilikPermohonanList = new ArrayList();
                    HakmilikPermohonan mohonHM = new HakmilikPermohonan();
                    DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

                    if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);

                        for (HakmilikPermohonan hpm : senaraiHakmilikPermohonan) {
                            //mohonHM = permohonan.getSenaraiHakmilik().get(0);
                            disMohonHM.setHakmilikPermohonan(hpm);
                            hakmilikPermohonanList.add(disMohonHM);
                            if (disMohonHM.getHakmilikPermohonan().getHakmilik() != null) {
                                idHakmilik = hpm.getHakmilik().getIdHakmilik();
                                hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
                                permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);

                            }
                        }
                    } else {
                        mohonHM = permohonan.getSenaraiHakmilik().get(0);
                        disMohonHM.setHakmilikPermohonan(mohonHM);
                        hakmilikPermohonanList.add(disMohonHM);
                        if (disMohonHM.getHakmilikPermohonan().getHakmilik() != null) {
                            idHakmilik = disMohonHM.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                            hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
                            permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, idHakmilik}, 1);

                        } else {
                            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                                hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                                hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                                hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                            } else if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
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
                            } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
//                            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                                List<HakmilikPermohonan> senaraiHp = new ArrayList<HakmilikPermohonan>();
                                senaraiHp = pelupusanService.findHakmilikPermohonanList(idPermohonan);

                                for (HakmilikPermohonan mh : senaraiHp) {
                                    if (mh.getHakmilik() != null) {
                                        //hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                                        hp = pelupusanService.findByIdPermohonan1(idPermohonan);
//                                        hp = pelupusanService.getHakmilikPermohonan(idPermohonan);

                                        if (hp.size() > 0) {

                                            senaraiNoPt = new ArrayList();
                                            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikPermohonan.get(0);
                                            noPtTemp = new NoPt();
                                            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{Long.toString(hakmilikPermohonan.getId())}, 0);
                                            noPtSementara = "" + noPtTemp.getHakmilikPermohonan().getId();
                                        }
                                        if (senaraiHakmilikPermohonan.size() > 0) {
                                            senaraiNoPt = new ArrayList();
                                            for (HakmilikPermohonan mohonHM1 : senaraiHakmilikPermohonan) {
                                                NoPt noPtTemp = new NoPt();
                                                noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{Long.toString(mohonHM1.getId())}, 0);
                                                senaraiNoPt.add(noPtTemp);
                                            }
                                        }

                                    } else {
                                        List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                                        senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                                        hakmilikPermohonan = senaraiHP.get(0);
                                    }
                                }

                            }
                        }

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
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
//                        hakmilikPermohonan = new HakmilikPermohonan();
//                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                    }
                }
                if (permohonanLaporanPelanList.size() > 0) {
                    if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                        senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHP) {
                            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hm.getId());

                            if (hm.getLuasDiluluskan() != null) {
                                luas = hm.getLuasDiluluskan();
                            }
                            if (hm.getLuasLulusUom() != null) {
                                kodLuas = hm.getLuasLulusUom().getNama();
                            }
                            if (permohonanLaporanPelan != null) {
                                if (permohonanLaporanPelan.getNoLitho() != null) {
                                    if (hm.getKodMilik() != null) {
                                        kodP = hm.getKodMilik().getKod();
                                        kodPString = hm.getKodMilik().getNama();
                                    }
                                    if (hm.getKodKawasanParlimen() != null) {
                                        kodKawasanParlimen = hm.getKodKawasanParlimen().getNama();
                                    }
                                    if (hm.getKodDUN() != null) {
                                        kodDUN = hm.getKodDUN().getNama();
                                    }
                                }
                            }
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PPRU")) {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
//                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
//                            permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, String.valueOf(hm.getId())}, 2);
                            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hm.getId());
                            if (permohonanLaporanPelan == null) {
                                permohonanLaporanPelan = new PermohonanLaporanPelan();
                                permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                                permohonanLaporanPelan.setPermohonan(permohonan);
                                permohonanLaporanPelan.setHakmilikPermohonan(hm);
                                permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                                laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                            }
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                        senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHP) {
                            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hm.getId());
                            if (hm.getLuasTerlibat() != null) {
                                luas = hm.getLuasTerlibat();
                            }
                            if (hm.getKodUnitLuas() != null) {
                                kodLuas = hm.getKodUnitLuas().getNama();
                            }

                            if (permohonanLaporanPelan.getNoLitho() != null) {
                                if (hm.getKodMilik() != null) {
                                    kodP = hm.getKodMilik().getKod();
                                    kodPString = hm.getKodMilik().getNama();
                                }
                                kodKawasanParlimen = hm.getKodKawasanParlimen().getNama();
                                kodDUN = hm.getKodDUN().getNama();
                            }
                        }
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
                        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hakmilikPermohonan.getId());
                    }
                } else {
                    if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                        List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                        senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHP) {
                            permohonanLaporanPelan = new PermohonanLaporanPelan();
                            permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                            permohonanLaporanPelan.setPermohonan(permohonan);
                            permohonanLaporanPelan.setHakmilikPermohonan(hm);
                            permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
//                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
                            permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{idPermohonan, String.valueOf(hm.getId())}, 2);
                            if (permohonanLaporanPelan == null) {
                                permohonanLaporanPelan = new PermohonanLaporanPelan();
                                permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                                permohonanLaporanPelan.setPermohonan(permohonan);
                                permohonanLaporanPelan.setHakmilikPermohonan(hm);
                                permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                                laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                            }
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        List<HakmilikPermohonan> senaraiHP = new ArrayList<HakmilikPermohonan>();
                        senaraiHP = pelupusanService.findHakmilikPermohonanList(idPermohonan);
                        for (HakmilikPermohonan hm : senaraiHP) {
                            permohonanLaporanPelan = new PermohonanLaporanPelan();
                            permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                            permohonanLaporanPelan.setPermohonan(permohonan);
                            permohonanLaporanPelan.setHakmilikPermohonan(hm);
                            permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                        }
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
                        if (hakmilikPermohonan != null) {
                            permohonanLaporanPelan = new PermohonanLaporanPelan();
                            permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                            permohonanLaporanPelan.setPermohonan(permohonan);
                            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                            permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                        } else {
                            hakmilikPermohonan = new HakmilikPermohonan();
                            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);

                            permohonanLaporanPelan = new PermohonanLaporanPelan();
                            permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                            permohonanLaporanPelan.setPermohonan(permohonan);
                            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                            permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pguna));
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                        }
                    }
                }
            }

            if (permohonanLaporanPelan != null) {
                if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
//                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
//                    HakmilikPermohonan mohonHmlk = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik("L", idPermohonan, idHakmilik);
                    for (HakmilikPermohonan hmp : senaraiHakmilikPermohonan) {
                        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hmp.getId());
                        noLitho = permohonanLaporanPelan.getNoLitho();
                    }
                } else {
                    noLitho = permohonanLaporanPelan.getNoLitho();
                }
                catatan = permohonanLaporanPelan.getCatatan();
                projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
                permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                if (permohonanLaporanUlasan != null) {
                    ulasan = permohonanLaporanUlasan.getUlasan();
                }
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
                kodP = hakmilikPermohonan.getKodMilik().getKod();
                kodPString = hakmilikPermohonan.getKodMilik().getNama();
            }

            permohonanLaporKSWList = laporanPelukisPelanService.findByidMohon(idPermohonan);
            LOG.info(permohonanLaporKSWList);
            for (PermohonanLaporanKawasan lpk : permohonanLaporKSWList) {
                LOG.info("trace kod rizab :: " + lpk.getKodRizab().getKod());
                String rezKod = String.valueOf(lpk.getKodRizab().getKod());
                if (rezKod.equalsIgnoreCase("1")) {
                    LOG.info("Load Kod Rizab 1");
                    pbt = "1";
                } else if (rezKod.equalsIgnoreCase("2")) {
                    gsa = "2";
                } else if (rezKod.equalsIgnoreCase("3")) {
                    rizab_melayu = "3";
                } else if (rezKod.equalsIgnoreCase("4")) {
                    hutan = "4";
                } else if (rezKod.equalsIgnoreCase("99")) {

                    lain = "99";
                    catatanLain = lpk.getCatatan();
                }
            }

            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);

            permohonanManualList = plpservice.findByIdMohonlist(idPermohonan);
            listpermohonanTanahTerdahulu = new ArrayList<DisPermohonanTanahTerdahulu>();
            for (PermohonanManual mohonManual : permohonanManualList) {
                Permohonan mohon = new Permohonan();
                if (mohonManual.getNoFail() == null) {
                    mohonManual.setNoFail("TIADA");
                    permohonanManualDAO.saveOrUpdate(mohonManual);
                }
                DisPermohonanTanahTerdahulu mohonTT = new DisPermohonanTanahTerdahulu();
                mohon = (Permohonan) disLaporanTanahService.findObject(mohon, new String[]{mohonManual.getNoFail()}, 0);
                mohonTT.setPermohonanTerdahulu(mohon);
                mohonTT.setPermohonanManualSemasa(mohonManual);
                if (mohon != null) {
                    if (mohon.getKeputusan() != null) {
                        mohonTT.setStatusKeputusan(mohon.getKeputusan().getNama());
                    }
                    if (mohon.getTarikhKeputusan() != null) {
                        mohonTT.setTarikhKeputusan(mohon.getTarikhKeputusan().toString());
                    }
                    if (mohon.getKeputusanOleh() != null) {
                        mohonTT.setKeputusanOleh(mohon.getKeputusanOleh().getNama());
                    }
                    if (mohon.getNamaPenerima() != null) {
                        mohonTT.setKeputusanOleh(mohon.getNamaPenerima());
                    }
                    if (mohon.getPenyerahNama() != null) {
                        mohonTT.setNamaPemohon(mohon.getPenyerahNama());
                    } else {
                        if (mohon.getSenaraiPemohon().isEmpty()) {
                            mohonTT.setNamaPemohon(mohon.getPenyerahNama());
                        }
                    }
                    mohonTT.setPermohonanTerdahulu(mohon);
                } else {
                    mohonTT.setNoFail(mohonManual.getNoFail());
                }
                listpermohonanTanahTerdahulu.add(mohonTT);
            }
        }
    }

    public Resolution simpanPerihal() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        catatanKeg = permohonan.getSebab();
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
        List<PermohonanLaporanPelan> mohonLaporPelanList = new ArrayList<PermohonanLaporanPelan>();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            if (!permohonan.getKodUrusan().getKod().equals("PHLP") && !permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                if (permohonan.getKodUrusan().getJabatan().getKod().equals("2")) {
                    senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                    String kodL = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                    String kodUnit = getContext().getRequest().getParameter("kodUnitLuas.kod");
                    LOG.info("kodL - " + kodL);
                    LOG.info("kodUnit - " + kodUnit);

                    if (kodL == null) {
                        addSimpleError("Sila masukkan nilai Luas dan Unit Luas");

                        return new JSP(DisPermohonanPage.getLP_STS_TANAH()).addParameter("tab", "true");
                    }


                    if (senaraiHakmilikPermohonan != null) {
                        for (int x = 0; x < senaraiHakmilikPermohonan.size(); x++) {
                            System.out.println("SIMPAN LUAS ::::::: ");
                            HakmilikPermohonan mohonHmlk = new HakmilikPermohonan();
                            mohonHmlk = senaraiHakmilikPermohonan.get(x);
                            if (mohonHmlk != null) {
                                mohonHmlk.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());

                                if (kodL != null) {
                                    mohonHmlk.setLuasLulusUom(kodUOMDAO.findById(kodUnit));
                                }
                                mohonHmlk.setInfoAudit(disLaporanTanahService.findAudit(mohonHmlk, "update", pguna));
                                plpservice.simpanHakmilikPermohonan(mohonHmlk);
                            }
                        }
                    }

                    mohonLaporPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                String kodL = getContext().getRequest().getParameter("kodLuas");

                if (luas == null) {
                    addSimpleError("Sila masukkan nilai Luas dan Unit Luas");

                    return new JSP(DisPermohonanPage.getLP_STS_TANAH()).addParameter("tab", "true");
                }

                LOG.info("kodL - " + kodL);
                LOG.info("luas - " + luas);

                if (senaraiHakmilikPermohonan != null) {
                    for (int x = 0; x < senaraiHakmilikPermohonan.size(); x++) {
                        System.out.println("SIMPAN LUAS ::::::: ");
                        HakmilikPermohonan mohonHmlk = new HakmilikPermohonan();
                        mohonHmlk = senaraiHakmilikPermohonan.get(x);
                        if (mohonHmlk != null) {
                            mohonHmlk.setLuasDiluluskan(luas);

                            if (kodL != null) {
                                mohonHmlk.setLuasLulusUom(kodUOMDAO.findById(kodL));
                            }
                            mohonHmlk.setInfoAudit(disLaporanTanahService.findAudit(mohonHmlk, "update", pguna));
                            plpservice.simpanHakmilikPermohonan(mohonHmlk);
                        }
                    }
                }

                mohonLaporPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
            } else {
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            }
            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan, idHakmilik}, 1);
        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            String kodL = getContext().getRequest().getParameter("kodLuas");

            if (senaraiHakmilikPermohonan != null) {
                for (int x = 0; x < senaraiHakmilikPermohonan.size(); x++) {
                    System.out.println("SIMPAN LUAS ::::::: ");
                    if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                        HakmilikPermohonan mohonHmlk = new HakmilikPermohonan();
                        mohonHmlk = senaraiHakmilikPermohonan.get(x);
                        if (mohonHmlk != null) {
                            mohonHmlk.setLuasDiluluskan(luas);
                            mohonHmlk.setLuasLulusUom(kodUOMDAO.findById(kodL));
                            mohonHmlk.setInfoAudit(disLaporanTanahService.findAudit(mohonHmlk, "update", pguna));
                            plpservice.simpanHakmilikPermohonan(mohonHmlk);
                        }
                    }
                }
            }

            mohonLaporPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
//            permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT") ||
        } else if (permohonan.getKodUrusan().getJabatan().getKod().equals("2")) {
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            String kodL = "";
            String kodUnit = "";

            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
                kodL = getContext().getRequest().getParameter("luas");
                kodUnit = getContext().getRequest().getParameter("kodLuas");
            } else {
                kodL = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                kodUnit = getContext().getRequest().getParameter("kodUnitLuas.kod");
            }
            LOG.info("kodL - " + kodL);
            LOG.info("kodUnit - " + kodUnit);

            if (kodL == null) {
                addSimpleError("Sila masukkan nilai Luas dan Unit Luas");

                return new JSP(DisPermohonanPage.getLP_STS_TANAH()).addParameter("tab", "true");
            }

            if (senaraiHakmilikPermohonan != null) {
                for (int x = 0; x < senaraiHakmilikPermohonan.size(); x++) {
                    System.out.println("SIMPAN LUAS ::::::: ");
                    HakmilikPermohonan mohonHmlk = new HakmilikPermohonan();
                    mohonHmlk = senaraiHakmilikPermohonan.get(x);
                    if (mohonHmlk != null) {
                        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                            DecimalFormat decimalFormat = new DecimalFormat();
                            decimalFormat.setParseBigDecimal(true);
                            BigDecimal kodLDecimal = (BigDecimal) decimalFormat.parse(kodL);
                            mohonHmlk.setLuasDiluluskan(kodLDecimal);
                        } else if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
                            BigDecimal bd = new BigDecimal(kodL);
                            mohonHmlk.setLuasDiluluskan(bd);
                        }

                        if (kodL != null) {
                            mohonHmlk.setLuasLulusUom(kodUOMDAO.findById(kodUnit));
                        }
                        mohonHmlk.setInfoAudit(disLaporanTanahService.findAudit(mohonHmlk, "update", pguna));
                        plpservice.simpanHakmilikPermohonan(mohonHmlk);
                    }
                }
            }

            mohonLaporPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPtTemp = new NoPt();
//            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{noPtSementara}, 2);
            mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, mohonHakmilik.getId());
        } else {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan}, 0);
        }

        /*
         * MOHON LAPOR PELAN
         */
        mohonLaporPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
            for (HakmilikPermohonan hm : senaraiHakmilikPermohonan) {
                for (PermohonanLaporanPelan plp : mohonLaporPelanList) {
                    if (plp == null) {
                        plp = new PermohonanLaporanPelan();
                        plp.setCawangan(permohonan.getCawangan());
                        plp.setPermohonan(permohonan);
                        plp.setInfoAudit(disLaporanTanahService.findAudit(plp, "new", pguna));
                        plp.setHakmilikPermohonan(hm);
                        plp.setPermohonan(permohonan);
                        plp.setNoLitho(noLitho);
                        plp.setCatatan(catatan);
                        plp.setProjekKerajaan(projekKerajaan);
                    } else {
                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
                        if (!senaraiHakmilikPermohonan.isEmpty()) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                                if (hp.getHakmilik() == null) {
                                    plp.setInfoAudit(disLaporanTanahService.findAudit(plp, "update", pguna));
                                    plp.setCawangan(permohonan.getCawangan());
                                    plp.setNoLitho(noLitho);
                                    plp.setProjekKerajaan(projekKerajaan);
                                    if (hp.getHakmilik() != null) {
                                        if (hp.getHakmilik().getIdHakmilik().isEmpty() || hp.getHakmilik().getIdHakmilik() == null) {
                                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(plp);
                                        }
                                    } else {
                                        plp.setCatatan(catatan);
                                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(plp);
                                    }
                                } else {
                                    plp.setNoLitho(noLitho);
                                    plp.setCawangan(permohonan.getCawangan());
                                    plp.setPermohonan(permohonan);
                                    plp.setHakmilikPermohonan(hp);
                                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(plp);
                                }
                            }
                        } else {
                            plp.setInfoAudit(disLaporanTanahService.findAudit(plp, "update", pguna));
                            plp.setCawangan(permohonan.getCawangan());
                            plp.setNoLitho(noLitho);
                            plp.setProjekKerajaan(projekKerajaan);
                            plp.setCatatan(catatan);
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(plp);
                        }
                    }

                    if (StringUtils.isNotBlank(kodT)) {
                        KodTanah ktt = disLaporanTanahService.getKodTanahDAO().findById(kodT);
                        if (plp != null) {
                            plp.setKodTanah(ktt);
                            plp.setInfoAudit(disLaporanTanahService.findAudit(plp, "update", pguna));
                        } else {
                            plp = new PermohonanLaporanPelan();
                            plp.setCawangan(permohonan.getCawangan());
                            plp.setPermohonan(permohonan);
                            plp.setInfoAudit(disLaporanTanahService.findAudit(plp, "new", pguna));
                            plp.setHakmilikPermohonan(hm);
                        }
                        if (kodT.equals("99")) { //Lain - lain
                            LOG.info("Adding for Ulasan Laporan Pelan for Lain-lain");
                            permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                            InfoAudit ia = new InfoAudit();
                            if (permohonanLaporanUlasan == null) {
                                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());

                            } else {
                                ia = permohonanLaporanUlasan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            }
                            permohonanLaporanUlasan.setInfoAudit(ia);
                            permohonanLaporanUlasan.setPermohonan(permohonan);
                            permohonanLaporanUlasan.setCawangan(cawangan);
                            permohonanLaporanUlasan.setJenisUlasan("JT_LL");
                            KodDokumen kodDok = kodDokumenDAO.findById("LPE");
                            permohonanLaporanUlasan.setKodDokumen(kodDok);
                            permohonanLaporanUlasan.setUlasan(ulasan);
                            pelupusanService.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);

                        } else {
                            permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                            LOG.info("Delete for Ulasan Laporan Pelan for Lain-lain");
                            if (permohonanLaporanUlasan != null) {
                                pelupusanService.deletePermohonanLaporanUlasan(permohonanLaporanUlasan);
                            }

                        }
                        if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
//                if ((noLitho == null) && (kodD.equals("")) && (kodT == null) && (kodT.equals(""))) {
//                    addSimpleError("SILA PENUHKAN PADA RUANG MANDATORI");
//
//                }
                            if (noLitho == null) {
                                addSimpleError("SILA ISI NO PIAWAI 1");
                            }
                            if (kodD == null) {
                                addSimpleError("SILA ISI NO PIAWAI 2");
                            }
                            if ((kodT == null) || (kodT.equals(""))) {
                                addSimpleError("SILA ISI NO TANAH");
                            } else {
                                disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(plp);
                            }
                        } else {
                            disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(plp);
                        }
                    }


                    /*
                     * HAKMILIKPERMOHONAN
                     */
                    if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        if (hm != null) {
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
                            kodUL = new KodUOM();
                            String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
                            if (hm != null && hm.getLuasTerlibat() != null) {
                                hm.setLuasTerlibat(hm.getLuasTerlibat());
                            }
                            if (StringUtils.isNotBlank(kod2)) {
                                kodUL.setKod(kod2);
                                hm.setKodUnitLuas(kodUL);
                            }
                            hm.setInfoAudit(disLaporanTanahService.findAudit(hm, "new", pguna));
                            disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hm);
                        }
                    } else {
                        for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                            if (hp != null) {
                                String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");

                                if ((kodS != null) && !kodS.equals("0")) {
                                    Integer a = Integer.parseInt(kodS);
                                    int kod = a.intValue();
                                    KodSeksyen kodSeksyen = disLaporanTanahService.getPlpservice().findByKodSeksyen(kod);
                                    hp.setKodSeksyen(kodSeksyen);
                                }
                                if (!Character.isWhitespace(kodP)) {
                                    KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                                    hp.setKodMilik(kpm);
                                }
                                if (!StringUtils.isEmpty(kodPar)) {
                                    KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                                    hp.setKodKawasanParlimen(kw);
                                }
                                kodD = getContext().getRequest().getParameter("kodDun");
                                if (kodD != null) {
                                    KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                                    hp.setKodDUN(kd);
                                }
                                kodUL = new KodUOM();
                                String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
                                if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null) {
                                    hp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                                }
                                if (StringUtils.isNotBlank(kod2)) {
                                    kodUL.setKod(kod2);
                                    hp.setKodUnitLuas(kodUL);
                                }

                                //checking before save
                                if ((kodD == null) || (kodD.equals("")) || (kodPar.equalsIgnoreCase("")) || (Character.isWhitespace(kodP))) {
                                    addSimpleError("SILA PENUHKAN PADA RUANG MANDATORI");

                                } else {
                                    hp.setInfoAudit(disLaporanTanahService.findAudit(hp, "update", pguna));
                                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hp);

                                }
                            }
                        }
                    }
                }

            }
            addSimpleMessage("Maklumat Telah Berjaya disimpan");
        } else {
            if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                LOG.info("idPermohonan == " + idPermohonan);
                LOG.info("idHakmilik == " + idHakmilik);
//                HakmilikPermohonan mohonHmlk = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik("L", idPermohonan, idHakmilik);
                List<HakmilikPermohonan> mohonHmlkList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan mohonHmlk : mohonHmlkList) {
                    if (mohonHmlk != null) {
                        mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, mohonHmlk.getId());
                    }
                    if (mohonLaporPelan == null) {

//            permohonanLaporanPelan = new PermohonanLaporanPelan();
                        mohonLaporPelan = new PermohonanLaporanPelan();
                        mohonLaporPelan.setCawangan(permohonan.getCawangan());
                        mohonLaporPelan.setPermohonan(permohonan);
                        mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pguna));
                        if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                            mohonLaporPelan.setHakmilikPermohonan(senaraiHakmilikPermohonan.get(0));
                        } else {
                            mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                        }
                        mohonLaporPelan.setPermohonan(permohonan);
                        mohonLaporPelan.setNoLitho(noLitho);
                        mohonLaporPelan.setCatatan(catatan);
                        mohonLaporPelan.setProjekKerajaan(projekKerajaan);
//            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);

                    } else {
//                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                        if (mohonHmlk != null) {
//                        for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                            if (mohonHmlk.getHakmilik() == null) {
                                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                mohonLaporPelan.setNoLitho(noLitho);
                                mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                                if (mohonHmlk.getHakmilik() != null) {
                                    if (mohonHmlk.getHakmilik().getIdHakmilik().isEmpty() || mohonHmlk.getHakmilik().getIdHakmilik() == null) {
                                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                    }
                                } else {
                                    mohonLaporPelan.setCatatan(catatan);
                                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                }
                            } else {
                                mohonLaporPelan.setNoLitho(noLitho);
                                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                mohonLaporPelan.setPermohonan(permohonan);
                                mohonLaporPelan.setHakmilikPermohonan(mohonHmlk);
                                laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                            }
//                        }
                        } else {
                            mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                            mohonLaporPelan.setCawangan(permohonan.getCawangan());
                            mohonLaporPelan.setNoLitho(noLitho);
                            mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                            mohonLaporPelan.setCatatan(catatan);
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                        }
                    }
                }
            } else {
                if (mohonLaporPelan == null) {

//            permohonanLaporanPelan = new PermohonanLaporanPelan();
                    mohonLaporPelan = new PermohonanLaporanPelan();
                    mohonLaporPelan.setCawangan(permohonan.getCawangan());
                    mohonLaporPelan.setPermohonan(permohonan);
                    mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pguna));
                    if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        mohonLaporPelan.setHakmilikPermohonan(senaraiHakmilikPermohonan.get(0));
                    } else {
                        mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                    }
                    mohonLaporPelan.setPermohonan(permohonan);
                    mohonLaporPelan.setNoLitho(noLitho);
                    mohonLaporPelan.setCatatan(catatan);
                    mohonLaporPelan.setProjekKerajaan(projekKerajaan);
//            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);

                } else {
//            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                    if (!senaraiHakmilikPermohonan.isEmpty()) {
                        for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                            if (hp.getHakmilik() == null) {
//                LOG.info(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                mohonLaporPelan.setNoLitho(noLitho);
                                mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                                if (hp.getHakmilik() != null) {
                                    if (hp.getHakmilik().getIdHakmilik().isEmpty() || hp.getHakmilik().getIdHakmilik() == null) {
                                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                    }
                                } else {
                                    mohonLaporPelan.setCatatan(catatan);
                                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                }
                            } else {
                                mohonLaporPelan.setNoLitho(noLitho);
                                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                mohonLaporPelan.setPermohonan(permohonan);
                                mohonLaporPelan.setHakmilikPermohonan(hp);
                                laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                            }
                        }
                    } else {
                        senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().findHakmilikPermohonanList(idPermohonan);

                        for (HakmilikPermohonan hmhn : senaraiHakmilikPermohonan) {
//                            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan}, 0);
                            if (hmhn != null) {
                                mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, hmhn.getId());
                            }
                            if (mohonLaporPelan == null) {
                                mohonLaporPelan = new PermohonanLaporanPelan();
                                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                mohonLaporPelan.setPermohonan(permohonan);
                                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pguna));
                                mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                                mohonLaporPelan.setNoLitho(noLitho);
                                mohonLaporPelan.setCatatan(catatan);
                                mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                                    if (!StringUtils.isEmpty(kodPar)) {
                                        KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                                        LOG.info("---Kaw Perlimen ---" + kw);
                                        mohonHakmilik.setKodKawasanParlimen(kw);
                                    }
                                    kodD = getContext().getRequest().getParameter("kodDun");
                                    if (kodD != null) {
                                        KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                                        LOG.info("---Kaw DUN ---" + kd);
                                        mohonHakmilik.setKodDUN(kd);
                                    }
                                    if (!Character.isWhitespace(kodP)) {
                                        KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                                        LOG.info("---Status Tanah ---" + kpm);
                                        mohonHakmilik.setKodMilik(kpm);
                                    }
                                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hmhn);
                            } else {
                                if (hmhn != null) {
                                    if (hmhn.getHakmilik() == null) {
                                        mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                                        mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                        mohonLaporPelan.setNoLitho(noLitho);
                                        mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                                        mohonLaporPelan.setCatatan(catatan);
                                        if (!StringUtils.isEmpty(kodPar)) {
                                        KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                                        LOG.info("---Kaw Perlimen ---" + kw);
                                        hmhn.setKodKawasanParlimen(kw);
                                        }
                                        kodD = getContext().getRequest().getParameter("kodDun");
                                        if (kodD != null) {
                                            KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                                            LOG.info("---Kaw DUN ---" + kd);
                                            hmhn.setKodDUN(kd);
                                        }
                                        if (!Character.isWhitespace(kodP)) {
                                            KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                                            LOG.info("---Status Tanah ---" + kpm);
                                            hmhn.setKodMilik(kpm);
                                        }

                                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hmhn);
                                    } else {
                                        mohonLaporPelan.setNoLitho(noLitho);
                                        mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                        mohonLaporPelan.setPermohonan(permohonan);
                                        mohonLaporPelan.setHakmilikPermohonan(hmhn);
                                        if (!StringUtils.isEmpty(kodPar)) {
                                        KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                                        LOG.info("---Kaw Perlimen ---" + kw);
                                        hmhn.setKodKawasanParlimen(kw);
                                        }
                                        kodD = getContext().getRequest().getParameter("kodDun");
                                        if (kodD != null) {
                                            KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                                            LOG.info("---Kaw DUN ---" + kd);
                                            hmhn.setKodDUN(kd);
                                        }
                                        if (!Character.isWhitespace(kodP)) {
                                            KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                                            LOG.info("---Status Tanah ---" + kpm);
                                            hmhn.setKodMilik(kpm);
                                        }

                                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hmhn);
                                    }
                                } else {
                                    mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                                    LOG.info("---info audit ---" + mohonLaporPelan.getInfoAudit());
                                    mohonLaporPelan.setCawangan(permohonan.getCawangan());
                                    mohonLaporPelan.setNoLitho(noLitho);
                                    mohonLaporPelan.setProjekKerajaan(projekKerajaan);
                                    mohonLaporPelan.setCatatan(catatan);
                                    mohonLaporPelan.setHakmilikPermohonan(hmhn);
                                    LOG.info("---mohon hakmilik ---" + hmhn);
                                    mohonLaporPelan.setPermohonan(permohonan);
                                    LOG.info("---id mohon ---" + permohonan);                                   
                                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                                }
                            }

//                            mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
//                            LOG.info("---info audit ---" + mohonLaporPelan.getInfoAudit());
//                            mohonLaporPelan.setCawangan(permohonan.getCawangan());
//                            mohonLaporPelan.setNoLitho(noLitho);
//                            mohonLaporPelan.setProjekKerajaan(projekKerajaan);
//                            mohonLaporPelan.setCatatan(catatan);
//                            mohonLaporPelan.setHakmilikPermohonan(hmhn);
//                            LOG.info("---mohon hakmilik ---" + hmhn);
//                            mohonLaporPelan.setPermohonan(permohonan);
//                            LOG.info("---id mohon ---" + permohonan);
//                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(mohonLaporPelan);
                        }
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
                    if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        mohonLaporPelan.setHakmilikPermohonan(senaraiHakmilikPermohonan.get(0));
                    } else {
                        mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                    }
//                mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                }
                if (kodT.equals("99")) { //Lain - lain
                    LOG.info("Adding for Ulasan Laporan Pelan for Lain-lain");
                    permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                    InfoAudit ia = new InfoAudit();
                    if (permohonanLaporanUlasan == null) {
                        permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());

                    } else {
                        ia = permohonanLaporanUlasan.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                    permohonanLaporanUlasan.setInfoAudit(ia);
                    permohonanLaporanUlasan.setPermohonan(permohonan);
                    permohonanLaporanUlasan.setCawangan(cawangan);
                    permohonanLaporanUlasan.setJenisUlasan("JT_LL");
                    KodDokumen kodDok = kodDokumenDAO.findById("LPE");
                    permohonanLaporanUlasan.setKodDokumen(kodDok);
                    permohonanLaporanUlasan.setUlasan(ulasan);
                    pelupusanService.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);

                } else {
                    permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                    LOG.info("Delete for Ulasan Laporan Pelan for Lain-lain");
                    if (permohonanLaporanUlasan != null) {
                        pelupusanService.deletePermohonanLaporanUlasan(permohonanLaporanUlasan);
                    }

                }
                if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
//                if ((noLitho == null) && (kodD.equals("")) && (kodT == null) && (kodT.equals(""))) {
//                    addSimpleError("SILA PENUHKAN PADA RUANG MANDATORI");
//
//                }
                    if (noLitho == null) {
                        addSimpleError("SILA ISI NO PIAWAI 1");
                    }
                    if (kodD == null) {
                        addSimpleError("SILA ISI NO PIAWAI 2");
                    }
                    if ((kodT == null) || (kodT.equals(""))) {
                        addSimpleError("SILA ISI NO TANAH");
                    } else {
                        disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);
                    }
                } else {
                    disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);
                }
            }


            /*
             * HAKMILIKPERMOHONAN
             */
            if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                if (mohonHakmilik == null) {
                    mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                }

                if (mohonHakmilik != null) {
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
                    kodUL = new KodUOM();
                    String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null) {
                        mohonHakmilik.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                    }
                    if (StringUtils.isNotBlank(kod2)) {
                        kodUL.setKod(kod2);
                        mohonHakmilik.setKodUnitLuas(kodUL);
                    }

//            if (!hakmilik.getNoLot().isEmpty()) {
//                mohonHakmilik.setNoLot(hakmilik.getNoLot());
//            }
//            if (!hakmilik.getLot().getKod().isEmpty()) {
//                mohonHakmilik.setLot(hakmilik.getLot().getKod());
//            }

//                    mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "new", pguna));
                    mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "update", pguna));
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilik);
                }
                addSimpleMessage("Maklumat Telah Berjaya disimpan");

            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                mohonHakmilik = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik("L", idPermohonan, idHakmilik);
                if (mohonHakmilik != null) {

                    String kodL = getContext().getRequest().getParameter("hakmilikPermohonan.luasDiluluskan");
                    String kodUnit = getContext().getRequest().getParameter("kodUnitLuas.kod");

                    mohonHakmilik.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());

                    if (kodL != null) {
                        mohonHakmilik.setLuasLulusUom(kodUOMDAO.findById(kodUnit));
                    }
                    mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(hp, "update", pguna));
                    plpservice.simpanHakmilikPermohonan(mohonHakmilik);

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
                    kodUL = new KodUOM();
                    String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
                    if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null) {
                        mohonHakmilik.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                    }
                    if (StringUtils.isNotBlank(kod2)) {
                        kodUL.setKod(kod2);
                        mohonHakmilik.setKodUnitLuas(kodUL);
                    }

//            if (!hakmilik.getNoLot().isEmpty()) {
//                mohonHakmilik.setNoLot(hakmilik.getNoLot());
//            }
//            if (!hakmilik.getLot().getKod().isEmpty()) {
//                mohonHakmilik.setLot(hakmilik.getLot().getKod());
//            }

                    mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "new", pguna));
                    disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilik);
                }
                addSimpleMessage("Maklumat Telah Berjaya disimpan");

            } else {
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                    if (hp != null) {
                        String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");

                        if ((kodS != null) && !kodS.equals("0")) {
                            Integer a = Integer.parseInt(kodS);
                            int kod = a.intValue();
                            KodSeksyen kodSeksyen = disLaporanTanahService.getPlpservice().findByKodSeksyen(kod);
                            hp.setKodSeksyen(kodSeksyen);
                        }
                        if (!Character.isWhitespace(kodP)) {
                            KodPemilikan kpm = disLaporanTanahService.getKodPemilikanDAO().findById(kodP);
                            hp.setKodMilik(kpm);
                        }
                        if (!StringUtils.isEmpty(kodPar)) {
                            KodKawasanParlimen kw = disLaporanTanahService.getKodKawasanParlimenDAO().findById(kodPar);
                            hp.setKodKawasanParlimen(kw);
                        }
                        kodD = getContext().getRequest().getParameter("kodDun");
                        if (kodD != null) {
                            KodDUN kd = disLaporanTanahService.getKodDUNDAO().findById(kodD);
                            hp.setKodDUN(kd);
                        }
                        kodUL = new KodUOM();
                        String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
                        if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null) {
                            hp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                        }
                        if (StringUtils.isNotBlank(kod2)) {
                            kodUL.setKod(kod2);
                            hp.setKodUnitLuas(kodUL);
                        }

                        //checking before save
                        if ((kodD == null) || (kodD.equals("")) || (kodPar.equalsIgnoreCase("")) || (Character.isWhitespace(kodP))) {
                            addSimpleError("SILA PENUHKAN PADA RUANG MANDATORI");

                        } else {
                            hp.setInfoAudit(disLaporanTanahService.findAudit(hp, "update", pguna));
                            disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hp);

//                        rehydrate();
                            addSimpleMessage("Maklumat Telah Berjaya disimpan");
                        }
                    }
                }
            }
        }
//        rehydrate();
        return new JSP(DisPermohonanPage.getLP_STS_TANAH()).addParameter("tab", "true");

    }

    public Resolution simpanLuasBaru() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        InfoAudit info = new InfoAudit();

        if (mohonHakmilik != null) {
            /*
             * HAKMILIKPERMOHONAN
             */

            String kod2 = getContext().getRequest().getParameter("luasLulusUom.kod");
            LOG.info("kod2 : " + kod2);
//            String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
            if (hakmilikPermohonan != null && hakmilikPermohonan.getLuasTerlibat() != null) {
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonHakmilik.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
            }
            if (StringUtils.isNotBlank(kod2)) {
//                kodUL = new KodUOM();
//                KodUOM kodUOM = kodUOMDAO.findById(kod2);
//                kodUL.setKod(kod2);
                mohonHakmilik.setLuasLulusUom(hakmilikPermohonan.getLuasLulusUom());
            }

//            mohonHakmilik.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilik, "update", pguna));
            disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilik);
        }

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(DisPermohonanPage.getLP_LUASBARU()).addParameter("tab", "true");
//        return new JSP(DisPermohonanPage.getLP_MAIN_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpanCatatan() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        noPtSementara = ctx.getRequest().getParameter("noPtSementara");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
            } else {
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
            }
            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan, idHakmilik}, 1);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{noPtSementara}, 2);
            mohonLaporPelan = pelupusanService.findByIdPermohonanPelanNIdMH(idPermohonan, mohonHakmilik.getId());
        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            hakmilikMohonList = pelupusanService.findHakmilikPermohonanList(idPermohonan);
            mohonLaporanPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
        } else {
//            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonan(idPermohonan);
//            mohonLaporPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(mohonLaporPelan, new String[]{idPermohonan}, 0);
            mohonLaporanPelanList = pelupusanService.findPermohonanLaporanPelanListByIdPermohonan(idPermohonan);
        }

        if (mohonLaporanPelanList != null) {
            for (int x = 0; x < mohonLaporanPelanList.size(); x++) {
                if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    PermohonanLaporanPelan mohon = new PermohonanLaporanPelan();
                    mohon = mohonLaporanPelanList.get(x);
                    if (mohon == null) {
                        mohon.setHakmilikPermohonan(mohonHakmilik);
                        mohon.setCawangan(permohonan.getCawangan());
                        mohon.setPermohonan(permohonan);
                        mohon.setCatatan(catatan);
                        mohon.setHakmilikPermohonan(hakmilikPermohonan);
                        mohon.setInfoAudit(disLaporanTanahService.findAudit(mohon, "new", pguna));
                        plpservice.simpanPermohonanLaporanPelan(mohon);
                    } else {
                        mohon.setCatatan(catatan);
                        mohon.setInfoAudit(disLaporanTanahService.findAudit(mohon, "update", pguna));
                        plpservice.simpanPermohonanLaporanPelan(mohon);
                    }
                }
            }
        } else {
            if (mohonLaporPelan == null) {
                mohonLaporPelan = new PermohonanLaporanPelan();
                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                mohonLaporPelan.setPermohonan(permohonan);
                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pguna));
                if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    mohonLaporPelan.setHakmilikPermohonan(senaraiHakmilikPermohonan.get(0));
                } else {
                    mohonLaporPelan.setHakmilikPermohonan(mohonHakmilik);
                }
                mohonLaporPelan.setPermohonan(permohonan);
                mohonLaporPelan.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);
            } else {
                mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "update", pguna));
                mohonLaporPelan.setCawangan(permohonan.getCawangan());
                mohonLaporPelan.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonanLaporanPelan(mohonLaporPelan);
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(DisPermohonanPage.getLP_CATATAN()).addParameter("tab", "true");

    }

    public Resolution tutup() throws ParseException {
        System.out.println("------------ tutup ------------");
        rehydrate();
        return new JSP(DisPermohonanPage.getLP_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution cariPermohonan() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        if (id == null || id.equals("")) {
            addSimpleError("Masukkan Permohonan Id");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
            return new JSP("pelupusan/laporan_pelan/laporan_pelanEdit/laporan_pelanV2AddPermohonanTerdahulu.jsp").addParameter("popup", "true");
        }
        prmhnn = permohonanDAO.findById(id);
        if (prmhnn != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
            pemohon = plpservice.findPemohonByIdPemohon(prmhnn.getIdPermohonan());
            System.out.println("----pemohon-------" + pemohon);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
//            hmpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            addSimpleMessage("Maklumat dijumpai");
            id2 = id;
        } else {
            addSimpleError("Maklumat tidak dijumpai");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
        }
        return new JSP("pelupusan/laporan_pelan/laporan_pelanEdit/laporan_pelanV2AddPermohonanTerdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPermohonanSblm() throws ParseException {
//        prmhnn = permohonanDAO.findById(id2);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonan.setPermohonanSebelum(prmhnn);
        cawangan = permohonan.getCawangan();
//        plpservice.simpanPermohonan(permohonan);
        LOG.info("------simpan---Permohonan Terdahulu--------------::");
        LOG.info("------idPermohonan--------------::" + idPermohonan);
        LOG.info("------NoFail--------------::" + id2);
        permohonanManual = plpservice.findByIdMohonFailNo(idPermohonan, id2);

        InfoAudit infoAudit = new InfoAudit();
        if (permohonanManual != null) {
            LOG.info("--------permohonanManual NOT Null------------::");
            infoAudit = permohonanManual.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("--------permohonanManual Null------------::");
            permohonanManual = new PermohonanManual();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanManual.setInfoAudit(infoAudit);
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setNoFail(id2);
            permohonanManual.setCawangan(cawangan);
        }
        plpservice.simpanPermohonanManual(permohonanManual);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();

        return new JSP(DisPermohonanPage.getLP_MOHON_TERDAHULU()).addParameter("tab", "true");

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

        plpservice.simpanPermohonanManual(permohonanManual);

        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        return new JSP(DisPermohonanPage.getLP_MOHON_TERDAHULU()).addParameter("tab", "true");
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
        PermohonanManual pml = plpservice.findByIdMohonFailNo(idPermohonan, no_Fail);
        per = plpservice.findIdPermohonanByIdManual(pml.getNoFail());
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
            } else if (keputusan.equals("T")) {
                p.setKeputusan(keputusanDAO.findById(keputusan));
            }
//            p.setKeputusanOleh(peng);
            p.setTarikhKeputusan(sdf.parse(tarikhKeputusan));
            p.setPenyerahNama(pmhn);
            p.setNamaPenerima(keputusanOleh);

            plpservice.simpanPermohonan(p);
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
            } else if (keputusan.equals("T")) {
                per.setKeputusan(keputusanDAO.findById(keputusan));
            }
//            per.setKeputusanOleh(peng);
            per.setTarikhKeputusan(sdf.parse(tarikhKeputusan));
            per.setPenyerahNama(pmhn);
            per.setNamaPenerima(keputusanOleh);

            plpservice.simpanPermohonan(per);
        }

        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        return new JSP(DisPermohonanPage.getLP_MOHON_TERDAHULU()).addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_pelan/laporan_pelanEdit/Update_laporan_pelanV2AddFail.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKawasan() throws ParseException {
        PermohonanLaporanKawasan kws = null;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        noPtSementara = getContext().getRequest().getParameter("noPtSementara");
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idtanahrizabPermohonan");
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        NoPt noPt = new NoPt();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPt = (NoPt) disLaporanTanahService.findObject(noPt, new String[]{noPtSementara}, 1);
//            mohonHakmilik = noPt.getHakmilikPermohonan();
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{noPtSementara}, 2);
        } else {
            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
        }
        InfoAudit infoAudit = new InfoAudit();

        if (StringUtils.isNotEmpty(idtanahrizabPermohonan)) {
            kws = new PermohonanLaporanKawasan();
            kws = disLaporanTanahService.getPelupusanService().findPermohonanLaporanKawasanById(Long.valueOf(idtanahrizabPermohonan));
        }

        if (kws != null) {
            if (StringUtils.isNotEmpty(kodRizab) && StringUtils.isNotEmpty(addnoWarta) && StringUtils.isNotEmpty(addtarikhWarta) && StringUtils.isNotEmpty(addnoPelanWarta)) {
                if (kodRizab.equals("99")) {
                    if (StringUtils.isNotEmpty(catatan)) {
                        infoAudit = kws.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                        kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
                        kws.setCatatan(catatan);
                        kws.setNoWarta(addnoWarta);
                        kws.setInfoAudit(infoAudit);
                        if (addtarikhWarta != null && !("").equals(addtarikhWarta)) {
                            kws.setTarikhWarta(sdf.parse(addtarikhWarta));
                        }
                        kws.setNoPelanWarta(addnoPelanWarta);
//                        kws.setCatatan(catatanKWS);
                        System.out.println("-------hakmilik permohonan ialah : " + mohonHakmilik);
                        plpservice.simpanPermohonanLaporKawasan(kws);
                        addSimpleMessage("Maklumat berjaya disimpan.");
                    } else {
                        addSimpleError("Sila masukkan Catatan terlebih dahulu.");
                    }
                } else {
                    infoAudit = kws.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
                    kws.setNoWarta(addnoWarta);
                    kws.setInfoAudit(infoAudit);
                    if (addtarikhWarta != null && !("").equals(addtarikhWarta)) {
                        kws.setTarikhWarta(sdf.parse(addtarikhWarta));
                    }
                    kws.setNoPelanWarta(addnoPelanWarta);
//                    kws.setCatatan(catatanKWS);
                    System.out.println("-------hakmilik permohonan ialah : " + mohonHakmilik);
                    plpservice.simpanPermohonanLaporKawasan(kws);
                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            } else {
                addSimpleError("Sila lengkapkan semua maklumat terlebih dahulu.");
            }
        } else {
            if (StringUtils.isNotEmpty(kodRizab) && StringUtils.isNotEmpty(addnoWarta) && StringUtils.isNotEmpty(addtarikhWarta) && StringUtils.isNotEmpty(addnoPelanWarta)) {
                if (kodRizab.equals("99")) {
                    if (StringUtils.isNotEmpty(catatan)) {
                        kws = new PermohonanLaporanKawasan();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
                        kws.setCatatan(catatan);
                        kws.setNoWarta(addnoWarta);
                        kws.setInfoAudit(infoAudit);
                        if (addtarikhWarta != null && !("").equals(addtarikhWarta)) {
                            kws.setTarikhWarta(sdf.parse(addtarikhWarta));
                        }
                        kws.setNoPelanWarta(addnoPelanWarta);
                        kws.setPermohonan(permohonan);
                        kws.setKodCawangan(permohonan.getCawangan());
                        kws.setHakmilikPermohonan(mohonHakmilik);
//                        kws.setCatatan(catatanKWS);
                        System.out.println("-------hakmilik permohonan ialah : " + mohonHakmilik);
                        plpservice.simpanPermohonanLaporKawasan(kws);
                        addSimpleMessage("Maklumat berjaya disimpan.");
                    } else {
                        addSimpleError("Sila masukkan Catatan terlebih dahulu.");
                    }
                } else {
                    kws = new PermohonanLaporanKawasan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
                    kws.setCatatan(catatan);
                    kws.setNoWarta(addnoWarta);
                    kws.setInfoAudit(infoAudit);
                    if (addtarikhWarta != null && !("").equals(addtarikhWarta)) {
                        kws.setTarikhWarta(sdf.parse(addtarikhWarta));
                    }
                    kws.setNoPelanWarta(addnoPelanWarta);
                    kws.setPermohonan(permohonan);
                    kws.setKodCawangan(permohonan.getCawangan());
                    kws.setHakmilikPermohonan(mohonHakmilik);
//                    kws.setCatatan(catatanKWS);
                    System.out.println("-------hakmilik permohonan ialah : " + mohonHakmilik);
                    plpservice.simpanPermohonanLaporKawasan(kws);
                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            } else {
                addSimpleError("Sila lengkapkan semua maklumat terlebih dahulu.");
            }
        }
        rehydrate();
        return new JSP(DisPermohonanPage.getLP_ADD_DLM_KWS()).addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKand");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor));
            addSimpleError("Maklumat Berjaya Dihapuskan");
        }
        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }

    public Resolution updateRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKand");
        LOG.info("idKand : " + idKand);
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");

        pm = plpservice.findByidMohonManual(Long.parseLong(idKand));
        per = plpservice.findIdPermohonanByIdManual(pm.getNoFail());

        if (per != null) {
            kodUrusan = per.getKodUrusan().getKod();
            keputusan = per.getKeputusan().getNama();
            keputusanOleh = per.getNamaPenerima();
            tarikhKeputusan = sdf.format(per.getTarikhKeputusan());
            pmhn = per.getPenyerahNama();
        }

//        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_pelan/laporan_pelanEdit/Update_laporan_pelanV2AddFail.jsp").addParameter("tab", "true");
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public KodKawasanParlimenDAO getKodKawasanParlimenDAO() {
        return kodKawasanParlimenDAO;
    }

    public void setKodKawasanParlimenDAO(KodKawasanParlimenDAO kodKawasanParlimenDAO) {
        this.kodKawasanParlimenDAO = kodKawasanParlimenDAO;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
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

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKWS() {
        return permohonanLaporanKWS;
    }

    public void setPermohonanLaporanKWS(PermohonanLaporanKawasan permohonanLaporanKWS) {
        this.permohonanLaporanKWS = permohonanLaporanKWS;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
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

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public NoPt getNoPtTemp() {
        return noPtTemp;
    }

    public void setNoPtTemp(NoPt noPtTemp) {
        this.noPtTemp = noPtTemp;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public List<Permohonan> getPermohonanTerdahuluList() {
        return permohonanTerdahuluList;
    }

    public void setPermohonanTerdahuluList(List<Permohonan> permohonanTerdahuluList) {
        this.permohonanTerdahuluList = permohonanTerdahuluList;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public List<Permohonan> getPermohonanSebelumList() {
        return permohonanSebelumList;
    }

    public void setPermohonanSebelumList(List<Permohonan> permohonanSebelumList) {
        this.permohonanSebelumList = permohonanSebelumList;
    }

    public List<DisPermohonanTanahTerdahulu> getListpermohonanTanahTerdahulu() {
        return listpermohonanTanahTerdahulu;
    }

    public void setListpermohonanTanahTerdahulu(List<DisPermohonanTanahTerdahulu> listpermohonanTanahTerdahulu) {
        this.listpermohonanTanahTerdahulu = listpermohonanTanahTerdahulu;
    }

    public List<NoPt> getSenaraiNoPt() {
        return senaraiNoPt;
    }

    public void setSenaraiNoPt(List<NoPt> senaraiNoPt) {
        this.senaraiNoPt = senaraiNoPt;
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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoLPS() {
        return noLPS;
    }

    public void setNoLPS(String noLPS) {
        this.noLPS = noLPS;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoPtSementara() {
        return noPtSementara;
    }

    public void setNoPtSementara(String noPtSementara) {
        this.noPtSementara = noPtSementara;
    }

    public String getIdHakmilikPermohonan() {
        return idHakmilikPermohonan;
    }

    public void setIdHakmilikPermohonan(String idHakmilikPermohonan) {
        this.idHakmilikPermohonan = idHakmilikPermohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public DisLaporanPelanController getDisLaporanPelanController() {
        return disLaporanPelanController;
    }

    public void setDisLaporanPelanController(DisLaporanPelanController disLaporanPelanController) {
        this.disLaporanPelanController = disLaporanPelanController;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public TanahRizabService getTanahRizabService() {
        return tanahRizabService;
    }

    public void setTanahRizabService(TanahRizabService tanahRizabService) {
        this.tanahRizabService = tanahRizabService;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
    }

    public String getRizab_melayu() {
        return rizab_melayu;
    }

    public void setRizab_melayu(String rizab_melayu) {
        this.rizab_melayu = rizab_melayu;
    }

    public String getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(String kodRizab) {
        this.kodRizab = kodRizab;
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

    public String getAddnoPelanWarta() {
        return addnoPelanWarta;
    }

    public void setAddnoPelanWarta(String addnoPelanWarta) {
        this.addnoPelanWarta = addnoPelanWarta;
    }

    public String getGsa() {
        return gsa;
    }

    public void setGsa(String gsa) {
        this.gsa = gsa;
    }

    public String getHutan() {
        return hutan;
    }

    public void setHutan(String hutan) {
        this.hutan = hutan;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public String getCatatanLain() {
        return catatanLain;
    }

    public void setCatatanLain(String catatanLain) {
        this.catatanLain = catatanLain;
    }

    public String getKodT() {
        return kodT;
    }

    public void setKodT(String kodT) {
        this.kodT = kodT;
    }

    public String getKodPString() {
        return kodPString;
    }

    public void setKodPString(String kodPString) {
        this.kodPString = kodPString;
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

    public String getKodD() {
        return kodD;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
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

    public String getCatatanKWS() {
        return catatanKWS;
    }

    public void setCatatanKWS(String catatanKWS) {
        this.catatanKWS = catatanKWS;
    }

    public List<KodDUN> getListKodDun() {
        return listKodDun;
    }

    public void setListKodDun(List<KodDUN> listKodDun) {
        this.listKodDun = listKodDun;
    }

    public String getCatatanKeg() {
        return catatanKeg;
    }

    public void setCatatanKeg(String catatanKeg) {
        this.catatanKeg = catatanKeg;
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

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public String getPmhn() {
        return pmhn;
    }

    public void setPmhn(String pmhn) {
        this.pmhn = pmhn;
    }

    public PermohonanManual getPm() {
        return pm;
    }

    public void setPm(PermohonanManual pm) {
        this.pm = pm;
    }

    public List<PermohonanManual> getPmList() {
        return pmList;
    }

    public void setPmList(List<PermohonanManual> pmList) {
        this.pmList = pmList;
    }

    public Permohonan getPer() {
        return per;
    }

    public void setPer(Permohonan per) {
        this.per = per;
    }

    public List<HakmilikPermohonan> getHp() {
        return hp;
    }

    public void setHp(List<HakmilikPermohonan> hp) {
        this.hp = hp;
    }

    public String getKodDUN() {
        return kodDUN;
    }

    public void setKodDUN(String kodDUN) {
        this.kodDUN = kodDUN;
    }

    public String getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(String kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
