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
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;

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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/kertas_rencana_mmkn_plpt")
public class KertasRencanaMMKNPLPTActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KertasRencanaMMKNPLPTActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
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
    DokumenDAO dokumenDAO;
    @Inject
    RegService regService;
    @Inject
    PembetulanService pembetulanService;
    private Permohonan permohonan;
    private String idPermohonan;
    private PermohonanKertas permohonanKertas;
    private String kertasBil;
    private String kertasTahun;
    private String tempat;
    private String tarikhmesyuarat;
    private String tarikhSah;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
    private List<PermohonanRujukanLuar> mohonRujLuarList;
    private PermohonanPihak penerima;
//    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private FasaPermohonan mohonFasa;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan21;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan22;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan23;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan24;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil1;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil2;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil3;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil4;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil5;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil6;
    private List<PermohonanKertasKandungan> senaraiKertasKandunganBil7;
    private PermohonanRujukanLuar mohonRujLuarAdun;
    private String tajuk;
    private Hakmilik hakmilik;
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
    String namaProjek;
    BigDecimal totalLuas = new BigDecimal(0.00);
    etanahActionBeanContext ctx;
    private PermohonanKertasKandungan kertasKemaskini;
    private String kodCaw;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/Kertas_Rencana_MMKN_PLPT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodCaw = pengguna.getKodCawangan().getKod();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        List<FasaPermohonan> mohonFasaList = pendudukanSementaraMMKNService.findFasaPermohonankpsnRisalat(idPermohonan);
        mohonRujLuarList = pembetulanService.searchMohonRujByIDPermohonan(idPermohonan);
        if (mohonFasaList.size() > 0) {
            mohonFasa = mohonFasaList.get(0);
        }

        mohonRujLuarList = pembetulanService.searchMohonRujByIDPermohonan(idPermohonan);
        mohonRujLuarAdun = pembetulanService.searchMohonRujByIDPermohonanAdun(idPermohonan);

        mohonRujLuarList = pembetulanService.searchMohonRujByIDPermohonan(idPermohonan);
        mohonRujLuarList.remove(mohonRujLuarAdun);
//        mohonRujLuarAdun = pendudukanSementaraMMKNService.mohonRujLuarByADUN(idPermohonan);
             permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonanMMKND(idPermohonan);

       

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            KodDokumen doc = new KodDokumen();
            doc.setKod("MMKND");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setTarikhMasuk(new Date());
            infoAudit.setDimasukOleh(pengguna);


            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setKodDokumen(doc);
            permohonanKertas.setInfoAudit(infoAudit);

            if (tempat == null) {
                tempat = "Tiada Data";
            }
            if (kertasBil == null) {
                kertasBil = "Tiada Data";
            }
            permohonanKertas.setTempatSidang(tempat);
            permohonanKertas.setTajuk(kertasBil);
            permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);
            senaraiKertasKandunganBil1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil4 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil5 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil6 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandunganBil7 = new ArrayList<PermohonanKertasKandungan>();
            
             permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonanMMKND(idPermohonan);
        PermohonanKertasKandungan mhnKertasKand = pendudukanSementaraMMKNService.findbil1ByIdKertasAndIdRujukan(permohonanKertas.getIdKertas(), '4', mohonRujLuarAdun.getIdRujukan());
        if (permohonanKertas != null) {
            if (mhnKertasKand == null) {
                List<PermohonanKertasKandungan> listKertasKand = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), Integer.parseInt("4"));
//                if (listKertasKand.size() <= 0) {
                Integer subTajukInt = Integer.parseInt(listKertasKand.isEmpty()?"0":listKertasKand.get(0).getSubtajuk());
                subTajukInt = subTajukInt + 1;

                String ulasan = "Tiada Ulasan Dibuat";

                infoAudit = new InfoAudit();
                infoAudit.setTarikhMasuk(new Date());
                infoAudit.setDimasukOleh(pengguna);

                PermohonanKertasKandungan kertasKandNew = new PermohonanKertasKandungan();

                kertasKandNew.setBil(Integer.parseInt("4"));
                kertasKandNew.setSubtajuk(String.valueOf(subTajukInt));
                if (mohonRujLuarAdun.getUlasan() != null) {
                    kertasKandNew.setKandungan(mohonRujLuarAdun.getUlasan());
                } else {
                    kertasKandNew.setKandungan(ulasan);
                }
                kertasKandNew.setRujukan(mohonRujLuarAdun);
                kertasKandNew.setAgensi(mohonRujLuarAdun.getAgensi());
                kertasKandNew.setKertas(permohonanKertas);
                kertasKandNew.setCawangan(permohonan.getCawangan());
                kertasKandNew.setInfoAudit(infoAudit);

                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasKandNew);
//                }
            }
        }
        for (PermohonanRujukanLuar mrl : mohonRujLuarList) {
            PermohonanKertasKandungan mhnKertasKand2 = pendudukanSementaraMMKNService.findbil1ByIdKertasAndIdRujukan(permohonanKertas.getIdKertas(), '4', mrl.getIdRujukan());

            if (permohonanKertas != null) {
                if (mhnKertasKand == null) {
                    List<PermohonanKertasKandungan> listKertasKand = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), Integer.parseInt("4"));
//                if (listKertasKand.size() <= 0) {
                    Integer subTajukInt = Integer.parseInt(listKertasKand.get(0).getSubtajuk());
                    subTajukInt = subTajukInt + 1;

                    String ulasan = "Tiada Ulasan Dibuat";

                     infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new Date());
                    infoAudit.setDimasukOleh(pengguna);

                    PermohonanKertasKandungan kertasKandNew = new PermohonanKertasKandungan();

                    kertasKandNew.setBil(Integer.parseInt("7"));
                    kertasKandNew.setSubtajuk(String.valueOf(subTajukInt));
                    if (mrl.getUlasan() != null) {
                        kertasKandNew.setKandungan(mrl.getUlasan());
                    } else {
                        kertasKandNew.setKandungan(ulasan);
                    }
                    kertasKandNew.setRujukan(mrl);
                    kertasKandNew.setAgensi(mrl.getAgensi());
                    kertasKandNew.setKertas(permohonanKertas);
                    kertasKandNew.setCawangan(permohonan.getCawangan());
                    kertasKandNew.setInfoAudit(infoAudit);

                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasKandNew);
//                }
                }
            }
        }

        if (permohonanKertas == null) {
            List<PermohonanKertasKandungan> listKertasKand = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), Integer.parseInt("7"));
            if (listKertasKand.size() <= 0) {
                Integer subTajukInt = Integer.parseInt(listKertasKand.get(0).getSubtajuk());
                subTajukInt = subTajukInt + 1;

                String ulasan = "Sila Kemaskini Ulasan anda";

                 infoAudit = new InfoAudit();
                infoAudit.setTarikhMasuk(new Date());
                infoAudit.setDimasukOleh(pengguna);

                PermohonanKertasKandungan kertasKandNew = new PermohonanKertasKandungan();

                kertasKandNew.setBil(Integer.parseInt("7"));
                kertasKandNew.setSubtajuk(String.valueOf(subTajukInt));
                kertasKandNew.setKandungan(mohonRujLuarAdun.getUlasan());
                kertasKandNew.setKertas(permohonanKertas);
                kertasKandNew.setCawangan(permohonan.getCawangan());
                kertasKandNew.setInfoAudit(infoAudit);

                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasKandNew);
            }
        }
            senaraiKertasKandunganBil4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);
                        senaraiKertasKandunganBil7 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 7);


//            simpan();
        } else {
            senaraiKertasKandunganBil1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 1);
            senaraiKertasKandunganBil2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);
            senaraiKertasKandunganBil3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
            senaraiKertasKandunganBil4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);
            senaraiKertasKandunganBil5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);

            senaraiKertasKandunganBil6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);

            senaraiKertasKandunganBil7 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 7);



        }
        if (senaraiKertasKandunganBil1.isEmpty()) {
            tujuan = "Tujuan kertas Ini adalah untuk memohon Perlanjutan Tempoh Pajakan Bagi hakmilik Ini";
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setTarikhMasuk(new Date());
            infoAudit.setDimasukOleh(pengguna);
//            for (PermohonanKertasKandungan mkd : senaraiKertasKandunganBil1) {
            PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
            pkk.setBil(1);
            pkk.setSubtajuk("1");
            pkk.setKandungan(tujuan);
            pkk.setKertas(permohonanKertas);
            pkk.setCawangan(permohonan.getCawangan());
            pkk.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(pkk);
            rehydrate();

//            }
        }


    }

    public Resolution kemaskiniMMKN() {

//        String idKandungan = (String) getContext().getRequest().getSession().getAttribute("idKandungan");
        String idKandungan = (String) getContext().getRequest().getParameter("idKandungan");
        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        return new JSP("pengambilan/kemaskini_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTanah() {

        String idHakmilikPihak = ctx.getRequest().getParameter("idKandungan");
        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        return new JSP("pengambilan/kemaskini_kertas_MMKN.jsp").addParameter("tab", "true");
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

    public Resolution saveKemaskini() throws ParseException {

        String kemaskini = (String) getContext().getRequest().getParameter("kandunganTajuk");
        String idKandungan = (String) getContext().getRequest().getParameter("idKandungan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//         String idKandungan = (String) getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan kertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(pengguna);
        if (kemaskini != null) {
            kertasKand.setKandungan(kemaskini);
            kertasKand.setInfoAudit(infoAudit);

        }
        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasKand);
        rehydrate();

        return new RedirectResolution(etanah.view.stripes.pengambilan.KertasRencanaMMKNPLPTActionBean.class, "showForm");

    }

    public Resolution deletKertasKand() throws ParseException {

        String idKandungan = (String) getContext().getRequest().getParameter("idKandungan");
        kertasKemaskini = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        permohonanKertasKandunganDAO.delete(kertasKemaskini);
        pendudukanSementaraMMKNService.deleteKertasKandungan(kertasKemaskini);

        rehydrate();

        return new RedirectResolution(etanah.view.stripes.pengambilan.KertasRencanaMMKNPLPTActionBean.class, "showForm");
    }

    public Resolution TambahPerihalPemohon() throws ParseException {

        String bilangan = (String) getContext().getRequest().getParameter("bil");
        String idKertas = (String) getContext().getRequest().getParameter("idKertas");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanKertasKandungan> listKertasKand = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), Integer.parseInt(bilangan));
        String subTajuk = null;
        if (listKertasKand.size() == 0) {
            subTajuk = "0";
        } else {
            subTajuk = listKertasKand.get(0).getSubtajuk();
        }
        Integer subTajukInt = Integer.parseInt(subTajuk);
        subTajukInt = subTajukInt + 1;

        String ulasan = "Sila Kemaskini Ulasan anda";

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(pengguna);

        PermohonanKertasKandungan kertasKandNew = new PermohonanKertasKandungan();

        kertasKandNew.setBil(Integer.parseInt(bilangan));
        kertasKandNew.setSubtajuk(String.valueOf(subTajukInt));
        kertasKandNew.setKandungan(ulasan);
        kertasKandNew.setKertas(permohonanKertas);
        kertasKandNew.setCawangan(permohonan.getCawangan());
        kertasKandNew.setInfoAudit(infoAudit);

        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasKandNew);

        rehydrate();

        return new RedirectResolution(etanah.view.stripes.pengambilan.KertasRencanaMMKNPLPTActionBean.class, "showForm");
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKND");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);

        kertasBil = getContext().getRequest().getParameter("permohonanKertas.tajuk");
//        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        tempat = getContext().getRequest().getParameter("permohonanKertas.tempatSidang");
        if (kertasBil == null) {
            kertasBil = "Tiada Data";
        }
        permohonanKertas.setTajuk(kertasBil + "/" + permohonan.getUntukTahun());

        tarikhmesyuarat = getContext().getRequest().getParameter("permohonanKertas.tarikhSidang");
        tarikhSah = getContext().getRequest().getParameter("permohonanKertas.tarikhSahKeputusan");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
        Date tarikhSidang = dateFormat.parse(tarikhmesyuarat);
        Date tarikhsah = dateFormat.parse(tarikhSah);
        permohonanKertas.setTarikhSidang(tarikhSidang);
        permohonanKertas.setTarikhSahKeputusan(tarikhsah);



        if (tempat == null) {
            tempat = "Tiada Data";
        }
        permohonanKertas.setTempatSidang(tempat);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(6);
            kandungan = getContext().getRequest().getParameter("heading");
            if (kandungan == null) {
                headingObj.setKandungan("Tiada");
            } else {
                headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if (kandungan == null) {
                tujuanObj.setKandungan("Tiada");
            } else {
                tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

//        senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 21);
//        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
//
//        for (int i = 1; i <= kira1; i++) {
//            if (senaraiKertasKandungan21.size() != 0 && i <= senaraiKertasKandungan21.size()) {
//                Long id = senaraiKertasKandungan21.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(21);
//            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
//            if (kandungan == null) {
//                kandungan = "TIADA DATA";
//            }
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//        }
//
//        senaraiKertasKandungan22 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 22);
//        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
//
//        for (int i = 1; i <= kira2; i++) {
//            if (senaraiKertasKandungan22.size() != 0 && i <= senaraiKertasKandungan22.size()) {
//                Long id = senaraiKertasKandungan22.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan2.setKertas(permohonanKertas);
//            permohonanKertasKandungan2.setBil(22);
//            kandungan = getContext().getRequest().getParameter("kandungan2" + i);
//            if (kandungan == null) {
//                kandungan = "TIADA DATA";
//            }
//            permohonanKertasKandungan2.setKandungan(kandungan);
//            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan2.setSubtajuk("2.2." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan2.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
//        }
//
//        senaraiKertasKandungan23 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 23);
//        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//
//        for (int i = 1; i <= kira3; i++) {
//            if (senaraiKertasKandungan23.size() != 0 && i <= senaraiKertasKandungan23.size()) {
//                Long id = senaraiKertasKandungan23.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan3.setKertas(permohonanKertas);
//            permohonanKertasKandungan3.setBil(23);
//            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
//            if (kandungan == null) {
//                kandungan = "TIADA DATA";
//            }
//            permohonanKertasKandungan3.setKandungan(kandungan);
//            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan3.setSubtajuk("2.3." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan3.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
//        }
//
//        senaraiKertasKandungan24 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 24);
//        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
//
//        for (int i = 1; i <= kira4; i++) {
//            if (senaraiKertasKandungan24.size() != 0 && i <= senaraiKertasKandungan24.size()) {
//                Long id = senaraiKertasKandungan24.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan4.setKertas(permohonanKertas);
//            permohonanKertasKandungan4.setBil(24);
//            kandungan = getContext().getRequest().getParameter("kandungan4" + i);
//            if (kandungan == null) {
//                kandungan = "TIADA DATA";
//            }
//            permohonanKertasKandungan4.setKandungan(kandungan);
//            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan4.setSubtajuk("2.4." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan4.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
//        }
//
//        if (ringkasanPermohonan == null) {
//            ringkasanPermohonan = new PermohonanKertasKandungan();
//        }
//        ringkasanPermohonan.setKertas(permohonanKertas);
//        ringkasanPermohonan.setBil(25);
//        kandungan = getContext().getRequest().getParameter("ringkasanPermohonan.kandungan");
//        if (kandungan == null) {
//            kandungan = "TIADA DATA";
//        }
//        ringkasanPermohonan.setKandungan(kandungan);
//        ringkasanPermohonan.setCawangan(permohonan.getCawangan());
//        InfoAudit iaP = new InfoAudit();
//        iaP.setTarikhMasuk(new Date());
//        iaP.setDimasukOleh(peng);
//        ringkasanPermohonan.setInfoAudit(iaP);
//        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ringkasanPermohonan);
//
//        if (syorPengarah == null) {
//            syorPengarah = new PermohonanKertasKandungan();
//        }
//        syorPengarah.setKertas(permohonanKertas);
//        syorPengarah.setBil(3);
//        kandungan = getContext().getRequest().getParameter("syorPengarah.kandungan");
//        if (kandungan == null) {
//            kandungan = "TIADA DATA";
//        }
//        syorPengarah.setKandungan(kandungan);
//        syorPengarah.setCawangan(permohonan.getCawangan());
//        InfoAudit iaSP = new InfoAudit();
//        iaSP.setTarikhMasuk(new Date());
//        iaSP.setDimasukOleh(peng);
//        syorPengarah.setInfoAudit(iaSP);
//        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(syorPengarah);
//
//        count4 = Integer.parseInt(getContext().getRequest().getParameter("count4"));
//        for (int i = 1; i <= count4; i++) {
//            String subtajuk = "4." + i;
//            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
//            int rowCount4 = Integer.parseInt(getContext().getRequest().getParameter("count4" + i));
//            for (int k = 1; k <= rowCount4; k++) {
//                if (senaraiKertasKandungan4 != null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
//                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                        infoAudit = permohonanKertasKandungan2.getInfoAudit();
//                        infoAudit.setDiKemaskiniOleh(peng);
//                        infoAudit.setTarikhKemaskini(new java.util.Date());
//                    }
//                } else {
//                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
//                    infoAudit.setDimasukOleh(peng);
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                }
//
//                permohonanKertasKandungan2.setKertas(permohonanKertas);
//                permohonanKertasKandungan2.setBil(4);
//                kandungan = getContext().getRequest().getParameter("perakuanPengarah" + i + k);
//                if (kandungan == null) {
//                    permohonanKertasKandungan2.setKandungan("Tiada");
//                } else {
//                    permohonanKertasKandungan2.setKandungan(kandungan);
//                }
//
//                permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
//                permohonanKertasKandungan2.setSubtajuk("4." + i + "." + str[k - 1]);
//                InfoAudit iaPP = new InfoAudit();
//                iaPP.setTarikhMasuk(new Date());
//                iaPP.setDimasukOleh(peng);
//                permohonanKertasKandungan2.setInfoAudit(iaPP);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
//            }
//        }//if table count
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new RedirectResolution(KertasRencanaMMKNPLPTActionBean.class, "locate");
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

    public static void setLogger(Logger logger) {
        KertasRencanaMMKNPLPTActionBean.logger = logger;
    }

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

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getTarikhSah() {
        return tarikhSah;
    }

    public void setTarikhSah(String tarikhSah) {
        this.tarikhSah = tarikhSah;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil5() {
        return senaraiKertasKandunganBil5;
    }

    public void setSenaraiKertasKandunganBil5(List<PermohonanKertasKandungan> senaraiKertasKandunganBil5) {
        this.senaraiKertasKandunganBil5 = senaraiKertasKandunganBil5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil6() {
        return senaraiKertasKandunganBil6;
    }

    public void setSenaraiKertasKandunganBil6(List<PermohonanKertasKandungan> senaraiKertasKandunganBil6) {
        this.senaraiKertasKandunganBil6 = senaraiKertasKandunganBil6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandunganBil7() {
        return senaraiKertasKandunganBil7;
    }

    public void setSenaraiKertasKandunganBil7(List<PermohonanKertasKandungan> senaraiKertasKandunganBil7) {
        this.senaraiKertasKandunganBil7 = senaraiKertasKandunganBil7;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }
}
