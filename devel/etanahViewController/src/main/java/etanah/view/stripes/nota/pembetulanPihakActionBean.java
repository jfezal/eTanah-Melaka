
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

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
import etanah.model.HakmilikWaris;
import etanah.dao.*;
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
import etanah.dao.KodPenubuhanSyarikatDAO;
import etanah.dao.PihakDAO;
import etanah.model.AlamatSurat;
import etanah.model.KodPenubuhanSyarikat;
import etanah.model.KodSekatanKepentingan;
import etanah.service.daftar.PembetulanService;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author khairil
 */
@UrlBinding("/daftar/pembetulan_pihak")
public class pembetulanPihakActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(pembetulanPihakActionBean.class);
    String idHakmilik;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
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
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    HakmilikAsalPermohonanDAO mohonHakmilikAsalDA0;
    @Inject
    HakmilikSebelumPermohonanDAO mohonHakmilikSblmDA0;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PembetulanService pService;
    @Inject
    PemohonService pemohonService;
    @Inject
    GeneratorIdHakmilik gh;
    @Inject
    KodBandarPekanMukimDAO kodBpmDAO;
    @Inject
    KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
//  @Inject
//  KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    HakmilikUrusanService hUService;
    @Inject
    HakmilikPihakBerkepentinganDAO pihakBerkepentinganDAO;
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
    PihakDAO pihakDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    private Permohonan p;
    private Pihak pihak;
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
    private List<KodPenubuhanSyarikat> senaraiSyktTubuh;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList;
    private List<HakmilikAsal> hakmilikAsalList;
    private List<HakmilikAsal> listHakmilikAsalByIDHakmilikAsal;
    private List<HakmilikSebelum> listHakmilikSebelumByIDHakmilikSebelum;
    private List<HakmilikSebelum> hakmilikSebelumList;
    private List<SejarahHakmilik> listHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikAsalPermohonan> listHakmilikAsalPermohonan = new ArrayList();
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan2;
    private List<HakmilikSebelumPermohonan> listHakmilikSblmPermohonan;
    private List<HakmilikPermohonan> listHP;
    private List<HakmilikWaris> hwList;
    private List<Pemohon> pemohonList;
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<HakmilikAsal> senaraiHakmilikAsal = new ArrayList();
    private List<PermohonanPihak> mohonPihakList;
    private List<KodKadarCukai> kadarCukaiList;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<KodSeksyen> listKodSeksyenByBpm;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<KodDaerah> senaraiKodDaerah;
    private List<HakmilikUrusan> senaraiHakmilikUrusan;
    private List<PermohonanPihak> senaraiMohonPihakTerlibat;
    private List<KodUOM> senaraiKodUOM;
    private List<HakmilikAsalPermohonan> listHakmilikSebelum = new ArrayList();
    private List<HakmilikSebelumPermohonan> listMohonHakmilikSebelum = new ArrayList();
    private HakmilikPihakBerkepentingan hakmilikPihak;
    String idAsal;
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
    String idPBK;
    private String[] syerPembilang;
    private String[] syerPenyebut;
    Fraction sum = new Fraction(0, 1);
    private int totalHakmilik = 1;
    BigDecimal total = BigDecimal.ZERO;
    int kodCukai = 0;
    BigDecimal kadarCukai = BigDecimal.ZERO;
    int size;
    String bpm;
    Long five = new Long(5);
    Long zero = new Long(0);
    String kodGunaTanah;
    String kodUnitLuas;
    String idHakmilikAsal;
    String idHakmilikPihak;
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
    private String syktTubuh;
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
    private String idHPW_Waris;
    private String nama_Waris;
    private String jeniskp_Waris;
    private String noP_Waris;
    private String pembilang_Waris;
    private String penyebut_Waris;
    etanahActionBeanContext ctx;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private boolean moreThanOneHakmilik = false;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        } else {
            hakmilik = p.getSenaraiHakmilik().get(0).getHakmilik();
            idHakmilik = hakmilik.getIdHakmilik();
        }

        logger.debug("idHakmilik :" + idHakmilik);
        logger.debug("idPermohonan :" + idPermohonan);
        if (StringUtils.isNotEmpty(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);        
            pihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilikBETPB(hakmilikDAO.findById(idHakmilik)); //yus tambah list tidak kuatkuasa BETPB 02102018
            syerPembilang = new String[pihakKepentinganList.size()];
            syerPenyebut = new String[pihakKepentinganList.size()];            
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
        return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("tab", "true");
    }

    public Resolution deletePihakKepentingan() {
        String idHPB = getContext().getRequest().getParameter("id_hP");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPihakBerkepentingan pB = hakmilikPihakKepentinganService.findById(idHPB);
        if (pB != null) {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            pB.setAktif('T');
            regService.simpanHakmilikPihak(pB);
        }

        addSimpleMessage("Data Telah Berjaya diHapuskan");
        
        logger.debug("idHakmilik :" + idHakmilik);
        logger.debug("idPermohonan :" + idPermohonan);
        if (StringUtils.isNotEmpty(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);        
            pihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikDAO.findById(idHakmilik));
            syerPembilang = new String[pihakKepentinganList.size()];
            syerPenyebut = new String[pihakKepentinganList.size()];            
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
        return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("tab", "true");
    }
    
    public Resolution deletePihakWaris() {
        String idHPB = getContext().getRequest().getParameter("id_hpw");
        HakmilikWaris hw = pService.findWarisByID(idHPB);
        pService.deleteWaris(hw);

        addSimpleMessage("Data Telah Berjaya diHapuskan");
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        hakmilikPihak = hakmilikPihakService.findById(idHakmilikPihakBerkepentingan);
        hwList = pService.findHakmilikWaris(idHakmilikPihakBerkepentingan);

        return new JSP("daftar/kemasukan_pihak_waris.jsp").addParameter("tab", "true");

    }
    
    public Resolution EditPihakWaris() {
        String idHPB = getContext().getRequest().getParameter("id_hpw");
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        HakmilikWaris hw = pService.findWarisByID(idHPB);

        if (hw != null) {
            idHPW_Waris = String.valueOf(hw.getIdWaris());

            if (hw.getNama() != null) {
                nama_Waris = hw.getNama();
            }
            if (hw.getJenisPengenalan() != null) {
                jeniskp_Waris = hw.getJenisPengenalan().getKod();
            }
            if (hw.getNoPengenalan() != null) {
                noP_Waris = hw.getNoPengenalan();
            }
            if (String.valueOf(hw.getSyerPembilang()) != null) {
                pembilang_Waris = String.valueOf(hw.getSyerPembilang());
            }
            if (String.valueOf(hw.getSyerPenyebut()) != null) {
                penyebut_Waris = String.valueOf(hw.getSyerPenyebut());
            }
        }
        return new JSP("daftar/kemasukan_pihak_waris_edit.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanWarisEdit() {
        String idHPB = getContext().getRequest().getParameter("idHPW_Waris");
        HakmilikWaris hw = pService.findWarisByID(idHPB);
        if (hw != null) {
            if (StringUtils.isNotBlank(nama_Waris)) {
                hw.setNama(nama_Waris);
            }
            if (StringUtils.isNotBlank(jeniskp_Waris)) {
                hw.setJenisPengenalan(kodJenisPengenalanDAO.findById(jeniskp_Waris));
            }
            if (StringUtils.isNotBlank(noP_Waris)) {
                hw.setNoPengenalan(noP_Waris);
            }
            if (StringUtils.isNotBlank(pembilang_Waris)) {
                hw.setSyerPembilang(Long.parseLong(pembilang_Waris));
            }
            if (StringUtils.isNotBlank(penyebut_Waris)) {
                hw.setSyerPenyebut(Long.parseLong(penyebut_Waris));
            }
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            hw.setInfoAudit(info);
            pService.simpanWaris(hw);
            addSimpleMessage("Data Telah Berjaya Dikemaskini");
        }

        String idHakmilikPihakBerkepentingan = String.valueOf(hw.getPemegangAmanah().getIdHakmilikPihakBerkepentingan());
        hakmilikPihak = hakmilikPihakService.findById(idHakmilikPihakBerkepentingan);
        hwList = pService.findHakmilikWaris(idHakmilikPihakBerkepentingan);

        return new JSP("daftar/kemasukan_pihak_waris.jsp").addParameter("tab", "true");

    }

    public Resolution showForms() {

        return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("showForm", "").addParameter("tab", "true");
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
                hpb.setJumlahPembilang(samaRata.getNumerator());
                hpb.setJumlahPenyebut(samaRata.getDenominator());
                hpb.setSyerPembilang(samaRata.getNumerator());
                hpb.setSyerPenyebut(samaRata.getDenominator());                
                senarai.add(hpb);
            }
        }

        //permohonanPihakService.saveOrUpdate(mohonPihakListBaru);
        hakmilikPihakKepentinganService.saveList(senarai);
        StringBuilder s = new StringBuilder();
        s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
        results = s.toString();
        logger.debug(results);

        return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("showForm", "").addParameter("tab", "true");

    }

    public Resolution simpanPihak() {
        idHakmilik = (String) getContext().getRequest().getAttribute("idHakmilik");
        syktTubuh = (String) getContext().getRequest().getAttribute("idPBK2");
        String penubuhanSyarikat = (String) getContext().getRequest().getParameter("pihak.penubuhanSyarikat.kod");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(info.getDimasukOleh());
        info.setTarikhMasuk(info.getTarikhMasuk());
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
//        info.setTarikhMasuk(new java.util.Date());
        logger.debug("simpan pihak for hakmilik :" + idHakmilik);
        Pihak p = new Pihak();
        p = pihakDAO.findById(pihak.getIdPihak());
        p.setNama(pihak.getNama().toUpperCase());
        p.setNoPengenalan(pihak.getNoPengenalan());
        p.setBangsa(pihak.getBangsa());
        if (pihak.getWargaNegara() != null) {
            p.setWargaNegara(pihak.getWargaNegara());
        }
        p.setKodJantina(pihak.getKodJantina());

        if (pihak.getAlamat1() != null) {
            p.setAlamat1(pihak.getAlamat1().toUpperCase());
        } else {
            p.setAlamat1("");
        }
        if (pihak.getAlamat2() != null) {
            p.setAlamat2(pihak.getAlamat2().toUpperCase());
        } else {
            p.setAlamat2("");
        }
        if (pihak.getAlamat3() != null) {
            p.setAlamat3(pihak.getAlamat3().toUpperCase());
        } else {
            p.setAlamat3("");
        }
        if (pihak.getAlamat4() != null) {
            p.setAlamat4(pihak.getAlamat4().toUpperCase());
        } else {
            p.setAlamat4("");
        }
        p.setPoskod(pihak.getPoskod());
        if (pihak.getNegeri() != null) {
            p.setNegeri(pihak.getNegeri());
        }

        if (pihak.getSuratAlamat1() != null) {
            p.setSuratAlamat1(pihak.getSuratAlamat1().toUpperCase());
        } else {
            p.setSuratAlamat1("");
        }
        if (pihak.getSuratAlamat2() != null) {
            p.setSuratAlamat2(pihak.getSuratAlamat2().toUpperCase());
        } else {
            p.setSuratAlamat2("");
        }
        if (pihak.getSuratAlamat3() != null) {
            p.setSuratAlamat3(pihak.getSuratAlamat3().toUpperCase());
        }
        if (pihak.getSuratAlamat4() != null) {
            p.setSuratAlamat4(pihak.getSuratAlamat4().toUpperCase());
        } else {
            p.setSuratAlamat4("");
        }
        p.setSuratPoskod(pihak.getSuratPoskod());
        if (pihak.getSuratNegeri() != null) {
            p.setSuratNegeri(pihak.getSuratNegeri());
        }
        p.setInfoAudit(info);
        if (p != null) {
            regService.updatePihak(p);
        }

        logger.info("--> id pbk " + idPBK);
        HakmilikPihakBerkepentingan pbk = pihakBerkepentinganDAO.findById(Long.parseLong(idPBK));
        if (pbk != null) {
            pbk.setNama(pihak.getNama().toUpperCase());
            pbk.setNoPengenalan(pihak.getNoPengenalan());
            pbk.setWargaNegara(pihak.getWargaNegara());
            if (pihak.getAlamat1() != null) {
                pbk.setAlamat1(pihak.getAlamat1().toUpperCase());
            } else {
                pbk.setAlamat1("");
            }

            if (pihak.getAlamat2() != null) {
                pbk.setAlamat2(pihak.getAlamat2().toUpperCase());
            } else {
                pbk.setAlamat2("");
            }

            if (pihak.getAlamat3() != null) {
                pbk.setAlamat3(pihak.getAlamat3().toUpperCase());
            } else {
                pbk.setAlamat3("");
            }

            if (pihak.getAlamat4() != null) {
                pbk.setAlamat4(pihak.getAlamat4().toUpperCase());
            } else {
                pbk.setAlamat4("");
            }

            pbk.setPoskod(pihak.getPoskod());
            pbk.setNegeri(pihak.getNegeri());

            AlamatSurat alamatSurat = new AlamatSurat();
            if (pihak.getSuratAlamat1() != null) {
                alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat1("");
            }
            if (pihak.getSuratAlamat2() != null) {
                alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat2("");
            }
            if (pihak.getSuratAlamat3() != null) {
                alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat3("");
            }
            if (pihak.getSuratAlamat4() != null) {
                alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat4("");
            }

            alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
            alamatSurat.setNegeriSurat(pihak.getSuratNegeri());
            pbk.setAlamatSurat(alamatSurat);
            pbk.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
//            logger.info("syarikat tubuh :" + pihak.getPenubuhanSyarikat().getNama());
            pbk.setInfoAudit(info);
            regService.simpanHakmilikPihak(pbk);
        }
        addSimpleMessage("Pihak Telah Berjaya diKemaskini");
        return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("showForm", "").addParameter("tab", "true");
    }

    public Resolution cariWaris() {
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        hakmilikPihak = hakmilikPihakService.findById(idHakmilikPihakBerkepentingan);
        hwList = pService.findHakmilikWaris(idHakmilikPihakBerkepentingan);

        return new JSP("daftar/kemasukan_pihak_waris.jsp").addParameter("tab", "true");
    }

    public Resolution tambahWaris() {
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        hakmilikPihak = hakmilikPihakService.findById(idHakmilikPihakBerkepentingan);
        hwList = pService.findHakmilikWaris(idHakmilikPihakBerkepentingan);

        idHakmilikPihak = idHakmilikPihakBerkepentingan;
        return new JSP("daftar/kemasukan_pihak_waris_insert.jsp").addParameter("tab", "true");
    }

    public Resolution simpanWaris() {
        String idHP = getContext().getRequest().getParameter("idHP");
        String nama = getContext().getRequest().getParameter("nama");
        String jeniskp = getContext().getRequest().getParameter("jeniskp");
        String noP = getContext().getRequest().getParameter("noP");
        String pembilang = getContext().getRequest().getParameter("pembilang");
        String penyebut = getContext().getRequest().getParameter("penyebut");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info =  new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        hakmilikPihak = hakmilikPihakService.findById(idHP);

        HakmilikWaris hw = new HakmilikWaris();
        hw.setInfoAudit(info);
        hw.setNoPengenalan(noP);
        hw.setNama(nama);
        hw.setPemegangAmanah(hakmilikPihak);
        hw.setJenisPengenalan(kodJenisPengenalanDAO.findById(jeniskp));
        hw.setSyerPembilang(Long.parseLong(pembilang));
        hw.setSyerPenyebut(Long.parseLong(penyebut));
        hw.setCawangan(peng.getKodCawangan());
        pService.simpanWaris(hw);

        hakmilikPihak = hakmilikPihakService.findById(idHP);
        hwList = pService.findHakmilikWaris(idHP);

        return new JSP("daftar/kemasukan_pihak_waris.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = new Permohonan();
        
        if(idPermohonan!=null)
        {
            p = permohonanDAO.findById(idPermohonan);
        }
        
        senaraiHakmilikTerlibat = p.getSenaraiHakmilik();        
        
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        } else {
            if (p.getIdPermohonan() != null) {
                hakmilik = p.getSenaraiHakmilik().get(0).getHakmilik();
                idHakmilik = hakmilik.getIdHakmilik();
            }            
        }
 
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

    public String getIdPBK() {
        return idPBK;
    }

    public void setIdPBK(String idPBK) {
        this.idPBK = idPBK;
    }

    public String getSyktTubuh() {
        return syktTubuh;
    }

    public void setSyktTubuh(String syktTubuh) {
        this.syktTubuh = syktTubuh;
    }

    public List<HakmilikWaris> getHwList() {
        return hwList;
    }

    public void setHwList(List<HakmilikWaris> hwList) {
        this.hwList = hwList;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public String getIdHakmilikPihak() {
        return idHakmilikPihak;
    }

    public void setIdHakmilikPihak(String idHakmilikPihak) {
        this.idHakmilikPihak = idHakmilikPihak;
    }

    public String getIdHPW_Waris() {
        return idHPW_Waris;
    }

    public void setIdHPW_Waris(String idHPW_Waris) {
        this.idHPW_Waris = idHPW_Waris;
    }

    public String getNama_Waris() {
        return nama_Waris;
    }

    public void setNama_Waris(String nama_Waris) {
        this.nama_Waris = nama_Waris;
    }

    public String getJeniskp_Waris() {
        return jeniskp_Waris;
    }

    public void setJeniskp_Waris(String jeniskp_Waris) {
        this.jeniskp_Waris = jeniskp_Waris;
    }

    public String getNoP_Waris() {
        return noP_Waris;
    }

    public void setNoP_Waris(String noP_Waris) {
        this.noP_Waris = noP_Waris;
    }

    public String getPembilang_Waris() {
        return pembilang_Waris;
    }

    public void setPembilang_Waris(String pembilang_Waris) {
        this.pembilang_Waris = pembilang_Waris;
    }

    public String getPenyebut_Waris() {
        return penyebut_Waris;
    }

    public void setPenyebut_Waris(String penyebut_Waris) {
        this.penyebut_Waris = penyebut_Waris;
    }

    public etanahActionBeanContext getCtx() {
        return ctx;
    }

    public void setCtx(etanahActionBeanContext ctx) {
        this.ctx = ctx;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public boolean isMoreThanOneHakmilik() {
        return moreThanOneHakmilik;
    }

    public void setMoreThanOneHakmilik(boolean moreThanOneHakmilik) {
        this.moreThanOneHakmilik = moreThanOneHakmilik;
    }
}
