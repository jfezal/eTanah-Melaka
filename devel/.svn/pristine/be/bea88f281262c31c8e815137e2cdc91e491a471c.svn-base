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
 * @author navin
 *
 */
@UrlBinding("/pelupusan/draf_pertimbangan_ptd")
public class DrafPertimbanganPTDActionBean extends AbleActionBean {

    Logger LOG = Logger.getLogger(DrafPertimbanganPTDActionBean.class);
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
    private HakmilikPermohonan hakmilikPermohonanSendiri ;
    private PermohonanPermitItem permitItem;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pihak pihak;
    private String alamatPenuhPihak;
    private String alamatPenuhPhbgn;
    private Pengguna peng;
    private String tujuan;
    //private String latarBelakang1;
    private LaporanTanah laporanTanah;
    private PermohonanLaporanPelan permohonanLaporPelan;
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
    private List<PermohonanKertasKandungan> senaraiLatarBelakang;
    private List<PermohonanKertasKandungan> senaraiPerihalTanah;
    private List<PermohonanKertasKandungan> senaraiPerihalPemohon;
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
    private String daerah;
    private String perihalPemohon21;
    private String perihalPemohon41;
    private String perihalPemohon42;
    private PermohonanKertasKandungan kertasKandungan;
    private PemohonHubungan pemohonHubungan;
    private String huraianPentadbir;
    private Boolean edit;
    private boolean editPTD;
    private boolean editPTG;
    private boolean openPTG;
    private boolean openPTD;
    private String syortolaklulus;
      private List<HakmilikPermohonan> hakmilikPermohonanList;

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
        if (permohonanKertas != null) {
            LOG.info("-------showForm--------senaraiLaporanKandungan1------------------------------" + senaraiLaporanKandungan1);
            senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
            if (senaraiLaporanKandungan1.isEmpty()) {
                senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            }

        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTG() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.TRUE;
        if (permohonanKertas != null) {
            LOG.info("-------showForm--------senaraiLaporanKandungan1------------------------------" + senaraiLaporanKandungan1);
            senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
            if (senaraiLaporanKandungan1.isEmpty()) {
                senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            }

        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }
    
    public Resolution popupHakmilikDetail() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        //showUrusan = true;
        //senaraiHakmilikUrusan = hUService.findHakmilikUrusanByIdHakmilik(idHakmilik);
        LOG.debug("popup idHakmilik :"+idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution viewFormForPTD() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormForPTG() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
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
         List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
         senaraiHakmilikTerlibat = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan); // For tanah lalu lalang awam
         if(senaraiHakmilikTerlibat.size() > 0){  
        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
         }
         
          List senaraiHakmilikSendiri = new ArrayList<HakmilikPermohonan>();
         senaraiHakmilikSendiri = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
         if(senaraiHakmilikSendiri.size() > 0){  
        hakmilikPermohonanSendiri = (HakmilikPermohonan) senaraiHakmilikSendiri.get(0);
         }
        hakmilikPermohonanList =permohonan.getSenaraiHakmilik();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if (pemohon != null) {
            pihak = pemohon.getPihak();
            if (pihak != null) {
                if (pihak.getAlamat1() != null && !("").equals(pihak.getAlamat1())) {
                    alamatPenuhPihak = pihak.getAlamat1();
                }
                if (pihak.getAlamat2() != null && !("").equals(pihak.getAlamat2())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat2();
                }
                if (pihak.getAlamat3() != null && !("").equals(pihak.getAlamat3())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat3();
                }
                if (pihak.getAlamat4() != null && !("").equals(pihak.getAlamat4())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat4();
                }
            }
        }
//        permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
      //  permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
//        permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
        //permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));

        String currentStageId = stageId(taskId);
//        String currentStageId = "08SemakSyorMMK";
//         String currentStageId = "06SediaDrafMMK";
        stageId = "06SediaDrafMMK"; // ***MUST GET SINCE TO GET KODKEPUTUSAN MMKN
        if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "11SedKrts");
        } else {
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "stageId");
//          fasaPermohonanJKTD = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "06SediaDrafMMK"); //CHANGE IF HAVE JKTD
        }

        if (syortolaklulus == null) {
            if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
                syortolaklulus = fasaPermohonan.getKeputusan().getKod();
            }
        }
//        else
//                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_rencana_ptg");
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        stageId = stageId(taskId);
//        fasaPermohonan= pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan,stageId);
        if(pemohon!=null)
        {
            pemohonHubungan = pelupusanService.findHubunganByIDSuamiIsteri(pemohon.getIdPemohon());
            if (pemohonHubungan != null) {
                if (pemohonHubungan.getAlamat1() != null && !("").equals(pemohonHubungan.getAlamat1())) {
                    alamatPenuhPhbgn = pemohonHubungan.getAlamat1();
                }
                if (pemohonHubungan.getAlamat2() != null && !("").equals(pemohonHubungan.getAlamat2())) {
                    alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat2();
                }
                if (pemohonHubungan.getAlamat3() != null && !("").equals(pemohonHubungan.getAlamat3())) {
                    alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat3();
                }
                if (pemohonHubungan.getAlamat4() != null && !("").equals(pemohonHubungan.getAlamat4())) {
                    alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat4();
                }
            }
         }
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
        LOG.info("-------------fasaPermohonan---------------------" + fasaPermohonan);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            fasaPermohonan.getKeputusan().getNama();
        }
        if (hakmilikPermohonan != null) {
            System.out.println("idPermohonan : " + idPermohonan);
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
                syaratNyata = hakmilikPermohonan.getSyaratNyataBaru().getSyarat();
            }
            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
                syaratNyata1 = hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan();
            }
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                kodKategori = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            }
            if (hakmilikPermohonan.getKodHakmilikSementara() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
            }
            if (hakmilikPermohonan.getKodHakmilikTetap() != null) {
                kodHmlk1 = hakmilikPermohonan.getKodHakmilikTetap().getKod();
            }
        }
        
//         if (hakmilikPermohonan.getBandarPekanMukimBaru().getKod().equals("PTGSA")){
//                /*
//                 * TEMPORARY SINCE PHLP ONLY CATER 1 HM DATE for PAT - 30/11/2011 
//                 */
//                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
//                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
//                if(hmList.size()>0){
//                    hakmilikPermohonan = hmList.get(0);
//                }
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
        daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String jarak = "";
        String unitJarak = "";
        String jarakDari = "";
     //   String kws = "";
        String ursan1 = "";


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
            ursan1 = " PERMOHONAN PENAMATAN TANAH BERKELOMPOK GSA DI BAWAH AKTA TANAH (KAWASAN-KAWASAN PENEMPATAN BERKELOMPOK) 1960.";
        }
        if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
//            Permohonan Hak Lalu Lalang Awam DiBawah Seksyen 390(2)b
            ursan1 = " PERMOHONAN HAK LALU LALANG AWAM DIBAWAH SEKSYEN 390(2)b.";
        }
        if (pemohon != null) {
            if (pemohon.getPihak() != null) {
                namaP = pemohon.getPihak().getNama();
            }
        }
//        if (permohonanLaporPelan != null) {
//            if (permohonanLaporPelan.getKawasanPihakBerkuasa() != null) {
//                kws = permohonanLaporPelan.getKawasanPihakBerkuasa();
//            }
//        }
        convNama = pelupusanUtiliti.convertStringtoInitCap(pihak.getNama());
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
            }else{
                if(hakmilikPermohonan.getHakmilik()!=null){
                    bpm = hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama();
                }
            }

            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
            if (hakmilikPermohonan.getJarak() != null) {
                jarak = hakmilikPermohonan.getJarak();
            }
            if (hakmilikPermohonan.getUnitJarak() != null) {
                unitJarak = hakmilikPermohonan.getUnitJarak().getNama();
            }
            if (hakmilikPermohonan.getJarakDari() != null) {
                jarakDari = hakmilikPermohonan.getJarakDari();
            }
        }
//
//        tajuk1 = "\n" + "PERMOHONAN DARIPADA " + namaP + " UNTUK " + ursan1 + "\n"
//                + "  " + " SELUAS LEBIH KURANG " + luas + " " + kodUnitLuas + "\n"
//                + "  " + " DI " + lokasi + ", " + bpm + ", " + " DAERAH " + daerah + "\n"
//                + "  " + " UNTUK TUJUAN " + ktanah + " " + sbb + ".";
//        
         String tajukLot = new String() ;
         String noLot = new String();
         if(hakmilikPermohonan != null){
             if(hakmilikPermohonan.getHakmilik()!=null){
                 tajukLot = hakmilikPermohonan.getHakmilik().getLot().getNama();
                 noLot = hakmilikPermohonan.getHakmilik().getNoLot();
             }
             else{
                 tajukLot = hakmilikPermohonan.getLot().getNama() ;
                 noLot = hakmilikPermohonan.getNoLot();
             }
         }
         tajuk1 = "PERMOHONAN UNTUK MELALUI " +tajukLot+" "+noLot+ "  " + " SELUAS LEBIH KURANG " + luas + " " + kodUnitLuas + 
                  " " + "DI " + lokasi + ", " + bpm + ", DAERAH " + daerah+
                  " " + "UNTUK PERMOHONAN HAK LALU LALANG AWAM DIBAWAH SEKSYEN 390(2)b.";
         if(!lokasi.isEmpty()){
             lokasi = pelupusanUtiliti.convertStringtoInitCap(lokasi);
         }
         tujuan = "\n" + "Tujuan kertas ini ialah untuk mendapatkan pertimbangan Pentadbir Tanah mengenai permohonan untuk melalui "+tajukLot+" "+noLot
                  +" seluas lebih kurang "+ luas +" "+kodUnitLuas+" di "+lokasi+", "+pelupusanUtiliti.convertStringtoInitCap(bpm)+", Daerah "+pelupusanUtiliti.convertStringtoInitCap(daerah);
        /*
         * BIL 2
         */
         String kodUOM = "";
         if(hakmilikPermohonanSendiri.getKodUnitLuas() != null)
             kodUOM = hakmilikPermohonanSendiri.getKodUnitLuas().getNama();

         perihalPemohon21 = "Pentadbir Tanah "+pelupusanUtiliti.convertStringtoInitCap(daerah)+" telah menerima permohonan daripada "+convNama+" untuk tanah "+hakmilikPermohonanSendiri.getHakmilik().getLot().getNama()+" "+hakmilikPermohonanSendiri.getHakmilik().getNoLot()
                            +" seluas lebih kurang "+ hakmilikPermohonanSendiri.getLuasTerlibat() +" "+kodUOM+" di "+hakmilikPermohonanSendiri.getLokasi()+", "+pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonanSendiri.getHakmilik().getBandarPekanMukim().getNama())+", Daerah "+pelupusanUtiliti.convertStringtoInitCap(daerah);
        /*
         * END OF BIL 2
         */
        /*
         * BIL 4
         */
        if(!alamatPenuhPihak.isEmpty())
            alamatPenuhPihak = pelupusanUtiliti.convertStringtoInitCap(alamatPenuhPihak);
        String ayatPengenalan = new String();
        if(pihak.getJenisPengenalan().getKod().equalsIgnoreCase("B")){
            ayatPengenalan = "No K/P "+pihak.getNoPengenalan();
        }else if(pihak.getJenisPengenalan().getKod().equalsIgnoreCase("S")){
            ayatPengenalan = "No Syarikat "+pihak.getNoPengenalan();
        }
        perihalPemohon41 = "Pemohon ialah "+convNama+" "+ayatPengenalan+" yang beralamat di "+alamatPenuhPihak;
        perihalPemohon42 = "Tujuan permohonan dibuat adalah untuk melalui tanah "+tajukLot+" "+noLot
                  +" seluas lebih kurang "+ luas +" "+kodUnitLuas+" di "+lokasi+", "+pelupusanUtiliti.convertStringtoInitCap(bpm)+", Daerah "+pelupusanUtiliti.convertStringtoInitCap(daerah)+" dari kawasan hak lalu lalang awam dibawah seksyen 390(2)b.";
        /*
         * END OF BIL 4
         */
//        latarBelakang1 = "\n" + "Tanah yang dimaksudkan itu seperti bertanda merah di dalam pelan letaknya :-" + "\n"
//                + " " + " i) Mukim :" + bpm + "." + "\n"
//                + " " + " ii)" + lokasi + "." + "\n"
//                + " " + " iii)" + "Lebihkurang" + jarak + " " + unitJarak + "dari" + " " + jarakDari + "." + "\n"
//                + " " + " iv)" + "Jenis Tanah Desa" + "." + "\n"
//                + " " + " v)" + kws + "Kawasan Rizab Melayu" + "." + "\n"
//                + " " + " vi)" + kws + "Kawasan GSA" + "." + "\n"
//                + " " + " vii)" + kws + "Kawasan GSA" + ".";

        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RPPTD");

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
//        if (permohonanKertas != null) {
//            senaraiLaporanKandunganUtil = new Vector();
//            //senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
//            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
//            senaraiPermohonanRujLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan);
//            for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
//                //PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
//                //kand1 = senaraiLaporanKandungan2.get(i);
//                PermohonanRujukanLuar kand1 = new PermohonanRujukanLuar();
//                kand1 = senaraiPermohonanRujLuar.get(i);
//                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
//                pelUtil.setPermohonanRujukanLuar(kand1);
//                if (kand1.getAgensi().getKategoriAgensi().getKod().equals("JTK")) {
//                    senaraiLaporanKandunganUtil.add(pelUtil);
//                }
//            }
//        } else {
//            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
//            senaraiLaporanKandunganUtil = new Vector();
//            senaraiPermohonanRujLuar = pelupusanService.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
//            if (!senaraiPermohonanRujLuar.isEmpty()) {
//                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
//                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
//                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
//
//                    PelupusanUtiliti pelUtil = new PelupusanUtiliti();
//                    if (rujLuar.getUlasan() == null) {
//                        rujLuar.setUlasan("Tiada ulasan diterima");
//                    }
//                    pelUtil.setPermohonanRujukanLuar(rujLuar);
//                    //kand1.setKandungan(kandVal);
//                    //kand1.setSubtajuk(rujLuar.getAgensi().getNama());
//                    if (rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK")) //senaraiLaporanKandungan2.add(kand1);
//                    {
//                        senaraiLaporanKandunganUtil.add(pelUtil);
//                    }
//                }
//            }
//
//
//        }
        System.out.println("--------------permohonanKertas--------:" + permohonanKertas);
        if (permohonanKertas != null) {
            senaraiLatarBelakang = new ArrayList<PermohonanKertasKandungan>();
            senaraiPerihalTanah = new ArrayList<PermohonanKertasKandungan>();
            senaraiPerihalPemohon = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganPTD = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganPTG = new ArrayList<PermohonanKertasKandungan>();
            
            List<PermohonanKertasKandungan> senaraiLatarBelakangTemp = new ArrayList<PermohonanKertasKandungan>();
            senaraiLatarBelakangTemp = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 2);
            for(PermohonanKertasKandungan pkk : senaraiLatarBelakangTemp){
                if(!pkk.getSubtajuk().equals("1")){
                    senaraiLatarBelakang.add(pkk);
                }
            }
            senaraiPerihalTanah = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
            
            List<PermohonanKertasKandungan> senaraiPerihalPemohonTemp = new ArrayList<PermohonanKertasKandungan>();
            senaraiPerihalPemohonTemp = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 4);             
            for(PermohonanKertasKandungan pkk : senaraiPerihalPemohonTemp){
                if(!pkk.getSubtajuk().equals("1")&&!pkk.getSubtajuk().equals("2")){
                    senaraiPerihalPemohon.add(pkk);
                }
            } 
            
            senaraiLaporanKandunganPTD = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
            senaraiLaporanKandunganPTG = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
            
            /*
             * INITIALIZE WHEN EMPTY
             */
            senaraiLatarBelakang = senaraiLatarBelakang.isEmpty()?new ArrayList<PermohonanKertasKandungan>():senaraiLatarBelakang;
            senaraiPerihalTanah = senaraiPerihalTanah.isEmpty()?new ArrayList<PermohonanKertasKandungan>():senaraiPerihalTanah;
            senaraiPerihalPemohon = senaraiPerihalPemohon.isEmpty()?new ArrayList<PermohonanKertasKandungan>():senaraiPerihalPemohon;
            senaraiLaporanKandunganPTD = senaraiLaporanKandunganPTD.isEmpty()?new ArrayList<PermohonanKertasKandungan>():senaraiLaporanKandunganPTD;
            senaraiLaporanKandunganPTG = senaraiLaporanKandunganPTG.isEmpty()?new ArrayList<PermohonanKertasKandungan>():senaraiLaporanKandunganPTG;            
            
        } else {
            senaraiLatarBelakang = new ArrayList<PermohonanKertasKandungan>();
            senaraiPerihalTanah = new ArrayList<PermohonanKertasKandungan>();
            senaraiPerihalPemohon = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganPTD = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganPTG = new ArrayList<PermohonanKertasKandungan>();
        }
//        if (permohonanKertas != null) {
//            List senaraiLaporanKandunganTolak = new ArrayList<PermohonanKertasKandungan>();
//            senaraiLaporanKandunganTolak = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
//            if (!senaraiLaporanKandunganTolak.isEmpty()) {
//                PermohonanKertasKandungan mkk = new PermohonanKertasKandungan();
//                mkk = (PermohonanKertasKandungan) senaraiLaporanKandunganTolak.get(0);
//                syor = mkk.getKandungan();
//            }
//        }
//        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
//        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
//            kodKepu = fasaPermohonan.getKeputusan().getKod();
//        }


       
        if(!StringUtils.isBlank(hakmilikPermohonan.getLokasi()))
        convTempat = pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getLokasi());
        if (permohonan.getKodUrusan().getKod().equals("PHLA")){
//        if (currentStageId.equals("11SedKrts")) {
            simpanNew();
        }
    }

    public Resolution showsyortolaklulus() {
        syortolaklulus = (String) getContext().getRequest().getParameter("syortolaklulus");
        rehydrate();
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
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
            if (pemohon.getPihak() != null) {
                namaP = pemohon.getPihak().getNama();
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

        }

        tajuk1 = "\n" + "PERMOHONAN DARIPADA" + namaP + "UNTUK" + ursan1 + "\n"
                + "  " + "SELUAS LEBIHKURANG" + luas + " " + kodUnitLuas + "\n"
                + "  " + "DI" + lokasi + "," + "MUKIM" + bpm + "," + "DAERAH" + daerah + "\n"
                + "  " + "UNTUK TUJUAN" + ktanah + " " + sbb + ".";




/// fasa permohon

        if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
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

        HakmilikPermohonan hakmilikPermohonanTemp = new HakmilikPermohonan();
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("SL")) {
                hakmilikPermohonanTemp = permohonan.getSenaraiHakmilik().get(0);
                if (hakmilikPermohonanTemp != null) {
                    infoAudit = hakmilikPermohonanTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    hakmilikPermohonanTemp = new HakmilikPermohonan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohHakmilik() != null) {
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

                if (kodSktn != null && !("").equals(kodSktn)) {
                    hakmilikPermohonanTemp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(kodSktn));
                }
                if (kod != null && !("").equals(kod)) {
                    hakmilikPermohonanTemp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(kod));
                }
                hakmilikPermohonanTemp.setTempohHakmilik(hakmilikPermohonan.getTempohHakmilik());
                //hakmilikPermohonanTemp.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
                //hakmilikPermohonanTemp.setCatatan(hakmilikPermohonan.getCatatan());
                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);
            }
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("popup", "true");

    }

    public Resolution simpanNew() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonanKertas = new PermohonanKertas();

        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RPPTD");
        /*
         * INSERTING MOHON_KERTAS
         */
        if (permohonanKertas == null) {
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(pengguna);
                
            permohonanKertas = new PermohonanKertas();
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
            permohonanKertas.setKodDokumen(kodDokumenDAO.findById("RPPTD"));
            permohonanKertas.setInfoAudit(iaP);
            pelupusanService.simpanPermohonanKertas(permohonanKertas);

            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RPPTD");
            if(permohonanKertas!=null){
                
                
                /*
                 * INSERTING TAJUK
                 */
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(0);
                String kandungan = tajuk1;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                
                /*
                 * INSERTING TUJUAN
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(1);
                kandungan = tujuan;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                
                /*
                 * INSERTING LATAR BELAKANG
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(2);
                kandungan = perihalPemohon21;
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                
                
                /*
                 * INSERTING PERIHAL PEMOHON
                 */
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(4);
                if(pihak.getJenisPengenalan().getKod().equals("B")||pihak.getJenisPengenalan().getKod().equals("L")||pihak.getJenisPengenalan().getKod().equals("P")||pihak.getJenisPengenalan().getKod().equals("T")||pihak.getJenisPengenalan().getKod().equals("I")||pihak.getJenisPengenalan().getKod().equals("O")||pihak.getJenisPengenalan().getKod().equals("N")||pihak.getJenisPengenalan().getKod().equals("F"))
                    kandungan = perihalPemohon41;
                else if(pihak.getJenisPengenalan().getKod().equals("U")||pihak.getJenisPengenalan().getKod().equals("D")||pihak.getJenisPengenalan().getKod().equals("S"))
                    kandungan = perihalPemohon41;
                
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(4);
                
                kandungan = perihalPemohon42;                
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2");
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                
                
                
                
                        
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();                
//                permohonanKertasKandungan1.setKertas(permohonankertas);
//                permohonanKertasKandungan1.setBil(7);
//                
//                kandungan = pentabirtanah2;                
//                permohonanKertasKandungan1.setKandungan(kandungan);
//                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//                permohonanKertasKandungan1.setSubtajuk("2");
//                permohonanKertasKandungan1.setInfoAudit(iaP);
//                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
            
        }
        /*
         * END OF MOHON_KERTAS AND MOHON_KERTAS KANDUNGAN
         */
        
        
        
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
        if (syortolaklulus != null) {
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(syortolaklulus));
        }
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
        //pelupusanService.simpanPermohonan(permohonan);
        
        // END : LPS
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
//            case 1:
//                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                senaraiLatarBelakang.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                senaraiPerihalTanah.add(pkk);
                break;
            case 4: //FOR HURAIAN PENTADBIR TANAH 
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiPerihalPemohon.add(pkk);
                break;
            case 5:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 5);
                senaraiLaporanKandunganPTD.add(pkk);
                break;
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                senaraiLaporanKandunganPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
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
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
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


        if(i==2){
            if (plk.isEmpty()) {
                pLK.setSubtajuk("2");
    //            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
        }else if(i==4){
            if (plk.isEmpty()) {
                pLK.setSubtajuk("3");
    //            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
        }
        else{
            if (plk.isEmpty()) {
                pLK.setSubtajuk("1");
    //            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
        }
        pLK.setBil((short) i);
        pLK.setKandungan(kand);
        pLK.setKertas(permohonanKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);        
        

    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1 : //NOTHING
                break;
            case 2: //LATAR BELAKANG
                updateKandungan(2, kand);
                break;
            case 3: //PERIHAL TANAH
                updateKandungan(3, kand);
                break;
            case 4: //PERIHAL PEMOHON
                updateKandungan(4, kand);
                break;
            case 5: //PERAKUAN PTD
                updateKandungan(5, kand);
                break;
            case 6: //PERAKUAN PTG
                updateKandungan(6, kand);
                break;
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/draf_pertimbangan_ptd.jsp").addParameter("tab", "true");
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

//    public String getLatarBelakang1() {
//        return latarBelakang1;
//    }
//
//    public void setLatarBelakang1(String latarBelakang1) {
//        this.latarBelakang1 = latarBelakang1;
//    }

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

    public String getPerihalPemohon41() {
        return perihalPemohon41;
    }

    public void setPerihalPemohon41(String perihalPemohon41) {
        this.perihalPemohon41 = perihalPemohon41;
    }

    public String getPerihalPemohon42() {
        return perihalPemohon42;
    }

    public void setPerihalPemohon42(String perihalPemohon42) {
        this.perihalPemohon42 = perihalPemohon42;
    }

    public String getPerihalPemohon21() {
        return perihalPemohon21;
    }

    public void setPerihalPemohon21(String perihalPemohon21) {
        this.perihalPemohon21 = perihalPemohon21;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLatarBelakang() {
        return senaraiLatarBelakang;
    }

    public void setSenaraiLatarBelakang(List<PermohonanKertasKandungan> senaraiLatarBelakang) {
        this.senaraiLatarBelakang = senaraiLatarBelakang;
    }

    public List<PermohonanKertasKandungan> getSenaraiPerihalPemohon() {
        return senaraiPerihalPemohon;
    }

    public void setSenaraiPerihalPemohon(List<PermohonanKertasKandungan> senaraiPerihalPemohon) {
        this.senaraiPerihalPemohon = senaraiPerihalPemohon;
    }

    public List<PermohonanKertasKandungan> getSenaraiPerihalTanah() {
        return senaraiPerihalTanah;
    }

    public void setSenaraiPerihalTanah(List<PermohonanKertasKandungan> senaraiPerihalTanah) {
        this.senaraiPerihalTanah = senaraiPerihalTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikPermohonan getHakmilikPermohonanSendiri() {
        return hakmilikPermohonanSendiri;
    }

    public void setHakmilikPermohonanSendiri(HakmilikPermohonan hakmilikPermohonanSendiri) {
        this.hakmilikPermohonanSendiri = hakmilikPermohonanSendiri;
    }
    
    
}
