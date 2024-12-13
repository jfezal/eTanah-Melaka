/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Author : Shazwan 15/6/2011
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodWarnaKPDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodWarnaKP;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
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
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 * Modified by Shazwan
 */
@UrlBinding("/pelupusan/draf_jkbb_pjbtr")
public class DrafJKBB_PJBTRActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodWarnaKPDAO kodWarnaDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    Logger logger = Logger.getLogger(DrafMMKNActionBean.class);
    @Inject
    BPelService service;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    private Permohonan permohonan;
    private Pemohon pemohon;
//    private PermohonanKertas permohonankertas;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PemohonHubungan pemohonHubungan;
    private PermohonanKertas permohonankertas;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2 = new Vector();
    private List<PihakPengarah> listPengarah;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal = new Vector();
    private List<PermohonanRujukanLuar> senaraiUlasanJKBB = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanAsasPertimbangan = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganButirTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganLokasiTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPegawaiTertinggi = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG = new Vector();
//    private List<PermohonanKertasKandungan> senaraiLaporanAsasPertimbangan = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanPerihalTanah = new Vector();
//    private List<PermohonanKertasKandungan> senaraiLaporanKandunganButirTanah = new Vector();
//    private List<PermohonanKertasKandungan> senaraiLaporanKandunganLokasiTanah = new Vector();
//    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD = new Vector();
//    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG = new Vector();
//    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal = new Vector();
//    private List<PermohonanRujukanLuar> senaraiUlasanJKBB = new Vector();
//    private List<PermohonanBahanBatuan> senaraiBahanBatu;
    private String subTajukPopUp;
    private String kandunganPopUp;
    private String tajukHeader;
    private String tajukMainDraf;
    private String tajukTujuanDraf;
    private String tajukPerihalPermohonan;
    private String tajukPerihalPemohon;
    private String tajukPerihalTanah;
    private String tajukUlasanJabatan;
    private String tajukUlasanJKBB;
    private String tajukPerakuanPTD;
    private String tajukPerakuanPTG;
    private String tajukAsasTimbang;
    private String stageId;
    private String drafDaerah;
    private boolean openPTD = false;
    private boolean openPTG = false;
    private boolean viewOnlyPTD = true;
    private boolean viewOnlyPTDOnly = true;
    private boolean viewOnlyPTG = true;
    private boolean viewOnlyPTGOnly = true;
    private String perakuanPTD;
    private String stageStatus;
    private String urusanStatus;
    private String kodNeg;
    private PermohonanBahanBatuan syaratBahanBatu;
    private Pengguna peng;
    private String idPermohonan;
    private String idPemohon;
    private Long x;
    private String jenisTanah;
    private String kuantitTanah;
    private String tuanTanah;
    private String tempohTanah;
    private String tempatTanah;
    private String adunTanah;
    private String asalTanah;
    private String jarakTanah;
    private String jenisJalan;
    private String tuanTanahAsal;
    private String alamatTuanTanah;
    private String alamatTanah;
    private String idSyarikatTuanTanah;
    private String tarikhPermohonan;
    private String mukim;
    private String tujuanTanah;
    private String kelulusan;
    private String syarat;
    private String noktah;
    private String noktahbertindih;
    private String meterTanah;
    private String jumlahKeneBayar;
    private double cagarKeneBayar;
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
    private Boolean edit;
    //add for bayaran kupon
    private double kuponAmaun;
    private int kuponQty;
    private BigDecimal kupon;
    private BigDecimal cagarJalan;
    private BigDecimal totalAll;
    private PermohonanTuntutanKos mohonTuntutKos;
    private FasaPermohonan fasaPermohonan; //Add for filtering keputusan sokong and tidak disokong
    private String kpsn;

    @DefaultHandler
    public Resolution showForm1() {
        edit = Boolean.FALSE;
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }
//    @DefaultHandler

    public Resolution showForm2() {
        edit = Boolean.TRUE;
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
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

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPemohon = String.valueOf(x);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        //For keputusan
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "laporan_tanah");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan() != null) {
                kpsn = fasaPermohonan.getKeputusan().getKod();
            }
        }

        stageId = stageId(taskId);
//        stageId = "semak_JKBB_TPTG";
        kodNeg = conf.getProperty("kodNegeri");
        String namaNegeri = new String();
        if (kodNeg.equals("04")) {
            namaNegeri = "Melaka";
        }
        if (kodNeg.equals("05")) {
            namaNegeri = "Negeri Sembilan";
        }
        logger.info("THIS IS IDPERMOHONAN = " + idPermohonan);


        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            settingDefaultValue();
            /*
             * CHECKING FOR STAGE AND STATUS TO BE USED IN JSP
             * FOR DISPLAY DAERAH IN JSP AND ON OFF FOR PERAKUAN
             */
//            stageId = "sedia_rencana_syor";
            drafDaerah = permohonan.getCawangan().getName();
            urusanStatus = permohonan.getKodUrusan().getKod();
            if (urusanStatus.equals("PJBTR")) {
                tajukHeader = "DRAF RENCANA UNTUK PERTIMBANGAN MESYUARAT JAWATANKUASA BELAH BAHAGI NEGERI " + namaNegeri;
                if (stageId.equals("sedia_draf_JKBB") || stageId.equals("semak_draf_JKBB_PTD") || stageId.equals("perakuan_draf_JKBB_PTD")) {
                    if (stageId.equals("sedia_draf_JKBB") || stageId.equals("semak_draf_JKBB_PTD")) {
                        viewOnlyPTD = false;
                    }
                    if (stageId.equals("perakuan_draf_JKBB_PTD")) {
                        viewOnlyPTD = true;
                        viewOnlyPTDOnly = false;
                    } else {
                        viewOnlyPTD = true;
                    }
                    openPTD = true;
                    openPTG = false;
                }
                if (stageId.equals("maklum_arahan_JKBB_PTG") || stageId.equals("semak_JKBB_PTG") || stageId.equals("maklum_agihan_JKBB") || stageId.equals("semak_JKBB_PTG") || stageId.equals("maklumat_tambahan_JKBB") || stageId.equals("semak_masuk_bil_kertas_JKBB") || stageId.equals("semak_JKBB_TPTG")) {
                    if (stageId.equals("maklum_arahan_JKBB_PTG") || stageId.equals("maklum_agihan_JKBB") || stageId.equals("semak_masuk_bil_kertas_JKBB")) {
//                        viewOnlyPTG = false;
                        viewOnlyPTG = true;
                        viewOnlyPTGOnly = false;
                    } else {
                        viewOnlyPTG = true;
                    }

                    openPTD = true;
                    openPTG = true;
                }

//                if (stageId.equals("maklum_agihan_JKBB") || stageId.equals("maklum_arahan_JKBB_PTG") || stageId.equals("semak_JKBB_TPTG") || stageId.equals("semak_JKBB_PTG") || stageId.equals("maklumat_tambahan_JKBB") || stageId.equals("semak_masuk_bil_kertas_JKBB") || stageId.equals("rekod_keputusan_JKBB")) {
//                    if (stageId.equals("maklum_agihan_JKBB") || stageId.equals("semak_JKBB_TPTG") || stageId.equals("semak_JKBB_PTG") || stageId.equals("semak_masuk_bil_kertas_JKBB")) {
////                        viewOnlyPTG = false;
//                        viewOnlyPTG = true;
//                        viewOnlyPTGOnly = false;
//                    } else {
//                        viewOnlyPTG = true;
//                    }
//
//                    openPTD = true;
//                    openPTG = true;
//                }
            }

            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            senaraiUlasanJabatanTeknikal = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
            senaraiUlasanJKBB = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
            permohonankertas = new PermohonanKertas();
            permohonankertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
//            logger.info(permohonankertas.getKodDokumen().getKod());
            settingDefaultValue();
            if (senaraiUlasanJabatanTeknikal.size() > 0) {
                for (int i = 0; i < senaraiUlasanJabatanTeknikal.size(); i++) {
                    PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
                    mohonRujuk = senaraiUlasanJabatanTeknikal.get(i);
                    if (mohonRujuk.getUlasan() == null) {
                        mohonRujuk.setUlasan("Tiada Ulasan Diterima");
                    }
                    pelupusanService.simpanPermohonanRujLuar(mohonRujuk);
                }
            }
            /*
             * ULASAN JKBB
             */
            if (senaraiUlasanJKBB.size() > 0) {
                for (int i = 0; i < senaraiUlasanJKBB.size(); i++) {
                    PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
                    mohonRujuk = senaraiUlasanJKBB.get(i);
//                            mohonRujuk.setUlasan(ulasanAgensi);
                    if (mohonRujuk.getUlasan() == null) {
                        mohonRujuk.setUlasan("Tiada Ulasan Diterima");
                    }
                    pelupusanService.simpanPermohonanRujLuar(mohonRujuk);
                }
            }



            tajukUlasanJabatan = "Pentadbir Tanah Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah merujukkan permohonan ini kepada Jabatan-Jabatan Teknikal berkenaan dan ulasannya adalah seperti berikut:-";
            String adunJKBB = "";
            if (permohonan.getKodUrusan().getKod().equals("PJBTR")) {
                adunJKBB = "JawatanKuasa Belah Bahagi berkenaan";
            } else {
                adunJKBB = "ADUN berkenaan";
            }
            if (senaraiUlasanJKBB.size() > 0) {
                tajukUlasanJKBB = "Pentadbir Tanah Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah merujukkan permohonan ini kepada " + adunJKBB + " dan ulasannya adalah seperti berikut:-";
            } else {
                tajukUlasanJKBB = "Permohonan ini tidak memerlukan ulasan daripada YB Adun.";
            }

            if (permohonankertas != null) {
//                senaraiLaporanAsasPertimbangan = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 5);
//                senaraiLaporanKandunganPerihalTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 22);
//                senaraiLaporanKandunganButirTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 24);
//                senaraiLaporanKandunganLokasiTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 25);
//                senaraiLaporanKandunganPerakuanPTD = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 6);
//                senaraiLaporanKandunganPerakuanPTG = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 7);
//                senaraiLaporanKandunganPerakuanPegawaiTertinggi = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 10);

                senaraiLaporanAsasPertimbangan = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 5);
                senaraiLaporanPerihalTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 22);
                senaraiLaporanKandunganButirTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 24);
                senaraiLaporanKandunganLokasiTanah = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 25);
                senaraiLaporanKandunganPerakuanPTD = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 6);
                senaraiLaporanKandunganPerakuanPTG = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 7);

            } else {
//                senaraiLaporanKandunganPerihalTanah = new Vector();
//                senaraiLaporanPerihalTanah = new Vector();
//                senaraiLaporanKandunganButirTanah = new Vector();
//                senaraiLaporanKandunganLokasiTanah = new Vector();
//                senaraiLaporanAsasPertimbangan = new Vector();
//                senaraiLaporanKandunganPerakuanPTD = new Vector();
//                senaraiLaporanKandunganPerakuanPTG = new Vector();
//                senaraiLaporanKandunganPerakuanPegawaiTertinggi = new Vector();

                senaraiLaporanPerihalTanah = new Vector();
                senaraiLaporanKandunganButirTanah = new Vector();
                senaraiLaporanKandunganLokasiTanah = new Vector();
                senaraiLaporanAsasPertimbangan = new Vector();
                senaraiLaporanKandunganPerakuanPTD = new Vector();
                senaraiLaporanKandunganPerakuanPTG = new Vector();
            }
            if (permohonankertas != null) {

                boolean checkExistBil0 = false;
                boolean checkExistBil1 = false;
                boolean checkExistSubTajuk211 = false;
                boolean checkExistSubTajuk221 = false;
                boolean checkExistSubTajuk231 = false;
                boolean checkExistBil5 = false;
                boolean checkExistBil6 = false;
                boolean checkExistBil7 = false;
                List<PermohonanKertasKandungan> vecMohonKertas;
                vecMohonKertas = pelupusanService.findByIdKertasOnly(permohonankertas.getIdKertas());
                if (vecMohonKertas.size() > 0) {
                    for (int i = 0; i < vecMohonKertas.size(); i++) {
                        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
                        pkk = vecMohonKertas.get(i);
                        switch (pkk.getBil()) {
                            case (0):
                                tajukMainDraf = pkk.getKandungan();
                                checkExistBil0 = true;
                                break;
                            case (1):
                                tajukTujuanDraf = pkk.getKandungan();
                                checkExistBil1 = true;
                                break;
                            case (2):
                                if (pkk.getSubtajuk().equals("1")) {
                                    tajukPerihalPermohonan = pkk.getKandungan();
                                    checkExistSubTajuk211 = true;
                                }
                                if (pkk.getSubtajuk().equals("2")) {
                                    tajukPerihalPemohon = pkk.getKandungan();
                                    checkExistSubTajuk221 = true;
                                }
//                                if (pkk.getSubtajuk().equals("3")) {
//                                    tajukPerihalTanah = pkk.getKandungan();
//                                    checkExistSubTajuk231 = true;
//                                }
                                break;
                            case (5):
                                if (pkk.getSubtajuk().equals("5.1")) {
                                    tajukAsasTimbang = pkk.getKandungan();
                                    checkExistBil5 = true;
                                }
                                break;
                            case (6):
                                if (pkk.getSubtajuk().equals("6.1")) {
                                    tajukPerakuanPTD = pkk.getKandungan();
                                    checkExistBil6 = true;
                                }
                                break;
                            case (7):
                                if (pkk.getSubtajuk().equals("7.1")) {
                                    tajukPerakuanPTG = pkk.getKandungan();
                                    checkExistBil7 = true;
                                }
                                break;
                        }
                    }

                    if (!checkExistBil0) {
                        settingBil0();
                    }
                    if (!checkExistBil1) {
                        settingBil1();
                    }
                    if (!checkExistSubTajuk211) {
                        settingBil211();
                    }
                    if (!checkExistSubTajuk221) {
                        settingBil221();
                    }
                    if (!checkExistSubTajuk231) {
                        settingBil231();
                    }

                } else {
                    settingBil0();
                    settingBil1();
                    settingBil211();
                    settingBil221();
                    settingBil231();
                }

            } else {

                settingBil0();
                settingBil1();
                settingBil211();
                settingBil221();
                settingBil231();
                SimpandrafJKBB() ;
            }
        }
        if (pemohon != null) {
            pemohonHubungan = pelupusanService.findHubunganByIDIsteri(pemohon.getIdPemohon());
        }
        if (pemohon != null) {
            listPengarah = pelupusanService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
        }
            mohonTuntutKos = new PermohonanTuntutanKos() ;
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISSTR");
//        if (kpsn.equals("SL")) {
//            PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
//            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
//            if (mohonTuntutKos != null) {
//                kuponQty = mohonTuntutKos.getKuantiti();
//                kuponAmaun = 50.00;
//                kupon = BigDecimal.valueOf(Double.parseDouble(String.valueOf(kuponQty)) * kuponAmaun);
//
//            } else {
//                kuponAmaun = 50.00;
//            }
//
//            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
//            if (mohonTuntutKos != null) {
//                cagarJalan = mohonTuntutKos.getAmaunTuntutan();
//
//            }
//            //JUMLAH KESELURUHAN
//            if (StringUtils.isEmpty(String.valueOf(cagarKeneBayar))) {
//                cagarKeneBayar = 0.00;
//            }
//            if (cagarJalan != null) {
//                if (StringUtils.isEmpty(cagarJalan.toString())) {
//                    cagarJalan = new BigDecimal(0);
//                }
//            } else {
//                cagarJalan = new BigDecimal(0);
//            }
//            if (kupon != null) {
//                if (StringUtils.isEmpty(kupon.toString())) {
//                    kupon = new BigDecimal(0);
//                }
//            } else {
//                kupon = new BigDecimal(0);
//            }
//
//            BigDecimal cagaran = new BigDecimal(0);
////                double percentDouble = 20/100;
////                BigDecimal percent = BigDecimal.valueOf(percentDouble);
//            cagaran = BigDecimal.valueOf(cagarKeneBayar);
//            totalAll = cagarJalan.add(BigDecimal.valueOf(Double.parseDouble(jumlahKeneBayar))).add(kupon).add(cagaran);
//        }

    }

//    public void settingSyarat() {
//    }
    public void settingBil0() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =0; 
         */
//        String permitItem = new String();
//        String jenisBahanBatu = new String();
//        for (int i = 0; i < senaraiMohonPermitItem.size(); i++) {
//            PermohonanPermitItem mohonPermitItem = new PermohonanPermitItem();
//            mohonPermitItem = (PermohonanPermitItem) senaraiMohonPermitItem.get(i);
//            if (senaraiMohonPermitItem.size() == 1) {
//                permitItem = mohonPermitItem.getKodItemPermit().getNama();
//            } else {
//                if (i != senaraiMohonPermitItem.size() - 1) {
//                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " / ";
//                } else {
//                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " iaitu ";
//                }
//            }
//        }
//
//        PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
//        if (senaraiMohonPermitItem.size() == 1) {
//            jenisBahanBatu = mohonBahanBatuan.getJenisBahanBatu().getNama();
//        } else {
//            jenisBahanBatu = jenisBahanBatu + mohonBahanBatuan.getJenisBahanBatu().getNama() + " ";
//        }
        if (hakmilikPermohonan.getNoLot() != null) {
            tajuk4 = tajuk4 + " " + hakmilikPermohonan.getNoLot();
        } else {
            tajuk4 = "";
        }
//        tajukMainDraf = tajuk + " DARIPADA " + pemohon.getPihak().getNama() + tajuk4 + " " + hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama()
//                + " DAERAH " + permohonan.getCawangan().getDaerah().getNama() + noktah;

        tajukMainDraf = tajuk + " DARIPADA " + pemohon.getPihak().getNama() + tajuk4 + " "
                + hakmilikPermohonan.getBandarPekanMukimBaru().getNama() + " DAERAH " + permohonan.getCawangan().getDaerah().getNama() + noktah;
    }

    public void settingBil1() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =1; 
         */
        System.out.println("pihak name ::" + pemohon.getPihak().getNama());
        System.out.println("hakmilik permohonan : bandar pekan mukim ::" + hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
        System.out.println("permohonan :: kod cawangan" + permohonan.getCawangan().getDaerah().getNama());
        tajukTujuanDraf = tujuan + tujuan2 + " " + " daripada " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + noktah;

//        String permitItem = new String();
//        String jenisBahanBatu = new String();
//        PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
//        if (senaraiMohonPermitItem.size() == 1) {
//            jenisBahanBatu = pelUtiliti.convertStringtoInitCap(mohonBahanBatuan.getJenisBahanBatu().getNama());
//        } else {
//            jenisBahanBatu = jenisBahanBatu + pelUtiliti.convertStringtoInitCap(mohonBahanBatuan.getJenisBahanBatu().getNama()) + " ";
//        }
//        if (kpsn.equals("SL")) {
//            String tempohUnit = new String();
//            tempohUnit = mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("B") ? "Bulan" : mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("T") ? "Tahun" : "Hari";
//            tajukTujuanDraf = tujuan + tujuan2 + jenisBahanBatu + tujuan3 + mohonBahanBatuan.getJumlahIsipadu() + " " + pelUtiliti.convertStringtoInitCap(mohonBahanBatuan.getJumlahIsipaduUom().getNama()) + " pada tempoh " + mohonBahanBatuan.getTempohKeluar() + " " + pelUtiliti.convertStringtoInitCap(tempohUnit) + " daripada " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(pemohon.getCawangan().getDaerah().getNama()) + " untuk tujuan " + pelUtiliti.convertStringtoInitCap(permohonan.getSebab()) + noktah;
//        } else {
//            String tempohUnit = new String();
//            tempohUnit = mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("B") ? "Bulan" : mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("T") ? "Tahun" : "Hari";
//            tajukTujuanDraf = tujuan + tujuan2 + jenisBahanBatu + tujuan3 + mohonBahanBatuan.getJumlahIsipaduDipohon() + " " + pelUtiliti.convertStringtoInitCap(mohonBahanBatuan.getJumlahIsipaduDipohonUom().getNama()) + " pada tempoh " + mohonBahanBatuan.getTempohKeluar() + " " + pelUtiliti.convertStringtoInitCap(tempohUnit) + " daripada " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(pemohon.getCawangan().getDaerah().getNama()) + " untuk tujuan " + pelUtiliti.convertStringtoInitCap(permohonan.getSebab()) + noktah;
//        }
    }

    public void settingBil211() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =2 AND SUBTAJUK = 2.1.1; 
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = permohonan.getInfoAudit().getTarikhMasuk();
        String date2 = sdf.format(date);
        tajukPerihalPermohonan = perihalpermohonan + perihalpermohonan2 + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + perihalpermohonan3 + date2 + noktah;
    }

    public void settingBil221() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =2 AND SUBTAJUK = 2.2.1; 
         */
        String perihalPemohon = new String();
        KodWarnaKP kodWarnaKP = new KodWarnaKP();
        kodWarnaKP = pelupusanService.findKodWarnaKPByKod(pemohon.getPihak().getWarnaKP());

        if (pemohon.getPihak().getJenisPengenalan().getKod().equals("S")) {
            perihalPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getJenisPengenalan().getNama()) + " " + pemohon.getPihak().getNoPengenalan();
        } else {
            perihalPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getJenisPengenalan().getNama()) + " " + pemohon.getPihak().getNoPengenalan() + " berwarna " + pelUtiliti.convertStringtoInitCap(kodWarnaKP.getNama()) + " adalah seorang Warganegara " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getWargaNegara().getNama()) + " berketurunan " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getBangsa().getNama()) + noktah + " Beliau bekerja sebagai " + pelUtiliti.convertStringtoInitCap(pemohon.getPekerjaan()) + " dengan pendapatan sebanyak RM" + pemohon.getPendapatan() + " sebulan.";
        }
        String alamat = new String();
        if (pemohon.getPihak().getAlamat1() != null) {
            alamat = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat1());
        }
        if (pemohon.getPihak().getAlamat2() != null) {
            alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat2());
        }
        if (pemohon.getPihak().getAlamat3() != null) {
            alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat3());
        }
        if (pemohon.getPihak().getAlamat4() != null) {
            alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat4());
        }
        if (pemohon.getPihak().getPoskod() != null) {
            alamat = alamat + ", " + pemohon.getPihak().getPoskod();
        }
        if (pemohon.getPihak().getNegeri() != null) {
            alamat = alamat + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNegeri().getNama());
        }

        tajukPerihalPemohon = perihalpemohon + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + perihalPemohon + " Alamat tempat tinggal pemohon ialah di " + alamat;
    }

    public void settingBil231() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =2 AND SUBTAJUK = 2.3.1; 
         */
        tajukPerihalTanah = perihaltanah1 + perihaltanah12 + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getKodMilik().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " seperti yang bertanda Merah di dalam pelan berkembar." + "\n" + perihaltanah15 + hakmilikPermohonan.getLuasTerlibat() + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getKodUnitLuas().getNama()) + noktah;
    }

    public void settingDefaultValue() {

        noktah = ".";
        noktahbertindih = ":";
        tajuk = " PERMOHONAN PAJAKAN STRATUM TANAH BAWAH TANAH DIBAWAH TANAH RIZAB ";
        tajuk4 = " DI ATAS LOT ";
        tajuk5 = " DAERAH " + permohonan.getCawangan().getDaerah().getNama() + " UNTUK TUJUAN ";
        if (permohonan.getKodUrusan().getKod().equals("PJBTR")) {
            // tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Yang Amat Berhormat Ketua Menteri, Melaka mengenai permohonan ";
            tujuan = " Tujuan rencana ini ialah untuk mendapatkan pertimbangan Mesyuarat Jawatankuasa Belah Bahagi Negeri Melaka mengenai permohonan ";
        }
        tujuan2 = " untuk pajakan tanah stratum tanah bawah tanah dibawah tanah rizab ";
        tujuan5 = " daripada ";
        tujuan6 = " Mukim ";
        tujuan7 = " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " untuk tujuan ";

        perihalpermohonan = " Pentadbir Tanah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah menerima permohonan untuk pajakan tanah stratum ";
        perihalpermohonan2 = " daripada ";
        perihalpermohonan3 = " pada ";

        perihalpemohon = "Pemohon ialah ";
        perihalpemohon2 = " no. syarikat ";
        perihalpemohon3 = " Alamat tempat ";
        perihalpemohon4 = " tinggal pemohon ialah di ";


    }

    public Resolution refreshDrafJKBB() {
        String editJKBB = (String) getContext().getRequest().getParameter("edit");
        rehydrate();
        if (Boolean.parseBoolean(editJKBB) != true) {
            edit = Boolean.FALSE;
        } else {
            edit = Boolean.TRUE;
        }


        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public boolean checkingValidation() {
        boolean returnCheck = false;
        if (tajukTujuanDraf == null) {
            addSimpleError("Sila Masukkan Maklumat Tujuan");
            returnCheck = true;
        } else if (tajukPerihalPermohonan == null) {
            addSimpleError("Sila Masukkan Maklumat Perihal Permohonan");
            returnCheck = true;
        } else if (tajukPerihalPemohon == null) {
            addSimpleError("Sila Masukkan Maklumat Pemohon");
            returnCheck = true;
        } else if (tajukPerihalTanah == null) {
            addSimpleError("Sila Masukkan Perihal Tanah");
            returnCheck = true;
        }
//        else if (tajukPerakuanPTD == null) {
//            addSimpleError("Sila Masukkan Perakuan Pentadbir Tanah Daerah");
//            returnCheck = true;
//        }
//        if (senaraiLaporanKandunganPerakuanPTD.size() > 0) {
//            for (int i = 0; i < senaraiLaporanKandunganPerakuanPTD.size(); i++) {
//                PermohonanKertasKandungan checkKK = new PermohonanKertasKandungan();
//                checkKK = senaraiLaporanKandunganPerakuanPTD.get(i);
//                String perakuanPTD = (String) getContext().getRequest().getParameter("perakuanPTD" + i);
//                if (perakuanPTD == null || ("").equals(perakuanPTD)) {
//                    addSimpleError("Sila Masukkan Perakuan Pentadbir Tanah Daerah pada bahagian " + checkKK.getSubtajuk());
//                    returnCheck = true;
//                }
//            }
//        }
//        if (senaraiLaporanKandunganPerakuanPTG.size() > 0) {
//            for (int i = 0; i < senaraiLaporanKandunganPerakuanPTG.size(); i++) {
//                PermohonanKertasKandungan checkKK = new PermohonanKertasKandungan();
//                checkKK = senaraiLaporanKandunganPerakuanPTG.get(i);
//                String perakuanPTG = (String) getContext().getRequest().getParameter("perakuanPTG" + i);
//                if (perakuanPTG == null || ("").equals(perakuanPTG)) {
//                    addSimpleError("Sila Masukkan Perakuan Pentadbir Tanah Dan Galian pada bahagian " + checkKK.getSubtajuk());
//                    returnCheck = true;
//                }
//            }
//        }
//        if (senaraiLaporanKandunganPerihalTanah.size() > 0) {
//            for (int i = 0; i < senaraiLaporanKandunganPerihalTanah.size(); i++) {
//                PermohonanKertasKandungan checkKK = new PermohonanKertasKandungan();
//                checkKK = senaraiLaporanKandunganPerihalTanah.get(i);
//                String perihalTanah = (String) getContext().getRequest().getParameter("perihalTanah.kandungan" + i);
//                if (perihalTanah == null || ("").equals(perihalTanah)) {
//                    addSimpleError("Sila Masukkan Perihal Tanah pada bahagian " + checkKK.getSubtajuk());
//                    returnCheck = true;
//                }
//            }
//        }
//        if (senaraiLaporanAsasPertimbangan.size() > 0) {
//            for (int i = 0; i < senaraiLaporanAsasPertimbangan.size(); i++) {
//                PermohonanKertasKandungan checkKK = new PermohonanKertasKandungan();
//                checkKK = senaraiLaporanAsasPertimbangan.get(i);
//                String asasTimbang = (String) getContext().getRequest().getParameter("asasTimbang" + i);
//                if (asasTimbang == null || ("").equals(asasTimbang)) {
//                    addSimpleError("Sila Masukkan Asas-asas Pertimbangan pada bahagian " + checkKK.getSubtajuk());
//                    returnCheck = true;
//                }
//            }
//        }
        return returnCheck;
    }

    public Resolution simpanDataPTD() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String editTest = (String) getContext().getRequest().getParameter("edit");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (editTest != null && !("").equals(editTest)) {
            edit = Boolean.parseBoolean(editTest);
        }
        List listmohonKertasKandunganInsert = new Vector();
        if (tajukPerakuanPTD != null) {
            List listmohonKertasKandungan = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
            for (int i = 0; i < listmohonKertasKandungan.size(); i++) {
                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                mohonKertasKandungan = (PermohonanKertasKandungan) listmohonKertasKandungan.get(i);
                switch (mohonKertasKandungan.getBil()) {

                    case 6:
                        if (mohonKertasKandungan.getSubtajuk().equals("6.1")) {
                            mohonKertasKandungan.setKandungan(tajukPerakuanPTD);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        break;
                    /*
                     *  END OF DEFAULT VALUE
                     */
                }

            }
        }


        // PERAKUAN PENTADBIR TANAH
        for (int z = 0; z < senaraiLaporanKandunganPerakuanPTD.size(); z++) {
            String test = (String) getContext().getRequest().getParameter("perakuanPTD" + z);
            if (test != null && !("").equals(test)) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();

                mohonKertasKand = senaraiLaporanKandunganPerakuanPTD.get(z);
                mohonKertasKand.setKandungan(test);
                listmohonKertasKandunganInsert.add(mohonKertasKand);
            }
        }
        /*
         * SAVING DATA INTO MOHON KERTAS KANDUNGAN
         */
        for (int ii = 0; ii < listmohonKertasKandunganInsert.size(); ii++) {
            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
            mohonKertasKand = (PermohonanKertasKandungan) listmohonKertasKandunganInsert.get(ii);
            PermohonanKertasKandungan mohonKertasKandInsert = pelupusanService.findkandunganByIdKandungan(mohonKertasKand.getIdKandungan());
            if (mohonKertasKandInsert != null) {
                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
            } else {
                pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
            }
        }
        /*
         * 
         */
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanDataPTG() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String editTest = (String) getContext().getRequest().getParameter("edit");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (editTest != null && !("").equals(editTest)) {
            edit = Boolean.parseBoolean(editTest);
        }
        List listmohonKertasKandunganInsert = new Vector();
        if (tajukPerakuanPTG != null) {
            List listmohonKertasKandungan = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
            for (int i = 0; i < listmohonKertasKandungan.size(); i++) {
                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                mohonKertasKandungan = (PermohonanKertasKandungan) listmohonKertasKandungan.get(i);
                switch (mohonKertasKandungan.getBil()) {

                    case 7:
                        if (mohonKertasKandungan.getSubtajuk().equals("7.1")) {
                            mohonKertasKandungan.setKandungan(tajukPerakuanPTG);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        break;
                    /*
                     *  END OF DEFAULT VALUE
                     */
                }

            }
        }
        // PERAKUAN PENGARAH TANAH DAN GALIAN
        for (int z = 0; z < senaraiLaporanKandunganPerakuanPTG.size(); z++) {
            String test = (String) getContext().getRequest().getParameter("perakuanPTG" + z);
            if (test != null && !("").equals(test)) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();

                mohonKertasKand = senaraiLaporanKandunganPerakuanPTG.get(z);
                mohonKertasKand.setKandungan(test);
                listmohonKertasKandunganInsert.add(mohonKertasKand);
            }
        }
        /*
         * SAVING DATA INTO MOHON KERTAS KANDUNGAN
         */
        for (int ii = 0; ii < listmohonKertasKandunganInsert.size(); ii++) {
            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
            mohonKertasKand = (PermohonanKertasKandungan) listmohonKertasKandunganInsert.get(ii);
            PermohonanKertasKandungan mohonKertasKandInsert = pelupusanService.findkandunganByIdKandungan(mohonKertasKand.getIdKandungan());
            if (mohonKertasKandInsert != null) {
                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
            } else {
                pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
            }
        }
        /*
         * 
         */
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution SimpandrafJKBB() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String editTest = (String) getContext().getRequest().getParameter("edit");
        if (editTest != null && !("").equals(editTest)) {
            edit = Boolean.parseBoolean(editTest);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
//        int checkSenarai = this.senaraiLaporanKandunganPerihalTanah.size();
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        if (!checkingValidation()) {
            /*
             * MOHON_KERTAS
             */
            if (permohonan != null) {
                PermohonanKertas mohonKertas = new PermohonanKertas();
                mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
                if (mohonKertas == null) {
                    mohonKertas = new PermohonanKertas();
                    mohonKertas.setCawangan(permohonan.getCawangan());
                    mohonKertas.setPermohonan(permohonan);
                    mohonKertas.setTajuk("Draf JKBB");
                    mohonKertas.setInfoAudit(info);
                    mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                    pelupusanService.simpanSavePermohonanKertas(mohonKertas);
                    mohonKertas = new PermohonanKertas();
                    mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
                }
                /*
                 * END OF MOHON KERTAS
                 */
                /*
                 * MOHON KERTAS KANDUNGAN
                 */

                if (mohonKertas != null) {
                    List listmohonKertasKandungan = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
                    List listmohonKertasKandunganInsert = new Vector();
                    boolean checkExistBil0 = false;
                    boolean checkExistBil1 = false;
                    boolean checkExistSubTajuk211 = false;
                    boolean checkExistSubTajuk221 = false;
                    boolean checkExistSubTajuk231 = false;
                    boolean checkExistBil4 = false;
                    boolean checkExistBil5 = false;
                    boolean checkExistBil6 = false;
                    boolean checkExistBil7 = false;
                    if (listmohonKertasKandungan.size() > 0) {


                        for (int i = 0; i < listmohonKertasKandungan.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan = (PermohonanKertasKandungan) listmohonKertasKandungan.get(i);
                            switch (mohonKertasKandungan.getBil()) {
                                case 0:
                                    mohonKertasKandungan.setKandungan(tajukMainDraf);
                                    listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                    checkExistBil0 = true;
                                    break;
                                case 1:
                                    if (mohonKertasKandungan.getSubtajuk().equals("1")) {
                                        mohonKertasKandungan.setKandungan(tajukTujuanDraf);
                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                        checkExistBil1 = true;
                                    }
                                    break;
                                case 2: /*
                                     * DEFAULT VALUE 
                                     */
                                    if (mohonKertasKandungan.getSubtajuk().equals("1")) {
                                        mohonKertasKandungan.setKandungan(tajukPerihalPermohonan);
                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                        checkExistSubTajuk211 = true;
                                    } else if (mohonKertasKandungan.getSubtajuk().equals("2")) {
                                        mohonKertasKandungan.setKandungan(tajukPerihalPemohon);
                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                        checkExistSubTajuk221 = true;
                                    }

                                    break;
//                                case 5:
//                                    if (mohonKertasKandungan.getSubtajuk().equals("1")) {
//                                        mohonKertasKandungan.setKandungan(tajukAsasTimbang);
//                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                                        checkExistBil5 = true;
//                                    }
//                                    break;
//                                case 6:
//                                    if (mohonKertasKandungan.getSubtajuk().equals("1")) {
//                                        mohonKertasKandungan.setKandungan(tajukPerakuanPTD);
//                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                                        checkExistBil6 = true;
//                                    }
//                                    break;
//                                case 7:
//                                    if (mohonKertasKandungan.getSubtajuk().equals("1")) {
//                                        mohonKertasKandungan.setKandungan(tajukPerakuanPTG);
//                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                                        checkExistBil7 = true;
//                                    }
//                                    break;
                                /*
                                 *  END OF DEFAULT VALUE
                                 */
                            }

                        }
                        /*
                         * DEFAULT VALUE IF NOT IN DB
                         */
                        if (!checkExistBil0) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(0);
                            mohonKertasKandungan.setKandungan(tajukMainDraf);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistBil1) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(1);
                            mohonKertasKandungan.setSubtajuk("1");
                            mohonKertasKandungan.setKandungan(tajukTujuanDraf);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistSubTajuk211) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(2);
                            mohonKertasKandungan.setSubtajuk("1");
                            mohonKertasKandungan.setKandungan(tajukPerihalPermohonan);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistSubTajuk221) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(2);
                            mohonKertasKandungan.setSubtajuk("2");
                            mohonKertasKandungan.setKandungan(tajukPerihalPemohon);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
//                        if (!checkExistBil5) {
//                            if (tajukAsasTimbang != null && !("").equals(tajukAsasTimbang)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(5);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukAsasTimbang);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }
//                        if (!checkExistBil6) {
//                            if (tajukPerakuanPTD != null && !("").equals(tajukPerakuanPTD)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(6);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukPerakuanPTD);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }
//                        if (!checkExistBil7) {
//                            if (tajukPerakuanPTG != null && !("").equals(tajukPerakuanPTG)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(7);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukPerakuanPTG);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }
                        /*
                         * DYNAMIC VALUE
                         */
                        //PERIHAL TANAH
//                        for (int z = 0; z < senaraiLaporanKandunganPerihalTanah.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("perihalTanah.kandungan" + z);
//                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                            mohonKertasKand = senaraiLaporanKandunganPerihalTanah.get(z);
//                            mohonKertasKand.setKandungan(test);
//                            listmohonKertasKandunganInsert.add(mohonKertasKand);
//                        }
//                        // PERAKUAN PENTADBIR TANAH
//                        for (int z = 0; z < senaraiLaporanKandunganPerakuanPTD.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("perakuanPTD" + z);
//                            if (test != null && !("").equals(test)) {
//                                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                                mohonKertasKand = senaraiLaporanKandunganPerakuanPTD.get(z);
//                                mohonKertasKand.setKandungan(test);
//                                listmohonKertasKandunganInsert.add(mohonKertasKand);
//                            }
//
//                        }
//                        // PERAKUAN PENGARAH TANAH DAN GALIAN
//                        for (int z = 0; z < senaraiLaporanKandunganPerakuanPTG.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("perakuanPTG" + z);
//                            if (test != null && !("").equals(test)) {
//                                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                                mohonKertasKand = senaraiLaporanKandunganPerakuanPTG.get(z);
//                                mohonKertasKand.setKandungan(test);
//                                listmohonKertasKandunganInsert.add(mohonKertasKand);
//                            }
//
//                        }
                        // ASAS-ASAS PERTIMBANGAN
//                        for (int z = 0; z < senaraiLaporanAsasPertimbangan.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("asasTimbang" + z);
//                            if (test != null && !("").equals(test)) {
//                                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                                mohonKertasKand = senaraiLaporanAsasPertimbangan.get(z);
//                                mohonKertasKand.setKandungan(test);
//                                listmohonKertasKandunganInsert.add(mohonKertasKand);
//                            }
//                        }
                        /*
                         * END OF DYNAMIC VALUE
                         */

                    } else {
                        /*
                         * DEFAULT VALUE IF NOT IN DB
                         */
                        if (!checkExistBil0) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(0);
                            mohonKertasKandungan.setKandungan(tajukMainDraf);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistBil1) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(1);
                            mohonKertasKandungan.setSubtajuk("1");
                            mohonKertasKandungan.setKandungan(tajukTujuanDraf);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistSubTajuk211) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(2);
                            mohonKertasKandungan.setSubtajuk("1");
                            mohonKertasKandungan.setKandungan(tajukPerihalPermohonan);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistSubTajuk221) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(2);
                            mohonKertasKandungan.setSubtajuk("2");
                            mohonKertasKandungan.setKandungan(tajukPerihalPemohon);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }

//                        if (!checkExistBil5) {
//                            if (tajukAsasTimbang != null && !("").equals(tajukAsasTimbang)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(5);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukAsasTimbang);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }
//                        if (!checkExistBil6) {
//                            if (tajukPerakuanPTD != null && !("").equals(tajukPerakuanPTD)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(6);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukPerakuanPTD);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }
//                        if (!checkExistBil7) {
//                            if (tajukPerakuanPTG != null && !("").equals(tajukPerakuanPTG)) {
//                                PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
//                                mohonKertasKandungan.setCawangan(permohonan.getCawangan());
//                                mohonKertasKandungan.setKertas(mohonKertas);
//                                mohonKertasKandungan.setInfoAudit(info);
//                                mohonKertasKandungan.setBil(7);
//                                mohonKertasKandungan.setSubtajuk("1");
//                                mohonKertasKandungan.setKandungan(tajukPerakuanPTG);
//                                listmohonKertasKandunganInsert.add(mohonKertasKandungan);
//                            }
//                        }

                        /*
                         * END OF DYNAMIC VALUE
                         */
                    }
                    /*
                     * SAVING DATA INTO MOHON KERTAS KANDUNGAN
                     */
                    for (int ii = 0; ii < listmohonKertasKandunganInsert.size(); ii++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = (PermohonanKertasKandungan) listmohonKertasKandunganInsert.get(ii);
                        PermohonanKertasKandungan mohonKertasKandInsert = pelupusanService.findkandunganByIdKandungan(mohonKertasKand.getIdKandungan());
                        if (mohonKertasKandInsert != null) {
                            pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                        } else {
                            pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
                        }
                    }
                    /*
                     * 
                     */
                } else {
                    addSimpleMessage("Mohon Kertas IS NULL");
                }
                /*
                 * END OF MOHON KERTAS KANDUNGAN
                 */
                /*Add for Bayaran Kupon 
                 * 
                 */
//                if (kpsn.equals("SL")) { //Add filtering for status keputusan
//                    mohonTuntutKos = new PermohonanTuntutanKos();
//                    mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");
//
//                    if (mohonTuntutKos == null) {
//                        mohonTuntutKos = new PermohonanTuntutanKos();
//                        mohonTuntutKos.setInfoAudit(info);
//                        mohonTuntutKos.setPermohonan(permohonan);
//                        mohonTuntutKos.setCawangan(permohonan.getCawangan());
//                        mohonTuntutKos.setItem("Bayaran Kupon");
//                        mohonTuntutKos.setAmaunTuntutan(kupon);
//                        mohonTuntutKos.setKuantiti(kuponQty);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKos.setAmaunSeunit(seUnit);
//                        mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
//                        mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
//                        pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
//                    } else {
//                        mohonTuntutKos.setInfoAudit(info);
//                        mohonTuntutKos.setPermohonan(permohonan);
//                        mohonTuntutKos.setCawangan(permohonan.getCawangan());
//                        mohonTuntutKos.setItem("Bayaran Kupon");
//                        mohonTuntutKos.setAmaunTuntutan(kupon);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKos.setAmaunSeunit(seUnit);
//                        mohonTuntutKos.setKuantiti(kuponQty);
//                        mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISKD"));
//                        mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
//                        pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
//                    }
//                    /* End for bayaran kupon
//                     * 
//                     */
//                    /*Add for CAGARAN JALAN
//                     * 
//                     */
//                    PermohonanTuntutanKos mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
//                    mohonTuntutKosCagarJln = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");
//
//                    if (mohonTuntutKosCagarJln == null) {
//                        mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
//                        mohonTuntutKosCagarJln.setInfoAudit(info);
//                        mohonTuntutKosCagarJln.setPermohonan(permohonan);
//                        mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
//                        mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
//                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagarJalan);
//////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
////                        BigDecimal seUnit = new BigDecimal(50);
////                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
//                        mohonTuntutKosCagarJln.setKodTransaksi(kodTuntutDAO.findById("DISKD").getKodKewTrans());
//                        pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKosCagarJln);
//                    } else {
//                        mohonTuntutKosCagarJln.setInfoAudit(info);
//                        mohonTuntutKosCagarJln.setPermohonan(permohonan);
//                        mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
//                        mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
//                        mohonTuntutKosCagarJln.setAmaunTuntutan(cagarJalan);
////                        BigDecimal seUnit = new BigDecimal(50);
////                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
//                        mohonTuntutKosCagarJln.setKodTuntut(kodTuntutDAO.findById("DISCJ"));
//                        mohonTuntutKosCagarJln.setKodTransaksi(kodTuntutDAO.findById("DISCJ").getKodKewTrans());
//                        pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKosCagarJln);
//                    }
//                }
                /* End for bayaran kupon
                 * 
                 */
                /*
                 * ULASAN JABATAN TEKNIKAL JTK
                 */
//                if (!edit) {
//                    if (senaraiUlasanJabatanTeknikal.size() > 0) {
//                        for (int i = 0; i < senaraiUlasanJabatanTeknikal.size(); i++) {
//                            String ulasanAgensi = (String) getContext().getRequest().getParameter("line.ulasan" + i);
//                            PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
//                            mohonRujuk = senaraiUlasanJabatanTeknikal.get(i);
//                            if (mohonRujuk.getUlasan() == null) {
//                                mohonRujuk.setUlasan("Tiada Ulasan Diterima");
//                            }
//                            pelupusanService.simpanPermohonanRujLuar(mohonRujuk);
//                        }
//                    }
//                    /*
//                     * ULASAN JKBB
//                     */
//                    if (senaraiUlasanJKBB.size() > 0) {
//                        for (int i = 0; i < senaraiUlasanJKBB.size(); i++) {
//                            String ulasanAgensi = (String) getContext().getRequest().getParameter("ulasanJKBB.ulasan" + i);
//                            PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
//                            mohonRujuk = senaraiUlasanJKBB.get(i);
////                            mohonRujuk.setUlasan(ulasanAgensi);
//                            if (mohonRujuk.getUlasan() == null) {
//                                mohonRujuk.setUlasan("Tiada Ulasan Diterima");
//                            }
//                            pelupusanService.simpanPermohonanRujLuar(mohonRujuk);
//                        }
//                    }
//                }


//                addSimpleMessage("Maklumat telah berjaya disimpan");
            } else {
//                addSimpleMessage("IDPERMOHONAN TIDAK DIJUMPAI -- SILA HUBUNGI PENTADBIR SISTEM");
            }
//            rehydrate();
        }
//        if(edit)
//            showForm2();
//        else
//            showForm1();

        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerihalTanahPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        subTajukPopUp = (String) getContext().getRequest().getParameter("subTajukPopUp");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohon != null) {

            if (mohonKertas != null) {
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            } else {
                mohonKertas = new PermohonanKertas();
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanSavePermohonanKertas(mohonKertas);
            }
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
            if (mohonKertas != null) {
                senaraiLaporanKandunganPerihalTanah = pelupusanService.findByidKertasNSubtajukNBil(mohonKertas.getIdKertas(), 2, "2.3.%");
                if (senaraiLaporanKandunganPerihalTanah.size() > 0) {
                    /*
                     * CHECKING WHETHER THE SUBTAJUK EXIST OR NOT, IF EXIST,OVERWRITE KANDUNGAN, ELSE INSERT NEW
                     */
                    boolean exists = false;
                    for (int i = 0; i < senaraiLaporanKandunganPerihalTanah.size(); i++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = senaraiLaporanKandunganPerihalTanah.get(i);
                        if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                            exists = true;
                        }
                    }
                    /*
                     * IF NOT EXISTS, INSERT NEW
                     */
                    if (!exists) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand.setCawangan(mohon.getCawangan());
                        mohonKertasKand.setKertas(mohonKertas);
                        mohonKertasKand.setKandungan(kandunganPopUp);
                        mohonKertasKand.setInfoAudit(info);
                        mohonKertasKand.setSubtajuk(subTajukPopUp);
                        mohonKertasKand.setBil(2);
                        pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);
                    } else {
                        for (int i = 0; i < senaraiLaporanKandunganPerihalTanah.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                            mohonKertasKand = senaraiLaporanKandunganPerihalTanah.get(i);
                            if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                                mohonKertasKand.setCawangan(mohon.getCawangan());
                                mohonKertasKand.setKertas(mohonKertas);
                                mohonKertasKand.setKandungan(kandunganPopUp);
                                mohonKertasKand.setInfoAudit(info);
                                mohonKertasKand.setSubtajuk(subTajukPopUp);
                                mohonKertasKand.setBil(2);
                                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                            }
                        }
                    }
                } else {
                    PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                    mohonKertasKand.setCawangan(mohon.getCawangan());
                    mohonKertasKand.setKertas(mohonKertas);
                    mohonKertasKand.setKandungan(kandunganPopUp);
                    mohonKertasKand.setInfoAudit(info);
                    mohonKertasKand.setSubtajuk(subTajukPopUp);
                    mohonKertasKand.setBil(2);
                    pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);

                }
            } else {
                addSimpleError("MOHON KERTAS TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

        } else {
            addSimpleError("IDPERMOHONAN TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
        }

        return new JSP("pelupusan/batuan/tambah_PerihalTanah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerakuanPTDPopUp() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        subTajukPopUp = (String) getContext().getRequest().getParameter("subTajukPopUp");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String editPTD = (String) getContext().getRequest().getParameter("edit");
        if (editPTD != null && !("").equals(editPTD)) {
            edit = Boolean.parseBoolean(editPTD);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohon != null) {

            if (mohonKertas != null) {
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            } else {
                mohonKertas = new PermohonanKertas();
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanSavePermohonanKertas(mohonKertas);
            }
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
            if (mohonKertas != null) {
                senaraiLaporanKandunganPerakuanPTD = pelupusanService.findByidKertasNSubtajukNBil(mohonKertas.getIdKertas(), 6, "6.%");
                if (senaraiLaporanKandunganPerakuanPTD.size() > 0) {
                    /*
                     * CHECKING WHETHER THE SUBTAJUK EXIST OR NOT, IF EXIST,OVERWRITE KANDUNGAN, ELSE INSERT NEW
                     */
                    boolean exists = false;
                    for (int i = 0; i < senaraiLaporanKandunganPerakuanPTD.size(); i++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = senaraiLaporanKandunganPerakuanPTD.get(i);
                        if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                            exists = true;
                        }
                    }
                    /*
                     * IF NOT EXISTS, INSERT NEW
                     */
                    if (!exists) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand.setCawangan(mohon.getCawangan());
                        mohonKertasKand.setKertas(mohonKertas);
                        mohonKertasKand.setKandungan(kandunganPopUp);
                        mohonKertasKand.setInfoAudit(info);
                        mohonKertasKand.setSubtajuk(subTajukPopUp);
                        mohonKertasKand.setBil(6);
                        pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);
                    } else {
                        for (int i = 0; i < senaraiLaporanKandunganPerakuanPTD.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                            mohonKertasKand = senaraiLaporanKandunganPerakuanPTD.get(i);
                            if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                                mohonKertasKand.setCawangan(mohon.getCawangan());
                                mohonKertasKand.setKertas(mohonKertas);
                                mohonKertasKand.setKandungan(kandunganPopUp);
                                mohonKertasKand.setInfoAudit(info);
                                mohonKertasKand.setSubtajuk(subTajukPopUp);
                                mohonKertasKand.setBil(6);
                                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                            }
                        }
                    }
                } else {
                    PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                    mohonKertasKand.setCawangan(mohon.getCawangan());
                    mohonKertasKand.setKertas(mohonKertas);
                    mohonKertasKand.setKandungan(kandunganPopUp);
                    mohonKertasKand.setInfoAudit(info);
                    mohonKertasKand.setSubtajuk(subTajukPopUp);
                    mohonKertasKand.setBil(6);
                    pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);

                }
            } else {
                addSimpleError("MOHON KERTAS TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

        } else {
            addSimpleError("IDPERMOHONAN TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
        }
        return new JSP("pelupusan/batuan/tambah_PerakuanPTD.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerakuanPTGPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        subTajukPopUp = (String) getContext().getRequest().getParameter("subTajukPopUp");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String editPTG = (String) getContext().getRequest().getParameter("edit");
        if (editPTG != null && !("").equals(editPTG)) {
            edit = Boolean.parseBoolean(editPTG);
        }
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohon != null) {

            if (mohonKertas != null) {
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            } else {
                mohonKertas = new PermohonanKertas();
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanSavePermohonanKertas(mohonKertas);
            }
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
            if (mohonKertas != null) {
                senaraiLaporanKandunganPerakuanPTG = pelupusanService.findByidKertasNSubtajukNBil(mohonKertas.getIdKertas(), 7, "7.%");
                if (senaraiLaporanKandunganPerakuanPTG.size() > 0) {
                    /*
                     * CHECKING WHETHER THE SUBTAJUK EXIST OR NOT, IF EXIST,OVERWRITE KANDUNGAN, ELSE INSERT NEW
                     */
                    boolean exists = false;
                    for (int i = 0; i < senaraiLaporanKandunganPerakuanPTG.size(); i++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = senaraiLaporanKandunganPerakuanPTG.get(i);
                        if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                            exists = true;
                        }
                    }
                    /*
                     * IF NOT EXISTS, INSERT NEW
                     */
                    if (!exists) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand.setCawangan(mohon.getCawangan());
                        mohonKertasKand.setKertas(mohonKertas);
                        mohonKertasKand.setKandungan(kandunganPopUp);
                        mohonKertasKand.setInfoAudit(info);
                        mohonKertasKand.setSubtajuk(subTajukPopUp);
                        mohonKertasKand.setBil(7);
                        pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);
                    } else {
                        for (int i = 0; i < senaraiLaporanKandunganPerakuanPTG.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                            mohonKertasKand = senaraiLaporanKandunganPerakuanPTG.get(i);
                            if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                                mohonKertasKand.setCawangan(mohon.getCawangan());
                                mohonKertasKand.setKertas(mohonKertas);
                                mohonKertasKand.setKandungan(kandunganPopUp);
                                mohonKertasKand.setInfoAudit(info);
                                mohonKertasKand.setSubtajuk(subTajukPopUp);
                                mohonKertasKand.setBil(7);
                                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                            }
                        }
                    }
                } else {
                    PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                    mohonKertasKand.setCawangan(mohon.getCawangan());
                    mohonKertasKand.setKertas(mohonKertas);
                    mohonKertasKand.setKandungan(kandunganPopUp);
                    mohonKertasKand.setInfoAudit(info);
                    mohonKertasKand.setSubtajuk(subTajukPopUp);
                    mohonKertasKand.setBil(7);
                    pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);

                }
            } else {
                addSimpleError("MOHON KERTAS TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

        } else {
            addSimpleError("IDPERMOHONAN TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
        }

        return new JSP("pelupusan/batuan/tambah_PerakuanPTG.jsp").addParameter("tab", "true");
    }

    public Resolution simpanAsasTimbangPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        subTajukPopUp = (String) getContext().getRequest().getParameter("subTajukPopUp");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohon != null) {

            if (mohonKertas != null) {
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            } else {
                mohonKertas = new PermohonanKertas();
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk("DRAF JKBB");
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                pelupusanService.simpanSavePermohonanKertas(mohonKertas);
            }
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
            if (mohonKertas != null) {
                senaraiLaporanAsasPertimbangan = pelupusanService.findByidKertasNSubtajukNBil(mohonKertas.getIdKertas(), 5, "5.%");
                if (senaraiLaporanAsasPertimbangan.size() > 0) {
                    /*
                     * CHECKING WHETHER THE SUBTAJUK EXIST OR NOT, IF EXIST,OVERWRITE KANDUNGAN, ELSE INSERT NEW
                     */
                    boolean exists = false;
                    for (int i = 0; i < senaraiLaporanAsasPertimbangan.size(); i++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = senaraiLaporanAsasPertimbangan.get(i);
                        if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                            exists = true;
                        }
                    }
                    /*
                     * IF NOT EXISTS, INSERT NEW
                     */
                    if (!exists) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand.setCawangan(mohon.getCawangan());
                        mohonKertasKand.setKertas(mohonKertas);
                        mohonKertasKand.setKandungan(kandunganPopUp);
                        mohonKertasKand.setInfoAudit(info);
                        mohonKertasKand.setSubtajuk(subTajukPopUp);
                        mohonKertasKand.setBil(5);
                        pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);
                    } else {
                        for (int i = 0; i < senaraiLaporanAsasPertimbangan.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                            mohonKertasKand = senaraiLaporanAsasPertimbangan.get(i);
                            if (mohonKertasKand.getSubtajuk().equals(subTajukPopUp)) {
                                mohonKertasKand.setCawangan(mohon.getCawangan());
                                mohonKertasKand.setKertas(mohonKertas);
                                mohonKertasKand.setKandungan(kandunganPopUp);
                                mohonKertasKand.setInfoAudit(info);
                                mohonKertasKand.setSubtajuk(subTajukPopUp);
                                mohonKertasKand.setBil(5);
                                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                            }
                        }
                    }
                } else {
                    PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                    mohonKertasKand.setCawangan(mohon.getCawangan());
                    mohonKertasKand.setKertas(mohonKertas);
                    mohonKertasKand.setKandungan(kandunganPopUp);
                    mohonKertasKand.setInfoAudit(info);
                    mohonKertasKand.setSubtajuk(subTajukPopUp);
                    mohonKertasKand.setBil(5);
                    pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);

                }
            } else {
                addSimpleError("MOHON KERTAS TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

        } else {
            addSimpleError("IDPERMOHONAN TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
        }

        return new JSP("pelupusan/batuan/tambah_AsasTimbang.jsp").addParameter("tab", "true");
    }

    public Resolution showTambahPerihalTanah() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohonKertas != null) {
            if (senaraiLaporanKandunganPerihalTanah.size() > 0) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                mohonKertasKand = senaraiLaporanKandunganPerihalTanah.get(senaraiLaporanKandunganPerihalTanah.size() - 1);
                String endSubTajuk = new String();
                String frontSubTajuk = new String();
                if (mohonKertasKand.getSubtajuk().length() > 5) {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 2);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 2, mohonKertasKand.getSubtajuk().length());

                } else {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 1);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 1, mohonKertasKand.getSubtajuk().length());
                }
                subTajukPopUp = frontSubTajuk + String.valueOf(Integer.parseInt(endSubTajuk) + 1);
            } else {
                subTajukPopUp = "2.3.2";
            }
        } else {
            subTajukPopUp = "2.3.2";
        }

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_PerihalTanah.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPerakuanPTD() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        String editPTD = (String) getContext().getRequest().getParameter("edit");
        if (editPTD != null && !("").equals(editPTD)) {
            edit = Boolean.parseBoolean(editPTD);
        }
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohonKertas != null) {
            if (senaraiLaporanKandunganPerakuanPTD.size() > 0) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                mohonKertasKand = senaraiLaporanKandunganPerakuanPTD.get(senaraiLaporanKandunganPerakuanPTD.size() - 1);
                String endSubTajuk = new String();
                String frontSubTajuk = new String();
                if (mohonKertasKand.getSubtajuk().length() > 5) {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 2);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 2, mohonKertasKand.getSubtajuk().length());

                } else {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 1);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 1, mohonKertasKand.getSubtajuk().length());
                }
                subTajukPopUp = frontSubTajuk + String.valueOf(Integer.parseInt(endSubTajuk) + 1);
            } else {
                subTajukPopUp = "6.2";
            }
        } else {
            subTajukPopUp = "6.2";
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_PerakuanPTD.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPerakuanPTG() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        String editPTG = (String) getContext().getRequest().getParameter("edit");
        if (editPTG != null && !("").equals(editPTG)) {
            edit = Boolean.parseBoolean(editPTG);
        }
        if (mohonKertas != null) {
            if (senaraiLaporanKandunganPerakuanPTG.size() > 0) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                mohonKertasKand = senaraiLaporanKandunganPerakuanPTG.get(senaraiLaporanKandunganPerakuanPTG.size() - 1);
                String endSubTajuk = new String();
                String frontSubTajuk = new String();
                if (mohonKertasKand.getSubtajuk().length() > 5) {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 2);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 2, mohonKertasKand.getSubtajuk().length());

                } else {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 1);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 1, mohonKertasKand.getSubtajuk().length());
                }
                subTajukPopUp = frontSubTajuk + String.valueOf(Integer.parseInt(endSubTajuk) + 1);
            } else {
                subTajukPopUp = "7.2";
            }
        } else {
            subTajukPopUp = "7.2";
        }

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_PerakuanPTG.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahAsasPertimbangan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
        if (mohonKertas != null) {
            if (senaraiLaporanAsasPertimbangan.size() > 0) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                mohonKertasKand = senaraiLaporanAsasPertimbangan.get(senaraiLaporanAsasPertimbangan.size() - 1);
                String endSubTajuk = new String();
                String frontSubTajuk = new String();
                if (mohonKertasKand.getSubtajuk().length() > 5) {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 2);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 2, mohonKertasKand.getSubtajuk().length());

                } else {
                    frontSubTajuk = mohonKertasKand.getSubtajuk().substring(0, mohonKertasKand.getSubtajuk().length() - 1);
                    endSubTajuk = mohonKertasKand.getSubtajuk().substring(mohonKertasKand.getSubtajuk().length() - 1, mohonKertasKand.getSubtajuk().length());
                }
                subTajukPopUp = frontSubTajuk + String.valueOf(Integer.parseInt(endSubTajuk) + 1);
            } else {
                subTajukPopUp = "5.2";
            }
        } else {
            subTajukPopUp = "5.2";
        }

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_AsasTimbang.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2: //FOR Perihal Permohonan
                updateKandungan(2, kand);

                break;
//            case 3:
//
//                updateKandungan(3, kand);
//
//                break;
//            case 4:
//
//                updateKandungan(4, kand);
//
//                break;
            case 5:// FOR ASAS-ASAS PERTIMBANGAN

                updateKandungan(5, kand);

                break;
            case 6:// PERAKUAN PENTADBIR TANAH DAERAH

                updateKandungan(6, kand);
                break;
            case 7: // PERAKUAN PENGARAH TANAH DAN GALIAN
                updateKandungan(7, kand);
                break;
            case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                updateKandungan(8, kand);
                break;
            case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                updateKandungan(9, kand);
                break;
            case 22: // FOR PERIHAL TANAH 2.3.*
                updateKandungan(22, kand);
                break;
            case 24: // FOR BUTIR TANAH 2.4.*
                updateKandungan(24, kand);
                break;
            case 25: // FOR LOKASI TANAH 2.5.*
                updateKandungan(25, kand);
                break;
            case 10: // FOR PENOLONG PEGAWAI TANAH TERTINGGI
                updateKandungan(10, kand);
                break;

//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("viewOnlyPTG", viewOnlyPTG);
        getContext().getRequest().setAttribute("viewOnlyPTD", viewOnlyPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
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
//            case 4:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 4);
//                listKertasSyorPTD.add(pkk);
//                break;
            case 5:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 5);
                senaraiLaporanAsasPertimbangan.add(pkk);
                break;
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                senaraiLaporanKandunganPerakuanPTD.add(pkk);
                break;
            case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 7);
                senaraiLaporanKandunganPerakuanPTG.add(pkk);
                break;
//              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 8);
//                  senaraiLaporanKandunganptg1.add(pkk);
//                  break;
//              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 9);
//                  senaraiLaporanKandunganptg2.add(pkk);
//                  break;
            case 22: // FOR PERIHAL TANAH 2.3.*
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 22);
                senaraiLaporanPerihalTanah.add(pkk);
                break;
            case 24: // FOR BUTIR2 TANAH 2.3.*
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 24);
                senaraiLaporanKandunganButirTanah.add(pkk);
                break;
            case 25: // FOR LOKASI TANAH 2.3.*
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 25);
                senaraiLaporanKandunganLokasiTanah.add(pkk);
                break;

            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("editPTG", viewOnlyPTG);
        getContext().getRequest().setAttribute("editPTD", viewOnlyPTD);
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandunganDAO.findById(Long.parseLong(idKand));
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
        getContext().getRequest().setAttribute("editPTG", viewOnlyPTG);
        getContext().getRequest().setAttribute("editPTD", viewOnlyPTD);
        return new JSP("pelupusan/pjbtr/draf_jkbb_mlk.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
//        logger.info(permohonankertas.getKodDokumen().getKod());

        if (permohonankertas != null) {
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonankertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        permohonankertas.setTajuk("Draf JKBB");
        KodDokumen kod = kodDokumenDAO.findById("JKBB");
        permohonankertas.setKodDokumen(kod);
        permohonankertas.setCawangan(cawangan);
        permohonankertas.setInfoAudit(infoAudit);
        permohonankertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonankertas);

        long a = permohonankertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);



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
        pLK.setKertas(permohonankertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public String getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(String jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getKadarBayar() {
        return kadarBayar;
    }

    public void setKadarBayar(String kadarBayar) {
        this.kadarBayar = kadarBayar;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(String kuantiti) {
        this.kuantiti = kuantiti;
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

    public String getNo7() {
        return no7;
    }

    public void setNo7(String no7) {
        this.no7 = no7;
    }

    public String getNo8() {
        return no8;
    }

    public void setNo8(String no8) {
        this.no8 = no8;
    }

    public String getNo9() {
        return no9;
    }

    public void setNo9(String no9) {
        this.no9 = no9;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getPerihalpemohon() {
        return perihalpemohon;
    }

    public void setPerihalpemohon(String perihalpemohon) {
        this.perihalpemohon = perihalpemohon;
    }

    public String getPerihalpermohonan() {
        return perihalpermohonan;
    }

    public void setPerihalpermohonan(String perihalpermohonan) {
        this.perihalpermohonan = perihalpermohonan;
    }

    public String getPerihaltanah1() {
        return perihaltanah1;
    }

    public void setPerihaltanah1(String perihaltanah1) {
        this.perihaltanah1 = perihaltanah1;
    }

    public String getPerihaltanah2() {
        return perihaltanah2;
    }

    public void setPerihaltanah2(String perihaltanah2) {
        this.perihaltanah2 = perihaltanah2;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getWangCagar() {
        return wangCagar;
    }

    public void setWangCagar(String wangCagar) {
        this.wangCagar = wangCagar;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public String getNoktah() {
        return noktah;
    }

    public void setNoktah(String noktah) {
        this.noktah = noktah;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
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

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
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

    public String getAlamatTuanTanah() {
        return alamatTuanTanah;
    }

    public void setAlamatTuanTanah(String alamatTuanTanah) {
        this.alamatTuanTanah = alamatTuanTanah;
    }

    public String getIdSyarikatTuanTanah() {
        return idSyarikatTuanTanah;
    }

    public void setIdSyarikatTuanTanah(String idSyarikatTuanTanah) {
        this.idSyarikatTuanTanah = idSyarikatTuanTanah;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
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

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
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

    public String getKadarBayar2() {
        return kadarBayar2;
    }

    public void setKadarBayar2(String kadarBayar2) {
        this.kadarBayar2 = kadarBayar2;
    }

    public String getKuantiti2() {
        return kuantiti2;
    }

    public void setKuantiti2(String kuantiti2) {
        this.kuantiti2 = kuantiti2;
    }

    public String getNo6a() {
        return no6a;
    }

    public void setNo6a(String no6a) {
        this.no6a = no6a;
    }

    public String getNo7a() {
        return no7a;
    }

    public void setNo7a(String no7a) {
        this.no7a = no7a;
    }

    public String getNo8a() {
        return no8a;
    }

    public void setNo8a(String no8a) {
        this.no8a = no8a;
    }

    public String getNo9a() {
        return no9a;
    }

    public void setNo9a(String no9a) {
        this.no9a = no9a;
    }

    public String getNoktahbertindih() {
        return noktahbertindih;
    }

    public void setNoktahbertindih(String noktahbertindih) {
        this.noktahbertindih = noktahbertindih;
    }

    public String getPerakuan2() {
        return perakuan2;
    }

    public void setPerakuan2(String perakuan2) {
        this.perakuan2 = perakuan2;
    }

    public String getKuantitTanah() {
        return kuantitTanah;
    }

    public void setKuantitTanah(String kuantitTanah) {
        this.kuantitTanah = kuantitTanah;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getTuanTanah() {
        return tuanTanah;
    }

    public void setTuanTanah(String tuanTanah) {
        this.tuanTanah = tuanTanah;
    }

    public String getTujuanTanah() {
        return tujuanTanah;
    }

    public void setTujuanTanah(String tujuanTanah) {
        this.tujuanTanah = tujuanTanah;
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

    public String getWangCagar2() {
        return wangCagar2;
    }

    public void setWangCagar2(String wangCagar2) {
        this.wangCagar2 = wangCagar2;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getAdunTanah() {
        return adunTanah;
    }

    public void setAdunTanah(String adunTanah) {
        this.adunTanah = adunTanah;
    }

    public String getAlamatTanah() {
        return alamatTanah;
    }

    public void setAlamatTanah(String alamatTanah) {
        this.alamatTanah = alamatTanah;
    }

    public String getAsalTanah() {
        return asalTanah;
    }

    public void setAsalTanah(String asalTanah) {
        this.asalTanah = asalTanah;
    }

    public String getJarakTanah() {
        return jarakTanah;
    }

    public void setJarakTanah(String jarakTanah) {
        this.jarakTanah = jarakTanah;
    }

    public String getJenisJalan() {
        return jenisJalan;
    }

    public void setJenisJalan(String jenisJalan) {
        this.jenisJalan = jenisJalan;
    }

    public String getJumlahKeneBayar() {
        return jumlahKeneBayar;
    }

    public void setJumlahKeneBayar(String jumlahKeneBayar) {
        this.jumlahKeneBayar = jumlahKeneBayar;
    }

    public String getKelulusan() {
        return kelulusan;
    }

    public void setKelulusan(String kelulusan) {
        this.kelulusan = kelulusan;
    }

    public String getMeterTanah() {
        return meterTanah;
    }

    public void setMeterTanah(String meterTanah) {
        this.meterTanah = meterTanah;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getTempohTanah() {
        return tempohTanah;
    }

    public void setTempohTanah(String tempohTanah) {
        this.tempohTanah = tempohTanah;
    }

    public String getTuanTanahAsal() {
        return tuanTanahAsal;
    }

    public void setTuanTanahAsal(String tuanTanahAsal) {
        this.tuanTanahAsal = tuanTanahAsal;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerihalTanah() {
        return senaraiLaporanKandunganPerihalTanah;
    }

    public void setSenaraiLaporanKandunganPerihalTanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah) {
        this.senaraiLaporanKandunganPerihalTanah = senaraiLaporanKandunganPerihalTanah;
    }

    public String getSubTajukPopUp() {
        return subTajukPopUp;
    }

    public void setSubTajukPopUp(String subTajukPopUp) {
        this.subTajukPopUp = subTajukPopUp;
    }

    public String getKandunganPopUp() {
        return kandunganPopUp;
    }

    public void setKandunganPopUp(String kandunganPopUp) {
        this.kandunganPopUp = kandunganPopUp;
    }

    public String getTajukMainDraf() {
        return tajukMainDraf;
    }

    public void setTajukMainDraf(String tajukMainDraf) {
        this.tajukMainDraf = tajukMainDraf;
    }

//    public List<PermohonanBahanBatuan> getSenaraiBahanBatu() {
//        return senaraiBahanBatu;
//    }
//
//    public void setSenaraiBahanBatu(List<PermohonanBahanBatuan> senaraiBahanBatu) {
//        this.senaraiBahanBatu = senaraiBahanBatu;
//    }
    public String getTajukTujuanDraf() {
        return tajukTujuanDraf;
    }

    public void setTajukTujuanDraf(String tajukTujuanDraf) {
        this.tajukTujuanDraf = tajukTujuanDraf;
    }

    public String getTajukPerihalPermohonan() {
        return tajukPerihalPermohonan;
    }

    public void setTajukPerihalPermohonan(String tajukPerihalPermohonan) {
        this.tajukPerihalPermohonan = tajukPerihalPermohonan;
    }

    public String getTajukPerihalPemohon() {
        return tajukPerihalPemohon;
    }

    public void setTajukPerihalPemohon(String tajukPerihalPemohon) {
        this.tajukPerihalPemohon = tajukPerihalPemohon;
    }

    public String getTajukPerihalTanah() {
        return tajukPerihalTanah;
    }

    public void setTajukPerihalTanah(String tajukPerihalTanah) {
        this.tajukPerihalTanah = tajukPerihalTanah;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public String getTajukUlasanJabatan() {
        return tajukUlasanJabatan;
    }

    public void setTajukUlasanJabatan(String tajukUlasanJabatan) {
        this.tajukUlasanJabatan = tajukUlasanJabatan;
    }

    public String getDrafDaerah() {
        return drafDaerah;
    }

    public void setDrafDaerah(String drafDaerah) {
        this.drafDaerah = drafDaerah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTD() {
        return senaraiLaporanKandunganPerakuanPTD;
    }

    public void setSenaraiLaporanKandunganPerakuanPTD(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD) {
        this.senaraiLaporanKandunganPerakuanPTD = senaraiLaporanKandunganPerakuanPTD;
    }

    public String getPerakuanPTD() {
        return perakuanPTD;
    }

    public void setPerakuanPTD(String perakuanPTD) {
        this.perakuanPTD = perakuanPTD;
    }

    public PermohonanBahanBatuan getSyaratBahanBatu() {
        return syaratBahanBatu;
    }

    public void setSyaratBahanBatu(PermohonanBahanBatuan syaratBahanBatu) {
        this.syaratBahanBatu = syaratBahanBatu;
    }

    public String getTajukPerakuanPTD() {
        return tajukPerakuanPTD;
    }

    public void setTajukPerakuanPTD(String tajukPerakuanPTD) {
        this.tajukPerakuanPTD = tajukPerakuanPTD;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJKBB() {
        return senaraiUlasanJKBB;
    }

    public void setSenaraiUlasanJKBB(List<PermohonanRujukanLuar> senaraiUlasanJKBB) {
        this.senaraiUlasanJKBB = senaraiUlasanJKBB;
    }

    public String getTajukUlasanJKBB() {
        return tajukUlasanJKBB;
    }

    public void setTajukUlasanJKBB(String tajukUlasanJKBB) {
        this.tajukUlasanJKBB = tajukUlasanJKBB;
    }

    public String getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
    }

    public String getUrusanStatus() {
        return urusanStatus;
    }

    public void setUrusanStatus(String urusanStatus) {
        this.urusanStatus = urusanStatus;
    }

    public String getTajukHeader() {
        return tajukHeader;
    }

    public void setTajukHeader(String tajukHeader) {
        this.tajukHeader = tajukHeader;
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

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public String getTajukAsasTimbang() {
        return tajukAsasTimbang;
    }

    public void setTajukAsasTimbang(String tajukAsasTimbang) {
        this.tajukAsasTimbang = tajukAsasTimbang;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanAsasPertimbangan() {
        return senaraiLaporanAsasPertimbangan;
    }

    public void setSenaraiLaporanAsasPertimbangan(List<PermohonanKertasKandungan> senaraiLaporanAsasPertimbangan) {
        this.senaraiLaporanAsasPertimbangan = senaraiLaporanAsasPertimbangan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTG() {
        return senaraiLaporanKandunganPerakuanPTG;
    }

    public void setSenaraiLaporanKandunganPerakuanPTG(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG) {
        this.senaraiLaporanKandunganPerakuanPTG = senaraiLaporanKandunganPerakuanPTG;
    }

    public String getTajukPerakuanPTG() {
        return tajukPerakuanPTG;
    }

    public void setTajukPerakuanPTG(String tajukPerakuanPTG) {
        this.tajukPerakuanPTG = tajukPerakuanPTG;
    }

    public boolean isViewOnlyPTD() {
        return viewOnlyPTD;
    }

    public void setViewOnlyPTD(boolean viewOnlyPTD) {
        this.viewOnlyPTD = viewOnlyPTD;
    }

    public boolean isViewOnlyPTG() {
        return viewOnlyPTG;
    }

    public void setViewOnlyPTG(boolean viewOnlyPTG) {
        this.viewOnlyPTG = viewOnlyPTG;
    }

    public double getCagarKeneBayar() {
        return cagarKeneBayar;
    }

    public void setCagarKeneBayar(double cagarKeneBayar) {
        this.cagarKeneBayar = cagarKeneBayar;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public boolean isViewOnlyPTDOnly() {
        return viewOnlyPTDOnly;
    }

    public void setViewOnlyPTDOnly(boolean viewOnlyPTDOnly) {
        this.viewOnlyPTDOnly = viewOnlyPTDOnly;
    }

    public boolean isViewOnlyPTGOnly() {
        return viewOnlyPTGOnly;
    }

    public void setViewOnlyPTGOnly(boolean viewOnlyPTGOnly) {
        this.viewOnlyPTGOnly = viewOnlyPTGOnly;
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

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public BigDecimal getCagarJalan() {
        return cagarJalan;
    }

    public void setCagarJalan(BigDecimal cagarJalan) {
        this.cagarJalan = cagarJalan;
    }

    public BigDecimal getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(BigDecimal totalAll) {
        this.totalAll = totalAll;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganButirTanah() {
        return senaraiLaporanKandunganButirTanah;
    }

    public void setSenaraiLaporanKandunganButirTanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganButirTanah) {
        this.senaraiLaporanKandunganButirTanah = senaraiLaporanKandunganButirTanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganLokasiTanah() {
        return senaraiLaporanKandunganLokasiTanah;
    }

    public void setSenaraiLaporanKandunganLokasiTanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganLokasiTanah) {
        this.senaraiLaporanKandunganLokasiTanah = senaraiLaporanKandunganLokasiTanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPegawaiTertinggi() {
        return senaraiLaporanKandunganPerakuanPegawaiTertinggi;
    }

    public void setSenaraiLaporanKandunganPerakuanPegawaiTertinggi(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPegawaiTertinggi) {
        this.senaraiLaporanKandunganPerakuanPegawaiTertinggi = senaraiLaporanKandunganPerakuanPegawaiTertinggi;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public void setSenaraiLaporanPerihalTanah(List<PermohonanKertasKandungan> senaraiLaporanPerihalTanah) {
        this.senaraiLaporanPerihalTanah = senaraiLaporanPerihalTanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanPerihalTanah() {
        return senaraiLaporanPerihalTanah;
    }
}
