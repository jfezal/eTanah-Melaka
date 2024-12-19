/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBankDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanPihakPendepositDAO;
import etanah.dao.PihakDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
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
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporTanahKawasan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahController;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPendepositController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/pendepositV2")
public class PendepositV2ActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodBankDAO kodBankDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    PihakDAO pihakDAO ;
    @Inject
    PermohonanPihakPendepositDAO permohonanPihakPendepositDAO ;
    @Inject
    KodNegeriDAO kodNegeriDAO ;
    private String idPermohonan;
    private String stageId;
    private String kodNegeri;    
    private String idHakmilik;
    private String idPihakPendeposit ;
    private DisPendepositController disPendepositController;
    private static final Logger LOG = Logger.getLogger(PendepositV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng; //Add for charting
    etanahActionBeanContext ctx;    
    private Permohonan permohonan;    
    private HakmilikPermohonan hakmilikPermohonan;    
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;    
    private List hakmilikPermohonanList;
    private PermohonanPihakPendeposit permohonanPihakPendeposit ;
    private List <PermohonanPihakPendeposit> permohonanPihakPendepositList ;
    private Pihak pihak ;
    
    @DefaultHandler
    public Resolution viewOnlyPendepositPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPendepositController = new DisPendepositController();
        disPendepositController = disPendepositController.viewOnlyPendepositPT();
        httpSession.setAttribute("disPendepositController", disPendepositController);
        return new JSP(DisPermohonanPage.getDEPO_MAIN_PAGE()).addParameter("tab", "true");
    }
    
    public Resolution viewOnlyPendeposit() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPendepositController = new DisPendepositController();
        disPendepositController = disPendepositController.viewOnlyPendeposit();
        httpSession.setAttribute("disPendepositController", disPendepositController);
        return new JSP(DisPermohonanPage.getDEPO_MAIN_PAGE()).addParameter("tab", "true");
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

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        idPihakPendeposit = getContext().getRequest().getParameter("idPihakPendeposit");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();
        permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
        permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
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
                    } else if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                        HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                        hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSblm, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 1);
                        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);

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
        }
        if(StringUtils.isNotBlank(idPihakPendeposit)){
            permohonanPihakPendeposit = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(idPihakPendeposit)) ;
        }
        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    /*
     * Author : Shazwan
     * Date : 9th February 2012
     * Purpose : To Refresh data based on type given;
     * Note : the type is set based on frame in laporanTanahV2, please consult before making changes since this method is sharing
     */
    public String refreshData(String type) {
        String forwardJSP = new String();
        int typeNum = type.equals("pendeposit") ? 1
                      : type.equals("main") ? 2
                      : type.equals("papar") ? 3
                      : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getDEPO_EDIT_PAGE();
               

                break;
            case 2:
                rehydrate();
                disPendepositController = (DisPendepositController) getContext().getRequest().getSession().getAttribute("disPendepositController");
                forwardJSP = DisPermohonanPage.getDEPO_MAIN_PAGE();
                break;
            case 3:
                forwardJSP = DisPermohonanPage.getDEPO_VIEW_PAGE();
               

                break;     
        }
        return forwardJSP;
    }

    public Resolution reload() {
        return showFormDisplay();
    }
    
    public Resolution refreshpage() {
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution showFormDisplay() {
//        statusPage = new String();
//        display = Boolean.TRUE;
        //Purpose : TO CATER LOADING MULTIPLE HAKMILIK USING DROPDOWN LIST
        rehydrate();
        HttpSession httpSession = getContext().getRequest().getSession();
        disPendepositController = new DisPendepositController();
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
                : 0;


        switch (typeNum) {
            case 1: // Urusan = PPBB
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 2: // Urusan = PBPTD
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 3: // Urusan = PBPTG
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 4: // Urusan = LMCRG
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 5: // Urusan = PJLB
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 6: // Urusan = LPSP
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 7: // Urusan = PLPS
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 8: // Urusan = PPRU
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 9: // Urusan = PPTR
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 10: // Urusan = PTGSA
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 11: // Urusan = PRMP
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 12: // Urusan = PBMT
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 13: // Urusan = MCMCL
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 14: // Urusan = MMMCL
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 15: // Urusan = PRIZ
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                } 
                break;
            case 16: // Urusan = PHLA
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 17: // Urusan = PBRZ
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 18: // Urusan = PBHL
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            case 19: // Urusan = BMBT
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
            default:
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    disPendepositController = disPendepositController.viewOnlyPendepositPT();
                    httpSession.setAttribute("disPendepositController", disPendepositController);
                }
                break;
        }
        return new JSP(DisPermohonanPage.getDEPO_MAIN_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanBersempadanan", "!showFormPopUp", "!simpanKandunganPendeposit", "!showFormKemaskiniBank", "!showFormKemaskiniPendeposit", "!kemaskiniKandunganPendeposit", "!deleteRow", "!kemaskiniKandunganBank", "!simpanImejLaporan"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");

        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); //Add for charting
        /*
         * Perihal Tanah
         */
        if (idPermohonan != null) {

            int syx = 0;
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
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
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
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
            } else {
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
                 }
            }
            //PermohonanPihakPendepositList 
            permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
            permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
            
//            String[] tname = {"permohonan"};
//            Object[] model = {permohonan};
//            permohonanPihakPendepositList = permohonanPihakPendepositDAO.findByEqualCriterias(tname, model, null);
        }
            
    }

    public Resolution simpanKandunganPendeposit() throws ParseException {
        
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanPihakPendeposit permohonanPihakPendepositTemp = new PermohonanPihakPendeposit();
        Pihak pihakTemp = new Pihak();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        pihakTemp = pelupusanService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        if (pihakTemp != null) {
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setInfoAudit(disLaporanTanahService.findAudit(pihakTemp, "update", peng));
            pelupusanService.saveOrUpdate(pihakTemp);
        } else {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setInfoAudit(disLaporanTanahService.findAudit(pihakTemp, "new", peng));
            pelupusanService.saveOnly(pihakTemp);
        }
        InfoAudit audit = new InfoAudit() ;
        audit.setDimasukOleh(pguna);
        audit.setDiKemaskiniOleh(pguna);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
//        PermohonanPihakPendeposit test = new PermohonanPihakPendeposit() ;
////        if(pelupusanService.getMaxOfPermohonanPihakPendeposit() != null){
////            test = pelupusanService.getMaxOfPermohonanPihakPendeposit() ;
////            int a = Integer.parseInt(test.getIdPermohonanPihakPendeposit()) + 1 ;
////            permohonanPihakPendepositTemp.setIdPermohonanPihakPendeposit(String.valueOf(a));
////        }else{
////            permohonanPihakPendepositTemp.setIdPermohonanPihakPendeposit("1");
////        }
////        permohonanPihakPendepositTemp.setIdPermohonanPihakPendeposit(String.valueOf(pihakTemp.getIdPihak()));
        permohonanPihakPendepositTemp.setPihak(pihakTemp);
        permohonanPihakPendepositTemp.setPermohonan(permohonan);
//        permohonanPihakPendepositTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanPihakPendepositTemp, "new", peng));
        permohonanPihakPendepositTemp.setInfoAudit(audit);
        permohonanPihakPendepositTemp.setCaraBayaran(permohonanPihakPendeposit.getCaraBayaran());
        permohonanPihakPendepositTemp.setBank(kodBankDAO.findById(permohonanPihakPendeposit.getBank().getKod()));
        permohonanPihakPendepositTemp.setNoAkaun(permohonanPihakPendeposit.getNoAkaun());
        permohonanPihakPendepositTemp.setAmaun(permohonanPihakPendeposit.getAmaun());
        if(permohonanPihakPendeposit.getCatatan() != null)
        permohonanPihakPendepositTemp.setCatatan(permohonanPihakPendeposit.getCatatan());
        permohonanPihakPendepositTemp.setCawangan(permohonan.getCawangan());

        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendepositTemp);


        rehydrate();

        return new JSP(DisPermohonanPage.getDEPO_EDIT_PAGE()).addParameter("tab", "true");
    }
    
    public Resolution kemaskiniKandunganPendeposit() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanPihakPendeposit permohonanPihakPendepositTemp = new PermohonanPihakPendeposit() ;
        permohonanPihakPendepositTemp = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(idPihakPendeposit));
        if(permohonanPihakPendepositTemp != null){
            Pihak pihakTemp = new Pihak() ;
            pihakTemp = pihakDAO.findById(permohonanPihakPendepositTemp.getPihak().getIdPihak()) ;
            if(pihakTemp != null){
                pihakTemp.setAlamat1(pihak.getAlamat1());
                pihakTemp.setAlamat2(pihak.getAlamat2());
                pihakTemp.setAlamat3(pihak.getAlamat3());
                pihakTemp.setAlamat4(pihak.getAlamat4());
                pihakTemp.setPoskod(pihak.getPoskod());
                pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
                pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
                pihakTemp.setEmail(pihak.getEmail());
                pihakTemp.setInfoAudit(disLaporanTanahService.findAudit(pihakTemp, "update", peng));
                pihakDAO.saveOrUpdate(pihakTemp);
            }
            InfoAudit audit = permohonanPihakPendepositTemp.getInfoAudit();
            audit.setDiKemaskiniOleh(pguna);
            audit.setTarikhKemaskini(new java.util.Date());
            permohonanPihakPendepositTemp.setInfoAudit(audit);
            permohonanPihakPendepositTemp.setPihak(pihakTemp);
            permohonanPihakPendepositTemp.setPermohonan(permohonan);
            permohonanPihakPendepositTemp.setCaraBayaran(permohonanPihakPendeposit.getCaraBayaran());
            permohonanPihakPendepositTemp.setBank(kodBankDAO.findById(permohonanPihakPendeposit.getBank().getKod()));
            permohonanPihakPendepositTemp.setNoAkaun(permohonanPihakPendeposit.getNoAkaun());
            permohonanPihakPendepositTemp.setAmaun(permohonanPihakPendeposit.getAmaun());
            if (permohonanPihakPendeposit.getCatatan() != null) {
                permohonanPihakPendepositTemp.setCatatan(permohonanPihakPendeposit.getCatatan());
            }
//            permohonanPihakPendepositTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanPihakPendepositTemp, "update", peng));
            permohonanPihakPendepositTemp.setCawangan(permohonan.getCawangan());

            pelupusanService.kemaskiniPermohonanPihakPendeposit(permohonanPihakPendepositTemp);
        }

        rehydrate();

        return new JSP(DisPermohonanPage.getDEPO_EDIT_PAGE()).addParameter("tab", "true");
    }
    
    public Resolution kemaskiniKandunganBank() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        PermohonanPihakPendeposit permohonanPihakPendepositTemp = new PermohonanPihakPendeposit() ;
        permohonanPihakPendepositTemp = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(idPihakPendeposit));
        if(permohonanPihakPendepositTemp != null){
            Pihak pihakTemp = new Pihak() ;
            pihakTemp = pihakDAO.findById(permohonanPihakPendepositTemp.getPihak().getIdPihak()) ;
            InfoAudit audit = permohonanPihakPendepositTemp.getInfoAudit();
            audit.setDiKemaskiniOleh(pguna);
            audit.setTarikhKemaskini(new java.util.Date());
            permohonanPihakPendepositTemp.setPihak(pihakTemp);
            permohonanPihakPendepositTemp.setPermohonan(permohonan);
//            permohonanPihakPendepositTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanPihakPendepositTemp, "update", peng));
            permohonanPihakPendepositTemp.setInfoAudit(audit);
            permohonanPihakPendepositTemp.setCawangan(permohonan.getCawangan());
            permohonanPihakPendepositTemp.setCaraBayaran(permohonanPihakPendeposit.getCaraBayaran());
            permohonanPihakPendepositTemp.setBank(kodBankDAO.findById(permohonanPihakPendeposit.getBank().getKod()));
            permohonanPihakPendepositTemp.setNoAkaun(permohonanPihakPendeposit.getNoAkaun());
            permohonanPihakPendepositTemp.setAmaun(permohonanPihakPendeposit.getAmaun());
            if(permohonanPihakPendeposit.getCatatan() != null)
            permohonanPihakPendepositTemp.setCatatan(permohonanPihakPendeposit.getCatatan());
            
            pelupusanService.kemaskiniPermohonanPihakPendeposit(permohonanPihakPendepositTemp);
        }

        rehydrate();

        return new JSP(DisPermohonanPage.getDEPO_EDIT_PAGE()).addParameter("tab", "true");
    }
    
    public Resolution deleteRow() throws ParseException {

        String idPihakPendeposit = getContext().getRequest().getParameter("idPihakPendeposit");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idPihakPendeposit != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idPihakPendeposit}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new JSP("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
//        return new JSP(DisPermohonanPage.getDEPO_EDIT_PAGE()).addParameter("tab", "true");
    }
    
    public Resolution showFormPopUp() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        pihak = new Pihak() ;
        permohonanPihakPendeposit = new PermohonanPihakPendeposit() ;
        return new JSP("pelupusan/common/pendeposit/pendepositV2Add.jsp").addParameter("popup", "true");
    }
    
    public Resolution showFormKemaskiniPendeposit() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPihakPendeposit = getContext().getRequest().getParameter("idPihakPendeposit");
        permohonanPihakPendeposit = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(idPihakPendeposit)) ;
        if(permohonanPihakPendeposit != null){
            pihak = permohonanPihakPendeposit.getPihak() ;
        }
        return new JSP("pelupusan/common/pendeposit/pendepositV2EditPendeposit.jsp").addParameter("popup", "true");
    }
    
    public Resolution showFormKemaskiniBank() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPihakPendeposit = getContext().getRequest().getParameter("idPihakPendeposit");
        permohonanPihakPendeposit = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(idPihakPendeposit)) ;
        if(permohonanPihakPendeposit != null){
            pihak = permohonanPihakPendeposit.getPihak() ;
        }
        return new JSP("pelupusan/common/pendeposit/pendepositV2EditBank.jsp").addParameter("popup", "true");
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public DisPendepositController getDisPendepositController() {
        return disPendepositController;
    }

    public void setDisPendepositController(DisPendepositController disPendepositController) {
        this.disPendepositController = disPendepositController;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanPihakPendeposit getPermohonanPihakPendeposit() {
        return permohonanPihakPendeposit;
    }

    public void setPermohonanPihakPendeposit(PermohonanPihakPendeposit permohonanPihakPendeposit) {
        this.permohonanPihakPendeposit = permohonanPihakPendeposit;
    }

    public List<PermohonanPihakPendeposit> getPermohonanPihakPendepositList() {
        return permohonanPihakPendepositList;
    }

    public void setPermohonanPihakPendepositList(List<PermohonanPihakPendeposit> permohonanPihakPendepositList) {
        this.permohonanPihakPendepositList = permohonanPihakPendepositList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihakPendeposit() {
        return idPihakPendeposit;
    }

    public void setIdPihakPendeposit(String idPihakPendeposit) {
        this.idPihakPendeposit = idPihakPendeposit;
    }
    
    
}
