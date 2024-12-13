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
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
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
import java.util.ArrayList;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.service.CalcTax;
import etanah.service.common.HakmilikPihakKepentinganService;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/maklumat_hakmilik_permohonan")
public class StrataMaklumatHakmilikPermohonanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(StrataMaklumatHakmilikPermohonanActionBean.class);
    @Inject
    HakmilikPermohonanService hPService;
    @Inject
    PermohonanDAO permohonanDAO;
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
        LOG.debug("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);
        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
    }

    public Resolution showFormHm() {


        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idHakmilik maklumat hakmilik permohonan :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        return new JSP("strata/hakmilik_baru_strata.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBerkelompok() {


        LOG.debug("----------simpan hakmilik----------:");

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
        LOG.debug("Deleting idHakmilik :" + idHakmilik);

        if (idHakmilik != null) {
            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);

            if (hp != null) {
                regService.deleteMohonHakmilik(hp);
            }
        }
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "senaraiHakmilikIsmen").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!deleteHakmilik,!generateIDHakmilikStrata"})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("05")) {
            hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        } else {
            hakmilikPermohonanList = strService.findIdHakmilikSortAsc(idPermohonan);
        }
        LOG.debug("-------hakmilikPermohonanList------::" + hakmilikPermohonanList.size());
        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp = hakmilikPermohonanList.get(i);
                if (hp.getHakmilik().getIdHakmilik().length() > 20 && hp.getHakmilik().getKodStatusHakmilik().getKod().equals("T")) {
                    hakmilikPermohonanBaruList.add(hp);
//                    if (kodNegeri.equals("05")) {
//                    List<HakmilikPetakAksesori> petakAksr = strService.findIDforPetakAsc(hp.getHakmilik().getIdHakmilik());
//                    petakAksesori2 = "";
//                    if (!petakAksr.equals(null)) {
//                        for (HakmilikPetakAksesori hpa : petakAksr) {
//                            if (!petakAksesori2.equals("")) {
//                                petakAksesori2 = petakAksesori2 + ", ";
//                            }
//                            petakAksesori2 = petakAksesori2 + "A" + hpa.getNama();
//                        }
//                    }
//                    }

                    LOG.debug("--Long hakmilikPermohonanBaruList--" + hakmilikPermohonanBaruList);
                }
            }
            LOG.debug("-------hakmilikPermohonanList--for Tanah TAB to get small IdHakmilik----::" + hakmilikPermohonanList);
            idHakmilik = hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik();
            LOG.debug("-------idHakmilik------::" + idHakmilik);
        }

        hakmilikListtemp = strService.findIdHakmilikByIdHakmilikInduk(idHakmilik);
        LOG.debug("-------hakmilikListtemp--for Registration Module----::" + hakmilikListtemp);

        hakmilikList = new ArrayList<Hakmilik>();
        for (int i = 0; i < hakmilikListtemp.size(); i++) {
            Hakmilik hk = new Hakmilik();
            hk = hakmilikListtemp.get(i);
            if (hk.getIdHakmilik().length() > 20) {
                hakmilikList.add(hk);
                LOG.debug("--Long hakmilik List for registration Module--" + hakmilikList);
            }
        }

        size = hakmilikList.size();
        if (hakmilikList.size() > 0) {

            strKodUOM = new String[hakmilikList.size()];
            strKodSyaratNyata = new String[hakmilikList.size()];
            strKodSekatan = new String[hakmilikList.size()];
            jenisLot = new String[hakmilikList.size()];
            kodLot = new String[hakmilikList.size()];

            int counter = 0;
            for (int i = 0; i < hakmilikList.size(); i++) {
                Hakmilik hakmilik = hakmilikList.get(i);
                strKodUOM[counter] = hakmilik.getKodUnitLuas().getNama();
                if (hakmilik.getSyaratNyata() != null) {
                    KodSyaratNyata ksn = regService.searchKodSyaratByCaw(hakmilik.getSyaratNyata().getKod(), hakmilik.getCawangan().getKod());
                    if (ksn != null) {
                        strKodSyaratNyata[counter] = ksn.getKodSyarat();
                    } else {
                        strKodSyaratNyata[counter] = "";
                    }
                }
                if (hakmilik.getSekatanKepentingan() != null) {
                    KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(hakmilik.getSekatanKepentingan().getKod(), hakmilik.getCawangan().getKod());

                    if (ksk != null) {
                        strKodSekatan[counter] = ksk.getKodSekatan();
                    } else {
                        strKodSekatan[counter] = "";
                    }

                }
//                    if (hp.getHubunganHakmilik() != null) {
//                        jenisLot[counter] = hp.getHubunganHakmilik().getKod();
//                    }
                if (hakmilik.getLot() != null) {
                    kodLot[counter] = hakmilik.getLot().getKod();
                }

                counter = counter + 1;
            }

            List<HakmilikUrusan> hakmilikUrusan = new ArrayList<HakmilikUrusan>();
            urusanList = new ArrayList<HakmilikUrusan>();
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;

                }
                idHakmilik = hp.getHakmilik().getIdHakmilik();
                hakmilikUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(idHakmilik);
                for (HakmilikUrusan hu : hakmilikUrusan) {
                    if (hu == null) {
                        continue;
                    }
                    urusanList.add(hu);
                }
            }
        }

        //to get unit syor from hakmilik
        LOG.debug("---Id permohonan---" + p);
        Permohonan idpsb = new Permohonan();
        idpsb = p;
        LOG.debug("---Id permohonan---SBLM---" + idpsb);
        List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanList1 = idpsb.getSenaraiHakmilik();
        LOG.debug("---HakmilikPermohonanList1 from---SBLM---" + hakmilikPermohonanList1);

        String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
        LOG.debug("---Id Hakmilik---SBLM---" + hkp1);
        hakmilik = hakmilikDAO.findById(hkp1);

        //to get Jumlah unit syor from Mohon_skim
        stageId = getBPLStageId();
        LOG.info("--stageId--:" + stageId);
        if (stageId.equals("g_keputusan2")) {
            LOG.debug("---current Id permohonan---" + p.getIdPermohonan());
            List<PermohonanBangunan> bng = strService.findByIDPermohonan1(p.getIdPermohonan());
            LOG.debug("---PermohonanBangunan for current idmohon------" + bng.get(0).getIdBangunan());
            PermohonanSkim idskim = bng.get(0).getPermohonanSkim();
            jumlahUnitSyor = idskim.getJumlahUnitSyer().toString();
            LOG.debug("---jumlahUnitSyor---" + jumlahUnitSyor);
        } else {
            LOG.debug("---current Id permohonan---" + p.getIdPermohonan());
            idpsb = p;
            LOG.debug("---Id permohonan---SBLM---" + idpsb.getPermohonanSebelum().getIdPermohonan());
            List<PermohonanBangunan> bng = strService.findByIDPermohonan1(idpsb.getPermohonanSebelum().getIdPermohonan());
            LOG.debug("---PermohonanBangunan------" + bng.get(0).getIdBangunan());
            LOG.debug("--- Kod Urusan Sblm ---" + p.getPermohonanSebelum().getKodUrusan());
            PermohonanSkim idskim = bng.get(0).getPermohonanSkim();
            if(p.getPermohonanSebelum().getKodUrusan().getKod().equals("HTSPB") 
              || p.getPermohonanSebelum().getKodUrusan().getKod().equals("HTSPS")){
              
                HakmilikPermohonan hp = strService.findMohonHakmilik(p.getPermohonanSebelum().getIdPermohonan());
                Hakmilik hm = strService.findInfoByIdHakmilik(hp.getHakmilik().getIdHakmilik());
                jumlahUnitSyor = hm.getUnitSyer().toString();
            } else {
                jumlahUnitSyor = idskim.getJumlahUnitSyer().toString();
            }
                        
            LOG.debug("---jumlahUnitSyor---" + jumlahUnitSyor);
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
        LOG.debug("kiraCukaiKelompok");
        LOG.debug(":idhakmilik:" + idHakmilik);
        LOG.debug(":kodUOM:" + kodUOM);

        BigDecimal luasHakmilik = new BigDecimal(luas);
        LOG.debug(":luas:" + luasHakmilik);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);
        if (h != null && StringUtils.isNotBlank(idHakmilik) && StringUtils.isNotBlank(kodUOM) && StringUtils.isNotBlank(luasHakmilik.toString())) {
            LOG.debug("kodTanah : " + h.getKegunaanTanah().getKod());
            LOG.debug("kodbpm : " + h.getBandarPekanMukim().getKod());
            if (h.getRizab() != null) {
                LOG.debug("kodRizab : " + h.getRizab().getKod());
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
        LOG.debug("---show Hakmilik Strata--noPelan---:" + noPelan1);


        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.debug("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.debug("::get jeniskodhakmilik PTD :::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
        }
        return new JSP("strata/pbbm/perincian_hakmilik_strata.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilikStrataReg() {
        LOG.debug("---simpan hakmilik---:");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik2 = (String) getContext().getRequest().getParameter("idHakmilik1");
        LOG.info("---ID Hakmilik---" + idHakmilik2);
        hakmilik = hakmilikDAO.findById(idHakmilik2);
        LOG.debug("---Hakmilik---:" + hakmilik);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Hakmilik hakmiliktemp = new Hakmilik();
        hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        LOG.debug("---hakmiliktemp---:" + hakmiliktemp);
        if (hakmiliktemp != null) {
            LOG.debug("---hakmiliktemp-not null--:");
            if (noPelan1 != null) {
                LOG.debug("---noPelan--:" + noPelan1);
                hakmiliktemp.setNoPelan(noPelan1);
            }
            LOG.debug("---infoAudit--:" + infoAudit);
            hakmiliktemp.setInfoAudit(infoAudit);
            strService.simpanHakmilik(hakmiliktemp);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showForm3");
    }

    public Resolution simpanNoPAB() {
        LOG.debug("---simpan hakmilik---:");

        hakmilikPermohonanList = strService.findIdHakmilikSortIDHMAsc(idPermohonan);
        String idHakmilik2 = "";
        noPelan1 = (String) getContext().getRequest().getParameter("noPelan1");
        noPu = (String) getContext().getRequest().getParameter("noPu");
        if (!hakmilikPermohonanList.isEmpty()) {
            hakmilikPermohonanBaruList = new ArrayList<HakmilikPermohonan>();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                idHakmilik2 = (String) getContext().getRequest().getParameter("checkbox" + i);

                Hakmilik hm = strService.findInfoByIdHakmilik(idHakmilik2);
                if (hm != null) {
                    hm.setNoPelan(noPelan1);
                    hm.setNoPu(noPu);
                    strService.simpanHakmilik(hm);

                    Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    //  String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


                    LOG.info("---ID Hakmilik---" + idHakmilik2);
                    hakmilik = hakmilikDAO.findById(idHakmilik2);
                    LOG.debug("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.debug("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.debug("---hakmiliktemp-not null--:");
                        if (noPelan1 != null) {
                            LOG.debug("---noPelan--:" + noPelan1);
                            hakmiliktemp.setNoPelan(noPelan1);
                        }
                        LOG.debug("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }
                }

            }
        }


        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution simpanNoPu() {
        LOG.debug("---simpan hakmilik---:");

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
                    LOG.debug("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp2 = new Hakmilik();
                    hakmiliktemp2 = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.debug("---hakmiliktemp---:" + hakmiliktemp2);
                    if (hakmiliktemp2 != null) {
                        LOG.debug("---hakmiliktemp-not null--:");
                        if (noPu != null) {
                            LOG.debug("---noPu--:" + noPu);
                            hakmiliktemp2.setNoPu(noPu);
                        }
                        LOG.debug("---infoAudit--:" + infoAudit);
                        hakmiliktemp2.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp2);
                    }
                }

            }
        }


        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution reset() {
        LOG.debug("---simpan hakmilik---:");

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
                    LOG.debug("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.debug("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.debug("---hakmiliktemp-not null--:");
                        if (noPelan1 != null) {
                            LOG.debug("---noPelan--:" + noPelan1);
                            hakmiliktemp.setNoPelan(noPelan1);
                        }
                        LOG.debug("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }

                }
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution reset2() {
        LOG.debug("---simpan hakmilik---:");

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
                    LOG.debug("---Hakmilik---:" + hakmilik);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    Hakmilik hakmiliktemp = new Hakmilik();
                    hakmiliktemp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
                    LOG.debug("---hakmiliktemp---:" + hakmiliktemp);
                    if (hakmiliktemp != null) {
                        LOG.debug("---hakmiliktemp-not null--:");
                        if (noPu != null) {
                            LOG.debug("---noPu--:" + noPu);
                            hakmiliktemp.setNoPu(noPu);
                        }
                        LOG.debug("---infoAudit--:" + infoAudit);
                        hakmiliktemp.setInfoAudit(infoAudit);
                        strService.simpanHakmilik(hakmiliktemp);
                    }

                }
            }
        }

        addSimpleMessage("Kemaskini Data Berjaya");
//        return new JSP("strata/pbbm/paparan_maklumat_hakmilik_permohonan_terperinci_strata_reg.jsp").addParameter("tab", "true");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showFormHm");
    }

    public Resolution simpanHakmilikStrata() {
        LOG.debug("---simpan hakmilik---:");
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
            LOG.debug("---hakmilik-not null--:");
            if (noPelan1 != null) {
                LOG.debug("---noPelan--:" + noPelan1);
                hakmilik.setNoPelan(noPelan1);
            }
            LOG.debug("---infoAudit--:" + infoAudit);
            hakmilik.setInfoAudit(infoAudit);
            strService.updateHakmilik(hakmilik);
        }
        addSimpleMessage("Kemaskini Data Berjaya");
        return new RedirectResolution(StrataMaklumatHakmilikPermohonanActionBean.class, "showForm2");
    }

    public Resolution showHakmilikStrataReg() {
        LOG.debug("---show Hakmilik Strata---:");
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
        LOG.debug("---show Hakmilik Strata--KATG BNGN---:" + katg_bngn);
        LOG.debug("---show Hakmilik Strata--noBangunan---:" + noBangunan);
        LOG.debug("---show Hakmilik Strata--noTingkat---:" + noTingkat);
        LOG.debug("---show Hakmilik Strata--noPetak---:" + noPetak);
        LOG.debug("---show Hakmilik Strata--noPelan---:" + noPelan1);
        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.debug("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.debug("::get jeniskodhakmilik PTD :::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
        }
        return new JSP("strata/pbbm/perincian_hakmilik_strata_reg.jsp").addParameter("tab", "true");
    }

    public Resolution showHakmilikStrataReg2() {
        LOG.debug("---show Hakmilik Strata---:");
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
        LOG.debug("---show Hakmilik Strata--KATG BNGN---:" + katg_bngn);
        LOG.debug("---show Hakmilik Strata--noBangunan---:" + noBangunan);
        LOG.debug("---show Hakmilik Strata--noTingkat---:" + noTingkat);
        LOG.debug("---show Hakmilik Strata--noPetak---:" + noPetak);
        LOG.debug("---show Hakmilik Strata--noPelan---:" + noPelan1);
        //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            LOG.debug("::get jeniskodhakmilik PTG:::");
            senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
        } else {
            LOG.debug("::get jeniskodhakmilik PTD :::");
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
}
