/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import etanah.dao.PermohonanKertasDAO;
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
 * @author Admin
 */
@UrlBinding("/pelupusan/draf_jkbb_pjbtrV2")
public class DrafJKBB_PJBTRV2ActionBean extends AbleActionBean {
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO ;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO ;
    @Inject
    PelupusanUtiliti pelUtiliti ;
    @Inject
    etanah.Configuration conf;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodCawanganDAO kodCawanganDAO ;
    @Inject
    KodDokumenDAO kodDokumenDAO ;
    
    private String idPermohonan ;
    private String stageId ;
    private String urusanStatus ;
    private String kodNeg ;
    private String tajukHeader ;
    private String tajukUlasanJKBB ;
    private String tajukUlasanJabatan ;
    private String tajukMainDraf;
    private String tajukTujuanDraf;
    private String tajukPerihalPermohonan;
    private String tajukPerihalPemohon;
    private String tajukPerihalTanah;
    private String tajukPerakuanPTD;
    private String tajukPerakuanPTG;
    private String tajukAsasTimbang;
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
    private String noktah ;
    private String noktahbertindih ;
    private boolean openPTD = false;
    private boolean openPTG = false;
    private boolean viewOnlyPTD = true;
    private boolean viewOnlyPTDOnly = true;
    private boolean viewOnlyPTG = true;
    private boolean viewOnlyPTGOnly = true;
    private Boolean edit;
    
    private Permohonan permohonan ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private PermohonanKertas permohonanKertas ;
    private FasaPermohonan fasaPermohonan ;
    private Pengguna peng ;
    private Pemohon pemohon ;
    private List<PermohonanKertasKandungan> senaraiLaporanAsasPertimbangan = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanPerihalTanah = new Vector() ;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganButirTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganLokasiTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG = new Vector();
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal = new Vector();
    private List<PermohonanRujukanLuar> senaraiUlasanJKBB = new Vector();
    
    
     @DefaultHandler
    public Resolution showForm1() {
        edit = Boolean.FALSE;
        return new JSP("pelupusan/pjbtr/draf_jkbb_pjbtrV2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit = Boolean.TRUE;
        return new JSP("pelupusan/pjbtr/draf_jkbb_pjbtrV2.jsp").addParameter("tab", "true");
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
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
//        stageId = "maklum_arahan_JKBB_PTG" ;
        kodNeg = conf.getProperty("kodNegeri");
        String namaNegeri = new String();
        if (kodNeg.equals("04")) {
            namaNegeri = "Melaka";
        }
        if (kodNeg.equals("05")) {
            namaNegeri = "Negeri Sembilan";
        }
//        logger.info("THIS IS ID PERMOHONAN = " + idPermohonan);       
        permohonan = permohonanDAO.findById(idPermohonan);
        urusanStatus = permohonan.getKodUrusan().getKod();
        if(urusanStatus.equals("PJBTR")){
            tajukHeader = "DRAF RENCANA UNTUK PERTIMBANGAN MESYUARAT JAWATANKUASA BELAH BAHAGI NEGERI " + namaNegeri.toUpperCase() ;
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
                if (stageId.equals("maklum_arahan_JKBB_PTG") || stageId.equals("semak_JKBB_PTG") || stageId.equals("maklum_agihan_JKBB") || stageId.equals("semak_JKBB_PTG") || stageId.equals("maklumat_tambahan_JKBB") || stageId.equals("semak_masuk_bil_kertas_JKBB")) {
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
        }
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            pemohon = pelupusanService.findPemohon(idPermohonan);
            senaraiUlasanJabatanTeknikal = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
            senaraiUlasanJKBB = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
            permohonanKertas = new PermohonanKertas();
            permohonanKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKBB");
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
                    if (mohonRujuk.getUlasan() == null) {
                        mohonRujuk.setUlasan("Tiada Ulasan Diterima");
                    }
                    pelupusanService.simpanPermohonanRujLuar(mohonRujuk);
                }
            }
            tajukUlasanJabatan = "Pentadbir Tanah Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah merujukkan permohonan ini kepada Jabatan-Jabatan Teknikal berkenaan dan ulasannya adalah seperti berikut:-";
            String adunJKBB = "JawatanKuasa Belah Bahagi berkenaan";
            if (senaraiUlasanJKBB.size() > 0) {
                tajukUlasanJKBB = "Pentadbir Tanah Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " telah merujukkan permohonan ini kepada " + adunJKBB + " dan ulasannya adalah seperti berikut:-";
            } else {
                tajukUlasanJKBB = "Permohonan ini tidak memerlukan ulasan daripada YB Adun.";
            }
        
            if (permohonanKertas != null) {
                senaraiLaporanAsasPertimbangan = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
                senaraiLaporanPerihalTanah = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 22);
                senaraiLaporanKandunganButirTanah = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 24);
                senaraiLaporanKandunganLokasiTanah = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 25);
                senaraiLaporanKandunganPerakuanPTD = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
                senaraiLaporanKandunganPerakuanPTG = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 7);
//                logger.info("-------senaraiLaporanKandunganPerihalTanah------" + senaraiLaporanPerihalTanah);

            } else {
                senaraiLaporanPerihalTanah = new Vector();
                senaraiLaporanKandunganButirTanah = new Vector();
                senaraiLaporanKandunganLokasiTanah = new Vector();
                senaraiLaporanAsasPertimbangan = new Vector();
                senaraiLaporanKandunganPerakuanPTD = new Vector();
                senaraiLaporanKandunganPerakuanPTG = new Vector();
            }
            
            if (permohonanKertas != null) {

                boolean checkExistBil0 = false;
                boolean checkExistBil1 = false;
                boolean checkExistSubTajuk211 = false;
                boolean checkExistSubTajuk221 = false;
                boolean checkExistSubTajuk231 = false;
                boolean checkExistBil5 = false;
                boolean checkExistBil6 = false;
                boolean checkExistBil7 = false;
                List<PermohonanKertasKandungan> vecMohonKertas;
                vecMohonKertas = pelupusanService.findByIdKertasOnly(permohonanKertas.getIdKertas());
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
            }
        
    }
    
    public void settingBil0() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =0; 
         */
        
        if (hakmilikPermohonan.getNoLot() != null) {
            tajuk4 = tajuk4 + " " + hakmilikPermohonan.getNoLot();
        } else {
            tajuk4 = "";
        }
        
        tajukMainDraf = tajuk + " DARIPADA " + pemohon.getPihak().getNama() + tajuk4 + " " + hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama() + 
                        " DAERAH " + permohonan.getCawangan().getDaerah().getNama() + noktah;
    }

    public void settingBil1() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =1; 
         */
        tajukTujuanDraf = tujuan + tujuan2 + " " + " daripada " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + noktah;
           
    }

    public void settingBil211() {
        /*
         * SETTING TAJUK DRAF WHICH BIL =2 AND SUBTAJUK = 2.1.1; 
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = permohonan.getInfoAudit().getTarikhMasuk();
        String date2 = sdf.format(date);
        tajukPerihalPermohonan = perihalpermohonan +  perihalpermohonan2 + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama()) + " " + perihalpermohonan3 + date2 + noktah;
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

        tajukPerihalTanah = perihaltanah1 + perihaltanah12 + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getHakmilik().getKodHakmilik().getNama()) + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama()) + " Daerah " + pelUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama()) + " seperti yang bertanda Merah di dalam pelan berkembar." + "\n" + perihaltanah15 + hakmilikPermohonan.getHakmilik().getLuas() + " " + pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getHakmilik().getKodUnitLuas().getNama()) + noktah;
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
        return new JSP("pelupusan/pjbtr/draf_jkbb_pjbtrV2.jsp").addParameter("tab", "true");
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
        return new JSP("pelupusan/pjbtr/draf_jkbb_pjbtrV2.jsp").addParameter("tab", "true");
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
        return new JSP("pelupusan/pjbtr/draf_jkbb_pjbtrV2.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
//        logger.info(permohonankertas.getKodDokumen().getKod());

        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        permohonanKertas.setTajuk("Draf JKBB");
        KodDokumen kod = kodDokumenDAO.findById("JKBB");
        permohonanKertas.setKodDokumen(kod);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonanKertas);

        long a = permohonanKertas.getIdKertas();
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
        pLK.setKertas(permohonanKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanAsasPertimbangan() {
        return senaraiLaporanAsasPertimbangan;
    }

    public void setSenaraiLaporanAsasPertimbangan(List<PermohonanKertasKandungan> senaraiLaporanAsasPertimbangan) {
        this.senaraiLaporanAsasPertimbangan = senaraiLaporanAsasPertimbangan;
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

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTD() {
        return senaraiLaporanKandunganPerakuanPTD;
    }

    public void setSenaraiLaporanKandunganPerakuanPTD(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD) {
        this.senaraiLaporanKandunganPerakuanPTD = senaraiLaporanKandunganPerakuanPTD;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTG() {
        return senaraiLaporanKandunganPerakuanPTG;
    }

    public void setSenaraiLaporanKandunganPerakuanPTG(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG) {
        this.senaraiLaporanKandunganPerakuanPTG = senaraiLaporanKandunganPerakuanPTG;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanPerihalTanah() {
        return senaraiLaporanPerihalTanah;
    }

    public void setSenaraiLaporanPerihalTanah(List<PermohonanKertasKandungan> senaraiLaporanPerihalTanah) {
        this.senaraiLaporanPerihalTanah = senaraiLaporanPerihalTanah;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJKBB() {
        return senaraiUlasanJKBB;
    }

    public void setSenaraiUlasanJKBB(List<PermohonanRujukanLuar> senaraiUlasanJKBB) {
        this.senaraiUlasanJKBB = senaraiUlasanJKBB;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public String getTajukAsasTimbang() {
        return tajukAsasTimbang;
    }

    public void setTajukAsasTimbang(String tajukAsasTimbang) {
        this.tajukAsasTimbang = tajukAsasTimbang;
    }

    public String getTajukHeader() {
        return tajukHeader;
    }

    public void setTajukHeader(String tajukHeader) {
        this.tajukHeader = tajukHeader;
    }

    public String getTajukMainDraf() {
        return tajukMainDraf;
    }

    public void setTajukMainDraf(String tajukMainDraf) {
        this.tajukMainDraf = tajukMainDraf;
    }

    public String getTajukPerakuanPTD() {
        return tajukPerakuanPTD;
    }

    public void setTajukPerakuanPTD(String tajukPerakuanPTD) {
        this.tajukPerakuanPTD = tajukPerakuanPTD;
    }

    public String getTajukPerakuanPTG() {
        return tajukPerakuanPTG;
    }

    public void setTajukPerakuanPTG(String tajukPerakuanPTG) {
        this.tajukPerakuanPTG = tajukPerakuanPTG;
    }

    public String getTajukPerihalPemohon() {
        return tajukPerihalPemohon;
    }

    public void setTajukPerihalPemohon(String tajukPerihalPemohon) {
        this.tajukPerihalPemohon = tajukPerihalPemohon;
    }

    public String getTajukPerihalPermohonan() {
        return tajukPerihalPermohonan;
    }

    public void setTajukPerihalPermohonan(String tajukPerihalPermohonan) {
        this.tajukPerihalPermohonan = tajukPerihalPermohonan;
    }

    public String getTajukPerihalTanah() {
        return tajukPerihalTanah;
    }

    public void setTajukPerihalTanah(String tajukPerihalTanah) {
        this.tajukPerihalTanah = tajukPerihalTanah;
    }

    public String getTajukTujuanDraf() {
        return tajukTujuanDraf;
    }

    public void setTajukTujuanDraf(String tajukTujuanDraf) {
        this.tajukTujuanDraf = tajukTujuanDraf;
    }

    public String getTajukUlasanJKBB() {
        return tajukUlasanJKBB;
    }

    public void setTajukUlasanJKBB(String tajukUlasanJKBB) {
        this.tajukUlasanJKBB = tajukUlasanJKBB;
    }

    public String getTajukUlasanJabatan() {
        return tajukUlasanJabatan;
    }

    public void setTajukUlasanJabatan(String tajukUlasanJabatan) {
        this.tajukUlasanJabatan = tajukUlasanJabatan;
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

    public String getUrusanStatus() {
        return urusanStatus;
    }

    public void setUrusanStatus(String urusanStatus) {
        this.urusanStatus = urusanStatus;
    }

    public boolean isViewOnlyPTD() {
        return viewOnlyPTD;
    }

    public void setViewOnlyPTD(boolean viewOnlyPTD) {
        this.viewOnlyPTD = viewOnlyPTD;
    }

    public boolean isViewOnlyPTDOnly() {
        return viewOnlyPTDOnly;
    }

    public void setViewOnlyPTDOnly(boolean viewOnlyPTDOnly) {
        this.viewOnlyPTDOnly = viewOnlyPTDOnly;
    }

    public boolean isViewOnlyPTG() {
        return viewOnlyPTG;
    }

    public void setViewOnlyPTG(boolean viewOnlyPTG) {
        this.viewOnlyPTG = viewOnlyPTG;
    }

    public boolean isViewOnlyPTGOnly() {
        return viewOnlyPTGOnly;
    }

    public void setViewOnlyPTGOnly(boolean viewOnlyPTGOnly) {
        this.viewOnlyPTGOnly = viewOnlyPTGOnly;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
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
    
    
    
}
