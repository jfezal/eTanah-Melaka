               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.BPelService;

import etanah.service.ConsentPtdService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/draf_mmk_GSA")
public class DrafMMKNGSAActionBean extends AbleActionBean {

 Logger LOG = Logger.getLogger(DrafMMKNGSAActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
//    @Inject
//    RegService regService;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PelupusanService pelupusanService;
     @Inject
    PelupusanUtiliti pelupusanUtiliti;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
//    @Inject
//    BPelService service;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
     @Inject
     KodDokumenDAO kodDokumenDAO;
     @Inject
     PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    String latarBelakang;
    String ulasanJabatanKebajikan;
    String ulasanJabatanTenagaKerja;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String display;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPermitItem permitItem ;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pihak pihak;
    private String alamatPenuhPihak;
    private String alamatPenuhPhbgn;
    private Pengguna peng;
    private String tujuan;
    private String latarBelakang1 ;
    private LaporanTanah laporanTanah;
    private PermohonanLaporanPelan  permohonanLaporPelan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private PermohonanKertasKandungan kertasK;
    private String sekatKpntgn2;
    private String stageId;
    private String syaratNyata2;
    private String kodSktn;
    private String kod;
    private String index;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPTD;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG2;
    private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List senaraiLaporanKandunganUtil;
    private KodDokumen kd;
    private String kodHmlk;
    private String kodHmlk1;
    private String jenishakmilik;
    private String kodKategori;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonanJKTD;
    private PermohonanKertas permohonanKertas;
    private KodKeputusan kodKeputusan;
    private String kodKepu;
    private String tajuk1;
    private String taskId;
    private String syor;
    private String syaratNyata;
    private String syaratNyata1;
    private String convNama;
    private String convTempat;
    private PermohonanKertasKandungan kertasKandungan;
    private PemohonHubungan pemohonHubungan;
    private String huraianPentadbir;
    private Boolean edit;
    private boolean editPTD;
    private boolean editPTG;
    private boolean openPTG;
    private boolean openPTD;
    private String syortolaklulus;
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.TRUE;
        editPTG = Boolean.FALSE;
         if(permohonanKertas!=null){
             LOG.info("-------showForm--------senaraiLaporanKandungan1------------------------------"+senaraiLaporanKandungan1);
       senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),4);
       if(senaraiLaporanKandungan1.isEmpty())
           senaraiLaporanKandungan1 = new ArrayList <PermohonanKertasKandungan>();

       }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Resolution showFormPTG() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.TRUE;
         if(permohonanKertas!=null){
             LOG.info("-------showForm--------senaraiLaporanKandungan1------------------------------"+senaraiLaporanKandungan1);
       senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),4);
       if(senaraiLaporanKandungan1.isEmpty())
           senaraiLaporanKandungan1 = new ArrayList <PermohonanKertasKandungan>();

       }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Resolution viewFormForPTD() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Resolution viewFormForPTG() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyata.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyataKpntngn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = pelupusanService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pelupusanService.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//         stageId(taskId);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata("%", kc, syaratNyata2);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
    }
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if(pemohon!=null){
            pihak = pemohon.getPihak();
            if(pihak!=null){
                if(pihak.getAlamat1()!=null&&!("").equals(pihak.getAlamat1())){
                alamatPenuhPihak = pihak.getAlamat1();
                }if(pihak.getAlamat2()!=null&&!("").equals(pihak.getAlamat2())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat2();
                }if(pihak.getAlamat3()!=null&&!("").equals(pihak.getAlamat3())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat3();
                }if(pihak.getAlamat4()!=null&&!("").equals(pihak.getAlamat4())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat4();
                }
            }            
        }
        permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
        permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
//        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
//REMOVE
        stageId = "06SediaDrafMMK"; // ***MUST GET SINCE TO GET KODKEPUTUSAN MMKN
        if(permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "06SediaDrafMMK");
        }else{
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "stageId");
//          fasaPermohonanJKTD = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "06SediaDrafMMK"); //CHANGE IF HAVE JKTD
        }
            
        if(syortolaklulus==null)
            if(fasaPermohonan!=null&&fasaPermohonan.getKeputusan()!=null)
                syortolaklulus = fasaPermohonan.getKeputusan().getKod();
//        else
//                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_rencana_ptg");
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        stageId = stageId(taskId);
//        fasaPermohonan= pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan,stageId);
        pemohonHubungan = pelupusanService.findHubunganByIDSuamiIsteri(pemohon.getIdPemohon());
        if(pemohonHubungan!=null){
            if(pemohonHubungan.getAlamat1()!=null&&!("").equals(pemohonHubungan.getAlamat1()))
                alamatPenuhPhbgn = pemohonHubungan.getAlamat1();
            if(pemohonHubungan.getAlamat2()!=null&&!("").equals(pemohonHubungan.getAlamat2()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat2();
            if(pemohonHubungan.getAlamat3()!=null&&!("").equals(pemohonHubungan.getAlamat3()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat3();
            if(pemohonHubungan.getAlamat4()!=null&&!("").equals(pemohonHubungan.getAlamat4()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat4();
        }
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
        LOG.info("-------------fasaPermohonan---------------------"+fasaPermohonan);
        if(fasaPermohonan!=null&&fasaPermohonan.getKeputusan()!=null){
            fasaPermohonan.getKeputusan().getNama();
        }
        if(hakmilikPermohonan!=null){
         System.out.println("idPermohonan : " + idPermohonan);
        if(hakmilikPermohonan.getSyaratNyataBaru() != null){
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod() ;
            syaratNyata = hakmilikPermohonan.getSyaratNyataBaru().getSyarat();
        }
         if(hakmilikPermohonan.getSekatanKepentinganBaru() != null){
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod() ;
            syaratNyata1 = hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan();
        }
        if(hakmilikPermohonan.getKategoriTanahBaru() != null){
            kodKategori = hakmilikPermohonan.getKategoriTanahBaru().getKod() ;
        }
          if (hakmilikPermohonan.getKodHakmilikSementara() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
            }
         if (hakmilikPermohonan.getKodHakmilikTetap() != null) {
                kodHmlk1 = hakmilikPermohonan.getKodHakmilikTetap().getKod();
            }
        }
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<LaporanTanah> listLaporanTanah;
        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

        if (!(listLaporanTanah.isEmpty())) {
            laporanTanah = listLaporanTanah.get(0);
        }

        String ktanah = "";
        String sbb = "";
        String namaP = "";
        String kodUnitLuas = "";
        String lokasi = "";
        String ursan = "";
        String bpm = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String jarak ="";
        String unitJarak="";
        String jarakDari ="";
        String  kws ="";
        String ursan1="";


        if (permohonan.getSebab() != null) {
            sbb = permohonan.getSebab();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan = " Untuk memiliki tanah Kerajaan secara hakmilik tetap.";
        }
         if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan1 = " MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965.";
        }
         if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
            ursan1 = " MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965.";
        }
        if (pemohon != null) {
            if(pemohon.getPihak()!=null)
            namaP = pemohon.getPihak().getNama();
        }
        if(permohonanLaporPelan!=null){
        if(permohonanLaporPelan.getKawasanPihakBerkuasa()!=null){
            kws = permohonanLaporPelan.getKawasanPihakBerkuasa();
        }
        }
         if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                kodUnitLuas = hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getLokasi() != null) {
                lokasi = hakmilikPermohonan.getLokasi();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
            if(hakmilikPermohonan.getJarak()!=null){                    
                jarak =hakmilikPermohonan.getJarak();
            }
            if(hakmilikPermohonan.getUnitJarak()!=null){
                unitJarak = hakmilikPermohonan.getUnitJarak().getNama();
            }
            if(hakmilikPermohonan.getJarakDari()!=null){
                jarakDari = hakmilikPermohonan.getJarakDari();
            }
        }

              tajuk1 =  "\n" +"PERMOHONAN DARIPADA " + namaP+ " UNTUK " +ursan1+ "\n"
                        + "  " +" SELUAS LEBIH KURANG " +luas+ " " +kodUnitLuas + "\n"
                        + "  " +" DI " +lokasi+ ", "+bpm+", "+" DAERAH "+daerah+ "\n"
                        + "  " +" UNTUK TUJUAN "+ktanah+" "+sbb+".";



               tujuan =  "\n" +"Tujuan kertas ini disediakan i = alah untuk  mendapatkan pertimbangan Jawatankuasa Tanah Daerah Seremban bagi permohonan daripada :- " + "\n"
               + " " + " i)"+ namaP+"."+ "\n"
               + " " + " ii)"+ ursan+ "\n"
               + " " + " iii)"+" Seluas lebihkurang" +luas+ " " +kodUnitLuas +"."+ "\n"
               + " " + " iv)"+"Di" +lokasi+ ","+"Mukim"+bpm+","+"Daerah"+daerah+"."+ "\n"
               + " " + " v)"+"Untuk kegunaan"+ktanah+" "+sbb+".";


               latarBelakang1 = "\n" +"Tanah yang dimaksudkan itu seperti bertanda merah di dalam pelan letaknya :-"+ "\n"
               + " " + " i) Mukim :"+ bpm+"."+ "\n"
               + " " + " ii)"+ lokasi+"."+ "\n"
               + " " + " iii)"+"Lebihkurang" +jarak+ " " +unitJarak +"dari"+" "+jarakDari+ "."+"\n"
               + " " + " iv)"+"Jenis Tanah Desa"+"."+"\n"
               + " " + " v)"+kws+"Kawasan Rizab Melayu"+"."+"\n"
               + " " + " vi)"+kws+"Kawasan GSA"+"."+"\n"
               + " " + " vii)"+kws+"Kawasan GSA"+".";

        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");

        LOG.info("---------rehydrate-----permohonankertas--------------::" + permohonanKertas);
//        if (permohonanKertas != null) {
//            if(permohonanKertas.getSenaraiKandungan()!=null){
//                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
//                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
//
//                    if (kertasKandungan.getBil() == 0) {
//                        tajuk1 = kertasKandungan.getKandungan();
//                    }else  if (kertasKandungan.getBil() == 1) {
//                        tujuan = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("2.1")) {
//                        latarBelakang1 = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("4.1")) {
//                        syor = kertasKandungan.getKandungan();
//                    }
//
//                }
//            }            
//        }
        if (permohonanKertas != null) {
            senaraiLaporanKandunganUtil = new Vector();
            //senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiPermohonanRujLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan);
            for(int i=0;i<senaraiPermohonanRujLuar.size();i++){
                //PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                //kand1 = senaraiLaporanKandungan2.get(i);
                PermohonanRujukanLuar kand1 = new PermohonanRujukanLuar();
                kand1 = senaraiPermohonanRujLuar.get(i);
                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                pelUtil.setPermohonanRujukanLuar(kand1);
                if(kand1.getAgensi().getKategoriAgensi().getKod().equals("JTK"))
                   senaraiLaporanKandunganUtil.add(pelUtil);
            }
        } else {
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiLaporanKandunganUtil = new Vector();
            senaraiPermohonanRujLuar = pelupusanService.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
            if (!senaraiPermohonanRujLuar.isEmpty()) {
                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                    
                    PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                    if(rujLuar.getUlasan() == null){
                        rujLuar.setUlasan("Tiada ulasan diterima");
                    }
                    pelUtil.setPermohonanRujukanLuar(rujLuar);
                    //kand1.setKandungan(kandVal);
                    //kand1.setSubtajuk(rujLuar.getAgensi().getNama());
                    if(rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK"))
                       //senaraiLaporanKandungan2.add(kand1);
                        senaraiLaporanKandunganUtil.add(pelUtil);
                }
            }


        }
       System.out.println("--------------permohonanKertas--------:"+permohonanKertas);
       if(permohonanKertas!=null){
//        senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),4);
        senaraiLaporanKandunganPTD = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),6);
        senaraiLaporanKandunganPTG = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),8);
        senaraiLaporanKandunganPTG2 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),9);
        if(senaraiLaporanKandunganPTD.isEmpty())
           senaraiLaporanKandunganPTD = new ArrayList<PermohonanKertasKandungan>();
        if(senaraiLaporanKandunganPTG.isEmpty())
           senaraiLaporanKandunganPTG = new ArrayList<PermohonanKertasKandungan>();
         if(senaraiLaporanKandunganPTG2.isEmpty())
           senaraiLaporanKandunganPTG2 = new ArrayList<PermohonanKertasKandungan>();
       }else{
          senaraiLaporanKandunganPTD = new ArrayList<PermohonanKertasKandungan>();
          senaraiLaporanKandunganPTG = new ArrayList<PermohonanKertasKandungan>();
          senaraiLaporanKandunganPTG2 = new ArrayList<PermohonanKertasKandungan>();
       }
       if(permohonanKertas!=null){
        List senaraiLaporanKandunganTolak = new ArrayList <PermohonanKertasKandungan>();
        senaraiLaporanKandunganTolak = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),5);
        if(!senaraiLaporanKandunganTolak.isEmpty()){
            PermohonanKertasKandungan mkk = new PermohonanKertasKandungan();
            mkk = (PermohonanKertasKandungan) senaraiLaporanKandunganTolak.get(0);
            syor = mkk.getKandungan();
        }
       }
       fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
         if(fasaPermohonan!= null && fasaPermohonan.getKeputusan()!= null){
             kodKepu = fasaPermohonan.getKeputusan().getKod();
         }
         
         
         convNama = pelupusanUtiliti.convertStringtoInitCap(pihak.getNama());
         convTempat = pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getLokasi());
         if(stageId.equals("06SediaDrafMMK"))
            simpanNew();
    }
    public Resolution showsyortolaklulus(){
        syortolaklulus = (String) getContext().getRequest().getParameter("syortolaklulus");
        rehydrate();
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
     public Resolution simpan() {

          LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RMN");
        LOG.info("----------Kod Dokumen----------::" + kd);
        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        LOG.info("-------Simpan-------permohonankertas--------------::");


         String ktanah = "";
         String sbb = "";
         String namaP = "";
         String kodUnitLuas = "";
         String lokasi = "";
         String bpm = "";
         String daerah = "";
         BigDecimal luas = BigDecimal.ZERO;
         String ursan1 = "";


        if (permohonan.getSebab() != null) {
            sbb = permohonan.getSebab();
        }
        
         if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan1 = " MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965.";
        }
        if (pemohon != null) {
            if(pemohon.getPihak()!=null)
            namaP = pemohon.getPihak().getNama();
        }
       
         if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                kodUnitLuas = hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getLokasi() != null) {
                lokasi = hakmilikPermohonan.getLokasi();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
           
        }

              tajuk1 =  "\n" +"PERMOHONAN DARIPADA" + namaP+ "UNTUK" +ursan1+ "\n"
                        + "  " +"SELUAS LEBIHKURANG" +luas+ " " +kodUnitLuas + "\n"
                        + "  " +"DI" +lokasi+ ","+"MUKIM"+bpm+","+"DAERAH"+daerah+ "\n"
                        + "  " +"UNTUK TUJUAN"+ktanah+" "+sbb+".";




/// fasa permohon

         if(permohonan.getKodUrusan().getKod().equals("PTGSA")){
                 fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "06SediaDrafMMK");
        }

//        else
//                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_rencana_ptg"); //MUST OPEN
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId"); //MUST OPEN
//        stageId = stageId(taskId);
         stageId = "06SediaDrafMMK"; //HIDE WHEN COMMIT
         if (fasaPermohonan == null) {
             LOG.info("------------if----FasaPermohonan--------------");
             fasaPermohonan = new FasaPermohonan();
             infoAudit.setDimasukOleh(peng);
             infoAudit.setTarikhMasuk(new java.util.Date());
         } else {
             LOG.info("------------else------FasaPermohonan------------");
             infoAudit = fasaPermohonan.getInfoAudit();
             infoAudit.setDiKemaskiniOleh(peng);
             infoAudit.setTarikhKemaskini(new java.util.Date());
         }
         fasaPermohonan.setInfoAudit(infoAudit);
         fasaPermohonan.setIdPengguna(peng.getIdPengguna());
         fasaPermohonan.setCawangan(peng.getKodCawangan());
         fasaPermohonan.setPermohonan(permohonan);
         fasaPermohonan.setIdAliran(stageId);
         fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
         pelupusanService.simpanFasaPermohonan(fasaPermohonan);

/// hakmilik permohonan

         HakmilikPermohonan hakmilikPermohonanTemp =new HakmilikPermohonan();
        if(fasaPermohonan!=null && fasaPermohonan.getKeputusan()!=null){
            if( fasaPermohonan.getKeputusan().getKod().equals("SL")){
        hakmilikPermohonanTemp = pelupusanService.findByIdPermohonan(idPermohonan);
         if(hakmilikPermohonanTemp!= null){
            infoAudit = hakmilikPermohonanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            hakmilikPermohonanTemp = new HakmilikPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        if(hakmilikPermohonan!=null&&hakmilikPermohonan.getTempohHakmilik()!=null){
            hakmilikPermohonanTemp.setTempohHakmilik(hakmilikPermohonan.getTempohHakmilik());
        }
        if (kodHmlk != null) {
            KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
            hakmilikPermohonanTemp.setKodHakmilikSementara(khm);
        }
         if (kodHmlk1 != null) {
            KodHakmilik khm1 = kodHakmilikDAO.findById(kodHmlk1);
            hakmilikPermohonanTemp.setKodHakmilikTetap(khm1);
        }
        if (kod != null) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonanTemp.setSyaratNyataBaru(kodSyarat);
        }

        if (kodSktn != null) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonanTemp.setSekatanKepentinganBaru(sekatan);
        }

        if (jenishakmilik != null) {
            KodHakmilik jenh = kodHakmilikDAO.findById(jenishakmilik);
            hakmilikPermohonanTemp.setKodHakmilik(jenh);
        }
        hakmilikPermohonanTemp.setInfoAudit(infoAudit);
        hakmilikPermohonanTemp.setPermohonan(permohonan);
        
        if(kodSktn!=null&&!("").equals(kodSktn))
           hakmilikPermohonanTemp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(kodSktn));
        if(kod!=null&&!("").equals(kod))
           hakmilikPermohonanTemp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(kod));
        hakmilikPermohonanTemp.setTempohHakmilik(hakmilikPermohonan.getTempohHakmilik());
        //hakmilikPermohonanTemp.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
        //hakmilikPermohonanTemp.setCatatan(hakmilikPermohonan.getCatatan());
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);
            }
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();
//        LOG.info("------------tajuk----------------------"+tajuk1);
//        if (tajuk1 == null || tajuk1.equals("")) {
//            tajuk1 = "TIADA DATA";
//        }
//
//        if (tujuan == null || tujuan.equals("")) {
//            tujuan = "TIADA DATA";
//        }
//        if (latarBelakang1 == null || latarBelakang1.equals("")) {
//            latarBelakang1 = "TIADA DATA";
//        }
//
//        if (syor == null || syor.equals("")) {
//            syor = "TIADA DATA";
//        }


//        listUlasan.add(tajuk1);
//        listUlasan.add(tujuan);
//        listUlasan.add(latarBelakang1);
//        if(syor!=null&&!("").equals(syor)){
//            updateKandungan(5, syor);
////            listUlasan.add(syor);
////            listSubtajuk.add("1");
////            billNo.add("5");
//        }
//
//        listSubtajuk.add("");
//        listSubtajuk.add("1.1");
//        listSubtajuk.add("2.1");
//        listSubtajuk.add("5.1");

//        billNo.add("0");
//        billNo.add("1");
//        billNo.add("2");
//        billNo.add("4");

//        if (permohonanKertas != null) {
//            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            pelupusanService.simpanPermohonanKertas(permohonanKertas);
//            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {
//
//                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
//                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
//                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
//
//                    LOG.info("----------------kertasKandungan-----------"+kertasKdgn);
//                    LOG.info("----------------kertasKandungan Size:::-----------"+permohonanKertas.getSenaraiKandungan());
//
//                    
//                       if (kertasKdgn.getSubtajuk()==null){
//                         kertasKdgn.setKandungan(tajuk1);
//                    }
//
//                    if (kertasKdgn.getSubtajuk()!=null && kertasKdgn.getSubtajuk().equals("1.1")) {
//                         kertasKdgn.setKandungan(tujuan);
//                        }
//                        if (kertasKdgn.getSubtajuk()!=null && kertasKdgn.getSubtajuk().equals("2.1")) {
//                        kertasKdgn.setKandungan(latarBelakang1);
//                        }
////                        if (kertasKdgn.getSubtajuk()!=null && kertasKdgn.getSubtajuk().equals("3.1")) {
////                        kertasKdgn.setKandungan(maklumanpengarah);
////                        }
//                        if (kertasKdgn.getSubtajuk()!=null && kertasKdgn.getSubtajuk().equals("4.1")) {
//                        kertasKdgn.setKandungan(syor);
//                        }
//
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    pelupusanService.simpanPermohonanKertasKandungan(kertasKdgn);
//                }
//            }
//        }else {
//            LOG.info("------else------permohonankertas:: NULL--------------::" + permohonanKertas);
//
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            permohonanKertas.setInfoAudit(infoAudit);
//            permohonanKertas.setPermohonan(permohonan);
//            permohonanKertas.setCawangan(permohonan.getCawangan());
//            permohonanKertas.setTajuk("Kertas JKTD");
//            kd = kodDokumenDAO.findById("JKTD");
//            permohonanKertas.setKodDokumen(kd);
//            pelupusanService.simpanPermohonanKertas(permohonanKertas);
//            for (int i = 0; i < listUlasan.size(); i++) {
//                String ulasan = (String) listUlasan.get(i);
//                String billNo1 = billNo.get(i);
//                String subTajuk = listSubtajuk.get(i);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
//                kertasKdgn.setKertas(permohonanKertas);
//                // kertasKdgn.setBil(i + 1);
//                kertasKdgn.setBil(Integer.parseInt(billNo1));
//                kertasKdgn.setSubtajuk(subTajuk);
//                kertasKdgn.setInfoAudit(infoAudit);
//                kertasKdgn.setKandungan(ulasan);
//                kertasKdgn.setCawangan(permohonan.getCawangan());
//                pelupusanService.simpanPermohonanKertasKandungan(kertasKdgn);
//            }
//        }


//        senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),4);

//        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
       
//        for (int i = 1; i <= kira; i++) {
//            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
//            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
//                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(3);
//            String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("3." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//           
//        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("popup", "true");

      }
     public Resolution simpanNew() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonanKertas = new PermohonanKertas();

        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        
        InfoAudit info = new InfoAudit();
        if (fasaPermohonan != null) {
            info = fasaPermohonan.getInfoAudit();
            info.setTarikhKemaskini(new java.util.Date());
            info.setDiKemaskiniOleh(pengguna);
        } else {
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            fasaPermohonan = new FasaPermohonan();
        }
        fasaPermohonan.setInfoAudit(info);
        fasaPermohonan.setCawangan(pengguna.getKodCawangan());
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setIdAliran(stageId);
        if(syortolaklulus!=null)
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
        //pelupusanService.simpanPermohonan(permohonan);
        
        // END : LPS
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
     public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
//            case 1:
//                break;
//            case 2:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 2);
//                senaraiLaporanKandungan1.add(pkk);
//                break;
//            case 3:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 3);
//                listKertasHuraianPTD.add(pkk);
//                break;
            case 4: //FOR HURAIAN PENTADBIR TANAH 
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiLaporanKandungan1.add(pkk);
                break;
//            case 5:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 5);
//                listKertasPerakuanPTG.add(pkk);
//                break;
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                senaraiLaporanKandunganPTD.add(pkk);
                break;
//              case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 7);
//                  senaraiLaporanKandunganpbtanah.add(pkk);
//                  break;
              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                  pkk = new PermohonanKertasKandungan();
                  pkk.setBil((short) 8);
                  senaraiLaporanKandunganPTG.add(pkk);
                  break;
              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                  pkk = new PermohonanKertasKandungan();
                  pkk.setBil((short) 9);
                  senaraiLaporanKandunganPTG2.add(pkk);
                  break;   
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
     public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
     public void updateKandungan(int i, String kand) {


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
            permohonanKertas.setTajuk("KERTAS MMKN");
            KodDokumen kod = kodDokumenDAO.findById("RMN");
            permohonanKertas.setKodDokumen(kod);
        } 
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonanKertas);

        long a = permohonanKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);
        
        if(i==5){
            pLK = pelupusanService.findByBilNIdKertas(5, permohonanKertas.getIdKertas());
            if(pLK==null){
                pLK = new PermohonanKertasKandungan();               
                pLK.setKertas(permohonanKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setSubtajuk("1");                
        }else{
            if (plk.isEmpty()) {
            pLK.setSubtajuk("1");
//            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setKertas(permohonanKertas);
            pLK.setInfoAudit(infoAudit);
            pLK.setCawangan(cawangan);
        }
        
        
        if(i==5){
            List senaraiLaporanKandunganTolak = new ArrayList <PermohonanKertasKandungan>();
            senaraiLaporanKandunganTolak = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),5);
            if(senaraiLaporanKandunganTolak.isEmpty()){
                pelPtService.simpanPermohonanKertasKandungan(pLK);
            }else{
                pelPtService.simpanUpdateOnlyPermohonanKertasKandungan(pLK);
            }            
        }             
        else
            pelPtService.simpanPermohonanKertasKandungan(pLK);

    }
     public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
//            case 2: //FOR Perihal Permohonan
//                updateKandungan(2, kand);
//
//                break;
//            case 3:
//
//                updateKandungan(3, kand);
//
//                break;
            case 4:  // FOR HURAIAN PENTADBIR TANAH DAERAH 

                updateKandungan(4, kand);

                break;
//            case 5:
//
//                updateKandungan(5, kand);
//
//                break;
            case 6: //FOR HURAIAN PTD

                updateKandungan(6, kand);
                break;
//              case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
//                  updateKandungan(7, kand);
//                  break;
              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                  updateKandungan(8, kand);
                  break;
              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                  updateKandungan(9, kand);
                  break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/gsa/draf_mmk_penamatan.jsp").addParameter("tab", "true");
    }
    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getUlasanJabatanKebajikan() {
        return ulasanJabatanKebajikan;
    }

    public void setUlasanJabatanKebajikan(String ulasanJabatanKebajikan) {
        this.ulasanJabatanKebajikan = ulasanJabatanKebajikan;
    }

    public String getUlasanJabatanTenagaKerja() {
        return ulasanJabatanTenagaKerja;
    }

    public void setUlasanJabatanTenagaKerja(String ulasanJabatanTenagaKerja) {
        this.ulasanJabatanTenagaKerja = ulasanJabatanTenagaKerja;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohonanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
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

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan() {
        return senaraiLaporanKandungan;
    }

    public void setSenaraiLaporanKandungan(List<PermohonanKertasKandungan> senaraiLaporanKandungan) {
        this.senaraiLaporanKandungan = senaraiLaporanKandungan;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getKodKategori() {
        return kodKategori;
    }

    public void setKodKategori(String kodKategori) {
        this.kodKategori = kodKategori;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodKepu() {
        return kodKepu;
    }

    public void setKodKepu(String kodKepu) {
        this.kodKepu = kodKepu;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }


 public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getKodHmlk1() {
        return kodHmlk1;
    }

    public void setKodHmlk1(String kodHmlk1) {
        this.kodHmlk1 = kodHmlk1;
    }

    public String getTajuk1() {
        return tajuk1;
    }

    public void setTajuk1(String tajuk1) {
        this.tajuk1 = tajuk1;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public PermohonanPermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermohonanPermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public String getAlamatPenuhPihak() {
        return alamatPenuhPihak;
    }

    public void setAlamatPenuhPihak(String alamatPenuhPihak) {
        this.alamatPenuhPihak = alamatPenuhPihak;
    }

    public String getAlamatPenuhPhbgn() {
        return alamatPenuhPhbgn;
    }

    public void setAlamatPenuhPhbgn(String alamatPenuhPhbgn) {
        this.alamatPenuhPhbgn = alamatPenuhPhbgn;
    }

    public List getSenaraiLaporanKandunganUtil() {
        return senaraiLaporanKandunganUtil;
    }

    public void setSenaraiLaporanKandunganUtil(List senaraiLaporanKandunganUtil) {
        this.senaraiLaporanKandunganUtil = senaraiLaporanKandunganUtil;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public boolean isEditPTG() {
        return editPTG;
    }

    public void setEditPTG(boolean editPTG) {
        this.editPTG = editPTG;
    }

    public boolean isOpenPTD() {
        return openPTD;
    }

    public void setOpenPTD(boolean openPTD) {
        this.openPTD = openPTD;
    }

    public boolean isOpenPTG() {
        return openPTG;
    }

    public void setOpenPTG(boolean openPTG) {
        this.openPTG = openPTG;
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

    public String getConvNama() {
        return convNama;
    }

    public void setConvNama(String convNama) {
        this.convNama = convNama;
    }

    public String getConvTempat() {
        return convTempat;
    }

    public void setConvTempat(String convTempat) {
        this.convTempat = convTempat;
    }

    public String getSyortolaklulus() {
        return syortolaklulus;
    }

    public void setSyortolaklulus(String syortolaklulus) {
        this.syortolaklulus = syortolaklulus;
    }

    public FasaPermohonan getFasaPermohonanJKTD() {
        return fasaPermohonanJKTD;
    }

    public void setFasaPermohonanJKTD(FasaPermohonan fasaPermohonanJKTD) {
        this.fasaPermohonanJKTD = fasaPermohonanJKTD;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPTD() {
        return senaraiLaporanKandunganPTD;
    }

    public void setSenaraiLaporanKandunganPTD(List<PermohonanKertasKandungan> senaraiLaporanKandunganPTD) {
        this.senaraiLaporanKandunganPTD = senaraiLaporanKandunganPTD;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPTG() {
        return senaraiLaporanKandunganPTG;
    }

    public void setSenaraiLaporanKandunganPTG(List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG) {
        this.senaraiLaporanKandunganPTG = senaraiLaporanKandunganPTG;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPTG2() {
        return senaraiLaporanKandunganPTG2;
    }

    public void setSenaraiLaporanKandunganPTG2(List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG2) {
        this.senaraiLaporanKandunganPTG2 = senaraiLaporanKandunganPTG2;
    }
    
}

