/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import etanah.model.Dokumen;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import etanah.model.KodBandarPekanMukim;
import etanah.service.common.TanahRizabService;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.model.TanahRizabPermohonan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.strata.CommonService;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/laporanTanahPengambilan_sblm")
public class LaporanTanahPengambilanSblmActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodJenisPendudukanDAO kodJenisPendudukanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    CommonService commonService;
    private static final Logger LOG = Logger.getLogger(LaporanTanahPengambilanSblmActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Hakmilik hakmilik;
    private HakmilikUrusan hakmilikUrusan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private Permohonan p;
    private LaporanTanah laporanTanah;
    private LaporanTanah laporanTanahTemp;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private String idHakmilik;
    private String noLot;
    private String noLitho;
    private String noWarta;
    private String lokasi;
    private String tarikhWarta;
    private String ulasan;
    private char pandanganImej;
    private char jenisKegunaan;
    private String adaPecahSempadan;
    private char adaJalanMasuk;
    private BigDecimal usahaLuas;
    private Integer usahaBilanganPokok = new Integer(0);
    private BigDecimal usahaHarga;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList;
    private String pemilik;
    private String penyewaBangunan;
    private String penyewaTanah;
    private String kategori;
    private String ulasan1;
    private String ulasan2;
    private String ulasan3;
    private PermohonanLaporanUlasan laporanUlasan;
    private PermohonanLaporanUlasan laporanUlasanPPT;
    private PermohonanLaporanUlasan laporanUlasanSOCIAL;
    private PermohonanLaporanUlasan laporanUlasanPPTK;
    private PermohonanLaporanUlasan laporanUlasanTemp;
    private FileBean fileToBeUpload;
    private String catatan;
    private Permohonan pSebelum;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        return new JSP("pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP("pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");

    }

    //    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getPermohonanSebelum() != null) {
                pSebelum = permohonan.getPermohonanSebelum();


                laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(pSebelum.getIdPermohonan(), "PPT", "24");

                if (laporanUlasanPPT == null) {
                    laporanUlasanPPT = SimpanLaporanUlasanSblm("PPT");
                }
                if (laporanUlasanPPT != null && laporanUlasanPPT.getUlasan() != null) {
                    ulasan1 = laporanUlasanPPT.getUlasan();
                }


                laporanUlasanSOCIAL = laporanTanahService.getLaporUlasanByIdMohonJenis(pSebelum.getIdPermohonan(), "SOCIAL", "228");

                if (laporanUlasanSOCIAL == null) {
                    laporanUlasanSOCIAL = SimpanLaporanUlasanSblm("SOCIAL");
                }
                if (laporanUlasanSOCIAL != null && laporanUlasanSOCIAL.getUlasan() != null) {
                    ulasan2 = laporanUlasanSOCIAL.getUlasan();
                }
                laporanUlasanPPTK = laporanTanahService.getLaporUlasanByIdMohonJenis(pSebelum.getIdPermohonan(), "PPTK", "32");
//                
                if (laporanUlasanPPTK == null) {
                    laporanUlasanPPTK = SimpanLaporanUlasanSblm("PPTK");
                }
                if (laporanUlasanPPTK != null && laporanUlasanPPTK.getUlasan() != null) {
                    ulasan3 = laporanUlasanPPTK.getUlasan();
                }

            }


        }


        String format1 = "image/jpeg";
        String format2 = "image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pguna.getNama());

    }

    public PermohonanLaporanUlasan SimpanLaporanUlasanSblm(String jenisUlasan) {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kod = "";
//        if(pguna.getKodCawangan().getKod().equals("00")){
//            kod = "104";
//        }else{
        kod = "228";
//        }

        PermohonanLaporanUlasan laporanUlasanSBLM = new PermohonanLaporanUlasan();

        if (permohonan.getPermohonanSebelum() != null) {
            laporanUlasanSBLM = laporanTanahService.getLaporUlasanByIdMohonJenis(permohonan.getPermohonanSebelum().getIdPermohonan(), jenisUlasan, kod);
            if (laporanUlasanSBLM != null) {

                laporanUlasanTemp = new PermohonanLaporanUlasan();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanUlasanTemp.setInfoAudit(infoAudit);
                laporanUlasanTemp.setPermohonan(permohonan);
                laporanUlasanTemp.setCawangan(permohonan.getCawangan());
                laporanUlasanTemp.setJenisUlasan(laporanUlasanSBLM.getJenisUlasan());
                laporanUlasanTemp.setKodPeranan(laporanUlasanSBLM.getKodPeranan());
                if (laporanUlasanSBLM.getUlasan() != null) {
                    laporanUlasanTemp.setUlasan(laporanUlasanSBLM.getUlasan());
                }
                laporanTanahService.simpanLaporanUlasan(laporanUlasanTemp);

            }

        }
        return laporanUlasanTemp;
    }

    public Resolution hakmilikDetails() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        p = permohonanDAO.findById(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanPUList = new ArrayList<PermohonanLaporanBangunan>();

//        if (p.getPermohonanSebelum() != null && idHakmilik != null) {
//            permohonan = p.getPermohonanSebelum();
        if (permohonan != null && idHakmilik != null) {
            hakmilikUrusan = hakmilikUrusanService.findByIdPerserahanIdHakmilik(pSebelum.getIdPermohonan(), idHakmilik);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(pSebelum.getIdPermohonan(), idHakmilik);
//            if(pguna.getKodCawangan().getKod().equals("00")){
//                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(permohonan.getIdPermohonan(),hakmilikPermohonan.getId(),"PTG");
//            }else{
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(pSebelum.getIdPermohonan(), hakmilikPermohonan.getId());
//            }
            if (laporanTanah == null) {
                laporanTanah = SimpanLaporanTanahSblm();
            }
            if (laporanTanah != null) {
                //uncomment this for ulasan
                laporanUlasan = laporanTanahService.getLaporUlasanByIdMohonidLaporan(pSebelum.getIdPermohonan(), laporanTanah.getIdLaporan());
                if (laporanUlasan != null) {
                    ulasan = laporanUlasan.getUlasan();
                }
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                if (laporanTanah.getAdaBangunan() != null && laporanTanah.getAdaBangunan() == 'Y') {
                    jenisKegunaan = 'B';
                    permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                }
                if (laporanTanah.getUsaha() != null && laporanTanah.getUsaha() == 'Y') {
                    jenisKegunaan = 'P';
                    usahaLuas = laporanTanah.getUsahaLuas();
                    if (laporanTanah.getUsahaBilanganPokok() != null) {
                        usahaBilanganPokok = laporanTanah.getUsahaBilanganPokok();
                    }
                    usahaHarga = laporanTanah.getUsahaHarga();
                    permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                }
                if (laporanTanah.getPerusahaan() != null && laporanTanah.getPerusahaan().equalsIgnoreCase("Y")) {
                    jenisKegunaan = 'U';
                    permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                }
                if (laporanTanah.getAdaJalanMasuk() != null) {
                    adaJalanMasuk = laporanTanah.getAdaJalanMasuk();
                }
                adaPecahSempadan = laporanTanah.getAdaPecahSempadan();
            }
        }
        return new JSP("pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");
    }

    public LaporanTanah SimpanLaporanTanahSblm() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<PermohonanLaporanBangunan> permohonanLaporanBangunanSblmList = new ArrayList<PermohonanLaporanBangunan>();
        List<ImejLaporan> utaraImejLaporanSblmList = new ArrayList<ImejLaporan>();
        List<ImejLaporan> selatanImejLaporanSblmList = new ArrayList<ImejLaporan>();
        List<ImejLaporan> timurImejLaporanSblmList = new ArrayList<ImejLaporan>();
        List<ImejLaporan> baratImejLaporanSblmList = new ArrayList<ImejLaporan>();

        LaporanTanah laporanTanahSblm = new LaporanTanah();

        if (permohonan.getPermohonanSebelum() != null) {
            HakmilikPermohonan hakmilikPermohonanSblm = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik());
            System.out.println("hakmilikPermohonanSblm" + hakmilikPermohonanSblm);
            System.out.println("permohonan" + permohonan.getPermohonanSebelum().getIdPermohonan());
            System.out.println("id hakmilik" + hakmilik.getIdHakmilik());
            if (pguna.getKodCawangan().getKod().equals("00")) {
                laporanTanahSblm = laporanTanahService.findLaporanTanahByIdMohonIdMH(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilikPermohonanSblm.getId());
                System.out.println("PTG");
            } else {
                laporanTanahSblm = laporanTanahService.findLaporanTanahByIdMohonIdMH(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilikPermohonanSblm.getId());
                System.out.println("PTD");
                System.out.println("laporan tanah sebelum" + laporanTanahSblm);
            }
            if (laporanTanahSblm != null) {
                System.out.println("1");
                laporanTanahTemp = new LaporanTanah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanTanahTemp.setInfoAudit(infoAudit);
                laporanTanahTemp.setPermohonan(permohonan);
                laporanTanahTemp.setHakmilikPermohonan(hakmilikPermohonan);
                if (pguna.getKodCawangan().getKod().equals("00")) {
                    laporanTanahTemp.setLaporanUntuk("PTG");
                } else {
                    laporanTanahTemp.setLaporanUntuk("PTD");
                }
                if (laporanTanahSblm.getAdaBangunan() != null && laporanTanahSblm.getAdaBangunan() == 'Y') {
                    laporanTanahTemp.setAdaBangunan('Y');

                }
                if (laporanTanahSblm.getUsaha() != null && laporanTanahSblm.getUsaha() == 'Y') {
                    laporanTanahTemp.setUsaha('Y');
                    laporanTanahTemp.setUsahaTanam(laporanTanahSblm.getUsahaTanam());
                    laporanTanahTemp.setUsahaLuas(laporanTanahSblm.getUsahaLuas());
                    laporanTanahTemp.setUsahaBilanganPokok(laporanTanahSblm.getUsahaBilanganPokok());
                    laporanTanahTemp.setUsahaHarga(laporanTanahSblm.getUsahaHarga());
                }
                if (laporanTanahSblm.getPerusahaan() != null && laporanTanahSblm.getPerusahaan().equalsIgnoreCase("Y")) {
                    laporanTanahTemp.setPerusahaan("Y");
                }
                laporanTanahTemp.setKeadaanTanah(laporanTanahSblm.getKeadaanTanah());
                laporanTanahTemp.setSempadanUtaraNoLot(laporanTanahSblm.getSempadanUtaraNoLot());
                laporanTanahTemp.setSempadanUtaraKegunaan(laporanTanahSblm.getSempadanUtaraKegunaan());
                laporanTanahTemp.setSempadanSelatanNoLot(laporanTanahSblm.getSempadanSelatanNoLot());
                laporanTanahTemp.setSempadanSelatanKegunaan(laporanTanahSblm.getSempadanSelatanKegunaan());
                laporanTanahTemp.setSempadanTimurNoLot(laporanTanahSblm.getSempadanTimurNoLot());
                laporanTanahTemp.setSempadanTimurKegunaan(laporanTanahSblm.getSempadanTimurKegunaan());
                laporanTanahTemp.setSempadanBaratNoLot(laporanTanahSblm.getSempadanBaratNoLot());
                laporanTanahTemp.setSempadanBaratKegunaan(laporanTanahSblm.getSempadanBaratKegunaan());
                laporanTanahTemp.setAdaPecahSempadan(laporanTanahSblm.getAdaPecahSempadan());
                laporanTanahTemp.setAdaJalanMasuk(laporanTanahSblm.getAdaJalanMasuk());
                laporanTanahTemp.setCatatanJalanMasuk(laporanTanahSblm.getCatatanJalanMasuk());

                laporanTanahTemp = laporanTanahService.simpanLaporanTanah(laporanTanahTemp);

                if (laporanTanahSblm.getAdaBangunan() != null && laporanTanahSblm.getAdaBangunan() == 'Y') {
                    permohonanLaporanBangunanSblmList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanahSblm.getIdLaporan(), "B");
                    for (PermohonanLaporanBangunan bangunanSblm : permohonanLaporanBangunanSblmList) {
                        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
                        permohonanLaporanBangunan.setPermohonan(permohonan);
                        permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanBangunan.setLaporanTanah(laporanTanahTemp);
                        permohonanLaporanBangunan.setKategori(bangunanSblm.getKategori());
                        permohonanLaporanBangunan.setJenisBangunan(bangunanSblm.getJenisBangunan());
                        permohonanLaporanBangunan.setUkuran(bangunanSblm.getUkuran());
                        permohonanLaporanBangunan.setNilai(bangunanSblm.getNilai());
                        permohonanLaporanBangunan.setTahunDibina(bangunanSblm.getTahunDibina());
                        permohonanLaporanBangunan.setNamaKetua(bangunanSblm.getNamaKetua());
                        permohonanLaporanBangunan.setNamaPemunya(bangunanSblm.getNamaPemunya());
                        permohonanLaporanBangunan.setJenisPendudukan(bangunanSblm.getJenisPendudukan());
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pguna);
                        info.setTarikhMasuk(new java.util.Date());
                        permohonanLaporanBangunan.setInfoAudit(info);
                        laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
                    }
                }

                if (laporanTanahSblm.getUsaha() != null && laporanTanahSblm.getUsaha() == 'Y') {
                    permohonanLaporanBangunanSblmList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanahSblm.getIdLaporan(), "P");
                    for (PermohonanLaporanBangunan bangunanSblm : permohonanLaporanBangunanSblmList) {
                        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
                        permohonanLaporanBangunan.setPermohonan(permohonan);
                        permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanBangunan.setLaporanTanah(laporanTanahTemp);
                        permohonanLaporanBangunan.setKategori(bangunanSblm.getKategori());
                        permohonanLaporanBangunan.setJenisBangunan(bangunanSblm.getJenisBangunan());
                        permohonanLaporanBangunan.setUkuran(bangunanSblm.getUkuran());
                        permohonanLaporanBangunan.setNilai(bangunanSblm.getNilai());
                        permohonanLaporanBangunan.setTahunDibina(bangunanSblm.getTahunDibina());
                        permohonanLaporanBangunan.setNamaKetua(bangunanSblm.getNamaKetua());
                        permohonanLaporanBangunan.setNamaPemunya(bangunanSblm.getNamaPemunya());
                        permohonanLaporanBangunan.setJenisPendudukan(bangunanSblm.getJenisPendudukan());
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pguna);
                        info.setTarikhMasuk(new java.util.Date());
                        permohonanLaporanBangunan.setInfoAudit(info);
                        laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
                    }
                }
                if (laporanTanahSblm.getPerusahaan() != null && laporanTanahSblm.getPerusahaan().equalsIgnoreCase("Y")) {
                    permohonanLaporanBangunanSblmList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanahSblm.getIdLaporan(), "PU");
                    for (PermohonanLaporanBangunan bangunanSblm : permohonanLaporanBangunanSblmList) {
                        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
                        permohonanLaporanBangunan.setPermohonan(permohonan);
                        permohonanLaporanBangunan.setCawangan(permohonan.getCawangan());
                        permohonanLaporanBangunan.setLaporanTanah(laporanTanahTemp);
                        permohonanLaporanBangunan.setKategori(bangunanSblm.getKategori());
                        permohonanLaporanBangunan.setJenisBangunan(bangunanSblm.getJenisBangunan());
                        permohonanLaporanBangunan.setUkuran(bangunanSblm.getUkuran());
                        permohonanLaporanBangunan.setNilai(bangunanSblm.getNilai());
                        permohonanLaporanBangunan.setTahunDibina(bangunanSblm.getTahunDibina());
                        permohonanLaporanBangunan.setNamaKetua(bangunanSblm.getNamaKetua());
                        permohonanLaporanBangunan.setNamaPemunya(bangunanSblm.getNamaPemunya());
                        permohonanLaporanBangunan.setJenisPendudukan(bangunanSblm.getJenisPendudukan());
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pguna);
                        info.setTarikhMasuk(new java.util.Date());
                        permohonanLaporanBangunan.setInfoAudit(info);
                        laporanTanahService.simpanLaporanTanahBangunan(permohonanLaporanBangunan);
                    }
                }

                PermohonanLaporanUlasan laporanUlasanSBLM = new PermohonanLaporanUlasan();

                laporanUlasanSBLM = laporanTanahService.getLaporUlasanByIdMohonidLaporan(permohonan.getPermohonanSebelum().getIdPermohonan(), laporanTanahSblm.getIdLaporan());

                if (laporanUlasanSBLM != null) {
                    laporanUlasanTemp = new PermohonanLaporanUlasan();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    laporanUlasanTemp.setInfoAudit(infoAudit);
                    laporanUlasanTemp.setPermohonan(permohonan);
                    laporanUlasanTemp.setCawangan(permohonan.getCawangan());
                    laporanUlasanTemp.setLaporanTanah(laporanTanahTemp);
                    laporanUlasanTemp.setUlasan(laporanUlasanSBLM.getUlasan());
                    laporanTanahService.simpanLaporanUlasan(laporanUlasanTemp);
                }


                //Imej
                utaraImejLaporanSblmList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanahSblm.getIdLaporan());
                selatanImejLaporanSblmList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanahSblm.getIdLaporan());
                timurImejLaporanSblmList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanahSblm.getIdLaporan());
                baratImejLaporanSblmList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanahSblm.getIdLaporan());
                for (ImejLaporan imejLaporanSblm : utaraImejLaporanSblmList) {
                    ImejLaporan imejLaporan = new ImejLaporan();
                    imejLaporan.setCawangan(permohonan.getCawangan());
                    imejLaporan.setDokumen(imejLaporanSblm.getDokumen());
                    imejLaporan.setPandanganImej(imejLaporanSblm.getPandanganImej());
                    imejLaporan.setCatatan(imejLaporanSblm.getCatatan());
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    imejLaporan.setInfoAudit(infoAudit);
                    imejLaporan.setLaporanTanah(laporanTanahTemp);
                    imejLaporan.setHakmilik(hakmilik);
                    laporanTanahService.simpanHakmilikImej(imejLaporan);
                }
                for (ImejLaporan imejLaporanSblm : selatanImejLaporanSblmList) {
                    ImejLaporan imejLaporan = new ImejLaporan();
                    imejLaporan.setCawangan(permohonan.getCawangan());
                    imejLaporan.setDokumen(imejLaporanSblm.getDokumen());
                    imejLaporan.setPandanganImej(imejLaporanSblm.getPandanganImej());
                    imejLaporan.setCatatan(imejLaporanSblm.getCatatan());
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    imejLaporan.setInfoAudit(infoAudit);
                    imejLaporan.setLaporanTanah(laporanTanahTemp);
                    imejLaporan.setHakmilik(hakmilik);
                    laporanTanahService.simpanHakmilikImej(imejLaporan);
                }
                for (ImejLaporan imejLaporanSblm : timurImejLaporanSblmList) {
                    ImejLaporan imejLaporan = new ImejLaporan();
                    imejLaporan.setCawangan(permohonan.getCawangan());
                    imejLaporan.setDokumen(imejLaporanSblm.getDokumen());
                    imejLaporan.setPandanganImej(imejLaporanSblm.getPandanganImej());
                    imejLaporan.setCatatan(imejLaporanSblm.getCatatan());
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    imejLaporan.setInfoAudit(infoAudit);
                    imejLaporan.setLaporanTanah(laporanTanahTemp);
                    imejLaporan.setHakmilik(hakmilik);
                    laporanTanahService.simpanHakmilikImej(imejLaporan);
                }
                for (ImejLaporan imejLaporanSblm : baratImejLaporanSblmList) {
                    ImejLaporan imejLaporan = new ImejLaporan();
                    imejLaporan.setCawangan(permohonan.getCawangan());
                    imejLaporan.setDokumen(imejLaporanSblm.getDokumen());
                    imejLaporan.setPandanganImej(imejLaporanSblm.getPandanganImej());
                    imejLaporan.setCatatan(imejLaporanSblm.getCatatan());
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    imejLaporan.setInfoAudit(infoAudit);
                    imejLaporan.setLaporanTanah(laporanTanahTemp);
                    imejLaporan.setHakmilik(hakmilik);
                    laporanTanahService.simpanHakmilikImej(imejLaporan);
                }

            }

        }
        return laporanTanahTemp;
    }

    public Resolution simpanLaporanTanah() throws Exception {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit infoAudit;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (idHakmilik != null) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                hakmilikUrusan = hakmilikUrusanService.findByIdPerserahanIdHakmilik(idPermohonan, idHakmilik);
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
                } else {
                    laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
                }

                if (laporanTanah != null) {
                    infoAudit = laporanTanah.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    laporanTanah.setInfoAudit(infoAudit);
                } else {
                    laporanTanah = new LaporanTanah();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    laporanTanah.setInfoAudit(infoAudit);
                    laporanTanah.setPermohonan(permohonan);
                    laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                    if (pengguna.getKodCawangan().getKod().equals("00")) {
                        laporanTanah.setLaporanUntuk("PTG");
                    } else {
                        laporanTanah.setLaporanUntuk("PTD");
                    }
                }
                laporanTanah.setKeadaanTanah(getContext().getRequest().getParameter("laporanTanah.keadaanTanah"));
                if (jenisKegunaan == 'B') {
                    laporanTanah.setAdaBangunan('Y');

                    laporanTanah.setUsaha(' ');
                    laporanTanah.setUsahaTanam(null);
                    laporanTanah.setUsahaLuas(null);
                    laporanTanah.setUsahaBilanganPokok(null);
                    laporanTanah.setUsahaHarga(null);
                    laporanTanah.setPerusahaan(null);
                    if (pengguna.getKodCawangan().getKod().equals("00")) {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    } else {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    }

                }
                if (jenisKegunaan == 'P') {
                    laporanTanah.setUsaha('Y');

                    laporanTanah.setAdaBangunan(' ');
                    laporanTanah.setPerusahaan(null);
                    if (pengguna.getKodCawangan().getKod().equals("00")) {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    } else {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    }
                    laporanTanah.setUsahaTanam(getContext().getRequest().getParameter("laporanTanah.usahaTanam"));
                    laporanTanah.setUsahaLuas(usahaLuas);
                    laporanTanah.setUsahaBilanganPokok(usahaBilanganPokok);
                    laporanTanah.setUsahaHarga(usahaHarga);
                }
                if (jenisKegunaan == 'U') {
                    laporanTanah.setPerusahaan("Y");

                    laporanTanah.setAdaBangunan(' ');
                    laporanTanah.setUsaha(' ');
                    laporanTanah.setUsahaTanam(null);
                    laporanTanah.setUsahaLuas(null);
                    laporanTanah.setUsahaBilanganPokok(null);
                    laporanTanah.setUsahaHarga(null);

                    if (pengguna.getKodCawangan().getKod().equals("00")) {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    } else {
                        if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                            permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                            for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                                laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                            }
                        }
                    }
                }
                laporanTanah.setSempadanUtaraNoLot(getContext().getRequest().getParameter("laporanTanah.sempadanUtaraNoLot"));
                laporanTanah.setSempadanUtaraKegunaan(getContext().getRequest().getParameter("laporanTanah.sempadanUtaraKegunaan"));
                laporanTanah.setSempadanSelatanNoLot(getContext().getRequest().getParameter("laporanTanah.sempadanSelatanNoLot"));
                laporanTanah.setSempadanSelatanKegunaan(getContext().getRequest().getParameter("laporanTanah.sempadanSelatanKegunaan"));
                laporanTanah.setSempadanTimurNoLot(getContext().getRequest().getParameter("laporanTanah.sempadanTimurNoLot"));
                laporanTanah.setSempadanTimurKegunaan(getContext().getRequest().getParameter("laporanTanah.sempadanTimurKegunaan"));
                laporanTanah.setSempadanBaratNoLot(getContext().getRequest().getParameter("laporanTanah.sempadanBaratNoLot"));
                laporanTanah.setSempadanBaratKegunaan(getContext().getRequest().getParameter("laporanTanah.sempadanBaratKegunaan"));
                laporanTanah.setAdaPecahSempadan(adaPecahSempadan);
                laporanTanah.setAdaJalanMasuk(adaJalanMasuk);
                laporanTanah.setCatatanJalanMasuk(getContext().getRequest().getParameter("laporanTanah.catatanJalanMasuk"));

                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }

            //uncomment this for ulasan
            laporanUlasan = laporanTanahService.getLaporUlasanByIdMohonidLaporan(idPermohonan, laporanTanah.getIdLaporan());

            if (laporanUlasan == null) {
                laporanUlasan = new PermohonanLaporanUlasan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanUlasan.setInfoAudit(infoAudit);
                laporanUlasan.setPermohonan(permohonan);
                laporanUlasan.setCawangan(permohonan.getCawangan());
                laporanUlasan.setLaporanTanah(laporanTanah);

            } else {
                infoAudit = laporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                laporanUlasan.setInfoAudit(infoAudit);
            }
            ulasan = (String) getContext().getRequest().getParameter("ulasan");
            laporanUlasan.setUlasan(ulasan);

            laporanTanahService.simpanLaporanUlasan(laporanUlasan);

            if (pengguna.getKodCawangan().getKod().equals("00")) {
                laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPT", "104");
            } else {
                laporanUlasanPPT = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPT", "228");
            }

            if (laporanUlasanPPT == null) {
                laporanUlasanPPT = new PermohonanLaporanUlasan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanUlasanPPT.setInfoAudit(infoAudit);
                laporanUlasanPPT.setPermohonan(permohonan);
                laporanUlasanPPT.setCawangan(permohonan.getCawangan());
                laporanUlasanPPT.setJenisUlasan("PPT");
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanUlasanPPT.setKodPeranan(kodPerananDAO.findById("104"));
                } else {
                    laporanUlasanPPT.setKodPeranan(kodPerananDAO.findById("228"));
                }
            } else {
                infoAudit = laporanUlasanPPT.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                laporanUlasanPPT.setInfoAudit(infoAudit);
            }
            ulasan1 = (String) getContext().getRequest().getParameter("ulasan1");
            laporanUlasanPPT.setUlasan(ulasan1);

            laporanTanahService.simpanLaporanUlasan(laporanUlasanPPT);

            if (pengguna.getKodCawangan().getKod().equals("00")) {
                laporanUlasanSOCIAL = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "SOCIAL", "104");
            } else {
                laporanUlasanSOCIAL = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "SOCIAL", "228");
            }
            if (laporanUlasanSOCIAL == null) {
                laporanUlasanSOCIAL = new PermohonanLaporanUlasan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanUlasanSOCIAL.setInfoAudit(infoAudit);
                laporanUlasanSOCIAL.setPermohonan(permohonan);
                laporanUlasanSOCIAL.setCawangan(permohonan.getCawangan());
                laporanUlasanSOCIAL.setJenisUlasan("SOCIAL");
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanUlasanSOCIAL.setKodPeranan(kodPerananDAO.findById("104"));
                } else {
                    laporanUlasanSOCIAL.setKodPeranan(kodPerananDAO.findById("228"));
                }
            } else {
                infoAudit = laporanUlasanSOCIAL.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                laporanUlasanSOCIAL.setInfoAudit(infoAudit);
            }
            ulasan2 = (String) getContext().getRequest().getParameter("ulasan2");
            laporanUlasanSOCIAL.setUlasan(ulasan2);
            laporanTanahService.simpanLaporanUlasan(laporanUlasanSOCIAL);

            if (pengguna.getKodCawangan().getKod().equals("00")) {
                laporanUlasanPPTK = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPTK", "104");
            } else {
                laporanUlasanPPTK = laporanTanahService.getLaporUlasanByIdMohonJenis(idPermohonan, "PPTK", "228");
            }
            if (laporanUlasanPPTK == null) {
                laporanUlasanPPTK = new PermohonanLaporanUlasan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanUlasanPPTK.setInfoAudit(infoAudit);
                laporanUlasanPPTK.setPermohonan(permohonan);
                laporanUlasanPPTK.setCawangan(permohonan.getCawangan());
                laporanUlasanPPTK.setJenisUlasan("PPTK");
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanUlasanPPTK.setKodPeranan(kodPerananDAO.findById("104"));
                } else {
                    laporanUlasanPPTK.setKodPeranan(kodPerananDAO.findById("228"));
                }
            } else {
                infoAudit = laporanUlasanPPTK.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                laporanUlasanPPTK.setInfoAudit(infoAudit);
            }
            ulasan3 = (String) getContext().getRequest().getParameter("ulasan3");
            laporanUlasanPPTK.setUlasan(ulasan3);
            laporanTanahService.simpanLaporanUlasan(laporanUlasanPPTK);

        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");
    }

    public Resolution tambahBangunanPopup() {
        kategori = getContext().getRequest().getParameter("kategori");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        return new JSP("pengambilan/melaka/seksyen4/laporan_tanah_new_popup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanBangunan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            InfoAudit infoAudit;
            if (pengguna.getKodCawangan().getKod().equals("00")) {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            } else {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            }
            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                laporanTanah.setInfoAudit(infoAudit);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanTanah.setLaporanUntuk("PTG");
                } else {
                    laporanTanah.setLaporanUntuk("PTD");
                }
            }
            if (kategori.equalsIgnoreCase("B")) {
                laporanTanah.setAdaBangunan('Y');
                laporanTanah.setUsaha(' ');
                laporanTanah.setUsahaTanam(null);
                laporanTanah.setUsahaLuas(null);
                laporanTanah.setUsahaBilanganPokok(null);
                laporanTanah.setUsahaHarga(null);
                laporanTanah.setPerusahaan(null);
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                } else {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                }
            } else if (kategori.equalsIgnoreCase("P")) {
                laporanTanah.setUsaha('Y');

                laporanTanah.setAdaBangunan(' ');
                laporanTanah.setPerusahaan(null);
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                } else {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanPUList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "PU");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPUList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                }

            } else if (kategori.equalsIgnoreCase("PU")) {
                laporanTanah.setPerusahaan("Y");

                laporanTanah.setAdaBangunan(' ');
                laporanTanah.setUsaha(' ');
                laporanTanah.setUsahaTanam(null);
                laporanTanah.setUsahaLuas(null);
                laporanTanah.setUsahaBilanganPokok(null);
                laporanTanah.setUsahaHarga(null);
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                } else {
                    if (laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId()) != null) {
                        permohonanLaporanBangunanPList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "P");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanPList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                        permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdLaporan(laporanTanah.getIdLaporan(), "B");
                        for (PermohonanLaporanBangunan permohonanLaporanBangunan : permohonanLaporanBangunanList) {
                            laporanTanahService.deleteLaporBngn(permohonanLaporanBangunan);
                        }
                    }
                }

            }

            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            if (permohonanLaporanBangunan != null) {
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
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");
    }

    public Resolution uploadDoc() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
        return new JSP("pengambilan/melaka/seksyen4/Upload_Imej_Laporan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanImejLaporan() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            InfoAudit info;
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            if (peng.getKodCawangan().getKod().equals("00")) {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            } else {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            }
            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(info);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                if (peng.getKodCawangan().getKod().equals("00")) {
                    laporanTanah.setLaporanUntuk("PTG");
                } else {
                    laporanTanah.setLaporanUntuk("PTD");
                }
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }

            String dokumenPath = conf.getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("pengambilan/melaka/seksyen4/Upload_Imej_Laporan.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("pengambilan/melaka/seksyen4/Upload_Imej_Laporan.jsp").addParameter("popup", "true");
            }
            if (fileToBeUpload != null) {
                Dokumen doc = commonService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), info, permohonan);
                ImejLaporan imejLaporan = new ImejLaporan();
                imejLaporan.setCawangan(permohonan.getCawangan());
                imejLaporan.setDokumen(doc);
                imejLaporan.setPandanganImej(pandanganImej);
                imejLaporan.setCatatan(catatan);
                imejLaporan.setInfoAudit(info);
                imejLaporan.setLaporanTanah(laporanTanah);
                imejLaporan.setHakmilik(hakmilik);
                laporanTanahService.simpanHakmilikImej(imejLaporan);

                addSimpleMessage("Muat naik fail berjaya.");
            }

        }

        return new JSP("pengambilan/melaka/seksyen4/Upload_Imej_Laporan.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanTanahPengambilanSblmActionBean.class, "locate");
    }

    public Resolution addLaporanImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            InfoAudit info;
            if (pengguna.getKodCawangan().getKod().equals("00")) {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            } else {
                laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMH(idPermohonan, hakmilikPermohonan.getId());
            }
            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                laporanTanah.setInfoAudit(info);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
                if (pengguna.getKodCawangan().getKod().equals("00")) {
                    laporanTanah.setLaporanUntuk("PTG");
                } else {
                    laporanTanah.setLaporanUntuk("PTD");
                }
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }

            String idDokumen = getContext().getRequest().getParameter("idDokumen");
            Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

            ImejLaporan imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah, dokumen, pandanganImej);
            if (imejLaporan == null) {
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                imejLaporan = new ImejLaporan();
                imejLaporan.setCawangan(cawangan);
                imejLaporan.setDokumen(dokumen);
                imejLaporan.setPandanganImej(pandanganImej);
                imejLaporan.setInfoAudit(infoAudit);
                imejLaporan.setLaporanTanah(laporanTanah);
                laporanTanahService.simpanHakmilikImej(imejLaporan);

                hakmilikDetails();
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            } else {
                addSimpleMessage("Imej already exist.");
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/laporan_tanah_new_n9_sblm.jsp").addParameter("tab", "true");
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
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

    public Integer getUsahaBilanganPokok() {
        return usahaBilanganPokok;
    }

    public void setUsahaBilanganPokok(Integer usahaBilanganPokok) {
        this.usahaBilanganPokok = usahaBilanganPokok;
    }

    public BigDecimal getUsahaLuas() {
        return usahaLuas;
    }

    public void setUsahaLuas(BigDecimal usahaLuas) {
        this.usahaLuas = usahaLuas;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getPenyewaBangunan() {
        return penyewaBangunan;
    }

    public void setPenyewaBangunan(String penyewaBangunan) {
        this.penyewaBangunan = penyewaBangunan;
    }

    public String getPenyewaTanah() {
        return penyewaTanah;
    }

    public void setPenyewaTanah(String penyewaTanah) {
        this.penyewaTanah = penyewaTanah;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public String getAdaPecahSempadan() {
        return adaPecahSempadan;
    }

    public void setAdaPecahSempadan(String adaPecahSempadan) {
        this.adaPecahSempadan = adaPecahSempadan;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public BigDecimal getUsahaHarga() {
        return usahaHarga;
    }

    public void setUsahaHarga(BigDecimal usahaHarga) {
        this.usahaHarga = usahaHarga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPList() {
        return permohonanLaporanBangunanPList;
    }

    public void setPermohonanLaporanBangunanPList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPList) {
        this.permohonanLaporanBangunanPList = permohonanLaporanBangunanPList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanPUList() {
        return permohonanLaporanBangunanPUList;
    }

    public void setPermohonanLaporanBangunanPUList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanPUList) {
        this.permohonanLaporanBangunanPUList = permohonanLaporanBangunanPUList;
    }

    public PermohonanLaporanUlasan getLaporanUlasanPPT() {
        return laporanUlasanPPT;
    }

    public void setLaporanUlasanPPT(PermohonanLaporanUlasan laporanUlasanPPT) {
        this.laporanUlasanPPT = laporanUlasanPPT;
    }

    public PermohonanLaporanUlasan getLaporanUlasanPPTK() {
        return laporanUlasanPPTK;
    }

    public void setLaporanUlasanPPTK(PermohonanLaporanUlasan laporanUlasanPPTK) {
        this.laporanUlasanPPTK = laporanUlasanPPTK;
    }

    public PermohonanLaporanUlasan getLaporanUlasanSOCIAL() {
        return laporanUlasanSOCIAL;
    }

    public void setLaporanUlasanSOCIAL(PermohonanLaporanUlasan laporanUlasanSOCIAL) {
        this.laporanUlasanSOCIAL = laporanUlasanSOCIAL;
    }

    public String getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(String ulasan1) {
        this.ulasan1 = ulasan1;
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

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public LaporanTanah getLaporanTanahTemp() {
        return laporanTanahTemp;
    }

    public void setLaporanTanahTemp(LaporanTanah laporanTanahTemp) {
        this.laporanTanahTemp = laporanTanahTemp;
    }

    public PermohonanLaporanUlasan getLaporanUlasan() {
        return laporanUlasan;
    }

    public void setLaporanUlasan(PermohonanLaporanUlasan laporanUlasan) {
        this.laporanUlasan = laporanUlasan;
    }

    public PermohonanLaporanUlasan getLaporanUlasanTemp() {
        return laporanUlasanTemp;
    }

    public void setLaporanUlasanTemp(PermohonanLaporanUlasan laporanUlasanTemp) {
        this.laporanUlasanTemp = laporanUlasanTemp;
    }

    public Permohonan getpSebelum() {
        return pSebelum;
    }

    public void setpSebelum(Permohonan pSebelum) {
        this.pSebelum = pSebelum;
    }
}
