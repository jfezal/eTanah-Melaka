/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.maklumat_tanah;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodItemPermit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.Pihak;
import etanah.model.Pemohon;
import etanah.model.PermohonanKelompok;
import etanah.model.TanahRizabPermohonan;
import etanah.service.BPelService;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.view.ListUtil;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.PelupusanUtiliti;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanLaporanPelan;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPermitItem;
import etanah.view.stripes.pelupusan.disClass.DisTanahRizabPermohonan;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import etanah.service.common.HakmilikService;
import java.math.BigInteger;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Shazwan
 * @version 2.0
 */
@UrlBinding("/pelupusan/maklumat_tanahV3")
public class MaklumatTanahV2ActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatTanahV2ActionBean.class);
    private static String kodDaerahStatic;
    private Pengguna pengguna;
    private String stageId;
    private String negeri;
    private String kodDaerah;
    private String kodCawangan;
    private String noLitho;
    private String kodBpm;
    private String katTanahPilihan;
    private String type;
    private String idMH;
    private String statusPage;
    private String noLot;
    private String noHM;
    private String idHakmilikCari;
    private String idHakmilik;
    private String kodBPM;
    private String seksyen;
    private String tanahR;
    private boolean forSeksyen;
    private boolean forBPM;
    private int sizeKod;
    private DisPermohonanLaporanPelan disPermohonanLaporanPelan;
    private DisPermohonanPermitItem disPermohonanPermitItem;
    private DisHakmilikPermohonan disHakmilikPermohonan;
    private DisTanahRizabPermohonan disTanahRizabPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private Pihak pihak;
    private Pemohon pemohon;
    private TanahRizabPermohonan mohonTrizab;
    private Permohonan permohonan;
    private Permohonan permohonanKelompok;
    private PermohonanPermitItem ppi;
    private LaporanTanah laporanTanah;
    private List<KodBandarPekanMukim> listKodBPM;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private List<KodItemPermit> senaraiKodItemPermit;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> mohonHakmilikKelompok = new ArrayList<HakmilikPermohonan>();

    ;
    private List<KodBandarPekanMukim> senaraiKodBPMLMCRGPJLB;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs;
    private List<KodSeksyen> senaraiKodSeksyen;
    private List<KodSeksyen> listKodSeksyen;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<Hakmilik> listHakmilik;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentingan;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    ListUtil listUtil;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    RegService regService;
    @Inject
    HakmilikService hakmilikService;
    private List<Pemohon> listPemohon;
    private List<PermohonanKelompok> senaraiKelompok;
    private static String[] LEPAS_HAKMILIK_BATAL = {
        "RPBNB",
        "PRPMB",
        "PMHHB"
    };

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewForm() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);

        return new JSP(DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP(DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm4() {
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/" + DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution refreshpage2() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/" + DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution senaraiKodGunaTanahByKatTanah() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan/kemasukanEdit/partial_kodgunatanah.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodSeksyen() {
        String kodBpm = (String) getContext().getRequest().getParameter("kodBPM");
        if (StringUtils.isNotBlank(kodBpm)) {
            listKodSeksyen = disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(kodBpm);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan/kemasukanEdit/partial_kodseksyen.jsp").addParameter("popup", "true");
    }

    public Resolution doCheckHakmilik() {

        logger.debug("*****RequestValidateIdHakmilik.doCheckHakmilik:hakmilik :" + idHakmilik);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        }
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;
        if (daerah != null) {
            kodDaerah = daerah.getKod();
        }
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            kodDaerah = null;
        }

        String results = "0";
        Hakmilik h = new Hakmilik();

        h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah);

        String sk = "";

        if (h != null) {
            if (h.getSekatanKepentingan() != null) {
                sk = h.getSekatanKepentingan().getKod();
            }
            System.out.println("sk : ====" + sk);
            // check status Daftar
            String st = h.getKodStatusHakmilik().getKod();
            if ("D".equals(st)) { // daftar
                results = "1";
                // check if tax paid
                Akaun ac = h.getAkaunCukai();
                KodSekatanKepentingan sekatan = h.getSekatanKepentingan();

                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
                    results = "2";
                } else {
                    results = "1";
                }
            } else if ("P".equals(st)) {
                results = "3";
            } else if ("B".equals(st)) {
                results = "4";
            }

            logger.debug(results);
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution carianIndependent() {
        noLitho = getContext().getRequest().getParameter("noLitho");
        katTanahPilihan = getContext().getRequest().getParameter("katTanahPilihan");
        kodBpm = getContext().getRequest().getParameter("mpb");
        type = getContext().getRequest().getParameter("type");
        idMH = getContext().getRequest().getParameter("idKandungan");
        String daerahDIS = (String) getContext().getRequest().getSession().getAttribute("daerahDIS");

        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (StringUtils.isEmpty(daerahDIS)) {
                getContext().getRequest().getSession().setAttribute("daerahDIS", this.getKodDaerah());
            }
        }
        String tempat = getContext().getRequest().getParameter("tempat");
        String Luas = getContext().getRequest().getParameter("Luas");
        String kULuas = getContext().getRequest().getParameter("kULuas");
        String Luas2 = getContext().getRequest().getParameter("Luas2");
        String kULuas2 = getContext().getRequest().getParameter("kULuas2");
        String kodlot = getContext().getRequest().getParameter("kodlot");
        String noLot = getContext().getRequest().getParameter("noLot");
        String Jarak = getContext().getRequest().getParameter("Jarak");
        String uJarak = getContext().getRequest().getParameter("uJarak");
        String jarakDari = getContext().getRequest().getParameter("jarakDari");
        String kodSeksyen = getContext().getRequest().getParameter("kodSeksyen");
        String kodGunaTanah = getContext().getRequest().getParameter("kodGunaTanah");
        if (StringUtils.isNotBlank(idMH)) {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
        } else {
            disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
            disHakmilikPermohonan.setKeluasanUOM(null);
            disHakmilikPermohonan.setKedalamanTanahUOM(null);
        }

        if (kodBpm != null && !kodBpm.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(Integer.valueOf(kodBpm)));
            int test = Integer.parseInt(kodBpm);
            KodBandarPekanMukim kodB = kodBandarPekanMukimDAO.findById(test);
            checkSeksyen(kodB);
        }
        if (tempat != null && !tempat.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setLokasi(tempat);
        }
        if (Luas != null && !Luas.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setLuasTerlibat(new BigDecimal(Luas));
        }
        if (kULuas != null && !kULuas.isEmpty()) {
            disHakmilikPermohonan.setKeluasanUOM(kULuas);
            disHakmilikPermohonan.getHakmilikPermohonan().setKodUnitLuas(disLaporanTanahService.getKodUOMDAO().findById(kULuas));
        }
        if (Luas2 != null && !Luas2.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setKedalamanTanah(new BigDecimal(Luas2));
        }
        if (kULuas2 != null && !kULuas2.isEmpty()) {
            disHakmilikPermohonan.setKedalamanTanahUOM(kULuas2);
            disHakmilikPermohonan.getHakmilikPermohonan().setKedalamanTanahUOM(disLaporanTanahService.getKodUOMDAO().findById(kULuas2));
        }
        if (kodlot != null && !kodlot.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setLot(kodLotDAO.findById(kodlot));
        }
        if (noLot != null && !noLot.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(noLot);
        }
        if (Jarak != null && !Jarak.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setJarak(Jarak);
        }
        if (uJarak != null && !uJarak.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setUnitJarak(disLaporanTanahService.getKodUOMDAO().findById(uJarak));
        }
        if (jarakDari != null && !jarakDari.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setJarakDari(jarakDari);
        }
        if (katTanahPilihan != null && !katTanahPilihan.isEmpty()) {
            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKategoriTanahDAO.findById(katTanahPilihan));
        }
        if (kodSeksyen != null && !kodSeksyen.equals("")) {
            disHakmilikPermohonan.getHakmilikPermohonan().setKodSeksyen(disLaporanTanahService.getPelupusanService().findByKodSeksyen(Integer.parseInt(kodSeksyen)));
        }

        if (kodGunaTanah != null && !kodGunaTanah.isEmpty()) {
            KodKegunaanTanah kgt = new KodKegunaanTanah();
            kgt = (KodKegunaanTanah) disLaporanTanahService.findObject(kgt, new String[]{kodGunaTanah}, 0);
            if (kgt != null) {
                disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(kgt);
            }
        }

//        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain.jsp").addParameter("popup", "true");
    }

    public void checkSeksyen(KodBandarPekanMukim kod) {
        try {
            String query = "Select u from etanah.model.KodSeksyen u where u.kodBandarPekanMukim.kod = :kod ";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setInteger("kod", kod.getKod());
            if (q.list().size() > 0) {
                this.setForSeksyen(Boolean.TRUE);
                this.setForBPM(Boolean.TRUE);
            } else {
                forSeksyen = false;
                forBPM = true;
                disHakmilikPermohonan.getHakmilikPermohonan().setKodSeksyen(null);
            }

        } finally {
        }
    }

    public void checkBPM(String kod) {
        try {
            String query = "Select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kod ";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod);
            if (q.list().size() > 0) {
                forBPM = true;
                forSeksyen = false;
            } else {
                forBPM = false;
                forSeksyen = false;
                disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(null);
            }

        } finally {
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan", "!getCaseDefaultSave", "!deleteRow"})
    public void rehydrate() {
        logger.debug("______________________________ rehydrate() ______________________________");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pengguna);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        negeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        }


        hakmilikPihakBerkepentingan = pelupusanService.findPemilikbyIdHakmilik(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("BMBT")) {

                HakmilikPermohonan hp = pelupusanService.findHakmilikPermohonan(idPermohonan);
                hp.setBandarPekanMukimBaru(hp.getHakmilik().getBandarPekanMukim());
                hp.setNoLot(hp.getHakmilik().getNoLot().replaceAll("^0*", ""));
                hp.setSyaratNyata(hp.getHakmilik().getSyaratNyata());
                hp.setSyaratNyataBaru(hp.getHakmilik().getSyaratNyata());
                hp.setSekatanKepentingan(hp.getHakmilik().getSekatanKepentingan());
                hp.setSekatanKepentinganBaru(hp.getHakmilik().getSekatanKepentingan());
                hp.setKategoriTanahBaru(hp.getHakmilik().getKategoriTanah());
                hp.setJenisPenggunaanTanah(hp.getHakmilik().getKategoriTanah());
                if (hp.getHakmilik().getLot() != null) {
                    hp.setLot(hp.getHakmilik().getLot());
                }

                Pemohon senaraipemohon = pelupusanService.findPemohon(idPermohonan);
                if (senaraipemohon == null) {
                    if (!hakmilikPihakBerkepentingan.isEmpty()) {
                        for (int i = 0; i < hakmilikPihakBerkepentingan.size(); i++) {
//                            Pihak ph = hakmilikPihakBerkepentingan.get(i).getPihak(); //if pemilik more than 1
                            Pihak ph = hakmilikPihakBerkepentingan.get(0).getPihak(); // set pemilik 1

                            pemohon = new Pemohon();
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            pemohon.setPermohonan(permohonan);
                            pemohon.setPihak(ph);
                            pemohon.setTanggungan(0);
                            pemohon.setUmur(0);
                            pemohon.setInfoAudit(info);
                            pemohon.setCawangan(permohonan.getCawangan());
                            pemohonService.saveOrUpdate(pemohon);
                        }
                    }
                }
            }
        }

        /*
         * SENARAI PEMOHON BERKELOMPOK
         */
        listPemohon = permohonan.getSenaraiPemohon();
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);

        mohonHakmilikKelompok = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanList(idPermohonan);
        logger.info("mohonHakmilikKelompok : " + mohonHakmilikKelompok.size());

        int negeriInt = negeri.equals("04") ? 1
                : negeri.equals("05") ? 2
                : 0;
        /**
         * PURPOSE : TO DEFINE SPECIFIC URUSAN CHANGES ONLY, DEFAULT DIFFERENT
         * CASE*
         */
        int typeNum = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
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
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 22
                : permohonan.getKodUrusan().getKod().equals("RLPSK") ? 23
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 24
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 25
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 26
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 27
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 28
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 29
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 30
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 31
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 32
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 33
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 31
                : 0;

        /**
         * PURPOSE : ALL DEFAULT ARE CATER HERE BASED ON STATE*
         */
        getCaseDefault(negeriInt);

        switch (typeNum) {
            case 1:
                //PPBB , PBPTD , PBPTG
                getCaseBatuan(negeriInt, "rehydrate");
                break;
//            case 2:
//                //PBPTD
//                break;
//            case 3:
//                //PBPTG
//                break;
            case 4:
                //LMCRG
                getCaseLMCRG(negeriInt, "rehydrate");
                break;
            case 5:
                //PJLB
                getCasePJLB(negeriInt, "rehydrate");
                break;
            case 6:
                //LPSP
                getCaseLPSP(negeriInt, "rehydrate");
                break;
            case 7:
                //PLPS
                getCasePLPS(negeriInt, "rehydrate");
                break;
            case 8:
                //PPRU
                getCasePPRU(negeriInt, "rehydrate");
                break;
            case 9:
                //PPTR
                getCasePPTR(negeriInt, "rehydrate");
                break;
            case 10:
                //PTGSA
                getCasePTGSA(negeriInt, "rehydrate");
                break;
            case 11:
                //PRMP
                getCasePRMP(negeriInt, "rehydrate");
                break;
            case 12:
                //PBMT
                getCasePBMT(negeriInt, "rehydrate");
                break;
            case 13:
                //MCMCL
                break;
            case 14:
                //MMMCL
                getCaseMMMCL(negeriInt, "rehydrate");
                break;
            case 15:
                //PRIZ
                getCasePRIZ(negeriInt, "rehydrate");
                break;
            case 16:
                //PHLA
                break;
            case 17:
                //PBRZ
                break;
            case 18:
                //PBHL
                break;
            case 19:
                //BMBT
                getCasePBMT(negeriInt, "rehydrate");
                break;
            case 20:
                //PJBTR
                break;
            case 21:
                //PLPTD
                getCasePLPTD(negeriInt, "rehydrate");
                break;
            case 22:
                //RLPS
                getCaseRLPS(negeriInt, "rehydrate");
                break;
            case 23:
                //RLPSK
                getCaseRLPSK(negeriInt, "rehydrate");
                break;
            case 24:
                //PJTK
                getCasePJTK(negeriInt, "rehydrate");
                break;
            case 25:
                //PWGSA
                getCasePWGSA(negeriInt, "rehydrate");
                break;
            case 26:
                //PBMT
                getCasePBMMK(negeriInt, "rehydrate");
                break;
            case 27:
                //PTPBP
                getCasePTPBP(negeriInt, "rehydrate");
                break;
            case 28:
                //PRMMK
                getCasePRMMK(negeriInt, "rehydrate");
                break;
            case 29:
                //PBGSA
                getCasePBGSA(negeriInt, "rehydrate");
                break;
            case 30:
                //WMRE
                getCasePBMMK(negeriInt, "rehydrate");
                break;
            case 31:
                //PCRG
                getCasePJTK(negeriInt, "rehydrate");
                break;
            case 32:
                //PTBTC
                getCasePBMT(negeriInt, "rehydrate");
                break;
            case 33:
                //PTBTS
                getCasePBMT(negeriInt, "rehydrate");
                break;
        }
        /**
         * END*
         */
    }

    public Resolution showFormPopUp() {

        idMH = getContext().getRequest().getParameter("idKandungan");
        if (idMH != null) {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(idMH));
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                disHakmilikPermohonan.setJarakDariNama(!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) : new String());
                if (disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas() != null) {
                    disHakmilikPermohonan.setKeluasanUOM(disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas().getKod());
                }
                if (!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                    disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                }
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
                disHakmilikPermohonan.setKodBpm(String.valueOf(disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getKod()));
                forBPM = true;
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru() != null) {
                disHakmilikPermohonan.setKatTanahPilihan(disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru().getKod());
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKodSeksyen() != null) {
                forSeksyen = true;
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
                kodBpm = disHakmilikPermohonan.getKodBpm();
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru() != null) {
                katTanahPilihan = disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru().getKod();
            }
            if (katTanahPilihan != null) {
                listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(katTanahPilihan);
            }
            if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                logger.debug(hakmilikPermohonan.getKodKegunaanTanah().getKod());
            }
        } else {
            disHakmilikPermohonan = new DisHakmilikPermohonan();
            disHakmilikPermohonan.setHakmilikPermohonan(null);
            disHakmilikPermohonan.setKeluasanUOM(null);
            forSeksyen = false;
            kodBpm = new String();
            katTanahPilihan = new String();
            idMH = new String();
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("save", Boolean.FALSE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain.jsp").addParameter("popup", "true");
    }

    public Resolution viewFormPopUp() {

        idMH = getContext().getRequest().getParameter("idKandungan");
        if (idMH != null) {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                disHakmilikPermohonan.setJarakDariNama(!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) : new String());
                if (disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas() != null) {
                    disHakmilikPermohonan.setKeluasanUOM(disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas().getKod());
                }
                if (!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                    disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                }
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
                disHakmilikPermohonan.setKodBpm(String.valueOf(disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getKod()));
                forBPM = true;
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru() != null) {
                disHakmilikPermohonan.setKatTanahPilihan(disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru().getKod());
            }
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKodSeksyen() != null) {
                forSeksyen = true;
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopUp1() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        }
        return new JSP("pelupusan/kemasukan/kemasukanEdit/kemasukanTujuan.jsp").addParameter("popup", "true");
    }

    public Resolution showFormPopUpForHakmilik() {

        idMH = getContext().getRequest().getParameter("idKandungan");
        disPermohonanPermitItem = new DisPermohonanPermitItem();
        if (idMH != null) {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(idMH));
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                disHakmilikPermohonan.setJarakDariNama(!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) : new String());
                if (disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas() != null) {
                    disHakmilikPermohonan.setKeluasanUOM(disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas().getKod());
                }
                if (!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                    disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                }
            }
            disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(disHakmilikPermohonan.getHakmilikPermohonan().getPermohonan().getIdPermohonan()));
            if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
            }
        }
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(idMH));
        idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("save", Boolean.FALSE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain2.jsp").addParameter("popup", "true");
    }

    public Resolution viewFormPopUpForHakmilik() {

        idMH = getContext().getRequest().getParameter("idKandungan");
        disPermohonanPermitItem = new DisPermohonanPermitItem();
        if (idMH != null) {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
            if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                disHakmilikPermohonan.setJarakDariNama(!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) : new String());
                if (disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas() != null) {
                    disHakmilikPermohonan.setKeluasanUOM(disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas().getKod());
                }
                if (!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                    disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                }
                disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(disHakmilikPermohonan.getHakmilikPermohonan().getPermohonan().getIdPermohonan()));
                if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                    disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                }
            }

        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain2.jsp").addParameter("popup", "true");
    }

    public Resolution simpanTujuan() throws ParseException {
        logger.debug("______________________________ entering simpanTujuan() ______________________________");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        String sebab = getContext().getRequest().getParameter("permohonan.sebab");
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan != null) {
                ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                    permohonan.setCatatan(catatan);
                    permohonan.setSebab(sebab);
                    disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
                } else {
                    permohonan.setSebab(sebab);
                    disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/kemasukan/kemasukanEdit/kemasukanTujuan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("______________________________ entering simpan() ______________________________");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String luasTerlibat = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
        String kedalamanTanah = getContext().getRequest().getParameter("hakmilikPermohonan.kedalamanTanah");
        String kCatatan = getContext().getRequest().getParameter("hakmilikPermohonan.catatan");
        disPermohonanPermitItem = new DisPermohonanPermitItem();
        String kod2 = getContext().getRequest().getParameter("kodUnitLuas");
        type = getContext().getRequest().getParameter("type");
//        String kod2 = keluasanUOM;

        negeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        }
        int negeriInt = negeri.equals("04") ? 1
                : negeri.equals("05") ? 2
                : 0;
        /**
         * PURPOSE : TO DEFINE SPECIFIC URUSAN CHANGES ONLY, DEFAULT DIFFERENT
         * CASE*
         */
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
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 22
                : permohonan.getKodUrusan().getKod().equals("RLPSK") ? 23
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 24
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 25
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 26
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 27
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 28
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 29
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 30
                : permohonan.getKodUrusan().getKod().equals("BMRE") ? 31
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 32
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 33
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 34
                : permohonan.getKodUrusan().getKod().equals("LSTP") ? 35
                : 0;

        /**
         * PURPOSE : ALL DEFAULT ARE CATER HERE BASED ON STATE*
         */
        getCaseDefault(negeriInt);

        switch (typeNum) {
            case 1:
                //PPBB
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 2:
                //PBPTD
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 3:
                //PBPTG
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 4:
                //LMCRG
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 5:
                //PJLB
                if (type.equals("save")) {
                    getCasePJLB(negeriInt, "save");
                } else {
                    getCasePJLB(negeriInt, "update");
                }
                break;
            case 6:
                //LPSP
                if (type.equals("save")) {
                    getCaseLPSP(negeriInt, "save");
                } else {
                    getCaseLPSP(negeriInt, "update");
                }
                break;
            case 7:
                //PLPS
                if (type.equals("save")) {
                    getCasePLPS(negeriInt, "save");
                } else {
                    getCasePLPS(negeriInt, "update");
                }
                break;
            case 8:
                //PPRU
                if (type.equals("save")) {
                    getCasePPRU(negeriInt, "save");
                } else {
                    getCasePPRU(negeriInt, "update");
                }
                break;
            case 9:
                //PPTR
                if (type.equals("save")) {
                    getCasePPTR(negeriInt, "save");
                } else {
                    getCasePPTR(negeriInt, "update");
                }
                break;
            case 10:
                //PTGSA
                if (type.equals("save")) {
                    getCasePTGSA(negeriInt, "save");
                } else {
                    getCasePTGSA(negeriInt, "update");
                }
                break;
            case 11:
                //PRMP
                getCasePRMP(negeriInt, "save");
                break;
            case 12:
                //PBMT
                if (type.equals("save")) {
                    getCasePBMT(negeriInt, "save");
                } else {
                    getCasePBMT(negeriInt, "update");
                }

                break;
            case 13:
                //MCMCL
                break;
            case 14:
                //MMMCL
                getCaseMMMCL(negeriInt, "save");
                break;
            case 15:
                //PRIZ
                if (type.equals("save")) {
                    getCasePRIZ(negeriInt, "save");
                } else {
                    getCasePRIZ(negeriInt, "update");
                }
                break;
            case 16:
                //PHLA
                break;
            case 17:
                //PBRZ
                if (type.equals("save")) {
                    getCasePRIZ(negeriInt, "save");
                } else {
                    getCasePRIZ(negeriInt, "update");
                }
                break;
            case 18:
                //PBHL
                break;
            case 19:
                //BMBT
                if (type.equals("save")) {
                    getCasePBMT(negeriInt, "save");
                } else {
                    getCasePBMT(negeriInt, "update");
                }
                break;
            case 20:
                //PJBTR
                break;
            case 21:
                //PLPTD
                getCasePLPTD(negeriInt, "save");
                break;
            case 22:
                //RLPS
                getCaseRLPS(negeriInt, "save");
                break;
            case 23:
                //RLPSK
                getCaseRLPSK(negeriInt, "save");
                break;
            case 24:
                //PTBTS
                if (type.equals("save")) {
                    getCasePBMT(negeriInt, "save");
                } else {
                    getCasePBMT(negeriInt, "update");
                }
            case 25:
                //PJTK
                if (type.equals("save")) {
                    getCasePJTK(negeriInt, "save");
                } else {
                    getCasePJTK(negeriInt, "update");
                }
                break;
            case 26:
                //PWGSA
                if (type.equals("save")) {
                    getCasePWGSA(negeriInt, "save");
                } else {
                    getCasePWGSA(negeriInt, "update");
                }
                break;
            case 27:
                //PBMMK
                if (type.equals("save")) {
                    getCasePBMMK(negeriInt, "save");
                } else {
                    getCasePBMMK(negeriInt, "update");
                }
                break;
            case 28:
                //PRMMK
                if (type.equals("save")) {
                    getCasePRMMK(negeriInt, "save");
                } else {
                    getCasePRMMK(negeriInt, "update");
                }
                break;
            case 29:
                //PBGSA
                if (type.equals("save")) {
                    getCasePBGSA(negeriInt, "save");
                } else {
                    getCasePBGSA(negeriInt, "update");
                }
                break;
            case 30:
                //WMRE
                if (type.equals("save")) {
                    getCasePBMMK(negeriInt, "save");
                } else {
                    getCasePBMMK(negeriInt, "update");
                }
                break;
            case 31:
                //BMRE
                if (type.equals("save")) {
                    getCasePRIZ(negeriInt, "save");
                } else {
                    getCasePRIZ(negeriInt, "update");
                }
                break;
            case 32:
                //PCRG
                if (type.equals("save")) {
                    getCasePJTK(negeriInt, "save");
                } else {
                    getCasePJTK(negeriInt, "update");
                }
                break;
            case 33:
                //PTBTC
                if (type.equals("save")) {
                    getCasePBMT(negeriInt, "save");
                } else {
                    getCasePBMT(negeriInt, "update");
                }
                break;
            case 34:
                //LPJH
                if (type.equals("save")) {
                    getCasePJTK(negeriInt, "save");
                } else {
                    getCasePJTK(negeriInt, "update");
                }
                break;
            case 35:
                //LSTP
                if (type.equals("save")) {
                    getCasePJLB(negeriInt, "save");
                } else {
                    getCasePJLB(negeriInt, "update");
                }
                break;
        }
        /**
         * END*
         */
//            if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
//                TanahRizabPermohonan mohonTrizabTemp = new TanahRizabPermohonan();
//                mohonTrizabTemp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
//                ia = new InfoAudit();
//                if (mohonTrizabTemp != null) {
//                    ia = mohonTrizabTemp.getInfoAudit();
//                    ia.setDiKemaskiniOleh(peng);
//                    ia.setTarikhKemaskini(new Date());
//                } else {
//                    ia = new InfoAudit();
//                    ia.setDimasukOleh(peng);
//                    ia.setTarikhMasuk(new Date());
//                    mohonTrizabTemp = new TanahRizabPermohonan();
//                    mohonTrizabTemp.setInfoAudit(ia);
//                    mohonTrizabTemp.setPermohonan(permohonan);
//                    mohonTrizabTemp.setCawangan(permohonan.getCawangan());
//                    mohonTrizabTemp.setDaerah(permohonan.getCawangan().getDaerah());
//                    mohonTrizabTemp.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
//                    mohonTrizabTemp.setNoLot(hakmilikPermohonan.getNoLot());
//                    mohonTrizabTemp.setAktif('Y');
//                }
//                mohonTrizabTemp.setLuasTerlibat(mohonTrizab.getLuasTerlibat());
//                mohonTrizabTemp.setNoLot(" ");
//
//                if (!StringUtils.isEmpty(tanahR)) {
//                    mohonTrizabTemp.setRizab(kodRizabDAO.findById(Integer.parseInt(tanahR)));
//                    pelupusanService.simpanTanahRizabPermohonan(mohonTrizabTemp);
//                }
//            }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("save", Boolean.TRUE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain.jsp").addParameter("tab", "true");

    }

    public Resolution simpanUntukHakmilik() throws ParseException {
        logger.debug("______________________________ entering simpanUntukHakmilik() ______________________________");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String luasTerlibat = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");

        String kod2 = getContext().getRequest().getParameter("kodUnitLuas");
        type = getContext().getRequest().getParameter("type");
//        String kod2 = keluasanUOM;

        negeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        }
        int negeriInt = negeri.equals("04") ? 1
                : negeri.equals("05") ? 2
                : 0;
        /**
         * PURPOSE : TO DEFINE SPECIFIC URUSAN CHANGES ONLY, DEFAULT DIFFERENT
         * CASE*
         */
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
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 22
                : permohonan.getKodUrusan().getKod().equals("RLPSK") ? 23
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 24
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 25
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 26
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 27
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 28
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 29
                : permohonan.getKodUrusan().getKod().equals("LSTP") ? 30
                : 0;

        /**
         * PURPOSE : ALL DEFAULT ARE CATER HERE BASED ON STATE*
         */
        getCaseDefault(negeriInt);

        switch (typeNum) {
            case 1:
                //PPBB
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 2:
                //PBPTD
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 3:
                //PBPTG
                if (type.equals("save")) {
                    getCaseBatuan(negeriInt, "save");
                } else {
                    getCaseBatuan(negeriInt, "update");
                }
                break;
            case 4:
                //LMCRG
                if (type.equals("save")) {
                    getCaseLMCRG(negeriInt, "save");
                } else {
                    getCaseLMCRG(negeriInt, "update");
                }
                break;
            case 5:
                //PJLB
                break;
            case 6:
                //LPSP
                if (type.equals("save")) {
                    getCaseLPSP(negeriInt, "save");
                } else {
                    getCaseLPSP(negeriInt, "update");
                }
                break;
            case 7:
                //PLPS
                if (type.equals("save")) {
                    getCasePLPS(negeriInt, "save");
                } else {
                    getCasePLPS(negeriInt, "update");
                }
                break;
            case 8:
                //PPRU
                if (type.equals("save")) {
                    getCasePPRU(negeriInt, "save");
                } else {
                    getCasePPRU(negeriInt, "update");
                }
                break;
            case 9:
                //PPTR
                if (type.equals("save")) {
                    getCasePPTR(negeriInt, "save");
                } else {
                    getCasePPTR(negeriInt, "update");
                }
                break;
            case 10:
                //PTGSA
                if (type.equals("save")) {
                    getCasePTGSA(negeriInt, "save");
                } else {
                    getCasePTGSA(negeriInt, "update");
                }
                break;
            case 11:
                //PRMP
                getCasePRMP(negeriInt, "update");
                break;
            case 12:
                //PBMT
                if (type.equals("save")) {
                    getCasePBMT(negeriInt, "save");
                } else {
                    getCasePBMT(negeriInt, "update");
                }

                break;
            case 13:
                //MCMCL
                break;
            case 14:
                //MMMCL
                getCaseMMMCL(negeriInt, "save");
                break;
            case 15:
                //PRIZ
                if (type.equals("save")) {
                    getCasePRIZ(negeriInt, "save");
                } else {
                    getCasePRIZ(negeriInt, "update");
                }
                break;
            case 16:
                //PHLA
                break;
            case 17:
                //PBRZ
                break;
            case 18:
                //PBHL
                break;
            case 19:
                //BMBT
                getCaseDefaultSave(negeriInt);
                break;
            case 20:
                //PJBTR
                break;
            case 21:
                //PLPTD
                getCasePLPTD(negeriInt, "save");
                break;
            case 22:
                //RLPS
                getCaseRLPS(negeriInt, "save");
                break;
            case 23:
                //RLPSK
                getCaseRLPSK(negeriInt, "save");
                break;
            case 24:
                //PTBTS
                getCasePTBTS(negeriInt, "save");
                break;
            case 25:
                //PJTK
                if (type.equals("save")) {
                    getCasePJTK(negeriInt, "save");
                } else {
                    getCasePJTK(negeriInt, "update");
                }
                break;
            case 26:
                //PWGSA
                if (type.equals("save")) {
                    getCasePWGSA(negeriInt, "save");
                } else {
                    getCasePWGSA(negeriInt, "update");
                }
                break;
            case 27:
                //PTPBP
                if (type.equals("save")) {
                    getCasePTPBP(negeriInt, "save");
                } else {
                    getCasePTPBP(negeriInt, "update");
                }
                break;
            case 28:
                //PRMMK
                if (type.equals("save")) {
                    getCasePTMTA(negeriInt, "save");
                } else {
                    getCasePTMTA(negeriInt, "update");
                }
                break;
            case 29:
                //PBGSA
                if (type.equals("save")) {
                    getCasePBGSA(negeriInt, "save");
                } else {
                    getCasePBGSA(negeriInt, "update");
                }
                break;
            case 30:
                //PRIZ
                if (type.equals("save")) {
                    getCaseLSTP(negeriInt, "save");
                } else {
                    getCaseLSTP(negeriInt, "update");
                }
                break;
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("save", Boolean.TRUE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2EditMain2.jsp").addParameter("tab", "true");

    }

    public void getCaseDefault(int numnegeri) {
        disHakmilikPermohonan = new DisHakmilikPermohonan();
        if (StringUtils.isEmpty(idMH)) {
            if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
                disHakmilikPermohonan.setHakmilikPermohonan(disLaporanTanahService.getPelupusanService().findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan()));
            } else {
                if (permohonan.getSenaraiHakmilik().size() > 0) {
                    disHakmilikPermohonan.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));
                }
            }
        } else {
            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
        }
        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
            disHakmilikPermohonan.setJarakDariNama(!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(disHakmilikPermohonan.getHakmilikPermohonan().getJarakDariNama()) : new String());
            if (disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas() != null) {
                disHakmilikPermohonan.setKeluasanUOM(disHakmilikPermohonan.getHakmilikPermohonan().getKodUnitLuas().getKod());
            }
            if (!StringUtils.isBlank(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
            }
        }
        if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
            disHakmilikPermohonan.setKodBpm(String.valueOf(disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getKod()));
            forBPM = true;
        }
        if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru() != null) {
            disHakmilikPermohonan.setKatTanahPilihan(disHakmilikPermohonan.getHakmilikPermohonan().getKategoriTanahBaru().getKod());
//            senaraiKodKegunaanTanahs = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(disHakmilikPermohonan.getKatTanahPilihan());
            katTanahPilihan = disHakmilikPermohonan.getKatTanahPilihan();
        }
        if (disHakmilikPermohonan.getHakmilikPermohonan() != null && disHakmilikPermohonan.getHakmilikPermohonan().getKodSeksyen() != null) {
            forSeksyen = true;
            kodBpm = disHakmilikPermohonan.getKodBpm();
//            senaraiKodSeksyen = disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(String.valueOf(disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getKod()));
        }

        if (disHakmilikPermohonan.getHakmilikPermohonan() == null) {
            System.out.println("Hakmilik Tiada");
            getContext().getRequest().setAttribute("hakmilik", Boolean.FALSE);
        } else {
            System.out.println("Hakmilik Ada");
            getContext().getRequest().setAttribute("hakmilik", Boolean.TRUE);
        }

        if (permohonan.getKodUrusan().getKod().equals("MMMCL")) {
            hakmilikPermohonanList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            hakmilikPermohonanList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
            //}else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            //    hakmilikPermohonanList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        } else {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }

        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
            String kodDaerahSession = (String) getContext().getRequest().getSession().getAttribute("daerahDIS");
            if (StringUtils.isEmpty(kodDaerah) && StringUtils.isEmpty(kodDaerahSession)) {
                if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {

                    if (permohonan.getKodUrusan().getKod().equals("LMCRG") && numnegeri == 1) {
                        kodDaerah = "00";
                        kodCawangan = "00";
                    } else if ((permohonan.getKodUrusan().getKod().equals("PJLB")) || (permohonan.getKodUrusan().getKod().equals("LMCRG") && numnegeri == 2)) {
                        kodDaerah = disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null ? disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getDaerah().getKod() : new String();
                        kodCawangan = disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getCawangan() != null ? disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru().getCawangan().getKod() : new String();
                    }

                    if (numnegeri == 2 && kodDaerah.equals("08")) { //Hardcode utk Gemas
                        kodDaerah = "06";
                    }
                } else {
                    kodDaerah = new String();
                    kodCawangan = new String();
                }
            } else {
                kodDaerah = kodDaerahSession;
                kodCawangan = permohonan.getCawangan().getKod();
                if (numnegeri == 2 && kodDaerah.equals("08")) { //Hardcode utk Gemas
                    kodDaerah = "06";
                }
            }
        } else {
            kodDaerah = permohonan.getCawangan().getDaerah().getKod();
            kodCawangan = permohonan.getCawangan().getKod();
            if (numnegeri == 2 && kodDaerah.equals("08")) { //Hardcode utk Gemas
                kodDaerah = "06";
            }
        }

        if (kodCawangan.equals("00")) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
            if (permohonan.getKodUrusan().getKod().equals("PCRG") || permohonan.getKodUrusan().getKod().equals("LPJH")) {
                senaraiKodBPM = listUtil.getSenaraiKodBandarMukim();
            }
        } else {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerahAndCawangan(kodDaerah, kodCawangan);
        }

        senaraiKodItemPermit = disLaporanTanahService.getPelupusanService().getSenaraiKodItemPermit();

        disTanahRizabPermohonan = new DisTanahRizabPermohonan();
        disTanahRizabPermohonan.setMohonTrizab(disLaporanTanahService.getPelupusanService().findTanahRizabByIdPermohonan(permohonan.getIdPermohonan()));
        if (disTanahRizabPermohonan.getMohonTrizab() != null) {
            if (disTanahRizabPermohonan.getMohonTrizab().getRizab() != null) {
                disTanahRizabPermohonan.setTanahR(String.valueOf(disTanahRizabPermohonan.getMohonTrizab().getRizab().getKod()));
            }
        }

        /**
         * PURPOSE: IF METHOD DIFF BY STATE*
         */
        switch (numnegeri) {
            case 1:
                //MELAKA

                break;
            case 2:
                //NEGERI SEMBILAN
//                disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
//                disPermohonanLaporanPelan.setPermohonanLaporanPelan(laporanPelukisPelanService.findByidP(permohonan.getIdPermohonan()));
//                if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
//                    disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
//                }
                break;
        }

    }

    public void getCaseDefaultSave(int negeri) {
        /*
         * IF USED BY TWO STATES AND IGNORED URUSAN
         */
        KodKegunaanTanah kegunaanTanah = new KodKegunaanTanah();
        String gunaTanahInput = getContext().getRequest().getParameter("kodGunaTanah");

        if (StringUtils.isNotBlank(gunaTanahInput)) {
            kegunaanTanah = (KodKegunaanTanah) disLaporanTanahService.findObject(kegunaanTanah, new String[]{gunaTanahInput}, 0);
            if (kegunaanTanah != null) {
                disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(kegunaanTanah);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
            String kCatatan = getContext().getRequest().getParameter("hakmilikPermohonan.catatan");
            if (StringUtils.isNotBlank(kCatatan)) {
                disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(kCatatan);
                logger.info(kCatatan);
            } else {
                disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(null);
            }

            KodUOM kodKT = new KodUOM();
            String keluasanInput2 = getContext().getRequest().getParameter("disHakmilikPermohonan.kedalamanTanahUOM");
            if (StringUtils.isNotBlank(keluasanInput2)) {
                kodKT = (KodUOM) disLaporanTanahService.findObject(kodKT, new String[]{keluasanInput2}, 0);
                disHakmilikPermohonan.getHakmilikPermohonan().setKedalamanTanahUOM(kodKT);
                logger.info(kodKT);
            }

            String kedalamanTanah = getContext().getRequest().getParameter("hakmilikPermohonan.kedalamanTanah");
            if (StringUtils.isNotBlank(kedalamanTanah)) {
                BigDecimal kedalamanTanah1 = BigDecimal.valueOf(Double.parseDouble(kedalamanTanah));
                disHakmilikPermohonan.getHakmilikPermohonan().setKedalamanTanah(kedalamanTanah1);
                logger.info(kedalamanTanah1);
            }
        }

        KodSeksyen kodSeksyen = new KodSeksyen();
        String seksyenInput = getContext().getRequest().getParameter("seksyen");
        if (StringUtils.isNotBlank(seksyenInput)) {
            kodSeksyen = (KodSeksyen) disLaporanTanahService.findObject(kodSeksyen, new String[]{seksyenInput}, 0);
            disHakmilikPermohonan.getHakmilikPermohonan().setKodSeksyen(kodSeksyen);
        } else {
            disHakmilikPermohonan.getHakmilikPermohonan().setKodSeksyen(null);
        }

        //if got other object
        KodBandarPekanMukim kodBPM = new KodBandarPekanMukim();
        String bpmInput = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
        if (StringUtils.isNotBlank(bpmInput)) {
            kodBPM = (KodBandarPekanMukim) disLaporanTanahService.findObject(kodBPM, new String[]{bpmInput}, 0);
            disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(kodBPM);
        }

        String lokasi = getContext().getRequest().getParameter("hakmilikPermohonan.lokasi");
        if (StringUtils.isNotBlank(lokasi)) {
            disHakmilikPermohonan.getHakmilikPermohonan().setLokasi(lokasi);
        }
        disHakmilikPermohonan.getHakmilikPermohonan().setPermohonan(permohonan);

        KodUOM kodUL = new KodUOM();
        String keluasanInput = getContext().getRequest().getParameter("disHakmilikPermohonan.keluasanUOM");
        if (StringUtils.isNotBlank(keluasanInput)) {
            kodUL = (KodUOM) disLaporanTanahService.findObject(kodUL, new String[]{keluasanInput}, 0);
            disHakmilikPermohonan.getHakmilikPermohonan().setKodUnitLuas(kodUL);
        }

        String luasTerlibat = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
        if (StringUtils.isNotBlank(luasTerlibat)) {
            BigDecimal luas = BigDecimal.valueOf(Double.parseDouble(luasTerlibat));
            disHakmilikPermohonan.getHakmilikPermohonan().setLuasTerlibat(luas);
        }
        /*
         * USE THIS CASE ONLY IF ALL URUSAN USING THE SAME METHOD, IF SPECIFIC PLEASE USED SPECIFIC CASE PROVIDED
         */
        switch (negeri) {
            case 1:
                //MELAKA
                if (!permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    String noLotInput = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
                    String kodLotInput = getContext().getRequest().getParameter("kodLot.kod");

                    if (StringUtils.isNotBlank(noLotInput)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(noLotInput);
                    } else {
                        if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                            logger.info("set No Lot");
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(null);
                        }
                    }
                    if (StringUtils.isNotBlank(kodLotInput)) {
                        KodLot kodLot = new KodLot();
                        kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLotInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setLot(kodLot);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setLot(null);
                    }

                    String jarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                    if (StringUtils.isNotBlank(jarak)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarak(jarak);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarak(null);
                    }

                    String unitJarakInput = getContext().getRequest().getParameter("unitJarak.kod");
                    if (StringUtils.isNotBlank(unitJarakInput)) {
                        KodUOM kodUnitJarakUOM = new KodUOM();
                        kodUnitJarakUOM = (KodUOM) disLaporanTanahService.findObject(kodUnitJarakUOM, new String[]{unitJarakInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setUnitJarak(kodUnitJarakUOM);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setUnitJarak(null);
                    }

                    String jarakDari = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDari");
                    if (StringUtils.isNotBlank(jarakDari)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDari(jarakDari);
                    }

                    String jarakDariKediaman = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDariKediaman");
                    if (StringUtils.isNotBlank(jarakDariKediaman)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediaman(BigDecimal.valueOf(Double.parseDouble(jarakDariKediaman)));
                    }

                    String jarakDariKediamanInput = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (StringUtils.isNotBlank(jarakDariKediamanInput)) {
                        KodUOM kodKediamanUOM = new KodUOM();
                        kodKediamanUOM = (KodUOM) disLaporanTanahService.findObject(kodKediamanUOM, new String[]{jarakDariKediamanInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediamanUom(kodKediamanUOM);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediamanUom(null);
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (!permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    String noLotInput = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
                    String kodLotInput = getContext().getRequest().getParameter("kodLot.kod");

                    if (StringUtils.isNotBlank(noLotInput)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(noLotInput);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(null);
                    }
                    if (StringUtils.isNotBlank(kodLotInput)) {
                        KodLot kodLot = new KodLot();
                        kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLotInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setLot(kodLot);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setLot(null);
                    }

                    String jarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                    if (StringUtils.isNotBlank(jarak)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarak(jarak);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarak(null);
                    }

                    String unitJarakInput = getContext().getRequest().getParameter("unitJarak.kod");
                    if (StringUtils.isNotBlank(unitJarakInput)) {
                        KodUOM kodUnitJarakUOM = new KodUOM();
                        kodUnitJarakUOM = (KodUOM) disLaporanTanahService.findObject(kodUnitJarakUOM, new String[]{unitJarakInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setUnitJarak(kodUnitJarakUOM);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setUnitJarak(null);
                    }

                    String jarakDari = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDari");
                    if (StringUtils.isNotBlank(jarakDari)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDari(jarakDari);
                    }

                    String jarakDariNama = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDariNama");
                    if (StringUtils.isNotBlank(jarakDariNama)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariNama(jarakDariNama);
                    }

                    String jarakDariKediaman = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDariKediaman");
                    if (StringUtils.isNotBlank(jarakDariKediaman)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediaman(BigDecimal.valueOf(Double.parseDouble(jarakDariKediaman)));
                    }

                    String jarakDariKediamanInput = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    if (StringUtils.isNotBlank(jarakDariKediamanInput)) {
                        KodUOM kodKediamanUOM = new KodUOM();
                        kodKediamanUOM = (KodUOM) disLaporanTanahService.findObject(kodKediamanUOM, new String[]{jarakDariKediamanInput}, 0);
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediamanUom(kodKediamanUOM);
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setJarakDariKediamanUom(null);
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("PCRG") || permohonan.getKodUrusan().getKod().equals("LSTP") || permohonan.getKodUrusan().getKod().equals("LPJH")) {
                        String bpmBaruInput = getContext().getRequest().getParameter("hakmilikPermohonan.bandarPekanMukimBaru.kod");
                        String kodCawInput = getContext().getRequest().getParameter("hakmilikPermohonan.cawangan.kod");
                        logger.info("kod caw : " + kodCawInput);

                        if (disHakmilikPermohonan.getHakmilikPermohonan().getBandarPekanMukimBaru() != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("hakmilikPermohonan.bandarPekanMukimBaru.kod"))));
                            disHakmilikPermohonan.getHakmilikPermohonan().setCawangan(kodCawanganDAO.findById(getContext().getRequest().getParameter("hakmilikPermohonan.cawangan.kod")));
                        } else {
                            idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
                            hakmilik = disLaporanTanahService.getHakmilikDAO().findById(idHakmilik);
                            logger.info("id mohon : " + permohonan.getIdPermohonan());
                            logger.info("id hakmilik : " + idHakmilik);
                            disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
                            disHakmilikPermohonan.getHakmilikPermohonan().setCawangan(hakmilik.getCawangan());
                        }

                    }
                }

                if (permohonan.getKodUrusan().getKod().equals("PTPBP") || permohonan.getKodUrusan().getKod().equals("PTMTA") || permohonan.getKodUrusan().getKod().equals("PTGSA") || permohonan.getKodUrusan().getKod().equals("PRMP")) {
                    //set data from hakmilik to mohon_hakmilik
                    if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                        if (disHakmilikPermohonan.getHakmilikPermohonan().getHakmilik() != null) {
                            String id = disHakmilikPermohonan.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                            Hakmilik hm = hakmilikService.findById(id);
                            if (hm != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(StringUtils.isNotBlank(hm.getNoLot()) ? hm.getNoLot() : null);
                                disHakmilikPermohonan.getHakmilikPermohonan().setLot(hm.getLot() != null ? hm.getLot() : null);
                                disHakmilikPermohonan.getHakmilikPermohonan().setBandarPekanMukimBaru(hm.getBandarPekanMukim() != null ? hm.getBandarPekanMukim() : null);
                                disHakmilikPermohonan.getHakmilikPermohonan().setSyaratNyata(hm.getSyaratNyata() != null ? hm.getSyaratNyata() : null);
                            }

                        }

                    }
                }
                break;
        }

        InfoAudit ia = disHakmilikPermohonan.getHakmilikPermohonan().getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
            disHakmilikPermohonan.getHakmilikPermohonan().setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            disHakmilikPermohonan.getHakmilikPermohonan().setInfoAudit(ia);
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
            PermohonanPermitItem ppi = disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan());
            if (ppi != null) {
                ppi.setInfoAudit(disLaporanTanahService.findAudit(ppi, "update", pengguna));
            } else {
                ppi = new PermohonanPermitItem();
                ppi.setInfoAudit(disLaporanTanahService.findAudit(ppi, "new", pengguna));
            }
            ppi.setKodCawangan(pengguna.getKodCawangan());
            ppi.setPermohonan(permohonan);
            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
            if (StringUtils.isNotBlank(kodPermit)) {
                KodItemPermit kitem = new KodItemPermit();
                kitem = disLaporanTanahService.getKodItemPermitDAO().findById(kodPermit);
                if (kitem != null) {
                    ppi.setKodItemPermit(kitem);
                }

            }
            disLaporanTanahService.getPelupusanService().saveOrUpdate(ppi);
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            TanahRizabPermohonan mohonTrizabTemp = new TanahRizabPermohonan();
            mohonTrizabTemp = pelupusanService.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());
            String noLotInput = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
            String kodLotInput = getContext().getRequest().getParameter("kodLot.kod");

            if (mohonTrizabTemp != null) {
                ia = mohonTrizabTemp.getInfoAudit();
                mohonTrizabTemp.setInfoAudit(ia);
            } else {
                mohonTrizabTemp = new TanahRizabPermohonan();
                mohonTrizabTemp.setInfoAudit(ia);
                mohonTrizabTemp.setPermohonan(permohonan);
                mohonTrizabTemp.setCawangan(permohonan.getCawangan());
                mohonTrizabTemp.setDaerah(permohonan.getCawangan().getDaerah());
                mohonTrizabTemp.setBandarPekanMukim(kodBPM);
                mohonTrizabTemp.setNoLot(noLotInput);
                mohonTrizabTemp.setAktif('Y');
            }
            if (StringUtils.isNotBlank(luasTerlibat)) {
                BigDecimal luas = BigDecimal.valueOf(Double.parseDouble(luasTerlibat));
                mohonTrizabTemp.setLuasTerlibat(luas);
            }

            if (!StringUtils.isEmpty(tanahR)) {
                mohonTrizabTemp.setRizab(kodRizabDAO.findById(Integer.parseInt(tanahR)));
                pelupusanService.simpanTanahRizabPermohonan(mohonTrizabTemp);
            }

            if (StringUtils.isNotBlank(noLotInput)) {
                disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(noLotInput);
            } else {
                disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(null);
            }
            if (StringUtils.isNotBlank(kodLotInput)) {
                KodLot kodLot = new KodLot();
                kodLot = (KodLot) disLaporanTanahService.findObject(kodLot, new String[]{kodLotInput}, 0);
                disHakmilikPermohonan.getHakmilikPermohonan().setLot(kodLot);
            } else {
                disHakmilikPermohonan.getHakmilikPermohonan().setLot(null);
            }
        }
        if (!StringUtils.isEmpty(disHakmilikPermohonan.getTandaBlok())) {
            disHakmilikPermohonan.getHakmilikPermohonan().setNoUnitPetak(disHakmilikPermohonan.getTandaBlok());
        }

        disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());

        if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
            InfoAudit infoAudit = new InfoAudit();
            disPermohonanLaporanPelan.getPermohonanLaporanPelan().setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
            disPermohonanLaporanPelan.getPermohonanLaporanPelan().setInfoAudit(infoAudit);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
            disPermohonanLaporanPelan.getPermohonanLaporanPelan().setInfoAudit(infoAudit);
            disLaporanTanahService.getPelupusanService().simpanPermohonanLaporanPelan(disPermohonanLaporanPelan.getPermohonanLaporanPelan());
        }
        idMH = String.valueOf(disHakmilikPermohonan.getHakmilikPermohonan().getId());
    }

    public Resolution senaraiKodDaerahByKodCaw() {
        String kodCaw = (String) getContext().getRequest().getParameter("kodCaw");
        if (StringUtils.isNotBlank(kodCaw)) {
            listKodBPM = disLaporanTanahService.getPelupusanService().findBPMKod(kodCaw);
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/partial_kodBPM.jsp").addParameter("popup", "true");
    }

    public Resolution deleteRow() {

        String idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idMohonHakmilik != null && tName != null) {
//             hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(idMohonHakmilik)) ;
//             if(hakmilikPermohonan != null){
//                 disLaporanTanahService.getPelupusanService().deletePermohanHakmilik(hakmilikPermohonan);
//             }
//            disLaporanTanahService.getPelupusanService().deleteHakmilikPermohonanByIdMH(Long.valueOf(idMohonHakmilik));
            disLaporanTanahService.delObject(tName, new String[]{idMohonHakmilik}, typeSyor);
//            disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMohonHakmilik)));
//            if(disHakmilikPermohonan.getHakmilikPermohonan() != null){
//                disLaporanTanahService.getPelupusanService().deleteHakmilikPermohonanByIdMH(disHakmilikPermohonan.getHakmilikPermohonan().getId());
//            }
//            HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(new Long(idMohonHakmilik));
//            if (hp != null) {
//                hp.setKodKegunaanTanah(null);
//                hp.setKategoriTanahBaru(null);
//                hp.setBandarPekanMukimBaru(null);
//                hp.setKodUnitLuas(null);
//                hp.setPermohonan(null);
//
////                hp.setId(new Long(null));
//                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hp);
//
//
//            }
//            HakmilikPermohonan hp1 = pelupusanService.findByIdPermohonan(idPermohonan) ;
//            if(hp1 != null){
//                  pelupusanService.deletePermohanHakmilik(hp1);
//            }
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(DisPermohonanPage.getMT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public void getCasePRMP(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }

                } else if (type.equals("save")) {
                    PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");

                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                        }
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCaseLMCRG(int numnegeri, String type) {

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }
                    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
                    String kodUOM = getContext().getRequest().getParameter("disHakmilikPermohonan.keluasanUOM");
                    hakmilikPermohonan = pelupusanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);

                    if (hakmilikPermohonan != null) {
                        if (hakmilikPermohonan.getLuasTerlibat() != null && hakmilikPermohonan.getLokasi() != null) {
                            hakmilikPermohonan.getLuasTerlibat();
                            hakmilikPermohonan.getLokasi();
                            hakmilikPermohonan.getKodUnitLuas();
                        }

                        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
                    }

                } else if (type.equals("save")) {
                    PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");
                    if (permohonanLaporanPelan == null) {

                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                        }
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    InfoAudit ia = new InfoAudit();
                    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
                    hakmilik = disLaporanTanahService.getHakmilikDAO().findById(idHakmilik);
                    logger.info("id mohon : " + permohonan.getIdPermohonan());
                    logger.info("id hakmilik : " + idHakmilik);
                    hakmilikPermohonan = pelupusanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
//                    String kodUOM = getContext().getRequest().getParameter("hakmilikPermohonan.kodUnitLuas.kod");

                    if (hakmilikPermohonan != null) {
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        KodUOM kodUL = new KodUOM();
                        String keluasanInput = getContext().getRequest().getParameter("disHakmilikPermohonan.keluasanUOM");
                        if (StringUtils.isNotBlank(keluasanInput)) {
                            kodUL = (KodUOM) disLaporanTanahService.findObject(kodUL, new String[]{keluasanInput}, 0);
                            disHakmilikPermohonan.getHakmilikPermohonan().setKodUnitLuas(kodUL);
                        }
//                        hakmilikPermohonan.setKodUnitLuas(kodUOMDAO.findById("kodUOM"));
                        hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
                        hakmilikPermohonan.setLot(hakmilik.getLot());
                        hakmilikPermohonan.setNoLot(hakmilik.getNoLot());

                        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
                    }

//                    getCaseDefaultSave(numnegeri);
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }
                } else if (type.equals("save")) {
                    PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");

                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                        }
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);

                }
                break;
        }

    }

    public void getCaseMMMCL(int numnegeri, String type) {

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }
                } else if (type.equals("save")) {
                    PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");

                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                        }
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCasePBMT(int numnegeri, String type) {

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                    idMH = null;
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    //TODO
//                    if (StringUtils.isNotEmpty(idMH)) {
//                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
//                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();

                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }

                    if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                        String kGunaTanahInput = getContext().getRequest().getParameter("partialKodGunaTanah");
                        String kCatatan = getContext().getRequest().getParameter("hakmilikPermohonan.catatan");
                        KodKegunaanTanah kodGunaTanah = new KodKegunaanTanah();

                        if (StringUtils.isNotBlank(kCatatan)) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(kCatatan);
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(null);
                        }
                        getCaseDefaultSave(numnegeri);
                        if (StringUtils.isNotBlank(kGunaTanahInput)) {
                            kodGunaTanah = (KodKegunaanTanah) disLaporanTanahService.findObject(kodGunaTanah, new String[]{kGunaTanahInput}, 0);
                            if (kodGunaTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(kodGunaTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(null);
                        }
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");

                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();

                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }

                        if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                            String kGunaTanahInput = getContext().getRequest().getParameter("partialKodGunaTanah");
                            String kCatatan = getContext().getRequest().getParameter("hakmilikPermohonan.catatan");
                            KodKegunaanTanah kodGunaTanah = new KodKegunaanTanah();

                            if (StringUtils.isNotBlank(kGunaTanahInput)) {
                                kodGunaTanah = (KodKegunaanTanah) disLaporanTanahService.findObject(kodGunaTanah, new String[]{kGunaTanahInput}, 0);
                                if (kodGunaTanah != null) {
                                    disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(kodGunaTanah);
                                }
                            } else {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKodKegunaanTanah(null);
                            }
                            if (StringUtils.isNotBlank(kCatatan)) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(kCatatan);
                            } else {
                                disHakmilikPermohonan.getHakmilikPermohonan().setCatatan(null);
                            }
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePBMMK(int numnegeri, String type) {

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                    idMH = null;
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    //TODO
//                    if (StringUtils.isNotEmpty(idMH)) {
//                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
//                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePTBTS(int numnegeri, String type) {

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                } else if (type.equals("save")) {
                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCasePLPS(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePLPTD(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }
                } else if (type.equals("save")) {
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                    /*PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");

                    if (permohonanLaporanPelan == null) {
                    permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                    permohonanLaporanPelan.setPermohonan(permohonan);
                    permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanPelan.setNoLitho(noLitho);
                    if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                    permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                    }
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                    permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                    permohonanLaporanPelan.setNoLitho(noLitho);
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);*/
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }

                break;
        }

    }

    public void getCaseRLPS(int numnegeri, String type) {
        //disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                    if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                        permohonan = new Permohonan();
                        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
                        HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
                        //HakmilikPermohonan hakmilikmohon = new HakmilikPermohonan();
                        hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.getPelupusanService().findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                        HakmilikPermohonan hakmilikmohon = (HakmilikPermohonan) disLaporanTanahService.getPelupusanService().findByIdPermohonan(permohonan.getIdPermohonan());
                        if (hakmilikmohon == null) {
                            hakmilikmohon = new HakmilikPermohonan();
                            hakmilikmohon.setBandarPekanMukimBaru(hakmilikPermohonanSblm.getBandarPekanMukimBaru());
                            hakmilikmohon.setCatatan(hakmilikPermohonanSblm.getCatatan());
                            hakmilikmohon.setHakmilik(hakmilikPermohonanSblm.getHakmilik());
                            hakmilikmohon.setInfoAudit(hakmilikPermohonanSblm.getInfoAudit());
                            hakmilikmohon.setJarak(hakmilikPermohonanSblm.getJarak());
                            hakmilikmohon.setJarakDari(hakmilikPermohonanSblm.getJarakDari());
                            hakmilikmohon.setJarakDariKediaman(hakmilikPermohonanSblm.getJarakDariKediaman());
                            hakmilikmohon.setJarakDariKediamanUom(hakmilikPermohonanSblm.getJarakDariKediamanUom());
                            hakmilikmohon.setJarakDariNama(hakmilikPermohonanSblm.getJarakDariNama());
                            hakmilikmohon.setJenisPenggunaanTanah(hakmilikPermohonanSblm.getJenisPenggunaanTanah());
                            hakmilikmohon.setKategoriTanahBaru(hakmilikPermohonanSblm.getKategoriTanahBaru());
                            hakmilikmohon.setKodDUN(hakmilikPermohonanSblm.getKodDUN());
                            hakmilikmohon.setKodHakmilik(hakmilikPermohonanSblm.getKodHakmilik());
                            hakmilikmohon.setKodHakmilikSementara(hakmilikPermohonanSblm.getKodHakmilikSementara());
                            hakmilikmohon.setKodHakmilikTetap(hakmilikPermohonanSblm.getKodHakmilikTetap());
                            hakmilikmohon.setKodKegunaanTanah(hakmilikPermohonanSblm.getKodKegunaanTanah());
                            hakmilikmohon.setKodKawasanParlimen(hakmilikPermohonanSblm.getKodKawasanParlimen());
                            hakmilikmohon.setKodMilik(hakmilikPermohonanSblm.getKodMilik());
                            hakmilikmohon.setKodUnitLuas(hakmilikPermohonanSblm.getKodUnitLuas());
                            hakmilikmohon.setLokasi(hakmilikPermohonanSblm.getLokasi());
                            hakmilikmohon.setLuasDiluluskan(hakmilikPermohonanSblm.getLuasDiluluskan());
                            hakmilikmohon.setLuasLulusUom(hakmilikPermohonanSblm.getLuasLulusUom());
                            hakmilikmohon.setNoLot(hakmilikPermohonanSblm.getNoLot());
                            hakmilikmohon.setPegangan(hakmilikPermohonanSblm.getPegangan());
                            hakmilikmohon.setSekatanKepentingan(hakmilikPermohonanSblm.getSekatanKepentingan());
                            hakmilikmohon.setSekatanKepentinganBaru(hakmilikPermohonanSblm.getSekatanKepentinganBaru());
                            //hakmilikmohon.setSenaraiLaporanTanah(hakmilikPermohonanSblm.getSenaraiLaporanTanah());
                            hakmilikmohon.setSyaratNyata(hakmilikPermohonanSblm.getSyaratNyata());
                            hakmilikmohon.setSyaratNyataBaru(hakmilikPermohonanSblm.getSyaratNyataBaru());
                            hakmilikmohon.setLuasTerlibat(hakmilikPermohonanSblm.getLuasTerlibat());
                            hakmilikmohon.setUnitJarak(hakmilikPermohonanSblm.getUnitJarak());
                            hakmilikmohon.setPermohonan(permohonan);
                            disLaporanTanahService.getPlpservice().saveOrUpdate(hakmilikmohon);
                        }

                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePBGSA(int numnegeri, String type) {
        //disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCaseRLPSK(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    getCaseDefaultSave(numnegeri);
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    if (disPermohonanLaporanPelan.getPermohonanLaporanPelan() != null) {
                        disPermohonanLaporanPelan.setNoLitho(disPermohonanLaporanPelan.getPermohonanLaporanPelan().getNoLitho());
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            if (!StringUtils.isEmpty(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak())) {
                                disHakmilikPermohonan.setTandaBlok(disHakmilikPermohonan.getHakmilikPermohonan().getNoUnitPetak());
                            }
                        }
                    }
                } else if (type.equals("save")) {
                    PermohonanLaporanPelan permohonanLaporanPelan = new PermohonanLaporanPelan();
                    permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan, new String[]{permohonan.getIdPermohonan()}, 0);
                    String noLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");

                    if (permohonanLaporanPelan == null) {
                        permohonanLaporanPelan = new PermohonanLaporanPelan();
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "new", pengguna));
                        permohonanLaporanPelan.setPermohonan(permohonan);
                        permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        if (disHakmilikPermohonan.getHakmilikPermohonan() != null) {
                            permohonanLaporanPelan.setHakmilikPermohonan(disHakmilikPermohonan.getHakmilikPermohonan());
                        }
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    } else {
                        permohonanLaporanPelan.setInfoAudit(disLaporanTanahService.findAudit(permohonanLaporanPelan, "update", pengguna));
                        permohonanLaporanPelan.setNoLitho(noLitho);
                        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                    }
                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCaseBatuan(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    String lokasi = getContext().getRequest().getParameter("disHakmilikPermohonan.hakmilikPermohonan.lokasi");
                    String NoLot = getContext().getRequest().getParameter("disHakmilikPermohonan.hakmilikPermohonan.noLot");
                    String tandaBlok = getContext().getRequest().getParameter("disHakmilikPermohonan.tandaBlok");
                    String luasTerlibat = getContext().getRequest().getParameter("disHakmilikPermohonan.hakmilikPermohonan.luasTerlibat");

                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    disHakmilikPermohonan.getHakmilikPermohonan().setLokasi(lokasi);
                    disHakmilikPermohonan.getHakmilikPermohonan().setNoLot(NoLot);
                    disHakmilikPermohonan.getHakmilikPermohonan().setNoUnitPetak(tandaBlok);
                    if (StringUtils.isNotBlank(luasTerlibat)) {
                        disHakmilikPermohonan.getHakmilikPermohonan().setLuasTerlibat(new BigDecimal(luasTerlibat));
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setLuasTerlibat(new BigDecimal(0));
                    }

                    String NoLitho = getContext().getRequest().getParameter("disPermohonanLaporanPelan.noLitho");
                    disPermohonanLaporanPelan = new DisPermohonanLaporanPelan();
                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(new PermohonanLaporanPelan());
//                    disPermohonanLaporanPelan.setPermohonanLaporanPelan(disLaporanTanahService.getLaporanPelukisPelanService().findMohonLaporPelanByidMohon(permohonan.getIdPermohonan()));
                    disPermohonanLaporanPelan.getPermohonanLaporanPelan().setCawangan(permohonan.getCawangan());
                    disPermohonanLaporanPelan.getPermohonanLaporanPelan().setPermohonan(permohonan);
                    disPermohonanLaporanPelan.getPermohonanLaporanPelan().setNoLitho(NoLitho);

                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCaseLPSP(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItemTanahLPSP(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem = new DisPermohonanPermitItem();
//                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
//                    List<PermohonanPermitItem> listPermitItemTujuan = new ArrayList<PermohonanPermitItem>();
//                    listPermitItemTujuan = pelupusanService.findPermitItemTanahLPSP(permohonan.getIdPermohonan());
//
//
//                    logger.info("::: size listPermitItemTujuan :" + listPermitItemTujuan.size());
//                    if (!listPermitItemTujuan.isEmpty()) {
//                        disPermohonanPermitItem.setMohonPermitItem(listPermitItemTujuan.get(0));
//                    }
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));
//                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
//                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
//                    }
//
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
        }

    }

    public void getCasePPRU(int numnegeri, String type) {
        logger.info("______________________________ getCasePPRU ______________________________");
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
        }

    }

    public void getCasePPTR(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    mohonTrizab = new TanahRizabPermohonan();
                    mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());
                    if (mohonTrizab != null) {
                        if (mohonTrizab.getRizab() != null) {
                            tanahR = "" + mohonTrizab.getRizab().getKod();
                        }
                    }
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    mohonTrizab = new TanahRizabPermohonan();
                    mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());
                    if (mohonTrizab != null) {
                        if (mohonTrizab.getRizab() != null) {
                            tanahR = "" + mohonTrizab.getRizab().getKod();
                        }
                    }
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePTGSA(int numnegeri, String type) {
        logger.info("-------------- getCasePTGSA --------------");

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TODO HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    //TODO
//                    if (StringUtils.isNotEmpty(idMH)) {
//                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
//                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePRIZ(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                } else if (type.equals("save")) {

                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCaseLSTP(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePJLB(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disHakmilikPermohonan = new DisHakmilikPermohonan();
//                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
//                    disHakmilikPermohonan.getHakmilikPermohonan().setPermohonan(permohonan);
                } else if (type.equals("save")) {
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                }
                break;
        }

    }

    public void getCasePJTK(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
        }
    }

    public void getCasePWGSA(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
//                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan())); //replace ppi..variable seems to used only once, so share the variable
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
        }
    }

    public void getCasePTPBP(int numnegeri, String type) {
        logger.info("-------------- getCasePTPBP --------------");

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    //TODO
                    idMH = null;
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    //TODO
//                    if (StringUtils.isNotEmpty(idMH)) {
//                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
//                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePTMTA(int numnegeri, String type) {
        logger.info("-------------- getCasePTMTA --------------");

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TODO HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    //TODO
//                    if (StringUtils.isNotEmpty(idMH)) {
//                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
//                    }
                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                    KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                    if (StringUtils.isNotBlank(katgTanahInput)) {
                        kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                        if (kodKatTanah != null) {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                        }
                    } else {
                        disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                    }
                    getCaseDefaultSave(numnegeri);
                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        String katgTanahInput = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                        KodKategoriTanah kodKatTanah = new KodKategoriTanah();
                        if (StringUtils.isNotBlank(katgTanahInput)) {
                            kodKatTanah = (KodKategoriTanah) disLaporanTanahService.findObject(kodKatTanah, new String[]{katgTanahInput}, 0);
                            if (kodKatTanah != null) {
                                disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(kodKatTanah);
                            }
                        } else {
                            disHakmilikPermohonan.getHakmilikPermohonan().setKategoriTanahBaru(null);
                        }
                        getCaseDefaultSave(numnegeri);
                    }

                }
                break;
        }

    }

    public void getCasePRMMK(int numnegeri, String type) {
        switch (numnegeri) {
            case 1:
                //MELAKA
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItemTanahLPSP(permohonan.getIdPermohonan()));
                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (type.equals("rehydrate")) {
                    disPermohonanPermitItem = new DisPermohonanPermitItem();
//                    disPermohonanPermitItem.setMohonPermitItem(new PermohonanPermitItem());
                    disPermohonanPermitItem.setMohonPermitItem(disLaporanTanahService.getPelupusanService().findByIdMohonPermitItem(permohonan.getIdPermohonan()));

                    if (disPermohonanPermitItem.getMohonPermitItem() != null) {
                        disPermohonanPermitItem.setKodGunaTanah(disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit() != null ? disPermohonanPermitItem.getMohonPermitItem().getKodItemPermit().getKod() : new String());
                    }

                } else if (type.equals("save")) {
                    //TODO
                    disHakmilikPermohonan = new DisHakmilikPermohonan();
                    disHakmilikPermohonan.setHakmilikPermohonan(new HakmilikPermohonan());
                    getCaseDefaultSave(numnegeri);

                } else if (type.equals("update")) {
                    //TODO
                    if (StringUtils.isNotEmpty(idMH)) {
                        disHakmilikPermohonan.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.valueOf(idMH)));
                        getCaseDefaultSave(numnegeri);
                    }
                }
                break;
        }

    }

    public Resolution carianHakmilikPopup() {
        statusPage = getContext().getRequest().getParameter("statusPage");
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2CarianHakmilik.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution cariHakmilik() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        statusPage = (String) getContext().getRequest().getParameter("statusPage");
        listHakmilik = disLaporanTanahService.getPelupusanService().searchHakmilik(permohonan.getCawangan().getDaerah().getKod(), !StringUtils.isBlank(kodBPM) ? Integer.parseInt(kodBPM) : 0, !StringUtils.isBlank(seksyen) ? seksyen : new String(), !StringUtils.isBlank(noLot) ? noLot : new String(), !StringUtils.isBlank(idHakmilikCari) ? idHakmilikCari : new String());
        if (listHakmilik.size() > 0) {
            sizeKod = listHakmilik.size();
            addSimpleMessage("Maklumat Dijumpai");
        } else {
            addSimpleError("Tiada Maklumat Dijumpai");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/" + DisPermohonanPage.getMT_CARIAN_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanHakmilik() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        String item = (String) getContext().getRequest().getParameter("idHakmilik");
        statusPage = (String) getContext().getRequest().getParameter("statusPage");

        String[] listHakmilikTemp = item.split(",");
        for (int i = 0; i < listHakmilikTemp.length; i++) {

            if (!listHakmilikTemp[i].equals("T")) {
                idHakmilik = listHakmilikTemp[i];
            }
        }
        hakmilik = disLaporanTanahService.getHakmilikDAO().findById(idHakmilik);
        System.out.println("hakmilik ==== " + hakmilik);

        if (hakmilik == null) {
            System.out.println("hakmilik null");
            addSimpleError("Maklumat hakmilik tidak dijumpai");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2CarianHakmilik.jsp").addParameter("popup", "true");

        } else {
            InfoAudit ia = new InfoAudit();
            HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();

            hakmilikPermohonan = disLaporanTanahService.getPelupusanService().findByIdPermohonanNCatatan(idPermohonan, new String(), hakmilik.getIdHakmilik());
            if (hakmilikPermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = hakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            hakmilikPermohonan.setCatatan(hakmilikPermohonan.getCatatan());

            hakmilikPermohonan.setHakmilik(hakmilik);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            if (hakmilik.getLot() != null) {
                hakmilikPermohonan.setLot(hakmilik.getLot());
            }
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya dijumpai.");
        }
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/kemasukan/maklumat_tanah_dipohonV2CarianHakmilik.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId, Pengguna pengguna) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public DisPermohonanLaporanPelan getDisPermohonanLaporanPelan() {
        return disPermohonanLaporanPelan;
    }

    public void setDisPermohonanLaporanPelan(DisPermohonanLaporanPelan disPermohonanLaporanPelan) {
        this.disPermohonanLaporanPelan = disPermohonanLaporanPelan;
    }

    public boolean isForBPM() {
        return forBPM;
    }

    public void setForBPM(boolean forBPM) {
        this.forBPM = forBPM;
    }

    public boolean isForSeksyen() {
        return forSeksyen;
    }

    public void setForSeksyen(boolean forSeksyen) {
        this.forSeksyen = forSeksyen;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public DisHakmilikPermohonan getDisHakmilikPermohonan() {
        return disHakmilikPermohonan;
    }

    public void setDisHakmilikPermohonan(DisHakmilikPermohonan disHakmilikPermohonan) {
        this.disHakmilikPermohonan = disHakmilikPermohonan;
    }

    public DisPermohonanPermitItem getDisPermohonanPermitItem() {
        return disPermohonanPermitItem;
    }

    public void setDisPermohonanPermitItem(DisPermohonanPermitItem disPermohonanPermitItem) {
        this.disPermohonanPermitItem = disPermohonanPermitItem;
    }

    public DisTanahRizabPermohonan getDisTanahRizabPermohonan() {
        return disTanahRizabPermohonan;
    }

    public void setDisTanahRizabPermohonan(DisTanahRizabPermohonan disTanahRizabPermohonan) {
        this.disTanahRizabPermohonan = disTanahRizabPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMLMCRGPJLB() {
        if (StringUtils.isNotBlank(kodDaerah)) {
            return disLaporanTanahService.getPelupusanService().getSenaraiKodBPM(kodDaerah);
        }
        return new ArrayList<KodBandarPekanMukim>();
    }

    public void setSenaraiKodBPMLMCRGPJLB(List<KodBandarPekanMukim> senaraiKodBPMLMCRGPJLB) {
        this.senaraiKodBPMLMCRGPJLB = senaraiKodBPMLMCRGPJLB;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs() {
        if (StringUtils.isNotBlank(katTanahPilihan)) {
            return disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(katTanahPilihan);
        }
        return new ArrayList<KodKegunaanTanah>();
    }

    public void setSenaraiKodKegunaanTanahs(List<KodKegunaanTanah> senaraiKodKegunaanTanahs) {
        this.senaraiKodKegunaanTanahs = senaraiKodKegunaanTanahs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
//        logger.info("THIS IS KODBPM ->"+kodBpm);
        if (StringUtils.isNotBlank(kodBpm)) {
            return disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(kodBpm);
        }
        return new ArrayList<KodSeksyen>();
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public String getStatusPage() {
        return statusPage;
    }

    public void setStatusPage(String statusPage) {
        this.statusPage = statusPage;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoHM() {
        return noHM;
    }

    public void setNoHM(String noHM) {
        this.noHM = noHM;
    }

    public String getIdHakmilikCari() {
        return idHakmilikCari;
    }

    public void setIdHakmilikCari(String idHakmilikCari) {
        this.idHakmilikCari = idHakmilikCari;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getTanahR() {
        return tanahR;
    }

    public void setTanahR(String tanahR) {
        this.tanahR = tanahR;
    }

    public TanahRizabPermohonan getMohonTrizab() {
        return mohonTrizab;
    }

    public void setMohonTrizab(TanahRizabPermohonan mohonTrizab) {
        this.mohonTrizab = mohonTrizab;
    }

    public List<KodBandarPekanMukim> getListKodBPM() {
        return listKodBPM;
    }

    public void setListKodBPM(List<KodBandarPekanMukim> listKodBPM) {
        this.listKodBPM = listKodBPM;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PermohonanKelompok> getSenaraiKelompok() {
        return senaraiKelompok;
    }

    public void setSenaraiKelompok(List<PermohonanKelompok> senaraiKelompok) {
        this.senaraiKelompok = senaraiKelompok;
    }

    public List<HakmilikPermohonan> getMohonHakmilikKelompok() {
        return mohonHakmilikKelompok;
    }

    public void setMohonHakmilikKelompok(List<HakmilikPermohonan> mohonHakmilikKelompok) {
        this.mohonHakmilikKelompok = mohonHakmilikKelompok;
    }

    public Permohonan getPermohonanKelompok() {
        return permohonanKelompok;
    }

    public void setPermohonanKelompok(Permohonan permohonanKelompok) {
        this.permohonanKelompok = permohonanKelompok;
    }
}
