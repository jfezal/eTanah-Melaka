/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;

import etanah.model.KodKeputusan;
import etanah.model.Pemohon;
import etanah.model.PemohonTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodSekatanKepentingan;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pengguna;
import etanah.service.PelupusanPtService;
import java.util.List;
import etanah.view.etanahActionBeanContext;

import etanah.service.PelupusanService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/draf_pertimbangan_ptd_catit_mcl/{currentStage}")
public class DrafPertimbanganPTDCatitmclActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PelupusanService plpservice;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private DisLaporanTanahSempadan disLaporanTanahSempadan;
    private KodKeputusan kodKpsn;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private PemohonTanah pemohonTanah;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    //private PermohonanKertas permohonanKertas;
    //private PermohonanKertasKandungan permohonanKK;
    private String tajuk;
    private String viewOnly;
    //private HakmilikPermohonan hmp ;
    private PermohonanKertas ppmn;
    private PermohonanKertasKandungan kertasKandungan;
    private String daerah;
    private String smpdnU;
    private String smpdnS;
    private String smpdnT;
    private String smpdnB;
    private String cwgn;
    private String jarak;
    private String tujuan;
    private String perihalPermohonan;
    private String perihalPermohonan2;
    boolean test;
    boolean simpan;
    boolean notSimpan = true;
    private String kodSementara = "TT";
    private String stageId;
    private Pengguna pguna;
    private BigDecimal cukaiSebenar;
    private List<PermohonanKertasKandungan> senaraiKandunganPenolongPTD = new Vector();
    private List<PermohonanKertasKandungan> senaraiSyarat = new Vector();
    private List<PermohonanKertasKandungan> senaraiKandunganTujuan = new Vector();
    private List<PermohonanKertasKandungan> senaraiKandunganPerihal = new Vector();
    private String idPermohonan;
    private String huraianpptd;
    private String huraianpptd2;
    private String huraianpptd3;
    private String kand;
    private FasaPermohonan fasaPermohonan;
    private BigDecimal premium;
    final static String SYSTEM_NEWLINE = "\n";
    final static float COMPLEXITY = 5.12f;  //Reducing this will increase efficiency but will decrease effectiveness

    @DefaultHandler
    public Resolution showForm() {
        HttpSession httpSession = getContext().getRequest().getSession();
        viewOnly = "false";
        httpSession.setAttribute("viewOnly", viewOnly);
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");

    }

    public Resolution viewRencanaOnly() {
        HttpSession httpSession = getContext().getRequest().getSession();
        viewOnly = "true";
        httpSession.setAttribute("viewOnly", viewOnly);
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        viewOnly = (String) getContext().getRequest().getSession().getAttribute("viewOnly");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List hakmilikL = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        hakmilikPermohonan = (HakmilikPermohonan) hakmilikL.get(0);
        if (!permohonan.getKodUrusan().getKod().equals("PLPS")) {
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        }
        fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
        if (hakmilik != null) {
            Double convert = 0.00;
            BigDecimal doubleVal = (hakmilik.getCukaiSebenar());
            convert = Double.parseDouble(doubleVal.toString()) / 2;
            BigDecimal b = new BigDecimal(convert);
            b = b.setScale(0, BigDecimal.ROUND_UP).setScale(2);
            cukaiSebenar = b;
        }

        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
        senaraiPemohon = pemohonDAO.findByEqualCriterias(tname, tvalue, null);
        pemohon = (Pemohon) senaraiPemohon.get(0);

        List kertas = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null);
        List<PermohonanKertas> listppmn = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null);

        daerah = hakmilik != null ? hakmilik.getDaerah().getNama().toUpperCase() : null;
        if (listppmn.isEmpty()) {
            getContext().getRequest().setAttribute("data", Boolean.FALSE);
            //ppmn = new PermohonanKertas();
            tajuk = "Permohonan Untuk Menjadikan Tanah Milik Sebagai Tanah Adat Melaka(MCL) Daripada ";
            int count = 0;
            String collecPemohon = new String();
            for (Pemohon pemhon : senaraiPemohon) {
                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                if (senaraiPemohon.size() > 1) {
                    if (count != senaraiPemohon.size()) {
                        collecPemohon = collecPemohon + pelUtil.convertStringtoInitCap(pemhon.getPihak().getNama()) + ", ";
                        count++;
                    }
                } else if (senaraiPemohon.size() == 1) {
                    collecPemohon = collecPemohon + pelUtil.convertStringtoInitCap(pemhon.getPihak().getNama());
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                tajuk = tajuk + collecPemohon;
            } else {
                tajuk = tajuk + collecPemohon + " Tanahnya Jenis " + hakmilik.getKodHakmilik().getNama() + " Dan No. Hakmilik HSM " + hakmilik.getNoHakmilik() + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka.";
            }
            // }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                tujuan = "Tujuan kertas ini untuk mendapatkan pertimbangan Pentadbir Tanah mengenai permohonan daripada " + collecPemohon + " No. Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + "sebagai Tanah Adat Melaka (Malacca Customary Land)";
            
                perihalPermohonan = "Pentadbir Tanah " + " telah menerima permohonan daripada " + collecPemohon + " No. Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + " pada " + sdf.format(permohonan.getInfoAudit().getTarikhMasuk()) + "sebagai Tanah Adat Melaka (Malacca Customary Land)";
                perihalPermohonan2 = "Pentadbir Tanah juga telah menerima permohonanan serah dan mohon semula (Borang 12A dan Jadual 1) pada " + sdf.format(permohonan.getInfoAudit().getTarikhMasuk()) + " supaya hakmilik tersebut menjadi Tanah MCL Kekal.";
                huraianpptd = "Permohonan daripada " + collecPemohon + " untuk menjadikan tanahnya sebagai Tanah Adat Melaka (MCL) "
                    + ", " + "dicatat (endorse) sebagai Tanah Adat Melaka (Malacca Customary Land) mengikut peruntukan Seksyen 109(A) Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka 1963).";
                huraianpptd2 = "Setelah catatan sebagai Tanah Adat Melaka (Malacca Customary Land) diselesaikan, Pentadbir Tanah memberikan persetujuan mengeluarkan hakmilik dalam daftar Hakmilik MCL"
                    + " Pejabat Tanah kepada pemilik tanah tersebut, setelah pemilik berkenaan membuat penyerahan dan memohon milik semula tanah tersebut sebagai Tanah Adat Melaka (Malacca Customary Land)"
                    + " hendaklah dikemukakan untuk pertimbangan Pentadbir Tanah " + ".";
                huraianpptd3 = "Permohonan daripada " + collecPemohon + " No. Kad Pengenalan : " + pemohon.getPihak().getNoPengenalan() + ", untuk mendapatkan hakmilik Tanah Adat Melaka (MCL) Kekal "
                    + " diluluskan dengan dikenakan syarat-syarat seperti berikut :-";
            } else {
                tujuan = "Tujuan kertas ini untuk mendapatkan pertimbangan Pentadbir Tanah " + hakmilik.getDaerah().getNama() + " mengenai permohonan daripada " + collecPemohon + " No. Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + " untuk mendaftarkan tanahnya Jenis No. Hakmilik " + hakmilik.getNoHakmilik() + " Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka sebagai Tanah Adat Melaka (Malacca Customary Land)";
            
                perihalPermohonan = "Pentadbir Tanah " + hakmilik.getDaerah().getNama() + " telah menerima permohonan daripada " + collecPemohon + " No. Kad Pengenalan: " + pemohon.getPihak().getNoPengenalan() + " pada " + sdf.format(permohonan.getInfoAudit().getTarikhMasuk()) + " untuk mendaftarkan tanahnya Jenis No. Hakmilik " + hakmilik.getNoHakmilik() + " Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka sebagai Tanah Adat Melaka (Malacca Customary Land)";
                perihalPermohonan2 = "Pentadbir Tanah juga telah menerima permohonanan serah dan mohon semula (Borang 12A dan Jadual 1) pada " + sdf.format(permohonan.getInfoAudit().getTarikhMasuk()) + " supaya hakmilik tersebut menjadi Tanah MCL Kekal.";
                huraianpptd = "Permohonan daripada " + collecPemohon + " untuk menjadikan tanahnya sebagai Tanah Adat Melaka (MCL) Jenis dan No.Hakmilik " + hakmilik.getKodHakmilik().getNama() + hakmilik.getNoHakmilik() + " Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama()
                    + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka dicatat (endorse) sebagai Tanah Adat Melaka (Malacca Customary Land) mengikut peruntukan Seksyen 109(A) Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka 1963).";
                huraianpptd2 = "Setelah catatan sebagai Tanah Adat Melaka (Malacca Customary Land) diselesaikan, Pentadbir Tanah " + hakmilik.getDaerah().getNama() + " memberikan persetujuan mengeluarkan hakmilik dalam daftar Hakmilik MCL"
                    + " Pejabat Tanah kepada pemilik tanah tersebut, setelah pemilik berkenaan membuat penyerahan dan memohon milik semula tanah tersebut sebagai Tanah Adat Melaka (Malacca Customary Land)"
                    + " hendaklah dikemukakan untuk pertimbangan Pentadbir Tanah " + hakmilik.getDaerah().getNama() + ".";
                huraianpptd3 = "Permohonan daripada " + collecPemohon + " No. Kad Pengenalan : " + pemohon.getPihak().getNoPengenalan() + ", untuk mendapatkan hakmilik Tanah Adat Melaka (MCL) Kekal ke atas tanah No. Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik() + "," + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                    + " diluluskan dengan dikenakan syarat-syarat seperti berikut :-";
            }

        } else {
            getContext().getRequest().setAttribute("data", Boolean.TRUE);
            this.ppmn = listppmn.get(0);
            tajuk = ppmn.getTajuk();
            for (int j = 0; j < ppmn.getSenaraiKandungan().size(); j++) {
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn = ppmn.getSenaraiKandungan().get(j);

                if (kertasKdgn.getBil() == 4) {
                    if (kertasKdgn.getSubtajuk().equals("1")) {
                        huraianpptd3 = kertasKdgn.getKandungan();
                    }
                }

            }

        }

        String currentStage = getContext().getRequest().getParameter("currentStage");
        if (!StringUtils.isBlank(currentStage)) {
            if (currentStage.equals("kemasukan")) {
                simpanMohonKertasRehy(hakmilikPermohonan.getId());
            }
        }
        settingLotSempadan();
        ppmn = plpservice.findMohonanKertasByIdPermohonan(idPermohonan);
        if (ppmn != null) {
            senaraiKandunganPenolongPTD = plpservice.findByIdLapor(ppmn.getIdKertas(), 3);
            senaraiKandunganPerihal = plpservice.findByIdLapor(ppmn.getIdKertas(), 2);
            senaraiKandunganTujuan = plpservice.findByIdLapor(ppmn.getIdKertas(), 1);
        }

        if (hakmilikPermohonan.getNilaianJpph() != null) {
            premium = hakmilikPermohonan.getNilaianJpph();
        }

    }

    public void settingLotSempadan() {
        /*
         * Added For Dynamic Lot-Lot Sempadan
         */
        disLaporanTanahSempadan = new DisLaporanTanahSempadan();
        disLaporanTanahSempadan.setListLaporTanahSpdnU(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnB(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnS(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnT(new ArrayList());
        LaporanTanah laporanTanah = new LaporanTanah();
        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
        if (laporanTanah != null) {
            List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnU().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
            }
        }

    }

    public Resolution simpanPremium() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hm = new HakmilikPermohonan();
        hm = pelPtService.findMohonHakmilik(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();

        if (hm != null) {
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            hm.setInfoAudit(infoAudit);
            hm.setNilaianJpph(premium);

            pelPtService.saveHakmilikPermohonan(hm);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        }

        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");

    }

    public void simpanMohonKertasRehy(Long idMohonHM) {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String error = "";
        String msg = "";
        String kndgn = tujuan;
        String kndgn2 = perihalPermohonan;
        String kndgn6 = perihalPermohonan2;
        String kndgn3 = huraianpptd;
        String kndgn4 = huraianpptd2;
        String kndgn5 = huraianpptd3;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        ppmn = plpservice.findMohonanKertasByIdPermohonan(idPermohonan);

        if (ppmn != null) {

            // ppmn = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setTajuk(tajuk);
            ppmn.setKodDokumen(kodDokumenDAO.findById("RENC"));
            plpservice.simpanPermohonanKertas(ppmn);
//            if (!ppmn.getSenaraiKandungan().isEmpty()) {
//
//                for (int j = 0; j < ppmn.getSenaraiKandungan().size(); j++) {
//                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
//                    kertasKdgn = ppmn.getSenaraiKandungan().get(j);
//
//                   if((kertasKdgn.getBil() == 1) && kertasKdgn.getSubtajuk().equals("1")){
//                    
//                    kertasKdgn.setKandungan(kndgn);
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
//                    }
//                    else if((kertasKdgn.getBil() == 2) && kertasKdgn.getSubtajuk().equals("1")){
//                    kertasKdgn.setKandungan(kndgn2);
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);    
//                    }
//                   else if((kertasKdgn.getBil() == 4) && kertasKdgn.getSubtajuk().equals("1")){
//                    kertasKdgn.setKandungan(kndgn5);
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);    
//                    }
//
//                }
//            }

        } else {

            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            ppmn = new PermohonanKertas();
            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setTajuk(tajuk);
            ppmn.setKodDokumen(kodDokumenDAO.findById("RENC"));
            plpservice.simpanPermohonanKertas(ppmn);

            infoAudit.setTarikhMasuk(new java.util.Date());
            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            kertasKdgn.setKertas(ppmn);
            kertasKdgn.setBil(1);
            kertasKdgn.setSubtajuk("1");
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKandungan(kndgn);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn);

            PermohonanKertasKandungan kertasKdgn2 = new PermohonanKertasKandungan();
            kertasKdgn2.setKertas(ppmn);
            kertasKdgn2.setBil(2);
            kertasKdgn2.setSubtajuk("1");
            kertasKdgn2.setInfoAudit(infoAudit);
            kertasKdgn2.setKandungan(kndgn2);
            kertasKdgn2.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn2);

            PermohonanKertasKandungan kertasKdgn6 = new PermohonanKertasKandungan();
            kertasKdgn6.setKertas(ppmn);
            kertasKdgn6.setBil(2);
            kertasKdgn6.setSubtajuk("2");
            kertasKdgn6.setInfoAudit(infoAudit);
            kertasKdgn6.setKandungan(kndgn6);
            kertasKdgn6.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn6);

            PermohonanKertasKandungan kertasKdgn3 = new PermohonanKertasKandungan();
            kertasKdgn3.setKertas(ppmn);
            kertasKdgn3.setBil(3);
            kertasKdgn3.setSubtajuk("1");
            kertasKdgn3.setInfoAudit(infoAudit);
            kertasKdgn3.setKandungan(kndgn3);
            kertasKdgn3.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn3);

            PermohonanKertasKandungan kertasKdgn4 = new PermohonanKertasKandungan();
            kertasKdgn4.setKertas(ppmn);
            kertasKdgn4.setBil(3);
            kertasKdgn4.setSubtajuk("2");
            kertasKdgn4.setInfoAudit(infoAudit);
            kertasKdgn4.setKandungan(kndgn4);
            kertasKdgn4.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn4);

            PermohonanKertasKandungan kertasKdgn5 = new PermohonanKertasKandungan();
            kertasKdgn5.setKertas(ppmn);
            kertasKdgn5.setBil(4);
            kertasKdgn5.setSubtajuk("1");
            kertasKdgn5.setInfoAudit(infoAudit);
            kertasKdgn5.setKandungan(kndgn5);
            kertasKdgn5.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn5);
        }
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
        HakmilikPermohonan mohonHakmilikPermohonan = new HakmilikPermohonan();
        mohonHakmilikPermohonan = hakmilikPermohonanDAO.findById(idMohonHM);
        if (mohonHakmilikPermohonan != null) {
            KodSekatanKepentingan kodSekatan = new KodSekatanKepentingan();
//            kodSekatan = plpservice.findSekatanByCawAndKodSekatan(permohonan.getCawangan().getKod(),"1010101"); //NEED TO USED ID FIRST SINCE ERROR WITH CLOB
            kodSekatan = kodSekatanKepentinganDAO.findById("31510005");
            infoAudit = mohonHakmilikPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonHakmilikPermohonan.setInfoAudit(infoAudit);
            mohonHakmilikPermohonan.setSekatanKepentinganBaru(kodSekatan);
            plpservice.simpanHakmilikPermohonan(mohonHakmilikPermohonan);
        }
        simpan = true;

    }

    public Resolution simpanMohonKertas() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String kndgn = tujuan;
        String kndgn2 = perihalPermohonan;
        String kndgn3 = huraianpptd;
        String kndgn4 = huraianpptd2;
        String kndgn5 = huraianpptd3;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        ppmn = plpservice.findMohonanKertasByIdPermohonan(idPermohonan);
        if (ppmn != null) {

            // ppmn = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setKodDokumen(kodDokumenDAO.findById("RENC"));
            ppmn.setTajuk(tajuk);
            plpservice.simpanPermohonanKertas(ppmn);
            if (!ppmn.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < ppmn.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = ppmn.getSenaraiKandungan().get(j);
                    if ((kertasKdgn.getBil() == 1) && kertasKdgn.getSubtajuk().equals("1")) {
                        kertasKdgn.setKandungan(kndgn);
                        kertasKdgn.setInfoAudit(infoAudit);
                        plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
                    } else if ((kertasKdgn.getBil() == 2) && kertasKdgn.getSubtajuk().equals("1")) {
                        kertasKdgn.setKandungan(kndgn2);
                        kertasKdgn.setInfoAudit(infoAudit);
                        plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
                    } else if ((kertasKdgn.getBil() == 4) && kertasKdgn.getSubtajuk().equals("1")) {
                        kertasKdgn.setKandungan(kndgn5);
                        kertasKdgn.setInfoAudit(infoAudit);
                        plpservice.simpanPermohonanKertasKandungan(kertasKdgn);
                    }
//                    else if(kertasKdgn.getBil() == 3 && kertasKdgn.getSubtajuk().equals("1")){
//                    kertasKdgn.setKandungan(kndgn3);
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);    
//                    }else if(kertasKdgn.getBil() == 3 && kertasKdgn.getSubtajuk().equals("2")){
//                    kertasKdgn.setKandungan(kndgn4);
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    plpservice.simpanPermohonanKertasKandungan(kertasKdgn);    
//                    }

                }
            }

        } else {

            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            ppmn = new PermohonanKertas();
            ppmn.setInfoAudit(infoAudit);
            ppmn.setPermohonan(permohonan);
            ppmn.setCawangan(permohonan.getCawangan());
            ppmn.setKodDokumen(kodDokumenDAO.findById("RENC"));
            ppmn.setTajuk(tajuk);

            plpservice.simpanPermohonanKertas(ppmn);

            infoAudit.setTarikhMasuk(new java.util.Date());
            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            kertasKdgn.setKertas(ppmn);
            kertasKdgn.setBil(1);
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKandungan(kndgn);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn);

            PermohonanKertasKandungan kertasKdgn2 = new PermohonanKertasKandungan();
            kertasKdgn2.setKertas(ppmn);
            kertasKdgn2.setBil(2);
            kertasKdgn2.setInfoAudit(infoAudit);
            kertasKdgn2.setKandungan(kndgn2);
            kertasKdgn2.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn2);

            PermohonanKertasKandungan kertasKdgn3 = new PermohonanKertasKandungan();
            kertasKdgn3.setKertas(ppmn);
            kertasKdgn3.setBil(3);
            kertasKdgn3.setSubtajuk("1");
            kertasKdgn3.setInfoAudit(infoAudit);
            kertasKdgn3.setKandungan(kndgn3);
            kertasKdgn3.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn3);

            PermohonanKertasKandungan kertasKdgn4 = new PermohonanKertasKandungan();
            kertasKdgn4.setKertas(ppmn);
            kertasKdgn4.setBil(3);
            kertasKdgn3.setSubtajuk("2");
            kertasKdgn4.setInfoAudit(infoAudit);
            kertasKdgn4.setKandungan(kndgn4);
            kertasKdgn4.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn4);

            PermohonanKertasKandungan kertasKdgn5 = new PermohonanKertasKandungan();
            kertasKdgn5.setKertas(ppmn);
            kertasKdgn5.setBil(4);
            kertasKdgn5.setSubtajuk("1");
            kertasKdgn5.setInfoAudit(infoAudit);
            kertasKdgn5.setKandungan(kndgn5);
            kertasKdgn5.setCawangan(permohonan.getCawangan());
            plpservice.simpanPermohonanKertasKandungan(kertasKdgn5);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        simpan = true;

        //return new RedirectResolution(DrafPertimbanganPTDmclActionBean.class, "showForm").addParameter("error", error) ;
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandungan2() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String kandungan = getContext().getRequest().getParameter("kand");
        PermohonanKertas mohonKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idMohon, "RENC");

        List<PermohonanKertasKandungan> senaraiPermohonanKertasKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiPermohonanKertasKandungan = disLaporanTanahService.getPelupusanService().findByIdLapor(mohonKertas.getIdKertas(), 4);

        if (!senaraiPermohonanKertasKandungan.isEmpty()) {
            for (PermohonanKertasKandungan pkk : senaraiPermohonanKertasKandungan) {
                pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "update", pengguna));
                pkk.setKandungan(kandungan);
                plpservice.simpanPermohonanKertasKandungan(pkk);
            }
        }

        rehydrate();

        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandungan() throws ParseException {

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                updateKandungan(1, kand);

                break;
            case 2:

                updateKandungan(2, kand);

                break;
            case 3:

                updateKandungan(3, kand);

                break;

        }
        rehydrate();

        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 1);
                senaraiKandunganTujuan.add(pkk);
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                senaraiKandunganPerihal.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                senaraiKandunganPenolongPTD.add(pkk);
                break;

            default:
        }
        System.out.println(index);
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");
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
        return new JSP("pelupusan/draf_PTD_Catit_MCL.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
//        logger.info(permohonankertas.getKodDokumen().getKod());

        if (ppmn != null) {
            infoAudit = ppmn.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            ppmn = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        ppmn.setTajuk(tajuk);
        KodDokumen kod = kodDokumenDAO.findById("RENC");
        ppmn.setKodDokumen(kod);
        ppmn.setCawangan(cawangan);
        ppmn.setInfoAudit(infoAudit);
        ppmn.setPermohonan(permohonan);
        plpservice.simpanPermohonanKertas(ppmn);

        long a = ppmn.getIdKertas();
        List<PermohonanKertasKandungan> plk = plpservice.findByIdLapor(a, i);

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
        pLK.setKertas(ppmn);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        plpservice.simpanPermohonanKertasKandungan(pLK);

    }

    private String justifyOperation(String s, float width) {
        float holder = (float) (COMPLEXITY * Math.random());
        while (s.contains(Float.toString(holder))) {
            holder = (float) (COMPLEXITY * Math.random());
        }
        String holder_string = Float.toString(holder);
        float lessThan = width;
        int timeOut = 100;
        int current = 0;
        while (s.length() < lessThan && current < timeOut) {
            s = s.replaceFirst(" ([^" + holder_string + "])", " " + holder_string + "$1");
            lessThan = s.length() + lessThan;
            current++;
        }
        String cleaned = s.replaceAll(holder_string, " ");
        return cleaned;
    }

    private String justify(String s, float width) {
        while (s.length() < width) {
            s = justifyOperation(s, width);
        }
        return s;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PemohonTanah getPemohonTanah() {
        return pemohonTanah;
    }

    public void setPemohonTanah(PemohonTanah pemohonTanah) {
        this.pemohonTanah = pemohonTanah;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    //public PermohonanKertas getPermohonanKertas() {
    //    return permohonanKertas;
    //  }
    // public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
    //     this.permohonanKertas = permohonanKertas;
    // }
    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public KodKeputusan getKodKpsn() {
        return kodKpsn;
    }

    public void setKodKpsn(KodKeputusan kodKpsn) {
        this.kodKpsn = kodKpsn;
    }

    public KodKeputusanDAO getKodKpsnDAO() {
        return kodKpsnDAO;
    }

    public void setKodKpsnDAO(KodKeputusanDAO kodKpsnDAO) {
        this.kodKpsnDAO = kodKpsnDAO;
    }

    public String getSmpdnB() {
        return smpdnB;
    }

    public void setSmpdnB(String smpdnB) {
        this.smpdnB = smpdnB;
    }

    public String getSmpdnS() {
        return smpdnS;
    }

    public void setSmpdnS(String smpdnS) {
        this.smpdnS = smpdnS;
    }

    public String getSmpdnT() {
        return smpdnT;
    }

    public void setSmpdnT(String smpdnT) {
        this.smpdnT = smpdnT;
    }

    public String getSmpdnU() {
        return smpdnU;
    }

    public void setSmpdnU(String smpdnU) {
        this.smpdnU = smpdnU;
    }

    public String getCwgn() {
        return cwgn;
    }

    public void setCwgn(String cwgn) {
        this.cwgn = cwgn;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isNotSimpan() {
        return notSimpan;
    }

    public void setNotSimpan(boolean notSimpan) {
        this.notSimpan = notSimpan;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public PermohonanKertas getPpmn() {
        return ppmn;
    }

    public void setPpmn(PermohonanKertas ppmn) {
        this.ppmn = ppmn;
    }

    public String getKodSementara() {
        return kodSementara;
    }

    public void setKodSementara(String kodSementara) {
        this.kodSementara = kodSementara;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(String viewOnly) {
        this.viewOnly = viewOnly;
    }

    public DisLaporanTanahSempadan getDisLaporanTanahSempadan() {
        return disLaporanTanahSempadan;
    }

    public void setDisLaporanTanahSempadan(DisLaporanTanahSempadan disLaporanTanahSempadan) {
        this.disLaporanTanahSempadan = disLaporanTanahSempadan;
    }

    public BigDecimal getCukaiSebenar() {
        return cukaiSebenar;
    }

    public void setCukaiSebenar(BigDecimal cukaiSebenar) {
        this.cukaiSebenar = cukaiSebenar;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPenolongPTD() {
        return senaraiKandunganPenolongPTD;
    }

    public void setSenaraiKandunganPenolongPTD(List<PermohonanKertasKandungan> senaraiKandunganPenolongPTD) {
        this.senaraiKandunganPenolongPTD = senaraiKandunganPenolongPTD;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getHuraianpptd() {
        return huraianpptd;
    }

    public void setHuraianpptd(String huraianpptd) {
        this.huraianpptd = huraianpptd;
    }

    public String getHuraianpptd2() {
        return huraianpptd2;
    }

    public void setHuraianpptd2(String huraianpptd2) {
        this.huraianpptd2 = huraianpptd2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerihal() {
        return senaraiKandunganPerihal;
    }

    public void setSenaraiKandunganPerihal(List<PermohonanKertasKandungan> senaraiKandunganPerihal) {
        this.senaraiKandunganPerihal = senaraiKandunganPerihal;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganTujuan() {
        return senaraiKandunganTujuan;
    }

    public void setSenaraiKandunganTujuan(List<PermohonanKertasKandungan> senaraiKandunganTujuan) {
        this.senaraiKandunganTujuan = senaraiKandunganTujuan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getHuraianpptd3() {
        return huraianpptd3;
    }

    public void setHuraianpptd3(String huraianpptd3) {
        this.huraianpptd3 = huraianpptd3;
    }

    public List<PermohonanKertasKandungan> getSenaraiSyarat() {
        return senaraiSyarat;
    }

    public void setSenaraiSyarat(List<PermohonanKertasKandungan> senaraiSyarat) {
        this.senaraiSyarat = senaraiSyarat;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getKand() {
        return kand;
    }

    public void setKand(String kand) {
        this.kand = kand;
    }
}
