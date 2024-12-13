/*
 * To change this template, choose Tools | Templates
 * and open the template in the .creator (???).
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pelupusan/draf_jksmnns/{currentStage}")
public class DrafJKSMNnsActionBean extends AbleActionBean {

    Logger logger = Logger.getLogger(DrafJKSMNActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    HakmilikPermohonanDAO mohonHakmilikDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    PelupusanService pservice;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private KodCawangan cawangan;
    private Pengguna peng;
    private String stageId;
    private String tajukUtama;
    private String tajuk;
    private String tajuk2;
    private String tajuk3;
    private String tajuk4;
//    Logger logger = Logger.getLogger(DrafJKMActionBean.class);
    private String tujuan;
    private String tujuan2;
    private String tujuan3;
    private String tujuan4;
    private String tujuan5;
    private String mukimNama;
    private String milikNama;
    private String perihaltanah1;
    private String perihaltanah12;
    private String perihaltanah13;
    private String perihaltanah14;
    private String perihaltanah15;
    private String perihaltanah16;
    private String perihaltanah17;
    private String perihaltanah2;
    private String perihaltanah21;
    private String perihaltanah22;
    private String tajukPerihalPermohonan;
    private String perakuan;
    private String ulasan;
    private String ulasan2;
    private String ulasan3;
    private String persidangan;
    private Date tarikh;
    private String masa;
    private String tempat;
    private String tempoh;
    private String kadarBayar;
    private String kadarBayar2;
    private String jumlahBayar;
    private String jumlahBayar2;
    private String jumlahBayar3;
    private String wangCagar;
    private String wangCagar2;
    private String noktah;
    private String idPermohonan;
    private String idPemohon;
    private String noktahbertindih;
    private Long x;
    private String kodNeg;
    private Permohonan permohonan;
    private PermohonanRujukanLuar mohonRujukLuar;
    private PermohonanRujukanLuar mohonRujukLuarPTD;
    private String urusanStatus;
    private String drafDaerah;
    private String tajukHeader;
    private boolean openPTD = false;
    private boolean openPTG = false;
    private boolean viewOnlyPTD = true;
    private boolean viewOnlyPTG = true;
    private Boolean edit;
    private Boolean viewForMMKN = false;
    private List<PihakPengarah> pihakPengarahList;
    private HakmilikPermohonan mohonHakmilik;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalPermohonan = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2 = new Vector();
    private List<PermohonanRujukanLuar> senaraiMohonRujukLuar = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG = new Vector();
    private List<PermohonanKertasKandungan> senaraiSyorPTG = new Vector();
    private List<PermohonanKertasKandungan> penutup = new Vector();
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal = new Vector();
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakBerkepentingan = new Vector();
    private String tajukPerakuanPTD;
    private String tajukPerakuanPTG;
    private LaporanTanah mohonLaportanah;
    private PermohonanKertas mohonKertas;
    private PermohonanRujukanLuar mohonRujukluar;
    private Pemohon pemohon;
    private String namaPemohon;
    private String alamatSuratPemohon;
    private Date tarikhPermohonan;
    private String daerah;
    private String namaPemilik;
    private String mukim;
    private String tanah;
    private String tujuanPermohonan;
    private String ulasanPerihalTanah1;
    private String ulasanPerihalTanah2;
    private String ulasanYBADUNkawasan;
    private String asasPertimbangan;
    private Hakmilik hakmilik;
    private Long idHakmilik;
    private String idHakmiliktemp;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private String namaAgensi;
    private KodDUN adunKawasan;
    private String ptg;
    private String bil_bngn;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<PermohonanRujukanLuar> senaraiYBAdun;
    private List<PermohonanLaporanUlasan> senaraiLaporanUlas = new Vector();
    private PermohonanRujukanLuar mohonRujLuarJKM;
    private KodDUN kodDun;
    private boolean viewMMKN;
    //Add for changing urusan PJLB because of HakmilikPermohonan is getting from permohonan.getPermohonanSebelum
    private Permohonan permohonanSebelum;

    @DefaultHandler
    public Resolution showForm1() {
        edit = Boolean.FALSE;
        viewMMKN = Boolean.FALSE;
        viewForMMKN = Boolean.FALSE;
        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm1() {
        edit = Boolean.TRUE;
        viewMMKN = Boolean.FALSE;
        viewForMMKN = Boolean.FALSE;
        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormMMKN() {

        edit = Boolean.FALSE;
        viewMMKN = Boolean.TRUE;
        viewForMMKN = Boolean.TRUE;
        mohonRujLuarJKM = pservice.findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan);

        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public Resolution refreshDrafJKBB() {
        String editJKM = (String) getContext().getRequest().getParameter("edit");
        rehydrate();
        if (Boolean.parseBoolean(editJKM) != true) {
            edit = Boolean.FALSE;
        } else {
            edit = Boolean.TRUE;
        }


        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
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

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPemohon = String.valueOf(x);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        String currentStage = getContext().getRequest().getParameter("currentStage");
        logger.info("currentStage: " + currentStage);
        if (!StringUtils.isBlank(currentStage) && currentStage.equals("mmknPage")) {
            viewForMMKN = Boolean.TRUE;
        } else if (!StringUtils.isBlank(currentStage) && currentStage.equals("jkmPage")) {
            viewForMMKN = Boolean.FALSE;
        } else {
            viewForMMKN = Boolean.FALSE;
        }
        logger.info("currentStage: " + currentStage);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            drafDaerah = permohonan.getCawangan().getName();
            urusanStatus = permohonan.getKodUrusan().getKod();
            senaraiLaporanUlas = pservice.findUlasan(idPermohonan, "Syarat JKM");
        }

        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonHakmilik = pservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonHakmilik = pservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else {
            mohonHakmilik = pservice.findByIdPermohonan(idPermohonan);
        }
        pemohon = pservice.findPemohonByIdPemohon(idPermohonan);

//        String nolot = mohonHakmilik.getLot().getKod();
        if (mohonHakmilik.getHakmilik() != null) {

            hakmilik = mohonHakmilik.getHakmilik();
            idHakmiliktemp = hakmilik.getIdHakmilik();
            //        idHakmilik = Long.parseLong(idHakmiliktemp);
            senaraiHakmilikPihakBerkepentingan = pservice.getHakmilikPihakBerkepentinganByStringIdHakmilik(idHakmiliktemp);

            //        if(hakmilik != null){
            //        hakmilikPihak = pservice.findHakmilikPihakBerkepentinganByStringIdHakmilik(idHakmiliktemp);
            //        }
        }
        mohonLaportanah = pservice.findLaporanTanahByIdPermohonan(idPermohonan);
        if (mohonLaportanah != null) {
            String test = "" + mohonLaportanah.getBilanganBangunan();
            if (test.equals("")) {
                bil_bngn = "0";
            } else {
                bil_bngn = test;
            }
        }


        mohonRujukLuar = pservice.findPermohonanRujByIdPermohonan(idPermohonan);
        mohonKertas = pservice.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKM");
        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            KodDokumen kodDokumen = kodDokumenDAO.findById("JKM");

            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonKertas.setCawangan(cawangan);
            mohonKertas.setKodDokumen(kodDokumen);
            permohonan.setIdPermohonan(idPermohonan);
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setTajuk("DRAF JKM");
            mohonKertas.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertas(mohonKertas);
        }

        if (pemohon.getPihak() != null) {
            pihakPengarahList = pservice.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
        }
        namaPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama());
        settingDefaultValue();
        settingBil0();
        settingBil2();
        List senaraiLaporanKandunganPTemp = new Vector();
        List senaraiDefaultValue = new Vector();
        tarikhPermohonan = permohonan.getInfoAudit().getTarikhMasuk();
        senaraiUlasanJabatanTeknikal = pservice.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
        List<PermohonanRujukanLuar> senaraiUlasanJbtWithPTD = new ArrayList<PermohonanRujukanLuar>();
        senaraiUlasanJbtWithPTD = pservice.findPermohonanRujLuarByIdPermohonanNADUNWithPTD(idPermohonan, "JTK");
        mohonRujukLuarPTD = senaraiUlasanJbtWithPTD.size() > 0 ? senaraiUlasanJbtWithPTD.get(0) : new PermohonanRujukanLuar();
        senaraiYBAdun = pservice.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
        if (senaraiYBAdun.size() > 0) {
            PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
            prl = senaraiYBAdun.get(0);
            kodDun = pservice.findKodDUNByAgensi(prl.getAgensi().getKod());
        }
        alamatSuratPemohon = " ";
        if (pemohon != null) {
            if (pemohon.getPihak().getAlamat1() != null) {
                alamatSuratPemohon = alamatSuratPemohon + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat1());
            }
            if (pemohon.getPihak().getAlamat2() != null) {
                alamatSuratPemohon = alamatSuratPemohon + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat2());
            }
            if (pemohon.getPihak().getAlamat3() != null) {
                alamatSuratPemohon = alamatSuratPemohon + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat3());
            }
            if (pemohon.getPihak().getPoskod() != null) {
                alamatSuratPemohon = alamatSuratPemohon + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getPoskod());
            }
            if (pemohon.getPihak().getAlamat4() != null) {
                alamatSuratPemohon = alamatSuratPemohon + ", " + pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getAlamat4()) + ".";
            }
        }
        //Bil 1
//        alamatSuratPemohon = pemohon.getPihak().getSuratAlamat1()+" "+ pemohon.getPihak().getSuratAlamat2()+" "+pemohon.getPihak().getSuratAlamat3()+" "+pemohon.getPihak().getSuratAlamat4();



        //Bil 4.1 & 4.2
        if (mohonHakmilik.getKodMilik() != null) {

            if (mohonHakmilik.getKodMilik().getNama() != null) {

                if (mohonHakmilik.getBandarPekanMukimBaru().getNama() != null) {
                    if (mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama() != null) {
                        if (mohonHakmilik.getLuasTerlibat() != null) {
                            if (mohonHakmilik.getKodUnitLuas().getNama() != null) {
                            }
                        }
                    }
                }
            }
            String unitJarakUOM = mohonHakmilik.getUnitJarak() != null ? mohonHakmilik.getUnitJarak().getNama() : new String();
            ulasanPerihalTanah1 = perihaltanah1 + perihaltanah12 + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodMilik().getNama())
                    + " Lot " + mohonHakmilik.getNoLot() + ", " + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getNama()) + perihaltanah14 + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama())
                    + perihaltanah15 + mohonHakmilik.getLuasTerlibat() + " " + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodUnitLuas().getNama());
            ulasanPerihalTanah2 = perihaltanah2 + mohonHakmilik.getLokasi() + perihaltanah21 + mohonHakmilik.getJarak() + " " + unitJarakUOM + " " + perihaltanah22 + mohonHakmilik.getJarakDari() + " " + mohonHakmilik.getJarakDariNama();
            if (permohonan.getKodUrusan().getKod().equals("LSTP")) {
                asasPertimbangan = "Seksyen 81 Enakmen Mineral Negeri 2002 telah menetapkan bahawa semua permohonan Lesen Melombong Tuan Punya perlu "
                    + "dibawa ke Mesyuarat Jawatankuasa Sumber Mineral Negeri, Negeri Sembilan untuk mendapatkan syor sebelum diangkat untuk "
                    + "pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan Darul Khusus.";
            } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                asasPertimbangan = "Seksyen 81 Enakmen Mineral Negeri 2002 telah menetapkan bahawa semua permohonan Lesen Pajakan Melombong perlu "
                        + "dibawa ke Mesyuarat Jawatankuasa Sumber Mineral Negeri, Negeri Sembilan untuk mendapatkan syor sebelum diangkat untuk "
                        + "pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan Darul Khusus.";
            } else if (permohonan.getKodUrusan().getKod().equals("PCRG")) {
                asasPertimbangan = "Seksyen 41 Enakmen Mineral Negeri 2002 telah menetapkan bahawa semua permohonan Lesen Permit Mencarigali perlu "
                        + "dibawa ke Mesyuarat Jawatankuasa Sumber Mineral Negeri, Negeri Sembilan untuk mendapatkan syor sebelum diangkat untuk "
                        + "pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan Darul Khusus.";
            } else if (permohonan.getKodUrusan().getKod().equals("LPJH")) {
                asasPertimbangan = "Seksyen 41 Enakmen Mineral Negeri 2002 telah menetapkan bahawa semua permohonan Lesen Penjelajahan perlu "
                        + "dibawa ke Mesyuarat Jawatankuasa Sumber Mineral Negeri, Negeri Sembilan untuk mendapatkan syor sebelum diangkat untuk "
                        + "pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan Darul Khusus.";
            }
        } else {
            if (mohonHakmilik.getBandarPekanMukimBaru().getNama() != null) {
                if (mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama() != null) {
                    if (mohonHakmilik.getLuasTerlibat() != null) {
                        if (mohonHakmilik.getKodUnitLuas().getNama() != null) {
                        }
                    }
                }
            }
            String unitJarakUOM = mohonHakmilik.getUnitJarak() != null ? mohonHakmilik.getUnitJarak().getNama() : new String();
            ulasanPerihalTanah1 = perihaltanah1 + perihaltanah12
                    + " Lot " + mohonHakmilik.getNoLot() + ", " + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getNama()) + perihaltanah14 + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama())
                    + perihaltanah15 + mohonHakmilik.getLuasTerlibat() + " " + pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodUnitLuas().getNama());
            ulasanPerihalTanah2 = perihaltanah2 + mohonHakmilik.getLokasi() + perihaltanah21 + mohonHakmilik.getJarak() + " " + unitJarakUOM + " " + perihaltanah22 + mohonHakmilik.getJarakDari() + " " + mohonHakmilik.getJarakDariNama();
        }


        //    System.out.println("-----------------"+sdf.format(mohonRujukLuar.getInfoAudit().getTarikhMasuk())+"---");
        //Bil 7
        if (mohonRujukLuar != null) {
            ulasanYBADUNkawasan = ulasan2 + namaAgensi + ulasan3 + sdf.format(mohonRujukLuar.getInfoAudit().getTarikhMasuk());
        } else {
            ulasanYBADUNkawasan = new String();
        }
//        namaPemilik = hakmilikPihak.getNama();

        if (mohonKertas != null) {
            /*
             * FOR BIL 2 AND 2.3.%
             * TUJUAN PERMOHONAN
             */
            senaraiLaporanKandunganPerihalPermohonan = pservice.findByIdLapor(mohonKertas.getIdKertas(), 3);
            if (!permohonan.getKodUrusan().getKod().equals("LMCRG") && !permohonan.getKodUrusan().getKod().equals("PJLB") && !permohonan.getKodUrusan().getKod().equals("MPJLB") && !permohonan.getKodUrusan().getKod().equals("PCRG")) {
                senaraiLaporanKandunganPerakuanPTD = pservice.findByIdLapor(mohonKertas.getIdKertas(), 9);
            }
            senaraiLaporanKandunganPerakuanPTG = pservice.findByIdLapor(mohonKertas.getIdKertas(), 8);
            senaraiSyorPTG = pservice.findByIdLapor(mohonKertas.getIdKertas(), 10);
            penutup = pservice.findByIdLapor(mohonKertas.getIdKertas(), 11);
        }
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("PCRG")) {
            senaraiMohonRujukLuar = pservice.findPermohonanRujLuarByIdPermohonanWithPTD(idPermohonan);
        }

        logger.info("-------mohon kertas----" + mohonKertas.getIdKertas());

        mohonKertas = pservice.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKM");

        List<PermohonanKertasKandungan> plk0 = pservice.findByIdLapor(mohonKertas.getIdKertas(), 2);
        List<PermohonanKertasKandungan> plkTajuk = pservice.findByIdLapor(mohonKertas.getIdKertas(), 0);
        List<PermohonanKertasKandungan> plkPerihalTanah1 = pservice.findByIdLapor(mohonKertas.getIdKertas(), 4);



        if (plk0.isEmpty()) {
            PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
            kertasK0.setCawangan(cawangan);
//            kertasK0.setInfoAudit(infoAudit);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(2);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(tujuanPermohonan);
            kertasK0.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertasKandungan(kertasK0);
        } else {
//            PermohonanKertasKandungan kertasK0 = plk0.get(0);  komen pada 11 Julai 2013
//            kertasK0.setKandungan(tujuanPermohonan);
//            pservice.simpanPermohonanKertasKandungan(kertasK0);
        }

//        save tajuk
        if (plkTajuk.isEmpty()) {
            PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
            kertasK0.setCawangan(cawangan);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(0);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(tajukUtama);
            kertasK0.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertasKandungan(kertasK0);
        }
        if (plkPerihalTanah1.isEmpty()) {
            PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
            kertasK0.setCawangan(cawangan);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(4);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(ulasanPerihalTanah1);
            kertasK0.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertasKandungan(kertasK0);

            PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
            kertasK1.setCawangan(cawangan);
            kertasK1.setKertas(mohonKertas);
            kertasK1.setBil(4);
            kertasK1.setSubtajuk("2");
            kertasK1.setKandungan(ulasanPerihalTanah2);
            kertasK1.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertasKandungan(kertasK1);
        } else {
//             PermohonanKertasKandungan kertasK0 = plkPerihalTanah1.get(0);
//             PermohonanKertasKandungan kertasK1 = plkPerihalTanah1.get(1);
//             if(kertasK0.getSubtajuk().equals("1")){
//                 kertasK0.setKandungan(ulasanPerihalTanah1);
//                 pservice.simpanPermohonanKertasKandungan(kertasK0);
//             }
//             if(kertasK1.getSubtajuk().equals("2")){
//                 kertasK1.setKandungan(ulasanPerihalTanah2);
//                 pservice.simpanPermohonanKertasKandungan(kertasK1);
//             }
        }
        mukimNama = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getNama());
        if (mohonHakmilik.getKodMilik() != null) {
            milikNama = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodMilik().getNama());
        }

    }

    public void settingDefaultValue() {

        noktah = ".";
        noktahbertindih = ":";
        if (urusanStatus.equals("PCRG")) {
            tajuk = "PERMOHONAN DARIPADA " + pemohon.getPihak().getNama() + " UNTUK MENDAPATKAN LESEN MENCARIGALI DI ATAS TANAH ";
            tajuk2 = " LOT " + mohonHakmilik.getNoLot();
            tajuk3 = ", DAERAH ";
        } else if (urusanStatus.equals("LPJH")) {
            tajuk = "PERMOHONAN DARIPADA " + pemohon.getPihak().getNama() + " UNTUK MENDAPATKAN LESEN PENJELAJAHAN DI ATAS TANAH ";
            tajuk2 = " LOT " + mohonHakmilik.getNoLot();
            tajuk3 = ", DAERAH ";
        } else if (urusanStatus.equals("PJLB")) {
            tajuk = "PERMOHONAN DARIPADA " + pemohon.getPihak().getNama() + " UNTUK MENDAPATKAN LESEN PAJAKAN MELOMBONG DI ATAS TANAH ";
            tajuk2 = " LOT " + mohonHakmilik.getNoLot();
            tajuk3 = ", DAERAH ";
            tajuk4 = " UNTUK TUJUAN JUALAN";
        } else if (urusanStatus.equals("LSTP")) {
            tajuk = "PERMOHONAN DARIPADA " + pemohon.getPihak().getNama() + " UNTUK MENDAPATKAN LESEN MELOMBONG TUAN PUNYA ";
            tajuk2 = " LOT " + mohonHakmilik.getNoLot();
            tajuk3 = ", DAERAH ";
            tajuk4 = " UNTUK TUJUAN JUALAN";
        } else if (urusanStatus.equals("MPJLB")) {
            tajuk = "PERMOHONAN DARIPADA " + pemohon.getPihak().getNama() + " UNTUK MEMPERBAHARUI LESEN PAJAKAN MELOMBONG DI ATAS TANAH ";
            tajuk2 = " LOT " + mohonHakmilik.getNoLot();
            tajuk3 = ", DAERAH ";
            tajuk4 = " UNTUK TUJUAN JUALAN";
        }

        if (viewForMMKN) {
            tujuan = "Tujuan permohonan ini adalah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri mengenai permohonan daripada " + namaPemohon;
        } else {
            tujuan = "Tujuan permohonan ini adalah untuk mendapatkan syor Jawatankuasa Sumber Mineral Negeri Sembilan Darul Khusus terhadap permohonan daripada " + namaPemohon + " bagi rayuan";
        }
        if (urusanStatus.equals("PJLB")) {
            tujuan2 = " untuk mendapatkan Lesen Pajakan Melombong di atas tanah ";
            tujuan3 = ", ";
            tujuan4 = ", Daerah ";
        } else if (urusanStatus.equals("MPJLB")) {
            tujuan2 = " untuk memperbaharui Lesen Pajakan Melombong di atas tanah ";
            tujuan3 = ", ";
            tujuan4 = ", Daerah ";
        } else if (urusanStatus.equals("LSTP")) {
            tujuan2 = " untuk mendapatkan Lesen Melombong Tuan Punya di atas tanah ";
            tujuan3 = ", ";
            tujuan4 = ", Daerah ";
        } else if (urusanStatus.equals("LPJH")) {
            tujuan2 = " untuk mendapatkan Lesen Penjelajahan di atas tanah ";
            tujuan3 = ", ";
            tujuan4 = ", Daerah ";
        } else if (urusanStatus.equals("PCRG")) {
            tujuan2 = " untuk mendapatkan Lesen Permit Mencarigali di atas tanah ";
            tujuan3 = ", ";
            tujuan4 = ", Daerah ";
        }

        String sebabPermohonan = !StringUtils.isEmpty(permohonan.getSebab()) ? permohonan.getSebab() : new String();

        if (urusanStatus.equals("PJLB") || urusanStatus.equals("MPJLB")) {
            tujuan5 = " untuk tujuan " + sebabPermohonan;
        } else {
            tujuan5 = " ";
        }

        perihaltanah1 = "Bahan mineral iaitu " + permohonan.getCatatan();
        perihaltanah12 = " yang hendak dikeluarkan adalah di atas tanah ";
        perihaltanah13 = ", Mukim ";
        perihaltanah14 = ", Daerah ";
        perihaltanah15 = " seperti yang bertanda merah di dalam pelan berkembar. Keseluruhan keluasan kawasan yang dipohon adalah lebih kurang ";
        perihaltanah16 = " hektar/ ";
        perihaltanah17 = " ekar ";

        perihaltanah2 = " Tanah berkenaan terletak di ";
        perihaltanah21 = ", lebih kurang ";
        perihaltanah22 = " dari ";



        perakuan = "Mesyuarat Jawatankuasa Mineral Negeri Sembilan diminta menimbangkan permohonan ini.";

        ulasan = " YB ADUN Kawasan ";
        ulasan2 = " Ulasan ke ";
        ulasan3 = " telah dihantar pada ";

        ptg = "Mesyuarat Jawatankuasa Mineral Negeri Sembilan diminta menimbangkan permohonan ini. ";

        kadarBayar = "KadarBayaran ";
        kadarBayar2 = " RM 2.00 semeter padu ";

        jumlahBayar = "Jumlah bayaran yang dikenakan";
        jumlahBayar2 = " RM 2.00 x ";

        jumlahBayar3 = " =RM ";

        wangCagar = "Wang Cagaran yang dikenakan ";
        wangCagar2 = " RM ";
    }
    /*
     * SETTING TAJUK DRAF WHICH BIL =0;
     */

    public void settingBil0() {
        namaPemohon = pemohon.getPihak().getNama();
        if (mohonHakmilik.getKodMilik() != null) {
            tanah = mohonHakmilik.getKodMilik().getNama().toUpperCase();
        }
        mukim = mohonHakmilik.getBandarPekanMukimBaru().getNama();
        daerah = mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama();
        tajukUtama = tajuk + tanah + tajuk2 + " DI " + mukim + tajuk3 + daerah + ", NEGERI SEMBILAN" + noktah;

    }

    public void settingBil2() {
        namaPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama());
        if (mohonHakmilik.getKodMilik() != null) {
            tanah = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodMilik().getNama());
        }
        mukim = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getNama());
        daerah = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama());
        if (permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("LSTP")) {
            tujuanPermohonan = tujuan + tujuan2 + tanah + tujuan3 + " Lot " + mohonHakmilik.getNoLot() + "," + mukim + tujuan4 + daerah + tujuan5 + " seluas lebih kurang " + mohonHakmilik.getLuasTerlibat() + " " + mohonHakmilik.getKodUnitLuas().getNama() + " mengikut Seksyen 81 Enakmen Mineral Negeri 2002 " + noktah;
        } else if (permohonan.getKodUrusan().getKod().equals("PCRG") || permohonan.getKodUrusan().getKod().equals("LPJH")) {
            tujuanPermohonan = tujuan + tujuan2 + tanah + tujuan3 + " Lot " + mohonHakmilik.getNoLot() + "," + mukim + tujuan4 + daerah + tujuan5 + " seluas lebih kurang " + mohonHakmilik.getLuasTerlibat() + " " + mohonHakmilik.getKodUnitLuas().getNama() + " mengikut Seksyen 41 Enakmen Mineral Negeri 2002 " + noktah;
        }

    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        switch (index) {
            case 3://PERIHAL PERMOHONAN
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                senaraiLaporanKandunganPerihalPermohonan.add(pkk);

                break;
            case 4: // PERIHAL TANAH
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiLaporanKandunganPerihalTanah.add(pkk);
                break;
            case 9: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 9);
                senaraiLaporanKandunganPerakuanPTD.add(pkk);
                break;
            case 10: // FOR PERAKUAN PENGARAH TANAH DAN GALIAN

                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 8);
                senaraiLaporanKandunganPerakuanPTG.add(pkk);
                break;
            case 11: // FOR SYOR PENGARAH TANAH DAN GALIAN

                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 10);
                senaraiSyorPTG.add(pkk);
                break;
            case 12: // FOR PENUTUP PTG

                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 11);
                penutup.add(pkk);
                break;
            default:
        }
        System.out.println(index);

        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
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
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPTG() {


        logger.info("------------Simpan Hurian started-----------::");


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        mohonKertas = pservice.findKertasByKod(idPermohonan, "PJLB");

        logger.info("-------Simpan---PTG--in--permohonankertas--------------::");


        if (mohonKertas != null) {
            logger.info("------if------permohonankertas NOT Null--------------::" + mohonKertas);
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pservice.simpanPermohonanKertas(mohonKertas);


            senaraiLaporanKandunganPerakuanPTD = pservice.findByIdLapor(mohonKertas.getIdKertas(), 9);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganPerakuanPTD.size() != 0 && i <= senaraiLaporanKandunganPerakuanPTD.size()) {
                    Long id = senaraiLaporanKandunganPerakuanPTD.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(peng);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(9);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("9." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                }
                permohonanKertasKandungan1.setKertas(mohonKertas);
                String kandungan = getContext().getRequest().getParameter("kandungan9" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }


            senaraiLaporanKandunganPerakuanPTG = pservice.findByIdLapor(mohonKertas.getIdKertas(), 8);

            kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount10"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandunganPerakuanPTG.size() != 0 && i <= senaraiLaporanKandunganPerakuanPTG.size()) {
                    Long id = senaraiLaporanKandunganPerakuanPTG.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(peng);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(8);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("8." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                }
                permohonanKertasKandungan1.setKertas(mohonKertas);
                String kandungan = getContext().getRequest().getParameter("kandungan10" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");

//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {

            case 3://PERIHAL PERMOHONAN
                updateKandungan(3, kand);
                break;
            case 4: // PERIHAL TANAH
                updateKandungan(4, kand);
                break;
            case 9: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                updateKandungan(9, kand);
                break;
            case 10: // FOR PERAKUAN PENGARAH TANAH DAN GALIAN
                updateKandungan(8, kand);
                break;
            case 11: // FOR SYOR PENGARAH TANAH DAN GALIAN
                updateKandungan(10, kand);
                break;
            case 12: // FOR PENUTUP PENGARAH TANAH DAN GALIAN
                updateKandungan(11, kand);
                break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/pcrg/draf_jkm_ns.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());


        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
//        if (permohonan.getKodUrusan().getKod().equals("JPLB")) {
//            mohonKertas.setTajuk("KERTAS JKM");
//            KodDokumen kod = kodDokumenDAO.findById("RMN");
//            mohonKertas.setKodDokumen(kod);
//        } 
        permohonan.setIdPermohonan(idPermohonan);
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setIdKertas(mohonKertas.getIdKertas());
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        mohonKertas.setTajuk(tajuk);

        pelPtService.simpanPermohonanKertas(mohonKertas);

        long a = mohonKertas.getIdKertas();
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
//        System.out.println(pLK.getIdKandungan());


        mohonKertas.setIdKertas(mohonKertas.getIdKertas());
        pLK.setKertas(mohonKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }

    public String getAlamatSuratPemohon() {
        return alamatSuratPemohon;
    }

    public void setAlamatSuratPemohon(String alamatSuratPemohon) {
        this.alamatSuratPemohon = alamatSuratPemohon;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getDrafDaerah() {
        return drafDaerah;
    }

    public void setDrafDaerah(String drafDaerah) {
        this.drafDaerah = drafDaerah;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public Long getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(Long idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public LaporanTanah getMohonLaportanah() {
        return mohonLaportanah;
    }

    public void setMohonLaportanah(LaporanTanah mohonLaportanah) {
        this.mohonLaportanah = mohonLaportanah;
    }

    public PermohonanRujukanLuar getMohonRujukLuar() {
        return mohonRujukLuar;
    }

    public void setMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        this.mohonRujukLuar = mohonRujukLuar;
    }

    public PermohonanRujukanLuar getMohonRujukluar() {
        return mohonRujukluar;
    }

    public void setMohonRujukluar(PermohonanRujukanLuar mohonRujukluar) {
        this.mohonRujukluar = mohonRujukluar;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihakBerkepentingan() {
        return senaraiHakmilikPihakBerkepentingan;
    }

    public void setSenaraiHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakBerkepentingan) {
        this.senaraiHakmilikPihakBerkepentingan = senaraiHakmilikPihakBerkepentingan;
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

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
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

    public String getPerihaltanah16() {
        return perihaltanah16;
    }

    public void setPerihaltanah16(String perihaltanah16) {
        this.perihaltanah16 = perihaltanah16;
    }

    public String getPerihaltanah17() {
        return perihaltanah17;
    }

    public void setPerihaltanah17(String perihaltanah17) {
        this.perihaltanah17 = perihaltanah17;
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

    public String getPerihaltanah22() {
        return perihaltanah22;
    }

    public void setPerihaltanah22(String perihaltanah22) {
        this.perihaltanah22 = perihaltanah22;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public String getPtg() {
        return ptg;
    }

    public void setPtg(String ptg) {
        this.ptg = ptg;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
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

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerihalPermohonan() {
        return senaraiLaporanKandunganPerihalPermohonan;
    }

    public void setSenaraiLaporanKandunganPerihalPermohonan(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalPermohonan) {
        this.senaraiLaporanKandunganPerihalPermohonan = senaraiLaporanKandunganPerihalPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerihalTanah() {
        return senaraiLaporanKandunganPerihalTanah;
    }

    public void setSenaraiLaporanKandunganPerihalTanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah) {
        this.senaraiLaporanKandunganPerihalTanah = senaraiLaporanKandunganPerihalTanah;
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

    public String getTajukHeader() {
        return tajukHeader;
    }

    public void setTajukHeader(String tajukHeader) {
        this.tajukHeader = tajukHeader;
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

    public String getTajukPerihalPermohonan() {
        return tajukPerihalPermohonan;
    }

    public void setTajukPerihalPermohonan(String tajukPerihalPermohonan) {
        this.tajukPerihalPermohonan = tajukPerihalPermohonan;
    }

    public String getTajukUtama() {
        return tajukUtama;
    }

    public void setTajukUtama(String tajukUtama) {
        this.tajukUtama = tajukUtama;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getBil_bngn() {
        return bil_bngn;
    }

    public void setBil_bngn(String bil_bngn) {
        this.bil_bngn = bil_bngn;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getIdHakmiliktemp() {
        return idHakmiliktemp;
    }

    public void setIdHakmiliktemp(String idHakmiliktemp) {
        this.idHakmiliktemp = idHakmiliktemp;
    }

    public KodDUN getAdunKawasan() {
        return adunKawasan;
    }

    public void setAdunKawasan(KodDUN adunKawasan) {
        this.adunKawasan = adunKawasan;
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

    public String getTujuanPermohonan() {
        return tujuanPermohonan;
    }

    public void setTujuanPermohonan(String tujuanPermohonan) {
        this.tujuanPermohonan = tujuanPermohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUlasan2() {
        return ulasan2;
    }

    public void setUlasan2(String ulasan2) {
        this.ulasan2 = ulasan2;
    }

    public String getUlasan3() {
        return ulasan3;
    }

    public void setUlasan3(String ulasan3) {
        this.ulasan3 = ulasan3;
    }

    public String getUlasanPerihalTanah1() {
        return ulasanPerihalTanah1;
    }

    public void setUlasanPerihalTanah1(String ulasanPerihalTanah1) {
        this.ulasanPerihalTanah1 = ulasanPerihalTanah1;
    }

    public String getUlasanPerihalTanah2() {
        return ulasanPerihalTanah2;
    }

    public void setUlasanPerihalTanah2(String ulasanPerihalTanah2) {
        this.ulasanPerihalTanah2 = ulasanPerihalTanah2;
    }

    public String getUlasanYBADUNkawasan() {
        return ulasanYBADUNkawasan;
    }

    public void setUlasanYBADUNkawasan(String ulasanYBADUNkawasan) {
        this.ulasanYBADUNkawasan = ulasanYBADUNkawasan;
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

    public boolean isViewOnlyPTG() {
        return viewOnlyPTG;
    }

    public void setViewOnlyPTG(boolean viewOnlyPTG) {
        this.viewOnlyPTG = viewOnlyPTG;
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

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<PermohonanRujukanLuar> getSenaraiYBAdun() {
        return senaraiYBAdun;
    }

    public void setSenaraiYBAdun(List<PermohonanRujukanLuar> senaraiYBAdun) {
        this.senaraiYBAdun = senaraiYBAdun;
    }

    public KodDUN getKodDun() {
        return kodDun;
    }

    public void setKodDun(KodDUN kodDun) {
        this.kodDun = kodDun;
    }

    public boolean isViewMMKN() {
        return viewMMKN;
    }

    public void setViewMMKN(boolean viewMMKN) {
        this.viewMMKN = viewMMKN;
    }

    public PermohonanRujukanLuar getMohonRujLuarJKM() {
        return mohonRujLuarJKM;
    }

    public void setMohonRujLuarJKM(PermohonanRujukanLuar mohonRujLuarJKM) {
        this.mohonRujLuarJKM = mohonRujLuarJKM;
    }

    public String getMilikNama() {
        return milikNama;
    }

    public void setMilikNama(String milikNama) {
        this.milikNama = milikNama;
    }

    public String getMukimNama() {
        return mukimNama;
    }

    public void setMukimNama(String mukimNama) {
        this.mukimNama = mukimNama;
    }

    public PermohonanRujukanLuar getMohonRujukLuarPTD() {
        return mohonRujukLuarPTD;
    }

    public void setMohonRujukLuarPTD(PermohonanRujukanLuar mohonRujukLuarPTD) {
        this.mohonRujukLuarPTD = mohonRujukLuarPTD;
    }

    public List<PermohonanRujukanLuar> getSenaraiMohonRujukLuar() {
        return senaraiMohonRujukLuar;
    }

    public void setSenaraiMohonRujukLuar(List<PermohonanRujukanLuar> senaraiMohonRujukLuar) {
        this.senaraiMohonRujukLuar = senaraiMohonRujukLuar;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanUlas() {
        return senaraiLaporanUlas;
    }

    public void setSenaraiLaporanUlas(List<PermohonanLaporanUlasan> senaraiLaporanUlas) {
        this.senaraiLaporanUlas = senaraiLaporanUlas;
    }

    public Boolean getViewForMMKN() {
        return viewForMMKN;
    }

    public void setViewForMMKN(Boolean viewForMMKN) {
        this.viewForMMKN = viewForMMKN;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public String getAsasPertimbangan() {
        return asasPertimbangan;
    }

    public void setAsasPertimbangan(String asasPertimbangan) {
        this.asasPertimbangan = asasPertimbangan;
    }

    public List<PermohonanKertasKandungan> getSenaraiSyorPTG() {
        return senaraiSyorPTG;
    }

    public void setSenaraiSyorPTG(List<PermohonanKertasKandungan> senaraiSyorPTG) {
        this.senaraiSyorPTG = senaraiSyorPTG;
    }

    public List<PermohonanKertasKandungan> getPenutup() {
        return penutup;
    }

    public void setPenutup(List<PermohonanKertasKandungan> penutup) {
        this.penutup = penutup;
    }
}

