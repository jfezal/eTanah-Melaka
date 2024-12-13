/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisRekodKeputusanController;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Afham
 */
@UrlBinding("/consent/rekod_keputusan")
public class RekodKeputusanActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String idPermohonan;
    private String stageId;
    private String idHakmilik;
    private String kodNegeri;
    private String keputusan;
    private String kod;
    private String kodU;
    private String kodHakmilik;
    private String index;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonanRayuan;
    etanahActionBeanContext ctx;
    private DisRekodKeputusanController disRekodKeputusanController;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List hakmilikPermohonanList;
    private static final Logger LOG = Logger.getLogger(RekodKeputusanActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng;

    @DefaultHandler
    public Resolution viewOnlyRekodKeputusanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP("consent/rekod_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnlyRekodKeputusan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusan();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP("consent/rekod_keputusan.jsp").addParameter("tab", "true");
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

        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : null;
            permohonanKertas = new PermohonanKertas();

            if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                fasaPermohonanRayuan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage9");
                if (fasaPermohonanRayuan != null) {
                    permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT RAYUAN");

                    if (permohonanKertas == null) {
                        permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT MMKN");
                    }
                } else {
                    permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT MMKN");
                }

            } else {
                permohonanKertas = consentService.findMohonKertas(idPermohonan);
            }

            if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {

                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(idPermohonan);

                if (permohonanRujukanLuar != null && permohonanRujukanLuar.getNoSidang() != null) {
                    permohonanKertas.setNomborRujukan(permohonanRujukanLuar.getNoSidang());
                } else {
                    permohonanKertas.setNomborRujukan(null);
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
//        hakmilikPermohonan = new HakmilikPermohonan();

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        String forwardJSP = new String();
//        String kodDok = new String();
        int typeNum = type.equals("mMesyuarat") ? 1
                : type.equals("sKelulusan") ? 2
                : type.equals("main") ? 3
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = "consent/rekod_keputusan_popup.jsp";
//                kodDok = kertasDok(permohonan.getKodUrusan().getKod());
                permohonanKertas = new PermohonanKertas();

                if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                    fasaPermohonanRayuan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage9");
                    if (fasaPermohonanRayuan != null) {
                        permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT RAYUAN");
                    } else {
                        permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT MMKN");
                    }
                } else {
                    permohonanKertas = consentService.findMohonKertas(idPermohonan);
                }

                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(permohonan.getIdPermohonan());
                if (permohonanRujukanLuar != null && permohonanRujukanLuar.getNoSidang() != null) {
                    permohonanKertas.setNomborRujukan(permohonanRujukanLuar.getNoSidang());
                } else {
                    permohonanKertas.setNomborRujukan(null);
                }
//                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
                break;
            case 2:
//                forwardJSP = this.getSyaratLulus();
                LOG.debug("Page--->" + forwardJSP);
                break;
            case 3:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = "consent/rekod_keputusan.jsp";
                break;
        }
        return forwardJSP;
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
        }
        return new JSP("consent/rekod_keputusan.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKeputusan() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        keputusan = ctx.getRequest().getParameter("keputusan");

        if (keputusan != null && permohonanKertas != null) {

            if (keputusan != null && permohonanKertas.getNomborRujukan() != null && permohonanKertas.getTarikhSidang() != null && permohonanKertas.getTarikhSahKeputusan() != null) {

                permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
                Permohonan permohonanTemp = new Permohonan();
                permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);

                if (permohonanTemp != null && keputusan != null) {
                    permohonanTemp.setKeputusan(kodKeputusanDAO.findById(keputusan));
                    permohonanTemp.setTarikhKeputusan(new java.util.Date());
                    permohonanTemp.setKeputusanOleh(peng);
                    permohonanTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanTemp, "update", peng));
                    disLaporanTanahService.getPlpservice().simpanPermohonan(permohonanTemp);
                }

                PermohonanKertas permohonanKertasTemp;

                if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                    fasaPermohonan = consentService.findMohonFasaByStage(idPermohonan, "Stage7");
                    fasaPermohonanRayuan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage9");
                    if (fasaPermohonanRayuan != null) {
                        permohonanKertasTemp = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT RAYUAN");
                    } else {
                        permohonanKertasTemp = consentService.findMohonKertasByTajuk(idPermohonan, "RISALAT MMKN");
                    }

                } else {
                    fasaPermohonan = consentService.findMohonFasaByStage(idPermohonan, "Stage6");
                    permohonanKertasTemp = consentService.findMohonKertas(idPermohonan);
                }

                if (permohonanKertasTemp != null) {
                    if (!permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                        permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
                    }

                    permohonanKertasTemp.setCawangan(permohonan.getCawangan());
                    permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
                    permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
                    permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
                    permohonanKertasTemp.setPermohonan(permohonan);
                    disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);
                }

                if (fasaPermohonan != null) {
                    InfoAudit infoAudit = fasaPermohonan.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                    fasaPermohonan.setTarikhKeputusan(new java.util.Date());
                    consentService.simpanFasaPermohonan(fasaPermohonan);
                }

                //KES RAYUAN TANAH LADANG MELAKA

                if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                    PermohonanRujukanLuar rujukanLuarTemp = consentService.findMohonRujukanNotTangguh2(idPermohonan);
                    InfoAudit infoAudit = new InfoAudit();

                    if (rujukanLuarTemp != null) {
                        infoAudit = rujukanLuarTemp.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    } else {
                        rujukanLuarTemp = new PermohonanRujukanLuar();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                    }

                    rujukanLuarTemp.setNoSidang(permohonanKertas.getNomborRujukan());
                    rujukanLuarTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
                    rujukanLuarTemp.setTarikhLulus(permohonanKertas.getTarikhSahKeputusan());
                    rujukanLuarTemp.setCawangan(permohonan.getCawangan());
                    KodRujukan kodRujukan = new KodRujukan();
                    kodRujukan.setKod("FL");
                    rujukanLuarTemp.setKodRujukan(kodRujukan);
                    rujukanLuarTemp.setPermohonan(permohonan);
                    rujukanLuarTemp.setInfoAudit(infoAudit);

                    consentService.simpanRujukanLuar(rujukanLuarTemp);
                }

                addSimpleMessage("Maklumat Telah Berjaya disimpan.");
            } else {
                addSimpleError("Sila masukkan maklumat dengan lengkap.");
            }

        } else {
            addSimpleError("Sila masukkan maklumat dengan lengkap.");
        }

        return new JSP("consent/rekod_keputusan_popup.jsp").addParameter("tab", "true");
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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public FasaPermohonan getFasaPermohonanRayuan() {
        return fasaPermohonanRayuan;
    }

    public void setFasaPermohonanRayuan(FasaPermohonan fasaPermohonanRayuan) {
        this.fasaPermohonanRayuan = fasaPermohonanRayuan;
    }
}
