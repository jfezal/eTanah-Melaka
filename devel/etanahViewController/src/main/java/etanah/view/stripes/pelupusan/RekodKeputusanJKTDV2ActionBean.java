/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
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
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
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
@UrlBinding("/pelupusan/rekod_keputusanJKTDV2")
public class RekodKeputusanJKTDV2ActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodKeputusanDAO kodKeputusanDAO ;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private FasaPermohonan fasaPermohonan;
    private Pengguna peng;
    private HakmilikPermohonan hakmilikPermohonan ;
    private DisRekodKeputusanController disRekodKeputusanController;
    private String kodNegeri;
    private String stageId;
    private String kpsnMohonFasa;
    private String idPermohonan;
    private static final Logger LOG = Logger.getLogger(RekodKeputusanJKTDV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    etanahActionBeanContext ctx;
    private List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan;
    private List<HakmilikPermohonan> senaraiHakmilikTolak;
    private List<HakmilikPermohonan> senaraiHakmilikLulus;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanKelompok> senaraiKelompok;
    private boolean kelompok;
    private int sizeTiadaKeputusan;
    private int sizeLulus;
    private int sizeTolak;

    @DefaultHandler
    public Resolution viewOnlyRekodKeputusanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_JKTD_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution showFormBeforeMesyuarat() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.showFormBeforeMesyuarat();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        httpSession.setAttribute("disShowFormBeforeMesyuarat", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_JKTD_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyRekodKeputusan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusan();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_JKTD_V2_MAIN_PAGE()).addParameter("tab", "true");
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
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
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
                senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();
                if (kelompok) {
                    if (senaraiKelompok.size() > 0) {
                        for (PermohonanKelompok pk : senaraiKelompok) {
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTemp = new ArrayList<HakmilikPermohonan>();
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTempTolak = new ArrayList<HakmilikPermohonan>();
                            senaraiHakmilikPermohonanTemp = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(pk.getPermohonan().getIdPermohonan());
                            senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                            senaraiHakmilikPermohonanTempTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(pk.getPermohonan().getIdPermohonan());
                            if (senaraiHakmilikPermohonanTemp.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTemp) {
                                    senaraiHakmilikTiadaKeputusan.add(hp);
                                }
                            }
                            if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                    senaraiHakmilikLulus.add(hp);
                                }
                            }
                            if (senaraiHakmilikPermohonanTempTolak.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempTolak) {
                                    senaraiHakmilikTolak.add(hp);
                                }
                            }
                        }
                        sizeTiadaKeputusan = senaraiHakmilikTiadaKeputusan.size() > 0 ? senaraiHakmilikTiadaKeputusan.size() : 0;
                        sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                        sizeTolak = senaraiHakmilikTolak.size() > 0 ? senaraiHakmilikTolak.size() : 0;
                    }
                }
            }

            String kodDok = kertasDok(permohonan.getKodUrusan().getKod());
            permohonanKertas = new PermohonanKertas();
            permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);

            String stageTemp = stageFasa(permohonan.getKodUrusan().getKod());
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan = (FasaPermohonan) disLaporanTanahService.findObject(fasaPermohonan, new String[]{idPermohonan, stageTemp}, 0);
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

        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
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
            senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();
            if (kelompok) {
                if (senaraiKelompok.size() > 0) {
                    for (PermohonanKelompok pk : senaraiKelompok) {
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTemp = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTempTolak = new ArrayList<HakmilikPermohonan>();
                        senaraiHakmilikPermohonanTemp = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(pk.getPermohonan().getIdPermohonan());
                        senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                        senaraiHakmilikPermohonanTempTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(pk.getPermohonan().getIdPermohonan());
                        if (senaraiHakmilikPermohonanTemp.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTemp) {
                                senaraiHakmilikTiadaKeputusan.add(hp);
                            }
                        }
                        if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                senaraiHakmilikLulus.add(hp);
                            }
                        }
                        if (senaraiHakmilikPermohonanTempTolak.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempTolak) {
                                senaraiHakmilikTolak.add(hp);
                            }
                        }
                    }
                    sizeTiadaKeputusan = senaraiHakmilikTiadaKeputusan.size() > 0 ? senaraiHakmilikTiadaKeputusan.size() : 0;
                    sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                    sizeTolak = senaraiHakmilikTolak.size() > 0 ? senaraiHakmilikTolak.size() : 0;
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

    public Resolution simpanKeputusan() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Permohonan permohonanTemp = new Permohonan();
        permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String stageTemp = stageFasa(permohonan.getKodUrusan().getKod());
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            if (kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PLPTD"))
            {
                permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            }else if(kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PBMT")){
                permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            }
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        }
        if (kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PBMT") && !StringUtils.isEmpty(kpsnMohonFasa)) {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, stageTemp);
            if (fasaPermohonan != null) {
                fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "update", peng));
            } else {
                fasaPermohonan = new FasaPermohonan();
                fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "new", peng));
            }
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setIdPengguna(peng.getIdPengguna());
            fasaPermohonan.setIdAliran(stageTemp);
            KodKeputusan kodKpsn = new KodKeputusan();
            kodKpsn = (KodKeputusan) disLaporanTanahService.findObject(kodKpsn, new String[]{kpsnMohonFasa}, 0);
            fasaPermohonan.setKeputusan(kodKpsn);
            disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);
        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
        return new JSP(DisPermohonanPage.getRK_JKTD_V2_MM_PAGE()).addParameter("tab", "true");
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
        String stageTemp = stageFasa(permohonan.getKodUrusan().getKod());
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            if(kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PBMT")){
                permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            }
//            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        }

        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
        return new JSP(DisPermohonanPage.getRK_JKTD_V2_MM_PAGE()).addParameter("tab", "true");
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
        int typeNum = type.equals("mMesyuarat") ? 1
                : type.equals("main") ? 2
                : type.equals("beforeMesyuarat") ? 3
                : type.equals("sHakmilik") ? 4
                : type.equals("saveHakmilik") ? 5
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getRK_JKTD_V2_MM_PAGE();
                kodDok = kertasDok(permohonan.getKodUrusan().getKod());
                permohonanKertas = new PermohonanKertas();
                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
                String stageTemp = stageFasa(permohonan.getKodUrusan().getKod());
                fasaPermohonan = (FasaPermohonan) disLaporanTanahService.findObject(fasaPermohonan, new String[]{idPermohonan, stageTemp}, 0);
                if (fasaPermohonan != null) {
                    if (fasaPermohonan.getKeputusan() != null) {
                        kpsnMohonFasa = fasaPermohonan.getKeputusan().getKod();
                    }
                }
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                break;
            case 2:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_JKTD_V2_MAIN_PAGE();
                break;
            case 3:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
                forwardJSP = DisPermohonanPage.getRK_JKTD_V2_MAIN_PAGE();
                break;
            case 4:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_JKTD_V2_SH_PAGE();
                break;
            case 5:
                addSimpleMessage("Data telah berjaya simpan");
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_JKTD_V2_SH_PAGE();
                break;
        }
        return forwardJSP;
    }
    
    public Resolution pilihTanah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kpsn = getContext().getRequest().getParameter("kpsn");
        String item = getContext().getRequest().getParameter("item");
        String[] listMH = item.split(",");
        LOG.info("Size :" + listMH.length);
        InfoAudit ia = new InfoAudit();

        for (int i = 0; i < listMH.length; i++) {

            if (!listMH[i].equals("T")) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(listMH[i]));
                if (hakmilikPermohonan != null) {
                    ia = hakmilikPermohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPermohonan.setInfoAudit(ia);
                    hakmilikPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
                    disLaporanTanahService.getPelupusanService().saveOrUpdate(hakmilikPermohonan);
                }
            }
        }
        rehydrate();
        addSimpleMessage("Data telah berjaya disimpan");
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SH_PAGE()).addParameter("tab", "true");
    }

    public String kertasDok(String urusan) {
        String kodDok = new String();
        kodDok = "JKTD";
        return kodDok;
    }

    public String stageFasa(String urusan) {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String stage = new String();
        int typeUrusan = urusan.equals("PBMT") ? 1
                : urusan.equals("PLPTD") ? 2
                : 0;

        switch (typeUrusan) {
            case 1:
                stage = "cetak_kertas_jktd";
                break;
            case 2:
                stage = "16Kptsn";
                break;
        }
        return stage;
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

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
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

    public String getKpsnMohonFasa() {
        return kpsnMohonFasa;
    }

    public void setKpsnMohonFasa(String kpsnMohonFasa) {
        this.kpsnMohonFasa = kpsnMohonFasa;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public boolean isKelompok() {
        return kelompok;
    }

    public void setKelompok(boolean kelompok) {
        this.kelompok = kelompok;
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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    
}
