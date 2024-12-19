/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPetakAksesoriDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.common.HakmilikPermohonanService;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.service.RegService;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodPerhubunganHakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanSkim;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import java.math.BigDecimal;
import java.util.*;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.service.CalcTax;
import etanah.service.common.HakmilikPihakKepentinganService;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/maklumat_hakmilik_permohonanNew")
public class NewStrataMaklumatHakmilikPermohonanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NewStrataMaklumatHakmilikPermohonanActionBean.class);
    @Inject
    HakmilikPermohonanService hPService;
    @Inject
    HakmilikPetakAksesoriDAO hakmilikPetakAksesoriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    CommonService comm;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    RegService regService;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodPerhubunganHakmilikDAO kodPerhubunganHakmilikDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    CalcTax calcTax;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanBaruList;
    private List<Hakmilik> hakmilikList;
    private List<Hakmilik> hakmilikListtemp;
    private List<HakmilikPermohonan> hakmilikPermohonanKemaskini;
    private List<HakmilikUrusan> urusanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList;
    private List<HakmilikPetakAksesori> listakmilikPetakAksesori;
    private List<KodHakmilik> senaraiKodHakmilik;
    private int size = 0;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pengguna pengguna;
    private Permohonan p;
    private Hakmilik hb;
    private Hakmilik hakmilik;
    private List hakmilikPermohonanMenanggung;
    private List hakmilikPermohonanMenguasai;
    private List<KodUOM> senaraiKodUOM;
    String hubunganHakmilik;
    String idHakmilikBaru;
    private String[] strKodUOM;
    private String[] strKodSyaratNyata;
    private String[] strKodSekatan;
    private String[] kodLot;
    private String[] jenisLot;
    private StringBuilder msg;
    private StringBuilder err;
    private List<KodDaerah> senaraiKodDaerah;
    private String noPelan1;
    private String noPu;
    private String kodNegeri;
    private String noBangunan;
    private String noTingkat;
    private String noPetak;
    private String katg_bngn;
    private String petakAksesori;
    private String petakAksesori2;
    private String idPermohonan;
    private String noSkim;
    private String noFail;
    private String bil;
    private String idHakmilikPelan;
    private String noPelanTamb;
    List<HakmilikPihakBerkepentingan> list;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    private String stageId;
    @Inject
    BPelService service;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    private String taskId;
    //added
    private String jumlahUnitSyor;

    @DefaultHandler
    public Resolution showForm() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
    }

    public Resolution showFormHm() {

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        return new JSP("strata/hakmilik_strata.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBerkelompok() {

        LOG.info("----------simpan hakmilik----------:");

        int counter = 0;
        for (Hakmilik hakmilik : hakmilikList) {

            KodUOM kuom = kodUOMDAO.findById(strKodUOM[counter]);
            KodSyaratNyata ksn = regService.searchKodSyaratByCaw(strKodSyaratNyata[counter], hakmilik.getCawangan().getKod());
            KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(strKodSekatan[counter], hakmilik.getCawangan().getKod());
            KodLot kl = kodLotDAO.findById(kodLot[counter]);

            counter = counter + 1;
            if (kuom != null) {
                hakmilik.setKodUnitLuas(kuom);
            }
            if (ksn != null) {
                hakmilik.setSyaratNyata(ksn);
            }
            if (ksk != null) {
                hakmilik.setSekatanKepentingan(ksk);
            }
            if (kl != null) {
                hakmilik.setLot(kl);
            }
            regService.simpanHakmilik(hakmilik);
        }

        addSimpleMessage("Kemaskini Data Berjaya");
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata.jsp").addParameter("tab", "true");
    }

    public Resolution deleteHakmilik() {

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("Deleting idHakmilik :" + idHakmilik);

        if (idHakmilik != null) {
            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);

            if (hp != null) {
                regService.deleteMohonHakmilik(hp);
            }
        }
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deleteHakmilik,!generateIDHakmilikStrata"})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());

        if (kodNegeri.equals("05")) {
            hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        } else {
            hakmilikPermohonanList = strService.findIdHakmilikSortAsc(idPermohonan);
        }

        String hm = null;

        if (p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null) {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk();
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }
        List<Hakmilik> listHakmilik = strService.findIdHakmilikByIdHakmilikInduk(hm);
        List<Hakmilik> listHakmilik2 = strService.findIdHakmilikByIdHakmilikIndukP(hm);

        Permohonan mohonHTB = strService.findIDSblmByKodUrusan(p.getIdPermohonan(), "HTB");
        Permohonan mohonPBBL = strService.findIDSblmByKodUrusan(p.getIdPermohonan(), "PBBL");
        Permohonan mohonPBBM = strService.findIDSblmByKodUrusan(p.getIdPermohonan(), "PBBM");
        Permohonan mohonECPI = strService.findIDSblmByKodUrusan(p.getIdPermohonan(), "ECPI");
        if (p.getKodUrusan().getKod().equals("PBBS") || p.getKodUrusan().getKod().equals("PBBD")
                /*|| p.getKodUrusan().getKod().equals("PSBS")*/ || p.getKodUrusan().getKod().equals("PBS")) {
            if (mohonHTB == null || mohonPBBL == null || mohonPBBM == null || mohonECPI ==  null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            } else if (mohonHTB.getKeputusan() == null || mohonPBBL.getKeputusan() == null
                    || mohonPBBM.getKeputusan() == null || mohonECPI.getKeputusan() == null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            }
        } else if (p.getKodUrusan().getKod().equals("PSBS")) {
            if (mohonPBBL == null || mohonPBBM == null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            } else if (mohonPBBL.getKeputusan() == null || mohonPBBM.getKeputusan() == null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            }
        }

        if (listHakmilik.size() <= 0) {
            if (mohonHTB == null || mohonECPI ==  null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            } else if (mohonHTB.getKeputusan() == null || mohonECPI.getKeputusan() == null) {
                addSimpleError("Sila pastikan endorsan telah didaftarkan! ");
            } else {
                comm.generateHakmilikStrata2(ia, p, pengguna);
            }
        } else if (p.getKodUrusan().getKod().equals("PSBS")) {
         if (listHakmilik2.size() > 0 && hakmilikPermohonanList.size() == 1) {
                comm.generateHakmilikStrata2(ia, p, pengguna);
            }
        }
        if (kodNegeri.equals("05")) {
            hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        } else {
            //hakmilikPermohonanList = strService.findIdHakmilikSortAsc(idPermohonan);
            hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc2(idPermohonan);
        }
        LOG.info("-------hakmilikPermohonanList------::" + hakmilikPermohonanList.size());
        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp = hakmilikPermohonanList.get(i);
                if (hp.getHakmilik().getIdHakmilikInduk() != null && hp.getHakmilik().getKodStatusHakmilik().getKod().equals("T")) {
                    hakmilikPermohonanBaruList.add(hp);
                }
            }
        }
        List<PermohonanBangunan> mbList = strService.checkMhnBangunanExist(idPermohonan);
        Integer jumlah = 0;
        for (PermohonanBangunan mb : mbList) {
            jumlah += mb.getSyerBlok();
        }
        jumlahUnitSyor = String.valueOf(jumlah);

//        if (p.getKodUrusan().getKod().equals("PSBS")) {
//            SimpanHakmilikAsal(idPermohonan, pengguna, hm);
//        }

        stageId = getBPLStageId();
        LOG.info("--stageId--:" + stageId);

    }

    public void SimpanHakmilikAsal(String idPermohonan, Pengguna pengguna, String idHakmilikInduk) {

        List<Hakmilik> senaraiHakmilik = strService.findHakmilikStrataByHakmilikIndukKATGBangunan(idHakmilikInduk);
        
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        if (senaraiHakmilik.size() > 0) {
            for (Hakmilik hakmiliks : senaraiHakmilik) {
                List<HakmilikAsalPermohonan> senaraiHakmilikAsal = regService.searchMohonHakmilikAsalByIDHakmilikAndHakmilikAsal(hakmiliks.getIdHakmilikInduk(), hakmiliks.getIdHakmilik());

                Hakmilik hmInduk = hakmilikDAO.findById(hakmiliks.getIdHakmilikInduk());
                HakmilikPermohonan mohonHakmilik = hakmilikPermohonanService.findHakmilikPermohonan(hakmiliks.getIdHakmilik(), idPermohonan);
                if (senaraiHakmilikAsal.size() <= 0) {

                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();

                    hap.setInfoAudit(infoAudit);
                    hap.setHakmilik(hmInduk);
                    hap.setHakmilikPermohonan(mohonHakmilik);
                    hap.setHakmilikSejarah(hakmiliks.getIdHakmilik());
                    regService.saveOrUpdate(hap);

                } else {
                    for (HakmilikAsalPermohonan hapLama : senaraiHakmilikAsal) {
                        InfoAudit infoAudit2 = new InfoAudit();
                        infoAudit2.setDimasukOleh(pengguna);
                        infoAudit2.setTarikhKemaskini(new java.util.Date());

                        hapLama.setInfoAudit(infoAudit2);
                        hapLama.setHakmilik(hmInduk);
                        hapLama.setHakmilikPermohonan(mohonHakmilik);
                        hapLama.setHakmilikSejarah(hakmiliks.getIdHakmilik());
                        regService.saveOrUpdate(hapLama);
                    }
                }
            }
        }
    }

    public String getBPLStageId() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("-------------stageId--" + stageId);
        }
        return stageId;
    }

    public Resolution kiraCukaiKelompok() {
        String result = "";
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
        String luas = (String) getContext().getRequest().getParameter("luas");
        String kodRizab;
        LOG.info("kiraCukaiKelompok");
        LOG.info(":idhakmilik:" + idHakmilik);
        LOG.info(":kodUOM:" + kodUOM);

        BigDecimal luasHakmilik = new BigDecimal(luas);
        LOG.info(":luas:" + luasHakmilik);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);
        if (h != null && StringUtils.isNotBlank(idHakmilik) && StringUtils.isNotBlank(kodUOM) && StringUtils.isNotBlank(luasHakmilik.toString())) {
            LOG.info("kodTanah : " + h.getKegunaanTanah().getKod());
            LOG.info("kodbpm : " + h.getBandarPekanMukim().getKod());
            if (h.getRizab() != null) {
                LOG.info("kodRizab : " + h.getRizab().getKod());
                kodRizab = String.valueOf(h.getRizab().getKod());
            } else {
                kodRizab = null;
            }
            result = String.valueOf(calcTax.calculate(h.getKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, luasHakmilik, h, kodRizab));
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution showHakmilikStrata() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        String idHm = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        LOG.info("ID HAKMILIK = " + idHm);
        pihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikDAO.findById(idHm));
        pihakKepentinganSelainPMList = regService.searchPihakBerKepentinganSelainPemilikWarisCucuCicit(idHm);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        noPelan1 = hakmilik.getNoPelan();
        LOG.info("---show Hakmilik Strata--noPelan---:" + noPelan1);

        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.info("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.info("::get jeniskodhakmilik PTD :::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
        }
        return new JSP("strata/pbbm/perincian_hakmilik_strata.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilikStrataReg() {
        LOG.info("---simpan hakmilik---:");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik2 = (String) getContext().getRequest().getParameter("idHakmilik1");
        LOG.info("---ID Hakmilik---" + idHakmilik2);
        hakmilik = hakmilikDAO.findById(idHakmilik2);
        LOG.info("---Hakmilik---:" + hakmilik);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Hakmilik hakmiliktemp = new Hakmilik();
        hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        LOG.info("---hakmiliktemp---:" + hakmiliktemp);
        if (hakmiliktemp != null) {
            LOG.info("---hakmiliktemp-not null--:");
            if (noPelan1 != null) {
                LOG.info("---noPelan--:" + noPelan1);
                hakmiliktemp.setNoPelan(noPelan1);
            }
            LOG.info("---infoAudit--:" + infoAudit);
            hakmiliktemp.setInfoAudit(infoAudit);
            strService.simpanHakmilik(hakmiliktemp);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showForm3");
    }

    public Resolution simpanNoPAB() {
        LOG.info("---simpan hakmilik---:");

//        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc2(idPermohonan);
        String idHakmilik2 = "";
        noPelan1 = (String) getContext().getRequest().getParameter("noPelan1");
        noPu = (String) getContext().getRequest().getParameter("noPu");
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        noFail = (String) getContext().getRequest().getParameter("noFail");
        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                idHakmilik2 = (String) getContext().getRequest().getParameter("checkbox" + i);

                Hakmilik hm = strService.findInfoByIdHakmilik(idHakmilik2);
                if (hm != null) {
                    hm.setNoPelan(noPelan1);
                    hm.setNoPu(noPu);
                    hm.setNoSkim(noSkim);
                    hm.setNoFail(noFail);
                    strService.simpanHakmilik(hm);

                    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    //  String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

                    LOG.info("---ID Hakmilik---" + idHakmilik2);
                    hakmilik = hakmilikDAO.findById(idHakmilik2);
                    LOG.info("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.info("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.info("---hakmiliktemp-not null--:");
                        if (noPelan1 != null) {
                            LOG.info("---noPelan--:" + noPelan1);
                            hakmiliktemp.setNoPelan(noPelan1);
                        }
                        LOG.info("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }
                }

            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution simpanLuas() {
        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc2(idPermohonan);
        String idHakmilik2 = "";

        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                String luas = (String) getContext().getRequest().getParameter("luas" + i);
                idHakmilik2 = (String) getContext().getRequest().getParameter("hm" + i);

                Hakmilik hm = strService.findInfoByIdHakmilik(idHakmilik2);
                if (hm != null) {
                    if (luas != String.valueOf(hm.getLuas())) {
                        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

                        LOG.info("---ID Hakmilik---" + idHakmilik2);
                        hakmilik = hakmilikDAO.findById(idHakmilik2);
                        LOG.info("---Hakmilik---:" + hakmilik);
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());

                        hm.setLuas(BigDecimal.valueOf(Double.parseDouble(luas)));
                        hm.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hm);
                    }
                }
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution simpanNoPu() {
        LOG.info("---simpan hakmilik---:");

        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        String idHakmilik2 = "";
        noPu = (String) getContext().getRequest().getParameter("noPu");
        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                idHakmilik2 = (String) getContext().getRequest().getParameter("checkbox" + i);

                Hakmilik hm = strService.findInfoByIdHakmilik(idHakmilik2);
                if (hm != null) {
                    hm.setNoPu(noPu);

                    strService.simpanHakmilik(hm);

                    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    //  String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

                    LOG.info("---ID Hakmilik---" + idHakmilik2);
                    hakmilik = hakmilikDAO.findById(idHakmilik2);
                    LOG.info("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp2 = new Hakmilik();
                    hakmiliktemp2 = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.info("---hakmiliktemp---:" + hakmiliktemp2);
                    if (hakmiliktemp2 != null) {
                        LOG.info("---hakmiliktemp-not null--:");
                        if (noPu != null) {
                            LOG.info("---noPu--:" + noPu);
                            hakmiliktemp2.setNoPu(noPu);
                        }
                        LOG.info("---infoAudit--:" + infoAudit);
                        hakmiliktemp2.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp2);
                    }
                }

            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution addPelan() {
        bil = (String) getContext().getRequest().getParameter("bil");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        listakmilikPetakAksesori = strService.findHakmilikPelanTambah(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null ? p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() : p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        return new ForwardResolution("/WEB-INF/jsp/strata/hakmilik_strata_pelan.jsp").addParameter("popup", "true");
    }

    public Resolution semakPelan() {
        bil = null;
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        listakmilikPetakAksesori = strService.findHakmilikPelanTambah(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null ? p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() : p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        return new ForwardResolution("/WEB-INF/jsp/strata/hakmilik_strata_pelan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPelanTamb() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        bil = (String) getContext().getRequest().getParameter("bil");
        idHakmilikPelan = (String) getContext().getRequest().getParameter("idHakmilikPelan");

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (idHakmilikPelan != null) {
            int count = 0;
            for (int i = 0; i < Integer.parseInt(bil); i++) {
                noPelanTamb = (String) getContext().getRequest().getParameter("noPelanTamb" + i);
                HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
                hpa.setCawangan(peng.getKodCawangan());
                hpa.setHakmilik(hakmilikDAO.findById(idHakmilikPelan));
                hpa.setInfoAudit(infoAudit);
                hpa.setNoPelan(noPelanTamb);
                strService.simpanhakmilikPetakAks(hpa);
                count++;
            }

            if (count > 0) {
                addSimpleMessage("Maklumat berjaya disimpan.");
            }
        }
        bil = null;
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        listakmilikPetakAksesori = strService.findHakmilikPelanTambah(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null ? p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() : p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

        return new ForwardResolution("/WEB-INF/jsp/strata/hakmilik_strata_pelan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePelanTamb() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idAksr = (String) getContext().getRequest().getParameter("idAksr");

        if (idAksr != null) {
            HakmilikPetakAksesori hpa = hakmilikPetakAksesoriDAO.findById(Long.parseLong(idAksr));

            if (hpa != null) {
                strService.deletehakmilikPetakAks(hpa);
                addSimpleMessage("Maklumat berjaya dihapuskan.");
            }
        }
        bil = null;
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        listakmilikPetakAksesori = strService.findHakmilikPelanTambah(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null ? p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() : p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

//        return new JSP("strata/hakmilik_strata_popup.jsp").addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/strata/hakmilik_strata_pelan.jsp").addParameter("popup", "true");
    }

    public Resolution reset() {
        LOG.info("---simpan hakmilik---:");

        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);

        noPelan1 = "";
        noPu = "";
        if (!hakmilikPermohonanList.isEmpty()) {

            for (HakmilikPermohonan hmilik : hakmilikPermohonanList) {

                Hakmilik hm = strService.findInfoByIdHakmilik(hmilik.getHakmilik().getIdHakmilik());
                if (hm != null) {
                    hm.setNoPelan(noPelan1);
                    hm.setNoPu(noPu);

                    strService.simpanHakmilik(hm);

                    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    //  String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    String idHakmilik2 = hmilik.getHakmilik().getIdHakmilik();
                    LOG.info("---ID Hakmilik---" + idHakmilik2);
                    hakmilik = hakmilikDAO.findById(idHakmilik2);
                    LOG.info("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.info("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.info("---hakmiliktemp-not null--:");
                        if (noPelan1 != null) {
                            LOG.info("---noPelan--:" + noPelan1);
                            hakmiliktemp.setNoPelan(noPelan1);
                        }
                        LOG.info("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }

                }
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution reset2() {
        LOG.info("---simpan hakmilik---:");

        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);

        noPu = "";

        if (!hakmilikPermohonanList.isEmpty()) {

            for (HakmilikPermohonan hmilik : hakmilikPermohonanList) {

                Hakmilik hm = strService.findInfoByIdHakmilik(hmilik.getHakmilik().getIdHakmilik());
                if (hm != null) {
                    hm.setNoPu(noPu);

                    strService.simpanHakmilik(hm);

                    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    //  String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    String idHakmilik2 = hmilik.getHakmilik().getIdHakmilik();
                    LOG.info("---ID Hakmilik---" + idHakmilik2);
                    hakmilik = hakmilikDAO.findById(idHakmilik2);
                    LOG.info("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.info("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.info("---hakmiliktemp-not null--:");
                        if (noPu != null) {
                            LOG.info("---noPu--:" + noPu);
                            hakmiliktemp.setNoPu(noPu);
                        }
                        LOG.info("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }

                }
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution simpanHakmilikStrata() {
        LOG.info("---simpan hakmilik---:");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idHakmilik2 = (String) getContext().getRequest().getParameter("idHakmilik1");
        LOG.info("---ID Hakmilik---" + idHakmilik2);
        hakmilik = hakmilikDAO.findById(idHakmilik2);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (hakmilik != null) {
            LOG.info("---hakmilik-not null--:");
            if (noPelan1 != null) {
                LOG.info("---noPelan--:" + noPelan1);
                hakmilik.setNoPelan(noPelan1);
            }
            LOG.info("---infoAudit--:" + infoAudit);
            hakmilik.setInfoAudit(infoAudit);
            strService.updateHakmilik(hakmilik);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
        return new RedirectResolution(NewStrataMaklumatHakmilikPermohonanActionBean.class, "showForm2");
    }

    public Resolution showHakmilikStrataReg() {
        LOG.info("---show Hakmilik Strata---:");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik1 = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("---ID Hakmilik---" + idHakmilik1);
        hakmilik = hakmilikDAO.findById(idHakmilik1);
        noBangunan = hakmilik.getNoBangunan();
        noTingkat = hakmilik.getNoTingkat();
        noPetak = hakmilik.getNoPetak();
        katg_bngn = hakmilik.getKodKategoriBangunan().getKod();
        // if (kodNegeri.equals("05")) {
        List<HakmilikPetakAksesori> petakAksr = strService.findIDforPetakAsc(hakmilik.getIdHakmilik());
        petakAksesori2 = "";
        if (!petakAksr.equals(null)) {
            for (HakmilikPetakAksesori hpa : petakAksr) {
                if (!petakAksesori2.equals("")) {
                    petakAksesori2 = petakAksesori2 + ", ";
                }
                petakAksesori2 = petakAksesori2 + "A" + hpa.getNama();
            }
        }

        if (katg_bngn.equals("L")) {
            noBangunan = "";
            noTingkat = "";
            noPetak = "L" + hakmilik.getNoPetak();
        }
        noPelan1 = hakmilik.getNoPelan();
        LOG.info("---show Hakmilik Strata--KATG BNGN---:" + katg_bngn);
        LOG.info("---show Hakmilik Strata--noBangunan---:" + noBangunan);
        LOG.info("---show Hakmilik Strata--noTingkat---:" + noTingkat);
        LOG.info("---show Hakmilik Strata--noPetak---:" + noPetak);
        LOG.info("---show Hakmilik Strata--noPelan---:" + noPelan1);
        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.info("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.info("::get jeniskodhakmilik PTD :::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
        }
        return new JSP("strata/pbbm/perincian_hakmilik_strata_reg.jsp").addParameter("tab", "true");
    }

    public Resolution showHakmilikStrataReg2() {
        LOG.info("---show Hakmilik Strata---:");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik1 = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("---ID Hakmilik---" + idHakmilik1);
        hakmilik = hakmilikDAO.findById(idHakmilik1);
        noBangunan = hakmilik.getNoBangunan();
        noTingkat = hakmilik.getNoTingkat();
        noPetak = hakmilik.getNoPetak();
        katg_bngn = hakmilik.getKodKategoriBangunan().getKod();
        // if (kodNegeri.equals("05")) {
        List<HakmilikPetakAksesori> petakAksr = strService.findIDforPetakAsc(hakmilik.getIdHakmilik());
        petakAksesori2 = "";
        if (!petakAksr.equals(null)) {
            for (HakmilikPetakAksesori hpa : petakAksr) {
                if (!petakAksesori2.equals("")) {
                    petakAksesori2 = petakAksesori2 + ", ";
                }
                petakAksesori2 = petakAksesori2 + "A" + hpa.getNama();
            }
        }

        if (katg_bngn.equals("L")) {
            noBangunan = "";
            noTingkat = "";
            noPetak = "L" + hakmilik.getNoPetak();
        }
        noPelan1 = hakmilik.getNoPelan();
        LOG.info("---show Hakmilik Strata--KATG BNGN---:" + katg_bngn);
        LOG.info("---show Hakmilik Strata--noBangunan---:" + noBangunan);
        LOG.info("---show Hakmilik Strata--noTingkat---:" + noTingkat);
        LOG.info("---show Hakmilik Strata--noPetak---:" + noPetak);
        LOG.info("---show Hakmilik Strata--noPelan---:" + noPelan1);
        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.info("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.info("::get jeniskodhakmilik PTD :::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
        }
        return new JSP("strata/perincian_petak_aksesori.jsp").addParameter("tab", "true");
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public String[] getStrKodUOM() {
        return strKodUOM;
    }

    public void setStrKodUOM(String[] strKodUOM) {
        this.strKodUOM = strKodUOM;
    }

    public List getHakmilikPermohonanMenguasai() {
        return hakmilikPermohonanMenguasai;
    }

    public void setHakmilikPermohonanMenguasai(List hakmilikPermohonanMenguasai) {
        this.hakmilikPermohonanMenguasai = hakmilikPermohonanMenguasai;
    }

    public List getHakmilikPermohonanMenanggung() {
        return hakmilikPermohonanMenanggung;
    }

    public void setHakmilikPermohonanMenanggung(List hakmilikPermohonanMenanggung) {
        this.hakmilikPermohonanMenanggung = hakmilikPermohonanMenanggung;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public Hakmilik getHb() {
        return hb;
    }

    public void setHb(Hakmilik hb) {
        this.hb = hb;
    }

    public String getHubunganHakmilik() {
        return hubunganHakmilik;
    }

    public void setHubunganHakmilik(String hubunganHakmilik) {
        this.hubunganHakmilik = hubunganHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikUrusan> getUrusanList() {
        return urusanList;
    }

    public void setUrusanList(List<HakmilikUrusan> urusanList) {
        this.urusanList = urusanList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanKemaskini() {
        return hakmilikPermohonanKemaskini;
    }

    public void setHakmilikPermohonanKemaskini(List<HakmilikPermohonan> hakmilikPermohonanKemaskini) {
        this.hakmilikPermohonanKemaskini = hakmilikPermohonanKemaskini;
    }

    public List<KodUOM> getSenaraiKodUOM() {
        return senaraiKodUOM;
    }

    public void setSenaraiKodUOM(List<KodUOM> senaraiKodUOM) {
        this.senaraiKodUOM = senaraiKodUOM;
    }

    public String[] getStrKodSekatan() {
        return strKodSekatan;
    }

    public void setStrKodSekatan(String[] strKodSekatan) {
        this.strKodSekatan = strKodSekatan;
    }

    public String[] getStrKodSyaratNyata() {
        return strKodSyaratNyata;
    }

    public void setStrKodSyaratNyata(String[] strKodSyaratNyata) {
        this.strKodSyaratNyata = strKodSyaratNyata;
    }

    public String[] getJenisLot() {
        return jenisLot;
    }

    public void setJenisLot(String[] jenisLot) {
        this.jenisLot = jenisLot;
    }

    public String[] getKodLot() {
        return kodLot;
    }

    public void setKodLot(String[] kodLot) {
        this.kodLot = kodLot;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
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

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganSelainPMList() {
        return pihakKepentinganSelainPMList;
    }

    public void setPihakKepentinganSelainPMList(List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList) {
        this.pihakKepentinganSelainPMList = pihakKepentinganSelainPMList;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public void setSenaraiKodDaerah(List<KodDaerah> senaraiKodDaerah) {
        this.senaraiKodDaerah = senaraiKodDaerah;
    }

    public List<KodHakmilik> getSenaraiKodHakmilik() {
        return senaraiKodHakmilik;
    }

    public void setSenaraiKodHakmilik(List<KodHakmilik> senaraiKodHakmilik) {
        this.senaraiKodHakmilik = senaraiKodHakmilik;
    }

    public List<Hakmilik> getHakmilikListtemp() {
        return hakmilikListtemp;
    }

    public void setHakmilikListtemp(List<Hakmilik> hakmilikListtemp) {
        this.hakmilikListtemp = hakmilikListtemp;
    }

    public String getNoPelan1() {
        return noPelan1;
    }

    public void setNoPelan1(String noPelan1) {
        this.noPelan1 = noPelan1;
    }

    public List<HakmilikPihakBerkepentingan> getList() {
        return list;
    }

    public void setList(List<HakmilikPihakBerkepentingan> list) {
        this.list = list;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getPetakAksesori() {
        return petakAksesori;
    }

    public void setPetakAksesori(String petakAksesori) {
        this.petakAksesori = petakAksesori;
    }

    public String getPetakAksesori2() {
        return petakAksesori2;
    }

    public void setPetakAksesori2(String petakAksesori2) {
        this.petakAksesori2 = petakAksesori2;
    }

    public String getKatg_bngn() {
        return katg_bngn;
    }

    public void setKatg_bngn(String katg_bngn) {
        this.katg_bngn = katg_bngn;
    }

    public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getJumlahUnitSyor() {
        return jumlahUnitSyor;
    }

    public void setJumlahUnitSyor(String jumlahUnitSyor) {
        this.jumlahUnitSyor = jumlahUnitSyor;
    }

    public String getNoPu() {
        return noPu;
    }

    public void setNoPu(String noPu) {
        this.noPu = noPu;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanBaruList() {
        return hakmilikPermohonanBaruList;
    }

    public void setHakmilikPermohonanBaruList(List<HakmilikPermohonan> hakmilikPermohonanBaruList) {
        this.hakmilikPermohonanBaruList = hakmilikPermohonanBaruList;
    }

    public String getNoSkim() {
        return noSkim;
    }

    public void setNoSkim(String noSkim) {
        this.noSkim = noSkim;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getBil() {
        return bil;
    }

    public void setBil(String bil) {
        this.bil = bil;
    }

    public String getIdHakmilikPelan() {
        return idHakmilikPelan;
    }

    public void setIdHakmilikPelan(String idHakmilikPelan) {
        this.idHakmilikPelan = idHakmilikPelan;
    }

    public String getNoPelanTamb() {
        return noPelanTamb;
    }

    public void setNoPelanTamb(String noPelanTamb) {
        this.noPelanTamb = noPelanTamb;
    }

    public List<HakmilikPetakAksesori> getListakmilikPetakAksesori() {
        return listakmilikPetakAksesori;
    }

    public void setListakmilikPetakAksesori(List<HakmilikPetakAksesori> listakmilikPetakAksesori) {
        this.listakmilikPetakAksesori = listakmilikPetakAksesori;
    }

}
