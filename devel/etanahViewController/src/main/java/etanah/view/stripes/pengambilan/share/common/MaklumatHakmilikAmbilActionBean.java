/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPihakBerkepentingan;
import java.math.BigDecimal;
import etanah.service.CalcTax;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.model.KodRizab;
import etanah.model.KodDaerah;
import etanah.model.KodBandarPekanMukim;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.PermohonanPihak;

import org.apache.commons.lang.StringUtils;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.model.KodUrusan;

import etanah.view.etanahActionBeanContext;
import java.util.*;
import etanah.model.KodCawangan;
import etanah.model.KodLot;
import etanah.model.KodSeksyen;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.PengambilanService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.ListUtil;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import org.hibernate.Session;
import org.hibernate.Query;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/pindaan/maklumat_hakmilikpengambilan")
public class MaklumatHakmilikAmbilActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatHakmilikAmbilActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    DisLaporanTanahService disLTS;
    @Inject
    LaporanPelukisPelanService serviceTanah;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    String idHakmilik;
    @Inject
    Hakmilik hakmilik;
    @Inject
    KodUOM KodOUM;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    private String kodCawangan;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> luasTerlibat1 = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private List<TanahRizabPermohonan> senaraiTanahAA;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private BigDecimal luasT;
    private String kodUnit;
    private int bandarPekanMukimBaru;
    private String kodUnitAmbil;
    private BigDecimal luasTAmbil;
    private String stageId;

    public List<TanahRizabPermohonan> getSenaraiTanahAA() {
        return senaraiTanahAA;
    }

    public void setSenaraiTanahAA(List<TanahRizabPermohonan> senaraiTanahAA) {
        this.senaraiTanahAA = senaraiTanahAA;
    }
    private TanahRizabPermohonan rizab;
    private List<TanahRizabPermohonan> tanahRizabList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihak mp;
    private int size = 0;
    private String[] listluasTerlibat;
    private String noHakmilik;
    private KodUrusan mohonKodUrusan;
    private BigDecimal convLuas;
//    private BigDecimal totalLuas;
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    private String namajaga;
    private String jagaTel;
    private String jagaFax;
    private String jagaEmail;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodRizab kodRizab;
    private String noLot;
    private String luasAmbil;
    private String adaCukai;
    private String cukai;
    private String pegangan;
    private String penarikBalikan;
    private String bpm;
    private KodDaerah daerah;
    private String koddaerahbpm;
    private String kodDaerah;
    private String kodBandarPekanMukim;
    private List<KodBandarPekanMukim> senaraiBPM;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private Permohonan permohonan;
    private static String staticKodBandarPekanMukim;
    private KodCawangan cawangan;
    private String idTanahRizabPermohonan;
    private String flagPBA = "0";
    private String no = "1";
    private String kodLot;
    private List<KodSeksyen> listKodSeksyen;
    private String kodSeksyen;
    private PermohonanPengambilanHakmilik permohonanPengambilan;
    private List<PermohonanPengambilanHakmilik> listpermohonanPengambilan;
    private List<PermohonanPengambilanHakmilik> listPermohonanPengambilan;
    private List<MaklumatTanahPengambilanForm> listmaklumatTanahPengambilanForms;
    private List<MaklumatTanahPengambilanXActiveForm> listmaklumatTanahPengambilanXactiveForms;
    private List<HakmilikPermohonan> listHakmilikPermohonanActive;
    private List<HakmilikPermohonan> listHakmilikPermohonanXActive;

    @DefaultHandler
    public Resolution showForm() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<HakmilikPermohonan> List = hakmilikpermohonanservice.findByIdPermohonan(idPermohonan);
        stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");
        
        listHakmilikPermohonanXActive = new ArrayList<HakmilikPermohonan>();
        listHakmilikPermohonanActive = new ArrayList<HakmilikPermohonan>();
        for (HakmilikPermohonan hp : List) {
            if (hp.getHakmilikAmbil().equals("A")) {
                listHakmilikPermohonanActive.add(hp);
            } else if (hp.getHakmilikAmbil().equals("TA")) {
                listHakmilikPermohonanXActive.add(hp);
            } else {
                listHakmilikPermohonanActive.add(hp);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/APT/hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanFlag() {

        String idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        hakmilikPermohonan = hakmilikpermohonanservice.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        hakmilikPermohonan.setHakmilikAmbil("TA");
        hakmilikpermohonanservice.save(hakmilikPermohonan);
        rehydrate();
        //find idMh in mohon_ambil_hakmilik
//        permohonanPengambilan = new PermohonanPengambilanHakmilik();
//        permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(Long.parseLong(idMohonHakmilik));

        return new JSP("pengambilan/APT/hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }
     public Resolution simpanFlagActive() {

        String idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        hakmilikPermohonan = hakmilikpermohonanservice.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        hakmilikPermohonan.setHakmilikAmbil("A");
        hakmilikpermohonanservice.save(hakmilikPermohonan);
        rehydrate();
        //find idMh in mohon_ambil_hakmilik
//        permohonanPengambilan = new PermohonanPengambilanHakmilik();
//        permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(Long.parseLong(idMohonHakmilik));

        return new JSP("pengambilan/APT/hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        String idMH = getContext().getRequest().getParameter("idMH");
        hakmilikPermohonan = hakmilikpermohonanservice.findHakmilikPermohonanbyidMH(Long.parseLong(idMH));
        //find idMh in mohon_ambil_hakmilik
//        permohonanPengambilan = new PermohonanPengambilanHakmilik();
        permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(Long.parseLong(idMH));
//
//        if (permohonanPengambilan != null) {
//            KodUOM kUOMAmbil = new KodUOM();
//            kUOMAmbil = kodUOMDAO.findById(permohonanPengambilan.getKodUnitLuas().getKod());
//
//            kodUnitAmbil = kUOMAmbil.getKod();
//            luasTAmbil = permohonanPengambilan.getLuasAmbil();
//        }

        kodDaerah = permohonan.getCawangan().getDaerah().getKod();
        kodCawangan = permohonan.getCawangan().getKod();
        if (kodCawangan.equals("00")) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
        } else {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerahAndCawangan(kodDaerah, kodCawangan);
        }

        return new JSP("pengambilan/APT/kemasukan_lot.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }
    
     public Resolution hantar() {
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);        

             etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();
             sek8aIntegrationFlowService.completeTask(ref8a.AGIHAN_LAPORAN_TANAH, permohonan, pengguna);
     
        return new RedirectResolution(TugasanActionBean.class);
    }

    public Resolution findKodSeksyen() {
        String kodBpm = getContext().getRequest().getParameter("kod");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listKodSeksyen = disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(kodBpm);
        logger.info("---listKodSeksyen---:" + listKodSeksyen.size());
        return new JSP("pengambilan/kemasukan_hakmilik.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        logger.info("kod Urusan permohonan :" + permohonan.getKodUrusan().getKod());
        stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");

        hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonanOnly(idPermohonan);
        listmaklumatTanahPengambilanForms = new ArrayList<MaklumatTanahPengambilanForm>();
        listmaklumatTanahPengambilanXactiveForms = new ArrayList<MaklumatTanahPengambilanXActiveForm>();
        if (hakmilikPermohonanList.size() > 0) {
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                MaklumatTanahPengambilanForm f = new MaklumatTanahPengambilanForm();
                MaklumatTanahPengambilanXActiveForm x = new MaklumatTanahPengambilanXActiveForm();
                f.setMohonHakmilik(hp);
                x.setMohonHakmilik(hp);
                if (hp.getHakmilikAmbil() != null) {
                    if (hp.getHakmilikAmbil().equals("A")) {
                        listmaklumatTanahPengambilanForms.add(f);
                    } else if (hp.getHakmilikAmbil().equals("TA")) {
                        listmaklumatTanahPengambilanXactiveForms.add(x);
                    }
                }else {
                    hp.setHakmilikAmbil("A");
                    hakmilikpermohonanservice.save(hp);
                    listmaklumatTanahPengambilanForms.add(f);
                }

//                listmaklumatTanahPengambilanForms.add(f);
                permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(hp.getId());
                if (permohonanPengambilan != null) {
                    f.setLuasAmbil(permohonanPengambilan.getLuasAmbil());

                }
            }
        }

    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

//    public String[] getAtasTanah() {
//        return luasTerlibat;
//    }
//
//    public void setAtasTanah(String[] luasTerlibat) {
//        this.luasTerlibat = luasTerlibat;
//    }
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

//        public List<String> getLuasdiambil() {
//        return luasdiambil;
//    }
//
//    public void setLuasdiambil(List<String> luasdiambil) {
//        this.luasdiambil = luasdiambil;
//    }
    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public KodUOM getKodOUM() {
        return KodOUM;
    }

    public void setKodOUM(KodUOM KodOUM) {
        this.KodOUM = KodOUM;
    }

    public TanahRizabPermohonan getRizab() {
        return rizab;
    }

    public void setRizab(TanahRizabPermohonan rizab) {
        this.rizab = rizab;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getKoddaerahbpm() {
        return koddaerahbpm;
    }

    public void setKoddaerahbpm(String koddaerahbpm) {
        this.koddaerahbpm = koddaerahbpm;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getadaCukai() {
        return adaCukai;
    }

    public void setAdaCukai(String adaCukai) {
        this.adaCukai = adaCukai;
    }

    public String getcukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(String luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getJagaEmail() {
        return jagaEmail;
    }

    public void setJagaEmail(String jagaEmail) {
        this.jagaEmail = jagaEmail;
    }

    public String getJagaFax() {
        return jagaFax;
    }

    public void setJagaFax(String jagaFax) {
        this.jagaFax = jagaFax;
    }

    public String getJagaTel() {
        return jagaTel;
    }

    public void setJagaTel(String jagaTel) {
        this.jagaTel = jagaTel;
    }

    public String getNamajaga() {
        return namajaga;
    }

    public void setNamajaga(String namajaga) {
        this.namajaga = namajaga;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getconvLuas() {
        return convLuas;
    }

    public void setconvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public BigDecimal gettotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public PermohonanPihak getMp() {
        return mp;
    }

    public void setMp(PermohonanPihak mp) {
        this.mp = mp;
    }

    public String getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(String kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public static String getStaticKodBandarPekanMukim() {
        return staticKodBandarPekanMukim;
    }

    public static void setStaticKodBandarPekanMukim(String staticKodBandarPekanMukim) {
        MaklumatHakmilikAmbilActionBean.staticKodBandarPekanMukim = staticKodBandarPekanMukim;
    }

    public String getIdTanahRizabPermohonan() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizabPermohonan(String idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    /**
     * @return the pegangan
     */
    public String getPegangan() {
        return pegangan;
    }

    /**
     * @param pegangan the pegangan to set
     */
    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getPenarikBalikan() {
        return penarikBalikan;
    }

    public void setPenarikBalikan(String penarikBalikan) {
        this.penarikBalikan = penarikBalikan;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public int getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public void setBandarPekanMukimBaru(int bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
    }

    public String getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(String kodUnit) {
        this.kodUnit = kodUnit;
    }

    public BigDecimal getLuasT() {
        return luasT;
    }

    public void setLuasT(BigDecimal luasT) {
        this.luasT = luasT;
    }

    public List<String> getLuasTerlibat1() {
        return luasTerlibat1;
    }

    public void setLuasTerlibat1(List<String> luasTerlibat1) {
        this.luasTerlibat1 = luasTerlibat1;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MaklumatHakmilikAmbilActionBean.logger = logger;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public HakmilikPermohonanService getHakmilikpermohonanservice() {
        return hakmilikpermohonanservice;
    }

    public void setHakmilikpermohonanservice(HakmilikPermohonanService hakmilikpermohonanservice) {
        this.hakmilikpermohonanservice = hakmilikpermohonanservice;
    }

    public DisLaporanTanahService getDisLTS() {
        return disLTS;
    }

    public void setDisLTS(DisLaporanTanahService disLTS) {
        this.disLTS = disLTS;
    }

    public LaporanPelukisPelanService getServiceTanah() {
        return serviceTanah;
    }

    public void setServiceTanah(LaporanPelukisPelanService serviceTanah) {
        this.serviceTanah = serviceTanah;
    }

    public HakmilikService getHakmilikService() {
        return hakmilikService;
    }

    public void setHakmilikService(HakmilikService hakmilikService) {
        this.hakmilikService = hakmilikService;
    }

    public PermohonanSupayaBantahanService getPermohonanSupayaBantahanService() {
        return permohonanSupayaBantahanService;
    }

    public void setPermohonanSupayaBantahanService(PermohonanSupayaBantahanService permohonanSupayaBantahanService) {
        this.permohonanSupayaBantahanService = permohonanSupayaBantahanService;
    }

    public KodSeksyenDAO getKodSeksyenDAO() {
        return kodSeksyenDAO;
    }

    public void setKodSeksyenDAO(KodSeksyenDAO kodSeksyenDAO) {
        this.kodSeksyenDAO = kodSeksyenDAO;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public CalcTax getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTax calcTax) {
        this.calcTax = calcTax;
    }

    public KodUOMDAO getKodUOMDAO() {
        return kodUOMDAO;
    }

    public void setKodUOMDAO(KodUOMDAO kodUOMDAO) {
        this.kodUOMDAO = kodUOMDAO;
    }

    public KodLotDAO getKodLotDAO() {
        return kodLotDAO;
    }

    public void setKodLotDAO(KodLotDAO kodLotDAO) {
        this.kodLotDAO = kodLotDAO;
    }

    public TanahRizabPermohonanDAO getTanahrizabPermohonanDAO() {
        return tanahrizabPermohonanDAO;
    }

    public void setTanahrizabPermohonanDAO(TanahRizabPermohonanDAO tanahrizabPermohonanDAO) {
        this.tanahrizabPermohonanDAO = tanahrizabPermohonanDAO;
    }

    public PermohonanPengambilanHakmilikDAO getPermohonanPengambilanHakmilikDAO() {
        return permohonanPengambilanHakmilikDAO;
    }

    public void setPermohonanPengambilanHakmilikDAO(PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO) {
        this.permohonanPengambilanHakmilikDAO = permohonanPengambilanHakmilikDAO;
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public PengambilanService getPengambilanService() {
        return pengambilanService;
    }

    public void setPengambilanService(PengambilanService pengambilanService) {
        this.pengambilanService = pengambilanService;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public KodUrusan getMohonKodUrusan() {
        return mohonKodUrusan;
    }

    public void setMohonKodUrusan(KodUrusan mohonKodUrusan) {
        this.mohonKodUrusan = mohonKodUrusan;
    }

    public BigDecimal getConvLuas() {
        return convLuas;
    }

    public void setConvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getFlagPBA() {
        return flagPBA;
    }

    public void setFlagPBA(String flagPBA) {
        this.flagPBA = flagPBA;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public PermohonanPengambilanHakmilik getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilanHakmilik permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public List<PermohonanPengambilanHakmilik> getListPermohonanPengambilan() {
        return listPermohonanPengambilan;
    }

    public void setListPermohonanPengambilan(List<PermohonanPengambilanHakmilik> listPermohonanPengambilan) {
        this.listPermohonanPengambilan = listPermohonanPengambilan;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getKodUnitAmbil() {
        return kodUnitAmbil;
    }

    public void setKodUnitAmbil(String kodUnitAmbil) {
        this.kodUnitAmbil = kodUnitAmbil;
    }

    public BigDecimal getLuasTAmbil() {
        return luasTAmbil;
    }

    public void setLuasTAmbil(BigDecimal luasTAmbil) {
        this.luasTAmbil = luasTAmbil;
    }

    public List<PermohonanPengambilanHakmilik> getListpermohonanPengambilan() {
        return listpermohonanPengambilan;
    }

    public void setListpermohonanPengambilan(List<PermohonanPengambilanHakmilik> listpermohonanPengambilan) {
        this.listpermohonanPengambilan = listpermohonanPengambilan;
    }

    public List<MaklumatTanahPengambilanForm> getListmaklumatTanahPengambilanForms() {
        return listmaklumatTanahPengambilanForms;
    }

    public void setListmaklumatTanahPengambilanForms(List<MaklumatTanahPengambilanForm> listmaklumatTanahPengambilanForms) {
        this.listmaklumatTanahPengambilanForms = listmaklumatTanahPengambilanForms;
    }

    public PengambilanAPTService getPengambilanAPTService() {
        return pengambilanAPTService;
    }

    public void setPengambilanAPTService(PengambilanAPTService pengambilanAPTService) {
        this.pengambilanAPTService = pengambilanAPTService;
    }

    public List<MaklumatTanahPengambilanXActiveForm> getListmaklumatTanahPengambilanXactiveForms() {
        return listmaklumatTanahPengambilanXactiveForms;
    }

    public void setListmaklumatTanahPengambilanXactiveForms(List<MaklumatTanahPengambilanXActiveForm> listmaklumatTanahPengambilanXactiveForms) {
        this.listmaklumatTanahPengambilanXactiveForms = listmaklumatTanahPengambilanXactiveForms;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonanActive() {
        return listHakmilikPermohonanActive;
    }

    public void setListHakmilikPermohonanActive(List<HakmilikPermohonan> listHakmilikPermohonanActive) {
        this.listHakmilikPermohonanActive = listHakmilikPermohonanActive;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonanXActive() {
        return listHakmilikPermohonanXActive;
    }

    public void setListHakmilikPermohonanXActive(List<HakmilikPermohonan> listHakmilikPermohonanXActive) {
        this.listHakmilikPermohonanXActive = listHakmilikPermohonanXActive;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    

}
