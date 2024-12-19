/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.Configuration;
import etanah.dao.PermohonanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.Integrasi;
import etanah.model.IntegrasiDokumen;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.NoPt;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.BPelService;
import etanah.service.JupemService;
import etanah.service.PembangunanService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanUkurController;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/PUV2")
public class PUV2ActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PUV2ActionBean.class);
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    JupemInIntegration jup;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    JupemService jupemService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Permohonan permohonanPBMT;
    private Pengguna pguna;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private List<NoPt> noPTList;
    private Boolean hntrpu;
    private String taskId;
    private String kodHakmilik;
    private String sebabUkur;
    private String lot;
    private String bahagian;
    private String sipuTundatangan;
    private String ditundatangan;
    private String jenisBayarUkur;
    private List<Pengguna> penggunaList;
    private DisPermohonanUkurController disPUController;
    private boolean checkQT;
    etanahActionBeanContext ctx;

    @DefaultHandler
    public Resolution viewOnlyPUPPTForQT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPUPPT();
        setCheckQT(Boolean.TRUE);
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyPUPTForFT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPUPPT();
        setCheckQT(Boolean.FALSE);
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyPUPPTKanan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPUPPTKanan();
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyPU() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPU();
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyPUTOJUPEM() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPUTOJUPEM();
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnly() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPUController = new DisPermohonanUkurController();
        disPUController = disPUController.viewOnlyPUTOJUPEM();
        httpSession.setAttribute("disPUController", disPUController);
        return new JSP(DisPermohonanPage.getPU_VIEW_PAGE()).addParameter("tab", "true");
    }

//stage tandatangan_surat
//    public Resolution viewSahPU() {
//        edit1 = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        simpan = Boolean.FALSE;
//        dms = Boolean.FALSE;
//        hntrpu = Boolean.FALSE;
//        view = Boolean.FALSE;
//        viewSah = Boolean.TRUE;
//        return new JSP("pelupusan/common/permohonan_ukur_prizV2.jsp").addParameter("tab", "true");
//    }
//    public Resolution hantarDMS() {
//        edit1 = Boolean.FALSE;
//        simpan = Boolean.FALSE;
//        dms = Boolean.TRUE;
//        hntrpu = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        view = Boolean.TRUE;
//        return new JSP("pelupusan/common/permohonan_ukur_prizV2.jsp").addParameter("tab", "true");
//    }
//    public Resolution chartingPU() {
//        edit1 = Boolean.FALSE;
//        simpan = Boolean.FALSE;
//        dms = Boolean.FALSE;
//        hntrpu = Boolean.TRUE;
//        edit2 = Boolean.FALSE;
//        view = Boolean.TRUE;
//        pu = Boolean.TRUE;
//        return new JSP("pelupusan/common/permohonan_ukur_prizV2.jsp").addParameter("tab", "true");
//    }
    // add SIPU
    public Resolution showSIPU() {
        return new JSP("pembangunan/melaka/sipu_tandatangan.jsp").addParameter("tab", "true");
    }
//    public Resolution showForm3() {
//        noPTList = new ArrayList<NoPt>();
//        noPTList = new ArrayList<NoPt>();
//        List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
//        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//        if(!hakmilikPermohonanList.isEmpty()){
//            HakmilikPermohonan hp = new HakmilikPermohonan();
//            hp = hakmilikPermohonanList.get(0);
//            int kodBPM = hp.getHakmilik().getBandarPekanMukim().getKod();
//            noPTList = pembangunanService.getNoPTByIdPermohonan(idPermohonan,kodBPM);
//        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
//        return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // idHakmilik = ctx.getRequest().getParameter("idHakmilik"); enable if got idHakmilik
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

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
     * Author : Shazwan Date : 9th February 2012 Purpose : To Refresh data based
     * on type given; Note : the type is set based on frame in laporanTanahV2,
     * please consult before making changes since this method is sharing
     */

    public String refreshData(String type) {
        String forwardJSP = new String();
        int typeNum = type.equals("mAsas") ? 1
                : type.equals("sHakmilik") ? 2
                : type.equals("bUkur") ? 3
                : type.equals("main") ? 4
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getPU_MASAS_PAGE();
                permohonanUkur = new PermohonanUkur();
                permohonanUkur = (PermohonanUkur) disLaporanTanahService.findObject(permohonanUkur, new String[]{idPermohonan}, 0);
                kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getPU_SHAKMILIK_PAGE();
                permohonanUkur = new PermohonanUkur();
                permohonanUkur = (PermohonanUkur) disLaporanTanahService.findObject(permohonanUkur, new String[]{idPermohonan}, 0);
                break;
            case 3:
                forwardJSP = DisPermohonanPage.getPU_BUKUR_PAGE();
                permohonanUkur = new PermohonanUkur();
                permohonanUkur = (PermohonanUkur) disLaporanTanahService.findObject(permohonanUkur, new String[]{idPermohonan}, 0);
                if (permohonanUkur.getJumlahBayaranUkur() == null && permohonanUkur.getJumlahPengecualian() == null && !StringUtils.isEmpty(permohonanUkur.getPemberiPengecualian())) {
                    jenisBayarUkur = "DP";
                } else if (permohonanUkur.getJumlahPengecualian() != null) {
                    jenisBayarUkur = "DS";
                } else if (permohonanUkur.getJumlahBayaranUkur() != null && !StringUtils.isEmpty(permohonanUkur.getJumlahBayaranUkur().toString()) && permohonanUkur.getJumlahPengecualian() != null && StringUtils.isEmpty(permohonanUkur.getJumlahPengecualian().toString()) && StringUtils.isEmpty(permohonanUkur.getPemberiPengecualian())) {
                    jenisBayarUkur = "BU";
                }
                break;
            case 4:
                rehydrate();
                disPUController = (DisPermohonanUkurController) getContext().getRequest().getSession().getAttribute("disPUController");
                forwardJSP = DisPermohonanPage.getPU_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    public Resolution refreshpage() {
        ctx = (etanahActionBeanContext) getContext();
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanMaklumatAsas", "!simpanbayaranUkur"})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);

        System.out.println("idPermohonan::" + idPermohonan);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Melaka";
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Negeri Sembilan";
        }

//        if (idPermohonan != null) {
//            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//            if (permohonanUkur != null && permohonanUkur.getKodHakmilik() != null) {
//                kodHakmilik = permohonanUkur.getKodHakmilik().getKod();
//            }
//            if (permohonanUkur == null) {
//                permohonanUkur = new PermohonanUkur();
//            }
//        }
//
//
//        if (idPermohonan != null) {
//            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//            if (permohonanUkur != null && permohonanUkur.getSebabUkur() != null) {
//                sebabUkur = permohonanUkur.getSebabUkur();
//
//            }
//            if (permohonanUkur == null) {
//                permohonanUkur = new PermohonanUkur();
//            }
//        }
//Something wrong somewhere
        if (idPermohonan != null) {
            penggunaList = getSenaraiPengguna(permohonan.getCawangan());

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
                    List<String> peranan = new ArrayList<String>();
                    peranan.add("pptkanan");
                    peranan.add("pptd");
                    peranan.add("tptd_ptd");
                    peranan.add("ptd");
                    penggunaList = getSenaraiPengguna(peranan, permohonan.getCawangan());
                }

            }
            permohonanUkur = new PermohonanUkur();
            permohonanUkur = (PermohonanUkur) disLaporanTanahService.findObject(permohonanUkur, new String[]{idPermohonan}, 0);
            if (permohonanUkur != null && permohonanUkur.getSebabUkur() != null) {
                sebabUkur = permohonanUkur.getSebabUkur();
            }
            if (permohonanUkur != null && permohonanUkur.getKodHakmilik() != null) {
                kodHakmilik = permohonanUkur.getKodHakmilik().getKod();
            }
//            if (permohonanUkur == null) {
//                permohonanUkur = new PermohonanUkur();
//            }
        }

        PermohonanTandatanganDokumen tandatanganDokumen = new PermohonanTandatanganDokumen();
        PermohonanTandatanganDokumen tandatanganDokumen2 = new PermohonanTandatanganDokumen();
        tandatanganDokumen = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
        tandatanganDokumen2 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
        LOG.info("----------------tandatanganDokumen-------------------" + tandatanganDokumen);
        LOG.info("----------------tandatanganDokumen2-------------------" + tandatanganDokumen2);
        if (tandatanganDokumen != null) {
            ditundatangan = tandatanganDokumen.getDiTandatangan();
        }
        if (tandatanganDokumen2 != null) {
            sipuTundatangan = tandatanganDokumen2.getDiTandatangan();
        }
    }

//        public Resolution executeCMD() throws IOException, Exception {
//        LOG.info("idPermohonan ........... ");
////        String peng = getContext().getRequest().getParameter("pengguna");
////        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
////        String idAliran = getContext().getRequest().getParameter("idAliran");
//        String  peng = "admin";
//        String  idPermohonan = "0405DEV2011007095";
//        String  idAliran ="agihancharting";
//
//        LOG.info("idPermohonan : " + idPermohonan);
//        LOG.info("idAliran : " + idAliran);
//        LOG.info("peng : " + peng);
////        Pengguna pguna = penggunaDAO.findById(peng);
//        if (peng != null && idPermohonan != null && idAliran != null) {
//            //jup.setIa(strataService.getInfo(peng));
//            InfoAudit ia = new InfoAudit();
//             ia.setTarikhMasuk(new Date());
//             ia.setDimasukOleh(pguna);
//          //  jup.setIa(jup.getInfoPenguna(peng));
//            jup.setIa(ia);
//            jup.setIdAliran(idAliran);
//            jup.setIdPermohonan(idPermohonan);
//            String msg = jup.inboundGIS();
//        }
//        return null;
//    }
    public Resolution simpanMaklumatAsas() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanUkur mohonUkur = new PermohonanUkur();
        mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{idPermohonan}, 0);

        if (mohonUkur != null) {
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "update", pguna));
            permohonanPBMT = permohonanDAO.findById(idPermohonan);
            LOG.info("simpanMaklumatAsas");

            if (permohonanPBMT.getKodUrusan().getKod().equals("PBMT")) {
                LOG.info("Ini adalah pbmt ; ");

                if (mohonUkur.getNoPermohonanUkur().equals("-")) {
                    LOG.info("mohonukur : " + mohonUkur.getNoPermohonanUkur());
                    NumberFormat df = new DecimalFormat("0000");
                    Date dt = new Date();
                    PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
                    System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

                    if (maxPuPermohonanUkur == null) {
                        int val = 1;
                        String noPU = "";
                        noPU = df.format(val) + "/" + (1900 + dt.getYear());

                        System.out.println("-----------Seq-------------" + noPU);
                        mohonUkur.setNoPermohonanUkur(noPU);
                    } else {
                        String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                        System.out.println("-----------maxNoPU-------------" + maxNoPU);
                        if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                            String subNoPU1 = maxNoPU.substring(0, 4);
                            int val = Integer.parseInt(subNoPU1);
                            val = val + 1;
                            String noPU = "";
                            noPU = df.format(val) + "/" + (1900 + dt.getYear());
                            System.out.println("-----------Seq-------------" + noPU);
                            mohonUkur.setNoPermohonanUkur(noPU);
                        } else {
                            int value = 1;
                            String nopu = "";
                            nopu = df.format(value) + "/" + (1900 + dt.getYear());
                            mohonUkur.setNoPermohonanUkur(nopu);
                        }
                    }
                }
            }
        } else {
            mohonUkur = new PermohonanUkur();
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));

            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
                noPU = df.format(val) + "/" + (1900 + dt.getYear());

                System.out.println("-----------Seq-------------" + noPU);
                mohonUkur.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    mohonUkur.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    mohonUkur.setNoPermohonanUkur(nopu);
                }
            }

        }

        mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
        mohonUkur.setNoRujukanPejabatUkur(!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.noRujukanPejabatUkur")) ? (String) getContext().getRequest().getParameter("permohonanUkur.noRujukanPejabatUkur") : new String());
        mohonUkur.setPermohonan(permohonan);
        disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
        rehydrate();
        addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        return new JSP(DisPermohonanPage.getPU_MASAS_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpansuratHakmilik() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanUkur mohonUkur = new PermohonanUkur();
        mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{idPermohonan}, 0);

        if (mohonUkur != null) {
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "update", pguna));
        } else {
            mohonUkur = new PermohonanUkur();
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
        }

        if (!StringUtils.isEmpty(kodHakmilik)) {
            KodHakmilik kodHakmilikTemp = new KodHakmilik();
            kodHakmilikTemp = disLaporanTanahService.getKodHakmilikDAO().findById(kodHakmilik);
            mohonUkur.setKodHakmilik(kodHakmilikTemp);

            int caseHakmilik = kodHakmilik.equals("GRN") ? 1
                    : kodHakmilik.equals("LN") ? 2
                    : kodHakmilik.equals("GM") ? 3
                    : kodHakmilik.equals("PM") ? 4
                    : kodHakmilik.equals("PL") ? 5
                    : kodHakmilik.equals("HS") ? 6
                    : 0;

            switch (caseHakmilik) {
                case 1:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5b"))) {
                        mohonUkur.setPerincianBorang5b((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5b"));
                        mohonUkur.setPerincianBorang5c(new String());
                        mohonUkur.setPerincianBorang5d(new String());
                        mohonUkur.setPerincianBorang5e(new String());
                        mohonUkur.setPerincianPajakanLombong(new String());
                        mohonUkur.setPerincianStrata(new String());
                    }
                    break;
                case 2:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5c"))) {
                        mohonUkur.setPerincianBorang5b(new String());
                        mohonUkur.setPerincianBorang5c((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5c"));
                        mohonUkur.setPerincianBorang5d(new String());
                        mohonUkur.setPerincianBorang5e(new String());
                        mohonUkur.setPerincianPajakanLombong(new String());
                        mohonUkur.setPerincianStrata(new String());
                    }
                    break;
                case 3:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5d"))) {
                        mohonUkur.setPerincianBorang5b(new String());
                        mohonUkur.setPerincianBorang5c(new String());
                        mohonUkur.setPerincianBorang5d((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5d"));
                        mohonUkur.setPerincianBorang5e(new String());
                        mohonUkur.setPerincianPajakanLombong(new String());
                        mohonUkur.setPerincianStrata(new String());
                    }
                    break;
                case 4:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5e"))) {
                        mohonUkur.setPerincianBorang5b(new String());
                        mohonUkur.setPerincianBorang5c(new String());
                        mohonUkur.setPerincianBorang5d(new String());
                        mohonUkur.setPerincianBorang5e((String) getContext().getRequest().getParameter("permohonanUkur.perincianBorang5e"));
                        mohonUkur.setPerincianPajakanLombong(new String());
                        mohonUkur.setPerincianStrata(new String());
                    }
                    break;
                case 5:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianPajakanLombong"))) {
                        mohonUkur.setPerincianBorang5b(new String());
                        mohonUkur.setPerincianBorang5c(new String());
                        mohonUkur.setPerincianBorang5d(new String());
                        mohonUkur.setPerincianBorang5e(new String());
                        mohonUkur.setPerincianPajakanLombong((String) getContext().getRequest().getParameter("permohonanUkur.perincianPajakanLombong"));
                        mohonUkur.setPerincianStrata(new String());
                    }
                    break;
                case 6:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.perincianStrata"))) {
                        mohonUkur.setPerincianBorang5b(new String());
                        mohonUkur.setPerincianBorang5c(new String());
                        mohonUkur.setPerincianBorang5d(new String());
                        mohonUkur.setPerincianBorang5e(new String());
                        mohonUkur.setPerincianPajakanLombong(new String());
                        mohonUkur.setPerincianStrata((String) getContext().getRequest().getParameter("permohonanUkur.perincianStrata"));
                    }
                    break;
            }
        } else {
            mohonUkur.setKodHakmilik(null);
            mohonUkur.setPerincianBorang5b(new String());
            mohonUkur.setPerincianBorang5c(new String());
            mohonUkur.setPerincianBorang5d(new String());
            mohonUkur.setPerincianBorang5e(new String());
            mohonUkur.setPerincianPajakanLombong(new String());
            mohonUkur.setPerincianStrata(new String());
        }
        if (!StringUtils.isEmpty(getContext().getRequest().getParameter("permohonanUkur.statusSuratanHakmilik"))) {
            String statusSuratHakmilik = new String();
            statusSuratHakmilik = (String) getContext().getRequest().getParameter("permohonanUkur.statusSuratanHakmilik");
            mohonUkur.setStatusSuratanHakmilik(statusSuratHakmilik.charAt(0));
        } else {
            mohonUkur.setStatusSuratanHakmilik('\0');
        }
        if (!StringUtils.isEmpty(getContext().getRequest().getParameter("permohonanUkur.statusSuratanHakmilikSementara"))) {
            String statusSuratHakmilik = new String();
            statusSuratHakmilik = (String) getContext().getRequest().getParameter("permohonanUkur.statusSuratanHakmilikSementara");
            mohonUkur.setStatusSuratanHakmilikSementara(statusSuratHakmilik.charAt(0));
        } else {
            mohonUkur.setStatusSuratanHakmilikSementara('\0');
        }
        mohonUkur.setPermohonan(permohonan);
        mohonUkur.setSebabUkur(sebabUkur);
        if (StringUtils.isNotBlank(sebabUkur)) {
            if (sebabUkur.equalsIgnoreCase("SBT") || sebabUkur.equalsIgnoreCase("PTK") || sebabUkur.equalsIgnoreCase("PL") || sebabUkur.equalsIgnoreCase("PS") || sebabUkur.equalsIgnoreCase("PBB") || sebabUkur.equalsIgnoreCase("PBT") || sebabUkur.equalsIgnoreCase("GTS") || sebabUkur.equalsIgnoreCase("BTS")) {
                mohonUkur.setLot(new String());
                mohonUkur.setBahagian(new String());
            }
            if (sebabUkur.equalsIgnoreCase("PT")) {
                mohonUkur.setLot((String) getContext().getRequest().getParameter("permohonanUkur.lotPT"));
                mohonUkur.setBahagian((String) getContext().getRequest().getParameter("permohonanUkur.bahagianPTTerusPecah"));
            }
            if (sebabUkur.equalsIgnoreCase("PSL")) {
                mohonUkur.setLot((String) getContext().getRequest().getParameter("permohonanUkur.lotPSL"));
                mohonUkur.setBahagian(new String());
            }
            if (sebabUkur.equalsIgnoreCase("PLL")) {
                mohonUkur.setLot((String) getContext().getRequest().getParameter("permohonanUkur.lotPLL"));
                mohonUkur.setBahagian((String) getContext().getRequest().getParameter("permohonanUkur.bahagianPLLBerimilik"));
            }
            if (sebabUkur.equalsIgnoreCase("PSLL")) {
                mohonUkur.setLot((String) getContext().getRequest().getParameter("permohonanUkur.lotPSLL"));
                mohonUkur.setBahagian(new String());
            }
            if (sebabUkur.equalsIgnoreCase("US")) {
                mohonUkur.setLot((String) getContext().getRequest().getParameter("permohonanUkur.lotUS"));
                mohonUkur.setBahagian(new String());
            }
        }

        disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP(DisPermohonanPage.getPU_SHAKMILIK_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanbayaranUkur() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanUkur mohonUkur = new PermohonanUkur();
        mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{idPermohonan}, 0);

        if (mohonUkur != null) {
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "update", pguna));
        } else {
            mohonUkur = new PermohonanUkur();
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
        }

        if (!StringUtils.isEmpty(jenisBayarUkur)) {

            int caseHakmilik = jenisBayarUkur.equals("DP") ? 1
                    : jenisBayarUkur.equals("DS") ? 2
                    : jenisBayarUkur.equals("BU") ? 3
                    : jenisBayarUkur.equals("JU") ? 4
                    : 0;

            switch (caseHakmilik) {
                case 1:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.pemberiPengecualian"))) {
                        mohonUkur.setPemberiPengecualian((String) getContext().getRequest().getParameter("permohonanUkur.pemberiPengecualian"));
                        mohonUkur.setPerengganKTN((String) getContext().getRequest().getParameter("permohonanUkur.perengganKTN"));
                        mohonUkur.setRujukanKTN((String) getContext().getRequest().getParameter("permohonanUkur.rujukanKTN"));
                        mohonUkur.setJumlahPengecualian(null);
                        mohonUkur.setJumlahBayaranUkur(null);
                        mohonUkur.setNoResit(new String());
                        mohonUkur.setTarikhResit(null);
                        mohonUkur.setDiUkurOleh(new String());
                        mohonUkur.setTujuan((String) getContext().getRequest().getParameter("jenisBayarUkur"));
                    }
                    break;
                case 2:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.pemberiPengecualian2"))) {
                        mohonUkur.setPemberiPengecualian((String) getContext().getRequest().getParameter("permohonanUkur.pemberiPengecualian2"));
                        mohonUkur.setPerengganKTN((String) getContext().getRequest().getParameter("permohonanUkur.perengganKTN2"));
                        mohonUkur.setRujukanKTN((String) getContext().getRequest().getParameter("permohonanUkur.rujukanKTN2"));
                        mohonUkur.setJumlahPengecualian(new BigDecimal((String) getContext().getRequest().getParameter("permohonanUkur.jumlahPengecualian")));
                        mohonUkur.setJumlahBayaranUkur(null);
                        mohonUkur.setNoResit(new String());
                        mohonUkur.setTarikhResit(null);
                        mohonUkur.setDiUkurOleh(new String());
                        mohonUkur.setTujuan((String) getContext().getRequest().getParameter("jenisBayarUkur"));
                    }
                    break;
                case 3:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.noResit"))) {
                        mohonUkur.setPemberiPengecualian(new String());
                        mohonUkur.setPerengganKTN(new String());
                        mohonUkur.setRujukanKTN(new String());
                        mohonUkur.setJumlahPengecualian(null);
                        mohonUkur.setJumlahBayaranUkur(new BigDecimal((String) getContext().getRequest().getParameter("permohonanUkur.jumlahBayaranUkur")));
                        mohonUkur.setNoResit((String) getContext().getRequest().getParameter("permohonanUkur.noResit"));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new java.util.Date((String) getContext().getRequest().getParameter("permohonanUkur.tarikhResit"));
                        mohonUkur.setTarikhResit(date);
                        mohonUkur.setDiUkurOleh(new String());
                        mohonUkur.setTujuan((String) getContext().getRequest().getParameter("jenisBayarUkur"));
                    }
                    break;
                case 4:
                    if (!StringUtils.isEmpty((String) getContext().getRequest().getParameter("permohonanUkur.diUkurOleh"))) {
                        mohonUkur.setPemberiPengecualian(new String());
                        mohonUkur.setPerengganKTN(new String());
                        mohonUkur.setRujukanKTN(new String());
                        mohonUkur.setJumlahPengecualian(null);
                        mohonUkur.setJumlahBayaranUkur(null);
                        mohonUkur.setNoResit(new String());
                        mohonUkur.setTarikhResit(null);
                        mohonUkur.setDiUkurOleh((String) getContext().getRequest().getParameter("permohonanUkur.diUkurOleh"));
                        mohonUkur.setTujuan((String) getContext().getRequest().getParameter("jenisBayarUkur"));
                    }
                    break;
            }
        } else {
            mohonUkur.setPemberiPengecualian(new String());
            mohonUkur.setPerengganKTN(new String());
            mohonUkur.setRujukanKTN(new String());
            mohonUkur.setJumlahPengecualian(null);
            mohonUkur.setJumlahBayaranUkur(null);
            mohonUkur.setNoResit(new String());
            mohonUkur.setTarikhResit(null);
            mohonUkur.setDiUkurOleh(new String());
        }
        disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP(DisPermohonanPage.getPU_BUKUR_PAGE()).addParameter("tab", "true");

    }

    public Resolution simpan() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanUkur mohonUkur = new PermohonanUkur();
        mohonUkur = (PermohonanUkur) disLaporanTanahService.findObject(mohonUkur, new String[]{idPermohonan}, 0);

        if (mohonUkur != null) {
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "update", pguna));
            permohonanPBMT = permohonanDAO.findById(idPermohonan);
            LOG.info("simpanMaklumatAsas");

            if (permohonanPBMT.getKodUrusan().getKod().equals("PBMT")) {
                LOG.info("Ini adalah pbmt ; ");

                if (mohonUkur.getNoPermohonanUkur().equals("-")) {
                    LOG.info("mohonukur : " + mohonUkur.getNoPermohonanUkur());
                    NumberFormat df = new DecimalFormat("0000");
                    Date dt = new Date();
                    PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
                    System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

                    if (maxPuPermohonanUkur == null) {
                        int val = 1;
                        String noPU = "";
                        noPU = df.format(val) + "/" + (1900 + dt.getYear());

                        System.out.println("-----------Seq-------------" + noPU);
                        mohonUkur.setNoPermohonanUkur(noPU);
                    } else {
                        String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                        System.out.println("-----------maxNoPU-------------" + maxNoPU);
                        if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                            String subNoPU1 = maxNoPU.substring(0, 4);
                            int val = Integer.parseInt(subNoPU1);
                            val = val + 1;
                            String noPU = "";
                            noPU = df.format(val) + "/" + (1900 + dt.getYear());
                            System.out.println("-----------Seq-------------" + noPU);
                            mohonUkur.setNoPermohonanUkur(noPU);
                        } else {
                            int value = 1;
                            String nopu = "";
                            nopu = df.format(value) + "/" + (1900 + dt.getYear());
                            mohonUkur.setNoPermohonanUkur(nopu);
                        }
                    }
                }
            }
        } else {
            mohonUkur = new PermohonanUkur();
            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));

            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
                noPU = df.format(val) + "/" + (1900 + dt.getYear());

                System.out.println("-----------Seq-------------" + noPU);
                mohonUkur.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    mohonUkur.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    mohonUkur.setNoPermohonanUkur(nopu);
                }
            }

        }
//        if (mohonUkur != null) {
//            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "update", pguna));
//        } else {
//            mohonUkur = new PermohonanUkur();
//            mohonUkur.setInfoAudit(disLaporanTanahService.findAudit(mohonUkur, "new", pguna));
//
//            NumberFormat df = new DecimalFormat("0000");
//            Date dt = new Date();
//            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
//            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);
//
//            if (maxPuPermohonanUkur == null) {
//                int val = 1;
//                String noPU = "";
//                noPU = df.format(val) + "/" + (1900 + dt.getYear());
//
//                System.out.println("-----------Seq-------------" + noPU);
//                mohonUkur.setNoPermohonanUkur(noPU);
//            } else {
//                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
//                System.out.println("-----------maxNoPU-------------" + maxNoPU);
//                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
//                    String subNoPU1 = maxNoPU.substring(0, 4);
//                    int val = Integer.parseInt(subNoPU1);
//                    val = val + 1;
//                    String noPU = "";
//                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
//                    System.out.println("-----------Seq-------------" + noPU);
//                    mohonUkur.setNoPermohonanUkur(noPU);
//                } else {
//                    int value = 1;
//                    String nopu = "";
//                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
//                    mohonUkur.setNoPermohonanUkur(nopu);
//                }
//            }
//
//        }

        mohonUkur.setNoRujukanPejabatTanah(idPermohonan);
        mohonUkur.setPermohonan(permohonan);
        disLaporanTanahService.getPelupusanService().simpanPermohonanUkur(mohonUkur);
        rehydrate();
        addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        if (checkQT) {
            return viewOnlyPUPPTForQT();
        } else {
            return viewOnlyPUPTForFT();
        }
    }

//    public Resolution janaNomborPU(){
//         NumberFormat df = new DecimalFormat("0000");
//         Date dt= new Date();
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if(idPermohonan!=null){
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//        }
//
//        String kodCaw="";
//                if(permohonan.getCawangan().getKod().equals("01")){
//                    kodCaw ="MT";
//                }else if(permohonan.getCawangan().getKod().equals("02")){
//                    kodCaw ="JS";
//                }else if(permohonan.getCawangan().getKod().equals("03")){
//                    kodCaw ="AG";
//                }
//
//
//        if((permohonanUkur!=null)&& (permohonanUkur.getNoPermohonanUkur()==null) ){
//                // get maximum Permohonan Ukur
//                PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur();
//                if(maxPuPermohonanUkur == null){
//                       int val=1;
//                      String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                      System.out.println("-----------Seq-------------"+noPU);
//                    permohonanUkur.setNoPermohonanUkur(noPU);
//                }else{
//                    String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
//                       String subNoPU1 = maxNoPU.substring(7);
//                       String subNoPU2 = maxNoPU.substring(subNoPU1.lastIndexOf("0"));
//                       int val = Integer.parseInt(subNoPU2);
//                       val = val+1;
//                       String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                       System.out.println("-----------Seq-------------"+noPU);
//                       permohonanUkur.setNoPermohonanUkur(noPU);
//                }
//        }
//
//
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//    }
//    public String currentStageId(String taskId) {
//        BPelService service = new BPelService();
//        String stageId = null;
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pguna);
//            stageId = t.getSystemAttributes().getStage();
//        }
//        return stageId;
//    }
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        LOG.info(" ---stageId : " + stageId);
//      else {
//            stageId = "g_penyediaan_pu";
//        }
        return stageId;
    }

    //-------------------------------Transfer File To JUPEM-------------------------------------//
    public Resolution transferFile() {
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        LOG.info(" ---transferFile----idPermohonan : " + idPermohonan);
////        List<Dokumen> listDoc1 = jupemService.findDokumenlist("0505DEV2011150187", "g_penyediaan_pu_pt");
//        List<Dokumen> listDoc1 = jupemService.findDokumenlist(idPermohonan, "g_penyediaan_pu_pt");
//        LOG.info(" -------Nama fizikal : " + listDoc1.size());
//        for (Dokumen doc : listDoc1) {
//         LOG.info(" -------Nama fizikal : " + doc.getNamaFizikal());
//         LOG.info(" ------Id Dokumen : " + doc.getIdDokumen());
////         LOG.info(" ------Id Dokumen--------- : " +  doc.getNamaFizikal().lastIndexOf("/"));
//        }

        String msg = "";
        String error = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);

//        edit1 = Boolean.TRUE;
//        simpan = Boolean.TRUE;
//        dms = Boolean.FALSE;
//        hntrpu = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        view = Boolean.FALSE;
//        edit1 = Boolean.FALSE;
//        simpan = Boolean.FALSE;
//        dms = Boolean.TRUE;
//        hntrpu = Boolean.FALSE;
//        edit2 = Boolean.FALSE;
//        view = Boolean.TRUE;
        // compare files
//        List<Dokumen> dokumenList=new ArrayList<Dokumen>();
//        dokumenList = jupemService.findDokumenList(idPermohonan);
//        LOG.info("dokumenList :"+dokumenList.size());
//            for(int i=0;i<dokumenList.size();i++){
//                LOG.info("Kod Dokumen :"+dokumenList.get(i).getKodDokumen().getKod());
//            }
//        List<IntegrasiDokumen> integDokumenList = new ArrayList<IntegrasiDokumen>();
//        LOG.info(this.permohonan.getKodUrusan().getKod());
//        String idAliran = "g_hantar_pu";
//        integDokumenList = jupemService.findIntegrasiDokumenList(permohonan.getKodUrusan().getKod(),idAliran);
//        LOG.info("integDokumenList:"+integDokumenList.size());
//            for(int i=0;i<integDokumenList.size();i++){
//                LOG.info("Kod dokumen :"+integDokumenList.get(i).getKodDokumen().getKod());
//            }
//
//        if(integDokumenList.size() != dokumenList.size()){
//            addSimpleError("Dokumen tiada dalam senarai, sila jana dokumen terlebih dahulu..");
//            return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//        }
//        obi = new OutBoundIntegration(permohonan, peng, taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();

        addSimpleMessage("Fail telah berjaya dihantar");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        //return new JSP("pelupusan/common/permohonan_ukur_prizV2.jsp").addParameter("tab", "true");
        if (checkQT) {
            return viewOnlyPUPPTForQT();
        } else {
            return viewOnlyPUPTForFT();
        }
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PU");
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. " + ditundatangan);
        if (ditundatangan != null) {
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("PU"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(ditundatangan);
            pembangunanService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new RedirectResolution(PUV2ActionBean.class, "showForm");
    }

    public Resolution simpanSIPU() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = pembangunanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SIPU");
        sipuTundatangan = getContext().getRequest().getParameter("sipuTundatangan");
        LOG.info("testing.............. " + sipuTundatangan);
        if (sipuTundatangan != null) {
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SIPU"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(sipuTundatangan);
            pembangunanService.simpanPermohonanTandatanganDokumen(tandatanganDokumen1);
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new JSP("pembangunan/melaka/sipu_tandatangan.jsp").addParameter("tab", "true");
    }

    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            // Melaka
//            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('71','74','75','77') and u.kodCawangan.kod = :kod order by u.nama";

            // NS
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('PTD','PPTD','TPTD','PPTK') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPengguna(List<String> peranan, KodCawangan kod) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kumpBPEL in(:peranan) and p.noPengenalan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("peranan", peranan);
        return q.list();
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<NoPt> getNoPTList() {
        return noPTList;
    }

    public void setNoPTList(List<NoPt> noPTList) {
        this.noPTList = noPTList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getSipuTundatangan() {
        return sipuTundatangan;
    }

    public void setSipuTundatangan(String sipuTundatangan) {
        this.sipuTundatangan = sipuTundatangan;
    }

    public Boolean getHntrpu() {
        return hntrpu;
    }

    public void setHntrpu(Boolean hntrpu) {
        this.hntrpu = hntrpu;
    }

    public DisPermohonanUkurController getDisPUController() {
        return disPUController;
    }

    public void setDisPUController(DisPermohonanUkurController disPUController) {
        this.disPUController = disPUController;
    }

    public String getJenisBayarUkur() {
        return jenisBayarUkur;
    }

    public void setJenisBayarUkur(String jenisBayarUkur) {
        this.jenisBayarUkur = jenisBayarUkur;
    }

    public String getSebabUkur() {
        return sebabUkur;
    }

    public void setSebabUkur(String sebabUkur) {
        this.sebabUkur = sebabUkur;
    }

    public Permohonan getPermohonanPBMT() {
        return permohonanPBMT;
    }

    public void setPermohonanPBMT(Permohonan permohonanPBMT) {
        this.permohonanPBMT = permohonanPBMT;
    }

    public boolean isCheckQT() {
        return checkQT;
    }

    public void setCheckQT(boolean checkQT) {
        this.checkQT = checkQT;
    }
}
