/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodLotDAO;
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
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTuntutanKos;
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

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/undian_kelompok")
public class UndianPermohonanBerkelompokActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodLotDAO kodLotDAO ;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Pengguna peng;
    private HakmilikPermohonan hakmilikPermohonan;
    private DisRekodKeputusanController disRekodKeputusanController;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private static final Logger LOG = Logger.getLogger(RekodKeputusanJKTDV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    etanahActionBeanContext ctx;
    private List<HakmilikPermohonan> senaraiHakmilikLulus;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanKelompok> senaraiKelompok;
    private boolean kelompok;
    private int sizeLulus;
    private String noLot;

    @DefaultHandler
    public Resolution viewOnlyRekodKeputusanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getCBT_UNDI_MAIN_PAGE()).addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanKeputusan"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        /**
         * Senarai Hakmilik
         */
        if (idPermohonan != null) {

            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            //senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
            if (senaraiKelompok.size() > 0) {
                kelompok = true;
            } else {
                //addSimpleError("Skrin ini hanya untuk urusan permohonan berkelompok sahaja");
                kelompok = false;
            }
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
                senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
                if (kelompok) {
                    if (senaraiKelompok.size() > 0) {
                        for (PermohonanKelompok pk : senaraiKelompok) {
                            if(pk.getJenisKelopok().equals("K")){
                                List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                                senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                                if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                        senaraiHakmilikLulus.add(hp);
                                    }
                                }
                            }                            
                        }
                        sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                    }
                }
            }
        }

    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
        if (senaraiKelompok.size() > 0) {
            kelompok = true;
        } else {
            kelompok = false;
        }
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
            senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
            if (kelompok) {
                if (senaraiKelompok.size() > 0) {
                    for (PermohonanKelompok pk : senaraiKelompok) {
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                        senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                        if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                senaraiHakmilikLulus.add(hp);
                            }
                        }
                    }
                    sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                }
            }
        }

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution simpanPlot() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String idHakmilik = ctx.getRequest().getParameter("idMH");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idHakmilik));
        if (hakmilikPermohonan != null) {
            hakmilikPermohonan.setLot(kodLotDAO.findById("2")); //Hardcode plot
            if (StringUtils.isNotBlank(noLot)) {
                hakmilikPermohonan.setNoLot(noLot);
            }
            PermohonanPlotPelan mohonPlotPelan = new PermohonanPlotPelan();
            String[] data = {String.valueOf(hakmilikPermohonan.getId())};
            mohonPlotPelan = (PermohonanPlotPelan) disLaporanTanahService.findObject(mohonPlotPelan, data, 1);
            if(mohonPlotPelan !=null){
                mohonPlotPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonPlotPelan, "update", peng));
            }else{
                mohonPlotPelan = new PermohonanPlotPelan();
                mohonPlotPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonPlotPelan, "new", peng));            
            }
                mohonPlotPelan.setHakmilikPermohonan(hakmilikPermohonan);
                mohonPlotPelan.setPermohonan(permohonan);
                mohonPlotPelan.setNoPlot(noLot);
                mohonPlotPelan.setCawangan(peng.getKodCawangan());
                mohonPlotPelan.setKategoriTanah(hakmilikPermohonan.getKategoriTanahBaru());
                mohonPlotPelan.setKegunaanTanah(hakmilikPermohonan.getKodKegunaanTanah());
                mohonPlotPelan.setLuas(hakmilikPermohonan.getLuasDiluluskan());
                mohonPlotPelan.setKodUnitLuas(hakmilikPermohonan.getLuasLulusUom());
                mohonPlotPelan.setPemilikan(hakmilikPermohonan.getKodMilik());
                disLaporanTanahService.getPelupusanService().saveOrUpdate(mohonPlotPelan);            
                disLaporanTanahService.getPelupusanService().saveOrUpdate(hakmilikPermohonan);
        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        return new JSP(DisPermohonanPage.getCBT_UNDI_EDIT_PAGE()).addParameter("tab", "true");
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

    public String refreshData(String type) {
        String forwardJSP = new String();
        String kodDok = new String();
        int typeNum = type.equals("editUndi") ? 1
                : type.equals("main") ? 2
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getCBT_UNDI_EDIT_PAGE();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                break;
            case 2:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getCBT_UNDI_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    public Resolution editDetails() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String type = ctx.getRequest().getParameter("type");
        String idHakmilik = ctx.getRequest().getParameter("idMH");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
        if (senaraiKelompok.size() > 0) {
            kelompok = true ;
        } else {
            kelompok = false;
        }
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idHakmilik));
        if(hakmilikPermohonan != null){
            if(StringUtils.isNotBlank(hakmilikPermohonan.getNoLot())){
                noLot = hakmilikPermohonan.getNoLot() ;
            }
        }

        return new JSP(DisPermohonanPage.getCBT_UNDI_EDIT_PAGE()).addParameter("tab", "true");


    }

    public Resolution refreshpage() {
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public DisRekodKeputusanController getDisRekodKeputusanController() {
        return disRekodKeputusanController;
    }

    public void setDisRekodKeputusanController(DisRekodKeputusanController disRekodKeputusanController) {
        this.disRekodKeputusanController = disRekodKeputusanController;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikLulus() {
        return senaraiHakmilikLulus;
    }

    public void setSenaraiHakmilikLulus(List<HakmilikPermohonan> senaraiHakmilikLulus) {
        this.senaraiHakmilikLulus = senaraiHakmilikLulus;
    }

    public boolean isKelompok() {
        return kelompok;
    }

    public void setKelompok(boolean kelompok) {
        this.kelompok = kelompok;
    }

    public int getSizeLulus() {
        return sizeLulus;
    }

    public void setSizeLulus(int sizeLulus) {
        this.sizeLulus = sizeLulus;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
}
