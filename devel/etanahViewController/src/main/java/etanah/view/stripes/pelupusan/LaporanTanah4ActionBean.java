/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodTanahDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.model.KodKeadaanTanah;
import etanah.model.KodDUN;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodKecerunanTanah;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.model.KodDaerah ;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.KodKadarPremium;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKeputusan;
import etanah.model.KodPemilikan;
import etanah.model.KodStrukturTanah;
import etanah.model.KodTanah;
import etanah.model.KodTuntut;
import etanah.model.NoPt;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanPermit;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.TanahRizabService;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collections;
import net.sourceforge.stripes.action.FileBean;
import org.hibernate.Transaction;
import org.hibernate.Session;
/**simpanLaporanTanah
 *
 * @author Murali
 * @Modified Shazwan
 */
@UrlBinding("/pelupusan/laporan_tanah4")
public class LaporanTanah4ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    CommonService commonService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO ;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO ;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    //carian hakmilik
    @Inject
    RegService regService;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    List<HakmilikPermohonan> senaraiHakmilik;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String kodNegeri;
    private boolean checkTanahExist = false;
    private NoPt noPT = new NoPt();
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Hakmilik hakmilik;
    private KodTanah kodTanah;
    private KodHakmilik kodHakmilik;
    private KodPemilikan kodPemilikan;
   // private KodUOM kodUOM;
    private KodKeadaanTanah kodKeadaanTanah;
    private KodTuntut kodTuntut;
    private KodKadarPremium kodKadarPremium;
    private List<String> senaraikodKadarPremium;
    private KodItemPermit kodItemPermit;
    private String idHakmilik;
    private BigDecimal luasDilulus;
    private Permohonan permohonan;
    private KodDUN kodDUN;
    private KodKawasanParlimen kodKawasanParlimen;
    private KodKeputusan kodKeputusan;
    private KodStrukturTanah kodStrukturTanah;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private TanahRizabPermohonan tanahRizabPermohonan;
    private TanahRizabPermohonan tanahrizabpermohonan1;
    private HakmilikPermohonan hakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(LaporanTanah4ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<TanahRizabPermohonan> tanahRizabPermohonanList;
    private char pandanganImej;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private List<ImejLaporan> hakmilikImejLaporanList;
    private FileBean fileToBeUpload;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String catatan;
    private KodBandarPekanMukim bandarPekanMukim;
     private KodKecerunanTanah kecerunanTanah;
     private KodDaerah daerah;
     private KodCawangan cawangan;
     private String noLot;
     private String noLitho;
     private String noWarta;
     private String lokasi;
     private KodRizab rizab;
     private String tarikhWarta;
     private String idtanahrizabPermohonan;
     private String ulasan;
     private String mohonlaporulasan;
     boolean p ;
     private List<KodSeksyen> kodSeksyenList;
     private Pengguna pguna;
     private String idPermohonan;
     private PermohonanLaporanKawasan permohonanLaporanKawasan;
     private String pbt;
     private  String  rizab1;
     private  String rizab2;
     private PermohonanManual permohonanManual;
     private Pemohon pemohon;
     private PermohonanPermitItem pmi = new PermohonanPermitItem();
     Permohonan prmhnn ;
     String id2;
     String id;
     private List<PermohonanManual> permohonanManualList;
     Long idlapor;
     private String index;
     private String kod;
     private String syaratNyata;
     private String syaratNyata1;
     private String kodSktn;
     private List<KodSekatanKepentingan> listKodSekatan;
     private String kodSekatanKepentingan;
     private String sekatKpntgn2;
     private String kodSyaratNyata;
     private List<KodSyaratNyata> listKodSyaratNyata;
     private String syaratNyata2;
     private String kodHmlk;
     private String kodHmlkTetap;
     private String kodU;
     private String kodUPlps;
     private String keadaanTanah;
     private String negeri;
     //Long keadaanTanah;
     private String keteranganKadarPremium;
     private char kodP;
     private String tanahR;
     private String kodT;
     private String kodD;
     private String kodPar;
     private String kecerunanT;
     private String klasifikasiT;
     private String ksn;
     private PermohonanPermitItem permohonanPermitItem;
     private PermohonanTuntutanKos permohonanTuntutanKos;
     private String keg;
     private BigDecimal amnt = BigDecimal.ZERO;
     private BigDecimal usahaLuas;
     private Integer usahaBilanganPokok = new Integer(0);
     private BigDecimal usahaHarga;
     private List<Hakmilik> list;
     //tambah bangunan
     private String kategori;
     private PermohonanLaporanBangunan permohonanLaporanBangunan;
     @Inject
     PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
     private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList;
     private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
     private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList;
     private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
     private HakmilikUrusan hakmilikUrusan;
     @Inject
     HakmilikUrusanService hakmilikUrusanService;
     private PermohonanLaporanUlasan laporanUlasan;
     private char jenisKegunaan;
     private char adaJalanMasuk;
     //private char adaPecahSempadan;
     private String adaPecahSempadan;
     private Boolean display = Boolean.FALSE;
     @Inject
     KodJenisPendudukanDAO jenisPendudukanDAO;
     // new
     private String plpulasan0;
     private String ulsn;
     //new
     private String pbt1;
     private String hutan;
     private String rizab_melayu;
     private String gsa;
     private String lain;
     private String catatanLain1;
     private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
     private PermohonanLaporanKandungan permohonanLaporanKandungan;
     @Inject
     PermohonanLaporanKandunganDAO permohonanLaporanKandunganDAO;
     private String kand;
     private String keadaankand;
     private List<PermohonanLaporanUlasan> senaraiLaporanKandungan1;
     private int rowCount2;
     private List<PermohonanLaporanUlasan> senarailaporanUlasan;
     private String kodRizab;
     private String addnoWarta;
     private String addtarikhWarta;
     private String addnoPelanWarta;     
     private String addcatatan;
     private String catatanKeg;
     private String ulasanKanan;
     private String statBdnPngwl;
     private PermohonanBahanBatuan permohonanBahanBatuan ;
     private KodUOM jumlahIsipaduUOM ;
     private BigDecimal jumlahIsipadu ;
     private String tempohSyorUOM;
     private PermohonanPermit permohonanPermit;
     
     //SYARAT-SYARAT
     private String jumlahKeneBayar;
     private double cagarKeneBayar;
     private double kuponAmaun;
     private int kuponQty ;
     private BigDecimal kupon;
     private BigDecimal cagarJalan;
     private BigDecimal totalAll;
     private PermohonanTuntutanKos mohonTuntutKos ;
     private PermohonanBahanBatuan syaratBahanBatu;
     
     //DEFAULT SYARAT-SYARAT VALUE
     private String noktah;
     private String noktahbertindih;
     private String tajuk;
     private String tajuk2;
     private String tajuk3;
     private String tajuk4;
     private String tajuk5;
     private String tujuan;
     private String tujuan2;
     private String tujuan3;
     private String tujuan4;
     private String tujuan5;
     private String tujuan6;
     private String tujuan7;
     private String perihalpermohonan;
     private String perihalpermohonan2;
     private String perihalpermohonan3;
     private String perihalpemohon;
     private String perihalpemohon2;
     private String perihalpemohon3;
     private String perihalpemohon4;
     private String perihaltanah1;
     private String perihaltanah12;
     private String perihaltanah13;
     private String perihaltanah14;
     private String perihaltanah15;
     private String perihaltanah2;
     private String perihaltanah21;
     private String perihaltanah22;
     private String perihaltanah23;
     private String perihaltanah24;
     private String perihaltanah25;
     private String perihaltanah26;
     private String perihaltanah27;
     private String perihaltanah28;
     private String perihaltanah29;
     private String perihaltanah210;
     private String perihaltanah211;
     private String perakuan;    
     private String perakuan2;
     private String perakuan3;
     private String perakuan4;
     private String perakuan5;
     private String perakuan6;
     private String perakuan7;
     private String perakuan8;
     private String kuantiti;
     private String kuantiti2;
     private String tempoh;
     private String kadarBayar;
     private String kadarBayar2;
     private String jumlahBayar;
     private String jumlahBayar2;
     private String jumlahBayar3;
     private String wangCagar;
     private String wangCagar2;
     private String no6;
     private String no6a;
     private String no7;
     private String no7a;
     private String no8;
     private String no8a;
     private String no9;
     private String no9a;
     private String no10;
     private InfoAudit ia;
     private Pengguna peng;
     private String checkId;
     private String pasId;
     private String testElement;
     private String countElement;
     
    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
       // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {        
        LOG.info("View Laporan Tanah");
        display = Boolean.TRUE;
        return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");

    }
    public Resolution permohonanTerdahuluPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/carian_permohonan_terdahulu1.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

     public Resolution carianHakmilikPopup(){
         LOG.info("---carianHakmilikPopup Start-----::");
         list = Collections.EMPTY_LIST;
        return new JSP("pelupusan/carian_Hakmilik_Popup.jsp").addParameter("popup", "true").addParameter("showForm", "false");
     }


     //TambahBangunan`s Tambah :Start
     public Resolution tambahBangunanPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        return new JSP("pelupusan/laporan_tanah_popup.jsp").addParameter("popup", "true");
    }

    public Resolution editBangunanPopup() {
        String idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanah_popup.jsp").addParameter("popup", "true");
    }
    public Resolution addBilBangunan() throws Exception {
        
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println(idPermohonan);
        boolean saveOnly = false;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
             if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
             hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
         }
         else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                 senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                 if(senaraiHakmilikTerlibat.size()>0)
                     hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
             }
             else
                 hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
         
         }
//            hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);
            LaporanTanah mohonLaporTanahTemp = new LaporanTanah();
            mohonLaporTanahTemp = laporanTanah;
            laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();
            if(laporanTanah==null){
                laporanTanah = new LaporanTanah();
                laporanTanah = mohonLaporTanahTemp;
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanTanah.setInfoAudit(infoAudit);
                laporanTanah.setPermohonan(permohonan);
                saveOnly = Boolean.TRUE;
            }else{
                infoAudit = laporanTanah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                laporanTanah.setInfoAudit(infoAudit);
                saveOnly = Boolean.FALSE;
            }
                
        }
        String data = getContext().getRequest().getParameter("status");
         char chardata=data.charAt(0);
        laporanTanah.setAdaBangunan(chardata);
        
        if(saveOnly)
            plpservice.simpanLaporanTanah(laporanTanah);
        else
            plpservice.simpanSaveorUpdateLaporanTanah(laporanTanah);
        if (kodD != null) {
                KodDUN kd = kodDUNDAO.findById(kodD);
                hakmilikPermohonan.setKodDUN(kd);
            }
         if (kodPar != null) {
                KodKawasanParlimen kw = kodKawasanParlimenDAO.findById(kodPar);
                hakmilikPermohonan.setKodKawasanParlimen(kw);
            }
        plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        getContext().getRequest().setAttribute("display", display);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    } 
    public Resolution removeLaporKawasan()  {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idMohonlaporKws = getContext().getRequest().getParameter("idMohonlaporKws");

        if (idMohonlaporKws != null) {
            PermohonanLaporanKawasan mohonLaporKwsn = new PermohonanLaporanKawasan();
            mohonLaporKwsn = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));
            if (mohonLaporKwsn != null) {
                plpservice.deletePermohonanLaporanKwsn(mohonLaporKwsn);

            }
        }

        return new RedirectResolution(LaporanTanah4ActionBean.class, "locate");
    }
     public Resolution simpanBangunan() {
          LOG.info("------simpanBangunan()--------------::");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
             if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
             hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
         }
         else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                 senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                 if(senaraiHakmilikTerlibat.size()>0)
                     hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
             }
             else
                 hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
         
         }
           // hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);
            InfoAudit infoAudit;
            laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanTanah.setInfoAudit(infoAudit);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            }
            if(kategori.equalsIgnoreCase("B")){
                laporanTanah.setAdaBangunan('Y');
                laporanTanah.setUsaha(' ');
                laporanTanah.setUsahaTanam(null);
                laporanTanah.setUsahaLuas(null);
                laporanTanah.setUsahaBilanganPokok(null);
                laporanTanah.setUsahaHarga(null);
                laporanTanah.setPerusahaan(null);
                if(plpservice.findLaporanTanahByIdPermohonan(idPermohonan) != null){
                    permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"P");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                    permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"PU");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                }
            }else if(kategori.equalsIgnoreCase("P")){
                laporanTanah.setUsaha('Y');

                laporanTanah.setAdaBangunan(' ');
                laporanTanah.setPerusahaan(null);
                    if(plpservice.findLaporanTanahByIdPermohonan(idPermohonan) != null){
                    permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                    permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"PU");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                }

            }else if(kategori.equalsIgnoreCase("PU")){
                laporanTanah.setPerusahaan("Y");

                laporanTanah.setAdaBangunan(' ');
                laporanTanah.setUsaha(' ');
                laporanTanah.setUsahaTanam(null);
                laporanTanah.setUsahaLuas(null);
                laporanTanah.setUsahaBilanganPokok(null);
                laporanTanah.setUsahaHarga(null);
                    if(plpservice.findLaporanTanahByIdPermohonan(idPermohonan) != null){
                    permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"P");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                    permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
                    for(PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList){
                        laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                    }
                }
            }

            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            if(permohonanLaporanBangunan != null){
                permohonanLaporanBangunan.setPermohonan(permohonan);
                permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
                permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
                permohonanLaporanBangunan.setKategori(kategori);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(info);
                laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
            }
        }
        hakmilikDetails();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

    public Resolution kawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/tambahKawasanLaporanTanah.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }
     public Resolution hakmilikDetails() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        hakmilikImejLaporanList = new ArrayList<ImejLaporan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPUList = new ArrayList<PermohonanLaporanBangunan>();

        if (idPermohonan != null) {
             if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
             hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
         }
         else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                 senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                 if(senaraiHakmilikTerlibat.size()>0)
                     hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
             }
             else
                 hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
         
         }
           // hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);

            laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
           
            if(laporanTanah != null){
                //uncomment this for ulasan    
                senarailaporanUlasan = plpservice.getLaporUlasanByIdMohonidLaporan(idPermohonan, laporanTanah.getIdLaporan());

                if (!(senarailaporanUlasan.isEmpty())){
              PermohonanLaporanUlasan  permohonanLaporanUlasanL = new PermohonanLaporanUlasan();
              permohonanLaporanUlasanL = senarailaporanUlasan.get(0);
              Long laporanUlasan1 = permohonanLaporanUlasanL.getIdLaporUlas();
              if(laporanUlasan1 != null){
                    ulasan = permohonanLaporanUlasanL.getUlasan();
                }
          }                
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                hakmilikImejLaporanList = laporanTanahService.getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
                if(laporanTanah.getAdaBangunan()!= null && laporanTanah.getAdaBangunan() == 'Y'){
                    jenisKegunaan = 'B';
                    permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
                }
                if(laporanTanah.getUsaha()!=null &&  laporanTanah.getUsaha() == 'Y'){
                    jenisKegunaan = 'P';
                    usahaLuas = laporanTanah.getUsahaLuas();
                    if(laporanTanah.getUsahaBilanganPokok() != null){
                        usahaBilanganPokok = laporanTanah.getUsahaBilanganPokok();
                    }
                    usahaHarga = laporanTanah.getUsahaHarga();
                    permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"P");
                }
                if(laporanTanah.getPerusahaan() != null && laporanTanah.getPerusahaan().equalsIgnoreCase("Y")){
                    jenisKegunaan = 'U';
                    permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"PU");
                }
                if(laporanTanah.getAdaJalanMasuk() != null)
                adaJalanMasuk = laporanTanah.getAdaJalanMasuk();
                adaPecahSempadan = laporanTanah.getAdaPecahSempadan();
            }
        }

        String PP = (String)getContext().getRequest().getParameter("view");
        if(PP!= null && PP.equalsIgnoreCase("true")){
            display = Boolean.TRUE;
        }

        return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }


      public Resolution editBangunan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
            permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.valueOf(idLaporBangunan));

            if(permohonanLaporanBangunan != null){
                InfoAudit info = permohonanLaporanBangunan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(info);
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan") != null)
                    permohonanLaporanBangunan.setJenisBangunan(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisBangunan"));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.ukuran") != null)
                    permohonanLaporanBangunan.setUkuran(new BigDecimal(getContext().getRequest().getParameter("permohonanLaporanBangunan.ukuran")));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai") != null)
                    permohonanLaporanBangunan.setNilai(new BigDecimal(getContext().getRequest().getParameter("permohonanLaporanBangunan.nilai")));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.tahunDibina") != null)
                    permohonanLaporanBangunan.setTahunDibina(Integer.valueOf(getContext().getRequest().getParameter("permohonanLaporanBangunan.tahunDibina")));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya") != null)
                    permohonanLaporanBangunan.setNamaPemunya(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaPemunya"));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua") != null)
                    permohonanLaporanBangunan.setNamaKetua(getContext().getRequest().getParameter("permohonanLaporanBangunan.namaKetua"));
                if(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod") != null)
                    permohonanLaporanBangunan.setJenisPendudukan(jenisPendudukanDAO.findById(getContext().getRequest().getParameter("permohonanLaporanBangunan.jenisPendudukan.kod")));
                laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
            }
        }
        hakmilikDetails();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }     


    //TambahBangunan`s Tambah :End

    public Resolution cariPermohonan() {
        if(id ==null || id.equals("")){
            addSimpleError("Masukkan Permohonan Id");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            return new JSP("pelupusan/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
        }
       prmhnn = permohonanDAO.findById(id);
        if (prmhnn != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
            pemohon = plpservice.findPemohonByIdPemohon(prmhnn.getIdPermohonan());
            System.out.println("----pemohon-------"+pemohon);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            addSimpleMessage("Maklumat dijumpai");
            id2 = id;
        } else{
            addSimpleError("Maklumat tidak dijumpai");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
        }
        return new JSP("pelupusan/carian_permohonan_terdahulu1.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPermohonanSblm() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        prmhnn = permohonanDAO.findById(id2);
        cawangan = permohonan.getCawangan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setPermohonanSebelum(prmhnn);
         LOG.info("------simpan---Permohonan Terdahulu--------------::");
         LOG.info("------idPermohonan--------------::"+idPermohonan);
         LOG.info("------NoFail--------------::"+id2);
         permohonanManual = plpservice.findByIdMohonFailNo(idPermohonan, id2);

           InfoAudit infoAudit = new InfoAudit();
          if(permohonanManual != null){
               LOG.info("--------permohonanManual NOT Null------------::");
                infoAudit = permohonanManual.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
          }else {
               LOG.info("--------permohonanManual Null------------::");
          permohonanManual = new PermohonanManual();
          infoAudit.setDimasukOleh(peng);
          infoAudit.setTarikhMasuk(new java.util.Date());
          permohonanManual.setInfoAudit(infoAudit);
          permohonanManual.setIdPermohonan(permohonan);
          permohonanManual.setNoFail(id2);
          permohonanManual.setCawangan(cawangan);
          }
            plpservice.simpanPermohonanManual(permohonanManual);

       addSimpleMessage("Data Telah Berjaya dikemaskini");
        rehydrate() ;
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");

    }

    public Resolution deletePermohonanTerdahulu()  {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

         LOG.info("--------Delete permohonanManual------------::");
        String idMohonManual = getContext().getRequest().getParameter("idMohonManual");

        if (idMohonManual != null) {
            LOG.info("--------permohonanManual not null------------::");
            PermohonanManual permohonanManual1 = permohonanManualDAO.findById(Long.parseLong(idMohonManual));
            LOG.info("--------permohonanManual1::------------::"+permohonanManual1);
            if (permohonanManual1 != null) {
                plpservice.deletePermohonanManual(permohonanManual1);

            }
        }
        return new RedirectResolution(LaporanTanah4ActionBean.class, "locate");
    }
   
        public Resolution uploadDoc() {
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
        return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
    }

        public Resolution simpanImejLaporan() throws Exception{
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            InfoAudit info;
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty()))
                laporanTanah = listLaporanTanah.get(0);

            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(info);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }
            
            if(listLaporanTanah.isEmpty()){
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(info);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }
            String dokumenPath = conf.getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null){
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }
            if ((catatan != null) && (fileToBeUpload != null)){
                Dokumen doc = commonService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), info, permohonan);
                ImejLaporan imejLaporan =  new ImejLaporan();
                imejLaporan.setCawangan(permohonan.getCawangan());
                imejLaporan.setDokumen(doc);
                imejLaporan.setPandanganImej(pandanganImej);
                imejLaporan.setCatatan(catatan);
                imejLaporan.setInfoAudit(info);                
                if(laporanTanah!=null){
                    imejLaporan.setLaporanTanah(laporanTanah);
                    if(laporanTanah.getHakmilikPermohonan()!=null){
                        if(laporanTanah.getHakmilikPermohonan().getHakmilik()!=null)
                            imejLaporan.setHakmilik(laporanTanah.getHakmilikPermohonan().getHakmilik());
                    }
                }
                
                laporanTanahService.simpanHakmilikImej(imejLaporan);
//                addSimpleMessage("Muat naik fail berjaya.");
                
                 InfoAudit ia = new InfoAudit();
                 ia.setTarikhMasuk(new java.util.Date());
                 ia.setDimasukOleh(pguna);
        
                if (fileToBeUpload != null) {
                    try {
                        System.out.println("no null::" + fileToBeUpload.getContentType());
                        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
                        FileUtil fileUtil = new FileUtil(dokumenPath);
                        LOG.info("###### fileUtil :" + fileUtil.toString());
                        doc = commonService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
                        String dokumenId = String.valueOf(doc.getIdDokumen());
                        LOG.info("###### dokumenId :" + dokumenId);
                        fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
                        String fizikalPath = permohonan.getFolderDokumen().getFolderId() + File.separator + dokumenId;
                        LOG.info("###### fizikalPath :" + fizikalPath);
//                        updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan, pguna);

                        LOG.info("simpanMuatNaik::finish");
                        addSimpleMessage("Muat naik fail berjaya.");
                    } catch (Exception ex) {
                        Logger.getLogger(LaporanTanah4ActionBean.class).error(ex);
                    }
                } else {
                    addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
                }
                
            }
        }
       return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
    }
    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan, Pengguna pguna) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null) {
            d.setTajuk(catatan);
        }
        ImejLaporan imejLaporan =  new ImejLaporan();
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pguna);
        imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejLaporan.setCawangan(pguna.getKodCawangan());
        imejLaporan.setDokumen(d);
        imejLaporan.setLaporanTanah(laporanTanah);
        imejLaporan.setCatatan(catatan);
//                LOG.info("LOKASI" + lokasi);
//                char c = 0;
//                if (StringUtils.isNotEmpty(lokasi)) {
//                    c = (lokasi).charAt(0);
//                    imejLaporan.setPandanganImej(c);
//                }

        imejLaporan.setInfoAudit(ia);
        commonService.saveImej(imejLaporan);

        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }    
       public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_lptn.jsp").addParameter("popup", "true");
    }

       public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn_lptn.jsp").addParameter("popup", "true");
    }

        public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = plpservice.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            LOG.info("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = plpservice.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn_lptn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = plpservice.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            LOG.info("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = plpservice.searchKodSyaratNyata("%", kc, syaratNyata2);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata_lptn.jsp").addParameter("popup", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        
        
          System.out.println("-------------rehydrate---------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--------Stage ID-----------"+stageId);
        permohonanLaporanPelan = plpservice.findByIdPermohonanPelan(idPermohonan);
        LOG.info("--------permohonanLaporanPelan-----------"+permohonanLaporanPelan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        hakmilikImejLaporanList = new ArrayList<ImejLaporan>();
        pbt="1";
        permohonanLaporanKawasan = plpservice.findByidMohonKodRizab(idPermohonan, pbt);
        LOG.info("--------permohonanLaporanKawasan-----------"+permohonanLaporanKawasan);
        if(permohonanLaporanKawasan!=null){
       rizab1 =   permohonanLaporanKawasan.getKodRizab().getNama();
        }

        if (idPermohonan != null) {
            
            int syx=0;
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getKodUrusan().getKod().equals("PPBB")||permohonan.getKodUrusan().getKod().equals("PBPTD")||permohonan.getKodUrusan().getKod().equals("PBPTG"))
                settingDefaultValue();
            catatanKeg = permohonan.getSebab()!=null&&!("").equals(permohonan.getSebab())?permohonan.getSebab():new String();
            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan); 
            if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
                hakmilikPermohonanList = permohonan.getPermohonanSebelum().getSenaraiHakmilik() ;
            }
            else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP"))
                 hakmilikPermohonanList = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);                 
             else
                  hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
             tanahRizabPermohonanList = permohonan.getSenaraiTanahRizab();
             if(hakmilikPermohonanList != null){
                 if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                     if(permohonan.getPermohonanSebelum() != null)
                     hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan()) ;
                 }
                 else {
                     if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                         List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                          if(permohonan.getKodUrusan().getKod().equals("PTGSA"))
                         senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                            else
                         senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                         if(senaraiHakmilikTerlibat.size()>0)
                             hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                     }
                     else{
                         if(permohonan.getKodUrusan().getKod().equals("MMMCL")){
                            HakmilikPermohonan hakmilikPermohonanSblm = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan()) ;
                            hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getIdPermohonan());
                            InfoAudit info = new InfoAudit();
                            if(hakmilikPermohonan!=null){
                                info = hakmilikPermohonan.getInfoAudit();
                                info.setDiKemaskiniOleh(pguna);
                                info.setTarikhKemaskini(new java.util.Date());
                                hakmilikPermohonan.setInfoAudit(info);
                            }else{
                                hakmilikPermohonan =  new HakmilikPermohonan();
                                info = new InfoAudit();
                                info.setDimasukOleh(pguna);
                                info.setTarikhMasuk(new java.util.Date());
                                hakmilikPermohonan.setInfoAudit(info);
                                hakmilikPermohonan.setPermohonan(permohonan);
                            }
                            hakmilikPermohonan.setHakmilik(hakmilikPermohonanSblm.getHakmilik());
                            plpservice.saveOrUpdate(hakmilikPermohonan);
                         }
                         else
                            hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
                     }

                 }
                 if(hakmilikPermohonan != null){
                    if(hakmilikPermohonan.getBandarPekanMukimBaru()!=null){
                      syx =  hakmilikPermohonan.getBandarPekanMukimBaru().getKod();
                    kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(syx) ;
                    }
                }
            }else{
                 if(permohonan.getKodUrusan().getKod().equals("MMMCL")){
                    HakmilikPermohonan hakmilikPermohonanSblm = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan()) ;
                    hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getIdPermohonan());
                    InfoAudit info = new InfoAudit();
                    if(hakmilikPermohonan!=null){
                        info = hakmilikPermohonan.getInfoAudit();
                        info.setDiKemaskiniOleh(pguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(info);
                    }else{
                        hakmilikPermohonan =  new HakmilikPermohonan();
                        info = new InfoAudit();
                        info.setDimasukOleh(pguna);
                        info.setTarikhMasuk(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(info);
                        hakmilikPermohonan.setPermohonan(permohonan);
                    }
                    hakmilikPermohonan.setHakmilik(hakmilikPermohonanSblm.getHakmilik());
                    plpservice.saveOrUpdate(hakmilikPermohonan);
                 }
             }
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

              laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
             checkTanahExist = laporanTanah!=null?true:false;
             if(laporanTanah!=null){

                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                hakmilikImejLaporanList = laporanTanahService.getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());

            }else{
                 laporanTanah = new LaporanTanah();
             }

            List<FasaPermohonan> listFasa;
//            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            //REMOVE 02SediaLaporan
//            stageId = "semak_laporan_tanah";
            listFasa = permohonan.getSenaraiFasa();
              if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
                    if (fp.getIdAliran().equals("laporan_tanah")) {
                        fasaPermohonan = listFasa.get(i);
                        //ulasan = fasaPermohonan.getUlasan() ; DISABLED SINCE USING PTLPULASAN1
                        if(fasaPermohonan.getUlasan()!=null)
                            ulasan = fasaPermohonan.getUlasan();
                        if(fasaPermohonan.getKeputusan()!=null)
                            ksn = fasaPermohonan.getKeputusan().getKod();
                    }
                }
            }

            List<PermohonanRujukanLuar> listRujukanLuar;
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);
        }
        
        
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String format1="image/jpeg";
        String format2="image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1,format2, pguna.getNama());

         tanahrizabpermohonan1=tanahRizabService.findByidPermohonan(idPermohonan);
         if(tanahrizabpermohonan1!=null){
             if(!StringUtils.isBlank(tanahrizabpermohonan1.getPenjaga())){
                statBdnPngwl = tanahrizabpermohonan1.getPenjaga().equals("SUK Negeri")?"1": 
                               tanahrizabpermohonan1.getPenjaga().equals("Pesuruhjaya Tanah Persekutuan")?"2":
                               !tanahrizabpermohonan1.getPenjaga().equals("SUK Negeri")&&!tanahrizabpermohonan1.getPenjaga().equals("Pesuruhjaya Tanah Persekutuan")?"3":
                               "0";
             }else{
                 statBdnPngwl = "0";
             }
         }
         LOG.info("--------tanahrizabpermohonan---------::"+tanahrizabpermohonan1);
        
        permohonanManualList = plpservice.findByIdMohonlist(idPermohonan);
        if(permohonanManualList!= null){
        LOG.info("--------permohonanManualList---------::"+permohonanManualList);
        }

        if(laporanTanah !=null){
        idlapor = laporanTanah.getIdLaporan();
        }
//        if(idlapor != null){
//         permohonanLaporanUlasan = plpservice.findByIdMohonIdLapor(idPermohonan, idlapor);
//        }

//          permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Syarat4A");
//          LOG.info("--------permohonanLaporanUlasan Rehydrate()---------::"+permohonanLaporanUlasan);


//          if(permohonanLaporanUlasan !=null){
//              plpulasan1 = permohonanLaporanUlasan.getUlasan();
//              LOG.info("--------plpulasan1 Rehydrate()---------::"+plpulasan1);
//          }

          permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Syarat Tambahan LPS", laporanTanah.getIdLaporan());
          LOG.info("--------permohonanLaporanKandungan Rehydrate()---------::"+permohonanLaporanKandungan);
          
          if(permohonanLaporanKandungan !=null){
              ulsn = permohonanLaporanKandungan.getKand();
              LOG.info("--------ulsn Rehydrate()---------::"+ulsn);
          }
          
     
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilik() != null)) {
                kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
            }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodHakmilik() != null)) {
                kodHmlkTetap = hakmilikPermohonan.getKodHakmilikTetap().getKod();
            }    
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasLulusUom() != null)) {
                kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
            }
        if ((laporanTanah != null)&&(laporanTanah.getKodKeadaanTanah() !=null)) {
                keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
            }

        
         if((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium()!=null)){
             keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();
             
         }
        
//        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)){
//                if(hakmilikPermohonan.getHakmilik()!=null){
//                    if(hakmilikPermohonan.getHakmilik()!=null){
//                           kodP = 'H';
//                    }
//                }else
//                    kodP = hakmilikPermohonan.getKodMilik().getKod();
//            }
        if(hakmilikPermohonan!=null){
            if(hakmilikPermohonan.getHakmilik()==null){
                if(permohonan.getKodUrusan().getKod().equals("PRMP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                    kodP = 'H';
                } else{
                    if(hakmilikPermohonan.getKodMilik() != null)
                    kodP = hakmilikPermohonan.getKodMilik().getKod();
                }
            }else{
                if(hakmilikPermohonan.getKodMilik()!=null)
                    kodP = hakmilikPermohonan.getKodMilik().getKod();
                else
                    kodP = 'H';
            }
        }
        
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                kodD = hakmilikPermohonan.getKodDUN().getKod();
            }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
            }
         if(permohonan.getKodUrusan().getKod().equals("PPBB")||permohonan.getKodUrusan().getKod().equals("PBPTD")||permohonan.getKodUrusan().getKod().equals("PBPTG")){
             syaratBahanBatu = plpservice.findPermohonanBahanBatuanByIdMohon(idPermohonan);
             //FOR JUMLAH KENE BAYAR IN SYARAT-SYARAT
            if (hakmilikPermohonan.getKodMilik().getKod().equals('H')) {
                if (syaratBahanBatu.getJenisBahanBatu().getKod().equals("BG") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("PS")
                        || syaratBahanBatu.getJenisBahanBatu().getKod().equals("TL") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("RP")
                        || syaratBahanBatu.getJenisBahanBatu().getKod().equals("TM") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("BT")) {
                    if(syaratBahanBatu.getJumlahIsipadu()!=null){
                        jumlahKeneBayar = syaratBahanBatu.getJenisBahanBatu().getRoyaltiTanahMilik().multiply(syaratBahanBatu.getJumlahIsipadu()).toString();
                        cagarKeneBayar = (0.2) * Double.parseDouble(jumlahKeneBayar);
                    }
                }
            }
            if (hakmilikPermohonan.getKodMilik().getKod().equals('K')) {
                if (syaratBahanBatu.getJenisBahanBatu().getKod().equals("BG") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("PS")
                        || syaratBahanBatu.getJenisBahanBatu().getKod().equals("TL") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("RP")
                        || syaratBahanBatu.getJenisBahanBatu().getKod().equals("TM") || syaratBahanBatu.getJenisBahanBatu().getKod().equals("BT")) {
                    if(syaratBahanBatu.getJumlahIsipadu()!=null){
                        jumlahKeneBayar = syaratBahanBatu.getJenisBahanBatu().getRoyaltiTanahKerajaan().multiply(syaratBahanBatu.getJumlahIsipadu()).toString();
                        cagarKeneBayar = 0.2 * Double.parseDouble(jumlahKeneBayar);
                    }
                }
            }
            PermohonanTuntutanKos mohonTuntutKosA = new PermohonanTuntutanKos();
            mohonTuntutKosA = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISKD");
            if(mohonTuntutKosA!=null){
                kuponQty = mohonTuntutKosA.getKuantiti();
                kuponAmaun = 50.00;
                kupon = BigDecimal.valueOf(Double.parseDouble(String.valueOf(kuponQty)) * kuponAmaun) ;
               
            }else{
                kuponAmaun = 50.00;
            }
            
            PermohonanTuntutanKos mohonTuntutKosB = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISCJ");
            if(mohonTuntutKosB!=null){
                cagarJalan = mohonTuntutKosB.getAmaunTuntutan();
               
            }
            //JUMLAH KESELURUHAN
            if(StringUtils.isEmpty(String.valueOf(cagarKeneBayar)))
                cagarKeneBayar = 0.00;
            if(cagarJalan!=null){
                if(StringUtils.isEmpty(cagarJalan.toString()))
                    cagarJalan = new BigDecimal(0);
            }else{
                cagarJalan = new BigDecimal(0);
            }
            if(kupon!=null){
                if(StringUtils.isEmpty(kupon.toString()))
                    kupon = new BigDecimal(0);
            }else{
                kupon = new BigDecimal(0);
            }
            
                BigDecimal cagaran = new BigDecimal(0);
                double percentDouble = 20/100; 
                BigDecimal percent = BigDecimal.valueOf(percentDouble);
                cagaran = BigDecimal.valueOf(cagarKeneBayar).multiply(percent);
                totalAll = cagarJalan.add(BigDecimal.valueOf(cagarKeneBayar)).add(kupon).add(cagaran);
         }
         if((tanahrizabpermohonan1 !=null) && tanahrizabpermohonan1.getRizab()!=null){
         int tr = tanahrizabpermohonan1.getRizab().getKod();
         tanahR = Integer.toString(tr);
         }

         if((permohonanLaporanPelan !=null) && permohonanLaporanPelan.getKodTanah()!=null){
         kodT = permohonanLaporanPelan.getKodTanah().getKod();
         }

         if((laporanTanah != null) && (laporanTanah.getKecerunanTanah() !=null)){
             kecerunanT = laporanTanah.getKecerunanTanah().getKod();
         }
        if((laporanTanah != null) && (laporanTanah.getStrukturTanah() !=null)){
             klasifikasiT = laporanTanah.getStrukturTanah().getKod();
         }

        if((fasaPermohonan != null) && (fasaPermohonan.getKeputusan() !=null)){
            ksn = fasaPermohonan.getKeputusan().getKod();
        }
        if(permohonan.getKodUrusan().getKod().equals("PLPS")){
           permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
        if((permohonanPermitItem != null) && (permohonanPermitItem.getKodItemPermit() != null)){
            keg = permohonanPermitItem.getKodItemPermit().getKod();
            LOG.info("----keg---------"+keg);
        }
        }
         permohonanTuntutanKos = plpservice.findMohonTuntutKosByIdPermohonan(idPermohonan);
         if((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() !=null)){
             amnt = permohonanTuntutanKos.getAmaunTuntutan();
         }

         if((laporanTanah !=null) &&  (laporanTanah.getUsaha() !=null)){
                   usahaLuas = laporanTanah.getUsahaLuas();
         }

         if((laporanTanah !=null) && (laporanTanah.getUsahaBilanganPokok() != null)){
              usahaBilanganPokok = laporanTanah.getUsahaBilanganPokok();
        }

         if((laporanTanah !=null) && (laporanTanah.getUsahaHarga() != null)){
         usahaHarga = laporanTanah.getUsahaHarga();
         }
        
//search options
         if((hakmilikPermohonan != null) && (hakmilikPermohonan.getSyaratNyataBaru() != null)) {
            String   kod1=(hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            LOG.info("----kod1---------"+kod1);
//             syaratNyata = ""+kod+" -- "+kod1+"";
            syaratNyata = ""+kod1;
        LOG.info("----syaratNyata---------"+syaratNyata);
        }else{
            kod ="";
        }

        if((hakmilikPermohonan != null) && (hakmilikPermohonan.getSekatanKepentinganBaru() != null)) {
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            LOG.info("----kodSktn1---------"+kodSktn);
              String   kod2=(hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
//               syaratNyata1 =  ""+kodSktn+" -- "+kod2+"";
               syaratNyata1 = ""+kod2;
        LOG.info("----syaratNyata1---------"+syaratNyata1);
        }
        else{
            kodSktn ="";
        }
            if(laporanTanah != null){
                LOG.info("----laporanTanah.getAdaBangunan()---------"+laporanTanah.getAdaBangunan());
               
                if(laporanTanah.getAdaBangunan()!= null && laporanTanah.getAdaBangunan() == 'Y'){
                    jenisKegunaan = 'B';
                    permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(),"B");
                }
                LOG.info("----permohonanLaporanBangunanList---------"+permohonanLaporanBangunanList);
            }

         senaraikodKadarPremium = plpservice.getSenaraiKodKadarPremiumDistinctNama();
         LOG.info("----senaraikodKadarPremium List---------"+senaraikodKadarPremium);


         //new

          ///trace mohon_laporan_kws
                  permohonanLaporKSWList = laporanPelukisPelanService.findByidMohon(idPermohonan);
                  LOG.info("---------------permohonanLaporKSWList-----------------"+permohonanLaporKSWList);
                        for(PermohonanLaporanKawasan lpk : permohonanLaporKSWList){
                            LOG.info("trace kod rizab :: "+lpk.getKodRizab().getKod());
                            String rezKod = String.valueOf(lpk.getKodRizab().getKod());
                         if(rezKod.equalsIgnoreCase("1")){
                             LOG.info("Load Kod Rizab 1");
                             pbt1 = "1";
                         }else if (rezKod.equalsIgnoreCase("2")){
                            gsa = "2";
                         } else if (rezKod.equalsIgnoreCase("3")){
                            rizab_melayu ="3";
                         } else if (rezKod.equalsIgnoreCase("4")){
                            hutan = "4";
                         } else if (rezKod.equalsIgnoreCase("99")){

                            lain = "99";
                            catatanLain1 = lpk.getCatatan();
                         }
                        }

             if(laporanTanah !=null){
           permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Jenis Tanah", laporanTanah.getIdLaporan());
             }
             LOG.info("-------permohonanLaporanKandungan rehydrate()-------::"+permohonanLaporanKandungan);
             if((laporanTanah !=null) && (permohonanLaporanKandungan !=null)){
                 kand = permohonanLaporanKandungan.getKand();                 
             }

              if(laporanTanah !=null){
           permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
             }
             LOG.info("-------permohonanLaporanKandungan rehydrate()-------::"+permohonanLaporanKandungan);
             if((laporanTanah !=null) && (permohonanLaporanKandungan !=null)){
                 keadaankand = permohonanLaporanKandungan.getKand();
             }

//          permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Syarat4A");
//          LOG.info("--------permohonanLaporanUlasan Rehydrate()---------::"+permohonanLaporanUlasan);

//             senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
//          senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "UlsanPTKnn");
//          rowCount2 = senaraiLaporanKandungan1.size();
//          
//          if (!(senaraiLaporanKandungan1.isEmpty())){
//              PermohonanLaporanUlasan  permohonanLaporanUlasan1 = new PermohonanLaporanUlasan();
//              permohonanLaporanUlasan1 = senaraiLaporanKandungan1.get(0);
//             
//                  ulasanKanan = permohonanLaporanUlasan1.getUlasan();
//                  LOG.info("-------Ulasan PPTKANAN1 ------::"+ulasanKanan);
//              
//          }
//          
          if(ksn != null){

                  senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Ulasan PPT");
                   System.out.println("----------senaraiLaporanKandungan1 if in-----------"+senaraiLaporanKandungan1.size());
               
          }else{
                 senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Ulasan PPT");
                   System.out.println("----------senaraiLaporanKandungan1 if in-----------"+senaraiLaporanKandungan1.size());
          }
          rowCount2 = senaraiLaporanKandungan1.size();
          System.out.println("----------rowCount2-----------"+rowCount2);
          
          permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "UlsanPTKnn") ;
          if(permohonanLaporanUlasan != null){
              ulasanKanan = permohonanLaporanUlasan.getUlasan() ;
          }
          
          if (!(senaraiLaporanKandungan1.isEmpty())){
              PermohonanLaporanUlasan  permohonanLaporanUlasan1 = new PermohonanLaporanUlasan();
              permohonanLaporanUlasan1 = senaraiLaporanKandungan1.get(0);
              LOG.info("-------permohonanLaporanUlasan1.getUlasan() ------::"+permohonanLaporanUlasan1.getUlasan());
              plpulasan0 = permohonanLaporanUlasan1.getUlasan();
     
          }
           senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
           List<PermohonanLaporanUlasan>  permohonanLaporanUlasan_ref = new ArrayList<PermohonanLaporanUlasan>();
          permohonanLaporanUlasan_ref = plpservice.findUlasan(idPermohonan, "Syarat4A");
          System.out.println("----------permohonanLaporanUlasan_ref out-----------"+permohonanLaporanUlasan_ref.size());
          if(permohonanLaporanUlasan_ref.size() > 0){
              System.out.println("----------permohonanLaporanUlasan_ref in-----------"+permohonanLaporanUlasan_ref.size());
              pasId = "1";
          }
          senaraiLaporanKandungan1 = permohonanLaporanUlasan_ref;
          System.out.println("----------senaraiLaporanKandungan1 out-----------"+senaraiLaporanKandungan1.size());

          
          /*
           * NO_PT
           */
          if(permohonan.getKodUrusan().getKod().equals("PBMT")){
              hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getIdPermohonan()) ;
              noPT = plpservice.findNoPTByIdMH(hakmilikPermohonan.getId());
              
             if(noPT!=null){
                 if(noPT.getKodUOM()!=null){
                     kodU = noPT.getKodUOM().getKod();
                 }
                 if(noPT.getLuasDilulus()!=null)
                    luasDilulus = noPT.getLuasDilulus();
             }
          }
          if(permohonan.getKodUrusan().getKod().equals("PLPS")){
              if(hakmilikPermohonan.getLuasLulusUom()!=null)
                kodUPlps = hakmilikPermohonan.getLuasLulusUom().getNama();
          }
          if(permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")){
              permohonanBahanBatuan = plpservice.findByIdMBB(idPermohonan);
              if(permohonanBahanBatuan != null && permohonanBahanBatuan.getTempohSyorUom() != null){
                  tempohSyorUOM = permohonanBahanBatuan.getTempohSyorUom().getKod() ;
              }
          }
          if(permohonan.getKodUrusan().getKod().equals("PRMP")){
              pmi = new PermohonanPermitItem();
              pmi = plpservice.findByIdMohonPermitItem(idPermohonan);
          }
    }
    public Resolution simpanKawasan() throws ParseException {
       PermohonanLaporanKawasan kws = new PermohonanLaporanKawasan();
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       permohonan = permohonanDAO.findById(idPermohonan);
       InfoAudit infoAudit = new InfoAudit();
       infoAudit.setDimasukOleh(pguna);
       infoAudit.setTarikhMasuk(new java.util.Date());
       kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
       if(kodRizab.equals("99"))
           kws.setCatatan(addcatatan);
       kws.setNoWarta(addnoWarta);
       kws.setInfoAudit(infoAudit);
       if(addtarikhWarta!=null&&!("").equals(addtarikhWarta))
           kws.setTarikhWarta(sdf.parse(addtarikhWarta));
       kws.setNoPelanWarta(addnoPelanWarta);
       kws.setPermohonan(permohonan);
       kws.setKodCawangan(permohonan.getCawangan());
       plpservice.simpanPermohonanLaporKawasan(kws);
       addSimpleMessage("Maklumat Berjaya Disimpan");         
       rehydrate();
       getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");

    }
     public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

        public boolean validation() {
        boolean flag = false;
        if(fasaPermohonan!=null)
        if(fasaPermohonan.getUlasan()==null||fasaPermohonan.getUlasan().trim().equals("")){
         flag = true;
         addSimpleError("Ulasan is empty");
        }
        return flag;
       }

    public boolean validation2() {
        boolean flag = true;
        if(laporanTanah!=null)
        if(laporanTanah.getSempadanUtaraNoLot()==null){
            flag = false;
        }else if(laporanTanah.getSempadanUtaraKegunaan()==null){
           flag = false;    
        }else if(laporanTanah.getSempadanSelatanNoLot()==null){
            flag=false;
        }else if(laporanTanah.getSempadanSelatanKegunaan()==null){
            flag=false;
        }else if(laporanTanah.getSempadanTimurNoLot()==null){
            flag=false;
        }else if(laporanTanah.getSempadanTimurKegunaan()==null){
            flag=false;
        }else if(laporanTanah.getSempadanBaratNoLot()==null){
            flag=false;
        }else if(laporanTanah.getSempadanBaratKegunaan()==null){
            flag=false;
        }
        
        if(!flag)
            addSimpleError("Sila penuhi perihal lot-lot bersempadanan");
        return flag;
       }
    //Add for gambar validation
        private boolean validation3() {
        boolean flag = true ;
        if(utaraImejLaporanList.isEmpty()){
             flag=false;
        }
        else if(selatanImejLaporanList.isEmpty()){
            flag=false;
        }
         else if(baratImejLaporanList.isEmpty()){
            flag=false;
        }
         else if(timurImejLaporanList.isEmpty()){
            flag=false;
        }
         if(!flag)
            addSimpleError("Sila muat naik gambar di lot-lot bersempadanan");
        return flag;
        
    }
    public Resolution simpanLaporanTanahBangunan() throws Exception { 

       LOG.info("-simpanLaporanTanah(-::");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
         String[] tname = {"permohonan"};
            Object[] model = {permohonan};

        String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");
        System.out.println("-----kodS---"+kodS) ;

        if((kodS!=null) && !kodS.equals("0")){
            Integer a = Integer.parseInt(kodS) ;
            int kod = a.intValue() ;
            KodSeksyen kodSeksyen = plpservice.findByKodSeksyen(kod) ;
            hakmilikPermohonan.setKodSeksyen(kodSeksyen);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }else {

        }       
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

       
        // Saving in Laporan Tanah
        LOG.info("-------------Saving In Laporan Tanah----------------");
         
        
//        BigDecimal jarakSempadanJalanRaya = laporanTanah.getJarakSempadanJalanraya();
//        BigDecimal jarakSempadanJalanKetapi = laporanTanah.getJarakSempadanKeretapi();
//        BigDecimal jarakSempadanLaut = laporanTanah.getJarakSempadanLaut();
//        BigDecimal jarakSempadanSungai = laporanTanah.getJarakSempadanSungai();
//        BigDecimal jaraskSempadanLain = laporanTanah.getJarakSempadanLain();
//        BigDecimal ketinggianDariJalan = laporanTanah.getKetinggianDariJalan();
//        BigDecimal kecerunanBukit = laporanTanah.getKecerunanBukit();
//        BigDecimal parasAir = laporanTanah.getParasAir();
//        laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
        
        
        //laporanTanah.setBolehBerimilik(kodBolehBerimilik);
        LOG.info("-------------laporanTanah----------------"+laporanTanah);
        LOG.info("THIS IS PENGGUNA ->"+pengguna);
        if (checkTanahExist) {
            LOG.info("-------------Laporan Tanah NOT Null----------------");
                infoAudit = new InfoAudit();
                infoAudit = laporanTanah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
            LOG.info("-------------Laporan Tanah Is Null----------------");
                //laporanTanah = new LaporanTanah();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            if(laporanTanah.getAdaBangunan()!= null){
                if(laporanTanah.getAdaBangunan() == 'T'){
                   laporanTanah.setBilanganBangunan(0);
               }
            }
        if(kecerunanT !=null){
                 LOG.info("-------------kecerunanT----------------::"+kecerunanT);
                KodKecerunanTanah kkt = kodKecerunanTanahDAO.findById(kecerunanT);
                LOG.info("-------------kkt----------------::"+kkt);
                laporanTanah.setKecerunanTanah(kkt);
            }
        if(klasifikasiT !=null){
                 LOG.info("-------------KlasifikasiT----------------::"+klasifikasiT);
                KodStrukturTanah kft = kodStrukturTanahDAO.findById(klasifikasiT);
                LOG.info("-------------kft----------------::"+kft);
                laporanTanah.setStrukturTanah(kft);
            }
         if(keadaanTanah !=null){
                 LOG.info("-------------keadaanTanah----------------::"+keadaanTanah);
           KodKeadaanTanah kdt = kodKeadaanTanahDAO.findById(keadaanTanah);
                LOG.info("-------------kdt----------------::"+kdt);
                laporanTanah.setKodKeadaanTanah(kdt);
            }
         /*
         * BERSEMPADANAN 
         */
//         laporanTanah.setNamaSempadanJalanraya(getContext().getRequest().getParameter("laporanTanah.namaSempadanJalanraya"));
//         laporanTanah.setNamaSempadanKeretapi(getContext().getRequest().getParameter("laporanTanah.namaSempadanKeretapi"));         
//         laporanTanah.setNamaSempadanLaut(getContext().getRequest().getParameter("laporanTanah.namaSempadanLaut"));
//         laporanTanah.setNamaSempadanSungai(getContext().getRequest().getParameter("laporanTanah.namaSempadanSungai"));
//         laporanTanah.setNamaSempadanlain(getContext().getRequest().getParameter("laporanTanah.namaSempadanlain"));
//         
//         laporanTanah.setJarakSempadanJalanraya(jarakSempadanJalanRaya);         
//         laporanTanah.setJarakSempadanKeretapi(jarakSempadanJalanKetapi);
//         laporanTanah.setJarakSempadanLaut(jarakSempadanLaut);
//         laporanTanah.setJarakSempadanSungai(jarakSempadanSungai);
//         laporanTanah.setJarakSempadanLain(jaraskSempadanLain);
//         
//         KodUOM km = new KodUOM();
//         km = kodUOMDAO.findById("KM");
//         
//         laporanTanah.setJarakSempadanJalanrayaUOM(km); 
//         laporanTanah.setJarakSempadanKeretapiUOM(km);
//         laporanTanah.setJarakSempadanLautUOM(km);
//         laporanTanah.setJarakSempadanSungaiUOM(km);
//         laporanTanah.setJarakSempadanLainUOM(km);
         
         /*
          * END OF BERSEMPADANAN
          */
         
//         laporanTanah.setBolehBerimilik(getContext().getRequest().getParameter(" laporanTanah.bolehBerimilik"));
//         laporanTanah.setSebabTidakBolehBerimilik(getContext().getRequest().getParameter("laporanTanah.sebabTidakBolehBerimilik"));
//         laporanTanah.setJenisJalan(getContext().getRequest().getParameter("laporanTanah.jenisJalan"));
//         laporanTanah.setAdaJalanMasuk(getContext().getRequest().getParameter("laporanTanah.adaJalanMasuk").charAt(0));
//         laporanTanah.setCatatanJalanMasuk(getContext().getRequest().getParameter("laporanTanah.catatanJalanMasuk"));
//         laporanTanah.setKetinggianDariJalan(ketinggianDariJalan);
//         laporanTanah.setKecerunanBukit(kecerunanBukit);
//         laporanTanah.setParasAir(parasAir);
//         laporanTanah.setStrukturTanahLain(getContext().getRequest().getParameter("laporanTanah.strukturTanahLain"));
//         laporanTanah.setTanahBertumpu(getContext().getRequest().getParameter("laporanTanah.tanahBertumpu"));
         
         laporanTanah.setInfoAudit(infoAudit);
         laporanTanah.setPermohonan(permohonan);
         laporanTanah.setUsahaLuas(usahaLuas);
         laporanTanah.setUsahaBilanganPokok(usahaBilanganPokok);
         laporanTanah.setUsahaHarga(usahaHarga);
         laporanTanahService.simpanLaporanTanah(laporanTanah);

        // Saving in MOHON_FASA

        LOG.info("-------------Saving in MOHON_FASA-----------------::");
        stageId = "laporan_tanah";
        fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        LOG.info("-------------fasaPermohonan-----------------::"+fasaPermohonan);
        if (fasaPermohonan != null) {
            LOG.info("-------------MOHON_FASA NOT Null-----------------::");
            infoAudit = fasaPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());            
        }else{
            LOG.info("-------------MOHON_FASA is Null-----------------::");
            fasaPermohonan = new FasaPermohonan();
            LOG.info("-------------Dimasuk-----------------::"+pengguna.getNama());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setPermohonan(permohonan);
            
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            if(ksn != null){
                LOG.info("-------------ksn-----------------::"+ksn);
                KodKeputusan kkp = kodKeputusanDAO.findById(ksn);
                LOG.info("-------------kkp-----------------::"+kkp);
                fasaPermohonan.setKeputusan(kkp);
            }
            //fasaPermohonan.setUlasan(ulasan); //DISABLED SINCE USING plpulasan1
            fasaPermohonan.setUlasan(plpulasan0);
            if(permohonan.getKodUrusan().getKod().equals("PRMP")){
                fasaPermohonan.setUlasan(ulasan);
            }
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            
        //saving in Mohon_Lapor_ulas
        
        LOG.info("-------Saving in MOHON_LAPOR_ULAS-------::");

        senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
        LOG.info("-----senaraiLaporanKandungan1-----:"+senaraiLaporanKandungan1);
         int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count------------:"+kira2);
        for (int i = 1; i <= kira2; i++) {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
               Long ju = senaraiLaporanKandungan1.get(i - 1).getIdLaporUlas();
               LOG.info("-----------ju------------:"+ju);
                if (ju != null) {
                    LOG.info("-----------ju Not Null------------:");
                    permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(ju);
                    infoAudit = permohonanLaporanUlasan.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                }
            } else {
                LOG.info("-----------ju is Null------------:");
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Syarat4A");
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            String julasan = getContext().getRequest().getParameter("plpulasan" + i);
            LOG.info("-----------ju ulasan------------:"+ulasan);
            if(julasan==null || julasan.equals("")){
                permohonanLaporanUlasan.setUlasan("TIADA DATA");
            }else{
                permohonanLaporanUlasan.setUlasan(julasan);
            }
           plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);            
        }

            

            //saving in Mohon_Lapor_ulas
        if(ksn != null && ksn.equalsIgnoreCase("SL")){
        LOG.info("-------Saving in MOHON_LAPOR_ULAS For Syor LULUS-------::");
        permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Ulasan PPT");
        LOG.info("------permohonanLaporanUlasan-------::"+permohonanLaporanUlasan);

        if(permohonanLaporanUlasan != null){
            LOG.info("-----permohonanLaporanUlasan NOT Null-----:");
            infoAudit = permohonanLaporanUlasan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        }else{
            LOG.info("-----permohonanLaporanUlasan is Null-----:");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
            permohonanLaporanUlasan.setUlasan(plpulasan0);
            LOG.info("-----ulsn-----:"+ulsn);
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);             
        }
      
        //Saving in Mohon_Rizab

            if(kodP == 'R'){
                LOG.info("-------kodP-------::"+kodP);
            
        LOG.info("-------Saving in MOHON_RIZAB-------::");
         tanahrizabpermohonan1=tanahRizabService.findByidPermohonan(idPermohonan);
         LOG.info("-------MOHON_RIZAB-------::"+tanahrizabpermohonan1);
        if(tanahrizabpermohonan1!=null){
            LOG.info("-------MOHON_RIZAB is Not null-------::");
                infoAudit = tanahrizabpermohonan1.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

        }else {
             LOG.info("-------MOHON_RIZAB is null-------::");
             tanahrizabpermohonan1 = new TanahRizabPermohonan();
             infoAudit.setDimasukOleh(pengguna);
             infoAudit.setTarikhMasuk(new java.util.Date());
        }
             tanahrizabpermohonan1.setInfoAudit(infoAudit);
             tanahrizabpermohonan1.setPermohonan(permohonan);
             tanahrizabpermohonan1.setCawangan(permohonan.getCawangan());
             tanahrizabpermohonan1.setDaerah(cawangan.getDaerah());
             tanahrizabpermohonan1.setAktif('Y');
             if((hakmilikPermohonan!=null) && (hakmilikPermohonan.getBandarPekanMukimBaru()!=null)){
             tanahrizabpermohonan1.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
             }

            if (tanahR != null) {
                KodRizab kr = kodRizabDAO.findById(Integer.parseInt(tanahR));
                tanahrizabpermohonan1.setRizab(kr);
            }
             if((hakmilikPermohonan!=null)&&(hakmilikPermohonan.getNoLot()!=null)){
             tanahrizabpermohonan1.setNoLot(hakmilikPermohonan.getNoLot());
             }
             if(!StringUtils.isBlank(statBdnPngwl)){
                 String penjaga = new String();
                  penjaga = statBdnPngwl.equals("1")?"SUK Negeri": 
                                 statBdnPngwl.equals("2")?"Pesuruhjaya Tanah Persekutuan":
                                 !statBdnPngwl.equals("1")&&!statBdnPngwl.equals("2")?"Lain-lain":
                                 "0";
                 tanahrizabpermohonan1.setPenjaga(penjaga);
             }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan1);
            }

         //Saving in Hakmilik Permohonan
         LOG.info("-------Saving in HakmilikPermohonan-------::");
         if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
             hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
         }
        else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                 senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                 if(senaraiHakmilikTerlibat.size()>0)
                     hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
             }
             else
                 hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
         
         }
         LOG.info("-------hakmilikPermohonan-------::"+hakmilikPermohonan);
        
         if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (kod != null) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
        }


         if (kodD != null) {
                KodDUN kd = kodDUNDAO.findById(kodD);
                hakmilikPermohonan.setKodDUN(kd);
            }
         if (kodPar != null) {
                KodKawasanParlimen kw = kodKawasanParlimenDAO.findById(kodPar);
                hakmilikPermohonan.setKodKawasanParlimen(kw);
            }
         
         if (kodSktn != null) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
        }
         
         if (kodHmlk != null) {
                KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
                hakmilikPermohonan.setKodHakmilikSementara(khm);
            }
        if (kodHmlkTetap != null) {
                KodHakmilik khmTetap = kodHakmilikDAO.findById(kodHmlkTetap);
                hakmilikPermohonan.setKodHakmilikTetap(khmTetap);
            }
         if (kodP != ' ') {
                KodPemilikan kpm = kodPemilikanDAO.findById(kodP);
                hakmilikPermohonan.setKodMilik(kpm);
            }
            
            hakmilikPermohonan.setKeteranganKadarPremium(keteranganKadarPremium);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setPermohonan(permohonan);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);

         
//         if (kodU != null) {
//                KodUOM kuom = kodUOMDAO.findById(kodU);
//                hakmilikPermohonan.setKodUnitLuas(kuom);
//            }
         /*
          * SAVING NO_PT
          */
            
            if(permohonan.getKodUrusan().getKod().equals("PBMT")){
                    HakmilikPermohonan mh = new HakmilikPermohonan();
                    LOG.info("This is ID Permohonan ->"+idPermohonan);
                    mh = plpservice.findByIdPermohonan(idPermohonan);
                    noPT = plpservice.findNoPtByIdHakmilikPermohonan(mh.getId()); 
                if (noPT != null) {
                    infoAudit = noPT.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    if(kodU!=null){
                        KodUOM kuom = kodUOMDAO.findById(kodU);
                        noPT.setKodUOM(kuom);
                        
                    }
                    noPT.setLuasDilulus(luasDilulus);
                    noPT.setInfoAudit(infoAudit);
                    noPT.setHakmilikPermohonan(mh);
                     plpservice.simpanNoPt(noPT);

                } else {
                    noPT = new NoPt();
                    LOG.info("THIS IS PENGGUNA ->"+pengguna);
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    noPT.setInfoAudit(infoAudit);
                    if(kodU!=null){
                        KodUOM kuom = kodUOMDAO.findById(kodU);
                        noPT.setKodUOM(kuom);
                    }
                    noPT.setLuasDilulus(luasDilulus);
                    noPT.setHakmilikPermohonan(mh);
                     plpservice.simpanSaveOnlyNoPt(noPT);
                }
                    
            }
            
         /*
          * END OF NO_PT SAVE
          */
            
            
         //Saving in MOHON LAPOR PELAN
             LOG.info("-------Saving in MOHON LAPOR PELAN-------::");
            PermohonanLaporanPelan permohonanLaporanPelan1 = plpservice.findByIdPermohonanPelan(idPermohonan);
             LOG.info("------permohonanLaporanPelan1------"+permohonanLaporanPelan1);
             if(permohonanLaporanPelan1!=null){
                 LOG.info("------permohonanLaporanPelan1 Not NULL------");
                 infoAudit = permohonanLaporanPelan1.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());                 
             } else {
                 LOG.info("-----permohonanLaporanPelan1 is NULL------");
                permohonanLaporanPelan1 = new PermohonanLaporanPelan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
             permohonanLaporanPelan1.setInfoAudit(infoAudit);
             permohonanLaporanPelan1.setCawangan(cawangan);
             permohonanLaporanPelan1.setPermohonan(permohonan);
             if (kodT != null) {
                KodTanah ktt = kodTanahDAO.findById(kodT);
                LOG.info("------kodT------"+kodT);
                LOG.info("------ktt------"+ktt);
                permohonanLaporanPelan1.setKodTanah(ktt);
            }
             plpservice.simpanPermohonanLaporanPelan(permohonanLaporanPelan1);

       // Saving in MOHON LAPOR KAND
             LOG.info("-------Saving in MOHON LAPOR KAND For Jenis Tanah-------::");
          if(kodT.equalsIgnoreCase("99")){
                 LOG.info("-------JenisTanah Selected-------::"+kodT);
             permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Jenis Tanah", laporanTanah.getIdLaporan());
             LOG.info("-------permohonanLaporanKandungan-------::"+permohonanLaporanKandungan);
             if(permohonanLaporanKandungan !=null){
                 LOG.info("-------permohonanLaporanKandungan NOT NULL-------::");
                 infoAudit = permohonanLaporanKandungan.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());                 
             }else{
                 LOG.info("-------permohonanLaporanKandungan IS NULL-------::");
                 permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                 infoAudit.setDimasukOleh(pengguna);
                 infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanLaporanKandungan.setInfoAudit(infoAudit);
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Jenis Tanah");
                permohonanLaporanKandungan.setKand(kand);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                 
       }

          // Saving in MOHON LAPOR KAND
             LOG.info("-------Saving in MOHON LAPOR KAND For Keadaan Tanah-------::");
           if(keadaanTanah.equalsIgnoreCase("LL")){
                 LOG.info("-------keadaanTanah Selected-------::"+keadaanTanah);
             permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
             LOG.info("-------permohonanLaporanKandungan-------::"+permohonanLaporanKandungan);
             if(permohonanLaporanKandungan !=null){
                 LOG.info("-------permohonanLaporanKandungan NOT NULL-------::");
                 infoAudit = permohonanLaporanKandungan.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
             }else{
                 LOG.info("-------permohonanLaporanKandungan IS NULL-------::");
                 permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                 infoAudit.setDimasukOleh(pengguna);
                 infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanLaporanKandungan.setInfoAudit(infoAudit);
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                permohonanLaporanKandungan.setKand(keadaankand);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);

     }
             
     // Saving in MOHON PERMIT ITEM            
       if(permohonan.getKodUrusan().getKod().equals("PLPS")){                            
             LOG.info("-------Saving in MOHON PERMIT ITEM FOR LPS-------::");
             permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
             LOG.info("-------permohonanPermitItem-------::"+permohonanPermitItem);
             if(permohonanPermitItem !=null){
                 LOG.info("-------permohonanPermitItem NOT Null-------::");
                 infoAudit = permohonanPermitItem.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());

             }else{
                 LOG.info("-------permohonanPermitItem Is Null-------::");
                 permohonanPermitItem = new PermohonanPermitItem();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
             permohonanPermitItem.setPermohonan(permohonan);
             permohonanPermitItem.setKodCawangan(cawangan);
             permohonanPermitItem.setInfoAudit(infoAudit);
             if(keg != null){
                 KodItemPermit kp = kodItemPermitDAO.findById(keg);
                 permohonanPermitItem.setKodItemPermit(kp);
             }
             plpservice.saveOrUpdate(permohonanPermitItem);
       }
            
             // Saving in MOHON PERMIT ITEM
             if(permohonan.getKodUrusan().getKod().equals("PBMT")){
               if(ksn != null && ksn.equalsIgnoreCase("SU")){
                   LOG.info("-------Syor Selected-------::"+ksn);             
             LOG.info("-------Saving in MOHON PERMIT ITEM FOR PBMT-------::");
             permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
             LOG.info("-------permohonanPermitItem-------::"+permohonanPermitItem);
             if(permohonanPermitItem !=null){
                 LOG.info("-------permohonanPermitItem NOT Null-------::");
                 infoAudit = permohonanPermitItem.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());

             }else{
                 LOG.info("-------permohonanPermitItem Is Null-------::");
                 permohonanPermitItem = new PermohonanPermitItem();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
             permohonanPermitItem.setPermohonan(permohonan);
             permohonanPermitItem.setKodCawangan(cawangan);
             permohonanPermitItem.setInfoAudit(infoAudit);
             if(keg != null){
                 KodItemPermit kp = kodItemPermitDAO.findById(keg);
                 permohonanPermitItem.setKodItemPermit(kp);
             }
             plpservice.saveOrUpdate(permohonanPermitItem);
                 }
       }
             if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                if (ksn != null && ksn.equalsIgnoreCase("SL")) {
                    permohonanBahanBatuan = plpservice.findByIdMBB(idPermohonan);
                    if (permohonanBahanBatuan != null) {
                        InfoAudit ia = new InfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDimasukOleh(permohonanBahanBatuan.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(permohonanBahanBatuan.getInfoAudit().getTarikhMasuk());
                        permohonanBahanBatuan.setInfoAudit(ia);

                        jumlahIsipaduUOM = new KodUOM();
                        String kod3 = getContext().getRequest().getParameter("jumlahIsipaduUom.kod");
                        jumlahIsipaduUOM.setKod(kod3);
                        permohonanBahanBatuan.setJumlahIsipaduUom(jumlahIsipaduUOM);
                        String isipadu = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipadu");
                        if (isipadu != null && !("").equals(isipadu)) {
                            jumlahIsipadu = new BigDecimal(isipadu);
                            permohonanBahanBatuan.setJumlahIsipadu(jumlahIsipadu);
                            permohonanBahanBatuan.setTempohSyorUom(kodUOMDAO.findById(tempohSyorUOM));
                            plpservice.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
                        }
                    }
                }

            }


             if(permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("LPSP")||permohonan.getKodUrusan().getKod().equals("LMCRG")||permohonan.getKodUrusan().getKod().equals("PJLB")){
               
             LOG.info("-------Saving in MOHON TUNTUTKOS For PLPS-------::");
             permohonanTuntutanKos = plpservice.findMohonTuntutKosByIdPermohonan(idPermohonan);
             LOG.info("-------permohonanTuntutanKos-------::"+permohonanTuntutanKos);
             if(permohonanTuntutanKos != null){
                 LOG.info("-------permohonanTuntutanKos NOT Null-------::");
                 infoAudit = permohonanTuntutanKos.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
             }else{
                 LOG.info("-------permohonanTuntutanKos Is Null-------::");
                 permohonanTuntutanKos = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setInfoAudit(infoAudit);
            permohonanTuntutanKos.setCawangan(pengguna.getKodCawangan());
             if(permohonan.getKodUrusan().getKod().equals("PLPS")) 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
             else if(permohonan.getKodUrusan().getKod().equals("LPSP")) 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DIS4B").getNama());
             else if(permohonan.getKodUrusan().getKod().equals("LMCRG")) 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISCR").getNama());
             else if(permohonan.getKodUrusan().getKod().equals("PJLB")) 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISLB").getNama());
             
            permohonanTuntutanKos.setAmaunTuntutan(amnt);
            plpservice.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
               }                  

         // Add record in mohon_lapor_kws
            LOG.info("---------------Saving in MOHON_LAPOR_KWS-------------::");
         LOG.info("-------Check Value catatan::------" + catatanLain1);
        LOG.info("Check Value PBT::" + pbt1);
        InfoAudit info = pengguna.getInfoAudit();
         if(StringUtils.isNotBlank(pbt1)){
             rizab = tanahRizabService.findByKod(pbt1);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, pbt1);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("1");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"1");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }


        if(StringUtils.isNotBlank(hutan)){
             rizab = tanahRizabService.findByKod(hutan);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, hutan);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("4");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"4");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }

         if(StringUtils.isNotBlank(rizab_melayu)){
             rizab = tanahRizabService.findByKod(rizab_melayu);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, rizab_melayu);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }
        else{
             rizab = tanahRizabService.findByKod("3");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"3");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }
         if(StringUtils.isNotBlank(gsa)){
             rizab = tanahRizabService.findByKod(gsa);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, gsa);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("2");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"2");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }
         if(StringUtils.isNotBlank(lain)){
             rizab = tanahRizabService.findByKod(lain);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, lain);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("99");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"99");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }

        senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
          senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
          rowCount2 = senaraiLaporanKandungan1.size();
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }
   public Resolution simpanLaporanTanah() throws Exception {

       LOG.info("-simpanLaporanTanah(-::");
        if(validation2()){
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
            permohonan.setSebab(catatanKeg);
            plpservice.simpanPermohonan(permohonan);
        }
        if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                     if(permohonan.getPermohonanSebelum() != null)
                     hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan()) ;
                 }
                 else {
                         if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                             List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                              if(permohonan.getKodUrusan().getKod().equals("PTGSA"))
                                senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                                else
                                senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                             if(senaraiHakmilikTerlibat.size()>0)
                                 hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                         }
                         else
                             hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);

                     }
         String[] tname = {"permohonan"};
            Object[] model = {permohonan};

        String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");
        System.out.println("-----kodS---"+kodS) ;

        if((kodS!=null) && !kodS.equals("0")){
            Integer a = Integer.parseInt(kodS) ;
            int kod = a.intValue() ;
            KodSeksyen kodSeksyen = plpservice.findByKodSeksyen(kod) ;
            hakmilikPermohonan.setKodSeksyen(kodSeksyen);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }else {

        }       
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        String validulasan =(getContext().getRequest().getParameter("plpulasan1"));
        String validulasan2 = (getContext().getRequest().getParameter("plpulasan0"));
         LOG.info("-------validulasan-------::"+validulasan);
         if((validulasan == null) || (validulasan == "")){
             if(validulasan2!=null&&StringUtils.isBlank(validulasan2)){
                 addSimpleError("Sila masukkan Ulasan.");
                return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
             }
             if(validulasan2==null){
                 addSimpleError("Sila masukkan Ulasan.");
                return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
             }
         }  
        
        // Saving in Laporan Tanah
        LOG.info("-------------Saving In Laporan Tanah----------------");
         
        
//        BigDecimal jarakSempadanJalanRaya = laporanTanah.getJarakSempadanJalanraya();
//        BigDecimal jarakSempadanJalanKetapi = laporanTanah.getJarakSempadanKeretapi();
//        BigDecimal jarakSempadanLaut = laporanTanah.getJarakSempadanLaut();
//        BigDecimal jarakSempadanSungai = laporanTanah.getJarakSempadanSungai();
//        BigDecimal jaraskSempadanLain = laporanTanah.getJarakSempadanLain();
//        BigDecimal ketinggianDariJalan = laporanTanah.getKetinggianDariJalan();
//        BigDecimal kecerunanBukit = laporanTanah.getKecerunanBukit();
//        BigDecimal parasAir = laporanTanah.getParasAir();
//        laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
        
        
        //laporanTanah.setBolehBerimilik(kodBolehBerimilik);
        LOG.info("-------------laporanTanah----------------"+laporanTanah);
        LOG.info("THIS IS PENGGUNA ->"+pengguna);
        if (checkTanahExist) {
            LOG.info("-------------Laporan Tanah NOT Null----------------");
                infoAudit = new InfoAudit();
                infoAudit = laporanTanah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
            LOG.info("-------------Laporan Tanah Is Null----------------");
                //laporanTanah = new LaporanTanah();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            if(laporanTanah.getAdaBangunan()!= null){
                if(laporanTanah.getAdaBangunan() == 'T'){
                   laporanTanah.setBilanganBangunan(0);
               }
            }
        if(kecerunanT !=null){
                 LOG.info("-------------kecerunanT----------------::"+kecerunanT);
                KodKecerunanTanah kkt = kodKecerunanTanahDAO.findById(kecerunanT);
                LOG.info("-------------kkt----------------::"+kkt);
                laporanTanah.setKecerunanTanah(kkt);
            }
        if(klasifikasiT !=null){
                 LOG.info("-------------KlasifikasiT----------------::"+klasifikasiT);
                KodStrukturTanah kft = kodStrukturTanahDAO.findById(klasifikasiT);
                LOG.info("-------------kft----------------::"+kft);
                laporanTanah.setStrukturTanah(kft);
            }
         if(keadaanTanah !=null){
                 LOG.info("-------------keadaanTanah----------------::"+keadaanTanah);
           KodKeadaanTanah kdt = kodKeadaanTanahDAO.findById(keadaanTanah);
                LOG.info("-------------kdt----------------::"+kdt);
                laporanTanah.setKodKeadaanTanah(kdt);
            }
         /*
         * BERSEMPADANAN 
         */
//         laporanTanah.setNamaSempadanJalanraya(getContext().getRequest().getParameter("laporanTanah.namaSempadanJalanraya"));
//         laporanTanah.setNamaSempadanKeretapi(getContext().getRequest().getParameter("laporanTanah.namaSempadanKeretapi"));         
//         laporanTanah.setNamaSempadanLaut(getContext().getRequest().getParameter("laporanTanah.namaSempadanLaut"));
//         laporanTanah.setNamaSempadanSungai(getContext().getRequest().getParameter("laporanTanah.namaSempadanSungai"));
//         laporanTanah.setNamaSempadanlain(getContext().getRequest().getParameter("laporanTanah.namaSempadanlain"));
//         
//         laporanTanah.setJarakSempadanJalanraya(jarakSempadanJalanRaya);         
//         laporanTanah.setJarakSempadanKeretapi(jarakSempadanJalanKetapi);
//         laporanTanah.setJarakSempadanLaut(jarakSempadanLaut);
//         laporanTanah.setJarakSempadanSungai(jarakSempadanSungai);
//         laporanTanah.setJarakSempadanLain(jaraskSempadanLain);
//         
//         KodUOM km = new KodUOM();
//         km = kodUOMDAO.findById("KM");
//         
//         laporanTanah.setJarakSempadanJalanrayaUOM(km); 
//         laporanTanah.setJarakSempadanKeretapiUOM(km);
//         laporanTanah.setJarakSempadanLautUOM(km);
//         laporanTanah.setJarakSempadanSungaiUOM(km);
//         laporanTanah.setJarakSempadanLainUOM(km);
         
         /*
          * END OF BERSEMPADANAN
          */
         
//         laporanTanah.setBolehBerimilik(getContext().getRequest().getParameter(" laporanTanah.bolehBerimilik"));
//         laporanTanah.setSebabTidakBolehBerimilik(getContext().getRequest().getParameter("laporanTanah.sebabTidakBolehBerimilik"));
//         laporanTanah.setJenisJalan(getContext().getRequest().getParameter("laporanTanah.jenisJalan"));
//         laporanTanah.setAdaJalanMasuk(getContext().getRequest().getParameter("laporanTanah.adaJalanMasuk").charAt(0));
//         laporanTanah.setCatatanJalanMasuk(getContext().getRequest().getParameter("laporanTanah.catatanJalanMasuk"));
//         laporanTanah.setKetinggianDariJalan(ketinggianDariJalan);
//         laporanTanah.setKecerunanBukit(kecerunanBukit);
//         laporanTanah.setParasAir(parasAir);
//         laporanTanah.setStrukturTanahLain(getContext().getRequest().getParameter("laporanTanah.strukturTanahLain"));
//         laporanTanah.setTanahBertumpu(getContext().getRequest().getParameter("laporanTanah.tanahBertumpu"));
         
         laporanTanah.setInfoAudit(infoAudit);
         laporanTanah.setPermohonan(permohonan);
         laporanTanah.setUsahaLuas(usahaLuas);
         laporanTanah.setUsahaBilanganPokok(usahaBilanganPokok);
         laporanTanah.setUsahaHarga(usahaHarga);
         laporanTanahService.simpanLaporanTanah(laporanTanah);

        // Saving in MOHON_FASA

        LOG.info("-------------Saving in MOHON_FASA-----------------::");
        stageId = "laporan_tanah";
        fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        LOG.info("-------------fasaPermohonan-----------------::"+fasaPermohonan);
        boolean checkSaveOnly = false;
        if (fasaPermohonan != null) {
            LOG.info("-------------MOHON_FASA NOT Null-----------------::");
            infoAudit = fasaPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());   
            checkSaveOnly = Boolean.FALSE;
        }else{
            LOG.info("-------------MOHON_FASA is Null-----------------::");
            fasaPermohonan = new FasaPermohonan();
            LOG.info("-------------Dimasuk-----------------::"+pengguna.getNama());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            checkSaveOnly = Boolean.TRUE;
        }
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setPermohonan(permohonan);
            
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            if(ksn != null){
                LOG.info("-------------ksn-----------------::"+ksn);
                KodKeputusan kkp = kodKeputusanDAO.findById(ksn);
                LOG.info("-------------kkp-----------------::"+kkp);
                fasaPermohonan.setKeputusan(kkp);
            }
            //fasaPermohonan.setUlasan(ulasan); //DISABLED SINCE USING plpulasan1
            fasaPermohonan.setUlasan(plpulasan0);
            fasaPermohonan.setIdAliran(stageId);
            if(checkSaveOnly)
                laporanTanahService.simpanSaveOnlyFasaPermohonan(fasaPermohonan);
            else
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            
        //saving in Mohon_Lapor_ulas
        
        LOG.info("-------Saving in MOHON_LAPOR_ULAS-------::");

        senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
        LOG.info("-----senaraiLaporanKandungan1-----:"+senaraiLaporanKandungan1);
        String countElement_ref =  getContext().getRequest().getParameter("countElement");
        LOG.info("-----------countElement_ref------------:"+countElement_ref);
        int countElement_ref_no = Integer.parseInt(countElement_ref);
        String julasan_ref = getContext().getRequest().getParameter("plpulasan" + String.valueOf(countElement_ref_no+1));
        if(julasan_ref ==null ){
            julasan_ref = getContext().getRequest().getParameter("plpulasan" + countElement_ref);
        }

        for(int j=1; j<= countElement_ref_no; j++){
           String idUlasan = getContext().getRequest().getParameter("idLaporUlas" + String.valueOf(j));
           String julasan = getContext().getRequest().getParameter("plpulasan" + String.valueOf(j));
                    permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                    permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(new Long(idUlasan));
                    infoAudit = permohonanLaporanUlasan.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    permohonanLaporanUlasan.setPermohonan(permohonan);
                    permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanUlasan.setInfoAudit(infoAudit);
                    permohonanLaporanUlasan.setJenisUlasan("Syarat4A");
                    permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
                    if(julasan==null || julasan.equals("")){
                        permohonanLaporanUlasan.setUlasan("TIADA DATA");
                    }else{
                        permohonanLaporanUlasan.setUlasan(julasan);
                    }
                    plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);

        }


            LOG.info("-----------julasan_ref------------:"+julasan_ref);
         if(julasan_ref !=null && !julasan_ref.equals("")){
         List<PermohonanLaporanUlasan> jenisUlasanList = plpservice.findJenisUlasan(idPermohonan,"Syarat4A",julasan_ref);
         permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            if (jenisUlasanList.size() < 0  ) {
               Long ju = senaraiLaporanKandungan1.get(0).getIdLaporUlas();
               LOG.info("-----------ju------------:"+ju);
                if (ju != null) {
                    LOG.info("-----------ju Not Null------------:");
                    permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(ju);
                    infoAudit = permohonanLaporanUlasan.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                }
            } else {
                LOG.info("-----------ju is Null------------:");
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Syarat4A");
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            if(julasan_ref==null || julasan_ref.equals("")){
                permohonanLaporanUlasan.setUlasan("TIADA DATA");
            }else{
                permohonanLaporanUlasan.setUlasan(julasan_ref);
            }
           plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);

      }

         String kira2_ref =  getContext().getRequest().getParameter("rowCount2");
         int kira2 = 0;
         if(kira2_ref != null && !kira2_ref.equals("")){
            kira2 = Integer.parseInt(kira2_ref);
         }

        LOG.info("-----------kira2------------:"+kira2);
        for (int i = 1; i <= kira2; i++) {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
               Long ju = senaraiLaporanKandungan1.get(i - 1).getIdLaporUlas();
               LOG.info("-----------ju------------:"+ju);
                if (ju != null) {
                    LOG.info("-----------ju Not Null------------:");
                    permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(ju);
                    infoAudit = permohonanLaporanUlasan.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                }
            } else {
                LOG.info("-----------ju is Null------------:");
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Syarat4A");
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            String julasan = getContext().getRequest().getParameter("plpulasan" + i);
            LOG.info("-----------ju ulasan------------:"+ulasan);
            if(julasan==null || julasan.equals("")){
                permohonanLaporanUlasan.setUlasan("TIADA DATA");
            }else{
                permohonanLaporanUlasan.setUlasan(julasan);
            }
           plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);            
        }

            

            //saving in Mohon_Lapor_ulas
        if(ksn != null && ksn.equalsIgnoreCase("SL")){
        LOG.info("-------Saving in MOHON_LAPOR_ULAS For Syor LULUS-------::");
        permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Ulasan PPT");
        LOG.info("------permohonanLaporanUlasan-------::"+permohonanLaporanUlasan);

        if(permohonanLaporanUlasan != null){
            LOG.info("-----permohonanLaporanUlasan NOT Null-----:");
            infoAudit = permohonanLaporanUlasan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        }else{
            LOG.info("-----permohonanLaporanUlasan is Null-----:");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
            permohonanLaporanUlasan.setUlasan(plpulasan0);
            LOG.info("-----ulsn-----:"+ulsn);
            permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);             
        }else{
            LOG.info("-------Saving in MOHON_LAPOR_ULAS CONDITION WHEN NO KEPUTUSAN-------::");
            permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Ulasan PPT");
            LOG.info("------permohonanLaporanUlasan-------::"+permohonanLaporanUlasan);

            if(permohonanLaporanUlasan != null){
                LOG.info("-----permohonanLaporanUlasan NOT Null-----:");
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            }else{
                LOG.info("-----permohonanLaporanUlasan is Null-----:");
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
                permohonanLaporanUlasan.setPermohonan(permohonan);
                permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
                permohonanLaporanUlasan.setInfoAudit(infoAudit);
                permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
                permohonanLaporanUlasan.setUlasan(plpulasan0);
                LOG.info("-----ulsn-----:"+ulsn);
                permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
                plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);          
        }
      
        //Saving in Mohon_Rizab

            if(kodP == 'R'){
                LOG.info("-------kodP-------::"+kodP);
            
        LOG.info("-------Saving in MOHON_RIZAB-------::");
         tanahrizabpermohonan1=tanahRizabService.findByidPermohonan(idPermohonan);
         LOG.info("-------MOHON_RIZAB-------::"+tanahrizabpermohonan1);
        if(tanahrizabpermohonan1!=null){
            LOG.info("-------MOHON_RIZAB is Not null-------::");
                infoAudit = tanahrizabpermohonan1.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

        }else {
             LOG.info("-------MOHON_RIZAB is null-------::");
             tanahrizabpermohonan1 = new TanahRizabPermohonan();
             infoAudit.setDimasukOleh(pengguna);
             infoAudit.setTarikhMasuk(new java.util.Date());
        }
             tanahrizabpermohonan1.setInfoAudit(infoAudit);
             tanahrizabpermohonan1.setPermohonan(permohonan);
             tanahrizabpermohonan1.setCawangan(permohonan.getCawangan());
             tanahrizabpermohonan1.setDaerah(cawangan.getDaerah());
             tanahrizabpermohonan1.setAktif('Y');
             if((hakmilikPermohonan!=null) && (hakmilikPermohonan.getBandarPekanMukimBaru()!=null)){
             tanahrizabpermohonan1.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
             }

            if (tanahR != null) {
                KodRizab kr = kodRizabDAO.findById(Integer.parseInt(tanahR));
                tanahrizabpermohonan1.setRizab(kr);
            }
             if((hakmilikPermohonan!=null)&&(hakmilikPermohonan.getNoLot()!=null)){
             tanahrizabpermohonan1.setNoLot(hakmilikPermohonan.getNoLot());
             }
             if(!StringUtils.isBlank(statBdnPngwl)){
                 String penjaga = new String();
                  penjaga = statBdnPngwl.equals("1")?"SUK Negeri": 
                                 statBdnPngwl.equals("2")?"Pesuruhjaya Tanah Persekutuan":
                                 !statBdnPngwl.equals("1")&&!statBdnPngwl.equals("2")?tanahrizabpermohonan1.getPenjaga():
                                 "0";
                 tanahrizabpermohonan1.setPenjaga(penjaga);
             }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan1);
            }

         //Saving in Hakmilik Permohonan
         LOG.info("-------Saving in HakmilikPermohonan-------::");
         if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
             hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
         }
         else {
             if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                 senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                 if(senaraiHakmilikTerlibat.size()>0)
                     hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
             }
             else
                 hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
         
         }
         LOG.info("-------hakmilikPermohonan-------::"+hakmilikPermohonan);
        
         if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            hakmilikPermohonan = new HakmilikPermohonan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
            
        if (kod != null) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
        }
        String jarakDariKediamanUOM = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
        String unitJarak = getContext().getRequest().getParameter("unitJarak.kod");
        if(!StringUtils.isEmpty(jarakDariKediamanUOM)){
            hakmilikPermohonan.setJarakDariKediamanUom(kodUOMDAO.findById(jarakDariKediamanUOM));
        }
        
        if(!StringUtils.isEmpty(unitJarak)){
            hakmilikPermohonan.setUnitJarak(kodUOMDAO.findById(unitJarak));
        }
         if (kodD != null) {
                KodDUN kd = kodDUNDAO.findById(kodD);
                hakmilikPermohonan.setKodDUN(kd);
            }
         if (kodPar != null) {
                KodKawasanParlimen kw = kodKawasanParlimenDAO.findById(kodPar);
                hakmilikPermohonan.setKodKawasanParlimen(kw);
            }
         
         if (kodSktn != null) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
        }
         
         if (kodHmlk != null) {
                KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
                hakmilikPermohonan.setKodHakmilikSementara(khm);
            }
        if (kodHmlkTetap != null) {
                KodHakmilik khmTetap = kodHakmilikDAO.findById(kodHmlkTetap);
                hakmilikPermohonan.setKodHakmilikTetap(khmTetap);
            }
         if (kodP != ' ') {
                KodPemilikan kpm = kodPemilikanDAO.findById(kodP);
                hakmilikPermohonan.setKodMilik(kpm);
            }
         String checkStatusLuas = getContext().getRequest().getParameter("hakmilikPermohonan.statusLuasDiluluskan");
         if(StringUtils.isNotBlank(checkStatusLuas)){
             if(checkStatusLuas.equals("P")){
                 hakmilikPermohonan.setLuasDiluluskan(hakmilikPermohonan.getLuasTerlibat());
                 hakmilikPermohonan.setLuasLulusUom(hakmilikPermohonan.getKodUnitLuas());
             }
             hakmilikPermohonan.setStatusLuasDiluluskan(checkStatusLuas);
         }
            
            hakmilikPermohonan.setKeteranganKadarPremium(keteranganKadarPremium);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            if(permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")){
                hakmilikPermohonan.setPermohonan(permohonan.getPermohonanSebelum());
            }
            else {
                hakmilikPermohonan.setPermohonan(permohonan);
            }
            
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);

         if(permohonan.getKodUrusan().getKod().equals("PRMP")){
                      if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
         }
         if(permohonan.getKodUrusan().getKod().equals("PPRU")){
                      if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
         }
          if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                      if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
         }
          if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                      if (kodU != null) {
                KodUOM kuom = kodUOMDAO.findById(kodU);
                hakmilikPermohonan.setLuasLulusUom(kuom);
            }
         }
         /*
          * SAVING NO_PT
          */
            
            if(permohonan.getKodUrusan().getKod().equals("PBMT")){
                    HakmilikPermohonan mh = new HakmilikPermohonan();
                    LOG.info("This is ID Permohonan ->"+idPermohonan);
                    mh = plpservice.findByIdPermohonan(idPermohonan);
                    noPT = plpservice.findNoPtByIdHakmilikPermohonan(mh.getId()); 
                if (noPT != null) {
                    infoAudit = noPT.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    if(kodU!=null){
                        KodUOM kuom = kodUOMDAO.findById(kodU);
                        noPT.setKodUOM(kuom);
                        
                    }
                    noPT.setLuasDilulus(luasDilulus);
                    noPT.setInfoAudit(infoAudit);
                    noPT.setHakmilikPermohonan(mh);
                     plpservice.simpanNoPt(noPT);

                } else {
                    noPT = new NoPt();
                    LOG.info("THIS IS PENGGUNA ->"+pengguna);
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    noPT.setInfoAudit(infoAudit);
                    if(kodU!=null){
                        KodUOM kuom = kodUOMDAO.findById(kodU);
                        noPT.setKodUOM(kuom);
                    }
                    noPT.setLuasDilulus(luasDilulus);
                    noPT.setHakmilikPermohonan(mh);
                     plpservice.simpanSaveOnlyNoPt(noPT);
                }
                    
            }
            
         /*
          * END OF NO_PT SAVE
          */
            
            
         //Saving in MOHON LAPOR PELAN
             LOG.info("-------Saving in MOHON LAPOR PELAN-------::");
            PermohonanLaporanPelan permohonanLaporanPelan1 = plpservice.findByIdPermohonanPelan(idPermohonan);
             LOG.info("------permohonanLaporanPelan1------"+permohonanLaporanPelan1);
             if(permohonanLaporanPelan1!=null){
                 LOG.info("------permohonanLaporanPelan1 Not NULL------");
                 infoAudit = permohonanLaporanPelan1.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());                 
             } else {
                 LOG.info("-----permohonanLaporanPelan1 is NULL------");
                permohonanLaporanPelan1 = new PermohonanLaporanPelan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
             permohonanLaporanPelan1.setInfoAudit(infoAudit);
             permohonanLaporanPelan1.setCawangan(cawangan);
             permohonanLaporanPelan1.setPermohonan(permohonan);
             if (kodT != null) {
                KodTanah ktt = kodTanahDAO.findById(kodT);
                LOG.info("------kodT------"+kodT);
                LOG.info("------ktt------"+ktt);
                permohonanLaporanPelan1.setKodTanah(ktt);
            }
             plpservice.simpanPermohonanLaporanPelan(permohonanLaporanPelan1);

       // Saving in MOHON LAPOR KAND
             LOG.info("-------Saving in MOHON LAPOR KAND For Jenis Tanah-------::");
          if(kodT.equalsIgnoreCase("99")){
                 LOG.info("-------JenisTanah Selected-------::"+kodT);
             permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Jenis Tanah", laporanTanah.getIdLaporan());
             LOG.info("-------permohonanLaporanKandungan-------::"+permohonanLaporanKandungan);
             if(permohonanLaporanKandungan !=null){
                 LOG.info("-------permohonanLaporanKandungan NOT NULL-------::");
                 infoAudit = permohonanLaporanKandungan.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());                 
             }else{
                 LOG.info("-------permohonanLaporanKandungan IS NULL-------::");
                 permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                 infoAudit.setDimasukOleh(pengguna);
                 infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanLaporanKandungan.setInfoAudit(infoAudit);
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Jenis Tanah");
                permohonanLaporanKandungan.setKand(kand);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
                 
       }

          // Saving in MOHON LAPOR KAND
             LOG.info("-------Saving in MOHON LAPOR KAND For Keadaan Tanah-------::");
           if(keadaanTanah.equalsIgnoreCase("LL")){
                 LOG.info("-------keadaanTanah Selected-------::"+keadaanTanah);
             permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", laporanTanah.getIdLaporan());
             LOG.info("-------permohonanLaporanKandungan-------::"+permohonanLaporanKandungan);
             if(permohonanLaporanKandungan !=null){
                 LOG.info("-------permohonanLaporanKandungan NOT NULL-------::");
                 infoAudit = permohonanLaporanKandungan.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
             }else{
                 LOG.info("-------permohonanLaporanKandungan IS NULL-------::");
                 permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                 infoAudit.setDimasukOleh(pengguna);
                 infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanLaporanKandungan.setInfoAudit(infoAudit);
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Lain-lain Keadaan Tanah");
                permohonanLaporanKandungan.setKand(keadaankand);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);

     }
             
     // Saving in MOHON PERMIT ITEM            
       if(permohonan.getKodUrusan().getKod().equals("PLPS")){                            
             LOG.info("-------Saving in MOHON PERMIT ITEM FOR LPS-------::");
             permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
             LOG.info("-------permohonanPermitItem-------::"+permohonanPermitItem);
             if(permohonanPermitItem !=null){
                 LOG.info("-------permohonanPermitItem NOT Null-------::");
                 infoAudit = permohonanPermitItem.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());

             }else{
                 LOG.info("-------permohonanPermitItem Is Null-------::");
                 permohonanPermitItem = new PermohonanPermitItem();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
             permohonanPermitItem.setPermohonan(permohonan);
             permohonanPermitItem.setKodCawangan(cawangan);
             permohonanPermitItem.setInfoAudit(infoAudit);
             if(keg != null){
                 KodItemPermit kp = kodItemPermitDAO.findById(keg);
                 permohonanPermitItem.setKodItemPermit(kp);
             }
             plpservice.saveOrUpdate(permohonanPermitItem);
             if(StringUtils.isNotBlank(ulsn)){
                 permohonanLaporanKandungan = plpservice.findByIdLaporSubtajuk("Syarat Tambahan LPS", laporanTanah.getIdLaporan());
             LOG.info("-------permohonanLaporanKandungan-------::"+permohonanLaporanKandungan);
             if(permohonanLaporanKandungan !=null){
                 LOG.info("-------permohonanLaporanKandungan NOT NULL-------::");
                 infoAudit = permohonanLaporanKandungan.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
             }else{
                 LOG.info("-------permohonanLaporanKandungan IS NULL-------::");
                 permohonanLaporanKandungan = new PermohonanLaporanKandungan();
                 infoAudit.setDimasukOleh(pengguna);
                 infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanLaporanKandungan.setInfoAudit(infoAudit);
                permohonanLaporanKandungan.setLaporanTanah(laporanTanah);
                permohonanLaporanKandungan.setSubtajuk("Syarat Tambahan LPS");
                permohonanLaporanKandungan.setKand(ulsn);
                plpservice.simpanPermohonanLaporanKandungan(permohonanLaporanKandungan);
             }            
       }
            
             // Saving in MOHON PERMIT ITEM
             if(permohonan.getKodUrusan().getKod().equals("PBMT")){
               if(ksn != null && ksn.equalsIgnoreCase("SU")){
                   LOG.info("-------Syor Selected-------::"+ksn);             
             LOG.info("-------Saving in MOHON PERMIT ITEM FOR PBMT-------::");
             permohonanPermitItem = plpservice.findByIdMohonPermitItem(idPermohonan);
             LOG.info("-------permohonanPermitItem-------::"+permohonanPermitItem);
             if(permohonanPermitItem !=null){
                 LOG.info("-------permohonanPermitItem NOT Null-------::");
                 infoAudit = permohonanPermitItem.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());

             }else{
                 LOG.info("-------permohonanPermitItem Is Null-------::");
                 permohonanPermitItem = new PermohonanPermitItem();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
             permohonanPermitItem.setPermohonan(permohonan);
             permohonanPermitItem.setKodCawangan(cawangan);
             permohonanPermitItem.setInfoAudit(infoAudit);
             if(keg != null){
                 KodItemPermit kp = kodItemPermitDAO.findById(keg);
                 permohonanPermitItem.setKodItemPermit(kp);
             }
             plpservice.saveOrUpdate(permohonanPermitItem);
                 }
       }
             if (permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") ) {
                if (ksn != null && ksn.equalsIgnoreCase("SL")) {
                    permohonanBahanBatuan = plpservice.findByIdMBB(idPermohonan);
                    if (permohonanBahanBatuan != null) {
                        InfoAudit ia = new InfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDimasukOleh(permohonanBahanBatuan.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(permohonanBahanBatuan.getInfoAudit().getTarikhMasuk());
                        permohonanBahanBatuan.setInfoAudit(ia);

                        jumlahIsipaduUOM = new KodUOM();
                        String kod3 = getContext().getRequest().getParameter("jumlahIsipaduUom.kod");
                        jumlahIsipaduUOM.setKod(kod3);
                        permohonanBahanBatuan.setJumlahIsipaduUom(jumlahIsipaduUOM);
                        String isipadu = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipadu");
                        String tempohDisyor = getContext().getRequest().getParameter("permohonanBahanBatuan.tempohDisyor");
                        if (isipadu != null && !("").equals(isipadu)) {
                            jumlahIsipadu = new BigDecimal(isipadu);
                            LOG.info(jumlahIsipadu);
                            permohonanBahanBatuan.setJumlahIsipadu(jumlahIsipadu);
                            if(tempohDisyor != null){
                                int tmph = Integer.parseInt(tempohDisyor) ;
                            permohonanBahanBatuan.setTempohDisyor(tmph);
                            }
                            permohonanBahanBatuan.setTempohSyorUom(kodUOMDAO.findById(tempohSyorUOM));
                            plpservice.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
                        }
                    }
                }

            }

              if(permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PRMP")|| permohonan.getKodUrusan().getKod().equals("PPRU")||permohonan.getKodUrusan().getKod().equals("PPTR")||permohonan.getKodUrusan().getKod().equals("LMCRG")||permohonan.getKodUrusan().getKod().equals("PJLB")){
               
             LOG.info("-------Saving in MOHON TUNTUTKOS For PLPS-------::");
             permohonanTuntutanKos = plpservice.findMohonTuntutKosByIdPermohonan(idPermohonan);
             LOG.info("-------permohonanTuntutanKos-------::"+permohonanTuntutanKos);
             if(permohonanTuntutanKos != null){
                 LOG.info("-------permohonanTuntutanKos NOT Null-------::");
                 infoAudit = permohonanTuntutanKos.getInfoAudit();
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
             }else{
                 LOG.info("-------permohonanTuntutanKos Is Null-------::");
                 permohonanTuntutanKos = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
             }
                permohonanTuntutanKos.setPermohonan(permohonan);
                permohonanTuntutanKos.setInfoAudit(infoAudit);
                permohonanTuntutanKos.setCawangan(pengguna.getKodCawangan());
            
             if(permohonan.getKodUrusan().getKod().equals("PLPS")){ 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISB4A").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("LPSP")){ 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DIS4B").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DIS4B"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DIS4B").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("PRMP")){ 
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB7").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB7"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISB7").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("PPRU")){
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4D").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB4D"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISB4D").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4E").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB4E"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISB4E").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISCR").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
             }
             else if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISLB").getNama());
                permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISLB"));
                permohonanTuntutanKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans());
             }
                permohonanTuntutanKos.setAmaunTuntutan(amnt);
                plpservice.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
               }            

         // Add record in mohon_lapor_kws
            LOG.info("---------------Saving in MOHON_LAPOR_KWS-------------::");
         LOG.info("-------Check Value catatan::------" + catatanLain1);
        LOG.info("Check Value PBT::" + pbt1);
        InfoAudit info = pengguna.getInfoAudit();
         if(StringUtils.isNotBlank(pbt1)){
             rizab = tanahRizabService.findByKod(pbt1);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, pbt1);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("1");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"1");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }


        if(StringUtils.isNotBlank(hutan)){
             rizab = tanahRizabService.findByKod(hutan);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, hutan);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("4");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"4");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }

         if(StringUtils.isNotBlank(rizab_melayu)){
             rizab = tanahRizabService.findByKod(rizab_melayu);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, rizab_melayu);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }
        else{
             rizab = tanahRizabService.findByKod("3");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"3");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }
         if(StringUtils.isNotBlank(gsa)){
             rizab = tanahRizabService.findByKod(gsa);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, gsa);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("2");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"2");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }
         if(StringUtils.isNotBlank(lain)){
             rizab = tanahRizabService.findByKod(lain);
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, lain);
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info, "tick");
                        }else
                        {
                           savePerLaPoKaw(permohonanLaporanKawasan, rizab, info);
                        }

            }else{
             rizab = tanahRizabService.findByKod("99");
             permohonanLaporanKawasan = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan,"99");
                        if(permohonanLaporanKawasan != null){
                            updatePerLaPoKaw(permohonanLaporanKawasan, rizab, info , "UnTick");
                        }else
                        {
                           //DO Nothing
                        }
            }
            if(permohonan.getKodUrusan().getKod().equals("PPBB")||permohonan.getKodUrusan().getKod().equals("PBPTD")||permohonan.getKodUrusan().getKod().equals("PBPTG")){
                /*Add for Bayaran Kupon 
                 * 
                 */
                 mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISKD");
                
                if(mohonTuntutKos==null){
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(kupon);
                    mohonTuntutKos.setKuantiti(kuponQty);
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
                   // plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                    permohonanTuntutanKosDAO.save(mohonTuntutKos);
                }else{
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(kupon);
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKuantiti(kuponQty);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
                    plpservice.simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                /* End for bayaran kupon
                 * 
                 */
                /*Add for CAGARAN JALAN
                 * 
                 */
                   PermohonanTuntutanKos mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                    mohonTuntutKosCagarJln = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISCJ");
                    
                    if(mohonTuntutKosCagarJln==null){
                        mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                        mohonTuntutKosCagarJln.setInfoAudit(info);
                        mohonTuntutKosCagarJln.setPermohonan(permohonan);
                        mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagarJalan);
////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
                        mohonTuntutKosCagarJln.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
                        mohonTuntutKosCagarJln.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
                        plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosCagarJln);
                    }else{
                        mohonTuntutKosCagarJln.setInfoAudit(info);
                        mohonTuntutKosCagarJln.setPermohonan(permohonan);
                        mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagarJalan);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
                        mohonTuntutKosCagarJln.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
                        mohonTuntutKosCagarJln.setKodTransaksi(kodTuntutDAO.findById("DISCJ").getKodKewTrans());
                        plpservice.simpanPermohonanTuntutanKos(mohonTuntutKosCagarJln);
                    }
                /* End for bayaran kupon
                 * 
                 */ 
            }
                
         
        senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
          senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
          rowCount2 = senaraiLaporanKandungan1.size();
        LOG.info("ulasan PTKANAN"+ulasanKanan); 
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        rehydrate();
        testElement = "0";
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

   public Resolution deleteSingle() {
        String idLaporUlas = getContext().getRequest().getParameter("idLaporUlas");
        String checkStatus = getContext().getRequest().getParameter("checkStatus");
         permohonanLaporanUlasan = new PermohonanLaporanUlasan();
        try {
            permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(Long.parseLong(idLaporUlas));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
        }
        if (permohonanLaporanUlasan != null) {
            plpservice.deletePermohonanLaporanUlasan(permohonanLaporanUlasan);
            LOG.debug("Record deleted Successfully...");
        }

         senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
        rowCount2 = senaraiLaporanKandungan1.size();
        if(checkStatus.equals("1")){
            getContext().getRequest().setAttribute("addBox", Boolean.TRUE);
        }
        display = false;

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");

         
    }

   public Resolution addSingle() {
         String idLaporUlas = getContext().getRequest().getParameter("idLaporUlas");
         String textAreaData = getContext().getRequest().getParameter("textAreaData");
         LOG.info("--------textAreaData------------::"+textAreaData);
         LOG.info("-simpanLaporanTanah(-::");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //plpulasan0 = (getContext().getRequest().getParameter("plpulasan0"));
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
         String[] tname = {"permohonan"};
            Object[] model = {permohonan};


        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());




        LOG.info("-------Saving in MOHON_LAPOR_ULAS-------::");
   if(ksn == null ){
        senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
        LOG.info("-----senaraiLaporanKandungan1-----:"+senaraiLaporanKandungan1);
         int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count------------:"+kira2);
        //for (int i = 1; i <= kira2; i++) {
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            //if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
             //  Long ju = senaraiLaporanKandungan1.get(i - 1).getIdLaporUlas();
             //  LOG.info("-----------ju------------:"+ju);
               // if (ju != null) {
                    LOG.info("-----------ju Not Null------------:");
               //     permohonanLaporanUlasan = permohonanLaporanUlasanDAO.findById(ju);
               //     infoAudit = permohonanLaporanUlasan.getInfoAudit();
               //     infoAudit.setTarikhKemaskini(new java.util.Date());
               //     infoAudit.setDiKemaskiniOleh(pengguna);
              //  }
            //} else {
                LOG.info("-----------ju is Null------------:");
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            //}
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Syarat4A");
            //permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
           // String julasan = getContext().getRequest().getParameter("plpulasan" + i);
            LOG.info("-----------ju ulasan------------:"+ulasan);
            if(textAreaData==null || textAreaData.equals("")){
                permohonanLaporanUlasan.setUlasan("TIADA DATA");
            }else{
                permohonanLaporanUlasan.setUlasan(textAreaData);
            }
           // plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);
           List<PermohonanLaporanUlasan> senaraiLaporanKandungan1_ref = plpservice.findUlasan(idPermohonan, "Ulasan PPT");
           LOG.info("-------senaraiLaporanKandungan1_ref-------::"+senaraiLaporanKandungan1_ref.size());
           if(senaraiLaporanKandungan1_ref.size()==0){
               LOG.info("-------plpulasan0-------::"+plpulasan0);
               permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
            }
           plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);
           senaraiLaporanKandungan1_ref = new ArrayList<PermohonanLaporanUlasan>();
           senaraiLaporanKandungan1_ref = plpservice.findUlasan(idPermohonan, "Ulasan PPT");
           LOG.info("-------senaraiLaporanKandungan1_ref123-------::"+senaraiLaporanKandungan1_ref.size());
             PermohonanLaporanUlasan  permohonanLaporanUlasan2 = new PermohonanLaporanUlasan();
              permohonanLaporanUlasan2 = senaraiLaporanKandungan1_ref.get(0);
              LOG.info("-------permohonanLaporanUlasan2.getUlasan()-------::"+permohonanLaporanUlasan2.getUlasan());
             plpulasan0 = permohonanLaporanUlasan2.getUlasan();
        //}
}


            //saving in Mohon_Lapor_ulas
        if(ksn != null && ksn.equalsIgnoreCase("SL")){
        LOG.info("-------Saving in MOHON_LAPOR_ULAS For Syor LULUS-------::");
        permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "Ulasan PPT");
        LOG.info("------permohonanLaporanUlasan-------::"+permohonanLaporanUlasan);

        if(permohonanLaporanUlasan != null){
            LOG.info("-----permohonanLaporanUlasan NOT Null-----:");
            infoAudit = permohonanLaporanUlasan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        }else{
            LOG.info("-----permohonanLaporanUlasan is Null-----:");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            permohonanLaporanUlasan.setJenisUlasan("Ulasan PPT");
            permohonanLaporanUlasan.setUlasan(plpulasan0);
            LOG.info("-----ulsn-----:"+ulsn);
            //permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
            plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);
        }
        rehydrate();
        display = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("addBox", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }


   public void savePerLaPoKaw(PermohonanLaporanKawasan laporanKawasan , KodRizab kodRizab, InfoAudit info){
       Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                          String kodRi = String.valueOf(kodRizab.getKod());
                          laporanKawasan = new PermohonanLaporanKawasan();
                          info.setDiKemaskiniOleh(pengguna) ;
                          info.setTarikhKemaskini(new java.util.Date()) ;
                          laporanKawasan.setInfoAudit(info);
                          laporanKawasan.setKodCawangan(cawangan);
                          laporanKawasan.setKodRizab(kodRizab);
                          laporanKawasan.setAktif("Y");
                          if(kodRi.equalsIgnoreCase("99")){
                           if(StringUtils.isNotBlank(catatanLain1)){
                           laporanKawasan.setCatatan(catatanLain1);
                           }
                          }
                          laporanKawasan.setPermohonan(permohonan);
                          laporanPelukisPelanService.saveOrUpdatePermohonanLaporanKawasan(laporanKawasan);

                            LOG.info("save success");
    }

      public void updatePerLaPoKaw(PermohonanLaporanKawasan laporanKawasan , KodRizab kodRizab, InfoAudit info, String FlagSave){
          Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodRi = String.valueOf(kodRizab.getKod());
                           if(laporanKawasan != null){
                            info.setDiKemaskiniOleh(pengguna) ;
                            info.setTarikhKemaskini(new java.util.Date()) ;
                            laporanKawasan.setInfoAudit(info);
                            if(FlagSave.equalsIgnoreCase("tick")){
                            laporanKawasan.setAktif("Y");
                            }else if(FlagSave.equalsIgnoreCase("UnTick")){
                                laporanKawasan.setAktif("T");
                            }
                                if(kodRi.equalsIgnoreCase("99")){
                           if(StringUtils.isNotBlank(catatanLain1)){
                           laporanKawasan.setCatatan(catatanLain1);
                           }
                          }
                            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanKawasan(laporanKawasan);
                            LOG.info("Succes update");
                        }

    }

    public Resolution addHakmilikImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if(idHakmilik != null){
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByHakmilikDokumen(idHakmilik,dokumen.getIdDokumen());
        if(imejLaporan == null) {
        imejLaporan =  new ImejLaporan();
        imejLaporan.setCawangan(cawangan);
        imejLaporan.setDokumen(dokumen);
        imejLaporan.setHakmilik(hakmilik);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        imejLaporan.setInfoAudit(infoAudit);
        imejLaporan.setLaporanTanah(laporanTanah);
        laporanTanahService.simpanHakmilikImej(imejLaporan);
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }else{
            addSimpleMessage("Imej already exist for Hakmilik.");
        }

       getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

    public Resolution addLaporanImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah,dokumen,pandanganImej);
        if(imejLaporan == null) {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        imejLaporan =  new ImejLaporan();
        imejLaporan.setCawangan(cawangan);
        imejLaporan.setDokumen(dokumen);
        imejLaporan.setPandanganImej(pandanganImej);
        imejLaporan.setInfoAudit(infoAudit);
        imejLaporan.setLaporanTanah(laporanTanah);
        laporanTanahService.simpanHakmilikImej(imejLaporan);

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }else{
            addSimpleMessage("Imej already exist.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true");
    }

     public Resolution refreshpage() {
          rehydrate();
          return new RedirectResolution(LaporanTanah4ActionBean.class, "locate");
      }


    public Resolution simpan() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        System.out.println("hakmilik ==== " + hakmilik);

        if (hakmilik == null) {
            System.out.println("hak  milik null");
            addSimpleError("Maklumat hakmilik tidak dijumpai");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("pelupusan/carian_Hakmilik_Popup.jsp").addParameter("popup", "true");

        } else {
            InfoAudit ia = new InfoAudit();
            if(permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null){
                hakmilikPermohonan = plpservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            }
            else {
                 if(permohonan.getKodUrusan().getKod().equals("PHLP")||permohonan.getKodUrusan().getKod().equals("PTGSA")){
                     List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                     senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                     if(senaraiHakmilikTerlibat.size()>0)
                         hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                 }
                 else
                     hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);

             }
            if (hakmilikPermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = hakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            hakmilikPermohonan.setHakmilik(hakmilik);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya dijumpai.");
        }
         
         if(permohonan.getKodUrusan().getKod().equals("PHLP"))
            hakmilikPermohonanList = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
         else
             hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/carian_Hakmilik_Popup.jsp").addParameter("popup", "true");
    }

    public Resolution ulasanPPTKanan(){
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
       
        
        //PermohonanLaporanUlasan  permohonanLaporanUlasan = new PermohonanLaporanUlasan();
        //permohonanLaporanUlasanL = senarailaporanUlasan.get(0);
        //Long laporanUlasan1 = permohonanLaporanUlasan.getIdLaporUlas();
        permohonanLaporanUlasan = plpservice.findByIdMohonByJenisUlasan(idPermohonan, "UlsanPTKnn") ;
        if(permohonan != null){
            if(permohonanLaporanUlasan == null ){    
                     infoAudit.setDimasukOleh(pguna);
                     infoAudit.setTarikhMasuk(new java.util.Date());
                     permohonanLaporanUlasan = new PermohonanLaporanUlasan() ;
                    permohonanLaporanUlasan.setPermohonan(permohonan);
           //          permohonanLaporanUlasan.setIdLaporUlas(laporanUlasan1);
                     permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
                     permohonanLaporanUlasan.setInfoAudit(infoAudit);
                     permohonanLaporanUlasan.setJenisUlasan("UlsanPTKnn");
                     permohonanLaporanUlasan.setUlasan(ulasanKanan);
                     permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
                     
                     plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan); 
                    
                    LOG.info("--------permohonan Laporan Ulasan::------------::"+permohonanLaporanUlasan);
                      LOG.info("--------ID permohonan::------------::"+permohonan.getIdPermohonan());
                     addSimpleMessage("Ulasan Telah Disimpan");
             }
             else{
                    infoAudit = permohonanLaporanUlasan.getInfoAudit() ;
                    infoAudit.setDiKemaskiniOleh(pguna);
                     infoAudit.setTarikhKemaskini(new java.util.Date());
                    permohonanLaporanUlasan.setUlasan(ulasanKanan);
                     permohonanLaporanUlasan.setInfoAudit(infoAudit);
                     plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan); 
                     addSimpleMessage("Ulasan Telah Dikemaskini");
             }
        }
        rehydrate();
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true"); 
    }
    
    public Resolution kemaskiniUlasan(){
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        
        Long idUlasan = permohonanLaporanUlasan.getIdLaporUlas();
        if(permohonan != null){
            if(ulasanKanan != null ){    
                  
                    permohonanLaporanUlasan.setPermohonan(permohonan);
                     permohonanLaporanUlasan.setIdLaporUlas(idUlasan);
                     permohonanLaporanUlasan.setCawangan(permohonan.getCawangan());
                     permohonanLaporanUlasan.setInfoAudit(infoAudit);
                     permohonanLaporanUlasan.setJenisUlasan("UlsanPTKnn");
                     permohonanLaporanUlasan.setUlasan(ulasanKanan);
                     permohonanLaporanUlasan.setLaporanTanah(laporanTanah);
                     
                     plpservice.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan); 
                    
                    LOG.info("--------permohonan Laporan Ulasan::------------::"+permohonanLaporanUlasan);
                      LOG.info("--------ID permohonan::------------::"+permohonan.getIdPermohonan());
                     addSimpleMessage("Ulasan Telah Disimpan");
             }
             else{
             addSimpleMessage("Sila Masukkan Ulasan Penolong Pegawai Tanah (Kanan)");
             }
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah4.jsp").addParameter("tab", "true"); 
    }
    
    public void settingDefaultValue() {

        noktah = ".";
        noktahbertindih = ":";
        tajuk = " PERMOHONAN UNTUK MENDAPATKAN PERMIT ";
        tajuk2 = " SEBANYAK ";
        tajuk3 = " METER PADU DARIPADA ";
        tajuk4 = " DI ATAS LOT ";
        tajuk5 = " DAERAH " + permohonan.getCawangan().getDaerah().getNama() + " UNTUK TUJUAN ";
        if(permohonan.getKodUrusan().getKod().equals("PPBB")){
            // tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Yang Amat Berhormat Ketua Menteri, Melaka mengenai permohonan ";
            tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Mesyuarat Jawatankuasa Belah Bahagi Negeri Melaka mengenai permohonan ";
        }
        else if(permohonan.getKodUrusan().getKod().equals("PBPTD")){
             tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Pentadbir Tanah Daerah "+pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama())+" mengenai permohonan ";
        }
        else if(permohonan.getKodUrusan().getKod().equals("PBPTG")){
             tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Pengarah Tanah dan Galian, Melaka mengenai permohonan ";
        }
        else {
            tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri, Melaka mengenai permohonan ";
        }
        tujuan2 = " untuk mendapat permit mengeluarkan ";
        tujuan3 = " sebanyak ";
        tujuan4 = " meter padu ";
        tujuan5 = " daripada ";
        tujuan6 = " Mukim ";
        tujuan7 = " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " untuk tujuan ";

        perihalpermohonan = " Pentadbir Tanah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah menerima permohonan untuk mendapatkan permit mengeluarkan ";
        perihalpermohonan2 = " daripada ";
        perihalpermohonan3 = " pada ";

        perihalpemohon = "Pemohon ialah ";
        perihalpemohon2 = " no. syarikat ";
        perihalpemohon3 = " Alamat tempat ";
        perihalpemohon4 = " tinggal pemohon ialah di ";

        perihaltanah1 = " Bahan Batuan iaitu ";
        perihaltanah12 = " yang hendak dikeluarkan adalah di atas tanah ";
        perihaltanah13 = " Mukim ";
        perihaltanah14 = " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " seperti yang bertanda Merah di dalam pelan berkembar.";
        perihaltanah15 = " Keluasan kawasan yang dipohon adalah lebih kurang ";

        //                      " meter padu baki daripada longgokkan "+" yang dikorek oleh "+
        perihaltanah2 = " Pemilik tanah ini ialah ";
        perihaltanah21 = " sebelum ini.Mereka telahpun memberi keizinan untuk menggunakan ";
        perihaltanah22 = " tanah ini bagi tujuan mengambil ";
        perihaltanah23 = " surat kebenaran daripada pemilik tanah ada dikembarkan.";
        perihaltanah24 = " ini adalah tanah desa yang termasuk di dalam DUN :";
        perihaltanah25 = " Letaknya ";
        perihaltanah26 = " ini ialah di ";
        perihaltanah27 = " lebih kurang ";
        perihaltanah28 = " daripada ";
        perihaltanah29 = " lebih kurang ";
        perihaltanah210 = " kaki lebar seperti lakaran bertanda biru di ";
        perihaltanah211 = " dalam pelan berkembar untuk sampai ke tanah ini. ";


        perakuan = " Pentadbir Tanah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah meneliti permohonan ini memperakukan supaya permohonan untuk mendapatkan permit ";
        perakuan2 = " mengeluarkan tanah ";
        perakuan3 = " sebanyak ";
        perakuan4 = " meter padu daripada ";
        perakuan5 = " di atas tanah lot ";
        perakuan6 = " Mukim ";
        perakuan7 = " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) ;
        perakuan8 = " dengan syarat-syarat ";

        kuantiti = " Kuantiti ";
        kuantiti2 = " Meter padu. ";

        tempoh = " Tempoh ";

        kadarBayar = " KadarBayaran ";
        kadarBayar2 = " RM 2.00 semeter padu ";

        jumlahBayar = "Jumlah bayaran yang dikenakan";
        jumlahBayar2 = " RM 2.00 x ";

        jumlahBayar3 = " =RM ";

        wangCagar = " Wang Cagaran yang dikenakan ";
        wangCagar2 = " RM ";

        no6 = "Sekiranya terdapat sungai, parit atau anak air yang mengalir di kawasan tersebut, pemohon tidak dibenarkan untuk";
        no6a = " menutup melencung atau menimbusnya. ";

        no7 = " Pemohon hendaklah membina parit-parit keliling di kawasan tersebut bagi tujuan mengurangkan kesan hakisan air di ";
        no7a = " kawasan ini dari masuk dan merosakkan kawasan berdekatan. Sekiranya perlu perangkap lodak hendaklah dibina di kawasan tersebut. ";

        no8 = " Segala sampah sarap, pokok-pokok yang tidak digunakan hendaklah dibakar dan dihapuskan oleh pemohon tanpa membiarkan ";
        no8a = " masuk ke sungai-sungai, anak-anak air atau parit yang ada disekitar. ";

        no9 = " Pemohon tidak dibenarkan membiarkan lodak-lodak dari kawasan ini masuk ke dalam sungai, parit atau anak-anak air yang ";
        no9a = " ada di dalam kawasan atau sekitarnya ";

        no10 = " Syarat-syarat kelulusan seperti di lampiran A dan B berkembar. ";


    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getHakmilikImejLaporanList() {
        return hakmilikImejLaporanList;
    }

    public void setHakmilikImejLaporanList(List<ImejLaporan> hakmilikImejLaporanList) {
        this.hakmilikImejLaporanList = hakmilikImejLaporanList;
    }
    
    
    
    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public List<TanahRizabPermohonan> getTanahRizabPermohonanList() {
        return tanahRizabPermohonanList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabPermohonanList) {
        this.tanahRizabPermohonanList = tanahRizabPermohonanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public TanahRizabService getTanahRizabService() {
        return tanahRizabService;
    }

    public void setTanahRizabService(TanahRizabService tanahRizabService) {
        this.tanahRizabService = tanahRizabService;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getRizab1() {
        return rizab1;
    }

    public void setRizab1(String rizab1) {
        this.rizab1 = rizab1;
    }

    public Permohonan getPrmhnn() {
        return prmhnn;
    }

    public void setPrmhnn(Permohonan prmhnn) {
        this.prmhnn = prmhnn;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getMohonlaporulasan() {
        return mohonlaporulasan;
    }

    public void setMohonlaporulasan(String mohonlaporulasan) {
        this.mohonlaporulasan = mohonlaporulasan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan1() {
        return tanahrizabpermohonan1;
    }

    public void setTanahrizabpermohonan1(TanahRizabPermohonan tanahrizabpermohonan1) {
        this.tanahrizabpermohonan1 = tanahrizabpermohonan1;
    }

    public String getRizab2() {
        return rizab2;
    }

    public void setRizab2(String rizab2) {
        this.rizab2 = rizab2;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

//    public KodUOM getKodUOM() {
//        return kodUOM;
//    }
//
//    public void setKodUOM(KodUOM kodUOM) {
//        this.kodUOM = kodUOM;
//    }

//    public Long getKeadaanTanah() {
//        return keadaanTanah;
//    }
//
//    public void setKeadaanTanah(Long keadaanTanah) {
//        this.keadaanTanah = keadaanTanah;
//    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }


    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public char getKodP() {
        return kodP;
    }

    public void setKodP(char kodP) {
        this.kodP = kodP;
    }

    public KodPemilikan getKodPemilikan() {
        return kodPemilikan;
    }

    public void setKodPemilikan(KodPemilikan kodPemilikan) {
        this.kodPemilikan = kodPemilikan;
    }

    public String getTanahR() {
        return tanahR;
    }

    public void setTanahR(String tanahR) {
        this.tanahR = tanahR;
    }

    public String getKodT() {
        return kodT;
    }

    public void setKodT(String kodT) {
        this.kodT = kodT;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }

    public Long getIdlapor() {
        return idlapor;
    }

    public void setIdlapor(Long idlapor) {
        this.idlapor = idlapor;
    }

    public KodDUN getKodDUN() {
        return kodDUN;
    }

    public void setKodDUN(KodDUN kodDUN) {
        this.kodDUN = kodDUN;
    }

    public KodKeadaanTanah getKodKeadaanTanah() {
        return kodKeadaanTanah;
    }

    public void setKodKeadaanTanah(KodKeadaanTanah kodKeadaanTanah) {
        this.kodKeadaanTanah = kodKeadaanTanah;
    }

    public String getKodPar() {
        return kodPar;
    }

    public void setKodPar(String kodPar) {
        this.kodPar = kodPar;
    }

    public KodKawasanParlimen getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(KodKawasanParlimen kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public String getKodD() {
        return kodD;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
    }

    public String getKecerunanT() {
        return kecerunanT;
    }

    public void setKecerunanT(String kecerunanT) {
        this.kecerunanT = kecerunanT;
    }

    public KodStrukturTanah getKodStrukturTanah() {
        return kodStrukturTanah;
    }

    public void setKodStrukturTanah(KodStrukturTanah kodStrukturTanah) {
        this.kodStrukturTanah = kodStrukturTanah;
    }

    public String getKlasifikasiT() {
        return klasifikasiT;
    }

    public void setKlasifikasiT(String klasifikasiT) {
        this.klasifikasiT = klasifikasiT;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public KodItemPermit getKodItemPermit() {
        return kodItemPermit;
    }

    public void setKodItemPermit(KodItemPermit kodItemPermit) {
        this.kodItemPermit = kodItemPermit;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public BigDecimal getUsahaLuas() {
        return usahaLuas;
    }

    public void setUsahaLuas(BigDecimal usahaLuas) {
        this.usahaLuas = usahaLuas;
    }

    public Integer getUsahaBilanganPokok() {
        return usahaBilanganPokok;
    }

    public void setUsahaBilanganPokok(Integer usahaBilanganPokok) {
        this.usahaBilanganPokok = usahaBilanganPokok;
    }

    public BigDecimal getUsahaHarga() {
        return usahaHarga;
    }

    public void setUsahaHarga(BigDecimal usahaHarga) {
        this.usahaHarga = usahaHarga;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    //tambah banguanan
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPList() {
        return permohonanLaporanBangunanPList;
    }

    public void setPermohonanLaporanBangunanPList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList) {
        this.permohonanLaporanBangunanPList = permohonanLaporanBangunanPList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPUList() {
        return permohonanLaporanBangunanPUList;
    }

    public void setPermohonanLaporanBangunanPUList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList) {
        this.permohonanLaporanBangunanPUList = permohonanLaporanBangunanPUList;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public PermohonanLaporanUlasan getLaporanUlasan() {
        return laporanUlasan;
    }

    public void setLaporanUlasan(PermohonanLaporanUlasan laporanUlasan) {
        this.laporanUlasan = laporanUlasan;
    }

    public char getJenisKegunaan() {
        return jenisKegunaan;
    }

    public void setJenisKegunaan(char jenisKegunaan) {
        this.jenisKegunaan = jenisKegunaan;
    }

    public char getAdaJalanMasuk() {
        return adaJalanMasuk;
    }

    public void setAdaJalanMasuk(char adaJalanMasuk) {
        this.adaJalanMasuk = adaJalanMasuk;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getAdaPecahSempadan() {
        return adaPecahSempadan;
    }

    public void setAdaPecahSempadan(String adaPecahSempadan) {
        this.adaPecahSempadan = adaPecahSempadan;
    }

    public String getPlpulasan0() {
        return plpulasan0;
    }

    public void setPlpulasan0(String plpulasan0) {
        this.plpulasan0 = plpulasan0;
    }
    
    public String getCatatanLain1() {
        return catatanLain1;
    }

    public void setCatatanLain1(String catatanLain1) {
        this.catatanLain1 = catatanLain1;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public String getGsa() {
        return gsa;
    }

    public void setGsa(String gsa) {
        this.gsa = gsa;
    }

    public HakmilikUrusanService getHakmilikUrusanService() {
        return hakmilikUrusanService;
    }

    public void setHakmilikUrusanService(HakmilikUrusanService hakmilikUrusanService) {
        this.hakmilikUrusanService = hakmilikUrusanService;
    }

    public String getHutan() {
        return hutan;
    }

    public void setHutan(String hutan) {
        this.hutan = hutan;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public String getPbt1() {
        return pbt1;
    }

    public void setPbt1(String pbt1) {
        this.pbt1 = pbt1;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public String getRizab_melayu() {
        return rizab_melayu;
    }

    public void setRizab_melayu(String rizab_melayu) {
        this.rizab_melayu = rizab_melayu;
    }

    public String getKand() {
        return kand;
    }

    public void setKand(String kand) {
        this.kand = kand;
    }

    public PermohonanLaporanKandungan getPermohonanLaporanKandungan() {
        return permohonanLaporanKandungan;
    }

    public void setPermohonanLaporanKandungan(PermohonanLaporanKandungan permohonanLaporanKandungan) {
        this.permohonanLaporanKandungan = permohonanLaporanKandungan;
    }

    public String getKeadaankand() {
        return keadaankand;
    }

    public void setKeadaankand(String keadaankand) {
        this.keadaankand = keadaankand;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanLaporanUlasan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public String getUlsn() {
        return ulsn;
    }

    public void setUlsn(String ulsn) {
        this.ulsn = ulsn;
    }

    public NoPt getNoPT() {
        return noPT;
    }

    public void setNoPT(NoPt noPT) {
        this.noPT = noPT;
    }


    public String getKodUPlps() {
        return kodUPlps;
    }

    public void setKodUPlps(String kodUPlps) {
        this.kodUPlps = kodUPlps;
    }

    public String getKodHmlkTetap() {
        return kodHmlkTetap;
    }

    public void setKodHmlkTetap(String kodHmlkTetap) {
        this.kodHmlkTetap = kodHmlkTetap;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PermohonanLaporanUlasan> getSenarailaporanUlasan() {
        return senarailaporanUlasan;
    }

    public void setSenarailaporanUlasan(List<PermohonanLaporanUlasan> senarailaporanUlasan) {
        this.senarailaporanUlasan = senarailaporanUlasan;
    }

    public BigDecimal getLuasDilulus() {
        return luasDilulus;
    }

    public void setLuasDilulus(BigDecimal luasDilulus) {
        this.luasDilulus = luasDilulus;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public boolean isCheckTanahExist() {
        return checkTanahExist;
    }

    public void setCheckTanahExist(boolean checkTanahExist) {
        this.checkTanahExist = checkTanahExist;
    }

    public String getAddcatatan() {
        return addcatatan;
    }

    public void setAddcatatan(String addcatatan) {
        this.addcatatan = addcatatan;
    }

    public String getAddnoPelanWarta() {
        return addnoPelanWarta;
    }

    public void setAddnoPelanWarta(String addnoPelanWarta) {
        this.addnoPelanWarta = addnoPelanWarta;
    }

    public String getAddnoWarta() {
        return addnoWarta;
    }

    public void setAddnoWarta(String addnoWarta) {
        this.addnoWarta = addnoWarta;
    }

    public String getAddtarikhWarta() {
        return addtarikhWarta;
    }

    public void setAddtarikhWarta(String addtarikhWarta) {
        this.addtarikhWarta = addtarikhWarta;
    }

    public String getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(String kodRizab) {
        this.kodRizab = kodRizab;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public String getCatatanKeg() {
        return catatanKeg;
    }

    public void setCatatanKeg(String catatanKeg) {
        this.catatanKeg = catatanKeg;
    }

    public String getUlasanKanan() {
        return ulasanKanan;
    }

    public void setUlasanKanan(String ulasanKanan) {
        this.ulasanKanan = ulasanKanan;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public KodUOM getJumlahIsipaduUOM() {
        return jumlahIsipaduUOM;
    }

    public void setJumlahIsipaduUOM(KodUOM jumlahIsipaduUOM) {
        this.jumlahIsipaduUOM = jumlahIsipaduUOM;
    }

    public BigDecimal getJumlahIsipadu() {
        return jumlahIsipadu;
    }

    public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
        this.jumlahIsipadu = jumlahIsipadu;
    }

    public String getTempohSyorUOM() {
        return tempohSyorUOM;
    }

    public void setTempohSyorUOM(String tempohSyorUOM) {
        this.tempohSyorUOM = tempohSyorUOM;
    }

    public PermohonanPermitItem getPmi() {
        return pmi;
    }

    public void setPmi(PermohonanPermitItem pmi) {
        this.pmi = pmi;
    }

    public BigDecimal getCagarJalan() {
        return cagarJalan;
    }

    public void setCagarJalan(BigDecimal cagarJalan) {
        this.cagarJalan = cagarJalan;
    }

    public double getCagarKeneBayar() {
        return cagarKeneBayar;
    }

    public void setCagarKeneBayar(double cagarKeneBayar) {
        this.cagarKeneBayar = cagarKeneBayar;
    }

    public String getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(String jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getJumlahBayar2() {
        return jumlahBayar2;
    }

    public void setJumlahBayar2(String jumlahBayar2) {
        this.jumlahBayar2 = jumlahBayar2;
    }

    public String getJumlahBayar3() {
        return jumlahBayar3;
    }

    public void setJumlahBayar3(String jumlahBayar3) {
        this.jumlahBayar3 = jumlahBayar3;
    }

    public String getJumlahKeneBayar() {
        return jumlahKeneBayar;
    }

    public void setJumlahKeneBayar(String jumlahKeneBayar) {
        this.jumlahKeneBayar = jumlahKeneBayar;
    }

    public String getKadarBayar() {
        return kadarBayar;
    }

    public void setKadarBayar(String kadarBayar) {
        this.kadarBayar = kadarBayar;
    }

    public String getKadarBayar2() {
        return kadarBayar2;
    }

    public void setKadarBayar2(String kadarBayar2) {
        this.kadarBayar2 = kadarBayar2;
    }

    public String getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(String kuantiti) {
        this.kuantiti = kuantiti;
    }

    public String getKuantiti2() {
        return kuantiti2;
    }

    public void setKuantiti2(String kuantiti2) {
        this.kuantiti2 = kuantiti2;
    }

    public BigDecimal getKupon() {
        return kupon;
    }

    public void setKupon(BigDecimal kupon) {
        this.kupon = kupon;
    }

    public double getKuponAmaun() {
        return kuponAmaun;
    }

    public void setKuponAmaun(double kuponAmaun) {
        this.kuponAmaun = kuponAmaun;
    }

    public int getKuponQty() {
        return kuponQty;
    }

    public void setKuponQty(int kuponQty) {
        this.kuponQty = kuponQty;
    }

    public String getNo10() {
        return no10;
    }

    public void setNo10(String no10) {
        this.no10 = no10;
    }

    public String getNo6() {
        return no6;
    }

    public void setNo6(String no6) {
        this.no6 = no6;
    }

    public String getNo6a() {
        return no6a;
    }

    public void setNo6a(String no6a) {
        this.no6a = no6a;
    }

    public String getNo7() {
        return no7;
    }

    public void setNo7(String no7) {
        this.no7 = no7;
    }

    public String getNo7a() {
        return no7a;
    }

    public void setNo7a(String no7a) {
        this.no7a = no7a;
    }

    public String getNo8() {
        return no8;
    }

    public void setNo8(String no8) {
        this.no8 = no8;
    }

    public String getNo8a() {
        return no8a;
    }

    public void setNo8a(String no8a) {
        this.no8a = no8a;
    }

    public String getNo9() {
        return no9;
    }

    public void setNo9(String no9) {
        this.no9 = no9;
    }

    public String getNo9a() {
        return no9a;
    }

    public void setNo9a(String no9a) {
        this.no9a = no9a;
    }

    public String getNoktah() {
        return noktah;
    }

    public void setNoktah(String noktah) {
        this.noktah = noktah;
    }

    public String getNoktahbertindih() {
        return noktahbertindih;
    }

    public void setNoktahbertindih(String noktahbertindih) {
        this.noktahbertindih = noktahbertindih;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getPerakuan2() {
        return perakuan2;
    }

    public void setPerakuan2(String perakuan2) {
        this.perakuan2 = perakuan2;
    }

    public String getPerakuan3() {
        return perakuan3;
    }

    public void setPerakuan3(String perakuan3) {
        this.perakuan3 = perakuan3;
    }

    public String getPerakuan4() {
        return perakuan4;
    }

    public void setPerakuan4(String perakuan4) {
        this.perakuan4 = perakuan4;
    }

    public String getPerakuan5() {
        return perakuan5;
    }

    public void setPerakuan5(String perakuan5) {
        this.perakuan5 = perakuan5;
    }

    public String getPerakuan6() {
        return perakuan6;
    }

    public void setPerakuan6(String perakuan6) {
        this.perakuan6 = perakuan6;
    }

    public String getPerakuan7() {
        return perakuan7;
    }

    public void setPerakuan7(String perakuan7) {
        this.perakuan7 = perakuan7;
    }

    public String getPerakuan8() {
        return perakuan8;
    }

    public void setPerakuan8(String perakuan8) {
        this.perakuan8 = perakuan8;
    }

    public String getPerihalpemohon() {
        return perihalpemohon;
    }

    public void setPerihalpemohon(String perihalpemohon) {
        this.perihalpemohon = perihalpemohon;
    }

    public String getPerihalpemohon2() {
        return perihalpemohon2;
    }

    public void setPerihalpemohon2(String perihalpemohon2) {
        this.perihalpemohon2 = perihalpemohon2;
    }

    public String getPerihalpemohon3() {
        return perihalpemohon3;
    }

    public void setPerihalpemohon3(String perihalpemohon3) {
        this.perihalpemohon3 = perihalpemohon3;
    }

    public String getPerihalpemohon4() {
        return perihalpemohon4;
    }

    public void setPerihalpemohon4(String perihalpemohon4) {
        this.perihalpemohon4 = perihalpemohon4;
    }

    public String getPerihalpermohonan() {
        return perihalpermohonan;
    }

    public void setPerihalpermohonan(String perihalpermohonan) {
        this.perihalpermohonan = perihalpermohonan;
    }

    public String getPerihalpermohonan2() {
        return perihalpermohonan2;
    }

    public void setPerihalpermohonan2(String perihalpermohonan2) {
        this.perihalpermohonan2 = perihalpermohonan2;
    }

    public String getPerihalpermohonan3() {
        return perihalpermohonan3;
    }

    public void setPerihalpermohonan3(String perihalpermohonan3) {
        this.perihalpermohonan3 = perihalpermohonan3;
    }

    public String getPerihaltanah1() {
        return perihaltanah1;
    }

    public void setPerihaltanah1(String perihaltanah1) {
        this.perihaltanah1 = perihaltanah1;
    }

    public String getPerihaltanah12() {
        return perihaltanah12;
    }

    public void setPerihaltanah12(String perihaltanah12) {
        this.perihaltanah12 = perihaltanah12;
    }

    public String getPerihaltanah13() {
        return perihaltanah13;
    }

    public void setPerihaltanah13(String perihaltanah13) {
        this.perihaltanah13 = perihaltanah13;
    }

    public String getPerihaltanah14() {
        return perihaltanah14;
    }

    public void setPerihaltanah14(String perihaltanah14) {
        this.perihaltanah14 = perihaltanah14;
    }

    public String getPerihaltanah15() {
        return perihaltanah15;
    }

    public void setPerihaltanah15(String perihaltanah15) {
        this.perihaltanah15 = perihaltanah15;
    }

    public String getPerihaltanah2() {
        return perihaltanah2;
    }

    public void setPerihaltanah2(String perihaltanah2) {
        this.perihaltanah2 = perihaltanah2;
    }

    public String getPerihaltanah21() {
        return perihaltanah21;
    }

    public void setPerihaltanah21(String perihaltanah21) {
        this.perihaltanah21 = perihaltanah21;
    }

    public String getPerihaltanah210() {
        return perihaltanah210;
    }

    public void setPerihaltanah210(String perihaltanah210) {
        this.perihaltanah210 = perihaltanah210;
    }

    public String getPerihaltanah211() {
        return perihaltanah211;
    }

    public void setPerihaltanah211(String perihaltanah211) {
        this.perihaltanah211 = perihaltanah211;
    }

    public String getPerihaltanah22() {
        return perihaltanah22;
    }

    public void setPerihaltanah22(String perihaltanah22) {
        this.perihaltanah22 = perihaltanah22;
    }

    public String getPerihaltanah23() {
        return perihaltanah23;
    }

    public void setPerihaltanah23(String perihaltanah23) {
        this.perihaltanah23 = perihaltanah23;
    }

    public String getPerihaltanah24() {
        return perihaltanah24;
    }

    public void setPerihaltanah24(String perihaltanah24) {
        this.perihaltanah24 = perihaltanah24;
    }

    public String getPerihaltanah25() {
        return perihaltanah25;
    }

    public void setPerihaltanah25(String perihaltanah25) {
        this.perihaltanah25 = perihaltanah25;
    }

    public String getPerihaltanah26() {
        return perihaltanah26;
    }

    public void setPerihaltanah26(String perihaltanah26) {
        this.perihaltanah26 = perihaltanah26;
    }

    public String getPerihaltanah27() {
        return perihaltanah27;
    }

    public void setPerihaltanah27(String perihaltanah27) {
        this.perihaltanah27 = perihaltanah27;
    }

    public String getPerihaltanah28() {
        return perihaltanah28;
    }

    public void setPerihaltanah28(String perihaltanah28) {
        this.perihaltanah28 = perihaltanah28;
    }

    public String getPerihaltanah29() {
        return perihaltanah29;
    }

    public void setPerihaltanah29(String perihaltanah29) {
        this.perihaltanah29 = perihaltanah29;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTajuk2() {
        return tajuk2;
    }

    public void setTajuk2(String tajuk2) {
        this.tajuk2 = tajuk2;
    }

    public String getTajuk3() {
        return tajuk3;
    }

    public void setTajuk3(String tajuk3) {
        this.tajuk3 = tajuk3;
    }

    public String getTajuk4() {
        return tajuk4;
    }

    public void setTajuk4(String tajuk4) {
        this.tajuk4 = tajuk4;
    }

    public String getTajuk5() {
        return tajuk5;
    }

    public void setTajuk5(String tajuk5) {
        this.tajuk5 = tajuk5;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public BigDecimal getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(BigDecimal totalAll) {
        this.totalAll = totalAll;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTujuan2() {
        return tujuan2;
    }

    public void setTujuan2(String tujuan2) {
        this.tujuan2 = tujuan2;
    }

    public String getTujuan3() {
        return tujuan3;
    }

    public void setTujuan3(String tujuan3) {
        this.tujuan3 = tujuan3;
    }

    public String getTujuan4() {
        return tujuan4;
    }

    public void setTujuan4(String tujuan4) {
        this.tujuan4 = tujuan4;
    }

    public String getTujuan5() {
        return tujuan5;
    }

    public void setTujuan5(String tujuan5) {
        this.tujuan5 = tujuan5;
    }

    public String getTujuan6() {
        return tujuan6;
    }

    public void setTujuan6(String tujuan6) {
        this.tujuan6 = tujuan6;
    }

    public String getTujuan7() {
        return tujuan7;
    }

    public void setTujuan7(String tujuan7) {
        this.tujuan7 = tujuan7;
    }

    public String getWangCagar() {
        return wangCagar;
    }

    public void setWangCagar(String wangCagar) {
        this.wangCagar = wangCagar;
    }

    public String getWangCagar2() {
        return wangCagar2;
    }

    public void setWangCagar2(String wangCagar2) {
        this.wangCagar2 = wangCagar2;
    }

    public PermohonanBahanBatuan getSyaratBahanBatu() {
        return syaratBahanBatu;
    }

    public void setSyaratBahanBatu(PermohonanBahanBatuan syaratBahanBatu) {
        this.syaratBahanBatu = syaratBahanBatu;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public String getStatBdnPngwl() {
        return statBdnPngwl;
    }

    public void setStatBdnPngwl(String statBdnPngwl) {
        this.statBdnPngwl = statBdnPngwl;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getPasId() {
        return pasId;
    }

    public void setPasId(String pasId) {
        this.pasId = pasId;
    }

    public String getTestElement() {
        return testElement;
    }

    public void setTestElement(String testElement) {
        this.testElement = testElement;
    }
    
    

    
    
}