/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.KodKadarPremium;
//import etanah.service.BPelService;
import etanah.model.KodTuntut;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.RegService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
//import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author khadijah
 */
@UrlBinding("/hasil/kertas_risalatmmkn")
public class DrafMMKNHasilActionBean extends AbleActionBean {

    Logger logger = Logger.getLogger(DrafMMKNHasilActionBean.class);
    @Inject
    BPelService service;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanPihakDAO PermohonanPihakDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    PemohonHubunganDAO PemohonHubunganDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
//    @Inject
//    KodItemPermitDAO kodItemPermitDAO;
    private List<KodItemPermit> senaraiKodItemPermit;
    private PermohonanPermitItem permohonanPermitItem;
    private KodTuntut kodTuntut;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private KodKadarPremium kodKadarPremium;
    private List<KodKadarPremium> senaraikodKadarPremium;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertas permohonankertas;
    private LaporanTanah laporanTanah;
    private PermohonanPlotPelan permohonanPlotPelan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String stageId;
    private Pengguna peng;
    private PemohonHubungan pemohonHubungan;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    String pejTanah;
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private PihakPengarah pihakPengarah;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String huraianPentadbir2;
    private String huraianPentadbir3;
    private String huraianPentadbir4;
    private String huraianPentadbir5;
    private String syorPentadbir2;
    private String syorPentadbir3;
    private String syorPentadbir4;
    private String syorPentadbir5;
    private String huraianPtg2;
    private String huraianPtg3;
    private String huraianPtg4;
    private String huraianPtg5;
    private String syorPtg2;
    private String syorPtg3;
    private String syorPtg4;
    private String syorPtg5;
    private boolean btn = true;
    private String lokasi;
    private String index;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private String sekatKpntgn2;
    private String syaratNyata2;
    private String kodSktn;
    private String kod;
    private String jenishakmilik;
    private String tempoh;
    private String hasil;
    private String kodHmlk;
    private KodHakmilik kodHakmilik;
    private String catatan;
    private Boolean edit;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodDokumen kodDokumen;
    private FasaPermohonan fasaPermohonan;
    private KodKeputusan kodKeputusan;
    private String kodKepu;
    private String participant;
    private String tajuk;
    private String perihalpermohonan;
    private String perihalpermohonan1;
    private String perihaltanah;
    private String perihaltanah1;
    private String perihalpemohon;
    private String perihalpemohon_s;
    private String perihalpemohon1;
    private String perihalpemohon2;
    private String perihalpemohon2suami;
    private String pentabirtanah1;
    private String pentabirtanah2;
    private String ulasanyb;
    private String hurianpengarah;
    private String syorpengarah;
    private String tajukPlps;
    private String tujuanPlps;
    private String perihalpermohonanPlps;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan3;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah;
    private String syaratNyata;
    private String syaratNyata1;
    private PermohonanPermitItem permitItem ;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//    private BPelService service;
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (kertasK != null) {
            btn = false;
        }
        return new JSP("pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        edit = Boolean.FALSE;
        return new JSP("pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit = Boolean.TRUE;
        return new JSP("pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");
    }public Resolution showForm3() {
        edit = Boolean.FALSE;
        return new JSP("pelupusan/pbmt/draf_mmkn_mlk.jsp").addParameter("tab", "true");
    }


    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = pelupusanService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pelupusanService.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn_drafMMK.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata("%", kc, syaratNyata2);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata_drafMMK.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermit();
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if (pemohon != null) {
            pemohonHubungan = pelupusanService.findHubunganByIDIsteri(pemohon.getIdPemohon());
        }
        if (pemohon != null) {
            listPengarah = pelupusanService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
        }


            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
            if((permohonanLaporanPelan != null) && (permohonanLaporanPelan.getKodTanah() != null)) {
            logger.info("--------------KodTanah:" + permohonanLaporanPelan.getKodTanah().getKod());
            }
             if((hakmilikPermohonan != null)&& (hakmilikPermohonan.getKodKegunaanTanah() != null)){
            logger.info("--------------KodGunaTanah:" + hakmilikPermohonan.getKodKegunaanTanah().getKod());
        }

        if ((permohonanLaporanPelan != null) && (permohonanLaporanPelan.getKodTanah() != null) && (hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            senaraikodKadarPremium = pelupusanService.findKodKadarPremiumNama(permohonanLaporanPelan.getKodTanah().getKod(), hakmilikPermohonan.getKodKegunaanTanah().getKod());
            logger.info("--------------senaraikodKadarPremium:" + senaraikodKadarPremium);
        }
        if (kodKadarPremium != null) {
        }

        if (pemohon != null) {
            pihak = pemohon.getPihak();
        }


        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        Task task = service.getTaskFromBPel(taskId, peng);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
//REMOVE
        stageId = "kira_premium";
        logger.info("----------stageID-------------------" + stageId);


        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            kodKepu = fasaPermohonan.getKeputusan().getKod();
        }


//          try {
//            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
//            for (Task t : l) {
//                stageID = t.getSystemAttributes().getStage();
//            participant = t.getSystemAttributes().getParticipantName();
//            logger.info("-----------------Stage ID:" + stageID + "----default--------");
//            }
//        } catch (Exception e) {
//            logger.error("error ::" + e.getMessage());
//        }



        String kc = peng.getKodCawangan().getKod();
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        String noLot = "";
        String bpm = "";
        String lotNama = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String ursan = "";
        String ktanah = "";
        String sbb = "";

        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            sbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
        }


        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
            if (hakmilikPermohonan.getKodHakmilik() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilik().getKod();

            }
//            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
//                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
//            }
//            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
//                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
//            }

 // rohan added
             if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
            String   kod1=(hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            logger.info("----kod1---------"+kod1);
             syaratNyata = ""+kod+" -- "+kod1+"";
        logger.info("----syaratNyata---------"+syaratNyata);
        }else{
            kod ="";
        }

        if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            logger.info("----kodSktn1---------"+kodSktn);
              String   kod2=(hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
               syaratNyata1 =  ""+kodSktn+" -- "+kod2+"";
        logger.info("----syaratNyata1---------"+syaratNyata1);
        }
        else{
            kodSktn ="";
        }

            if (hakmilikPermohonan.getNoLot() != null) {
                noLot = hakmilikPermohonan.getNoLot();
            }
            luas = hakmilikPermohonan.getLuasTerlibat();
            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }
            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();

            }
            if (hakmilikPermohonan.getLot() != null) {
                lotNama = hakmilikPermohonan.getLot().getNama();
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan = "Memiliki Tanah Kerajaan";
        }
//        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
//            ursan = "MENDUDUKI TANAH KERAJAAN";
//        }

        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }
        lokasi = lotNama + " " + noLot + ", " + bpm + ", Daerah " + daerah + ", ";

        String sub = permohonan.getIdPermohonan().substring(0, 2);
        String tempat = "";
        if (sub.equals("04")) {
            tempat = "Melaka";
        } else if (sub.equals("05")) {
            tempat = "Negeri Sembilan Darul Khusus";
        }

        String koduom = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
            koduom = hakmilikPermohonan.getKodUnitLuas().getNama();
            logger.info("-----------koduom---------" + koduom);
        }

        String tujuanursan = " ";
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            tujuanursan = "Memiliki Tanah Kerajaan";
        }

        tujuan = " Tujuan kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri mengenai permohonan untuk "
                + tujuanursan + " " + noLot + " seluas " + luas + " " + koduom + " di Mukim / Kawasan " + bpm + " Daerah " + daerah + " untuk tujuan "
                + ktanah + " " + (sbb) + " .";



        String tujuanursanLps = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            tujuanursanLps = "Menduduki Tanah Kerajaan Secara Pendudukan Sementara";
            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
            sbb = permitItem.getKodItemPermit().getNama() ;
        }

        tujuanPlps = " Tujuan kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri mengenai permohonan untuk "
                + tujuanursanLps + ", " + noLot + " seluas " + luas + " " + koduom + " di Mukim / Kawasan " + bpm + " Daerah " + daerah + " untuk tujuan "
                + sbb + " .";

        String hklokasi = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLokasi() != null)) {
            hklokasi = hakmilikPermohonan.getLokasi();
        }

        String pihakname = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakname = pemohon.getPihak().getNama();
        }

        String mtarikmasuk = " ";
        if (permohonan != null) {
            mtarikmasuk = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
        }

        String ursan1 = " ";
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan1 = "Memilik Tanah Kerajaan";
        }


        perihalpermohonan = " Pentadbir Tanah Melaka Tengah telah menerima satu permohonan daripada " + pihakname + " pada " + mtarikmasuk + " untuk "
                + ursan1 + " di " + hklokasi + ", " + " selulas " + luas + " " + koduom + " di mukim/kawasan " + bpm + ", "
                + " Daerah Melaka Tengah untuk tujuan " + sbb + ".";


        String ursanPlps1 = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            ursanPlps1 = "Menduduki Tanah Kerajaan Secara Pendudukan Sementara";
        }

        perihalpermohonanPlps = " Pentadbir Tanah " + permohonan.getCawangan().getDaerah().getNama() + " telah menerima satu permohonan daripada " + pihakname + " pada " + mtarikmasuk + " untuk "
                + ursanPlps1 + " di " + hklokasi + ", " + " seluas " + luas + " " + koduom + " di mukim/kawasan " + bpm + ", "
                + " Daerah " + permohonan.getCawangan().getDaerah().getNama() + " untuk tujuan " + sbb + ".";

        String kodmilik = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
            kodmilik = hakmilikPermohonan.getKodMilik().getNama();
        }

        String jarak = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getJarak() != null)) {
            jarak = hakmilikPermohonan.getJarak();
        }

        String ujarak = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getUnitJarak() != null)) {
            ujarak = hakmilikPermohonan.getUnitJarak().getNama();
        }

        String jarakd = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getJarakDari() != null)) {
            jarakd = hakmilikPermohonan.getJarakDari();
        }

        String dan = " ";
        if ((laporanTanah != null) && (laporanTanah.getKeadaanTanah() != null)) {
            dan = laporanTanah.getKeadaanTanah();
        }
        perihaltanah = " Tanah ini ialah tanah " + kodmilik + " dan terletak di " + hklokasi + " lebih kurang " + jarak + " " + ujarak
                + " dari " + jarakd + " keadaan tanah adalah rata, kering dan " + dan;


        perihalpermohonan1 = " Tanah yang dipohon adalah seperti ditunjuk dengan garis sempadan warna merah dalam pelan yang berkembar. ";

        perihaltanah1 = " Tanah yang dipohon adalah tanah Kerajaan yang boleh diberimilik. ";


        String pnama = " ";
        if ((pemohon != null) && (pemohon.getPihak() != null)) {
            pnama = pemohon.getPihak().getNama();
        }

        String nopengalan = " ";
        if ((pemohon != null) && (pemohon.getPihak() != null)) {
            nopengalan = pemohon.getPihak().getNoPengenalan();
        }

        int umur = 0;
        if (pemohon != null) {
            umur = pemohon.getUmur();
        }

        BigDecimal gaji = BigDecimal.ZERO;
        if (pemohon != null) {
            gaji = pemohon.getPendapatan();
        }

        int tangun = 0;
        if (pemohon != null) {
            tangun = pemohon.getTanggungan();
        }

        String kerja = " ";
        if (pemohon != null) {
            kerja = pemohon.getPekerjaan();
        }

        perihalpemohon = " Pemohon ialah " + pnama + " No. KP: " + nopengalan + " adalah seorang Warganegara Malaysia.  Pemohon berumur " + umur
                + " tahun dan mempunyai " + tangun + " tanggungan.  Pekerjaan pemohon adalah sebagai " + kerja + " dengan pendapatan sebanyak RM "
                + gaji + " sebulan ";

        logger.info("------perihalpemohon--if (B)----" + perihalpemohon);

        String pihaknama = " ";
        if ((pihak != null) && (pihak.getNama() != null)) {
            pihaknama = pihak.getNama();
        }


        perihalpemohon_s = "Jika syarikat: Pemohon adalah syarikat " + pihaknama + " yang didaftarkan di Malaysia pada " + nopengalan
                + " Nombor pendaftaranya ialah " + " .. " + " Senerai Ahli Lembaga Pengarah adalah seperti berikut; " + '\n' + '\n';

        if ((pihakPengarah != null) && (listPengarah != null)) {
            for (PihakPengarah pp : listPengarah) {
                perihalpemohon_s = perihalpemohon_s + '\n' + pp.getNama() + "  " + " " + pp.getNoPengenalan();
            }
        }

        logger.info("------perihalpemohon--if (S)----" + perihalpemohon);

        int tahun = 0;
        if (pemohon != null && pemohon.getTempohMastautin() != null) {
            tahun = pemohon.getTempohMastautin();
        }

        perihalpemohon1 = " Pemohon tinggal di " + hklokasi + " semenjak " + tahun + " tahun yang lalu. Jarak tanah yang dipohon dengan tempat tinggal "
                + " pemohon ialah " + "  " + " Pemohon adalah anak tempatan di Kawasan tersebut. ";


        String phbngnama = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getNama() != null)) {
            phbngnama = pemohonHubungan.getNama();
        }

        String phbngno = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getNoPengenalan() != null)) {
            phbngno = pemohonHubungan.getNoPengenalan();
        }

        String phbngkerja = " ";
        if ((pemohonHubungan != null) && (pemohonHubungan.getPekerjaan() != null)) {
            phbngkerja = pemohonHubungan.getPekerjaan();
        }

        BigDecimal phbnggaji = BigDecimal.ZERO;
        if ((pemohonHubungan != null) && (pemohonHubungan.getGaji() != null)) {
            phbnggaji = pemohonHubungan.getGaji();
        }

        perihalpemohon2 = " Isteri pemohon " + phbngnama + " No. KP: " + phbngno + " adalah merupakan seorang (suri rumah/pegawai Kerajaan berjawatan "
                + phbngkerja + " dengan pendapatan sebanyak RM " + phbnggaji + " sebulan) ";


        perihalpemohon2suami = " Suami pemohon " + phbngnama + " No. KP: " + phbngno + " adalah merupakan seorang (suri rumah/pegawai Kerajaan berjawatan "
                + phbngkerja + " dengan pendapatan sebanyak RM " + phbnggaji + " sebulan) ";


        String cawangan = " ";
        if ((permohonan != null) && (permohonan.getCawangan() != null)) {
            cawangan = permohonan.getCawangan().getDaerah().getNama();
        }

        String agency = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getAgensi() != null)) {
            agency = permohonanRujukanLuar.getAgensi().getKategoriAgensi().getNama();
        }

        pentabirtanah1 = " Pentadbir Tanah Daerah " + cawangan + " setelah meneliti permohonan tersebut serta setelah mengambil kira ulasan Jabatan-Jabatan Teknikal "
                + " ( dan ulasan YB ADUN Kawasan " + agency + " ) " + " berpendapat permohonan ini boleh diluluskan kerana :- ";


        String pihakName = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakName = pemohon.getPihak().getNama();
        }

        String koduom1 = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
//            koduom1 = hakmilikPermohonan.getKodUnitLuas().getNama();
            koduom1 = hakmilikPermohonan.getKodUnitLuas().getNama();
            logger.info("----koduom1----" + koduom1);
        }

        String tajukktanah = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKategoriTanahBaru() != null)) {
            tajukktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
        }

        String tajuksbb = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
//            tajuksbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
            tajuksbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
        }


        tajuk = " PERMOHONAN DARIPADA  " + pihakName + " UNTUK " + ursan + " Pt/LOT " + noLot + " SELUAS " + luas + " " + koduom1 + " DI MUKIM/KAWASAN "
                + bpm + " DAERAH " + daerah + " UNTUK TUJUAN " + tajukktanah + " " + tajuksbb + ".";


        String ursanPlps = " ";
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            ursanPlps = "MENDUDUKI TANAH KERAJAAN SECARA PENDUDUKAN SEMENTARA" ;
            permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan) ;
            if(permitItem.getKodItemPermit() != null){
                tajuksbb = permitItem.getKodItemPermit().getNama() ;
            }
            else {
                tajuksbb = hakmilikPermohonan.getCatatan() ;
            }
        }
        if(noLot.isEmpty()) {
            noLot = "" ;
        }
        else {
            String a = noLot ;
            noLot = " PT/LOT " + a + " ";
        }


        tajukPlps = " PERMOHONAN DARIPADA  " + pihakName + " UNTUK " + ursanPlps + noLot + " SELUAS " + luas + " " + koduom1 + " DI MUKIM/KAWASAN "
                + bpm + " DAERAH " + daerah + " UNTUK TUJUAN " + tajuksbb + ".";


        String pnopng = " ";
        if ((pihak != null) && (pihak.getNoPengenalan() != null)) {
            pnopng = pihak.getNoPengenalan();
        }

        String hpnolot = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getNoLot() != null)) {
            hpnolot = hakmilikPermohonan.getNoLot();
        }

        BigDecimal luasterlibat = BigDecimal.ZERO;
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLuasTerlibat() != null)) {
            luasterlibat = hakmilikPermohonan.getLuasTerlibat();
        }

        String ursan2 = " ";
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan2 = "Memilik Tanah Kerajaan";
        }

        String koduom2 = " ";
        if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodUnitLuas() != null)) {
            koduom2 = hakmilikPermohonan.getKodUnitLuas().getNama();
        }

        pentabirtanah2 = " Pentadbir Tanah Daerah " + cawangan + " dengan ini memperakukan supaya permohonan daripada " + pihakName
                + " No. KP: " + pnopng + " untuk " + ursan2 + " di PT/Lot " + hpnolot + " , " + " seluas " + luasterlibat + " " + koduom2 + " di Mukim/Kawasan "
                + bpm + " , " + " Daerah " + cawangan + " seperti ditunjuk dengan garis sempadan berwarna merah dalam pelan berkembar, "
                + " di bawah Seksyen 42(1)a Kanun Tanah Negara dan didaftar atas nama pemohon dengan syarat-syarat seperti berikut:- ";


        String noruj = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getNoRujukan() != null)) {
            noruj = permohonanRujukanLuar.getNoRujukan();
        }

        String trhruj = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getTarikhRujukan() != null)) {
            trhruj = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
        }

        String lokasiagency = " ";
        if ((permohonanRujukanLuar != null) && (permohonanRujukanLuar.getLokasiAgensi() != null)) {
            lokasiagency = permohonanRujukanLuar.getLokasiAgensi();
        }

        ulasanyb = " Pentadbir Tanah Melaka Tengah telah meminta ulasan Y.B ADUAN Kawasan " + lokasiagency + " melalui surat bilangan PTMT: A4/ " + noruj
                + " bertarikh " + trhruj + " dan beliau tiada halangan serta menyokong penuh permohonan ini / tetapi tiada ulasan diterima. ";

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        if (permohonankertas != null) {
            for (int j = 0; j < permohonankertas.getSenaraiKandungan().size(); j++) {
                kertasK = permohonankertas.getSenaraiKandungan().get(j);

                if (kertasK.getBil() == 0) {
                    tajuk = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("1.1")) {
                    tujuan = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.1.1")) {
                    perihalpermohonan = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.1.2")) {
                    perihalpermohonan1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.2.1")) {
                    perihaltanah = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.2.2")) {
                    perihaltanah1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.1")) {
                    perihalpemohon = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.2")) {
                    perihalpemohon1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("2.3.3")) {
                    perihalpemohon2 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("4.1")) {
                    ulasanyb = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("5.1")) {
                    pentabirtanah1 = kertasK.getKandungan();
                } else if (kertasK.getSubtajuk() != null && kertasK.getSubtajuk().equals("5.2")) {
                    pentabirtanah2 = kertasK.getKandungan();
                }

            }
        }

        if (permohonankertas != null) {
            senaraiLaporanKandungan1 = pelupusanService.findByIdbysubtajuk(permohonankertas.getIdKertas(), 2);
            senaraiLaporanKandunganptg1 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 6);
            logger.info("-------senaraiLaporanKandunganptg1------" + senaraiLaporanKandunganptg1);
            senaraiLaporanKandunganptg2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 7);
            logger.info("-------senaraiLaporanKandunganptg2------" + senaraiLaporanKandunganptg2);
            senaraiLaporanKandunganpbtanah = pelupusanService.findBySubtajuk(permohonankertas.getIdKertas(), 5);
            logger.info("-------senaraiLaporanKandunganpbtanah------" + senaraiLaporanKandunganpbtanah);
        }

        // Mohon Rujulur
        senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        if (permohonankertas != null) {
            senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
            logger.info("------if-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        } else {
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiPermohonanRujLuar = pelupusanService.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
            logger.info("-----------senaraiPermohonanRujLuar-------:" + senaraiPermohonanRujLuar.size());
            if (!senaraiPermohonanRujLuar.isEmpty()) {
                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                    String kandVal = rujLuar.getUlasan();
                    if (rujLuar.getAgensi() != null) {
                        kandVal = rujLuar.getAgensi().getNama() + '\n' + kandVal;
                    }
                    if(rujLuar.getUlasan() == null){
                        kandVal = rujLuar.getAgensi().getNama()+'\n'+" Tiada ulasan diterima ";
                    }

                    kand1.setKandungan(kandVal);
                    if(rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK"))
                       senaraiLaporanKandungan2.add(kand1);
                }
            }


            logger.info("------else-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        }


        //Laporan Tanah

        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);

        // mohon tutukos
        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);

        // mohon permit item
        permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }


        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {

            logger.info("--------------PBMT-------------------");


            if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (kodHmlk != null) {
                KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
                hakmilikPermohonan.setKodHakmilik(khm);
            }
            if (kod != null) {
                KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
                hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
            }

            if (kodSktn != null) {
                KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
                hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
            }

            if (jenishakmilik != null) {
                KodHakmilik jenh = kodHakmilikDAO.findById(jenishakmilik);
                hakmilikPermohonan.setKodHakmilik(jenh);
            }
//        hakmilikPermohonan =pelupusanService.findByIdPermohonan(idPermohonan);
            logger.info("--------------pengguna-------------------" + pengguna);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setPermohonan(permohonan);
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        }



        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();

        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA";
        }

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA";
        }

        if (perihalpermohonan == null || perihalpermohonan.equals("")) {
            perihalpermohonan = "TIADA DATA";
        }

        if (perihalpermohonan1 == null || perihalpermohonan1.equals("")) {
            perihalpermohonan1 = "TIADA DATA";
        }

        if (perihaltanah == null || perihaltanah.equals("")) {
            perihaltanah = "TIADA DATA";
        }

        if (perihaltanah1 == null || perihaltanah1.equals("")) {
            perihaltanah1 = "TIADA DATA";
        }

        if (perihalpemohon == null || perihalpemohon.equals("")) {
            perihalpemohon = "TIADA DATA";
        }

        if (pentabirtanah1 == null || pentabirtanah1.equals("")) {
            pentabirtanah1 = "TIADA DATA";
        }

        if (pentabirtanah2 == null || pentabirtanah2.equals("")) {
            pentabirtanah2 = "TIADA DATA";
        }

        if (ulasanyb == null || ulasanyb.equals("")) {
            ulasanyb = "TIADA DATA";
        }

        if (perihalpemohon1 == null || perihalpemohon1.equals("")) {
            perihalpemohon1 = "TIADA DATA";
        }

        if (perihalpemohon2 == null || perihalpemohon2.equals("")) {
            perihalpemohon2 = "TIADA DATA";
        }

        logger.info("--------------tajuk-------------------" + tajuk);
        logger.info("--------------tujuan-------------------" + tujuan);
        logger.info("--------------perihalpermohonan-------------------" + perihalpermohonan);
        logger.info("--------------perihaltanah-------------------" + perihaltanah);
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(perihalpermohonan);
        listUlasan.add(perihalpermohonan1);
        listUlasan.add(perihaltanah);
        listUlasan.add(perihaltanah1);
        listUlasan.add(perihalpemohon);
        listUlasan.add(perihalpemohon1);
        listUlasan.add(perihalpemohon2);
        listUlasan.add(ulasanyb);
        listUlasan.add(pentabirtanah1);
        listUlasan.add(pentabirtanah2);


        listSubtajuk.add(" ");
        listSubtajuk.add("1.1");
        listSubtajuk.add("2.1.1");
        listSubtajuk.add("2.1.2");
        listSubtajuk.add("2.2.1");
        listSubtajuk.add("2.2.2");
        listSubtajuk.add("2.3.1");
        listSubtajuk.add("2.3.2");
        listSubtajuk.add("2.3.3");
        listSubtajuk.add("4.1");
        listSubtajuk.add("5.1");
        listSubtajuk.add("5.2");

        billNo.add("0");
        billNo.add("1");
        billNo.add("2");
        billNo.add("2");
        billNo.add("2");
        billNo.add("2");
        billNo.add("2");
        billNo.add("2");
        billNo.add("2");
        billNo.add("4");
        billNo.add("5");
        billNo.add("5");


        if (permohonankertas != null) {
            logger.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pelupusanService.simpanPermohonanKertas(permohonankertas);


            if (!permohonankertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonankertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonankertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 0) {
                        kertasKandungan.setKandungan(tajuk);
                    }
                    if (kertasKandungan.getSubtajuk().equals("1.1")) {
                        kertasKandungan.setKandungan(tujuan);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.1.1")) {
                        kertasKandungan.setKandungan(perihalpermohonan);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.1.2")) {
                        kertasKandungan.setKandungan(perihalpermohonan1);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.2.1")) {
                        kertasKandungan.setKandungan(perihaltanah);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.2.2")) {
                        kertasKandungan.setKandungan(perihaltanah1);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.3.1")) {
                        kertasKandungan.setKandungan(perihalpemohon);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.3.2")) {
                        kertasKandungan.setKandungan(perihalpemohon1);
                    }
                    if (kertasKandungan.getSubtajuk().equals("2.3.3")) {
                        kertasKandungan.setKandungan(perihalpemohon2);
                    }
                    if (kertasKandungan.getSubtajuk().equals("4.1")) {
                        kertasKandungan.setKandungan(ulasanyb);
                    }
                    if (kertasKandungan.getSubtajuk().equals("5.1")) {
                        kertasKandungan.setKandungan(pentabirtanah1);
                    }
                    if (kertasKandungan.getSubtajuk().equals("5.2")) {
                        kertasKandungan.setKandungan(pentabirtanah2);
                    }
                    kertasKandungan.setInfoAudit(infoAudit);
                    pelupusanService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }
        } else {
            logger.info("------else------permohonankertas:: NULL--------------::" + permohonankertas);

            permohonankertas = new PermohonanKertas();
            permohonankertas.setInfoAudit(infoAudit);
            permohonankertas.setCawangan(pengguna.getKodCawangan());
            permohonankertas.setPermohonan(permohonan);
            permohonankertas.setTajuk("KERTAS MMKN");
            permohonankertas.setKodDokumen(kodDokumenDAO.findById("RMN"));
            pelupusanService.simpanPermohonanKertas(permohonankertas);

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                logger.info("---------ulasan-----------::" + ulasan);
                String billNo1 = billNo.get(i);
                String subTajuk = listSubtajuk.get(i);
                logger.info("---------subTajuk-----------::" + subTajuk);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                kertasKandungan.setKertas(permohonankertas);
                // kertasKdgn.setBil(i + 1);
                kertasKandungan.setBil(Integer.parseInt(billNo1));
                kertasKandungan.setSubtajuk(subTajuk);
                kertasKandungan.setInfoAudit(infoAudit);
                kertasKandungan.setKandungan(ulasan);
                kertasKandungan.setCawangan(pengguna.getKodCawangan());
                pelupusanService.simpanPermohonanKertasKandungan(kertasKandungan);
            }

        }

        senaraiLaporanKandungan1 = pelupusanService.findByIdbysubtajuk(permohonankertas.getIdKertas(), 2);
        logger.info("---------senaraiLaporanKandungan1--------:" + senaraiLaporanKandungan1);
        logger.info("---------senaraiLaporanKandungan1--size------:" + senaraiLaporanKandungan1.size());

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        logger.info("---------kira--------:" + kira);
        InfoAudit iaP = new InfoAudit();
        for (int i = 3; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
                logger.info("---------if--------:");
                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
                logger.info("---------id--------:" + id);
                if (id != null) {
                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setDiKemaskiniOleh(pengguna);
                    iaP.setTarikhKemaskini(new java.util.Date());

                    logger.info("---------Bil--------:" + permohonanKertasKandungan1.getBil());
                    logger.info("------SubTajuk-----------:" + permohonanKertasKandungan1.getSubtajuk());
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonankertas);
            logger.info("---------i--------:" + i);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            logger.info("---------kandungan--------:" + kandungan);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            logger.info("---------Bil-2-------:" + permohonanKertasKandungan1.getBil());
            logger.info("------SubTajuk2-----------:" + permohonanKertasKandungan1.getSubtajuk());
            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

        }

        senaraiLaporanKandunganpbtanah = pelupusanService.findByIdbysubtajuk(permohonankertas.getIdKertas(), 5);
        logger.info("---------senaraiLaporanKandunganpbtanah--------:" + senaraiLaporanKandunganpbtanah);
        logger.info("---------senaraiLaporanKandunganpbtanah--size------:" + senaraiLaporanKandunganpbtanah.size());

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        logger.info("---------kira--------:" + kira);
        //InfoAudit iaP = new InfoAudit();
        for (int i = 2; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandunganpbtanah.size() != 0 && i <= senaraiLaporanKandunganpbtanah.size()) {
                logger.info("---------if--------:");
                Long id = senaraiLaporanKandunganpbtanah.get(i - 1).getIdKandungan();
                logger.info("---------id--------:" + id);
                if (id != null) {
                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setDiKemaskiniOleh(pengguna);
                    iaP.setTarikhKemaskini(new java.util.Date());

                    logger.info("---------Bil--------:" + permohonanKertasKandungan1.getBil());
                    logger.info("------SubTajuk-----------:" + permohonanKertasKandungan1.getSubtajuk());
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonankertas);
            logger.info("---------i--------:" + i);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("kandungan5" + i);
            logger.info("---------kandungan--------:" + kandungan);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5.1." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            logger.info("---------Bil-2-------:" + permohonanKertasKandungan1.getBil());
            logger.info("------SubTajuk2-----------:" + permohonanKertasKandungan1.getSubtajuk());
            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);

        }



        //added for Mohon_rujulur

        senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan2.size() != 0 && i <= senaraiLaporanKandungan2.size()) {
                Long id = senaraiLaporanKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonankertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("ulasan" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            //InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(pengguna);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }



        if (kodHmlk != null) {
            KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
            hakmilikPermohonan.setKodHakmilik(khm);
        }

        if (kod != null) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
        }

        if (kodSktn != null) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
        }

        if (jenishakmilik != null) {
            KodHakmilik jenh = kodHakmilikDAO.findById(jenishakmilik);
            hakmilikPermohonan.setKodHakmilik(jenh);
        }
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
        // fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kodKepu));
        fasaPermohonan.setInfoAudit(info);
        fasaPermohonan.setCawangan(pengguna.getKodCawangan());
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);
        pelupusanService.simpanPermohonan(permohonan);


        //STRAT: FOR LPS

        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {

            logger.info("--------------LPS-------------------");

            // Mohon Hakmilik

            logger.info("--------------LPS hakmilikPermohonan Saving::-------------------");
            if (hakmilikPermohonan != null) {
                infoAudit = hakmilikPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                hakmilikPermohonan = new HakmilikPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setPermohonan(permohonan);
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

            //Mohon Tuntukos

            logger.info("--------------LPS PermohonanTuntutanKos Saving::-------------------");

            PermohonanTuntutanKos permohonanTuntutanKosTemp = pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);

            logger.info("--------------LPS permohonanTuntutanKosTemp ::-------------------:" + permohonanTuntutanKosTemp);

            if (permohonanTuntutanKosTemp != null) {
                logger.info("--------------LPS permohonanTuntutanKosTemp Not Null ::-------------------:");
                infoAudit = permohonanTuntutanKosTemp.getInfoAudit();
                logger.info("--------------LPS infoAudit ::-------------------:" + infoAudit);
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                logger.info("--------------LPS permohonanTuntutanKosTemp Null ::-------------------:");
                permohonanTuntutanKosTemp = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            logger.info("--------------pengguna-------------------" + pengguna);
            permohonanTuntutanKosTemp.setInfoAudit(infoAudit);
            permohonanTuntutanKosTemp.setPermohonan(permohonan);
            permohonanTuntutanKosTemp.setCawangan(pengguna.getKodCawangan());
            permohonanTuntutanKosTemp.setKodTransaksi(kodTuntutDAO.findById("DISB4A").getKodKewTrans());
            permohonanTuntutanKosTemp.setItem(kodTuntutDAO.findById("DISB4A").getNama());
            permohonanTuntutanKosTemp.setKodTuntut(kodTuntutDAO.findById("DISB4A")) ;
            permohonanTuntutanKosTemp.setHakmilikPermohonan(hakmilikPermohonan);
            permohonanTuntutanKosTemp.setAmaunTuntutan(permohonanTuntutanKos.getAmaunTuntutan());
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKosTemp);


            // Mohon Permit Item

            logger.info("--------------LPS permohonanPermitItem Saving::-------------------");
            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
            KodItemPermit kitem = new KodItemPermit();
            kitem.setKod(kodPermit);
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
            permohonanPermitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (permohonanPermitItem != null) {
                 logger.info("--------------LPS permohonanPermitItem  NOT Null::-------------------");

                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                permohonanPermitItem.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(permohonanPermitItem);

            } else {
                 logger.info("--------------LPS permohonanPermitItem Null::-------------------");
                permohonanPermitItem = new PermohonanPermitItem();
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(pengguna.getKodCawangan());
                permohonanPermitItem.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(permohonanPermitItem);
            }
        }
senaraiLaporanKandunganpbtanah = pelupusanService.findBySubtajuk(permohonankertas.getIdKertas(), 5);
        // END : LPS
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        rehydrate();

 return new ForwardResolution("/WEB-INF/jsp/pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");
    }

    // saving PTG
    public Resolution simpanPTG() {


        logger.info("------------Simpan Hurian started-----------::");


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");

        logger.info("-------Simpan---PTG--in--permohonankertas--------------::");


        if (permohonankertas != null) {
            logger.info("------if------permohonankertas NOT Null--------------::" + permohonankertas);
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pelupusanService.simpanPermohonanKertas(permohonankertas);


            senaraiLaporanKandunganptg1 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 6);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganptg1.size() != 0 && i <= senaraiLaporanKandunganptg1.size()) {
                    Long id = senaraiLaporanKandunganptg1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(6);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("6." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonankertas);
                String kandungan = getContext().getRequest().getParameter("kandungan2" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }


            senaraiLaporanKandunganptg2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 7);

            kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganptg2.size() != 0 && i <= senaraiLaporanKandunganptg2.size()) {
                    Long id = senaraiLaporanKandunganptg2.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(7);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("7." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonankertas);
                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/draf_mmkn_ptd.jsp").addParameter("tab", "true");

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

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getHuraianPentadbir2() {
        return huraianPentadbir2;
    }

    public void setHuraianPentadbir2(String huraianPentadbir2) {
        this.huraianPentadbir2 = huraianPentadbir2;
    }

    public String getHuraianPentadbir3() {
        return huraianPentadbir3;
    }

    public void setHuraianPentadbir3(String huraianPentadbir3) {
        this.huraianPentadbir3 = huraianPentadbir3;
    }

    public String getHuraianPentadbir4() {
        return huraianPentadbir4;
    }

    public void setHuraianPentadbir4(String huraianPentadbir4) {
        this.huraianPentadbir4 = huraianPentadbir4;
    }

    public String getHuraianPentadbir5() {
        return huraianPentadbir5;
    }

    public void setHuraianPentadbir5(String huraianPentadbir5) {
        this.huraianPentadbir5 = huraianPentadbir5;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public String getHuraianPtg2() {
        return huraianPtg2;
    }

    public void setHuraianPtg2(String huraianPtg2) {
        this.huraianPtg2 = huraianPtg2;
    }

    public String getHuraianPtg3() {
        return huraianPtg3;
    }

    public void setHuraianPtg3(String huraianPtg3) {
        this.huraianPtg3 = huraianPtg3;
    }

    public String getHuraianPtg4() {
        return huraianPtg4;
    }

    public void setHuraianPtg4(String huraianPtg4) {
        this.huraianPtg4 = huraianPtg4;
    }

    public String getHuraianPtg5() {
        return huraianPtg5;
    }

    public void setHuraianPtg5(String huraianPtg5) {
        this.huraianPtg5 = huraianPtg5;
    }

    public String getSyorPtg2() {
        return syorPtg2;
    }

    public void setSyorPtg2(String syorPtg2) {
        this.syorPtg2 = syorPtg2;
    }

    public String getSyorPtg3() {
        return syorPtg3;
    }

    public void setSyorPtg3(String syorPtg3) {
        this.syorPtg3 = syorPtg3;
    }

    public String getSyorPtg4() {
        return syorPtg4;
    }

    public void setSyorPtg4(String syorPtg4) {
        this.syorPtg4 = syorPtg4;
    }

    public String getSyorPtg5() {
        return syorPtg5;
    }

    public void setSyorPtg5(String syorPtg5) {
        this.syorPtg5 = syorPtg5;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getSyorPentadbir2() {
        return syorPentadbir2;
    }

    public void setSyorPentadbir2(String syorPentadbir2) {
        this.syorPentadbir2 = syorPentadbir2;
    }

    public String getSyorPentadbir3() {
        return syorPentadbir3;
    }

    public void setSyorPentadbir3(String syorPentadbir3) {
        this.syorPentadbir3 = syorPentadbir3;
    }

    public String getSyorPentadbir4() {
        return syorPentadbir4;
    }

    public void setSyorPentadbir4(String syorPentadbir4) {
        this.syorPentadbir4 = syorPentadbir4;
    }

    public String getSyorPentadbir5() {
        return syorPentadbir5;
    }

    public void setSyorPentadbir5(String syorPentadbir5) {
        this.syorPentadbir5 = syorPentadbir5;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKodKepu() {
        return kodKepu;
    }

    public void setKodKepu(String kodKepu) {
        this.kodKepu = kodKepu;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getPerihalpermohonan() {
        return perihalpermohonan;
    }

    public void setPerihalpermohonan(String perihalpermohonan) {
        this.perihalpermohonan = perihalpermohonan;
    }

    public String getPerihaltanah() {
        return perihaltanah;
    }

    public void setPerihaltanah(String perihaltanah) {
        this.perihaltanah = perihaltanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public String getPerihalpermohonan1() {
        return perihalpermohonan1;
    }

    public void setPerihalpermohonan1(String perihalpermohonan1) {
        this.perihalpermohonan1 = perihalpermohonan1;
    }

    public String getPerihaltanah1() {
        return perihaltanah1;
    }

    public void setPerihaltanah1(String perihaltanah1) {
        this.perihaltanah1 = perihaltanah1;
    }

    public String getPerihalpemohon() {
        return perihalpemohon;
    }

    public void setPerihalpemohon(String perihalpemohon) {
        this.perihalpemohon = perihalpemohon;
    }

    public String getPentabirtanah1() {
        return pentabirtanah1;
    }

    public void setPentabirtanah1(String pentabirtanah1) {
        this.pentabirtanah1 = pentabirtanah1;
    }

    public String getPentabirtanah2() {
        return pentabirtanah2;
    }

    public void setPentabirtanah2(String pentabirtanah2) {
        this.pentabirtanah2 = pentabirtanah2;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan3() {
        return senaraiLaporanKandungan3;
    }

    public void setSenaraiLaporanKandungan3(List<PermohonanKertasKandungan> senaraiLaporanKandungan3) {
        this.senaraiLaporanKandungan3 = senaraiLaporanKandungan3;
    }

    public String getUlasanyb() {
        return ulasanyb;
    }

    public void setUlasanyb(String ulasanyb) {
        this.ulasanyb = ulasanyb;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg1() {
        return senaraiLaporanKandunganptg1;
    }

    public void setSenaraiLaporanKandunganptg1(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1) {
        this.senaraiLaporanKandunganptg1 = senaraiLaporanKandunganptg1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg2() {
        return senaraiLaporanKandunganptg2;
    }

    public void setSenaraiLaporanKandunganptg2(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2) {
        this.senaraiLaporanKandunganptg2 = senaraiLaporanKandunganptg2;
    }

    public String getHurianpengarah() {
        return hurianpengarah;
    }

    public void setHurianpengarah(String hurianpengarah) {
        this.hurianpengarah = hurianpengarah;
    }

    public String getSyorpengarah() {
        return syorpengarah;
    }

    public void setSyorpengarah(String syorpengarah) {
        this.syorpengarah = syorpengarah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganpbtanah() {
        return senaraiLaporanKandunganpbtanah;
    }

    public void setSenaraiLaporanKandunganpbtanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah) {
        this.senaraiLaporanKandunganpbtanah = senaraiLaporanKandunganpbtanah;
    }

    public String getPerihalpemohon1() {
        return perihalpemohon1;
    }

    public void setPerihalpemohon1(String perihalpemohon1) {
        this.perihalpemohon1 = perihalpemohon1;
    }

    public String getPerihalpemohon2() {
        return perihalpemohon2;
    }

    public void setPerihalpemohon2(String perihalpemohon2) {
        this.perihalpemohon2 = perihalpemohon2;
    }

    public String getPerihalpemohon_s() {
        return perihalpemohon_s;
    }

    public void setPerihalpemohon_s(String perihalpemohon_s) {
        this.perihalpemohon_s = perihalpemohon_s;
    }

    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public List<KodKadarPremium> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<KodKadarPremium> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getTajukPlps() {
        return tajukPlps;
    }

    public void setTajukPlps(String tajukPlps) {
        this.tajukPlps = tajukPlps;
    }

    public String getTujuanPlps() {
        return tujuanPlps;
    }

    public void setTujuanPlps(String tujuanPlps) {
        this.tujuanPlps = tujuanPlps;
    }

    public String getPerihalpermohonanPlps() {
        return perihalpermohonanPlps;
    }

    public void setPerihalpermohonanPlps(String perihalpermohonanPlps) {
        this.perihalpermohonanPlps = perihalpermohonanPlps;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public String getPerihalpemohon2suami() {
        return perihalpemohon2suami;
    }

    public void setPerihalpemohon2suami(String perihalpemohon2suami) {
        this.perihalpemohon2suami = perihalpemohon2suami;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
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

    public PermohonanPermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermohonanPermitItem permitItem) {
        this.permitItem = permitItem;
    }

}
