
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.SejarahHakmilik;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
//import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import etanah.service.RegService;
import etanah.service.SyerValidationService;
import etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean;
import java.util.ArrayList;
import java.util.Collections;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.math.fraction.Fraction;
import etanah.model.KodSeksyen;
import etanah.model.KodHakmilik;
import etanah.model.KodLot;
import etanah.model.KodKategoriTanah;
import etanah.model.KodUOM;
import etanah.model.KodDaerah;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikWaris;
import etanah.sequence.GeneratorIdHakmilik;
import java.util.Date;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.KodStatusHakmilik;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PemohonService;
import etanah.model.Pemohon;
import etanah.model.KodKadarCukai;
import java.math.BigDecimal;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodKegunaanTanah;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.Configuration;
import etanah.dao.HakmilikUrusanDAO;
import java.math.MathContext;
import java.math.RoundingMode;
import etanah.dao.KodNegeriDAO;
import etanah.model.KodNegeri;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.dao.KodBandarPekanMukimDAO;
import org.hibernate.Transaction;
import org.hibernate.Session;
import etanah.dao.KodDaerahDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.gis.PelanGIS;
import etanah.service.common.PermohonanPihakService;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.sequence.GeneratorNoAkaun;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.model.KodPBT;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.CalcTax;
import etanah.view.ListUtil;
import java.text.SimpleDateFormat;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.model.KodSekatanKepentingan;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.daftar.PembetulanService;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanPembetulanHakmilikDAO;
import etanah.model.PermohonanPembetulanHakmilik;

/**
 *
 * @author khairil
 */
@HttpCache(allow = false)
@UrlBinding("/pendaftaran/kemasukan_perincian_hakmilik")
public class KemasukanPerincianHakmilikActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KemasukanPerincianHakmilikActionBean.class);
    String idHakmilik;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikWarisService hakmilikWarisService;
    @Inject
    HakmilikWarisDAO hakmilikWarisdao;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSblmDAO;
    @Inject
    RegService regService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodSeksyen kodSeksyen;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    HakmilikAsalPermohonanDAO mohonHakmilikAsalDA0;
    @Inject
    HakmilikSebelumPermohonanDAO mohonHakmilikSblmDA0;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    GeneratorIdHakmilik gh;
    @Inject
    KodBandarPekanMukimDAO kodBpmDAO;
//  @Inject
//  KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanPembetulanHakmilikDAO permohonanPembetulanHakmilikDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    HakmilikUrusanService hUService;
    @Inject
    HakmilikPihakKepentinganService hPBService;
    @Inject
    KodBandarPekanMukimDAO kodBPMDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PelanGISDAO pelanGISDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanPihakService ppService;
    @Inject
    KodKegunaanTanahDAO kodGunaTanahDAO;
    @Inject
    PembetulanService pService;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    GeneratorNoAkaun genNoAkaun;
    @Inject
    KodBandarPekanMukimDAO bpmDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    ListUtil listUtil;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    private Permohonan p;
    private HakmilikAsalPermohonan mohonHakmilikAsal = new HakmilikAsalPermohonan();
    private HakmilikSebelumPermohonan mohonHakmilikSblm = new HakmilikSebelumPermohonan();
    private Hakmilik hakmilik;
    private HakmilikAsal hakmilikAsal;
    private HakmilikSebelum hakmilikSblm;
    private HakmilikPermohonan hp;
    private SejarahHakmilik sejarahHakmilik = new SejarahHakmilik();
//    private List<Pihak> listTuanTanah;
    private Permohonan mohon;
    private List<KodHakmilik> senaraiKodHakmilik;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList;
    private List<HakmilikAsal> hakmilikAsalList;
    private List<HakmilikAsal> listHakmilikAsalByIDHakmilikAsal;
    private List<HakmilikSebelum> listHakmilikSebelumByIDHakmilikSebelum;
    private List<HakmilikSebelum> hakmilikSebelumList;
    private List<SejarahHakmilik> listHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private List<HakmilikAsalPermohonan> listHakmilikAsalPermohonan = new ArrayList();
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan2;
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan;
    private List<HakmilikUrusan> hakmilikUrusanHmLama;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<Pemohon> senaraiPemohon;
    private List<HakmilikPermohonan> listHP;
    private List<Pemohon> pemohonList;
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<HakmilikAsal> senaraiHakmilikAsal = new ArrayList();
    private List<PermohonanPihak> mohonPihakList;
    private List<KodKadarCukai> kadarCukaiList;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<KodSeksyen> listKodSeksyenByBpm;
    private List<KodSeksyen> listKodSeksyenByBpm2;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<KodDaerah> senaraiKodDaerah;
    private List<HakmilikUrusan> senaraiHakmilikUrusan;
    private List<PermohonanPihak> senaraiMohonPihakTerlibat;
    private List<KodUOM> senaraiKodUOM;
    private List<HakmilikAsalPermohonan> listHakmilikSebelum = new ArrayList();
    private List<HakmilikSebelumPermohonan> listMohonHakmilikSebelum = new ArrayList();
    private KodSeksyen seksyen1;
    String idAsal;
    String seksyen2;
    String idSebelum;
    String idPermohonan;
    String kodCawangan;
    String kodNegeri;
    String namaNegeri;
    String kodDaerah;
    String namaBPM;
    String kodSyarat;
    String kodSekatan;
    String kodBPM;
    String jenisPelan = "";
    String kodPelan = "";
    Boolean pengPTG;
    private String[] syerPembilang;
    private String[] syerPenyebut;
    Fraction sum = new Fraction(0, 1);
    private int totalHakmilik = 1;
    BigDecimal total = BigDecimal.ZERO;
    int kodCukai = 0;
    BigDecimal kadarCukai = BigDecimal.ZERO;
    int size;
    int sizeHmSblm;
    String bpm;
    Long five = new Long(5);
    Long zero = new Long(0);
    String kodGunaTanah;
    String kodUnitLuas;
    String idHakmilikAsal;
    private int totalPihakHakmilikLama = 0;
    private String idHakmilikBaru;
    private List<KodPBT> senaraiKodPBT;
    private String trhWartaRezab;
    private String trhWartaPbt;
    private String trhWartaGsa;
    private String luasHakmilikAsal;
    private String cukaiLama;
    private String dmsPath;
    private String kodUnitLuasLama;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private BigDecimal totalLuas;
    private String kodUnitLuasDari;
    private String kodUnitLuasKepada;
    private BigDecimal luasDari;
    private BigDecimal luasTerlibat;
    private String kodUnitLuasTerlibat;
    private BigDecimal luasLain;
    private String kodUnitLuasLain;
    private List<Map<String, String>> senaraiUrusan;

    @DefaultHandler
    public Resolution showForm() {

        if (listKodSeksyenByBpm != null) {
            logger.debug("listKodSeksyenByBpm.size : " + listKodSeksyenByBpm.size());
        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getAttribute("idHakmilik");
        dmsPath = conf.getProperty("document.path");
        logger.debug("dmsPath" + dmsPath);
        logger.debug("::SHOWFORM::");
        logger.debug("idHakmilik :" + idHakmilik);
        logger.debug("idPermohonan :" + idPermohonan);
        if (StringUtils.isNotEmpty(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            if (hakmilik.getKegunaanTanah() != null) {
                kodGunaTanah = hakmilik.getKegunaanTanah().getKod();
            }
            if (hakmilik.getBandarPekanMukim() != null) {
                kodBPM = hakmilik.getBandarPekanMukim().getbandarPekanMukim();
            }
            if (hakmilik.getKodUnitLuas() != null) {
                if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                        || p.getKodUrusan().getKod().equals("HSSB")
                        || p.getKodUrusan().getKod().equals("HKABT")) {
                    listHP = regService.searchMohonHakmilik(idHakmilik, idPermohonan);
                    Long idMohonHakmilik = listHP.get(0).getId();
                    hp = hakmilikPermohonanDAO.findById(idMohonHakmilik);
                    if (hp != null) {
                        if (hp.getKodUnitLuas() != null) {
                            kodUnitLuas = hp.getKodUnitLuas().getKod();
                        }
                    }
                } else if (hakmilik.getKodUnitLuas() != null) {
                    kodUnitLuas = hakmilik.getKodUnitLuas().getKod();
                }
                logger.debug("SELECT KODUOM " + kodUnitLuas);
            }
            if (hakmilik.getSyaratNyata() != null) {
                kodSyarat = hakmilik.getSyaratNyata().getKod();
            }
            if (hakmilik.getSekatanKepentingan() != null) {
                kodSekatan = hakmilik.getSekatanKepentingan().getKod();
            }
            if (hakmilik.getRizabTarikhWarta() != null) {
                trhWartaRezab = sdf.format(hakmilik.getRizabTarikhWarta());
            }
            if (hakmilik.getPbtTarikhWarta() != null) {
                trhWartaPbt = sdf.format(hakmilik.getPbtTarikhWarta());
            }
            if (hakmilik.getGsaTarikhWarta() != null) {
                trhWartaGsa = sdf.format(hakmilik.getGsaTarikhWarta());
            }
            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                listHP = regService.searchMohonHakmilik(idHakmilik, idPermohonan);
                Long idMohonHakmilik = listHP.get(0).getId();
                hp = hakmilikPermohonanDAO.findById(idMohonHakmilik);
                listHakmilikAsalPermohonan = hp.getSenaraiHakmilikAsal();
                Hakmilik asal = hakmilikDAO.findById(listHakmilikAsalPermohonan.get(0).getHakmilikSejarah());
                luasHakmilikAsal = asal.getLuas().toString();
                kodUnitLuasLama = asal.getKodUnitLuas().getNama();
                cukaiLama = asal.getCukai().toString();
                logger.info("-> luas hakmilik asal : " + luasHakmilikAsal);
                logger.info("-> kodUnitLuasLama : " + kodUnitLuasLama);
                logger.info("-> cukaiLama : " + cukaiLama);
            }
            senaraiPemohon = pemohonService.findPemohonByHakmilik(idHakmilik);
            senaraiPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdHakmilik(idHakmilik);
            if (p.getKodUrusan().getKod().equals("HKGHS")) {
                List<HakmilikPermohonan> senaraiHakmilik = regService.searchMohonHakmilik(idHakmilik);
                List<HakmilikPermohonan> listDel = new ArrayList();
                for (HakmilikPermohonan hp : senaraiHakmilik) {
//                    HakmilikUrusan hu = pService.findHUrusan(hp.getHakmilik().getIdHakmilik(), hp.getPermohonan().getIdPermohonan());
                    List<HakmilikUrusan> senaraiHu = pService.findByIdHakmilikAndIdMohonList(hp.getHakmilik().getIdHakmilik(), hp.getPermohonan().getIdPermohonan());
                    for (HakmilikUrusan hu : senaraiHu) {
                        if (!hp.getPermohonan().getKodUrusan().getKod().equals("HKGHS")) {
                            if (hu == null) {
                                listDel.add(hp);
                            }
                            regService.deleteMohonHakmilik(listDel);
                        }
                    }
                }

            }
        }
        return new JSP("daftar/kemasukan_perincian_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution showFormHakmilikAsal() {
        return new JSP("daftar/kemasukan_hakmilik_asal.jsp").addParameter("tab", "true");
    }

    public Resolution showFormHakmilikSebelum() {
        listMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByIDPermohonan(idPermohonan);
        return new JSP("daftar/kemasukan_hakmilik_sebelum.jsp").addParameter("tab", "true");
    }

    public Resolution showCarianHakmilikAsal() {
        rehydrate();
        return new JSP("daftar/common/carian_hakmilik_asal.jsp").addParameter("tab", "true");
    }

    public Resolution showCarianHakmilikSebelum() {
        listMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByIDPermohonan(idPermohonan);
        rehydrate();
        return new JSP("daftar/common/carian_hakmilik_sebelum.jsp").addParameter("tab", "true");
    }

    public Resolution popupAreaConversion() {
        return new ForwardResolution("/WEB-INF/jsp/common/paparan_tukar_luas.jsp").addParameter("popup", "true");
    }

    public Resolution kira() {
        totalLuas = calcTax.kiraUnitLuas(kodUnitLuasKepada, kodUnitLuasDari, luasDari);
//        if(kodUnitLuasKepada.equals("H")){
//            totalLuas = calcTax.toHectare(kodUnitLuasDari,luasDari);
//        }
//        if(kodUnitLuasKepada.equals("M")){
//            totalLuas = calcTax.toMeter(kodUnitLuasDari, luasDari);
//        }
//        if(kodUnitLuasKepada.equals("E")){
//            totalLuas = calcTax.toAcre(kodUnitLuasDari, luasDari);
//        }
//        if(kodUnitLuasKepada.equals("K")){
//            totalLuas = calcTax.toSquareFoot(kodUnitLuasDari, luasDari);
//        }
        return new ForwardResolution("/WEB-INF/jsp/common/paparan_tukar_luas.jsp").addParameter("popup", "true");
    }

    public Resolution serahHakmilikPopup() {
//        if (p.getKodUrusan().getKod().equals("HSPB") || p.getKodUrusan().getKod().equals("HKPB")
//                || p.getKodUrusan().getKod().equals("HSPS") || p.getKodUrusan().getKod().equals("HKPS")) {
//            totalHakmilik = totalPihakHakmilikLama;
//        }
        listMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByIDPermohonan(idPermohonan);
        return new JSP("daftar/penyerahan_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiPBPopup() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        //String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.debug("idPermohonan" + idPermohonan);
        //logger.debug("idHakmilik"+idHakmilik);
        senaraiMohonPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
        logger.debug("size senaraiMohonPihakTerlibat" + senaraiMohonPihakTerlibat.size());
        //return new JSP("daftar/paparan_senarai_pb.jsp").addParameter("popup", "true");
        return new ForwardResolution("/WEB-INF/jsp/daftar/paparan_senarai_pb.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodGunaTanahByKatTanah() {
        logger.debug("::start filtering kod guna tanah");
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        logger.debug("kodKatTanah" + kodKatTanah);
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodgunatanah.jsp").addParameter("popup", "true");
    }

    public Resolution senaraiKodBPMByDaerah() {
        logger.debug(":::start search for kodbpm by daerah:::");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        String popup = (String) getContext().getRequest().getParameter("popup");
        logger.debug("kodDaerah :" + kodDaerah);
        if (StringUtils.isNotBlank(kodDaerah)) {
            listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(kodDaerah);
        } else {
            listBandarPekanMukim = kodBpmDAO.findAll();
            //listBandarPekanMukim.clear();
        }
        if (popup != null) {
            return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodbpm.jsp").addParameter("popup", "true");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodbpm_notpopup.jsp").addParameter("popup", "true");
        }
    }

    public Resolution senaraiSeksyenByBPM() {
        //listKodSeksyenByBpm.clear();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodBpm = (String) getContext().getRequest().getParameter("kodBpm");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        logger.debug("kodBpm : " + kodBpm);
        logger.debug("kodDaerah : " + kodDaerah);
        //KodBandarPekanMukim kbpm = kodBpmDAO.findById(Integer.parseInt(kodBpm));
        //KodBandarPekanMukim kbpm = regService.cariBPM(kodBpm, hakmilik.getDaerah().getKod());
        if (kodBpm != null) {
            //bpm = kbpm.getbandarPekanMukim();
            listKodSeksyenByBpm = regService.getSenaraiKodSeksyenByBPM(kodBpm, kodDaerah);
            if (listKodSeksyenByBpm.size() > 0) {
                logger.debug("listKodSeksyenByBpm.size : " + listKodSeksyenByBpm.size());
            }
        }
//        senaraiSeksyenByBPM2(kodBpm, kodDaerah);
        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodseksyen.jsp").addParameter("tab", "true");
//        return new ForwardResolution("daftar/kemasukan_perincian_hakmilik.jsp").addParameter("tab", "true");
//        return showForm();
//        return rehydrate();
    }

    public void senaraiSeksyenByBPM2(String kodBpm, String kodDaerah) {
        //listKodSeksyenByBpm.clear();
//        String kodBpm1 = (String) getContext().getRequest().getParameter("kodBpm");
//        String kodDaerah1 = (String) getContext().getRequest().getParameter("kodDaerah");
        logger.debug("kodBpm : " + kodBpm);
        logger.debug("kodDaerah : " + kodDaerah);
        if (kodBpm != null) {
            //bpm = kbpm.getbandarPekanMukim();
            listKodSeksyenByBpm = regService.getSenaraiKodSeksyenByBPM(kodBpm, kodDaerah);
            if (listKodSeksyenByBpm.size() > 0) {
                logger.debug("listKodSeksyenByBpm.size : " + listKodSeksyenByBpm.size());
            }
        }
//        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_kodseksyen.jsp").addParameter("tab", "true");
//        return new ForwardResolution("daftar/kemasukan_perincian_hakmilik.jsp").addParameter("tab", "true");
        showForm();
//        return rehydrate();
    }

    public Resolution filterKodUOM() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("katTanah");
        logger.debug("kodKatTanah :" + kodKatTanah);
        senaraiKodUOM = regService.senaraiKodUOMByKatTanah(kodKatTanah);
        if (p != null
                && (p.getKodUrusan().getKod().equals("BMSTM") || p.getKodUrusan().getKod().equals("HSBM")
                || p.getKodUrusan().getKod().equals("HKBM"))) {
//        senaraiKodUOM = listUtil.getSenaraiKodUOMMeterPadu();
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_koduom.jsp").addParameter("tab", "true");
    }

    public Resolution filterKodUOMLain() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("katTanah");
        logger.debug("kodKatTanah :" + kodKatTanah);
        senaraiKodUOM = regService.senaraiKodUOMByKatTanah(kodKatTanah);

        return new ForwardResolution("/WEB-INF/jsp/daftar/partial_koduomLain.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihakKelompok() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        logger.debug("simpan pihak kelompok utk :" + idHakmilik);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        List<HakmilikPermohonan> SenaraiHakmilikPermohonan = regService.searchMohonHakmilikByIdMohon(p.getIdPermohonan());
        for (HakmilikPermohonan hakmilikPermohonan : SenaraiHakmilikPermohonan) {
            String idHakmilik1 = hakmilikPermohonan.getHakmilik().getIdHakmilik();
            List<HakmilikPihakBerkepentingan> SenaraiHakmilikPihak = regService.searchPihakBerKepentinganPemilik(idHakmilik1);
            for (HakmilikPihakBerkepentingan hakmilikPihak : SenaraiHakmilikPihak) {
                hakmilikPihak.setHakmilik(hakmilikPermohonan.getHakmilik());
                hakmilikPihak.setCawangan(h.getCawangan());
                hakmilikPihak.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                hakmilikPihak.setJenis(hakmilikPihak.getJenis());
                hakmilikPihak.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                hakmilikPihak.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                hakmilikPihak.setJumlahPembilang(hakmilikPihak.getJumlahPembilang());
                hakmilikPihak.setJumlahPenyebut(hakmilikPihak.getJumlahPenyebut());
                hakmilikPihak.setPerserahan(hakmilikPihak.getPerserahan());
                hakmilikPihak.setPerserahanKaveat(hakmilikPihak.getPerserahanKaveat());
                hakmilikPihak.setKaveatAktif(hakmilikPihak.getKaveatAktif());
                hakmilikPihak.setAktif(hakmilikPihak.getAktif());
                hakmilikPihak.setPihak(hakmilikPihak.getPihak());
                hakmilikPihak.setInfoAudit(info);
                hakmilikPihak.setNama(hakmilikPihak.getNama());
                hakmilikPihak.setJenisPengenalan(hakmilikPihak.getJenisPengenalan());
                hakmilikPihak.setNoPengenalan(hakmilikPihak.getNoPengenalan());
                hakmilikPihak.setAlamat1(hakmilikPihak.getAlamat1());
                hakmilikPihak.setAlamat2(hakmilikPihak.getAlamat2());
                hakmilikPihak.setAlamat3(hakmilikPihak.getAlamat3());
                hakmilikPihak.setAlamat4(hakmilikPihak.getAlamat4());
                hakmilikPihak.setPoskod(hakmilikPihak.getPoskod());
                hakmilikPihak.setNegeri(hakmilikPihak.getNegeri());
                hakmilikPihak.setAlamatSurat(hakmilikPihak.getAlamatSurat());
                hakmilikPihakKepentinganService.save(hakmilikPihak);
            }
            addSimpleMessage("Kemasukan Data Berjaya");
            getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        }
        //rehydrate();
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter(
                "showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new RedirectResolution(KemasukanPerincianHakmilikActionBean.class).addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanSeksyen() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        BigDecimal luas = new BigDecimal("0.0");
        BigDecimal cukai = new BigDecimal("0.00");
//        idHakmilik = (String) getContext().getRequest().getParameter("id");
//        String kodSeksyen = (String) getContext().getRequest().getParameter("kodSeksyen");
        String kodSeksyen = (String) getContext().getRequest().getParameter("id");
//        String kodSeksyen2 = (String) getContext().getRequest().getParameter("partialKodSeksyen");
//        String kodSeksyen3 = (String) getContext().getRequest().getParameter("selectKodSeksyen");
        if (!kodSeksyen.equals("")) {
            seksyen1 = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
            kodSeksyen = seksyen1.getSeksyen();
            seksyen2 = seksyen1.getSeksyen();
        }

        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                luas = new BigDecimal(getContext().getRequest().getParameter("luasSA"));
                cukai = new BigDecimal(getContext().getRequest().getParameter("cukaiSA"));
            }
            logger.debug("idPermohonan :" + idPermohonan);
            logger.debug("Saving idHakmilik : " + hakmilik.getIdHakmilik());
            List<HakmilikPermohonan> senaraiHp = regService.searchMohonHakmilik(hakmilik.getIdHakmilik(), p.getIdPermohonan());
            logger.debug("senarai hakmilik permohonan lama size : " + senaraiHp.size());
            HakmilikPermohonan hp = senaraiHp.get(0);

            if (hakmilik.getIdHakmilik() != null) {
                List<HakmilikPermohonan> senaraiHP = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : senaraiHP) {
                    Hakmilik hm = hakmilikPermohonan.getHakmilik();
//                Hakmilik hm = hakmilikDAO.findById(hakmilik.getIdHakmilik());

                    Transaction tx = sessionProvider.get().beginTransaction();
                    tx.begin();
                    try {
                        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());

                        logger.debug("hm bpm:" + hm.getBandarPekanMukim().getbandarPekanMukim());
                        if (kodBPM != null) {
                            hakmilik.setBandarPekanMukim(regService.cariBPM(kodBPM, hakmilik.getDaerah().getKod()));
                        }
                        logger.debug("hakmilik bpm:" + hakmilik.getBandarPekanMukim().getbandarPekanMukim());
                        if (!hm.getBandarPekanMukim().getbandarPekanMukim().equals(hakmilik.getBandarPekanMukim().getbandarPekanMukim())
                                || !hm.getDaerah().getKod().equals(hakmilik.getDaerah().getKod())) {
                            logger.debug(":START PROCESS GEN ID BARU N TRANSFER DATA N DELETE OLD DATA:");
                            Hakmilik hmbaru = new Hakmilik();
                            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                            kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                            hakmilik.setBandarPekanMukim(kbpm);
                            idHakmilikBaru = gh.generate(kodNegeri, null, hakmilik);
                            hmbaru.setIdHakmilik(idHakmilikBaru);
                            hmbaru.setInfoAudit(info);
                            hmbaru.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));
                            hmbaru.setSeksyen(seksyen1);

                            KodUOM kuom = new KodUOM();
                            kuom = kodUOMDAO.findById(kodUnitLuas);
                            if (kuom != null) {
                                logger.debug("simpan kodUOM " + kuom.getKod());
                                hmbaru.setKodUnitLuas(kuom);
                            }
                            logger.debug("kodSyarat: " + kodSyarat);
                            logger.debug("kodSekatan: " + kodSekatan);
                            hmbaru.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));

                            if (kodSekatan != null) {
                                hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            hmbaru.setKodHakmilik(hakmilik.getKodHakmilik());
                            hmbaru.setLokasi(hakmilik.getLokasi());
                            hmbaru.setCawangan(hakmilik.getCawangan());
                            hmbaru.setDaerah(hakmilik.getDaerah());
                            hmbaru.setRizab(hakmilik.getRizab());
                            hmbaru.setPegangan(hakmilik.getPegangan());
                            hmbaru.setTempohPegangan(hakmilik.getTempohPegangan());
                            hmbaru.setNoPu(hakmilik.getNoPu());
                            hmbaru.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                            hmbaru.setPbt(hakmilik.getPbt());
                            hmbaru.setNoHakmilik(idHakmilikBaru.substring(idHakmilikBaru.length() - 6));
                            hmbaru.setNoFail(hakmilik.getNoFail());
                            hmbaru.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
                            hmbaru.setSeksyen(hakmilik.getSeksyen());
                            hmbaru.setKategoriTanah(hakmilik.getKategoriTanah());
                            hmbaru.setNoLot(hakmilik.getNoLot());
                            hmbaru.setLot(hakmilik.getLot());
                            hmbaru.setNoPelan(hakmilik.getNoPelan());
                            hmbaru.setNoLitho(hakmilik.getNoLitho());
                            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                                hmbaru.setLuas(luas);
                                hmbaru.setCukai(cukai);

                            } else {
                                hmbaru.setLuas(hakmilik.getLuas());
                                hmbaru.setCukai(hakmilik.getCukai());
                            }
                            hmbaru.setLotBumiputera(hakmilik.getLotBumiputera());
                            hmbaru.setMilikPersekutuan('T');
                            hmbaru.setTarikhDaftar(hm.getTarikhDaftar());
                            hmbaru.setTarikhDaftarAsal(hm.getTarikhDaftarAsal());
                            hmbaru.setTarikhLuput(hm.getTarikhLuput());
                            hmbaru.setPbtNoWarta(hakmilik.getPbtNoWarta());
                            hmbaru.setPbtKawasan(hakmilik.getPbtKawasan());
                            hmbaru.setRizabNoWarta(hakmilik.getRizabNoWarta());
                            hmbaru.setRizabKawasan(hakmilik.getRizabKawasan());
                            hmbaru.setGsaNoWarta(hakmilik.getGsaNoWarta());
                            hmbaru.setGsaKawasan(hakmilik.getGsaKawasan());
                            if (StringUtils.isNotBlank(trhWartaRezab)) {
                                hmbaru.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                            }
                            if (StringUtils.isNotBlank(trhWartaPbt)) {
                                hmbaru.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                            }
                            if (StringUtils.isNotBlank(trhWartaGsa)) {
                                hmbaru.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                            }
                            /*SAVE MAKLUMAT STRATA*/
                            hmbaru.setNoBangunan(hakmilik.getNoBangunan());
                            hmbaru.setNoTingkat(hakmilik.getNoTingkat());
                            hmbaru.setNoPetak(hakmilik.getNoPetak());

                            if (hakmilik.getKedalamanTanah() != null) {
                                hmbaru.setKedalamanTanah(hakmilik.getKedalamanTanah());
                            }
                            if (hakmilik.getKedalamanTanahUOM() != null) {
                                hmbaru.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                            }
                            HakmilikPermohonan hpbaru = new HakmilikPermohonan();
                            hpbaru.setPermohonan(p);
                            hpbaru.setHakmilik(hmbaru);

                            if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                    || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                    || p.getKodUrusan().getKod().equals("HKABT")) {
                                hpbaru.setLuasTerlibat(luasTerlibat);
                                hpbaru.setKodUnitLuas(hmbaru.getKodUnitLuas());
                            }
                            hpbaru.setInfoAudit(info);

                            regService.simpanMohonHakmilikDanHakmilik(hmbaru, hpbaru);

                            /*copy hakmilikpihak from hakmiliklama to baru*/
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                                if (hpk == null || hpk.getAktif() == 'T') {
                                    continue;
                                }
                                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                                hpb.setHakmilik(hmbaru);
                                hpb.setCawangan(hmbaru.getCawangan());
                                hpb.setPihakCawangan(hpk.getPihakCawangan());
                                hpb.setJenis(hpk.getJenis());
                                hpb.setSyerPembilang(hpk.getSyerPembilang());
                                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                                hpb.setPerserahan(hpk.getPerserahan());
                                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                                hpb.setKaveatAktif(hpk.getKaveatAktif());
                                hpb.setAktif(hpk.getAktif());
                                hpb.setPihak(hpk.getPihak());
                                hpb.setInfoAudit(info);
                                hpkService.saveWOT(hpb);
                            }

                            List<HakmilikPermohonan> listhakmilikpermohonanlama = regService.senaraiHakmilikPermohonanByIDHakmilik(hm.getIdHakmilik());
                            if (!listhakmilikpermohonanlama.isEmpty()) {
                                Permohonan permohonanlama = listhakmilikpermohonanlama.get(0).getPermohonan();
                            }
                            List<HakmilikSebelumPermohonan> senaraiMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByID(listhakmilikpermohonanlama.get(0).getId());
                            logger.debug("size senaraiMohonHakmilikSebelum :" + senaraiMohonHakmilikSebelum.size());
                            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiMohonHakmilikSebelum) {
                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                hsp.setHakmilikPermohonan(hpbaru);
                                hsp.setPermohonan(p);
                                hsp.setCawangan(hakmilikSebelumPermohonan.getCawangan());
                                hsp.setHakmilik(hakmilikSebelumPermohonan.getHakmilik());
                                hsp.setHakmilikSejarah(hakmilikSebelumPermohonan.getHakmilikSejarah());
                                hsp.setInfoAudit(info);
                                regService.simpanMohonHakmilikSebelumWOT(hsp);
                            }

                            List<HakmilikUrusan> senaraiHakmilikurusan = hUService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                            logger.debug("senaraiHakmilikUrusan lama size :" + senaraiHakmilikurusan.size());
                            for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                                hakmilikUrusanBaru.setHakmilik(hmbaru);
                                hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                                hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                                hakmilikUrusanBaru.setAktif(hu.getAktif());
                                hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                                hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                                hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                                hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                                hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                                hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                                hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                                hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                                hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                                hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                                hakmilikUrusanBaru.setItem(hu.getItem());
                                hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                                hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                                hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());

                                hakmilikUrusanBaru.setInfoAudit(info);
                                hUService.saveOrUpdate(hakmilikUrusanBaru);

                            }
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("B");
                            hm.setKodStatusHakmilik(ksh);
                            regService.simpanHakmilik(hm);

                            HakmilikPermohonan hplama = regService.searchMohonHakmilikObject(hm.getIdHakmilik(), idPermohonan);
                            if (hplama != null) {
                                List<HakmilikAsalPermohonan> listMohonHakmilikAsal = regService.searchMohonHakmilikAsalByID(hplama.getId());
                                List<HakmilikSebelumPermohonan> listMohonHakmilikSblm = regService.searchMohonHakmilikSblmByID(hplama.getId());

                                if (listMohonHakmilikAsal.size() > 0) {
                                    regService.deleteMohonHakmilikAsal(listMohonHakmilikAsal);
                                }

                                if (listMohonHakmilikSblm.size() > 0) {
                                    regService.deleteMohonHakmilikSblm(listMohonHakmilikSblm);
                                }
                                regService.deleteMohonHakmilikWOT(hp);
                            }
                            idHakmilik = hmbaru.getIdHakmilik();

                        } else {

                            hm.setInfoAudit(info);
                            if (StringUtils.isNotBlank(kodGunaTanah)) {
                                hm.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));
                            }

                            KodUOM ku = new KodUOM();
//                        ku = kodUOMDAO.findById(kodUnitLuas);
//                        if (ku != null) {
                            logger.debug("simpan kodUOM " + ku.getKod());
                            hm.setKodUnitLuas(hm.getKodUnitLuas());
//                        }

                            logger.debug("luas lain~~~: " + hakmilik.getLuasAlternatif());
                            if (hakmilik.getLuasAlternatif() != null) {
                                hm.setLuasAlternatif(hakmilik.getLuasAlternatif());
                            } else {
                                hm.setLuasAlternatif(null);
                            }

                            if (kodUnitLuasLain != null) {
                                KodUOM kul = new KodUOM();
                                kul = kodUOMDAO.findById(kodUnitLuasLain);
                                logger.debug("simpan kodUOM lain~~~" + kul.getKod());
                                hm.setKodUnitLuasAlternatif(kul);
                            } else {
                                hm.setKodUnitLuasAlternatif(null);
                            }
                            if (kodSyarat != null) {
                                hm.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            if (kodSekatan == null) {
                                KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                                if (kodNegeri.equals("04")) {
                                    KodSekatanKepentingan ksknull = regService.searchKodSekatanNULLByCaw(hakmilik.getCawangan().getKod());
                                    ksk.setKod(ksknull.getKod());
                                } else {
                                    ksk.setKod("0000000");
                                }
                                hm.setSekatanKepentingan(ksk);
                            } else {
                                hm.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            hm.setKodHakmilik(hakmilik.getKodHakmilik());
                            hm.setLokasi(hakmilik.getLokasi());
                            hm.setCawangan(hakmilik.getCawangan());
                            hm.setDaerah(hakmilik.getDaerah());
                            hm.setRizab(hakmilik.getRizab());
                            hm.setPegangan(hakmilik.getPegangan());
                            hm.setTempohPegangan(hakmilik.getTempohPegangan());
                            hm.setNoPu(hakmilik.getNoPu());
                            hm.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                            hm.setPbt(hakmilik.getPbt());
                            hm.setNoHakmilik(hm.getNoHakmilik());
                            hm.setNoFail(hakmilik.getNoFail());
                            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                            kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                            hm.setBandarPekanMukim(kbpm);
//                            if (p.getKodUrusan().getKod().equals("HKTK")) {
                            hm.setSeksyen(seksyen1);
//                            } else {
//                                hm.setSeksyen(hakmilik.getSeksyen());
//                            }
                            hm.setKategoriTanah(hakmilik.getKategoriTanah());
                            hm.setNoLot(hakmilik.getNoLot());
                            hm.setLot(hakmilik.getLot());
                            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                                hm.setLuas(luas);
                                hm.setCukai(cukai);
                            } else {
                                hm.setLuas(hakmilik.getLuas());
                                hm.setCukai(hakmilik.getCukai());
                            }
                            hm.setNoPelan(hakmilik.getNoPelan());
                            hm.setNoLitho(hakmilik.getNoLitho());
                            hm.setLotBumiputera(hakmilik.getLotBumiputera());
                            hm.setPbtNoWarta(hakmilik.getPbtNoWarta());
                            hm.setPbtKawasan(hakmilik.getPbtKawasan());
                            hm.setRizabNoWarta(hakmilik.getRizabNoWarta());
                            hm.setRizabKawasan(hakmilik.getRizabKawasan());
                            hm.setGsaNoWarta(hakmilik.getGsaNoWarta());
                            hm.setGsaKawasan(hakmilik.getGsaKawasan());
                            if (StringUtils.isNotBlank(trhWartaRezab)) {
                                hm.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                            }
                            if (StringUtils.isNotBlank(trhWartaPbt)) {
                                hm.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                            }
                            if (StringUtils.isNotBlank(trhWartaGsa)) {
                                hm.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                            }

                            /*SAVE MAKLUMAT STRATA*/
                            hm.setNoBangunan(hakmilik.getNoBangunan());
                            hm.setNoTingkat(hakmilik.getNoTingkat());
                            hm.setNoPetak(hakmilik.getNoPetak());

                            if (hakmilik.getKedalamanTanah() != null) {
                                hm.setKedalamanTanah(hakmilik.getKedalamanTanah());
                            }
                            if (hakmilik.getKedalamanTanahUOM() != null) {
                                hm.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                            }
                            if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                    || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                    || p.getKodUrusan().getKod().equals("HKABT")) {
                                hp.setLuasTerlibat(luasTerlibat);
                                hp.setKodUnitLuas(hm.getKodUnitLuas());
                            }
                            regService.simpanMohonHakmilikDanHakmilik(hm, hp);
                            idHakmilik = hm.getIdHakmilik();
                        }

                        tx.commit();

                    } catch (Exception e) {
                        tx.rollback();
                        Throwable t = e;
                        while (t.getCause() != null) {
                            t = t.getCause();
                        }
                        t.printStackTrace();
                        addSimpleError("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                        return null;
                    }

                    addSimpleMessage("Kemasukan Data Berjaya");
                }
            } else {
                addSimpleError("Hakmilik Tiada");
            }
        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);

    }

//    public Resolution simpanBerkelompokBaru() {
//      
//
//    }
    public Resolution simpanKelompok() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        kodUnitLuas = (String) getContext().getRequest().getParameter("kodUOM");
        kodUnitLuas = (String) getContext().getRequest().getParameter("kodUOM");
        kodUnitLuas = (String) getContext().getRequest().getParameter("kodUnitLuas");
        BigDecimal luas = new BigDecimal("0.0");
        BigDecimal cukai = new BigDecimal("0.00");



        logger.info(idPermohonan);
        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                luas = new BigDecimal(getContext().getRequest().getParameter("luasSA"));
                cukai = new BigDecimal(getContext().getRequest().getParameter("cukaiSA"));
            }
            logger.debug("idPermohonan :" + idPermohonan);
            logger.debug("Saving idHakmilik : " + hakmilik.getIdHakmilik());
            List<HakmilikPermohonan> senaraiHp = regService.searchMohonHakmilik(hakmilik.getIdHakmilik(), p.getIdPermohonan());
            logger.debug("senarai hakmilik permohonan lama size : " + senaraiHp.size());
            HakmilikPermohonan hp = senaraiHp.get(0);

            if (hakmilik.getIdHakmilik() != null) {
                List<HakmilikPermohonan> senaraiHP = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : senaraiHP) {
                    Hakmilik hm = hakmilikPermohonan.getHakmilik();
//                Hakmilik hm = hakmilikDAO.findById(hakmilik.getIdHakmilik());

                    Transaction tx = sessionProvider.get().beginTransaction();
                    tx.begin();
                    try {
                        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());

                        logger.debug("hm bpm:" + hm.getBandarPekanMukim().getbandarPekanMukim());
                        if (kodBPM != null) {
                            hakmilik.setBandarPekanMukim(regService.cariBPM(kodBPM, hakmilik.getDaerah().getKod()));
                        }
                        logger.debug("hakmilik bpm:" + hakmilik.getBandarPekanMukim().getbandarPekanMukim());
                        if (!hm.getBandarPekanMukim().getbandarPekanMukim().equals(hakmilik.getBandarPekanMukim().getbandarPekanMukim())
                                || !hm.getDaerah().getKod().equals(hakmilik.getDaerah().getKod())) {
                            logger.debug(":START PROCESS GEN ID BARU N TRANSFER DATA N DELETE OLD DATA:");
                            Hakmilik hmbaru = new Hakmilik();
                            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                            kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                            hakmilik.setBandarPekanMukim(kbpm);
                            idHakmilikBaru = gh.generate(kodNegeri, null, hakmilik);
                            hmbaru.setIdHakmilik(idHakmilikBaru);
                            hmbaru.setInfoAudit(info);
                            hmbaru.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));

                            KodUOM kuom = new KodUOM();
                            kuom = kodUOMDAO.findById(kodUnitLuas);
                            if (kuom != null) {
                                logger.debug("simpan kodUOM " + kuom.getKod());
                                hmbaru.setKodUnitLuas(kuom);
                            }
                            logger.debug("kodSyarat: " + kodSyarat);
                            logger.debug("kodSekatan: " + kodSekatan);
                            hmbaru.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));

                            if (kodSekatan != null) {
                                hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            hmbaru.setKodHakmilik(hakmilik.getKodHakmilik());
                            hmbaru.setLokasi(hakmilik.getLokasi());
                            hmbaru.setCawangan(hakmilik.getCawangan());
                            hmbaru.setDaerah(hakmilik.getDaerah());
                            hmbaru.setRizab(hakmilik.getRizab());
                            hmbaru.setPegangan(hakmilik.getPegangan());
                            hmbaru.setTempohPegangan(hakmilik.getTempohPegangan());
                            hmbaru.setNoPu(hakmilik.getNoPu());
                            hmbaru.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                            hmbaru.setPbt(hakmilik.getPbt());
                            hmbaru.setNoHakmilik(idHakmilikBaru.substring(idHakmilikBaru.length() - 6));
                            hmbaru.setNoFail(hakmilik.getNoFail());
                            hmbaru.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
                            hmbaru.setSeksyen(hakmilik.getSeksyen());
                            hmbaru.setKategoriTanah(hakmilik.getKategoriTanah());
                            hmbaru.setNoLot(hakmilik.getNoLot());
                            hmbaru.setLot(hakmilik.getLot());
                            hmbaru.setNoPelan(hakmilik.getNoPelan());
                            hmbaru.setNoLitho(hakmilik.getNoLitho());
                            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                                hmbaru.setLuas(luas);
                                hmbaru.setCukai(cukai);

                            } else {
                                hmbaru.setLuas(hakmilik.getLuas());
                                hmbaru.setCukai(hakmilik.getCukai());
                            }
                            hmbaru.setLotBumiputera(hakmilik.getLotBumiputera());
                            hmbaru.setMilikPersekutuan('T');
                            hmbaru.setTarikhDaftar(hm.getTarikhDaftar());
                            hmbaru.setTarikhDaftarAsal(hm.getTarikhDaftarAsal());
                            hmbaru.setTarikhLuput(hm.getTarikhLuput());
                            hmbaru.setPbtNoWarta(hakmilik.getPbtNoWarta());
                            hmbaru.setPbtKawasan(hakmilik.getPbtKawasan());
                            hmbaru.setRizabNoWarta(hakmilik.getRizabNoWarta());
                            hmbaru.setRizabKawasan(hakmilik.getRizabKawasan());
                            hmbaru.setGsaNoWarta(hakmilik.getGsaNoWarta());
                            hmbaru.setGsaKawasan(hakmilik.getGsaKawasan());
                            if (StringUtils.isNotBlank(trhWartaRezab)) {
                                hmbaru.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                            }
                            if (StringUtils.isNotBlank(trhWartaPbt)) {
                                hmbaru.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                            }
                            if (StringUtils.isNotBlank(trhWartaGsa)) {
                                hmbaru.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                            }
                            /*SAVE MAKLUMAT STRATA*/
                            hmbaru.setNoBangunan(hakmilik.getNoBangunan());
                            hmbaru.setNoTingkat(hakmilik.getNoTingkat());
                            hmbaru.setNoPetak(hakmilik.getNoPetak());

                            if (hakmilik.getKedalamanTanah() != null) {
                                hmbaru.setKedalamanTanah(hakmilik.getKedalamanTanah());
                            }
                            if (hakmilik.getKedalamanTanahUOM() != null) {
                                hmbaru.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                            }
                            HakmilikPermohonan hpbaru = new HakmilikPermohonan();
                            hpbaru.setPermohonan(p);
                            hpbaru.setHakmilik(hmbaru);

                            if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                    || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                    || p.getKodUrusan().getKod().equals("HKABT")) {
                                hpbaru.setLuasTerlibat(luasTerlibat);
                                hpbaru.setKodUnitLuas(hmbaru.getKodUnitLuas());
                            }
                            hpbaru.setInfoAudit(info);

                            regService.simpanMohonHakmilikDanHakmilik(hmbaru, hpbaru);

                            /*copy hakmilikpihak from hakmiliklama to baru*/
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                                if (hpk == null || hpk.getAktif() == 'T') {
                                    continue;
                                }
                                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                                hpb.setHakmilik(hmbaru);
                                hpb.setCawangan(hmbaru.getCawangan());
                                hpb.setPihakCawangan(hpk.getPihakCawangan());
                                hpb.setJenis(hpk.getJenis());
                                hpb.setSyerPembilang(hpk.getSyerPembilang());
                                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                                hpb.setPerserahan(hpk.getPerserahan());
                                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                                hpb.setKaveatAktif(hpk.getKaveatAktif());
                                hpb.setAktif(hpk.getAktif());
                                hpb.setPihak(hpk.getPihak());
                                hpb.setInfoAudit(info);
                                hpkService.saveWOT(hpb);
                            }

                            List<HakmilikPermohonan> listhakmilikpermohonanlama = regService.senaraiHakmilikPermohonanByIDHakmilik(hm.getIdHakmilik());
                            if (!listhakmilikpermohonanlama.isEmpty()) {
                                Permohonan permohonanlama = listhakmilikpermohonanlama.get(0).getPermohonan();
                            }
                            List<HakmilikSebelumPermohonan> senaraiMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByID(listhakmilikpermohonanlama.get(0).getId());
                            logger.debug("size senaraiMohonHakmilikSebelum :" + senaraiMohonHakmilikSebelum.size());
                            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiMohonHakmilikSebelum) {
                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                                hsp.setHakmilikPermohonan(hpbaru);
                                hsp.setPermohonan(p);
                                hsp.setCawangan(hakmilikSebelumPermohonan.getCawangan());
                                hsp.setHakmilik(hakmilikSebelumPermohonan.getHakmilik());
                                hsp.setHakmilikSejarah(hakmilikSebelumPermohonan.getHakmilikSejarah());
                                hsp.setInfoAudit(info);
                                regService.simpanMohonHakmilikSebelumWOT(hsp);
                            }

                            List<HakmilikUrusan> senaraiHakmilikurusan = hUService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                            logger.debug("senaraiHakmilikUrusan lama size :" + senaraiHakmilikurusan.size());
                            for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                                hakmilikUrusanBaru.setHakmilik(hmbaru);
                                hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                                hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                                hakmilikUrusanBaru.setAktif(hu.getAktif());
                                hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                                hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                                hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                                hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                                hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                                hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                                hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                                hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                                hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                                hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                                hakmilikUrusanBaru.setItem(hu.getItem());
                                hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                                hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                                hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());

                                hakmilikUrusanBaru.setInfoAudit(info);
                                hUService.saveOrUpdate(hakmilikUrusanBaru);

                            }
                            KodStatusHakmilik ksh = new KodStatusHakmilik();
                            ksh.setKod("B");
                            hm.setKodStatusHakmilik(ksh);
                            regService.simpanHakmilik(hm);

                            HakmilikPermohonan hplama = regService.searchMohonHakmilikObject(hm.getIdHakmilik(), idPermohonan);
                            if (hplama != null) {
                                List<HakmilikAsalPermohonan> listMohonHakmilikAsal = regService.searchMohonHakmilikAsalByID(hplama.getId());
                                List<HakmilikSebelumPermohonan> listMohonHakmilikSblm = regService.searchMohonHakmilikSblmByID(hplama.getId());

                                if (listMohonHakmilikAsal.size() > 0) {
                                    regService.deleteMohonHakmilikAsal(listMohonHakmilikAsal);
                                }

                                if (listMohonHakmilikSblm.size() > 0) {
                                    regService.deleteMohonHakmilikSblm(listMohonHakmilikSblm);
                                }
                                regService.deleteMohonHakmilikWOT(hp);
                            }
                            idHakmilik = hmbaru.getIdHakmilik();

                        } else {

                            hm.setInfoAudit(info);
                            if (StringUtils.isNotBlank(kodGunaTanah)) {
                                hm.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));
                            }

                            KodUOM ku = new KodUOM();
//                        ku = kodUOMDAO.findById(kodUnitLuas);
//                        if (ku != null) {
                            if (kodUnitLuas != null) {
                                KodUOM kodUom = kodUOMDAO.findById(kodUnitLuas);
                                if (kodUom != null) {
                                    hm.setKodUnitLuas(kodUom);
                                } else if (kodUom == null) {
                                    hm.setKodUnitLuas(hm.getKodUnitLuas());
                                }
                            }
                            logger.debug("simpan kodUOM " + ku.getKod());

//                        }

                            logger.debug("luas lain~~~: " + hakmilik.getLuasAlternatif());
                            if (hakmilik.getLuasAlternatif() != null) {
                                hm.setLuasAlternatif(hakmilik.getLuasAlternatif());
                            } else {
                                hm.setLuasAlternatif(null);
                            }

                            if (kodUnitLuasLain != null) {
                                KodUOM kul = new KodUOM();
                                kul = kodUOMDAO.findById(kodUnitLuasLain);
                                logger.debug("simpan kodUOM lain~~~" + kul.getKod());
                                hm.setKodUnitLuasAlternatif(kul);
                            } else {
                                hm.setKodUnitLuasAlternatif(null);
                            }
                            if (kodSyarat != null) {
                                hm.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            if (kodSekatan == null) {
                                KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                                if (kodNegeri.equals("04")) {
                                    KodSekatanKepentingan ksknull = regService.searchKodSekatanNULLByCaw(hakmilik.getCawangan().getKod());
                                    ksk.setKod(ksknull.getKod());
                                } else {
                                    ksk.setKod("0000000");
                                }
                                hm.setSekatanKepentingan(ksk);
                            } else {
                                hm.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                            }
                            hm.setKodHakmilik(hakmilik.getKodHakmilik());
                            hm.setLokasi(hakmilik.getLokasi());
                            hm.setCawangan(hakmilik.getCawangan());
                            hm.setDaerah(hakmilik.getDaerah());
                            hm.setRizab(hakmilik.getRizab());
                            hm.setPegangan(hakmilik.getPegangan());
                            hm.setTempohPegangan(hakmilik.getTempohPegangan());
                            hm.setNoPu(hakmilik.getNoPu());
                            hm.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                            hm.setPbt(hakmilik.getPbt());
                            hm.setNoHakmilik(hm.getNoHakmilik());
                            hm.setNoFail(hakmilik.getNoFail());
                            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                            kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                            hm.setBandarPekanMukim(kbpm);
                            hm.setSeksyen(hakmilik.getSeksyen());
                            hm.setKategoriTanah(hakmilik.getKategoriTanah());
                            hm.setNoLot(hakmilik.getNoLot());
                            hm.setLot(hakmilik.getLot());
                            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                                hm.setLuas(luas);
                                hm.setCukai(cukai);
                            } else {
                                hm.setLuas(hakmilik.getLuas());
                                hm.setCukai(hakmilik.getCukai());
                            }
                            hm.setNoPelan(hakmilik.getNoPelan());
                            hm.setNoLitho(hakmilik.getNoLitho());
                            hm.setLotBumiputera(hakmilik.getLotBumiputera());
                            hm.setPbtNoWarta(hakmilik.getPbtNoWarta());
                            hm.setPbtKawasan(hakmilik.getPbtKawasan());
                            hm.setRizabNoWarta(hakmilik.getRizabNoWarta());
                            hm.setRizabKawasan(hakmilik.getRizabKawasan());
                            hm.setGsaNoWarta(hakmilik.getGsaNoWarta());
                            hm.setGsaKawasan(hakmilik.getGsaKawasan());
                            if (StringUtils.isNotBlank(trhWartaRezab)) {
                                hm.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                            }
                            if (StringUtils.isNotBlank(trhWartaPbt)) {
                                hm.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                            }
                            if (StringUtils.isNotBlank(trhWartaGsa)) {
                                hm.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                            }

                            /*SAVE MAKLUMAT STRATA*/
                            hm.setNoBangunan(hakmilik.getNoBangunan());
                            hm.setNoTingkat(hakmilik.getNoTingkat());
                            hm.setNoPetak(hakmilik.getNoPetak());

                            if (hakmilik.getKedalamanTanah() != null) {
                                hm.setKedalamanTanah(hakmilik.getKedalamanTanah());
                            }
                            if (hakmilik.getKedalamanTanahUOM() != null) {
                                hm.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                            }
                            if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                    || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                    || p.getKodUrusan().getKod().equals("HKABT")) {
                                hp.setLuasTerlibat(luasTerlibat);
                                hp.setKodUnitLuas(hm.getKodUnitLuas());
                            }
                            regService.simpanMohonHakmilikDanHakmilik(hm, hp);
                            idHakmilik = hm.getIdHakmilik();
                        }

                        tx.commit();

                    } catch (Exception e) {
                        tx.rollback();
                        Throwable t = e;
                        while (t.getCause() != null) {
                            t = t.getCause();
                        }
                        t.printStackTrace();
                        addSimpleError("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                        return null;
                    }

                    addSimpleMessage("Kemasukan Data Berjaya");
                }
            } else {
                addSimpleError("Hakmilik Tiada");
            }
        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

//    public static void main(String[] param) throws ParseException {
//        SimpleDateFormat sdfoct = new SimpleDateFormat("dd-MM-yyyy");
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = Calendar.DECEMBER;
//        int day = 01;
//        Date oct = sdfoct.parse(day + "-" + month + "-" + year);
//        logger.debug("date NOV :" + sdfoct.format(oct.getTime()));
//        Date tarikhDaftar = cal.getTime();
//        logger.debug("date NOW :" + sdfoct.format(cal.getTime()));
//        if (tarikhDaftar.before(oct)) {
//            logger.debug("before oct");
//        }
//    }
    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodUnitLuas = (String) getContext().getRequest().getParameter("kodUOM");
        String kodUnitLuasBaru = (String) getContext().getRequest().getParameter("kodUnitLuas");
        BigDecimal luas = new BigDecimal("0.0");
        BigDecimal cukai = new BigDecimal("0.00");

        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                luas = new BigDecimal(getContext().getRequest().getParameter("luasSA"));
                cukai = new BigDecimal(getContext().getRequest().getParameter("cukaiSA"));
            }
            logger.debug("idPermohonan :" + idPermohonan);
            logger.debug("Saving idHakmilik : " + hakmilik.getIdHakmilik());
//            Hakmilik hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
            List<HakmilikPermohonan> senaraiHp = regService.searchMohonHakmilik(hakmilik.getIdHakmilik(), p.getIdPermohonan());
            logger.debug("senarai hakmilik permohonan lama size : " + senaraiHp.size());
            HakmilikPermohonan hp = senaraiHp.get(0);

            if (hakmilik.getIdHakmilik() != null) {
                Hakmilik hm = hakmilikDAO.findById(hakmilik.getIdHakmilik());

                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {
                    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    /*REGENERATE ID HAKMILIK FOR TUKAR KAWASAN*/
//                    if (p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HSTK")
//                            || p.getKodUrusan().getKod().equals("HKCTK") || p.getKodUrusan().getKod().equals("HSCTK")
//                            || p.getKodUrusan().getKod().equals("HKBTK") || p.getKodUrusan().getKod().equals("HSBTK")
//                            || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HKTKP")
//                            || p.getKodUrusan().getKod().equals("HSTKP") || p.getKodUrusan().getKod().equals("HKPTK")
//                            || p.getKodUrusan().getKod().equals("HKSTK") || p.getKodUrusan().getKod().equals("HKABT")) {
                    logger.debug("hm bpm:" + hm.getBandarPekanMukim().getbandarPekanMukim());
                    if (kodBPM != null) {
                        hakmilik.setBandarPekanMukim(regService.cariBPM(kodBPM, hakmilik.getDaerah().getKod()));
                    }
                    logger.debug("hakmilik bpm:" + hakmilik.getBandarPekanMukim().getbandarPekanMukim());
//                    if(!peng.isPenggunaPTG()){
                    if (!hm.getBandarPekanMukim().getbandarPekanMukim().equals(hakmilik.getBandarPekanMukim().getbandarPekanMukim())
                            || !hm.getDaerah().getKod().equals(hakmilik.getDaerah().getKod())) {
                        logger.debug(":START PROCESS GEN ID BARU N TRANSFER DATA N DELETE OLD DATA:");
                        Hakmilik hmbaru = new Hakmilik();
                        KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                        hakmilik.setBandarPekanMukim(kbpm);
                        idHakmilikBaru = gh.generate(kodNegeri, null, hakmilik);
                        hmbaru.setIdHakmilik(idHakmilikBaru);
                        hmbaru.setInfoAudit(info);
                        hmbaru.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));

                        KodUOM kuom = new KodUOM();
                        kuom = kodUOMDAO.findById(kodUnitLuas);
                        if (kuom == null) {
                            kuom = kodUOMDAO.findById(kodUnitLuasBaru);
                        }
                        if (kuom != null) {
                            logger.debug("simpan kodUOM " + kuom.getKod());
                            hmbaru.setKodUnitLuas(kuom);
                        }
                        //hm.setIdHakmilik(hakmilik.getIdHakmilik());
                        logger.debug("kodSyarat: " + kodSyarat);
                        logger.debug("kodSekatan: " + kodSekatan);
                        hmbaru.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));
//                        if (kodSekatan == null) {
//                            KodSekatanKepentingan ksk = new KodSekatanKepentingan();
//                            if (kodNegeri.equals("04")) {
////                                ksk.setKod("020000002");
//                                KodSekatanKepentingan ksknull = regService.searchKodSekatanNULLByCaw(hakmilik.getCawangan().getKod());
//                                ksk.setKod(ksknull.getKod());
//                            } else {
//                                ksk.setKod("0000000");
//                            }
//                            hmbaru.setSekatanKepentingan(ksk);
//                        } else {
////                            h.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan, kodDaerah));
//                            hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan, hakmilik.getCawangan().getKod()));
//                        }

                        if (kodSekatan != null) {
                            hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                        }
//                        hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan, hakmilik.getCawangan().getKod()));
                        //hmbaru.setSekatanKepentingan(hm.getSekatanKepentingan());
                        hmbaru.setKodHakmilik(hakmilik.getKodHakmilik());
                        hmbaru.setLokasi(hakmilik.getLokasi());
                        hmbaru.setCawangan(hakmilik.getCawangan());
                        hmbaru.setDaerah(hakmilik.getDaerah());
                        hmbaru.setRizab(hakmilik.getRizab());
                        hmbaru.setPegangan(hakmilik.getPegangan());
                        hmbaru.setTempohPegangan(hakmilik.getTempohPegangan());
                        hmbaru.setNoPu(hakmilik.getNoPu());
                        hmbaru.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                        hmbaru.setPbt(hakmilik.getPbt());
//                        hmbaru.setNoHakmilik(idHakmilikBaru.substring(idHakmilikBaru.length() - 8));
                        hmbaru.setNoHakmilik(idHakmilikBaru.substring(idHakmilikBaru.length() - 6));
                        hmbaru.setNoFail(hakmilik.getNoFail());
                        hmbaru.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
                        hmbaru.setSeksyen(hakmilik.getSeksyen());
                        hmbaru.setKategoriTanah(hakmilik.getKategoriTanah());
                        //String lotFormatted = String.format("%7d", hakmilik.getNoLot());
                        hmbaru.setNoLot(hakmilik.getNoLot());
                        hmbaru.setLot(hakmilik.getLot());
                        hmbaru.setNoPelan(hakmilik.getNoPelan());
                        hmbaru.setNoLitho(hakmilik.getNoLitho());
                        if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                            hmbaru.setLuas(luas);
                            hmbaru.setCukai(cukai);
                        } else {
                            hmbaru.setLuas(hakmilik.getLuas());
                            hmbaru.setCukai(hakmilik.getCukai());
                        }
                        hmbaru.setLotBumiputera(hakmilik.getLotBumiputera());
                        hmbaru.setMilikPersekutuan('T');
                        hmbaru.setTarikhDaftar(hm.getTarikhDaftar());
                        hmbaru.setTarikhDaftarAsal(hm.getTarikhDaftarAsal());
                        hmbaru.setTarikhLuput(hm.getTarikhLuput());
                        hmbaru.setPbtNoWarta(hakmilik.getPbtNoWarta());
                        hmbaru.setPbtKawasan(hakmilik.getPbtKawasan());
                        hmbaru.setRizabNoWarta(hakmilik.getRizabNoWarta());
                        hmbaru.setRizabKawasan(hakmilik.getRizabKawasan());
                        hmbaru.setGsaNoWarta(hakmilik.getGsaNoWarta());
                        hmbaru.setGsaKawasan(hakmilik.getGsaKawasan());
                        if (StringUtils.isNotBlank(trhWartaRezab)) {
                            hmbaru.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                        }
                        if (StringUtils.isNotBlank(trhWartaPbt)) {
                            hmbaru.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                        }
                        if (StringUtils.isNotBlank(trhWartaGsa)) {
                            hmbaru.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                        }
                        /*SAVE MAKLUMAT STRATA*/
                        hmbaru.setNoBangunan(hakmilik.getNoBangunan());
                        hmbaru.setNoTingkat(hakmilik.getNoTingkat());
                        hmbaru.setNoPetak(hakmilik.getNoPetak());

                        if (hakmilik.getKedalamanTanah() != null) {
                            hmbaru.setKedalamanTanah(hakmilik.getKedalamanTanah());
                        }
                        if (hakmilik.getKedalamanTanahUOM() != null) {
                            hmbaru.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                        }

                        //BigDecimal totalLuas = BigDecimal.ZERO;
                        //BigDecimal cukaiBaru = BigDecimal.ZERO;
                        //BigDecimal luasBaru = hakmilik.getLuas();
                        //String kodUOMBaru = hakmilik.getKodUnitLuas().getKod();
                        //BigDecimal luasLama = hp.getHakmilik().getSenaraiHakmilikAsal().get(0).getHakmilik().getLuas();
                        //String kodUOMLama = hp.getHakmilik().getSenaraiHakmilikAsal().get(0).getHakmilik().getKodUnitLuas().getKod();

                        /*                  TODO: CHECK UOM LUAS LAMA AND BARU SAMA DULU SEBELUM SUBTRACT*/
//                    if (kodUOMBaru.equals(kodUOMLama)) {
//                    } else {
//                        if (kodUOMBaru.equals("H")) {
//                            double q = 10000;
//                            BigDecimal conv = BigDecimal.valueOf(q);
//                            luasLama = luasLama.multiply(conv);
//                            totalLuas = luasLama.subtract(luasBaru);
//                        }
//                    }
                        HakmilikPermohonan hpbaru = new HakmilikPermohonan();
                        hpbaru.setPermohonan(p);
                        hpbaru.setHakmilik(hmbaru);

                        if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                || p.getKodUrusan().getKod().equals("HKABT")) {
                            hpbaru.setLuasTerlibat(luasTerlibat);
                            hpbaru.setKodUnitLuas(hmbaru.getKodUnitLuas());
                        }
                        hpbaru.setInfoAudit(info);

//                        if (p.getKodUrusan().getKod().equals("HKABT")) {
//                            List<HakmilikSebelumPermohonan> senaraiHAP = regService.searchMohonHakmilikSblmByID(hpbaru.getId());
//                            logger.debug("senarai hakmilik asal size" + senaraiHAP.size());
//                            HakmilikSebelumPermohonan hsp = senaraiHAP.get(0);
//                            Hakmilik hakmilikLama = hsp.getHakmilik();
//                            BigDecimal luasLama = hakmilikLama.getLuas();
//                            logger.debug("luas lama : " + luasLama);
//                            totalLuas = luasLama.subtract(luasBaru);
//                            logger.debug("luas baru : " + luasBaru);
//                            logger.debug("total luas : " + totalLuas);
//                            cukaiBaru = calcTax.calculate(hakmilikLama.getKegunaanTanah().getKod(), String.valueOf(hakmilikLama.getBandarPekanMukim().getKod()), hakmilikLama.getKodUnitLuas().getKod(), totalLuas, hakmilikLama,String.valueOf(hakmilikLama.getRizab().getKod()));
//                            logger.debug("cukaiBaru : " + cukaiBaru);
//                            hpbaru.setLuasTerlibat(totalLuas);
//                            hpbaru.setCukaiBaru(cukaiBaru);
//                            hpbaru.setKodUnitLuas(hakmilikLama.getKodUnitLuas());
//                        }
                        regService.simpanMohonHakmilikDanHakmilik(hmbaru, hpbaru);

                        /*copy hakmilikpihak from hakmiliklama to baru*/
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
                        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                            if (hpk == null || hpk.getAktif() == 'T') {
                                continue;
                            }
                            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                            hpb.setHakmilik(hmbaru);
                            hpb.setCawangan(hmbaru.getCawangan());
                            hpb.setPihakCawangan(hpk.getPihakCawangan());
                            hpb.setJenis(hpk.getJenis());
                            hpb.setSyerPembilang(hpk.getSyerPembilang());
                            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                            hpb.setPerserahan(hpk.getPerserahan());
                            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                            hpb.setKaveatAktif(hpk.getKaveatAktif());
                            hpb.setAktif(hpk.getAktif());
                            hpb.setPihak(hpk.getPihak());
                            hpb.setInfoAudit(info);
                            hpkService.saveWOT(hpb);
                        }

                        List<HakmilikPermohonan> listhakmilikpermohonanlama = regService.senaraiHakmilikPermohonanByIDHakmilik(hm.getIdHakmilik());
                        //for (HakmilikPermohonan hakmilikPermohonanLama : listhakmilikpermohonanlama) {
                        if (!listhakmilikpermohonanlama.isEmpty()) {
                            Permohonan permohonanlama = listhakmilikpermohonanlama.get(0).getPermohonan();
                        }
//                        List<PermohonanRujukanLuar> senaraiRujLuar = permohonanlama.getSenaraiRujukanLuar();
//                        PermohonanRujukanLuar permohonanRujukanLuar = null;
//                        if (senaraiRujLuar != null && senaraiRujLuar.size() > 0) {
//                            permohonanRujukanLuar = senaraiRujLuar.get(0);//TODO : for multiple rujukan luar?
//                        }

                        List<HakmilikSebelumPermohonan> senaraiMohonHakmilikSebelum = regService.searchMohonHakmilikSblmByID(listhakmilikpermohonanlama.get(0).getId());
                        logger.debug("size senaraiMohonHakmilikSebelum :" + senaraiMohonHakmilikSebelum.size());
                        for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : senaraiMohonHakmilikSebelum) {
                            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                            hsp.setHakmilikPermohonan(hpbaru);
                            hsp.setPermohonan(p);
                            hsp.setCawangan(hakmilikSebelumPermohonan.getCawangan());
                            hsp.setHakmilik(hakmilikSebelumPermohonan.getHakmilik());
                            hsp.setHakmilikSejarah(hakmilikSebelumPermohonan.getHakmilikSejarah());
                            //info.setDimasukOleh(peng);
                            //info.setTarikhMasuk(new java.util.Date());
                            hsp.setInfoAudit(info);
                            regService.simpanMohonHakmilikSebelumWOT(hsp);
                            //hakmilikSblmPermohonanDAO.save(hsp);
                        }

                        List<HakmilikUrusan> senaraiHakmilikurusan = hUService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                        logger.debug("senaraiHakmilikUrusan lama size :" + senaraiHakmilikurusan.size());
                        for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                            HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                            hakmilikUrusanBaru.setHakmilik(hmbaru);
                            hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                            hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                            hakmilikUrusanBaru.setAktif(hu.getAktif());
                            hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                            hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                            hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                            hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                            //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
                            hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                            hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                            hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                            hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                            hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                            hakmilikUrusanBaru.setItem(hu.getItem());
                            hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                            hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                            hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());

                            hakmilikUrusanBaru.setInfoAudit(info);
                            hUService.saveOrUpdate(hakmilikUrusanBaru);

                        }

                        /*DELETE HAKMILIK YG TELAH DITUKAR KAWASAN*/
                        //Hakmilik hakmiliklama = hakmilikDAO.findById(hm.getIdHakmilik());
                        //regService.deleteHakmilikWOT(hm);
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("B");
                        hm.setKodStatusHakmilik(ksh);
                        regService.simpanHakmilik(hm);

                        //List<HakmilikPermohonan> listhplama = regService.searchMohonHakmilik(hm.getIdHakmilik(), idPermohonan);
                        HakmilikPermohonan hplama = regService.searchMohonHakmilikObject(hm.getIdHakmilik(), idPermohonan);
                        if (hplama != null) {
                            List<HakmilikAsalPermohonan> listMohonHakmilikAsal = regService.searchMohonHakmilikAsalByID(hplama.getId());
                            List<HakmilikSebelumPermohonan> listMohonHakmilikSblm = regService.searchMohonHakmilikSblmByID(hplama.getId());

                            if (listMohonHakmilikAsal.size() > 0) {
                                regService.deleteMohonHakmilikAsal(listMohonHakmilikAsal);
                            }

                            if (listMohonHakmilikSblm.size() > 0) {
                                regService.deleteMohonHakmilikSblm(listMohonHakmilikSblm);
                            }
                            regService.deleteMohonHakmilikWOT(hp);
                        }

                        //if(senaraiHakmilikPihak.size() > 0){
                        //hpkService.deleteHakmilikPihakBerkepentingan(senaraiHakmilikPihak);
//
//
//                        for (HakmilikUrusan hu : senaraiHakmilikurusan) {
//                            HakmilikUrusan hub = new HakmilikUrusan();
//                            hub.setAktif('T');
//                            hUService.saveOrUpdate(hub);
//                        }
                        idHakmilik = hmbaru.getIdHakmilik();

                    } else {

                        hm.setInfoAudit(info);
                        if (StringUtils.isNotBlank(kodGunaTanah)) {
                            hm.setKegunaanTanah(kodGunaTanahDAO.findById(kodGunaTanah));
                        }

                        KodUOM ku = new KodUOM();
                        if (kodUnitLuas != null) {
                            ku = kodUOMDAO.findById(kodUnitLuas);
                            if (ku != null) {
                                logger.debug("simpan kodUOM " + ku.getKod());
                                hm.setKodUnitLuas(ku);
                            } else if (ku == null) {
                                hm.setKodUnitLuas(hm.getKodUnitLuas());
                            }
                        } else if (kodUnitLuasBaru != null) {
                            ku = kodUOMDAO.findById(kodUnitLuasBaru);
                            if (ku != null) {
                                logger.debug("simpan kodUOM " + ku.getKod());
                                hm.setKodUnitLuas(ku);
                            } else if (ku == null) {
                                hm.setKodUnitLuas(hm.getKodUnitLuas());
                            }
                        }



                        logger.debug("luas lain~~~: " + hakmilik.getLuasAlternatif());
                        if (hakmilik.getLuasAlternatif() != null) {
                            hm.setLuasAlternatif(hakmilik.getLuasAlternatif());
                        } else {
                            hm.setLuasAlternatif(null);
                        }

                        if (kodUnitLuasLain != null) {
                            KodUOM kul = new KodUOM();
                            kul = kodUOMDAO.findById(kodUnitLuasLain);
                            logger.debug("simpan kodUOM lain~~~" + kul.getKod());
                            hm.setKodUnitLuasAlternatif(kul);
                        } else {
                            hm.setKodUnitLuasAlternatif(null);
                        }

                        //hm.setIdHakmilik(hakmilik.getIdHakmilik());
                        hm.setSyaratNyata(regService.searchKodSyaratByCaw(kodSyarat.substring(2), hakmilik.getCawangan().getKod()));
                        if (kodSekatan == null) {
                            KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                            if (kodNegeri.equals("04")) {
                                KodSekatanKepentingan ksknull = regService.searchKodSekatanNULLByCaw(hakmilik.getCawangan().getKod());
                                ksk.setKod(ksknull.getKod());
                            } else {
                                ksk.setKod("0000000");
                            }
                            hm.setSekatanKepentingan(ksk);
                        } else {
//                             hmbaru.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan, hakmilik.getCawangan().getKod()));
                            hm.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan.substring(2), hakmilik.getCawangan().getKod()));
                        }
//                        hm.setSekatanKepentingan(regService.searchKodSekatanByCaw(kodSekatan, hakmilik.getCawangan().getKod()));
                        hm.setKodHakmilik(hakmilik.getKodHakmilik());
                        hm.setLokasi(hakmilik.getLokasi());
                        hm.setCawangan(hakmilik.getCawangan());
                        hm.setDaerah(hakmilik.getDaerah());
                        hm.setRizab(hakmilik.getRizab());
                        hm.setPegangan(hakmilik.getPegangan());
                        hm.setTempohPegangan(hakmilik.getTempohPegangan());
                        hm.setNoPu(hakmilik.getNoPu());
                        hm.setKodStatusHakmilik(hakmilik.getKodStatusHakmilik());
                        hm.setPbt(hakmilik.getPbt());
                        hm.setNoHakmilik(hm.getNoHakmilik());
                        hm.setNoFail(hakmilik.getNoFail());
                        KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        kbpm = regService.cariBPM(hakmilik.getBandarPekanMukim().getbandarPekanMukim(), hakmilik.getDaerah().getKod());
                        //hakmilik.setBandarPekanMukim(kbpm);
                        hm.setBandarPekanMukim(kbpm);
                        hm.setSeksyen(hakmilik.getSeksyen());
                        hm.setKategoriTanah(hakmilik.getKategoriTanah());
                        hm.setNoLot(hakmilik.getNoLot());
                        hm.setLot(hakmilik.getLot());
                        if (p.getKodUrusan().getKod().equals("HKSA") || p.getKodUrusan().getKod().equals("HSSA")) {
                            hm.setLuas(luas);
                            hm.setCukai(cukai);
                        } else {
                            hm.setLuas(hakmilik.getLuas());
                            hm.setCukai(hakmilik.getCukai());
                        }
                        hm.setNoPelan(hakmilik.getNoPelan());
                        hm.setNoLitho(hakmilik.getNoLitho());
                        hm.setLotBumiputera(hakmilik.getLotBumiputera());
                        hm.setPbtNoWarta(hakmilik.getPbtNoWarta());
                        hm.setPbtKawasan(hakmilik.getPbtKawasan());
                        hm.setRizabNoWarta(hakmilik.getRizabNoWarta());
                        hm.setRizabKawasan(hakmilik.getRizabKawasan());
                        hm.setGsaNoWarta(hakmilik.getGsaNoWarta());
                        hm.setGsaKawasan(hakmilik.getGsaKawasan());
                        if (StringUtils.isNotBlank(trhWartaRezab)) {
                            hm.setRizabTarikhWarta(sdf.parse(trhWartaRezab));
                        }
                        if (StringUtils.isNotBlank(trhWartaPbt)) {
                            hm.setPbtTarikhWarta(sdf.parse(trhWartaPbt));
                        }
                        if (StringUtils.isNotBlank(trhWartaGsa)) {
                            hm.setGsaTarikhWarta(sdf.parse(trhWartaGsa));
                        }

                        /*SAVE MAKLUMAT STRATA*/
                        hm.setNoBangunan(hakmilik.getNoBangunan());
                        hm.setNoTingkat(hakmilik.getNoTingkat());
                        hm.setNoPetak(hakmilik.getNoPetak());

                        if (hakmilik.getKedalamanTanah() != null) {
                            hm.setKedalamanTanah(hakmilik.getKedalamanTanah());
                        }
                        if (hakmilik.getKedalamanTanahUOM() != null) {
                            hm.setKedalamanTanahUOM(hakmilik.getKedalamanTanahUOM());
                        }

                        if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HSSTA") || p.getKodUrusan().getKod().equals("HKSTA")
                                || p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                                || p.getKodUrusan().getKod().equals("HKABT")) {
                            hp.setLuasTerlibat(luasTerlibat);
                            hp.setKodUnitLuas(hm.getKodUnitLuas());
                        }
                        regService.simpanMohonHakmilikDanHakmilik(hm, hp);
                        idHakmilik = hm.getIdHakmilik();
                    }

                    tx.commit();

                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }

                addSimpleMessage("Kemasukan Data Berjaya");
            } else {
                addSimpleError("Hakmilik Tiada");
            }

        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
//        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);

    }


    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan", "!simpanKelompok"})
    public void rehydrate() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (!StringUtils.isBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        }
        if (StringUtils.isBlank(idHakmilik)) {
            idHakmilik = null;
        }

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (peng != null) {
            if (peng.getKodCawangan().getKod().equals("00")) {
                pengPTG = true;
                kodDaerah = "01";
                logger.debug("pegawai ptg");
                listBandarPekanMukim = kodBpmDAO.findAll();
                if (idHakmilik != null) {
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                    if (hakmilik != null) {
                        senaraiKodDaerah = regService.cariDaerah(hakmilik.getDaerah().getKod());
                    }
//                    logger.debug("senaraiKodDaerah.size()" + senaraiKodDaerah.size());
                }
            } else {
                pengPTG = false;
                kodDaerah = peng.getKodCawangan().getDaerah().getKod();
                logger.debug("pegawai ptd");
                listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(peng.getKodCawangan().getDaerah().getKod());
                senaraiKodDaerah = regService.cariDaerah(kodDaerah);
                logger.debug("senaraiKodDaerah.size()" + senaraiKodDaerah.size());
            }

        }
        kodNegeri = conf.getProperty("kodNegeri");
        logger.debug("::rehydrate kodNegeri :" + kodNegeri);
        KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
        namaNegeri = kn.getNama();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("REHYDRATING");
        logger.debug("idHakmilik :" + idHakmilik);
        logger.debug("idPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            Hakmilik hakmiliktest = p.getSenaraiHakmilik().get(0).getHakmilik();
            logger.debug("idHakmilik :" + hakmiliktest.getKodHakmilik().getKod());
//            String kodHakmilik = p.getSenaraiHakmilik().get(0).getKodHakmilik().getKod();
            if (hakmiliktest.getKodHakmilik().getKod().equals("GRN") || hakmiliktest.getKodHakmilik().getKod().equals("PN")
                    || hakmiliktest.getKodHakmilik().getKod().equals("GMM") || hakmiliktest.getKodHakmilik().getKod().equals("GM")
                    || hakmiliktest.getKodHakmilik().getKod().equals("PM")) {
                jenisPelan = "B1";
                kodPelan = "1";
            } else {

                jenisPelan = "B2";
                kodPelan = "3";
            }

            logger.debug(" jenis pelan: " + jenisPelan);
            logger.debug(" kod pelan: " + kodPelan);
            //GET SENARAI KOD HAKMILIK BASED ON PTG / PTD
            logger.debug("peng.getKodCawangan().getName() : " + peng.getKodCawangan().getName());
//            }
            //TODO:TEMP SOLUTION TO DIFFERENTIATE PTG / PTD
            if (peng.getKodCawangan().getKod().equals("00")) {
                logger.debug("::get jeniskodhakmilik PTG:::");
                senaraiKodHakmilik = regService.senaraiKodHakmilikPTG(p.getKodUrusan().getKod());
            } else {
                logger.debug("::get jeniskodhakmilik PTD :::");
                if (kodNegeri.equals("05")) {
                    senaraiKodHakmilik = regService.listKodHakmilikPTDns(p.getKodUrusan().getKod()); // add by azri 30/7/2013 : for N9, there's hakmilik HMM and GMM 
                } else {
                    senaraiKodHakmilik = regService.senaraiKodHakmilikPTD(p.getKodUrusan().getKod());
                }
            }
            hakmilikPermohonanList = p.getSenaraiHakmilik();

        }
        if (idHakmilik != null) {
            if (idPermohonan != null) {
                listHP = regService.searchMohonHakmilik(idHakmilik, idPermohonan);
                Long idMohonHakmilik = listHP.get(0).getId();
                hp = hakmilikPermohonanDAO.findById(idMohonHakmilik);
                if (hp != null) {
                    if (p.getKodUrusan().getKod().equals("HKABS")
                            || p.getKodUrusan().getKod().equals("HSSB") || p.getKodUrusan().getKod().equals("HKSB")
                            || p.getKodUrusan().getKod().equals("HKABT")) {
                        luasTerlibat = hp.getLuasTerlibat();
                    }
                }
            }
            hakmilik = hakmilikDAO.findById(idHakmilik);
            senaraiKodPBT = listUtil.getSenaraiKodPBTByDaerah(hakmilik.getDaerah().getKod());

            // add by azri 30/7/2013 : refer to Safina SME Pendaftaran
            /* table Mohon_Hakmilik _Asal = hakmilik first bile buat sambungan -> Always 1 record. 
             * maklumat hakmilik asal takkan berubah 
             * table Mohon_Hakmilik_Sblm = hakmilik sebelum hakmilik sambungan itu. -> can have more then one 
             */
            listHakmilikAsalPermohonan = hp.getSenaraiHakmilikAsal();     // get mohon_hakmilik_asal   
            /*if (listHakmilikAsalPermohonan.size() <0){
             listHakmilikAsalPermohonan =  regService.searchMohonHakmilikAsalByIDHakmilik(idHakmilik);
            }*/
            listHakmilikSblmPermohonan = hp.getSenaraiHakmilikSebelum();  // get mohon_hakmliik_sblm
            size = listHakmilikAsalPermohonan.size();
            sizeHmSblm = listHakmilikSblmPermohonan.size(); //yus edit 08042019



            if (listHakmilikSblmPermohonan.size() > 0) {
                if (listHakmilikSblmPermohonan.get(0).getHakmilik().getLuas() != null
                        && StringUtils.isNotBlank(listHakmilikSblmPermohonan.get(0).getHakmilik().getLuas().toString())) {

                    Hakmilik hm = hakmilikDAO.findById(listHakmilikSblmPermohonan.get(0).getHakmilikSejarah());
                    if (hm != null && hm.getLuas() != null) {
                        luasHakmilikAsal = hm.getLuas().toString();
                    }
                }
                if (listHakmilikSblmPermohonan.get(0).getHakmilik().getCukai() != null
                        && StringUtils.isNotBlank(listHakmilikSblmPermohonan.get(0).getHakmilik().getCukai().toString())) {
                    //Hakmilik hm = hakmilikDAO.findById(listHakmilikSblmPermohonan.get(0).getHakmilikSejarah());
                    Hakmilik hm = hakmilikDAO.findById(listHakmilikSblmPermohonan.get(sizeHmSblm - 1).getHakmilikSejarah()); //yus edit 08042019
                    if (hm != null) {
                        cukaiLama = hm.getCukai().toString();
                    }
                }
                if (listHakmilikSblmPermohonan.get(0).getHakmilik().getKodUnitLuas() != null
                        && StringUtils.isNotBlank(listHakmilikSblmPermohonan.get(0).getHakmilik().getKodUnitLuas().getNama())) {
                    Hakmilik hm = hakmilikDAO.findById(listHakmilikSblmPermohonan.get(0).getHakmilikSejarah());
                    if (hm != null) {
                        kodUnitLuasLama = hm.getKodUnitLuas().getNama();
                    }
                }
            }

            hakmilikUrusanHmLama = new ArrayList();
            senaraiHakmilikPihak = new ArrayList();
            if (p.getKodUrusan().getKod().equals("HKGHS")) {
                if (listHakmilikSblmPermohonan.size() > 0) {
                    for (HakmilikSebelumPermohonan hakmilikSblm : listHakmilikSblmPermohonan) {
                        List<HakmilikUrusan> senaraiHU = hakmilikUrusanService.findHakmilikUrusanUrusniagaAndNB(hakmilikSblm.getHakmilikSejarah());
                        for (HakmilikUrusan hmUrusan : senaraiHU) {
                            HakmilikUrusan huLama = hUService.findByIdPerserahanIdHakmilik(hmUrusan.getIdPerserahan(), hakmilikSblm.getHakmilik().getIdHakmilik());
                            if (huLama == null) {
                                hakmilikUrusanHmLama.add(hmUrusan);
                            } else {
                                hakmilikUrusanHmLama.add(hmUrusan);
                            }
                        }
                        Hakmilik hakmilik = hakmilikDAO.findById(hakmilikSblm.getHakmilikSejarah());
                        if (hakmilik != null) {
                            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak1 = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hakmilik);
                            for (HakmilikPihakBerkepentingan hp : senaraiHakmilikPihak1) {
                                logger.info("ID HAKMILIK PIHAK = " + hp.getPihak().getIdPihak());
                                logger.info("ID HAKMILIK  = " + hakmilikSblm.getHakmilik().getIdHakmilik());
//                                logger.info("ID pihak  = " + hp.getPihak().getIdPihak());
//                                List<HakmilikPihakBerkepentingan> listHPBaru = pService.findByidHKodaktifList(String.valueOf(hp.getPihak().getIdPihak()), hakmilikSblm.getHakmilik().getIdHakmilik(), hp.getJenis().getKod());
                                List<HakmilikPihakBerkepentingan> hpBaru = pService.findByidHKodaktifList(String.valueOf(hp.getPihak().getIdPihak()), hakmilikSblm.getHakmilik().getIdHakmilik(), hp.getJenis().getKod());
                                if (hpBaru.size() > 0) {
                                    senaraiHakmilikPihak.add(hp);
                                }
                            }
                        }
                    }
                } else if (listHakmilikAsalPermohonan.size() > 0) {
                    for (HakmilikAsalPermohonan hakmilikAsal : listHakmilikAsalPermohonan) {
                        List<HakmilikUrusan> senaraiHU2 = hakmilikUrusanService.findHakmilikUrusanUrusniagaAndNB(hakmilikAsal.getHakmilikSejarah());
                        for (HakmilikUrusan hmUrusan2 : senaraiHU2) {
                            HakmilikUrusan huLama2 = hUService.findByIdPerserahanIdHakmilik(hmUrusan2.getIdPerserahan(), hakmilikAsal.getHakmilik().getIdHakmilik());
                            if (huLama2 == null) {
                                logger.info("TEST....MASUK TAK CODING NI");
                                hakmilikUrusanHmLama.add(hmUrusan2);
                            } else {
                                hakmilikUrusanHmLama.add(hmUrusan2);
                            }
                        }
                        Hakmilik hakmilik = hakmilikDAO.findById(hakmilikAsal.getHakmilikSejarah());
                        logger.info("ID HAKMILIK PIHAK hakmilik= " + hakmilik.getIdHakmilik());
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak2 = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hakmilik);
//                       List<HakmilikPihakBerkepentingan> 
                        if (senaraiHakmilikPihak2.size() > 0) {
                            senaraiHakmilikPihak.addAll(senaraiHakmilikPihak2);

                        }
                        List<HakmilikPermohonan> hakmilikLama = p.getSenaraiHakmilik();
                        List<HakmilikPihakBerkepentingan> senaraiHakmilikP = hakmilikPihakKepentinganService.findAllPihakBerkepentinganByIdHakmilik(hakmilikLama.get(0).getHakmilik().getIdHakmilik());
                        for (HakmilikPihakBerkepentingan hakmilikP : senaraiHakmilikP) {
//                            HakmilikPihakBerkepentingan hakmilikPihak = pService.findByidHKodaktif(String.valueOf(hakmilikP.getPihak().getIdPihak()), hakmilik.getIdHakmilik(), hakmilikP.getJenis().getKod());

                            List<HakmilikPihakBerkepentingan> listHakmilikPihak = pService.findByidHKodaktifList(String.valueOf(hakmilikP.getPihak().getIdPihak()), hakmilik.getIdHakmilik(), hakmilikP.getJenis().getKod());
                            for (HakmilikPihakBerkepentingan hakmilikPihak : listHakmilikPihak) {
                                //                            for (HakmilikPihakBerkepentingan hpBaru : senaraiHakmilikPihak) {
//                                logger.info("hpBaru" + hakmilikPihak.getPerserahan().getIdUrusan());
//                                logger.info("hakmilikP" + hakmilikP.getPerserahan().getIdUrusan());
                                if (hakmilikPihak != null) {
                                    senaraiHakmilikPihak.remove(hakmilikPihak);
                                }
                            }
//                            }
                        }

//                        for (HakmilikPihakBerkepentingan hp : senaraiHakmilikPihak2) {
////                            logger.info("ID HAKMILIK PIHAK = " + hp.getIdHakmilikPihakBerkepentingan());
////                            logger.info("hp.getHakmilik() = " + hp.getHakmilik().getIdHakmilik());
////                            logger.info("id pihak = " + hp.getPihak().getIdPihak());
//                            List<HakmilikPihakBerkepentingan> ListhpBaru = pService.findByidHKodaktifList(String.valueOf(hp.getPihak().getIdPihak()), hakmilikAsal.getHakmilik().getIdHakmilik(), hp.getJenis().getKod());
//                            if (!ListhpBaru.isEmpty()) {
//                                if (senaraiHakmilikPihak.isEmpty()) {
//                                    for (HakmilikPihakBerkepentingan hp1 : ListhpBaru) {
//                                        for (HakmilikPihakBerkepentingan hp2 : senaraiHakmilikPihak) {
//                                            if (hp1.equals(hp2)) {
//                                                ListhpBaru.remove(hp1);
//                                            } else {
//                                                senaraiHakmilikPihak.add(hp1);
//                                            }
//                                        }
//                                    }
//                                    senaraiHakmilikPihak.addAll(ListhpBaru);
//                                }
//                            }
//                        }
                    }
                }
            } else {
                hakmilikUrusanHmLama = hUService.findHakmilikUrusanByIdHakmilikBatal(idHakmilik);
            }
            senaraiPermohonanPihak = new ArrayList();
            senaraiPemohon = new ArrayList();
            for (HakmilikUrusan hakmilikUrusanHmLama1 : hakmilikUrusanHmLama) {
                List<PermohonanPihak> senaraiPermohonanPihak1 = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hakmilikUrusanHmLama1.getIdPerserahan(), hakmilikUrusanHmLama1.getHakmilik().getIdHakmilik());
                List<Pemohon> senaraiPemohon1 = pemohonService.findPemohonByIdPermohonanAndHakmilik(hakmilikUrusanHmLama1.getIdPerserahan(), hakmilikUrusanHmLama1.getHakmilik().getIdHakmilik());

                if (senaraiPermohonanPihak1.size() > 0) {
                    for (PermohonanPihak mohonPihak : senaraiPermohonanPihak1) {
                        HakmilikPihakBerkepentingan hp = null;
                        if (mohonPihak.getPihak() != null) {
                            hp = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(mohonPihak.getPihak(), mohonPihak.getHakmilik());
                        }

                        if (hp == null) {
                            senaraiPermohonanPihak.add(mohonPihak);
                        }
                    }
//                    senaraiPermohonanPihak.addAll(senaraiPermohonanPihak1);
                }
//                if (senaraiPemohon1.size() > 0) {
//                    for (Pemohon pemohon : senaraiPemohon1) {
//                        HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pemohon.getPihak(), pemohon.getHakmilik());
//                        if (hp == null) {
//                            senaraiPemohon1.add(pemohon);
//                        }
//                    }
////                    senaraiPemohon.addAll(senaraiPemohon1);
//                }
            }
            senaraiUrusan = new ArrayList();
            senaraiHakmilikUrusan = hUService.findHakmilikUrusanByIdHakmilik(idHakmilik);
            pihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikDAO.findById(idHakmilik));
            pihakKepentinganSelainPMList = regService.searchPihakBerKepentinganSelainPemilikWarisCucuCicit(idHakmilik);
            syerPembilang = new String[pihakKepentinganList.size()];
            syerPenyebut = new String[pihakKepentinganList.size()];

        }
        if (pihakKepentinganList
                != null) {
            int counter = 0;
            for (int i = 0; i < pihakKepentinganList.size(); i++) {
                HakmilikPihakBerkepentingan hpb = pihakKepentinganList.get(i);
                syerPembilang[counter] = String.valueOf(hpb.getSyerPembilang());
                syerPenyebut[counter] = String.valueOf(hpb.getSyerPenyebut());
                counter = counter + 1;
            }
        }
    }

    public Resolution janaIDHakmilik() {

        List<HakmilikUrusan> lhuBaru = new ArrayList();
        logger.debug("jana totalHakmilik : " + totalHakmilik);
        if (totalHakmilik > 0) {

            for (int i = 0; i < totalHakmilik; i++) {
                if (idHakmilikAsal != null) {
                    Hakmilik ha = hakmilikDAO.findById(idHakmilikAsal);

                    if (ha != null) {
                        logger.debug("kodUrusan : " + p.getKodUrusan().getKod());
                        if (p.getKodUrusan().getKod().equals("HKABT") || p.getKodUrusan().getKod().equals("HKBTK")
                                || p.getKodUrusan().getKod().equals("HKPTK") || p.getKodUrusan().getKod().equals("HKSTK")
                                || p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HKTKP")
                                || p.getKodUrusan().getKod().equals("HSBTK") || p.getKodUrusan().getKod().equals("HSCTK")
                                || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HSTK")
                                || p.getKodUrusan().getKod().equals("HSTKP")) {
                            logger.debug("start tukar mukim for urusan tukar kawasan");
                            KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                            kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                            hakmilik.setBandarPekanMukim(kbpm);
                        } else {
                            logger.debug("start copy mukim from hakmilik asal");
                            hakmilik.setBandarPekanMukim(ha.getBandarPekanMukim());
                        }

                        idHakmilikBaru = gh.generate(kodNegeri, null, hakmilik);
                        logger.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                        Hakmilik hakmilikBaru = new Hakmilik();
                        /*SET ALL THE NOT NULLABLE COLUMN IN HAKMILIK*/
                        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                        KodCawangan kc = new KodCawangan();
                        kc.setKod(kodCawangan);
                        hakmilikBaru.setCawangan(kc);
                        hakmilikBaru.setDaerah(ha.getDaerah());
                        if (p.getKodUrusan().getKod().equals("HKABT") || p.getKodUrusan().getKod().equals("HKBTK")
                                || p.getKodUrusan().getKod().equals("HKPTK") || p.getKodUrusan().getKod().equals("HKSTK")
                                || p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HKTKP")
                                || p.getKodUrusan().getKod().equals("HSBTK") || p.getKodUrusan().getKod().equals("HSCTK")
                                || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HSTK")
                                || p.getKodUrusan().getKod().equals("HSTKP")) {
                            hakmilikBaru.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
                        } else {
                            hakmilikBaru.setBandarPekanMukim(ha.getBandarPekanMukim());
                        }
                        hakmilikBaru.setSeksyen(ha.getSeksyen());
                        hakmilikBaru.setLokasi(ha.getLokasi());
                        hakmilikBaru.setNoPu(ha.getNoPu());
                        hakmilikBaru.setNoLitho(ha.getNoLitho());
                        hakmilikBaru.setSekatanKepentingan(ha.getSekatanKepentingan());
                        hakmilikBaru.setSyaratNyata(ha.getSyaratNyata());
                        hakmilikBaru.setPbt(ha.getPbt());
                        KodHakmilik kodHakmilik = new KodHakmilik();
                        kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
                        hakmilikBaru.setKodHakmilik(kodHakmilik);
                        hakmilikBaru.setCukai(ha.getCukai());
                        //hakmilikBaru.set
                        //ha.getAkaunCukai().getBaki();
                        KodLot kl = new KodLot();
                        kl.setKod("1");
                        hakmilikBaru.setLot(kl);
                        // hakmilikBaru.setLot(ha.getLot());
                        // hakmilikBaru.setNoLot(ha.getNoLot());
//                    KodKategoriTanah kkt = new KodKategoriTanah();
//                    kkt.setKod("1");
                        hakmilikBaru.setKategoriTanah(ha.getKategoriTanah());
                        hakmilikBaru.setKegunaanTanah(ha.getKegunaanTanah());
                        if (ha.getSenaraiHakmilikAsal().size() > 0) {
                            Hakmilik asal = hakmilikDAO.findById(ha.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                            hakmilikBaru.setTarikhDaftar(asal.getTarikhDaftar());
                            hakmilikBaru.setTarikhLuput(asal.getTarikhLuput());
                        } else {
                            hakmilikBaru.setTarikhDaftar(ha.getTarikhDaftar());
                            hakmilikBaru.setTarikhLuput(ha.getTarikhLuput());
                        }
                        hakmilikBaru.setNoPelan(ha.getNoPelan());
                        hakmilikBaru.setTempohPegangan(ha.getTempohPegangan());
                        hakmilikBaru.setPegangan(ha.getPegangan());
                        KodStatusHakmilik ksh = new KodStatusHakmilik();
                        ksh.setKod("T");
                        hakmilikBaru.setKodStatusHakmilik(ksh);
                        hakmilikBaru.setLotBumiputera(ha.getLotBumiputera());
                        hakmilikBaru.setMilikPersekutuan(ha.getMilikPersekutuan());
//                    KodUOM kuo = new KodUOM();
//                    kuo.setKod("M");
                        hakmilikBaru.setKodUnitLuas(ha.getKodUnitLuas());
                        hakmilikBaru.setLuas(ha.getLuas());
                        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                        /*copy NO HAKMILIK*/
                        logger.debug("noHakmilik :" + noHakmilik);
                        hakmilikBaru.setNoHakmilik(noHakmilik);
                        /*AUDIT INFO*/
                        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                        InfoAudit info = peng.getInfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        hakmilikBaru.setInfoAudit(info);

                        /*INSERT INTO MOHON HAKMILIK*/
                        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                        mohonHakmilikBaru.setPermohonan(mohon);
                        mohonHakmilikBaru.setInfoAudit(info);
                        listMohonHakmilikBaru.add(mohonHakmilikBaru);
                        listHakmilikBaru.add(hakmilikBaru);

                    }
                } else {
                    KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                    logger.debug("daerah :" + hakmilik.getDaerah().getKod());
                    kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                    hakmilik.setBandarPekanMukim(kbpm);
                    logger.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                    logger.debug("kodNegeri : " + kodNegeri);
                    idHakmilikBaru = gh.generate(kodNegeri, null, hakmilik);
                    logger.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                    Hakmilik hakmilikBaru = new Hakmilik();
                    /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    KodCawangan kc = new KodCawangan();
                    kc.setKod(kodCawangan);
                    hakmilikBaru.setCawangan(kc);
                    hakmilikBaru.setDaerah(hakmilik.getDaerah());
                    hakmilikBaru.setBandarPekanMukim(kbpm);
                    hakmilikBaru.setSeksyen(null);
                    KodHakmilik kodHakmilik = new KodHakmilik();
                    kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
                    hakmilikBaru.setKodHakmilik(kodHakmilik);
                    KodLot kl = new KodLot();
                    kl.setKod("1");
                    hakmilikBaru.setLot(kl);
                    //hakmilikBaru.setNoLot("0");
                    KodKategoriTanah kkt = new KodKategoriTanah();
                    kkt.setKod("1");
                    hakmilikBaru.setKategoriTanah(kkt);
                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                    ksh.setKod("T");
                    hakmilikBaru.setKodStatusHakmilik(ksh);
                    hakmilikBaru.setLotBumiputera('T');
                    hakmilikBaru.setMilikPersekutuan('T');
                    KodUOM kuo = new KodUOM();
                    kuo.setKod("M");
                    hakmilikBaru.setKodUnitLuas(kuo);
                    hakmilikBaru.setLuas(BigDecimal.ZERO);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    /*copy NO HAKMILIK*/
                    logger.debug("noHakmilik :" + noHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    /*AUDIT INFO*/
                    Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikBaru.setInfoAudit(info);

                    /*INSERT INTO MOHON HAKMILIK*/
                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(mohon);
                    mohonHakmilikBaru.setInfoAudit(info);
                    listMohonHakmilikBaru.add(mohonHakmilikBaru);
                    listHakmilikBaru.add(hakmilikBaru);
                }
            }

            if (!listHakmilikBaru.isEmpty()) {
                regService.simpanHakmilikList(listHakmilikBaru);
            }
            if (!listMohonHakmilikBaru.isEmpty()) {
                regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
            }

            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            if (idHakmilikAsal != null) {
                Hakmilik ha = hakmilikDAO.findById(idHakmilikAsal);
                if (ha != null) {
                    List<HakmilikPermohonan> hpList = p.getSenaraiHakmilik();
                    List<HakmilikAsalPermohonan> hapList = new ArrayList();

                    for (int k = 0; k < hpList.size(); k++) {
                        HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                        hsp.setHakmilikPermohonan(p.getSenaraiHakmilik().get(k));
                        hsp.setHakmilik(ha);
                        hsp.setHakmilikSejarah(ha.getIdHakmilik());
                        hsp.setInfoAudit(info);
                        mohonHakmilikSblmDA0.save(hsp);

//                        if (ha.getSenaraiHakmilikAsal().size() > 0) {
//
//                            HakmilikAsalPermohonan mohonHakmilikAsal = new HakmilikAsalPermohonan();
//                            mohonHakmilikAsal.setHakmilikPermohonan(p.getSenaraiHakmilik().get(k));
//                            mohonHakmilikAsal.setHakmilik(hakmilikDAO.findById(ha.getSenaraiHakmilikAsal().get(0).getHakmilikAsal().getIdHakmilik()));
//                            mohonHakmilikAsal.setHakmilikSejarah(ha.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
//                            info.setDimasukOleh(peng);
//                            info.setTarikhMasuk(new java.util.Date());
//                            mohonHakmilikAsal.setInfoAudit(info);
//                            hapList.add(mohonHakmilikAsal);
//
//                            for (int n = 0; n < ha.getSenaraiHakmilikAsal().size(); n++) {
//                                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
//                                hsp.setHakmilikPermohonan(p.getSenaraiHakmilik().get(k));
//                                hsp.setHakmilik(ha.getSenaraiHakmilikAsal().get(n).getHakmilik());
//                                hsp.setHakmilikSejarah(sejarahHakmilikDAO.findById(ha.getIdHakmilik()));
//                                hsp.setInfoAudit(info);
//                                mohonHakmilikSblmDA0.save(hsp);
//                            }
//
//                        } else {
//
//                            HakmilikAsalPermohonan mohonHakmilikAsal = new HakmilikAsalPermohonan();
//                            mohonHakmilikAsal.setHakmilikPermohonan(p.getSenaraiHakmilik().get(k));
//                            mohonHakmilikAsal.setHakmilik(ha);
//                            mohonHakmilikAsal.setHakmilikSejarah(sejarahHakmilikDAO.findById(ha.getIdHakmilik()));
//                            info.setDimasukOleh(peng);
//                            info.setTarikhMasuk(new java.util.Date());
//                            mohonHakmilikAsal.setInfoAudit(info);
//                            hapList.add(mohonHakmilikAsal);
//
//                        }
                        logger.debug(":::START COPY HAKMILIK PIHAK FROM HAKMILIK ASAL:::");
                        List<HakmilikPihakBerkepentingan> lhpb = new ArrayList();
                        lhpb = hPBService.findHakmilikAllPihakActiveByHakmilik(ha);
                        if (lhpb.size() > 0) {
                            logger.debug(lhpb.size() + " PIHAK FOUND");
                        }

                        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhpb) {
                            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                            InfoAudit ia = hpb.getInfoAudit();
                            if (ia != null) {
                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                            } else {
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                            }
                            hpb.setHakmilik(p.getSenaraiHakmilik().get(k).getHakmilik());
                            hpb.setCawangan(p.getSenaraiHakmilik().get(k).getHakmilik().getCawangan());
                            hpb.setPihakCawangan(hakmilikPihakBerkepentingan.getPihakCawangan());
                            hpb.setJenis(hakmilikPihakBerkepentingan.getJenis());
                            hpb.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
                            hpb.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
                            hpb.setPerserahan(hakmilikPihakBerkepentingan.getPerserahan());
                            hpb.setPerserahanKaveat(hakmilikPihakBerkepentingan.getPerserahanKaveat());
                            hpb.setKaveatAktif(hakmilikPihakBerkepentingan.getKaveatAktif());
                            hpb.setAktif(hakmilikPihakBerkepentingan.getAktif());
                            hpb.setPihak(hakmilikPihakBerkepentingan.getPihak());
                            hpb.setInfoAudit(info);
                            hakmilikPihakKepentinganService.save(hpb);
                        }

                        List<HakmilikUrusan> lhu = new ArrayList();

                        logger.debug("START COPY HAKMILIK URUSAN FROM HAKMILIK ASAL");
                        //lhu = hUService.findHakmilikUrusanByIdHakmilik(sejarahHakmilik.getIdHakmilik());
                        List<HakmilikPermohonan> listhakmilikpermohonanlama = regService.senaraiHakmilikPermohonanByIDHakmilik(ha.getIdHakmilik());
                        logger.debug("listhakmilikpermohonanlama size :" + listhakmilikpermohonanlama.size());

                        Permohonan permohonanlama = listhakmilikpermohonanlama.get(0).getPermohonan();
                        List<PermohonanRujukanLuar> senaraiRujLuar = permohonanlama.getSenaraiRujukanLuar();
                        PermohonanRujukanLuar permohonanRujukanLuar = null;
                        if (senaraiRujLuar != null && senaraiRujLuar.size() > 0) {
                            permohonanRujukanLuar = senaraiRujLuar.get(0);//TODO : for multiple rujukan luar?
                        }

                        lhu = hUService.findHakmilikUrusanActiveByIdHakmilik(ha.getIdHakmilik());
                        if (lhu.size() > 0) {
                            logger.debug(lhu.size() + "URUSAN  FOUND");
                            logger.debug("saving hakmilik urusan to :" + p.getSenaraiHakmilik().get(k).getHakmilik().getIdHakmilik());
                        }

                        for (HakmilikUrusan hakmilikUrusan : lhu) {
                            HakmilikUrusan u = new HakmilikUrusan();
                            u.setHakmilik(p.getSenaraiHakmilik().get(k).getHakmilik());
                            u.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
                            u.setKodUrusan(hakmilikUrusan.getKodUrusan());
                            u.setAktif(hakmilikUrusan.getAktif());
                            u.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
                            u.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
                            u.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
                            u.setCawangan(hakmilikUrusan.getCawangan());
                            u.setLuasTerlibat(hakmilikUrusan.getLuasTerlibat());
                            //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
                            u.setCukaiLama(hakmilikUrusan.getCukaiLama());
                            u.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
                            u.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
                            u.setNoRujukan(hakmilikUrusan.getNoRujukan());
                            if (permohonanRujukanLuar != null) {
                                u.setTempohTahun(permohonanRujukanLuar.getTempohTahun());
                                u.setTempohBulan(permohonanRujukanLuar.getTempohBulan());
                                u.setTempohHari(permohonanRujukanLuar.getTempohHari());
                            }
//                                info.setTarikhMasuk(hakmilikUrusan.getInfoAudit().getTarikhMasuk());
                            u.setTarikhRujukan(hakmilikUrusan.getTarikhRujukan());
                            u.setItem(hakmilikUrusan.getItem());
                            u.setInfoAudit(info);
                            //hUService.saveOrUpdateUrusan(u);
                            //hUService.saveOrUpdate(u);
                            lhuBaru.add(u);
                        }
                        logger.debug("list urusan yang di copy size :" + lhuBaru.size());
                        hUService.saveOrUpdateUrusan(lhuBaru);

                    }
                    //regService.simpanMohonHakmilikAsal(hapList);

                }

            }
            addSimpleMessage("Kemasukan Hakmilik Baru Berjaya");

        }
//        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "showForm2").addParameter("tab", "true");
    }

    public Resolution checkNoLot() {
        String result = "0";
        String noLot = (String) getContext().getRequest().getParameter("noLot");
        String kodNegeri = (String) getContext().getRequest().getParameter("kodNegeri");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        String kodBPM = (String) getContext().getRequest().getParameter("kodBPM");
        String jenisLot = (String) getContext().getRequest().getParameter("jenisLot");
        String kodSeksyen = (String) getContext().getRequest().getParameter("kodSeksyen");

        if (!kodSeksyen.equals("")) {
            seksyen1 = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
            kodSeksyen = seksyen1.getSeksyen();
            seksyen2 = seksyen1.getSeksyen();
        }

        if (!kodBPM.isEmpty()) {
            logger.debug("::start check pelan for kodNegeri " + kodNegeri + "kodDaerah" + kodDaerah + "kodBPM" + kodBPM + "kodLot" + jenisLot);
//            KodBandarPekanMukim bpm = bpmDAO.findById(Integer.parseInt(kodBPM));
//            String kodBandarPekanMukim = bpm.getbandarPekanMukim();
//            KodBandarPekanMukim kbpm = regService.cariBPM(kodBandarPekanMukim, kodDaerah);
//            kodBPM = kbpm.getbandarPekanMukim();

            logger.info(":start check pelan for noLot: " + noLot);
            if (StringUtils.isNotBlank(noLot)) {
                //PelanGIS pgis = pelanGISDAO.findById(noLot);
                Hakmilik h = regService.checkNoLot1(kodNegeri, kodDaerah, kodBPM, noLot, kodSeksyen, jenisLot);
                if (h != null) {
                    result = "0";
                } else {
                    result = "1";
                }
            }
        }
        logger.debug("result noLot : " + result);
        return new StreamingResolution("text/plain", result);
    }

    public Resolution checkPelan() {
        String result = "0";
        String noLot = (String) getContext().getRequest().getParameter("noLot");
        String kodNegeri = (String) getContext().getRequest().getParameter("kodNegeri");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        String kodBPM = (String) getContext().getRequest().getParameter("kodBPM");
        String kodSeksyen = (String) getContext().getRequest().getParameter("kodSeksyen");
        logger.info("kodSeksyen = " + kodSeksyen);
        logger.info("kodBPM = " + kodBPM);
        logger.info("kodDaerah = " + kodDaerah);
        logger.info("kodSeksyen = " + kodSeksyen);
//        KodBandarPekanMukim kodBPMbaru = kodBpmDAO.findById(Integer.parseInt(kodBPM));
//        logger.info("getbandarPekanMukim = " + kodBPMbaru.getbandarPekanMukim());
        KodBandarPekanMukim kodBandarPekanMukim = regService.findKodBPMbyKodBPMAndKodDaerah(kodBPM, kodDaerah);
        logger.info("kod daerah kod = " + kodBandarPekanMukim.getKod());
        if (!kodSeksyen.equals(null)) {
            if (!kodBPM.equals("41")) {
                if (!kodSeksyen.equals("000")) {
                    seksyen1 = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
                    kodSeksyen = seksyen1.getSeksyen();
                    seksyen2 = seksyen1.getSeksyen();
                }
            } else if (kodBPM.equals("41")) {
                seksyen1 = regService.findKodSeksyenBandarWithKodBPM(kodSeksyen, kodBandarPekanMukim.getKod());
//                seksyen1 = kodSeksyenDAO.findById(Integer.valueOf(kodSeksyen));
                kodSeksyen = seksyen1.getSeksyen();
                seksyen2 = seksyen1.getSeksyen();
            } else {
                if (!kodSeksyen.equals("000")) {
                    seksyen1 = hPBService.searchBySeksyen(kodSeksyen);
                    kodSeksyen = seksyen1.getSeksyen();
                    seksyen2 = seksyen1.getSeksyen();
                }
            }
        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
        }
        if (!kodBPM.isEmpty()) {

            logger.debug("::start check pelan for kodNegeri " + kodNegeri + "kodDaerah" + kodDaerah + "kodBPM" + kodBPM + "kodSeksyen" + kodSeksyen + "jenisPelan" + jenisPelan);

            logger.info(":start check pelan for noLot: " + noLot);
            if (StringUtils.isNotBlank(noLot)) {

                if (seksyen2 == null) {
                    seksyen2 = kodSeksyen;
                }


                String path = regService.cariPathPelan(kodNegeri, kodDaerah, kodBPM, seksyen2, noLot, jenisPelan);
                if (StringUtils.isNotBlank(path)) {
                    result = "1";
                } else {
                    result = "0";
                }
            }
        }
        logger.debug("result Pelan : " + result);
        return new StreamingResolution("text/plain", result);
    }

    public Resolution calcLuasAmbilBalik() {
        logger.debug(":START CALC LUAS UNTUK AMBIL BALIK:");
        String result = "0";
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String luasAmbil = (String) getContext().getRequest().getParameter("luasAmbil");
        String kodUOMKepada = (String) getContext().getRequest().getParameter("kodUOMKepada");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("luas :" + luasAmbil);
        logger.debug("idHakmilik :" + idHakmilik);
        logger.debug("idPermohonan :" + idPermohonan);
        if (!StringUtils.isEmpty(idHakmilik)) {
            //Hakmilik ha = hakmilikDAO.findById(idHakmilikAsal);
            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);
//            Hakmilik h = hakmilikDAO.findById(idHakmilik);
            if (hp != null) {
                List<HakmilikSebelumPermohonan> lhs = regService.searchMohonHakmilikSblmByID(hp.getId());
                if (lhs.size() > 0) {
                    HakmilikSebelumPermohonan hsp = lhs.get(0);
                    //BigDecimal luasLama = hsp.getHakmilik().getLuas();
                    SejarahHakmilik sej = sejarahHakmilikDAO.findById(hsp.getHakmilikSejarah());
//        String kodUOMDari = hsp.getHakmilikSejarah().getKodUnitLuas().getKod();
                    String kodUOMDari = "";
                    if (sej != null) {
                        kodUOMDari = sej.getKodUnitLuas().getKod();
                    }

                    //String kodTanah = hsp.getHakmilik().getKegunaanTanah().getKod();
                    BigDecimal luasDiambil = new BigDecimal(luasAmbil);

//                String kodUOMKepada;
//                if(kodTanah.startsWith("P")){
//                    kodUOMKepada = "H";
//                }else{
//                    kodUOMKepada = "M";
//                }
//        BigDecimal luasLama = calcTax.kiraUnitLuas(kodUOMKepada, kodUOMDari, sej.getLuas());
//        logger.debug("luas lama : " + luasLama);
                    BigDecimal totalLuasBaru = hsp.getHakmilik().getLuas();//luasLama.subtract(luasDiambil);

                    logger.debug("luas baru : " + totalLuasBaru);

                    NumberFormat nf = new DecimalFormat("#.0000");
                    String formattedLuasBaru = nf.format(Double.parseDouble(totalLuasBaru.toString()));

                    if (hsp == null) {
                        result = "0";
                    } else {
                        result = formattedLuasBaru;
                    }
                }
            }
        }
        logger.debug("result : " + result);
        //getContext().getRequest().setAttribute("idHakmilikAsal", idHakmilikAsal);
        return new StreamingResolution("text/plain", result);
    }

    public Resolution checkHakmilik() {
        String result = "0";
        String idHakmilikAsal = (String) getContext().getRequest().getParameter("idHakmilikAsal");
        if (!StringUtils.isEmpty(idHakmilikAsal)) {
            Hakmilik ha = hakmilikDAO.findById(idHakmilikAsal);
            if (ha != null) {
                result = "1";
            } else {
                result = "0";
            }
        }
        logger.debug("result : " + result);
        getContext().getRequest().setAttribute("idHakmilikAsal", idHakmilikAsal);
        return new StreamingResolution("text/plain", result);
    }

    public Resolution checkTotalHakmilik() {
        String result = "0";
        idHakmilikAsal = (String) getContext().getRequest().getParameter("idHakmilikAsal");
        if (idHakmilikAsal != null) {
            logger.debug("idHakmilikAsal : " + idHakmilikAsal);
            Hakmilik hakmiliklama = hakmilikDAO.findById(idHakmilikAsal);
            if (hakmiliklama != null) {
                List<HakmilikPihakBerkepentingan> lhpb = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmiliklama);
                totalPihakHakmilikLama = lhpb.size();
            }
        }
        result = String.valueOf(totalPihakHakmilikLama);

        logger.debug("result : " + result);
        return new StreamingResolution("text/plain", result);
    }

//    public Resolution cariKodCukai() {
//        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
//        String kodBpm = (String) getContext().getRequest().getParameter("kodBpm");
//        String luas = (String) getContext().getRequest().getParameter("luas");
//
//        BigDecimal luasByH = new BigDecimal(luas);
//        logger.debug("kodBpm : " + kodBpm);
//        logger.debug("luas : " + luas);
//        String result = "";
//        if (StringUtils.isNotBlank(kodBpm) && StringUtils.isNotBlank(kodTanah)) {
//            /*TODO:FOR N9 NEED TO CHANGE */
//            if (kodTanah.equals("P03") && kodTanah.equals("P02") && kodTanah.equals("P01")) {
//                BigDecimal bg = new BigDecimal(5);
//                if (luasByH.compareTo(bg) < 0) {
//                    kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, five);
//                } else {
//                    kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
//                }
//            } else {
//                kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
//            }
//
//            if (kadarCukaiList.size() > 0) {
//                result = String.valueOf(kadarCukaiList.get(0).getKod());
//            } else {
//                result = "";
//            }
//        }
//        return new StreamingResolution("text/plain", result);
//    }
//    public Resolution cariKadarCukai() {
//        String kodBpm = (String) getContext().getRequest().getParameter("kodBpm");
//        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
//        String luas = (String) getContext().getRequest().getParameter("luas");
//        String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
//        BigDecimal luasByH = new BigDecimal(luas);
//        logger.debug("kodBpm : " + kodBpm);
//        logger.debug("kodTanah : " + kodTanah);
//        logger.debug("luas : " + luasByH);
//        logger.debug("kodUOM : " + kodUOM);
//        String result = "";
//        if (StringUtils.isNotBlank(kodBpm) && StringUtils.isNotBlank(kodTanah)) {
//            /*TODO:FOR N9 NEED TO CHANGE */
//            if (kodTanah.equals("P03") && kodTanah.equals("P02") && kodTanah.equals("P01")) {
//                BigDecimal bg = new BigDecimal(5);
//                if (luasByH.compareTo(bg) < 0) {
//                    kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, five);
//                } else {
//                    kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
//                }
//            } else {
//                kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
//            }
//            if (kadarCukaiList.size() > 0) {
//
//                result = String.valueOf(kadarCukaiList.get(0).getKadarMeterPersegi());
//                if (kodUOM.equals("H")) {
//                    //Long kadar = Long.parseLong(result);
//                    BigDecimal total = new BigDecimal(result);
//                    BigDecimal conv = new BigDecimal(10000);
//                    total = total.divide(conv);
//                }
//                if (kodUOM.equals("M") || kodUOM.equals("K")) {
//                    //Long kadar = Long.parseLong(result);
//                    BigDecimal total = new BigDecimal(result);
//                    BigDecimal conv = new BigDecimal(1000);
//                    total = total.divide(conv);
//
//                }
//                result = String.valueOf(total);
//            } else {
//                result = "";
//            }
//        }
//        return new StreamingResolution("text/plain", result);
//    }
    public Resolution kiraCukai() {
        String result = "";
        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
        String kodBpm = (String) getContext().getRequest().getParameter("kodBpm");
        KodBandarPekanMukim kbpm = regService.cariBPM(kodBpm, hakmilik.getDaerah().getKod());
        String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
        kodUnitLuas = kodUOM;
        String kodRizab = (String) getContext().getRequest().getParameter("kodRizab");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);
        logger.debug("idPermohonan : " + idPermohonan);
        logger.debug("idPermohonan : " + idHakmilik);

        Permohonan p = permohonanDAO.findById(idPermohonan);

        if (hp != null) {
            List<HakmilikSebelumPermohonan> lhs = regService.searchMohonHakmilikSblmByID(hp.getId());

            /*if ada hakmilik sebelum validate luas from hakmilik sebelum*/
            if (lhs.size() > 0) {
                logger.debug("lhs size : " + lhs.size());
                HakmilikSebelumPermohonan hsp = lhs.get(0);
                if (p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HKSB")
                        || p.getKodUrusan().getKod().equals("HSSB") || p.getKodUrusan().getKod().equals("HKABT")) {
                    logger.debug("luas comparation : " + hakmilik.getLuas().compareTo(hsp.getHakmilik().getLuas()));
                    if (hakmilik.getLuas().compareTo(hsp.getHakmilik().getLuas()) > 0) {
                        result = "1";
                    } else {
                        result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(kbpm.getKod()), kodUOM, hakmilik.getLuas(), hakmilik, kodRizab));
                    }
                }
                if (p.getKodUrusan().getKod().equals("HSPB") || p.getKodUrusan().getKod().equals("HKPB")) {

//          BigDecimal luasHakmilikAsal = hsp.getHakmilik().getLuas();
                    BigDecimal luasHakmilikAsal = BigDecimal.ZERO;
                    if (StringUtils.isNotBlank(hsp.getHakmilikSejarah())) {
                        luasHakmilikAsal = hakmilikDAO.findById(hsp.getHakmilikSejarah()).getLuas();
                    }

                    List<HakmilikPermohonan> lhp = p.getSenaraiHakmilik();
                    BigDecimal totalLuasSemuaHakmilik = BigDecimal.ZERO;
                    for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                        if (hakmilikPermohonan.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                            continue;
                        }
                        totalLuasSemuaHakmilik = totalLuasSemuaHakmilik.add(hakmilikPermohonan.getHakmilik().getLuas());
                    }

                    BigDecimal jumlahLuasKemasukan = hakmilik.getLuas().add(totalLuasSemuaHakmilik);
//          if (jumlahLuasKemasukan.compareTo(luasHakmilikAsal) > 0) {
//            result = ">1";
//          } else {
//            result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(kbpm.getKod()), kodUOM, hakmilik.getLuas(), hakmilik, kodRizab));
//          }
                    result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(kbpm.getKod()), kodUOM, hakmilik.getLuas(), hakmilik, kodRizab));
                } else {
                    result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(kbpm.getKod()), kodUOM, hakmilik.getLuas(), hakmilik, kodRizab));
                }
                /*validate luas tidak boleh lebih kecil dari 0*/
            } else if (hakmilik.getLuas().longValue() < 0) {
                result = "<1";
            } else {
                result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(kbpm.getKod()), kodUOM, hakmilik.getLuas(), hakmilik, kodRizab));
            }
            logger.debug("result kiraCukai : " + result);

        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution kiraCukaiKelompok() {
        String result = "";
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
        String luas = (String) getContext().getRequest().getParameter("luas");
        String kodRizab;
        logger.debug("kiraCukaiKelompok");
        logger.debug(":idhakmilik:" + idHakmilik);
        logger.debug(":kodUOM:" + kodUOM);

        BigDecimal luasHakmilik = new BigDecimal(luas);
        logger.debug(":luas:" + luasHakmilik);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);
        if (h != null && StringUtils.isNotBlank(idHakmilik) && StringUtils.isNotBlank(kodUOM) && StringUtils.isNotBlank(luasHakmilik.toString())) {
            logger.debug("kodTanah : " + h.getKegunaanTanah().getKod());
            logger.debug("kodbpm : " + h.getBandarPekanMukim().getKod());
            if (h.getRizab() != null) {
                logger.debug("kodRizab : " + h.getRizab().getKod());
                kodRizab = String.valueOf(h.getRizab().getKod());
            } else {
                kodRizab = null;
            }
            result = String.valueOf(calcTax.calculate(h.getKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, luasHakmilik, h, kodRizab));
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution agihSamaRata() {
        logger.debug("start agih sama rata");
        String results = "0";
        String DELIM = "__^$__";

        Fraction sumAllPemohon = Fraction.ONE;
        Fraction samaRata = Fraction.ZERO;

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("idHakmilik:" + hakmilik.getIdHakmilik());
        logger.debug("idPermohonan:" + idPermohonan);
//        List<PermohonanPihak> mohonPihakListBaru = new ArrayList();
//        mohonPihakListBaru = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//
//        if (mohonPihakListBaru != null && mohonPihakListBaru.size() > 0) {
//            logger.debug("size pihak:" + mohonPihakListBaru.size());
//            samaRata = sumAllPemohon.divide(mohonPihakListBaru.size());
//
//            for (PermohonanPihak pp : mohonPihakListBaru) {
//                pp.setSyerPembilang(samaRata.getNumerator());
//                pp.setSyerPenyebut(samaRata.getDenominator());
//            }
//        }
//
//        permohonanPihakService.saveOrUpdate(mohonPihakListBaru);

        List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
        List<HakmilikPihakBerkepentingan> hakmilikPihakListBaru = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilik);

        if (hakmilikPihakListBaru != null && hakmilikPihakListBaru.size() > 0) {
            logger.debug("size pihak:" + hakmilikPihakListBaru.size());
            samaRata = sumAllPemohon.divide(hakmilikPihakListBaru.size());

            for (HakmilikPihakBerkepentingan hpb : hakmilikPihakListBaru) {
                hpb.setSyerPembilang(samaRata.getNumerator());
                hpb.setSyerPenyebut(samaRata.getDenominator());
                hpb.setJumlahPembilang(samaRata.getNumerator());
                hpb.setJumlahPenyebut(samaRata.getDenominator());
                senarai.add(hpb);
            }
        }

        //permohonanPihakService.saveOrUpdate(mohonPihakListBaru);
        hakmilikPihakKepentinganService.saveList(senarai);
        StringBuilder s = new StringBuilder();
        s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
        results = s.toString();
        logger.debug(results);

        //return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        return new StreamingResolution("text/plain", results);
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution resetUrusan() {
        //idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.debug("start reset urusan");
        logger.debug("idPermohonan :" + idPermohonan);
        logger.debug("idHakmilik :" + idHakmilik);
//        if (StringUtils.isNotBlank(idHakmilik)) {
        //List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
        //Permohonan p = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
//        HakmilikPermohonan hp = lhp.get(0);
        for (HakmilikPermohonan hakmilikPermohonan : lhp) {
            List<HakmilikSebelumPermohonan> lhsp = hakmilikPermohonan.getSenaraiHakmilikSebelum();
            logger.debug("size urusan hakmilik asal : " + lhsp.size());
            List<HakmilikUrusan> lhutemp = new ArrayList();
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
            KodCawangan caw = pengguna.getKodCawangan();
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
                Hakmilik hakmilikSebelum = hakmilikSebelumPermohonan.getHakmilik();
                logger.debug("hakmilik urusan bagi hakmilik :" + hakmilikSebelum.getIdHakmilik());
                List<HakmilikUrusan> senaraiHakmilikurusan = hUService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hakmilikSebelum.getIdHakmilik());
                logger.debug("size hakmilik urusan lama : " + senaraiHakmilikUrusan.size());
                for (HakmilikUrusan hakmilikUrusan : senaraiHakmilikurusan) {
                    if (hakmilikUrusan.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                        continue;
                    }

                    List<HakmilikUrusan> lhu = hUService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(idHakmilik);
                    if (lhu.size() > 0) {
                        hUService.deleteHakmilikUrusan(lhu);
                    }

                    HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                    hakmilikUrusanBaru.setHakmilik(hakmilikDAO.findById(idHakmilik));
                    hakmilikUrusanBaru.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
                    hakmilikUrusanBaru.setKodUrusan(hakmilikUrusan.getKodUrusan());
                    hakmilikUrusanBaru.setAktif(hakmilikUrusan.getAktif());
                    hakmilikUrusanBaru.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
                    hakmilikUrusanBaru.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
                    hakmilikUrusanBaru.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
                    hakmilikUrusanBaru.setLuasTerlibat(hakmilikUrusan.getLuasTerlibat());
                    hakmilikUrusanBaru.setCawangan(hakmilikUrusan.getCawangan());
                    hakmilikUrusanBaru.setDaerah(hakmilikUrusan.getDaerah());
                    hakmilikUrusanBaru.setCukaiLama(hakmilikUrusan.getCukaiLama());
                    hakmilikUrusanBaru.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
                    hakmilikUrusanBaru.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
                    hakmilikUrusanBaru.setNoRujukan(hakmilikUrusan.getNoRujukan());
                    hakmilikUrusanBaru.setTempohTahun(hakmilikUrusan.getTempohTahun());
                    hakmilikUrusanBaru.setTempohBulan(hakmilikUrusan.getTempohBulan());
                    hakmilikUrusanBaru.setTempohHari(hakmilikUrusan.getTempohHari());
                    hakmilikUrusanBaru.setTarikhRujukan(hakmilikUrusan.getTarikhRujukan());
                    hakmilikUrusanBaru.setItem(hakmilikUrusan.getItem());
                    hakmilikUrusanBaru.setInfoAudit(ia);
                    hUService.saveOrUpdateUrusan(hakmilikUrusanBaru);

                }
            }

        }
//        List<HakmilikSebelumPermohonan> lhsp = hp.getSenaraiHakmilikSebelum();
//        logger.debug("size urusan hakmilik asal : " + lhsp.size());
//        List<HakmilikUrusan> lhutemp = new ArrayList();
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();
//        KodCawangan caw = pengguna.getKodCawangan();
//        Date now = new Date();
//        InfoAudit ia = new InfoAudit();
//        ia.setDimasukOleh(pengguna);
//        ia.setTarikhMasuk(now);
//        for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
//            Hakmilik hakmilikSebelum = hakmilikSebelumPermohonan.getHakmilik();
//            logger.debug("hakmilik urusan bagi hakmilik :" + hakmilikSebelum.getIdHakmilik());
//            List<HakmilikUrusan> senaraiHakmilikurusan = hUService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hakmilikSebelum.getIdHakmilik());
//            logger.debug("size hakmilik urusan lama : " + senaraiHakmilikUrusan.size());
//            for (HakmilikUrusan hakmilikUrusan : senaraiHakmilikurusan) {
//                if (hakmilikUrusan.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
//                    continue;
//                }
//
//                List<HakmilikUrusan> lhu = hUService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(idHakmilik);
//                if (lhu.size() > 0) {
//                    hUService.deleteHakmilikUrusan(lhu);
//                }
//
//                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
//                hakmilikUrusanBaru.setHakmilik(hakmilikDAO.findById(idHakmilik));
//                hakmilikUrusanBaru.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
//                hakmilikUrusanBaru.setKodUrusan(hakmilikUrusan.getKodUrusan());
//                hakmilikUrusanBaru.setAktif(hakmilikUrusan.getAktif());
//                hakmilikUrusanBaru.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
//                hakmilikUrusanBaru.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
//                hakmilikUrusanBaru.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
//                hakmilikUrusanBaru.setLuasTerlibat(hakmilikUrusan.getLuasTerlibat());
//                hakmilikUrusanBaru.setCawangan(hakmilikUrusan.getCawangan());
//                hakmilikUrusanBaru.setDaerah(hakmilikUrusan.getDaerah());
//                hakmilikUrusanBaru.setCukaiLama(hakmilikUrusan.getCukaiLama());
//                hakmilikUrusanBaru.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
//                hakmilikUrusanBaru.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
//                hakmilikUrusanBaru.setNoRujukan(hakmilikUrusan.getNoRujukan());
//                hakmilikUrusanBaru.setTempohTahun(hakmilikUrusan.getTempohTahun());
//                hakmilikUrusanBaru.setTempohBulan(hakmilikUrusan.getTempohBulan());
//                hakmilikUrusanBaru.setTempohHari(hakmilikUrusan.getTempohHari());
//                hakmilikUrusanBaru.setInfoAudit(ia);
//                hUService.saveOrUpdateUrusan(hakmilikUrusanBaru);
//
//            }
//        }
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution resetPihak() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("idHakmilik : " + idHakmilik);
        if (StringUtils.isNotBlank(idHakmilik)) {
            HakmilikPermohonan hp = regService.searchMohonHakmilikObject(idHakmilik, idPermohonan);
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
            KodCawangan caw = pengguna.getKodCawangan();
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
//            List <HakmilikSebelumPermohonan> senaraihsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
            HakmilikSebelumPermohonan hsb = hp.getSenaraiHakmilikSebelum().get(0);
            List<HakmilikPihakBerkepentingan> senaraiHPK = hsb.getHakmilik().getSenaraiPihakBerkepentingan();
            for (HakmilikPihakBerkepentingan hpk : senaraiHPK) {
                if (hpk == null || hpk.getAktif() == 'T') {
                    continue;
                }

                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                hpb.setHakmilik(hm);
                hpb.setCawangan(hm.getCawangan());
                hpb.setPihakCawangan(hpk.getPihakCawangan());
                hpb.setJenis(hpk.getJenis());
                hpb.setSyerPembilang(hpk.getSyerPembilang());
                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                hpb.setPerserahan(hpk.getPerserahan());
                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                hpb.setKaveatAktif(hpk.getKaveatAktif());
                hpb.setAktif(hpk.getAktif());
                hpb.setPihak(hpk.getPihak());
                hpb.setInfoAudit(ia);
                hpkService.save(hpb);

            }
        }
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution semakSyer() {

        String result = "Pembahagian tanah berjaya.";
        int i = 0;
        List<HakmilikPihakBerkepentingan> pihakBerkepentingan = new ArrayList<HakmilikPihakBerkepentingan>();
//        List<PermohonanPihak> mohonPihak = new ArrayList<PermohonanPihak>();
//        for (PermohonanPihak permohonanPihak : mohonPihakList) {
//            Fraction f = new Fraction(Integer.parseInt(syerPembilang[i]), Integer.parseInt(syerPenyebut[i]));
//            logger.debug("f" + i + ":" + f);
//            sum = sum.add(f);
//            logger.debug("syerPembilang" + i + ":" + syerPembilang[i]);
//            logger.debug("syerPenyebut" + i + ":" + syerPenyebut[i]);
//            permohonanPihak.setSyerPembilang(Integer.parseInt(syerPembilang[i]));
//            permohonanPihak.setSyerPenyebut(Integer.parseInt(syerPenyebut[i]));
//            mohonPihak.add(permohonanPihak);
//            i = i + 1;
//        }
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : pihakKepentinganList) {
            Fraction f = new Fraction(Integer.parseInt(syerPembilang[i]), Integer.parseInt(syerPenyebut[i]));
            logger.debug("f" + i + ":" + f);
            sum = sum.add(f);
            logger.debug("syerPembilang" + i + ":" + syerPembilang[i]);
            logger.debug("syerPenyebut" + i + ":" + syerPenyebut[i]);
            hakmilikPihakBerkepentingan.setSyerPembilang(Integer.parseInt(syerPembilang[i]));
            hakmilikPihakBerkepentingan.setSyerPenyebut(Integer.parseInt(syerPenyebut[i]));
            pihakBerkepentingan.add(hakmilikPihakBerkepentingan);
            i = i + 1;
        }

        logger.debug("sum :" + sum);
        if (sum.doubleValue() == 1) {
            //permohonanPihakService.saveOrUpdate(mohonPihak);
            hakmilikPihakKepentinganService.save(pihakBerkepentingan);
        } else if (sum.doubleValue() < 1) {
            result = "Jumlah pembahagian tanah tidak mencukupi";
        } else {
            result = "Jumlah pembahagian tanah melebihi daripada 1";
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution deleteHakmilikAsal() {
        String idHakmilikAsal = getContext().getRequest().getParameter("idHakmilikAsal");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("idHakmilikAsal : " + idHakmilikAsal);
        logger.debug("idHakmilik :" + idHakmilik);

        List<HakmilikUrusan> lhu = new ArrayList();
        List<PermohonanPihak> lpp = new ArrayList();

        if (idHakmilikAsal != null) {
            HakmilikAsalPermohonan hap = mohonHakmilikAsalDA0.findById(Long.parseLong(idHakmilikAsal));
            //List<HakmilikAsalPermohonan> listhap = regService.searchMohonHakmilikAsalByID(Long.parseLong(idHakmilikAsal));
            if (hap != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {
                    lhu = hUService.findHakmilikUrusanByIdHakmilikAndIdPermohonan(idHakmilik, idPermohonan);
                    logger.debug("DELETING " + lhu.size() + " HAKMILIK_URUSAN FOR " + idHakmilik + "ID_MOHON " + idPermohonan);
                    if (lhu.size() > 0) {
                        hUService.deleteHakmilikUrusan(lhu);
                    }
                    //regService.deleteMohonHakmilikAsal(listhap);
                    regService.deleteMohonHakmilikAsal(hap);
//                    lpp = ppService.getSenaraiPmohonPihakByHakmilik(idPermohonan, idHakmilik);
//                    if (lpp.size() > 0) {
//                        ppService.delete(lpp);
//                    }
                    tx.commit();
                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }
            }

        }
        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deletePemohon() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        if (!StringUtils.isEmpty(idPemohon)) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {
                    pemohonService.delete(pemohon);
                    tx.commit();
                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }
            }
        }
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deleteMohonPihak() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (!StringUtils.isEmpty(idMohonPihak)) {
            PermohonanPihak mohonPihak = permohonanPihakService.findById(idMohonPihak);
            if (mohonPihak != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {
                    permohonanPihakService.delete(mohonPihak);
                    tx.commit();
                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }
            }
        }
        addSimpleMessage("Data Telah Berjaya diHapuskan");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deleteHakmilikUrusan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idUrusan = getContext().getRequest().getParameter("idUrusan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("idUrusan : " + idUrusan);
        logger.debug("idHakmilik :" + idHakmilik);

        if (!StringUtils.isEmpty(idUrusan)) {
            HakmilikUrusan hu = hUService.findById(Long.parseLong(idUrusan));
            if (hu != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {

                    List<HakmilikPihakBerkepentingan> hpb = hakmilikPihakKepentinganService.findAllHakmilikPihakByIdUrusan(hu, hu.getHakmilik());

                    for (HakmilikPihakBerkepentingan hpb1 : hpb) {
                        hpb1.setPerserahan(null);
                        hakmilikPihakKepentinganService.save(hpb1);
                    }

                    hUService.deleteHakmilikUrusan(hu);
                    tx.commit();
                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }
            }
            PermohonanRujukanLuar mrl = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
            if (mrl != null) {
                permohonanRujukanLuarService.delete(mrl);
            }
        }

        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deleteHakmilik() {
        String idMh = getContext().getRequest().getParameter("idMh");
        List<PermohonanPihak> lpp = new ArrayList();
        if (idMh != null) {
            HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
            if (hp != null) {
                logger.debug("DELETING MOHON HAKMILIK");
                regService.deleteMohonHakmilik(hp);
                lpp = ppService.getSenaraiPmohonPihakByHakmilik(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                logger.debug("DELETING " + lpp.size() + " HAKMILIK_PIHAK FOR " + idHakmilik);
                if (lpp.size() > 0) {
                    ppService.delete(lpp);
                }
                //regService.deleteHakmilik(hp.getHakmilik());
            }
        }
        rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution deleteHakmilikSblm() {
        String idHakmilikSblm = getContext().getRequest().getParameter("idHakmilikSblm");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        logger.debug("idHakmilikSblm : " + idHakmilikSblm);
        logger.debug("idHakmilik :" + idHakmilik);
        List<HakmilikUrusan> lhu = new ArrayList();
        List<HakmilikPihakBerkepentingan> lhp = new ArrayList();
        List<PermohonanPihak> lpp = new ArrayList();

        if (idHakmilikSblm != null) {
            HakmilikSebelumPermohonan hsp = mohonHakmilikSblmDA0.findById(Long.parseLong(idHakmilikSblm));
//            List<HakmilikSebelumPermohonan> listhsp = regService.searchMohonHakmilikSblmByID(Long.parseLong(idHakmilikSblm));
//            logger.debug("listhsp size:" + listhsp.size());
            if (hsp != null) {
                Transaction tx = sessionProvider.get().beginTransaction();
                tx.begin();
                try {

                    //lhu = hUService.findHakmilikUrusanByIdHakmilik(idHakmilik);
                    lhu = hUService.findHakmilikUrusanByIdHakmilikAndIdPermohonan(idHakmilik, idPermohonan);
                    logger.debug("DELETING " + lhu.size() + " HAKMILIK_URUSAN FOR " + idHakmilik + "ID_MOHON " + idPermohonan);
                    if (lhu.size() > 0) {
                        hUService.deleteHakmilikUrusan(lhu);
                    }
//                    lpp = ppService.getSenaraiPmohonPihakByHakmilik(idPermohonan, idHakmilik);
//                    logger.debug("DELETING " + lpp.size() + " HAKMILIK_PIHAK FOR " + idHakmilik);
//                    if (lpp.size() > 0) {
//                        ppService.delete(lpp);
//                    }
//                    lhp = hPBService.findHakmilikAllPihakActiveByHakmilik(hakmilik);
//                    logger.debug("DELETING " + lhp.size() + " HAKMILIK_PIHAK FOR " + idHakmilik);
//                    if (lhp.size() > 0) {
//                        hPBService.deleteHakmilikPihakBerkepentingan(lhp);
//                    }
//                    regService.deleteMohonHakmilikSblm(listhsp);
                    mohonHakmilikSblmDA0.delete(hsp);
                    tx.commit();
                } catch (Exception e) {
                    tx.rollback();
                    Throwable t = e;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                    return null;
                }
            }
        }
        // rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanHakmilikAsal() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        logger.debug("saving hakmilik asal");
        logger.debug("idHakmilik : " + idHakmilik);
        logger.debug("idHakmilik Asal  :" + sejarahHakmilik.getIdHakmilik());
        hakmilik = hakmilikDAO.findById(sejarahHakmilik.getIdHakmilik());

        sejarahHakmilik = sejarahHakmilikDAO.findById(sejarahHakmilik.getIdHakmilik());

        Hakmilik hb = new Hakmilik();
        hb = hakmilikDAO.findById(sejarahHakmilik.getIdHakmilik());

        hakmilikPermohonanList = regService.searchMohonHakmilik(idHakmilik, idPermohonan);
        HakmilikPermohonan ph = new HakmilikPermohonan();
        ph = hakmilikPermohonanList.get(0);

        // mohonHakmilikAsal.setHakmilikPermohonan(ph);
        // if (hakmilik != null) {
        //     mohonHakmilikAsal.setHakmilik(hakmilik);
        // }
        //if (sejarahHakmilik != null) {
        //    mohonHakmilikAsal.setHakmilikSejarah(sejarahHakmilik);
        //}
        List<HakmilikAsalPermohonan> hapList = new ArrayList();
        if (hakmilik.getSenaraiHakmilikAsal().size() > 0) {

            HakmilikAsalPermohonan mohonHakmilikAsal = new HakmilikAsalPermohonan();
            mohonHakmilikAsal.setHakmilikPermohonan(ph);
            mohonHakmilikAsal.setHakmilik(hakmilikDAO.findById(hakmilik.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
            mohonHakmilikAsal.setHakmilikSejarah(hakmilik.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            mohonHakmilikAsal.setInfoAudit(info);
            hapList.add(mohonHakmilikAsal);

            for (int n = 0; n < hakmilik.getSenaraiHakmilikAsal().size(); n++) {
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(ph);
                hsp.setHakmilik(hakmilik.getSenaraiHakmilikAsal().get(n).getHakmilik());
                hsp.setHakmilikSejarah(hakmilik.getIdHakmilik());
                hsp.setInfoAudit(info);
                mohonHakmilikSblmDA0.save(hsp);
            }

        } else {

            HakmilikAsalPermohonan mohonHakmilikAsal = new HakmilikAsalPermohonan();
            mohonHakmilikAsal.setHakmilikPermohonan(ph);
            mohonHakmilikAsal.setHakmilik(hakmilik);
            mohonHakmilikAsal.setHakmilikSejarah(sejarahHakmilikDAO.findById(hakmilik.getIdHakmilik()).getIdHakmilik());
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            mohonHakmilikAsal.setInfoAudit(info);
            hapList.add(mohonHakmilikAsal);

        }
        regService.simpanMohonHakmilikAsal(hapList);

        /*TODO:COPY TEMPOH AND TARIKH PAJAKAN FROM HAKMILIK ASAL*/
        if (sejarahHakmilik != null || hakmilik != null) {
//            if (!p.getKodUrusan().getKod().equals("HSBM") || !p.getKodUrusan().getKod().equals("HKBM")
//                    || !p.getKodUrusan().getKod().equals("HKPB") || !p.getKodUrusan().getKod().equals("HSPB")) {
            if (!p.getKodUrusan().getKod().equals("HSBM") || !p.getKodUrusan().getKod().equals("HKBM")) {
                logger.debug(":::START COPY TEMPOH / TARIKH PAJAKAN / SEKATAN / SYARAT NYATA / LOKASI / SYIT / KATEGORI TANAH / KEGUNAAN TANAH FROM HAKMILIK ASAL:::");
                List<HakmilikPermohonan> lhp = new ArrayList();
                List<Hakmilik> lh = new ArrayList();
                lhp = p.getSenaraiHakmilik();
                if (lhp.size() > 0) {
                    logger.debug(lhp.size() + " HAKMILIK FOUND");
                }
                for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                    Hakmilik h = hakmilikPermohonan.getHakmilik();
                    InfoAudit ia = h.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    h.setTempohPegangan(sejarahHakmilik.getTempohPegangan());
                    h.setLokasi(sejarahHakmilik.getLokasi());
                    h.setSekatanKepentingan(sejarahHakmilik.getSekatanKepentingan());
                    h.setSyaratNyata(sejarahHakmilik.getSyaratNyata());
                    h.setNoLitho(sejarahHakmilik.getNoLitho());
                    h.setKategoriTanah(sejarahHakmilik.getKategoriTanah());
                    h.setKegunaanTanah(sejarahHakmilik.getKegunaanTanah());
                    h.setPegangan(sejarahHakmilik.getPegangan());
                    h.setInfoAudit(ia);
                    lh.add(h);
                }
                regService.simpanHakmilikList(lh);

                logger.debug(":::START COPY HAKMILIK PIHAK FROM HAKMILIK ASAL:::");
                List<HakmilikPihakBerkepentingan> lhpb = new ArrayList();
                lhpb = hPBService.findHakmilikAllPihakActiveByHakmilik(hb);
                if (lhpb.size() > 0) {
                    logger.debug(lhpb.size() + " PIHAK FOUND");
                }

//                List<PermohonanPihak> lpp = new ArrayList();
//                for (int i = 0; i < lhpb.size(); i++) {
//                    PermohonanPihak pp = new PermohonanPihak();
//                    InfoAudit ia = pp.getInfoAudit();
//                    if (ia != null) {
//                        info.setDimasukOleh(peng);
//                        info.setTarikhMasuk(new java.util.Date());
//                    } else {
//                        info.setDiKemaskiniOleh(peng);
//                        info.setTarikhKemaskini(new java.util.Date());
//                    }
//                    pp.setHakmilik(hakmilikDAO.findById(idHakmilik));
//                    pp.setPihakCawangan(lhpb.get(i).getPihakCawangan());
//                    pp.setPihak(lhpb.get(i).getPihak());
//                    pp.setCawangan(hakmilik.getCawangan());
//                    pp.setJenis(lhpb.get(i).getJenis());
//                    pp.setSyerPembilang(lhpb.get(i).getSyerPembilang());
//                    pp.setSyerPenyebut(lhpb.get(i).getSyerPenyebut());
//                    pp.setPermohonan(p);
//                    pp.setInfoAudit(info);
//                    lpp.add(pp);
//                }
//                ppService.saveOrUpdate(lpp);
                for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhpb) {
                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    InfoAudit ia = hpb.getInfoAudit();
                    if (ia != null) {
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                    } else {
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                    }
                    hpb.setHakmilik(hakmilikDAO.findById(idHakmilik));
                    hpb.setCawangan(hakmilikDAO.findById(idHakmilik).getCawangan());
                    hpb.setPihakCawangan(hakmilikPihakBerkepentingan.getPihakCawangan());
                    hpb.setJenis(hakmilikPihakBerkepentingan.getJenis());
                    hpb.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
                    hpb.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
                    hpb.setPerserahan(hakmilikPihakBerkepentingan.getPerserahan());
                    hpb.setPerserahanKaveat(hakmilikPihakBerkepentingan.getPerserahanKaveat());
                    hpb.setKaveatAktif(hakmilikPihakBerkepentingan.getKaveatAktif());
                    hpb.setAktif(hakmilikPihakBerkepentingan.getAktif());
                    hpb.setPihak(hakmilikPihakBerkepentingan.getPihak());
                    hpb.setInfoAudit(info);
                    hakmilikPihakKepentinganService.save(hpb);
                }

                List<HakmilikUrusan> lhu = new ArrayList();
                List<HakmilikUrusan> lhuBaru = new ArrayList();
                logger.debug("START COPY HAKMILIK URUSAN FROM HAKMILIK ASAL");
                //lhu = hUService.findHakmilikUrusanByIdHakmilik(sejarahHakmilik.getIdHakmilik());
                lhu = hUService.findHakmilikUrusanActiveByIdHakmilik(sejarahHakmilik.getIdHakmilik());
                if (lhu.size() > 0) {
                    logger.debug(lhu.size() + " HAKMILIK  FOUND");
                }
                for (HakmilikUrusan hakmilikUrusan : lhu) {
                    HakmilikUrusan u = new HakmilikUrusan();
                    u.setHakmilik(hakmilikDAO.findById(idHakmilik));
                    u.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
                    u.setKodUrusan(hakmilikUrusan.getKodUrusan());
                    u.setAktif(hakmilikUrusan.getAktif());
                    u.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
                    u.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
                    u.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
                    u.setCawangan(hakmilikUrusan.getCawangan());
                    //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
                    u.setCukaiLama(hakmilikUrusan.getCukaiLama());
                    u.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
                    u.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
                    u.setNoRujukan(hakmilikUrusan.getNoRujukan());
                    u.setTarikhRujukan(hakmilikUrusan.getTarikhRujukan());
                    u.setItem(hakmilikUrusan.getItem());
                    u.setInfoAudit(info);
                    lhuBaru.add(u);
                }
                hUService.saveOrUpdate(lhuBaru);

            }
        }

        //info.setDimasukOleh(peng);
        //info.setTarikhMasuk(new java.util.Date());
        //mohonHakmilikAsal.setInfoAudit(info);
        //regService.simpanMohonHakmilikAsal(mohonHakmilikAsal);
        addSimpleMessage("Kemasukan Data Berjaya");

        return new RedirectResolution(KemasukanPerincianHakmilikActionBean.class).addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
//        return new JSP("daftar/kemasukan_perincian_hakmilik.jsp")
//                .addParameter("tab", "true")
//                .addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanHakmilikSblm() {
        logger.debug("saving hakmilik sblm");
        logger.debug("idHakmilik :" + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        Hakmilik hb = new Hakmilik();
        hb = hakmilikDAO.findById(sejarahHakmilik.getIdHakmilik());
        sejarahHakmilik = sejarahHakmilikDAO.findById(sejarahHakmilik.getIdHakmilik());
        hakmilikPermohonanList = regService.searchMohonHakmilik(idHakmilik);
        HakmilikPermohonan ph = new HakmilikPermohonan();
        ph = hakmilikPermohonanList.get(0);
        mohonHakmilikSblm.setHakmilikPermohonan(ph);
        if (sejarahHakmilik != null) {
            mohonHakmilikSblm.setHakmilikSejarah(sejarahHakmilik.getIdHakmilik());
        }

        if (hb != null) {
            mohonHakmilikSblm.setHakmilik(hb);
        }
        if (sejarahHakmilik != null || hb != null) {
            if (!p.getKodUrusan().getKod().equals("HSBM") || !p.getKodUrusan().getKod().equals("HKBM")
                    || !p.getKodUrusan().getKod().equals("HKPB") || !p.getKodUrusan().getKod().equals("HSPB")) {

                List<HakmilikUrusan> lhu = new ArrayList();
                List<HakmilikUrusan> lhuBaru = new ArrayList();

                List<HakmilikPihakBerkepentingan> lhp = new ArrayList();
                List<HakmilikPihakBerkepentingan> lhpBaru = new ArrayList();

                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = peng.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());

                logger.debug(":::START COPY TEMPOH / TARIKH PAJAKAN / SEKATAN / SYARAT NYATA / LOKASI / SYIT / KATEGORI TANAH / KEGUNAAN TANAH FROM HAKMILIK ASAL:::");
                List<HakmilikPermohonan> lhpermohonan = new ArrayList();
                List<Hakmilik> lh = new ArrayList();
                lhpermohonan = p.getSenaraiHakmilik();
                if (lhp.size() > 0) {
                    logger.debug(lhp.size() + " HAKMILIK FOUND");
                }
                for (HakmilikPermohonan hakmilikPermohonan : lhpermohonan) {
                    Hakmilik h = hakmilikPermohonan.getHakmilik();
                    InfoAudit ia = h.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    h.setTempohPegangan(sejarahHakmilik.getTempohPegangan());
                    h.setLokasi(sejarahHakmilik.getLokasi());
                    h.setSekatanKepentingan(sejarahHakmilik.getSekatanKepentingan());
                    h.setSyaratNyata(sejarahHakmilik.getSyaratNyata());
                    h.setNoLitho(sejarahHakmilik.getNoLitho());
                    h.setKategoriTanah(sejarahHakmilik.getKategoriTanah());
                    h.setKegunaanTanah(sejarahHakmilik.getKegunaanTanah());
                    h.setPegangan(sejarahHakmilik.getPegangan());
                    h.setInfoAudit(ia);
                    lh.add(h);
                }
                regService.simpanHakmilikList(lh);

                logger.debug("COPYING HAKMILIK URUSAN");
                lhu = hUService.findHakmilikUrusanActiveByIdHakmilik(sejarahHakmilik.getIdHakmilik());
                for (HakmilikUrusan hakmilikUrusan : lhu) {
                    HakmilikUrusan u = new HakmilikUrusan();
                    u.setHakmilik(hakmilik);
                    u.setIdPerserahan(hakmilikUrusan.getIdPerserahan());
                    u.setKodUrusan(hakmilikUrusan.getKodUrusan());
                    u.setAktif('Y');
                    u.setTarikhBatal(hakmilikUrusan.getTarikhBatal());
                    u.setTarikhDaftar(hakmilikUrusan.getTarikhDaftar());
                    u.setKodUnitLuas(hakmilikUrusan.getKodUnitLuas());
                    u.setCawangan(hakmilikUrusan.getCawangan());
                    //u.setUrusanBatal(hakmilikUrusan.getUrusanBatal());
                    u.setCukaiLama(hakmilikUrusan.getCukaiLama());
                    u.setCukaiBaru(hakmilikUrusan.getCukaiBaru());
                    u.setFolderDokumen(hakmilikUrusan.getFolderDokumen());
                    u.setNoRujukan(hakmilikUrusan.getNoRujukan());
                    u.setTarikhRujukan(hakmilikUrusan.getTarikhRujukan());
                    u.setItem(hakmilikUrusan.getItem());
                    u.setInfoAudit(info);
                    lhuBaru.add(u);
                }
                hUService.saveOrUpdate(lhuBaru);
                logger.debug("COPYING HAKMILIKPIHAK");
                lhp = hPBService.findHakmilikAllPihakActiveByHakmilik(hb);
                if (lhp.size() > 0) {
                    logger.debug(lhp.size() + " PIHAK FOUND");
                }

                for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhp) {
                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    InfoAudit ia = hpb.getInfoAudit();
                    if (ia != null) {
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                    } else {
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                    }
                    hpb.setHakmilik(hakmilikDAO.findById(idHakmilik));
                    hpb.setCawangan(hakmilikDAO.findById(idHakmilik).getCawangan());
                    hpb.setPihakCawangan(hakmilikPihakBerkepentingan.getPihakCawangan());
                    hpb.setJenis(hakmilikPihakBerkepentingan.getJenis());
                    hpb.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
                    hpb.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
                    hpb.setPerserahan(hakmilikPihakBerkepentingan.getPerserahan());
                    hpb.setPerserahanKaveat(hakmilikPihakBerkepentingan.getPerserahanKaveat());
                    hpb.setKaveatAktif(hakmilikPihakBerkepentingan.getKaveatAktif());
                    hpb.setAktif(hakmilikPihakBerkepentingan.getAktif());
                    hpb.setPihak(hakmilikPihakBerkepentingan.getPihak());
                    hpb.setInfoAudit(info);
                    hakmilikPihakKepentinganService.save(hpb);
                }

//            for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : lhp) {
//                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
//                hpb.setHakmilik(hakmilik);
//                hpb.setAktif(hakmilikPihakBerkepentingan.getAktif());
//                hpb.setPihakCawangan(hakmilikPihakBerkepentingan.getPihakCawangan());
//                hpb.setJenis(hakmilikPihakBerkepentingan.getJenis());
//                hpb.setKaveatAktif(hakmilikPihakBerkepentingan.getKaveatAktif());
//                hpb.setPihak(hakmilikPihakBerkepentingan.getPihak());
//                hpb.setSyerPembilang(hakmilikPihakBerkepentingan.getSyerPembilang());
//                hpb.setSyerPenyebut(hakmilikPihakBerkepentingan.getSyerPenyebut());
//                hpb.setInfoAudit(info);
//                lhpBaru.add(hpb);
//            }
//            hPBService.save(lhpBaru);
            }
        }

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        mohonHakmilikSblm.setInfoAudit(info);
        regService.simpanMohonHakmilikSebelum(mohonHakmilikSblm);
        addSimpleMessage("Kemasukan Data Berjaya");

        return new RedirectResolution(KemasukanPerincianHakmilikActionBean.class).addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution searchHakmilikAsalByIDHakmilikAsal() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.debug("IDHakmilik Asal :" + idAsal);
        logger.debug("IDHakmilik :" + idHakmilik);
        if (idAsal != null) {
//            listHakmilik = regService.searchSejHakmilik(idAsal);
//            if (!listHakmilik.isEmpty()) {
//                sejarahHakmilik = listHakmilik.get(0);
//            } else {
            Hakmilik hb = new Hakmilik();
            hb = hakmilikDAO.findById(idAsal);

            if (hb != null) {
                sejarahHakmilik.setIdHakmilik(hb.getIdHakmilik());
                sejarahHakmilik.setTarikhDaftar(hb.getTarikhDaftar());
            } else {
                addSimpleError("Tiada Hakmilik Dijumpai");
            }

            //}
        } else {
            addSimpleError("Sila Masukkan ID Hakmilik");
        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/daftar/common/carian_hakmilik_asal.jsp").addParameter("popup", "true");
    }

    public Resolution searchHakmilikSebelumByIDHakmilikSebelum() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.debug("IDHakmilik Sebelum :" + idSebelum);
        if (idSebelum != null) {
            //TO DO SEARCH FROM HAKMILIK SEBELUM ? HAKMILIK?
//             Long id = Long.parseLong(idSebelum);
//            listHakmilikSebelumByIDHakmilikSebelum = regService.searchHakmilikSebelumByIDHakmilikSebelum(id);
//            listHakmilik = regService.searchSejHakmilik(idSebelum);

//            if (!listHakmilik.isEmpty()) {
//                sejarahHakmilik = listHakmilik.get(0);
//            } else {
            Hakmilik hb = new Hakmilik();
            hb = hakmilikDAO.findById(idSebelum);

            if (hb != null) {
                //COPY HAKMILIK_URUSAN AND HAKMILIK_PIHAK
                sejarahHakmilik.setIdHakmilik(hb.getIdHakmilik());
                //HakmilikUrusan hu = new HakmilikUrusan();
            } else {
                addSimpleError("Tiada Hakmilik Dijumpai");
            }
//            }
        } else {
            addSimpleError("Sila Masukkan ID Hakmilik");
        }
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/daftar/common/carian_hakmilik_sebelum.jsp").addParameter("popup", "true");
    }

    public Resolution deleteMultipleMohonHakmilik() {
        String[] id = getContext().getRequest().getParameterValues("idMohonHakmilik");
        for (String string : id) {
            String idMh = string;
            List<PermohonanPihak> lpp = new ArrayList();
            if (idMh != null) {
                HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                if (hp != null) {
                    logger.debug("DELETING MOHON HAKMILIK");
                    List<HakmilikAsalPermohonan> listMohonHakmilikAsal = regService.searchMohonHakmilikAsalByID(Long.parseLong(idMh));
                    List<HakmilikSebelumPermohonan> listMohonHakmilikSblm = regService.searchMohonHakmilikSblmByID(Long.parseLong(idMh));

                    if (listMohonHakmilikAsal.size() > 0) {
                        regService.deleteMohonHakmilikAsal(listMohonHakmilikAsal);
                    }

                    if (listMohonHakmilikSblm.size() > 0) {
                        regService.deleteMohonHakmilikSblm(listMohonHakmilikSblm);
                    }

                    regService.deleteMohonHakmilik(hp);
                    lpp = ppService.getSenaraiPmohonPihakByHakmilik(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                    logger.debug("DELETING " + lpp.size() + " HAKMILIK_PIHAK FOR " + idHakmilik);
                    if (lpp.size() > 0) {
                        ppService.delete(lpp);
                    }

                    //regService.deleteHakmilik(hp.getHakmilik());
                }
            }

        }

        rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution deleteMultipleHakmilikPihak() {
        String[] id = getContext().getRequest().getParameterValues("idHakmilikPihak");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        for (String string : id) {
            String idMh = string;
            if (idMh != null) {
                //HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                HakmilikPihakBerkepentingan hpb = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idMh));
                if (hpb != null) {
                    logger.debug("DELETING HAKMILIK PIHAK");
                    List<HakmilikWaris> hw = hakmilikWarisService.getSenaraiWarisByIdHakmilikPihak(String.valueOf(hpb.getIdHakmilikPihakBerkepentingan()));

                    if (!hw.isEmpty()) {
                        for (HakmilikWaris waris : hw) {
                            hakmilikWarisdao.delete(waris);
                        }
                    }
                    regService.deletePihakBerkepentingan(hpb);

//                    if (StringUtils.isNotBlank(idHakmilik)) {
//                        List<HakmilikPihakBerkepentingan> lpb = regService.searchPihakBerKepentinganPemilik(idHakmilik);
//                        List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
//
//                        int pbsize = 0;
//                        pbsize = lpb.size();
//
//                        Fraction sumAllPemohon = Fraction.ONE;
//                        Fraction samaRata = Fraction.ZERO;
//
//                        if (lpb != null && lpb.size() > 0) {
//                            logger.debug("size pihak:" + lpb.size());
//                            samaRata = sumAllPemohon.divide(lpb.size());
//
//                            for (HakmilikPihakBerkepentingan hpbaru : lpb) {
//                                hpbaru.setSyerPembilang(samaRata.getNumerator());
//                                hpbaru.setSyerPenyebut(samaRata.getDenominator());
//                                senarai.add(hpbaru);
//                            }
//                            hakmilikPihakKepentinganService.saveList(senarai);
//                        }
//                    }
                }
            }

        }

        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution deleteMultipleUrusan() {
        String[] id = getContext().getRequest().getParameterValues("idUrusan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        for (String string : id) {
            String idMh = string;
            if (idMh != null) {
                //HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                //HakmilikPihakBerkepentingan hpb = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idMh));
                HakmilikUrusan hl = hakmilikUrusanDAO.findById(Long.parseLong(idMh));
                if (hl != null) {
                    logger.debug("DELETING MULTIPLE HAKMILIK URUSAN");
                    List<HakmilikPihakBerkepentingan> hpb = hakmilikPihakKepentinganService.findAllHakmilikPihakByIdUrusan(hl, hl.getHakmilik());

                    for (HakmilikPihakBerkepentingan hpb1 : hpb) {
                        hpb1.setPerserahan(null);
                        hakmilikPihakKepentinganService.save(hpb1);
                    }
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        delpemohon(idMh, idHakmilik);
                        delMohonHakmilik(idMh, idHakmilik);
                        delMohonPihak(idMh, idHakmilik);
                        delMRL(idMh, idHakmilik);
                        delMHB(idMh, idHakmilik);
                        hl.setAktif('T');
                        hakmilikUrusanService.saveOrUpdateUrusan(hl);
                    } else {
                        delMRL(idMh, idHakmilik);
                        delMohonHakmilik(idMh, idHakmilik);
                        delMHB(idMh, idHakmilik);
                        hl.setAktif('T');
                        hakmilikUrusanService.saveOrUpdateUrusan(hl);
                    }
                }
            }
        }
        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    private void delpemohon(String idUrusan, String idHakmilik) {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
        Pemohon pmhn = pemohonService.findPemohonByidmohonAndIdHakmilik(mohon, hu.getHakmilik());
        if (pmhn != null) {
            pemohonService.delete(pmhn);
        }
    }

    private void delMohonHakmilik(String idUrusan, String idHakmilik) {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
        HakmilikPermohonan mohonHakmilik = pService.findByIdHakmilikIdPermohonan(mohon.getIdPermohonan(), idHakmilik);
        logger.info(mohonHakmilik);
        if (mohonHakmilik != null) {
            hakmilikPermohonanDAO.delete(mohonHakmilik);
        }
    }

    private void delMohonPihak(String idUrusan, String idHakmilik) {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
        List<PermohonanPihak> senaraiMohonPihak = pService.findMohonPihakByIdMohonAndIdHakmilik(mohon.getIdPermohonan(), idHakmilik);
//       for (PermohonanPihak mohonPihak : senaraiMohonPihak){
        logger.info(senaraiMohonPihak);
        permohonanPihakService.delete(senaraiMohonPihak);
//        }
    }

    private void delMRL(String idUrusan, String idHakmilik) {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
        PermohonanRujukanLuar mlr = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, mohon.getIdPermohonan());
        logger.info(mlr);
        if (mlr != null) {
            permohonanRujukanLuarService.delete(mlr);
        }
    }
    
    private void delMHB(String idUrusan, String idHakmilik) {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
        //PermohonanPembetulanHakmilik mhb = pService.findByidPidH(mohon.getIdPermohonan(),idHakmilik);
        List<PermohonanPembetulanHakmilik> mhb = pService.findByidPidH2(mohon.getIdPermohonan(),idHakmilik);
        logger.info("IDHAKMILIK " + idHakmilik);
        logger.info("IDMOHON " + mohon.getIdPermohonan());
        logger.info(mhb);
        if (mhb != null) {
            pService.deleteListUrusanHakmilikBetul(mhb);
        }
    }

    private void simpanMohonHakmilik(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        String idMohonHakmilikLama = hu.getIdPerserahan();
        String idHakmilikLama = hu.getHakmilik().getIdHakmilik();

        HakmilikPermohonan hakmilikPermohonanLama = regService.searchMohonHakmilikObject(idHakmilikLama, idMohonHakmilikLama);
        List<HakmilikPermohonan> senaraiHPLama = regService.searchEditMohonHakmilik(idMohonHakmilikLama, hakmilik.getIdHakmilik());
        if (senaraiHPLama.size() <= 0) {
            if (!hakmilikPermohonanLama.equals(null)) {
                HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
                hakmilikPermohonanBaru.setHakmilik(hakmilik);
                hakmilikPermohonanBaru.setPermohonan(hakmilikPermohonanLama.getPermohonan());
                hakmilikPermohonanBaru.setLuasTerlibat(hakmilikPermohonanLama.getLuasTerlibat());
                hakmilikPermohonanBaru.setKodUnitLuas(hakmilikPermohonanLama.getKodUnitLuas());
                hakmilikPermohonanBaru.setBandarPekanMukimBaru(hakmilikPermohonanLama.getBandarPekanMukimBaru());
                hakmilikPermohonanBaru.setKodHakmilik(hakmilikPermohonanLama.getKodHakmilik());
                hakmilikPermohonanBaru.setLot(hakmilikPermohonanLama.getLot());
                hakmilikPermohonanBaru.setNoLot(hakmilikPermohonanLama.getNoLot());
                hakmilikPermohonanBaru.setDokumen1(hakmilikPermohonanLama.getDokumen1());
                hakmilikPermohonanBaru.setDokumen4(hakmilikPermohonanLama.getDokumen4());
                hakmilikPermohonanBaru.setInfoAudit(info);
                hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonanBaru);

            }
        }

    }

    private void simpanMohonHakmilik2(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        HakmilikPermohonan hmpLama = hakmilikPermohonanService.findIdhakmilikbyIdPermohonan2nLast(hu.getIdPerserahan());
        HakmilikPermohonan hmpBaru = hakmilikPermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());

        //      List<HakmilikPermohonan> senaraiHPLama = regService.searchEditMohonHakmilik(idMohonHakmilikLama, hakmilik.getIdHakmilik());
        if (hmpLama != null) {
            if (hmpBaru != null) {
                hmpBaru.setHakmilik(hakmilik);
                hmpBaru.setLuasTerlibat(hmpLama.getLuasTerlibat());
                hmpBaru.setKodUnitLuas(hmpLama.getKodUnitLuas());
                hmpBaru.setBandarPekanMukimBaru(hmpLama.getBandarPekanMukimBaru());
                hmpBaru.setKodHakmilik(hmpLama.getKodHakmilik());
                hmpBaru.setLot(hmpLama.getLot());
                hmpBaru.setNoLot(hmpLama.getNoLot());
                hmpBaru.setDokumen1(hmpLama.getDokumen1());
                hmpBaru.setDokumen4(hmpLama.getDokumen4());
                hmpBaru.setLuasTerlibat(hmpLama.getLuasTerlibat());
                hmpBaru.setCukaiBaru(hmpLama.getCukaiBaru());
                hmpBaru.setInfoAudit(info);
                hakmilikPermohonanDAO.saveOrUpdate(hmpBaru);
            } else { //nad filter kes mohon_hakmilik untuk hakmilik baru is null 30/05/19
                HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
                hakmilikPermohonanBaru.setPermohonan(hmpLama.getPermohonan());
                hakmilikPermohonanBaru.setHakmilik(hakmilik);
                hakmilikPermohonanBaru.setLuasTerlibat(hmpLama.getLuasTerlibat());
                hakmilikPermohonanBaru.setKodUnitLuas(hmpLama.getKodUnitLuas());
                hakmilikPermohonanBaru.setBandarPekanMukimBaru(hmpLama.getBandarPekanMukimBaru());
                hakmilikPermohonanBaru.setKodHakmilik(hmpLama.getKodHakmilik());
                hakmilikPermohonanBaru.setLot(hmpLama.getLot());
                hakmilikPermohonanBaru.setNoLot(hmpLama.getNoLot());
                hakmilikPermohonanBaru.setDokumen1(hmpLama.getDokumen1());
                hakmilikPermohonanBaru.setDokumen4(hmpLama.getDokumen4());
                hakmilikPermohonanBaru.setLuasTerlibat(hmpLama.getLuasTerlibat());
                hakmilikPermohonanBaru.setCukaiBaru(hmpLama.getCukaiBaru());
                hakmilikPermohonanBaru.setInfoAudit(info);
                hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonanBaru);
            }

        }

    }

    private void simpanMohonPihak(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik);
        List<PermohonanPihak> senaraiMohonPihak = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        List<PermohonanPihak> senaraiMohonPihakBaru = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        if (senaraiMohonPihakBaru.size() <= 0) {
            for (PermohonanPihak mohonPihak : senaraiMohonPihak) {
                PermohonanPihak mohonPihakBaru = new PermohonanPihak();
                mohonPihakBaru.setAlamat(mohonPihak.getAlamat());
                mohonPihakBaru.setAlamatSurat(mohonPihak.getAlamatSurat());
                mohonPihakBaru.setCawangan(mohonPihak.getCawangan());
                mohonPihakBaru.setHakmilik(hakmilik);
                mohonPihakBaru.setInfoAudit(info);
                mohonPihakBaru.setJenis(mohonPihak.getJenis());
                mohonPihakBaru.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                if (mohonPihak.getJumlahPembilang() != null) {
                    mohonPihakBaru.setJumlahPembilang(mohonPihak.getJumlahPembilang());
                }
                if (mohonPihak.getJumlahPenyebut() != null) {
                    mohonPihakBaru.setJumlahPenyebut(mohonPihak.getJumlahPenyebut());
                }
                mohonPihakBaru.setNama(mohonPihak.getNama());
                mohonPihakBaru.setNoPengenalan(mohonPihak.getNoPengenalan());
                mohonPihakBaru.setNoRujukan(mohonPihak.getNoRujukan());
                mohonPihakBaru.setPermohonan(mohonPihak.getPermohonan());
                mohonPihakBaru.setPihak(mohonPihak.getPihak());
                mohonPihakBaru.setPihakCawangan(mohonPihak.getPihakCawangan());
                mohonPihakBaru.setSyerBersama(mohonPihak.getSyerBersama());
                if (mohonPihak.getSyerPembilang() != null) {
                    mohonPihakBaru.setSyerPembilang(mohonPihak.getSyerPembilang());
                }
                if (mohonPihak.getSyerPenyebut() != null) {
                    mohonPihakBaru.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                }
                mohonPihakBaru.setWargaNegara(mohonPihak.getWargaNegara());
                permohonanPihakService.saveOrUpdate(mohonPihakBaru);

            }
        }
    }

    private void simpanPemohon(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik);
        List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        List<Pemohon> senaraiPemohonBaru = pemohonService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        if (senaraiPemohonBaru.size() <= 0) {
            for (Pemohon pemohon : senaraiPemohon) {
                Permohonan mohon = permohonanDAO.findById(hu.getIdPerserahan());
                Pemohon pemohonBaru = new Pemohon();
                pemohonBaru.setPermohonan(mohon);
                pemohonBaru.setHakmilik(hakmilik);
                pemohonBaru.setAlamat(pemohon.getAlamat());
                pemohonBaru.setAlamatSurat(pemohon.getAlamatSurat());
                pemohonBaru.setCawangan(pemohon.getCawangan());
                pemohonBaru.setDalamanNilai1(pemohon.getDalamanNilai1());
                pemohonBaru.setDalamanNilai2(pemohon.getDalamanNilai2());
                pemohonBaru.setJenis(pemohon.getJenis());
                pemohonBaru.setJenisPemohon(pemohon.getJenisPemohon());
                pemohonBaru.setJenisPengenalan(pemohon.getJenisPengenalan());
                pemohonBaru.setJumlahPembilang(pemohon.getJumlahPembilang());
                pemohonBaru.setJumlahPenyebut(pemohon.getJumlahPenyebut());
                pemohonBaru.setNama(pemohon.getNama());
                pemohonBaru.setNoPengenalan(pemohon.getNoPengenalan());
                pemohonBaru.setPekerjaan(pemohon.getPekerjaan());
                pemohonBaru.setPendapatan(pemohon.getPendapatan());
                pemohonBaru.setPihak(pemohon.getPihak());
                pemohonBaru.setPihakCawangan(pemohon.getPihakCawangan());
                pemohonBaru.setSyerPembilang(pemohon.getSyerPembilang());
                pemohonBaru.setSyerPenyebut(pemohon.getSyerPenyebut());
                pemohonBaru.setWargaNegara(pemohon.getWargaNegara());
                pemohonBaru.setInfoAudit(info);
                pemohonService.saveOrUpdate(pemohonBaru);

            }
        }
    }

    private void simpanhp(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) { //yus add 28112018
        HakmilikPermohonan hp = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        if (hp != null) {
            HakmilikPermohonan hpNew = new HakmilikPermohonan();
            hpNew.setPermohonan(hp.getPermohonan());
            hpNew.setHakmilik(hakmilik);
            hpNew.setLuasTerlibat(hp.getLuasTerlibat());
            hpNew.setKodUnitLuas(hp.getKodUnitLuas());
            hpNew.setBandarPekanMukimBaru(hp.getBandarPekanMukimBaru());
            hpNew.setKodHakmilik(hp.getKodHakmilik());
            hpNew.setLot(hp.getLot());
            hpNew.setNoLot(hp.getNoLot());
            hpNew.setInfoAudit(info);
            hakmilikPermohonanService.save(hpNew);
        }
    }

    private void simpanmrl2(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) { //yus add 28112018

        logger.info("Hakmilik Baru = " + hakmilik);
        logger.info("hu hakmilik :" + hu.getHakmilik().getIdHakmilik() + "hu mohon : " + hu.getIdPerserahan());
        PermohonanRujukanLuar mrl = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        //PermohonanRujukanLuar mrl = permohonanRujukanLuarService.findByidPermohonanHSC(hu.getIdPerserahan(),hu.getHakmilik().getIdHakmilik() );
        logger.info("mrl :" + mrl);
        if (mrl != null) {
            PermohonanRujukanLuar mrlNew = new PermohonanRujukanLuar();
            logger.info("Tarikh Tamat ::: " + mrl.getTarikhTamat());
            mrlNew.setCawangan(mrl.getCawangan());
            mrlNew.setPermohonan(mrl.getPermohonan());
            mrlNew.setKodRujukan(mrl.getKodRujukan());
            mrlNew.setNoFail(mrl.getNoFail());
            mrlNew.setCatatan(mrl.getCatatan());
            mrlNew.setHakmilik(hakmilik);
            mrlNew.setInfoAudit(info);
            mrlNew.setTarikhTamat(mrl.getTarikhTamat()); //hkabs mohon_ruj_luar tak carry. yus add 07012019
            mrlNew.setTarikhRujukan(mrl.getTarikhRujukan());
            mrlNew.setItem(mrl.getItem());
            logger.info("Baru :" + mrlNew);
            permohonanRujukanLuarService.save(mrlNew);

        }
    }

    private void simpanmrl3(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) { //yus add 28112018

        PermohonanRujukanLuar mrl = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        PermohonanRujukanLuar mrlLama = permohonanRujukanLuarService.findMohonRujukLuarIdMohonFirst(hu.getIdPerserahan());
        logger.info("mrl :" + mrl);
        if (mrl != null) {
            //PermohonanRujukanLuar mrlNew = new PermohonanRujukanLuar();
            logger.info("Tarikh Tamat ::: " + mrlLama.getTarikhTamat());
            mrl.setCawangan(mrlLama.getCawangan());
            mrl.setPermohonan(mrlLama.getPermohonan());
            //mrl.setKodRujukan(mrlLama.getKodRujukan());
            mrl.setNoFail(mrlLama.getNoFail());
            mrl.setCatatan(mrlLama.getCatatan());
            mrl.setHakmilik(hakmilik);
            mrl.setInfoAudit(info);
            mrl.setKodRujukan(kodRujukanDAO.findById("FL"));
            mrl.setTarikhTamat(mrlLama.getTarikhTamat()); //hkabs mohon_ruj_luar tak carry trh_tamat. yus add 07012019     
            mrl.setTarikhKuatkuasa(mrlLama.getTarikhKuatkuasa()); //hkstk mohon_ruj_luar tak carry trh_kkuasa. yus add 11022019     
            mrl.setTarikhRujukan(mrlLama.getTarikhRujukan());
            mrl.setItem(mrlLama.getItem());
            permohonanRujukanLuarService.saveOrUpdate(mrl);
        } else {
            logger.info("Hakmilik Baru = " + hakmilik);
            PermohonanRujukanLuar mrlNew = new PermohonanRujukanLuar();
            mrlNew.setTarikhKuatkuasa(mrlLama.getTarikhKuatkuasa());
            mrlNew.setCawangan(mrlLama.getCawangan());
            mrlNew.setPermohonan(mrlLama.getPermohonan());
            mrlNew.setKodRujukan(mrlLama.getKodRujukan());
            mrlNew.setTarikhSidang(mrlLama.getTarikhSidang());
            mrlNew.setTarikhTamat(mrlLama.getTarikhTamat());
            mrlNew.setTarikhRujukan(mrlLama.getTarikhRujukan());
            mrlNew.setNoRujukan(mrlLama.getNoRujukan());
            mrlNew.setItem(mrlLama.getItem());
            if (mrlLama.getTempohTahun() != null) {
                mrlNew.setTempohTahun(mrlLama.getTempohTahun());
            }
            if (mrlLama.getTempohBulan() != null) {
                mrlNew.setTempohBulan(mrlLama.getTempohBulan());
            }
            if (mrlLama.getTempohHari() != null) {
                mrlNew.setTempohHari(mrlLama.getTempohHari());
            }
            mrlNew.setNoFail(mrlLama.getNoFail());
            mrlNew.setCatatan(mrlLama.getCatatan());
            mrlNew.setHakmilik(hakmilik);
            mrlNew.setInfoAudit(info);
            permohonanRujukanLuarService.save(mrlNew);

        }
    }

    //nad add 29112019
    private void simpanmhb(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik.getIdHakmilik());
        logger.info("hu hakmilik :" + hu.getHakmilik().getIdHakmilik() + " hu mohon : " + hu.getIdPerserahan());
        List<PermohonanPembetulanHakmilik> mhbLama = pService.findIdPermohonanIdHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        List<PermohonanPembetulanHakmilik> mhbBaru = pService.findIdPermohonanIdHakmilik(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        HakmilikPermohonan idHp = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        logger.info("mhb PSPL:" + mhbBaru.size());
        if (mhbBaru.size() <= 0) {
            for (PermohonanPembetulanHakmilik mhb : mhbLama) {
                 //filter mohon_hakmilik_betul untuk hakmilik baru is null
                 PermohonanPembetulanHakmilik mhbNew = new PermohonanPembetulanHakmilik();
                 logger.info("NEW mohon hakmilik betul");
                 mhbNew.setPermohonan(mhb.getPermohonan());
                 mhbNew.setHakmilik(idHp);
                 mhbNew.setIdHakmilik(hakmilik.getIdHakmilik());
                 mhbNew.setCawangan(mhb.getCawangan());
                 mhbNew.setTarikhDaftar(mhb.getTarikhDaftar());
                 mhbNew.setTempohPengangan(mhb.getTempohPengangan());
                 mhbNew.setTarikhLuput(mhb.getTarikhLuput());
                 mhbNew.setTempohPenganganSemasa(mhb.getTempohPenganganSemasa());
                 mhbNew.setTarikhLuputSemasa(mhb.getTarikhLuputSemasa());
                 mhbNew.setInfoAudit(info);
                 pService.simpanBetul(mhbNew);
            }
        }    
    }
    
        //nad add 04052021
    private void simpanmhb2(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik.getIdHakmilik());
        logger.info("hu hakmilik :" + hu.getHakmilik().getIdHakmilik() + " hu mohon : " + hu.getIdPerserahan());
        List<PermohonanPembetulanHakmilik> mhbLama = pService.findIdPermohonanIdHakmilikFirst(hu.getIdPerserahan());
        List<PermohonanPembetulanHakmilik> mhbBaru = pService.findIdPermohonanIdHakmilik(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        HakmilikPermohonan idHp = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        logger.info("mhb2 PSPL:" + mhbBaru.size());
        if (mhbBaru.size() <= 0) {
            for (PermohonanPembetulanHakmilik mhb : mhbLama) {
                 //filter mohon_hakmilik_betul untuk hakmilik baru is null
                 PermohonanPembetulanHakmilik mhbNew = new PermohonanPembetulanHakmilik();
                 logger.info("NEW mohon_hakmilik_betul");
                 mhbNew.setPermohonan(mhb.getPermohonan());
                 mhbNew.setHakmilik(idHp);
                 mhbNew.setIdHakmilik(hakmilik.getIdHakmilik());
                 mhbNew.setCawangan(mhb.getCawangan());
                 mhbNew.setTarikhDaftar(mhb.getTarikhDaftar());
                 mhbNew.setTempohPengangan(mhb.getTempohPengangan());
                 mhbNew.setTarikhLuput(mhb.getTarikhLuput());
                 mhbNew.setTempohPenganganSemasa(mhb.getTempohPenganganSemasa());
                 mhbNew.setTarikhLuputSemasa(mhb.getTarikhLuputSemasa());
                 mhbNew.setInfoAudit(info);
                 pService.simpanBetul(mhbNew);
            }
        }   
    }
    
    //nad add 15032024 HSPS 1 hm pecah 2 hm
    private void simpanmhb3(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik.getIdHakmilik());
        logger.info("hu hakmilik :" + hu.getHakmilik().getIdHakmilik() + " hu mohon : " + hu.getIdPerserahan());
        //List<PermohonanPembetulanHakmilik> mhbLama = pService.findIdPermohonanIdHakmilikFirst(hu.getIdPerserahan());
        PermohonanPembetulanHakmilik mhbLama = pService.findIdPermohonanIdHakmilikFirst1(hu.getIdPerserahan());
        List<PermohonanPembetulanHakmilik> mhbBaru = pService.findIdPermohonanIdHakmilik(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        HakmilikPermohonan idHp = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hakmilik.getIdHakmilik());
        logger.info("mhb2 PSPL:" + mhbBaru.size());
        if (mhbBaru.size() <= 0) {
                 //filter mohon_hakmilik_betul untuk hakmilik baru is null
                 PermohonanPembetulanHakmilik mhbNew = new PermohonanPembetulanHakmilik();
                 logger.info("NEW mohon_hakmilik_betul");
                 mhbNew.setPermohonan(mhbLama.getPermohonan());
                 mhbNew.setHakmilik(idHp);
                 mhbNew.setIdHakmilik(hakmilik.getIdHakmilik());
                 mhbNew.setCawangan(mhbLama.getCawangan());
                 mhbNew.setTarikhDaftar(mhbLama.getTarikhDaftar());
                 mhbNew.setTempohPengangan(mhbLama.getTempohPengangan());
                 mhbNew.setTarikhLuput(mhbLama.getTarikhLuput());
                 mhbNew.setTempohPenganganSemasa(mhbLama.getTempohPenganganSemasa());
                 mhbNew.setTarikhLuputSemasa(mhbLama.getTarikhLuputSemasa());
                 mhbNew.setInfoAudit(info);
                 pService.simpanBetul(mhbNew);
            
        }   
    }

    private void simpanmrl(HakmilikUrusan hu, Hakmilik hakmilik, InfoAudit info) {

        logger.info("Hakmilik Baru = " + hakmilik);
        PermohonanRujukanLuar mrl = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        if (mrl != null) {
            PermohonanRujukanLuar mrlNew = new PermohonanRujukanLuar();
            mrlNew.setTarikhKuatkuasa(mrl.getTarikhKuatkuasa());
            mrlNew.setCawangan(mrl.getCawangan());
            mrlNew.setPermohonan(mrl.getPermohonan());
            mrlNew.setKodRujukan(mrl.getKodRujukan());
            mrlNew.setTarikhSidang(mrl.getTarikhSidang());
            mrlNew.setTarikhTamat(mrl.getTarikhTamat());
            mrlNew.setTarikhRujukan(mrl.getTarikhRujukan());
            mrlNew.setNoRujukan(mrl.getNoRujukan());
            mrlNew.setItem(mrl.getItem());
            if (mrl.getTempohTahun() != null) {
                mrlNew.setTempohTahun(mrl.getTempohTahun());
            }
            if (mrl.getTempohBulan() != null) {
                mrlNew.setTempohBulan(mrl.getTempohBulan());
            }
            if (mrl.getTempohHari() != null) {
                mrlNew.setTempohHari(mrl.getTempohHari());
            }
            mrlNew.setNoFail(mrl.getNoFail());
            mrlNew.setCatatan(mrl.getCatatan());
            mrlNew.setHakmilik(hakmilik);
            mrlNew.setInfoAudit(info);
            permohonanRujukanLuarService.save(mrlNew);
        }
    }

    public Resolution saveMultipleHakmilikPihakTertinggal() {

        String[] id = getContext().getRequest().getParameterValues("idUrusan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        for (String string : id) {
            String idHP = string;
            logger.info("ID HakmilikPihak = " + idHP);
            if (idHP != null) {

                HakmilikPihakBerkepentingan hakmilikPihak = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHP));
                if (hakmilikPihak != null) {

                    HakmilikPihakBerkepentingan hpbBaru = new HakmilikPihakBerkepentingan();
//                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    hpbBaru.setHakmilik(hakmilik);
                    hpbBaru.setCawangan(hakmilik.getCawangan());
                    hpbBaru.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                    hpbBaru.setJenis(hakmilikPihak.getJenis());
                    hpbBaru.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                    hpbBaru.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                    if (hakmilikPihak.getPerserahan() != null) {
                        hpbBaru.setPerserahan(hakmilikPihak.getPerserahan());
                    }
                    hpbBaru.setPerserahanKaveat(hakmilikPihak.getPerserahanKaveat());
                    hpbBaru.setKaveatAktif(hakmilikPihak.getKaveatAktif());
                    hpbBaru.setAktif(hakmilikPihak.getAktif());
                    hpbBaru.setPihak(hakmilikPihak.getPihak());
                    hpbBaru.setNama(hakmilikPihak.getNama());
                    hpbBaru.setAlamat1(hakmilikPihak.getAlamat1());
                    hpbBaru.setAlamat2(hakmilikPihak.getAlamat2());
                    hpbBaru.setAlamat3(hakmilikPihak.getAlamat3());
                    hpbBaru.setAlamat4(hakmilikPihak.getAlamat4());
                    hpbBaru.setAlamatSurat(hakmilikPihak.getAlamatSurat());
                    hpbBaru.setPoskod(hakmilikPihak.getPoskod());
                    hpbBaru.setNegeri(hakmilikPihak.getNegeri());
                    hpbBaru.setPihakKongsiBersama(hakmilikPihak.getPihakKongsiBersama());
                    hpbBaru.setNoPengenalan(hakmilikPihak.getNoPengenalan());
                    if (hpbBaru.getNoPengenalanLama() != null) {
                        hpbBaru.setNoPengenalanLama(hakmilikPihak.getNoPengenalanLama());
                    }
                    hpbBaru.setJenisPengenalan(hakmilikPihak.getJenisPengenalan());
                    hpbBaru.setPenubuhanSyarikat(hakmilikPihak.getPenubuhanSyarikat());
                    hpbBaru.setJumlahPenyebut(hakmilikPihak.getSyerPenyebut());
                    hpbBaru.setJumlahPembilang(hakmilikPihak.getSyerPembilang());
                    hpbBaru.setWargaNegara(hakmilikPihak.getWargaNegara());
                    hpbBaru.setInfoAudit(info);

                    hpkService.save(hpbBaru);
                }
            }
        }

        addSimpleMessage("Data Telah Berjaya di simpan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution saveMultipleMohonPihak() {

        String[] id = getContext().getRequest().getParameterValues("idUrusan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        for (String string : id) {
            String IdMP = string;
            logger.info("ID Mohon Pihak = " + IdMP);
            if (IdMP != null) {
                PermohonanPihak mohonPihak = permohonanPihakService.findById(IdMP);
                if (mohonPihak != null) {
                    PermohonanPihak mohonPihakBaru = new PermohonanPihak();
                    mohonPihakBaru.setAlamat(mohonPihak.getAlamat());
                    mohonPihakBaru.setAlamatSurat(mohonPihak.getAlamatSurat());
                    mohonPihakBaru.setCawangan(mohonPihak.getCawangan());
                    mohonPihakBaru.setHakmilik(hakmilik);
                    mohonPihakBaru.setInfoAudit(info);
                    mohonPihakBaru.setJenis(mohonPihak.getJenis());
                    mohonPihakBaru.setJenisPengenalan(mohonPihak.getJenisPengenalan());
                    mohonPihakBaru.setJumlahPembilang(mohonPihak.getJumlahPembilang());
                    mohonPihakBaru.setJumlahPenyebut(mohonPihak.getJumlahPenyebut());
                    mohonPihakBaru.setNama(mohonPihak.getNama());
                    mohonPihakBaru.setNoPengenalan(mohonPihak.getNoPengenalan());
                    mohonPihakBaru.setNoRujukan(mohonPihak.getNoRujukan());
                    mohonPihakBaru.setPermohonan(mohonPihak.getPermohonan());
                    mohonPihakBaru.setPihak(mohonPihak.getPihak());
                    mohonPihakBaru.setPihakCawangan(mohonPihak.getPihakCawangan());
                    mohonPihakBaru.setSyerBersama(mohonPihak.getSyerBersama());
                    mohonPihakBaru.setSyerPembilang(mohonPihak.getSyerPembilang());
                    mohonPihakBaru.setSyerPenyebut(mohonPihak.getSyerPenyebut());
                    mohonPihakBaru.setWargaNegara(mohonPihak.getWargaNegara());
                    permohonanPihakService.saveOrUpdate(mohonPihakBaru);

                }
            }
        }

        addSimpleMessage("Data Telah Berjaya di simpan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution saveMultipleUrusan() {
        String[] id = getContext().getRequest().getParameterValues("idUrusan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        logger.info("Test Sebelum :::");
        if (p.getKodUrusan().getKod().equals("HSC")) { //yus add 28112018
            logger.info("Test HSC :::");
            for (String string : id) {
                String idMh2 = string;
                //start create mohon_ruj_luar jika belum wujud
                HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idMh2));
                PermohonanRujukanLuar prl = permohonanRujukanLuarService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
                if ((idMh2 != null) && (prl == null) && (!hu.getKodUrusan().getKod().equals("SA")) && (!hu.getKodUrusan().getKod().equals("SB")) && (!hu.getKodUrusan().getKod().equals("SW"))) {
                    PermohonanRujukanLuar prl2 = permohonanRujukanLuarService.findByidPermohonan2(hu.getIdPerserahan());
                    HakmilikUrusan hu2 = hakmilikUrusanService.findByIdPerserahanIdHakmilik(hu.getIdPerserahan(), prl2.getHakmilik().getIdHakmilik());
                    simpanmrl2(hu2, hakmilik, info);
                }
                //end create mohon_ruj_luar jika belum wujud

                //start create mohon_hakmilik jika belum wujud
                HakmilikPermohonan hp2 = hakmilikPermohonanService.checkByIdHakmilikIdMohon(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
                if ((idMh2 != null) && (hp2 == null)) {
                    HakmilikPermohonan hp3 = hakmilikPermohonanService.checkByIdMohonLatest(hu.getIdPerserahan());
                    HakmilikUrusan hu2 = hakmilikUrusanService.findByIdPerserahanIdHakmilik(hu.getIdPerserahan(), hp3.getHakmilik().getIdHakmilik());
                    simpanhp(hu2, hakmilik, info);  
                }
                //end create mohon_hakmilik

                if (hu != null) {
                    hu.setAktif('Y');
                }
                hakmilikUrusanService.saveOrUpdateUrusan(hu);
                simpanmhb2(hu, hakmilik, info);
            }
        } else if (p.getKodUrusan().getKod().equals("HKGHS")) {
            logger.info("Test HKGHS :::");
            for (String string : id) {
                String idMh = string;
                if (idMh != null) {
                    //HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                    //HakmilikPihakBerkepentingan hpb = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idMh));
                    HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idMh));
                    if (hu != null) {
//                    HakmilikUrusan hl = hakmilikUrusanDAO.findById(Long.parseLong(idMh));
//                    HakmilikUrusan hu = new HakmilikUrusan();
                        HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                        hakmilikUrusanBaru.setHakmilik(hakmilik);
                        hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                        hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                        hakmilikUrusanBaru.setAktif(hu.getAktif());
                        hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                        if (hu.getTarikhDaftar() != null) {
                            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                        } else {
                            hakmilikUrusanBaru.setTarikhDaftar(new java.util.Date());
                        }
                        hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                        hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                        hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                        hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                        hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                        hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                        hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                        hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                        if (hu.getTempohTahun() != null) {
                            hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                        }
                        if (hu.getTempohBulan() != null) {
                            hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                        }
                        if (hu.getTempohHari() != null) {
                            hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                        }
                        hakmilikUrusanBaru.setTarikhRujukan(hu.getTarikhRujukan());
                        hakmilikUrusanBaru.setItem(hu.getItem());
                        hakmilikUrusanBaru.setInfoAudit(info);
                        hakmilikUrusanService.saveOrUpdateUrusan(hakmilikUrusanBaru);
                        simpanMohonPihak(hu, hakmilik, info);
                        simpanMohonHakmilik(hu, hakmilik, info);
                        simpanPemohon(hu, hakmilik, info);
                        simpanmrl(hu, hakmilik, info);
                        simpanmhb(hu, hakmilik, info);
                    }
                }
            }
        } else {
            logger.info("Test Else :::");
            for (String string : id) {
                String idUrusan = string;
                if (idUrusan != null) {

                    HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));

                    //simpanmrl3(hu, hakmilik, info);                    
                    if (hu != null) {
                        hu.setAktif('Y');
                        hakmilikUrusanService.saveOrUpdateUrusan(hu);
                        simpanMohonHakmilik2(hu, hakmilik, info);
                        simpanmrl3(hu, hakmilik, info);
                        simpanmhb3(hu, hakmilik, info);
                    }
                    hakmilikUrusanService.saveOrUpdateUrusan(hu);
                }
            }
        }
        //rehydrate();
        addSimpleMessage("Data Telah Berjaya di simpan");
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

    public String getKodUnitLuasLama() {
        return kodUnitLuasLama;
    }

    public void setKodUnitLuasLama(String kodUnitLuasLama) {
        this.kodUnitLuasLama = kodUnitLuasLama;
    }

    public String getDmsPath() {
        return dmsPath;
    }

    public void setDmsPath(String dmsPath) {
        this.dmsPath = dmsPath;
    }

    public String getCukaiLama() {
        return cukaiLama;
    }

    public void setCukaiLama(String cukaiLama) {
        this.cukaiLama = cukaiLama;
    }

    public String getLuasHakmilikAsal() {
        return luasHakmilikAsal;
    }

    public void setLuasHakmilikAsal(String luasHakmilikAsal) {
        this.luasHakmilikAsal = luasHakmilikAsal;
    }

    public Boolean getPengPTG() {
        return pengPTG;
    }

    public void setPengPTG(Boolean pengPTG) {
        this.pengPTG = pengPTG;
    }

    public List<KodPBT> getSenaraiKodPBT() {
        return senaraiKodPBT;
    }

    public void setSenaraiKodPBT(List<KodPBT> senaraiKodPBT) {
        this.senaraiKodPBT = senaraiKodPBT;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public int getTotalPihakHakmilikLama() {
        return totalPihakHakmilikLama;
    }

    public void setTotalPihakHakmilikLama(int totalPihakHakmilikLama) {
        this.totalPihakHakmilikLama = totalPihakHakmilikLama;
    }

    public List<HakmilikAsalPermohonan> getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(List<HakmilikAsalPermohonan> listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }

    public String getIdHakmilikAsal() {
        return idHakmilikAsal;
    }

    public void setIdHakmilikAsal(String idHakmilikAsal) {
        this.idHakmilikAsal = idHakmilikAsal;
    }

    public String getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(String kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public List<KodUOM> getSenaraiKodUOM() {
        return senaraiKodUOM;
    }

    public void setSenaraiKodUOM(List<KodUOM> senaraiKodUOM) {
        this.senaraiKodUOM = senaraiKodUOM;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public List<PermohonanPihak> getSenaraiMohonPihakTerlibat() {
        return senaraiMohonPihakTerlibat;
    }

    public void setSenaraiMohonPihakTerlibat(List<PermohonanPihak> senaraiMohonPihakTerlibat) {
        this.senaraiMohonPihakTerlibat = senaraiMohonPihakTerlibat;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
        return senaraiHakmilikUrusan;
    }

    public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
        this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return senaraiKodDaerah;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodSyarat() {
        return kodSyarat;
    }

    public void setKodSyarat(String kodSyarat) {
        this.kodSyarat = kodSyarat;
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

    public BigDecimal getKadarCukai() {
        return kadarCukai;
    }

    public void setKadarCukai(BigDecimal kadarCukai) {
        this.kadarCukai = kadarCukai;
    }

    public int getKodCukai() {
        return kodCukai;
    }

    public void setKodCukai(int kodCukai) {
        this.kodCukai = kodCukai;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public List<KodSeksyen> getListKodSeksyenByBpm() {
        return listKodSeksyenByBpm;
    }

    public void setListKodSeksyenByBpm(List<KodSeksyen> listKodSeksyenByBpm) {
        this.listKodSeksyenByBpm = listKodSeksyenByBpm;
    }

    public List<KodSeksyen> getListKodSeksyenByBpm2() {
        return listKodSeksyenByBpm2;
    }

    public void setListKodSeksyenByBpm2(List<KodSeksyen> listKodSeksyenByBpm2) {
        this.listKodSeksyenByBpm2 = listKodSeksyenByBpm2;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<HakmilikSebelumPermohonan> getListHakmilikSblmPermohonan() {
        return listHakmilikSblmPermohonan;
    }

    public void setListHakmilikSblmPermohonan(List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan) {
        this.listHakmilikSblmPermohonan = listHakmilikSblmPermohonan;
    }

    public List<HakmilikAsalPermohonan> getListHakmilikAsalPermohonan() {
        return listHakmilikAsalPermohonan;
    }

    public void setListHakmilikAsalPermohonan(List<HakmilikAsalPermohonan> listHakmilikAsalPermohonan) {
        this.listHakmilikAsalPermohonan = listHakmilikAsalPermohonan;
    }

    public ArrayList<HakmilikPermohonan> getListMohonHakmilikBaru() {
        return listMohonHakmilikBaru;
    }

    public void setListMohonHakmilikBaru(ArrayList<HakmilikPermohonan> listMohonHakmilikBaru) {
        this.listMohonHakmilikBaru = listMohonHakmilikBaru;
    }

    public HakmilikAsalPermohonan getMohonHakmilikAsal() {
        return mohonHakmilikAsal;
    }

    public void setMohonHakmilikAsal(HakmilikAsalPermohonan mohonHakmilikAsal) {
        this.mohonHakmilikAsal = mohonHakmilikAsal;
    }

    public HakmilikSebelumPermohonan getMohonHakmilikSblm() {
        return mohonHakmilikSblm;
    }

    public void setMohonHakmilikSblm(HakmilikSebelumPermohonan mohonHakmilikSblm) {
        this.mohonHakmilikSblm = mohonHakmilikSblm;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public ArrayList<Hakmilik> getListHakmilikBaru() {
        return listHakmilikBaru;
    }

    public void setListHakmilikBaru(ArrayList<Hakmilik> listHakmilikBaru) {
        this.listHakmilikBaru = listHakmilikBaru;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public int getTotalHakmilik() {
        return totalHakmilik;
    }

    public void setTotalHakmilik(int totalHakmilik) {
        this.totalHakmilik = totalHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public SejarahHakmilik getSejarahHakmilik() {
        return sejarahHakmilik;
    }

    public void setSejarahHakmilik(SejarahHakmilik sejarahHakmilik) {
        this.sejarahHakmilik = sejarahHakmilik;
    }

    public String[] getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(String[] syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public String[] getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(String[] syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<SejarahHakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<SejarahHakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }

    public List<HakmilikSebelum> getListHakmilikSebelumByIDHakmilikSebelum() {
        return listHakmilikSebelumByIDHakmilikSebelum;
    }

    public void setListHakmilikSebelumByIDHakmilikSebelum(List<HakmilikSebelum> listHakmilikSebelumByIDHakmilikSebelum) {
        this.listHakmilikSebelumByIDHakmilikSebelum = listHakmilikSebelumByIDHakmilikSebelum;
    }

    public String getIdAsal() {
        return idAsal;
    }

    public void setIdAsal(String idAsal) {
        this.idAsal = idAsal;
    }

    public HakmilikAsal getHakmilikAsal() {
        return hakmilikAsal;
    }

    public void setHakmilikAsal(HakmilikAsal hakmilikAsal) {
        this.hakmilikAsal = hakmilikAsal;
    }

    public List<HakmilikAsal> getListHakmilikAsalByIDHakmilikAsal() {
        return listHakmilikAsalByIDHakmilikAsal;
    }

    public void setListHakmilikAsalByIDHakmilikAsal(List<HakmilikAsal> listHakmilikAsalByIDHakmilikAsal) {
        this.listHakmilikAsalByIDHakmilikAsal = listHakmilikAsalByIDHakmilikAsal;
    }

    public ArrayList<HakmilikAsal> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(ArrayList<HakmilikAsal> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
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

    public List<HakmilikSebelum> getHakmilikSebelumList() {
        return hakmilikSebelumList;
    }

    public void setHakmilikSebelumList(List<HakmilikSebelum> hakmilikSebelumList) {
        this.hakmilikSebelumList = hakmilikSebelumList;
    }

    public List<HakmilikAsal> getHakmilikAsalList() {
        return hakmilikAsalList;
    }

    public void setHakmilikAsalList(List<HakmilikAsal> hakmilikAsalList) {
        this.hakmilikAsalList = hakmilikAsalList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<KodKadarCukai> getKadarCukaiList() {
        return kadarCukaiList;
    }

    public void setKadarCukaiList(List<KodKadarCukai> kadarCukaiList) {
        rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");
        this.kadarCukaiList = kadarCukaiList;
    }

    public String getNamaBPM() {
        return namaBPM;
    }

    public void setNamaBPM(String namaBPM) {
        this.namaBPM = namaBPM;
    }

    public List<HakmilikSebelumPermohonan> getListMohonHakmilikSebelum() {
        return listMohonHakmilikSebelum;
    }

    public void setListMohonHakmilikSebelum(List<HakmilikSebelumPermohonan> listMohonHakmilikSebelum) {
        this.listMohonHakmilikSebelum = listMohonHakmilikSebelum;
    }

    public String getTrhWartaGsa() {
        return trhWartaGsa;
    }

    public void setTrhWartaGsa(String trhWartaGsa) {
        this.trhWartaGsa = trhWartaGsa;
    }

    public String getTrhWartaPbt() {
        return trhWartaPbt;
    }

    public void setTrhWartaPbt(String trhWartaPbt) {
        this.trhWartaPbt = trhWartaPbt;
    }

    public String getTrhWartaRezab() {
        return trhWartaRezab;
    }

    public void setTrhWartaRezab(String trhWartaRezab) {
        this.trhWartaRezab = trhWartaRezab;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public String getKodUnitLuasDari() {
        return kodUnitLuasDari;
    }

    public void setKodUnitLuasDari(String kodUnitLuasDari) {
        this.kodUnitLuasDari = kodUnitLuasDari;
    }

    public String getKodUnitLuasKepada() {
        return kodUnitLuasKepada;
    }

    public void setKodUnitLuasKepada(String kodUnitLuasKepada) {
        this.kodUnitLuasKepada = kodUnitLuasKepada;
    }

    public BigDecimal getLuasDari() {
        return luasDari;
    }

    public void setLuasDari(BigDecimal luasDari) {
        this.luasDari = luasDari;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public String getKodUnitLuasTerlibat() {
        return kodUnitLuasTerlibat;
    }

    public void setKodUnitLuasTerlibat(String kodUnitLuasTerlibat) {
        this.kodUnitLuasTerlibat = kodUnitLuasTerlibat;
    }

    public String getJenisPelan() {
        return jenisPelan;
    }

    public void setJenisPelan(String jenisPelan) {
        this.jenisPelan = jenisPelan;
    }

    public String getKodPelan() {
        return kodPelan;
    }

    public void setKodPelan(String kodPelan) {
        this.kodPelan = kodPelan;
    }

    public List<HakmilikSebelumPermohonan> getListHakmilikSblmPermohonan2() {
        return listHakmilikSblmPermohonan2;
    }

    public void setListHakmilikSblmPermohonan2(List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan2) {
        this.listHakmilikSblmPermohonan2 = listHakmilikSblmPermohonan2;
    }

    public BigDecimal getLuasLain() {
        return luasLain;
    }

    public void setLuasLain(BigDecimal luasLain) {
        this.luasLain = luasLain;
    }

    public String getKodUnitLuasLain() {
        return kodUnitLuasLain;
    }

    public void setKodUnitLuasLain(String kodUnitLuasLain) {
        this.kodUnitLuasLain = kodUnitLuasLain;
    }

    public List<HakmilikUrusan> getHakmilikUrusanHmLama() {
        return hakmilikUrusanHmLama;
    }

    public void setHakmilikUrusanHmLama(List<HakmilikUrusan> hakmilikUrusanHmLama) {
        this.hakmilikUrusanHmLama = hakmilikUrusanHmLama;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public PembetulanService getpService() {
        return pService;
    }

    public void setpService(PembetulanService pService) {
        this.pService = pService;
    }

    public KodSeksyen getSeksyen1() {
        return seksyen1;
    }

    public void setSeksyen1(KodSeksyen seksyen1) {
        this.seksyen1 = seksyen1;
    }

    public String getSeksyen2() {
        return seksyen2;
    }

    public void setSeksyen2(String seksyen2) {
        this.seksyen2 = seksyen2;
    }
}
