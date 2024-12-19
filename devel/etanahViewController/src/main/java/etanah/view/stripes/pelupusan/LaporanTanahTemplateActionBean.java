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
import etanah.dao.KodCawanganDAO;
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
import etanah.dao.LaporanTanahSempadanDAO;
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
import etanah.model.LaporanTanahSempadan;
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
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.TanahRizabService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
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

/**simpanLaporanTanah
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/laporan_tanahTemplate")
public class LaporanTanahTemplateActionBean extends AbleActionBean {

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
    KodCawanganDAO kodCawanganDAO;    
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
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    //carian hakmilik
    @Inject
    RegService regService;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PelupusanPtService pelPtService;
    List<HakmilikPermohonan> senaraiHakmilik;
    private String kodNegeri;
    private boolean checkTanahExist = false;
    private NoPt noPT = new NoPt();
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Hakmilik hakmilik;
    private KodTanah kodTanah;
    private KodHakmilik kodHakmilik;
    private KodPemilikan kodPemilikan;
    private KodUOM kodUOM;
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
    private static final Logger LOG = Logger.getLogger(LaporanTanahTemplateActionBean.class);
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
    private List listLaporTanahSpdnU;
    private List listLaporTanahSpdnS;
    private List listLaporTanahSpdnB;
    private List listLaporTanahSpdnT;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
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
     private String plpulasan1;
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
     private PermohonanBahanBatuan permohonanBahanBatuan ;
     private KodUOM jumlahIsipaduUOM ;
     private BigDecimal jumlahIsipadu ;
     private String tempohSyorUOM;
     private PermohonanPermit permohonanPermit;
     private String noLaporan = "";
     private LaporanTanahSempadan laporanTanahSempadan;
     private InfoAudit ia;
     private Pengguna peng;
     //private Hakmilik hakmilik;
     private String milik;
     private String hakmilik_ref;
     private String edit;
     private String idKandungan;
     private String statusSempadan;
     private String jenisSmpdn;
     private String idHakmilikSmpdn;
     private String kegunaanSmpdn;
     private String keadaanTanahSmpdn;
     private String catatanSmpdn;
     private String milikKerajaanSmpdn;
     
     


    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
       // getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {        
        LOG.info("View Laporan Tanah");
        display = Boolean.TRUE;
        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");

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
            hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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

        return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
    }
     public Resolution simpanBangunan() {
          LOG.info("------simpanBangunan()--------------::");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPUList = new ArrayList<PermohonanLaporanBangunan>();

        if (idPermohonan != null) {
            hakmilikPermohonan= plpservice.findByIdPermohonan(idPermohonan);

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

        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");

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
        return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
    }
   
        public Resolution uploadDoc() {
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
        return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
    }
    public Resolution uploadDocSmpdn() {
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
                imejLaporan.setLaporanTanah(laporanTanah);
                laporanTanahService.simpanHakmilikImej(imejLaporan);
                addSimpleMessage("Muat naik fail berjaya.");
            }

        }
       return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
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

    @Before(stages = {LifecycleStage.BindingAndValidation}, on={"!showFormPopUp", "!updateKandunganPopUp", "!showEditSempadan"})
    //@Before(stages = {LifecycleStage.BindingAndValidation})
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
        pbt="1";
        permohonanLaporanKawasan = plpservice.findByidMohonKodRizab(idPermohonan, pbt);
        LOG.info("--------permohonanLaporanKawasan-----------"+permohonanLaporanKawasan);
        if(permohonanLaporanKawasan!=null){
       rizab1 =   permohonanLaporanKawasan.getKodRizab().getNama();
        }

        if (idPermohonan != null) {
            
            int syx=0;
            permohonan = permohonanDAO.findById(idPermohonan);
            catatanKeg = permohonan.getSebab()!=null&&!("").equals(permohonan.getSebab())?permohonan.getSebab():new String();
            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan); 
             hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
             tanahRizabPermohonanList = permohonan.getSenaraiTanahRizab();
             if(hakmilikPermohonanList != null){
                hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan) ;
                 if(hakmilikPermohonan != null){
                if(hakmilikPermohonan.getBandarPekanMukimBaru()!=null){
                  syx =  hakmilikPermohonan.getBandarPekanMukimBaru().getKod();
                kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(syx) ;
            }
          }
         }
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

              laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
             checkTanahExist = laporanTanah!=null?true:false;
             //noLaporan = (String) getContext().getRequest().getParameter("noLaporan");
             //if( noLaporan == null || !noLaporan.equals("1")  ){
             if(laporanTanah!=null){

                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                
                /*
                 * Added For Dynamic Lot-Lot Sempadan
                 */
                listLaporTanahSpdnU = new ArrayList();
                listLaporTanahSpdnB = new ArrayList();
                listLaporTanahSpdnS = new ArrayList();
                listLaporTanahSpdnT = new ArrayList();
                
                List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();                
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnU.add(ls);
                }
                LOG.info("listLaporTanahSpdnU.size():"+listLaporTanahSpdnU.size());
                uSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnS.add(ls);
                }
                sSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnT.add(ls);
                }
                tSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();  
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnB.add(ls);
                }
                bSize = listLaporTanahSpdnTemp.size();
//                listLaporTanahSpdnB = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
//                listLaporTanahSpdnS = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
//                listLaporTanahSpdnT = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
                /*
                 * END OF CODE
                 */

            }else{
                 laporanTanah = new LaporanTanah();
             }
          /*}else if(noLaporan.equals("1")){
                 listLaporTanahSpdnU = new ArrayList();
                listLaporTanahSpdnB = new ArrayList();
                listLaporTanahSpdnS = new ArrayList();
                listLaporTanahSpdnT = new ArrayList();

                List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn_ref("U"); // UTARA
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnU.add(ls);
                }
                LOG.info("listLaporTanahSpdnU.size():"+listLaporTanahSpdnU.size());
                uSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn_ref("S"); // SELATAN
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnS.add(ls);
                }
                sSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn_ref("T"); // TIMUR
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnT.add(ls);
                }
                tSize = listLaporTanahSpdnTemp.size();
                listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
                listLaporTanahSpdnTemp = plpservice.findLaporTanahSmpdnByIdLaporNJSmpdn_ref("B"); // BARAT
                for(LaporanTanahSempadan lts: listLaporTanahSpdnTemp){
                    LotSempadan ls = new LotSempadan();
                    ls.setLaporanTanahSmpdn(lts);
                    listLaporTanahSpdnB.add(ls);
                }
                bSize = listLaporTanahSpdnTemp.size();
          }*/
            List<FasaPermohonan> listFasa;
//            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            //REMOVE 02SediaLaporan
//            stageId = "laporan_tanah";
            listFasa = permohonan.getSenaraiFasa();
              if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
                    if (fp.getIdAliran().equals("laporan_tanah")) {
                        fasaPermohonan = listFasa.get(i);
                        //ulasan = fasaPermohonan.getUlasan() ; DISABLED SINCE USING PTLPULASAN1
                        if(fasaPermohonan.getUlasan()!=null)
                            plpulasan1 = fasaPermohonan.getUlasan();
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
         LOG.info("--------tanahrizabpermohonan---------::"+tanahrizabpermohonan1);
        
        permohonanManualList = plpservice.findByIdMohonlist(idPermohonan);
        if(permohonanManualList!= null){
        LOG.info("--------permohonanManualList---------::"+permohonanManualList);
        }

        if(laporanTanah !=null){
        idlapor = laporanTanah.getIdLaporan();
        
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
          }
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
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
                kodU = hakmilikPermohonan.getKodUnitLuas().getKod();
            }
        if ((laporanTanah != null)&&(laporanTanah.getKodKeadaanTanah() !=null)) {
                keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
            }

        
         if((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium()!=null)){
             keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();
             
         }

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)){
                kodP = hakmilikPermohonan.getKodMilik().getKod();
            }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                kodD = hakmilikPermohonan.getKodDUN().getKod();
            }
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
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
          senaraiLaporanKandungan1 = new ArrayList<PermohonanLaporanUlasan>();
          senaraiLaporanKandungan1 = plpservice.findUlasan(idPermohonan, "Syarat4A");
          rowCount2 = senaraiLaporanKandungan1.size();

          if (!(senaraiLaporanKandungan1.isEmpty())){
              PermohonanLaporanUlasan  permohonanLaporanUlasan1 = new PermohonanLaporanUlasan();
              permohonanLaporanUlasan1 = senaraiLaporanKandungan1.get(0);
              LOG.info("-------permohonanLaporanUlasan1.getUlasan() ------::"+permohonanLaporanUlasan1.getUlasan());
              plpulasan1 = permohonanLaporanUlasan1.getUlasan();
     
          }
          
          
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
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");

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
//        if(laporanTanah!=null)
//        if(laporanTanah.getSempadanUtaraNoLot()==null){
//            flag = false;
//        }else if(laporanTanah.getSempadanUtaraKegunaan()==null){
//           flag = false;    
//        }else if(laporanTanah.getSempadanSelatanNoLot()==null){
//            flag=false;
//        }else if(laporanTanah.getSempadanSelatanKegunaan()==null){
//            flag=false;
//        }else if(laporanTanah.getSempadanTimurNoLot()==null){
//            flag=false;
//        }else if(laporanTanah.getSempadanTimurKegunaan()==null){
//            flag=false;
//        }else if(laporanTanah.getSempadanBaratNoLot()==null){
//            flag=false;
//        }else if(laporanTanah.getSempadanBaratKegunaan()==null){
//            flag=false;
//        }
        
        if(!flag)
            addSimpleError("Sila penuhi perihal lot-lot bersempadanan");
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
            fasaPermohonan.setUlasan(plpulasan1);
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
            permohonanLaporanUlasan.setUlasan(plpulasan1);
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
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan1);
            }

         //Saving in Hakmilik Permohonan
         LOG.info("-------Saving in HakmilikPermohonan-------::");
         hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
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


             if(permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("LPSP")){
               
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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
         LOG.info("-------validulasan-------::"+validulasan);
         if((validulasan == null) || (validulasan == "")){
             addSimpleError("Sila masukkan Ulasan.");
                return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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
            fasaPermohonan.setUlasan(plpulasan1);
            fasaPermohonan.setIdAliran(stageId);
            if(checkSaveOnly)
                laporanTanahService.simpanSaveOnlyFasaPermohonan(fasaPermohonan);
            else
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
            permohonanLaporanUlasan.setUlasan(plpulasan1);
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
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan1);
            }

         //Saving in Hakmilik Permohonan
         LOG.info("-------Saving in HakmilikPermohonan-------::");
         hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
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

              if(permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PRMP")){
               
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
             else if(permohonan.getKodUrusan().getKod().equals("PRMP")) 
            permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB7").getNama());
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
        LOG.info("ulasan PTKANAN"+ulasanKanan); 
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }

   public Resolution deleteSingle() {
        String idLaporUlas = getContext().getRequest().getParameter("idLaporUlas");
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
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
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
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

       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }

     public Resolution refreshpage() {
          rehydrate();
          return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
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
            hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
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
         hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/carian_Hakmilik_Popup.jsp").addParameter("popup", "true");
    }

    public Resolution ulasanPPTKanan(){
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        
        PermohonanLaporanUlasan  permohonanLaporanUlasan = new PermohonanLaporanUlasan();
        //permohonanLaporanUlasanL = senarailaporanUlasan.get(0);
        Long laporanUlasan1 = permohonanLaporanUlasan.getIdLaporUlas();
        
        if(permohonan != null){
            if(ulasanKanan != null ){    
                  
                    permohonanLaporanUlasan.setPermohonan(permohonan);
                     permohonanLaporanUlasan.setIdLaporUlas(laporanUlasan1);
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
        rehydrate();
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true"); 
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
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true"); 
    }
    
    public Resolution tambahRow() {

        LaporanTanahSempadan lts = new LaporanTanahSempadan();
        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        if(!StringUtils.isBlank(typeIndex));
         numType = typeIndex.equals("U")?1:typeIndex.equals("B")?2:typeIndex.equals("T")?3:typeIndex.equals("S")?4:0;
         LotSempadan ls = new LotSempadan();
         if(listLaporTanahSpdnU ==null)
             listLaporTanahSpdnU = new ArrayList();
         if(listLaporTanahSpdnB ==null)
             listLaporTanahSpdnB = new ArrayList();
         if(listLaporTanahSpdnT ==null)
             listLaporTanahSpdnT = new ArrayList();
         if(listLaporTanahSpdnS==null)
             listLaporTanahSpdnS = new ArrayList();
        switch (numType) {
            case 1:                    
                    listLaporTanahSpdnU.add(ls);
                break;
            case 2:                   
                    listLaporTanahSpdnB.add(ls);
                break;
            case 3:
                    listLaporTanahSpdnT.add(ls);
                break;
            case 4:
                    listLaporTanahSpdnS.add(ls);
                break;
            default:
        }
        getContext().getRequest().setAttribute("display", display);
        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }
    public Resolution simpanKandungan() throws ParseException {
        
        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        String idHakmilikSempadan = getContext().getRequest().getParameter("idHakmilik");
        String kegunaanSempadan = getContext().getRequest().getParameter("kegunaan");
        String keadaanTanahSempadan = getContext().getRequest().getParameter("keadaanTanah");
        String catatanSempadan = getContext().getRequest().getParameter("catatan");
        String milikKerajaanSempadan = getContext().getRequest().getParameter("milikKerajaan");
        if(!StringUtils.isBlank(typeIndex));
         numType = typeIndex.equals("U")?1:typeIndex.equals("B")?2:typeIndex.equals("T")?3:typeIndex.equals("S")?4:0;
         
         String[] data = {  StringUtils.isNotEmpty(idHakmilikSempadan)?idHakmilikSempadan:new String(),
                            StringUtils.isNotEmpty(kegunaanSempadan)?kegunaanSempadan:new String(),
                            StringUtils.isNotEmpty(keadaanTanahSempadan)?keadaanTanahSempadan:new String(),
                            StringUtils.isNotEmpty(catatanSempadan)?catatanSempadan:new String(),
                            StringUtils.isNotEmpty(milikKerajaanSempadan)?milikKerajaanSempadan:new String()};
        /*
         * LEGEND 
         * 1 - UTARA
         * 2 - BARAT
         * 3 - TIMUR
         * 4 - SELATAN
         * 0 - NULL
         */
        
        switch (numType) {
            case 1:
                updateKandungan("U", data);
                break;
            case 2: 
                updateKandungan("B", data);
                break;
            case 3:
                updateKandungan("T", data);               
                break;
            case 4:
                updateKandungan("S", data);
                break;
        }
        rehydrate();
        getContext().getRequest().setAttribute("display", display);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }
    public Resolution simpanKandunganSempadan() throws ParseException {
        
        int numType = 0;
        String typeIndex = getContext().getRequest().getParameter("index");
        String idHakmilikSempadan = new String();
        if(StringUtils.isNotBlank(statusSempadan)&&statusSempadan.equals("U"))
            idHakmilikSempadan = idHakmilikSmpdn;
        else
            idHakmilikSempadan = getContext().getRequest().getParameter("idHakmilik");
        String kegunaanSempadan = getContext().getRequest().getParameter("kegunaan");
        String keadaanTanahSempadan = getContext().getRequest().getParameter("keadaanTanah");
        String catatanSempadan = getContext().getRequest().getParameter("catatan");
        String milikKerajaanSempadan = getContext().getRequest().getParameter("milikKerajaan");
        if(!StringUtils.isBlank(typeIndex));
         numType = typeIndex.equals("U")?1:typeIndex.equals("B")?2:typeIndex.equals("T")?3:typeIndex.equals("S")?4:0;
         
         String[] data = {idHakmilikSempadan,kegunaanSempadan,keadaanTanahSempadan,catatanSempadan,milikKerajaanSempadan};
        /*
         * LEGEND 
         * 1 - UTARA
         * 2 - BARAT
         * 3 - TIMUR
         * 4 - SELATAN
         * 0 - NULL
         */
        
        switch (numType) {
            case 1:
                updateKandungan("U", data);
                break;
            case 2: 
                updateKandungan("B", data);
                break;
            case 3:
                updateKandungan("T", data);               
                break;
            case 4:
                updateKandungan("S", data);
                break;
        }
                rehydrate();
//        getContext().getRequest().setAttribute("display", display);
//        addSimpleMessage("Maklumat Berjaya Disimpan");
//        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
        return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");
    }
    public Resolution updateKandunganSempadan(){


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
           if(!StringUtils.isBlank(idHakmilik))
             hakmilik = hakmilikDAO.findById(idHakmilik);
           
            LaporanTanah lt = new LaporanTanah();
            lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            InfoAudit ia = new InfoAudit();
            if(lt==null){
                lt = new LaporanTanah();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                plpservice.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            if(lt!=null){
                if(hakmilik!=null){
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();

                    ia = new InfoAudit();
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                        lts = laporanTanahSempadanDAO.findById(laporanTanahSempadan.getIdLaporTanahSpdn());
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setTarikhMasuk(lts.getInfoAudit().getTarikhMasuk());
                        ia.setDimasukOleh(lts.getInfoAudit().getDimasukOleh());

                    }else{
                        ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    lts.setInfoAudit(ia);
                    lts.setLaporanTanah(lt);
                    lts.setHakmilik(hakmilik);
                    lts.setGuna("kegunaan tanah");
                    lts.setJenisSempadan(laporanTanahSempadan.getJenisSempadan());
                    lts.setKeadaanTanah(laporanTanahSempadan.getKeadaanTanah());
                    lts.setCatatan(laporanTanahSempadan.getCatatan());
                    lts.setMilikKerajaan(laporanTanahSempadan.getMilikKerajaan());
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                    //lts.setIdLaporTanahSpdn(laporanTanahSempadan.getIdLaporTanahSpdn());
                    edit = "3";
                    }else{
                        edit = "1";
                    }
                    plpservice.simpanLaporanTanahSempadan(lts);
                    
                    laporanTanahSempadan = new LaporanTanahSempadan();
                }else{
                addSimpleError("Hakmilik No. Ini adalah tidak sah");
                edit = "2";
               }

            }

                 return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");

 
    }
    public void updateKandungan(String jnsSmpdn, String[] data) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
       
        Hakmilik hakmilikSmpdn = new Hakmilik();
        if(data.length>0){
            hakmilikSmpdn = hakmilikDAO.findById(data[0]);
            LaporanTanah lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            InfoAudit ia = new InfoAudit();
            if(lt==null){
                lt = new LaporanTanah();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                plpservice.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
            
            if(lt!=null){
                if(hakmilikSmpdn!=null){
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();
                    if(StringUtils.isNotBlank(statusSempadan)&&(statusSempadan.equals("U"))){
                        lts = plpservice.findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                        if(lts!=null){
                             ia = new InfoAudit();
                             ia = lts.getInfoAudit();
                             ia.setDiKemaskiniOleh(pengguna);
                             ia.setTarikhKemaskini(new java.util.Date());
                             lts.setInfoAudit(ia);
                             lts.setLaporanTanah(lt);
                             lts.setHakmilik(hakmilikSmpdn);
                             lts.setGuna(data[1]);
                             lts.setKeadaanTanah(data[2]);
                             lts.setCatatan(data[3]);
                             lts.setMilikKerajaan(data[4]);
                             lts.setJenisSempadan(jnsSmpdn);
                             plpservice.simpanLaporanTanahSempadan(lts);
                             addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");
                        }
                    }else{
                        List<LaporanTanahSempadan> listLTS = plpservice.findListLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                        if(listLTS.size()>0){
                            addSimpleError("ID HAKMILIK TELAH DIGUNAKAN, SILA KEMASKINI HAKMILIK UNTUK PENGUBAHSUAIAN LOT BERSEMPADANAN");
                        }else{                        
                            lts = new LaporanTanahSempadan();
                            ia = new InfoAudit();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            lts.setInfoAudit(ia);
                            lts.setLaporanTanah(lt);
                            lts.setHakmilik(hakmilikSmpdn);
                            lts.setGuna(data[1]);
                            lts.setKeadaanTanah(data[2]);
                            lts.setCatatan(data[3]);
                            lts.setMilikKerajaan(data[4]);
                            lts.setJenisSempadan(jnsSmpdn);
                            plpservice.simpanLaporanTanahSempadan(lts);
                            addSimpleMessage("MAKLUMAT BERJAYA DISIMPAN");                        
                        }
                    }
                    
//                    lts = plpservice.findLaporTanahSmpdnByIdLaporNIdHakmilik(lt.getIdLaporan(),data[0]);
                    
                    
                }else{
                    addSimpleError("ID HAKMILIK TIDAK DIJUMPAI");
                }
            }            
        }
    }
    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        if (idKand != null) {
            LaporanTanahSempadan plk = new LaporanTanahSempadan();
            plk = laporanTanahSempadanDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteLaporanTanahSempadan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("display", display);
        return new JSP("pelupusan/laporan_tanahTemplate.jsp").addParameter("tab", "true");
    }

    // added by Srinvas
    public Resolution sempadanSimpanan(){
            //System.out.println("laporanTanahSempadan.getHakmilik()---"+getContext().getRequest().getParameter("hakmilik"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        laporanTanah = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
        ia = new InfoAudit();
            LaporanTanahSempadan laporanTanahSempadanRef = new LaporanTanahSempadan();
            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            laporanTanahSempadanRef.setInfoAudit(ia);
            LaporanTanah lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);

            if(lt==null){

                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                plpservice.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);

            milik = getHakmilik_ref();
            if(milik == null){milik = "";}
            hakmilik = hakmilikDAO.findById(milik);
            if(hakmilik != null){
               laporanTanahSempadanRef.setHakmilik(hakmilik);
               laporanTanahSempadanRef.setJenisSempadan(laporanTanahSempadan.getJenisSempadan());
               laporanTanahSempadanRef.setKeadaanTanah(laporanTanahSempadan.getKeadaanTanah());
               laporanTanahSempadanRef.setCatatan(laporanTanahSempadan.getCatatan());
               laporanTanahSempadanRef.setMilikKerajaan(laporanTanahSempadan.getMilikKerajaan());
               laporanTanahSempadanRef.setLaporanTanah(lt);
               laporanTanahSempadanRef.setGuna("kegunaan tanah");
               plpservice.saveOrUpdateSempadan(laporanTanahSempadanRef);
               edit = "1";
               laporanTanahSempadan = new LaporanTanahSempadan();
            }else{
                addSimpleError("Hakmilik No. Ini adalah tidak sah");
                edit = "2";
            }


            return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");
    }

    // added by Srinvas
    public Resolution updateKandunganPopUp(){

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
           //milik = getHakmilik_ref();
             milik = (String) getContext().getRequest().getParameter("hakmilik_ref");
           if(milik == null){milik = "";}
           hakmilik = hakmilikDAO.findById(laporanTanahSempadan.getHakmilik().getIdHakmilik());
            LaporanTanah lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);
              if(lt==null){ 
                lt = new LaporanTanah();
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);    
                ia.setTarikhMasuk(new java.util.Date());
                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                plpservice.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = plpservice.findLaporanTanahByIdPermohonan(idPermohonan);

            if(lt!=null){
                if(hakmilik!=null){
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();

                    ia = new InfoAudit();
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                        lts = laporanTanahSempadanDAO.findById(laporanTanahSempadan.getIdLaporTanahSpdn());
                        ia = lts.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());

                    }else{
                        ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    lts.setInfoAudit(ia);
                    lts.setLaporanTanah(lt);
                    lts.setHakmilik(hakmilik);
                    lts.setGuna("kegunaan tanah");
                    lts.setJenisSempadan(laporanTanahSempadan.getJenisSempadan());
                    lts.setKeadaanTanah(laporanTanahSempadan.getKeadaanTanah());
                    lts.setCatatan(laporanTanahSempadan.getCatatan());
                    lts.setMilikKerajaan(laporanTanahSempadan.getMilikKerajaan());
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                    //lts.setIdLaporTanahSpdn(laporanTanahSempadan.getIdLaporTanahSpdn());
                    edit = "3";
                    }else{
                        edit = "1";
                    }
                    plpservice.simpanLaporanTanahSempadan(lts);

                    laporanTanahSempadan = new LaporanTanahSempadan();
                }else{
                addSimpleError("Hakmilik No. Ini adalah tidak sah");
                edit = "2";
               }

            }
   
                 return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("popup", "true");


    }

    public Resolution showFormPopUp() {
       if(getIdKandungan() != null){
           laporanTanahSempadan = laporanTanahSempadanDAO.findById(new Long(getIdKandungan()));
           hakmilik_ref = laporanTanahSempadan.getHakmilik().getIdHakmilik();
           if(laporanTanahSempadan!=null){
                jenisSmpdn = laporanTanahSempadan.getJenisSempadan();
                idHakmilikSmpdn = laporanTanahSempadan.getHakmilik().getIdHakmilik();
                kegunaanSmpdn = laporanTanahSempadan.getGuna();
                keadaanTanah = laporanTanahSempadan.getKeadaanTanah();
                catatan = laporanTanahSempadan.getCatatan();
                milikKerajaanSmpdn = laporanTanahSempadan.getMilikKerajaan();
                statusSempadan = "U"; // U for update   
           }
       }

        return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("popup", "true");
    }

     // click on edit button
    // added by Srinvas
    public Resolution showEditSempadan() {
        laporanTanahSempadan = new LaporanTanahSempadan();
        hakmilik_ref = "";
        setHakmilik_ref("");
        return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("popup", "true");
    }
    // added by Srinvas
   public Resolution closeWindow() {
       return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
       //return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate").addParameter("noLaporan", "1");
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

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

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

    public String getPlpulasan1() {
        return plpulasan1;
    }

    public void setPlpulasan1(String plpulasan1) {
        this.plpulasan1 = plpulasan1;
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

    public List getListLaporTanahSpdnB() {
        return listLaporTanahSpdnB;
    }

    public void setListLaporTanahSpdnB(List listLaporTanahSpdnB) {
        this.listLaporTanahSpdnB = listLaporTanahSpdnB;
    }

    public List getListLaporTanahSpdnS() {
        return listLaporTanahSpdnS;
    }

    public void setListLaporTanahSpdnS(List listLaporTanahSpdnS) {
        this.listLaporTanahSpdnS = listLaporTanahSpdnS;
    }

    public List getListLaporTanahSpdnT() {
        return listLaporTanahSpdnT;
    }

    public void setListLaporTanahSpdnT(List listLaporTanahSpdnT) {
        this.listLaporTanahSpdnT = listLaporTanahSpdnT;
    }

    public List getListLaporTanahSpdnU() {
        return listLaporTanahSpdnU;
    }

    public void setListLaporTanahSpdnU(List listLaporTanahSpdnU) {
        this.listLaporTanahSpdnU = listLaporTanahSpdnU;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public int getbSize() {
        return bSize;
    }

    public void setbSize(int bSize) {
        this.bSize = bSize;
    }

    public int getsSize() {
        return sSize;
    }

    public void setsSize(int sSize) {
        this.sSize = sSize;
    }

    public int gettSize() {
        return tSize;
    }

    public void settSize(int tSize) {
        this.tSize = tSize;
    }

    public int getuSize() {
        return uSize;
    }

    public void setuSize(int uSize) {
        this.uSize = uSize;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getHakmilik_ref() {
        return hakmilik_ref;
    }

    public void setHakmilik_ref(String hakmilik_ref) {
        this.hakmilik_ref = hakmilik_ref;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }




    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public String getStatusSempadan() {
        return statusSempadan;
    }

    public void setStatusSempadan(String statusSempadan) {
        this.statusSempadan = statusSempadan;
    }

    public String getCatatanSmpdn() {
        return catatanSmpdn;
    }

    public void setCatatanSmpdn(String catatanSmpdn) {
        this.catatanSmpdn = catatanSmpdn;
    }

    public String getIdHakmilikSmpdn() {
        return idHakmilikSmpdn;
    }

    public void setIdHakmilikSmpdn(String idHakmilikSmpdn) {
        this.idHakmilikSmpdn = idHakmilikSmpdn;
    }

    public String getJenisSmpdn() {
        return jenisSmpdn;
    }

    public void setJenisSmpdn(String jenisSmpdn) {
        this.jenisSmpdn = jenisSmpdn;
    }

    public String getKeadaanTanahSmpdn() {
        return keadaanTanahSmpdn;
    }

    public void setKeadaanTanahSmpdn(String keadaanTanahSmpdn) {
        this.keadaanTanahSmpdn = keadaanTanahSmpdn;
    }

    public String getKegunaanSmpdn() {
        return kegunaanSmpdn;
    }

    public void setKegunaanSmpdn(String kegunaanSmpdn) {
        this.kegunaanSmpdn = kegunaanSmpdn;
    }

    public String getMilikKerajaanSmpdn() {
        return milikKerajaanSmpdn;
    }

    public void setMilikKerajaanSmpdn(String milikKerajaanSmpdn) {
        this.milikKerajaanSmpdn = milikKerajaanSmpdn;
    }

    




}