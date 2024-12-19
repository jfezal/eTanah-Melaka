/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.LaporanTanahService;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.RegService;
import etanah.service.daftar.PembetulanService;
import etanah.view.penguatkuasaan.MohonLaporTanahActionBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.SejarahHakmilik;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
//import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import etanah.service.RegService;
import etanah.service.SyerValidationService;
import etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean;
import java.util.ArrayList;
import java.util.Collections;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.math.fraction.Fraction;
import etanah.model.KodSeksyen;
import etanah.model.KertasRisalat;
import etanah.model.KodHakmilik;
import etanah.model.KodLot;
import etanah.model.KodKategoriTanah;
import etanah.model.KodUOM;
import etanah.model.KodDaerah;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikWaris;
import etanah.sequence.GeneratorIdHakmilik;
import java.util.Date;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.KodStatusHakmilik;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PemohonService;
import etanah.model.Pemohon;
import etanah.model.KodKadarCukai;
import java.math.BigDecimal;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodKegunaanTanah;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.Configuration;
import etanah.dao.HakmilikUrusanDAO;
import java.math.MathContext;
import java.math.RoundingMode;
import etanah.dao.KodNegeriDAO;
import etanah.model.KodNegeri;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.dao.KodBandarPekanMukimDAO;
import org.hibernate.Transaction;
import org.hibernate.Session;
import etanah.dao.KodDaerahDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.gis.PelanGIS;
import etanah.service.common.PermohonanPihakService;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.sequence.GeneratorNoAkaun;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KertasRisalatDAO;
import etanah.model.KodPBT;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.CalcTax;
import etanah.view.ListUtil;
import java.text.SimpleDateFormat;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.UlasanJabatanTeknikal;
import etanah.service.PelupusanService;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.daftar.PembetulanService;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/RingkasanDrafRisalatPTG")
public class RingkasanDrafRisalatPTG extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KertasRencanaMMKNPLPTActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    RegService regService;
    @Inject
    PembetulanService pembetulanService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KertasRisalatDAO kertasRisalatDAO;
    @Inject
    DokumenDAO dokumenDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private PermohonanKertas permohonanKertas;
    private String kertasBil;
    private String kertasTahun;
    private String tempat;
    private String tarikhmesyuarat;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanKertasKandungan ringkasanPermohonan;
    private PermohonanKertasKandungan syorPengarah;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<LaporanTanah> senaraiLaporanTanah;
    private LaporanTanah laporanTanah;
    private PermohonanPihak penerima;
//    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan21;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan22;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan23;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan24;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil1;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil2;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil3;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil4;
    private List<PermohonanRujukanLuar> mohonRujLuarList;
    private PermohonanRujukanLuar mohonRujLuarAdun;
    private HakmilikPermohonan hakmilikPermohonan;
    private KertasRisalat risalat;
    private String tajuk;
    private Hakmilik hakmilik;
    private UlasanJabatanTeknikal ulasanJabatanTeknikal;
    private List<UlasanJabatanTeknikal> senaraiUlasanJabatanTeknikal;
    private Dokumen dokumen;
    private String tujuan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private int count4 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List senaraiPerakuanPengarah[] = new ArrayList[5];
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    private String parameter;
    String namaProjek;
    BigDecimal totalLuas = new BigDecimal(0.00);
    etanahActionBeanContext ctx;
    private PermohonanKertasKandungan kertasKemaskini;
    private List<Permohonan> senaraiPermohonan;
    private List<FasaPermohonan> senaraiFasaPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);


        return new JSP("pengambilan/Ringkasa_draf_risalatPTG.jsp").addParameter("tab", "true");
    }

    public Resolution searchDetail() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        hakmilikPermohonan = regService.searchMohonHakmilikByIdMhIdMohon(Long.parseLong(idMohonHakmilik), idPermohonan);
//        permohonanKertas = pendudukanSementaraMMKNService.find

//        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//        rehydrate();
        return new RedirectResolution(RingkasanDrafRisalatPTG.class).addParameter("showForm").addParameter("idHP", hakmilikPermohonan.getId());
//        return new JSP("pengambilan/Ringkasa_draf_risalatPTG.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listPemohon = permohonan.getSenaraiPemohon();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        if (hakmilikPermohonanList.size() > 0) {
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            hakmilik = hakmilikPermohonan.getHakmilik();
        }
//        mohonRujLuarList = new ArrayList <PermohonanRujukanLuar>();
        mohonRujLuarList = pembetulanService.searchMohonRujByIDPermohonan(idPermohonan);
        mohonRujLuarAdun = pembetulanService.searchMohonRujByIDPermohonanAdun(idPermohonan);
        mohonRujLuarList.remove(mohonRujLuarAdun);
        senaraiLaporanTanah = laporanTanahService.findSenaraiLaporanTanahByIdMohon(idPermohonan);
        if (senaraiLaporanTanah.size() > 0) {
            laporanTanah = senaraiLaporanTanah.get(0);

        }
        senaraiUlasanJabatanTeknikal = pelupusanService.findUlasanJabatanTeknikalAdunList(idPermohonan);
        if (senaraiUlasanJabatanTeknikal.size() > 0) {
            ulasanJabatanTeknikal = senaraiUlasanJabatanTeknikal.get(0);
        }

        permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonanMMKND(idPermohonan);

        if (permohonanKertas != null) {
            mohonRujLuarAdun = pembetulanService.searchMohonRujByIDPermohonanAdun(idPermohonan);
            risalat = pendudukanSementaraMMKNService.findKertasRisalat(idPermohonan, String.valueOf(permohonanKertas.getIdKertas()));
            String tiadaData = "Tiada Data Dimasukan";
            String keadaanTanah = null;
            if (laporanTanah.getKeadaanTanah() != null && laporanTanah.getStrukturTanahLain() != null) {
                keadaanTanah = laporanTanah.getKeadaanTanah() + "(" + laporanTanah.getStrukturTanahLain() + ")";
            } else if (laporanTanah.getKeadaanTanah() != null && laporanTanah.getStrukturTanahLain() == null) {
                keadaanTanah = laporanTanah.getKeadaanTanah() + "(" + tiadaData + ")";
            } else if (laporanTanah.getKeadaanTanah() == null && laporanTanah.getStrukturTanahLain() != null) {
                keadaanTanah = tiadaData + "(" + laporanTanah.getStrukturTanahLain() + ")";
            } else if (laporanTanah.getKeadaanTanah() == null && laporanTanah.getStrukturTanahLain() == null) {
                keadaanTanah = tiadaData + "(" + tiadaData + ")";
            }


            if (risalat == null) {
                KertasRisalat kertasRisalat = new KertasRisalat();
                InfoAudit ia = new InfoAudit();
                ia = pengguna.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                kertasRisalat.setIdKertas(permohonanKertas.getIdKertas());
                kertasRisalat.setPermohonan(permohonan);
                if (ulasanJabatanTeknikal != null) {
                    kertasRisalat.setAgensi(ulasanJabatanTeknikal.getKodAgensi());
                } else {
                    kertasRisalat.setAgensi(null);
                }
                if (keadaanTanah != null) {
                    kertasRisalat.setKeadaanTanah(keadaanTanah);
                }
                if (laporanTanah.getKedudukanTanah() != null) {
                    kertasRisalat.setLokasi(laporanTanah.getKedudukanTanah());
                } else {
                    kertasRisalat.setLokasi(tiadaData);
                }
                if (mohonRujLuarAdun != null) {
                    kertasRisalat.setAgensi(mohonRujLuarAdun.getAgensi());
                    kertasRisalat.setUlasanAdun(mohonRujLuarAdun.getUlasan());
                }
                kertasRisalat.setTajuk(permohonanKertas.getTajuk());
                if (ulasanJabatanTeknikal != null) {
                    kertasRisalat.setUlasanAdun(ulasanJabatanTeknikal.getUlasan());
                } else {
                    kertasRisalat.setUlasanAdun("Tiada Data");
                }
                kertasRisalat.setPerakuanPTG("Tiada kemasukan");
                kertasRisalat.setInfoAudit(ia);
                pendudukanSementaraMMKNService.simpanKertasRisalat(kertasRisalat);
                risalat = kertasRisalat;
            }
        }
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            if (hp.getPermohonan().getKodUrusan().getKod().equals("PLPT")) {
                senaraiPermohonan = new ArrayList();
                senaraiPermohonan.add(hp.getPermohonan());
            }
        }
        for (Permohonan mohon : senaraiPermohonan) {
            senaraiFasaPermohonan = pendudukanSementaraMMKNService.findFasaPermohonanterima_keputusan_ptd(idPermohonan);
        }
    }

    public Resolution kemaskiniMMKN() {

//        String idKertas = (String) getContext().getRequest().getSession().getAttribute("idKertas");
//        String parameter = (String) getContext().getRequest().getSession().getAttribute("parameter");
        parameter = getContext().getRequest().getParameter("parameter");
        String idKertas = getContext().getRequest().getParameter("idKertas");
        risalat = kertasRisalatDAO.findById(Long.parseLong(idKertas));
//        String idKandungan = (String) getContext().getRequest().getParameter("idKandungan");
//        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        return new JSP("pengambilan/kemaskini_risalatPTG.jsp").addParameter("tab", "true");
    }

    public Resolution saveKemaskini() {

        String idKertas = getContext().getRequest().getParameter("idKertas");
        parameter = getContext().getRequest().getParameter("parameter");
        String kandungan = getContext().getRequest().getParameter("kandunganTajuk");
        String Lulus = getContext().getRequest().getParameter("Lulus");
        String Tolak = getContext().getRequest().getParameter("Tolak");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia = pengguna.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        risalat = kertasRisalatDAO.findById(Long.parseLong(idKertas));
        if (parameter.equals("lokasi")) {
            risalat.setLokasi(kandungan);
        } else if (parameter.equals("Keadaan Tanah")) {
            risalat.setKeadaanTanah(kandungan);
        } else if (parameter.equals("Risalat MMKN")) {
            risalat.setItem(kandungan);
        } else if (parameter.equals("No. Ruj. PTG")) {
            risalat.setNoRujukan(kandungan);
        } else if (parameter.equals("Tajuk")) {
            risalat.setTajuk(kandungan);
        } else if (parameter.equals("Ulasan Adun")) {
            risalat.setUlasanAdun(kandungan);
        } else if (parameter.equals("Perakuan PTG")) {
            risalat.setPerakuanPTG(kandungan);
            if (Lulus != null) {
                risalat.setKeputusan(Lulus);
            } else if (Tolak != null) {
                risalat.setPerakuanPTG(Tolak);
            }
        }
        risalat.setInfoAudit(ia);
        pendudukanSementaraMMKNService.simpanKertasRisalat(risalat);

        return new RedirectResolution(RingkasanDrafRisalatPTG.class).addParameter("showForm").addParameter("idHP", hakmilikPermohonan.getId());
    }

    public Resolution kemaskiniPemohon() {

        String idHakmilikPihak = ctx.getRequest().getParameter("idKandungan");
        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        return new JSP("pengambilan/kemaskini_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTeknikal() {

        String idHakmilikPihak = ctx.getRequest().getParameter("idKandungan");
        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        return new JSP("pengambilan/kemaskini_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() throws ParseException {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try {
            permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

        return new JSP("pengambilan/Kertas_Rencana_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() throws ParseException {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), bil);

        if (kandList != null && !kandList.isEmpty()) {
            PermohonanKertasKandungan maxKertasKand = kandList.get(0);
            if (maxKertasKand != null) {
                String subtajuk = maxKertasKand.getSubtajuk();
                String str = subtajuk.substring(2, 3);
                String subtajuk1 = bil + "." + str;
                List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), bil, subtajuk1);
                for (PermohonanKertasKandungan kand : kandList1) {
                    pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                }
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

        return new JSP("pengambilan/Kertas_Rencana_MMKN.jsp").addParameter("tab", "true");

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
        return permohonanKertasKandungan3;
    }

    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;

    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;

    }

//    public int getBil() {
//        return bil;
//    }
//
//    public void setBil(int bil) {
//        this.bil = bil;
//    }
    public String getKandungan() {
        return kandungan;

    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(String kertasTahun) {
        this.kertasTahun = kertasTahun;
    }

    public String getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(String tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getRingkasanPermohonan() {
        return ringkasanPermohonan;
    }

    public void setRingkasanPermohonan(PermohonanKertasKandungan ringkasanPermohonan) {
        this.ringkasanPermohonan = ringkasanPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan21() {
        return senaraiKertasKandungan21;
    }

    public void setSenaraiKertasKandungan21(List<PermohonanKertasKandungan> senaraiKertasKandungan21) {
        this.senaraiKertasKandungan21 = senaraiKertasKandungan21;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan22() {
        return senaraiKertasKandungan22;
    }

    public void setSenaraiKertasKandungan22(List<PermohonanKertasKandungan> senaraiKertasKandungan22) {
        this.senaraiKertasKandungan22 = senaraiKertasKandungan22;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan23() {
        return senaraiKertasKandungan23;
    }

    public void setSenaraiKertasKandungan23(List<PermohonanKertasKandungan> senaraiKertasKandungan23) {
        this.senaraiKertasKandungan23 = senaraiKertasKandungan23;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan24() {
        return senaraiKertasKandungan24;
    }

    public void setSenaraiKertasKandungan24(List<PermohonanKertasKandungan> senaraiKertasKandungan24) {
        this.senaraiKertasKandungan24 = senaraiKertasKandungan24;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List[] getSenaraiPerakuanPengarah() {
        return senaraiPerakuanPengarah;
    }

    public void setSenaraiPerakuanPengarah(List[] senaraiPerakuanPengarah) {
        this.senaraiPerakuanPengarah = senaraiPerakuanPengarah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanKertasKandungan getSyorPengarah() {
        return syorPengarah;
    }

    public void setSyorPengarah(PermohonanKertasKandungan syorPengarah) {
        this.syorPengarah = syorPengarah;
    }

    public static Logger getLogger() {
        return logger;
    }

//    public static void setLogger(Logger logger) {
//        KertasRencanaMMKNPLPTActionBean.logger = logger;
//    }
    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil1() {
        return senaraiKertasKandunganBil1;
    }

    public void setSenaraiKertasKandunganBil1(List<PermohonanKertasKandungan> senaraiKertasKandunganBil1) {
        this.senaraiKertasKandunganBil1 = senaraiKertasKandunganBil1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil2() {
        return senaraiKertasKandunganBil2;
    }

    public void setSenaraiKertasKandunganBil2(List<PermohonanKertasKandungan> senaraiKertasKandunganBil2) {
        this.senaraiKertasKandunganBil2 = senaraiKertasKandunganBil2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil3() {
        return senaraiKertasKandunganBil3;
    }

    public void setSenaraiKertasKandunganBil3(List<PermohonanKertasKandungan> senaraiKertasKandunganBil3) {
        this.senaraiKertasKandunganBil3 = senaraiKertasKandunganBil3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil4() {
        return senaraiKertasKandunganBil4;
    }

    public void setSenaraiKertasKandunganBil4(List<PermohonanKertasKandungan> senaraiKertasKandunganBil4) {
        this.senaraiKertasKandunganBil4 = senaraiKertasKandunganBil4;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getSubtajuk() {
        return subtajuk;
    }

    public void setSubtajuk(String subtajuk) {
        this.subtajuk = subtajuk;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public etanahActionBeanContext getCtx() {
        return ctx;
    }

    public void setCtx(etanahActionBeanContext ctx) {
        this.ctx = ctx;
    }

    public PermohonanKertasKandungan getKertasKemaskini() {
        return kertasKemaskini;
    }

    public void setKertasKemaskini(PermohonanKertasKandungan kertasKemaskini) {
        this.kertasKemaskini = kertasKemaskini;
    }

    public List<PermohonanRujukanLuar> getMohonRujLuarList() {
        return mohonRujLuarList;
    }

    public void setMohonRujLuarList(List<PermohonanRujukanLuar> mohonRujLuarList) {
        this.mohonRujLuarList = mohonRujLuarList;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public PembetulanService getPembetulanService() {
        return pembetulanService;
    }

    public void setPembetulanService(PembetulanService pembetulanService) {
        this.pembetulanService = pembetulanService;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public List<LaporanTanah> getSenaraiLaporanTanah() {
        return senaraiLaporanTanah;
    }

    public void setSenaraiLaporanTanah(List<LaporanTanah> senaraiLaporanTanah) {
        this.senaraiLaporanTanah = senaraiLaporanTanah;
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

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public KertasRisalatDAO getKertasRisalatDAO() {
        return kertasRisalatDAO;
    }

    public void setKertasRisalatDAO(KertasRisalatDAO kertasRisalatDAO) {
        this.kertasRisalatDAO = kertasRisalatDAO;
    }

    public KertasRisalat getRisalat() {
        return risalat;
    }

    public void setRisalat(KertasRisalat risalat) {
        this.risalat = risalat;
    }

    public UlasanJabatanTeknikal getUlasanJabatanTeknikal() {
        return ulasanJabatanTeknikal;
    }

    public void setUlasanJabatanTeknikal(UlasanJabatanTeknikal ulasanJabatanTeknikal) {
        this.ulasanJabatanTeknikal = ulasanJabatanTeknikal;
    }

    public List<UlasanJabatanTeknikal> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<UlasanJabatanTeknikal> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public PermohonanRujukanLuar getMohonRujLuarAdun() {
        return mohonRujLuarAdun;
    }

    public void setMohonRujLuarAdun(PermohonanRujukanLuar mohonRujLuarAdun) {
        this.mohonRujLuarAdun = mohonRujLuarAdun;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }
    
}
