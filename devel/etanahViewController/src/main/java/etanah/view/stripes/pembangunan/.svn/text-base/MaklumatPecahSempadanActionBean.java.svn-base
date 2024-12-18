/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.internal.Nullable;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.PermohonanPlotPelan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
//import etanah.model.KodDokumen;
//import etanah.model.KodRujukan;
import etanah.model.KodCawangan;
import etanah.model.KodPemilikan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodRizab;
import etanah.service.RegService;
import etanah.service.LaporanTanahService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.PermohonanSemakPelanDAO;
import etanah.model.Dokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodNegeri;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotSyaratNyata;
import etanah.model.PermohonanSemakPelan;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import etanah.service.DevIntegrationService;
import etanah.service.common.DokumenService;
import java.math.RoundingMode;
import org.hibernate.validator.NotNull;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/maklumat_pecahSempadan")
public class MaklumatPecahSempadanActionBean extends AbleActionBean {
    
    private static final Logger logger = Logger.getLogger(MaklumatPecahSempadanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    DevIntegrationService dis;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanSemakPelanDAO permohonanSemakPelanDAO;
    @Inject
    DokumenService dokumenService;
    private String tajuk;
    private List<KodKegunaanTanah> listGT;
    private Hakmilik hakmilik;
    private Permohonan permohonan;
    private KodUOM kodUnitLuasPA;
    private int jumlahPlot;
    private int jumlahPlottemp;
    private int bilplotHakmilik = 0;
    private String phkBerkuasa;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private int bilplotTHakmilik = 0;
    private int bilplotKerajaan = 0;
    private PermohonanPlotPelan mpp;
    private String katTanah;
    private String kodGunaTanah;
    private int dari;
    private int ke;
    private BigDecimal luas = new BigDecimal(0.00);
    private BigDecimal luashakmilik;
    private String kodUOM;
    private String gunatanahrizab;
    private BigDecimal luasrizab;
    private String kodUOMrizab;
    private int daririzab;
    private int kerizab;
    private String gunatanahkerajaan;
    private BigDecimal luaskerajaan;
    private String kodUOMkerajaan;
    private int darikerajaan;
    private int kekerajaan;
    private PermohonanPengambilan mohonAmbil;
    private List<PermohonanPlotPelan> listHakmilik;
    private ArrayList listRizab = new ArrayList();
    private ArrayList listKerajaan = new ArrayList();
    private ArrayList listAmbilKerajaan = new ArrayList();
    private String jenisRizab;
    private BigDecimal jumluasHakmilik = new BigDecimal(0.00);
    private BigDecimal jumluasTHakmilik = new BigDecimal(0.00);
    private BigDecimal jumluasKerajaan = new BigDecimal(0.00);
    private BigDecimal jumluasAmbilKerajaan = new BigDecimal(0.00);
    private BigDecimal jumlahPlotYang = new BigDecimal(0.00);
    private BigDecimal baki = new BigDecimal(0.00);
    private BigDecimal jumluasPA = new BigDecimal(0.00);
    private List<PermohonanPlotPelan> listplotpelan;
    private int[] plotArray;
    private int[] plotArray2;
    private String idPermohonan;
    private String idPlot;
    private int bilplotHakmiliktemp = 0;
    private int bilplotTHakmiliktemp = 0;
    private int bilplotKerajaantemp = 0;
    private int bilplotKerajaanAmbiltemp = 0;
    private int bilplotHakmiliktbl = 0;
    private int bilplotTHakmiliktbl = 0;
    private int bilplotKerajaantbl = 0;
    private int bilplotKerajaanAmbiltbl = 0;
    private String noPlot;
    //s
    private BigDecimal luastot = new BigDecimal(0.00);
    //e
    private String idHakmilik;
    private BigDecimal luasHmcur = new BigDecimal(0.00);
    private final BigDecimal devisor = new BigDecimal(10000);
    private final BigDecimal devisor2 = new BigDecimal(2.471);
    private final BigDecimal devisor3 = new BigDecimal(2.4711);
    private List<HakmilikPermohonan> hpList;
    private List<NoPt> listNoPT1 = new ArrayList<NoPt>();
    private List<NoPt> listNoPT2 = new ArrayList<NoPt>();
    private List<NoPt> listNoPT3 = new ArrayList<NoPt>();
    private String pelanTatatur;
    private String praHitungan;
    private String kodNegeri;
    private String taskId;
    private String stageId;
    private Task task = null;
    private BPelService service;
    private BigDecimal luaskerajaanAmbil;
    private String perluNoPT;
    private String kodKategoriTanah;
    private String koduomAsal;
    private String kodUOM0;
    private HakmilikPermohonan hpToget0;
    private Integer bilUnit;
    
    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        
        return new JSP("pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        
    }
    
    public Resolution showFormPindaan() {
        logger.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        
        return new JSP("pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan_pindaan.jsp").addParameter("tab", "true");
        
    }
    
    public Resolution showForm2() {
        logger.info("showForm2");
        return new JSP("pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm3() {
        logger.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pembangunan/TS_MMK/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution editForm3() {
        logger.info("editForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/TS_MMK/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showHakmilikPopup() {
        logger.info("showHakmilikPopup");
        tajuk = "KHK";
        jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showRizabPopup() {
        logger.info("showRizabPopup");
        tajuk = "TKHK";
//        jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showKerajaanPopup() {
        tajuk = "AK";
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showAmbilKerajaanPopup() {
        tajuk = "AMK";
//        jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }

    // show LuasDilulus for GIS Integration 06-05-2011
    public Resolution showLuasDilulus() {
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }

    // simpam dilulusan for GIS Integration 06-05-2011
    public Resolution simpanLuasDilulus() {
        for (int i = 0; i < listNoPT1.size(); i++) {
            NoPt noPt = new NoPt();
            noPt = listNoPT1.get(i);
            pembangunanServ.simpanNoPt(noPt);
        }
        for (int i = 0; i < listNoPT2.size(); i++) {
            NoPt noPt = new NoPt();
            noPt = listNoPT2.get(i);
            pembangunanServ.simpanNoPt(noPt);
        }
        for (int i = 0; i < listNoPT3.size(); i++) {
            NoPt noPt = new NoPt();
            noPt = listNoPT3.get(i);
            pembangunanServ.simpanNoPt(noPt);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution senaraiKodGunaTanahByKatTanah() {
        logger.debug("::start filtering kod guna tanah");
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        logger.debug("kodKatTanah" + kodKatTanah);
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listGT = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/partial_kegunaanTanah.jsp").addParameter("popup", "true");
        
    }
    
    public Resolution showEditHakmilikPopup() {
        logger.info("show kemaskini hakmilik popup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        noPlot = getContext().getRequest().getParameter("noPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        tajuk = "EKHK";
//      jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        luashakmilik = mpp.getLuas();
        bilplotHakmilik = mpp.getBilanganPlot();
        //s
        noPlot = mpp.getNoPlot();
        bilUnit = mpp.getBilanganPlot();
        //e
//        katTanah = mpp.getKategoriTanah().getKod();
//        kodGunaTanah = mpp.getKegunaanTanah().getKod();
        idHakmilik = mpp.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
        luasHmcur = mpp.getLuas();
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showEditRizabPopup() {
        logger.info("show kemaskini rizab popup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        noPlot = getContext().getRequest().getParameter("noPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        tajuk = "ETKHK";
//      jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        luashakmilik = mpp.getLuas();
        bilplotHakmilik = mpp.getBilanganPlot();
        //s
        noPlot = mpp.getNoPlot();
        bilUnit = mpp.getBilanganPlot();
        luasrizab = mpp.getLuas();
        gunatanahrizab = mpp.getKegunaanTanahLain();
        //e
//        katTanah = mpp.getKategoriTanah().getKod();
//        kodGunaTanah = mpp.getKegunaanTanah().getKod();
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showEditKerajaanPopup() {
        logger.info("show kemaskini kerajaan popup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        noPlot = getContext().getRequest().getParameter("noPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        tajuk = "EAK";
//      jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        luashakmilik = mpp.getLuas();
        bilplotHakmilik = mpp.getBilanganPlot();
        luaskerajaan = mpp.getLuas();
        //s
        noPlot = mpp.getNoPlot();
        bilUnit = mpp.getBilanganPlot();
        gunatanahkerajaan = mpp.getKegunaanTanahLain();
        
        if (kodNegeri.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                perluNoPT = mpp.getNoPt();
                if (mpp.getKategoriTanah() != null) {
                    kodKategoriTanah = mpp.getKategoriTanah().getKod();
                }
            }
        }
        //e
//        katTanah = mpp.getKategoriTanah().getKod();
//        kodGunaTanah = mpp.getKegunaanTanah().getKod();
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    public Resolution showEditAmbilKerajaanPopup() {
        logger.info("show kemaskini kerajaan popup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        noPlot = getContext().getRequest().getParameter("noPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        tajuk = "EAMK";
//      jumlahPlot = Integer.parseInt((String) getContext().getRequest().getParameter("plot"));
        luashakmilik = mpp.getLuas();
        bilplotHakmilik = mpp.getBilanganPlot();
        luaskerajaanAmbil = mpp.getLuas();
        //s
        noPlot = mpp.getNoPlot();
        bilUnit = mpp.getBilanganPlot();
        gunatanahkerajaan = mpp.getKegunaanTanahLain();

        //e
//        katTanah = mpp.getKategoriTanah().getKod();
//        kodGunaTanah = mpp.getKegunaanTanah().getKod();
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_plot_popup.jsp").addParameter("popup", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("---------rhydrate start.-----------");
        bilplotHakmiliktemp = 0;
        bilplotTHakmiliktemp = 0;
        bilplotKerajaantemp = 0;
        bilplotKerajaanAmbiltemp = 0;
        bilplotHakmiliktbl = 0;
        bilplotTHakmiliktbl = 0;
        bilplotKerajaantbl = 0;
        bilplotKerajaanAmbiltbl = 0;
        jumluasHakmilik = new BigDecimal(0.00);
        jumluasTHakmilik = new BigDecimal(0.00);
        jumluasKerajaan = new BigDecimal(0.00);
        jumluasAmbilKerajaan = new BigDecimal(0.00);
        listHakmilik = new ArrayList<PermohonanPlotPelan>();
        listRizab = new ArrayList();
        listKerajaan = new ArrayList();
        listAmbilKerajaan = new ArrayList();
        bilplotHakmilik = 0;
        bilplotTHakmilik = 0;
        bilplotKerajaan = 0;
        luas = new BigDecimal(0.00);
        luastot = new BigDecimal(0.00);
        jumlahPlotYang = new BigDecimal(0.00);
        baki = new BigDecimal(0.00);
        
        System.out.println("plot hakmilik temp = " + bilplotHakmiliktemp);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        if (idPermohonan != null) {
            
            permohonan = permohonanDAO.findById(idPermohonan);
            mohonAmbil = permohonan.getPengambilan();
            
            if (mohonAmbil != null) {
                jumlahPlot = mohonAmbil.getBilanganLot();
            }
            
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            
            hpList = new ArrayList<HakmilikPermohonan>();
            hpList = permohonan.getSenaraiHakmilik();
            
            logger.info("hakmilik list." + hpList.size());
            BigDecimal meterLuas = new BigDecimal(0.00);
            BigDecimal hektarLuas = new BigDecimal(0.00);

            //LUAS PA
            BigDecimal meterLuasPA = new BigDecimal(0.00);
            BigDecimal hektarLuasPA = new BigDecimal(0.00);

            //comment out N move to top
            Hakmilik hakmilik1 = null;
            Hakmilik hakmilik2 = null;
            for (int j = 0; j < hpList.size(); j++) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp = hpList.get(j);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                koduomAsal = hakmilik.getKodUnitLuas().getKod();
                if (permohonan.getKodUrusan().getKod().equals("TSPTD")) {
                    kodUOM0 = hakmilik.getKodUnitLuas().getKod();
                }
                
                if (hakmilik.getKodUnitLuas().getKod().equals("H")) {
                    hektarLuas = hektarLuas.add(hakmilik.getLuas());
                    hakmilik1 = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    logger.info("---------hektarLuas1---------:" + hektarLuas);
                }
                if (hakmilik.getKodUnitLuas().getKod().equals("M")) {
                    meterLuas = meterLuas.add(hakmilik.getLuas());
                    hakmilik2 = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    logger.info("---------meterLuas1---------:" + meterLuas);
                }
                
            }
            //to get the first mohonHakmilik
            hpToget0 = hpList.get(0);
            
            if (hpList.size() >= 1) {
                luas = new BigDecimal(0.00);
                if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("H")) {
                    if (hakmilik1 != null) {
                        if (hakmilik2 != null) {
                            hektarLuas = hektarLuas.add(meterLuas.divide(new BigDecimal(10000)));
                        }
                        luas = luas.add(hektarLuas);
                        hakmilik = hakmilik1;
                    }
                } else {
                    meterLuas = meterLuas.add(hektarLuas.multiply(new BigDecimal(10000)));
                    luas = luas.add(meterLuas);
                    hakmilik = hakmilik2;
                }
                
            }
            
            logger.info("plot pelan list.");
            listplotpelan = new ArrayList<PermohonanPlotPelan>();
//            listplotpelan = permohonanPlotPelanDAO.findByEqualCriterias(tname, model, null);
            listplotpelan = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
            // Added code for PPT
            if (listplotpelan.isEmpty() && permohonan.getKodUrusan().getKod().equals("PPT")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    listplotpelan = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            }
            logger.info("---------listplotpelan---------list:" + listplotpelan.size());
            
            if (!(listplotpelan.isEmpty())) {
                
                for (int a = 0; a < listplotpelan.size(); a++) {
                    mpp = new PermohonanPlotPelan();
                    mpp = listplotpelan.get(a);
                    
                    if (mpp.getPemilikan().getKod() == 'H') {
                        logger.info("plot pelan hakmilik list.");
                        bilplotHakmiliktbl = mpp.getBilanganPlot();
                        
                        BigDecimal luasH = mpp.getLuas();
                        
                        if (mpp.getKodUnitLuas() != null) {
                            if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("H")) {
                                if (mpp.getKodUnitLuas().getKod().equals("M")) {
                                    logger.info("---Luas Hektar : " + luasH);
                                    luasH = luasH.divide(devisor);
                                }
                            } else if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("M")) {
                                if (mpp.getKodUnitLuas().getKod().equals("H")) {
                                    logger.info("---Luas Meter Persegi : " + luasH);
                                    luasH = luasH.multiply(devisor);
                                }
                            }
                        }
                        
                        logger.info("---Luas Hektar/Meter Persegi : " + luasH + hpToget0.getHakmilik().getKodUnitLuas().getKod());
                        logger.info("---" + luasH + " X " + mpp.getBilanganPlot());
                        
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            if (!(permohonan.getKodUrusan().getKod().equals("SBMS") || permohonan.getKodUrusan().getKod().equals("TSPSS"))) {
                                luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                            }
                        }
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            if (!permohonan.getKodUrusan().getKod().equals("SBMS")) {
                                luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                            }
                        }
                        
                        System.out.println("****** luasH ******* = " + luasH);
                        jumluasHakmilik = jumluasHakmilik.add(luasH);
                        bilplotHakmiliktemp = bilplotHakmiliktemp + bilplotHakmiliktbl;
                        System.out.println("plot hakmilik = " + bilplotHakmiliktemp);
                        listHakmilik.add(mpp);
                    } else if (mpp.getPemilikan().getKod() == 'R') {
                        logger.info("plot pelan rizab list.");
                        bilplotTHakmiliktbl = mpp.getBilanganPlot();
                        BigDecimal luasTH = mpp.getLuas();
                        
                        if (mpp.getKodUnitLuas() != null) {
                            if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("H")) {
                                if (mpp.getKodUnitLuas().getKod().equals("M")) {
                                    logger.info("---Luas Hektar : " + luasTH);
                                    luasTH = luasTH.divide(devisor);
                                }
                            } else if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("M")) {
                                if (mpp.getKodUnitLuas().getKod().equals("H")) {
                                    logger.info("---Luas Meter Persegi : " + luasTH);
                                    luasTH = luasTH.multiply(devisor);
                                }
                            }
                        }
                        logger.info("---Luas Hektar/Meter Persegi : " + luasTH + hpToget0.getHakmilik().getKodUnitLuas().getKod());
                        
                        jumluasTHakmilik = jumluasTHakmilik.add(luasTH);
                        bilplotTHakmiliktemp = bilplotTHakmiliktemp + bilplotTHakmiliktbl;
                        listRizab.add(mpp);
                    } else if (mpp.getPemilikan().getKod() == 'K') {
                        logger.info("plot pelan agensi kerajaan list.");
                        bilplotKerajaantbl = mpp.getBilanganPlot();
                        BigDecimal luasK = mpp.getLuas();
                        
                        if (mpp.getKodUnitLuas() != null) {
                            if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("H")) {
                                if (mpp.getKodUnitLuas().getKod().equals("M")) {
                                    logger.info("---Luas Hektar : " + luasK);
                                    luasK = luasK.divide(devisor);
                                }
                            } else if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("M")) {
                                if (mpp.getKodUnitLuas().getKod().equals("H")) {
                                    logger.info("---Luas Meter Persegi : " + luasK);
                                    luasK = luasK.multiply(devisor);
                                }
                            }
                        }
                        logger.info("---Luas Hektar : " + luasK + hpToget0.getHakmilik().getKodUnitLuas().getKod());
                        
                        jumluasKerajaan = jumluasKerajaan.add(luasK);
                        bilplotKerajaantemp = bilplotKerajaantemp + bilplotKerajaantbl;
                        listKerajaan.add(mpp);
                    } else if (mpp.getPemilikan().getKod() == 'A') {
                        logger.info("plot pelan ambil tanah dari kerajaan kerajaan list.");
                        bilplotKerajaanAmbiltbl = mpp.getBilanganPlot();
                        BigDecimal luasK = mpp.getLuas();
                        jumluasAmbilKerajaan = jumluasAmbilKerajaan.add(luasK);
                        bilplotKerajaanAmbiltemp = bilplotKerajaanAmbiltemp + bilplotKerajaanAmbiltbl;
                        listAmbilKerajaan.add(mpp);
                    }
                }
            }
            
            listNoPT1 = pembangunanServ.senaraiPlotHakmilikNoPTByIdPermohonan(idPermohonan);
            listNoPT2 = pembangunanServ.senaraiPlotRizabNoPTByIdPermohonan(idPermohonan);
            listNoPT3 = pembangunanServ.senaraiPlotKerajaanNoPTByIdPermohonan(idPermohonan);
            // added on 19-12-11
            jumlahPlotYang = new BigDecimal(0.00);
            baki = new BigDecimal(0.00);
            
            for (int a = 0; a < listplotpelan.size(); a++) {
                logger.info("---SAIZ---- (=.=') : " + listplotpelan.size());
                PermohonanPlotPelan mpp1 = (PermohonanPlotPelan) listplotpelan.get(a);
                if (mpp1.getPemilikan().getKod() != 'A') {
                    
                    BigDecimal luasH1 = mpp1.getLuas();
                    
                    if (mpp1.getKodUnitLuas() != null) {
                        if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("H")) {
                            if (mpp1.getKodUnitLuas().getKod().equals("M")) {
                                logger.info("---Luas Hektar : " + luasH1);
                                luasH1 = luasH1.divide(devisor);
                            }
                        } else if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("M")) {
                            if (mpp1.getKodUnitLuas().getKod().equals("H")) {
                                logger.info("---Luas Meter Persegi : " + luasH1);
                                luasH1 = luasH1.multiply(devisor);
                            }
                        } else if (hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("A") || hpToget0.getHakmilik().getKodUnitLuas().getKod().equalsIgnoreCase("P")) {
                            luasH1 = luasH1.multiply(devisor2);
                        }
                    }
                    
                    logger.info("---Luas Hektar : " + luasH1 + hpToget0.getHakmilik().getKodUnitLuas().getKod());
                    logger.info("---" + luasH1 + " X " + mpp1.getBilanganPlot());
                    
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        if (!(permohonan.getKodUrusan().getKod().equals("SBMS") || permohonan.getKodUrusan().getKod().equals("TSPSS"))) {
                            luasH1 = luasH1.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                        }
                    }
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        if (!permohonan.getKodUrusan().getKod().equals("SBMS")) {
                            luasH1 = luasH1.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                        }
                    }
                    logger.info(":::: " + luasH1);
                    //luasH1 = luasH1.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                    jumlahPlotYang = jumlahPlotYang.add(luasH1);
                    logger.info("---Luas dah guna : " + jumlahPlotYang);
                }
                logger.info("#################");
            }
            
            Dokumen dokumenSPSP = dokumenService.findDok(idPermohonan, "SPSP");
            if (dokumenSPSP != null) {
                PermohonanSemakPelan semakPelan = null;
                if (StringUtils.isNotBlank(dokumenSPSP.getNoRujukan())) {
                    semakPelan = permohonanSemakPelanDAO.findById(Long.parseLong(dokumenSPSP.getNoRujukan()));
                } else {
                }
//                PermohonanSemakPelan semakPelan = StringUtils.isNotBlank(dokumenSPSP.getNoRujukan()) ? permohonanSemakPelanDAO.findById(Long.parseLong(dokumenSPSP.getNoRujukan())) : null;
                if (semakPelan != null) {
                    BigDecimal con = semakPelan.getJumLuas();
                    kodUnitLuasPA = hakmilik.getKodUnitLuas();
                    if (hakmilik.getKodUnitLuas().getKod().equals("H")) {
                        if (semakPelan.getKodUom().getKod().equals("M")) {
                            logger.info("---Luas Hektar : " + con);
                            con = con.divide(devisor);
                            
                        }
                        baki = con;
                    } else {
                        if (semakPelan.getKodUom().getKod().equals("H")) {
                            logger.info("---Luas Hektar : " + con);
                            
                            logger.info("---Luas Meter Persegi : " + con);
                            con = con.multiply(devisor);
                            
                            
                        }
                        
                    }
                    baki = con;
                    jumluasPA = baki;
                    luas = jumluasPA;
                } else {
                    baki = luas;
                }
            } else {
                baki = luas;
            }
            
            baki = baki.subtract(jumlahPlotYang);
            logger.info("---Luas Baki : " + baki);
            HakmilikPermohonan hp1 = hpList.get(0);
            pelanTatatur = hp1.getCatatan();
            praHitungan = hp1.getNomborRujukan();
            
        }
        stageId = currentStageId();
        kodNegeri = conf.getProperty("kodNegeri");
        
        logger.info("rehydrate finish.");
    }
    
    public Resolution simpanNoRujukan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();
        logger.info("---pelanTatatur---:" + pelanTatatur);
        logger.info("---pelanTatatur---:" + praHitungan);
        for (HakmilikPermohonan hp : hpList) {
            hp.setCatatan(pelanTatatur);
            hp.setNomborRujukan(praHitungan);
            pembangunanServ.simpanHakmilikPermohonan(hp);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }


    /* Latest code to save Hakmilik Plot */
    public Resolution simpanHakmilikPlot() {
        logger.info("----------------- simpan start.------------Size---------:" + listHakmilik.size());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        logger.info("----size--- listhakmilik utk tukar syarat double check " + listHakmilik.size());
        for (int i = 0; i < listHakmilik.size(); i++) {
            String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah" + i);
            String kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);
            String idHakmilik = getContext().getRequest().getParameter("idHakmilik" + i);
            String kodUOM = getContext().getRequest().getParameter("kodUOM" + i);
            
            logger.info("---- " + kodKategoriTanah);
            logger.info("---- " + kodKegunaanTanah);
            logger.info("---- " + idHakmilik);
            logger.info("---- " + kodUOM);
            
            PermohonanPlotPelan mpp = new PermohonanPlotPelan();
            mpp = (PermohonanPlotPelan) listHakmilik.get(i);
            
            if (idHakmilik != null && !idHakmilik.equals("")) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, idHakmilik);
                mpp.setHakmilikPermohonan(hp);// set hakmilik from carian                              

            } else {
                mpp.setHakmilikPermohonan(hp);// set hakmilik from get(0)
            }
            
            if (mpp != null && mpp.getIdPlot() == 0) {
                if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                    mpp.setPermohonan(permohonan.getPermohonanSebelum());
                    mpp.setCawangan(permohonan.getPermohonanSebelum().getCawangan());
                } else {
                    mpp.setPermohonan(permohonan);
                    mpp.setCawangan(permohonan.getCawangan());
                }
                mpp.setPemilikan(kodPemilikanDAO.findById('H'));
                mpp.setNoDari(0);
                mpp.setNoKe(0);
                mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
                //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
                InfoAudit info = new InfoAudit();
                info.setTarikhMasuk(new java.util.Date());
                info.setDimasukOleh(pengguna);
                mpp.setInfoAudit(info);
            }
            
            if (kodKategoriTanah != null && !kodKategoriTanah.equals("")) {
                KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                mpp.setKategoriTanah(kodKategoriTanah1);
                hp.setJenisPenggunaanTanah(kodKategoriTanah1);
            } else {
                mpp.setKategoriTanah(null);
            }
            if (kodKegunaanTanah != null && !kodKegunaanTanah.equals("")) {
                KodKegunaanTanah kodKegunaanTanah1 = kodKegunaanTanahDAO.findById(kodKegunaanTanah);
                mpp.setKegunaanTanah(kodKegunaanTanah1);
                hp.setKodKegunaanTanah(kodKegunaanTanah1);
            } else {
                mpp.setKegunaanTanah(null);
            }
            
            if (kodUOM != null && !kodUOM.equals("")) {
                KodUOM kodUOM1 = kodUOMDAO.findById(kodUOM);
                mpp.setKodUnitLuas(kodUOM1);
            } else {
                mpp.setKodUnitLuas(null);
            }
            
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPCB") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPCBA")) {
                    mpp.setKodHakmilikTetap(kodHakmilikDAO.findById(hakmilik.getKodHakmilik().getKod()));
                }
            }
            
            pembangunanServ.simpanHakmilikPermohonan(hp);
            pembangunanServ.simpanPermohonanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration
            pembangunanServ.deleteNoPtByIdPlot(mpp.getIdPlot());
            
            for (int j = 0; j < mpp.getBilanganPlot(); j++) {
                InfoAudit ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                NoPt noPT = new NoPt();
                noPT.setLuasDilulus(mpp.getLuas());
                noPT.setKodUOM(mpp.getKodUnitLuas());
                //noPT.setKodUOM(hakmilik.getKodUnitLuas());
                if (idHakmilik != null && !idHakmilik.equals("")) {
                    noPT.setHakmilikPermohonan(hp);
                }
                kodNegeri = conf.getProperty("kodNegeri");
                KodNegeri negeri = kodNegeriDAO.findById(kodNegeri);
                noPT.setKodNegeri(negeri);
                noPT.setKodDaerah(hakmilik.getDaerah());
                noPT.setKodBpm(hakmilik.getBandarPekanMukim());
                noPT.setInfoAudit(ia);
                noPT.setPermohonanPlotPelan(mpp);
                pembangunanServ.simpanNoPt(noPT);
            }
            //end Add NoPT record for GISIntegration 
        }
        // Added on 20-12-11
        hpList = new ArrayList<HakmilikPermohonan>();
        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
            hpList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        } else {
            hpList = permohonan.getSenaraiHakmilik();
        }
        HakmilikPermohonan hp1 = hpList.get(0);
        hp1.setCatatan(pelanTatatur);
        hp1.setNomborRujukan(praHitungan);
        hp1.setBandarPekanMukimBaru(hp1.getHakmilik().getBandarPekanMukim());
        pembangunanServ.simpanHakmilikPermohonan(hp1);
        
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanHakmilikPlotSBMS() {
        logger.info("----------------- simpan start.------------Size---------:" + listHakmilik.size());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        
        for (int i = 0; i < listHakmilik.size(); i++) {
            String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah" + i);
            String kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);
            String idHakmilik = getContext().getRequest().getParameter("idHakmilik" + i);
            String kodUOM = getContext().getRequest().getParameter("kodUOM" + i);
            
            PermohonanPlotPelan mpp = new PermohonanPlotPelan();
            mpp = (PermohonanPlotPelan) listHakmilik.get(i);
            
            if (idHakmilik != null && !idHakmilik.equals("")) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, idHakmilik);
                mpp.setHakmilikPermohonan(hp);
            } else {
                HakmilikPermohonan hp1 = hpList.get(0);
                mpp.setHakmilikPermohonan(hp1);
            }
            
            if (mpp != null && mpp.getIdPlot() == 0) {
                if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                    mpp.setPermohonan(permohonan.getPermohonanSebelum());
                    mpp.setCawangan(permohonan.getPermohonanSebelum().getCawangan());
                } else {
                    mpp.setPermohonan(permohonan);
                    mpp.setCawangan(permohonan.getCawangan());
                }
                mpp.setPemilikan(kodPemilikanDAO.findById('H'));
                mpp.setNoDari(0);
                mpp.setNoKe(0);
                //mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
                //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
                InfoAudit info = new InfoAudit();
                info.setTarikhMasuk(new java.util.Date());
                info.setDimasukOleh(pengguna);
                mpp.setInfoAudit(info);
            }
            
            if (kodKategoriTanah != null && !kodKategoriTanah.equals("")) {
                KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                mpp.setKategoriTanah(kodKategoriTanah1);
                hp.setJenisPenggunaanTanah(kodKategoriTanah1);
            } else {
                mpp.setKategoriTanah(null);
            }
            if (kodKegunaanTanah != null && !kodKegunaanTanah.equals("")) {
                KodKegunaanTanah kodKegunaanTanah1 = kodKegunaanTanahDAO.findById(kodKegunaanTanah);
                mpp.setKegunaanTanah(kodKegunaanTanah1);
                hp.setKodKegunaanTanah(kodKegunaanTanah1);
            } else {
                mpp.setKegunaanTanah(null);
            }
            
            if (kodUOM != null && !kodUOM.equals("")) {
                KodUOM kodUOM1 = kodUOMDAO.findById(kodUOM);
                mpp.setKodUnitLuas(kodUOM1);
            } else {
                mpp.setKodUnitLuas(null);
            }
            
            mpp.setTempohPegangan(hakmilik.getTempohPegangan());
            mpp.setPegangan(hakmilik.getPegangan());
            mpp.setKodHakmilikTetap(hakmilik.getKodHakmilik());
            
            if (hakmilik.getKodHakmilik().getKod().equals("GRN") || hakmilik.getKodHakmilik().getKod().equals("PN")) {
                mpp.setKodHakmilikSementara(kodHakmilikDAO.findById("HSD"));
            } else if (hakmilik.getKodHakmilik().getKod().equals("GMM") || hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM")) {
                mpp.setKodHakmilikSementara(kodHakmilikDAO.findById("HSM"));
            }
            mpp.setKodSyaratNyata(hakmilik.getSyaratNyata());
            mpp.setKodSekatanKepentingan(hakmilik.getSekatanKepentingan());
            mpp.setKodHakmilikSementara(null);
            
            pembangunanServ.simpanHakmilikPermohonan(hp);
            pembangunanServ.simpanPermohonanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration
            pembangunanServ.deleteNoPtByIdPlot(mpp.getIdPlot());
            
            for (int j = 0; j < mpp.getBilanganPlot(); j++) {
                InfoAudit ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                NoPt noPT = new NoPt();
                BigDecimal bilPlot = new BigDecimal(mpp.getBilanganPlot());
                noPT.setLuasDilulus(mpp.getLuas().divide(bilPlot, 4, RoundingMode.HALF_UP));
                noPT.setKodUOM(mpp.getKodUnitLuas());
                //noPT.setKodUOM(hakmilik.getKodUnitLuas());
                if (idHakmilik != null && !idHakmilik.equals("")) {
                    noPT.setHakmilikPermohonan(hp);
                } else {
                    HakmilikPermohonan hp1 = hpList.get(0);
                    noPT.setHakmilikPermohonan(hp1);
                }
                kodNegeri = conf.getProperty("kodNegeri");
                KodNegeri negeri = kodNegeriDAO.findById(kodNegeri);
                noPT.setKodNegeri(negeri);
                noPT.setKodDaerah(hakmilik.getDaerah());
                noPT.setKodBpm(hakmilik.getBandarPekanMukim());
                noPT.setInfoAudit(ia);
                noPT.setPermohonanPlotPelan(mpp);
                pembangunanServ.simpanNoPt(noPT);
            }
            //end Add NoPT record for GISIntegration 
        }
        // Added on 20-12-11
        hpList = new ArrayList<HakmilikPermohonan>();
        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
            hpList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        } else {
            hpList = permohonan.getSenaraiHakmilik();
        }
        HakmilikPermohonan hp1 = hpList.get(0);
        hp1.setCatatan(pelanTatatur);
        hp1.setNomborRujukan(praHitungan);
        hp1.setBandarPekanMukimBaru(hp1.getHakmilik().getBandarPekanMukim());
        pembangunanServ.simpanHakmilikPermohonan(hp1);
        
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }

    //Saving Luas PA
    public Resolution deleteSingle() {
        logger.info("------deleteSingle------");
        String idPlot = getContext().getRequest().getParameter("param");
        String rowIndex = getContext().getRequest().getParameter("index");
//        try {
        if (listHakmilik.size() > 0) {
            int index = Integer.valueOf(rowIndex);
            index = index -1;
//             index = listHakmilik.size() - 1;
            PermohonanPlotPelan mpp1 = listHakmilik.get(index);
            
            
            pembangunanServ.deleteNoPtByIdPlot(mpp1.getIdPlot());
            
            List<PermohonanPlotSyaratNyata> listPpst = pembangunanServ.findPermohonanPlotSyaratNyata(mpp1.getIdPlot());
            for (PermohonanPlotSyaratNyata PPST : listPpst) {
                pembangunanServ.deletePermohonanPlotSyaratNyata(String.valueOf(PPST.getIdMppSyarat()));
            }
//                NoPt noPT = new NoPt();
//                noPT =pembangunanServ.findNoPTByIdPlotPelan(mpp1.getIdPlot());
//                if(noPT!=null){
//                    pembangunanServ.deleteNoPt(noPT);
//                }
//                permohonanPlotPelanDAO.delete(mpp1);
            pembangunanServ.deletePermohonanPlotPelan(mpp1);
        }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(MaklumatPecahSempadanActionBean.class, "locate");
    }
    
    public Resolution simpanPlotHakmilik() {
        logger.info("simpan plot hakmilik start.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            char kodmilik = 'H';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
//            hakmilik = hakmilikDAO.findById(idHakmilik);
//           HakmilikPermohonan hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, idHakmilik);

            mpp = new PermohonanPlotPelan();
            mpp.setCawangan(caw);
            mpp.setPermohonan(permohonan);
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(0);
            mpp.setNoKe(0);
            mpp.setLuas(luashakmilik);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
//            mpp.setKategoriTanah(kodKategoriTanahDAO.findById(katTanah));
//            mpp.setKegunaanTanah(kodKegunaanTanahDAO.findById(kodGunaTanah));
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);
            //e
//            mpp.setHakmilikPermohonan(hp);
            pembangunanServ.simpanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration
            NoPt noPT = new NoPt();
            noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
            if (noPT == null) {
                noPT = new NoPt();
            } else {
                ia = noPT.getInfoAudit();
            }
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(hakmilik.getKodUnitLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            
            pembangunanServ.simpanNoPt(noPT);
            //End Add NoPT record for GISIntegration

            logger.info("simpan plot hakmilik finish.");
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKemaskiniHakmilik() {
        logger.info("kemaskini hakmilik start.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia = mpp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            
            char kodmilik = 'H';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
            //HakmilikPermohonan hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, idHakmilik);

            mpp.setCawangan(caw);
            mpp.setPermohonan(permohonan);
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(0);
            mpp.setNoKe(0);
            mpp.setLuas(luashakmilik);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
//            mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
//            mpp.setKategoriTanah(kodKategoriTanahDAO.findById(katTanah));
//            mpp.setKegunaanTanah(kodKegunaanTanahDAO.findById(kodGunaTanah));
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            //e
            //mpp.setHakmilikPermohonan(hp);
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);
            pembangunanServ.simpanPlotPelan(mpp);

            // Added for GIS Integration
            NoPt noPT = new NoPt();
            noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            // noPT.setKodUOM(hakmilik.getKodUnitLuas());
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            noPT.setInfoAudit(ia);
            pembangunanServ.simpanNoPt(noPT);
            //End Added for GIS Integration

            addSimpleMessage("Kemaskini telah berjaya.");
            logger.info("kemaskini plot hakmilik finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemaskini Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanPlotRizab() {
        logger.info("simpan plot rizab start.");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            char kodmilik = 'R';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);

//            int kodrizab = Integer.parseInt(jenisRizab);
//            KodRizab kr = new KodRizab();
//            kr.setKod(kodrizab);
            mpp = new PermohonanPlotPelan();
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(0);
            mpp.setNoKe(0);
            mpp.setLuas(luasrizab);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahrizab);
//            mpp.setRizab(kr);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);

//            HakmilikPermohonan hp = pembangunanServ.findHakmilikPermohonanByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//            mpp.setHakmilikPermohonan(hp);
            pembangunanServ.simpanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration 
            NoPt noPT = new NoPt();
            kodNegeri = conf.getProperty("kodNegeri");
            KodNegeri negeri = kodNegeriDAO.findById(kodNegeri);
            noPT.setKodNegeri(negeri);
            noPT.setKodDaerah(hakmilik.getDaerah());
            noPT.setPermohonanPlotPelan(mpp);
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            //noPT.setKodUOM(hakmilik.getKodUnitLuas());
            noPT.setKodBpm(hakmilik.getBandarPekanMukim());
            noPT.setInfoAudit(ia);
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            pembangunanServ.simpanNoPt(noPT);
            //end Add NoPT record for GISIntegration 

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            logger.info("simpan plot rizab finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hunbungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKemaskiniRizab() {
        logger.info("kemaskini plot rizab start.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia = mpp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            
            char kodmilik = 'R';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);

//            int kodrizab = Integer.parseInt(jenisRizab);
//            KodRizab kr = new KodRizab();
//            kr.setKod(kodrizab);
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(0);
            mpp.setNoKe(0);
            mpp.setLuas(luasrizab);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahrizab);
//            mpp.setRizab(kr);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);
            //e
            pembangunanServ.simpanPlotPelan(mpp);

            // Added for GIS Integration
            NoPt noPT = new NoPt();
            noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            pembangunanServ.simpanNoPt(noPT);
            //End Added for GIS Integration

            addSimpleMessage("Kemaskini telah berjaya.");
            logger.info("kemaskini plot rizab finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemaskini Data Gagal.Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanPlotAmbilKerajaan() {
        logger.info("simpan plot ambil dari kerajaan finish.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            
            char kodmilik = 'A';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
            
            mpp = new PermohonanPlotPelan();
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(darikerajaan);
            mpp.setNoKe(kekerajaan);
            mpp.setLuas(luaskerajaanAmbil);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahkerajaan);

            //kod_katg_tanah
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah");
                KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                mpp.setKategoriTanah(kodKategoriTanah1);
            }
            //mpp.setKegunaanTanah(null);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            //e
            pembangunanServ.simpanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration 
            NoPt noPT = new NoPt();
            noPT.setPermohonanPlotPelan(mpp);
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            //noPT.setKodUOM(hakmilik.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            pembangunanServ.simpanNoPt(noPT);
            //end Add NoPT record for GISIntegration 

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            logger.info("simpan plot kerajaan finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanPlotKerajaan() {
        logger.info("simpan plot kerajaan finish.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            
            char kodmilik = 'K';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
            
            mpp = new PermohonanPlotPelan();
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(darikerajaan);
            mpp.setNoKe(kekerajaan);
            mpp.setLuas(luaskerajaan);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahkerajaan);

            //kod_katg_tanah && no_PT
            if (kodNegeri.equals("04")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                    String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah");
                    if (kodKategoriTanah != null) {
                        KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                        mpp.setKategoriTanah(kodKategoriTanah1);
                    }
                    mpp.setNoPt(perluNoPT);
                }
            }
            //mpp.setKegunaanTanah(null);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }
            //s
            mpp.setNoPlot(noPlot);
            //e
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);
            
            pembangunanServ.simpanPlotPelan(mpp);

            //start Add NoPT record for GISIntegration 
            NoPt noPT = new NoPt();
            noPT.setPermohonanPlotPelan(mpp);
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            //noPT.setKodUOM(hakmilik.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            /*if (mpp.getNoPt().equalsIgnoreCase("Y")) {
             noPT.setKodNegeri(kodNegeriDAO.findById(kodNegeri));
             noPT.setKodDaerah(hakmilik.getDaerah());
             noPT.setKodBpm(hakmilik.getBandarPekanMukim());
             }*/
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            pembangunanServ.simpanNoPt(noPT);
            //end Add NoPT record for GISIntegration 

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            logger.info("simpan plot kerajaan finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKemaskiniKerajaan() {
        logger.info("kemaskini plot kerajaan kerajaan.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia = mpp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            
            char kodmilik = 'K';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
            
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(darikerajaan);
            mpp.setNoKe(kekerajaan);
            mpp.setLuas(luaskerajaan);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahkerajaan);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }

            //kod_katg_tanah
            if (kodNegeri.equals("04")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                    String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah");
                    KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                    mpp.setKategoriTanah(kodKategoriTanah1);
                    mpp.setNoPt(perluNoPT);
                }
            }

            //s
            mpp.setNoPlot(noPlot);
            //e
            HakmilikPermohonan hp1 = hpList.get(0);
            mpp.setHakmilikPermohonan(hp1);
            pembangunanServ.simpanPlotPelan(mpp);

            // Added for GIS Integration
            NoPt noPT = new NoPt();
            noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            HakmilikPermohonan hp2 = hpList.get(0);
            noPT.setHakmilikPermohonan(hp2);
            pembangunanServ.simpanNoPt(noPT);
            //End Added for GIS Integration

            addSimpleMessage("Kemaskini telah berjaya.");
            logger.info("kemaskini plot kerajaan finish.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemaskini Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKemaskiniPlotAmbilKerajaan() {
        logger.info("kemaskini plot ambil kerajaan kerajaan.");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            System.out.println("Id Plot : " + idPlot);
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia = mpp.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            
            char kodmilik = 'A';
            KodPemilikan kp = new KodPemilikan();
            kp.setKod(kodmilik);
            
            mpp.setCawangan(caw);
            if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PPT")) {
                mpp.setPermohonan(permohonan.getPermohonanSebelum());
            } else {
                mpp.setPermohonan(permohonan);
            }
            mpp.setPemilikan(kp);
            mpp.setInfoAudit(ia);
            mpp.setNoDari(darikerajaan);
            mpp.setNoKe(kekerajaan);
            mpp.setLuas(luaskerajaanAmbil);
            mpp.setKodUnitLuas(kodUOMDAO.findById(kodUOM));
            //mpp.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            mpp.setKegunaanTanahLain(gunatanahkerajaan);
            //mpp.setBilanganPlot(1);
            if (bilUnit != null) {
                mpp.setBilanganPlot(bilUnit);
            } else {
                mpp.setBilanganPlot(1);
            }

            //kod_katg_tanah
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                String kodKategoriTanah = getContext().getRequest().getParameter("kodKategoriTanah");
                KodKategoriTanah kodKategoriTanah1 = kodKategoriTanahDAO.findById(kodKategoriTanah);
                mpp.setKategoriTanah(kodKategoriTanah1);
            }

            //s
            mpp.setNoPlot(noPlot);
            //e
            pembangunanServ.simpanPlotPelan(mpp);

            // Added for GIS Integration
            NoPt noPT = new NoPt();
            noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
            noPT.setLuasDilulus(mpp.getLuas());
            noPT.setKodUOM(mpp.getKodUnitLuas());
            noPT.setInfoAudit(ia);
            pembangunanServ.simpanNoPt(noPT);
            //End Added for GIS Integration

            addSimpleMessage("Kemaskini telah berjaya.");
            logger.info("kemaskini plot kerajaan finish.");
        } catch (Exception e) {
            //System.out.println("EEE : "+e);
            tx.rollback();
            addSimpleError("Kemaskini Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
        }
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
    }

//    public Resolution simpan(){
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit infoAudit = new InfoAudit();
//
//        if (permohonan != null) {
//            infoAudit = permohonan.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//        } else {
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//        }
//        if (permohonanRujukanLuar != null) {
//            permohonanRujukanLuar.setPermohonan(permohonan);
//            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
//            permohonanRujukanLuar.setInfoAudit(infoAudit);
//            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
//
//            KodDokumen kodDokumen= new KodDokumen();
//            kodDokumen.setKod("0L");
//            permohonanRujukanLuar.setKodDokumenRujukan(kodDokumen);
//            KodRujukan kodRuj = new KodRujukan();
//            kodRuj.setKod("NF");
//            permohonanRujukanLuar.setKodRujukan(kodRuj);
//            laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
//        }
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_maklumat_pecah_sempadan.jsp").addParameter("tab", "true");
//    }
    public Resolution simpanPlot() {
        logger.info("simpan plot start.");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        
        if (mohonAmbil != null) {
            logger.info("kemaskini user.");
            ia = mohonAmbil.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            logger.info("new user.");
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        if (mohonAmbil != null) {
            logger.info("kemaskini data.");
            mohonAmbil.setPermohonan(permohonan);
            mohonAmbil.setInfoAudit(ia);
            mohonAmbil.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
            mohonAmbil.setBilanganLot(jumlahPlot);
            pembangunanServ.simpanMohonAmbil(mohonAmbil);
        } else {
            logger.info("new data.");
            mohonAmbil = new PermohonanPengambilan();
            mohonAmbil.setPermohonan(permohonan);
            mohonAmbil.setInfoAudit(ia);
            mohonAmbil.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
            mohonAmbil.setBilanganLot(jumlahPlot);
            mohonAmbil.setCawangan(caw);
            mohonAmbil.setLuasTerlibat(luas);
            mohonAmbil.setKodUnitLuas(kodUOMDAO.findById(hakmilik.getKodUnitLuas().getKod()));
            pembangunanServ.simpanMohonAmbil(mohonAmbil);
        }
        
        addSimpleMessage("Plot telah disimpan.");
        return refreshpage();
    }
    
    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatPecahSempadanActionBean.class, "locate");
    }
    
    public Resolution delete() {
        logger.info("delete data start.");
        idPlot = getContext().getRequest().getParameter("idPlot");
        if (idPlot != null) {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
            if (mpp != null) {
                // Added for GIS Integration
                NoPt noPT = new NoPt();
                noPT = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
                pembangunanServ.deleteNoPt(noPT);
                //End Added for GIS Integration
                pembangunanServ.deletePermohonanPlotPelan(mpp);
                listplotpelan = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
            }
        }
        logger.info("delete data finish.");
        return refreshpage();
    }
    
    public String currentStageId() {
        service = new BPelService();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        logger.info("----stageId----:" + stageId);
        return stageId;
    }
    
    public String getTajuk() {
        return tajuk;
    }
    
    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }
    
    public List<KodKegunaanTanah> getListGT() {
        return listGT;
    }
    
    public void setListGT(List<KodKegunaanTanah> listGT) {
        this.listGT = listGT;
    }
    
    public Hakmilik getHakmilik() {
        return hakmilik;
    }
    
    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public int getBilplotHakmilik() {
        return bilplotHakmilik;
    }
    
    public void setBilplotHakmilik(int bilplotHakmilik) {
        this.bilplotHakmilik = bilplotHakmilik;
    }
    
    public int getJumlahPlot() {
        return jumlahPlot;
    }
    
    public void setJumlahPlot(int jumlahPlot) {
        this.jumlahPlot = jumlahPlot;
    }
    
    public int getJumlahPlottemp() {
        return jumlahPlottemp;
    }
    
    public void setJumlahPlottemp(int jumlahPlottemp) {
        this.jumlahPlottemp = jumlahPlottemp;
    }
    
    public String getPhkBerkuasa() {
        return phkBerkuasa;
    }
    
    public void setPhkBerkuasa(String phkBerkuasa) {
        this.phkBerkuasa = phkBerkuasa;
    }
    
    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }
    
    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    
    public int getBilplotKerajaan() {
        return bilplotKerajaan;
    }
    
    public void setBilplotKerajaan(int bilplotKerajaan) {
        this.bilplotKerajaan = bilplotKerajaan;
    }
    
    public int getBilplotTHakmilik() {
        return bilplotTHakmilik;
    }
    
    public void setBilplotTHakmilik(int bilplotTHakmilik) {
        this.bilplotTHakmilik = bilplotTHakmilik;
    }
    
    public PermohonanPlotPelan getMpp() {
        return mpp;
    }
    
    public void setMpp(PermohonanPlotPelan mpp) {
        this.mpp = mpp;
    }
    
    public int getDari() {
        return dari;
    }
    
    public void setDari(int dari) {
        this.dari = dari;
    }
    
    public String getKodGunaTanah() {
        return kodGunaTanah;
    }
    
    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }
    
    public String getKatTanah() {
        return katTanah;
    }
    
    public void setKatTanah(String katTanah) {
        this.katTanah = katTanah;
    }
    
    public int getKe() {
        return ke;
    }
    
    public void setKe(int ke) {
        this.ke = ke;
    }
    
    public String getKodUOM() {
        return kodUOM;
    }
    
    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }
    
    public BigDecimal getLuas() {
        return luas;
    }
    
    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }
    
    public int getDaririzab() {
        return daririzab;
    }
    
    public void setDaririzab(int daririzab) {
        this.daririzab = daririzab;
    }
    
    public String getGunatanahrizab() {
        return gunatanahrizab;
    }
    
    public void setGunatanahrizab(String gunatanahrizab) {
        this.gunatanahrizab = gunatanahrizab;
    }
    
    public int getKerizab() {
        return kerizab;
    }
    
    public void setKerizab(int kerizab) {
        this.kerizab = kerizab;
    }
    
    public String getKodUOMrizab() {
        return kodUOMrizab;
    }
    
    public void setKodUOMrizab(String kodUOMrizab) {
        this.kodUOMrizab = kodUOMrizab;
    }
    
    public BigDecimal getLuasrizab() {
        return luasrizab;
    }
    
    public void setLuasrizab(BigDecimal luasrizab) {
        this.luasrizab = luasrizab;
    }
    
    public int getDarikerajaan() {
        return darikerajaan;
    }
    
    public void setDarikerajaan(int darikerajaan) {
        this.darikerajaan = darikerajaan;
    }
    
    public String getGunatanahkerajaan() {
        return gunatanahkerajaan;
    }
    
    public void setGunatanahkerajaan(String gunatanahkerajaan) {
        this.gunatanahkerajaan = gunatanahkerajaan;
    }
    
    public int getKekerajaan() {
        return kekerajaan;
    }
    
    public void setKekerajaan(int kekerajaan) {
        this.kekerajaan = kekerajaan;
    }
    
    public String getKodUOMkerajaan() {
        return kodUOMkerajaan;
    }
    
    public void setKodUOMkerajaan(String kodUOMkerajaan) {
        this.kodUOMkerajaan = kodUOMkerajaan;
    }
    
    public BigDecimal getLuaskerajaan() {
        return luaskerajaan;
    }
    
    public void setLuaskerajaan(BigDecimal luaskerajaan) {
        this.luaskerajaan = luaskerajaan;
    }
    
    public PermohonanPengambilan getMohonAmbil() {
        return mohonAmbil;
    }
    
    public void setMohonAmbil(PermohonanPengambilan mohonAmbil) {
        this.mohonAmbil = mohonAmbil;
    }
    
    public List<PermohonanPlotPelan> getListHakmilik() {
        return listHakmilik;
    }
    
    public void setListHakmilik(List<PermohonanPlotPelan> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

//    public ArrayList getListHakmilik() {
//        return listHakmilik;
//    }
//
//    public void setListHakmilik(ArrayList listHakmilik) {
//        this.listHakmilik = listHakmilik;
//    }
    public ArrayList getListKerajaan() {
        return listKerajaan;
    }
    
    public void setListKerajaan(ArrayList listKerajaan) {
        this.listKerajaan = listKerajaan;
    }
    
    public ArrayList getListRizab() {
        return listRizab;
    }
    
    public void setListRizab(ArrayList listRizab) {
        this.listRizab = listRizab;
    }
    
    public String getJenisRizab() {
        return jenisRizab;
    }
    
    public void setJenisRizab(String jenisRizab) {
        this.jenisRizab = jenisRizab;
    }
    
    public BigDecimal getLuashakmilik() {
        return luashakmilik;
    }
    
    public void setLuashakmilik(BigDecimal luashakmilik) {
        this.luashakmilik = luashakmilik;
    }
    
    public BigDecimal getJumluasHakmilik() {
        return jumluasHakmilik;
    }
    
    public void setJumluasHakmilik(BigDecimal jumluasHakmilik) {
        this.jumluasHakmilik = jumluasHakmilik;
    }
    
    public BigDecimal getJumluasKerajaan() {
        return jumluasKerajaan;
    }
    
    public void setJumluasKerajaan(BigDecimal jumluasKerajaan) {
        this.jumluasKerajaan = jumluasKerajaan;
    }
    
    public BigDecimal getJumluasTHakmilik() {
        return jumluasTHakmilik;
    }
    
    public void setJumluasTHakmilik(BigDecimal jumluasTHakmilik) {
        this.jumluasTHakmilik = jumluasTHakmilik;
    }
    
    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }
    
    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }
    
    public int[] getPlotArray() {
        return plotArray;
    }
    
    public void setPlotArray(int[] plotArray) {
        this.plotArray = plotArray;
    }
    
    public int[] getPlotArray2() {
        return plotArray2;
    }
    
    public void setPlotArray2(int[] plotArray2) {
        this.plotArray2 = plotArray2;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getIdPlot() {
        return idPlot;
    }
    
    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }
    
    public int getBilplotHakmiliktemp() {
        return bilplotHakmiliktemp;
    }
    
    public void setBilplotHakmiliktemp(int bilplotHakmiliktemp) {
        this.bilplotHakmiliktemp = bilplotHakmiliktemp;
    }
    
    public int getBilplotKerajaantemp() {
        return bilplotKerajaantemp;
    }
    
    public void setBilplotKerajaantemp(int bilplotKerajaantemp) {
        this.bilplotKerajaantemp = bilplotKerajaantemp;
    }
    
    public int getBilplotTHakmiliktemp() {
        return bilplotTHakmiliktemp;
    }
    
    public void setBilplotTHakmiliktemp(int bilplotTHakmiliktemp) {
        this.bilplotTHakmiliktemp = bilplotTHakmiliktemp;
    }
    
    public int getBilplotHakmiliktbl() {
        return bilplotHakmiliktbl;
    }
    
    public void setBilplotHakmiliktbl(int bilplotHakmiliktbl) {
        this.bilplotHakmiliktbl = bilplotHakmiliktbl;
    }
    
    public int getBilplotKerajaantbl() {
        return bilplotKerajaantbl;
    }
    
    public void setBilplotKerajaantbl(int bilplotKerajaantbl) {
        this.bilplotKerajaantbl = bilplotKerajaantbl;
    }
    
    public int getBilplotTHakmiliktbl() {
        return bilplotTHakmiliktbl;
    }
    
    public void setBilplotTHakmiliktbl(int bilplotTHakmiliktbl) {
        this.bilplotTHakmiliktbl = bilplotTHakmiliktbl;
    }
    
    public String getNoPlot() {
        return noPlot;
    }
    
    public void setNoPlot(String noPlot) {
        this.noPlot = noPlot;
    }
    
    public BigDecimal getLuastot() {
        return luastot;
    }
    
    public void setLuastot(BigDecimal luastot) {
        this.luastot = luastot;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }
    
    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
    
    public List<HakmilikPermohonan> getHpList() {
        return hpList;
    }
    
    public void setHpList(List<HakmilikPermohonan> hpList) {
        this.hpList = hpList;
    }
    
    public List<NoPt> getListNoPT1() {
        return listNoPT1;
    }
    
    public void setListNoPT1(List<NoPt> listNoPT1) {
        this.listNoPT1 = listNoPT1;
    }
    
    public List<NoPt> getListNoPT2() {
        return listNoPT2;
    }
    
    public void setListNoPT2(List<NoPt> listNoPT2) {
        this.listNoPT2 = listNoPT2;
    }
    
    public List<NoPt> getListNoPT3() {
        return listNoPT3;
    }
    
    public void setListNoPT3(List<NoPt> listNoPT3) {
        this.listNoPT3 = listNoPT3;
    }
    
    public BigDecimal getBaki() {
        return baki;
    }
    
    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }
    
    public BigDecimal getJumlahPlotYang() {
        return jumlahPlotYang;
    }
    
    public void setJumlahPlotYang(BigDecimal jumlahPlotYang) {
        this.jumlahPlotYang = jumlahPlotYang;
    }
    
    public String getPelanTatatur() {
        return pelanTatatur;
    }
    
    public void setPelanTatatur(String pelanTatatur) {
        this.pelanTatatur = pelanTatatur;
    }
    
    public String getPraHitungan() {
        return praHitungan;
    }
    
    public void setPraHitungan(String praHitungan) {
        this.praHitungan = praHitungan;
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
    
    public ArrayList getListAmbilKerajaan() {
        return listAmbilKerajaan;
    }
    
    public void setListAmbilKerajaan(ArrayList listAmbilKerajaan) {
        this.listAmbilKerajaan = listAmbilKerajaan;
    }
    
    public BigDecimal getJumluasAmbilKerajaan() {
        return jumluasAmbilKerajaan;
    }
    
    public void setJumluasAmbilKerajaan(BigDecimal jumluasAmbilKerajaan) {
        this.jumluasAmbilKerajaan = jumluasAmbilKerajaan;
    }
    
    public BigDecimal getLuaskerajaanAmbil() {
        return luaskerajaanAmbil;
    }
    
    public void setLuaskerajaanAmbil(BigDecimal luaskerajaanAmbil) {
        this.luaskerajaanAmbil = luaskerajaanAmbil;
    }
    
    public String getPerluNoPT() {
        return perluNoPT;
    }
    
    public void setPerluNoPT(String perluNoPT) {
        this.perluNoPT = perluNoPT;
    }
    
    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }
    
    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }
    
    public int getBilplotKerajaanAmbiltemp() {
        return bilplotKerajaanAmbiltemp;
    }
    
    public void setBilplotKerajaanAmbiltemp(int bilplotKerajaanAmbiltemp) {
        this.bilplotKerajaanAmbiltemp = bilplotKerajaanAmbiltemp;
    }
    
    public int getBilplotKerajaanAmbiltbl() {
        return bilplotKerajaanAmbiltbl;
    }
    
    public void setBilplotKerajaanAmbiltbl(int bilplotKerajaanAmbiltbl) {
        this.bilplotKerajaanAmbiltbl = bilplotKerajaanAmbiltbl;
    }
    
    public String getKoduomAsal() {
        return koduomAsal;
    }
    
    public void setKoduomAsal(String koduomAsal) {
        this.koduomAsal = koduomAsal;
    }
    
    public HakmilikPermohonan getHpToget0() {
        return hpToget0;
    }
    
    public void setHpToget0(HakmilikPermohonan hpToget0) {
        this.hpToget0 = hpToget0;
    }
    
    public String getKodUOM0() {
        return kodUOM0;
    }
    
    public void setKodUOM0(String kodUOM0) {
        this.kodUOM0 = kodUOM0;
    }
    
    public Integer getBilUnit() {
        return bilUnit;
    }
    
    public void setBilUnit(Integer bilUnit) {
        this.bilUnit = bilUnit;
    }
    
    public KodUOM getKodUnitLuasPA() {
        return kodUnitLuasPA;
    }
    
    public void setKodUnitLuasPA(KodUOM kodUnitLuasPA) {
        this.kodUnitLuasPA = kodUnitLuasPA;
    }
    
    public BigDecimal getJumluasPA() {
        return jumluasPA;
    }
    
    public void setJumluasPA(BigDecimal jumluasPA) {
        this.jumluasPA = jumluasPA;
    }
}
